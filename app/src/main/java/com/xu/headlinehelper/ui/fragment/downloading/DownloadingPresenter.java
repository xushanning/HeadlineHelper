package com.xu.headlinehelper.ui.fragment.downloading;

import com.xu.headlinehelper.base.BasePresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Mission;

/**
 * @author 言吾許
 */

public class DownloadingPresenter extends BasePresenter<IDownloadingContract.IDownloadingView> implements IDownloadingContract.IDownloadingPresenter {

    @Override
    public void getCurrentDownloadingTask() {
        RxDownload.INSTANCE.getAllMission()
                .compose(mView.<List<Mission>>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Mission>>() {
                    @Override
                    public void accept(List<Mission> missions) throws Exception {
                        mView.loadDownloadingData(missions);
                    }
                });

    }
}
