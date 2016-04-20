package com.dimon.ganwumei.injector.modules;


import android.app.Application;

import com.dimon.ganwumei.network.HttpMethods;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 网络接口
 * Created by Dimon on 2016/3/18.
 */
@Module
public class ApiModule {
    private Application mApplication;

    public ApiModule(Application mApplication) {
        this.mApplication =mApplication;
    }


    @Provides
    @Singleton
    HttpMethods provideHttpMethods(){
        return HttpMethods.getInstance();
    }
}
