package com.cloudinary.android.uploadwidget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.MimeTypes;
import com.cloudinary.android.UploadRequest;
import com.cloudinary.android.uploadwidget.model.CropPoints;
import com.cloudinary.android.uploadwidget.ui.UploadWidgetActivity;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes11.dex */
public class UploadWidget {
    public static final String ACTION_EXTRA = "required_action_extra";
    public static final String RESULT_EXTRA = "upload_widget_result_extra";
    public static final String URIS_EXTRA = "uris_extra";

    public enum Action {
        DISPATCH,
        START_NOW,
        NONE
    }

    @Deprecated
    public static void startActivity(Activity activity, int requestCode, ArrayList<Uri> uris) {
        startActivity(activity, requestCode, new Options(Action.NONE, uris));
    }

    public static void startActivity(Activity activity, int requestCode) {
        startActivity(activity, requestCode, new Options(Action.DISPATCH, null));
    }

    public static void startActivity(Activity activity, int requestCode, Options options) {
        Intent intent = new Intent(activity, (Class<?>) UploadWidgetActivity.class).putExtra(ACTION_EXTRA, options.action);
        if (options.uris != null && !options.uris.isEmpty()) {
            intent.putParcelableArrayListExtra(URIS_EXTRA, new ArrayList<>(options.uris));
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public static ArrayList<UploadRequest> preprocessResults(Context context, Intent data) {
        checkDataNotNull(data);
        ArrayList<Result> results = data.getParcelableArrayListExtra(RESULT_EXTRA);
        ArrayList<UploadRequest> uploadRequests = new ArrayList<>(results.size());
        for (Result result : results) {
            UploadRequest uploadRequest = UploadWidgetResultProcessor.process(context, result);
            uploadRequests.add(uploadRequest);
        }
        return uploadRequests;
    }

    public static UploadRequest preprocessResult(Context context, Result result) {
        return UploadWidgetResultProcessor.process(context, result);
    }

    public static UploadRequest preprocessResult(Context context, UploadRequest uploadRequest, Result result) {
        return UploadWidgetResultProcessor.process(context, uploadRequest, result);
    }

    public static void openMediaChooser(Activity activity, int requestCode) {
        Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT");
        intent.addCategory("android.intent.category.OPENABLE");
        int i = Build.VERSION.SDK_INT;
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        int i2 = Build.VERSION.SDK_INT;
        intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/jpeg", "image/jpg", MimeTypes.IMAGE_PNG, "video/*"});
        intent.setType("(*/*");
        activity.startActivityForResult(intent, requestCode);
    }

    private static void checkDataNotNull(Intent data) {
        ArrayList<Result> results = data.getParcelableArrayListExtra(RESULT_EXTRA);
        if (results == null) {
            throw new IllegalArgumentException("Data must contain upload widget results");
        }
    }

    public static final class Result implements Parcelable {
        public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() { // from class: com.cloudinary.android.uploadwidget.UploadWidget.Result.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Result createFromParcel(Parcel in) {
                return new Result(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Result[] newArray(int size) {
                return new Result[size];
            }
        };
        public CropPoints cropPoints;
        public String requestId;
        public int rotationAngle;
        public Uri uri;

        public Result(Uri uri) {
            this.uri = uri;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.uri, flags);
            dest.writeParcelable(this.cropPoints, flags);
            dest.writeInt(this.rotationAngle);
            dest.writeString(this.requestId);
        }

        protected Result(Parcel in) {
            this.uri = (Uri) in.readParcelable(Uri.class.getClassLoader());
            this.cropPoints = (CropPoints) in.readParcelable(CropPoints.class.getClassLoader());
            this.rotationAngle = in.readInt();
            this.requestId = in.readString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    public static class Options {
        final Action action;
        final Collection<Uri> uris;

        public Options(Action action, Collection<Uri> uris) {
            this.action = action;
            this.uris = uris;
        }
    }
}
