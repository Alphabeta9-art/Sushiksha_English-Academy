package com.cloudinary.android.uploadwidget.ui;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.cloudinary.android.UploadRequest;
import com.cloudinary.android.ui.R;
import com.cloudinary.android.uploadwidget.UploadWidget;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes11.dex */
public class UploadWidgetActivity extends AppCompatActivity implements UploadWidgetFragment.UploadWidgetListener {
    private static final int MEDIA_CHOOSER_REQUEST_CODE = 5050;
    private static final String UPLOAD_WIDGET_FRAGMENT_TAG = "upload_widget_fragment_tag";
    private UploadWidget.Action action;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_widget);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        this.action = (UploadWidget.Action) getIntent().getSerializableExtra(UploadWidget.ACTION_EXTRA);
        ArrayList<Uri> uris = getIntent().getParcelableArrayListExtra(UploadWidget.URIS_EXTRA);
        if (uris != null && !uris.isEmpty()) {
            showImages(uris);
        } else {
            UploadWidget.openMediaChooser(this, MEDIA_CHOOSER_REQUEST_CODE);
        }
    }

    private void showImages(ArrayList<Uri> uris) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(UPLOAD_WIDGET_FRAGMENT_TAG);
        if (fragment == null) {
            fragment = UploadWidgetFragment.newInstance(uris);
        }
        fragmentManager.beginTransaction().replace(R.id.container, fragment, UPLOAD_WIDGET_FRAGMENT_TAG).commit();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MEDIA_CHOOSER_REQUEST_CODE) {
            if (resultCode == -1 && data != null) {
                ArrayList<Uri> uris = extractImageUris(data);
                int i = Build.VERSION.SDK_INT;
                int takeFlags = data.getFlags() & 1;
                for (Uri uri : uris) {
                    if (DocumentsContract.isDocumentUri(this, uri)) {
                        getContentResolver().takePersistableUriPermission(uri, takeFlags);
                    }
                }
                showImages(uris);
                return;
            }
            setResult(0);
            finish();
        }
    }

    private ArrayList<Uri> extractImageUris(Intent data) {
        ArrayList<Uri> imageUris = new ArrayList<>();
        int i = Build.VERSION.SDK_INT;
        ClipData clipData = data.getClipData();
        if (clipData != null) {
            for (int i2 = 0; i2 < clipData.getItemCount(); i2++) {
                imageUris.add(clipData.getItemAt(i2).getUri());
            }
        } else if (data.getData() != null) {
            imageUris.add(data.getData());
        }
        return imageUris;
    }

    @Override // com.cloudinary.android.uploadwidget.ui.UploadWidgetFragment.UploadWidgetListener
    public void onConfirm(ArrayList<UploadWidget.Result> results) {
        Intent data = new Intent();
        if (this.action != UploadWidget.Action.NONE) {
            for (UploadWidget.Result result : results) {
                UploadRequest uploadRequest = UploadWidget.preprocessResult(this, result);
                result.requestId = this.action == UploadWidget.Action.START_NOW ? uploadRequest.startNow(this) : uploadRequest.dispatch(this);
            }
        }
        data.putParcelableArrayListExtra(UploadWidget.RESULT_EXTRA, results);
        setResult(-1, data);
        finish();
    }
}
