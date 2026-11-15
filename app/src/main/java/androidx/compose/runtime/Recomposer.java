package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectList;
import androidx.collection.ObjectListKt;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MultiValueMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScatterSetWrapperKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.ExtensionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.internal.SnapshotThreadLocal;
import androidx.compose.runtime.internal.Trace;
import androidx.compose.runtime.internal.Utils_androidKt;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.ObserverHandle;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.TransparentObserverMutableSnapshot;
import androidx.compose.runtime.snapshots.TransparentObserverSnapshot;
import androidx.compose.runtime.tooling.ComposeStackTraceMode;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.CompositionObserverKt;
import androidx.compose.runtime.tooling.CompositionRegistrationObserver;
import androidx.compose.runtime.tooling.ObservableComposition;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.common.util.UriUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: Recomposer.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000ð\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 í\u00012\u00020\u0001:\né\u0001ê\u0001ë\u0001ì\u0001í\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010R\u001a\n\u0012\u0004\u0012\u000202\u0018\u000101H\u0002J\b\u0010S\u001a\u000202H\u0002J\u0006\u0010a\u001a\u00020bJ\b\u0010c\u001a\u000206H\u0002J\u001d\u0010c\u001a\u0002022\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u0002020eH\u0082\bJ\u0010\u0010f\u001a\u0002022\u0006\u0010g\u001a\u00020\u0014H\u0002J\u000e\u0010h\u001a\u000202H\u0086@¢\u0006\u0002\u0010iJ&\u0010j\u001a\u0002022\u0006\u0010k\u001a\u00020\u00162\n\b\u0002\u0010l\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010m\u001a\u000206H\u0002J\u0017\u0010n\u001a\u0002022\f\u0010o\u001a\b\u0012\u0004\u0012\u0002020pH\u0082\bJ\u000e\u0010q\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0002J\u000e\u0010r\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bH\u0002J\b\u0010s\u001a\u000202H\u0002J\u0010\u0010t\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0010\u0010v\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0010\u0010w\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0010\u0010x\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0015\u0010y\u001a\u00020z2\u0006\u0010{\u001a\u00020OH\u0000¢\u0006\u0002\b|J\n\u0010}\u001a\u0004\u0018\u000108H\u0002J\b\u0010~\u001a\u000202H\u0002J\u0010\u0010\u007f\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J\u0018\u0010\u0080\u0001\u001a\u0002022\u0006\u0010D\u001a\u00020\u0003H\u0087@¢\u0006\u0003\u0010\u0081\u0001J$\u0010\u0082\u0001\u001a\u0002022\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001H\u0082@¢\u0006\u0003\u0010\u0087\u0001J\u000f\u0010\u008a\u0001\u001a\u000202H\u0082@¢\u0006\u0002\u0010iJV\u0010\u008b\u0001\u001a\u0002022D\u0010o\u001a@\b\u0001\u0012\u0005\u0012\u00030\u008d\u0001\u0012\u0017\u0012\u00150\u0084\u0001¢\u0006\u000f\b\u008e\u0001\u0012\n\b\u008f\u0001\u0012\u0005\b\b(\u0083\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u0002020\u0090\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u008c\u0001¢\u0006\u0003\b\u0091\u0001H\u0082@¢\u0006\u0003\u0010\u0092\u0001J\u0007\u0010\u0093\u0001\u001a\u000202J\u0007\u0010\u0094\u0001\u001a\u000202J\u000f\u0010\u0095\u0001\u001a\u000202H\u0086@¢\u0006\u0002\u0010iJ\u0019\u0010\u0096\u0001\u001a\u00030\u0097\u00012\r\u0010\u0098\u0001\u001a\b\u0012\u0004\u0012\u0002020pH\u0016J/\u0010\u0099\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u00192\u0013\u0010\u009a\u0001\u001a\u000e\u0012\u0004\u0012\u0002020p¢\u0006\u0003\b\u009b\u0001H\u0010¢\u0006\u0006\b\u009c\u0001\u0010\u009d\u0001J@\u0010\u009e\u0001\u001a\t\u0012\u0004\u0012\u00020?0\u009f\u00012\u0006\u0010u\u001a\u00020\u00192\b\u0010 \u0001\u001a\u00030¡\u00012\u0013\u0010\u009a\u0001\u001a\u000e\u0012\u0004\u0012\u0002020p¢\u0006\u0003\b\u009b\u0001H\u0010¢\u0006\u0006\b¢\u0001\u0010£\u0001J8\u0010¤\u0001\u001a\t\u0012\u0004\u0012\u00020?0\u009f\u00012\u0006\u0010u\u001a\u00020\u00192\b\u0010 \u0001\u001a\u00030¡\u00012\u000e\u0010¥\u0001\u001a\t\u0012\u0004\u0012\u00020?0\u009f\u0001H\u0010¢\u0006\u0003\b¦\u0001J\u0018\u0010§\u0001\u001a\u0002022\u0007\u0010¨\u0001\u001a\u00020?H\u0010¢\u0006\u0003\b©\u0001J\u0011\u0010ª\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0002J$\u0010«\u0001\u001a\u0004\u0018\u00010\u00192\u0006\u0010u\u001a\u00020\u00192\u000f\u0010¬\u0001\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001dH\u0002J/\u0010\u00ad\u0001\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\r\u0010®\u0001\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u000f\u0010¬\u0001\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001dH\u0002J\t\u0010¯\u0001\u001a\u000202H\u0002J\u001d\u0010°\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002020e2\u0006\u0010u\u001a\u00020\u0019H\u0002J.\u0010±\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002020e2\u0006\u0010u\u001a\u00020\u00192\u000f\u0010¬\u0001\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001dH\u0002J@\u0010²\u0001\u001a\u0003H³\u0001\"\u0005\b\u0000\u0010³\u00012\u0006\u0010u\u001a\u00020\u00192\u000f\u0010¬\u0001\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001d2\r\u0010o\u001a\t\u0012\u0005\u0012\u0003H³\u00010pH\u0082\b¢\u0006\u0003\u0010´\u0001J\u0013\u0010µ\u0001\u001a\u0002022\b\u0010¶\u0001\u001a\u00030·\u0001H\u0002J\u000f\u0010¾\u0001\u001a\u000202H\u0086@¢\u0006\u0002\u0010iJ\u0007\u0010¿\u0001\u001a\u000202J\u0007\u0010À\u0001\u001a\u000202J\u001f\u0010Ì\u0001\u001a\u0002022\u000e\u0010Í\u0001\u001a\t\u0012\u0005\u0012\u00030Î\u00010/H\u0010¢\u0006\u0003\bÏ\u0001J\u0017\u0010Ð\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0010¢\u0006\u0003\bÑ\u0001J\u0017\u0010Ò\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0010¢\u0006\u0003\bÓ\u0001J\u0017\u0010Ô\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0010¢\u0006\u0003\bÕ\u0001J\u0018\u0010Ö\u0001\u001a\u0002022\u0007\u0010¨\u0001\u001a\u00020?H\u0010¢\u0006\u0003\b×\u0001J\u0018\u0010Ø\u0001\u001a\u0002022\u0007\u0010Ù\u0001\u001a\u00020\"H\u0010¢\u0006\u0003\bÚ\u0001J\u0018\u0010Û\u0001\u001a\u0002022\u0007\u0010Ù\u0001\u001a\u00020\"H\u0010¢\u0006\u0003\bÜ\u0001J/\u0010Ý\u0001\u001a\u0002022\u0007\u0010Ù\u0001\u001a\u00020\"2\u0007\u0010Þ\u0001\u001a\u00020+2\f\u0010ß\u0001\u001a\u0007\u0012\u0002\b\u00030à\u0001H\u0010¢\u0006\u0003\bá\u0001J\u0017\u0010â\u0001\u001a\u0002022\u0006\u0010u\u001a\u00020\u0019H\u0010¢\u0006\u0003\bã\u0001J\u001a\u0010ä\u0001\u001a\u0004\u0018\u00010+2\u0007\u0010Ù\u0001\u001a\u00020\"H\u0010¢\u0006\u0003\bå\u0001R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00190\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010#\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100%\u0012\u0004\u0012\u00020\"0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020+0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"0$X\u0082\u0004¢\u0006\u0004\n\u0002\u0010&R\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00100\u001a\n\u0012\u0004\u0012\u000202\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u000206X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010=\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020?\u0018\u00010\u001d0>X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\u00038PX\u0090\u0004¢\u0006\u0006\u001a\u0004\bE\u0010CR\u0014\u0010F\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0014\u0010I\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010HR\u0014\u0010K\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bL\u0010HR\u001c\u0010M\u001a\n\u0012\u0004\u0012\u00020O\u0018\u00010NX\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\bP\u0010QR\u0014\u0010T\u001a\u0002068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bU\u0010HR \u0010V\u001a\b\u0012\u0004\u0012\u00020<0W8FX\u0087\u0004¢\u0006\f\u0012\u0004\bX\u0010Q\u001a\u0004\bY\u0010ZR\u0017\u0010[\u001a\b\u0012\u0004\u0012\u00020<0\\8F¢\u0006\u0006\u001a\u0004\b]\u0010^R\u0012\u0010_\u001a\u00060`R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0088\u0001\u001a\u0002068BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b\u0089\u0001\u0010HR\u0013\u0010¸\u0001\u001a\u0002068F¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010HR\u0016\u0010º\u0001\u001a\u0002068BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b»\u0001\u0010HR\u0016\u0010¼\u0001\u001a\u0002068BX\u0082\u0004¢\u0006\u0007\u001a\u0005\b½\u0001\u0010HR\u001b\u0010Á\u0001\u001a\u00070\u0007j\u0003`Â\u00018PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÃ\u0001\u0010\nR\u0016\u0010Ä\u0001\u001a\u0002068PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÅ\u0001\u0010HR\u0016\u0010Æ\u0001\u001a\u0002068PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÇ\u0001\u0010HR\u0016\u0010È\u0001\u001a\u0002068PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÉ\u0001\u0010HR\u0016\u0010Ê\u0001\u001a\u0002068PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bË\u0001\u0010HR\u0019\u0010u\u001a\u0005\u0018\u00010æ\u00018PX\u0090\u0004¢\u0006\b\u001a\u0006\bç\u0001\u0010è\u0001¨\u0006î\u0001"}, d2 = {"Landroidx/compose/runtime/Recomposer;", "Landroidx/compose/runtime/CompositionContext;", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lkotlin/coroutines/CoroutineContext;)V", "value", "", "changeCount", "getChangeCount", "()J", "broadcastFrameClock", "Landroidx/compose/runtime/BroadcastFrameClock;", "nextFrameEndCallbackQueue", "Landroidx/compose/runtime/NextFrameEndCallbackQueue;", "stateLock", "", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "runnerJob", "Lkotlinx/coroutines/Job;", "closeCause", "", "_knownCompositions", "", "Landroidx/compose/runtime/ControlledComposition;", "_knownCompositionsCache", "", "snapshotInvalidations", "Landroidx/collection/MutableScatterSet;", "compositionInvalidations", "Landroidx/compose/runtime/collection/MutableVector;", "compositionsAwaitingApply", "movableContentAwaitingInsert", "Landroidx/compose/runtime/MovableContentStateReference;", "movableContentRemoved", "Landroidx/compose/runtime/collection/MultiValueMap;", "Landroidx/compose/runtime/MovableContent;", "Landroidx/collection/MutableScatterMap;", "movableContentNestedStatesAvailable", "Landroidx/compose/runtime/NestedContentMap;", "movableContentStatesAvailable", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/MovableContentState;", "movableContentNestedExtractionsPending", "failedCompositions", "compositionsRemoved", "", "workContinuation", "Lkotlinx/coroutines/CancellableContinuation;", "", "concurrentCompositionsOutstanding", "", "isClosed", "", "errorState", "Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "frameClockPaused", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/runtime/Recomposer$State;", "pausedScopes", "Landroidx/compose/runtime/internal/SnapshotThreadLocal;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "effectJob", "Lkotlinx/coroutines/CompletableJob;", "getEffectCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "recomposeCoroutineContext", "getRecomposeCoroutineContext$runtime", "hasBroadcastFrameClockAwaitersLocked", "getHasBroadcastFrameClockAwaitersLocked", "()Z", "hasNextFrameEndAwaitersLocked", "getHasNextFrameEndAwaitersLocked", "hasBroadcastFrameClockAwaiters", "getHasBroadcastFrameClockAwaiters", "registrationObservers", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/runtime/tooling/CompositionRegistrationObserver;", "getRegistrationObservers$annotations", "()V", "deriveStateLocked", "onNewFrameAwaiter", "shouldKeepRecomposing", "getShouldKeepRecomposing", "state", "Lkotlinx/coroutines/flow/Flow;", "getState$annotations", "getState", "()Lkotlinx/coroutines/flow/Flow;", "currentState", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentState", "()Lkotlinx/coroutines/flow/StateFlow;", "recomposerInfo", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "asRecomposerInfo", "Landroidx/compose/runtime/RecomposerInfo;", "recordComposerModifications", "onEachInvalidComposition", "Lkotlin/Function1;", "registerRunnerJob", "callingJob", "runRecomposeAndApplyChanges", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processCompositionError", "e", "failedInitialComposition", "recoverable", "withTransparentSnapshot", "block", "Lkotlin/Function0;", "knownCompositions", "knownCompositionsLocked", "clearKnownCompositionsLocked", "removeKnownCompositionLocked", "composition", "addKnownCompositionLocked", "registerCompositionLocked", "unregisterCompositionLocked", "addCompositionRegistrationObserver", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "addCompositionRegistrationObserver$runtime", "resetErrorState", "retryFailedCompositions", "recordFailedCompositionLocked", "runRecomposeConcurrentlyAndApplyChanges", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runFrameLoop", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;", "frameSignal", "Landroidx/compose/runtime/ProduceFrameSignal;", "(Landroidx/compose/runtime/MonotonicFrameClock;Landroidx/compose/runtime/ProduceFrameSignal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasSchedulingWork", "getHasSchedulingWork", "awaitWorkAvailable", "recompositionRunner", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancel", "close", "join", "scheduleFrameEndCallback", "Landroidx/compose/runtime/CancellationHandle;", "action", "composeInitial", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/runtime/Composable;", "composeInitial$runtime", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "composeInitialPaused", "Landroidx/collection/ScatterSet;", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "composeInitialPaused$runtime", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ShouldPauseCallback;Lkotlin/jvm/functions/Function2;)Landroidx/collection/ScatterSet;", "recomposePaused", "invalidScopes", "recomposePaused$runtime", "reportPausedScope", "scope", "reportPausedScope$runtime", "performInitialMovableContentInserts", "performRecompose", "modifiedValues", "performInsertValues", "references", "discardUnusedMovableContentState", "readObserverOf", "writeObserverOf", "composing", ExifInterface.GPS_DIRECTION_TRUE, "(Landroidx/compose/runtime/ControlledComposition;Landroidx/collection/MutableScatterSet;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "applyAndCheck", "snapshot", "Landroidx/compose/runtime/snapshots/MutableSnapshot;", "hasPendingWork", "getHasPendingWork", "hasFrameWorkLocked", "getHasFrameWorkLocked", "hasConcurrentFrameWorkLocked", "getHasConcurrentFrameWorkLocked", "awaitIdle", "pauseCompositionFrameClock", "resumeCompositionFrameClock", "compositeKeyHashCode", "Landroidx/compose/runtime/CompositeKeyHashCode;", "getCompositeKeyHashCode$runtime", "collectingCallByInformation", "getCollectingCallByInformation$runtime", "collectingParameterInformation", "getCollectingParameterInformation$runtime", "collectingSourceInformation", "getCollectingSourceInformation$runtime", "stackTraceEnabled", "getStackTraceEnabled$runtime", "recordInspectionTable", "table", "Landroidx/compose/runtime/tooling/CompositionData;", "recordInspectionTable$runtime", "registerComposition", "registerComposition$runtime", "unregisterComposition", "unregisterComposition$runtime", "invalidate", "invalidate$runtime", "invalidateScope", "invalidateScope$runtime", "insertMovableContent", "reference", "insertMovableContent$runtime", "deletedMovableContent", "deletedMovableContent$runtime", "movableContentStateReleased", "data", "applier", "Landroidx/compose/runtime/Applier;", "movableContentStateReleased$runtime", "reportRemovedComposition", "reportRemovedComposition$runtime", "movableContentStateResolve", "movableContentStateResolve$runtime", "Landroidx/compose/runtime/Composition;", "getComposition$runtime", "()Landroidx/compose/runtime/Composition;", "State", "RecomposerInfoImpl", "HotReloadable", "RecomposerErrorState", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Recomposer extends CompositionContext {
    private List<? extends ControlledComposition> _knownCompositionsCache;
    private long changeCount;
    private Throwable closeCause;
    private Set<ControlledComposition> compositionsRemoved;
    private int concurrentCompositionsOutstanding;
    private final CoroutineContext effectCoroutineContext;
    private final CompletableJob effectJob;
    private RecomposerErrorState errorState;
    private List<ControlledComposition> failedCompositions;
    private boolean frameClockPaused;
    private boolean isClosed;
    private final RecomposerInfoImpl recomposerInfo;
    private MutableObjectList<CompositionRegistrationObserver> registrationObservers;
    private Job runnerJob;
    private CancellableContinuation<? super Unit> workContinuation;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final MutableStateFlow<PersistentSet<RecomposerInfoImpl>> _runningRecomposers = StateFlowKt.MutableStateFlow(ExtensionsKt.persistentSetOf());
    private static final AtomicReference<Boolean> _hotReloadEnabled = new AtomicReference<>(false);
    private final BroadcastFrameClock broadcastFrameClock = new BroadcastFrameClock(new Function0() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Recomposer.broadcastFrameClock$lambda$0(this.f$0);
        }
    });
    private final NextFrameEndCallbackQueue nextFrameEndCallbackQueue = new NextFrameEndCallbackQueue(new Function0() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Recomposer.nextFrameEndCallbackQueue$lambda$0(this.f$0);
        }
    });
    private final Object stateLock = new Object();
    private final List<ControlledComposition> _knownCompositions = new ArrayList();
    private MutableScatterSet<Object> snapshotInvalidations = new MutableScatterSet<>(0, 1, null);
    private final MutableVector<ControlledComposition> compositionInvalidations = new MutableVector<>(new ControlledComposition[16], 0);
    private final List<ControlledComposition> compositionsAwaitingApply = new ArrayList();
    private final List<MovableContentStateReference> movableContentAwaitingInsert = new ArrayList();
    private final MutableScatterMap<Object, Object> movableContentRemoved = MultiValueMap.m4447constructorimpl$default(null, 1, null);
    private final NestedContentMap movableContentNestedStatesAvailable = new NestedContentMap();
    private final MutableScatterMap<MovableContentStateReference, MovableContentState> movableContentStatesAvailable = ScatterMapKt.mutableScatterMapOf();
    private final MutableScatterMap<Object, Object> movableContentNestedExtractionsPending = MultiValueMap.m4447constructorimpl$default(null, 1, null);
    private final MutableStateFlow<State> _state = StateFlowKt.MutableStateFlow(State.Inactive);
    private final SnapshotThreadLocal<MutableScatterSet<RecomposeScopeImpl>> pausedScopes = new SnapshotThreadLocal<>();

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/compose/runtime/Recomposer$State;", "", "<init>", "(Ljava/lang/String;I)V", "ShutDown", "ShuttingDown", "Inactive", "InactivePendingWork", "Idle", "PendingWork", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum State {
        ShutDown,
        ShuttingDown,
        Inactive,
        InactivePendingWork,
        Idle,
        PendingWork;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<State> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runFrameLoop$1, reason: invalid class name */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer", f = "Recomposer.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {1042, 1049}, m = "runFrameLoop", n = {"parentFrameClock", "frameSignal", "toRecompose", "toApply", "parentFrameClock", "frameSignal", "toRecompose", "toApply"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return Recomposer.this.runFrameLoop(null, null, this);
        }
    }

    private static /* synthetic */ void getRegistrationObservers$annotations() {
    }

    @Deprecated(message = "Replaced by currentState as a StateFlow", replaceWith = @ReplaceWith(expression = "currentState", imports = {}))
    public static /* synthetic */ void getState$annotations() {
    }

    public Recomposer(CoroutineContext effectCoroutineContext) {
        CompletableJob $this$effectJob_u24lambda_u240 = JobKt.Job((Job) effectCoroutineContext.get(Job.INSTANCE));
        $this$effectJob_u24lambda_u240.invokeOnCompletion(new Function1() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Recomposer.effectJob$lambda$0$0(this.f$0, (Throwable) obj);
            }
        });
        this.effectJob = $this$effectJob_u24lambda_u240;
        this.effectCoroutineContext = effectCoroutineContext.plus(this.broadcastFrameClock).plus(this.effectJob);
        this.recomposerInfo = new RecomposerInfoImpl();
    }

    public final long getChangeCount() {
        return this.changeCount;
    }

    static final Unit broadcastFrameClock$lambda$0(Recomposer this$0) {
        this$0.onNewFrameAwaiter();
        return Unit.INSTANCE;
    }

    static final Unit nextFrameEndCallbackQueue$lambda$0(Recomposer this$0) {
        this$0.onNewFrameAwaiter();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit effectJob$lambda$0$0(final Recomposer this$0, final Throwable throwable) {
        CancellationException cancellation = ExceptionsKt.CancellationException("Recomposer effect job completed", throwable);
        Object continuationToResume = null;
        Object lock$iv = this$0.stateLock;
        synchronized (lock$iv) {
            Job runnerJob = this$0.runnerJob;
            if (runnerJob != null) {
                this$0._state.setValue(State.ShuttingDown);
                if (!this$0.isClosed) {
                    runnerJob.cancel(cancellation);
                } else if (this$0.workContinuation != null) {
                    continuationToResume = this$0.workContinuation;
                }
                this$0.workContinuation = null;
                runnerJob.invokeOnCompletion(new Function1() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Recomposer.effectJob$lambda$0$0$0$0(this.f$0, throwable, (Throwable) obj);
                    }
                });
            } else {
                this$0.closeCause = cancellation;
                this$0._state.setValue(State.ShutDown);
                Unit unit = Unit.INSTANCE;
            }
        }
        if (continuationToResume != null) {
            Result.Companion companion = Result.INSTANCE;
            ((Continuation) continuationToResume).resumeWith(Result.m9088constructorimpl(Unit.INSTANCE));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit effectJob$lambda$0$0$0$0(Recomposer this$0, Throwable $throwable, Throwable runnerJobCause) {
        Object lock$iv = this$0.stateLock;
        synchronized (lock$iv) {
            Throwable it = null;
            if ($throwable != null) {
                if (runnerJobCause != null) {
                    it = runnerJobCause instanceof CancellationException ? null : runnerJobCause;
                    if (it != null) {
                        kotlin.ExceptionsKt.addSuppressed($throwable, it);
                    }
                }
                it = $throwable;
            }
            this$0.closeCause = it;
            this$0._state.setValue(State.ShutDown);
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public CoroutineContext getEffectCoroutineContext() {
        return this.effectCoroutineContext;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public CoroutineContext getRecomposeCoroutineContext$runtime() {
        return EmptyCoroutineContext.INSTANCE;
    }

    private final boolean getHasBroadcastFrameClockAwaitersLocked() {
        return !this.frameClockPaused && this.broadcastFrameClock.getHasAwaiters();
    }

    private final boolean getHasNextFrameEndAwaitersLocked() {
        return !this.frameClockPaused && this.nextFrameEndCallbackQueue.getHasAwaiters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasBroadcastFrameClockAwaiters() {
        boolean hasBroadcastFrameClockAwaitersLocked;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            hasBroadcastFrameClockAwaitersLocked = getHasBroadcastFrameClockAwaitersLocked();
        }
        return hasBroadcastFrameClockAwaitersLocked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CancellableContinuation<Unit> deriveStateLocked() {
        State newState;
        int i = 0;
        int i2 = 1;
        DefaultConstructorMarker defaultConstructorMarker = null;
        if (this._state.getValue().compareTo(State.ShuttingDown) <= 0) {
            clearKnownCompositionsLocked();
            this.snapshotInvalidations = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
            this.compositionInvalidations.clear();
            this.compositionsAwaitingApply.clear();
            this.movableContentAwaitingInsert.clear();
            this.failedCompositions = null;
            CancellableContinuation<? super Unit> cancellableContinuation = this.workContinuation;
            if (cancellableContinuation != null) {
                CancellableContinuation.DefaultImpls.cancel$default(cancellableContinuation, null, 1, null);
            }
            this.workContinuation = null;
            this.errorState = null;
            return null;
        }
        if (this.errorState != null) {
            newState = State.Inactive;
        } else if (this.runnerJob == null) {
            this.snapshotInvalidations = new MutableScatterSet<>(i, i2, defaultConstructorMarker);
            this.compositionInvalidations.clear();
            if (getHasBroadcastFrameClockAwaitersLocked() || getHasNextFrameEndAwaitersLocked()) {
                newState = State.InactivePendingWork;
            } else {
                newState = State.Inactive;
            }
        } else {
            if ((this.compositionInvalidations.getSize() != 0 ? 1 : 0) != 0 || this.snapshotInvalidations.isNotEmpty() || !this.compositionsAwaitingApply.isEmpty() || !this.movableContentAwaitingInsert.isEmpty() || this.concurrentCompositionsOutstanding > 0 || getHasBroadcastFrameClockAwaitersLocked() || getHasNextFrameEndAwaitersLocked() || MultiValueMap.m4456isNotEmptyimpl(this.movableContentRemoved)) {
                newState = State.PendingWork;
            } else {
                newState = State.Idle;
            }
        }
        this._state.setValue(newState);
        if (newState != State.PendingWork) {
            return null;
        }
        CancellableContinuation cancellableContinuation2 = this.workContinuation;
        this.workContinuation = null;
        return cancellableContinuation2;
    }

    private final void onNewFrameAwaiter() {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
            if (this._state.getValue().compareTo(State.ShuttingDown) <= 0) {
                throw ExceptionsKt.CancellationException("Recomposer shutdown; frame clock awaiter will never resume", this.closeCause);
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m9088constructorimpl(Unit.INSTANCE));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0030  */
    /* JADX WARN: Code duplicated, block: B:28:? A[RETURN, SYNTHETIC] */
    public final boolean getShouldKeepRecomposing() {
        boolean z;
        boolean z2;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            z = this.isClosed;
        }
        if (!z) {
            return true;
        }
        for (Object element$iv : this.effectJob.getChildren()) {
            Job it = (Job) element$iv;
            if (it.isActive()) {
                z2 = true;
                if (z2) {
                    return true;
                }
                return false;
            }
        }
        z2 = false;
        if (z2) {
            return false;
        }
        return true;
    }

    public final Flow<State> getState() {
        return getCurrentState();
    }

    public final StateFlow<State> getCurrentState() {
        return this._state;
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eJ\b\u0010 \u001a\u0004\u0018\u00010!J\u0006\u0010\"\u001a\u00020\u001aR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006#"}, d2 = {"Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/RecomposerInfo;", "<init>", "(Landroidx/compose/runtime/Recomposer;)V", "state", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/compose/runtime/Recomposer$State;", "getState", "()Lkotlinx/coroutines/flow/Flow;", "hasPendingWork", "", "getHasPendingWork", "()Z", "changeCount", "", "getChangeCount", "()J", "currentError", "Landroidx/compose/runtime/RecomposerErrorInfo;", "getCurrentError", "()Landroidx/compose/runtime/RecomposerErrorInfo;", "observe", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "Landroidx/compose/runtime/tooling/CompositionRegistrationObserver;", "invalidateGroupsWithKey", "", "key", "", "saveStateAndDisposeForHotReload", "", "Landroidx/compose/runtime/Recomposer$HotReloadable;", "resetErrorState", "Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "retryFailedCompositions", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private final class RecomposerInfoImpl implements RecomposerInfo {
        public RecomposerInfoImpl() {
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public Flow<State> getState() {
            return Recomposer.this.getCurrentState();
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public boolean getHasPendingWork() {
            return Recomposer.this.getHasPendingWork();
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public long getChangeCount() {
            return Recomposer.this.getChangeCount();
        }

        public final RecomposerErrorInfo getCurrentError() {
            RecomposerErrorState recomposerErrorState;
            Object lock$iv = Recomposer.this.stateLock;
            Recomposer recomposer = Recomposer.this;
            synchronized (lock$iv) {
                recomposerErrorState = recomposer.errorState;
            }
            return recomposerErrorState;
        }

        @Override // androidx.compose.runtime.RecomposerInfo
        public CompositionObserverHandle observe(CompositionRegistrationObserver observer) {
            return CompositionObserverKt.observe(Recomposer.this, observer);
        }

        public final void invalidateGroupsWithKey(int key) throws Throwable {
            List compositions = Recomposer.this.knownCompositions();
            List target$iv = new ArrayList(compositions.size());
            int size = compositions.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                Object item$iv$iv = compositions.get(index$iv$iv);
                ControlledComposition it = (ControlledComposition) item$iv$iv;
                CompositionImpl compositionImpl = it instanceof CompositionImpl ? (CompositionImpl) it : null;
                if (compositionImpl != null) {
                    target$iv.add(compositionImpl);
                }
            }
            List $this$fastForEach$iv = target$iv;
            int size2 = $this$fastForEach$iv.size();
            for (int index$iv = 0; index$iv < size2; index$iv++) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                ((CompositionImpl) item$iv).invalidateGroupsWithKey(key);
            }
        }

        public final List<HotReloadable> saveStateAndDisposeForHotReload() {
            List compositions = Recomposer.this.knownCompositions();
            List target$iv = new ArrayList(compositions.size());
            int size = compositions.size();
            for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                Object item$iv$iv = compositions.get(index$iv$iv);
                ControlledComposition it = (ControlledComposition) item$iv$iv;
                CompositionImpl compositionImpl = it instanceof CompositionImpl ? (CompositionImpl) it : null;
                if (compositionImpl != null) {
                    target$iv.add(compositionImpl);
                }
            }
            List $this$fastMap$iv = target$iv;
            List target$iv2 = new ArrayList($this$fastMap$iv.size());
            int size2 = $this$fastMap$iv.size();
            for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                Object item$iv$iv2 = $this$fastMap$iv.get(index$iv$iv2);
                HotReloadable $this$saveStateAndDisposeForHotReload_u24lambda_u241_u240 = new HotReloadable((CompositionImpl) item$iv$iv2);
                $this$saveStateAndDisposeForHotReload_u24lambda_u241_u240.clearContent();
                target$iv2.add($this$saveStateAndDisposeForHotReload_u24lambda_u241_u240);
            }
            return target$iv2;
        }

        public final RecomposerErrorState resetErrorState() {
            return Recomposer.this.resetErrorState();
        }

        public final void retryFailedCompositions() {
            Recomposer.this.retryFailedCompositions();
        }
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u000e"}, d2 = {"Landroidx/compose/runtime/Recomposer$HotReloadable;", "", "composition", "Landroidx/compose/runtime/CompositionImpl;", "<init>", "(Landroidx/compose/runtime/CompositionImpl;)V", "composable", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/jvm/functions/Function2;", "clearContent", "resetContent", "recompose", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class HotReloadable {
        private Function2<? super Composer, ? super Integer, Unit> composable;
        private final CompositionImpl composition;

        public HotReloadable(CompositionImpl composition) {
            this.composition = composition;
            this.composable = this.composition.getComposable();
        }

        public final void clearContent() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(ComposableSingletons$RecomposerKt.INSTANCE.m4301getLambda$1091980426$runtime());
            }
        }

        public final void resetContent() {
            this.composition.setComposable(this.composable);
        }

        public final void recompose() {
            if (this.composition.getIsRoot()) {
                this.composition.setContent(this.composable);
            }
        }
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/runtime/Recomposer$RecomposerErrorState;", "Landroidx/compose/runtime/RecomposerErrorInfo;", "recoverable", "", "cause", "", "<init>", "(ZLjava/lang/Throwable;)V", "getRecoverable", "()Z", "getCause", "()Ljava/lang/Throwable;", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class RecomposerErrorState implements RecomposerErrorInfo {
        private final Throwable cause;
        private final boolean recoverable;

        public RecomposerErrorState(boolean recoverable, Throwable cause) {
            this.recoverable = recoverable;
            this.cause = cause;
        }

        @Override // androidx.compose.runtime.RecomposerErrorInfo
        public boolean getRecoverable() {
            return this.recoverable;
        }

        @Override // androidx.compose.runtime.RecomposerErrorInfo
        public Throwable getCause() {
            return this.cause;
        }
    }

    public final RecomposerInfo asRecomposerInfo() {
        return this.recomposerInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean recordComposerModifications() {
        boolean hasFrameWorkLocked;
        CollectionsKt.emptyList();
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            if (this.snapshotInvalidations.isEmpty()) {
                return getHasFrameWorkLocked();
            }
            List<ControlledComposition> listKnownCompositionsLocked = knownCompositionsLocked();
            Set<? extends Object> setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(this.snapshotInvalidations);
            this.snapshotInvalidations = new MutableScatterSet<>(0, 1, null);
            try {
                Recomposer $this$recordComposerModifications_u24lambda_u241 = this;
                int size = listKnownCompositionsLocked.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = listKnownCompositionsLocked.get(index$iv);
                    ControlledComposition composition = (ControlledComposition) item$iv;
                    composition.recordModificationsOf(setWrapIntoSet);
                    if ($this$recordComposerModifications_u24lambda_u241._state.getValue().compareTo(State.ShuttingDown) <= 0) {
                        break;
                    }
                }
                Object lock$iv2 = this.stateLock;
                synchronized (lock$iv2) {
                    if (deriveStateLocked() != null) {
                        throw new IllegalStateException("called outside of runRecomposeAndApplyChanges".toString());
                    }
                    hasFrameWorkLocked = getHasFrameWorkLocked();
                }
                return hasFrameWorkLocked;
            } catch (Throwable th) {
                synchronized (this.stateLock) {
                    this.snapshotInvalidations.addAll(setWrapIntoSet);
                    throw th;
                }
            }
        }
    }

    private final void recordComposerModifications(Function1<? super ControlledComposition, Unit> onEachInvalidComposition) {
        MutableScatterSet it;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            it = this.snapshotInvalidations;
            if (it.isNotEmpty()) {
                this.snapshotInvalidations = new MutableScatterSet(0, 1, null);
            }
        }
        Set<? extends Object> setWrapIntoSet = ScatterSetWrapperKt.wrapIntoSet(it);
        if (!setWrapIntoSet.isEmpty()) {
            List $this$fastForEach$iv = knownCompositionsLocked();
            int size = $this$fastForEach$iv.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                ControlledComposition composition = (ControlledComposition) item$iv;
                composition.recordModificationsOf(setWrapIntoSet);
            }
        }
        MutableVector this_$iv = this.compositionInvalidations;
        Object[] content$iv = this_$iv.content;
        int size$iv = this_$iv.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            onEachInvalidComposition.invoke(content$iv[i$iv]);
        }
        this.compositionInvalidations.clear();
        Object lock$iv2 = this.stateLock;
        synchronized (lock$iv2) {
            if (deriveStateLocked() != null) {
                throw new IllegalStateException("called outside of runRecomposeAndApplyChanges".toString());
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerRunnerJob(Job callingJob) {
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            Throwable it = this.closeCause;
            if (it != null) {
                throw it;
            }
            if (this._state.getValue().compareTo(State.ShuttingDown) <= 0) {
                throw new IllegalStateException("Recomposer shut down".toString());
            }
            if (this.runnerJob != null) {
                throw new IllegalStateException("Recomposer already running".toString());
            }
            this.runnerJob = callingJob;
            deriveStateLocked();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeAndApplyChanges$2", f = "Recomposer.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {598, TypedValues.MotionType.TYPE_POLAR_RELATIVETO}, m = "invokeSuspend", n = {"parentFrameClock", "toRecompose", "toInsert", "toApply", "toLateApply", "toComplete", "modifiedValues", "modifiedValuesSet", "alreadyComposed", "parentFrameClock", "toRecompose", "toInsert", "toApply", "toLateApply", "toComplete", "modifiedValues", "modifiedValuesSet", "alreadyComposed"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 1)
    static final class C03442 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;

        C03442(Continuation<? super C03442> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
            C03442 c03442 = Recomposer.this.new C03442(continuation);
            c03442.L$0 = monotonicFrameClock;
            return c03442.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x00c2  */
        /* JADX WARN: Code duplicated, block: B:13:0x00e1 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:14:0x00e2  */
        /* JADX WARN: Code duplicated, block: B:17:0x00f3  */
        /* JADX WARN: Code duplicated, block: B:19:0x0118 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:20:0x0119  */
        /* JADX WARN: Code duplicated, block: B:22:0x0132  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0119 -> B:21:0x0122). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0132 -> B:9:0x00ba). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instruction units count: 332
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C03442.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX WARN: Code duplicated, block: B:51:0x0127 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:52:0x0129 A[Catch: all -> 0x01be, LOOP:3: B:38:0x00de->B:52:0x0129, LOOP_END, TryCatch #1 {all -> 0x01be, blocks: (B:25:0x008e, B:27:0x00a5, B:33:0x00b5, B:36:0x00c7, B:38:0x00de, B:40:0x00f2, B:42:0x00fe, B:47:0x010c, B:48:0x011e, B:52:0x0129, B:54:0x012f, B:56:0x0149, B:58:0x015b, B:60:0x0167, B:65:0x0174, B:67:0x0190, B:73:0x01a5, B:76:0x01b5), top: B:87:0x008e }] */
        /* JADX WARN: Code duplicated, block: B:98:0x012f A[EDGE_INSN: B:98:0x012f->B:54:0x012f BREAK  A[LOOP:3: B:38:0x00de->B:52:0x0129], SYNTHETIC] */
        private static final void invokeSuspend$clearRecompositionState(Recomposer this$0, List<ControlledComposition> list, List<MovableContentStateReference> list2, List<ControlledComposition> list3, MutableScatterSet<ControlledComposition> mutableScatterSet, MutableScatterSet<ControlledComposition> mutableScatterSet2, MutableScatterSet<Object> mutableScatterSet3, MutableScatterSet<ControlledComposition> mutableScatterSet4) throws Throwable {
            long j;
            long j2;
            int i;
            long slot$iv$iv;
            Object lock$iv = this$0.stateLock;
            int $i$f$synchronized = 0;
            synchronized (lock$iv) {
                int i2 = 0;
                try {
                    list.clear();
                    list2.clear();
                    int size = list3.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        try {
                            Object item$iv = list3.get(index$iv);
                            ControlledComposition it = (ControlledComposition) item$iv;
                            it.abandonChanges();
                            this$0.recordFailedCompositionLocked(it);
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    }
                    list3.clear();
                    MutableScatterSet<ControlledComposition> this_$iv = mutableScatterSet;
                    Object[] elements$iv = this_$iv.elements;
                    long[] m$iv$iv = this_$iv.metadata;
                    int lastIndex$iv$iv = m$iv$iv.length - 2;
                    int i$iv$iv = 0;
                    long j3 = 255;
                    int i3 = 8;
                    if (0 <= lastIndex$iv$iv) {
                        while (true) {
                            long slot$iv$iv2 = m$iv$iv[i$iv$iv];
                            j2 = 128;
                            int $i$f$synchronized2 = $i$f$synchronized;
                            ScatterSet this_$iv2 = this_$iv;
                            if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) != -9187201950435737472L) {
                                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                int j$iv$iv = 0;
                                while (j$iv$iv < bitCount$iv$iv) {
                                    long value$iv$iv$iv = slot$iv$iv2 & j3;
                                    if (value$iv$iv$iv < 128) {
                                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                        try {
                                            ControlledComposition it2 = (ControlledComposition) elements$iv[index$iv$iv];
                                            it2.abandonChanges();
                                            this$0.recordFailedCompositionLocked(it2);
                                        } catch (Throwable th2) {
                                            th = th2;
                                            throw th;
                                        }
                                    }
                                    slot$iv$iv2 >>= 8;
                                    j$iv$iv++;
                                    j3 = j3;
                                }
                                j = j3;
                                if (bitCount$iv$iv != 8) {
                                    break;
                                }
                            } else {
                                j = j3;
                            }
                            if (i$iv$iv == lastIndex$iv$iv) {
                                break;
                            }
                            i$iv$iv++;
                            $i$f$synchronized = $i$f$synchronized2;
                            this_$iv = this_$iv2;
                            j3 = j;
                        }
                    } else {
                        j = 255;
                        j2 = 128;
                    }
                    mutableScatterSet.clear();
                    MutableScatterSet<ControlledComposition> this_$iv3 = mutableScatterSet2;
                    Object[] elements$iv2 = this_$iv3.elements;
                    long[] m$iv$iv2 = this_$iv3.metadata;
                    int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
                    int i$iv$iv2 = 0;
                    if (0 <= lastIndex$iv$iv2) {
                        while (true) {
                            long slot$iv$iv3 = m$iv$iv2[i$iv$iv2];
                            int i4 = i3;
                            if ((slot$iv$iv3 & ((~slot$iv$iv3) << 7) & (-9187201950435737472L)) != -9187201950435737472L) {
                                int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                                for (int j$iv$iv2 = 0; j$iv$iv2 < bitCount$iv$iv2; j$iv$iv2++) {
                                    long value$iv$iv$iv2 = slot$iv$iv & j;
                                    if (value$iv$iv$iv2 < j2) {
                                        slot$iv$iv = slot$iv$iv3;
                                        slot$iv$iv = slot$iv$iv3;
                                        int index$iv$iv2 = (i$iv$iv2 << 3) + j$iv$iv2;
                                        ((ControlledComposition) elements$iv2[index$iv$iv2]).changesApplied();
                                    } else {
                                        slot$iv$iv = slot$iv$iv3;
                                        slot$iv$iv = slot$iv$iv3;
                                    }
                                    slot$iv$iv >>= i4;
                                }
                                slot$iv$iv = slot$iv$iv3;
                                if (bitCount$iv$iv2 != i4) {
                                    break;
                                }
                                if (i$iv$iv2 != lastIndex$iv$iv2) {
                                    break;
                                }
                                i$iv$iv2++;
                                i3 = 8;
                            } else if (i$iv$iv2 != lastIndex$iv$iv2) {
                                break;
                                break;
                            } else {
                                i$iv$iv2++;
                                i3 = 8;
                            }
                        }
                    }
                    mutableScatterSet2.clear();
                    mutableScatterSet3.clear();
                    MutableScatterSet<ControlledComposition> this_$iv4 = mutableScatterSet4;
                    int $i$f$forEach = 0;
                    Object[] elements$iv3 = this_$iv4.elements;
                    long[] m$iv$iv3 = this_$iv4.metadata;
                    int lastIndex$iv$iv3 = m$iv$iv3.length - 2;
                    int i$iv$iv3 = 0;
                    if (0 <= lastIndex$iv$iv3) {
                        while (true) {
                            long slot$iv$iv4 = m$iv$iv3[i$iv$iv3];
                            ScatterSet this_$iv5 = this_$iv4;
                            int $i$f$forEach2 = $i$f$forEach;
                            if ((((~slot$iv$iv4) << 7) & slot$iv$iv4 & (-9187201950435737472L)) != -9187201950435737472L) {
                                int bitCount$iv$iv3 = 8 - ((~(i$iv$iv3 - lastIndex$iv$iv3)) >>> 31);
                                int j$iv$iv3 = 0;
                                while (j$iv$iv3 < bitCount$iv$iv3) {
                                    long value$iv$iv$iv3 = slot$iv$iv4 & j;
                                    if (value$iv$iv$iv3 < j2) {
                                        int index$iv$iv3 = (i$iv$iv3 << 3) + j$iv$iv3;
                                        ControlledComposition it3 = (ControlledComposition) elements$iv3[index$iv$iv3];
                                        it3.abandonChanges();
                                        this$0.recordFailedCompositionLocked(it3);
                                    }
                                    slot$iv$iv4 >>= 8;
                                    j$iv$iv3++;
                                    i2 = i2;
                                }
                                i = i2;
                                if (bitCount$iv$iv3 != 8) {
                                    break;
                                }
                            } else {
                                i = i2;
                            }
                            if (i$iv$iv3 == lastIndex$iv$iv3) {
                                break;
                            }
                            i$iv$iv3++;
                            this_$iv4 = this_$iv5;
                            $i$f$forEach = $i$f$forEach2;
                            i2 = i;
                        }
                    }
                    mutableScatterSet4.clear();
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        }

        private static final void invokeSuspend$fillToInsert(List<MovableContentStateReference> list, Recomposer this$0) {
            list.clear();
            Object lock$iv = this$0.stateLock;
            synchronized (lock$iv) {
                List $this$fastForEach$iv = this$0.movableContentAwaitingInsert;
                int size = $this$fastForEach$iv.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = $this$fastForEach$iv.get(index$iv);
                    MovableContentStateReference it = (MovableContentStateReference) item$iv;
                    list.add(it);
                }
                this$0.movableContentAwaitingInsert.clear();
                Unit unit = Unit.INSTANCE;
            }
        }

        /* JADX WARN: Code duplicated, block: B:213:0x0471 A[Catch: all -> 0x05a1, TryCatch #1 {all -> 0x05a1, blocks: (B:17:0x0046, B:18:0x004f, B:271:0x059f, B:272:0x05a0, B:26:0x008a, B:27:0x008b, B:28:0x0091, B:205:0x0454, B:207:0x045f, B:242:0x051d, B:252:0x0549, B:213:0x0471, B:214:0x0477, B:258:0x056d, B:259:0x056e, B:241:0x051c, B:263:0x0590, B:265:0x0596, B:266:0x0599, B:36:0x00c4, B:167:0x038e, B:168:0x0399, B:171:0x039e, B:172:0x039f, B:176:0x03bc, B:177:0x03bd, B:190:0x03f0, B:191:0x03f3, B:151:0x0359, B:107:0x027a, B:196:0x0410, B:198:0x0419, B:200:0x0428, B:202:0x0443, B:118:0x02a6, B:120:0x02b9, B:122:0x02d2, B:124:0x02de, B:129:0x02ea, B:170:0x039b, B:244:0x0524, B:245:0x0527, B:247:0x0530, B:262:0x0577), top: B:277:0x0046, inners: #14, #15, #20, #24, #28 }] */
        /* JADX WARN: Code duplicated, block: B:215:0x0478  */
        /* JADX WARN: Code duplicated, block: B:218:0x048b A[Catch: all -> 0x056a, TRY_LEAVE, TryCatch #12 {all -> 0x056a, blocks: (B:216:0x0479, B:218:0x048b), top: B:298:0x0479 }] */
        /* JADX WARN: Code duplicated, block: B:220:0x04a2  */
        /* JADX WARN: Code duplicated, block: B:223:0x04ac A[Catch: all -> 0x0568, TryCatch #9 {all -> 0x0568, blocks: (B:221:0x04a6, B:223:0x04ac, B:225:0x04b5, B:226:0x04be, B:228:0x04ce, B:230:0x04de, B:232:0x04e4, B:236:0x04f1, B:239:0x0502, B:238:0x04f6, B:240:0x0509), top: B:292:0x04a6 }] */
        /* JADX WARN: Code duplicated, block: B:224:0x04b3  */
        /* JADX WARN: Code duplicated, block: B:228:0x04ce A[Catch: all -> 0x0568, TryCatch #9 {all -> 0x0568, blocks: (B:221:0x04a6, B:223:0x04ac, B:225:0x04b5, B:226:0x04be, B:228:0x04ce, B:230:0x04de, B:232:0x04e4, B:236:0x04f1, B:239:0x0502, B:238:0x04f6, B:240:0x0509), top: B:292:0x04a6 }] */
        /* JADX WARN: Code duplicated, block: B:233:0x04ed  */
        /* JADX WARN: Code duplicated, block: B:236:0x04f1 A[Catch: all -> 0x0568, TryCatch #9 {all -> 0x0568, blocks: (B:221:0x04a6, B:223:0x04ac, B:225:0x04b5, B:226:0x04be, B:228:0x04ce, B:230:0x04de, B:232:0x04e4, B:236:0x04f1, B:239:0x0502, B:238:0x04f6, B:240:0x0509), top: B:292:0x04a6 }] */
        /* JADX WARN: Code duplicated, block: B:237:0x04f4 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:238:0x04f6 A[Catch: all -> 0x0568, TryCatch #9 {all -> 0x0568, blocks: (B:221:0x04a6, B:223:0x04ac, B:225:0x04b5, B:226:0x04be, B:228:0x04ce, B:230:0x04de, B:232:0x04e4, B:236:0x04f1, B:239:0x0502, B:238:0x04f6, B:240:0x0509), top: B:292:0x04a6 }] */
        /* JADX WARN: Code duplicated, block: B:343:0x04b5 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:348:0x0502 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:355:0x024e A[EDGE_INSN: B:355:0x024e->B:100:0x024e BREAK  A[LOOP:8: B:82:0x01f4->B:96:0x023f], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:95:0x023d A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:96:0x023f A[LOOP:8: B:82:0x01f4->B:96:0x023f, LOOP_END] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r10v1 */
        /* JADX WARN: Type inference failed for: r10v11 */
        /* JADX WARN: Type inference failed for: r10v14 */
        /* JADX WARN: Type inference failed for: r10v16 */
        /* JADX WARN: Type inference failed for: r10v17 */
        /* JADX WARN: Type inference failed for: r10v18 */
        /* JADX WARN: Type inference failed for: r10v19 */
        /* JADX WARN: Type inference failed for: r10v2, types: [androidx.compose.runtime.snapshots.Snapshot] */
        /* JADX WARN: Type inference failed for: r10v3 */
        /* JADX WARN: Type inference failed for: r10v5 */
        /* JADX WARN: Type inference failed for: r10v7 */
        /* JADX WARN: Type inference failed for: r2v44, types: [T[], java.lang.Object[]] */
        /* JADX WARN: Type inference failed for: r2v48, types: [T[]] */
        /* JADX WARN: Type inference failed for: r48v0, types: [long] */
        /* JADX WARN: Type inference failed for: r5v39, types: [androidx.compose.runtime.BroadcastFrameClock] */
        /* JADX WARN: Type inference failed for: r9v0 */
        /* JADX WARN: Type inference failed for: r9v1 */
        /* JADX WARN: Type inference failed for: r9v2 */
        /* JADX WARN: Type inference failed for: r9v25, types: [long] */
        /* JADX WARN: Type inference failed for: r9v26 */
        /* JADX WARN: Type inference failed for: r9v3 */
        /* JADX WARN: Type inference failed for: r9v4, types: [androidx.compose.runtime.snapshots.Snapshot] */
        /* JADX WARN: Type inference failed for: r9v5 */
        /* JADX WARN: Type inference failed for: r9v7 */
        /* JADX WARN: Type inference failed for: r9v9 */
        static final Unit invokeSuspend$lambda$2(Recomposer recomposer, MutableScatterSet mutableScatterSet, MutableScatterSet mutableScatterSet2, List list, List list2, MutableScatterSet mutableScatterSet3, List list3, MutableScatterSet mutableScatterSet4, Set set, long j) throws Throwable {
            Snapshot snapshotMakeCurrent;
            Unit unit;
            long j2;
            long j3;
            char c;
            Object obj;
            Object obj2;
            List listKnownCompositionsLocked;
            int i;
            int size;
            MutableVector mutableVector;
            int i2;
            int i3;
            int size2;
            int i4;
            ControlledComposition controlledComposition;
            boolean z;
            ControlledComposition controlledComposition2;
            Recomposer recomposer2 = recomposer;
            mutableScatterSet4 = mutableScatterSet4;
            if (recomposer2.getHasBroadcastFrameClockAwaiters()) {
                Object objBeginSection = Trace.INSTANCE.beginSection("Recomposer:animation");
                try {
                    ?? r9 = j;
                    try {
                        recomposer2.broadcastFrameClock.sendFrame(r9);
                        Snapshot.INSTANCE.sendApplyNotifications();
                        Unit unit2 = Unit.INSTANCE;
                        Trace.INSTANCE.endSection(objBeginSection);
                        j = r9;
                    } catch (Throwable th) {
                        th = th;
                        Trace.INSTANCE.endSection(objBeginSection);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                j = j;
            }
            Object objBeginSection2 = Trace.INSTANCE.beginSection("Recomposer:recompose");
            try {
                recomposer2.recordComposerModifications();
                int i5 = 0;
                synchronized (recomposer2.stateLock) {
                    int i6 = 0;
                    try {
                        MutableVector mutableVector2 = recomposer2.compositionInvalidations;
                        int i7 = 0;
                        Object[] objArr = mutableVector2.content;
                        int size3 = mutableVector2.getSize();
                        while (i7 < size3) {
                            int i8 = i6;
                            int i9 = i5;
                            try {
                                list.add((ControlledComposition) objArr[i7]);
                                i7++;
                                i6 = i8;
                                i5 = i9;
                            } catch (Throwable th3) {
                                th = th3;
                                throw th;
                            }
                        }
                        recomposer2.compositionInvalidations.clear();
                        Unit unit3 = Unit.INSTANCE;
                        mutableScatterSet.clear();
                        mutableScatterSet2.clear();
                        while (true) {
                            if (list.isEmpty()) {
                                try {
                                    if (list2.isEmpty()) {
                                        break;
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    Trace.INSTANCE.endSection(objBeginSection2);
                                    throw th;
                                }
                            }
                            List list4 = list;
                            int i10 = 0;
                            int i11 = 0;
                            try {
                                int size4 = list4.size();
                                ?? r10 = snapshotMakeCurrent;
                                while (i11 < size4) {
                                    Object obj3 = list4.get(i11);
                                    ControlledComposition controlledComposition3 = (ControlledComposition) obj3;
                                    ControlledComposition controlledCompositionPerformRecompose = recomposer2.performRecompose(controlledComposition3, mutableScatterSet);
                                    if (controlledCompositionPerformRecompose != null) {
                                        list3.add(controlledCompositionPerformRecompose);
                                        Unit unit4 = Unit.INSTANCE;
                                        Unit unit5 = Unit.INSTANCE;
                                    }
                                    mutableScatterSet2.add(controlledComposition3);
                                    i11++;
                                    list4 = list4;
                                    i10 = i10;
                                    r10 = obj3;
                                }
                                list.clear();
                                if (mutableScatterSet.isNotEmpty()) {
                                    obj = recomposer2.stateLock;
                                    synchronized (obj) {
                                        try {
                                            listKnownCompositionsLocked = recomposer2.knownCompositionsLocked();
                                            i = 0;
                                            size = listKnownCompositionsLocked.size();
                                            while (i < size) {
                                                controlledComposition2 = (ControlledComposition) listKnownCompositionsLocked.get(i);
                                                int i12 = size;
                                                if (mutableScatterSet2.contains(controlledComposition2)) {
                                                    obj2 = obj;
                                                } else {
                                                    obj2 = obj;
                                                    try {
                                                        if (controlledComposition2.observesAnyOf(set)) {
                                                            list.add(controlledComposition2);
                                                        }
                                                    } catch (Throwable th5) {
                                                        th = th5;
                                                        throw th;
                                                    }
                                                }
                                                i++;
                                                obj = obj2;
                                                size = i12;
                                            }
                                            Object obj4 = obj;
                                            mutableVector = recomposer2.compositionInvalidations;
                                            i2 = 0;
                                            i3 = 0;
                                            size2 = mutableVector.getSize();
                                            i4 = 0;
                                            while (i4 < size2) {
                                                int i13 = i2;
                                                controlledComposition = (ControlledComposition) mutableVector.content[i4];
                                                if (!mutableScatterSet2.contains(controlledComposition) || list.contains(controlledComposition)) {
                                                    z = false;
                                                } else {
                                                    list.add(controlledComposition);
                                                    z = true;
                                                }
                                                if (z) {
                                                    i3++;
                                                } else if (i3 > 0) {
                                                    mutableVector.content[i4 - i3] = mutableVector.content[i4];
                                                }
                                                i4++;
                                                list = list;
                                                i2 = i13;
                                            }
                                            ArraysKt.fill((Object[]) mutableVector.content, (Object) null, size2 - i3, size2);
                                            mutableVector.setSize(size2 - i3);
                                            Unit unit6 = Unit.INSTANCE;
                                            snapshotMakeCurrent = size2;
                                        } catch (Throwable th6) {
                                            th = th6;
                                            obj2 = obj;
                                        }
                                    }
                                } else if (recomposer2.compositionInvalidations.getSize() != 0) {
                                    obj = recomposer2.stateLock;
                                    synchronized (obj) {
                                        listKnownCompositionsLocked = recomposer2.knownCompositionsLocked();
                                        i = 0;
                                        size = listKnownCompositionsLocked.size();
                                        while (i < size) {
                                            controlledComposition2 = (ControlledComposition) listKnownCompositionsLocked.get(i);
                                            int i14 = size;
                                            if (mutableScatterSet2.contains(controlledComposition2)) {
                                                obj2 = obj;
                                                if (controlledComposition2.observesAnyOf(set)) {
                                                    list.add(controlledComposition2);
                                                }
                                            } else {
                                                obj2 = obj;
                                            }
                                            i++;
                                            obj = obj2;
                                            size = i14;
                                        }
                                        Object obj5 = obj;
                                        mutableVector = recomposer2.compositionInvalidations;
                                        i2 = 0;
                                        i3 = 0;
                                        size2 = mutableVector.getSize();
                                        i4 = 0;
                                        while (i4 < size2) {
                                            int i15 = i2;
                                            controlledComposition = (ControlledComposition) mutableVector.content[i4];
                                            if (mutableScatterSet2.contains(controlledComposition)) {
                                                z = false;
                                            } else {
                                                z = false;
                                            }
                                            if (z) {
                                                i3++;
                                            } else if (i3 > 0) {
                                                mutableVector.content[i4 - i3] = mutableVector.content[i4];
                                            }
                                            i4++;
                                            list = list;
                                            i2 = i15;
                                        }
                                        ArraysKt.fill((Object[]) mutableVector.content, (Object) null, size2 - i3, size2);
                                        mutableVector.setSize(size2 - i3);
                                        Unit unit7 = Unit.INSTANCE;
                                        snapshotMakeCurrent = size2;
                                    }
                                } else {
                                    snapshotMakeCurrent = r10;
                                }
                                if (list.isEmpty()) {
                                    try {
                                        invokeSuspend$fillToInsert(list2, recomposer2);
                                        while (!list2.isEmpty()) {
                                            mutableScatterSet3.plusAssign((Iterable) recomposer2.performInsertValues(list2, mutableScatterSet));
                                            invokeSuspend$fillToInsert(list2, recomposer2);
                                        }
                                    } catch (Throwable th7) {
                                        Recomposer.processCompositionError$default(recomposer2, th7, null, true, 2, null);
                                        invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                        unit = Unit.INSTANCE;
                                        Trace.INSTANCE.endSection(objBeginSection2);
                                        return unit;
                                    }
                                } else {
                                    recomposer2 = recomposer;
                                }
                            } catch (Throwable th8) {
                                try {
                                    Recomposer.processCompositionError$default(recomposer, th8, null, true, 2, null);
                                    invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                    unit = Unit.INSTANCE;
                                    list.clear();
                                } catch (Throwable th9) {
                                    list.clear();
                                    throw th9;
                                }
                            }
                        }
                        Snapshot current = Snapshot.INSTANCE.getCurrent();
                        TransparentObserverSnapshot transparentObserverMutableSnapshot = current instanceof MutableSnapshot ? new TransparentObserverMutableSnapshot((MutableSnapshot) current, null, null, true, false) : new TransparentObserverSnapshot(current, null, true, false);
                        j = transparentObserverMutableSnapshot;
                        try {
                            snapshotMakeCurrent = j.makeCurrent();
                            try {
                                try {
                                    if (!list3.isEmpty()) {
                                        try {
                                            recomposer2.changeCount = recomposer2.getChangeCount() + 1;
                                            try {
                                                try {
                                                    int i16 = 0;
                                                    for (int size5 = list3.size(); i16 < size5; size5 = size5) {
                                                        mutableScatterSet4.add((ControlledComposition) list3.get(i16));
                                                        i16++;
                                                    }
                                                    int size6 = list3.size();
                                                    for (int i17 = 0; i17 < size6; i17++) {
                                                        ((ControlledComposition) list3.get(i17)).applyChanges();
                                                    }
                                                    try {
                                                        list3.clear();
                                                    } catch (Throwable th10) {
                                                        th = th10;
                                                        j = j;
                                                        snapshotMakeCurrent = snapshotMakeCurrent;
                                                        j.restoreCurrent(snapshotMakeCurrent);
                                                        throw th;
                                                    }
                                                } catch (Throwable th11) {
                                                    th = th11;
                                                }
                                            } catch (Throwable th12) {
                                                j = j;
                                                snapshotMakeCurrent = snapshotMakeCurrent;
                                                try {
                                                    Recomposer.processCompositionError$default(recomposer2, th12, null, false, 6, null);
                                                    current = current;
                                                    try {
                                                        invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                                        try {
                                                            unit = Unit.INSTANCE;
                                                            list3.clear();
                                                            try {
                                                                j.restoreCurrent(snapshotMakeCurrent);
                                                                transparentObserverMutableSnapshot.dispose();
                                                                Trace.INSTANCE.endSection(objBeginSection2);
                                                                return unit;
                                                            } catch (Throwable th13) {
                                                                th = th13;
                                                                transparentObserverMutableSnapshot.dispose();
                                                                throw th;
                                                            }
                                                        } catch (Throwable th14) {
                                                            th = th14;
                                                            list3.clear();
                                                            throw th;
                                                        }
                                                    } catch (Throwable th15) {
                                                        th = th15;
                                                    }
                                                } catch (Throwable th16) {
                                                    th = th16;
                                                }
                                            }
                                        } catch (Throwable th17) {
                                            th = th17;
                                            j = j;
                                            snapshotMakeCurrent = snapshotMakeCurrent;
                                        }
                                    }
                                    if (mutableScatterSet3.isNotEmpty()) {
                                        try {
                                            mutableScatterSet4.plusAssign((ScatterSet) mutableScatterSet3);
                                            MutableScatterSet mutableScatterSet5 = mutableScatterSet3;
                                            j2 = 128;
                                            Object[] objArr2 = mutableScatterSet5.elements;
                                            j3 = 255;
                                            long[] jArr = mutableScatterSet5.metadata;
                                            int length = jArr.length - 2;
                                            c = 7;
                                            int i18 = 0;
                                            if (0 <= length) {
                                                while (true) {
                                                    long j4 = jArr[i18];
                                                    MutableScatterSet mutableScatterSet6 = mutableScatterSet5;
                                                    Object[] objArr3 = objArr2;
                                                    if ((((~j4) << 7) & j4 & (-9187201950435737472L)) == -9187201950435737472L) {
                                                        if (i18 != length) {
                                                            break;
                                                            break;
                                                        }
                                                        i18++;
                                                        mutableScatterSet5 = mutableScatterSet6;
                                                        objArr2 = objArr3;
                                                    } else {
                                                        int i19 = 8 - ((~(i18 - length)) >>> 31);
                                                        for (int i20 = 0; i20 < i19; i20++) {
                                                            if (((j4 & 255) < 128 ? 1 : 0) != 0) {
                                                                ((ControlledComposition) objArr3[(i18 << 3) + i20]).applyLateChanges();
                                                            }
                                                            j4 >>= 8;
                                                        }
                                                        if (i19 != 8) {
                                                            break;
                                                        }
                                                        if (i18 != length) {
                                                            break;
                                                        }
                                                        i18++;
                                                        mutableScatterSet5 = mutableScatterSet6;
                                                        objArr2 = objArr3;
                                                    }
                                                }
                                            }
                                            mutableScatterSet3.clear();
                                        } catch (Throwable th18) {
                                            try {
                                                Recomposer.processCompositionError$default(recomposer, th18, null, false, 6, null);
                                                invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                                unit = Unit.INSTANCE;
                                                mutableScatterSet3.clear();
                                                j.restoreCurrent(snapshotMakeCurrent);
                                                transparentObserverMutableSnapshot.dispose();
                                                Trace.INSTANCE.endSection(objBeginSection2);
                                                return unit;
                                            } catch (Throwable th19) {
                                                mutableScatterSet3.clear();
                                                throw th19;
                                            }
                                        }
                                    } else {
                                        j2 = 128;
                                        j3 = 255;
                                        c = 7;
                                    }
                                    try {
                                        if (mutableScatterSet4.isNotEmpty()) {
                                            try {
                                                MutableScatterSet mutableScatterSet7 = mutableScatterSet4;
                                                int i21 = 0;
                                                Object[] objArr4 = mutableScatterSet7.elements;
                                                MutableScatterSet mutableScatterSet8 = mutableScatterSet7;
                                                long[] jArr2 = mutableScatterSet8.metadata;
                                                int length2 = jArr2.length - 2;
                                                int i22 = 0;
                                                if (0 <= length2) {
                                                    while (true) {
                                                        long j5 = jArr2[i22];
                                                        MutableScatterSet mutableScatterSet9 = mutableScatterSet7;
                                                        int i23 = i21;
                                                        Object[] objArr5 = objArr4;
                                                        MutableScatterSet mutableScatterSet10 = mutableScatterSet8;
                                                        if ((((~j5) << c) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                            int i24 = 8 - ((~(i22 - length2)) >>> 31);
                                                            for (int i25 = 0; i25 < i24; i25++) {
                                                                if ((j5 & j3) < j2) {
                                                                    ((ControlledComposition) objArr5[(i22 << 3) + i25]).changesApplied();
                                                                }
                                                                j5 >>= 8;
                                                            }
                                                            if (i24 != 8) {
                                                                break;
                                                            }
                                                        }
                                                        if (i22 == length2) {
                                                            break;
                                                        }
                                                        i22++;
                                                        mutableScatterSet7 = mutableScatterSet9;
                                                        objArr4 = objArr5;
                                                        mutableScatterSet8 = mutableScatterSet10;
                                                        i21 = i23;
                                                    }
                                                }
                                                mutableScatterSet4.clear();
                                            } catch (Throwable th20) {
                                                try {
                                                    Recomposer.processCompositionError$default(recomposer, th20, null, false, 6, null);
                                                    try {
                                                        invokeSuspend$clearRecompositionState(recomposer, list, list2, list3, mutableScatterSet3, mutableScatterSet4, mutableScatterSet, mutableScatterSet2);
                                                        try {
                                                            Unit unit8 = Unit.INSTANCE;
                                                            mutableScatterSet4.clear();
                                                            try {
                                                                j.restoreCurrent(snapshotMakeCurrent);
                                                                transparentObserverMutableSnapshot.dispose();
                                                                Trace.INSTANCE.endSection(objBeginSection2);
                                                                return unit8;
                                                            } catch (Throwable th21) {
                                                                th = th21;
                                                                transparentObserverMutableSnapshot.dispose();
                                                                throw th;
                                                            }
                                                        } catch (Throwable th22) {
                                                            th = th22;
                                                            mutableScatterSet4.clear();
                                                            throw th;
                                                        }
                                                    } catch (Throwable th23) {
                                                        th = th23;
                                                    }
                                                } catch (Throwable th24) {
                                                    th = th24;
                                                }
                                            }
                                        }
                                        Unit unit9 = Unit.INSTANCE;
                                        j.restoreCurrent(snapshotMakeCurrent);
                                        transparentObserverMutableSnapshot.dispose();
                                        synchronized (recomposer.stateLock) {
                                            recomposer.deriveStateLocked();
                                        }
                                        Snapshot.INSTANCE.notifyObjectsInitialized();
                                        mutableScatterSet2.clear();
                                        mutableScatterSet.clear();
                                        recomposer.compositionsRemoved = null;
                                        Unit unit10 = Unit.INSTANCE;
                                        Trace.INSTANCE.endSection(objBeginSection2);
                                        return Unit.INSTANCE;
                                    } catch (Throwable th25) {
                                        th = th25;
                                        j.restoreCurrent(snapshotMakeCurrent);
                                        throw th;
                                    }
                                } catch (Throwable th26) {
                                    th = th26;
                                    j = j;
                                    snapshotMakeCurrent = snapshotMakeCurrent;
                                }
                            } catch (Throwable th27) {
                                th = th27;
                            }
                        } catch (Throwable th28) {
                            th = th28;
                        }
                    } catch (Throwable th29) {
                        th = th29;
                    }
                }
            } catch (Throwable th30) {
                th = th30;
            }
        }
    }

    public final Object runRecomposeAndApplyChanges(Continuation<? super Unit> continuation) {
        Object objRecompositionRunner = recompositionRunner(new C03442(null), continuation);
        return objRecompositionRunner == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRecompositionRunner : Unit.INSTANCE;
    }

    static /* synthetic */ void processCompositionError$default(Recomposer recomposer, Throwable th, ControlledComposition controlledComposition, boolean z, int i, Object obj) throws Throwable {
        if ((i & 2) != 0) {
            controlledComposition = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        recomposer.processCompositionError(th, controlledComposition, z);
    }

    private final void processCompositionError(Throwable e, ControlledComposition failedInitialComposition, boolean recoverable) throws Throwable {
        int i = 0;
        if (_hotReloadEnabled.get().booleanValue() && !(e instanceof ComposeRuntimeError)) {
            Object lock$iv = this.stateLock;
            synchronized (lock$iv) {
                Utils_androidKt.logError("Error was captured in composition while live edit was enabled.", e);
                this.compositionsAwaitingApply.clear();
                this.compositionInvalidations.clear();
                this.snapshotInvalidations = new MutableScatterSet<>(i, 1, null);
                this.movableContentAwaitingInsert.clear();
                MultiValueMap.m4445clearimpl(this.movableContentRemoved);
                this.movableContentStatesAvailable.clear();
                this.errorState = new RecomposerErrorState(recoverable, e);
                if (failedInitialComposition != null) {
                    recordFailedCompositionLocked(failedInitialComposition);
                }
                deriveStateLocked();
            }
            return;
        }
        Object lock$iv2 = this.stateLock;
        synchronized (lock$iv2) {
            Utils_androidKt.logError("Error was captured in composition.", e);
            RecomposerErrorState errorState = this.errorState;
            if (errorState == null) {
                this.errorState = new RecomposerErrorState(false, e);
                Unit unit = Unit.INSTANCE;
            } else {
                throw errorState.getCause();
            }
        }
        throw e;
    }

    private final void withTransparentSnapshot(Function0<Unit> block) {
        TransparentObserverSnapshot snapshot;
        Snapshot currentSnapshot = Snapshot.INSTANCE.getCurrent();
        if (currentSnapshot instanceof MutableSnapshot) {
            snapshot = new TransparentObserverMutableSnapshot((MutableSnapshot) currentSnapshot, null, null, true, false);
        } else {
            snapshot = new TransparentObserverSnapshot(currentSnapshot, null, true, false);
        }
        Snapshot this_$iv = snapshot;
        try {
            Snapshot previous$iv = this_$iv.makeCurrent();
            try {
                block.invoke();
                this_$iv.restoreCurrent(previous$iv);
                snapshot.dispose();
            } catch (Throwable th) {
                this_$iv.restoreCurrent(previous$iv);
                throw th;
            }
        } catch (Throwable th2) {
            snapshot.dispose();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ControlledComposition> knownCompositions() {
        List<ControlledComposition> listKnownCompositionsLocked;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            listKnownCompositionsLocked = knownCompositionsLocked();
        }
        return listKnownCompositionsLocked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ControlledComposition> knownCompositionsLocked() {
        List cache = this._knownCompositionsCache;
        if (cache != null) {
            return cache;
        }
        List<ControlledComposition> list = this._knownCompositions;
        ArrayList arrayListEmptyList = list.isEmpty() ? CollectionsKt.emptyList() : new ArrayList(list);
        this._knownCompositionsCache = arrayListEmptyList;
        return arrayListEmptyList;
    }

    private final void clearKnownCompositionsLocked() {
        List<ControlledComposition> listKnownCompositionsLocked = knownCompositionsLocked();
        int size = listKnownCompositionsLocked.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = listKnownCompositionsLocked.get(index$iv);
            ControlledComposition composition = (ControlledComposition) item$iv;
            unregisterCompositionLocked(composition);
        }
        this._knownCompositions.clear();
        this._knownCompositionsCache = CollectionsKt.emptyList();
    }

    private final void removeKnownCompositionLocked(ControlledComposition composition) {
        if (this._knownCompositions.remove(composition)) {
            this._knownCompositionsCache = null;
            unregisterCompositionLocked(composition);
        }
    }

    private final void addKnownCompositionLocked(ControlledComposition composition) {
        this._knownCompositions.add(composition);
        this._knownCompositionsCache = null;
    }

    private final void registerCompositionLocked(ControlledComposition composition) {
        ObjectList objectList = this.registrationObservers;
        if (objectList == null) {
            return;
        }
        ObjectList this_$iv = objectList;
        Object[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            CompositionRegistrationObserver it = (CompositionRegistrationObserver) content$iv[i$iv];
            if (composition instanceof ObservableComposition) {
                it.onCompositionRegistered((ObservableComposition) composition);
            }
        }
    }

    private final void unregisterCompositionLocked(ControlledComposition composition) {
        ObjectList objectList = this.registrationObservers;
        if (objectList == null) {
            return;
        }
        ObjectList this_$iv = objectList;
        Object[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            CompositionRegistrationObserver it = (CompositionRegistrationObserver) content$iv[i$iv];
            if (composition instanceof ObservableComposition) {
                it.onCompositionUnregistered((ObservableComposition) composition);
            }
        }
    }

    public final CompositionObserverHandle addCompositionRegistrationObserver$runtime(final CompositionRegistrationObserver observer) {
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            MutableObjectList<CompositionRegistrationObserver> mutableObjectList = this.registrationObservers;
            if (mutableObjectList == null) {
                mutableObjectList = new MutableObjectList<>(0, 1, null);
                this.registrationObservers = mutableObjectList;
            }
            mutableObjectList.add(observer);
            List<ControlledComposition> list = this._knownCompositions;
            int size = list.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = list.get(index$iv);
                ControlledComposition composition = (ControlledComposition) item$iv;
                if (composition instanceof ObservableComposition) {
                    observer.onCompositionRegistered((ObservableComposition) composition);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.Recomposer$addCompositionRegistrationObserver$2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object lock$iv2 = this.this$0.stateLock;
                Recomposer recomposer = this.this$0;
                CompositionRegistrationObserver compositionRegistrationObserver = observer;
                synchronized (lock$iv2) {
                    MutableObjectList mutableObjectList2 = recomposer.registrationObservers;
                    if (mutableObjectList2 != null) {
                        Boolean.valueOf(mutableObjectList2.remove(compositionRegistrationObserver));
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RecomposerErrorState resetErrorState() {
        RecomposerErrorState error;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            error = this.errorState;
            if (error != null) {
                this.errorState = null;
                deriveStateLocked();
            }
        }
        return error;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void retryFailedCompositions() {
        List<ControlledComposition> list;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            list = this.failedCompositions;
            this.failedCompositions = null;
        }
        if (list == null) {
            return;
        }
        while (!list.isEmpty()) {
            try {
                ControlledComposition composition = (ControlledComposition) CollectionsKt.removeLast(list);
                if (composition instanceof CompositionImpl) {
                    ((CompositionImpl) composition).invalidateAll();
                    ((CompositionImpl) composition).setContent(((CompositionImpl) composition).getComposable());
                    if (this.errorState != null) {
                        break;
                    }
                }
            } catch (Throwable th) {
                if (!list.isEmpty()) {
                    synchronized (this.stateLock) {
                        int size = list.size();
                        for (int index$iv = 0; index$iv < size; index$iv++) {
                            Object item$iv = list.get(index$iv);
                            ControlledComposition it = (ControlledComposition) item$iv;
                            recordFailedCompositionLocked(it);
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
                throw th;
            }
        }
        if (list.isEmpty()) {
            return;
        }
        Object lock$iv2 = this.stateLock;
        synchronized (lock$iv2) {
            int size2 = list.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = list.get(index$iv2);
                ControlledComposition it2 = (ControlledComposition) item$iv2;
                recordFailedCompositionLocked(it2);
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordFailedCompositionLocked(ControlledComposition composition) {
        ArrayList arrayList = this.failedCompositions;
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.failedCompositions = arrayList;
        }
        if (!arrayList.contains(composition)) {
            arrayList.add(composition);
        }
        removeKnownCompositionLocked(composition);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "parentFrameClock", "Landroidx/compose/runtime/MonotonicFrameClock;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2", f = "Recomposer.kt", i = {0, 0, 0, 1}, l = {1011, 1031, 1032}, m = "invokeSuspend", n = {"recomposeCoroutineScope", "frameSignal", "frameLoop", "frameLoop"}, s = {"L$0", "L$1", "L$2", "L$0"}, v = 1)
    static final class C03452 extends SuspendLambda implements Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineContext $recomposeCoroutineContext;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ Recomposer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03452(CoroutineContext coroutineContext, Recomposer recomposer, Continuation<? super C03452> continuation) {
            super(3, continuation);
            this.$recomposeCoroutineContext = coroutineContext;
            this.this$0 = recomposer;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, MonotonicFrameClock monotonicFrameClock, Continuation<? super Unit> continuation) {
            C03452 c03452 = new C03452(this.$recomposeCoroutineContext, this.this$0, continuation);
            c03452.L$0 = coroutineScope;
            c03452.L$1 = monotonicFrameClock;
            return c03452.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:19:0x00c5  */
        /* JADX WARN: Code duplicated, block: B:21:0x00d8 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:22:0x00d9  */
        /* JADX WARN: Code duplicated, block: B:25:0x00e7  */
        /* JADX WARN: Code duplicated, block: B:28:0x00f6 A[Catch: all -> 0x01ff, TRY_LEAVE, TryCatch #4 {all -> 0x01ff, blocks: (B:26:0x00e8, B:28:0x00f6), top: B:104:0x00e8 }] */
        /* JADX WARN: Code duplicated, block: B:34:0x0105  */
        /* JADX WARN: Code duplicated, block: B:39:0x011c  */
        /* JADX WARN: Code duplicated, block: B:41:0x012e A[LOOP:0: B:40:0x012c->B:41:0x012e, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:43:0x0142  */
        /* JADX WARN: Code duplicated, block: B:46:0x0153  */
        /* JADX WARN: Code duplicated, block: B:48:0x0160  */
        /* JADX WARN: Code duplicated, block: B:57:0x01b1  */
        /* JADX WARN: Code duplicated, block: B:60:0x01b9 A[Catch: all -> 0x01fc, TRY_LEAVE, TryCatch #2 {, blocks: (B:58:0x01b2, B:60:0x01b9, B:77:0x01f0, B:78:0x01fb), top: B:100:0x01b2 }] */
        /* JADX WARN: Code duplicated, block: B:64:0x01c8  */
        /* JADX WARN: Code duplicated, block: B:67:0x01cf A[Catch: all -> 0x01ed, TRY_LEAVE, TryCatch #3 {, blocks: (B:65:0x01c9, B:67:0x01cf), top: B:102:0x01c9 }] */
        /* JADX WARN: Code duplicated, block: B:69:0x01d4  */
        /* JADX WARN: Code duplicated, block: B:72:0x01d9  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00d9 -> B:23:0x00db). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r25) {
            /*
                Method dump skipped, instruction units count: 576
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.C03452.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final Object runRecomposeConcurrentlyAndApplyChanges(CoroutineContext recomposeCoroutineContext, Continuation<? super Unit> continuation) {
        Object objRecompositionRunner = recompositionRunner(new C03452(recomposeCoroutineContext, this, null), continuation);
        return objRecompositionRunner == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRecompositionRunner : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0083 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:18:0x0084  */
    /* JADX WARN: Code duplicated, block: B:21:0x00a0 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x00a1 -> B:15:0x006f). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object runFrameLoop(androidx.compose.runtime.MonotonicFrameClock r10, androidx.compose.runtime.ProduceFrameSignal r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof androidx.compose.runtime.Recomposer.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.runtime.Recomposer$runFrameLoop$1 r0 = (androidx.compose.runtime.Recomposer.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.runtime.Recomposer$runFrameLoop$1 r0 = new androidx.compose.runtime.Recomposer$runFrameLoop$1
            r0.<init>(r12)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L5d;
                case 1: goto L48;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2c:
            r10 = r9
            java.lang.Object r11 = r0.L$3
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r3 = r0.L$2
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r4 = r0.L$1
            androidx.compose.runtime.ProduceFrameSignal r4 = (androidx.compose.runtime.ProduceFrameSignal) r4
            java.lang.Object r5 = r0.L$0
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            kotlin.ResultKt.throwOnFailure(r1)
            r8 = r3
            r3 = r10
            r10 = r5
            r5 = r11
            r11 = r4
            r4 = r8
            goto La7
        L48:
            r10 = r9
            java.lang.Object r11 = r0.L$3
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r3 = r0.L$2
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r4 = r0.L$1
            androidx.compose.runtime.ProduceFrameSignal r4 = (androidx.compose.runtime.ProduceFrameSignal) r4
            java.lang.Object r5 = r0.L$0
            androidx.compose.runtime.MonotonicFrameClock r5 = (androidx.compose.runtime.MonotonicFrameClock) r5
            kotlin.ResultKt.throwOnFailure(r1)
            goto L8a
        L5d:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r9
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r4 = (java.util.List) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.List r5 = (java.util.List) r5
        L6f:
            java.lang.Object r6 = r3.stateLock
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r4
            r0.L$3 = r5
            r7 = 1
            r0.label = r7
            java.lang.Object r6 = r11.awaitFrameRequest(r6, r0)
            if (r6 != r2) goto L84
            return r2
        L84:
            r8 = r5
            r5 = r10
            r10 = r3
            r3 = r4
            r4 = r11
            r11 = r8
        L8a:
            androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda6 r6 = new androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda6
            r6.<init>()
            r0.L$0 = r5
            r0.L$1 = r4
            r0.L$2 = r3
            r0.L$3 = r11
            r7 = 2
            r0.label = r7
            java.lang.Object r6 = r5.withFrameNanos(r6, r0)
            if (r6 != r2) goto La1
            return r2
        La1:
            r8 = r3
            r3 = r10
            r10 = r5
            r5 = r11
            r11 = r4
            r4 = r8
        La7:
            goto L6f
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.runFrameLoop(androidx.compose.runtime.MonotonicFrameClock, androidx.compose.runtime.ProduceFrameSignal, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static final CancellableContinuation runFrameLoop$lambda$0(Recomposer this$0, List $toRecompose, List $toApply, ProduceFrameSignal $frameSignal, long frameTime) throws Throwable {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        if (this$0.getHasBroadcastFrameClockAwaiters()) {
            Object token$iv = Trace.INSTANCE.beginSection("Recomposer:animation");
            try {
                try {
                    this$0.broadcastFrameClock.sendFrame(frameTime);
                    Snapshot.INSTANCE.sendApplyNotifications();
                    Unit unit = Unit.INSTANCE;
                    Trace.INSTANCE.endSection(token$iv);
                } catch (Throwable th) {
                    th = th;
                    Trace.INSTANCE.endSection(token$iv);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        Object token$iv2 = Trace.INSTANCE.beginSection("Recomposer:recompose");
        try {
            this$0.recordComposerModifications();
            Object lock$iv = this$0.stateLock;
            synchronized (lock$iv) {
                int i = 0;
                List<ControlledComposition> list = this$0.compositionsAwaitingApply;
                int index$iv = 0;
                int size = list.size();
                while (index$iv < size) {
                    Object item$iv = list.get(index$iv);
                    $toApply.add((ControlledComposition) item$iv);
                    index$iv++;
                    i = i;
                }
                this$0.compositionsAwaitingApply.clear();
                MutableVector<ControlledComposition> mutableVector = this$0.compositionInvalidations;
                int i$iv = 0;
                Object[] content$iv = mutableVector.content;
                int size$iv = mutableVector.getSize();
                while (i$iv < size$iv) {
                    $toRecompose.add((ControlledComposition) content$iv[i$iv]);
                    i$iv++;
                    mutableVector = mutableVector;
                }
                this$0.compositionInvalidations.clear();
                $frameSignal.takeFrameRequestLocked();
                Unit unit2 = Unit.INSTANCE;
            }
            MutableScatterSet<Object> mutableScatterSet = new MutableScatterSet<>(0, 1, null);
            List $this$fastForEach$iv = $toRecompose;
            int index$iv2 = 0;
            try {
                int size2 = $this$fastForEach$iv.size();
                while (index$iv2 < size2) {
                    Object item$iv2 = $this$fastForEach$iv.get(index$iv2);
                    ControlledComposition composer = (ControlledComposition) item$iv2;
                    ControlledComposition it = this$0.performRecompose(composer, mutableScatterSet);
                    if (it != null) {
                        $toApply.add(it);
                    }
                    index$iv2++;
                    $this$fastForEach$iv = $this$fastForEach$iv;
                }
                $toRecompose.clear();
                if (!$toApply.isEmpty()) {
                    this$0.changeCount++;
                }
                try {
                    int size3 = $toApply.size();
                    for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
                        Object item$iv3 = $toApply.get(index$iv3);
                        ControlledComposition composition = (ControlledComposition) item$iv3;
                        composition.applyChanges();
                    }
                    $toApply.clear();
                    Object lock$iv2 = this$0.stateLock;
                    synchronized (lock$iv2) {
                        cancellableContinuationDeriveStateLocked = this$0.deriveStateLocked();
                    }
                    Trace.INSTANCE.endSection(token$iv2);
                    return cancellableContinuationDeriveStateLocked;
                } catch (Throwable th3) {
                    $toApply.clear();
                    throw th3;
                }
            } catch (Throwable th4) {
                $toRecompose.clear();
                throw th4;
            }
        } catch (Throwable th5) {
            Trace.INSTANCE.endSection(token$iv2);
            throw th5;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasSchedulingWork() {
        boolean z;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            z = true;
            if (!this.snapshotInvalidations.isNotEmpty()) {
                if (!(this.compositionInvalidations.getSize() != 0) && !getHasBroadcastFrameClockAwaitersLocked() && !getHasNextFrameEndAwaitersLocked()) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitWorkAvailable(Continuation<? super Unit> continuation) {
        CancellableContinuation cancellableContinuation;
        if (getHasSchedulingWork()) {
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl co = cancellable$iv;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            if (!getHasSchedulingWork()) {
                this.workContinuation = co;
                cancellableContinuation = null;
            } else {
                cancellableContinuation = co;
            }
        }
        if (cancellableContinuation != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuation.resumeWith(Result.m9088constructorimpl(Unit.INSTANCE));
        }
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2", f = "Recomposer.kt", i = {0, 0}, l = {1173}, m = "invokeSuspend", n = {"callingJob", "unregisterApplyObserver"}, s = {"L$0", "L$1"}, v = 1)
    static final class C03432 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ MonotonicFrameClock $parentFrameClock;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03432(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, MonotonicFrameClock monotonicFrameClock, Continuation<? super C03432> continuation) {
            super(2, continuation);
            this.$block = function3;
            this.$parentFrameClock = monotonicFrameClock;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03432 c03432 = Recomposer.this.new C03432(this.$block, this.$parentFrameClock, continuation);
            c03432.L$0 = obj;
            return c03432;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03432) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:21:0x009a  */
        /* JADX WARN: Code duplicated, block: B:24:0x00a1 A[Catch: all -> 0x00b7, TryCatch #1 {, blocks: (B:22:0x009b, B:24:0x00a1, B:25:0x00a4), top: B:49:0x009b }] */
        /* JADX WARN: Code duplicated, block: B:36:0x00cb  */
        /* JADX WARN: Code duplicated, block: B:39:0x00d2 A[Catch: all -> 0x00e5, TryCatch #0 {, blocks: (B:37:0x00cc, B:39:0x00d2, B:40:0x00d5), top: B:47:0x00cc }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) throws Throwable {
            Job callingJob;
            Throwable th;
            ObserverHandle unregisterApplyObserver;
            Object lock$iv;
            Recomposer recomposer;
            Object lock$iv2;
            Recomposer recomposer2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$withContext = (CoroutineScope) this.L$0;
                    callingJob = JobKt.getJob($this$withContext.getCoroutineContext());
                    Recomposer.this.registerRunnerJob(callingJob);
                    Snapshot.Companion companion = Snapshot.INSTANCE;
                    final Recomposer recomposer3 = Recomposer.this;
                    ObserverHandle unregisterApplyObserver2 = companion.registerApplyObserver(new Function2() { // from class: androidx.compose.runtime.Recomposer$recompositionRunner$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return Recomposer.C03432.invokeSuspend$lambda$0(recomposer3, (Set) obj, (Snapshot) obj2);
                        }
                    });
                    Recomposer.INSTANCE.addRunning(Recomposer.this.recomposerInfo);
                    try {
                        List $this$fastForEach$iv = Recomposer.this.knownCompositions();
                        int size = $this$fastForEach$iv.size();
                        for (int index$iv = 0; index$iv < size; index$iv++) {
                            Object item$iv = $this$fastForEach$iv.get(index$iv);
                            ControlledComposition it = (ControlledComposition) item$iv;
                            it.invalidateAll();
                        }
                        this.L$0 = callingJob;
                        this.L$1 = unregisterApplyObserver2;
                        this.label = 1;
                        if (CoroutineScopeKt.coroutineScope(new C00772(this.$block, this.$parentFrameClock, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        unregisterApplyObserver = unregisterApplyObserver2;
                        unregisterApplyObserver.dispose();
                        lock$iv2 = Recomposer.this.stateLock;
                        recomposer2 = Recomposer.this;
                        synchronized (lock$iv2) {
                            if (recomposer2.runnerJob == callingJob) {
                                recomposer2.runnerJob = null;
                            }
                            recomposer2.deriveStateLocked();
                        }
                        Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                        return Unit.INSTANCE;
                    } catch (Throwable th2) {
                        th = th2;
                        unregisterApplyObserver = unregisterApplyObserver2;
                        unregisterApplyObserver.dispose();
                        lock$iv = Recomposer.this.stateLock;
                        recomposer = Recomposer.this;
                        synchronized (lock$iv) {
                            if (recomposer.runnerJob == callingJob) {
                                recomposer.runnerJob = null;
                            }
                            recomposer.deriveStateLocked();
                        }
                        Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                        throw th;
                    }
                case 1:
                    unregisterApplyObserver = (ObserverHandle) this.L$1;
                    callingJob = (Job) this.L$0;
                    try {
                        ResultKt.throwOnFailure($result);
                        unregisterApplyObserver.dispose();
                        lock$iv2 = Recomposer.this.stateLock;
                        recomposer2 = Recomposer.this;
                        synchronized (lock$iv2) {
                            if (recomposer2.runnerJob == callingJob) {
                                recomposer2.runnerJob = null;
                            }
                            recomposer2.deriveStateLocked();
                            Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                            return Unit.INSTANCE;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        unregisterApplyObserver.dispose();
                        lock$iv = Recomposer.this.stateLock;
                        recomposer = Recomposer.this;
                        synchronized (lock$iv) {
                            if (recomposer.runnerJob == callingJob) {
                                recomposer.runnerJob = null;
                            }
                            recomposer.deriveStateLocked();
                            Recomposer.INSTANCE.removeRunning(Recomposer.this.recomposerInfo);
                            throw th;
                        }
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        /* JADX WARN: Code duplicated, block: B:53:0x0126 A[Catch: all -> 0x0133, TryCatch #2 {all -> 0x0133, blocks: (B:24:0x0091, B:26:0x0095, B:35:0x00bc, B:30:0x00ab, B:41:0x00d6, B:56:0x012d, B:45:0x00f1, B:46:0x0104, B:48:0x010a, B:50:0x0115, B:53:0x0126), top: B:74:0x0026 }] */
        static final Unit invokeSuspend$lambda$0(Recomposer this$0, Set changed, Snapshot snapshot) throws Throwable {
            Object lock$iv;
            CancellableContinuation cancellableContinuationDeriveStateLocked;
            int $i$f$synchronized;
            Set $this$fastForEach$iv;
            Set $this$fastForEach$iv2;
            Object lock$iv2 = this$0.stateLock;
            int $i$f$synchronized2 = 0;
            synchronized (lock$iv2) {
                int i = 0;
                try {
                    if (((State) this$0._state.getValue()).compareTo(State.Idle) >= 0) {
                        MutableScatterSet snapshotInvalidations = this$0.snapshotInvalidations;
                        Set $this$fastForEach$iv3 = changed;
                        try {
                            if ($this$fastForEach$iv3 instanceof ScatterSetWrapper) {
                                ScatterSet this_$iv$iv = ((ScatterSetWrapper) $this$fastForEach$iv3).getSet$runtime();
                                int $i$f$forEach = 0;
                                Object[] elements$iv$iv = this_$iv$iv.elements;
                                long[] m$iv$iv$iv = this_$iv$iv.metadata;
                                int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
                                int i$iv$iv$iv = 0;
                                if (0 <= lastIndex$iv$iv$iv) {
                                    while (true) {
                                        long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                                        int $i$f$forEach2 = $i$f$forEach;
                                        int i2 = i;
                                        lock$iv = lock$iv2;
                                        if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i3 = 8;
                                            int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                                            int j$iv$iv$iv = 0;
                                            while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                                                long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                                if (value$iv$iv$iv$iv < 128) {
                                                    int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                                    try {
                                                        Object it = elements$iv$iv[index$iv$iv$iv];
                                                        if (it instanceof StateObjectImpl) {
                                                            ReaderKind.Companion companion = ReaderKind.INSTANCE;
                                                            $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                                            if (!((StateObjectImpl) it).m4558isReadInh_f27i8$runtime(ReaderKind.m4546constructorimpl(1))) {
                                                            }
                                                        } else {
                                                            $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                                        }
                                                        snapshotInvalidations.add(it);
                                                    } catch (Throwable th) {
                                                        th = th;
                                                        throw th;
                                                    }
                                                } else {
                                                    $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                                }
                                                slot$iv$iv$iv >>= i3;
                                                j$iv$iv$iv++;
                                                $i$f$synchronized2 = $i$f$synchronized2;
                                                i3 = i3;
                                                $this$fastForEach$iv3 = $this$fastForEach$iv2;
                                            }
                                            $i$f$synchronized = $i$f$synchronized2;
                                            $this$fastForEach$iv = $this$fastForEach$iv3;
                                            if (bitCount$iv$iv$iv != i3) {
                                                break;
                                            }
                                        } else {
                                            $i$f$synchronized = $i$f$synchronized2;
                                            $this$fastForEach$iv = $this$fastForEach$iv3;
                                        }
                                        if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                                            break;
                                        }
                                        i$iv$iv$iv++;
                                        lock$iv2 = lock$iv;
                                        i = i2;
                                        $i$f$forEach = $i$f$forEach2;
                                        $i$f$synchronized2 = $i$f$synchronized;
                                        $this$fastForEach$iv3 = $this$fastForEach$iv;
                                    }
                                } else {
                                    lock$iv = lock$iv2;
                                }
                            } else {
                                lock$iv = lock$iv2;
                                Set $this$forEach$iv$iv = $this$fastForEach$iv3;
                                for (Object element$iv$iv : $this$forEach$iv$iv) {
                                    if (element$iv$iv instanceof StateObjectImpl) {
                                        ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                                        if (((StateObjectImpl) element$iv$iv).m4558isReadInh_f27i8$runtime(ReaderKind.m4546constructorimpl(1))) {
                                            snapshotInvalidations.add(element$iv$iv);
                                        }
                                    } else {
                                        snapshotInvalidations.add(element$iv$iv);
                                    }
                                }
                            }
                            cancellableContinuationDeriveStateLocked = this$0.deriveStateLocked();
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else {
                        lock$iv = lock$iv2;
                        cancellableContinuationDeriveStateLocked = null;
                    }
                    if (cancellableContinuationDeriveStateLocked != null) {
                        Result.Companion companion3 = Result.INSTANCE;
                        cancellableContinuationDeriveStateLocked.resumeWith(Result.m9088constructorimpl(Unit.INSTANCE));
                    }
                    return Unit.INSTANCE;
                } catch (Throwable th3) {
                    th = th3;
                    lock$iv = lock$iv2;
                }
            }
        }

        /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$recompositionRunner$2$2, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Recomposer.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.runtime.Recomposer$recompositionRunner$2$2", f = "Recomposer.kt", i = {}, l = {1173}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        static final class C00772 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> $block;
            final /* synthetic */ MonotonicFrameClock $parentFrameClock;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00772(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, MonotonicFrameClock monotonicFrameClock, Continuation<? super C00772> continuation) {
                super(2, continuation);
                this.$block = function3;
                this.$parentFrameClock = monotonicFrameClock;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00772 c00772 = new C00772(this.$block, this.$parentFrameClock, continuation);
                c00772.L$0 = obj;
                return c00772;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00772) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object $result) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                        Function3<CoroutineScope, MonotonicFrameClock, Continuation<? super Unit>, Object> function3 = this.$block;
                        MonotonicFrameClock monotonicFrameClock = this.$parentFrameClock;
                        this.label = 1;
                        if (function3.invoke($this$coroutineScope, monotonicFrameClock, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object recompositionRunner(Function3<? super CoroutineScope, ? super MonotonicFrameClock, ? super Continuation<? super Unit>, ? extends Object> function3, Continuation<? super Unit> continuation) {
        MonotonicFrameClock parentFrameClock = MonotonicFrameClockKt.getMonotonicFrameClock(continuation.getContext());
        Object objWithContext = BuildersKt.withContext(this.broadcastFrameClock, new C03432(function3, parentFrameClock, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final void cancel() {
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            if (this._state.getValue().compareTo(State.Idle) >= 0) {
                this._state.setValue(State.ShuttingDown);
            }
            Unit unit = Unit.INSTANCE;
        }
        Job.DefaultImpls.cancel$default((Job) this.effectJob, (CancellationException) null, 1, (Object) null);
    }

    public final void close() {
        if (this.effectJob.complete()) {
            Object lock$iv = this.stateLock;
            synchronized (lock$iv) {
                this.isClosed = true;
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$join$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/runtime/Recomposer$State;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$join$2", f = "Recomposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C03422 extends SuspendLambda implements Function2<State, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C03422(Continuation<? super C03422> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03422 c03422 = new C03422(continuation);
            c03422.L$0 = obj;
            return c03422;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(State state, Continuation<? super Boolean> continuation) {
            return ((C03422) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    State it = (State) this.L$0;
                    return Boxing.boxBoolean(it == State.ShutDown);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object join(Continuation<? super Unit> continuation) {
        Object objFirst = FlowKt.first(getCurrentState(), new C03422(null), continuation);
        return objFirst == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objFirst : Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public CancellationHandle scheduleFrameEndCallback(Function0<Unit> action) {
        return this.nextFrameEndCallbackQueue.scheduleFrameEndCallback(action);
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void composeInitial$runtime(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content) throws Throwable {
        boolean newComposition;
        boolean composerWasComposing = composition.isComposing();
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            if (this._state.getValue().compareTo(State.ShuttingDown) > 0) {
                newComposition = !knownCompositionsLocked().contains(composition);
                if (newComposition) {
                    registerCompositionLocked(composition);
                }
            } else {
                newComposition = true;
            }
        }
        try {
            MutableSnapshot snapshot$iv = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, null));
            try {
                MutableSnapshot this_$iv$iv = snapshot$iv;
                Snapshot previous$iv$iv = this_$iv$iv.makeCurrent();
                try {
                    composition.composeContent(content);
                    Unit unit = Unit.INSTANCE;
                    this_$iv$iv.restoreCurrent(previous$iv$iv);
                    applyAndCheck(snapshot$iv);
                    Object lock$iv2 = this.stateLock;
                    synchronized (lock$iv2) {
                        if (this._state.getValue().compareTo(State.ShuttingDown) > 0) {
                            if (!knownCompositionsLocked().contains(composition)) {
                                addKnownCompositionLocked(composition);
                            }
                        } else {
                            unregisterCompositionLocked(composition);
                        }
                        Unit unit2 = Unit.INSTANCE;
                    }
                    if (!composerWasComposing) {
                        Snapshot.INSTANCE.notifyObjectsInitialized();
                    }
                    try {
                        performInitialMovableContentInserts(composition);
                        try {
                            composition.applyChanges();
                            composition.applyLateChanges();
                            if (!composerWasComposing) {
                                Snapshot.INSTANCE.notifyObjectsInitialized();
                            }
                        } catch (Throwable e) {
                            processCompositionError$default(this, e, null, false, 6, null);
                        }
                    } catch (Throwable e2) {
                        processCompositionError(e2, composition, true);
                    }
                } catch (Throwable th) {
                    this_$iv$iv.restoreCurrent(previous$iv$iv);
                    throw th;
                }
            } catch (Throwable th2) {
                applyAndCheck(snapshot$iv);
                throw th2;
            }
        } catch (Throwable e3) {
            if (newComposition) {
                synchronized (this.stateLock) {
                    unregisterCompositionLocked(composition);
                    Unit unit3 = Unit.INSTANCE;
                }
            }
            processCompositionError(e3, composition, true);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public ScatterSet<RecomposeScopeImpl> composeInitialPaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, Function2<? super Composer, ? super Integer, Unit> content) {
        try {
            ShouldPauseCallback previous$iv = composition.getAndSetShouldPauseCallback(shouldPause);
            try {
                composeInitial$runtime(composition, content);
                MutableScatterSet<RecomposeScopeImpl> mutableScatterSet = this.pausedScopes.get();
                MutableScatterSet<RecomposeScopeImpl> mutableScatterSetEmptyScatterSet = mutableScatterSet != null ? mutableScatterSet : ScatterSetKt.emptyScatterSet();
                composition.getAndSetShouldPauseCallback(previous$iv);
                this.pausedScopes.set(null);
                return mutableScatterSetEmptyScatterSet;
            } catch (Throwable th) {
                composition.getAndSetShouldPauseCallback(previous$iv);
                throw th;
            }
        } catch (Throwable th2) {
            this.pausedScopes.set(null);
            throw th2;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public ScatterSet<RecomposeScopeImpl> recomposePaused$runtime(ControlledComposition composition, ShouldPauseCallback shouldPause, ScatterSet<RecomposeScopeImpl> invalidScopes) {
        try {
            recordComposerModifications();
            composition.recordModificationsOf(ScatterSetWrapperKt.wrapIntoSet(invalidScopes));
            ShouldPauseCallback previous$iv = composition.getAndSetShouldPauseCallback(shouldPause);
            try {
                ControlledComposition needsApply = performRecompose(composition, null);
                if (needsApply != null) {
                    performInitialMovableContentInserts(composition);
                    needsApply.applyChanges();
                    needsApply.applyLateChanges();
                }
                MutableScatterSet<RecomposeScopeImpl> mutableScatterSet = this.pausedScopes.get();
                MutableScatterSet<RecomposeScopeImpl> mutableScatterSetEmptyScatterSet = mutableScatterSet != null ? mutableScatterSet : ScatterSetKt.emptyScatterSet();
                composition.getAndSetShouldPauseCallback(previous$iv);
                this.pausedScopes.set(null);
                return mutableScatterSetEmptyScatterSet;
            } catch (Throwable th) {
                composition.getAndSetShouldPauseCallback(previous$iv);
                throw th;
            }
        } catch (Throwable th2) {
            this.pausedScopes.set(null);
            throw th2;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void reportPausedScope$runtime(RecomposeScopeImpl scope) {
        MutableScatterSet<RecomposeScopeImpl> mutableScatterSet = this.pausedScopes.get();
        if (mutableScatterSet == null) {
            Recomposer $this$reportPausedScope_u24lambda_u240 = this;
            MutableScatterSet<RecomposeScopeImpl> mutableScatterSetMutableScatterSetOf = ScatterSetKt.mutableScatterSetOf();
            $this$reportPausedScope_u24lambda_u240.pausedScopes.set(mutableScatterSetMutableScatterSetOf);
            mutableScatterSet = mutableScatterSetMutableScatterSetOf;
        }
        mutableScatterSet.add(scope);
    }

    private final void performInitialMovableContentInserts(ControlledComposition composition) throws Throwable {
        boolean z;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            int i = 0;
            List<MovableContentStateReference> list = this.movableContentAwaitingInsert;
            int index$iv$iv = 0;
            int size = list.size();
            while (true) {
                if (index$iv$iv >= size) {
                    z = false;
                    break;
                }
                Object item$iv$iv = list.get(index$iv$iv);
                MovableContentStateReference it = (MovableContentStateReference) item$iv$iv;
                int i2 = i;
                if (Intrinsics.areEqual(it.getComposition(), composition)) {
                    z = true;
                    break;
                } else {
                    index$iv$iv++;
                    i = i2;
                }
            }
            if (z) {
                Unit unit = Unit.INSTANCE;
                List toInsert = new ArrayList();
                performInitialMovableContentInserts$fillToInsert(toInsert, this, composition);
                while (!toInsert.isEmpty()) {
                    performInsertValues(toInsert, null);
                    performInitialMovableContentInserts$fillToInsert(toInsert, this, composition);
                }
            }
        }
    }

    private static final void performInitialMovableContentInserts$fillToInsert(List<MovableContentStateReference> list, Recomposer this$0, ControlledComposition $composition) {
        list.clear();
        Object lock$iv = this$0.stateLock;
        synchronized (lock$iv) {
            Iterator<MovableContentStateReference> it = this$0.movableContentAwaitingInsert.iterator();
            while (it.hasNext()) {
                MovableContentStateReference value = it.next();
                if (Intrinsics.areEqual(value.getComposition(), $composition)) {
                    list.add(value);
                    it.remove();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:21:0x0048  */
    public final ControlledComposition performRecompose(final ControlledComposition composition, final MutableScatterSet<Object> modifiedValues) {
        if (!composition.isComposing() && !composition.isDisposed()) {
            Set<ControlledComposition> set = this.compositionsRemoved;
            boolean z = true;
            if (!(set != null && set.contains(composition))) {
                MutableSnapshot snapshot$iv = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, modifiedValues));
                try {
                    MutableSnapshot this_$iv$iv = snapshot$iv;
                    Snapshot previous$iv$iv = this_$iv$iv.makeCurrent();
                    if (modifiedValues != null) {
                        try {
                            if (!modifiedValues.isNotEmpty()) {
                                z = false;
                            }
                        } catch (Throwable th) {
                            this_$iv$iv.restoreCurrent(previous$iv$iv);
                            throw th;
                        }
                    } else {
                        z = false;
                    }
                    if (z) {
                        composition.prepareCompose(new Function0() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return Recomposer.performRecompose$lambda$0$0(modifiedValues, composition);
                            }
                        });
                    }
                    boolean zRecompose = composition.recompose();
                    this_$iv$iv.restoreCurrent(previous$iv$iv);
                    applyAndCheck(snapshot$iv);
                    if (zRecompose) {
                        return composition;
                    }
                    return null;
                } catch (Throwable th2) {
                    applyAndCheck(snapshot$iv);
                    throw th2;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit performRecompose$lambda$0$0(MutableScatterSet $modifiedValues, ControlledComposition $composition) {
        ScatterSet this_$iv;
        MutableScatterSet this_$iv2 = $modifiedValues;
        Object[] elements$iv = this_$iv2.elements;
        long[] m$iv$iv = this_$iv2.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    this_$iv = this_$iv2;
                } else {
                    int i = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            Object it = elements$iv[index$iv$iv];
                            $composition.recordWriteOf(it);
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i = i;
                        this_$iv2 = this_$iv2;
                    }
                    this_$iv = this_$iv2;
                    if (bitCount$iv$iv != i) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv2 = this_$iv;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:173:0x018f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x0191 A[Catch: all -> 0x03a1, LOOP:3: B:38:0x0155->B:49:0x0191, LOOP_END, TryCatch #0 {all -> 0x03a1, blocks: (B:30:0x010d, B:32:0x0115, B:35:0x0130, B:37:0x0141, B:39:0x0157, B:41:0x016f, B:52:0x01a3, B:54:0x01c3, B:56:0x01dc, B:62:0x021e, B:59:0x01f6, B:63:0x022b, B:49:0x0191), top: B:148:0x010d }] */
    public final List<ControlledComposition> performInsertValues(List<MovableContentStateReference> references, MutableScatterSet<Object> modifiedValues) throws Throwable {
        Iterator it;
        Map tasks;
        List pairs;
        ArrayList $this$fastAll$iv;
        boolean z;
        ArrayList toInsert;
        boolean z2;
        boolean z3;
        int index$iv$iv;
        ArrayList target$iv;
        Pair<MovableContentStateReference, MovableContentStateReference> pair;
        int index$iv$iv2;
        List $this$fastForEach$iv$iv;
        int index$iv$iv3;
        Object answer$iv$iv;
        List<MovableContentStateReference> list = references;
        Map destination$iv = new HashMap(list.size());
        int index$iv$iv4 = 0;
        int size = list.size();
        while (index$iv$iv4 < size) {
            MovableContentStateReference it2 = list.get(index$iv$iv4);
            ControlledComposition composition = it2.getComposition();
            Map $this$getOrPut$iv$iv = destination$iv;
            Object value$iv$iv = $this$getOrPut$iv$iv.get(composition);
            if (value$iv$iv == null) {
                answer$iv$iv = new ArrayList();
                $this$getOrPut$iv$iv.put(composition, answer$iv$iv);
            } else {
                answer$iv$iv = value$iv$iv;
            }
            ArrayList list$iv = (ArrayList) answer$iv$iv;
            list$iv.add(it2);
            index$iv$iv4++;
            list = list;
        }
        Map tasks2 = destination$iv;
        Iterator it3 = tasks2.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry entry = (Map.Entry) it3.next();
            ControlledComposition composition2 = (ControlledComposition) entry.getKey();
            List refs = (List) entry.getValue();
            boolean value$iv = !composition2.isComposing();
            if (!value$iv) {
                ComposerKt.composeImmediateRuntimeError("Check failed");
            }
            MutableScatterSet<Object> mutableScatterSet = modifiedValues;
            MutableSnapshot snapshot$iv = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition2), writeObserverOf(composition2, mutableScatterSet));
            try {
                MutableSnapshot this_$iv$iv = snapshot$iv;
                Snapshot previous$iv$iv = this_$iv$iv.makeCurrent();
                try {
                    Object lock$iv = this.stateLock;
                    synchronized (lock$iv) {
                        try {
                            it = it3;
                            List target$iv2 = new ArrayList(refs.size());
                            List $this$fastForEach$iv$iv2 = refs;
                            tasks = tasks2;
                            try {
                                int size2 = $this$fastForEach$iv$iv2.size();
                                int index$iv$iv5 = 0;
                                while (index$iv$iv5 < size2) {
                                    try {
                                        Object item$iv$iv = $this$fastForEach$iv$iv2.get(index$iv$iv5);
                                        List $this$fastForEach$iv$iv3 = $this$fastForEach$iv$iv2;
                                        List list2 = target$iv2;
                                        MovableContentStateReference reference = (MovableContentStateReference) item$iv$iv;
                                        int i = size2;
                                        int index$iv$iv6 = index$iv$iv5;
                                        Object objM4458removeLastimpl = MultiValueMap.m4458removeLastimpl(this.movableContentRemoved, reference.getContent$runtime());
                                        MovableContentStateReference it4 = (MovableContentStateReference) objM4458removeLastimpl;
                                        if (it4 != null) {
                                            try {
                                                this.movableContentNestedStatesAvailable.usedContainer(it4);
                                            } catch (Throwable th) {
                                                th = th;
                                                throw th;
                                            }
                                        }
                                        list2.add(TuplesKt.to(reference, objM4458removeLastimpl));
                                        index$iv$iv5 = index$iv$iv6 + 1;
                                        $this$fastForEach$iv$iv2 = $this$fastForEach$iv$iv3;
                                        size2 = i;
                                        mutableScatterSet = mutableScatterSet;
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                }
                                List pairs2 = target$iv2;
                                int i2 = 0;
                                try {
                                    if (ComposeRuntimeFlags.isMovingNestedMovableContentEnabled) {
                                        List $this$fastForEach$iv$iv4 = pairs2;
                                        int size3 = $this$fastForEach$iv$iv4.size();
                                        pairs = pairs2;
                                        int index$iv$iv7 = 0;
                                        while (true) {
                                            if (index$iv$iv7 >= size3) {
                                                z3 = false;
                                                break;
                                            }
                                            int i3 = i2;
                                            List $this$fastForEach$iv$iv5 = $this$fastForEach$iv$iv4;
                                            Object item$iv$iv2 = $this$fastForEach$iv$iv5.get(index$iv$iv7);
                                            Pair<MovableContentStateReference, MovableContentStateReference> pair2 = (Pair) item$iv$iv2;
                                            if (pair2.getSecond() == null) {
                                                index$iv$iv2 = index$iv$iv7;
                                                $this$fastForEach$iv$iv = $this$fastForEach$iv$iv5;
                                                index$iv$iv3 = this.movableContentNestedStatesAvailable.contains(pair2.getFirst().getContent$runtime()) ? 1 : 0;
                                                if (index$iv$iv3 != 0) {
                                                    z3 = true;
                                                    break;
                                                }
                                                index$iv$iv7 = index$iv$iv2 + 1;
                                                i2 = i3;
                                                $this$fastForEach$iv$iv4 = $this$fastForEach$iv$iv;
                                            } else {
                                                index$iv$iv2 = index$iv$iv7;
                                                $this$fastForEach$iv$iv = $this$fastForEach$iv$iv5;
                                            }
                                            if (index$iv$iv3 != 0) {
                                                z3 = true;
                                                break;
                                            }
                                            index$iv$iv7 = index$iv$iv2 + 1;
                                            i2 = i3;
                                            $this$fastForEach$iv$iv4 = $this$fastForEach$iv$iv;
                                        }
                                        if (z3) {
                                            ArrayList target$iv3 = new ArrayList(pairs.size());
                                            List $this$fastForEach$iv$iv6 = pairs;
                                            int size4 = $this$fastForEach$iv$iv6.size();
                                            int $i$f$fastMap = 0;
                                            while ($i$f$fastMap < size4) {
                                                Object item$iv$iv3 = $this$fastForEach$iv$iv6.get($i$f$fastMap);
                                                int i4 = size4;
                                                ArrayList arrayList = target$iv3;
                                                Pair<MovableContentStateReference, MovableContentStateReference> pair3 = (Pair) item$iv$iv3;
                                                if (pair3.getSecond() == null) {
                                                    index$iv$iv = $i$f$fastMap;
                                                    target$iv = target$iv3;
                                                    NestedMovableContent nestedContentReference = this.movableContentNestedStatesAvailable.removeLast(pair3.getFirst().getContent$runtime());
                                                    if (nestedContentReference != null) {
                                                        MovableContentStateReference content = nestedContentReference.getContent();
                                                        MovableContentStateReference container = nestedContentReference.getContainer();
                                                        $this$fastForEach$iv$iv6 = $this$fastForEach$iv$iv6;
                                                        MultiValueMap.m4443addimpl(this.movableContentNestedExtractionsPending, container, content);
                                                        pair = TuplesKt.to(pair3.getFirst(), content);
                                                    }
                                                    arrayList.add(pair);
                                                    $i$f$fastMap = index$iv$iv + 1;
                                                    size4 = i4;
                                                    target$iv3 = target$iv;
                                                    $this$fastForEach$iv$iv6 = $this$fastForEach$iv$iv6;
                                                } else {
                                                    index$iv$iv = $i$f$fastMap;
                                                    target$iv = target$iv3;
                                                }
                                                pair = pair3;
                                                arrayList.add(pair);
                                                $i$f$fastMap = index$iv$iv + 1;
                                                size4 = i4;
                                                target$iv3 = target$iv;
                                                $this$fastForEach$iv$iv6 = $this$fastForEach$iv$iv6;
                                            }
                                            $this$fastAll$iv = target$iv3;
                                        }
                                    } else {
                                        pairs = pairs2;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    this_$iv$iv.restoreCurrent(previous$iv$iv);
                                    throw th;
                                }
                                $this$fastAll$iv = pairs;
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    }
                    List pairs3 = $this$fastAll$iv;
                    List $this$fastForEach$iv$iv7 = $this$fastAll$iv;
                    int index$iv$iv8 = 0;
                    int size5 = $this$fastForEach$iv$iv7.size();
                    while (true) {
                        if (index$iv$iv8 >= size5) {
                            z = true;
                            break;
                        }
                        Object item$iv$iv4 = $this$fastForEach$iv$iv7.get(index$iv$iv8);
                        if (!(((Pair) item$iv$iv4).getSecond() == null)) {
                            z = false;
                            break;
                        }
                        index$iv$iv8++;
                    }
                    try {
                        if (!z) {
                            int index$iv$iv9 = 0;
                            int size6 = pairs3.size();
                            while (true) {
                                if (index$iv$iv9 >= size6) {
                                    z2 = true;
                                    break;
                                }
                                Object item$iv$iv5 = pairs3.get(index$iv$iv9);
                                if (!(((Pair) item$iv$iv5).getSecond() != null)) {
                                    z2 = false;
                                    break;
                                }
                                index$iv$iv9++;
                            }
                            if (!z2) {
                                List target$iv4 = new ArrayList(pairs3.size());
                                int index$iv$iv10 = 0;
                                for (int size7 = pairs3.size(); index$iv$iv10 < size7; size7 = size7) {
                                    Object item$iv$iv6 = pairs3.get(index$iv$iv10);
                                    Pair<MovableContentStateReference, MovableContentStateReference> pair4 = (Pair) item$iv$iv6;
                                    MovableContentStateReference first = pair4.getSecond() == null ? pair4.getFirst() : null;
                                    if (first != null) {
                                        target$iv4.add(first);
                                    }
                                    index$iv$iv10++;
                                }
                                List toReturn = target$iv4;
                                Object lock$iv2 = this.stateLock;
                                synchronized (lock$iv2) {
                                    CollectionsKt.addAll(this.movableContentAwaitingInsert, toReturn);
                                    Unit unit = Unit.INSTANCE;
                                }
                                ArrayList target$iv5 = new ArrayList(pairs3.size());
                                int index$iv$iv11 = 0;
                                for (int size8 = pairs3.size(); index$iv$iv11 < size8; size8 = size8) {
                                    Pair<MovableContentStateReference, MovableContentStateReference> pair5 = pairs3.get(index$iv$iv11);
                                    if (pair5.getSecond() != null) {
                                        target$iv5.add(pair5);
                                    }
                                    index$iv$iv11++;
                                }
                                toInsert = target$iv5;
                            }
                            composition2.insertMovableContent(toInsert);
                            Unit unit2 = Unit.INSTANCE;
                            this_$iv$iv.restoreCurrent(previous$iv$iv);
                            applyAndCheck(snapshot$iv);
                            it3 = it;
                            tasks2 = tasks;
                        }
                        this_$iv$iv.restoreCurrent(previous$iv$iv);
                        applyAndCheck(snapshot$iv);
                        it3 = it;
                        tasks2 = tasks;
                    } catch (Throwable th6) {
                        th = th6;
                        applyAndCheck(snapshot$iv);
                        throw th;
                    }
                    toInsert = pairs3;
                    composition2.insertMovableContent(toInsert);
                    Unit unit3 = Unit.INSTANCE;
                } catch (Throwable th7) {
                    th = th7;
                }
            } catch (Throwable th8) {
                th = th8;
            }
        }
        return CollectionsKt.toList(tasks2.keySet());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void discardUnusedMovableContentState() {
        MutableObjectList unusedValues;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            int i = 0;
            if (MultiValueMap.m4456isNotEmptyimpl(this.movableContentRemoved)) {
                ObjectList references = MultiValueMap.m4461valuesimpl(this.movableContentRemoved);
                MultiValueMap.m4445clearimpl(this.movableContentRemoved);
                this.movableContentNestedStatesAvailable.clear();
                MultiValueMap.m4445clearimpl(this.movableContentNestedExtractionsPending);
                MutableObjectList target$iv = new MutableObjectList(references.getSize());
                Object[] content$iv$iv = references.content;
                int i$iv$iv = 0;
                int i2 = references._size;
                while (i$iv$iv < i2) {
                    Object it$iv = content$iv$iv[i$iv$iv];
                    MovableContentStateReference it = (MovableContentStateReference) it$iv;
                    target$iv.add(TuplesKt.to(it, this.movableContentStatesAvailable.get(it)));
                    i$iv$iv++;
                    i = i;
                }
                unusedValues = target$iv;
                this.movableContentStatesAvailable.clear();
            } else {
                unusedValues = ObjectListKt.emptyObjectList();
            }
        }
        ObjectList this_$iv = unusedValues;
        Object[] content$iv = this_$iv.content;
        int i3 = this_$iv._size;
        for (int i$iv = 0; i$iv < i3; i$iv++) {
            Pair pair = (Pair) content$iv[i$iv];
            MovableContentStateReference reference = (MovableContentStateReference) pair.component1();
            MovableContentState state = (MovableContentState) pair.component2();
            if (state != null) {
                reference.getComposition().disposeUnusedMovableContent(state);
            }
        }
    }

    private final Function1<Object, Unit> readObserverOf(final ControlledComposition composition) {
        return new Function1() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Recomposer.readObserverOf$lambda$0(composition, obj);
            }
        };
    }

    static final Unit readObserverOf$lambda$0(ControlledComposition $composition, Object value) {
        $composition.recordReadOf(value);
        return Unit.INSTANCE;
    }

    private final Function1<Object, Unit> writeObserverOf(final ControlledComposition composition, final MutableScatterSet<Object> modifiedValues) {
        return new Function1() { // from class: androidx.compose.runtime.Recomposer$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Recomposer.writeObserverOf$lambda$0(composition, modifiedValues, obj);
            }
        };
    }

    static final Unit writeObserverOf$lambda$0(ControlledComposition $composition, MutableScatterSet $modifiedValues, Object value) {
        $composition.recordWriteOf(value);
        if ($modifiedValues != null) {
            $modifiedValues.add(value);
        }
        return Unit.INSTANCE;
    }

    private final <T> T composing(ControlledComposition composition, MutableScatterSet<Object> modifiedValues, Function0<? extends T> block) {
        MutableSnapshot snapshot = Snapshot.INSTANCE.takeMutableSnapshot(readObserverOf(composition), writeObserverOf(composition, modifiedValues));
        try {
            MutableSnapshot this_$iv = snapshot;
            Snapshot previous$iv = this_$iv.makeCurrent();
            try {
                T tInvoke = block.invoke();
                this_$iv.restoreCurrent(previous$iv);
                applyAndCheck(snapshot);
                return tInvoke;
            } catch (Throwable th) {
                this_$iv.restoreCurrent(previous$iv);
                throw th;
            }
        } catch (Throwable th2) {
            applyAndCheck(snapshot);
            throw th2;
        }
    }

    private final void applyAndCheck(MutableSnapshot snapshot) {
        try {
            SnapshotApplyResult applyResult = snapshot.apply();
            if (applyResult instanceof SnapshotApplyResult.Failure) {
                throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.".toString());
            }
            snapshot.dispose();
        } catch (Throwable th) {
            snapshot.dispose();
            throw th;
        }
    }

    public final boolean getHasPendingWork() {
        boolean z;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            z = true;
            if (!this.snapshotInvalidations.isNotEmpty()) {
                if (!(this.compositionInvalidations.getSize() != 0) && this.concurrentCompositionsOutstanding <= 0 && this.compositionsAwaitingApply.isEmpty() && !getHasBroadcastFrameClockAwaitersLocked() && !getHasNextFrameEndAwaitersLocked() && !MultiValueMap.m4456isNotEmptyimpl(this.movableContentRemoved)) {
                    z = false;
                }
            }
        }
        return z;
    }

    private final boolean getHasFrameWorkLocked() {
        return (this.compositionInvalidations.getSize() != 0) || getHasBroadcastFrameClockAwaitersLocked() || getHasNextFrameEndAwaitersLocked() || MultiValueMap.m4456isNotEmptyimpl(this.movableContentRemoved);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getHasConcurrentFrameWorkLocked() {
        return !this.compositionsAwaitingApply.isEmpty() || getHasBroadcastFrameClockAwaitersLocked() || getHasNextFrameEndAwaitersLocked();
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.Recomposer$awaitIdle$2, reason: invalid class name */
    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/runtime/Recomposer$State;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.Recomposer$awaitIdle$2", f = "Recomposer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<State, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(State state, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    State it = (State) this.L$0;
                    return Boxing.boxBoolean(it.compareTo(State.Idle) > 0);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    public final Object awaitIdle(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.collect(FlowKt.takeWhile(getCurrentState(), new AnonymousClass2(null)), continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    public final void pauseCompositionFrameClock() {
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            this.frameClockPaused = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void resumeCompositionFrameClock() {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            if (this.frameClockPaused) {
                this.frameClockPaused = false;
                cancellableContinuationDeriveStateLocked = deriveStateLocked();
            } else {
                cancellableContinuationDeriveStateLocked = null;
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m9088constructorimpl(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getCompositeKeyHashCode$runtime */
    public long getCompositeKeyHashCode() {
        return 1000;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public boolean getCollectingCallByInformation$runtime() {
        return _hotReloadEnabled.get().booleanValue();
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getCollectingParameterInformation$runtime */
    public boolean getCollectingParameterInformation() {
        return false;
    }

    @Override // androidx.compose.runtime.CompositionContext
    /* JADX INFO: renamed from: getCollectingSourceInformation$runtime */
    public boolean getCollectingSourceInformation() {
        return ComposeStackTraceMode.m4563equalsimpl0(ComposerKt.getComposeStackTraceMode(), ComposeStackTraceMode.INSTANCE.m4570getSourceInformationMD5MrJc());
    }

    @Override // androidx.compose.runtime.CompositionContext
    public boolean getStackTraceEnabled$runtime() {
        return !ComposeStackTraceMode.m4563equalsimpl0(ComposerKt.getComposeStackTraceMode(), ComposeStackTraceMode.INSTANCE.m4569getNoneMD5MrJc());
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void recordInspectionTable$runtime(Set<CompositionData> table) {
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void registerComposition$runtime(ControlledComposition composition) {
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void unregisterComposition$runtime(ControlledComposition composition) {
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            removeKnownCompositionLocked(composition);
            this.compositionInvalidations.remove(composition);
            this.compositionsAwaitingApply.remove(composition);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidate$runtime(ControlledComposition composition) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            if (this.compositionInvalidations.contains(composition)) {
                cancellableContinuationDeriveStateLocked = null;
            } else {
                this.compositionInvalidations.add(composition);
                cancellableContinuationDeriveStateLocked = deriveStateLocked();
            }
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m9088constructorimpl(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void invalidateScope$runtime(RecomposeScopeImpl scope) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            this.snapshotInvalidations.add(scope);
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m9088constructorimpl(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void insertMovableContent$runtime(MovableContentStateReference reference) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            this.movableContentAwaitingInsert.add(reference);
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m9088constructorimpl(Unit.INSTANCE));
        }
    }

    private static final void deletedMovableContent$lambda$0$recordNestedStatesOf(Recomposer this$0, MovableContentStateReference container, MovableContentStateReference reference) {
        List<MovableContentStateReference> nestedReferences$runtime = reference.getNestedReferences$runtime();
        if (nestedReferences$runtime == null) {
            return;
        }
        int size = nestedReferences$runtime.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = nestedReferences$runtime.get(index$iv);
            MovableContentStateReference nestedReference = (MovableContentStateReference) item$iv;
            this$0.movableContentNestedStatesAvailable.add(nestedReference.getContent$runtime(), new NestedMovableContent(nestedReference, container));
            deletedMovableContent$lambda$0$recordNestedStatesOf(this$0, container, nestedReference);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void deletedMovableContent$runtime(MovableContentStateReference reference) {
        CancellableContinuation<Unit> cancellableContinuationDeriveStateLocked;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            MultiValueMap.m4443addimpl(this.movableContentRemoved, reference.getContent$runtime(), reference);
            if (reference.getNestedReferences$runtime() != null) {
                deletedMovableContent$lambda$0$recordNestedStatesOf(this, reference, reference);
            }
            cancellableContinuationDeriveStateLocked = deriveStateLocked();
        }
        if (cancellableContinuationDeriveStateLocked != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationDeriveStateLocked.resumeWith(Result.m9088constructorimpl(Unit.INSTANCE));
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00ac A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x00ae A[Catch: all -> 0x00d5, LOOP:0: B:10:0x0034->B:27:0x00ae, LOOP_END, TryCatch #1 {all -> 0x00d5, blocks: (B:21:0x0078, B:23:0x009b, B:34:0x00d0, B:27:0x00ae), top: B:46:0x0078 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x00c3 A[EDGE_INSN: B:51:0x00c3->B:30:0x00c3 BREAK  A[LOOP:0: B:10:0x0034->B:27:0x00ae], SYNTHETIC] */
    @Override // androidx.compose.runtime.CompositionContext
    public void movableContentStateReleased$runtime(MovableContentStateReference reference, MovableContentState data, Applier<?> applier) throws Throwable {
        Object lock$iv;
        Recomposer recomposer = this;
        Object lock$iv2 = recomposer.stateLock;
        int $i$f$synchronized = 0;
        synchronized (lock$iv2) {
            try {
                recomposer.movableContentStatesAvailable.set(reference, data);
                ObjectList<MovableContentStateReference> objectListM4453getimpl = MultiValueMap.m4453getimpl(recomposer.movableContentNestedExtractionsPending, reference);
                if (objectListM4453getimpl.isNotEmpty()) {
                    try {
                        ScatterMap<MovableContentStateReference, MovableContentState> scatterMapExtractNestedStates$runtime = data.extractNestedStates$runtime(applier, objectListM4453getimpl);
                        Object[] k$iv = scatterMapExtractNestedStates$runtime.keys;
                        Object[] v$iv = scatterMapExtractNestedStates$runtime.values;
                        long[] m$iv$iv = scatterMapExtractNestedStates$runtime.metadata;
                        int lastIndex$iv$iv = m$iv$iv.length - 2;
                        int i$iv$iv = 0;
                        if (0 <= lastIndex$iv$iv) {
                            while (true) {
                                long slot$iv$iv = m$iv$iv[i$iv$iv];
                                lock$iv = lock$iv2;
                                int $i$f$synchronized2 = $i$f$synchronized;
                                ObjectList<MovableContentStateReference> objectList = objectListM4453getimpl;
                                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                                    if (i$iv$iv != lastIndex$iv$iv) {
                                        break;
                                        break;
                                    }
                                    i$iv$iv++;
                                    recomposer = this;
                                    $i$f$synchronized = $i$f$synchronized2;
                                    objectListM4453getimpl = objectList;
                                    lock$iv2 = lock$iv;
                                } else {
                                    int i = 8;
                                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                    int j$iv$iv = 0;
                                    while (j$iv$iv < bitCount$iv$iv) {
                                        long value$iv$iv$iv = slot$iv$iv & 255;
                                        if (value$iv$iv$iv < 128) {
                                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                            try {
                                                Object obj = k$iv[index$iv$iv];
                                                MovableContentState state = (MovableContentState) v$iv[index$iv$iv];
                                                recomposer.movableContentStatesAvailable.set((MovableContentStateReference) obj, state);
                                            } catch (Throwable th) {
                                                th = th;
                                                throw th;
                                            }
                                        }
                                        slot$iv$iv >>= i;
                                        j$iv$iv++;
                                        recomposer = this;
                                        i = i;
                                    }
                                    if (bitCount$iv$iv != i) {
                                        break;
                                    }
                                    if (i$iv$iv != lastIndex$iv$iv) {
                                        break;
                                    }
                                    i$iv$iv++;
                                    recomposer = this;
                                    $i$f$synchronized = $i$f$synchronized2;
                                    objectListM4453getimpl = objectList;
                                    lock$iv2 = lock$iv;
                                }
                            }
                        } else {
                            lock$iv = lock$iv2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        lock$iv = lock$iv2;
                        throw th;
                    }
                } else {
                    lock$iv = lock$iv2;
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public void reportRemovedComposition$runtime(ControlledComposition composition) {
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            LinkedHashSet linkedHashSet = this.compositionsRemoved;
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet();
                this.compositionsRemoved = linkedHashSet;
            }
            linkedHashSet.add(composition);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public MovableContentState movableContentStateResolve$runtime(MovableContentStateReference reference) {
        MovableContentState movableContentStateRemove;
        Object lock$iv = this.stateLock;
        synchronized (lock$iv) {
            movableContentStateRemove = this.movableContentStatesAvailable.remove(reference);
        }
        return movableContentStateRemove;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public Composition getComposition$runtime() {
        return null;
    }

    /* JADX INFO: compiled from: Recomposer.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0000¢\u0006\u0002\b\u0015J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\u0019J\u0014\u0010\u001a\u001a\u00020\u00172\n\u0010\u001b\u001a\u00060\u0007R\u00020\bH\u0002J\u0014\u0010\u001c\u001a\u00020\u00172\n\u0010\u001b\u001a\u00060\u0007R\u00020\bH\u0002J\r\u0010\u001d\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u001eJ\u0015\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u0001H\u0000¢\u0006\u0002\b!J\u0015\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H\u0000¢\u0006\u0002\b%J\u0013\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'H\u0000¢\u0006\u0002\b)J\r\u0010*\u001a\u00020\u0017H\u0000¢\u0006\u0002\b+R\u001e\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0007R\u00020\b0\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006,"}, d2 = {"Landroidx/compose/runtime/Recomposer$Companion;", "", "<init>", "()V", "_runningRecomposers", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet;", "Landroidx/compose/runtime/Recomposer$RecomposerInfoImpl;", "Landroidx/compose/runtime/Recomposer;", "_hotReloadEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "runningRecomposers", "Lkotlinx/coroutines/flow/StateFlow;", "", "Landroidx/compose/runtime/RecomposerInfo;", "getRunningRecomposers", "()Lkotlinx/coroutines/flow/StateFlow;", "currentRunningRecomposers", "currentRunningRecomposers$runtime", "setHotReloadEnabled", "", "value", "setHotReloadEnabled$runtime", "addRunning", "info", "removeRunning", "saveStateAndDisposeForHotReload", "saveStateAndDisposeForHotReload$runtime", "loadStateAndComposeForHotReload", "token", "loadStateAndComposeForHotReload$runtime", "invalidateGroupsWithKey", "key", "", "invalidateGroupsWithKey$runtime", "getCurrentErrors", "", "Landroidx/compose/runtime/RecomposerErrorInfo;", "getCurrentErrors$runtime", "clearErrors", "clearErrors$runtime", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final StateFlow<Set<RecomposerInfo>> getRunningRecomposers() {
            return Recomposer._runningRecomposers;
        }

        public final Set<RecomposerInfo> currentRunningRecomposers$runtime() {
            return (Set) Recomposer._runningRecomposers.getValue();
        }

        public final void setHotReloadEnabled$runtime(boolean value) {
            Recomposer._hotReloadEnabled.set(Boolean.valueOf(value));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addRunning(RecomposerInfoImpl info) {
            PersistentSet old;
            PersistentSet persistentSetAdd;
            do {
                old = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetAdd = old.add(info);
                if (old == persistentSetAdd) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.compareAndSet(old, persistentSetAdd));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void removeRunning(RecomposerInfoImpl info) {
            PersistentSet old;
            PersistentSet persistentSetRemove;
            do {
                old = (PersistentSet) Recomposer._runningRecomposers.getValue();
                persistentSetRemove = old.remove(info);
                if (old == persistentSetRemove) {
                    return;
                }
            } while (!Recomposer._runningRecomposers.compareAndSet(old, persistentSetRemove));
        }

        public final Object saveStateAndDisposeForHotReload$runtime() {
            Recomposer._hotReloadEnabled.set(true);
            Iterable $this$flatMap$iv = (Iterable) Recomposer._runningRecomposers.getValue();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$flatMap$iv) {
                RecomposerInfoImpl it = (RecomposerInfoImpl) element$iv$iv;
                Iterable list$iv$iv = it.saveStateAndDisposeForHotReload();
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            }
            return (List) destination$iv$iv;
        }

        public final void loadStateAndComposeForHotReload$runtime(Object token) {
            Recomposer._hotReloadEnabled.set(true);
            Iterable $this$forEach$iv = (Iterable) Recomposer._runningRecomposers.getValue();
            for (Object element$iv : $this$forEach$iv) {
                RecomposerInfoImpl it = (RecomposerInfoImpl) element$iv;
                it.resetErrorState();
            }
            Intrinsics.checkNotNull(token, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.runtime.Recomposer.HotReloadable>");
            List holders = (List) token;
            int size = holders.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = holders.get(index$iv);
                HotReloadable it2 = (HotReloadable) item$iv;
                it2.resetContent();
            }
            int size2 = holders.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = holders.get(index$iv2);
                HotReloadable it3 = (HotReloadable) item$iv2;
                it3.recompose();
            }
            Iterable $this$forEach$iv2 = (Iterable) Recomposer._runningRecomposers.getValue();
            for (Object element$iv2 : $this$forEach$iv2) {
                RecomposerInfoImpl it4 = (RecomposerInfoImpl) element$iv2;
                it4.retryFailedCompositions();
            }
        }

        public final void invalidateGroupsWithKey$runtime(int key) throws Throwable {
            Recomposer._hotReloadEnabled.set(true);
            Iterable $this$forEach$iv = (Iterable) Recomposer._runningRecomposers.getValue();
            for (Object element$iv : $this$forEach$iv) {
                RecomposerInfoImpl it = (RecomposerInfoImpl) element$iv;
                RecomposerErrorInfo currentError = it.getCurrentError();
                boolean z = false;
                if (currentError != null && !currentError.getRecoverable()) {
                    z = true;
                }
                if (!z) {
                    it.resetErrorState();
                    it.invalidateGroupsWithKey(key);
                    it.retryFailedCompositions();
                }
            }
        }

        public final List<RecomposerErrorInfo> getCurrentErrors$runtime() {
            Iterable $this$mapNotNull$iv = (Iterable) Recomposer._runningRecomposers.getValue();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                RecomposerInfoImpl it = (RecomposerInfoImpl) element$iv$iv$iv;
                RecomposerErrorInfo currentError = it.getCurrentError();
                if (currentError != null) {
                    destination$iv$iv.add(currentError);
                }
            }
            return (List) destination$iv$iv;
        }

        public final void clearErrors$runtime() {
            Iterable $this$mapNotNull$iv = (Iterable) Recomposer._runningRecomposers.getValue();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                RecomposerInfoImpl it = (RecomposerInfoImpl) element$iv$iv$iv;
                RecomposerErrorState recomposerErrorStateResetErrorState = it.resetErrorState();
                if (recomposerErrorStateResetErrorState != null) {
                    destination$iv$iv.add(recomposerErrorStateResetErrorState);
                }
            }
        }
    }
}
