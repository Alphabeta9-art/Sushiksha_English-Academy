package com.example.sushikhasacademyapp;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.MutableState;
import com.cloudinary.provisioning.Account;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.tasks.TasksKt;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchTestsSection$1$1", f = "BatchDetailActivity.kt", i = {}, l = {1414}, m = "invokeSuspend", n = {}, s = {})
final class BatchDetailActivityKt$BatchTestsSection$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $studentName$delegate;
    final /* synthetic */ String $studentUid;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchTestsSection$1$1(String str, MutableState<String> mutableState, Continuation<? super BatchDetailActivityKt$BatchTestsSection$1$1> continuation) {
        super(2, continuation);
        this.$studentUid = str;
        this.$studentName$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchTestsSection$1$1(this.$studentUid, this.$studentName$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchTestsSection$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0052 A[Catch: Exception -> 0x0066, TryCatch #0 {Exception -> 0x0066, blocks: (B:6:0x0011, B:14:0x004a, B:16:0x0052, B:18:0x005c, B:19:0x0062, B:9:0x001a, B:11:0x0024), top: B:24:0x0006 }] */
    /* JADX WARN: Code duplicated, block: B:18:0x005c A[Catch: Exception -> 0x0066, TryCatch #0 {Exception -> 0x0066, blocks: (B:6:0x0011, B:14:0x004a, B:16:0x0052, B:18:0x005c, B:19:0x0062, B:9:0x001a, B:11:0x0024), top: B:24:0x0006 }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object objAwait;
        DocumentSnapshot doc;
        String string;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    if (!StringsKt.isBlank(this.$studentUid)) {
                        Task<DocumentSnapshot> task = FirebaseFirestore.getInstance().collection(Account.USERS).document(this.$studentUid).get();
                        Intrinsics.checkNotNullExpressionValue(task, "get(...)");
                        this.label = 1;
                        objAwait = TasksKt.await(task, this);
                        if (objAwait == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        doc = (DocumentSnapshot) objAwait;
                        if (doc.exists()) {
                            MutableState<String> mutableState = this.$studentName$delegate;
                            string = doc.getString(HintConstants.AUTOFILL_HINT_USERNAME);
                            if (string == null) {
                                string = BatchDetailActivityKt.BatchTestsSection$lambda$251(this.$studentName$delegate);
                            }
                            mutableState.setValue(string);
                        }
                        break;
                    }
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objAwait = $result;
                    doc = (DocumentSnapshot) objAwait;
                    if (doc.exists()) {
                        MutableState<String> mutableState2 = this.$studentName$delegate;
                        string = doc.getString(HintConstants.AUTOFILL_HINT_USERNAME);
                        if (string == null) {
                            string = BatchDetailActivityKt.BatchTestsSection$lambda$251(this.$studentName$delegate);
                        }
                        mutableState2.setValue(string);
                        break;
                    }
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Exception e) {
        }
    }
}
