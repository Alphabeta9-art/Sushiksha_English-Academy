package com.cloudinary.android.uploadwidget.ui;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.cloudinary.android.ui.R;
import com.cloudinary.android.uploadwidget.UploadWidget;
import com.cloudinary.android.uploadwidget.model.BitmapManager;
import com.cloudinary.android.uploadwidget.model.CropRotateResult;
import com.cloudinary.android.uploadwidget.utils.MediaType;
import com.cloudinary.android.uploadwidget.utils.UriUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes11.dex */
public class UploadWidgetFragment extends Fragment implements CropRotateFragment.Callback {
    private static final String IMAGES_URIS_LIST_ARG = "images_uris_list_arg";
    private MediaPagerAdapter mediaPagerAdapter;
    private ViewPager mediaViewPager;
    private ThumbnailsAdapter thumbnailsAdapter;
    private RecyclerView thumbnailsRecyclerView;
    private Map<Uri, UploadWidget.Result> uriResults;
    private ArrayList<Uri> uris;

    public interface UploadWidgetListener {
        void onConfirm(ArrayList<UploadWidget.Result> arrayList);
    }

    public static UploadWidgetFragment newInstance(ArrayList<Uri> imagesUris) {
        UploadWidgetFragment fragment = new UploadWidgetFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(IMAGES_URIS_LIST_ARG, imagesUris);
        fragment.setArguments(args);
        return fragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.uris = arguments.getParcelableArrayList(IMAGES_URIS_LIST_ARG);
        }
        this.uriResults = new HashMap(this.uris.size());
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_widget, container, false);
        this.mediaViewPager = (ViewPager) view.findViewById(R.id.imagesViewPager);
        this.mediaPagerAdapter = new MediaPagerAdapter(this.uris, this.mediaViewPager);
        this.mediaViewPager.setAdapter(this.mediaPagerAdapter);
        this.mediaViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.cloudinary.android.uploadwidget.ui.UploadWidgetFragment.1
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                UploadWidgetFragment.this.thumbnailsAdapter.setSelectedThumbnail(position);
                UploadWidgetFragment.this.thumbnailsRecyclerView.scrollToPosition(position);
                super.onPageSelected(position);
            }
        });
        FloatingActionButton uploadFab = (FloatingActionButton) view.findViewById(R.id.uploadFab);
        uploadFab.setOnClickListener(new View.OnClickListener() { // from class: com.cloudinary.android.uploadwidget.ui.UploadWidgetFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (UploadWidgetFragment.this.getActivity() instanceof UploadWidgetListener) {
                    ((UploadWidgetListener) UploadWidgetFragment.this.getActivity()).onConfirm(UploadWidgetFragment.this.getResults());
                }
            }
        });
        this.thumbnailsRecyclerView = (RecyclerView) view.findViewById(R.id.thumbnailsRecyclerView);
        if (this.uris.size() > 1) {
            this.thumbnailsAdapter = new ThumbnailsAdapter(this.uris, new ThumbnailsAdapter.Callback() { // from class: com.cloudinary.android.uploadwidget.ui.UploadWidgetFragment.3
                @Override // com.cloudinary.android.uploadwidget.ui.ThumbnailsAdapter.Callback
                public void onThumbnailClicked(Uri uri) {
                    UploadWidgetFragment.this.mediaViewPager.setCurrentItem(UploadWidgetFragment.this.mediaPagerAdapter.getMediaPosition(uri), true);
                }
            });
            this.thumbnailsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            this.thumbnailsRecyclerView.setAdapter(this.thumbnailsAdapter);
        } else {
            this.thumbnailsRecyclerView.setVisibility(4);
        }
        setToolbarIfNeeded();
        return view;
    }

    private void setToolbarIfNeeded() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            activity.setSupportActionBar(toolbar);
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.upload_widget_menu, menu);
        MenuItem cropItem = menu.findItem(R.id.crop_action);
        View cropActionView = cropItem.getActionView();
        cropActionView.setOnClickListener(new View.OnClickListener() { // from class: com.cloudinary.android.uploadwidget.ui.UploadWidgetFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Uri uri = (Uri) UploadWidgetFragment.this.uris.get(UploadWidgetFragment.this.mediaViewPager.getCurrentItem());
                CropRotateFragment cropRotateFragment = CropRotateFragment.newInstance(uri, UploadWidgetFragment.this);
                FragmentActivity activity = UploadWidgetFragment.this.getActivity();
                if (activity != null) {
                    activity.getSupportFragmentManager().beginTransaction().replace(android.R.id.content, cropRotateFragment, (String) null).addToBackStack(null).commit();
                }
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override // com.cloudinary.android.uploadwidget.ui.CropRotateFragment.Callback
    public void onCropRotateFinish(final Uri uri, CropRotateResult result, Bitmap resultBitmap) {
        UploadWidget.Result uwResult = this.uriResults.get(uri);
        if (uwResult == null) {
            uwResult = new UploadWidget.Result(uri);
        }
        uwResult.rotationAngle = result.getRotationAngle();
        uwResult.cropPoints = result.getCropPoints();
        this.uriResults.put(uri, uwResult);
        MediaType mediaType = UriUtils.getMediaType(getContext(), uri);
        if (mediaType == MediaType.IMAGE) {
            BitmapManager.get().save(getContext(), resultBitmap, new BitmapManager.SaveCallback() { // from class: com.cloudinary.android.uploadwidget.ui.UploadWidgetFragment.5
                @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.SaveCallback
                public void onSuccess(Uri resultUri) {
                    UploadWidgetFragment.this.mediaPagerAdapter.updateMediaResult(uri, resultUri);
                }

                @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.SaveCallback
                public void onFailure() {
                }
            });
        }
    }

    @Override // com.cloudinary.android.uploadwidget.ui.CropRotateFragment.Callback
    public void onCropRotateCancel(Uri uri) {
        UploadWidget.Result result = this.uriResults.get(uri);
        if (result != null) {
            result.rotationAngle = 0;
            result.cropPoints = null;
        }
        this.mediaPagerAdapter.resetMediaResult(uri);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentActivity activity;
        if (item.getItemId() == 16908332 && (activity = getActivity()) != null) {
            activity.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<UploadWidget.Result> getResults() {
        for (Uri uri : this.uris) {
            if (!this.uriResults.containsKey(uri)) {
                this.uriResults.put(uri, new UploadWidget.Result(uri));
            }
        }
        ArrayList<UploadWidget.Result> results = new ArrayList<>(this.uriResults.size());
        results.addAll(this.uriResults.values());
        return results;
    }
}
