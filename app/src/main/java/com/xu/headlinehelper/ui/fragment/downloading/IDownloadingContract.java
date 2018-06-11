package com.xu.headlinehelper.ui.fragment.downloading;

import com.xu.headlinehelper.base.IBaseContract;

import java.util.List;

import zlc.season.rxdownload3.core.Mission;

/**
 * @author 言吾許
 */

public interface IDownloadingContract extends IBaseContract {
    interface IDownloadingView extends IBaseView {
        /**
         * 加载正在下载的任务数据
         *
         * @param missions 数据
         */
        void loadDownloadingData(List<Mission> missions);
    }

    interface IDownloadingPresenter extends IBasePresenter<IDownloadingView> {
        /**
         * 获取当前正在执行的任务
         */
        void getCurrentDownloadingTask();
    }
}
