package com.shy.lunbotu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shy.lunbotu.lunbotu.LunBoTuActivity;
import com.shy.lunbotu.myView.MyViewActivity;
import com.shy.lunbotu.myView.tpbz.TpbzActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View lbt = findViewById(R.id.lbt);
        View myview = findViewById(R.id.myview);
        View viewById = findViewById(R.id.tpbz);

        lbt.setOnClickListener(this);
        myview.setOnClickListener(this);
        viewById.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lbt :
                startActivity(new Intent(MainActivity.this, LunBoTuActivity.class));
                break;
            case R.id.myview :
                startActivity(new Intent(MainActivity.this, MyViewActivity.class));
                break;

            case R.id.tpbz:
                startActivity(new Intent(MainActivity.this, TpbzActivity.class));
                break;
                default:
        }
    }
}
