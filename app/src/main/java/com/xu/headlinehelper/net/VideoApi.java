package com.xu.headlinehelper.net;

import com.xu.headlinehelper.bean.VideoAddressBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author 言吾許
 */

public interface VideoApi {
    /**
     * 获取分享页面的html代码
     *
     * @param shareUrl 分享地址
     * @return observable
     */
    @GET
    Observable<String> getVideoHtml(@Url String shareUrl);

    /**
     * 获取视频的真实地址
     *
     * @param videoAddressUrl 真实地址请求接口
     * @return 真实地址（三种清晰格式）
     */
    @GET
    Observable<VideoAddressBean> getVideoAddress(@Url String videoAddressUrl);
}
