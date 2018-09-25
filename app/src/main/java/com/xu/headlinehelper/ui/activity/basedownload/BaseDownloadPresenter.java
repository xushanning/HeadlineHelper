package com.xu.headlinehelper.ui.activity.basedownload;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.base.BasePresenter;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.bean.VideoDownloadBean;
import com.xu.headlinehelper.util.VideoUrlAnalysis;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * @author 许 on 2018/6/7.
 * 下载视频的基Presenter
 */

public abstract class BaseDownloadPresenter<T extends IDownloadView> extends BasePresenter<T> implements IDownloadPresenter<T> {
    @Inject
    VideoUrlAnalysis videoUrlAnalysis;

    public BaseDownloadPresenter(CompositeDisposable mCompositeDisposable) {
        super(mCompositeDisposable);
    }

    @Override
    public void getVideoUrl(String shareUrl) {
        Logger.d(shareUrl);
        videoUrlAnalysis
                .getDownloadObservable(shareUrl)
                .subscribe(dataBean -> getView().showDownLoadWindow(dataBean),
                        throwable -> {
                            Logger.d(throwable.getMessage());
                            getView().analysisUrlFailed(throwable.getMessage());
                        });

    }

    @Override
    public void downloadVideo(VideoDownloadBean downloadBean) {
        GetRequest<File> request = OkGo.get(downloadBean.getUrl());
        OkDownload.request(downloadBean.getUrl(), request)
                .extra1(downloadBean)
                .fileName(downloadBean.getTitle() + ".mp4")
                .save()
                //.register(new LogDownloadListener())
                .start();
        getView().downloading();
    }
}
