package com.burhan.livetv;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.burhan.livetv.R;
import com.burhan.livetv.model.Channel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminActivity extends AppCompatActivity implements LiveChannelsCrawler.CrawlerListener {

    private static final String TAG = AdminActivity.class.getName();
    @BindView(R.id.tv_logs)
    TextView tvLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);
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
        tvLogs.setText("Click the FAB to get started.");

    }

    private void startCrawling() {
        LiveChannelsCrawler crawler = new LiveChannelsCrawler();
        crawler.setListener(this);
        crawler.start();
    }

    @Override
    public void onCrawlingStarted() {
        Log.d(TAG, "onCrawlingStarted() called");
        tvLogs.append("\n-> onCrawlingStarted() called");
    }

    @Override
    public void onCrawlingProgress(Channel channel) {
        Log.d(TAG, "onCrawlingProgress() called with: channel = [" + channel + "]");
        tvLogs.append("\n-> "+ channel.toString());
    }

    @Override
    public void onCrawlingFinished(List<Channel> channels) {
        Log.d(TAG, "onCrawlingFinished() called");
        tvLogs.append("\n-> onCrawlingFinished("+channels.size()+" channels ) called");
    }
}
