package com.xu.headlinehelper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xu.headlinehelper.MyApplication;
import com.xu.headlinehelper.di.component.ActivityComponent;
import com.xu.headlinehelper.di.component.DaggerActivityComponent;
import com.xu.headlinehelper.di.module.ActivityModule;

import javax.inject.Inject;

/**
 * mvp activity 基类
 *
 * @author xusn10
 */

public abstract class BaseMvpActivity<T extends IPresenter> extends BaseActivity {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityComponent activityComponent = DaggerActivityComponent
                .builder()
                .appComponent(((MyApplication) getApplication()).getAppComponent())
                .activityModule(new ActivityModule())
                .build();
        inject(activityComponent);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
