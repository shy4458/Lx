package com.shy.lunbotu.load;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.shy.lunbotu.R;

public class SplashView extends View {

    //旋转圆画笔
    private Paint mPaint;
    //扩散圆画笔
    private Paint mHolePaint;
    //动画
    private ValueAnimator mValueAnimator;
    //背景色
    private int mBackgroundColor = Color.WHITE;
    private int[] mCircleColors;
    //旋转圆中心坐标
    private float mCenterX;
    private float mCenterY;
    //斜对角的一般，扩散的最大半径
    private float mDistance;
    //六小球半径
    private float mCircleRadius = 18;
    //旋转大圆半径
    private float mRotateRadius = 90;

    //当前大圆旋转角度
    private float mCurrentRotateAngle = 0F;
    //当前大圆半径
    private float getmCurrentRotateRaduis = mRotateRadius;
    //扩散圆的半径
    private float mCurrentHoleRadius = 0F;
    //表示旋转动画时长
    private int mRotateDuration = 1200;


    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint.setStyle(Paint.Style.STROKE);
        mHolePaint.setColor(mBackgroundColor);
        mCircleColors = context.getResources().getIntArray(R.array.splash_circle_colors);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w * 1f / 2;
        mCenterY = w * 1f / 2;
        mDistance = (float) (Math.hypot(w, h) / 2);
    }
}
