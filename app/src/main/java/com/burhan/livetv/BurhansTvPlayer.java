package com.burhan.livetv;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.afollestad.easyvideoplayer.EasyVideoPlayer;


/**
 * Created by Burhan on 10/08/2017.
 */

public class BurhansTvPlayer extends EasyVideoPlayer {

    private static final String TAG = BurhansTvPlayer.class.getName();

    public BurhansTvPlayer(Context context) {
        super(context);
        init();
    }

    public BurhansTvPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BurhansTvPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
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
