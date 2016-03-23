package com.dimon.ganwumei.features.newsfeed.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.entitys.News;
import com.dimon.ganwumei.features.newsfeed.GanWuListView;
import com.dimon.ganwumei.features.newsfeed.adapter.GanWuAdapter;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Dimon on 2016/3/23.
 */
public class GanWuFragment extends Fragment implements GanWuListView {

    private View view;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GanWuAdapter mAdapter;
    private List<News> newsList;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        KLog.a(mRecyclerView);
        linearLayoutManager = new LinearLayoutManager(context());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter = new GanWuAdapter(newsList, context()));

    }

    private void initData() {
        newsList = new ArrayList<>();
        //添加新闻
        newsList.add(new News(getString(R.string.news_one_title), getString(R.string.news_one_desc), R.mipmap.news_one));
        newsList.add(new News(getString(R.string.news_two_title), getString(R.string.news_two_desc), R.mipmap.news_two));
        newsList.add(new News(getString(R.string.news_three_title), getString(R.string.news_three_desc), R.mipmap.news_three));
        newsList.add(new News(getString(R.string.news_four_title), getString(R.string.news_four_desc), R.mipmap.news_four));
    }

    @Override
    public void renderNewsList(Collection<News> newsCollection) {

    }

    @Override
    public void viewNews(News news) {

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
        return this.getActivity();
    }
}
