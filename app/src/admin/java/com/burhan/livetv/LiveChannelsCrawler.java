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

        void onCrawlingFailed(List<Channel> channels);
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

        private String[] names = {"TRT 1", "ATV", "SHOW TV", "STAR TV", "TV 8", "FOX TV", "Kanal 7", "CNN Turk", "A Haber",
        "Kanal D","NTV","A2","NTV Spor","Haber Türk","Kanal 24","TRT 3","Flash TV","TeVe2","TvNet","360","TRT Haber","TGRT"};
        private String[] images = {"http://www.hdcanlitvizler.com/wp-content/uploads/2016/10/trt-1-logo-png-300x214.png",
                "http://www.globya.com.tr/wp-content/uploads/2016/03/clients-06.png",
                "http://www.sermermedya.com/wp-content/uploads/2016/02/show-tv-logo-png.png",
                "http://www.turkcespiker.com/files/imagecache/full_gorunum/dosya_resimler/Denizlili2O/Star_TV_logosu.png",
                "https://img.webme.com/pic/c/canlitvlerizlee/tV8.png",
                "http://www.boliviaenmovimiento.net/wp-content/uploads/2017/07/Glamorous-Fox-Tv-Logo-Png-75-About-Remodel-Logos-with-Fox-Tv-Logo-Png.jpg",
                "http://1.bp.blogspot.com/-KJ7KNCWfeq4/UDjAp3PbMxI/AAAAAAAACNc/v5QEhB0TNDU/s1600/Kanal7.png",
                "http://www.canlitvizleyin.com/kanallar/cnn-turk.png",
                "http://i.ahaber.com.tr/site/v2/i/ahaber-facebook-logo.png",
                "https://s.kanald.com.tr/ps/kanald_proxy/assets/img/kanal-d.png",
                "http://canlitvx.com/wp-content/uploads/NTV-Canli-Yayin-izle-150x120.png",
                "https://3.bp.blogspot.com/-YYuNOv5qv30/WNp6G000BoI/AAAAAAAAATE/TJwHXr38JGI-8aqYzle23UM0Lu4dVPC8ACLcB/s320/a2-tv.png",
                "http://www.ecanlitvizle.net/logo/ntv-spor-hd-izle.png",
                "http://canlitvx.com/wp-content/uploads/haberturk-compressor.png",
                "https://www.turkcebilgi.com/uploads/baslik/thumb/2332638.png",
                "http://3.bp.blogspot.com/--Bqck2C_elU/Ubw_PmpmoOI/AAAAAAAASDQ/nu5jXYTwWFU/s320/trtspor-logo.png",
                "http://mobiletv.mobibase.com/html/logo/hd/channel_ld_627.png",
                "https://www.kablonet.net/upload/dosyalar/136.jpg",
                "http://azonceoldu.com/resimler/images/tvnet.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/c/c3/360_tv.png",
                "http://www.wowmedyatv.com/wp-content/uploads/2016/02/trthaber-350x327.png",
                "http://a5.mzstatic.com/us/r30/Purple4/v4/1a/37/5f/1a375f08-578c-1775-31a4-a3547ecbb2da/icon175x175.png"
        };
        private String[] urls = {"http://www.canlitv.com/trt-1",
                "http://www.canlitv.com/canli-atv",
                "http://www.canlitv.com/show-tv",
                "http://www.canlitvlive.io/izle/star-tv-izle.html",
                "http://www.canlitv.com/tv-8",
                "http://www.canlitv.com/fox-tv",
                "http://www.canlitv.com/kanal-7",
                "http://www.canlitvlive.io/izle/cnn-turk.html",
                "http://www.canlitv.com/canli-a-haber",
        "http://www.canlitvlive.io/izle/kanal-d-hd-izle.html",
        "http://www.canlitvlive.io/izle/ntv-izle.html",
        "http://www.canlitvlive.io/izle/a2-tv.html",
        "http://www.canlitvlive.io/izle/ntv-spor-izle.html",
        "http://www.canlitvlive.io/izle/haber-turk-canli-yayin.html",
        "http://www.canlitvlive.io/izle/kanal-24.html",
        "http://www.canlitvlive.io/izle/trt-3-spor.html",
        "http://www.canlitvlive.io/izle/flash-tv.html",
        "http://www.canlitvlive.io/izle/teve2.html",
        "http://www.canlitvlive.io/izle/tv-net.html",
        "http://www.canlitvlive.io/izle/sky-turk.html",
        "http://www.canlitvlive.io/izle/trt-haber.html",
        "http://www.canlitvlive.io/izle/tgrt-haber.html"};

        private List<Channel> channels = new ArrayList<>();
        private List<Channel> failedChannels = new ArrayList<>();

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
                    } else {
                        failedChannels.add(channel);
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
            listener.onCrawlingFailed(failedChannels);

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
