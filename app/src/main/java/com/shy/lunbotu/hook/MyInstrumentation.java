package com.shy.lunbotu.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.shy.lunbotu.utils.Reflex;

public class MyInstrumentation extends Instrumentation {

    private Instrumentation instrumentation;

    public MyInstrumentation(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target, Intent intent, int requestCode, Bundle options) {

        Log.d("-----", "啦啦啦我是hook进来的!");
        Class[] classes = {Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class};
        Object[] objects = {who, contextThread, token, target, intent, requestCode, options};
        Log.d("-----", "啦啦啦我是hook进来的!!");
        return (Instrumentation.ActivityResult) Reflex.invokeInstanceMethod(instrumentation, "execStartActivity", classes, objects);

    }
}
