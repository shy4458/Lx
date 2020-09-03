package com.shy.lunbotu.myView.pintOrCanvas.lphz;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//离屏绘制
public class LphzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new myFermodeView(this));
    }
}
