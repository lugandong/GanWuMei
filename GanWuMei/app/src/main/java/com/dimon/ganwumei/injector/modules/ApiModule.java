package com.dimon.ganwumei.injector.modules;


import android.app.Application;

import com.dimon.ganwumei.network.HttpMethods;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 网络接口
 * Created by Dimon on 2016/3/18.
 */
@Module
public class ApiModule {
    private static final String BASE_URL = "http://gank.io/api/";
    private Application mApplication;

    final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();

    public ApiModule(Application mApplication) {
        this.mApplication =mApplication;
    }


//    @Provides
//    @Singleton
//    RestAPI provideApiService() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(okHttpClient())
//                .build();
//        return retrofit.create(RestAPI.class);
//    }
//
//    private OkHttpClient okHttpClient(){
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // config log
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        return new OkHttpClient.Builder()
//                .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
//                .addInterceptor(logging)   //拦截器
//                .build();
//
//    }

    @Provides
    @Singleton
    HttpMethods provideHttpMethods(){
        return new HttpMethods();
    }
}
