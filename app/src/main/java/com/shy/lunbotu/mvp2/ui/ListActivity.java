package com.shy.lunbotu.mvp2.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;
import com.shy.lunbotu.mvp2.bean.ListBean;
import com.shy.lunbotu.mvp2.presenter.ListPersenter;
import com.shy.lunbotu.mvp2.view.IListView;

import java.util.List;

public class ListActivity extends AppCompatActivity implements IListView {

    private ListPersenter persenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianqi);
        persenter = new ListPersenter(this);
        persenter.setList();

    }

    @Override
    public void onListViewData(List<ListBean> listBeans) {
        Toast.makeText(this,listBeans.toString(),Toast.LENGTH_SHORT).show();
    }
}
