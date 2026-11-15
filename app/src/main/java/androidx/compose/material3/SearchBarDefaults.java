package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec_androidKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsSides;
import androidx.compose.foundation.layout.WindowInsets_androidKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.OutputTransformation;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.internal.SystemBarsDefaultInsets_androidKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.SearchBarTokens;
import androidx.compose.material3.tokens.SearchViewTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.google.android.gms.actions.SearchIntents;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SearchBar.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J[\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020!2\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u000e\b\u0002\u0010&\u001a\b\u0012\u0004\u0012\u00020!0'2\u000e\b\u0002\u0010(\u001a\b\u0012\u0004\u0012\u00020!0)2\b\b\u0002\u0010*\u001a\u00020%H\u0007¢\u0006\u0002\u0010+J-\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020/2\b\b\u0002\u00101\u001a\u000202H\u0007¢\u0006\u0004\b3\u00104Jõ\u0001\u00101\u001a\u0002022\b\b\u0002\u00105\u001a\u00020/2\b\b\u0002\u00106\u001a\u00020/2\b\b\u0002\u00107\u001a\u00020/2\b\b\u0002\u00108\u001a\u00020/2\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020/2\b\b\u0002\u0010<\u001a\u00020/2\b\b\u0002\u0010=\u001a\u00020/2\b\b\u0002\u0010>\u001a\u00020/2\b\b\u0002\u0010?\u001a\u00020/2\b\b\u0002\u0010@\u001a\u00020/2\b\b\u0002\u0010A\u001a\u00020/2\b\b\u0002\u0010B\u001a\u00020/2\b\b\u0002\u0010C\u001a\u00020/2\b\b\u0002\u0010D\u001a\u00020/2\b\b\u0002\u0010E\u001a\u00020/2\b\b\u0002\u0010F\u001a\u00020/2\b\b\u0002\u0010G\u001a\u00020/2\b\b\u0002\u0010H\u001a\u00020/2\b\b\u0002\u0010I\u001a\u00020/2\b\b\u0002\u0010J\u001a\u00020/2\b\b\u0002\u0010K\u001a\u00020/2\b\b\u0002\u0010L\u001a\u00020/H\u0007¢\u0006\u0004\bM\u0010NJ\u008e\u0002\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020T2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020W\u0012\u0004\u0012\u00020P0V2\b\b\u0002\u0010X\u001a\u00020Y2\b\b\u0002\u0010Z\u001a\u00020%2\b\b\u0002\u0010[\u001a\u00020%2\b\b\u0002\u0010\\\u001a\u00020]2\u0015\b\u0002\u0010^\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010`\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010b\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010c\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\n\b\u0002\u0010d\u001a\u0004\u0018\u00010e2\n\b\u0002\u0010f\u001a\u0004\u0018\u00010g2\b\b\u0002\u0010h\u001a\u00020i2\b\b\u0002\u0010j\u001a\u00020\u00112\b\b\u0002\u0010,\u001a\u0002022\n\b\u0002\u0010k\u001a\u0004\u0018\u00010lH\u0007¢\u0006\u0002\u0010mJ¢\u0002\u0010O\u001a\u00020P2\u0006\u0010n\u001a\u00020R2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020W\u0012\u0004\u0012\u00020P0V2\u0006\u0010o\u001a\u00020%2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020P0V2\b\b\u0002\u0010X\u001a\u00020Y2\b\b\u0002\u0010Z\u001a\u00020%2\b\b\u0002\u0010[\u001a\u00020%2\b\b\u0002\u0010\\\u001a\u00020]2\u0015\b\u0002\u0010^\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010`\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010b\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010c\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\n\b\u0002\u0010d\u001a\u0004\u0018\u00010e2\n\b\u0002\u0010f\u001a\u0004\u0018\u00010g2\b\b\u0002\u0010h\u001a\u00020i2\b\b\u0002\u0010j\u001a\u00020\u00112\b\b\u0002\u0010,\u001a\u0002022\n\b\u0002\u0010k\u001a\u0004\u0018\u00010lH\u0007¢\u0006\u0002\u0010qJÈ\u0001\u0010O\u001a\u00020P2\u0006\u0010r\u001a\u00020W2\u0012\u0010s\u001a\u000e\u0012\u0004\u0012\u00020W\u0012\u0004\u0012\u00020P0V2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020W\u0012\u0004\u0012\u00020P0V2\u0006\u0010o\u001a\u00020%2\u0012\u0010p\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020P0V2\b\b\u0002\u0010X\u001a\u00020Y2\b\b\u0002\u0010Z\u001a\u00020%2\u0015\b\u0002\u0010^\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010`\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020P\u0018\u00010$¢\u0006\u0002\b_2\b\b\u0002\u0010,\u001a\u0002022\n\b\u0002\u0010k\u001a\u0004\u0018\u00010lH\u0007¢\u0006\u0002\u0010tJ#\u0010,\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020/H\u0007¢\u0006\u0004\bu\u0010vJ\u009b\u0001\u00101\u001a\u0002022\b\b\u0002\u00105\u001a\u00020/2\b\b\u0002\u00106\u001a\u00020/2\b\b\u0002\u00107\u001a\u00020/2\b\b\u0002\u00108\u001a\u00020/2\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020/2\b\b\u0002\u0010<\u001a\u00020/2\b\b\u0002\u0010=\u001a\u00020/2\b\b\u0002\u0010>\u001a\u00020/2\b\b\u0002\u0010?\u001a\u00020/2\b\b\u0002\u0010@\u001a\u00020/2\b\b\u0002\u0010A\u001a\u00020/2\b\b\u0002\u0010B\u001a\u00020/2\b\b\u0002\u0010C\u001a\u00020/H\u0007¢\u0006\u0004\bw\u0010xJ\u0087\u0001\u00101\u001a\u0002022\b\b\u0002\u0010y\u001a\u00020/2\b\b\u0002\u00107\u001a\u00020/2\b\b\u0002\u00108\u001a\u00020/2\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020/2\b\b\u0002\u0010<\u001a\u00020/2\b\b\u0002\u0010=\u001a\u00020/2\b\b\u0002\u0010>\u001a\u00020/2\b\b\u0002\u0010?\u001a\u00020/2\b\b\u0002\u0010@\u001a\u00020/2\b\b\u0002\u0010z\u001a\u00020/2\b\b\u0002\u0010C\u001a\u00020/H\u0007¢\u0006\u0004\b{\u0010|R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u001e\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\f\u0010\u0003\u001a\u0004\b\r\u0010\u0007R\u0013\u0010\u000e\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00198G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00198G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001b¨\u0006}²\u0006\n\u0010~\u001a\u00020%X\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/SearchBarDefaults;", "", "<init>", "()V", "TonalElevation", "Landroidx/compose/ui/unit/Dp;", "getTonalElevation-D9Ej5fM", "()F", "F", "ShadowElevation", "getShadowElevation-D9Ej5fM", "Elevation", "getElevation-D9Ej5fM$annotations", "getElevation-D9Ej5fM", "InputFieldHeight", "getInputFieldHeight-D9Ej5fM", "inputFieldShape", "Landroidx/compose/ui/graphics/Shape;", "getInputFieldShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "fullScreenShape", "getFullScreenShape", "dockedShape", "getDockedShape", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "getWindowInsets", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "fullScreenWindowInsets", "getFullScreenWindowInsets", "enterAlwaysSearchBarScrollBehavior", "Landroidx/compose/material3/SearchBarScrollBehavior;", "initialOffset", "", "initialOffsetLimit", "canScroll", "Lkotlin/Function0;", "", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "reverseLayout", "(FFLkotlin/jvm/functions/Function0;Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/DecayAnimationSpec;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SearchBarScrollBehavior;", "colors", "Landroidx/compose/material3/SearchBarColors;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "dividerColor", "inputFieldColors", "Landroidx/compose/material3/TextFieldColors;", "colors-Klgx-Pg", "(JJLandroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SearchBarColors;", "focusedTextColor", "unfocusedTextColor", "disabledTextColor", "cursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "inputFieldColors-JVEmHcM", "(JJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material3/TextFieldColors;", "InputField", "", "textFieldState", "Landroidx/compose/foundation/text/input/TextFieldState;", "searchBarState", "Landroidx/compose/material3/SearchBarState;", "onSearch", "Lkotlin/Function1;", "", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "placeholder", "Landroidx/compose/runtime/Composable;", "leadingIcon", "trailingIcon", "prefix", "suffix", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "shape", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/material3/SearchBarState;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "state", "expanded", "onExpandedChange", "(Landroidx/compose/foundation/text/input/TextFieldState;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/text/input/InputTransformation;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", SearchIntents.EXTRA_QUERY, "onQueryChange", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "colors-dgg9oW8", "(JJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SearchBarColors;", "inputFieldColors-ITpI4ow", "(JJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TextFieldColors;", "textColor", "placeholderColor", "inputFieldColors--u-KgnY", "(JJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/TextFieldColors;", "material3", "focused"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SearchBarDefaults {
    public static final int $stable = 0;
    private static final float Elevation;
    private static final float InputFieldHeight;
    public static final SearchBarDefaults INSTANCE = new SearchBarDefaults();
    private static final float TonalElevation = ElevationTokens.INSTANCE.m3719getLevel0D9Ej5fM();
    private static final float ShadowElevation = ElevationTokens.INSTANCE.m3719getLevel0D9Ej5fM();

    static final Unit InputField$lambda$20(SearchBarDefaults searchBarDefaults, TextFieldState textFieldState, SearchBarState searchBarState, Function1 function1, Modifier modifier, boolean z, boolean z2, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, Function2 function6, InputTransformation inputTransformation, OutputTransformation outputTransformation, ScrollState scrollState, Shape shape, TextFieldColors textFieldColors, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        searchBarDefaults.InputField(textFieldState, searchBarState, function1, modifier, z, z2, textStyle, function2, function3, function4, function5, function6, inputTransformation, outputTransformation, scrollState, shape, textFieldColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit InputField$lambda$33(SearchBarDefaults searchBarDefaults, TextFieldState textFieldState, Function1 function1, boolean z, Function1 function2, Modifier modifier, boolean z2, boolean z3, TextStyle textStyle, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, InputTransformation inputTransformation, OutputTransformation outputTransformation, ScrollState scrollState, Shape shape, TextFieldColors textFieldColors, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        searchBarDefaults.InputField(textFieldState, function1, z, function2, modifier, z2, z3, textStyle, function3, function4, function5, function6, function7, inputTransformation, outputTransformation, scrollState, shape, textFieldColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit InputField$lambda$44(SearchBarDefaults searchBarDefaults, String str, Function1 function1, Function1 function2, boolean z, Function1 function3, Modifier modifier, boolean z2, Function2 function4, Function2 function5, Function2 function6, TextFieldColors textFieldColors, MutableInteractionSource mutableInteractionSource, int i, int i2, int i3, Composer composer, int i4) {
        searchBarDefaults.InputField(str, function1, function2, z, function3, modifier, z2, function4, function5, function6, textFieldColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to TonalElevation. Not to be confused with ShadowElevation.", replaceWith = @ReplaceWith(expression = "TonalElevation", imports = {}))
    /* JADX INFO: renamed from: getElevation-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m2770getElevationD9Ej5fM$annotations() {
    }

    private SearchBarDefaults() {
    }

    static {
        SearchBarDefaults searchBarDefaults = INSTANCE;
        Elevation = TonalElevation;
        InputFieldHeight = SearchBarTokens.INSTANCE.m4065getContainerHeightD9Ej5fM();
    }

    /* JADX INFO: renamed from: getTonalElevation-D9Ej5fM, reason: not valid java name */
    public final float m2776getTonalElevationD9Ej5fM() {
        return TonalElevation;
    }

    /* JADX INFO: renamed from: getShadowElevation-D9Ej5fM, reason: not valid java name */
    public final float m2775getShadowElevationD9Ej5fM() {
        return ShadowElevation;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name */
    public final float m2773getElevationD9Ej5fM() {
        return Elevation;
    }

    /* JADX INFO: renamed from: getInputFieldHeight-D9Ej5fM, reason: not valid java name */
    public final float m2774getInputFieldHeightD9Ej5fM() {
        return InputFieldHeight;
    }

    public final Shape getInputFieldShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1770571533, "C(<get-inputFieldShape>)1039@46188L5:SearchBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1770571533, $changed, -1, "androidx.compose.material3.SearchBarDefaults.<get-inputFieldShape> (SearchBar.kt:1039)");
        }
        Shape value = ShapesKt.getValue(SearchBarTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getFullScreenShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -2009956471, "C(<get-fullScreenShape>)1043@46362L5:SearchBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2009956471, $changed, -1, "androidx.compose.material3.SearchBarDefaults.<get-fullScreenShape> (SearchBar.kt:1043)");
        }
        Shape value = ShapesKt.getValue(SearchViewTokens.INSTANCE.getFullScreenContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final Shape getDockedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1447354121, "C(<get-dockedShape>)1047@46512L5:SearchBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1447354121, $changed, -1, "androidx.compose.material3.SearchBarDefaults.<get-dockedShape> (SearchBar.kt:1047)");
        }
        Shape value = ShapesKt.getValue(SearchViewTokens.INSTANCE.getDockedContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    public final WindowInsets getWindowInsets(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1795925906, "C(<get-windowInsets>)1053@46670L29:SearchBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1795925906, $changed, -1, "androidx.compose.material3.SearchBarDefaults.<get-windowInsets> (SearchBar.kt:1053)");
        }
        WindowInsets windowInsetsM892onlybOOhFvg = WindowInsetsKt.m892onlybOOhFvg(SystemBarsDefaultInsets_androidKt.getSystemBarsForVisualComponents(WindowInsets.INSTANCE, $composer, 6), WindowInsetsSides.m904plusgK_yJZ4(WindowInsetsSides.INSTANCE.m914getHorizontalJoeWqyM(), WindowInsetsSides.INSTANCE.m918getTopJoeWqyM()));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return windowInsetsM892onlybOOhFvg;
    }

    public final WindowInsets getFullScreenWindowInsets(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1745169224, "C(<get-fullScreenWindowInsets>)1059@46961L11:SearchBar.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1745169224, $changed, -1, "androidx.compose.material3.SearchBarDefaults.<get-fullScreenWindowInsets> (SearchBar.kt:1059)");
        }
        WindowInsets safeDrawing = WindowInsets_androidKt.getSafeDrawing(WindowInsets.INSTANCE, $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return safeDrawing;
    }

    static final boolean enterAlwaysSearchBarScrollBehavior$lambda$1$lambda$0() {
        return true;
    }

    public final SearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior(float initialOffset, float initialOffsetLimit, Function0<Boolean> function0, AnimationSpec<Float> animationSpec, DecayAnimationSpec<Float> decayAnimationSpec, boolean reverseLayout, Composer $composer, int $changed, int i) {
        final Function0<Boolean> function1;
        ComposerKt.sourceInformationMarkerStart($composer, 1222500790, "C(enterAlwaysSearchBarScrollBehavior)N(initialOffset,initialOffsetLimit,canScroll,snapAnimationSpec,flingAnimationSpec,reverseLayout)1088@48597L8,1090@48770L7,1091@48835L26,1105@49345L375,1094@48944L776:SearchBar.kt#uh7d8r");
        final float initialOffset2 = (i & 1) != 0 ? 0.0f : initialOffset;
        final float initialOffsetLimit2 = (i & 2) != 0 ? -3.4028235E38f : initialOffsetLimit;
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -1997773186, "CC(remember):SearchBar.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Boolean.valueOf(SearchBarDefaults.enterAlwaysSearchBarScrollBehavior$lambda$1$lambda$0());
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            function1 = (Function0) it$iv;
        } else {
            function1 = function0;
        }
        final AnimationSpec<Float> animationSpecValue = (i & 8) != 0 ? MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, $composer, 6) : animationSpec;
        final DecayAnimationSpec<Float> decayAnimationSpecRememberSplineBasedDecay = (i & 16) != 0 ? SplineBasedFloatDecayAnimationSpec_androidKt.rememberSplineBasedDecay($composer, 0) : decayAnimationSpec;
        boolean reverseLayout2 = (i & 32) != 0 ? false : reverseLayout;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1222500790, $changed, -1, "androidx.compose.material3.SearchBarDefaults.enterAlwaysSearchBarScrollBehavior (SearchBar.kt:1094)");
        }
        Object[] objArr = {animationSpecValue, decayAnimationSpecRememberSplineBasedDecay, function1, Boolean.valueOf(reverseLayout2)};
        Saver<EnterAlwaysSearchBarScrollBehavior, ?> Saver = EnterAlwaysSearchBarScrollBehavior.INSTANCE.Saver(function1, animationSpecValue, decayAnimationSpecRememberSplineBasedDecay);
        ComposerKt.sourceInformationMarkerStart($composer, -1997748883, "CC(remember):SearchBar.kt#9igjgp");
        boolean z = true;
        boolean z2 = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialOffset2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialOffsetLimit2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(function1)) || ($changed & 384) == 256);
        if ((((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) <= 131072 || !$composer.changed(reverseLayout2)) && ($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 131072) {
            z = false;
        }
        boolean invalid$iv = z2 | z | $composer.changedInstance(animationSpecValue) | $composer.changedInstance(decayAnimationSpecRememberSplineBasedDecay);
        Object value$iv2 = $composer.rememberedValue();
        if (invalid$iv || value$iv2 == Composer.INSTANCE.getEmpty()) {
            final boolean reverseLayout3 = reverseLayout2;
            value$iv2 = new Function0() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SearchBarDefaults.enterAlwaysSearchBarScrollBehavior$lambda$3$lambda$2(initialOffset2, initialOffsetLimit2, function1, reverseLayout3, animationSpecValue, decayAnimationSpecRememberSplineBasedDecay);
                }
            };
            $composer.updateRememberedValue(value$iv2);
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior = (EnterAlwaysSearchBarScrollBehavior) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) value$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return enterAlwaysSearchBarScrollBehavior;
    }

    static final EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior$lambda$3$lambda$2(float $initialOffset, float $initialOffsetLimit, Function0 $canScroll, boolean $reverseLayout, AnimationSpec $snapAnimationSpec, DecayAnimationSpec $flingAnimationSpec) {
        return new EnterAlwaysSearchBarScrollBehavior($initialOffset, $initialOffsetLimit, $canScroll, $reverseLayout, $snapAnimationSpec, $flingAnimationSpec);
    }

    /* JADX INFO: renamed from: colors-Klgx-Pg, reason: not valid java name */
    public final SearchBarColors m2771colorsKlgxPg(long containerColor, long dividerColor, TextFieldColors inputFieldColors, Composer $composer, int $changed, int i) {
        long containerColor2;
        TextFieldColors inputFieldColors2;
        ComposerKt.sourceInformationMarkerStart($composer, 701925149, "C(colors)N(containerColor:c#ui.graphics.Color,dividerColor:c#ui.graphics.Color,inputFieldColors)1128@50345L5,1129@50412L5,1131@50475L202:SearchBar.kt#uh7d8r");
        long containerColor3 = (i & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), $composer, 6) : containerColor;
        long dividerColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(SearchViewTokens.INSTANCE.getDividerColor(), $composer, 6) : dividerColor;
        if ((i & 4) != 0) {
            containerColor2 = containerColor3;
            inputFieldColors2 = m2779inputFieldColorsJVEmHcM(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, containerColor2, containerColor2, containerColor2, $composer, 0, 0, ($changed & 14) | (($changed << 3) & 112) | (($changed << 6) & 896) | ($changed & 7168), 1048575);
        } else {
            containerColor2 = containerColor3;
            inputFieldColors2 = inputFieldColors;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(701925149, $changed, -1, "androidx.compose.material3.SearchBarDefaults.colors (SearchBar.kt:1137)");
        }
        SearchBarColors searchBarColors = new SearchBarColors(containerColor2, dividerColor2, inputFieldColors2, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return searchBarColors;
    }

    /* JADX INFO: renamed from: inputFieldColors-JVEmHcM, reason: not valid java name */
    public final TextFieldColors m2779inputFieldColorsJVEmHcM(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long cursorColor, SelectionColors selectionColors, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long disabledTextColor2;
        SelectionColors selectionColors2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledPlaceholderColor2;
        long disabledPrefixColor2;
        long disabledSuffixColor2;
        ComposerKt.sourceInformationMarkerStart($composer, -2000124979, "C(inputFieldColors)N(focusedTextColor:c#ui.graphics.Color,unfocusedTextColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,cursorColor:c#ui.graphics.Color,selectionColors,focusedLeadingIconColor:c#ui.graphics.Color,unfocusedLeadingIconColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,focusedTrailingIconColor:c#ui.graphics.Color,unfocusedTrailingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color,focusedPlaceholderColor:c#ui.graphics.Color,unfocusedPlaceholderColor:c#ui.graphics.Color,disabledPlaceholderColor:c#ui.graphics.Color,focusedPrefixColor:c#ui.graphics.Color,unfocusedPrefixColor:c#ui.graphics.Color,disabledPrefixColor:c#ui.graphics.Color,focusedSuffixColor:c#ui.graphics.Color,unfocusedSuffixColor:c#ui.graphics.Color,disabledSuffixColor:c#ui.graphics.Color,focusedContainerColor:c#ui.graphics.Color,unfocusedContainerColor:c#ui.graphics.Color,disabledContainerColor:c#ui.graphics.Color)1178@53452L5,1179@53526L5,1181@53621L5,1184@53777L5,1185@53856L7,1186@53939L5,1187@54022L5,1189@54130L5,1192@54306L5,1193@54391L5,1195@54501L5,1198@54679L5,1199@54765L5,1201@54867L5,1204@55036L5,1205@55120L5,1207@55215L5,1210@55384L5,1211@55468L5,1213@55563L5,1216@55727L5,1217@55806L5,1218@55884L5,1220@55942L1360:SearchBar.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), $composer, 6) : unfocusedTextColor;
        if ((i & 4) != 0) {
            long value = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledTextColor2 = Color.m5095copywmQWz5c(value, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long cursorColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6) : cursorColor;
        if ((i & 16) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) objConsume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedLeadingIconColor2 = (i & 32) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 64) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((i & 128) != 0) {
            long value2 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6);
            disabledLeadingIconColor2 = Color.m5095copywmQWz5c(value2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value2) : FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value2) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long focusedTrailingIconColor2 = (i & 256) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (i & 512) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((i & 1024) != 0) {
            long value3 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6);
            disabledTrailingIconColor2 = Color.m5095copywmQWz5c(value3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value3) : FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value3) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long focusedPlaceholderColor2 = (i & 2048) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), $composer, 6) : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (i & 4096) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), $composer, 6) : unfocusedPlaceholderColor;
        if ((i & 8192) != 0) {
            long value4 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledPlaceholderColor2 = Color.m5095copywmQWz5c(value4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value4) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value4) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        long focusedPrefixColor2 = (i & 16384) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : focusedPrefixColor;
        long unfocusedPrefixColor2 = (32768 & i) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6) : unfocusedPrefixColor;
        if ((65536 & i) != 0) {
            long value5 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
            disabledPrefixColor2 = Color.m5095copywmQWz5c(value5, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value5) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value5) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value5) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value5) : 0.0f);
        } else {
            disabledPrefixColor2 = disabledPrefixColor;
        }
        long focusedSuffixColor2 = (131072 & i) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : focusedSuffixColor;
        long unfocusedSuffixColor2 = (262144 & i) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6) : unfocusedSuffixColor;
        if ((524288 & i) != 0) {
            long value6 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
            disabledSuffixColor2 = Color.m5095copywmQWz5c(value6, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value6) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value6) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value6) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value6) : 0.0f);
        } else {
            disabledSuffixColor2 = disabledSuffixColor;
        }
        long focusedContainerColor2 = (1048576 & i) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), $composer, 6) : focusedContainerColor;
        long unfocusedContainerColor2 = (2097152 & i) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), $composer, 6) : unfocusedContainerColor;
        long disabledContainerColor2 = (i & 4194304) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), $composer, 6) : disabledContainerColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2000124979, $changed, $changed1, "androidx.compose.material3.SearchBarDefaults.inputFieldColors (SearchBar.kt:1220)");
        }
        TextFieldColors textFieldColorsM3052colors0hiis_0 = TextFieldDefaults.INSTANCE.m3052colors0hiis_0(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, 0L, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, 0L, cursorColor2, 0L, selectionColors2, 0L, 0L, 0L, 0L, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, 0L, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, 0L, 0L, 0L, 0L, 0L, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, 0L, 0L, 0L, 0L, 0L, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, 0L, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, 0L, $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | (($changed2 << 12) & 57344) | (($changed2 << 12) & 458752) | (($changed2 << 12) & 3670016) | (($changed << 15) & 234881024), (($changed >> 12) & 14) | ($changed & 458752) | ($changed & 3670016) | ($changed & 29360128) | (($changed << 3) & 1879048192), (($changed >> 27) & 14) | (($changed1 << 3) & 112) | (($changed1 << 18) & 29360128) | (($changed1 << 18) & 234881024) | (($changed1 << 18) & 1879048192), (($changed1 << 3) & 458752) | (($changed1 << 3) & 3670016) | (($changed1 << 3) & 29360128) | (($changed1 << 6) & 1879048192), (($changed1 >> 24) & 14) | 3072 | (($changed1 >> 24) & 112), 1204058760, 2191);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return textFieldColorsM3052colors0hiis_0;
    }

    public final void InputField(final TextFieldState textFieldState, final SearchBarState searchBarState, final Function1<? super String, Unit> function1, Modifier modifier, boolean enabled, boolean readOnly, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, InputTransformation inputTransformation, OutputTransformation outputTransformation, ScrollState scrollState, Shape shape, TextFieldColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        boolean z2;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int i2;
        int i3;
        Composer $composer2;
        final Modifier modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final OutputTransformation outputTransformation2;
        final ScrollState scrollState2;
        final Shape shape2;
        final TextFieldColors colors2;
        final MutableInteractionSource interactionSource2;
        final boolean enabled2;
        final boolean readOnly2;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final InputTransformation inputTransformation2;
        boolean readOnly3;
        TextStyle textStyle4;
        int $dirty;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        InputTransformation inputTransformation3;
        ScrollState scrollState3;
        Shape shape3;
        int $dirty1;
        TextFieldColors colors3;
        MutableInteractionSource interactionSource3;
        final TextFieldColors colors4;
        Modifier modifier3;
        final boolean enabled3;
        TextStyle textStyle5;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        Function2<? super Composer, ? super Integer, Unit> function17;
        OutputTransformation outputTransformation3;
        final Shape shape4;
        MutableInteractionSource interactionSource4;
        final SearchBarState searchBarState2;
        Object value$iv;
        Object value$iv2;
        ComposableLambda composableLambdaRememberComposableLambda;
        ComposableLambda composableLambdaRememberComposableLambda2;
        final SearchBarState searchBarState3;
        TextFieldState textFieldState2;
        State<Boolean> state;
        Composer $composer3 = $composer.startRestartGroup(759286022);
        ComposerKt.sourceInformation($composer3, "C(InputField)N(textFieldState,searchBarState,onSearch,modifier,enabled,readOnly,textStyle,placeholder,leadingIcon,trailingIcon,prefix,suffix,inputTransformation,outputTransformation,scrollState,shape,colors,interactionSource)1323@62121L25,1324@62192L7,1325@62250L7,1327@62318L34,1328@62397L39,1336@62701L24,1342@62874L721,1360@63845L199,1365@64076L244,1377@64667L44,1402@65952L645,1383@64973L1643,1338@62735L3892,1421@66956L148,1421@66904L200,1428@67216L736,1428@67169L783,1446@68100L113,1446@68058L155:SearchBar.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty3 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(textFieldState) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(searchBarState) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 2048 : 1024;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            z = enabled;
        } else if (($changed & 24576) == 0) {
            z = enabled;
            $dirty2 |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = enabled;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z2 = readOnly;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            z2 = readOnly;
            $dirty2 |= $composer3.changed(z2) ? 131072 : 65536;
        } else {
            z2 = readOnly;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                textStyle2 = textStyle;
                int i7 = $composer3.changed(textStyle2) ? 1048576 : 524288;
                $dirty2 |= i7;
            } else {
                textStyle2 = textStyle;
            }
            $dirty2 |= i7;
        } else {
            textStyle2 = textStyle;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
            function7 = function2;
        } else if (($changed & 12582912) == 0) {
            function7 = function2;
            $dirty2 |= $composer3.changedInstance(function7) ? 8388608 : 4194304;
        } else {
            function7 = function2;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty2 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 536870912 : 268435456;
        }
        int i11 = i & 1024;
        if (i11 != 0) {
            $dirty3 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function5) ? 4 : 2;
        }
        int i12 = i & 2048;
        if (i12 != 0) {
            $dirty3 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function6) ? 32 : 16;
        }
        int i13 = i & 4096;
        if (i13 != 0) {
            $dirty3 |= 384;
            i2 = i13;
        } else {
            i2 = i13;
            if (($changed1 & 384) == 0) {
                $dirty3 |= $composer3.changed(inputTransformation) ? 256 : 128;
            }
        }
        int i14 = i & 8192;
        if (i14 != 0) {
            $dirty3 |= 3072;
            i3 = i14;
        } else {
            i3 = i14;
            if (($changed1 & 3072) == 0) {
                $dirty3 |= $composer3.changed(outputTransformation) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            $dirty3 |= ((i & 16384) == 0 && $composer3.changed(scrollState)) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= ((i & 32768) == 0 && $composer3.changed(shape)) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty3 |= ((i & 65536) == 0 && $composer3.changed(colors)) ? 1048576 : 524288;
        }
        int i15 = i & 131072;
        if (i15 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty3 |= $composer3.changed(interactionSource) ? 8388608 : 4194304;
        }
        if ((i & 262144) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed1 & 100663296) == 0) {
            $dirty3 |= $composer3.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer3.shouldExecute((($dirty2 & 306783379) == 306783378 && (38347923 & $dirty3) == 38347922) ? false : true, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1298@60882L7,1306@61310L21,1307@61356L15,1308@61407L18");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier modifier4 = i4 != 0 ? Modifier.INSTANCE : modifier;
                boolean enabled4 = i5 != 0 ? true : z;
                readOnly3 = i6 != 0 ? false : z2;
                if ((i & 64) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer3.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    textStyle4 = (TextStyle) objConsume;
                    $dirty = $dirty2 & (-3670017);
                } else {
                    textStyle4 = textStyle2;
                    $dirty = $dirty2;
                }
                Function2<? super Composer, ? super Integer, Unit> function18 = i8 != 0 ? null : function7;
                function13 = i9 != 0 ? null : function3;
                function14 = i10 != 0 ? null : function4;
                Function2<? super Composer, ? super Integer, Unit> function19 = i11 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function20 = i12 != 0 ? null : function6;
                inputTransformation3 = i2 != 0 ? null : inputTransformation;
                OutputTransformation outputTransformation4 = i3 != 0 ? null : outputTransformation;
                if ((i & 16384) != 0) {
                    $dirty3 &= -57345;
                    scrollState3 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                } else {
                    scrollState3 = scrollState;
                }
                if ((32768 & i) != 0) {
                    shape3 = getInputFieldShape($composer3, ($dirty3 >> 24) & 14);
                    $dirty1 = $dirty3 & (-458753);
                } else {
                    shape3 = shape;
                    $dirty1 = $dirty3;
                }
                if ((i & 65536) != 0) {
                    colors3 = m2779inputFieldColorsJVEmHcM(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty1 >> 15) & 7168, 8388607);
                    $dirty1 &= -3670017;
                } else {
                    colors3 = colors;
                }
                if (i15 != 0) {
                    $composer3 = $composer3;
                    interactionSource3 = null;
                    colors4 = colors3;
                    modifier3 = modifier4;
                    enabled3 = enabled4;
                    textStyle5 = textStyle4;
                    $dirty2 = $dirty;
                    function15 = function18;
                    function16 = function19;
                    function17 = function20;
                    outputTransformation3 = outputTransformation4;
                    shape4 = shape3;
                    $dirty3 = $dirty1;
                } else {
                    $composer3 = $composer3;
                    interactionSource3 = interactionSource;
                    colors4 = colors3;
                    modifier3 = modifier4;
                    enabled3 = enabled4;
                    textStyle5 = textStyle4;
                    $dirty2 = $dirty;
                    function15 = function18;
                    function16 = function19;
                    function17 = function20;
                    outputTransformation3 = outputTransformation4;
                    shape4 = shape3;
                    $dirty3 = $dirty1;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 16384) != 0) {
                    $dirty3 &= -57345;
                }
                if ((32768 & i) != 0) {
                    $dirty3 &= -458753;
                }
                if ((i & 65536) != 0) {
                    $dirty3 &= -3670017;
                }
                modifier3 = modifier;
                function13 = function3;
                function14 = function4;
                function17 = function6;
                inputTransformation3 = inputTransformation;
                outputTransformation3 = outputTransformation;
                scrollState3 = scrollState;
                colors4 = colors;
                interactionSource3 = interactionSource;
                enabled3 = z;
                readOnly3 = z2;
                textStyle5 = textStyle2;
                function15 = function7;
                function16 = function5;
                shape4 = shape;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(759286022, $dirty2, $dirty3, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.kt:1310)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(-1701839691);
                ComposerKt.sourceInformation($composer3, "1312@61585L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -470540051, "CC(remember):SearchBar.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv3);
                    it$iv = value$iv3;
                }
                interactionSource4 = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-470540702);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            final State<Boolean> stateCollectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(interactionSource4, $composer3, 0);
            ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
            MutableInteractionSource interactionSource5 = interactionSource4;
            Function2<? super Composer, ? super Integer, Unit> function21 = function15;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localFocusManager);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final FocusManager focusManager = (FocusManager) objConsume2;
            ProvidableCompositionLocal<InputModeManager> localInputModeManager = CompositionLocalsKt.getLocalInputModeManager();
            Function2<? super Composer, ? super Integer, Unit> function22 = function16;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer3.consume(localInputModeManager);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final boolean isInTouchMode = InputMode.m5903equalsimpl0(((InputModeManager) objConsume3).mo5909getInputModeaOaMEAU(), InputMode.INSTANCE.m5908getTouchaOaMEAU());
            Strings.Companion companion = Strings.INSTANCE;
            final String searchSemantics = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_search_bar_search), $composer3, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            final String suggestionsAvailableSemantics = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_suggestions_available), $composer3, 0);
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle5.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                $this$takeOrElse_u2dDxMtmZc$iv = colors4.m3039textColorXeAY9LY$material3(enabled3, false, InputField$lambda$5(stateCollectIsFocusedAsState));
            }
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle5.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            TextStyle textStyle6 = textStyle5;
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
            Composer composer$iv = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, 683737348, "CC(remember):Effects.kt#9igjgp");
            Composer $this$cache$iv$iv = $composer3;
            Object it$iv$iv = $this$cache$iv$iv.rememberedValue();
            int $dirty4 = $dirty3;
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer$iv);
                $this$cache$iv$iv.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            final CoroutineScope coroutineScope = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -470498121, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(isInTouchMode) | (($dirty2 & 112) == 32) | $composer3.changedInstance(coroutineScope) | $composer3.changedInstance(focusManager);
            Composer $this$cache$iv2 = $composer3;
            Object it$iv2 = $this$cache$iv2.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                searchBarState2 = searchBarState;
                value$iv = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                        return m2780invokeZmokQxo(keyEvent.m6242unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m2780invokeZmokQxo(android.view.KeyEvent it) {
                        boolean expandOnDownKey = (isInTouchMode || SearchBarKt.isExpanded(searchBarState2)) ? false : true;
                        if (!expandOnDownKey || !Key.m5945equalsimpl0(KeyEvent_androidKt.m6253getKeyZmokQxo(it), Key.INSTANCE.m6018getDirectionDownEK5gGoQ())) {
                            if (SearchBarKt.isExpanded(searchBarState2) && Key.m5945equalsimpl0(KeyEvent_androidKt.m6253getKeyZmokQxo(it), Key.INSTANCE.m6018getDirectionDownEK5gGoQ())) {
                                focusManager.mo4741moveFocus3ESFkO8(FocusDirection.INSTANCE.m4731getDowndhqQ8s());
                                return true;
                            }
                            return false;
                        }
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(searchBarState2, null), 3, null);
                        return true;
                    }

                    /* JADX INFO: renamed from: androidx.compose.material3.SearchBarDefaults$InputField$1$1$1, reason: invalid class name */
                    /* JADX INFO: compiled from: SearchBar.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.material3.SearchBarDefaults$InputField$1$1$1", f = "SearchBar.kt", i = {}, l = {1346}, m = "invokeSuspend", n = {}, s = {})
                    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ SearchBarState $searchBarState;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        AnonymousClass1(SearchBarState searchBarState, Continuation<? super AnonymousClass1> continuation) {
                            super(2, continuation);
                            this.$searchBarState = searchBarState;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new AnonymousClass1(this.$searchBarState, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object $result) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch (this.label) {
                                case 0:
                                    ResultKt.throwOnFailure($result);
                                    this.label = 1;
                                    if (this.$searchBarState.animateToExpanded(this) == coroutine_suspended) {
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
                };
                $this$cache$iv2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv2;
                searchBarState2 = searchBarState;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM869sizeInqDBjuR0$default = SizeKt.m869sizeInqDBjuR0$default(KeyInputModifierKt.onPreviewKeyEvent(modifier3, (Function1) value$iv), SearchBarKt.getSearchBarMinWidth(), InputFieldHeight, SearchBarKt.SearchBarMaxWidth, 0.0f, 8, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -470467571, "CC(remember):SearchBar.kt#9igjgp");
            Modifier modifier5 = modifier3;
            boolean invalid$iv2 = $composer3.changed(isInTouchMode) | $composer3.changedInstance(coroutineScope) | (($dirty2 & 112) == 32);
            Composer $this$cache$iv3 = $composer3;
            Object value$iv4 = $this$cache$iv3.rememberedValue();
            if (invalid$iv2 || value$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv4 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchBarDefaults.InputField$lambda$9$lambda$8(isInTouchMode, coroutineScope, searchBarState2, (FocusState) obj);
                    }
                };
                $this$cache$iv3.updateRememberedValue(value$iv4);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierM869sizeInqDBjuR0$default, (Function1) value$iv4);
            ComposerKt.sourceInformationMarkerStart($composer3, -470460134, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv3 = $composer3.changed(searchSemantics) | (($dirty2 & 112) == 32) | $composer3.changed(suggestionsAvailableSemantics);
            Composer $this$cache$iv4 = $composer3;
            Object it$iv3 = $this$cache$iv4.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchBarDefaults.InputField$lambda$11$lambda$10(searchSemantics, searchBarState2, suggestionsAvailableSemantics, (SemanticsPropertyReceiver) obj);
                    }
                };
                $this$cache$iv4.updateRememberedValue(value$iv5);
                it$iv3 = value$iv5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierOnFocusChanged, false, (Function1) it$iv3, 1, null);
            TextFieldLineLimits.SingleLine singleLine = TextFieldLineLimits.SingleLine.INSTANCE;
            Composer $composer4 = $composer3;
            SolidColor solidColor = new SolidColor(colors4.m2989cursorColorvNxB06k$material3(false), null);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m7515getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
            ComposerKt.sourceInformationMarkerStart($composer4, -470441422, "CC(remember):SearchBar.kt#9igjgp");
            int $dirty5 = $dirty2;
            boolean invalid$iv4 = (($dirty2 & 896) == 256) | (($dirty2 & 14) == 4);
            Object it$iv4 = $composer4.rememberedValue();
            if (invalid$iv4 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new KeyboardActionHandler() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda10
                    @Override // androidx.compose.foundation.text.input.KeyboardActionHandler
                    public final void onKeyboardAction(Function0 function0) {
                        function1.invoke(textFieldState.getText().toString());
                    }
                };
                $composer4.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv4;
            }
            KeyboardActionHandler keyboardActionHandler = (KeyboardActionHandler) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
            TextFieldLineLimits.SingleLine singleLine2 = TextFieldLineLimits.SingleLine.INSTANCE;
            MutableInteractionSource mutableInteractionSource = interactionSource5;
            if (function13 == null) {
                $composer4.startReplaceGroup(-1698078028);
                $composer4.endReplaceGroup();
                composableLambdaRememberComposableLambda = null;
            } else {
                $composer4.startReplaceGroup(-1698078027);
                ComposerKt.sourceInformation($composer4, "*1392@65419L64");
                final Function2<? super Composer, ? super Integer, Unit> function23 = function13;
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(759038428, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$5$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer5, int $changed2) {
                        Function0<ComposeUiNode> function0;
                        ComposerKt.sourceInformation($composer5, "C1392@65421L60:SearchBar.kt#uh7d8r");
                        if ($composer5.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(759038428, $changed2, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous> (SearchBar.kt:1392)");
                            }
                            Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(Modifier.INSTANCE, SearchBarKt.SearchBarIconOffsetX, 0.0f, 2, null);
                            Function2<Composer, Integer, Unit> function24 = function23;
                            ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                            int $changed$iv$iv = (6 << 3) & 112;
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
                            int i16 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                            int i17 = ((6 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1668617416, "C1392@65470L9:SearchBar.kt#uh7d8r");
                            function24.invoke($composer5, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer5.skipToGroupEnd();
                    }
                }, $composer4, 54);
                $composer4.endReplaceGroup();
            }
            if (function14 == null) {
                $composer4.startReplaceGroup(-1697869615);
                $composer4.endReplaceGroup();
                composableLambdaRememberComposableLambda2 = null;
            } else {
                $composer4.startReplaceGroup(-1697869614);
                ComposerKt.sourceInformation($composer4, "*1396@65630L66");
                final Function2<? super Composer, ? super Integer, Unit> function24 = function14;
                composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(55642171, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$6$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer5, int $changed2) {
                        Function0<ComposeUiNode> function0;
                        ComposerKt.sourceInformation($composer5, "C1396@65632L62:SearchBar.kt#uh7d8r");
                        if (!$composer5.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer5.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(55642171, $changed2, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous> (SearchBar.kt:1396)");
                        }
                        Modifier.Companion companion3 = Modifier.INSTANCE;
                        float arg0$iv = SearchBarKt.SearchBarIconOffsetX;
                        Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(companion3, Dp.m7905constructorimpl(-arg0$iv), 0.0f, 2, null);
                        Function2<Composer, Integer, Unit> function25 = function24;
                        ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv = (6 << 3) & 112;
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
                        int i16 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i17 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer5, -1401072488, "C1396@65682L10:SearchBar.kt#uh7d8r");
                        function25.invoke($composer5, 0);
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
                }, $composer4, 54);
                $composer4.endReplaceGroup();
            }
            Shape shape5 = shape4;
            TextFieldColors colors5 = colors4;
            Function2<? super Composer, ? super Integer, Unit> function25 = function17;
            boolean enabled5 = enabled3;
            OutputTransformation outputTransformation5 = outputTransformation3;
            boolean readOnly4 = readOnly3;
            InputTransformation inputTransformation4 = inputTransformation3;
            ScrollState scrollState4 = scrollState3;
            BasicTextFieldKt.BasicTextField(textFieldState, modifierSemantics$default, enabled5, readOnly4, inputTransformation4, mergedTextStyle, keyboardOptions, keyboardActionHandler, singleLine, (Function2<? super Density, ? super Function0<TextLayoutResult>, Unit>) null, interactionSource5, solidColor, outputTransformation5, textFieldDefaults.decorator(textFieldState, enabled5, singleLine2, outputTransformation5, mutableInteractionSource, null, null, function21, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, function22, function25, null, false, colors5, TextFieldDefaults.m3042contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null), ComposableLambdaKt.rememberComposableLambda(1500441906, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.7
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer5, int $changed2) {
                    ComposerKt.sourceInformation($composer5, "C1411@66449L7,1404@66027L460,1413@66512L63:SearchBar.kt#uh7d8r");
                    if (!$composer5.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer5.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1500441906, $changed2, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.kt:1403)");
                    }
                    BoxKt.Box(TextFieldImplKt.textFieldBackground(Modifier.INSTANCE, new SearchBarKt$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(SingleValueAnimationKt.m135animateColorAsStateeuL9pac(colors4.m2987containerColorXeAY9LY$material3(enabled3, false, SearchBarDefaults.InputField$lambda$5(stateCollectIsFocusedAsState)), MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer5, 6), null, null, $composer5, 0, 12)) { // from class: androidx.compose.material3.SearchBarDefaults.InputField.7.1
                        @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                        public Object get() {
                            return ((State) this.receiver).getValue();
                        }
                    }), shape4), $composer5, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer4, 54), $composer4, ($dirty5 & 14) | 384 | (($dirty5 >> 9) & 112) | ($dirty4 & 7168) | ($dirty5 & 29360128), ($dirty4 & 14) | 14155776 | ($dirty4 & 112) | (($dirty4 >> 6) & 57344), 12384), scrollState4, $composer4, ($dirty5 & 14) | 102236160 | (($dirty5 >> 6) & 896) | (($dirty5 >> 6) & 7168) | (($dirty4 << 6) & 57344), (($dirty4 >> 3) & 896) | ($dirty4 & 57344), 512);
            $composer2 = $composer4;
            MutableInteractionSource mutableInteractionSource2 = interactionSource5;
            ComposerKt.sourceInformationMarkerStart($composer2, -470368070, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv5 = (($dirty5 & 112) == 32) | $composer2.changedInstance(coroutineScope);
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv5 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                searchBarState3 = searchBarState;
                Object value$iv6 = new Function0() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SearchBarDefaults.InputField$lambda$17$lambda$16(searchBarState3, coroutineScope);
                    }
                };
                $composer2.updateRememberedValue(value$iv6);
                it$iv5 = value$iv6;
            } else {
                searchBarState3 = searchBarState;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SearchBarKt.DetectClickFromInteractionSource(mutableInteractionSource2, (Function0) it$iv5, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -470359162, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv6 = (($dirty5 & 112) == 32) | (($dirty5 & 14) == 4) | $composer2.changed(stateCollectIsFocusedAsState) | $composer2.changedInstance(coroutineScope);
            Object it$iv6 = $composer2.rememberedValue();
            if (invalid$iv6 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                textFieldState2 = textFieldState;
                state = stateCollectIsFocusedAsState;
                Object value$iv7 = (Function2) new SearchBarDefaults$InputField$9$1(searchBarState3, textFieldState, coroutineScope, stateCollectIsFocusedAsState, null);
                $composer2.updateRememberedValue(value$iv7);
                it$iv6 = value$iv7;
            } else {
                state = stateCollectIsFocusedAsState;
                textFieldState2 = textFieldState;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(searchBarState3, textFieldState2, (Function2) it$iv6, $composer2, (($dirty5 >> 3) & 14) | (($dirty5 << 3) & 112));
            boolean shouldClearFocusOnCollapse = !SearchBarKt.isExpanded(searchBarState3) && InputField$lambda$5(state) && isInTouchMode;
            Boolean boolValueOf = Boolean.valueOf(SearchBarKt.isExpanded(searchBarState3));
            ComposerKt.sourceInformationMarkerStart($composer2, -470331497, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv7 = $composer2.changed(shouldClearFocusOnCollapse) | $composer2.changedInstance(focusManager);
            Object it$iv7 = $composer2.rememberedValue();
            if (invalid$iv7 || it$iv7 == Composer.INSTANCE.getEmpty()) {
                Object value$iv8 = (Function2) new SearchBarDefaults$InputField$10$1(shouldClearFocusOnCollapse, focusManager, null);
                $composer2.updateRememberedValue(value$iv8);
                it$iv7 = value$iv8;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv7, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled2 = enabled5;
            readOnly2 = readOnly4;
            outputTransformation2 = outputTransformation5;
            scrollState2 = scrollState4;
            colors2 = colors5;
            interactionSource2 = interactionSource3;
            modifier2 = modifier5;
            textStyle3 = textStyle6;
            function9 = function21;
            function10 = function13;
            function11 = function14;
            function12 = function22;
            function8 = function25;
            shape2 = shape5;
            inputTransformation2 = inputTransformation4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            function8 = function6;
            outputTransformation2 = outputTransformation;
            scrollState2 = scrollState;
            shape2 = shape;
            colors2 = colors;
            interactionSource2 = interactionSource;
            enabled2 = z;
            readOnly2 = z2;
            textStyle3 = textStyle2;
            function9 = function7;
            function10 = function3;
            function11 = function4;
            function12 = function5;
            inputTransformation2 = inputTransformation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarDefaults.InputField$lambda$20(this.f$0, textFieldState, searchBarState, function1, modifier2, enabled2, readOnly2, textStyle3, function9, function10, function11, function12, function8, inputTransformation2, outputTransformation2, scrollState2, shape2, colors2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean InputField$lambda$5(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final Unit InputField$lambda$9$lambda$8(boolean $isInTouchMode, CoroutineScope $coroutineScope, SearchBarState $searchBarState, FocusState it) {
        if (it.isFocused() && $isInTouchMode) {
            BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new SearchBarDefaults$InputField$2$1$1($searchBarState, null), 3, null);
        }
        return Unit.INSTANCE;
    }

    static final Unit InputField$lambda$11$lambda$10(String $searchSemantics, SearchBarState $searchBarState, String $suggestionsAvailableSemantics, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $searchSemantics);
        if (SearchBarKt.isExpanded($searchBarState)) {
            SemanticsPropertiesKt.setStateDescription($this$semantics, $suggestionsAvailableSemantics);
        }
        return Unit.INSTANCE;
    }

    static final Unit InputField$lambda$17$lambda$16(SearchBarState $searchBarState, CoroutineScope $coroutineScope) {
        if (!SearchBarKt.isExpanded($searchBarState)) {
            BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new SearchBarDefaults$InputField$8$1$1($searchBarState, null), 3, null);
        }
        return Unit.INSTANCE;
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 23391. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    public final void InputField(androidx.compose.foundation.text.input.TextFieldState r110, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r111, boolean r112, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r113, androidx.compose.ui.Modifier r114, boolean r115, boolean r116, androidx.compose.ui.text.TextStyle r117, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r118, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r119, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r120, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r121, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r122, androidx.compose.foundation.text.input.InputTransformation r123, androidx.compose.foundation.text.input.OutputTransformation r124, androidx.compose.foundation.ScrollState r125, androidx.compose.ui.graphics.Shape r126, androidx.compose.material3.TextFieldColors r127, androidx.compose.foundation.interaction.MutableInteractionSource r128, androidx.compose.runtime.Composer r129, int r130, int r131, int r132) {
        /*
            Method dump skipped, instruction units count: 2339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBarDefaults.InputField(androidx.compose.foundation.text.input.TextFieldState, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.foundation.text.input.InputTransformation, androidx.compose.foundation.text.input.OutputTransformation, androidx.compose.foundation.ScrollState, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    static final Unit InputField$lambda$25$lambda$24(Function1 $onExpandedChange, FocusState it) {
        if (it.isFocused()) {
            $onExpandedChange.invoke(true);
        }
        return Unit.INSTANCE;
    }

    static final Unit InputField$lambda$27$lambda$26(String $searchSemantics, boolean $expanded, String $suggestionsAvailableSemantics, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $searchSemantics);
        if ($expanded) {
            SemanticsPropertiesKt.setStateDescription($this$semantics, $suggestionsAvailableSemantics);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:213:0x0433  */
    /* JADX WARN: Code duplicated, block: B:214:0x0435  */
    /* JADX WARN: Code duplicated, block: B:217:0x0448  */
    /* JADX WARN: Code duplicated, block: B:220:0x0453  */
    /* JADX WARN: Code duplicated, block: B:224:0x04ed  */
    /* JADX WARN: Code duplicated, block: B:225:0x04ef  */
    /* JADX WARN: Code duplicated, block: B:228:0x04f5  */
    /* JADX WARN: Code duplicated, block: B:229:0x04f7  */
    /* JADX WARN: Code duplicated, block: B:232:0x0503  */
    /* JADX WARN: Code duplicated, block: B:235:0x050e  */
    /* JADX WARN: Code duplicated, block: B:238:0x05b3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:240:0x05b8  */
    /* JADX WARN: Code duplicated, block: B:243:0x05da  */
    /* JADX WARN: Code duplicated, block: B:246:0x05e3  */
    /* JADX WARN: Code duplicated, block: B:249:0x0604  */
    public final void InputField(final String query, final Function1<? super String, Unit> function1, final Function1<? super String, Unit> function2, final boolean expanded, final Function1<? super Boolean, Unit> function3, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, TextFieldColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier modifier2;
        boolean z;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final TextFieldColors colors2;
        final MutableInteractionSource interactionSource2;
        final boolean enabled2;
        final Modifier modifier3;
        Modifier.Companion modifier4;
        Composer $composer3;
        TextFieldColors colors3;
        final TextFieldColors colors4;
        boolean enabled3;
        Function2<? super Composer, ? super Integer, Unit> function10;
        int $dirty1;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Function2<? super Composer, ? super Integer, Unit> function12;
        MutableInteractionSource interactionSource3;
        MutableInteractionSource interactionSource4;
        Object value$iv;
        boolean enabled4;
        boolean z2;
        boolean invalid$iv;
        Object value$iv2;
        FocusRequester focusRequester;
        boolean z3;
        boolean z4;
        boolean invalid$iv2;
        Composer $this$cache$iv;
        Object it$iv;
        boolean shouldClearFocus;
        boolean invalid$iv3;
        Object it$iv2;
        Object value$iv3;
        Composer $composer4 = $composer.startRestartGroup(748018311);
        ComposerKt.sourceInformation($composer4, "C(InputField)N(query,onQueryChange,onSearch,expanded,onExpandedChange,modifier,enabled,placeholder,leadingIcon,trailingIcon,colors,interactionSource)1659@79407L25,1660@79468L29,1661@79543L7,1663@79582L34,1664@79661L39,1667@79753L7,1682@80316L44,1683@80392L227,1691@80722L7,1694@80976L19,1697@81105L1935,1671@79881L3170,1737@83138L320,1737@83113L345:SearchBar.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty2 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer4.changed(query) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer4.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer4.changed(expanded) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer4.changedInstance(function3) ? 16384 : 8192;
        }
        int i2 = i & 32;
        if (i2 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            modifier2 = modifier;
            $dirty |= $composer4.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 64;
        if (i3 != 0) {
            $dirty |= 1572864;
            z = enabled;
        } else if (($changed & 1572864) == 0) {
            z = enabled;
            $dirty |= $composer4.changed(z) ? 1048576 : 524288;
        } else {
            z = enabled;
        }
        int i4 = i & 128;
        if (i4 != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer4.changedInstance(function4) ? 8388608 : 4194304;
        }
        int i5 = i & 256;
        if (i5 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer4.changedInstance(function5) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i6 = i & 512;
        if (i6 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer4.changedInstance(function6) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty2 |= ((i & 1024) == 0 && $composer4.changed(colors)) ? 4 : 2;
        }
        int i7 = i & 2048;
        if (i7 != 0) {
            $dirty2 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty2 |= $composer4.changed(interactionSource) ? 32 : 16;
        }
        if ((i & 4096) != 0) {
            $dirty2 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty2 |= $composer4.changed(this) ? 256 : 128;
        }
        if ($composer4.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty2 & 147) == 146) ? false : true, $dirty & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "1653@79148L18");
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled5 = i3 != 0 ? true : z;
                Function2<? super Composer, ? super Integer, Unit> function13 = i4 != 0 ? null : function4;
                Function2<? super Composer, ? super Integer, Unit> function14 = i5 != 0 ? null : function5;
                Function2<? super Composer, ? super Integer, Unit> function15 = i6 != 0 ? null : function6;
                if ((i & 1024) != 0) {
                    colors3 = m2779inputFieldColorsJVEmHcM(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, ($dirty2 << 3) & 7168, 8388607);
                    $composer3 = $composer4;
                    $dirty2 &= -15;
                } else {
                    $composer3 = $composer4;
                    colors3 = colors;
                }
                if (i7 != 0) {
                    Function2<? super Composer, ? super Integer, Unit> function16 = function14;
                    colors4 = colors3;
                    enabled3 = enabled5;
                    function10 = function16;
                    Function2<? super Composer, ? super Integer, Unit> function17 = function15;
                    $dirty1 = $dirty2;
                    function11 = function13;
                    function12 = function17;
                    interactionSource3 = null;
                } else {
                    Function2<? super Composer, ? super Integer, Unit> function18 = function14;
                    colors4 = colors3;
                    enabled3 = enabled5;
                    function10 = function18;
                    Function2<? super Composer, ? super Integer, Unit> function19 = function15;
                    $dirty1 = $dirty2;
                    function11 = function13;
                    function12 = function19;
                    interactionSource3 = interactionSource;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 1024) != 0) {
                    $dirty2 &= -15;
                }
                function10 = function5;
                colors4 = colors;
                interactionSource3 = interactionSource;
                $dirty1 = $dirty2;
                modifier4 = modifier2;
                function11 = function4;
                $composer3 = $composer4;
                enabled3 = z;
                function12 = function6;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(748018311, $dirty, $dirty1, "androidx.compose.material3.SearchBarDefaults.InputField (SearchBar.kt:1655)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(1923851092);
                ComposerKt.sourceInformation($composer3, "1657@79326L39");
                ComposerKt.sourceInformationMarkerStart($composer3, 1586080366, "CC(remember):SearchBar.kt#9igjgp");
                Composer $this$cache$iv2 = $composer3;
                Object it$iv3 = $this$cache$iv2.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv3;
                }
                interactionSource4 = (MutableInteractionSource) value$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1586079715);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            final MutableInteractionSource interactionSource5 = interactionSource4;
            final boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource4, $composer3, 0).getValue().booleanValue();
            ComposerKt.sourceInformationMarkerStart($composer3, 1586084900, "CC(remember):SearchBar.kt#9igjgp");
            Composer $this$cache$iv3 = $composer3;
            Object it$iv4 = $this$cache$iv3.rememberedValue();
            final Function2<? super Composer, ? super Integer, Unit> function20 = function10;
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv = new FocusRequester();
                $this$cache$iv3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv4;
            }
            FocusRequester focusRequester2 = (FocusRequester) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ProvidableCompositionLocal<FocusManager> localFocusManager = CompositionLocalsKt.getLocalFocusManager();
            final Function2<? super Composer, ? super Integer, Unit> function21 = function12;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localFocusManager);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FocusManager focusManager = (FocusManager) objConsume;
            Strings.Companion companion = Strings.INSTANCE;
            final String searchSemantics = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_search_bar_search), $composer3, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            final String suggestionsAvailableSemantics = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_suggestions_available), $composer3, 0);
            ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer3.consume(localTextStyle);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            long $this$takeOrElse_u2dDxMtmZc$iv = ((TextStyle) objConsume2).m7358getColor0d7_KjU();
            if (($this$takeOrElse_u2dDxMtmZc$iv != 16 ? 1 : 0) == 0) {
                $this$takeOrElse_u2dDxMtmZc$iv = colors4.m3039textColorXeAY9LY$material3(enabled3, false, focused);
            }
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            Modifier modifier5 = modifier4;
            Modifier modifierFocusRequester = FocusRequesterModifierKt.focusRequester(SizeKt.m869sizeInqDBjuR0$default(modifier4, SearchBarKt.getSearchBarMinWidth(), InputFieldHeight, SearchBarKt.SearchBarMaxWidth, 0.0f, 8, null), focusRequester2);
            ComposerKt.sourceInformationMarkerStart($composer3, 1586112051, "CC(remember):SearchBar.kt#9igjgp");
            boolean invalid$iv4 = (57344 & $dirty) == 16384;
            Composer $this$cache$iv4 = $composer3;
            boolean invalid$iv5 = invalid$iv4;
            Object value$iv4 = $this$cache$iv4.rememberedValue();
            if (invalid$iv5) {
                enabled4 = enabled3;
            } else {
                enabled4 = enabled3;
                if (value$iv4 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierOnFocusChanged = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester, (Function1) value$iv4);
                ComposerKt.sourceInformationMarkerStart($composer3, 1586114666, "CC(remember):SearchBar.kt#9igjgp");
                boolean zChanged = $composer3.changed(searchSemantics);
                if (($dirty & 7168) == 2048) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                invalid$iv = zChanged | z2 | $composer3.changed(suggestionsAvailableSemantics);
                Composer $this$cache$iv5 = $composer3;
                value$iv2 = $this$cache$iv5.rememberedValue();
                if (invalid$iv) {
                    focusRequester = focusRequester2;
                } else {
                    focusRequester = focusRequester2;
                    if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierOnFocusChanged, false, (Function1) value$iv2, 1, null);
                    ProvidableCompositionLocal<TextStyle> localTextStyle2 = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume3 = $composer3.consume(localTextStyle2);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    TextStyle textStyleMerge = ((TextStyle) objConsume3).merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                    SolidColor solidColor = new SolidColor(colors4.m2989cursorColorvNxB06k$material3(false), null);
                    KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m7515getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 1586133146, "CC(remember):SearchBar.kt#9igjgp");
                    if (($dirty & 896) == 256) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (($dirty & 14) == 4) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    invalid$iv2 = z3 | z4;
                    $this$cache$iv = $composer3;
                    it$iv = $this$cache$iv.rememberedValue();
                    if (invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv5 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return SearchBarDefaults.InputField$lambda$42$lambda$41(function2, query, (KeyboardActionScope) obj);
                            }
                        };
                        $this$cache$iv.updateRememberedValue(value$iv5);
                        it$iv = value$iv5;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    final Function2<? super Composer, ? super Integer, Unit> function22 = function11;
                    final boolean enabled6 = enabled4;
                    int $dirty3 = $dirty;
                    TextFieldColors colors5 = colors4;
                    boolean enabled7 = enabled4;
                    Composer $composer5 = $composer3;
                    BasicTextFieldKt.BasicTextField(query, function1, modifierSemantics$default, enabled7, false, textStyleMerge, keyboardOptions, new KeyboardActions(null, null, null, null, (Function1) it$iv, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource5, (Brush) solidColor, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-813926844, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function23, Composer composer, Integer num) {
                            invoke((Function2<? super Composer, ? super Integer, Unit>) function23, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Function2<? super Composer, ? super Integer, Unit> function23, Composer $composer6, int $changed2) {
                            Function2<? super Composer, ? super Integer, Unit> function24;
                            ComposableLambda composableLambda;
                            Function2 trailing;
                            ComposerKt.sourceInformation($composer6, "CN(innerTextField)1714@82051L15,1717@82234L765,1698@81163L1859:SearchBar.kt#uh7d8r");
                            int $dirty4 = $changed2;
                            if (($changed2 & 6) == 0) {
                                function24 = function23;
                                $dirty4 |= $composer6.changedInstance(function24) ? 4 : 2;
                            } else {
                                function24 = function23;
                            }
                            if ($composer6.shouldExecute(($dirty4 & 19) != 18, $dirty4 & 1)) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-813926844, $dirty4, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.kt:1698)");
                                }
                                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                                int $dirty5 = $dirty4;
                                String str = query;
                                boolean z5 = enabled6;
                                VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                                MutableInteractionSource mutableInteractionSource = interactionSource5;
                                Function2<Composer, Integer, Unit> function25 = function22;
                                final Function2<Composer, Integer, Unit> function26 = function20;
                                if (function26 == null) {
                                    $composer6.startReplaceGroup(1691265870);
                                    $composer6.endReplaceGroup();
                                    composableLambda = null;
                                } else {
                                    $composer6.startReplaceGroup(1691265871);
                                    ComposerKt.sourceInformation($composer6, "*1708@81676L64");
                                    ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1718822048, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$23$1$1
                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                            invoke(composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer $composer7, int $changed3) {
                                            Function0<ComposeUiNode> function0;
                                            ComposerKt.sourceInformation($composer7, "C1708@81678L60:SearchBar.kt#uh7d8r");
                                            if ($composer7.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(1718822048, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:1708)");
                                                }
                                                Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(Modifier.INSTANCE, SearchBarKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                                Function2<Composer, Integer, Unit> function27 = function26;
                                                ComposerKt.sourceInformationMarkerStart($composer7, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                                int $changed$iv$iv = (6 << 3) & 112;
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
                                                int i8 = ($changed$iv$iv$iv >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer7, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                int i9 = ((6 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer7, 1796932852, "C1708@81727L9:SearchBar.kt#uh7d8r");
                                                function27.invoke($composer7, 0);
                                                ComposerKt.sourceInformationMarkerEnd($composer7);
                                                ComposerKt.sourceInformationMarkerEnd($composer7);
                                                $composer7.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer7);
                                                ComposerKt.sourceInformationMarkerEnd($composer7);
                                                ComposerKt.sourceInformationMarkerEnd($composer7);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer7.skipToGroupEnd();
                                        }
                                    }, $composer6, 54);
                                    $composer6.endReplaceGroup();
                                    composableLambda = composableLambdaRememberComposableLambda;
                                }
                                final Function2<Composer, Integer, Unit> function27 = function21;
                                if (function27 == null) {
                                    $composer6.startReplaceGroup(1691490155);
                                    $composer6.endReplaceGroup();
                                    trailing = null;
                                } else {
                                    $composer6.startReplaceGroup(1691490156);
                                    ComposerKt.sourceInformation($composer6, "*1712@81903L66");
                                    Function2 trailing2 = ComposableLambdaKt.rememberComposableLambda(-305306680, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$23$2$1
                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                            invoke(composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer $composer7, int $changed3) {
                                            Function0<ComposeUiNode> function0;
                                            ComposerKt.sourceInformation($composer7, "C1712@81905L62:SearchBar.kt#uh7d8r");
                                            if (!$composer7.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                                $composer7.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-305306680, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:1712)");
                                            }
                                            Modifier.Companion companion3 = Modifier.INSTANCE;
                                            float arg0$iv = SearchBarKt.SearchBarIconOffsetX;
                                            Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(companion3, Dp.m7905constructorimpl(-arg0$iv), 0.0f, 2, null);
                                            Function2<Composer, Integer, Unit> function28 = function27;
                                            ComposerKt.sourceInformationMarkerStart($composer7, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                            int $changed$iv$iv = (6 << 3) & 112;
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
                                            int i8 = ($changed$iv$iv$iv >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer7, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            int i9 = ((6 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer7, 1336107703, "C1712@81955L10:SearchBar.kt#uh7d8r");
                                            function28.invoke($composer7, 0);
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
                                    }, $composer6, 54);
                                    trailing = trailing2;
                                    $composer6.endReplaceGroup();
                                }
                                Shape inputFieldShape = SearchBarDefaults.INSTANCE.getInputFieldShape($composer6, 6);
                                TextFieldColors textFieldColors = colors4;
                                PaddingValues paddingValuesM3042contentPaddingWithoutLabela9UjIt4$default = TextFieldDefaults.m3042contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                                final TextFieldColors textFieldColors2 = colors4;
                                final boolean z6 = enabled6;
                                final boolean z7 = focused;
                                textFieldDefaults.DecorationBox(str, function24, z5, true, none, mutableInteractionSource, false, null, function25, composableLambda, trailing, null, null, null, inputFieldShape, textFieldColors, paddingValuesM3042contentPaddingWithoutLabela9UjIt4$default, ComposableLambdaKt.rememberComposableLambda(-1197464330, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23.3
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer7, int $changed3) {
                                        ComposerKt.sourceInformation($composer7, "C1726@82767L7,1719@82317L492,1729@82927L15,1728@82838L135:SearchBar.kt#uh7d8r");
                                        if (!$composer7.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                            $composer7.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1197464330, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous> (SearchBar.kt:1718)");
                                        }
                                        BoxKt.Box(TextFieldImplKt.textFieldBackground(Modifier.INSTANCE, new SearchBarKt$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(SingleValueAnimationKt.m135animateColorAsStateeuL9pac(textFieldColors2.m2987containerColorXeAY9LY$material3(z6, false, z7), MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer7, 6), null, null, $composer7, 0, 12)) { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23.3.1
                                            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                                            public Object get() {
                                                return ((State) this.receiver).getValue();
                                            }
                                        }), SearchBarDefaults.INSTANCE.getInputFieldShape($composer7, 6)), $composer7, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, $composer6, 54), $composer6, (($dirty5 << 3) & 112) | 27648, 113246208, 14528);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer6.skipToGroupEnd();
                        }
                    }, $composer3, 54), $composer5, ($dirty3 & 14) | 102236160 | ($dirty3 & 112) | (($dirty3 >> 9) & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                    $composer2 = $composer5;
                    if (expanded && focused) {
                        shouldClearFocus = true;
                    } else {
                        shouldClearFocus = false;
                    }
                    Boolean boolValueOf = Boolean.valueOf(expanded);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1586202631, "CC(remember):SearchBar.kt#9igjgp");
                    invalid$iv3 = $composer2.changed(shouldClearFocus) | $composer2.changedInstance(focusManager);
                    it$iv2 = $composer2.rememberedValue();
                    if (invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv6 = (Function2) new SearchBarDefaults$InputField$24$1(shouldClearFocus, focusManager, null);
                        $composer2.updateRememberedValue(value$iv6);
                        it$iv2 = value$iv6;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer2, ($dirty3 >> 9) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    enabled2 = enabled7;
                    interactionSource2 = interactionSource3;
                    modifier3 = modifier5;
                    colors2 = colors5;
                    function7 = function22;
                    function8 = function20;
                    function9 = function21;
                }
                value$iv2 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchBarDefaults.InputField$lambda$40$lambda$39(searchSemantics, expanded, suggestionsAvailableSemantics, (SemanticsPropertyReceiver) obj);
                    }
                };
                $this$cache$iv5.updateRememberedValue(value$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged, false, (Function1) value$iv2, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle3 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume4 = $composer3.consume(localTextStyle3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                TextStyle textStyleMerge2 = ((TextStyle) objConsume4).merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                SolidColor solidColor2 = new SolidColor(colors4.m2989cursorColorvNxB06k$material3(false), null);
                KeyboardOptions keyboardOptions2 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m7515getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1586133146, "CC(remember):SearchBar.kt#9igjgp");
                if (($dirty & 896) == 256) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (($dirty & 14) == 4) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                invalid$iv2 = z3 | z4;
                $this$cache$iv = $composer3;
                it$iv = $this$cache$iv.rememberedValue();
                if (invalid$iv2) {
                    Object value$iv7 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SearchBarDefaults.InputField$lambda$42$lambda$41(function2, query, (KeyboardActionScope) obj);
                        }
                    };
                    $this$cache$iv.updateRememberedValue(value$iv7);
                    it$iv = value$iv7;
                } else {
                    Object value$iv8 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SearchBarDefaults.InputField$lambda$42$lambda$41(function2, query, (KeyboardActionScope) obj);
                        }
                    };
                    $this$cache$iv.updateRememberedValue(value$iv8);
                    it$iv = value$iv8;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Function2<? super Composer, ? super Integer, Unit> function23 = function11;
                final boolean enabled8 = enabled4;
                int $dirty4 = $dirty;
                TextFieldColors colors6 = colors4;
                boolean enabled9 = enabled4;
                Composer $composer6 = $composer3;
                BasicTextFieldKt.BasicTextField(query, function1, modifierSemantics$default2, enabled9, false, textStyleMerge2, keyboardOptions2, new KeyboardActions(null, null, null, null, (Function1) it$iv, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource5, (Brush) solidColor2, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-813926844, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function24, Composer composer, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function24, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function24, Composer $composer7, int $changed2) {
                        Function2<? super Composer, ? super Integer, Unit> function25;
                        ComposableLambda composableLambda;
                        Function2 trailing;
                        ComposerKt.sourceInformation($composer7, "CN(innerTextField)1714@82051L15,1717@82234L765,1698@81163L1859:SearchBar.kt#uh7d8r");
                        int $dirty5 = $changed2;
                        if (($changed2 & 6) == 0) {
                            function25 = function24;
                            $dirty5 |= $composer7.changedInstance(function25) ? 4 : 2;
                        } else {
                            function25 = function24;
                        }
                        if ($composer7.shouldExecute(($dirty5 & 19) != 18, $dirty5 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-813926844, $dirty5, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.kt:1698)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            int $dirty6 = $dirty5;
                            String str = query;
                            boolean z5 = enabled8;
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            MutableInteractionSource mutableInteractionSource = interactionSource5;
                            Function2<Composer, Integer, Unit> function26 = function23;
                            final Function2<? super Composer, ? super Integer, Unit> function27 = function20;
                            if (function27 == null) {
                                $composer7.startReplaceGroup(1691265870);
                                $composer7.endReplaceGroup();
                                composableLambda = null;
                            } else {
                                $composer7.startReplaceGroup(1691265871);
                                ComposerKt.sourceInformation($composer7, "*1708@81676L64");
                                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1718822048, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$23$1$1
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer8, int $changed3) {
                                        Function0<ComposeUiNode> function0;
                                        ComposerKt.sourceInformation($composer8, "C1708@81678L60:SearchBar.kt#uh7d8r");
                                        if ($composer8.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1718822048, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:1708)");
                                            }
                                            Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(Modifier.INSTANCE, SearchBarKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                            Function2<Composer, Integer, Unit> function28 = function27;
                                            ComposerKt.sourceInformationMarkerStart($composer8, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                            int $changed$iv$iv = (6 << 3) & 112;
                                            ComposerKt.sourceInformationMarkerStart($composer8, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer8, 0);
                                            CompositionLocalMap localMap$iv$iv = $composer8.getCurrentCompositionLocalMap();
                                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer8, modifier$iv);
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer8, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                            if (!($composer8.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer8.startReusableNode();
                                            if ($composer8.getInserting()) {
                                                function0 = constructor;
                                                $composer8.createNode(function0);
                                            } else {
                                                function0 = constructor;
                                                $composer8.useNode();
                                            }
                                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer8);
                                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                            }
                                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i8 = ($changed$iv$iv$iv >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer8, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            int i9 = ((6 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer8, 1796932852, "C1708@81727L9:SearchBar.kt#uh7d8r");
                                            function28.invoke($composer8, 0);
                                            ComposerKt.sourceInformationMarkerEnd($composer8);
                                            ComposerKt.sourceInformationMarkerEnd($composer8);
                                            $composer8.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer8);
                                            ComposerKt.sourceInformationMarkerEnd($composer8);
                                            ComposerKt.sourceInformationMarkerEnd($composer8);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer8.skipToGroupEnd();
                                    }
                                }, $composer7, 54);
                                $composer7.endReplaceGroup();
                                composableLambda = composableLambdaRememberComposableLambda;
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function28 = function21;
                            if (function28 == null) {
                                $composer7.startReplaceGroup(1691490155);
                                $composer7.endReplaceGroup();
                                trailing = null;
                            } else {
                                $composer7.startReplaceGroup(1691490156);
                                ComposerKt.sourceInformation($composer7, "*1712@81903L66");
                                Function2 trailing2 = ComposableLambdaKt.rememberComposableLambda(-305306680, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$23$2$1
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer8, int $changed3) {
                                        Function0<ComposeUiNode> function0;
                                        ComposerKt.sourceInformation($composer8, "C1712@81905L62:SearchBar.kt#uh7d8r");
                                        if (!$composer8.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                            $composer8.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-305306680, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:1712)");
                                        }
                                        Modifier.Companion companion3 = Modifier.INSTANCE;
                                        float arg0$iv = SearchBarKt.SearchBarIconOffsetX;
                                        Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(companion3, Dp.m7905constructorimpl(-arg0$iv), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function29 = function28;
                                        ComposerKt.sourceInformationMarkerStart($composer8, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                        int $changed$iv$iv = (6 << 3) & 112;
                                        ComposerKt.sourceInformationMarkerStart($composer8, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer8, 0);
                                        CompositionLocalMap localMap$iv$iv = $composer8.getCurrentCompositionLocalMap();
                                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer8, modifier$iv);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer8, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                        if (!($composer8.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        $composer8.startReusableNode();
                                        if ($composer8.getInserting()) {
                                            function0 = constructor;
                                            $composer8.createNode(function0);
                                        } else {
                                            function0 = constructor;
                                            $composer8.useNode();
                                        }
                                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer8);
                                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                        }
                                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i8 = ($changed$iv$iv$iv >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart($composer8, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i9 = ((6 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer8, 1336107703, "C1712@81955L10:SearchBar.kt#uh7d8r");
                                        function29.invoke($composer8, 0);
                                        ComposerKt.sourceInformationMarkerEnd($composer8);
                                        ComposerKt.sourceInformationMarkerEnd($composer8);
                                        $composer8.endNode();
                                        ComposerKt.sourceInformationMarkerEnd($composer8);
                                        ComposerKt.sourceInformationMarkerEnd($composer8);
                                        ComposerKt.sourceInformationMarkerEnd($composer8);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, $composer7, 54);
                                trailing = trailing2;
                                $composer7.endReplaceGroup();
                            }
                            Shape inputFieldShape = SearchBarDefaults.INSTANCE.getInputFieldShape($composer7, 6);
                            TextFieldColors textFieldColors = colors4;
                            PaddingValues paddingValuesM3042contentPaddingWithoutLabela9UjIt4$default = TextFieldDefaults.m3042contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                            final TextFieldColors textFieldColors2 = colors4;
                            final boolean z6 = enabled8;
                            final boolean z7 = focused;
                            textFieldDefaults.DecorationBox(str, function25, z5, true, none, mutableInteractionSource, false, null, function26, composableLambda, trailing, null, null, null, inputFieldShape, textFieldColors, paddingValuesM3042contentPaddingWithoutLabela9UjIt4$default, ComposableLambdaKt.rememberComposableLambda(-1197464330, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23.3
                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                    invoke(composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer $composer8, int $changed3) {
                                    ComposerKt.sourceInformation($composer8, "C1726@82767L7,1719@82317L492,1729@82927L15,1728@82838L135:SearchBar.kt#uh7d8r");
                                    if (!$composer8.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                        $composer8.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1197464330, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous> (SearchBar.kt:1718)");
                                    }
                                    BoxKt.Box(TextFieldImplKt.textFieldBackground(Modifier.INSTANCE, new SearchBarKt$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(SingleValueAnimationKt.m135animateColorAsStateeuL9pac(textFieldColors2.m2987containerColorXeAY9LY$material3(z6, false, z7), MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer8, 6), null, null, $composer8, 0, 12)) { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23.3.1
                                        @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                                        public Object get() {
                                            return ((State) this.receiver).getValue();
                                        }
                                    }), SearchBarDefaults.INSTANCE.getInputFieldShape($composer8, 6)), $composer8, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, $composer7, 54), $composer7, (($dirty6 << 3) & 112) | 27648, 113246208, 14528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer7.skipToGroupEnd();
                    }
                }, $composer3, 54), $composer6, ($dirty4 & 14) | 102236160 | ($dirty4 & 112) | (($dirty4 >> 9) & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                $composer2 = $composer6;
                if (expanded) {
                    shouldClearFocus = false;
                } else {
                    shouldClearFocus = false;
                }
                Boolean boolValueOf2 = Boolean.valueOf(expanded);
                ComposerKt.sourceInformationMarkerStart($composer2, 1586202631, "CC(remember):SearchBar.kt#9igjgp");
                invalid$iv3 = $composer2.changed(shouldClearFocus) | $composer2.changedInstance(focusManager);
                it$iv2 = $composer2.rememberedValue();
                if (invalid$iv3) {
                    Object value$iv9 = (Function2) new SearchBarDefaults$InputField$24$1(shouldClearFocus, focusManager, null);
                    $composer2.updateRememberedValue(value$iv9);
                    it$iv2 = value$iv9;
                } else {
                    Object value$iv10 = (Function2) new SearchBarDefaults$InputField$24$1(shouldClearFocus, focusManager, null);
                    $composer2.updateRememberedValue(value$iv10);
                    it$iv2 = value$iv10;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(boolValueOf2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer2, ($dirty4 >> 9) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                enabled2 = enabled9;
                interactionSource2 = interactionSource3;
                modifier3 = modifier5;
                colors2 = colors6;
                function7 = function23;
                function8 = function20;
                function9 = function21;
            }
            value$iv4 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SearchBarDefaults.InputField$lambda$38$lambda$37(function3, (FocusState) obj);
                }
            };
            $this$cache$iv4.updateRememberedValue(value$iv4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierOnFocusChanged2 = FocusChangedModifierKt.onFocusChanged(modifierFocusRequester, (Function1) value$iv4);
            ComposerKt.sourceInformationMarkerStart($composer3, 1586114666, "CC(remember):SearchBar.kt#9igjgp");
            boolean zChanged2 = $composer3.changed(searchSemantics);
            if (($dirty & 7168) == 2048) {
                z2 = true;
            } else {
                z2 = false;
            }
            invalid$iv = zChanged2 | z2 | $composer3.changed(suggestionsAvailableSemantics);
            Composer $this$cache$iv6 = $composer3;
            value$iv2 = $this$cache$iv6.rememberedValue();
            if (invalid$iv) {
                focusRequester = focusRequester2;
                if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierSemantics$default3 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged2, false, (Function1) value$iv2, 1, null);
                ProvidableCompositionLocal<TextStyle> localTextStyle4 = TextKt.getLocalTextStyle();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume5 = $composer3.consume(localTextStyle4);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                TextStyle textStyleMerge3 = ((TextStyle) objConsume5).merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
                SolidColor solidColor3 = new SolidColor(colors4.m2989cursorColorvNxB06k$material3(false), null);
                KeyboardOptions keyboardOptions3 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m7515getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1586133146, "CC(remember):SearchBar.kt#9igjgp");
                if (($dirty & 896) == 256) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (($dirty & 14) == 4) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                invalid$iv2 = z3 | z4;
                $this$cache$iv = $composer3;
                it$iv = $this$cache$iv.rememberedValue();
                if (invalid$iv2) {
                    Object value$iv11 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SearchBarDefaults.InputField$lambda$42$lambda$41(function2, query, (KeyboardActionScope) obj);
                        }
                    };
                    $this$cache$iv.updateRememberedValue(value$iv11);
                    it$iv = value$iv11;
                } else {
                    Object value$iv12 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SearchBarDefaults.InputField$lambda$42$lambda$41(function2, query, (KeyboardActionScope) obj);
                        }
                    };
                    $this$cache$iv.updateRememberedValue(value$iv12);
                    it$iv = value$iv12;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                final Function2<? super Composer, ? super Integer, Unit> function24 = function11;
                final boolean enabled10 = enabled4;
                int $dirty5 = $dirty;
                TextFieldColors colors7 = colors4;
                boolean enabled11 = enabled4;
                Composer $composer7 = $composer3;
                BasicTextFieldKt.BasicTextField(query, function1, modifierSemantics$default3, enabled11, false, textStyleMerge3, keyboardOptions3, new KeyboardActions(null, null, null, null, (Function1) it$iv, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource5, (Brush) solidColor3, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-813926844, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function25, Composer composer, Integer num) {
                        invoke((Function2<? super Composer, ? super Integer, Unit>) function25, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Function2<? super Composer, ? super Integer, Unit> function25, Composer $composer8, int $changed2) {
                        Function2<? super Composer, ? super Integer, Unit> function26;
                        ComposableLambda composableLambda;
                        Function2 trailing;
                        ComposerKt.sourceInformation($composer8, "CN(innerTextField)1714@82051L15,1717@82234L765,1698@81163L1859:SearchBar.kt#uh7d8r");
                        int $dirty6 = $changed2;
                        if (($changed2 & 6) == 0) {
                            function26 = function25;
                            $dirty6 |= $composer8.changedInstance(function26) ? 4 : 2;
                        } else {
                            function26 = function25;
                        }
                        if ($composer8.shouldExecute(($dirty6 & 19) != 18, $dirty6 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-813926844, $dirty6, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.kt:1698)");
                            }
                            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                            int $dirty7 = $dirty6;
                            String str = query;
                            boolean z5 = enabled10;
                            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                            MutableInteractionSource mutableInteractionSource = interactionSource5;
                            Function2<Composer, Integer, Unit> function27 = function24;
                            final Function2<? super Composer, ? super Integer, Unit> function28 = function20;
                            if (function28 == null) {
                                $composer8.startReplaceGroup(1691265870);
                                $composer8.endReplaceGroup();
                                composableLambda = null;
                            } else {
                                $composer8.startReplaceGroup(1691265871);
                                ComposerKt.sourceInformation($composer8, "*1708@81676L64");
                                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1718822048, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$23$1$1
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer9, int $changed3) {
                                        Function0<ComposeUiNode> function0;
                                        ComposerKt.sourceInformation($composer9, "C1708@81678L60:SearchBar.kt#uh7d8r");
                                        if ($composer9.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1718822048, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:1708)");
                                            }
                                            Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(Modifier.INSTANCE, SearchBarKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                            Function2<Composer, Integer, Unit> function29 = function28;
                                            ComposerKt.sourceInformationMarkerStart($composer9, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                            int $changed$iv$iv = (6 << 3) & 112;
                                            ComposerKt.sourceInformationMarkerStart($composer9, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer9, 0);
                                            CompositionLocalMap localMap$iv$iv = $composer9.getCurrentCompositionLocalMap();
                                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer9, modifier$iv);
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer9, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                            if (!($composer9.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer9.startReusableNode();
                                            if ($composer9.getInserting()) {
                                                function0 = constructor;
                                                $composer9.createNode(function0);
                                            } else {
                                                function0 = constructor;
                                                $composer9.useNode();
                                            }
                                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer9);
                                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                            }
                                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i8 = ($changed$iv$iv$iv >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer9, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                            int i9 = ((6 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer9, 1796932852, "C1708@81727L9:SearchBar.kt#uh7d8r");
                                            function29.invoke($composer9, 0);
                                            ComposerKt.sourceInformationMarkerEnd($composer9);
                                            ComposerKt.sourceInformationMarkerEnd($composer9);
                                            $composer9.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer9);
                                            ComposerKt.sourceInformationMarkerEnd($composer9);
                                            ComposerKt.sourceInformationMarkerEnd($composer9);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer9.skipToGroupEnd();
                                    }
                                }, $composer8, 54);
                                $composer8.endReplaceGroup();
                                composableLambda = composableLambdaRememberComposableLambda;
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function29 = function21;
                            if (function29 == null) {
                                $composer8.startReplaceGroup(1691490155);
                                $composer8.endReplaceGroup();
                                trailing = null;
                            } else {
                                $composer8.startReplaceGroup(1691490156);
                                ComposerKt.sourceInformation($composer8, "*1712@81903L66");
                                Function2 trailing2 = ComposableLambdaKt.rememberComposableLambda(-305306680, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$23$2$1
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer9, int $changed3) {
                                        Function0<ComposeUiNode> function0;
                                        ComposerKt.sourceInformation($composer9, "C1712@81905L62:SearchBar.kt#uh7d8r");
                                        if (!$composer9.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                            $composer9.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-305306680, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:1712)");
                                        }
                                        Modifier.Companion companion3 = Modifier.INSTANCE;
                                        float arg0$iv = SearchBarKt.SearchBarIconOffsetX;
                                        Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(companion3, Dp.m7905constructorimpl(-arg0$iv), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function210 = function29;
                                        ComposerKt.sourceInformationMarkerStart($composer9, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                        int $changed$iv$iv = (6 << 3) & 112;
                                        ComposerKt.sourceInformationMarkerStart($composer9, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer9, 0);
                                        CompositionLocalMap localMap$iv$iv = $composer9.getCurrentCompositionLocalMap();
                                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer9, modifier$iv);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer9, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                        if (!($composer9.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        $composer9.startReusableNode();
                                        if ($composer9.getInserting()) {
                                            function0 = constructor;
                                            $composer9.createNode(function0);
                                        } else {
                                            function0 = constructor;
                                            $composer9.useNode();
                                        }
                                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer9);
                                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                        }
                                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i8 = ($changed$iv$iv$iv >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart($composer9, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i9 = ((6 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer9, 1336107703, "C1712@81955L10:SearchBar.kt#uh7d8r");
                                        function210.invoke($composer9, 0);
                                        ComposerKt.sourceInformationMarkerEnd($composer9);
                                        ComposerKt.sourceInformationMarkerEnd($composer9);
                                        $composer9.endNode();
                                        ComposerKt.sourceInformationMarkerEnd($composer9);
                                        ComposerKt.sourceInformationMarkerEnd($composer9);
                                        ComposerKt.sourceInformationMarkerEnd($composer9);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }
                                }, $composer8, 54);
                                trailing = trailing2;
                                $composer8.endReplaceGroup();
                            }
                            Shape inputFieldShape = SearchBarDefaults.INSTANCE.getInputFieldShape($composer8, 6);
                            TextFieldColors textFieldColors = colors4;
                            PaddingValues paddingValuesM3042contentPaddingWithoutLabela9UjIt4$default = TextFieldDefaults.m3042contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                            final TextFieldColors textFieldColors2 = colors4;
                            final boolean z6 = enabled10;
                            final boolean z7 = focused;
                            textFieldDefaults.DecorationBox(str, function26, z5, true, none, mutableInteractionSource, false, null, function27, composableLambda, trailing, null, null, null, inputFieldShape, textFieldColors, paddingValuesM3042contentPaddingWithoutLabela9UjIt4$default, ComposableLambdaKt.rememberComposableLambda(-1197464330, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23.3
                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                    invoke(composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer $composer9, int $changed3) {
                                    ComposerKt.sourceInformation($composer9, "C1726@82767L7,1719@82317L492,1729@82927L15,1728@82838L135:SearchBar.kt#uh7d8r");
                                    if (!$composer9.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                        $composer9.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1197464330, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous> (SearchBar.kt:1718)");
                                    }
                                    BoxKt.Box(TextFieldImplKt.textFieldBackground(Modifier.INSTANCE, new SearchBarKt$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(SingleValueAnimationKt.m135animateColorAsStateeuL9pac(textFieldColors2.m2987containerColorXeAY9LY$material3(z6, false, z7), MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer9, 6), null, null, $composer9, 0, 12)) { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23.3.1
                                        @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                                        public Object get() {
                                            return ((State) this.receiver).getValue();
                                        }
                                    }), SearchBarDefaults.INSTANCE.getInputFieldShape($composer9, 6)), $composer9, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, $composer8, 54), $composer8, (($dirty7 << 3) & 112) | 27648, 113246208, 14528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer8.skipToGroupEnd();
                    }
                }, $composer3, 54), $composer7, ($dirty5 & 14) | 102236160 | ($dirty5 & 112) | (($dirty5 >> 9) & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
                $composer2 = $composer7;
                if (expanded) {
                    shouldClearFocus = false;
                } else {
                    shouldClearFocus = false;
                }
                Boolean boolValueOf3 = Boolean.valueOf(expanded);
                ComposerKt.sourceInformationMarkerStart($composer2, 1586202631, "CC(remember):SearchBar.kt#9igjgp");
                invalid$iv3 = $composer2.changed(shouldClearFocus) | $composer2.changedInstance(focusManager);
                it$iv2 = $composer2.rememberedValue();
                if (invalid$iv3) {
                    Object value$iv13 = (Function2) new SearchBarDefaults$InputField$24$1(shouldClearFocus, focusManager, null);
                    $composer2.updateRememberedValue(value$iv13);
                    it$iv2 = value$iv13;
                } else {
                    Object value$iv14 = (Function2) new SearchBarDefaults$InputField$24$1(shouldClearFocus, focusManager, null);
                    $composer2.updateRememberedValue(value$iv14);
                    it$iv2 = value$iv14;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(boolValueOf3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer2, ($dirty5 >> 9) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                enabled2 = enabled11;
                interactionSource2 = interactionSource3;
                modifier3 = modifier5;
                colors2 = colors7;
                function7 = function24;
                function8 = function20;
                function9 = function21;
            } else {
                focusRequester = focusRequester2;
            }
            value$iv2 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SearchBarDefaults.InputField$lambda$40$lambda$39(searchSemantics, expanded, suggestionsAvailableSemantics, (SemanticsPropertyReceiver) obj);
                }
            };
            $this$cache$iv6.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default4 = SemanticsModifierKt.semantics$default(modifierOnFocusChanged2, false, (Function1) value$iv2, 1, null);
            ProvidableCompositionLocal<TextStyle> localTextStyle5 = TextKt.getLocalTextStyle();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume6 = $composer3.consume(localTextStyle5);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            TextStyle textStyleMerge4 = ((TextStyle) objConsume6).merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            SolidColor solidColor4 = new SolidColor(colors4.m2989cursorColorvNxB06k$material3(false), null);
            KeyboardOptions keyboardOptions4 = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m7515getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1586133146, "CC(remember):SearchBar.kt#9igjgp");
            if (($dirty & 896) == 256) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (($dirty & 14) == 4) {
                z4 = true;
            } else {
                z4 = false;
            }
            invalid$iv2 = z3 | z4;
            $this$cache$iv = $composer3;
            it$iv = $this$cache$iv.rememberedValue();
            if (invalid$iv2) {
                Object value$iv15 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchBarDefaults.InputField$lambda$42$lambda$41(function2, query, (KeyboardActionScope) obj);
                    }
                };
                $this$cache$iv.updateRememberedValue(value$iv15);
                it$iv = value$iv15;
            } else {
                Object value$iv16 = new Function1() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchBarDefaults.InputField$lambda$42$lambda$41(function2, query, (KeyboardActionScope) obj);
                    }
                };
                $this$cache$iv.updateRememberedValue(value$iv16);
                it$iv = value$iv16;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Function2<? super Composer, ? super Integer, Unit> function25 = function11;
            final boolean enabled12 = enabled4;
            int $dirty6 = $dirty;
            TextFieldColors colors8 = colors4;
            boolean enabled13 = enabled4;
            Composer $composer8 = $composer3;
            BasicTextFieldKt.BasicTextField(query, function1, modifierSemantics$default4, enabled13, false, textStyleMerge4, keyboardOptions4, new KeyboardActions(null, null, null, null, (Function1) it$iv, null, 47, null), true, 0, 0, (VisualTransformation) null, (Function1<? super TextLayoutResult, Unit>) null, interactionSource5, (Brush) solidColor4, (Function3<? super Function2<? super Composer, ? super Integer, Unit>, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-813926844, true, new Function3<Function2<? super Composer, ? super Integer, ? extends Unit>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Function2<? super Composer, ? super Integer, ? extends Unit> function26, Composer composer, Integer num) {
                    invoke((Function2<? super Composer, ? super Integer, Unit>) function26, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Function2<? super Composer, ? super Integer, Unit> function26, Composer $composer9, int $changed2) {
                    Function2<? super Composer, ? super Integer, Unit> function27;
                    ComposableLambda composableLambda;
                    Function2 trailing;
                    ComposerKt.sourceInformation($composer9, "CN(innerTextField)1714@82051L15,1717@82234L765,1698@81163L1859:SearchBar.kt#uh7d8r");
                    int $dirty7 = $changed2;
                    if (($changed2 & 6) == 0) {
                        function27 = function26;
                        $dirty7 |= $composer9.changedInstance(function27) ? 4 : 2;
                    } else {
                        function27 = function26;
                    }
                    if ($composer9.shouldExecute(($dirty7 & 19) != 18, $dirty7 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-813926844, $dirty7, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous> (SearchBar.kt:1698)");
                        }
                        TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                        int $dirty8 = $dirty7;
                        String str = query;
                        boolean z5 = enabled12;
                        VisualTransformation none = VisualTransformation.INSTANCE.getNone();
                        MutableInteractionSource mutableInteractionSource = interactionSource5;
                        Function2<Composer, Integer, Unit> function28 = function25;
                        final Function2<? super Composer, ? super Integer, Unit> function29 = function20;
                        if (function29 == null) {
                            $composer9.startReplaceGroup(1691265870);
                            $composer9.endReplaceGroup();
                            composableLambda = null;
                        } else {
                            $composer9.startReplaceGroup(1691265871);
                            ComposerKt.sourceInformation($composer9, "*1708@81676L64");
                            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1718822048, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$23$1$1
                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                    invoke(composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer $composer10, int $changed3) {
                                    Function0<ComposeUiNode> function0;
                                    ComposerKt.sourceInformation($composer10, "C1708@81678L60:SearchBar.kt#uh7d8r");
                                    if ($composer10.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1718822048, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:1708)");
                                        }
                                        Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(Modifier.INSTANCE, SearchBarKt.SearchBarIconOffsetX, 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function210 = function29;
                                        ComposerKt.sourceInformationMarkerStart($composer10, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                        int $changed$iv$iv = (6 << 3) & 112;
                                        ComposerKt.sourceInformationMarkerStart($composer10, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer10, 0);
                                        CompositionLocalMap localMap$iv$iv = $composer10.getCurrentCompositionLocalMap();
                                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer10, modifier$iv);
                                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer10, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                        if (!($composer10.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        $composer10.startReusableNode();
                                        if ($composer10.getInserting()) {
                                            function0 = constructor;
                                            $composer10.createNode(function0);
                                        } else {
                                            function0 = constructor;
                                            $composer10.useNode();
                                        }
                                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer10);
                                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                        }
                                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i8 = ($changed$iv$iv$iv >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart($composer10, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        int i9 = ((6 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart($composer10, 1796932852, "C1708@81727L9:SearchBar.kt#uh7d8r");
                                        function210.invoke($composer10, 0);
                                        ComposerKt.sourceInformationMarkerEnd($composer10);
                                        ComposerKt.sourceInformationMarkerEnd($composer10);
                                        $composer10.endNode();
                                        ComposerKt.sourceInformationMarkerEnd($composer10);
                                        ComposerKt.sourceInformationMarkerEnd($composer10);
                                        ComposerKt.sourceInformationMarkerEnd($composer10);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer10.skipToGroupEnd();
                                }
                            }, $composer9, 54);
                            $composer9.endReplaceGroup();
                            composableLambda = composableLambdaRememberComposableLambda;
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function210 = function21;
                        if (function210 == null) {
                            $composer9.startReplaceGroup(1691490155);
                            $composer9.endReplaceGroup();
                            trailing = null;
                        } else {
                            $composer9.startReplaceGroup(1691490156);
                            ComposerKt.sourceInformation($composer9, "*1712@81903L66");
                            Function2 trailing2 = ComposableLambdaKt.rememberComposableLambda(-305306680, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults$InputField$23$2$1
                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                    invoke(composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer $composer10, int $changed3) {
                                    Function0<ComposeUiNode> function0;
                                    ComposerKt.sourceInformation($composer10, "C1712@81905L62:SearchBar.kt#uh7d8r");
                                    if (!$composer10.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                        $composer10.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-305306680, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous>.<anonymous> (SearchBar.kt:1712)");
                                    }
                                    Modifier.Companion companion3 = Modifier.INSTANCE;
                                    float arg0$iv = SearchBarKt.SearchBarIconOffsetX;
                                    Modifier modifier$iv = OffsetKt.m779offsetVpY3zN4$default(companion3, Dp.m7905constructorimpl(-arg0$iv), 0.0f, 2, null);
                                    Function2<Composer, Integer, Unit> function211 = function210;
                                    ComposerKt.sourceInformationMarkerStart($composer10, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                    MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                    int $changed$iv$iv = (6 << 3) & 112;
                                    ComposerKt.sourceInformationMarkerStart($composer10, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                    int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer10, 0);
                                    CompositionLocalMap localMap$iv$iv = $composer10.getCurrentCompositionLocalMap();
                                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer10, modifier$iv);
                                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer10, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                    if (!($composer10.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer10.startReusableNode();
                                    if ($composer10.getInserting()) {
                                        function0 = constructor;
                                        $composer10.createNode(function0);
                                    } else {
                                        function0 = constructor;
                                        $composer10.useNode();
                                    }
                                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer10);
                                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                        $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                        $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                                    }
                                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i8 = ($changed$iv$iv$iv >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer10, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i9 = ((6 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer10, 1336107703, "C1712@81955L10:SearchBar.kt#uh7d8r");
                                    function211.invoke($composer10, 0);
                                    ComposerKt.sourceInformationMarkerEnd($composer10);
                                    ComposerKt.sourceInformationMarkerEnd($composer10);
                                    $composer10.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer10);
                                    ComposerKt.sourceInformationMarkerEnd($composer10);
                                    ComposerKt.sourceInformationMarkerEnd($composer10);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }, $composer9, 54);
                            trailing = trailing2;
                            $composer9.endReplaceGroup();
                        }
                        Shape inputFieldShape = SearchBarDefaults.INSTANCE.getInputFieldShape($composer9, 6);
                        TextFieldColors textFieldColors = colors4;
                        PaddingValues paddingValuesM3042contentPaddingWithoutLabela9UjIt4$default = TextFieldDefaults.m3042contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                        final TextFieldColors textFieldColors2 = colors4;
                        final boolean z6 = enabled12;
                        final boolean z7 = focused;
                        textFieldDefaults.DecorationBox(str, function27, z5, true, none, mutableInteractionSource, false, null, function28, composableLambda, trailing, null, null, null, inputFieldShape, textFieldColors, paddingValuesM3042contentPaddingWithoutLabela9UjIt4$default, ComposableLambdaKt.rememberComposableLambda(-1197464330, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23.3
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                invoke(composer, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer10, int $changed3) {
                                ComposerKt.sourceInformation($composer10, "C1726@82767L7,1719@82317L492,1729@82927L15,1728@82838L135:SearchBar.kt#uh7d8r");
                                if (!$composer10.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                    $composer10.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1197464330, $changed3, -1, "androidx.compose.material3.SearchBarDefaults.InputField.<anonymous>.<anonymous> (SearchBar.kt:1718)");
                                }
                                BoxKt.Box(TextFieldImplKt.textFieldBackground(Modifier.INSTANCE, new SearchBarKt$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(SingleValueAnimationKt.m135animateColorAsStateeuL9pac(textFieldColors2.m2987containerColorXeAY9LY$material3(z6, false, z7), MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer10, 6), null, null, $composer10, 0, 12)) { // from class: androidx.compose.material3.SearchBarDefaults.InputField.23.3.1
                                    @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                                    public Object get() {
                                        return ((State) this.receiver).getValue();
                                    }
                                }), SearchBarDefaults.INSTANCE.getInputFieldShape($composer10, 6)), $composer10, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer9, 54), $composer9, (($dirty8 << 3) & 112) | 27648, 113246208, 14528);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer9.skipToGroupEnd();
                }
            }, $composer3, 54), $composer8, ($dirty6 & 14) | 102236160 | ($dirty6 & 112) | (($dirty6 >> 9) & 7168), ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 7696);
            $composer2 = $composer8;
            if (expanded) {
                shouldClearFocus = false;
            } else {
                shouldClearFocus = false;
            }
            Boolean boolValueOf4 = Boolean.valueOf(expanded);
            ComposerKt.sourceInformationMarkerStart($composer2, 1586202631, "CC(remember):SearchBar.kt#9igjgp");
            invalid$iv3 = $composer2.changed(shouldClearFocus) | $composer2.changedInstance(focusManager);
            it$iv2 = $composer2.rememberedValue();
            if (invalid$iv3) {
                Object value$iv17 = (Function2) new SearchBarDefaults$InputField$24$1(shouldClearFocus, focusManager, null);
                $composer2.updateRememberedValue(value$iv17);
                it$iv2 = value$iv17;
            } else {
                Object value$iv18 = (Function2) new SearchBarDefaults$InputField$24$1(shouldClearFocus, focusManager, null);
                $composer2.updateRememberedValue(value$iv18);
                it$iv2 = value$iv18;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(boolValueOf4, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer2, ($dirty6 >> 9) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            enabled2 = enabled13;
            interactionSource2 = interactionSource3;
            modifier3 = modifier5;
            colors2 = colors8;
            function7 = function25;
            function8 = function20;
            function9 = function21;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            function7 = function4;
            function8 = function5;
            function9 = function6;
            colors2 = colors;
            interactionSource2 = interactionSource;
            enabled2 = z;
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SearchBarDefaults$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SearchBarDefaults.InputField$lambda$44(this.f$0, query, function1, function2, expanded, function3, modifier3, enabled2, function7, function8, function9, colors2, interactionSource2, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit InputField$lambda$38$lambda$37(Function1 $onExpandedChange, FocusState it) {
        if (it.isFocused()) {
            $onExpandedChange.invoke(true);
        }
        return Unit.INSTANCE;
    }

    static final Unit InputField$lambda$40$lambda$39(String $searchSemantics, boolean $expanded, String $suggestionsAvailableSemantics, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $searchSemantics);
        if ($expanded) {
            SemanticsPropertiesKt.setStateDescription($this$semantics, $suggestionsAvailableSemantics);
        }
        return Unit.INSTANCE;
    }

    static final Unit InputField$lambda$42$lambda$41(Function1 $onSearch, String $query, KeyboardActionScope $this$KeyboardActions) {
        $onSearch.invoke($query);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: colors-dgg9oW8, reason: not valid java name */
    public final /* synthetic */ SearchBarColors m2772colorsdgg9oW8(long containerColor, long dividerColor, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 891254734, "C(colors)N(containerColor:c#ui.graphics.Color,dividerColor:c#ui.graphics.Color)1750@83659L5,1751@83726L5,1757@83916L218:SearchBar.kt#uh7d8r");
        long containerColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), $composer, 6) : containerColor;
        long dividerColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(SearchViewTokens.INSTANCE.getDividerColor(), $composer, 6) : dividerColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(891254734, $changed, -1, "androidx.compose.material3.SearchBarDefaults.colors (SearchBar.kt:1753)");
        }
        long containerColor3 = containerColor2;
        SearchBarColors searchBarColors = new SearchBarColors(containerColor3, dividerColor2, m2779inputFieldColorsJVEmHcM(0L, 0L, 0L, 0L, null, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, containerColor3, containerColor3, containerColor3, $composer, 0, 0, ($changed & 14) | (($changed << 3) & 112) | (($changed << 6) & 896) | (($changed << 3) & 7168), 1048575), null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return searchBarColors;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: inputFieldColors-ITpI4ow, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m2778inputFieldColorsITpI4ow(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long cursorColor, SelectionColors selectionColors, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int i) {
        long disabledTextColor2;
        SelectionColors selectionColors2;
        int i2;
        long focusedLeadingIconColor2;
        long disabledLeadingIconColor2;
        int i3;
        long focusedTrailingIconColor2;
        long disabledTrailingIconColor2;
        int i4;
        long unfocusedPlaceholderColor2;
        long disabledPlaceholderColor2;
        ComposerKt.sourceInformationMarkerStart($composer, -2048506052, "C(inputFieldColors)N(focusedTextColor:c#ui.graphics.Color,unfocusedTextColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,cursorColor:c#ui.graphics.Color,selectionColors,focusedLeadingIconColor:c#ui.graphics.Color,unfocusedLeadingIconColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,focusedTrailingIconColor:c#ui.graphics.Color,unfocusedTrailingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color,focusedPlaceholderColor:c#ui.graphics.Color,unfocusedPlaceholderColor:c#ui.graphics.Color,disabledPlaceholderColor:c#ui.graphics.Color)1767@84342L5,1768@84416L5,1770@84511L5,1773@84667L5,1774@84746L7,1775@84829L5,1776@84912L5,1778@85020L5,1781@85196L5,1782@85281L5,1784@85391L5,1787@85569L5,1788@85655L5,1790@85757L5,1809@86804L5,1810@86885L5,1812@86981L5,1815@87155L5,1816@87236L5,1818@87332L5,1821@87501L5,1822@87577L5,1823@87652L5,1794@85884L1784:SearchBar.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), $composer, 6) : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), $composer, 6) : unfocusedTextColor;
        if ((i & 4) != 0) {
            long value = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledTextColor2 = Color.m5095copywmQWz5c(value, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long cursorColor2 = (i & 8) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6) : cursorColor;
        if ((i & 16) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) objConsume;
        } else {
            selectionColors2 = selectionColors;
        }
        if ((i & 32) != 0) {
            i2 = 6;
            focusedLeadingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, 6);
        } else {
            i2 = 6;
            focusedLeadingIconColor2 = focusedLeadingIconColor;
        }
        long unfocusedLeadingIconColor2 = (i & 64) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, i2) : unfocusedLeadingIconColor;
        if ((i & 128) != 0) {
            long value2 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, i2);
            disabledLeadingIconColor2 = Color.m5095copywmQWz5c(value2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value2) : FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value2) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        SelectionColors selectionColors3 = selectionColors2;
        if ((i & 256) != 0) {
            i3 = 6;
            focusedTrailingIconColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, 6);
        } else {
            i3 = 6;
            focusedTrailingIconColor2 = focusedTrailingIconColor;
        }
        long unfocusedTrailingIconColor2 = (i & 512) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, i3) : unfocusedTrailingIconColor;
        if ((i & 1024) != 0) {
            long value3 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, i3);
            disabledTrailingIconColor2 = Color.m5095copywmQWz5c(value3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value3) : FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value3) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long focusedPlaceholderColor2 = (i & 2048) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), $composer, 6) : focusedPlaceholderColor;
        if ((i & 4096) != 0) {
            i4 = 6;
            unfocusedPlaceholderColor2 = ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), $composer, 6);
        } else {
            i4 = 6;
            unfocusedPlaceholderColor2 = unfocusedPlaceholderColor;
        }
        if ((i & 8192) != 0) {
            long value4 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, i4);
            disabledPlaceholderColor2 = Color.m5095copywmQWz5c(value4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value4) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value4) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2048506052, $changed, $changed1, "androidx.compose.material3.SearchBarDefaults.inputFieldColors (SearchBar.kt:1794)");
        }
        long value5 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
        long value6 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
        long value7 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputPrefixColor(), $composer, 6);
        long jM5095copywmQWz5c = Color.m5095copywmQWz5c(value7, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value7) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value7) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value7) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value7) : 0.0f);
        long value8 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
        long value9 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
        long value10 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getInputSuffixColor(), $composer, 6);
        long disabledTextColor3 = disabledTextColor2;
        long cursorColor3 = cursorColor2;
        long disabledTextColor4 = focusedLeadingIconColor2;
        long cursorColor4 = unfocusedLeadingIconColor2;
        long focusedTrailingIconColor3 = focusedTrailingIconColor2;
        long unfocusedLeadingIconColor3 = unfocusedTrailingIconColor2;
        long focusedLeadingIconColor3 = disabledLeadingIconColor2;
        long focusedTrailingIconColor4 = disabledTrailingIconColor2;
        long unfocusedTrailingIconColor3 = focusedPlaceholderColor2;
        TextFieldColors textFieldColorsM2779inputFieldColorsJVEmHcM = m2779inputFieldColorsJVEmHcM(focusedTextColor2, unfocusedTextColor2, disabledTextColor3, cursorColor3, selectionColors3, disabledTextColor4, cursorColor4, focusedLeadingIconColor3, focusedTrailingIconColor3, unfocusedLeadingIconColor3, focusedTrailingIconColor4, unfocusedTrailingIconColor3, unfocusedPlaceholderColor2, disabledPlaceholderColor2, value5, value6, jM5095copywmQWz5c, value8, value9, Color.m5095copywmQWz5c(value10, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value10) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value10) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value10) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value10) : 0.0f), ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), $composer, 6), ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), $composer, 6), ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getContainerColor(), $composer, 6), $composer, ($changed & 14) | ($changed & 112) | ($changed & 896) | ($changed & 7168) | (57344 & $changed) | (458752 & $changed) | (3670016 & $changed) | (29360128 & $changed) | (234881024 & $changed) | (1879048192 & $changed), ($changed1 & 14) | ($changed1 & 112) | ($changed1 & 896) | ($changed1 & 7168), ($changed1 >> 3) & 7168, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return textFieldColorsM2779inputFieldColorsJVEmHcM;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: inputFieldColors--u-KgnY, reason: not valid java name */
    public final /* synthetic */ TextFieldColors m2777inputFieldColorsuKgnY(long textColor, long disabledTextColor, long cursorColor, SelectionColors selectionColors, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long placeholderColor, long disabledPlaceholderColor, Composer $composer, int $changed, int $changed1, int i) {
        long disabledTextColor2;
        SelectionColors selectionColors2;
        long disabledLeadingIconColor2;
        long disabledTrailingIconColor2;
        long disabledPlaceholderColor2;
        ComposerKt.sourceInformationMarkerStart($composer, 1842555178, "C(inputFieldColors)N(textColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,cursorColor:c#ui.graphics.Color,selectionColors,focusedLeadingIconColor:c#ui.graphics.Color,unfocusedLeadingIconColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,focusedTrailingIconColor:c#ui.graphics.Color,unfocusedTrailingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color,placeholderColor:c#ui.graphics.Color,disabledPlaceholderColor:c#ui.graphics.Color)1829@87858L5,1831@87953L5,1834@88109L5,1835@88188L7,1836@88271L5,1837@88354L5,1839@88462L5,1842@88638L5,1843@88723L5,1845@88833L5,1848@89004L5,1850@89106L5,1854@89216L825:SearchBar.kt#uh7d8r");
        long textColor2 = (i & 1) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getInputTextColor(), $composer, 6) : textColor;
        if ((i & 2) != 0) {
            long value = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledTextColor2 = Color.m5095copywmQWz5c(value, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value) : 0.0f);
        } else {
            disabledTextColor2 = disabledTextColor;
        }
        long cursorColor2 = (i & 4) != 0 ? ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getCaretColor(), $composer, 6) : cursorColor;
        if ((i & 8) != 0) {
            ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localTextSelectionColors);
            ComposerKt.sourceInformationMarkerEnd($composer);
            selectionColors2 = (SelectionColors) objConsume;
        } else {
            selectionColors2 = selectionColors;
        }
        long focusedLeadingIconColor2 = (i & 16) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (i & 32) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getLeadingIconColor(), $composer, 6) : unfocusedLeadingIconColor;
        if ((i & 64) != 0) {
            long value2 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor(), $composer, 6);
            disabledLeadingIconColor2 = Color.m5095copywmQWz5c(value2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value2) : FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value2) : 0.0f);
        } else {
            disabledLeadingIconColor2 = disabledLeadingIconColor;
        }
        long focusedTrailingIconColor2 = (i & 128) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (i & 256) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getTrailingIconColor(), $composer, 6) : unfocusedTrailingIconColor;
        if ((i & 512) != 0) {
            long value3 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor(), $composer, 6);
            disabledTrailingIconColor2 = Color.m5095copywmQWz5c(value3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value3) : FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value3) : 0.0f);
        } else {
            disabledTrailingIconColor2 = disabledTrailingIconColor;
        }
        long placeholderColor2 = (i & 1024) != 0 ? ColorSchemeKt.getValue(SearchBarTokens.INSTANCE.getSupportingTextColor(), $composer, 6) : placeholderColor;
        if ((i & 2048) != 0) {
            long value4 = ColorSchemeKt.getValue(FilledTextFieldTokens.INSTANCE.getDisabledInputColor(), $composer, 6);
            disabledPlaceholderColor2 = Color.m5095copywmQWz5c(value4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(value4) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(value4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(value4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(value4) : 0.0f);
        } else {
            disabledPlaceholderColor2 = disabledPlaceholderColor;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1842555178, $changed, $changed1, "androidx.compose.material3.SearchBarDefaults.inputFieldColors (SearchBar.kt:1854)");
        }
        long textColor3 = textColor2;
        long disabledLeadingIconColor3 = disabledLeadingIconColor2;
        long disabledLeadingIconColor4 = unfocusedTrailingIconColor2;
        long unfocusedTrailingIconColor3 = placeholderColor2;
        long placeholderColor3 = disabledPlaceholderColor2;
        long cursorColor3 = cursorColor2;
        long cursorColor4 = focusedLeadingIconColor2;
        long focusedLeadingIconColor3 = unfocusedLeadingIconColor2;
        long unfocusedLeadingIconColor3 = focusedTrailingIconColor2;
        long focusedTrailingIconColor3 = disabledTrailingIconColor2;
        TextFieldColors textFieldColorsM2779inputFieldColorsJVEmHcM = m2779inputFieldColorsJVEmHcM(textColor3, textColor3, disabledTextColor2, cursorColor3, selectionColors2, cursorColor4, focusedLeadingIconColor3, disabledLeadingIconColor3, unfocusedLeadingIconColor3, disabledLeadingIconColor4, focusedTrailingIconColor3, unfocusedTrailingIconColor3, unfocusedTrailingIconColor3, placeholderColor3, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, ($changed & 14) | (($changed << 3) & 112) | (($changed << 3) & 896) | (($changed << 3) & 7168) | (($changed << 3) & 57344) | (($changed << 3) & 458752) | (($changed << 3) & 3670016) | (($changed << 3) & 29360128) | (($changed << 3) & 234881024) | (($changed << 3) & 1879048192), (($changed >> 27) & 14) | (($changed1 << 3) & 112) | (($changed1 << 6) & 896) | (($changed1 << 6) & 7168), ($changed1 << 3) & 7168, 8372224);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return textFieldColorsM2779inputFieldColorsJVEmHcM;
    }
}
