package com.burhan.livetv.ui.main;

import android.net.Uri;
import android.util.Log;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.google.firebase.crash.FirebaseCrash;

/**
 * Created by Burhan on 11/08/2017.
 */

class BurhansLiveTvCallBack implements EasyVideoCallback {
    public static final String TAG = BurhansLiveTvCallBack.class.getName();
    
    @Override
    public void onStarted(EasyVideoPlayer player) {
        Log.d(TAG, "onStarted: ");
    }

    @Override
    public void onPaused(EasyVideoPlayer player) {
        Log.d(TAG, "onPaused: ");
    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {
        Log.d(TAG, "onPreparing: ");
    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {
        Log.d(TAG, "onPrepared: ");
    }

    @Override
    public void onBuffering(int percent) {
        Log.d(TAG, "onBuffering: ");
    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {
        Log.d(TAG, "onError: ");
        FirebaseCrash.log(e.getMessage());
    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
        Log.d(TAG, "onCompletion: ");
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {

    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {

    }
}
