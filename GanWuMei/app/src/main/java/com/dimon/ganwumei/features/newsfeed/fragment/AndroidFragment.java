package com.dimon.ganwumei.features.newsfeed.fragment;

import android.content.Context;

import com.dimon.ganwumei.database.entitys.News;
import com.dimon.ganwumei.features.newsfeed.GanWuListView;

import java.util.Collection;

/**
 * Created by Dimon on 2016/3/26.
 */
public class AndroidFragment extends BaseFragment implements GanWuListView{
    @Override
    public void renderNewsList(Collection<News> newsCollection) {

    }

    @Override
    public void viewNews(News news) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }
}
