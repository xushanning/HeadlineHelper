package com.xu.headlinehelper.ui.activity.main;

import com.xu.headlinehelper.ui.activity.basedownload.BaseDownloadPresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author 言吾許
 */

public class MainPresenter extends BaseDownloadPresenter<IMainView> implements IMainPresenter {
    @Inject
    public MainPresenter(CompositeDisposable mCompositeDisposable) {
        super(mCompositeDisposable);
    }
}
