package com.shy.lunbotu.pmsp;

import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;

public class LhpspActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //判断是否是刘海屏
        boolean hasDisplayCutout = hasDisplayCutout(window);
        if (hasDisplayCutout) {
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
        }

        setContentView(R.layout.activity_lhpsp);

        //全面屏背景已经适配了 还是适配特殊情况，例如按钮太靠近状态栏了
        View button = findViewById(R.id.button);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) button.getLayoutParams();
        layoutParams.topMargin = heightForDisplayCutout();
        button.setLayoutParams(layoutParams);

    }

    //获取状态栏的高
    private int heightForDisplayCutout() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0){
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
}
