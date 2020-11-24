package com.shy.lunbotu.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;
import com.shy.lunbotu.utils.Reflex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ThreadPoolExecutor;

public class HookActivity extends AppCompatActivity implements View.OnClickListener {



    private View view1;
    private View view2;
    private Field field;
    private Object singletonObj;
    private Object iActivityManagerObj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hok);

        initView();
        initEvent();

    }

    private void initEvent() {
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
    }

    private void initView() {
        view1 = findViewById(R.id.hookActivity1);
        view2 = findViewById(R.id.hookActivity2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hookActivity1:
                startActivity(new Intent(HookActivity.this,HookActivity1.class));
                break;

            case R.id.hookActivity2:
                //hook
                hookFun();
                break;

                default:
                    break;
        }
    }

    private void hookFun() {
        Instrumentation instrumentation = (Instrumentation) Reflex.getFieldObject(Activity.class,HookActivity.this,"mInstrumentation");
        MyInstrumentation instrumentation1 = new MyInstrumentation(instrumentation);
        Reflex.setFieldObject(Activity.class,this,"mInstrumentation",instrumentation1);

    }
}
