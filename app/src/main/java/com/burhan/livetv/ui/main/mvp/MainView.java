package com.burhan.livetv.ui.main.mvp;

import android.view.View;

import com.burhan.livetv.model.Channel;

import java.util.List;

/**
 * Created by Burhan on 10/08/2017.
 */

public interface MainView extends MvpView{

    void onStartProgress();
    void onChannelsLoaded(List<Channel> channels);
    void onChannelSelected(Channel channel);
    void onHideLoading();
}
