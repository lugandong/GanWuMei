package com.dimon.ganwumei.ui.newsfeed.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.entities.Item;
import com.dimon.ganwumei.util.ImageLoader;

import java.util.List;

/**
 *
 * Created by Dimon on 2016/4/20.
 */
public class AndroidListAdapter extends RecyclerView.Adapter<AndroidListAdapter.NewsViewHolder>{
    private List<Item> mItems;
    private ImageLoader mImageLoader;

    private Context mContext;

    public AndroidListAdapter(List<Item> items, Context context) {
        this.mItems = items;
        this.mContext = context;
        mImageLoader = ImageLoader.getInstance(3, ImageLoader.Type.LIFO);
    }


    //自定义ViewHolder类
    static class NewsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView news_photo;
        TextView news_date;
        TextView news_desc;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            news_photo= (ImageView) itemView.findViewById(R.id.news_photo);
            news_date= (TextView) itemView.findViewById(R.id.news_date);
            news_desc= (TextView) itemView.findViewById(R.id.news_desc);

            //设置TextView背景为半透明
            news_date.setBackgroundColor(Color.argb(20, 0, 0, 0));
        }

    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_list,viewGroup,false);
        NewsViewHolder nvh=new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder personViewHolder, int i) {
        Glide.with(mContext)
                .load(mItems.get(i).getImageurl())
                .placeholder(R.drawable.dog) //设置占位图
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参.crossFade() //设置淡入淡出效果，默认300ms，可以传参
                .into(personViewHolder.news_photo);
        personViewHolder.news_date.setText(mItems.get(i).getDate());
        personViewHolder.news_desc.setText(mItems.get(i).getDescription());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
