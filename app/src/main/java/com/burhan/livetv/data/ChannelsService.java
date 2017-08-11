package com.burhan.livetv.data;

import com.burhan.livetv.model.Channel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Burhan on 12/08/2017.
 */

public interface ChannelsService {

    @GET("m9c71")
    Call<List<Channel>> retrieveAllChannels();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.myjson.com/bins/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
