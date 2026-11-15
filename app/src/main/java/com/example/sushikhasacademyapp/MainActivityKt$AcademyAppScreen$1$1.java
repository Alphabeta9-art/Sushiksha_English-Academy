package com.example.sushikhasacademyapp;

import androidx.compose.runtime.MutableState;
import com.cloudinary.provisioning.Account;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$1$1", f = "MainActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class MainActivityKt$AcademyAppScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FirebaseAuth $auth;
    final /* synthetic */ MutableState<List<String>> $enrolledBatchesState$delegate;
    final /* synthetic */ MutableState<String> $loggedInEmail$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainActivityKt$AcademyAppScreen$1$1(FirebaseAuth firebaseAuth, MutableState<String> mutableState, MutableState<List<String>> mutableState2, Continuation<? super MainActivityKt$AcademyAppScreen$1$1> continuation) {
        super(2, continuation);
        this.$auth = firebaseAuth;
        this.$loggedInEmail$delegate = mutableState;
        this.$enrolledBatchesState$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainActivityKt$AcademyAppScreen$1$1(this.$auth, this.$loggedInEmail$delegate, this.$enrolledBatchesState$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainActivityKt$AcademyAppScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
                FirebaseUser currentUser = this.$auth.getCurrentUser();
                String currentUid = currentUser != null ? currentUser.getUid() : null;
                String cleanEmail = StringsKt.trim((CharSequence) MainActivityKt.AcademyAppScreen$lambda$10(this.$loggedInEmail$delegate)).toString().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(cleanEmail, "toLowerCase(...)");
                final MutableState<List<String>> mutableState = this.$enrolledBatchesState$delegate;
                final Function1 updateBatchesFromDoc = new Function1() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$1$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MainActivityKt$AcademyAppScreen$1$1.invokeSuspend$lambda$1(mutableState, (DocumentSnapshot) obj);
                    }
                };
                String str = currentUid;
                if (!(str == null || StringsKt.isBlank(str))) {
                    db.collection(Account.USERS).document(currentUid).addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$1$1$$ExternalSyntheticLambda1
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            MainActivityKt$AcademyAppScreen$1$1.invokeSuspend$lambda$2(updateBatchesFromDoc, (DocumentSnapshot) obj, firebaseFirestoreException);
                        }
                    });
                }
                if (!StringsKt.isBlank(cleanEmail)) {
                    db.collection(Account.USERS).whereEqualTo("email", cleanEmail).addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$1$1$$ExternalSyntheticLambda2
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            MainActivityKt$AcademyAppScreen$1$1.invokeSuspend$lambda$3(updateBatchesFromDoc, (QuerySnapshot) obj, firebaseFirestoreException);
                        }
                    });
                    db.collection("Users").whereEqualTo("email", cleanEmail).addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.MainActivityKt$AcademyAppScreen$1$1$$ExternalSyntheticLambda3
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            MainActivityKt$AcademyAppScreen$1$1.invokeSuspend$lambda$4(updateBatchesFromDoc, (QuerySnapshot) obj, firebaseFirestoreException);
                        }
                    });
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final Unit invokeSuspend$lambda$1(MutableState $enrolledBatchesState$delegate, DocumentSnapshot doc) {
        Object obj = doc.get("enrolledBatches");
        List list = null;
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
        }
        if (list != null) {
            $enrolledBatchesState$delegate.setValue(list);
        }
        return Unit.INSTANCE;
    }

    static final void invokeSuspend$lambda$2(Function1 $updateBatchesFromDoc, DocumentSnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (snapshot != null && snapshot.exists()) {
            $updateBatchesFromDoc.invoke(snapshot);
        }
    }

    static final void invokeSuspend$lambda$3(Function1 $updateBatchesFromDoc, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (snapshot != null && !snapshot.isEmpty()) {
            List<DocumentSnapshot> documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Object objFirst = CollectionsKt.first((List<? extends Object>) documents);
            Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
            $updateBatchesFromDoc.invoke(objFirst);
        }
    }

    static final void invokeSuspend$lambda$4(Function1 $updateBatchesFromDoc, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (snapshot != null && !snapshot.isEmpty()) {
            List<DocumentSnapshot> documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Object objFirst = CollectionsKt.first((List<? extends Object>) documents);
            Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
            $updateBatchesFromDoc.invoke(objFirst);
        }
    }
}
