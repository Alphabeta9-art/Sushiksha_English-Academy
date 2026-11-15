package com.example.sushikhasacademyapp;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.example.sushikhasacademyapp.data.FullTestModel;
import com.example.sushikhasacademyapp.data.LocalTestManager;
import com.example.sushikhasacademyapp.data.StudentSubmission;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$BatchTestsSection$2$1", f = "BatchDetailActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class BatchDetailActivityKt$BatchTestsSection$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $batchTitle;
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<List<StudentSubmission>> $submissionsList$delegate;
    final /* synthetic */ MutableState<List<FullTestModel>> $testsList$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$BatchTestsSection$2$1(Context context, String str, MutableState<List<FullTestModel>> mutableState, MutableState<List<StudentSubmission>> mutableState2, Continuation<? super BatchDetailActivityKt$BatchTestsSection$2$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$batchTitle = str;
        this.$testsList$delegate = mutableState;
        this.$submissionsList$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$BatchTestsSection$2$1(this.$context, this.$batchTitle, this.$testsList$delegate, this.$submissionsList$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$BatchTestsSection$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.$testsList$delegate.setValue(LocalTestManager.INSTANCE.getLocalTests(this.$context, this.$batchTitle));
                this.$submissionsList$delegate.setValue(LocalTestManager.INSTANCE.getLocalSubmissions(this.$context, this.$batchTitle));
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
