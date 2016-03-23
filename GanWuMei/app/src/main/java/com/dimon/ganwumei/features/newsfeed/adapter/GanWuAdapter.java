package com.dimon.ganwumei.features.newsfeed.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.Image;
import com.dimon.ganwumei.database.entity.News;
import com.dimon.ganwumei.features.newsfeed.activity.NewsActivity;
import com.dimon.ganwumei.features.newsfeed.widget.ArrayRecyclerAdapter;
import com.dimon.ganwumei.features.newsfeed.widget.RatioImageView;
import com.dimon.ganwumei.service.ImageWrapper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dimon on 2016/3/11.
 */
public abstract class GanWuAdapter extends ArrayRecyclerAdapter<ImageWrapper, GanWuAdapter.GanWuViewHolder> {

    private List<News> newses;
    private final Context context;
    private final LayoutInflater inflater;

//    private ArrayList<Ganhuo> ganhuosItemData;


    public GanWuAdapter(List<News> newses,Context context) {
        this.newses = newses;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        setHasStableIds(true);
    }

    @Override
    public void onBindViewHolder(GanWuViewHolder holder, int position) {
        final int j=position;

        holder.imageView.setImageResource(newses.get(position).getPhotoId());
        holder.newsTitle.setText(newses.get(position).getTitle());
        holder.newsDesc.setText(newses.get(position).getDesc());

        //为btn_share btn_readMore cardView设置点击事件
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,NewsActivity.class);
                intent.putExtra("News",newses.get(j));
                context.startActivity(intent);
            }
        });


//        Ganhuo ganhuo = ganhuosItemData.get(position);
//        Image image = ImageGanWuCache.getIns().getImgGoodsRandom(position);
//        boolean hasImg = null != image;
//
//        holder.newsTitle.setText("#"+ganhuo.getDesc());
//        holder.newsDesc.setText("@"+ganhuo.getDesc());
//        loadImages(holder,image);
//

//        //为btn_share btn_readMore cardView设置点击事件
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, GanWuActivity.class);
//                intent.putExtra("Image",images.get(j));
//                context.startActivity(intent);
//            }
//        });
//
//        holder.btnShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//                intent.putExtra(Intent.EXTRA_TEXT, images.get(j).getDesc());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(Intent.createChooser(intent, images.get(j).getTitle()));
//            }
//        });
//
//        holder.btnMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context,NewsActivity.class);
//                intent.putExtra("News",images.get(j));
//                context.startActivity(intent);
//            }
//        });

    }

    private void loadImages(final GanWuViewHolder holder, Image image) {
        if(null == image || TextUtils.isEmpty(image.getUrl())){
            Glide.with(context)
                    .load(R.drawable.dog)
                    .centerCrop()
                    .into(holder.imageView);
        }else {
            Glide.with(context)
                    .load(image.getUrl())
                    .centerCrop()
                    .into(holder.imageView);
        }
    }


    @Override
    public GanWuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_ganhuo, parent, false);
        final GanWuViewHolder ganWuViewHolder = new GanWuViewHolder(view);
        return ganWuViewHolder;
    }

    @Override
    public long getItemId(int position) {
        return get(position).url.hashCode();
    }

    protected abstract void onItemClick(View v, int position);

    //自定义ViewHolder类
    public class GanWuViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.news_photo)
        public RatioImageView imageView;
        @Bind(R.id.news_title)
        TextView newsTitle;
        @Bind(R.id.news_header)
        RelativeLayout newsHeader;
        @Bind(R.id.news_desc)
        TextView newsDesc;
        @Bind(R.id.btn_share)
        Button btnShare;
        @Bind(R.id.btn_more)
        Button btnMore;
        @Bind(R.id.card_view)
        CardView cardView;



        public GanWuViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            //设置TextView背景为半透明
            newsTitle.setBackgroundColor(Color.argb(20, 0, 0, 0));

        }

    }

}
