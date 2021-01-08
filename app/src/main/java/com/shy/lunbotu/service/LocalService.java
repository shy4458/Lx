package com.shy.lunbotu.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class LocalService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();
    public class LocalBinder extends Binder {
        LocalService getService() {
            return LocalService.this;
        }
    }
    @Override
    public void onCreate() {
        Log.d("////", "onCreate");
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("////", "onBind");
//        绑定成功后系统要调用ServiceConnection对象上的onServiceConnected方法，
//        并且将service的onBind方法返回的对象作为实参传递过来了
        return mBinder;
    }
    public int getRandomNmber() {
        Log.d("////", "getRandomNmber");
        return mGenerator.nextInt(100);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("////", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("////", "onUnbind");
        return super.onUnbind(intent);
    }
    @Override
    public void onDestroy() {
        Log.d("////", "onDestroy");
    }
}
