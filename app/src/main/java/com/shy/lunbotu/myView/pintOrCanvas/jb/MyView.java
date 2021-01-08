package com.shy.lunbotu.myView.pintOrCanvas.jb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.shy.lunbotu.R;

//渐变
public class MyView extends View {

    private Paint paint;
    private LinearGradient linearGradient;
    private Bitmap bitmap;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context,attrs,defStyleAttr,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bgcj);
        paint = new Paint();
        paint.setAntiAlias(true); //抗锯齿
        paint.setStyle(Paint.Style.FILL);   //填充效果
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //x0 y0 起始坐标 x1 y1 终止坐标
        //int是要渐变的颜色
        // float是渐变的位置数组，取值范围是0~1 作用是某个位置的颜色值 可以为null，就是线性渐变
        linearGradient = new LinearGradient(0, 0, 500, 500, new int[]{Color.RED, Color.BLUE, Color.GREEN}, new float[]{0f,0.75f,1}, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
//        canvas.drawCircle(250,250,250,paint); //圆
        canvas.drawRect(0,0,500,500,paint); //矩形

//      环形渐变 由里到外，
        RadialGradient radialGradient = new RadialGradient(750, 250, 250, new int[]{Color.RED, Color.BLUE, Color.GREEN}, null, Shader.TileMode.CLAMP);
        paint.setShader(radialGradient);
        canvas.drawCircle(750,250,250,paint);

        //旋转渐变
        SweepGradient sweepGradient = new SweepGradient(250, 750, Color.RED, Color.GREEN);
        paint.setShader(sweepGradient);
        canvas.drawCircle(250,750,250,paint);

        canvas.translate(0,1000);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        paint.setShader(bitmapShader);
//        canvas.drawCircle(250,250,250,paint);
        canvas.drawRect(0,0,bitmap.getWidth()*2,bitmap.getHeight()*2,paint);


    }
}
