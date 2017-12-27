package com.dlwx.hzquser.view;

/**
 * view接口
 */

public interface ViewInterface {
    void showLoading();
    /**
     * 显示数据
     */
    void showData(String s);
    void disLoading();
    void onError();
}
