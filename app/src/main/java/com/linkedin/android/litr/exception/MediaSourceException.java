package com.linkedin.android.litr.exception;

import android.net.Uri;

/* JADX INFO: loaded from: classes13.dex */
public class MediaSourceException extends MediaTransformationException {
    private static final String DATA_SOURCE_ERROR_TEXT = "data source error";
    private static final String MEDIA_EXTRACTOR_CREATION_ERROR_TEXT = "Failed to create media source due to a ";
    private final Error error;
    private final Uri inputUri;

    public MediaSourceException(Error error, Uri inputUri, Throwable throwable) {
        super(throwable);
        this.error = error;
        this.inputUri = inputUri;
    }

    public enum Error {
        DATA_SOURCE(MediaSourceException.DATA_SOURCE_ERROR_TEXT);

        private final String text;

        Error(String message) {
            this.text = message;
        }
    }

    public Error getError() {
        return this.error;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return MEDIA_EXTRACTOR_CREATION_ERROR_TEXT + this.error.text;
    }

    @Override // com.linkedin.android.litr.exception.MediaTransformationException, java.lang.Throwable
    public String toString() {
        return super.toString() + '\n' + MEDIA_EXTRACTOR_CREATION_ERROR_TEXT + this.error.text + "\nUri: " + this.inputUri;
    }
}
