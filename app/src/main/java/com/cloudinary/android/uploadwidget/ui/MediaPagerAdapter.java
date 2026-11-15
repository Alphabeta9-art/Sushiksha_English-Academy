package com.cloudinary.android.uploadwidget.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.cloudinary.android.ui.R;
import com.cloudinary.android.uploadwidget.model.Media;
import com.cloudinary.android.uploadwidget.ui.imageview.UploadWidgetImageView;
import com.cloudinary.android.uploadwidget.utils.MediaType;
import com.cloudinary.android.uploadwidget.utils.UriUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes11.dex */
class MediaPagerAdapter extends PagerAdapter {
    private int currentPagePosition;
    private ArrayList<Media> mediaList;
    private SparseArray<View> views;

    public MediaPagerAdapter(ArrayList<Uri> uris, ViewPager mediaViewPager) {
        this.mediaList = new ArrayList<>(uris.size());
        this.views = new SparseArray<>(uris.size());
        for (Uri sourceUri : uris) {
            this.mediaList.add(new Media(sourceUri));
        }
        MediaPageChangedListener pageChangeListener = new MediaPageChangedListener();
        if (mediaViewPager != null) {
            mediaViewPager.addOnPageChangeListener(pageChangeListener);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        MediaType mediaType;
        Context context = viewGroup.getContext();
        Media media = this.mediaList.get(i);
        MediaType mediaType2 = MediaType.IMAGE;
        Uri resultUri = media.getResultUri();
        if (resultUri == null && (mediaType = UriUtils.getMediaType(context, (resultUri = media.getSourceUri()))) != null) {
            mediaType2 = mediaType;
        }
        View view = null;
        if (mediaType2 == MediaType.IMAGE) {
            UploadWidgetImageView uploadWidgetImageView = new UploadWidgetImageView(context);
            uploadWidgetImageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            uploadWidgetImageView.setImageUri(resultUri);
            view = uploadWidgetImageView;
            this.views.put(i, uploadWidgetImageView);
        } else if (mediaType2 == MediaType.VIDEO) {
            FrameLayout frameLayout = new FrameLayout(context);
            final UploadWidgetVideoView uploadWidgetVideoView = new UploadWidgetVideoView(context);
            final ImageView imageView = new ImageView(context);
            frameLayout.addView(uploadWidgetVideoView);
            frameLayout.addView(imageView);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams.gravity = 17;
            int dimension = (int) context.getResources().getDimension(R.dimen.video_play_button_overlay_size);
            layoutParams.height = dimension;
            layoutParams.width = dimension;
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.play_overlay);
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) uploadWidgetVideoView.getLayoutParams();
            layoutParams2.gravity = 17;
            uploadWidgetVideoView.setLayoutParams(layoutParams2);
            uploadWidgetVideoView.setVideoURI(resultUri);
            uploadWidgetVideoView.setListener(new UploadWidgetVideoView.VideoListener() { // from class: com.cloudinary.android.uploadwidget.ui.MediaPagerAdapter.1
                @Override // com.cloudinary.android.uploadwidget.ui.UploadWidgetVideoView.VideoListener
                public void onPlay() {
                    imageView.setVisibility(8);
                }

                @Override // com.cloudinary.android.uploadwidget.ui.UploadWidgetVideoView.VideoListener
                public void onPause() {
                    imageView.setVisibility(0);
                }
            });
            uploadWidgetVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.cloudinary.android.uploadwidget.ui.MediaPagerAdapter.2
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mp) {
                    uploadWidgetVideoView.seekTo(1);
                }
            });
            view = frameLayout;
            this.views.put(i, uploadWidgetVideoView);
        }
        viewGroup.addView(view);
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        this.views.remove(position);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object object) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mediaList.size();
    }

    public void updateMediaResult(Uri sourceUri, Uri resultUri) {
        for (Media media : this.mediaList) {
            if (media.getSourceUri().toString().equals(sourceUri.toString())) {
                media.setResultUri(resultUri);
                notifyDataSetChanged();
                return;
            }
        }
    }

    public void resetMediaResult(Uri sourceUri) {
        updateMediaResult(sourceUri, null);
    }

    public int getMediaPosition(Uri uri) {
        for (int i = 0; i < this.mediaList.size(); i++) {
            Media media = this.mediaList.get(i);
            if (media.getSourceUri().toString().equals(uri.toString())) {
                return i;
            }
        }
        return -1;
    }

    public class MediaPageChangedListener extends ViewPager.SimpleOnPageChangeListener {
        public MediaPageChangedListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int position) {
            View currentView = (View) MediaPagerAdapter.this.views.get(MediaPagerAdapter.this.currentPagePosition);
            if (currentView instanceof UploadWidgetVideoView) {
                UploadWidgetVideoView videoView = (UploadWidgetVideoView) currentView;
                videoView.pause();
            }
            MediaPagerAdapter.this.currentPagePosition = position;
        }
    }
}
