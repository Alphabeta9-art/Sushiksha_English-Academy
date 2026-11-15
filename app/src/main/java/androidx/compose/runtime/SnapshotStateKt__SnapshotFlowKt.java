package androidx.compose.runtime;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSet;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.exifinterface.media.ExifInterface;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: SnapshotFlow.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a?\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0001\"\b\b\u0000\u0010\u0002*\u0002H\u0007\"\u0004\b\u0001\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00020\b2\u0006\u0010\t\u001a\u0002H\u00072\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\n\u001a \u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\r\u001a%\u0010\u000e\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002¢\u0006\u0002\b\u0014¨\u0006\u0015"}, d2 = {"collectAsState", "Landroidx/compose/runtime/State;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/StateFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/StateFlow;Lkotlin/coroutines/CoroutineContext;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "R", "Lkotlinx/coroutines/flow/Flow;", "initial", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "snapshotFlow", "block", "Lkotlin/Function0;", "intersects", "", "Landroidx/collection/MutableScatterSet;", "", "set", "", "intersects$SnapshotStateKt__SnapshotFlowKt", "runtime"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotStateKt")
final /* synthetic */ class SnapshotStateKt__SnapshotFlowKt {
    public static final <T> State<T> collectAsState(StateFlow<? extends T> stateFlow, CoroutineContext context, Composer $composer, int $changed, int i) {
        CoroutineContext context2;
        ComposerKt.sourceInformationMarkerStart($composer, -1439883919, "C(collectAsState)N(context)49@1914L30:SnapshotFlow.kt#9igjgp");
        if ((i & 1) != 0) {
            CoroutineContext context3 = EmptyCoroutineContext.INSTANCE;
            context2 = context3;
        } else {
            context2 = context;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1439883919, $changed, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:49)");
        }
        State<T> stateCollectAsState = SnapshotStateKt.collectAsState(stateFlow, stateFlow.getValue(), context2, $composer, ($changed & 14) | (($changed << 3) & 896), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return stateCollectAsState;
    }

    public static final <T extends R, R> State<R> collectAsState(Flow<? extends T> flow, R r, CoroutineContext context, Composer $composer, int $changed, int i) {
        CoroutineContext context2;
        ComposerKt.sourceInformationMarkerStart($composer, -606625098, "C(collectAsState)N(initial,context)65@2592L153,65@2555L190:SnapshotFlow.kt#9igjgp");
        if ((i & 2) != 0) {
            CoroutineContext context3 = EmptyCoroutineContext.INSTANCE;
            context2 = context3;
        } else {
            context2 = context;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-606625098, $changed, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:65)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1148838511, "CC(remember):SnapshotFlow.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(context2) | $composer.changedInstance(flow);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = (Function2) new SnapshotStateKt__SnapshotFlowKt$collectAsState$1$1(context2, flow, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        State<R> stateProduceState = SnapshotStateKt.produceState(r, flow, context2, (Function2) it$iv, $composer, (($changed >> 3) & 8) | (($changed >> 3) & 14) | (($changed << 3) & 112) | ($changed & 896));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return stateProduceState;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotFlow.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1", f = "SnapshotFlow.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {143, 147, 170}, m = "invokeSuspend", n = {"$this$flow", "readSet", "readObserver", "appliedChanges", "unregisterApplyObserver", "lastValue", "$this$flow", "readSet", "readObserver", "appliedChanges", "unregisterApplyObserver", "lastValue", "found", "$this$flow", "readSet", "readObserver", "appliedChanges", "unregisterApplyObserver", "lastValue"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"}, v = 1)
    static final class AnonymousClass1<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<T> $block;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function0<? extends T> function0, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$block = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$block, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:32:0x00fb A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:33:0x00fc  */
        /* JADX WARN: Code duplicated, block: B:37:0x010d A[Catch: all -> 0x0194, TryCatch #2 {all -> 0x0194, blocks: (B:34:0x0108, B:37:0x010d, B:41:0x0116, B:44:0x0125, B:51:0x0147, B:53:0x0154, B:63:0x0182, B:64:0x0185, B:46:0x0135, B:50:0x0142, B:60:0x017d, B:61:0x0180, B:48:0x013c), top: B:84:0x0108, inners: #1 }] */
        /* JADX WARN: Code duplicated, block: B:40:0x0115  */
        /* JADX WARN: Code duplicated, block: B:44:0x0125 A[Catch: all -> 0x0194, TRY_LEAVE, TryCatch #2 {all -> 0x0194, blocks: (B:34:0x0108, B:37:0x010d, B:41:0x0116, B:44:0x0125, B:51:0x0147, B:53:0x0154, B:63:0x0182, B:64:0x0185, B:46:0x0135, B:50:0x0142, B:60:0x017d, B:61:0x0180, B:48:0x013c), top: B:84:0x0108, inners: #1 }] */
        /* JADX WARN: Code duplicated, block: B:53:0x0154 A[Catch: all -> 0x0194, TRY_LEAVE, TryCatch #2 {all -> 0x0194, blocks: (B:34:0x0108, B:37:0x010d, B:41:0x0116, B:44:0x0125, B:51:0x0147, B:53:0x0154, B:63:0x0182, B:64:0x0185, B:46:0x0135, B:50:0x0142, B:60:0x017d, B:61:0x0180, B:48:0x013c), top: B:84:0x0108, inners: #1 }] */
        /* JADX WARN: Code duplicated, block: B:55:0x016d A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:56:0x016e  */
        /* JADX WARN: Code duplicated, block: B:65:0x0186  */
        /* JADX WARN: Code duplicated, block: B:66:0x0191 A[LOOP:0: B:36:0x010b->B:66:0x0191, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:97:0x0123 A[SYNTHETIC] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
        /* JADX WARN: Type inference failed for: r10v10 */
        /* JADX WARN: Type inference failed for: r10v11, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v13 */
        /* JADX WARN: Type inference failed for: r10v16 */
        /* JADX WARN: Type inference failed for: r10v17 */
        /* JADX WARN: Type inference failed for: r10v18 */
        /* JADX WARN: Type inference failed for: r10v2 */
        /* JADX WARN: Type inference failed for: r10v8 */
        /* JADX WARN: Type inference failed for: r10v9 */
        /* JADX WARN: Type inference failed for: r11v11, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
        /* JADX WARN: Type inference failed for: r11v17 */
        /* JADX WARN: Type inference failed for: r11v20 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x016e -> B:57:0x0177). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:36:0x010b
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 440
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        static final Unit invokeSuspend$lambda$0(MutableScatterSet $readSet, Object it) {
            if (it instanceof StateObjectImpl) {
                ReaderKind.Companion companion = ReaderKind.INSTANCE;
                ((StateObjectImpl) it).m4559recordReadInh_f27i8$runtime(ReaderKind.m4546constructorimpl(4));
            }
            $readSet.add(it);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:49:0x0107  */
        /* JADX WARN: Code duplicated, block: B:58:0x008c A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:63:0x0097 A[SYNTHETIC] */
        static final Unit invokeSuspend$lambda$1(Channel $appliedChanges, Set changed, Snapshot snapshot) {
            boolean maybeObserved;
            boolean z;
            Set $this$fastAny$iv;
            int $i$f$fastAny;
            int $i$f$fastAny2;
            boolean z2;
            Set $this$fastAny$iv2 = changed;
            int $i$f$fastAny3 = 0;
            if ($this$fastAny$iv2 instanceof ScatterSetWrapper) {
                ScatterSet<T> set$runtime = ((ScatterSetWrapper) $this$fastAny$iv2).getSet$runtime();
                int $i$f$any = 0;
                Object[] elements$iv$iv$iv = set$runtime.elements;
                long[] m$iv$iv$iv$iv = set$runtime.metadata;
                int lastIndex$iv$iv$iv$iv = m$iv$iv$iv$iv.length - 2;
                int i$iv$iv$iv$iv = 0;
                if (0 <= lastIndex$iv$iv$iv$iv) {
                    loop0: while (true) {
                        long slot$iv$iv$iv$iv = m$iv$iv$iv$iv[i$iv$iv$iv$iv];
                        int $i$f$any2 = $i$f$any;
                        if ((((~slot$iv$iv$iv$iv) << 7) & slot$iv$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i = 8;
                            int bitCount$iv$iv$iv$iv = 8 - ((~(i$iv$iv$iv$iv - lastIndex$iv$iv$iv$iv)) >>> 31);
                            int j$iv$iv$iv$iv = 0;
                            while (j$iv$iv$iv$iv < bitCount$iv$iv$iv$iv) {
                                long value$iv$iv$iv$iv$iv = slot$iv$iv$iv$iv & 255;
                                int $i$f$isFull = value$iv$iv$iv$iv$iv < 128 ? 1 : 0;
                                if ($i$f$isFull != 0) {
                                    int index$iv$iv$iv$iv = (i$iv$iv$iv$iv << 3) + j$iv$iv$iv$iv;
                                    Object element$iv$iv = elements$iv$iv$iv[index$iv$iv$iv$iv];
                                    if (element$iv$iv instanceof StateObjectImpl) {
                                        ReaderKind.Companion companion = ReaderKind.INSTANCE;
                                        $i$f$fastAny2 = $i$f$fastAny3;
                                        int $i$f$fastAny4 = ReaderKind.m4546constructorimpl(4);
                                        z2 = ((StateObjectImpl) element$iv$iv).m4558isReadInh_f27i8$runtime($i$f$fastAny4);
                                        if (z2) {
                                            maybeObserved = true;
                                            break loop0;
                                        }
                                    } else {
                                        $i$f$fastAny2 = $i$f$fastAny3;
                                    }
                                    if (z2) {
                                        maybeObserved = true;
                                        break loop0;
                                    }
                                } else {
                                    $i$f$fastAny2 = $i$f$fastAny3;
                                }
                                slot$iv$iv$iv$iv >>= i;
                                j$iv$iv$iv$iv++;
                                $this$fastAny$iv2 = $this$fastAny$iv2;
                                i = i;
                                $i$f$fastAny3 = $i$f$fastAny2;
                            }
                            $this$fastAny$iv = $this$fastAny$iv2;
                            $i$f$fastAny = $i$f$fastAny3;
                            if (bitCount$iv$iv$iv$iv == i) {
                            }
                        } else {
                            $this$fastAny$iv = $this$fastAny$iv2;
                            $i$f$fastAny = $i$f$fastAny3;
                        }
                        if (i$iv$iv$iv$iv != lastIndex$iv$iv$iv$iv) {
                            i$iv$iv$iv$iv++;
                            $i$f$any = $i$f$any2;
                            $this$fastAny$iv2 = $this$fastAny$iv;
                            $i$f$fastAny3 = $i$f$fastAny;
                        }
                    }
                }
                maybeObserved = false;
                break;
            } else {
                Set $this$any$iv$iv = $this$fastAny$iv2;
                if (!($this$any$iv$iv instanceof Collection) || !$this$any$iv$iv.isEmpty()) {
                    Iterator<T> it = $this$any$iv$iv.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            maybeObserved = false;
                            break;
                        }
                        Object element$iv$iv2 = it.next();
                        if (element$iv$iv2 instanceof StateObjectImpl) {
                            ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                            if (((StateObjectImpl) element$iv$iv2).m4558isReadInh_f27i8$runtime(ReaderKind.m4546constructorimpl(4))) {
                                z = true;
                            } else {
                                z = false;
                            }
                        } else {
                            z = true;
                        }
                        if (z) {
                            maybeObserved = true;
                            break;
                        }
                    }
                } else {
                    maybeObserved = false;
                }
            }
            if (maybeObserved) {
                $appliedChanges.mo10578trySendJP2dKIU(changed);
            }
            return Unit.INSTANCE;
        }
    }

    public static final <T> Flow<T> snapshotFlow(Function0<? extends T> function0) {
        return FlowKt.flow(new AnonymousClass1(function0, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean intersects$SnapshotStateKt__SnapshotFlowKt(MutableScatterSet<Object> mutableScatterSet, Set<? extends Object> set) {
        ScatterSet this_$iv;
        MutableScatterSet<Object> this_$iv2 = mutableScatterSet;
        Object[] elements$iv$iv = this_$iv2.elements;
        long[] m$iv$iv$iv = this_$iv2.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 <= lastIndex$iv$iv$iv) {
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                long slot$iv$iv$iv2 = slot$iv$iv$iv;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i = 8;
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv = 0;
                    while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv2 & 255;
                        if (value$iv$iv$iv$iv < 128) {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                            Object element$iv = elements$iv$iv[index$iv$iv$iv];
                            if (set.contains(element$iv)) {
                                return true;
                            }
                        }
                        slot$iv$iv$iv2 >>= i;
                        j$iv$iv$iv++;
                        this_$iv2 = this_$iv2;
                        i = i;
                    }
                    this_$iv = this_$iv2;
                    if (bitCount$iv$iv$iv == i) {
                    }
                } else {
                    this_$iv = this_$iv2;
                }
                if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                    i$iv$iv$iv++;
                    this_$iv2 = this_$iv;
                }
            }
        }
        return false;
    }
}
