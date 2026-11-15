package com.example.sushikhasacademyapp;

import androidx.compose.runtime.ComposerImplKt;
import androidx.compose.runtime.MutableState;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.cloudinary.provisioning.Account;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.tasks.TasksKt;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchDetailScreen$1$1", f = "BatchDetailActivity.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2}, l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, ComposerImplKt.nodeKey, 133}, m = "invokeSuspend", n = {"canonicalTarget", "checkEnrollmentInDoc", "db", "canonicalTarget", "checkEnrollmentInDoc", "db", "docSnap", "canonicalTarget", "checkEnrollmentInDoc", "db", "docSnap", "emailSnap"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"})
final class BatchDetailActivityKt$BatchDetailScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $batchTitle;
    final /* synthetic */ boolean $isAdmin;
    final /* synthetic */ MutableState<Boolean> $isCheckingEnrollment$delegate;
    final /* synthetic */ MutableState<Boolean> $isEnrolled$delegate;
    final /* synthetic */ String $studentUid;
    final /* synthetic */ String $userEmail;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchDetailScreen$1$1(boolean z, String str, String str2, String str3, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, Continuation<? super BatchDetailActivityKt$BatchDetailScreen$1$1> continuation) {
        super(2, continuation);
        this.$isAdmin = z;
        this.$batchTitle = str;
        this.$studentUid = str2;
        this.$userEmail = str3;
        this.$isEnrolled$delegate = mutableState;
        this.$isCheckingEnrollment$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchDetailScreen$1$1(this.$isAdmin, this.$batchTitle, this.$studentUid, this.$userEmail, this.$isEnrolled$delegate, this.$isCheckingEnrollment$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchDetailScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00e5 A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0036, B:45:0x01d0, B:47:0x01d8, B:49:0x01f2, B:52:0x0203, B:10:0x004e, B:35:0x0151, B:37:0x0159, B:39:0x0173, B:41:0x0180, B:13:0x0064, B:23:0x00dd, B:25:0x00e5, B:27:0x00f4, B:29:0x0101, B:31:0x010b, B:20:0x007f), top: B:58:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:27:0x00f4 A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0036, B:45:0x01d0, B:47:0x01d8, B:49:0x01f2, B:52:0x0203, B:10:0x004e, B:35:0x0151, B:37:0x0159, B:39:0x0173, B:41:0x0180, B:13:0x0064, B:23:0x00dd, B:25:0x00e5, B:27:0x00f4, B:29:0x0101, B:31:0x010b, B:20:0x007f), top: B:58:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x010b A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0036, B:45:0x01d0, B:47:0x01d8, B:49:0x01f2, B:52:0x0203, B:10:0x004e, B:35:0x0151, B:37:0x0159, B:39:0x0173, B:41:0x0180, B:13:0x0064, B:23:0x00dd, B:25:0x00e5, B:27:0x00f4, B:29:0x0101, B:31:0x010b, B:20:0x007f), top: B:58:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:33:0x014b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:0x014c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0159 A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0036, B:45:0x01d0, B:47:0x01d8, B:49:0x01f2, B:52:0x0203, B:10:0x004e, B:35:0x0151, B:37:0x0159, B:39:0x0173, B:41:0x0180, B:13:0x0064, B:23:0x00dd, B:25:0x00e5, B:27:0x00f4, B:29:0x0101, B:31:0x010b, B:20:0x007f), top: B:58:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x0173 A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0036, B:45:0x01d0, B:47:0x01d8, B:49:0x01f2, B:52:0x0203, B:10:0x004e, B:35:0x0151, B:37:0x0159, B:39:0x0173, B:41:0x0180, B:13:0x0064, B:23:0x00dd, B:25:0x00e5, B:27:0x00f4, B:29:0x0101, B:31:0x010b, B:20:0x007f), top: B:58:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x01cc A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:44:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:47:0x01d8 A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0036, B:45:0x01d0, B:47:0x01d8, B:49:0x01f2, B:52:0x0203, B:10:0x004e, B:35:0x0151, B:37:0x0159, B:39:0x0173, B:41:0x0180, B:13:0x0064, B:23:0x00dd, B:25:0x00e5, B:27:0x00f4, B:29:0x0101, B:31:0x010b, B:20:0x007f), top: B:58:0x0017 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x01f2 A[Catch: Exception -> 0x020f, TryCatch #0 {Exception -> 0x020f, blocks: (B:7:0x0036, B:45:0x01d0, B:47:0x01d8, B:49:0x01f2, B:52:0x0203, B:10:0x004e, B:35:0x0151, B:37:0x0159, B:39:0x0173, B:41:0x0180, B:13:0x0064, B:23:0x00dd, B:25:0x00e5, B:27:0x00f4, B:29:0x0101, B:31:0x010b, B:20:0x007f), top: B:58:0x0017 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        final String canonicalTarget;
        Function1 checkEnrollmentInDoc;
        FirebaseFirestore db;
        Object objAwait;
        DocumentSnapshot docSnap;
        Object objAwait2;
        FirebaseFirestore db2;
        DocumentSnapshot docSnap2;
        QuerySnapshot emailSnap;
        Object objAwait3;
        FirebaseFirestore db3;
        Function1 checkEnrollmentInDoc2;
        Object objFirst;
        QuerySnapshot capitalSnap;
        Object objFirst2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    if (this.$isAdmin) {
                        BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                        BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                        return Unit.INSTANCE;
                    }
                    String lowerCase = StringsKt.trim((CharSequence) this.$batchTitle).toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                    canonicalTarget = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(lowerCase, "(", (String) null, 2, (Object) null)).toString();
                    final String str = this.$batchTitle;
                    checkEnrollmentInDoc = new Function1() { // from class: com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchDetailScreen$1$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(BatchDetailActivityKt$BatchDetailScreen$1$1.invokeSuspend$lambda$2(canonicalTarget, str, (DocumentSnapshot) obj));
                        }
                    };
                    db = FirebaseFirestore.getInstance();
                    Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
                    Task<DocumentSnapshot> task = db.collection(Account.USERS).document(this.$studentUid).get();
                    Intrinsics.checkNotNullExpressionValue(task, "get(...)");
                    this.L$0 = SpillingKt.nullOutSpilledVariable(canonicalTarget);
                    this.L$1 = checkEnrollmentInDoc;
                    this.L$2 = db;
                    this.label = 1;
                    objAwait = TasksKt.await(task, this);
                    if (objAwait == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    docSnap = (DocumentSnapshot) objAwait;
                    if (docSnap.exists()) {
                        Intrinsics.checkNotNull(docSnap);
                        if (((Boolean) checkEnrollmentInDoc.invoke(docSnap)).booleanValue()) {
                            BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                            BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                            return Unit.INSTANCE;
                        }
                    }
                    if (!StringsKt.isBlank(this.$userEmail)) {
                        CollectionReference collectionReferenceCollection = db.collection(Account.USERS);
                        String lowerCase2 = StringsKt.trim((CharSequence) this.$userEmail).toString().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                        Task<QuerySnapshot> task2 = collectionReferenceCollection.whereEqualTo("email", lowerCase2).get();
                        Intrinsics.checkNotNullExpressionValue(task2, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable(canonicalTarget);
                        this.L$1 = checkEnrollmentInDoc;
                        this.L$2 = db;
                        this.L$3 = SpillingKt.nullOutSpilledVariable(docSnap);
                        this.label = 2;
                        objAwait2 = TasksKt.await(task2, this);
                        if (objAwait2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        db2 = db;
                        docSnap2 = docSnap;
                        emailSnap = (QuerySnapshot) objAwait2;
                        if (!emailSnap.isEmpty()) {
                            List<DocumentSnapshot> documents = emailSnap.getDocuments();
                            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
                            objFirst = CollectionsKt.first((List<? extends Object>) documents);
                            Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
                            if (((Boolean) checkEnrollmentInDoc.invoke(objFirst)).booleanValue()) {
                                BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                                BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                                return Unit.INSTANCE;
                            }
                        }
                        CollectionReference collectionReferenceCollection2 = db2.collection("Users");
                        String lowerCase3 = StringsKt.trim((CharSequence) this.$userEmail).toString().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                        Task<QuerySnapshot> task3 = collectionReferenceCollection2.whereEqualTo("email", lowerCase3).get();
                        Intrinsics.checkNotNullExpressionValue(task3, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable(canonicalTarget);
                        this.L$1 = checkEnrollmentInDoc;
                        this.L$2 = SpillingKt.nullOutSpilledVariable(db2);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(docSnap2);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(emailSnap);
                        this.label = 3;
                        objAwait3 = TasksKt.await(task3, this);
                        if (objAwait3 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        db3 = db2;
                        checkEnrollmentInDoc2 = checkEnrollmentInDoc;
                        capitalSnap = (QuerySnapshot) objAwait3;
                        if (!capitalSnap.isEmpty()) {
                            List<DocumentSnapshot> documents2 = capitalSnap.getDocuments();
                            Intrinsics.checkNotNullExpressionValue(documents2, "getDocuments(...)");
                            objFirst2 = CollectionsKt.first((List<? extends Object>) documents2);
                            Intrinsics.checkNotNullExpressionValue(objFirst2, "first(...)");
                            if (((Boolean) checkEnrollmentInDoc2.invoke(objFirst2)).booleanValue()) {
                                BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                                BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                                return Unit.INSTANCE;
                            }
                        }
                    }
                    BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, false);
                    BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                    return Unit.INSTANCE;
                case 1:
                    db = (FirebaseFirestore) this.L$2;
                    checkEnrollmentInDoc = (Function1) this.L$1;
                    canonicalTarget = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    objAwait = $result;
                    docSnap = (DocumentSnapshot) objAwait;
                    if (docSnap.exists()) {
                        Intrinsics.checkNotNull(docSnap);
                        if (((Boolean) checkEnrollmentInDoc.invoke(docSnap)).booleanValue()) {
                            BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                            BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                            return Unit.INSTANCE;
                        }
                    }
                    if (!StringsKt.isBlank(this.$userEmail)) {
                        CollectionReference collectionReferenceCollection3 = db.collection(Account.USERS);
                        String lowerCase4 = StringsKt.trim((CharSequence) this.$userEmail).toString().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                        Task<QuerySnapshot> task4 = collectionReferenceCollection3.whereEqualTo("email", lowerCase4).get();
                        Intrinsics.checkNotNullExpressionValue(task4, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable(canonicalTarget);
                        this.L$1 = checkEnrollmentInDoc;
                        this.L$2 = db;
                        this.L$3 = SpillingKt.nullOutSpilledVariable(docSnap);
                        this.label = 2;
                        objAwait2 = TasksKt.await(task4, this);
                        if (objAwait2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        db2 = db;
                        docSnap2 = docSnap;
                        emailSnap = (QuerySnapshot) objAwait2;
                        if (!emailSnap.isEmpty()) {
                            List<DocumentSnapshot> documents3 = emailSnap.getDocuments();
                            Intrinsics.checkNotNullExpressionValue(documents3, "getDocuments(...)");
                            objFirst = CollectionsKt.first((List<? extends Object>) documents3);
                            Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
                            if (((Boolean) checkEnrollmentInDoc.invoke(objFirst)).booleanValue()) {
                                BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                                BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                                return Unit.INSTANCE;
                            }
                        }
                        CollectionReference collectionReferenceCollection4 = db2.collection("Users");
                        String lowerCase5 = StringsKt.trim((CharSequence) this.$userEmail).toString().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase5, "toLowerCase(...)");
                        Task<QuerySnapshot> task5 = collectionReferenceCollection4.whereEqualTo("email", lowerCase5).get();
                        Intrinsics.checkNotNullExpressionValue(task5, "get(...)");
                        this.L$0 = SpillingKt.nullOutSpilledVariable(canonicalTarget);
                        this.L$1 = checkEnrollmentInDoc;
                        this.L$2 = SpillingKt.nullOutSpilledVariable(db2);
                        this.L$3 = SpillingKt.nullOutSpilledVariable(docSnap2);
                        this.L$4 = SpillingKt.nullOutSpilledVariable(emailSnap);
                        this.label = 3;
                        objAwait3 = TasksKt.await(task5, this);
                        if (objAwait3 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        db3 = db2;
                        checkEnrollmentInDoc2 = checkEnrollmentInDoc;
                        capitalSnap = (QuerySnapshot) objAwait3;
                        if (!capitalSnap.isEmpty()) {
                            List<DocumentSnapshot> documents4 = capitalSnap.getDocuments();
                            Intrinsics.checkNotNullExpressionValue(documents4, "getDocuments(...)");
                            objFirst2 = CollectionsKt.first((List<? extends Object>) documents4);
                            Intrinsics.checkNotNullExpressionValue(objFirst2, "first(...)");
                            if (((Boolean) checkEnrollmentInDoc2.invoke(objFirst2)).booleanValue()) {
                                BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                                BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                                return Unit.INSTANCE;
                            }
                        }
                    }
                    BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, false);
                    BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                    return Unit.INSTANCE;
                case 2:
                    docSnap2 = (DocumentSnapshot) this.L$3;
                    FirebaseFirestore db4 = (FirebaseFirestore) this.L$2;
                    Function1 checkEnrollmentInDoc3 = (Function1) this.L$1;
                    String canonicalTarget2 = (String) this.L$0;
                    ResultKt.throwOnFailure($result);
                    canonicalTarget = canonicalTarget2;
                    checkEnrollmentInDoc = checkEnrollmentInDoc3;
                    db2 = db4;
                    objAwait2 = $result;
                    emailSnap = (QuerySnapshot) objAwait2;
                    if (!emailSnap.isEmpty()) {
                        List<DocumentSnapshot> documents5 = emailSnap.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents5, "getDocuments(...)");
                        objFirst = CollectionsKt.first((List<? extends Object>) documents5);
                        Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
                        if (((Boolean) checkEnrollmentInDoc.invoke(objFirst)).booleanValue()) {
                            BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                            BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                            return Unit.INSTANCE;
                        }
                    }
                    CollectionReference collectionReferenceCollection5 = db2.collection("Users");
                    String lowerCase6 = StringsKt.trim((CharSequence) this.$userEmail).toString().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase6, "toLowerCase(...)");
                    Task<QuerySnapshot> task6 = collectionReferenceCollection5.whereEqualTo("email", lowerCase6).get();
                    Intrinsics.checkNotNullExpressionValue(task6, "get(...)");
                    this.L$0 = SpillingKt.nullOutSpilledVariable(canonicalTarget);
                    this.L$1 = checkEnrollmentInDoc;
                    this.L$2 = SpillingKt.nullOutSpilledVariable(db2);
                    this.L$3 = SpillingKt.nullOutSpilledVariable(docSnap2);
                    this.L$4 = SpillingKt.nullOutSpilledVariable(emailSnap);
                    this.label = 3;
                    objAwait3 = TasksKt.await(task6, this);
                    if (objAwait3 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    db3 = db2;
                    checkEnrollmentInDoc2 = checkEnrollmentInDoc;
                    capitalSnap = (QuerySnapshot) objAwait3;
                    if (!capitalSnap.isEmpty()) {
                        List<DocumentSnapshot> documents6 = capitalSnap.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents6, "getDocuments(...)");
                        objFirst2 = CollectionsKt.first((List<? extends Object>) documents6);
                        Intrinsics.checkNotNullExpressionValue(objFirst2, "first(...)");
                        if (((Boolean) checkEnrollmentInDoc2.invoke(objFirst2)).booleanValue()) {
                            BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                            BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                            return Unit.INSTANCE;
                        }
                    }
                    BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, false);
                    BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                    return Unit.INSTANCE;
                case 3:
                    docSnap2 = (DocumentSnapshot) this.L$3;
                    db3 = (FirebaseFirestore) this.L$2;
                    checkEnrollmentInDoc2 = (Function1) this.L$1;
                    ResultKt.throwOnFailure($result);
                    objAwait3 = $result;
                    capitalSnap = (QuerySnapshot) objAwait3;
                    if (!capitalSnap.isEmpty()) {
                        List<DocumentSnapshot> documents7 = capitalSnap.getDocuments();
                        Intrinsics.checkNotNullExpressionValue(documents7, "getDocuments(...)");
                        objFirst2 = CollectionsKt.first((List<? extends Object>) documents7);
                        Intrinsics.checkNotNullExpressionValue(objFirst2, "first(...)");
                        if (((Boolean) checkEnrollmentInDoc2.invoke(objFirst2)).booleanValue()) {
                            BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
                            BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                            return Unit.INSTANCE;
                        }
                    }
                    BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, false);
                    BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Exception e) {
            BatchDetailActivityKt.BatchDetailScreen$lambda$3(this.$isEnrolled$delegate, true);
            BatchDetailActivityKt.BatchDetailScreen$lambda$6(this.$isCheckingEnrollment$delegate, false);
        }
    }

    static final boolean invokeSuspend$lambda$2(String $canonicalTarget, String $batchTitle, DocumentSnapshot doc) {
        Collection list;
        Map<String, Object> data = doc.getData();
        Object obj = data != null ? data.get("enrolledBatches") : null;
        Iterable iterable = obj instanceof List ? (List) obj : null;
        if (iterable != null) {
            Collection arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                String string = next != null ? next.toString() : null;
                if (string != null) {
                    arrayList.add(string);
                }
            }
            list = (List) arrayList;
        } else {
            list = null;
        }
        if (list == null) {
            return false;
        }
        Collection<String> collection = list;
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return false;
        }
        for (String str : collection) {
            String lowerCase = StringsKt.trim((CharSequence) str).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String string2 = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(lowerCase, "(", (String) null, 2, (Object) null)).toString();
            if (Intrinsics.areEqual(string2, $canonicalTarget) || StringsKt.equals(StringsKt.trim((CharSequence) str).toString(), StringsKt.trim((CharSequence) $batchTitle).toString(), true) || StringsKt.contains$default((CharSequence) string2, (CharSequence) $canonicalTarget, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) $canonicalTarget, (CharSequence) string2, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }
}
