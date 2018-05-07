package com.xu.headlinehelper.ui.mian;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.R;
import com.xu.headlinehelper.base.BaseActivity;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.util.ToastUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import me.jessyan.progressmanager.ProgressListener;
import me.jessyan.progressmanager.ProgressManager;
import me.jessyan.progressmanager.body.ProgressInfo;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author xusn10
 */
@RuntimePermissions
public class MainActivity extends BaseActivity<IMainContract.IMainPresenter> implements IMainContract.IMainView, ProgressListener {
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;
    @BindView(R.id.nv_drawer)
    NavigationView nvDrawer;
    @BindView(R.id.tb_main)
    Toolbar tbMain;
    private ActionBarDrawerToggle drawerToggle;
    /**
     * 视频网页的正则
     */
    Pattern pattern = Pattern.compile("【(.+)】\\n(http.+)");

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initOthers() {
        String shareUrl = "https://m.365yg.com/group/6530825337272533512/?iid=29858226621&app=news_article&timestamp=1524014770&tt_from=android_share&utm_medium=toutiao_android&utm_campaign=client_share";
        mPresenter.getVideoUrl(shareUrl);
        String originalUrl = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        if (originalUrl != null) {
            Logger.d(originalUrl);
            handleUrl(originalUrl);
        }
        initDrawer();


    }

    /**
     * 初始化侧滑栏相关
     */
    private void initDrawer() {
        setSupportActionBar(tbMain);
        drawerToggle = new ActionBarDrawerToggle(this, dlMain, tbMain, R.string.drawer_open, R.string.drawer_close);
        dlMain.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        nvDrawer.setCheckedItem(R.id.drawer_about_me0);
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_about_me0:

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public IMainContract.IMainPresenter setPresenter() {
        return new MainPresenter();
    }

    private void handleUrl(String originalUrl) {
        Matcher matcher = pattern.matcher(originalUrl);
        if (matcher.find()) {
            String videoName = matcher.group(1);
            String shareUrl = matcher.group(2);
            mPresenter.getVideoUrl(shareUrl);
            ProgressManager.getInstance().addResponseListener("url", this);
        }
    }

    @Override
    public void showDownLoadWindow(VideoAddressBean.DataBean.VideoListBean videoListBean) {
        //先固定下在第一个
        MainActivityPermissionsDispatcher.downLoadVideoWithPermissionCheck(this, videoListBean.getVideo_1().getMain_url());
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void downLoadVideo(String videoAddressUrl) {
        mPresenter.downLoadVideo(videoAddressUrl);
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
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onProgress(ProgressInfo progressInfo) {

    }

    @Override
    public void onError(long id, Exception e) {

    }
}
