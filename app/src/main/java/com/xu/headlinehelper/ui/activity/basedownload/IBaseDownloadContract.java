package com.xu.headlinehelper.ui.activity.basedownload;

import com.xu.headlinehelper.base.IBaseContract;
import com.xu.headlinehelper.bean.VideoAddressBean;

/**
 * @author 许 on 2018/6/7.
 */

public interface IBaseDownloadContract extends IBaseContract {
    interface IBaseDownloadView extends IBaseView {
        /**
         * 显示下载窗口
         *
         * @param dataBean 视频信息
         */
        void showDownLoadWindow(VideoAddressBean.DataBean dataBean);

        /**
         * 分析视频真实地址失败
         *
         * @param msg 失败原因
         */
        void analysisUrlFailed(String msg);

        /**
         * 正在下载
         */
        void downloading();
    }

    interface IBaseDownloadPresenter<T extends IBaseDownloadView> extends IBasePresenter<T> {
        /**
         * 获取视频的真实地址
         *
         * @param shareUrl 分享的地址
         */
        void getVideoUrl(String shareUrl);

        /**
         * 下载视频
         *
         * @param videoAddressUrl 视频真实地址
         */
        void downloadVideo(String videoAddressUrl);
    }
}
