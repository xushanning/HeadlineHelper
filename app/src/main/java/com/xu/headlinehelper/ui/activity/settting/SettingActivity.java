package com.xu.headlinehelper.ui.activity.settting;

import android.widget.CompoundButton;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.base.BaseActivity;
import com.xu.headlinehelper.di.component.ActivityComponent;
import com.xu.headlinehelper.util.ToastUtil;
import com.xu.headlinehelper.view.NumberPickerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 言吾許
 */

public class SettingActivity extends BaseActivity<ISettingPresenter> implements ISettingView {


    @BindView(R.id.tv_current_path)
    TextView tvCurrentPath;
    @BindView(R.id.sb_shock)
    SwitchButton sbShock;
    @BindView(R.id.sb_notify)
    SwitchButton sbNotify;
    @BindView(R.id.sb_wifi)
    SwitchButton sbWifi;
    @BindView(R.id.np_down_load_count)
    NumberPickerView npDownLoadCount;
    @BindView(R.id.np_retry_count)
    NumberPickerView npRetryCount;

    @Override
    public int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }


    @Override
    public void initOthers() {
        sbShock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        sbNotify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        sbWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        //保存配置
        mPresenter.saveSettingToDb();
    }

    @Override
    public void saveResult(boolean result) {
        if (result) {
            ToastUtil.toastShort(this, "设置成功!");
            finish();
        } else {
            ToastUtil.toastShort(this, "设置失败!");
        }
    }
}
