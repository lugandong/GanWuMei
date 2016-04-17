package com.dimon.ganwumei.ui.newsfeed.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.entities.Images;
import com.dimon.ganwumei.network.RestAPI;
import com.dimon.ganwumei.ui.base.BaseFragment;
import com.dimon.ganwumei.ui.newsfeed.adapter.GanWuAdapter;
import com.dimon.ganwumei.widget.MultiSwipeRefreshLayout;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Dimon on 2016/3/23.
 */
public class GanWuFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Nullable
    @Bind(R.id.swipe_refresh_layout)
    public MultiSwipeRefreshLayout mSwipeRefreshLayout;

    private Realm mRealm;
    private static final String FRAGMENT_INDEX = "fragment_index";
    private int mGanWuIndex = -1;
    private int mPage = 1;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;
    private View view;
    private LinearLayoutManager linearLayoutManager;
    protected Subscription subscription;
    private static RestAPI restAPI;
    private GanWuAdapter mAdapter;
    private boolean mIsRequestDataRefresh = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
            //获取索引值
            Bundle bundle = getArguments();
            if (bundle != null) {
                mGanWuIndex = bundle.getInt(FRAGMENT_INDEX);
            }
        }
        ButterKnife.bind(this, view);
        mRealm = Realm.getDefaultInstance();
        isPrepared = true;
        lazyLoad();
        //因为共用一个Fragment视图，所以当前这个视图已被加载到Activity中，必须先清除后再加入Activity
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
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

    }

    private void loadData(boolean clean) {
//        HttpMethods.getInstance().getImage(new Subscriber<List<Images>>() {
//            @Override
//            public void onCompleted() {
//                if (mSwipeRefreshLayout != null) {
//                    mSwipeRefreshLayout.setRefreshing(false);
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                assert mSwipeRefreshLayout != null;
//                mSwipeRefreshLayout.setRefreshing(false);
//                KLog.e(e);
//            }
//
//            @Override
//            public void onNext(List<Images> images) {
//                mRecyclerView.setAdapter(mAdapter = new GanWuAdapter(images, context()));
//            }
//        });
        subscription = mRealm
                .where(Images.class)
                .findAllSortedAsync("desc")
                .asObservable()
                .filter(images -> images.isLoaded())
                .flatMap(Observable::from)
                .flatMap(images1 -> restAPI.getImageData(mPage))
                .map(imageData -> imageData.results)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(images3 -> {
                    if (clean) images3.clear();
                    mRecyclerView.setAdapter(mAdapter = new GanWuAdapter(images3, context()));
                    setRequestDataRefresh(false);
                }, throwable -> loadError(throwable));
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Snackbar.make(mRecyclerView, R.string.snap_load_fail,
                Snackbar.LENGTH_LONG).setAction(R.string.retry, v -> {
            requestDataRefresh();
        }).show();
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
        mPage = 1;
        loadData(true);
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
        mRealm.close();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        loadData(true);
        mHasLoadedOnce = true;
    }
}
