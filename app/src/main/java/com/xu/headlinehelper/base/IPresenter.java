package com.xu.headlinehelper.base;

/**
 * @author 言吾許
 */

public interface IPresenter<T extends IView> {
    /**
     * 绑定view
     *
     * @param view view
     */
    void attachView(T view);

    /**
     * 解绑view
     */
    void detachView();
}
