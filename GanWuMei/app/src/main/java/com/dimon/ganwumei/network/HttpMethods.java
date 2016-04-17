package com.dimon.ganwumei.network;

import com.dimon.ganwumei.database.entities.Images;
import com.dimon.ganwumei.database.entities.Item;
import com.dimon.ganwumei.util.GanWuDataToItemsMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.socks.library.KLog;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

    private Realm mRealm;
    private static RestAPI restAPI;
    private Retrofit retrofit;

    protected Subscription subscription;

    //构造方法私有

    public HttpMethods(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient())
                .build();
        restAPI = retrofit.create(RestAPI.class);
    }

    final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    private OkHttpClient okHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // config log
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)   //拦截器
                .build();

    }

    /**
     * 被Dagger代替
     */
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
        KLog.a(restAPI);
        unsubscribe();
        Observable observable = restAPI.getGanWuData("2016","04","08")
                .map(GanWuDataToItemsMapper.getInstance());
        toSubscribe(observable, subscriber);
    }

    public void getImage(Subscriber<List<Images>> subscriber){
        unsubscribe();
//        Observable observable = restAPI.getImageData(1)
//                .map(imageData -> imageData.results);
        subscription = mRealm
                .where(Images.class)
                .findAllSortedAsync("desc")
                .asObservable()
                .filter(images -> images.isLoaded())
                .flatMap(images1 -> Observable.from(images1))
                .flatMap(images2 -> restAPI.getImageData(1))
                .map(imageData -> imageData.results)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
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
