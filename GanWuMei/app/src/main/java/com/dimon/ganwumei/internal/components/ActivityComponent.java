package com.dimon.ganwumei.internal.components;

import android.app.Activity;

import com.dimon.ganwumei.internal.ActivityScope;
import com.dimon.ganwumei.internal.modules.ActivityModule;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/20.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();

}
