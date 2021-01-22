package com.shy.lunbotu.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shy.lunbotu.R;
import com.shy.lunbotu.mvp.bean.Goods;
import com.shy.lunbotu.mvp.presenter.GoodsPersenter;
import com.shy.lunbotu.mvp.view.IGoodsView;
import com.shy.lunbotu.mvp2.ui.ListActivity;

import java.util.List;

/**
 * M LoginModel 数据层 数据库 IO 网络
 * V LoginView  UI交互回调 持有P层的引用
 * P LoginPresenter MV的中介 关联绑定 P层同时持有M,V的引用
 * <p>
 * 思想: 从MVC转变过来的,把原来的UI逻辑抽象成View接口,业务逻辑抽象成presenter接口,model还是原来的model
 * <p>
 * <p>
 * MVP缺点:当某些场景时,程序后退退出后,activity应该销毁,而实时上在表示层(p层)还持有activity的引用,这个引用还和model有交互关系
 * 如果model请求数据时间很长,一直没有返回.这时候就产生内存泄漏了.
 */

/**
 *
 */
public class LoginActivity extends AppCompatActivity implements IGoodsView {

    private GoodsPersenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        presenter = new GoodsPersenter(this);
        findViewById(R.id.b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.fetch();
            }
        });
        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ListActivity.class));
            }
        });
    }

    @Override
    public void showGoodsView(List<Goods> goods) {
        //获得数据
        Toast.makeText(this, "666", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
