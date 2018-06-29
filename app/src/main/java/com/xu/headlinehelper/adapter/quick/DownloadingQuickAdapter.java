package com.xu.headlinehelper.adapter.quick;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.model.Progress;
import com.lzy.okserver.download.DownloadListener;
import com.lzy.okserver.download.DownloadTask;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.net.LogDownloadListener;

import java.io.File;
import java.util.List;

/**
 * @author 言吾許
 */

public class DownloadingQuickAdapter extends BaseQuickAdapter<DownloadTask, DownloadingQuickAdapter.MyViewHolder> {


    public DownloadingQuickAdapter(@Nullable List<DownloadTask> data) {
        super(R.layout.item_downloading, data);
    }

    @Override
    protected void convert(final MyViewHolder helper, final DownloadTask item) {
        item.register(new ListDownloadListener(item.progress.tag, helper))
                .register(new LogDownloadListener());
        helper.setTask(item);
        helper.setTag(item.progress.tag);
        helper.refreshProgress(item.progress);
    }

    public class MyViewHolder extends BaseViewHolder {
        private DownloadTask task;
        private ProgressBar progressBar;
        private TextView textView;
        private String tag;

        public MyViewHolder(View view) {
            super(view);
            progressBar = getView(R.id.progressBar);
            progressBar.setMax(10000);
            textView = getView(R.id.tv_start);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshProgress(task.progress);
                }
            });
        }

        void setTask(DownloadTask task) {
            this.task = task;
        }

        void refreshProgress(Progress currentProgress) {
            progressBar.setProgress((int) (currentProgress.fraction * 10000));

            String speed = Formatter.formatFileSize(mContext, currentProgress.speed);
            String currentSize = Formatter.formatFileSize(mContext, currentProgress.currentSize);
            String totalSize = Formatter.formatFileSize(mContext, currentProgress.totalSize);
            setText(R.id.tv_title, currentProgress.fileName)
                    .setText(R.id.tv_percent, speed + "%")
                    .setText(R.id.tv_size, mContext.getString(R.string.downloading_size, currentSize, totalSize));
            switch (currentProgress.status) {
                case Progress.NONE:
                    textView.setText("下载");
                    task.start();
                    break;
                case Progress.PAUSE:
                    textView.setText("继续");
                    task.start();
                    break;
                case Progress.ERROR:
                    textView.setText("出错");
                    task.start();
                    break;
                case Progress.WAITING:
                    textView.setText("等待");
                    break;
                case Progress.FINISH:
                    textView.setText("完成");
                    break;
                case Progress.LOADING:
                    textView.setText("暂停");
                    task.pause();
                    break;
                default:
                    break;
            }
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

    }

    private class ListDownloadListener extends DownloadListener {

        private MyViewHolder holder;

        public ListDownloadListener(Object tag, MyViewHolder holder) {
            super(tag);
            this.holder = holder;
        }

        @Override
        public void onStart(Progress progress) {

        }

        @Override
        public void onProgress(Progress progress) {
            if (tag == holder.getTag()) {
                holder.refreshProgress(progress);
            }
        }

        @Override
        public void onError(Progress progress) {
            Throwable throwable = progress.exception;
            if (throwable != null) {
                throwable.printStackTrace();
            }
        }

        @Override
        public void onFinish(File file, Progress progress) {

        }

        @Override
        public void onRemove(Progress progress) {

        }
    }
}
