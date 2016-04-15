package com.dimon.ganwumei.widget.subscribers;

import android.content.Context;

import rx.Subscriber;

/**
 * Created by Dimon on 2016/4/15.
 */
public class SwipeRefreshSubscriber<T> extends Subscriber<T>  {

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private Context context;

    public SwipeRefreshSubscriber (SubscriberOnNextListener mSubscriberOnNextListener, Context context){
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
    }
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {
        mSubscriberOnNextListener.onNext(t);
    }
}
