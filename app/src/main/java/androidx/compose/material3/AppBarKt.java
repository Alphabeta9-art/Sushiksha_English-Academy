package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.AppBarLargeFlexibleTokens;
import androidx.compose.material3.tokens.AppBarLargeTokens;
import androidx.compose.material3.tokens.AppBarMediumFlexibleTokens;
import androidx.compose.material3.tokens.AppBarMediumTokens;
import androidx.compose.material3.tokens.AppBarSmallTokens;
import androidx.compose.material3.tokens.AppBarTokens;
import androidx.compose.material3.tokens.BottomAppBarTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.Velocity;
import androidx.compose.ui.unit.VelocityKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000ä\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u007f\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001a\u008b\u0001\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u007f\u0010\u0017\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001a\u008b\u0001\u0010\u0017\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0004\b\u0018\u0010\u0016\u001a¨\u0001\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u007f\u0010\u001e\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001a\u0095\u0001\u0010\u001e\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0004\b \u0010!\u001a¶\u0001\u0010\"\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001f\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0001¢\u0006\u0004\b#\u0010$\u001a\u007f\u0010%\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001a\u0095\u0001\u0010%\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0004\b&\u0010!\u001a¶\u0001\u0010'\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001f\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0001¢\u0006\u0004\b(\u0010$\u001aà\u0001\u0010)\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0013\u0012\u00110*¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062*\b\u0002\u0010\u0019\u001a$\u0012\u0013\u0012\u00110*¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t¢\u0006\u0002\b\u00042\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001f\u001a\u00020\u00142\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0001¢\u0006\u0004\b.\u0010/\u001a\u0080\u0001\u00100\u001a\u00020\u00012\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u0002032\b\b\u0002\u00105\u001a\u00020\u00142\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0004\b8\u00109\u001a\u008c\u0001\u00100\u001a\u00020\u00012\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u0002032\b\b\u0002\u00105\u001a\u00020\u00142\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010:H\u0007¢\u0006\u0004\b;\u0010<\u001ai\u00100\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u0002032\b\b\u0002\u00105\u001a\u00020\u00142\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0004\b>\u0010?\u001au\u00100\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u0002032\b\b\u0002\u00105\u001a\u00020\u00142\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010:2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0004\b@\u0010A\u001a\u007f\u0010B\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u0002032\b\b\u0002\u00104\u001a\u0002032\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010:2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000bH\u0001¢\u0006\u0004\bE\u0010F\u001ay\u0010G\u001a\u00020\u00012\u0006\u0010H\u001a\u00020\u00142\u0006\u0010C\u001a\u00020D2\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u0002072\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010:2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000bH\u0003¢\u0006\u0004\bI\u0010J\u001a+\u0010K\u001a\u00020L2\b\b\u0002\u0010M\u001a\u00020N2\b\b\u0002\u0010O\u001a\u00020N2\b\b\u0002\u0010P\u001a\u00020NH\u0007¢\u0006\u0002\u0010Q\u001a+\u0010R\u001a\u00020S2\b\b\u0002\u0010M\u001a\u00020N2\b\b\u0002\u0010O\u001a\u00020N2\b\b\u0002\u0010P\u001a\u00020NH\u0007¢\u0006\u0002\u0010T\u001a \u0010U\u001a\u00020S2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020N2\u0006\u0010P\u001a\u00020NH\u0007\u001a>\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020S2\u0006\u0010Y\u001a\u00020N2\u000e\u0010Z\u001a\n\u0012\u0004\u0012\u00020N\u0018\u00010[2\u000e\u0010\\\u001a\n\u0012\u0004\u0012\u00020N\u0018\u00010]H\u0082@¢\u0006\u0002\u0010^\u001a¬\u0001\u0010f\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u0006\u0010g\u001a\u00020h2\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0006\u0010i\u001a\u00020h2\u0006\u0010\u001a\u001a\u00020\u001b2\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0003¢\u0006\u0004\bj\u0010k\u001aô\u0001\u0010)\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u0006\u0010g\u001a\u00020h2\u0006\u0010s\u001a\u00020\u00142\u0011\u0010t\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u0006\u0010u\u001a\u00020h2\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0006\u0010i\u001a\u00020h2\u0013\u0010v\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0006\u0010w\u001a\u00020h2\u0006\u0010\u001a\u001a\u00020\u001b2\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0004¢\u0006\u0002\b\u000b2\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0003¢\u0006\u0004\bx\u0010y\u001a\u0016\u0010~\u001a\u00020\u0006*\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002\u001aá\u0001\u0010\u007f\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0082\u0001\u001a\u0002032\u0007\u0010\u0083\u0001\u001a\u0002032\u0007\u0010\u0084\u0001\u001a\u0002032\u0007\u0010\u0085\u0001\u001a\u0002032\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u0006\u0010g\u001a\u00020h2\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0006\u0010i\u001a\u00020h2\r\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020N0\u00032\b\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0007\u0010s\u001a\u00030\u0089\u00012\u0007\u0010\u008a\u0001\u001a\u00020*2\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u0007\u0010\u008b\u0001\u001a\u00020\u0014H\u0003¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001\u001a@\u0010\u008e\u0001\u001a\u00020W2\u0006\u0010X\u001a\u00020L2\u0006\u0010Y\u001a\u00020N2\u000e\u0010Z\u001a\n\u0012\u0004\u0012\u00020N\u0018\u00010[2\u000e\u0010\\\u001a\n\u0012\u0004\u0012\u00020N\u0018\u00010]H\u0082@¢\u0006\u0003\u0010\u008f\u0001\u001a\u0016\u0010\u0090\u0001\u001a\t\u0012\u0004\u0012\u00020*0\u0091\u0001H\u0003¢\u0006\u0003\u0010\u0092\u0001\"\u0010\u0010_\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010`\"\u0016\u0010a\u001a\u00020\u0014X\u0080\u0004¢\u0006\n\n\u0002\u0010`\u001a\u0004\bb\u0010c\"\u0010\u0010d\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010`\"\u0010\u0010e\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010`\" \u0010l\u001a\b\u0012\u0004\u0012\u00020n0mX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bo\u0010p\u001a\u0004\bq\u0010r\" \u0010z\u001a\b\u0012\u0004\u0012\u00020{0mX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b|\u0010p\u001a\u0004\b}\u0010r\"\u0018\u0010\u0093\u0001\u001a\u00030\u0094\u0001X\u0080\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001\"\u0011\u0010\u0097\u0001\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010`\"\u0011\u0010\u0098\u0001\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010`\"\u0011\u0010\u0099\u0001\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010`\"\u0011\u0010\u009a\u0001\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010`¨\u0006\u009b\u0001²\u0006\u000b\u0010\u009c\u0001\u001a\u00020*X\u008a\u0084\u0002"}, d2 = {"TopAppBar", "", "title", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "navigationIcon", "actions", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Lkotlin/ExtensionFunctionType;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "colors", "Landroidx/compose/material3/TopAppBarColors;", "scrollBehavior", "Landroidx/compose/material3/TopAppBarScrollBehavior;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "expandedHeight", "Landroidx/compose/ui/unit/Dp;", "TopAppBar-GHTll3U", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "CenterAlignedTopAppBar", "CenterAlignedTopAppBar-GHTll3U", "subtitle", "titleHorizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "TopAppBar-cJHQLPU", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Alignment$Horizontal;FLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "MediumTopAppBar", "collapsedHeight", "MediumTopAppBar-oKE7A98", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "MediumFlexibleTopAppBar", "MediumFlexibleTopAppBar-eXZ4JBQ", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Alignment$Horizontal;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;III)V", "LargeTopAppBar", "LargeTopAppBar-oKE7A98", "LargeFlexibleTopAppBar", "LargeFlexibleTopAppBar-eXZ4JBQ", "TwoRowsTopAppBar", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "expanded", "TwoRowsTopAppBar-eXZ4JBQ", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Alignment$Horizontal;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;III)V", "BottomAppBar", "floatingActionButton", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "BottomAppBar-Snr_uVM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/material3/BottomAppBarScrollBehavior;", "BottomAppBar-qhFBPw4", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/BottomAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", UriUtil.LOCAL_CONTENT_SCHEME, "BottomAppBar-1oL4kX8", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomAppBar-e-3WI5M", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/BottomAppBarScrollBehavior;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FlexibleBottomAppBar", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "FlexibleBottomAppBar-wBhsO_E", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/Arrangement$Horizontal;FLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/BottomAppBarScrollBehavior;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomAppBarLayout", "containerHeight", "BottomAppBarLayout-t5fmz9U", "(FLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/BottomAppBarScrollBehavior;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "rememberTopAppBarState", "Landroidx/compose/material3/TopAppBarState;", "initialHeightOffsetLimit", "", "initialHeightOffset", "initialContentOffset", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarState;", "rememberBottomAppBarState", "Landroidx/compose/material3/BottomAppBarState;", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomAppBarState;", "BottomAppBarState", "settleAppBarBottom", "Landroidx/compose/ui/unit/Velocity;", "state", "velocity", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/material3/BottomAppBarState;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "BottomAppBarHorizontalPadding", "F", "BottomAppBarVerticalPadding", "getBottomAppBarVerticalPadding", "()F", "FABHorizontalPadding", "FABVerticalPadding", "SingleRowTopAppBar", "titleTextStyle", "Landroidx/compose/ui/text/TextStyle;", "subtitleTextStyle", "SingleRowTopAppBar-wn8IZOc", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/Alignment$Horizontal;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;III)V", "LocalSingleRowTopAppBarOverride", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/SingleRowTopAppBarOverride;", "getLocalSingleRowTopAppBarOverride$annotations", "()V", "getLocalSingleRowTopAppBarOverride", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "titleBottomPadding", "smallTitle", "smallTitleTextStyle", "smallSubtitle", "smallSubtitleTextStyle", "TwoRowsTopAppBar-pJA5dT0", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;FLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/Alignment$Horizontal;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;III)V", "LocalTwoRowsTopAppBarOverride", "Landroidx/compose/material3/TwoRowsTopAppBarOverride;", "getLocalTwoRowsTopAppBarOverride$annotations", "getLocalTwoRowsTopAppBarOverride", "adjustHeightOffsetLimit", "TopAppBarLayout", "scrolledOffset", "Landroidx/compose/material3/internal/FloatProducer;", "navigationIconContentColor", "titleContentColor", "subtitleContentColor", "actionIconContentColor", "titleAlpha", "titleVerticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "", "hideTitleSemantics", "height", "TopAppBarLayout-lyUyIHI", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/internal/FloatProducer;JJJJLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;IZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;FLandroidx/compose/runtime/Composer;II)V", "settleAppBar", "(Landroidx/compose/material3/TopAppBarState;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rememberTouchExplorationService", "Landroidx/compose/runtime/State;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "TopTitleAlphaEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "getTopTitleAlphaEasing", "()Landroidx/compose/animation/core/CubicBezierEasing;", "MediumTitleBottomPadding", "LargeTitleBottomPadding", "TopAppBarHorizontalPadding", "TopAppBarTitleInset", "material3", "touchExplorationServiceEnabled"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AppBarKt {
    private static final float BottomAppBarHorizontalPadding;
    private static final float BottomAppBarVerticalPadding;
    private static final float FABHorizontalPadding;
    private static final float FABVerticalPadding;
    private static final float LargeTitleBottomPadding;
    private static final ProvidableCompositionLocal<SingleRowTopAppBarOverride> LocalSingleRowTopAppBarOverride;
    private static final ProvidableCompositionLocal<TwoRowsTopAppBarOverride> LocalTwoRowsTopAppBarOverride;
    private static final float MediumTitleBottomPadding;
    private static final float TopAppBarHorizontalPadding;
    private static final float TopAppBarTitleInset;
    private static final CubicBezierEasing TopTitleAlphaEasing;

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$settleAppBar$1, reason: invalid class name */
    /* JADX INFO: compiled from: AppBar.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.AppBarKt", f = "AppBar.kt", i = {0, 0, 0, 1}, l = {3464, 3480}, m = "settleAppBar", n = {"state", "snapAnimationSpec", "remainingVelocity", "remainingVelocity"}, s = {"L$0", "L$1", "L$2", "L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AppBarKt.settleAppBar(null, 0.0f, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$settleAppBarBottom$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AppBar.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.material3.AppBarKt", f = "AppBar.kt", i = {0, 0, 0, 1}, l = {2424, 2440}, m = "settleAppBarBottom", n = {"state", "snapAnimationSpec", "remainingVelocity", "remainingVelocity"}, s = {"L$0", "L$1", "L$2", "L$0"})
    static final class C03041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C03041(Continuation<? super C03041> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AppBarKt.settleAppBarBottom(null, 0.0f, null, null, this);
        }
    }

    static final Unit BottomAppBarLayout_t5fmz9U$lambda$24(float f, Arrangement.Horizontal horizontal, Modifier modifier, long j, long j2, float f2, PaddingValues paddingValues, WindowInsets windowInsets, BottomAppBarScrollBehavior bottomAppBarScrollBehavior, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2082BottomAppBarLayoutt5fmz9U(f, horizontal, modifier, j, j2, f2, paddingValues, windowInsets, bottomAppBarScrollBehavior, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BottomAppBar_1oL4kX8$lambda$14(Modifier modifier, long j, long j2, float f, PaddingValues paddingValues, WindowInsets windowInsets, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2078BottomAppBar1oL4kX8(modifier, j, j2, f, paddingValues, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BottomAppBar_Snr_uVM$lambda$12(Function3 function3, Modifier modifier, Function2 function2, long j, long j2, float f, PaddingValues paddingValues, WindowInsets windowInsets, int i, int i2, Composer composer, int i3) {
        m2079BottomAppBarSnr_uVM(function3, modifier, function2, j, j2, f, paddingValues, windowInsets, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BottomAppBar_e_3WI5M$lambda$15(Modifier modifier, long j, long j2, float f, PaddingValues paddingValues, WindowInsets windowInsets, BottomAppBarScrollBehavior bottomAppBarScrollBehavior, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2080BottomAppBare3WI5M(modifier, j, j2, f, paddingValues, windowInsets, bottomAppBarScrollBehavior, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit BottomAppBar_qhFBPw4$lambda$13(Function3 function3, Modifier modifier, Function2 function2, long j, long j2, float f, PaddingValues paddingValues, WindowInsets windowInsets, BottomAppBarScrollBehavior bottomAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        m2081BottomAppBarqhFBPw4(function3, modifier, function2, j, j2, f, paddingValues, windowInsets, bottomAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit CenterAlignedTopAppBar$lambda$2(Function2 function2, Modifier modifier, Function2 function3, Function3 function4, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        CenterAlignedTopAppBar(function2, modifier, function3, function4, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit CenterAlignedTopAppBar_GHTll3U$lambda$3(Function2 function2, Modifier modifier, Function2 function3, Function3 function4, float f, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        m2083CenterAlignedTopAppBarGHTll3U(function2, modifier, function3, function4, f, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit FlexibleBottomAppBar_wBhsO_E$lambda$16(Modifier modifier, long j, long j2, PaddingValues paddingValues, Arrangement.Horizontal horizontal, float f, WindowInsets windowInsets, BottomAppBarScrollBehavior bottomAppBarScrollBehavior, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2084FlexibleBottomAppBarwBhsO_E(modifier, j, j2, paddingValues, horizontal, f, windowInsets, bottomAppBarScrollBehavior, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LargeFlexibleTopAppBar_eXZ4JBQ$lambda$10(Function2 function2, Modifier modifier, Function2 function3, Function2 function4, Function3 function5, Alignment.Horizontal horizontal, float f, float f2, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, int i3, Composer composer, int i4) {
        m2085LargeFlexibleTopAppBareXZ4JBQ(function2, modifier, function3, function4, function5, horizontal, f, f2, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit LargeTopAppBar$lambda$8(Function2 function2, Modifier modifier, Function2 function3, Function3 function4, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        LargeTopAppBar(function2, modifier, function3, function4, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit LargeTopAppBar_oKE7A98$lambda$9(Function2 function2, Modifier modifier, Function2 function3, Function3 function4, float f, float f2, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        m2086LargeTopAppBaroKE7A98(function2, modifier, function3, function4, f, f2, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MediumFlexibleTopAppBar_eXZ4JBQ$lambda$7(Function2 function2, Modifier modifier, Function2 function3, Function2 function4, Function3 function5, Alignment.Horizontal horizontal, float f, float f2, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, int i3, Composer composer, int i4) {
        m2087MediumFlexibleTopAppBareXZ4JBQ(function2, modifier, function3, function4, function5, horizontal, f, f2, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit MediumTopAppBar$lambda$5(Function2 function2, Modifier modifier, Function2 function3, Function3 function4, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        MediumTopAppBar(function2, modifier, function3, function4, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit MediumTopAppBar_oKE7A98$lambda$6(Function2 function2, Modifier modifier, Function2 function3, Function3 function4, float f, float f2, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        m2088MediumTopAppBaroKE7A98(function2, modifier, function3, function4, f, f2, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SingleRowTopAppBar_wn8IZOc$lambda$32(Modifier modifier, Function2 function2, TextStyle textStyle, Function2 function3, TextStyle textStyle2, Alignment.Horizontal horizontal, Function2 function4, Function3 function5, float f, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, int i3, Composer composer, int i4) {
        m2089SingleRowTopAppBarwn8IZOc(modifier, function2, textStyle, function3, textStyle2, horizontal, function4, function5, f, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit TopAppBar$lambda$0(Function2 function2, Modifier modifier, Function2 function3, Function3 function4, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        TopAppBar(function2, modifier, function3, function4, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TopAppBarLayout_lyUyIHI$lambda$53(Modifier modifier, FloatProducer floatProducer, long j, long j2, long j3, long j4, Function2 function2, TextStyle textStyle, Function2 function3, TextStyle textStyle2, Function0 function0, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, int i, boolean z, Function2 function4, Function2 function5, float f, int i2, int i3, Composer composer, int i4) {
        m2092TopAppBarLayoutlyUyIHI(modifier, floatProducer, j, j2, j3, j4, function2, textStyle, function3, textStyle2, function0, vertical, horizontal, i, z, function4, function5, f, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    static final Unit TopAppBar_GHTll3U$lambda$1(Function2 function2, Modifier modifier, Function2 function3, Function3 function4, float f, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        m2090TopAppBarGHTll3U(function2, modifier, function3, function4, f, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TopAppBar_cJHQLPU$lambda$4(Function2 function2, Function2 function3, Modifier modifier, Function2 function4, Function3 function5, Alignment.Horizontal horizontal, float f, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, Composer composer, int i3) {
        m2091TopAppBarcJHQLPU(function2, function3, modifier, function4, function5, horizontal, f, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TwoRowsTopAppBar_eXZ4JBQ$lambda$11(Function3 function3, Modifier modifier, Function3 function4, Function2 function2, Function3 function5, Alignment.Horizontal horizontal, float f, float f2, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, int i3, Composer composer, int i4) {
        m2093TwoRowsTopAppBareXZ4JBQ(function3, modifier, function4, function2, function5, horizontal, f, f2, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit TwoRowsTopAppBar_pJA5dT0$lambda$35(Modifier modifier, Function2 function2, TextStyle textStyle, float f, Function2 function3, TextStyle textStyle2, Function2 function4, TextStyle textStyle3, Function2 function5, TextStyle textStyle4, Alignment.Horizontal horizontal, Function2 function6, Function3 function7, float f2, float f3, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i, int i2, int i3, Composer composer, int i4) {
        m2094TwoRowsTopAppBarpJA5dT0(modifier, function2, textStyle, f, function3, textStyle2, function4, textStyle3, function5, textStyle4, horizontal, function6, function7, f2, f3, windowInsets, topAppBarColors, topAppBarScrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void getLocalSingleRowTopAppBarOverride$annotations() {
    }

    public static /* synthetic */ void getLocalTwoRowsTopAppBarOverride$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of TopAppBar with expandedHeight parameter")
    public static final /* synthetic */ void TopAppBar(final Function2 title, Modifier modifier, Function2 navigationIcon, Function3 actions, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2 function2;
        Modifier modifier2;
        Function2 navigationIcon2;
        Function3 actions2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        Composer $composer2;
        final Modifier modifier3;
        final Function3 actions3;
        final Function2 navigationIcon3;
        final WindowInsets windowInsets3;
        final TopAppBarColors colors3;
        final TopAppBarScrollBehavior scrollBehavior2;
        TopAppBarColors colors4;
        TopAppBarScrollBehavior scrollBehavior3;
        Function3 actions4;
        WindowInsets windowInsets4;
        Modifier modifier4;
        Function2 navigationIcon4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(1788762628);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)N(title,modifier,navigationIcon,actions,windowInsets,colors,scrollBehavior)168@8176L307:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function2 = title;
        } else if (($changed & 6) == 0) {
            function2 = title;
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = title;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            navigationIcon2 = navigationIcon;
        } else if (($changed & 384) == 0) {
            navigationIcon2 = navigationIcon;
            $dirty |= $composer3.changedInstance(navigationIcon2) ? 256 : 128;
        } else {
            navigationIcon2 = navigationIcon;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            actions2 = actions;
        } else if (($changed & 3072) == 0) {
            actions2 = actions;
            $dirty |= $composer3.changedInstance(actions2) ? 2048 : 1024;
        } else {
            actions2 = actions;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i6 = $composer3.changed(windowInsets2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i6;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 131072 : 65536;
                $dirty |= i7;
            } else {
                colors2 = colors;
            }
            $dirty |= i7;
        } else {
            colors2 = colors;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if ((1572864 & $changed) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ($composer3.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "164@8034L12,165@8096L17");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if (i4 != 0) {
                    navigationIcon2 = ComposableSingletons$AppBarKt.INSTANCE.m2296getLambda$944449303$material3();
                }
                if (i5 != 0) {
                    actions2 = ComposableSingletons$AppBarKt.INSTANCE.getLambda$917296843$material3();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    scrollBehavior3 = null;
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                    navigationIcon4 = navigationIcon2;
                    actions4 = actions2;
                    modifier4 = modifier5;
                    i2 = 1788762628;
                } else {
                    colors4 = colors2;
                    scrollBehavior3 = topAppBarScrollBehavior;
                    actions4 = actions2;
                    windowInsets4 = windowInsets2;
                    modifier4 = modifier5;
                    navigationIcon4 = navigationIcon2;
                    i2 = 1788762628;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                colors4 = colors2;
                scrollBehavior3 = topAppBarScrollBehavior;
                i2 = 1788762628;
                actions4 = actions2;
                windowInsets4 = windowInsets2;
                modifier4 = modifier2;
                navigationIcon4 = navigationIcon2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.TopAppBar (AppBar.kt:168)");
            }
            $composer2 = $composer3;
            m2090TopAppBarGHTll3U(function2, modifier4, navigationIcon4, actions4, TopAppBarDefaults.INSTANCE.m3284getTopAppBarExpandedHeightD9Ej5fM(), windowInsets4, colors4, scrollBehavior3, $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            navigationIcon3 = navigationIcon4;
            actions3 = actions4;
            windowInsets3 = windowInsets4;
            colors3 = colors4;
            scrollBehavior2 = scrollBehavior3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            actions3 = actions2;
            navigationIcon3 = navigationIcon2;
            windowInsets3 = windowInsets2;
            colors3 = colors2;
            scrollBehavior2 = topAppBarScrollBehavior;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBar$lambda$0(title, modifier3, navigationIcon3, actions3, windowInsets3, colors3, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TopAppBar-GHTll3U, reason: not valid java name */
    public static final void m2090TopAppBarGHTll3U(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> lambda$657782987$material3;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M2280getLambda$1270442071$material3;
        float expandedHeight2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        Composer $composer2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function7;
        final float expandedHeight3;
        final WindowInsets windowInsets3;
        final TopAppBarColors colors3;
        final TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        TopAppBarScrollBehavior scrollBehavior3;
        Modifier modifier4;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function9;
        float expandedHeight4;
        WindowInsets windowInsets4;
        TopAppBarColors colors4;
        Composer $composer3 = $composer.startRestartGroup(1784421840);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)N(title,modifier,navigationIcon,actions,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)228@11268L5,225@11143L667:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function5 = function2;
        } else if (($changed & 6) == 0) {
            function5 = function2;
            $dirty2 |= $composer3.changedInstance(function5) ? 4 : 2;
        } else {
            function5 = function2;
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
            lambda$657782987$material3 = function3;
        } else if (($changed & 384) == 0) {
            lambda$657782987$material3 = function3;
            $dirty2 |= $composer3.changedInstance(lambda$657782987$material3) ? 256 : 128;
        } else {
            lambda$657782987$material3 = function3;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function3M2280getLambda$1270442071$material3 = function4;
        } else if (($changed & 3072) == 0) {
            function3M2280getLambda$1270442071$material3 = function4;
            $dirty2 |= $composer3.changedInstance(function3M2280getLambda$1270442071$material3) ? 2048 : 1024;
        } else {
            function3M2280getLambda$1270442071$material3 = function4;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            expandedHeight2 = expandedHeight;
        } else if (($changed & 24576) == 0) {
            expandedHeight2 = expandedHeight;
            $dirty2 |= $composer3.changed(expandedHeight2) ? 16384 : 8192;
        } else {
            expandedHeight2 = expandedHeight;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i6 = $composer3.changed(windowInsets2) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i6;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty2 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i7;
        } else {
            colors2 = colors;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(scrollBehavior) ? 8388608 : 4194304;
        }
        int $dirty3 = $dirty2;
        if ($composer3.shouldExecute(($dirty2 & 4793491) != 4793490, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "221@11001L12,222@11063L17");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if (i3 != 0) {
                    lambda$657782987$material3 = ComposableSingletons$AppBarKt.INSTANCE.getLambda$657782987$material3();
                }
                if (i4 != 0) {
                    function3M2280getLambda$1270442071$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2280getLambda$1270442071$material3();
                }
                if (i5 != 0) {
                    expandedHeight2 = TopAppBarDefaults.INSTANCE.m3284getTopAppBarExpandedHeightD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty3 & (-458753);
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                } else {
                    $dirty = $dirty3;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    colors2 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    modifier4 = modifier5;
                    scrollBehavior3 = null;
                    function8 = lambda$657782987$material3;
                    function9 = function3M2280getLambda$1270442071$material3;
                    expandedHeight4 = expandedHeight2;
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                } else {
                    scrollBehavior3 = scrollBehavior;
                    modifier4 = modifier5;
                    function8 = lambda$657782987$material3;
                    function9 = function3M2280getLambda$1270442071$material3;
                    expandedHeight4 = expandedHeight2;
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 32) != 0 ? $dirty3 & (-458753) : $dirty3;
                if ((i & 64) != 0) {
                    $dirty4 &= -3670017;
                }
                scrollBehavior3 = scrollBehavior;
                modifier4 = modifier2;
                function8 = lambda$657782987$material3;
                function9 = function3M2280getLambda$1270442071$material3;
                windowInsets4 = windowInsets2;
                colors4 = colors2;
                $dirty = $dirty4;
                expandedHeight4 = expandedHeight2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1784421840, $dirty, -1, "androidx.compose.material3.TopAppBar (AppBar.kt:225)");
            }
            $composer2 = $composer3;
            m2089SingleRowTopAppBarwn8IZOc(modifier4, function5, TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getTitleFont(), $composer3, 6), null, TextStyle.INSTANCE.getDefault(), Alignment.INSTANCE.getStart(), function8, function9, (Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) ? TopAppBarDefaults.INSTANCE.m3284getTopAppBarExpandedHeightD9Ej5fM() : expandedHeight4, windowInsets4, colors4, scrollBehavior3, $composer2, (($dirty >> 3) & 14) | 224256 | (($dirty << 3) & 112) | (($dirty << 12) & 3670016) | (($dirty << 12) & 29360128) | (($dirty << 12) & 1879048192), (($dirty >> 18) & 14) | (($dirty >> 18) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            expandedHeight3 = expandedHeight4;
            modifier3 = modifier4;
            function6 = function8;
            windowInsets3 = windowInsets4;
            colors3 = colors4;
            scrollBehavior2 = scrollBehavior3;
            function7 = function9;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function6 = lambda$657782987$material3;
            function7 = function3M2280getLambda$1270442071$material3;
            expandedHeight3 = expandedHeight2;
            windowInsets3 = windowInsets2;
            colors3 = colors2;
            scrollBehavior2 = scrollBehavior;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBar_GHTll3U$lambda$1(function2, modifier3, function6, function7, expandedHeight3, windowInsets3, colors3, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of CenterAlignedTopAppBar with expandedHeight parameter")
    public static final /* synthetic */ void CenterAlignedTopAppBar(final Function2 title, Modifier modifier, Function2 navigationIcon, Function3 actions, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2 function2;
        Modifier modifier2;
        Function2 navigationIcon2;
        Function3 actions2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        Composer $composer2;
        final Modifier modifier3;
        final Function3 actions3;
        final Function2 navigationIcon3;
        final WindowInsets windowInsets3;
        final TopAppBarColors colors3;
        final TopAppBarScrollBehavior scrollBehavior2;
        TopAppBarColors colors4;
        TopAppBarScrollBehavior scrollBehavior3;
        Function3 actions4;
        WindowInsets windowInsets4;
        Modifier modifier4;
        Function2 navigationIcon4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(603182999);
        ComposerKt.sourceInformation($composer3, "C(CenterAlignedTopAppBar)N(title,modifier,navigationIcon,actions,windowInsets,colors,scrollBehavior)291@14250L320:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function2 = title;
        } else if (($changed & 6) == 0) {
            function2 = title;
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = title;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            navigationIcon2 = navigationIcon;
        } else if (($changed & 384) == 0) {
            navigationIcon2 = navigationIcon;
            $dirty |= $composer3.changedInstance(navigationIcon2) ? 256 : 128;
        } else {
            navigationIcon2 = navigationIcon;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            actions2 = actions;
        } else if (($changed & 3072) == 0) {
            actions2 = actions;
            $dirty |= $composer3.changedInstance(actions2) ? 2048 : 1024;
        } else {
            actions2 = actions;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i6 = $composer3.changed(windowInsets2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i6;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 131072 : 65536;
                $dirty |= i7;
            } else {
                colors2 = colors;
            }
            $dirty |= i7;
        } else {
            colors2 = colors;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if ((1572864 & $changed) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ($composer3.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "287@14108L12,288@14170L17");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if (i4 != 0) {
                    navigationIcon2 = ComposableSingletons$AppBarKt.INSTANCE.getLambda$1866429650$material3();
                }
                if (i5 != 0) {
                    actions2 = ComposableSingletons$AppBarKt.INSTANCE.m2290getLambda$541682128$material3();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    scrollBehavior3 = null;
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                    navigationIcon4 = navigationIcon2;
                    actions4 = actions2;
                    modifier4 = modifier5;
                    i2 = 603182999;
                } else {
                    colors4 = colors2;
                    scrollBehavior3 = topAppBarScrollBehavior;
                    actions4 = actions2;
                    windowInsets4 = windowInsets2;
                    modifier4 = modifier5;
                    navigationIcon4 = navigationIcon2;
                    i2 = 603182999;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                colors4 = colors2;
                scrollBehavior3 = topAppBarScrollBehavior;
                i2 = 603182999;
                actions4 = actions2;
                windowInsets4 = windowInsets2;
                modifier4 = modifier2;
                navigationIcon4 = navigationIcon2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.CenterAlignedTopAppBar (AppBar.kt:291)");
            }
            $composer2 = $composer3;
            m2083CenterAlignedTopAppBarGHTll3U(function2, modifier4, navigationIcon4, actions4, TopAppBarDefaults.INSTANCE.m3284getTopAppBarExpandedHeightD9Ej5fM(), windowInsets4, colors4, scrollBehavior3, $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            navigationIcon3 = navigationIcon4;
            actions3 = actions4;
            windowInsets3 = windowInsets4;
            colors3 = colors4;
            scrollBehavior2 = scrollBehavior3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            actions3 = actions2;
            navigationIcon3 = navigationIcon2;
            windowInsets3 = windowInsets2;
            colors3 = colors2;
            scrollBehavior2 = topAppBarScrollBehavior;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.CenterAlignedTopAppBar$lambda$2(title, modifier3, navigationIcon3, actions3, windowInsets3, colors3, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: CenterAlignedTopAppBar-GHTll3U, reason: not valid java name */
    public static final void m2083CenterAlignedTopAppBarGHTll3U(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> lambda$575301698$material3;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M2291getLambda$643931612$material3;
        float expandedHeight2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        Composer $composer2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function7;
        final float expandedHeight3;
        final WindowInsets windowInsets3;
        final TopAppBarColors colors3;
        final TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        TopAppBarScrollBehavior scrollBehavior3;
        Modifier modifier4;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function9;
        float expandedHeight4;
        WindowInsets windowInsets4;
        TopAppBarColors colors4;
        Composer $composer3 = $composer.startRestartGroup(-302230691);
        ComposerKt.sourceInformation($composer3, "C(CenterAlignedTopAppBar)N(title,modifier,navigationIcon,actions,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)352@17429L5,349@17304L680:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function5 = function2;
        } else if (($changed & 6) == 0) {
            function5 = function2;
            $dirty2 |= $composer3.changedInstance(function5) ? 4 : 2;
        } else {
            function5 = function2;
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
            lambda$575301698$material3 = function3;
        } else if (($changed & 384) == 0) {
            lambda$575301698$material3 = function3;
            $dirty2 |= $composer3.changedInstance(lambda$575301698$material3) ? 256 : 128;
        } else {
            lambda$575301698$material3 = function3;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function3M2291getLambda$643931612$material3 = function4;
        } else if (($changed & 3072) == 0) {
            function3M2291getLambda$643931612$material3 = function4;
            $dirty2 |= $composer3.changedInstance(function3M2291getLambda$643931612$material3) ? 2048 : 1024;
        } else {
            function3M2291getLambda$643931612$material3 = function4;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            expandedHeight2 = expandedHeight;
        } else if (($changed & 24576) == 0) {
            expandedHeight2 = expandedHeight;
            $dirty2 |= $composer3.changed(expandedHeight2) ? 16384 : 8192;
        } else {
            expandedHeight2 = expandedHeight;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i6 = $composer3.changed(windowInsets2) ? 131072 : 65536;
                $dirty2 |= i6;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty2 |= i6;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty2 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i7;
        } else {
            colors2 = colors;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(scrollBehavior) ? 8388608 : 4194304;
        }
        int $dirty3 = $dirty2;
        if ($composer3.shouldExecute(($dirty2 & 4793491) != 4793490, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "345@17162L12,346@17224L17");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if (i3 != 0) {
                    lambda$575301698$material3 = ComposableSingletons$AppBarKt.INSTANCE.getLambda$575301698$material3();
                }
                if (i4 != 0) {
                    function3M2291getLambda$643931612$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2291getLambda$643931612$material3();
                }
                if (i5 != 0) {
                    expandedHeight2 = TopAppBarDefaults.INSTANCE.m3284getTopAppBarExpandedHeightD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty3 & (-458753);
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                } else {
                    $dirty = $dirty3;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    colors2 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    modifier4 = modifier5;
                    scrollBehavior3 = null;
                    function8 = lambda$575301698$material3;
                    function9 = function3M2291getLambda$643931612$material3;
                    expandedHeight4 = expandedHeight2;
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                } else {
                    scrollBehavior3 = scrollBehavior;
                    modifier4 = modifier5;
                    function8 = lambda$575301698$material3;
                    function9 = function3M2291getLambda$643931612$material3;
                    expandedHeight4 = expandedHeight2;
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 32) != 0 ? $dirty3 & (-458753) : $dirty3;
                if ((i & 64) != 0) {
                    $dirty4 &= -3670017;
                }
                scrollBehavior3 = scrollBehavior;
                modifier4 = modifier2;
                function8 = lambda$575301698$material3;
                function9 = function3M2291getLambda$643931612$material3;
                windowInsets4 = windowInsets2;
                colors4 = colors2;
                $dirty = $dirty4;
                expandedHeight4 = expandedHeight2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-302230691, $dirty, -1, "androidx.compose.material3.CenterAlignedTopAppBar (AppBar.kt:349)");
            }
            $composer2 = $composer3;
            m2089SingleRowTopAppBarwn8IZOc(modifier4, function5, TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getTitleFont(), $composer3, 6), null, TextStyle.INSTANCE.getDefault(), Alignment.INSTANCE.getCenterHorizontally(), function8, function9, (Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) ? TopAppBarDefaults.INSTANCE.m3284getTopAppBarExpandedHeightD9Ej5fM() : expandedHeight4, windowInsets4, colors4, scrollBehavior3, $composer2, (($dirty >> 3) & 14) | 224256 | (($dirty << 3) & 112) | (($dirty << 12) & 3670016) | (($dirty << 12) & 29360128) | (($dirty << 12) & 1879048192), (($dirty >> 18) & 14) | (($dirty >> 18) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            expandedHeight3 = expandedHeight4;
            modifier3 = modifier4;
            function6 = function8;
            windowInsets3 = windowInsets4;
            colors3 = colors4;
            scrollBehavior2 = scrollBehavior3;
            function7 = function9;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function6 = lambda$575301698$material3;
            function7 = function3M2291getLambda$643931612$material3;
            expandedHeight3 = expandedHeight2;
            windowInsets3 = windowInsets2;
            colors3 = colors2;
            scrollBehavior2 = scrollBehavior;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.CenterAlignedTopAppBar_GHTll3U$lambda$3(function2, modifier3, function6, function7, expandedHeight3, windowInsets3, colors3, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TopAppBar-cJHQLPU, reason: not valid java name */
    public static final void m2091TopAppBarcJHQLPU(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function4, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function5, Alignment.Horizontal titleHorizontalAlignment, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function2M2278getLambda$1168245838$material3;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M2282getLambda$1401945836$material3;
        Alignment.Horizontal titleHorizontalAlignment2;
        Composer $composer2;
        final WindowInsets windowInsets2;
        final TopAppBarScrollBehavior scrollBehavior2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function9;
        final Alignment.Horizontal titleHorizontalAlignment3;
        final float expandedHeight2;
        final TopAppBarColors colors2;
        float expandedHeight3;
        WindowInsets windowInsets3;
        int $dirty;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior3;
        WindowInsets windowInsets4;
        Function2<? super Composer, ? super Integer, Unit> function10;
        TopAppBarColors colors4;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function11;
        Alignment.Horizontal titleHorizontalAlignment4;
        Modifier modifier4;
        float fM3284getTopAppBarExpandedHeightD9Ej5fM;
        Composer $composer3 = $composer.startRestartGroup(1954772493);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)N(title,subtitle,modifier,navigationIcon,actions,titleHorizontalAlignment,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)386@18726L5,388@18821L5,383@18601L699:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function6 = function2;
        } else if (($changed & 6) == 0) {
            function6 = function2;
            $dirty2 |= $composer3.changedInstance(function6) ? 4 : 2;
        } else {
            function6 = function2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function7 = function3;
        } else if (($changed & 48) == 0) {
            function7 = function3;
            $dirty2 |= $composer3.changedInstance(function7) ? 32 : 16;
        } else {
            function7 = function3;
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
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty2 |= 3072;
            function2M2278getLambda$1168245838$material3 = function4;
        } else if (($changed & 3072) == 0) {
            function2M2278getLambda$1168245838$material3 = function4;
            $dirty2 |= $composer3.changedInstance(function2M2278getLambda$1168245838$material3) ? 2048 : 1024;
        } else {
            function2M2278getLambda$1168245838$material3 = function4;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty2 |= 24576;
            function3M2282getLambda$1401945836$material3 = function5;
        } else if (($changed & 24576) == 0) {
            function3M2282getLambda$1401945836$material3 = function5;
            $dirty2 |= $composer3.changedInstance(function3M2282getLambda$1401945836$material3) ? 16384 : 8192;
        } else {
            function3M2282getLambda$1401945836$material3 = function5;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            titleHorizontalAlignment2 = titleHorizontalAlignment;
        } else if ((196608 & $changed) == 0) {
            titleHorizontalAlignment2 = titleHorizontalAlignment;
            $dirty2 |= $composer3.changed(titleHorizontalAlignment2) ? 131072 : 65536;
        } else {
            titleHorizontalAlignment2 = titleHorizontalAlignment;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changed(expandedHeight) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= ((i & 128) == 0 && $composer3.changed(windowInsets)) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= ((i & 256) == 0 && $composer3.changed(colors)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i7 = i & 512;
        if (i7 != 0) {
            $dirty2 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changed(scrollBehavior) ? 536870912 : 268435456;
        }
        int $dirty3 = $dirty2;
        if (!$composer3.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty3 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            windowInsets2 = windowInsets;
            scrollBehavior2 = scrollBehavior;
            modifier3 = modifier2;
            function8 = function2M2278getLambda$1168245838$material3;
            function9 = function3M2282getLambda$1401945836$material3;
            titleHorizontalAlignment3 = titleHorizontalAlignment2;
            expandedHeight2 = expandedHeight;
            colors2 = colors;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "379@18459L12,380@18521L17");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 128) != 0 ? $dirty3 & (-29360129) : $dirty3;
                if ((i & 256) != 0) {
                    $dirty4 &= -234881025;
                }
                windowInsets4 = windowInsets;
                colors4 = colors;
                scrollBehavior3 = scrollBehavior;
                $dirty = $dirty4;
                function10 = function2M2278getLambda$1168245838$material3;
                function11 = function3M2282getLambda$1401945836$material3;
                titleHorizontalAlignment4 = titleHorizontalAlignment2;
                expandedHeight3 = expandedHeight;
                modifier4 = modifier2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function2M2278getLambda$1168245838$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2278getLambda$1168245838$material3();
                }
                if (i4 != 0) {
                    function3M2282getLambda$1401945836$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2282getLambda$1401945836$material3();
                }
                if (i5 != 0) {
                    titleHorizontalAlignment2 = Alignment.INSTANCE.getStart();
                }
                if (i6 == 0) {
                    expandedHeight3 = expandedHeight;
                } else {
                    expandedHeight3 = TopAppBarDefaults.INSTANCE.m3284getTopAppBarExpandedHeightD9Ej5fM();
                }
                if ((i & 128) == 0) {
                    windowInsets3 = windowInsets;
                    $dirty = $dirty3;
                } else {
                    windowInsets3 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty = $dirty3 & (-29360129);
                }
                if ((i & 256) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                    $dirty &= -234881025;
                }
                if (i7 == 0) {
                    scrollBehavior3 = scrollBehavior;
                    windowInsets4 = windowInsets3;
                    function10 = function2M2278getLambda$1168245838$material3;
                    colors4 = colors3;
                    function11 = function3M2282getLambda$1401945836$material3;
                    titleHorizontalAlignment4 = titleHorizontalAlignment2;
                    modifier4 = modifier2;
                } else {
                    windowInsets4 = windowInsets3;
                    function10 = function2M2278getLambda$1168245838$material3;
                    colors4 = colors3;
                    function11 = function3M2282getLambda$1401945836$material3;
                    scrollBehavior3 = null;
                    titleHorizontalAlignment4 = titleHorizontalAlignment2;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1954772493, $dirty, -1, "androidx.compose.material3.TopAppBar (AppBar.kt:383)");
            }
            TextStyle value = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getTitleFont(), $composer3, 6);
            TextStyle value2 = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getSubtitleFont(), $composer3, 6);
            if (Dp.m7910equalsimpl0(expandedHeight3, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(expandedHeight3, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                fM3284getTopAppBarExpandedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3284getTopAppBarExpandedHeightD9Ej5fM();
            } else {
                fM3284getTopAppBarExpandedHeightD9Ej5fM = expandedHeight3;
            }
            $composer2 = $composer3;
            m2089SingleRowTopAppBarwn8IZOc(modifier4, function6, value, function7, value2, titleHorizontalAlignment4, function10, function11, fM3284getTopAppBarExpandedHeightD9Ej5fM, windowInsets4, colors4, scrollBehavior3, $composer2, (($dirty >> 6) & 14) | (($dirty << 3) & 112) | (($dirty << 6) & 7168) | (458752 & $dirty) | (($dirty << 9) & 3670016) | (($dirty << 9) & 29360128) | (($dirty << 6) & 1879048192), (($dirty >> 24) & 14) | (($dirty >> 24) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            expandedHeight2 = expandedHeight3;
            modifier3 = modifier4;
            function8 = function10;
            function9 = function11;
            windowInsets2 = windowInsets4;
            colors2 = colors4;
            scrollBehavior2 = scrollBehavior3;
            titleHorizontalAlignment3 = titleHorizontalAlignment4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBar_cJHQLPU$lambda$4(function2, function3, modifier3, function8, function9, titleHorizontalAlignment3, expandedHeight2, windowInsets2, colors2, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of MediumTopAppBar with collapsedHeight and expandedHeight parameters")
    public static final /* synthetic */ void MediumTopAppBar(final Function2 title, Modifier modifier, Function2 navigationIcon, Function3 actions, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2 function2;
        Modifier modifier2;
        Function2 navigationIcon2;
        Function3 actions2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        Composer $composer2;
        final Modifier modifier3;
        final Function3 actions3;
        final Function2 navigationIcon3;
        final WindowInsets windowInsets3;
        final TopAppBarColors colors3;
        final TopAppBarScrollBehavior scrollBehavior2;
        WindowInsets windowInsets4;
        TopAppBarColors colors4;
        TopAppBarScrollBehavior scrollBehavior3;
        Function2 navigationIcon4;
        Function3 actions4;
        Modifier modifier4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(-319217319);
        ComposerKt.sourceInformation($composer3, "C(MediumTopAppBar)N(title,modifier,navigationIcon,actions,windowInsets,colors,scrollBehavior)451@21910L389:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function2 = title;
        } else if (($changed & 6) == 0) {
            function2 = title;
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = title;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            navigationIcon2 = navigationIcon;
        } else if (($changed & 384) == 0) {
            navigationIcon2 = navigationIcon;
            $dirty |= $composer3.changedInstance(navigationIcon2) ? 256 : 128;
        } else {
            navigationIcon2 = navigationIcon;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            actions2 = actions;
        } else if (($changed & 3072) == 0) {
            actions2 = actions;
            $dirty |= $composer3.changedInstance(actions2) ? 2048 : 1024;
        } else {
            actions2 = actions;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i6 = $composer3.changed(windowInsets2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i6;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 131072 : 65536;
                $dirty |= i7;
            } else {
                colors2 = colors;
            }
            $dirty |= i7;
        } else {
            colors2 = colors;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if ((1572864 & $changed) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ($composer3.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "447@21768L12,448@21830L17");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if (i4 != 0) {
                    navigationIcon2 = ComposableSingletons$AppBarKt.INSTANCE.getLambda$1152131198$material3();
                }
                if (i5 != 0) {
                    actions2 = ComposableSingletons$AppBarKt.INSTANCE.m2283getLambda$1707954976$material3();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    scrollBehavior3 = null;
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                    navigationIcon4 = navigationIcon2;
                    actions4 = actions2;
                    modifier4 = modifier5;
                    i2 = -319217319;
                } else {
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                    scrollBehavior3 = topAppBarScrollBehavior;
                    navigationIcon4 = navigationIcon2;
                    actions4 = actions2;
                    modifier4 = modifier5;
                    i2 = -319217319;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                windowInsets4 = windowInsets2;
                colors4 = colors2;
                scrollBehavior3 = topAppBarScrollBehavior;
                i2 = -319217319;
                navigationIcon4 = navigationIcon2;
                actions4 = actions2;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.MediumTopAppBar (AppBar.kt:451)");
            }
            $composer2 = $composer3;
            m2088MediumTopAppBaroKE7A98(function2, modifier4, navigationIcon4, actions4, TopAppBarDefaults.INSTANCE.m3280getMediumAppBarCollapsedHeightD9Ej5fM(), TopAppBarDefaults.INSTANCE.m3281getMediumAppBarExpandedHeightD9Ej5fM(), windowInsets4, colors4, scrollBehavior3, $composer2, ($dirty & 14) | 221184 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 3670016) | (($dirty << 6) & 29360128) | (($dirty << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            navigationIcon3 = navigationIcon4;
            actions3 = actions4;
            windowInsets3 = windowInsets4;
            colors3 = colors4;
            scrollBehavior2 = scrollBehavior3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            actions3 = actions2;
            navigationIcon3 = navigationIcon2;
            windowInsets3 = windowInsets2;
            colors3 = colors2;
            scrollBehavior2 = topAppBarScrollBehavior;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.MediumTopAppBar$lambda$5(title, modifier3, navigationIcon3, actions3, windowInsets3, colors3, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: MediumTopAppBar-oKE7A98, reason: not valid java name */
    public static final void m2088MediumTopAppBaroKE7A98(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4, float collapsedHeight, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function2M2288getLambda$229000834$material3;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M2281getLambda$1276513184$material3;
        float collapsedHeight2;
        float expandedHeight2;
        Composer $composer2;
        final WindowInsets windowInsets2;
        final TopAppBarScrollBehavior scrollBehavior2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function7;
        final float collapsedHeight3;
        final float expandedHeight3;
        final TopAppBarColors colors2;
        WindowInsets windowInsets3;
        int $dirty;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior3;
        WindowInsets windowInsets4;
        TopAppBarColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function9;
        float collapsedHeight4;
        Modifier modifier4;
        int $dirty2;
        float expandedHeight4;
        float fM3280getMediumAppBarCollapsedHeightD9Ej5fM;
        float fM3281getMediumAppBarExpandedHeightD9Ej5fM;
        Composer $composer3 = $composer.startRestartGroup(-1378129383);
        ComposerKt.sourceInformation($composer3, "C(MediumTopAppBar)N(title,modifier,navigationIcon,actions,collapsedHeight:c#ui.unit.Dp,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)521@25897L5,522@25962L5,518@25773L1142:AppBar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function5 = function2;
        } else if (($changed & 6) == 0) {
            function5 = function2;
            $dirty3 |= $composer3.changedInstance(function5) ? 4 : 2;
        } else {
            function5 = function2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            function2M2288getLambda$229000834$material3 = function3;
        } else if (($changed & 384) == 0) {
            function2M2288getLambda$229000834$material3 = function3;
            $dirty3 |= $composer3.changedInstance(function2M2288getLambda$229000834$material3) ? 256 : 128;
        } else {
            function2M2288getLambda$229000834$material3 = function3;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            function3M2281getLambda$1276513184$material3 = function4;
        } else if (($changed & 3072) == 0) {
            function3M2281getLambda$1276513184$material3 = function4;
            $dirty3 |= $composer3.changedInstance(function3M2281getLambda$1276513184$material3) ? 2048 : 1024;
        } else {
            function3M2281getLambda$1276513184$material3 = function4;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
            collapsedHeight2 = collapsedHeight;
        } else if (($changed & 24576) == 0) {
            collapsedHeight2 = collapsedHeight;
            $dirty3 |= $composer3.changed(collapsedHeight2) ? 16384 : 8192;
        } else {
            collapsedHeight2 = collapsedHeight;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            expandedHeight2 = expandedHeight;
        } else if ((196608 & $changed) == 0) {
            expandedHeight2 = expandedHeight;
            $dirty3 |= $composer3.changed(expandedHeight2) ? 131072 : 65536;
        } else {
            expandedHeight2 = expandedHeight;
        }
        if (($changed & 1572864) == 0) {
            $dirty3 |= ((i & 64) == 0 && $composer3.changed(windowInsets)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= ((i & 128) == 0 && $composer3.changed(colors)) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changed(scrollBehavior) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if (!$composer3.shouldExecute(($dirty3 & 38347923) != 38347922, $dirty4 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            windowInsets2 = windowInsets;
            scrollBehavior2 = scrollBehavior;
            modifier3 = modifier2;
            function6 = function2M2288getLambda$229000834$material3;
            function7 = function3M2281getLambda$1276513184$material3;
            collapsedHeight3 = collapsedHeight2;
            expandedHeight3 = expandedHeight2;
            colors2 = colors;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "514@25631L12,515@25693L17");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 64) != 0 ? $dirty4 & (-3670017) : $dirty4;
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                Modifier modifier5 = modifier2;
                $dirty2 = $dirty5;
                collapsedHeight4 = collapsedHeight2;
                modifier4 = modifier5;
                windowInsets4 = windowInsets;
                colors4 = colors;
                scrollBehavior3 = scrollBehavior;
                function8 = function2M2288getLambda$229000834$material3;
                function9 = function3M2281getLambda$1276513184$material3;
                expandedHeight4 = expandedHeight2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function2M2288getLambda$229000834$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2288getLambda$229000834$material3();
                }
                if (i4 != 0) {
                    function3M2281getLambda$1276513184$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2281getLambda$1276513184$material3();
                }
                if (i5 != 0) {
                    collapsedHeight2 = TopAppBarDefaults.INSTANCE.m3280getMediumAppBarCollapsedHeightD9Ej5fM();
                }
                if (i6 != 0) {
                    expandedHeight2 = TopAppBarDefaults.INSTANCE.m3281getMediumAppBarExpandedHeightD9Ej5fM();
                }
                if ((i & 64) == 0) {
                    windowInsets3 = windowInsets;
                    $dirty = $dirty4;
                } else {
                    windowInsets3 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty = $dirty4 & (-3670017);
                }
                if ((i & 128) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                    $dirty &= -29360129;
                }
                if (i7 == 0) {
                    scrollBehavior3 = scrollBehavior;
                    windowInsets4 = windowInsets3;
                    colors4 = colors3;
                    function8 = function2M2288getLambda$229000834$material3;
                    function9 = function3M2281getLambda$1276513184$material3;
                    collapsedHeight4 = collapsedHeight2;
                    modifier4 = modifier2;
                    $dirty2 = $dirty;
                    expandedHeight4 = expandedHeight2;
                } else {
                    windowInsets4 = windowInsets3;
                    colors4 = colors3;
                    function8 = function2M2288getLambda$229000834$material3;
                    scrollBehavior3 = null;
                    function9 = function3M2281getLambda$1276513184$material3;
                    collapsedHeight4 = collapsedHeight2;
                    modifier4 = modifier2;
                    $dirty2 = $dirty;
                    expandedHeight4 = expandedHeight2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1378129383, $dirty2, -1, "androidx.compose.material3.MediumTopAppBar (AppBar.kt:518)");
            }
            TextStyle value = TypographyKt.getValue(AppBarMediumTokens.INSTANCE.getTitleFont(), $composer3, 6);
            TextStyle value2 = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getTitleFont(), $composer3, 6);
            float f = MediumTitleBottomPadding;
            TextStyle textStyle = TextStyle.INSTANCE.getDefault();
            TextStyle textStyle2 = TextStyle.INSTANCE.getDefault();
            Alignment.Horizontal start = Alignment.INSTANCE.getStart();
            if (Dp.m7910equalsimpl0(collapsedHeight4, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(collapsedHeight4, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                fM3280getMediumAppBarCollapsedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3280getMediumAppBarCollapsedHeightD9Ej5fM();
            } else {
                fM3280getMediumAppBarCollapsedHeightD9Ej5fM = collapsedHeight4;
            }
            if (Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                fM3281getMediumAppBarExpandedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3281getMediumAppBarExpandedHeightD9Ej5fM();
            } else {
                fM3281getMediumAppBarExpandedHeightD9Ej5fM = expandedHeight4;
            }
            $composer2 = $composer3;
            m2094TwoRowsTopAppBarpJA5dT0(modifier4, function5, value, f, function2, value2, null, textStyle, null, textStyle2, start, function8, function9, fM3280getMediumAppBarCollapsedHeightD9Ej5fM, fM3281getMediumAppBarExpandedHeightD9Ej5fM, windowInsets4, colors4, scrollBehavior3, $composer2, (($dirty2 >> 3) & 14) | 920128512 | (($dirty2 << 3) & 112) | (($dirty2 << 12) & 57344), (($dirty2 >> 3) & 112) | 6 | (($dirty2 >> 3) & 896) | (($dirty2 >> 3) & 458752) | (($dirty2 >> 3) & 3670016) | (($dirty2 >> 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            collapsedHeight3 = collapsedHeight4;
            expandedHeight3 = expandedHeight4;
            modifier3 = modifier4;
            function6 = function8;
            windowInsets2 = windowInsets4;
            colors2 = colors4;
            scrollBehavior2 = scrollBehavior3;
            function7 = function9;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.MediumTopAppBar_oKE7A98$lambda$6(function2, modifier3, function6, function7, collapsedHeight3, expandedHeight3, windowInsets2, colors2, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: MediumFlexibleTopAppBar-eXZ4JBQ, reason: not valid java name */
    public static final void m2087MediumFlexibleTopAppBareXZ4JBQ(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function5, Alignment.Horizontal titleHorizontalAlignment, float collapsedHeight, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function6;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function2M2285getLambda$185842620$material3;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> lambda$1113419554$material3;
        float collapsedHeight2;
        Composer $composer2;
        final WindowInsets windowInsets2;
        final TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        final float expandedHeight2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Alignment.Horizontal titleHorizontalAlignment2;
        final float expandedHeight3;
        final TopAppBarColors colors2;
        Alignment.Horizontal titleHorizontalAlignment3;
        float expandedHeight4;
        int $dirty2;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior3;
        Alignment.Horizontal titleHorizontalAlignment4;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function10;
        WindowInsets windowInsets4;
        Function2<? super Composer, ? super Integer, Unit> function11;
        TopAppBarColors colors4;
        Modifier modifier4;
        float fM3280getMediumAppBarCollapsedHeightD9Ej5fM;
        float fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM;
        Composer $composer3 = $composer.startRestartGroup(-1895470263);
        ComposerKt.sourceInformation($composer3, "C(MediumFlexibleTopAppBar)N(title,modifier,subtitle,navigationIcon,actions,titleHorizontalAlignment,collapsedHeight:c#ui.unit.Dp,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)616@30967L5,617@31032L5,621@31225L5,623@31336L5,613@30835L1421:AppBar.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function6 = function2;
        } else if (($changed & 6) == 0) {
            function6 = function2;
            $dirty3 |= $composer3.changedInstance(function6) ? 4 : 2;
        } else {
            function6 = function2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            function7 = function3;
        } else if (($changed & 384) == 0) {
            function7 = function3;
            $dirty3 |= $composer3.changedInstance(function7) ? 256 : 128;
        } else {
            function7 = function3;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            function2M2285getLambda$185842620$material3 = function4;
        } else if (($changed & 3072) == 0) {
            function2M2285getLambda$185842620$material3 = function4;
            $dirty3 |= $composer3.changedInstance(function2M2285getLambda$185842620$material3) ? 2048 : 1024;
        } else {
            function2M2285getLambda$185842620$material3 = function4;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
            lambda$1113419554$material3 = function5;
        } else if (($changed & 24576) == 0) {
            lambda$1113419554$material3 = function5;
            $dirty3 |= $composer3.changedInstance(lambda$1113419554$material3) ? 16384 : 8192;
        } else {
            lambda$1113419554$material3 = function5;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changed(titleHorizontalAlignment) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            collapsedHeight2 = collapsedHeight;
        } else if (($changed & 1572864) == 0) {
            collapsedHeight2 = collapsedHeight;
            $dirty3 |= $composer3.changed(collapsedHeight2) ? 1048576 : 524288;
        } else {
            collapsedHeight2 = collapsedHeight;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= ((i & 128) == 0 && $composer3.changed(expandedHeight)) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty3 |= ((i & 256) == 0 && $composer3.changed(windowInsets)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= ((i & 512) == 0 && $composer3.changed(colors)) ? 536870912 : 268435456;
        }
        int i8 = i & 1024;
        if (i8 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(scrollBehavior) ? 4 : 2;
        }
        int $dirty4 = $dirty3;
        if (!$composer3.shouldExecute((($dirty3 & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty4 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            windowInsets2 = windowInsets;
            scrollBehavior2 = scrollBehavior;
            $dirty = $dirty4;
            expandedHeight2 = collapsedHeight2;
            modifier3 = modifier2;
            function8 = function7;
            function9 = function2M2285getLambda$185842620$material3;
            titleHorizontalAlignment2 = titleHorizontalAlignment;
            expandedHeight3 = expandedHeight;
            colors2 = colors;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "609@30693L12,610@30755L17");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 128) != 0 ? $dirty4 & (-29360129) : $dirty4;
                if ((i & 256) != 0) {
                    $dirty5 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty5 &= -1879048193;
                }
                titleHorizontalAlignment4 = titleHorizontalAlignment;
                expandedHeight4 = expandedHeight;
                windowInsets4 = windowInsets;
                colors4 = colors;
                scrollBehavior3 = scrollBehavior;
                $dirty2 = $dirty5;
                function10 = lambda$1113419554$material3;
                modifier4 = modifier2;
                function11 = function2M2285getLambda$185842620$material3;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function7 = null;
                }
                if (i4 != 0) {
                    function2M2285getLambda$185842620$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2285getLambda$185842620$material3();
                }
                if (i5 != 0) {
                    lambda$1113419554$material3 = ComposableSingletons$AppBarKt.INSTANCE.getLambda$1113419554$material3();
                }
                if (i6 == 0) {
                    titleHorizontalAlignment3 = titleHorizontalAlignment;
                } else {
                    titleHorizontalAlignment3 = Alignment.INSTANCE.getStart();
                }
                if (i7 != 0) {
                    collapsedHeight2 = TopAppBarDefaults.INSTANCE.m3280getMediumAppBarCollapsedHeightD9Ej5fM();
                }
                if ((i & 128) == 0) {
                    expandedHeight4 = expandedHeight;
                    $dirty2 = $dirty4;
                } else {
                    if (function7 != null) {
                        expandedHeight4 = TopAppBarDefaults.INSTANCE.m3282getMediumFlexibleAppBarWithSubtitleExpandedHeightD9Ej5fM();
                    } else {
                        expandedHeight4 = TopAppBarDefaults.INSTANCE.m3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM();
                    }
                    $dirty2 = $dirty4 & (-29360129);
                }
                if ((i & 256) == 0) {
                    windowInsets3 = windowInsets;
                } else {
                    windowInsets3 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty2 &= -234881025;
                }
                if ((i & 512) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                    $dirty2 &= -1879048193;
                }
                if (i8 == 0) {
                    scrollBehavior3 = scrollBehavior;
                    titleHorizontalAlignment4 = titleHorizontalAlignment3;
                    function10 = lambda$1113419554$material3;
                    windowInsets4 = windowInsets3;
                    function11 = function2M2285getLambda$185842620$material3;
                    colors4 = colors3;
                    modifier4 = modifier2;
                } else {
                    titleHorizontalAlignment4 = titleHorizontalAlignment3;
                    function10 = lambda$1113419554$material3;
                    windowInsets4 = windowInsets3;
                    function11 = function2M2285getLambda$185842620$material3;
                    colors4 = colors3;
                    scrollBehavior3 = null;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1895470263, $dirty2, $dirty1, "androidx.compose.material3.MediumFlexibleTopAppBar (AppBar.kt:613)");
            }
            TextStyle value = TypographyKt.getValue(AppBarMediumFlexibleTokens.INSTANCE.getTitleFont(), $composer3, 6);
            TextStyle value2 = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getTitleFont(), $composer3, 6);
            float f = MediumTitleBottomPadding;
            Function2<? super Composer, ? super Integer, Unit> function2M2295getLambda$790317886$material3 = function7 == null ? ComposableSingletons$AppBarKt.INSTANCE.m2295getLambda$790317886$material3() : function7;
            TextStyle value3 = TypographyKt.getValue(AppBarMediumFlexibleTokens.INSTANCE.getSubtitleFont(), $composer3, 6);
            Function2<? super Composer, ? super Integer, Unit> function2M2286getLambda$1926007989$material3 = function7 == null ? ComposableSingletons$AppBarKt.INSTANCE.m2286getLambda$1926007989$material3() : function7;
            TextStyle value4 = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getSubtitleFont(), $composer3, 6);
            if (Dp.m7910equalsimpl0(collapsedHeight2, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(collapsedHeight2, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                fM3280getMediumAppBarCollapsedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3280getMediumAppBarCollapsedHeightD9Ej5fM();
            } else {
                fM3280getMediumAppBarCollapsedHeightD9Ej5fM = collapsedHeight2;
            }
            if (Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                if (function7 != null) {
                    fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3282getMediumFlexibleAppBarWithSubtitleExpandedHeightD9Ej5fM();
                } else {
                    fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM();
                }
            } else {
                fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM = expandedHeight4;
            }
            $composer2 = $composer3;
            m2094TwoRowsTopAppBarpJA5dT0(modifier4, function6, value, f, function2, value2, function2M2295getLambda$790317886$material3, value3, function2M2286getLambda$1926007989$material3, value4, titleHorizontalAlignment4, function11, function10, fM3280getMediumAppBarCollapsedHeightD9Ej5fM, fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM, windowInsets4, colors4, scrollBehavior3, $composer2, (($dirty2 >> 3) & 14) | 3072 | (($dirty2 << 3) & 112) | (($dirty2 << 12) & 57344), (($dirty2 >> 15) & 14) | (($dirty2 >> 6) & 112) | (($dirty2 >> 6) & 896) | (($dirty2 >> 9) & 458752) | (($dirty2 >> 9) & 3670016) | (($dirty1 << 21) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            expandedHeight3 = expandedHeight4;
            function8 = function7;
            modifier3 = modifier4;
            function9 = function11;
            lambda$1113419554$material3 = function10;
            colors2 = colors4;
            scrollBehavior2 = scrollBehavior3;
            expandedHeight2 = collapsedHeight2;
            $dirty = $dirty2;
            titleHorizontalAlignment2 = titleHorizontalAlignment4;
            windowInsets2 = windowInsets4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.MediumFlexibleTopAppBar_eXZ4JBQ$lambda$7(function2, modifier3, function8, function9, lambda$1113419554$material3, titleHorizontalAlignment2, expandedHeight2, expandedHeight3, windowInsets2, colors2, scrollBehavior2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of LargeTopAppBar with collapsedHeight and expandedHeight parameters")
    public static final /* synthetic */ void LargeTopAppBar(final Function2 title, Modifier modifier, Function2 navigationIcon, Function3 actions, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2 function2;
        Modifier modifier2;
        Function2 navigationIcon2;
        Function3 actions2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        Composer $composer2;
        final Modifier modifier3;
        final Function3 actions3;
        final Function2 navigationIcon3;
        final WindowInsets windowInsets3;
        final TopAppBarColors colors3;
        final TopAppBarScrollBehavior scrollBehavior2;
        WindowInsets windowInsets4;
        TopAppBarColors colors4;
        TopAppBarScrollBehavior scrollBehavior3;
        Function2 navigationIcon4;
        Function3 actions4;
        Modifier modifier4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(-330927229);
        ComposerKt.sourceInformation($composer3, "C(LargeTopAppBar)N(title,modifier,navigationIcon,actions,windowInsets,colors,scrollBehavior)696@34858L386:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function2 = title;
        } else if (($changed & 6) == 0) {
            function2 = title;
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = title;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            navigationIcon2 = navigationIcon;
        } else if (($changed & 384) == 0) {
            navigationIcon2 = navigationIcon;
            $dirty |= $composer3.changedInstance(navigationIcon2) ? 256 : 128;
        } else {
            navigationIcon2 = navigationIcon;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            actions2 = actions;
        } else if (($changed & 3072) == 0) {
            actions2 = actions;
            $dirty |= $composer3.changedInstance(actions2) ? 2048 : 1024;
        } else {
            actions2 = actions;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                int i6 = $composer3.changed(windowInsets2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty |= i6;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 131072 : 65536;
                $dirty |= i7;
            } else {
                colors2 = colors;
            }
            $dirty |= i7;
        } else {
            colors2 = colors;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if ((1572864 & $changed) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ($composer3.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "692@34716L12,693@34778L17");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if (i4 != 0) {
                    navigationIcon2 = ComposableSingletons$AppBarKt.INSTANCE.m2293getLambda$699106370$material3();
                }
                if (i5 != 0) {
                    actions2 = ComposableSingletons$AppBarKt.INSTANCE.getLambda$1702484764$material3();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    scrollBehavior3 = null;
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                    navigationIcon4 = navigationIcon2;
                    actions4 = actions2;
                    modifier4 = modifier5;
                    i2 = -330927229;
                } else {
                    windowInsets4 = windowInsets2;
                    colors4 = colors2;
                    scrollBehavior3 = topAppBarScrollBehavior;
                    navigationIcon4 = navigationIcon2;
                    actions4 = actions2;
                    modifier4 = modifier5;
                    i2 = -330927229;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                windowInsets4 = windowInsets2;
                colors4 = colors2;
                scrollBehavior3 = topAppBarScrollBehavior;
                i2 = -330927229;
                navigationIcon4 = navigationIcon2;
                actions4 = actions2;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.LargeTopAppBar (AppBar.kt:696)");
            }
            $composer2 = $composer3;
            m2086LargeTopAppBaroKE7A98(function2, modifier4, navigationIcon4, actions4, TopAppBarDefaults.INSTANCE.m3276getLargeAppBarCollapsedHeightD9Ej5fM(), TopAppBarDefaults.INSTANCE.m3277getLargeAppBarExpandedHeightD9Ej5fM(), windowInsets4, colors4, scrollBehavior3, $composer2, ($dirty & 14) | 221184 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 3670016) | (($dirty << 6) & 29360128) | (($dirty << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            navigationIcon3 = navigationIcon4;
            actions3 = actions4;
            windowInsets3 = windowInsets4;
            colors3 = colors4;
            scrollBehavior2 = scrollBehavior3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            actions3 = actions2;
            navigationIcon3 = navigationIcon2;
            windowInsets3 = windowInsets2;
            colors3 = colors2;
            scrollBehavior2 = topAppBarScrollBehavior;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.LargeTopAppBar$lambda$8(title, modifier3, navigationIcon3, actions3, windowInsets3, colors3, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: LargeTopAppBar-oKE7A98, reason: not valid java name */
    public static final void m2086LargeTopAppBaroKE7A98(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4, float collapsedHeight, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function2M2279getLambda$1230986050$material3;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> lambda$1921811868$material3;
        float collapsedHeight2;
        float expandedHeight2;
        Composer $composer2;
        final WindowInsets windowInsets2;
        final TopAppBarScrollBehavior scrollBehavior2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function7;
        final float collapsedHeight3;
        final float expandedHeight3;
        final TopAppBarColors colors2;
        WindowInsets windowInsets3;
        int $dirty;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior3;
        WindowInsets windowInsets4;
        TopAppBarColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function9;
        float collapsedHeight4;
        Modifier modifier4;
        int $dirty2;
        float expandedHeight4;
        float fM3276getLargeAppBarCollapsedHeightD9Ej5fM;
        float fM3277getLargeAppBarExpandedHeightD9Ej5fM;
        Composer $composer3 = $composer.startRestartGroup(-1406602045);
        ComposerKt.sourceInformation($composer3, "C(LargeTopAppBar)N(title,modifier,navigationIcon,actions,collapsedHeight:c#ui.unit.Dp,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)765@38799L5,766@38864L5,763@38705L1138:AppBar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function5 = function2;
        } else if (($changed & 6) == 0) {
            function5 = function2;
            $dirty3 |= $composer3.changedInstance(function5) ? 4 : 2;
        } else {
            function5 = function2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            function2M2279getLambda$1230986050$material3 = function3;
        } else if (($changed & 384) == 0) {
            function2M2279getLambda$1230986050$material3 = function3;
            $dirty3 |= $composer3.changedInstance(function2M2279getLambda$1230986050$material3) ? 256 : 128;
        } else {
            function2M2279getLambda$1230986050$material3 = function3;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            lambda$1921811868$material3 = function4;
        } else if (($changed & 3072) == 0) {
            lambda$1921811868$material3 = function4;
            $dirty3 |= $composer3.changedInstance(lambda$1921811868$material3) ? 2048 : 1024;
        } else {
            lambda$1921811868$material3 = function4;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
            collapsedHeight2 = collapsedHeight;
        } else if (($changed & 24576) == 0) {
            collapsedHeight2 = collapsedHeight;
            $dirty3 |= $composer3.changed(collapsedHeight2) ? 16384 : 8192;
        } else {
            collapsedHeight2 = collapsedHeight;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            expandedHeight2 = expandedHeight;
        } else if ((196608 & $changed) == 0) {
            expandedHeight2 = expandedHeight;
            $dirty3 |= $composer3.changed(expandedHeight2) ? 131072 : 65536;
        } else {
            expandedHeight2 = expandedHeight;
        }
        if (($changed & 1572864) == 0) {
            $dirty3 |= ((i & 64) == 0 && $composer3.changed(windowInsets)) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= ((i & 128) == 0 && $composer3.changed(colors)) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changed(scrollBehavior) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if (!$composer3.shouldExecute(($dirty3 & 38347923) != 38347922, $dirty4 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            windowInsets2 = windowInsets;
            scrollBehavior2 = scrollBehavior;
            modifier3 = modifier2;
            function6 = function2M2279getLambda$1230986050$material3;
            function7 = lambda$1921811868$material3;
            collapsedHeight3 = collapsedHeight2;
            expandedHeight3 = expandedHeight2;
            colors2 = colors;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "759@38563L12,760@38625L17");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 64) != 0 ? $dirty4 & (-3670017) : $dirty4;
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                Modifier modifier5 = modifier2;
                $dirty2 = $dirty5;
                collapsedHeight4 = collapsedHeight2;
                modifier4 = modifier5;
                windowInsets4 = windowInsets;
                colors4 = colors;
                scrollBehavior3 = scrollBehavior;
                function8 = function2M2279getLambda$1230986050$material3;
                function9 = lambda$1921811868$material3;
                expandedHeight4 = expandedHeight2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function2M2279getLambda$1230986050$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2279getLambda$1230986050$material3();
                }
                if (i4 != 0) {
                    lambda$1921811868$material3 = ComposableSingletons$AppBarKt.INSTANCE.getLambda$1921811868$material3();
                }
                if (i5 != 0) {
                    collapsedHeight2 = TopAppBarDefaults.INSTANCE.m3276getLargeAppBarCollapsedHeightD9Ej5fM();
                }
                if (i6 != 0) {
                    expandedHeight2 = TopAppBarDefaults.INSTANCE.m3277getLargeAppBarExpandedHeightD9Ej5fM();
                }
                if ((i & 64) == 0) {
                    windowInsets3 = windowInsets;
                    $dirty = $dirty4;
                } else {
                    windowInsets3 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty = $dirty4 & (-3670017);
                }
                if ((i & 128) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                    $dirty &= -29360129;
                }
                if (i7 == 0) {
                    scrollBehavior3 = scrollBehavior;
                    windowInsets4 = windowInsets3;
                    colors4 = colors3;
                    function8 = function2M2279getLambda$1230986050$material3;
                    function9 = lambda$1921811868$material3;
                    collapsedHeight4 = collapsedHeight2;
                    modifier4 = modifier2;
                    $dirty2 = $dirty;
                    expandedHeight4 = expandedHeight2;
                } else {
                    windowInsets4 = windowInsets3;
                    colors4 = colors3;
                    function8 = function2M2279getLambda$1230986050$material3;
                    scrollBehavior3 = null;
                    function9 = lambda$1921811868$material3;
                    collapsedHeight4 = collapsedHeight2;
                    modifier4 = modifier2;
                    $dirty2 = $dirty;
                    expandedHeight4 = expandedHeight2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1406602045, $dirty2, -1, "androidx.compose.material3.LargeTopAppBar (AppBar.kt:763)");
            }
            TextStyle value = TypographyKt.getValue(AppBarLargeTokens.INSTANCE.getTitleFont(), $composer3, 6);
            TextStyle value2 = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getTitleFont(), $composer3, 6);
            float f = LargeTitleBottomPadding;
            TextStyle textStyle = TextStyle.INSTANCE.getDefault();
            TextStyle textStyle2 = TextStyle.INSTANCE.getDefault();
            Alignment.Horizontal start = Alignment.INSTANCE.getStart();
            if (Dp.m7910equalsimpl0(collapsedHeight4, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(collapsedHeight4, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                fM3276getLargeAppBarCollapsedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3276getLargeAppBarCollapsedHeightD9Ej5fM();
            } else {
                fM3276getLargeAppBarCollapsedHeightD9Ej5fM = collapsedHeight4;
            }
            if (Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                fM3277getLargeAppBarExpandedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3277getLargeAppBarExpandedHeightD9Ej5fM();
            } else {
                fM3277getLargeAppBarExpandedHeightD9Ej5fM = expandedHeight4;
            }
            $composer2 = $composer3;
            m2094TwoRowsTopAppBarpJA5dT0(modifier4, function5, value, f, function2, value2, null, textStyle, null, textStyle2, start, function8, function9, fM3276getLargeAppBarCollapsedHeightD9Ej5fM, fM3277getLargeAppBarExpandedHeightD9Ej5fM, windowInsets4, colors4, scrollBehavior3, $composer2, (($dirty2 >> 3) & 14) | 920128512 | (($dirty2 << 3) & 112) | (($dirty2 << 12) & 57344), (($dirty2 >> 3) & 112) | 6 | (($dirty2 >> 3) & 896) | (($dirty2 >> 3) & 458752) | (($dirty2 >> 3) & 3670016) | (($dirty2 >> 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            collapsedHeight3 = collapsedHeight4;
            expandedHeight3 = expandedHeight4;
            modifier3 = modifier4;
            function6 = function8;
            windowInsets2 = windowInsets4;
            colors2 = colors4;
            scrollBehavior2 = scrollBehavior3;
            function7 = function9;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.LargeTopAppBar_oKE7A98$lambda$9(function2, modifier3, function6, function7, collapsedHeight3, expandedHeight3, windowInsets2, colors2, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: LargeFlexibleTopAppBar-eXZ4JBQ, reason: not valid java name */
    public static final void m2085LargeFlexibleTopAppBareXZ4JBQ(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function5, Alignment.Horizontal titleHorizontalAlignment, float collapsedHeight, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function6;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function2M2294getLambda$780193532$material3;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M2284getLambda$1846660506$material3;
        float collapsedHeight2;
        Composer $composer2;
        final WindowInsets windowInsets2;
        final TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        final float expandedHeight2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Alignment.Horizontal titleHorizontalAlignment2;
        final float expandedHeight3;
        final TopAppBarColors colors2;
        Alignment.Horizontal titleHorizontalAlignment3;
        float expandedHeight4;
        int $dirty2;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior3;
        Alignment.Horizontal titleHorizontalAlignment4;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function10;
        WindowInsets windowInsets4;
        Function2<? super Composer, ? super Integer, Unit> function11;
        TopAppBarColors colors4;
        Modifier modifier4;
        float fM3276getLargeAppBarCollapsedHeightD9Ej5fM;
        float fM3279getLargeFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM;
        Composer $composer3 = $composer.startRestartGroup(1104319839);
        ComposerKt.sourceInformation($composer3, "C(LargeFlexibleTopAppBar)N(title,modifier,subtitle,navigationIcon,actions,titleHorizontalAlignment,collapsedHeight:c#ui.unit.Dp,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)860@43856L5,861@43921L5,866@44141L5,868@44252L5,858@43754L1415:AppBar.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function6 = function2;
        } else if (($changed & 6) == 0) {
            function6 = function2;
            $dirty3 |= $composer3.changedInstance(function6) ? 4 : 2;
        } else {
            function6 = function2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty3 |= 384;
            function7 = function3;
        } else if (($changed & 384) == 0) {
            function7 = function3;
            $dirty3 |= $composer3.changedInstance(function7) ? 256 : 128;
        } else {
            function7 = function3;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty3 |= 3072;
            function2M2294getLambda$780193532$material3 = function4;
        } else if (($changed & 3072) == 0) {
            function2M2294getLambda$780193532$material3 = function4;
            $dirty3 |= $composer3.changedInstance(function2M2294getLambda$780193532$material3) ? 2048 : 1024;
        } else {
            function2M2294getLambda$780193532$material3 = function4;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty3 |= 24576;
            function3M2284getLambda$1846660506$material3 = function5;
        } else if (($changed & 24576) == 0) {
            function3M2284getLambda$1846660506$material3 = function5;
            $dirty3 |= $composer3.changedInstance(function3M2284getLambda$1846660506$material3) ? 16384 : 8192;
        } else {
            function3M2284getLambda$1846660506$material3 = function5;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changed(titleHorizontalAlignment) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty3 |= 1572864;
            collapsedHeight2 = collapsedHeight;
        } else if (($changed & 1572864) == 0) {
            collapsedHeight2 = collapsedHeight;
            $dirty3 |= $composer3.changed(collapsedHeight2) ? 1048576 : 524288;
        } else {
            collapsedHeight2 = collapsedHeight;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= ((i & 128) == 0 && $composer3.changed(expandedHeight)) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty3 |= ((i & 256) == 0 && $composer3.changed(windowInsets)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= ((i & 512) == 0 && $composer3.changed(colors)) ? 536870912 : 268435456;
        }
        int i8 = i & 1024;
        if (i8 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(scrollBehavior) ? 4 : 2;
        }
        int $dirty4 = $dirty3;
        if (!$composer3.shouldExecute((($dirty3 & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty4 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            windowInsets2 = windowInsets;
            scrollBehavior2 = scrollBehavior;
            $dirty = $dirty4;
            expandedHeight2 = collapsedHeight2;
            modifier3 = modifier2;
            function8 = function7;
            function9 = function2M2294getLambda$780193532$material3;
            titleHorizontalAlignment2 = titleHorizontalAlignment;
            expandedHeight3 = expandedHeight;
            colors2 = colors;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "854@43612L12,855@43674L17");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 128) != 0 ? $dirty4 & (-29360129) : $dirty4;
                if ((i & 256) != 0) {
                    $dirty5 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty5 &= -1879048193;
                }
                titleHorizontalAlignment4 = titleHorizontalAlignment;
                expandedHeight4 = expandedHeight;
                windowInsets4 = windowInsets;
                colors4 = colors;
                scrollBehavior3 = scrollBehavior;
                $dirty2 = $dirty5;
                function10 = function3M2284getLambda$1846660506$material3;
                modifier4 = modifier2;
                function11 = function2M2294getLambda$780193532$material3;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function7 = null;
                }
                if (i4 != 0) {
                    function2M2294getLambda$780193532$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2294getLambda$780193532$material3();
                }
                if (i5 != 0) {
                    function3M2284getLambda$1846660506$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2284getLambda$1846660506$material3();
                }
                if (i6 == 0) {
                    titleHorizontalAlignment3 = titleHorizontalAlignment;
                } else {
                    titleHorizontalAlignment3 = Alignment.INSTANCE.getStart();
                }
                if (i7 != 0) {
                    collapsedHeight2 = TopAppBarDefaults.INSTANCE.m3276getLargeAppBarCollapsedHeightD9Ej5fM();
                }
                if ((i & 128) == 0) {
                    expandedHeight4 = expandedHeight;
                    $dirty2 = $dirty4;
                } else {
                    if (function7 != null) {
                        expandedHeight4 = TopAppBarDefaults.INSTANCE.m3278getLargeFlexibleAppBarWithSubtitleExpandedHeightD9Ej5fM();
                    } else {
                        expandedHeight4 = TopAppBarDefaults.INSTANCE.m3279getLargeFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM();
                    }
                    $dirty2 = $dirty4 & (-29360129);
                }
                if ((i & 256) == 0) {
                    windowInsets3 = windowInsets;
                } else {
                    windowInsets3 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty2 &= -234881025;
                }
                if ((i & 512) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                    $dirty2 &= -1879048193;
                }
                if (i8 == 0) {
                    scrollBehavior3 = scrollBehavior;
                    titleHorizontalAlignment4 = titleHorizontalAlignment3;
                    function10 = function3M2284getLambda$1846660506$material3;
                    windowInsets4 = windowInsets3;
                    function11 = function2M2294getLambda$780193532$material3;
                    colors4 = colors3;
                    modifier4 = modifier2;
                } else {
                    titleHorizontalAlignment4 = titleHorizontalAlignment3;
                    function10 = function3M2284getLambda$1846660506$material3;
                    windowInsets4 = windowInsets3;
                    function11 = function2M2294getLambda$780193532$material3;
                    colors4 = colors3;
                    scrollBehavior3 = null;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1104319839, $dirty2, $dirty1, "androidx.compose.material3.LargeFlexibleTopAppBar (AppBar.kt:858)");
            }
            TextStyle value = TypographyKt.getValue(AppBarLargeFlexibleTokens.INSTANCE.getTitleFont(), $composer3, 6);
            TextStyle value2 = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getTitleFont(), $composer3, 6);
            float f = LargeTitleBottomPadding;
            Function2<? super Composer, ? super Integer, Unit> function2M2292getLambda$661145402$material3 = function7 == null ? ComposableSingletons$AppBarKt.INSTANCE.m2292getLambda$661145402$material3() : function7;
            TextStyle value3 = TypographyKt.getValue(AppBarLargeFlexibleTokens.INSTANCE.getSubtitleFont(), $composer3, 6);
            Function2<? super Composer, ? super Integer, Unit> function2M2277getLambda$1113422563$material3 = function7 == null ? ComposableSingletons$AppBarKt.INSTANCE.m2277getLambda$1113422563$material3() : function7;
            TextStyle value4 = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getSubtitleFont(), $composer3, 6);
            if (Dp.m7910equalsimpl0(collapsedHeight2, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(collapsedHeight2, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                fM3276getLargeAppBarCollapsedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3276getLargeAppBarCollapsedHeightD9Ej5fM();
            } else {
                fM3276getLargeAppBarCollapsedHeightD9Ej5fM = collapsedHeight2;
            }
            if (Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(expandedHeight4, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                if (function7 != null) {
                    fM3279getLargeFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3278getLargeFlexibleAppBarWithSubtitleExpandedHeightD9Ej5fM();
                } else {
                    fM3279getLargeFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3279getLargeFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM();
                }
            } else {
                fM3279getLargeFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM = expandedHeight4;
            }
            $composer2 = $composer3;
            m2094TwoRowsTopAppBarpJA5dT0(modifier4, function6, value, f, function2, value2, function2M2292getLambda$661145402$material3, value3, function2M2277getLambda$1113422563$material3, value4, titleHorizontalAlignment4, function11, function10, fM3276getLargeAppBarCollapsedHeightD9Ej5fM, fM3279getLargeFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM, windowInsets4, colors4, scrollBehavior3, $composer2, (($dirty2 >> 3) & 14) | 3072 | (($dirty2 << 3) & 112) | (($dirty2 << 12) & 57344), (($dirty2 >> 15) & 14) | (($dirty2 >> 6) & 112) | (($dirty2 >> 6) & 896) | (($dirty2 >> 9) & 458752) | (($dirty2 >> 9) & 3670016) | (($dirty1 << 21) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            expandedHeight3 = expandedHeight4;
            function8 = function7;
            modifier3 = modifier4;
            function9 = function11;
            function3M2284getLambda$1846660506$material3 = function10;
            colors2 = colors4;
            scrollBehavior2 = scrollBehavior3;
            expandedHeight2 = collapsedHeight2;
            $dirty = $dirty2;
            titleHorizontalAlignment2 = titleHorizontalAlignment4;
            windowInsets2 = windowInsets4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.LargeFlexibleTopAppBar_eXZ4JBQ$lambda$10(function2, modifier3, function8, function9, function3M2284getLambda$1846660506$material3, titleHorizontalAlignment2, expandedHeight2, expandedHeight3, windowInsets2, colors2, scrollBehavior2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TwoRowsTopAppBar-eXZ4JBQ, reason: not valid java name */
    public static final void m2093TwoRowsTopAppBareXZ4JBQ(final Function3<? super Boolean, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, Function3<? super Boolean, ? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function2, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function5, Alignment.Horizontal titleHorizontalAlignment, float collapsedHeight, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        final Function3<? super Boolean, ? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> lambda$1767216677$material3;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3M2289getLambda$470693625$material3;
        final float collapsedHeight2;
        Composer $composer2;
        final WindowInsets windowInsets2;
        final TopAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function7;
        final Modifier modifier3;
        final Function3<? super Boolean, ? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Alignment.Horizontal titleHorizontalAlignment2;
        final float expandedHeight2;
        final TopAppBarColors colors2;
        Alignment.Horizontal titleHorizontalAlignment3;
        float expandedHeight3;
        WindowInsets windowInsets3;
        int $dirty2;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior3;
        Alignment.Horizontal titleHorizontalAlignment4;
        TopAppBarColors colors4;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function10;
        WindowInsets windowInsets4;
        Function2<? super Composer, ? super Integer, Unit> function11;
        int $dirty3;
        Modifier modifier4;
        float fM3280getMediumAppBarCollapsedHeightD9Ej5fM;
        float fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM;
        Composer $composer3 = $composer.startRestartGroup(1672878784);
        ComposerKt.sourceInformation($composer3, "C(TwoRowsTopAppBar)N(title,modifier,subtitle,navigationIcon,actions,titleHorizontalAlignment,collapsedHeight:c#ui.unit.Dp,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)965@49886L5,966@49951L5,971@50176L5,973@50300L5,964@49807L15,968@50014L16,970@50080L26,972@50207L27,963@49773L1578:AppBar.kt#uh7d8r");
        int $dirty4 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty4 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            function6 = function4;
        } else if (($changed & 384) == 0) {
            function6 = function4;
            $dirty4 |= $composer3.changedInstance(function6) ? 256 : 128;
        } else {
            function6 = function4;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            lambda$1767216677$material3 = function2;
        } else if (($changed & 3072) == 0) {
            lambda$1767216677$material3 = function2;
            $dirty4 |= $composer3.changedInstance(lambda$1767216677$material3) ? 2048 : 1024;
        } else {
            lambda$1767216677$material3 = function2;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty4 |= 24576;
            function3M2289getLambda$470693625$material3 = function5;
        } else if (($changed & 24576) == 0) {
            function3M2289getLambda$470693625$material3 = function5;
            $dirty4 |= $composer3.changedInstance(function3M2289getLambda$470693625$material3) ? 16384 : 8192;
        } else {
            function3M2289getLambda$470693625$material3 = function5;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty4 |= $composer3.changed(titleHorizontalAlignment) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty4 |= 1572864;
            collapsedHeight2 = collapsedHeight;
        } else if (($changed & 1572864) == 0) {
            collapsedHeight2 = collapsedHeight;
            $dirty4 |= $composer3.changed(collapsedHeight2) ? 1048576 : 524288;
        } else {
            collapsedHeight2 = collapsedHeight;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty4 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty4 |= $composer3.changed(expandedHeight) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty4 |= ((i & 256) == 0 && $composer3.changed(windowInsets)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty4 |= ((i & 512) == 0 && $composer3.changed(colors)) ? 536870912 : 268435456;
        }
        int i9 = i & 1024;
        if (i9 != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(scrollBehavior) ? 4 : 2;
        }
        int $dirty5 = $dirty4;
        if ($composer3.shouldExecute((($dirty4 & 306783379) == 306783378 && ($dirty1 & 3) == 2) ? false : true, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "959@49631L12,960@49693L17");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                $dirty3 = (i & 256) != 0 ? $dirty5 & (-234881025) : $dirty5;
                if ((i & 512) != 0) {
                    $dirty3 &= -1879048193;
                }
                titleHorizontalAlignment4 = titleHorizontalAlignment;
                expandedHeight3 = expandedHeight;
                windowInsets4 = windowInsets;
                colors4 = colors;
                scrollBehavior3 = scrollBehavior;
                function10 = function3M2289getLambda$470693625$material3;
                modifier4 = modifier2;
                function11 = lambda$1767216677$material3;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function6 = null;
                }
                if (i4 != 0) {
                    lambda$1767216677$material3 = ComposableSingletons$AppBarKt.INSTANCE.getLambda$1767216677$material3();
                }
                if (i5 != 0) {
                    function3M2289getLambda$470693625$material3 = ComposableSingletons$AppBarKt.INSTANCE.m2289getLambda$470693625$material3();
                }
                if (i6 == 0) {
                    titleHorizontalAlignment3 = titleHorizontalAlignment;
                } else {
                    titleHorizontalAlignment3 = Alignment.INSTANCE.getStart();
                }
                if (i7 != 0) {
                    collapsedHeight2 = Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM();
                }
                if (i8 == 0) {
                    expandedHeight3 = expandedHeight;
                } else {
                    expandedHeight3 = Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM();
                }
                if ((i & 256) == 0) {
                    windowInsets3 = windowInsets;
                    $dirty2 = $dirty5;
                } else {
                    windowInsets3 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty2 = $dirty5 & (-234881025);
                }
                if ((i & 512) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                    $dirty2 &= -1879048193;
                }
                if (i9 == 0) {
                    scrollBehavior3 = scrollBehavior;
                    titleHorizontalAlignment4 = titleHorizontalAlignment3;
                    colors4 = colors3;
                    function10 = function3M2289getLambda$470693625$material3;
                    windowInsets4 = windowInsets3;
                    function11 = lambda$1767216677$material3;
                    $dirty3 = $dirty2;
                    modifier4 = modifier2;
                } else {
                    titleHorizontalAlignment4 = titleHorizontalAlignment3;
                    colors4 = colors3;
                    function10 = function3M2289getLambda$470693625$material3;
                    windowInsets4 = windowInsets3;
                    function11 = lambda$1767216677$material3;
                    $dirty3 = $dirty2;
                    scrollBehavior3 = null;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1672878784, $dirty3, $dirty1, "androidx.compose.material3.TwoRowsTopAppBar (AppBar.kt:962)");
            }
            TextStyle value = TypographyKt.getValue(AppBarMediumFlexibleTokens.INSTANCE.getTitleFont(), $composer3, 6);
            TextStyle value2 = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getTitleFont(), $composer3, 6);
            float fM7905constructorimpl = Dp.m7905constructorimpl(0);
            TextStyle value3 = TypographyKt.getValue(AppBarMediumFlexibleTokens.INSTANCE.getSubtitleFont(), $composer3, 6);
            TextStyle value4 = TypographyKt.getValue(AppBarSmallTokens.INSTANCE.getSubtitleFont(), $composer3, 6);
            if (Dp.m7910equalsimpl0(collapsedHeight2, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(collapsedHeight2, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                fM3280getMediumAppBarCollapsedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3280getMediumAppBarCollapsedHeightD9Ej5fM();
            } else {
                fM3280getMediumAppBarCollapsedHeightD9Ej5fM = collapsedHeight2;
            }
            if (Dp.m7910equalsimpl0(expandedHeight3, Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM()) || Dp.m7910equalsimpl0(expandedHeight3, Dp.INSTANCE.m7924getInfinityD9Ej5fM())) {
                if (function6 != null) {
                    fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3282getMediumFlexibleAppBarWithSubtitleExpandedHeightD9Ej5fM();
                } else {
                    fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM = TopAppBarDefaults.INSTANCE.m3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM();
                }
            } else {
                fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM = expandedHeight3;
            }
            $composer2 = $composer3;
            m2094TwoRowsTopAppBarpJA5dT0(modifier4, ComposableLambdaKt.rememberComposableLambda(-1674885936, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C964@49809L11:AppBar.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1674885936, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:964)");
                    }
                    function3.invoke(true, $composer4, 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), value, fM7905constructorimpl, ComposableLambdaKt.rememberComposableLambda(1295408045, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C968@50016L12:AppBar.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1295408045, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:968)");
                    }
                    function3.invoke(false, $composer4, 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), value2, ComposableLambdaKt.rememberComposableLambda(1843948267, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C:AppBar.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1843948267, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:970)");
                    }
                    Function3<Boolean, Composer, Integer, Unit> function12 = function6;
                    if (function12 == null) {
                        $composer4.startReplaceGroup(431118634);
                    } else {
                        $composer4.startReplaceGroup(-1787208265);
                        ComposerKt.sourceInformation($composer4, "970@50092L12");
                        function12.invoke(true, $composer4, 6);
                    }
                    $composer4.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), value3, ComposableLambdaKt.rememberComposableLambda(-1902478807, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$4
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C:AppBar.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1902478807, $changed2, -1, "androidx.compose.material3.TwoRowsTopAppBar.<anonymous> (AppBar.kt:972)");
                    }
                    Function3<Boolean, Composer, Integer, Unit> function12 = function6;
                    if (function12 == null) {
                        $composer4.startReplaceGroup(-357689013);
                    } else {
                        $composer4.startReplaceGroup(1373934966);
                        ComposerKt.sourceInformation($composer4, "972@50219L13");
                        function12.invoke(false, $composer4, 6);
                    }
                    $composer4.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), value4, titleHorizontalAlignment4, function11, function10, fM3280getMediumAppBarCollapsedHeightD9Ej5fM, fM3283getMediumFlexibleAppBarWithoutSubtitleExpandedHeightD9Ej5fM, windowInsets4, colors4, scrollBehavior3, $composer2, (($dirty3 >> 3) & 14) | 102263856, (($dirty3 >> 15) & 14) | (($dirty3 >> 6) & 112) | (($dirty3 >> 6) & 896) | (($dirty3 >> 9) & 458752) | (($dirty3 >> 9) & 3670016) | (($dirty1 << 21) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            expandedHeight2 = expandedHeight3;
            function8 = function6;
            modifier3 = modifier4;
            titleHorizontalAlignment2 = titleHorizontalAlignment4;
            function7 = function10;
            windowInsets2 = windowInsets4;
            colors2 = colors4;
            scrollBehavior2 = scrollBehavior3;
            $dirty = $dirty3;
            function9 = function11;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            windowInsets2 = windowInsets;
            scrollBehavior2 = scrollBehavior;
            $dirty = $dirty5;
            function7 = function3M2289getLambda$470693625$material3;
            modifier3 = modifier2;
            function8 = function6;
            function9 = lambda$1767216677$material3;
            titleHorizontalAlignment2 = titleHorizontalAlignment;
            expandedHeight2 = expandedHeight;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TwoRowsTopAppBar_eXZ4JBQ$lambda$11(function3, modifier3, function8, function9, function7, titleHorizontalAlignment2, collapsedHeight2, expandedHeight2, windowInsets2, colors2, scrollBehavior2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: BottomAppBar-Snr_uVM, reason: not valid java name */
    public static final void m2079BottomAppBarSnr_uVM(final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, long containerColor, long contentColor, float tonalElevation, PaddingValues contentPadding, WindowInsets windowInsets, Composer $composer, final int $changed, final int i) {
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        long containerColor2;
        long contentColor2;
        float tonalElevation2;
        Composer $composer2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final long containerColor3;
        final long contentColor3;
        final float tonalElevation3;
        final PaddingValues contentPadding2;
        final WindowInsets windowInsets2;
        PaddingValues contentPadding3;
        WindowInsets windowInsets3;
        PaddingValues contentPadding4;
        Modifier modifier4;
        long containerColor4;
        long contentColor4;
        float tonalElevation4;
        int i2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Composer $composer3 = $composer.startRestartGroup(-2144093983);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBar)N(actions,modifier,floatingActionButton,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,contentPadding,windowInsets)1044@53613L356:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function4 = function3;
        } else if (($changed & 6) == 0) {
            function4 = function3;
            $dirty |= $composer3.changedInstance(function4) ? 4 : 2;
        } else {
            function4 = function3;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            function5 = function2;
        } else if (($changed & 384) == 0) {
            function5 = function2;
            $dirty |= $composer3.changedInstance(function5) ? 256 : 128;
        } else {
            function5 = function2;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                int i5 = $composer3.changed(containerColor2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                containerColor2 = containerColor;
            }
            $dirty |= i5;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i6 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                contentColor2 = contentColor;
            }
            $dirty |= i6;
        } else {
            contentColor2 = contentColor;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            tonalElevation2 = tonalElevation;
        } else if ((196608 & $changed) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty |= $composer3.changed(tonalElevation2) ? 131072 : 65536;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(contentPadding) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= ((i & 128) == 0 && $composer3.changed(windowInsets)) ? 8388608 : 4194304;
        }
        if (!$composer3.shouldExecute(($dirty & 4793491) != 4793490, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function6 = function5;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            tonalElevation3 = tonalElevation2;
            contentPadding2 = contentPadding;
            windowInsets2 = windowInsets;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1038@53323L14,1039@53365L31,1042@53591L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                contentPadding4 = contentPadding;
                windowInsets3 = windowInsets;
                modifier4 = modifier2;
                containerColor4 = containerColor2;
                contentColor4 = contentColor2;
                tonalElevation4 = tonalElevation2;
                i2 = -2144093983;
                function7 = function5;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    function5 = null;
                }
                if ((i & 8) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    contentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(containerColor2, $composer3, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i7 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m2105getContainerElevationD9Ej5fM();
                }
                if (i8 == 0) {
                    contentPadding3 = contentPadding;
                } else {
                    contentPadding3 = BottomAppBarDefaults.INSTANCE.getContentPadding();
                }
                if ((i & 128) == 0) {
                    windowInsets3 = windowInsets;
                    contentPadding4 = contentPadding3;
                    modifier4 = modifier2;
                    containerColor4 = containerColor2;
                    contentColor4 = contentColor2;
                    tonalElevation4 = tonalElevation2;
                    i2 = -2144093983;
                    function7 = function5;
                } else {
                    $dirty &= -29360129;
                    contentPadding4 = contentPadding3;
                    modifier4 = modifier2;
                    windowInsets3 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    containerColor4 = containerColor2;
                    contentColor4 = contentColor2;
                    tonalElevation4 = tonalElevation2;
                    i2 = -2144093983;
                    function7 = function5;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:1044)");
            }
            $composer2 = $composer3;
            m2081BottomAppBarqhFBPw4(function4, modifier4, function7, containerColor4, contentColor4, tonalElevation4, contentPadding4, windowInsets3, null, $composer2, ($dirty & 14) | 100663296 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            function6 = function7;
            containerColor3 = containerColor4;
            contentColor3 = contentColor4;
            tonalElevation3 = tonalElevation4;
            contentPadding2 = contentPadding4;
            windowInsets2 = windowInsets3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.BottomAppBar_Snr_uVM$lambda$12(function3, modifier3, function6, containerColor3, contentColor3, tonalElevation3, contentPadding2, windowInsets2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: BottomAppBar-qhFBPw4, reason: not valid java name */
    public static final void m2081BottomAppBarqhFBPw4(final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, long containerColor, long contentColor, float tonalElevation, PaddingValues contentPadding, WindowInsets windowInsets, BottomAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function4;
        long j;
        long j2;
        float f;
        Composer $composer2;
        final Modifier modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final long containerColor2;
        final long contentColor2;
        final float tonalElevation2;
        final PaddingValues contentPadding2;
        final WindowInsets windowInsets2;
        final BottomAppBarScrollBehavior scrollBehavior2;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        long containerColor3;
        long contentColor3;
        WindowInsets windowInsets3;
        BottomAppBarScrollBehavior scrollBehavior3;
        WindowInsets windowInsets4;
        long containerColor4;
        float tonalElevation3;
        PaddingValues contentPadding3;
        int i2;
        Modifier modifier3;
        long contentColor4;
        Composer $composer3 = $composer.startRestartGroup(272234465);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBar)N(actions,modifier,floatingActionButton,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,contentPadding,windowInsets,scrollBehavior)1118@57288L540,1110@57001L827:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 4 : 2;
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
            function4 = function2;
        } else if (($changed & 384) == 0) {
            function4 = function2;
            $dirty |= $composer3.changedInstance(function4) ? 256 : 128;
        } else {
            function4 = function2;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = containerColor;
                int i5 = $composer3.changed(j) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                j = containerColor;
            }
            $dirty |= i5;
        } else {
            j = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                j2 = contentColor;
                int i6 = $composer3.changed(j2) ? 16384 : 8192;
                $dirty |= i6;
            } else {
                j2 = contentColor;
            }
            $dirty |= i6;
        } else {
            j2 = contentColor;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = tonalElevation;
        } else if ((196608 & $changed) == 0) {
            f = tonalElevation;
            $dirty |= $composer3.changed(f) ? 131072 : 65536;
        } else {
            f = tonalElevation;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(contentPadding) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= ((i & 128) == 0 && $composer3.changed(windowInsets)) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(scrollBehavior) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer3.shouldExecute(($dirty & 38347923) != 38347922, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1103@56655L14,1104@56697L31,1107@56923L12");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier;
                function6 = i4 != 0 ? null : function4;
                if ((i & 8) != 0) {
                    containerColor3 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty &= -7169;
                } else {
                    containerColor3 = j;
                }
                if ((i & 16) != 0) {
                    contentColor3 = ColorSchemeKt.m2263contentColorForek8zF_U(containerColor3, $composer3, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                } else {
                    contentColor3 = j2;
                }
                float tonalElevation4 = i7 != 0 ? BottomAppBarDefaults.INSTANCE.m2105getContainerElevationD9Ej5fM() : f;
                PaddingValues contentPadding4 = i8 != 0 ? BottomAppBarDefaults.INSTANCE.getContentPadding() : contentPadding;
                if ((i & 128) != 0) {
                    windowInsets3 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty &= -29360129;
                } else {
                    windowInsets3 = windowInsets;
                }
                if (i9 != 0) {
                    windowInsets4 = windowInsets3;
                    tonalElevation3 = tonalElevation4;
                    contentPadding3 = contentPadding4;
                    scrollBehavior3 = null;
                    i2 = 272234465;
                    modifier3 = modifier4;
                    containerColor4 = containerColor3;
                    contentColor4 = contentColor3;
                } else {
                    scrollBehavior3 = scrollBehavior;
                    windowInsets4 = windowInsets3;
                    containerColor4 = containerColor3;
                    tonalElevation3 = tonalElevation4;
                    contentPadding3 = contentPadding4;
                    i2 = 272234465;
                    modifier3 = modifier4;
                    contentColor4 = contentColor3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                modifier3 = modifier;
                contentPadding3 = contentPadding;
                windowInsets4 = windowInsets;
                scrollBehavior3 = scrollBehavior;
                function6 = function4;
                tonalElevation3 = f;
                i2 = 272234465;
                containerColor4 = j;
                contentColor4 = j2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:1110)");
            }
            $composer2 = $composer3;
            m2080BottomAppBare3WI5M(modifier3, containerColor4, contentColor4, tonalElevation3, contentPadding3, windowInsets4, scrollBehavior3, ComposableLambdaKt.rememberComposableLambda(-1943077286, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$2
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                    invoke(rowScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:34:0x0165  */
                /* JADX WARN: Code duplicated, block: B:36:0x01df  */
                /* JADX WARN: Code duplicated, block: B:39:0x01eb  */
                /* JADX WARN: Code duplicated, block: B:40:0x01f1  */
                /* JADX WARN: Code duplicated, block: B:49:0x02ac  */
                /* JADX WARN: Code duplicated, block: B:52:0x02bb  */
                /* JADX WARN: Code duplicated, block: B:55:? A[RETURN, SYNTHETIC] */
                public final void invoke(RowScope $this$BottomAppBar, Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    int $changed$iv;
                    int compositeKeyHash$iv$iv;
                    Function0<ComposeUiNode> constructor;
                    Function0<ComposeUiNode> function1;
                    Composer $this$Layout_u24lambda_u240$iv$iv;
                    ComposerKt.sourceInformation($composer4, "C1119@57298L204:AppBar.kt#uh7d8r");
                    int $dirty2 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty2 |= $composer4.changed($this$BottomAppBar) ? 4 : 2;
                    }
                    if ($composer4.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1943077286, $dirty2, -1, "androidx.compose.material3.BottomAppBar.<anonymous> (AppBar.kt:1119)");
                        }
                        Modifier modifier$iv = RowScope.weight$default($this$BottomAppBar, Modifier.INSTANCE, 1.0f, false, 2, null);
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                        Function3<RowScope, Composer, Integer, Unit> function7 = function3;
                        ComposerKt.sourceInformationMarkerStart($composer4, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer4, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                        int $changed$iv$iv = (432 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function0 = constructor2;
                            $composer4.createNode(function0);
                        } else {
                            function0 = constructor2;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer4);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                            $changed$iv = 432;
                            if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                            }
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i10 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                            function7.invoke(RowScopeInstance.INSTANCE, $composer4, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            if (function6 != null) {
                                $composer4.startReplaceGroup(1354051795);
                                ComposerKt.sourceInformation($composer4, "1126@57559L253");
                                Modifier modifier$iv2 = PaddingKt.m823paddingqDBjuR0$default(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, AppBarKt.FABVerticalPadding, AppBarKt.FABHorizontalPadding, 0.0f, 9, null);
                                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                Function2<Composer, Integer, Unit> function8 = function6;
                                ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                int $changed$iv$iv2 = (54 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                                CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
                                constructor = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                if (!($composer4.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer4.startReusableNode();
                                if ($composer4.getInserting()) {
                                    function1 = constructor;
                                    $composer4.createNode(function1);
                                } else {
                                    function1 = constructor;
                                    $composer4.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                                }
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                                int i11 = ($changed$iv$iv$iv2 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                int i12 = ((54 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer4, 1457541364, "C1131@57776L22:AppBar.kt#uh7d8r");
                                function8.invoke($composer4, 0);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                $composer4.endReplaceGroup();
                            } else {
                                $composer4.startReplaceGroup(1354317992);
                                $composer4.endReplaceGroup();
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $changed$iv = 432;
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i13 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                        function7.invoke(RowScopeInstance.INSTANCE, $composer4, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        if (function6 != null) {
                            $composer4.startReplaceGroup(1354051795);
                            ComposerKt.sourceInformation($composer4, "1126@57559L253");
                            Modifier modifier$iv3 = PaddingKt.m823paddingqDBjuR0$default(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, AppBarKt.FABVerticalPadding, AppBarKt.FABHorizontalPadding, 0.0f, 9, null);
                            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                            Function2<Composer, Integer, Unit> function9 = function6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                            int $changed$iv$iv3 = (54 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                            CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer4, modifier$iv3);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer4.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer4.startReusableNode();
                            if ($composer4.getInserting()) {
                                function1 = constructor;
                                $composer4.createNode(function1);
                            } else {
                                function1 = constructor;
                                $composer4.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                            int i14 = ($changed$iv$iv$iv3 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                            int i15 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer4, 1457541364, "C1131@57776L22:AppBar.kt#uh7d8r");
                            function9.invoke($composer4, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer4);
                            $composer4.endReplaceGroup();
                        } else {
                            $composer4.startReplaceGroup(1354317992);
                            $composer4.endReplaceGroup();
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | 12582912 | (($dirty >> 6) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty >> 6) & 57344) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            containerColor2 = containerColor4;
            tonalElevation2 = tonalElevation3;
            contentPadding2 = contentPadding3;
            windowInsets2 = windowInsets4;
            scrollBehavior2 = scrollBehavior3;
            function5 = function6;
            contentColor2 = contentColor4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            function5 = function4;
            containerColor2 = j;
            contentColor2 = j2;
            tonalElevation2 = f;
            contentPadding2 = contentPadding;
            windowInsets2 = windowInsets;
            scrollBehavior2 = scrollBehavior;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.BottomAppBar_qhFBPw4$lambda$13(function3, modifier2, function5, containerColor2, contentColor2, tonalElevation2, contentPadding2, windowInsets2, scrollBehavior2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: BottomAppBar-1oL4kX8, reason: not valid java name */
    public static final void m2078BottomAppBar1oL4kX8(Modifier modifier, long containerColor, long contentColor, float tonalElevation, PaddingValues contentPadding, WindowInsets windowInsets, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long containerColor2;
        long contentColor2;
        float tonalElevation2;
        PaddingValues contentPadding2;
        WindowInsets windowInsets2;
        Composer $composer2;
        final Modifier modifier3;
        final long containerColor3;
        final long contentColor3;
        final float tonalElevation3;
        final PaddingValues contentPadding3;
        final WindowInsets windowInsets3;
        int $dirty;
        int $dirty2;
        PaddingValues contentPadding4;
        WindowInsets windowInsets4;
        int i2;
        long containerColor4;
        long contentColor4;
        float tonalElevation4;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(107726720);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBar)N(modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,contentPadding,windowInsets,content)1173@59794L303:AppBar.kt#uh7d8r");
        int $dirty3 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty3 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer3.changed(containerColor2) ? 32 : 16;
                $dirty3 |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty3 |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer3.changed(contentColor2) ? 256 : 128;
                $dirty3 |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty3 |= i5;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty3 |= 3072;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 3072) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty3 |= $composer3.changed(tonalElevation2) ? 2048 : 1024;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty3 |= 24576;
            contentPadding2 = contentPadding;
        } else if (($changed & 24576) == 0) {
            contentPadding2 = contentPadding;
            $dirty3 |= $composer3.changed(contentPadding2) ? 16384 : 8192;
        } else {
            contentPadding2 = contentPadding;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i8 = $composer3.changed(windowInsets2) ? 131072 : 65536;
                $dirty3 |= i8;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty3 |= i8;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty3 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        int $dirty4 = $dirty3;
        if (!$composer3.shouldExecute(($dirty3 & 599187) != 599186, $dirty4 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            tonalElevation3 = tonalElevation2;
            contentPadding3 = contentPadding2;
            windowInsets3 = windowInsets2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1166@59458L14,1167@59500L31,1170@59726L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 2) != 0 ? $dirty4 & (-113) : $dirty4;
                if ((i & 4) != 0) {
                    $dirty5 &= -897;
                }
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                $dirty2 = $dirty5;
                contentPadding4 = contentPadding2;
                windowInsets4 = windowInsets2;
                i2 = 107726720;
                containerColor4 = containerColor2;
                contentColor4 = contentColor2;
                tonalElevation4 = tonalElevation2;
                modifier4 = modifier2;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) == 0) {
                    $dirty = $dirty4;
                } else {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty = $dirty4 & (-113);
                }
                if ((i & 4) != 0) {
                    contentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(containerColor2, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                }
                if (i6 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m2105getContainerElevationD9Ej5fM();
                }
                if (i7 != 0) {
                    contentPadding2 = BottomAppBarDefaults.INSTANCE.getContentPadding();
                }
                if ((i & 32) == 0) {
                    $dirty2 = $dirty;
                    contentPadding4 = contentPadding2;
                    windowInsets4 = windowInsets2;
                    i2 = 107726720;
                    containerColor4 = containerColor2;
                    contentColor4 = contentColor2;
                    tonalElevation4 = tonalElevation2;
                    modifier4 = modifier2;
                } else {
                    windowInsets4 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    contentColor4 = contentColor2;
                    contentPadding4 = contentPadding2;
                    i2 = 107726720;
                    $dirty2 = $dirty & (-458753);
                    containerColor4 = containerColor2;
                    tonalElevation4 = tonalElevation2;
                    modifier4 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty2, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:1173)");
            }
            $composer2 = $composer3;
            m2080BottomAppBare3WI5M(modifier4, containerColor4, contentColor4, tonalElevation4, contentPadding4, windowInsets4, null, function3, $composer2, ($dirty2 & 14) | 1572864 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (($dirty2 << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            containerColor3 = containerColor4;
            modifier3 = modifier4;
            contentColor3 = contentColor4;
            tonalElevation3 = tonalElevation4;
            contentPadding3 = contentPadding4;
            windowInsets3 = windowInsets4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.BottomAppBar_1oL4kX8$lambda$14(modifier3, containerColor3, contentColor3, tonalElevation3, contentPadding3, windowInsets3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: BottomAppBar-e-3WI5M, reason: not valid java name */
    public static final void m2080BottomAppBare3WI5M(Modifier modifier, long containerColor, long contentColor, float tonalElevation, PaddingValues contentPadding, WindowInsets windowInsets, BottomAppBarScrollBehavior scrollBehavior, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long containerColor2;
        long contentColor2;
        float tonalElevation2;
        PaddingValues contentPadding2;
        WindowInsets windowInsets2;
        Composer $composer2;
        final BottomAppBarScrollBehavior scrollBehavior2;
        final Modifier modifier3;
        final long containerColor3;
        final long contentColor3;
        final float tonalElevation3;
        final PaddingValues contentPadding3;
        final WindowInsets windowInsets3;
        int $dirty;
        BottomAppBarScrollBehavior scrollBehavior3;
        int $dirty2;
        long containerColor4;
        long contentColor4;
        float tonalElevation4;
        PaddingValues contentPadding4;
        WindowInsets windowInsets4;
        int $dirty3;
        Modifier modifier4;
        Composer $composer3 = $composer.startRestartGroup(1562683362);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBar)N(modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,contentPadding,windowInsets,scrollBehavior,content)1227@62600L432:AppBar.kt#uh7d8r");
        int $dirty4 = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty4 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                containerColor2 = containerColor;
                int i3 = $composer3.changed(containerColor2) ? 32 : 16;
                $dirty4 |= i3;
            } else {
                containerColor2 = containerColor;
            }
            $dirty4 |= i3;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                int i4 = $composer3.changed(contentColor2) ? 256 : 128;
                $dirty4 |= i4;
            } else {
                contentColor2 = contentColor;
            }
            $dirty4 |= i4;
        } else {
            contentColor2 = contentColor;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty4 |= 3072;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 3072) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty4 |= $composer3.changed(tonalElevation2) ? 2048 : 1024;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty4 |= 24576;
            contentPadding2 = contentPadding;
        } else if (($changed & 24576) == 0) {
            contentPadding2 = contentPadding;
            $dirty4 |= $composer3.changed(contentPadding2) ? 16384 : 8192;
        } else {
            contentPadding2 = contentPadding;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                int i7 = $composer3.changed(windowInsets2) ? 131072 : 65536;
                $dirty4 |= i7;
            } else {
                windowInsets2 = windowInsets;
            }
            $dirty4 |= i7;
        } else {
            windowInsets2 = windowInsets;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty4 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty4 |= $composer3.changed(scrollBehavior) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty4 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        int $dirty5 = $dirty4;
        if (!$composer3.shouldExecute(($dirty4 & 4793491) != 4793490, $dirty5 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            scrollBehavior2 = scrollBehavior;
            modifier3 = modifier2;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            tonalElevation3 = tonalElevation2;
            contentPadding3 = contentPadding2;
            windowInsets3 = windowInsets2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1219@62208L14,1220@62250L31,1223@62476L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 2) != 0 ? $dirty5 & (-113) : $dirty5;
                if ((i & 4) != 0) {
                    $dirty6 &= -897;
                }
                if ((i & 32) != 0) {
                    $dirty6 &= -458753;
                }
                scrollBehavior3 = scrollBehavior;
                $dirty2 = $dirty6;
                containerColor4 = containerColor2;
                contentColor4 = contentColor2;
                tonalElevation4 = tonalElevation2;
                contentPadding4 = contentPadding2;
                windowInsets4 = windowInsets2;
                $dirty3 = 1562683362;
                modifier4 = modifier2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) == 0) {
                    $dirty = $dirty5;
                } else {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty = $dirty5 & (-113);
                }
                if ((i & 4) != 0) {
                    contentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(containerColor2, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                }
                if (i5 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m2105getContainerElevationD9Ej5fM();
                }
                if (i6 != 0) {
                    contentPadding2 = BottomAppBarDefaults.INSTANCE.getContentPadding();
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    windowInsets2 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if (i8 == 0) {
                    scrollBehavior3 = scrollBehavior;
                    $dirty2 = $dirty;
                    containerColor4 = containerColor2;
                    contentColor4 = contentColor2;
                    tonalElevation4 = tonalElevation2;
                    contentPadding4 = contentPadding2;
                    windowInsets4 = windowInsets2;
                    $dirty3 = 1562683362;
                    modifier4 = modifier2;
                } else {
                    scrollBehavior3 = null;
                    containerColor4 = containerColor2;
                    contentColor4 = contentColor2;
                    tonalElevation4 = tonalElevation2;
                    contentPadding4 = contentPadding2;
                    windowInsets4 = windowInsets2;
                    $dirty2 = $dirty;
                    modifier4 = modifier2;
                    $dirty3 = 1562683362;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart($dirty3, $dirty2, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:1226)");
            }
            $composer2 = $composer3;
            m2082BottomAppBarLayoutt5fmz9U(BottomAppBarTokens.INSTANCE.m3509getContainerHeightD9Ej5fM(), Arrangement.INSTANCE.getStart(), modifier4, containerColor4, contentColor4, tonalElevation4, contentPadding4, windowInsets4, scrollBehavior3, function3, $composer2, (($dirty2 << 6) & 896) | 54 | (($dirty2 << 6) & 7168) | (($dirty2 << 6) & 57344) | (($dirty2 << 6) & 458752) | (($dirty2 << 6) & 3670016) | (($dirty2 << 6) & 29360128) | (($dirty2 << 6) & 234881024) | (($dirty2 << 6) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            containerColor3 = containerColor4;
            contentColor3 = contentColor4;
            tonalElevation3 = tonalElevation4;
            contentPadding3 = contentPadding4;
            windowInsets3 = windowInsets4;
            scrollBehavior2 = scrollBehavior3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.BottomAppBar_e_3WI5M$lambda$15(modifier3, containerColor3, contentColor3, tonalElevation3, contentPadding3, windowInsets3, scrollBehavior2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:161:0x0245  */
    /* JADX INFO: renamed from: FlexibleBottomAppBar-wBhsO_E, reason: not valid java name */
    public static final void m2084FlexibleBottomAppBarwBhsO_E(Modifier modifier, long containerColor, long contentColor, PaddingValues contentPadding, Arrangement.Horizontal horizontalArrangement, float expandedHeight, WindowInsets windowInsets, BottomAppBarScrollBehavior scrollBehavior, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long containerColor2;
        long contentColor2;
        PaddingValues paddingValues;
        Arrangement.Horizontal horizontal;
        Composer $composer2;
        final WindowInsets windowInsets2;
        final Modifier modifier3;
        final long containerColor3;
        final long contentColor3;
        final PaddingValues contentPadding2;
        final Arrangement.Horizontal horizontalArrangement2;
        final float expandedHeight2;
        final BottomAppBarScrollBehavior scrollBehavior2;
        int $dirty;
        float expandedHeight3;
        WindowInsets windowInsets3;
        BottomAppBarScrollBehavior scrollBehavior3;
        PaddingValues contentPadding3;
        WindowInsets windowInsets4;
        long containerColor4;
        Arrangement.Horizontal horizontalArrangement3;
        int i2;
        int $dirty2;
        long contentColor4;
        float fM2106getFlexibleBottomAppBarHeightD9Ej5fM$material3;
        Composer $composer3 = $composer.startRestartGroup(-1717957447);
        ComposerKt.sourceInformation($composer3, "C(FlexibleBottomAppBar)N(modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,contentPadding,horizontalArrangement,expandedHeight:c#ui.unit.Dp,windowInsets,scrollBehavior,content)1298@66503L646:AppBar.kt#uh7d8r");
        int $dirty3 = $changed;
        int i3 = i & 1;
        if (i3 != 0) {
            $dirty3 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer3.changed(containerColor2) ? 32 : 16;
                $dirty3 |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty3 |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer3.changed(contentColor2) ? 256 : 128;
                $dirty3 |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty3 |= i5;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty3 |= 3072;
            paddingValues = contentPadding;
        } else if (($changed & 3072) == 0) {
            paddingValues = contentPadding;
            $dirty3 |= $composer3.changed(paddingValues) ? 2048 : 1024;
        } else {
            paddingValues = contentPadding;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty3 |= 24576;
            horizontal = horizontalArrangement;
        } else if (($changed & 24576) == 0) {
            horizontal = horizontalArrangement;
            $dirty3 |= $composer3.changed(horizontal) ? 16384 : 8192;
        } else {
            horizontal = horizontalArrangement;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changed(expandedHeight) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty3 |= ((i & 64) == 0 && $composer3.changed(windowInsets)) ? 1048576 : 524288;
        }
        int $dirty4 = i & 128;
        if ($dirty4 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changed(scrollBehavior) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty5 = $dirty3;
        if ($composer3.shouldExecute(($dirty3 & 38347923) != 38347922, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1288@65983L14,1289@66025L31,1294@66379L12");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty = $dirty5 & (-113);
                } else {
                    $dirty = $dirty5;
                }
                if ((i & 4) != 0) {
                    contentColor2 = ColorSchemeKt.m2263contentColorForek8zF_U(containerColor2, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                }
                PaddingValues contentPadding4 = i6 != 0 ? BottomAppBarDefaults.INSTANCE.getFlexibleContentPadding$material3() : paddingValues;
                Arrangement.Horizontal horizontalArrangement4 = i7 != 0 ? BottomAppBarDefaults.INSTANCE.getFlexibleHorizontalArrangement$material3() : horizontal;
                expandedHeight3 = i8 != 0 ? BottomAppBarDefaults.INSTANCE.m2106getFlexibleBottomAppBarHeightD9Ej5fM$material3() : expandedHeight;
                if ((i & 64) != 0) {
                    windowInsets3 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty &= -3670017;
                } else {
                    windowInsets3 = windowInsets;
                }
                if ($dirty4 != 0) {
                    contentPadding3 = contentPadding4;
                    windowInsets4 = windowInsets3;
                    containerColor4 = containerColor2;
                    horizontalArrangement3 = horizontalArrangement4;
                    scrollBehavior3 = null;
                    i2 = -1717957447;
                    $dirty2 = $dirty;
                    contentColor4 = contentColor2;
                } else {
                    scrollBehavior3 = scrollBehavior;
                    contentPadding3 = contentPadding4;
                    windowInsets4 = windowInsets3;
                    containerColor4 = containerColor2;
                    horizontalArrangement3 = horizontalArrangement4;
                    i2 = -1717957447;
                    $dirty2 = $dirty;
                    contentColor4 = contentColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 2) != 0 ? $dirty5 & (-113) : $dirty5;
                if ((i & 4) != 0) {
                    $dirty6 &= -897;
                }
                if ((i & 64) != 0) {
                    $dirty6 &= -3670017;
                }
                windowInsets4 = windowInsets;
                scrollBehavior3 = scrollBehavior;
                $dirty2 = $dirty6;
                containerColor4 = containerColor2;
                contentPadding3 = paddingValues;
                horizontalArrangement3 = horizontal;
                i2 = -1717957447;
                expandedHeight3 = expandedHeight;
                contentColor4 = contentColor2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty2, -1, "androidx.compose.material3.FlexibleBottomAppBar (AppBar.kt:1297)");
            }
            float $this$isFinite$iv = expandedHeight3;
            if ((Float.floatToRawIntBits($this$isFinite$iv) & Integer.MAX_VALUE) < 2139095040) {
                float $this$isSpecified$iv = expandedHeight3;
                if (Float.isNaN($this$isSpecified$iv)) {
                    fM2106getFlexibleBottomAppBarHeightD9Ej5fM$material3 = BottomAppBarDefaults.INSTANCE.m2106getFlexibleBottomAppBarHeightD9Ej5fM$material3();
                } else {
                    int $this$dp$iv = Dp.m7904compareTo0680j_4(expandedHeight3, Dp.m7905constructorimpl(0));
                    if ($this$dp$iv > 0) {
                        fM2106getFlexibleBottomAppBarHeightD9Ej5fM$material3 = expandedHeight3;
                    } else {
                        fM2106getFlexibleBottomAppBarHeightD9Ej5fM$material3 = BottomAppBarDefaults.INSTANCE.m2106getFlexibleBottomAppBarHeightD9Ej5fM$material3();
                    }
                }
            } else {
                fM2106getFlexibleBottomAppBarHeightD9Ej5fM$material3 = BottomAppBarDefaults.INSTANCE.m2106getFlexibleBottomAppBarHeightD9Ej5fM$material3();
            }
            $composer2 = $composer3;
            Modifier modifier4 = modifier2;
            m2082BottomAppBarLayoutt5fmz9U(fM2106getFlexibleBottomAppBarHeightD9Ej5fM$material3, horizontalArrangement3, modifier4, containerColor4, contentColor4, AppBarTokens.INSTANCE.m3480getContainerElevationD9Ej5fM(), contentPadding3, windowInsets4, scrollBehavior3, function3, $composer2, (($dirty2 >> 9) & 112) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty2 << 6) & 896) | (($dirty2 << 6) & 7168) | (($dirty2 << 6) & 57344) | (($dirty2 << 9) & 3670016) | (($dirty2 << 3) & 29360128) | (($dirty2 << 3) & 234881024) | (($dirty2 << 3) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            expandedHeight2 = expandedHeight3;
            horizontalArrangement2 = horizontalArrangement3;
            modifier3 = modifier4;
            containerColor3 = containerColor4;
            contentColor3 = contentColor4;
            contentPadding2 = contentPadding3;
            windowInsets2 = windowInsets4;
            scrollBehavior2 = scrollBehavior3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            windowInsets2 = windowInsets;
            modifier3 = modifier2;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            contentPadding2 = paddingValues;
            horizontalArrangement2 = horizontal;
            expandedHeight2 = expandedHeight;
            scrollBehavior2 = scrollBehavior;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.FlexibleBottomAppBar_wBhsO_E$lambda$16(modifier3, containerColor3, contentColor3, contentPadding2, horizontalArrangement2, expandedHeight2, windowInsets2, scrollBehavior2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:144:0x0212  */
    /* JADX INFO: renamed from: BottomAppBarLayout-t5fmz9U, reason: not valid java name */
    private static final void m2082BottomAppBarLayoutt5fmz9U(final float containerHeight, final Arrangement.Horizontal horizontalArrangement, Modifier modifier, final long containerColor, final long contentColor, final float tonalElevation, final PaddingValues contentPadding, final WindowInsets windowInsets, final BottomAppBarScrollBehavior scrollBehavior, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        final Arrangement.Horizontal horizontal;
        Modifier modifier2;
        long j;
        int $dirty;
        float f;
        final PaddingValues paddingValues;
        WindowInsets windowInsets2;
        BottomAppBarScrollBehavior bottomAppBarScrollBehavior;
        Composer $composer2;
        final Modifier appBarDragModifier;
        int $dirty2;
        Modifier.Companion appBarDragModifier2;
        boolean invalid$iv;
        AppBarKt$BottomAppBarLayout$appBarDragModifier$2$1 value$iv;
        Composer $composer3 = $composer.startRestartGroup(-141162176);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBarLayout)N(containerHeight:c#ui.unit.Dp,horizontalArrangement,modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,contentPadding,windowInsets,scrollBehavior,content)1331@67632L33,1363@69116L5,1366@69187L620,1379@69857L350,1358@68849L1358:AppBar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(containerHeight) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
            horizontal = horizontalArrangement;
        } else if (($changed & 48) == 0) {
            horizontal = horizontalArrangement;
            $dirty3 |= $composer3.changed(horizontal) ? 32 : 16;
        } else {
            horizontal = horizontalArrangement;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if ((i & 8) != 0) {
            $dirty3 |= 3072;
            j = containerColor;
        } else if (($changed & 3072) == 0) {
            j = containerColor;
            $dirty3 |= $composer3.changed(j) ? 2048 : 1024;
        } else {
            j = containerColor;
        }
        if ((i & 16) != 0) {
            $dirty = $dirty3 | 24576;
        } else if (($changed & 24576) == 0) {
            $dirty = $dirty3 | ($composer3.changed(contentColor) ? 16384 : 8192);
        } else {
            $dirty = $dirty3;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = tonalElevation;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            f = tonalElevation;
            $dirty |= $composer3.changed(f) ? 131072 : 65536;
        } else {
            f = tonalElevation;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
            paddingValues = contentPadding;
        } else if (($changed & 1572864) == 0) {
            paddingValues = contentPadding;
            $dirty |= $composer3.changed(paddingValues) ? 1048576 : 524288;
        } else {
            paddingValues = contentPadding;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
            windowInsets2 = windowInsets;
        } else if (($changed & 12582912) == 0) {
            windowInsets2 = windowInsets;
            $dirty |= $composer3.changed(windowInsets2) ? 8388608 : 4194304;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
            bottomAppBarScrollBehavior = scrollBehavior;
        } else if (($changed & 100663296) == 0) {
            bottomAppBarScrollBehavior = scrollBehavior;
            $dirty |= $composer3.changed(bottomAppBarScrollBehavior) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            bottomAppBarScrollBehavior = scrollBehavior;
        }
        if ((i & 512) != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        if ($composer3.shouldExecute(($dirty & 306783379) != 306783378, $dirty & 1)) {
            Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-141162176, $dirty, -1, "androidx.compose.material3.BottomAppBarLayout (AppBar.kt:1330)");
            }
            final BottomAppBarScrollBehavior activeScrollBehavior = !BottomAppBarLayout_t5fmz9U$lambda$17(rememberTouchExplorationService($composer3, 0)) ? bottomAppBarScrollBehavior : null;
            if (activeScrollBehavior == null || activeScrollBehavior.getIsPinned()) {
                $dirty2 = $dirty;
                $composer3.startReplaceGroup(-587975934);
                $composer3.endReplaceGroup();
                appBarDragModifier2 = Modifier.INSTANCE;
            } else {
                $composer3.startReplaceGroup(-588583503);
                ComposerKt.sourceInformation($composer3, "1339@68112L105,1339@68089L128,1342@68251L308");
                Modifier.Companion companion = Modifier.INSTANCE;
                Orientation orientation = Orientation.Vertical;
                ComposerKt.sourceInformationMarkerStart($composer3, 1782133129, "CC(remember):AppBar.kt#9igjgp");
                boolean invalid$iv2 = $composer3.changed(activeScrollBehavior);
                Object value$iv2 = $composer3.rememberedValue();
                if (invalid$iv2) {
                    $dirty2 = $dirty;
                } else {
                    $dirty2 = $dirty;
                    if (value$iv2 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    DraggableState draggableStateRememberDraggableState = DraggableKt.rememberDraggableState((Function1) value$iv2, $composer3, 0);
                    Modifier.Companion companion2 = companion;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1782137780, "CC(remember):AppBar.kt#9igjgp");
                    invalid$iv = $composer3.changed(activeScrollBehavior);
                    value$iv = $composer3.rememberedValue();
                    if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = new AppBarKt$BottomAppBarLayout$appBarDragModifier$2$1(activeScrollBehavior, null);
                        $composer3.updateRememberedValue(value$iv);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    appBarDragModifier2 = DraggableKt.draggable(companion2, draggableStateRememberDraggableState, orientation, (TsExtractor.TS_PACKET_SIZE & 4) != 0, (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : null, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : false, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) value$iv, (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : false);
                    $composer3.endReplaceGroup();
                }
                value$iv2 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppBarKt.BottomAppBarLayout_t5fmz9U$lambda$19$lambda$18(activeScrollBehavior, ((Float) obj).floatValue());
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                DraggableState draggableStateRememberDraggableState2 = DraggableKt.rememberDraggableState((Function1) value$iv2, $composer3, 0);
                Modifier.Companion companion3 = companion;
                ComposerKt.sourceInformationMarkerStart($composer3, 1782137780, "CC(remember):AppBar.kt#9igjgp");
                invalid$iv = $composer3.changed(activeScrollBehavior);
                value$iv = $composer3.rememberedValue();
                if (invalid$iv) {
                    value$iv = new AppBarKt$BottomAppBarLayout$appBarDragModifier$2$1(activeScrollBehavior, null);
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = new AppBarKt$BottomAppBarLayout$appBarDragModifier$2$1(activeScrollBehavior, null);
                    $composer3.updateRememberedValue(value$iv);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                appBarDragModifier2 = DraggableKt.draggable(companion3, draggableStateRememberDraggableState2, orientation, (TsExtractor.TS_PACKET_SIZE & 4) != 0, (TsExtractor.TS_PACKET_SIZE & 8) != 0 ? null : null, (TsExtractor.TS_PACKET_SIZE & 16) != 0 ? false : false, (TsExtractor.TS_PACKET_SIZE & 32) != 0 ? DraggableKt.NoOpOnDragStarted : null, (TsExtractor.TS_PACKET_SIZE & 64) != 0 ? DraggableKt.NoOpOnDragStopped : (Function3) value$iv, (TsExtractor.TS_PACKET_SIZE & 128) != 0 ? false : false);
                $composer3.endReplaceGroup();
            }
            Shape value = ShapesKt.getValue(BottomAppBarTokens.INSTANCE.getContainerShape(), $composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, 1782168044, "CC(remember):AppBar.kt#9igjgp");
            boolean invalid$iv3 = $composer3.changed(activeScrollBehavior);
            Object value$iv3 = $composer3.rememberedValue();
            if (invalid$iv3 || value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = new Function3() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return AppBarKt.BottomAppBarLayout_t5fmz9U$lambda$23$lambda$22(activeScrollBehavior, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                    }
                };
                $composer3.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final WindowInsets windowInsets3 = windowInsets2;
            int $dirty4 = $dirty2;
            $composer2 = $composer3;
            SurfaceKt.m2929SurfaceT9BRK9s(LayoutModifierKt.layout(modifier3, (Function3) value$iv3).then(appBarDragModifier2), value, j, contentColor, f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1054101083, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBarLayout$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    ComposerKt.sourceInformation($composer4, "C1380@69867L334:AppBar.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1054101083, $changed2, -1, "androidx.compose.material3.BottomAppBarLayout.<anonymous> (AppBar.kt:1380)");
                    }
                    Modifier modifier$iv = PaddingKt.padding(SizeKt.m851height3ABfNKs(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), windowInsets3), containerHeight), paddingValues);
                    Arrangement.Horizontal horizontalArrangement$iv = horizontal;
                    Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getCenterVertically();
                    Function3<RowScope, Composer, Integer, Unit> function4 = function3;
                    ComposerKt.sourceInformationMarkerStart($composer4, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer4, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    int $changed$iv$iv = (384 << 3) & 112;
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
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i3 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                    function4.invoke(RowScopeInstance.INSTANCE, $composer4, Integer.valueOf(((384 >> 6) & 112) | 6));
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, (($dirty4 >> 3) & 896) | 12582912 | (($dirty4 >> 3) & 7168) | (($dirty4 >> 3) & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            appBarDragModifier = modifier3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            appBarDragModifier = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.BottomAppBarLayout_t5fmz9U$lambda$24(containerHeight, horizontalArrangement, appBarDragModifier, containerColor, contentColor, tonalElevation, contentPadding, windowInsets, scrollBehavior, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean BottomAppBarLayout_t5fmz9U$lambda$17(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final Unit BottomAppBarLayout_t5fmz9U$lambda$19$lambda$18(BottomAppBarScrollBehavior $activeScrollBehavior, float delta) {
        BottomAppBarState state = $activeScrollBehavior.getState();
        state.setHeightOffset(state.getHeightOffset() - delta);
        return Unit.INSTANCE;
    }

    static final MeasureResult BottomAppBarLayout_t5fmz9U$lambda$23$lambda$22(BottomAppBarScrollBehavior $activeScrollBehavior, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        BottomAppBarState state;
        BottomAppBarState state2;
        final Placeable placeable = measurable.mo6541measureBRTryo0(constraints.getValue());
        if ($activeScrollBehavior != null && (state2 = $activeScrollBehavior.getState()) != null) {
            state2.setHeightOffsetLimit(-placeable.getHeight());
        }
        float height = RangesKt.coerceAtLeast(placeable.getHeight() + (($activeScrollBehavior == null || (state = $activeScrollBehavior.getState()) == null) ? 0.0f : state.getHeightOffset()), 0.0f);
        return MeasureScope.layout$default($this$layout, placeable.getWidth(), MathKt.roundToInt(height), null, new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda34
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppBarKt.BottomAppBarLayout_t5fmz9U$lambda$23$lambda$22$lambda$21(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit BottomAppBarLayout_t5fmz9U$lambda$23$lambda$22$lambda$21(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static final TopAppBarState rememberTopAppBarState(final float initialHeightOffsetLimit, final float initialHeightOffset, final float initialContentOffset, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1801969826, "C(rememberTopAppBarState)N(initialHeightOffsetLimit,initialHeightOffset,initialContentOffset)1858@91732L99,1858@91685L146:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialHeightOffsetLimit = -3.4028235E38f;
        }
        if ((i & 2) != 0) {
            initialHeightOffset = 0.0f;
        }
        if ((i & 4) != 0) {
            initialContentOffset = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1801969826, $changed, -1, "androidx.compose.material3.rememberTopAppBarState (AppBar.kt:1857)");
        }
        Object[] objArr = new Object[0];
        Saver<TopAppBarState, ?> saver = TopAppBarState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, 821190565, "CC(remember):AppBar.kt#9igjgp");
        boolean z = true;
        boolean z2 = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialHeightOffsetLimit)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialHeightOffset)) || ($changed & 48) == 32);
        if (((($changed & 896) ^ 384) <= 256 || !$composer.changed(initialContentOffset)) && ($changed & 384) != 256) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppBarKt.rememberTopAppBarState$lambda$26$lambda$25(initialHeightOffsetLimit, initialHeightOffset, initialContentOffset);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TopAppBarState topAppBarState = (TopAppBarState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) saver, (Function0) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return topAppBarState;
    }

    static final TopAppBarState rememberTopAppBarState$lambda$26$lambda$25(float $initialHeightOffsetLimit, float $initialHeightOffset, float $initialContentOffset) {
        return new TopAppBarState($initialHeightOffsetLimit, $initialHeightOffset, $initialContentOffset);
    }

    public static final BottomAppBarState rememberBottomAppBarState(final float initialHeightOffsetLimit, final float initialHeightOffset, final float initialContentOffset, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1420874240, "C(rememberBottomAppBarState)N(initialHeightOffsetLimit,initialHeightOffset,initialContentOffset)2235@107287L102,2235@107237L152:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialHeightOffsetLimit = -3.4028235E38f;
        }
        if ((i & 2) != 0) {
            initialHeightOffset = 0.0f;
        }
        if ((i & 4) != 0) {
            initialContentOffset = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1420874240, $changed, -1, "androidx.compose.material3.rememberBottomAppBarState (AppBar.kt:2234)");
        }
        Object[] objArr = new Object[0];
        Saver<BottomAppBarState, ?> saver = BottomAppBarState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, -339446298, "CC(remember):AppBar.kt#9igjgp");
        boolean z = true;
        boolean z2 = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialHeightOffsetLimit)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialHeightOffset)) || ($changed & 48) == 32);
        if (((($changed & 896) ^ 384) <= 256 || !$composer.changed(initialContentOffset)) && ($changed & 384) != 256) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppBarKt.BottomAppBarState(initialHeightOffsetLimit, initialHeightOffset, initialContentOffset);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        BottomAppBarState bottomAppBarState = (BottomAppBarState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) saver, (Function0) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return bottomAppBarState;
    }

    public static final BottomAppBarState BottomAppBarState(float initialHeightOffsetLimit, float initialHeightOffset, float initialContentOffset) {
        return new BottomAppBarStateImpl(initialHeightOffsetLimit, initialHeightOffset, initialContentOffset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:39:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:40:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:43:0x011d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:44:0x011e  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object settleAppBarBottom(final BottomAppBarState state, float velocity, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Continuation<? super Velocity> continuation) {
        C03041 c03041;
        Ref.FloatRef remainingVelocity;
        final BottomAppBarState state2;
        AnimationSpec<Float> animationSpec2;
        BottomAppBarState state3;
        Ref.FloatRef remainingVelocity2;
        AnimationSpec<Float> animationSpec3;
        AnimationState animationStateAnimationState$default;
        float heightOffsetLimit;
        Float fBoxFloat;
        Function1 function1;
        Ref.FloatRef remainingVelocity3;
        if (continuation instanceof C03041) {
            c03041 = (C03041) continuation;
            if ((c03041.label & Integer.MIN_VALUE) != 0) {
                c03041.label -= Integer.MIN_VALUE;
            } else {
                c03041 = new C03041(continuation);
            }
        } else {
            c03041 = new C03041(continuation);
        }
        C03041 c03042 = c03041;
        Object $result = c03042.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (c03042.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (state.getCollapsedFraction() >= 0.01f) {
                    if (!(state.getCollapsedFraction() == 1.0f)) {
                        final Ref.FloatRef remainingVelocity4 = new Ref.FloatRef();
                        remainingVelocity4.element = velocity;
                        if (decayAnimationSpec != null && Math.abs(velocity) > 1.0f) {
                            final Ref.FloatRef lastValue = new Ref.FloatRef();
                            AnimationState animationStateAnimationState$default2 = AnimationStateKt.AnimationState$default(0.0f, velocity, 0L, 0L, false, 28, null);
                            Function1 function2 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda30
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AppBarKt.settleAppBarBottom$lambda$29(lastValue, state, remainingVelocity4, (AnimationScope) obj);
                                }
                            };
                            c03042.L$0 = state;
                            c03042.L$1 = animationSpec;
                            c03042.L$2 = remainingVelocity4;
                            c03042.label = 1;
                            if (SuspendAnimationKt.animateDecay$default(animationStateAnimationState$default2, decayAnimationSpec, false, function2, c03042, 2, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            state3 = state;
                            remainingVelocity2 = remainingVelocity4;
                            animationSpec3 = animationSpec;
                            remainingVelocity = remainingVelocity2;
                            state2 = state3;
                            animationSpec2 = animationSpec3;
                        } else {
                            remainingVelocity = remainingVelocity4;
                            state2 = state;
                            animationSpec2 = animationSpec;
                        }
                        if (animationSpec2 != null && state2.getHeightOffset() < 0.0f && state2.getHeightOffset() > state2.getHeightOffsetLimit()) {
                            animationStateAnimationState$default = AnimationStateKt.AnimationState$default(state2.getHeightOffset(), 0.0f, 0L, 0L, false, 30, null);
                            if (state2.getCollapsedFraction() < 0.5f) {
                                heightOffsetLimit = 0.0f;
                            } else {
                                heightOffsetLimit = state2.getHeightOffsetLimit();
                            }
                            fBoxFloat = Boxing.boxFloat(heightOffsetLimit);
                            function1 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda31
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AppBarKt.settleAppBarBottom$lambda$30(state2, (AnimationScope) obj);
                                }
                            };
                            c03042.L$0 = remainingVelocity;
                            c03042.L$1 = null;
                            c03042.L$2 = null;
                            c03042.label = 2;
                            if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec2, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return Unit.INSTANCE;
                                }
                            } : function1, c03042) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            remainingVelocity3 = remainingVelocity;
                            remainingVelocity = remainingVelocity3;
                        }
                        return Velocity.m8134boximpl(VelocityKt.Velocity(0.0f, remainingVelocity.element));
                    }
                }
                return Velocity.m8134boximpl(Velocity.INSTANCE.m8154getZero9UxMQ8M());
            case 1:
                remainingVelocity2 = (Ref.FloatRef) c03042.L$2;
                animationSpec3 = (AnimationSpec) c03042.L$1;
                state3 = (BottomAppBarState) c03042.L$0;
                ResultKt.throwOnFailure($result);
                remainingVelocity = remainingVelocity2;
                state2 = state3;
                animationSpec2 = animationSpec3;
                if (animationSpec2 != null) {
                    animationStateAnimationState$default = AnimationStateKt.AnimationState$default(state2.getHeightOffset(), 0.0f, 0L, 0L, false, 30, null);
                    if (state2.getCollapsedFraction() < 0.5f) {
                        heightOffsetLimit = 0.0f;
                    } else {
                        heightOffsetLimit = state2.getHeightOffsetLimit();
                    }
                    fBoxFloat = Boxing.boxFloat(heightOffsetLimit);
                    function1 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda31
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppBarKt.settleAppBarBottom$lambda$30(state2, (AnimationScope) obj);
                        }
                    };
                    c03042.L$0 = remainingVelocity;
                    c03042.L$1 = null;
                    c03042.L$2 = null;
                    c03042.label = 2;
                    if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec2, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    } : function1, c03042) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    remainingVelocity3 = remainingVelocity;
                    remainingVelocity = remainingVelocity3;
                }
                return Velocity.m8134boximpl(VelocityKt.Velocity(0.0f, remainingVelocity.element));
            case 2:
                remainingVelocity3 = (Ref.FloatRef) c03042.L$0;
                ResultKt.throwOnFailure($result);
                remainingVelocity = remainingVelocity3;
                return Velocity.m8134boximpl(VelocityKt.Velocity(0.0f, remainingVelocity.element));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final Unit settleAppBarBottom$lambda$29(Ref.FloatRef $lastValue, BottomAppBarState $state, Ref.FloatRef $remainingVelocity, AnimationScope $this$animateDecay) {
        float delta = ((Number) $this$animateDecay.getValue()).floatValue() - $lastValue.element;
        float initialHeightOffset = $state.getHeightOffset();
        $state.setHeightOffset(initialHeightOffset + delta);
        float consumed = Math.abs(initialHeightOffset - $state.getHeightOffset());
        $lastValue.element = ((Number) $this$animateDecay.getValue()).floatValue();
        $remainingVelocity.element = ((Number) $this$animateDecay.getVelocity()).floatValue();
        if (Math.abs(delta - consumed) > 0.5f) {
            $this$animateDecay.cancelAnimation();
        }
        return Unit.INSTANCE;
    }

    static final Unit settleAppBarBottom$lambda$30(BottomAppBarState $state, AnimationScope $this$animateTo) {
        $state.setHeightOffset(((Number) $this$animateTo.getValue()).floatValue());
        return Unit.INSTANCE;
    }

    static {
        float arg0$iv = Dp.m7905constructorimpl(16);
        float other$iv = Dp.m7905constructorimpl(12);
        BottomAppBarHorizontalPadding = Dp.m7905constructorimpl(arg0$iv - other$iv);
        float arg0$iv2 = Dp.m7905constructorimpl(16);
        float other$iv2 = Dp.m7905constructorimpl(12);
        BottomAppBarVerticalPadding = Dp.m7905constructorimpl(arg0$iv2 - other$iv2);
        float arg0$iv3 = Dp.m7905constructorimpl(16);
        float other$iv3 = BottomAppBarHorizontalPadding;
        FABHorizontalPadding = Dp.m7905constructorimpl(arg0$iv3 - other$iv3);
        float arg0$iv4 = Dp.m7905constructorimpl(12);
        float other$iv4 = BottomAppBarVerticalPadding;
        FABVerticalPadding = Dp.m7905constructorimpl(arg0$iv4 - other$iv4);
        LocalSingleRowTopAppBarOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda32
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultSingleRowTopAppBarOverride.INSTANCE;
            }
        }, 1, null);
        LocalTwoRowsTopAppBarOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda33
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultTwoRowsTopAppBarOverride.INSTANCE;
            }
        }, 1, null);
        TopTitleAlphaEasing = new CubicBezierEasing(0.8f, 0.0f, 0.8f, 0.15f);
        MediumTitleBottomPadding = Dp.m7905constructorimpl(24);
        LargeTitleBottomPadding = Dp.m7905constructorimpl(28);
        TopAppBarHorizontalPadding = Dp.m7905constructorimpl(4);
        float arg0$iv5 = Dp.m7905constructorimpl(16);
        float other$iv5 = TopAppBarHorizontalPadding;
        TopAppBarTitleInset = Dp.m7905constructorimpl(arg0$iv5 - other$iv5);
    }

    public static final float getBottomAppBarVerticalPadding() {
        return BottomAppBarVerticalPadding;
    }

    /* JADX INFO: renamed from: SingleRowTopAppBar-wn8IZOc, reason: not valid java name */
    private static final void m2089SingleRowTopAppBarwn8IZOc(Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle titleTextStyle, final Function2<? super Composer, ? super Integer, Unit> function3, final TextStyle subtitleTextStyle, final Alignment.Horizontal titleHorizontalAlignment, final Function2<? super Composer, ? super Integer, Unit> function4, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function5, final float expandedHeight, final WindowInsets windowInsets, final TopAppBarColors colors, final TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        TextStyle textStyle;
        Function2<? super Composer, ? super Integer, Unit> function7;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function9;
        float f;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(-2033800111);
        ComposerKt.sourceInformation($composer2, "C(SingleRowTopAppBar)N(modifier,title,titleTextStyle,subtitle,subtitleTextStyle,titleHorizontalAlignment,navigationIcon,actions,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)2500@117679L7,*2500@117696L20:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            function6 = function2;
        } else if (($changed & 48) == 0) {
            function6 = function2;
            $dirty |= $composer2.changedInstance(function6) ? 32 : 16;
        } else {
            function6 = function2;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
            textStyle = titleTextStyle;
        } else if (($changed & 384) == 0) {
            textStyle = titleTextStyle;
            $dirty |= $composer2.changed(textStyle) ? 256 : 128;
        } else {
            textStyle = titleTextStyle;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
            function7 = function3;
        } else if (($changed & 3072) == 0) {
            function7 = function3;
            $dirty |= $composer2.changedInstance(function7) ? 2048 : 1024;
        } else {
            function7 = function3;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
            textStyle2 = subtitleTextStyle;
        } else if (($changed & 24576) == 0) {
            textStyle2 = subtitleTextStyle;
            $dirty |= $composer2.changed(textStyle2) ? 16384 : 8192;
        } else {
            textStyle2 = subtitleTextStyle;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changed(titleHorizontalAlignment) ? 131072 : 65536;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
            function8 = function4;
        } else if (($changed & 1572864) == 0) {
            function8 = function4;
            $dirty |= $composer2.changedInstance(function8) ? 1048576 : 524288;
        } else {
            function8 = function4;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
            function9 = function5;
        } else if (($changed & 12582912) == 0) {
            function9 = function5;
            $dirty |= $composer2.changedInstance(function9) ? 8388608 : 4194304;
        } else {
            function9 = function5;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
            f = expandedHeight;
        } else if (($changed & 100663296) == 0) {
            f = expandedHeight;
            $dirty |= $composer2.changed(f) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            f = expandedHeight;
        }
        if ((i & 512) != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer2.changed(windowInsets) ? 536870912 : 268435456;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changed(colors) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changed(scrollBehavior) ? 32 : 16;
        }
        if (!$composer2.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 19) == 18) ? false : true, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2033800111, $dirty, $dirty1, "androidx.compose.material3.SingleRowTopAppBar (AppBar.kt:2484)");
            }
            SingleRowTopAppBarOverrideScope scope = new SingleRowTopAppBarOverrideScope(modifier3, function6, textStyle, function7, textStyle2, titleHorizontalAlignment, function8, function9, f, windowInsets, colors, scrollBehavior, null);
            ProvidableCompositionLocal<SingleRowTopAppBarOverride> providableCompositionLocal = LocalSingleRowTopAppBarOverride;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SingleRowTopAppBarOverride $this$SingleRowTopAppBar_wn8IZOc_u24lambda_u2431 = (SingleRowTopAppBarOverride) objConsume;
            $this$SingleRowTopAppBar_wn8IZOc_u24lambda_u2431.SingleRowTopAppBar(scope, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.SingleRowTopAppBar_wn8IZOc$lambda$32(modifier4, function2, titleTextStyle, function3, subtitleTextStyle, titleHorizontalAlignment, function4, function5, expandedHeight, windowInsets, colors, scrollBehavior, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final ProvidableCompositionLocal<SingleRowTopAppBarOverride> getLocalSingleRowTopAppBarOverride() {
        return LocalSingleRowTopAppBarOverride;
    }

    /* JADX INFO: renamed from: TwoRowsTopAppBar-pJA5dT0, reason: not valid java name */
    private static final void m2094TwoRowsTopAppBarpJA5dT0(Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle titleTextStyle, final float titleBottomPadding, final Function2<? super Composer, ? super Integer, Unit> function3, final TextStyle smallTitleTextStyle, final Function2<? super Composer, ? super Integer, Unit> function4, final TextStyle subtitleTextStyle, final Function2<? super Composer, ? super Integer, Unit> function5, final TextStyle smallSubtitleTextStyle, final Alignment.Horizontal titleHorizontalAlignment, final Function2<? super Composer, ? super Integer, Unit> function6, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function7, final float collapsedHeight, final float expandedHeight, final WindowInsets windowInsets, final TopAppBarColors colors, final TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function8;
        float f;
        Function2<? super Composer, ? super Integer, Unit> function9;
        TextStyle textStyle;
        Function2<? super Composer, ? super Integer, Unit> function10;
        Function2<? super Composer, ? super Integer, Unit> function11;
        final Modifier modifier3;
        Modifier modifier4;
        Composer $composer2 = $composer.startRestartGroup(1092180406);
        ComposerKt.sourceInformation($composer2, "C(TwoRowsTopAppBar)N(modifier,title,titleTextStyle,titleBottomPadding:c#ui.unit.Dp,smallTitle,smallTitleTextStyle,subtitle,subtitleTextStyle,smallSubtitle,smallSubtitleTextStyle,titleHorizontalAlignment,navigationIcon,actions,collapsedHeight:c#ui.unit.Dp,expandedHeight:c#ui.unit.Dp,windowInsets,colors,scrollBehavior)2722@127583L7,*2722@127600L18:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            function8 = function2;
        } else if (($changed & 48) == 0) {
            function8 = function2;
            $dirty |= $composer2.changedInstance(function8) ? 32 : 16;
        } else {
            function8 = function2;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(titleTextStyle) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
            f = titleBottomPadding;
        } else if (($changed & 3072) == 0) {
            f = titleBottomPadding;
            $dirty |= $composer2.changed(f) ? 2048 : 1024;
        } else {
            f = titleBottomPadding;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
            function9 = function3;
        } else if (($changed & 24576) == 0) {
            function9 = function3;
            $dirty |= $composer2.changedInstance(function9) ? 16384 : 8192;
        } else {
            function9 = function3;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            textStyle = smallTitleTextStyle;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle = smallTitleTextStyle;
            $dirty |= $composer2.changed(textStyle) ? 131072 : 65536;
        } else {
            textStyle = smallTitleTextStyle;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
            function10 = function4;
        } else if (($changed & 1572864) == 0) {
            function10 = function4;
            $dirty |= $composer2.changedInstance(function10) ? 1048576 : 524288;
        } else {
            function10 = function4;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(subtitleTextStyle) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty |= 100663296;
            function11 = function5;
        } else if ((100663296 & $changed) == 0) {
            function11 = function5;
            $dirty |= $composer2.changedInstance(function11) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            function11 = function5;
        }
        if ((i & 512) != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer2.changed(smallSubtitleTextStyle) ? 536870912 : 268435456;
        }
        if ((i & 1024) != 0) {
            $dirty1 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changed(titleHorizontalAlignment) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty1 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changedInstance(function6) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty1 |= $composer2.changedInstance(function7) ? 256 : 128;
        }
        if ((i & 8192) != 0) {
            $dirty1 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer2.changed(collapsedHeight) ? 2048 : 1024;
        }
        if ((i & 16384) != 0) {
            $dirty1 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer2.changed(expandedHeight) ? 16384 : 8192;
        }
        if ((i & 32768) != 0) {
            $dirty1 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer2.changed(windowInsets) ? 131072 : 65536;
        }
        if ((i & 65536) != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer2.changed(colors) ? 1048576 : 524288;
        }
        if ((i & 131072) != 0) {
            $dirty1 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty1 |= $composer2.changed(scrollBehavior) ? 8388608 : 4194304;
        }
        if (!$composer2.shouldExecute((($dirty & 306783379) == 306783378 && (4793491 & $dirty1) == 4793490) ? false : true, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1092180406, $dirty, $dirty1, "androidx.compose.material3.TwoRowsTopAppBar (AppBar.kt:2700)");
            }
            TwoRowsTopAppBarOverrideScope scope = new TwoRowsTopAppBarOverrideScope(modifier4, function8, titleTextStyle, f, function9, textStyle, function10, subtitleTextStyle, function11, smallSubtitleTextStyle, titleHorizontalAlignment, function6, function7, collapsedHeight, expandedHeight, windowInsets, colors, scrollBehavior, null);
            ProvidableCompositionLocal<TwoRowsTopAppBarOverride> providableCompositionLocal = LocalTwoRowsTopAppBarOverride;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            TwoRowsTopAppBarOverride $this$TwoRowsTopAppBar_pJA5dT0_u24lambda_u2434 = (TwoRowsTopAppBarOverride) objConsume;
            $this$TwoRowsTopAppBar_pJA5dT0_u24lambda_u2434.TwoRowsTopAppBar(scope, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TwoRowsTopAppBar_pJA5dT0$lambda$35(modifier3, function2, titleTextStyle, titleBottomPadding, function3, smallTitleTextStyle, function4, subtitleTextStyle, function5, smallSubtitleTextStyle, titleHorizontalAlignment, function6, function7, collapsedHeight, expandedHeight, windowInsets, colors, scrollBehavior, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final ProvidableCompositionLocal<TwoRowsTopAppBarOverride> getLocalTwoRowsTopAppBarOverride() {
        return LocalTwoRowsTopAppBarOverride;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier adjustHeightOffsetLimit(Modifier $this$adjustHeightOffsetLimit, TopAppBarScrollBehavior scrollBehavior) {
        final TopAppBarState it;
        Modifier modifierOnSizeChanged;
        return (scrollBehavior == null || (it = scrollBehavior.getState()) == null || (modifierOnSizeChanged = OnRemeasuredModifierKt.onSizeChanged($this$adjustHeightOffsetLimit, new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppBarKt.adjustHeightOffsetLimit$lambda$38$lambda$37(it, (IntSize) obj);
            }
        })) == null) ? $this$adjustHeightOffsetLimit : modifierOnSizeChanged;
    }

    static final Unit adjustHeightOffsetLimit$lambda$38$lambda$37(TopAppBarState $it, IntSize size) {
        long arg0$iv = size.m8080unboximpl();
        float offset = ((int) (4294967295L & arg0$iv)) - $it.getHeightOffset();
        $it.setHeightOffsetLimit(-offset);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:165:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:166:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:169:0x0203  */
    /* JADX WARN: Code duplicated, block: B:170:0x0206  */
    /* JADX WARN: Code duplicated, block: B:173:0x020e  */
    /* JADX WARN: Code duplicated, block: B:174:0x0211  */
    /* JADX WARN: Code duplicated, block: B:177:0x021b  */
    /* JADX WARN: Code duplicated, block: B:178:0x021e  */
    /* JADX WARN: Code duplicated, block: B:184:0x023f  */
    /* JADX WARN: Code duplicated, block: B:187:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:190:0x02b7  */
    /* JADX WARN: Code duplicated, block: B:191:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:201:0x03ae  */
    /* JADX WARN: Code duplicated, block: B:204:0x03ba  */
    /* JADX WARN: Code duplicated, block: B:205:0x03c0  */
    /* JADX WARN: Code duplicated, block: B:215:0x0486  */
    /* JADX WARN: Code duplicated, block: B:217:0x04a7  */
    /* JADX WARN: Code duplicated, block: B:219:0x04d2  */
    /* JADX WARN: Code duplicated, block: B:222:0x04ee  */
    /* JADX WARN: Code duplicated, block: B:225:0x050c  */
    /* JADX WARN: Code duplicated, block: B:231:0x0522  */
    /* JADX WARN: Code duplicated, block: B:234:0x059c  */
    /* JADX WARN: Code duplicated, block: B:237:0x05a8  */
    /* JADX WARN: Code duplicated, block: B:238:0x05ac  */
    /* JADX WARN: Code duplicated, block: B:247:0x069b  */
    /* JADX WARN: Code duplicated, block: B:249:0x06c5  */
    /* JADX WARN: Code duplicated, block: B:251:0x06ea  */
    /* JADX WARN: Code duplicated, block: B:254:0x0706  */
    /* JADX WARN: Code duplicated, block: B:257:0x0722  */
    /* JADX WARN: Code duplicated, block: B:263:0x0736  */
    /* JADX WARN: Code duplicated, block: B:266:0x07a1  */
    /* JADX WARN: Code duplicated, block: B:269:0x07ad  */
    /* JADX WARN: Code duplicated, block: B:270:0x07b3  */
    /* JADX WARN: Code duplicated, block: B:281:0x08ea  */
    /* JADX WARN: Code duplicated, block: B:284:0x08f6  */
    /* JADX WARN: Code duplicated, block: B:285:0x08fc  */
    /* JADX WARN: Code duplicated, block: B:288:0x092d  */
    /* JADX WARN: Code duplicated, block: B:290:0x093f  */
    /* JADX WARN: Code duplicated, block: B:291:0x0942  */
    /* JADX WARN: Code duplicated, block: B:295:0x09d6  */
    /* JADX INFO: renamed from: TopAppBarLayout-lyUyIHI, reason: not valid java name */
    public static final void m2092TopAppBarLayoutlyUyIHI(final Modifier modifier, final FloatProducer scrolledOffset, final long navigationIconContentColor, final long titleContentColor, final long subtitleContentColor, final long actionIconContentColor, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle titleTextStyle, final Function2<? super Composer, ? super Integer, Unit> function3, final TextStyle subtitleTextStyle, final Function0<Float> function0, final Arrangement.Vertical titleVerticalArrangement, final Alignment.Horizontal titleHorizontalAlignment, final int titleBottomPadding, final boolean hideTitleSemantics, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final float height, Composer $composer, final int $changed, final int $changed1) {
        int $dirty1;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean invalid$iv;
        Object value$iv;
        int i;
        int compositeKeyHash$iv;
        Function0<ComposeUiNode> constructor;
        Composer $this$Layout_u24lambda_u240$iv;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function1;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        int $dirty2;
        Composer $composer2;
        String str;
        Modifier.Companion companionClearAndSetSemantics;
        boolean invalid$iv2;
        Object it$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function6;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        Object it$iv2;
        int compositeKeyHash$iv$iv3;
        Function0<ComposeUiNode> constructor4;
        Function0<ComposeUiNode> function7;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        int $changed$iv;
        String str2;
        Modifier.Companion companionClearAndSetSemantics2;
        Object it$iv3;
        int compositeKeyHash$iv$iv4;
        Function0<ComposeUiNode> constructor5;
        Composer $this$Layout_u24lambda_u240$iv$iv4;
        Object it$iv4;
        Composer $composer3 = $composer.startRestartGroup(126395868);
        ComposerKt.sourceInformation($composer3, "C(TopAppBarLayout)N(modifier,scrolledOffset,navigationIconContentColor:c#ui.graphics.Color,titleContentColor:c#ui.graphics.Color,subtitleContentColor:c#ui.graphics.Color,actionIconContentColor:c#ui.graphics.Color,title,titleTextStyle,subtitle,subtitleTextStyle,titleAlpha,titleVerticalArrangement,titleHorizontalAlignment,titleBottomPadding,hideTitleSemantics,navigationIcon,actions,height:c#ui.unit.Dp)3053@144004L469,2995@141484L2996:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty3 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer3.changed(scrolledOffset) : $composer3.changedInstance(scrolledOffset) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(navigationIconContentColor) ? 256 : 128;
        }
        int $dirty4 = $dirty;
        if (($changed & 3072) == 0) {
            $dirty4 |= $composer3.changed(titleContentColor) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty4 |= $composer3.changed(subtitleContentColor) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty4 |= $composer3.changed(actionIconContentColor) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty4 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty4 |= $composer3.changed(titleTextStyle) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty4 |= $composer3.changed(subtitleTextStyle) ? 536870912 : 268435456;
        }
        int $dirty5 = $dirty4;
        if (($changed1 & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty3 |= $composer3.changed(titleVerticalArrangement) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty3 |= $composer3.changed(titleHorizontalAlignment) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty3 |= $composer3.changed(titleBottomPadding) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty3 |= $composer3.changed(hideTitleSemantics) ? 16384 : 8192;
        }
        if ((196608 & $changed1) == 0) {
            $dirty3 |= $composer3.changedInstance(function4) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty3 |= $composer3.changedInstance(function5) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty3 |= $composer3.changed(height) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute((($dirty5 & 306783379) == 306783378 && (4793491 & $dirty3) == 4793490) ? false : true, $dirty5 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(126395868, $dirty5, $dirty3, "androidx.compose.material3.TopAppBarLayout (AppBar.kt:2994)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1207447665, "CC(remember):AppBar.kt#9igjgp");
            if (($dirty5 & 112) != 32) {
                $dirty1 = $dirty3;
                if (($dirty5 & 64) == 0 || !$composer3.changed(scrolledOffset)) {
                    z = false;
                }
                if (($dirty1 & 112) == 32) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean z6 = z | z2;
                if (($dirty1 & 896) == 256) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z7 = z6 | z3;
                if (($dirty1 & 7168) == 2048) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                boolean z8 = z7 | z4;
                if (($dirty1 & 29360128) == 8388608) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = z8 | z5;
                value$iv = $composer3.rememberedValue();
                if (!invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                    i = 0;
                    value$iv = new TopAppBarMeasurePolicy(scrolledOffset, titleVerticalArrangement, titleHorizontalAlignment, titleBottomPadding, height, null);
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    i = 0;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                MeasurePolicy measurePolicy$iv = (TopAppBarMeasurePolicy) value$iv;
                int $changed$iv2 = ($dirty5 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, i);
                CompositionLocalMap localMap$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer3, modifier);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv = (($changed$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                    $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                    $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = ($changed$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -1360991215, "C2997@141514L281,3044@143662L265:AppBar.kt#uh7d8r");
                Modifier modifier$iv = PaddingKt.m823paddingqDBjuR0$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "navigationIcon"), TopAppBarHorizontalPadding, 0.0f, 0.0f, 0.0f, 14, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                int $changed$iv$iv2 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
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
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
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
                int i4 = ((6 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1301830080, "C2998@141617L164:AppBar.kt#uh7d8r");
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(navigationIconContentColor)), function4, $composer3, ProvidedValue.$stable | (($dirty1 >> 12) & 112));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (function3 != null) {
                    $composer3.startReplaceGroup(-1360720648);
                    ComposerKt.sourceInformation($composer3, "3012@142259L24,3004@141848L997");
                    Modifier modifierM821paddingVpY3zN4$default = PaddingKt.m821paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "title"), TopAppBarHorizontalPadding, 0.0f, 2, null);
                    if (hideTitleSemantics) {
                        $composer3.startReplaceGroup(510303260);
                        ComposerKt.sourceInformation($composer3, "3009@142137L2");
                        Modifier.Companion companion = Modifier.INSTANCE;
                        str2 = r3;
                        ComposerKt.sourceInformationMarkerStart($composer3, 510303911, str2);
                        it$iv4 = $composer3.rememberedValue();
                        if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv2 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda1
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return Unit.INSTANCE;
                                }
                            };
                            $composer3.updateRememberedValue(value$iv2);
                            it$iv4 = value$iv2;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        companionClearAndSetSemantics2 = SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) it$iv4);
                        $composer3.endReplaceGroup();
                    } else {
                        str2 = r3;
                        $composer3.startReplaceGroup(510305197);
                        $composer3.endReplaceGroup();
                        companionClearAndSetSemantics2 = Modifier.INSTANCE;
                    }
                    Modifier modifierThen = modifierM821paddingVpY3zN4$default.then(companionClearAndSetSemantics2);
                    ComposerKt.sourceInformationMarkerStart($composer3, 510307837, str2);
                    invalid$iv2 = ($dirty1 & 14) == 4;
                    it$iv3 = $composer3.rememberedValue();
                    if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv3 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AppBarKt.TopAppBarLayout_lyUyIHI$lambda$51$lambda$43$lambda$42(function0, (GraphicsLayerScope) obj);
                            }
                        };
                        $composer3.updateRememberedValue(value$iv3);
                        it$iv3 = value$iv3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifier$iv2 = GraphicsLayerModifierKt.graphicsLayer(modifierThen, (Function1) it$iv3);
                    int $changed$iv3 = $dirty1 & 896;
                    ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                    Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
                    MeasurePolicy measurePolicy$iv3 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, titleHorizontalAlignment, $composer3, (($changed$iv3 >> 3) & 14) | (($changed$iv3 >> 3) & 112));
                    int $changed$iv$iv3 = ($changed$iv3 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                    CompositionLocalMap localMap$iv$iv2 = $composer3.getCurrentCompositionLocalMap();
                    $dirty2 = $dirty1;
                    Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier$iv2);
                    constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv2 = (($changed$iv$iv3 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                        $composer3.createNode(constructor5);
                    } else {
                        $composer3.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4367constructorimpl($composer3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv4.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv4))) {
                        $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                        $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash3);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i6 = (($changed$iv3 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -190335683, "C3015@142393L202,3020@142616L211:AppBar.kt#uh7d8r");
                    ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(titleContentColor, titleTextStyle, function2, $composer3, (($dirty5 >> 9) & 14) | (($dirty5 >> 18) & 112) | (($dirty5 >> 12) & 896));
                    str = "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh";
                    $composer2 = $composer3;
                    ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(subtitleContentColor, subtitleTextStyle, function3, $composer3, (($dirty5 >> 12) & 14) | (($dirty5 >> 24) & 112) | (($dirty5 >> 18) & 896));
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceGroup();
                } else {
                    $dirty2 = $dirty1;
                    $composer2 = $composer3;
                    str = "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh";
                    $composer2.startReplaceGroup(-1359701523);
                    ComposerKt.sourceInformation($composer2, "3035@143350L24,3027@142942L693");
                    Modifier modifierM821paddingVpY3zN4$default2 = PaddingKt.m821paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "title"), TopAppBarHorizontalPadding, 0.0f, 2, null);
                    if (hideTitleSemantics) {
                        $composer2.startReplaceGroup(510338172);
                        ComposerKt.sourceInformation($composer2, "3032@143228L2");
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer2, 510338823, "CC(remember):AppBar.kt#9igjgp");
                        it$iv2 = $composer2.rememberedValue();
                        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv4 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return Unit.INSTANCE;
                                }
                            };
                            $composer2.updateRememberedValue(value$iv4);
                            it$iv2 = value$iv4;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        companionClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(companion2, (Function1) it$iv2);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(510340109);
                        $composer2.endReplaceGroup();
                        companionClearAndSetSemantics = Modifier.INSTANCE;
                    }
                    Modifier modifierThen2 = modifierM821paddingVpY3zN4$default2.then(companionClearAndSetSemantics);
                    ComposerKt.sourceInformationMarkerStart($composer2, 510342749, "CC(remember):AppBar.kt#9igjgp");
                    invalid$iv2 = ($dirty2 & 14) == 4;
                    it$iv = $composer2.rememberedValue();
                    if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv5 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AppBarKt.TopAppBarLayout_lyUyIHI$lambda$51$lambda$48$lambda$47(function0, (GraphicsLayerScope) obj);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv5);
                        it$iv = value$iv5;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifier$iv3 = GraphicsLayerModifierKt.graphicsLayer(modifierThen2, (Function1) it$iv);
                    ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                    int $changed$iv$iv4 = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str);
                    compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int $i$f$Box = $changed$iv$iv4 << 6;
                    int $changed$iv$iv$iv3 = ($i$f$Box & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        function6 = constructor3;
                        $composer2.createNode(function6);
                    } else {
                        function6 = constructor3;
                        $composer2.useNode();
                    }
                    $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash4);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i7 = ($changed$iv$iv$iv3 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i8 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -314975490, "C3037@143415L202:AppBar.kt#uh7d8r");
                    ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(titleContentColor, titleTextStyle, function2, $composer2, (($dirty5 >> 9) & 14) | (($dirty5 >> 18) & 112) | (($dirty5 >> 12) & 896));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceGroup();
                }
                Modifier modifier$iv4 = PaddingKt.m823paddingqDBjuR0$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "actionIcons"), 0.0f, 0.0f, TopAppBarHorizontalPadding, 0.0f, 11, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv5 = (6 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str);
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
                constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv4 = (($changed$iv$iv5 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function7 = constructor4;
                    $composer2.createNode(function7);
                } else {
                    function7 = constructor4;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                    $changed$iv = 6;
                } else {
                    $changed$iv = 6;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i9 = ($changed$iv$iv$iv4 >> 6) & 14;
                    Composer $composer$iv = $composer2;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i10 = (($changed$iv >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer$iv, -1525354718, "C3045@143760L153:AppBar.kt#uh7d8r");
                    CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(actionIconContentColor)), function5, $composer$iv, ProvidedValue.$stable | (($dirty2 >> 15) & 112));
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
                $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash5);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = ($changed$iv$iv$iv4 >> 6) & 14;
                Composer $composer$iv2 = $composer2;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                int i12 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv2, -1525354718, "C3045@143760L153:AppBar.kt#uh7d8r");
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(actionIconContentColor)), function5, $composer$iv2, ProvidedValue.$stable | (($dirty2 >> 15) & 112));
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
                $dirty1 = $dirty3;
            }
            z = true;
            if (($dirty1 & 112) == 32) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean z9 = z | z2;
            if (($dirty1 & 896) == 256) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z10 = z9 | z3;
            if (($dirty1 & 7168) == 2048) {
                z4 = true;
            } else {
                z4 = false;
            }
            boolean z11 = z10 | z4;
            if (($dirty1 & 29360128) == 8388608) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv = z11 | z5;
            value$iv = $composer3.rememberedValue();
            if (invalid$iv) {
                i = 0;
                value$iv = new TopAppBarMeasurePolicy(scrolledOffset, titleVerticalArrangement, titleHorizontalAlignment, titleBottomPadding, height, null);
                $composer3.updateRememberedValue(value$iv);
            } else {
                i = 0;
                value$iv = new TopAppBarMeasurePolicy(scrolledOffset, titleVerticalArrangement, titleHorizontalAlignment, titleBottomPadding, height, null);
                $composer3.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MeasurePolicy measurePolicy$iv6 = (TopAppBarMeasurePolicy) value$iv;
            int $changed$iv4 = ($dirty5 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, i);
            CompositionLocalMap localMap$iv2 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv2 = ComposedModifierKt.materializeModifier($composer3, modifier);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv6 = (($changed$iv4 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash6);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv2, ComposeUiNode.INSTANCE.getSetModifier());
            int i13 = ($changed$iv$iv6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -1360991215, "C2997@141514L281,3044@143662L265:AppBar.kt#uh7d8r");
            Modifier modifier$iv5 = PaddingKt.m823paddingqDBjuR0$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "navigationIcon"), TopAppBarHorizontalPadding, 0.0f, 0.0f, 0.0f, 14, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv7 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
            int $changed$iv$iv7 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer3, modifier$iv5);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv5 = (($changed$iv$iv7 << 6) & 896) | 6;
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
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer3);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash7);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = ($changed$iv$iv$iv5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            int i15 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1301830080, "C2998@141617L164:AppBar.kt#uh7d8r");
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(navigationIconContentColor)), function4, $composer3, ProvidedValue.$stable | (($dirty1 >> 12) & 112));
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (function3 != null) {
                $composer3.startReplaceGroup(-1360720648);
                ComposerKt.sourceInformation($composer3, "3012@142259L24,3004@141848L997");
                Modifier modifierM821paddingVpY3zN4$default3 = PaddingKt.m821paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "title"), TopAppBarHorizontalPadding, 0.0f, 2, null);
                if (hideTitleSemantics) {
                    $composer3.startReplaceGroup(510303260);
                    ComposerKt.sourceInformation($composer3, "3009@142137L2");
                    Modifier.Companion companion3 = Modifier.INSTANCE;
                    str2 = r3;
                    ComposerKt.sourceInformationMarkerStart($composer3, 510303911, str2);
                    it$iv4 = $composer3.rememberedValue();
                    if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv6 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Unit.INSTANCE;
                            }
                        };
                        $composer3.updateRememberedValue(value$iv6);
                        it$iv4 = value$iv6;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    companionClearAndSetSemantics2 = SemanticsModifierKt.clearAndSetSemantics(companion3, (Function1) it$iv4);
                    $composer3.endReplaceGroup();
                } else {
                    str2 = r3;
                    $composer3.startReplaceGroup(510305197);
                    $composer3.endReplaceGroup();
                    companionClearAndSetSemantics2 = Modifier.INSTANCE;
                }
                Modifier modifierThen3 = modifierM821paddingVpY3zN4$default3.then(companionClearAndSetSemantics2);
                ComposerKt.sourceInformationMarkerStart($composer3, 510307837, str2);
                if (($dirty1 & 14) == 4) {
                }
                it$iv3 = $composer3.rememberedValue();
                if (invalid$iv2) {
                    Object value$iv7 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppBarKt.TopAppBarLayout_lyUyIHI$lambda$51$lambda$43$lambda$42(function0, (GraphicsLayerScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv7);
                    it$iv3 = value$iv7;
                } else {
                    Object value$iv8 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppBarKt.TopAppBarLayout_lyUyIHI$lambda$51$lambda$43$lambda$42(function0, (GraphicsLayerScope) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv8);
                    it$iv3 = value$iv8;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifier$iv6 = GraphicsLayerModifierKt.graphicsLayer(modifierThen3, (Function1) it$iv3);
                int $changed$iv5 = $dirty1 & 896;
                ComposerKt.sourceInformationMarkerStart($composer3, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                Arrangement.Vertical verticalArrangement$iv2 = Arrangement.INSTANCE.getTop();
                MeasurePolicy measurePolicy$iv8 = ColumnKt.columnMeasurePolicy(verticalArrangement$iv2, titleHorizontalAlignment, $composer3, (($changed$iv5 >> 3) & 14) | (($changed$iv5 >> 3) & 112));
                int $changed$iv$iv8 = ($changed$iv5 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv4 = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                CompositionLocalMap localMap$iv$iv6 = $composer3.getCurrentCompositionLocalMap();
                $dirty2 = $dirty1;
                Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer3, modifier$iv6);
                constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv6 = (($changed$iv$iv8 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor5);
                } else {
                    $composer3.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv4 = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, measurePolicy$iv8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv4.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv4.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv4));
                $this$Layout_u24lambda_u240$iv$iv4.apply(Integer.valueOf(compositeKeyHash$iv$iv4), setCompositeKeyHash8);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv4, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                int i16 = ($changed$iv$iv$iv6 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i17 = (($changed$iv5 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -190335683, "C3015@142393L202,3020@142616L211:AppBar.kt#uh7d8r");
                ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(titleContentColor, titleTextStyle, function2, $composer3, (($dirty5 >> 9) & 14) | (($dirty5 >> 18) & 112) | (($dirty5 >> 12) & 896));
                str = "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh";
                $composer2 = $composer3;
                ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(subtitleContentColor, subtitleTextStyle, function3, $composer3, (($dirty5 >> 12) & 14) | (($dirty5 >> 24) & 112) | (($dirty5 >> 18) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                $dirty2 = $dirty1;
                $composer2 = $composer3;
                str = "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh";
                $composer2.startReplaceGroup(-1359701523);
                ComposerKt.sourceInformation($composer2, "3035@143350L24,3027@142942L693");
                Modifier modifierM821paddingVpY3zN4$default4 = PaddingKt.m821paddingVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "title"), TopAppBarHorizontalPadding, 0.0f, 2, null);
                if (hideTitleSemantics) {
                    $composer2.startReplaceGroup(510338172);
                    ComposerKt.sourceInformation($composer2, "3032@143228L2");
                    Modifier.Companion companion4 = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 510338823, "CC(remember):AppBar.kt#9igjgp");
                    it$iv2 = $composer2.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv9 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Unit.INSTANCE;
                            }
                        };
                        $composer2.updateRememberedValue(value$iv9);
                        it$iv2 = value$iv9;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    companionClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(companion4, (Function1) it$iv2);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(510340109);
                    $composer2.endReplaceGroup();
                    companionClearAndSetSemantics = Modifier.INSTANCE;
                }
                Modifier modifierThen4 = modifierM821paddingVpY3zN4$default4.then(companionClearAndSetSemantics);
                ComposerKt.sourceInformationMarkerStart($composer2, 510342749, "CC(remember):AppBar.kt#9igjgp");
                if (($dirty2 & 14) == 4) {
                }
                it$iv = $composer2.rememberedValue();
                if (invalid$iv2) {
                    Object value$iv10 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppBarKt.TopAppBarLayout_lyUyIHI$lambda$51$lambda$48$lambda$47(function0, (GraphicsLayerScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv10);
                    it$iv = value$iv10;
                } else {
                    Object value$iv11 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppBarKt.TopAppBarLayout_lyUyIHI$lambda$51$lambda$48$lambda$47(function0, (GraphicsLayerScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv11);
                    it$iv = value$iv11;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifier$iv7 = GraphicsLayerModifierKt.graphicsLayer(modifierThen4, (Function1) it$iv);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv5 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv9 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv5, false);
                int $changed$iv$iv9 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str);
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv7 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv7 = ComposedModifierKt.materializeModifier($composer2, modifier$iv7);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $i$f$Box2 = $changed$iv$iv9 << 6;
                int $changed$iv$iv$iv7 = ($i$f$Box2 & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function6 = constructor3;
                    $composer2.createNode(function6);
                } else {
                    function6 = constructor3;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash9);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                int i18 = ($changed$iv$iv$iv7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                int i19 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -314975490, "C3037@143415L202:AppBar.kt#uh7d8r");
                ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(titleContentColor, titleTextStyle, function2, $composer2, (($dirty5 >> 9) & 14) | (($dirty5 >> 18) & 112) | (($dirty5 >> 12) & 896));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            Modifier modifier$iv8 = PaddingKt.m823paddingqDBjuR0$default(LayoutIdKt.layoutId(Modifier.INSTANCE, "actionIcons"), 0.0f, 0.0f, TopAppBarHorizontalPadding, 0.0f, 11, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv6 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv10 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv6, false);
            int $changed$iv$iv10 = (6 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, str);
            compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv8 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv8 = ComposedModifierKt.materializeModifier($composer2, modifier$iv8);
            constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv8 = (($changed$iv$iv10 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function7 = constructor4;
                $composer2.createNode(function7);
            } else {
                function7 = constructor4;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash10 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                $changed$iv = 6;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
                int i110 = ($changed$iv$iv$iv8 >> 6) & 14;
                Composer $composer$iv3 = $composer2;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                int i111 = (($changed$iv >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer$iv3, -1525354718, "C3045@143760L153:AppBar.kt#uh7d8r");
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(actionIconContentColor)), function5, $composer$iv3, ProvidedValue.$stable | (($dirty2 >> 15) & 112));
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
                ComposerKt.sourceInformationMarkerEnd($composer$iv3);
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
                $changed$iv = 6;
            }
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash10);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv8, ComposeUiNode.INSTANCE.getSetModifier());
            int i112 = ($changed$iv$iv$iv8 >> 6) & 14;
            Composer $composer$iv4 = $composer2;
            ComposerKt.sourceInformationMarkerStart($composer$iv4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            int i113 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer$iv4, -1525354718, "C3045@143760L153:AppBar.kt#uh7d8r");
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5087boximpl(actionIconContentColor)), function5, $composer$iv4, ProvidedValue.$stable | (($dirty2 >> 15) & 112));
            ComposerKt.sourceInformationMarkerEnd($composer$iv4);
            ComposerKt.sourceInformationMarkerEnd($composer$iv4);
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
            $dirty5 = $dirty5;
            $composer3.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppBarKt.TopAppBarLayout_lyUyIHI$lambda$53(modifier, scrolledOffset, navigationIconContentColor, titleContentColor, subtitleContentColor, actionIconContentColor, function2, titleTextStyle, function3, subtitleTextStyle, function0, titleVerticalArrangement, titleHorizontalAlignment, titleBottomPadding, hideTitleSemantics, function4, function5, height, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TopAppBarLayout_lyUyIHI$lambda$51$lambda$43$lambda$42(Function0 $titleAlpha, GraphicsLayerScope $this$graphicsLayer) {
        $this$graphicsLayer.setAlpha(((Number) $titleAlpha.invoke()).floatValue());
        return Unit.INSTANCE;
    }

    static final Unit TopAppBarLayout_lyUyIHI$lambda$51$lambda$48$lambda$47(Function0 $titleAlpha, GraphicsLayerScope $this$graphicsLayer) {
        $this$graphicsLayer.setAlpha(((Number) $titleAlpha.invoke()).floatValue());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:39:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:40:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:43:0x011d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:44:0x011e  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object settleAppBar(final TopAppBarState state, float velocity, DecayAnimationSpec<Float> decayAnimationSpec, AnimationSpec<Float> animationSpec, Continuation<? super Velocity> continuation) {
        AnonymousClass1 anonymousClass1;
        Ref.FloatRef remainingVelocity;
        final TopAppBarState state2;
        AnimationSpec<Float> animationSpec2;
        TopAppBarState state3;
        Ref.FloatRef remainingVelocity2;
        AnimationSpec<Float> animationSpec3;
        AnimationState animationStateAnimationState$default;
        float heightOffsetLimit;
        Float fBoxFloat;
        Function1 function1;
        Ref.FloatRef remainingVelocity3;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object $result = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (anonymousClass2.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (state.getCollapsedFraction() >= 0.01f) {
                    if (!(state.getCollapsedFraction() == 1.0f)) {
                        final Ref.FloatRef remainingVelocity4 = new Ref.FloatRef();
                        remainingVelocity4.element = velocity;
                        if (decayAnimationSpec != null && Math.abs(velocity) > 1.0f) {
                            final Ref.FloatRef lastValue = new Ref.FloatRef();
                            AnimationState animationStateAnimationState$default2 = AnimationStateKt.AnimationState$default(0.0f, velocity, 0L, 0L, false, 28, null);
                            Function1 function2 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda26
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AppBarKt.settleAppBar$lambda$54(lastValue, state, remainingVelocity4, (AnimationScope) obj);
                                }
                            };
                            anonymousClass2.L$0 = state;
                            anonymousClass2.L$1 = animationSpec;
                            anonymousClass2.L$2 = remainingVelocity4;
                            anonymousClass2.label = 1;
                            if (SuspendAnimationKt.animateDecay$default(animationStateAnimationState$default2, decayAnimationSpec, false, function2, anonymousClass2, 2, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            state3 = state;
                            remainingVelocity2 = remainingVelocity4;
                            animationSpec3 = animationSpec;
                            remainingVelocity = remainingVelocity2;
                            state2 = state3;
                            animationSpec2 = animationSpec3;
                        } else {
                            remainingVelocity = remainingVelocity4;
                            state2 = state;
                            animationSpec2 = animationSpec;
                        }
                        if (animationSpec2 != null && state2.getHeightOffset() < 0.0f && state2.getHeightOffset() > state2.getHeightOffsetLimit()) {
                            animationStateAnimationState$default = AnimationStateKt.AnimationState$default(state2.getHeightOffset(), 0.0f, 0L, 0L, false, 30, null);
                            if (state2.getCollapsedFraction() < 0.5f) {
                                heightOffsetLimit = 0.0f;
                            } else {
                                heightOffsetLimit = state2.getHeightOffsetLimit();
                            }
                            fBoxFloat = Boxing.boxFloat(heightOffsetLimit);
                            function1 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda27
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return AppBarKt.settleAppBar$lambda$55(state2, (AnimationScope) obj);
                                }
                            };
                            anonymousClass2.L$0 = remainingVelocity;
                            anonymousClass2.L$1 = null;
                            anonymousClass2.L$2 = null;
                            anonymousClass2.label = 2;
                            if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec2, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return Unit.INSTANCE;
                                }
                            } : function1, anonymousClass2) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            remainingVelocity3 = remainingVelocity;
                            remainingVelocity = remainingVelocity3;
                        }
                        return Velocity.m8134boximpl(VelocityKt.Velocity(0.0f, remainingVelocity.element));
                    }
                }
                return Velocity.m8134boximpl(Velocity.INSTANCE.m8154getZero9UxMQ8M());
            case 1:
                remainingVelocity2 = (Ref.FloatRef) anonymousClass2.L$2;
                animationSpec3 = (AnimationSpec) anonymousClass2.L$1;
                state3 = (TopAppBarState) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                remainingVelocity = remainingVelocity2;
                state2 = state3;
                animationSpec2 = animationSpec3;
                if (animationSpec2 != null) {
                    animationStateAnimationState$default = AnimationStateKt.AnimationState$default(state2.getHeightOffset(), 0.0f, 0L, 0L, false, 30, null);
                    if (state2.getCollapsedFraction() < 0.5f) {
                        heightOffsetLimit = 0.0f;
                    } else {
                        heightOffsetLimit = state2.getHeightOffsetLimit();
                    }
                    fBoxFloat = Boxing.boxFloat(heightOffsetLimit);
                    function1 = new Function1() { // from class: androidx.compose.material3.AppBarKt$$ExternalSyntheticLambda27
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AppBarKt.settleAppBar$lambda$55(state2, (AnimationScope) obj);
                        }
                    };
                    anonymousClass2.L$0 = remainingVelocity;
                    anonymousClass2.L$1 = null;
                    anonymousClass2.L$2 = null;
                    anonymousClass2.label = 2;
                    if (SuspendAnimationKt.animateTo(animationStateAnimationState$default, fBoxFloat, (4 & 2) != 0 ? AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null) : animationSpec2, (4 & 4) != 0 ? false : false, (4 & 8) != 0 ? new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$$ExternalSyntheticLambda9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    } : function1, anonymousClass2) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    remainingVelocity3 = remainingVelocity;
                    remainingVelocity = remainingVelocity3;
                }
                return Velocity.m8134boximpl(VelocityKt.Velocity(0.0f, remainingVelocity.element));
            case 2:
                remainingVelocity3 = (Ref.FloatRef) anonymousClass2.L$0;
                ResultKt.throwOnFailure($result);
                remainingVelocity = remainingVelocity3;
                return Velocity.m8134boximpl(VelocityKt.Velocity(0.0f, remainingVelocity.element));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final Unit settleAppBar$lambda$54(Ref.FloatRef $lastValue, TopAppBarState $state, Ref.FloatRef $remainingVelocity, AnimationScope $this$animateDecay) {
        float delta = ((Number) $this$animateDecay.getValue()).floatValue() - $lastValue.element;
        float initialHeightOffset = $state.getHeightOffset();
        $state.setHeightOffset(initialHeightOffset + delta);
        float consumed = Math.abs(initialHeightOffset - $state.getHeightOffset());
        $lastValue.element = ((Number) $this$animateDecay.getValue()).floatValue();
        $remainingVelocity.element = ((Number) $this$animateDecay.getVelocity()).floatValue();
        if (Math.abs(delta - consumed) > 0.5f) {
            $this$animateDecay.cancelAnimation();
        }
        return Unit.INSTANCE;
    }

    static final Unit settleAppBar$lambda$55(TopAppBarState $state, AnimationScope $this$animateTo) {
        $state.setHeightOffset(((Number) $this$animateTo.getValue()).floatValue());
        return Unit.INSTANCE;
    }

    private static final State<Boolean> rememberTouchExplorationService(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1660719518, "C(rememberTouchExplorationService)3498@163258L171:AppBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1660719518, $changed, -1, "androidx.compose.material3.rememberTouchExplorationService (AppBar.kt:3498)");
        }
        State<Boolean> stateRememberAccessibilityServiceState = AccessibilityServiceStateProvider_androidKt.rememberAccessibilityServiceState(true, false, false, $composer, 438, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return stateRememberAccessibilityServiceState;
    }

    public static final CubicBezierEasing getTopTitleAlphaEasing() {
        return TopTitleAlphaEasing;
    }
}
