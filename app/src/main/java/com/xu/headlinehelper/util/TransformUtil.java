package com.xu.headlinehelper.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xusn10
 *
 * @author xu
 */

public class TransformUtil {
    public static <T> ObservableTransformer<T, T> defaultSchedulers() {
        return upstream -> upstream.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }

    public static <T> ObservableTransformer<T, T> allIo() {
        return upstream -> upstream.observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }
}
