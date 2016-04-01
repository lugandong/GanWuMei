package com.dimon.ganwumei.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Dimon on 2016/3/27.
 */
public class ToastUtils {

    public static void ToastMessage(Context context, String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
