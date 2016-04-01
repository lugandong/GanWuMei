package com.dimon.ganwumei.ui.newsfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.entities.Item;
import com.dimon.ganwumei.ui.newsfeed.activity.NewsActivity;
import com.dimon.ganwumei.util.ImageLoader;

import java.util.List;

/**
 * Created by Dimon on 2016/3/11.
 */
public class GanWuAdapter extends RecyclerView.Adapter<GanWuAdapter.NewsViewHolder>{

    private List<Item> newses;
    private ImageLoader mImageLoader;

    private Context mContext;

    public GanWuAdapter(List<Item> newses, Context context) {
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
        Button share;
        Button readMore;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            news_photo= (ImageView) itemView.findViewById(R.id.news_photo);
            news_title= (TextView) itemView.findViewById(R.id.news_title);
            news_desc= (TextView) itemView.findViewById(R.id.news_desc);
            share= (Button) itemView.findViewById(R.id.btn_share);
            readMore= (Button) itemView.findViewById(R.id.btn_more);
            //设置TextView背景为半透明
            news_title.setBackgroundColor(Color.argb(20, 0, 0, 0));

        }


    }
    @Override
    public GanWuAdapter.NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_ganhuo,viewGroup,false);
        NewsViewHolder nvh=new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(GanWuAdapter.NewsViewHolder personViewHolder, int i) {
        final int j=i;

        personViewHolder.news_photo.setImageResource(R.drawable.dog);
//        DownloadImgUtils.downloadImgByUrl(Images.imageThumbUrls[i], personViewHolder.news_photo);
//        //mImageLoader.loadImage(getItem(position), personViewHolder.news_photo, true);
//        personViewHolder.news_title.setText(newses.get(i).getTitle());
        personViewHolder.news_desc.setText(newses.get(i).description);

        //为btn_share btn_readMore cardView设置点击事件
        personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,NewsActivity.class);
                intent.putExtra("News",newses.get(j).description);
                mContext.startActivity(intent);
            }
        });

        personViewHolder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, newses.get(j).description);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(Intent.createChooser(intent, newses.get(j).getTitle()));
            }
        });

        personViewHolder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,NewsActivity.class);
                intent.putExtra("News",newses.get(j).description);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return newses.size();
    }
}