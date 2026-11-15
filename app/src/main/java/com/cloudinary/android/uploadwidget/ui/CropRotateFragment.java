package com.cloudinary.android.uploadwidget.ui;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.cloudinary.android.ui.R;
import com.cloudinary.android.uploadwidget.model.BitmapManager;
import com.cloudinary.android.uploadwidget.model.CropRotateResult;
import com.cloudinary.android.uploadwidget.ui.imageview.UploadWidgetImageView;
import com.cloudinary.android.uploadwidget.utils.MediaType;
import com.cloudinary.android.uploadwidget.utils.UriUtils;

/* JADX INFO: loaded from: classes11.dex */
public class CropRotateFragment extends Fragment {
    private static final String URI_ARG = "uri_arg";
    private Callback callback;
    private UploadWidgetImageView uploadWidgetImageView;
    private Uri uri;

    public interface Callback {
        void onCropRotateCancel(Uri uri);

        void onCropRotateFinish(Uri uri, CropRotateResult cropRotateResult, Bitmap bitmap);
    }

    public static CropRotateFragment newInstance(Uri uri, Callback callback) {
        if (uri == null) {
            throw new IllegalArgumentException("Uri must be provided");
        }
        CropRotateFragment fragment = new CropRotateFragment();
        Bundle args = new Bundle();
        args.putString(URI_ARG, uri.toString());
        fragment.setArguments(args);
        fragment.setCallback(callback);
        return fragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.uri = Uri.parse(arguments.getString(URI_ARG));
        }
        setHasOptionsMenu(true);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crop_rotate, container, false);
        this.uploadWidgetImageView = (UploadWidgetImageView) view.findViewById(R.id.imageView);
        MediaType mediaType = UriUtils.getMediaType(getContext(), this.uri);
        if (mediaType == MediaType.VIDEO) {
            Bitmap videoThumbnail = UriUtils.getVideoThumbnail(getContext(), this.uri);
            BitmapManager.get().save(getContext(), videoThumbnail, new BitmapManager.SaveCallback() { // from class: com.cloudinary.android.uploadwidget.ui.CropRotateFragment.1
                @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.SaveCallback
                public void onSuccess(Uri resultUri) {
                    CropRotateFragment.this.uploadWidgetImageView.setImageUri(resultUri);
                    CropRotateFragment.this.uploadWidgetImageView.showCropOverlay();
                }

                @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.SaveCallback
                public void onFailure() {
                }
            });
        } else {
            this.uploadWidgetImageView.setImageUri(this.uri);
            this.uploadWidgetImageView.showCropOverlay();
        }
        Button doneButton = (Button) view.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() { // from class: com.cloudinary.android.uploadwidget.ui.CropRotateFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CropRotateFragment.this.callback != null) {
                    CropRotateFragment.this.callback.onCropRotateFinish(CropRotateFragment.this.uri, CropRotateFragment.this.getResult(), CropRotateFragment.this.uploadWidgetImageView.getResultBitmap());
                    CropRotateFragment.this.onBackPressed();
                }
            }
        });
        Button cancelButton = (Button) view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.cloudinary.android.uploadwidget.ui.CropRotateFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (CropRotateFragment.this.callback != null) {
                    CropRotateFragment.this.callback.onCropRotateCancel(CropRotateFragment.this.uri);
                }
                CropRotateFragment.this.onBackPressed();
            }
        });
        ImageView rotateButton = (ImageView) view.findViewById(R.id.rotateButton);
        if (mediaType == MediaType.VIDEO) {
            rotateButton.setVisibility(8);
        } else {
            rotateButton.setOnClickListener(new View.OnClickListener() { // from class: com.cloudinary.android.uploadwidget.ui.CropRotateFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    CropRotateFragment.this.uploadWidgetImageView.rotateImage();
                }
            });
        }
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() { // from class: com.cloudinary.android.uploadwidget.ui.CropRotateFragment.5
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 4 && event.getAction() == 1) {
                    if (CropRotateFragment.this.callback != null) {
                        CropRotateFragment.this.callback.onCropRotateCancel(CropRotateFragment.this.uri);
                    }
                    CropRotateFragment.this.onBackPressed();
                    return true;
                }
                return false;
            }
        });
        setToolbarIfNeeded();
        return view;
    }

    private void setToolbarIfNeeded() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.cropRotateToolbar);
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
        menu.clear();
        inflater.inflate(R.menu.crop_rotate_menu, menu);
        MenuItem aspectRatioItem = menu.findItem(R.id.aspect_ratio_action);
        final View aspectRatioActionView = aspectRatioItem.getActionView();
        aspectRatioActionView.setOnClickListener(new View.OnClickListener() { // from class: com.cloudinary.android.uploadwidget.ui.CropRotateFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                TextView aspectRatioTextView = (TextView) aspectRatioActionView.findViewById(R.id.aspectRatioTextView);
                ImageView aspectRatioImageView = (ImageView) aspectRatioActionView.findViewById(R.id.aspectRatioImageView);
                if (CropRotateFragment.this.uploadWidgetImageView.isAspectRatioLocked()) {
                    CropRotateFragment.this.uploadWidgetImageView.setAspectRatioLocked(false);
                    aspectRatioTextView.setText(CropRotateFragment.this.getString(R.string.menu_item_aspect_ratio_unlocked));
                    aspectRatioImageView.setImageResource(R.drawable.unlock);
                } else {
                    CropRotateFragment.this.uploadWidgetImageView.setAspectRatioLocked(true);
                    aspectRatioTextView.setText(CropRotateFragment.this.getString(R.string.menu_item_aspect_ratio_locked));
                    aspectRatioImageView.setImageResource(R.drawable.lock);
                }
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            if (this.callback != null) {
                this.callback.onCropRotateCancel(this.uri);
            }
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CropRotateResult getResult() {
        return new CropRotateResult(this.uploadWidgetImageView.getRotationAngle(), this.uploadWidgetImageView.getCropPoints());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackPressed() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }
}
