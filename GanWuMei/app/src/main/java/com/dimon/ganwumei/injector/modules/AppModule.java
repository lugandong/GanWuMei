package com.dimon.ganwumei.injector.modules;

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

    private final MyApplication myApplication;

    public AppModule(MyApplication myApplication){
        this.myApplication =myApplication;
    }

    @Provides
    @Singleton
    MyApplication provideApplicationContext() {
        return myApplication;
    }


    @Provides
    @Singleton
    ThreadPoolManager provideThreadPoolManager(){
        return new ThreadPoolManager();
    }
}
