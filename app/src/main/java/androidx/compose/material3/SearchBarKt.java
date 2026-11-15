package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.foundation.shape.GenericShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.material3.internal.BackEventProgress;
import androidx.compose.material3.internal.BackHandler_androidKt;
import androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt;
import androidx.compose.material3.internal.MutableWindowInsets;
import androidx.compose.material3.internal.PredictiveBack;
import androidx.compose.material3.internal.PredictiveBackState;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.compose.ui.window.PopupProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SearchBar.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u008e\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\u001a\\\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001ar\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0099\u0001\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u0013\b\u0002\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00140\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u0006¢\u0006\u0002\b H\u0007¢\u0006\u0004\b!\u0010\"\u001a\u0084\u0001\u0010#\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020$2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u0006¢\u0006\u0002\b H\u0007¢\u0006\u0004\b%\u0010&\u001a\u0098\u0001\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010'\u001a\u00020(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00010\u001e2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u0006¢\u0006\u0002\b H\u0007¢\u0006\u0004\b*\u0010+\u001a\u008e\u0001\u0010,\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010'\u001a\u00020(2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00010\u001e2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u0006¢\u0006\u0002\b H\u0007¢\u0006\u0004\b-\u0010.\u001a7\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u0002012\u000e\b\u0002\u00102\u001a\b\u0012\u0004\u0012\u000204032\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u00020403H\u0007¢\u0006\u0002\u00106\u001a\u0090\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u00107\u001a\u0002082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00010\u001e2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00010\u001e2\u0006\u0010;\u001a\u00020(2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00010\u001e2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010=\u001a\u00020(2\u0015\b\u0002\u0010>\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010?\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010@\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u0006¢\u0006\u0002\b H\u0007¢\u0006\u0004\bC\u0010D\u001a\u0086\u0002\u0010,\u001a\u00020\u00012\u0006\u00107\u001a\u0002082\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00010\u001e2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00010\u001e2\u0006\u0010;\u001a\u00020(2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00010\u001e2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010=\u001a\u00020(2\u0015\b\u0002\u0010>\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010?\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\u0015\b\u0002\u0010@\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010B2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u0006¢\u0006\u0002\b H\u0007¢\u0006\u0004\bE\u0010F\u001aÄ\u0001\u0010K\u001a\u00020\u00012\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020N0M2\u0006\u0010O\u001a\u00020P2\u0014\u0010Q\u001a\u0010\u0012\f\u0012\n\u0018\u00010Sj\u0004\u0018\u0001`T0R2\u0014\u0010U\u001a\u0010\u0012\f\u0012\n\u0018\u00010Sj\u0004\u0018\u0001`T0R2\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u0006¢\u0006\u0002\b H\u0001¢\u0006\u0004\bV\u0010W\u001a \u0001\u0010X\u001a\u00020\u00012\u0012\u0010L\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020N0M2\u0006\u0010O\u001a\u00020P2\u0014\u0010Q\u001a\u0010\u0012\f\u0012\n\u0018\u00010Sj\u0004\u0018\u0001`T0R2\u0014\u0010U\u001a\u0010\u0012\f\u0012\n\u0018\u00010Sj\u0004\u0018\u0001`T0R2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0011\u0010Y\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0002\u0010Z\u001ap\u0010[\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u0006¢\u0006\u0002\b H\u0003¢\u0006\u0004\b\\\u0010]\u001a\u0080\u0001\u0010^\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010_\u001a\u00020`2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u001c\u0010\u001d\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u001e¢\u0006\u0002\b\u0006¢\u0006\u0002\b H\u0003¢\u0006\u0004\ba\u0010b\u001a\u000e\u0010c\u001a\u000204*\u0004\u0018\u00010dH\u0002\u001a#\u0010j\u001a\u00020\u00012\u0006\u0010A\u001a\u00020k2\f\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010m\u001a-\u0010n\u001a\u0002042\u000e\u0010U\u001a\n\u0018\u00010Sj\u0004\u0018\u0001`T2\u0006\u0010o\u001a\u0002042\u0006\u0010O\u001a\u000204H\u0002¢\u0006\u0002\u0010p\u001aG\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020t2\u0006\u0010u\u001a\u00020r2\u000e\u0010U\u001a\n\u0018\u00010Sj\u0004\u0018\u0001`T2\u0006\u0010v\u001a\u00020w2\u0006\u0010o\u001a\u0002042\u0006\u0010x\u001a\u000204H\u0002¢\u0006\u0004\by\u0010z\u001aW\u0010{\u001a\u00020r2\u0006\u0010s\u001a\u00020t2\u0006\u0010u\u001a\u00020r2\u000e\u0010U\u001a\n\u0018\u00010Sj\u0004\u0018\u0001`T2\u000e\u0010Q\u001a\n\u0018\u00010Sj\u0004\u0018\u0001`T2\u0006\u0010|\u001a\u00020r2\u0006\u0010}\u001a\u00020r2\u0006\u0010x\u001a\u000204H\u0002¢\u0006\u0004\b~\u0010\u007f\"\u001e\u0010G\u001a\u00020(*\u00020\u00038BX\u0082\u0004¢\u0006\f\u0012\u0004\bH\u0010I\u001a\u0004\bG\u0010J\"\u001e\u0010e\u001a\u00020f*\u00020\u00038BX\u0082\u0004¢\u0006\f\u0012\u0004\bg\u0010I\u001a\u0004\bh\u0010i\"\u0010\u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000f\u0010\u0082\u0001\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0083\u0001\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u0084\u0001\u001a\u000208X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0085\u0001\u001a\u00020\u000eX\u0080\u0004¢\u0006\r\n\u0003\u0010\u0088\u0001\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001\"\u001a\u0010\u0089\u0001\u001a\u00020\u000eX\u0082\u0004¢\u0006\r\n\u0003\u0010\u0088\u0001\u0012\u0006\b\u008a\u0001\u0010\u008b\u0001\"\u001a\u0010\u008c\u0001\u001a\u00020\u000eX\u0080\u0004¢\u0006\r\n\u0003\u0010\u0088\u0001\u001a\u0006\b\u008d\u0001\u0010\u0087\u0001\"\u000f\u0010\u008e\u0001\u001a\u000204X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u008f\u0001\u001a\u00020\u000eX\u0080\u0004¢\u0006\r\n\u0003\u0010\u0088\u0001\u001a\u0006\b\u0090\u0001\u0010\u0087\u0001\"\u0012\u0010\u0091\u0001\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0088\u0001\"\u001a\u0010\u0092\u0001\u001a\u00020\u000eX\u0080\u0004¢\u0006\r\n\u0003\u0010\u0088\u0001\u001a\u0006\b\u0093\u0001\u0010\u0087\u0001\"\u0012\u0010\u0094\u0001\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0088\u0001\"\u000f\u0010\u0095\u0001\u001a\u000204X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0096\u0001\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0088\u0001\"\u000f\u0010\u0097\u0001\u001a\u000204X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0098\u0001\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u0088\u0001\"\u000f\u0010\u0099\u0001\u001a\u00020rX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u009a\u0001\u001a\u00020rX\u0082T¢\u0006\u0002\n\u0000\"\u000f\u0010\u009b\u0001\u001a\u00020rX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u009c\u0001\u001a\u00030\u009d\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u009e\u0001\u001a\u00030\u009d\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u009f\u0001\u001a\t\u0012\u0004\u0012\u0002040 \u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010¡\u0001\u001a\t\u0012\u0004\u0012\u0002040 \u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010¢\u0001\u001a\t\u0012\u0004\u0012\u0002040 \u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010£\u0001\u001a\n\u0012\u0005\u0012\u00030¤\u00010 \u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010¥\u0001\u001a\n\u0012\u0005\u0012\u00030¤\u00010 \u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010¦\u0001\u001a\u00030§\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010¨\u0001\u001a\u00030©\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006ª\u0001²\u0006\u000b\u0010«\u0001\u001a\u00020(X\u008a\u0084\u0002²\u0006\u000b\u0010¬\u0001\u001a\u00020(X\u008a\u0084\u0002²\u0006\f\u0010\u00ad\u0001\u001a\u00030®\u0001X\u008a\u0084\u0002"}, d2 = {"SearchBar", "", "state", "Landroidx/compose/material3/SearchBarState;", "inputField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/SearchBarColors;", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", "SearchBar-nbWgWpA", "(Landroidx/compose/material3/SearchBarState;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/runtime/Composer;II)V", "TopSearchBar", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "scrollBehavior", "Landroidx/compose/material3/SearchBarScrollBehavior;", "TopSearchBar-qKj4JfE", "(Landroidx/compose/material3/SearchBarState;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/SearchBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "ExpandedFullScreenSearchBar", "collapsedShape", "properties", "Landroidx/compose/ui/window/DialogProperties;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ExpandedFullScreenSearchBar-_UtchM0", "(Landroidx/compose/material3/SearchBarState;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLkotlin/jvm/functions/Function2;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ExpandedDockedSearchBar", "Landroidx/compose/ui/window/PopupProperties;", "ExpandedDockedSearchBar-qKj4JfE", "(Landroidx/compose/material3/SearchBarState;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "expanded", "", "onExpandedChange", "SearchBar-Y92LkZI", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DockedSearchBar", "DockedSearchBar-EQC0FA8", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "rememberSearchBarState", "initialValue", "Landroidx/compose/material3/SearchBarValue;", "animationSpecForExpand", "Landroidx/compose/animation/core/AnimationSpec;", "", "animationSpecForCollapse", "(Landroidx/compose/material3/SearchBarValue;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SearchBarState;", SearchIntents.EXTRA_QUERY, "", "onQueryChange", "onSearch", "active", "onActiveChange", "enabled", "placeholder", "leadingIcon", "trailingIcon", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "SearchBar-WuY5d9Q", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "DockedSearchBar-eWTbjVg", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "isExpanded", "isExpanded$annotations", "(Landroidx/compose/material3/SearchBarState;)V", "(Landroidx/compose/material3/SearchBarState;)Z", "SearchBarImpl", "animationProgress", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "finalBackProgress", "Landroidx/compose/runtime/MutableFloatState;", "firstBackEvent", "Landroidx/compose/runtime/MutableState;", "Landroidx/activity/BackEventCompat;", "Landroidx/compose/material3/internal/BackEventCompat;", "currentBackEvent", "SearchBarImpl-j1jLAyQ", "(Landroidx/compose/animation/core/Animatable;Landroidx/compose/runtime/MutableFloatState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBarLayout", "surface", "(Landroidx/compose/animation/core/Animatable;Landroidx/compose/runtime/MutableFloatState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "DockedSearchBarLayout", "DockedSearchBarLayout-nbWgWpA", "(Landroidx/compose/material3/SearchBarState;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "FullScreenSearchBarLayout", "predictiveBackState", "Landroidx/compose/material3/internal/PredictiveBackState;", "FullScreenSearchBarLayout-EQC0FA8", "(Landroidx/compose/material3/SearchBarState;Landroidx/compose/material3/internal/PredictiveBackState;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "transform", "Landroidx/compose/material3/internal/BackEventProgress$InProgress;", "collapsedBounds", "Landroidx/compose/ui/unit/IntRect;", "getCollapsedBounds$annotations", "getCollapsedBounds", "(Landroidx/compose/material3/SearchBarState;)Landroidx/compose/ui/unit/IntRect;", "DetectClickFromInteractionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "onClick", "(Landroidx/compose/foundation/interaction/InteractionSource;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "calculatePredictiveBackMultiplier", "progress", "(Landroidx/activity/BackEventCompat;FF)F", "calculatePredictiveBackOffsetX", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "minMargin", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "predictiveBackMultiplier", "calculatePredictiveBackOffsetX-rOvwMX4", "(JILandroidx/activity/BackEventCompat;Landroidx/compose/ui/unit/LayoutDirection;FF)I", "calculatePredictiveBackOffsetY", "height", "maxOffsetY", "calculatePredictiveBackOffsetY-dzo92Q0", "(JILandroidx/activity/BackEventCompat;Landroidx/activity/BackEventCompat;IIF)I", "UnspecifiedTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "LayoutIdInputField", "LayoutIdSurface", "LayoutIdSearchContent", "SearchBarAsTopBarPadding", "getSearchBarAsTopBarPadding", "()F", "F", "SearchBarCornerRadius", "getSearchBarCornerRadius$annotations", "()V", "DockedExpandedTableMinHeight", "getDockedExpandedTableMinHeight", "DockedExpandedTableMaxHeightScreenRatio", "SearchBarMinWidth", "getSearchBarMinWidth", "SearchBarMaxWidth", "SearchBarVerticalPadding", "getSearchBarVerticalPadding", "SearchBarIconOffsetX", "SearchBarPredictiveBackMinScale", "SearchBarPredictiveBackMinMargin", "SearchBarPredictiveBackMaxOffsetXRatio", "SearchBarPredictiveBackMaxOffsetY", "AnimationEnterDurationMillis", "AnimationExitDurationMillis", "AnimationDelayMillis", "AnimationEnterEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "AnimationExitEasing", "AnimationEnterFloatSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "AnimationExitFloatSpec", "AnimationPredictiveBackExitFloatSpec", "AnimationEnterSizeSpec", "Landroidx/compose/ui/unit/IntSize;", "AnimationExitSizeSpec", "DockedEnterTransition", "Landroidx/compose/animation/EnterTransition;", "DockedExitTransition", "Landroidx/compose/animation/ExitTransition;", "material3", "useFullScreenShape", "showContent", "backEvent", "Landroidx/compose/material3/internal/BackEventProgress;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SearchBarKt {
    private static final int AnimationDelayMillis = 100;
    private static final int AnimationEnterDurationMillis = 600;
    private static final CubicBezierEasing AnimationEnterEasing;
    private static final FiniteAnimationSpec<Float> AnimationEnterFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationEnterSizeSpec;
    private static final int AnimationExitDurationMillis = 350;
    private static final CubicBezierEasing AnimationExitEasing;
    private static final FiniteAnimationSpec<Float> AnimationExitFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationExitSizeSpec;
    private static final FiniteAnimationSpec<Float> AnimationPredictiveBackExitFloatSpec;
    private static final EnterTransition DockedEnterTransition;
    private static final ExitTransition DockedExitTransition;
    private static final float DockedExpandedTableMaxHeightScreenRatio = 0.6666667f;
    private static final float DockedExpandedTableMinHeight;
    private static final String LayoutIdInputField = "InputField";
    private static final String LayoutIdSearchContent = "Content";
    private static final String LayoutIdSurface = "Surface";
    private static final float SearchBarCornerRadius;
    private static final float SearchBarIconOffsetX;
    private static final float SearchBarMaxWidth;
    private static final float SearchBarMinWidth;
    private static final float SearchBarPredictiveBackMaxOffsetXRatio = 0.05f;
    private static final float SearchBarPredictiveBackMaxOffsetY;
    private static final float SearchBarPredictiveBackMinMargin;
    private static final float SearchBarPredictiveBackMinScale = 0.9f;
    private static final float SearchBarVerticalPadding;
    private static final TextFieldColors UnspecifiedTextFieldColors = new TextFieldColors(Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), new SelectionColors(Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), null), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), Color.INSTANCE.m5133getUnspecified0d7_KjU(), null);
    private static final float SearchBarAsTopBarPadding = Dp.m7905constructorimpl(8);

    static final Unit DetectClickFromInteractionSource$lambda$73(InteractionSource interactionSource, Function0 function0, int i, Composer composer, int i2) {
        DetectClickFromInteractionSource(interactionSource, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DockedSearchBarLayout_nbWgWpA$lambda$52(SearchBarState searchBarState, Function2 function2, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, Function3 function3, int i, Composer composer, int i2) {
        m2783DockedSearchBarLayoutnbWgWpA(searchBarState, function2, modifier, shape, searchBarColors, f, f2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DockedSearchBar_EQC0FA8$lambda$25(Function2 function2, boolean z, Function1 function1, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2781DockedSearchBarEQC0FA8(function2, z, function1, modifier, shape, searchBarColors, f, f2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DockedSearchBar_eWTbjVg$lambda$29(String str, Function1 function1, Function1 function2, boolean z, Function1 function3, Modifier modifier, boolean z2, Function2 function4, Function2 function5, Function2 function6, Shape shape, SearchBarColors searchBarColors, float f, float f2, MutableInteractionSource mutableInteractionSource, Function3 function7, int i, int i2, int i3, Composer composer, int i4) {
        m2782DockedSearchBareWTbjVg(str, function1, function2, z, function3, modifier, z2, function4, function5, function6, shape, searchBarColors, f, f2, mutableInteractionSource, function7, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit ExpandedDockedSearchBar_qKj4JfE$lambda$10(SearchBarState searchBarState, Function2 function2, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, PopupProperties popupProperties, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2784ExpandedDockedSearchBarqKj4JfE(searchBarState, function2, modifier, shape, searchBarColors, f, f2, popupProperties, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ExpandedDockedSearchBar_qKj4JfE$lambda$14(SearchBarState searchBarState, Function2 function2, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, PopupProperties popupProperties, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2784ExpandedDockedSearchBarqKj4JfE(searchBarState, function2, modifier, shape, searchBarColors, f, f2, popupProperties, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ExpandedFullScreenSearchBar__UtchM0$lambda$6(SearchBarState searchBarState, Function2 function2, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, Function2 function3, DialogProperties dialogProperties, Function3 function4, int i, int i2, Composer composer, int i3) {
        m2785ExpandedFullScreenSearchBar_UtchM0(searchBarState, function2, modifier, shape, searchBarColors, f, f2, function3, dialogProperties, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ExpandedFullScreenSearchBar__UtchM0$lambda$9(SearchBarState searchBarState, Function2 function2, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, Function2 function3, DialogProperties dialogProperties, Function3 function4, int i, int i2, Composer composer, int i3) {
        m2785ExpandedFullScreenSearchBar_UtchM0(searchBarState, function2, modifier, shape, searchBarColors, f, f2, function3, dialogProperties, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit FullScreenSearchBarLayout_EQC0FA8$lambda$70(SearchBarState searchBarState, PredictiveBackState predictiveBackState, Function2 function2, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, WindowInsets windowInsets, Function3 function3, int i, Composer composer, int i2) {
        m2786FullScreenSearchBarLayoutEQC0FA8(searchBarState, predictiveBackState, function2, modifier, shape, searchBarColors, f, f2, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SearchBarImpl_j1jLAyQ$lambda$39(Animatable animatable, MutableFloatState mutableFloatState, MutableState mutableState, MutableState mutableState2, Modifier modifier, Function2 function2, Shape shape, SearchBarColors searchBarColors, float f, float f2, WindowInsets windowInsets, Function3 function3, int i, int i2, int i3, Composer composer, int i4) {
        m2790SearchBarImplj1jLAyQ(animatable, mutableFloatState, mutableState, mutableState2, modifier, function2, shape, searchBarColors, f, f2, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit SearchBarLayout$lambda$49(Animatable animatable, MutableFloatState mutableFloatState, MutableState mutableState, MutableState mutableState2, Modifier modifier, WindowInsets windowInsets, Function2 function2, Function2 function3, Function2 function4, int i, Composer composer, int i2) {
        SearchBarLayout(animatable, mutableFloatState, mutableState, mutableState2, modifier, windowInsets, function2, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SearchBar_WuY5d9Q$lambda$28(String str, Function1 function1, Function1 function2, boolean z, Function1 function3, Modifier modifier, boolean z2, Function2 function4, Function2 function5, Function2 function6, Shape shape, SearchBarColors searchBarColors, float f, float f2, WindowInsets windowInsets, MutableInteractionSource mutableInteractionSource, Function3 function7, int i, int i2, int i3, Composer composer, int i4) {
        m2787SearchBarWuY5d9Q(str, function1, function2, z, function3, modifier, z2, function4, function5, function6, shape, searchBarColors, f, f2, windowInsets, mutableInteractionSource, function7, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit SearchBar_Y92LkZI$lambda$22(Function2 function2, boolean z, Function1 function1, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, WindowInsets windowInsets, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2788SearchBarY92LkZI(function2, z, function1, modifier, shape, searchBarColors, f, f2, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SearchBar_nbWgWpA$lambda$2(SearchBarState searchBarState, Function2 function2, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, int i, int i2, Composer composer, int i3) {
        m2789SearchBarnbWgWpA(searchBarState, function2, modifier, shape, searchBarColors, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TopSearchBar_qKj4JfE$lambda$5(SearchBarState searchBarState, Function2 function2, Modifier modifier, Shape shape, SearchBarColors searchBarColors, float f, float f2, WindowInsets windowInsets, SearchBarScrollBehavior searchBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        m2791TopSearchBarqKj4JfE(searchBarState, function2, modifier, shape, searchBarColors, f, f2, windowInsets, searchBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private static /* synthetic */ void getCollapsedBounds$annotations(SearchBarState searchBarState) {
    }

    private static /* synthetic */ void getSearchBarCornerRadius$annotations() {
    }

    private static /* synthetic */ void isExpanded$annotations(SearchBarState searchBarState) {
    }

    /* JADX INFO: renamed from: SearchBar-nbWgWpA, reason: not valid java name */
    public static final void m2789SearchBarnbWgWpA(final SearchBarState state, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function3;
        Modifier modifier2;
        Shape shape2;
        SearchBarColors searchBarColors;
        float f;
        float f2;
        final SearchBarColors colors2;
        final Modifier modifier3;
        final Shape shape3;
        final float tonalElevation2;
        final float shadowElevation2;
        SearchBarColors colors3;
        float tonalElevation3;
        int $dirty;
        SearchBarColors colors4;
        Shape shape4;
        float tonalElevation4;
        float shadowElevation3;
        Composer $composer2 = $composer.startRestartGroup(1234122643);
        ComposerKt.sourceInformation($composer2, "C(SearchBar)N(state,inputField,modifier,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp)234@11650L30,237@11767L38,233@11592L334:SearchBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function3 = function2;
        } else if (($changed & 48) == 0) {
            function3 = function2;
            $dirty2 |= $composer2.changedInstance(function3) ? 32 : 16;
        } else {
            function3 = function2;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i3 = $composer2.changed(shape2) ? 2048 : 1024;
                $dirty2 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                searchBarColors = colors;
                int i4 = $composer2.changed(searchBarColors) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                searchBarColors = colors;
            }
            $dirty2 |= i4;
        } else {
            searchBarColors = colors;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = tonalElevation;
        } else if ((196608 & $changed) == 0) {
            f = tonalElevation;
            $dirty2 |= $composer2.changed(f) ? 131072 : 65536;
        } else {
            f = tonalElevation;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            f2 = shadowElevation;
        } else if ((1572864 & $changed) == 0) {
            f2 = shadowElevation;
            $dirty2 |= $composer2.changed(f2) ? 1048576 : 524288;
        } else {
            f2 = shadowElevation;
        }
        boolean z = false;
        if ($composer2.shouldExecute((599187 & $dirty2) != 599186, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "228@11389L15,229@11454L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                SearchBarColors searchBarColors2 = searchBarColors;
                $dirty = $dirty2;
                colors4 = searchBarColors2;
                float f3 = f;
                shape4 = shape2;
                tonalElevation4 = f3;
                shadowElevation3 = f2;
                z = false;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer2, 6);
                }
                if ((i & 16) != 0) {
                    colors3 = SearchBarDefaults.INSTANCE.m2771colorsKlgxPg(0L, 0L, null, $composer2, 3072, 7);
                    $dirty2 &= -57345;
                } else {
                    colors3 = colors;
                }
                if (i5 == 0) {
                    tonalElevation3 = tonalElevation;
                } else {
                    tonalElevation3 = SearchBarDefaults.INSTANCE.m2776getTonalElevationD9Ej5fM();
                }
                if (i6 == 0) {
                    SearchBarColors searchBarColors3 = colors3;
                    $dirty = $dirty2;
                    colors4 = searchBarColors3;
                    float f4 = tonalElevation3;
                    shape4 = shape2;
                    tonalElevation4 = f4;
                    shadowElevation3 = shadowElevation;
                } else {
                    SearchBarColors searchBarColors4 = colors3;
                    $dirty = $dirty2;
                    colors4 = searchBarColors4;
                    float f5 = tonalElevation3;
                    shape4 = shape2;
                    tonalElevation4 = f5;
                    shadowElevation3 = SearchBarDefaults.INSTANCE.m2775getShadowElevationD9Ej5fM();
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1234122643, $dirty, -1, "androidx.compose.material3.SearchBar (SearchBar.kt:232)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 580903377, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4 ? true : z;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchBarKt.SearchBar_nbWgWpA$lambda$1$lambda$0(state, (LayoutCoordinates) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            float tonalElevation5 = tonalElevation4;
            SurfaceKt.m2929SurfaceT9BRK9s(OnGloballyPositionedModifierKt.onGloballyPositioned(modifier2, (Function1) it$iv), shape4, colors4.m2768getContainerColor0d7_KjU(), ColorSchemeKt.m2263contentColorForek8zF_U(colors4.m2768getContainerColor0d7_KjU(), $composer2, 0), tonalElevation5, shadowElevation3, null, function3, $composer2, (($dirty >> 6) & 112) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752) | (($dirty << 18) & 29360128), 64);
            $composer2 = $composer2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            Modifier modifier4 = modifier2;
            tonalElevation2 = tonalElevation5;
            modifier3 = modifier4;
            colors2 = colors4;
            shape3 = shape4;
            shadowElevation2 = shadowElevation3;
        } else {
            $composer2.skipToGroupEnd();
            colors2 = colors;
            modifier3 = modifier2;
            shape3 = shape2;
            tonalElevation2 = tonalElevation;
            shadowElevation2 = shadowElevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.SearchBar_nbWgWpA$lambda$2(state, function2, modifier3, shape3, colors2, tonalElevation2, shadowElevation2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit SearchBar_nbWgWpA$lambda$1$lambda$0(SearchBarState $state, LayoutCoordinates it) {
        $state.setCollapsedCoords(it);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:153:0x0226  */
    /* JADX INFO: renamed from: TopSearchBar-qKj4JfE, reason: not valid java name */
    public static final void m2791TopSearchBarqKj4JfE(final SearchBarState state, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, WindowInsets windowInsets, SearchBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        SearchBarState searchBarState;
        Function2<? super Composer, ? super Integer, Unit> function3;
        final Shape shape2;
        SearchBarColors colors2;
        float f;
        float f2;
        final WindowInsets windowInsets2;
        Composer $composer2;
        final Modifier modifier2;
        final float tonalElevation2;
        final float shadowElevation2;
        final SearchBarScrollBehavior scrollBehavior2;
        final SearchBarColors colors3;
        Modifier.Companion modifier3;
        Shape shape3;
        int $dirty;
        int i2;
        int i3;
        float tonalElevation3;
        float shadowElevation3;
        WindowInsets windowInsets3;
        SearchBarScrollBehavior scrollBehavior3;
        float tonalElevation4;
        float shadowElevation4;
        SearchBarColors colors4;
        Shape shape4;
        boolean z;
        int i4;
        Modifier modifier4;
        Modifier.Companion companionSearchBarScrollBehavior;
        Composer $composer3 = $composer.startRestartGroup(782822734);
        ComposerKt.sourceInformation($composer3, "C(TopSearchBar)N(state,inputField,modifier,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,windowInsets,scrollBehavior)293@14798L586:SearchBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            searchBarState = state;
        } else if (($changed & 6) == 0) {
            searchBarState = state;
            $dirty2 |= $composer3.changed(searchBarState) ? 4 : 2;
        } else {
            searchBarState = state;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function3 = function2;
        } else if (($changed & 48) == 0) {
            function3 = function2;
            $dirty2 |= $composer3.changedInstance(function3) ? 32 : 16;
        } else {
            function3 = function2;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i6 = $composer3.changed(shape2) ? 2048 : 1024;
                $dirty2 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 16384 : 8192;
                $dirty2 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i7;
        } else {
            colors2 = colors;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = tonalElevation;
        } else if ((196608 & $changed) == 0) {
            f = tonalElevation;
            $dirty2 |= $composer3.changed(f) ? 131072 : 65536;
        } else {
            f = tonalElevation;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty2 |= 1572864;
            f2 = shadowElevation;
        } else if ((1572864 & $changed) == 0) {
            f2 = shadowElevation;
            $dirty2 |= $composer3.changed(f2) ? 1048576 : 524288;
        } else {
            f2 = shadowElevation;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                windowInsets2 = windowInsets;
                int i10 = $composer3.changed(windowInsets2) ? 8388608 : 4194304;
                $dirty2 |= i10;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i10;
        } else {
            windowInsets2 = windowInsets;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(scrollBehavior) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (!$composer3.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            tonalElevation2 = tonalElevation;
            shadowElevation2 = shadowElevation;
            windowInsets2 = windowInsets;
            scrollBehavior2 = scrollBehavior;
            colors3 = colors2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "286@14477L15,287@14542L8,290@14723L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                modifier4 = modifier;
                scrollBehavior3 = scrollBehavior;
                shape4 = shape2;
                tonalElevation4 = f;
                shadowElevation4 = f2;
                colors4 = colors2;
                z = false;
                i4 = 782822734;
            } else {
                if (i5 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 8) == 0) {
                    shape3 = shape2;
                    $dirty = $dirty2;
                } else {
                    shape3 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                    $dirty = $dirty2 & (-7169);
                }
                if ((i & 16) != 0) {
                    i3 = i11;
                    i2 = 6;
                    $dirty &= -57345;
                    colors2 = SearchBarDefaults.INSTANCE.m2771colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                } else {
                    i2 = 6;
                    i3 = i11;
                }
                if (i8 == 0) {
                    tonalElevation3 = tonalElevation;
                } else {
                    tonalElevation3 = SearchBarDefaults.INSTANCE.m2776getTonalElevationD9Ej5fM();
                }
                if (i9 == 0) {
                    shadowElevation3 = shadowElevation;
                } else {
                    shadowElevation3 = SearchBarDefaults.INSTANCE.m2775getShadowElevationD9Ej5fM();
                }
                if ((i & 128) == 0) {
                    windowInsets3 = windowInsets;
                } else {
                    windowInsets3 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, i2);
                    $dirty &= -29360129;
                }
                if (i3 == 0) {
                    scrollBehavior3 = scrollBehavior;
                    windowInsets2 = windowInsets3;
                    tonalElevation4 = tonalElevation3;
                    shadowElevation4 = shadowElevation3;
                    colors4 = colors2;
                    shape4 = shape3;
                    $dirty2 = $dirty;
                    z = false;
                    i4 = 782822734;
                    modifier4 = modifier3;
                } else {
                    scrollBehavior3 = null;
                    windowInsets2 = windowInsets3;
                    tonalElevation4 = tonalElevation3;
                    shadowElevation4 = shadowElevation3;
                    colors4 = colors2;
                    shape4 = shape3;
                    $dirty2 = $dirty;
                    z = false;
                    i4 = 782822734;
                    modifier4 = modifier3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i4, $dirty2, -1, "androidx.compose.material3.TopSearchBar (SearchBar.kt:292)");
            }
            if (scrollBehavior3 == null) {
                companionSearchBarScrollBehavior = Modifier.INSTANCE;
            } else {
                SearchBarScrollBehavior it = scrollBehavior3;
                companionSearchBarScrollBehavior = it.searchBarScrollBehavior(Modifier.INSTANCE);
                if (companionSearchBarScrollBehavior == null) {
                    companionSearchBarScrollBehavior = Modifier.INSTANCE;
                }
            }
            $composer2 = $composer3;
            m2789SearchBarnbWgWpA(searchBarState, function3, SizeKt.wrapContentWidth$default(SizeKt.fillMaxWidth$default(PaddingKt.m819padding3ABfNKs(WindowInsetsPaddingKt.windowInsetsPadding(modifier4.then(companionSearchBarScrollBehavior), windowInsets2), SearchBarAsTopBarPadding), 0.0f, 1, null), null, z, 3, null), shape4, colors4, tonalElevation4, shadowElevation4, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            scrollBehavior2 = scrollBehavior3;
            shape2 = shape4;
            colors3 = colors4;
            tonalElevation2 = tonalElevation4;
            shadowElevation2 = shadowElevation4;
            modifier2 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.TopSearchBar_qKj4JfE$lambda$5(state, function2, modifier2, shape2, colors3, tonalElevation2, shadowElevation2, windowInsets2, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ExpandedFullScreenSearchBar-_UtchM0, reason: not valid java name */
    public static final void m2785ExpandedFullScreenSearchBar_UtchM0(final SearchBarState state, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Shape collapsedShape, SearchBarColors colors, float tonalElevation, float shadowElevation, Function2<? super Composer, ? super Integer, ? extends WindowInsets> function3, DialogProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        Shape shape;
        SearchBarColors colors2;
        final float tonalElevation2;
        float f;
        Function2<? super Composer, ? super Integer, ? extends WindowInsets> function6;
        DialogProperties dialogProperties;
        Composer $composer2;
        final float tonalElevation3;
        final float shadowElevation2;
        final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function7;
        final Modifier modifier3;
        final Shape collapsedShape2;
        final DialogProperties properties2;
        final SearchBarColors colors3;
        Shape collapsedShape3;
        int $dirty;
        Composer $composer3;
        int i2;
        SearchBarKt$ExpandedFullScreenSearchBar$1 searchBarKt$ExpandedFullScreenSearchBar$1;
        final float shadowElevation3;
        final DialogProperties properties3;
        final SearchBarColors colors4;
        int $dirty2;
        final Modifier modifier4;
        Composer $composer4;
        final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function8;
        final Shape collapsedShape4;
        Composer $composer5 = $composer.startRestartGroup(-909632031);
        ComposerKt.sourceInformation($composer5, "C(ExpandedFullScreenSearchBar)N(state,inputField,modifier,collapsedShape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,windowInsets,properties,content)356@17952L24,359@18032L56,361@18129L1354,358@17982L1501:SearchBar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer5.changed(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
            function5 = function2;
        } else if (($changed & 48) == 0) {
            function5 = function2;
            $dirty3 |= $composer5.changedInstance(function5) ? 32 : 16;
        } else {
            function5 = function2;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer5.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape = collapsedShape;
                int i4 = $composer5.changed(shape) ? 2048 : 1024;
                $dirty3 |= i4;
            } else {
                shape = collapsedShape;
            }
            $dirty3 |= i4;
        } else {
            shape = collapsedShape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                colors2 = colors;
                int i5 = $composer5.changed(colors2) ? 16384 : 8192;
                $dirty3 |= i5;
            } else {
                colors2 = colors;
            }
            $dirty3 |= i5;
        } else {
            colors2 = colors;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            tonalElevation2 = tonalElevation;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty3 |= $composer5.changed(tonalElevation2) ? 131072 : 65536;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            f = shadowElevation;
        } else if ((1572864 & $changed) == 0) {
            f = shadowElevation;
            $dirty3 |= $composer5.changed(f) ? 1048576 : 524288;
        } else {
            f = shadowElevation;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                function6 = function3;
                int i8 = $composer5.changedInstance(function6) ? 8388608 : 4194304;
                $dirty3 |= i8;
            } else {
                function6 = function3;
            }
            $dirty3 |= i8;
        } else {
            function6 = function3;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty3 |= 100663296;
            dialogProperties = properties;
        } else if (($changed & 100663296) == 0) {
            dialogProperties = properties;
            $dirty3 |= $composer5.changed(dialogProperties) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            dialogProperties = properties;
        }
        if ((i & 512) != 0) {
            $dirty3 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer5.changedInstance(function4) ? 536870912 : 268435456;
        }
        if ($composer5.shouldExecute(($dirty3 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer5.startDefaults();
            ComposerKt.sourceInformation($composer5, "346@17492L15,347@17557L8");
            if (($changed & 1) == 0 || $composer5.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 8) != 0) {
                    collapsedShape3 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer5, 6);
                    $dirty = $dirty3 & (-7169);
                } else {
                    collapsedShape3 = shape;
                    $dirty = $dirty3;
                }
                if ((i & 16) != 0) {
                    i2 = i9;
                    $composer3 = $composer5;
                    $dirty &= -57345;
                    colors2 = SearchBarDefaults.INSTANCE.m2771colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                } else {
                    $composer3 = $composer5;
                    i2 = i9;
                }
                float tonalElevation4 = i6 != 0 ? SearchBarDefaults.INSTANCE.m2776getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i7 != 0 ? SearchBarDefaults.INSTANCE.m2775getShadowElevationD9Ej5fM() : shadowElevation;
                if ((i & 128) != 0) {
                    searchBarKt$ExpandedFullScreenSearchBar$1 = new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.SearchBarKt$ExpandedFullScreenSearchBar$1
                        public final WindowInsets invoke(Composer $composer6, int $changed2) {
                            $composer6.startReplaceGroup(-2028768625);
                            ComposerKt.sourceInformation($composer6, "C350@17758L22:SearchBar.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2028768625, $changed2, -1, "androidx.compose.material3.ExpandedFullScreenSearchBar.<anonymous> (SearchBar.kt:350)");
                            }
                            WindowInsets fullScreenWindowInsets = SearchBarDefaults.INSTANCE.getFullScreenWindowInsets($composer6, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            $composer6.endReplaceGroup();
                            return fullScreenWindowInsets;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ WindowInsets invoke(Composer composer, Integer num) {
                            return invoke(composer, num.intValue());
                        }
                    };
                    $dirty &= -29360129;
                } else {
                    searchBarKt$ExpandedFullScreenSearchBar$1 = function3;
                }
                if (i2 != 0) {
                    tonalElevation2 = tonalElevation4;
                    shadowElevation3 = shadowElevation4;
                    properties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    colors4 = colors2;
                    $dirty2 = $dirty;
                    modifier4 = modifier5;
                    $composer4 = $composer3;
                    function8 = searchBarKt$ExpandedFullScreenSearchBar$1;
                    collapsedShape4 = collapsedShape3;
                } else {
                    tonalElevation2 = tonalElevation4;
                    shadowElevation3 = shadowElevation4;
                    properties3 = dialogProperties;
                    colors4 = colors2;
                    $dirty2 = $dirty;
                    modifier4 = modifier5;
                    $composer4 = $composer3;
                    function8 = searchBarKt$ExpandedFullScreenSearchBar$1;
                    collapsedShape4 = collapsedShape3;
                }
            } else {
                $composer5.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 128) != 0) {
                    $dirty3 &= -29360129;
                }
                modifier4 = modifier2;
                collapsedShape4 = shape;
                $composer4 = $composer5;
                shadowElevation3 = f;
                function8 = function6;
                properties3 = dialogProperties;
                colors4 = colors2;
                $dirty2 = $dirty3;
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-909632031, $dirty2, -1, "androidx.compose.material3.ExpandedFullScreenSearchBar (SearchBar.kt:353)");
            }
            if (!isExpanded(state)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer4.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Function2<? super Composer, ? super Integer, Unit> function9 = function5;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return SearchBarKt.ExpandedFullScreenSearchBar__UtchM0$lambda$6(state, function9, modifier4, collapsedShape4, colors4, tonalElevation2, shadowElevation3, function8, properties3, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            $composer2 = $composer4;
            DialogProperties properties4 = properties3;
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer2, 683737348, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer2.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer2);
                $composer2.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            final CoroutineScope coroutineScope = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 2016970937, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(coroutineScope) | (($dirty2 & 14) == 4);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchBarKt.ExpandedFullScreenSearchBar__UtchM0$lambda$8$lambda$7(coroutineScope, state);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Modifier modifier6 = modifier4;
            final Shape collapsedShape5 = collapsedShape4;
            final SearchBarColors colors5 = colors4;
            final float tonalElevation5 = tonalElevation2;
            final float tonalElevation6 = shadowElevation3;
            final Function2<? super Composer, ? super Integer, ? extends WindowInsets> function10 = function8;
            BasicEdgeToEdgeDialog_androidKt.BasicEdgeToEdgeDialog((Function0) it$iv, null, properties4, false, false, ComposableLambdaKt.rememberComposableLambda(625093617, true, new Function3<PredictiveBackState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$ExpandedFullScreenSearchBar$4
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(PredictiveBackState predictiveBackState, Composer composer, Integer num) {
                    invoke(predictiveBackState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(PredictiveBackState predictiveBackState, Composer $composer6, int $changed2) {
                    ComposerKt.sourceInformation($composer6, "CN(predictiveBackState)362@18183L29,366@18355L231,379@18814L14,363@18221L649,385@19020L33,385@18999L54,389@19286L7,390@19336L141,390@19302L175:SearchBar.kt#uh7d8r");
                    int $dirty4 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty4 |= ($changed2 & 8) == 0 ? $composer6.changed(predictiveBackState) : $composer6.changedInstance(predictiveBackState) ? 4 : 2;
                    }
                    int $dirty5 = $dirty4;
                    if (!$composer6.shouldExecute(($dirty5 & 19) != 18, $dirty5 & 1)) {
                        $composer6.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(625093617, $dirty5, -1, "androidx.compose.material3.ExpandedFullScreenSearchBar.<anonymous> (SearchBar.kt:362)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer6, -580453746, "CC(remember):SearchBar.kt#9igjgp");
                    Object it$iv2 = $composer6.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = new FocusRequester();
                        $composer6.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    final FocusRequester focusRequester = (FocusRequester) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer6);
                    SearchBarState searchBarState = state;
                    final Function2<Composer, Integer, Unit> function11 = function2;
                    SearchBarKt.m2786FullScreenSearchBarLayoutEQC0FA8(searchBarState, predictiveBackState, ComposableLambdaKt.rememberComposableLambda(-2142632188, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$ExpandedFullScreenSearchBar$4.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer7, int $changed3) {
                            Function0<ComposeUiNode> function0;
                            ComposerKt.sourceInformation($composer7, "C367@18373L199:SearchBar.kt#uh7d8r");
                            if (!$composer7.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer7.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2142632188, $changed3, -1, "androidx.compose.material3.ExpandedFullScreenSearchBar.<anonymous>.<anonymous> (SearchBar.kt:367)");
                            }
                            Modifier modifier$iv = FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester);
                            Function2<Composer, Integer, Unit> function12 = function11;
                            ComposerKt.sourceInformationMarkerStart($composer7, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
                            int $changed$iv$iv = (384 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer7, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer7, 0);
                            CompositionLocalMap localMap$iv$iv = $composer7.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer7, modifier$iv);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer7, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer7.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer7.startReusableNode();
                            if ($composer7.getInserting()) {
                                function0 = constructor;
                                $composer7.createNode(function0);
                            } else {
                                function0 = constructor;
                                $composer7.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer7);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i10 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer7, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i11 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer7, 1606432749, "C371@18542L12:SearchBar.kt#uh7d8r");
                            function12.invoke($composer7, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            $composer7.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer6, 54), modifier6, collapsedShape5, colors5, tonalElevation5, tonalElevation6, function10.invoke($composer6, 0), function4, $composer6, (($dirty5 << 3) & 112) | 384);
                    Unit unit = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer6, -580426958, "CC(remember):SearchBar.kt#9igjgp");
                    Object it$iv3 = $composer6.rememberedValue();
                    if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv3 = (Function2) new SearchBarKt$ExpandedFullScreenSearchBar$4$2$1(focusRequester, null);
                        $composer6.updateRememberedValue(value$iv3);
                        it$iv3 = value$iv3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer6);
                    EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv3, $composer6, 6);
                    ProvidableCompositionLocal<SoftwareKeyboardController> localSoftwareKeyboardController = CompositionLocalsKt.getLocalSoftwareKeyboardController();
                    ComposerKt.sourceInformationMarkerStart($composer6, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer6.consume(localSoftwareKeyboardController);
                    ComposerKt.sourceInformationMarkerEnd($composer6);
                    SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) objConsume;
                    SearchBarValue targetValue = state.getTargetValue();
                    ComposerKt.sourceInformationMarkerStart($composer6, -580416738, "CC(remember):SearchBar.kt#9igjgp");
                    boolean invalid$iv2 = $composer6.changed(state) | $composer6.changed(softwareKeyboardController);
                    SearchBarState searchBarState2 = state;
                    Object it$iv4 = $composer6.rememberedValue();
                    if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv4 = (Function2) new SearchBarKt$ExpandedFullScreenSearchBar$4$3$1(searchBarState2, softwareKeyboardController, null);
                        $composer6.updateRememberedValue(value$iv4);
                        it$iv4 = value$iv4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer6);
                    EffectsKt.LaunchedEffect(targetValue, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv4, $composer6, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, (($dirty2 >> 18) & 896) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 26);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            properties2 = properties4;
            collapsedShape2 = collapsedShape5;
            colors3 = colors5;
            tonalElevation3 = tonalElevation5;
            shadowElevation2 = tonalElevation6;
            function7 = function10;
            modifier3 = modifier6;
        } else {
            $composer2 = $composer5;
            $composer2.skipToGroupEnd();
            tonalElevation3 = tonalElevation;
            shadowElevation2 = shadowElevation;
            function7 = function3;
            modifier3 = modifier2;
            collapsedShape2 = shape;
            properties2 = dialogProperties;
            colors3 = colors2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.ExpandedFullScreenSearchBar__UtchM0$lambda$9(state, function2, modifier3, collapsedShape2, colors3, tonalElevation3, shadowElevation2, function7, properties2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ExpandedFullScreenSearchBar__UtchM0$lambda$8$lambda$7(CoroutineScope $coroutineScope, SearchBarState $state) {
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new SearchBarKt$ExpandedFullScreenSearchBar$3$1$1($state, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: ExpandedDockedSearchBar-qKj4JfE, reason: not valid java name */
    public static final void m2784ExpandedDockedSearchBarqKj4JfE(final SearchBarState state, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, PopupProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        SearchBarColors searchBarColors;
        float f;
        PopupProperties popupProperties;
        Composer $composer2;
        final PopupProperties properties2;
        final Modifier modifier3;
        final Shape shape3;
        final SearchBarColors colors2;
        final float tonalElevation2;
        final float shadowElevation2;
        final SearchBarColors colors3;
        final PopupProperties properties3;
        int $dirty;
        Modifier modifier4;
        final Shape shape4;
        final float shadowElevation3;
        final float tonalElevation3;
        Composer $composer3 = $composer.startRestartGroup(-1121062437);
        ComposerKt.sourceInformation($composer3, "C(ExpandedDockedSearchBar)N(state,inputField,modifier,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,properties,content)439@21817L380,450@22215L24,454@22329L47,456@22417L1212,452@22245L1384:SearchBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shape2 = shape;
                int i3 = $composer3.changed(shape2) ? 2048 : 1024;
                $dirty2 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                searchBarColors = colors;
                int i4 = $composer3.changed(searchBarColors) ? 16384 : 8192;
                $dirty2 |= i4;
            } else {
                searchBarColors = colors;
            }
            $dirty2 |= i4;
        } else {
            searchBarColors = colors;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = tonalElevation;
        } else if ((196608 & $changed) == 0) {
            f = tonalElevation;
            $dirty2 |= $composer3.changed(f) ? 131072 : 65536;
        } else {
            f = tonalElevation;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
        } else if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer3.changed(shadowElevation) ? 1048576 : 524288;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty2 |= 12582912;
            popupProperties = properties;
        } else if ((12582912 & $changed) == 0) {
            popupProperties = properties;
            $dirty2 |= $composer3.changed(popupProperties) ? 8388608 : 4194304;
        } else {
            popupProperties = properties;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty3 = 1;
        if ($composer3.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "429@21409L11,430@21470L8");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                    shape2 = SearchBarDefaults.INSTANCE.getDockedShape($composer3, 6);
                }
                if ((i & 16) != 0) {
                    colors3 = SearchBarDefaults.INSTANCE.m2771colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                    $composer2 = $composer3;
                    $dirty2 &= -57345;
                } else {
                    $composer2 = $composer3;
                    colors3 = searchBarColors;
                }
                float tonalElevation4 = i5 != 0 ? SearchBarDefaults.INSTANCE.m2776getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i6 != 0 ? SearchBarDefaults.INSTANCE.m2775getShadowElevationD9Ej5fM() : shadowElevation;
                if (i7 != 0) {
                    shape4 = shape2;
                    shadowElevation3 = shadowElevation4;
                    properties3 = new PopupProperties(true, false, false, false, 6, (DefaultConstructorMarker) null);
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    tonalElevation3 = tonalElevation4;
                } else {
                    properties3 = properties;
                    $dirty = $dirty2;
                    modifier4 = modifier2;
                    shape4 = shape2;
                    shadowElevation3 = shadowElevation4;
                    tonalElevation3 = tonalElevation4;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                shape4 = shape2;
                $dirty3 = 1;
                properties3 = popupProperties;
                shadowElevation3 = shadowElevation;
                $dirty = $dirty2;
                modifier4 = modifier2;
                tonalElevation3 = f;
                $composer2 = $composer3;
                colors3 = searchBarColors;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1121062437, $dirty, -1, "androidx.compose.material3.ExpandedDockedSearchBar (SearchBar.kt:435)");
            }
            if (!isExpanded(state)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier4;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return SearchBarKt.ExpandedDockedSearchBar_qKj4JfE$lambda$10(state, function2, modifier5, shape4, colors3, tonalElevation3, shadowElevation3, properties3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            PopupProperties properties4 = properties3;
            ComposerKt.sourceInformationMarkerStart($composer2, 695809367, "CC(remember):SearchBar.kt#9igjgp");
            int i8 = ($dirty & 14) == 4 ? $dirty3 : 0;
            Composer $this$cache$iv = $composer2;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (i8 != 0 || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new PopupPositionProvider() { // from class: androidx.compose.material3.SearchBarKt$ExpandedDockedSearchBar$positionProvider$1$1
                    @Override // androidx.compose.ui.window.PopupPositionProvider
                    /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
                    public long mo374calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
                        return SearchBarKt.getCollapsedBounds(state).m8062getTopLeftnOccac();
                    }
                };
                $this$cache$iv.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            SearchBarKt$ExpandedDockedSearchBar$positionProvider$1$1 positionProvider = (SearchBarKt$ExpandedDockedSearchBar$positionProvider$1$1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            Composer composer$iv = $composer2;
            ComposerKt.sourceInformationMarkerStart($composer2, 683737348, "CC(remember):Effects.kt#9igjgp");
            Composer $this$cache$iv$iv = $composer2;
            Object value$iv$iv = $this$cache$iv$iv.rememberedValue();
            final Modifier modifier6 = modifier4;
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer$iv);
                $this$cache$iv$iv.updateRememberedValue(value$iv$iv);
            }
            final CoroutineScope scope = (CoroutineScope) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SearchBarKt$ExpandedDockedSearchBar$positionProvider$1$1 searchBarKt$ExpandedDockedSearchBar$positionProvider$1$1 = positionProvider;
            ComposerKt.sourceInformationMarkerStart($composer2, 695825418, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(scope) | (($dirty & 14) == 4);
            Composer $this$cache$iv2 = $composer2;
            Object it$iv2 = $this$cache$iv2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function0() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchBarKt.ExpandedDockedSearchBar_qKj4JfE$lambda$13$lambda$12(scope, state);
                    }
                };
                $this$cache$iv2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Shape shape5 = shape4;
            final SearchBarColors colors4 = colors3;
            final float tonalElevation5 = tonalElevation3;
            final float tonalElevation6 = shadowElevation3;
            AndroidPopup_androidKt.Popup(searchBarKt$ExpandedDockedSearchBar$positionProvider$1$1, (Function0) it$iv2, properties4, ComposableLambdaKt.rememberComposableLambda(-363177991, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$ExpandedDockedSearchBar$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C457@22448L29,461@22562L231,459@22487L529,479@23166L33,479@23145L54,483@23432L7,484@23482L141,484@23448L175:SearchBar.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-363177991, $changed2, -1, "androidx.compose.material3.ExpandedDockedSearchBar.<anonymous> (SearchBar.kt:457)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer4, -1121697354, "CC(remember):SearchBar.kt#9igjgp");
                    Object it$iv3 = $composer4.rememberedValue();
                    if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv3 = new FocusRequester();
                        $composer4.updateRememberedValue(value$iv3);
                        it$iv3 = value$iv3;
                    }
                    final FocusRequester focusRequester = (FocusRequester) it$iv3;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    SearchBarState searchBarState = state;
                    final Function2<Composer, Integer, Unit> function4 = function2;
                    SearchBarKt.m2783DockedSearchBarLayoutnbWgWpA(searchBarState, ComposableLambdaKt.rememberComposableLambda(2123999554, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$ExpandedDockedSearchBar$3.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer5, int $changed3) {
                            Function0<ComposeUiNode> function0;
                            ComposerKt.sourceInformation($composer5, "C462@22580L199:SearchBar.kt#uh7d8r");
                            if (!$composer5.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer5.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2123999554, $changed3, -1, "androidx.compose.material3.ExpandedDockedSearchBar.<anonymous>.<anonymous> (SearchBar.kt:462)");
                            }
                            Modifier modifier$iv = FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester);
                            Function2<Composer, Integer, Unit> function5 = function4;
                            ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
                            int $changed$iv$iv = (384 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer5, modifier$iv);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                function0 = constructor;
                                $composer5.createNode(function0);
                            } else {
                                function0 = constructor;
                                $composer5.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer5);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i9 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i10 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, 1558467823, "C466@22749L12:SearchBar.kt#uh7d8r");
                            function5.invoke($composer5, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54), modifier6, shape5, colors4, tonalElevation5, tonalElevation6, function3, $composer4, 48);
                    Unit unit = Unit.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1121674374, "CC(remember):SearchBar.kt#9igjgp");
                    Object it$iv4 = $composer4.rememberedValue();
                    if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv4 = (Function2) new SearchBarKt$ExpandedDockedSearchBar$3$2$1(focusRequester, null);
                        $composer4.updateRememberedValue(value$iv4);
                        it$iv4 = value$iv4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv4, $composer4, 6);
                    ProvidableCompositionLocal<SoftwareKeyboardController> localSoftwareKeyboardController = CompositionLocalsKt.getLocalSoftwareKeyboardController();
                    ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer4.consume(localSoftwareKeyboardController);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) objConsume;
                    SearchBarValue targetValue = state.getTargetValue();
                    ComposerKt.sourceInformationMarkerStart($composer4, -1121664154, "CC(remember):SearchBar.kt#9igjgp");
                    boolean invalid$iv2 = $composer4.changed(state) | $composer4.changed(softwareKeyboardController);
                    SearchBarState searchBarState2 = state;
                    Object it$iv5 = $composer4.rememberedValue();
                    if (invalid$iv2 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv5 = (Function2) new SearchBarKt$ExpandedDockedSearchBar$3$3$1(searchBarState2, softwareKeyboardController, null);
                        $composer4.updateRememberedValue(value$iv5);
                        it$iv5 = value$iv5;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    EffectsKt.LaunchedEffect(targetValue, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv5, $composer4, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, (($dirty >> 15) & 896) | 3072, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier6;
            shape3 = shape5;
            properties2 = properties4;
            colors2 = colors4;
            tonalElevation2 = tonalElevation5;
            shadowElevation2 = tonalElevation6;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            properties2 = properties;
            modifier3 = modifier2;
            shape3 = shape2;
            colors2 = searchBarColors;
            tonalElevation2 = tonalElevation;
            shadowElevation2 = shadowElevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.ExpandedDockedSearchBar_qKj4JfE$lambda$14(state, function2, modifier3, shape3, colors2, tonalElevation2, shadowElevation2, properties2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ExpandedDockedSearchBar_qKj4JfE$lambda$13$lambda$12(CoroutineScope $scope, SearchBarState $state) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new SearchBarKt$ExpandedDockedSearchBar$2$1$1($state, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: SearchBar-Y92LkZI, reason: not valid java name */
    public static final void m2788SearchBarY92LkZI(final Function2<? super Composer, ? super Integer, Unit> function2, boolean expanded, final Function1<? super Boolean, Unit> function1, Modifier modifier, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        SearchBarColors searchBarColors;
        float f;
        WindowInsets windowInsets2;
        final boolean z;
        Composer $composer2;
        final SearchBarColors colors2;
        final float tonalElevation2;
        final float shadowElevation2;
        final WindowInsets windowInsets3;
        final Modifier modifier3;
        final Shape shape3;
        Shape shape4;
        Composer $composer3;
        SearchBarColors colors3;
        SearchBarColors colors4;
        float tonalElevation3;
        int $dirty;
        WindowInsets windowInsets4;
        float shadowElevation3;
        Shape shape5;
        Animatable animationProgress;
        Boolean bool;
        MutableFloatState finalBackProgress;
        int $dirty2;
        Composer $composer4;
        MutableFloatState finalBackProgress2;
        Animatable animationProgress2;
        Composer $composer5 = $composer.startRestartGroup(1451547856);
        ComposerKt.sourceInformation($composer5, "C(SearchBar)N(inputField,expanded,onExpandedChange,modifier,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,windowInsets,content)545@26468L24,546@26521L64,547@26614L43,548@26683L51,549@26762L51,551@26844L638,551@26819L663,567@27507L27,568@27581L1149,568@27539L1191,596@28736L458:SearchBar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer5.changedInstance(function2) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer5.changed(expanded) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer5.changedInstance(function1) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty3 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer5.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i3 = $composer5.changed(shape2) ? 16384 : 8192;
                $dirty3 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i3;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                searchBarColors = colors;
                int i4 = $composer5.changed(searchBarColors) ? 131072 : 65536;
                $dirty3 |= i4;
            } else {
                searchBarColors = colors;
            }
            $dirty3 |= i4;
        } else {
            searchBarColors = colors;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty3 |= 1572864;
            f = tonalElevation;
        } else if ((1572864 & $changed) == 0) {
            f = tonalElevation;
            $dirty3 |= $composer5.changed(f) ? 1048576 : 524288;
        } else {
            f = tonalElevation;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer5.changed(shadowElevation) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer5.changed(windowInsets2) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                $dirty3 |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty3 |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 512) != 0) {
            $dirty3 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer5.changedInstance(function3) ? 536870912 : 268435456;
        }
        if ($composer5.shouldExecute(($dirty3 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer5.startDefaults();
            ComposerKt.sourceInformation($composer5, "538@26130L15,539@26195L8,542@26376L12");
            if (($changed & 1) == 0 || $composer5.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 16) != 0) {
                    shape4 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer5, 6);
                    $dirty3 &= -57345;
                } else {
                    shape4 = shape2;
                }
                if ((i & 32) != 0) {
                    colors3 = SearchBarDefaults.INSTANCE.m2771colorsKlgxPg(0L, 0L, null, $composer5, 3072, 7);
                    $composer3 = $composer5;
                    $dirty3 &= -458753;
                } else {
                    $composer3 = $composer5;
                    colors3 = colors;
                }
                float tonalElevation4 = i5 != 0 ? SearchBarDefaults.INSTANCE.m2776getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i6 != 0 ? SearchBarDefaults.INSTANCE.m2775getShadowElevationD9Ej5fM() : shadowElevation;
                if ((i & 256) != 0) {
                    colors4 = colors3;
                    tonalElevation3 = tonalElevation4;
                    $dirty = $dirty3 & (-234881025);
                    windowInsets4 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    shadowElevation3 = shadowElevation4;
                    shape5 = shape4;
                } else {
                    colors4 = colors3;
                    tonalElevation3 = tonalElevation4;
                    $dirty = $dirty3;
                    windowInsets4 = windowInsets2;
                    shadowElevation3 = shadowElevation4;
                    shape5 = shape4;
                }
            } else {
                $composer5.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 256) != 0) {
                    $dirty3 &= -234881025;
                }
                shadowElevation3 = shadowElevation;
                windowInsets4 = windowInsets2;
                shape5 = shape2;
                colors4 = searchBarColors;
                tonalElevation3 = f;
                $composer3 = $composer5;
                $dirty = $dirty3;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1451547856, $dirty, -1, "androidx.compose.material3.SearchBar (SearchBar.kt:544)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            Composer composer$iv = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, 683737348, "CC(remember):Effects.kt#9igjgp");
            Composer $this$cache$iv$iv = $composer3;
            Object value$iv$iv = $this$cache$iv$iv.rememberedValue();
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer$iv);
                $this$cache$iv$iv.updateRememberedValue(value$iv$iv);
            }
            CoroutineScope coroutineScope = (CoroutineScope) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -926861680, "CC(remember):SearchBar.kt#9igjgp");
            Composer $this$cache$iv = $composer3;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                int $i$f$cache = expanded ? 1065353216 : 0;
                Object value$iv = AnimatableKt.Animatable$default($i$f$cache, 0.0f, 2, null);
                $this$cache$iv.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            Animatable animationProgress3 = (Animatable) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -926858725, "CC(remember):SearchBar.kt#9igjgp");
            Composer $this$cache$iv2 = $composer3;
            Object it$iv2 = $this$cache$iv2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(Float.NaN);
                $this$cache$iv2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            MutableFloatState mutableFloatState = (MutableFloatState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -926856509, "CC(remember):SearchBar.kt#9igjgp");
            Composer $this$cache$iv3 = $composer3;
            Object it$iv3 = $this$cache$iv3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $this$cache$iv3.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            MutableState firstBackEvent = (MutableState) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -926853981, "CC(remember):SearchBar.kt#9igjgp");
            Composer $this$cache$iv4 = $composer3;
            Object it$iv4 = $this$cache$iv4.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $this$cache$iv4.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
            }
            MutableState currentBackEvent = (MutableState) it$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Boolean boolValueOf = Boolean.valueOf(expanded);
            ComposerKt.sourceInformationMarkerStart($composer3, -926850770, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = $composer3.changedInstance(animationProgress3) | (($dirty & 112) == 32);
            Composer $this$cache$iv5 = $composer3;
            Object value$iv5 = $this$cache$iv5.rememberedValue();
            if (invalid$iv || value$iv5 == Composer.INSTANCE.getEmpty()) {
                animationProgress = animationProgress3;
                bool = boolValueOf;
                z = expanded;
                finalBackProgress = mutableFloatState;
                value$iv5 = new SearchBarKt$SearchBar$3$1(animationProgress, z, finalBackProgress, firstBackEvent, currentBackEvent, null);
                $this$cache$iv5.updateRememberedValue(value$iv5);
            } else {
                bool = boolValueOf;
                animationProgress = animationProgress3;
                z = expanded;
                finalBackProgress = mutableFloatState;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(bool, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv5, $composer3, ($dirty >> 3) & 14);
            ComposerKt.sourceInformationMarkerStart($composer3, -926830165, r1);
            Composer $this$cache$iv6 = $composer3;
            Object it$iv5 = $this$cache$iv6.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = new MutatorMutex();
                $this$cache$iv6.updateRememberedValue(value$iv6);
                it$iv5 = value$iv6;
            }
            MutatorMutex mutatorMutex = (MutatorMutex) it$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -926826675, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changedInstance(animationProgress) | (($dirty & 896) == 256) | $composer3.changedInstance(coroutineScope);
            Composer $this$cache$iv7 = $composer3;
            Object it$iv6 = $this$cache$iv7.rememberedValue();
            if (invalid$iv2 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                MutableFloatState finalBackProgress3 = finalBackProgress;
                $dirty2 = $dirty;
                $composer4 = $composer3;
                Animatable animationProgress4 = animationProgress;
                finalBackProgress2 = finalBackProgress3;
                animationProgress2 = animationProgress4;
                Object value$iv7 = (Function2) new SearchBarKt$SearchBar$4$1(mutatorMutex, finalBackProgress3, animationProgress4, function1, coroutineScope, firstBackEvent, currentBackEvent, null);
                $this$cache$iv7.updateRememberedValue(value$iv7);
                it$iv6 = value$iv7;
            } else {
                int i8 = $dirty;
                finalBackProgress2 = finalBackProgress;
                $dirty2 = i8;
                $composer4 = $composer3;
                animationProgress2 = animationProgress;
            }
            ComposerKt.sourceInformationMarkerEnd($composer4);
            BackHandler_androidKt.PredictiveBackHandler(z, (Function2) it$iv6, $composer4, ($dirty2 >> 3) & 14, 0);
            SearchBarColors colors5 = colors4;
            float shadowElevation5 = shadowElevation3;
            Composer $composer6 = $composer4;
            Modifier modifier4 = modifier2;
            float tonalElevation5 = tonalElevation3;
            Shape shape6 = shape5;
            m2790SearchBarImplj1jLAyQ(animationProgress2, finalBackProgress2, firstBackEvent, currentBackEvent, modifier4, function2, shape6, colors5, tonalElevation5, shadowElevation5, windowInsets4, function3, $composer6, Animatable.$stable | 3504 | (($dirty2 << 3) & 57344) | (($dirty2 << 15) & 458752) | (($dirty2 << 6) & 3670016) | (($dirty2 << 6) & 29360128) | (($dirty2 << 6) & 234881024) | (($dirty2 << 6) & 1879048192), (($dirty2 >> 24) & 14) | (($dirty2 >> 24) & 112), 0);
            $composer2 = $composer6;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors5;
            modifier3 = modifier4;
            tonalElevation2 = tonalElevation5;
            shadowElevation2 = shadowElevation5;
            windowInsets3 = windowInsets4;
            shape3 = shape6;
        } else {
            z = expanded;
            $composer2 = $composer5;
            $composer2.skipToGroupEnd();
            colors2 = colors;
            tonalElevation2 = tonalElevation;
            shadowElevation2 = shadowElevation;
            windowInsets3 = windowInsets2;
            modifier3 = modifier2;
            shape3 = shape2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.SearchBar_Y92LkZI$lambda$22(function2, z, function1, modifier3, shape3, colors2, tonalElevation2, shadowElevation2, windowInsets3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DockedSearchBar-EQC0FA8, reason: not valid java name */
    public static final void m2781DockedSearchBarEQC0FA8(final Function2<? super Composer, ? super Integer, Unit> function2, final boolean expanded, final Function1<? super Boolean, Unit> function1, Modifier modifier, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        float f;
        float f2;
        Composer $composer2;
        final SearchBarColors colors2;
        final Modifier modifier3;
        final float shadowElevation2;
        final float tonalElevation2;
        final Shape shape3;
        Shape shape4;
        int $dirty;
        int i2;
        Composer $composer3;
        int i3;
        SearchBarColors colors3;
        float tonalElevation3;
        final SearchBarColors colors4;
        float shadowElevation3;
        float tonalElevation4;
        Composer $composer4 = $composer.startRestartGroup(-2008777812);
        ComposerKt.sourceInformation($composer4, "C(DockedSearchBar)N(inputField,expanded,onExpandedChange,modifier,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,content)661@31616L38,665@31811L696,658@31522L985,686@32545L27,686@32513L59:SearchBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer4.changedInstance(function2) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer4.changed(expanded) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer4.changedInstance(function1) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer4.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i5 = $composer4.changed(shape2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i5;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= ((i & 32) == 0 && $composer4.changed(colors)) ? 131072 : 65536;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
            f = tonalElevation;
        } else if ((1572864 & $changed) == 0) {
            f = tonalElevation;
            $dirty2 |= $composer4.changed(f) ? 1048576 : 524288;
        } else {
            f = tonalElevation;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty2 |= 12582912;
            f2 = shadowElevation;
        } else if (($changed & 12582912) == 0) {
            f2 = shadowElevation;
            $dirty2 |= $composer4.changed(f2) ? 8388608 : 4194304;
        } else {
            f2 = shadowElevation;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty3 = $dirty2;
        if ($composer4.shouldExecute((38347923 & $dirty2) != 38347922, $dirty3 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "652@31274L11,653@31335L8");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty3 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty3 &= -458753;
                }
                colors4 = colors;
                shadowElevation3 = f2;
                tonalElevation4 = f;
                $dirty = $dirty3;
                $composer3 = $composer4;
                i3 = 0;
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 16) == 0) {
                    shape4 = shape2;
                    $dirty = $dirty3;
                } else {
                    shape4 = SearchBarDefaults.INSTANCE.getDockedShape($composer4, 6);
                    $dirty = $dirty3 & (-57345);
                }
                if ((i & 32) != 0) {
                    i3 = 0;
                    i2 = i7;
                    colors3 = SearchBarDefaults.INSTANCE.m2771colorsKlgxPg(0L, 0L, null, $composer4, 3072, 7);
                    $composer3 = $composer4;
                    $dirty &= -458753;
                } else {
                    i2 = i7;
                    $composer3 = $composer4;
                    i3 = 0;
                    colors3 = colors;
                }
                if (i6 == 0) {
                    tonalElevation3 = tonalElevation;
                } else {
                    tonalElevation3 = SearchBarDefaults.INSTANCE.m2776getTonalElevationD9Ej5fM();
                }
                if (i2 == 0) {
                    SearchBarColors searchBarColors = colors3;
                    shape2 = shape4;
                    colors4 = searchBarColors;
                    shadowElevation3 = f2;
                    tonalElevation4 = tonalElevation3;
                } else {
                    SearchBarColors searchBarColors2 = colors3;
                    shape2 = shape4;
                    colors4 = searchBarColors2;
                    shadowElevation3 = SearchBarDefaults.INSTANCE.m2775getShadowElevationD9Ej5fM();
                    tonalElevation4 = tonalElevation3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2008777812, $dirty, -1, "androidx.compose.material3.DockedSearchBar (SearchBar.kt:657)");
            }
            long jM2768getContainerColor0d7_KjU = colors4.m2768getContainerColor0d7_KjU();
            int $dirty4 = $dirty;
            long jM2263contentColorForek8zF_U = ColorSchemeKt.m2263contentColorForek8zF_U(colors4.m2768getContainerColor0d7_KjU(), $composer3, i3);
            SearchBarColors colors5 = colors4;
            Composer $composer5 = $composer3;
            Shape shape5 = shape2;
            SurfaceKt.m2929SurfaceT9BRK9s(SizeKt.m870width3ABfNKs(ZIndexModifierKt.zIndex(modifier2, 1.0f), SearchBarMinWidth), shape5, jM2768getContainerColor0d7_KjU, jM2263contentColorForek8zF_U, tonalElevation4, shadowElevation3, null, ComposableLambdaKt.rememberComposableLambda(401953073, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBar$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer6, int $changed2) {
                    ComposerKt.sourceInformation($composer6, "C666@31821L680:SearchBar.kt#uh7d8r");
                    if (!$composer6.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer6.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(401953073, $changed2, -1, "androidx.compose.material3.DockedSearchBar.<anonymous> (SearchBar.kt:666)");
                    }
                    Function2<Composer, Integer, Unit> function4 = function2;
                    boolean z = expanded;
                    final SearchBarColors searchBarColors3 = colors4;
                    final Function3<ColumnScope, Composer, Integer, Unit> function5 = function3;
                    ComposerKt.sourceInformationMarkerStart($composer6, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                    MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer6, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer6, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer6, 0);
                    CompositionLocalMap localMap$iv$iv = $composer6.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer6, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer6, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer6.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer6.startReusableNode();
                    if ($composer6.getInserting()) {
                        $composer6.createNode(constructor);
                    } else {
                        $composer6.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer6);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i8 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer6, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                    ColumnScope $this$invoke_u24lambda_u240 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer6, -439279917, "C667@31842L12,673@32030L461,669@31868L623:SearchBar.kt#uh7d8r");
                    function4.invoke($composer6, 0);
                    AnimatedVisibilityKt.AnimatedVisibility($this$invoke_u24lambda_u240, z, (Modifier) null, SearchBarKt.DockedEnterTransition, SearchBarKt.DockedExitTransition, (String) null, ComposableLambdaKt.rememberComposableLambda(-1224554113, true, new Function3<AnimatedVisibilityScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBar$1$1$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
                            invoke(animatedVisibilityScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer7, int $changed3) {
                            Function0<ComposeUiNode> function0;
                            ComposerKt.sourceInformation($composer7, "C674@32076L26,678@32301L176:SearchBar.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1224554113, $changed3, -1, "androidx.compose.material3.DockedSearchBar.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:674)");
                            }
                            float windowContainerHeight = SearchBar_androidKt.getWindowContainerHeight($composer7, 0);
                            float other$iv = Dp.m7905constructorimpl(windowContainerHeight * 0.6666667f);
                            float minHeight = ((Dp) RangesKt.coerceAtMost(Dp.m7903boximpl(SearchBarKt.getDockedExpandedTableMinHeight()), Dp.m7903boximpl(other$iv))).m7919unboximpl();
                            Modifier modifier$iv2 = SizeKt.m852heightInVpY3zN4(Modifier.INSTANCE, minHeight, other$iv);
                            SearchBarColors searchBarColors4 = searchBarColors3;
                            Function3<ColumnScope, Composer, Integer, Unit> function6 = function5;
                            ComposerKt.sourceInformationMarkerStart($composer7, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                            Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv2 = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, horizontalAlignment$iv2, $composer7, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv2 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer7, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer7, 0);
                            CompositionLocalMap localMap$iv$iv2 = $composer7.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer7, modifier$iv2);
                            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer7, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer7.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer7.startReusableNode();
                            if ($composer7.getInserting()) {
                                function0 = constructor2;
                                $composer7.createNode(function0);
                            } else {
                                function0 = constructor2;
                                $composer7.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer7);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                            int i9 = ($changed$iv$iv$iv2 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer7, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                            int $changed4 = ((0 >> 6) & 112) | 6;
                            ColumnScope $this$invoke_u24lambda_u241 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer7, -1564650142, "C679@32383L46,680@32450L9:SearchBar.kt#uh7d8r");
                            DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, searchBarColors4.m2769getDividerColor0d7_KjU(), $composer7, 0, 3);
                            function6.invoke($this$invoke_u24lambda_u241, $composer7, Integer.valueOf($changed4 & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            $composer7.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            ComposerKt.sourceInformationMarkerEnd($composer7);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer6, 54), $composer6, ((((0 >> 6) & 112) | 6) & 14) | 1600512, 18);
                    ComposerKt.sourceInformationMarkerEnd($composer6);
                    ComposerKt.sourceInformationMarkerEnd($composer6);
                    $composer6.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer6);
                    ComposerKt.sourceInformationMarkerEnd($composer6);
                    ComposerKt.sourceInformationMarkerEnd($composer6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer5, (($dirty4 >> 9) & 112) | 12582912 | (($dirty4 >> 6) & 57344) | (($dirty4 >> 6) & 458752), 64);
            $composer2 = $composer5;
            ComposerKt.sourceInformationMarkerStart($composer2, -1995113209, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = ($dirty4 & 896) == 256;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchBarKt.DockedSearchBar_EQC0FA8$lambda$24$lambda$23(function1);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            BackHandler_androidKt.BackHandler(expanded, (Function0) it$iv, $composer2, ($dirty4 >> 3) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors2 = colors5;
            modifier3 = modifier2;
            tonalElevation2 = tonalElevation4;
            shadowElevation2 = shadowElevation3;
            shape3 = shape5;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            colors2 = colors;
            modifier3 = modifier2;
            shadowElevation2 = f2;
            tonalElevation2 = tonalElevation;
            shape3 = shape2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.DockedSearchBar_EQC0FA8$lambda$25(function2, expanded, function1, modifier3, shape3, colors2, tonalElevation2, shadowElevation2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit DockedSearchBar_EQC0FA8$lambda$24$lambda$23(Function1 $onExpandedChange) {
        $onExpandedChange.invoke(false);
        return Unit.INSTANCE;
    }

    public static final SearchBarState rememberSearchBarState(final SearchBarValue initialValue, final AnimationSpec<Float> animationSpec, final AnimationSpec<Float> animationSpec2, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -546016819, "C(rememberSearchBarState)N(initialValue,animationSpecForExpand,animationSpecForCollapse)817@37213L7,818@37312L7,829@37663L208,820@37352L519:SearchBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialValue = SearchBarValue.Collapsed;
        }
        if ((i & 2) != 0) {
            AnimationSpec animationSpecForExpand = MotionSchemeKt.value(MotionSchemeKeyTokens.SlowSpatial, $composer, 6);
            animationSpec = animationSpecForExpand;
        }
        if ((i & 4) != 0) {
            AnimationSpec animationSpecForCollapse = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer, 6);
            animationSpec2 = animationSpecForCollapse;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-546016819, $changed, -1, "androidx.compose.material3.rememberSearchBarState (SearchBar.kt:819)");
        }
        Object[] objArr = {initialValue, animationSpec, animationSpec2};
        Saver<SearchBarState, ?> Saver = SearchBarState.INSTANCE.Saver(animationSpec, animationSpec2);
        ComposerKt.sourceInformationMarkerStart($composer, -736131555, "CC(remember):SearchBar.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue.ordinal())) || ($changed & 6) == 4) | $composer.changedInstance(animationSpec) | $composer.changedInstance(animationSpec2);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SearchBarKt.rememberSearchBarState$lambda$27$lambda$26(initialValue, animationSpec, animationSpec2);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SearchBarState searchBarState = (SearchBarState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return searchBarState;
    }

    static final SearchBarState rememberSearchBarState$lambda$27$lambda$26(SearchBarValue $initialValue, AnimationSpec $animationSpecForExpand, AnimationSpec $animationSpecForCollapse) {
        return new SearchBarState($initialValue, (AnimationSpec<Float>) $animationSpecForExpand, (AnimationSpec<Float>) $animationSpecForCollapse);
    }

    @Deprecated(message = "Use overload which takes inputField as a parameter", replaceWith = @ReplaceWith(expression = "SearchBar(\n    inputField = {\n        SearchBarDefaults.InputField(\n            query = query,\n            onQueryChange = onQueryChange,\n            onSearch = onSearch,\n            expanded = active,\n            onExpandedChange = onActiveChange,\n            enabled = enabled,\n            placeholder = placeholder,\n            leadingIcon = leadingIcon,\n            trailingIcon = trailingIcon,\n            colors = colors.inputFieldColors,\n            interactionSource = interactionSource,\n        )\n    },\n    expanded = active,\n    onExpandedChange = onActiveChange,\n    modifier = modifier,\n    shape = shape,\n    colors = colors,\n    tonalElevation = tonalElevation,\n    shadowElevation = shadowElevation,\n    windowInsets = windowInsets,\n    content = content,\n)", imports = {}))
    /* JADX INFO: renamed from: SearchBar-WuY5d9Q, reason: not valid java name */
    public static final void m2787SearchBarWuY5d9Q(final String query, final Function1<? super String, Unit> function1, final Function1<? super String, Unit> function2, final boolean active, final Function1<? super Boolean, Unit> function3, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, WindowInsets windowInsets, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function7, Composer $composer, final int $changed, final int $changed1, final int i) {
        String str;
        Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function8;
        int $dirty1;
        Composer $composer2;
        final Shape shape2;
        final float tonalElevation2;
        final float shadowElevation2;
        final WindowInsets windowInsets2;
        final MutableInteractionSource interactionSource2;
        final Modifier modifier3;
        final boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final SearchBarColors colors2;
        int $dirty2;
        Shape shape3;
        int $dirty;
        Composer $composer3;
        int i2;
        SearchBarColors colors3;
        float tonalElevation3;
        WindowInsets windowInsets3;
        final MutableInteractionSource interactionSource3;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final SearchBarColors colors4;
        float shadowElevation3;
        WindowInsets windowInsets4;
        Shape shape4;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        Modifier modifier4;
        int $dirty3;
        final boolean enabled3;
        int $dirty4;
        Composer $composer4 = $composer.startRestartGroup(1506988286);
        ComposerKt.sourceInformation($composer4, "C(SearchBar)N(query,onQueryChange,onSearch,active,onActiveChange,modifier,enabled,placeholder,leadingIcon,trailingIcon,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,windowInsets,interactionSource,content)1968@93682L591,1967@93650L925:SearchBar.kt#uh7d8r");
        int $dirty5 = $changed;
        int $dirty6 = $changed1;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
            str = query;
        } else if (($changed & 6) == 0) {
            str = query;
            $dirty5 |= $composer4.changed(str) ? 4 : 2;
        } else {
            str = query;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty5 |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty5 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty5 |= $composer4.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty5 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty5 |= $composer4.changed(active) ? 2048 : 1024;
        }
        int i3 = 8192;
        if ((i & 16) != 0) {
            $dirty5 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty5 |= $composer4.changedInstance(function3) ? 16384 : 8192;
        }
        int i4 = i & 32;
        if (i4 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer4.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty5 |= 1572864;
            z = enabled;
        } else if (($changed & 1572864) == 0) {
            z = enabled;
            $dirty5 |= $composer4.changed(z) ? 1048576 : 524288;
        } else {
            z = enabled;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty5 |= 12582912;
            function8 = function4;
        } else if (($changed & 12582912) == 0) {
            function8 = function4;
            $dirty5 |= $composer4.changedInstance(function8) ? 8388608 : 4194304;
        } else {
            function8 = function4;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty5 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty5 |= $composer4.changedInstance(function5) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i8 = i & 512;
        if (i8 != 0) {
            $dirty5 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty5 |= $composer4.changedInstance(function6) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty6 |= ((i & 1024) == 0 && $composer4.changed(shape)) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty6 |= ((i & 2048) == 0 && $composer4.changed(colors)) ? 32 : 16;
        }
        int i9 = i & 4096;
        if (i9 != 0) {
            $dirty6 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty6 |= $composer4.changed(tonalElevation) ? 256 : 128;
        }
        int i10 = i & 8192;
        if (i10 != 0) {
            $dirty6 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty6 |= $composer4.changed(shadowElevation) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer4.changed(windowInsets)) {
                i3 = 16384;
            }
            $dirty6 |= i3;
        }
        int i11 = i & 32768;
        if (i11 != 0) {
            $dirty1 = $dirty6 | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 = $dirty6 | ($composer4.changed(interactionSource) ? 131072 : 65536);
        } else {
            $dirty1 = $dirty6;
        }
        if ((i & 65536) != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer4.changedInstance(function7) ? 1048576 : 524288;
        }
        if ($composer4.shouldExecute((($dirty5 & 306783379) == 306783378 && ($dirty1 & 599187) == 599186) ? false : true, $dirty5 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "1959@93276L15,1960@93341L8,1963@93522L12");
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i5 != 0 ? true : z;
                Function2<? super Composer, ? super Integer, Unit> function15 = i6 != 0 ? null : function8;
                Function2<? super Composer, ? super Integer, Unit> function16 = i7 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function17 = i8 != 0 ? null : function6;
                if ((i & 1024) != 0) {
                    $dirty2 = $dirty1 & (-15);
                    shape3 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer4, 6);
                } else {
                    $dirty2 = $dirty1;
                    shape3 = shape;
                }
                if ((i & 2048) != 0) {
                    i2 = i10;
                    $composer3 = $composer4;
                    $dirty = $dirty5;
                    colors3 = SearchBarDefaults.INSTANCE.m2771colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                    $dirty2 &= -113;
                } else {
                    $dirty = $dirty5;
                    $composer3 = $composer4;
                    i2 = i10;
                    colors3 = colors;
                }
                tonalElevation3 = i9 != 0 ? SearchBarDefaults.INSTANCE.m2776getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i2 != 0 ? SearchBarDefaults.INSTANCE.m2775getShadowElevationD9Ej5fM() : shadowElevation;
                if ((i & 16384) != 0) {
                    windowInsets3 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty2 &= -57345;
                } else {
                    windowInsets3 = windowInsets;
                }
                if (i11 != 0) {
                    function12 = function16;
                    colors4 = colors3;
                    shadowElevation3 = shadowElevation4;
                    windowInsets4 = windowInsets3;
                    interactionSource3 = null;
                    shape4 = shape3;
                    function13 = function15;
                    function14 = function17;
                    modifier4 = modifier5;
                    $dirty3 = $dirty2;
                    enabled3 = enabled4;
                } else {
                    interactionSource3 = interactionSource;
                    function12 = function16;
                    colors4 = colors3;
                    shadowElevation3 = shadowElevation4;
                    windowInsets4 = windowInsets3;
                    shape4 = shape3;
                    function13 = function15;
                    function14 = function17;
                    modifier4 = modifier5;
                    $dirty3 = $dirty2;
                    enabled3 = enabled4;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 1024) != 0) {
                    $dirty1 &= -15;
                }
                if ((i & 2048) != 0) {
                    $dirty1 &= -113;
                }
                if ((i & 16384) != 0) {
                    $dirty1 &= -57345;
                }
                function12 = function5;
                function14 = function6;
                shape4 = shape;
                colors4 = colors;
                tonalElevation3 = tonalElevation;
                shadowElevation3 = shadowElevation;
                interactionSource3 = interactionSource;
                $dirty = $dirty5;
                modifier4 = modifier2;
                enabled3 = z;
                function13 = function8;
                $composer3 = $composer4;
                $dirty3 = $dirty1;
                windowInsets4 = windowInsets;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty4 = $dirty;
                ComposerKt.traceEventStart(1506988286, $dirty4, $dirty3, "androidx.compose.material3.SearchBar (SearchBar.kt:1967)");
            } else {
                $dirty4 = $dirty;
            }
            final String str2 = str;
            SearchBarColors colors5 = colors4;
            Composer $composer5 = $composer3;
            float tonalElevation4 = tonalElevation3;
            m2788SearchBarY92LkZI(ComposableLambdaKt.rememberComposableLambda(-1597173218, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBar$6
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer6, int $changed2) {
                    ComposerKt.sourceInformation($composer6, "C1969@93714L549:SearchBar.kt#uh7d8r");
                    if (!$composer6.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer6.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1597173218, $changed2, -1, "androidx.compose.material3.SearchBar.<anonymous> (SearchBar.kt:1969)");
                    }
                    SearchBarDefaults.INSTANCE.InputField(str2, function1, function2, active, function3, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), enabled3, function13, function12, function14, colors4.getInputFieldColors(), interactionSource3, $composer6, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 384, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), active, function3, modifier4, shape4, colors5, tonalElevation4, shadowElevation3, windowInsets4, function7, $composer5, (($dirty4 >> 6) & 112) | 6 | (($dirty4 >> 6) & 896) | (($dirty4 >> 6) & 7168) | (($dirty3 << 12) & 57344) | (($dirty3 << 12) & 458752) | (($dirty3 << 12) & 3670016) | (($dirty3 << 12) & 29360128) | (($dirty3 << 12) & 234881024) | (($dirty3 << 9) & 1879048192), 0);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            tonalElevation2 = tonalElevation4;
            shadowElevation2 = shadowElevation3;
            windowInsets2 = windowInsets4;
            colors2 = colors5;
            enabled2 = enabled3;
            function10 = function12;
            function11 = function14;
            interactionSource2 = interactionSource3;
            shape2 = shape4;
            function9 = function13;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            tonalElevation2 = tonalElevation;
            shadowElevation2 = shadowElevation;
            windowInsets2 = windowInsets;
            interactionSource2 = interactionSource;
            modifier3 = modifier2;
            enabled2 = z;
            function9 = function8;
            function10 = function5;
            function11 = function6;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.SearchBar_WuY5d9Q$lambda$28(query, function1, function2, active, function3, modifier3, enabled2, function9, function10, function11, shape2, colors2, tonalElevation2, shadowElevation2, windowInsets2, interactionSource2, function7, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(message = "Use overload which takes inputField as a parameter", replaceWith = @ReplaceWith(expression = "DockedSearchBar(\n    inputField = {\n        SearchBarDefaults.InputField(\n            query = query,\n            onQueryChange = onQueryChange,\n            onSearch = onSearch,\n            expanded = active,\n            onExpandedChange = onActiveChange,\n            enabled = enabled,\n            placeholder = placeholder,\n            leadingIcon = leadingIcon,\n            trailingIcon = trailingIcon,\n            colors = colors.inputFieldColors,\n            interactionSource = interactionSource,\n        )\n    },\n    expanded = active,\n    onExpandedChange = onActiveChange,\n    modifier = modifier,\n    shape = shape,\n    colors = colors,\n    tonalElevation = tonalElevation,\n    shadowElevation = shadowElevation,\n    content = content,\n)", imports = {}))
    /* JADX INFO: renamed from: DockedSearchBar-eWTbjVg, reason: not valid java name */
    public static final void m2782DockedSearchBareWTbjVg(final String query, final Function1<? super String, Unit> function1, final Function1<? super String, Unit> function2, final boolean active, final Function1<? super Boolean, Unit> function3, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function7, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function1<? super String, Unit> function8;
        Modifier modifier2;
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function9;
        int $dirty1;
        int $dirty;
        Composer $composer2;
        final Shape shape2;
        final float tonalElevation2;
        final float shadowElevation2;
        final MutableInteractionSource interactionSource2;
        final Modifier modifier3;
        final boolean enabled2;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final SearchBarColors colors2;
        int $dirty2;
        Shape shape3;
        boolean z2;
        Composer $composer3;
        int i2;
        SearchBarColors colors3;
        float tonalElevation3;
        Modifier modifier4;
        final MutableInteractionSource interactionSource3;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        SearchBarColors colors4;
        float shadowElevation3;
        Shape shape4;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        int $dirty3;
        final boolean enabled3;
        final Function2<? super Composer, ? super Integer, Unit> function15;
        Composer $composer4 = $composer.startRestartGroup(1929583712);
        ComposerKt.sourceInformation($composer4, "C(DockedSearchBar)N(query,onQueryChange,onSearch,active,onActiveChange,modifier,enabled,placeholder,leadingIcon,trailingIcon,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,interactionSource,content)2048@96829L591,2047@96791L894:SearchBar.kt#uh7d8r");
        int $dirty4 = $changed;
        int $dirty5 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer4.changed(query) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function8 = function1;
        } else if (($changed & 48) == 0) {
            function8 = function1;
            $dirty4 |= $composer4.changedInstance(function8) ? 32 : 16;
        } else {
            function8 = function1;
        }
        if ((i & 4) != 0) {
            $dirty4 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty4 |= $composer4.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty4 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty4 |= $composer4.changed(active) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty4 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty4 |= $composer4.changedInstance(function3) ? 16384 : 8192;
        }
        int i3 = i & 32;
        if (i3 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer4.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 64;
        if (i4 != 0) {
            $dirty4 |= 1572864;
            z = enabled;
        } else if (($changed & 1572864) == 0) {
            z = enabled;
            $dirty4 |= $composer4.changed(z) ? 1048576 : 524288;
        } else {
            z = enabled;
        }
        int i5 = i & 128;
        if (i5 != 0) {
            $dirty4 |= 12582912;
            function9 = function4;
        } else if (($changed & 12582912) == 0) {
            function9 = function4;
            $dirty4 |= $composer4.changedInstance(function9) ? 8388608 : 4194304;
        } else {
            function9 = function4;
        }
        int i6 = i & 256;
        if (i6 != 0) {
            $dirty4 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty4 |= $composer4.changedInstance(function5) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty4 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty4 |= $composer4.changedInstance(function6) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty5 |= ((i & 1024) == 0 && $composer4.changed(shape)) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty5 |= ((i & 2048) == 0 && $composer4.changed(colors)) ? 32 : 16;
        }
        int i8 = i & 4096;
        if (i8 != 0) {
            $dirty5 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty5 |= $composer4.changed(tonalElevation) ? 256 : 128;
        }
        int i9 = i & 8192;
        if (i9 != 0) {
            $dirty5 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty5 |= $composer4.changed(shadowElevation) ? 2048 : 1024;
        }
        int i10 = i & 16384;
        if (i10 != 0) {
            $dirty5 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty5 |= $composer4.changed(interactionSource) ? 16384 : 8192;
        }
        if ((i & 32768) != 0) {
            $dirty1 = $dirty5 | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 = $dirty5 | ($composer4.changedInstance(function7) ? 131072 : 65536);
        } else {
            $dirty1 = $dirty5;
        }
        if ($composer4.shouldExecute((($dirty4 & 306783379) == 306783378 && ($dirty1 & 74899) == 74898) ? false : true, $dirty4 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "2040@96486L11,2041@96547L8");
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i4 != 0 ? true : z;
                Function2<? super Composer, ? super Integer, Unit> function16 = i5 != 0 ? null : function9;
                Function2<? super Composer, ? super Integer, Unit> function17 = i6 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function18 = i7 != 0 ? null : function6;
                if ((i & 1024) != 0) {
                    $dirty2 = $dirty1 & (-15);
                    shape3 = SearchBarDefaults.INSTANCE.getDockedShape($composer4, 6);
                } else {
                    $dirty2 = $dirty1;
                    shape3 = shape;
                }
                if ((i & 2048) != 0) {
                    i2 = i10;
                    $composer3 = $composer4;
                    z2 = true;
                    $dirty = $dirty4;
                    colors3 = SearchBarDefaults.INSTANCE.m2771colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                    $dirty2 &= -113;
                } else {
                    $dirty = $dirty4;
                    z2 = true;
                    $composer3 = $composer4;
                    i2 = i10;
                    colors3 = colors;
                }
                tonalElevation3 = i8 != 0 ? SearchBarDefaults.INSTANCE.m2776getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i9 != 0 ? SearchBarDefaults.INSTANCE.m2775getShadowElevationD9Ej5fM() : shadowElevation;
                if (i2 != 0) {
                    function13 = function17;
                    colors4 = colors3;
                    shadowElevation3 = shadowElevation4;
                    interactionSource3 = null;
                    shape4 = shape3;
                    function14 = function18;
                    $dirty3 = $dirty2;
                    modifier4 = modifier5;
                    enabled3 = enabled4;
                    function15 = function16;
                } else {
                    modifier4 = modifier5;
                    interactionSource3 = interactionSource;
                    function13 = function17;
                    colors4 = colors3;
                    shadowElevation3 = shadowElevation4;
                    shape4 = shape3;
                    function14 = function18;
                    $dirty3 = $dirty2;
                    enabled3 = enabled4;
                    function15 = function16;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 1024) != 0) {
                    $dirty1 &= -15;
                }
                if ((i & 2048) != 0) {
                    $dirty1 &= -113;
                }
                function13 = function5;
                function14 = function6;
                shape4 = shape;
                shadowElevation3 = shadowElevation;
                interactionSource3 = interactionSource;
                $dirty = $dirty4;
                z2 = true;
                modifier4 = modifier2;
                enabled3 = z;
                function15 = function9;
                $composer3 = $composer4;
                $dirty3 = $dirty1;
                colors4 = colors;
                tonalElevation3 = tonalElevation;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1929583712, $dirty, $dirty3, "androidx.compose.material3.DockedSearchBar (SearchBar.kt:2047)");
            }
            final Function1<? super String, Unit> function19 = function8;
            final SearchBarColors colors5 = colors4;
            Composer $composer5 = $composer3;
            float tonalElevation4 = tonalElevation3;
            m2781DockedSearchBarEQC0FA8(ComposableLambdaKt.rememberComposableLambda(-1275782414, z2, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBar$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer6, int $changed2) {
                    ComposerKt.sourceInformation($composer6, "C2049@96861L549:SearchBar.kt#uh7d8r");
                    if (!$composer6.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer6.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1275782414, $changed2, -1, "androidx.compose.material3.DockedSearchBar.<anonymous> (SearchBar.kt:2049)");
                    }
                    SearchBarDefaults.INSTANCE.InputField(query, function19, function2, active, function3, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), enabled3, function15, function13, function14, colors5.getInputFieldColors(), interactionSource3, $composer6, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 384, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), active, function3, modifier4, shape4, colors5, tonalElevation4, shadowElevation3, function7, $composer5, (($dirty >> 6) & 112) | 6 | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty3 << 12) & 57344) | (($dirty3 << 12) & 458752) | (($dirty3 << 12) & 3670016) | (($dirty3 << 12) & 29360128) | (($dirty3 << 9) & 234881024), 0);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            tonalElevation2 = tonalElevation4;
            shadowElevation2 = shadowElevation3;
            colors2 = colors5;
            enabled2 = enabled3;
            function11 = function13;
            function12 = function14;
            interactionSource2 = interactionSource3;
            shape2 = shape4;
            function10 = function15;
        } else {
            $dirty = $dirty4;
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            tonalElevation2 = tonalElevation;
            shadowElevation2 = shadowElevation;
            interactionSource2 = interactionSource;
            modifier3 = modifier2;
            enabled2 = z;
            function10 = function9;
            function11 = function5;
            function12 = function6;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.DockedSearchBar_eWTbjVg$lambda$29(query, function1, function2, active, function3, modifier3, enabled2, function10, function11, function12, shape2, colors2, tonalElevation2, shadowElevation2, interactionSource2, function7, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isExpanded(SearchBarState $this$isExpanded) {
        return $this$isExpanded.getCurrentValue() == SearchBarValue.Expanded;
    }

    /* JADX INFO: renamed from: SearchBarImpl-j1jLAyQ, reason: not valid java name */
    public static final void m2790SearchBarImplj1jLAyQ(final Animatable<Float, AnimationVector1D> animatable, final MutableFloatState finalBackProgress, final MutableState<BackEventCompat> mutableState, final MutableState<BackEventCompat> mutableState2, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        MutableState<BackEventCompat> mutableState3;
        final Modifier modifier2;
        SearchBarColors searchBarColors;
        WindowInsets windowInsets2;
        Composer $composer2;
        final Shape shape2;
        final SearchBarColors colors2;
        final float tonalElevation2;
        final float shadowElevation2;
        final WindowInsets windowInsets3;
        Modifier.Companion modifier3;
        Shape shape3;
        int $dirty;
        int i2;
        SearchBarColors colors3;
        float tonalElevation3;
        float shadowElevation3;
        final float tonalElevation4;
        final float shadowElevation4;
        int $dirty1;
        int $dirty2;
        Modifier modifier4;
        final SearchBarColors colors4;
        Shape shape4;
        Shape shape5;
        Shape defaultInputFieldShape;
        Object value$iv;
        Function2 wrappedContent;
        Composer $composer3 = $composer.startRestartGroup(501752896);
        ComposerKt.sourceInformation($composer3, "C(SearchBarImpl)N(animationProgress,finalBackProgress,firstBackEvent,currentBackEvent,modifier,inputField,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,windowInsets,content)2094@98568L7,2096@98628L15,2097@98695L15,2098@98741L101,2102@98875L666,2118@99580L333,2129@99938L99,2142@100370L362:SearchBar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= ($changed & 8) == 0 ? $composer3.changed(animatable) : $composer3.changedInstance(animatable) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(finalBackProgress) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 384;
            mutableState3 = mutableState;
        } else if (($changed & 384) == 0) {
            mutableState3 = mutableState;
            $dirty3 |= $composer3.changed(mutableState3) ? 256 : 128;
        } else {
            mutableState3 = mutableState;
        }
        if ((i & 8) != 0) {
            $dirty3 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changed(mutableState2) ? 2048 : 1024;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty3 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        if ((i & 32) != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 131072 : 65536;
        }
        int $dirty4 = $changed1;
        if (($changed & 1572864) == 0) {
            $dirty3 |= ((i & 64) == 0 && $composer3.changed(shape)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                searchBarColors = colors;
                int i4 = $composer3.changed(searchBarColors) ? 8388608 : 4194304;
                $dirty3 |= i4;
            } else {
                searchBarColors = colors;
            }
            $dirty3 |= i4;
        } else {
            searchBarColors = colors;
        }
        int i5 = i & 256;
        if (i5 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changed(tonalElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i6 = i & 512;
        if (i6 != 0) {
            $dirty3 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer3.changed(shadowElevation) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            if ((i & 1024) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer3.changed(windowInsets2) ? 4 : 2;
                $dirty4 |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty4 |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 2048) != 0) {
            $dirty4 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        if ($composer3.shouldExecute(((306783379 & $dirty3) == 306783378 && ($dirty4 & 19) == 18) ? false : true, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "2087@98224L15,2088@98289L8,2091@98470L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty3 &= -29360129;
                }
                if ((i & 1024) != 0) {
                    $dirty4 &= -15;
                }
                modifier4 = modifier2;
                colors4 = searchBarColors;
                $dirty1 = $dirty4;
                tonalElevation4 = tonalElevation;
                shadowElevation4 = shadowElevation;
                $dirty2 = $dirty3;
                shape4 = shape;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 64) == 0) {
                    shape3 = shape;
                    $dirty = $dirty3;
                } else {
                    shape3 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                    $dirty = $dirty3 & (-3670017);
                }
                if ((i & 128) != 0) {
                    i2 = 6;
                    colors3 = SearchBarDefaults.INSTANCE.m2771colorsKlgxPg(0L, 0L, null, $composer3, 3072, 7);
                    $dirty &= -29360129;
                } else {
                    i2 = 6;
                    colors3 = colors;
                }
                if (i5 == 0) {
                    tonalElevation3 = tonalElevation;
                } else {
                    tonalElevation3 = SearchBarDefaults.INSTANCE.m2776getTonalElevationD9Ej5fM();
                }
                if (i6 == 0) {
                    shadowElevation3 = shadowElevation;
                } else {
                    shadowElevation3 = SearchBarDefaults.INSTANCE.m2775getShadowElevationD9Ej5fM();
                }
                if ((i & 1024) == 0) {
                    windowInsets2 = windowInsets;
                    tonalElevation4 = tonalElevation3;
                    shadowElevation4 = shadowElevation3;
                    $dirty1 = $dirty4;
                    $dirty2 = $dirty;
                    modifier4 = modifier3;
                    colors4 = colors3;
                    shape4 = shape3;
                } else {
                    shadowElevation4 = shadowElevation3;
                    windowInsets2 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, i2);
                    $dirty1 = $dirty4 & (-15);
                    $dirty2 = $dirty;
                    modifier4 = modifier3;
                    tonalElevation4 = tonalElevation3;
                    colors4 = colors3;
                    shape4 = shape3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(501752896, $dirty2, $dirty1, "androidx.compose.material3.SearchBarImpl (SearchBar.kt:2093)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            WindowInsets windowInsets4 = windowInsets2;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) objConsume;
            Shape defaultInputFieldShape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
            int $dirty5 = $dirty1;
            Object defaultFullScreenShape = SearchBarDefaults.INSTANCE.getFullScreenShape($composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, 1148225381, "CC(remember):SearchBar.kt#9igjgp");
            Object value$iv2 = $composer3.rememberedValue();
            if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(SearchBarKt.SearchBarImpl_j1jLAyQ$lambda$31$lambda$30(animatable));
                    }
                });
                $composer3.updateRememberedValue(value$iv2);
            }
            State useFullScreenShape$delegate = (State) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean zSearchBarImpl_j1jLAyQ$lambda$32 = SearchBarImpl_j1jLAyQ$lambda$32(useFullScreenShape$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 1148230234, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(zSearchBarImpl_j1jLAyQ$lambda$32) | ((((3670016 & $dirty2) ^ 1572864) > 1048576 && $composer3.changed(shape4)) || ($dirty2 & 1572864) == 1048576);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                if (Intrinsics.areEqual(shape4, defaultInputFieldShape2)) {
                    shape5 = shape4;
                    defaultInputFieldShape = defaultInputFieldShape2;
                    value$iv = (Shape) new GenericShape(new Function3() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return SearchBarKt.SearchBarImpl_j1jLAyQ$lambda$35$lambda$34(density, animatable, (Path) obj, (Size) obj2, (LayoutDirection) obj3);
                        }
                    });
                } else {
                    shape5 = shape4;
                    defaultInputFieldShape = defaultInputFieldShape2;
                    value$iv = SearchBarImpl_j1jLAyQ$lambda$32(useFullScreenShape$delegate) ? defaultFullScreenShape : shape5;
                }
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                shape5 = shape4;
                defaultInputFieldShape = defaultInputFieldShape2;
            }
            final Shape animatedShape = (Shape) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Function2 surface = ComposableLambdaKt.rememberComposableLambda(-1304392981, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$SearchBarImpl$surface$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C2122@99720L38,2119@99594L309:SearchBar.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1304392981, $changed2, -1, "androidx.compose.material3.SearchBarImpl.<anonymous> (SearchBar.kt:2119)");
                    }
                    SurfaceKt.m2929SurfaceT9BRK9s(null, animatedShape, colors4.m2768getContainerColor0d7_KjU(), ColorSchemeKt.m2263contentColorForek8zF_U(colors4.m2768getContainerColor0d7_KjU(), $composer4, 0), tonalElevation4, shadowElevation4, null, ComposableSingletons$SearchBarKt.INSTANCE.getLambda$1165377840$material3(), $composer4, 12582912, 65);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54);
            ComposerKt.sourceInformationMarkerStart($composer3, 1148263683, "CC(remember):SearchBar.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(SearchBarKt.SearchBarImpl_j1jLAyQ$lambda$37$lambda$36(animatable));
                    }
                });
                $composer3.updateRememberedValue(value$iv3);
                it$iv2 = value$iv3;
            }
            State showContent$delegate = (State) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (SearchBarImpl_j1jLAyQ$lambda$38(showContent$delegate)) {
                $composer3.startReplaceGroup(1236615731);
                ComposerKt.sourceInformation($composer3, "2134@100129L215");
                wrappedContent = ComposableLambdaKt.rememberComposableLambda(1831594093, true, new SearchBarKt$SearchBarImpl$wrappedContent$1(animatable, colors4, function3), $composer3, 54);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1236851485);
                $composer3.endReplaceGroup();
                wrappedContent = null;
            }
            Modifier modifier5 = modifier4;
            SearchBarColors colors5 = colors4;
            float tonalElevation5 = tonalElevation4;
            float shadowElevation5 = shadowElevation4;
            SearchBarLayout(animatable, finalBackProgress, mutableState3, mutableState2, modifier5, windowInsets4, function2, surface, wrappedContent, $composer3, Animatable.$stable | 12582912 | ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (($dirty5 << 15) & 458752) | (($dirty2 << 3) & 3670016));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets3 = windowInsets4;
            colors2 = colors5;
            tonalElevation2 = tonalElevation5;
            shape2 = shape5;
            shadowElevation2 = shadowElevation5;
            modifier2 = modifier5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape2 = shape;
            colors2 = colors;
            tonalElevation2 = tonalElevation;
            shadowElevation2 = shadowElevation;
            windowInsets3 = windowInsets;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.SearchBarImpl_j1jLAyQ$lambda$39(animatable, finalBackProgress, mutableState, mutableState2, modifier2, function2, shape2, colors2, tonalElevation2, shadowElevation2, windowInsets3, function3, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean SearchBarImpl_j1jLAyQ$lambda$32(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final boolean SearchBarImpl_j1jLAyQ$lambda$31$lambda$30(Animatable $animationProgress) {
        return ((Number) $animationProgress.getValue()).floatValue() == 1.0f;
    }

    static final Unit SearchBarImpl_j1jLAyQ$lambda$35$lambda$34(Density $density, Animatable $animationProgress, Path $this$GenericShape, Size size, LayoutDirection layoutDirection) {
        float arg0$iv = SearchBarCornerRadius;
        float other$iv = 1.0f - ((Number) $animationProgress.getValue()).floatValue();
        float radius = $density.mo406toPx0680j_4(Dp.m7905constructorimpl(arg0$iv * other$iv));
        Rect rectM4942toRectuvyYCjk = androidx.compose.ui.geometry.SizeKt.m4942toRectuvyYCjk(size.m4926unboximpl());
        long v1$iv$iv = Float.floatToRawIntBits(radius);
        long v2$iv$iv = Float.floatToRawIntBits(radius);
        Path.addRoundRect$default($this$GenericShape, RoundRectKt.m4907RoundRectsniSvfs(rectM4942toRectuvyYCjk, CornerRadius.m4806constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv))), null, 2, null);
        return Unit.INSTANCE;
    }

    private static final boolean SearchBarImpl_j1jLAyQ$lambda$38(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final boolean SearchBarImpl_j1jLAyQ$lambda$37$lambda$36(Animatable $animationProgress) {
        return ((Number) $animationProgress.getValue()).floatValue() > 0.0f;
    }

    /* JADX WARN: Code duplicated, block: B:137:0x030d  */
    /* JADX WARN: Code duplicated, block: B:140:0x0319  */
    /* JADX WARN: Code duplicated, block: B:141:0x031f  */
    /* JADX WARN: Code duplicated, block: B:151:0x043c  */
    /* JADX WARN: Code duplicated, block: B:154:0x0448  */
    /* JADX WARN: Code duplicated, block: B:155:0x044e  */
    /* JADX WARN: Code duplicated, block: B:165:0x0506  */
    /* JADX WARN: Code duplicated, block: B:166:0x0513  */
    /* JADX WARN: Code duplicated, block: B:168:0x0585  */
    /* JADX WARN: Code duplicated, block: B:171:0x0591  */
    /* JADX WARN: Code duplicated, block: B:172:0x0597  */
    /* JADX WARN: Code duplicated, block: B:183:0x0668  */
    private static final void SearchBarLayout(final Animatable<Float, AnimationVector1D> animatable, final MutableFloatState finalBackProgress, final MutableState<BackEventCompat> mutableState, final MutableState<BackEventCompat> mutableState2, final Modifier modifier, final WindowInsets windowInsets, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed) {
        MutableState<BackEventCompat> mutableState3;
        MutableState<BackEventCompat> mutableState4;
        boolean z;
        MutableWindowInsets unconsumedInsets;
        int i;
        int $dirty;
        Function0<ComposeUiNode> function0;
        int $changed$iv$iv;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function5;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        int compositeKeyHash$iv$iv3;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function6;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1217602934);
        ComposerKt.sourceInformation($composer3, "C(SearchBarLayout)N(animationProgress,finalBackProgress,firstBackEvent,currentBackEvent,modifier,windowInsets,inputField,surface,content)2171@101429L34,2176@101591L120,2191@102227L5012,2172@101468L5771:SearchBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= ($changed & 8) == 0 ? $composer3.changed(animatable) : $composer3.changedInstance(animatable) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(finalBackProgress) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            mutableState3 = mutableState;
            $dirty2 |= $composer3.changed(mutableState3) ? 256 : 128;
        } else {
            mutableState3 = mutableState;
        }
        if (($changed & 3072) == 0) {
            mutableState4 = mutableState2;
            $dirty2 |= $composer3.changed(mutableState4) ? 2048 : 1024;
        } else {
            mutableState4 = mutableState2;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer3.changed(windowInsets) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer3.shouldExecute((38347923 & $dirty2) != 38347922, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1217602934, $dirty2, -1, "androidx.compose.material3.SearchBarLayout (SearchBar.kt:2167)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1885337272, "CC(remember):SearchBar.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                z = true;
                Object value$iv = new MutableWindowInsets(null, 1, null);
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                z = true;
            }
            final MutableWindowInsets unconsumedInsets2 = (MutableWindowInsets) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierZIndex = ZIndexModifierKt.zIndex(modifier, 1.0f);
            ComposerKt.sourceInformationMarkerStart($composer3, 1885342542, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = (458752 & $dirty2) == 131072 ? z : false;
            Object it$iv2 = $composer3.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchBarKt.SearchBarLayout$lambda$42$lambda$41(unconsumedInsets2, windowInsets, (WindowInsets) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierConsumeWindowInsets = WindowInsetsPaddingKt.consumeWindowInsets(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifierZIndex, (Function1) it$iv2), windowInsets);
            ComposerKt.sourceInformationMarkerStart($composer3, 1885367786, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 14) == 4 || (($dirty2 & 8) != 0 && $composer3.changedInstance(animatable))) | (($dirty2 & 7168) == 2048) | (($dirty2 & 112) == 32) | (($dirty2 & 896) == 256);
            Object value$iv3 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                int $dirty3 = $dirty2;
                unconsumedInsets = unconsumedInsets2;
                MutableState<BackEventCompat> mutableState5 = mutableState3;
                i = 0;
                $dirty = $dirty3;
                value$iv3 = new SearchBarKt$SearchBarLayout$2$1(animatable, unconsumedInsets, mutableState4, finalBackProgress, mutableState5);
                $composer3.updateRememberedValue(value$iv3);
            } else {
                $dirty = $dirty2;
                i = 0;
                unconsumedInsets = unconsumedInsets2;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            int $changed$iv = i;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, i);
            CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifierConsumeWindowInsets);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv2 = (($changed$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor4;
                $composer3.createNode(function0);
            } else {
                function0 = constructor4;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                $changed$iv$iv = $changed$iv$iv2;
            } else {
                $changed$iv$iv = $changed$iv$iv2;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = ($changed$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2058590599, "C2181@101796L85,2182@101894L119:SearchBar.kt#uh7d8r");
                Modifier modifier$iv = LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdSurface);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = ((((390 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i4 = ((390 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 329239585, "C2181@101870L9:SearchBar.kt#uh7d8r");
                function3.invoke($composer3, Integer.valueOf(($dirty >> 21) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifier$iv2 = LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdInputField);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, true);
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = ((((390 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function5 = constructor2;
                    $composer3.createNode(function5);
                } else {
                    function5 = constructor2;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i6 = ((390 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 397275701, "C2183@101987L12:SearchBar.kt#uh7d8r");
                function2.invoke($composer3, Integer.valueOf(($dirty >> 18) & 14));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (function4 == null) {
                    $composer3.startReplaceGroup(2058820276);
                    $composer3.endReplaceGroup();
                    $composer2 = $composer3;
                } else {
                    $composer3.startReplaceGroup(2058820277);
                    ComposerKt.sourceInformation($composer3, "*2186@102068L127");
                    Modifier modifier$iv3 = LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdSearchContent);
                    ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, true);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer3, modifier$iv3);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv3 = ((((390 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        function6 = constructor3;
                        $composer3.createNode(function6);
                    } else {
                        function6 = constructor3;
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer3);
                    $composer2 = $composer3;
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                        $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                        $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash4);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i7 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i8 = ((390 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1819747385, "C2187@102168L9:SearchBar.kt#uh7d8r");
                    function4.invoke($composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Unit unit = Unit.INSTANCE;
                    $composer2.endReplaceGroup();
                    Unit unit2 = Unit.INSTANCE;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i9 = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2058590599, "C2181@101796L85,2182@101894L119:SearchBar.kt#uh7d8r");
            Modifier modifier$iv4 = LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdSurface);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, true);
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer3, modifier$iv4);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function1 = constructor;
                $composer3.createNode(function1);
            } else {
                function1 = constructor;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash5);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i11 = ((390 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 329239585, "C2181@101870L9:SearchBar.kt#uh7d8r");
            function3.invoke($composer3, Integer.valueOf(($dirty >> 21) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier$iv5 = LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdInputField);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, true);
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer3, modifier$iv5);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv5 = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function5 = constructor2;
                $composer3.createNode(function5);
            } else {
                function5 = constructor2;
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash6);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
            int i12 = ($changed$iv$iv$iv5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            int i13 = ((390 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 397275701, "C2183@101987L12:SearchBar.kt#uh7d8r");
            function2.invoke($composer3, Integer.valueOf(($dirty >> 18) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (function4 == null) {
                $composer3.startReplaceGroup(2058820276);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
            } else {
                $composer3.startReplaceGroup(2058820277);
                ComposerKt.sourceInformation($composer3, "*2186@102068L127");
                Modifier modifier$iv6 = LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdSearchContent);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv6 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv6 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, true);
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv6 = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer3, modifier$iv6);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv6 = ((((390 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function6 = constructor3;
                    $composer3.createNode(function6);
                } else {
                    function6 = constructor3;
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer3);
                $composer2 = $composer3;
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash7);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = ($changed$iv$iv$iv6 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                int i15 = ((390 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1819747385, "C2187@102168L9:SearchBar.kt#uh7d8r");
                function4.invoke($composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Unit unit3 = Unit.INSTANCE;
                $composer2.endReplaceGroup();
                Unit unit4 = Unit.INSTANCE;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.SearchBarLayout$lambda$49(animatable, finalBackProgress, mutableState, mutableState2, modifier, windowInsets, function2, function3, function4, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit SearchBarLayout$lambda$42$lambda$41(MutableWindowInsets $unconsumedInsets, WindowInsets $windowInsets, WindowInsets consumedInsets) {
        $unconsumedInsets.setInsets(WindowInsetsKt.exclude($windowInsets, consumedInsets));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: DockedSearchBarLayout-nbWgWpA, reason: not valid java name */
    public static final void m2783DockedSearchBarLayoutnbWgWpA(final SearchBarState state, final Function2<? super Composer, ? super Integer, Unit> function2, final Modifier modifier, final Shape shape, final SearchBarColors colors, final float tonalElevation, final float shadowElevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Shape shape2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1402423467);
        ComposerKt.sourceInformation($composer3, "C(DockedSearchBarLayout)N(state,inputField,modifier,shape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,content)2324@107586L24,2325@107655L47,2325@107615L87,2330@107802L38,2334@107974L2233,2327@107708L2499:SearchBar.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            shape2 = shape;
            $dirty |= $composer3.changed(shape2) ? 2048 : 1024;
        } else {
            shape2 = shape;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(colors) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(tonalElevation) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changed(shadowElevation) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute((4793491 & $dirty) != 4793490, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1402423467, $dirty, -1, "androidx.compose.material3.DockedSearchBarLayout (SearchBar.kt:2323)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, 683737348, "CC(remember):Effects.kt#9igjgp");
            Object value$iv$iv = $composer3.rememberedValue();
            int $dirty2 = $dirty;
            if (value$iv$iv == Composer.INSTANCE.getEmpty()) {
                value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(value$iv$iv);
            }
            final CoroutineScope scope = (CoroutineScope) value$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean zIsExpanded = isExpanded(state);
            ComposerKt.sourceInformationMarkerStart($composer3, -890765926, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = $composer3.changedInstance(scope) | (($dirty2 & 14) == 4);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchBarKt.DockedSearchBarLayout_nbWgWpA$lambda$51$lambda$50(scope, state);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BackHandler_androidKt.BackHandler(zIsExpanded, (Function0) it$iv, $composer3, 0, 0);
            $composer2 = $composer3;
            SurfaceKt.m2929SurfaceT9BRK9s(WindowInsetsPadding_androidKt.imePadding(modifier), shape2, colors.m2768getContainerColor0d7_KjU(), ColorSchemeKt.m2263contentColorForek8zF_U(colors.m2768getContainerColor0d7_KjU(), $composer3, 0), tonalElevation, shadowElevation, null, ComposableLambdaKt.rememberComposableLambda(-956905210, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBarLayout$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer4, "C2335@108012L26,2343@108320L195,2350@108545L1656,2339@108213L1988:SearchBar.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-956905210, $changed2, -1, "androidx.compose.material3.DockedSearchBarLayout.<anonymous> (SearchBar.kt:2335)");
                    }
                    float windowContainerHeight = SearchBar_androidKt.getWindowContainerHeight($composer4, 0);
                    float other$iv = Dp.m7905constructorimpl(windowContainerHeight * 0.6666667f);
                    float minHeight = ((Dp) RangesKt.coerceAtMost(Dp.m7903boximpl(SearchBarKt.getDockedExpandedTableMinHeight()), Dp.m7903boximpl(other$iv))).m7919unboximpl();
                    final SearchBarColors searchBarColors = colors;
                    final Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    List contents$iv = CollectionsKt.listOf((Object[]) new Function2[]{function2, ComposableLambdaKt.rememberComposableLambda(-1776541672, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarKt$DockedSearchBarLayout$2.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Code duplicated, block: B:27:0x0171  */
                        /* JADX WARN: Code duplicated, block: B:30:? A[RETURN, SYNTHETIC] */
                        public final void invoke(Composer $composer5, int $changed3) {
                            Function0<ComposeUiNode> function1;
                            int compositeKeyHash$iv$iv;
                            ComposerKt.sourceInformation($composer5, "C2344@108346L147:SearchBar.kt#uh7d8r");
                            if (!$composer5.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer5.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1776541672, $changed3, -1, "androidx.compose.material3.DockedSearchBarLayout.<anonymous>.<anonymous> (SearchBar.kt:2344)");
                            }
                            SearchBarColors searchBarColors2 = searchBarColors;
                            Function3<ColumnScope, Composer, Integer, Unit> function5 = function4;
                            ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            int $changed$iv$iv = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer5, modifier$iv);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                function1 = constructor;
                                $composer5.createNode(function1);
                            } else {
                                function1 = constructor;
                                $composer5.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer5);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                                compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
                                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                }
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                int i = ($changed$iv$iv$iv >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                                ColumnScope $this$invoke_u24lambda_u240 = ColumnScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer5, 1313930401, "C2345@108383L46,2346@108458L9:SearchBar.kt#uh7d8r");
                                DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, searchBarColors2.m2769getDividerColor0d7_KjU(), $composer5, 0, 3);
                                function5.invoke($this$invoke_u24lambda_u240, $composer5, Integer.valueOf((((0 >> 6) & 112) | 6) & 14));
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                            compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i2 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                            ColumnScope $this$invoke_u24lambda_u241 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer5, 1313930401, "C2345@108383L46,2346@108458L9:SearchBar.kt#uh7d8r");
                            DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, searchBarColors2.m2769getDividerColor0d7_KjU(), $composer5, 0, 3);
                            function5.invoke($this$invoke_u24lambda_u241, $composer5, Integer.valueOf((((0 >> 6) & 112) | 6) & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54)});
                    ComposerKt.sourceInformationMarkerStart($composer4, -462885538, "CC(remember):SearchBar.kt#9igjgp");
                    boolean invalid$iv2 = $composer4.changed(state) | $composer4.changed(other$iv) | $composer4.changed(minHeight);
                    SearchBarState searchBarState = state;
                    Object it$iv2 = $composer4.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = (MultiContentMeasurePolicy) new SearchBarKt$DockedSearchBarLayout$2$2$1(searchBarState, other$iv, minHeight);
                        $composer4.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    MultiContentMeasurePolicy measurePolicy$iv = (MultiContentMeasurePolicy) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerStart($composer4, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
                    Modifier modifier$iv = Modifier.INSTANCE;
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(contents$iv);
                    ComposerKt.sourceInformationMarkerStart($composer4, -290764973, "CC(remember):Layout.kt#9igjgp");
                    boolean invalid$iv$iv = (((0 & 896) ^ 384) > 256 && $composer4.changed(measurePolicy$iv)) || (0 & 384) == 256;
                    Object it$iv$iv = $composer4.rememberedValue();
                    if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv$iv2 = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy$iv);
                        $composer4.updateRememberedValue(value$iv$iv2);
                        it$iv$iv = value$iv$iv2;
                    }
                    MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) it$iv$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    int $changed$iv$iv = 0 & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        function0 = constructor;
                        $composer4.createNode(function0);
                    } else {
                        function0 = constructor;
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts.invoke($composer4, Integer.valueOf(($changed$iv$iv$iv >> 6) & 14));
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, (($dirty2 >> 6) & 112) | 12582912 | (($dirty2 >> 3) & 57344) | (($dirty2 >> 3) & 458752), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.DockedSearchBarLayout_nbWgWpA$lambda$52(state, function2, modifier, shape, colors, tonalElevation, shadowElevation, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit DockedSearchBarLayout_nbWgWpA$lambda$51$lambda$50(CoroutineScope $scope, SearchBarState $state) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new SearchBarKt$DockedSearchBarLayout$1$1$1($state, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:195:0x071b  */
    /* JADX INFO: renamed from: FullScreenSearchBarLayout-EQC0FA8, reason: not valid java name */
    public static final void m2786FullScreenSearchBarLayoutEQC0FA8(final SearchBarState state, final PredictiveBackState predictiveBackState, final Function2<? super Composer, ? super Integer, Unit> function2, final Modifier modifier, final Shape collapsedShape, final SearchBarColors colors, final float tonalElevation, final float shadowElevation, final WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Shape shape;
        float f;
        Object value$iv;
        final MutableState lastInProgressValue;
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function4;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-740838201);
        ComposerKt.sourceInformation($composer3, "C(FullScreenSearchBarLayout)N(state,predictiveBackState,inputField,modifier,collapsedShape,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,windowInsets,content)2399@110650L57,2401@110747L64,2410@111159L64,2419@111546L7,2420@111598L15,2422@111646L1042,2447@112954L34,2453@113205L120,2484@114348L4812,2450@113110L6050:SearchBar.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer3.changed(predictiveBackState) : $composer3.changedInstance(predictiveBackState) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(modifier) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            shape = collapsedShape;
            $dirty |= $composer3.changed(shape) ? 16384 : 8192;
        } else {
            shape = collapsedShape;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(colors) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            f = tonalElevation;
            $dirty |= $composer3.changed(f) ? 1048576 : 524288;
        } else {
            f = tonalElevation;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changed(shadowElevation) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(windowInsets) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        if ($composer3.shouldExecute(($dirty & 306783379) != 306783378, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-740838201, $dirty, -1, "androidx.compose.material3.FullScreenSearchBarLayout (SearchBar.kt:2398)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1019074080, "CC(remember):SearchBar.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return predictiveBackState.getValue();
                    }
                });
                $composer3.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            }
            State backEvent$delegate = (State) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1019077191, "CC(remember):SearchBar.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            int $changed$iv = $dirty;
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv3);
                it$iv2 = value$iv3;
            }
            MutableState $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2457 = (MutableState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BackEventProgress event = FullScreenSearchBarLayout_EQC0FA8$lambda$55(backEvent$delegate);
            if (event instanceof BackEventProgress.InProgress) {
                if ($this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2457.getValue() == null) {
                    $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2457.setValue(event);
                }
            } else if (Intrinsics.areEqual(event, BackEventProgress.NotRunning.INSTANCE)) {
                $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2457.setValue(null);
            } else if (!Intrinsics.areEqual(event, BackEventProgress.Completed.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1019090375, "CC(remember):SearchBar.kt#9igjgp");
            Object value$iv4 = $composer3.rememberedValue();
            if (value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv4);
            }
            MutableState $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2459 = (MutableState) value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BackEventProgress event2 = FullScreenSearchBarLayout_EQC0FA8$lambda$55(backEvent$delegate);
            if (event2 instanceof BackEventProgress.InProgress) {
                $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2459.setValue(event2);
            } else if (Intrinsics.areEqual(event2, BackEventProgress.NotRunning.INSTANCE)) {
                $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2459.setValue(null);
            } else if (!Intrinsics.areEqual(event2, BackEventProgress.Completed.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) objConsume;
            final Shape fullScreenShape = SearchBarDefaults.INSTANCE.getFullScreenShape($composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, 1019106937, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(density) | $composer3.changed(fullScreenShape);
            Object it$iv3 = $composer3.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                final Shape shape2 = shape;
                lastInProgressValue = $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2459;
                value$iv = new GenericShape(new Function3() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return SearchBarKt.FullScreenSearchBarLayout_EQC0FA8$lambda$62$lambda$61(shape2, fullScreenShape, density, state, lastInProgressValue, (Path) obj, (Size) obj2, (LayoutDirection) obj3);
                    }
                });
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv3;
                lastInProgressValue = $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2459;
            }
            GenericShape animatedShape = (GenericShape) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1019147785, "CC(remember):SearchBar.kt#9igjgp");
            Object value$iv5 = $composer3.rememberedValue();
            if (value$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = new MutableWindowInsets(null, 1, null);
                $composer3.updateRememberedValue(value$iv5);
            }
            final MutableWindowInsets unconsumedInsets = (MutableWindowInsets) value$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            WindowInsets nonTopInsets = WindowInsetsKt.m892onlybOOhFvg(unconsumedInsets.getInsets(), WindowInsetsSides.m904plusgK_yJZ4(WindowInsetsSides.INSTANCE.m914getHorizontalJoeWqyM(), WindowInsetsSides.INSTANCE.m912getBottomJoeWqyM()));
            ComposerKt.sourceInformationMarkerStart($composer3, 1019155903, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv2 = ($changed$iv & 234881024) == 67108864;
            boolean invalid$iv3 = invalid$iv2;
            Object value$iv6 = $composer3.rememberedValue();
            if (invalid$iv3 || value$iv6 == Composer.INSTANCE.getEmpty()) {
                value$iv6 = new Function1() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchBarKt.FullScreenSearchBarLayout_EQC0FA8$lambda$65$lambda$64(unconsumedInsets, windowInsets, (WindowInsets) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv6);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierConsumeWindowInsets = WindowInsetsPaddingKt.consumeWindowInsets(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier, (Function1) value$iv6), windowInsets);
            ComposerKt.sourceInformationMarkerStart($composer3, 1019197171, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv4 = $composer3.changed(lastInProgressValue) | (($changed$iv & 14) == 4) | $composer3.changed($this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2457);
            SearchBarKt$FullScreenSearchBarLayout$2$1 value$iv7 = $composer3.rememberedValue();
            if (invalid$iv4 || value$iv7 == Composer.INSTANCE.getEmpty()) {
                value$iv7 = new SearchBarKt$FullScreenSearchBarLayout$2$1(lastInProgressValue, state, unconsumedInsets, $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2457);
                $composer3.updateRememberedValue(value$iv7);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv7;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifierConsumeWindowInsets);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = ((0 << 6) & 896) | 6;
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
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1312429125, "C2461@113581L17,2458@113410L297,2471@113910L38,2467@113721L372,2478@114193L17,2477@114107L223:SearchBar.kt#uh7d8r");
            Modifier modifier$iv = PaddingKt.padding(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdInputField), WindowInsetsKt.asPaddingValues(WindowInsetsKt.m892onlybOOhFvg(nonTopInsets, WindowInsetsSides.INSTANCE.m914getHorizontalJoeWqyM()), $composer3, 0));
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
            int $changed$iv$iv2 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
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
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
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
            int i3 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 714795213, "C2464@113681L12:SearchBar.kt#uh7d8r");
            function2.invoke($composer3, Integer.valueOf(($changed$iv >> 6) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SurfaceKt.m2929SurfaceT9BRK9s(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdSurface), animatedShape, colors.m2768getContainerColor0d7_KjU(), ColorSchemeKt.m2263contentColorForek8zF_U(colors.m2768getContainerColor0d7_KjU(), $composer3, 0), f, shadowElevation, null, ComposableSingletons$SearchBarKt.INSTANCE.m2305getLambda$1146652811$material3(), $composer3, (($changed$iv >> 6) & 57344) | 12582918 | (($changed$iv >> 6) & 458752), 64);
            Modifier modifier$iv2 = PaddingKt.padding(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutIdSearchContent), WindowInsetsKt.asPaddingValues(nonTopInsets, $composer3, 0));
            ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
            MeasurePolicy measurePolicy$iv2 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            int $changed$iv$iv3 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function4 = constructor3;
                $composer3.createNode(function4);
            } else {
                function4 = constructor3;
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                $composer2 = $composer3;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = ($changed$iv$iv$iv2 >> 6) & 14;
                Composer $composer$iv = $composer2;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                ColumnScope $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2469_u24lambda_u2468 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer$iv, -1748770687, "C2480@114244L46,2481@114307L9:SearchBar.kt#uh7d8r");
                DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, colors.m2769getDividerColor0d7_KjU(), $composer$iv, 0, 3);
                function3.invoke($this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2469_u24lambda_u2468, $composer$iv, Integer.valueOf(((((0 >> 6) & 112) | 6) & 14) | (($changed$iv >> 24) & 112)));
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
            } else {
                $composer2 = $composer3;
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
            Composer $composer$iv2 = $composer2;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -384672921, "C89@4556L9:Column.kt#2w3rfo");
            ColumnScope $this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2469_u24lambda_u2469 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer$iv2, -1748770687, "C2480@114244L46,2481@114307L9:SearchBar.kt#uh7d8r");
            DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, colors.m2769getDividerColor0d7_KjU(), $composer$iv2, 0, 3);
            function3.invoke($this$FullScreenSearchBarLayout_EQC0FA8_u24lambda_u2469_u24lambda_u2469, $composer$iv2, Integer.valueOf(((((0 >> 6) & 112) | 6) & 14) | (($changed$iv >> 24) & 112)));
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
            $composer3.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.FullScreenSearchBarLayout_EQC0FA8$lambda$70(state, predictiveBackState, function2, modifier, collapsedShape, colors, tonalElevation, shadowElevation, windowInsets, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final BackEventProgress FullScreenSearchBarLayout_EQC0FA8$lambda$55(State<? extends BackEventProgress> state) {
        Object thisObj$iv = state.getValue();
        return (BackEventProgress) thisObj$iv;
    }

    static final Unit FullScreenSearchBarLayout_EQC0FA8$lambda$62$lambda$61(Shape $collapsedShape, Shape $fullScreenShape, Density $density, SearchBarState $state, MutableState $lastInProgressValue, Path $this$GenericShape, Size size, LayoutDirection layoutDirection) {
        Shape shape;
        if ($collapsedShape != RoundedCornerShapeKt.getCircleShape()) {
            shape = $fullScreenShape;
        } else {
            shape = $fullScreenShape;
            if (shape == RectangleShapeKt.getRectangleShape()) {
                float fraction = Math.max(1.0f - $state.getProgress(), transform((BackEventProgress.InProgress) $lastInProgressValue.getValue()));
                float arg0$iv = SearchBarCornerRadius;
                float radius = $density.mo406toPx0680j_4(Dp.m7905constructorimpl(arg0$iv * fraction));
                if (radius < 0.001d) {
                    Path.addRect$default($this$GenericShape, androidx.compose.ui.geometry.SizeKt.m4942toRectuvyYCjk(size.m4926unboximpl()), null, 2, null);
                } else {
                    Rect rectM4942toRectuvyYCjk = androidx.compose.ui.geometry.SizeKt.m4942toRectuvyYCjk(size.m4926unboximpl());
                    long v1$iv$iv = Float.floatToRawIntBits(radius);
                    long v2$iv$iv = Float.floatToRawIntBits(radius);
                    Path.addRoundRect$default($this$GenericShape, RoundRectKt.m4907RoundRectsniSvfs(rectM4942toRectuvyYCjk, CornerRadius.m4806constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L))), null, 2, null);
                }
            }
            return Unit.INSTANCE;
        }
        Shape shape2 = $state.getProgress() < 0.5f ? $collapsedShape : shape;
        OutlineKt.addOutline($this$GenericShape, shape2.mo321createOutlinePq9zytI(size.m4926unboximpl(), layoutDirection, $density));
        return Unit.INSTANCE;
    }

    static final Unit FullScreenSearchBarLayout_EQC0FA8$lambda$65$lambda$64(MutableWindowInsets $unconsumedInsets, WindowInsets $windowInsets, WindowInsets consumedInsets) {
        $unconsumedInsets.setInsets(WindowInsetsKt.exclude($windowInsets, consumedInsets));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float transform(BackEventProgress.InProgress $this$transform) {
        if ($this$transform == null) {
            return 0.0f;
        }
        return PredictiveBack.INSTANCE.transform$material3($this$transform.getProgress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRect getCollapsedBounds(SearchBarState $this$collapsedBounds) {
        IntRect intRectM8066IntRectVbeCjmY;
        LayoutCoordinates it = $this$collapsedBounds.getCollapsedCoords();
        return (it == null || (intRectM8066IntRectVbeCjmY = IntRectKt.m8066IntRectVbeCjmY(IntOffsetKt.m8050roundk4lQ0M(LayoutCoordinatesKt.positionInWindow(it)), it.mo6549getSizeYbymL2g())) == null) ? IntRect.INSTANCE.getZero() : intRectM8066IntRectVbeCjmY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetectClickFromInteractionSource(final InteractionSource interactionSource, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-652650823);
        ComposerKt.sourceInformation($composer2, "C(DetectClickFromInteractionSource)N(interactionSource,onClick)2593@119695L148,2593@119661L182:SearchBar.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-652650823, $dirty, -1, "androidx.compose.material3.DetectClickFromInteractionSource (SearchBar.kt:2592)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -132210195, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = (($dirty & 14) == 4) | (($dirty & 112) == 32);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function2) new SearchBarKt$DetectClickFromInteractionSource$1$1(interactionSource, function0, null);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv, $composer2, $dirty & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarKt.DetectClickFromInteractionSource$lambda$73(interactionSource, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackMultiplier(BackEventCompat currentBackEvent, float progress, float finalBackProgress) {
        if (currentBackEvent == null) {
            return 0.0f;
        }
        if (Float.isNaN(finalBackProgress)) {
            return 1.0f;
        }
        if (finalBackProgress <= 0.0f) {
            return 0.0f;
        }
        return progress / finalBackProgress;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculatePredictiveBackOffsetX-rOvwMX4, reason: not valid java name */
    public static final int m2796calculatePredictiveBackOffsetXrOvwMX4(long constraints, int minMargin, BackEventCompat currentBackEvent, LayoutDirection layoutDirection, float progress, float predictiveBackMultiplier) {
        if (currentBackEvent != null) {
            if (!(predictiveBackMultiplier == 0.0f)) {
                int directionMultiplier = currentBackEvent.getSwipeEdge() == 0 ? 1 : -1;
                int rtlMultiplier = layoutDirection != LayoutDirection.Ltr ? -1 : 1;
                float maxOffsetX = (Constraints.m7858getMaxWidthimpl(constraints) * SearchBarPredictiveBackMaxOffsetXRatio) - minMargin;
                float interpolatedOffsetX = (1.0f - progress) * maxOffsetX;
                return MathKt.roundToInt(interpolatedOffsetX * predictiveBackMultiplier * directionMultiplier * rtlMultiplier);
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculatePredictiveBackOffsetY-dzo92Q0, reason: not valid java name */
    public static final int m2797calculatePredictiveBackOffsetYdzo92Q0(long constraints, int minMargin, BackEventCompat currentBackEvent, BackEventCompat firstBackEvent, int height, int maxOffsetY, float predictiveBackMultiplier) {
        if (firstBackEvent != null && currentBackEvent != null) {
            if (!(predictiveBackMultiplier == 0.0f)) {
                int availableVerticalSpace = Math.max(0, ((Constraints.m7857getMaxHeightimpl(constraints) - height) / 2) - minMargin);
                int adjustedMaxOffsetY = Math.min(availableVerticalSpace, maxOffsetY);
                float yDelta = currentBackEvent.getTouchY() - firstBackEvent.getTouchY();
                float yProgress = Math.abs(yDelta) / Constraints.m7857getMaxHeightimpl(constraints);
                float directionMultiplier = Math.signum(yDelta);
                int interpolatedOffsetY = MathHelpersKt.lerp(0, adjustedMaxOffsetY, yProgress);
                return MathKt.roundToInt(interpolatedOffsetY * predictiveBackMultiplier * directionMultiplier);
            }
        }
        return 0;
    }

    static {
        float arg0$iv = SearchBarDefaults.INSTANCE.m2774getInputFieldHeightD9Ej5fM();
        SearchBarCornerRadius = Dp.m7905constructorimpl(arg0$iv / 2);
        DockedExpandedTableMinHeight = Dp.m7905constructorimpl(PsExtractor.VIDEO_STREAM_MASK);
        SearchBarMinWidth = Dp.m7905constructorimpl(360);
        SearchBarMaxWidth = Dp.m7905constructorimpl(720);
        SearchBarVerticalPadding = Dp.m7905constructorimpl(8);
        SearchBarIconOffsetX = Dp.m7905constructorimpl(4);
        SearchBarPredictiveBackMinMargin = Dp.m7905constructorimpl(8);
        SearchBarPredictiveBackMaxOffsetY = Dp.m7905constructorimpl(24);
        AnimationEnterEasing = MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier();
        AnimationExitEasing = new CubicBezierEasing(0.0f, 1.0f, 0.0f, 1.0f);
        AnimationEnterFloatSpec = AnimationSpecKt.tween(600, 100, AnimationEnterEasing);
        AnimationExitFloatSpec = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, AnimationExitEasing);
        AnimationPredictiveBackExitFloatSpec = AnimationSpecKt.tween$default(AnimationExitDurationMillis, 0, AnimationExitEasing, 2, null);
        AnimationEnterSizeSpec = AnimationSpecKt.tween(600, 100, AnimationEnterEasing);
        AnimationExitSizeSpec = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, AnimationExitEasing);
        DockedEnterTransition = EnterExitTransitionKt.fadeIn$default(AnimationEnterFloatSpec, 0.0f, 2, null).plus(EnterExitTransitionKt.expandVertically$default(AnimationEnterSizeSpec, null, false, null, 14, null));
        DockedExitTransition = EnterExitTransitionKt.fadeOut$default(AnimationExitFloatSpec, 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkVertically$default(AnimationExitSizeSpec, null, false, null, 14, null));
    }

    public static final float getSearchBarAsTopBarPadding() {
        return SearchBarAsTopBarPadding;
    }

    public static final float getDockedExpandedTableMinHeight() {
        return DockedExpandedTableMinHeight;
    }

    public static final float getSearchBarMinWidth() {
        return SearchBarMinWidth;
    }

    public static final float getSearchBarVerticalPadding() {
        return SearchBarVerticalPadding;
    }
}
