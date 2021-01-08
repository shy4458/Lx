package com.shy.lunbotu.bitmap;

import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;


public class BigBitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigbitmap);
        ImageView iv = findViewById(R.id.iv_bigBitmap);
//        iv.setImageBitmap(getRes("bitmap"));
//        Bitmap bitmap = getRes("bitmap");
//        int byteCount = bitmap.getByteCount();
//        int count = (byteCount/1024)/1024;
//        Log.d("/////",count + "M");






        //创建一个可选项对象，该对象用于配置图片的处理参数
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 将该参数设置为 true 则加载器不加载图片, 而是把图片的 out(宽和高)的字段信息取出来
        opts.inJustDecodeBounds = true;
        /**
         * 加载图片，该方法只是从图片文件中读取图片的宽和高信息，而没有真正的加载到内存中
         * 参数 1：是图片的地址
         * 参数 2：是加载图片时的配置信息
         */
        ApplicationInfo appInfo = getApplicationInfo();
        int resID = getResources().getIdentifier("bitmap", "mipmap", appInfo.packageName);
        BitmapFactory.decodeResource(getResources(), resID, opts);
        //获取到图片的宽和高信息
        int imageWidth = opts.outWidth;
        int imageHeight = opts.outHeight;
        // 获取到屏幕对象
        Display display = getWindowManager().getDefaultDisplay();
        // 获取到屏幕的真是宽和高
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();
        // 计算缩放比例
        int widthScale = imageWidth / screenWidth;
        int heightScale = imageHeight / screenHeight;
        //计算出最大的比例
        int scale = widthScale > heightScale ? widthScale : heightScale;
        // 使用缩放比例进行缩放加载图片
        opts.inJustDecodeBounds = false; // 加载器就会返回图片了
        // 配置该参数加载图片时 BitmapFactory 就会自动缩放图片
        opts.inSampleSize = scale;
        //重新加载图片，这次才是 真正的压缩加载图片
        int resID2 = getResources().getIdentifier("bitmap", "mipmap", appInfo.packageName);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resID2, opts);
        // 显示在屏幕上
        iv.setImageBitmap(bitmap);
        System.out.println(bitmap.getByteCount()/1024/1024 + "M");
    }

    public Bitmap getRes(String name,BitmapFactory.Options opts) {
        ApplicationInfo appInfo = getApplicationInfo();
        int resID = getResources().getIdentifier(name, "mipmap", appInfo.packageName);
        return BitmapFactory.decodeResource(getResources(), resID,opts);
    }
}
