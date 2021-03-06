package com.xu.headlinehelper.ui.fragment.downloading;

import com.lzy.okgo.db.DownloadManager;
import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadTask;
import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author 言吾許
 */

public class DownloadingPresenter extends BasePresenter<IDownloadingView> implements IDownloadingPresenter {
    @Inject
    public DownloadingPresenter(CompositeDisposable mCompositeDisposable) {
        super(mCompositeDisposable);
    }

    @Override
    public void getCurrentDownloadingTask() {
        List<DownloadTask> tasks = OkDownload.restore(DownloadManager.getInstance().getDownloading());
        if (tasks != null && tasks.size() != 0) {
            Logger.d(tasks.get(0).progress.fileName);
            getView().loadDownloadingData(tasks);
        }
    }
}
