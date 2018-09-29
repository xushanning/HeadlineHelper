package com.xu.headlinehelper.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.xu.headlinehelper.MyApplication;
import com.xu.headlinehelper.di.component.ActivityComponent;
import com.xu.headlinehelper.di.component.DaggerActivityComponent;
import com.xu.headlinehelper.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 言吾許
 * 无mvp fragment
 */
public abstract class BaseFragment extends RxFragment implements IFragment, IView {
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(setLayout(), container, false);
        bind = ButterKnife.bind(this, mView);
        ActivityComponent activityComponent = DaggerActivityComponent
                .builder()
                .appComponent(MyApplication.getAppComponent())
                .activityModule(new ActivityModule())
                .build();
        inject(activityComponent);
        initMvp();
        initData(savedInstanceState);
        return mView;
    }

    public void initMvp() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void finish() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public <K> LifecycleTransformer<K> bindToLife() {
        return this.bindToLifecycle();
    }
}
