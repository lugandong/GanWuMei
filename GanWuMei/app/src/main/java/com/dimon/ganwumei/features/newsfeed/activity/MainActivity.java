package com.dimon.ganwumei.features.newsfeed.activity;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dimon.ganwumei.R;
import com.dimon.ganwumei.features.newsfeed.adapter.TabFragmentAdapter;
import com.dimon.ganwumei.features.newsfeed.fragment.GanWuFragment;
import com.dimon.ganwumei.internal.HasComponent;
import com.dimon.ganwumei.internal.components.DaggerGanWuComponent;
import com.dimon.ganwumei.internal.components.GanWuComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements HasComponent<GanWuComponent> {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tabLayout)
    TabLayout mTab;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.fabBtn)
    FloatingActionButton fabBtn;
    @Bind(R.id.rootLayout)
    CoordinatorLayout rootLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private GanWuComponent ganWuComponent;


    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializeInjector();
        initView();
    }


    private void initView() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("GanWuMei");
        mTab.addTab(mTab.newTab().setText("All"));
        mTab.addTab(mTab.newTab().setText("Android"));
        mTab.addTab(mTab.newTab().setText("iOS"));
        List<String> tabList = new ArrayList<>();
        tabList.add("All");
        tabList.add("Android");
        tabList.add("iOS");
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < tabList.size(); i++) {
            if (i == 0){
                Fragment fragment = new GanWuFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }else if (i == 1){
                Fragment fragment = new GanWuFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }else {
                Fragment fragment = new GanWuFragment();
                Bundle bundle = new Bundle();
                fragment.setArguments(bundle);
                fragmentList.add(fragment);
            }

        }
        TabFragmentAdapter fragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragmentList, tabList);
        viewPager.setAdapter(fragmentAdapter);//给ViewPager设置适配器
        mTab.setupWithViewPager(viewPager);//将TabLayout和ViewPager关联起来。
        mTab.setTabsFromPagerAdapter(fragmentAdapter);//给Tabs设置适配器

        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);

        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "Hello. I am Snackbar!", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();
            }
        });

        collapsingToolbarLayout.setTitle("GanWuMei");

    }

    private void initializeInjector() {
        this.ganWuComponent = DaggerGanWuComponent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public GanWuComponent getComponent() {
        return ganWuComponent;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}