package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.DefaultLoadControl;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyLayoutScrollScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0082\b\u001a\u0014\u0010\f\u001a\u00020\u0006*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a2\u0010\u0010\u001a\u00020\b*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0080@¢\u0006\u0002\u0010\u0015\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"TargetDistance", "Landroidx/compose/ui/unit/Dp;", "F", "BoundDistance", "MinimumDistance", "DEBUG", "", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "isItemVisible", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "index", "", "animateScrollToItem", "scrollOffset", "numOfItemsForTeleport", "density", "Landroidx/compose/ui/unit/Density;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;IIILandroidx/compose/ui/unit/Density;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutScrollScopeKt {
    private static final boolean DEBUG = false;
    private static final float TargetDistance = Dp.m7905constructorimpl(DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS);
    private static final float BoundDistance = Dp.m7905constructorimpl(1500);
    private static final float MinimumDistance = Dp.m7905constructorimpl(50);

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$animateScrollToItem$1, reason: invalid class name */
    /* JADX INFO: compiled from: LazyLayoutScrollScope.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt", f = "LazyLayoutScrollScope.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, l = {177, 264}, m = "animateScrollToItem", n = {"$this$animateScrollToItem", "loop", "anim", "loops", "index", "scrollOffset", "numOfItemsForTeleport", "targetDistancePx", "boundDistancePx", "minDistancePx", "forward", "$this$animateScrollToItem", "index", "scrollOffset"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2", "F$0", "F$1", "F$2", "I$3", "L$0", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LazyLayoutScrollScopeKt.animateScrollToItem(null, 0, 0, 0, null, this);
        }
    }

    private static final void debugLog(Function0<String> function0) {
    }

    public static final boolean isItemVisible(LazyLayoutScrollScope $this$isItemVisible, int index) {
        return index <= $this$isItemVisible.getLastVisibleItemIndex() && $this$isItemVisible.getFirstVisibleItemIndex() <= index;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00f9 A[Catch: ItemFoundInScroll -> 0x0244, TryCatch #2 {ItemFoundInScroll -> 0x0244, blocks: (B:34:0x00f5, B:36:0x00f9, B:38:0x00ff, B:50:0x0130, B:54:0x016c, B:58:0x0174), top: B:109:0x00f5 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [T, androidx.compose.animation.core.AnimationState] */
    /* JADX WARN: Type inference failed for: r8v21, types: [T, androidx.compose.animation.core.AnimationState] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:69:0x01c7 -> B:107:0x01d6). Please report as a decompilation issue!!! */
    public static final Object animateScrollToItem(LazyLayoutScrollScope lazyLayoutScrollScope, int i, int i2, int numOfItemsForTeleport, Density density, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        float f;
        LazyLayoutScrollScope $this$animateScrollToItem;
        int scrollOffset;
        int index;
        boolean z;
        final LazyLayoutScrollScope $this$animateScrollToItem2;
        int index2;
        float targetDistancePx;
        Ref.BooleanRef loop;
        int scrollOffset2;
        final int scrollOffset3;
        final int numOfItemsForTeleport2;
        Ref.ObjectRef anim;
        float minDistancePx;
        final float boundDistancePx;
        final Ref.IntRef loops;
        int scrollOffset4;
        LazyLayoutScrollScope $this$animateScrollToItem3;
        final float target;
        final LazyLayoutScrollScope $this$animateScrollToItem4;
        final int index3;
        int scrollOffset5;
        AnonymousClass1 anonymousClass2;
        Ref.IntRef loops2;
        Continuation<? super Unit> continuation2 = continuation;
        if (continuation2 instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation2;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation2);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation2);
        }
        AnonymousClass1 anonymousClass3 = anonymousClass1;
        Object $result = anonymousClass3.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z2 = true;
        switch (anonymousClass3.label) {
            case 0:
                f = 0.0f;
                ResultKt.throwOnFailure($result);
                $this$animateScrollToItem = lazyLayoutScrollScope;
                scrollOffset = i2;
                index = i;
                boolean value$iv = ((float) index) >= 0.0f;
                if (!value$iv) {
                    InlineClassHelperKt.throwIllegalArgumentException("Index should be non-negative");
                }
                try {
                    targetDistancePx = density.mo406toPx0680j_4(TargetDistance);
                    float boundDistancePx2 = density.mo406toPx0680j_4(BoundDistance);
                    float minDistancePx2 = density.mo406toPx0680j_4(MinimumDistance);
                    loop = new Ref.BooleanRef();
                    loop.element = true;
                    Ref.ObjectRef anim2 = new Ref.ObjectRef();
                    anim2.element = AnimationStateKt.AnimationState$default(0.0f, 0.0f, 0L, 0L, false, 30, null);
                    if (isItemVisible($this$animateScrollToItem, index)) {
                        z = false;
                        try {
                            int targetItemInitialOffset = LazyLayoutScrollScope.calculateDistanceTo$default($this$animateScrollToItem, index, 0, 2, null);
                            throw new ItemFoundInScroll(targetItemInitialOffset, (AnimationState) anim2.element);
                        } catch (ItemFoundInScroll e) {
                            itemFound = e;
                            $this$animateScrollToItem2 = $this$animateScrollToItem;
                            index2 = index;
                        }
                    } else {
                        try {
                            scrollOffset2 = index > $this$animateScrollToItem.getFirstVisibleItemIndex() ? 1 : 0;
                            Ref.IntRef loops3 = new Ref.IntRef();
                            loops3.element = 1;
                            scrollOffset3 = scrollOffset;
                            numOfItemsForTeleport2 = numOfItemsForTeleport;
                            anim = anim2;
                            minDistancePx = minDistancePx2;
                            boundDistancePx = boundDistancePx2;
                            loops = loops3;
                            try {
                                if (loop.element || $this$animateScrollToItem.getItemCount() <= 0) {
                                    return Unit.INSTANCE;
                                }
                                try {
                                    try {
                                        try {
                                            try {
                                                int expectedDistance = LazyLayoutScrollScope.calculateDistanceTo$default($this$animateScrollToItem, index, 0, 2, null) + scrollOffset3;
                                                if (Math.abs(expectedDistance) >= targetDistancePx) {
                                                    target = scrollOffset2 != 0 ? targetDistancePx : -targetDistancePx;
                                                } else {
                                                    float absTargetPx = Math.max(Math.abs(expectedDistance), minDistancePx);
                                                    target = scrollOffset2 != 0 ? absTargetPx : -absTargetPx;
                                                }
                                                anim.element = AnimationStateKt.copy$default((AnimationState) anim.element, 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                                                final Ref.FloatRef prevValue = new Ref.FloatRef();
                                                AnimationState animationState = (AnimationState) anim.element;
                                                Float fBoxFloat = Boxing.boxFloat(target);
                                                boolean z3 = (((Number) ((AnimationState) anim.element).getVelocity()).floatValue() == f ? z2 : false) ^ z2;
                                                final boolean z4 = scrollOffset2 != 0 ? z2 : false;
                                                final Ref.BooleanRef loop2 = loop;
                                                $this$animateScrollToItem4 = $this$animateScrollToItem;
                                                final Ref.ObjectRef anim3 = anim;
                                                index3 = index;
                                                Function1 function1 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda0
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        return LazyLayoutScrollScopeKt.animateScrollToItem$lambda$5($this$animateScrollToItem4, index3, target, prevValue, loop2, z4, boundDistancePx, loops, numOfItemsForTeleport2, scrollOffset3, anim3, (AnimationScope) obj);
                                                    }
                                                };
                                                Continuation<? super Unit> continuation3 = continuation2;
                                                Object $result2 = $result;
                                                $this$animateScrollToItem2 = $this$animateScrollToItem4;
                                                float boundDistancePx3 = boundDistancePx;
                                                Ref.IntRef loops4 = loops;
                                                int numOfItemsForTeleport3 = numOfItemsForTeleport2;
                                                scrollOffset5 = scrollOffset3;
                                                anonymousClass3.L$0 = $this$animateScrollToItem2;
                                                anonymousClass3.L$1 = loop2;
                                                anonymousClass3.L$2 = anim3;
                                                anonymousClass3.L$3 = loops4;
                                                anonymousClass3.I$0 = index3;
                                                anonymousClass3.I$1 = scrollOffset5;
                                                anonymousClass3.I$2 = numOfItemsForTeleport3;
                                                anonymousClass3.F$0 = targetDistancePx;
                                                anonymousClass3.F$1 = boundDistancePx3;
                                                anonymousClass3.F$2 = minDistancePx;
                                                anonymousClass3.I$3 = scrollOffset2;
                                                scrollOffset3 = scrollOffset5;
                                                anonymousClass3.label = 1;
                                                anonymousClass2 = anonymousClass3;
                                                if (SuspendAnimationKt.animateTo(animationState, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : null, (4 & 4) != 0 ? false : z3, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj) {
                                                        return Unit.INSTANCE;
                                                    }
                                                } : function1, anonymousClass2) == coroutine_suspended) {
                                                    return coroutine_suspended;
                                                }
                                                try {
                                                    continuation2 = continuation3;
                                                    boundDistancePx = boundDistancePx3;
                                                    loops2 = loops4;
                                                    loop = loop2;
                                                    numOfItemsForTeleport2 = numOfItemsForTeleport3;
                                                    anonymousClass3 = anonymousClass2;
                                                    index = index3;
                                                    anim = anim3;
                                                    $this$animateScrollToItem = $this$animateScrollToItem2;
                                                    $result = $result2;
                                                    int numOfItemsForTeleport4 = loops2.element;
                                                    loops2.element = numOfItemsForTeleport4 + 1;
                                                    loops = loops2;
                                                    z2 = true;
                                                    if (loop.element) {
                                                    }
                                                } catch (ItemFoundInScroll e2) {
                                                    itemFound = e2;
                                                    $this$animateScrollToItem2 = $this$animateScrollToItem;
                                                    scrollOffset = scrollOffset3;
                                                    z = false;
                                                    index2 = index;
                                                }
                                                return Unit.INSTANCE;
                                            } catch (ItemFoundInScroll e3) {
                                                itemFound = e3;
                                                anonymousClass3 = anonymousClass2;
                                                scrollOffset = scrollOffset3;
                                                z = false;
                                                index2 = index3;
                                            }
                                        } catch (ItemFoundInScroll e4) {
                                            itemFound = e4;
                                            $this$animateScrollToItem2 = $this$animateScrollToItem4;
                                            scrollOffset = scrollOffset3;
                                            z = false;
                                            index2 = index3;
                                        }
                                    } catch (ItemFoundInScroll e5) {
                                        itemFound = e5;
                                        scrollOffset = scrollOffset5;
                                        z = false;
                                        index2 = index3;
                                    }
                                } catch (ItemFoundInScroll e6) {
                                    itemFound = e6;
                                    scrollOffset = scrollOffset3;
                                    z = false;
                                    index2 = index3;
                                }
                            } catch (ItemFoundInScroll e7) {
                                itemFound = e7;
                                $this$animateScrollToItem2 = $this$animateScrollToItem;
                                scrollOffset = scrollOffset3;
                                z = false;
                                index2 = index;
                            }
                        } catch (ItemFoundInScroll e8) {
                            itemFound = e8;
                            $this$animateScrollToItem2 = $this$animateScrollToItem;
                            z = false;
                            index2 = index;
                        }
                    }
                } catch (ItemFoundInScroll e9) {
                    itemFound = e9;
                    z = false;
                    $this$animateScrollToItem2 = $this$animateScrollToItem;
                    index2 = index;
                }
                AnimationState anim4 = AnimationStateKt.copy$default((AnimationState) itemFound.getPreviousAnimation(), 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
                final float target2 = itemFound.getItemOffset() + scrollOffset;
                final Ref.FloatRef prevValue2 = new Ref.FloatRef();
                Float fBoxFloat2 = Boxing.boxFloat(target2);
                boolean z5 = !(((Number) anim4.getVelocity()).floatValue() == f ? true : z);
                Function1 function2 = new Function1() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LazyLayoutScrollScopeKt.animateScrollToItem$lambda$7(target2, prevValue2, $this$animateScrollToItem2, (AnimationScope) obj);
                    }
                };
                anonymousClass3.L$0 = $this$animateScrollToItem2;
                anonymousClass3.L$1 = null;
                anonymousClass3.L$2 = null;
                anonymousClass3.L$3 = null;
                anonymousClass3.I$0 = index2;
                anonymousClass3.I$1 = scrollOffset;
                anonymousClass3.label = 2;
                Continuation $continuation = anonymousClass3;
                if (SuspendAnimationKt.animateTo(anim4, fBoxFloat2, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : null, (4 & 4) != 0 ? false : z5, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                } : function2, $continuation) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                scrollOffset4 = scrollOffset;
                $this$animateScrollToItem3 = $this$animateScrollToItem2;
                $this$animateScrollToItem3.snapToItem(index2, scrollOffset4);
                return Unit.INSTANCE;
            case 1:
                int scrollOffset6 = anonymousClass3.I$3;
                minDistancePx = anonymousClass3.F$2;
                float boundDistancePx4 = anonymousClass3.F$1;
                targetDistancePx = anonymousClass3.F$0;
                int numOfItemsForTeleport5 = anonymousClass3.I$2;
                int scrollOffset7 = anonymousClass3.I$1;
                int index4 = anonymousClass3.I$0;
                f = 0.0f;
                Ref.IntRef loops5 = (Ref.IntRef) anonymousClass3.L$3;
                Ref.ObjectRef anim5 = (Ref.ObjectRef) anonymousClass3.L$2;
                Ref.BooleanRef loop3 = (Ref.BooleanRef) anonymousClass3.L$1;
                LazyLayoutScrollScope $this$animateScrollToItem5 = (LazyLayoutScrollScope) anonymousClass3.L$0;
                try {
                    ResultKt.throwOnFailure($result);
                    anim = anim5;
                    $this$animateScrollToItem = $this$animateScrollToItem5;
                    loops2 = loops5;
                    boundDistancePx = boundDistancePx4;
                    numOfItemsForTeleport2 = numOfItemsForTeleport5;
                    scrollOffset3 = scrollOffset7;
                    scrollOffset2 = scrollOffset6;
                    loop = loop3;
                    index = index4;
                    int numOfItemsForTeleport6 = loops2.element;
                    loops2.element = numOfItemsForTeleport6 + 1;
                    loops = loops2;
                    z2 = true;
                    if (loop.element) {
                    }
                    break;
                } catch (ItemFoundInScroll e10) {
                    itemFound = e10;
                    scrollOffset = scrollOffset7;
                    index2 = index4;
                    z = false;
                    $this$animateScrollToItem2 = $this$animateScrollToItem5;
                    break;
                }
                return Unit.INSTANCE;
            case 2:
                scrollOffset4 = anonymousClass3.I$1;
                index2 = anonymousClass3.I$0;
                $this$animateScrollToItem3 = (LazyLayoutScrollScope) anonymousClass3.L$0;
                ResultKt.throwOnFailure($result);
                $this$animateScrollToItem3.snapToItem(index2, scrollOffset4);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private static final boolean animateScrollToItem$isOvershot(boolean forward, LazyLayoutScrollScope $this_animateScrollToItem, int $index, int $scrollOffset) {
        if (forward) {
            if ($this_animateScrollToItem.getFirstVisibleItemIndex() > $index) {
                return true;
            }
            return $this_animateScrollToItem.getFirstVisibleItemIndex() == $index && $this_animateScrollToItem.getFirstVisibleItemScrollOffset() > $scrollOffset;
        }
        if ($this_animateScrollToItem.getFirstVisibleItemIndex() < $index) {
            return true;
        }
        return $this_animateScrollToItem.getFirstVisibleItemIndex() == $index && $this_animateScrollToItem.getFirstVisibleItemScrollOffset() < $scrollOffset;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final Unit animateScrollToItem$lambda$5(LazyLayoutScrollScope $this_animateScrollToItem, int $index, float $target, Ref.FloatRef $prevValue, Ref.BooleanRef $loop, boolean $forward, float $boundDistancePx, Ref.IntRef $loops, int $numOfItemsForTeleport, int $scrollOffset, Ref.ObjectRef $anim, AnimationScope $this$animateTo) {
        float coercedValue;
        if (!isItemVisible($this_animateScrollToItem, $index)) {
            if ($target > 0.0f) {
                coercedValue = RangesKt.coerceAtMost(((Number) $this$animateTo.getValue()).floatValue(), $target);
            } else {
                coercedValue = RangesKt.coerceAtLeast(((Number) $this$animateTo.getValue()).floatValue(), $target);
            }
            float delta = coercedValue - $prevValue.element;
            float consumed = $this_animateScrollToItem.scrollBy(delta);
            if (!isItemVisible($this_animateScrollToItem, $index) && !animateScrollToItem$isOvershot($forward, $this_animateScrollToItem, $index, $scrollOffset)) {
                if (!(delta == consumed)) {
                    $this$animateTo.cancelAnimation();
                    $loop.element = false;
                    return Unit.INSTANCE;
                }
                $prevValue.element += delta;
                if ($forward) {
                    if (((Number) $this$animateTo.getValue()).floatValue() > $boundDistancePx) {
                        $this$animateTo.cancelAnimation();
                    }
                } else if (((Number) $this$animateTo.getValue()).floatValue() < (-$boundDistancePx)) {
                    $this$animateTo.cancelAnimation();
                }
                if ($forward) {
                    if ($loops.element >= 2 && $index - $this_animateScrollToItem.getLastVisibleItemIndex() > $numOfItemsForTeleport) {
                        int $i$f$debugLog = $index - $numOfItemsForTeleport;
                        $this_animateScrollToItem.snapToItem($i$f$debugLog, 0);
                    }
                } else if ($loops.element >= 2 && $this_animateScrollToItem.getFirstVisibleItemIndex() - $index > $numOfItemsForTeleport) {
                    int $i$f$debugLog2 = $index + $numOfItemsForTeleport;
                    $this_animateScrollToItem.snapToItem($i$f$debugLog2, 0);
                }
            }
        }
        if (animateScrollToItem$isOvershot($forward, $this_animateScrollToItem, $index, $scrollOffset)) {
            $this_animateScrollToItem.snapToItem($index, $scrollOffset);
            $loop.element = false;
            $this$animateTo.cancelAnimation();
            return Unit.INSTANCE;
        }
        if (isItemVisible($this_animateScrollToItem, $index)) {
            int targetItemOffset = LazyLayoutScrollScope.calculateDistanceTo$default($this_animateScrollToItem, $index, 0, 2, null);
            throw new ItemFoundInScroll(targetItemOffset, (AnimationState) $anim.element);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0056  */
    static final Unit animateScrollToItem$lambda$7(float $target, Ref.FloatRef $prevValue, LazyLayoutScrollScope $this_animateScrollToItem, AnimationScope $this$animateTo) {
        float coercedValue = 0.0f;
        if ($target > 0.0f) {
            coercedValue = RangesKt.coerceAtMost(((Number) $this$animateTo.getValue()).floatValue(), $target);
        } else if ($target < 0.0f) {
            coercedValue = RangesKt.coerceAtLeast(((Number) $this$animateTo.getValue()).floatValue(), $target);
        }
        float delta = coercedValue - $prevValue.element;
        float consumed = $this_animateScrollToItem.scrollBy(delta);
        if (delta == consumed) {
            if (!(coercedValue == ((Number) $this$animateTo.getValue()).floatValue())) {
                $this$animateTo.cancelAnimation();
            }
        } else {
            $this$animateTo.cancelAnimation();
        }
        $prevValue.element += delta;
        return Unit.INSTANCE;
    }
}
