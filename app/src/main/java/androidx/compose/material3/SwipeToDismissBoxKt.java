package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.AnchoredDraggableDefaults;
import androidx.compose.foundation.gestures.AnchoredDraggableKt;
import androidx.compose.foundation.gestures.AnchoredDraggableState;
import androidx.compose.foundation.gestures.DraggableAnchorsConfig;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.TargetedFlingBehavior;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.material3.internal.DraggableAnchorsKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImplKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SwipeToDismissBox.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a<\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¢\u0006\u0002\u0010\n\u001aR\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u00052#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¢\u0006\u0002\u0010\r\u001a\u008f\u0001\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00012\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\f2\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u00052\u001c\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0002\u0010\u001c\u001ay\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00012\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\f2\u001c\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0002\u0010\u001d\"\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 ¨\u0006!"}, d2 = {"rememberSwipeToDismissBoxState", "Landroidx/compose/material3/SwipeToDismissBoxState;", "initialValue", "Landroidx/compose/material3/SwipeToDismissBoxValue;", "positionalThreshold", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "totalDistance", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SwipeToDismissBoxState;", "confirmValueChange", "", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SwipeToDismissBoxState;", "SwipeToDismissBox", "", "state", "backgroundContent", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "enableDismissFromStartToEnd", "enableDismissFromEndToStart", "gesturesEnabled", "onDismiss", UriUtil.LOCAL_CONTENT_SCHEME, "(Landroidx/compose/material3/SwipeToDismissBoxState;Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;ZZZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/SwipeToDismissBoxState;Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;ZZZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DismissVelocityThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SwipeToDismissBoxKt {
    private static final float DismissVelocityThreshold = Dp.m7905constructorimpl(ComposerImplKt.nodeKey);

    static final Unit SwipeToDismissBox$lambda$13(SwipeToDismissBoxState swipeToDismissBoxState, Function3 function3, Modifier modifier, boolean z, boolean z2, boolean z3, Function1 function1, Function3 function4, int i, int i2, Composer composer, int i3) {
        SwipeToDismissBox(swipeToDismissBoxState, function3, modifier, z, z2, z3, function1, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SwipeToDismissBox$lambda$16(SwipeToDismissBoxState swipeToDismissBoxState, Function3 function3, Modifier modifier, boolean z, boolean z2, boolean z3, Function3 function4, int i, int i2, Composer composer, int i3) {
        SwipeToDismissBox(swipeToDismissBoxState, function3, modifier, z, z2, z3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final SwipeToDismissBoxState rememberSwipeToDismissBoxState(final SwipeToDismissBoxValue initialValue, final Function1<? super Float, Float> function1, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -470572556, "C(rememberSwipeToDismissBoxState)N(initialValue,positionalThreshold)242@9960L19,246@10132L73,244@10020L185:SwipeToDismissBox.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialValue = SwipeToDismissBoxValue.Settled;
        }
        if ((i & 2) != 0) {
            function1 = SwipeToDismissBoxDefaults.INSTANCE.getPositionalThreshold($composer, 6);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-470572556, $changed, -1, "androidx.compose.material3.rememberSwipeToDismissBoxState (SwipeToDismissBox.kt:243)");
        }
        Object[] objArr = new Object[0];
        Saver<SwipeToDismissBoxState, SwipeToDismissBoxValue> Saver = SwipeToDismissBoxState.INSTANCE.Saver(function1);
        ComposerKt.sourceInformationMarkerStart($composer, -1248647747, "CC(remember):SwipeToDismissBox.kt#9igjgp");
        boolean z = true;
        boolean z2 = ((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue.ordinal())) || ($changed & 6) == 4;
        if (((($changed & 112) ^ 48) <= 32 || !$composer.changed(function1)) && ($changed & 48) != 32) {
            z = false;
        }
        boolean invalid$iv = z2 | z;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SwipeToDismissBoxKt.rememberSwipeToDismissBoxState$lambda$1$lambda$0(initialValue, function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SwipeToDismissBoxState swipeToDismissBoxState = (SwipeToDismissBoxState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return swipeToDismissBoxState;
    }

    static final SwipeToDismissBoxState rememberSwipeToDismissBoxState$lambda$1$lambda$0(SwipeToDismissBoxValue $initialValue, Function1 $positionalThreshold) {
        return new SwipeToDismissBoxState($initialValue, $positionalThreshold);
    }

    static final boolean rememberSwipeToDismissBoxState$lambda$3$lambda$2(SwipeToDismissBoxValue it) {
        return true;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = DraggableAnchorsKt.ConfirmValueChangeDeprecated, replaceWith = @ReplaceWith(expression = "rememberSwipeToDismissBoxState(initialValue, positionalThreshold)", imports = {}))
    public static final SwipeToDismissBoxState rememberSwipeToDismissBoxState(final SwipeToDismissBoxValue initialValue, final Function1<? super SwipeToDismissBoxValue, Boolean> function1, final Function1<? super Float, Float> function2, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -246335487, "C(rememberSwipeToDismissBoxState)N(initialValue,confirmValueChange,positionalThreshold)270@11214L8,272@11317L19,274@11397L7,282@11663L102,275@11416L349:SwipeToDismissBox.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialValue = SwipeToDismissBoxValue.Settled;
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -505188695, "CC(remember):SwipeToDismissBox.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(SwipeToDismissBoxKt.rememberSwipeToDismissBoxState$lambda$3$lambda$2((SwipeToDismissBoxValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            function1 = (Function1) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        if ((i & 4) != 0) {
            function2 = SwipeToDismissBoxDefaults.INSTANCE.getPositionalThreshold($composer, 6);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-246335487, $changed, -1, "androidx.compose.material3.rememberSwipeToDismissBoxState (SwipeToDismissBox.kt:273)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        Object[] objArr = new Object[0];
        Saver<SwipeToDismissBoxState, SwipeToDismissBoxValue> Saver = SwipeToDismissBoxState.INSTANCE.Saver(function1, function2, density);
        ComposerKt.sourceInformationMarkerStart($composer, -505174233, "CC(remember):SwipeToDismissBox.kt#9igjgp");
        boolean z = true;
        boolean zChanged = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue.ordinal())) || ($changed & 6) == 4) | $composer.changed(density) | (((($changed & 112) ^ 48) > 32 && $composer.changed(function1)) || ($changed & 48) == 32);
        if (((($changed & 896) ^ 384) <= 256 || !$composer.changed(function2)) && ($changed & 384) != 256) {
            z = false;
        }
        boolean invalid$iv = zChanged | z;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SwipeToDismissBoxKt.rememberSwipeToDismissBoxState$lambda$5$lambda$4(initialValue, density, function1, function2);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SwipeToDismissBoxState swipeToDismissBoxState = (SwipeToDismissBoxState) RememberSaveableKt.m4538rememberSaveable(objArr, (Saver) Saver, (Function0) it$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return swipeToDismissBoxState;
    }

    static final SwipeToDismissBoxState rememberSwipeToDismissBoxState$lambda$5$lambda$4(SwipeToDismissBoxValue $initialValue, Density $density, Function1 $confirmValueChange, Function1 $positionalThreshold) {
        return new SwipeToDismissBoxState($initialValue, $density, $confirmValueChange, $positionalThreshold);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0126  */
    /* JADX WARN: Code duplicated, block: B:102:0x0129  */
    /* JADX WARN: Code duplicated, block: B:103:0x012b  */
    /* JADX WARN: Code duplicated, block: B:106:0x0131  */
    /* JADX WARN: Code duplicated, block: B:108:0x0148  */
    /* JADX WARN: Code duplicated, block: B:111:0x015e  */
    /* JADX WARN: Code duplicated, block: B:114:0x0166  */
    /* JADX WARN: Code duplicated, block: B:120:0x0183  */
    /* JADX WARN: Code duplicated, block: B:123:0x018c  */
    /* JADX WARN: Code duplicated, block: B:124:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:127:0x0233  */
    /* JADX WARN: Code duplicated, block: B:130:0x023f  */
    /* JADX WARN: Code duplicated, block: B:131:0x0245  */
    /* JADX WARN: Code duplicated, block: B:141:0x035a  */
    /* JADX WARN: Code duplicated, block: B:144:0x0366  */
    /* JADX WARN: Code duplicated, block: B:145:0x036c  */
    /* JADX WARN: Code duplicated, block: B:155:0x041e  */
    /* JADX WARN: Code duplicated, block: B:156:0x0421  */
    /* JADX WARN: Code duplicated, block: B:159:0x042c  */
    /* JADX WARN: Code duplicated, block: B:160:0x042f  */
    /* JADX WARN: Code duplicated, block: B:170:0x04cc  */
    /* JADX WARN: Code duplicated, block: B:173:0x04d8  */
    /* JADX WARN: Code duplicated, block: B:174:0x04de  */
    /* JADX WARN: Code duplicated, block: B:184:0x05a5  */
    /* JADX WARN: Code duplicated, block: B:185:0x05a8  */
    /* JADX WARN: Code duplicated, block: B:191:0x05be  */
    /* JADX WARN: Code duplicated, block: B:194:0x05e1  */
    /* JADX WARN: Code duplicated, block: B:196:0x05ed  */
    /* JADX WARN: Code duplicated, block: B:199:0x05ff  */
    /* JADX WARN: Code duplicated, block: B:201:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x0107  */
    /* JADX WARN: Code duplicated, block: B:90:0x010a  */
    /* JADX WARN: Code duplicated, block: B:93:0x0113 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:94:0x0115  */
    /* JADX WARN: Code duplicated, block: B:95:0x011c  */
    /* JADX WARN: Code duplicated, block: B:97:0x0120  */
    /* JADX WARN: Code duplicated, block: B:99:0x0124  */
    public static final void SwipeToDismissBox(final SwipeToDismissBoxState state, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, boolean enableDismissFromStartToEnd, boolean enableDismissFromEndToStart, boolean gesturesEnabled, Function1<? super SwipeToDismissBoxValue, Unit> function1, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final boolean enableDismissFromStartToEnd2;
        boolean z;
        int i2;
        int $dirty;
        boolean z2;
        final boolean gesturesEnabled2;
        final Modifier modifier3;
        final boolean enableDismissFromStartToEnd3;
        final boolean enableDismissFromEndToStart2;
        final Function1<? super SwipeToDismissBoxValue, Unit> function2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        final boolean enableDismissFromEndToStart3;
        boolean gesturesEnabled3;
        Function1<? super SwipeToDismissBoxValue, Unit> function5;
        boolean z3;
        TargetedFlingBehavior targetedFlingBehaviorFlingBehavior;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        int compositeKeyHash$iv$iv2;
        Function0<ComposeUiNode> constructor2;
        Function0<ComposeUiNode> function6;
        Composer $this$Layout_u24lambda_u240$iv$iv2;
        Function1<? super SwipeToDismissBoxValue, Unit> function7;
        boolean z4;
        boolean z5;
        boolean invalid$iv;
        Object value$iv;
        int compositeKeyHash$iv$iv3;
        Function0<ComposeUiNode> constructor3;
        Function0<ComposeUiNode> function8;
        Composer $this$Layout_u24lambda_u240$iv$iv3;
        boolean z6;
        boolean invalid$iv2;
        Object it$iv;
        Function1<? super SwipeToDismissBoxValue, Unit> function9;
        Object it$iv2;
        Composer $composer2 = $composer.startRestartGroup(-741495334);
        ComposerKt.sourceInformation($composer2, "C(SwipeToDismissBox)N(state,backgroundContent,modifier,enableDismissFromStartToEnd,enableDismissFromEndToStart,gesturesEnabled,onDismiss,content)311@13044L2,314@13102L1524,350@14677L200,350@14631L246:SwipeToDismissBox.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(state) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            enableDismissFromStartToEnd2 = enableDismissFromStartToEnd;
        } else if (($changed & 3072) == 0) {
            enableDismissFromStartToEnd2 = enableDismissFromStartToEnd;
            $dirty2 |= $composer2.changed(enableDismissFromStartToEnd2) ? 2048 : 1024;
        } else {
            enableDismissFromStartToEnd2 = enableDismissFromStartToEnd;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            z = enableDismissFromEndToStart;
        } else if (($changed & 24576) == 0) {
            z = enableDismissFromEndToStart;
            $dirty2 |= $composer2.changed(z) ? 16384 : 8192;
        } else {
            z = enableDismissFromEndToStart;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty2 |= $composer2.changed(gesturesEnabled) ? 131072 : 65536;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 1048576 : 524288;
        }
        if ((i & 128) == 0) {
            if ((12582912 & $changed) == 0) {
                i2 = $composer2.changedInstance(function4) ? 8388608 : 4194304;
            }
            $dirty = $dirty2;
            if ((4793491 & $dirty) != 4793490) {
                z2 = true;
            } else {
                z2 = false;
            }
            if ($composer2.shouldExecute(z2, $dirty & 1)) {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    enableDismissFromStartToEnd2 = true;
                }
                if (i5 != 0) {
                    enableDismissFromEndToStart3 = z;
                } else {
                    enableDismissFromEndToStart3 = true;
                }
                if (i6 != 0) {
                    gesturesEnabled3 = gesturesEnabled;
                } else {
                    gesturesEnabled3 = true;
                }
                if (i7 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer2, 387566236, "CC(remember):SwipeToDismissBox.kt#9igjgp");
                    it$iv2 = $composer2.rememberedValue();
                    if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv2 = new Function1() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Unit.INSTANCE;
                            }
                        };
                        $composer2.updateRememberedValue(value$iv2);
                        it$iv2 = value$iv2;
                    }
                    function5 = (Function1) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                } else {
                    function5 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-741495334, $dirty, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
                }
                AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material3 = state.getAnchoredDraggableState$material3();
                Orientation orientation = Orientation.Horizontal;
                if (gesturesEnabled3 || state.getSettledValue() != SwipeToDismissBoxValue.Settled) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (state.getUseFlingBehavior$material3()) {
                    $composer2.startReplaceGroup(387581105);
                    ComposerKt.sourceInformation($composer2, "322@13503L183");
                    targetedFlingBehaviorFlingBehavior = AnchoredDraggableDefaults.INSTANCE.flingBehavior(state.getAnchoredDraggableState$material3(), state.getPositionalThreshold$material3(), null, $composer2, AnchoredDraggableDefaults.$stable << 9, 4);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(-869685853);
                    $composer2.endReplaceGroup();
                    targetedFlingBehaviorFlingBehavior = null;
                }
                Modifier modifier$iv$iv = AnchoredDraggableKt.anchoredDraggable(modifier4, anchoredDraggableState$material3, orientation, (24 & 4) != 0 ? true : z3, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (24 & 32) != 0 ? null : targetedFlingBehaviorFlingBehavior);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, true);
                int $changed$iv$iv = (384 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv$iv);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = ($changed$iv$iv$iv >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                int i9 = ((384 >> 6) & 112) | 6;
                BoxScope $this$SwipeToDismissBox_u24lambda_u2411 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1704030332, "C330@13789L71,334@14026L583,331@13869L751:SwipeToDismissBox.kt#uh7d8r");
                Modifier modifier$iv$iv2 = $this$SwipeToDismissBox_u24lambda_u2411.matchParentSize(Modifier.INSTANCE);
                int $changed$iv = ($dirty << 6) & 7168;
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                MeasurePolicy measurePolicy$iv2 = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer2, (($changed$iv >> 3) & 14) | (($changed$iv >> 3) & 112));
                int $changed$iv$iv2 = ($changed$iv << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv$iv2);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function6 = constructor2;
                    $composer2.createNode(function6);
                } else {
                    function6 = constructor2;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
                function7 = function5;
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                    $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                    $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash2);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i10 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                function3.invoke(RowScopeInstance.INSTANCE, $composer2, Integer.valueOf((($changed$iv >> 6) & 112) | 6));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier.Companion companion = Modifier.INSTANCE;
                AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material4 = state.getAnchoredDraggableState$material3();
                Orientation orientation2 = Orientation.Horizontal;
                ComposerKt.sourceInformationMarkerStart($composer2, -609150713, "CC(remember):SwipeToDismissBox.kt#9igjgp");
                if (($dirty & 7168) == 2048) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if ((57344 & $dirty) == 16384) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                invalid$iv = z4 | z5 | $composer2.changedInstance(state);
                value$iv = $composer2.rememberedValue();
                if (!invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new Function2() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return TuplesKt.to(AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda7
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj3) {
                                    return SwipeToDismissBoxKt.SwipeToDismissBox$lambda$11$lambda$10$lambda$9$lambda$8(intSize, z, z, (DraggableAnchorsConfig) obj3);
                                }
                            }), state.getTargetValue());
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifier$iv$iv3 = DraggableAnchorsKt.draggableAnchorsV2(companion, anchoredDraggableState$material4, orientation2, (Function2) value$iv);
                int $changed$iv2 = ($dirty >> 12) & 7168;
                ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                Arrangement.Horizontal horizontalArrangement$iv2 = Arrangement.INSTANCE.getStart();
                Alignment.Vertical verticalAlignment$iv2 = Alignment.INSTANCE.getTop();
                MeasurePolicy measurePolicy$iv3 = RowKt.rowMeasurePolicy(horizontalArrangement$iv2, verticalAlignment$iv2, $composer2, (($changed$iv2 >> 3) & 14) | (($changed$iv2 >> 3) & 112));
                int $changed$iv$iv3 = ($changed$iv2 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
                boolean enableDismissFromEndToStart4 = enableDismissFromEndToStart3;
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv$iv3);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function8 = constructor3;
                    $composer2.createNode(function8);
                } else {
                    function8 = constructor3;
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                    $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                    $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash3);
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                function4.invoke(RowScopeInstance.INSTANCE, $composer2, Integer.valueOf((($changed$iv2 >> 6) & 112) | 6));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SwipeToDismissBoxValue settledValue = state.getSettledValue();
                ComposerKt.sourceInformationMarkerStart($composer2, 387618690, "CC(remember):SwipeToDismissBox.kt#9igjgp");
                boolean zChangedInstance = $composer2.changedInstance(state);
                if (($dirty & 3670016) == 1048576) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                invalid$iv2 = zChangedInstance | z6;
                it$iv = $composer2.rememberedValue();
                if (!invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                    function9 = function7;
                    Object value$iv3 = (Function2) new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(state, function9, null);
                    $composer2.updateRememberedValue(value$iv3);
                    it$iv = value$iv3;
                } else {
                    function9 = function7;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(settledValue, function9, (Function2) it$iv, $composer2, ($dirty >> 15) & 112);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                gesturesEnabled2 = gesturesEnabled3;
                enableDismissFromStartToEnd3 = enableDismissFromStartToEnd2;
                function2 = function9;
                modifier3 = modifier4;
                enableDismissFromEndToStart2 = enableDismissFromEndToStart4;
            } else {
                $composer2.skipToGroupEnd();
                gesturesEnabled2 = gesturesEnabled;
                modifier3 = modifier2;
                enableDismissFromStartToEnd3 = enableDismissFromStartToEnd2;
                enableDismissFromEndToStart2 = z;
                function2 = function1;
            }
            scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.SwipeToDismissBox$lambda$13(state, function3, modifier3, enableDismissFromStartToEnd3, enableDismissFromEndToStart2, gesturesEnabled2, function2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i2 = 12582912;
        $dirty2 |= i2;
        $dirty = $dirty2;
        if ((4793491 & $dirty) != 4793490) {
            z2 = true;
        } else {
            z2 = false;
        }
        if ($composer2.shouldExecute(z2, $dirty & 1)) {
            if (i3 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i4 != 0) {
                enableDismissFromStartToEnd2 = true;
            }
            if (i5 != 0) {
                enableDismissFromEndToStart3 = z;
            } else {
                enableDismissFromEndToStart3 = true;
            }
            if (i6 != 0) {
                gesturesEnabled3 = gesturesEnabled;
            } else {
                gesturesEnabled3 = true;
            }
            if (i7 != 0) {
                ComposerKt.sourceInformationMarkerStart($composer2, 387566236, "CC(remember):SwipeToDismissBox.kt#9igjgp");
                it$iv2 = $composer2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer2.updateRememberedValue(value$iv4);
                    it$iv2 = value$iv4;
                }
                function5 = (Function1) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
            } else {
                function5 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-741495334, $dirty, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
            }
            AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material5 = state.getAnchoredDraggableState$material3();
            Orientation orientation3 = Orientation.Horizontal;
            if (gesturesEnabled3) {
                z3 = false;
            } else {
                z3 = false;
            }
            if (state.getUseFlingBehavior$material3()) {
                $composer2.startReplaceGroup(387581105);
                ComposerKt.sourceInformation($composer2, "322@13503L183");
                targetedFlingBehaviorFlingBehavior = AnchoredDraggableDefaults.INSTANCE.flingBehavior(state.getAnchoredDraggableState$material3(), state.getPositionalThreshold$material3(), null, $composer2, AnchoredDraggableDefaults.$stable << 9, 4);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-869685853);
                $composer2.endReplaceGroup();
                targetedFlingBehaviorFlingBehavior = null;
            }
            Modifier modifier$iv$iv4 = AnchoredDraggableKt.anchoredDraggable(modifier4, anchoredDraggableState$material5, orientation3, (24 & 4) != 0 ? true : z3, (24 & 8) != 0 ? null : null, (24 & 16) != 0 ? null : null, (24 & 32) != 0 ? null : targetedFlingBehaviorFlingBehavior);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, true);
            int $changed$iv$iv4 = (384 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer2, modifier$iv$iv4);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
            int i12 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            int i13 = ((384 >> 6) & 112) | 6;
            BoxScope $this$SwipeToDismissBox_u24lambda_u2412 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -1704030332, "C330@13789L71,334@14026L583,331@13869L751:SwipeToDismissBox.kt#uh7d8r");
            Modifier modifier$iv$iv5 = $this$SwipeToDismissBox_u24lambda_u2412.matchParentSize(Modifier.INSTANCE);
            int $changed$iv3 = ($dirty << 6) & 7168;
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv3 = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv3 = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv5 = RowKt.rowMeasurePolicy(horizontalArrangement$iv3, verticalAlignment$iv3, $composer2, (($changed$iv3 >> 3) & 14) | (($changed$iv3 >> 3) & 112));
            int $changed$iv$iv5 = ($changed$iv3 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv5 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer2, modifier$iv$iv5);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function6 = constructor2;
                $composer2.createNode(function6);
            } else {
                function6 = constructor2;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer2);
            function7 = function5;
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash5);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = ($changed$iv$iv$iv5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            function3.invoke(RowScopeInstance.INSTANCE, $composer2, Integer.valueOf((($changed$iv3 >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material6 = state.getAnchoredDraggableState$material3();
            Orientation orientation4 = Orientation.Horizontal;
            ComposerKt.sourceInformationMarkerStart($composer2, -609150713, "CC(remember):SwipeToDismissBox.kt#9igjgp");
            if (($dirty & 7168) == 2048) {
                z4 = true;
            } else {
                z4 = false;
            }
            if ((57344 & $dirty) == 16384) {
                z5 = true;
            } else {
                z5 = false;
            }
            invalid$iv = z4 | z5 | $composer2.changedInstance(state);
            value$iv = $composer2.rememberedValue();
            if (!invalid$iv) {
            }
            value$iv = new Function2() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TuplesKt.to(AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return SwipeToDismissBoxKt.SwipeToDismissBox$lambda$11$lambda$10$lambda$9$lambda$8(intSize, z, z, (DraggableAnchorsConfig) obj3);
                        }
                    }), state.getTargetValue());
                }
            };
            $composer2.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv$iv6 = DraggableAnchorsKt.draggableAnchorsV2(companion2, anchoredDraggableState$material6, orientation4, (Function2) value$iv);
            int $changed$iv4 = ($dirty >> 12) & 7168;
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            Arrangement.Horizontal horizontalArrangement$iv4 = Arrangement.INSTANCE.getStart();
            Alignment.Vertical verticalAlignment$iv4 = Alignment.INSTANCE.getTop();
            MeasurePolicy measurePolicy$iv6 = RowKt.rowMeasurePolicy(horizontalArrangement$iv4, verticalAlignment$iv4, $composer2, (($changed$iv4 >> 3) & 14) | (($changed$iv4 >> 3) & 112));
            int $changed$iv$iv6 = ($changed$iv4 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv6 = $composer2.getCurrentCompositionLocalMap();
            boolean enableDismissFromEndToStart5 = enableDismissFromEndToStart3;
            Modifier materialized$iv$iv6 = ComposedModifierKt.materializeModifier($composer2, modifier$iv$iv6);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv6 = (($changed$iv$iv6 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function8 = constructor3;
                $composer2.createNode(function8);
            } else {
                function8 = constructor3;
                $composer2.useNode();
            }
            $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
            }
            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash6);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv6, ComposeUiNode.INSTANCE.getSetModifier());
            int i15 = ($changed$iv$iv$iv6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            function4.invoke(RowScopeInstance.INSTANCE, $composer2, Integer.valueOf((($changed$iv4 >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SwipeToDismissBoxValue settledValue2 = state.getSettledValue();
            ComposerKt.sourceInformationMarkerStart($composer2, 387618690, "CC(remember):SwipeToDismissBox.kt#9igjgp");
            boolean zChangedInstance2 = $composer2.changedInstance(state);
            if (($dirty & 3670016) == 1048576) {
                z6 = true;
            } else {
                z6 = false;
            }
            invalid$iv2 = zChangedInstance2 | z6;
            it$iv = $composer2.rememberedValue();
            if (!invalid$iv2) {
                function9 = function7;
                Object value$iv5 = (Function2) new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(state, function9, null);
                $composer2.updateRememberedValue(value$iv5);
                it$iv = value$iv5;
            } else {
                function9 = function7;
                Object value$iv6 = (Function2) new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(state, function9, null);
                $composer2.updateRememberedValue(value$iv6);
                it$iv = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(settledValue2, function9, (Function2) it$iv, $composer2, ($dirty >> 15) & 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            gesturesEnabled2 = gesturesEnabled3;
            enableDismissFromStartToEnd3 = enableDismissFromStartToEnd2;
            function2 = function9;
            modifier3 = modifier4;
            enableDismissFromEndToStart2 = enableDismissFromEndToStart5;
        } else {
            $composer2.skipToGroupEnd();
            gesturesEnabled2 = gesturesEnabled;
            modifier3 = modifier2;
            enableDismissFromStartToEnd3 = enableDismissFromStartToEnd2;
            enableDismissFromEndToStart2 = z;
            function2 = function1;
        }
        scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SwipeToDismissBoxKt.SwipeToDismissBox$lambda$13(state, function3, modifier3, enableDismissFromStartToEnd3, enableDismissFromEndToStart2, gesturesEnabled2, function2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit SwipeToDismissBox$lambda$11$lambda$10$lambda$9$lambda$8(IntSize $size, boolean $enableDismissFromStartToEnd, boolean $enableDismissFromEndToStart, DraggableAnchorsConfig $this$DraggableAnchors) {
        long arg0$iv = $size.m8080unboximpl();
        float width = (int) (arg0$iv >> 32);
        $this$DraggableAnchors.at(SwipeToDismissBoxValue.Settled, 0.0f);
        if ($enableDismissFromStartToEnd) {
            $this$DraggableAnchors.at(SwipeToDismissBoxValue.StartToEnd, width);
        }
        if ($enableDismissFromEndToStart) {
            $this$DraggableAnchors.at(SwipeToDismissBoxValue.EndToStart, -width);
        }
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use updated signature with onDismissed parameter.")
    public static final /* synthetic */ void SwipeToDismissBox(final SwipeToDismissBoxState state, final Function3 backgroundContent, Modifier modifier, boolean enableDismissFromStartToEnd, boolean enableDismissFromEndToStart, boolean gesturesEnabled, final Function3 content, Composer $composer, final int $changed, final int i) {
        SwipeToDismissBoxState swipeToDismissBoxState;
        Function3 function3;
        Modifier modifier2;
        boolean z;
        Composer $composer2;
        final boolean enableDismissFromEndToStart2;
        final Modifier modifier3;
        final boolean enableDismissFromStartToEnd2;
        final boolean gesturesEnabled2;
        Modifier modifier4;
        int i2;
        boolean enableDismissFromStartToEnd3;
        boolean enableDismissFromEndToStart3;
        boolean gesturesEnabled3;
        Composer $composer3 = $composer.startRestartGroup(-1807005299);
        ComposerKt.sourceInformation($composer3, "C(SwipeToDismissBox)N(state,backgroundContent,modifier,enableDismissFromStartToEnd,enableDismissFromEndToStart,gesturesEnabled,content)388@16066L2,381@15751L351:SwipeToDismissBox.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            swipeToDismissBoxState = state;
        } else if (($changed & 6) == 0) {
            swipeToDismissBoxState = state;
            $dirty |= $composer3.changedInstance(swipeToDismissBoxState) ? 4 : 2;
        } else {
            swipeToDismissBoxState = state;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            function3 = backgroundContent;
        } else if (($changed & 48) == 0) {
            function3 = backgroundContent;
            $dirty |= $composer3.changedInstance(function3) ? 32 : 16;
        } else {
            function3 = backgroundContent;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            z = enableDismissFromStartToEnd;
        } else if (($changed & 3072) == 0) {
            z = enableDismissFromStartToEnd;
            $dirty |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enableDismissFromStartToEnd;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(enableDismissFromEndToStart) ? 16384 : 8192;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changed(gesturesEnabled) ? 131072 : 65536;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(content) ? 1048576 : 524288;
        }
        if (!$composer3.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            enableDismissFromEndToStart2 = enableDismissFromEndToStart;
            modifier3 = modifier2;
            enableDismissFromStartToEnd2 = z;
            gesturesEnabled2 = gesturesEnabled;
        } else {
            if (i3 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i4 == 0) {
                i2 = i6;
                enableDismissFromStartToEnd3 = z;
            } else {
                enableDismissFromStartToEnd3 = true;
                i2 = i6;
            }
            if (i5 == 0) {
                enableDismissFromEndToStart3 = enableDismissFromEndToStart;
            } else {
                enableDismissFromEndToStart3 = true;
            }
            if (i2 == 0) {
                gesturesEnabled3 = gesturesEnabled;
            } else {
                gesturesEnabled3 = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1807005299, $dirty, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -1365277681, "CC(remember):SwipeToDismissBox.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            SwipeToDismissBox(swipeToDismissBoxState, function3, modifier4, enableDismissFromStartToEnd3, enableDismissFromEndToStart3, gesturesEnabled3, (Function1) it$iv, content, $composer2, ($dirty & 14) | 1572864 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enableDismissFromStartToEnd2 = enableDismissFromStartToEnd3;
            enableDismissFromEndToStart2 = enableDismissFromEndToStart3;
            gesturesEnabled2 = gesturesEnabled3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SwipeToDismissBoxKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SwipeToDismissBoxKt.SwipeToDismissBox$lambda$16(state, backgroundContent, modifier3, enableDismissFromStartToEnd2, enableDismissFromEndToStart2, gesturesEnabled2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
