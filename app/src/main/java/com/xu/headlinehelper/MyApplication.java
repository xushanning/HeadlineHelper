package com.xu.headlinehelper;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.db.dao.DaoMaster;
import com.xu.headlinehelper.db.dao.DaoSession;
import com.xu.headlinehelper.db.dbmanager.CustomSqliteActor;

import zlc.season.rxdownload3.core.DownloadConfig;
import zlc.season.rxdownload3.database.SQLiteActor;
import zlc.season.rxdownload3.extension.ApkInstallExtension;
import zlc.season.rxdownload3.extension.ApkOpenExtension;

/**
 * @author 言吾許
 */

public class MyApplication extends Application {
    private DaoSession mDaoSession;
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initLogger();
        initDatabase();
        initDownload();
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
        DownloadConfig.Builder builder = DownloadConfig.Builder.Companion.create(this)
                .enableDb(true)
                .enableAutoStart(true)
                .setDebug(true)
                .setDbActor(new CustomSqliteActor(this))
                .enableService(true)
                .enableNotification(true)
                .addExtension(ApkInstallExtension.class)
                .addExtension(ApkOpenExtension.class);

        DownloadConfig.INSTANCE.init(builder);
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
