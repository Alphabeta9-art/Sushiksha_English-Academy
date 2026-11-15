package com.example.sushikhasacademyapp;

import androidx.compose.runtime.MutableState;
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
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchScheduleSection$1$1", f = "BatchDetailActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class BatchDetailActivityKt$BatchScheduleSection$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $batchTitle;
    final /* synthetic */ MutableState<BatchScheduleData> $schedule$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchScheduleSection$1$1(String str, MutableState<BatchScheduleData> mutableState, Continuation<? super BatchDetailActivityKt$BatchScheduleSection$1$1> continuation) {
        super(2, continuation);
        this.$batchTitle = str;
        this.$schedule$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchScheduleSection$1$1(this.$batchTitle, this.$schedule$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchScheduleSection$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    DocumentReference documentReferenceDocument = FirebaseFirestore.getInstance().collection("batch_official_schedules").document(this.$batchTitle);
                    final MutableState<BatchScheduleData> mutableState = this.$schedule$delegate;
                    Intrinsics.checkNotNull(documentReferenceDocument.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchScheduleSection$1$1$$ExternalSyntheticLambda0
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            BatchDetailActivityKt$BatchScheduleSection$1$1.invokeSuspend$lambda$0(mutableState, (DocumentSnapshot) obj, firebaseFirestoreException);
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

    static final void invokeSuspend$lambda$0(MutableState $schedule$delegate, DocumentSnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (snapshot != null && snapshot.exists()) {
            String string = snapshot.getString("days");
            if (string == null) {
                string = BatchDetailActivityKt.BatchScheduleSection$lambda$476($schedule$delegate).getDays();
            }
            String str = string;
            String string2 = snapshot.getString("timeSlot");
            if (string2 == null) {
                string2 = BatchDetailActivityKt.BatchScheduleSection$lambda$476($schedule$delegate).getTimeSlot();
            }
            String str2 = string2;
            String string3 = snapshot.getString("venue");
            if (string3 == null) {
                string3 = BatchDetailActivityKt.BatchScheduleSection$lambda$476($schedule$delegate).getVenue();
            }
            String str3 = string3;
            String string4 = snapshot.getString("mentor");
            if (string4 == null) {
                string4 = BatchDetailActivityKt.BatchScheduleSection$lambda$476($schedule$delegate).getMentor();
            }
            String str4 = string4;
            String string5 = snapshot.getString("topic");
            if (string5 == null) {
                string5 = BatchDetailActivityKt.BatchScheduleSection$lambda$476($schedule$delegate).getTopic();
            }
            String str5 = string5;
            String string6 = snapshot.getString("specialNote");
            if (string6 == null) {
                string6 = BatchDetailActivityKt.BatchScheduleSection$lambda$476($schedule$delegate).getSpecialNote();
            }
            $schedule$delegate.setValue(new BatchScheduleData(str, str2, str3, str4, str5, string6));
        }
    }
}
