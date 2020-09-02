package com.shy.lunbotu.myView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.shy.lunbotu.R;

//刮刮卡
public class MyGGKView extends View {

    private Paint paint;
    private Bitmap mTxtBmp;
    private Bitmap mSrcBmp;
    private Bitmap mDstBmp;
    private Path mPath;


    public MyGGKView(Context context) {
        this(context, null);
    }

    public MyGGKView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGGKView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MyGGKView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(80);

        //禁止硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        int newW = 1000;
        int newH = 800;
        //初始化图片对象
        mTxtBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.h);
//        int heightText = mTxtBmp.getHeight();
//        int widthText = mTxtBmp.getWidth();
//
//        float textW = (float)newW/widthText;
//        float textH = (float)newH/heightText;
//
//        Matrix matrix = new Matrix();
//        matrix.postScale(textW,textH);
//
//        Bitmap.createBitmap(mTxtBmp,0,0,widthText,heightText,matrix,true);

        mSrcBmp = BitmapFactory.decodeResource(getResources(), R.mipmap.z);

//        int heightSrc = mTxtBmp.getHeight();
//        int widthSrc = mTxtBmp.getWidth();
//
//        float srcW = (float)newW/widthText;
//        float srcH = (float)newH/heightText;
//
//        Matrix matrix1 = new Matrix();
//        matrix.postScale(srcW,srcH);
//
//        Bitmap.createBitmap(mSrcBmp,0,0,widthSrc,heightSrc,matrix1,true);


        mDstBmp = Bitmap.createBitmap(mSrcBmp.getWidth(), mSrcBmp.getHeight(), Bitmap.Config.ARGB_8888);

        //路径
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mTxtBmp, 0, 0, paint);
        int layer = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        Canvas dstCanvas = new Canvas(mDstBmp);
        dstCanvas.drawPath(mPath, paint);


        canvas.drawBitmap(mDstBmp, 0, 0, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        canvas.drawBitmap(mSrcBmp, 0, 0, paint);

        paint.setXfermode(null);

        canvas.restoreToCount(layer);
    }


    private float x;
    private float y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                float v = (event.getX() - x) / 2 + x;
                float w = (event.getY() - y) / 2 + y;
                mPath.quadTo(x,y,v,w);
                x = event.getX();
                y = event.getY();
                break;
        }
        invalidate();
        return true;
    }
}
