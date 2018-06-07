package com.xu.headlinehelper.ui.activity.newtask;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.base.BaseActivity;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * @author 言吾許
 *         新建下载任务的activity
 */

public class NewTaskActivity extends BaseActivity<INewTaskContract.INewTaskPresenter> implements INewTaskContract.INewTaskView {
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.tv_download)
    TextView tvDownload;

    @Override
    public int setLayoutId() {
        return R.layout.activity_new_task;
    }

    @Override
    public INewTaskContract.INewTaskPresenter setPresenter() {
        return new NewTaskPresenter();
    }

    @Override
    public void initOthers() {
        mPresenter.getClipboardData(this);
        RxTextView.textChanges(etInput)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
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
                    }
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
    public void showDownLoadWindow(VideoAddressBean.DataBean.VideoListBean videoListBean) {

    }


    @OnClick({R.id.tv_download, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_download:
                mPresenter.downloadVideo(etInput.getText().toString());
                break;
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }
}
