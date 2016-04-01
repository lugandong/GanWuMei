package com.dimon.ganwumei.injector.components;

import android.content.Context;

import com.dimon.ganwumei.injector.ActivityScope;
import com.dimon.ganwumei.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/20.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = ActivityModule.class
)
public interface ActivityComponent {
    Context context();

}
