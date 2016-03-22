package com.dimon.ganwumei.features.newsfeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimon.ganwumei.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dimon on 2016/3/14.
 */
public class RecyclerViewFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Inject MainActivity mainActivity;

    private static final int ITEM_COUNT = 100;


    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mainActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(new RecyclerViewAdapter(mainActivity));

//        {
//            for (int i = 0; i < ITEM_COUNT; ++i)
//                mContentItems.add(new Object());
        //mAdapter.notifyDataSetChanged();
//        }

        MaterialViewPagerHelper.registerRecyclerView(mainActivity, mRecyclerView, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

