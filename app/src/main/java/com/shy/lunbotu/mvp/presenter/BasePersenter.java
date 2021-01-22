package com.shy.lunbotu.mvp.presenter;

import com.shy.lunbotu.mvp.view.IBaseView;

import java.lang.ref.WeakReference;

public class BasePersenter<T extends IBaseView> {

    WeakReference<T> iGoodsView;
    /**
     * 软引用虽然可以避免内存溢出,但是gc清理也不是及时的,所以使用手动绑定和解绑
     */
    //绑定
    public void attachView(T view) {
        this.iGoodsView = new WeakReference<>(view);
    }

    //解绑
    public void datachView() {
        if (iGoodsView != null) {
            iGoodsView.clear();
            iGoodsView = null;
        }
    }
}
