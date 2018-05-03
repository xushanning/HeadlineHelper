package com.xu.headlinehelper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xusn10 on 2018/1/15.
 * 懒加载
 * http://www.cnblogs.com/dasusu/p/6745032.html
 *
 * @author xu
 */

public abstract class BaseFragment<T extends IBaseContract.IBasePresenter> extends RxFragment implements IBaseContract.IBaseView, IBase {

    protected T mPresenter;
    private Unbinder bind;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(setLayoutId(), container, false);
        bind = ButterKnife.bind(this, mView);

        mPresenter = createPresenter();
        if (mPresenter == null) {
            throw new NullPointerException("presenter 不能为空!");
        }
        mPresenter.attachView(this);
        initOthers();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * 初始化其他
     */
    public abstract void initOthers();

    /**
     * 设置presenter
     *
     * @return 对应的presenter
     */
    public abstract T createPresenter();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        bind.unbind();

    }

    @Override
    public <K> LifecycleTransformer<K> bindToLife() {
        return this.bindToLifecycle();
    }
}
