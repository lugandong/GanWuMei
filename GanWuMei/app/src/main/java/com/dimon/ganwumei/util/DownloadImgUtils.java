package com.dimon.ganwumei.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by Dimon on 2016/3/25.
 */
public class DownloadImgUtils {


    public static boolean downloadImgByUrl(String urlStr, File file) {
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;

        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(urlStr)
                .build();

    }

}
