package com.xu.headlinehelper.base;

/**
 * Created by xusn10 on 2018/1/16.
 *
 * @author xusn10
 */

public class BasePresenter<T extends IBaseContract.IBaseView> implements IBaseContract.IBasePresenter<T> {
    protected T mView;

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


}
