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
import com.dimon.ganwumei.database.entities.Images;
import com.dimon.ganwumei.util.ImageLoader;

import java.util.List;

/**
 * Created by Dimon on 2016/3/11.
 */
public class GanWuAdapter extends RecyclerView.Adapter<GanWuAdapter.NewsViewHolder>{

    private List<Images> newses;
    private ImageLoader mImageLoader;

    private Context mContext;

    public GanWuAdapter(List<Images> newses, Context context) {
        this.newses = newses;
        this.mContext=context;
        mImageLoader = ImageLoader.getInstance(3, ImageLoader.Type.LIFO);
    }


    //自定义ViewHolder类
    static class NewsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView news_photo;
        TextView news_title;
        TextView news_desc;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            news_photo= (ImageView) itemView.findViewById(R.id.news_photo);
            news_title= (TextView) itemView.findViewById(R.id.news_title);
            news_desc= (TextView) itemView.findViewById(R.id.news_desc);

            //设置TextView背景为半透明
            news_title.setBackgroundColor(Color.argb(20, 0, 0, 0));
        }

    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_ganhuo,viewGroup,false);
        NewsViewHolder nvh=new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder personViewHolder, int i) {
        Glide.with(mContext)
                .load(newses.get(i).getUrl())
                .placeholder(R.drawable.dog) //设置占位图
                .crossFade() //设置淡入淡出效果，默认300ms，可以传参.crossFade() //设置淡入淡出效果，默认300ms，可以传参
                .into(personViewHolder.news_photo);
        personViewHolder.news_title.setText(newses.get(i).getDesc());
        personViewHolder.news_desc.setText(newses.get(i).getWho());

    }

    @Override
    public int getItemCount() {
        return newses.size();
    }
}