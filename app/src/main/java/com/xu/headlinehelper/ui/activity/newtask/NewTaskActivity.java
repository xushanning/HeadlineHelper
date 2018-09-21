package com.xu.headlinehelper.ui.activity.newtask;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.di.component.ActivityComponent;
import com.xu.headlinehelper.ui.activity.basedownload.BaseDownloadMvpActivity;
import com.xu.headlinehelper.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * @author 言吾許
 * 新建下载任务的activity
 */

public class NewTaskActivity extends BaseDownloadMvpActivity<INewTaskPresenter> implements INewTaskView {
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.tv_download)
    TextView tvDownload;

    @Override
    public int setLayout() {
        return R.layout.activity_new_task;
    }

    @Override
    public void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.getClipboardData(this);
        RxTextView.textChanges(etInput)
                .subscribe(charSequence -> {
                    GradientDrawable drawable = new GradientDrawable();
                    drawable.setCornerRadius(5);
                    if (charSequence.length() == 0) {
                        tvDownload.setClickable(false);
                        drawable.setStroke(1, ContextCompat.getColor(NewTaskActivity.this, R.color.button_unpressed_bg));
                        drawable.setColor(ContextCompat.getColor(NewTaskActivity.this, R.color.button_unpressed_bg));
                    } else {
                        drawable.setStroke(1, ContextCompat.getColor(NewTaskActivity.this, R.color.colorPrimary));
                        drawable.setColor(ContextCompat.getColor(NewTaskActivity.this, R.color.colorPrimary));
                        tvDownload.setClickable(true);
                    }
                    tvDownload.setBackground(drawable);
                });
    }

    @Override
    public void loadClipboardData(String data) {
        etInput.setText(data);
    }

    @Override
    public void analysisUrlFailed(String msg) {
        ToastUtil.toastShort(this, msg);
    }

    @Override
    public void downloading() {
        super.downloading();
        finish();
    }

    @OnClick({R.id.tv_download, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_download:
                mPresenter.getVideoUrl(etInput.getText().toString());
                break;
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }
}
