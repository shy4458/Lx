package com.shy.lunbotu.myView.tpbz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;

//图片粒子爆炸效果
public class TpbzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpbz);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bgcj);
        bitmap.getWidth();
        bitmap.getHeight();
        //传入位置 ，返回当前位置像素的颜色值
        int pixel = bitmap.getPixel(0, 0);



    }
}
