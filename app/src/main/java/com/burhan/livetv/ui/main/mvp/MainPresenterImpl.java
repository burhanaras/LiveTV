package com.burhan.livetv.ui.main.mvp;


import com.burhan.livetv.model.Channel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burhan on 10/08/2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView view;

    public MainView getView() {
        return view;
    }

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void loadChannels() {

        getView().onStartProgress();

        Channel channel = new Channel();
        List<Channel> response = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            channel = new Channel();
            channel.setName("Show TV");
            channel.setLogo("http://www.sermermedya.com/wp-content/uploads/2016/02/show-tv-logo-png.png");
            channel.setUrl("http://yayin7.canlitvlive.io/showtv/live.m3u8?tkn=4467iUawN5vvAK5ZAW2kjQ&tms=1502398087");
            response.add(channel);

        }


        getView().onChannelsLoaded(response);

        getView().onHideLoading();
    }
}
