package com.dimon.ganwumei.ui.newsfeed.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.dimon.ganwumei.R;
import com.dimon.ganwumei.database.entities.Item;

/**
 * Created by Dimon on 2016/3/23.
 */
public class NewsActivity extends AppCompatActivity {
    private ImageView newsPhoto;
    private TextView newsTitle;
    private TextView newsDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsPhoto= (ImageView) findViewById(R.id.news_info_photo);
        newsTitle= (TextView) findViewById(R.id.news_info_title);
        newsDesc= (TextView) findViewById(R.id.news_info_desc);

        Intent intent=getIntent();

        Item item= (Item) intent.getSerializableExtra("News");
        //newsPhoto.setImageResource();
        newsTitle.setText(item.who);
        newsDesc.setText(item.description);

    }
}