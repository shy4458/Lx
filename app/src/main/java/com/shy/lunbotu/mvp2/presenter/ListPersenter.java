package com.shy.lunbotu.mvp2.presenter;

import com.shy.lunbotu.mvp2.bean.ListBean;
import com.shy.lunbotu.mvp2.model.i.IListModel;
import com.shy.lunbotu.mvp2.model.ListModel;
import com.shy.lunbotu.mvp2.view.IListView;

import java.lang.ref.WeakReference;
import java.util.List;

public class ListPersenter {

    private WeakReference iListView;
    private IListModel iListModel = new ListModel();

    public ListPersenter(IListView iListView) {
        this.iListView = new WeakReference(iListView);
    }

    //执行业务逻辑
    public void setList() {
        if (iListView.get() != null && iListView != null) {
            iListModel.loadListData(new IListModel.OnLoadListData() {
                @Override
                public void success(List<ListBean> listBeanList) {
                    ((IListView) iListView.get()).onListViewData(listBeanList);
                }

                @Override
                public void fail(String msg) {

                }
            });
        }
    }

}
