package androidx.compose.ui.platform;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.os.Trace;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ScrollCaptureTarget;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import androidx.autofill.HintConstants;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.collection.ScatterMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.retain.ForgetfulRetainedValuesStore;
import androidx.compose.runtime.retain.RetainedValuesStore;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.R;
import androidx.compose.ui.SessionMutex;
import androidx.compose.ui.autofill.AndroidAutofill;
import androidx.compose.ui.autofill.AndroidAutofillManager;
import androidx.compose.ui.autofill.AndroidAutofill_androidKt;
import androidx.compose.ui.autofill.Autofill;
import androidx.compose.ui.autofill.AutofillCallback;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.autofill.PlatformAutofillManagerImpl;
import androidx.compose.ui.contentcapture.AndroidContentCaptureManager;
import androidx.compose.ui.draganddrop.AndroidDragAndDropManager;
import androidx.compose.ui.draganddrop.ComposeDragShadowBuilder;
import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.focus.FocusListener;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusOwnerImplKt;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTraversalKt;
import androidx.compose.ui.focus.PlatformFocusOwner;
import androidx.compose.ui.focus.TwoDimensionalFocusSearchKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidGraphicsContext_androidKt;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.InputModeManagerImpl;
import androidx.compose.ui.input.indirect.AndroidIndirectPointerEvent;
import androidx.compose.ui.input.indirect.AndroidIndirectPointerEvent_androidKt;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerEventPrimaryDirectionalMotionAxis;
import androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.MatrixPositionCalculator;
import androidx.compose.ui.input.pointer.MotionEventAdapter;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PointerInputEvent;
import androidx.compose.ui.input.pointer.PointerInputEventData;
import androidx.compose.ui.input.pointer.PointerInputEventProcessor;
import androidx.compose.ui.input.pointer.PointerInputEventProcessorKt;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.InsetsListener;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.layout.RectRulers;
import androidx.compose.ui.layout.RootMeasurePolicy;
import androidx.compose.ui.layout.RulerScope;
import androidx.compose.ui.layout.WindowInsetsRulerProvider;
import androidx.compose.ui.layout.WindowInsetsRulers_androidKt;
import androidx.compose.ui.layout.WindowWindowInsetsAnimationValues;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.MeasureAndLayoutDelegate;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.OutOfFrameExecutor;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.platform.AndroidComposeView.RootModifierNode;
import androidx.compose.ui.relocation.BringIntoViewModifierNode;
import androidx.compose.ui.scrollcapture.ScrollCapture;
import androidx.compose.ui.semantics.EmptySemanticsModifier;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.spatial.RectManager;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontFamilyResolver_androidKt;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputServiceAndroid;
import androidx.compose.ui.unit.AndroidDensity_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.core.app.NotificationCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AndroidComposeView.android.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000¢\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d*\u0002Ù\u0002\b\u0001\u0018\u0000 ÿ\u00042\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000b:\u0006ÿ\u0004\u0080\u0005\u0081\u0005B\u0017\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010M\u001a\u00020NH\u0016J\u0017\u0010^\u001a\u00020\u00162\u0006\u0010_\u001a\u00020`H\u0002¢\u0006\u0004\ba\u0010bJ\u0017\u0010c\u001a\u00020\u00162\u0006\u0010_\u001a\u00020`H\u0002¢\u0006\u0004\bd\u0010bJ\u0017\u0010e\u001a\u00020\u00162\u0006\u0010_\u001a\u00020`H\u0002¢\u0006\u0004\bf\u0010bJ\u0017\u0010g\u001a\u00020\u00162\u0006\u0010_\u001a\u00020`H\u0016¢\u0006\u0004\bh\u0010bJ\u0019\u0010i\u001a\u0004\u0018\u00010$2\u0006\u0010_\u001a\u00020`H\u0002¢\u0006\u0004\bj\u0010kJ\n\u0010l\u001a\u0004\u0018\u00010mH\u0016J\b\u0010n\u001a\u000207H\u0016J\u0012\u0010o\u001a\u0004\u0018\u00010$2\u0006\u0010p\u001a\u00020NH\u0002JA\u0010\u009e\u0002\u001a\u00030\u009f\u00022.\u0010 \u0002\u001a)\b\u0001\u0012\u0005\u0012\u00030¢\u0002\u0012\f\u0012\n\u0012\u0005\u0012\u00030\u009f\u00020£\u0002\u0012\u0007\u0012\u0005\u0018\u00010¤\u00020¡\u0002¢\u0006\u0003\b¥\u0002H\u0096@¢\u0006\u0003\u0010¦\u0002J\u0013\u0010ã\u0002\u001a\u0002072\b\u0010ä\u0002\u001a\u00030å\u0002H\u0016J=\u0010æ\u0002\u001a\u0002072!\u0010ç\u0002\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010$\u0018\u00010è\u0002j\r\u0012\u0006\u0012\u0004\u0018\u00010$\u0018\u0001`é\u00022\u0006\u0010p\u001a\u00020N2\u0007\u0010ê\u0002\u001a\u00020NH\u0016J\u0013\u0010ë\u0002\u001a\u0002072\b\u0010ì\u0002\u001a\u00030í\u0002H\u0016J.\u0010ò\u0002\u001a\u0002072\b\u0010ó\u0002\u001a\u00030å\u00022\b\u0010ô\u0002\u001a\u00030õ\u00022\u000f\u0010ö\u0002\u001a\n\u0012\u0005\u0012\u00030ø\u00020÷\u0002H\u0016J\u0013\u0010ù\u0002\u001a\u0002072\b\u0010ú\u0002\u001a\u00030û\u0002H\u0016J\u0013\u0010ü\u0002\u001a\u0002072\b\u0010ú\u0002\u001a\u00030û\u0002H\u0016J\u001e\u0010ý\u0002\u001a\u0004\u0018\u00010$2\t\u0010þ\u0002\u001a\u0004\u0018\u00010$2\u0006\u0010p\u001a\u00020NH\u0016J\u001b\u0010ÿ\u0002\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002J\u001b\u0010\u0081\u0003\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002J\u001b\u0010\u0082\u0003\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002J\u001d\u0010\u0083\u0003\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002H\u0016J$\u0010\u0084\u0003\u001a\u00020\u00162\b\u0010_\u001a\u0004\u0018\u00010`2\t\u0010\u0080\u0003\u001a\u0004\u0018\u00010mH\u0016¢\u0006\u0003\b\u0085\u0003J\t\u0010\u0086\u0003\u001a\u000207H\u0016J&\u0010\u0087\u0003\u001a\u0002072\u0007\u0010\u0088\u0003\u001a\u00020\u00162\u0006\u0010p\u001a\u00020N2\n\u0010\u0080\u0003\u001a\u0005\u0018\u00010å\u0002H\u0014J!\u0010\u0087\u0003\u001a\u0002072\n\u0010\u0089\u0003\u001a\u0005\u0018\u00010\u008a\u00032\n\u0010\u008b\u0003\u001a\u0005\u0018\u00010\u008a\u0003H\u0016J\u0012\u0010\u008c\u0003\u001a\u0002072\u0007\u0010\u008d\u0003\u001a\u00020\u0016H\u0016J\u001c\u0010\u008e\u0003\u001a\u00020\u00162\b\u0010\u008f\u0003\u001a\u00030\u0090\u0003H\u0016¢\u0006\u0006\b\u0091\u0003\u0010\u0092\u0003J\u0013\u0010\u0093\u0003\u001a\u00020\u00162\b\u0010\u0094\u0003\u001a\u00030\u0095\u0003H\u0017J\u0013\u0010\u0096\u0003\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030\u0098\u0003H\u0016J\u0013\u0010\u0099\u0003\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030\u0098\u0003H\u0016J\u0012\u0010\u009a\u0003\u001a\u0002072\u0007\u0010\u009b\u0003\u001a\u00020\u0016H\u0016J\u0013\u0010\u009c\u0003\u001a\u0002072\b\u0010\u009d\u0003\u001a\u00030è\u0001H\u0016J\u0012\u0010\u009e\u0003\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0016J\u0012\u0010 \u0003\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0016J\u0012\u0010¡\u0003\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0016J\u0012\u0010¢\u0003\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0016J\u0007\u0010£\u0003\u001a\u000207J\t\u0010¤\u0003\u001a\u000207H\u0016J\u0018\u0010¥\u0003\u001a\u0002072\r\u0010¦\u0003\u001a\b\u0012\u0004\u0012\u00020706H\u0016JC\u0010§\u0003\u001a\u00020\u00162\b\u0010¨\u0003\u001a\u00030©\u00032\b\u0010ª\u0003\u001a\u00030«\u00032\u001b\u0010¬\u0003\u001a\u0016\u0012\u0005\u0012\u00030\u00ad\u0003\u0012\u0004\u0012\u0002070\u008a\u0002¢\u0006\u0003\b¥\u0002H\u0002¢\u0006\u0006\b®\u0003\u0010¯\u0003J\u0012\u0010°\u0003\u001a\u0002072\u0007\u0010±\u0003\u001a\u00020\u0001H\u0002J&\u0010²\u0003\u001a\u0002072\u0007\u0010³\u0003\u001a\u00020N2\b\u0010´\u0003\u001a\u00030µ\u00032\b\u0010¶\u0003\u001a\u00030·\u0003H\u0002J\u0014\u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$H\u0016J\u001d\u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$2\u0007\u0010º\u0003\u001a\u00020NH\u0016J&\u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$2\u0007\u0010»\u0003\u001a\u00020N2\u0007\u0010¼\u0003\u001a\u00020NH\u0016J \u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$2\n\u0010½\u0003\u001a\u0005\u0018\u00010¾\u0003H\u0016J)\u0010¸\u0003\u001a\u0002072\t\u0010¹\u0003\u001a\u0004\u0018\u00010$2\u0007\u0010º\u0003\u001a\u00020N2\n\u0010½\u0003\u001a\u0005\u0018\u00010¾\u0003H\u0016J\u0019\u0010¿\u0003\u001a\u0002072\u0007\u0010#\u001a\u00030À\u00032\u0007\u0010Á\u0003\u001a\u00020|J\u0010\u0010Â\u0003\u001a\u0002072\u0007\u0010#\u001a\u00030À\u0003J\u001a\u0010Ã\u0003\u001a\u0002072\u0007\u0010#\u001a\u00030À\u00032\b\u0010Ä\u0003\u001a\u00030Å\u0003J\u0016\u0010Æ\u0003\u001a\u0002072\u000b\b\u0002\u0010Ç\u0003\u001a\u0004\u0018\u00010|H\u0002J\r\u0010È\u0003\u001a\u00020\u0016*\u00020|H\u0002J\u0012\u0010É\u0003\u001a\u0002072\u0007\u0010Ê\u0003\u001a\u00020\u0016H\u0016J%\u0010É\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\b\u0010Ë\u0003\u001a\u00030ã\u0001H\u0016¢\u0006\u0006\bÌ\u0003\u0010Í\u0003J\t\u0010Î\u0003\u001a\u000207H\u0002J\u001b\u0010Ï\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010Ð\u0003\u001a\u00020\u0016H\u0016J-\u0010Ñ\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010Ð\u0003\u001a\u00020\u00162\u0007\u0010Ò\u0003\u001a\u00020\u00162\u0007\u0010Æ\u0003\u001a\u00020\u0016H\u0016J$\u0010Ó\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010Ð\u0003\u001a\u00020\u00162\u0007\u0010Ò\u0003\u001a\u00020\u0016H\u0016J\u0012\u0010Ô\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|H\u0016J\t\u0010Õ\u0003\u001a\u000207H\u0016J\u0015\u0010Ö\u0003\u001a\u0002072\n\u0010×\u0003\u001a\u0005\u0018\u00010\u008d\u0001H\u0016J\u001b\u0010Ø\u0003\u001a\u0002072\u0007\u0010Ù\u0003\u001a\u00020N2\u0007\u0010Ú\u0003\u001a\u00020NH\u0014J\u0018\u0010Û\u0003\u001a\u00020N*\u00030Ü\u0003H\u0082\n¢\u0006\u0006\bÝ\u0003\u0010Þ\u0003J\u0018\u0010ß\u0003\u001a\u00020N*\u00030Ü\u0003H\u0082\n¢\u0006\u0006\bà\u0003\u0010Þ\u0003J%\u0010á\u0003\u001a\u00030Ü\u00032\u0007\u0010â\u0003\u001a\u00020N2\u0007\u0010ã\u0003\u001a\u00020NH\u0002¢\u0006\u0006\bä\u0003\u0010å\u0003J\u001c\u0010æ\u0003\u001a\u00030Ü\u00032\u0007\u0010ç\u0003\u001a\u00020NH\u0002¢\u0006\u0006\bè\u0003\u0010é\u0003J6\u0010ê\u0003\u001a\u0002072\u0007\u0010ë\u0003\u001a\u00020\u00162\u0007\u0010ì\u0003\u001a\u00020N2\u0007\u0010í\u0003\u001a\u00020N2\u0007\u0010î\u0003\u001a\u00020N2\u0007\u0010ã\u0003\u001a\u00020NH\u0014J\t\u0010ð\u0003\u001a\u000207H\u0002J\u0013\u0010ñ\u0003\u001a\u0002072\b\u0010Ä\u0003\u001a\u00030Å\u0003H\u0014Ji\u0010ò\u0003\u001a\u00030¬\u00012B\u0010ó\u0003\u001a=\u0012\u0017\u0012\u00150ô\u0003¢\u0006\u000f\bõ\u0003\u0012\n\bö\u0003\u0012\u0005\b\b(Ä\u0003\u0012\u0019\u0012\u0017\u0018\u00010÷\u0003¢\u0006\u000f\bõ\u0003\u0012\n\bö\u0003\u0012\u0005\b\b(ø\u0003\u0012\u0004\u0012\u0002070¡\u00022\r\u0010ù\u0003\u001a\b\u0012\u0004\u0012\u000207062\n\u0010ú\u0003\u001a\u0005\u0018\u00010÷\u0003H\u0016J\u0019\u0010û\u0003\u001a\u00020\u00162\b\u0010ü\u0003\u001a\u00030¬\u0001H\u0000¢\u0006\u0003\bý\u0003J\t\u0010þ\u0003\u001a\u000207H\u0016J\u0012\u0010ÿ\u0003\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|H\u0016J\u0012\u0010\u0080\u0004\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|H\u0016J\u001b\u0010\u0081\u0004\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010\u0082\u0004\u001a\u00020NH\u0016J\u001b\u0010\u0083\u0004\u001a\u0002072\u0007\u0010Á\u0003\u001a\u00020|2\u0007\u0010\u0082\u0004\u001a\u00020NH\u0016J\u0016\u0010\u0084\u0004\u001a\u0002072\u000b\u0010#\u001a\u00070$j\u0003`\u0085\u0004H\u0016J\u0013\u0010\u0086\u0004\u001a\u0002072\b\u0010¦\u0003\u001a\u00030\u0087\u0004H\u0016J\u0013\u0010\u0088\u0004\u001a\u0002072\b\u0010Ä\u0003\u001a\u00030Å\u0003H\u0014J\"\u0010\u0089\u0004\u001a\u0002072\b\u0010ü\u0003\u001a\u00030¬\u00012\u0007\u0010\u008a\u0004\u001a\u00020\u0016H\u0000¢\u0006\u0003\b\u008b\u0004J\u001e\u0010\u008c\u0004\u001a\u0002072\u0015\u0010\u008d\u0004\u001a\u0010\u0012\u0005\u0012\u00030þ\u0001\u0012\u0004\u0012\u0002070\u008a\u0002J\u0010\u0010\u008e\u0004\u001a\u000207H\u0086@¢\u0006\u0003\u0010\u008f\u0004J\u0010\u0010\u0090\u0004\u001a\u000207H\u0086@¢\u0006\u0003\u0010\u008f\u0004J\u0012\u0010\u0091\u0004\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0002J\u0012\u0010\u0092\u0004\u001a\u0002072\u0007\u0010\u009f\u0003\u001a\u00020|H\u0002J\t\u0010\u0093\u0004\u001a\u000207H\u0016J\t\u0010\u0094\u0004\u001a\u000207H\u0014J#\u0010\u0095\u0004\u001a\u0004\u0018\u0001002\n\u0010\u0096\u0004\u001a\u0005\u0018\u00010û\u00022\n\u0010\u0097\u0004\u001a\u0005\u0018\u00010\u0098\u0004H\u0002J\t\u0010\u0099\u0004\u001a\u000207H\u0014J\u001e\u0010\u009a\u0004\u001a\u0002072\n\u0010ì\u0002\u001a\u0005\u0018\u00010í\u00022\u0007\u0010\u009b\u0004\u001a\u00020NH\u0016J\u001a\u0010Á\u0001\u001a\u0002072\u000f\u0010\u009c\u0004\u001a\n\u0012\u0005\u0012\u00030\u009e\u00040\u009d\u0004H\u0016J0\u0010\u009f\u0004\u001a\u0002072\b\u0010 \u0004\u001a\u00030¡\u00042\b\u0010¢\u0004\u001a\u00030ð\u00012\u0011\u0010£\u0004\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010¤\u00040÷\u0002H\u0017J\u001c\u0010¥\u0004\u001a\u0002072\u0011\u0010¦\u0004\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010¨\u00040§\u0004H\u0017J\u0013\u0010©\u0004\u001a\u00020\u00162\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0016J\u0013\u0010«\u0004\u001a\u00020\u00162\b\u0010\u0094\u0003\u001a\u00030\u0095\u0003H\u0002J\u0013\u0010¬\u0004\u001a\u00020\u00162\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0016J\u0013\u0010\u00ad\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0002J\u001d\u0010®\u0004\u001a\u00030¯\u00042\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0002¢\u0006\u0006\b°\u0004\u0010±\u0004J\u001d\u0010²\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u00022\b\u0010³\u0004\u001a\u00030Ð\u0002H\u0002J\u0013\u0010´\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0002J\u001d\u0010µ\u0004\u001a\u00030¯\u00042\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0002¢\u0006\u0006\b¶\u0004\u0010±\u0004J1\u0010·\u0004\u001a\u0002072\b\u0010ª\u0004\u001a\u00030Ð\u00022\u0007\u0010¸\u0004\u001a\u00020N2\b\u0010¹\u0004\u001a\u00030è\u00012\t\b\u0002\u0010º\u0004\u001a\u00020\u0016H\u0002J\u0011\u0010»\u0004\u001a\u00020\u00162\u0006\u0010p\u001a\u00020NH\u0016J\u0011\u0010¼\u0004\u001a\u00020\u00162\u0006\u0010p\u001a\u00020NH\u0016J\u0013\u0010½\u0004\u001a\u00020\u00162\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0002J\u001b\u0010¾\u0004\u001a\u00020\u00132\u0007\u0010¿\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bÀ\u0004\u0010Á\u0004J\u001c\u0010¾\u0004\u001a\u0002072\b\u0010Â\u0004\u001a\u00030ò\u0001H\u0016¢\u0006\u0006\bÃ\u0004\u0010Ä\u0004J\u001b\u0010Å\u0004\u001a\u00020\u00132\u0007\u0010Æ\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bÇ\u0004\u0010Á\u0004J\t\u0010È\u0004\u001a\u000207H\u0002J\u0013\u0010È\u0004\u001a\u0002072\b\u0010ª\u0004\u001a\u00030Ð\u0002H\u0002J\t\u0010É\u0004\u001a\u000207H\u0002J\t\u0010Ê\u0004\u001a\u000207H\u0002J\t\u0010Ë\u0004\u001a\u00020\u0016H\u0016J\u0016\u0010Ì\u0004\u001a\u0005\u0018\u00010Í\u00042\b\u0010Î\u0004\u001a\u00030Ï\u0004H\u0016J\u001b\u0010Ð\u0004\u001a\u00020\u00132\u0007\u0010Ñ\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bÒ\u0004\u0010Á\u0004J\u001b\u0010Ó\u0004\u001a\u00020\u00132\u0007\u0010¿\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bÔ\u0004\u0010Á\u0004J\u0013\u0010Õ\u0004\u001a\u0002072\b\u0010Ö\u0004\u001a\u00030´\u0001H\u0014J\t\u0010×\u0004\u001a\u000207H\u0002J\u0013\u0010Ø\u0004\u001a\u0002072\b\u0010Ö\u0004\u001a\u00030´\u0001H\u0002J\u0012\u0010Ù\u0004\u001a\u0002072\u0007\u0010·\u0002\u001a\u00020NH\u0016J\t\u0010Ú\u0004\u001a\u00020\u0016H\u0002J\u0013\u0010Û\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0016J\u0013\u0010Ü\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0002J\u0013\u0010Ý\u0004\u001a\u00020\u00162\b\u0010\u0097\u0003\u001a\u00030Ð\u0002H\u0002J\u001d\u0010Þ\u0004\u001a\u0004\u0018\u00010$2\u0007\u0010ß\u0004\u001a\u00020N2\u0007\u0010à\u0004\u001a\u00020$H\u0002J\u001d\u0010á\u0004\u001a\u00030â\u00042\b\u0010\u0097\u0003\u001a\u00030Ð\u00022\u0007\u0010ã\u0004\u001a\u00020NH\u0017J\u0012\u0010è\u0004\u001a\u0004\u0018\u00010$2\u0007\u0010ß\u0004\u001a\u00020NJ\t\u0010ê\u0004\u001a\u00020\u0016H\u0016J\t\u0010ì\u0004\u001a\u000207H\u0016J\t\u0010í\u0004\u001a\u000207H\u0016J\t\u0010ï\u0004\u001a\u000207H\u0016J\t\u0010ð\u0004\u001a\u000207H\u0016J\u0018\u0010ô\u0004\u001a\u0002072\r\u0010õ\u0004\u001a\b\u0012\u0004\u0012\u00020706H\u0016J\u0013\u0010ö\u0004\u001a\u0002072\b\u0010÷\u0004\u001a\u00030Ö\u0002H\u0017J\u001b\u0010ø\u0004\u001a\u0002072\u0007\u0010ù\u0004\u001a\u00020\u0013H\u0016¢\u0006\u0006\bú\u0004\u0010ú\u0001J\t\u0010û\u0004\u001a\u000207H\u0016J\t\u0010ü\u0004\u001a\u000207H\u0016J\u0012\u0010ý\u0004\u001a\u0002072\u0007\u0010þ\u0004\u001a\u00020\u0016H\u0016R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00101\u001a\u0002002\u0006\u0010/\u001a\u000200@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001a\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070605X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010<\u001a\u00020;2\u0006\u0010:\u001a\u00020;8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u000e\u0010C\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010D\u001a\u00020\u0016X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u000e\u0010G\u001a\u00020HX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\u00020JX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR$\u0010\u000e\u001a\u00020\u000f2\u0006\u0010/\u001a\u00020\u000f@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0014\u0010S\u001a\u00020TX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010VR\u000e\u0010W\u001a\u00020XX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010Y\u001a\u00020Z8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u000e\u0010]\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010s\u001a\u00020tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bu\u0010vR\u0011\u0010w\u001a\u00020x¢\u0006\b\n\u0000\u001a\u0004\by\u0010zR\u001a\u0010{\u001a\u00020|X\u0096\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b}\u0010\u001a\u001a\u0004\b~\u0010\u007fR\u001e\u0010\u0080\u0001\u001a\t\u0012\u0004\u0012\u00020|0\u0081\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0018\u0010\u0084\u0001\u001a\u00030\u0085\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0018\u0010\u0088\u0001\u001a\u00030\u0089\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R\"\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001\"\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0018\u0010\u0092\u0001\u001a\u00030\u0093\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0010\u0010\u0096\u0001\u001a\u00030\u0097\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001\"\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0018\u0010\u009e\u0001\u001a\u00030\u009f\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b \u0001\u0010¡\u0001R\u0018\u0010¢\u0001\u001a\u00030£\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¤\u0001\u0010¥\u0001R\u0018\u0010¦\u0001\u001a\u00030§\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¨\u0001\u0010©\u0001R\u0017\u0010ª\u0001\u001a\n\u0012\u0005\u0012\u00030¬\u00010«\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u00ad\u0001\u001a\f\u0012\u0005\u0012\u00030¬\u0001\u0018\u00010«\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010®\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010¯\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010°\u0001\u001a\u00030±\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010²\u0001\u001a\u00030³\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010µ\u0001\u001a\u00030´\u00012\u0007\u0010:\u001a\u00030´\u00018F@FX\u0086\u008e\u0002¢\u0006\u0017\n\u0005\bº\u0001\u0010B\u001a\u0006\b¶\u0001\u0010·\u0001\"\u0006\b¸\u0001\u0010¹\u0001R\u0012\u0010»\u0001\u001a\u0005\u0018\u00010¼\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010½\u0001\u001a\u0005\u0018\u00010¾\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b¿\u0001\u0010À\u0001R\u001a\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÃ\u0001\u0010Ä\u0001R\u001a\u0010Å\u0001\u001a\u0005\u0018\u00010Æ\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÇ\u0001\u0010È\u0001R\u000f\u0010É\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010Ê\u0001\u001a\u00030Ë\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÌ\u0001\u0010Í\u0001R\u0018\u0010Î\u0001\u001a\u00030Ï\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÐ\u0001\u0010Ñ\u0001R\u0018\u0010Ò\u0001\u001a\u00030Ó\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÔ\u0001\u0010Õ\u0001R'\u0010Ö\u0001\u001a\u00020\u00168VX\u0096\u000e¢\u0006\u0018\n\u0000\u0012\u0005\b×\u0001\u0010\u001a\u001a\u0005\bØ\u0001\u0010F\"\u0006\bÙ\u0001\u0010Ú\u0001R\u0012\u0010Û\u0001\u001a\u0005\u0018\u00010Ü\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010Ý\u0001\u001a\u00030Ü\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bÞ\u0001\u0010ß\u0001R\u0012\u0010à\u0001\u001a\u0005\u0018\u00010á\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010â\u0001\u001a\u0005\u0018\u00010ã\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010ä\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010å\u0001\u001a\u00030æ\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010ç\u0001\u001a\u00030è\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bé\u0001\u0010ê\u0001R\u0016\u0010ë\u0001\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bì\u0001\u0010FR\u0012\u0010í\u0001\u001a\u00030î\u0001X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010ï\u0001\u001a\u00030ð\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010ñ\u0001\u001a\u00030ò\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010ó\u0001R\u0013\u0010ô\u0001\u001a\u00030ò\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010ó\u0001R\u0013\u0010õ\u0001\u001a\u00030ò\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010ó\u0001R+\u0010ö\u0001\u001a\u00030è\u00018\u0000@\u0000X\u0081\u000e¢\u0006\u0019\n\u0000\u0012\u0005\b÷\u0001\u0010\u001a\u001a\u0006\bø\u0001\u0010ê\u0001\"\u0006\bù\u0001\u0010ú\u0001R\u000f\u0010û\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010ü\u0001\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000f\u0010ý\u0001\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R7\u0010ÿ\u0001\u001a\u0005\u0018\u00010þ\u00012\t\u0010:\u001a\u0005\u0018\u00010þ\u00018B@BX\u0082\u008e\u0002¢\u0006\u0017\n\u0005\b\u0084\u0002\u0010B\u001a\u0006\b\u0080\u0002\u0010\u0081\u0002\"\u0006\b\u0082\u0002\u0010\u0083\u0002R#\u0010\u0085\u0002\u001a\u0005\u0018\u00010þ\u00018FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\b\u0087\u0002\u0010\u0088\u0002\u001a\u0006\b\u0086\u0002\u0010\u0081\u0002R\u001f\u0010\u0089\u0002\u001a\u0012\u0012\u0005\u0012\u00030þ\u0001\u0012\u0004\u0012\u000207\u0018\u00010\u008a\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u008b\u0002\u001a\u00030\u008c\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u008d\u0002\u001a\u00030\u008e\u00028\u0016X\u0097\u0004¢\u0006\u0011\n\u0000\u0012\u0005\b\u008f\u0002\u0010\u001a\u001a\u0006\b\u0090\u0002\u0010\u0091\u0002R\u001a\u0010\u0092\u0002\u001a\n\u0012\u0005\u0012\u00030\u0094\u00020\u0093\u0002X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0095\u0002R\u0018\u0010\u0096\u0002\u001a\u00030\u0097\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0098\u0002\u0010\u0099\u0002R\u0018\u0010\u009a\u0002\u001a\u00030\u009b\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009c\u0002\u0010\u009d\u0002R!\u0010§\u0002\u001a\u00030¨\u00028\u0016X\u0097\u0004¢\u0006\u0011\n\u0000\u0012\u0005\b©\u0002\u0010\u001a\u001a\u0006\bª\u0002\u0010«\u0002R3\u0010\u00ad\u0002\u001a\u00030¬\u00022\u0007\u0010:\u001a\u00030¬\u00028V@RX\u0096\u008e\u0002¢\u0006\u0017\n\u0005\b²\u0002\u0010B\u001a\u0006\b®\u0002\u0010¯\u0002\"\u0006\b°\u0002\u0010±\u0002R\u001c\u0010³\u0002\u001a\u00020N*\u00030´\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b´\u0002\u0010µ\u0002R3\u0010·\u0002\u001a\u00030¶\u00022\u0007\u0010:\u001a\u00030¶\u00028V@RX\u0096\u008e\u0002¢\u0006\u0017\n\u0005\b¼\u0002\u0010B\u001a\u0006\b¸\u0002\u0010¹\u0002\"\u0006\bº\u0002\u0010»\u0002R\u0018\u0010½\u0002\u001a\u00030¾\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¿\u0002\u0010À\u0002R\u0010\u0010Á\u0002\u001a\u00030Â\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010Ã\u0002\u001a\u00030Ä\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\bÅ\u0002\u0010Æ\u0002R\u0018\u0010Ç\u0002\u001a\u00030È\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÉ\u0002\u0010Ê\u0002R\u0018\u0010Ë\u0002\u001a\u00030Ì\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bÍ\u0002\u0010Î\u0002R\u0012\u0010Ï\u0002\u001a\u0005\u0018\u00010Ð\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Ñ\u0002\u001a\u00030è\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010Ò\u0002\u001a\n\u0012\u0005\u0012\u00030¬\u00010Ó\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010Ô\u0002\u001a\u0011\u0012\f\u0012\n\u0012\u0004\u0012\u000207\u0018\u0001060«\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Õ\u0002\u001a\u00030Ö\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010×\u0002\u001a\u00030Ö\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010Ø\u0002\u001a\u00030Ù\u0002X\u0082\u0004¢\u0006\u0005\n\u0003\u0010Ú\u0002R\u000f\u0010Û\u0002\u001a\u000209X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010Ü\u0002\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010Ý\u0002\u001a\u00030Þ\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010ß\u0002\u001a\b\u0012\u0004\u0012\u00020706X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010à\u0002\u001a\u00030á\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010â\u0002\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010î\u0002\u001a\u0005\u0018\u00010ï\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010ð\u0002\u001a\u00020\u00168@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bñ\u0002\u0010FR\u0011\u0010ï\u0003\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010ä\u0004\u001a\u00030å\u0004X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\bæ\u0004\u0010ç\u0004R\u0016\u0010é\u0004\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bé\u0004\u0010FR\u000f\u0010ë\u0004\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010î\u0004\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010ñ\u0004\u001a\u0004\u0018\u00010\u00008VX\u0096\u0004¢\u0006\b\u001a\u0006\bò\u0004\u0010ó\u0004¨\u0006\u0082\u0005"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView;", "Landroid/view/ViewGroup;", "Landroidx/compose/ui/node/Owner;", "Landroidx/compose/ui/focus/PlatformFocusOwner;", "Landroidx/compose/ui/platform/ViewRootForTest;", "Landroidx/compose/ui/input/pointer/MatrixPositionCalculator;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroidx/compose/ui/node/OutOfFrameExecutor;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "Landroid/view/ViewTreeObserver$OnScrollChangedListener;", "Landroid/view/ViewTreeObserver$OnTouchModeChangeListener;", "Landroidx/compose/ui/focus/FocusListener;", "context", "Landroid/content/Context;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Landroid/content/Context;Lkotlin/coroutines/CoroutineContext;)V", "lastDownPointerPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "superclassInitComplete", "", "primaryDirectionalMotionAxisOverride", "Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "getPrimaryDirectionalMotionAxisOverride-dqNNBbU$ui$annotations", "()V", "getPrimaryDirectionalMotionAxisOverride-dqNNBbU$ui", "()Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;", "setPrimaryDirectionalMotionAxisOverride-r2epLt8$ui", "(Landroidx/compose/ui/input/indirect/IndirectPointerEventPrimaryDirectionalMotionAxis;)V", "sharedDrawScope", "Landroidx/compose/ui/node/LayoutNodeDrawScope;", "getSharedDrawScope", "()Landroidx/compose/ui/node/LayoutNodeDrawScope;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "frameEndScheduler", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;", "getFrameEndScheduler$ui", "()Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;", "setFrameEndScheduler$ui", "(Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$FrameEndScheduler;)V", "lifecycleRetainedValuesStoreOwnerEntry", "Landroidx/compose/ui/platform/LifecycleRetainedValuesStoreOwner$RetainedValuesStoreEntry;", "value", "Landroidx/compose/runtime/retain/RetainedValuesStore;", "retainedValuesStore", "getRetainedValuesStore", "()Landroidx/compose/runtime/retain/RetainedValuesStore;", "outOfFrameQueue", "Lkotlin/collections/ArrayDeque;", "Lkotlin/Function0;", "", "outOfFrameRunnable", "Ljava/lang/Runnable;", "<set-?>", "Landroidx/compose/ui/unit/Density;", "density", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "density$delegate", "Landroidx/compose/runtime/MutableState;", "frameRateCategoryView", "isArrEnabled", "isArrEnabled$ui", "()Z", "rootSemanticsNode", "Landroidx/compose/ui/semantics/EmptySemanticsModifier;", "focusOwner", "Landroidx/compose/ui/focus/FocusOwner;", "getFocusOwner", "()Landroidx/compose/ui/focus/FocusOwner;", "getImportantForAutofill", "", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "setCoroutineContext", "(Lkotlin/coroutines/CoroutineContext;)V", "dragAndDropManager", "Landroidx/compose/ui/draganddrop/AndroidDragAndDropManager;", "getDragAndDropManager", "()Landroidx/compose/ui/draganddrop/AndroidDragAndDropManager;", "_windowInfo", "Landroidx/compose/ui/platform/LazyWindowInfo;", "windowInfo", "Landroidx/compose/ui/platform/WindowInfo;", "getWindowInfo", "()Landroidx/compose/ui/platform/WindowInfo;", "processingRequestFocusForNextNonChildView", "moveFocusInChildrenCurrent", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "moveFocusInChildrenCurrent-3ESFkO8", "(I)Z", "moveFocusInChildrenViewFocusFix", "moveFocusInChildrenViewFocusFix-3ESFkO8", "moveFocusInChildrenBypassUnfocusableComposeView", "moveFocusInChildrenBypassUnfocusableComposeView-3ESFkO8", "moveFocusInChildren", "moveFocusInChildren-3ESFkO8", "findNextViewInEmbeddedView", "findNextViewInEmbeddedView-3ESFkO8", "(I)Landroid/view/View;", "getEmbeddedViewFocusRect", "Landroidx/compose/ui/geometry/Rect;", "focusTargetAvailable", "findNextNonChildView", "direction", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "insetsListener", "Landroidx/compose/ui/layout/InsetsListener;", "getInsetsListener", "()Landroidx/compose/ui/layout/InsetsListener;", "root", "Landroidx/compose/ui/node/LayoutNode;", "getRoot$annotations", "getRoot", "()Landroidx/compose/ui/node/LayoutNode;", "layoutNodes", "Landroidx/collection/MutableIntObjectMap;", "getLayoutNodes", "()Landroidx/collection/MutableIntObjectMap;", "rectManager", "Landroidx/compose/ui/spatial/RectManager;", "getRectManager", "()Landroidx/compose/ui/spatial/RectManager;", "rootForTest", "Landroidx/compose/ui/node/RootForTest;", "getRootForTest", "()Landroidx/compose/ui/node/RootForTest;", "uncaughtExceptionHandler", "Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;", "getUncaughtExceptionHandler$ui", "()Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;", "setUncaughtExceptionHandler$ui", "(Landroidx/compose/ui/node/RootForTest$UncaughtExceptionHandler;)V", "semanticsOwner", "Landroidx/compose/ui/semantics/SemanticsOwner;", "getSemanticsOwner", "()Landroidx/compose/ui/semantics/SemanticsOwner;", "composeAccessibilityDelegate", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "contentCaptureManager", "Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "getContentCaptureManager$ui", "()Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "setContentCaptureManager$ui", "(Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;)V", "accessibilityManager", "Landroidx/compose/ui/platform/AndroidAccessibilityManager;", "getAccessibilityManager", "()Landroidx/compose/ui/platform/AndroidAccessibilityManager;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "getGraphicsContext", "()Landroidx/compose/ui/graphics/GraphicsContext;", "autofillTree", "Landroidx/compose/ui/autofill/AutofillTree;", "getAutofillTree", "()Landroidx/compose/ui/autofill/AutofillTree;", "dirtyLayers", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/node/OwnedLayer;", "postponedDirtyLayers", "isDrawingContent", "isPendingInteropViewLayoutChangeDispatch", "motionEventAdapter", "Landroidx/compose/ui/input/pointer/MotionEventAdapter;", "pointerInputEventProcessor", "Landroidx/compose/ui/input/pointer/PointerInputEventProcessor;", "Landroid/content/res/Configuration;", "configuration", "getConfiguration", "()Landroid/content/res/Configuration;", "setConfiguration", "(Landroid/content/res/Configuration;)V", "configuration$delegate", "_autofill", "Landroidx/compose/ui/autofill/AndroidAutofill;", "_autofillManager", "Landroidx/compose/ui/autofill/AndroidAutofillManager;", "get_autofillManager$ui", "()Landroidx/compose/ui/autofill/AndroidAutofillManager;", "autofill", "Landroidx/compose/ui/autofill/Autofill;", "getAutofill", "()Landroidx/compose/ui/autofill/Autofill;", "autofillManager", "Landroidx/compose/ui/autofill/AutofillManager;", "getAutofillManager", "()Landroidx/compose/ui/autofill/AutofillManager;", "observationClearRequested", "clipboardManager", "Landroidx/compose/ui/platform/AndroidClipboardManager;", "getClipboardManager", "()Landroidx/compose/ui/platform/AndroidClipboardManager;", "clipboard", "Landroidx/compose/ui/platform/AndroidClipboard;", "getClipboard", "()Landroidx/compose/ui/platform/AndroidClipboard;", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "showLayoutBounds", "getShowLayoutBounds$annotations", "getShowLayoutBounds", "setShowLayoutBounds", "(Z)V", "_androidViewsHandler", "Landroidx/compose/ui/platform/AndroidViewsHandler;", "androidViewsHandler", "getAndroidViewsHandler$ui", "()Landroidx/compose/ui/platform/AndroidViewsHandler;", "viewLayersContainer", "Landroidx/compose/ui/platform/DrawChildContainer;", "onMeasureConstraints", "Landroidx/compose/ui/unit/Constraints;", "wasMeasuredWithMultipleConstraints", "measureAndLayoutDelegate", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate;", "measureIteration", "", "getMeasureIteration", "()J", "hasPendingMeasureOrLayout", "getHasPendingMeasureOrLayout", "globalPosition", "Landroidx/compose/ui/unit/IntOffset;", "tmpPositionArray", "", "tmpMatrix", "Landroidx/compose/ui/graphics/Matrix;", "[F", "viewToWindowMatrix", "windowToViewMatrix", "lastMatrixRecalculationAnimationTime", "getLastMatrixRecalculationAnimationTime$ui$annotations", "getLastMatrixRecalculationAnimationTime$ui", "setLastMatrixRecalculationAnimationTime$ui", "(J)V", "forceUseMatrixCache", "windowPosition", "isRenderNodeCompatible", "Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "_viewTreeOwners", "get_viewTreeOwners", "()Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "set_viewTreeOwners", "(Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;)V", "_viewTreeOwners$delegate", "viewTreeOwners", "getViewTreeOwners", "viewTreeOwners$delegate", "Landroidx/compose/runtime/State;", "onViewTreeOwnersAvailable", "Lkotlin/Function1;", "legacyTextInputServiceAndroid", "Landroidx/compose/ui/text/input/TextInputServiceAndroid;", "textInputService", "Landroidx/compose/ui/text/input/TextInputService;", "getTextInputService$annotations", "getTextInputService", "()Landroidx/compose/ui/text/input/TextInputService;", "textInputSessionMutex", "Landroidx/compose/ui/SessionMutex;", "Landroidx/compose/ui/platform/AndroidPlatformTextInputSession;", "Ljava/util/concurrent/atomic/AtomicReference;", "softwareKeyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "getSoftwareKeyboardController", "()Landroidx/compose/ui/platform/SoftwareKeyboardController;", "placementScope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "getPlacementScope", "()Landroidx/compose/ui/layout/Placeable$PlacementScope;", "textInputSession", "", "session", "Lkotlin/Function2;", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fontLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "getFontLoader$annotations", "getFontLoader", "()Landroidx/compose/ui/text/font/Font$ResourceLoader;", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "getFontFamilyResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "setFontFamilyResolver", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;)V", "fontFamilyResolver$delegate", "fontWeightAdjustmentCompat", "getFontWeightAdjustmentCompat", "(Landroid/content/res/Configuration;)I", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "layoutDirection$delegate", "hapticFeedBack", "Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "getHapticFeedBack", "()Landroidx/compose/ui/hapticfeedback/HapticFeedback;", "_inputModeManager", "Landroidx/compose/ui/input/InputModeManagerImpl;", "inputModeManager", "Landroidx/compose/ui/input/InputModeManager;", "getInputModeManager", "()Landroidx/compose/ui/input/InputModeManager;", "modifierLocalManager", "Landroidx/compose/ui/modifier/ModifierLocalManager;", "getModifierLocalManager", "()Landroidx/compose/ui/modifier/ModifierLocalManager;", "textToolbar", "Landroidx/compose/ui/platform/TextToolbar;", "getTextToolbar", "()Landroidx/compose/ui/platform/TextToolbar;", "previousMotionEvent", "Landroid/view/MotionEvent;", "relayoutTime", "layerCache", "Landroidx/compose/ui/platform/WeakCache;", "endApplyChangesListeners", "currentFrameRate", "", "currentFrameRateCategory", "resendMotionEventRunnable", "androidx/compose/ui/platform/AndroidComposeView$resendMotionEventRunnable$1", "Landroidx/compose/ui/platform/AndroidComposeView$resendMotionEventRunnable$1;", "sendHoverExitEvent", "hoverExitReceived", "indirectPointerNavigationGestureDetector", "Landroidx/compose/ui/platform/IndirectPointerNavigationGestureDetector;", "resendMotionEventOnLayout", "matrixToWindow", "Landroidx/compose/ui/platform/CalculateMatrixToWindow;", "keyboardModifiersRequireUpdate", "getFocusedRect", "rect", "Landroid/graphics/Rect;", "addFocusables", "views", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "focusableMode", "dispatchProvideStructure", "structure", "Landroid/view/ViewStructure;", "scrollCapture", "Landroidx/compose/ui/scrollcapture/ScrollCapture;", "scrollCaptureInProgress", "getScrollCaptureInProgress$ui", "onScrollCaptureSearch", "localVisibleRect", "windowOffset", "Landroid/graphics/Point;", "targets", "Ljava/util/function/Consumer;", "Landroid/view/ScrollCaptureTarget;", "onResume", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "focusSearch", "focused", "requestFocusCurrent", "previouslyFocusedRect", "requestFocusViewFocusFix", "requestFocusBypassUnfocusableComposeView", "requestFocus", "requestOwnerFocus", "requestOwnerFocus-7o62pno", "clearOwnerFocus", "onFocusChanged", "gainFocus", "previous", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "current", "onWindowFocusChanged", "hasWindowFocus", "sendKeyEvent", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "sendKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "sendIndirectPointerEvent", "indirectPointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "dispatchKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "dispatchKeyEventPreIme", "forceAccessibilityForTesting", "enable", "setAccessibilityEventBatchIntervalMillis", "intervalMillis", "onPreAttach", "node", "onPostAttach", "onDetach", "requestAutofill", "requestClearInvalidObservations", "onEndApplyChanges", "registerOnEndApplyChangesListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "startDrag", "transferData", "Landroidx/compose/ui/draganddrop/DragAndDropTransferData;", "decorationSize", "Landroidx/compose/ui/geometry/Size;", "drawDragDecoration", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "startDrag-12SF9DM", "(Landroidx/compose/ui/draganddrop/DragAndDropTransferData;JLkotlin/jvm/functions/Function1;)Z", "clearChildInvalidObservations", "viewGroup", "addExtraDataToAccessibilityNodeInfoHelper", "virtualViewId", "info", "Landroid/view/accessibility/AccessibilityNodeInfo;", "extraDataKey", "", "addView", "child", "index", "width", "height", "params", "Landroid/view/ViewGroup$LayoutParams;", "addAndroidView", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "layoutNode", "removeAndroidView", "drawAndroidView", "canvas", "Landroid/graphics/Canvas;", "scheduleMeasureAndLayout", "nodeToRemeasure", "childSizeCanAffectParentSize", "measureAndLayout", "sendPointerUpdate", "constraints", "measureAndLayout-0kLqBqw", "(Landroidx/compose/ui/node/LayoutNode;J)V", "dispatchPendingInteropLayoutCallbacks", "forceMeasureTheSubtree", "affectsLookahead", "onRequestMeasure", "forceRequest", "onRequestRelayout", "requestOnPositionedCallback", "measureAndLayoutForTest", "setUncaughtExceptionHandler", "handler", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "component1", "Lkotlin/ULong;", "component1-VKZWuLQ", "(J)I", "component2", "component2-VKZWuLQ", "pack", CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, "b", "pack-ZIaKswc", "(II)J", "convertMeasureSpec", "measureSpec", "convertMeasureSpec-I7RO_PI", "(I)J", "onLayout", "changed", CmcdHeadersFactory.STREAM_TYPE_LIVE, "t", "r", "_rootView", "updatePositionCacheAndDispatch", "onDraw", "createLayer", "drawBlock", "Landroidx/compose/ui/graphics/Canvas;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "parentLayer", "invalidateParentLayer", "explicitLayer", "recycle", "layer", "recycle$ui", "onSemanticsChange", "onLayoutChange", "onLayoutNodeDeactivated", "onPreLayoutNodeReused", "oldSemanticsId", "onPostLayoutNodeReused", "onInteropViewLayoutChange", "Landroidx/compose/ui/viewinterop/InteropView;", "registerOnLayoutCompletedListener", "Landroidx/compose/ui/node/Owner$OnLayoutCompletedListener;", "dispatchDraw", "notifyLayerIsDirty", "isDirty", "notifyLayerIsDirty$ui", "setOnViewTreeOwnersAvailable", "callback", "boundsUpdatesContentCaptureEventLoop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "boundsUpdatesAccessibilityEventLoop", "invalidateLayoutNodeMeasurement", "invalidateLayers", "invalidateDescendants", "onAttachedToWindow", "installLocalRetainedValuesStore", "lifecycleOwner", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "onDetachedFromWindow", "onProvideAutofillVirtualStructure", "flags", "values", "Landroid/util/SparseArray;", "Landroid/view/autofill/AutofillValue;", "onCreateVirtualViewTranslationRequests", "virtualIds", "", "supportedFormats", "requestsCollector", "Landroid/view/translation/ViewTranslationRequest;", "onVirtualViewTranslationResponses", "response", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "dispatchGenericMotionEvent", "motionEvent", "handleIndirectPointerEvent", "dispatchTouchEvent", "handleRotaryEvent", "handleMotionEvent", "Landroidx/compose/ui/input/pointer/ProcessResult;", "handleMotionEvent-8iAsVTc", "(Landroid/view/MotionEvent;)I", "hasChangedDevices", "lastEvent", "isDevicePressEvent", "sendMotionEvent", "sendMotionEvent-8iAsVTc", "sendSimulatedEvent", "action", "eventTime", "forceHover", "canScrollHorizontally", "canScrollVertically", "isInBounds", "localToScreen", "localPosition", "localToScreen-MK-Hz9U", "(J)J", "localTransform", "localToScreen-58bKbWc", "([F)V", "screenToLocal", "positionOnScreen", "screenToLocal-MK-Hz9U", "recalculateWindowPosition", "recalculateWindowViewTransforms", "updateWindowMetrics", "onCheckIsTextEditor", "onCreateInputConnection", "Landroid/view/inputmethod/InputConnection;", "outAttrs", "Landroid/view/inputmethod/EditorInfo;", "calculateLocalPosition", "positionInWindow", "calculateLocalPosition-MK-Hz9U", "calculatePositionInWindow", "calculatePositionInWindow-MK-Hz9U", "onConfigurationChanged", "newConfig", "dispatchConfigurationChangeIfNeeded", "updateConfiguration", "onRtlPropertiesChanged", "autofillSupported", "dispatchHoverEvent", "isBadMotionEvent", "isPositionChanged", "findViewByAccessibilityIdRootedAtCurrentView", "accessibilityId", "currentView", "onResolvePointerIcon", "Landroid/view/PointerIcon;", "pointerIndex", "pointerIconService", "Landroidx/compose/ui/input/pointer/PointerIconService;", "getPointerIconService", "()Landroidx/compose/ui/input/pointer/PointerIconService;", "findViewByAccessibilityIdTraversal", "isLifecycleInResumedState", "shouldDelayChildPressedState", "sensitiveComponentCount", "incrementSensitiveComponentCount", "decrementSensitiveComponentCount", "keepScreenOnCount", "incrementKeepScreenOnCount", "decrementKeepScreenOnCount", "outOfFrameExecutor", "getOutOfFrameExecutor", "()Landroidx/compose/ui/platform/AndroidComposeView;", "schedule", "block", "voteFrameRate", "frameRate", "dispatchOnScrollChanged", "delta", "dispatchOnScrollChanged-k-4lQ0M", "onGlobalLayout", "onScrollChanged", "onTouchModeChanged", "isInTouchMode", "Companion", "ViewTreeOwners", "RootModifierNode", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AndroidComposeView extends ViewGroup implements Owner, PlatformFocusOwner, ViewRootForTest, MatrixPositionCalculator, DefaultLifecycleObserver, OutOfFrameExecutor, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, ViewTreeObserver.OnTouchModeChangeListener, FocusListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static Method addChangeCallbackMethod;
    private static final MutableObjectList<AndroidComposeView> composeViews;
    private static Method dispatchOnScrollChangedMethod;
    private static Method getBooleanMethod;
    private static Runnable systemPropertiesChangedRunnable;
    private static Class<?> systemPropertiesClass;
    private AndroidViewsHandler _androidViewsHandler;
    private final AndroidAutofill _autofill;
    private final AndroidAutofillManager _autofillManager;
    private final InputModeManagerImpl _inputModeManager;
    private View _rootView;

    /* JADX INFO: renamed from: _viewTreeOwners$delegate, reason: from kotlin metadata */
    private final MutableState _viewTreeOwners;
    private final LazyWindowInfo _windowInfo;
    private final AndroidAccessibilityManager accessibilityManager;
    private final AutofillTree autofillTree;
    private final CanvasHolder canvasHolder;
    private final AndroidClipboard clipboard;
    private final AndroidClipboardManager clipboardManager;
    private final AndroidComposeViewAccessibilityDelegateCompat composeAccessibilityDelegate;

    /* JADX INFO: renamed from: configuration$delegate, reason: from kotlin metadata */
    private final MutableState configuration;
    private AndroidContentCaptureManager contentCaptureManager;
    private CoroutineContext coroutineContext;
    private float currentFrameRate;
    private float currentFrameRateCategory;

    /* JADX INFO: renamed from: density$delegate, reason: from kotlin metadata */
    private final MutableState density;
    private final MutableObjectList<OwnedLayer> dirtyLayers;
    private final AndroidDragAndDropManager dragAndDropManager;
    private final MutableObjectList<Function0<Unit>> endApplyChangesListeners;
    private final FocusOwner focusOwner;

    /* JADX INFO: renamed from: fontFamilyResolver$delegate, reason: from kotlin metadata */
    private final MutableState fontFamilyResolver;
    private final Font.ResourceLoader fontLoader;
    private boolean forceUseMatrixCache;
    private LifecycleRetainedValuesStoreOwner.FrameEndScheduler frameEndScheduler;
    private View frameRateCategoryView;
    private long globalPosition;
    private final GraphicsContext graphicsContext;
    private final HapticFeedback hapticFeedBack;
    private boolean hoverExitReceived;
    private final IndirectPointerNavigationGestureDetector indirectPointerNavigationGestureDetector;
    private final InsetsListener insetsListener;
    private final boolean isArrEnabled;
    private boolean isDrawingContent;
    private boolean isPendingInteropViewLayoutChangeDispatch;
    private boolean isRenderNodeCompatible;
    private int keepScreenOnCount;
    private boolean keyboardModifiersRequireUpdate;
    private long lastDownPointerPosition;
    private long lastMatrixRecalculationAnimationTime;
    private final WeakCache<OwnedLayer> layerCache;

    /* JADX INFO: renamed from: layoutDirection$delegate, reason: from kotlin metadata */
    private final MutableState layoutDirection;
    private final MutableIntObjectMap<LayoutNode> layoutNodes;
    private final TextInputServiceAndroid legacyTextInputServiceAndroid;
    private LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry lifecycleRetainedValuesStoreOwnerEntry;
    private final CalculateMatrixToWindow matrixToWindow;
    private final MeasureAndLayoutDelegate measureAndLayoutDelegate;
    private final ModifierLocalManager modifierLocalManager;
    private final MotionEventAdapter motionEventAdapter;
    private boolean observationClearRequested;
    private Constraints onMeasureConstraints;
    private Function1<? super ViewTreeOwners, Unit> onViewTreeOwnersAvailable;
    private final ArrayDeque<Function0<Unit>> outOfFrameQueue;
    private final Runnable outOfFrameRunnable;
    private final PointerIconService pointerIconService;
    private final PointerInputEventProcessor pointerInputEventProcessor;
    private MutableObjectList<OwnedLayer> postponedDirtyLayers;
    private MotionEvent previousMotionEvent;
    private IndirectPointerEventPrimaryDirectionalMotionAxis primaryDirectionalMotionAxisOverride;
    private boolean processingRequestFocusForNextNonChildView;
    private final RectManager rectManager;
    private long relayoutTime;
    private final Function0<Unit> resendMotionEventOnLayout;
    private final AndroidComposeView$resendMotionEventRunnable$1 resendMotionEventRunnable;
    private RetainedValuesStore retainedValuesStore;
    private final LayoutNode root;
    private final RootForTest rootForTest;
    private final EmptySemanticsModifier rootSemanticsNode;
    private final ScrollCapture scrollCapture;
    private final SemanticsOwner semanticsOwner;
    private final Runnable sendHoverExitEvent;
    private int sensitiveComponentCount;
    private final LayoutNodeDrawScope sharedDrawScope;
    private boolean showLayoutBounds;
    private final OwnerSnapshotObserver snapshotObserver;
    private final SoftwareKeyboardController softwareKeyboardController;
    private boolean superclassInitComplete;
    private final TextInputService textInputService;
    private final AtomicReference<SessionMutex.Session<AndroidPlatformTextInputSession>> textInputSessionMutex;
    private final TextToolbar textToolbar;
    private final float[] tmpMatrix;
    private final int[] tmpPositionArray;
    private RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler;
    private final ViewConfiguration viewConfiguration;
    private DrawChildContainer viewLayersContainer;
    private final float[] viewToWindowMatrix;

    /* JADX INFO: renamed from: viewTreeOwners$delegate, reason: from kotlin metadata */
    private final State viewTreeOwners;
    private boolean wasMeasuredWithMultipleConstraints;
    private long windowPosition;
    private final float[] windowToViewMatrix;

    /* JADX INFO: renamed from: androidx.compose.ui.platform.AndroidComposeView$textInputSession$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AndroidComposeView.android.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.platform.AndroidComposeView", f = "AndroidComposeView.android.kt", i = {}, l = {776}, m = "textInputSession", n = {}, s = {}, v = 1)
    static final class C03731 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C03731(Continuation<? super C03731> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AndroidComposeView.this.textInputSession(null, this);
        }
    }

    @Deprecated(message = "fontLoader is deprecated, use fontFamilyResolver", replaceWith = @ReplaceWith(expression = "fontFamilyResolver", imports = {}))
    public static /* synthetic */ void getFontLoader$annotations() {
    }

    public static /* synthetic */ void getLastMatrixRecalculationAnimationTime$ui$annotations() {
    }

    /* JADX INFO: renamed from: getPrimaryDirectionalMotionAxisOverride-dqNNBbU$ui$annotations, reason: not valid java name */
    public static /* synthetic */ void m6971getPrimaryDirectionalMotionAxisOverridedqNNBbU$ui$annotations() {
    }

    public static /* synthetic */ void getRoot$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    @Deprecated(message = "Use PlatformTextInputModifierNode instead.")
    public static /* synthetic */ void getTextInputService$annotations() {
    }

    /* JADX WARN: Type inference failed for: r6v70, types: [androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1] */
    public AndroidComposeView(Context context, CoroutineContext coroutineContext) {
        AndroidAutofillManager androidAutofillManager;
        CalculateMatrixToWindow calculateMatrixToWindowApi29;
        super(context);
        this.lastDownPointerPosition = Offset.INSTANCE.m4867getUnspecifiedF1C5BW0();
        int i = 1;
        this.superclassInitComplete = true;
        this.sharedDrawScope = new LayoutNodeDrawScope(0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        this.retainedValuesStore = ForgetfulRetainedValuesStore.INSTANCE;
        this.outOfFrameQueue = new ArrayDeque<>();
        this.outOfFrameRunnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeView.outOfFrameRunnable$lambda$0(this.f$0);
            }
        };
        this.density = SnapshotStateKt.mutableStateOf(AndroidDensity_androidKt.Density(context), SnapshotStateKt.referentialEqualityPolicy());
        boolean z = false;
        this.isArrEnabled = ComposeUiFlags.isAdaptiveRefreshRateEnabled && Build.VERSION.SDK_INT >= 35;
        this.rootSemanticsNode = new EmptySemanticsModifier();
        this.focusOwner = new FocusOwnerImpl(this, this);
        this.coroutineContext = coroutineContext;
        this.dragAndDropManager = new AndroidDragAndDropManager(new AndroidComposeView$dragAndDropManager$1(this));
        this._windowInfo = new LazyWindowInfo();
        this.canvasHolder = new CanvasHolder();
        this.viewConfiguration = new AndroidViewConfiguration(android.view.ViewConfiguration.get(context));
        this.insetsListener = new InsetsListener(this);
        LayoutNode layoutNode = new LayoutNode(z, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        layoutNode.setMeasurePolicy(RootMeasurePolicy.INSTANCE);
        layoutNode.setDensity(getDensity());
        layoutNode.setViewConfiguration(getViewConfiguration());
        layoutNode.setModifier(new ModifierNodeElement<RootModifierNode>() { // from class: androidx.compose.ui.platform.AndroidComposeView$root$1$1
            @Override // androidx.compose.ui.node.ModifierNodeElement
            /* JADX INFO: renamed from: create */
            public AndroidComposeView.RootModifierNode getNode() {
                return this.this$0.new RootModifierNode();
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public void update(AndroidComposeView.RootModifierNode node) {
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
                $this$inspectableProperties.setName("rootModifier");
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public int hashCode() {
                return this.this$0.hashCode();
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public boolean equals(Object other) {
                return other == this;
            }
        }.then(getFocusOwner().getModifier()).then(getDragAndDropManager().getModifier()));
        this.root = layoutNode;
        this.layoutNodes = IntObjectMapKt.mutableIntObjectMapOf();
        this.rectManager = new RectManager(getLayoutNodes());
        this.rootForTest = this;
        this.semanticsOwner = new SemanticsOwner(getRoot(), this.rootSemanticsNode, getLayoutNodes());
        this.composeAccessibilityDelegate = new AndroidComposeViewAccessibilityDelegateCompat(this);
        this.contentCaptureManager = new AndroidContentCaptureManager(this, new AndroidComposeView$contentCaptureManager$1(this));
        this.accessibilityManager = new AndroidAccessibilityManager(context);
        this.graphicsContext = AndroidGraphicsContext_androidKt.GraphicsContext(this);
        this.autofillTree = new AutofillTree();
        this.dirtyLayers = new MutableObjectList<>(0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        this.motionEventAdapter = new MotionEventAdapter();
        this.pointerInputEventProcessor = new PointerInputEventProcessor(getRoot());
        this.configuration = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new Configuration(context.getResources().getConfiguration()), null, 2, null);
        this._autofill = autofillSupported() ? new AndroidAutofill(this, getAutofillTree()) : null;
        if (autofillSupported()) {
            AutofillManager autofillManager = (AutofillManager) context.getSystemService(AutofillManager.class);
            if (autofillManager != null) {
                androidAutofillManager = new AndroidAutofillManager(new PlatformAutofillManagerImpl(autofillManager), getSemanticsOwner(), this, getRectManager(), context.getPackageName());
            } else {
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Autofill service could not be located.");
                throw new KotlinNothingValueException();
            }
        } else {
            androidAutofillManager = null;
        }
        this._autofillManager = androidAutofillManager;
        this.clipboardManager = new AndroidClipboardManager(context);
        this.clipboard = new AndroidClipboard(getClipboardManager());
        this.snapshotObserver = new OwnerSnapshotObserver(new AndroidComposeView$snapshotObserver$1(this));
        this.measureAndLayoutDelegate = new MeasureAndLayoutDelegate(getRoot());
        this.globalPosition = IntOffset.m8027constructorimpl((((long) Integer.MAX_VALUE) << 32) | (((long) Integer.MAX_VALUE) & 4294967295L));
        this.tmpPositionArray = new int[]{0, 0};
        this.tmpMatrix = Matrix.m5341constructorimpl$default(null, 1, null);
        this.viewToWindowMatrix = Matrix.m5341constructorimpl$default(null, 1, null);
        this.windowToViewMatrix = Matrix.m5341constructorimpl$default(null, 1, null);
        this.lastMatrixRecalculationAnimationTime = -1L;
        this.windowPosition = Offset.INSTANCE.m4866getInfiniteF1C5BW0();
        this.isRenderNodeCompatible = true;
        this._viewTreeOwners = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.viewTreeOwners = SnapshotStateKt.derivedStateOf(new Function0<ViewTreeOwners>() { // from class: androidx.compose.ui.platform.AndroidComposeView$viewTreeOwners$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AndroidComposeView.ViewTreeOwners invoke() {
                return this.this$0.get_viewTreeOwners();
            }
        });
        this.legacyTextInputServiceAndroid = new TextInputServiceAndroid(getView(), this);
        this.textInputService = new TextInputService(AndroidComposeView_androidKt.getPlatformTextInputServiceInterceptor().invoke(this.legacyTextInputServiceAndroid));
        this.textInputSessionMutex = SessionMutex.m4585constructorimpl();
        this.softwareKeyboardController = new DelegatingSoftwareKeyboardController(getTextInputService());
        this.fontLoader = new AndroidFontResourceLoader(context);
        this.fontFamilyResolver = SnapshotStateKt.mutableStateOf(FontFamilyResolver_androidKt.createFontFamilyResolver(context), SnapshotStateKt.referentialEqualityPolicy());
        LayoutDirection layoutDirection = FocusInteropUtils_androidKt.toLayoutDirection(context.getResources().getConfiguration().getLayoutDirection());
        this.layoutDirection = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(layoutDirection == null ? LayoutDirection.Ltr : layoutDirection, null, 2, null);
        this.hapticFeedBack = new PlatformHapticFeedback(this);
        this._inputModeManager = new InputModeManagerImpl(isInTouchMode() ? InputMode.INSTANCE.m5908getTouchaOaMEAU() : InputMode.INSTANCE.m5907getKeyboardaOaMEAU(), new Function1<InputMode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$_inputModeManager$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(InputMode inputMode) {
                return m6981invokeiuPiT84(inputMode.getValue());
            }

            /* JADX INFO: renamed from: invoke-iuPiT84, reason: not valid java name */
            public final Boolean m6981invokeiuPiT84(int i2) {
                boolean zRequestFocusFromTouch;
                if (InputMode.m5903equalsimpl0(i2, InputMode.INSTANCE.m5908getTouchaOaMEAU())) {
                    zRequestFocusFromTouch = this.this$0.isInTouchMode();
                } else if (InputMode.m5903equalsimpl0(i2, InputMode.INSTANCE.m5907getKeyboardaOaMEAU())) {
                    zRequestFocusFromTouch = this.this$0.isInTouchMode() ? this.this$0.requestFocusFromTouch() : true;
                } else {
                    zRequestFocusFromTouch = false;
                }
                return Boolean.valueOf(zRequestFocusFromTouch);
            }
        }, 0 == true ? 1 : 0);
        this.modifierLocalManager = new ModifierLocalManager(this);
        this.textToolbar = new AndroidTextToolbar(this);
        this.layerCache = new WeakCache<>();
        this.endApplyChangesListeners = new MutableObjectList<>(0 == true ? 1 : 0, i, 0 == true ? 1 : 0);
        this.resendMotionEventRunnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                int newAction;
                this.this$0.removeCallbacks(this);
                MotionEvent lastMotionEvent = this.this$0.previousMotionEvent;
                if (lastMotionEvent != null) {
                    boolean resend = false;
                    boolean wasMouseEvent = lastMotionEvent.getToolType(0) == 3;
                    int action = lastMotionEvent.getActionMasked();
                    if (wasMouseEvent) {
                        if (action != 10 && action != 1) {
                            resend = true;
                        }
                    } else if (action != 1) {
                        resend = true;
                    }
                    if (resend) {
                        switch (action) {
                            case 7:
                            case 9:
                                newAction = 7;
                                break;
                            case 8:
                            default:
                                newAction = 2;
                                break;
                        }
                        this.this$0.sendSimulatedEvent(lastMotionEvent, newAction, this.this$0.relayoutTime, false);
                    }
                }
            }
        };
        this.sendHoverExitEvent = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeView.sendHoverExitEvent$lambda$0(this.f$0);
            }
        };
        this.indirectPointerNavigationGestureDetector = new IndirectPointerNavigationGestureDetector(context, new Function1<FocusDirection, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$indirectPointerNavigationGestureDetector$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FocusDirection focusDirection) {
                m6983invoke3ESFkO8(focusDirection.getValue());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke-3ESFkO8, reason: not valid java name */
            public final void m6983invoke3ESFkO8(int i2) {
                this.this$0.getFocusOwner().mo4747moveFocusaToIllA(i2, false);
            }
        });
        this.resendMotionEventOnLayout = new Function0<Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventOnLayout$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MotionEvent lastEvent = this.this$0.previousMotionEvent;
                if (lastEvent != null) {
                    switch (lastEvent.getActionMasked()) {
                        case 7:
                        case 9:
                            this.this$0.relayoutTime = SystemClock.uptimeMillis();
                            this.this$0.post(this.this$0.resendMotionEventRunnable);
                            break;
                    }
                }
            }
        };
        if (Build.VERSION.SDK_INT < 29) {
            calculateMatrixToWindowApi29 = new CalculateMatrixToWindowApi21(this.tmpMatrix, 0 == true ? 1 : 0);
        } else {
            calculateMatrixToWindowApi29 = new CalculateMatrixToWindowApi29();
        }
        this.matrixToWindow = calculateMatrixToWindowApi29;
        addOnAttachStateChangeListener(this.contentCaptureManager);
        setWillNotDraw(false);
        setFocusable(true);
        if (Build.VERSION.SDK_INT >= 26) {
            AndroidComposeViewVerificationHelperMethodsO.INSTANCE.focusable(this, 1, false);
        }
        setFocusableInTouchMode(true);
        setClipChildren(false);
        ViewCompat.setAccessibilityDelegate(this, this.composeAccessibilityDelegate);
        Function1<ViewRootForTest, Unit> onViewCreatedCallback = ViewRootForTest.INSTANCE.getOnViewCreatedCallback();
        if (onViewCreatedCallback != null) {
            onViewCreatedCallback.invoke(this);
        }
        setOnDragListener(getDragAndDropManager());
        getRoot().attach$ui(this);
        if (Build.VERSION.SDK_INT >= 29) {
            AndroidComposeViewForceDarkModeQ.INSTANCE.disallowForceDark(this);
        }
        if (this.isArrEnabled) {
            View view = new View(context);
            view.setLayoutParams(new ViewGroup.LayoutParams(1, 1));
            view.setTag(R.id.hide_in_inspector_tag, true);
            this.frameRateCategoryView = view;
            View view2 = this.frameRateCategoryView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("frameRateCategoryView");
                view2 = null;
            }
            addView(view2);
        }
        this.scrollCapture = Build.VERSION.SDK_INT >= 31 ? new ScrollCapture() : null;
        this.pointerIconService = new PointerIconService() { // from class: androidx.compose.ui.platform.AndroidComposeView$pointerIconService$1
            private PointerIcon currentMouseCursorIcon = PointerIcon.INSTANCE.getDefault();
            private PointerIcon currentStylusHoverIcon;

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            /* JADX INFO: renamed from: getIcon, reason: from getter */
            public PointerIcon getCurrentMouseCursorIcon() {
                return this.currentMouseCursorIcon;
            }

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            public void setIcon(PointerIcon value) {
                this.currentMouseCursorIcon = value == null ? PointerIcon.INSTANCE.getDefault() : value;
                int i2 = Build.VERSION.SDK_INT;
                AndroidComposeViewVerificationHelperMethodsN.INSTANCE.setPointerIcon(this.this$0, this.currentMouseCursorIcon);
            }

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            /* JADX INFO: renamed from: getStylusHoverIcon, reason: from getter */
            public PointerIcon getCurrentStylusHoverIcon() {
                return this.currentStylusHoverIcon;
            }

            @Override // androidx.compose.ui.input.pointer.PointerIconService
            public void setStylusHoverIcon(PointerIcon value) {
                this.currentStylusHoverIcon = value;
            }
        };
    }

    /* JADX INFO: renamed from: getPrimaryDirectionalMotionAxisOverride-dqNNBbU$ui, reason: not valid java name and from getter */
    public final IndirectPointerEventPrimaryDirectionalMotionAxis getPrimaryDirectionalMotionAxisOverride() {
        return this.primaryDirectionalMotionAxisOverride;
    }

    /* JADX INFO: renamed from: setPrimaryDirectionalMotionAxisOverride-r2epLt8$ui, reason: not valid java name */
    public final void m6980setPrimaryDirectionalMotionAxisOverrider2epLt8$ui(IndirectPointerEventPrimaryDirectionalMotionAxis indirectPointerEventPrimaryDirectionalMotionAxis) {
        this.primaryDirectionalMotionAxisOverride = indirectPointerEventPrimaryDirectionalMotionAxis;
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNodeDrawScope getSharedDrawScope() {
        return this.sharedDrawScope;
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public View getView() {
        return this;
    }

    /* JADX INFO: renamed from: getFrameEndScheduler$ui, reason: from getter */
    public final LifecycleRetainedValuesStoreOwner.FrameEndScheduler getFrameEndScheduler() {
        return this.frameEndScheduler;
    }

    public final void setFrameEndScheduler$ui(LifecycleRetainedValuesStoreOwner.FrameEndScheduler frameEndScheduler) {
        this.frameEndScheduler = frameEndScheduler;
    }

    @Override // androidx.compose.ui.node.Owner
    public RetainedValuesStore getRetainedValuesStore() {
        return this.retainedValuesStore;
    }

    static final void outOfFrameRunnable$lambda$0(AndroidComposeView this$0) {
        Trace.beginSection("AndroidOwner:outOfFrameExecutor");
        while (!this$0.outOfFrameQueue.isEmpty()) {
            try {
                this$0.outOfFrameQueue.removeLast().invoke();
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        Unit unit = Unit.INSTANCE;
        Trace.endSection();
    }

    private void setDensity(Density density) {
        MutableState $this$setValue$iv = this.density;
        $this$setValue$iv.setValue(density);
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public Density getDensity() {
        State $this$getValue$iv = this.density;
        return (Density) $this$getValue$iv.getValue();
    }

    /* JADX INFO: renamed from: isArrEnabled$ui, reason: from getter */
    public final boolean getIsArrEnabled() {
        return this.isArrEnabled;
    }

    @Override // androidx.compose.ui.node.Owner
    public FocusOwner getFocusOwner() {
        return this.focusOwner;
    }

    @Override // android.view.View
    public int getImportantForAutofill() {
        return 1;
    }

    @Override // androidx.compose.ui.node.Owner
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public void setCoroutineContext(CoroutineContext value) {
        boolean z;
        DelegatableNode delegatableNode;
        DelegatableNode delegatableNode2;
        Modifier.Node node;
        Modifier.Node node2;
        this.coroutineContext = value;
        DelegatableNode head = getRoot().getNodes().getHead();
        if (head instanceof SuspendingPointerInputModifierNode) {
            ((SuspendingPointerInputModifierNode) head).resetPointerInputHandler();
        }
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv = head;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(16);
        boolean value$iv$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtree_u2dY_u2dYKmho_u24default$iv.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            boolean diveDeeper$iv$iv = true;
            if (!(branches$iv$iv.getSize() != 0)) {
                return;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv != null && node$iv$iv.getIsAttached()) {
                        if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                            Modifier.Node it$iv = node$iv$iv;
                            MutableVector mutableVector = null;
                            Modifier.Node this_$iv$iv$iv = it$iv;
                            while (this_$iv$iv$iv != null) {
                                if (this_$iv$iv$iv instanceof PointerInputModifierNode) {
                                    PointerInputModifierNode it = (PointerInputModifierNode) this_$iv$iv$iv;
                                    delegatableNode2 = head;
                                    if (it instanceof SuspendingPointerInputModifierNode) {
                                        ((SuspendingPointerInputModifierNode) it).resetPointerInputHandler();
                                    }
                                    node = this_$iv$iv$iv;
                                } else {
                                    delegatableNode2 = head;
                                    if (((this_$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? 1 : 0) == 0 || !(this_$iv$iv$iv instanceof DelegatingNode)) {
                                        node = this_$iv$iv$iv;
                                    } else {
                                        int count$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) this_$iv$iv$iv;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if (((next$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? 1 : 0) != 0) {
                                                count$iv$iv++;
                                                Modifier.Node node3 = this_$iv$iv$iv;
                                                if (count$iv$iv == 1) {
                                                    node2 = next$iv$iv;
                                                    this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                                } else {
                                                    MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                    if (node3 != null) {
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(node3);
                                                        }
                                                        node2 = null;
                                                    } else {
                                                        node2 = node3;
                                                    }
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(next$iv$iv);
                                                    }
                                                    mutableVector = mutableVector2;
                                                    count$iv$iv = count$iv$iv;
                                                }
                                            } else {
                                                this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                                node2 = this_$iv$iv$iv;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            this_$iv$iv$iv = node2;
                                            this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                        }
                                        node = this_$iv$iv$iv;
                                        if (count$iv$iv == 1) {
                                            head = delegatableNode2;
                                            this_$iv$iv$iv = node;
                                        }
                                    }
                                }
                                this_$iv$iv$iv = DelegatableNodeKt.pop(mutableVector);
                                head = delegatableNode2;
                            }
                            delegatableNode = head;
                            z = true;
                            if (1 == 0) {
                                head = delegatableNode;
                                break;
                            }
                        } else {
                            z = diveDeeper$iv$iv;
                            delegatableNode = head;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        diveDeeper$iv$iv = z;
                        head = delegatableNode;
                    }
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            head = head;
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidDragAndDropManager getDragAndDropManager() {
        return this.dragAndDropManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public WindowInfo getWindowInfo() {
        return this._windowInfo;
    }

    /* JADX INFO: renamed from: moveFocusInChildrenCurrent-3ESFkO8, reason: not valid java name */
    private final boolean m6974moveFocusInChildrenCurrent3ESFkO8(int focusDirection) {
        View nextView;
        if (FocusDirection.m4727equalsimpl0(focusDirection, FocusDirection.INSTANCE.m4732getEnterdhqQ8s()) || FocusDirection.m4727equalsimpl0(focusDirection, FocusDirection.INSTANCE.m4733getExitdhqQ8s())) {
            return false;
        }
        Object value$iv = FocusInteropUtils_androidKt.m4739toAndroidFocusDirection3ESFkO8(focusDirection);
        if (value$iv != null) {
            int direction = ((Number) value$iv).intValue();
            Rect embeddedViewFocusRect = getEmbeddedViewFocusRect();
            android.graphics.Rect focusedRect = embeddedViewFocusRect != null ? RectHelper_androidKt.toAndroidRect(embeddedViewFocusRect) : null;
            FocusFinderCompat it = FocusFinderCompat.INSTANCE.getInstance();
            if (focusedRect == null) {
                nextView = it.findNextFocus(this, findFocus(), direction);
            } else {
                nextView = it.findNextFocusFromRect(this, focusedRect, direction);
            }
            if (nextView != null) {
                return FocusInteropUtils_androidKt.requestInteropFocus(nextView, Integer.valueOf(direction), focusedRect);
            }
            return false;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid focus direction");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: renamed from: moveFocusInChildrenViewFocusFix-3ESFkO8, reason: not valid java name */
    private final boolean m6975moveFocusInChildrenViewFocusFix3ESFkO8(int focusDirection) {
        AndroidViewsHandler androidViewsHandler;
        android.graphics.Rect focusedRect;
        if (FocusDirection.m4727equalsimpl0(focusDirection, FocusDirection.INSTANCE.m4732getEnterdhqQ8s()) || FocusDirection.m4727equalsimpl0(focusDirection, FocusDirection.INSTANCE.m4733getExitdhqQ8s()) || !hasFocus() || (androidViewsHandler = this._androidViewsHandler) == null) {
            return false;
        }
        Object value$iv = FocusInteropUtils_androidKt.m4739toAndroidFocusDirection3ESFkO8(focusDirection);
        if (value$iv != null) {
            int direction = ((Number) value$iv).intValue();
            View rootView = getRootView();
            Intrinsics.checkNotNull(rootView, "null cannot be cast to non-null type android.view.ViewGroup");
            ViewGroup root = (ViewGroup) rootView;
            View currentFocus = root.findFocus();
            if (currentFocus == null) {
                throw new IllegalStateException("view hasFocus but root can't find it".toString());
            }
            FocusFinderCompat focusFinder = FocusFinderCompat.INSTANCE.getInstance();
            View nextView = focusFinder.findNextFocus(root, currentFocus, direction);
            if (FocusOwnerImplKt.m4754is1dFocusSearch3ESFkO8(focusDirection) && androidViewsHandler.hasFocus()) {
                focusedRect = null;
            } else {
                Rect embeddedViewFocusRect = getEmbeddedViewFocusRect();
                focusedRect = embeddedViewFocusRect != null ? RectHelper_androidKt.toAndroidRect(embeddedViewFocusRect) : null;
                if (nextView != null && focusedRect != null) {
                    root.offsetDescendantRectToMyCoords(this, focusedRect);
                    root.offsetRectIntoDescendantCoords(nextView, focusedRect);
                }
            }
            if (nextView == null || nextView == currentFocus) {
                return false;
            }
            View focusedChild = androidViewsHandler.getFocusedChild();
            ViewParent nextParent = nextView.getParent();
            while (nextParent != null && nextParent != focusedChild) {
                nextParent = nextParent.getParent();
            }
            if (nextParent == null) {
                return false;
            }
            return FocusInteropUtils_androidKt.requestInteropFocus(nextView, Integer.valueOf(direction), focusedRect);
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid focus direction");
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: renamed from: moveFocusInChildrenBypassUnfocusableComposeView-3ESFkO8, reason: not valid java name */
    private final boolean m6973moveFocusInChildrenBypassUnfocusableComposeView3ESFkO8(int focusDirection) {
        if (FocusDirection.m4727equalsimpl0(focusDirection, FocusDirection.INSTANCE.m4732getEnterdhqQ8s()) || FocusDirection.m4727equalsimpl0(focusDirection, FocusDirection.INSTANCE.m4733getExitdhqQ8s())) {
            return false;
        }
        Object value$iv = FocusInteropUtils_androidKt.m4739toAndroidFocusDirection3ESFkO8(focusDirection);
        if (value$iv != null) {
            int direction = ((Number) value$iv).intValue();
            View nextView = m6970findNextViewInEmbeddedView3ESFkO8(focusDirection);
            if (nextView != null) {
                return FocusInteropUtils_androidKt.requestInteropFocus(nextView, Integer.valueOf(direction), null);
            }
            return false;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid focus direction");
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    /* JADX INFO: renamed from: moveFocusInChildren-3ESFkO8 */
    public boolean mo4793moveFocusInChildren3ESFkO8(int focusDirection) {
        if (ComposeUiFlags.isViewFocusFixEnabled) {
            return m6975moveFocusInChildrenViewFocusFix3ESFkO8(focusDirection);
        }
        if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
            return m6973moveFocusInChildrenBypassUnfocusableComposeView3ESFkO8(focusDirection);
        }
        return m6974moveFocusInChildrenCurrent3ESFkO8(focusDirection);
    }

    /* JADX INFO: renamed from: findNextViewInEmbeddedView-3ESFkO8, reason: not valid java name */
    private final View m6970findNextViewInEmbeddedView3ESFkO8(int focusDirection) {
        View nextView;
        FocusTargetNode activeFocusTargetNode = getFocusOwner().getActiveFocusTargetNode();
        if (activeFocusTargetNode == null) {
            throw new IllegalStateException("findNextViewInEmbeddedView called when owner does not have anything focused.".toString());
        }
        Object value$iv = FocusInteropUtils_androidKt.m4739toAndroidFocusDirection3ESFkO8(focusDirection);
        if (value$iv != null) {
            int direction = ((Number) value$iv).intValue();
            View interopView = DelegatableNodeKt.requireLayoutNode(activeFocusTargetNode).getInteropView();
            View currentlyFocusedView = findFocus();
            if (Build.VERSION.SDK_INT < 26 && ComposeUiFlags.isPre26FocusFinderFixEnabled) {
                FocusFinderCompat companion = FocusFinderCompat.INSTANCE.getInstance();
                View rootView = getRootView();
                Intrinsics.checkNotNull(rootView, "null cannot be cast to non-null type android.view.ViewGroup");
                nextView = companion.findNextFocus((ViewGroup) rootView, currentlyFocusedView, direction);
            } else {
                FocusFinder focusFinder = FocusFinder.getInstance();
                View rootView2 = getRootView();
                Intrinsics.checkNotNull(rootView2, "null cannot be cast to non-null type android.view.ViewGroup");
                nextView = focusFinder.findNextFocus((ViewGroup) rootView2, currentlyFocusedView, direction);
            }
            if (nextView != null) {
                boolean z = false;
                if (interopView != null && AndroidComposeView_androidKt.containsDescendant(interopView, nextView)) {
                    z = true;
                }
                if (z) {
                    return nextView;
                }
                return null;
            }
            return null;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid focus direction");
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    public Rect getEmbeddedViewFocusRect() {
        if (isFocused()) {
            return getFocusOwner().getFocusRect();
        }
        View viewFindFocus = findFocus();
        if (viewFindFocus != null) {
            return FocusInteropUtils_androidKt.calculateFocusRectRelativeTo(viewFindFocus, this);
        }
        return null;
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    public void focusTargetAvailable() {
        if (getFocusOwner().getRootState().getHasFocus()) {
            return;
        }
        focusableViewAvailable(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final View findNextNonChildView(int direction) {
        AndroidComposeView currentView = this;
        FocusFinderCompat focusFinder = FocusFinderCompat.INSTANCE.getInstance();
        while (currentView != null) {
            View rootView = getRootView();
            Intrinsics.checkNotNull(rootView, "null cannot be cast to non-null type android.view.ViewGroup");
            currentView = focusFinder.findNextFocus((ViewGroup) rootView, currentView, direction);
            if (currentView != null && !AndroidComposeView_androidKt.containsDescendant(this, currentView)) {
                return currentView;
            }
        }
        return null;
    }

    @Override // androidx.compose.ui.node.Owner
    public ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    public final InsetsListener getInsetsListener() {
        return this.insetsListener;
    }

    @Override // androidx.compose.ui.node.Owner
    public LayoutNode getRoot() {
        return this.root;
    }

    @Override // androidx.compose.ui.node.Owner
    public MutableIntObjectMap<LayoutNode> getLayoutNodes() {
        return this.layoutNodes;
    }

    @Override // androidx.compose.ui.node.Owner
    public RectManager getRectManager() {
        return this.rectManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public RootForTest getRootForTest() {
        return this.rootForTest;
    }

    /* JADX INFO: renamed from: getUncaughtExceptionHandler$ui, reason: from getter */
    public final RootForTest.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return this.uncaughtExceptionHandler;
    }

    public final void setUncaughtExceptionHandler$ui(RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public SemanticsOwner getSemanticsOwner() {
        return this.semanticsOwner;
    }

    /* JADX INFO: renamed from: getContentCaptureManager$ui, reason: from getter */
    public final AndroidContentCaptureManager getContentCaptureManager() {
        return this.contentCaptureManager;
    }

    public final void setContentCaptureManager$ui(AndroidContentCaptureManager androidContentCaptureManager) {
        this.contentCaptureManager = androidContentCaptureManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidAccessibilityManager getAccessibilityManager() {
        return this.accessibilityManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public GraphicsContext getGraphicsContext() {
        return this.graphicsContext;
    }

    @Override // androidx.compose.ui.node.Owner
    public AutofillTree getAutofillTree() {
        return this.autofillTree;
    }

    public final Configuration getConfiguration() {
        State $this$getValue$iv = this.configuration;
        return (Configuration) $this$getValue$iv.getValue();
    }

    public final void setConfiguration(Configuration configuration) {
        MutableState $this$setValue$iv = this.configuration;
        $this$setValue$iv.setValue(configuration);
    }

    /* JADX INFO: renamed from: get_autofillManager$ui, reason: from getter */
    public final AndroidAutofillManager get_autofillManager() {
        return this._autofillManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public Autofill getAutofill() {
        return this._autofill;
    }

    @Override // androidx.compose.ui.node.Owner
    public androidx.compose.ui.autofill.AutofillManager getAutofillManager() {
        return this._autofillManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidClipboard getClipboard() {
        return this.clipboard;
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnerSnapshotObserver getSnapshotObserver() {
        return this.snapshotObserver;
    }

    @Override // androidx.compose.ui.node.Owner
    public void setShowLayoutBounds(boolean z) {
        this.showLayoutBounds = z;
    }

    @Override // androidx.compose.ui.node.Owner
    public boolean getShowLayoutBounds() {
        return Build.VERSION.SDK_INT >= 30 ? Api30Impl.INSTANCE.isShowingLayoutBounds(this) : this.showLayoutBounds;
    }

    public final AndroidViewsHandler getAndroidViewsHandler$ui() {
        if (this._androidViewsHandler == null) {
            this._androidViewsHandler = new AndroidViewsHandler(getContext());
            addView(this._androidViewsHandler);
            requestLayout();
        }
        AndroidViewsHandler androidViewsHandler = this._androidViewsHandler;
        Intrinsics.checkNotNull(androidViewsHandler);
        return androidViewsHandler;
    }

    @Override // androidx.compose.ui.node.Owner
    public long getMeasureIteration() {
        return this.measureAndLayoutDelegate.getMeasureIteration();
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public boolean getHasPendingMeasureOrLayout() {
        return this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout() || !this.outOfFrameQueue.isEmpty();
    }

    /* JADX INFO: renamed from: getLastMatrixRecalculationAnimationTime$ui, reason: from getter */
    public final long getLastMatrixRecalculationAnimationTime() {
        return this.lastMatrixRecalculationAnimationTime;
    }

    public final void setLastMatrixRecalculationAnimationTime$ui(long j) {
        this.lastMatrixRecalculationAnimationTime = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ViewTreeOwners get_viewTreeOwners() {
        State $this$getValue$iv = this._viewTreeOwners;
        return (ViewTreeOwners) $this$getValue$iv.getValue();
    }

    private final void set_viewTreeOwners(ViewTreeOwners viewTreeOwners) {
        MutableState $this$setValue$iv = this._viewTreeOwners;
        $this$setValue$iv.setValue(viewTreeOwners);
    }

    public final ViewTreeOwners getViewTreeOwners() {
        State $this$getValue$iv = this.viewTreeOwners;
        return (ViewTreeOwners) $this$getValue$iv.getValue();
    }

    @Override // androidx.compose.ui.node.Owner, androidx.compose.ui.node.RootForTest
    public TextInputService getTextInputService() {
        return this.textInputService;
    }

    @Override // androidx.compose.ui.node.Owner
    public SoftwareKeyboardController getSoftwareKeyboardController() {
        return this.softwareKeyboardController;
    }

    @Override // androidx.compose.ui.node.Owner
    public Placeable.PlacementScope getPlacementScope() {
        return PlaceableKt.PlacementScope(this);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.ui.node.Owner
    public Object textInputSession(Function2<? super PlatformTextInputSessionScope, ? super Continuation<?>, ? extends Object> function2, Continuation<?> continuation) {
        C03731 c03731;
        if (continuation instanceof C03731) {
            c03731 = (C03731) continuation;
            if ((c03731.label & Integer.MIN_VALUE) != 0) {
                c03731.label -= Integer.MIN_VALUE;
            } else {
                c03731 = new C03731(continuation);
            }
        } else {
            c03731 = new C03731(continuation);
        }
        Object $result = c03731.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c03731.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                AtomicReference<SessionMutex.Session<AndroidPlatformTextInputSession>> atomicReference = this.textInputSessionMutex;
                Function1<CoroutineScope, AndroidPlatformTextInputSession> function1 = new Function1<CoroutineScope, AndroidPlatformTextInputSession>() { // from class: androidx.compose.ui.platform.AndroidComposeView.textInputSession.2
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final AndroidPlatformTextInputSession invoke(CoroutineScope it) {
                        return new AndroidPlatformTextInputSession(AndroidComposeView.this, AndroidComposeView.this.getTextInputService(), it);
                    }
                };
                c03731.label = 1;
                if (SessionMutex.m4592withSessionCancellingPreviousimpl(atomicReference, function1, function2, c03731) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        throw new KotlinNothingValueException();
    }

    @Override // androidx.compose.ui.node.Owner
    public Font.ResourceLoader getFontLoader() {
        return this.fontLoader;
    }

    private void setFontFamilyResolver(FontFamily.Resolver resolver) {
        MutableState $this$setValue$iv = this.fontFamilyResolver;
        $this$setValue$iv.setValue(resolver);
    }

    @Override // androidx.compose.ui.node.Owner
    public FontFamily.Resolver getFontFamilyResolver() {
        State $this$getValue$iv = this.fontFamilyResolver;
        return (FontFamily.Resolver) $this$getValue$iv.getValue();
    }

    private final int getFontWeightAdjustmentCompat(Configuration $this$fontWeightAdjustmentCompat) {
        if (Build.VERSION.SDK_INT >= 31) {
            return $this$fontWeightAdjustmentCompat.fontWeightAdjustment;
        }
        return 0;
    }

    private void setLayoutDirection(LayoutDirection layoutDirection) {
        MutableState $this$setValue$iv = this.layoutDirection;
        $this$setValue$iv.setValue(layoutDirection);
    }

    @Override // android.view.View, android.view.ViewParent, androidx.compose.ui.node.Owner
    public LayoutDirection getLayoutDirection() {
        State $this$getValue$iv = this.layoutDirection;
        return (LayoutDirection) $this$getValue$iv.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    @Override // androidx.compose.ui.node.Owner
    public InputModeManager getInputModeManager() {
        return this._inputModeManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public ModifierLocalManager getModifierLocalManager() {
        return this.modifierLocalManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    static final void sendHoverExitEvent$lambda$0(AndroidComposeView this$0) {
        this$0.hoverExitReceived = false;
        MotionEvent lastEvent = this$0.previousMotionEvent;
        Intrinsics.checkNotNull(lastEvent);
        if (!(lastEvent.getActionMasked() == 10)) {
            throw new IllegalStateException("The ACTION_HOVER_EXIT event was not cleared.".toString());
        }
        this$0.m6977sendMotionEvent8iAsVTc(lastEvent);
    }

    @Override // android.view.View
    public void getFocusedRect(android.graphics.Rect rect) {
        Rect focusRect = getEmbeddedViewFocusRect();
        if (focusRect != null) {
            float $this$fastRoundToInt$iv = focusRect.getLeft();
            rect.left = Math.round($this$fastRoundToInt$iv);
            float $this$fastRoundToInt$iv2 = focusRect.getTop();
            rect.top = Math.round($this$fastRoundToInt$iv2);
            float $this$fastRoundToInt$iv3 = focusRect.getRight();
            rect.right = Math.round($this$fastRoundToInt$iv3);
            float $this$fastRoundToInt$iv4 = focusRect.getBottom();
            rect.bottom = Math.round($this$fastRoundToInt$iv4);
            return;
        }
        if (!Intrinsics.areEqual((Object) getFocusOwner().mo4746focusSearchULY8qGw(FocusDirection.INSTANCE.m4731getDowndhqQ8s(), null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView.getFocusedRect.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                return true;
            }
        }), (Object) true)) {
            rect.set(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        } else {
            super.getFocusedRect(rect);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
            if (getFocusOwner().hasFocusableContent()) {
                super.addFocusables(views, direction, focusableMode);
                if (getFocusOwner().hasNonInteropFocusableContent() || views == null) {
                    return;
                }
                views.remove(this);
                return;
            }
            return;
        }
        super.addFocusables(views, direction, focusableMode);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideStructure(ViewStructure structure) {
        if (Build.VERSION.SDK_INT < 28) {
            AndroidComposeViewAssistHelperMethodsO.INSTANCE.setClassName(structure, getView());
        } else {
            super.dispatchProvideStructure(structure);
        }
    }

    public final boolean getScrollCaptureInProgress$ui() {
        ScrollCapture scrollCapture;
        if (Build.VERSION.SDK_INT < 31 || (scrollCapture = this.scrollCapture) == null) {
            return false;
        }
        return scrollCapture.getScrollCaptureInProgress();
    }

    @Override // android.view.View
    public void onScrollCaptureSearch(android.graphics.Rect localVisibleRect, Point windowOffset, Consumer<ScrollCaptureTarget> targets) {
        ScrollCapture scrollCapture;
        if (Build.VERSION.SDK_INT >= 31 && (scrollCapture = this.scrollCapture) != null) {
            scrollCapture.onScrollCaptureSearch(this, getSemanticsOwner(), getCoroutineContext(), targets);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onResume(LifecycleOwner owner) {
        if (Build.VERSION.SDK_INT < 30) {
            setShowLayoutBounds(INSTANCE.getIsShowingLayoutBounds());
        }
        LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry retainedValuesStoreEntry = this.lifecycleRetainedValuesStoreOwnerEntry;
        if (retainedValuesStoreEntry != null) {
            LifecycleRetainedValuesStoreOwner.FrameEndScheduler frameEndScheduler = this.frameEndScheduler;
            Intrinsics.checkNotNull(frameEndScheduler);
            retainedValuesStoreEntry.stopRetainingExitedValues(frameEndScheduler);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry retainedValuesStoreEntry = this.lifecycleRetainedValuesStoreOwnerEntry;
        if (retainedValuesStoreEntry != null) {
            retainedValuesStoreEntry.startRetainingExitedValues();
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0045  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View focused, int direction) {
        Rect focusedBounds;
        if (focused == null || this.measureAndLayoutDelegate.getDuringMeasureLayout()) {
            return super.focusSearch(focused, direction);
        }
        View rootView = getRootView();
        Intrinsics.checkNotNull(rootView, "null cannot be cast to non-null type android.view.ViewGroup");
        ViewGroup root = (ViewGroup) rootView;
        View nextView = (Build.VERSION.SDK_INT >= 26 || !ComposeUiFlags.isPre26FocusFinderFixEnabled) ? FocusFinder.getInstance().findNextFocus(root, focused, direction) : FocusFinderCompat.INSTANCE.getInstance().findNextFocus(root, focused, direction);
        if (nextView != null) {
            View it = nextView;
            if (!AndroidComposeView_androidKt.containsDescendant(this, it)) {
                nextView = null;
            }
        } else {
            nextView = null;
        }
        if (focused != this || (focusedBounds = getFocusOwner().getFocusRect()) == null) {
            focusedBounds = FocusInteropUtils_androidKt.calculateFocusRectRelativeTo(focused, this);
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(direction);
        int focusDirection2 = focusDirection != null ? focusDirection.getValue() : FocusDirection.INSTANCE.m4731getDowndhqQ8s();
        final Ref.ObjectRef focusTarget = new Ref.ObjectRef();
        Boolean searchResult = getFocusOwner().mo4746focusSearchULY8qGw(focusDirection2, focusedBounds, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$focusSearch$searchResult$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode focusTargetNode) {
                focusTarget.element = focusTargetNode;
                return true;
            }
        });
        if (searchResult == null) {
            return focused;
        }
        if (focusTarget.element == 0) {
            if (nextView == null) {
                return super.focusSearch(focused, direction);
            }
        } else {
            if (nextView == null) {
                return this;
            }
            if (FocusOwnerImplKt.m4754is1dFocusSearch3ESFkO8(focusDirection2)) {
                return ComposeUiFlags.isBypassUnfocusableComposeViewEnabled ? this : super.focusSearch(focused, direction);
            }
            if (TwoDimensionalFocusSearchKt.m4800isBetterCandidateI7lrPNg(FocusTraversalKt.focusRect((FocusTargetNode) focusTarget.element), FocusInteropUtils_androidKt.calculateFocusRectRelativeTo(nextView, this), focusedBounds, focusDirection2)) {
                return this;
            }
        }
        return nextView;
    }

    public final boolean requestFocusCurrent(int direction, android.graphics.Rect previouslyFocusedRect) {
        if (isFocused()) {
            return true;
        }
        if (getFocusOwner().getRootState().getHasFocus()) {
            return super.requestFocus(direction, previouslyFocusedRect);
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(direction);
        final int focusDirection2 = focusDirection != null ? focusDirection.getValue() : FocusDirection.INSTANCE.m4732getEnterdhqQ8s();
        return Intrinsics.areEqual((Object) getFocusOwner().mo4746focusSearchULY8qGw(focusDirection2, previouslyFocusedRect != null ? RectHelper_androidKt.toComposeRect(previouslyFocusedRect) : null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView.requestFocusCurrent.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection2));
            }
        }), (Object) true);
    }

    public final boolean requestFocusViewFocusFix(int direction, android.graphics.Rect previouslyFocusedRect) {
        if (isFocused()) {
            return true;
        }
        if (this.processingRequestFocusForNextNonChildView) {
            return false;
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(direction);
        final int focusDirection2 = focusDirection != null ? focusDirection.getValue() : FocusDirection.INSTANCE.m4732getEnterdhqQ8s();
        if (hasFocus() && mo4793moveFocusInChildren3ESFkO8(focusDirection2)) {
            return true;
        }
        final Ref.BooleanRef foundFocusable = new Ref.BooleanRef();
        Boolean focusSearchResult = getFocusOwner().mo4746focusSearchULY8qGw(focusDirection2, previouslyFocusedRect != null ? RectHelper_androidKt.toComposeRect(previouslyFocusedRect) : null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$requestFocusViewFocusFix$focusSearchResult$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                foundFocusable.element = true;
                return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection2));
            }
        });
        if (focusSearchResult == null) {
            return false;
        }
        if (focusSearchResult.booleanValue()) {
            return true;
        }
        if (foundFocusable.element) {
            return false;
        }
        if (previouslyFocusedRect != null && !hasFocus()) {
            Boolean altFocus = getFocusOwner().mo4746focusSearchULY8qGw(focusDirection2, null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$requestFocusViewFocusFix$altFocus$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(FocusTargetNode it) {
                    return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection2));
                }
            });
            if (Intrinsics.areEqual((Object) altFocus, (Object) true)) {
                return true;
            }
        }
        View nextFocusedView = findNextNonChildView(direction);
        if (nextFocusedView == null || nextFocusedView == this) {
            return true;
        }
        this.processingRequestFocusForNextNonChildView = true;
        boolean requestFocusResult = nextFocusedView.requestFocus(direction);
        this.processingRequestFocusForNextNonChildView = false;
        return requestFocusResult;
    }

    public final boolean requestFocusBypassUnfocusableComposeView(int direction, android.graphics.Rect previouslyFocusedRect) {
        if (isFocused()) {
            return true;
        }
        FocusDirection focusDirection = FocusInteropUtils_androidKt.toFocusDirection(direction);
        final int focusDirection2 = focusDirection != null ? focusDirection.getValue() : FocusDirection.INSTANCE.m4732getEnterdhqQ8s();
        Boolean requestFocusWithPrevRect = getFocusOwner().mo4746focusSearchULY8qGw(focusDirection2, previouslyFocusedRect != null ? RectHelper_androidKt.toComposeRect(previouslyFocusedRect) : null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$requestFocusBypassUnfocusableComposeView$requestFocusWithPrevRect$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection2));
            }
        });
        if (Intrinsics.areEqual((Object) requestFocusWithPrevRect, (Object) true)) {
            return true;
        }
        if (ComposeUiFlags.isIgnoreInvalidPrevFocusRectEnabled) {
            Boolean requestFocusWithoutPrevRect = getFocusOwner().mo4746focusSearchULY8qGw(focusDirection2, null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$requestFocusBypassUnfocusableComposeView$requestFocusWithoutPrevRect$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(FocusTargetNode it) {
                    return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection2));
                }
            });
            if (Intrinsics.areEqual((Object) requestFocusWithoutPrevRect, (Object) true)) {
                return true;
            }
        }
        if (hasFocus() && FocusOwnerImplKt.m4754is1dFocusSearch3ESFkO8(focusDirection2)) {
            return getFocusOwner().mo4749resetFocus3ESFkO8(focusDirection2);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean requestFocus(int direction, android.graphics.Rect previouslyFocusedRect) {
        if (ComposeUiFlags.isViewFocusFixEnabled) {
            return requestFocusViewFocusFix(direction, previouslyFocusedRect);
        }
        if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
            return requestFocusBypassUnfocusableComposeView(direction, previouslyFocusedRect);
        }
        return requestFocusCurrent(direction, previouslyFocusedRect);
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    /* JADX INFO: renamed from: requestOwnerFocus-7o62pno */
    public boolean mo4794requestOwnerFocus7o62pno(FocusDirection focusDirection, Rect previouslyFocusedRect) {
        Integer numM4739toAndroidFocusDirection3ESFkO8;
        if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
            if (isFocused()) {
                return true;
            }
        } else if (isFocused() || hasFocus()) {
            return true;
        }
        return super.requestFocus((focusDirection == null || (numM4739toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m4739toAndroidFocusDirection3ESFkO8(focusDirection.getValue())) == null) ? TsExtractor.TS_STREAM_TYPE_HDMV_DTS : numM4739toAndroidFocusDirection3ESFkO8.intValue(), previouslyFocusedRect != null ? RectHelper_androidKt.toAndroidRect(previouslyFocusedRect) : null);
    }

    @Override // androidx.compose.ui.focus.PlatformFocusOwner
    public void clearOwnerFocus() {
        if (isFocused() || (!ComposeUiFlags.isViewFocusFixEnabled && hasFocus())) {
            super.clearFocus();
        } else if (hasFocus()) {
            View viewFindFocus = findFocus();
            if (viewFindFocus != null) {
                viewFindFocus.clearFocus();
            }
            super.clearFocus();
        }
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean gainFocus, int direction, android.graphics.Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (!gainFocus && !hasFocus()) {
            getFocusOwner().releaseFocus();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r0v40, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v52, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v12, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r21v5 */
    /* JADX WARN: Type inference failed for: r29v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r29v5 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r38v1 */
    /* JADX WARN: Type inference failed for: r38v2 */
    /* JADX WARN: Type inference failed for: r38v3 */
    /* JADX WARN: Type inference failed for: r38v6 */
    /* JADX WARN: Type inference failed for: r38v8 */
    @Override // androidx.compose.ui.focus.FocusListener
    public void onFocusChanged(FocusTargetModifierNode previous, FocusTargetModifierNode current) {
        Object tail;
        int i;
        FocusTargetModifierNode focusTargetModifierNode;
        int i2;
        boolean z;
        NodeChain nodes;
        boolean z2;
        Modifier.Node node;
        FocusTargetModifierNode focusTargetModifierNode2;
        int i3;
        NodeChain nodes2;
        ?? parent;
        int i4;
        ?? r2;
        int i5;
        ?? r38;
        ?? r3;
        if (!ComposeUiFlags.isOptimizedFocusEventDispatchEnabled || previous == null) {
            return;
        }
        FocusTargetModifierNode focusTargetModifierNode3 = previous;
        int i6 = 2097152;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(2097152);
        ArrayList arrayList = null;
        if (!focusTargetModifierNode3.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = focusTargetModifierNode3.getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetModifierNode3);
        Object obj = node2;
        while (true) {
            tail = null;
            i = i6;
            if (layoutNodeRequireLayoutNode == null) {
                break;
            }
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                while (parent != 0) {
                    if ((parent.getKindSet() & iM6860constructorimpl) != 0) {
                        MutableVector mutableVector = null;
                        ?? Pop = parent;
                        while (Pop != 0) {
                            focusTargetModifierNode3 = focusTargetModifierNode3;
                            if (Pop instanceof IndirectPointerInputModifierNode) {
                                ?? r0 = Pop;
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                arrayList.add(r0);
                                i4 = iM6860constructorimpl;
                            } else if (((Pop.getKindSet() & iM6860constructorimpl) != 0 ? 1 : 0) == 0 || !(Pop instanceof DelegatingNode)) {
                                i4 = iM6860constructorimpl;
                            } else {
                                int i7 = 0;
                                Modifier.Node delegate$ui = ((DelegatingNode) Pop).getDelegate();
                                while (delegate$ui != null) {
                                    Modifier.Node node3 = delegate$ui;
                                    if (((node3.getKindSet() & iM6860constructorimpl) != 0 ? 1 : 0) != 0) {
                                        r2 = Pop;
                                        i7++;
                                        i5 = iM6860constructorimpl;
                                        if (i7 == 1) {
                                            r2 = Pop;
                                            r38 = node3;
                                        } else {
                                            MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                            ?? r1 = r2;
                                            if (r1 != 0) {
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(r1);
                                                }
                                                r3 = 0;
                                            }
                                            if (mutableVector2 != null) {
                                                r3 = r2;
                                                mutableVector2.add(node3);
                                            } else {
                                                r3 = r2;
                                            }
                                            r38 = r3;
                                            i7 = i7;
                                            mutableVector = mutableVector2;
                                        }
                                    } else {
                                        r2 = Pop;
                                        r2 = Pop;
                                        i5 = iM6860constructorimpl;
                                        r38 = r2;
                                    }
                                    delegate$ui = delegate$ui.getChild();
                                    iM6860constructorimpl = i5;
                                    r2 = r38;
                                }
                                r2 = Pop;
                                i4 = iM6860constructorimpl;
                                ?? r39 = r2;
                                if (i7 == 1) {
                                    iM6860constructorimpl = i4;
                                    Pop = r39;
                                }
                            }
                            Pop = DelegatableNodeKt.pop(mutableVector);
                            iM6860constructorimpl = i4;
                        }
                    }
                    focusTargetModifierNode3 = focusTargetModifierNode3;
                    iM6860constructorimpl = iM6860constructorimpl;
                    parent = parent.getParent();
                }
                focusTargetModifierNode2 = focusTargetModifierNode3;
                i3 = iM6860constructorimpl;
            } else {
                parent = obj;
                focusTargetModifierNode2 = focusTargetModifierNode3;
                i3 = iM6860constructorimpl;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            if (layoutNodeRequireLayoutNode != null && (nodes2 = layoutNodeRequireLayoutNode.getNodes()) != null) {
                tail = nodes2.getTail();
            }
            obj = tail;
            i6 = i;
            focusTargetModifierNode3 = focusTargetModifierNode2;
            iM6860constructorimpl = i3;
        }
        if (arrayList != null) {
            if (current != null) {
                FocusTargetModifierNode focusTargetModifierNode4 = current;
                int iM6860constructorimpl2 = NodeKind.m6860constructorimpl(i);
                boolean z3 = true;
                LinkedHashSet linkedHashSet = null;
                if (!focusTargetModifierNode4.getNode().getIsAttached()) {
                    InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
                }
                Modifier.Node node4 = focusTargetModifierNode4.getNode();
                LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(focusTargetModifierNode4);
                while (layoutNodeRequireLayoutNode2 != null) {
                    if ((layoutNodeRequireLayoutNode2.getNodes().getHead().getAggregateChildKindSet() & iM6860constructorimpl2) != 0) {
                        while (node4 != null) {
                            if ((node4.getKindSet() & iM6860constructorimpl2) != 0) {
                                MutableVector mutableVector3 = null;
                                Modifier.Node nodePop = node4;
                                while (nodePop != null) {
                                    iM6860constructorimpl2 = iM6860constructorimpl2;
                                    if (nodePop instanceof IndirectPointerInputModifierNode) {
                                        Modifier.Node node5 = nodePop;
                                        if (linkedHashSet == null) {
                                            linkedHashSet = new LinkedHashSet();
                                        }
                                        linkedHashSet.add(node5);
                                        z2 = z3;
                                    } else if (((nodePop.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                        z2 = z3;
                                    } else {
                                        int i8 = 0;
                                        Modifier.Node delegate$ui2 = ((DelegatingNode) nodePop).getDelegate();
                                        while (delegate$ui2 != null) {
                                            Modifier.Node node6 = delegate$ui2;
                                            if (((node6.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) != 0) {
                                                i8++;
                                                node = nodePop;
                                                if (i8 == 1) {
                                                    node = node6;
                                                    z3 = z3;
                                                } else {
                                                    MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                    if (node != null) {
                                                        if (mutableVector4 != null) {
                                                            mutableVector4.add(node);
                                                        }
                                                        node = null;
                                                    }
                                                    if (mutableVector4 != null) {
                                                        mutableVector4.add(node6);
                                                    }
                                                    mutableVector3 = mutableVector4;
                                                    i8 = i8;
                                                }
                                            } else {
                                                node = nodePop;
                                                z3 = z3;
                                            }
                                            delegate$ui2 = delegate$ui2.getChild();
                                            nodePop = node;
                                            z3 = z3;
                                        }
                                        Modifier.Node node7 = nodePop;
                                        z2 = z3;
                                        if (i8 == 1) {
                                            nodePop = node7;
                                            z3 = z2;
                                        }
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector3);
                                    z3 = z2;
                                }
                            }
                            node4 = node4.getParent();
                            focusTargetModifierNode4 = focusTargetModifierNode4;
                            iM6860constructorimpl2 = iM6860constructorimpl2;
                            z3 = z3;
                        }
                        focusTargetModifierNode = focusTargetModifierNode4;
                        i2 = iM6860constructorimpl2;
                        z = z3;
                    } else {
                        focusTargetModifierNode = focusTargetModifierNode4;
                        i2 = iM6860constructorimpl2;
                        z = z3;
                    }
                    layoutNodeRequireLayoutNode2 = layoutNodeRequireLayoutNode2.getParent$ui();
                    node4 = (layoutNodeRequireLayoutNode2 == null || (nodes = layoutNodeRequireLayoutNode2.getNodes()) == null) ? null : nodes.getTail();
                    focusTargetModifierNode4 = focusTargetModifierNode;
                    iM6860constructorimpl2 = i2;
                    z3 = z;
                }
                tail = linkedHashSet;
            }
            ?? r4 = tail;
            List list = arrayList;
            int size = list.size();
            for (int i9 = 0; i9 < size; i9++) {
                IndirectPointerInputModifierNode indirectPointerInputModifierNode = (IndirectPointerInputModifierNode) list.get(i9);
                if (!(r4 != 0 ? r4.contains(indirectPointerInputModifierNode) : false)) {
                    indirectPointerInputModifierNode.onCancelIndirectPointerInput();
                }
            }
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        boolean newShowLayoutBounds;
        this._windowInfo.setWindowFocused(hasWindowFocus);
        this.keyboardModifiersRequireUpdate = true;
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && Build.VERSION.SDK_INT < 30 && getShowLayoutBounds() != (newShowLayoutBounds = INSTANCE.getIsShowingLayoutBounds())) {
            setShowLayoutBounds(newShowLayoutBounds);
            invalidateDescendants();
        }
    }

    @Override // androidx.compose.ui.node.RootForTest
    /* JADX INFO: renamed from: sendKeyEvent-ZmokQxo */
    public boolean mo6928sendKeyEventZmokQxo(KeyEvent keyEvent) {
        return getFocusOwner().mo4744dispatchInterceptedSoftKeyboardEventZmokQxo(keyEvent) || FocusOwner.m4742dispatchKeyEventYhN2O0w$default(getFocusOwner(), keyEvent, null, 2, null);
    }

    @Override // androidx.compose.ui.node.RootForTest
    public boolean sendIndirectPointerEvent(IndirectPointerEvent indirectPointerEvent) {
        if (AndroidIndirectPointerEvent_androidKt.getNativeEvent(indirectPointerEvent).getActionMasked() == 3) {
            getFocusOwner().dispatchIndirectPointerCancel();
            return true;
        }
        return handleIndirectPointerEvent(indirectPointerEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(final KeyEvent event) {
        if (isFocused()) {
            this._windowInfo.m7048setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m6472constructorimpl(event.getMetaState()));
            return FocusOwner.m4742dispatchKeyEventYhN2O0w$default(getFocusOwner(), androidx.compose.ui.input.key.KeyEvent.m6237constructorimpl(event), null, 2, null) || super.dispatchKeyEvent(event);
        }
        return getFocusOwner().mo4745dispatchKeyEventYhN2O0w(androidx.compose.ui.input.key.KeyEvent.m6237constructorimpl(event), new Function0<Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView.dispatchKeyEvent.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(AndroidComposeView.super.dispatchKeyEvent(event));
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        return (isFocused() && getFocusOwner().mo4744dispatchInterceptedSoftKeyboardEventZmokQxo(androidx.compose.ui.input.key.KeyEvent.m6237constructorimpl(event))) || super.dispatchKeyEventPreIme(event);
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void forceAccessibilityForTesting(boolean enable) {
        this.composeAccessibilityDelegate.setAccessibilityForceEnabledForTesting$ui(enable);
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void setAccessibilityEventBatchIntervalMillis(long intervalMillis) {
        this.composeAccessibilityDelegate.setSendRecurringAccessibilityEventsIntervalMillis$ui(intervalMillis);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onPreAttach(LayoutNode node) {
        getLayoutNodes().set(node.getSemanticsId(), node);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onPostAttach(LayoutNode node) {
        AndroidAutofillManager androidAutofillManager;
        if (!autofillSupported() || !ComposeUiFlags.isSemanticAutofillEnabled || (androidAutofillManager = this._autofillManager) == null) {
            return;
        }
        androidAutofillManager.onPostAttach$ui(node);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onDetach(LayoutNode node) {
        AndroidAutofillManager androidAutofillManager;
        getLayoutNodes().remove(node.getSemanticsId());
        this.measureAndLayoutDelegate.onNodeDetached(node);
        requestClearInvalidObservations();
        if (!autofillSupported() || !ComposeUiFlags.isSemanticAutofillEnabled || (androidAutofillManager = this._autofillManager) == null) {
            return;
        }
        androidAutofillManager.onDetach$ui(node);
    }

    @Override // androidx.compose.ui.node.Owner
    public void requestAutofill(LayoutNode node) {
        AndroidAutofillManager androidAutofillManager;
        if (!autofillSupported() || !ComposeUiFlags.isSemanticAutofillEnabled || (androidAutofillManager = this._autofillManager) == null) {
            return;
        }
        androidAutofillManager.requestAutofill$ui(node);
    }

    public final void requestClearInvalidObservations() {
        this.observationClearRequested = true;
    }

    @Override // androidx.compose.ui.node.Owner
    public void onEndApplyChanges() {
        AndroidAutofillManager androidAutofillManager;
        if (this.observationClearRequested) {
            getSnapshotObserver().clearInvalidObservations$ui();
            this.observationClearRequested = false;
        }
        AndroidViewsHandler childAndroidViews = this._androidViewsHandler;
        if (childAndroidViews != null) {
            clearChildInvalidObservations(childAndroidViews);
        }
        if (autofillSupported() && ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
            androidAutofillManager.onEndApplyChanges$ui();
        }
        while (this.endApplyChangesListeners.isNotEmpty() && this.endApplyChangesListeners.get(0) != null) {
            int size = this.endApplyChangesListeners.getSize();
            for (int i = 0; i < size; i++) {
                Function0<Unit> function0 = this.endApplyChangesListeners.get(i);
                this.endApplyChangesListeners.set(i, null);
                if (function0 != null) {
                    function0.invoke();
                }
            }
            this.endApplyChangesListeners.removeRange(0, size);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void registerOnEndApplyChangesListener(Function0<Unit> listener) {
        if (this.endApplyChangesListeners.contains(listener)) {
            return;
        }
        this.endApplyChangesListeners.add(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startDrag-12SF9DM, reason: not valid java name */
    public final boolean m6978startDrag12SF9DM(DragAndDropTransferData transferData, long decorationSize, Function1<? super DrawScope, Unit> drawDragDecoration) {
        Resources $this$startDrag_12SF9DM_u24lambda_u240 = getContext().getResources();
        Density density = DensityKt.Density($this$startDrag_12SF9DM_u24lambda_u240.getDisplayMetrics().density, $this$startDrag_12SF9DM_u24lambda_u240.getConfiguration().fontScale);
        ComposeDragShadowBuilder shadowBuilder = new ComposeDragShadowBuilder(density, decorationSize, drawDragDecoration, null);
        int i = Build.VERSION.SDK_INT;
        return AndroidComposeViewStartDragAndDropN.INSTANCE.startDragAndDrop(this, transferData, shadowBuilder);
    }

    private final void clearChildInvalidObservations(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof AndroidComposeView) {
                ((AndroidComposeView) child).onEndApplyChanges();
            } else if (child instanceof ViewGroup) {
                clearChildInvalidObservations((ViewGroup) child);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int virtualViewId, AccessibilityNodeInfo info, String extraDataKey) {
        int it;
        if (Intrinsics.areEqual(extraDataKey, this.composeAccessibilityDelegate.getExtraDataTestTraversalBeforeVal())) {
            int it2 = this.composeAccessibilityDelegate.getIdToBeforeMap().getOrDefault(virtualViewId, -1);
            if (it2 != -1) {
                info.getExtras().putInt(extraDataKey, it2);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(extraDataKey, this.composeAccessibilityDelegate.getExtraDataTestTraversalAfterVal()) && (it = this.composeAccessibilityDelegate.getIdToAfterMap().getOrDefault(virtualViewId, -1)) != -1) {
            info.getExtras().putInt(extraDataKey, it);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View child) {
        addView(child, -1);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index) {
        Intrinsics.checkNotNull(child);
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        addView(child, index, layoutParams);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int width, int height) {
        ViewGroup.LayoutParams it = generateDefaultLayoutParams();
        it.width = width;
        it.height = height;
        Unit unit = Unit.INSTANCE;
        addView(child, -1, it);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View child, ViewGroup.LayoutParams params) {
        addView(child, -1, params);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        addViewInLayout(child, index, params, true);
    }

    public final void addAndroidView(AndroidViewHolder view, final LayoutNode layoutNode) {
        getAndroidViewsHandler$ui().getHolderToLayoutNode().put(view, layoutNode);
        getAndroidViewsHandler$ui().addView(view);
        getAndroidViewsHandler$ui().getLayoutNodeToHolder().put(layoutNode, view);
        view.setImportantForAccessibility(1);
        ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: androidx.compose.ui.platform.AndroidComposeView.addAndroidView.1
            /* JADX WARN: Code duplicated, block: B:21:0x005f  */
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                if (AndroidComposeView.this.composeAccessibilityDelegate.isEnabled$ui()) {
                    info.setVisibleToUser(false);
                }
                LayoutNode $this$findClosestParentNode$iv = layoutNode;
                LayoutNode currentParent$iv = $this$findClosestParentNode$iv.getParent$ui();
                while (true) {
                    if (currentParent$iv == null) {
                        currentParent$iv = null;
                        break;
                    }
                    LayoutNode it = currentParent$iv;
                    if (it.getNodes().m6821hasH91voCI$ui(NodeKind.m6860constructorimpl(8))) {
                        break;
                    } else {
                        currentParent$iv = currentParent$iv.getParent$ui();
                    }
                }
                Integer parentId = currentParent$iv != null ? Integer.valueOf(currentParent$iv.getSemanticsId()) : null;
                if (parentId != null) {
                    if (parentId.intValue() == AndroidComposeView.this.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
                        parentId = -1;
                    }
                } else {
                    parentId = -1;
                }
                info.setParent(this, parentId.intValue());
                int semanticsId = layoutNode.getSemanticsId();
                int beforeId = AndroidComposeView.this.composeAccessibilityDelegate.getIdToBeforeMap().getOrDefault(semanticsId, -1);
                if (beforeId != -1) {
                    View beforeView = SemanticsUtils_androidKt.semanticsIdToView(AndroidComposeView.this.getAndroidViewsHandler$ui(), beforeId);
                    if (beforeView != null) {
                        info.setTraversalBefore(beforeView);
                    } else {
                        info.setTraversalBefore(this, beforeId);
                    }
                    AndroidComposeView.this.addExtraDataToAccessibilityNodeInfoHelper(semanticsId, info.unwrap(), AndroidComposeView.this.composeAccessibilityDelegate.getExtraDataTestTraversalBeforeVal());
                }
                int afterId = AndroidComposeView.this.composeAccessibilityDelegate.getIdToAfterMap().getOrDefault(semanticsId, -1);
                if (afterId != -1) {
                    View afterView = SemanticsUtils_androidKt.semanticsIdToView(AndroidComposeView.this.getAndroidViewsHandler$ui(), afterId);
                    if (afterView != null) {
                        info.setTraversalAfter(afterView);
                    } else {
                        info.setTraversalAfter(this, afterId);
                    }
                    AndroidComposeView.this.addExtraDataToAccessibilityNodeInfoHelper(semanticsId, info.unwrap(), AndroidComposeView.this.composeAccessibilityDelegate.getExtraDataTestTraversalAfterVal());
                }
            }
        });
    }

    public final void removeAndroidView(AndroidViewHolder view) {
        getAndroidViewsHandler$ui().removeViewInLayout(view);
        HashMap<LayoutNode, AndroidViewHolder> layoutNodeToHolder = getAndroidViewsHandler$ui().getLayoutNodeToHolder();
        TypeIntrinsics.asMutableMap(layoutNodeToHolder).remove(getAndroidViewsHandler$ui().getHolderToLayoutNode().remove(view));
        view.setImportantForAccessibility(0);
    }

    public final void drawAndroidView(AndroidViewHolder view, Canvas canvas) {
        getAndroidViewsHandler$ui().drawView(view, canvas);
    }

    static /* synthetic */ void scheduleMeasureAndLayout$default(AndroidComposeView androidComposeView, LayoutNode layoutNode, int i, Object obj) {
        if ((i & 1) != 0) {
            layoutNode = null;
        }
        androidComposeView.scheduleMeasureAndLayout(layoutNode);
    }

    private final void scheduleMeasureAndLayout(LayoutNode nodeToRemeasure) {
        if (!isLayoutRequested() && isAttachedToWindow()) {
            if (nodeToRemeasure != null) {
                LayoutNode node = nodeToRemeasure;
                while (node != null && node.getMeasuredByParent$ui() == LayoutNode.UsageByParent.InMeasureBlock && childSizeCanAffectParentSize(node)) {
                    node = node.getParent$ui();
                }
                if (node == getRoot()) {
                    requestLayout();
                    return;
                }
            }
            if (getWidth() == 0 || getHeight() == 0) {
                requestLayout();
            } else {
                invalidate();
            }
        }
    }

    private final boolean childSizeCanAffectParentSize(LayoutNode $this$childSizeCanAffectParentSize) {
        if (this.wasMeasuredWithMultipleConstraints) {
            return true;
        }
        LayoutNode parent$ui = $this$childSizeCanAffectParentSize.getParent$ui();
        return parent$ui != null && !parent$ui.getHasFixedInnerContentConstraints$ui();
    }

    @Override // androidx.compose.ui.node.Owner
    public void measureAndLayout(boolean sendPointerUpdate) {
        Function0<Unit> function0;
        if (!this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout() && !this.measureAndLayoutDelegate.getHasPendingOnPositionedCallbacks()) {
            return;
        }
        Trace.beginSection("AndroidOwner:measureAndLayout");
        if (!sendPointerUpdate) {
            function0 = null;
        } else {
            try {
                function0 = this.resendMotionEventOnLayout;
            } finally {
                Trace.endSection();
            }
        }
        boolean rootNodeResized = this.measureAndLayoutDelegate.measureAndLayout(function0);
        if (rootNodeResized) {
            requestLayout();
        }
        MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
        dispatchPendingInteropLayoutCallbacks();
        Unit unit = Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: measureAndLayout-0kLqBqw */
    public void mo6927measureAndLayout0kLqBqw(LayoutNode layoutNode, long constraints) {
        Trace.beginSection("AndroidOwner:measureAndLayout");
        try {
            this.measureAndLayoutDelegate.m6808measureAndLayout0kLqBqw(layoutNode, constraints);
            if (!this.measureAndLayoutDelegate.getHasPendingMeasureOrLayout()) {
                MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks$default(this.measureAndLayoutDelegate, false, 1, null);
                dispatchPendingInteropLayoutCallbacks();
            }
            getRectManager().dispatchCallbacks();
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }

    private final void dispatchPendingInteropLayoutCallbacks() {
        if (this.isPendingInteropViewLayoutChangeDispatch) {
            getViewTreeObserver().dispatchOnGlobalLayout();
            this.isPendingInteropViewLayoutChangeDispatch = false;
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void forceMeasureTheSubtree(LayoutNode layoutNode, boolean affectsLookahead) {
        this.measureAndLayoutDelegate.forceMeasureTheSubtree(layoutNode, affectsLookahead);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestMeasure(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest, boolean scheduleMeasureAndLayout) {
        if (affectsLookahead) {
            if (this.measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, forceRequest) && scheduleMeasureAndLayout) {
                scheduleMeasureAndLayout(layoutNode);
                return;
            }
            return;
        }
        if (this.measureAndLayoutDelegate.requestRemeasure(layoutNode, forceRequest) && scheduleMeasureAndLayout) {
            scheduleMeasureAndLayout(layoutNode);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void onRequestRelayout(LayoutNode layoutNode, boolean affectsLookahead, boolean forceRequest) {
        if (affectsLookahead) {
            if (this.measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, forceRequest)) {
                scheduleMeasureAndLayout$default(this, null, 1, null);
            }
        } else if (this.measureAndLayoutDelegate.requestRelayout(layoutNode, forceRequest)) {
            scheduleMeasureAndLayout$default(this, null, 1, null);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.measureAndLayoutDelegate.requestOnPositionedCallback(layoutNode);
        scheduleMeasureAndLayout$default(this, null, 1, null);
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void measureAndLayoutForTest() {
        Owner.measureAndLayout$default(this, false, 1, null);
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.outOfFrameRunnable);
        }
        this.outOfFrameRunnable.run();
    }

    @Override // androidx.compose.ui.node.RootForTest
    public void setUncaughtExceptionHandler(RootForTest.UncaughtExceptionHandler handler) {
        this.uncaughtExceptionHandler = handler;
        this.measureAndLayoutDelegate.setUncaughtExceptionHandler$ui(handler);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) throws Throwable {
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                invalidateLayoutNodeMeasurement(getRoot());
            }
            long jM6969convertMeasureSpecI7RO_PI = m6969convertMeasureSpecI7RO_PI(widthMeasureSpec);
            int minWidth = (int) ULong.m9262constructorimpl(jM6969convertMeasureSpecI7RO_PI >>> 32);
            int maxWidth = (int) ULong.m9262constructorimpl(jM6969convertMeasureSpecI7RO_PI & 4294967295L);
            try {
                long jM6969convertMeasureSpecI7RO_PI2 = m6969convertMeasureSpecI7RO_PI(heightMeasureSpec);
                int minHeight = (int) ULong.m9262constructorimpl(jM6969convertMeasureSpecI7RO_PI2 >>> 32);
                int maxHeight = (int) ULong.m9262constructorimpl(jM6969convertMeasureSpecI7RO_PI2 & 4294967295L);
                long constraints = Constraints.INSTANCE.m7866fitPrioritizingHeightZbe2FdA(minWidth, maxWidth, minHeight, maxHeight);
                if (this.onMeasureConstraints == null) {
                    this.onMeasureConstraints = Constraints.m7845boximpl(constraints);
                    this.wasMeasuredWithMultipleConstraints = false;
                } else {
                    Constraints constraints2 = this.onMeasureConstraints;
                    if (!(constraints2 != null ? Constraints.m7851equalsimpl0(constraints2.getValue(), constraints) : false)) {
                        this.wasMeasuredWithMultipleConstraints = true;
                    }
                }
                this.measureAndLayoutDelegate.m6809updateRootConstraintsBRTryo0(constraints);
                this.measureAndLayoutDelegate.measureOnly();
                setMeasuredDimension(getRoot().getWidth(), getRoot().getHeight());
                if (this._androidViewsHandler != null) {
                    getAndroidViewsHandler$ui().measure(View.MeasureSpec.makeMeasureSpec(getRoot().getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getRoot().getHeight(), 1073741824));
                }
                Unit unit = Unit.INSTANCE;
                Trace.endSection();
            } catch (Throwable th) {
                th = th;
                Trace.endSection();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: component1-VKZWuLQ, reason: not valid java name */
    private final int m6967component1VKZWuLQ(long j) {
        return (int) ULong.m9262constructorimpl(j >>> 32);
    }

    /* JADX INFO: renamed from: component2-VKZWuLQ, reason: not valid java name */
    private final int m6968component2VKZWuLQ(long j) {
        return (int) ULong.m9262constructorimpl(4294967295L & j);
    }

    /* JADX INFO: renamed from: pack-ZIaKswc, reason: not valid java name */
    private final long m6976packZIaKswc(int a, int b) {
        return ULong.m9262constructorimpl(ULong.m9262constructorimpl(ULong.m9262constructorimpl(a) << 32) | ULong.m9262constructorimpl(b));
    }

    /* JADX INFO: renamed from: convertMeasureSpec-I7RO_PI, reason: not valid java name */
    private final long m6969convertMeasureSpecI7RO_PI(int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case Integer.MIN_VALUE:
                return m6976packZIaKswc(0, size);
            case 0:
                return m6976packZIaKswc(0, Integer.MAX_VALUE);
            case 1073741824:
                return m6976packZIaKswc(size, size);
            default:
                throw new IllegalStateException();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.lastMatrixRecalculationAnimationTime = 0L;
        this.measureAndLayoutDelegate.measureAndLayout(this.resendMotionEventOnLayout);
        this.onMeasureConstraints = null;
        updatePositionCacheAndDispatch();
        if (this._androidViewsHandler != null) {
            getAndroidViewsHandler$ui().layout(0, 0, r - l, b - t);
        }
    }

    private final void updatePositionCacheAndDispatch() {
        boolean positionChanged = false;
        getLocationOnScreen(this.tmpPositionArray);
        long j = this.globalPosition;
        int globalX = IntOffset.m8033getXimpl(j);
        int globalY = IntOffset.m8034getYimpl(j);
        if (globalX != this.tmpPositionArray[0] || globalY != this.tmpPositionArray[1] || this.lastMatrixRecalculationAnimationTime < 0) {
            int x$iv = this.tmpPositionArray[0];
            int y$iv = this.tmpPositionArray[1];
            this.globalPosition = IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) y$iv) & 4294967295L));
            if (globalX != Integer.MAX_VALUE && globalY != Integer.MAX_VALUE) {
                positionChanged = true;
                getRoot().getLayoutDelegate().getMeasurePassDelegate().notifyChildrenUsingCoordinatesWhilePlacing();
            }
        }
        recalculateWindowPosition();
        View it = this._rootView;
        if (it == null) {
            it = getRootView();
            this._rootView = it;
        }
        getRectManager().m7122updateOffsetsgTq6Wqs(this.globalPosition, IntOffsetKt.m8050roundk4lQ0M(this.windowPosition), this.viewToWindowMatrix, it.getWidth(), it.getHeight());
        this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(positionChanged);
        getRectManager().dispatchCallbacks();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
    }

    @Override // androidx.compose.ui.node.Owner
    public OwnedLayer createLayer(Function2<? super androidx.compose.ui.graphics.Canvas, ? super GraphicsLayer, Unit> drawBlock, Function0<Unit> invalidateParentLayer, GraphicsLayer explicitLayer) {
        if (explicitLayer != null) {
            return new GraphicsLayerOwnerLayer(explicitLayer, null, this, drawBlock, invalidateParentLayer);
        }
        OwnedLayer layer = this.layerCache.pop();
        if (layer != null) {
            layer.reuseLayer(drawBlock, invalidateParentLayer);
            return layer;
        }
        int i = Build.VERSION.SDK_INT;
        return new GraphicsLayerOwnerLayer(getGraphicsContext().createGraphicsLayer(), getGraphicsContext(), this, drawBlock, invalidateParentLayer);
    }

    public final boolean recycle$ui(OwnedLayer layer) {
        if (this.viewLayersContainer != null && !ViewLayer.INSTANCE.getShouldUseDispatchDraw()) {
            int i = Build.VERSION.SDK_INT;
        }
        if (1 != 0) {
            this.layerCache.push(layer);
        }
        this.dirtyLayers.remove(layer);
        return true;
    }

    @Override // androidx.compose.ui.node.Owner
    public void onSemanticsChange() {
        this.composeAccessibilityDelegate.onSemanticsChange$ui();
        this.contentCaptureManager.onSemanticsChange$ui();
    }

    @Override // androidx.compose.ui.node.Owner
    public void onLayoutChange(LayoutNode layoutNode) {
        this.composeAccessibilityDelegate.onLayoutChange$ui(layoutNode);
        this.contentCaptureManager.onLayoutChange$ui();
    }

    @Override // androidx.compose.ui.node.Owner
    public void onLayoutNodeDeactivated(LayoutNode layoutNode) {
        AndroidAutofillManager androidAutofillManager;
        if (!autofillSupported() || !ComposeUiFlags.isSemanticAutofillEnabled || (androidAutofillManager = this._autofillManager) == null) {
            return;
        }
        androidAutofillManager.onLayoutNodeDeactivated$ui(layoutNode);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onPreLayoutNodeReused(LayoutNode layoutNode, int oldSemanticsId) {
        getLayoutNodes().remove(oldSemanticsId);
        getLayoutNodes().set(layoutNode.getSemanticsId(), layoutNode);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onPostLayoutNodeReused(LayoutNode layoutNode, int oldSemanticsId) {
        AndroidAutofillManager androidAutofillManager;
        if (!autofillSupported() || !ComposeUiFlags.isSemanticAutofillEnabled || (androidAutofillManager = this._autofillManager) == null) {
            return;
        }
        androidAutofillManager.onPostLayoutNodeReused$ui(layoutNode, oldSemanticsId);
    }

    @Override // androidx.compose.ui.node.Owner
    public void onInteropViewLayoutChange(View view) {
        this.isPendingInteropViewLayoutChangeDispatch = true;
    }

    @Override // androidx.compose.ui.node.Owner
    public void registerOnLayoutCompletedListener(Owner.OnLayoutCompletedListener listener) {
        this.measureAndLayoutDelegate.registerOnLayoutCompletedListener(listener);
        scheduleMeasureAndLayout$default(this, null, 1, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (!isAttachedToWindow()) {
            invalidateLayers(getRoot());
        }
        View view = null;
        Owner.measureAndLayout$default(this, false, 1, null);
        Snapshot.INSTANCE.notifyObjectsInitialized();
        this.isDrawingContent = true;
        try {
            CanvasHolder this_$iv = this.canvasHolder;
            Canvas previousCanvas$iv = this_$iv.getAndroidCanvas().getInternalCanvas();
            this_$iv.getAndroidCanvas().setInternalCanvas(canvas);
            androidx.compose.ui.graphics.Canvas $this$dispatchDraw_u24lambda_u240 = this_$iv.getAndroidCanvas();
            getRoot().draw$ui($this$dispatchDraw_u24lambda_u240, null);
            this_$iv.getAndroidCanvas().setInternalCanvas(previousCanvas$iv);
            if (this.dirtyLayers.isNotEmpty()) {
                int size = this.dirtyLayers.getSize();
                for (int i = 0; i < size; i++) {
                    OwnedLayer layer = this.dirtyLayers.get(i);
                    layer.updateDisplayList();
                }
            }
            if (ViewLayer.INSTANCE.getShouldUseDispatchDraw()) {
                int saveCount = canvas.save();
                canvas.clipRect(0.0f, 0.0f, 0.0f, 0.0f);
                super.dispatchDraw(canvas);
                canvas.restoreToCount(saveCount);
            }
            this.dirtyLayers.clear();
            this.isDrawingContent = false;
        } catch (Throwable t) {
            RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.uncaughtExceptionHandler;
            if (uncaughtExceptionHandler == null) {
                throw t;
            }
            uncaughtExceptionHandler.onUncaughtException(t);
        }
        if (this.postponedDirtyLayers != null) {
            MutableObjectList<OwnedLayer> mutableObjectList = this.postponedDirtyLayers;
            Intrinsics.checkNotNull(mutableObjectList);
            this.dirtyLayers.addAll(mutableObjectList);
            mutableObjectList.clear();
        }
        if (this.isArrEnabled) {
            Api35Impl.setRequestedFrameRate(this, this.currentFrameRate);
            View view2 = this.frameRateCategoryView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("frameRateCategoryView");
                view2 = null;
            }
            Api35Impl.setRequestedFrameRate(view2, this.currentFrameRateCategory);
            if (!Float.isNaN(this.currentFrameRateCategory)) {
                View view3 = this.frameRateCategoryView;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("frameRateCategoryView");
                    view3 = null;
                }
                view3.invalidate();
                View view4 = this.frameRateCategoryView;
                if (view4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("frameRateCategoryView");
                } else {
                    view = view4;
                }
                drawChild(canvas, view, getDrawingTime());
            }
            this.currentFrameRate = Float.NaN;
            this.currentFrameRateCategory = Float.NaN;
        }
        getRectManager().dispatchCallbacks();
    }

    public final void notifyLayerIsDirty$ui(OwnedLayer layer, boolean isDirty) {
        if (!isDirty) {
            if (!this.isDrawingContent) {
                this.dirtyLayers.remove(layer);
                MutableObjectList<OwnedLayer> mutableObjectList = this.postponedDirtyLayers;
                if (mutableObjectList != null) {
                    mutableObjectList.remove(layer);
                    return;
                }
                return;
            }
            return;
        }
        if (this.isDrawingContent) {
            MutableObjectList<OwnedLayer> mutableObjectList2 = this.postponedDirtyLayers;
            if (mutableObjectList2 == null) {
                mutableObjectList2 = new MutableObjectList<>(0, 1, null);
                this.postponedDirtyLayers = mutableObjectList2;
            }
            mutableObjectList2.add(layer);
            return;
        }
        this.dirtyLayers.add(layer);
    }

    public final void setOnViewTreeOwnersAvailable(Function1<? super ViewTreeOwners, Unit> callback) {
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null) {
            callback.invoke(viewTreeOwners);
        }
        if (!isAttachedToWindow()) {
            this.onViewTreeOwnersAvailable = callback;
        }
    }

    public final Object boundsUpdatesContentCaptureEventLoop(Continuation<? super Unit> continuation) {
        Object objBoundsUpdatesEventLoop$ui = this.contentCaptureManager.boundsUpdatesEventLoop$ui(continuation);
        return objBoundsUpdatesEventLoop$ui == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBoundsUpdatesEventLoop$ui : Unit.INSTANCE;
    }

    public final Object boundsUpdatesAccessibilityEventLoop(Continuation<? super Unit> continuation) {
        Object objBoundsUpdatesEventLoop$ui = this.composeAccessibilityDelegate.boundsUpdatesEventLoop$ui(continuation);
        return objBoundsUpdatesEventLoop$ui == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objBoundsUpdatesEventLoop$ui : Unit.INSTANCE;
    }

    private final void invalidateLayoutNodeMeasurement(LayoutNode node) {
        MeasureAndLayoutDelegate.requestRemeasure$default(this.measureAndLayoutDelegate, node, false, 2, null);
        MutableVector<LayoutNode> mutableVector = node.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            invalidateLayoutNodeMeasurement(it);
        }
    }

    private final void invalidateLayers(LayoutNode node) {
        node.invalidateLayers$ui();
        MutableVector<LayoutNode> mutableVector = node.get_children$ui();
        Object[] content$iv$iv = mutableVector.content;
        int size$iv$iv = mutableVector.getSize();
        for (int i$iv$iv = 0; i$iv$iv < size$iv$iv; i$iv$iv++) {
            LayoutNode it = (LayoutNode) content$iv$iv[i$iv$iv];
            invalidateLayers(it);
        }
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public void invalidateDescendants() {
        invalidateLayers(getRoot());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        LifecycleOwner lifecycleOwner;
        LifecycleOwner lifecycleOwner2;
        Lifecycle lifecycle;
        AndroidAutofill it;
        super.onAttachedToWindow();
        if (Build.VERSION.SDK_INT < 30) {
            setShowLayoutBounds(INSTANCE.getIsShowingLayoutBounds());
        }
        if (ComposeUiFlags.areWindowInsetsRulersEnabled) {
            this.insetsListener.onViewAttachedToWindow(this);
        }
        INSTANCE.addNotificationForSysPropsChange(this);
        this._windowInfo.setWindowFocused(hasWindowFocus());
        this._windowInfo.setOnInitializeContainerSize(new Function0<DerivedSize>() { // from class: androidx.compose.ui.platform.AndroidComposeView.onAttachedToWindow.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DerivedSize invoke() {
                return AndroidWindowInfo_androidKt.calculateWindowSize(AndroidComposeView.this);
            }
        });
        updateWindowMetrics();
        invalidateLayoutNodeMeasurement(getRoot());
        invalidateLayers(getRoot());
        getSnapshotObserver().startObserving$ui();
        if (autofillSupported() && (it = this._autofill) != null) {
            AutofillCallback.INSTANCE.register(it);
        }
        LifecycleOwner lifecycleOwner3 = ViewTreeLifecycleOwner.get(this);
        SavedStateRegistryOwner savedStateRegistryOwner = ViewTreeSavedStateRegistryOwner.get(this);
        ViewModelStoreOwner viewModelStoreOwner = ViewTreeViewModelStoreOwner.get(this);
        ForgetfulRetainedValuesStore forgetfulRetainedValuesStoreInstallLocalRetainedValuesStore = installLocalRetainedValuesStore(lifecycleOwner3, viewModelStoreOwner);
        if (forgetfulRetainedValuesStoreInstallLocalRetainedValuesStore == null) {
            forgetfulRetainedValuesStoreInstallLocalRetainedValuesStore = ForgetfulRetainedValuesStore.INSTANCE;
        }
        this.retainedValuesStore = forgetfulRetainedValuesStoreInstallLocalRetainedValuesStore;
        ViewTreeOwners oldViewTreeOwners = getViewTreeOwners();
        boolean resetViewTreeOwner = oldViewTreeOwners == null || !(lifecycleOwner3 == null || savedStateRegistryOwner == null || (lifecycleOwner3 == oldViewTreeOwners.getLifecycleOwner() && savedStateRegistryOwner == oldViewTreeOwners.getSavedStateRegistryOwner() && viewModelStoreOwner == oldViewTreeOwners.getViewModelStoreOwner()));
        Lifecycle lifecycle2 = null;
        if (resetViewTreeOwner) {
            if (lifecycleOwner3 == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
            }
            if (savedStateRegistryOwner == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
            }
            if (oldViewTreeOwners != null && (lifecycleOwner2 = oldViewTreeOwners.getLifecycleOwner()) != null && (lifecycle = lifecycleOwner2.getLifecycle()) != null) {
                lifecycle.removeObserver(this);
            }
            lifecycleOwner3.getLifecycle().addObserver(this);
            ViewTreeOwners viewTreeOwners = new ViewTreeOwners(lifecycleOwner3, savedStateRegistryOwner, viewModelStoreOwner);
            set_viewTreeOwners(viewTreeOwners);
            Function1<? super ViewTreeOwners, Unit> function1 = this.onViewTreeOwnersAvailable;
            if (function1 != null) {
                function1.invoke(viewTreeOwners);
            }
            this.onViewTreeOwnersAvailable = null;
        }
        this._inputModeManager.m5911setInputModeiuPiT84(isInTouchMode() ? InputMode.INSTANCE.m5908getTouchaOaMEAU() : InputMode.INSTANCE.m5907getKeyboardaOaMEAU());
        ViewTreeOwners viewTreeOwners2 = getViewTreeOwners();
        if (viewTreeOwners2 != null && (lifecycleOwner = viewTreeOwners2.getLifecycleOwner()) != null) {
            lifecycle2 = lifecycleOwner.getLifecycle();
        }
        if (lifecycle2 != null) {
            lifecycle2.addObserver(this);
            lifecycle2.addObserver(this.contentCaptureManager);
            getViewTreeObserver().addOnGlobalLayoutListener(this);
            getViewTreeObserver().addOnScrollChangedListener(this);
            getViewTreeObserver().addOnTouchModeChangeListener(this);
            if (Build.VERSION.SDK_INT >= 31) {
                AndroidComposeViewTranslationCallbackS.INSTANCE.setViewTranslationCallback(this);
            }
            AndroidAutofillManager it2 = this._autofillManager;
            if (it2 != null) {
                getFocusOwner().getListeners().add(it2);
                getSemanticsOwner().getListeners$ui().add(it2);
            }
            getFocusOwner().getListeners().add(this);
            return;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("No lifecycle owner exists");
        throw new KotlinNothingValueException();
    }

    private final RetainedValuesStore installLocalRetainedValuesStore(LifecycleOwner lifecycleOwner, ViewModelStoreOwner viewModelStoreOwner) {
        LifecycleRetainedValuesStoreOwner.FrameEndScheduler frameEndScheduler = this.frameEndScheduler;
        if (lifecycleOwner == null || viewModelStoreOwner == null || frameEndScheduler == null) {
            return null;
        }
        ViewModelProvider $this$get$iv = ViewModelProvider.Companion.create$default(ViewModelProvider.INSTANCE, viewModelStoreOwner.getViewModelStore(), new ViewModelProvider.NewInstanceFactory(), (CreationExtras) null, 4, (Object) null);
        LifecycleRetainedValuesStoreOwner retainedValuesStoreOwner = (LifecycleRetainedValuesStoreOwner) $this$get$iv.get(Reflection.getOrCreateKotlinClass(LifecycleRetainedValuesStoreOwner.class));
        Object parent = getParent();
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        int viewId = ((View) parent).getId();
        LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry retainedValuesStoreEntry = retainedValuesStoreOwner.getOrCreateRetainedValuesStoreEntry(viewId);
        this.lifecycleRetainedValuesStoreOwnerEntry = retainedValuesStoreEntry;
        return retainedValuesStoreEntry.getRetainedValuesStore();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        AndroidAutofill it;
        LifecycleOwner lifecycleOwner;
        super.onDetachedFromWindow();
        if (ComposeUiFlags.areWindowInsetsRulersEnabled) {
            this.insetsListener.onViewDetachedFromWindow(this);
        }
        if (this.isArrEnabled) {
            View view = this.frameRateCategoryView;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("frameRateCategoryView");
                view = null;
            }
            removeView(view);
        }
        INSTANCE.removeNotificationForSysPropsChange(this);
        getSnapshotObserver().stopObserving$ui();
        this._windowInfo.setOnInitializeContainerSize(null);
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        Lifecycle lifecycle = (viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null) ? null : lifecycleOwner.getLifecycle();
        if (lifecycle != null) {
            lifecycle.removeObserver(this.contentCaptureManager);
            lifecycle.removeObserver(this);
            if (autofillSupported() && (it = this._autofill) != null) {
                AutofillCallback.INSTANCE.unregister(it);
            }
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
            getViewTreeObserver().removeOnScrollChangedListener(this);
            getViewTreeObserver().removeOnTouchModeChangeListener(this);
            LifecycleRetainedValuesStoreOwner.RetainedValuesStoreEntry retainedValuesStoreEntry = this.lifecycleRetainedValuesStoreOwnerEntry;
            if (retainedValuesStoreEntry != null) {
                retainedValuesStoreEntry.release();
            }
            this.lifecycleRetainedValuesStoreOwnerEntry = null;
            if (Build.VERSION.SDK_INT >= 31) {
                AndroidComposeViewTranslationCallbackS.INSTANCE.clearViewTranslationCallback(this);
            }
            AndroidAutofillManager it2 = this._autofillManager;
            if (it2 != null) {
                getSemanticsOwner().getListeners$ui().remove(it2);
                getFocusOwner().getListeners().remove(it2);
            }
            getRectManager().removeScheduledCallback();
            getFocusOwner().getListeners().remove(this);
            return;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("No lifecycle owner exists");
        throw new KotlinNothingValueException();
    }

    @Override // android.view.View
    public void onProvideAutofillVirtualStructure(ViewStructure structure, int flags) {
        AndroidAutofillManager androidAutofillManager;
        if (autofillSupported() && structure != null) {
            if (ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
                androidAutofillManager.populateViewStructure(structure);
            }
            AndroidAutofill androidAutofill = this._autofill;
            if (androidAutofill != null) {
                AndroidAutofill_androidKt.populateViewStructure(androidAutofill, structure);
            }
        }
    }

    @Override // android.view.View
    public void autofill(SparseArray<AutofillValue> values) {
        AndroidAutofillManager androidAutofillManager;
        if (autofillSupported()) {
            if (ComposeUiFlags.isSemanticAutofillEnabled && (androidAutofillManager = this._autofillManager) != null) {
                androidAutofillManager.performAutofill(values);
            }
            AndroidAutofill androidAutofill = this._autofill;
            if (androidAutofill != null) {
                AndroidAutofill_androidKt.performAutofill(androidAutofill, values);
            }
        }
    }

    @Override // android.view.View
    public void onCreateVirtualViewTranslationRequests(long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
        this.contentCaptureManager.onCreateVirtualViewTranslationRequests$ui(virtualIds, supportedFormats, requestsCollector);
    }

    @Override // android.view.View
    public void onVirtualViewTranslationResponses(LongSparseArray<ViewTranslationResponse> response) {
        this.contentCaptureManager.onVirtualViewTranslationResponses$ui(this.contentCaptureManager, response);
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            if (motionEvent.getActionMasked() == 8) {
                this.hoverExitReceived = false;
            } else {
                this.sendHoverExitEvent.run();
            }
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        if (motionEvent.getActionMasked() == 8) {
            if (motionEvent.isFromSource(4194304)) {
                return handleRotaryEvent(motionEvent);
            }
            return (m6972handleMotionEvent8iAsVTc(motionEvent) & 1) != 0;
        }
        if (motionEvent.isFromSource(2097152)) {
            AndroidIndirectPointerEvent indirectPointerEvent = this.motionEventAdapter.m6329convertToIndirectPointerEventk92h6UU$ui(motionEvent, this.primaryDirectionalMotionAxisOverride);
            if (indirectPointerEvent != null) {
                if (handleIndirectPointerEvent(indirectPointerEvent)) {
                    return true;
                }
            } else {
                getFocusOwner().dispatchIndirectPointerCancel();
                this.indirectPointerNavigationGestureDetector.cancelCurrentEventStream();
                return true;
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    private final boolean handleIndirectPointerEvent(IndirectPointerEvent indirectPointerEvent) {
        boolean isConsumed = getFocusOwner().dispatchIndirectPointerEvent(indirectPointerEvent);
        if (ComposeUiFlags.isIndirectPointerNavigationGestureDetectorEnabled) {
            this.indirectPointerNavigationGestureDetector.onIndirectPointerEvent(indirectPointerEvent, isConsumed);
            return true;
        }
        return isConsumed;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        Object objM6992boximpl;
        FocusTargetNode activeFocusTargetNode;
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            MotionEvent lastEvent = this.previousMotionEvent;
            Intrinsics.checkNotNull(lastEvent);
            if (motionEvent.getActionMasked() != 0 || hasChangedDevices(motionEvent, lastEvent)) {
                this.sendHoverExitEvent.run();
            } else {
                this.hoverExitReceived = false;
            }
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        if (motionEvent.getActionMasked() == 2 && !isPositionChanged(motionEvent)) {
            return false;
        }
        int processResult = m6972handleMotionEvent8iAsVTc(motionEvent);
        if (((processResult & 2) != 0 ? 1 : 0) != 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        boolean isDown = motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 5;
        boolean isFromMouseOrTouchpad = motionEvent.isFromSource(8194) || motionEvent.isFromSource(InputDeviceCompat.SOURCE_TOUCHPAD);
        if (!isDown || !isFromMouseOrTouchpad) {
            z = true;
        } else {
            Object parent = getParent();
            View view = parent instanceof View ? (View) parent : null;
            if (view == null || (objM6992boximpl = view.getTag(R.id.auto_clear_focus_behavior_tag)) == null) {
                objM6992boximpl = AutoClearFocusBehavior.m6992boximpl(AutoClearFocusBehavior.INSTANCE.m7000getDefault4UtRPd4());
            }
            if (!Intrinsics.areEqual(objM6992boximpl, AutoClearFocusBehavior.m6992boximpl(AutoClearFocusBehavior.INSTANCE.m6999getCursorBased4UtRPd4())) || (activeFocusTargetNode = getFocusOwner().getActiveFocusTargetNode()) == null) {
                z = true;
            } else {
                Rect focusedNodeBounds = LayoutCoordinatesKt.boundsInRoot(DelegatableNodeKt.requireLayoutCoordinates(activeFocusTargetNode));
                float x$iv = motionEvent.getX();
                float y$iv = motionEvent.getY();
                long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                if (focusedNodeBounds.m4878containsk4lQ0M(Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)))) {
                    z = true;
                } else {
                    z = true;
                    FocusManager.clearFocus$default(getFocusOwner(), false, 1, null);
                }
            }
        }
        if ((processResult & 1) != 0) {
            return z;
        }
        return false;
    }

    private final boolean handleRotaryEvent(final MotionEvent event) {
        android.view.ViewConfiguration config = android.view.ViewConfiguration.get(getContext());
        float axisValue = -event.getAxisValue(26);
        RotaryScrollEvent rotaryEvent = new RotaryScrollEvent(ViewConfigurationCompat.getScaledVerticalScrollFactor(config, getContext()) * axisValue, ViewConfigurationCompat.getScaledHorizontalScrollFactor(config, getContext()) * axisValue, event.getEventTime(), event.getDeviceId());
        return getFocusOwner().dispatchRotaryEvent(rotaryEvent, new Function0<Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView.handleRotaryEvent.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(AndroidComposeView.super.dispatchGenericMotionEvent(event));
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:24:0x006e  */
    /* JADX WARN: Code duplicated, block: B:85:0x0123  */
    /* JADX INFO: renamed from: handleMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m6972handleMotionEvent8iAsVTc(MotionEvent motionEvent) {
        boolean z;
        int i;
        AndroidComposeView androidComposeView;
        boolean previousAndCurrentCoordinatesDoNotMatch;
        removeCallbacks(this.resendMotionEventRunnable);
        try {
            recalculateWindowPosition(motionEvent);
            this.forceUseMatrixCache = true;
            measureAndLayout(false);
            Trace.beginSection("AndroidOwner:onTouch");
            try {
                int action = motionEvent.getActionMasked();
                MotionEvent lastEvent = this.previousMotionEvent;
                if (lastEvent == null || lastEvent.getToolType(0) != 3) {
                    z = false;
                } else {
                    z = true;
                }
                boolean wasMouseEvent = z;
                if (lastEvent != null) {
                    try {
                        if (!hasChangedDevices(motionEvent, lastEvent)) {
                            i = 10;
                        } else if (!isDevicePressEvent(lastEvent)) {
                            if (lastEvent.getActionMasked() == 10 || !wasMouseEvent) {
                                i = 10;
                            } else {
                                i = 10;
                                sendSimulatedEvent$default(this, lastEvent, 10, lastEvent.getEventTime(), false, 8, null);
                            }
                        } else {
                            this.pointerInputEventProcessor.processCancel();
                            i = 10;
                        }
                    } catch (Throwable th) {
                        th = th;
                        Trace.endSection();
                        throw th;
                    }
                } else {
                    i = 10;
                }
                boolean isMouseEvent = motionEvent.getToolType(0) == 3;
                if (wasMouseEvent || !isMouseEvent || action == 3 || action == 9 || !isInBounds(motionEvent)) {
                    androidComposeView = this;
                } else {
                    androidComposeView = this;
                    sendSimulatedEvent$default(androidComposeView, motionEvent, 9, motionEvent.getEventTime(), false, 8, null);
                }
                if (lastEvent != 0) {
                    lastEvent.recycle();
                }
                MotionEvent motionEvent2 = androidComposeView.previousMotionEvent;
                if (motionEvent2 != null && motionEvent2.getAction() == i) {
                    MotionEvent motionEvent3 = androidComposeView.previousMotionEvent;
                    int previousEventDefaultPointerId = motionEvent3 != null ? motionEvent3.getPointerId(0) : -1;
                    if (motionEvent.getAction() == 9 && motionEvent.getHistorySize() == 0) {
                        if (previousEventDefaultPointerId >= 0) {
                            androidComposeView.motionEventAdapter.endStream(previousEventDefaultPointerId);
                        }
                    } else if (motionEvent.getAction() == 0 && motionEvent.getHistorySize() == 0) {
                        MotionEvent motionEvent4 = androidComposeView.previousMotionEvent;
                        float previousX = motionEvent4 != null ? motionEvent4.getX() : Float.NaN;
                        MotionEvent motionEvent5 = androidComposeView.previousMotionEvent;
                        float previousY = motionEvent5 != null ? motionEvent5.getY() : Float.NaN;
                        float currentX = motionEvent.getX();
                        float currentY = motionEvent.getY();
                        if (previousX == currentX) {
                            if (previousY == currentY) {
                                previousAndCurrentCoordinatesDoNotMatch = false;
                            } else {
                                previousAndCurrentCoordinatesDoNotMatch = true;
                            }
                        } else {
                            previousAndCurrentCoordinatesDoNotMatch = true;
                        }
                        MotionEvent motionEvent6 = androidComposeView.previousMotionEvent;
                        long previousEventTime = motionEvent6 != null ? motionEvent6.getEventTime() : -1L;
                        boolean previousAndCurrentEventTimesDoNotMatch = previousEventTime != motionEvent.getEventTime();
                        boolean previousHoverEventWasNotSyntheticallyProducedFromADownEvent = previousAndCurrentCoordinatesDoNotMatch || previousAndCurrentEventTimesDoNotMatch;
                        if (previousHoverEventWasNotSyntheticallyProducedFromADownEvent) {
                            if (previousEventDefaultPointerId >= 0) {
                                androidComposeView.motionEventAdapter.endStream(previousEventDefaultPointerId);
                            }
                            androidComposeView.pointerInputEventProcessor.clearPreviouslyHitModifierNodes();
                        }
                    }
                }
                androidComposeView.previousMotionEvent = MotionEvent.obtainNoHistory(motionEvent);
                int iM6977sendMotionEvent8iAsVTc = m6977sendMotionEvent8iAsVTc(motionEvent);
                Trace.endSection();
                androidComposeView.forceUseMatrixCache = false;
                return iM6977sendMotionEvent8iAsVTc;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            this.forceUseMatrixCache = false;
            throw th3;
        }
    }

    private final boolean hasChangedDevices(MotionEvent event, MotionEvent lastEvent) {
        return (lastEvent.getSource() == event.getSource() && lastEvent.getToolType(0) == event.getToolType(0)) ? false : true;
    }

    private final boolean isDevicePressEvent(MotionEvent event) {
        if (event.getButtonState() != 0) {
            return true;
        }
        switch (event.getActionMasked()) {
            case 0:
            case 2:
            case 6:
                return true;
            default:
                return false;
        }
    }

    /* JADX INFO: renamed from: sendMotionEvent-8iAsVTc, reason: not valid java name */
    private final int m6977sendMotionEvent8iAsVTc(MotionEvent motionEvent) {
        Object item$iv;
        if (this.keyboardModifiersRequireUpdate) {
            this.keyboardModifiersRequireUpdate = false;
            this._windowInfo.m7048setKeyboardModifiers5xRPYO0(PointerKeyboardModifiers.m6472constructorimpl(motionEvent.getMetaState()));
        }
        PointerInputEvent pointerInputEvent = this.motionEventAdapter.convertToPointerInputEvent$ui(motionEvent, this);
        int action = motionEvent.getActionMasked();
        if (pointerInputEvent == null) {
            this.pointerInputEventProcessor.processCancel();
            return PointerInputEventProcessorKt.ProcessResult(false, false, false);
        }
        List<PointerInputEventData> pointers = pointerInputEvent.getPointers();
        int size = pointers.size() - 1;
        if (size < 0) {
            item$iv = null;
            break;
        }
        while (true) {
            int index$iv = size;
            size--;
            item$iv = pointers.get(index$iv);
            PointerInputEventData it = (PointerInputEventData) item$iv;
            if (it.getDown() && (action == 0 || action == 5 || !ComposeUiFlags.isCanScrollUsingLastDownEventFixEnabled)) {
                break;
            }
            if (size < 0) {
                item$iv = null;
                break;
            }
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) item$iv;
        if (pointerInputEventData != null) {
            long it2 = pointerInputEventData.m6426getPositionF1C5BW0();
            this.lastDownPointerPosition = it2;
        }
        int result = this.pointerInputEventProcessor.m6431processBIzXfog(pointerInputEvent, this, isInBounds(motionEvent));
        pointerInputEvent.setMotionEvent(null);
        if (action != 0 && action != 5) {
            return result;
        }
        if ((result & 1) != 0) {
            return result;
        }
        this.motionEventAdapter.endStream(motionEvent.getPointerId(motionEvent.getActionIndex()));
        return result;
    }

    static /* synthetic */ void sendSimulatedEvent$default(AndroidComposeView androidComposeView, MotionEvent motionEvent, int i, long j, boolean z, int i2, Object obj) {
        boolean z2;
        if ((i2 & 8) == 0) {
            z2 = z;
        } else {
            z2 = true;
        }
        androidComposeView.sendSimulatedEvent(motionEvent, i, j, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSimulatedEvent(MotionEvent motionEvent, int action, long eventTime, boolean forceHover) {
        long downTime;
        MotionEvent motionEvent2 = motionEvent;
        int upIndex = -1;
        switch (motionEvent2.getActionMasked()) {
            case 1:
                switch (action) {
                    case 9:
                    case 10:
                        break;
                    default:
                        upIndex = 0;
                        break;
                }
                break;
            case 6:
                upIndex = motionEvent2.getActionIndex();
                break;
        }
        int pointerCount = motionEvent2.getPointerCount() - (upIndex >= 0 ? 1 : 0);
        if (pointerCount == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerProperties = new MotionEvent.PointerProperties[pointerCount];
        for (int i = 0; i < pointerCount; i++) {
            pointerProperties[i] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoords = new MotionEvent.PointerCoords[pointerCount];
        for (int i2 = 0; i2 < pointerCount; i2++) {
            pointerCoords[i2] = new MotionEvent.PointerCoords();
        }
        int i3 = 0;
        while (i3 < pointerCount) {
            int sourceIndex = ((upIndex < 0 || i3 < upIndex) ? 0 : 1) + i3;
            motionEvent2.getPointerProperties(sourceIndex, pointerProperties[i3]);
            MotionEvent.PointerCoords coords = pointerCoords[i3];
            motionEvent2.getPointerCoords(sourceIndex, coords);
            float x$iv = coords.x;
            float y$iv = coords.y;
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long localPosition = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            long screenPosition = mo6490localToScreenMKHz9U(localPosition);
            long localPosition2 = screenPosition >> 32;
            int bits$iv$iv$iv = (int) localPosition2;
            coords.x = Float.intBitsToFloat(bits$iv$iv$iv);
            int bits$iv$iv$iv2 = (int) (screenPosition & 4294967295L);
            coords.y = Float.intBitsToFloat(bits$iv$iv$iv2);
            i3++;
            motionEvent2 = motionEvent;
        }
        int buttonState = forceHover ? 0 : motionEvent.getButtonState();
        if (motionEvent.getDownTime() == motionEvent.getEventTime()) {
            downTime = eventTime;
        } else {
            downTime = motionEvent.getDownTime();
        }
        MotionEvent event = MotionEvent.obtain(downTime, eventTime, action, pointerCount, pointerProperties, pointerCoords, motionEvent.getMetaState(), buttonState, motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        PointerInputEvent pointerInputEvent = this.motionEventAdapter.convertToPointerInputEvent$ui(event, this);
        Intrinsics.checkNotNull(pointerInputEvent);
        this.pointerInputEventProcessor.m6431processBIzXfog(pointerInputEvent, this, true);
        event.recycle();
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int direction) {
        return this.composeAccessibilityDelegate.m6986canScroll0AR0LA0$ui(false, direction, this.lastDownPointerPosition);
    }

    @Override // android.view.View
    public boolean canScrollVertically(int direction) {
        return this.composeAccessibilityDelegate.m6986canScroll0AR0LA0$ui(true, direction, this.lastDownPointerPosition);
    }

    private final boolean isInBounds(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (0.0f <= x && x <= ((float) getWidth())) {
            if (0.0f <= y && y <= ((float) getHeight())) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U */
    public long mo6490localToScreenMKHz9U(long localPosition) {
        recalculateWindowPosition();
        long local = Matrix.m5347mapMKHz9U(this.viewToWindowMatrix, localPosition);
        int bits$iv$iv$iv = (int) (local >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (this.windowPosition >> 32);
        float x$iv = fIntBitsToFloat + Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) (local & 4294967295L);
        float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv3);
        int bits$iv$iv$iv4 = (int) (this.windowPosition & 4294967295L);
        float y$iv = fIntBitsToFloat2 + Float.intBitsToFloat(bits$iv$iv$iv4);
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        return Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    @Override // androidx.compose.ui.input.pointer.MatrixPositionCalculator
    /* JADX INFO: renamed from: localToScreen-58bKbWc */
    public void mo6326localToScreen58bKbWc(float[] localTransform) {
        recalculateWindowPosition();
        Matrix.m5360timesAssign58bKbWc(localTransform, this.viewToWindowMatrix);
        int bits$iv$iv$iv = (int) (this.windowPosition >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (4294967295L & this.windowPosition);
        AndroidComposeView_androidKt.m6991preTranslatecG2Xzmc(localTransform, fIntBitsToFloat, Float.intBitsToFloat(bits$iv$iv$iv2), this.tmpMatrix);
    }

    @Override // androidx.compose.ui.input.pointer.PositionCalculator
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo6491screenToLocalMKHz9U(long positionOnScreen) {
        recalculateWindowPosition();
        int bits$iv$iv$iv = (int) (positionOnScreen >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (this.windowPosition >> 32);
        float x = fIntBitsToFloat - Float.intBitsToFloat(bits$iv$iv$iv2);
        int bits$iv$iv$iv3 = (int) (positionOnScreen & 4294967295L);
        float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv3);
        int bits$iv$iv$iv4 = (int) (this.windowPosition & 4294967295L);
        float y = fIntBitsToFloat2 - Float.intBitsToFloat(bits$iv$iv$iv4);
        float[] fArr = this.windowToViewMatrix;
        long v1$iv$iv = Float.floatToRawIntBits(x);
        long v2$iv$iv = Float.floatToRawIntBits(y);
        return Matrix.m5347mapMKHz9U(fArr, Offset.m4844constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv)));
    }

    private final void recalculateWindowPosition() {
        if (!this.forceUseMatrixCache) {
            long animationTime = AnimationUtils.currentAnimationTimeMillis();
            if (animationTime != this.lastMatrixRecalculationAnimationTime) {
                this.lastMatrixRecalculationAnimationTime = animationTime;
                recalculateWindowViewTransforms();
                ViewParent viewParent = getParent();
                AndroidComposeView view = this;
                while (viewParent instanceof ViewGroup) {
                    view = (View) viewParent;
                    viewParent = ((ViewGroup) view).getParent();
                }
                view.getLocationOnScreen(this.tmpPositionArray);
                float screenX = this.tmpPositionArray[0];
                float screenY = this.tmpPositionArray[1];
                view.getLocationInWindow(this.tmpPositionArray);
                float windowX = this.tmpPositionArray[0];
                float windowY = this.tmpPositionArray[1];
                float x$iv = screenX - windowX;
                float y$iv = screenY - windowY;
                long animationTime2 = Float.floatToRawIntBits(x$iv);
                long v1$iv$iv = Float.floatToRawIntBits(y$iv);
                long v2$iv$iv = Offset.m4844constructorimpl((animationTime2 << 32) | (v1$iv$iv & 4294967295L));
                this.windowPosition = v2$iv$iv;
            }
        }
    }

    private final void recalculateWindowPosition(MotionEvent motionEvent) {
        this.lastMatrixRecalculationAnimationTime = AnimationUtils.currentAnimationTimeMillis();
        recalculateWindowViewTransforms();
        float[] fArr = this.viewToWindowMatrix;
        float x$iv = motionEvent.getX();
        float y$iv = motionEvent.getY();
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long positionInWindow = Matrix.m5347mapMKHz9U(fArr, Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L)));
        int bits$iv$iv$iv = (int) (positionInWindow >> 32);
        float x$iv2 = motionEvent.getRawX() - Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (positionInWindow & 4294967295L);
        float y$iv2 = motionEvent.getRawY() - Float.intBitsToFloat(bits$iv$iv$iv2);
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        this.windowPosition = Offset.m4844constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2));
    }

    private final void recalculateWindowViewTransforms() {
        this.matrixToWindow.mo7002calculateMatrixToWindowEL8BTi8(this, this.viewToWindowMatrix);
        InvertMatrixKt.m7039invertToJiSxe2E(this.viewToWindowMatrix, this.windowToViewMatrix);
    }

    private final void updateWindowMetrics() {
        LazyWindowInfo this_$iv = this._windowInfo;
        MutableState it$iv = this_$iv._containerSize;
        if (it$iv == null) {
            return;
        }
        it$iv.setValue(AndroidWindowInfo_androidKt.calculateWindowSize(this));
    }

    @Override // android.view.View
    public boolean onCheckIsTextEditor() {
        AndroidPlatformTextInputSession parentSession = (AndroidPlatformTextInputSession) SessionMutex.m4589getCurrentSessionimpl(this.textInputSessionMutex);
        if (parentSession == null) {
            return this.legacyTextInputServiceAndroid.getEditorHasFocus();
        }
        return parentSession.isReadyForConnection();
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        AndroidPlatformTextInputSession parentSession = (AndroidPlatformTextInputSession) SessionMutex.m4589getCurrentSessionimpl(this.textInputSessionMutex);
        if (parentSession == null) {
            return this.legacyTextInputServiceAndroid.createInputConnection(outAttrs);
        }
        return parentSession.createInputConnection(outAttrs);
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: calculateLocalPosition-MK-Hz9U */
    public long mo6924calculateLocalPositionMKHz9U(long positionInWindow) {
        recalculateWindowPosition();
        return Matrix.m5347mapMKHz9U(this.windowToViewMatrix, positionInWindow);
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: calculatePositionInWindow-MK-Hz9U */
    public long mo6925calculatePositionInWindowMKHz9U(long localPosition) {
        recalculateWindowPosition();
        return Matrix.m5347mapMKHz9U(this.viewToWindowMatrix, localPosition);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateConfiguration(newConfig);
    }

    private final void dispatchConfigurationChangeIfNeeded() {
        int i = Build.VERSION.SDK_INT;
        boolean z = false;
        if (32 <= i && i < 34) {
            z = true;
        }
        if (z) {
            updateConfiguration(getResources().getConfiguration());
        }
    }

    private final void updateConfiguration(Configuration newConfig) {
        Configuration oldConfig = getConfiguration();
        if (!Intrinsics.areEqual(oldConfig, newConfig)) {
            setConfiguration(new Configuration(newConfig));
            if (!(oldConfig.fontScale == newConfig.fontScale) || oldConfig.densityDpi != newConfig.densityDpi) {
                setDensity(AndroidDensity_androidKt.Density(getContext()));
            }
            if (AndroidComposeView_androidKt.diffForWindowMetricsChanged(oldConfig, newConfig)) {
                updateWindowMetrics();
            }
            if (getFontWeightAdjustmentCompat(oldConfig) != getFontWeightAdjustmentCompat(newConfig)) {
                setFontFamilyResolver(FontFamilyResolver_androidKt.createFontFamilyResolver(getContext()));
            }
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int layoutDirection) {
        if (this.superclassInitComplete) {
            LayoutDirection layoutDirection2 = FocusInteropUtils_androidKt.toLayoutDirection(layoutDirection);
            if (layoutDirection2 == null) {
                layoutDirection2 = LayoutDirection.Ltr;
            }
            setLayoutDirection(layoutDirection2);
        }
    }

    private final boolean autofillSupported() {
        return Build.VERSION.SDK_INT >= 26;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchHoverEvent(MotionEvent event) {
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            this.sendHoverExitEvent.run();
        }
        if (isBadMotionEvent(event) || !isAttachedToWindow()) {
            return false;
        }
        this.composeAccessibilityDelegate.dispatchHoverEvent$ui(event);
        switch (event.getActionMasked()) {
            case 7:
                if (!isPositionChanged(event)) {
                    return false;
                }
                break;
            case 10:
                if (isInBounds(event)) {
                    if (event.getToolType(0) == 3 && event.getButtonState() != 0) {
                        return false;
                    }
                    MotionEvent motionEvent = this.previousMotionEvent;
                    if (motionEvent != null) {
                        motionEvent.recycle();
                    }
                    this.previousMotionEvent = MotionEvent.obtainNoHistory(event);
                    this.hoverExitReceived = true;
                    postDelayed(this.sendHoverExitEvent, 8L);
                    return false;
                }
                break;
        }
        int result = m6972handleMotionEvent8iAsVTc(event);
        return (result & 1) != 0;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004d  */
    /* JADX WARN: Code duplicated, block: B:44:0x008b  */
    private final boolean isBadMotionEvent(MotionEvent event) {
        boolean eventInvalid;
        boolean z;
        float $this$fastIsFinite$iv = event.getX();
        if ((Float.floatToRawIntBits($this$fastIsFinite$iv) & Integer.MAX_VALUE) < 2139095040) {
            float $this$fastIsFinite$iv2 = event.getY();
            if ((Float.floatToRawIntBits($this$fastIsFinite$iv2) & Integer.MAX_VALUE) < 2139095040) {
                float $this$fastIsFinite$iv3 = event.getRawX();
                if ((Float.floatToRawIntBits($this$fastIsFinite$iv3) & Integer.MAX_VALUE) < 2139095040) {
                    float $this$fastIsFinite$iv4 = event.getRawY();
                    if ((Float.floatToRawIntBits($this$fastIsFinite$iv4) & Integer.MAX_VALUE) < 2139095040) {
                        eventInvalid = false;
                    } else {
                        eventInvalid = true;
                    }
                } else {
                    eventInvalid = true;
                }
            } else {
                eventInvalid = true;
            }
        } else {
            eventInvalid = true;
        }
        if (!eventInvalid) {
            int pointerCount = event.getPointerCount();
            for (int index = 1; index < pointerCount; index++) {
                float $this$fastIsFinite$iv5 = event.getX(index);
                if ((Float.floatToRawIntBits($this$fastIsFinite$iv5) & Integer.MAX_VALUE) < 2139095040) {
                    float $this$fastIsFinite$iv6 = event.getY(index);
                    if (!((Float.floatToRawIntBits($this$fastIsFinite$iv6) & Integer.MAX_VALUE) < 2139095040) || (Build.VERSION.SDK_INT >= 29 && !MotionEventVerifierApi29.INSTANCE.isValidMotionEvent(event, index))) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = true;
                }
                eventInvalid = z;
                if (eventInvalid) {
                    break;
                }
            }
        }
        return eventInvalid;
    }

    private final boolean isPositionChanged(MotionEvent event) {
        MotionEvent lastEvent;
        if (event.getPointerCount() != 1 || (lastEvent = this.previousMotionEvent) == null || lastEvent.getPointerCount() != event.getPointerCount()) {
            return true;
        }
        if (event.getRawX() == lastEvent.getRawX()) {
            return !((event.getRawY() > lastEvent.getRawY() ? 1 : (event.getRawY() == lastEvent.getRawY() ? 0 : -1)) == 0);
        }
        return true;
    }

    private final View findViewByAccessibilityIdRootedAtCurrentView(int accessibilityId, View currentView) throws NoSuchMethodException {
        if (Build.VERSION.SDK_INT < 29) {
            Method getAccessibilityViewIdMethod = Class.forName(AndroidComposeViewAccessibilityDelegateCompat.ClassName).getDeclaredMethod("getAccessibilityViewId", new Class[0]);
            getAccessibilityViewIdMethod.setAccessible(true);
            if (Intrinsics.areEqual(getAccessibilityViewIdMethod.invoke(currentView, new Object[0]), Integer.valueOf(accessibilityId))) {
                return currentView;
            }
            if (currentView instanceof ViewGroup) {
                int childCount = ((ViewGroup) currentView).getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View foundView = findViewByAccessibilityIdRootedAtCurrentView(accessibilityId, ((ViewGroup) currentView).getChildAt(i));
                    if (foundView != null) {
                        return foundView;
                    }
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public android.view.PointerIcon onResolvePointerIcon(MotionEvent event, int pointerIndex) {
        PointerIcon icon;
        int toolType = event.getToolType(pointerIndex);
        if (!event.isFromSource(8194) && event.isFromSource(InputDeviceCompat.SOURCE_STYLUS) && ((toolType == 2 || toolType == 4) && (icon = getPointerIconService().getCurrentStylusHoverIcon()) != null)) {
            return AndroidComposeViewVerificationHelperMethodsN.INSTANCE.toAndroidPointerIcon(getContext(), icon);
        }
        return super.onResolvePointerIcon(event, pointerIndex);
    }

    @Override // androidx.compose.ui.node.Owner
    public PointerIconService getPointerIconService() {
        return this.pointerIconService;
    }

    public final View findViewByAccessibilityIdTraversal(int accessibilityId) throws IllegalAccessException, InvocationTargetException {
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                Method findViewByAccessibilityIdTraversalMethod = Class.forName(AndroidComposeViewAccessibilityDelegateCompat.ClassName).getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
                findViewByAccessibilityIdTraversalMethod.setAccessible(true);
                Object objInvoke = findViewByAccessibilityIdTraversalMethod.invoke(this, Integer.valueOf(accessibilityId));
                if (objInvoke instanceof View) {
                    return (View) objInvoke;
                }
                return null;
            }
            return findViewByAccessibilityIdRootedAtCurrentView(accessibilityId, this);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Override // androidx.compose.ui.platform.ViewRootForTest
    public boolean isLifecycleInResumedState() {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycle;
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        return ((viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null || (lifecycle = lifecycleOwner.getLifecycle()) == null) ? null : lifecycle.getState()) == Lifecycle.State.RESUMED;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // androidx.compose.ui.node.Owner
    public void incrementSensitiveComponentCount() {
        if (Build.VERSION.SDK_INT >= 35) {
            if (this.sensitiveComponentCount == 0) {
                AndroidComposeViewSensitiveContent35.INSTANCE.setContentSensitivity(getView(), true);
            }
            this.sensitiveComponentCount++;
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void decrementSensitiveComponentCount() {
        if (Build.VERSION.SDK_INT >= 35) {
            if (this.sensitiveComponentCount == 1) {
                AndroidComposeViewSensitiveContent35.INSTANCE.setContentSensitivity(getView(), false);
            }
            this.sensitiveComponentCount--;
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void incrementKeepScreenOnCount() {
        this.keepScreenOnCount++;
        getView().setKeepScreenOn(this.keepScreenOnCount > 0);
    }

    @Override // androidx.compose.ui.node.Owner
    public void decrementKeepScreenOnCount() {
        this.keepScreenOnCount--;
        getView().setKeepScreenOn(this.keepScreenOnCount > 0);
    }

    @Override // androidx.compose.ui.node.Owner
    public AndroidComposeView getOutOfFrameExecutor() {
        if (isAttachedToWindow()) {
            return this;
        }
        return null;
    }

    @Override // androidx.compose.ui.node.OutOfFrameExecutor
    public void schedule(Function0<Unit> block) {
        boolean shouldSchedule = this.outOfFrameQueue.isEmpty();
        this.outOfFrameQueue.addLast(block);
        if (shouldSchedule) {
            Handler handler = getHandler();
            if (handler == null) {
                throw new IllegalArgumentException("schedule is called when outOfFrameExecutor is not available (view is detached)".toString());
            }
            handler.postAtFrontOfQueue(this.outOfFrameRunnable);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public void voteFrameRate(float frameRate) {
        if (this.isArrEnabled) {
            if (frameRate > 0.0f) {
                if (Float.isNaN(this.currentFrameRate) || frameRate > this.currentFrameRate) {
                    this.currentFrameRate = frameRate;
                    return;
                }
                return;
            }
            if (frameRate < 0.0f) {
                if (Float.isNaN(this.currentFrameRateCategory) || frameRate < this.currentFrameRateCategory) {
                    this.currentFrameRateCategory = frameRate;
                }
            }
        }
    }

    @Override // androidx.compose.ui.node.Owner
    /* JADX INFO: renamed from: dispatchOnScrollChanged-k-4lQ0M */
    public void mo6926dispatchOnScrollChangedk4lQ0M(long delta) {
        INSTANCE.dispatchOnScrollChanged(getViewTreeObserver());
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.lastMatrixRecalculationAnimationTime = 0L;
        updatePositionCacheAndDispatch();
        dispatchConfigurationChangeIfNeeded();
    }

    @Override // android.view.ViewTreeObserver.OnScrollChangedListener
    public void onScrollChanged() {
        updatePositionCacheAndDispatch();
    }

    @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
    public void onTouchModeChanged(boolean isInTouchMode) {
        InputModeManagerImpl inputModeManagerImpl = this._inputModeManager;
        InputMode.Companion companion = InputMode.INSTANCE;
        inputModeManagerImpl.m5911setInputModeiuPiT84(isInTouchMode ? companion.m5908getTouchaOaMEAU() : companion.m5907getKeyboardaOaMEAU());
    }

    /* JADX INFO: compiled from: AndroidComposeView.android.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0007R\u0014\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$Companion;", "", "<init>", "()V", "systemPropertiesClass", "Ljava/lang/Class;", "getBooleanMethod", "Ljava/lang/reflect/Method;", "addChangeCallbackMethod", "composeViews", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/platform/AndroidComposeView;", "systemPropertiesChangedRunnable", "Ljava/lang/Runnable;", "dispatchOnScrollChangedMethod", "getIsShowingLayoutBounds", "", "addNotificationForSysPropsChange", "", "composeView", "removeNotificationForSysPropsChange", "dispatchOnScrollChanged", "viewTreeObserver", "Landroid/view/ViewTreeObserver;", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean getIsShowingLayoutBounds() {
            try {
                if (AndroidComposeView.systemPropertiesClass == null) {
                    AndroidComposeView.systemPropertiesClass = Class.forName("android.os.SystemProperties");
                }
                if (AndroidComposeView.getBooleanMethod == null) {
                    Class cls = AndroidComposeView.systemPropertiesClass;
                    AndroidComposeView.getBooleanMethod = cls != null ? cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE) : null;
                }
                Method method = AndroidComposeView.getBooleanMethod;
                Object objInvoke = method != null ? method.invoke(null, "debug.layout", false) : null;
                return Intrinsics.areEqual((Object) (objInvoke instanceof Boolean ? (Boolean) objInvoke : null), (Object) true);
            } catch (Exception e) {
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addNotificationForSysPropsChange(AndroidComposeView composeView) {
            if (Build.VERSION.SDK_INT > 28) {
                if (AndroidComposeView.systemPropertiesChangedRunnable == null) {
                    Runnable runnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$Companion$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            AndroidComposeView.Companion.addNotificationForSysPropsChange$lambda$0();
                        }
                    };
                    AndroidComposeView.systemPropertiesChangedRunnable = runnable;
                    StrictMode.VmPolicy origPolicy = StrictMode.getVmPolicy();
                    try {
                        if (AndroidComposeView.systemPropertiesClass == null) {
                            AndroidComposeView.systemPropertiesClass = Class.forName("android.os.SystemProperties");
                        }
                        if (AndroidComposeView.addChangeCallbackMethod == null) {
                            StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
                            Class cls = AndroidComposeView.systemPropertiesClass;
                            AndroidComposeView.addChangeCallbackMethod = cls != null ? cls.getDeclaredMethod("addChangeCallback", Runnable.class) : null;
                        }
                        Method method = AndroidComposeView.addChangeCallbackMethod;
                        if (method != null) {
                            method.invoke(null, runnable);
                        }
                    } catch (Throwable th) {
                    }
                    StrictMode.setVmPolicy(origPolicy);
                }
                Object lock$iv = AndroidComposeView.composeViews;
                synchronized (lock$iv) {
                    MutableObjectList this_$iv = AndroidComposeView.composeViews;
                    this_$iv.add(composeView);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        static final void addNotificationForSysPropsChange$lambda$0() {
            Object lock$iv = AndroidComposeView.composeViews;
            synchronized (lock$iv) {
                if (Build.VERSION.SDK_INT < 30) {
                    ObjectList this_$iv = AndroidComposeView.composeViews;
                    Object[] content$iv = this_$iv.content;
                    int i = this_$iv._size;
                    for (int i$iv = 0; i$iv < i; i$iv++) {
                        AndroidComposeView it = (AndroidComposeView) content$iv[i$iv];
                        boolean oldValue = it.getShowLayoutBounds();
                        it.setShowLayoutBounds(AndroidComposeView.INSTANCE.getIsShowingLayoutBounds());
                        if (oldValue != it.getShowLayoutBounds()) {
                            it.invalidateDescendants();
                        }
                    }
                } else {
                    ObjectList this_$iv2 = AndroidComposeView.composeViews;
                    Object[] content$iv2 = this_$iv2.content;
                    int i2 = this_$iv2._size;
                    for (int i$iv2 = 0; i$iv2 < i2; i$iv2++) {
                        ((AndroidComposeView) content$iv2[i$iv2]).invalidateDescendants();
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void removeNotificationForSysPropsChange(AndroidComposeView composeView) {
            if (Build.VERSION.SDK_INT > 28) {
                Object lock$iv = AndroidComposeView.composeViews;
                synchronized (lock$iv) {
                    MutableObjectList this_$iv = AndroidComposeView.composeViews;
                    this_$iv.remove(composeView);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        public final void dispatchOnScrollChanged(ViewTreeObserver viewTreeObserver) {
            try {
                if (AndroidComposeView.dispatchOnScrollChangedMethod == null) {
                    Method it = viewTreeObserver.getClass().getDeclaredMethod("dispatchOnScrollChanged", new Class[0]);
                    it.setAccessible(true);
                    AndroidComposeView.dispatchOnScrollChangedMethod = it;
                }
                Method method = AndroidComposeView.dispatchOnScrollChangedMethod;
                if (method != null) {
                    method.invoke(viewTreeObserver, new Object[0]);
                }
            } catch (Exception e) {
            }
        }
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        INSTANCE = new Companion(defaultConstructorMarker);
        composeViews = new MutableObjectList<>(0, 1, defaultConstructorMarker);
    }

    /* JADX INFO: compiled from: AndroidComposeView.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$ViewTreeOwners;", "", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "savedStateRegistryOwner", "Landroidx/savedstate/SavedStateRegistryOwner;", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/savedstate/SavedStateRegistryOwner;Landroidx/lifecycle/ViewModelStoreOwner;)V", "getLifecycleOwner", "()Landroidx/lifecycle/LifecycleOwner;", "getSavedStateRegistryOwner", "()Landroidx/savedstate/SavedStateRegistryOwner;", "getViewModelStoreOwner", "()Landroidx/lifecycle/ViewModelStoreOwner;", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class ViewTreeOwners {
        public static final int $stable = 8;
        private final LifecycleOwner lifecycleOwner;
        private final SavedStateRegistryOwner savedStateRegistryOwner;
        private final ViewModelStoreOwner viewModelStoreOwner;

        public ViewTreeOwners(LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner, ViewModelStoreOwner viewModelStoreOwner) {
            this.lifecycleOwner = lifecycleOwner;
            this.savedStateRegistryOwner = savedStateRegistryOwner;
            this.viewModelStoreOwner = viewModelStoreOwner;
        }

        public final LifecycleOwner getLifecycleOwner() {
            return this.lifecycleOwner;
        }

        public final SavedStateRegistryOwner getSavedStateRegistryOwner() {
            return this.savedStateRegistryOwner;
        }

        public final ViewModelStoreOwner getViewModelStoreOwner() {
            return this.viewModelStoreOwner;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AndroidComposeView.android.kt */
    @Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\bB\u0007¢\u0006\u0004\b\t\u0010\nJ#\u00103\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0016¢\u0006\u0004\b:\u0010;J\f\u0010?\u001a\u00020-*\u00020@H\u0016J&\u0010A\u001a\u00020-2\u0006\u0010B\u001a\u00020C2\u000e\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010F0EH\u0096@¢\u0006\u0002\u0010GJ\u0010\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020KH\u0016J\u0010\u0010L\u001a\u00020I2\u0006\u0010J\u001a\u00020KH\u0016J\u0017\u0010M\u001a\u00020I2\u0006\u0010J\u001a\u00020NH\u0016¢\u0006\u0004\bO\u0010PJ\u0017\u0010Q\u001a\u00020I2\u0006\u0010J\u001a\u00020NH\u0016¢\u0006\u0004\bR\u0010PR \u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0014\u0010&\u001a\u00020'8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R(\u0010*\u001a\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+¢\u0006\u0002\b.¢\u0006\u000e\n\u0000\u0012\u0004\b/\u00100\u001a\u0004\b1\u00102R\u0014\u0010<\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>¨\u0006S"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView$RootModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/relocation/BringIntoViewModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/input/rotary/RotaryInputModifierNode;", "Landroidx/compose/ui/input/key/KeyInputModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/layout/WindowInsetsRulerProvider;", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "insetsValues", "Landroidx/collection/ScatterMap;", "", "Landroidx/compose/ui/layout/WindowWindowInsetsAnimationValues;", "getInsetsValues", "()Landroidx/collection/ScatterMap;", "generation", "Landroidx/compose/runtime/MutableIntState;", "getGeneration", "()Landroidx/compose/runtime/MutableIntState;", "previousGeneration", "", "getPreviousGeneration", "()I", "setPreviousGeneration", "(I)V", "cutoutRects", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/runtime/MutableState;", "Landroid/graphics/Rect;", "getCutoutRects", "()Landroidx/collection/MutableObjectList;", "cutoutRulers", "", "Landroidx/compose/ui/layout/RectRulers;", "getCutoutRulers", "()Ljava/util/List;", "insetsListener", "Landroidx/compose/ui/layout/InsetsListener;", "getInsetsListener", "()Landroidx/compose/ui/layout/InsetsListener;", "rulerLambda", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/RulerScope;", "", "Lkotlin/ExtensionFunctionType;", "getRulerLambda$annotations", "()V", "getRulerLambda", "()Lkotlin/jvm/functions/Function1;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "traverseKey", "getTraverseKey", "()Ljava/lang/Object;", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "bringIntoView", "childCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "boundsProvider", "Lkotlin/Function0;", "Landroidx/compose/ui/geometry/Rect;", "(Landroidx/compose/ui/layout/LayoutCoordinates;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onRotaryScrollEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/rotary/RotaryScrollEvent;", "onPreRotaryScrollEvent", "onPreKeyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onPreKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onKeyEvent", "onKeyEvent-ZmokQxo", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
    final class RootModifierNode extends Modifier.Node implements BringIntoViewModifierNode, SemanticsModifierNode, RotaryInputModifierNode, KeyInputModifierNode, LayoutModifierNode, TraversableNode, WindowInsetsRulerProvider {
        private int previousGeneration = -1;
        private final Function1<RulerScope, Unit> rulerLambda = new Function1<RulerScope, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$rulerLambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RulerScope rulerScope) {
                invoke2(rulerScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RulerScope rulerScope) {
                this.this$0.setPreviousGeneration(this.this$0.getGeneration().getIntValue());
                if (this.this$0.getPreviousGeneration() > 0 && ComposeUiFlags.areWindowInsetsRulersEnabled) {
                    WindowInsetsRulers_androidKt.provideWindowInsetsRulers(rulerScope, this.this$0);
                }
            }
        };

        public static /* synthetic */ void getRulerLambda$annotations() {
        }

        public RootModifierNode() {
        }

        @Override // androidx.compose.ui.layout.WindowInsetsRulerProvider
        public ScatterMap<Object, WindowWindowInsetsAnimationValues> getInsetsValues() {
            return getInsetsListener().getInsetsValues();
        }

        public final MutableIntState getGeneration() {
            return getInsetsListener().getGeneration();
        }

        public final int getPreviousGeneration() {
            return this.previousGeneration;
        }

        public final void setPreviousGeneration(int i) {
            this.previousGeneration = i;
        }

        @Override // androidx.compose.ui.layout.WindowInsetsRulerProvider
        public MutableObjectList<MutableState<android.graphics.Rect>> getCutoutRects() {
            return getInsetsListener().getDisplayCutouts();
        }

        @Override // androidx.compose.ui.layout.WindowInsetsRulerProvider
        public List<RectRulers> getCutoutRulers() {
            return getInsetsListener().getDisplayCutoutRulers();
        }

        @Override // androidx.compose.ui.layout.WindowInsetsRulerProvider
        public InsetsListener getInsetsListener() {
            return AndroidComposeView.this.getInsetsListener();
        }

        public final Function1<RulerScope, Unit> getRulerLambda() {
            return this.rulerLambda;
        }

        @Override // androidx.compose.ui.node.LayoutModifierNode
        /* JADX INFO: renamed from: measure-3p2s80s */
        public MeasureResult mo70measure3p2s80s(MeasureScope $this$measure_u2d3p2s80s, Measurable measurable, long j) {
            final Placeable placeable = measurable.mo6541measureBRTryo0(j);
            int width = placeable.getWidth();
            int height = placeable.getHeight();
            return MeasureScope.layout$default($this$measure_u2d3p2s80s, width, height, null, this.rulerLambda, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$measure$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope $this$layout) {
                    Placeable.PlacementScope.place$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
                }
            }, 4, null);
        }

        @Override // androidx.compose.ui.node.TraversableNode
        public Object getTraverseKey() {
            return WindowInsetsRulers_androidKt.RulerKey;
        }

        @Override // androidx.compose.ui.node.SemanticsModifierNode
        public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        }

        @Override // androidx.compose.ui.relocation.BringIntoViewModifierNode
        public Object bringIntoView(LayoutCoordinates childCoordinates, Function0<Rect> function0, Continuation<? super Unit> continuation) {
            long childOffset = LayoutCoordinatesKt.positionInRoot(childCoordinates);
            Rect rectInvoke = function0.invoke();
            Rect rootRect = rectInvoke != null ? rectInvoke.m4889translatek4lQ0M(childOffset) : null;
            if (rootRect != null) {
                AndroidComposeView.this.requestRectangleOnScreen(RectHelper_androidKt.toAndroidRect(rootRect), false);
            }
            return Unit.INSTANCE;
        }

        @Override // androidx.compose.ui.input.rotary.RotaryInputModifierNode
        public boolean onRotaryScrollEvent(RotaryScrollEvent event) {
            return false;
        }

        @Override // androidx.compose.ui.input.rotary.RotaryInputModifierNode
        public boolean onPreRotaryScrollEvent(RotaryScrollEvent event) {
            return false;
        }

        @Override // androidx.compose.ui.input.key.KeyInputModifierNode
        /* JADX INFO: renamed from: onPreKeyEvent-ZmokQxo */
        public boolean mo235onPreKeyEventZmokQxo(KeyEvent event) {
            return false;
        }

        @Override // androidx.compose.ui.input.key.KeyInputModifierNode
        /* JADX INFO: renamed from: onKeyEvent-ZmokQxo */
        public boolean mo233onKeyEventZmokQxo(KeyEvent event) {
            Boolean boolMo4746focusSearchULY8qGw;
            final FocusDirection focusDirection = FocusInteropUtils_androidKt.m4740toFocusDirectionZmokQxo(event);
            if (focusDirection == null || !KeyEventType.m6246equalsimpl0(KeyEvent_androidKt.m6254getTypeZmokQxo(event), KeyEventType.INSTANCE.m6250getKeyDownCS__XNY())) {
                return false;
            }
            if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
                FocusTargetNode activeFocusTargetNode = AndroidComposeView.this.getFocusOwner().getActiveFocusTargetNode();
                if ((activeFocusTargetNode != null && activeFocusTargetNode.getIsInteropViewHost()) && AndroidComposeView.this.mo4793moveFocusInChildren3ESFkO8(focusDirection.getValue())) {
                    return true;
                }
                Boolean boolMo4746focusSearchULY8qGw2 = AndroidComposeView.this.getFocusOwner().mo4746focusSearchULY8qGw(focusDirection.getValue(), AndroidComposeView.this.getEmbeddedViewFocusRect(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$onKeyEvent$focusWasMovedOrCancelled$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(FocusTargetNode it) {
                        return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection.getValue()));
                    }
                });
                boolean focusWasMovedOrCancelled = boolMo4746focusSearchULY8qGw2 != null ? boolMo4746focusSearchULY8qGw2.booleanValue() : true;
                if (focusWasMovedOrCancelled) {
                    return true;
                }
                if (FocusOwnerImplKt.m4754is1dFocusSearch3ESFkO8(focusDirection.getValue())) {
                    Integer numM4739toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m4739toAndroidFocusDirection3ESFkO8(focusDirection.getValue());
                    int direction = numM4739toAndroidFocusDirection3ESFkO8 != null ? numM4739toAndroidFocusDirection3ESFkO8.intValue() : 2;
                    FocusFinder focusFinder = FocusFinder.getInstance();
                    View rootView = AndroidComposeView.this.getRootView();
                    Intrinsics.checkNotNull(rootView, "null cannot be cast to non-null type android.view.ViewGroup");
                    View nextView = focusFinder.findNextFocus((ViewGroup) rootView, AndroidComposeView.this.getView(), direction);
                    if (nextView == null || Intrinsics.areEqual(nextView, AndroidComposeView.this)) {
                        return AndroidComposeView.this.getFocusOwner().mo4749resetFocus3ESFkO8(focusDirection.getValue());
                    }
                }
                return false;
            }
            Integer androidDirection = FocusInteropUtils_androidKt.m4739toAndroidFocusDirection3ESFkO8(focusDirection.getValue());
            if (ComposeUiFlags.isViewFocusFixEnabled && AndroidComposeView.this.hasFocus() && androidDirection != null && AndroidComposeView.this.mo4793moveFocusInChildren3ESFkO8(focusDirection.getValue())) {
                return true;
            }
            Rect focusedRect = AndroidComposeView.this.getEmbeddedViewFocusRect();
            Boolean boolMo4746focusSearchULY8qGw3 = AndroidComposeView.this.getFocusOwner().mo4746focusSearchULY8qGw(focusDirection.getValue(), focusedRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$onKeyEvent$focusWasMovedOrCancelled$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(FocusTargetNode it) {
                    return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection.getValue()));
                }
            });
            boolean focusWasMovedOrCancelled2 = boolMo4746focusSearchULY8qGw3 != null ? boolMo4746focusSearchULY8qGw3.booleanValue() : true;
            if (focusWasMovedOrCancelled2) {
                return true;
            }
            if (!FocusOwnerImplKt.m4754is1dFocusSearch3ESFkO8(focusDirection.getValue())) {
                return false;
            }
            if (androidDirection != null) {
                View nextView2 = AndroidComposeView.this.findNextNonChildView(androidDirection.intValue());
                if (Intrinsics.areEqual(nextView2, AndroidComposeView.this)) {
                    nextView2 = null;
                }
                if (nextView2 != null) {
                    android.graphics.Rect androidRect = focusedRect != null ? RectHelper_androidKt.toAndroidRect(focusedRect) : null;
                    if (androidRect != null) {
                        View rootView2 = AndroidComposeView.this.getRootView();
                        Intrinsics.checkNotNull(rootView2, "null cannot be cast to non-null type android.view.ViewGroup");
                        ViewGroup rootView3 = (ViewGroup) rootView2;
                        rootView3.offsetDescendantRectToMyCoords(AndroidComposeView.this.getView(), androidRect);
                        rootView3.offsetRectIntoDescendantCoords(nextView2, androidRect);
                        if (FocusInteropUtils_androidKt.requestInteropFocus(nextView2, androidDirection, androidRect)) {
                            return true;
                        }
                    } else {
                        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid rect");
                        throw new KotlinNothingValueException();
                    }
                }
            }
            boolean clearedFocusSuccessfully = AndroidComposeView.this.getFocusOwner().mo4743clearFocusI7lrPNg(false, true, false, focusDirection.getValue());
            if (clearedFocusSuccessfully && (boolMo4746focusSearchULY8qGw = AndroidComposeView.this.getFocusOwner().mo4746focusSearchULY8qGw(focusDirection.getValue(), null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeView$RootModifierNode$onKeyEvent$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(FocusTargetNode it) {
                    return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection.getValue()));
                }
            })) != null) {
                return boolMo4746focusSearchULY8qGw.booleanValue();
            }
            return true;
        }
    }
}
