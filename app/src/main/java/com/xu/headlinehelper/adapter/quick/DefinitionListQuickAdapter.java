package com.xu.headlinehelper.adapter.quick;


import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.bean.VideoInfoBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author 言吾許
 */

public class DefinitionListQuickAdapter extends BaseQuickAdapter<VideoInfoBean, BaseViewHolder> {
    private SparseArray<RadioButton> radioButtons;
    /**
     * 存放radio的状态
     */
    private SparseArray<RadioCheckable> radioCheckables;
    private OnRadioButtonSelectListener onRadioButtonSelectListener;

    public DefinitionListQuickAdapter(@Nullable List<VideoInfoBean> data) {
        super(R.layout.item_definition, data);
        radioButtons = new SparseArray<>();
        radioCheckables = new SparseArray<>();
    }

    @Override
    protected void convert(final BaseViewHolder helper, VideoInfoBean item) {
        final RadioButton rbDefinition = helper.getView(R.id.rb_definition);
        RadioCheckable radioCheckable = new RadioCheckable();
        radioCheckables.put(helper.getLayoutPosition(), radioCheckable);
        radioButtons.append(helper.getLayoutPosition(), rbDefinition);
        rbDefinition.setText(mContext.getString(R.string.new_task_definition_item, item.getDefinition(), transferBitToMb(item.getSize())));
        rbDefinition.setOnClickListener(v -> {
            setOtherRadioButtonUnchecked(helper.getLayoutPosition());
            boolean isChecked = radioCheckables.get(helper.getLayoutPosition()).isChecked();
            radioCheckables.get(helper.getLayoutPosition()).setChecked(!isChecked);
            rbDefinition.setChecked(!isChecked);
            onRadioButtonSelectListener.onRadioButtonChange(getData().get(helper.getLayoutPosition()).getMain_url());
        });
    }

    /**
     * 将bit转为mb数据
     *
     * @param bit bit大小
     * @return mb大小
     */
    private String transferBitToMb(float bit) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(bit / 1024 / 1024);
    }

    private void setOtherRadioButtonUnchecked(int position) {
        for (int i = 0; i < radioButtons.size(); i++) {
            if (i != position) {
                setRadioButtonUnchecked(i);
            }
        }
    }

    private void setRadioButtonUnchecked(int position) {
        RadioButton radioButton = radioButtons.get(position);
        if (radioButton != null) {
            radioCheckables.get(position).setChecked(false);
            radioButton.setChecked(false);
        }
    }

    /**
     * radioButton变化监听
     */
    public interface OnRadioButtonSelectListener {
        /**
         * 变化
         *
         * @param downloadUrl 下载链接
         */
        void onRadioButtonChange(String downloadUrl);
    }

    public void setOnRadioButtonChangeListener(OnRadioButtonSelectListener onRadioButtonSelectListener) {
        this.onRadioButtonSelectListener = onRadioButtonSelectListener;
    }

    class RadioCheckable {
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }
}
