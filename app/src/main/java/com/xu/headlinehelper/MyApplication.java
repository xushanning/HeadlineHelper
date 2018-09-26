package com.xu.headlinehelper;

import android.app.Application;
import android.os.Environment;
import android.text.TextUtils;

import com.alibaba.sdk.android.feedback.impl.FeedbackAPI;
import com.coorchice.library.ImageEngine;
import com.lzy.okgo.OkGo;
import com.lzy.okserver.OkDownload;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.bugly.crashreport.CrashReport;
import com.xu.headlinehelper.db.dao.DownLoadSettingDbBeanDao;
import com.xu.headlinehelper.db.dbbean.DownLoadSettingDbBean;
import com.xu.headlinehelper.di.component.AppComponent;

import com.xu.headlinehelper.di.component.DaggerAppComponent;
import com.xu.headlinehelper.di.module.ApplicationModule;
import com.xu.headlinehelper.di.module.ClientModule;
import com.xu.headlinehelper.util.GlideEngine;
import com.xu.headlinehelper.util.LoggerStrategy;
import com.xu.headlinehelper.util.SecretConstant;
import com.xu.headlinehelper.util.TransformUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * @author 言吾許
 */

public class MyApplication extends Application {
    @Inject
    DownLoadSettingDbBeanDao settingDbBeanDao;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .appModule(new ApplicationModule())
                .clientModule(new ClientModule())
                .build();
        appComponent.inject(this);

        ImageEngine.install(new GlideEngine(this));
        initLogger();
        initDownload();
        if (!BuildConfig.DEBUG) {
            initBugly();
        }
        //反馈需要依赖好几个库，暂时注释掉
        // FeedbackAPI.init(this, SecretConstant.ID_SECRET, SecretConstant.APP_SECRET);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(3)
                .methodOffset(5)
                .logStrategy(new LoggerStrategy())
                .tag("headline")
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }

    private void initBugly() {
        String packageName = getPackageName();
        String processName = getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(this);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(this, "c4dca0492b", BuildConfig.DEBUG, strategy);
    }

    /**
     * 获取进程名称
     *
     * @param pid 进程号
     * @return 进程名称
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    private void initDownload() {
        Disposable downloadDis = Observable
                .create((ObservableOnSubscribe<DownLoadSettingDbBean>) e -> {
                    DownLoadSettingDbBean dbBean = settingDbBeanDao.queryBuilder().unique();
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
                        settingDbBeanDao.insert(newDbBean);
                        e.onNext(newDbBean);
                    }
                    e.onComplete();
                })
                .compose(TransformUtil.<DownLoadSettingDbBean>defaultSchedulers())
                .subscribe(downLoadSettingDbBean -> {
                    OkGo.getInstance().init(MyApplication.this)
                            .setRetryCount(downLoadSettingDbBean.getRetryCount());

                    OkDownload.getInstance().setFolder(downLoadSettingDbBean.getSavePath());
                    OkDownload.getInstance().getThreadPool().setCorePoolSize(downLoadSettingDbBean.getDownLoadCount());
                }, throwable -> {
                    //默认设置
                    Logger.d(throwable.getMessage());
                });


    }


}
