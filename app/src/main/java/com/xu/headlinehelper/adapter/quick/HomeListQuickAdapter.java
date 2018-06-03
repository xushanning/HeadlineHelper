package com.xu.headlinehelper.adapter.quick;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xu.headlinehelper.R;

import java.util.List;

import zlc.season.rxdownload3.core.Mission;

/**
 * @author 许 on 2018/5/16.
 */

public class HomeListQuickAdapter extends BaseQuickAdapter<Mission, BaseViewHolder> {
    public HomeListQuickAdapter(@Nullable List<Mission> missions) {
        super(R.layout.item_home_list, missions);
    }

    @Override
    protected void convert(BaseViewHolder helper, Mission item) {

    }
}
