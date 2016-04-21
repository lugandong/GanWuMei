package com.dimon.ganwumei.injector.components;

import android.content.Context;

import com.dimon.ganwumei.database.DataManager;
import com.dimon.ganwumei.injector.modules.ApiModule;
import com.dimon.ganwumei.injector.modules.AppModule;
import com.dimon.ganwumei.injector.qualifier.ContextType;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/18.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})

public interface AppComponent {

    @ContextType("application")
    Context context();

    DataManager DATA_MANAGER();
}
