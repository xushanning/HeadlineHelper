package com.xu.headlinehelper.ui.activity.settting;

import com.xu.headlinehelper.base.IBasePresenter;

/**
 * @author 言吾許
 */

public interface ISettingPresenter extends IBasePresenter<ISettingView> {
    /**
     * 将设置保存到数据库中
     */
    void saveSettingToDb();
}
