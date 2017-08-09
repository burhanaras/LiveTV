package com.burhan.livetv;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;

public class MainActivity extends AppCompatActivity implements EasyVideoCallback {


    private static final String TEST_URL = "http://yayin7.canlitvlive.io/startv/live.m3u8?tkn=3ssO2t0cI94_OAWNIGdYLw&tms=1502322811";
    private static final String TAG = MainActivity.class.getName();

    private BurhansTvPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grabs a reference to the player view
        player = (BurhansTvPlayer) findViewById(R.id.player);
        player.setCallback(this);
        player.setAutoPlay(true);
        // Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))
        player.setSource(Uri.parse(TEST_URL));


    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    public void onStarted(EasyVideoPlayer player) {
        Log.d(TAG, "onStarted() called with: player = [" + player + "]");
    }

    @Override
    public void onPaused(EasyVideoPlayer player) {
        Log.d(TAG, "onPaused() called with: player = [" + player + "]");
    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {
        Log.d(TAG, "onPreparing() called with: player = [" + player + "]");
    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {
        Log.d(TAG, "onPrepared() called with: player = [" + player + "]");
    }

    @Override
    public void onBuffering(int percent) {
        Log.d(TAG, "onBuffering() called with: percent = [" + percent + "]");
    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {
        Log.e(TAG, "onError: ",e );
    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
        Log.d(TAG, "onCompletion() called with: player = [" + player + "]");
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {
        Log.d(TAG, "onRetry() called with: player = [" + player + "], source = [" + source + "]");
    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {
        Log.d(TAG, "onSubmit() called with: player = [" + player + "], source = [" + source + "]");
    }

}
