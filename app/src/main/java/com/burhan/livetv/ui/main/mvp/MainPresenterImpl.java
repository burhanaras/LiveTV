package com.burhan.livetv.ui.main.mvp;


import android.util.Log;

import com.burhan.livetv.data.ChannelsService;
import com.burhan.livetv.model.Channel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Burhan on 10/08/2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private static final String TAG = MainPresenterImpl.class.getName();
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

        ChannelsService channelsService =  ChannelsService.retrofit.create(ChannelsService.class);
        Call<List<Channel>> call =  channelsService.retrieveAllChannels();
        call.enqueue(new Callback<List<Channel>>() {
            @Override
            public void onResponse(Call<List<Channel>> call, Response<List<Channel>> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                Log.d(TAG, "onResponse: "+response.body());
                getView().onHideLoading();
                getView().onChannelsLoaded(response.body());
            }

            @Override
            public void onFailure(Call<List<Channel>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                getView().onHideLoading();
            }
        });

    }
}
