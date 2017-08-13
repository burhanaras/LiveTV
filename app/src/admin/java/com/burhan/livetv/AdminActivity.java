package com.burhan.livetv;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.burhan.livetv.R;
import com.burhan.livetv.model.Channel;

public class AdminActivity extends AppCompatActivity implements LiveChannelsCrawler.CrawlerListener {

    private static final String TAG = AdminActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Crawling started...", Snackbar.LENGTH_LONG)
                        .setAction("OK", null).show();
                startCrawling();
            }
        });
    }

    private void startCrawling() {
        LiveChannelsCrawler crawler = new LiveChannelsCrawler();
        crawler.setListener(this);
        crawler.start();
    }

    @Override
    public void onCrawlingStarted() {
        Log.d(TAG, "onCrawlingStarted() called");
    }

    @Override
    public void onCrawlingProgress(Channel channel) {
        Log.d(TAG, "onCrawlingProgress() called with: channel = [" + channel + "]");
    }

    @Override
    public void onCrawlingFinished() {
        Log.d(TAG, "onCrawlingFinished() called");
    }
}
