package com.burhan.livetv.ui.main.mvp;

import com.burhan.livetv.model.Channel;

import java.util.List;

/**
 * Created by Burhan on 10/08/2017.
 */

public interface MainView {

    void onStartProgress();
    void onChannelsLoaded(List<Channel> channels);
    void onHideLoading();
}
