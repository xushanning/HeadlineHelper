package com.xu.headlinehelper.bean;

import java.io.Serializable;

/**
 * @author 言吾許
 *         视频下载信息bean
 */

public class VideoDownloadBean implements Serializable {
    /**
     * 视频的地址
     */
    private String url;
    /**
     * 缩略图地址
     */
    private String portraitUrl;
    /**
     * 视频名字
     */
    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
