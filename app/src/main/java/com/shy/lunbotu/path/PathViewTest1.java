package com.shy.lunbotu.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class PathViewTest1 extends View {

    private Paint mPaint;
    private Path mPath;

    public PathViewTest1(Context context) {
        this(context,null);
    }

    public PathViewTest1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PathViewTest1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public PathViewTest1(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mPaint.setStyle(Paint.Style.FILL);  //填充
//        mPath.moveTo(100,70);   //移动点  默认0,0
////        mPath.rLineTo(40,730); //  相对当前的path点来连线
//        mPath.lineTo(140,800);
//        mPath.lineTo(250,600);  //连线
//        mPath.close();        //设置曲线是否闭合（首尾相连）

//        //绘制弧形
//        mPath.addArc(200,200,400,400,-170,170);
//        //矩形 CW顺时针方向绘制，CCW逆时针方向绘制
//        mPath.addRect(500,500,900,900,Path.Direction.CW);
//        //圆
//        mPath.addCircle(700,700,200,Path.Direction.CCW);
//        //椭圆
//        mPath.addOval(0,0,500,300,Path.Direction.CCW);


//        mPath.moveTo(0,0);
//        mPath.lineTo(100,100);
//        //追加图形  设置true时移动原点，false会连接起来
//        mPath.arcTo(400,200,600,400,0,270,false);
//
//        Path path = new Path();
//        path.moveTo(500,500);
//        path.lineTo(600,600);
//        //添加路径
//        mPath.addPath(path);

//        //二阶贝塞尔曲线
//        mPath.moveTo(300,500);
//        mPath.quadTo(500,100,800,500);

        //三阶贝赛尔曲线
        mPath.moveTo(300,500);
        mPath.cubicTo(500,100,600,1200,800,500);



        canvas.drawPath(mPath,mPaint);


    }
}
