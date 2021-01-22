package com.shy.lunbotu.mvp.model.i;

import com.shy.lunbotu.mvp.bean.Goods;

import java.util.List;

public interface IGoodsModel {

    void loadGoodsData(OnLoadListener onLoadListener);

    interface OnLoadListener {
        void onComplete(List<Goods> goods);

        void onError(String msg);
    }


}
