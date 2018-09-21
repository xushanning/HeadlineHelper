package com.xu.headlinehelper.ui.activity.settting;

import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.MyApplication;
import com.xu.headlinehelper.base.BasePresenter;
import com.xu.headlinehelper.db.dao.DownLoadSettingDbBeanDao;
import com.xu.headlinehelper.db.dbbean.DownLoadSettingDbBean;
import com.xu.headlinehelper.net.ApiException;
import com.xu.headlinehelper.util.TransformUtil;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author 言吾許
 */

public class SettingPresenter extends BasePresenter<ISettingView> implements ISettingPresenter {
    private DownLoadSettingDbBeanDao downLoadSettingDbBeanDao;

    @Inject
    public SettingPresenter(CompositeDisposable mCompositeDisposable, DownLoadSettingDbBeanDao downLoadSettingDbBeanDao) {
        super(mCompositeDisposable);
        this.downLoadSettingDbBeanDao = downLoadSettingDbBeanDao;
    }

    @Override
    public void saveSettingToDb() {
        Disposable saveDis = Observable
                .create((ObservableOnSubscribe<DownLoadSettingDbBean>) e -> {
                    DownLoadSettingDbBean dbBean = downLoadSettingDbBeanDao.queryBuilder().where(DownLoadSettingDbBeanDao.Properties.Id.eq("")).build().unique();
                    if (dbBean != null) {
                        e.onNext(dbBean);
                        e.onComplete();
                    } else {
                        throw new ApiException("获取设置失败");
                    }
                })
                .compose(TransformUtil.<DownLoadSettingDbBean>defaultSchedulers())
                .compose(getView().<DownLoadSettingDbBean>bindToLife())
                .subscribe(downLoadSettingDbBean -> {

                }, throwable -> {
                    Logger.d(throwable.getMessage());
                });
        addDispose(saveDis);
    }
}
