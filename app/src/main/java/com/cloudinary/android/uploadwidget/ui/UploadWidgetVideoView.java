package com.cloudinary.android.uploadwidget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.VideoView;
import androidx.core.view.GestureDetectorCompat;

/* JADX INFO: loaded from: classes11.dex */
public class UploadWidgetVideoView extends VideoView {
    private GestureDetectorCompat gestureDetector;
    private VideoListener mListener;

    public interface VideoListener {
        void onPause();

        void onPlay();
    }

    public UploadWidgetVideoView(Context context) {
        super(context);
        init();
    }

    public UploadWidgetVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UploadWidgetVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        this.gestureDetector = new GestureDetectorCompat(getContext(), new GestureListener());
    }

    public void setListener(VideoListener listener) {
        this.mListener = listener;
    }

    @Override // android.widget.VideoView, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        this.gestureDetector.onTouchEvent(ev);
        return true;
    }

    @Override // android.widget.VideoView, android.widget.MediaController.MediaPlayerControl
    public void pause() {
        super.pause();
        if (this.mListener != null) {
            this.mListener.onPause();
        }
    }

    @Override // android.widget.VideoView, android.widget.MediaController.MediaPlayerControl
    public void start() {
        super.start();
        if (this.mListener != null) {
            this.mListener.onPlay();
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent e) {
            if (UploadWidgetVideoView.this.isPlaying()) {
                UploadWidgetVideoView.this.pause();
            } else {
                UploadWidgetVideoView.this.start();
            }
            return super.onSingleTapUp(e);
        }
    }
}
