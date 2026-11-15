package com.example.sushikhasacademyapp;

import androidx.compose.runtime.snapshots.SnapshotStateMap;
import com.example.sushikhasacademyapp.data.ExtendedQuestion;
import com.example.sushikhasacademyapp.data.StudentSubmission;
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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BatchDetailActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.BatchDetailActivityKt$AdminEvaluationDialog$2$1", f = "BatchDetailActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class BatchDetailActivityKt$AdminEvaluationDialog$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<ExtendedQuestion> $qList;
    final /* synthetic */ SnapshotStateMap<String, String> $remarksMap;
    final /* synthetic */ SnapshotStateMap<String, String> $scoresMap;
    final /* synthetic */ StudentSubmission $submission;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BatchDetailActivityKt$AdminEvaluationDialog$2$1(List<ExtendedQuestion> list, SnapshotStateMap<String, String> snapshotStateMap, StudentSubmission studentSubmission, SnapshotStateMap<String, String> snapshotStateMap2, Continuation<? super BatchDetailActivityKt$AdminEvaluationDialog$2$1> continuation) {
        super(2, continuation);
        this.$qList = list;
        this.$scoresMap = snapshotStateMap;
        this.$submission = studentSubmission;
        this.$remarksMap = snapshotStateMap2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BatchDetailActivityKt$AdminEvaluationDialog$2$1(this.$qList, this.$scoresMap, this.$submission, this.$remarksMap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BatchDetailActivityKt$AdminEvaluationDialog$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Iterable iterable = this.$qList;
                SnapshotStateMap<String, String> snapshotStateMap = this.$scoresMap;
                StudentSubmission studentSubmission = this.$submission;
                SnapshotStateMap<String, String> snapshotStateMap2 = this.$remarksMap;
                int i = 0;
                for (Object obj : iterable) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    ExtendedQuestion extendedQuestion = (ExtendedQuestion) obj;
                    String strValueOf = String.valueOf(i);
                    SnapshotStateMap<String, String> snapshotStateMap3 = snapshotStateMap;
                    Integer num = studentSubmission.getScoresMap().get(strValueOf);
                    snapshotStateMap3.put(strValueOf, String.valueOf(num != null ? num.intValue() : (extendedQuestion.isMcq() && Intrinsics.areEqual(studentSubmission.getAnswersMap().get(strValueOf), extendedQuestion.getCorrectAns())) ? extendedQuestion.getMarks() : 0));
                    SnapshotStateMap<String, String> snapshotStateMap4 = snapshotStateMap2;
                    String str = studentSubmission.getRemarksMap().get(strValueOf);
                    if (str == null) {
                        str = "";
                    }
                    snapshotStateMap4.put(strValueOf, str);
                    i = i2;
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
