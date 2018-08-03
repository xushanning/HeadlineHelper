package com.xu.headlinehelper;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.lzy.okgo.OkGo;
import com.lzy.okserver.OkDownload;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.db.dao.DaoMaster;
import com.xu.headlinehelper.db.dao.DaoSession;
import com.xu.headlinehelper.db.dao.DownLoadSettingDbBeanDao;
import com.xu.headlinehelper.db.dbbean.DownLoadSettingDbBean;
import com.xu.headlinehelper.di.component.ApplicationComponent;
import com.xu.headlinehelper.di.component.DaggerApplicationComponent;
import com.xu.headlinehelper.di.module.ApplicationModule;
import com.xu.headlinehelper.util.TransformUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * @author 言吾許
 */

public class MyApplication extends Application {
    private DaoSession mDaoSession;
    private static MyApplication mInstance;
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mInstance = this;
        initLogger();
        initDatabase();
        initDownload();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    private void initDownload() {
        Observable
                .create(new ObservableOnSubscribe<DownLoadSettingDbBean>() {
                    @Override
                    public void subscribe(ObservableEmitter<DownLoadSettingDbBean> e) throws Exception {
                        DownLoadSettingDbBeanDao dbBeanDao = MyApplication.getInstance().getDaoSession().getDownLoadSettingDbBeanDao();
                        DownLoadSettingDbBean dbBean = dbBeanDao.queryBuilder().unique();
                        if (dbBean != null) {
                            e.onNext(dbBean);
                        } else {
                            //把默认的配置加入到数据中去
                            DownLoadSettingDbBean newDbBean = new DownLoadSettingDbBean();
                            //默认只有wifi才能下载
                            newDbBean.setWifiDownLoad(true);
                            //默认显示通知
                            newDbBean.setShowNotify(true);
                            //默认不震动
                            newDbBean.setShock(false);
                            //默认最大下载数为3
                            newDbBean.setDownLoadCount(3);
                            //默认最大尝试次数为3
                            newDbBean.setRetryCount(3);
                            //默认存储路径
                            String path = Environment.getExternalStorageDirectory().getPath() + "/头条助手/";
                            newDbBean.setSavePath(path);
                            dbBeanDao.insert(newDbBean);
                            e.onNext(newDbBean);
                        }
                        e.onComplete();
                    }
                })
                .compose(TransformUtil.<DownLoadSettingDbBean>defaultSchedulers())
                .subscribe(new Consumer<DownLoadSettingDbBean>() {
                    @Override
                    public void accept(DownLoadSettingDbBean downLoadSettingDbBean) throws Exception {
                        OkGo.getInstance().init(MyApplication.this)
                                .setRetryCount(downLoadSettingDbBean.getRetryCount());

                        OkDownload.getInstance().setFolder(downLoadSettingDbBean.getSavePath());
                        OkDownload.getInstance().getThreadPool().setCorePoolSize(downLoadSettingDbBean.getDownLoadCount());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //默认设置
                        Logger.d(throwable.getMessage());
                    }
                });


    }

    private void initDatabase() {
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, "video-helper-db", null);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}
