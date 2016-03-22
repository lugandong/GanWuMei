package com.dimon.ganwumei.network;

import android.graphics.Point;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Dimon on 2016/3/11.
 */
public interface ImageFetcher {
    void prefetchImage(String url, Point measured)
            throws IOException, InterruptedException, ExecutionException;
}
