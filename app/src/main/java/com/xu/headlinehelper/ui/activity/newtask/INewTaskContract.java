package com.xu.headlinehelper.ui.activity.newtask;

import android.content.Context;

import com.xu.headlinehelper.base.IBaseContract;

/**
 * @author 言吾許
 */

public interface INewTaskContract extends IBaseContract {
    interface INewTaskView extends IBaseView {
        /**
         * 加载粘贴板的字符串
         *
         * @param data 字符串
         */
        void loadClipboardData(String data);
    }

    interface INewTaskPresenter extends IBasePresenter<INewTaskView> {
        /**
         * 获取粘贴板的字符串
         *
         * @param context 上下文
         */
        void getClipboardData(Context context);

        /**
         * 下载视频，首先判断url是否合法
         *
         * @param originalUrl 用户原始输入
         */
        void downloadVideo(String originalUrl);
    }
}
