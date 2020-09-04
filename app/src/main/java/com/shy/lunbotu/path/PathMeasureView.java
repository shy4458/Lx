package com.shy.lunbotu.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class PathMeasureView extends View {

    private Paint mPaint;
    private Path mPath;

    public PathMeasureView(Context context) {
        this(context,null);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public PathMeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(getWidth()/2,getHeight()/2);
        mPath.lineTo(0,200);
        mPath.lineTo(200,200);
        mPath.lineTo(200,0);
//        mPath.close();

        canvas.drawPath(mPath,mPaint);

//        PathMeasure pathMeasure = new PathMeasure();
////        PathMeasure pathMeasure = new PathMeasure(mPath, false);
////        pathMeasure.setPath(mPath,true);
//        pathMeasure.setPath(mPath,false);
//        float length = pathMeasure.getLength();
//        Log.d("TAG",length + "");
        /**
         * 使用空参构造时，.setPath()第二个参数传入true时，会测量绘制的总长度加上最后一个点到起始点的长度
         * 如果传入false则只测量绘制的长度
         *
         * 使用有参构造时 传入的Path，booble 同.setPath()方法中一样道理
         *
         * 如果更改了Path 需要重新调用.setPath()方法
         */

    }
}
