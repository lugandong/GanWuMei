package com.dimon.ganwumei.injector.components;

import com.dimon.ganwumei.MyApplication;
import com.dimon.ganwumei.api.thread.ThreadPoolManager;
import com.dimon.ganwumei.ui.base.BaseActivity;
import com.dimon.ganwumei.injector.modules.ApiModule;
import com.dimon.ganwumei.injector.modules.AppModule;
import com.dimon.ganwumei.util.FileUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/18.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class
        }
)

public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(FileUtils fileUtils);

    MyApplication app();
    ThreadPoolManager threadPoolManager();

}
