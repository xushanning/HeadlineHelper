package com.xu.headlinehelper.util;

import android.util.Log;

import com.orhanobut.logger.LogStrategy;

/**
 * @author 言吾許
 * logger策略
 */
public class LoggerStrategy implements LogStrategy {
    @Override
    public void log(int priority, String tag, String message) {
        Log.println(priority, randomKey() + tag, message);
    }

    private int last;

    private String randomKey() {
        int random = (int) (10 * Math.random());
        if (random == last) {
            random = (random + 1) % 10;
        }
        last = random;
        return String.valueOf(random);
    }

}