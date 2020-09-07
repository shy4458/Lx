package com.shy.lunbotu.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

public class Density {

    //参考设备的宽    单位dp
    private static final float WIDTH = 360;

    //表示屏幕密度
    private static float appDensity;

    //字体缩放比例，默认appDensity
    private static float appScaleDensity;

    public static void setDensity(final Application application, Activity activity){

        //获取当前app的屏幕显示信息
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0){
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;

            //添加字体监听的回调
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration newConfig) {
                    //更改系统配置的回调
                    if (newConfig != null && newConfig.fontScale > 0){
                        //字体发证更改
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        //计算目标值
        float targetDensity = displayMetrics.widthPixels / WIDTH;
        float targetScaleDensity = targetDensity*(appScaleDensity / appDensity);
        int targetDensityDpi = (int) (targetDensity * 160);

        //替换activity 的 density ScaleDensity ScaleDensityDpi的值

        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        dm.density = targetDensity;
        dm.scaledDensity = targetScaleDensity;
        dm.densityDpi = targetDensityDpi;

    }

}
