package com.shy.lunbotu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shy.lunbotu.lunbotu.LunBoTuActivity;
import com.shy.lunbotu.myView.ggk.GgkActivity;
import com.shy.lunbotu.myView.pintOrCanvas.jb.JbACtivity;
import com.shy.lunbotu.myView.pintOrCanvas.lphz.LphzActivity;
import com.shy.lunbotu.myView.pintOrCanvas.paint.MyViewActivity;
import com.shy.lunbotu.myView.tpbz.TpbzActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View lbt = findViewById(R.id.lbt);
        View myview = findViewById(R.id.myview);
        View viewById = findViewById(R.id.tpbz);
        View viewById1 = findViewById(R.id.jb);
        View viewById2 = findViewById(R.id.ggk);
        View viewById3 = findViewById(R.id.button);

        lbt.setOnClickListener(this);
        myview.setOnClickListener(this);
        viewById.setOnClickListener(this);
        viewById1.setOnClickListener(this);
        viewById2.setOnClickListener(this);
        viewById3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lbt :
                startActivity(new Intent(MainActivity.this, LunBoTuActivity.class));
                break;
            case R.id.myview :
                startActivity(new Intent(MainActivity.this, LphzActivity.class));
                break;

            case R.id.tpbz:
                startActivity(new Intent(MainActivity.this, TpbzActivity.class));
                break;
            case R.id.jb:
                startActivity(new Intent(MainActivity.this, JbACtivity.class));
                break;
            case R.id.ggk:
                startActivity(new Intent(MainActivity.this, GgkActivity.class));
                break;
            case R.id.button:
                startActivity(new Intent(MainActivity.this, MyViewActivity.class));
                break;
                default:
        }
    }
}
