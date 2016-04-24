package com.dimon.ganwumei.ui.newsfeed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.entities.Meizhi;
import com.dimon.ganwumei.func.OnMeizhiTouchListener;
import com.dimon.ganwumei.util.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dimon on 2016/3/11.
 */
public class GanWuAdapter extends RecyclerView.Adapter<GanWuAdapter.NewsViewHolder> {


    private List<Meizhi> mMeizhis;
    private ImageLoader mImageLoader;
    private OnMeizhiTouchListener mOnMeizhiTouchListener;
    private Context mContext;

    public GanWuAdapter(List<Meizhi> mMeizhis, Context context) {
        this.mMeizhis = mMeizhis;
        this.mContext = context;
        mImageLoader = ImageLoader.getInstance(3, ImageLoader.Type.LIFO);
    }


    //自定义ViewHolder类
    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.news_photo)
        ImageView mNewsPhoto;
        @Bind(R.id.news_title)
        TextView mNewsTitle;
        View card;
        Meizhi meizhi;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            card = itemView;
            ButterKnife.bind(this, itemView);
            mNewsPhoto.setOnClickListener(this);
            card.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnMeizhiTouchListener.onTouch(v, mNewsPhoto, card, meizhi);
        }
    }
    public void setOnMeizhiTouchListener(OnMeizhiTouchListener onMeizhiTouchListener) {
        this.mOnMeizhiTouchListener = onMeizhiTouchListener;
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_ganhuo, viewGroup, false);
        NewsViewHolder nvh = new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder viewHolder, int i) {
        Meizhi meizhi = mMeizhis.get(i);
        viewHolder.meizhi = meizhi;
        Glide.with(mContext)
                .load(meizhi.getUrl())
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参.crossFade() //设置淡入淡出效果，默认300ms，可以传参
                .into(viewHolder.mNewsPhoto);
        viewHolder.mNewsTitle.setText(meizhi.getDesc());
    }

    @Override
    public int getItemCount() {
        return mMeizhis.size();
    }
}