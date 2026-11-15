package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.text.TextRange;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TextFieldSelectionManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "clickLocation", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.selection.TextFieldSelectionManager$contextMenuAreaModifier$1", f = "TextFieldSelectionManager.kt", i = {0}, l = {228, 230}, m = "invokeSuspend", n = {"clickLocation"}, s = {"J$0"}, v = 1)
final class TextFieldSelectionManager$contextMenuAreaModifier$1 extends SuspendLambda implements Function2<Offset, Continuation<? super Unit>, Object> {
    /* synthetic */ long J$0;
    int label;
    final /* synthetic */ TextFieldSelectionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldSelectionManager$contextMenuAreaModifier$1(TextFieldSelectionManager textFieldSelectionManager, Continuation<? super TextFieldSelectionManager$contextMenuAreaModifier$1> continuation) {
        super(2, continuation);
        this.this$0 = textFieldSelectionManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TextFieldSelectionManager$contextMenuAreaModifier$1 textFieldSelectionManager$contextMenuAreaModifier$1 = new TextFieldSelectionManager$contextMenuAreaModifier$1(this.this$0, continuation);
        textFieldSelectionManager$contextMenuAreaModifier$1.J$0 = ((Offset) obj).m4862unboximpl();
        return textFieldSelectionManager$contextMenuAreaModifier$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Offset offset, Continuation<? super Unit> continuation) {
        return m1669invoke3MmeM6k(offset.m4862unboximpl(), continuation);
    }

    /* JADX INFO: renamed from: invoke-3MmeM6k, reason: not valid java name */
    public final Object m1669invoke3MmeM6k(long j, Continuation<? super Unit> continuation) {
        return ((TextFieldSelectionManager$contextMenuAreaModifier$1) create(Offset.m4841boximpl(j), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x003b  */
    /* JADX WARN: Code duplicated, block: B:15:0x0054  */
    /* JADX WARN: Code duplicated, block: B:17:0x0066 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:18:0x0067  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        long clickLocation;
        Pair contextTextAndSelection;
        String text;
        long selection;
        PlatformSelectionBehaviors platformSelectionBehaviors;
        Offset offsetM4841boximpl;
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                clickLocation = this.J$0;
                this.J$0 = clickLocation;
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
                        offsetM4841boximpl = Offset.m4841boximpl(clickLocation);
                        this.label = 2;
                        if (platformSelectionBehaviors.mo1591onShowContextMenu_2OEclM(text, selection, offsetM4841boximpl, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i = 0;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                clickLocation = this.J$0;
                ResultKt.throwOnFailure($result);
                contextTextAndSelection = this.this$0.getContextTextAndSelection();
                if (contextTextAndSelection != null) {
                    TextFieldSelectionManager textFieldSelectionManager2 = this.this$0;
                    text = (String) contextTextAndSelection.component1();
                    selection = ((TextRange) contextTextAndSelection.component2()).getPackedValue();
                    platformSelectionBehaviors = textFieldSelectionManager2.getPlatformSelectionBehaviors();
                    if (platformSelectionBehaviors != null) {
                        offsetM4841boximpl = Offset.m4841boximpl(clickLocation);
                        this.label = 2;
                        if (platformSelectionBehaviors.mo1591onShowContextMenu_2OEclM(text, selection, offsetM4841boximpl, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i = 0;
                    }
                }
                return Unit.INSTANCE;
            case 2:
                i = 0;
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
