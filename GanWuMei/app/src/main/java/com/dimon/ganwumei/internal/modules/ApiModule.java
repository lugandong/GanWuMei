package com.dimon.ganwumei.internal.modules;


import com.dimon.ganwumei.database.DayGanhuoResult;
import com.dimon.ganwumei.database.GanhuoResult;
import com.dimon.ganwumei.network.RestAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * 网络接口
 * Created by Dimon on 2016/3/18.
 */
@Module
public class ApiModule {
    private static final String BASE_URL = "http://gank.io/api/";


    final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").serializeNulls().create();

    private  RestAPI mWebService;

    @Provides
    @Singleton
    RestAPI provideApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient())
                .build();
        return retrofit.create(RestAPI.class);
    }

    private OkHttpClient okHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // config log
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(logging)
                .build();

    }

    public Observable<GanhuoResult> getCommonGoods(String type, int limit, int page) {
        if("Android".equalsIgnoreCase(type)){
            return mWebService.getAndroidGoods(limit, page);
        }
        if("IOS".equalsIgnoreCase(type)){
            return mWebService.getIosGoods(limit, page);
        }
        return mWebService.getAndroidGoods(limit, page);
    }

    public Observable<GanhuoResult> getAndroidGoods(int limit, int page) {
        return mWebService.getAndroidGoods(limit, page);
    }

    public Observable<GanhuoResult> getIosGoods(int limit, int page) {
        return mWebService.getIosGoods(limit, page);
    }

    public Observable<GanhuoResult> getAllGoods(int limit, int page) {
        return mWebService.getAllGoods(limit, page);
    }

    public Observable<GanhuoResult> getBenefitsGoods(int limit, int page) {
        return mWebService.getBenefitsGoods(limit, page);
    }

    public Observable<DayGanhuoResult> getGoodsByDay(int year, int month, int day) {
        return mWebService.getGoodsByDay(year, month,day);
    }
}
