package com.example.sushikhasacademyapp;

import androidx.compose.runtime.MutableState;
import com.example.sushikhasacademyapp.data.GoogleDriveStorageService;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchUploadsSection$1$1", f = "BatchDetailActivity.kt", i = {0}, l = {448}, m = "invokeSuspend", n = {"canonicalCurrentBatch"}, s = {"L$0"})
final class BatchDetailActivityKt$BatchUploadsSection$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $batchTitle;
    final /* synthetic */ MutableState<Boolean> $isLoadingDocuments$delegate;
    final /* synthetic */ MutableState<List<UploadItem>> $uploadsList$delegate;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchUploadsSection$1$1(String str, MutableState<Boolean> mutableState, MutableState<List<UploadItem>> mutableState2, Continuation<? super BatchDetailActivityKt$BatchUploadsSection$1$1> continuation) {
        super(2, continuation);
        this.$batchTitle = str;
        this.$isLoadingDocuments$delegate = mutableState;
        this.$uploadsList$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchUploadsSection$1$1(this.$batchTitle, this.$isLoadingDocuments$delegate, this.$uploadsList$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchUploadsSection$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objFetchFilesForBatch;
        final String canonicalCurrentBatch;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    BatchDetailActivityKt.BatchUploadsSection$lambda$59(this.$isLoadingDocuments$delegate, true);
                    String canonicalCurrentBatch2 = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(StringsKt.trim((CharSequence) this.$batchTitle).toString(), "(", (String) null, 2, (Object) null)).toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(canonicalCurrentBatch2, "toLowerCase(...)");
                    this.L$0 = canonicalCurrentBatch2;
                    this.label = 1;
                    objFetchFilesForBatch = GoogleDriveStorageService.INSTANCE.fetchFilesForBatch(this.$batchTitle, this);
                    if (objFetchFilesForBatch == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    canonicalCurrentBatch = canonicalCurrentBatch2;
                    break;
                    break;
                case 1:
                    canonicalCurrentBatch = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objFetchFilesForBatch = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            final List batchDriveFiles = (List) objFetchFilesForBatch;
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
            CollectionReference collectionReferenceCollection = db.collection("batch_uploads");
            final MutableState<List<UploadItem>> mutableState = this.$uploadsList$delegate;
            final MutableState<Boolean> mutableState2 = this.$isLoadingDocuments$delegate;
            Intrinsics.checkNotNull(collectionReferenceCollection.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchUploadsSection$1$1$$ExternalSyntheticLambda0
                @Override // com.google.firebase.firestore.EventListener
                public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                    BatchDetailActivityKt$BatchUploadsSection$1$1.invokeSuspend$lambda$3(batchDriveFiles, canonicalCurrentBatch, mutableState, mutableState2, (QuerySnapshot) obj, firebaseFirestoreException);
                }
            }));
        } catch (Exception e) {
            BatchDetailActivityKt.BatchUploadsSection$lambda$59(this.$isLoadingDocuments$delegate, false);
        }
        return Unit.INSTANCE;
    }

    static final void invokeSuspend$lambda$3(List $batchDriveFiles, String $canonicalCurrentBatch, MutableState $uploadsList$delegate, MutableState $isLoadingDocuments$delegate, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        UploadItem uploadItem;
        List remoteUploads = new ArrayList();
        if (snapshot != null) {
            Iterable documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Iterable iterable = documents;
            int i = 0;
            Collection arrayList = new ArrayList();
            Iterable<DocumentSnapshot> iterable2 = iterable;
            for (DocumentSnapshot documentSnapshot : iterable2) {
                String string = documentSnapshot.getString("batchTitle");
                if (string == null) {
                    string = "";
                }
                Iterable iterable3 = iterable;
                int i2 = i;
                Iterable iterable4 = iterable2;
                String lowerCase = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(StringsKt.trim((CharSequence) string).toString(), "(", (String) null, 2, (Object) null)).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String string2 = documentSnapshot.getString("batchKey");
                String lowerCase2 = StringsKt.trim((CharSequence) (string2 != null ? string2 : "")).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                if ((!StringsKt.isBlank(lowerCase2) && Intrinsics.areEqual(lowerCase2, $canonicalCurrentBatch)) || (!StringsKt.isBlank(lowerCase) && Intrinsics.areEqual(lowerCase, $canonicalCurrentBatch))) {
                    String id = documentSnapshot.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                    String string3 = documentSnapshot.getString("title");
                    if (string3 == null) {
                        string3 = "Study Material";
                    }
                    String str = string3;
                    String string4 = documentSnapshot.getString("meta");
                    if (string4 == null) {
                        string4 = "Uploaded Document";
                    }
                    String str2 = string4;
                    Boolean bool = documentSnapshot.getBoolean("isImage");
                    uploadItem = new UploadItem(id, str, str2, bool != null ? bool.booleanValue() : false, documentSnapshot.getString("imageUri"));
                } else {
                    uploadItem = null;
                }
                if (uploadItem != null) {
                    arrayList.add(uploadItem);
                }
                iterable = iterable3;
                i = i2;
                iterable2 = iterable4;
            }
            List firestoreItems = (List) arrayList;
            remoteUploads.addAll(firestoreItems);
        }
        Iterable iterablePlus = CollectionsKt.plus((Collection) $batchDriveFiles, (Iterable) remoteUploads);
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : iterablePlus) {
            UploadItem uploadItem2 = (UploadItem) obj;
            String id2 = uploadItem2.getId();
            if (StringsKt.isBlank(id2)) {
                id2 = uploadItem2.getTitle();
            }
            if (hashSet.add(id2)) {
                arrayList2.add(obj);
            }
        }
        ArrayList combined = arrayList2;
        $uploadsList$delegate.setValue(combined);
        BatchDetailActivityKt.BatchUploadsSection$lambda$59($isLoadingDocuments$delegate, false);
    }
}
