package com.shy.lunbotu.pmsp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.shy.lunbotu.R;


/**
 *          density scaleDensity densityDpi
 *
 *          1.density表示屏幕密度  可以理解为某一个尺寸的分辨率 他的缩放比例
 *              每一寸的像素点是160个px 那么density的值就是1
 *              假设某一个设备 某英寸上边的像素点是320个px 那么density的值就是2
 *
 *          2.scaleDensity 字体缩放比例
 *          默认情况下 scaleDensity和density是一样的
 *
 *          3.densityDpi 某英寸上的像素点有多少个  例如1.中的160的值
 *
 *          ？？ 为什么更改这些可以做到屏幕适配
 *              因为控件最终都会转换为px来显示，不管设置什么单位，在最终加载完都会转换为px
 *              在TypedValue.java中的applyDimension()中转换的,通过源码可以得知，
 *              通过DispalyMetrics中的density scaleDensity来完成适配
 *
 */

public class DensityActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_density);
    }
}
