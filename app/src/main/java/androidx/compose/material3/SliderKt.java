package androidx.compose.material3;

import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.AccessibilityUtilKt;
import androidx.compose.material3.internal.DragGestureDetectorCopyKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.VerticalAlignmentLine;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.profileinstaller.ProfileVerifier;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: Slider.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a}\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0003\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001a³\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0003\u0010\f\u001a\u00020\r2\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0007¢\u0006\u0002\u0010\u0019\u001as\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00162\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u001b\u001a}\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00162\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0017H\u0001¢\u0006\u0002\u0010\u001e\u001a\u007f\u0010\u001f\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0003\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010 \u001aä\u0001\u0010\u001f\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00132\b\b\u0002\u0010\"\u001a\u00020\u00132\u0019\b\u0002\u0010#\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0019\b\u0002\u0010%\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\b\b\u0003\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010&\u001a\u0098\u0001\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020$2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00132\b\b\u0002\u0010\"\u001a\u00020\u00132\u0019\b\u0002\u0010#\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0019\b\u0002\u0010%\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010'\u001a_\u0010(\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0017H\u0003¢\u0006\u0002\u0010)\u001a`\u0010*\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\t2\u0014\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000fH\u0002\u001a\u0080\u0001\u0010-\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020$2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\u0017\u0010#\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0017\u0010%\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00172\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0017H\u0003¢\u0006\u0002\u0010.\u001a(\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u00020\u0003H\u0002\u001a2\u00105\u001a\u0010\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u0003\u0018\u000106*\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0082@¢\u0006\u0004\b=\u0010>\u001a\u0010\u0010?\u001a\u0002022\u0006\u0010\f\u001a\u00020\rH\u0002\u001a0\u0010@\u001a\u00020\u00032\u0006\u0010A\u001a\u00020\u00032\u0006\u0010B\u001a\u00020\u00032\u0006\u0010C\u001a\u00020\u00032\u0006\u0010D\u001a\u00020\u00032\u0006\u0010E\u001a\u00020\u0003H\u0002\u001a?\u0010@\u001a\u00020F2\u0006\u0010G\u001a\u00020\t2\u0006\u0010A\u001a\u00020\u00032\u0006\u0010B\u001a\u00020\u00032\u0006\u0010H\u001a\u00020F2\u0006\u0010D\u001a\u00020\u00032\u0006\u0010E\u001a\u00020\u0003H\u0002¢\u0006\u0004\bI\u0010J\u001a \u0010K\u001a\u00020\u00032\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u00032\u0006\u0010N\u001a\u00020\u0003H\u0002\u001a\u001c\u0010O\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u001c\u0010P\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u001a\u001a\u00020$2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u001c\u0010Q\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u001a\u001a\u00020$2\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\f\u0010R\u001a\u00020S*\u00020\u0003H\u0002\u001a$\u0010T\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\tH\u0003\u001a,\u0010U\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u001a\u001a\u00020$2\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\tH\u0003\u001aC\u0010d\u001a\u00020\u00162\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0007¢\u0006\u0002\u0010e\u001aM\u0010f\u001a\u00020$2\b\b\u0002\u0010g\u001a\u00020\u00032\b\b\u0002\u0010h\u001a\u00020\u00032\b\b\u0003\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000f2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0007¢\u0006\u0002\u0010i\u001a\u001d\u0010j\u001a\u00020F2\u0006\u0010k\u001a\u00020\u00032\u0006\u0010l\u001a\u00020\u0003H\u0001¢\u0006\u0002\u0010m\u001a\u001b\u0010j\u001a\u00020F2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0001¢\u0006\u0002\u0010o\"\u0016\u0010V\u001a\u00020WX\u0080\u0004¢\u0006\n\n\u0002\u0010Z\u001a\u0004\bX\u0010Y\"\u0016\u0010[\u001a\u00020WX\u0080\u0004¢\u0006\n\n\u0002\u0010Z\u001a\u0004\b\\\u0010Y\"\u0010\u0010]\u001a\u00020WX\u0082\u0004¢\u0006\u0004\n\u0002\u0010Z\"\u0010\u0010^\u001a\u00020_X\u0082\u0004¢\u0006\u0004\n\u0002\u0010`\"\u0010\u0010a\u001a\u00020_X\u0082\u0004¢\u0006\u0004\n\u0002\u0010`\"\u0010\u0010b\u001a\u00020WX\u0082\u0004¢\u0006\u0004\n\u0002\u0010Z\"\u0010\u0010c\u001a\u00020WX\u0082\u0004¢\u0006\u0004\n\u0002\u0010Z\"\u001e\u0010p\u001a\u00020\t*\u00020F8@X\u0081\u0004¢\u0006\f\u0012\u0004\bq\u0010r\u001a\u0004\bs\u0010t\"\u0014\u0010u\u001a\u00020vX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bw\u0010x¨\u0006y"}, d2 = {"Slider", "", "value", "", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "valueRange", "Lkotlin/ranges/ClosedFloatingPointRange;", "steps", "", "onValueChangeFinished", "Lkotlin/Function0;", "colors", "Landroidx/compose/material3/SliderColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "thumb", "Landroidx/compose/material3/SliderState;", "Landroidx/compose/runtime/Composable;", "track", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;ILkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/Composer;III)V", "state", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "VerticalSlider", "reverseDirection", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "RangeSlider", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;ILkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/runtime/Composer;II)V", "startInteractionSource", "endInteractionSource", "startThumb", "Landroidx/compose/material3/RangeSliderState;", "endThumb", "(Lkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/ranges/ClosedFloatingPointRange;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;ILandroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "slideOnKeyEvents", "onValueChangeState", "onValueChangeFinishedState", "RangeSliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/RangeSliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "snapValueToTick", "current", "tickFractions", "", "minPx", "maxPx", "awaitSlop", "Lkotlin/Pair;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "id", "Landroidx/compose/ui/input/pointer/PointerId;", "type", "Landroidx/compose/ui/input/pointer/PointerType;", "awaitSlop-8vUncbI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stepsToTickFractions", "scale", "a1", "b1", "x1", "a2", "b2", "Landroidx/compose/material3/SliderRange;", "isStart", "x", "scale-2geJ7wY", "(ZFFJFF)J", "calcFraction", CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, "b", "pos", "sliderSemantics", "rangeSliderStartThumbSemantics", "rangeSliderEndThumbSemantics", "formatForSemantics", "", "sliderTapModifier", "rangeSliderPressDragModifier", "TrackHeight", "Landroidx/compose/ui/unit/Dp;", "getTrackHeight", "()F", "F", "ThumbWidth", "getThumbWidth", "ThumbHeight", "ThumbSize", "Landroidx/compose/ui/unit/DpSize;", "J", "VerticalThumbSize", "ThumbTrackGapSize", "TrackInsideCornerSize", "rememberSliderState", "(FILkotlin/jvm/functions/Function0;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SliderState;", "rememberRangeSliderState", "activeRangeStart", "activeRangeEnd", "(FFILkotlin/jvm/functions/Function0;Lkotlin/ranges/ClosedFloatingPointRange;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/RangeSliderState;", "SliderRange", TtmlNode.START, "endInclusive", "(FF)J", "range", "(Lkotlin/ranges/ClosedFloatingPointRange;)J", "isSpecified", "isSpecified-If1S1O4$annotations", "(J)V", "isSpecified-If1S1O4", "(J)Z", "CornerSizeAlignmentLine", "Landroidx/compose/ui/layout/VerticalAlignmentLine;", "getCornerSizeAlignmentLine", "()Landroidx/compose/ui/layout/VerticalAlignmentLine;", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SliderKt {
    private static final float TrackHeight = SliderTokens.INSTANCE.m4088getInactiveTrackHeightD9Ej5fM();
    private static final float ThumbWidth = SliderTokens.INSTANCE.m4086getHandleWidthD9Ej5fM();
    private static final float ThumbHeight = SliderTokens.INSTANCE.m4085getHandleHeightD9Ej5fM();
    private static final long ThumbSize = DpKt.m7927DpSizeYgX7TsA(ThumbWidth, ThumbHeight);
    private static final long VerticalThumbSize = DpKt.m7927DpSizeYgX7TsA(ThumbHeight, ThumbWidth);
    private static final float ThumbTrackGapSize = SliderTokens.INSTANCE.m4078getActiveHandleLeadingSpaceD9Ej5fM();
    private static final float TrackInsideCornerSize = Dp.m7905constructorimpl(2);
    private static final VerticalAlignmentLine CornerSizeAlignmentLine = new VerticalAlignmentLine(SliderKt$CornerSizeAlignmentLine$1.INSTANCE);

    static final Unit RangeSlider$lambda$13(ClosedFloatingPointRange closedFloatingPointRange, Function1 function1, Modifier modifier, boolean z, ClosedFloatingPointRange closedFloatingPointRange2, int i, Function0 function0, SliderColors sliderColors, int i2, int i3, Composer composer, int i4) {
        RangeSlider(closedFloatingPointRange, function1, modifier, z, closedFloatingPointRange2, i, function0, sliderColors, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit RangeSlider$lambda$19(ClosedFloatingPointRange closedFloatingPointRange, Function1 function1, Modifier modifier, boolean z, ClosedFloatingPointRange closedFloatingPointRange2, Function0 function0, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, Function3 function3, Function3 function4, Function3 function5, int i, int i2, int i3, int i4, Composer composer, int i5) {
        RangeSlider(closedFloatingPointRange, function1, modifier, z, closedFloatingPointRange2, function0, sliderColors, mutableInteractionSource, mutableInteractionSource2, function3, function4, function5, i, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit RangeSlider$lambda$23(RangeSliderState rangeSliderState, Modifier modifier, boolean z, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, Function3 function3, Function3 function4, Function3 function5, int i, int i2, Composer composer, int i3) {
        RangeSlider(rangeSliderState, modifier, z, sliderColors, mutableInteractionSource, mutableInteractionSource2, function3, function4, function5, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit RangeSliderImpl$lambda$46(Modifier modifier, RangeSliderState rangeSliderState, boolean z, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, Function3 function3, Function3 function4, Function3 function5, int i, Composer composer, int i2) {
        RangeSliderImpl(modifier, rangeSliderState, z, mutableInteractionSource, mutableInteractionSource2, function3, function4, function5, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit Slider$lambda$1(float f, Function1 function1, Modifier modifier, boolean z, ClosedFloatingPointRange closedFloatingPointRange, int i, Function0 function0, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, int i2, int i3, Composer composer, int i4) {
        Slider(f, function1, modifier, z, closedFloatingPointRange, i, function0, sliderColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit Slider$lambda$4(float f, Function1 function1, Modifier modifier, boolean z, Function0 function0, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, int i, Function3 function3, Function3 function4, ClosedFloatingPointRange closedFloatingPointRange, int i2, int i3, int i4, Composer composer, int i5) {
        Slider(f, function1, modifier, z, function0, sliderColors, mutableInteractionSource, i, function3, function4, closedFloatingPointRange, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit Slider$lambda$7(SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, Function3 function3, Function3 function4, int i, int i2, Composer composer, int i3) {
        Slider(sliderState, modifier, z, sliderColors, mutableInteractionSource, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SliderImpl$lambda$31(Modifier modifier, SliderState sliderState, boolean z, MutableInteractionSource mutableInteractionSource, Function3 function3, Function3 function4, int i, Composer composer, int i2) {
        SliderImpl(modifier, sliderState, z, mutableInteractionSource, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit VerticalSlider$lambda$10(SliderState sliderState, Modifier modifier, boolean z, boolean z2, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, Function3 function3, Function3 function4, int i, int i2, Composer composer, int i3) {
        VerticalSlider(sliderState, modifier, z, z2, sliderColors, mutableInteractionSource, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: isSpecified-If1S1O4$annotations, reason: not valid java name */
    public static /* synthetic */ void m2896isSpecifiedIf1S1O4$annotations(long j) {
    }

    public static final void Slider(final float value, final Function1<? super Float, Unit> function1, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange, int steps, Function0<Unit> function0, SliderColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        float f;
        Function1<? super Float, Unit> function2;
        Modifier modifier2;
        boolean enabled2;
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
        int steps2;
        Function0<Unit> function3;
        Composer $composer2;
        final SliderColors colors2;
        final Modifier modifier3;
        final boolean enabled3;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange2;
        final int steps3;
        final Function0<Unit> function4;
        final MutableInteractionSource interactionSource2;
        int $dirty;
        final SliderColors colors3;
        final MutableInteractionSource interactionSource3;
        Modifier modifier4;
        ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        int steps4;
        Function0<Unit> function5;
        int i2;
        final boolean enabled4;
        Composer $composer3 = $composer.startRestartGroup(-202044027);
        ComposerKt.sourceInformation($composer3, "C(Slider)N(value,onValueChange,modifier,enabled,valueRange,steps,onValueChangeFinished,colors,interactionSource)201@9624L182,208@9824L122,192@9332L654:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            f = value;
        } else if (($changed & 6) == 0) {
            f = value;
            $dirty2 |= $composer3.changed(f) ? 4 : 2;
        } else {
            f = value;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function2 = function1;
        } else if (($changed & 48) == 0) {
            function2 = function1;
            $dirty2 |= $composer3.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = function1;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                closedFloatingPointRangeRangeTo = closedFloatingPointRange;
                int i5 = $composer3.changed(closedFloatingPointRangeRangeTo) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                closedFloatingPointRangeRangeTo = closedFloatingPointRange;
            }
            $dirty2 |= i5;
        } else {
            closedFloatingPointRangeRangeTo = closedFloatingPointRange;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            steps2 = steps;
        } else if ((196608 & $changed) == 0) {
            steps2 = steps;
            $dirty2 |= $composer3.changed(steps2) ? 131072 : 65536;
        } else {
            steps2 = steps;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            function3 = function0;
        } else if ((1572864 & $changed) == 0) {
            function3 = function0;
            $dirty2 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        } else {
            function3 = function0;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer3.changed(colors)) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty3 = $dirty2;
        if (!$composer3.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty3 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            colors2 = colors;
            modifier3 = modifier2;
            enabled3 = enabled2;
            closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
            steps3 = steps2;
            function4 = function3;
            interactionSource2 = interactionSource;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "189@9223L8,190@9283L39");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 16) != 0 ? $dirty3 & (-57345) : $dirty3;
                if ((i & 128) != 0) {
                    $dirty4 &= -29360129;
                }
                interactionSource3 = interactionSource;
                $dirty = $dirty4;
                modifier4 = modifier2;
                closedFloatingPointRange3 = closedFloatingPointRangeRangeTo;
                steps4 = steps2;
                function5 = function3;
                i2 = -202044027;
                colors3 = colors;
                enabled4 = enabled2;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) == 0) {
                    $dirty = $dirty3;
                } else {
                    $dirty = $dirty3 & (-57345);
                    closedFloatingPointRangeRangeTo = RangesKt.rangeTo(0.0f, 1.0f);
                }
                if (i6 != 0) {
                    steps2 = 0;
                }
                if (i7 != 0) {
                    function3 = null;
                }
                if ((i & 128) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = SliderDefaults.INSTANCE.colors($composer3, 6);
                    $dirty &= -29360129;
                }
                if (i8 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -890767796, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    SliderColors colors4 = colors3;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    modifier4 = modifier2;
                    closedFloatingPointRange3 = closedFloatingPointRangeRangeTo;
                    steps4 = steps2;
                    function5 = function3;
                    i2 = -202044027;
                    colors3 = colors4;
                    enabled4 = enabled2;
                } else {
                    interactionSource3 = interactionSource;
                    modifier4 = modifier2;
                    closedFloatingPointRange3 = closedFloatingPointRangeRangeTo;
                    steps4 = steps2;
                    function5 = function3;
                    i2 = -202044027;
                    enabled4 = enabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.Slider (Slider.kt:191)");
            }
            SliderColors colors5 = colors3;
            $composer2 = $composer3;
            MutableInteractionSource interactionSource4 = interactionSource3;
            Slider(f, function2, modifier4, enabled4, function5, colors5, interactionSource4, steps4, ComposableLambdaKt.rememberComposableLambda(308249025, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.Slider.2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer, Integer num) {
                    invoke(sliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(SliderState it, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "CN(it)202@9653L143:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(308249025, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:202)");
                    }
                    SliderDefaults.INSTANCE.m2877Thumb9LiSoMs(interactionSource3, null, colors3, enabled4, 0L, $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-1843234110, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.Slider.3
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer, Integer num) {
                    invoke(sliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(SliderState sliderState, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "CN(sliderState)209@9868L68:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1843234110, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:209)");
                    }
                    SliderDefaults.INSTANCE.m2880Track4EFweAY(sliderState, (Modifier) null, enabled4, colors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer4, ($changed2 & 14) | 100663296, 242);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), closedFloatingPointRange3, $composer2, ($dirty & 14) | 905969664 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 6) & 57344) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016) | (($dirty << 6) & 29360128), ($dirty >> 12) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled3 = enabled4;
            function4 = function5;
            colors2 = colors5;
            interactionSource2 = interactionSource4;
            steps3 = steps4;
            closedFloatingPointRange2 = closedFloatingPointRange3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderKt.Slider$lambda$1(value, function1, modifier3, enabled3, closedFloatingPointRange2, steps3, function4, colors2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:193:0x0333  */
    public static final void Slider(final float value, final Function1<? super Float, Unit> function1, Modifier modifier, boolean enabled, Function0<Unit> function0, SliderColors colors, MutableInteractionSource interactionSource, int steps, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function4, ClosedFloatingPointRange<Float> closedFloatingPointRange, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        final boolean enabled2;
        Function0<Unit> function2;
        final SliderColors colors2;
        Composer $composer2;
        final MutableInteractionSource interactionSource2;
        final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function5;
        final SliderColors colors3;
        final Modifier modifier3;
        final boolean enabled3;
        final int steps2;
        final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function6;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange2;
        final Function0<Unit> function7;
        final MutableInteractionSource interactionSource3;
        int steps3;
        ComposableLambda composableLambdaRememberComposableLambda;
        ComposableLambda composableLambdaRememberComposableLambda2;
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
        int $dirty;
        int $dirty1;
        Composer $composer3;
        Composer $composer4 = $composer.startRestartGroup(985901935);
        ComposerKt.sourceInformation($composer4, "C(Slider)N(value,onValueChange,modifier,enabled,onValueChangeFinished,colors,interactionSource,steps,thumb,track,valueRange)294@13990L92,299@14208L185:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty3 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer4.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer4.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            function2 = function0;
        } else if (($changed & 24576) == 0) {
            function2 = function0;
            $dirty2 |= $composer4.changedInstance(function2) ? 16384 : 8192;
        } else {
            function2 = function0;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i5 = $composer4.changed(colors2) ? 131072 : 65536;
                $dirty2 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer4.changed(interactionSource) ? 1048576 : 524288;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer4.changed(steps) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty2 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer4.changedInstance(function4) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty3 |= ((i & 1024) == 0 && $composer4.changed(closedFloatingPointRange)) ? 4 : 2;
        }
        int $dirty4 = $dirty2;
        int $dirty5 = $dirty3;
        if ($composer4.shouldExecute((($dirty2 & 306783379) == 306783378 && ($dirty5 & 3) == 2) ? false : true, $dirty4 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "278@13393L8,279@13453L39,281@13581L158,288@13788L114");
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if (i4 != 0) {
                    function2 = null;
                }
                if ((i & 32) != 0) {
                    colors2 = SliderDefaults.INSTANCE.colors($composer4, 6);
                    $dirty4 &= -458753;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer4, -1736002634, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv = $composer4.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer4.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    interactionSource3 = (MutableInteractionSource) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                } else {
                    interactionSource3 = interactionSource;
                }
                steps3 = i7 != 0 ? 0 : steps;
                composableLambdaRememberComposableLambda = i8 != 0 ? ComposableLambdaKt.rememberComposableLambda(-1689130945, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.Slider.6
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer, Integer num) {
                        invoke(sliderState, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(SliderState it, Composer $composer5, int $changed2) {
                        ComposerKt.sourceInformation($composer5, "CN(it)282@13606L127:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1689130945, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:282)");
                        }
                        SliderDefaults.INSTANCE.m2877Thumb9LiSoMs(interactionSource3, null, colors2, enabled2, 0L, $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer4, 54) : function3;
                composableLambdaRememberComposableLambda2 = i9 != 0 ? ComposableLambdaKt.rememberComposableLambda(-294493388, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.Slider.7
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer, Integer num) {
                        invoke(sliderState, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(SliderState sliderState, Composer $composer5, int $changed2) {
                        ComposerKt.sourceInformation($composer5, "CN(sliderState)289@13828L68:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-294493388, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:289)");
                        }
                        SliderDefaults.INSTANCE.m2880Track4EFweAY(sliderState, (Modifier) null, enabled2, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer5, ($changed2 & 14) | 100663296, 242);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer4, 54) : function4;
                if ((i & 1024) != 0) {
                    closedFloatingPointRangeRangeTo = RangesKt.rangeTo(0.0f, 1.0f);
                    $dirty1 = $dirty5 & (-15);
                    $dirty = $dirty4;
                } else {
                    closedFloatingPointRangeRangeTo = closedFloatingPointRange;
                    $dirty = $dirty4;
                    $dirty1 = $dirty5;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty4 &= -458753;
                }
                if ((i & 1024) != 0) {
                    composableLambdaRememberComposableLambda = function3;
                    composableLambdaRememberComposableLambda2 = function4;
                    closedFloatingPointRangeRangeTo = closedFloatingPointRange;
                    $dirty1 = $dirty5 & (-15);
                    $dirty = $dirty4;
                    interactionSource3 = interactionSource;
                    steps3 = steps;
                } else {
                    interactionSource3 = interactionSource;
                    composableLambdaRememberComposableLambda = function3;
                    composableLambdaRememberComposableLambda2 = function4;
                    closedFloatingPointRangeRangeTo = closedFloatingPointRange;
                    $dirty = $dirty4;
                    $dirty1 = $dirty5;
                    steps3 = steps;
                }
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(985901935, $dirty, $dirty1, "androidx.compose.material3.Slider (Slider.kt:292)");
            }
            ComposerKt.sourceInformationMarkerStart($composer4, -1735985397, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = ((29360128 & $dirty) == 8388608) | (((($dirty1 & 14) ^ 6) > 4 && $composer4.changed(closedFloatingPointRangeRangeTo)) || ($dirty1 & 6) == 4);
            Object value$iv2 = $composer4.rememberedValue();
            if (invalid$iv) {
                $composer3 = $composer4;
            } else {
                $composer3 = $composer4;
                if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                }
                SliderState state = (SliderState) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                state.setOnValueChangeFinished(function2);
                state.setOnValueChange(function1);
                state.setValue(value);
                Composer $composer5 = $composer3;
                Slider(state, modifier2, enabled2, null, interactionSource3, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, $composer5, (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 6) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016), 8);
                MutableInteractionSource interactionSource4 = interactionSource3;
                Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function8 = composableLambdaRememberComposableLambda;
                $composer2 = $composer5;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function9 = composableLambdaRememberComposableLambda2;
                interactionSource2 = interactionSource4;
                modifier3 = modifier2;
                function6 = function8;
                colors3 = colors2;
                steps2 = steps3;
                enabled3 = enabled2;
                closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
                function5 = function9;
                function7 = function2;
            }
            value$iv2 = new SliderState(value, steps3, function2, closedFloatingPointRangeRangeTo);
            $composer4.updateRememberedValue(value$iv2);
            SliderState state2 = (SliderState) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            state2.setOnValueChangeFinished(function2);
            state2.setOnValueChange(function1);
            state2.setValue(value);
            Composer $composer6 = $composer3;
            Slider(state2, modifier2, enabled2, null, interactionSource3, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, $composer6, (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 6) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016), 8);
            MutableInteractionSource interactionSource5 = interactionSource3;
            Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function10 = composableLambdaRememberComposableLambda;
            $composer2 = $composer6;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function11 = composableLambdaRememberComposableLambda2;
            interactionSource2 = interactionSource5;
            modifier3 = modifier2;
            function6 = function10;
            colors3 = colors2;
            steps2 = steps3;
            enabled3 = enabled2;
            closedFloatingPointRange2 = closedFloatingPointRangeRangeTo;
            function5 = function11;
            function7 = function2;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            interactionSource2 = interactionSource;
            function5 = function4;
            colors3 = colors2;
            modifier3 = modifier2;
            enabled3 = enabled2;
            steps2 = steps;
            function6 = function3;
            closedFloatingPointRange2 = closedFloatingPointRange;
            function7 = function2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderKt.Slider$lambda$4(value, function1, modifier3, enabled3, function7, colors3, interactionSource2, steps2, function6, function5, closedFloatingPointRange2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void Slider(final SliderState state, Modifier modifier, boolean enabled, SliderColors colors, MutableInteractionSource interactionSource, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        SliderState sliderState;
        boolean z;
        final SliderColors colors2;
        final MutableInteractionSource interactionSource2;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda2;
        final Modifier modifier2;
        final boolean enabled2;
        final SliderColors colors3;
        final MutableInteractionSource interactionSource3;
        final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function5;
        final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function6;
        boolean z2;
        boolean enabled3;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function7;
        MutableInteractionSource interactionSource4;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(409861960);
        ComposerKt.sourceInformation($composer2, "C(Slider)N(state,modifier,enabled,colors,interactionSource,thumb,track)374@17399L189:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            sliderState = state;
        } else if (($changed & 6) == 0) {
            sliderState = state;
            $dirty |= $composer2.changedInstance(sliderState) ? 4 : 2;
        } else {
            sliderState = state;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i4 = $composer2.changed(colors2) ? 2048 : 1024;
                $dirty |= i4;
            } else {
                colors2 = colors;
            }
            $dirty |= i4;
        } else {
            colors2 = colors;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            interactionSource2 = interactionSource;
        } else if (($changed & 24576) == 0) {
            interactionSource2 = interactionSource;
            $dirty |= $composer2.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function3RememberComposableLambda = function3;
        } else if ((196608 & $changed) == 0) {
            function3RememberComposableLambda = function3;
            $dirty |= $composer2.changedInstance(function3RememberComposableLambda) ? 131072 : 65536;
        } else {
            function3RememberComposableLambda = function3;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
            function3RememberComposableLambda2 = function4;
        } else if ((1572864 & $changed) == 0) {
            function3RememberComposableLambda2 = function4;
            $dirty |= $composer2.changedInstance(function3RememberComposableLambda2) ? 1048576 : 524288;
        } else {
            function3RememberComposableLambda2 = function4;
        }
        if ($composer2.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "359@16862L8,360@16922L39,361@17010L158,368@17217L114");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier;
                final boolean enabled4 = i3 != 0 ? true : z;
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    colors2 = SliderDefaults.INSTANCE.colors($composer2, 6);
                }
                if (i5 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, -1259639953, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv = $composer2.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer2.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    interactionSource2 = (MutableInteractionSource) it$iv;
                }
                if (i6 != 0) {
                    z2 = true;
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-2100927368, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.Slider.10
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState2, Composer composer, Integer num) {
                            invoke(sliderState2, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SliderState it, Composer $composer3, int $changed2) {
                            ComposerKt.sourceInformation($composer3, "CN(it)362@17035L127:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2100927368, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:362)");
                            }
                            SliderDefaults.INSTANCE.m2877Thumb9LiSoMs(interactionSource2, null, colors2, enabled4, 0L, $composer3, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54);
                } else {
                    z2 = true;
                }
                if (i7 != 0) {
                    function3RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-81224541, z2, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.Slider.11
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState2, Composer composer, Integer num) {
                            invoke(sliderState2, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SliderState sliderState2, Composer $composer3, int $changed2) {
                            ComposerKt.sourceInformation($composer3, "CN(sliderState)369@17257L68:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-81224541, $changed2, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:369)");
                            }
                            SliderDefaults.INSTANCE.m2880Track4EFweAY(sliderState2, (Modifier) null, enabled4, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer3, ($changed2 & 14) | 100663296, 242);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer2, 54);
                    enabled3 = enabled4;
                    function7 = function3RememberComposableLambda;
                    interactionSource4 = interactionSource2;
                    modifier3 = modifier4;
                } else {
                    enabled3 = enabled4;
                    function7 = function3RememberComposableLambda;
                    interactionSource4 = interactionSource2;
                    modifier3 = modifier4;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                enabled3 = z;
                function7 = function3RememberComposableLambda;
                z2 = true;
                interactionSource4 = interactionSource2;
                modifier3 = modifier;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(409861960, $dirty, -1, "androidx.compose.material3.Slider (Slider.kt:371)");
            }
            if (!(sliderState.getSteps() >= 0 ? z2 : false)) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            SliderImpl(modifier3, sliderState, enabled3, interactionSource4, function7, function3RememberComposableLambda2, $composer2, (($dirty >> 3) & 14) | (($dirty << 3) & 112) | ($dirty & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors3 = colors2;
            modifier2 = modifier3;
            enabled2 = enabled3;
            interactionSource3 = interactionSource4;
            function5 = function7;
            function6 = function3RememberComposableLambda2;
        } else {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            enabled2 = z;
            colors3 = colors2;
            interactionSource3 = interactionSource2;
            function5 = function3RememberComposableLambda;
            function6 = function3RememberComposableLambda2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderKt.Slider$lambda$7(state, modifier2, enabled2, colors3, interactionSource3, function5, function6, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void VerticalSlider(final SliderState state, Modifier modifier, boolean enabled, boolean reverseDirection, SliderColors colors, MutableInteractionSource interactionSource, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final boolean enabled2;
        boolean reverseDirection2;
        final SliderColors colors2;
        final MutableInteractionSource interactionSource2;
        Composer $composer2;
        final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function5;
        final boolean enabled3;
        final boolean reverseDirection3;
        final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function6;
        final Modifier modifier3;
        final SliderColors colors3;
        final MutableInteractionSource interactionSource3;
        int $dirty;
        boolean z;
        ComposableLambda composableLambdaRememberComposableLambda;
        ComposableLambda composableLambdaRememberComposableLambda2;
        Composer $composer3 = $composer.startRestartGroup(-1841025790);
        ComposerKt.sourceInformation($composer3, "C(VerticalSlider)N(state,modifier,enabled,reverseDirection,colors,interactionSource,thumb,track)444@20428L189:Slider.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changedInstance(state) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            reverseDirection2 = reverseDirection;
        } else if (($changed & 3072) == 0) {
            reverseDirection2 = reverseDirection;
            $dirty2 |= $composer3.changed(reverseDirection2) ? 2048 : 1024;
        } else {
            reverseDirection2 = reverseDirection;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i5;
        } else {
            colors2 = colors;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            interactionSource2 = interactionSource;
        } else if ((196608 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty2 |= $composer3.changed(interactionSource2) ? 131072 : 65536;
        } else {
            interactionSource2 = interactionSource;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 8388608 : 4194304;
        }
        int $dirty3 = $dirty2;
        if ($composer3.shouldExecute(($dirty2 & 4793491) != 4793490, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "419@19613L8,420@19673L39,421@19761L255,430@20065L207");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if (i4 != 0) {
                    reverseDirection2 = false;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    colors2 = SliderDefaults.INSTANCE.colors($composer3, 6);
                } else {
                    $dirty = $dirty3;
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 300751721, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    interactionSource2 = (MutableInteractionSource) it$iv;
                }
                if (i7 != 0) {
                    z = true;
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1896624690, true, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.VerticalSlider.2
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer, Integer num) {
                            invoke(sliderState, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(SliderState sliderState, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "CN(sliderState)422@19801L209:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1896624690, $changed2, -1, "androidx.compose.material3.VerticalSlider.<anonymous> (Slider.kt:422)");
                            }
                            SliderDefaults.INSTANCE.m2878ThumbHwbPF3A$material3(interactionSource2, sliderState, null, colors2, enabled2, SliderKt.VerticalThumbSize, $composer4, (($changed2 << 3) & 112) | 1769472, 4);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                } else {
                    z = true;
                    composableLambdaRememberComposableLambda = function3;
                }
                composableLambdaRememberComposableLambda2 = i8 != 0 ? ComposableLambdaKt.rememberComposableLambda(-1702448035, z, new Function3<SliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.VerticalSlider.3
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(SliderState sliderState, Composer composer, Integer num) {
                        invoke(sliderState, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(SliderState sliderState, Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "CN(sliderState)431@20105L161:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1702448035, $changed2, -1, "androidx.compose.material3.VerticalSlider.<anonymous> (Slider.kt:431)");
                        }
                        SliderDefaults.INSTANCE.m2882TrackmnvyFg4$material3(sliderState, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM(), (Modifier) null, enabled2, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer4, ($changed2 & 14) | 805306416, 484);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54) : function4;
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    composableLambdaRememberComposableLambda = function3;
                    composableLambdaRememberComposableLambda2 = function4;
                    z = true;
                } else {
                    composableLambdaRememberComposableLambda = function3;
                    composableLambdaRememberComposableLambda2 = function4;
                    $dirty = $dirty3;
                    z = true;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1841025790, $dirty, -1, "androidx.compose.material3.VerticalSlider (Slider.kt:438)");
            }
            if (!(state.getSteps() >= 0 ? z : false)) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            state.setOrientation$material3(Orientation.Vertical);
            state.setReverseVerticalDirection$material3(reverseDirection2);
            SliderImpl(modifier2, state, enabled2, interactionSource2, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, $composer3, (($dirty >> 3) & 14) | (($dirty << 3) & 112) | ($dirty & 896) | (($dirty >> 6) & 7168) | (($dirty >> 6) & 57344) | (($dirty >> 6) & 458752));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled3 = enabled2;
            function6 = composableLambdaRememberComposableLambda;
            reverseDirection3 = reverseDirection2;
            function5 = composableLambdaRememberComposableLambda2;
            modifier3 = modifier2;
            colors3 = colors2;
            interactionSource3 = interactionSource2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function5 = function4;
            enabled3 = enabled2;
            reverseDirection3 = reverseDirection2;
            function6 = function3;
            modifier3 = modifier2;
            colors3 = colors2;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderKt.VerticalSlider$lambda$10(state, modifier3, enabled3, reverseDirection3, colors3, interactionSource3, function6, function5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void RangeSlider(final ClosedFloatingPointRange<Float> closedFloatingPointRange, final Function1<? super ClosedFloatingPointRange<Float>, Unit> function1, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange2, int steps, Function0<Unit> function0, SliderColors colors, Composer $composer, final int $changed, final int i) {
        ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        Function1<? super ClosedFloatingPointRange<Float>, Unit> function2;
        Modifier modifier2;
        boolean enabled2;
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
        int steps2;
        Function0<Unit> function3;
        Composer $composer2;
        final SliderColors colors2;
        final Modifier modifier3;
        final boolean enabled3;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange4;
        final int steps3;
        final Function0<Unit> function4;
        Modifier.Companion modifier4;
        int $dirty;
        final SliderColors colors3;
        int $dirty2;
        final boolean enabled4;
        int steps4;
        Function0<Unit> function5;
        Modifier modifier5;
        ClosedFloatingPointRange<Float> closedFloatingPointRange5;
        int $dirty3;
        Composer $composer3 = $composer.startRestartGroup(-743091416);
        ComposerKt.sourceInformation($composer3, "C(RangeSlider)N(value,onValueChange,modifier,enabled,valueRange,steps,onValueChangeFinished,colors)499@23112L39,500@23209L39,512@23627L187,519@23835L185,526@24038L200,502@23254L991:Slider.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            closedFloatingPointRange3 = closedFloatingPointRange;
        } else if (($changed & 6) == 0) {
            closedFloatingPointRange3 = closedFloatingPointRange;
            $dirty4 |= $composer3.changed(closedFloatingPointRange3) ? 4 : 2;
        } else {
            closedFloatingPointRange3 = closedFloatingPointRange;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function2 = function1;
        } else if (($changed & 48) == 0) {
            function2 = function1;
            $dirty4 |= $composer3.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = function1;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty4 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty4 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                closedFloatingPointRangeRangeTo = closedFloatingPointRange2;
                int i4 = $composer3.changed(closedFloatingPointRangeRangeTo) ? 16384 : 8192;
                $dirty4 |= i4;
            } else {
                closedFloatingPointRangeRangeTo = closedFloatingPointRange2;
            }
            $dirty4 |= i4;
        } else {
            closedFloatingPointRangeRangeTo = closedFloatingPointRange2;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            steps2 = steps;
        } else if ((196608 & $changed) == 0) {
            steps2 = steps;
            $dirty4 |= $composer3.changed(steps2) ? 131072 : 65536;
        } else {
            steps2 = steps;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty4 |= 1572864;
            function3 = function0;
        } else if ((1572864 & $changed) == 0) {
            function3 = function0;
            $dirty4 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        } else {
            function3 = function0;
        }
        if (($changed & 12582912) == 0) {
            $dirty4 |= ((i & 128) == 0 && $composer3.changed(colors)) ? 8388608 : 4194304;
        }
        int $dirty5 = $dirty4;
        if (!$composer3.shouldExecute(($dirty4 & 4793491) != 4793490, $dirty5 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            colors2 = colors;
            modifier3 = modifier2;
            enabled3 = enabled2;
            closedFloatingPointRange4 = closedFloatingPointRangeRangeTo;
            steps3 = steps2;
            function4 = function3;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "497@23039L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 16) != 0 ? $dirty5 & (-57345) : $dirty5;
                if ((i & 128) != 0) {
                    $dirty6 &= -29360129;
                }
                $dirty2 = $dirty6;
                enabled4 = enabled2;
                steps4 = steps2;
                function5 = function3;
                $dirty3 = -743091416;
                modifier5 = modifier2;
                closedFloatingPointRange5 = closedFloatingPointRangeRangeTo;
                colors3 = colors;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 != 0) {
                    enabled2 = true;
                }
                if ((i & 16) == 0) {
                    $dirty = $dirty5;
                } else {
                    $dirty = $dirty5 & (-57345);
                    closedFloatingPointRangeRangeTo = RangesKt.rangeTo(0.0f, 1.0f);
                }
                if (i5 != 0) {
                    steps2 = 0;
                }
                if (i6 != 0) {
                    function3 = null;
                }
                if ((i & 128) == 0) {
                    colors3 = colors;
                    $dirty2 = $dirty;
                    enabled4 = enabled2;
                    steps4 = steps2;
                    function5 = function3;
                    modifier5 = modifier4;
                    closedFloatingPointRange5 = closedFloatingPointRangeRangeTo;
                    $dirty3 = -743091416;
                } else {
                    colors3 = SliderDefaults.INSTANCE.colors($composer3, 6);
                    $dirty2 = $dirty & (-29360129);
                    enabled4 = enabled2;
                    steps4 = steps2;
                    function5 = function3;
                    modifier5 = modifier4;
                    closedFloatingPointRange5 = closedFloatingPointRangeRangeTo;
                    $dirty3 = -743091416;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart($dirty3, $dirty2, -1, "androidx.compose.material3.RangeSlider (Slider.kt:498)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1146263569, "CC(remember):Slider.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final MutableInteractionSource startInteractionSource = (MutableInteractionSource) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1146260465, "CC(remember):Slider.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            final MutableInteractionSource endInteractionSource = (MutableInteractionSource) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            RangeSlider(closedFloatingPointRange3, function2, modifier5, enabled4, closedFloatingPointRange5, function5, null, startInteractionSource, endInteractionSource, ComposableLambdaKt.rememberComposableLambda(-811582901, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSlider.1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer, Integer num) {
                    invoke(rangeSliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "CN(it)513@23656L148:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-811582901, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:513)");
                    }
                    SliderDefaults.INSTANCE.m2877Thumb9LiSoMs(startInteractionSource, null, colors3, enabled4, 0L, $composer4, 196614, 18);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-1832060001, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSlider.2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer, Integer num) {
                    invoke(rangeSliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "CN(it)520@23864L146:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1832060001, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:520)");
                    }
                    SliderDefaults.INSTANCE.m2877Thumb9LiSoMs(endInteractionSource, null, colors3, enabled4, 0L, $composer4, 196614, 18);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(377064480, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSlider.3
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer, Integer num) {
                    invoke(rangeSliderState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(RangeSliderState rangeSliderState, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "CN(rangeSliderState)527@24087L141:Slider.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(377064480, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:527)");
                    }
                    SliderDefaults.INSTANCE.m2879Track4EFweAY(rangeSliderState, (Modifier) null, enabled4, colors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer4, ($changed2 & 14) | 100663296, 242);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), steps4, $composer2, ($dirty2 & 14) | 918552576 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (($dirty2 >> 3) & 458752), (($dirty2 >> 9) & 896) | 54, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors3;
            modifier3 = modifier5;
            enabled3 = enabled4;
            closedFloatingPointRange4 = closedFloatingPointRange5;
            function4 = function5;
            steps3 = steps4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderKt.RangeSlider$lambda$13(closedFloatingPointRange, function1, modifier3, enabled3, closedFloatingPointRange4, steps3, function4, colors2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:232:0x03b7  */
    /* JADX WARN: Code duplicated, block: B:235:0x03c2  */
    /* JADX WARN: Code duplicated, block: B:238:0x03cd  */
    /* JADX WARN: Code duplicated, block: B:242:0x0452  */
    public static final void RangeSlider(final ClosedFloatingPointRange<Float> closedFloatingPointRange, final Function1<? super ClosedFloatingPointRange<Float>, Unit> function1, Modifier modifier, boolean enabled, ClosedFloatingPointRange<Float> closedFloatingPointRange2, Function0<Unit> function0, SliderColors colors, MutableInteractionSource startInteractionSource, MutableInteractionSource endInteractionSource, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function4, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function5, int steps, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean enabled2;
        ClosedFloatingPointRange<Float> closedFloatingPointRange3;
        Function0<Unit> function2;
        SliderColors sliderColors;
        Composer $composer2;
        final MutableInteractionSource endInteractionSource2;
        final int $dirty;
        final Modifier modifier3;
        final boolean enabled3;
        final Function0<Unit> function6;
        final ClosedFloatingPointRange<Float> closedFloatingPointRange4;
        final SliderColors colors2;
        final MutableInteractionSource startInteractionSource2;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function7;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function8;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function9;
        int $dirty2;
        ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo;
        final SliderColors colors3;
        final MutableInteractionSource startInteractionSource3;
        final MutableInteractionSource endInteractionSource3;
        boolean z;
        ComposableLambda composableLambdaRememberComposableLambda;
        ComposableLambda composableLambdaRememberComposableLambda2;
        ComposableLambda composableLambdaRememberComposableLambda3;
        int $dirty3;
        int $dirty4;
        int $dirty1;
        int steps2;
        boolean invalid$iv;
        Object it$iv;
        Composer $composer3 = $composer.startRestartGroup(1924256162);
        ComposerKt.sourceInformation($composer3, "C(RangeSlider)N(value,onValueChange,modifier,enabled,valueRange,onValueChangeFinished,colors,startInteractionSource,endInteractionSource,startThumb,endThumb,track,steps)626@28935L238,637@29257L44,641@29394L292:Slider.kt#uh7d8r");
        int $dirty5 = $changed;
        int $dirty6 = $changed1;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changed(closedFloatingPointRange) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty5 |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty5 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty5 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty5 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                closedFloatingPointRange3 = closedFloatingPointRange2;
                int i4 = $composer3.changed(closedFloatingPointRange3) ? 16384 : 8192;
                $dirty5 |= i4;
            } else {
                closedFloatingPointRange3 = closedFloatingPointRange2;
            }
            $dirty5 |= i4;
        } else {
            closedFloatingPointRange3 = closedFloatingPointRange2;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function2 = function0;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function2 = function0;
            $dirty5 |= $composer3.changedInstance(function2) ? 131072 : 65536;
        } else {
            function2 = function0;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                sliderColors = colors;
                int i6 = $composer3.changed(sliderColors) ? 1048576 : 524288;
                $dirty5 |= i6;
            } else {
                sliderColors = colors;
            }
            $dirty5 |= i6;
        } else {
            sliderColors = colors;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty5 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty5 |= $composer3.changed(startInteractionSource) ? 8388608 : 4194304;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty5 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty5 |= $composer3.changed(endInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i & 512;
        if (i9 != 0) {
            $dirty5 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty5 |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        int i10 = i & 1024;
        if (i10 != 0) {
            $dirty6 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty6 |= $composer3.changedInstance(function4) ? 4 : 2;
        }
        int i11 = i & 2048;
        if (i11 != 0) {
            $dirty6 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty6 |= $composer3.changedInstance(function5) ? 32 : 16;
        }
        int i12 = i & 4096;
        if (i12 != 0) {
            $dirty6 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty6 |= $composer3.changed(steps) ? 256 : 128;
        }
        int $dirty7 = $dirty5;
        if ($composer3.shouldExecute((($dirty5 & 306783379) == 306783378 && ($dirty6 & 147) == 146) ? false : true, $dirty7 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "599@27997L8,600@28062L39,601@28156L39,602@28254L163,609@28474L161,616@28689L176");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                final boolean enabled4 = i3 != 0 ? true : enabled2;
                if ((i & 16) != 0) {
                    closedFloatingPointRangeRangeTo = RangesKt.rangeTo(0.0f, 1.0f);
                    $dirty2 = $dirty7 & (-57345);
                } else {
                    $dirty2 = $dirty7;
                    closedFloatingPointRangeRangeTo = closedFloatingPointRange3;
                }
                if (i5 != 0) {
                    function2 = null;
                }
                if ((i & 64) != 0) {
                    colors3 = SliderDefaults.INSTANCE.colors($composer3, 6);
                    $dirty2 &= -3670017;
                } else {
                    colors3 = sliderColors;
                }
                if (i7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -1920718807, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv2 = value$iv;
                    }
                    startInteractionSource3 = (MutableInteractionSource) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    startInteractionSource3 = startInteractionSource;
                }
                if (i8 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, -1920715799, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv3 = $composer3.rememberedValue();
                    if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv2);
                        it$iv3 = value$iv2;
                    }
                    endInteractionSource3 = (MutableInteractionSource) it$iv3;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                } else {
                    endInteractionSource3 = endInteractionSource;
                }
                if (i9 != 0) {
                    Function3<RangeSliderState, Composer, Integer, Unit> function10 = new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSlider.7
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer, Integer num) {
                            invoke(rangeSliderState, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "CN(it)603@28279L132:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-743960051, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:603)");
                            }
                            SliderDefaults.INSTANCE.m2877Thumb9LiSoMs(startInteractionSource3, null, colors3, enabled4, 0L, $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    };
                    z = true;
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-743960051, true, function10, $composer3, 54);
                } else {
                    z = true;
                    composableLambdaRememberComposableLambda = function3;
                }
                composableLambdaRememberComposableLambda2 = i10 != 0 ? ComposableLambdaKt.rememberComposableLambda(1306751174, z, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSlider.8
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer, Integer num) {
                        invoke(rangeSliderState, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "CN(it)610@28499L130:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1306751174, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:610)");
                        }
                        SliderDefaults.INSTANCE.m2877Thumb9LiSoMs(endInteractionSource3, null, colors3, enabled4, 0L, $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54) : function4;
                composableLambdaRememberComposableLambda3 = i11 != 0 ? ComposableLambdaKt.rememberComposableLambda(-1234185578, z, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSlider.9
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState, Composer composer, Integer num) {
                        invoke(rangeSliderState, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(RangeSliderState rangeSliderState, Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "CN(rangeSliderState)617@28734L125:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1234185578, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:617)");
                        }
                        SliderDefaults.INSTANCE.m2879Track4EFweAY(rangeSliderState, (Modifier) null, enabled4, colors3, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer4, ($changed2 & 14) | 100663296, 242);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54) : function5;
                if (i12 != 0) {
                    $dirty3 = $dirty2;
                    $dirty4 = 0;
                    closedFloatingPointRange3 = closedFloatingPointRangeRangeTo;
                    enabled2 = enabled4;
                    modifier2 = modifier4;
                } else {
                    closedFloatingPointRange3 = closedFloatingPointRangeRangeTo;
                    $dirty3 = $dirty2;
                    $dirty4 = steps;
                    enabled2 = enabled4;
                    modifier2 = modifier4;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty8 = (i & 16) != 0 ? $dirty7 & (-57345) : $dirty7;
                if ((i & 64) != 0) {
                    $dirty8 &= -3670017;
                }
                startInteractionSource3 = startInteractionSource;
                endInteractionSource3 = endInteractionSource;
                composableLambdaRememberComposableLambda2 = function4;
                composableLambdaRememberComposableLambda3 = function5;
                $dirty4 = steps;
                $dirty3 = $dirty8;
                colors3 = sliderColors;
                composableLambdaRememberComposableLambda = function3;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1924256162, $dirty3, $dirty6, "androidx.compose.material3.RangeSlider (Slider.kt:624)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1920690672, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv2 = (($dirty6 & 896) == 256) | ((((57344 & $dirty3) ^ 24576) > 16384 && $composer3.changed(closedFloatingPointRange3)) || ($dirty3 & 24576) == 16384);
            Object it$iv4 = $composer3.rememberedValue();
            if (invalid$iv2) {
                $dirty1 = $dirty6;
            } else {
                $dirty1 = $dirty6;
                if (it$iv4 != Composer.INSTANCE.getEmpty()) {
                    steps2 = $dirty4;
                }
                RangeSliderState state = (RangeSliderState) it$iv4;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                state.setOnValueChangeFinished(function2);
                ComposerKt.sourceInformationMarkerStart($composer3, -1920680562, "CC(remember):Slider.kt#9igjgp");
                invalid$iv = ($dirty3 & 112) == 32;
                it$iv = $composer3.rememberedValue();
                if (invalid$iv) {
                    $composer2 = $composer3;
                } else {
                    $composer2 = $composer3;
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    state.setOnValueChange$material3((Function1) it$iv);
                    state.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
                    state.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
                    RangeSlider(state, modifier2, enabled2, null, startInteractionSource3, endInteractionSource3, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, composableLambdaRememberComposableLambda3, $composer2, (($dirty3 >> 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 >> 9) & 57344) | (($dirty3 >> 9) & 458752) | (($dirty3 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024), 8);
                    Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function11 = composableLambdaRememberComposableLambda3;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    closedFloatingPointRange4 = closedFloatingPointRange3;
                    endInteractionSource2 = endInteractionSource3;
                    colors2 = colors3;
                    function8 = composableLambdaRememberComposableLambda2;
                    function6 = function2;
                    function7 = composableLambdaRememberComposableLambda;
                    $dirty = steps2;
                    function9 = function11;
                    enabled3 = enabled2;
                    startInteractionSource2 = startInteractionSource3;
                }
                Object value$iv3 = new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderKt.RangeSlider$lambda$18$lambda$17(function1, (SliderRange) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
                it$iv = value$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                state.setOnValueChange$material3((Function1) it$iv);
                state.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
                state.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
                RangeSlider(state, modifier2, enabled2, null, startInteractionSource3, endInteractionSource3, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, composableLambdaRememberComposableLambda3, $composer2, (($dirty3 >> 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 >> 9) & 57344) | (($dirty3 >> 9) & 458752) | (($dirty3 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024), 8);
                Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function12 = composableLambdaRememberComposableLambda3;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                closedFloatingPointRange4 = closedFloatingPointRange3;
                endInteractionSource2 = endInteractionSource3;
                colors2 = colors3;
                function8 = composableLambdaRememberComposableLambda2;
                function6 = function2;
                function7 = composableLambdaRememberComposableLambda;
                $dirty = steps2;
                function9 = function12;
                enabled3 = enabled2;
                startInteractionSource2 = startInteractionSource3;
            }
            int steps3 = $dirty4;
            Object value$iv4 = new RangeSliderState(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), steps3, function2, closedFloatingPointRange3);
            steps2 = steps3;
            $composer3.updateRememberedValue(value$iv4);
            it$iv4 = value$iv4;
            RangeSliderState state2 = (RangeSliderState) it$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            state2.setOnValueChangeFinished(function2);
            ComposerKt.sourceInformationMarkerStart($composer3, -1920680562, "CC(remember):Slider.kt#9igjgp");
            if (($dirty3 & 112) == 32) {
            }
            it$iv = $composer3.rememberedValue();
            if (invalid$iv) {
                $composer2 = $composer3;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                state2.setOnValueChange$material3((Function1) it$iv);
                state2.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
                state2.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
                RangeSlider(state2, modifier2, enabled2, null, startInteractionSource3, endInteractionSource3, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, composableLambdaRememberComposableLambda3, $composer2, (($dirty3 >> 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 >> 9) & 57344) | (($dirty3 >> 9) & 458752) | (($dirty3 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024), 8);
                Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function13 = composableLambdaRememberComposableLambda3;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                closedFloatingPointRange4 = closedFloatingPointRange3;
                endInteractionSource2 = endInteractionSource3;
                colors2 = colors3;
                function8 = composableLambdaRememberComposableLambda2;
                function6 = function2;
                function7 = composableLambdaRememberComposableLambda;
                $dirty = steps2;
                function9 = function13;
                enabled3 = enabled2;
                startInteractionSource2 = startInteractionSource3;
            } else {
                $composer2 = $composer3;
            }
            Object value$iv5 = new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SliderKt.RangeSlider$lambda$18$lambda$17(function1, (SliderRange) obj);
                }
            };
            $composer3.updateRememberedValue(value$iv5);
            it$iv = value$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            state2.setOnValueChange$material3((Function1) it$iv);
            state2.setActiveRangeStart(closedFloatingPointRange.getStart().floatValue());
            state2.setActiveRangeEnd(closedFloatingPointRange.getEndInclusive().floatValue());
            RangeSlider(state2, modifier2, enabled2, null, startInteractionSource3, endInteractionSource3, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, composableLambdaRememberComposableLambda3, $composer2, (($dirty3 >> 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 >> 9) & 57344) | (($dirty3 >> 9) & 458752) | (($dirty3 >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty1 << 21) & 234881024), 8);
            Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function14 = composableLambdaRememberComposableLambda3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            closedFloatingPointRange4 = closedFloatingPointRange3;
            endInteractionSource2 = endInteractionSource3;
            colors2 = colors3;
            function8 = composableLambdaRememberComposableLambda2;
            function6 = function2;
            function7 = composableLambdaRememberComposableLambda;
            $dirty = steps2;
            function9 = function14;
            enabled3 = enabled2;
            startInteractionSource2 = startInteractionSource3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            endInteractionSource2 = endInteractionSource;
            $dirty = steps;
            modifier3 = modifier2;
            enabled3 = enabled2;
            function6 = function2;
            closedFloatingPointRange4 = closedFloatingPointRange3;
            colors2 = sliderColors;
            startInteractionSource2 = startInteractionSource;
            function7 = function3;
            function8 = function4;
            function9 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderKt.RangeSlider$lambda$19(closedFloatingPointRange, function1, modifier3, enabled3, closedFloatingPointRange4, function6, colors2, startInteractionSource2, endInteractionSource2, function7, function8, function9, $dirty, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit RangeSlider$lambda$18$lambda$17(Function1 $onValueChange, SliderRange it) {
        $onValueChange.invoke(RangesKt.rangeTo(SliderRange.m2905getStartimpl(it.m2908unboximpl()), SliderRange.m2904getEndInclusiveimpl(it.m2908unboximpl())));
        return Unit.INSTANCE;
    }

    public static final void RangeSlider(final RangeSliderState state, Modifier modifier, boolean enabled, SliderColors colors, MutableInteractionSource startInteractionSource, MutableInteractionSource endInteractionSource, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function4, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function5, Composer $composer, final int $changed, final int i) {
        RangeSliderState rangeSliderState;
        final boolean enabled2;
        final SliderColors colors2;
        final MutableInteractionSource startInteractionSource2;
        final MutableInteractionSource endInteractionSource2;
        Composer $composer2;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function6;
        final boolean enabled3;
        final SliderColors colors3;
        final MutableInteractionSource startInteractionSource3;
        final MutableInteractionSource endInteractionSource3;
        final Modifier modifier2;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function7;
        final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function8;
        boolean z;
        ComposableLambda composableLambdaRememberComposableLambda;
        Modifier modifier3;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function9;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function10;
        MutableInteractionSource startInteractionSource4;
        MutableInteractionSource endInteractionSource4;
        boolean z2;
        int i2;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda;
        boolean enabled4;
        Composer $composer3 = $composer.startRestartGroup(-781154979);
        ComposerKt.sourceInformation($composer3, "C(RangeSlider)N(state,modifier,enabled,colors,startInteractionSource,endInteractionSource,startThumb,endThumb,track)729@33295L296:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            rangeSliderState = state;
        } else if (($changed & 6) == 0) {
            rangeSliderState = state;
            $dirty |= $composer3.changedInstance(rangeSliderState) ? 4 : 2;
        } else {
            rangeSliderState = state;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty |= $composer3.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                colors2 = colors;
                int i5 = $composer3.changed(colors2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                colors2 = colors;
            }
            $dirty |= i5;
        } else {
            colors2 = colors;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            startInteractionSource2 = startInteractionSource;
        } else if (($changed & 24576) == 0) {
            startInteractionSource2 = startInteractionSource;
            $dirty |= $composer3.changed(startInteractionSource2) ? 16384 : 8192;
        } else {
            startInteractionSource2 = startInteractionSource;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            endInteractionSource2 = endInteractionSource;
        } else if ((196608 & $changed) == 0) {
            endInteractionSource2 = endInteractionSource;
            $dirty |= $composer3.changed(endInteractionSource2) ? 131072 : 65536;
        } else {
            endInteractionSource2 = endInteractionSource;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 8388608 : 4194304;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer3.shouldExecute(($dirty & 38347923) != 38347922, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "702@32359L8,703@32424L39,704@32518L39,705@32616L163,712@32836L161,719@33051L176");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier;
                if (i4 != 0) {
                    enabled2 = true;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    colors2 = SliderDefaults.INSTANCE.colors($composer3, 6);
                }
                if (i6 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 929350180, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    startInteractionSource2 = (MutableInteractionSource) it$iv;
                }
                if (i7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 929353188, "CC(remember):Slider.kt#9igjgp");
                    Object it$iv2 = $composer3.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    endInteractionSource2 = (MutableInteractionSource) it$iv2;
                }
                if (i8 != 0) {
                    z = true;
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1597255314, true, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSlider.14
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState2, Composer composer, Integer num) {
                            invoke(rangeSliderState2, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "CN(it)706@32641L132:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1597255314, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:706)");
                            }
                            SliderDefaults.INSTANCE.m2877Thumb9LiSoMs(startInteractionSource2, null, colors2, enabled2, 0L, $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                } else {
                    z = true;
                    composableLambdaRememberComposableLambda = function3;
                }
                ComposableLambda composableLambdaRememberComposableLambda2 = i9 != 0 ? ComposableLambdaKt.rememberComposableLambda(1348023737, z, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSlider.15
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState2, Composer composer, Integer num) {
                        invoke(rangeSliderState2, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(RangeSliderState it, Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "CN(it)713@32861L130:Slider.kt#uh7d8r");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1348023737, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:713)");
                        }
                        SliderDefaults.INSTANCE.m2877Thumb9LiSoMs(endInteractionSource2, null, colors2, enabled2, 0L, $composer4, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 18);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54) : function4;
                if (i10 != 0) {
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-453269015, z, new Function3<RangeSliderState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SliderKt.RangeSlider.16
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(RangeSliderState rangeSliderState2, Composer composer, Integer num) {
                            invoke(rangeSliderState2, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(RangeSliderState rangeSliderState2, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "CN(rangeSliderState)720@33096L125:Slider.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-453269015, $changed2, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:720)");
                            }
                            SliderDefaults.INSTANCE.m2879Track4EFweAY(rangeSliderState2, (Modifier) null, enabled2, colors2, (Function2<? super DrawScope, ? super Offset, Unit>) null, (Function3<? super DrawScope, ? super Offset, ? super Color, Unit>) null, 0.0f, 0.0f, $composer4, ($changed2 & 14) | 100663296, 242);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    z2 = false;
                    modifier3 = modifier4;
                    function9 = composableLambdaRememberComposableLambda;
                    function10 = composableLambdaRememberComposableLambda2;
                    startInteractionSource4 = startInteractionSource2;
                    endInteractionSource4 = endInteractionSource2;
                    i2 = -781154979;
                    enabled4 = enabled2;
                } else {
                    modifier3 = modifier4;
                    function9 = composableLambdaRememberComposableLambda;
                    function10 = composableLambdaRememberComposableLambda2;
                    startInteractionSource4 = startInteractionSource2;
                    endInteractionSource4 = endInteractionSource2;
                    z2 = false;
                    i2 = -781154979;
                    function3RememberComposableLambda = function5;
                    enabled4 = enabled2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier3 = modifier;
                function9 = function3;
                function10 = function4;
                startInteractionSource4 = startInteractionSource2;
                endInteractionSource4 = endInteractionSource2;
                z2 = false;
                i2 = -781154979;
                z = true;
                function3RememberComposableLambda = function5;
                enabled4 = enabled2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.RangeSlider (Slider.kt:726)");
            }
            if (rangeSliderState.getSteps() < 0) {
                z = z2;
            }
            if (!z) {
                throw new IllegalArgumentException("steps should be >= 0".toString());
            }
            $composer2 = $composer3;
            RangeSliderImpl(modifier3, rangeSliderState, enabled4, startInteractionSource4, endInteractionSource4, function9, function10, function3RememberComposableLambda, $composer2, (($dirty >> 3) & 14) | (($dirty << 3) & 112) | ($dirty & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752) | (($dirty >> 3) & 3670016) | (($dirty >> 3) & 29360128));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            colors3 = colors2;
            enabled3 = enabled4;
            startInteractionSource3 = startInteractionSource4;
            endInteractionSource3 = endInteractionSource4;
            function7 = function9;
            function6 = function10;
            function8 = function3RememberComposableLambda;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function6 = function4;
            enabled3 = enabled2;
            colors3 = colors2;
            startInteractionSource3 = startInteractionSource2;
            endInteractionSource3 = endInteractionSource2;
            modifier2 = modifier;
            function7 = function3;
            function8 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderKt.RangeSlider$lambda$23(state, modifier2, enabled3, colors3, startInteractionSource3, endInteractionSource3, function7, function6, function8, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:144:0x059c  */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.ui.Alignment$Horizontal, androidx.compose.ui.Alignment$Vertical, java.lang.Object] */
    private static final void SliderImpl(final Modifier modifier, final SliderState state, final boolean enabled, MutableInteractionSource interactionSource, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function3, Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed) {
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function5;
        MutableInteractionSource mutableInteractionSource;
        Object obj;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Composer $composer2;
        Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function6 = function4;
        Composer $composer3 = $composer.startRestartGroup(898172835);
        ComposerKt.sourceInformation($composer3, "C(SliderImpl)N(modifier,state,enabled,interactionSource,thumb,track)751@33925L7,762@34446L28,806@36026L3110,773@34814L4322:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(interactionSource) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function6) ? 131072 : 65536;
        }
        if ($composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(898172835, $dirty, -1, "androidx.compose.material3.SliderImpl (Slider.kt:750)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            state.setRtl$material3(objConsume == LayoutDirection.Rtl);
            boolean reverseDirection = (state.getOrientation() == Orientation.Horizontal && state.getIsRtl()) || (state.getOrientation() == Orientation.Vertical && state.getReverseVerticalDirection());
            Modifier press = sliderTapModifier(Modifier.INSTANCE, state, interactionSource, enabled);
            Modifier modifier2 = Modifier.INSTANCE;
            Orientation orientation$material3 = state.getOrientation();
            boolean reverseDirection2 = reverseDirection;
            boolean reverseDirection3 = state.isDragging();
            Modifier press2 = modifier2;
            SliderState sliderState = state;
            ComposerKt.sourceInformationMarkerStart($composer3, -144304801, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv = $composer3.changedInstance(state);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                obj = null;
                Object value$iv = (Function3) new SliderKt$SliderImpl$drag$1$1(state, null);
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                obj = null;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ?? r0 = obj;
            int $dirty2 = $dirty;
            Modifier drag = DraggableKt.draggable(press2, sliderState, orientation$material3, (TsExtractor.TS_PACKET_SIZE & 4) != 0 ? true : enabled, (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : interactionSource, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : reverseDirection3, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) it$iv, (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : reverseDirection2);
            mutableInteractionSource = interactionSource;
            Modifier thumbModifier = state.getOrientation() == Orientation.Vertical ? SizeKt.wrapContentHeight$default(LayoutIdKt.layoutId(Modifier.INSTANCE, SliderComponents.THUMB), r0, false, 3, r0) : SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(Modifier.INSTANCE, SliderComponents.THUMB), r0, false, 3, r0);
            Modifier modifier$iv = slideOnKeyEvents(FocusableKt.focusable(sliderSemantics(SizeKt.m861requiredSizeInqDBjuR0$default(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier), state.getOrientation() == Orientation.Vertical ? TrackHeight : ThumbWidth, state.getOrientation() == Orientation.Vertical ? ThumbWidth : TrackHeight, 0.0f, 0.0f, 12, null), state, enabled), enabled, mutableInteractionSource), enabled, state.getSteps(), state.getValueRange(), state.getValue(), reverseDirection2, state.getOnValueChange(), state.getOnValueChangeFinished()).then(press).then(drag);
            ComposerKt.sourceInformationMarkerStart($composer3, -144251159, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changedInstance(state);
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = (MeasurePolicy) new SliderKt$SliderImpl$2$1(state);
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            MeasurePolicy measurePolicy$iv = (MeasurePolicy) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv = 0 << 6;
            int $changed$iv$iv = ($changed$iv & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1885666906, "C777@34924L129,775@34844L268,784@35125L74:Slider.kt#uh7d8r");
            ComposerKt.sourceInformationMarkerStart($composer3, 1878837019, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv3 = $composer3.changedInstance(state);
            Object value$iv3 = $composer3.rememberedValue();
            if (invalid$iv3 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SliderKt.SliderImpl$lambda$29$lambda$26$lambda$25(state, (IntSize) obj2);
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv2 = OnRemeasuredModifierKt.onSizeChanged(thumbModifier, (Function1) value$iv3);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor2;
                $composer3.createNode(function1);
            } else {
                function1 = constructor2;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -294052009, "C782@35086L12:Slider.kt#uh7d8r");
            function5 = function3;
            function5.invoke(state, $composer3, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 9) & 112)));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv3 = LayoutIdKt.layoutId(Modifier.INSTANCE, SliderComponents.TRACK);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
            int $changed$iv$iv3 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor3;
                $composer3.createNode(function2);
            } else {
                function2 = constructor3;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                $composer2 = $composer3;
            } else {
                $composer2 = $composer3;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                Composer $composer$iv = $composer2;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i5 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -836287744, "C784@35185L12:Slider.kt#uh7d8r");
                function6 = function4;
                function6.invoke(state, $composer$iv, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 12) & 112)));
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i6 = ($changed$iv$iv$iv2 >> 6) & 14;
            Composer $composer$iv2 = $composer2;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i7 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -836287744, "C784@35185L12:Slider.kt#uh7d8r");
            function6 = function4;
            function6.invoke(state, $composer$iv2, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 12) & 112)));
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer$iv2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            function5 = function3;
            mutableInteractionSource = interactionSource;
            $composer3.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function7 = function6;
            final Function3<? super SliderState, ? super Composer, ? super Integer, Unit> function8 = function5;
            final MutableInteractionSource mutableInteractionSource2 = mutableInteractionSource;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return SliderKt.SliderImpl$lambda$31(modifier, state, enabled, mutableInteractionSource2, function8, function7, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit SliderImpl$lambda$29$lambda$26$lambda$25(SliderState $state, IntSize it) {
        long arg0$iv = it.m8080unboximpl();
        $state.setThumbWidth$material3((int) (arg0$iv >> 32));
        long arg0$iv2 = it.m8080unboximpl();
        $state.setThumbHeight$material3((int) (4294967295L & arg0$iv2));
        return Unit.INSTANCE;
    }

    private static final Modifier slideOnKeyEvents(Modifier $this$slideOnKeyEvents, final boolean enabled, final int steps, final ClosedFloatingPointRange<Float> closedFloatingPointRange, final float value, final boolean reverseDirection, final Function1<? super Float, Unit> function1, final Function0<Unit> function0) {
        if (steps >= 0) {
            return KeyInputModifierKt.onKeyEvent($this$slideOnKeyEvents, new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.SliderKt.slideOnKeyEvents.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                    return m2898invokeZmokQxo(keyEvent.m6242unboximpl());
                }

                /* JADX WARN: Type inference incomplete: some casts might be missing */
                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m2898invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z = false;
                    if (!enabled || function1 == null) {
                        return false;
                    }
                    int iM6254getTypeZmokQxo = KeyEvent_androidKt.m6254getTypeZmokQxo(keyEvent);
                    if (KeyEventType.m6246equalsimpl0(iM6254getTypeZmokQxo, KeyEventType.INSTANCE.m6250getKeyDownCS__XNY())) {
                        float fAbs = Math.abs(closedFloatingPointRange.getEndInclusive().floatValue() - closedFloatingPointRange.getStart().floatValue());
                        int i = steps > 0 ? steps + 1 : 100;
                        float f = fAbs / i;
                        int i2 = reverseDirection ? -1 : 1;
                        long jM6253getKeyZmokQxo = KeyEvent_androidKt.m6253getKeyZmokQxo(keyEvent);
                        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6023getDirectionUpEK5gGoQ())) {
                            function1.invoke((Float) RangesKt.coerceIn(Float.valueOf(value + (i2 * f)), closedFloatingPointRange));
                            z = true;
                        } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6018getDirectionDownEK5gGoQ())) {
                            function1.invoke((Float) RangesKt.coerceIn(Float.valueOf(value - (i2 * f)), closedFloatingPointRange));
                            z = true;
                        } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6022getDirectionRightEK5gGoQ())) {
                            function1.invoke((Float) RangesKt.coerceIn(Float.valueOf(value + (i2 * f)), closedFloatingPointRange));
                            z = true;
                        } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6021getDirectionLeftEK5gGoQ())) {
                            function1.invoke((Float) RangesKt.coerceIn(Float.valueOf(value - (i2 * f)), closedFloatingPointRange));
                            z = true;
                        } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6097getMoveHomeEK5gGoQ())) {
                            function1.invoke(closedFloatingPointRange.getStart());
                            z = true;
                        } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6096getMoveEndEK5gGoQ())) {
                            function1.invoke(closedFloatingPointRange.getEndInclusive());
                            z = true;
                        } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6134getPageUpEK5gGoQ())) {
                            function1.invoke((Float) RangesKt.coerceIn(Float.valueOf(value - (RangesKt.coerceIn(i / 10, 1, 10) * f)), closedFloatingPointRange));
                            z = true;
                        } else if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6133getPageDownEK5gGoQ())) {
                            function1.invoke((Float) RangesKt.coerceIn(Float.valueOf(value + (RangesKt.coerceIn(i / 10, 1, 10) * f)), closedFloatingPointRange));
                            z = true;
                        }
                    } else if (KeyEventType.m6246equalsimpl0(iM6254getTypeZmokQxo, KeyEventType.INSTANCE.m6251getKeyUpCS__XNY())) {
                        long jM6253getKeyZmokQxo2 = KeyEvent_androidKt.m6253getKeyZmokQxo(keyEvent);
                        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6023getDirectionUpEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6018getDirectionDownEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6022getDirectionRightEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6021getDirectionLeftEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6097getMoveHomeEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6096getMoveEndEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6134getPageUpEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo2, Key.INSTANCE.m6133getPageDownEK5gGoQ())) {
                            Function0<Unit> function2 = function0;
                            if (function2 != null) {
                                function2.invoke();
                            }
                            z = true;
                        }
                    }
                    return Boolean.valueOf(z);
                }
            });
        }
        throw new IllegalArgumentException("steps should be >= 0".toString());
    }

    /* JADX WARN: Code duplicated, block: B:131:0x0460  */
    /* JADX WARN: Code duplicated, block: B:134:0x04d6  */
    /* JADX WARN: Code duplicated, block: B:137:0x04e2  */
    /* JADX WARN: Code duplicated, block: B:138:0x04e8  */
    /* JADX WARN: Code duplicated, block: B:148:0x0600  */
    /* JADX WARN: Code duplicated, block: B:151:0x060c  */
    /* JADX WARN: Code duplicated, block: B:152:0x0612  */
    /* JADX WARN: Code duplicated, block: B:155:0x0643  */
    /* JADX WARN: Code duplicated, block: B:157:0x0653  */
    /* JADX WARN: Code duplicated, block: B:158:0x0656  */
    /* JADX WARN: Code duplicated, block: B:162:0x06dc  */
    private static final void RangeSliderImpl(final Modifier modifier, final RangeSliderState state, final boolean enabled, final MutableInteractionSource startInteractionSource, final MutableInteractionSource endInteractionSource, final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function3, final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function4, Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function5, Composer $composer, final int $changed) {
        Modifier modifier2;
        Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function6;
        Composer $composer2;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        boolean invalid$iv;
        Object it$iv;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function2;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function7;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        int $changed$iv;
        Composer $composer3 = $composer.startRestartGroup(-287468326);
        ComposerKt.sourceInformation($composer3, "C(RangeSliderImpl)N(modifier,state,enabled,startInteractionSource,endInteractionSource,startThumb,endThumb,track)971@42967L7,981@43221L35,982@43289L33,1025@45118L3284,984@43328L5074:Slider.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(enabled) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(startInteractionSource) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(endInteractionSource) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? 8388608 : 4194304;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute((4793491 & $dirty2) != 4793490, $dirty2 & 1)) {
            function6 = function5;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-287468326, $dirty2, -1, "androidx.compose.material3.RangeSliderImpl (Slider.kt:970)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            state.setRtl$material3(objConsume == LayoutDirection.Rtl);
            Modifier pressDrag = rangeSliderPressDragModifier(Modifier.INSTANCE, state, startInteractionSource, endInteractionSource, enabled);
            Strings.Companion companion = Strings.INSTANCE;
            final String startContentDescription = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.range_start), $composer3, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            final String endContentDescription = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(androidx.compose.ui.R.string.range_end), $composer3, 0);
            Modifier modifier$iv = SizeKt.m861requiredSizeInqDBjuR0$default(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier2), ThumbWidth, TrackHeight, 0.0f, 0.0f, 12, null).then(pressDrag);
            ComposerKt.sourceInformationMarkerStart($composer3, -1379067122, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changedInstance(state);
            SliderKt$RangeSliderImpl$2$1 value$iv = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new SliderKt$RangeSliderImpl$2$1(state);
                $composer3.updateRememberedValue(value$iv);
            }
            MeasurePolicy measurePolicy$iv = (MeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = ((0 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor3;
                $composer3.createNode(function0);
            } else {
                function0 = constructor3;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1046432705, "C990@43545L171,995@43849L100,986@43358L723,1006@44279L167,1011@44577L98,1002@44094L709,1018@44816L79:Slider.kt#uh7d8r");
            Modifier modifierWrapContentWidth$default = SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.STARTTHUMB), null, false, 3, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1905911374, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv3 = $composer3.changedInstance(state);
            Object value$iv2 = $composer3.rememberedValue();
            if (invalid$iv3 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderKt.RangeSliderImpl$lambda$44$lambda$34$lambda$33(state, (IntSize) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierRangeSliderStartThumbSemantics = rangeSliderStartThumbSemantics(OnRemeasuredModifierKt.onSizeChanged(modifierWrapContentWidth$default, (Function1) value$iv2), state, enabled);
            ComposerKt.sourceInformationMarkerStart($composer3, 1905921031, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv4 = $composer3.changed(startContentDescription);
            Object value$iv3 = $composer3.rememberedValue();
            if (invalid$iv4 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderKt.RangeSliderImpl$lambda$44$lambda$36$lambda$35(startContentDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv2 = FocusableKt.focusable(SemanticsModifierKt.semantics(modifierRangeSliderStartThumbSemantics, true, (Function1) value$iv3), enabled, startInteractionSource);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv2 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv2 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor4;
                $composer3.createNode(function1);
            } else {
                function1 = constructor4;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash2);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 239610549, "C1000@44050L17:Slider.kt#uh7d8r");
            function3.invoke(state, $composer3, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 12) & 112)));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierWrapContentWidth$default2 = SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.ENDTHUMB), null, false, 3, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1905934858, "CC(remember):Slider.kt#9igjgp");
            boolean invalid$iv5 = $composer3.changedInstance(state);
            Object value$iv4 = $composer3.rememberedValue();
            if (!invalid$iv5) {
                $composer2 = $composer3;
                if (value$iv4 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierRangeSliderEndThumbSemantics = rangeSliderEndThumbSemantics(OnRemeasuredModifierKt.onSizeChanged(modifierWrapContentWidth$default2, (Function1) value$iv4), state, enabled);
                ComposerKt.sourceInformationMarkerStart($composer3, 1905944325, "CC(remember):Slider.kt#9igjgp");
                invalid$iv = $composer3.changed(endContentDescription);
                it$iv = $composer3.rememberedValue();
                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv5 = new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SliderKt.RangeSliderImpl$lambda$44$lambda$41$lambda$40(endContentDescription, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv5);
                    it$iv = value$iv5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifier$iv3 = FocusableKt.focusable(SemanticsModifierKt.semantics(modifierRangeSliderEndThumbSemantics, true, (Function1) it$iv), enabled, endInteractionSource);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv3 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function2 = constructor;
                    $composer3.createNode(function2);
                } else {
                    function2 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i5 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 85810862, "C1016@44774L15:Slider.kt#uh7d8r");
                function4.invoke(state, $composer3, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 15) & 112)));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifier$iv4 = LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.TRACK);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv4 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv4 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function7 = constructor2;
                    $composer3.createNode(function7);
                } else {
                    function7 = constructor2;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                    $changed$iv = 6;
                } else {
                    $changed$iv = 6;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i6 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i7 = (($changed$iv >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -979172334, "C1018@44881L12:Slider.kt#uh7d8r");
                    function6 = function5;
                    function6.invoke(state, $composer3, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 18) & 112)));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash4);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i9 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -979172334, "C1018@44881L12:Slider.kt#uh7d8r");
                function6 = function5;
                function6.invoke(state, $composer3, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 18) & 112)));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2 = $composer3;
            }
            value$iv4 = new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SliderKt.RangeSliderImpl$lambda$44$lambda$39$lambda$38(state, (IntSize) obj);
                }
            };
            $composer3.updateRememberedValue(value$iv4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierRangeSliderEndThumbSemantics2 = rangeSliderEndThumbSemantics(OnRemeasuredModifierKt.onSizeChanged(modifierWrapContentWidth$default2, (Function1) value$iv4), state, enabled);
            ComposerKt.sourceInformationMarkerStart($composer3, 1905944325, "CC(remember):Slider.kt#9igjgp");
            invalid$iv = $composer3.changed(endContentDescription);
            it$iv = $composer3.rememberedValue();
            if (!invalid$iv) {
                Object value$iv6 = new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderKt.RangeSliderImpl$lambda$44$lambda$41$lambda$40(endContentDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv6);
                it$iv = value$iv6;
            } else {
                Object value$iv7 = new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SliderKt.RangeSliderImpl$lambda$44$lambda$41$lambda$40(endContentDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv7);
                it$iv = value$iv7;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv5 = FocusableKt.focusable(SemanticsModifierKt.semantics(modifierRangeSliderEndThumbSemantics2, true, (Function1) it$iv), enabled, endInteractionSource);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
            int $changed$iv$iv5 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv5);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv5 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function2 = constructor;
                $composer3.createNode(function2);
            } else {
                function2 = constructor;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash5);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            int i11 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 85810862, "C1016@44774L15:Slider.kt#uh7d8r");
            function4.invoke(state, $composer3, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 15) & 112)));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv6 = LayoutIdKt.layoutId(Modifier.INSTANCE, RangeSliderComponents.TRACK);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv6 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
            int $changed$iv$iv6 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer3, modifier$iv6);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv5 = (($changed$iv$iv6 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function7 = constructor2;
                $composer3.createNode(function7);
            } else {
                function7 = constructor2;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                $changed$iv = 6;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                int i12 = ($changed$iv$iv$iv5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                int i13 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -979172334, "C1018@44881L12:Slider.kt#uh7d8r");
                function6 = function5;
                function6.invoke(state, $composer3, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 18) & 112)));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $changed$iv = 6;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash6);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = ($changed$iv$iv$iv5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
            int i15 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -979172334, "C1018@44881L12:Slider.kt#uh7d8r");
            function6 = function5;
            function6.invoke(state, $composer3, Integer.valueOf((($dirty2 >> 3) & 14) | (($dirty2 >> 18) & 112)));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function3<? super RangeSliderState, ? super Composer, ? super Integer, Unit> function8 = function6;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SliderKt.RangeSliderImpl$lambda$46(modifier, state, enabled, startInteractionSource, endInteractionSource, function3, function4, function8, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit RangeSliderImpl$lambda$44$lambda$34$lambda$33(RangeSliderState $state, IntSize it) {
        long arg0$iv = it.m8080unboximpl();
        $state.setStartThumbWidth$material3((int) (arg0$iv >> 32));
        long arg0$iv2 = it.m8080unboximpl();
        $state.setStartThumbHeight$material3((int) (4294967295L & arg0$iv2));
        return Unit.INSTANCE;
    }

    static final Unit RangeSliderImpl$lambda$44$lambda$36$lambda$35(String $startContentDescription, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $startContentDescription);
        return Unit.INSTANCE;
    }

    static final Unit RangeSliderImpl$lambda$44$lambda$39$lambda$38(RangeSliderState $state, IntSize it) {
        long arg0$iv = it.m8080unboximpl();
        $state.setEndThumbWidth$material3((int) (arg0$iv >> 32));
        long arg0$iv2 = it.m8080unboximpl();
        $state.setEndThumbHeight$material3((int) (4294967295L & arg0$iv2));
        return Unit.INSTANCE;
    }

    static final Unit RangeSliderImpl$lambda$44$lambda$41$lambda$40(String $endContentDescription, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $endContentDescription);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float snapValueToTick(float current, float[] tickFractions, float minPx, float maxPx) {
        Float fValueOf;
        if (tickFractions.length == 0) {
            fValueOf = null;
        } else {
            float minElem$iv = tickFractions[0];
            int lastIndex$iv = ArraysKt.getLastIndex(tickFractions);
            if (lastIndex$iv == 0) {
                fValueOf = Float.valueOf(minElem$iv);
            } else {
                float minValue$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, minElem$iv) - current);
                int i$iv = 1;
                if (1 <= lastIndex$iv) {
                    while (true) {
                        float e$iv = tickFractions[i$iv];
                        float v$iv = Math.abs(MathHelpersKt.lerp(minPx, maxPx, e$iv) - current);
                        if (Float.compare(minValue$iv, v$iv) > 0) {
                            minElem$iv = e$iv;
                            minValue$iv = v$iv;
                        }
                        if (i$iv == lastIndex$iv) {
                            break;
                        }
                        i$iv++;
                    }
                }
                fValueOf = Float.valueOf(minElem$iv);
            }
        }
        if (fValueOf == null) {
            return current;
        }
        float $this$snapValueToTick_u24lambda_u2448 = fValueOf.floatValue();
        return MathHelpersKt.lerp(minPx, maxPx, $this$snapValueToTick_u24lambda_u2448);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: awaitSlop-8vUncbI, reason: not valid java name */
    public static final Object m2894awaitSlop8vUncbI(AwaitPointerEventScope $this$awaitSlop_u2d8vUncbI, long id, int type, Continuation<? super Pair<PointerInputChange, Float>> continuation) {
        SliderKt$awaitSlop$1 sliderKt$awaitSlop$1;
        final Ref.FloatRef initialDelta;
        Object objM3355awaitHorizontalPointerSlopOrCancellationgDDlDlE;
        if (continuation instanceof SliderKt$awaitSlop$1) {
            sliderKt$awaitSlop$1 = (SliderKt$awaitSlop$1) continuation;
            if ((sliderKt$awaitSlop$1.label & Integer.MIN_VALUE) != 0) {
                sliderKt$awaitSlop$1.label -= Integer.MIN_VALUE;
            } else {
                sliderKt$awaitSlop$1 = new SliderKt$awaitSlop$1(continuation);
            }
        } else {
            sliderKt$awaitSlop$1 = new SliderKt$awaitSlop$1(continuation);
        }
        SliderKt$awaitSlop$1 sliderKt$awaitSlop$2 = sliderKt$awaitSlop$1;
        Object $result = sliderKt$awaitSlop$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (sliderKt$awaitSlop$2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                initialDelta = new Ref.FloatRef();
                Function2 postPointerSlop = new Function2() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SliderKt.awaitSlop_8vUncbI$lambda$49(initialDelta, (PointerInputChange) obj, ((Float) obj2).floatValue());
                    }
                };
                sliderKt$awaitSlop$2.L$0 = initialDelta;
                sliderKt$awaitSlop$2.label = 1;
                objM3355awaitHorizontalPointerSlopOrCancellationgDDlDlE = DragGestureDetectorCopyKt.m3355awaitHorizontalPointerSlopOrCancellationgDDlDlE($this$awaitSlop_u2d8vUncbI, id, type, postPointerSlop, sliderKt$awaitSlop$2);
                if (objM3355awaitHorizontalPointerSlopOrCancellationgDDlDlE == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                initialDelta = (Ref.FloatRef) sliderKt$awaitSlop$2.L$0;
                ResultKt.throwOnFailure($result);
                objM3355awaitHorizontalPointerSlopOrCancellationgDDlDlE = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PointerInputChange afterSlopResult = (PointerInputChange) objM3355awaitHorizontalPointerSlopOrCancellationgDDlDlE;
        if (afterSlopResult != null) {
            return TuplesKt.to(afterSlopResult, Boxing.boxFloat(initialDelta.element));
        }
        return null;
    }

    static final Unit awaitSlop_8vUncbI$lambda$49(Ref.FloatRef $initialDelta, PointerInputChange pointerInput, float offset) {
        pointerInput.consume();
        $initialDelta.element = offset;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float[] stepsToTickFractions(int steps) {
        if (steps == 0) {
            return new float[0];
        }
        int i = steps + 2;
        float[] fArr = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = i2 / (steps + 1);
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float scale(float a1, float b1, float x1, float a2, float b2) {
        return MathHelpersKt.lerp(a2, b2, calcFraction(a1, b1, x1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: scale-2geJ7wY, reason: not valid java name */
    public static final long m2897scale2geJ7wY(boolean isStart, float a1, float b1, long x, float a2, float b2) {
        float start = scale(a1, b1, SliderRange.m2905getStartimpl(x), a2, b2);
        float end = scale(a1, b1, SliderRange.m2904getEndInclusiveimpl(x), a2, b2);
        if (isStart) {
            return SliderRange(RangesKt.coerceAtMost(start, end), end);
        }
        return SliderRange(start, RangesKt.coerceAtLeast(end, start));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calcFraction(float a, float b, float pos) {
        return RangesKt.coerceIn(((b - a) > 0.0f ? 1 : ((b - a) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (pos - a) / (b - a), 0.0f, 1.0f);
    }

    private static final Modifier sliderSemantics(Modifier $this$sliderSemantics, final SliderState state, final boolean enabled) {
        Modifier increaseHorizontalSemanticsBounds;
        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default($this$sliderSemantics, false, new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SliderKt.sliderSemantics$lambda$52(enabled, state, (SemanticsPropertyReceiver) obj);
            }
        }, 1, null);
        if (state.getOrientation() == Orientation.Vertical) {
            increaseHorizontalSemanticsBounds = AccessibilityUtilKt.getIncreaseVerticalSemanticsBounds();
        } else {
            increaseHorizontalSemanticsBounds = AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds();
        }
        return ProgressSemanticsKt.progressSemantics(modifierSemantics$default.then(increaseHorizontalSemanticsBounds), state.getValue(), RangesKt.rangeTo(state.getValueRange().getStart().floatValue(), state.getValueRange().getEndInclusive().floatValue()), state.getSteps());
    }

    static final Unit sliderSemantics$lambda$52(boolean $enabled, final SliderState $state, SemanticsPropertyReceiver $this$semantics) {
        if (!$enabled) {
            SemanticsPropertiesKt.disabled($this$semantics);
        }
        SemanticsPropertiesKt.setStateDescription($this$semantics, formatForSemantics($state.getValue()));
        SemanticsPropertiesKt.setProgress$default($this$semantics, null, new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SliderKt.sliderSemantics$lambda$52$lambda$51($state, ((Float) obj).floatValue()));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    static final boolean sliderSemantics$lambda$52$lambda$51(SliderState $state, float targetValue) {
        float newValue = RangesKt.coerceIn(targetValue, $state.getValueRange().getStart().floatValue(), $state.getValueRange().getEndInclusive().floatValue());
        if ($state.getSteps() > 0) {
            float distance = newValue;
            int i = 0;
            int steps = $state.getSteps() + 1;
            if (0 <= steps) {
                while (true) {
                    float stepValue = MathHelpersKt.lerp($state.getValueRange().getStart().floatValue(), $state.getValueRange().getEndInclusive().floatValue(), i / ($state.getSteps() + 1));
                    if (Math.abs(stepValue - newValue) <= distance) {
                        distance = Math.abs(stepValue - newValue);
                        newValue = stepValue;
                    }
                    if (i == steps) {
                        break;
                    }
                    i++;
                }
            }
        }
        float resolvedValue = newValue;
        if (resolvedValue == $state.getValue()) {
            return false;
        }
        if (!(resolvedValue == $state.getValue())) {
            if ($state.getOnValueChange() != null) {
                Function1<Float, Unit> onValueChange = $state.getOnValueChange();
                if (onValueChange != null) {
                    onValueChange.invoke(Float.valueOf(resolvedValue));
                }
            } else {
                $state.setValue(resolvedValue);
            }
        }
        Function0<Unit> onValueChangeFinished = $state.getOnValueChangeFinished();
        if (onValueChangeFinished == null) {
            return true;
        }
        onValueChangeFinished.invoke();
        return true;
    }

    private static final Modifier rangeSliderStartThumbSemantics(Modifier $this$rangeSliderStartThumbSemantics, final RangeSliderState state, final boolean enabled) {
        final ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo = RangesKt.rangeTo(state.getValueRange().getStart().floatValue(), state.getActiveRangeEnd());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default($this$rangeSliderStartThumbSemantics, false, new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SliderKt.rangeSliderStartThumbSemantics$lambda$55(enabled, state, closedFloatingPointRangeRangeTo, (SemanticsPropertyReceiver) obj);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), state.getActiveRangeStart(), closedFloatingPointRangeRangeTo, state.getStartSteps$material3());
    }

    static final Unit rangeSliderStartThumbSemantics$lambda$55(boolean $enabled, final RangeSliderState $state, final ClosedFloatingPointRange $valueRange, SemanticsPropertyReceiver $this$semantics) {
        if (!$enabled) {
            SemanticsPropertiesKt.disabled($this$semantics);
        }
        SemanticsPropertiesKt.setStateDescription($this$semantics, formatForSemantics($state.getActiveRangeStart()));
        SemanticsPropertiesKt.setProgress$default($this$semantics, null, new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SliderKt.rangeSliderStartThumbSemantics$lambda$55$lambda$54($valueRange, $state, ((Float) obj).floatValue()));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final boolean rangeSliderStartThumbSemantics$lambda$55$lambda$54(ClosedFloatingPointRange $valueRange, RangeSliderState $state, float targetValue) {
        float newValue = RangesKt.coerceIn(targetValue, ((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue());
        if ($state.getStartSteps$material3() > 0) {
            float distance = newValue;
            int i = 0;
            int startSteps$material3 = $state.getStartSteps$material3() + 1;
            if (0 <= startSteps$material3) {
                while (true) {
                    float stepValue = MathHelpersKt.lerp(((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue(), i / ($state.getStartSteps$material3() + 1));
                    if (Math.abs(stepValue - newValue) <= distance) {
                        distance = Math.abs(stepValue - newValue);
                        newValue = stepValue;
                    }
                    if (i == startSteps$material3) {
                        break;
                    }
                    i++;
                }
            }
        }
        float resolvedValue = newValue;
        if (resolvedValue == $state.getActiveRangeStart()) {
            return false;
        }
        long resolvedRange = SliderRange(resolvedValue, $state.getActiveRangeEnd());
        long activeRange = SliderRange($state.getActiveRangeStart(), $state.getActiveRangeEnd());
        if (!SliderRange.m2903equalsimpl0(resolvedRange, activeRange)) {
            if ($state.getOnValueChange$material3() != null) {
                Function1<SliderRange, Unit> onValueChange$material3 = $state.getOnValueChange$material3();
                if (onValueChange$material3 != null) {
                    onValueChange$material3.invoke(SliderRange.m2900boximpl(resolvedRange));
                }
            } else {
                $state.setActiveRangeStart(SliderRange.m2905getStartimpl(resolvedRange));
                $state.setActiveRangeEnd(SliderRange.m2904getEndInclusiveimpl(resolvedRange));
            }
        }
        Function0<Unit> onValueChangeFinished = $state.getOnValueChangeFinished();
        if (onValueChangeFinished == null) {
            return true;
        }
        onValueChangeFinished.invoke();
        return true;
    }

    private static final Modifier rangeSliderEndThumbSemantics(Modifier $this$rangeSliderEndThumbSemantics, final RangeSliderState state, final boolean enabled) {
        final ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo = RangesKt.rangeTo(state.getActiveRangeStart(), state.getValueRange().getEndInclusive().floatValue());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default($this$rangeSliderEndThumbSemantics, false, new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SliderKt.rangeSliderEndThumbSemantics$lambda$58(enabled, state, closedFloatingPointRangeRangeTo, (SemanticsPropertyReceiver) obj);
            }
        }, 1, null).then(AccessibilityUtilKt.getIncreaseHorizontalSemanticsBounds()), state.getActiveRangeEnd(), closedFloatingPointRangeRangeTo, state.getEndSteps$material3());
    }

    static final Unit rangeSliderEndThumbSemantics$lambda$58(boolean $enabled, final RangeSliderState $state, final ClosedFloatingPointRange $valueRange, SemanticsPropertyReceiver $this$semantics) {
        if (!$enabled) {
            SemanticsPropertiesKt.disabled($this$semantics);
        }
        SemanticsPropertiesKt.setStateDescription($this$semantics, formatForSemantics($state.getActiveRangeEnd()));
        SemanticsPropertiesKt.setProgress$default($this$semantics, null, new Function1() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(SliderKt.rangeSliderEndThumbSemantics$lambda$58$lambda$57($valueRange, $state, ((Float) obj).floatValue()));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static final boolean rangeSliderEndThumbSemantics$lambda$58$lambda$57(ClosedFloatingPointRange $valueRange, RangeSliderState $state, float targetValue) {
        float newValue = RangesKt.coerceIn(targetValue, ((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue());
        if ($state.getEndSteps$material3() > 0) {
            float distance = newValue;
            int i = 0;
            int endSteps$material3 = $state.getEndSteps$material3() + 1;
            if (0 <= endSteps$material3) {
                while (true) {
                    float stepValue = MathHelpersKt.lerp(((Number) $valueRange.getStart()).floatValue(), ((Number) $valueRange.getEndInclusive()).floatValue(), i / ($state.getEndSteps$material3() + 1));
                    if (Math.abs(stepValue - newValue) <= distance) {
                        distance = Math.abs(stepValue - newValue);
                        newValue = stepValue;
                    }
                    if (i == endSteps$material3) {
                        break;
                    }
                    i++;
                }
            }
        }
        float resolvedValue = newValue;
        if (resolvedValue == $state.getActiveRangeEnd()) {
            return false;
        }
        long resolvedRange = SliderRange($state.getActiveRangeStart(), resolvedValue);
        long activeRange = SliderRange($state.getActiveRangeStart(), $state.getActiveRangeEnd());
        if (!SliderRange.m2903equalsimpl0(resolvedRange, activeRange)) {
            if ($state.getOnValueChange$material3() != null) {
                Function1<SliderRange, Unit> onValueChange$material3 = $state.getOnValueChange$material3();
                if (onValueChange$material3 != null) {
                    onValueChange$material3.invoke(SliderRange.m2900boximpl(resolvedRange));
                }
            } else {
                $state.setActiveRangeStart(SliderRange.m2905getStartimpl(resolvedRange));
                $state.setActiveRangeEnd(SliderRange.m2904getEndInclusiveimpl(resolvedRange));
            }
        }
        Function0<Unit> onValueChangeFinished = $state.getOnValueChangeFinished();
        if (onValueChangeFinished == null) {
            return true;
        }
        onValueChangeFinished.invoke();
        return true;
    }

    private static final String formatForSemantics(float $this$formatForSemantics) {
        return String.valueOf(MathKt.roundToInt($this$formatForSemantics * 100.0f) / 100.0f);
    }

    private static final Modifier sliderTapModifier(Modifier $this$sliderTapModifier, SliderState state, MutableInteractionSource interactionSource, boolean enabled) {
        if (enabled) {
            return SuspendingPointerInputFilterKt.pointerInput($this$sliderTapModifier, state, interactionSource, new C03281(state));
        }
        return $this$sliderTapModifier;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$sliderTapModifier$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Slider.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class C03281 implements PointerInputEventHandler {
        final /* synthetic */ SliderState $state;

        C03281(SliderState sliderState) {
            this.$state = sliderState;
        }

        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$sliderTapModifier$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Slider.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "it", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.material3.SliderKt$sliderTapModifier$1$1", f = "Slider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00611 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
            final /* synthetic */ SliderState $state;
            /* synthetic */ long J$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00611(SliderState sliderState, Continuation<? super C00611> continuation) {
                super(3, continuation);
                this.$state = sliderState;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
                return m2899invoked4ec7I(pressGestureScope, offset.m4862unboximpl(), continuation);
            }

            /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
            public final Object m2899invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
                C00611 c00611 = new C00611(this.$state, continuation);
                c00611.J$0 = j;
                return c00611.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        long it = this.J$0;
                        this.$state.m2911onPressk4lQ0M$material3(it);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }

        @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
        public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
            C00611 c00611 = new C00611(this.$state, null);
            final SliderState sliderState = this.$state;
            Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default($this$pointerInput, null, null, c00611, new Function1() { // from class: androidx.compose.material3.SliderKt$sliderTapModifier$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SliderKt.C03281.invoke$lambda$0(sliderState, (Offset) obj);
                }
            }, continuation, 3, null);
            return objDetectTapGestures$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures$default : Unit.INSTANCE;
        }

        static final Unit invoke$lambda$0(SliderState $state, Offset it) {
            $state.dispatchRawDelta(0.0f);
            $state.getGestureEndAction$material3().invoke();
            return Unit.INSTANCE;
        }
    }

    private static final Modifier rangeSliderPressDragModifier(Modifier $this$rangeSliderPressDragModifier, final RangeSliderState state, final MutableInteractionSource startInteractionSource, final MutableInteractionSource endInteractionSource, boolean enabled) {
        if (enabled) {
            return SuspendingPointerInputFilterKt.pointerInput($this$rangeSliderPressDragModifier, new Object[]{startInteractionSource, endInteractionSource, state}, new PointerInputEventHandler() { // from class: androidx.compose.material3.SliderKt.rangeSliderPressDragModifier.1

                /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: Slider.kt */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1", f = "Slider.kt", i = {}, l = {2437}, m = "invokeSuspend", n = {}, s = {})
                static final class C00591 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                    final /* synthetic */ RangeSliderState $state;
                    final /* synthetic */ PointerInputScope $this_pointerInput;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00591(PointerInputScope pointerInputScope, RangeSliderState rangeSliderState, RangeSliderLogic rangeSliderLogic, Continuation<? super C00591> continuation) {
                        super(2, continuation);
                        this.$this_pointerInput = pointerInputScope;
                        this.$state = rangeSliderState;
                        this.$rangeSliderLogic = rangeSliderLogic;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00591 c00591 = new C00591(this.$this_pointerInput, this.$state, this.$rangeSliderLogic, continuation);
                        c00591.L$0 = obj;
                        return c00591;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C00591) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1, reason: invalid class name and collision with other inner class name */
                    /* JADX INFO: compiled from: Slider.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1", f = "Slider.kt", i = {0, 1, 1, 1, 1, 1, 2, 2}, l = {2438, 2450, 2473}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", NotificationCompat.CATEGORY_EVENT, "interaction", "posX", "draggingStart", "interaction", "draggingStart"}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1"})
                    static final class C00601 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ CoroutineScope $$this$coroutineScope;
                        final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                        final /* synthetic */ RangeSliderState $state;
                        private /* synthetic */ Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C00601(RangeSliderState rangeSliderState, RangeSliderLogic rangeSliderLogic, CoroutineScope coroutineScope, Continuation<? super C00601> continuation) {
                            super(2, continuation);
                            this.$state = rangeSliderState;
                            this.$rangeSliderLogic = rangeSliderLogic;
                            this.$$this$coroutineScope = coroutineScope;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            C00601 c00601 = new C00601(this.$state, this.$rangeSliderLogic, this.$$this$coroutineScope, continuation);
                            c00601.L$0 = obj;
                            return c00601;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                            return ((C00601) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        /* JADX WARN: Code duplicated, block: B:19:0x008b  */
                        /* JADX WARN: Code duplicated, block: B:20:0x00a9  */
                        /* JADX WARN: Code duplicated, block: B:23:0x00ce A[DONT_INVERT] */
                        /* JADX WARN: Code duplicated, block: B:24:0x00d0  */
                        /* JADX WARN: Code duplicated, block: B:25:0x00d2  */
                        /* JADX WARN: Code duplicated, block: B:26:0x00d4  */
                        /* JADX WARN: Code duplicated, block: B:28:0x00e0  */
                        /* JADX WARN: Code duplicated, block: B:29:0x00e2  */
                        /* JADX WARN: Code duplicated, block: B:32:0x0103 A[RETURN] */
                        /* JADX WARN: Code duplicated, block: B:33:0x0104  */
                        /* JADX WARN: Code duplicated, block: B:36:0x010c  */
                        /* JADX WARN: Code duplicated, block: B:38:0x012a  */
                        /* JADX WARN: Code duplicated, block: B:41:0x013b  */
                        /* JADX WARN: Code duplicated, block: B:44:0x013f  */
                        /* JADX WARN: Code duplicated, block: B:46:0x0150  */
                        /* JADX WARN: Code duplicated, block: B:49:0x0155  */
                        /* JADX WARN: Code duplicated, block: B:58:0x01b7 A[RETURN] */
                        /* JADX WARN: Code duplicated, block: B:59:0x01b8  */
                        /* JADX WARN: Code duplicated, block: B:62:0x01c3 A[Catch: CancellationException -> 0x01da, all -> 0x01de, TryCatch #2 {CancellationException -> 0x01da, blocks: (B:60:0x01b9, B:62:0x01c3, B:63:0x01cb), top: B:82:0x01b9 }] */
                        /* JADX WARN: Code duplicated, block: B:63:0x01cb A[Catch: CancellationException -> 0x01da, all -> 0x01de, TRY_LEAVE, TryCatch #2 {CancellationException -> 0x01da, blocks: (B:60:0x01b9, B:62:0x01c3, B:63:0x01cb), top: B:82:0x01b9 }] */
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) throws Throwable {
                            Object $result2;
                            Object $result3;
                            AwaitPointerEventScope $this$awaitEachGesture;
                            PointerInputChange event;
                            DragInteraction.Start interaction;
                            Ref.FloatRef posX;
                            char c;
                            float fIntBitsToFloat;
                            int compare;
                            final Ref.BooleanRef draggingStart;
                            boolean z;
                            Object objM2894awaitSlop8vUncbI;
                            DragInteraction.Start interaction2;
                            Object $result4;
                            Object $result5;
                            Pair it;
                            Object $result6;
                            Ref.BooleanRef draggingStart2;
                            DragInteraction.Start interaction3;
                            RangeSliderState rangeSliderState;
                            float slop;
                            boolean shouldUpdateCapturedThumb;
                            DragInteraction.Cancel finishInteraction;
                            boolean success;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                                    this.L$0 = $this$awaitEachGesture2;
                                    this.label = 1;
                                    Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture2, false, null, this, 2, null);
                                    if (objAwaitFirstDown$default == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    $result2 = $result;
                                    $result3 = objAwaitFirstDown$default;
                                    $this$awaitEachGesture = $this$awaitEachGesture2;
                                    event = (PointerInputChange) $result3;
                                    interaction = new DragInteraction.Start();
                                    posX = new Ref.FloatRef();
                                    if (this.$state.isRtl$material3()) {
                                        float totalWidth$material3 = this.$state.getTotalWidth$material3();
                                        long arg0$iv = event.getPosition();
                                        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
                                        fIntBitsToFloat = totalWidth$material3 - Float.intBitsToFloat(bits$iv$iv$iv);
                                        c = ' ';
                                    } else {
                                        long arg0$iv2 = event.getPosition();
                                        c = ' ';
                                        int bits$iv$iv$iv2 = (int) (arg0$iv2 >> 32);
                                        fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
                                    }
                                    posX.element = fIntBitsToFloat;
                                    compare = this.$rangeSliderLogic.compareOffsets(posX.element);
                                    draggingStart = new Ref.BooleanRef();
                                    if (compare != 0) {
                                        if (compare < 0) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                    } else if (this.$state.getRawOffsetStart$material3() > posX.element) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    draggingStart.element = z;
                                    this.L$0 = $this$awaitEachGesture;
                                    this.L$1 = event;
                                    this.L$2 = interaction;
                                    this.L$3 = posX;
                                    this.L$4 = draggingStart;
                                    this.label = 2;
                                    objM2894awaitSlop8vUncbI = SliderKt.m2894awaitSlop8vUncbI($this$awaitEachGesture, event.getId(), event.getType(), this);
                                    if (objM2894awaitSlop8vUncbI == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    interaction2 = interaction;
                                    $result4 = objM2894awaitSlop8vUncbI;
                                    $result5 = $result2;
                                    it = (Pair) $result4;
                                    if (it != null) {
                                        rangeSliderState = this.$state;
                                        slop = DragGestureDetectorCopyKt.m3358pointerSlopE8SPZFQ($this$awaitEachGesture.getViewConfiguration(), event.getType());
                                        if (Math.abs(rangeSliderState.getRawOffsetEnd$material3() - posX.element) < slop || Math.abs(rangeSliderState.getRawOffsetStart$material3() - posX.element) >= slop) {
                                            shouldUpdateCapturedThumb = false;
                                        } else {
                                            shouldUpdateCapturedThumb = true;
                                        }
                                        if (shouldUpdateCapturedThumb) {
                                            float dir = ((Number) it.getSecond()).floatValue();
                                            draggingStart.element = rangeSliderState.isRtl$material3() ? dir < 0.0f : dir >= 0.0f;
                                            float f = posX.element;
                                            long arg0$iv3 = PointerEventKt.positionChange((PointerInputChange) it.getFirst());
                                            int bits$iv$iv$iv3 = (int) (arg0$iv3 >> c);
                                            posX.element = f + Float.intBitsToFloat(bits$iv$iv$iv3);
                                        }
                                    }
                                    this.$rangeSliderLogic.captureThumb(draggingStart.element, posX.element, interaction2, this.$$this$coroutineScope);
                                    try {
                                        try {
                                            this.$state.setDragging$material3(true);
                                            long id = event.getId();
                                            final RangeSliderState rangeSliderState2 = this.$state;
                                            this.L$0 = interaction2;
                                            this.L$1 = draggingStart;
                                            this.L$2 = null;
                                            this.L$3 = null;
                                            this.L$4 = null;
                                            this.label = 3;
                                            $result6 = DragGestureDetectorKt.m475horizontalDragjO51t88($this$awaitEachGesture, id, new Function1() { // from class: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1$$ExternalSyntheticLambda0
                                                @Override // kotlin.jvm.functions.Function1
                                                public final Object invoke(Object obj) {
                                                    return SliderKt.C03261.C00591.C00601.invokeSuspend$lambda$1(rangeSliderState2, draggingStart, (PointerInputChange) obj);
                                                }
                                            }, this);
                                            if ($result6 == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            interaction3 = interaction2;
                                            try {
                                                success = ((Boolean) $result6).booleanValue();
                                                if (success) {
                                                    finishInteraction = new DragInteraction.Stop(interaction3);
                                                } else {
                                                    finishInteraction = new DragInteraction.Cancel(interaction3);
                                                }
                                                this.$state.setDragging$material3(false);
                                            } catch (CancellationException e) {
                                                $result6 = $result5;
                                                draggingStart2 = draggingStart;
                                                finishInteraction = new DragInteraction.Cancel(interaction3);
                                                this.$state.setDragging$material3(false);
                                                draggingStart = draggingStart2;
                                            }
                                            this.$state.getGestureEndAction$material3().invoke(Boxing.boxBoolean(draggingStart.element));
                                            BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart, finishInteraction, null), 3, null);
                                            return Unit.INSTANCE;
                                        } catch (Throwable th) {
                                            th = th;
                                            this.$state.setDragging$material3(false);
                                            throw th;
                                        }
                                    } catch (CancellationException e2) {
                                        $result6 = $result5;
                                        draggingStart2 = draggingStart;
                                        interaction3 = interaction2;
                                        finishInteraction = new DragInteraction.Cancel(interaction3);
                                        this.$state.setDragging$material3(false);
                                        draggingStart = draggingStart2;
                                        this.$state.getGestureEndAction$material3().invoke(Boxing.boxBoolean(draggingStart.element));
                                        BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart, finishInteraction, null), 3, null);
                                        return Unit.INSTANCE;
                                    }
                                case 1:
                                    $result3 = $result;
                                    AwaitPointerEventScope $this$awaitEachGesture3 = (AwaitPointerEventScope) this.L$0;
                                    ResultKt.throwOnFailure($result3);
                                    $this$awaitEachGesture = $this$awaitEachGesture3;
                                    $result2 = $result3;
                                    event = (PointerInputChange) $result3;
                                    interaction = new DragInteraction.Start();
                                    posX = new Ref.FloatRef();
                                    if (this.$state.isRtl$material3()) {
                                        float totalWidth$material4 = this.$state.getTotalWidth$material3();
                                        long arg0$iv4 = event.getPosition();
                                        int bits$iv$iv$iv4 = (int) (arg0$iv4 >> 32);
                                        fIntBitsToFloat = totalWidth$material4 - Float.intBitsToFloat(bits$iv$iv$iv4);
                                        c = ' ';
                                    } else {
                                        long arg0$iv5 = event.getPosition();
                                        c = ' ';
                                        int bits$iv$iv$iv5 = (int) (arg0$iv5 >> 32);
                                        fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv5);
                                    }
                                    posX.element = fIntBitsToFloat;
                                    compare = this.$rangeSliderLogic.compareOffsets(posX.element);
                                    draggingStart = new Ref.BooleanRef();
                                    if (compare != 0) {
                                        if (compare < 0) {
                                            z = true;
                                        } else {
                                            z = false;
                                        }
                                    } else if (this.$state.getRawOffsetStart$material3() > posX.element) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    draggingStart.element = z;
                                    this.L$0 = $this$awaitEachGesture;
                                    this.L$1 = event;
                                    this.L$2 = interaction;
                                    this.L$3 = posX;
                                    this.L$4 = draggingStart;
                                    this.label = 2;
                                    objM2894awaitSlop8vUncbI = SliderKt.m2894awaitSlop8vUncbI($this$awaitEachGesture, event.getId(), event.getType(), this);
                                    if (objM2894awaitSlop8vUncbI == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    interaction2 = interaction;
                                    $result4 = objM2894awaitSlop8vUncbI;
                                    $result5 = $result2;
                                    it = (Pair) $result4;
                                    if (it != null) {
                                        rangeSliderState = this.$state;
                                        slop = DragGestureDetectorCopyKt.m3358pointerSlopE8SPZFQ($this$awaitEachGesture.getViewConfiguration(), event.getType());
                                        if (Math.abs(rangeSliderState.getRawOffsetEnd$material3() - posX.element) < slop) {
                                            shouldUpdateCapturedThumb = false;
                                        } else {
                                            shouldUpdateCapturedThumb = false;
                                        }
                                        if (shouldUpdateCapturedThumb) {
                                            float dir2 = ((Number) it.getSecond()).floatValue();
                                            draggingStart.element = rangeSliderState.isRtl$material3() ? dir2 < 0.0f : dir2 >= 0.0f;
                                            float f2 = posX.element;
                                            long arg0$iv6 = PointerEventKt.positionChange((PointerInputChange) it.getFirst());
                                            int bits$iv$iv$iv6 = (int) (arg0$iv6 >> c);
                                            posX.element = f2 + Float.intBitsToFloat(bits$iv$iv$iv6);
                                        }
                                    }
                                    this.$rangeSliderLogic.captureThumb(draggingStart.element, posX.element, interaction2, this.$$this$coroutineScope);
                                    this.$state.setDragging$material3(true);
                                    long id2 = event.getId();
                                    final RangeSliderState rangeSliderState3 = this.$state;
                                    this.L$0 = interaction2;
                                    this.L$1 = draggingStart;
                                    this.L$2 = null;
                                    this.L$3 = null;
                                    this.L$4 = null;
                                    this.label = 3;
                                    $result6 = DragGestureDetectorKt.m475horizontalDragjO51t88($this$awaitEachGesture, id2, new Function1() { // from class: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1$$ExternalSyntheticLambda0
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return SliderKt.C03261.C00591.C00601.invokeSuspend$lambda$1(rangeSliderState3, draggingStart, (PointerInputChange) obj);
                                        }
                                    }, this);
                                    if ($result6 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    interaction3 = interaction2;
                                    success = ((Boolean) $result6).booleanValue();
                                    if (success) {
                                        finishInteraction = new DragInteraction.Stop(interaction3);
                                    } else {
                                        finishInteraction = new DragInteraction.Cancel(interaction3);
                                    }
                                    this.$state.setDragging$material3(false);
                                    this.$state.getGestureEndAction$material3().invoke(Boxing.boxBoolean(draggingStart.element));
                                    BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart, finishInteraction, null), 3, null);
                                    return Unit.INSTANCE;
                                case 2:
                                    $result4 = $result;
                                    Ref.BooleanRef draggingStart3 = (Ref.BooleanRef) this.L$4;
                                    posX = (Ref.FloatRef) this.L$3;
                                    interaction2 = (DragInteraction.Start) this.L$2;
                                    event = (PointerInputChange) this.L$1;
                                    $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                                    ResultKt.throwOnFailure($result4);
                                    c = ' ';
                                    draggingStart = draggingStart3;
                                    $result5 = $result4;
                                    it = (Pair) $result4;
                                    if (it != null) {
                                        rangeSliderState = this.$state;
                                        slop = DragGestureDetectorCopyKt.m3358pointerSlopE8SPZFQ($this$awaitEachGesture.getViewConfiguration(), event.getType());
                                        if (Math.abs(rangeSliderState.getRawOffsetEnd$material3() - posX.element) < slop) {
                                            shouldUpdateCapturedThumb = false;
                                        } else {
                                            shouldUpdateCapturedThumb = false;
                                        }
                                        if (shouldUpdateCapturedThumb) {
                                            float dir3 = ((Number) it.getSecond()).floatValue();
                                            draggingStart.element = rangeSliderState.isRtl$material3() ? dir3 < 0.0f : dir3 >= 0.0f;
                                            float f3 = posX.element;
                                            long arg0$iv7 = PointerEventKt.positionChange((PointerInputChange) it.getFirst());
                                            int bits$iv$iv$iv7 = (int) (arg0$iv7 >> c);
                                            posX.element = f3 + Float.intBitsToFloat(bits$iv$iv$iv7);
                                        }
                                    }
                                    this.$rangeSliderLogic.captureThumb(draggingStart.element, posX.element, interaction2, this.$$this$coroutineScope);
                                    this.$state.setDragging$material3(true);
                                    long id3 = event.getId();
                                    final RangeSliderState rangeSliderState4 = this.$state;
                                    this.L$0 = interaction2;
                                    this.L$1 = draggingStart;
                                    this.L$2 = null;
                                    this.L$3 = null;
                                    this.L$4 = null;
                                    this.label = 3;
                                    $result6 = DragGestureDetectorKt.m475horizontalDragjO51t88($this$awaitEachGesture, id3, new Function1() { // from class: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1$$ExternalSyntheticLambda0
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return SliderKt.C03261.C00591.C00601.invokeSuspend$lambda$1(rangeSliderState4, draggingStart, (PointerInputChange) obj);
                                        }
                                    }, this);
                                    if ($result6 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    interaction3 = interaction2;
                                    success = ((Boolean) $result6).booleanValue();
                                    if (success) {
                                        finishInteraction = new DragInteraction.Stop(interaction3);
                                    } else {
                                        finishInteraction = new DragInteraction.Cancel(interaction3);
                                    }
                                    this.$state.setDragging$material3(false);
                                    this.$state.getGestureEndAction$material3().invoke(Boxing.boxBoolean(draggingStart.element));
                                    BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart, finishInteraction, null), 3, null);
                                    return Unit.INSTANCE;
                                case 3:
                                    $result6 = $result;
                                    draggingStart2 = (Ref.BooleanRef) this.L$1;
                                    interaction3 = (DragInteraction.Start) this.L$0;
                                    try {
                                        try {
                                            ResultKt.throwOnFailure($result6);
                                            draggingStart = draggingStart2;
                                            $result5 = $result6;
                                            success = ((Boolean) $result6).booleanValue();
                                            if (success) {
                                                finishInteraction = new DragInteraction.Stop(interaction3);
                                            } else {
                                                finishInteraction = new DragInteraction.Cancel(interaction3);
                                            }
                                            this.$state.setDragging$material3(false);
                                        } catch (Throwable th2) {
                                            th = th2;
                                            this.$state.setDragging$material3(false);
                                            throw th;
                                        }
                                    } catch (CancellationException e3) {
                                        finishInteraction = new DragInteraction.Cancel(interaction3);
                                        this.$state.setDragging$material3(false);
                                        draggingStart = draggingStart2;
                                        this.$state.getGestureEndAction$material3().invoke(Boxing.boxBoolean(draggingStart.element));
                                        BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart, finishInteraction, null), 3, null);
                                        return Unit.INSTANCE;
                                    }
                                    this.$state.getGestureEndAction$material3().invoke(Boxing.boxBoolean(draggingStart.element));
                                    BuildersKt__Builders_commonKt.launch$default(this.$$this$coroutineScope, null, null, new AnonymousClass2(this.$rangeSliderLogic, draggingStart, finishInteraction, null), 3, null);
                                    return Unit.INSTANCE;
                                default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        static final Unit invokeSuspend$lambda$1(RangeSliderState $state, Ref.BooleanRef $draggingStart, PointerInputChange it) {
                            long arg0$iv = PointerEventKt.positionChange(it);
                            int bits$iv$iv$iv = (int) (arg0$iv >> 32);
                            float deltaX = Float.intBitsToFloat(bits$iv$iv$iv);
                            $state.onDrag$material3($draggingStart.element, $state.isRtl$material3() ? -deltaX : deltaX);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1$2, reason: invalid class name */
                        /* JADX INFO: compiled from: Slider.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                        @DebugMetadata(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1$2", f = "Slider.kt", i = {}, l = {2493}, m = "invokeSuspend", n = {}, s = {})
                        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ Ref.BooleanRef $draggingStart;
                            final /* synthetic */ DragInteraction $finishInteraction;
                            final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                            int label;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(RangeSliderLogic rangeSliderLogic, Ref.BooleanRef booleanRef, DragInteraction dragInteraction, Continuation<? super AnonymousClass2> continuation) {
                                super(2, continuation);
                                this.$rangeSliderLogic = rangeSliderLogic;
                                this.$draggingStart = booleanRef;
                                this.$finishInteraction = dragInteraction;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                return new AnonymousClass2(this.$rangeSliderLogic, this.$draggingStart, this.$finishInteraction, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object $result) {
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch (this.label) {
                                    case 0:
                                        ResultKt.throwOnFailure($result);
                                        this.label = 1;
                                        if (this.$rangeSliderLogic.activeInteraction(this.$draggingStart.element).emit(this.$finishInteraction, this) == coroutine_suspended) {
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

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object $result) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                                this.label = 1;
                                if (ForEachGestureKt.awaitEachGesture(this.$this_pointerInput, new C00601(this.$state, this.$rangeSliderLogic, $this$coroutineScope, null), this) == coroutine_suspended) {
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

                @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
                public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                    RangeSliderLogic rangeSliderLogic = new RangeSliderLogic(state, startInteractionSource, endInteractionSource);
                    Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C00591($this$pointerInput, state, rangeSliderLogic, null), continuation);
                    return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
                }
            });
        }
        return $this$rangeSliderPressDragModifier;
    }

    public static final float getTrackHeight() {
        return TrackHeight;
    }

    public static final float getThumbWidth() {
        return ThumbWidth;
    }

    public static final SliderState rememberSliderState(final float value, final int steps, final Function0<Unit> function0, final ClosedFloatingPointRange<Float> closedFloatingPointRange, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1193499219, "C(rememberSliderState)N(value,steps,onValueChangeFinished,valueRange)2912@125235L188,2912@125156L267:Slider.kt#uh7d8r");
        if ((i & 1) != 0) {
            value = 0.0f;
        }
        if ((i & 2) != 0) {
            steps = 0;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        if ((i & 8) != 0) {
            closedFloatingPointRange = RangesKt.rangeTo(0.0f, 1.0f);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1193499219, $changed, -1, "androidx.compose.material3.rememberSliderState (Slider.kt:2911)");
        }
        Object[] objArr = new Object[0];
        Saver<SliderState, ?> Saver = SliderState.INSTANCE.Saver(function0, closedFloatingPointRange);
        ComposerKt.sourceInformationMarkerStart($composer, 196492495, "CC(remember):Slider.kt#9igjgp");
        boolean z = true;
        boolean z2 = (((($changed & 14) ^ 6) > 4 && $composer.changed(value)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(steps)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(function0)) || ($changed & 384) == 256);
        if (((($changed & 7168) ^ 3072) <= 2048 || !$composer.changed(closedFloatingPointRange)) && ($changed & 3072) != 2048) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SliderKt.rememberSliderState$lambda$60$lambda$59(value, steps, function0, closedFloatingPointRange);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SliderState sliderState = (SliderState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return sliderState;
    }

    static final SliderState rememberSliderState$lambda$60$lambda$59(float $value, int $steps, Function0 $onValueChangeFinished, ClosedFloatingPointRange $valueRange) {
        return new SliderState($value, $steps, $onValueChangeFinished, $valueRange);
    }

    public static final RangeSliderState rememberRangeSliderState(float activeRangeStart, float activeRangeEnd, int steps, Function0<Unit> function0, ClosedFloatingPointRange<Float> closedFloatingPointRange, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 756708139, "C(rememberRangeSliderState)N(activeRangeStart,activeRangeEnd,steps,onValueChangeFinished,valueRange)3126@135179L260,3126@135095L344:Slider.kt#uh7d8r");
        final float activeRangeStart2 = (i & 1) != 0 ? 0.0f : activeRangeStart;
        final float activeRangeEnd2 = (i & 2) != 0 ? 1.0f : activeRangeEnd;
        final int steps2 = (i & 4) != 0 ? 0 : steps;
        final Function0<Unit> function1 = (i & 8) != 0 ? null : function0;
        final ClosedFloatingPointRange<Float> closedFloatingPointRangeRangeTo = (i & 16) != 0 ? RangesKt.rangeTo(0.0f, 1.0f) : closedFloatingPointRange;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(756708139, $changed, -1, "androidx.compose.material3.rememberRangeSliderState (Slider.kt:3125)");
        }
        Object[] objArr = new Object[0];
        Saver<RangeSliderState, ?> Saver = RangeSliderState.INSTANCE.Saver(function1, closedFloatingPointRangeRangeTo);
        ComposerKt.sourceInformationMarkerStart($composer, 1347061551, "CC(remember):Slider.kt#9igjgp");
        boolean z = true;
        boolean z2 = (((($changed & 14) ^ 6) > 4 && $composer.changed(activeRangeStart2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(activeRangeEnd2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(steps2)) || ($changed & 384) == 256) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(function1)) || ($changed & 3072) == 2048);
        if ((((57344 & $changed) ^ 24576) <= 16384 || !$composer.changed(closedFloatingPointRangeRangeTo)) && ($changed & 24576) != 16384) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.SliderKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SliderKt.rememberRangeSliderState$lambda$62$lambda$61(activeRangeStart2, activeRangeEnd2, steps2, function1, closedFloatingPointRangeRangeTo);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        RangeSliderState rangeSliderState = (RangeSliderState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return rangeSliderState;
    }

    static final RangeSliderState rememberRangeSliderState$lambda$62$lambda$61(float $activeRangeStart, float $activeRangeEnd, int $steps, Function0 $onValueChangeFinished, ClosedFloatingPointRange $valueRange) {
        return new RangeSliderState($activeRangeStart, $activeRangeEnd, $steps, $onValueChangeFinished, $valueRange);
    }

    public static final long SliderRange(float start, float endInclusive) {
        boolean z = true;
        boolean isUnspecified = Float.isNaN(start) && Float.isNaN(endInclusive);
        if (!isUnspecified && start > endInclusive) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(("start(" + start + ") must be <= endInclusive(" + endInclusive + ')').toString());
        }
        long v1$iv = Float.floatToRawIntBits(start);
        long v2$iv = Float.floatToRawIntBits(endInclusive);
        return SliderRange.m2901constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    public static final long SliderRange(ClosedFloatingPointRange<Float> closedFloatingPointRange) {
        float start = closedFloatingPointRange.getStart().floatValue();
        float endInclusive = closedFloatingPointRange.getEndInclusive().floatValue();
        boolean z = true;
        boolean isUnspecified = Float.isNaN(start) && Float.isNaN(endInclusive);
        if (!isUnspecified && start > endInclusive) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(("ClosedFloatingPointRange<Float>.start(" + start + ") must be <= ClosedFloatingPoint.endInclusive(" + endInclusive + ')').toString());
        }
        long v1$iv = Float.floatToRawIntBits(start);
        long v2$iv = Float.floatToRawIntBits(endInclusive);
        return SliderRange.m2901constructorimpl((v1$iv << 32) | (4294967295L & v2$iv));
    }

    /* JADX INFO: renamed from: isSpecified-If1S1O4, reason: not valid java name */
    public static final boolean m2895isSpecifiedIf1S1O4(long $this$isSpecified) {
        return $this$isSpecified != SliderRange.INSTANCE.m2910getUnspecifiedFYbKRX4();
    }

    public static final VerticalAlignmentLine getCornerSizeAlignmentLine() {
        return CornerSizeAlignmentLine;
    }
}
