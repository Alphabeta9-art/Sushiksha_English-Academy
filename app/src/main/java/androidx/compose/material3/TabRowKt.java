package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TabRow.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a}\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\u001e\b\u0002\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f¢\u0006\u0002\b\r2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a}\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\u001e\b\u0002\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f¢\u0006\u0002\b\r2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b\u0014\u0010\u0012\u001a\u009b\u0001\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001e\b\u0002\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f¢\u0006\u0002\b\r2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\b\b\u0002\u0010\u001a\u001a\u00020\u00192\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u009b\u0001\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001e\b\u0002\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f¢\u0006\u0002\b\r2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\b\b\u0002\u0010\u001a\u001a\u00020\u00192\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b\u001e\u0010\u001c\u001ak\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u001c\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f¢\u0006\u0002\b\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0003¢\u0006\u0004\b \u0010!\u001a\u008b\u0001\u0010\"\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00172\u001c\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f¢\u0006\u0002\b\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0003¢\u0006\u0004\b#\u0010$\u001a{\u0010%\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072,\u0010\t\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020'0&¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0003¢\u0006\u0004\b+\u0010!\u001a\u009d\u0001\u0010,\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032,\u0010\t\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020'0&¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0003¢\u0006\u0004\b-\u0010.\u001a\u0091\u0001\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001e\b\u0002\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f¢\u0006\u0002\b\r2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b/\u00100\u001a\u0091\u0001\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u001e\b\u0002\u0010\t\u001a\u0018\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f¢\u0006\u0002\b\r2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b1\u00100\u001a\u008d\u0001\u00102\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072.\b\u0002\u0010\t\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020'0&¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b3\u0010\u0012\u001a\u0097\u0001\u00104\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00192.\b\u0002\u0010\t\u001a(\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020'0&¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\f2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b5\u00106¨\u00067"}, d2 = {"PrimaryTabRow", "", "selectedTabIndex", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "indicator", "Lkotlin/Function1;", "Landroidx/compose/material3/TabIndicatorScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "divider", "Lkotlin/Function0;", "tabs", "PrimaryTabRow-pAZo6Ak", "(ILandroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "SecondaryTabRow", "SecondaryTabRow-pAZo6Ak", "PrimaryScrollableTabRow", "scrollState", "Landroidx/compose/foundation/ScrollState;", "edgePadding", "Landroidx/compose/ui/unit/Dp;", "minTabWidth", "PrimaryScrollableTabRow-cx2KkNY", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "SecondaryScrollableTabRow", "SecondaryScrollableTabRow-cx2KkNY", "TabRowImpl", "TabRowImpl-DTcfvLk", "(Landroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ScrollableTabRowImpl", "ScrollableTabRowImpl-xam5sdo", "(ILandroidx/compose/ui/Modifier;JJFFLandroidx/compose/foundation/ScrollState;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TabRowWithSubcomposeImpl", "", "Landroidx/compose/material3/TabPosition;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "tabPositions", "TabRowWithSubcomposeImpl-DTcfvLk", "ScrollableTabRowWithSubcomposeImpl", "ScrollableTabRowWithSubcomposeImpl-qhFBPw4", "(ILkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/ScrollState;Landroidx/compose/runtime/Composer;II)V", "PrimaryScrollableTabRow-qhFBPw4", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "SecondaryScrollableTabRow-qhFBPw4", "TabRow", "TabRow-pAZo6Ak", "ScrollableTabRow", "ScrollableTabRow-sKfQg0A", "(ILandroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TabRowKt {
    static final Unit PrimaryScrollableTabRow_cx2KkNY$lambda$2(int i, Modifier modifier, ScrollState scrollState, long j, long j2, float f, Function3 function3, Function2 function2, float f2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m2974PrimaryScrollableTabRowcx2KkNY(i, modifier, scrollState, j, j2, f, function3, function2, f2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit PrimaryScrollableTabRow_qhFBPw4$lambda$8(int i, Modifier modifier, ScrollState scrollState, long j, long j2, float f, Function3 function3, Function2 function2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m2975PrimaryScrollableTabRowqhFBPw4(i, modifier, scrollState, j, j2, f, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit PrimaryTabRow_pAZo6Ak$lambda$0(int i, Modifier modifier, long j, long j2, Function3 function3, Function2 function2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m2976PrimaryTabRowpAZo6Ak(i, modifier, j, j2, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit ScrollableTabRowImpl_xam5sdo$lambda$5(int i, Modifier modifier, long j, long j2, float f, float f2, ScrollState scrollState, Function3 function3, Function2 function2, Function2 function4, int i2, Composer composer, int i3) {
        m2978ScrollableTabRowImplxam5sdo(i, modifier, j, j2, f, f2, scrollState, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    static final Unit ScrollableTabRowWithSubcomposeImpl_qhFBPw4$lambda$7(int i, Function3 function3, Modifier modifier, long j, long j2, float f, Function2 function2, Function2 function4, ScrollState scrollState, int i2, int i3, Composer composer, int i4) {
        m2979ScrollableTabRowWithSubcomposeImplqhFBPw4(i, function3, modifier, j, j2, f, function2, function4, scrollState, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit ScrollableTabRow_sKfQg0A$lambda$11(int i, Modifier modifier, long j, long j2, float f, Function3 function3, Function2 function2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m2977ScrollableTabRowsKfQg0A(i, modifier, j, j2, f, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit SecondaryScrollableTabRow_cx2KkNY$lambda$3(int i, Modifier modifier, ScrollState scrollState, long j, long j2, float f, Function3 function3, Function2 function2, float f2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m2980SecondaryScrollableTabRowcx2KkNY(i, modifier, scrollState, j, j2, f, function3, function2, f2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit SecondaryScrollableTabRow_qhFBPw4$lambda$9(int i, Modifier modifier, ScrollState scrollState, long j, long j2, float f, Function3 function3, Function2 function2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m2981SecondaryScrollableTabRowqhFBPw4(i, modifier, scrollState, j, j2, f, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit SecondaryTabRow_pAZo6Ak$lambda$1(int i, Modifier modifier, long j, long j2, Function3 function3, Function2 function2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m2982SecondaryTabRowpAZo6Ak(i, modifier, j, j2, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit TabRowImpl_DTcfvLk$lambda$4(Modifier modifier, long j, long j2, Function3 function3, Function2 function2, Function2 function4, int i, Composer composer, int i2) {
        m2984TabRowImplDTcfvLk(modifier, j, j2, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TabRowWithSubcomposeImpl_DTcfvLk$lambda$6(Modifier modifier, long j, long j2, Function3 function3, Function2 function2, Function2 function4, int i, Composer composer, int i2) {
        m2985TabRowWithSubcomposeImplDTcfvLk(modifier, j, j2, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TabRow_pAZo6Ak$lambda$10(int i, Modifier modifier, long j, long j2, Function3 function3, Function2 function2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m2983TabRowpAZo6Ak(i, modifier, j, j2, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: PrimaryTabRow-pAZo6Ak, reason: not valid java name */
    public static final void m2976PrimaryTabRowpAZo6Ak(final int selectedTabIndex, Modifier modifier, long containerColor, long contentColor, Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        long containerColor2;
        long j;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Composer $composer2;
        final long containerColor3;
        final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function6;
        final Modifier modifier2;
        final long contentColor2;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Modifier.Companion modifier3;
        long contentColor3;
        Modifier modifier4;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function2M2308getLambda$1429684928$material3;
        int i2;
        long contentColor4;
        long containerColor4;
        Composer $composer3 = $composer.startRestartGroup(-1012974221);
        ComposerKt.sourceInformation($composer3, "C(PrimaryTabRow)N(selectedTabIndex,modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,indicator,divider,tabs)163@7987L76:TabRow.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer3.changed(containerColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = contentColor;
                int i5 = $composer3.changed(j) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                j = contentColor;
            }
            $dirty |= i5;
        } else {
            j = contentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            function3RememberComposableLambda = function3;
        } else if (($changed & 24576) == 0) {
            function3RememberComposableLambda = function3;
            $dirty |= $composer3.changedInstance(function3RememberComposableLambda) ? 16384 : 8192;
        } else {
            function3RememberComposableLambda = function3;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function2;
        } else if ((196608 & $changed) == 0) {
            function5 = function2;
            $dirty |= $composer3.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function2;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "152@7536L21,153@7600L19,154@7679L189");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier4 = modifier;
                function8 = function3RememberComposableLambda;
                function2M2308getLambda$1429684928$material3 = function5;
                i2 = -1012974221;
                containerColor4 = containerColor2;
                contentColor4 = j;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getPrimaryContainerColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) == 0) {
                    contentColor3 = j;
                } else {
                    contentColor3 = TabRowDefaults.INSTANCE.getPrimaryContentColor($composer3, 6);
                    $dirty &= -7169;
                }
                if (i6 != 0) {
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1338273762, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$PrimaryTabRow$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer, Integer num) {
                            invoke(tabIndicatorScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(TabIndicatorScope tabIndicatorScope, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C155@7704L158:TabRow.kt#uh7d8r");
                            int $dirty2 = $changed2;
                            if (($changed2 & 6) == 0) {
                                $dirty2 |= ($changed2 & 8) == 0 ? $composer4.changed(tabIndicatorScope) : $composer4.changedInstance(tabIndicatorScope) ? 4 : 2;
                            }
                            int $dirty3 = $dirty2;
                            if (!$composer4.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1338273762, $dirty3, -1, "androidx.compose.material3.PrimaryTabRow.<anonymous> (TabRow.kt:155)");
                            }
                            TabRowDefaults.INSTANCE.m2970PrimaryIndicator10LGxhE(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, selectedTabIndex, true), Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM(), 0.0f, 0L, null, $composer4, 196656, 28);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                if (i7 != 0) {
                    modifier4 = modifier3;
                    function2M2308getLambda$1429684928$material3 = ComposableSingletons$TabRowKt.INSTANCE.m2308getLambda$1429684928$material3();
                    contentColor4 = contentColor3;
                    function8 = function3RememberComposableLambda;
                    i2 = -1012974221;
                    containerColor4 = containerColor2;
                } else {
                    modifier4 = modifier3;
                    function8 = function3RememberComposableLambda;
                    function2M2308getLambda$1429684928$material3 = function5;
                    i2 = -1012974221;
                    contentColor4 = contentColor3;
                    containerColor4 = containerColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.PrimaryTabRow (TabRow.kt:162)");
            }
            $composer2 = $composer3;
            m2984TabRowImplDTcfvLk(modifier4, containerColor4, contentColor4, function8, function2M2308getLambda$1429684928$material3, function4, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            containerColor3 = containerColor4;
            modifier2 = modifier4;
            contentColor2 = contentColor4;
            function6 = function8;
            function7 = function2M2308getLambda$1429684928$material3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            containerColor3 = containerColor2;
            function6 = function3RememberComposableLambda;
            modifier2 = modifier;
            contentColor2 = j;
            function7 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.PrimaryTabRow_pAZo6Ak$lambda$0(selectedTabIndex, modifier2, containerColor3, contentColor2, function6, function7, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: SecondaryTabRow-pAZo6Ak, reason: not valid java name */
    public static final void m2982SecondaryTabRowpAZo6Ak(final int selectedTabIndex, Modifier modifier, long containerColor, long contentColor, Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        long containerColor2;
        long j;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Composer $composer2;
        final long containerColor3;
        final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function6;
        final Modifier modifier2;
        final long contentColor2;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Modifier.Companion modifier3;
        long contentColor3;
        Modifier modifier4;
        Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function2M2311getLambda$463596174$material3;
        int i2;
        long contentColor4;
        long containerColor4;
        Composer $composer3 = $composer.startRestartGroup(563434725);
        ComposerKt.sourceInformation($composer3, "C(SecondaryTabRow)N(selectedTabIndex,modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,indicator,divider,tabs)214@10853L76:TabRow.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer3.changed(containerColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = contentColor;
                int i5 = $composer3.changed(j) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                j = contentColor;
            }
            $dirty |= i5;
        } else {
            j = contentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            function3RememberComposableLambda = function3;
        } else if (($changed & 24576) == 0) {
            function3RememberComposableLambda = function3;
            $dirty |= $composer3.changedInstance(function3RememberComposableLambda) ? 16384 : 8192;
        } else {
            function3RememberComposableLambda = function3;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function2;
        } else if ((196608 & $changed) == 0) {
            function5 = function2;
            $dirty |= $composer3.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function2;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "203@10407L23,204@10473L21,206@10574L160");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier4 = modifier;
                function8 = function3RememberComposableLambda;
                function2M2311getLambda$463596174$material3 = function5;
                i2 = 563434725;
                containerColor4 = containerColor2;
                contentColor4 = j;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getSecondaryContainerColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) == 0) {
                    contentColor3 = j;
                } else {
                    contentColor3 = TabRowDefaults.INSTANCE.getSecondaryContentColor($composer3, 6);
                    $dirty &= -7169;
                }
                if (i6 != 0) {
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(959948692, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$SecondaryTabRow$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer, Integer num) {
                            invoke(tabIndicatorScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(TabIndicatorScope tabIndicatorScope, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C207@10603L121:TabRow.kt#uh7d8r");
                            int $dirty2 = $changed2;
                            if (($changed2 & 6) == 0) {
                                $dirty2 |= ($changed2 & 8) == 0 ? $composer4.changed(tabIndicatorScope) : $composer4.changedInstance(tabIndicatorScope) ? 4 : 2;
                            }
                            int $dirty3 = $dirty2;
                            if (!$composer4.shouldExecute(($dirty3 & 19) != 18, $dirty3 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(959948692, $dirty3, -1, "androidx.compose.material3.SecondaryTabRow.<anonymous> (TabRow.kt:207)");
                            }
                            TabRowDefaults.INSTANCE.m2971SecondaryIndicator9IZ8Weo(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, selectedTabIndex, false), 0.0f, 0L, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                if (i7 != 0) {
                    modifier4 = modifier3;
                    function2M2311getLambda$463596174$material3 = ComposableSingletons$TabRowKt.INSTANCE.m2311getLambda$463596174$material3();
                    contentColor4 = contentColor3;
                    function8 = function3RememberComposableLambda;
                    i2 = 563434725;
                    containerColor4 = containerColor2;
                } else {
                    modifier4 = modifier3;
                    function8 = function3RememberComposableLambda;
                    function2M2311getLambda$463596174$material3 = function5;
                    i2 = 563434725;
                    contentColor4 = contentColor3;
                    containerColor4 = containerColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.SecondaryTabRow (TabRow.kt:213)");
            }
            $composer2 = $composer3;
            m2984TabRowImplDTcfvLk(modifier4, containerColor4, contentColor4, function8, function2M2311getLambda$463596174$material3, function4, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            containerColor3 = containerColor4;
            modifier2 = modifier4;
            contentColor2 = contentColor4;
            function6 = function8;
            function7 = function2M2311getLambda$463596174$material3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            containerColor3 = containerColor2;
            function6 = function3RememberComposableLambda;
            modifier2 = modifier;
            contentColor2 = j;
            function7 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.SecondaryTabRow_pAZo6Ak$lambda$1(selectedTabIndex, modifier2, containerColor3, contentColor2, function6, function7, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: PrimaryScrollableTabRow-cx2KkNY, reason: not valid java name */
    public static final void m2974PrimaryScrollableTabRowcx2KkNY(final int selectedTabIndex, Modifier modifier, ScrollState scrollState, long containerColor, long contentColor, float edgePadding, Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, float minTabWidth, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        ScrollState scrollState2;
        long j;
        long j2;
        float f;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function2M2312getLambda$773954579$material3;
        final ScrollState scrollState3;
        final long contentColor2;
        final long contentColor3;
        final float edgePadding2;
        final Modifier modifier2;
        final ComposableLambda composableLambdaRememberComposableLambda;
        final float minTabWidth2;
        Modifier.Companion modifier3;
        ScrollState scrollState4;
        long containerColor2;
        long contentColor4;
        float edgePadding3;
        int $dirty;
        float minTabWidth3;
        ScrollState scrollState5;
        long containerColor3;
        float edgePadding4;
        long contentColor5;
        Composer $composer3 = $composer.startRestartGroup(450849184);
        ComposerKt.sourceInformation($composer3, "C(PrimaryScrollableTabRow)N(selectedTabIndex,modifier,scrollState,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,edgePadding:c#ui.unit.Dp,indicator,divider,minTabWidth:c#ui.unit.Dp,tabs)270@14011L363:TabRow.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                scrollState2 = scrollState;
                int i3 = $composer3.changed(scrollState2) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                scrollState2 = scrollState;
            }
            $dirty2 |= i3;
        } else {
            scrollState2 = scrollState;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = containerColor;
                int i4 = $composer3.changed(j) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                j = containerColor;
            }
            $dirty2 |= i4;
        } else {
            j = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                j2 = contentColor;
                int i5 = $composer3.changed(j2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                j2 = contentColor;
            }
            $dirty2 |= i5;
        } else {
            j2 = contentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = edgePadding;
        } else if ((196608 & $changed) == 0) {
            f = edgePadding;
            $dirty2 |= $composer3.changed(f) ? 131072 : 65536;
        } else {
            f = edgePadding;
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
            $dirty2 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(minTabWidth) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) != 0) {
            $dirty2 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 536870912 : 268435456;
        }
        if ($composer3.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "255@13328L21,256@13394L21,257@13458L19,260@13628L198");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                long j3 = j2;
                scrollState5 = scrollState2;
                contentColor5 = j3;
                float f2 = f;
                $dirty = $dirty2;
                containerColor3 = j;
                edgePadding4 = f2;
                modifier3 = modifier;
                composableLambdaRememberComposableLambda = function3;
                function2M2312getLambda$773954579$material3 = function2;
                minTabWidth3 = minTabWidth;
            } else {
                if (i2 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) != 0) {
                    scrollState4 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    $dirty2 &= -897;
                } else {
                    scrollState4 = scrollState2;
                }
                if ((i & 8) == 0) {
                    containerColor2 = j;
                } else {
                    containerColor2 = TabRowDefaults.INSTANCE.getPrimaryContainerColor($composer3, 6);
                    $dirty2 &= -7169;
                }
                if ((i & 16) == 0) {
                    contentColor4 = j2;
                } else {
                    contentColor4 = TabRowDefaults.INSTANCE.getPrimaryContentColor($composer3, 6);
                    $dirty2 &= -57345;
                }
                if (i6 == 0) {
                    edgePadding3 = f;
                } else {
                    edgePadding3 = TabRowDefaults.INSTANCE.m2972getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                }
                if (i7 == 0) {
                    composableLambdaRememberComposableLambda = function3;
                } else {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(835301263, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$PrimaryScrollableTabRow$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer, Integer num) {
                            invoke(tabIndicatorScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(TabIndicatorScope tabIndicatorScope, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C261@13657L159:TabRow.kt#uh7d8r");
                            int $dirty3 = $changed2;
                            if (($changed2 & 6) == 0) {
                                $dirty3 |= ($changed2 & 8) == 0 ? $composer4.changed(tabIndicatorScope) : $composer4.changedInstance(tabIndicatorScope) ? 4 : 2;
                            }
                            int $dirty4 = $dirty3;
                            if (!$composer4.shouldExecute(($dirty4 & 19) != 18, $dirty4 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(835301263, $dirty4, -1, "androidx.compose.material3.PrimaryScrollableTabRow.<anonymous> (TabRow.kt:261)");
                            }
                            TabRowDefaults.INSTANCE.m2970PrimaryIndicator10LGxhE(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, selectedTabIndex, true), Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM(), 0.0f, 0L, null, $composer4, 196656, 28);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                function2M2312getLambda$773954579$material3 = i8 != 0 ? ComposableSingletons$TabRowKt.INSTANCE.m2312getLambda$773954579$material3() : function2;
                if (i9 == 0) {
                    $dirty = $dirty2;
                    long j4 = contentColor4;
                    minTabWidth3 = minTabWidth;
                    scrollState5 = scrollState4;
                    containerColor3 = containerColor2;
                    edgePadding4 = edgePadding3;
                    contentColor5 = j4;
                } else {
                    $dirty = $dirty2;
                    long j5 = contentColor4;
                    scrollState5 = scrollState4;
                    containerColor3 = containerColor2;
                    minTabWidth3 = TabRowDefaults.INSTANCE.m2973getScrollableTabRowMinTabWidthD9Ej5fM();
                    edgePadding4 = edgePadding3;
                    contentColor5 = j5;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(450849184, $dirty, -1, "androidx.compose.material3.PrimaryScrollableTabRow (TabRow.kt:269)");
            }
            Modifier modifier4 = modifier3;
            m2978ScrollableTabRowImplxam5sdo(selectedTabIndex, modifier4, containerColor3, contentColor5, edgePadding4, minTabWidth3, scrollState5, composableLambdaRememberComposableLambda, function2M2312getLambda$773954579$material3, function4, $composer3, ($dirty & 14) | ($dirty & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 9) & 458752) | (($dirty << 12) & 3670016) | (($dirty << 3) & 29360128) | (($dirty << 3) & 234881024) | (1879048192 & $dirty));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            minTabWidth2 = minTabWidth3;
            long j6 = containerColor3;
            modifier2 = modifier4;
            scrollState3 = scrollState5;
            edgePadding2 = edgePadding4;
            contentColor3 = contentColor5;
            contentColor2 = j6;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function2M2312getLambda$773954579$material3 = function2;
            scrollState3 = scrollState2;
            contentColor2 = j;
            contentColor3 = j2;
            edgePadding2 = f;
            modifier2 = modifier;
            composableLambdaRememberComposableLambda = function3;
            minTabWidth2 = minTabWidth;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.PrimaryScrollableTabRow_cx2KkNY$lambda$2(selectedTabIndex, modifier2, scrollState3, contentColor2, contentColor3, edgePadding2, composableLambdaRememberComposableLambda, function2M2312getLambda$773954579$material3, minTabWidth2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: SecondaryScrollableTabRow-cx2KkNY, reason: not valid java name */
    public static final void m2980SecondaryScrollableTabRowcx2KkNY(final int selectedTabIndex, Modifier modifier, ScrollState scrollState, long containerColor, long contentColor, float edgePadding, Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, float minTabWidth, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        ScrollState scrollState2;
        long j;
        long j2;
        float f;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> lambda$303717663$material3;
        final ScrollState scrollState3;
        final long contentColor2;
        final long contentColor3;
        final float edgePadding2;
        final Modifier modifier2;
        final ComposableLambda composableLambdaRememberComposableLambda;
        final float minTabWidth2;
        Modifier.Companion modifier3;
        ScrollState scrollState4;
        long containerColor2;
        long contentColor4;
        float edgePadding3;
        int $dirty;
        float minTabWidth3;
        ScrollState scrollState5;
        long containerColor3;
        float edgePadding4;
        long contentColor5;
        Composer $composer3 = $composer.startRestartGroup(519094802);
        ComposerKt.sourceInformation($composer3, "C(SecondaryScrollableTabRow)N(selectedTabIndex,modifier,scrollState,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,edgePadding:c#ui.unit.Dp,indicator,divider,minTabWidth:c#ui.unit.Dp,tabs)340@17697L363:TabRow.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                scrollState2 = scrollState;
                int i3 = $composer3.changed(scrollState2) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                scrollState2 = scrollState;
            }
            $dirty2 |= i3;
        } else {
            scrollState2 = scrollState;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = containerColor;
                int i4 = $composer3.changed(j) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                j = containerColor;
            }
            $dirty2 |= i4;
        } else {
            j = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                j2 = contentColor;
                int i5 = $composer3.changed(j2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                j2 = contentColor;
            }
            $dirty2 |= i5;
        } else {
            j2 = contentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f = edgePadding;
        } else if ((196608 & $changed) == 0) {
            f = edgePadding;
            $dirty2 |= $composer3.changed(f) ? 131072 : 65536;
        } else {
            f = edgePadding;
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
            $dirty2 |= $composer3.changedInstance(function2) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(minTabWidth) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 512) != 0) {
            $dirty2 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 536870912 : 268435456;
        }
        if ($composer3.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "326@17048L21,327@17114L23,328@17180L21,331@17352L160");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                long j3 = j2;
                scrollState5 = scrollState2;
                contentColor5 = j3;
                float f2 = f;
                $dirty = $dirty2;
                containerColor3 = j;
                edgePadding4 = f2;
                modifier3 = modifier;
                composableLambdaRememberComposableLambda = function3;
                lambda$303717663$material3 = function2;
                minTabWidth3 = minTabWidth;
            } else {
                if (i2 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) != 0) {
                    scrollState4 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    $dirty2 &= -897;
                } else {
                    scrollState4 = scrollState2;
                }
                if ((i & 8) == 0) {
                    containerColor2 = j;
                } else {
                    containerColor2 = TabRowDefaults.INSTANCE.getSecondaryContainerColor($composer3, 6);
                    $dirty2 &= -7169;
                }
                if ((i & 16) == 0) {
                    contentColor4 = j2;
                } else {
                    contentColor4 = TabRowDefaults.INSTANCE.getSecondaryContentColor($composer3, 6);
                    $dirty2 &= -57345;
                }
                if (i6 == 0) {
                    edgePadding3 = f;
                } else {
                    edgePadding3 = TabRowDefaults.INSTANCE.m2972getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                }
                if (i7 == 0) {
                    composableLambdaRememberComposableLambda = function3;
                } else {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(610355265, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$SecondaryScrollableTabRow$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer, Integer num) {
                            invoke(tabIndicatorScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(TabIndicatorScope tabIndicatorScope, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C332@17381L121:TabRow.kt#uh7d8r");
                            int $dirty3 = $changed2;
                            if (($changed2 & 6) == 0) {
                                $dirty3 |= ($changed2 & 8) == 0 ? $composer4.changed(tabIndicatorScope) : $composer4.changedInstance(tabIndicatorScope) ? 4 : 2;
                            }
                            int $dirty4 = $dirty3;
                            if (!$composer4.shouldExecute(($dirty4 & 19) != 18, $dirty4 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(610355265, $dirty4, -1, "androidx.compose.material3.SecondaryScrollableTabRow.<anonymous> (TabRow.kt:332)");
                            }
                            TabRowDefaults.INSTANCE.m2971SecondaryIndicator9IZ8Weo(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, selectedTabIndex, false), 0.0f, 0L, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                lambda$303717663$material3 = i8 != 0 ? ComposableSingletons$TabRowKt.INSTANCE.getLambda$303717663$material3() : function2;
                if (i9 == 0) {
                    $dirty = $dirty2;
                    long j4 = contentColor4;
                    minTabWidth3 = minTabWidth;
                    scrollState5 = scrollState4;
                    containerColor3 = containerColor2;
                    edgePadding4 = edgePadding3;
                    contentColor5 = j4;
                } else {
                    $dirty = $dirty2;
                    long j5 = contentColor4;
                    scrollState5 = scrollState4;
                    containerColor3 = containerColor2;
                    minTabWidth3 = TabRowDefaults.INSTANCE.m2973getScrollableTabRowMinTabWidthD9Ej5fM();
                    edgePadding4 = edgePadding3;
                    contentColor5 = j5;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(519094802, $dirty, -1, "androidx.compose.material3.SecondaryScrollableTabRow (TabRow.kt:339)");
            }
            Modifier modifier4 = modifier3;
            m2978ScrollableTabRowImplxam5sdo(selectedTabIndex, modifier4, containerColor3, contentColor5, edgePadding4, minTabWidth3, scrollState5, composableLambdaRememberComposableLambda, lambda$303717663$material3, function4, $composer3, ($dirty & 14) | ($dirty & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 9) & 458752) | (($dirty << 12) & 3670016) | (($dirty << 3) & 29360128) | (($dirty << 3) & 234881024) | (1879048192 & $dirty));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            minTabWidth2 = minTabWidth3;
            long j6 = containerColor3;
            modifier2 = modifier4;
            scrollState3 = scrollState5;
            edgePadding2 = edgePadding4;
            contentColor3 = contentColor5;
            contentColor2 = j6;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            lambda$303717663$material3 = function2;
            scrollState3 = scrollState2;
            contentColor2 = j;
            contentColor3 = j2;
            edgePadding2 = f;
            modifier2 = modifier;
            composableLambdaRememberComposableLambda = function3;
            minTabWidth2 = minTabWidth;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.SecondaryScrollableTabRow_cx2KkNY$lambda$3(selectedTabIndex, modifier2, scrollState3, contentColor2, contentColor3, edgePadding2, composableLambdaRememberComposableLambda, lambda$303717663$material3, minTabWidth2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TabRowImpl-DTcfvLk, reason: not valid java name */
    private static final void m2984TabRowImplDTcfvLk(Modifier modifier, final long containerColor, final long contentColor, final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed) {
        Modifier modifier2;
        long j;
        long j2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1955286154);
        ComposerKt.sourceInformation($composer3, "C(TabRowImpl)N(modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,indicator,divider,tabs)403@19706L4058,399@19575L4189:TabRow.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            j = containerColor;
            $dirty |= $composer3.changed(j) ? 32 : 16;
        } else {
            j = containerColor;
        }
        if (($changed & 384) == 0) {
            j2 = contentColor;
            $dirty |= $composer3.changed(j2) ? 256 : 128;
        } else {
            j2 = contentColor;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 131072 : 65536;
        }
        if (!$composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1955286154, $dirty, -1, "androidx.compose.material3.TabRowImpl (TabRow.kt:398)");
            }
            $composer2 = $composer3;
            SurfaceKt.m2929SurfaceT9BRK9s(SelectableGroupKt.selectableGroup(modifier2), null, j, j2, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(830280655, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowImpl$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:50:0x01dc  */
                /* JADX WARN: Code duplicated, block: B:53:? A[RETURN, SYNTHETIC] */
                public final void invoke(Composer $composer4, int $changed2) {
                    int compositeKeyHash$iv$iv;
                    ComposerKt.sourceInformation($composer4, "C405@19861L11,406@19893L1219,440@21223L21,441@21257L2501,438@21122L2636:TabRow.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(830280655, $changed2, -1, "androidx.compose.material3.TabRowImpl.<anonymous> (TabRow.kt:405)");
                    }
                    FiniteAnimationSpec tabIndicatorAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer4, 6);
                    ComposerKt.sourceInformationMarkerStart($composer4, -964200398, "CC(remember):TabRow.kt#9igjgp");
                    Object it$iv = $composer4.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new TabRowKt$TabRowImpl$1$scope$1$1(tabIndicatorAnimationSpec);
                        $composer4.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    final TabRowKt$TabRowImpl$1$scope$1$1 scope = (TabRowKt$TabRowImpl$1$scope$1$1) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    Modifier modifier$iv$iv = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    final Function3<TabIndicatorScope, Composer, Integer, Unit> function5 = function3;
                    List listListOf = CollectionsKt.listOf((Object[]) new Function2[]{function4, function2, ComposableLambdaKt.rememberComposableLambda(-1333331860, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRowImpl$1.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer5, int $changed3) {
                            ComposerKt.sourceInformation($composer5, "C440@21231L11:TabRow.kt#uh7d8r");
                            if (!$composer5.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer5.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1333331860, $changed3, -1, "androidx.compose.material3.TabRowImpl.<anonymous>.<anonymous> (TabRow.kt:440)");
                            }
                            function5.invoke(scope, $composer5, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54)});
                    ComposerKt.sourceInformationMarkerStart($composer4, -964155468, "CC(remember):TabRow.kt#9igjgp");
                    Object it$iv2 = $composer4.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = (MultiContentMeasurePolicy) new TabRowKt$TabRowImpl$1$2$1(scope);
                        $composer4.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    MultiContentMeasurePolicy multiContentMeasurePolicy = (MultiContentMeasurePolicy) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerStart($composer4, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listListOf);
                    ComposerKt.sourceInformationMarkerStart($composer4, -290764973, "CC(remember):Layout.kt#9igjgp");
                    boolean invalid$iv$iv = (((432 & 896) ^ 384) > 256 && $composer4.changed(multiContentMeasurePolicy)) || (432 & 384) == 256;
                    Object it$iv$iv = $composer4.rememberedValue();
                    if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv$iv = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicy);
                        $composer4.updateRememberedValue(value$iv$iv);
                        it$iv$iv = value$iv$iv;
                    }
                    MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) it$iv$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    int $changed$iv$iv = 432 & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv$iv);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer4.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer4.startReusableNode();
                    if ($composer4.getInserting()) {
                        $composer4.createNode(constructor);
                    } else {
                        $composer4.useNode();
                    }
                    Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer4);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                        compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
                        if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
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
                    compositeKeyHash$iv$iv = compositeKeyHash$iv$iv2;
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
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
            }, $composer3, 54), $composer2, (($dirty << 3) & 896) | 12582912 | (($dirty << 3) & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.TabRowImpl_DTcfvLk$lambda$4(modifier3, containerColor, contentColor, function3, function2, function4, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ScrollableTabRowImpl-xam5sdo, reason: not valid java name */
    private static final void m2978ScrollableTabRowImplxam5sdo(final int selectedTabIndex, final Modifier modifier, final long containerColor, final long contentColor, final float edgePadding, final float minTabWidth, final ScrollState scrollState, final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed) {
        final int i;
        Modifier modifier2;
        float f;
        float f2;
        ScrollState scrollState2;
        final Function3<? super TabIndicatorScope, ? super Composer, ? super Integer, Unit> function5;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(414860860);
        ComposerKt.sourceInformation($composer3, "C(ScrollableTabRowImpl)N(selectedTabIndex,modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,edgePadding:c#ui.unit.Dp,minTabWidth:c#ui.unit.Dp,scrollState,indicator,divider,tabs)522@24247L5677,522@24165L5759:TabRow.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            i = selectedTabIndex;
            $dirty |= $composer3.changed(i) ? 4 : 2;
        } else {
            i = selectedTabIndex;
        }
        if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(containerColor) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(contentColor) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            f = edgePadding;
            $dirty |= $composer3.changed(f) ? 16384 : 8192;
        } else {
            f = edgePadding;
        }
        if ((196608 & $changed) == 0) {
            f2 = minTabWidth;
            $dirty |= $composer3.changed(f2) ? 131072 : 65536;
        } else {
            f2 = minTabWidth;
        }
        if ((1572864 & $changed) == 0) {
            scrollState2 = scrollState;
            $dirty |= $composer3.changed(scrollState2) ? 1048576 : 524288;
        } else {
            scrollState2 = scrollState;
        }
        if (($changed & 12582912) == 0) {
            function5 = function3;
            $dirty |= $composer3.changedInstance(function5) ? 8388608 : 4194304;
        } else {
            function5 = function3;
        }
        if ((100663296 & $changed) == 0) {
            function6 = function2;
            $dirty |= $composer3.changedInstance(function6) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            function6 = function2;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            function7 = function4;
            $dirty |= $composer3.changedInstance(function7) ? 536870912 : 268435456;
        } else {
            function7 = function4;
        }
        if (!$composer3.shouldExecute(($dirty & 306783379) != 306783378, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(414860860, $dirty, -1, "androidx.compose.material3.ScrollableTabRowImpl (TabRow.kt:521)");
            }
            final ScrollState scrollState3 = scrollState2;
            final Function2<? super Composer, ? super Integer, Unit> function8 = function6;
            final float f3 = f2;
            final float f4 = f;
            final Function2<? super Composer, ? super Integer, Unit> function9 = function7;
            $composer2 = $composer3;
            SurfaceKt.m2929SurfaceT9BRK9s(modifier2, null, containerColor, contentColor, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1878374785, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowImpl$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Code duplicated, block: B:60:0x02f5  */
                /* JADX WARN: Code duplicated, block: B:63:0x0341  */
                /* JADX WARN: Code duplicated, block: B:66:0x034d  */
                /* JADX WARN: Code duplicated, block: B:67:0x0353  */
                /* JADX WARN: Code duplicated, block: B:77:0x03f2  */
                /* JADX WARN: Code duplicated, block: B:80:? A[RETURN, SYNTHETIC] */
                public final void invoke(Composer $composer4, int $changed2) {
                    Function0<ComposeUiNode> function0;
                    Modifier modifier$iv;
                    MultiContentMeasurePolicy measurePolicy$iv;
                    boolean invalid$iv$iv;
                    Object it$iv$iv;
                    int compositeKeyHash$iv$iv;
                    Function0<ComposeUiNode> constructor;
                    Function0<ComposeUiNode> function1;
                    Composer $this$Layout_u24lambda_u240$iv$iv;
                    ComposerKt.sourceInformation($composer4, "C523@24278L24,525@24450L14,527@24579L7,529@24631L263,537@24916L1219,568@26144L3774:TabRow.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1878374785, $changed2, -1, "androidx.compose.material3.ScrollableTabRowImpl.<anonymous> (TabRow.kt:523)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer4, 773894976, "CC(rememberCoroutineScope)N(getContext)608@27648L68:Effects.kt#9igjgp");
                    ComposerKt.sourceInformationMarkerStart($composer4, 683737348, "CC(remember):Effects.kt#9igjgp");
                    Object it$iv$iv2 = $composer4.rememberedValue();
                    if (it$iv$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer4);
                        $composer4.updateRememberedValue(value$iv$iv);
                        it$iv$iv2 = value$iv$iv;
                    }
                    CoroutineScope coroutineScope = (CoroutineScope) it$iv$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    FiniteAnimationSpec scrollAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer4, 6);
                    FiniteAnimationSpec tabIndicatorAnimationSpec = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, $composer4, 6);
                    ComposerKt.sourceInformationMarkerStart($composer4, 1231916616, "CC(remember):TabRow.kt#9igjgp");
                    boolean invalid$iv = $composer4.changed(scrollState3) | $composer4.changed(coroutineScope);
                    ScrollState scrollState4 = scrollState3;
                    Object it$iv = $composer4.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = new ScrollableTabData(scrollState4, coroutineScope, scrollAnimationSpec);
                        $composer4.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    ScrollableTabData scrollableTabData = (ScrollableTabData) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerStart($composer4, 1231926692, "CC(remember):TabRow.kt#9igjgp");
                    Object it$iv2 = $composer4.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = new TabRowKt$ScrollableTabRowImpl$1$scope$1$1(tabIndicatorAnimationSpec);
                        $composer4.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    final TabRowKt$ScrollableTabRowImpl$1$scope$1$1 scope = (TabRowKt$ScrollableTabRowImpl$1$scope$1$1) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getBottomStart();
                    Function2<Composer, Integer, Unit> function10 = function8;
                    Function2<Composer, Integer, Unit> function11 = function9;
                    ScrollState scrollState5 = scrollState3;
                    float f5 = f4;
                    float f6 = f3;
                    int i2 = i;
                    final Function3<TabIndicatorScope, Composer, Integer, Unit> function12 = function5;
                    ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Modifier modifier$iv2 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv = (48 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    int compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv2);
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
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if ($this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                        $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                        $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash);
                    }
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                    int i3 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i4 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer4, 738154596, "C569@26204L9,571@26274L21,578@26594L3314,570@26226L3682:TabRow.kt#uh7d8r");
                    function10.invoke($composer4, 0);
                    List contents$iv = CollectionsKt.listOf((Object[]) new Function2[]{function11, ComposableLambdaKt.rememberComposableLambda(509386037, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRowImpl$1$1$1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer5, int $changed3) {
                            ComposerKt.sourceInformation($composer5, "C571@26282L11:TabRow.kt#uh7d8r");
                            if (!$composer5.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer5.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(509386037, $changed3, -1, "androidx.compose.material3.ScrollableTabRowImpl.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:571)");
                            }
                            function12.invoke(scope, $composer5, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54)});
                    Modifier modifierClipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), scrollState5, false, null, false, 14, null)));
                    ComposerKt.sourceInformationMarkerStart($composer4, 578012857, "CC(remember):TabRow.kt#9igjgp");
                    boolean invalid$iv2 = $composer4.changed(f5) | $composer4.changed(f6) | $composer4.changed(i2) | $composer4.changedInstance(scrollableTabData);
                    Object it$iv3 = $composer4.rememberedValue();
                    if (!invalid$iv2) {
                        modifier$iv = modifierClipToBounds;
                        if (it$iv3 != Composer.INSTANCE.getEmpty()) {
                        }
                        measurePolicy$iv = (MultiContentMeasurePolicy) it$iv3;
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerStart($composer4, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(contents$iv);
                        ComposerKt.sourceInformationMarkerStart($composer4, -290764973, "CC(remember):Layout.kt#9igjgp");
                        invalid$iv$iv = (((0 & 896) ^ 384) <= 256 && $composer4.changed(measurePolicy$iv)) || (0 & 384) == 256;
                        it$iv$iv = $composer4.rememberedValue();
                        if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            Object value$iv$iv2 = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy$iv);
                            $composer4.updateRememberedValue(value$iv$iv2);
                            it$iv$iv = value$iv$iv2;
                        }
                        MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) it$iv$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        int $changed$iv$iv2 = 0 & 112;
                        Modifier modifier$iv$iv = modifier$iv;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv2 = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer4, modifier$iv$iv);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv = $changed$iv$iv2 << 6;
                        int $changed$iv$iv$iv2 = ($changed$iv & 896) | 6;
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
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                        }
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        function2CombineAsVirtualLayouts.invoke($composer4, Integer.valueOf(($changed$iv$iv$iv2 >> 6) & 14));
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    modifier$iv = modifierClipToBounds;
                    Object value$iv3 = (MultiContentMeasurePolicy) new TabRowKt$ScrollableTabRowImpl$1$1$2$1(f5, f6, scope, i2, scrollableTabData);
                    $composer4.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                    measurePolicy$iv = (MultiContentMeasurePolicy) it$iv3;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerStart($composer4, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
                    Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts2 = LayoutKt.combineAsVirtualLayouts(contents$iv);
                    ComposerKt.sourceInformationMarkerStart($composer4, -290764973, "CC(remember):Layout.kt#9igjgp");
                    if (((0 & 896) ^ 384) <= 256) {
                    }
                    it$iv$iv = $composer4.rememberedValue();
                    if (invalid$iv$iv) {
                        Object value$iv$iv3 = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy$iv);
                        $composer4.updateRememberedValue(value$iv$iv3);
                        it$iv$iv = value$iv$iv3;
                    } else {
                        Object value$iv$iv4 = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy$iv);
                        $composer4.updateRememberedValue(value$iv$iv4);
                        it$iv$iv = value$iv$iv4;
                    }
                    MeasurePolicy measurePolicy$iv$iv2 = (MeasurePolicy) it$iv$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    int $changed$iv$iv3 = 0 & 112;
                    Modifier modifier$iv$iv2 = modifier$iv;
                    ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                    CompositionLocalMap localMap$iv$iv3 = $composer4.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer4, modifier$iv$iv2);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv2 = $changed$iv$iv3 << 6;
                    int $changed$iv$iv$iv3 = ($changed$iv2 & 896) | 6;
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
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                    }
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                    function2CombineAsVirtualLayouts2.invoke($composer4, Integer.valueOf(($changed$iv$iv$iv3 >> 6) & 14));
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    $composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | 12582912 | ($dirty & 896) | ($dirty & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.ScrollableTabRowImpl_xam5sdo$lambda$5(selectedTabIndex, modifier, containerColor, contentColor, edgePadding, minTabWidth, scrollState, function3, function2, function4, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TabRowWithSubcomposeImpl-DTcfvLk, reason: not valid java name */
    private static final void m2985TabRowWithSubcomposeImplDTcfvLk(Modifier modifier, final long containerColor, final long contentColor, final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed) {
        Modifier modifier2;
        long j;
        long j2;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(148841506);
        ComposerKt.sourceInformation($composer3, "C(TabRowWithSubcomposeImpl)N(modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,indicator,divider,tabs)767@33784L2218,763@33653L2349:TabRow.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            j = containerColor;
            $dirty |= $composer3.changed(j) ? 32 : 16;
        } else {
            j = containerColor;
        }
        if (($changed & 384) == 0) {
            j2 = contentColor;
            $dirty |= $composer3.changed(j2) ? 256 : 128;
        } else {
            j2 = contentColor;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 131072 : 65536;
        }
        if (!$composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(148841506, $dirty, -1, "androidx.compose.material3.TabRowWithSubcomposeImpl (TabRow.kt:762)");
            }
            $composer2 = $composer3;
            SurfaceKt.m2929SurfaceT9BRK9s(SelectableGroupKt.selectableGroup(modifier2), null, j, j2, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1815327065, true, new TabRowKt$TabRowWithSubcomposeImpl$1(function4, function2, function3), $composer3, 54), $composer2, (($dirty << 3) & 896) | 12582912 | (($dirty << 3) & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.TabRowWithSubcomposeImpl_DTcfvLk$lambda$6(modifier3, containerColor, contentColor, function3, function2, function4, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ScrollableTabRowWithSubcomposeImpl-qhFBPw4, reason: not valid java name */
    private static final void m2979ScrollableTabRowWithSubcomposeImplqhFBPw4(final int selectedTabIndex, final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, long containerColor, long contentColor, float edgePadding, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, final ScrollState scrollState, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long containerColor2;
        long contentColor2;
        float edgePadding2;
        Composer $composer2;
        final Modifier modifier3;
        final long containerColor3;
        final long contentColor3;
        final float edgePadding3;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        int $dirty;
        long contentColor4;
        float edgePadding4;
        Function2<? super Composer, ? super Integer, Unit> lambda$2075817209$material3;
        Modifier modifier4;
        long containerColor4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(901781420);
        ComposerKt.sourceInformation($composer3, "C(ScrollableTabRowWithSubcomposeImpl)N(selectedTabIndex,indicator,modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,edgePadding:c#ui.unit.Dp,divider,tabs,scrollState)835@36626L4172,835@36544L4254:TabRow.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function3) ? 32 : 16;
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
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer3.changed(containerColor2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty2 |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty2 |= i5;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            edgePadding2 = edgePadding;
        } else if ((196608 & $changed) == 0) {
            edgePadding2 = edgePadding;
            $dirty2 |= $composer3.changed(edgePadding2) ? 131072 : 65536;
        } else {
            edgePadding2 = edgePadding;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(scrollState) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (!$composer3.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            edgePadding3 = edgePadding2;
            function5 = function2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "828@36241L21,829@36305L19");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                $dirty = $dirty2;
                contentColor4 = contentColor2;
                edgePadding4 = edgePadding2;
                lambda$2075817209$material3 = function2;
                modifier4 = modifier2;
                containerColor4 = containerColor2;
                i2 = 901781420;
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 8) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getPrimaryContainerColor($composer3, 6);
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    contentColor2 = TabRowDefaults.INSTANCE.getPrimaryContentColor($composer3, 6);
                    $dirty2 &= -57345;
                }
                if (i6 != 0) {
                    edgePadding2 = TabRowDefaults.INSTANCE.m2972getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                }
                if (i7 != 0) {
                    $dirty = $dirty2;
                    contentColor4 = contentColor2;
                    lambda$2075817209$material3 = ComposableSingletons$TabRowKt.INSTANCE.getLambda$2075817209$material3();
                    containerColor4 = containerColor2;
                    edgePadding4 = edgePadding2;
                    modifier4 = modifier2;
                    i2 = 901781420;
                } else {
                    $dirty = $dirty2;
                    contentColor4 = contentColor2;
                    edgePadding4 = edgePadding2;
                    lambda$2075817209$material3 = function2;
                    modifier4 = modifier2;
                    containerColor4 = containerColor2;
                    i2 = 901781420;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.ScrollableTabRowWithSubcomposeImpl (TabRow.kt:834)");
            }
            float edgePadding5 = edgePadding4;
            Function2<? super Composer, ? super Integer, Unit> function6 = lambda$2075817209$material3;
            Function2 divider = new TabRowKt$ScrollableTabRowWithSubcomposeImpl$1(scrollState, edgePadding5, function4, function6, function3, selectedTabIndex);
            $composer2 = $composer3;
            SurfaceKt.m2929SurfaceT9BRK9s(modifier4, null, containerColor4, contentColor4, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(2077251399, true, divider, $composer3, 54), $composer2, (($dirty >> 6) & 14) | 12582912 | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            edgePadding3 = edgePadding5;
            function5 = function6;
            containerColor3 = containerColor4;
            contentColor3 = contentColor4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.ScrollableTabRowWithSubcomposeImpl_qhFBPw4$lambda$7(selectedTabIndex, function3, modifier3, containerColor3, contentColor3, edgePadding3, function5, function4, scrollState, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for Binary Compatibility.")
    /* JADX INFO: renamed from: PrimaryScrollableTabRow-qhFBPw4, reason: not valid java name */
    public static final /* synthetic */ void m2975PrimaryScrollableTabRowqhFBPw4(final int selectedTabIndex, Modifier modifier, ScrollState scrollState, long containerColor, long contentColor, float edgePadding, Function3 indicator, Function2 divider, final Function2 tabs, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        ScrollState scrollState2;
        long containerColor2;
        long contentColor2;
        float edgePadding2;
        Composer $composer2;
        final Modifier modifier3;
        final ScrollState scrollState3;
        final long containerColor3;
        final long contentColor3;
        final float edgePadding3;
        final Function3 indicator2;
        final Function2 divider2;
        ComposableLambda indicator3;
        ScrollState scrollState4;
        Function2 divider3;
        int $dirty;
        long containerColor4;
        float edgePadding4;
        Function3 indicator4;
        Modifier modifier4;
        long contentColor4;
        Composer $composer3 = $composer.startRestartGroup(1501129198);
        ComposerKt.sourceInformation($composer3, "C(PrimaryScrollableTabRow)N(selectedTabIndex,modifier,scrollState,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,edgePadding:c#ui.unit.Dp,indicator,divider,tabs)1205@51273L397:TabRow.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedTabIndex) ? 4 : 2;
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
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                scrollState2 = scrollState;
                int i3 = $composer3.changed(scrollState2) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                scrollState2 = scrollState;
            }
            $dirty2 |= i3;
        } else {
            scrollState2 = scrollState;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer3.changed(containerColor2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty2 |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty2 |= i5;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            edgePadding2 = edgePadding;
        } else if ((196608 & $changed) == 0) {
            edgePadding2 = edgePadding;
            $dirty2 |= $composer3.changed(edgePadding2) ? 131072 : 65536;
        } else {
            edgePadding2 = edgePadding;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(indicator) ? 1048576 : 524288;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(divider) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(tabs) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer3.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1191@50656L21,1192@50722L21,1193@50786L19,1196@50956L198");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                modifier4 = modifier2;
                scrollState4 = scrollState2;
                contentColor4 = contentColor2;
                divider3 = divider;
                $dirty = $dirty2;
                containerColor4 = containerColor2;
                edgePadding4 = edgePadding2;
                indicator4 = indicator;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                }
                if ((i & 8) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getPrimaryContainerColor($composer3, 6);
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    contentColor2 = TabRowDefaults.INSTANCE.getPrimaryContentColor($composer3, 6);
                }
                if (i6 != 0) {
                    edgePadding2 = TabRowDefaults.INSTANCE.m2972getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                }
                if (i7 == 0) {
                    indicator3 = indicator;
                } else {
                    indicator3 = ComposableLambdaKt.rememberComposableLambda(-1872002465, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$PrimaryScrollableTabRow$3
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer, Integer num) {
                            invoke(tabIndicatorScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(TabIndicatorScope tabIndicatorScope, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C1197@50985L159:TabRow.kt#uh7d8r");
                            int $dirty3 = $changed2;
                            if (($changed2 & 6) == 0) {
                                $dirty3 |= ($changed2 & 8) == 0 ? $composer4.changed(tabIndicatorScope) : $composer4.changedInstance(tabIndicatorScope) ? 4 : 2;
                            }
                            int $dirty4 = $dirty3;
                            if (!$composer4.shouldExecute(($dirty4 & 19) != 18, $dirty4 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1872002465, $dirty4, -1, "androidx.compose.material3.PrimaryScrollableTabRow.<anonymous> (TabRow.kt:1197)");
                            }
                            TabRowDefaults.INSTANCE.m2970PrimaryIndicator10LGxhE(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, selectedTabIndex, true), Dp.INSTANCE.m7925getUnspecifiedD9Ej5fM(), 0.0f, 0L, null, $composer4, 196656, 28);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                if (i8 != 0) {
                    long j = containerColor2;
                    indicator4 = indicator3;
                    modifier4 = modifier2;
                    edgePadding4 = edgePadding2;
                    long j2 = contentColor2;
                    divider3 = ComposableSingletons$TabRowKt.INSTANCE.m2309getLambda$306947391$material3();
                    $dirty = $dirty2;
                    scrollState4 = scrollState2;
                    containerColor4 = j;
                    contentColor4 = j2;
                } else {
                    scrollState4 = scrollState2;
                    long j3 = contentColor2;
                    divider3 = divider;
                    $dirty = $dirty2;
                    containerColor4 = containerColor2;
                    edgePadding4 = edgePadding2;
                    indicator4 = indicator3;
                    modifier4 = modifier2;
                    contentColor4 = j3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1501129198, $dirty, -1, "androidx.compose.material3.PrimaryScrollableTabRow (TabRow.kt:1205)");
            }
            m2974PrimaryScrollableTabRowcx2KkNY(selectedTabIndex, modifier4, scrollState4, containerColor4, contentColor4, edgePadding4, indicator4, divider3, TabRowDefaults.INSTANCE.m2973getScrollableTabRowMinTabWidthD9Ej5fM(), tabs, $composer3, ($dirty & 14) | 100663296 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (($dirty << 3) & 1879048192), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            divider2 = divider3;
            indicator2 = indicator4;
            edgePadding3 = edgePadding4;
            contentColor3 = contentColor4;
            containerColor3 = containerColor4;
            scrollState3 = scrollState4;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            scrollState3 = scrollState2;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            edgePadding3 = edgePadding2;
            indicator2 = indicator;
            divider2 = divider;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.PrimaryScrollableTabRow_qhFBPw4$lambda$8(selectedTabIndex, modifier3, scrollState3, containerColor3, contentColor3, edgePadding3, indicator2, divider2, tabs, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for Binary Compatibility.")
    /* JADX INFO: renamed from: SecondaryScrollableTabRow-qhFBPw4, reason: not valid java name */
    public static final /* synthetic */ void m2981SecondaryScrollableTabRowqhFBPw4(final int selectedTabIndex, Modifier modifier, ScrollState scrollState, long containerColor, long contentColor, float edgePadding, Function3 indicator, Function2 divider, final Function2 tabs, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        ScrollState scrollState2;
        long containerColor2;
        long contentColor2;
        float edgePadding2;
        Composer $composer2;
        final Modifier modifier3;
        final ScrollState scrollState3;
        final long containerColor3;
        final long contentColor3;
        final float edgePadding3;
        final Function3 indicator2;
        final Function2 divider2;
        ComposableLambda indicator3;
        ScrollState scrollState4;
        Function2 divider3;
        int $dirty;
        long containerColor4;
        float edgePadding4;
        Function3 indicator4;
        Modifier modifier4;
        long contentColor4;
        Composer $composer3 = $composer.startRestartGroup(-712886596);
        ComposerKt.sourceInformation($composer3, "C(SecondaryScrollableTabRow)N(selectedTabIndex,modifier,scrollState,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,edgePadding:c#ui.unit.Dp,indicator,divider,tabs)1236@52486L399:TabRow.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedTabIndex) ? 4 : 2;
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
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                scrollState2 = scrollState;
                int i3 = $composer3.changed(scrollState2) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                scrollState2 = scrollState;
            }
            $dirty2 |= i3;
        } else {
            scrollState2 = scrollState;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer3.changed(containerColor2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty2 |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                int i5 = $composer3.changed(contentColor2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                contentColor2 = contentColor;
            }
            $dirty2 |= i5;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            edgePadding2 = edgePadding;
        } else if ((196608 & $changed) == 0) {
            edgePadding2 = edgePadding;
            $dirty2 |= $composer3.changed(edgePadding2) ? 131072 : 65536;
        } else {
            edgePadding2 = edgePadding;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(indicator) ? 1048576 : 524288;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(divider) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(tabs) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer3.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1223@51903L21,1224@51969L23,1225@52035L21,1228@52207L160");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                modifier4 = modifier2;
                scrollState4 = scrollState2;
                contentColor4 = contentColor2;
                divider3 = divider;
                $dirty = $dirty2;
                containerColor4 = containerColor2;
                edgePadding4 = edgePadding2;
                indicator4 = indicator;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                }
                if ((i & 8) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getSecondaryContainerColor($composer3, 6);
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                    contentColor2 = TabRowDefaults.INSTANCE.getSecondaryContentColor($composer3, 6);
                }
                if (i6 != 0) {
                    edgePadding2 = TabRowDefaults.INSTANCE.m2972getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                }
                if (i7 == 0) {
                    indicator3 = indicator;
                } else {
                    indicator3 = ComposableLambdaKt.rememberComposableLambda(407893741, true, new Function3<TabIndicatorScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$SecondaryScrollableTabRow$3
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TabIndicatorScope tabIndicatorScope, Composer composer, Integer num) {
                            invoke(tabIndicatorScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(TabIndicatorScope tabIndicatorScope, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C1229@52236L121:TabRow.kt#uh7d8r");
                            int $dirty3 = $changed2;
                            if (($changed2 & 6) == 0) {
                                $dirty3 |= ($changed2 & 8) == 0 ? $composer4.changed(tabIndicatorScope) : $composer4.changedInstance(tabIndicatorScope) ? 4 : 2;
                            }
                            int $dirty4 = $dirty3;
                            if (!$composer4.shouldExecute(($dirty4 & 19) != 18, $dirty4 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(407893741, $dirty4, -1, "androidx.compose.material3.SecondaryScrollableTabRow.<anonymous> (TabRow.kt:1229)");
                            }
                            TabRowDefaults.INSTANCE.m2971SecondaryIndicator9IZ8Weo(tabIndicatorScope.tabIndicatorOffset(Modifier.INSTANCE, selectedTabIndex, false), 0.0f, 0L, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                if (i8 != 0) {
                    long j = containerColor2;
                    indicator4 = indicator3;
                    modifier4 = modifier2;
                    edgePadding4 = edgePadding2;
                    long j2 = contentColor2;
                    divider3 = ComposableSingletons$TabRowKt.INSTANCE.getLambda$1187266255$material3();
                    $dirty = $dirty2;
                    scrollState4 = scrollState2;
                    containerColor4 = j;
                    contentColor4 = j2;
                } else {
                    scrollState4 = scrollState2;
                    long j3 = contentColor2;
                    divider3 = divider;
                    $dirty = $dirty2;
                    containerColor4 = containerColor2;
                    edgePadding4 = edgePadding2;
                    indicator4 = indicator3;
                    modifier4 = modifier2;
                    contentColor4 = j3;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-712886596, $dirty, -1, "androidx.compose.material3.SecondaryScrollableTabRow (TabRow.kt:1236)");
            }
            m2980SecondaryScrollableTabRowcx2KkNY(selectedTabIndex, modifier4, scrollState4, containerColor4, contentColor4, edgePadding4, indicator4, divider3, TabRowDefaults.INSTANCE.m2973getScrollableTabRowMinTabWidthD9Ej5fM(), tabs, $composer3, ($dirty & 14) | 100663296 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (($dirty << 3) & 1879048192), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            divider2 = divider3;
            indicator2 = indicator4;
            edgePadding3 = edgePadding4;
            contentColor3 = contentColor4;
            containerColor3 = containerColor4;
            scrollState3 = scrollState4;
            modifier3 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            scrollState3 = scrollState2;
            containerColor3 = containerColor2;
            contentColor3 = contentColor2;
            edgePadding3 = edgePadding2;
            indicator2 = indicator;
            divider2 = divider;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.SecondaryScrollableTabRow_qhFBPw4$lambda$9(selectedTabIndex, modifier3, scrollState3, containerColor3, contentColor3, edgePadding3, indicator2, divider2, tabs, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Replaced with PrimaryTabRow and SecondaryTabRow.", replaceWith = @ReplaceWith(expression = "SecondaryTabRow(selectedTabIndex, modifier, containerColor, contentColor, indicator, divider, tabs)", imports = {}))
    /* JADX INFO: renamed from: TabRow-pAZo6Ak, reason: not valid java name */
    public static final void m2983TabRowpAZo6Ak(final int selectedTabIndex, Modifier modifier, long containerColor, long contentColor, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        long containerColor2;
        long j;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Composer $composer2;
        final long containerColor3;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function6;
        final Modifier modifier2;
        final long contentColor2;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Modifier.Companion modifier3;
        long contentColor3;
        Modifier modifier4;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function2M2307getLambda$1132537920$material3;
        int i2;
        long contentColor4;
        long containerColor4;
        Composer $composer3 = $composer.startRestartGroup(1445190381);
        ComposerKt.sourceInformation($composer3, "C(TabRow)N(selectedTabIndex,modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,indicator,divider,tabs)1349@57943L90:TabRow.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                containerColor2 = containerColor;
                int i4 = $composer3.changed(containerColor2) ? 256 : 128;
                $dirty |= i4;
            } else {
                containerColor2 = containerColor;
            }
            $dirty |= i4;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = contentColor;
                int i5 = $composer3.changed(j) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                j = contentColor;
            }
            $dirty |= i5;
        } else {
            j = contentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            function3RememberComposableLambda = function3;
        } else if (($changed & 24576) == 0) {
            function3RememberComposableLambda = function3;
            $dirty |= $composer3.changedInstance(function3RememberComposableLambda) ? 16384 : 8192;
        } else {
            function3RememberComposableLambda = function3;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function2;
        } else if ((196608 & $changed) == 0) {
            function5 = function2;
            $dirty |= $composer3.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function2;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1336@57402L21,1337@57466L19,1339@57578L246");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier4 = modifier;
                function8 = function3RememberComposableLambda;
                function2M2307getLambda$1132537920$material3 = function5;
                i2 = 1445190381;
                containerColor4 = containerColor2;
                contentColor4 = j;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getPrimaryContainerColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) == 0) {
                    contentColor3 = j;
                } else {
                    contentColor3 = TabRowDefaults.INSTANCE.getPrimaryContentColor($composer3, 6);
                    $dirty &= -7169;
                }
                if (i6 != 0) {
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(906699528, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$TabRow$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                            invoke((List<TabPosition>) list, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(List<TabPosition> list, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "CN(tabPositions):TabRow.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(906699528, $changed2, -1, "androidx.compose.material3.TabRow.<anonymous> (TabRow.kt:1340)");
                            }
                            if (selectedTabIndex < list.size()) {
                                $composer4.startReplaceGroup(436390614);
                                ComposerKt.sourceInformation($composer4, "1341@57683L117");
                                TabRowDefaults.INSTANCE.m2971SecondaryIndicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, list.get(selectedTabIndex)), 0.0f, 0L, $composer4, 3072, 6);
                                $composer4.endReplaceGroup();
                            } else {
                                $composer4.startReplaceGroup(436548218);
                                $composer4.endReplaceGroup();
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                if (i7 != 0) {
                    modifier4 = modifier3;
                    function2M2307getLambda$1132537920$material3 = ComposableSingletons$TabRowKt.INSTANCE.m2307getLambda$1132537920$material3();
                    contentColor4 = contentColor3;
                    function8 = function3RememberComposableLambda;
                    i2 = 1445190381;
                    containerColor4 = containerColor2;
                } else {
                    modifier4 = modifier3;
                    function8 = function3RememberComposableLambda;
                    function2M2307getLambda$1132537920$material3 = function5;
                    i2 = 1445190381;
                    contentColor4 = contentColor3;
                    containerColor4 = containerColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.TabRow (TabRow.kt:1348)");
            }
            $composer2 = $composer3;
            m2985TabRowWithSubcomposeImplDTcfvLk(modifier4, containerColor4, contentColor4, function8, function2M2307getLambda$1132537920$material3, function4, $composer2, (($dirty >> 3) & 14) | (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            containerColor3 = containerColor4;
            modifier2 = modifier4;
            contentColor2 = contentColor4;
            function6 = function8;
            function7 = function2M2307getLambda$1132537920$material3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            containerColor3 = containerColor2;
            function6 = function3RememberComposableLambda;
            modifier2 = modifier;
            contentColor2 = j;
            function7 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.TabRow_pAZo6Ak$lambda$10(selectedTabIndex, modifier2, containerColor3, contentColor2, function6, function7, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Replaced with PrimaryScrollableTabRow and SecondaryScrollableTabRow tab variants.", replaceWith = @ReplaceWith(expression = "SecondaryScrollableTabRow(selectedTabIndex, modifier, containerColor, contentColor, edgePadding, indicator, divider, tabs)", imports = {}))
    /* JADX INFO: renamed from: ScrollableTabRow-sKfQg0A, reason: not valid java name */
    public static final void m2977ScrollableTabRowsKfQg0A(final int selectedTabIndex, Modifier modifier, long containerColor, long contentColor, float edgePadding, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        long containerColor2;
        long j;
        float f;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function5;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final long contentColor2;
        final long containerColor3;
        final float edgePadding2;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function7;
        final Modifier modifier2;
        Modifier.Companion modifier3;
        long contentColor3;
        ComposableLambda composableLambdaRememberComposableLambda;
        int $dirty;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function8;
        Function2<? super Composer, ? super Integer, Unit> function2M2310getLambda$358046007$material3;
        Composer $composer3 = $composer.startRestartGroup(847049916);
        ComposerKt.sourceInformation($composer3, "C(ScrollableTabRow)N(selectedTabIndex,modifier,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,edgePadding:c#ui.unit.Dp,indicator,divider,tabs)1423@61760L21,1414@61436L352:TabRow.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                containerColor2 = containerColor;
                int i3 = $composer3.changed(containerColor2) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                containerColor2 = containerColor;
            }
            $dirty2 |= i3;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j = contentColor;
                int i4 = $composer3.changed(j) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                j = contentColor;
            }
            $dirty2 |= i4;
        } else {
            j = contentColor;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            f = edgePadding;
        } else if (($changed & 24576) == 0) {
            f = edgePadding;
            $dirty2 |= $composer3.changed(f) ? 16384 : 8192;
        } else {
            f = edgePadding;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
        } else if ((196608 & $changed) == 0) {
            function5 = function3;
            $dirty2 |= $composer3.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function3;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute(($dirty2 & 4793491) != 4793490, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "1402@60906L21,1403@60970L19,1406@61153L164");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                $dirty = $dirty2;
                contentColor2 = containerColor2;
                containerColor3 = j;
                edgePadding2 = f;
                function8 = function5;
                modifier2 = modifier;
                function2M2310getLambda$358046007$material3 = function2;
            } else {
                if (i2 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) != 0) {
                    containerColor2 = TabRowDefaults.INSTANCE.getPrimaryContainerColor($composer3, 6);
                    $dirty2 &= -897;
                }
                if ((i & 8) == 0) {
                    contentColor3 = j;
                } else {
                    contentColor3 = TabRowDefaults.INSTANCE.getPrimaryContentColor($composer3, 6);
                    $dirty2 &= -7169;
                }
                if (i5 == 0) {
                    edgePadding2 = f;
                } else {
                    edgePadding2 = TabRowDefaults.INSTANCE.m2972getScrollableTabRowEdgeStartPaddingD9Ej5fM();
                }
                if (i6 == 0) {
                    composableLambdaRememberComposableLambda = function5;
                } else {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-720441215, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabRowKt$ScrollableTabRow$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                            invoke((List<TabPosition>) list, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(List<TabPosition> list, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "CN(tabPositions)1407@61198L109:TabRow.kt#uh7d8r");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-720441215, $changed2, -1, "androidx.compose.material3.ScrollableTabRow.<anonymous> (TabRow.kt:1407)");
                            }
                            TabRowDefaults.INSTANCE.m2971SecondaryIndicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, list.get(selectedTabIndex)), 0.0f, 0L, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                if (i7 != 0) {
                    int i8 = $dirty2;
                    modifier2 = modifier3;
                    function8 = composableLambdaRememberComposableLambda;
                    function2M2310getLambda$358046007$material3 = ComposableSingletons$TabRowKt.INSTANCE.m2310getLambda$358046007$material3();
                    $dirty = i8;
                    long j2 = containerColor2;
                    containerColor3 = contentColor3;
                    contentColor2 = j2;
                } else {
                    long j3 = containerColor2;
                    containerColor3 = contentColor3;
                    contentColor2 = j3;
                    $dirty = $dirty2;
                    modifier2 = modifier3;
                    function8 = composableLambdaRememberComposableLambda;
                    function2M2310getLambda$358046007$material3 = function2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(847049916, $dirty, -1, "androidx.compose.material3.ScrollableTabRow (TabRow.kt:1413)");
            }
            m2979ScrollableTabRowWithSubcomposeImplqhFBPw4(selectedTabIndex, function8, modifier2, contentColor2, containerColor3, edgePadding2, function2M2310getLambda$358046007$material3, function4, ScrollKt.rememberScrollState(0, $composer3, 0, 1), $composer3, ($dirty & 14) | (($dirty >> 12) & 112) | (($dirty << 3) & 896) | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (3670016 & $dirty) | (29360128 & $dirty), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function6 = function2M2310getLambda$358046007$material3;
            function7 = function8;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function6 = function2;
            contentColor2 = containerColor2;
            containerColor3 = j;
            edgePadding2 = f;
            function7 = function5;
            modifier2 = modifier;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TabRowKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.ScrollableTabRow_sKfQg0A$lambda$11(selectedTabIndex, modifier2, contentColor2, containerColor3, edgePadding2, function7, function6, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
