package com.dimon.ganwumei.features.newsfeed;

import android.content.Context;

/**
 * 用来加载数据的接口
 * Created by Dimon on 2016/3/22.
 */
public interface LoadDataView {
    /**
     * 显示一个视图去显示一个进度条加载过程。
     */
    void showLoading();

    /**
     * 隐藏一个加载视图。
     */
    void hideLoading();

    /**
     *
     * 显示重试视图检索数据时的一个错误。
     */
    void showRetry();

    /**
     *
     * 如果有一个错误的话，隐藏显示重试视图检索数据。
     */
    void hideRetry();

    /**
     * 展示一个错误信息
     *
     * @param message 这样一个message代表一个错误
     */
    void showError(String message);

    /**
     * Get a {@link android.content.Context}.
     */
    Context context();
}

