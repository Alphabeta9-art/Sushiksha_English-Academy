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
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000bH\u0096@¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"androidx/compose/material3/ExitAlwaysScrollBehavior$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "consumed", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ExitAlwaysScrollBehavior$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ ExitAlwaysScrollBehavior this$0;

    ExitAlwaysScrollBehavior$nestedScrollConnection$1(ExitAlwaysScrollBehavior $receiver) {
        this.this$0 = $receiver;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo574onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.this$0.getCanScroll().invoke().booleanValue()) {
            return Offset.INSTANCE.m4868getZeroF1C5BW0();
        }
        BottomAppBarState state = this.this$0.getState();
        int bits$iv$iv$iv = (int) (consumed & 4294967295L);
        state.setContentOffset(state.getContentOffset() + Float.intBitsToFloat(bits$iv$iv$iv));
        int bits$iv$iv$iv2 = (int) (4294967295L & consumed);
        this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Float.intBitsToFloat(bits$iv$iv$iv2));
        return Offset.INSTANCE.m4868getZeroF1C5BW0();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0080  */
    /* JADX WARN: Code duplicated, block: B:33:0x00bd A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo573onPostFlingRZ2iAVY(long consumed, long available, Continuation<? super Velocity> continuation) {
        ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        ExitAlwaysScrollBehavior$nestedScrollConnection$1 exitAlwaysScrollBehavior$nestedScrollConnection$1;
        Object objMo573onPostFlingRZ2iAVY;
        long available2;
        long packedValue;
        Object obj;
        if (continuation instanceof ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1) {
            exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 = (ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1) continuation;
            if ((exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
            } else {
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 = new ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1(this, continuation);
            }
        } else {
            exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 = new ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1(this, continuation);
        }
        ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2 = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        Object $result = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                exitAlwaysScrollBehavior$nestedScrollConnection$1 = this;
                if (Velocity.m8144getYimpl(available) > 0.0f) {
                    if (exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getState().getHeightOffset() == 0.0f) {
                        exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getState().setContentOffset(0.0f);
                    } else {
                        if (exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getState().getHeightOffset() == exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getState().getHeightOffsetLimit()) {
                            exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getState().setContentOffset(0.0f);
                        }
                    }
                }
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = available;
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 1;
                objMo573onPostFlingRZ2iAVY = super.mo573onPostFlingRZ2iAVY(consumed, available, exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2);
                if (objMo573onPostFlingRZ2iAVY == coroutine_suspended) {
                    return coroutine_suspended;
                }
                available2 = available;
                packedValue = ((Velocity) objMo573onPostFlingRZ2iAVY).getPackedValue();
                BottomAppBarState state = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getState();
                float fM8144getYimpl = Velocity.m8144getYimpl(available2);
                DecayAnimationSpec<Float> flingAnimationSpec = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getFlingAnimationSpec();
                AnimationSpec<Float> snapAnimationSpec = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getSnapAnimationSpec();
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = packedValue;
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 2;
                obj = AppBarKt.settleAppBarBottom(state, fM8144getYimpl, flingAnimationSpec, snapAnimationSpec, exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Velocity.m8134boximpl(Velocity.m8147plusAH228Gc(packedValue, ((Velocity) obj).getPackedValue()));
            case 1:
                available2 = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0;
                ResultKt.throwOnFailure($result);
                exitAlwaysScrollBehavior$nestedScrollConnection$1 = this;
                objMo573onPostFlingRZ2iAVY = $result;
                packedValue = ((Velocity) objMo573onPostFlingRZ2iAVY).getPackedValue();
                BottomAppBarState state2 = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getState();
                float fM8144getYimpl2 = Velocity.m8144getYimpl(available2);
                DecayAnimationSpec<Float> flingAnimationSpec2 = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getFlingAnimationSpec();
                AnimationSpec<Float> snapAnimationSpec2 = exitAlwaysScrollBehavior$nestedScrollConnection$1.this$0.getSnapAnimationSpec();
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = packedValue;
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 2;
                obj = AppBarKt.settleAppBarBottom(state2, fM8144getYimpl2, flingAnimationSpec2, snapAnimationSpec2, exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Velocity.m8134boximpl(Velocity.m8147plusAH228Gc(packedValue, ((Velocity) obj).getPackedValue()));
            case 2:
                long j = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0;
                ResultKt.throwOnFailure($result);
                packedValue = j;
                obj = $result;
                return Velocity.m8134boximpl(Velocity.m8147plusAH228Gc(packedValue, ((Velocity) obj).getPackedValue()));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
