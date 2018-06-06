package com.xu.headlinehelper.ui.activity.newtask;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.xu.headlinehelper.base.BasePresenter;

/**
 * @author 言吾許
 */

public class NewTaskPresenter extends BasePresenter<INewTaskContract.INewTaskView> implements INewTaskContract.INewTaskPresenter {


    @Override
    public void getClipboardData(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager.hasPrimaryClip()) {
            ClipData clipData = clipboardManager.getPrimaryClip();
            mView.loadClipboardData(clipData.getItemAt(0).getText().toString());
        }
    }
}
