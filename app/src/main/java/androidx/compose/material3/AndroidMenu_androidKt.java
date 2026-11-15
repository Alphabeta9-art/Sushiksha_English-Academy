package androidx.compose.material3;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.internal.DropdownMenuPositionProvider;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupProperties;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
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
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u009f\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u001c\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001ak\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001aa\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\u001c\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007¢\u0006\u0004\b \u0010!\u001a\u0090\u0001\u0010\"\u001a\u00020\u00012\u0011\u0010#\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001a2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0015\b\u0002\u0010%\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010&\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\u001a2\b\b\u0002\u0010'\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-H\u0007¢\u0006\u0002\u0010.\"\u0014\u0010/\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101¨\u00062"}, d2 = {"DropdownMenu", "", "expanded", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", TypedValues.CycleType.S_WAVE_OFFSET, "Landroidx/compose/ui/unit/DpOffset;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "properties", "Landroidx/compose/ui/window/PopupProperties;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DropdownMenu-IlH_yew", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/ScrollState;Landroidx/compose/ui/window/PopupProperties;Landroidx/compose/ui/graphics/Shape;JFFLandroidx/compose/foundation/BorderStroke;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "DropdownMenu-4kj-_NE", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/ScrollState;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenu-ILWXrKs", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItem", "text", "onClick", "leadingIcon", "trailingIcon", "enabled", "colors", "Landroidx/compose/material3/MenuItemColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/MenuItemColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "DefaultMenuProperties", "getDefaultMenuProperties", "()Landroidx/compose/ui/window/PopupProperties;", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidMenu_androidKt {
    private static final PopupProperties DefaultMenuProperties = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);

    static final Unit DropdownMenuItem$lambda$7(Function2 function2, Function0 function0, Modifier modifier, Function2 function3, Function2 function4, boolean z, MenuItemColors menuItemColors, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        DropdownMenuItem(function2, function0, modifier, function3, function4, z, menuItemColors, paddingValues, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DropdownMenu_4kj__NE$lambda$5(boolean z, Function0 function0, Modifier modifier, long j, ScrollState scrollState, PopupProperties popupProperties, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2067DropdownMenu4kj_NE(z, function0, modifier, j, scrollState, popupProperties, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DropdownMenu_ILWXrKs$lambda$6(boolean z, Function0 function0, Modifier modifier, long j, PopupProperties popupProperties, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2068DropdownMenuILWXrKs(z, function0, modifier, j, popupProperties, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DropdownMenu_IlH_yew$lambda$4(boolean z, Function0 function0, Modifier modifier, long j, ScrollState scrollState, PopupProperties popupProperties, Shape shape, long j2, float f, float f2, BorderStroke borderStroke, Function3 function3, int i, int i2, int i3, Composer composer, int i4) {
        m2069DropdownMenuIlH_yew(z, function0, modifier, j, scrollState, popupProperties, shape, j2, f, f2, borderStroke, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DropdownMenu-IlH_yew, reason: not valid java name */
    public static final void m2069DropdownMenuIlH_yew(final boolean expanded, final Function0<Unit> function0, Modifier modifier, long offset, ScrollState scrollState, PopupProperties properties, Shape shape, long containerColor, float tonalElevation, float shadowElevation, BorderStroke border, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function0<Unit> function1;
        Modifier modifier2;
        long j;
        int i2;
        final ScrollState scrollState2;
        PopupProperties properties2;
        Shape shape2;
        int $dirty;
        int $dirty1;
        Composer $composer2;
        final float tonalElevation2;
        final BorderStroke border2;
        final Shape shape3;
        final long offset2;
        final Modifier modifier3;
        final long containerColor2;
        final float shadowElevation2;
        final PopupProperties properties3;
        long offset3;
        Shape shape4;
        long containerColor3;
        final Modifier modifier4;
        final BorderStroke border3;
        final Shape shape5;
        final ScrollState scrollState3;
        final float tonalElevation3;
        final long containerColor4;
        final float shadowElevation3;
        int $dirty2;
        boolean z;
        int $dirty3;
        Composer $composer3 = $composer.startRestartGroup(1725609375);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)N(expanded,onDismissRequest,modifier,offset:c#ui.unit.DpOffset,scrollState,properties,shape,containerColor:c#ui.graphics.Color,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp,border,content)55@2074L42:AndroidMenu.android.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changed(expanded) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function1 = function0;
        } else if (($changed & 48) == 0) {
            function1 = function0;
            $dirty4 |= $composer3.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty4 |= 3072;
            j = offset;
            i2 = 32;
        } else if (($changed & 3072) == 0) {
            j = offset;
            i2 = 32;
            $dirty4 |= $composer3.changed(j) ? 2048 : 1024;
        } else {
            j = offset;
            i2 = 32;
        }
        int i5 = i2;
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                scrollState2 = scrollState;
                int i6 = $composer3.changed(scrollState2) ? 16384 : 8192;
                $dirty4 |= i6;
            } else {
                scrollState2 = scrollState;
            }
            $dirty4 |= i6;
        } else {
            scrollState2 = scrollState;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            properties2 = properties;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            properties2 = properties;
            $dirty4 |= $composer3.changed(properties2) ? 131072 : 65536;
        } else {
            properties2 = properties;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                shape2 = shape;
                int i8 = $composer3.changed(shape2) ? 1048576 : 524288;
                $dirty4 |= i8;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i8;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
                int i9 = $composer3.changed(containerColor) ? 8388608 : 4194304;
                $dirty = $dirty3 | i9;
            } else {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
            }
            $dirty = $dirty3 | i9;
        } else {
            $dirty = $dirty4;
            $dirty1 = $changed1;
        }
        int $dirty5 = $dirty1;
        int $dirty6 = i & 256;
        if ($dirty6 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(tonalElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty7 = i & 512;
        if ($dirty7 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changed(shadowElevation) ? 536870912 : 268435456;
        }
        int $dirty8 = i & 1024;
        if ($dirty8 != 0) {
            $dirty5 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty5 |= $composer3.changed(border) ? 4 : 2;
        }
        if ((i & 2048) != 0) {
            $dirty5 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty5 |= $composer3.changedInstance(function3) ? i5 : 16;
        }
        int $dirty9 = $dirty5;
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty9 & 19) == 18) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "178@6461L21,182@6573L5,184@6621L14");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                if (i4 != 0) {
                    float x$iv = Dp.m7905constructorimpl(0);
                    float y$iv = Dp.m7905constructorimpl(0);
                    long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                    long v1$iv$iv2 = Float.floatToRawIntBits(y$iv);
                    offset3 = DpOffset.m7961constructorimpl((v1$iv$iv << i5) | (v1$iv$iv2 & 4294967295L));
                } else {
                    offset3 = j;
                }
                if ((i & 16) != 0) {
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                    $dirty &= -57345;
                }
                if (i7 != 0) {
                    properties2 = DefaultMenuProperties;
                }
                if ((i & 64) != 0) {
                    shape4 = MenuDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty &= -3670017;
                } else {
                    shape4 = shape2;
                }
                if ((i & 128) != 0) {
                    containerColor3 = MenuDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty &= -29360129;
                } else {
                    containerColor3 = containerColor;
                }
                float tonalElevation4 = $dirty6 != 0 ? MenuDefaults.INSTANCE.m2595getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = $dirty7 != 0 ? MenuDefaults.INSTANCE.m2594getShadowElevationD9Ej5fM() : shadowElevation;
                if ($dirty8 != 0) {
                    modifier4 = modifier5;
                    shape5 = shape4;
                    scrollState3 = scrollState2;
                    tonalElevation3 = tonalElevation4;
                    containerColor4 = containerColor3;
                    shadowElevation3 = shadowElevation4;
                    border3 = null;
                    $dirty2 = $dirty;
                } else {
                    modifier4 = modifier5;
                    border3 = border;
                    shape5 = shape4;
                    scrollState3 = scrollState2;
                    tonalElevation3 = tonalElevation4;
                    containerColor4 = containerColor3;
                    shadowElevation3 = shadowElevation4;
                    $dirty2 = $dirty;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                containerColor4 = containerColor;
                tonalElevation3 = tonalElevation;
                shadowElevation3 = shadowElevation;
                border3 = border;
                scrollState3 = scrollState2;
                shape5 = shape2;
                offset3 = j;
                modifier4 = modifier2;
                $dirty2 = $dirty;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1725609375, $dirty2, $dirty9, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:54)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 453245993, "CC(remember):AndroidMenu.android.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                z = false;
                Object value$iv = new MutableTransitionState(false);
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            } else {
                z = false;
            }
            final MutableTransitionState expandedState = (MutableTransitionState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            expandedState.setTargetState$animation_core(Boolean.valueOf(expanded));
            if (((Boolean) expandedState.getCurrentState()).booleanValue() || ((Boolean) expandedState.getTargetState()).booleanValue()) {
                $composer3.startReplaceGroup(1165905588);
                ComposerKt.sourceInformation($composer3, "59@2261L51,60@2348L7,62@2404L251,72@2827L494,68@2665L656");
                ComposerKt.sourceInformationMarkerStart($composer3, 453251986, "CC(remember):AndroidMenu.android.kt#9igjgp");
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
                ComposerKt.sourceInformationMarkerStart($composer3, 453256762, "CC(remember):AndroidMenu.android.kt#9igjgp");
                if (($dirty2 & 7168) == 2048) {
                    z = true;
                }
                boolean invalid$iv = z | $composer3.changed(density);
                Object it$iv3 = $composer3.rememberedValue();
                if (invalid$iv || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new DropdownMenuPositionProvider(offset3, density, 0, new Function2() { // from class: androidx.compose.material3.AndroidMenu_androidKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidMenu_androidKt.DropdownMenu_IlH_yew$lambda$3$lambda$2(transformOriginState, (IntRect) obj, (IntRect) obj2);
                        }
                    }, 4, null);
                    $composer3.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                DropdownMenuPositionProvider popupPositionProvider = (DropdownMenuPositionProvider) it$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                AndroidPopup_androidKt.Popup(popupPositionProvider, function1, properties2, ComposableLambdaKt.rememberComposableLambda(-917492520, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AndroidMenu_androidKt$DropdownMenu$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C73@2841L470:AndroidMenu.android.kt#uh7d8r");
                        if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-917492520, $changed2, -1, "androidx.compose.material3.DropdownMenu.<anonymous> (AndroidMenu.android.kt:73)");
                        }
                        MenuKt.m2608DropdownMenuContentQj0Zi0g(modifier4, expandedState, transformOriginState, scrollState3, shape5, containerColor4, tonalElevation3, shadowElevation3, border3, function3, $composer4, (MutableTransitionState.$stable << 3) | 384);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54), $composer3, ($dirty2 & 112) | 3072 | (($dirty2 >> 9) & 896), 0);
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(1166965571);
                $composer3.endReplaceGroup();
                $composer2 = $composer3;
                offset3 = offset3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            offset2 = offset3;
            modifier3 = modifier4;
            scrollState2 = scrollState3;
            shape3 = shape5;
            containerColor2 = containerColor4;
            tonalElevation2 = tonalElevation3;
            shadowElevation2 = shadowElevation3;
            border2 = border3;
            properties3 = properties2;
        } else {
            $dirty9 = $dirty9;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            tonalElevation2 = tonalElevation;
            border2 = border;
            shape3 = shape2;
            offset2 = j;
            modifier3 = modifier2;
            containerColor2 = containerColor;
            shadowElevation2 = shadowElevation;
            properties3 = properties2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AndroidMenu_androidKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidMenu_androidKt.DropdownMenu_IlH_yew$lambda$4(expanded, function0, modifier3, offset2, scrollState2, properties3, shape3, containerColor2, tonalElevation2, shadowElevation2, border2, function3, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit DropdownMenu_IlH_yew$lambda$3$lambda$2(MutableState $transformOriginState, IntRect parentBounds, IntRect menuBounds) {
        $transformOriginState.setValue(TransformOrigin.m5497boximpl(MenuKt.calculateTransformOrigin(parentBounds, menuBounds)));
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with parameters for shape, color, elevation, and border.", replaceWith = @ReplaceWith(expression = "DropdownMenu(\n    expanded = expanded,\n    onDismissRequest = onDismissRequest,\n    modifier = modifier,\n    offset = offset,\n    scrollState = scrollState,\n    properties = properties,\n    shape = MenuDefaults.shape,\n    containerColor = MenuDefaults.containerColor,\n    tonalElevation = MenuDefaults.TonalElevation,\n    shadowElevation = MenuDefaults.ShadowElevation,\n    border = null,\n    content = content,\n)", imports = {}))
    /* JADX INFO: renamed from: DropdownMenu-4kj-_NE, reason: not valid java name */
    public static final /* synthetic */ void m2067DropdownMenu4kj_NE(final boolean expanded, final Function0 onDismissRequest, Modifier modifier, long offset, ScrollState scrollState, PopupProperties properties, final Function3 content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0 function0;
        Modifier modifier2;
        long offset2;
        ScrollState scrollState2;
        PopupProperties popupProperties;
        Composer $composer2;
        final Modifier modifier3;
        final long offset3;
        final ScrollState scrollState3;
        final PopupProperties properties2;
        int $dirty;
        ScrollState scrollState4;
        PopupProperties properties3;
        int i2;
        Modifier modifier4;
        long offset4;
        Composer $composer3 = $composer.startRestartGroup(1518067413);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)N(expanded,onDismissRequest,modifier,offset:c#ui.unit.DpOffset,scrollState,properties,content)130@4963L5,131@5008L14,123@4724L465:AndroidMenu.android.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            z = expanded;
        } else if (($changed & 6) == 0) {
            z = expanded;
            $dirty2 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function0 = onDismissRequest;
        } else if (($changed & 48) == 0) {
            function0 = onDismissRequest;
            $dirty2 |= $composer3.changedInstance(function0) ? 32 : 16;
        } else {
            function0 = onDismissRequest;
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
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            offset2 = offset;
        } else if (($changed & 3072) == 0) {
            offset2 = offset;
            $dirty2 |= $composer3.changed(offset2) ? 2048 : 1024;
        } else {
            offset2 = offset;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                scrollState2 = scrollState;
                int i5 = $composer3.changed(scrollState2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                scrollState2 = scrollState;
            }
            $dirty2 |= i5;
        } else {
            scrollState2 = scrollState;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            popupProperties = properties;
        } else if ((196608 & $changed) == 0) {
            popupProperties = properties;
            $dirty2 |= $composer3.changed(popupProperties) ? 131072 : 65536;
        } else {
            popupProperties = properties;
        }
        if ((i & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(content) ? 1048576 : 524288;
        }
        int $dirty3 = $dirty2;
        if ($composer3.shouldExecute(($dirty2 & 599187) != 599186, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "119@4575L21");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    float x$iv = Dp.m7905constructorimpl(0);
                    float y$iv = Dp.m7905constructorimpl(0);
                    long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                    long v1$iv$iv2 = Float.floatToRawIntBits(y$iv);
                    long v2$iv$iv = DpOffset.m7961constructorimpl((v1$iv$iv << 32) | (v1$iv$iv2 & 4294967295L));
                    offset2 = v2$iv$iv;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    scrollState2 = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
                } else {
                    $dirty = $dirty3;
                }
                if (i6 != 0) {
                    offset4 = offset2;
                    scrollState4 = scrollState2;
                    properties3 = new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null);
                    i2 = 1518067413;
                    modifier4 = modifier2;
                } else {
                    scrollState4 = scrollState2;
                    properties3 = popupProperties;
                    i2 = 1518067413;
                    modifier4 = modifier2;
                    offset4 = offset2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty = $dirty3 & (-57345);
                    scrollState4 = scrollState2;
                    properties3 = popupProperties;
                    i2 = 1518067413;
                    modifier4 = modifier2;
                    offset4 = offset2;
                } else {
                    scrollState4 = scrollState2;
                    properties3 = popupProperties;
                    $dirty = $dirty3;
                    i2 = 1518067413;
                    modifier4 = modifier2;
                    offset4 = offset2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:123)");
            }
            $composer2 = $composer3;
            m2069DropdownMenuIlH_yew(z, function0, modifier4, offset4, scrollState4, properties3, MenuDefaults.INSTANCE.getShape($composer3, 6), MenuDefaults.INSTANCE.getContainerColor($composer3, 6), MenuDefaults.INSTANCE.m2595getTonalElevationD9Ej5fM(), MenuDefaults.INSTANCE.m2594getShadowElevationD9Ej5fM(), null, content, $composer2, ($dirty & 14) | 905969664 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty), (($dirty >> 15) & 112) | 6, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            offset3 = offset4;
            scrollState3 = scrollState4;
            properties2 = properties3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            offset3 = offset2;
            scrollState3 = scrollState2;
            properties2 = popupProperties;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AndroidMenu_androidKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidMenu_androidKt.DropdownMenu_4kj__NE$lambda$5(expanded, onDismissRequest, modifier3, offset3, scrollState3, properties2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced by a DropdownMenu function with a ScrollState parameter", replaceWith = @ReplaceWith(expression = "DropdownMenu(expanded,onDismissRequest, modifier, offset, rememberScrollState(), properties, content)", imports = {"androidx.compose.foundation.rememberScrollState"}))
    /* JADX INFO: renamed from: DropdownMenu-ILWXrKs, reason: not valid java name */
    public static final /* synthetic */ void m2068DropdownMenuILWXrKs(final boolean expanded, final Function0 onDismissRequest, Modifier modifier, long offset, PopupProperties properties, final Function3 content, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0 function0;
        Modifier modifier2;
        Function3 function3;
        Composer $composer2;
        final PopupProperties properties2;
        final Modifier modifier3;
        final long offset2;
        long offset3;
        Composer $composer3 = $composer.startRestartGroup(1744198621);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenu)N(expanded,onDismissRequest,modifier,offset:c#ui.unit.DpOffset,properties,content)163@6066L21,158@5902L252:AndroidMenu.android.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            z = expanded;
        } else if (($changed & 6) == 0) {
            z = expanded;
            $dirty |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = expanded;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            function0 = onDismissRequest;
        } else if (($changed & 48) == 0) {
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
            $dirty |= $composer3.changed(offset) ? 2048 : 1024;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(properties) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function3 = content;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
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
                offset3 = v2$iv$iv;
            } else {
                offset3 = offset;
            }
            PopupProperties properties3 = i4 != 0 ? new PopupProperties(true, false, false, false, 14, (DefaultConstructorMarker) null) : properties;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1744198621, $dirty, -1, "androidx.compose.material3.DropdownMenu (AndroidMenu.android.kt:158)");
            }
            $composer2 = $composer3;
            m2069DropdownMenuIlH_yew(z, function0, modifier4, offset3, ScrollKt.rememberScrollState(0, $composer3, 0, 1), properties3, null, 0L, 0.0f, 0.0f, null, function3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752), ($dirty >> 12) & 112, 1984);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            offset2 = offset3;
            properties2 = properties3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            properties2 = properties;
            modifier3 = modifier2;
            offset2 = offset;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AndroidMenu_androidKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidMenu_androidKt.DropdownMenu_ILWXrKs$lambda$6(expanded, onDismissRequest, modifier3, offset2, properties2, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void DropdownMenuItem(final Function2<? super Composer, ? super Integer, Unit> function2, final Function0<Unit> function0, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, boolean enabled, MenuItemColors colors, PaddingValues contentPadding, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function0<Unit> function1;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        boolean enabled2;
        MenuItemColors colors2;
        Composer $composer2;
        final MutableInteractionSource interactionSource2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final boolean enabled3;
        final MenuItemColors colors3;
        final PaddingValues contentPadding2;
        int $dirty;
        MutableInteractionSource interactionSource3;
        PaddingValues contentPadding3;
        Function2<? super Composer, ? super Integer, Unit> function10;
        boolean enabled4;
        MenuItemColors colors4;
        int i2;
        Modifier modifier4;
        Function2<? super Composer, ? super Integer, Unit> function11;
        Composer $composer3 = $composer.startRestartGroup(-532959117);
        ComposerKt.sourceInformation($composer3, "C(DropdownMenuItem)N(text,onClick,modifier,leadingIcon,trailingIcon,enabled,colors,contentPadding,interactionSource)180@6512L319:AndroidMenu.android.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function5 = function2;
        } else if (($changed & 6) == 0) {
            function5 = function2;
            $dirty2 |= $composer3.changedInstance(function5) ? 4 : 2;
        } else {
            function5 = function2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function1 = function0;
        } else if (($changed & 48) == 0) {
            function1 = function0;
            $dirty2 |= $composer3.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
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
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            function6 = function3;
        } else if (($changed & 3072) == 0) {
            function6 = function3;
            $dirty2 |= $composer3.changedInstance(function6) ? 2048 : 1024;
        } else {
            function6 = function3;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            function7 = function4;
        } else if (($changed & 24576) == 0) {
            function7 = function4;
            $dirty2 |= $composer3.changedInstance(function7) ? 16384 : 8192;
        } else {
            function7 = function4;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if ((196608 & $changed) == 0) {
            enabled2 = enabled;
            $dirty2 |= $composer3.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i7 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty2 |= i7;
            } else {
                colors2 = colors;
            }
            $dirty2 |= i7;
        } else {
            colors2 = colors;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changed(contentPadding) ? 8388608 : 4194304;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty3 = $dirty2;
        if (!$composer3.shouldExecute(($dirty2 & 38347923) != 38347922, $dirty3 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            interactionSource2 = interactionSource;
            modifier3 = modifier2;
            function8 = function6;
            function9 = function7;
            enabled3 = enabled2;
            colors3 = colors2;
            contentPadding2 = contentPadding;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "194@8776L12");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty = $dirty3 & (-3670017);
                    contentPadding3 = contentPadding;
                    interactionSource3 = interactionSource;
                    function10 = function7;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    i2 = -532959117;
                    modifier4 = modifier2;
                    function11 = function6;
                } else {
                    contentPadding3 = contentPadding;
                    interactionSource3 = interactionSource;
                    $dirty = $dirty3;
                    function10 = function7;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    i2 = -532959117;
                    modifier4 = modifier2;
                    function11 = function6;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    function6 = null;
                }
                if (i5 != 0) {
                    function7 = null;
                }
                if (i6 != 0) {
                    enabled2 = true;
                }
                if ((i & 64) != 0) {
                    $dirty = $dirty3 & (-3670017);
                    colors2 = MenuDefaults.INSTANCE.itemColors($composer3, 6);
                } else {
                    $dirty = $dirty3;
                }
                PaddingValues contentPadding4 = i8 != 0 ? MenuDefaults.INSTANCE.getDropdownMenuItemContentPadding() : contentPadding;
                if (i9 != 0) {
                    contentPadding3 = contentPadding4;
                    interactionSource3 = null;
                    function10 = function7;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    i2 = -532959117;
                    modifier4 = modifier2;
                    function11 = function6;
                } else {
                    interactionSource3 = interactionSource;
                    contentPadding3 = contentPadding4;
                    function10 = function7;
                    enabled4 = enabled2;
                    colors4 = colors2;
                    i2 = -532959117;
                    modifier4 = modifier2;
                    function11 = function6;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.DropdownMenuItem (AndroidMenu.android.kt:179)");
            }
            $composer2 = $composer3;
            MenuKt.DropdownMenuItemContent(function5, function1, modifier4, function11, function10, enabled4, colors4, contentPadding3, interactionSource3, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            function8 = function11;
            function9 = function10;
            enabled3 = enabled4;
            colors3 = colors4;
            contentPadding2 = contentPadding3;
            interactionSource2 = interactionSource3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.AndroidMenu_androidKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidMenu_androidKt.DropdownMenuItem$lambda$7(function2, function0, modifier3, function8, function9, enabled3, colors3, contentPadding2, interactionSource2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final PopupProperties getDefaultMenuProperties() {
        return DefaultMenuProperties;
    }
}
