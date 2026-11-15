package com.example.sushikhasacademyapp;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import com.cloudinary.provisioning.Account;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchDetailsSection$1$1", f = "BatchDetailActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class BatchDetailActivityKt$BatchDetailsSection$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableLongState $attendedDays$delegate;
    final /* synthetic */ MutableState<String> $studentClass$delegate;
    final /* synthetic */ MutableState<String> $studentExam$delegate;
    final /* synthetic */ MutableState<String> $studentName$delegate;
    final /* synthetic */ MutableState<String> $studentSchool$delegate;
    final /* synthetic */ String $studentUid;
    final /* synthetic */ MutableLongState $totalWorkingDays$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchDetailsSection$1$1(String str, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableLongState mutableLongState, MutableLongState mutableLongState2, Continuation<? super BatchDetailActivityKt$BatchDetailsSection$1$1> continuation) {
        super(2, continuation);
        this.$studentUid = str;
        this.$studentName$delegate = mutableState;
        this.$studentClass$delegate = mutableState2;
        this.$studentSchool$delegate = mutableState3;
        this.$studentExam$delegate = mutableState4;
        this.$attendedDays$delegate = mutableLongState;
        this.$totalWorkingDays$delegate = mutableLongState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchDetailsSection$1$1(this.$studentUid, this.$studentName$delegate, this.$studentClass$delegate, this.$studentSchool$delegate, this.$studentExam$delegate, this.$attendedDays$delegate, this.$totalWorkingDays$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchDetailsSection$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
                    DocumentReference documentReferenceDocument = db.collection(Account.USERS).document(this.$studentUid);
                    final MutableState<String> mutableState = this.$studentName$delegate;
                    final MutableState<String> mutableState2 = this.$studentClass$delegate;
                    final MutableState<String> mutableState3 = this.$studentSchool$delegate;
                    final MutableState<String> mutableState4 = this.$studentExam$delegate;
                    final MutableLongState mutableLongState = this.$attendedDays$delegate;
                    final MutableLongState mutableLongState2 = this.$totalWorkingDays$delegate;
                    Intrinsics.checkNotNull(documentReferenceDocument.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchDetailsSection$1$1$$ExternalSyntheticLambda0
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            BatchDetailActivityKt$BatchDetailsSection$1$1.invokeSuspend$lambda$0(mutableState, mutableState2, mutableState3, mutableState4, mutableLongState, mutableLongState2, (DocumentSnapshot) obj, firebaseFirestoreException);
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

    static final void invokeSuspend$lambda$0(MutableState $studentName$delegate, MutableState $studentClass$delegate, MutableState $studentSchool$delegate, MutableState $studentExam$delegate, MutableLongState $attendedDays$delegate, MutableLongState $totalWorkingDays$delegate, DocumentSnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (snapshot != null && snapshot.exists()) {
            String string = snapshot.getString(HintConstants.AUTOFILL_HINT_USERNAME);
            if (string == null) {
                string = BatchDetailActivityKt.BatchDetailsSection$lambda$166($studentName$delegate);
            }
            $studentName$delegate.setValue(string);
            String string2 = snapshot.getString("classLevel");
            if (string2 == null) {
                string2 = BatchDetailActivityKt.BatchDetailsSection$lambda$169($studentClass$delegate);
            }
            $studentClass$delegate.setValue(string2);
            String string3 = snapshot.getString("school");
            if (string3 == null) {
                string3 = BatchDetailActivityKt.BatchDetailsSection$lambda$172($studentSchool$delegate);
            }
            $studentSchool$delegate.setValue(string3);
            String string4 = snapshot.getString("exam");
            if (string4 == null) {
                string4 = BatchDetailActivityKt.BatchDetailsSection$lambda$175($studentExam$delegate);
            }
            $studentExam$delegate.setValue(string4);
            Long l = snapshot.getLong("attendedDays");
            $attendedDays$delegate.setLongValue(l != null ? l.longValue() : 24L);
            Long l2 = snapshot.getLong("totalWorkingDays");
            $totalWorkingDays$delegate.setLongValue(l2 != null ? l2.longValue() : 30L);
        }
    }
}
