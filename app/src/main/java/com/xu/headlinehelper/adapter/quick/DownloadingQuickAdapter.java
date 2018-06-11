package com.xu.headlinehelper.adapter.quick;

import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.db.dbmanager.CustomMission;
import com.xu.headlinehelper.util.ImageLoaderUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Downloading;
import zlc.season.rxdownload3.core.Failed;
import zlc.season.rxdownload3.core.Mission;
import zlc.season.rxdownload3.core.Normal;
import zlc.season.rxdownload3.core.Status;
import zlc.season.rxdownload3.core.Succeed;
import zlc.season.rxdownload3.core.Suspend;
import zlc.season.rxdownload3.core.Waiting;
import zlc.season.rxdownload3.extension.ApkInstallExtension;

/**
 * @author 言吾許
 */

public class DownloadingQuickAdapter extends BaseQuickAdapter<Mission, BaseViewHolder> {

    private SparseArray<Status> statusSparseArray = new SparseArray<>();

    public DownloadingQuickAdapter(@Nullable List<Mission> data) {
        super(R.layout.item_downloading, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Mission item) {
        final CustomMission customMission = (CustomMission) item;
        final TextView textView = helper.getView(R.id.tv_start);
        RxDownload.INSTANCE.create(customMission, false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Status>() {
                    @Override
                    public void accept(final Status status) throws Exception {
                        //添加状态
                        statusSparseArray.append(helper.getLayoutPosition(), status);
                        helper.setText(R.id.tv_title, customMission.getIntroduce())
                                .setText(R.id.tv_percent, status.percent())
                                .setText(R.id.tv_size, status.formatString());
                        ProgressBar progressBar = helper.getView(R.id.progressBar);
                        progressBar.setMax((int) status.getTotalSize());
                        progressBar.setProgress((int) status.getDownloadSize());
                        ImageView imageView = helper.getView(R.id.img_portrait);
                        ImageLoaderUtil.loadImage(mContext, customMission.getImg(), imageView);
                        String text = "";
                        if (status instanceof Normal) {
                            text = "开始";
                        } else if (status instanceof Suspend) {
                            text = "已暂停";
                        } else if (status instanceof Waiting) {
                            text = "等待中";
                        } else if (status instanceof Downloading) {
                            text = "暂停";
                        } else if (status instanceof Failed) {
                            text = "失败";
                        }
                        textView.setText(text);
                    }
                });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status currentStatus = statusSparseArray.get(helper.getLayoutPosition());
                if (currentStatus instanceof Normal) {
                    RxDownload.INSTANCE.start(customMission).subscribe();
                } else if (currentStatus instanceof Suspend) {
                    RxDownload.INSTANCE.start(customMission).subscribe();
                } else if (currentStatus instanceof Failed) {
                    RxDownload.INSTANCE.start(customMission).subscribe();
                } else if (currentStatus instanceof Downloading) {
                    RxDownload.INSTANCE.stop(customMission).subscribe();
                } else if (currentStatus instanceof Succeed) {
                    //打开

                }
            }
        });

    }
}
