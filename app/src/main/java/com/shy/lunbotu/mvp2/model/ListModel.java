package com.shy.lunbotu.mvp2.model;

import com.shy.lunbotu.mvp2.bean.ListBean;
import com.shy.lunbotu.mvp2.model.i.IListModel;

import java.util.ArrayList;
import java.util.List;

public class ListModel implements IListModel {

    @Override
    public void loadListData(OnLoadListData onLoadListData) {
        onLoadListData.success(getData());
    }

    //请求数据
    private List<ListBean> getData() {
        try {
            List<ListBean> list = new ArrayList<>();
            ListBean listBean = new ListBean();
            listBean.setMessage("123");
            listBean.setStatus("200");
            list.add(listBean);
            Thread.sleep(2000);
            return list;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
