package com.xu.headlinehelper.ui.activity.newtask;

import com.xu.headlinehelper.ui.activity.basedownload.IDownloadView;

/**
 * @author 言吾許
 */

public interface INewTaskView extends IDownloadView {
    /**
     * 加载粘贴板的字符串
     *
     * @param data 字符串
     */
    void loadClipboardData(String data);
}
