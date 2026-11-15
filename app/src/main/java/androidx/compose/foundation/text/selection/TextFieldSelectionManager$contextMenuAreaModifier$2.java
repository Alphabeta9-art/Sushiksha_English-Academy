package androidx.compose.foundation.text.selection;

import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager$contextMenuAreaModifier$2", f = "TextFieldSelectionManager.kt", i = {}, l = {241, 243}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TextFieldSelectionManager$contextMenuAreaModifier$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ TextFieldSelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionManager$contextMenuAreaModifier$2(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super TextFieldSelectionManager$contextMenuAreaModifier$2> continuation) {
        super(1, continuation);
        this.this$0 = textFieldSelectionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new TextFieldSelectionManager$contextMenuAreaModifier$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((TextFieldSelectionManager$contextMenuAreaModifier$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0035  */
    /* JADX WARN: Code duplicated, block: B:15:0x004e  */
    /* JADX WARN: Code duplicated, block: B:17:0x005a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:18:0x005b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Pair contextTextAndSelection;
        String text;
        long selection;
        PlatformSelectionBehaviors platformSelectionBehaviors;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                if (this.this$0.updateClipboardEntry$foundation(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                contextTextAndSelection = this.this$0.getContextTextAndSelection();
                if (contextTextAndSelection != null) {
                    TextFieldSelectionManager textFieldSelectionManager = this.this$0;
                    text = (String) contextTextAndSelection.component1();
                    selection = ((TextRange) contextTextAndSelection.component2()).getPackedValue();
                    platformSelectionBehaviors = textFieldSelectionManager.getPlatformSelectionBehaviors();
                    if (platformSelectionBehaviors != null) {
                        this.label = 2;
                        if (platformSelectionBehaviors.mo1592onShowSelectionToolbarSbBc2M(text, selection, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                this.this$0.setTextToolbarShownViaProvider$foundation(true);
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                contextTextAndSelection = this.this$0.getContextTextAndSelection();
                if (contextTextAndSelection != null) {
                    TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                    text = (String) contextTextAndSelection.component1();
                    selection = ((TextRange) contextTextAndSelection.component2()).getPackedValue();
                    platformSelectionBehaviors = textFieldSelectionManager2.getPlatformSelectionBehaviors();
                    if (platformSelectionBehaviors != null) {
                        this.label = 2;
                        if (platformSelectionBehaviors.mo1592onShowSelectionToolbarSbBc2M(text, selection, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                this.this$0.setTextToolbarShownViaProvider$foundation(true);
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                this.this$0.setTextToolbarShownViaProvider$foundation(true);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
