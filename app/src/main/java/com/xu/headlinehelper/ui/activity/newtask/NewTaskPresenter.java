package com.xu.headlinehelper.ui.activity.newtask;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.xu.headlinehelper.base.BasePresenter;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.util.VideoUrlAnalysis;

import java.util.regex.Pattern;

import io.reactivex.functions.Consumer;

/**
 * @author 言吾許
 */

public class NewTaskPresenter extends BasePresenter<INewTaskContract.INewTaskView> implements INewTaskContract.INewTaskPresenter {
    Pattern pattern = Pattern.compile("(http://[A-Za-z0-9\\\\.\\\\/=\\\\?%\\\\-\\\\_\\\\&~`@':+!(^\\\\<)]+)");

    @Override
    public void getClipboardData(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager.hasPrimaryClip()) {
            ClipData clipData = clipboardManager.getPrimaryClip();
            mView.loadClipboardData(clipData.getItemAt(0).getText().toString());
        }
    }

    @Override
    public void analysisVideoUrl(String originalUrl) {
        new VideoUrlAnalysis().getDownloadObservable(originalUrl)
                .subscribe(new Consumer<VideoAddressBean.DataBean.VideoListBean>() {
                    @Override
                    public void accept(VideoAddressBean.DataBean.VideoListBean videoListBean) throws Exception {
                        mView.showDownLoadWindow(videoListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.analysisUrlFailed(throwable.getMessage());
                    }
                });
    }

    @Override
    public void downloadVideo(String originalUrl) {

    }
}
