package com.dimon.ganwumei.database;

import android.app.Application;

import com.dimon.ganwumei.database.entities.Images;
import com.dimon.ganwumei.database.entities.News;
import com.dimon.ganwumei.database.entities.Types;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Dimon on 2016/3/27.
 */
public class RealmDataService implements DataService {

    @Inject
    Application application;

    @Override
    public Observable<List<News>> newsList() {
        return null;
    }

    @Override
    public Observable<News> news() {
        return null;
    }

    @Override
    public Observable<Images> findImages(String url) {
        return null;
    }

    @Override
    public Observable<List<News>> newsListByImages(Images images) {
        return null;
    }

    @Override
    public Observable<News> newNews(String type, String desc, String url, Images images, List<Types> types) {
        return null;
    }

}
