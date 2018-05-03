package com.xu.headlinehelper.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by xusn10 on 2018/1/15.
 *
 * @author xusn10
 */

public interface IBaseContract {

    interface IBaseView {
        /**
         * 绑定生命周期
         *
         * @param <T>
         * @return
         */
        <T> LifecycleTransformer<T> bindToLife();

    }

    interface IBasePresenter<T extends IBaseView> {
        /**
         * 绑定view
         *
         * @param view view接口
         */
        void attachView(T view);

        /**
         * 解绑view
         */
        void detachView();


    }

}
