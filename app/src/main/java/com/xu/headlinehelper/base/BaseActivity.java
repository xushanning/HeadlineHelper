package com.xu.headlinehelper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xu.headlinehelper.MyApplication;
import com.xu.headlinehelper.di.component.ActivityComponent;
import com.xu.headlinehelper.di.component.DaggerActivityComponent;
import com.xu.headlinehelper.di.module.ActivityModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by xusn10 on 2018/1/15.
 *
 * @author xusn10
 */

public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView {

    @Inject
    protected T mPresenter;
    private Unbinder bind;
    public CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);

        ActivityComponent activityComponent = DaggerActivityComponent
                .builder()
                .applicationComponent(((MyApplication) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule())
                .build();

        inject(activityComponent);

        if (mPresenter == null) {
            throw new NullPointerException("presenter 不能为空!");
        }
        bind = ButterKnife.bind(this);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initOthers();
    }

    /**
     * 注入
     *
     * @param activityComponent component
     */
    public abstract void inject(ActivityComponent activityComponent);


    /**
     * 初始化其他
     */
    public abstract void initOthers();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
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
