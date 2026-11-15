package androidx.compose.material3.internal;

import android.view.View;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.DialogProperties;
import androidx.compose.ui.window.SecureFlagPolicy;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: BasicEdgeToEdgeDialog.android.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\\\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000eH\u0001¢\u0006\u0002\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\t*\u00020\u00112\u0006\u0010\u0012\u001a\u00020\tH\u0000¨\u0006\u0013²\u0006\u001b\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000eX\u008a\u0084\u0002²\u0006\u0010\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u008a\u0084\u0002²\u0006\n\u0010\u0016\u001a\u00020\tX\u008a\u0084\u0002"}, d2 = {"BasicEdgeToEdgeDialog", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "lightStatusBars", "", "lightNavigationBars", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/material3/internal/PredictiveBackState;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/window/DialogProperties;ZZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "shouldApplySecureFlag", "Landroidx/compose/ui/window/SecureFlagPolicy;", "isSecureFlagSetOnParent", "material3", "currentContent", "currentOnDismissRequest", "currentDismissOnBackPress"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BasicEdgeToEdgeDialog_androidKt {

    /* JADX INFO: compiled from: BasicEdgeToEdgeDialog.android.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SecureFlagPolicy.values().length];
            try {
                iArr[SecureFlagPolicy.SecureOff.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SecureFlagPolicy.SecureOn.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SecureFlagPolicy.Inherit.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit BasicEdgeToEdgeDialog$lambda$12(Function0 function0, Modifier modifier, DialogProperties dialogProperties, boolean z, boolean z2, Function3 function3, int i, int i2, Composer composer, int i3) {
        BasicEdgeToEdgeDialog(function0, modifier, dialogProperties, z, z2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:127:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:128:0x02fd  */
    /* JADX WARN: Code duplicated, block: B:131:0x0307  */
    /* JADX WARN: Code duplicated, block: B:132:0x0309  */
    /* JADX WARN: Code duplicated, block: B:154:0x0367  */
    /* JADX WARN: Code duplicated, block: B:157:0x038d  */
    public static final void BasicEdgeToEdgeDialog(final Function0<Unit> function0, Modifier modifier, DialogProperties properties, boolean lightStatusBars, boolean lightNavigationBars, final Function3<? super PredictiveBackState, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        DialogProperties dialogProperties;
        final boolean lightStatusBars2;
        final boolean lightNavigationBars2;
        final Modifier modifier3;
        final DialogProperties properties2;
        final boolean lightStatusBars3;
        final boolean lightStatusBars4;
        Modifier.Companion modifier4;
        DialogProperties properties3;
        Modifier modifier5;
        int $dirty;
        DialogProperties properties4;
        Object value$iv;
        View view;
        View view2;
        Object value$iv2;
        final DialogWrapper dialog;
        boolean invalid$iv;
        Object it$iv;
        boolean z;
        boolean z2;
        boolean invalid$iv2;
        Object value$iv3;
        DialogProperties properties5;
        Composer $composer2 = $composer.startRestartGroup(814581409);
        ComposerKt.sourceInformation($composer2, "C(BasicEdgeToEdgeDialog)N(onDismissRequest,modifier,properties,lightStatusBars,lightNavigationBars,content)91@3820L7,92@3859L7,93@3914L7,94@3944L28,95@4009L21,95@3992L38,97@4058L29,98@4123L38,99@4199L51,102@4277L1031,128@5339L129,128@5314L154,137@5485L285,137@5474L296:BasicEdgeToEdgeDialog.android.kt#mqatfk");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            dialogProperties = properties;
        } else if (($changed & 384) == 0) {
            dialogProperties = properties;
            $dirty2 |= $composer2.changed(dialogProperties) ? 256 : 128;
        } else {
            dialogProperties = properties;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                lightStatusBars2 = lightStatusBars;
                int i4 = $composer2.changed(lightStatusBars2) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                lightStatusBars2 = lightStatusBars;
            }
            $dirty2 |= i4;
        } else {
            lightStatusBars2 = lightStatusBars;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                lightNavigationBars2 = lightNavigationBars;
                int i5 = $composer2.changed(lightNavigationBars2) ? 16384 : 8192;
                $dirty2 |= i5;
            } else {
                lightNavigationBars2 = lightNavigationBars;
            }
            $dirty2 |= i5;
        } else {
            lightNavigationBars2 = lightNavigationBars;
        }
        if ((i & 32) != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        if ($composer2.shouldExecute(($dirty2 & 74899) != 74898, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "37@1426L7,39@1507L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                $dirty = $dirty2;
                modifier5 = modifier2;
                properties4 = dialogProperties;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i3 == 0) {
                    properties3 = dialogProperties;
                } else {
                    properties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                }
                if ((i & 8) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty2 &= -7169;
                    lightStatusBars2 = ColorKt.m5149luminance8_81llA(((Color) objConsume).m5107unboximpl()) < 0.5f;
                }
                if ((i & 16) == 0) {
                    modifier5 = modifier4;
                    $dirty = $dirty2;
                    properties4 = properties3;
                } else {
                    ProvidableCompositionLocal<Color> localContentColor2 = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume2 = $composer2.consume(localContentColor2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean lightNavigationBars3 = ColorKt.m5149luminance8_81llA(((Color) objConsume2).m5107unboximpl()) < 0.5f;
                    modifier5 = modifier4;
                    lightNavigationBars2 = lightNavigationBars3;
                    $dirty = $dirty2 & (-57345);
                    properties4 = properties3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(814581409, $dirty, -1, "androidx.compose.material3.internal.BasicEdgeToEdgeDialog (BasicEdgeToEdgeDialog.android.kt:90)");
            }
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer2.consume(localView);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            View view3 = (View) objConsume3;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume4 = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density = (Density) objConsume4;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume5 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final LayoutDirection layoutDirection = (LayoutDirection) objConsume5;
            CompositionContext composition = ComposablesKt.rememberCompositionContext($composer2, 0);
            Object[] objArr = new Object[0];
            ComposerKt.sourceInformationMarkerStart($composer2, 1128701718, "CC(remember):BasicEdgeToEdgeDialog.android.kt#9igjgp");
            final DialogProperties properties6 = properties4;
            Object it$iv2 = $composer2.rememberedValue();
            int $dirty3 = $dirty;
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UUID.randomUUID();
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            UUID dialogId = (UUID) RememberSaveableKt.rememberSaveable(objArr, (Function0) value$iv, $composer2, 48);
            State currentContent$delegate = SnapshotStateKt.rememberUpdatedState(function3, $composer2, ($dirty3 >> 15) & 14);
            State currentOnDismissRequest$delegate = SnapshotStateKt.rememberUpdatedState(function0, $composer2, $dirty3 & 14);
            State currentDismissOnBackPress$delegate = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(properties6.getDismissOnBackPress()), $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 1128711304, "CC(remember):BasicEdgeToEdgeDialog.android.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changed(view3) | $composer2.changed(density);
            Object it$iv3 = $composer2.rememberedValue();
            if (!invalid$iv3) {
                view = view3;
                if (it$iv3 != Composer.INSTANCE.getEmpty()) {
                    value$iv2 = it$iv3;
                    view2 = view;
                }
                dialog = (DialogWrapper) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2 = $composer2;
                ComposerKt.sourceInformationMarkerStart($composer2, 1128744386, "CC(remember):BasicEdgeToEdgeDialog.android.kt#9igjgp");
                invalid$iv = $composer2.changedInstance(dialog);
                it$iv = $composer2.rememberedValue();
                if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BasicEdgeToEdgeDialog_androidKt.BasicEdgeToEdgeDialog$lambda$9$lambda$8(dialog, (DisposableEffectScope) obj);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv4);
                    it$iv = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.DisposableEffect(dialog, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv, $composer2, 0);
                ComposerKt.sourceInformationMarkerStart($composer2, 1128749214, "CC(remember):BasicEdgeToEdgeDialog.android.kt#9igjgp");
                boolean zChangedInstance = $composer2.changedInstance(dialog);
                if (($dirty3 & 14) == 4) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z3 = zChangedInstance | z;
                if (($dirty3 & 896) == 256) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                invalid$iv2 = z3 | z2 | $composer2.changed(layoutDirection.ordinal()) | (((($dirty3 & 7168) ^ 3072) <= 2048 && $composer2.changed(lightStatusBars2)) || ($dirty3 & 3072) == 2048) | (((($dirty3 & 57344) ^ 24576) <= 16384 && $composer2.changed(lightNavigationBars2)) || ($dirty3 & 24576) == 16384);
                Object it$iv4 = $composer2.rememberedValue();
                if (!invalid$iv2 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new Function0() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BasicEdgeToEdgeDialog_androidKt.BasicEdgeToEdgeDialog$lambda$11$lambda$10(dialog, function0, properties6, layoutDirection, lightStatusBars2, lightNavigationBars2);
                        }
                    };
                    properties5 = properties6;
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv4;
                    properties5 = properties6;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.SideEffect((Function0) value$iv3, $composer2, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                properties2 = properties5;
                lightStatusBars3 = lightStatusBars2;
                lightStatusBars4 = lightNavigationBars2;
                modifier3 = modifier5;
            } else {
                view = view3;
            }
            View view4 = view;
            DialogWrapper $this$BasicEdgeToEdgeDialog_u24lambda_u246_u24lambda_u245 = new DialogWrapper(function0, properties6, view4, layoutDirection, density, dialogId, lightStatusBars2, lightNavigationBars2);
            view2 = view4;
            $this$BasicEdgeToEdgeDialog_u24lambda_u246_u24lambda_u245.setContent(composition, ComposableLambdaKt.composableLambdaInstance(-635938462, true, new BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$dialog$1$1$1(modifier5, currentDismissOnBackPress$delegate, currentOnDismissRequest$delegate, currentContent$delegate)));
            value$iv2 = $this$BasicEdgeToEdgeDialog_u24lambda_u246_u24lambda_u245;
            $composer2.updateRememberedValue(value$iv2);
            dialog = (DialogWrapper) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2 = $composer2;
            ComposerKt.sourceInformationMarkerStart($composer2, 1128744386, "CC(remember):BasicEdgeToEdgeDialog.android.kt#9igjgp");
            invalid$iv = $composer2.changedInstance(dialog);
            it$iv = $composer2.rememberedValue();
            if (!invalid$iv) {
            }
            Object value$iv5 = new Function1() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return BasicEdgeToEdgeDialog_androidKt.BasicEdgeToEdgeDialog$lambda$9$lambda$8(dialog, (DisposableEffectScope) obj);
                }
            };
            $composer2.updateRememberedValue(value$iv5);
            it$iv = value$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.DisposableEffect(dialog, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) it$iv, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 1128749214, "CC(remember):BasicEdgeToEdgeDialog.android.kt#9igjgp");
            boolean zChangedInstance2 = $composer2.changedInstance(dialog);
            if (($dirty3 & 14) == 4) {
                z = true;
            } else {
                z = false;
            }
            boolean z4 = zChangedInstance2 | z;
            if (($dirty3 & 896) == 256) {
                z2 = true;
            } else {
                z2 = false;
            }
            invalid$iv2 = z4 | z2 | $composer2.changed(layoutDirection.ordinal()) | (((($dirty3 & 7168) ^ 3072) <= 2048 && $composer2.changed(lightStatusBars2)) || ($dirty3 & 3072) == 2048) | (((($dirty3 & 57344) ^ 24576) <= 16384 && $composer2.changed(lightNavigationBars2)) || ($dirty3 & 24576) == 16384);
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv2) {
                value$iv3 = new Function0() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BasicEdgeToEdgeDialog_androidKt.BasicEdgeToEdgeDialog$lambda$11$lambda$10(dialog, function0, properties6, layoutDirection, lightStatusBars2, lightNavigationBars2);
                    }
                };
                properties5 = properties6;
                $composer2.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = new Function0() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BasicEdgeToEdgeDialog_androidKt.BasicEdgeToEdgeDialog$lambda$11$lambda$10(dialog, function0, properties6, layoutDirection, lightStatusBars2, lightNavigationBars2);
                    }
                };
                properties5 = properties6;
                $composer2.updateRememberedValue(value$iv3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.SideEffect((Function0) value$iv3, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            properties2 = properties5;
            lightStatusBars3 = lightStatusBars2;
            lightStatusBars4 = lightNavigationBars2;
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            properties2 = dialogProperties;
            lightStatusBars3 = lightStatusBars2;
            lightStatusBars4 = lightNavigationBars2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BasicEdgeToEdgeDialog_androidKt.BasicEdgeToEdgeDialog$lambda$12(function0, modifier3, properties2, lightStatusBars3, lightStatusBars4, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function3<PredictiveBackState, Composer, Integer, Unit> BasicEdgeToEdgeDialog$lambda$2(State<? extends Function3<? super PredictiveBackState, ? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function3) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function0<Unit> BasicEdgeToEdgeDialog$lambda$3(State<? extends Function0<Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function0) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean BasicEdgeToEdgeDialog$lambda$4(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    static final DisposableEffectResult BasicEdgeToEdgeDialog$lambda$9$lambda$8(final DialogWrapper $dialog, DisposableEffectScope $this$DisposableEffect) {
        $dialog.show();
        return new DisposableEffectResult() { // from class: androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt$BasicEdgeToEdgeDialog$lambda$9$lambda$8$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $dialog.dismiss();
                $dialog.disposeComposition();
            }
        };
    }

    static final Unit BasicEdgeToEdgeDialog$lambda$11$lambda$10(DialogWrapper $dialog, Function0 $onDismissRequest, DialogProperties $properties, LayoutDirection $layoutDirection, boolean $lightStatusBars, boolean $lightNavigationBars) {
        $dialog.updateParameters($onDismissRequest, $properties, $layoutDirection, $lightStatusBars, $lightNavigationBars);
        return Unit.INSTANCE;
    }

    public static final boolean shouldApplySecureFlag(SecureFlagPolicy $this$shouldApplySecureFlag, boolean isSecureFlagSetOnParent) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$shouldApplySecureFlag.ordinal()]) {
            case 1:
                return false;
            case 2:
                return true;
            case 3:
                return isSecureFlagSetOnParent;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
