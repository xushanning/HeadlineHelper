package com.xu.headlinehelper.ui.activity.newtask;

import android.content.Context;

import com.xu.headlinehelper.base.IBaseContract;
import com.xu.headlinehelper.bean.VideoAddressBean;

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

        /**
         * 分析视频真实地址失败
         *
         * @param msg 失败原因
         */
        void analysisUrlFailed(String msg);

        /**
         * 显示下载窗口
         *
         * @param videoListBean 视频信息列表
         */
        void showDownLoadWindow(VideoAddressBean.DataBean.VideoListBean videoListBean);

    }

    interface INewTaskPresenter extends IBasePresenter<INewTaskView> {
        /**
         * 获取粘贴板的字符串
         *
         * @param context 上下文
         */
        void getClipboardData(Context context);

        /**
         * 分析视频的真实地址
         *
         * @param originalUrl 原始分享地址
         */
        void analysisVideoUrl(String originalUrl);

        /**
         * 下载视频，首先判断url是否合法
         *
         * @param originalUrl 用户原始输入
         */
        void downloadVideo(String originalUrl);
    }
}
