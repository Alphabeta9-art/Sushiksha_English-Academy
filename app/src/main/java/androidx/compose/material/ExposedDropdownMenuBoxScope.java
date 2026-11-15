package androidx.compose.material;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntRect;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: ExposedDropdownMenu.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u0005*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&JU\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u001c\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\t0\u0011¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/compose/material/ExposedDropdownMenuBoxScope;", "", "<init>", "()V", "exposedDropdownSize", "Landroidx/compose/ui/Modifier;", "matchTextFieldWidth", "", "ExposedDropdownMenu", "", "expanded", "onDismissRequest", "Lkotlin/Function0;", "modifier", "scrollState", "Landroidx/compose/foundation/ScrollState;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class ExposedDropdownMenuBoxScope {
    public static final int $stable = 0;

    static final Unit ExposedDropdownMenu$lambda$4(ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, boolean z, Function0 function0, Modifier modifier, ScrollState scrollState, Function3 function3, int i, int i2, Composer composer, int i3) {
        exposedDropdownMenuBoxScope.ExposedDropdownMenu(z, function0, modifier, scrollState, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public abstract Modifier exposedDropdownSize(Modifier modifier, boolean z);

    public static /* synthetic */ Modifier exposedDropdownSize$default(ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, Modifier modifier, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: exposedDropdownSize");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        return exposedDropdownMenuBoxScope.exposedDropdownSize(modifier, z);
    }

    public final void ExposedDropdownMenu(boolean expanded, final Function0<Unit> function0, Modifier modifier, ScrollState scrollState, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        ScrollState scrollState2;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4;
        final ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope;
        Composer $composer2;
        final Modifier modifier3;
        final ScrollState scrollState3;
        Modifier.Companion modifier4;
        int $dirty;
        final ScrollState scrollState4;
        final Modifier modifier5;
        Composer $composer3 = $composer.startRestartGroup(-1576205770);
        ComposerKt.sourceInformation($composer3, "C(ExposedDropdownMenu)N(expanded,onDismissRequest,modifier,scrollState,content)196@8241L42:ExposedDropdownMenu.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            z = expanded;
            $dirty2 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 32 : 16;
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
                scrollState2 = scrollState;
                int i3 = $composer3.changed(scrollState2) ? 2048 : 1024;
                $dirty2 |= i3;
            } else {
                scrollState2 = scrollState;
            }
            $dirty2 |= i3;
        } else {
            scrollState2 = scrollState;
        }
        if (($changed & 24576) == 0) {
            function4 = function3;
            $dirty2 |= $composer3.changedInstance(function4) ? 16384 : 8192;
        } else {
            function4 = function3;
        }
        if ((196608 & $changed) == 0) {
            exposedDropdownMenuBoxScope = this;
            $dirty2 |= $composer3.changed(exposedDropdownMenuBoxScope) ? 131072 : 65536;
        } else {
            exposedDropdownMenuBoxScope = this;
        }
        if (!$composer3.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            scrollState3 = scrollState2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "184@7740L21");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                ScrollState scrollState5 = scrollState2;
                $dirty = $dirty2;
                scrollState4 = scrollState5;
                modifier5 = modifier2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 8) == 0) {
                    ScrollState scrollState6 = scrollState2;
                    $dirty = $dirty2;
                    scrollState4 = scrollState6;
                    modifier5 = modifier4;
                } else {
                    $dirty = $dirty2 & (-7169);
                    modifier5 = modifier4;
                    scrollState4 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1576205770, $dirty, -1, "androidx.compose.material.ExposedDropdownMenuBoxScope.ExposedDropdownMenu (ExposedDropdownMenu.kt:186)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1389731840, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new MutableTransitionState(false);
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final MutableTransitionState expandedStates = (MutableTransitionState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            expandedStates.setTargetState$animation_core(Boolean.valueOf(z));
            if (((Boolean) expandedStates.getCurrentState()).booleanValue() || ((Boolean) expandedStates.getTargetState()).booleanValue()) {
                $composer3.startReplaceGroup(132200795);
                ComposerKt.sourceInformation($composer3, "200@8443L51,201@8534L7,203@8651L147,210@8968L337,207@8812L493");
                ComposerKt.sourceInformationMarkerStart($composer3, 1389738313, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                Object it$iv2 = $composer3.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TransformOrigin.m5497boximpl(TransformOrigin.INSTANCE.m5510getCenterSzJe1aQ()), null, 2, null);
                    $composer3.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                final MutableState transformOriginState = (MutableState) it$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Density density = (Density) objConsume;
                long jM7976getZeroRKDOV3M = DpOffset.INSTANCE.m7976getZeroRKDOV3M();
                ComposerKt.sourceInformationMarkerStart($composer3, 1389745065, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                Object it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function2() { // from class: androidx.compose.material.ExposedDropdownMenuBoxScope$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return ExposedDropdownMenuBoxScope.ExposedDropdownMenu$lambda$2$0(transformOriginState, (IntRect) obj, (IntRect) obj2);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                DropdownMenuPositionProvider popupPositionProvider = new DropdownMenuPositionProvider(jM7976getZeroRKDOV3M, density, (Function2) it$iv3, null);
                DropdownMenuPositionProvider dropdownMenuPositionProvider = popupPositionProvider;
                final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function5 = function4;
                ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup(function0, dropdownMenuPositionProvider, ComposableLambdaKt.rememberComposableLambda(1604457564, true, new Function2() { // from class: androidx.compose.material.ExposedDropdownMenuBoxScope$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ExposedDropdownMenuBoxScope.ExposedDropdownMenu$lambda$3(expandedStates, transformOriginState, scrollState4, exposedDropdownMenuBoxScope, modifier5, function5, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), $composer3, (($dirty >> 3) & 14) | 384, 0);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(123836716);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            scrollState3 = scrollState4;
            modifier3 = modifier5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z2 = z;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ExposedDropdownMenuBoxScope$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ExposedDropdownMenuBoxScope.ExposedDropdownMenu$lambda$4(this.f$0, z2, function0, modifier3, scrollState3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenu$lambda$2$0(MutableState $transformOriginState, IntRect parentBounds, IntRect menuBounds) {
        $transformOriginState.setValue(TransformOrigin.m5497boximpl(MenuKt.calculateTransformOrigin(parentBounds, menuBounds)));
        return Unit.INSTANCE;
    }

    static final Unit ExposedDropdownMenu$lambda$3(MutableTransitionState $expandedStates, MutableState $transformOriginState, ScrollState $scrollState, ExposedDropdownMenuBoxScope this$0, Modifier $modifier, Function3 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C211@8986L305:ExposedDropdownMenu.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1604457564, $changed, -1, "androidx.compose.material.ExposedDropdownMenuBoxScope.ExposedDropdownMenu.<anonymous> (ExposedDropdownMenu.kt:211)");
            }
            MenuKt.DropdownMenuContent($expandedStates, $transformOriginState, $scrollState, exposedDropdownSize$default(this$0, $modifier, false, 1, null), $content, $composer, MutableTransitionState.$stable | 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
