package com.dimon.ganwumei.internal.components;

import com.dimon.ganwumei.features.newsfeed.RecyclerViewFragment;
import com.dimon.ganwumei.internal.ActivityScope;
import com.dimon.ganwumei.internal.modules.ActivityModule;
import com.dimon.ganwumei.internal.modules.GanWuModule;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/22.
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = {ActivityModule.class, GanWuModule.class})
public interface GanWuComponent extends ActivityComponent{
    void inject(RecyclerViewFragment recyclerViewFragment);

}
