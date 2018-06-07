package com.xu.headlinehelper.ui.activity.main;

import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.base.BasePresenter;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.util.VideoUrlAnalysis;

import io.reactivex.functions.Consumer;

/**
 * @author 言吾許
 */

public class MainPresenter extends BasePresenter<IMainContract.IMainView> implements IMainContract.IMainPresenter {

    @Override
    public void getVideoUrl(String shareUrl) {
        new VideoUrlAnalysis().getDownloadObservable(shareUrl)
                .subscribe(new Consumer<VideoAddressBean.DataBean.VideoListBean>() {
                    @Override
                    public void accept(VideoAddressBean.DataBean.VideoListBean videoListBean) throws Exception {
                        Logger.d(videoListBean.getVideo_1().getMain_url());
                        mView.showDownLoadWindow(videoListBean);
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
    public void downLoadVideo(String videoAddressUrl) {

    }
}
