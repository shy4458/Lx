package com.shy.lunbotu.myView.carView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.shy.lunbotu.R;

public class CarView extends View {


    private Bitmap carBitmap; //小车
    private Path path;
    private PathMeasure pathMeasure;//路径计算
    private float distanceRatio = 0;
    private Paint circlePaint;//画圆圈的画笔
    private Paint carPaint;//小车的画笔
    private Matrix carMatrix;

    public CarView(Context context) {
        this(context, null);
    }

    public CarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        carBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.car);
        path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);
        pathMeasure = new PathMeasure(path, false);

        circlePaint = new Paint();
        circlePaint.setStrokeWidth(5);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLUE);

        carPaint = new Paint();
        carPaint.setStrokeWidth(5);
        carPaint.setStyle(Paint.Style.STROKE);
        carPaint.setAntiAlias(true);
        carPaint.setColor(Color.DKGRAY);

        carMatrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        carMatrix.reset();
        distanceRatio += 0.006f;
        if (distanceRatio >= 1){
            distanceRatio = 0;
        }




        canvas.drawPath(path, carPaint);
    }
}
