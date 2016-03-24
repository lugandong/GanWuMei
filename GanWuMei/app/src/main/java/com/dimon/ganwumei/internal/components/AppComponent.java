package com.dimon.ganwumei.internal.components;

import android.content.Context;

import com.dimon.ganwumei.features.newsfeed.activity.BaseActivity;
import com.dimon.ganwumei.internal.modules.ApiModule;
import com.dimon.ganwumei.internal.modules.AppModule;
import com.dimon.ganwumei.util.FileUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/18.
 */
@Singleton
@Component(modules = {AppModule.class , ApiModule.class})
public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(FileUtils fileUtils);

    Context context();

}
