package com.xu.headlinehelper.db.dbmanager;


import com.xu.headlinehelper.db.dao.DownLoadSettingDbBeanDao;
import com.xu.headlinehelper.db.dbbean.DownLoadSettingDbBean;
import com.xu.headlinehelper.net.ApiException;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author 许 on 2018/6/5.
 */

public class DBManager {
    @Inject
    DownLoadSettingDbBeanDao dbBeanDao;

    @Inject
    public DBManager() {
    }

    /**
     * 获取下载配置的observable
     *
     * @return observable
     */
    public Observable<DownLoadSettingDbBean> getDownLoadSetting() {
        return Observable.create(e -> {
            DownLoadSettingDbBean dbBean = dbBeanDao.queryBuilder().where(DownLoadSettingDbBeanDao.Properties.Id.eq("")).build().unique();
            if (dbBean != null) {
                e.onNext(dbBean);
                e.onComplete();
            } else {
                throw new ApiException("获取设置失败");
            }
        });
    }

}
