package com.shy.lunbotu.zhbj.activity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.shy.lunbotu.R;
import com.shy.lunbotu.zhbj.adapter.MainVpAdapter;
import com.shy.lunbotu.zhbj.fragments.FwFragment;
import com.shy.lunbotu.zhbj.fragments.SyFragment;
import com.shy.lunbotu.zhbj.fragments.XwFragment;
import com.shy.lunbotu.zhbj.fragments.ZwFragment;

import java.util.ArrayList;

public class ZhbjMainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private ViewPager mainVp;
    private RadioGroup mainRg;
    private RadioButton mainSy;
    private RadioButton mainFw;
    private RadioButton mainZw;
    private RadioButton mainXw;
    private RadioButton mainSz;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhbj_activity_main);
        initView();
        initData();
        initEvent();
        mainSy.setChecked(true);
    }

    private void initView() {
        mainVp = findViewById(R.id.main_vp);
        mainRg = findViewById(R.id.main_rg);
        mainSy = findViewById(R.id.main_rb_sy);
        mainFw = findViewById(R.id.main_rb_fw);
        mainZw = findViewById(R.id.main_rb_zw);
        mainXw = findViewById(R.id.main_rb_xw);
        fragments = new ArrayList<>();
    }

    private void initData() {
        fragments.add(new SyFragment());
        fragments.add(new XwFragment());
        fragments.add(new FwFragment());
        fragments.add(new ZwFragment());
        mainVp.setAdapter(new MainVpAdapter(getSupportFragmentManager(), fragments));
    }

    private void initEvent() {
        mainRg.setOnCheckedChangeListener(this);
        mainVp.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int item = 0;
        switch (checkedId) {
            case R.id.main_rb_sy:
                item = 0;
                break;
            case R.id.main_rb_xw:
                item = 1;
                break;
            case R.id.main_rb_fw:
                item = 2;
                break;
            case R.id.main_rb_zw:
                item = 3;
                break;
        }
        mainVp.setCurrentItem(item, false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mainSy.setChecked(true);
                break;
            case 1:
                mainXw.setChecked(true);
                break;
            case 2:
                mainFw.setChecked(true);
                break;
            case 3:
                mainZw.setChecked(true);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
