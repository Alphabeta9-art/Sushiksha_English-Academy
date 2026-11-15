package androidx.compose.material;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImplKt;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.window.core.layout.WindowSizeClass;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\u001aU\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¢\u0006\u0002\u0010\r\u001a?\u0010\u000e\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\t\u001a\u00020\u0013H\u0003¢\u0006\u0002\u0010\u0014\u001a+\u0010\u0015\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\"\u0016\u0010\u001d\u001a\u00020\u001eX\u0080\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 \"\u0016\u0010\"\u001a\u00020\u001eX\u0080\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b#\u0010 \"\u0016\u0010$\u001a\u00020\u001eX\u0080\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b%\u0010 \"\u0010\u0010&\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010!\"\u0010\u0010'\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010!\"\u0010\u0010(\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010!\"\u0010\u0010)\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010!\"\u0010\u0010*\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010!\"\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00120,X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010-\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010!\"\u0010\u0010.\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010!\"\u000e\u0010/\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u00100\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010!¨\u00061²\u0006\n\u00102\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\u0018\u00103\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005X\u008a\u0084\u0002²\u0006\n\u00104\u001a\u00020\u0003X\u008a\u0084\u0002²\u0006\n\u0010\u0017\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\n\u00105\u001a\u00020\u0018X\u008a\u0084\u0002²\u0006\n\u00106\u001a\u00020\u0018X\u008a\u0084\u0002"}, d2 = {"Switch", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "colors", "Landroidx/compose/material/SwitchColors;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/SwitchColors;Landroidx/compose/runtime/Composer;II)V", "SwitchImpl", "Landroidx/compose/foundation/layout/BoxScope;", "thumbValue", "Lkotlin/Function0;", "", "Landroidx/compose/foundation/interaction/InteractionSource;", "(Landroidx/compose/foundation/layout/BoxScope;ZZLandroidx/compose/material/SwitchColors;Lkotlin/jvm/functions/Function0;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)V", "drawTrack", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "trackColor", "Landroidx/compose/ui/graphics/Color;", "trackWidth", "strokeWidth", "drawTrack-RPmYEkk", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFF)V", "TrackWidth", "Landroidx/compose/ui/unit/Dp;", "getTrackWidth", "()F", "F", "TrackStrokeWidth", "getTrackStrokeWidth", "ThumbDiameter", "getThumbDiameter", "ThumbRippleRadius", "DefaultSwitchPadding", "SwitchWidth", "SwitchHeight", "ThumbPathLength", "AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "ThumbDefaultElevation", "ThumbPressedElevation", "SwitchPositionalThreshold", "SwitchVelocityThreshold", "material", "forceAnimationCheck", "currentOnCheckedChange", "currentChecked", "thumbColor", "resolvedThumbColor"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SwitchKt {
    private static final TweenSpec<Float> AnimationSpec;
    private static final float SwitchPositionalThreshold = 0.7f;
    private static final float SwitchVelocityThreshold;
    private static final float ThumbDefaultElevation;
    private static final float ThumbPathLength;
    private static final float ThumbPressedElevation;
    private static final float TrackWidth = Dp.m7905constructorimpl(34);
    private static final float TrackStrokeWidth = Dp.m7905constructorimpl(14);
    private static final float ThumbDiameter = Dp.m7905constructorimpl(20);
    private static final float ThumbRippleRadius = Dp.m7905constructorimpl(24);
    private static final float DefaultSwitchPadding = Dp.m7905constructorimpl(2);
    private static final float SwitchWidth = TrackWidth;
    private static final float SwitchHeight = ThumbDiameter;

    static final Unit Switch$lambda$12(boolean z, Function1 function1, Modifier modifier, boolean z2, MutableInteractionSource mutableInteractionSource, SwitchColors switchColors, int i, int i2, Composer composer, int i3) {
        Switch(z, function1, modifier, z2, mutableInteractionSource, switchColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SwitchImpl$lambda$7(BoxScope boxScope, boolean z, boolean z2, SwitchColors switchColors, Function0 function0, InteractionSource interactionSource, int i, Composer composer, int i2) {
        SwitchImpl(boxScope, z, z2, switchColors, function0, interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:165:0x0561  */
    public static final void Switch(final boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, boolean z2, MutableInteractionSource mutableInteractionSource, SwitchColors switchColors, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        boolean z3;
        MutableInteractionSource mutableInteractionSource2;
        SwitchColors switchColorsM1966colorsSQMK_m0;
        Composer composer2;
        final SwitchColors switchColors2;
        final Modifier modifier3;
        final MutableInteractionSource mutableInteractionSource3;
        final boolean z4;
        Modifier.Companion companion;
        boolean z5;
        MutableInteractionSource mutableInteractionSource4;
        int i3;
        boolean z6;
        MutableInteractionSource mutableInteractionSource5;
        boolean z7;
        MutableInteractionSource mutableInteractionSource6;
        boolean z8;
        int i4;
        Modifier.Companion companionM1096toggleableO2vRcR0;
        Modifier.Companion companionMinimumInteractiveComponentSize;
        Function0<ComposeUiNode> function0;
        int i5;
        boolean zChanged;
        Object objRememberedValue;
        Composer composerStartRestartGroup = composer.startRestartGroup(25866825);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Switch)N(checked,onCheckedChange,modifier,enabled,interactionSource,colors)103@4720L7,108@5102L34,109@5191L7,111@5276L524,124@5835L37,125@5899L29,126@5972L315,126@5933L354,135@6337L133,135@6292L178,140@6508L7,155@6950L1052:Switch.kt#jmzs0o");
        int i6 = i;
        if ((i & 6) == 0) {
            i6 |= composerStartRestartGroup.changed(z) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i6 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i7 = i2 & 4;
        if (i7 != 0) {
            i6 |= 384;
            modifier2 = modifier;
        } else if ((i & 384) == 0) {
            modifier2 = modifier;
            i6 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i8 = i2 & 8;
        if (i8 != 0) {
            i6 |= 3072;
            z3 = z2;
        } else if ((i & 3072) == 0) {
            z3 = z2;
            i6 |= composerStartRestartGroup.changed(z3) ? 2048 : 1024;
        } else {
            z3 = z2;
        }
        int i9 = i2 & 16;
        if (i9 != 0) {
            i6 |= 24576;
            mutableInteractionSource2 = mutableInteractionSource;
        } else if ((i & 24576) == 0) {
            mutableInteractionSource2 = mutableInteractionSource;
            i6 |= composerStartRestartGroup.changed(mutableInteractionSource2) ? 16384 : 8192;
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                switchColorsM1966colorsSQMK_m0 = switchColors;
                int i10 = composerStartRestartGroup.changed(switchColorsM1966colorsSQMK_m0) ? 131072 : 65536;
                i6 |= i10;
            } else {
                switchColorsM1966colorsSQMK_m0 = switchColors;
            }
            i6 |= i10;
        } else {
            switchColorsM1966colorsSQMK_m0 = switchColors;
        }
        if (composerStartRestartGroup.shouldExecute((74899 & i6) != 74898, i6 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "98@4526L8");
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 32) != 0) {
                    i6 &= -458753;
                }
                composer2 = composerStartRestartGroup;
                i3 = i6;
                mutableInteractionSource4 = mutableInteractionSource2;
                z6 = z3;
            } else {
                if (i7 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier2;
                }
                if (i8 == 0) {
                    z5 = z3;
                } else {
                    z5 = true;
                }
                if (i9 == 0) {
                    mutableInteractionSource4 = mutableInteractionSource2;
                } else {
                    mutableInteractionSource4 = null;
                }
                if ((i2 & 32) == 0) {
                    composer2 = composerStartRestartGroup;
                    switchColorsM1966colorsSQMK_m0 = switchColors;
                    i3 = i6;
                    modifier2 = companion;
                    z6 = z5;
                } else {
                    composer2 = composerStartRestartGroup;
                    modifier2 = companion;
                    switchColorsM1966colorsSQMK_m0 = SwitchDefaults.INSTANCE.m1966colorsSQMK_m0(0L, 0L, 0.0f, 0L, 0L, 0.0f, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, 6, 1023);
                    z6 = z5;
                    i3 = i6 & (-458753);
                }
            }
            composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(25866825, i3, -1, "androidx.compose.material.Switch (Switch.kt:99)");
            }
            if (mutableInteractionSource4 == null) {
                composer2.startReplaceGroup(1799771122);
                ComposerKt.sourceInformation(composer2, "101@4621L39");
                ComposerKt.sourceInformationMarkerStart(composer2, -911774192, "CC(remember):Switch.kt#9igjgp");
                Composer composer3 = composer2;
                Object objRememberedValue2 = composer3.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    MutableInteractionSource MutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                    composer3.updateRememberedValue(MutableInteractionSource);
                    objRememberedValue2 = MutableInteractionSource;
                }
                mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-911774843);
                composer2.endReplaceGroup();
                mutableInteractionSource5 = mutableInteractionSource4;
            }
            MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource5;
            final float f = 0.0f;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            Composer composer4 = composer2;
            ComposerKt.sourceInformationMarkerStart(composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composer4.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            final float fMo406toPx0680j_4 = ((Density) objConsume).mo406toPx0680j_4(ThumbPathLength);
            ComposerKt.sourceInformationMarkerStart(composer2, -911758805, "CC(remember):Switch.kt#9igjgp");
            Composer composer5 = composer2;
            Object objRememberedValue3 = composer5.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composer5.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue3 = mutableStateMutableStateOf$default;
            }
            MutableState mutableState = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
            Composer composer6 = composer2;
            ComposerKt.sourceInformationMarkerStart(composer6, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = composer6.consume(localDensity2);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            final float fMo406toPx0680j_5 = ((Density) objConsume2).mo406toPx0680j_4(SwitchVelocityThreshold);
            ComposerKt.sourceInformationMarkerStart(composer2, -911752747, "CC(remember):Switch.kt#9igjgp");
            boolean zChanged2 = composer2.changed(fMo406toPx0680j_4) | composer2.changed(fMo406toPx0680j_5);
            Composer composer7 = composer2;
            Object objRememberedValue4 = composer7.rememberedValue();
            if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                AnchoredDraggableState anchoredDraggableState = new AnchoredDraggableState(Boolean.valueOf(z), AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material.SwitchKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SwitchKt.Switch$lambda$6$0(f, fMo406toPx0680j_4, (DraggableAnchorsConfig) obj);
                    }
                }), new Function1() { // from class: androidx.compose.material.SwitchKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Float.valueOf(SwitchKt.Switch$lambda$6$1(((Float) obj).floatValue()));
                    }
                }, new Function0() { // from class: androidx.compose.material.SwitchKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Float.valueOf(SwitchKt.Switch$lambda$6$2(fMo406toPx0680j_5));
                    }
                }, AnimationSpec, null, 32, null);
                composer7.updateRememberedValue(anchoredDraggableState);
                objRememberedValue4 = anchoredDraggableState;
            }
            final AnchoredDraggableState anchoredDraggableState2 = (AnchoredDraggableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composer2);
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composer2, (i3 >> 3) & 14);
            State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(z), composer2, i3 & 14);
            ComposerKt.sourceInformationMarkerStart(composer2, -911730684, "CC(remember):Switch.kt#9igjgp");
            boolean zChanged3 = composer2.changed(anchoredDraggableState2) | composer2.changed(stateRememberUpdatedState2) | composer2.changed(stateRememberUpdatedState);
            Composer composer8 = composer2;
            Object objRememberedValue5 = composer8.rememberedValue();
            if (zChanged3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                SwitchKt$Switch$1$1 switchKt$Switch$1$1 = new SwitchKt$Switch$1$1(anchoredDraggableState2, stateRememberUpdatedState2, stateRememberUpdatedState, mutableState, null);
                composer8.updateRememberedValue(switchKt$Switch$1$1);
                objRememberedValue5 = switchKt$Switch$1$1;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            EffectsKt.LaunchedEffect(anchoredDraggableState2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, 0);
            Boolean boolValueOf = Boolean.valueOf(z);
            Boolean boolValueOf2 = Boolean.valueOf(Switch$lambda$3(mutableState));
            ComposerKt.sourceInformationMarkerStart(composer2, -911719186, "CC(remember):Switch.kt#9igjgp");
            boolean zChanged4 = ((i3 & 14) == 4) | composer2.changed(anchoredDraggableState2);
            Composer composer9 = composer2;
            SwitchKt$Switch$2$1 switchKt$Switch$2$1RememberedValue = composer9.rememberedValue();
            if (zChanged4 || switchKt$Switch$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                switchKt$Switch$2$1RememberedValue = new SwitchKt$Switch$2$1(z, anchoredDraggableState2, null);
                composer9.updateRememberedValue(switchKt$Switch$2$1RememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            EffectsKt.LaunchedEffect(boolValueOf, boolValueOf2, (Function2) switchKt$Switch$2$1RememberedValue, composer2, i3 & 14);
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            Composer composer10 = composer2;
            ComposerKt.sourceInformationMarkerStart(composer10, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = composer10.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composer10);
            boolean z9 = objConsume3 == LayoutDirection.Rtl;
            if (function1 != null) {
                z7 = z6;
                mutableInteractionSource6 = mutableInteractionSource7;
                z8 = false;
                i4 = 2;
                companionM1096toggleableO2vRcR0 = ToggleableKt.m1096toggleableO2vRcR0(Modifier.INSTANCE, z, mutableInteractionSource6, null, z7, Role.m7092boximpl(Role.INSTANCE.m7105getSwitcho7Vup1c()), function1);
            } else {
                z7 = z6;
                mutableInteractionSource6 = mutableInteractionSource7;
                z8 = false;
                i4 = 2;
                companionM1096toggleableO2vRcR0 = Modifier.INSTANCE;
            }
            if (function1 != null) {
                companionMinimumInteractiveComponentSize = InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE);
            } else {
                companionMinimumInteractiveComponentSize = Modifier.INSTANCE;
            }
            MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource6;
            Modifier modifierM859requiredSizeVpY3zN4 = SizeKt.m859requiredSizeVpY3zN4(PaddingKt.m819padding3ABfNKs(SizeKt.wrapContentSize$default(AnchoredDraggableKt.anchoredDraggable(modifier2.then(companionMinimumInteractiveComponentSize).then(companionM1096toggleableO2vRcR0), 
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x03cb: INVOKE (r2v14 'modifierM859requiredSizeVpY3zN4' androidx.compose.ui.Modifier) = 
                  (wrap androidx.compose.ui.Modifier:0x03c3: INVOKE 
                  (wrap androidx.compose.ui.Modifier:0x03bd: INVOKE 
                  (wrap androidx.compose.ui.Modifier:0x03b0: INVOKE 
                  (wrap androidx.compose.ui.Modifier:0x0394: INVOKE 
                  (wrap androidx.compose.ui.Modifier:0x0390: INVOKE 
                  (r9v8 'modifier2' androidx.compose.ui.Modifier A[D('modifier' androidx.compose.ui.Modifier)])
                  (r1v4 'companionMinimumInteractiveComponentSize' androidx.compose.ui.Modifier)
                 INTERFACE call: androidx.compose.ui.Modifier.then(androidx.compose.ui.Modifier):androidx.compose.ui.Modifier A[MD:(androidx.compose.ui.Modifier):androidx.compose.ui.Modifier (m), WRAPPED] (LINE:158))
                  (r0v18 'companionM1096toggleableO2vRcR0' androidx.compose.ui.Modifier A[D('toggleableModifier' androidx.compose.ui.Modifier)])
                 INTERFACE call: androidx.compose.ui.Modifier.then(androidx.compose.ui.Modifier):androidx.compose.ui.Modifier A[MD:(androidx.compose.ui.Modifier):androidx.compose.ui.Modifier (m), WRAPPED] (LINE:165))
                  (r3v18 ?? I:??[OBJECT, ARRAY])
                  (wrap androidx.compose.foundation.gestures.Orientation:0x0399: SGET  A[WRAPPED] (LINE:168) androidx.compose.foundation.gestures.Orientation.Horizontal androidx.compose.foundation.gestures.Orientation)
                  (wrap boolean:?: TERNARY null = ((!((r4v30 'z7' boolean A[D('enabled' boolean)]) == true) || (r46v0 'function1' kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> A[D('onCheckedChange' kotlin.jvm.functions.Function1)]) == (null kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit>))) ? (r7v15 'z8' boolean) : true)
                  (r19v2 'z9' boolean A[D('isRtl' boolean)])
                  (r20v2 'mutableInteractionSource8' androidx.compose.foundation.interaction.MutableInteractionSource A[D('interactionSource' androidx.compose.foundation.interaction.MutableInteractionSource)])
                  false
                 STATIC call: androidx.compose.material.AnchoredDraggableKt.anchoredDraggable(androidx.compose.ui.Modifier, androidx.compose.material.AnchoredDraggableState, androidx.compose.foundation.gestures.Orientation, boolean, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, boolean):androidx.compose.ui.Modifier A[MD:<T>:(androidx.compose.ui.Modifier, androidx.compose.material.AnchoredDraggableState<T>, androidx.compose.foundation.gestures.Orientation, boolean, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, boolean):androidx.compose.ui.Modifier (m), WRAPPED] (LINE:166))
                  (wrap androidx.compose.ui.Alignment:0x03b8: INVOKE 
                  (wrap androidx.compose.ui.Alignment$Companion:0x03b6: SGET  A[WRAPPED] androidx.compose.ui.Alignment.Companion androidx.compose.ui.Alignment$Companion)
                 VIRTUAL call: androidx.compose.ui.Alignment.Companion.getCenter():androidx.compose.ui.Alignment A[MD:():androidx.compose.ui.Alignment (m), WRAPPED])
                  (r7v15 'z8' boolean)
                  (r13v9 'i4' int)
                  (null java.lang.Object)
                 STATIC call: androidx.compose.foundation.layout.SizeKt.wrapContentSize$default(androidx.compose.ui.Modifier, androidx.compose.ui.Alignment, boolean, int, java.lang.Object):androidx.compose.ui.Modifier A[MD:(androidx.compose.ui.Modifier, androidx.compose.ui.Alignment, boolean, int, java.lang.Object):androidx.compose.ui.Modifier (m), WRAPPED] (LINE:166))
                  (wrap float:0x03c1: SGET  A[WRAPPED] (LINE:175) androidx.compose.material.SwitchKt.DefaultSwitchPadding float)
                 STATIC call: androidx.compose.foundation.layout.PaddingKt.padding-3ABfNKs(androidx.compose.ui.Modifier, float):androidx.compose.ui.Modifier A[MD:(androidx.compose.ui.Modifier, float):androidx.compose.ui.Modifier (m), WRAPPED] (LINE:175))
                  (wrap float:0x03c7: SGET  A[WRAPPED] (LINE:176) androidx.compose.material.SwitchKt.SwitchWidth float)
                  (wrap float:0x03c9: SGET  A[WRAPPED] androidx.compose.material.SwitchKt.SwitchHeight float)
                 STATIC call: androidx.compose.foundation.layout.SizeKt.requiredSize-VpY3zN4(androidx.compose.ui.Modifier, float, float):androidx.compose.ui.Modifier A[DECLARE_VAR, MD:(androidx.compose.ui.Modifier, float, float):androidx.compose.ui.Modifier (m)] (LINE:176) in method: androidx.compose.material.SwitchKt.Switch(boolean, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit>, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.SwitchColors, androidx.compose.runtime.Composer, int, int):void, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r3v18 ??
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 1420
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.SwitchKt.Switch(boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.SwitchColors, androidx.compose.runtime.Composer, int, int):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean Switch$lambda$3(MutableState<Boolean> mutableState) {
            MutableState<Boolean> $this$getValue$iv = mutableState;
            return $this$getValue$iv.getValue().booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void Switch$lambda$4(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit Switch$lambda$6$0(float $minBound, float $maxBound, DraggableAnchorsConfig $this$DraggableAnchors) {
            $this$DraggableAnchors.at(false, $minBound);
            $this$DraggableAnchors.at(true, $maxBound);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float Switch$lambda$6$1(float distance) {
            return 0.7f * distance;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final float Switch$lambda$6$2(float $switchVelocityThresholdPx) {
            return $switchVelocityThresholdPx;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Function1<Boolean, Unit> Switch$lambda$7(State<? extends Function1<? super Boolean, Unit>> state) {
            Object thisObj$iv = state.getValue();
            return (Function1) thisObj$iv;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean Switch$lambda$8(State<Boolean> state) {
            Object thisObj$iv = state.getValue();
            return ((Boolean) thisObj$iv).booleanValue();
        }

        private static final void SwitchImpl(final BoxScope $this$SwitchImpl, final boolean checked, final boolean enabled, final SwitchColors colors, final Function0<Float> function0, final InteractionSource interactionSource, Composer $composer, final int $changed) {
            float f;
            String str;
            boolean z;
            long jSwitchImpl$lambda$4;
            Composer $composer2 = $composer.startRestartGroup(70908914);
            ComposerKt.sourceInformation($composer2, "C(SwitchImpl)N(checked,enabled,colors,thumbValue,interactionSource)220@9074L46,222@9160L614,222@9126L648,242@10001L28,243@10089L81,243@10034L136,246@10200L28,247@10278L7,248@10337L7,251@10460L6,250@10395L252,259@10726L43,257@10652L455:Switch.kt#jmzs0o");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer2.changed($this$SwitchImpl) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer2.changed(checked) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                $dirty |= $composer2.changed(enabled) ? 256 : 128;
            }
            if (($changed & 3072) == 0) {
                $dirty |= $composer2.changed(colors) ? 2048 : 1024;
            }
            if (($changed & 24576) == 0) {
                $dirty |= $composer2.changedInstance(function0) ? 16384 : 8192;
            }
            if ((196608 & $changed) == 0) {
                $dirty |= $composer2.changed(interactionSource) ? 131072 : 65536;
            }
            int $dirty2 = $dirty;
            if ($composer2.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(70908914, $dirty2, -1, "androidx.compose.material.SwitchImpl (Switch.kt:219)");
                }
                ComposerKt.sourceInformationMarkerStart($composer2, -576004160, "CC(remember):Switch.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = SnapshotStateKt.mutableStateListOf();
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                SnapshotStateList interactions = (SnapshotStateList) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -576000840, "CC(remember):Switch.kt#9igjgp");
                boolean invalid$iv = (458752 & $dirty2) == 131072;
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = (Function2) new SwitchKt$SwitchImpl$1$1(interactionSource, interactions, null);
                    $composer2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer2, ($dirty2 >> 15) & 14);
                boolean hasInteraction = !interactions.isEmpty();
                if (hasInteraction) {
                    f = ThumbPressedElevation;
                } else {
                    f = ThumbDefaultElevation;
                }
                float elevation = f;
                final State<Color> stateTrackColor = colors.trackColor(enabled, checked, $composer2, (($dirty2 >> 6) & 14) | ($dirty2 & 112) | (($dirty2 >> 3) & 896));
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default($this$SwitchImpl.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, -575971645, "CC(remember):Switch.kt#9igjgp");
                boolean invalid$iv2 = $composer2.changed(stateTrackColor);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv3 = new Function1() { // from class: androidx.compose.material.SwitchKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SwitchKt.SwitchImpl$lambda$3$0(stateTrackColor, (DrawScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                    it$iv3 = value$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(modifierFillMaxSize$default, (Function1) it$iv3, $composer2, 0);
                State<Color> stateThumbColor = colors.thumbColor(enabled, checked, $composer2, (($dirty2 >> 6) & 14) | ($dirty2 & 112) | (($dirty2 >> 3) & 896));
                ProvidableCompositionLocal<ElevationOverlay> localElevationOverlay = ElevationOverlayKt.getLocalElevationOverlay();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localElevationOverlay);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ElevationOverlay elevationOverlay = (ElevationOverlay) objConsume;
                ProvidableCompositionLocal<Dp> localAbsoluteElevation = ElevationOverlayKt.getLocalAbsoluteElevation();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume2 = $composer2.consume(localAbsoluteElevation);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                float arg0$iv = ((Dp) objConsume2).m7919unboximpl();
                float absoluteElevation = Dp.m7905constructorimpl(arg0$iv + elevation);
                if (Color.m5098equalsimpl0(SwitchImpl$lambda$4(stateThumbColor), MaterialTheme.INSTANCE.getColors($composer2, 6).m1769getSurface0d7_KjU()) && elevationOverlay != null) {
                    $composer2.startReplaceGroup(-674840005);
                    ComposerKt.sourceInformation($composer2, "252@10539L36");
                    str = "CC(remember):Switch.kt#9igjgp";
                    z = true;
                    long jMo1796apply7g2Lkgo = elevationOverlay.mo1796apply7g2Lkgo(SwitchImpl$lambda$4(stateThumbColor), absoluteElevation, $composer2, 0);
                    $composer2.endReplaceGroup();
                    jSwitchImpl$lambda$4 = jMo1796apply7g2Lkgo;
                } else {
                    str = "CC(remember):Switch.kt#9igjgp";
                    z = true;
                    $composer2.startReplaceGroup(-674751066);
                    $composer2.endReplaceGroup();
                    jSwitchImpl$lambda$4 = SwitchImpl$lambda$4(stateThumbColor);
                }
                State<Color> stateM135animateColorAsStateeuL9pac = SingleValueAnimationKt.m135animateColorAsStateeuL9pac(jSwitchImpl$lambda$4, null, null, null, $composer2, 0, 14);
                $composer2 = $composer2;
                Modifier modifierAlign = $this$SwitchImpl.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart());
                ComposerKt.sourceInformationMarkerStart($composer2, -575951299, str);
                boolean invalid$iv3 = ($dirty2 & 57344) == 16384 ? z : false;
                Object it$iv4 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material.SwitchKt$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SwitchKt.SwitchImpl$lambda$6$0(function0, (Density) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv4);
                    it$iv4 = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(BackgroundKt.m264backgroundbw27NRU(ShadowKt.m4695shadows4CzXII$default(SizeKt.m857requiredSize3ABfNKs(IndicationKt.indication(OffsetKt.offset(modifierAlign, (Function1) it$iv4), interactionSource, RippleKt.m1907rippleH2RKhps$default(false, ThumbRippleRadius, 0L, 4, null)), ThumbDiameter), elevation, RoundedCornerShapeKt.getCircleShape(), false, 0L, 0L, 24, null), SwitchImpl$lambda$5(stateM135animateColorAsStateeuL9pac), RoundedCornerShapeKt.getCircleShape()), $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SwitchKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return SwitchKt.SwitchImpl$lambda$7($this$SwitchImpl, checked, enabled, colors, function0, interactionSource, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        private static final long SwitchImpl$lambda$2(State<Color> state) {
            Object thisObj$iv = state.getValue();
            return ((Color) thisObj$iv).m5107unboximpl();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit SwitchImpl$lambda$3$0(State $trackColor$delegate, DrawScope $this$Canvas) {
            m1968drawTrackRPmYEkk($this$Canvas, SwitchImpl$lambda$2($trackColor$delegate), $this$Canvas.mo406toPx0680j_4(TrackWidth), $this$Canvas.mo406toPx0680j_4(TrackStrokeWidth));
            return Unit.INSTANCE;
        }

        private static final long SwitchImpl$lambda$4(State<Color> state) {
            Object thisObj$iv = state.getValue();
            return ((Color) thisObj$iv).m5107unboximpl();
        }

        private static final long SwitchImpl$lambda$5(State<Color> state) {
            Object thisObj$iv = state.getValue();
            return ((Color) thisObj$iv).m5107unboximpl();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final IntOffset SwitchImpl$lambda$6$0(Function0 $thumbValue, Density $this$offset) {
            int x$iv = MathKt.roundToInt(((Number) $thumbValue.invoke()).floatValue());
            return IntOffset.m8024boximpl(IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) 0) & 4294967295L)));
        }

        /* JADX INFO: renamed from: drawTrack-RPmYEkk, reason: not valid java name */
        private static final void m1968drawTrackRPmYEkk(DrawScope $this$drawTrack_u2dRPmYEkk, long j, float trackWidth, float strokeWidth) {
            float strokeRadius = strokeWidth / 2.0f;
            long arg0$iv = $this$drawTrack_u2dRPmYEkk.mo5670getCenterF1C5BW0();
            int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
            float y$iv = Float.intBitsToFloat(bits$iv$iv$iv);
            long v1$iv$iv = Float.floatToRawIntBits(strokeRadius);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            long jM4844constructorimpl = Offset.m4844constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
            float x$iv = trackWidth - strokeRadius;
            long arg0$iv2 = $this$drawTrack_u2dRPmYEkk.mo5670getCenterF1C5BW0();
            int bits$iv$iv$iv2 = (int) (arg0$iv2 & 4294967295L);
            float y$iv2 = Float.intBitsToFloat(bits$iv$iv$iv2);
            long v1$iv$iv2 = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
            DrawScope.m5657drawLineNGM6Ib0$default($this$drawTrack_u2dRPmYEkk, j, jM4844constructorimpl, Offset.m4844constructorimpl((v1$iv$iv2 << 32) | (4294967295L & v2$iv$iv2)), strokeWidth, StrokeCap.INSTANCE.m5472getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
        }

        static {
            float arg0$iv = TrackWidth;
            float other$iv = ThumbDiameter;
            ThumbPathLength = Dp.m7905constructorimpl(arg0$iv - other$iv);
            AnimationSpec = new TweenSpec<>(100, 0, null, 6, null);
            ThumbDefaultElevation = Dp.m7905constructorimpl(1);
            ThumbPressedElevation = Dp.m7905constructorimpl(6);
            SwitchVelocityThreshold = Dp.m7905constructorimpl(ComposerImplKt.nodeKey);
        }

        public static final float getTrackWidth() {
            return TrackWidth;
        }

        public static final float getTrackStrokeWidth() {
            return TrackStrokeWidth;
        }

        public static final float getThumbDiameter() {
            return ThumbDiameter;
        }
    }
