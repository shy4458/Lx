package com.shy.lunbotu.mvp.view;

import com.shy.lunbotu.mvp.bean.Goods;

import java.util.List;

public interface IGoodsView extends IBaseView {

    void showGoodsView(List<Goods> goods);

}
