package com.xu.headlinehelper.adapter.quick;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okserver.download.DownloadTask;
import com.xu.headlinehelper.R;

import java.util.List;

/**
 * @author 许 on 2018/5/16.
 */

public class HomeListQuickAdapter extends BaseQuickAdapter<DownloadTask, BaseViewHolder> {
    public HomeListQuickAdapter(@Nullable List<DownloadTask> missions) {
        super(R.layout.item_home_list, missions);
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadTask item) {

    }
}
