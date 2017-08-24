package com.burhan.livetv.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burhan on 24/08/2017.
 */

public class ChannelList {
    private List<Channel> channels=new ArrayList<>();

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
