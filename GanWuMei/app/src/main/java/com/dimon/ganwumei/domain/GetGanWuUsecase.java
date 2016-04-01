package com.dimon.ganwumei.domain;

import com.dimon.ganwumei.database.entities.News;
import com.dimon.ganwumei.database.repository.GanWuRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by Dimon on 2016/3/31.
 */
public class GetGanWuUsecase extends Usecase<List<News>> {
    public final static int DEFAULT_CHARACTERS_LIMIT = 20;
    private int mGanWuLimit = DEFAULT_CHARACTERS_LIMIT;
    private final GanWuRepository mRepository;
    private int mCurrentOffset;

    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;

    @Inject
    public GetGanWuUsecase(GanWuRepository mRepository, int mCurrentOffset,
                           @Named("ui_thread") Scheduler mUiThread,
                           @Named("executor_thread") Scheduler mExecutorThread) {
        this.mRepository = mRepository;
        this.mCurrentOffset = mCurrentOffset;
        this.mUiThread = mUiThread;
        this.mExecutorThread = mExecutorThread;
    }

    @Override
    public Observable<List<News>> buildObservable() {
        return null;
    }
}
