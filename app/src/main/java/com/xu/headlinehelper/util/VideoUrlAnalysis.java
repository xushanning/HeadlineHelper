package com.xu.headlinehelper.util;

import android.util.Base64;

import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.net.ApiException;
import com.xu.headlinehelper.net.HttpConstants;
import com.xu.headlinehelper.net.RetrofitFactory;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * @author 言吾許
 *         视频地址解析
 */

public class VideoUrlAnalysis {
    /**
     * 视频网页的正则
     */
    private Pattern httpPattern = Pattern.compile("(http.+)");
    /**
     * 抖音小视频的正则
     */
    private Pattern douYinPattern = Pattern.compile("group/(.+?)/");
    /**
     * 西瓜视频的正则
     */
    private Pattern xiGuaPattern = Pattern.compile("cn/(.+?)/");
    /**
     * 视频id的正则
     */
    private Pattern videoPattern = Pattern.compile("(?<=videoId: ').+(?=')");
    /**
     * 视频标题的正则
     */
    private Pattern titlePattern = Pattern.compile("<title>(.+)</title>");
    /**
     * 视频播放网页的base url
     */
    private static final String BASE_365_URL = "https://www.365yg.com/";
    /**
     * 随机数的位数
     */
    private static final int RANDOM_COUNT = 16;
    /**
     * 视频名称
     */
    private String videoTitle = "未知";

    /**
     * 获取视频真实地址
     *
     * @param originalUrl 分享的链接
     * @return 真实地址的列表
     */
    public Observable<VideoAddressBean.DataBean> getDownloadObservable(final String originalUrl) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //是否是分享链接
                Matcher matcher = httpPattern.matcher(originalUrl);
                if (matcher.find()) {
                    String shareUrl = matcher.group(1);
                    e.onNext(shareUrl);
                } else {
                    throw new ApiException("不是分享链接");
                }
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                Matcher douYinMatcher = douYinPattern.matcher(s);
                Matcher xiGuaMatcher = xiGuaPattern.matcher(s);

                if (douYinMatcher.find()) {
                    return BASE_365_URL + "a" + douYinMatcher.group(1);
                } else if (xiGuaMatcher.find()) {
                    return BASE_365_URL + xiGuaMatcher.group(1);
                } else {
                    throw new ApiException("不是抖音或者西瓜视频链接！");
                }
            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return RetrofitFactory.getVideoApi().getVideoHtml(s);
            }
        }).flatMap(new Function<String, ObservableSource<VideoAddressBean>>() {
            @Override
            public ObservableSource<VideoAddressBean> apply(String s) throws Exception {
                Matcher videoPatcher = videoPattern.matcher(s);
                Matcher titlePatcher = titlePattern.matcher(s);
                if (videoPatcher.find()) {
                    //把视频名称抓出来
                    if (titlePatcher.find()) {
                        videoTitle = titlePatcher.group(1);
                    }
                    String videoId = videoPatcher.group(0);
                    //1.将/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()}，进行crc32加密。
                    Random random = new Random();
                    StringBuilder randomResult = new StringBuilder();
                    for (int i = 0; i < RANDOM_COUNT; i++) {
                        randomResult.append(random.nextInt(10));
                    }
                    CRC32 crc32 = new CRC32();
                    String videoUrl = "/video/urls/v/1/toutiao/mp4/%s?r=%s";
                    String format = String.format(videoUrl, videoId, randomResult.toString());
                    crc32.update(format.getBytes());
                    //2.访问http://i.snssdk.com/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()}&s={crc32值}
                    String crcString = String.valueOf(crc32.getValue());
                    String videoAddressUrl = HttpConstants.VIDEO_URL + format + "&s=" + crcString;
                    return RetrofitFactory.getVideoApi().getVideoAddress(videoAddressUrl);
                } else {
                    throw new ApiException("解析错误!");
                }
            }
        }).map(new Function<VideoAddressBean, VideoAddressBean.DataBean>() {
                   @Override
                   public VideoAddressBean.DataBean apply(VideoAddressBean videoAddressBean) throws Exception {
                       if (videoAddressBean.getMessage().equals(HttpConstants.MESSAGE_SUCCESS)) {
                           //把这个闲着的user_id变成视频的名称
                           VideoAddressBean.DataBean dataBean = videoAddressBean.getData();
                           dataBean.setUser_id(videoTitle);
                           dataBean.setVideo_list(decodeVideoAddress(dataBean.getVideo_list()));
                           return dataBean;
                       } else {
                           throw new ApiException("未包含视频地址!");
                       }
                   }

                   /**
                    * 转换videoList
                    * @param originalList 初始化list
                    * @return 变换后的list
                    */
                   private VideoAddressBean.DataBean.VideoListBean decodeVideoAddress(VideoAddressBean.DataBean.VideoListBean originalList) {
                       if (originalList.getVideo_1() != null) {
                           originalList.getVideo_1().setMain_url(getRealPath(originalList.getVideo_1().getMain_url()));
                       }
                       if (originalList.getVideo_2() != null) {
                           originalList.getVideo_2().setMain_url(getRealPath(originalList.getVideo_2().getMain_url()));
                       }
                       if (originalList.getVideo_3() != null) {
                           originalList.getVideo_3().setMain_url(getRealPath(originalList.getVideo_3().getMain_url()));
                       }
                       return originalList;
                   }

                   /**
                    * 解码
                    * @param base64 base64的原string
                    * @return 解码后的
                    */
                   private String getRealPath(String base64) {
                       return new String(Base64.decode(base64.getBytes(), Base64.DEFAULT));
                   }
               }
        ).compose(TransformUtil.<VideoAddressBean.DataBean>defaultSchedulers());
    }
}
