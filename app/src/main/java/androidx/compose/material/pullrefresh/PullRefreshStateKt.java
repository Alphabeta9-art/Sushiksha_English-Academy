package androidx.compose.material.pullrefresh;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PullRefreshState.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001a9\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\n\u0010\u000b\"\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"rememberPullRefreshState", "Landroidx/compose/material/pullrefresh/PullRefreshState;", "refreshing", "", "onRefresh", "Lkotlin/Function0;", "", "refreshThreshold", "Landroidx/compose/ui/unit/Dp;", "refreshingOffset", "rememberPullRefreshState-UuyPYSY", "(ZLkotlin/jvm/functions/Function0;FFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/pullrefresh/PullRefreshState;", "DragMultiplier", "", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PullRefreshStateKt {
    private static final float DragMultiplier = 0.5f;

    /* JADX INFO: renamed from: rememberPullRefreshState-UuyPYSY, reason: not valid java name */
    public static final PullRefreshState m2037rememberPullRefreshStateUuyPYSY(final boolean refreshing, Function0<Unit> function0, float f, float f2, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -174977512, "C(rememberPullRefreshState)N(refreshing,onRefresh,refreshThreshold:c#ui.unit.Dp,refreshingOffset:c#ui.unit.Dp)64@2637L24,65@2687L31,69@2803L7,75@2944L92,77@3053L141,77@3042L152:PullRefreshState.kt#t44y28");
        float fM2029getRefreshThresholdD9Ej5fM = (i & 4) != 0 ? PullRefreshDefaults.INSTANCE.m2029getRefreshThresholdD9Ej5fM() : f;
        float fM2030getRefreshingOffsetD9Ej5fM = (i & 8) != 0 ? PullRefreshDefaults.INSTANCE.m2030getRefreshingOffsetD9Ej5fM() : f2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-174977512, $changed, -1, "androidx.compose.material.pullrefresh.rememberPullRefreshState (PullRefreshState.kt:61)");
        }
        int $this$dp$iv = Dp.m7904compareTo0680j_4(fM2029getRefreshThresholdD9Ej5fM, Dp.m7905constructorimpl(0));
        if (!($this$dp$iv > 0)) {
            throw new IllegalArgumentException("The refresh trigger must be greater than zero!".toString());
        }
        ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
        ComposerKt.sourceInformationMarkerStart($composer, 683736516, "CC(remember):Effects.kt#9igjgp");
        Object it$iv$iv = $composer.rememberedValue();
        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer);
            $composer.updateRememberedValue(value$iv$iv);
            it$iv$iv = value$iv$iv;
        }
        CoroutineScope scope = (CoroutineScope) it$iv$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        State onRefreshState = SnapshotStateKt.rememberUpdatedState(function0, $composer, ($changed >> 3) & 14);
        final Ref.FloatRef thresholdPx = new Ref.FloatRef();
        final Ref.FloatRef refreshingOffsetPx = new Ref.FloatRef();
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Density $this$rememberPullRefreshState_UuyPYSY_u24lambda_u241 = (Density) objConsume;
        thresholdPx.element = $this$rememberPullRefreshState_UuyPYSY_u24lambda_u241.mo406toPx0680j_4(fM2029getRefreshThresholdD9Ej5fM);
        refreshingOffsetPx.element = $this$rememberPullRefreshState_UuyPYSY_u24lambda_u241.mo406toPx0680j_4(fM2030getRefreshingOffsetD9Ej5fM);
        ComposerKt.sourceInformationMarkerStart($composer, -649643660, "CC(remember):PullRefreshState.kt#9igjgp");
        boolean invalid$iv = $composer.changed(scope);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new PullRefreshState(scope, onRefreshState, refreshingOffsetPx.element, thresholdPx.element);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        final PullRefreshState state = (PullRefreshState) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -649640123, "CC(remember):PullRefreshState.kt#9igjgp");
        boolean invalid$iv2 = $composer.changedInstance(state) | (((($changed & 14) ^ 6) > 4 && $composer.changed(refreshing)) || ($changed & 6) == 4) | $composer.changed(thresholdPx.element) | $composer.changed(refreshingOffsetPx.element);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.material.pullrefresh.PullRefreshStateKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PullRefreshStateKt.rememberPullRefreshState_UuyPYSY$lambda$3$0(state, refreshing, thresholdPx, refreshingOffsetPx);
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.SideEffect((Function0) it$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit rememberPullRefreshState_UuyPYSY$lambda$3$0(PullRefreshState $state, boolean $refreshing, Ref.FloatRef $thresholdPx, Ref.FloatRef $refreshingOffsetPx) {
        $state.setRefreshing$material($refreshing);
        $state.setThreshold$material($thresholdPx.element);
        $state.setRefreshingOffset$material($refreshingOffsetPx.element);
        return Unit.INSTANCE;
    }
}
