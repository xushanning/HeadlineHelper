package com.xu.headlinehelper.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by xusn10 on 2018/3/9.
 *
 * @author xusn10
 *         请求重试
 */

public class RetryRequestWithDelay implements Function<Observable<Throwable>, ObservableSource<?>> {
    /**
     * 最大尝试次数
     */
    private final int maxRetryCount;
    /**
     * 尝试间隔
     */
    private final int retryIntervalMills;
    /**
     * 已经尝试的次数
     */
    private int hasRetryCount = 0;

    /**
     * @param maxRetryCount      最大尝试次数
     * @param retryIntervalMills 尝试间隔(毫秒)
     */
    public RetryRequestWithDelay(int maxRetryCount, int retryIntervalMills) {
        this.maxRetryCount = maxRetryCount;
        this.retryIntervalMills = retryIntervalMills;
    }

    @Override
    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                //当发生的异常 = 网络异常 = IO异常 才选择重试

                if (throwable instanceof IOException) {
                    if (++hasRetryCount <= maxRetryCount) {
                        return Observable.timer(retryIntervalMills, TimeUnit.MILLISECONDS);
                    } else {
                        return Observable.error(new Throwable("重试次数已经达到最大值:" + maxRetryCount + "次"));
                    }
                } else {
                    return Observable.error(throwable);
                }
            }
        });
    }
}
