package com.shy.lunbotu.zhbj.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;

import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener {

    private Handler mHandler = new Handler();
    private RelativeLayout splashRl;
    private LinearLayout splashLl;
    private Button splashJrxd;
    private Button splashJrzcx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //判断是否是刘海屏
//        boolean hasDisplayCutout = hasDisplayCutout(window);
//        if (hasDisplayCutout) {
        //2.允许内容延伸进刘海区
        WindowManager.LayoutParams params = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            /**
             * {                        LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT,  默认模式
             *                         LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES,   允许内容延伸进刘海区 ，父容器已经填充了刘海区
             *                         LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER})    不允许内容延伸进刘海
             */

            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(params);
        }

        //3.设置沉浸式
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        int visibility = window.getDecorView().getSystemUiVisibility();
        visibility |= flags;
        window.getDecorView().setSystemUiVisibility(visibility);
//        }
        setContentView(R.layout.zhbj_activity_splash);

        initView();
        initEvent();
    }


    private void initView() {
        splashRl = findViewById(R.id.splash_mm);
        splashLl = findViewById(R.id.splash_ll);
        splashJrxd = findViewById(R.id.splash_jrxd);
        splashJrzcx = findViewById(R.id.splash_jrzcx);

    }

    private void initEvent() {
        AnimationSet set = createrAnimation();
        splashRl.setAnimation(set);
        set.setAnimationListener(this);

        splashJrxd.setOnClickListener(this);
        splashJrzcx.setOnClickListener(this);
    }

    private AnimationSet createrAnimation() {
        AnimationSet set = new AnimationSet(false);
        //旋转
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2000);
        //缩放
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(2000);
        //透明度
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(1000);

        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        return set;

    }

    //获取状态栏的高
    private int heightForDisplayCutout() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 96;
    }

    //判断是否是全面屏
    private boolean hasDisplayCutout(Window window) {
        View decorView = window.getDecorView();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            WindowInsets insets = decorView.getRootWindowInsets(); //屏幕下错
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P && insets != null) {
                DisplayCutout displayCutout = insets.getDisplayCutout();
                if (displayCutout != null && displayCutout.getBoundingRects() != null && displayCutout.getBoundingRects().size() > 0 && displayCutout.getSafeInsetTop() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        splashLl.setVisibility(View.VISIBLE);
//        mHandler.postDelayed(new MyTask(), 2000);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.splash_jrxd:
                startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                finish();
                break;
            case R.id.splash_jrzcx:
                startActivity(new Intent(SplashActivity.this, ZhbjMainActivity.class));
                finish();
                break;
            default:

                break;
        }
    }

    class MyTask implements Runnable {

        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, ZhbjMainActivity.class));
            finish();
        }
    }
}
