package com.dimon.ganwumei.features.newsfeed.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.Image;
import com.dimon.ganwumei.database.entity.News;
import com.dimon.ganwumei.features.newsfeed.GanWuListView;
import com.dimon.ganwumei.features.newsfeed.adapter.GanWuAdapter;
import com.dimon.ganwumei.internal.components.GanWuComponent;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmChangeListener;

/**
 * Created by Dimon on 2016/3/14.
 */

public class RecyclerViewFragment extends BaseFragment implements GanWuListView , SwipeRefreshLayout.OnRefreshListener,
        RealmChangeListener {



    public interface GanWuListListener {
        void onGanWuClicked(final Image image);
    }
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private GanWuAdapter ganWuAdapter;
    private ArrayList<Image> mAllBenefitImage;
    private Realm mRealm;
    private LinearLayoutManager mLayoutManager;
    private List<News> newsList;
    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(GanWuComponent.class).inject(this);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupBaseView();

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }

    private void setupBaseView() {
        mSwipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mLayoutManager=new LinearLayoutManager(context());
        initPersonData();
        ganWuAdapter= new GanWuAdapter(newsList, context()) {
            @Override
            protected void onItemClick(View v, int position) {

            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(ganWuAdapter);
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    RecyclerViewFragment.this.onScrollStateChanged();
//                }
//            }
//        });
    }

    private void initPersonData() {
        newsList =new ArrayList<>();
        //添加新闻
        newsList.add(new News(getString(R.string.news_one_title),getString(R.string.news_one_desc),R.mipmap.news_one));
        newsList.add(new News(getString(R.string.news_two_title),getString(R.string.news_two_desc),R.mipmap.news_two));
        newsList.add(new News(getString(R.string.news_three_title),getString(R.string.news_three_desc),R.mipmap.news_three));
        newsList.add(new News(getString(R.string.news_four_title),getString(R.string.news_four_desc),R.mipmap.news_four));
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public void viewImage(Image image) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onChange() {

    }
}

