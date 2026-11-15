package androidx.compose.foundation.text;

import androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: CommonContextMenuArea.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.text.CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1", f = "CommonContextMenuArea.kt", i = {}, l = {105, 106, 107}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TextContextMenuItems $item;
    final /* synthetic */ TextFieldSelectionState $this_contextMenuBuilder;
    int label;

    /* JADX INFO: compiled from: CommonContextMenuArea.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TextContextMenuItems.values().length];
            try {
                iArr[TextContextMenuItems.Cut.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TextContextMenuItems.Copy.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TextContextMenuItems.Paste.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TextContextMenuItems.SelectAll.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[TextContextMenuItems.Autofill.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1(TextContextMenuItems textContextMenuItems, TextFieldSelectionState textFieldSelectionState, Continuation<? super CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1> continuation) {
        super(2, continuation);
        this.$item = textContextMenuItems;
        this.$this_contextMenuBuilder = textFieldSelectionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1(this.$item, this.$this_contextMenuBuilder, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x004c, code lost:
    
        if (r4.$this_contextMenuBuilder.paste(r4) == r0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005d, code lost:
    
        if (r4.$this_contextMenuBuilder.copy(false, r4) == r0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006d, code lost:
    
        if (r4.$this_contextMenuBuilder.cut(r4) == r0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006f, code lost:
    
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
            goto L4f
        L16:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L60
        L1a:
            kotlin.ResultKt.throwOnFailure(r5)
            goto L70
        L1e:
            kotlin.ResultKt.throwOnFailure(r5)
            androidx.compose.foundation.text.TextContextMenuItems r1 = r4.$item
            int[] r2 = androidx.compose.foundation.text.CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1.WhenMappings.$EnumSwitchMapping$0
            int r1 = r1.ordinal()
            r1 = r2[r1]
            switch(r1) {
                case 1: goto L61;
                case 2: goto L50;
                case 3: goto L40;
                case 4: goto L3a;
                case 5: goto L34;
                default: goto L2e;
            }
        L2e:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L34:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r0 = r4.$this_contextMenuBuilder
            r0.autofill()
            goto L71
        L3a:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r0 = r4.$this_contextMenuBuilder
            r0.selectAll()
            goto L71
        L40:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = r4.$this_contextMenuBuilder
            r2 = r4
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 3
            r4.label = r3
            java.lang.Object r1 = r1.paste(r2)
            if (r1 != r0) goto L4f
            goto L6f
        L4f:
            goto L71
        L50:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = r4.$this_contextMenuBuilder
            r2 = r4
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 2
            r4.label = r3
            r3 = 0
            java.lang.Object r1 = r1.copy(r3, r2)
            if (r1 != r0) goto L60
            goto L6f
        L60:
            goto L71
        L61:
            androidx.compose.foundation.text.input.internal.selection.TextFieldSelectionState r1 = r4.$this_contextMenuBuilder
            r2 = r4
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 1
            r4.label = r3
            java.lang.Object r1 = r1.cut(r2)
            if (r1 != r0) goto L70
        L6f:
            return r0
        L70:
        L71:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.CommonContextMenuAreaKt$CommonContextMenuArea$menuBuilder$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
