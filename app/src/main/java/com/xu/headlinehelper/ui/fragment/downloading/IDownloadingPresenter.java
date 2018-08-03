package com.xu.headlinehelper.ui.fragment.downloading;

import com.xu.headlinehelper.base.IBasePresenter;

/**
 * @author 言吾許
 */

public interface IDownloadingPresenter extends IBasePresenter<IDownloadingView> {
    /**
     * 获取当前正在执行的任务
     */
    void getCurrentDownloadingTask();
}
