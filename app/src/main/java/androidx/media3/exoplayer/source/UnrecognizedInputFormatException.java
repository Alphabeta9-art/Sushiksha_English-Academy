package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.common.ParserException;

/* JADX INFO: loaded from: classes11.dex */
public class UnrecognizedInputFormatException extends ParserException {
    public final Uri uri;

    public UnrecognizedInputFormatException(String message, Uri uri) {
        super(message, null, false, 1);
        this.uri = uri;
    }
}
