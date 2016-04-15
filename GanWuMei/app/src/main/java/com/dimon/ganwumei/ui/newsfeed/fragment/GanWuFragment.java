package com.dimon.ganwumei.ui.newsfeed.fragment;

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
import com.dimon.ganwumei.database.entities.Item;
import com.dimon.ganwumei.network.HttpMethods;
import com.dimon.ganwumei.ui.newsfeed.adapter.GanWuAdapter;
import com.dimon.ganwumei.widget.subscribers.ProgressSubscriber;
import com.dimon.ganwumei.widget.subscribers.SubscriberOnNextListener;
import com.socks.library.KLog;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Dimon on 2016/3/23.
 */
public class GanWuFragment extends Fragment  {

    private View view;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GanWuAdapter mAdapter;

    @Inject HttpMethods mHttpMethods;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KLog.a("onNext里面");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        KLog.a(mRecyclerView);
        linearLayoutManager = new LinearLayoutManager(context());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter = new GanWuAdapter(subjects, context()));
    }



    public Context context() {
        return this.getActivity();
    }
}
