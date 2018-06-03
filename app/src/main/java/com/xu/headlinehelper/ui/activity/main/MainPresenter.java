package com.xu.headlinehelper.ui.activity.main;

import android.util.Base64;

import com.orhanobut.logger.Logger;
import com.xu.headlinehelper.base.BasePresenter;
import com.xu.headlinehelper.bean.VideoAddressBean;
import com.xu.headlinehelper.net.ApiExecption;
import com.xu.headlinehelper.net.HttpConstants;
import com.xu.headlinehelper.net.RetrofitFactory;
import com.xu.headlinehelper.net.RetryRequestWithDelay;
import com.xu.headlinehelper.util.TransformUtil;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * @author 言吾許
 */

public class MainPresenter extends BasePresenter<IMainContract.IMainView> implements IMainContract.IMainPresenter {
    /**
     * 视频真实地址接口请求地址的正则
     */
    private Pattern pattern = Pattern.compile("(?<=videoId: ').+(?=')");
    /**
     * 随机数的位数
     */
    private static final int RANDOM_COUNT = 16;

    @Override
    public void getVideoUrl(String shareUrl) {
        RetrofitFactory.getVideoApi()
                .getVideoHtml(shareUrl)
                .flatMap(new Function<String, ObservableSource<VideoAddressBean>>() {
                    @Override
                    public ObservableSource<VideoAddressBean> apply(String s) throws Exception {
                        Logger.d(s);
                        Matcher matcher = pattern.matcher(s);
                        if (matcher.find()) {
                            Logger.d("----");
                            String videoId = matcher.group(0);
                            Logger.d(videoId + "");
                            //1.将/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()}，进行crc32加密。
                            Random random = new Random();
                            StringBuilder randomResult = new StringBuilder();
                            for (int i = 0; i < RANDOM_COUNT; i++) {
                                randomResult.append(random.nextInt(10));
                            }
                            CRC32 crc32 = new CRC32();
                            String URL_VIDEO = "/video/urls/v/1/toutiao/mp4/%s?r=%s";
                            String format = String.format(URL_VIDEO, videoId, randomResult.toString());
                            crc32.update(format.getBytes());
                            //2.访问http://i.snssdk.com/video/urls/v/1/toutiao/mp4/{videoid}?r={Math.random()}&s={crc32值}
                            String crcString = String.valueOf(crc32.getValue());
                            String videoAddressUrl = HttpConstants.VIDEO_URL + format + "&s=" + crcString;
                            return RetrofitFactory.getVideoApi().getVideoAddress(videoAddressUrl);
                        } else {
                            throw new ApiExecption("未找到视频ID!");
                        }
                    }
                }).map(new Function<VideoAddressBean, VideoAddressBean.DataBean.VideoListBean>() {
                           @Override
                           public VideoAddressBean.DataBean.VideoListBean apply(VideoAddressBean videoAddressBean) throws Exception {
                               if (videoAddressBean.getMessage().equals(HttpConstants.MESSAGE_SUCCESS)) {
                                   VideoAddressBean.DataBean.VideoListBean videoList = videoAddressBean.getData().getVideo_list();
                                   return decodeVideoAddress(videoList);
                               } else {
                                   throw new ApiExecption("未包含视频地址!");
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
        ).compose(mView.<VideoAddressBean.DataBean.VideoListBean>bindToLife())
                .compose(TransformUtil.<VideoAddressBean.DataBean.VideoListBean>defaultSchedulers())
                .retryWhen(new RetryRequestWithDelay(3, 3000))
                .subscribe(new Consumer<VideoAddressBean.DataBean.VideoListBean>() {
                    @Override
                    public void accept(VideoAddressBean.DataBean.VideoListBean videoListBean) throws Exception {
                        Logger.d(videoListBean.getVideo_1().getMain_url());
                        mView.showDownLoadWindow(videoListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.d(throwable.getMessage());
                    }
                });

    }

    @Override
    public void downLoadVideo(String videoAddressUrl) {

    }
}
