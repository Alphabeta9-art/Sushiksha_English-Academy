package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\fJ \u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"androidx/compose/material3/ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ ExitUntilCollapsedScrollBehavior this$0;

    ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1(ExitUntilCollapsedScrollBehavior $receiver) {
        this.this$0 = $receiver;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo897onPreScrollOzD1aCk(long available, int source) {
        if (this.this$0.getCanScroll().invoke().booleanValue()) {
            int bits$iv$iv$iv = (int) (available & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv) <= 0.0f) {
                float prevHeightOffset = this.this$0.getState().getHeightOffset();
                int bits$iv$iv$iv2 = (int) (4294967295L & available);
                this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Float.intBitsToFloat(bits$iv$iv$iv2));
                return !((prevHeightOffset > this.this$0.getState().getHeightOffset() ? 1 : (prevHeightOffset == this.this$0.getState().getHeightOffset() ? 0 : -1)) == 0) ? Offset.m4846copydBAh8RU$default(available, 0.0f, 0.0f, 2, null) : Offset.INSTANCE.m4868getZeroF1C5BW0();
            }
        }
        return Offset.INSTANCE.m4868getZeroF1C5BW0();
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo574onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.this$0.getCanScroll().invoke().booleanValue()) {
            return Offset.INSTANCE.m4868getZeroF1C5BW0();
        }
        TopAppBarState state = this.this$0.getState();
        int bits$iv$iv$iv = (int) (consumed & 4294967295L);
        state.setContentOffset(state.getContentOffset() + Float.intBitsToFloat(bits$iv$iv$iv));
        int bits$iv$iv$iv2 = (int) (available & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv2) >= 0.0f) {
            int bits$iv$iv$iv3 = (int) (consumed & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv3) >= 0.0f) {
                int bits$iv$iv$iv4 = (int) (available & 4294967295L);
                if (Float.intBitsToFloat(bits$iv$iv$iv4) <= 0.0f) {
                    return Offset.INSTANCE.m4868getZeroF1C5BW0();
                }
                float oldHeightOffset = this.this$0.getState().getHeightOffset();
                int bits$iv$iv$iv5 = (int) (available & 4294967295L);
                this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Float.intBitsToFloat(bits$iv$iv$iv5));
                float y$iv = this.this$0.getState().getHeightOffset() - oldHeightOffset;
                long v1$iv$iv = Float.floatToRawIntBits(0.0f);
                long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                return Offset.m4844constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
            }
        }
        float oldHeightOffset2 = this.this$0.getState().getHeightOffset();
        int bits$iv$iv$iv6 = (int) (consumed & 4294967295L);
        this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Float.intBitsToFloat(bits$iv$iv$iv6));
        float y$iv2 = this.this$0.getState().getHeightOffset() - oldHeightOffset2;
        long v1$iv$iv2 = Float.floatToRawIntBits(0.0f);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        return Offset.m4844constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2));
    }

    /* JADX WARN: Code duplicated, block: B:23:0x008b A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo573onPostFlingRZ2iAVY(long consumed, long available, Continuation<? super Velocity> continuation) {
        ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1 exitUntilCollapsedScrollBehavior$nestedScrollConnection$1;
        Object objMo573onPostFlingRZ2iAVY;
        long available2;
        long packedValue;
        Object obj;
        if (continuation instanceof ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1) {
            exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 = (ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1) continuation;
            if ((exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
            } else {
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 = new ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1(this, continuation);
            }
        } else {
            exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 = new ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1(this, continuation);
        }
        ExitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        Object $result = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1 = this;
                if (Velocity.m8144getYimpl(available) > 0.0f) {
                    exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getState().setContentOffset(0.0f);
                }
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = available;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 1;
                objMo573onPostFlingRZ2iAVY = super.mo573onPostFlingRZ2iAVY(consumed, available, exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2);
                if (objMo573onPostFlingRZ2iAVY == coroutine_suspended) {
                    return coroutine_suspended;
                }
                available2 = available;
                packedValue = ((Velocity) objMo573onPostFlingRZ2iAVY).getPackedValue();
                TopAppBarState state = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getState();
                float fM8144getYimpl = Velocity.m8144getYimpl(available2);
                DecayAnimationSpec<Float> flingAnimationSpec = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getFlingAnimationSpec();
                AnimationSpec<Float> snapAnimationSpec = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getSnapAnimationSpec();
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = packedValue;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 2;
                obj = AppBarKt.settleAppBar(state, fM8144getYimpl, flingAnimationSpec, snapAnimationSpec, exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Velocity.m8134boximpl(Velocity.m8147plusAH228Gc(packedValue, ((Velocity) obj).getPackedValue()));
            case 1:
                available2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0;
                ResultKt.throwOnFailure($result);
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1 = this;
                objMo573onPostFlingRZ2iAVY = $result;
                packedValue = ((Velocity) objMo573onPostFlingRZ2iAVY).getPackedValue();
                TopAppBarState state2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getState();
                float fM8144getYimpl2 = Velocity.m8144getYimpl(available2);
                DecayAnimationSpec<Float> flingAnimationSpec2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getFlingAnimationSpec();
                AnimationSpec<Float> snapAnimationSpec2 = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1.this$0.getSnapAnimationSpec();
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = packedValue;
                exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 2;
                obj = AppBarKt.settleAppBar(state2, fM8144getYimpl2, flingAnimationSpec2, snapAnimationSpec2, exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Velocity.m8134boximpl(Velocity.m8147plusAH228Gc(packedValue, ((Velocity) obj).getPackedValue()));
            case 2:
                long j = exitUntilCollapsedScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0;
                ResultKt.throwOnFailure($result);
                packedValue = j;
                obj = $result;
                return Velocity.m8134boximpl(Velocity.m8147plusAH228Gc(packedValue, ((Velocity) obj).getPackedValue()));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
