package com.xu.headlinehelper.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * @author 言吾許
 */

public class ImageLoaderUtil {
    /**
     * 常规使用
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 目标view
     */
    public static void loadImage(Context context, Object url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(new DrawableTransitionOptions().crossFade(800))
                .into(imageView);
    }

    public static void loadCircleImage(Context context, Object url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadLocalImage(Context context, String localPath, ImageView imageView) {
        File file = new File(localPath);
        Glide.with(context).load(file)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(new DrawableTransitionOptions().crossFade(800))
                .into(imageView);
    }
}
