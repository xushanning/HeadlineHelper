package com.xu.headlinehelper.ui.activity.basedownload;

import com.xu.headlinehelper.base.IBasePresenter;
import com.xu.headlinehelper.bean.VideoDownloadBean;

/**
 * @author 言吾許
 */

public interface IBaseDownloadPresenter<T extends IBaseDownloadView> extends IBasePresenter<T> {
    /**
     * 获取视频的真实地址
     *
     * @param shareUrl 分享的地址
     */
    void getVideoUrl(String shareUrl);

    /**
     * 下载视频
     *
     * @param downloadBean 视频真实地址、描述、头像  信息类
     */
    void downloadVideo(VideoDownloadBean downloadBean);
}
