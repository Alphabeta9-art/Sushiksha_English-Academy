package androidx.compose.foundation;

import android.content.Context;
import android.os.Build;
import android.widget.EdgeEffect;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AndroidOverscroll.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ3\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0$H\u0016¢\u0006\u0004\b%\u0010&J<\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)2\"\u0010*\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0,\u0012\u0006\u0012\u0004\u0018\u00010-0+H\u0096@¢\u0006\u0004\b.\u0010/J\u0017\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u000201H\u0000¢\u0006\u0004\b5\u00106J\u000f\u00109\u001a\u00020\rH\u0000¢\u0006\u0004\b:\u0010;J\r\u0010B\u001a\u00020\u0013H\u0000¢\u0006\u0002\bCJ\b\u0010D\u001a\u00020\u0013H\u0002J\u0017\u0010E\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\rH\u0002¢\u0006\u0004\bF\u0010GJ\u0017\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bK\u0010LJ\u0017\u0010M\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bN\u0010LJ\u0017\u0010O\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bP\u0010LJ\u0017\u0010Q\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bR\u0010LR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00178\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u00102\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u001bR\u0010\u00107\u001a\u000208X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\u00020?X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010A¨\u0006S"}, d2 = {"Landroidx/compose/foundation/AndroidEdgeEffectOverscrollEffect;", "Landroidx/compose/foundation/OverscrollEffect;", "context", "Landroid/content/Context;", "density", "Landroidx/compose/ui/unit/Density;", "glowColor", "Landroidx/compose/ui/graphics/Color;", "glowDrawPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "<init>", "(Landroid/content/Context;Landroidx/compose/ui/unit/Density;JLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "edgeEffectWrapper", "Landroidx/compose/foundation/EdgeEffectWrapper;", "redrawSignal", "Landroidx/compose/runtime/MutableState;", "", "getRedrawSignal$foundation", "()Landroidx/compose/runtime/MutableState;", "invalidationEnabled", "", "getInvalidationEnabled$foundation$annotations", "()V", "getInvalidationEnabled$foundation", "()Z", "setInvalidationEnabled$foundation", "(Z)V", "scrollCycleInProgress", "applyToScroll", "delta", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "performScroll", "Lkotlin/Function1;", "applyToScroll-Rhakbz0", "(JILkotlin/jvm/functions/Function1;)J", "applyToFling", "velocity", "Landroidx/compose/ui/unit/Velocity;", "performFling", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "applyToFling-BMRW4eQ", "(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "containerSize", "Landroidx/compose/ui/geometry/Size;", "isInProgress", "updateSize", "size", "updateSize-uvyYCjk$foundation", "(J)V", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "displacement", "displacement-F1C5BW0$foundation", "()J", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "node", "Landroidx/compose/ui/node/DelegatableNode;", "getNode", "()Landroidx/compose/ui/node/DelegatableNode;", "invalidateOverscroll", "invalidateOverscroll$foundation", "animateToReleaseIfNeeded", "releaseOppositeOverscroll", "releaseOppositeOverscroll-k-4lQ0M", "(J)Z", "pullTop", "", "scroll", "pullTop-k-4lQ0M", "(J)F", "pullBottom", "pullBottom-k-4lQ0M", "pullLeft", "pullLeft-k-4lQ0M", "pullRight", "pullRight-k-4lQ0M", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidEdgeEffectOverscrollEffect implements OverscrollEffect {
    public static final int $stable = 0;
    private long containerSize;
    private final Density density;
    private final EdgeEffectWrapper edgeEffectWrapper;
    private boolean invalidationEnabled;
    private final DelegatableNode node;
    private long pointerId;
    private final SuspendingPointerInputModifierNode pointerInputNode;
    private long pointerPosition;
    private final MutableState<Unit> redrawSignal;
    private boolean scrollCycleInProgress;

    public /* synthetic */ AndroidEdgeEffectOverscrollEffect(Context context, Density density, long j, PaddingValues paddingValues, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, density, j, paddingValues);
    }

    public static /* synthetic */ void getInvalidationEnabled$foundation$annotations() {
    }

    private AndroidEdgeEffectOverscrollEffect(Context context, Density density, long glowColor, PaddingValues glowDrawPadding) {
        GlowOverscrollNode glowOverscrollNode;
        this.density = density;
        this.pointerPosition = Offset.INSTANCE.m4867getUnspecifiedF1C5BW0();
        this.edgeEffectWrapper = new EdgeEffectWrapper(context, ColorKt.m5151toArgb8_81llA(glowColor));
        this.redrawSignal = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.invalidationEnabled = true;
        this.containerSize = Size.INSTANCE.m4930getZeroNHjbRc();
        this.pointerId = PointerId.m6390constructorimpl(-1L);
        this.pointerInputNode = SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1

            /* JADX INFO: renamed from: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: AndroidOverscroll.android.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1$1", f = "AndroidOverscroll.android.kt", i = {0, 1}, l = {783, 787}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, s = {"L$0", "L$0"}, v = 1)
            static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ AndroidEdgeEffectOverscrollEffect this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = androidEdgeEffectOverscrollEffect;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:25:0x00c9  */
                /* JADX WARN: Code duplicated, block: B:27:0x00e7 A[LOOP:1: B:24:0x00c7->B:27:0x00e7, LOOP_END] */
                /* JADX WARN: Code duplicated, block: B:47:0x00f4 A[SYNTHETIC] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0076 -> B:17:0x007d). Please report as a decompilation issue!!! */
                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final java.lang.Object invokeSuspend(java.lang.Object r21) {
                    /*
                        Method dump skipped, instruction units count: 326
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$SuspendingPointerInputModifierNode, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$SuspendingPointerInputModifierNode, new AnonymousClass1(this.this$0, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        });
        if (Build.VERSION.SDK_INT >= 31) {
            glowOverscrollNode = new StretchOverscrollNode(this.pointerInputNode, this, this.edgeEffectWrapper);
        } else {
            glowOverscrollNode = new GlowOverscrollNode(this.pointerInputNode, this, this.edgeEffectWrapper, glowDrawPadding);
        }
        this.node = glowOverscrollNode;
    }

    public final MutableState<Unit> getRedrawSignal$foundation() {
        return this.redrawSignal;
    }

    /* JADX INFO: renamed from: getInvalidationEnabled$foundation, reason: from getter */
    public final boolean getInvalidationEnabled() {
        return this.invalidationEnabled;
    }

    public final void setInvalidationEnabled$foundation(boolean z) {
        this.invalidationEnabled = z;
    }

    /* JADX WARN: Code duplicated, block: B:107:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:109:0x02ff  */
    /* JADX WARN: Code duplicated, block: B:110:0x0301  */
    /* JADX WARN: Code duplicated, block: B:112:0x0304  */
    /* JADX WARN: Code duplicated, block: B:114:0x031a  */
    /* JADX WARN: Code duplicated, block: B:115:0x031c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0320  */
    /* JADX WARN: Code duplicated, block: B:120:0x032a  */
    /* JADX WARN: Code duplicated, block: B:126:0x033c  */
    /* JADX WARN: Code duplicated, block: B:57:0x0172  */
    /* JADX WARN: Code duplicated, block: B:78:0x0204  */
    /* JADX WARN: Code duplicated, block: B:80:0x020d  */
    /* JADX WARN: Code duplicated, block: B:82:0x0220  */
    /* JADX WARN: Code duplicated, block: B:84:0x022e  */
    /* JADX WARN: Code duplicated, block: B:87:0x024f  */
    /* JADX WARN: Code duplicated, block: B:88:0x0251  */
    /* JADX WARN: Code duplicated, block: B:90:0x0254  */
    /* JADX WARN: Code duplicated, block: B:91:0x0264  */
    /* JADX WARN: Code duplicated, block: B:92:0x0267  */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToScroll-Rhakbz0, reason: not valid java name */
    public long mo243applyToScrollRhakbz0(long delta, int source, Function1<? super Offset, Offset> performScroll) {
        float f;
        long destretchDelta;
        float consumedPixelsY;
        float consumedPixelsY2;
        float consumedPixelsX;
        int bits$iv$iv$iv;
        float consumed;
        int bits$iv$iv$iv2;
        boolean z;
        int bits$iv$iv$iv3;
        boolean z2;
        EdgeEffectWrapper $this$applyToScroll_Rhakbz0_u24lambda_u244;
        int bits$iv$iv$iv4;
        boolean z3;
        float f2;
        boolean appliedHorizontalOverscroll;
        boolean appliedVerticalOverscroll;
        if (Size.m4923isEmptyimpl(this.containerSize)) {
            return performScroll.invoke(Offset.m4841boximpl(delta)).m4862unboximpl();
        }
        if (!this.scrollCycleInProgress) {
            if (this.edgeEffectWrapper.isLeftStretched()) {
                m238pullLeftk4lQ0M(Offset.INSTANCE.m4868getZeroF1C5BW0());
            }
            if (this.edgeEffectWrapper.isRightStretched()) {
                m239pullRightk4lQ0M(Offset.INSTANCE.m4868getZeroF1C5BW0());
            }
            if (this.edgeEffectWrapper.isTopStretched()) {
                m240pullTopk4lQ0M(Offset.INSTANCE.m4868getZeroF1C5BW0());
            }
            if (this.edgeEffectWrapper.isBottomStretched()) {
                m237pullBottomk4lQ0M(Offset.INSTANCE.m4868getZeroF1C5BW0());
            }
            this.scrollCycleInProgress = true;
        }
        float destretchMultiplier = AndroidOverscroll_androidKt.m262destretchMultiplierGyEprt8(source);
        long destretchDelta2 = Offset.m4859timestuRUvjQ(delta, destretchMultiplier);
        int bits$iv$iv$iv5 = (int) (delta & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv5) == 0.0f) {
            destretchDelta = destretchDelta2;
            consumedPixelsY = 0.0f;
            f = 0.0f;
        } else {
            if (this.edgeEffectWrapper.isTopStretched()) {
                f = 0.0f;
                int bits$iv$iv$iv6 = (int) (delta & 4294967295L);
                if (Float.intBitsToFloat(bits$iv$iv$iv6) < 0.0f) {
                    float consumed2 = m240pullTopk4lQ0M(destretchDelta2);
                    if (!this.edgeEffectWrapper.isTopStretched()) {
                        this.edgeEffectWrapper.getOrCreateTopEffect().finish();
                    }
                    int bits$iv$iv$iv7 = (int) (destretchDelta2 & 4294967295L);
                    if (consumed2 == Float.intBitsToFloat(bits$iv$iv$iv7)) {
                        int bits$iv$iv$iv8 = (int) (delta & 4294967295L);
                        consumedPixelsY = Float.intBitsToFloat(bits$iv$iv$iv8);
                        destretchDelta = destretchDelta2;
                    } else {
                        consumedPixelsY = consumed2 / destretchMultiplier;
                        destretchDelta = destretchDelta2;
                    }
                }
            } else {
                f = 0.0f;
            }
            if (this.edgeEffectWrapper.isBottomStretched()) {
                int bits$iv$iv$iv9 = (int) (delta & 4294967295L);
                if (Float.intBitsToFloat(bits$iv$iv$iv9) > f) {
                    destretchDelta = destretchDelta2;
                    float consumed3 = m237pullBottomk4lQ0M(destretchDelta);
                    if (!this.edgeEffectWrapper.isBottomStretched()) {
                        this.edgeEffectWrapper.getOrCreateBottomEffect().finish();
                    }
                    int bits$iv$iv$iv10 = (int) (destretchDelta & 4294967295L);
                    if (consumed3 == Float.intBitsToFloat(bits$iv$iv$iv10)) {
                        int bits$iv$iv$iv11 = (int) (delta & 4294967295L);
                        consumedPixelsY = Float.intBitsToFloat(bits$iv$iv$iv11);
                    } else {
                        consumedPixelsY = consumed3 / destretchMultiplier;
                    }
                } else {
                    destretchDelta = destretchDelta2;
                    consumedPixelsY = f;
                }
            } else {
                destretchDelta = destretchDelta2;
                consumedPixelsY = f;
            }
        }
        int bits$iv$iv$iv12 = (int) (delta >> 32);
        if (Float.intBitsToFloat(bits$iv$iv$iv12) == f) {
            consumedPixelsY2 = consumedPixelsY;
            consumedPixelsX = f;
        } else if (this.edgeEffectWrapper.isLeftStretched()) {
            int bits$iv$iv$iv13 = (int) (delta >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv13) < f) {
                float consumed4 = m238pullLeftk4lQ0M(destretchDelta);
                if (!this.edgeEffectWrapper.isLeftStretched()) {
                    this.edgeEffectWrapper.getOrCreateLeftEffect().finish();
                }
                consumedPixelsY2 = consumedPixelsY;
                int bits$iv$iv$iv14 = (int) (destretchDelta >> 32);
                if (consumed4 == Float.intBitsToFloat(bits$iv$iv$iv14)) {
                    int bits$iv$iv$iv15 = (int) (delta >> 32);
                    consumedPixelsX = Float.intBitsToFloat(bits$iv$iv$iv15);
                } else {
                    consumedPixelsX = consumed4 / destretchMultiplier;
                }
            } else {
                consumedPixelsY2 = consumedPixelsY;
                if (this.edgeEffectWrapper.isRightStretched()) {
                    bits$iv$iv$iv = (int) (delta >> 32);
                    if (Float.intBitsToFloat(bits$iv$iv$iv) > f) {
                        consumed = m239pullRightk4lQ0M(destretchDelta);
                        if (!this.edgeEffectWrapper.isRightStretched()) {
                            this.edgeEffectWrapper.getOrCreateRightEffect().finish();
                        }
                        bits$iv$iv$iv2 = (int) (destretchDelta >> 32);
                        if (consumed == Float.intBitsToFloat(bits$iv$iv$iv2)) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            int bits$iv$iv$iv16 = (int) (delta >> 32);
                            consumedPixelsX = Float.intBitsToFloat(bits$iv$iv$iv16);
                        } else {
                            consumedPixelsX = consumed / destretchMultiplier;
                        }
                    } else {
                        consumedPixelsX = f;
                    }
                } else {
                    consumedPixelsX = f;
                }
            }
        } else {
            consumedPixelsY2 = consumedPixelsY;
            if (this.edgeEffectWrapper.isRightStretched()) {
                bits$iv$iv$iv = (int) (delta >> 32);
                if (Float.intBitsToFloat(bits$iv$iv$iv) > f) {
                    consumed = m239pullRightk4lQ0M(destretchDelta);
                    if (!this.edgeEffectWrapper.isRightStretched()) {
                        this.edgeEffectWrapper.getOrCreateRightEffect().finish();
                    }
                    bits$iv$iv$iv2 = (int) (destretchDelta >> 32);
                    if (consumed == Float.intBitsToFloat(bits$iv$iv$iv2)) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        int bits$iv$iv$iv17 = (int) (delta >> 32);
                        consumedPixelsX = Float.intBitsToFloat(bits$iv$iv$iv17);
                    } else {
                        consumedPixelsX = consumed / destretchMultiplier;
                    }
                } else {
                    consumedPixelsX = f;
                }
            } else {
                consumedPixelsX = f;
            }
        }
        float y$iv = consumedPixelsY2;
        float x$iv = consumedPixelsX;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long consumedOffset = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        if (!Offset.m4849equalsimpl0(consumedOffset, Offset.INSTANCE.m4868getZeroF1C5BW0())) {
            invalidateOverscroll$foundation();
        }
        long leftForDelta = Offset.m4856minusMKHz9U(delta, consumedOffset);
        long consumedByDelta = performScroll.invoke(Offset.m4841boximpl(leftForDelta)).m4862unboximpl();
        long leftForOverscroll = Offset.m4856minusMKHz9U(leftForDelta, consumedByDelta);
        int bits$iv$iv$iv18 = (int) (leftForDelta >> 32);
        if (Float.intBitsToFloat(bits$iv$iv$iv18) == f) {
            int bits$iv$iv$iv19 = (int) (leftForDelta & 4294967295L);
            if (!(Float.intBitsToFloat(bits$iv$iv$iv19) == f)) {
                bits$iv$iv$iv3 = (int) (consumedByDelta >> 32);
                if (Float.intBitsToFloat(bits$iv$iv$iv3) == f) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    bits$iv$iv$iv4 = (int) (consumedByDelta & 4294967295L);
                    if (Float.intBitsToFloat(bits$iv$iv$iv4) == f) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (!z3) {
                        $this$applyToScroll_Rhakbz0_u24lambda_u244 = this.edgeEffectWrapper;
                        if ($this$applyToScroll_Rhakbz0_u24lambda_u244.isLeftStretched() || $this$applyToScroll_Rhakbz0_u24lambda_u244.isTopStretched() || $this$applyToScroll_Rhakbz0_u24lambda_u244.isRightStretched() || $this$applyToScroll_Rhakbz0_u24lambda_u244.isBottomStretched()) {
                            animateToReleaseIfNeeded();
                        }
                    }
                } else {
                    $this$applyToScroll_Rhakbz0_u24lambda_u244 = this.edgeEffectWrapper;
                    if ($this$applyToScroll_Rhakbz0_u24lambda_u244.isLeftStretched()) {
                        animateToReleaseIfNeeded();
                    } else {
                        animateToReleaseIfNeeded();
                    }
                }
            }
        } else {
            bits$iv$iv$iv3 = (int) (consumedByDelta >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv3) == f) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                bits$iv$iv$iv4 = (int) (consumedByDelta & 4294967295L);
                if (Float.intBitsToFloat(bits$iv$iv$iv4) == f) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (!z3) {
                    $this$applyToScroll_Rhakbz0_u24lambda_u244 = this.edgeEffectWrapper;
                    if ($this$applyToScroll_Rhakbz0_u24lambda_u244.isLeftStretched()) {
                        animateToReleaseIfNeeded();
                    } else {
                        animateToReleaseIfNeeded();
                    }
                }
            } else {
                $this$applyToScroll_Rhakbz0_u24lambda_u244 = this.edgeEffectWrapper;
                if ($this$applyToScroll_Rhakbz0_u24lambda_u244.isLeftStretched()) {
                    animateToReleaseIfNeeded();
                } else {
                    animateToReleaseIfNeeded();
                }
            }
        }
        boolean appliedVerticalOverscroll2 = false;
        if (NestedScrollSource.m6278equalsimpl0(source, NestedScrollSource.INSTANCE.m6290getUserInputWNlRxjI())) {
            int bits$iv$iv$iv20 = (int) (leftForOverscroll >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv20) > 0.5f) {
                m238pullLeftk4lQ0M(leftForOverscroll);
                f2 = 0.5f;
                appliedHorizontalOverscroll = true;
            } else {
                f2 = 0.5f;
                int bits$iv$iv$iv21 = (int) (leftForOverscroll >> 32);
                if (Float.intBitsToFloat(bits$iv$iv$iv21) < -0.5f) {
                    m239pullRightk4lQ0M(leftForOverscroll);
                    appliedHorizontalOverscroll = true;
                } else {
                    appliedHorizontalOverscroll = false;
                }
            }
            boolean appliedHorizontalOverscroll2 = appliedHorizontalOverscroll;
            int bits$iv$iv$iv22 = (int) (leftForOverscroll & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv22) > f2) {
                m240pullTopk4lQ0M(leftForOverscroll);
                appliedVerticalOverscroll = true;
            } else {
                int bits$iv$iv$iv23 = (int) (leftForOverscroll & 4294967295L);
                if (Float.intBitsToFloat(bits$iv$iv$iv23) < -0.5f) {
                    m237pullBottomk4lQ0M(leftForOverscroll);
                    appliedVerticalOverscroll = true;
                } else {
                    appliedVerticalOverscroll = false;
                }
            }
            appliedVerticalOverscroll2 = appliedHorizontalOverscroll2 || appliedVerticalOverscroll;
        }
        if (!Offset.m4849equalsimpl0(leftForDelta, Offset.INSTANCE.m4868getZeroF1C5BW0())) {
            appliedVerticalOverscroll2 = m241releaseOppositeOverscrollk4lQ0M(delta) || appliedVerticalOverscroll2;
        }
        if (appliedVerticalOverscroll2) {
            invalidateOverscroll$foundation();
        }
        return Offset.m4857plusMKHz9U(consumedOffset, consumedByDelta);
    }

    /* JADX WARN: Code duplicated, block: B:52:0x0194  */
    /* JADX WARN: Code duplicated, block: B:53:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:55:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:58:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:59:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:61:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:7:0x0018  */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ, reason: not valid java name */
    public Object mo242applyToFlingBMRW4eQ(long j, Function2<? super Velocity, ? super Continuation<? super Velocity>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        AndroidEdgeEffectOverscrollEffect$applyToFling$1 androidEdgeEffectOverscrollEffect$applyToFling$1;
        float f;
        long j2;
        float consumedX;
        float consumedY;
        long remainingVelocity;
        Object objInvoke;
        AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect;
        long consumedByVelocity;
        if (continuation instanceof AndroidEdgeEffectOverscrollEffect$applyToFling$1) {
            androidEdgeEffectOverscrollEffect$applyToFling$1 = (AndroidEdgeEffectOverscrollEffect$applyToFling$1) continuation;
            if ((androidEdgeEffectOverscrollEffect$applyToFling$1.label & Integer.MIN_VALUE) != 0) {
                androidEdgeEffectOverscrollEffect$applyToFling$1.label -= Integer.MIN_VALUE;
            } else {
                androidEdgeEffectOverscrollEffect$applyToFling$1 = new AndroidEdgeEffectOverscrollEffect$applyToFling$1(this, continuation);
            }
        } else {
            androidEdgeEffectOverscrollEffect$applyToFling$1 = new AndroidEdgeEffectOverscrollEffect$applyToFling$1(this, continuation);
        }
        Object $result = androidEdgeEffectOverscrollEffect$applyToFling$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (androidEdgeEffectOverscrollEffect$applyToFling$1.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (Size.m4923isEmptyimpl(this.containerSize)) {
                    Velocity velocityM8134boximpl = Velocity.m8134boximpl(j);
                    androidEdgeEffectOverscrollEffect$applyToFling$1.label = 1;
                    if (function2.invoke(velocityM8134boximpl, androidEdgeEffectOverscrollEffect$applyToFling$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
                if (!this.edgeEffectWrapper.isLeftStretched() || Velocity.m8143getXimpl(j) >= 0.0f) {
                    f = 0.0f;
                    j2 = j;
                    if (this.edgeEffectWrapper.isRightStretched() && Velocity.m8143getXimpl(j2) > 0.0f) {
                        EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
                        EdgeEffect orCreateRightEffect = this.edgeEffectWrapper.getOrCreateRightEffect();
                        float f2 = -Velocity.m8143getXimpl(j2);
                        int bits$iv$iv$iv = (int) (this.containerSize >> 32);
                        consumedX = -edgeEffectCompat.absorbToRelaxIfNeeded(orCreateRightEffect, f2, Float.intBitsToFloat(bits$iv$iv$iv), this.density);
                    } else {
                        consumedX = 0.0f;
                    }
                } else {
                    EdgeEffectCompat edgeEffectCompat2 = EdgeEffectCompat.INSTANCE;
                    EdgeEffect orCreateLeftEffect = this.edgeEffectWrapper.getOrCreateLeftEffect();
                    float fM8143getXimpl = Velocity.m8143getXimpl(j);
                    f = 0.0f;
                    j2 = j;
                    int bits$iv$iv$iv2 = (int) (this.containerSize >> 32);
                    consumedX = edgeEffectCompat2.absorbToRelaxIfNeeded(orCreateLeftEffect, fM8143getXimpl, Float.intBitsToFloat(bits$iv$iv$iv2), this.density);
                }
                if (this.edgeEffectWrapper.isTopStretched() && Velocity.m8144getYimpl(j2) < f) {
                    EdgeEffectCompat edgeEffectCompat3 = EdgeEffectCompat.INSTANCE;
                    EdgeEffect orCreateTopEffect = this.edgeEffectWrapper.getOrCreateTopEffect();
                    float fM8144getYimpl = Velocity.m8144getYimpl(j2);
                    int bits$iv$iv$iv3 = (int) (4294967295L & this.containerSize);
                    consumedY = edgeEffectCompat3.absorbToRelaxIfNeeded(orCreateTopEffect, fM8144getYimpl, Float.intBitsToFloat(bits$iv$iv$iv3), this.density);
                } else if (this.edgeEffectWrapper.isBottomStretched() && Velocity.m8144getYimpl(j2) > f) {
                    EdgeEffectCompat edgeEffectCompat4 = EdgeEffectCompat.INSTANCE;
                    EdgeEffect orCreateBottomEffect = this.edgeEffectWrapper.getOrCreateBottomEffect();
                    float f3 = -Velocity.m8144getYimpl(j2);
                    int bits$iv$iv$iv4 = (int) (4294967295L & this.containerSize);
                    consumedY = -edgeEffectCompat4.absorbToRelaxIfNeeded(orCreateBottomEffect, f3, Float.intBitsToFloat(bits$iv$iv$iv4), this.density);
                } else {
                    consumedY = f;
                }
                long consumed = VelocityKt.Velocity(consumedX, consumedY);
                if (!Velocity.m8142equalsimpl0(consumed, Velocity.INSTANCE.m8154getZero9UxMQ8M())) {
                    invalidateOverscroll$foundation();
                }
                remainingVelocity = Velocity.m8146minusAH228Gc(j2, consumed);
                Velocity velocityM8134boximpl2 = Velocity.m8134boximpl(remainingVelocity);
                androidEdgeEffectOverscrollEffect$applyToFling$1.J$0 = remainingVelocity;
                androidEdgeEffectOverscrollEffect$applyToFling$1.label = 2;
                objInvoke = function2.invoke(velocityM8134boximpl2, androidEdgeEffectOverscrollEffect$applyToFling$1);
                if (objInvoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                androidEdgeEffectOverscrollEffect = this;
                consumedByVelocity = Velocity.m8146minusAH228Gc(remainingVelocity, ((Velocity) objInvoke).getPackedValue());
                androidEdgeEffectOverscrollEffect.scrollCycleInProgress = false;
                if (Velocity.m8143getXimpl(consumedByVelocity) > f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.edgeEffectWrapper.getOrCreateLeftEffect(), MathKt.roundToInt(Velocity.m8143getXimpl(consumedByVelocity)));
                } else if (Velocity.m8143getXimpl(consumedByVelocity) < f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.edgeEffectWrapper.getOrCreateRightEffect(), -MathKt.roundToInt(Velocity.m8143getXimpl(consumedByVelocity)));
                }
                if (Velocity.m8144getYimpl(consumedByVelocity) > f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.edgeEffectWrapper.getOrCreateTopEffect(), MathKt.roundToInt(Velocity.m8144getYimpl(consumedByVelocity)));
                } else if (Velocity.m8144getYimpl(consumedByVelocity) < f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.edgeEffectWrapper.getOrCreateBottomEffect(), -MathKt.roundToInt(Velocity.m8144getYimpl(consumedByVelocity)));
                }
                androidEdgeEffectOverscrollEffect.animateToReleaseIfNeeded();
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 2:
                androidEdgeEffectOverscrollEffect = this;
                remainingVelocity = androidEdgeEffectOverscrollEffect$applyToFling$1.J$0;
                ResultKt.throwOnFailure($result);
                objInvoke = $result;
                f = 0.0f;
                consumedByVelocity = Velocity.m8146minusAH228Gc(remainingVelocity, ((Velocity) objInvoke).getPackedValue());
                androidEdgeEffectOverscrollEffect.scrollCycleInProgress = false;
                if (Velocity.m8143getXimpl(consumedByVelocity) > f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.edgeEffectWrapper.getOrCreateLeftEffect(), MathKt.roundToInt(Velocity.m8143getXimpl(consumedByVelocity)));
                } else if (Velocity.m8143getXimpl(consumedByVelocity) < f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.edgeEffectWrapper.getOrCreateRightEffect(), -MathKt.roundToInt(Velocity.m8143getXimpl(consumedByVelocity)));
                }
                if (Velocity.m8144getYimpl(consumedByVelocity) > f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.edgeEffectWrapper.getOrCreateTopEffect(), MathKt.roundToInt(Velocity.m8144getYimpl(consumedByVelocity)));
                } else if (Velocity.m8144getYimpl(consumedByVelocity) < f) {
                    EdgeEffectCompat.INSTANCE.onAbsorbCompat(androidEdgeEffectOverscrollEffect.edgeEffectWrapper.getOrCreateBottomEffect(), -MathKt.roundToInt(Velocity.m8144getYimpl(consumedByVelocity)));
                }
                androidEdgeEffectOverscrollEffect.animateToReleaseIfNeeded();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0023  */
    /* JADX WARN: Code duplicated, block: B:13:0x002e  */
    /* JADX WARN: Code duplicated, block: B:14:0x0030  */
    /* JADX WARN: Code duplicated, block: B:16:0x0034  */
    /* JADX WARN: Code duplicated, block: B:18:0x003a  */
    /* JADX WARN: Code duplicated, block: B:20:0x0045  */
    /* JADX WARN: Code duplicated, block: B:21:0x0047  */
    /* JADX WARN: Code duplicated, block: B:23:0x004b  */
    /* JADX WARN: Code duplicated, block: B:25:0x0051  */
    /* JADX WARN: Code duplicated, block: B:27:0x005c  */
    /* JADX WARN: Code duplicated, block: B:28:0x005e  */
    /* JADX WARN: Code duplicated, block: B:9:0x001d  */
    @Override // androidx.compose.foundation.OverscrollEffect
    public boolean isInProgress() {
        EdgeEffect it;
        EdgeEffect it2;
        EdgeEffect it3;
        boolean z;
        boolean z2;
        boolean z3;
        EdgeEffectWrapper this_$iv = this.edgeEffectWrapper;
        EdgeEffect it4 = this_$iv.topEffect;
        if (it4 == null) {
            it = this_$iv.bottomEffect;
            if (it == null) {
                it2 = this_$iv.leftEffect;
                if (it2 == null) {
                    it3 = this_$iv.rightEffect;
                    if (it3 != null) {
                        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it3) == 0.0f) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                        }
                    }
                    return false;
                }
                if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it2) == 0.0f) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    it3 = this_$iv.rightEffect;
                    if (it3 != null) {
                        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it3) == 0.0f) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                        }
                    }
                    return false;
                }
            } else {
                if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it) == 0.0f) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    it2 = this_$iv.leftEffect;
                    if (it2 == null) {
                        it3 = this_$iv.rightEffect;
                        if (it3 != null) {
                            if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it3) == 0.0f) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (!z) {
                            }
                        }
                        return false;
                    }
                    if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it2) == 0.0f) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        it3 = this_$iv.rightEffect;
                        if (it3 != null) {
                            if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it3) == 0.0f) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (!z) {
                            }
                        }
                        return false;
                    }
                }
            }
        } else {
            if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it4) == 0.0f) {
                it = this_$iv.bottomEffect;
                if (it == null) {
                    it2 = this_$iv.leftEffect;
                    if (it2 == null) {
                        it3 = this_$iv.rightEffect;
                        if (it3 != null) {
                            if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it3) == 0.0f) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (!z) {
                            }
                        }
                        return false;
                    }
                    if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it2) == 0.0f) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        it3 = this_$iv.rightEffect;
                        if (it3 != null) {
                            if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it3) == 0.0f) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (!z) {
                            }
                        }
                        return false;
                    }
                } else {
                    if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it) == 0.0f) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        it2 = this_$iv.leftEffect;
                        if (it2 == null) {
                            it3 = this_$iv.rightEffect;
                            if (it3 != null) {
                                if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it3) == 0.0f) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (!z) {
                                }
                            }
                            return false;
                        }
                        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it2) == 0.0f) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            it3 = this_$iv.rightEffect;
                            if (it3 != null) {
                                if (EdgeEffectCompat.INSTANCE.getDistanceCompat(it3) == 0.0f) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (!z) {
                                }
                            }
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: updateSize-uvyYCjk$foundation, reason: not valid java name */
    public final void m245updateSizeuvyYCjk$foundation(long size) {
        boolean initialSetSize = Size.m4917equalsimpl0(this.containerSize, Size.INSTANCE.m4930getZeroNHjbRc());
        boolean differentSize = !Size.m4917equalsimpl0(size, this.containerSize);
        this.containerSize = size;
        if (differentSize) {
            EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
            int bits$iv$iv$iv = (int) (size >> 32);
            int width$iv = MathKt.roundToInt(Float.intBitsToFloat(bits$iv$iv$iv));
            int bits$iv$iv$iv2 = (int) (size & 4294967295L);
            int height$iv = MathKt.roundToInt(Float.intBitsToFloat(bits$iv$iv$iv2));
            edgeEffectWrapper.m319updateSizeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
        }
        if (initialSetSize || !differentSize) {
            return;
        }
        animateToReleaseIfNeeded();
    }

    /* JADX INFO: renamed from: displacement-F1C5BW0$foundation, reason: not valid java name */
    public final long m244displacementF1C5BW0$foundation() {
        long pointer = ((9223372034707292159L & this.pointerPosition) > InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : ((9223372034707292159L & this.pointerPosition) == InlineClassHelperKt.UnspecifiedPackedFloats ? 0 : -1)) != 0 ? this.pointerPosition : SizeKt.m4931getCenteruvyYCjk(this.containerSize);
        int bits$iv$iv$iv = (int) (pointer >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (this.containerSize >> 32);
        float x = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) (pointer & 4294967295L);
        float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv3);
        int bits$iv$iv$iv4 = (int) (this.containerSize & 4294967295L);
        float y = fIntBitsToFloat2 / Float.intBitsToFloat(bits$iv$iv$iv4);
        long v1$iv$iv = Float.floatToRawIntBits(x);
        long v2$iv$iv = Float.floatToRawIntBits(y);
        return Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public DelegatableNode getNode() {
        return this.node;
    }

    public final void invalidateOverscroll$foundation() {
        if (this.invalidationEnabled) {
            this.redrawSignal.setValue(Unit.INSTANCE);
        }
    }

    private final void animateToReleaseIfNeeded() {
        boolean needsInvalidation = false;
        EdgeEffectWrapper this_$iv = this.edgeEffectWrapper;
        EdgeEffect it = this_$iv.topEffect;
        boolean z = true;
        if (it != null) {
            it.onRelease();
            needsInvalidation = !it.isFinished();
        }
        EdgeEffect it2 = this_$iv.bottomEffect;
        if (it2 != null) {
            it2.onRelease();
            needsInvalidation = !it2.isFinished() || needsInvalidation;
        }
        EdgeEffect it3 = this_$iv.leftEffect;
        if (it3 != null) {
            it3.onRelease();
            needsInvalidation = !it3.isFinished() || needsInvalidation;
        }
        EdgeEffect it4 = this_$iv.rightEffect;
        if (it4 != null) {
            it4.onRelease();
            if (it4.isFinished() && !needsInvalidation) {
                z = false;
            }
            needsInvalidation = z;
        }
        if (needsInvalidation) {
            invalidateOverscroll$foundation();
        }
    }

    /* JADX INFO: renamed from: releaseOppositeOverscroll-k-4lQ0M, reason: not valid java name */
    private final boolean m241releaseOppositeOverscrollk4lQ0M(long delta) {
        boolean needsInvalidation = false;
        if (this.edgeEffectWrapper.isLeftAnimating()) {
            int bits$iv$iv$iv = (int) (delta >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv) < 0.0f) {
                int bits$iv$iv$iv2 = (int) (delta >> 32);
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateLeftEffect(), Float.intBitsToFloat(bits$iv$iv$iv2));
                needsInvalidation = this.edgeEffectWrapper.isLeftAnimating();
            }
        }
        if (this.edgeEffectWrapper.isRightAnimating()) {
            int bits$iv$iv$iv3 = (int) (delta >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv3) > 0.0f) {
                int bits$iv$iv$iv4 = (int) (delta >> 32);
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateRightEffect(), Float.intBitsToFloat(bits$iv$iv$iv4));
                needsInvalidation = needsInvalidation || this.edgeEffectWrapper.isRightAnimating();
            }
        }
        if (this.edgeEffectWrapper.isTopAnimating()) {
            int bits$iv$iv$iv5 = (int) (delta & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv5) < 0.0f) {
                int bits$iv$iv$iv6 = (int) (delta & 4294967295L);
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateTopEffect(), Float.intBitsToFloat(bits$iv$iv$iv6));
                needsInvalidation = needsInvalidation || this.edgeEffectWrapper.isTopAnimating();
            }
        }
        if (!this.edgeEffectWrapper.isBottomAnimating()) {
            return needsInvalidation;
        }
        int bits$iv$iv$iv7 = (int) (delta & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv7) <= 0.0f) {
            return needsInvalidation;
        }
        int bits$iv$iv$iv8 = (int) (4294967295L & delta);
        EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateBottomEffect(), Float.intBitsToFloat(bits$iv$iv$iv8));
        return needsInvalidation || this.edgeEffectWrapper.isBottomAnimating();
    }

    /* JADX INFO: renamed from: pullTop-k-4lQ0M, reason: not valid java name */
    private final float m240pullTopk4lQ0M(long scroll) {
        int bits$iv$iv$iv = (int) (m244displacementF1C5BW0$foundation() >> 32);
        float displacementX = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (scroll & 4294967295L);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) (this.containerSize & 4294967295L);
        float pullY = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv3);
        EdgeEffect topEffect = this.edgeEffectWrapper.getOrCreateTopEffect();
        float fOnPullDistanceCompat = EdgeEffectCompat.INSTANCE.onPullDistanceCompat(topEffect, pullY, displacementX);
        int bits$iv$iv$iv4 = (int) (this.containerSize & 4294967295L);
        float consumed = fOnPullDistanceCompat * Float.intBitsToFloat(bits$iv$iv$iv4);
        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(topEffect) == 0.0f) {
            return consumed;
        }
        int bits$iv$iv$iv5 = (int) (4294967295L & scroll);
        return Float.intBitsToFloat(bits$iv$iv$iv5);
    }

    /* JADX INFO: renamed from: pullBottom-k-4lQ0M, reason: not valid java name */
    private final float m237pullBottomk4lQ0M(long scroll) {
        int bits$iv$iv$iv = (int) (m244displacementF1C5BW0$foundation() >> 32);
        float displacementX = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (scroll & 4294967295L);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) (this.containerSize & 4294967295L);
        float pullY = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv3);
        EdgeEffect bottomEffect = this.edgeEffectWrapper.getOrCreateBottomEffect();
        float f = -EdgeEffectCompat.INSTANCE.onPullDistanceCompat(bottomEffect, -pullY, 1.0f - displacementX);
        int bits$iv$iv$iv4 = (int) (this.containerSize & 4294967295L);
        float consumed = f * Float.intBitsToFloat(bits$iv$iv$iv4);
        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(bottomEffect) == 0.0f) {
            return consumed;
        }
        int bits$iv$iv$iv5 = (int) (4294967295L & scroll);
        return Float.intBitsToFloat(bits$iv$iv$iv5);
    }

    /* JADX INFO: renamed from: pullLeft-k-4lQ0M, reason: not valid java name */
    private final float m238pullLeftk4lQ0M(long scroll) {
        int bits$iv$iv$iv = (int) (4294967295L & m244displacementF1C5BW0$foundation());
        float displacementY = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (scroll >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) (this.containerSize >> 32);
        float pullX = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv3);
        EdgeEffect leftEffect = this.edgeEffectWrapper.getOrCreateLeftEffect();
        float fOnPullDistanceCompat = EdgeEffectCompat.INSTANCE.onPullDistanceCompat(leftEffect, pullX, 1.0f - displacementY);
        int bits$iv$iv$iv4 = (int) (this.containerSize >> 32);
        float consumed = fOnPullDistanceCompat * Float.intBitsToFloat(bits$iv$iv$iv4);
        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(leftEffect) == 0.0f) {
            return consumed;
        }
        int bits$iv$iv$iv5 = (int) (scroll >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv5);
    }

    /* JADX INFO: renamed from: pullRight-k-4lQ0M, reason: not valid java name */
    private final float m239pullRightk4lQ0M(long scroll) {
        int bits$iv$iv$iv = (int) (4294967295L & m244displacementF1C5BW0$foundation());
        float displacementY = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (scroll >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) (this.containerSize >> 32);
        float pullX = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv3);
        EdgeEffect rightEffect = this.edgeEffectWrapper.getOrCreateRightEffect();
        float f = -EdgeEffectCompat.INSTANCE.onPullDistanceCompat(rightEffect, -pullX, displacementY);
        int bits$iv$iv$iv4 = (int) (this.containerSize >> 32);
        float consumed = f * Float.intBitsToFloat(bits$iv$iv$iv4);
        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(rightEffect) == 0.0f) {
            return consumed;
        }
        int bits$iv$iv$iv5 = (int) (scroll >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv5);
    }
}
