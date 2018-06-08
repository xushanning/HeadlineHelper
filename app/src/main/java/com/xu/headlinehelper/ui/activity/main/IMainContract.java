package com.xu.headlinehelper.ui.activity.main;

import com.xu.headlinehelper.base.IBaseContract;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.ui.activity.basedownload.IBaseDownloadContract;

/**
 * @author 言吾許
 */

public interface IMainContract extends IBaseContract {
    interface IMainView extends IBaseDownloadContract.IBaseDownloadView {


    }

    interface IMainPresenter extends IBaseDownloadContract.IBaseDownloadPresenter<IMainView> {

    }
}
