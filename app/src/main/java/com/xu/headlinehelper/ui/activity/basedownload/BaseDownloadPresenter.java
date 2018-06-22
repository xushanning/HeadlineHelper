package com.xu.headlinehelper.ui.activity.basedownload;

import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.base.BasePresenter;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.db.dbmanager.CustomMission;
import com.xu.headlinehelper.util.VideoUrlAnalysis;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Failed;
import zlc.season.rxdownload3.core.Status;
import zlc.season.rxdownload3.core.Succeed;
import zlc.season.rxdownload3.extension.ApkInstallExtension;

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
    public void downloadVideo(CustomMission customMission) {
        RxDownload.INSTANCE.create(customMission, true)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Status>() {
                    @Override
                    public void accept(Status status) throws Exception {
                        Logger.d(status instanceof Failed);
                        mView.downloading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.d(throwable.getMessage());
                    }
                });

    }
}
