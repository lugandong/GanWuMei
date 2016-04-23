package com.dimon.ganwumei.ui.newsfeed.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.DataManager;
import com.dimon.ganwumei.database.entities.Image;
import com.dimon.ganwumei.database.entities.Meizhi;
import com.dimon.ganwumei.func.OnMeizhiTouchListener;
import com.dimon.ganwumei.injector.components.DaggerGanWuComponent;
import com.dimon.ganwumei.injector.modules.GanWuModule;
import com.dimon.ganwumei.ui.base.BaseFragment;
import com.dimon.ganwumei.ui.newsfeed.activity.MainActivity;
import com.dimon.ganwumei.ui.newsfeed.activity.PictureActivity;
import com.dimon.ganwumei.ui.newsfeed.adapter.GanWuAdapter;
import com.dimon.ganwumei.util.ImageToMeizhiMapper;
import com.dimon.ganwumei.util.ToastUtils;
import com.dimon.ganwumei.widget.MultiSwipeRefreshLayout;
import com.socks.library.KLog;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * Created by Dimon on 2016/3/23.
 */
public class GanWuFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Nullable
    @Bind(R.id.swipe_refresh_layout)
    public MultiSwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mIsFirstTimeTouchBottom = true;
    private static final int PRELOAD_SIZE = 10;
    private Realm mRealm;
    private static final String FRAGMENT_INDEX = "fragment_index";
    private int mGanWuIndex = -1;
    private int mPage = 1;
    private List<Meizhi> mMeizhisList;
    private boolean mMeizhiBeTouched;
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
    private GanWuAdapter mGanWuAdapter;
    private boolean mIsRequestDataRefresh = false;

    @Inject
    DataManager mDataManager;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInject();
    }

    private void initInject() {
        MainActivity activity = (MainActivity)getActivity();
        DaggerGanWuComponent.builder()
                .ganWuModule(new GanWuModule())
                .activityComponent(activity.getComponent())
                .build()
                .inject(this);
    }



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
        mMeizhisList = new ArrayList<>();
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
        loadData(false);
        KLog.a(mRecyclerView);
        linearLayoutManager = new LinearLayoutManager(context());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mGanWuAdapter = new GanWuAdapter(mMeizhisList, context());
        mRecyclerView.setAdapter(mGanWuAdapter);

        mRecyclerView.addOnScrollListener(getOnBottomListener(linearLayoutManager));
        mGanWuAdapter.setOnMeizhiTouchListener(getOnMeizhiTouchListener());

    }

    private void loadData(boolean clean) {
        subscription = mRealm
                .where(Image.class)
                .isNotNull("desc")
                .findAllSortedAsync("publishedAt")
                .asObservable()
                .filter(image -> image.isLoaded())
                .flatMap(image1 ->
                        mDataManager.getImageData(mPage)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread()))
                .map(ImageToMeizhiMapper.getInstance())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(images3 -> {
                    if (clean) mMeizhisList.clear();
                    mMeizhisList.addAll(images3);
                    mGanWuAdapter.notifyDataSetChanged();
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

    private OnMeizhiTouchListener getOnMeizhiTouchListener() {
        return (v, meizhiView, card, meizhi) -> {
            if (meizhi == null) return;
            if (v == meizhiView && !mMeizhiBeTouched) {
                mMeizhiBeTouched = true;
                Picasso.with(context()).load(meizhi.getUrl()).fetch(new Callback() {

                    @Override public void onSuccess() {
                        mMeizhiBeTouched = false;
                        startPictureActivity(meizhi, meizhiView);
                    }
                    @Override public void onError() {mMeizhiBeTouched = false;}
                });
            }
            else if (v == card) {
//                startGankActivity(meizhi.getDate());
                ToastUtils.ToastMessage(context(),"还没做呢...");
                KLog.a("TODO...");
            }
        };
    }
//    private void startGankActivity(Date publishedAt) {
//        Intent intent = new Intent(this, GankActivity.class);
//        intent.putExtra(GankActivity.EXTRA_GANK_DATE, publishedAt);
//        startActivity(intent);
//    }


    private void startPictureActivity(Meizhi meizhi, View transitView) {
        Intent intent = PictureActivity.newIntent(context(), meizhi.getUrl(),
                meizhi.getDesc());
        ActivityOptionsCompat optionsCompat
                = ActivityOptionsCompat.makeSceneTransitionAnimation(
                (Activity) context(), transitView, PictureActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity((Activity) context(), intent,
                    optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            startActivity(intent);
        }
    }

    RecyclerView.OnScrollListener getOnBottomListener(LinearLayoutManager layoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override public void onScrolled(RecyclerView rv, int dx, int dy) {
                boolean isBottom =
                        layoutManager.findLastCompletelyVisibleItemPosition() >=
                                mGanWuAdapter.getItemCount() - PRELOAD_SIZE;
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom) {
                    if (!mIsFirstTimeTouchBottom) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        mPage += 1;
                        loadData(false);
                    }
                    else {
                        mIsFirstTimeTouchBottom = false;
                    }
                }
            }
        };
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
        loadData(false);
    }
}
