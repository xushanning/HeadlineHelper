package com.xu.headlinehelper.ui.activity.basedownload;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.OkDownload;
import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.base.BasePresenter;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.bean.VideoDownloadBean;
import com.xu.headlinehelper.net.LogDownloadListener;
import com.xu.headlinehelper.util.VideoUrlAnalysis;

import java.io.File;

import io.reactivex.functions.Consumer;

/**
 * @author 许 on 2018/6/7.
 *         下载视频的基Presenter
 */

public abstract class BaseDownloadPresenter<T extends IBaseDownloadContract.IBaseDownloadView> extends BasePresenter<T> implements IBaseDownloadContract.IBaseDownloadPresenter<T> {

    @Override
    public void getVideoUrl(String shareUrl) {
        Logger.d(shareUrl);
        new VideoUrlAnalysis().getDownloadObservable(shareUrl)
                .subscribe(new Consumer<VideoAddressBean.DataBean>() {
                    @Override
                    public void accept(VideoAddressBean.DataBean dataBean) throws Exception {
                        mView.showDownLoadWindow(dataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.d(throwable.getMessage());
                        mView.analysisUrlFailed(throwable.getMessage());
                    }
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
        mView.downloading();
    }
}
