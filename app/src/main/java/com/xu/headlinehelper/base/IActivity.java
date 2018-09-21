package com.xu.headlinehelper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xu.headlinehelper.di.component.ActivityComponent;

/**
 * @author 言吾許
 */
public interface IActivity {


    /**
     * 设置布局
     *
     * @return 布局id
     */
    int setLayout();

    /**
     * 注入
     *
     * @param activityComponent component
     */
    void inject(ActivityComponent activityComponent);

    /**
     * 初始化数据
     *
     * @param savedInstanceState bundle
     */
    void initData(@Nullable Bundle savedInstanceState);


}
