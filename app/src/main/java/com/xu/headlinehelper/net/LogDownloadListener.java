package com.xu.headlinehelper.net;

import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * @author 言吾許
 */

public class LogDownloadListener extends DownloadListener {
    public LogDownloadListener() {
        super("LogDownloadListener");
    }

    @Override
    public void onStart(Progress progress) {
        Logger.d("onStart: " + progress);
    }

    @Override
    public void onProgress(Progress progress) {
        Logger.d("onProgress: " + progress);
    }

    @Override
    public void onError(Progress progress) {
        Logger.d("onError: " + progress);
        progress.exception.printStackTrace();
    }

    @Override
    public void onFinish(File file, Progress progress) {
        Logger.d("onFinish: " + progress);
    }

    @Override
    public void onRemove(Progress progress) {
        Logger.d("onRemove: " + progress);
    }
}
