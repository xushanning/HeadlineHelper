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
import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.adapter.quick.DefinitionListQuickAdapter;
import com.xu.headlinehelper.base.BaseActivity;
import com.xu.headlinehelper.bean.VideoAddressBean;
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
 *         下载的基础类  主activity和newtask  activity都要继承自此类
 *         封装权限控制
 */
@RuntimePermissions
public abstract class BaseDownloadActivity<T extends IBaseDownloadContract.IBaseDownloadPresenter> extends BaseActivity<T> implements IBaseDownloadContract.IBaseDownloadView {
    private String mDownloadUrl;

    /**
     * 检查权限并且下载
     *
     * @param downloadUrl 下载地址
     *                    由于BaseDownloadActivityPermissionsDispatcher 这个类非public，所以在
     *                    main activity中不能直接调用，因此写成这种形式
     */
    public void permissionCheckAndDownload(String downloadUrl) {
        BaseDownloadActivityPermissionsDispatcher.downLoadVideoWithPermissionCheck(this, downloadUrl);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void downLoadVideo(String videoAddressUrl) {
        mPresenter.downloadVideo(videoAddressUrl);
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(R.string.permission_write_external_storage)
                .setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        request.cancel();
                    }
                }).show();
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
        BaseDownloadActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
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
        quickAdapter.setOnRadioButtonChangeListener(new DefinitionListQuickAdapter.OnRadioButtonSelectListener() {
            @Override
            public void onRadioButtonChange(String downloadUrl) {
                mDownloadUrl = downloadUrl;
            }
        });
        tvTitle.setText(dataBean.getUser_id());
        ImageLoaderUtil.loadImage(this, dataBean.getPoster_url(), imgThumbnail);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDownloadUrl == null) {
                    ToastUtil.toastShort(getApplicationContext(), "请选择分辨率!");
                } else {
                    permissionCheckAndDownload(mDownloadUrl);
                    dialog.dismiss();
                }

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
