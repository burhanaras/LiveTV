package com.burhan.livetv;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.burhan.livetv.model.Channel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burhan on 14/08/2017.
 */

class LiveChannelsCrawler {
    private static final String TAG = LiveChannelsCrawler.class.getName();
    private CrawlerListener listener;

    public interface CrawlerListener {
        void onCrawlingStarted();

        void onCrawlingProgress(Channel channel);

        void onCrawlingFinished(List<Channel> channels);
    }

    public void setListener(CrawlerListener listener) {
        this.listener = listener;
    }

    public CrawlerListener getListener() {
        return listener;
    }

    public void start() {
        if (listener != null) {
            listener.onCrawlingStarted();
            new HTMLCrawlerTask().execute();
        }
    }

    class HTMLCrawlerTask extends AsyncTask<Void, Void, String> {

        private String[] names = {"TRT 1", "ATV", "SHOW TV", "STAR TV", "TV 8", "FOX TV", "Kanal 7", "CNN Turk", "A Haber"};
        private String[] images = {"http://www.hdcanlitvizler.com/wp-content/uploads/2016/10/trt-1-logo-png-300x214.png",
                "http://www.globya.com.tr/wp-content/uploads/2016/03/clients-06.png",
                "http://www.sermermedya.com/wp-content/uploads/2016/02/show-tv-logo-png.png",
                "http://www.turkcespiker.com/files/imagecache/full_gorunum/dosya_resimler/Denizlili2O/Star_TV_logosu.png",
                "https://img.webme.com/pic/c/canlitvlerizlee/tV8.png",
                "http://www.boliviaenmovimiento.net/wp-content/uploads/2017/07/Glamorous-Fox-Tv-Logo-Png-75-About-Remodel-Logos-with-Fox-Tv-Logo-Png.jpg",
                "http://1.bp.blogspot.com/-KJ7KNCWfeq4/UDjAp3PbMxI/AAAAAAAACNc/v5QEhB0TNDU/s1600/Kanal7.png",
                "http://www.canlitvizleyin.com/kanallar/cnn-turk.png",
                "http://i.ahaber.com.tr/site/v2/i/ahaber-facebook-logo.png"
        };
        private String[] urls = {"http://www.canlitv.com/trt-1",
                "http://www.canlitv.com/canli-atv",
                "http://www.canlitv.com/show-tv",
                "http://www.startv.com.tr/canli-yayin",
                "http://www.canlitv.com/tv-8",
                "http://www.canlitv.com/fox-tv",
                "http://www.canlitv.com/kanal-7",
                "https://www.cnnturk.com/canli-yayin",
                "http://www.canlitv.com/canli-a-haber"};

        private List<Channel> channels = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                for (int i = 0; i < names.length; i++) {
                    Channel channel = new Channel();
                    channel.setName(names[i]);
                    channel.setLogo(images[i]);
                    channel.setUrl(fetch(urls[i]));
                    if (channel.getUrl() != null) {
                        channels.add(channel);
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String url) {
            super.onPostExecute(url);
            Log.d(TAG, "onPostExecute: " + url);
            for (Channel channel : channels) {
                listener.onCrawlingProgress(channel);
            }
            listener.onCrawlingFinished(channels);

        }

        @Nullable
        private String fetch(String url) throws IOException {
            // Build and set timeout values for the request.
            URLConnection connection = (new URL(url)).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            // Read and store the result line by line then return the entire string.
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                sb.append(line);
            }
            in.close();

            String html = sb.toString();
            int indexM3U8 = html.indexOf("m3u8");
            if (indexM3U8 == -1) return null;
            int start = html.substring(0, indexM3U8).lastIndexOf("\"") + 1;
            int end = html.substring(indexM3U8).indexOf("\"");
            end = indexM3U8 + end;
            return html.substring(start, end);
        }


    }
}
