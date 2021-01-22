package com.shy.lunbotu.zhbj.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.shy.lunbotu.R;
import com.shy.lunbotu.zhbj.adapter.GuideVpAdapter;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager guideVp;
    private ArrayList<ImageView> list;
    private LinearLayout containerGrayPoint;
    private int width;
    private View redPoint;
    private Button btStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WindowManager.LayoutParams params = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            /**
             * {LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT,       默认模式
             *  LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES,   允许内容延伸进刘海区 ，父容器已经填充了刘海区
             *  LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER})        不允许内容延伸进刘海
             */
            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(params);
        }
        //3.设置沉浸式
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        int visibility = window.getDecorView().getSystemUiVisibility();
        visibility |= flags;
        window.getDecorView().setSystemUiVisibility(visibility);
        setContentView(R.layout.zhbj_acitivty_guide);
        initView();
        initData();
        initEvent();

    }

    private void initView() {
        guideVp = findViewById(R.id.guideVp);
        containerGrayPoint = findViewById(R.id.container_gray_point);
        redPoint = findViewById(R.id.red_point);
        btStart = findViewById(R.id.bt_start);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(R.mipmap.splash_src);
            list.add(imageView);

            //创建3个小圆点添加到父布局中
            View view = new View(this);
            view.setBackgroundResource(R.drawable.point_gray_bg);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            layoutParams.rightMargin = 20;
            //addView 需要添加两个参数 第二个参数是父布局
            containerGrayPoint.addView(view, layoutParams);
        }


    }

    private void initEvent() {
        guideVp.setAdapter(new GuideVpAdapter(list));
        //小红点在Vp滑动的时候跟着移动,
        //分别获取三个灰点的左边距
        guideVp.addOnPageChangeListener(this);
        btStart.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (width == 0) {
            width = containerGrayPoint.getChildAt(1).getLeft() - containerGrayPoint.getChildAt(0).getLeft();
        }
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
        params.leftMargin = (int) (position * width + width * positionOffset);
        redPoint.setLayoutParams(params);
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 2) {
            btStart.setVisibility(View.VISIBLE);
        } else {
            btStart.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ZhbjMainActivity.class));
        finish();
    }
}
