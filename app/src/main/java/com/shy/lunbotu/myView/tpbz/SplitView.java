package com.shy.lunbotu.myView.tpbz;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.shy.lunbotu.R;

import java.util.ArrayList;

public class SplitView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private ArrayList<Ball> balls;
    private float d = 3;
    private ValueAnimator mAnimator;
    int i = 0;

    public SplitView(Context context) {
        this(context, null);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        balls = new ArrayList<>();
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
        Log.d("////////宽：",mBitmap.getWidth() + "");
        Log.d("////////高",mBitmap.getHeight() + "");

        for (int i = 0; i < mBitmap.getWidth(); i++) {
            for (int j = 0; j < mBitmap.getHeight(); j++) {
                Ball ball = new Ball();
                ball.color = mBitmap.getPixel(i, j);
                ball.x = i * d + d / 2;
                ball.y = j * d + d / 2;
                ball.r = d / 2;

                //速度
                ball.vX = (float)(Math.pow(-1,Math.ceil(Math.random()*1000)) *20 * Math.random());
                ball.vY = rangInt(-15,35);
                //加速度
                ball.aX = 0;
                ball.aY = 0.98f;

                balls.add(ball);
            }
        }
        Log.d("////////",balls.size() + "");

        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setRepeatCount(-1);
        mAnimator.setDuration(2000);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                updateBall();
                invalidate(); // 会触发onDraw方法

            }
        });

    }

    private int rangInt(int i, int j) {
        int max = Math.max(i, j);
        int min = Math.min(i, j);
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    private void updateBall() {
        for(Ball ball : balls){
            ball.x += ball.vX;
            ball.y += ball.vY;

            ball.vX += ball.aX;
            ball.vY += ball.aY;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(200,200);
        for (int i = 0; i <balls.size(); i++) {
            mPaint.setColor(balls.get(i).color);
            canvas.drawCircle(balls.get(i).x,balls.get(i).y,balls.get(i).r,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //执行动画 开始爆炸
            mAnimator.start();
        }
        return super.onTouchEvent(event);
    }
}
