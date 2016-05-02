package com.dimon.ganwumei.ui.newsfeed.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.entities.Item;
import com.dimon.ganwumei.ui.UiHelper;

import java.util.List;

/**
 *
 * Created by Dimon on 2016/4/20.
 */
public class GanWuListAdapter extends RecyclerView.Adapter<GanWuListAdapter.NewsViewHolder>{
    private List<Item> mItems;
    private Context mContext;
    private boolean animateItems = false;
    private int lastAnimatedPosition = -1;
    public GanWuListAdapter(List<Item> items, Context context) {
        this.mItems = items;
        this.mContext = context;
    }

    //自定义ViewHolder类
    static class NewsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView news_who;
        TextView news_desc;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            news_who= (TextView) itemView.findViewById(R.id.news_who);
            news_desc= (TextView) itemView.findViewById(R.id.news_desc);
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
        runEnterAnimation(personViewHolder.itemView,i);
        personViewHolder.news_who.setText(mItems.get(i).getWho() + "：");
        personViewHolder.news_desc.setText(mItems.get(i).getDescription());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateItems(List<Item> items, boolean animated) {
        animateItems = animated;
        lastAnimatedPosition = -1;
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    private void runEnterAnimation(View view, int position) {
        if (!animateItems || position >= 5) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(UiHelper.getScreenHeight(mContext));
            view.animate()
                    .translationY(0)
                    .setStartDelay(100 * position)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }
}
