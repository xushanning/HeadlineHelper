package com.xu.headlinehelper.ui.activity.newtask;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.xu.headlinehelper.ui.activity.basedownload.BaseDownloadPresenter;

import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author 言吾許
 */

public class NewTaskPresenter extends BaseDownloadPresenter<INewTaskView> implements INewTaskPresenter {


    Pattern pattern = Pattern.compile("(http://[A-Za-z0-9\\\\.\\\\/=\\\\?%\\\\-\\\\_\\\\&~`@':+!(^\\\\<)]+)");

    @Inject
    public NewTaskPresenter(CompositeDisposable mCompositeDisposable) {
        super(mCompositeDisposable);
    }

    @Override
    public void getClipboardData(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager.hasPrimaryClip()) {
            ClipData clipData = clipboardManager.getPrimaryClip();
            getView().loadClipboardData(clipData.getItemAt(0).getText().toString());
        }
    }
}
