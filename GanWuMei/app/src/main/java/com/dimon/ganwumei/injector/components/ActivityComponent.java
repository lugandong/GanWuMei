package com.dimon.ganwumei.injector.components;

import android.content.Context;

import com.dimon.ganwumei.database.DataManager;
import com.dimon.ganwumei.injector.scope.ActivityScope;
import com.dimon.ganwumei.injector.modules.ActivityModule;
import com.dimon.ganwumei.ui.base.BaseActivity;
import com.dimon.ganwumei.ui.newsfeed.activity.MainActivity;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/20.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);
    void inject(MainActivity mainActivity);

    Context CONTEXT();
    DataManager DATA_MANAGER();
}
