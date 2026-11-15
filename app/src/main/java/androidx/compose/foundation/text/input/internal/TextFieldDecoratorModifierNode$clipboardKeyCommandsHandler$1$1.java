package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.KeyCommand;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1", f = "TextFieldDecoratorModifier.kt", i = {}, l = {394, 395, 396}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ KeyCommand $keyCommand;
    int label;
    final /* synthetic */ TextFieldDecoratorModifierNode this$0;

    /* JADX INFO: compiled from: TextFieldDecoratorModifier.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KeyCommand.values().length];
            try {
                iArr[KeyCommand.COPY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[KeyCommand.CUT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[KeyCommand.PASTE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1(KeyCommand keyCommand, TextFieldDecoratorModifierNode textFieldDecoratorModifierNode, Continuation<? super TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1> continuation) {
        super(2, continuation);
        this.$keyCommand = keyCommand;
        this.this$0 = textFieldDecoratorModifierNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1(this.$keyCommand, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003f, code lost:
    
        if (r4.this$0.getTextFieldSelectionState().paste(r4) == r0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0053, code lost:
    
        if (r4.this$0.getTextFieldSelectionState().cut(r4) == r0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
    
        if (r4.this$0.getTextFieldSelectionState().copy(false, r4) == r0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006a, code lost:
    
        return r0;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            switch(r1) {
                case 0: goto L1e;
                case 1: goto L1a;
                case 2: goto L16;
                case 3: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L12:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L42
        L16:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L56
        L1a:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L6b
        L1e:
            kotlin.ResultKt.throwOnFailure(r5)
            androidx.compose.foundation.text.KeyCommand r1 = r4.$keyCommand
            int[] r2 = androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1.WhenMappings.$EnumSwitchMapping$0
            int r1 = r1.ordinal()
            r1 = r2[r1]
            switch(r1) {
                case 1: goto L57;
                case 2: goto L43;
                case 3: goto L2f;
                default: goto L2e;
            }
        L2e:
            goto L6c
        L2f:
            androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r1 = r4.this$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = r1.getTextFieldSelectionState()
            r2 = r4
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 3
            r4.label = r3
            java.lang.Object r1 = r1.paste(r2)
            if (r1 != r0) goto L42
            goto L6a
        L42:
            goto L6c
        L43:
            androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r1 = r4.this$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = r1.getTextFieldSelectionState()
            r2 = r4
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 2
            r4.label = r3
            java.lang.Object r1 = r1.cut(r2)
            if (r1 != r0) goto L56
            goto L6a
        L56:
            goto L6c
        L57:
            androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode r1 = r4.this$0
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = r1.getTextFieldSelectionState()
            r2 = r4
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 1
            r4.label = r3
            r3 = 0
            java.lang.Object r1 = r1.copy(r3, r2)
            if (r1 != r0) goto L6b
        L6a:
            return r0
        L6b:
        L6c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TextFieldDecoratorModifierNode$clipboardKeyCommandsHandler$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
