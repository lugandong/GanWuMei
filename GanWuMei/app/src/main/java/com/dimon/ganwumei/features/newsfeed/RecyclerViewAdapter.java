package com.dimon.ganwumei.features.newsfeed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimon.ganwumei.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Dimon on 2016/3/14.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String[] mTitles;

    public RecyclerViewAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_ganhuo, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mTextView.setText(mTitles[position]);
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view)
        TextView mTextView;
        @Bind(R.id.image)
        ImageView picture;

        ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ImageViewHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }


}
