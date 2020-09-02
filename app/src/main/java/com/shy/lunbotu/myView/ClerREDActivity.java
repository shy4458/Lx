package com.shy.lunbotu.myView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.shy.lunbotu.R;

public class ClerREDActivity extends View {


    private Paint paint;
    private Bitmap bitmap;

    public ClerREDActivity(Context context) {
        this(context,null);
    }

    public ClerREDActivity(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ClerREDActivity(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public ClerREDActivity(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bghy);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        * new LightingColorFilter(0x00ffff, 0x000000);
        * 这两个参数就去除了红色素
        *
        * */
//        LightingColorFilter lightingColorFilter = new LightingColorFilter(0x00ffff, 0x000000);
//        paint.setColorFilter(lightingColorFilter);
//        canvas.drawBitmap(bitmap,0,0,paint);

        //保存原始图片 new LightingColorFilter(0xffffff, 0x000000);
        LightingColorFilter lightingColorFilter = new LightingColorFilter(0xffffff, 0x000000);
        paint.setColorFilter(lightingColorFilter);
        canvas.drawBitmap(bitmap,0,0,paint);
    }
}
