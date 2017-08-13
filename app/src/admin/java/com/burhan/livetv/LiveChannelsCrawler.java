package com.burhan.livetv;

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

    }
}
