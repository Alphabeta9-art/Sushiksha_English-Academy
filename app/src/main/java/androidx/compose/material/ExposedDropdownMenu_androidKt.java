package androidx.compose.material;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntRect;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExposedDropdownMenu.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001aQ\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\f\u001a\"\u0010\r\u001a\u00020\u0007*\u00020\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a6\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u0005H\u0002¨\u0006\u0019²\u0006\n\u0010\u001a\u001a\u00020\u0017X\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u0017X\u008a\u008e\u0002"}, d2 = {"ExposedDropdownMenuBox", "", "expanded", "", "onExpandedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/material/ExposedDropdownMenuBoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "expandable", "Lkotlin/Function0;", "menuLabel", "", "updateHeight", "windowBounds", "Landroidx/compose/ui/unit/IntRect;", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "verticalMarginInPx", "", "onHeightUpdate", "material", "width", "menuHeight"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ExposedDropdownMenu_androidKt {
    static final Unit ExposedDropdownMenuBox$lambda$15(boolean z, Function1 function1, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        ExposedDropdownMenuBox(z, function1, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x032a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:106:0x03a8  */
    /* JADX WARN: Code duplicated, block: B:107:0x03aa  */
    /* JADX WARN: Code duplicated, block: B:110:0x03b4  */
    /* JADX WARN: Code duplicated, block: B:113:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:116:0x03ef  */
    /* JADX WARN: Code duplicated, block: B:119:0x03f8  */
    /* JADX WARN: Code duplicated, block: B:122:0x0413  */
    /* JADX WARN: Code duplicated, block: B:77:0x021a  */
    /* JADX WARN: Code duplicated, block: B:78:0x021c  */
    /* JADX WARN: Code duplicated, block: B:81:0x0223  */
    /* JADX WARN: Code duplicated, block: B:82:0x0225  */
    /* JADX WARN: Code duplicated, block: B:85:0x0234  */
    /* JADX WARN: Code duplicated, block: B:88:0x023f  */
    /* JADX WARN: Code duplicated, block: B:92:0x02d2  */
    /* JADX WARN: Code duplicated, block: B:95:0x02de  */
    /* JADX WARN: Code duplicated, block: B:96:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:99:0x0315  */
    public static final void ExposedDropdownMenuBox(final boolean expanded, final Function1<? super Boolean, Unit> function1, Modifier modifier, final Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Modifier modifier3;
        final WindowBoundsCalculator windowBoundsCalculator;
        final Ref coordinates;
        final WindowBoundsCalculator windowBoundsCalculator2;
        Object value$iv;
        boolean z;
        boolean z2;
        boolean invalid$iv;
        Object value$iv2;
        Modifier modifier4;
        int compositeKeyHash$iv$iv;
        Function0<ComposeUiNode> constructor;
        Composer $this$Layout_u24lambda_u240$iv$iv;
        boolean invalid$iv2;
        Object it$iv;
        boolean invalid$iv3;
        Object it$iv2;
        Composer $composer2 = $composer.startRestartGroup(-1337700255);
        ComposerKt.sourceInformation($composer2, "C(ExposedDropdownMenuBox)N(expanded,onExpandedChange,modifier,content)95@4064L7,96@4105L32,97@4155L33,98@4211L33,100@4345L37,103@4408L536,116@4970L29,120@5061L414,132@5536L31,133@5597L38,118@5005L727,140@5749L47,140@5738L58,142@5831L273,142@5802L302:ExposedDropdownMenu.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(expanded) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1337700255, $dirty2, -1, "androidx.compose.material.ExposedDropdownMenuBox (ExposedDropdownMenu.kt:94)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final Density density = (Density) objConsume;
            WindowBoundsCalculator windowBoundsCalculator3 = ExposedDropdownMenu_android.platformWindowBoundsCalculator($composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -1334713342, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            final MutableIntState width$delegate = (MutableIntState) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1334711550, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object it$iv4 = $composer2.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer2.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
            }
            final MutableIntState menuHeight$delegate = (MutableIntState) it$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final int verticalMarginInPx = density.mo400roundToPx0680j_4(MenuKt.getMenuVerticalMargin());
            ComposerKt.sourceInformationMarkerStart($composer2, -1334707258, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object it$iv5 = $composer2.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = new Ref();
                $composer2.updateRememberedValue(value$iv5);
                it$iv5 = value$iv5;
            }
            final Ref coordinates2 = (Ref) it$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int iExposedDropdownMenuBox$lambda$4 = ExposedDropdownMenuBox$lambda$4(menuHeight$delegate);
            int iExposedDropdownMenuBox$lambda$1 = ExposedDropdownMenuBox$lambda$1(width$delegate);
            ComposerKt.sourceInformationMarkerStart($composer2, -1334704743, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            boolean invalid$iv4 = $composer2.changed(density) | $composer2.changed(iExposedDropdownMenuBox$lambda$4) | $composer2.changed(iExposedDropdownMenuBox$lambda$1);
            Object it$iv6 = $composer2.rememberedValue();
            if (invalid$iv4 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = new ExposedDropdownMenuBoxScope() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$ExposedDropdownMenuBox$scope$1$1
                    @Override // androidx.compose.material.ExposedDropdownMenuBoxScope
                    public Modifier exposedDropdownSize(Modifier $this$exposedDropdownSize, boolean matchTextFieldWidth) {
                        Density $this$exposedDropdownSize_u24lambda_u240 = density;
                        MutableIntState mutableIntState = menuHeight$delegate;
                        MutableIntState mutableIntState2 = width$delegate;
                        Modifier it = SizeKt.m853heightInVpY3zN4$default($this$exposedDropdownSize, 0.0f, $this$exposedDropdownSize_u24lambda_u240.mo403toDpu2uoSUM(ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$4(mutableIntState)), 1, null);
                        if (matchTextFieldWidth) {
                            return SizeKt.m870width3ABfNKs(it, $this$exposedDropdownSize_u24lambda_u240.mo403toDpu2uoSUM(ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$1(mutableIntState2)));
                        }
                        return it;
                    }
                };
                $composer2.updateRememberedValue(value$iv6);
                it$iv6 = value$iv6;
            }
            ExposedDropdownMenu_androidKt$ExposedDropdownMenuBox$scope$1$1 scope = (ExposedDropdownMenu_androidKt$ExposedDropdownMenuBox$scope$1$1) it$iv6;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1334687266, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object it$iv7 = $composer2.rememberedValue();
            if (it$iv7 == Composer.INSTANCE.getEmpty()) {
                Object value$iv7 = new FocusRequester();
                $composer2.updateRememberedValue(value$iv7);
                it$iv7 = value$iv7;
            }
            final FocusRequester focusRequester = (FocusRequester) it$iv7;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1334683969, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            boolean invalid$iv5 = $composer2.changedInstance(coordinates2) | $composer2.changedInstance(windowBoundsCalculator3) | $composer2.changed(verticalMarginInPx);
            Object it$iv8 = $composer2.rememberedValue();
            if (!invalid$iv5) {
                windowBoundsCalculator = windowBoundsCalculator3;
                if (it$iv8 != Composer.INSTANCE.getEmpty()) {
                    coordinates = coordinates2;
                    value$iv = it$iv8;
                    windowBoundsCalculator2 = windowBoundsCalculator;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier2, (Function1) value$iv);
                ComposerKt.sourceInformationMarkerStart($composer2, -1334669152, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                if (($dirty2 & 112) == 32) {
                    z = true;
                } else {
                    z = false;
                }
                if (($dirty2 & 14) == 4) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                invalid$iv = z | z2;
                value$iv2 = $composer2.rememberedValue();
                if (!invalid$iv) {
                    modifier4 = modifier2;
                    if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifier$iv = FocusRequesterModifierKt.focusRequester(expandable(modifierOnGloballyPositioned, (Function0) value$iv2, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1948getExposedDropdownMenuUdPEhr4(), $composer2, 6)), focusRequester);
                    ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                    Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                    MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                    int $changed$iv$iv = (0 << 3) & 112;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                    compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                    CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                        $composer2.createNode(constructor);
                    } else {
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
                    int i3 = ($changed$iv$iv$iv >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i4 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 527294489, "C137@5717L9:ExposedDropdownMenu.kt#jmzs0o");
                    function3.invoke(scope, $composer2, Integer.valueOf(($dirty2 >> 6) & 112));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1334662320, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                    if (($dirty2 & 14) == 4) {
                        invalid$iv2 = true;
                    } else {
                        invalid$iv2 = false;
                    }
                    it$iv = $composer2.rememberedValue();
                    if (!invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv8 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$13$0(expanded, focusRequester);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv8);
                        it$iv = value$iv8;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.SideEffect((Function0) it$iv, $composer2, 0);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1334659470, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                    invalid$iv3 = $composer2.changedInstance(windowBoundsCalculator2) | $composer2.changedInstance(coordinates) | $composer2.changed(verticalMarginInPx);
                    it$iv2 = $composer2.rememberedValue();
                    if (!invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv9 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$14$0(windowBoundsCalculator2, coordinates, verticalMarginInPx, menuHeight$delegate);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv9);
                        it$iv2 = value$iv9;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ExposedDropdownMenu_android.OnPlatformWindowBoundsChange((Function0) it$iv2, $composer2, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                } else {
                    modifier4 = modifier2;
                }
                value$iv2 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$11$0(function1, expanded);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifier$iv2 = FocusRequesterModifierKt.focusRequester(expandable(modifierOnGloballyPositioned, (Function0) value$iv2, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1948getExposedDropdownMenuUdPEhr4(), $composer2, 6)), focusRequester);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                int $changed$iv$iv2 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor);
                } else {
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = ($changed$iv$iv$iv2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 527294489, "C137@5717L9:ExposedDropdownMenu.kt#jmzs0o");
                function3.invoke(scope, $composer2, Integer.valueOf(($dirty2 >> 6) & 112));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -1334662320, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                if (($dirty2 & 14) == 4) {
                    invalid$iv2 = true;
                } else {
                    invalid$iv2 = false;
                }
                it$iv = $composer2.rememberedValue();
                if (!invalid$iv2) {
                    Object value$iv10 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$13$0(expanded, focusRequester);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv10);
                    it$iv = value$iv10;
                } else {
                    Object value$iv11 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$13$0(expanded, focusRequester);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv11);
                    it$iv = value$iv11;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) it$iv, $composer2, 0);
                ComposerKt.sourceInformationMarkerStart($composer2, -1334659470, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                invalid$iv3 = $composer2.changedInstance(windowBoundsCalculator2) | $composer2.changedInstance(coordinates) | $composer2.changed(verticalMarginInPx);
                it$iv2 = $composer2.rememberedValue();
                if (!invalid$iv3) {
                    Object value$iv12 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$14$0(windowBoundsCalculator2, coordinates, verticalMarginInPx, menuHeight$delegate);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv12);
                    it$iv2 = value$iv12;
                } else {
                    Object value$iv13 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$14$0(windowBoundsCalculator2, coordinates, verticalMarginInPx, menuHeight$delegate);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv13);
                    it$iv2 = value$iv13;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ExposedDropdownMenu_android.OnPlatformWindowBoundsChange((Function0) it$iv2, $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
            } else {
                windowBoundsCalculator = windowBoundsCalculator3;
            }
            coordinates = coordinates2;
            windowBoundsCalculator2 = windowBoundsCalculator;
            value$iv = new Function1() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$10$0(coordinates2, windowBoundsCalculator, verticalMarginInPx, width$delegate, menuHeight$delegate, (LayoutCoordinates) obj);
                }
            };
            $composer2.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierOnGloballyPositioned2 = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier2, (Function1) value$iv);
            ComposerKt.sourceInformationMarkerStart($composer2, -1334669152, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            if (($dirty2 & 112) == 32) {
                z = true;
            } else {
                z = false;
            }
            if (($dirty2 & 14) == 4) {
                z2 = true;
            } else {
                z2 = false;
            }
            invalid$iv = z | z2;
            value$iv2 = $composer2.rememberedValue();
            if (!invalid$iv) {
                modifier4 = modifier2;
                if (value$iv2 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifier$iv3 = FocusRequesterModifierKt.focusRequester(expandable(modifierOnGloballyPositioned2, (Function0) value$iv2, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1948getExposedDropdownMenuUdPEhr4(), $composer2, 6)), focusRequester);
                ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                int $changed$iv$iv3 = (0 << 3) & 112;
                ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
                CompositionLocalMap localMap$iv$iv3 = $composer2.getCurrentCompositionLocalMap();
                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(constructor);
                } else {
                    $composer2.useNode();
                }
                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer2);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                }
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash3);
                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = ($changed$iv$iv$iv3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                int i8 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 527294489, "C137@5717L9:ExposedDropdownMenu.kt#jmzs0o");
                function3.invoke(scope, $composer2, Integer.valueOf(($dirty2 >> 6) & 112));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -1334662320, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                if (($dirty2 & 14) == 4) {
                    invalid$iv2 = true;
                } else {
                    invalid$iv2 = false;
                }
                it$iv = $composer2.rememberedValue();
                if (!invalid$iv2) {
                    Object value$iv14 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$13$0(expanded, focusRequester);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv14);
                    it$iv = value$iv14;
                } else {
                    Object value$iv15 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$13$0(expanded, focusRequester);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv15);
                    it$iv = value$iv15;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) it$iv, $composer2, 0);
                ComposerKt.sourceInformationMarkerStart($composer2, -1334659470, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                invalid$iv3 = $composer2.changedInstance(windowBoundsCalculator2) | $composer2.changedInstance(coordinates) | $composer2.changed(verticalMarginInPx);
                it$iv2 = $composer2.rememberedValue();
                if (!invalid$iv3) {
                    Object value$iv16 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$14$0(windowBoundsCalculator2, coordinates, verticalMarginInPx, menuHeight$delegate);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv16);
                    it$iv2 = value$iv16;
                } else {
                    Object value$iv17 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$14$0(windowBoundsCalculator2, coordinates, verticalMarginInPx, menuHeight$delegate);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv17);
                    it$iv2 = value$iv17;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ExposedDropdownMenu_android.OnPlatformWindowBoundsChange((Function0) it$iv2, $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
            } else {
                modifier4 = modifier2;
            }
            value$iv2 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$11$0(function1, expanded);
                }
            };
            $composer2.updateRememberedValue(value$iv2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv4 = FocusRequesterModifierKt.focusRequester(expandable(modifierOnGloballyPositioned2, (Function0) value$iv2, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1948getExposedDropdownMenuUdPEhr4(), $composer2, 6)), focusRequester);
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
            int $changed$iv$iv4 = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv4 = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
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
            int i9 = ($changed$iv$iv$iv4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i10 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 527294489, "C137@5717L9:ExposedDropdownMenu.kt#jmzs0o");
            function3.invoke(scope, $composer2, Integer.valueOf(($dirty2 >> 6) & 112));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -1334662320, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            if (($dirty2 & 14) == 4) {
                invalid$iv2 = true;
            } else {
                invalid$iv2 = false;
            }
            it$iv = $composer2.rememberedValue();
            if (!invalid$iv2) {
                Object value$iv18 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$13$0(expanded, focusRequester);
                    }
                };
                $composer2.updateRememberedValue(value$iv18);
                it$iv = value$iv18;
            } else {
                Object value$iv19 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$13$0(expanded, focusRequester);
                    }
                };
                $composer2.updateRememberedValue(value$iv19);
                it$iv = value$iv19;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) it$iv, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -1334659470, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            invalid$iv3 = $composer2.changedInstance(windowBoundsCalculator2) | $composer2.changedInstance(coordinates) | $composer2.changed(verticalMarginInPx);
            it$iv2 = $composer2.rememberedValue();
            if (!invalid$iv3) {
                Object value$iv110 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$14$0(windowBoundsCalculator2, coordinates, verticalMarginInPx, menuHeight$delegate);
                    }
                };
                $composer2.updateRememberedValue(value$iv110);
                it$iv2 = value$iv110;
            } else {
                Object value$iv111 = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$14$0(windowBoundsCalculator2, coordinates, verticalMarginInPx, menuHeight$delegate);
                    }
                };
                $composer2.updateRememberedValue(value$iv111);
                it$iv2 = value$iv111;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ExposedDropdownMenu_android.OnPlatformWindowBoundsChange((Function0) it$iv2, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$15(expanded, function1, modifier3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ExposedDropdownMenuBox$lambda$1(MutableIntState $width$delegate) {
        MutableIntState $this$getValue$iv = $width$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ExposedDropdownMenuBox$lambda$4(MutableIntState $menuHeight$delegate) {
        MutableIntState $this$getValue$iv = $menuHeight$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuBox$lambda$10$0(Ref $coordinates, WindowBoundsCalculator $windowBoundsCalculator, int $verticalMarginInPx, MutableIntState $width$delegate, final MutableIntState $menuHeight$delegate, LayoutCoordinates it) {
        long arg0$iv = it.mo6549getSizeYbymL2g();
        $width$delegate.setIntValue((int) (arg0$iv >> 32));
        $coordinates.setValue(it);
        updateHeight($windowBoundsCalculator.getVisibleWindowBounds(), (LayoutCoordinates) $coordinates.getValue(), $verticalMarginInPx, new Function1() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$10$0$0($menuHeight$delegate, ((Integer) obj).intValue());
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuBox$lambda$10$0$0(MutableIntState $menuHeight$delegate, int newHeight) {
        $menuHeight$delegate.setIntValue(newHeight);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuBox$lambda$11$0(Function1 $onExpandedChange, boolean $expanded) {
        $onExpandedChange.invoke(Boolean.valueOf(!$expanded));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuBox$lambda$13$0(boolean $expanded, FocusRequester $focusRequester) {
        if ($expanded) {
            FocusRequester.m4757requestFocus3ESFkO8$default($focusRequester, 0, 1, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuBox$lambda$14$0(WindowBoundsCalculator $windowBoundsCalculator, Ref $coordinates, int $verticalMarginInPx, final MutableIntState $menuHeight$delegate) {
        updateHeight($windowBoundsCalculator.getVisibleWindowBounds(), (LayoutCoordinates) $coordinates.getValue(), $verticalMarginInPx, new Function1() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExposedDropdownMenu_androidKt.ExposedDropdownMenuBox$lambda$14$0$0($menuHeight$delegate, ((Integer) obj).intValue());
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ExposedDropdownMenuBox$lambda$14$0$0(MutableIntState $menuHeight$delegate, int newHeight) {
        $menuHeight$delegate.setIntValue(newHeight);
        return Unit.INSTANCE;
    }

    private static final Modifier expandable(Modifier $this$expandable, final Function0<Unit> function0, final String menuLabel) {
        return SemanticsModifierKt.semantics$default(SuspendingPointerInputFilterKt.pointerInput($this$expandable, function0, new PointerInputEventHandler() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt.expandable.1

            /* JADX INFO: renamed from: androidx.compose.material.ExposedDropdownMenu_androidKt$expandable$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: ExposedDropdownMenu.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material.ExposedDropdownMenu_androidKt$expandable$1$1", f = "ExposedDropdownMenu.kt", i = {0}, l = {450, 451}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"}, v = 1)
            static final class C00471 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function0<Unit> $onExpandedChange;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00471(Function0<Unit> function0, Continuation<? super C00471> continuation) {
                    super(2, continuation);
                    this.$onExpandedChange = function0;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00471 c00471 = new C00471(this.$onExpandedChange, continuation);
                    c00471.L$0 = obj;
                    return c00471;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00471) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:14:0x004d A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:15:0x004e  */
                /* JADX WARN: Code duplicated, block: B:18:0x0054  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    AwaitPointerEventScope $this$awaitEachGesture;
                    Object objWaitForUpOrCancellation;
                    PointerInputChange upEvent;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                            this.L$0 = $this$awaitEachGesture2;
                            this.label = 1;
                            if (TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture2, false, PointerEventPass.Initial, this, 1, null) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$awaitEachGesture = $this$awaitEachGesture2;
                            this.L$0 = null;
                            this.label = 2;
                            objWaitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation($this$awaitEachGesture, PointerEventPass.Initial, this);
                            if (objWaitForUpOrCancellation == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result = objWaitForUpOrCancellation;
                            upEvent = (PointerInputChange) $result;
                            if (upEvent != null) {
                                this.$onExpandedChange.invoke();
                            }
                            return Unit.INSTANCE;
                        case 1:
                            $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                            ResultKt.throwOnFailure($result);
                            this.L$0 = null;
                            this.label = 2;
                            objWaitForUpOrCancellation = TapGestureDetectorKt.waitForUpOrCancellation($this$awaitEachGesture, PointerEventPass.Initial, this);
                            if (objWaitForUpOrCancellation == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result = objWaitForUpOrCancellation;
                            upEvent = (PointerInputChange) $result;
                            if (upEvent != null) {
                                this.$onExpandedChange.invoke();
                            }
                            return Unit.INSTANCE;
                        case 2:
                            ResultKt.throwOnFailure($result);
                            upEvent = (PointerInputChange) $result;
                            if (upEvent != null) {
                                this.$onExpandedChange.invoke();
                            }
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C00471(function0, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        }), false, new Function1() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExposedDropdownMenu_androidKt.expandable$lambda$0(menuLabel, function0, (SemanticsPropertyReceiver) obj);
            }
        }, 1, null);
    }

    static final Unit expandable$lambda$0(String $menuLabel, final Function0 $onExpandedChange, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $menuLabel);
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material.ExposedDropdownMenu_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(ExposedDropdownMenu_androidKt.expandable$lambda$0$0($onExpandedChange));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean expandable$lambda$0$0(Function0 $onExpandedChange) {
        $onExpandedChange.invoke();
        return true;
    }

    private static final void updateHeight(IntRect windowBounds, LayoutCoordinates coordinates, int verticalMarginInPx, Function1<? super Integer, Unit> function1) {
        if (coordinates == null) {
            return;
        }
        float heightAbove = LayoutCoordinatesKt.boundsInWindow(coordinates, true).getTop() - windowBounds.getTop();
        float heightBelow = (windowBounds.getBottom() - windowBounds.getTop()) - LayoutCoordinatesKt.boundsInWindow(coordinates, true).getBottom();
        function1.invoke(Integer.valueOf(((int) Math.max(heightAbove, heightBelow)) - verticalMarginInPx));
    }
}
