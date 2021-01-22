package com.shy.lunbotu.surfaceView;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shy.lunbotu.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "RvAdapter";
    private Context context;
    private ArrayList<RvBeanInfo> list;
    private static SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private Boolean noPlay = true;//定义播放状态
    public GSYVideoOptionBuilder gsyVideoOptionBuilder;


    public RvAdapter(Context context, ArrayList<RvBeanInfo> list) {
        this.context = context;
        this.list = list;
        mediaPlayer = new MediaPlayer();//创建MediaPlayer对象
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置多媒体类型
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_surface_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.textView.setText(list.get(position).getTitle());
        viewHolder.item_var.setText(list.get(position).getVar());
        Map<String, String> header = new HashMap<>();
        header.put("ee", "33");
        //配置视频播放器参数
        gsyVideoOptionBuilder
                .setIsTouchWiget(false)
                .setUrl(list.get(position).getUrl())
                .setVideoTitle(list.get(position).getTitle())
                .setCacheWithPlay(false)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setPlayTag(TAG)
                .setMapHeadData(header)
                .setShowFullAnimation(true)
                .setNeedLockFull(true)
                .setPlayPosition(position)
                .setReleaseWhenLossAudio(false)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        if (!viewHolder.gsyVideoPlayer.isIfCurrentIsFullscreen()) {
                            //静音
                            GSYVideoManager.instance().setNeedMute(true);
                        }
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        //全屏不静音
                        //GSYVideoManager.instance().setNeedMute(true);
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        GSYVideoManager.instance().setNeedMute(false);
                        viewHolder.gsyVideoPlayer.getCurrentPlayer().getTitleTextView().setText((String) objects[position]);
                    }
                }).build(viewHolder.gsyVideoPlayer);

        //设置返回键
        viewHolder.gsyVideoPlayer.getBackButton().setVisibility(View.GONE);

        //设置全屏按键功能
        viewHolder.gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.gsyVideoPlayer.startWindowFullscreen(context, false, true);
            }
        });
        if (position == 0) {
            viewHolder.gsyVideoPlayer.startPlayLogic();
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        private final TextView item_var;
        private final StandardGSYVideoPlayer gsyVideoPlayer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
            gsyVideoPlayer = itemView.findViewById(R.id.item_gsyvideoplayer);
            imageView = itemView.findViewById(R.id.item_iv);
            textView = itemView.findViewById(R.id.item_title);
            item_var = itemView.findViewById(R.id.item_var);
        }
    }
}
