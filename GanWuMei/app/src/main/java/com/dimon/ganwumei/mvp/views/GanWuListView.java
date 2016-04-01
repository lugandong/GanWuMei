package com.dimon.ganwumei.mvp.views;

import com.dimon.ganwumei.database.entities.News;

import java.util.List;

/**
 * Created by Dimon on 2016/3/22.
 */
public interface GanWuListView extends View{

    void bindGanWuList(List<News> newses);

    void showGanWuList();

    void hideGanWuList();

    void showLoadingMoreGanWuIndicator();

    void hideLoadingMoreGanWuIndicator();

    void hideLoadingIndicator ();

    void showLoadingView();

    void hideLoadingView();

    void showLightError();

    void hideErrorView();

    void showEmptyIndicator();

    void hideEmptyIndicator();

    void updateGanWuList(int ganWuLimit);

    void showConnectionErrorMessage();

    void showServerErrorMessage();

    void showUknownErrorMessage();

    void showDetailScreen(String desc, String url);
}
