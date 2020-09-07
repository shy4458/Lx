package com.shy.lunbotu.pmsp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;


/**
 * 百分比适配 对容器的扩展，扩展属性，对容器的原有属性不影响
 */
public class BfbspAvtivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfbsp);
    }
}
