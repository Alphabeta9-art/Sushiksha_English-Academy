package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Chip.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.SelectableChipElevation$animateElevation$2$1", f = "Chip.kt", i = {}, l = {2562, 2564}, m = "invokeSuspend", n = {}, s = {})
final class SelectableChipElevation$animateElevation$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ MutableState<Interaction> $lastInteraction$delegate;
    final /* synthetic */ float $target;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SelectableChipElevation$animateElevation$2$1(Animatable<Dp, AnimationVector1D> animatable, float f, boolean z, Interaction interaction, MutableState<Interaction> mutableState, Continuation<? super SelectableChipElevation$animateElevation$2$1> continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.$target = f;
        this.$enabled = z;
        this.$interaction = interaction;
        this.$lastInteraction$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SelectableChipElevation$animateElevation$2$1(this.$animatable, this.$target, this.$enabled, this.$interaction, this.$lastInteraction$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SelectableChipElevation$animateElevation$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0046, code lost:
    
        if (r7.$animatable.snapTo(androidx.compose.ui.unit.Dp.m7903boximpl(r7.$target), r7) == r0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0061, code lost:
    
        if (androidx.compose.material3.internal.ElevationKt.m3363animateElevationrAjV9yQ(r7.$animatable, r7.$target, r1, r7.$interaction, r7) == r0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0063, code lost:
    
        return r0;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            switch(r1) {
                case 0: goto L19;
                case 1: goto L15;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L11:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L64
        L15:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L49
        L19:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r7.$animatable
            java.lang.Object r1 = r1.getTargetValue()
            androidx.compose.ui.unit.Dp r1 = (androidx.compose.ui.unit.Dp) r1
            float r1 = r1.m7919unboximpl()
            float r2 = r7.$target
            boolean r1 = androidx.compose.ui.unit.Dp.m7910equalsimpl0(r1, r2)
            if (r1 != 0) goto L6c
            boolean r1 = r7.$enabled
            if (r1 != 0) goto L4a
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r7.$animatable
            float r2 = r7.$target
            androidx.compose.ui.unit.Dp r2 = androidx.compose.ui.unit.Dp.m7903boximpl(r2)
            r3 = r7
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 1
            r7.label = r4
            java.lang.Object r1 = r1.snapTo(r2, r3)
            if (r1 != r0) goto L49
            goto L63
        L49:
            goto L65
        L4a:
            androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.Interaction> r1 = r7.$lastInteraction$delegate
            androidx.compose.foundation.interaction.Interaction r1 = androidx.compose.material3.SelectableChipElevation.access$animateElevation$lambda$2(r1)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r2 = r7.$animatable
            float r3 = r7.$target
            androidx.compose.foundation.interaction.Interaction r4 = r7.$interaction
            r5 = r7
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6 = 2
            r7.label = r6
            java.lang.Object r1 = androidx.compose.material3.internal.ElevationKt.m3363animateElevationrAjV9yQ(r2, r3, r1, r4, r5)
            if (r1 != r0) goto L64
        L63:
            return r0
        L64:
        L65:
            androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.Interaction> r0 = r7.$lastInteraction$delegate
            androidx.compose.foundation.interaction.Interaction r1 = r7.$interaction
            androidx.compose.material3.SelectableChipElevation.access$animateElevation$lambda$3(r0, r1)
        L6c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SelectableChipElevation$animateElevation$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
