package com.xu.headlinehelper.ui.activity.settting;

import com.xu.headlinehelper.base.IBaseContract;

/**
 * @author 言吾許
 */

public interface ISettingContract extends IBaseContract {
    interface ISettingView extends IBaseView {
        /**
         * 保存设置的结果
         *
         * @param result 结果
         */
        void saveResult(boolean result);
    }

    interface ISettingPresenter extends IBasePresenter<ISettingView> {
        /**
         * 将设置保存到数据库中
         */
        void saveSettingToDb();

    }
}
