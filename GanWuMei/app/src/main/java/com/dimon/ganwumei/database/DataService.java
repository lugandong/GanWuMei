package com.dimon.ganwumei.database;

import com.dimon.ganwumei.database.entities.Image;
import com.dimon.ganwumei.database.entities.News;
import com.dimon.ganwumei.database.entities.Types;

import java.util.List;

import rx.Observable;

/**
 *
 * Created by Dimon on 2016/3/27.
 */
public interface DataService {
    Observable<List<News>> newsList();
    Observable<News> news();
    Observable<Image> findImages(String url);
    Observable<List<News>> newsListByImages(Image image);
    Observable<News> newNews(String type, String desc, String url, Image image, List<Types> types);
}
