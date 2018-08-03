package com.xu.headlinehelper.base;

import com.xu.headlinehelper.net.ApiException;

import javax.inject.Inject;

/**
 * Created by xusn10 on 2018/1/16.
 *
 * @author xusn10
 */

public class BasePresenter<T extends  IBaseView> implements  IBasePresenter<T> {
    private T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }

    public T getView() {
        return mView;
    }

    @Override
    public void handleApiException(ApiException apiException) {

    }

}
