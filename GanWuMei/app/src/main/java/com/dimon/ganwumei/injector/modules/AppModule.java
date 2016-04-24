package com.dimon.ganwumei.injector.modules;

import android.content.Context;

import com.dimon.ganwumei.MyApplication;
import com.dimon.ganwumei.injector.qualifier.ContextType;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dimon on 2016/3/18.
 */
@Module
public class AppModule {

    private MyApplication mApplication;

    public AppModule(MyApplication application){
        this.mApplication = application;
    }

    @Provides
    @Singleton
    MyApplication provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    @ContextType("application")
    Context provideContext(){
        return MyApplication.getApplication();
    }
}
