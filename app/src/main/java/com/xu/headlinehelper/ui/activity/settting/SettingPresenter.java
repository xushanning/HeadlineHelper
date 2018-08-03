package com.xu.headlinehelper.ui.activity.settting;

import com.xu.headlinehelper.base.BasePresenter;
import com.xu.headlinehelper.db.dbmanager.DBManager;
import com.xu.headlinehelper.db.dbbean.DownLoadSettingDbBean;
import com.xu.headlinehelper.util.TransformUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * @author 言吾許
 */

public class SettingPresenter extends BasePresenter<ISettingView> implements ISettingPresenter {
    @Inject
    public SettingPresenter() {
    }

    @Override
    public void saveSettingToDb() {
        DBManager.getDownLoadSetting()
                .compose(TransformUtil.<DownLoadSettingDbBean>defaultSchedulers())
                .compose(getView().<DownLoadSettingDbBean>bindToLife())
                .subscribe(new Consumer<DownLoadSettingDbBean>() {
                    @Override
                    public void accept(DownLoadSettingDbBean downLoadSettingDbBean) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
