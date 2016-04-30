package com.dimon.ganwumei.injector.components;

import com.dimon.ganwumei.injector.modules.GanWuFragmentModule;
import com.dimon.ganwumei.injector.scope.FragmentScope;
import com.dimon.ganwumei.ui.newsfeed.fragment.GanWuFragment;
import com.dimon.ganwumei.ui.newsfeed.fragment.GanWuListFragment;

import dagger.Component;

/**
 * Created by Dimon on 2016/3/22.
 */
@FragmentScope
@Component(dependencies = ActivityComponent.class,modules = GanWuFragmentModule.class)
public interface GanWuComponent {
    void inject(GanWuFragment fragment);
    void inject(GanWuListFragment fragment);
}
