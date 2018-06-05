package com.xu.headlinehelper.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xu.headlinehelper.R;

/**
 * @author 言吾許
 */

public class NumberPickerView extends LinearLayout implements View.OnClickListener {
    /**
     * 默认最大值为9999
     */
    private int maxValue = 9999;
    /**
     * 默认最小值为1
     */
    private int minValue = 1;
    /**
     * 显示的数值
     */
    private TextView tvNumber;

    public NumberPickerView(Context context) {
        this(context, null);
    }

    public NumberPickerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.view_layout, this);
        TextView tvMinus = findViewById(R.id.tv_minus);
        TextView tvAdd = findViewById(R.id.tv_add);
        tvNumber = findViewById(R.id.tv_number);
        tvMinus.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
    }

    /**
     * 设置最大值
     *
     * @param maxValue 最大值
     * @return 自身
     */
    public NumberPickerView setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    /**
     * 设置最小值
     *
     * @param minValue 最小值
     * @return 自身
     */
    public NumberPickerView setMinValue(int minValue) {
        this.minValue = minValue;
        return this;
    }

    public NumberPickerView setCurrentNumber(int currentNumber) {
        if (minValue > currentNumber || maxValue < currentNumber) {
            throw new RuntimeException("当前数字不能大于最大值或者小于最小值");
        } else {
            tvNumber.setText(String.valueOf(currentNumber));
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        int currentNumber = Integer.valueOf(tvNumber.getText().toString());
        if (viewId == R.id.tv_minus) {
            //减去
            tvNumber.setText(String.valueOf(currentNumber - 1));
        } else if (viewId == R.id.tv_add) {
            //加上
            tvNumber.setText(String.valueOf(currentNumber + 1));
        }
    }
}
