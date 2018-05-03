package com.xu.headlinehelper.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xusn10 on 2017/1/9.
 *
 * @author xusn
 */
public class ToastUtil {
    public static void toastLong(Context context, String strText) {

        Toast.makeText(context.getApplicationContext(), strText, Toast.LENGTH_LONG).show();

    }

    public static void toastShort(Context context, String strText) {

        Toast.makeText(context.getApplicationContext(), strText, Toast.LENGTH_SHORT).show();

    }
}
