package com.example.sushikhasacademyapp;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.example.sushikhasacademyapp.utils.BatchNotificationHelper;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$4$1", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class MainActivityKt$AcademyAppScreen$4$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<List<String>> $enrolledBatchesState$delegate;
    final /* synthetic */ MutableState<Boolean> $isAdminLoggedIn$delegate;
    final /* synthetic */ MutableState<Triple<String, String, String>> $latestBatchNotice$delegate;
    final /* synthetic */ Set<String> $notifiedDocIds;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivityKt$AcademyAppScreen$4$1(MutableState<List<String>> mutableState, Set<String> set, Context context, MutableState<Triple<String, String, String>> mutableState2, MutableState<Boolean> mutableState3, Continuation<? super MainActivityKt$AcademyAppScreen$4$1> continuation) {
        super(2, continuation);
        this.$enrolledBatchesState$delegate = mutableState;
        this.$notifiedDocIds = set;
        this.$context = context;
        this.$latestBatchNotice$delegate = mutableState2;
        this.$isAdminLoggedIn$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivityKt$AcademyAppScreen$4$1(this.$enrolledBatchesState$delegate, this.$notifiedDocIds, this.$context, this.$latestBatchNotice$delegate, this.$isAdminLoggedIn$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivityKt$AcademyAppScreen$4$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (MainActivityKt.AcademyAppScreen$lambda$13(this.$enrolledBatchesState$delegate).isEmpty()) {
                    return Unit.INSTANCE;
                }
                try {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
                    CollectionReference collectionReferenceCollection = db.collection("batch_announcements");
                    final Set<String> set = this.$notifiedDocIds;
                    final Context context = this.$context;
                    final MutableState<List<String>> mutableState = this.$enrolledBatchesState$delegate;
                    final MutableState<Triple<String, String, String>> mutableState2 = this.$latestBatchNotice$delegate;
                    final MutableState<Boolean> mutableState3 = this.$isAdminLoggedIn$delegate;
                    Intrinsics.checkNotNull(collectionReferenceCollection.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$4$1$$ExternalSyntheticLambda0
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            MainActivityKt$AcademyAppScreen$4$1.invokeSuspend$lambda$2(set, context, mutableState, mutableState2, mutableState3, (QuerySnapshot) obj, firebaseFirestoreException);
                        }
                    }));
                    break;
                } catch (Exception e) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00dc A[LOOP:1: B:26:0x007d->B:36:0x00dc, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:55:0x00e6 A[EDGE_INSN: B:55:0x00e6->B:38:0x00e6 BREAK  A[LOOP:1: B:26:0x007d->B:36:0x00dc], SYNTHETIC] */
    static final void invokeSuspend$lambda$2(Set $notifiedDocIds, Context $context, MutableState $enrolledBatchesState$delegate, MutableState $latestBatchNotice$delegate, MutableState $isAdminLoggedIn$delegate, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        Iterable iterable;
        int i;
        boolean z;
        boolean z2;
        if (snapshot != null) {
            Iterable documentChanges = snapshot.getDocumentChanges();
            Intrinsics.checkNotNullExpressionValue(documentChanges, "getDocumentChanges(...)");
            Iterable<DocumentChange> iterable2 = documentChanges;
            int i2 = 0;
            for (DocumentChange documentChange : iterable2) {
                if (documentChange.getType() == DocumentChange.Type.ADDED) {
                    QueryDocumentSnapshot document = documentChange.getDocument();
                    Intrinsics.checkNotNullExpressionValue(document, "getDocument(...)");
                    String string = document.getString("batchTitle");
                    if (string == null) {
                        string = "";
                    }
                    String str = string;
                    String string2 = document.getString("title");
                    String str2 = string2 == null ? "" : string2;
                    String string3 = document.getString("message");
                    String str3 = string3 == null ? "" : string3;
                    String id = document.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                    Iterable iterableAcademyAppScreen$lambda$13 = MainActivityKt.AcademyAppScreen$lambda$13($enrolledBatchesState$delegate);
                    if (!(iterableAcademyAppScreen$lambda$13 instanceof Collection) || !((Collection) iterableAcademyAppScreen$lambda$13).isEmpty()) {
                        Iterator it = iterableAcademyAppScreen$lambda$13.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                String str4 = (String) it.next();
                                iterable = iterable2;
                                i = i2;
                                if (StringsKt.equals(StringsKt.trim((CharSequence) str4).toString(), StringsKt.trim((CharSequence) str).toString(), true)) {
                                    z = true;
                                } else {
                                    z = true;
                                    if (!StringsKt.startsWith(StringsKt.trim((CharSequence) str4).toString(), str + " (", true)) {
                                        z2 = false;
                                    }
                                    if (!z2) {
                                        break;
                                    }
                                    i2 = i;
                                    iterable2 = iterable;
                                }
                                z2 = z;
                                if (!z2) {
                                    break;
                                    break;
                                } else {
                                    i2 = i;
                                    iterable2 = iterable;
                                }
                            } else {
                                iterable = iterable2;
                                i = i2;
                                z = false;
                                break;
                            }
                        }
                    } else {
                        iterable = iterable2;
                        i = i2;
                        z = false;
                    }
                    if (z && !$notifiedDocIds.contains(id) && !StringsKt.isBlank(str2)) {
                        $notifiedDocIds.add(id);
                        $latestBatchNotice$delegate.setValue(new Triple(str, str2, str3));
                        BatchNotificationHelper.INSTANCE.showBatchAnnouncementNotification($context, str, str2, str3, MainActivityKt.AcademyAppScreen$lambda$6($isAdminLoggedIn$delegate));
                    }
                } else {
                    iterable = iterable2;
                    i = i2;
                }
                i2 = i;
                iterable2 = iterable;
            }
        }
    }
}
