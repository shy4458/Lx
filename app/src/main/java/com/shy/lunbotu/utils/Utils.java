package com.shy.lunbotu.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utils {

    private static Utils utils;

    //设计稿的宽高
    private static final float STANDARD_WIDTH = 720;
    private static final float STANDARD_HEIGHT = 1280;

    //屏幕显示宽高
    private int mDisplayWidth;
    private int mDisplayHeigh;

    private Utils(Context context){
        //获取屏幕宽高
        if ( mDisplayHeigh ==0 || mDisplayWidth == 0){
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (manager != null){
                //屏幕信息现实对象
                DisplayMetrics displayMetrics = new DisplayMetrics();
                manager.getDefaultDisplay().getMetrics(displayMetrics);
                if (displayMetrics.widthPixels > displayMetrics.heightPixels){
                    //横屏
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDisplayHeigh = displayMetrics.widthPixels;
                }else {
                    mDisplayHeigh = displayMetrics.heightPixels - getStatusBarHeight(context);
                    mDisplayWidth = displayMetrics.widthPixels;
                }
            }
        }


    }
    public int getStatusBarHeight(Context context){
        //获得状态栏高度
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0){
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }


    public static Utils getInstance(Context context){
        if (utils == null){
            utils = new Utils(context.getApplicationContext());
        }
        return utils;
    }

    //获得缩放比例
    public float getHorizontalScale(){
        return mDisplayWidth / STANDARD_WIDTH;
    }

    public float getVerticalScale(){
        return mDisplayHeigh / STANDARD_HEIGHT;
    }

}
