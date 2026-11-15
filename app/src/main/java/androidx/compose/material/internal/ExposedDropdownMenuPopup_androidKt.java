package androidx.compose.material.internal;

import android.view.View;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import com.facebook.common.util.UriUtil;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ExposedDropdownMenuPopup.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a:\u0010\u0000\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0007H\u0001¢\u0006\u0002\u0010\b\u001a+\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0013\b\b\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0007H\u0083\b¢\u0006\u0002\u0010\u0011\"\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0012²\u0006\u0015\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0007X\u008a\u0084\u0002"}, d2 = {"ExposedDropdownMenuPopup", "", "onDismissRequest", "Lkotlin/Function0;", "popupPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "LocalPopupTestTag", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalPopupTestTag", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "SimpleStack", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "material", "currentContent"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ExposedDropdownMenuPopup_androidKt {
    private static final ProvidableCompositionLocal<String> LocalPopupTestTag = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ExposedDropdownMenuPopup_androidKt.LocalPopupTestTag$lambda$0();
        }
    }, 1, null);

    static final Unit ExposedDropdownMenuPopup$lambda$9(Function0 function0, PopupPositionProvider popupPositionProvider, Function2 function2, int i, int i2, Composer composer, int i3) {
        ExposedDropdownMenuPopup(function0, popupPositionProvider, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:108:0x03e3  */
    public static final void ExposedDropdownMenuPopup(Function0<Unit> function0, final PopupPositionProvider popupPositionProvider, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        final Function0<Unit> function3;
        Function0<Unit> function4;
        final Function0<Unit> function5;
        boolean z;
        final String testTag;
        Object value$iv;
        Function0<ComposeUiNode> function6;
        Composer $composer$iv;
        final PopupPositionProvider popupPositionProvider2 = popupPositionProvider;
        Composer $composer2 = $composer.startRestartGroup(1705178815);
        ComposerKt.sourceInformation($composer2, "C(ExposedDropdownMenuPopup)N(onDismissRequest,popupPositionProvider,content)81@3323L7,82@3362L7,83@3406L7,84@3461L7,85@3497L28,86@3552L29,87@3617L21,87@3600L38,88@3661L960,114@4657L351,114@4627L381,128@5025L182,128@5014L193,136@5253L126,136@5213L166,148@5648L494,159@6150L99,145@5557L692:ExposedDropdownMenuPopup.android.kt#mnwmf7");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            function1 = function0;
        } else if (($changed & 6) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 4 : 2;
        } else {
            function1 = function0;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(popupPositionProvider2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            if (i2 != 0) {
                function4 = null;
            } else {
                function4 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1705178815, $dirty2, -1, "androidx.compose.material.internal.ExposedDropdownMenuPopup (ExposedDropdownMenuPopup.android.kt:80)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            View view = (View) objConsume;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density = (Density) objConsume2;
            ProvidableCompositionLocal<String> providableCompositionLocal = LocalPopupTestTag;
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(providableCompositionLocal);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            String testTag2 = (String) objConsume3;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final LayoutDirection layoutDirection = (LayoutDirection) objConsume4;
            CompositionContext parentComposition = ComposablesKt.rememberCompositionContext($composer2, 0);
            final State currentContent$delegate = SnapshotStateKt.rememberUpdatedState(function2, $composer2, ($dirty2 >> 6) & 14);
            Object[] objArr = new Object[0];
            ComposerKt.sourceInformationMarkerStart($composer2, -2000650572, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function0() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UUID.randomUUID();
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            UUID popupId = (UUID) RememberSaveableKt.rememberSaveable(objArr, (Function0) it$iv, $composer2, 48);
            ComposerKt.sourceInformationMarkerStart($composer2, -2000648225, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Function0<Unit> function7 = function4;
                final PopupLayout popupLayout = new PopupLayout(function7, testTag2, view, density, popupPositionProvider2, popupId);
                testTag = testTag2;
                popupPositionProvider2 = popupPositionProvider2;
                function5 = function7;
                z = true;
                popupLayout.setContent(parentComposition, ComposableLambdaKt.composableLambdaInstance(1879981140, true, new Function2() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup$lambda$2$0$0(popupLayout, currentContent$delegate, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }));
                value$iv = popupLayout;
                $composer2.updateRememberedValue(value$iv);
            } else {
                function5 = function4;
                z = true;
                testTag = testTag2;
                value$iv = it$iv2;
            }
            final PopupLayout popupLayout2 = (PopupLayout) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -2000616962, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(popupLayout2) | (($dirty2 & 14) == 4 ? z : false) | $composer2.changed(testTag) | $composer2.changed(layoutDirection.ordinal());
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function1() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup$lambda$3$0(popupLayout2, function5, testTag, layoutDirection, (DisposableEffectScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(popupLayout2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv3, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -2000605355, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(popupLayout2) | (($dirty2 & 14) == 4) | $composer2.changed(testTag) | $composer2.changed(layoutDirection.ordinal());
            Object it$iv4 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = new Function0() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup$lambda$4$0(popupLayout2, function5, testTag, layoutDirection);
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) it$iv4, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -2000598115, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changedInstance(popupLayout2) | (($dirty2 & 112) == 32);
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = new Function1() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup$lambda$5$0(popupLayout2, popupPositionProvider2, (DisposableEffectScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
                it$iv5 = value$iv5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(popupPositionProvider2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv5, $composer2, ($dirty2 >> 3) & 14);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -2000585107, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            boolean invalid$iv4 = $composer2.changedInstance(popupLayout2);
            Object it$iv6 = $composer2.rememberedValue();
            if (invalid$iv4 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = new Function1() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup$lambda$7$0(popupLayout2, (LayoutCoordinates) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv6);
                it$iv6 = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv = OnGloballyPositionedModifierKt.onGloballyPositioned(companion, (Function1) it$iv6);
            ComposerKt.sourceInformationMarkerStart($composer2, -2000569438, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            boolean invalid$iv5 = $composer2.changedInstance(popupLayout2) | $composer2.changed(layoutDirection.ordinal());
            Object it$iv7 = $composer2.rememberedValue();
            if (invalid$iv5 || it$iv7 == Composer.INSTANCE.getEmpty()) {
                Object value$iv7 = (MeasurePolicy) new ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$6$1(popupLayout2, layoutDirection);
                $composer2.updateRememberedValue(value$iv7);
                it$iv7 = value$iv7;
            }
            MeasurePolicy measurePolicy$iv = (MeasurePolicy) it$iv7;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = ((0 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function6 = constructor;
                $composer2.createNode(function6);
            } else {
                function6 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                $composer$iv = $composer2;
            } else {
                $composer$iv = $composer2;
                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                }
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = ($changed$iv$iv >> 6) & 14;
                Composer $composer3 = $composer$iv;
                ComposerKt.sourceInformationMarkerStart($composer3, -88834835, "C:ExposedDropdownMenuPopup.android.kt#mnwmf7");
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer$iv.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                ComposerKt.sourceInformationMarkerEnd($composer$iv);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function5;
            }
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = ($changed$iv$iv >> 6) & 14;
            Composer $composer4 = $composer$iv;
            ComposerKt.sourceInformationMarkerStart($composer4, -88834835, "C:ExposedDropdownMenuPopup.android.kt#mnwmf7");
            ComposerKt.sourceInformationMarkerEnd($composer4);
            $composer$iv.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function3 = function5;
        } else {
            $composer2.skipToGroupEnd();
            function3 = function1;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup$lambda$9(function3, popupPositionProvider, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final Function2<Composer, Integer, Unit> ExposedDropdownMenuPopup$lambda$0(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuPopup$lambda$2$0$0(final PopupLayout $this_apply, State $currentContent$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C100@4111L16,102@4230L134,99@4055L528:ExposedDropdownMenuPopup.android.kt#mnwmf7");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1879981140, $changed, -1, "androidx.compose.material.internal.ExposedDropdownMenuPopup.<anonymous>.<anonymous>.<anonymous> (ExposedDropdownMenuPopup.android.kt:99)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1519353564, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup$lambda$2$0$0$0$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1519349638, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            boolean invalid$iv = $composer.changedInstance($this_apply);
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup$lambda$2$0$0$1$0($this_apply, (IntSize) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifier$iv = AlphaKt.alpha(OnRemeasuredModifierKt.onSizeChanged(modifierSemantics$default, (Function1) it$iv2), $this_apply.getCanCalculatePosition() ? 1.0f : 0.0f);
            Function2<Composer, Integer, Unit> function2ExposedDropdownMenuPopup$lambda$0 = ExposedDropdownMenuPopup$lambda$0($currentContent$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -780896836, "CC(SimpleStack)N(modifier,content)175@6840L896,175@6793L943:ExposedDropdownMenuPopup.android.kt#mnwmf7");
            ComposerKt.sourceInformationMarkerStart($composer, 1177422492, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = (MeasurePolicy) ExposedDropdownMenuPopup_androidKt$SimpleStack$1$1.INSTANCE;
                $composer.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            int $changed$iv$iv = ((0 >> 3) & 14) | 384 | ((0 << 3) & 112);
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
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            function2ExposedDropdownMenuPopup$lambda$0.invoke($composer, Integer.valueOf(($changed$iv$iv$iv >> 6) & 14));
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
    public static final Unit ExposedDropdownMenuPopup$lambda$2$0$0$0$0(SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.popup($this$semantics);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuPopup$lambda$2$0$0$1$0(PopupLayout $this_apply, IntSize it) {
        $this_apply.m2028setPopupContentSizefhxjrPA(it);
        $this_apply.updatePosition();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult ExposedDropdownMenuPopup$lambda$3$0(final PopupLayout $popupLayout, Function0 $onDismissRequest, String $testTag, LayoutDirection $layoutDirection, DisposableEffectScope $this$DisposableEffect) {
        $popupLayout.show();
        $popupLayout.updateParameters($onDismissRequest, $testTag, $layoutDirection);
        return new DisposableEffectResult() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$lambda$3$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $popupLayout.disposeComposition();
                $popupLayout.dismiss();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuPopup$lambda$4$0(PopupLayout $popupLayout, Function0 $onDismissRequest, String $testTag, LayoutDirection $layoutDirection) {
        $popupLayout.updateParameters($onDismissRequest, $testTag, $layoutDirection);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult ExposedDropdownMenuPopup$lambda$5$0(PopupLayout $popupLayout, PopupPositionProvider $popupPositionProvider, DisposableEffectScope $this$DisposableEffect) {
        $popupLayout.setPositionProvider($popupPositionProvider);
        $popupLayout.updatePosition();
        return new DisposableEffectResult() { // from class: androidx.compose.material.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$lambda$5$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuPopup$lambda$7$0(PopupLayout $popupLayout, LayoutCoordinates childCoordinates) {
        LayoutCoordinates coordinates = childCoordinates.getParentLayoutCoordinates();
        Intrinsics.checkNotNull(coordinates);
        long layoutSize = coordinates.mo6549getSizeYbymL2g();
        long position = LayoutCoordinatesKt.positionInWindow(coordinates);
        int bits$iv$iv$iv = (int) (position >> 32);
        int x$iv = MathKt.roundToInt(Float.intBitsToFloat(bits$iv$iv$iv));
        int bits$iv$iv$iv2 = (int) (position & 4294967295L);
        int y$iv = MathKt.roundToInt(Float.intBitsToFloat(bits$iv$iv$iv2));
        long layoutPosition = IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) y$iv) & 4294967295L));
        $popupLayout.setParentBounds(IntRectKt.m8066IntRectVbeCjmY(layoutPosition, layoutSize));
        $popupLayout.updatePosition();
        return Unit.INSTANCE;
    }

    static final String LocalPopupTestTag$lambda$0() {
        return "DEFAULT_TEST_TAG";
    }

    public static final ProvidableCompositionLocal<String> getLocalPopupTestTag() {
        return LocalPopupTestTag;
    }

    private static final void SimpleStack(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -780896836, "CC(SimpleStack)N(modifier,content)175@6840L896,175@6793L943:ExposedDropdownMenuPopup.android.kt#mnwmf7");
        ComposerKt.sourceInformationMarkerStart($composer, 1177422492, "CC(remember):ExposedDropdownMenuPopup.android.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = (MeasurePolicy) ExposedDropdownMenuPopup_androidKt$SimpleStack$1$1.INSTANCE;
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        MeasurePolicy measurePolicy$iv = (MeasurePolicy) it$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        int $changed$iv = (($changed >> 3) & 14) | 384 | (($changed << 3) & 112);
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
        int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer, modifier);
        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            $composer.createNode(constructor);
        } else {
            $composer.useNode();
        }
        Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer);
        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
            $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
            $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), setCompositeKeyHash);
        }
        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
        function2.invoke($composer, Integer.valueOf(($changed$iv$iv >> 6) & 14));
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
    }
}
