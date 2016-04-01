package com.dimon.ganwumei.injector.components;

import android.content.Context;

import com.dimon.ganwumei.injector.ActivityScope;
import com.dimon.ganwumei.injector.modules.ActivityModule;
import com.dimon.ganwumei.injector.modules.GanWuModule;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/22.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = {ActivityModule.class, GanWuModule.class})
public interface GanWuComponent extends ActivityComponent{

    Context activityContext();

}
