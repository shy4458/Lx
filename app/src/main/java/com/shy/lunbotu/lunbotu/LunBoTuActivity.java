package com.shy.lunbotu.lunbotu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.shy.lunbotu.R;

import java.util.ArrayList;

public class LunBoTuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            vp.setCurrentItem(vp.getCurrentItem() + 1);
        }
    };
    private ViewPager vp;
    private int curponsition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunbotu);
        vp = findViewById(R.id.vp);
        int arr[] = {R.mipmap.bgcj,R.mipmap.bghj,R.mipmap.bghy,R.mipmap.bgnr,R.mipmap.sjcj,R.mipmap.ttcj,R.mipmap.tycj};
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(arr[i]);
            imageViews.add(imageView);
        }

        vp.setAdapter(new VpAdapter(imageViews,this));
        vp.addOnPageChangeListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(0);
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        curponsition = position;
        Log.d("//////", "onPageScrolled: " + position);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
//            if (curponsition == 0){
//                vp.setCurrentItem(3, false);//切换，不要动画效果
//            } else
                if (curponsition == 6) {
                    SystemClock.sleep(3000);
                vp.setCurrentItem(0, false);//切换，不要动画效果
            }
        }
    }
}
