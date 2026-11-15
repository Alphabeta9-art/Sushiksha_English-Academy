package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.material3.ProgressIndicatorKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: Clickable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.AbstractClickableNode$handlePressInteraction$2$1", f = "Clickable.kt", i = {0, 1, 2}, l = {ProgressIndicatorKt.LinearAnimationDuration, 1752, 1759, 1760, 1770}, m = "invokeSuspend", n = {"delayJob", "success", "release"}, s = {"L$0", "Z$0", "L$0"}, v = 1)
final class AbstractClickableNode$handlePressInteraction$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-offset$0, reason: not valid java name */
    final /* synthetic */ long f2$$v$c$androidxcomposeuigeometryOffset$offset$0;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ PressGestureScope $this_handlePressInteraction;
    private /* synthetic */ Object L$0;
    boolean Z$0;
    int label;
    final /* synthetic */ AbstractClickableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AbstractClickableNode$handlePressInteraction$2$1(PressGestureScope pressGestureScope, long j, MutableInteractionSource mutableInteractionSource, AbstractClickableNode abstractClickableNode, Continuation<? super AbstractClickableNode$handlePressInteraction$2$1> continuation) {
        super(2, continuation);
        this.$this_handlePressInteraction = pressGestureScope;
        this.f2$$v$c$androidxcomposeuigeometryOffset$offset$0 = j;
        this.$interactionSource = mutableInteractionSource;
        this.this$0 = abstractClickableNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AbstractClickableNode$handlePressInteraction$2$1 abstractClickableNode$handlePressInteraction$2$1 = new AbstractClickableNode$handlePressInteraction$2$1(this.$this_handlePressInteraction, this.f2$$v$c$androidxcomposeuigeometryOffset$offset$0, this.$interactionSource, this.this$0, continuation);
        abstractClickableNode$handlePressInteraction$2$1.L$0 = obj;
        return abstractClickableNode$handlePressInteraction$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AbstractClickableNode$handlePressInteraction$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0077  */
    /* JADX WARN: Code duplicated, block: B:19:0x0087 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x0088  */
    /* JADX WARN: Code duplicated, block: B:22:0x008d  */
    /* JADX WARN: Code duplicated, block: B:24:0x00ac A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:28:0x00c1 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:30:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:31:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:33:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:35:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:36:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:39:0x00f3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:40:0x00f4  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Job delayJob;
        boolean success;
        PressInteraction.Press pressInteraction;
        MutableInteractionSource mutableInteractionSource;
        PressInteraction endInteraction;
        boolean success2;
        PressInteraction.Press press;
        PressInteraction.Release release;
        PressInteraction.Release release2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                Job delayJob2 = BuildersKt__Builders_commonKt.launch$default($this$coroutineScope, null, null, new AbstractClickableNode$handlePressInteraction$2$1$delayJob$1(this.this$0, this.f2$$v$c$androidxcomposeuigeometryOffset$offset$0, this.$interactionSource, null), 3, null);
                this.L$0 = delayJob2;
                this.label = 1;
                Object objTryAwaitRelease = this.$this_handlePressInteraction.tryAwaitRelease(this);
                if (objTryAwaitRelease == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result = objTryAwaitRelease;
                delayJob = delayJob2;
                success = ((Boolean) $result).booleanValue();
                if (delayJob.isActive()) {
                    this.L$0 = null;
                    this.Z$0 = success;
                    this.label = 2;
                    if (JobKt.cancelAndJoin(delayJob, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    success2 = success;
                    if (success2) {
                        $result = $result;
                        press = new PressInteraction.Press(this.f2$$v$c$androidxcomposeuigeometryOffset$offset$0, null);
                        release = new PressInteraction.Release(press);
                        this.L$0 = release;
                        this.label = 3;
                        if (this.$interactionSource.emit(press, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        release2 = release;
                        this.L$0 = null;
                        this.label = 4;
                        if (this.$interactionSource.emit(release2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        $result = $result;
                    }
                } else {
                    pressInteraction = this.this$0.pressInteraction;
                    if (pressInteraction != null) {
                        mutableInteractionSource = this.$interactionSource;
                        if (success) {
                            endInteraction = new PressInteraction.Release(pressInteraction);
                        } else {
                            endInteraction = new PressInteraction.Cancel(pressInteraction);
                        }
                        this.L$0 = null;
                        this.label = 5;
                        if (mutableInteractionSource.emit(endInteraction, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = $result;
                    }
                }
                this.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            case 1:
                Job delayJob3 = (Job) this.L$0;
                ResultKt.throwOnFailure($result);
                delayJob = delayJob3;
                success = ((Boolean) $result).booleanValue();
                if (delayJob.isActive()) {
                    this.L$0 = null;
                    this.Z$0 = success;
                    this.label = 2;
                    if (JobKt.cancelAndJoin(delayJob, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    success2 = success;
                    if (success2) {
                        $result = $result;
                        press = new PressInteraction.Press(this.f2$$v$c$androidxcomposeuigeometryOffset$offset$0, null);
                        release = new PressInteraction.Release(press);
                        this.L$0 = release;
                        this.label = 3;
                        if (this.$interactionSource.emit(press, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        release2 = release;
                        this.L$0 = null;
                        this.label = 4;
                        if (this.$interactionSource.emit(release2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        $result = $result;
                    }
                } else {
                    pressInteraction = this.this$0.pressInteraction;
                    if (pressInteraction != null) {
                        mutableInteractionSource = this.$interactionSource;
                        if (success) {
                            endInteraction = new PressInteraction.Release(pressInteraction);
                        } else {
                            endInteraction = new PressInteraction.Cancel(pressInteraction);
                        }
                        this.L$0 = null;
                        this.label = 5;
                        if (mutableInteractionSource.emit(endInteraction, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result = $result;
                    }
                }
                this.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            case 2:
                success2 = this.Z$0;
                ResultKt.throwOnFailure($result);
                if (success2) {
                    $result = $result;
                    press = new PressInteraction.Press(this.f2$$v$c$androidxcomposeuigeometryOffset$offset$0, null);
                    release = new PressInteraction.Release(press);
                    this.L$0 = release;
                    this.label = 3;
                    if (this.$interactionSource.emit(press, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    release2 = release;
                    this.L$0 = null;
                    this.label = 4;
                    if (this.$interactionSource.emit(release2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    $result = $result;
                }
                this.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            case 3:
                release2 = (PressInteraction.Release) this.L$0;
                ResultKt.throwOnFailure($result);
                this.L$0 = null;
                this.label = 4;
                if (this.$interactionSource.emit(release2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                this.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            case 4:
                ResultKt.throwOnFailure($result);
                this.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            case 5:
                ResultKt.throwOnFailure($result);
                this.this$0.pressInteraction = null;
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
