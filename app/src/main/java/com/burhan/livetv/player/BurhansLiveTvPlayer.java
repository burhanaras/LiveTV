package com.burhan.livetv.player;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.afollestad.easyvideoplayer.EasyVideoPlayer;


/**
 * Created by Burhan on 10/08/2017.
 */

public class BurhansLiveTvPlayer extends EasyVideoPlayer {

    private static final String TAG = BurhansLiveTvPlayer.class.getName();

    public BurhansLiveTvPlayer(Context context) {
        super(context);
        init();
    }

    public BurhansLiveTvPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BurhansLiveTvPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    public void toggleControls() {
        super.toggleControls();
        Log.d(TAG, "toggleControls() called");
    }
}
