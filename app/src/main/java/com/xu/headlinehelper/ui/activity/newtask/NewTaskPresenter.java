package com.xu.headlinehelper.ui.activity.newtask;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.base.BasePresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void downloadVideo(String originalUrl) {
        Logger.d(originalUrl);
        Matcher matcher = pattern.matcher(originalUrl);
        if (matcher.find()) {
            String shareUrl = matcher.group();
            Logger.d(shareUrl);
        }
    }
}
