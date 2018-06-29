package com.xu.headlinehelper.ui.fragment.downloading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okserver.OkDownload;
import com.lzy.okserver.download.DownloadTask;
import com.lzy.okserver.task.XExecutor;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.adapter.quick.DownloadingQuickAdapter;
import com.xu.headlinehelper.base.BaseFragment;
import com.xu.headlinehelper.ui.activity.newtask.NewTaskActivity;
import com.xu.headlinehelper.view.MultipleStatusView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author 言吾許
 *         正在下载fragment
 */

public class DownloadingFragment extends BaseFragment<IDownloadingContract.IDownloadingPresenter> implements IDownloadingContract.IDownloadingView, XExecutor.OnAllTaskEndListener {
    @BindView(R.id.rv_downloading)
    RecyclerView rvDownloading;
    @BindView(R.id.status_view)
    MultipleStatusView statusView;

    private OkDownload okDownload;

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
        okDownload = OkDownload.getInstance();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDownloading.setLayoutManager(layoutManager);

        downloadingQuickAdapter = new DownloadingQuickAdapter(new ArrayList<DownloadTask>());
        rvDownloading.setAdapter(downloadingQuickAdapter);
        statusView.showLoading();
        statusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewTaskActivity.class);
                startActivity(intent);
            }
        });
        okDownload.addOnAllTaskEndListener(this);
        refreshDownloadingList();
    }

    @Override
    public IDownloadingContract.IDownloadingPresenter createPresenter() {
        return new DownloadingPresenter();
    }

    @Override
    public void loadDownloadingData(List<DownloadTask> tasks) {
        if (tasks.size() == 0) {
            statusView.showNoData();
        } else {
            statusView.showContent();
            downloadingQuickAdapter.setNewData(tasks);
        }
    }

    /**
     * 刷新下载列表
     */
    public void refreshDownloadingList() {
        mPresenter.getCurrentDownloadingTask();
    }

    @Override
    public void onAllTaskEnd() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        okDownload.removeOnAllTaskEndListener(this);
    }
}
