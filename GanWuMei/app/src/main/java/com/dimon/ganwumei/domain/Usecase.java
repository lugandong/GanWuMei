package com.dimon.ganwumei.domain;

import rx.Observable;

/**
 * Created by Dimon on 2016/3/31.
 */
public abstract class Usecase<T> {
    public abstract Observable<T> buildObservable();

    public Observable<T> execute() {
        return buildObservable();
    }
}
