package com.xu.headlinehelper.ui.activity.settting;

import android.os.Bundle;

import com.xu.headlinehelper.R;
import com.xu.headlinehelper.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 言吾許
 */

public class SettingActivity extends BaseActivity<ISettingContract.ISettingPresenter> implements ISettingContract.ISettingView {


    @Override
    public int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public ISettingContract.ISettingPresenter setPresenter() {
        return new SettingPresenter();
    }

    @Override
    public void initOthers() {

    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
