package com.shy.lunbotu.myView.pintOrCanvas.lphz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class myFermodeView extends View {

    private Paint paint;
    private int width;
    private int height;

    public myFermodeView(Context context) {
        this(context,null);
    }

    public myFermodeView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public myFermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context,attrs,defStyleAttr,0);
    }

    public myFermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //在android api14之后 有些api是不支持硬件加速的 而系统默认是开启的硬件加速
        //禁止硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        setBackgroundColor(Color.GRAY);

        //离屏绘制
        int layer = canvas.saveLayer(0, 0, getHeight(), getWidth(), paint, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(createRectBitmap(width,height),0,0,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(createCirclrBitmap(width,height),0,0,paint);
        paint.setXfermode(null);

        canvas.restoreToCount(layer);
    }


    public Bitmap createRectBitmap(int width,int height){
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint dstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dstPaint.setColor(0xFF66AAFF);
        canvas.drawRect(new Rect(width/20,height/3,2*width/3,19*height/20),dstPaint);
        return bitmap;
    }

    public Bitmap createCirclrBitmap(int width,int height){
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint scrPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        scrPaint.setColor(0xFFFFCC44);
        canvas.drawCircle(width *2 /3,height/3,height/4,scrPaint);
        return bitmap;
    }
}
