package com.xu.headlinehelper.ui.activity.newtask;

import android.content.Context;

import com.xu.headlinehelper.ui.activity.basedownload.IBaseDownloadPresenter;

/**
 * @author 言吾許
 */

public interface INewTaskPresenter extends IBaseDownloadPresenter<INewTaskView> {
    /**
     * 获取粘贴板的字符串
     *
     * @param context 上下文
     */
    void getClipboardData(Context context);
}
