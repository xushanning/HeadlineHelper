package com.xu.headlinehelper.ui.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.xu.headlinehelper.R;
import com.xu.headlinehelper.base.BaseActivity;
import com.xu.headlinehelper.di.component.ActivityComponent;
import com.xu.headlinehelper.ui.activity.main.MainActivity;

/**
 * @author 言吾許
 */
public class SplashActivity extends BaseActivity {
    @Override
    public int setLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        Intent intent = new Intent(this, MainActivity.class);
       // startActivity(intent);
    }
}
