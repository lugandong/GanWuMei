package com.dimon.ganwumei.database.repository;

import com.dimon.ganwumei.database.entities.News;

import java.util.List;

import rx.Observable;

/**
 * Created by Dimon on 2016/4/1.
 */
public interface GanWuRepository {
    Observable<News> getGanWu (final int characterId);

    Observable<List<News>> getGanWus (int offset);

}
