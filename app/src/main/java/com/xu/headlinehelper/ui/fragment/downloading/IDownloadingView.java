package com.xu.headlinehelper.ui.fragment.downloading;

import com.lzy.okserver.download.DownloadTask;
import com.xu.headlinehelper.base.IBaseView;

import java.util.List;

/**
 * @author 言吾許
 */

public interface IDownloadingView extends IBaseView {
    /**
     * 加载正在下载的任务数据
     *
     * @param tasks 数据
     */
    void loadDownloadingData(List<DownloadTask> tasks);
}
