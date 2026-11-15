package com.example.sushikhasacademyapp;

import androidx.compose.runtime.MutableState;
import com.cloudinary.provisioning.Account;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.List;
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

/* JADX INFO: compiled from: ProfileActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.ProfileActivityKt$ProfileScreen$1$1", f = "ProfileActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class ProfileActivityKt$ProfileScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<List<String>> $enrolledBatches$delegate;
    final /* synthetic */ MutableState<List<String>> $enrolledCourses$delegate;
    final /* synthetic */ String $userEmail;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ProfileActivityKt$ProfileScreen$1$1(String str, MutableState<List<String>> mutableState, MutableState<List<String>> mutableState2, Continuation<? super ProfileActivityKt$ProfileScreen$1$1> continuation) {
        super(2, continuation);
        this.$userEmail = str;
        this.$enrolledCourses$delegate = mutableState;
        this.$enrolledBatches$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileActivityKt$ProfileScreen$1$1(this.$userEmail, this.$enrolledCourses$delegate, this.$enrolledBatches$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ProfileActivityKt$ProfileScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        String strReplace$default;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser == null || (strReplace$default = currentUser.getUid()) == null) {
                    strReplace$default = StringsKt.replace$default(this.$userEmail, ".", "_", false, 4, (Object) null);
                }
                String uid = strReplace$default;
                try {
                    DocumentReference documentReferenceDocument = FirebaseFirestore.getInstance().collection(Account.USERS).document(uid);
                    final MutableState<List<String>> mutableState = this.$enrolledCourses$delegate;
                    final MutableState<List<String>> mutableState2 = this.$enrolledBatches$delegate;
                    Intrinsics.checkNotNull(documentReferenceDocument.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$ProfileScreen$1$1$$ExternalSyntheticLambda0
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            ProfileActivityKt$ProfileScreen$1$1.invokeSuspend$lambda$0(mutableState, mutableState2, (DocumentSnapshot) obj, firebaseFirestoreException);
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

    static final void invokeSuspend$lambda$0(MutableState $enrolledCourses$delegate, MutableState $enrolledBatches$delegate, DocumentSnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (snapshot != null && snapshot.exists()) {
            Object obj = snapshot.get("enrolledCourses");
            List courses = obj instanceof List ? (List) obj : null;
            $enrolledCourses$delegate.setValue(courses == null ? CollectionsKt.emptyList() : courses);
            Object obj2 = snapshot.get("enrolledBatches");
            List batches = obj2 instanceof List ? (List) obj2 : null;
            $enrolledBatches$delegate.setValue(batches == null ? CollectionsKt.emptyList() : batches);
            return;
        }
        $enrolledCourses$delegate.setValue(CollectionsKt.emptyList());
        $enrolledBatches$delegate.setValue(CollectionsKt.emptyList());
    }
}
