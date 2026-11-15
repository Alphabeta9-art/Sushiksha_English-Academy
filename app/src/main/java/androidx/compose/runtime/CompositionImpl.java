package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.tooling.CompositionErrorContext;
import androidx.compose.runtime.tooling.CompositionObserver;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.ObservableComposition;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.common.util.UriUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000®\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B'\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ \u0010g\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010aJ \u0010i\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010aJ \u0010j\u001a\u00020k2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010lJ \u0010m\u001a\u00020k2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010lJ\u001d\u0010n\u001a\u00020\\2\u000e\u0010o\u001a\n\u0012\u0004\u0012\u00020q\u0018\u00010pH\u0000¢\u0006\u0002\brJ \u0010s\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0002¢\u0006\u0002\u0010aJ(\u0010t\u001a\u00020k2\u0006\u0010u\u001a\u00020<2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0002¢\u0006\u0002\u0010vJ \u0010w\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0002¢\u0006\u0002\u0010aJ\b\u0010x\u001a\u00020\\H\u0002J\b\u0010y\u001a\u00020<H\u0002J\u0010\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020}H\u0016J\u000e\u0010~\u001a\u00020\\2\u0006\u0010\u007f\u001a\u00020HJ\t\u0010\u0080\u0001\u001a\u00020\\H\u0002J\t\u0010\u0081\u0001\u001a\u00020\\H\u0002J\t\u0010\u0082\u0001\u001a\u00020\\H\u0002J!\u0010\u0083\u0001\u001a\u00020\\2\u0011\u0010h\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]H\u0016¢\u0006\u0002\u0010aJ\u000f\u0010\u0084\u0001\u001a\u00020\\H\u0000¢\u0006\u0003\b\u0085\u0001J\t\u0010\u0086\u0001\u001a\u00020\\H\u0016J\u0018\u0010\u0089\u0001\u001a\u00020\\2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130(H\u0016J\u0018\u0010\u008b\u0001\u001a\u00020<2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130(H\u0016J\u0018\u0010\u008c\u0001\u001a\u00020\\2\r\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\\0[H\u0016J:\u0010\u008e\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130\u008f\u0001032\u0015\u0010\u0090\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u0092\u0001\u0012\u0004\u0012\u00020<0\u0091\u0001H\u0080\b¢\u0006\u0003\b\u0093\u0001J\u001b\u0010\u0094\u0001\u001a\u00020\\2\u0007\u0010\u0095\u0001\u001a\u00020\u00132\u0007\u0010\u0096\u0001\u001a\u00020<H\u0002J!\u0010\u0094\u0001\u001a\u00020\\2\r\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00130(2\u0007\u0010\u0096\u0001\u001a\u00020<H\u0002J\t\u0010\u0097\u0001\u001a\u00020\\H\u0002J\u0012\u0010\u0098\u0001\u001a\u00020\\2\u0007\u0010\u0095\u0001\u001a\u00020\u0013H\u0016J\u0012\u0010\u0099\u0001\u001a\u00020\\2\u0007\u0010\u0095\u0001\u001a\u00020\u0013H\u0002J\u0012\u0010\u009a\u0001\u001a\u00020\\2\u0007\u0010\u0095\u0001\u001a\u00020\u0013H\u0016J\t\u0010\u009b\u0001\u001a\u00020<H\u0016J)\u0010\u009c\u0001\u001a\u00020\\2\u001e\u0010\u009d\u0001\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030\u009e\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u009e\u00010\u008f\u000103H\u0016J\u0012\u0010\u009f\u0001\u001a\u00020\\2\u0007\u0010W\u001a\u00030 \u0001H\u0016J\u0011\u0010¡\u0001\u001a\u00020\\2\u0006\u00106\u001a\u000207H\u0002J\t\u0010¢\u0001\u001a\u00020\\H\u0016J\t\u0010£\u0001\u001a\u00020\\H\u0016J\t\u0010¤\u0001\u001a\u00020\\H\u0016JL\u0010¥\u0001\u001a\u0003H¦\u0001\"\u0005\b\u0000\u0010¦\u000122\u0010\u008d\u0001\u001a-\u0012!\u0012\u001f\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130$¢\u0006\u000e\b§\u0001\u0012\t\b¨\u0001\u0012\u0004\b\b(6\u0012\u0005\u0012\u0003H¦\u00010\u0091\u0001H\u0082\b¢\u0006\u0003\u0010©\u0001J(\u0010ª\u0001\u001a\u0003H¦\u0001\"\u0005\b\u0000\u0010¦\u00012\u000e\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u0003H¦\u00010[H\u0082\b¢\u0006\u0003\u0010«\u0001J\t\u0010¬\u0001\u001a\u00020\\H\u0016J\t\u0010\u00ad\u0001\u001a\u00020\\H\u0016J\t\u0010®\u0001\u001a\u00020\\H\u0016J;\u0010¯\u0001\u001a\u0003H°\u0001\"\u0005\b\u0000\u0010°\u00012\t\u0010±\u0001\u001a\u0004\u0018\u00010\u00012\u0007\u0010²\u0001\u001a\u00020H2\u000e\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u0003H°\u00010[H\u0016¢\u0006\u0003\u0010³\u0001J\u0015\u0010´\u0001\u001a\u0004\u0018\u00010C2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\u001e\u0010µ\u0001\u001a\u00030¶\u00012\u0007\u0010·\u0001\u001a\u00020%2\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010¹\u0001\u001a\u00020\\2\u0007\u0010·\u0001\u001a\u00020%H\u0016J)\u0010º\u0001\u001a\u0005\u0018\u0001H¦\u0001\"\u0005\b\u0000\u0010¦\u00012\u000e\u0010\u007f\u001a\n\u0012\u0005\u0012\u0003H¦\u00010»\u0001H\u0016¢\u0006\u0003\u0010¼\u0001J\u001d\u0010½\u0001\u001a\u00020<2\u0007\u0010·\u0001\u001a\u00020%2\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u0013H\u0002J(\u0010¾\u0001\u001a\u00030¶\u00012\u0007\u0010·\u0001\u001a\u00020%2\b\u0010¿\u0001\u001a\u00030\u0092\u00012\t\u0010¸\u0001\u001a\u0004\u0018\u00010\u0013H\u0002J!\u0010À\u0001\u001a\u00020\\2\u0007\u0010¸\u0001\u001a\u00020\u00132\u0007\u0010·\u0001\u001a\u00020%H\u0000¢\u0006\u0003\bÁ\u0001J\u001b\u0010Â\u0001\u001a\u00020\\2\n\u0010W\u001a\u0006\u0012\u0002\b\u00030/H\u0000¢\u0006\u0003\bÃ\u0001J\u001e\u0010Ä\u0001\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130$H\u0002¢\u0006\u0006\bÅ\u0001\u0010Æ\u0001J\u0011\u0010Ç\u0001\u001a\u00020\\2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J(\u0010È\u0001\u001a\u0003H¦\u0001\"\u0005\b\u0000\u0010¦\u00012\u000e\u0010\u008d\u0001\u001a\t\u0012\u0005\u0012\u0003H¦\u00010[H\u0082\b¢\u0006\u0003\u0010«\u0001J\n\u0010|\u001a\u0004\u0018\u00010}H\u0002J\t\u0010É\u0001\u001a\u00020\\H\u0016J\u000f\u0010Ê\u0001\u001a\u00020HH\u0000¢\u0006\u0003\bË\u0001R\u0013\u0010\u0007\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0013`\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0014\u0010\u0016\u001a\u00060\u0013j\u0002`\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u001d\u001a\u0004\b!\u0010\"R\u001c\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00130(8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020%0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020%0,X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010.\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\b\u0012\u0006\u0012\u0002\b\u00030/0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020\u00130(8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b1\u0010*R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020%038AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020%0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u001c\u0010:\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00130$X\u0082\u000e¢\u0006\u0004\n\u0002\u0010&R \u0010;\u001a\u00020<X\u0080\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b=\u0010\u001d\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\u00020JX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u000e\u0010M\u001a\u00020NX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010O\u001a\u00020PX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR\u0010\u0010S\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0011\u0010V\u001a\u00020<¢\u0006\b\n\u0000\u001a\u0004\bV\u0010?R\u000e\u0010W\u001a\u00020HX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010X\u001a\u00020<8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bY\u0010?R'\u0010Z\u001a\r\u0012\u0004\u0012\u00020\\0[¢\u0006\u0002\b]X\u0086\u000e¢\u0006\u0010\n\u0002\u0010b\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u0014\u0010c\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bc\u0010?R\u0014\u0010d\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010?R\u0014\u0010e\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bf\u0010?R\u0016\u0010\u0087\u0001\u001a\u00020<8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0088\u0001\u0010?¨\u0006Ì\u0001"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/ReusableComposition;", "Landroidx/compose/runtime/RecomposeScopeOwner;", "Landroidx/compose/runtime/CompositionServices;", "Landroidx/compose/runtime/PausableComposition;", "Landroidx/compose/runtime/tooling/ObservableComposition;", "parent", "Landroidx/compose/runtime/CompositionContext;", "applier", "Landroidx/compose/runtime/Applier;", "recomposeContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;Lkotlin/coroutines/CoroutineContext;)V", "getParent", "()Landroidx/compose/runtime/CompositionContext;", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "lock", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "getAbandonSet$annotations", "()V", "slotTable", "Landroidx/compose/runtime/SlotTable;", "getSlotTable$runtime$annotations", "getSlotTable$runtime", "()Landroidx/compose/runtime/SlotTable;", "observations", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/collection/MutableScatterMap;", "observedObjects", "", "getObservedObjects$runtime", "()Ljava/util/Set;", "invalidatedScopes", "Landroidx/collection/MutableScatterSet;", "conditionallyInvalidatedScopes", "derivedStates", "Landroidx/compose/runtime/DerivedState;", "derivedStateDependencies", "getDerivedStateDependencies$runtime", "conditionalScopes", "", "getConditionalScopes$runtime", "()Ljava/util/List;", "changes", "Landroidx/compose/runtime/changelist/ChangeList;", "lateChanges", "observationsProcessed", "invalidations", "pendingInvalidScopes", "", "getPendingInvalidScopes$runtime$annotations", "getPendingInvalidScopes$runtime", "()Z", "setPendingInvalidScopes$runtime", "(Z)V", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "pendingPausedComposition", "Landroidx/compose/runtime/PausedCompositionImpl;", "invalidationDelegate", "invalidationDelegateGroup", "", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime", "()Landroidx/compose/runtime/CompositionObserverHolder;", "rememberManager", "Landroidx/compose/runtime/internal/RememberEventDispatcher;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "getComposer$runtime", "()Landroidx/compose/runtime/ComposerImpl;", "_recomposeContext", "getRecomposeContext", "()Lkotlin/coroutines/CoroutineContext;", "isRoot", "state", "areChildrenComposing", "getAreChildrenComposing", "composable", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "getComposable", "()Lkotlin/jvm/functions/Function2;", "setComposable", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "isComposing", "isDisposed", "hasPendingChanges", "getHasPendingChanges", "setContent", UriUtil.LOCAL_CONTENT_SCHEME, "setContentWithReuse", "setPausableContent", "Landroidx/compose/runtime/PausedComposition;", "(Lkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/PausedComposition;", "setPausableContentWithReuse", "pausedCompositionFinished", "ignoreSet", "Landroidx/collection/ScatterSet;", "Landroidx/compose/runtime/RememberObserverHolder;", "pausedCompositionFinished$runtime", "composeInitial", "composeInitialPaused", "reusable", "(ZLkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/PausedComposition;", "composeInitialWithReuse", "ensureRunning", "clearDeactivated", "setObserver", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "Landroidx/compose/runtime/tooling/CompositionObserver;", "invalidateGroupsWithKey", "key", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "drainPendingModificationsOutOfBandLocked", "composeContent", "updateMovingInvalidations", "updateMovingInvalidations$runtime", "dispose", "hasInvalidations", "getHasInvalidations", "recordModificationsOf", "values", "observesAnyOf", "prepareCompose", "block", "extractInvalidationsOfGroup", "Lkotlin/Pair;", "inGroup", "Lkotlin/Function1;", "Landroidx/compose/runtime/Anchor;", "extractInvalidationsOfGroup$runtime", "addPendingInvalidationsLocked", "value", "forgetConditionalScopes", "cleanUpDerivedStateObservations", "recordReadOf", "invalidateScopeOfLocked", "recordWriteOf", "recompose", "insertMovableContent", "references", "Landroidx/compose/runtime/MovableContentStateReference;", "disposeUnusedMovableContent", "Landroidx/compose/runtime/MovableContentState;", "applyChangesInLocked", "applyChanges", "applyLateChanges", "changesApplied", "guardInvalidationsLocked", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "guardChanges", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "abandonChanges", "invalidateAll", "verifyConsistent", "delegateInvalidations", "R", TypedValues.TransitionType.S_TO, "groupIndex", "(Landroidx/compose/runtime/ControlledComposition;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAndSetShouldPauseCallback", "invalidate", "Landroidx/compose/runtime/InvalidationResult;", "scope", "instance", "recomposeScopeReleased", "getCompositionService", "Landroidx/compose/runtime/CompositionServiceKey;", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "tryImminentInvalidation", "invalidateChecked", "anchor", "removeObservation", "removeObservation$runtime", "removeDerivedStateObservation", "removeDerivedStateObservation$runtime", "takeInvalidations", "takeInvalidations-afanTW4", "()Landroidx/collection/MutableScatterMap;", "validateRecomposeScopeAnchors", "trackAbandonedValues", "deactivate", "composerStacksSizes", "composerStacksSizes$runtime", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CompositionImpl implements ControlledComposition, ReusableComposition, RecomposeScopeOwner, CompositionServices, PausableComposition, ObservableComposition {
    public static final int $stable = 8;
    private final CoroutineContext _recomposeContext;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final ChangeList changes;
    private Function2<? super Composer, ? super Integer, Unit> composable;
    private final ComposerImpl composer;
    private final MutableScatterSet<RecomposeScopeImpl> conditionallyInvalidatedScopes;
    private final MutableScatterMap<Object, Object> derivedStates;
    private final MutableScatterSet<RecomposeScopeImpl> invalidatedScopes;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private MutableScatterMap<Object, Object> invalidations;
    private final boolean isRoot;
    private final ChangeList lateChanges;
    private final Object lock;
    private final MutableScatterMap<Object, Object> observations;
    private final MutableScatterMap<Object, Object> observationsProcessed;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference<Object> pendingModifications;
    private PausedCompositionImpl pendingPausedComposition;
    private final RememberEventDispatcher rememberManager;
    private ShouldPauseCallback shouldPause;
    private final SlotTable slotTable;
    private int state;

    private static /* synthetic */ void getAbandonSet$annotations() {
    }

    public static /* synthetic */ void getPendingInvalidScopes$runtime$annotations() {
    }

    public static /* synthetic */ void getSlotTable$runtime$annotations() {
    }

    public CompositionImpl(CompositionContext parent, Applier<?> applier, CoroutineContext recomposeContext) {
        this.parent = parent;
        this.applier = applier;
        DefaultConstructorMarker defaultConstructorMarker = null;
        this.pendingModifications = new AtomicReference<>(null);
        this.lock = new Object();
        int i = 0;
        int i2 = 1;
        this.abandonSet = new MutableScatterSet(i, i2, defaultConstructorMarker).asMutableSet();
        SlotTable it = new SlotTable();
        if (this.parent.getCollectingCallByInformation$runtime()) {
            it.collectCalledByInformation();
        }
        if (this.parent.getCollectingSourceInformation()) {
            it.collectSourceInformation();
        }
        this.slotTable = it;
        this.observations = ScopeMap.m4469constructorimpl$default(null, 1, null);
        this.invalidatedScopes = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
        this.conditionallyInvalidatedScopes = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
        this.derivedStates = ScopeMap.m4469constructorimpl$default(null, 1, null);
        this.changes = new ChangeList();
        this.lateChanges = new ChangeList();
        this.observationsProcessed = ScopeMap.m4469constructorimpl$default(null, 1, null);
        this.invalidations = ScopeMap.m4469constructorimpl$default(null, 1, null);
        this.observerHolder = new CompositionObserverHolder(null, false, this.parent, 3, null);
        this.rememberManager = new RememberEventDispatcher();
        ComposerImpl it2 = new ComposerImpl(this.applier, this.parent, this.slotTable, this.abandonSet, this.changes, this.lateChanges, this.observerHolder, this);
        this.parent.registerComposer$runtime(it2);
        this.composer = it2;
        this._recomposeContext = recomposeContext;
        this.isRoot = this.parent instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.getLambda$954879418$runtime();
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compositionContext, applier, (i & 4) != 0 ? null : coroutineContext);
    }

    public final CompositionContext getParent() {
        return this.parent;
    }

    /* JADX INFO: renamed from: getSlotTable$runtime, reason: from getter */
    public final SlotTable getSlotTable() {
        return this.slotTable;
    }

    public final Set<Object> getObservedObjects$runtime() {
        return this.observations.asMap().keySet();
    }

    public final Set<Object> getDerivedStateDependencies$runtime() {
        return this.derivedStates.asMap().keySet();
    }

    public final List<RecomposeScopeImpl> getConditionalScopes$runtime() {
        return CollectionsKt.toList(this.conditionallyInvalidatedScopes.asSet());
    }

    /* JADX INFO: renamed from: getPendingInvalidScopes$runtime, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final void setPendingInvalidScopes$runtime(boolean z) {
        this.pendingInvalidScopes = z;
    }

    /* JADX INFO: renamed from: getObserverHolder$runtime, reason: from getter */
    public final CompositionObserverHolder getObserverHolder() {
        return this.observerHolder;
    }

    /* JADX INFO: renamed from: getComposer$runtime, reason: from getter */
    public final ComposerImpl getComposer() {
        return this.composer;
    }

    public final CoroutineContext getRecomposeContext() {
        CoroutineContext coroutineContext = this._recomposeContext;
        return coroutineContext == null ? this.parent.getRecomposeCoroutineContext$runtime() : coroutineContext;
    }

    /* JADX INFO: renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime();
    }

    public final Function2<Composer, Integer, Unit> getComposable() {
        return this.composable;
    }

    public final void setComposable(Function2<? super Composer, ? super Integer, Unit> function2) {
        this.composable = function2;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    public boolean isDisposed() {
        return this.state == 3;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            hasPendingChanges$runtime = this.composer.getHasPendingChanges$runtime();
        }
        return hasPendingChanges$runtime;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        boolean wasDeactivated = clearDeactivated();
        ensureRunning();
        if (wasDeactivated) {
            composeInitialWithReuse(content);
        } else {
            composeInitial(content);
        }
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void setContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        clearDeactivated();
        ensureRunning();
        composeInitialWithReuse(content);
    }

    @Override // androidx.compose.runtime.PausableComposition
    public PausedComposition setPausableContent(Function2<? super Composer, ? super Integer, Unit> content) {
        boolean wasDeactivated = clearDeactivated();
        return composeInitialPaused(wasDeactivated, content);
    }

    @Override // androidx.compose.runtime.PausableComposition
    public PausedComposition setPausableContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        clearDeactivated();
        ensureRunning();
        return composeInitialPaused(true, content);
    }

    public final void pausedCompositionFinished$runtime(ScatterSet<RememberObserverHolder> ignoreSet) {
        this.pendingPausedComposition = null;
        if (ignoreSet != null) {
            this.rememberManager.ignoreForgotten(ignoreSet);
            this.state = 2;
        }
    }

    private final void composeInitial(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composable = content;
        this.parent.composeInitial$runtime(this, this.composable);
    }

    private final PausedComposition composeInitialPaused(boolean reusable, Function2<? super Composer, ? super Integer, Unit> content) {
        boolean value$iv = this.pendingPausedComposition == null;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("A pausable composition is in progress");
        }
        PausedCompositionImpl pausedComposition = new PausedCompositionImpl(this, this.parent, this.composer, this.abandonSet, content, reusable, this.applier, this.lock);
        this.pendingPausedComposition = pausedComposition;
        return pausedComposition;
    }

    private final void composeInitialWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composer.startReuseFromRoot();
        composeInitial(content);
        this.composer.endReuseFromRoot();
    }

    private final void ensureRunning() {
        String str;
        boolean value$iv = this.state == 0;
        if (!value$iv) {
            switch (this.state) {
                case 1:
                    str = "The composition should be activated before setting content.";
                    break;
                case 2:
                    str = "A previous pausable composition for this composition was cancelled. This composition must be disposed.";
                    break;
                case 3:
                    str = "The composition is disposed";
                    break;
                default:
                    str = "";
                    break;
            }
            PreconditionsKt.throwIllegalStateException(str);
        }
        boolean value$iv2 = this.pendingPausedComposition == null;
        if (value$iv2) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("A pausable composition is in progress");
    }

    private final boolean clearDeactivated() {
        boolean isDeactivated;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            isDeactivated = this.state == 1;
            if (isDeactivated) {
                this.state = 0;
            }
        }
        return isDeactivated;
    }

    @Override // androidx.compose.runtime.tooling.ObservableComposition
    public CompositionObserverHandle setObserver(final CompositionObserver observer) {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            this.observerHolder.setObserver(observer);
            this.observerHolder.setRoot(true);
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.CompositionImpl.setObserver.2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object lock$iv2 = CompositionImpl.this.lock;
                CompositionImpl compositionImpl = CompositionImpl.this;
                CompositionObserver compositionObserver = observer;
                synchronized (lock$iv2) {
                    if (Intrinsics.areEqual(compositionImpl.getObserverHolder().getObserver(), compositionObserver)) {
                        compositionImpl.getObserverHolder().setObserver(null);
                        compositionImpl.getObserverHolder().setRoot(false);
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        };
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0045  */
    public final void invalidateGroupsWithKey(int key) throws Throwable {
        boolean forceComposition;
        boolean z;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                try {
                    List<RecomposeScopeImpl> listInvalidateGroupsWithKey$runtime = this.slotTable.invalidateGroupsWithKey$runtime(key);
                    if (listInvalidateGroupsWithKey$runtime != null) {
                        int index$iv$iv = 0;
                        int size = listInvalidateGroupsWithKey$runtime.size();
                        while (true) {
                            if (index$iv$iv < size) {
                                Object item$iv$iv = listInvalidateGroupsWithKey$runtime.get(index$iv$iv);
                                RecomposeScopeImpl it = (RecomposeScopeImpl) item$iv$iv;
                                if (it.invalidateForResult(null) == InvalidationResult.IGNORED) {
                                    z = true;
                                    break;
                                }
                                index$iv$iv++;
                            } else {
                                z = false;
                                break;
                            }
                        }
                        if (z) {
                            forceComposition = true;
                        } else {
                            forceComposition = false;
                        }
                    } else {
                        forceComposition = true;
                    }
                    if (forceComposition && this.composer.forceRecomposeScopes$runtime()) {
                        this.parent.invalidate$runtime(this);
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object toRecord = this.pendingModifications.getAndSet(CompositionKt.PendingApplyNoModifications);
        if (toRecord != null) {
            if (Intrinsics.areEqual(toRecord, CompositionKt.PendingApplyNoModifications)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                throw new KotlinNothingValueException();
            }
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) toRecord, true);
                return;
            }
            if (toRecord instanceof Object[]) {
                for (Set<? extends Object> set : (Set[]) toRecord) {
                    addPendingInvalidationsLocked(set, true);
                }
                return;
            }
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            throw new KotlinNothingValueException();
        }
    }

    private final void drainPendingModificationsLocked() {
        Object toRecord = this.pendingModifications.getAndSet(null);
        if (!Intrinsics.areEqual(toRecord, CompositionKt.PendingApplyNoModifications)) {
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) toRecord, false);
                return;
            }
            if (toRecord instanceof Object[]) {
                for (Set<? extends Object> set : (Set[]) toRecord) {
                    addPendingInvalidationsLocked(set, false);
                }
                return;
            }
            if (toRecord == null) {
                if (this.pendingPausedComposition == null) {
                    ComposerKt.composeImmediateRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
                    return;
                }
                return;
            }
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            throw new KotlinNothingValueException();
        }
    }

    private final void drainPendingModificationsOutOfBandLocked() {
        Object toRecord = this.pendingModifications.getAndSet(SetsKt.emptySet());
        if (!Intrinsics.areEqual(toRecord, CompositionKt.PendingApplyNoModifications) && toRecord != null) {
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) toRecord, false);
                return;
            }
            if (toRecord instanceof Object[]) {
                for (Set<? extends Object> set : (Set[]) toRecord) {
                    addPendingInvalidationsLocked(set, false);
                }
                return;
            }
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            throw new KotlinNothingValueException();
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2<? super Composer, ? super Integer, Unit> content) {
        try {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                try {
                    try {
                        drainPendingModificationsForCompositionLocked();
                        MutableScatterMap<Object, Object> mutableScatterMapM4310takeInvalidationsafanTW4 = m4310takeInvalidationsafanTW4();
                        try {
                            try {
                                try {
                                    try {
                                        this.composer.m4306composeContentZbOJvo$runtime(mutableScatterMapM4310takeInvalidationsafanTW4, content, this.shouldPause);
                                        Unit unit = Unit.INSTANCE;
                                        Unit unit2 = Unit.INSTANCE;
                                        Unit unit3 = Unit.INSTANCE;
                                    } catch (Throwable th) {
                                        e$iv = th;
                                        this.invalidations = mutableScatterMapM4310takeInvalidationsafanTW4;
                                        throw e$iv;
                                    }
                                } catch (Throwable th2) {
                                    e$iv = th2;
                                }
                            } catch (Throwable th3) {
                                e$iv = th3;
                            }
                        } catch (Throwable th4) {
                            e$iv = th4;
                            throw e$iv;
                        }
                    } catch (Throwable th5) {
                        e$iv = th5;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    if (0 == 0) {
                        try {
                            if (!this.abandonSet.isEmpty()) {
                                RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                                Set<RememberObserver> set = this.abandonSet;
                                CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                                try {
                                    this_$iv$iv$iv.prepare(set, traceContext$iv$iv$iv);
                                    this_$iv$iv$iv.dispatchAbandons();
                                } finally {
                                    this_$iv$iv$iv.clear();
                                }
                            }
                        } catch (Throwable e$iv) {
                            abandonChanges();
                            throw e$iv;
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th7) {
            th = th7;
        }
    }

    public final void updateMovingInvalidations$runtime() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            drainPendingModificationsOutOfBandLocked();
            MutableScatterMap<Object, Object> mutableScatterMapM4310takeInvalidationsafanTW4 = m4310takeInvalidationsafanTW4();
            try {
                this.composer.m4308updateComposerInvalidationsRY85e9Y(mutableScatterMapM4310takeInvalidationsafanTW4);
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable e$iv) {
                this.invalidations = mutableScatterMapM4310takeInvalidationsafanTW4;
                throw e$iv;
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() throws Throwable {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                boolean value$iv = !this.composer.getIsComposing();
                if (!value$iv) {
                    try {
                        PreconditionsKt.throwIllegalStateException("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                try {
                    if (this.state != 3) {
                        this.state = 3;
                        this.composable = ComposableSingletons$CompositionKt.INSTANCE.getLambda$1918065384$runtime();
                        ChangeList deferredChanges = this.composer.getDeferredChanges();
                        if (deferredChanges != null) {
                            applyChangesInLocked(deferredChanges);
                        }
                        boolean nonEmptySlotTable = this.slotTable.getGroupsSize() > 0;
                        if (nonEmptySlotTable || !this.abandonSet.isEmpty()) {
                            RememberEventDispatcher this_$iv = this.rememberManager;
                            Set<RememberObserver> set = this.abandonSet;
                            CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
                            try {
                                this_$iv.prepare(set, traceContext$iv);
                                if (nonEmptySlotTable) {
                                    this.applier.onBeginChanges();
                                    SlotWriter writer$iv = this.slotTable.openWriter();
                                    try {
                                        try {
                                            ComposerKt.removeCurrentGroup(writer$iv, this.rememberManager);
                                            Unit unit = Unit.INSTANCE;
                                            writer$iv.close(true);
                                            this.applier.clear();
                                            this.applier.onEndChanges();
                                            this_$iv.dispatchRememberObservers();
                                        } catch (Throwable th2) {
                                            th = th2;
                                            this_$iv.clear();
                                            throw th;
                                        }
                                    } catch (Throwable th3) {
                                        writer$iv.close(false);
                                        throw th3;
                                    }
                                }
                                this_$iv.dispatchAbandons();
                                this_$iv.clear();
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        }
                        this.composer.dispose$runtime();
                    }
                    Unit unit2 = Unit.INSTANCE;
                    this.parent.unregisterComposition$runtime(this);
                } catch (Throwable th5) {
                    th = th5;
                    throw th;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            z = ScopeMap.m4474getSizeimpl(this.invalidations) > 0;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        Object objPlus;
        do {
            obj = this.pendingModifications.get();
            if (obj == null || Intrinsics.areEqual(obj, CompositionKt.PendingApplyNoModifications)) {
                objPlus = values;
            } else if (obj instanceof Set) {
                objPlus = new Set[]{obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                objPlus = ArraysKt.plus((Set<? extends Object>[]) obj, values);
            }
        } while (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.pendingModifications, obj, objPlus));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean observesAnyOf(Set<? extends Object> values) {
        ScatterSet this_$iv$iv;
        Set $this$fastForEach$iv = values;
        int $i$f$fastForEach = 0;
        if (!($this$fastForEach$iv instanceof ScatterSetWrapper)) {
            Set $this$forEach$iv$iv = $this$fastForEach$iv;
            for (Object element$iv$iv : $this$forEach$iv$iv) {
                if (ScopeMap.m4470containsimpl(this.observations, element$iv$iv) || ScopeMap.m4470containsimpl(this.derivedStates, element$iv$iv)) {
                    return true;
                }
            }
            return false;
        }
        ScatterSet this_$iv$iv2 = ((ScatterSetWrapper) $this$fastForEach$iv).getSet$runtime();
        Object[] elements$iv$iv = this_$iv$iv2.elements;
        long[] m$iv$iv$iv = this_$iv$iv2.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return false;
        }
        loop0: while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            Set $this$fastForEach$iv2 = $this$fastForEach$iv;
            int $i$f$fastForEach2 = $i$f$fastForEach;
            if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                int i = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv = 0;
                while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                    if ($i$f$isFull != 0) {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                        Object value = elements$iv$iv[index$iv$iv$iv];
                        if (ScopeMap.m4470containsimpl(this.observations, value) || ScopeMap.m4470containsimpl(this.derivedStates, value)) {
                            break loop0;
                        }
                    }
                    slot$iv$iv$iv >>= i;
                    j$iv$iv$iv++;
                    i = i;
                    this_$iv$iv2 = this_$iv$iv2;
                }
                this_$iv$iv = this_$iv$iv2;
                if (bitCount$iv$iv$iv != i) {
                    return false;
                }
            } else {
                this_$iv$iv = this_$iv$iv2;
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return false;
            }
            i$iv$iv$iv++;
            $this$fastForEach$iv = $this$fastForEach$iv2;
            $i$f$fastForEach = $i$f$fastForEach2;
            this_$iv$iv2 = this_$iv$iv;
        }
        return true;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0<Unit> block) {
        this.composer.prepareCompose$runtime(block);
    }

    public final List<Pair<RecomposeScopeImpl, Object>> extractInvalidationsOfGroup$runtime(Function1<? super Anchor, Boolean> inGroup) {
        int $i$f$extractInvalidationsOfGroup$runtime;
        int $i$f$removeIf;
        ScatterMap this_$iv$iv$iv;
        int $i$f$forEachIndexed;
        long[] m$iv$iv$iv;
        int $i$f$extractInvalidationsOfGroup$runtime2;
        int $i$f$forEachIndexed2;
        long[] m$iv$iv$iv2;
        long $this$maskEmptyOrDeleted$iv$iv$iv$iv;
        int j$iv$iv$iv;
        boolean zIsEmpty;
        int j$iv$iv$iv2;
        int j$iv$iv$iv3;
        int $i$f$extractInvalidationsOfGroup$runtime3 = 0;
        if (ScopeMap.m4474getSizeimpl(this.invalidations) <= 0) {
            return CollectionsKt.emptyList();
        }
        List result = new ArrayList();
        MutableScatterMap mutableScatterMap = this.invalidations;
        int i = 0;
        int $i$f$removeIf2 = 0;
        MutableScatterMap this_$iv$iv$iv2 = mutableScatterMap;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv$iv3 = this_$iv$iv$iv2.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv3.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return result;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv3[i$iv$iv$iv];
            MutableScatterMap mutableScatterMap2 = mutableScatterMap;
            int i2 = i;
            if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                $i$f$extractInvalidationsOfGroup$runtime = $i$f$extractInvalidationsOfGroup$runtime3;
                $i$f$removeIf = $i$f$removeIf2;
                this_$iv$iv$iv = this_$iv$iv$iv2;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
                m$iv$iv$iv = m$iv$iv$iv3;
            } else {
                int i3 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv4 = 0;
                while (j$iv$iv$iv4 < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    boolean z = false;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        $i$f$extractInvalidationsOfGroup$runtime2 = $i$f$extractInvalidationsOfGroup$runtime3;
                        $i$f$removeIf2 = $i$f$removeIf2;
                        this_$iv$iv$iv2 = this_$iv$iv$iv2;
                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                        m$iv$iv$iv2 = m$iv$iv$iv3;
                        $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                        j$iv$iv$iv = j$iv$iv$iv4;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv4;
                        Object key$iv = mutableScatterMap.keys[index$iv$iv$iv];
                        int i4 = i3;
                        Object scopes$iv = mutableScatterMap.values[index$iv$iv$iv];
                        $i$f$extractInvalidationsOfGroup$runtime2 = $i$f$extractInvalidationsOfGroup$runtime3;
                        Intrinsics.checkNotNull(key$iv, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.ScopeMap");
                        if (!(scopes$iv instanceof MutableScatterSet)) {
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv$iv2 = m$iv$iv$iv3;
                            $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                            j$iv$iv$iv = j$iv$iv$iv4;
                            Intrinsics.checkNotNull(scopes$iv, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                            RecomposeScopeImpl scope = (RecomposeScopeImpl) key$iv;
                            Anchor scopeAnchor = scope.getAnchor();
                            if (scopeAnchor != null && inGroup.invoke(scopeAnchor).booleanValue()) {
                                result.add(TuplesKt.to(scope, scopes$iv));
                                z = true;
                            }
                            zIsEmpty = z;
                        } else {
                            Intrinsics.checkNotNull(scopes$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                            MutableScatterSet this_$iv$iv = (MutableScatterSet) scopes$iv;
                            Object[] elements$iv$iv = this_$iv$iv.elements;
                            long[] m$iv$iv$iv4 = this_$iv$iv.metadata;
                            int lastIndex$iv$iv$iv2 = m$iv$iv$iv4.length - 2;
                            int i$iv$iv$iv2 = 0;
                            if (0 <= lastIndex$iv$iv$iv2) {
                                while (true) {
                                    long slot$iv$iv$iv2 = m$iv$iv$iv4[i$iv$iv$iv2];
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                    m$iv$iv$iv2 = m$iv$iv$iv3;
                                    $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                                    if ((((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                                        j$iv$iv$iv = j$iv$iv$iv4;
                                    } else {
                                        int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv2)) >>> 31);
                                        int j$iv$iv$iv5 = 0;
                                        while (j$iv$iv$iv5 < bitCount$iv$iv$iv2) {
                                            long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                            if (!(value$iv$iv$iv$iv2 < 128)) {
                                                j$iv$iv$iv2 = j$iv$iv$iv5;
                                                j$iv$iv$iv4 = j$iv$iv$iv4;
                                            } else {
                                                int index$iv$iv$iv2 = (i$iv$iv$iv2 << 3) + j$iv$iv$iv5;
                                                Object it$iv = elements$iv$iv[index$iv$iv$iv2];
                                                RecomposeScopeImpl scope2 = (RecomposeScopeImpl) key$iv;
                                                j$iv$iv$iv2 = j$iv$iv$iv5;
                                                Anchor scopeAnchor2 = scope2.getAnchor();
                                                if (scopeAnchor2 != null && inGroup.invoke(scopeAnchor2).booleanValue()) {
                                                    result.add(TuplesKt.to(scope2, it$iv));
                                                    j$iv$iv$iv3 = 1;
                                                } else {
                                                    j$iv$iv$iv3 = 0;
                                                }
                                                if (j$iv$iv$iv3 != 0) {
                                                    this_$iv$iv.removeElementAt(index$iv$iv$iv2);
                                                }
                                            }
                                            slot$iv$iv$iv2 >>= i4;
                                            j$iv$iv$iv5 = j$iv$iv$iv2 + 1;
                                            j$iv$iv$iv4 = j$iv$iv$iv4;
                                        }
                                        j$iv$iv$iv = j$iv$iv$iv4;
                                        int j$iv$iv$iv6 = i4;
                                        if (bitCount$iv$iv$iv2 != j$iv$iv$iv6) {
                                            break;
                                        }
                                    }
                                    if (i$iv$iv$iv2 == lastIndex$iv$iv$iv2) {
                                        break;
                                    }
                                    i$iv$iv$iv2++;
                                    slot$iv$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv$iv;
                                    $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                    m$iv$iv$iv3 = m$iv$iv$iv2;
                                    j$iv$iv$iv4 = j$iv$iv$iv;
                                    i4 = 8;
                                }
                            } else {
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                m$iv$iv$iv2 = m$iv$iv$iv3;
                                $this$maskEmptyOrDeleted$iv$iv$iv$iv = slot$iv$iv$iv;
                                j$iv$iv$iv = j$iv$iv$iv4;
                            }
                            zIsEmpty = ((MutableScatterSet) scopes$iv).isEmpty();
                        }
                        if (zIsEmpty) {
                            mutableScatterMap.removeValueAt(index$iv$iv$iv);
                        }
                    }
                    slot$iv$iv$iv = $this$maskEmptyOrDeleted$iv$iv$iv$iv >> 8;
                    j$iv$iv$iv4 = j$iv$iv$iv + 1;
                    i3 = 8;
                    $i$f$extractInvalidationsOfGroup$runtime3 = $i$f$extractInvalidationsOfGroup$runtime2;
                    $i$f$removeIf2 = $i$f$removeIf2;
                    this_$iv$iv$iv2 = this_$iv$iv$iv2;
                    $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                    m$iv$iv$iv3 = m$iv$iv$iv2;
                }
                $i$f$extractInvalidationsOfGroup$runtime = $i$f$extractInvalidationsOfGroup$runtime3;
                $i$f$removeIf = $i$f$removeIf2;
                this_$iv$iv$iv = this_$iv$iv$iv2;
                $i$f$forEachIndexed = $i$f$forEachIndexed3;
                m$iv$iv$iv = m$iv$iv$iv3;
                if (bitCount$iv$iv$iv != i3) {
                    return result;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return result;
            }
            i$iv$iv$iv++;
            mutableScatterMap = mutableScatterMap2;
            i = i2;
            $i$f$extractInvalidationsOfGroup$runtime3 = $i$f$extractInvalidationsOfGroup$runtime;
            $i$f$removeIf2 = $i$f$removeIf;
            this_$iv$iv$iv2 = this_$iv$iv$iv;
            $i$f$forEachIndexed3 = $i$f$forEachIndexed;
            m$iv$iv$iv3 = m$iv$iv$iv;
        }
    }

    private final void addPendingInvalidationsLocked(Object value, boolean forgetConditionalScopes) {
        int i;
        int j$iv$iv$iv;
        MutableScatterMap<Object, Object> mutableScatterMap = this.observations;
        Object key$iv = value;
        int i2 = 0;
        Object value$iv = mutableScatterMap.get(key$iv);
        if (value$iv == null) {
            return;
        }
        if (!(value$iv instanceof MutableScatterSet)) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) value$iv;
            if (!ScopeMap.m4476removeimpl(this.observationsProcessed, value, scope) && scope.invalidateForResult(value) != InvalidationResult.IGNORED) {
                if (scope.isConditional() && !forgetConditionalScopes) {
                    this.conditionallyInvalidatedScopes.add(scope);
                    return;
                } else {
                    this.invalidatedScopes.add(scope);
                    return;
                }
            }
            return;
        }
        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
        Object[] elements$iv$iv = this_$iv$iv.elements;
        long[] m$iv$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            MutableScatterMap<Object, Object> mutableScatterMap2 = mutableScatterMap;
            Object key$iv2 = key$iv;
            int i3 = i2;
            Object value$iv2 = value$iv;
            if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                int i4 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv2 = 0;
                while (j$iv$iv$iv2 < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        i = i4;
                        j$iv$iv$iv = j$iv$iv$iv2;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv2;
                        i = i4;
                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) elements$iv$iv[index$iv$iv$iv];
                        j$iv$iv$iv = j$iv$iv$iv2;
                        if (!ScopeMap.m4476removeimpl(this.observationsProcessed, value, scope2) && scope2.invalidateForResult(value) != InvalidationResult.IGNORED) {
                            if (scope2.isConditional() && !forgetConditionalScopes) {
                                this.conditionallyInvalidatedScopes.add(scope2);
                            } else {
                                this.invalidatedScopes.add(scope2);
                            }
                        }
                    }
                    slot$iv$iv$iv >>= i;
                    j$iv$iv$iv2 = j$iv$iv$iv + 1;
                    i4 = i;
                }
                if (bitCount$iv$iv$iv != i4) {
                    return;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return;
            }
            i$iv$iv$iv++;
            i2 = i3;
            value$iv = value$iv2;
            mutableScatterMap = mutableScatterMap2;
            key$iv = key$iv2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:231:0x0143 A[EDGE_INSN: B:231:0x0143->B:45:0x0143 BREAK  A[LOOP:2: B:24:0x00b5->B:39:0x010b], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:263:0x0297 A[EDGE_INSN: B:263:0x0297->B:90:0x0297 BREAK  A[LOOP:13: B:70:0x020c->B:84:0x0260], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:38:0x0109 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:39:0x010b A[LOOP:2: B:24:0x00b5->B:39:0x010b, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:83:0x025e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:84:0x0260 A[LOOP:13: B:70:0x020c->B:84:0x0260, LOOP_END] */
    private final void addPendingInvalidationsLocked(Set<? extends Object> values, boolean forgetConditionalScopes) {
        long j;
        long j2;
        char c;
        long j3;
        Iterable $this$forEach$iv$iv;
        int $i$f$forEach;
        Iterator it;
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet;
        String str;
        int $i$f$removeIf;
        ScatterMap this_$iv$iv$iv;
        int j$iv$iv$iv;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv$iv2;
        boolean zContains;
        int $i$f$removeIf3;
        ScatterMap this_$iv$iv$iv3;
        int $i$f$forEachIndexed;
        int i$iv$iv$iv;
        String str2;
        int j$iv$iv$iv2;
        int $i$f$removeIf4;
        ScatterMap this_$iv$iv$iv4;
        int $i$f$forEachIndexed2;
        String str3;
        boolean zIsEmpty;
        Set $this$fastForEach$iv;
        int $i$f$fastForEach;
        ScatterSet this_$iv$iv;
        Object[] elements$iv$iv;
        ScatterSet this_$iv$iv$iv5;
        ScatterSet this_$iv$iv2;
        Object[] elements$iv$iv2;
        ScatterSet this_$iv$iv$iv6;
        Set $this$fastForEach$iv2 = values;
        int $i$f$fastForEach2 = 0;
        if ($this$fastForEach$iv2 instanceof ScatterSetWrapper) {
            ScatterSet this_$iv$iv3 = ((ScatterSetWrapper) $this$fastForEach$iv2).getSet$runtime();
            j = 128;
            Object[] elements$iv$iv3 = this_$iv$iv3.elements;
            ScatterSet this_$iv$iv$iv7 = this_$iv$iv3;
            j2 = 255;
            long[] m$iv$iv$iv = this_$iv$iv$iv7.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            c = 7;
            int i$iv$iv$iv2 = 0;
            if (0 <= lastIndex$iv$iv$iv) {
                while (true) {
                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv2];
                    int i = 8;
                    j3 = -9187201950435737472L;
                    if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv)) >>> 31);
                        int j$iv$iv$iv3 = 0;
                        while (j$iv$iv$iv3 < bitCount$iv$iv$iv) {
                            long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                            int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                            if ($i$f$isFull != 0) {
                                int i2 = i;
                                Object value = elements$iv$iv3[(i$iv$iv$iv2 << 3) + j$iv$iv$iv3];
                                if (value instanceof RecomposeScopeImpl) {
                                    ((RecomposeScopeImpl) value).invalidateForResult(null);
                                    this_$iv$iv2 = this_$iv$iv3;
                                    elements$iv$iv2 = elements$iv$iv3;
                                    this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                } else {
                                    addPendingInvalidationsLocked(value, forgetConditionalScopes);
                                    Object key$iv = value;
                                    Object value$iv = this.derivedStates.get(key$iv);
                                    if (value$iv == null) {
                                        this_$iv$iv2 = this_$iv$iv3;
                                        elements$iv$iv2 = elements$iv$iv3;
                                        this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                    } else if (value$iv instanceof MutableScatterSet) {
                                        ScatterSet this_$iv$iv4 = (MutableScatterSet) value$iv;
                                        Object[] elements$iv$iv4 = this_$iv$iv4.elements;
                                        long[] m$iv$iv$iv2 = this_$iv$iv4.metadata;
                                        int lastIndex$iv$iv$iv2 = m$iv$iv$iv2.length - 2;
                                        int i$iv$iv$iv3 = 0;
                                        if (0 <= lastIndex$iv$iv$iv2) {
                                            while (true) {
                                                long slot$iv$iv$iv2 = m$iv$iv$iv2[i$iv$iv$iv3];
                                                this_$iv$iv2 = this_$iv$iv3;
                                                Object key$iv2 = key$iv;
                                                elements$iv$iv2 = elements$iv$iv3;
                                                this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                                if ((((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                    if (i$iv$iv$iv3 != lastIndex$iv$iv$iv2) {
                                                        break;
                                                        break;
                                                    }
                                                    i$iv$iv$iv3++;
                                                    elements$iv$iv3 = elements$iv$iv2;
                                                    this_$iv$iv$iv7 = this_$iv$iv$iv6;
                                                    this_$iv$iv3 = this_$iv$iv2;
                                                    key$iv = key$iv2;
                                                    i2 = 8;
                                                } else {
                                                    int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv3 - lastIndex$iv$iv$iv2)) >>> 31);
                                                    for (int j$iv$iv$iv4 = 0; j$iv$iv$iv4 < bitCount$iv$iv$iv2; j$iv$iv$iv4++) {
                                                        long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                                        if (value$iv$iv$iv$iv2 < 128) {
                                                            DerivedState it2 = (DerivedState) elements$iv$iv4[(i$iv$iv$iv3 << 3) + j$iv$iv$iv4];
                                                            addPendingInvalidationsLocked(it2, forgetConditionalScopes);
                                                        }
                                                        slot$iv$iv$iv2 >>= i2;
                                                    }
                                                    int j$iv$iv$iv5 = i2;
                                                    if (bitCount$iv$iv$iv2 != j$iv$iv$iv5) {
                                                        break;
                                                    }
                                                    if (i$iv$iv$iv3 != lastIndex$iv$iv$iv2) {
                                                        break;
                                                    }
                                                    i$iv$iv$iv3++;
                                                    elements$iv$iv3 = elements$iv$iv2;
                                                    this_$iv$iv$iv7 = this_$iv$iv$iv6;
                                                    this_$iv$iv3 = this_$iv$iv2;
                                                    key$iv = key$iv2;
                                                    i2 = 8;
                                                }
                                            }
                                        } else {
                                            this_$iv$iv2 = this_$iv$iv3;
                                            elements$iv$iv2 = elements$iv$iv3;
                                            this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                        }
                                    } else {
                                        this_$iv$iv2 = this_$iv$iv3;
                                        elements$iv$iv2 = elements$iv$iv3;
                                        this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                        DerivedState it3 = (DerivedState) value$iv;
                                        addPendingInvalidationsLocked(it3, forgetConditionalScopes);
                                    }
                                    Unit unit = Unit.INSTANCE;
                                }
                            } else {
                                this_$iv$iv2 = this_$iv$iv3;
                                elements$iv$iv2 = elements$iv$iv3;
                                this_$iv$iv$iv6 = this_$iv$iv$iv7;
                            }
                            i = 8;
                            slot$iv$iv$iv >>= 8;
                            j$iv$iv$iv3++;
                            $this$fastForEach$iv2 = $this$fastForEach$iv2;
                            $i$f$fastForEach2 = $i$f$fastForEach2;
                            elements$iv$iv3 = elements$iv$iv2;
                            this_$iv$iv$iv7 = this_$iv$iv$iv6;
                            this_$iv$iv3 = this_$iv$iv2;
                        }
                        $this$fastForEach$iv = $this$fastForEach$iv2;
                        $i$f$fastForEach = $i$f$fastForEach2;
                        this_$iv$iv = this_$iv$iv3;
                        elements$iv$iv = elements$iv$iv3;
                        this_$iv$iv$iv5 = this_$iv$iv$iv7;
                        if (bitCount$iv$iv$iv != i) {
                            break;
                        }
                    } else {
                        $this$fastForEach$iv = $this$fastForEach$iv2;
                        $i$f$fastForEach = $i$f$fastForEach2;
                        this_$iv$iv = this_$iv$iv3;
                        elements$iv$iv = elements$iv$iv3;
                        this_$iv$iv$iv5 = this_$iv$iv$iv7;
                    }
                    if (i$iv$iv$iv2 == lastIndex$iv$iv$iv) {
                        break;
                    }
                    i$iv$iv$iv2++;
                    $this$fastForEach$iv2 = $this$fastForEach$iv;
                    $i$f$fastForEach2 = $i$f$fastForEach;
                    elements$iv$iv3 = elements$iv$iv;
                    this_$iv$iv$iv7 = this_$iv$iv$iv5;
                    this_$iv$iv3 = this_$iv$iv;
                }
            } else {
                j3 = -9187201950435737472L;
            }
        } else {
            j = 128;
            j2 = 255;
            c = 7;
            j3 = -9187201950435737472L;
            Set $this$forEach$iv$iv2 = $this$fastForEach$iv2;
            int $i$f$forEach2 = 0;
            Iterator it4 = $this$forEach$iv$iv2.iterator();
            while (it4.hasNext()) {
                Object element$iv$iv = it4.next();
                Object value2 = element$iv$iv;
                int i3 = 0;
                if (value2 instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) value2).invalidateForResult(null);
                    $this$forEach$iv$iv = $this$forEach$iv$iv2;
                    $i$f$forEach = $i$f$forEach2;
                    it = it4;
                } else {
                    addPendingInvalidationsLocked(value2, forgetConditionalScopes);
                    Object value$iv2 = this.derivedStates.get(value2);
                    if (value$iv2 == null) {
                        $this$forEach$iv$iv = $this$forEach$iv$iv2;
                        $i$f$forEach = $i$f$forEach2;
                        it = it4;
                    } else if (value$iv2 instanceof MutableScatterSet) {
                        ScatterSet this_$iv$iv5 = (MutableScatterSet) value$iv2;
                        Object[] elements$iv$iv5 = this_$iv$iv5.elements;
                        $this$forEach$iv$iv = $this$forEach$iv$iv2;
                        long[] m$iv$iv$iv3 = this_$iv$iv5.metadata;
                        $i$f$forEach = $i$f$forEach2;
                        int $i$f$forEach3 = m$iv$iv$iv3.length;
                        int lastIndex$iv$iv$iv3 = $i$f$forEach3 - 2;
                        int i$iv$iv$iv4 = 0;
                        if (0 <= lastIndex$iv$iv$iv3) {
                            while (true) {
                                long slot$iv$iv$iv3 = m$iv$iv$iv3[i$iv$iv$iv4];
                                it = it4;
                                Object element$iv$iv2 = element$iv$iv;
                                Object value3 = value2;
                                int i4 = i3;
                                if ((((~slot$iv$iv$iv3) << 7) & slot$iv$iv$iv3 & (-9187201950435737472L)) == -9187201950435737472L) {
                                    if (i$iv$iv$iv4 != lastIndex$iv$iv$iv3) {
                                        break;
                                        break;
                                    }
                                    i$iv$iv$iv4++;
                                    it4 = it;
                                    element$iv$iv = element$iv$iv2;
                                    value2 = value3;
                                    i3 = i4;
                                } else {
                                    int bitCount$iv$iv$iv3 = 8 - ((~(i$iv$iv$iv4 - lastIndex$iv$iv$iv3)) >>> 31);
                                    for (int j$iv$iv$iv6 = 0; j$iv$iv$iv6 < bitCount$iv$iv$iv3; j$iv$iv$iv6++) {
                                        long value$iv$iv$iv$iv3 = slot$iv$iv$iv3 & 255;
                                        int $i$f$isFull2 = value$iv$iv$iv$iv3 < 128 ? 1 : 0;
                                        if ($i$f$isFull2 != 0) {
                                            DerivedState it5 = (DerivedState) elements$iv$iv5[(i$iv$iv$iv4 << 3) + j$iv$iv$iv6];
                                            addPendingInvalidationsLocked(it5, forgetConditionalScopes);
                                        }
                                        slot$iv$iv$iv3 >>= 8;
                                    }
                                    if (bitCount$iv$iv$iv3 != 8) {
                                        break;
                                    }
                                    if (i$iv$iv$iv4 != lastIndex$iv$iv$iv3) {
                                        break;
                                    }
                                    i$iv$iv$iv4++;
                                    it4 = it;
                                    element$iv$iv = element$iv$iv2;
                                    value2 = value3;
                                    i3 = i4;
                                }
                            }
                        } else {
                            it = it4;
                        }
                    } else {
                        $this$forEach$iv$iv = $this$forEach$iv$iv2;
                        $i$f$forEach = $i$f$forEach2;
                        it = it4;
                        DerivedState it6 = (DerivedState) value$iv2;
                        addPendingInvalidationsLocked(it6, forgetConditionalScopes);
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
                $this$forEach$iv$iv2 = $this$forEach$iv$iv;
                $i$f$forEach2 = $i$f$forEach;
                it4 = it;
            }
        }
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet2 = this.conditionallyInvalidatedScopes;
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet3 = this.invalidatedScopes;
        String str4 = "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>";
        String str5 = "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap";
        if (!forgetConditionalScopes || !mutableScatterSet2.isNotEmpty()) {
            if (mutableScatterSet3.isNotEmpty()) {
                MutableScatterMap<Object, Object> mutableScatterMap = this.observations;
                int i5 = 0;
                int $i$f$removeIf5 = 0;
                MutableScatterMap<Object, Object> this_$iv$iv$iv8 = mutableScatterMap;
                long[] m$iv$iv$iv4 = this_$iv$iv$iv8.metadata;
                int lastIndex$iv$iv$iv4 = m$iv$iv$iv4.length - 2;
                int i$iv$iv$iv5 = 0;
                if (0 <= lastIndex$iv$iv$iv4) {
                    while (true) {
                        long slot$iv$iv$iv4 = m$iv$iv$iv4[i$iv$iv$iv5];
                        int i$iv$iv$iv6 = i$iv$iv$iv5;
                        MutableScatterMap<Object, Object> mutableScatterMap2 = mutableScatterMap;
                        int i6 = i5;
                        if ((((~slot$iv$iv$iv4) << c) & slot$iv$iv$iv4 & j3) != j3) {
                            int bitCount$iv$iv$iv4 = 8 - ((~(i$iv$iv$iv6 - lastIndex$iv$iv$iv4)) >>> 31);
                            int j$iv$iv$iv7 = 0;
                            while (j$iv$iv$iv7 < bitCount$iv$iv$iv4) {
                                long value$iv$iv$iv$iv4 = slot$iv$iv$iv4 & j2;
                                int $i$f$isFull3 = value$iv$iv$iv$iv4 < j ? 1 : 0;
                                if ($i$f$isFull3 != 0) {
                                    int index$iv$iv$iv = (i$iv$iv$iv6 << 3) + j$iv$iv$iv7;
                                    Object obj = mutableScatterMap.keys[index$iv$iv$iv];
                                    Object value$iv3 = mutableScatterMap.values[index$iv$iv$iv];
                                    if (value$iv3 instanceof MutableScatterSet) {
                                        Intrinsics.checkNotNull(value$iv3, str4);
                                        MutableScatterSet this_$iv$iv6 = (MutableScatterSet) value$iv3;
                                        Object[] elements$iv$iv6 = this_$iv$iv6.elements;
                                        long[] m$iv$iv$iv5 = this_$iv$iv6.metadata;
                                        int lastIndex$iv$iv$iv5 = m$iv$iv$iv5.length - 2;
                                        int i$iv$iv$iv7 = 0;
                                        if (0 <= lastIndex$iv$iv$iv5) {
                                            while (true) {
                                                long slot$iv$iv$iv5 = m$iv$iv$iv5[i$iv$iv$iv7];
                                                j$iv$iv$iv = j$iv$iv$iv7;
                                                int index$iv$iv$iv2 = index$iv$iv$iv;
                                                $i$f$removeIf2 = $i$f$removeIf5;
                                                this_$iv$iv$iv2 = this_$iv$iv$iv8;
                                                if ((((~slot$iv$iv$iv5) << c) & slot$iv$iv$iv5 & j3) != j3) {
                                                    int bitCount$iv$iv$iv5 = 8 - ((~(i$iv$iv$iv7 - lastIndex$iv$iv$iv5)) >>> 31);
                                                    for (int j$iv$iv$iv8 = 0; j$iv$iv$iv8 < bitCount$iv$iv$iv5; j$iv$iv$iv8++) {
                                                        long value$iv$iv$iv$iv5 = slot$iv$iv$iv5 & j2;
                                                        int $i$f$isFull4 = value$iv$iv$iv$iv5 < j ? 1 : 0;
                                                        if ($i$f$isFull4 != 0) {
                                                            int index$iv$iv$iv3 = (i$iv$iv$iv7 << 3) + j$iv$iv$iv8;
                                                            RecomposeScopeImpl scope = (RecomposeScopeImpl) elements$iv$iv6[index$iv$iv$iv3];
                                                            if (mutableScatterSet3.contains(scope)) {
                                                                this_$iv$iv6.removeElementAt(index$iv$iv$iv3);
                                                            }
                                                        }
                                                        slot$iv$iv$iv5 >>= 8;
                                                    }
                                                    if (bitCount$iv$iv$iv5 != 8) {
                                                        break;
                                                    }
                                                }
                                                if (i$iv$iv$iv7 == lastIndex$iv$iv$iv5) {
                                                    break;
                                                }
                                                i$iv$iv$iv7++;
                                                value$iv3 = value$iv3;
                                                $i$f$removeIf5 = $i$f$removeIf2;
                                                this_$iv$iv$iv8 = this_$iv$iv$iv2;
                                                j$iv$iv$iv7 = j$iv$iv$iv;
                                                index$iv$iv$iv = index$iv$iv$iv2;
                                            }
                                        } else {
                                            j$iv$iv$iv = j$iv$iv$iv7;
                                            $i$f$removeIf2 = $i$f$removeIf5;
                                            this_$iv$iv$iv2 = this_$iv$iv$iv8;
                                        }
                                        zContains = this_$iv$iv6.isEmpty();
                                    } else {
                                        j$iv$iv$iv = j$iv$iv$iv7;
                                        $i$f$removeIf2 = $i$f$removeIf5;
                                        this_$iv$iv$iv2 = this_$iv$iv$iv8;
                                        Intrinsics.checkNotNull(value$iv3, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) value$iv3;
                                        zContains = mutableScatterSet3.contains(scope2);
                                    }
                                    if (zContains) {
                                        mutableScatterMap.removeValueAt(index$iv$iv$iv);
                                    }
                                } else {
                                    mutableScatterSet2 = mutableScatterSet2;
                                    str4 = str4;
                                    j$iv$iv$iv = j$iv$iv$iv7;
                                    $i$f$removeIf2 = $i$f$removeIf5;
                                    this_$iv$iv$iv2 = this_$iv$iv$iv8;
                                }
                                slot$iv$iv$iv4 >>= 8;
                                j$iv$iv$iv7 = j$iv$iv$iv + 1;
                                mutableScatterSet2 = mutableScatterSet2;
                                str4 = str4;
                                $i$f$removeIf5 = $i$f$removeIf2;
                                this_$iv$iv$iv8 = this_$iv$iv$iv2;
                            }
                            mutableScatterSet = mutableScatterSet2;
                            str = str4;
                            $i$f$removeIf = $i$f$removeIf5;
                            this_$iv$iv$iv = this_$iv$iv$iv8;
                            if (bitCount$iv$iv$iv4 != 8) {
                                break;
                            }
                        } else {
                            mutableScatterSet = mutableScatterSet2;
                            str = str4;
                            $i$f$removeIf = $i$f$removeIf5;
                            this_$iv$iv$iv = this_$iv$iv$iv8;
                        }
                        if (i$iv$iv$iv6 == lastIndex$iv$iv$iv4) {
                            break;
                        }
                        i$iv$iv$iv5 = i$iv$iv$iv6 + 1;
                        mutableScatterMap = mutableScatterMap2;
                        i5 = i6;
                        mutableScatterSet2 = mutableScatterSet;
                        str4 = str;
                        $i$f$removeIf5 = $i$f$removeIf;
                        this_$iv$iv$iv8 = this_$iv$iv$iv;
                    }
                }
                cleanUpDerivedStateObservations();
                mutableScatterSet3.clear();
                return;
            }
            return;
        }
        MutableScatterMap<Object, Object> mutableScatterMap3 = this.observations;
        int i7 = 0;
        int $i$f$removeIf6 = 0;
        MutableScatterMap<Object, Object> this_$iv$iv$iv9 = mutableScatterMap3;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv$iv6 = this_$iv$iv$iv9.metadata;
        int lastIndex$iv$iv$iv6 = m$iv$iv$iv6.length - 2;
        int i$iv$iv$iv8 = 0;
        if (0 <= lastIndex$iv$iv$iv6) {
            while (true) {
                long slot$iv$iv$iv6 = m$iv$iv$iv6[i$iv$iv$iv8];
                String str6 = str5;
                MutableScatterMap<Object, Object> mutableScatterMap4 = mutableScatterMap3;
                int i8 = i7;
                if ((((~slot$iv$iv$iv6) << c) & slot$iv$iv$iv6 & j3) != j3) {
                    int bitCount$iv$iv$iv6 = 8 - ((~(i$iv$iv$iv8 - lastIndex$iv$iv$iv6)) >>> 31);
                    i$iv$iv$iv = i$iv$iv$iv8;
                    int j$iv$iv$iv9 = 0;
                    while (j$iv$iv$iv9 < bitCount$iv$iv$iv6) {
                        long value$iv$iv$iv$iv6 = slot$iv$iv$iv6 & j2;
                        int $i$f$isFull5 = value$iv$iv$iv$iv6 < j ? 1 : 0;
                        if ($i$f$isFull5 != 0) {
                            int index$iv$iv$iv4 = (i$iv$iv$iv << 3) + j$iv$iv$iv9;
                            Object obj2 = mutableScatterMap3.keys[index$iv$iv$iv4];
                            Object value$iv4 = mutableScatterMap3.values[index$iv$iv$iv4];
                            if (value$iv4 instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(value$iv4, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                MutableScatterSet this_$iv$iv7 = (MutableScatterSet) value$iv4;
                                j$iv$iv$iv2 = j$iv$iv$iv9;
                                Object[] elements$iv$iv7 = this_$iv$iv7.elements;
                                long[] m$iv$iv$iv7 = this_$iv$iv7.metadata;
                                int lastIndex$iv$iv$iv7 = m$iv$iv$iv7.length - 2;
                                int i$iv$iv$iv9 = 0;
                                if (0 <= lastIndex$iv$iv$iv7) {
                                    while (true) {
                                        long slot$iv$iv$iv7 = m$iv$iv$iv7[i$iv$iv$iv9];
                                        $i$f$removeIf4 = $i$f$removeIf6;
                                        this_$iv$iv$iv4 = this_$iv$iv$iv9;
                                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                        Object obj3 = value$iv4;
                                        if ((((~slot$iv$iv$iv7) << c) & slot$iv$iv$iv7 & j3) != j3) {
                                            int bitCount$iv$iv$iv7 = 8 - ((~(i$iv$iv$iv9 - lastIndex$iv$iv$iv7)) >>> 31);
                                            for (int j$iv$iv$iv10 = 0; j$iv$iv$iv10 < bitCount$iv$iv$iv7; j$iv$iv$iv10++) {
                                                long value$iv$iv$iv$iv7 = slot$iv$iv$iv7 & j2;
                                                int $i$f$isFull6 = value$iv$iv$iv$iv7 < j ? 1 : 0;
                                                if ($i$f$isFull6 != 0) {
                                                    int index$iv$iv$iv5 = (i$iv$iv$iv9 << 3) + j$iv$iv$iv10;
                                                    RecomposeScopeImpl scope3 = (RecomposeScopeImpl) elements$iv$iv7[index$iv$iv$iv5];
                                                    if (mutableScatterSet2.contains(scope3) || mutableScatterSet3.contains(scope3)) {
                                                        this_$iv$iv7.removeElementAt(index$iv$iv$iv5);
                                                    }
                                                }
                                                slot$iv$iv$iv7 >>= 8;
                                            }
                                            if (bitCount$iv$iv$iv7 != 8) {
                                                break;
                                            }
                                        }
                                        if (i$iv$iv$iv9 == lastIndex$iv$iv$iv7) {
                                            break;
                                        }
                                        i$iv$iv$iv9++;
                                        value$iv4 = obj3;
                                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                        $i$f$removeIf6 = $i$f$removeIf4;
                                        this_$iv$iv$iv9 = this_$iv$iv$iv4;
                                    }
                                } else {
                                    $i$f$removeIf4 = $i$f$removeIf6;
                                    this_$iv$iv$iv4 = this_$iv$iv$iv9;
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                }
                                zIsEmpty = this_$iv$iv7.isEmpty();
                                str3 = str6;
                            } else {
                                j$iv$iv$iv2 = j$iv$iv$iv9;
                                $i$f$removeIf4 = $i$f$removeIf6;
                                this_$iv$iv$iv4 = this_$iv$iv$iv9;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                str3 = str6;
                                Intrinsics.checkNotNull(value$iv4, str3);
                                RecomposeScopeImpl scope4 = (RecomposeScopeImpl) value$iv4;
                                zIsEmpty = mutableScatterSet2.contains(scope4) || mutableScatterSet3.contains(scope4);
                            }
                            if (zIsEmpty) {
                                mutableScatterMap3.removeValueAt(index$iv$iv$iv4);
                            }
                        } else {
                            j$iv$iv$iv2 = j$iv$iv$iv9;
                            $i$f$removeIf4 = $i$f$removeIf6;
                            this_$iv$iv$iv4 = this_$iv$iv$iv9;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            str3 = str6;
                        }
                        slot$iv$iv$iv6 >>= 8;
                        j$iv$iv$iv9 = j$iv$iv$iv2 + 1;
                        str6 = str3;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                        $i$f$removeIf6 = $i$f$removeIf4;
                        this_$iv$iv$iv9 = this_$iv$iv$iv4;
                    }
                    $i$f$removeIf3 = $i$f$removeIf6;
                    this_$iv$iv$iv3 = this_$iv$iv$iv9;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    str2 = str6;
                    if (bitCount$iv$iv$iv6 != 8) {
                        break;
                    }
                } else {
                    $i$f$removeIf3 = $i$f$removeIf6;
                    this_$iv$iv$iv3 = this_$iv$iv$iv9;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    i$iv$iv$iv = i$iv$iv$iv8;
                    str2 = str6;
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv6) {
                    break;
                }
                int i9 = i$iv$iv$iv + 1;
                str5 = str2;
                i$iv$iv$iv8 = i9;
                mutableScatterMap3 = mutableScatterMap4;
                i7 = i8;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                $i$f$removeIf6 = $i$f$removeIf3;
                this_$iv$iv$iv9 = this_$iv$iv$iv3;
            }
        }
        mutableScatterSet2.clear();
        cleanUpDerivedStateObservations();
    }

    private final void cleanUpDerivedStateObservations() {
        char c;
        long j;
        MutableScatterMap<Object, Object> mutableScatterMap;
        int i;
        int $i$f$removeIf;
        ScatterMap this_$iv$iv$iv;
        int $i$f$forEachIndexed;
        long[] m$iv$iv$iv;
        MutableScatterMap<Object, Object> mutableScatterMap2;
        int i2;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv$iv2;
        int $i$f$forEachIndexed2;
        long[] m$iv$iv$iv2;
        int j$iv$iv$iv;
        boolean zIsEmpty;
        int j$iv$iv$iv2;
        long[] m$iv$iv$iv3;
        MutableScatterMap<Object, Object> mutableScatterMap3 = this.derivedStates;
        int i3 = 0;
        int $i$f$removeIf3 = 0;
        MutableScatterMap<Object, Object> this_$iv$iv$iv3 = mutableScatterMap3;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv$iv4 = this_$iv$iv$iv3.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv4.length - 2;
        int i$iv$iv$iv = 0;
        int i4 = 8;
        if (0 <= lastIndex$iv$iv$iv) {
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv4[i$iv$iv$iv];
                c = 7;
                j = 255;
                if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    mutableScatterMap = mutableScatterMap3;
                    i = i3;
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv4;
                } else {
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv3 = 0;
                    while (j$iv$iv$iv3 < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        if (!(value$iv$iv$iv$iv < 128)) {
                            mutableScatterMap2 = mutableScatterMap3;
                            i2 = i3;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv$iv2 = this_$iv$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv$iv2 = m$iv$iv$iv4;
                            j$iv$iv$iv = j$iv$iv$iv3;
                        } else {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv3;
                            Object obj = mutableScatterMap3.keys[index$iv$iv$iv];
                            Object value$iv = mutableScatterMap3.values[index$iv$iv$iv];
                            int i5 = i4;
                            if (value$iv instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                MutableScatterSet set$iv = (MutableScatterSet) value$iv;
                                mutableScatterMap2 = mutableScatterMap3;
                                i2 = i3;
                                Object[] elements$iv$iv = set$iv.elements;
                                $i$f$removeIf2 = $i$f$removeIf3;
                                long[] m$iv$iv$iv5 = set$iv.metadata;
                                int lastIndex$iv$iv$iv2 = m$iv$iv$iv5.length - 2;
                                int i$iv$iv$iv2 = 0;
                                if (0 <= lastIndex$iv$iv$iv2) {
                                    while (true) {
                                        long slot$iv$iv$iv2 = m$iv$iv$iv5[i$iv$iv$iv2];
                                        this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                        j$iv$iv$iv = j$iv$iv$iv3;
                                        int index$iv$iv$iv2 = index$iv$iv$iv;
                                        if ((((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                                            m$iv$iv$iv2 = m$iv$iv$iv4;
                                        } else {
                                            int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv2)) >>> 31);
                                            int j$iv$iv$iv4 = 0;
                                            while (j$iv$iv$iv4 < bitCount$iv$iv$iv2) {
                                                long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                                if (!(value$iv$iv$iv$iv2 < 128)) {
                                                    j$iv$iv$iv2 = j$iv$iv$iv4;
                                                    m$iv$iv$iv3 = m$iv$iv$iv4;
                                                } else {
                                                    int index$iv$iv$iv3 = (i$iv$iv$iv2 << 3) + j$iv$iv$iv4;
                                                    j$iv$iv$iv2 = j$iv$iv$iv4;
                                                    DerivedState derivedState = (DerivedState) elements$iv$iv[index$iv$iv$iv3];
                                                    m$iv$iv$iv3 = m$iv$iv$iv4;
                                                    if (!ScopeMap.m4470containsimpl(this.observations, derivedState)) {
                                                        set$iv.removeElementAt(index$iv$iv$iv3);
                                                    }
                                                }
                                                slot$iv$iv$iv2 >>= i5;
                                                j$iv$iv$iv4 = j$iv$iv$iv2 + 1;
                                                m$iv$iv$iv4 = m$iv$iv$iv3;
                                            }
                                            m$iv$iv$iv2 = m$iv$iv$iv4;
                                            int j$iv$iv$iv5 = i5;
                                            if (bitCount$iv$iv$iv2 != j$iv$iv$iv5) {
                                                break;
                                            }
                                        }
                                        if (i$iv$iv$iv2 == lastIndex$iv$iv$iv2) {
                                            break;
                                        }
                                        i$iv$iv$iv2++;
                                        j$iv$iv$iv3 = j$iv$iv$iv;
                                        index$iv$iv$iv = index$iv$iv$iv2;
                                        this_$iv$iv$iv3 = this_$iv$iv$iv2;
                                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                        m$iv$iv$iv4 = m$iv$iv$iv2;
                                        i5 = 8;
                                    }
                                } else {
                                    this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                    m$iv$iv$iv2 = m$iv$iv$iv4;
                                    j$iv$iv$iv = j$iv$iv$iv3;
                                }
                                zIsEmpty = set$iv.isEmpty();
                            } else {
                                mutableScatterMap2 = mutableScatterMap3;
                                i2 = i3;
                                $i$f$removeIf2 = $i$f$removeIf3;
                                this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                m$iv$iv$iv2 = m$iv$iv$iv4;
                                j$iv$iv$iv = j$iv$iv$iv3;
                                Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                DerivedState derivedState2 = (DerivedState) value$iv;
                                zIsEmpty = !ScopeMap.m4470containsimpl(this.observations, derivedState2);
                            }
                            if (zIsEmpty) {
                                mutableScatterMap3.removeValueAt(index$iv$iv$iv);
                            }
                        }
                        slot$iv$iv$iv >>= 8;
                        j$iv$iv$iv3 = j$iv$iv$iv + 1;
                        i4 = 8;
                        i3 = i2;
                        mutableScatterMap3 = mutableScatterMap2;
                        $i$f$removeIf3 = $i$f$removeIf2;
                        this_$iv$iv$iv3 = this_$iv$iv$iv2;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                        m$iv$iv$iv4 = m$iv$iv$iv2;
                    }
                    mutableScatterMap = mutableScatterMap3;
                    i = i3;
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv4;
                    int $i$f$forEachIndexed4 = i4;
                    if (bitCount$iv$iv$iv != $i$f$forEachIndexed4) {
                        break;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    break;
                }
                i$iv$iv$iv++;
                i3 = i;
                mutableScatterMap3 = mutableScatterMap;
                $i$f$removeIf3 = $i$f$removeIf;
                this_$iv$iv$iv3 = this_$iv$iv$iv;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                m$iv$iv$iv4 = m$iv$iv$iv;
                i4 = 8;
            }
        } else {
            c = 7;
            j = 255;
        }
        if (this.conditionallyInvalidatedScopes.isNotEmpty()) {
            MutableScatterSet<RecomposeScopeImpl> mutableScatterSet = this.conditionallyInvalidatedScopes;
            Object[] elements$iv = mutableScatterSet.elements;
            MutableScatterSet<RecomposeScopeImpl> this_$iv$iv = mutableScatterSet;
            long[] m$iv$iv = this_$iv$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                if ((((~slot$iv$iv) << c) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                        long value$iv$iv$iv = slot$iv$iv & j;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            RecomposeScopeImpl scope = (RecomposeScopeImpl) elements$iv[index$iv$iv];
                            if (!scope.isConditional()) {
                                mutableScatterSet.removeElementAt(index$iv$iv);
                            }
                        }
                        slot$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv != 8) {
                        return;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                } else {
                    i$iv$iv++;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.RecomposeScopeOwner
    public void recordReadOf(Object value) {
        RecomposeScopeImpl scope;
        DerivedState.Record record;
        int i;
        if (!getAreChildrenComposing() && (scope = this.composer.getCurrentRecomposeScope$runtime()) != null) {
            int i2 = 0;
            int i3 = 1;
            scope.setUsed(true);
            boolean alreadyRead = scope.recordRead(value);
            CompositionObserver compositionObserverObserver = observer();
            if (compositionObserverObserver != null) {
                compositionObserverObserver.onReadInScope(scope, value);
            }
            if (!alreadyRead) {
                if (value instanceof StateObjectImpl) {
                    ReaderKind.Companion companion = ReaderKind.INSTANCE;
                    ((StateObjectImpl) value).m4559recordReadInh_f27i8$runtime(ReaderKind.m4546constructorimpl(1));
                }
                ScopeMap.m4463addimpl(this.observations, value, scope);
                if (value instanceof DerivedState) {
                    DerivedState.Record record2 = ((DerivedState) value).getCurrentRecord();
                    ScopeMap.m4478removeScopeimpl(this.derivedStates, value);
                    ObjectIntMap<StateObject> dependencies = record2.getDependencies();
                    Object[] k$iv = dependencies.keys;
                    long[] m$iv$iv = dependencies.metadata;
                    int lastIndex$iv$iv = m$iv$iv.length - 2;
                    int i$iv$iv = 0;
                    if (0 <= lastIndex$iv$iv) {
                        while (true) {
                            long slot$iv$iv = m$iv$iv[i$iv$iv];
                            int i4 = i3;
                            boolean alreadyRead2 = alreadyRead;
                            record = record2;
                            ObjectIntMap<StateObject> objectIntMap = dependencies;
                            if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                                i = i2;
                            } else {
                                int i5 = 8;
                                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                int j$iv$iv = 0;
                                while (j$iv$iv < bitCount$iv$iv) {
                                    long value$iv$iv$iv = slot$iv$iv & 255;
                                    if ((value$iv$iv$iv < 128 ? i4 : 0) != 0) {
                                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                        StateObject dependency = (StateObject) k$iv[index$iv$iv];
                                        if (dependency instanceof StateObjectImpl) {
                                            ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                                            int j$iv$iv2 = ReaderKind.m4546constructorimpl(i4);
                                            ((StateObjectImpl) dependency).m4559recordReadInh_f27i8$runtime(j$iv$iv2);
                                        }
                                        ScopeMap.m4463addimpl(this.derivedStates, dependency, value);
                                    } else {
                                        j$iv$iv = j$iv$iv;
                                    }
                                    slot$iv$iv >>= i5;
                                    j$iv$iv++;
                                    i5 = i5;
                                    i2 = i2;
                                }
                                i = i2;
                                if (bitCount$iv$iv != i5) {
                                    break;
                                }
                            }
                            if (i$iv$iv == lastIndex$iv$iv) {
                                break;
                            }
                            i$iv$iv++;
                            record2 = record;
                            dependencies = objectIntMap;
                            i3 = i4;
                            alreadyRead = alreadyRead2;
                            i2 = i;
                        }
                    } else {
                        record = record2;
                    }
                    scope.recordDerivedStateValue((DerivedState) value, record.getCurrentValue());
                }
            }
        }
    }

    private final void invalidateScopeOfLocked(Object value) {
        int i;
        int j$iv$iv$iv;
        MutableScatterMap<Object, Object> mutableScatterMap = this.observations;
        Object key$iv = value;
        int i2 = 0;
        Object value$iv = mutableScatterMap.get(key$iv);
        if (value$iv == null) {
            return;
        }
        if (!(value$iv instanceof MutableScatterSet)) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) value$iv;
            if (scope.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                ScopeMap.m4463addimpl(this.observationsProcessed, value, scope);
                return;
            }
            return;
        }
        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
        Object[] elements$iv$iv = this_$iv$iv.elements;
        long[] m$iv$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            MutableScatterMap<Object, Object> mutableScatterMap2 = mutableScatterMap;
            Object key$iv2 = key$iv;
            int i3 = i2;
            Object value$iv2 = value$iv;
            if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                int i4 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv2 = 0;
                while (j$iv$iv$iv2 < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        i = i4;
                        j$iv$iv$iv = j$iv$iv$iv2;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv2;
                        i = i4;
                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) elements$iv$iv[index$iv$iv$iv];
                        j$iv$iv$iv = j$iv$iv$iv2;
                        if (scope2.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                            ScopeMap.m4463addimpl(this.observationsProcessed, value, scope2);
                        }
                    }
                    slot$iv$iv$iv >>= i;
                    j$iv$iv$iv2 = j$iv$iv$iv + 1;
                    i4 = i;
                }
                if (bitCount$iv$iv$iv != i4) {
                    return;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return;
            }
            i$iv$iv$iv++;
            i2 = i3;
            value$iv = value$iv2;
            mutableScatterMap = mutableScatterMap2;
            key$iv = key$iv2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0091 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:28:0x0093 A[Catch: all -> 0x00c7, LOOP:0: B:11:0x002d->B:28:0x0093, LOOP_END, TryCatch #0 {all -> 0x00c7, blocks: (B:22:0x0073, B:24:0x0086, B:34:0x00c1, B:28:0x0093, B:32:0x00a9), top: B:43:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x00a7 A[EDGE_INSN: B:48:0x00a7->B:31:0x00a7 BREAK  A[LOOP:0: B:11:0x002d->B:28:0x0093], SYNTHETIC] */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordWriteOf(Object value) throws Throwable {
        Object lock$iv = this.lock;
        int $i$f$synchronized = 0;
        synchronized (lock$iv) {
            try {
                invalidateScopeOfLocked(value);
                MutableScatterMap<Object, Object> mutableScatterMap = this.derivedStates;
                Object key$iv = value;
                int i = 0;
                Object value$iv = mutableScatterMap.get(key$iv);
                try {
                    if (value$iv != null) {
                        if (!(value$iv instanceof MutableScatterSet)) {
                            DerivedState it = (DerivedState) value$iv;
                            invalidateScopeOfLocked(it);
                        } else {
                            ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
                            Object[] elements$iv$iv = this_$iv$iv.elements;
                            long[] m$iv$iv$iv = this_$iv$iv.metadata;
                            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
                            int i$iv$iv$iv = 0;
                            if (0 <= lastIndex$iv$iv$iv) {
                                while (true) {
                                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                                    int $i$f$synchronized2 = $i$f$synchronized;
                                    MutableScatterMap<Object, Object> mutableScatterMap2 = mutableScatterMap;
                                    Object key$iv2 = key$iv;
                                    int i2 = i;
                                    if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                                        if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                                            break;
                                            break;
                                        }
                                        i$iv$iv$iv++;
                                        key$iv = key$iv2;
                                        i = i2;
                                        $i$f$synchronized = $i$f$synchronized2;
                                        mutableScatterMap = mutableScatterMap2;
                                    } else {
                                        int i3 = 8;
                                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                                        int j$iv$iv$iv = 0;
                                        while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                                            long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                            if (value$iv$iv$iv$iv < 128) {
                                                int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                                DerivedState it2 = (DerivedState) elements$iv$iv[index$iv$iv$iv];
                                                invalidateScopeOfLocked(it2);
                                            }
                                            slot$iv$iv$iv >>= i3;
                                            j$iv$iv$iv++;
                                            i3 = i3;
                                        }
                                        if (bitCount$iv$iv$iv != i3) {
                                            break;
                                        }
                                        if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                                            break;
                                        }
                                        i$iv$iv$iv++;
                                        key$iv = key$iv2;
                                        i = i2;
                                        $i$f$synchronized = $i$f$synchronized2;
                                        mutableScatterMap = mutableScatterMap2;
                                    }
                                }
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean recompose() throws Throwable {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                PausedCompositionImpl pendingPausedComposition = this.pendingPausedComposition;
                if (pendingPausedComposition != null) {
                    try {
                        if (!pendingPausedComposition.isRecomposing$runtime()) {
                            pendingPausedComposition.markIncomplete$runtime();
                            pendingPausedComposition.getPausableApplier$runtime().markRecomposePending();
                            return false;
                        }
                    } catch (Throwable th) {
                        e$iv = th;
                        throw e$iv;
                    }
                }
                drainPendingModificationsForCompositionLocked();
                try {
                    MutableScatterMap<Object, Object> mutableScatterMapM4310takeInvalidationsafanTW4 = m4310takeInvalidationsafanTW4();
                    try {
                        try {
                            boolean shouldDrain = this.composer.m4307recomposeaFTiNEg$runtime(mutableScatterMapM4310takeInvalidationsafanTW4, this.shouldPause);
                            if (!shouldDrain) {
                                drainPendingModificationsLocked();
                            }
                            return shouldDrain;
                        } catch (Throwable th2) {
                            e$iv = th2;
                            try {
                                this.invalidations = mutableScatterMapM4310takeInvalidationsafanTW4;
                                throw e$iv;
                            } catch (Throwable th3) {
                                e$iv = th3;
                                if (0 == 0) {
                                    try {
                                        if (!this.abandonSet.isEmpty()) {
                                            RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                                            Set<RememberObserver> set = this.abandonSet;
                                            CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                                            try {
                                                this_$iv$iv$iv.prepare(set, traceContext$iv$iv$iv);
                                                this_$iv$iv$iv.dispatchAbandons();
                                            } finally {
                                                this_$iv$iv$iv.clear();
                                            }
                                        }
                                    } catch (Throwable e$iv) {
                                        try {
                                            abandonChanges();
                                            throw e$iv;
                                        } catch (Throwable th4) {
                                            e$iv = th4;
                                            throw e$iv;
                                        }
                                    }
                                }
                                throw e$iv;
                            }
                        }
                    } catch (Throwable th5) {
                        e$iv = th5;
                    }
                } catch (Throwable th6) {
                    e$iv = th6;
                }
            } catch (Throwable th7) {
                e$iv = th7;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void insertMovableContent(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        boolean value$iv;
        int index$iv$iv = 0;
        int size = references.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = references.get(index$iv$iv);
                if (!Intrinsics.areEqual(((Pair) item$iv$iv).getFirst().getComposition(), this)) {
                    value$iv = false;
                    break;
                }
                index$iv$iv++;
            } else {
                value$iv = true;
                break;
            }
        }
        boolean value$iv$iv = value$iv;
        if (!value$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        try {
            this.composer.insertMovableContentReferences(references);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            if (0 == 0) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                        Set<RememberObserver> set = this.abandonSet;
                        CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                        try {
                            this_$iv$iv$iv.prepare(set, traceContext$iv$iv$iv);
                            this_$iv$iv$iv.dispatchAbandons();
                        } finally {
                            this_$iv$iv$iv.clear();
                        }
                    }
                } catch (Throwable e$iv) {
                    abandonChanges();
                    throw e$iv;
                }
            }
            throw th;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void disposeUnusedMovableContent(MovableContentState state) {
        RememberEventDispatcher this_$iv = this.rememberManager;
        Set<RememberObserver> set = this.abandonSet;
        CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
        try {
            this_$iv.prepare(set, traceContext$iv);
            SlotTable slotTable = state.getSlotTable();
            SlotWriter writer$iv = slotTable.openWriter();
            try {
                ComposerKt.removeCurrentGroup(writer$iv, this.rememberManager);
                Unit unit = Unit.INSTANCE;
                writer$iv.close(true);
                this_$iv.dispatchRememberObservers();
                this_$iv.clear();
            } catch (Throwable th) {
                writer$iv.close(false);
                throw th;
            }
        } catch (Throwable th2) {
            this_$iv.clear();
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x023b  */
    /* JADX WARN: Code duplicated, block: B:102:0x0247 A[Catch: all -> 0x027d, LOOP:0: B:51:0x00ea->B:102:0x0247, LOOP_END, TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:103:0x025d  */
    /* JADX WARN: Code duplicated, block: B:113:0x028c  */
    /* JADX WARN: Code duplicated, block: B:169:0x026e A[EDGE_INSN: B:169:0x026e->B:105:0x026e BREAK  A[LOOP:0: B:51:0x00ea->B:102:0x0247], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:170:0x026e A[EDGE_INSN: B:170:0x026e->B:105:0x026e BREAK  A[LOOP:0: B:51:0x00ea->B:102:0x0247], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:174:0x01da A[EDGE_INSN: B:174:0x01da->B:86:0x01da BREAK  A[LOOP:2: B:66:0x0170->B:83:0x01c4], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:175:0x01da A[EDGE_INSN: B:175:0x01da->B:86:0x01da BREAK  A[LOOP:2: B:66:0x0170->B:83:0x01c4], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:177:0x01b9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:179:0x01b9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:35:0x0077 A[Catch: all -> 0x02c3, TRY_ENTER, TryCatch #3 {all -> 0x02c3, blocks: (B:28:0x0063, B:36:0x0079, B:35:0x0077), top: B:154:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:47:0x00c9 A[Catch: all -> 0x02d0, TRY_LEAVE, TryCatch #1 {all -> 0x02d0, blocks: (B:3:0x0012, B:12:0x0031, B:14:0x0035, B:16:0x003b, B:18:0x0040, B:20:0x0045, B:22:0x004b, B:26:0x0056, B:45:0x00b5, B:47:0x00c9, B:111:0x0286, B:112:0x028b, B:106:0x0277, B:136:0x02ca, B:137:0x02cf, B:17:0x003e), top: B:151:0x0012, outer: #6 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x00ea A[Catch: all -> 0x027f, LOOP:0: B:51:0x00ea->B:102:0x0247, LOOP_START, PHI: r0 r2 r3 r5 r7 r8 r9 r11 r12
  0x00ea: PHI (r0v51 int) = (r0v45 int), (r0v52 int) binds: [B:50:0x00e8, B:102:0x0247] A[DONT_GENERATE, DONT_INLINE]
  0x00ea: PHI (r2v11 androidx.compose.runtime.Applier<?>) = (r2v7 androidx.compose.runtime.Applier<?>), (r2v14 androidx.compose.runtime.Applier<?>) binds: [B:50:0x00e8, B:102:0x0247] A[DONT_GENERATE, DONT_INLINE]
  0x00ea: PHI (r3v10 'sectionName$iv' java.lang.String) = (r3v8 'sectionName$iv' java.lang.String), (r3v11 'sectionName$iv' java.lang.String) binds: [B:50:0x00e8, B:102:0x0247] A[DONT_GENERATE, DONT_INLINE]
  0x00ea: PHI (r5v3 '$i$f$trace' int) = (r5v1 '$i$f$trace' int), (r5v4 '$i$f$trace' int) binds: [B:50:0x00e8, B:102:0x0247] A[DONT_GENERATE, DONT_INLINE]
  0x00ea: PHI (r7v3 'i$iv$iv$iv' int) = (r7v2 'i$iv$iv$iv' int), (r7v4 'i$iv$iv$iv' int) binds: [B:50:0x00e8, B:102:0x0247] A[DONT_GENERATE, DONT_INLINE]
  0x00ea: PHI (r8v2 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>) = 
  (r8v1 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
  (r8v3 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
 binds: [B:50:0x00e8, B:102:0x0247] A[DONT_GENERATE, DONT_INLINE]
  0x00ea: PHI (r9v2 int) = (r9v1 int), (r9v3 int) binds: [B:50:0x00e8, B:102:0x0247] A[DONT_GENERATE, DONT_INLINE]
  0x00ea: PHI (r11v2 '$i$f$removeIf' int) = (r11v1 '$i$f$removeIf' int), (r11v3 '$i$f$removeIf' int) binds: [B:50:0x00e8, B:102:0x0247] A[DONT_GENERATE, DONT_INLINE]
  0x00ea: PHI (r12v3 'this_$iv$iv$iv' androidx.collection.ScatterMap) = 
  (r12v2 'this_$iv$iv$iv' androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
  (r12v4 'this_$iv$iv$iv' androidx.collection.ScatterMap)
 binds: [B:50:0x00e8, B:102:0x0247] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #10 {all -> 0x027f, blocks: (B:49:0x00d7, B:51:0x00ea), top: B:167:0x00d7 }] */
    /* JADX WARN: Code duplicated, block: B:54:0x0109  */
    /* JADX WARN: Code duplicated, block: B:56:0x0115  */
    /* JADX WARN: Code duplicated, block: B:58:0x0122  */
    /* JADX WARN: Code duplicated, block: B:59:0x0125  */
    /* JADX WARN: Code duplicated, block: B:61:0x0128  */
    /* JADX WARN: Code duplicated, block: B:64:0x0142 A[Catch: all -> 0x027d, TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x0170 A[Catch: all -> 0x027d, LOOP:2: B:66:0x0170->B:83:0x01c4, LOOP_START, PHI: r5 r8 r9 r11 r12 r30
  0x0170: PHI (r5v15 'i$iv$iv$iv' int) = (r5v14 'i$iv$iv$iv' int), (r5v16 'i$iv$iv$iv' int) binds: [B:65:0x016e, B:83:0x01c4] A[DONT_GENERATE, DONT_INLINE]
  0x0170: PHI (r8v6 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>) = 
  (r8v4 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
  (r8v9 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
 binds: [B:65:0x016e, B:83:0x01c4] A[DONT_GENERATE, DONT_INLINE]
  0x0170: PHI (r9v8 int) = (r9v4 int), (r9v9 int) binds: [B:65:0x016e, B:83:0x01c4] A[DONT_GENERATE, DONT_INLINE]
  0x0170: PHI (r11v6 '$i$f$removeIf' int) = (r11v4 '$i$f$removeIf' int), (r11v11 '$i$f$removeIf' int) binds: [B:65:0x016e, B:83:0x01c4] A[DONT_GENERATE, DONT_INLINE]
  0x0170: PHI (r12v7 'this_$iv$iv$iv' androidx.collection.ScatterMap) = (r12v5 'this_$iv$iv$iv' androidx.collection.ScatterMap), (r12v8 'this_$iv$iv$iv' androidx.collection.ScatterMap) binds: [B:65:0x016e, B:83:0x01c4] A[DONT_GENERATE, DONT_INLINE]
  0x0170: PHI (r30v1 int) = (r30v0 int), (r30v2 int) binds: [B:65:0x016e, B:83:0x01c4] A[DONT_GENERATE, DONT_INLINE], TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x018a A[Catch: all -> 0x027d, TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x0194 A[Catch: all -> 0x027d, TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:72:0x019c  */
    /* JADX WARN: Code duplicated, block: B:73:0x019f  */
    /* JADX WARN: Code duplicated, block: B:75:0x01a2 A[Catch: all -> 0x027d, TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x01b4 A[Catch: all -> 0x027d, TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x01c2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:83:0x01c4 A[Catch: all -> 0x027d, LOOP:2: B:66:0x0170->B:83:0x01c4, LOOP_END, TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:84:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:87:0x01e0 A[Catch: all -> 0x027d, TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:89:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:92:0x0202 A[Catch: all -> 0x027d, TryCatch #8 {all -> 0x027d, blocks: (B:62:0x0131, B:64:0x0142, B:66:0x0170, B:68:0x018a, B:70:0x0194, B:75:0x01a2, B:77:0x01b4, B:79:0x01b9, B:86:0x01da, B:92:0x0202, B:96:0x0218, B:83:0x01c4, B:87:0x01e0, B:102:0x0247, B:105:0x026e), top: B:163:0x0131 }] */
    /* JADX WARN: Code duplicated, block: B:93:0x0208  */
    /* JADX WARN: Code duplicated, block: B:95:0x020c  */
    /* JADX WARN: Code duplicated, block: B:99:0x023a  */
    private final void applyChangesInLocked(ChangeList changes) {
        RememberEventDispatcher rememberManager$runtime;
        SlotWriter writer$iv;
        String sectionName$iv;
        int $i$f$trace;
        Object token$iv;
        int i;
        MutableScatterMap<Object, Object> mutableScatterMap;
        int i2;
        int $i$f$removeIf;
        MutableScatterMap<Object, Object> this_$iv$iv$iv;
        long[] m$iv$iv$iv;
        int lastIndex$iv$iv$iv;
        int i$iv$iv$iv;
        long slot$iv$iv$iv;
        Applier<?> applier;
        String sectionName$iv2;
        int $i$f$trace2;
        int i3;
        MutableScatterMap<Object, Object> mutableScatterMap2;
        int i4;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv$iv2;
        int i5;
        int bitCount$iv$iv$iv;
        int j$iv$iv$iv;
        long value$iv$iv$iv$iv;
        boolean zIsEmpty;
        int $i$f$isFull;
        int j$iv$iv$iv2;
        MutableScatterMap<Object, Object> mutableScatterMap3;
        int i6;
        int $i$f$removeIf3;
        ScatterMap this_$iv$iv$iv3;
        int index$iv$iv$iv;
        int i7;
        Object value$iv;
        RecomposeScopeImpl scope;
        MutableScatterSet this_$iv$iv;
        Object[] elements$iv$iv;
        long[] m$iv$iv$iv2;
        int lastIndex$iv$iv$iv2;
        int i$iv$iv$iv2;
        long slot$iv$iv$iv2;
        int bitCount$iv$iv$iv2;
        int j$iv$iv$iv3;
        int j$iv$iv$iv4;
        long value$iv$iv$iv$iv2;
        boolean z;
        int index$iv$iv$iv2;
        RecomposeScopeImpl scope2;
        RecordingApplier<Object> pausableApplier$runtime;
        this.rememberManager.prepare(this.abandonSet, this.composer.getErrorContext$runtime());
        try {
            try {
                if (changes.isEmpty()) {
                    if (this.lateChanges.isEmpty() && this.pendingPausedComposition == null) {
                        this.rememberManager.dispatchAbandons();
                    }
                    this.rememberManager.clear();
                    return;
                }
                PausedCompositionImpl pausedCompositionImpl = this.pendingPausedComposition;
                Applier<?> applier2 = (pausedCompositionImpl == null || (pausableApplier$runtime = pausedCompositionImpl.getPausableApplier$runtime()) == null) ? this.applier : pausableApplier$runtime;
                PausedCompositionImpl pausedCompositionImpl2 = this.pendingPausedComposition;
                String traceName = Intrinsics.areEqual(applier2, pausedCompositionImpl2 != null ? pausedCompositionImpl2.getPausableApplier$runtime() : null) ? "Compose:recordChanges" : "Compose:applyChanges";
                Object token$iv2 = Trace.INSTANCE.beginSection(traceName);
                try {
                    PausedCompositionImpl pausedCompositionImpl3 = this.pendingPausedComposition;
                    if (pausedCompositionImpl3 == null) {
                        rememberManager$runtime = this.rememberManager;
                        RememberEventDispatcher rememberManager = rememberManager$runtime;
                        applier2.onBeginChanges();
                        SlotTable this_$iv = this.slotTable;
                        writer$iv = this_$iv.openWriter();
                        changes.executeAndFlushAllPendingChanges(applier2, writer$iv, rememberManager, this.composer.getErrorContext$runtime());
                        Unit unit = Unit.INSTANCE;
                        writer$iv.close(true);
                        applier2.onEndChanges();
                        Unit unit2 = Unit.INSTANCE;
                        Trace.INSTANCE.endSection(token$iv2);
                        this.rememberManager.dispatchRememberObservers();
                        this.rememberManager.dispatchSideEffects();
                        if (this.pendingInvalidScopes) {
                            sectionName$iv = "Compose:unobserve";
                            $i$f$trace = 0;
                            token$iv = Trace.INSTANCE.beginSection(sectionName$iv);
                            i = 0;
                            this.pendingInvalidScopes = false;
                            mutableScatterMap = this.observations;
                            i2 = 0;
                            $i$f$removeIf = 0;
                            this_$iv$iv$iv = mutableScatterMap;
                            m$iv$iv$iv = this_$iv$iv$iv.metadata;
                            lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
                            i$iv$iv$iv = 0;
                            if (0 <= lastIndex$iv$iv$iv) {
                                while (true) {
                                    slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                                    applier = applier2;
                                    sectionName$iv2 = sectionName$iv;
                                    $i$f$trace2 = $i$f$trace;
                                    if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                                        i5 = 8;
                                        bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                                        j$iv$iv$iv = 0;
                                        while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                                            value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                            zIsEmpty = true;
                                            if (value$iv$iv$iv$iv < 128) {
                                                $i$f$isFull = 1;
                                            } else {
                                                $i$f$isFull = 0;
                                            }
                                            if ($i$f$isFull != 0) {
                                                index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                                i7 = i5;
                                                Object obj = mutableScatterMap.keys[index$iv$iv$iv];
                                                value$iv = mutableScatterMap.values[index$iv$iv$iv];
                                                if (value$iv instanceof MutableScatterSet) {
                                                    Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                                    this_$iv$iv = (MutableScatterSet) value$iv;
                                                    j$iv$iv$iv2 = j$iv$iv$iv;
                                                    elements$iv$iv = this_$iv$iv.elements;
                                                    m$iv$iv$iv2 = this_$iv$iv.metadata;
                                                    lastIndex$iv$iv$iv2 = m$iv$iv$iv2.length - 2;
                                                    i$iv$iv$iv2 = 0;
                                                    if (0 <= lastIndex$iv$iv$iv2) {
                                                        while (true) {
                                                            slot$iv$iv$iv2 = m$iv$iv$iv2[i$iv$iv$iv2];
                                                            mutableScatterMap3 = mutableScatterMap;
                                                            i6 = i2;
                                                            $i$f$removeIf3 = $i$f$removeIf;
                                                            this_$iv$iv$iv3 = this_$iv$iv$iv;
                                                            if ((((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                if (i$iv$iv$iv2 != lastIndex$iv$iv$iv2) {
                                                                    break;
                                                                    break;
                                                                }
                                                                i$iv$iv$iv2++;
                                                                $i$f$removeIf = $i$f$removeIf3;
                                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                                mutableScatterMap = mutableScatterMap3;
                                                                i2 = i6;
                                                                i7 = 8;
                                                            } else {
                                                                bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv2)) >>> 31);
                                                                for (j$iv$iv$iv3 = 0; j$iv$iv$iv3 < bitCount$iv$iv$iv2; j$iv$iv$iv3++) {
                                                                    value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                                                    if (value$iv$iv$iv$iv2 < 128) {
                                                                        z = true;
                                                                    } else {
                                                                        z = false;
                                                                    }
                                                                    if (z) {
                                                                        index$iv$iv$iv2 = (i$iv$iv$iv2 << 3) + j$iv$iv$iv3;
                                                                        scope2 = (RecomposeScopeImpl) elements$iv$iv[index$iv$iv$iv2];
                                                                        if (!scope2.getValid()) {
                                                                            this_$iv$iv.removeElementAt(index$iv$iv$iv2);
                                                                        }
                                                                    }
                                                                    slot$iv$iv$iv2 >>= i7;
                                                                }
                                                                j$iv$iv$iv4 = i7;
                                                                if (bitCount$iv$iv$iv2 == j$iv$iv$iv4) {
                                                                    break;
                                                                    break;
                                                                }
                                                                if (i$iv$iv$iv2 != lastIndex$iv$iv$iv2) {
                                                                    break;
                                                                    break;
                                                                }
                                                                i$iv$iv$iv2++;
                                                                $i$f$removeIf = $i$f$removeIf3;
                                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                                mutableScatterMap = mutableScatterMap3;
                                                                i2 = i6;
                                                                i7 = 8;
                                                            }
                                                        }
                                                    } else {
                                                        mutableScatterMap3 = mutableScatterMap;
                                                        i6 = i2;
                                                        $i$f$removeIf3 = $i$f$removeIf;
                                                        this_$iv$iv$iv3 = this_$iv$iv$iv;
                                                    }
                                                    zIsEmpty = this_$iv$iv.isEmpty();
                                                } else {
                                                    j$iv$iv$iv2 = j$iv$iv$iv;
                                                    mutableScatterMap3 = mutableScatterMap;
                                                    i6 = i2;
                                                    $i$f$removeIf3 = $i$f$removeIf;
                                                    this_$iv$iv$iv3 = this_$iv$iv$iv;
                                                    Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                                    scope = (RecomposeScopeImpl) value$iv;
                                                    if (scope.getValid()) {
                                                        zIsEmpty = false;
                                                    }
                                                }
                                                if (zIsEmpty) {
                                                    mutableScatterMap.removeValueAt(index$iv$iv$iv);
                                                }
                                            } else {
                                                j$iv$iv$iv2 = j$iv$iv$iv;
                                                mutableScatterMap3 = mutableScatterMap;
                                                i6 = i2;
                                                $i$f$removeIf3 = $i$f$removeIf;
                                                this_$iv$iv$iv3 = this_$iv$iv$iv;
                                            }
                                            slot$iv$iv$iv >>= 8;
                                            j$iv$iv$iv = j$iv$iv$iv2 + 1;
                                            i5 = 8;
                                            i = i;
                                            $i$f$removeIf = $i$f$removeIf3;
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            mutableScatterMap = mutableScatterMap3;
                                            i2 = i6;
                                        }
                                        i3 = i;
                                        mutableScatterMap2 = mutableScatterMap;
                                        i4 = i2;
                                        $i$f$removeIf2 = $i$f$removeIf;
                                        this_$iv$iv$iv2 = this_$iv$iv$iv;
                                        if (bitCount$iv$iv$iv == i5) {
                                            break;
                                            break;
                                        }
                                    } else {
                                        i3 = i;
                                        mutableScatterMap2 = mutableScatterMap;
                                        i4 = i2;
                                        $i$f$removeIf2 = $i$f$removeIf;
                                        this_$iv$iv$iv2 = this_$iv$iv$iv;
                                    }
                                    if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                                        break;
                                        break;
                                    }
                                    i$iv$iv$iv++;
                                    $i$f$trace = $i$f$trace2;
                                    applier2 = applier;
                                    sectionName$iv = sectionName$iv2;
                                    i = i3;
                                    $i$f$removeIf = $i$f$removeIf2;
                                    this_$iv$iv$iv = this_$iv$iv$iv2;
                                    mutableScatterMap = mutableScatterMap2;
                                    i2 = i4;
                                }
                            }
                            cleanUpDerivedStateObservations();
                            Unit unit3 = Unit.INSTANCE;
                            Trace.INSTANCE.endSection(token$iv);
                        }
                        if (this.lateChanges.isEmpty()) {
                            this.rememberManager.dispatchAbandons();
                        }
                        this.rememberManager.clear();
                        return;
                    }
                    try {
                        rememberManager$runtime = pausedCompositionImpl3.getRememberManager();
                        if (rememberManager$runtime == null) {
                            rememberManager$runtime = this.rememberManager;
                        }
                        RememberEventDispatcher rememberManager2 = rememberManager$runtime;
                        applier2.onBeginChanges();
                        SlotTable this_$iv2 = this.slotTable;
                        writer$iv = this_$iv2.openWriter();
                        try {
                            try {
                                try {
                                    changes.executeAndFlushAllPendingChanges(applier2, writer$iv, rememberManager2, this.composer.getErrorContext$runtime());
                                    Unit unit4 = Unit.INSTANCE;
                                    try {
                                        writer$iv.close(true);
                                        applier2.onEndChanges();
                                        Unit unit5 = Unit.INSTANCE;
                                        Trace.INSTANCE.endSection(token$iv2);
                                        this.rememberManager.dispatchRememberObservers();
                                        this.rememberManager.dispatchSideEffects();
                                        if (this.pendingInvalidScopes) {
                                            sectionName$iv = "Compose:unobserve";
                                            $i$f$trace = 0;
                                            token$iv = Trace.INSTANCE.beginSection(sectionName$iv);
                                            i = 0;
                                            try {
                                                this.pendingInvalidScopes = false;
                                                mutableScatterMap = this.observations;
                                                i2 = 0;
                                                $i$f$removeIf = 0;
                                                this_$iv$iv$iv = mutableScatterMap;
                                                m$iv$iv$iv = this_$iv$iv$iv.metadata;
                                                lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
                                                i$iv$iv$iv = 0;
                                                if (0 <= lastIndex$iv$iv$iv) {
                                                    while (true) {
                                                        slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                                                        applier = applier2;
                                                        sectionName$iv2 = sectionName$iv;
                                                        $i$f$trace2 = $i$f$trace;
                                                        if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                                                            i5 = 8;
                                                            bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                                                            j$iv$iv$iv = 0;
                                                            while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                                                                value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                                                zIsEmpty = true;
                                                                if (value$iv$iv$iv$iv < 128) {
                                                                    $i$f$isFull = 1;
                                                                } else {
                                                                    $i$f$isFull = 0;
                                                                }
                                                                if ($i$f$isFull != 0) {
                                                                    index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                                                    i7 = i5;
                                                                    try {
                                                                        Object obj2 = mutableScatterMap.keys[index$iv$iv$iv];
                                                                        value$iv = mutableScatterMap.values[index$iv$iv$iv];
                                                                        if (value$iv instanceof MutableScatterSet) {
                                                                            Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap>");
                                                                            this_$iv$iv = (MutableScatterSet) value$iv;
                                                                            j$iv$iv$iv2 = j$iv$iv$iv;
                                                                            elements$iv$iv = this_$iv$iv.elements;
                                                                            m$iv$iv$iv2 = this_$iv$iv.metadata;
                                                                            lastIndex$iv$iv$iv2 = m$iv$iv$iv2.length - 2;
                                                                            i$iv$iv$iv2 = 0;
                                                                            if (0 <= lastIndex$iv$iv$iv2) {
                                                                                while (true) {
                                                                                    slot$iv$iv$iv2 = m$iv$iv$iv2[i$iv$iv$iv2];
                                                                                    mutableScatterMap3 = mutableScatterMap;
                                                                                    i6 = i2;
                                                                                    $i$f$removeIf3 = $i$f$removeIf;
                                                                                    this_$iv$iv$iv3 = this_$iv$iv$iv;
                                                                                    if ((((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                                                        if (i$iv$iv$iv2 != lastIndex$iv$iv$iv2) {
                                                                                            break;
                                                                                            break;
                                                                                        }
                                                                                        i$iv$iv$iv2++;
                                                                                        $i$f$removeIf = $i$f$removeIf3;
                                                                                        this_$iv$iv$iv = this_$iv$iv$iv3;
                                                                                        mutableScatterMap = mutableScatterMap3;
                                                                                        i2 = i6;
                                                                                        i7 = 8;
                                                                                    } else {
                                                                                        bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv2)) >>> 31);
                                                                                        while (j$iv$iv$iv3 < bitCount$iv$iv$iv2) {
                                                                                            value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                                                                            if (value$iv$iv$iv$iv2 < 128) {
                                                                                                z = true;
                                                                                            } else {
                                                                                                z = false;
                                                                                            }
                                                                                            if (z) {
                                                                                                index$iv$iv$iv2 = (i$iv$iv$iv2 << 3) + j$iv$iv$iv3;
                                                                                                scope2 = (RecomposeScopeImpl) elements$iv$iv[index$iv$iv$iv2];
                                                                                                if (!scope2.getValid()) {
                                                                                                    this_$iv$iv.removeElementAt(index$iv$iv$iv2);
                                                                                                }
                                                                                            }
                                                                                            slot$iv$iv$iv2 >>= i7;
                                                                                        }
                                                                                        j$iv$iv$iv4 = i7;
                                                                                        if (bitCount$iv$iv$iv2 == j$iv$iv$iv4) {
                                                                                            break;
                                                                                        }
                                                                                        if (i$iv$iv$iv2 != lastIndex$iv$iv$iv2) {
                                                                                            break;
                                                                                        }
                                                                                        i$iv$iv$iv2++;
                                                                                        $i$f$removeIf = $i$f$removeIf3;
                                                                                        this_$iv$iv$iv = this_$iv$iv$iv3;
                                                                                        mutableScatterMap = mutableScatterMap3;
                                                                                        i2 = i6;
                                                                                        i7 = 8;
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                mutableScatterMap3 = mutableScatterMap;
                                                                                i6 = i2;
                                                                                $i$f$removeIf3 = $i$f$removeIf;
                                                                                this_$iv$iv$iv3 = this_$iv$iv$iv;
                                                                            }
                                                                            zIsEmpty = this_$iv$iv.isEmpty();
                                                                        } else {
                                                                            j$iv$iv$iv2 = j$iv$iv$iv;
                                                                            mutableScatterMap3 = mutableScatterMap;
                                                                            i6 = i2;
                                                                            $i$f$removeIf3 = $i$f$removeIf;
                                                                            this_$iv$iv$iv3 = this_$iv$iv$iv;
                                                                            Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap");
                                                                            scope = (RecomposeScopeImpl) value$iv;
                                                                            if (scope.getValid()) {
                                                                                zIsEmpty = false;
                                                                            }
                                                                        }
                                                                        if (zIsEmpty) {
                                                                            mutableScatterMap.removeValueAt(index$iv$iv$iv);
                                                                        }
                                                                    } catch (Throwable th) {
                                                                        th = th;
                                                                        Trace.INSTANCE.endSection(token$iv);
                                                                        throw th;
                                                                    }
                                                                } else {
                                                                    j$iv$iv$iv2 = j$iv$iv$iv;
                                                                    mutableScatterMap3 = mutableScatterMap;
                                                                    i6 = i2;
                                                                    $i$f$removeIf3 = $i$f$removeIf;
                                                                    this_$iv$iv$iv3 = this_$iv$iv$iv;
                                                                }
                                                                slot$iv$iv$iv >>= 8;
                                                                j$iv$iv$iv = j$iv$iv$iv2 + 1;
                                                                i5 = 8;
                                                                i = i;
                                                                $i$f$removeIf = $i$f$removeIf3;
                                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                                mutableScatterMap = mutableScatterMap3;
                                                                i2 = i6;
                                                            }
                                                            i3 = i;
                                                            mutableScatterMap2 = mutableScatterMap;
                                                            i4 = i2;
                                                            $i$f$removeIf2 = $i$f$removeIf;
                                                            this_$iv$iv$iv2 = this_$iv$iv$iv;
                                                            if (bitCount$iv$iv$iv == i5) {
                                                                break;
                                                            }
                                                        } else {
                                                            i3 = i;
                                                            mutableScatterMap2 = mutableScatterMap;
                                                            i4 = i2;
                                                            $i$f$removeIf2 = $i$f$removeIf;
                                                            this_$iv$iv$iv2 = this_$iv$iv$iv;
                                                        }
                                                        if (i$iv$iv$iv != lastIndex$iv$iv$iv) {
                                                            break;
                                                        }
                                                        i$iv$iv$iv++;
                                                        $i$f$trace = $i$f$trace2;
                                                        applier2 = applier;
                                                        sectionName$iv = sectionName$iv2;
                                                        i = i3;
                                                        $i$f$removeIf = $i$f$removeIf2;
                                                        this_$iv$iv$iv = this_$iv$iv$iv2;
                                                        mutableScatterMap = mutableScatterMap2;
                                                        i2 = i4;
                                                    }
                                                }
                                                cleanUpDerivedStateObservations();
                                                Unit unit6 = Unit.INSTANCE;
                                                Trace.INSTANCE.endSection(token$iv);
                                            } catch (Throwable th2) {
                                                th = th2;
                                            }
                                        }
                                        if (this.lateChanges.isEmpty() && this.pendingPausedComposition == null) {
                                            this.rememberManager.dispatchAbandons();
                                        }
                                        this.rememberManager.clear();
                                        return;
                                    } catch (Throwable th3) {
                                        th = th3;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    try {
                                        writer$iv.close(false);
                                        throw th;
                                    } catch (Throwable th5) {
                                        th = th5;
                                    }
                                }
                            } catch (Throwable th6) {
                                th = th6;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                    }
                } catch (Throwable th9) {
                    th = th9;
                }
                Trace.INSTANCE.endSection(token$iv2);
                throw th;
            } catch (Throwable th10) {
                if (this.lateChanges.isEmpty() && this.pendingPausedComposition == null) {
                    this.rememberManager.dispatchAbandons();
                }
                this.rememberManager.clear();
                throw th10;
            }
        } catch (Throwable th11) {
            this.rememberManager.clear();
            throw th11;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                            Set<RememberObserver> set = this.abandonSet;
                            CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                            try {
                                this_$iv$iv$iv.prepare(set, traceContext$iv$iv$iv);
                                this_$iv$iv$iv.dispatchAbandons();
                            } finally {
                                this_$iv$iv$iv.clear();
                            }
                        }
                    } catch (Throwable e$iv) {
                        abandonChanges();
                        throw e$iv;
                    }
                }
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyLateChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                if (this.lateChanges.isNotEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                            Set<RememberObserver> set = this.abandonSet;
                            CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                            try {
                                this_$iv$iv$iv.prepare(set, traceContext$iv$iv$iv);
                                this_$iv$iv$iv.dispatchAbandons();
                            } finally {
                                this_$iv$iv$iv.clear();
                            }
                        }
                    } catch (Throwable e$iv) {
                        abandonChanges();
                        throw e$iv;
                    }
                }
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void changesApplied() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                this.composer.changesApplied$runtime();
                if (!this.abandonSet.isEmpty()) {
                    RememberEventDispatcher this_$iv = this.rememberManager;
                    Set<RememberObserver> set = this.abandonSet;
                    CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
                    try {
                        this_$iv.prepare(set, traceContext$iv);
                        this_$iv.dispatchAbandons();
                        this_$iv.clear();
                    } catch (Throwable th) {
                        this_$iv.clear();
                        throw th;
                    }
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } catch (Throwable th2) {
                if (0 == 0) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            RememberEventDispatcher this_$iv$iv$iv = this.rememberManager;
                            Set<RememberObserver> set2 = this.abandonSet;
                            CompositionErrorContext traceContext$iv$iv$iv = this.composer.getErrorContext$runtime();
                            try {
                                this_$iv$iv$iv.prepare(set2, traceContext$iv$iv$iv);
                                this_$iv$iv$iv.dispatchAbandons();
                            } finally {
                                this_$iv$iv$iv.clear();
                            }
                        }
                    } catch (Throwable e$iv) {
                        abandonChanges();
                        throw e$iv;
                    }
                }
                throw th2;
            }
        }
    }

    private final <T> T guardInvalidationsLocked(Function1<? super ScopeMap<RecomposeScopeImpl, Object>, ? extends T> block) {
        MutableScatterMap<Object, Object> mutableScatterMapM4310takeInvalidationsafanTW4 = m4310takeInvalidationsafanTW4();
        try {
            return block.invoke(ScopeMap.m4466boximpl(mutableScatterMapM4310takeInvalidationsafanTW4));
        } catch (Throwable e) {
            this.invalidations = mutableScatterMapM4310takeInvalidationsafanTW4;
            throw e;
        }
    }

    private final <T> T guardChanges(Function0<? extends T> block) {
        try {
            return block.invoke();
        } catch (Throwable th) {
            if (0 == 0) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        RememberEventDispatcher this_$iv$iv = this.rememberManager;
                        Set<RememberObserver> set = this.abandonSet;
                        CompositionErrorContext traceContext$iv$iv = this.composer.getErrorContext$runtime();
                        try {
                            this_$iv$iv.prepare(set, traceContext$iv$iv);
                            this_$iv$iv.dispatchAbandons();
                        } finally {
                            this_$iv$iv.clear();
                        }
                    }
                } catch (Throwable e) {
                    abandonChanges();
                    throw e;
                }
            }
            throw th;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        if (!this.abandonSet.isEmpty()) {
            RememberEventDispatcher this_$iv = this.rememberManager;
            Set<RememberObserver> set = this.abandonSet;
            CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
            try {
                this_$iv.prepare(set, traceContext$iv);
                this_$iv.dispatchAbandons();
            } finally {
                this_$iv.clear();
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            Object[] $this$forEach$iv = this.slotTable.getSlots();
            for (Object element$iv : $this$forEach$iv) {
                RecomposeScopeImpl recomposeScopeImpl = element$iv instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) element$iv : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.invalidate();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            if (!isComposing()) {
                this.composer.verifyConsistent$runtime();
                this.slotTable.verifyWellFormed();
                validateRecomposeScopeAnchors(this.slotTable);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public <R> R delegateInvalidations(ControlledComposition to, int groupIndex, Function0<? extends R> block) {
        if (to != null && !Intrinsics.areEqual(to, this) && groupIndex >= 0) {
            this.invalidationDelegate = (CompositionImpl) to;
            this.invalidationDelegateGroup = groupIndex;
            try {
                return block.invoke();
            } finally {
                this.invalidationDelegate = null;
                this.invalidationDelegateGroup = 0;
            }
        }
        return block.invoke();
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public ShouldPauseCallback getAndSetShouldPauseCallback(ShouldPauseCallback shouldPause) {
        ShouldPauseCallback previous = this.shouldPause;
        this.shouldPause = shouldPause;
        return previous;
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) throws Throwable {
        CompositionObserver compositionObserverObserver;
        CompositionImpl delegate;
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (!this.slotTable.ownsAnchor(anchor)) {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                delegate = this.invalidationDelegate;
            }
            if (delegate != null && delegate.tryImminentInvalidation(scope, instance)) {
                return InvalidationResult.IMMINENT;
            }
            return InvalidationResult.IGNORED;
        }
        if (!scope.getCanRecompose()) {
            return InvalidationResult.IGNORED;
        }
        InvalidationResult it = invalidateChecked(scope, anchor, instance);
        if (it != InvalidationResult.IGNORED && (compositionObserverObserver = observer()) != null) {
            compositionObserverObserver.onScopeInvalidated(scope, instance);
        }
        return it;
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public void recomposeScopeReleased(RecomposeScopeImpl scope) {
        this.pendingInvalidScopes = true;
        CompositionObserver compositionObserverObserver = observer();
        if (compositionObserverObserver != null) {
            compositionObserverObserver.onScopeDisposed(scope);
        }
    }

    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        if (Intrinsics.areEqual(key, CompositionKt.getObservableCompositionServiceKey())) {
            return (T) this;
        }
        return null;
    }

    private final boolean tryImminentInvalidation(RecomposeScopeImpl scope, Object instance) {
        return isComposing() && this.composer.tryImminentInvalidation$runtime(scope, instance);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x00fc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:15:0x0024 A[Catch: all -> 0x0176, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0176, blocks: (B:5:0x000d, B:15:0x0024, B:23:0x003f, B:29:0x0057, B:31:0x0065, B:33:0x006b), top: B:93:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:17:0x002a A[Catch: all -> 0x0052, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0052, blocks: (B:9:0x0013, B:17:0x002a, B:21:0x0030, B:25:0x0043), top: B:92:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:20:0x002e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:21:0x0030 A[Catch: all -> 0x0052, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0052, blocks: (B:9:0x0013, B:17:0x002a, B:21:0x0030, B:25:0x0043), top: B:92:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:23:0x003f A[Catch: all -> 0x0176, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0176, blocks: (B:5:0x000d, B:15:0x0024, B:23:0x003f, B:29:0x0057, B:31:0x0065, B:33:0x006b), top: B:93:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:25:0x0043 A[Catch: all -> 0x0052, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0052, blocks: (B:9:0x0013, B:17:0x002a, B:21:0x0030, B:25:0x0043), top: B:92:0x0013 }] */
    /* JADX WARN: Code duplicated, block: B:29:0x0057 A[Catch: all -> 0x0176, TRY_ENTER, TryCatch #1 {all -> 0x0176, blocks: (B:5:0x000d, B:15:0x0024, B:23:0x003f, B:29:0x0057, B:31:0x0065, B:33:0x006b), top: B:93:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:31:0x0065 A[Catch: all -> 0x0176, TryCatch #1 {all -> 0x0176, blocks: (B:5:0x000d, B:15:0x0024, B:23:0x003f, B:29:0x0057, B:31:0x0065, B:33:0x006b), top: B:93:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:33:0x006b A[Catch: all -> 0x0176, TRY_LEAVE, TryCatch #1 {all -> 0x0176, blocks: (B:5:0x000d, B:15:0x0024, B:23:0x003f, B:29:0x0057, B:31:0x0065, B:33:0x006b), top: B:93:0x000d }] */
    /* JADX WARN: Code duplicated, block: B:37:0x008e A[Catch: all -> 0x014e, LOOP:0: B:37:0x008e->B:58:0x0104, LOOP_START, PHI: r6 r7 r8 r9 r10
  0x008e: PHI (r6v6 'i$iv$iv$iv$iv' int) = (r6v5 'i$iv$iv$iv$iv' int), (r6v7 'i$iv$iv$iv$iv' int) binds: [B:36:0x008c, B:58:0x0104] A[DONT_GENERATE, DONT_INLINE]
  0x008e: PHI (r7v8 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>) = 
  (r7v3 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
  (r7v11 androidx.collection.MutableScatterMap<java.lang.Object, java.lang.Object>)
 binds: [B:36:0x008c, B:58:0x0104] A[DONT_GENERATE, DONT_INLINE]
  0x008e: PHI (r8v3 'delegate' androidx.compose.runtime.CompositionImpl) = (r8v1 'delegate' androidx.compose.runtime.CompositionImpl), (r8v4 'delegate' androidx.compose.runtime.CompositionImpl) binds: [B:36:0x008c, B:58:0x0104] A[DONT_GENERATE, DONT_INLINE]
  0x008e: PHI (r9v2 'key$iv' java.lang.Object) = (r9v0 'key$iv' java.lang.Object), (r9v7 'key$iv' java.lang.Object) binds: [B:36:0x008c, B:58:0x0104] A[DONT_GENERATE, DONT_INLINE]
  0x008e: PHI (r10v1 int) = (r10v0 int), (r10v2 int) binds: [B:36:0x008c, B:58:0x0104] A[DONT_GENERATE, DONT_INLINE], TryCatch #2 {all -> 0x014e, blocks: (B:35:0x0082, B:37:0x008e, B:39:0x00af, B:41:0x00bb, B:46:0x00cc, B:72:0x0148, B:54:0x00f5, B:58:0x0104, B:62:0x011a), top: B:95:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00af A[Catch: all -> 0x014e, TryCatch #2 {all -> 0x014e, blocks: (B:35:0x0082, B:37:0x008e, B:39:0x00af, B:41:0x00bb, B:46:0x00cc, B:72:0x0148, B:54:0x00f5, B:58:0x0104, B:62:0x011a), top: B:95:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x00bb A[Catch: all -> 0x014e, TryCatch #2 {all -> 0x014e, blocks: (B:35:0x0082, B:37:0x008e, B:39:0x00af, B:41:0x00bb, B:46:0x00cc, B:72:0x0148, B:54:0x00f5, B:58:0x0104, B:62:0x011a), top: B:95:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:44:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:46:0x00cc A[Catch: all -> 0x014e, TryCatch #2 {all -> 0x014e, blocks: (B:35:0x0082, B:37:0x008e, B:39:0x00af, B:41:0x00bb, B:46:0x00cc, B:72:0x0148, B:54:0x00f5, B:58:0x0104, B:62:0x011a), top: B:95:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:48:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:49:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:52:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:53:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:57:0x0102 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x0104 A[Catch: all -> 0x014e, LOOP:0: B:37:0x008e->B:58:0x0104, LOOP_END, TryCatch #2 {all -> 0x014e, blocks: (B:35:0x0082, B:37:0x008e, B:39:0x00af, B:41:0x00bb, B:46:0x00cc, B:72:0x0148, B:54:0x00f5, B:58:0x0104, B:62:0x011a), top: B:95:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x010f  */
    /* JADX WARN: Code duplicated, block: B:62:0x011a A[Catch: all -> 0x014e, TryCatch #2 {all -> 0x014e, blocks: (B:35:0x0082, B:37:0x008e, B:39:0x00af, B:41:0x00bb, B:46:0x00cc, B:72:0x0148, B:54:0x00f5, B:58:0x0104, B:62:0x011a), top: B:95:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x012e  */
    /* JADX WARN: Code duplicated, block: B:65:0x0131  */
    /* JADX WARN: Code duplicated, block: B:67:0x0134  */
    /* JADX WARN: Code duplicated, block: B:68:0x0137  */
    /* JADX WARN: Code duplicated, block: B:69:0x0138  */
    /* JADX WARN: Code duplicated, block: B:72:0x0148 A[Catch: all -> 0x014e, TRY_LEAVE, TryCatch #2 {all -> 0x014e, blocks: (B:35:0x0082, B:37:0x008e, B:39:0x00af, B:41:0x00bb, B:46:0x00cc, B:72:0x0148, B:54:0x00f5, B:58:0x0104, B:62:0x011a), top: B:95:0x0063 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x0150  */
    /* JADX WARN: Code duplicated, block: B:81:0x015d  */
    /* JADX WARN: Code duplicated, block: B:83:0x0162  */
    /* JADX WARN: Code duplicated, block: B:85:0x0170  */
    /* JADX WARN: Code duplicated, block: B:86:0x0173  */
    /* JADX WARN: Code duplicated, block: B:97:0x00eb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:98:0x0118 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:99:0x0118 A[SYNTHETIC] */
    private final InvalidationResult invalidateChecked(RecomposeScopeImpl scope, Anchor anchor, Object instance) throws Throwable {
        CompositionImpl delegate;
        CompositionImpl delegate2;
        MutableScatterMap<Object, Object> mutableScatterMap;
        Object key$iv;
        int i;
        Object value$iv$iv;
        boolean z;
        boolean z2;
        Object[] elements$iv$iv$iv;
        long[] m$iv$iv$iv$iv;
        int lastIndex$iv$iv$iv$iv;
        int i$iv$iv$iv$iv;
        long slot$iv$iv$iv$iv;
        MutableScatterMap<Object, Object> mutableScatterMap2;
        Object key$iv2;
        int i2;
        int i3;
        int bitCount$iv$iv$iv$iv;
        int j$iv$iv$iv$iv;
        long value$iv$iv$iv$iv$iv;
        int $i$f$isFull;
        Object it$iv;
        boolean z3;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                CompositionImpl changeDelegate = this.invalidationDelegate;
                CompositionImpl delegate3 = null;
                if (changeDelegate == null) {
                    if (delegate3 == null) {
                        delegate = delegate3;
                    } else {
                        if (tryImminentInvalidation(scope, instance)) {
                            return InvalidationResult.IMMINENT;
                        }
                        if (instance == null) {
                            ScopeMap.m4480setimpl(this.invalidations, scope, ScopeInvalidated.INSTANCE);
                            delegate = delegate3;
                        } else if (instance instanceof DerivedState) {
                            mutableScatterMap = this.invalidations;
                            key$iv = scope;
                            i = 0;
                            value$iv$iv = mutableScatterMap.get(key$iv);
                            if (value$iv$iv != null) {
                                if (value$iv$iv instanceof MutableScatterSet) {
                                    ScatterSet this_$iv$iv$iv = (MutableScatterSet) value$iv$iv;
                                    elements$iv$iv$iv = this_$iv$iv$iv.elements;
                                    m$iv$iv$iv$iv = this_$iv$iv$iv.metadata;
                                    lastIndex$iv$iv$iv$iv = m$iv$iv$iv$iv.length - 2;
                                    i$iv$iv$iv$iv = 0;
                                    if (0 <= lastIndex$iv$iv$iv$iv) {
                                        while (true) {
                                            slot$iv$iv$iv$iv = m$iv$iv$iv$iv[i$iv$iv$iv$iv];
                                            mutableScatterMap2 = mutableScatterMap;
                                            delegate = delegate3;
                                            key$iv2 = key$iv;
                                            i2 = i;
                                            if ((((~slot$iv$iv$iv$iv) << 7) & slot$iv$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                                                i3 = 8;
                                                bitCount$iv$iv$iv$iv = 8 - ((~(i$iv$iv$iv$iv - lastIndex$iv$iv$iv$iv)) >>> 31);
                                                j$iv$iv$iv$iv = 0;
                                                while (true) {
                                                    if (j$iv$iv$iv$iv < bitCount$iv$iv$iv$iv) {
                                                        value$iv$iv$iv$iv$iv = slot$iv$iv$iv$iv & 255;
                                                        if (value$iv$iv$iv$iv$iv < 128) {
                                                            $i$f$isFull = 1;
                                                        } else {
                                                            $i$f$isFull = 0;
                                                        }
                                                        if ($i$f$isFull != 0) {
                                                            int index$iv$iv$iv$iv = (i$iv$iv$iv$iv << 3) + j$iv$iv$iv$iv;
                                                            it$iv = elements$iv$iv$iv[index$iv$iv$iv$iv];
                                                            if (it$iv == ScopeInvalidated.INSTANCE) {
                                                                z3 = true;
                                                            } else {
                                                                z3 = false;
                                                            }
                                                            if (z3) {
                                                                z = true;
                                                            }
                                                        }
                                                        slot$iv$iv$iv$iv >>= i3;
                                                        j$iv$iv$iv$iv++;
                                                        i3 = i3;
                                                    } else if (bitCount$iv$iv$iv$iv == i3) {
                                                        if (i$iv$iv$iv$iv != lastIndex$iv$iv$iv$iv) {
                                                            i$iv$iv$iv$iv++;
                                                            key$iv = key$iv2;
                                                            i = i2;
                                                            delegate3 = delegate;
                                                            mutableScatterMap = mutableScatterMap2;
                                                        }
                                                    }
                                                }
                                            } else if (i$iv$iv$iv$iv != lastIndex$iv$iv$iv$iv) {
                                                i$iv$iv$iv$iv++;
                                                key$iv = key$iv2;
                                                i = i2;
                                                delegate3 = delegate;
                                                mutableScatterMap = mutableScatterMap2;
                                            }
                                        }
                                    } else {
                                        delegate = delegate3;
                                    }
                                } else {
                                    delegate = delegate3;
                                    if (value$iv$iv == ScopeInvalidated.INSTANCE) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    if (z2) {
                                        z = true;
                                    }
                                }
                                if (!z) {
                                    ScopeMap.m4463addimpl(this.invalidations, scope, instance);
                                }
                            } else {
                                delegate = delegate3;
                            }
                            z = false;
                            if (!z) {
                                ScopeMap.m4463addimpl(this.invalidations, scope, instance);
                            }
                        } else {
                            ScopeMap.m4480setimpl(this.invalidations, scope, ScopeInvalidated.INSTANCE);
                            delegate = delegate3;
                        }
                    }
                    delegate2 = delegate;
                    if (delegate2 != null) {
                        return delegate2.invalidateChecked(scope, anchor, instance);
                    }
                    this.parent.invalidate$runtime(this);
                    if (isComposing()) {
                    }
                }
                try {
                    if (this.slotTable.groupContainsAnchor(this.invalidationDelegateGroup, anchor)) {
                        delegate3 = changeDelegate;
                    }
                    if (delegate3 == null) {
                        delegate = delegate3;
                    } else {
                        if (tryImminentInvalidation(scope, instance)) {
                            return InvalidationResult.IMMINENT;
                        }
                        if (instance == null) {
                            ScopeMap.m4480setimpl(this.invalidations, scope, ScopeInvalidated.INSTANCE);
                            delegate = delegate3;
                        } else if (instance instanceof DerivedState) {
                            ScopeMap.m4480setimpl(this.invalidations, scope, ScopeInvalidated.INSTANCE);
                            delegate = delegate3;
                        } else {
                            mutableScatterMap = this.invalidations;
                            key$iv = scope;
                            i = 0;
                            value$iv$iv = mutableScatterMap.get(key$iv);
                            try {
                                if (value$iv$iv != null) {
                                    if (value$iv$iv instanceof MutableScatterSet) {
                                        ScatterSet this_$iv$iv$iv2 = (MutableScatterSet) value$iv$iv;
                                        elements$iv$iv$iv = this_$iv$iv$iv2.elements;
                                        m$iv$iv$iv$iv = this_$iv$iv$iv2.metadata;
                                        lastIndex$iv$iv$iv$iv = m$iv$iv$iv$iv.length - 2;
                                        i$iv$iv$iv$iv = 0;
                                        if (0 <= lastIndex$iv$iv$iv$iv) {
                                            while (true) {
                                                slot$iv$iv$iv$iv = m$iv$iv$iv$iv[i$iv$iv$iv$iv];
                                                mutableScatterMap2 = mutableScatterMap;
                                                delegate = delegate3;
                                                key$iv2 = key$iv;
                                                i2 = i;
                                                if ((((~slot$iv$iv$iv$iv) << 7) & slot$iv$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                                                    i3 = 8;
                                                    bitCount$iv$iv$iv$iv = 8 - ((~(i$iv$iv$iv$iv - lastIndex$iv$iv$iv$iv)) >>> 31);
                                                    j$iv$iv$iv$iv = 0;
                                                    while (true) {
                                                        if (j$iv$iv$iv$iv < bitCount$iv$iv$iv$iv) {
                                                            value$iv$iv$iv$iv$iv = slot$iv$iv$iv$iv & 255;
                                                            if (value$iv$iv$iv$iv$iv < 128) {
                                                                $i$f$isFull = 1;
                                                            } else {
                                                                $i$f$isFull = 0;
                                                            }
                                                            if ($i$f$isFull != 0) {
                                                                int index$iv$iv$iv$iv2 = (i$iv$iv$iv$iv << 3) + j$iv$iv$iv$iv;
                                                                it$iv = elements$iv$iv$iv[index$iv$iv$iv$iv2];
                                                                if (it$iv == ScopeInvalidated.INSTANCE) {
                                                                    z3 = true;
                                                                } else {
                                                                    z3 = false;
                                                                }
                                                                if (z3) {
                                                                    z = true;
                                                                }
                                                            }
                                                            slot$iv$iv$iv$iv >>= i3;
                                                            j$iv$iv$iv$iv++;
                                                            i3 = i3;
                                                        } else if (bitCount$iv$iv$iv$iv == i3) {
                                                            if (i$iv$iv$iv$iv != lastIndex$iv$iv$iv$iv) {
                                                                i$iv$iv$iv$iv++;
                                                                key$iv = key$iv2;
                                                                i = i2;
                                                                delegate3 = delegate;
                                                                mutableScatterMap = mutableScatterMap2;
                                                            }
                                                        }
                                                    }
                                                } else if (i$iv$iv$iv$iv != lastIndex$iv$iv$iv$iv) {
                                                    i$iv$iv$iv$iv++;
                                                    key$iv = key$iv2;
                                                    i = i2;
                                                    delegate3 = delegate;
                                                    mutableScatterMap = mutableScatterMap2;
                                                }
                                            }
                                        } else {
                                            delegate = delegate3;
                                        }
                                    } else {
                                        delegate = delegate3;
                                        if (value$iv$iv == ScopeInvalidated.INSTANCE) {
                                            z2 = true;
                                        } else {
                                            z2 = false;
                                        }
                                        if (z2) {
                                            z = true;
                                        }
                                    }
                                    if (!z) {
                                        ScopeMap.m4463addimpl(this.invalidations, scope, instance);
                                    }
                                } else {
                                    delegate = delegate3;
                                }
                                z = false;
                                if (!z) {
                                    ScopeMap.m4463addimpl(this.invalidations, scope, instance);
                                }
                            } catch (Throwable th) {
                                th = th;
                            }
                        }
                    }
                    delegate2 = delegate;
                    if (delegate2 != null) {
                        return delegate2.invalidateChecked(scope, anchor, instance);
                    }
                    this.parent.invalidate$runtime(this);
                    return isComposing() ? InvalidationResult.DEFERRED : InvalidationResult.SCHEDULED;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
            throw th;
        }
    }

    public final void removeObservation$runtime(Object instance, RecomposeScopeImpl scope) {
        ScopeMap.m4476removeimpl(this.observations, instance, scope);
    }

    public final void removeDerivedStateObservation$runtime(DerivedState<?> state) {
        if (!ScopeMap.m4470containsimpl(this.observations, state)) {
            ScopeMap.m4478removeScopeimpl(this.derivedStates, state);
        }
    }

    /* JADX INFO: renamed from: takeInvalidations-afanTW4, reason: not valid java name */
    private final MutableScatterMap<Object, Object> m4310takeInvalidationsafanTW4() {
        MutableScatterMap<Object, Object> mutableScatterMap = this.invalidations;
        this.invalidations = ScopeMap.m4469constructorimpl$default(null, 1, null);
        return mutableScatterMap;
    }

    private final void validateRecomposeScopeAnchors(SlotTable slotTable) {
        SlotTable slotTable2 = slotTable;
        Object[] $this$mapNotNull$iv = slotTable2.getSlots();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            RecomposeScopeImpl recomposeScopeImpl = element$iv$iv$iv instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) element$iv$iv$iv : null;
            if (recomposeScopeImpl != null) {
                destination$iv$iv.add(recomposeScopeImpl);
            }
        }
        List scopes = (List) destination$iv$iv;
        int index$iv = 0;
        int size = scopes.size();
        while (index$iv < size) {
            Object item$iv = scopes.get(index$iv);
            RecomposeScopeImpl scope = (RecomposeScopeImpl) item$iv;
            Anchor anchor = scope.getAnchor();
            if (anchor != null) {
                boolean value$iv = slotTable2.slotsOf$runtime(anchor.toIndexFor(slotTable2)).contains(scope);
                if (!value$iv) {
                    int dataIndex = ArraysKt.indexOf((RecomposeScopeImpl[]) slotTable2.getSlots(), scope);
                    PreconditionsKt.throwIllegalStateException("Misaligned anchor " + anchor + " in scope " + scope + " encountered, scope found at " + dataIndex);
                }
            }
            index$iv++;
            slotTable2 = slotTable;
        }
    }

    private final <T> T trackAbandonedValues(Function0<? extends T> block) {
        try {
            return block.invoke();
        } catch (Throwable th) {
            if (0 == 0 && !this.abandonSet.isEmpty()) {
                RememberEventDispatcher this_$iv = this.rememberManager;
                Set<RememberObserver> set = this.abandonSet;
                CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
                try {
                    this_$iv.prepare(set, traceContext$iv);
                    this_$iv.dispatchAbandons();
                } finally {
                    this_$iv.clear();
                }
            }
            throw th;
        }
    }

    private final CompositionObserver observer() {
        return this.observerHolder.current();
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void deactivate() throws Throwable {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                boolean value$iv = this.pendingPausedComposition == null;
                if (!value$iv) {
                    try {
                        PreconditionsKt.throwIllegalStateException("Deactivate is not supported while pausable composition is in progress");
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                boolean nonEmptySlotTable = this.slotTable.getGroupsSize() > 0;
                try {
                    if (nonEmptySlotTable || !this.abandonSet.isEmpty()) {
                        Object token$iv = Trace.INSTANCE.beginSection("Compose:deactivate");
                        try {
                            RememberEventDispatcher this_$iv = this.rememberManager;
                            Set<RememberObserver> set = this.abandonSet;
                            CompositionErrorContext traceContext$iv = this.composer.getErrorContext$runtime();
                            try {
                                try {
                                    this_$iv.prepare(set, traceContext$iv);
                                    try {
                                        if (nonEmptySlotTable) {
                                            this.applier.onBeginChanges();
                                            SlotWriter writer$iv = this.slotTable.openWriter();
                                            try {
                                                try {
                                                    ComposerImplKt.deactivateCurrentGroup(writer$iv, this.rememberManager);
                                                    Unit unit = Unit.INSTANCE;
                                                    writer$iv.close(true);
                                                    this.applier.onEndChanges();
                                                    this_$iv.dispatchRememberObservers();
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    writer$iv.close(false);
                                                    throw th;
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                            }
                                        }
                                        this_$iv.dispatchAbandons();
                                        this_$iv.clear();
                                        Unit unit2 = Unit.INSTANCE;
                                        Trace.INSTANCE.endSection(token$iv);
                                    } catch (Throwable th4) {
                                        th = th4;
                                        this_$iv.clear();
                                        throw th;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                Trace.INSTANCE.endSection(token$iv);
                                throw th;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                        }
                    }
                    ScopeMap.m4467clearimpl(this.observations);
                    ScopeMap.m4467clearimpl(this.derivedStates);
                    ScopeMap.m4467clearimpl(this.invalidations);
                    this.changes.clear();
                    this.lateChanges.clear();
                    this.composer.deactivate$runtime();
                    this.state = 1;
                    Unit unit3 = Unit.INSTANCE;
                } catch (Throwable th8) {
                    th = th8;
                    throw th;
                }
            } catch (Throwable th9) {
                th = th9;
            }
        }
    }

    public final int composerStacksSizes$runtime() {
        return this.composer.stacksSize$runtime();
    }
}
