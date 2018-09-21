package com.xu.headlinehelper.ui.fragment.homelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okserver.download.DownloadTask;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.adapter.quick.HomeListQuickAdapter;
import com.xu.headlinehelper.base.BaseMvpFragment;
import com.xu.headlinehelper.di.component.ActivityComponent;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author xu
 */

public class HomeListFragment extends BaseMvpFragment<IHomeListPresenter> implements IHomeListView {

    @BindView(R.id.rv_home_list)
    RecyclerView rvHomeList;


    public static HomeListFragment newInstance() {
        Bundle args = new Bundle();
        HomeListFragment homeListFragment = new HomeListFragment();
        homeListFragment.setArguments(args);
        return homeListFragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_home_list;
    }

    @Override
    public void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        HomeListQuickAdapter quickAdapter = new HomeListQuickAdapter(new ArrayList<>());
        rvHomeList.setLayoutManager(linearLayoutManager);
        rvHomeList.setAdapter(quickAdapter);
    }


    @Override
    public void finish() {

    }
}
