package com.xu.headlinehelper;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Keep;
import android.support.multidex.MultiDex;

import com.orhanobut.logger.Logger;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.xu.headlinehelper.util.SecretConstant;
import com.xu.headlinehelper.util.ToastUtil;

/**
 * @author 言吾許
 * 热修复application 禁止业务逻辑
 */
public class SophixStubApplication extends SophixApplication {

    @Keep
    @SophixEntry(MyApplication.class)

    static class RealApplicationStub {
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        initSophix();
    }

    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData(SecretConstant.ID_SECRET, SecretConstant.APP_SECRET, SecretConstant.RSA_SECRET)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub((i, code, s, i2) -> {
                    Logger.d(code + s);
                    switch (code) {
                        case PatchStatus.CODE_LOAD_SUCCESS:
                            Logger.d("表明补丁加载成功");
                            break;
                        case PatchStatus.CODE_LOAD_RELAUNCH:
                            Logger.d("新补丁生效需要重启");
                            ToastUtil.toastShort(getApplicationContext(), "更新完成，应用即将重启");
                            new Handler().postDelayed(SophixManager.getInstance()::killProcessSafely, 1000);
                            break;
                        case PatchStatus.CODE_REQ_NOUPDATE:
                            Logger.d("没有最新的补丁");
                            break;
                        case PatchStatus.CODE_DOWNLOAD_SUCCESS:
                            Logger.d("补丁下载成功");
                            break;
                        default:
                            Logger.d("其它code信息, 查看PatchStatus类说明");
                            break;
                    }
                }).initialize();
    }
}
