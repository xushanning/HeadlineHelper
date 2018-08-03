package com.xu.headlinehelper.di.component;

import com.xu.headlinehelper.di.module.ActivityModule;
import com.xu.headlinehelper.ui.activity.main.MainActivity;
import com.xu.headlinehelper.ui.activity.newtask.NewTaskActivity;
import com.xu.headlinehelper.ui.activity.settting.SettingActivity;

import dagger.Component;

/**
 * @author 言吾許
 */
@Component(modules = {ActivityModule.class}, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    /**
     * 注入到main中
     *
     * @param mainActivity 目标activity
     */
    void inject(MainActivity mainActivity);

    /**
     * 注入到new task 中
     *
     * @param newTaskActivity 目标activity
     */
    void inject(NewTaskActivity newTaskActivity);

    /**
     * 注入到setting 中
     *
     * @param settingActivity 目标activity
     */
    void inject(SettingActivity settingActivity);
}
