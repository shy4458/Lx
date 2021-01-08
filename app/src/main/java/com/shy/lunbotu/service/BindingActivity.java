package com.shy.lunbotu.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;

public class BindingActivity extends AppCompatActivity implements View.OnClickListener {

    LocalService mService;
    boolean mBound = false;

    private View start;
    private View stop;
    private View bind;
    private View unBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);

    }

    @Override
    protected void onResume() {
        super.onResume();


        initView();
        initEvent();
    }


    private void initView() {
        start = findViewById(R.id.startService);
        stop = findViewById(R.id.stopService);
        bind = findViewById(R.id.bindService);
        unBind = findViewById(R.id.unBindService);
    }

    private void initEvent() {
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unBind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, LocalService.class);
        switch (v.getId()) {
            case R.id.startService:
                startService(intent);
                break;
            case R.id.stopService:
                stopService(intent);
                break;
            case R.id.bindService:
                bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
                //参数3,当Activity去绑定Service的时候,如果Service没有创建,则让Service自动创建 通过bindService开启服务.
                break;
            case R.id.unBindService:
                unbindService(mConnection);
                break;
            default:
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mBound){
            unbindService(mConnection);
            mBound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //这个IBinder就是Service中onBind方法返回来的
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };
}
