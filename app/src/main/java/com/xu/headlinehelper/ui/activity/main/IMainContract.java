package com.xu.headlinehelper.ui.activity.main;

import com.xu.headlinehelper.base.IBaseContract;
import com.xu.headlinehelper.bean.VideoAddressBean;

/**
 * @author 言吾許
 */

public interface IMainContract extends IBaseContract {
    interface IMainView extends IBaseView {
        /**
         * 显示下载窗口
         *
         * @param videoListBean 视频信息列表
         */
        void showDownLoadWindow(VideoAddressBean.DataBean.VideoListBean videoListBean);

        /**
         * 分析视频真实地址失败
         *
         * @param msg 失败原因
         */
        void analysisUrlFailed(String msg);

    }

    interface IMainPresenter extends IBasePresenter<IMainView> {
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
        void downLoadVideo(String videoAddressUrl);
    }
}
