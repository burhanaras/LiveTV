package com.burhan.livetv;

import android.os.Handler;

import com.burhan.livetv.model.Channel;

/**
 * Created by Burhan on 14/08/2017.
 */

class LiveChannelsCrawler {
    private CrawlerListener listener;

    public interface CrawlerListener {
        void onCrawlingStarted();
        void onCrawlingProgress(Channel channel);
        void onCrawlingFinished();
    }

    public void setListener(CrawlerListener listener) {
        this.listener = listener;
    }

    public CrawlerListener getListener() {
        return listener;
    }

    public void start() {
        if(listener != null){
            listener.onCrawlingStarted();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listener.onCrawlingProgress(new Channel());
                    listener.onCrawlingFinished();
                }
            }, 2000);
        }
    }
}
