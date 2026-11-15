package com.cloudinary.android.uploadwidget.model;

import android.net.Uri;

/* JADX INFO: loaded from: classes11.dex */
public class Media {
    private Uri resultUri;
    private Uri sourceUri;

    public Media(Uri sourceUri) {
        this.sourceUri = sourceUri;
    }

    public Uri getSourceUri() {
        return this.sourceUri;
    }

    public void setSourceUri(Uri sourceUri) {
        this.sourceUri = sourceUri;
    }

    public Uri getResultUri() {
        return this.resultUri;
    }

    public void setResultUri(Uri resultUri) {
        this.resultUri = resultUri;
    }
}
