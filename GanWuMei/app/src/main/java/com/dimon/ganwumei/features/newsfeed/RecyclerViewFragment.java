package com.dimon.ganwumei.features.newsfeed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.view.MaterialListView;
import com.dimon.ganwumei.R;
import com.dimon.ganwumei.internal.components.GanWuComponent;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.socks.library.KLog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dagger.Component;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by Dimon on 2016/3/14.
 */
public class RecyclerViewFragment extends Fragment {

    @Bind(R.id.material_listview)
    MaterialListView mRecyclerView;
    @Bind(R.id.imageView)
    ImageView emptyView ;


    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Bind the MaterialListView to a variable

        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(300);
        mRecyclerView.getItemAnimator().setRemoveDuration(300);

        emptyView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        KLog.a(mainActivity);
        mRecyclerView.setEmptyView(emptyView);
        Picasso.with(mainActivity)
                .load("https://www.skyverge.com/wp-content/uploads/2012/05/github-logo.png")
                .resize(100, 100)
                .centerInside()
                .into(emptyView);
        // Fill the array withProvider mock content
        fillArray();
        
        MaterialViewPagerHelper.registerRecyclerView(mainActivity, mRecyclerView, null);
    }



    private void fillArray() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            cards.add(getRandomCard());
        }
        mRecyclerView.getAdapter().addAll(cards);
    }

    private Card getRandomCard() {
        Card card = new Card.Builder(mainActivity)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Card!!!")
                .setSubtitle("Lorem ipsum dolor sit amet")
                .setDrawable(R.drawable.photo)
                .endConfig()
                .build();
        return card;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

