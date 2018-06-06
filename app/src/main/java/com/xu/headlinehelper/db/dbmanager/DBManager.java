package com.xu.headlinehelper.db.dbmanager;


import com.xu.headlinehelper.MyApplication;
import com.xu.headlinehelper.db.dao.DownLoadSettingDbBeanDao;
import com.xu.headlinehelper.db.dbbean.DownLoadSettingDbBean;
import com.xu.headlinehelper.net.ApiExecption;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author 许 on 2018/6/5.
 */

public class DBManager {

    /**
     * 获取下载配置的observable
     *
     * @return observable
     */
    public static Observable<DownLoadSettingDbBean> getDownLoadSetting() {
        return Observable.create(new ObservableOnSubscribe<DownLoadSettingDbBean>() {
            @Override
            public void subscribe(ObservableEmitter<DownLoadSettingDbBean> e) throws Exception {
                DownLoadSettingDbBeanDao dbBeanDao = MyApplication.getInstance().getDaoSession().getDownLoadSettingDbBeanDao();
                DownLoadSettingDbBean dbBean = dbBeanDao.queryBuilder().where(DownLoadSettingDbBeanDao.Properties.Id.eq("")).build().unique();
                if (dbBean != null) {
                    e.onNext(dbBean);
                    e.onComplete();
                } else {
                    throw new ApiExecption("获取设置失败");
                }
            }
        });
    }

}
