package com.dimon.ganwumei.features.newsfeed;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dimon.ganwumei.R;
import com.dimon.ganwumei.features.newsfeed.widget.ArrayRecyclerAdapter;
import com.dimon.ganwumei.features.newsfeed.widget.RatioImageView;
import com.dimon.ganwumei.service.ImageWrapper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dimon on 2016/3/11.
 */
public abstract class GanhuoAdapter extends ArrayRecyclerAdapter<ImageWrapper, GanhuoAdapter.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;

    public GanhuoAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        setHasStableIds(true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(R.layout.item_ganhuo, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageWrapper image = get(position);

        holder.imageView.setOriginalSize(image.width, image.height);

        Glide.with(context)
                .load(image.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        ViewCompat.setTransitionName(holder.imageView, image.url);
    }

    @Override
    public long getItemId(int position) {
        return get(position).url.hashCode();
    }

    protected abstract void onItemClick(View v, int position);

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.picture)
        public RatioImageView imageView;

        public ViewHolder(@LayoutRes int resource, ViewGroup parent) {
            super(inflater.inflate(resource, parent, false));
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getAdapterPosition();
                }
            });
        }

    }

}
