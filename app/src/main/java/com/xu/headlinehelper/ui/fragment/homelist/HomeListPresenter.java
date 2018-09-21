package com.xu.headlinehelper.ui.fragment.homelist;

import com.xu.headlinehelper.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author 许 on 2018/5/16.
 */

public class HomeListPresenter extends BasePresenter<IHomeListView> implements IHomeListPresenter {
    @Inject
    public HomeListPresenter(CompositeDisposable mCompositeDisposable) {
        super(mCompositeDisposable);
    }
}
