package com.shy.lunbotu.surfaceView;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shy.lunbotu.R;

import java.util.ArrayList;

public class RvSurfaceViewActivity extends AppCompatActivity {

    private static final String URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private int playPosition = -1;
    private RvAdapter rvAdapter;
    private RecyclerView rv;
    private ArrayList<RvBeanInfo> rvBeanInfos;
    private OrientationUtils orientationUtils;
    private ScrollCalculatorHelper scrollCalculatorHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvsurfaceview);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        rvAdapter = new RvAdapter(this, rvBeanInfos);
        rv.setAdapter(rvAdapter);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //如果newState的状态==RecyclerView.SCROLL_STATE_IDLE;
                    //播放对应的视频
                    scrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                Log.d("//////", "onScrolled: " + firstVisibleItem);
                //实时获取设置 当前显示的GSYBaseVideoPlayer的下标
                scrollCalculatorHelper.onScroll(recyclerView, firstVisibleItem, lastVisibleItem, 4);

            }
        });
    }

    private void initData() {
        rvBeanInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rvBeanInfos.add(new RvBeanInfo("行走的大树", URL, i, "986"));
        }
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        //获取屏幕宽高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //自定播放帮助类 限定范围为屏幕一半的上下偏移180   括号里不用在意 因为是一个item一个屏幕
        scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.rv
                ,DpTools.dip2px(this, 260)
                ,DpTools.dip2px(this, 260));
    }

    @Override
    protected void onPause() {
        super.onPause();
//        gsyVideoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏

        } else if (ori == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏

        }
        super.onResume();
//        gsyVideoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
//        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            gsyVideoPlayer.getFullscreenButton().performClick();
//            return;
//        }
//        //释放所有
//        gsyVideoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }

}
