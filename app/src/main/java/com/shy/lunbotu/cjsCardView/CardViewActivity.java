package com.shy.lunbotu.cjsCardView;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;


/**
 * 沉浸式状态栏、cardView
 */
public class CardViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_cardview);
        immersive();
        setHeightAndPadding(this,findViewById(R.id.toolbar));

    }

    private void immersive() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色透明
            window.setStatusBarColor(Color.TRANSPARENT);
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            //布局内容全屏展示
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            //防止内容区域大小发生变化
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            window.getDecorView().setSystemUiVisibility(systemUiVisibility);

        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

    }

    //获取状态栏的高度
    private int getStatusBarHeight(Context context){
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0){
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    //下移toolbar
    public void setHeightAndPadding(Context context,View view){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height += getStatusBarHeight(context);
        view.setPadding(view.getPaddingLeft(),view.getPaddingTop() + getStatusBarHeight(context),view.getPaddingRight(),view.getPaddingBottom());
    }
}
