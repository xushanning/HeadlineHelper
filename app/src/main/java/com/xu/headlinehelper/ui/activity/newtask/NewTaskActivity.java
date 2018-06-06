package com.xu.headlinehelper.ui.activity.newtask;

import com.xu.headlinehelper.R;
import com.xu.headlinehelper.base.BaseActivity;

/**
 * @author 言吾許
 *         新建下载任务的activity
 */

public class NewTaskActivity extends BaseActivity<INewTaskContract.INewTaskPresenter> implements INewTaskContract.INewTaskView {
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
    }

    @Override
    public void loadClipboardData(String data) {

    }
}
