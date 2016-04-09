package com.dimon.ganwumei.injector.modules;

import android.app.Application;

import com.dimon.ganwumei.api.thread.ThreadPoolManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dimon on 2016/3/18.
 */
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application mApplication){
        this.mApplication =mApplication;
    }

    @Provides
    @Singleton
    Application provideApplicationContext() {
        return mApplication;
    }


    @Provides
    @Singleton
    ThreadPoolManager provideThreadPoolManager(){
        return new ThreadPoolManager();
    }
}
