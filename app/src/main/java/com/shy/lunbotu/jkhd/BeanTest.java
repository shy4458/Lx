package com.shy.lunbotu.jkhd;

public class BeanTest implements MyImpl {

    private main main;

    public BeanTest(com.shy.lunbotu.jkhd.main main) {
        this.main = main;
    }

    @Override
    public void implFun() {
        System.out.println("回调成功");
    }
}
