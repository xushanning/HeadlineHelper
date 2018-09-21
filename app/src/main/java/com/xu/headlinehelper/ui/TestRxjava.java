package com.xu.headlinehelper.ui;

import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author 言吾許
 */
public class TestRxjava {


    private void test() {
        Observable
                .create((ObservableOnSubscribe<Integer>) e -> {

                })
                .map(integer -> "aaa")
                .map(s -> "aaa")
                .subscribe();

        Observable
                .just(11, 1123)
                .map(integer -> "hello")
                .map(integer -> 222)
                .map(s -> "xu")
                .map((String s) -> {
                    String a = "sssss";
                    String b = "ccc" + a;
                    return b;
                })
                .subscribe(s -> {
                    Logger.d(s);
                    String b = s;
                }, throwable -> {
                    Logger.d(throwable.getMessage());
                });


    }
}
