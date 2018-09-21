package com.xu.headlinehelper.base;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author 言吾許
 */

public interface IView {
    /**
     * 显示dialog
     */
    void showDialog();

    /**
     * 隐藏dialog
     */
    void hideDialog();

    /**
     * 显示吐司
     *
     * @param message 消息内容，不能为空
     */
    void showMessage(@NonNull String message);

    /**
     * 结束当前界面
     */
    void finish();

    /**
     * 绑定生命周期
     *
     * @param <T> 泛型
     * @return 泛型
     */
    <T> LifecycleTransformer<T> bindToLife();
}
