package com.dimon.ganwumei.ui.newsfeed.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.dimon.ganwumei.widget.MultiSwipeRefreshLayout;
import com.socks.library.KLog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by Dimon on 2016/3/23.
 */
public class GanWuFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Nullable
    @Bind(R.id.swipe_refresh_layout)
    public MultiSwipeRefreshLayout mSwipeRefreshLayout;
//    @Inject
//    HttpMethods mHttpMethods;

    private View view;
    private LinearLayoutManager linearLayoutManager;
    private GanWuAdapter mAdapter;
    private boolean mIsRequestDataRefresh = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        trySetupSwipeRefresh();
        new Handler().postDelayed(() -> setRequestDataRefresh(true), 358);
        KLog.a(mRecyclerView);
        linearLayoutManager = new LinearLayoutManager(context());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        loadData(true);
    }
    private void loadData(boolean clean) {
        HttpMethods.getInstance().getGanWu(new Subscriber<List<Item>>() {
            @Override
            public void onCompleted() {
                setRequestDataRefresh(false);
            }

            @Override
            public void onError(Throwable e) {
                setRequestDataRefresh(false);
                KLog.e(e);
            }

            @Override
            public void onNext(List<Item> items) {
                mRecyclerView.setAdapter(mAdapter = new GanWuAdapter(items, context()));
            }
        });

    }

    void trySetupSwipeRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3,
                    R.color.refresh_progress_2, R.color.refresh_progress_1);
            // do not use lambda!!
            mSwipeRefreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            requestDataRefresh();
                        }
                    });
        }
    }

    public void requestDataRefresh() {
        mIsRequestDataRefresh = true;
    }

    public void setRequestDataRefresh(boolean requestDataRefresh) {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        if (!requestDataRefresh) {
            mIsRequestDataRefresh = false;
            // 防止刷新消失太快，让子弹飞一会儿.
            mSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
            }, 1000);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }


    public Context context() {
        return this.getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
