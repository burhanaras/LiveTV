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
import com.burhan.livetv.model.ChannelList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

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
        tvLogs.setText("\n-> onCrawlingStarted() called");
    }

    @Override
    public void onCrawlingProgress(Channel channel) {
        Log.d(TAG, "onCrawlingProgress() called with: channel = [" + channel + "]");
        tvLogs.setText(tvLogs.getText() + "\n-> " + channel.toString());
    }

    @Override
    public void onCrawlingFinished(List<Channel> channels) {
        Log.d(TAG, "onCrawlingFinished() called");
        tvLogs.setText(tvLogs.getText() + "\n-> onCrawlingFinished(" + channels.size() + " channels ) called");
        ChannelList channelList = new ChannelList();
        channelList.setChannels(channels);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference channelsRef = database.getReference("channeldata");
        channelsRef.setValue(channelList);
        Log.d(TAG, "onCrawlingFinished: Firebase real time db has been updated.");

    }
}
