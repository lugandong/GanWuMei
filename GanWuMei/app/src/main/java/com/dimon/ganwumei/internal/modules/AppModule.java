package com.dimon.ganwumei.internal.modules;

import android.app.Application;
import android.content.Context;

import com.dimon.ganwumei.MyApplication;
import com.dimon.ganwumei.api.thread.ThreadPoolManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dimon on 2016/3/18.
 */
@Module
public class AppModule {

    private final MyApplication application;

    public AppModule(MyApplication application){
        this.application=application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    ThreadPoolManager provideThreadPoolManager(){
        return new ThreadPoolManager();
    }
}
