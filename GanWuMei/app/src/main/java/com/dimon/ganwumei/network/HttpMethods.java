package com.dimon.ganwumei.network;

import com.dimon.ganwumei.database.entities.Item;
import com.dimon.ganwumei.util.GanWuDataToItemsMapper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Dimon on 2016/3/31.
 */
public class HttpMethods {
    public static final String BASE_URL = "http://gank.io/api/";

    private static final int DEFAULT_TIMEOUT = 5;

    @Inject
    static RestAPI restAPI;
    protected Subscription subscription;

    //构造方法私有
    private HttpMethods(){
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }


    /**
     * 用于获取Gank里新闻消息
     * @param subscriber 由调用者传过来的观察者对象
     *
     */
    public void getGanWu(Subscriber<List<Item>> subscriber){

        unsubscribe();
        Observable observable = restAPI.getGanWuData(2016,03,25)
                .map(GanWuDataToItemsMapper.getInstance());

        toSubscribe(observable, subscriber);
    }
    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        subscription = o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
