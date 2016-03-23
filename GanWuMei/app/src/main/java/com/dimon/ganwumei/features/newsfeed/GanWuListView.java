package com.dimon.ganwumei.features.newsfeed;

import com.dimon.ganwumei.database.entitys.News;

import java.util.Collection;

/**
 * Created by Dimon on 2016/3/22.
 */
public interface GanWuListView extends LoadDataView{

    void renderNewsList(Collection<News> newsCollection);

    void viewNews(News news);
}
