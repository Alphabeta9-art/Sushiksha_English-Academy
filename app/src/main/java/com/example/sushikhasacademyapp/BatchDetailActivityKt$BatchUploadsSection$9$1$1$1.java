package com.example.sushikhasacademyapp;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.example.sushikhasacademyapp.data.GoogleDriveStorageService;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchUploadsSection$9$1$1$1", f = "BatchDetailActivity.kt", i = {0}, l = {703, 711}, m = "invokeSuspend", n = {"fileUri"}, s = {"L$0"})
final class BatchDetailActivityKt$BatchUploadsSection$9$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $batchTitle;
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<String> $driveLinkInput$delegate;
    final /* synthetic */ boolean $isImg;
    final /* synthetic */ MutableState<Boolean> $isUploading$delegate;
    final /* synthetic */ MutableState<Uri> $selectedFileUri$delegate;
    final /* synthetic */ MutableState<Boolean> $showUploadDialog$delegate;
    final /* synthetic */ String $titleText;
    final /* synthetic */ MutableIntState $uploadModeIndex$delegate;
    final /* synthetic */ MutableState<String> $uploadTitleInput$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchUploadsSection$9$1$1$1(Context context, String str, String str2, boolean z, MutableIntState mutableIntState, MutableState<Uri> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, MutableState<String> mutableState5, Continuation<? super BatchDetailActivityKt$BatchUploadsSection$9$1$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$titleText = str;
        this.$batchTitle = str2;
        this.$isImg = z;
        this.$uploadModeIndex$delegate = mutableIntState;
        this.$selectedFileUri$delegate = mutableState;
        this.$driveLinkInput$delegate = mutableState2;
        this.$isUploading$delegate = mutableState3;
        this.$showUploadDialog$delegate = mutableState4;
        this.$uploadTitleInput$delegate = mutableState5;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchUploadsSection$9$1$1$1(this.$context, this.$titleText, this.$batchTitle, this.$isImg, this.$uploadModeIndex$delegate, this.$selectedFileUri$delegate, this.$driveLinkInput$delegate, this.$isUploading$delegate, this.$showUploadDialog$delegate, this.$uploadTitleInput$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchUploadsSection$9$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0096  */
    /* JADX WARN: Code duplicated, block: B:25:0x00ba  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objRegisterGoogleDriveLinkForBatch;
        Object objUploadToGoogleDriveServer;
        Pair pair;
        boolean success;
        String message;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (BatchDetailActivityKt.BatchUploadsSection$lambda$74(this.$uploadModeIndex$delegate) == 0) {
                    Uri fileUri = BatchDetailActivityKt.BatchUploadsSection$lambda$62(this.$selectedFileUri$delegate);
                    if (fileUri == null) {
                        return Unit.INSTANCE;
                    }
                    this.L$0 = SpillingKt.nullOutSpilledVariable(fileUri);
                    this.label = 1;
                    objUploadToGoogleDriveServer = GoogleDriveStorageService.INSTANCE.uploadToGoogleDriveServer(this.$context, fileUri, this.$titleText, this.$batchTitle, this.$isImg, this);
                    if (objUploadToGoogleDriveServer == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    pair = (Pair) objUploadToGoogleDriveServer;
                    success = ((Boolean) pair.component1()).booleanValue();
                    message = (String) pair.component2();
                    BatchDetailActivityKt.BatchUploadsSection$lambda$100(this.$isUploading$delegate, false);
                    if (success) {
                        BatchDetailActivityKt.BatchUploadsSection$lambda$47(this.$showUploadDialog$delegate, false);
                        this.$uploadTitleInput$delegate.setValue("");
                        this.$selectedFileUri$delegate.setValue(null);
                        this.$driveLinkInput$delegate.setValue("");
                        Toast.makeText(this.$context, message, 0).show();
                    } else {
                        Toast.makeText(this.$context, message, 1).show();
                    }
                    return Unit.INSTANCE;
                }
                this.label = 2;
                objRegisterGoogleDriveLinkForBatch = GoogleDriveStorageService.INSTANCE.registerGoogleDriveLinkForBatch(this.$titleText, StringsKt.trim((CharSequence) BatchDetailActivityKt.BatchUploadsSection$lambda$71(this.$driveLinkInput$delegate)).toString(), this.$batchTitle, this.$isImg, this);
                if (objRegisterGoogleDriveLinkForBatch == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pair = (Pair) objRegisterGoogleDriveLinkForBatch;
                success = ((Boolean) pair.component1()).booleanValue();
                message = (String) pair.component2();
                BatchDetailActivityKt.BatchUploadsSection$lambda$100(this.$isUploading$delegate, false);
                if (success) {
                    BatchDetailActivityKt.BatchUploadsSection$lambda$47(this.$showUploadDialog$delegate, false);
                    this.$uploadTitleInput$delegate.setValue("");
                    this.$selectedFileUri$delegate.setValue(null);
                    this.$driveLinkInput$delegate.setValue("");
                    Toast.makeText(this.$context, message, 0).show();
                } else {
                    Toast.makeText(this.$context, message, 1).show();
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                objUploadToGoogleDriveServer = $result;
                pair = (Pair) objUploadToGoogleDriveServer;
                success = ((Boolean) pair.component1()).booleanValue();
                message = (String) pair.component2();
                BatchDetailActivityKt.BatchUploadsSection$lambda$100(this.$isUploading$delegate, false);
                if (success) {
                    BatchDetailActivityKt.BatchUploadsSection$lambda$47(this.$showUploadDialog$delegate, false);
                    this.$uploadTitleInput$delegate.setValue("");
                    this.$selectedFileUri$delegate.setValue(null);
                    this.$driveLinkInput$delegate.setValue("");
                    Toast.makeText(this.$context, message, 0).show();
                } else {
                    Toast.makeText(this.$context, message, 1).show();
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                objRegisterGoogleDriveLinkForBatch = $result;
                pair = (Pair) objRegisterGoogleDriveLinkForBatch;
                success = ((Boolean) pair.component1()).booleanValue();
                message = (String) pair.component2();
                BatchDetailActivityKt.BatchUploadsSection$lambda$100(this.$isUploading$delegate, false);
                if (success) {
                    BatchDetailActivityKt.BatchUploadsSection$lambda$47(this.$showUploadDialog$delegate, false);
                    this.$uploadTitleInput$delegate.setValue("");
                    this.$selectedFileUri$delegate.setValue(null);
                    this.$driveLinkInput$delegate.setValue("");
                    Toast.makeText(this.$context, message, 0).show();
                } else {
                    Toast.makeText(this.$context, message, 1).show();
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
