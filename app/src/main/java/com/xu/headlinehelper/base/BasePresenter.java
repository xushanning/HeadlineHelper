package com.xu.headlinehelper.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by xusn10 on 2018/1/16.
 *
 * @author xusn10
 */

public class BasePresenter<V extends IView> implements IPresenter<V> {
    private V mView;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenter(CompositeDisposable mCompositeDisposable) {
        this.mCompositeDisposable = mCompositeDisposable;
    }

    public void addDispose(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public V getView() {
        return mView;
    }


    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        mCompositeDisposable.clear();
        mCompositeDisposable = null;
    }
}
