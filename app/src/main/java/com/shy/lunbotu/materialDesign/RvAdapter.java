package com.shy.lunbotu.materialDesign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shy.lunbotu.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class RvAdapter  extends RecyclerView.Adapter<RvAdapter.RvViewHoder> {


    @NonNull
    @Override
    public RvViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);

        return new RvViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHoder holder, int position) {
        //头像
        Picasso.get().load(getAvatarResId(position)).centerInside().fit().into(holder.circleImageView);
        //内容
        Picasso.get().load(getContentResId(position)).centerInside().fit().into(holder.imageView);

        holder.textView.setText("NetTase " + position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    private int getAvatarResId(int p){
        switch (p % 4){
            case 0:

                return R.mipmap.bg;
            case 1:
                return R.mipmap.z;
            case 2:
                return R.mipmap.h;
            case 3:
                return R.mipmap.bg;
        }
        return 0;
    }

    private int getContentResId(int p){
        switch (p % 4){
            case 0:

                return R.mipmap.bg;
            case 1:
                return R.mipmap.z;
            case 2:
                return R.mipmap.h;
            case 3:
                return R.mipmap.bg;
        }
        return 0;
    }



    public static class RvViewHoder extends RecyclerView.ViewHolder{


        CircleImageView circleImageView;
        TextView textView;
        ImageView imageView;

        public RvViewHoder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.iv_avater);
            textView = itemView.findViewById(R.id.tv_nickname);
            imageView = itemView.findViewById(R.id.iv_content);

        }
    }
}
