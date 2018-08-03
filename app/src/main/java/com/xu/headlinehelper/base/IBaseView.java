package com.xu.headlinehelper.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author 言吾許
 */

public interface IBaseView {
    /**
     * 绑定生命周期
     *
     * @param <T> 泛型
     * @return 泛型
     */
    <T> LifecycleTransformer<T> bindToLife();

    /**
     * 设置layout
     *
     * @return 布局id
     */
    int setLayoutId();
}
