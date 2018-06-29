package com.xu.headlinehelper.ui.fragment.downloading;

import com.lzy.okserver.download.DownloadTask;
import com.xu.headlinehelper.base.IBaseContract;

import java.util.List;

/**
 * @author 言吾許
 */

public interface IDownloadingContract extends IBaseContract {
    interface IDownloadingView extends IBaseView {
        /**
         * 加载正在下载的任务数据
         *
         * @param tasks 数据
         */
        void loadDownloadingData(List<DownloadTask> tasks);
    }

    interface IDownloadingPresenter extends IBasePresenter<IDownloadingView> {
        /**
         * 获取当前正在执行的任务
         */
        void getCurrentDownloadingTask();
    }
}
