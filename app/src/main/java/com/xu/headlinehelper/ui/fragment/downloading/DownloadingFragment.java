package com.xu.headlinehelper.ui.fragment.downloading;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xu.headlinehelper.R;
import com.xu.headlinehelper.adapter.quick.DownloadingQuickAdapter;
import com.xu.headlinehelper.base.BaseFragment;
import com.xu.headlinehelper.view.MultipleStatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload3.core.Mission;

/**
 * @author 言吾許
 *         正在下载fragment
 */

public class DownloadingFragment extends BaseFragment<IDownloadingContract.IDownloadingPresenter> implements IDownloadingContract.IDownloadingView {
    @BindView(R.id.rv_downloading)
    RecyclerView rvDownloading;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;

    private DownloadingQuickAdapter downloadingQuickAdapter;

    public static DownloadingFragment newInstance() {
        Bundle args = new Bundle();
        DownloadingFragment downloadingFragment = new DownloadingFragment();
        downloadingFragment.setArguments(args);
        return downloadingFragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_downloading;
    }

    @Override
    public void initOthers() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDownloading.setLayoutManager(layoutManager);

        downloadingQuickAdapter = new DownloadingQuickAdapter(new ArrayList<Mission>());
        rvDownloading.setAdapter(downloadingQuickAdapter);
        statusView.showLoading();
        refreshDownloadingList();
    }

    @Override
    public IDownloadingContract.IDownloadingPresenter createPresenter() {
        return new DownloadingPresenter();
    }

    @Override
    public void loadDownloadingData(List<Mission> missions) {
        if (missions.size() == 0) {
            statusView.showNoData();
        } else {
            statusView.showContent();
            downloadingQuickAdapter.setNewData(missions);
        }
    }

    /**
     * 刷新下载列表
     */
    public void refreshDownloadingList() {
        mPresenter.getCurrentDownloadingTask();
    }
}
