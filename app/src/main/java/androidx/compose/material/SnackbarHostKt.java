package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AccessibilityManager;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.util.ListUtilsKt;
import com.facebook.common.util.UriUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SnackbarHost.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\u001a:\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0019\b\u0002\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\n\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0000\u001a:\u0010\u0012\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\tH\u0003¢\u0006\u0002\u0010\u0015\u001a9\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001e2\u0006\u0010\u001f\u001a\u00020\u000f2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010\u0017H\u0003¢\u0006\u0002\u0010!\u001a)\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001e2\u0006\u0010\u001f\u001a\u00020\u000fH\u0003¢\u0006\u0002\u0010#\"\u000e\u0010$\u001a\u00020%X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010&\u001a\u00020%X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010'\u001a\u00020%X\u0082T¢\u0006\u0002\n\u0000*b\b\u0002\u0010\u0016\"-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\t¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\t2-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\t¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\t¨\u0006("}, d2 = {"SnackbarHost", "", "hostState", "Landroidx/compose/material/SnackbarHostState;", "modifier", "Landroidx/compose/ui/Modifier;", "snackbar", "Lkotlin/Function1;", "Landroidx/compose/material/SnackbarData;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material/SnackbarHostState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "toMillis", "", "Landroidx/compose/material/SnackbarDuration;", "hasAction", "", "accessibilityManager", "Landroidx/compose/ui/platform/AccessibilityManager;", "FadeInFadeOutWithScale", "current", UriUtil.LOCAL_CONTENT_SCHEME, "(Landroidx/compose/material/SnackbarData;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "FadeInFadeOutTransition", "Lkotlin/Function0;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "animatedOpacity", "Landroidx/compose/runtime/State;", "", "animation", "Landroidx/compose/animation/core/AnimationSpec;", "visible", "onAnimationFinish", "(Landroidx/compose/animation/core/AnimationSpec;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animatedScale", "(Landroidx/compose/animation/core/AnimationSpec;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "SnackbarFadeInMillis", "", "SnackbarFadeOutMillis", "SnackbarInBetweenDelayMillis", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SnackbarHostKt {
    private static final int SnackbarFadeInMillis = 150;
    private static final int SnackbarFadeOutMillis = 75;
    private static final int SnackbarInBetweenDelayMillis = 0;

    /* JADX INFO: compiled from: SnackbarHost.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SnackbarDuration.values().length];
            try {
                iArr[SnackbarDuration.Indefinite.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SnackbarDuration.Long.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SnackbarDuration.Short.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit FadeInFadeOutWithScale$lambda$4(SnackbarData snackbarData, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        FadeInFadeOutWithScale(snackbarData, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SnackbarHost$lambda$1(SnackbarHostState snackbarHostState, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        SnackbarHost(snackbarHostState, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void SnackbarHost(SnackbarHostState hostState, Modifier modifier, Function3<? super SnackbarData, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        SnackbarHostState snackbarHostState;
        Modifier modifier2;
        Function3<? super SnackbarData, ? super Composer, ? super Integer, Unit> lambda$1890101041$material;
        final Function3<? super SnackbarData, ? super Composer, ? super Integer, Unit> function4;
        int i2;
        Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(1351125615);
        ComposerKt.sourceInformation($composer2, "C(SnackbarHost)N(hostState,modifier,snackbar)157@6684L7,158@6732L333,158@6696L369,169@7070L135:SnackbarHost.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            snackbarHostState = hostState;
            $dirty |= $composer2.changed(snackbarHostState) ? 4 : 2;
        } else {
            snackbarHostState = hostState;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            lambda$1890101041$material = function3;
        } else if (($changed & 384) == 0) {
            lambda$1890101041$material = function3;
            $dirty |= $composer2.changedInstance(lambda$1890101041$material) ? 256 : 128;
        } else {
            lambda$1890101041$material = function3;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            function4 = lambda$1890101041$material;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
                i2 = i4;
            } else {
                i2 = i4;
                modifier3 = modifier2;
            }
            if (i2 != 0) {
                lambda$1890101041$material = ComposableSingletons$SnackbarHostKt.INSTANCE.getLambda$1890101041$material();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1351125615, $dirty2, -1, "androidx.compose.material.SnackbarHost (SnackbarHost.kt:155)");
            }
            SnackbarData currentSnackbarData = snackbarHostState.getCurrentSnackbarData();
            ProvidableCompositionLocal<AccessibilityManager> localAccessibilityManager = CompositionLocalsKt.getLocalAccessibilityManager();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localAccessibilityManager);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AccessibilityManager accessibilityManager = (AccessibilityManager) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer2, 1351603356, "CC(remember):SnackbarHost.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(currentSnackbarData) | $composer2.changedInstance(accessibilityManager);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function2) new SnackbarHostKt$SnackbarHost$1$1(currentSnackbarData, accessibilityManager, null);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(currentSnackbarData, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv, $composer2, 0);
            FadeInFadeOutWithScale(snackbarHostState.getCurrentSnackbarData(), modifier3, lambda$1890101041$material, $composer2, ($dirty2 & 896) | ($dirty2 & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            function4 = lambda$1890101041$material;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final SnackbarHostState snackbarHostState2 = snackbarHostState;
            final Modifier modifier4 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SnackbarHostKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SnackbarHostKt.SnackbarHost$lambda$1(snackbarHostState2, modifier4, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final long toMillis(SnackbarDuration $this$toMillis, boolean hasAction, AccessibilityManager accessibilityManager) {
        long j;
        switch (WhenMappings.$EnumSwitchMapping$0[$this$toMillis.ordinal()]) {
            case 1:
                j = Long.MAX_VALUE;
                break;
            case 2:
                j = 10000;
                break;
            case 3:
                j = 4000;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        long original = j;
        if (accessibilityManager == null) {
            return original;
        }
        return accessibilityManager.calculateRecommendedTimeoutMillis(original, true, true, hasAction);
    }

    private static final void FadeInFadeOutWithScale(final SnackbarData current, Modifier modifier, final Function3<? super SnackbarData, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Modifier modifier3;
        Modifier modifier4;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(1354335728);
        ComposerKt.sourceInformation($composer2, "C(FadeInFadeOutWithScale)N(current,modifier,content)246@9399L48,247@9472L36,315@12255L162:SnackbarHost.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(current) : $composer2.changedInstance(current) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1354335728, $dirty2, -1, "androidx.compose.material.FadeInFadeOutWithScale (SnackbarHost.kt:245)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 141549088, "CC(remember):SnackbarHost.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new FadeInFadeOutState();
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final FadeInFadeOutState state = (FadeInFadeOutState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final String a11yPaneTitle = Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1952getSnackbarPaneTitleUdPEhr4(), $composer2, 6);
            if (Intrinsics.areEqual(current, state.getCurrent())) {
                $composer2.startReplaceGroup(83728146);
            } else {
                $composer2.startReplaceGroup(93279711);
                ComposerKt.sourceInformation($composer2, "*256@9859L2375");
                state.setCurrent(current);
                List $this$fastMap$iv = state.getItems();
                List target$iv = new ArrayList($this$fastMap$iv.size());
                int size = $this$fastMap$iv.size();
                for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                    Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
                    FadeInFadeOutAnimationItem it = (FadeInFadeOutAnimationItem) item$iv$iv;
                    target$iv.add((SnackbarData) it.getKey());
                }
                final List keys = CollectionsKt.toMutableList((Collection) target$iv);
                if (!keys.contains(current)) {
                    keys.add(current);
                }
                state.getItems().clear();
                List $this$fastMapTo$iv = ListUtilsKt.fastFilterNotNull(keys);
                Collection destination$iv = state.getItems();
                int index$iv$iv2 = 0;
                int size2 = $this$fastMapTo$iv.size();
                while (index$iv$iv2 < size2) {
                    Object item$iv$iv2 = $this$fastMapTo$iv.get(index$iv$iv2);
                    final SnackbarData key = (SnackbarData) item$iv$iv2;
                    int index$iv$iv3 = size2;
                    destination$iv.add(new FadeInFadeOutAnimationItem(key, ComposableLambdaKt.rememberComposableLambda(-1032415134, true, new Function3() { // from class: androidx.compose.material.SnackbarHostKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return SnackbarHostKt.FadeInFadeOutWithScale$lambda$2$0(key, current, keys, state, a11yPaneTitle, (Function2) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer2, 54)));
                    index$iv$iv2++;
                    size2 = index$iv$iv3;
                    keys = keys;
                    a11yPaneTitle = a11yPaneTitle;
                }
            }
            $composer2.endReplaceGroup();
            int $changed$iv = ($dirty2 >> 3) & 14;
            Modifier modifier$iv = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = ($changed$iv << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $i$f$Box = $changed$iv$iv << 6;
            int $changed$iv$iv$iv = ($i$f$Box & 896) | 6;
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
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i3 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int $changed2 = (($changed$iv >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1344811861, "C316@12293L21:SnackbarHost.kt#jmzs0o");
            state.setScope(ComposablesKt.getCurrentRecomposeScope($composer2, 0));
            $composer2.startReplaceGroup(-1757732554);
            ComposerKt.sourceInformation($composer2, "");
            List $this$fastForEach$iv = state.getItems();
            int $i$f$fastForEach = $this$fastForEach$iv.size();
            int $changed$iv2 = 0;
            while ($changed$iv2 < $i$f$fastForEach) {
                Object item$iv = $this$fastForEach$iv.get($changed$iv2);
                FadeInFadeOutAnimationItem fadeInFadeOutAnimationItem = (FadeInFadeOutAnimationItem) item$iv;
                List $this$fastForEach$iv2 = $this$fastForEach$iv;
                final SnackbarData item = (SnackbarData) fadeInFadeOutAnimationItem.component1();
                int i4 = $i$f$fastForEach;
                Function3<Function2<? super Composer, ? super Integer, Unit>, Composer, Integer, Unit> function3Component2 = fadeInFadeOutAnimationItem.component2();
                int index$iv = $changed$iv2;
                $composer2.startMovableGroup(-1515535286, item);
                ComposerKt.sourceInformation($composer2, "317@12388L19,317@12380L27");
                function3Component2.invoke(ComposableLambdaKt.rememberComposableLambda(2017516783, true, new Function2() { // from class: androidx.compose.material.SnackbarHostKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SnackbarHostKt.FadeInFadeOutWithScale$lambda$3$0$0(function3, item, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer2, 54), $composer2, 6);
                $composer2.endMovableGroup();
                $changed$iv2 = index$iv + 1;
                $changed2 = $changed2;
                $i$f$fastForEach = i4;
                $this$fastForEach$iv = $this$fastForEach$iv2;
                state = state;
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SnackbarHostKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SnackbarHostKt.FadeInFadeOutWithScale$lambda$4(current, modifier3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FadeInFadeOutWithScale$lambda$2$0(final SnackbarData $key, SnackbarData $current, List $keys, final FadeInFadeOutState $state, final String $a11yPaneTitle, Function2 children, Composer $composer, int $changed) {
        int i;
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "CN(children)275@10748L313,267@10364L720,284@11133L366,299@11777L374,293@11516L704:SnackbarHost.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changedInstance(children) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1032415134, $dirty2, -1, "androidx.compose.material.FadeInFadeOutWithScale.<anonymous>.<anonymous> (SnackbarHost.kt:257)");
            }
            final boolean isVisible = Intrinsics.areEqual($key, $current);
            int duration = isVisible ? 150 : 75;
            if (isVisible && ListUtilsKt.fastFilterNotNull($keys).size() != 1) {
                i = 75;
            } else {
                i = 0;
            }
            int animationDelay = i;
            TweenSpec tweenSpecTween = AnimationSpecKt.tween(duration, animationDelay, EasingKt.getLinearEasing());
            ComposerKt.sourceInformationMarkerStart($composer, -13469637, "CC(remember):SnackbarHost.kt#9igjgp");
            boolean invalid$iv = $composer.changedInstance($key) | $composer.changedInstance($state);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material.SnackbarHostKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SnackbarHostKt.FadeInFadeOutWithScale$lambda$2$0$0$0($key, $state);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            State<Float> stateAnimatedOpacity = animatedOpacity(tweenSpecTween, isVisible, (Function0) it$iv, $composer, 0, 0);
            State<Float> stateAnimatedScale = animatedScale(AnimationSpecKt.tween(duration, animationDelay, EasingKt.getFastOutSlowInEasing()), isVisible, $composer, 0);
            Modifier modifierM5259graphicsLayerAp8cVGQ = GraphicsLayerModifierKt.m5259graphicsLayerAp8cVGQ(Modifier.INSTANCE, (131064 & 1) != 0 ? 1.0f : stateAnimatedScale.getValue().floatValue(), (131064 & 2) != 0 ? 1.0f : stateAnimatedScale.getValue().floatValue(), (131064 & 4) == 0 ? stateAnimatedOpacity.getValue().floatValue() : 1.0f, (131064 & 8) != 0 ? 0.0f : 0.0f, (131064 & 16) != 0 ? 0.0f : 0.0f, (131064 & 32) != 0 ? 0.0f : 0.0f, (131064 & 64) != 0 ? 0.0f : 0.0f, (131064 & 128) != 0 ? 0.0f : 0.0f, (131064 & 256) == 0 ? 0.0f : 0.0f, (131064 & 512) != 0 ? 8.0f : 0.0f, (131064 & 1024) != 0 ? TransformOrigin.INSTANCE.m5510getCenterSzJe1aQ() : 0L, (131064 & 2048) != 0 ? RectangleShapeKt.getRectangleShape() : null, (131064 & 4096) != 0 ? false : false, (131064 & 8192) != 0 ? null : null, (131064 & 16384) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (32768 & 131064) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : 0L, (131064 & 65536) != 0 ? CompositingStrategy.INSTANCE.m5186getAutoNrFUSI() : 0);
            ComposerKt.sourceInformationMarkerStart($composer, -13436648, "CC(remember):SnackbarHost.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed(isVisible) | $composer.changed($a11yPaneTitle) | $composer.changedInstance($key);
            Object value$iv2 = $composer.rememberedValue();
            if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new Function1() { // from class: androidx.compose.material.SnackbarHostKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SnackbarHostKt.FadeInFadeOutWithScale$lambda$2$0$1$0(isVisible, $a11yPaneTitle, $key, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv2);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv = SemanticsModifierKt.semantics$default(modifierM5259graphicsLayerAp8cVGQ, false, (Function1) value$iv2, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
            CompositionLocalMap localMap$iv$iv = $composer.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1502911965, "C310@12192L10:SnackbarHost.kt#jmzs0o");
            children.invoke($composer, Integer.valueOf($dirty2 & 14));
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FadeInFadeOutWithScale$lambda$2$0$0$0(final SnackbarData $key, FadeInFadeOutState $state) {
        if (!Intrinsics.areEqual($key, $state.getCurrent())) {
            CollectionsKt.removeAll($state.getItems(), new Function1() { // from class: androidx.compose.material.SnackbarHostKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(SnackbarHostKt.FadeInFadeOutWithScale$lambda$2$0$0$0$0($key, (FadeInFadeOutAnimationItem) obj));
                }
            });
            RecomposeScope scope = $state.getScope();
            if (scope != null) {
                scope.invalidate();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean FadeInFadeOutWithScale$lambda$2$0$0$0$0(SnackbarData $key, FadeInFadeOutAnimationItem it) {
        return Intrinsics.areEqual(it.getKey(), $key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FadeInFadeOutWithScale$lambda$2$0$1$0(boolean $isVisible, String $a11yPaneTitle, final SnackbarData $key, SemanticsPropertyReceiver $this$semantics) {
        if ($isVisible) {
            SemanticsPropertiesKt.m7117setLiveRegionhR3wRGc($this$semantics, LiveRegionMode.INSTANCE.m7091getPolite0phEisY());
        }
        SemanticsPropertiesKt.setPaneTitle($this$semantics, $a11yPaneTitle);
        SemanticsPropertiesKt.dismiss$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.SnackbarHostKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SnackbarHostKt.FadeInFadeOutWithScale$lambda$2$0$1$0$0($key));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean FadeInFadeOutWithScale$lambda$2$0$1$0$0(SnackbarData $key) {
        $key.dismiss();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit FadeInFadeOutWithScale$lambda$3$0$0(Function3 $content, SnackbarData $item, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C317@12390L15:SnackbarHost.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2017516783, $changed, -1, "androidx.compose.material.FadeInFadeOutWithScale.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SnackbarHost.kt:317)");
            }
            Intrinsics.checkNotNull($item);
            $content.invoke($item, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    private static final State<Float> animatedOpacity(AnimationSpec<Float> animationSpec, boolean visible, Function0<Unit> function0, Composer $composer, int $changed, int i) {
        Function0<Unit> function1;
        ComposerKt.sourceInformationMarkerStart($composer, 1016418159, "C(animatedOpacity)N(animation,visible,onAnimationFinish)339@13029L2,341@13067L49,342@13145L111,342@13121L135:SnackbarHost.kt#jmzs0o");
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 1820278321, "CC(remember):SnackbarHost.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function0() { // from class: androidx.compose.material.SnackbarHostKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
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
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1016418159, $changed, -1, "androidx.compose.material.animatedOpacity (SnackbarHost.kt:340)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1820279584, "CC(remember):SnackbarHost.kt#9igjgp");
        Object it$iv2 = $composer.rememberedValue();
        if (it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = AnimatableKt.Animatable$default(!visible ? 1.0f : 0.0f, 0.0f, 2, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        Animatable alpha = (Animatable) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Boolean boolValueOf = Boolean.valueOf(visible);
        ComposerKt.sourceInformationMarkerStart($composer, 1820282142, "CC(remember):SnackbarHost.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(alpha) | (((($changed & 112) ^ 48) > 32 && $composer.changed(visible)) || ($changed & 48) == 32) | $composer.changedInstance(animationSpec) | (((($changed & 896) ^ 384) > 256 && $composer.changed(function1)) || ($changed & 384) == 256);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = (Function2) new SnackbarHostKt$animatedOpacity$2$1(alpha, visible, animationSpec, function1, null);
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv3, $composer, ($changed >> 3) & 14);
        State<Float> stateAsState = alpha.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return stateAsState;
    }

    private static final State<Float> animatedScale(AnimationSpec<Float> animationSpec, boolean visible, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 2003504988, "C(animatedScale)N(animation,visible)351@13408L51,352@13488L85,352@13464L109:SnackbarHost.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2003504988, $changed, -1, "androidx.compose.material.animatedScale (SnackbarHost.kt:350)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 1222948143, "CC(remember):SnackbarHost.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = AnimatableKt.Animatable$default(!visible ? 1.0f : 0.8f, 0.0f, 2, null);
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        Animatable scale = (Animatable) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Boolean boolValueOf = Boolean.valueOf(visible);
        ComposerKt.sourceInformationMarkerStart($composer, 1222950737, "CC(remember):SnackbarHost.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(scale) | (((($changed & 112) ^ 48) > 32 && $composer.changed(visible)) || ($changed & 48) == 32) | $composer.changedInstance(animationSpec);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = (Function2) new SnackbarHostKt$animatedScale$1$1(scale, visible, animationSpec, null);
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer, ($changed >> 3) & 14);
        State<Float> stateAsState = scale.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return stateAsState;
    }
}
