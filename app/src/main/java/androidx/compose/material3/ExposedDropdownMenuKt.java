package androidx.compose.material3;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.material3.internal.BackHandler_androidKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
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
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSizeKt;
import com.facebook.common.util.UriUtil;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
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
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ExposedDropdownMenu.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aQ\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u001c\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bH\u0007¢\u0006\u0002\u0010\f\u001a\u001b\u0010\r\u001a\u00020\u0003*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001aa\u0010\u001b\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020!2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002¢\u0006\u0004\b&\u0010'\u001a\"\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u0001032\u0006\u00104\u001a\u00020/H\u0002\u001a\u000e\u00105\u001a\u000203*\u0004\u0018\u000106H\u0002\"\u0018\u0010(\u001a\u00020\u0003*\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+\"\u0018\u0010,\u001a\u00020\u0003*\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b-\u0010+\"\u0010\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0004\n\u0002\u00109*8\b\u0007\u0010\u0012\"\u00020\u000e2\u00020\u000eB*\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u001c\b\u0016\u0012\u0018\b\u000bB\u0014\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0006\b\u001a\u0012\u0002\b\f¨\u0006:²\u0006\f\u0010;\u001a\u0004\u0018\u000106X\u008a\u008e\u0002²\u0006\n\u0010<\u001a\u00020/X\u008a\u008e\u0002²\u0006\n\u0010=\u001a\u00020/X\u008a\u008e\u0002"}, d2 = {"ExposedDropdownMenuBox", "", "expanded", "", "onExpandedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", UriUtil.LOCAL_CONTENT_SCHEME, "Landroidx/compose/material3/ExposedDropdownMenuBoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "hasGreaterOrEqualPriorityThan", "Landroidx/compose/material3/ExposedDropdownMenuAnchorType;", "that", "hasGreaterOrEqualPriorityThan-vVDBVkM", "(Ljava/lang/String;Ljava/lang/String;)Z", "MenuAnchorType", "Lkotlin/Deprecated;", "message", "Renamed to ExposedDropdownMenuAnchorType", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "ExposedDropdownMenuAnchorType", "imports", "expandable", "Lkotlin/Function0;", "anchorType", "alwaysFocusable", "Landroidx/compose/runtime/MutableState;", "expandedDescription", "", "collapsedDescription", "toggleDescription", "keyboardController", "Landroidx/compose/ui/platform/SoftwareKeyboardController;", "expandable-3-2CpT8", "(Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function0;Ljava/lang/String;Landroidx/compose/runtime/MutableState;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/platform/SoftwareKeyboardController;)Landroidx/compose/ui/Modifier;", "isClick", "Landroidx/compose/ui/input/key/KeyEvent;", "isClick-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "isEnterMinusSpacebar", "isEnterMinusSpacebar-ZmokQxo", "calculateMaxHeight", "", "windowBounds", "Landroidx/compose/ui/unit/IntRect;", "anchorBounds", "Landroidx/compose/ui/geometry/Rect;", "verticalMargin", "getAnchorBounds", "Landroidx/compose/ui/layout/LayoutCoordinates;", "ExposedDropdownMenuItemHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "material3", "anchorCoordinates", "anchorWidth", "menuMaxHeight"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ExposedDropdownMenuKt {
    private static final float ExposedDropdownMenuItemHorizontalPadding = Dp.m7905constructorimpl(16);

    static final Unit ExposedDropdownMenuBox$lambda$23(boolean z, Function1 function1, Modifier modifier, Function3 function3, int i, int i2, Composer composer, int i3) {
        ExposedDropdownMenuBox(z, function1, modifier, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(message = "Renamed to ExposedDropdownMenuAnchorType", replaceWith = @ReplaceWith(expression = "ExposedDropdownMenuAnchorType", imports = {}))
    public static /* synthetic */ void MenuAnchorType$annotations() {
    }

    public static final void ExposedDropdownMenuBox(boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier3;
        Modifier.Companion companion;
        boolean z2;
        String str;
        final WindowBoundsCalculator windowBoundsCalculator;
        String str2;
        MutableState mutableState;
        final FocusRequester focusRequester;
        final MutableState mutableState2;
        Object obj;
        Function0<ComposeUiNode> function0;
        final boolean z3 = z;
        Composer composerStartRestartGroup = composer.startRestartGroup(1597265892);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ExposedDropdownMenuBox)N(expanded,onExpandedChange,modifier,content)142@6635L32,143@6699L7,147@6812L53,148@6889L33,149@6948L33,151@7008L29,152@7099L7,153@7137L31,154@7200L32,155@7261L37,156@7325L89,159@7441L34,162@7501L2786,217@10336L377,216@10293L458,242@11135L47,242@11124L58,246@11347L27,246@11315L59:ExposedDropdownMenu.kt#uh7d8r");
        int i3 = i;
        if ((i2 & 1) != 0) {
            i3 |= 6;
        } else if ((i & 6) == 0) {
            i3 |= composerStartRestartGroup.changed(z3) ? 4 : 2;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 != 0) {
            i3 |= 384;
            modifier2 = modifier;
        } else if ((i & 384) == 0) {
            modifier2 = modifier;
            i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            if (i4 != 0) {
                companion = Modifier.INSTANCE;
            } else {
                companion = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1597265892, i3, -1, "androidx.compose.material3.ExposedDropdownMenuBox (ExposedDropdownMenu.kt:141)");
            }
            WindowBoundsCalculator windowBoundsCalculatorPlatformWindowBoundsCalculator = ExposedDropdownMenu_androidKt.platformWindowBoundsCalculator(composerStartRestartGroup, 0);
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume;
            final int iMo400roundToPx0680j_4 = density.mo400roundToPx0680j_4(MenuKt.getMenuVerticalMargin());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669211801, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669214245, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableIntStateOf = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objMutableIntStateOf);
                objRememberedValue2 = objMutableIntStateOf;
            }
            final MutableIntState mutableIntState = (MutableIntState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669216133, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableIntStateOf2 = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objMutableIntStateOf2);
                objRememberedValue3 = objMutableIntStateOf2;
            }
            final MutableIntState mutableIntState2 = (MutableIntState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669218049, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            int i5 = i3;
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object focusRequester2 = new FocusRequester();
                composerStartRestartGroup.updateRememberedValue(focusRequester2);
                objRememberedValue4 = focusRequester2;
            }
            FocusRequester focusRequester3 = (FocusRequester) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ProvidableCompositionLocal<SoftwareKeyboardController> localSoftwareKeyboardController = CompositionLocalsKt.getLocalSoftwareKeyboardController();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localSoftwareKeyboardController);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SoftwareKeyboardController softwareKeyboardController = (SoftwareKeyboardController) objConsume2;
            Strings.Companion companion2 = Strings.INSTANCE;
            String strM3448getString2EP1pXo = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_dropdown_menu_expanded), composerStartRestartGroup, 0);
            Strings.Companion companion3 = Strings.INSTANCE;
            String strM3448getString2EP1pXo2 = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_dropdown_menu_collapsed), composerStartRestartGroup, 0);
            Strings.Companion companion4 = Strings.INSTANCE;
            String strM3448getString2EP1pXo3 = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_dropdown_menu_toggle), composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669228253, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ExposedDropdownMenuAnchorType.m2414boximpl(ExposedDropdownMenuAnchorType.INSTANCE.m2422getPrimaryNotEditableoYjWRB4()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            MutableState mutableState4 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669231910, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                z2 = false;
                objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            } else {
                z2 = false;
            }
            MutableState mutableState5 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669236582, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            boolean zChanged = ((i5 & 14) == 4 ? true : z2) | ((i5 & 112) == 32 ? true : z2) | composerStartRestartGroup.changed(windowBoundsCalculatorPlatformWindowBoundsCalculator) | composerStartRestartGroup.changed(density);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                str = strM3448getString2EP1pXo3;
                windowBoundsCalculator = windowBoundsCalculatorPlatformWindowBoundsCalculator;
                str2 = strM3448getString2EP1pXo;
                mutableState = mutableState4;
                objRememberedValue7 = new ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1(focusRequester3, z, mutableState5, str2, strM3448getString2EP1pXo2, str, softwareKeyboardController, mutableState, function1, mutableIntState, mutableIntState2);
                focusRequester = focusRequester3;
                z3 = z;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            } else {
                str = strM3448getString2EP1pXo3;
                str2 = strM3448getString2EP1pXo;
                windowBoundsCalculator = windowBoundsCalculatorPlatformWindowBoundsCalculator;
                focusRequester = focusRequester3;
                z3 = z;
                mutableState = mutableState4;
            }
            ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1 = (ExposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669324893, r8);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(windowBoundsCalculator) | composerStartRestartGroup.changed(iMo400roundToPx0680j_4);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                final WindowBoundsCalculator windowBoundsCalculator2 = windowBoundsCalculator;
                Object obj2 = new Function1() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$15$lambda$14(windowBoundsCalculator2, iMo400roundToPx0680j_4, mutableState3, mutableIntState, mutableIntState2, (LayoutCoordinates) obj3);
                    }
                };
                mutableState2 = mutableState3;
                obj = obj2;
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue8;
                mutableState2 = mutableState3;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifier4 = companion;
            Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(modifier4, (Function1) obj);
            boolean z4 = z2;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            int i6 = ((z4 ? 1 : 0) << 3) & 112;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, z2 ? 1 : 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierOnGloballyPositioned);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((i6 << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                function0 = constructor;
                composerStartRestartGroup.createNode(function0);
            } else {
                function0 = constructor;
                composerStartRestartGroup.useNode();
            }
            Composer composerM4367constructorimpl = Updater.m4367constructorimpl(composerStartRestartGroup);
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM4367constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4367constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM4367constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM4367constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = (((z4 ? 1 : 0) >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2140542826, "C228@10736L9:ExposedDropdownMenu.kt#uh7d8r");
            function4 = function3;
            function4.invoke(exposedDropdownMenuKt$ExposedDropdownMenuBox$scope$1$1, composerStartRestartGroup, Integer.valueOf((i5 >> 6) & 112));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (z3) {
                composerStartRestartGroup.startReplaceGroup(209894723);
                ComposerKt.sourceInformation(composerStartRestartGroup, "232@10810L302,232@10781L331");
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669339986, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(windowBoundsCalculator) | composerStartRestartGroup.changed(iMo400roundToPx0680j_4);
                Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance2 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function0() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$18$lambda$17(windowBoundsCalculator, iMo400roundToPx0680j_4, mutableState2, mutableIntState2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(obj3);
                    objRememberedValue9 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ExposedDropdownMenu_androidKt.OnPlatformWindowBoundsChange((Function0) objRememberedValue9, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(210228190);
                composerStartRestartGroup.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669350131, r8);
            boolean z5 = (i5 & 14) == 4;
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function0() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$20$lambda$19(z3, focusRequester);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj4);
                objRememberedValue10 = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.SideEffect((Function0) objRememberedValue10, composerStartRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1669356895, r8);
            boolean z6 = (i5 & 112) == 32;
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (z6 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                Object obj5 = new Function0() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$22$lambda$21(function1);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj5);
                objRememberedValue11 = obj5;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            BackHandler_androidKt.BackHandler(z3, (Function0) objRememberedValue11, composerStartRestartGroup, i5 & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        } else {
            function4 = function3;
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function3<? super ExposedDropdownMenuBoxScope, ? super Composer, ? super Integer, Unit> function5 = function4;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    return ExposedDropdownMenuKt.ExposedDropdownMenuBox$lambda$23(z3, function1, modifier3, function5, i, i2, (Composer) obj6, ((Integer) obj7).intValue());
                }
            });
        }
    }

    private static final LayoutCoordinates ExposedDropdownMenuBox$lambda$2(MutableState<LayoutCoordinates> mutableState) {
        MutableState<LayoutCoordinates> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ExposedDropdownMenuBox$lambda$5(MutableIntState $anchorWidth$delegate) {
        MutableIntState $this$getValue$iv = $anchorWidth$delegate;
        return $this$getValue$iv.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int ExposedDropdownMenuBox$lambda$8(MutableIntState $menuMaxHeight$delegate) {
        MutableIntState $this$getValue$iv = $menuMaxHeight$delegate;
        return $this$getValue$iv.getIntValue();
    }

    static final Unit ExposedDropdownMenuBox$lambda$15$lambda$14(WindowBoundsCalculator $windowBoundsCalculator, int $verticalMargin, MutableState $anchorCoordinates$delegate, MutableIntState $anchorWidth$delegate, MutableIntState $menuMaxHeight$delegate, LayoutCoordinates it) {
        $anchorCoordinates$delegate.setValue(it);
        long arg0$iv = it.mo6549getSizeYbymL2g();
        $anchorWidth$delegate.setIntValue((int) (arg0$iv >> 32));
        $menuMaxHeight$delegate.setIntValue(calculateMaxHeight($windowBoundsCalculator.getVisibleWindowBounds(), getAnchorBounds(ExposedDropdownMenuBox$lambda$2($anchorCoordinates$delegate)), $verticalMargin));
        return Unit.INSTANCE;
    }

    static final Unit ExposedDropdownMenuBox$lambda$18$lambda$17(WindowBoundsCalculator $windowBoundsCalculator, int $verticalMargin, MutableState $anchorCoordinates$delegate, MutableIntState $menuMaxHeight$delegate) {
        $menuMaxHeight$delegate.setIntValue(calculateMaxHeight($windowBoundsCalculator.getVisibleWindowBounds(), getAnchorBounds(ExposedDropdownMenuBox$lambda$2($anchorCoordinates$delegate)), $verticalMargin));
        return Unit.INSTANCE;
    }

    static final Unit ExposedDropdownMenuBox$lambda$20$lambda$19(boolean $expanded, FocusRequester $focusRequester) {
        if ($expanded) {
            FocusRequester.m4757requestFocus3ESFkO8$default($focusRequester, 0, 1, null);
        }
        return Unit.INSTANCE;
    }

    static final Unit ExposedDropdownMenuBox$lambda$22$lambda$21(Function1 $onExpandedChange) {
        $onExpandedChange.invoke(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hasGreaterOrEqualPriorityThan-vVDBVkM, reason: not valid java name */
    public static final boolean m2440hasGreaterOrEqualPriorityThanvVDBVkM(String $this$hasGreaterOrEqualPriorityThan_u2dvVDBVkM, String that) {
        if (ExposedDropdownMenuAnchorType.m2417equalsimpl0($this$hasGreaterOrEqualPriorityThan_u2dvVDBVkM, ExposedDropdownMenuAnchorType.INSTANCE.m2422getPrimaryNotEditableoYjWRB4()) || ExposedDropdownMenuAnchorType.m2417equalsimpl0($this$hasGreaterOrEqualPriorityThan_u2dvVDBVkM, ExposedDropdownMenuAnchorType.INSTANCE.m2421getPrimaryEditableoYjWRB4())) {
            return true;
        }
        if (ExposedDropdownMenuAnchorType.m2417equalsimpl0($this$hasGreaterOrEqualPriorityThan_u2dvVDBVkM, ExposedDropdownMenuAnchorType.INSTANCE.m2423getSecondaryEditableoYjWRB4())) {
            return ExposedDropdownMenuAnchorType.m2417equalsimpl0(that, ExposedDropdownMenuAnchorType.INSTANCE.m2423getSecondaryEditableoYjWRB4());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: expandable-3-2CpT8, reason: not valid java name */
    public static final Modifier m2439expandable32CpT8(Modifier $this$expandable_u2d3_u2d2CpT8, final boolean expanded, final Function0<Unit> function0, final String anchorType, final MutableState<Boolean> mutableState, final String expandedDescription, final String collapsedDescription, final String toggleDescription, final SoftwareKeyboardController keyboardController) {
        return SemanticsModifierKt.semantics$default(KeyInputModifierKt.onPreviewKeyEvent(SuspendingPointerInputFilterKt.pointerInput($this$expandable_u2d3_u2d2CpT8, function0, new PointerInputEventHandler() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$expandable$1

            /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenuKt$expandable$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: ExposedDropdownMenu.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.ExposedDropdownMenuKt$expandable$1$1", f = "ExposedDropdownMenu.kt", i = {0}, l = {1426, 1430}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
            static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ String $anchorType;
                final /* synthetic */ Function0<Unit> $onExpandedChange;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(String str, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$anchorType = str;
                    this.$onExpandedChange = function0;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$anchorType, this.$onExpandedChange, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:14:0x004f  */
                /* JADX WARN: Code duplicated, block: B:17:0x0063 A[RETURN] */
                /* JADX WARN: Code duplicated, block: B:20:0x0068  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object $result) {
                    AwaitPointerEventScope $this$awaitEachGesture;
                    PointerInputChange downEvent;
                    PointerInputChange upEvent;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            $this$awaitEachGesture = (AwaitPointerEventScope) this.L$0;
                            this.L$0 = $this$awaitEachGesture;
                            this.label = 1;
                            Object objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default($this$awaitEachGesture, false, PointerEventPass.Initial, this, 1, null);
                            if (objAwaitFirstDown$default == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result = objAwaitFirstDown$default;
                            downEvent = (PointerInputChange) $result;
                            if (ExposedDropdownMenuAnchorType.m2417equalsimpl0(this.$anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2423getSecondaryEditableoYjWRB4())) {
                                downEvent.consume();
                            }
                            this.L$0 = null;
                            this.label = 2;
                            $result = TapGestureDetectorKt.waitForUpOrCancellation($this$awaitEachGesture, PointerEventPass.Initial, this);
                            if ($result == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            upEvent = (PointerInputChange) $result;
                            if (upEvent != null) {
                                this.$onExpandedChange.invoke();
                            }
                            return Unit.INSTANCE;
                        case 1:
                            AwaitPointerEventScope $this$awaitEachGesture2 = (AwaitPointerEventScope) this.L$0;
                            ResultKt.throwOnFailure($result);
                            $this$awaitEachGesture = $this$awaitEachGesture2;
                            downEvent = (PointerInputChange) $result;
                            if (ExposedDropdownMenuAnchorType.m2417equalsimpl0(this.$anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2423getSecondaryEditableoYjWRB4())) {
                                downEvent.consume();
                            }
                            this.L$0 = null;
                            this.label = 2;
                            $result = TapGestureDetectorKt.waitForUpOrCancellation($this$awaitEachGesture, PointerEventPass.Initial, this);
                            if ($result == coroutine_suspended) {
                                return coroutine_suspended;
                            }
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
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new AnonymousClass1(anchorType, function0, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        }), new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$expandable$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                return m2443invokeZmokQxo(keyEvent.m6242unboximpl());
            }

            /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
            public final Boolean m2443invokeZmokQxo(android.view.KeyEvent it) {
                if (ExposedDropdownMenuKt.m2441isClickZmokQxo(it)) {
                    if (ExposedDropdownMenuAnchorType.m2417equalsimpl0(anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2421getPrimaryEditableoYjWRB4())) {
                        if (ExposedDropdownMenuKt.m2442isEnterMinusSpacebarZmokQxo(it)) {
                            function0.invoke();
                            return true;
                        }
                    } else {
                        function0.invoke();
                    }
                }
                if (ExposedDropdownMenuAnchorType.m2417equalsimpl0(anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2421getPrimaryEditableoYjWRB4()) && expanded && (Key.m5945equalsimpl0(KeyEvent_androidKt.m6253getKeyZmokQxo(it), Key.INSTANCE.m6181getTabEK5gGoQ()) || Key.m5945equalsimpl0(KeyEvent_androidKt.m6253getKeyZmokQxo(it), Key.INSTANCE.m6018getDirectionDownEK5gGoQ()) || Key.m5945equalsimpl0(KeyEvent_androidKt.m6253getKeyZmokQxo(it), Key.INSTANCE.m6023getDirectionUpEK5gGoQ()))) {
                    mutableState.setValue(true);
                    return true;
                }
                mutableState.setValue(false);
                return false;
            }
        }), false, new Function1() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ExposedDropdownMenuKt.expandable_3_2CpT8$lambda$25(anchorType, expanded, expandedDescription, collapsedDescription, toggleDescription, function0, keyboardController, (SemanticsPropertyReceiver) obj);
            }
        }, 1, null);
    }

    static final Unit expandable_3_2CpT8$lambda$25(final String $anchorType, boolean $expanded, String $expandedDescription, String $collapsedDescription, String $toggleDescription, final Function0 $onExpandedChange, final SoftwareKeyboardController $keyboardController, SemanticsPropertyReceiver $this$semantics) {
        if (ExposedDropdownMenuAnchorType.m2417equalsimpl0($anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2423getSecondaryEditableoYjWRB4())) {
            SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7099getButtono7Vup1c());
            SemanticsPropertiesKt.setStateDescription($this$semantics, $expanded ? $expandedDescription : $collapsedDescription);
            SemanticsPropertiesKt.setContentDescription($this$semantics, $toggleDescription);
        } else {
            SemanticsPropertiesKt.m7118setRolekuIjeqM($this$semantics, Role.INSTANCE.m7102getDropdownListo7Vup1c());
        }
        SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0() { // from class: androidx.compose.material3.ExposedDropdownMenuKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(ExposedDropdownMenuKt.expandable_3_2CpT8$lambda$25$lambda$24($onExpandedChange, $anchorType, $keyboardController));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    static final boolean expandable_3_2CpT8$lambda$25$lambda$24(Function0 $onExpandedChange, String $anchorType, SoftwareKeyboardController $keyboardController) {
        $onExpandedChange.invoke();
        if (!ExposedDropdownMenuAnchorType.m2417equalsimpl0($anchorType, ExposedDropdownMenuAnchorType.INSTANCE.m2421getPrimaryEditableoYjWRB4()) || $keyboardController == null) {
            return true;
        }
        $keyboardController.show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isClick-ZmokQxo, reason: not valid java name */
    public static final boolean m2441isClickZmokQxo(android.view.KeyEvent $this$isClick) {
        return KeyEventType.m6246equalsimpl0(KeyEvent_androidKt.m6254getTypeZmokQxo($this$isClick), KeyEventType.INSTANCE.m6251getKeyUpCS__XNY()) && (m2442isEnterMinusSpacebarZmokQxo($this$isClick) || Key.m5945equalsimpl0(KeyEvent_androidKt.m6253getKeyZmokQxo($this$isClick), Key.INSTANCE.m6169getSpacebarEK5gGoQ()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isEnterMinusSpacebar-ZmokQxo, reason: not valid java name */
    public static final boolean m2442isEnterMinusSpacebarZmokQxo(android.view.KeyEvent $this$isEnterMinusSpacebar) {
        long jM6253getKeyZmokQxo = KeyEvent_androidKt.m6253getKeyZmokQxo($this$isEnterMinusSpacebar);
        if (Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6017getDirectionCenterEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6031getEnterEK5gGoQ()) || Key.m5945equalsimpl0(jM6253getKeyZmokQxo, Key.INSTANCE.m6123getNumPadEnterEK5gGoQ())) {
            return true;
        }
        return false;
    }

    private static final int calculateMaxHeight(IntRect windowBounds, Rect anchorBounds, int verticalMargin) {
        int availableHeight;
        if (anchorBounds == null) {
            return 0;
        }
        int marginedWindowTop = windowBounds.getTop() + verticalMargin;
        int marginedWindowBottom = windowBounds.getBottom() - verticalMargin;
        if (anchorBounds.getTop() > windowBounds.getBottom() || anchorBounds.getBottom() < windowBounds.getTop()) {
            availableHeight = marginedWindowBottom - marginedWindowTop;
        } else {
            float heightAbove = anchorBounds.getTop() - marginedWindowTop;
            float heightBelow = marginedWindowBottom - anchorBounds.getBottom();
            availableHeight = MathKt.roundToInt(Math.max(heightAbove, heightBelow));
        }
        return Math.max(availableHeight, 0);
    }

    private static final Rect getAnchorBounds(LayoutCoordinates $this$getAnchorBounds) {
        return ($this$getAnchorBounds == null || !$this$getAnchorBounds.isAttached()) ? Rect.INSTANCE.getZero() : RectKt.m4892Recttz77jQw(LayoutCoordinatesKt.positionInWindow($this$getAnchorBounds), IntSizeKt.m8088toSizeozmzZPI($this$getAnchorBounds.mo6549getSizeYbymL2g()));
    }
}
