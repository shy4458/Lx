package com.shy.lunbotu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.mvp.ui.LoginActivity;
import com.shy.lunbotu.surfaceView.RvSurfaceViewActivity;
import com.shy.lunbotu.bitmap.BigBitmapActivity;
import com.shy.lunbotu.cjsCardView.CardViewActivity;
import com.shy.lunbotu.contentProvider.ProviderActivity;
import com.shy.lunbotu.hook.HookActivity;
import com.shy.lunbotu.inter.OkHttpActivity;
import com.shy.lunbotu.load.SplashActivity;
import com.shy.lunbotu.lunbotu.LunBoTuActivity;
import com.shy.lunbotu.materialDesign.MaterialDesignActivity;
import com.shy.lunbotu.music.MusicActivity;
import com.shy.lunbotu.myView.ggk.GgkActivity;
import com.shy.lunbotu.myView.pintOrCanvas.jb.JbACtivity;
import com.shy.lunbotu.myView.pintOrCanvas.lphz.LphzActivity;
import com.shy.lunbotu.myView.pintOrCanvas.paint.MyViewActivity;
import com.shy.lunbotu.myView.tpbz.TpbzActivity;
import com.shy.lunbotu.path.PathActivity;
import com.shy.lunbotu.path.PathMeasureActivity;
import com.shy.lunbotu.pmsp.BfbspAvtivity;
import com.shy.lunbotu.pmsp.DensityActivity;
import com.shy.lunbotu.pmsp.LhpspActivity;
import com.shy.lunbotu.pmsp.PmspActivity;
import com.shy.lunbotu.service.BindingActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

        }
    };

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
        View viewById4 = findViewById(R.id.splash);
        View viewById5 = findViewById(R.id.pathtest1);
        View viewById6 = findViewById(R.id.pathmeas);
        View viewById7 = findViewById(R.id.pmsp);
        View viewById8 = findViewById(R.id.bfbsp);
        View viewById9 = findViewById(R.id.density);
        View viewById10 = findViewById(R.id.lhp);
        View viewById11 = findViewById(R.id.MusicPlayer);
        View viewById12 = findViewById(R.id.cardView);
        View viewById13 = findViewById(R.id.car);
        View viewById14 = findViewById(R.id.hook);
        View viewById15 = findViewById(R.id.okhttp);
        View viewById16 = findViewById(R.id.service);
        View viewById17 = findViewById(R.id.contentProvider);
        View viewById18 = findViewById(R.id.bigBitmap);
        View viewById19 = findViewById(R.id.music);
        View viewById20 = findViewById(R.id.zhbjMain);
        View viewById21 = findViewById(R.id.GSYVideoPlayer);


        lbt.setOnClickListener(this);
        myview.setOnClickListener(this);
        viewById.setOnClickListener(this);
        viewById1.setOnClickListener(this);
        viewById2.setOnClickListener(this);
        viewById3.setOnClickListener(this);
        viewById4.setOnClickListener(this);
        viewById5.setOnClickListener(this);
        viewById6.setOnClickListener(this);
        viewById7.setOnClickListener(this);
        viewById8.setOnClickListener(this);
        viewById9.setOnClickListener(this);
        viewById10.setOnClickListener(this);
        viewById11.setOnClickListener(this);
        viewById12.setOnClickListener(this);
        viewById13.setOnClickListener(this);
        viewById14.setOnClickListener(this);
        viewById15.setOnClickListener(this);
        viewById16.setOnClickListener(this);
        viewById17.setOnClickListener(this);
        viewById18.setOnClickListener(this);
        viewById19.setOnClickListener(this);
        viewById20.setOnClickListener(this);
        viewById21.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lbt:
                startActivity(new Intent(MainActivity.this, LunBoTuActivity.class));
                break;
            case R.id.myview:
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
            case R.id.splash:
                startActivity(new Intent(MainActivity.this, SplashActivity.class));
                break;
            case R.id.pathtest1:
                startActivity(new Intent(MainActivity.this, PathActivity.class));
                break;
            case R.id.pathmeas:
                startActivity(new Intent(MainActivity.this, PathMeasureActivity.class));
                break;
            case R.id.pmsp:
                startActivity(new Intent(MainActivity.this, PmspActivity.class));
                break;
            case R.id.bfbsp:
                startActivity(new Intent(MainActivity.this, BfbspAvtivity.class));
                break;
            case R.id.density:
                startActivity(new Intent(MainActivity.this, DensityActivity.class));
                break;
            case R.id.lhp:
                startActivity(new Intent(MainActivity.this, LhpspActivity.class));
                break;
            case R.id.MusicPlayer:
                startActivity(new Intent(MainActivity.this, MaterialDesignActivity.class));
                break;
            case R.id.cardView:
                startActivity(new Intent(MainActivity.this, CardViewActivity.class));
                break;
            case R.id.car:
                startActivity(new Intent(MainActivity.this, RvSurfaceViewActivity.class));
                break;
            case R.id.hook:
                startActivity(new Intent(MainActivity.this, HookActivity.class));
                break;
            case R.id.okhttp:
                startActivity(new Intent(MainActivity.this, OkHttpActivity.class));
                break;
            case R.id.service:
                startActivity(new Intent(MainActivity.this, BindingActivity.class));
                break;
            case R.id.contentProvider:
                startActivity(new Intent(MainActivity.this, ProviderActivity.class));
                break;

            case R.id.bigBitmap:
                startActivity(new Intent(MainActivity.this, BigBitmapActivity.class));
                break;

            case R.id.music:
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
                break;

            case R.id.zhbjMain:
                startActivity(new Intent(MainActivity.this, com.shy.lunbotu.zhbj.activity.SplashActivity.class));
                break;
            case R.id.GSYVideoPlayer:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;

            default:
        }
    }
}
