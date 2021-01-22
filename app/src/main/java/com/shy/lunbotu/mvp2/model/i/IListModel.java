package com.shy.lunbotu.mvp2.model.i;

import com.shy.lunbotu.mvp2.bean.ListBean;

import java.util.List;

public interface IListModel {

    void loadListData(OnLoadListData onLoadListData);

    interface OnLoadListData {
        void success(List<ListBean> listBeanList);

        void fail(String msg);

    }
}
