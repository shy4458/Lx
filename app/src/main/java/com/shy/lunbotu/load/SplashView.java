package com.shy.lunbotu.load;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

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
    //斜对角的一半，扩散的最大半径
    private float mDistance;
    //六小球半径
    private float mCircleRadius = 18;
    //旋转大圆半径
    private float mRotateRadius = 90;

    //当前大圆旋转角度
    private float mCurrentRotateAngle = 0F;
    //当前大圆半径
    private float mCurrentRotateRaduis = mRotateRadius;
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
        mCenterY = h * 1f / 2;
        mDistance = (float) (Math.hypot(w, h) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mState == null){
            mState = new RotateState();
            mState.drawState(canvas);
        }else {
            mState.drawState(canvas);
        }

    }

    private abstract class SplashState{
        abstract void drawState(Canvas canvas);
    }
    private SplashState mState;
    //1.旋转
    private class RotateState extends SplashState{

        private RotateState(){
            mValueAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI * 2));
            mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mRotateDuration);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    /**
                     * 这个方法是多次执行的，每次都会获得一个递增的mCurrentRotateAngle值，
                     * 在调用invalidate()使onDraw重新执行，在调用这个方法，依次循环，所以这个动画并没有实际的动画效果，
                     * 只为了得到下次onDraw绘制时所要使用的值。
                     */
                    mCurrentRotateAngle = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mState = new MerginState();
                }
            });
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            //绘制背景
            drawBackgound(canvas);
            //绘制小球
            drawCircles(canvas);
        }
    }

    private void drawCircles(Canvas canvas) {
        //每个小球之间的角度
        float rotateAngle= (float) (Math.PI *2 / mCircleColors.length);
        for (int i = 0; i < mCircleColors.length; i++) {
            float angle = i * rotateAngle + mCurrentRotateAngle;
            float cx = (float)(Math.cos(angle) * mCurrentRotateRaduis + mCenterX);
            float cy = (float)(Math.sin(angle) * mCurrentRotateRaduis + mCenterY);
            mPaint.setColor(mCircleColors[i]);
            canvas.drawCircle(cx,cy,mCircleRadius,mPaint);
        }
    }

    private void drawBackgound(Canvas canvas) {
        if (mCurrentHoleRadius > 0){
            //第三种动画 绘制空心圆
            /**
             * 实现这个空心圆的效果实际上是改变.setStrokeWidth()中的值，从最大变为最小。
             * 圆的半径从最小变为最大
             */
            float strokeWidth = mDistance - mCurrentHoleRadius;
            float radius = strokeWidth / 2 + mCurrentHoleRadius;
            mHolePaint.setStrokeWidth(strokeWidth);
            canvas.drawCircle(mCenterX,mCenterY,radius,mHolePaint);
        }else {
            canvas.drawColor(mBackgroundColor);
        }
    }

    //2.扩散聚合
    private class MerginState extends SplashState{

        private MerginState(){
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius,mRotateRadius);
            mValueAnimator.setDuration(mRotateDuration);
            mValueAnimator.setInterpolator(new OvershootInterpolator(10f));
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotateRaduis = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mState = new ExpandState();
                }
            });
            //动画反向执行
            mValueAnimator.reverse();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackgound(canvas);
            drawCircles(canvas);
        }
    }
    //3.水波纹
    private class ExpandState extends SplashState{

        public ExpandState() {
            mValueAnimator = ValueAnimator.ofFloat(mCircleRadius,mDistance);
            mValueAnimator.setDuration(mRotateDuration);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentHoleRadius = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
//            mValueAnimator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                }
//            });
            //动画反向执行
            mValueAnimator.start();
        }

        @Override
        void drawState(Canvas canvas) {
            drawBackgound(canvas);
        }
    }
}
