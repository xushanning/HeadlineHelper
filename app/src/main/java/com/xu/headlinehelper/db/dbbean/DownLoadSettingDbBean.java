package com.xu.headlinehelper.db.dbbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author 许 on 2018/6/5.
 *         下载设置bean
 */
@Entity
public class DownLoadSettingDbBean {
    @Id(autoincrement = true)
    private Long id;
    /**
     * 是否只有wifi下才能下载
     */
    private boolean wifiDownLoad;
    /**
     * 是否显示通知
     */
    private boolean showNotify;
    /**
     * 是否震动
     */
    private boolean shock;
    /**
     * 同时下载数
     */
    private int downLoadCount;
    /**
     * 重试次数
     */
    private int retryCount;
    /**
     * 存储路径
     */
    private String savePath;

    @Generated(hash = 1791938447)
    public DownLoadSettingDbBean(Long id, boolean wifiDownLoad, boolean showNotify,
                                 boolean shock, int downLoadCount, int retryCount, String savePath) {
        this.id = id;
        this.wifiDownLoad = wifiDownLoad;
        this.showNotify = showNotify;
        this.shock = shock;
        this.downLoadCount = downLoadCount;
        this.retryCount = retryCount;
        this.savePath = savePath;
    }

    @Generated(hash = 1508587014)
    public DownLoadSettingDbBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getWifiDownLoad() {
        return this.wifiDownLoad;
    }

    public void setWifiDownLoad(boolean wifiDownLoad) {
        this.wifiDownLoad = wifiDownLoad;
    }

    public boolean getShowNotify() {
        return this.showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public boolean getShock() {
        return this.shock;
    }

    public void setShock(boolean shock) {
        this.shock = shock;
    }

    public int getDownLoadCount() {
        return this.downLoadCount;
    }

    public void setDownLoadCount(int downLoadCount) {
        this.downLoadCount = downLoadCount;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

}
