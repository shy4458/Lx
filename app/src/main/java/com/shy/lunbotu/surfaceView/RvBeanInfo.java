package com.shy.lunbotu.surfaceView;

public class RvBeanInfo {

    String title;
    String url;
    int i;
    String var;

    public RvBeanInfo(String title, String url, int i, String var) {
        this.title = title;
        this.url = url;
        this.i = i;
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
