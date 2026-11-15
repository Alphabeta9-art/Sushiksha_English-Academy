package com.linkedin.android.litr.exception;

import android.net.Uri;

/* JADX INFO: loaded from: classes13.dex */
public class MediaTargetException extends MediaTransformationException {
    private static final String INVALID_PARAMS_TEXT = "Invalid parameters";
    private static final String IO_FAILURE_TEXT = "Failed to open the media target for write.";
    private static final String UNSUPPORTED_URI_TYPE_TEXT = "URI type not supported at API level below 26";
    private final Error error;
    private final String outputFilePath;
    private final int outputFormat;

    public MediaTargetException(Error error, Uri outputFileUri, int outputFormat, Throwable cause) {
        this(error, outputFileUri.toString(), outputFormat, cause);
    }

    public MediaTargetException(Error error, String outputFilePath, int outputFormat, Throwable cause) {
        super(cause);
        this.error = error;
        this.outputFilePath = outputFilePath;
        this.outputFormat = outputFormat;
    }

    public enum Error {
        INVALID_PARAMS(MediaTargetException.INVALID_PARAMS_TEXT),
        IO_FAILUE(MediaTargetException.IO_FAILURE_TEXT),
        UNSUPPORTED_URI_TYPE(MediaTargetException.UNSUPPORTED_URI_TYPE_TEXT);

        private final String text;

        Error(String text) {
            this.text = text;
        }
    }

    public Error getError() {
        return this.error;
    }

    @Override // com.linkedin.android.litr.exception.MediaTransformationException, java.lang.Throwable
    public String toString() {
        return super.toString() + '\n' + this.error.text + "\nOutput file path or Uri encoded string: " + this.outputFilePath + "\nMediaMuxer output format: " + this.outputFormat;
    }
}
