package com.xu.headlinehelper.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coorchice.library.ImageEngine;
import com.coorchice.library.image_engine.Engine;

/**
 * @author 言吾許
 * superTextView glide引擎
 */
public class GlideEngine implements Engine {
    private Context context;

    public GlideEngine(Context context) {
        this.context = context;
    }

    @Override
    public void load(String url, ImageEngine.Callback callback) {
        Glide.with(context).load(url).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                callback.onCompleted(resource);
            }
        });
    }
}
