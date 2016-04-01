package com.dimon.ganwumei.database;

import com.dimon.ganwumei.database.entities.Images;
import com.dimon.ganwumei.database.entities.News;
import com.dimon.ganwumei.database.entities.Types;

import java.util.List;

import rx.Observable;

/**
 * Created by Dimon on 2016/3/27.
 */
public interface DataService {
    Observable<List<News>> newsList();
    Observable<News> news();
    Observable<Images> findImages(String url);
    Observable<List<News>> newsListByImages(Images images);
    Observable<News> newNews(String type, String desc, String url, Images images, List<Types> types);


}
