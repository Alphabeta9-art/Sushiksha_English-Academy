package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Button.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material.DefaultButtonElevation$elevation$2$1", f = "Button.kt", i = {}, l = {551, 560}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class DefaultButtonElevation$elevation$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ float $target;
    int label;
    final /* synthetic */ DefaultButtonElevation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DefaultButtonElevation$elevation$2$1(Animatable<Dp, AnimationVector1D> animatable, float f, boolean z, DefaultButtonElevation defaultButtonElevation, Interaction interaction, Continuation<? super DefaultButtonElevation$elevation$2$1> continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.$target = f;
        this.$enabled = z;
        this.this$0 = defaultButtonElevation;
        this.$interaction = interaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DefaultButtonElevation$elevation$2$1(this.$animatable, this.$target, this.$enabled, this.this$0, this.$interaction, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DefaultButtonElevation$elevation$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0048, code lost:
    
        if (r7.$animatable.snapTo(androidx.compose.ui.unit.Dp.m7903boximpl(r7.$target), r7) == r0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00b2, code lost:
    
        if (androidx.compose.material.ElevationKt.m1816animateElevationrAjV9yQ(r7.$animatable, r7.$target, r3, r7.$interaction, r7) == r0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b4, code lost:
    
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
                case 0: goto L1b;
                case 1: goto L17;
                case 2: goto L12;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L12:
            kotlin.ResultKt.throwOnFailure(r8)
            goto Lb5
        L17:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L4b
        L1b:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r7.$animatable
            java.lang.Object r1 = r1.getTargetValue()
            androidx.compose.ui.unit.Dp r1 = (androidx.compose.ui.unit.Dp) r1
            float r1 = r1.m7919unboximpl()
            float r2 = r7.$target
            boolean r1 = androidx.compose.ui.unit.Dp.m7910equalsimpl0(r1, r2)
            if (r1 != 0) goto Lb6
            boolean r1 = r7.$enabled
            if (r1 != 0) goto L4c
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r7.$animatable
            float r2 = r7.$target
            androidx.compose.ui.unit.Dp r2 = androidx.compose.ui.unit.Dp.m7903boximpl(r2)
            r3 = r7
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 1
            r7.label = r4
            java.lang.Object r1 = r1.snapTo(r2, r3)
            if (r1 != r0) goto L4b
            goto Lb4
        L4b:
            goto Lb6
        L4c:
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r7.$animatable
            java.lang.Object r1 = r1.getTargetValue()
            androidx.compose.ui.unit.Dp r1 = (androidx.compose.ui.unit.Dp) r1
            float r1 = r1.m7919unboximpl()
            androidx.compose.material.DefaultButtonElevation r2 = r7.this$0
            float r2 = androidx.compose.material.DefaultButtonElevation.access$getPressedElevation$p(r2)
            boolean r2 = androidx.compose.ui.unit.Dp.m7910equalsimpl0(r1, r2)
            r3 = 0
            if (r2 == 0) goto L74
            androidx.compose.foundation.interaction.PressInteraction$Press r1 = new androidx.compose.foundation.interaction.PressInteraction$Press
            androidx.compose.ui.geometry.Offset$Companion r2 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r4 = r2.m4868getZeroF1C5BW0()
            r1.<init>(r4, r3)
            r3 = r1
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
            goto L9f
        L74:
            androidx.compose.material.DefaultButtonElevation r2 = r7.this$0
            float r2 = androidx.compose.material.DefaultButtonElevation.access$getHoveredElevation$p(r2)
            boolean r2 = androidx.compose.ui.unit.Dp.m7910equalsimpl0(r1, r2)
            if (r2 == 0) goto L89
            androidx.compose.foundation.interaction.HoverInteraction$Enter r1 = new androidx.compose.foundation.interaction.HoverInteraction$Enter
            r1.<init>()
            r3 = r1
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
            goto L9f
        L89:
            androidx.compose.material.DefaultButtonElevation r2 = r7.this$0
            float r2 = androidx.compose.material.DefaultButtonElevation.access$getFocusedElevation$p(r2)
            boolean r1 = androidx.compose.ui.unit.Dp.m7910equalsimpl0(r1, r2)
            if (r1 == 0) goto L9e
            androidx.compose.foundation.interaction.FocusInteraction$Focus r1 = new androidx.compose.foundation.interaction.FocusInteraction$Focus
            r1.<init>()
            r3 = r1
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
            goto L9f
        L9e:
        L9f:
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r7.$animatable
            float r2 = r7.$target
            androidx.compose.foundation.interaction.Interaction r4 = r7.$interaction
            r5 = r7
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6 = 2
            r7.label = r6
            java.lang.Object r1 = androidx.compose.material.ElevationKt.m1816animateElevationrAjV9yQ(r1, r2, r3, r4, r5)
            if (r1 != r0) goto Lb5
        Lb4:
            return r0
        Lb5:
        Lb6:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.DefaultButtonElevation$elevation$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
