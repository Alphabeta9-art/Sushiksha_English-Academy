package com.example.sushikhasacademyapp.data;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Toast;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.FileProvider;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.window.core.layout.WindowSizeClass;
import com.cloudinary.ArchiveParams;
import com.example.sushikhasacademyapp.UploadItem;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.tasks.Task;
import com.google.common.net.HttpHeaders;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.tasks.TasksKt;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: GoogleDriveStorageService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005J\u000e\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u0005J\u000e\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u001bJ\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u0019\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u001bJ$\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00050 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002JB\u0010&\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00050 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010*\u001a\u00020'H\u0086@¢\u0006\u0002\u0010+J:\u0010,\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00050 2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010*\u001a\u00020'H\u0086@¢\u0006\u0002\u0010.J*\u0010/\u001a\u0004\u0018\u00010%2\u0006\u0010\"\u001a\u00020#2\u0006\u00100\u001a\u00020\u00052\u0006\u0010*\u001a\u00020'2\u0006\u00101\u001a\u00020!H\u0002J&\u00102\u001a\u0002032\u0006\u0010\"\u001a\u00020#2\u0006\u0010)\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\u0006\u0010*\u001a\u00020'R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/example/sushikhasacademyapp/data/GoogleDriveStorageService;", "", "<init>", "()V", "CLOUDINARY_CLOUD_NAME", "", "getCLOUDINARY_CLOUD_NAME", "()Ljava/lang/String;", "setCLOUDINARY_CLOUD_NAME", "(Ljava/lang/String;)V", "CLOUDINARY_UPLOAD_PRESET", "getCLOUDINARY_UPLOAD_PRESET", "setCLOUDINARY_UPLOAD_PRESET", "GOOGLE_DRIVE_FOLDER_ID", "GOOGLE_DRIVE_API_KEY", "GOOGLE_OAUTH_CLIENT_ID", "client", "Lokhttp3/OkHttpClient;", "getGoogleDriveDirectDownloadUrl", "driveFileId", "getGoogleDriveThumbnailUrl", "getGoogleDriveViewUrl", "extractDriveFileId", "urlOrId", "canonicalBatchKey", "batchTitle", "getOrCreateBatchSubfolderId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchFilesForBatch", "", "Lcom/example/sushikhasacademyapp/UploadItem;", "compressImageIfNeeded", "Lkotlin/Pair;", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "uploadToGoogleDriveServer", "", "fileUri", "title", "isImage", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerGoogleDriveLinkForBatch", "driveUrlOrId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveToPublicDownloads", "fileName", "bytes", "downloadFromGoogleDriveServer", "", "fileUrlOrData", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GoogleDriveStorageService {
    public static final String GOOGLE_DRIVE_API_KEY = "AIzaSyBMfiNv7mNpRcOspusVUii2XjKPiBIRsx4";
    public static final String GOOGLE_DRIVE_FOLDER_ID = "1NON-AjsYRC9-cfd3f8W-aXKRJrYESKs6";
    public static final String GOOGLE_OAUTH_CLIENT_ID = "719401769275-h3uchtrptbnsfsps5fgptk04ngj5k63h.apps.googleusercontent.com";
    public static final GoogleDriveStorageService INSTANCE = new GoogleDriveStorageService();
    private static String CLOUDINARY_CLOUD_NAME = "xjjv2y43";
    private static String CLOUDINARY_UPLOAD_PRESET = "sushiksha_preset";
    private static final OkHttpClient client = new OkHttpClient.Builder().followRedirects(true).followSslRedirects(true).connectTimeout(60, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build();
    public static final int $stable = 8;

    private GoogleDriveStorageService() {
    }

    public final String getCLOUDINARY_CLOUD_NAME() {
        return CLOUDINARY_CLOUD_NAME;
    }

    public final void setCLOUDINARY_CLOUD_NAME(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        CLOUDINARY_CLOUD_NAME = str;
    }

    public final String getCLOUDINARY_UPLOAD_PRESET() {
        return CLOUDINARY_UPLOAD_PRESET;
    }

    public final void setCLOUDINARY_UPLOAD_PRESET(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        CLOUDINARY_UPLOAD_PRESET = str;
    }

    public final String getGoogleDriveDirectDownloadUrl(String driveFileId) {
        Intrinsics.checkNotNullParameter(driveFileId, "driveFileId");
        return "https://drive.google.com/uc?export=download&id=" + driveFileId;
    }

    public final String getGoogleDriveThumbnailUrl(String driveFileId) {
        Intrinsics.checkNotNullParameter(driveFileId, "driveFileId");
        return "https://lh3.googleusercontent.com/d/" + driveFileId;
    }

    public final String getGoogleDriveViewUrl(String driveFileId) {
        Intrinsics.checkNotNullParameter(driveFileId, "driveFileId");
        return "https://drive.google.com/file/d/" + driveFileId + "/view";
    }

    public final String extractDriveFileId(String urlOrId) {
        Intrinsics.checkNotNullParameter(urlOrId, "urlOrId");
        String trimmed = StringsKt.trim((CharSequence) urlOrId).toString();
        if (StringsKt.contains$default((CharSequence) trimmed, (CharSequence) "/file/d/", false, 2, (Object) null)) {
            return StringsKt.substringBefore$default(StringsKt.substringBefore$default(StringsKt.substringAfter$default(trimmed, "/file/d/", (String) null, 2, (Object) null), DomExceptionUtils.SEPARATOR, (String) null, 2, (Object) null), "?", (String) null, 2, (Object) null);
        }
        if (StringsKt.contains$default((CharSequence) trimmed, (CharSequence) "id=", false, 2, (Object) null)) {
            return StringsKt.substringBefore$default(StringsKt.substringAfter$default(trimmed, "id=", (String) null, 2, (Object) null), "&", (String) null, 2, (Object) null);
        }
        if (StringsKt.contains$default((CharSequence) trimmed, (CharSequence) "/folders/", false, 2, (Object) null)) {
            return StringsKt.substringBefore$default(StringsKt.substringBefore$default(StringsKt.substringAfter$default(trimmed, "/folders/", (String) null, 2, (Object) null), DomExceptionUtils.SEPARATOR, (String) null, 2, (Object) null), "?", (String) null, 2, (Object) null);
        }
        if (new Regex("^[a-zA-Z0-9_-]{20,}$").matches(trimmed)) {
            return trimmed;
        }
        return null;
    }

    public final String canonicalBatchKey(String batchTitle) {
        Intrinsics.checkNotNullParameter(batchTitle, "batchTitle");
        String lowerCase = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(StringsKt.trim((CharSequence) batchTitle).toString(), "(", (String) null, 2, (Object) null)).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.GoogleDriveStorageService$getOrCreateBatchSubfolderId$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GoogleDriveStorageService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.GoogleDriveStorageService$getOrCreateBatchSubfolderId$2", f = "GoogleDriveStorageService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04272 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ String $batchTitle;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04272(String str, Continuation<? super C04272> continuation) {
            super(2, continuation);
            this.$batchTitle = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04272(this.$batchTitle, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C04272) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            String str = "toLowerCase(...)";
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    try {
                        String cleanBatch = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(StringsKt.trim((CharSequence) this.$batchTitle).toString(), "(", (String) null, 2, (Object) null)).toString();
                        String cleanKey = cleanBatch.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(cleanKey, "toLowerCase(...)");
                        Request req = new Request.Builder().url("https://www.googleapis.com/drive/v3/files?q='1NON-AjsYRC9-cfd3f8W-aXKRJrYESKs6'+in+parents+and+mimeType='application/vnd.google-apps.folder'+and+trashed=false&key=AIzaSyBMfiNv7mNpRcOspusVUii2XjKPiBIRsx4&fields=files(id,name)").get().build();
                        Response resp = GoogleDriveStorageService.client.newCall(req).execute();
                        ResponseBody responseBodyBody = resp.body();
                        String body = responseBodyBody != null ? responseBodyBody.string() : null;
                        if (body == null) {
                            body = "";
                        }
                        if (resp.isSuccessful() && !StringsKt.isBlank(body)) {
                            JSONObject json = new JSONObject(body);
                            JSONArray files = json.optJSONArray("files");
                            if (files != null) {
                                String bestMatchId = null;
                                int i = 0;
                                int length = files.length();
                                while (i < length) {
                                    JSONObject folder = files.getJSONObject(i);
                                    String strOptString = folder.optString(HintConstants.AUTOFILL_HINT_NAME);
                                    Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                                    String folderName = StringsKt.trim((CharSequence) strOptString).toString();
                                    String folderKey = folderName.toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(folderKey, str);
                                    String str2 = str;
                                    if (Intrinsics.areEqual(folderKey, cleanKey)) {
                                        return folder.optString("id");
                                    }
                                    String cleanBatch2 = cleanBatch;
                                    String cleanKey2 = cleanKey;
                                    if (StringsKt.contains$default((CharSequence) cleanKey, (CharSequence) folderKey, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) folderKey, (CharSequence) cleanKey2, false, 2, (Object) null)) {
                                        bestMatchId = folder.optString("id");
                                    }
                                    i++;
                                    str = str2;
                                    cleanBatch = cleanBatch2;
                                    cleanKey = cleanKey2;
                                }
                                if (bestMatchId != null) {
                                    return bestMatchId;
                                }
                                return null;
                            }
                            return null;
                        }
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object getOrCreateBatchSubfolderId(String batchTitle, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C04272(batchTitle, null), continuation);
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.GoogleDriveStorageService$fetchFilesForBatch$2, reason: invalid class name */
    /* JADX INFO: compiled from: GoogleDriveStorageService.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/example/sushikhasacademyapp/UploadItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.GoogleDriveStorageService$fetchFilesForBatch$2", f = "GoogleDriveStorageService.kt", i = {0}, l = {114}, m = "invokeSuspend", n = {"list"}, s = {"L$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends UploadItem>>, Object> {
        final /* synthetic */ String $batchTitle;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$batchTitle = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$batchTitle, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends UploadItem>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<UploadItem>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<UploadItem>> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object obj2;
            Object orCreateBatchSubfolderId;
            List list;
            JSONArray jSONArrayOptJSONArray;
            String googleDriveDirectDownloadUrl;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                switch (i) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        ArrayList arrayList = new ArrayList();
                        this.L$0 = arrayList;
                        this.label = 1;
                        orCreateBatchSubfolderId = GoogleDriveStorageService.INSTANCE.getOrCreateBatchSubfolderId(this.$batchTitle, this);
                        list = arrayList;
                        if (orCreateBatchSubfolderId == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        List list2 = (List) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        orCreateBatchSubfolderId = obj;
                        list = list2;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String str = (String) orCreateBatchSubfolderId;
                String str2 = str;
                if (str2 == null || StringsKt.isBlank(str2)) {
                    return CollectionsKt.emptyList();
                }
                String str3 = "https://www.googleapis.com/drive/v3/files?q='" + str + "'+in+parents+and+mimeType!='application/vnd.google-apps.folder'+and+trashed=false&key=AIzaSyBMfiNv7mNpRcOspusVUii2XjKPiBIRsx4&fields=files(id,name,mimeType)";
                Response responseExecute = GoogleDriveStorageService.client.newCall(new Request.Builder().url(str3).get().build()).execute();
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                if (strString == null) {
                    strString = "";
                }
                if (!responseExecute.isSuccessful() || StringsKt.isBlank(strString) || (jSONArrayOptJSONArray = new JSONObject(strString).optJSONArray("files")) == null) {
                    obj2 = list;
                } else {
                    int i2 = 0;
                    int length = jSONArrayOptJSONArray.length();
                    while (i2 < length) {
                        JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i2);
                        String strOptString = jSONObject.optString("id");
                        String strOptString2 = jSONObject.optString(HintConstants.AUTOFILL_HINT_NAME);
                        if (strOptString2 == null) {
                            strOptString2 = "Study Document";
                        }
                        String str4 = strOptString2;
                        String strOptString3 = jSONObject.optString("mimeType");
                        if (strOptString3 == null) {
                            strOptString3 = "";
                        }
                        String str5 = str3;
                        String str6 = str;
                        boolean zStartsWith$default = StringsKt.startsWith$default(strOptString3, "image/", false, 2, (Object) null);
                        if (zStartsWith$default) {
                            GoogleDriveStorageService googleDriveStorageService = GoogleDriveStorageService.INSTANCE;
                            Intrinsics.checkNotNull(strOptString);
                            googleDriveDirectDownloadUrl = googleDriveStorageService.getGoogleDriveThumbnailUrl(strOptString);
                        } else {
                            GoogleDriveStorageService googleDriveStorageService2 = GoogleDriveStorageService.INSTANCE;
                            Intrinsics.checkNotNull(strOptString);
                            googleDriveDirectDownloadUrl = googleDriveStorageService2.getGoogleDriveDirectDownloadUrl(strOptString);
                        }
                        list.add(new UploadItem(strOptString, str4, zStartsWith$default ? "Google Drive Photo • Image" : "Google Drive PDF • Document", zStartsWith$default, googleDriveDirectDownloadUrl));
                        i2++;
                        str = str6;
                        str3 = str5;
                    }
                    obj2 = list;
                }
                return obj2;
            } catch (Exception e) {
                e.printStackTrace();
                obj2 = i;
            }
        }
    }

    public final Object fetchFilesForBatch(String batchTitle, Continuation<? super List<UploadItem>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(batchTitle, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<byte[], String> compressImageIfNeeded(Context context, Uri uri) throws IOException {
        byte[] rawBytes;
        float scale;
        Bitmap scaledBitmap;
        String type = context.getContentResolver().getType(uri);
        if (type == null) {
            type = "image/jpeg";
        }
        String mimeType = type;
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        if (inputStream == null || (rawBytes = ByteStreamsKt.readBytes(inputStream)) == null) {
            rawBytes = new byte[0];
        }
        if (inputStream != null) {
            inputStream.close();
        }
        if (StringsKt.startsWith$default(mimeType, "image", false, 2, (Object) null) && rawBytes.length >= 512000) {
            try {
                Bitmap bitmap = BitmapFactory.decodeByteArray(rawBytes, 0, rawBytes.length);
                if (bitmap != null) {
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    if (width > 1400 || height > 1400) {
                        scale = 1400 / Math.max(width, height);
                    } else {
                        scale = 1.0f;
                    }
                    if (scale < 1.0f) {
                        scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (width * scale), (int) (height * scale), true);
                    } else {
                        scaledBitmap = bitmap;
                    }
                    Intrinsics.checkNotNull(scaledBitmap);
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 75, outStream);
                    byte[] compressedBytes = outStream.toByteArray();
                    return new Pair<>(compressedBytes, "image/jpeg");
                }
            } catch (Exception e) {
            }
            return new Pair<>(rawBytes, mimeType);
        }
        return new Pair<>(rawBytes, mimeType);
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.GoogleDriveStorageService$uploadToGoogleDriveServer$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GoogleDriveStorageService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.GoogleDriveStorageService$uploadToGoogleDriveServer$2", f = "GoogleDriveStorageService.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {RotationOptions.ROTATE_270, 300}, m = "invokeSuspend", n = {"currentUser", "cleanTitle", "canonicalBatch", "cleanBatch", "fileExt", "cleanFileName", "bytes", "mimeType", "allowedMimeTypes", "uploadUrl", "requestBody", "req", "resp", "respStr", "json", "secureUrl", "uploadData", "maxSizeBytes", "currentUser", "cleanTitle", "canonicalBatch", "cleanBatch", "fileExt", "cleanFileName", "bytes", "mimeType", "allowedMimeTypes", "base64Data", "dataUri", "uploadData", "maxSizeBytes"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0"})
    static final class C04292 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Boolean, ? extends String>>, Object> {
        final /* synthetic */ String $batchTitle;
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $fileUri;
        final /* synthetic */ boolean $isImage;
        final /* synthetic */ String $title;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04292(String str, String str2, boolean z, Context context, Uri uri, Continuation<? super C04292> continuation) {
            super(2, continuation);
            this.$title = str;
            this.$batchTitle = str2;
            this.$isImage = z;
            this.$context = context;
            this.$fileUri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04292(this.$title, this.$batchTitle, this.$isImage, this.$context, this.$fileUri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Boolean, ? extends String>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super Pair<Boolean, String>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Pair<Boolean, String>> continuation) {
            return ((C04292) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:140:0x05f6  */
        /* JADX WARN: Code duplicated, block: B:143:0x0619 A[Catch: Exception -> 0x0688, TRY_LEAVE, TryCatch #17 {Exception -> 0x0688, blocks: (B:141:0x0610, B:143:0x0619, B:150:0x0648), top: B:222:0x0610 }] */
        /* JADX WARN: Code duplicated, block: B:147:0x0630  */
        /* JADX WARN: Code duplicated, block: B:150:0x0648 A[Catch: Exception -> 0x0688, TRY_ENTER, TRY_LEAVE, TryCatch #17 {Exception -> 0x0688, blocks: (B:141:0x0610, B:143:0x0619, B:150:0x0648), top: B:222:0x0610 }] */
        /* JADX WARN: Code duplicated, block: B:157:0x0682  */
        /* JADX WARN: Code duplicated, block: B:171:0x0736 A[Catch: Exception -> 0x0899, TryCatch #16 {Exception -> 0x0899, blocks: (B:7:0x0067, B:179:0x0848, B:166:0x06fe, B:169:0x072c, B:171:0x0736, B:175:0x076f, B:181:0x086f, B:15:0x0100, B:17:0x010c, B:19:0x0118, B:27:0x0178, B:32:0x01b8, B:34:0x01c6, B:36:0x01ce, B:38:0x01dc, B:40:0x020b, B:52:0x0269, B:54:0x028e, B:56:0x029c, B:58:0x02aa, B:43:0x021c, B:44:0x0220, B:46:0x0226, B:23:0x016f), top: B:221:0x0028 }] */
        /* JADX WARN: Code duplicated, block: B:173:0x076a  */
        /* JADX WARN: Code duplicated, block: B:174:0x076d  */
        /* JADX WARN: Code duplicated, block: B:177:0x0835 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:178:0x0836  */
        /* JADX WARN: Code duplicated, block: B:181:0x086f A[Catch: Exception -> 0x0899, TRY_LEAVE, TryCatch #16 {Exception -> 0x0899, blocks: (B:7:0x0067, B:179:0x0848, B:166:0x06fe, B:169:0x072c, B:171:0x0736, B:175:0x076f, B:181:0x086f, B:15:0x0100, B:17:0x010c, B:19:0x0118, B:27:0x0178, B:32:0x01b8, B:34:0x01c6, B:36:0x01ce, B:38:0x01dc, B:40:0x020b, B:52:0x0269, B:54:0x028e, B:56:0x029c, B:58:0x02aa, B:43:0x021c, B:44:0x0220, B:46:0x0226, B:23:0x016f), top: B:221:0x0028 }] */
        /* JADX WARN: Code duplicated, block: B:197:0x0628 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            FirebaseUser currentUser;
            String str;
            String respStr;
            String cleanFileName;
            int maxSizeBytes;
            String str2;
            byte[] bytes;
            boolean z;
            String cleanFileName2;
            String cleanBatch;
            String cleanBatch2;
            String cleanFileName3;
            byte[] bytes2;
            String cleanTitle;
            Object obj;
            String canonicalBatch;
            String cleanFileName4;
            String cleanBatch3;
            String fileExt;
            List allowedMimeTypes;
            String cleanTitle2;
            int maxSizeBytes2;
            String mimeType;
            List allowedMimeTypes2;
            String fileExt2;
            String canonicalBatch2;
            String strString;
            String respStr2;
            String canonicalBatch3;
            String mimeType2;
            HashMap uploadData;
            Request req;
            JSONObject json;
            String secureUrl;
            JSONObject errorObj;
            String errMsg;
            String canonicalBatch4;
            String str3;
            String str4;
            String str5;
            Task<DocumentReference> taskAdd;
            String canonicalBatch5;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            String secureUrl2 = "toLowerCase(...)";
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (currentUser == null) {
                            return new Pair(Boxing.boxBoolean(false), "Authentication required to upload study materials.");
                        }
                        String cleanTitle3 = StringsKt.trim((CharSequence) this.$title).toString();
                        str = "!";
                        respStr = "Document uploaded exclusively to ";
                        String canonicalBatch6 = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(StringsKt.trim((CharSequence) this.$batchTitle).toString(), "(", (String) null, 2, (Object) null)).toString();
                        String cleanBatch4 = new Regex("[^a-zA-Z0-9_]").replace(canonicalBatch6, "_");
                        cleanFileName = "batchKey";
                        String canonicalBatch7 = canonicalBatch6;
                        String fileExt3 = StringsKt.contains$default((CharSequence) cleanTitle3, (CharSequence) ".", false, 2, (Object) null) ? "" : this.$isImage ? ".jpg" : ".pdf";
                        String cleanFileName5 = new Regex("[^a-zA-Z0-9_.-]").replace(cleanTitle3, "_") + fileExt3;
                        Pair pairCompressImageIfNeeded = GoogleDriveStorageService.INSTANCE.compressImageIfNeeded(this.$context, this.$fileUri);
                        byte[] bytes3 = (byte[]) pairCompressImageIfNeeded.component1();
                        String mimeType3 = (String) pairCompressImageIfNeeded.component2();
                        if (bytes3.length == 0) {
                            return new Pair(Boxing.boxBoolean(false), "Could not read selected file from device.");
                        }
                        int maxSizeBytes3 = 52428800;
                        if (bytes3.length > 52428800) {
                            return new Pair(Boxing.boxBoolean(false), "File size exceeds 50 MB limit.");
                        }
                        String fileExt4 = fileExt3;
                        List allowedMimeTypes3 = CollectionsKt.listOf((Object[]) new String[]{"image/", "application/pdf", "application/msword", "application/vnd.openxmlformats-officedocument", "text/plain"});
                        List list = allowedMimeTypes3;
                        if ((list instanceof Collection) && list.isEmpty()) {
                            maxSizeBytes = 52428800;
                            str2 = "toLowerCase(...)";
                            bytes = bytes3;
                            z = false;
                        } else {
                            Iterator it = list.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    String str6 = (String) it.next();
                                    Iterable iterable = list;
                                    String lowerCase = mimeType3.toLowerCase(Locale.ROOT);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase, secureUrl2);
                                    maxSizeBytes = maxSizeBytes3;
                                    str2 = secureUrl2;
                                    Iterator it2 = it;
                                    bytes = bytes3;
                                    if (StringsKt.startsWith$default(lowerCase, str6, false, 2, (Object) null)) {
                                        z = true;
                                    } else {
                                        bytes3 = bytes;
                                        list = iterable;
                                        it = it2;
                                        maxSizeBytes3 = maxSizeBytes;
                                        secureUrl2 = str2;
                                    }
                                } else {
                                    maxSizeBytes = maxSizeBytes3;
                                    str2 = secureUrl2;
                                    bytes = bytes3;
                                    z = false;
                                }
                            }
                        }
                        if (!z) {
                            return new Pair(Boxing.boxBoolean(false), "File format (" + mimeType3 + ") is not allowed.");
                        }
                        if (!StringsKt.isBlank(GoogleDriveStorageService.INSTANCE.getCLOUDINARY_CLOUD_NAME()) && !StringsKt.isBlank(GoogleDriveStorageService.INSTANCE.getCLOUDINARY_UPLOAD_PRESET()) && !Intrinsics.areEqual(GoogleDriveStorageService.INSTANCE.getCLOUDINARY_CLOUD_NAME(), "dqx123abc")) {
                            try {
                                String uploadUrl = "https://api.cloudinary.com/v1_1/" + GoogleDriveStorageService.INSTANCE.getCLOUDINARY_CLOUD_NAME() + "/auto/upload";
                                byte[] bytes4 = bytes;
                                try {
                                    bytes2 = bytes4;
                                    try {
                                        MultipartBody requestBody = new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM).addFormDataPart("upload_preset", GoogleDriveStorageService.INSTANCE.getCLOUDINARY_UPLOAD_PRESET()).addFormDataPart("folder", "sushiksha_batches/" + cleanBatch4).addFormDataPart(UriUtil.LOCAL_FILE_SCHEME, cleanFileName5, RequestBody.Companion.create$default(RequestBody.INSTANCE, bytes4, MediaType.INSTANCE.parse(mimeType3), 0, 0, 6, (Object) null)).build();
                                        Request req2 = new Request.Builder().url(uploadUrl).post(requestBody).build();
                                        Response resp = GoogleDriveStorageService.client.newCall(req2).execute();
                                        ResponseBody responseBodyBody = resp.body();
                                        if (responseBodyBody != null) {
                                            try {
                                                strString = responseBodyBody.string();
                                            } catch (Exception e) {
                                                e = e;
                                                cleanFileName4 = cleanFileName5;
                                                cleanBatch3 = cleanBatch4;
                                                bytes = bytes2;
                                                mimeType = mimeType3;
                                                cleanBatch2 = str;
                                                cleanFileName3 = respStr;
                                                canonicalBatch2 = canonicalBatch7;
                                                fileExt = fileExt4;
                                                allowedMimeTypes2 = allowedMimeTypes3;
                                                secureUrl2 = str2;
                                                fileExt2 = cleanTitle3;
                                                obj = coroutine_suspended;
                                            }
                                        } else {
                                            strString = null;
                                        }
                                        if (strString == null) {
                                            strString = "";
                                        }
                                        String respStr3 = strString;
                                        if (resp.isSuccessful()) {
                                            try {
                                                if (StringsKt.isBlank(respStr3)) {
                                                    cleanFileName2 = cleanFileName5;
                                                    cleanBatch = cleanBatch4;
                                                    cleanBatch2 = str;
                                                    cleanFileName3 = respStr;
                                                    canonicalBatch3 = canonicalBatch7;
                                                    secureUrl2 = str2;
                                                    cleanTitle = cleanTitle3;
                                                    obj = coroutine_suspended;
                                                    try {
                                                        if (!StringsKt.isBlank(respStr3)) {
                                                            errorObj = new JSONObject(respStr3).optJSONObject("error");
                                                            if (errorObj != null) {
                                                                try {
                                                                    errMsg = errorObj.optString("message");
                                                                    if (errMsg != null) {
                                                                        canonicalBatch4 = canonicalBatch3;
                                                                    } else {
                                                                        canonicalBatch4 = canonicalBatch3;
                                                                        try {
                                                                            errMsg = "Cloudinary error: " + resp.code();
                                                                        } catch (Exception e2) {
                                                                            e = e2;
                                                                            bytes = bytes2;
                                                                            mimeType = mimeType3;
                                                                            cleanFileName4 = cleanFileName2;
                                                                            cleanBatch3 = cleanBatch;
                                                                            fileExt = fileExt4;
                                                                            allowedMimeTypes2 = allowedMimeTypes3;
                                                                            fileExt2 = cleanTitle;
                                                                            canonicalBatch2 = canonicalBatch4;
                                                                        }
                                                                    }
                                                                } catch (Exception e3) {
                                                                    e = e3;
                                                                    bytes = bytes2;
                                                                    mimeType = mimeType3;
                                                                    cleanFileName4 = cleanFileName2;
                                                                    cleanBatch3 = cleanBatch;
                                                                    fileExt = fileExt4;
                                                                    allowedMimeTypes2 = allowedMimeTypes3;
                                                                    fileExt2 = cleanTitle;
                                                                    canonicalBatch2 = canonicalBatch3;
                                                                    e.printStackTrace();
                                                                    allowedMimeTypes = allowedMimeTypes2;
                                                                    mimeType3 = mimeType;
                                                                    bytes2 = bytes;
                                                                    canonicalBatch = canonicalBatch2;
                                                                    cleanTitle2 = fileExt2;
                                                                    maxSizeBytes2 = maxSizeBytes;
                                                                    str3 = cleanBatch2;
                                                                    str4 = cleanFileName3;
                                                                    if (bytes2.length <= 921600) {
                                                                        int sizeKb = bytes2.length / 1024;
                                                                        return new Pair(Boxing.boxBoolean(false), "File is " + sizeKb + " KB. Please add your Cloudinary Cloud Name & Upload Preset in GoogleDriveStorageService.kt to upload files of any size!");
                                                                    }
                                                                    String base64Data = Base64.encodeToString(bytes2, 2);
                                                                    byte[] bytes5 = bytes2;
                                                                    String dataUri = "data:" + mimeType3 + ";base64," + base64Data;
                                                                    Pair[] pairArr = new Pair[8];
                                                                    pairArr[0] = TuplesKt.to("title", cleanTitle2);
                                                                    if (this.$isImage) {
                                                                        str5 = "Class Photo • Image";
                                                                    } else {
                                                                        str5 = "Class Notes • PDF Document";
                                                                    }
                                                                    pairArr[1] = TuplesKt.to("meta", str5);
                                                                    pairArr[2] = TuplesKt.to("isImage", Boxing.boxBoolean(this.$isImage));
                                                                    pairArr[3] = TuplesKt.to("imageUri", dataUri);
                                                                    pairArr[4] = TuplesKt.to("serverType", "DirectDocument");
                                                                    pairArr[5] = TuplesKt.to("batchTitle", canonicalBatch);
                                                                    String lowerCase2 = canonicalBatch.toLowerCase(Locale.ROOT);
                                                                    Intrinsics.checkNotNullExpressionValue(lowerCase2, secureUrl2);
                                                                    pairArr[6] = TuplesKt.to(cleanFileName, lowerCase2);
                                                                    pairArr[7] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
                                                                    HashMap uploadData2 = MapsKt.hashMapOf(pairArr);
                                                                    taskAdd = FirebaseFirestore.getInstance().collection("batch_uploads").add(uploadData2);
                                                                    Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                                                                    this.L$0 = SpillingKt.nullOutSpilledVariable(currentUser);
                                                                    this.L$1 = SpillingKt.nullOutSpilledVariable(cleanTitle2);
                                                                    this.L$2 = canonicalBatch;
                                                                    this.L$3 = SpillingKt.nullOutSpilledVariable(cleanBatch3);
                                                                    this.L$4 = SpillingKt.nullOutSpilledVariable(fileExt);
                                                                    this.L$5 = SpillingKt.nullOutSpilledVariable(cleanFileName4);
                                                                    this.L$6 = SpillingKt.nullOutSpilledVariable(bytes5);
                                                                    this.L$7 = SpillingKt.nullOutSpilledVariable(mimeType3);
                                                                    this.L$8 = SpillingKt.nullOutSpilledVariable(allowedMimeTypes);
                                                                    this.L$9 = SpillingKt.nullOutSpilledVariable(base64Data);
                                                                    this.L$10 = SpillingKt.nullOutSpilledVariable(dataUri);
                                                                    this.L$11 = SpillingKt.nullOutSpilledVariable(uploadData2);
                                                                    this.L$12 = null;
                                                                    this.L$13 = null;
                                                                    this.L$14 = null;
                                                                    this.L$15 = null;
                                                                    this.L$16 = null;
                                                                    this.I$0 = maxSizeBytes2;
                                                                    this.label = 2;
                                                                    if (TasksKt.await(taskAdd, this) == obj) {
                                                                        return obj;
                                                                    }
                                                                    canonicalBatch5 = canonicalBatch;
                                                                    return new Pair(Boxing.boxBoolean(true), str4 + canonicalBatch5 + str3);
                                                                }
                                                            } else {
                                                                canonicalBatch4 = canonicalBatch3;
                                                                errMsg = "Cloudinary error: " + resp.code();
                                                            }
                                                            return new Pair(Boxing.boxBoolean(false), errMsg);
                                                        }
                                                        canonicalBatch7 = canonicalBatch3;
                                                    } catch (Exception e4) {
                                                        e = e4;
                                                        bytes = bytes2;
                                                        mimeType = mimeType3;
                                                        cleanFileName4 = cleanFileName2;
                                                        cleanBatch3 = cleanBatch;
                                                        fileExt = fileExt4;
                                                        allowedMimeTypes2 = allowedMimeTypes3;
                                                        fileExt2 = cleanTitle;
                                                        canonicalBatch2 = canonicalBatch3;
                                                    }
                                                    e.printStackTrace();
                                                    allowedMimeTypes = allowedMimeTypes2;
                                                    mimeType3 = mimeType;
                                                    bytes2 = bytes;
                                                    canonicalBatch = canonicalBatch2;
                                                    cleanTitle2 = fileExt2;
                                                    maxSizeBytes2 = maxSizeBytes;
                                                    str3 = cleanBatch2;
                                                    str4 = cleanFileName3;
                                                    if (bytes2.length <= 921600) {
                                                        int sizeKb2 = bytes2.length / 1024;
                                                        return new Pair(Boxing.boxBoolean(false), "File is " + sizeKb2 + " KB. Please add your Cloudinary Cloud Name & Upload Preset in GoogleDriveStorageService.kt to upload files of any size!");
                                                    }
                                                    String base64Data2 = Base64.encodeToString(bytes2, 2);
                                                    byte[] bytes6 = bytes2;
                                                    String dataUri2 = "data:" + mimeType3 + ";base64," + base64Data2;
                                                    Pair[] pairArr2 = new Pair[8];
                                                    pairArr2[0] = TuplesKt.to("title", cleanTitle2);
                                                    if (this.$isImage) {
                                                        str5 = "Class Photo • Image";
                                                    } else {
                                                        str5 = "Class Notes • PDF Document";
                                                    }
                                                    pairArr2[1] = TuplesKt.to("meta", str5);
                                                    pairArr2[2] = TuplesKt.to("isImage", Boxing.boxBoolean(this.$isImage));
                                                    pairArr2[3] = TuplesKt.to("imageUri", dataUri2);
                                                    pairArr2[4] = TuplesKt.to("serverType", "DirectDocument");
                                                    pairArr2[5] = TuplesKt.to("batchTitle", canonicalBatch);
                                                    String lowerCase3 = canonicalBatch.toLowerCase(Locale.ROOT);
                                                    Intrinsics.checkNotNullExpressionValue(lowerCase3, secureUrl2);
                                                    pairArr2[6] = TuplesKt.to(cleanFileName, lowerCase3);
                                                    pairArr2[7] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
                                                    HashMap uploadData3 = MapsKt.hashMapOf(pairArr2);
                                                    taskAdd = FirebaseFirestore.getInstance().collection("batch_uploads").add(uploadData3);
                                                    Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                                                    this.L$0 = SpillingKt.nullOutSpilledVariable(currentUser);
                                                    this.L$1 = SpillingKt.nullOutSpilledVariable(cleanTitle2);
                                                    this.L$2 = canonicalBatch;
                                                    this.L$3 = SpillingKt.nullOutSpilledVariable(cleanBatch3);
                                                    this.L$4 = SpillingKt.nullOutSpilledVariable(fileExt);
                                                    this.L$5 = SpillingKt.nullOutSpilledVariable(cleanFileName4);
                                                    this.L$6 = SpillingKt.nullOutSpilledVariable(bytes6);
                                                    this.L$7 = SpillingKt.nullOutSpilledVariable(mimeType3);
                                                    this.L$8 = SpillingKt.nullOutSpilledVariable(allowedMimeTypes);
                                                    this.L$9 = SpillingKt.nullOutSpilledVariable(base64Data2);
                                                    this.L$10 = SpillingKt.nullOutSpilledVariable(dataUri2);
                                                    this.L$11 = SpillingKt.nullOutSpilledVariable(uploadData3);
                                                    this.L$12 = null;
                                                    this.L$13 = null;
                                                    this.L$14 = null;
                                                    this.L$15 = null;
                                                    this.L$16 = null;
                                                    this.I$0 = maxSizeBytes2;
                                                    this.label = 2;
                                                    if (TasksKt.await(taskAdd, this) == obj) {
                                                        return obj;
                                                    }
                                                    canonicalBatch5 = canonicalBatch;
                                                } else {
                                                    respStr2 = respStr3;
                                                    JSONObject json2 = new JSONObject(respStr2);
                                                    String strOptString = json2.optString("secure_url");
                                                    if (StringsKt.isBlank(strOptString)) {
                                                        strOptString = json2.optString(ImagesContract.URL);
                                                    }
                                                    String secureUrl3 = strOptString;
                                                    Intrinsics.checkNotNull(secureUrl3);
                                                    if (StringsKt.isBlank(secureUrl3)) {
                                                        cleanFileName2 = cleanFileName5;
                                                        cleanBatch = cleanBatch4;
                                                        cleanBatch2 = str;
                                                        cleanFileName3 = respStr;
                                                        secureUrl2 = str2;
                                                        cleanTitle = cleanTitle3;
                                                        obj = coroutine_suspended;
                                                    } else {
                                                        Pair[] pairArr3 = new Pair[8];
                                                        pairArr3[0] = TuplesKt.to("title", cleanTitle3);
                                                        pairArr3[1] = TuplesKt.to("meta", this.$isImage ? "Cloud Photo • Image" : "Study Material • PDF Document");
                                                        pairArr3[2] = TuplesKt.to("isImage", Boxing.boxBoolean(this.$isImage));
                                                        pairArr3[3] = TuplesKt.to("imageUri", secureUrl3);
                                                        pairArr3[4] = TuplesKt.to("serverType", "Cloudinary");
                                                        canonicalBatch3 = canonicalBatch7;
                                                        try {
                                                            pairArr3[5] = TuplesKt.to("batchTitle", canonicalBatch3);
                                                            String lowerCase4 = canonicalBatch3.toLowerCase(Locale.ROOT);
                                                            secureUrl2 = str2;
                                                            try {
                                                                Intrinsics.checkNotNullExpressionValue(lowerCase4, secureUrl2);
                                                                cleanFileName2 = cleanFileName5;
                                                                try {
                                                                    pairArr3[6] = TuplesKt.to(cleanFileName, lowerCase4);
                                                                    cleanBatch = cleanBatch4;
                                                                    try {
                                                                        pairArr3[7] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
                                                                        HashMap uploadData4 = MapsKt.hashMapOf(pairArr3);
                                                                        cleanFileName = cleanFileName;
                                                                        try {
                                                                            Task<DocumentReference> taskAdd2 = FirebaseFirestore.getInstance().collection("batch_uploads").add(uploadData4);
                                                                            Intrinsics.checkNotNullExpressionValue(taskAdd2, "add(...)");
                                                                            C04292 c04292 = this;
                                                                            this.L$0 = SpillingKt.nullOutSpilledVariable(currentUser);
                                                                            this.L$1 = cleanTitle3;
                                                                            this.L$2 = canonicalBatch3;
                                                                            this.L$3 = SpillingKt.nullOutSpilledVariable(cleanBatch);
                                                                            this.L$4 = SpillingKt.nullOutSpilledVariable(fileExt4);
                                                                            this.L$5 = SpillingKt.nullOutSpilledVariable(cleanFileName2);
                                                                            this.L$6 = bytes2;
                                                                            this.L$7 = mimeType3;
                                                                            this.L$8 = SpillingKt.nullOutSpilledVariable(allowedMimeTypes3);
                                                                            this.L$9 = SpillingKt.nullOutSpilledVariable(uploadUrl);
                                                                            this.L$10 = SpillingKt.nullOutSpilledVariable(requestBody);
                                                                            this.L$11 = SpillingKt.nullOutSpilledVariable(req2);
                                                                            this.L$12 = SpillingKt.nullOutSpilledVariable(resp);
                                                                            this.L$13 = SpillingKt.nullOutSpilledVariable(respStr2);
                                                                            this.L$14 = SpillingKt.nullOutSpilledVariable(json2);
                                                                            this.L$15 = SpillingKt.nullOutSpilledVariable(secureUrl3);
                                                                            this.L$16 = SpillingKt.nullOutSpilledVariable(uploadData4);
                                                                            cleanTitle = cleanTitle3;
                                                                            int maxSizeBytes4 = maxSizeBytes;
                                                                            try {
                                                                                this.I$0 = maxSizeBytes4;
                                                                                maxSizeBytes = maxSizeBytes4;
                                                                                try {
                                                                                    this.label = 1;
                                                                                    obj = coroutine_suspended;
                                                                                    if (TasksKt.await(taskAdd2, c04292) == obj) {
                                                                                        return obj;
                                                                                    }
                                                                                    mimeType2 = canonicalBatch3;
                                                                                    uploadData = uploadData4;
                                                                                    mimeType = mimeType3;
                                                                                    req = req2;
                                                                                    json = json2;
                                                                                    secureUrl = secureUrl3;
                                                                                    cleanFileName4 = cleanFileName2;
                                                                                    cleanBatch3 = cleanBatch;
                                                                                    fileExt = fileExt4;
                                                                                    allowedMimeTypes2 = allowedMimeTypes3;
                                                                                    fileExt2 = cleanTitle;
                                                                                    try {
                                                                                        cleanFileName3 = respStr;
                                                                                        try {
                                                                                            cleanBatch2 = str;
                                                                                            try {
                                                                                                return new Pair(Boxing.boxBoolean(true), cleanFileName3 + mimeType2 + cleanBatch2);
                                                                                            } catch (Exception e5) {
                                                                                                e = e5;
                                                                                                bytes = bytes2;
                                                                                                canonicalBatch2 = mimeType2;
                                                                                                e.printStackTrace();
                                                                                                allowedMimeTypes = allowedMimeTypes2;
                                                                                                mimeType3 = mimeType;
                                                                                                bytes2 = bytes;
                                                                                                canonicalBatch = canonicalBatch2;
                                                                                                cleanTitle2 = fileExt2;
                                                                                                maxSizeBytes2 = maxSizeBytes;
                                                                                                str3 = cleanBatch2;
                                                                                                str4 = cleanFileName3;
                                                                                                if (bytes2.length <= 921600) {
                                                                                                    int sizeKb3 = bytes2.length / 1024;
                                                                                                    return new Pair(Boxing.boxBoolean(false), "File is " + sizeKb3 + " KB. Please add your Cloudinary Cloud Name & Upload Preset in GoogleDriveStorageService.kt to upload files of any size!");
                                                                                                }
                                                                                                String base64Data3 = Base64.encodeToString(bytes2, 2);
                                                                                                byte[] bytes7 = bytes2;
                                                                                                String dataUri3 = "data:" + mimeType3 + ";base64," + base64Data3;
                                                                                                Pair[] pairArr4 = new Pair[8];
                                                                                                pairArr4[0] = TuplesKt.to("title", cleanTitle2);
                                                                                                if (this.$isImage) {
                                                                                                    str5 = "Class Photo • Image";
                                                                                                } else {
                                                                                                    str5 = "Class Notes • PDF Document";
                                                                                                }
                                                                                                pairArr4[1] = TuplesKt.to("meta", str5);
                                                                                                pairArr4[2] = TuplesKt.to("isImage", Boxing.boxBoolean(this.$isImage));
                                                                                                pairArr4[3] = TuplesKt.to("imageUri", dataUri3);
                                                                                                pairArr4[4] = TuplesKt.to("serverType", "DirectDocument");
                                                                                                pairArr4[5] = TuplesKt.to("batchTitle", canonicalBatch);
                                                                                                String lowerCase5 = canonicalBatch.toLowerCase(Locale.ROOT);
                                                                                                Intrinsics.checkNotNullExpressionValue(lowerCase5, secureUrl2);
                                                                                                pairArr4[6] = TuplesKt.to(cleanFileName, lowerCase5);
                                                                                                pairArr4[7] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
                                                                                                HashMap uploadData5 = MapsKt.hashMapOf(pairArr4);
                                                                                                taskAdd = FirebaseFirestore.getInstance().collection("batch_uploads").add(uploadData5);
                                                                                                Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                                                                                                this.L$0 = SpillingKt.nullOutSpilledVariable(currentUser);
                                                                                                this.L$1 = SpillingKt.nullOutSpilledVariable(cleanTitle2);
                                                                                                this.L$2 = canonicalBatch;
                                                                                                this.L$3 = SpillingKt.nullOutSpilledVariable(cleanBatch3);
                                                                                                this.L$4 = SpillingKt.nullOutSpilledVariable(fileExt);
                                                                                                this.L$5 = SpillingKt.nullOutSpilledVariable(cleanFileName4);
                                                                                                this.L$6 = SpillingKt.nullOutSpilledVariable(bytes7);
                                                                                                this.L$7 = SpillingKt.nullOutSpilledVariable(mimeType3);
                                                                                                this.L$8 = SpillingKt.nullOutSpilledVariable(allowedMimeTypes);
                                                                                                this.L$9 = SpillingKt.nullOutSpilledVariable(base64Data3);
                                                                                                this.L$10 = SpillingKt.nullOutSpilledVariable(dataUri3);
                                                                                                this.L$11 = SpillingKt.nullOutSpilledVariable(uploadData5);
                                                                                                this.L$12 = null;
                                                                                                this.L$13 = null;
                                                                                                this.L$14 = null;
                                                                                                this.L$15 = null;
                                                                                                this.L$16 = null;
                                                                                                this.I$0 = maxSizeBytes2;
                                                                                                this.label = 2;
                                                                                                if (TasksKt.await(taskAdd, this) == obj) {
                                                                                                    return obj;
                                                                                                }
                                                                                                canonicalBatch5 = canonicalBatch;
                                                                                                return new Pair(Boxing.boxBoolean(true), str4 + canonicalBatch5 + str3);
                                                                                            }
                                                                                        } catch (Exception e6) {
                                                                                            e = e6;
                                                                                            cleanBatch2 = str;
                                                                                        }
                                                                                    } catch (Exception e7) {
                                                                                        e = e7;
                                                                                        cleanBatch2 = str;
                                                                                        cleanFileName3 = respStr;
                                                                                    }
                                                                                } catch (Exception e8) {
                                                                                    e = e8;
                                                                                    cleanBatch2 = str;
                                                                                    cleanFileName3 = respStr;
                                                                                    obj = coroutine_suspended;
                                                                                    bytes = bytes2;
                                                                                    mimeType = mimeType3;
                                                                                    cleanFileName4 = cleanFileName2;
                                                                                    cleanBatch3 = cleanBatch;
                                                                                    fileExt = fileExt4;
                                                                                    allowedMimeTypes2 = allowedMimeTypes3;
                                                                                    fileExt2 = cleanTitle;
                                                                                    canonicalBatch2 = canonicalBatch3;
                                                                                }
                                                                            } catch (Exception e9) {
                                                                                e = e9;
                                                                                maxSizeBytes = maxSizeBytes4;
                                                                                cleanBatch2 = str;
                                                                                cleanFileName3 = respStr;
                                                                                obj = coroutine_suspended;
                                                                                bytes = bytes2;
                                                                                mimeType = mimeType3;
                                                                                cleanFileName4 = cleanFileName2;
                                                                                cleanBatch3 = cleanBatch;
                                                                                fileExt = fileExt4;
                                                                                allowedMimeTypes2 = allowedMimeTypes3;
                                                                                fileExt2 = cleanTitle;
                                                                                canonicalBatch2 = canonicalBatch3;
                                                                            }
                                                                        } catch (Exception e10) {
                                                                            e = e10;
                                                                            cleanBatch2 = str;
                                                                            cleanFileName3 = respStr;
                                                                            obj = coroutine_suspended;
                                                                            bytes = bytes2;
                                                                            mimeType = mimeType3;
                                                                            cleanFileName4 = cleanFileName2;
                                                                            cleanBatch3 = cleanBatch;
                                                                            fileExt = fileExt4;
                                                                            allowedMimeTypes2 = allowedMimeTypes3;
                                                                            fileExt2 = cleanTitle3;
                                                                            canonicalBatch2 = canonicalBatch3;
                                                                        }
                                                                    } catch (Exception e11) {
                                                                        e = e11;
                                                                        cleanFileName = cleanFileName;
                                                                    }
                                                                } catch (Exception e12) {
                                                                    e = e12;
                                                                    cleanFileName = cleanFileName;
                                                                    cleanBatch2 = str;
                                                                    cleanFileName3 = respStr;
                                                                    obj = coroutine_suspended;
                                                                    bytes = bytes2;
                                                                    mimeType = mimeType3;
                                                                    cleanFileName4 = cleanFileName2;
                                                                    cleanBatch3 = cleanBatch4;
                                                                    fileExt = fileExt4;
                                                                    allowedMimeTypes2 = allowedMimeTypes3;
                                                                    fileExt2 = cleanTitle3;
                                                                    canonicalBatch2 = canonicalBatch3;
                                                                }
                                                            } catch (Exception e13) {
                                                                e = e13;
                                                                cleanBatch2 = str;
                                                                cleanFileName3 = respStr;
                                                                obj = coroutine_suspended;
                                                                bytes = bytes2;
                                                                mimeType = mimeType3;
                                                                cleanFileName4 = cleanFileName5;
                                                                cleanBatch3 = cleanBatch4;
                                                                fileExt = fileExt4;
                                                                allowedMimeTypes2 = allowedMimeTypes3;
                                                                fileExt2 = cleanTitle3;
                                                                canonicalBatch2 = canonicalBatch3;
                                                            }
                                                        } catch (Exception e14) {
                                                            e = e14;
                                                            cleanBatch2 = str;
                                                            cleanFileName3 = respStr;
                                                            secureUrl2 = str2;
                                                            obj = coroutine_suspended;
                                                            bytes = bytes2;
                                                            mimeType = mimeType3;
                                                            cleanFileName4 = cleanFileName5;
                                                            cleanBatch3 = cleanBatch4;
                                                            fileExt = fileExt4;
                                                            allowedMimeTypes2 = allowedMimeTypes3;
                                                            fileExt2 = cleanTitle3;
                                                            canonicalBatch2 = canonicalBatch3;
                                                        }
                                                    }
                                                    e.printStackTrace();
                                                    allowedMimeTypes = allowedMimeTypes2;
                                                    mimeType3 = mimeType;
                                                    bytes2 = bytes;
                                                    canonicalBatch = canonicalBatch2;
                                                    cleanTitle2 = fileExt2;
                                                    maxSizeBytes2 = maxSizeBytes;
                                                    str3 = cleanBatch2;
                                                    str4 = cleanFileName3;
                                                    if (bytes2.length <= 921600) {
                                                        int sizeKb4 = bytes2.length / 1024;
                                                        return new Pair(Boxing.boxBoolean(false), "File is " + sizeKb4 + " KB. Please add your Cloudinary Cloud Name & Upload Preset in GoogleDriveStorageService.kt to upload files of any size!");
                                                    }
                                                    String base64Data4 = Base64.encodeToString(bytes2, 2);
                                                    byte[] bytes8 = bytes2;
                                                    String dataUri4 = "data:" + mimeType3 + ";base64," + base64Data4;
                                                    Pair[] pairArr5 = new Pair[8];
                                                    pairArr5[0] = TuplesKt.to("title", cleanTitle2);
                                                    if (this.$isImage) {
                                                        str5 = "Class Photo • Image";
                                                    } else {
                                                        str5 = "Class Notes • PDF Document";
                                                    }
                                                    pairArr5[1] = TuplesKt.to("meta", str5);
                                                    pairArr5[2] = TuplesKt.to("isImage", Boxing.boxBoolean(this.$isImage));
                                                    pairArr5[3] = TuplesKt.to("imageUri", dataUri4);
                                                    pairArr5[4] = TuplesKt.to("serverType", "DirectDocument");
                                                    pairArr5[5] = TuplesKt.to("batchTitle", canonicalBatch);
                                                    String lowerCase6 = canonicalBatch.toLowerCase(Locale.ROOT);
                                                    Intrinsics.checkNotNullExpressionValue(lowerCase6, secureUrl2);
                                                    pairArr5[6] = TuplesKt.to(cleanFileName, lowerCase6);
                                                    pairArr5[7] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
                                                    HashMap uploadData6 = MapsKt.hashMapOf(pairArr5);
                                                    taskAdd = FirebaseFirestore.getInstance().collection("batch_uploads").add(uploadData6);
                                                    Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                                                    this.L$0 = SpillingKt.nullOutSpilledVariable(currentUser);
                                                    this.L$1 = SpillingKt.nullOutSpilledVariable(cleanTitle2);
                                                    this.L$2 = canonicalBatch;
                                                    this.L$3 = SpillingKt.nullOutSpilledVariable(cleanBatch3);
                                                    this.L$4 = SpillingKt.nullOutSpilledVariable(fileExt);
                                                    this.L$5 = SpillingKt.nullOutSpilledVariable(cleanFileName4);
                                                    this.L$6 = SpillingKt.nullOutSpilledVariable(bytes8);
                                                    this.L$7 = SpillingKt.nullOutSpilledVariable(mimeType3);
                                                    this.L$8 = SpillingKt.nullOutSpilledVariable(allowedMimeTypes);
                                                    this.L$9 = SpillingKt.nullOutSpilledVariable(base64Data4);
                                                    this.L$10 = SpillingKt.nullOutSpilledVariable(dataUri4);
                                                    this.L$11 = SpillingKt.nullOutSpilledVariable(uploadData6);
                                                    this.L$12 = null;
                                                    this.L$13 = null;
                                                    this.L$14 = null;
                                                    this.L$15 = null;
                                                    this.L$16 = null;
                                                    this.I$0 = maxSizeBytes2;
                                                    this.label = 2;
                                                    if (TasksKt.await(taskAdd, this) == obj) {
                                                        return obj;
                                                    }
                                                    canonicalBatch5 = canonicalBatch;
                                                }
                                            } catch (Exception e15) {
                                                e = e15;
                                                cleanBatch2 = str;
                                                cleanFileName3 = respStr;
                                                secureUrl2 = str2;
                                                obj = coroutine_suspended;
                                                bytes = bytes2;
                                                mimeType = mimeType3;
                                                cleanFileName4 = cleanFileName5;
                                                cleanBatch3 = cleanBatch4;
                                                fileExt = fileExt4;
                                                allowedMimeTypes2 = allowedMimeTypes3;
                                                fileExt2 = cleanTitle3;
                                                canonicalBatch2 = canonicalBatch7;
                                            }
                                        } else {
                                            cleanFileName2 = cleanFileName5;
                                            cleanBatch = cleanBatch4;
                                            cleanBatch2 = str;
                                            cleanFileName3 = respStr;
                                            canonicalBatch3 = canonicalBatch7;
                                            secureUrl2 = str2;
                                            cleanTitle = cleanTitle3;
                                            obj = coroutine_suspended;
                                            if (!StringsKt.isBlank(respStr3)) {
                                                errorObj = new JSONObject(respStr3).optJSONObject("error");
                                                if (errorObj != null) {
                                                    errMsg = errorObj.optString("message");
                                                    if (errMsg != null) {
                                                        canonicalBatch4 = canonicalBatch3;
                                                    } else {
                                                        canonicalBatch4 = canonicalBatch3;
                                                        errMsg = "Cloudinary error: " + resp.code();
                                                    }
                                                } else {
                                                    canonicalBatch4 = canonicalBatch3;
                                                    errMsg = "Cloudinary error: " + resp.code();
                                                }
                                                return new Pair(Boxing.boxBoolean(false), errMsg);
                                            }
                                            canonicalBatch7 = canonicalBatch3;
                                            e.printStackTrace();
                                            allowedMimeTypes = allowedMimeTypes2;
                                            mimeType3 = mimeType;
                                            bytes2 = bytes;
                                            canonicalBatch = canonicalBatch2;
                                            cleanTitle2 = fileExt2;
                                            maxSizeBytes2 = maxSizeBytes;
                                            str3 = cleanBatch2;
                                            str4 = cleanFileName3;
                                            if (bytes2.length <= 921600) {
                                                int sizeKb5 = bytes2.length / 1024;
                                                return new Pair(Boxing.boxBoolean(false), "File is " + sizeKb5 + " KB. Please add your Cloudinary Cloud Name & Upload Preset in GoogleDriveStorageService.kt to upload files of any size!");
                                            }
                                            String base64Data5 = Base64.encodeToString(bytes2, 2);
                                            byte[] bytes9 = bytes2;
                                            String dataUri5 = "data:" + mimeType3 + ";base64," + base64Data5;
                                            Pair[] pairArr6 = new Pair[8];
                                            pairArr6[0] = TuplesKt.to("title", cleanTitle2);
                                            if (this.$isImage) {
                                                str5 = "Class Photo • Image";
                                            } else {
                                                str5 = "Class Notes • PDF Document";
                                            }
                                            pairArr6[1] = TuplesKt.to("meta", str5);
                                            pairArr6[2] = TuplesKt.to("isImage", Boxing.boxBoolean(this.$isImage));
                                            pairArr6[3] = TuplesKt.to("imageUri", dataUri5);
                                            pairArr6[4] = TuplesKt.to("serverType", "DirectDocument");
                                            pairArr6[5] = TuplesKt.to("batchTitle", canonicalBatch);
                                            String lowerCase7 = canonicalBatch.toLowerCase(Locale.ROOT);
                                            Intrinsics.checkNotNullExpressionValue(lowerCase7, secureUrl2);
                                            pairArr6[6] = TuplesKt.to(cleanFileName, lowerCase7);
                                            pairArr6[7] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
                                            HashMap uploadData7 = MapsKt.hashMapOf(pairArr6);
                                            taskAdd = FirebaseFirestore.getInstance().collection("batch_uploads").add(uploadData7);
                                            Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                                            this.L$0 = SpillingKt.nullOutSpilledVariable(currentUser);
                                            this.L$1 = SpillingKt.nullOutSpilledVariable(cleanTitle2);
                                            this.L$2 = canonicalBatch;
                                            this.L$3 = SpillingKt.nullOutSpilledVariable(cleanBatch3);
                                            this.L$4 = SpillingKt.nullOutSpilledVariable(fileExt);
                                            this.L$5 = SpillingKt.nullOutSpilledVariable(cleanFileName4);
                                            this.L$6 = SpillingKt.nullOutSpilledVariable(bytes9);
                                            this.L$7 = SpillingKt.nullOutSpilledVariable(mimeType3);
                                            this.L$8 = SpillingKt.nullOutSpilledVariable(allowedMimeTypes);
                                            this.L$9 = SpillingKt.nullOutSpilledVariable(base64Data5);
                                            this.L$10 = SpillingKt.nullOutSpilledVariable(dataUri5);
                                            this.L$11 = SpillingKt.nullOutSpilledVariable(uploadData7);
                                            this.L$12 = null;
                                            this.L$13 = null;
                                            this.L$14 = null;
                                            this.L$15 = null;
                                            this.L$16 = null;
                                            this.I$0 = maxSizeBytes2;
                                            this.label = 2;
                                            if (TasksKt.await(taskAdd, this) == obj) {
                                                return obj;
                                            }
                                            canonicalBatch5 = canonicalBatch;
                                        }
                                    } catch (Exception e16) {
                                        e = e16;
                                        cleanBatch2 = str;
                                        cleanFileName3 = respStr;
                                        secureUrl2 = str2;
                                        obj = coroutine_suspended;
                                        bytes = bytes2;
                                        mimeType = mimeType3;
                                        cleanFileName4 = cleanFileName5;
                                        cleanBatch3 = cleanBatch4;
                                        fileExt = fileExt4;
                                        allowedMimeTypes2 = allowedMimeTypes3;
                                        fileExt2 = cleanTitle3;
                                        canonicalBatch2 = canonicalBatch7;
                                    }
                                } catch (Exception e17) {
                                    e = e17;
                                    cleanBatch2 = str;
                                    cleanFileName3 = respStr;
                                    secureUrl2 = str2;
                                    obj = coroutine_suspended;
                                    bytes = bytes4;
                                    mimeType = mimeType3;
                                    cleanFileName4 = cleanFileName5;
                                    cleanBatch3 = cleanBatch4;
                                    fileExt = fileExt4;
                                    allowedMimeTypes2 = allowedMimeTypes3;
                                    fileExt2 = cleanTitle3;
                                    canonicalBatch2 = canonicalBatch7;
                                }
                                break;
                            } catch (Exception e18) {
                                e = e18;
                                cleanBatch2 = str;
                                cleanFileName3 = respStr;
                                secureUrl2 = str2;
                                obj = coroutine_suspended;
                                mimeType = mimeType3;
                                cleanFileName4 = cleanFileName5;
                                cleanBatch3 = cleanBatch4;
                                fileExt = fileExt4;
                                allowedMimeTypes2 = allowedMimeTypes3;
                                fileExt2 = cleanTitle3;
                                canonicalBatch2 = canonicalBatch7;
                            }
                            return new Pair(Boxing.boxBoolean(true), str4 + canonicalBatch5 + str3);
                        }
                        cleanFileName2 = cleanFileName5;
                        cleanBatch = cleanBatch4;
                        cleanBatch2 = str;
                        cleanFileName3 = respStr;
                        bytes2 = bytes;
                        secureUrl2 = str2;
                        cleanTitle = cleanTitle3;
                        obj = coroutine_suspended;
                        canonicalBatch = canonicalBatch7;
                        cleanFileName4 = cleanFileName2;
                        cleanBatch3 = cleanBatch;
                        fileExt = fileExt4;
                        allowedMimeTypes = allowedMimeTypes3;
                        cleanTitle2 = cleanTitle;
                        maxSizeBytes2 = maxSizeBytes;
                        str3 = cleanBatch2;
                        str4 = cleanFileName3;
                        if (bytes2.length <= 921600) {
                            int sizeKb6 = bytes2.length / 1024;
                            return new Pair(Boxing.boxBoolean(false), "File is " + sizeKb6 + " KB. Please add your Cloudinary Cloud Name & Upload Preset in GoogleDriveStorageService.kt to upload files of any size!");
                        }
                        String base64Data6 = Base64.encodeToString(bytes2, 2);
                        byte[] bytes10 = bytes2;
                        String dataUri6 = "data:" + mimeType3 + ";base64," + base64Data6;
                        Pair[] pairArr7 = new Pair[8];
                        pairArr7[0] = TuplesKt.to("title", cleanTitle2);
                        if (this.$isImage) {
                            str5 = "Class Photo • Image";
                        } else {
                            str5 = "Class Notes • PDF Document";
                        }
                        pairArr7[1] = TuplesKt.to("meta", str5);
                        pairArr7[2] = TuplesKt.to("isImage", Boxing.boxBoolean(this.$isImage));
                        pairArr7[3] = TuplesKt.to("imageUri", dataUri6);
                        pairArr7[4] = TuplesKt.to("serverType", "DirectDocument");
                        pairArr7[5] = TuplesKt.to("batchTitle", canonicalBatch);
                        String lowerCase8 = canonicalBatch.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase8, secureUrl2);
                        pairArr7[6] = TuplesKt.to(cleanFileName, lowerCase8);
                        pairArr7[7] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
                        HashMap uploadData8 = MapsKt.hashMapOf(pairArr7);
                        taskAdd = FirebaseFirestore.getInstance().collection("batch_uploads").add(uploadData8);
                        Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable(currentUser);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(cleanTitle2);
                        this.L$2 = canonicalBatch;
                        this.L$3 = SpillingKt.nullOutSpilledVariable(cleanBatch3);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(fileExt);
                        this.L$5 = SpillingKt.nullOutSpilledVariable(cleanFileName4);
                        this.L$6 = SpillingKt.nullOutSpilledVariable(bytes10);
                        this.L$7 = SpillingKt.nullOutSpilledVariable(mimeType3);
                        this.L$8 = SpillingKt.nullOutSpilledVariable(allowedMimeTypes);
                        this.L$9 = SpillingKt.nullOutSpilledVariable(base64Data6);
                        this.L$10 = SpillingKt.nullOutSpilledVariable(dataUri6);
                        this.L$11 = SpillingKt.nullOutSpilledVariable(uploadData8);
                        this.L$12 = null;
                        this.L$13 = null;
                        this.L$14 = null;
                        this.L$15 = null;
                        this.L$16 = null;
                        this.I$0 = maxSizeBytes2;
                        this.label = 2;
                        if (TasksKt.await(taskAdd, this) == obj) {
                            return obj;
                        }
                        canonicalBatch5 = canonicalBatch;
                        return new Pair(Boxing.boxBoolean(true), str4 + canonicalBatch5 + str3);
                    case 1:
                        int maxSizeBytes5 = this.I$0;
                        HashMap uploadData9 = (HashMap) this.L$16;
                        secureUrl = (String) this.L$15;
                        json = (JSONObject) this.L$14;
                        String respStr4 = (String) this.L$13;
                        Request req3 = (Request) this.L$11;
                        allowedMimeTypes2 = (List) this.L$8;
                        mimeType = (String) this.L$7;
                        bytes = (byte[]) this.L$6;
                        cleanFileName4 = (String) this.L$5;
                        fileExt = (String) this.L$4;
                        cleanBatch3 = (String) this.L$3;
                        canonicalBatch2 = (String) this.L$2;
                        fileExt2 = (String) this.L$1;
                        currentUser = (FirebaseUser) this.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            maxSizeBytes = maxSizeBytes5;
                            req = req3;
                            obj = coroutine_suspended;
                            cleanFileName = "batchKey";
                            uploadData = uploadData9;
                            respStr2 = respStr4;
                            str = "!";
                            respStr = "Document uploaded exclusively to ";
                            bytes2 = bytes;
                            mimeType2 = canonicalBatch2;
                            cleanFileName3 = respStr;
                            cleanBatch2 = str;
                            return new Pair(Boxing.boxBoolean(true), cleanFileName3 + mimeType2 + cleanBatch2);
                        } catch (Exception e19) {
                            e = e19;
                            maxSizeBytes = maxSizeBytes5;
                            cleanFileName = "batchKey";
                            cleanBatch2 = "!";
                            cleanFileName3 = "Document uploaded exclusively to ";
                            obj = coroutine_suspended;
                        }
                        break;
                    case 2:
                        maxSizeBytes2 = this.I$0;
                        canonicalBatch5 = (String) this.L$2;
                        ResultKt.throwOnFailure($result);
                        str3 = "!";
                        str4 = "Document uploaded exclusively to ";
                        return new Pair(Boxing.boxBoolean(true), str4 + canonicalBatch5 + str3);
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } catch (Exception e20) {
                e20.printStackTrace();
                Boolean boolBoxBoolean = Boxing.boxBoolean(false);
                String localizedMessage = e20.getLocalizedMessage();
                if (localizedMessage == null) {
                    localizedMessage = "Unknown error";
                }
                return new Pair(boolBoxBoolean, "Upload error: " + localizedMessage);
            }
        }
    }

    public final Object uploadToGoogleDriveServer(Context context, Uri fileUri, String title, String batchTitle, boolean isImage, Continuation<? super Pair<Boolean, String>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C04292(title, batchTitle, isImage, context, fileUri, null), continuation);
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.GoogleDriveStorageService$registerGoogleDriveLinkForBatch$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GoogleDriveStorageService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.GoogleDriveStorageService$registerGoogleDriveLinkForBatch$2", f = "GoogleDriveStorageService.kt", i = {0, 0, 0, 0, 0}, l = {345}, m = "invokeSuspend", n = {"cleanTitle", "canonicalBatch", "driveFileId", "downloadUrl", "uploadData"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    static final class C04282 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Boolean, ? extends String>>, Object> {
        final /* synthetic */ String $batchTitle;
        final /* synthetic */ String $driveUrlOrId;
        final /* synthetic */ boolean $isImage;
        final /* synthetic */ String $title;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04282(String str, String str2, String str3, boolean z, Continuation<? super C04282> continuation) {
            super(2, continuation);
            this.$title = str;
            this.$batchTitle = str2;
            this.$driveUrlOrId = str3;
            this.$isImage = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04282(this.$title, this.$batchTitle, this.$driveUrlOrId, this.$isImage, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Boolean, ? extends String>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super Pair<Boolean, String>>) continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Pair<Boolean, String>> continuation) {
            return ((C04282) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            String canonicalBatch;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        String cleanTitle = StringsKt.trim((CharSequence) this.$title).toString();
                        String canonicalBatch2 = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(StringsKt.trim((CharSequence) this.$batchTitle).toString(), "(", (String) null, 2, (Object) null)).toString();
                        String driveFileId = GoogleDriveStorageService.INSTANCE.extractDriveFileId(this.$driveUrlOrId);
                        String str = driveFileId;
                        String downloadUrl = str == null || StringsKt.isBlank(str) ? StringsKt.trim((CharSequence) this.$driveUrlOrId).toString() : this.$isImage ? GoogleDriveStorageService.INSTANCE.getGoogleDriveThumbnailUrl(driveFileId) : GoogleDriveStorageService.INSTANCE.getGoogleDriveDirectDownloadUrl(driveFileId);
                        Pair[] pairArr = new Pair[9];
                        pairArr[0] = TuplesKt.to("title", cleanTitle);
                        pairArr[1] = TuplesKt.to("meta", this.$isImage ? "Google Drive Photo • Image" : "Google Drive Document • PDF");
                        pairArr[2] = TuplesKt.to("isImage", Boxing.boxBoolean(this.$isImage));
                        pairArr[3] = TuplesKt.to("imageUri", downloadUrl);
                        pairArr[4] = TuplesKt.to("driveFileId", driveFileId == null ? "" : driveFileId);
                        pairArr[5] = TuplesKt.to("serverType", "GoogleDriveLink");
                        pairArr[6] = TuplesKt.to("batchTitle", canonicalBatch2);
                        String lowerCase = canonicalBatch2.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                        pairArr[7] = TuplesKt.to("batchKey", lowerCase);
                        pairArr[8] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
                        HashMap uploadData = MapsKt.hashMapOf(pairArr);
                        Task<DocumentReference> taskAdd = FirebaseFirestore.getInstance().collection("batch_uploads").add(uploadData);
                        Intrinsics.checkNotNullExpressionValue(taskAdd, "add(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable(cleanTitle);
                        this.L$1 = canonicalBatch2;
                        this.L$2 = SpillingKt.nullOutSpilledVariable(driveFileId);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(downloadUrl);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(uploadData);
                        this.label = 1;
                        if (TasksKt.await(taskAdd, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        canonicalBatch = canonicalBatch2;
                        break;
                    case 1:
                        canonicalBatch = (String) this.L$1;
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return new Pair(Boxing.boxBoolean(true), "Document linked to " + canonicalBatch + " successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                Boolean boolBoxBoolean = Boxing.boxBoolean(false);
                String localizedMessage = e.getLocalizedMessage();
                if (localizedMessage == null) {
                    localizedMessage = "Please try again";
                }
                return new Pair(boolBoxBoolean, "Failed to link file: " + localizedMessage);
            }
        }
    }

    public final Object registerGoogleDriveLinkForBatch(String title, String driveUrlOrId, String batchTitle, boolean isImage, Continuation<? super Pair<Boolean, String>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C04282(title, batchTitle, driveUrlOrId, isImage, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Uri saveToPublicDownloads(Context context, String fileName, boolean isImage, byte[] bytes) {
        OutputStream outputStreamOpenOutputStream;
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                String mime = (isImage || StringsKt.endsWith$default(fileName, ".jpg", false, 2, (Object) null) || StringsKt.endsWith$default(fileName, ".jpeg", false, 2, (Object) null) || StringsKt.endsWith$default(fileName, ".png", false, 2, (Object) null)) ? "image/jpeg" : "application/pdf";
                ContentValues contentValues = new ContentValues();
                contentValues.put("_display_name", fileName);
                contentValues.put("mime_type", mime);
                contentValues.put("relative_path", Environment.DIRECTORY_DOWNLOADS + "/SushikshaAcademy");
                ContentResolver resolver = context.getContentResolver();
                Uri uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
                if (uri != null && (outputStreamOpenOutputStream = resolver.openOutputStream(uri)) != null) {
                    OutputStream outputStream = outputStreamOpenOutputStream;
                    try {
                        outputStream.write(bytes);
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(outputStream, null);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(outputStream, th);
                            throw th2;
                        }
                    }
                }
                return uri;
            }
            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(downloadsDir, "SushikshaAcademy");
            file.mkdirs();
            File targetFile = new File(file, fileName);
            FilesKt.writeBytes(targetFile, bytes);
            return FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v14, types: [T, java.lang.String] */
    public final void downloadFromGoogleDriveServer(Context context, String title, String fileUrlOrData, boolean isImage) {
        String string;
        String strExtractDriveFileId;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(fileUrlOrData, "fileUrlOrData");
        try {
            String strReplace = new Regex("[^a-zA-Z0-9_.-]").replace(StringsKt.trim((CharSequence) title).toString(), "_");
            if (StringsKt.contains$default((CharSequence) strReplace, (CharSequence) ".", false, 2, (Object) null)) {
                string = strReplace;
            } else {
                string = (isImage ? new StringBuilder().append(strReplace).append(".jpg") : new StringBuilder().append(strReplace).append(".pdf")).toString();
            }
            if (StringsKt.isBlank(fileUrlOrData)) {
                Toast.makeText(context, "Download link is empty.", 0).show();
                return;
            }
            if (StringsKt.startsWith$default(fileUrlOrData, "data:", false, 2, (Object) null)) {
                byte[] bArrDecode = Base64.decode(StringsKt.substringAfter$default(fileUrlOrData, "base64,", (String) null, 2, (Object) null), 0);
                Intrinsics.checkNotNull(bArrDecode);
                Uri uriSaveToPublicDownloads = saveToPublicDownloads(context, string, isImage, bArrDecode);
                if (uriSaveToPublicDownloads == null) {
                    Toast.makeText(context, "Failed to save file to Downloads.", 0).show();
                    return;
                }
                Toast.makeText(context, "Saved " + string + " to Downloads/SushikshaAcademy!", 1).show();
                String str = (isImage || StringsKt.endsWith$default(string, ".jpg", false, 2, (Object) null) || StringsKt.endsWith$default(string, ".jpeg", false, 2, (Object) null) || StringsKt.endsWith$default(string, ".png", false, 2, (Object) null)) ? "image/*" : "application/pdf";
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(uriSaveToPublicDownloads, str);
                intent.addFlags(1);
                intent.addFlags(268435456);
                context.startActivity(Intent.createChooser(intent, "Open Study Material"));
                return;
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = (!StringsKt.contains$default((CharSequence) fileUrlOrData, (CharSequence) "drive.google.com", false, 2, (Object) null) || (strExtractDriveFileId = extractDriveFileId(fileUrlOrData)) == null) ? fileUrlOrData : getGoogleDriveDirectDownloadUrl(strExtractDriveFileId);
            if (StringsKt.startsWith$default((String) objectRef.element, "http://", false, 2, (Object) null)) {
                objectRef.element = StringsKt.replace$default((String) objectRef.element, "http://", "https://", false, 4, (Object) null);
            }
            Toast.makeText(context, "Downloading " + string + "...", 0).show();
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(objectRef, context, string, isImage, null), 3, null);
        } catch (Exception e) {
            Toast.makeText(context, "Download error: " + e.getLocalizedMessage(), 0).show();
        }
    }

    /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.GoogleDriveStorageService$downloadFromGoogleDriveServer$1, reason: invalid class name */
    /* JADX INFO: compiled from: GoogleDriveStorageService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.sushikhasacademyapp.data.GoogleDriveStorageService$downloadFromGoogleDriveServer$1", f = "GoogleDriveStorageService.kt", i = {0, 0, 1, 1, 1}, l = {466, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND}, m = "invokeSuspend", n = {"savedUri", "errorDetail", "savedUri", "errorDetail", "finalErr"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Ref.ObjectRef<String> $directUrl;
        final /* synthetic */ String $fileName;
        final /* synthetic */ boolean $isImage;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.ObjectRef<String> objectRef, Context context, String str, boolean z, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$directUrl = objectRef;
            this.$context = context;
            this.$fileName = str;
            this.$isImage = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$directUrl, this.$context, this.$fileName, this.$isImage, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r5v15, types: [T, android.net.Uri] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            String errorDetail;
            String errorDetail2;
            Ref.ObjectRef savedUri;
            String str;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Ref.ObjectRef savedUri2 = new Ref.ObjectRef();
                    try {
                        Request req = new Request.Builder().url(this.$directUrl.element).header(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36").build();
                        Response response = GoogleDriveStorageService.client.newCall(req).execute();
                        if (response.isSuccessful() && response.body() != null) {
                            ResponseBody responseBodyBody = response.body();
                            Intrinsics.checkNotNull(responseBodyBody);
                            byte[] bytes = responseBodyBody.bytes();
                            if (!(bytes.length == 0)) {
                                savedUri2.element = GoogleDriveStorageService.INSTANCE.saveToPublicDownloads(this.$context, this.$fileName, this.$isImage, bytes);
                                errorDetail = null;
                            } else {
                                errorDetail = "Downloaded file was empty";
                            }
                        } else {
                            int code = response.code();
                            switch (code) {
                                case TypedValues.CycleType.TYPE_ALPHA /* 403 */:
                                case 404:
                                    str = "Cloudinary returned HTTP " + code + " (Check Cloudinary -> Settings -> Security -> uncheck 'PDF and ZIP delivery restriction')";
                                    break;
                                default:
                                    str = "HTTP " + code + " " + response.message();
                                    break;
                            }
                            errorDetail = str;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        String localizedMessage = e.getLocalizedMessage();
                        if (localizedMessage == null) {
                            localizedMessage = "Connection error";
                        }
                        errorDetail = localizedMessage;
                    }
                    if (savedUri2.element != 0) {
                        this.L$0 = SpillingKt.nullOutSpilledVariable(savedUri2);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(errorDetail);
                        this.label = 1;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new C00991(this.$context, this.$fileName, this.$isImage, savedUri2, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        String finalErr = errorDetail == null ? "Download failed" : errorDetail;
                        this.L$0 = SpillingKt.nullOutSpilledVariable(savedUri2);
                        this.L$1 = SpillingKt.nullOutSpilledVariable(errorDetail);
                        this.L$2 = SpillingKt.nullOutSpilledVariable(finalErr);
                        this.label = 2;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.$context, finalErr, this.$directUrl, this.$fileName, this.$isImage, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        errorDetail2 = errorDetail;
                        savedUri = savedUri2;
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 2:
                    errorDetail2 = (String) this.L$1;
                    savedUri = (Ref.ObjectRef) this.L$0;
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.GoogleDriveStorageService$downloadFromGoogleDriveServer$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: GoogleDriveStorageService.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.GoogleDriveStorageService$downloadFromGoogleDriveServer$1$1", f = "GoogleDriveStorageService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00991 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Context $context;
            final /* synthetic */ String $fileName;
            final /* synthetic */ boolean $isImage;
            final /* synthetic */ Ref.ObjectRef<Uri> $savedUri;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00991(Context context, String str, boolean z, Ref.ObjectRef<Uri> objectRef, Continuation<? super C00991> continuation) {
                super(2, continuation);
                this.$context = context;
                this.$fileName = str;
                this.$isImage = z;
                this.$savedUri = objectRef;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00991(this.$context, this.$fileName, this.$isImage, this.$savedUri, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00991) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        Toast.makeText(this.$context, "Saved " + this.$fileName + " to Downloads/SushikshaAcademy!", 1).show();
                        try {
                            String mime = (this.$isImage || StringsKt.endsWith$default(this.$fileName, ".jpg", false, 2, (Object) null) || StringsKt.endsWith$default(this.$fileName, ".jpeg", false, 2, (Object) null) || StringsKt.endsWith$default(this.$fileName, ".png", false, 2, (Object) null)) ? "image/*" : "application/pdf";
                            Intent openIntent = new Intent("android.intent.action.VIEW");
                            openIntent.setDataAndType(this.$savedUri.element, mime);
                            openIntent.addFlags(1);
                            openIntent.addFlags(268435456);
                            this.$context.startActivity(Intent.createChooser(openIntent, "Open Study Material"));
                            break;
                        } catch (Exception e) {
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        /* JADX INFO: renamed from: com.example.sushikhasacademyapp.data.GoogleDriveStorageService$downloadFromGoogleDriveServer$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: GoogleDriveStorageService.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.example.sushikhasacademyapp.data.GoogleDriveStorageService$downloadFromGoogleDriveServer$1$2", f = "GoogleDriveStorageService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Context $context;
            final /* synthetic */ Ref.ObjectRef<String> $directUrl;
            final /* synthetic */ String $fileName;
            final /* synthetic */ String $finalErr;
            final /* synthetic */ boolean $isImage;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(Context context, String str, Ref.ObjectRef<String> objectRef, String str2, boolean z, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$context = context;
                this.$finalErr = str;
                this.$directUrl = objectRef;
                this.$fileName = str2;
                this.$isImage = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$context, this.$finalErr, this.$directUrl, this.$fileName, this.$isImage, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                String str;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        Toast.makeText(this.$context, "Download failed: " + this.$finalErr, 1).show();
                        try {
                            File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                            new File(downloadsDir, "SushikshaAcademy").mkdirs();
                            Uri uri = Uri.parse(this.$directUrl.element);
                            Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            String str2 = this.$fileName;
                            boolean z = this.$isImage;
                            request.setTitle("Downloading " + str2);
                            request.setDescription("Sushiksha English Academy Study Material");
                            request.setNotificationVisibility(1);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "SushikshaAcademy/" + str2);
                            request.addRequestHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36");
                            if (z || StringsKt.endsWith$default(str2, ".jpg", false, 2, (Object) null) || StringsKt.endsWith$default(str2, ".jpeg", false, 2, (Object) null) || StringsKt.endsWith$default(str2, ".png", false, 2, (Object) null)) {
                                str = "image/*";
                            } else {
                                str = StringsKt.endsWith$default(str2, ".pdf", false, 2, (Object) null) ? "application/pdf" : "application/octet-stream";
                            }
                            request.setMimeType(str);
                            request.setAllowedOverMetered(true);
                            request.setAllowedOverRoaming(true);
                            Object systemService = this.$context.getSystemService(ArchiveParams.MODE_DOWNLOAD);
                            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.DownloadManager");
                            DownloadManager manager = (DownloadManager) systemService;
                            manager.enqueue(request);
                            break;
                        } catch (Exception e) {
                        }
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }
}
