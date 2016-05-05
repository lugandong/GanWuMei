package com.dimon.ganwumei.injector.components;

import com.dimon.ganwumei.injector.modules.GanWuFragmentModule;
import com.dimon.ganwumei.injector.scope.FragmentScope;
import com.dimon.ganwumei.ui.newsfeed.fragment.GanDailyFragment;
import com.dimon.ganwumei.ui.newsfeed.fragment.GanWuFragment;
import com.dimon.ganwumei.ui.newsfeed.fragment.GanWuListFragment;

import dagger.Component;

/**
 * Fragment组件，可以理解为一个Fragment级别的注入器，是@Inject和@Module的桥梁
 * Created by Dimon on 2016/3/22.
 */
@FragmentScope
@Component(dependencies = ActivityComponent.class,modules = GanWuFragmentModule.class)
public interface GanWuComponent {
    void inject(GanWuFragment fragment);
    void inject(GanDailyFragment fragment);
    void inject(GanWuListFragment fragment);
}
