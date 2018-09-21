package com.xu.headlinehelper.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 言吾許
 * 由于某些activity功能比较单一，不需要mvp，固剥离无mvp  activity
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IActivity, IView {
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
        bind = ButterKnife.bind(this);
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
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    /**
     * 绑定生命周期
     *
     * @param <T> T
     * @return LifecycleTransformer
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }
}
