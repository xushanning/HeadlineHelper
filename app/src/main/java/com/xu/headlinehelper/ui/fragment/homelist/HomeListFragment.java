package com.xu.headlinehelper.ui.fragment.homelist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okserver.download.DownloadTask;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.adapter.quick.HomeListQuickAdapter;
import com.xu.headlinehelper.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author xu
 */

public class HomeListFragment extends BaseFragment<IHomeListContract.IHomeListPresenter> implements IHomeListContract.IHomeListView {

    @BindView(R.id.rv_home_list)
    RecyclerView rvHomeList;


    public static HomeListFragment newInstance() {
        Bundle args = new Bundle();
        HomeListFragment homeListFragment = new HomeListFragment();
        homeListFragment.setArguments(args);
        return homeListFragment;
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_home_list;
    }

    @Override
    public void initOthers() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        HomeListQuickAdapter quickAdapter = new HomeListQuickAdapter(new ArrayList<DownloadTask>());
        rvHomeList.setLayoutManager(linearLayoutManager);
        rvHomeList.setAdapter(quickAdapter);
    }

    @Override
    public IHomeListContract.IHomeListPresenter createPresenter() {
        return new HomeListPresenter();
    }


}
