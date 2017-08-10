package com.burhan.livetv.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.burhan.livetv.R;
import com.burhan.livetv.model.Channel;
import com.burhan.livetv.ui.main.mvp.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burhan on 10/08/2017.
 */

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelViewHolder> {
    private ArrayList<Channel> items;
    private MainView mainView;

    public ChannelsAdapter(ArrayList<Channel> channels, MainView mainView) {

        items = channels;
        this.mainView = mainView;
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_channel, parent,false);
        ChannelViewHolder viewHolder =  new ChannelViewHolder(view);
        viewHolder.setView(mainView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        if(items!= null && items.size() > position){
            holder.bind(items.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setData(List<Channel> channels) {
        if(channels != null){
            if(items== null) items=new ArrayList<>();
            items.clear();
            items.addAll(channels);
            notifyDataSetChanged();
        }
    }
}
