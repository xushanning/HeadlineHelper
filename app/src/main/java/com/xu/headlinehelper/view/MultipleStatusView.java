package com.xu.headlinehelper.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xu.headlinehelper.R;

import java.util.ArrayList;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;

/**
 * @author 言吾許
 *         显示多种页面状态的view
 */

public class MultipleStatusView extends RelativeLayout {

    /**
     * 无书局布局id
     */
    //  private int noDataViewResId;
    /**
     * 错误布局id
     */
    // private int errorViewResId;
    /**
     * 内容布局id
     */
    /// private int contentViewResId;
    /**
     * 正在加载布局id
     */
    //  private int loadingViewResId;
    /**
     * 无布局
     */
    private static final int NULL_RESOURCE_ID = -1;

    private LayoutInflater mInflater;
    /**
     * 展示的view的类型
     */
    private int viewStatus;
    private static final int STATUS_CONTENT = 0x00;
    private static final int STATUS_LOADING = 0x01;
    private static final int STATUS_ERROR = 0x02;
    private static final int STATUS_NO_NETWORK = 0x03;

    private View noDataView;
    private View errorView;
    private View contentView;
    private View loadingView;

    private static final LayoutParams DEFAULT_LAYOUT_PARAMS = new LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT);
    /**
     * 把异常状态的view的id存放起来
     */
    private List<Integer> otherId = new ArrayList<>();

    private OnClickListener onNewTaskListener;

    public MultipleStatusView(Context context) {
        this(context, null);
    }

    public MultipleStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultipleStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView, defStyleAttr, 0);
//        noDataViewResId = typedArray.getResourceId(R.styleable.MultipleStatusView_noDataView, R.layout.view_no_data);
//        errorViewResId = typedArray.getResourceId(R.styleable.MultipleStatusView_errorView, R.layout.view_error);
//        loadingViewResId = typedArray.getResourceId(R.styleable.MultipleStatusView_loadingView, R.layout.view_loading);
//        contentViewResId = typedArray.getResourceId(R.styleable.MultipleStatusView_contentView, NULL_RESOURCE_ID);
        // typedArray.recycle();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        showContent();
    }


    /**
     * 显示内容视图
     */
    public final void showContent() {
        viewStatus = STATUS_CONTENT;
//        if (contentView == null && contentViewResId != NULL_RESOURCE_ID) {
//            contentView = mInflater.inflate(contentViewResId, null);
//            addView(contentView, 0, DEFAULT_LAYOUT_PARAMS);
//        }
        showContentView();
    }

    /**
     * 显示无网络视图
     */
    public final void showNoData() {
        viewStatus = STATUS_NO_NETWORK;
        if (noDataView == null) {
            noDataView = mInflater.inflate(R.layout.view_no_data, null);
            otherId.add(noDataView.getId());
            //在这里加载新建任务布局
            TextView tvNewTask = noDataView.findViewById(R.id.tv_new_task);
            tvNewTask.setOnClickListener(onNewTaskListener);
            addView(noDataView, 0, DEFAULT_LAYOUT_PARAMS);
        }
        showViewById(noDataView.getId());
    }

    /**
     * 显示加载视图
     */
    public final void showLoading() {
        viewStatus = STATUS_LOADING;
        if (loadingView == null) {
            loadingView = mInflater.inflate(R.layout.view_loading, null);
            //荧光动画
            ShimmerLayout shimmerLayout = loadingView.findViewById(R.id.sl_loading);
            shimmerLayout.setShimmerAnimationDuration(1500);
            shimmerLayout.setShimmerColor(R.color.color_loading_Shimmer);
            shimmerLayout.startShimmerAnimation();
            otherId.add(loadingView.getId());
            addView(loadingView, 0, DEFAULT_LAYOUT_PARAMS);
        }
        showViewById(loadingView.getId());
    }

    /**
     * 显示错误视图
     */
    public final void showError() {
        viewStatus = STATUS_ERROR;
        if (errorView == null) {
            errorView = mInflater.inflate(R.layout.view_error, null);
            otherId.add(errorView.getId());
            addView(errorView, 0, DEFAULT_LAYOUT_PARAMS);
        }
        showViewById(errorView.getId());
    }

    private void showContentView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            //如果子view在这个list中，那么gone，否则，则代表是content的view，显示
            view.setVisibility(otherId.contains(view.getId()) ? View.GONE : View.VISIBLE);
        }
    }

    private void showViewById(int viewId) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.setVisibility(childView.getId() == viewId ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 无网络重试点击
     *
     * @param onNewTaskListener 重试事件
     */
    public void setOnRetryClickListener(OnClickListener onNewTaskListener) {
        this.onNewTaskListener = onNewTaskListener;
    }

    /**
     * 销毁view
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clearView(loadingView, errorView, noDataView);
        if (otherId != null) {
            otherId.clear();
        }
        if (onNewTaskListener != null) {
            onNewTaskListener = null;
        }
        mInflater = null;
    }

    private void clearView(View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view != null) {
                removeView(view);
            }
        }
    }
}
