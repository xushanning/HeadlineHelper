package com.xu.headlinehelper.ui.activity.basedownload;

import android.Manifest;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.adapter.quick.DefinitionListQuickAdapter;
import com.xu.headlinehelper.base.BaseMvpActivity;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.bean.VideoDownloadBean;
import com.xu.headlinehelper.bean.VideoInfoBean;
import com.xu.headlinehelper.util.ImageLoaderUtil;
import com.xu.headlinehelper.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author 许 on 2018/6/7.
 * 下载的基础类  主activity和newtask  activity都要继承自此类
 * 封装权限控制
 */
@RuntimePermissions
public abstract class BaseDownloadMvpActivity<T extends IDownloadPresenter> extends BaseMvpActivity<T> implements IDownloadView {
    private String mDownloadUrl;

    /**
     * 检查权限并且下载
     *
     * @param downloadBean 下载信息类
     *                     由于BaseDownloadActivityPermissionsDispatcher 这个类非public，所以在
     *                     main activity中不能直接调用，因此写成这种形式
     */
    public void permissionCheckAndDownload(VideoDownloadBean downloadBean) {
        BaseDownloadMvpActivityPermissionsDispatcher.downLoadVideoWithPermissionCheck(this, downloadBean);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void downLoadVideo(VideoDownloadBean downloadBean) {
        mPresenter.downloadVideo(downloadBean);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(R.string.permission_write_external_storage)
                .setPositiveButton(R.string.button_allow, (dialogInterface, i) -> request.proceed())
                .setNegativeButton(R.string.button_cancel, (dialogInterface, i) -> request.cancel())
                .show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showPermissionDenied() {
        ToastUtil.toastShort(this, getString(R.string.permission_write_external_denied));
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showPermissionNeverAskAgain() {
        ToastUtil.toastShort(this, getString(R.string.permission_write_external_denied));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        BaseDownloadMvpActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void showDownLoadWindow(VideoAddressBean.DataBean dataBean) {
        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .cancelable(false)
                .customView(R.layout.dialog_definition_select, false)
                .show();

        View dialogView = dialog.getCustomView();
        TextView tvTitle = dialogView.findViewById(R.id.tv_title);
        ImageView imgThumbnail = dialogView.findViewById(R.id.img_thumbnail);
        ImageView imgClose = dialogView.findViewById(R.id.img_close);
        TextView tvDownload = dialogView.findViewById(R.id.tv_download);
        RecyclerView rvDefinition = dialogView.findViewById(R.id.rv_definition);
        DefinitionListQuickAdapter quickAdapter = new DefinitionListQuickAdapter(getAdapterData(dataBean.getVideo_list()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvDefinition.setLayoutManager(linearLayoutManager);
        rvDefinition.setAdapter(quickAdapter);
        quickAdapter.setOnRadioButtonChangeListener(downloadUrl -> mDownloadUrl = downloadUrl);
        final String title = dataBean.getUser_id();
        final String portraitUrl = dataBean.getPoster_url();
        tvTitle.setText(title);
        ImageLoaderUtil.loadImage(this, portraitUrl, imgThumbnail);
        imgClose.setOnClickListener(v -> dialog.dismiss());
        tvDownload.setOnClickListener(v -> {
            if (mDownloadUrl == null) {
                ToastUtil.toastShort(getApplicationContext(), "请选择分辨率!");
            } else {
                String url = "http://shouji.360tpcdn.com/170922/9ffde35adefc28d3740d4e16612f078a/com.tencent.tmgp.sgame_22011304.apk";
                VideoDownloadBean downloadBean = new VideoDownloadBean();
                downloadBean.setTitle(title);
                downloadBean.setUrl(url);
                downloadBean.setPortraitUrl(portraitUrl);
                permissionCheckAndDownload(downloadBean);
                dialog.dismiss();
            }

        });

    }

    @Override
    public void downloading() {
        ToastUtil.toastShort(this, "已经添加到下载列表");
    }

    private List<VideoInfoBean> getAdapterData(VideoAddressBean.DataBean.VideoListBean originalList) {
        List<VideoInfoBean> resultList = new ArrayList<>();
        if (originalList.getVideo_1() != null) {
            resultList.add(originalList.getVideo_1());
        }
        if (originalList.getVideo_2() != null) {
            resultList.add(originalList.getVideo_2());
        }
        if (originalList.getVideo_3() != null) {
            resultList.add(originalList.getVideo_3());
        }
        return resultList;
    }
}
