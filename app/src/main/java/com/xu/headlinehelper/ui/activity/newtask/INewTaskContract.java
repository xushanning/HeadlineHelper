package com.xu.headlinehelper.ui.activity.newtask;

import android.content.Context;

import com.xu.headlinehelper.base.IBaseContract;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.ui.activity.basedownload.IBaseDownloadContract;

/**
 * @author 言吾許
 */

public interface INewTaskContract extends IBaseDownloadContract {
    interface INewTaskView extends IBaseDownloadView {
        /**
         * 加载粘贴板的字符串
         *
         * @param data 字符串
         */
        void loadClipboardData(String data);

    }

    interface INewTaskPresenter extends IBaseDownloadPresenter<INewTaskView> {
        /**
         * 获取粘贴板的字符串
         *
         * @param context 上下文
         */
        void getClipboardData(Context context);


    }
}
