package com.xu.headlinehelper.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xu.headlinehelper.MyApplication;
import com.xu.headlinehelper.di.component.ActivityComponent;
import com.xu.headlinehelper.di.component.DaggerActivityComponent;
import com.xu.headlinehelper.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * Created by xusn10 on 2018/1/15.
 * 懒加载
 * http://www.cnblogs.com/dasusu/p/6745032.html
 *
 * @author xu
 */

public abstract class BaseMvpFragment<T extends IPresenter> extends BaseFragment {
    @Inject
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityComponent activityComponent = DaggerActivityComponent
                .builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
                .activityModule(new ActivityModule())
                .build();
        inject(activityComponent);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initMvp() {
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
