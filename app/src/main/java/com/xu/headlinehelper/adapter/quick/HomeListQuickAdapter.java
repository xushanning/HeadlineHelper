package com.xu.headlinehelper.adapter.quick;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xu.headlinehelper.R;

import java.util.List;

/**
 * @author 许 on 2018/5/16.
 */

public class HomeListQuickAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public HomeListQuickAdapter(@Nullable List<String> data) {
        super(R.layout.item_home_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
