package com.xu.headlinehelper;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * @author 言吾許
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
    }

    private void initLogger() {
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
    }
}
