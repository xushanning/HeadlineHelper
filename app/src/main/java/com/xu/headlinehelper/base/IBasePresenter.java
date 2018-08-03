package com.xu.headlinehelper.base;

import com.xu.headlinehelper.net.ApiException;

/**
 * @author 言吾許
 */

public interface IBasePresenter<T extends IBaseView> {
    /**
     * 绑定view
     *
     * @param view view接口
     */
    void attachView(T view);

    /**
     * 解绑view
     */
    void detachView();

    /**
     * 处理错误
     *
     * @param apiException 错误
     */
    void handleApiException(ApiException apiException);
}
