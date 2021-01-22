package com.shy.lunbotu.mvp.presenter;

import com.shy.lunbotu.mvp.bean.Goods;
import com.shy.lunbotu.mvp.model.GoodsModel;
import com.shy.lunbotu.mvp.model.i.IGoodsModel;
import com.shy.lunbotu.mvp.view.IBaseView;
import com.shy.lunbotu.mvp.view.IGoodsView;

import java.lang.ref.WeakReference;
import java.util.List;

public class GoodsPersenter<T extends IBaseView> {
    //1.持有view接口
//    IGoodsView iGoodsView; //这种可能出现内存泄漏
    WeakReference<T> iGoodsView;//这种方式在GC第二次扫描的时候就会回收掉了
    //2.持有model接口
    IGoodsModel iGoodsModel = new GoodsModel();

    public GoodsPersenter(T view) {
//        this.iGoodsView = (IGoodsView) view;//这种可能出现内存泄漏
        this.iGoodsView = new WeakReference<>(view);
    }

    //3.执行业务逻辑
    public void fetch() {
        if (iGoodsModel != null && iGoodsView.get() != null) {
            iGoodsModel.loadGoodsData(new IGoodsModel.OnLoadListener() {
                @Override
                public void onComplete(List<Goods> goods) {
                    ((IGoodsView) iGoodsView.get()).showGoodsView(goods);
                }

                @Override
                public void onError(String msg) {

                }
            });
        }
    }
}
