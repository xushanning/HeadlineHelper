package com.xu.headlinehelper.ui.activity.basedownload;

import com.xu.headlinehelper.base.IView;
import com.xu.headlinehelper.bean.VideoAddressBean;

/**
 * @author 言吾許
 */

public interface IDownloadView extends IView {
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
