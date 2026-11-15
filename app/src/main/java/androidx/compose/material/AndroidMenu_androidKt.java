package androidx.compose.material;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
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
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AndroidMenu.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aa\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001ak\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001ac\u0010\u0017\u001a\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001f\"\u0014\u0010 \u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"DropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/unit/DpOffset;", "properties", "Landroidx/compose/ui/window/PopupProperties;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DropdownMenu-ILWXrKs", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "scrollState", "Landroidx/compose/foundation/ScrollState;", "DropdownMenu-4kj-_NE", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/ScrollState;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItem", "onClick", "enabled", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/layout/RowScope;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DefaultMenuProperties", "getDefaultMenuProperties", "()Landroidx/compose/ui/window/PopupProperties;", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidMenu_androidKt {
    private static final PopupProperties DefaultMenuProperties = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);

    static final Unit DropdownMenuItem$lambda$0(Function0 function0, Modifier modifier, boolean z, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, Function3 function3, int i, int i2, Composer composer, int i3) {
        DropdownMenuItem(function0, modifier, z, paddingValues, mutableInteractionSource, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DropdownMenu_4kj__NE$lambda$4(boolean z, Function0 function0, Modifier modifier, long j, ScrollState scrollState, PopupProperties popupProperties, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1684DropdownMenu4kj_NE(z, function0, modifier, j, scrollState, popupProperties, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DropdownMenu_ILWXrKs$lambda$0(boolean z, Function0 function0, Modifier modifier, long j, PopupProperties popupProperties, Function3 function3, int i, int i2, Composer composer, int i3) {
        m1685DropdownMenuILWXrKs(z, function0, modifier, j, popupProperties, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced by a DropdownMenu function with a ScrollState parameter", replaceWith = @ReplaceWith(expression = "DropdownMenu(expanded,onDismissRequest, modifier, offset, rememberScrollState(), properties, content)", imports = {"androidx.compose.foundation.rememberScrollState"}))
    /* JADX INFO: renamed from: DropdownMenu-ILWXrKs, reason: not valid java name */
    public static final /* synthetic */ void m1685DropdownMenuILWXrKs(final boolean expanded, final Function0 onDismissRequest, Modifier modifier, long j, PopupProperties properties, final Function3 content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0 function0;
        Modifier modifier2;
        Function3 function3;
        Composer $composer2;
        final PopupProperties properties2;
        final Modifier modifier3;
        final long j2;
        long j3;
        Composer $composer3 = $composer.startRestartGroup(-2042390678);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)N(expanded,onDismissRequest,modifier,offset:c#ui.unit.DpOffset,properties,content)61@2359L21,56@2195L252:AndroidMenu.android.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            z = expanded;
            $dirty |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if (($changed & 48) == 0) {
            function0 = onDismissRequest;
            $dirty |= $composer3.changedInstance(function0) ? 32 : 16;
        } else {
            function0 = onDismissRequest;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(j) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(properties) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function3 = content;
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        } else {
            function3 = content;
        }
        if ($composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            Modifier modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (i3 != 0) {
                float x$iv = Dp.m7905constructorimpl(0);
                float y$iv = Dp.m7905constructorimpl(0);
                long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                long v1$iv$iv2 = Float.floatToRawIntBits(y$iv);
                long v2$iv$iv = DpOffset.m7961constructorimpl((v1$iv$iv << 32) | (v1$iv$iv2 & 4294967295L));
                j3 = v2$iv$iv;
            } else {
                j3 = j;
            }
            PopupProperties properties3 = i4 != 0 ? new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null) : properties;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2042390678, $dirty, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:56)");
            }
            $composer2 = $composer3;
            m1684DropdownMenu4kj_NE(z, function0, modifier4, j3, ScrollKt.rememberScrollState(0, $composer3, 0, 1), properties3, function3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            j2 = j3;
            properties2 = properties3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            properties2 = properties;
            modifier3 = modifier2;
            j2 = j;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AndroidMenu_androidKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidMenu_androidKt.DropdownMenu_ILWXrKs$lambda$0(expanded, onDismissRequest, modifier3, j2, properties2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DropdownMenu-4kj-_NE, reason: not valid java name */
    public static final void m1684DropdownMenu4kj_NE(boolean expanded, final Function0<Unit> function0, Modifier modifier, long j, ScrollState scrollState, PopupProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        long jM7961constructorimpl;
        ScrollState scrollState2;
        PopupProperties popupProperties;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4;
        char c;
        Composer $composer2;
        final Modifier modifier3;
        final ScrollState scrollState3;
        final PopupProperties properties2;
        final long j2;
        int $dirty;
        int $dirty2;
        final Modifier modifier4;
        final ScrollState scrollState4;
        PopupProperties properties3;
        Composer $composer3 = $composer.startRestartGroup(1275450738);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)N(expanded,onDismissRequest,modifier,offset:c#ui.unit.DpOffset,scrollState,properties,content)76@2730L42:AndroidMenu.android.kt#jmzs0o");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            z = expanded;
            $dirty3 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function0) ? 32 : 16;
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
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty3 |= 3072;
            jM7961constructorimpl = j;
        } else if (($changed & 3072) == 0) {
            jM7961constructorimpl = j;
            $dirty3 |= $composer3.changed(jM7961constructorimpl) ? 2048 : 1024;
        } else {
            jM7961constructorimpl = j;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                scrollState2 = scrollState;
                int i4 = $composer3.changed(scrollState2) ? 16384 : 8192;
                $dirty3 |= i4;
            } else {
                scrollState2 = scrollState;
            }
            $dirty3 |= i4;
        } else {
            scrollState2 = scrollState;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            popupProperties = properties;
        } else if ((196608 & $changed) == 0) {
            popupProperties = properties;
            $dirty3 |= $composer3.changed(popupProperties) ? 131072 : 65536;
        } else {
            popupProperties = properties;
        }
        if (($changed & 1572864) == 0) {
            function4 = function3;
            c = ' ';
            $dirty3 |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        } else {
            function4 = function3;
            c = ' ';
        }
        int $dirty4 = $dirty3;
        if ($composer3.shouldExecute(($dirty3 & 599187) != 599186, $dirty4 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "122@5703L21");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    float x$iv = Dp.m7905constructorimpl(0);
                    float y$iv = Dp.m7905constructorimpl(0);
                    long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                    long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                    jM7961constructorimpl = DpOffset.m7961constructorimpl((v1$iv$iv << c) | (v2$iv$iv & 4294967295L));
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty4 & (-57345);
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                } else {
                    $dirty = $dirty4;
                }
                if (i5 != 0) {
                    properties3 = DefaultMenuProperties;
                    $dirty2 = $dirty;
                    modifier4 = modifier2;
                    scrollState4 = scrollState2;
                } else {
                    $dirty2 = $dirty;
                    modifier4 = modifier2;
                    scrollState4 = scrollState2;
                    properties3 = popupProperties;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 = $dirty4 & (-57345);
                    modifier4 = modifier2;
                    scrollState4 = scrollState2;
                    properties3 = popupProperties;
                } else {
                    modifier4 = modifier2;
                    scrollState4 = scrollState2;
                    properties3 = popupProperties;
                    $dirty2 = $dirty4;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1275450738, $dirty2, -1, "androidx.compose.material.DropdownMenu (AndroidMenu.android.kt:75)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1642488284, "CC(remember):AndroidMenu.android.kt#9igjgp");
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
                $composer3.startReplaceGroup(-622294666);
                ComposerKt.sourceInformation($composer3, "80@2920L51,81@3007L7,83@3109L139,91@3420L283,87@3258L445");
                ComposerKt.sourceInformationMarkerStart($composer3, 1642494373, "CC(remember):AndroidMenu.android.kt#9igjgp");
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
                ComposerKt.sourceInformationMarkerStart($composer3, 1642500509, "CC(remember):AndroidMenu.android.kt#9igjgp");
                Object it$iv3 = $composer3.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function2() { // from class: androidx.compose.material.AndroidMenu_androidKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidMenu_androidKt.DropdownMenu_4kj__NE$lambda$2$0(transformOriginState, (IntRect) obj, (IntRect) obj2);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                DropdownMenuPositionProvider popupPositionProvider = new DropdownMenuPositionProvider(jM7961constructorimpl, density, (Function2) it$iv3, null);
                final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function5 = function4;
                AndroidPopup_androidKt.Popup(popupPositionProvider, function0, properties3, ComposableLambdaKt.rememberComposableLambda(1788768427, true, new Function2() { // from class: androidx.compose.material.AndroidMenu_androidKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidMenu_androidKt.DropdownMenu_4kj__NE$lambda$3(expandedStates, transformOriginState, scrollState4, modifier4, function5, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer3, 54), $composer3, ($dirty2 & 112) | 3072 | (($dirty2 >> 9) & 896), 0);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(-625181200);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            properties2 = properties3;
            scrollState3 = scrollState4;
            modifier3 = modifier4;
            j2 = jM7961constructorimpl;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            scrollState3 = scrollState2;
            properties2 = popupProperties;
            j2 = jM7961constructorimpl;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final boolean z2 = z;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AndroidMenu_androidKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidMenu_androidKt.DropdownMenu_4kj__NE$lambda$4(z2, function0, modifier3, j2, scrollState3, properties2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DropdownMenu_4kj__NE$lambda$2$0(MutableState $transformOriginState, IntRect parentBounds, IntRect menuBounds) {
        $transformOriginState.setValue(TransformOrigin.m5497boximpl(MenuKt.calculateTransformOrigin(parentBounds, menuBounds)));
        return Unit.INSTANCE;
    }

    static final Unit DropdownMenu_4kj__NE$lambda$3(MutableTransitionState $expandedStates, MutableState $transformOriginState, ScrollState $scrollState, Modifier $modifier, Function3 $content, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C92@3434L259:AndroidMenu.android.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1788768427, $changed, -1, "androidx.compose.material.DropdownMenu.<anonymous> (AndroidMenu.android.kt:92)");
            }
            MenuKt.DropdownMenuContent($expandedStates, $transformOriginState, $scrollState, $modifier, $content, $composer, MutableTransitionState.$stable | 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static final void DropdownMenuItem(final Function0<Unit> function0, Modifier modifier, boolean enabled, PaddingValues contentPadding, MutableInteractionSource interactionSource, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        Modifier modifier2;
        boolean z;
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4;
        final PaddingValues contentPadding2;
        final Modifier modifier3;
        final boolean enabled2;
        final MutableInteractionSource interactionSource2;
        Modifier modifier4;
        int i2;
        boolean enabled3;
        Composer $composer2 = $composer.startRestartGroup(670540513);
        ComposerKt.sourceInformation($composer2, "C(DropdownMenuItem)N(onClick,modifier,enabled,contentPadding,interactionSource,content)112@3970L160:AndroidMenu.android.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 4 : 2;
        } else {
            function1 = function0;
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
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty |= $composer2.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(contentPadding) ? 2048 : 1024;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(interactionSource) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function3;
        }
        if (!$composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            contentPadding2 = contentPadding;
            modifier3 = modifier2;
            enabled2 = z;
            interactionSource2 = interactionSource;
        } else {
            if (i3 == 0) {
                modifier4 = modifier2;
            } else {
                modifier4 = Modifier.INSTANCE;
            }
            if (i4 != 0) {
                enabled3 = true;
                i2 = i6;
            } else {
                i2 = i6;
                enabled3 = z;
            }
            PaddingValues contentPadding3 = i5 != 0 ? MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding() : contentPadding;
            MutableInteractionSource interactionSource3 = i2 != 0 ? null : interactionSource;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(670540513, $dirty, -1, "androidx.compose.material.DropdownMenuItem (AndroidMenu.android.kt:112)");
            }
            MenuKt.DropdownMenuItemContent(function1, modifier4, enabled3, contentPadding3, interactionSource3, function4, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            enabled2 = enabled3;
            contentPadding2 = contentPadding3;
            interactionSource2 = interactionSource3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AndroidMenu_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidMenu_androidKt.DropdownMenuItem$lambda$0(function0, modifier3, enabled2, contentPadding2, interactionSource2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final PopupProperties getDefaultMenuProperties() {
        return DefaultMenuProperties;
    }
}
