package com.shy.lunbotu.mvp.model;

import com.shy.lunbotu.mvp.bean.Goods;
import com.shy.lunbotu.mvp.model.i.IGoodsModel;

import java.util.ArrayList;
import java.util.List;

public class GoodsModel implements IGoodsModel {
    @Override
    public void loadGoodsData(OnLoadListener onLoadListener) {
        onLoadListener.onComplete(getData());
    }

    private List<Goods> getData() {
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("一星"));
        goods.add(new Goods("二星"));
        goods.add(new Goods("三星"));
        goods.add(new Goods("四星"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return goods;
    }
}
