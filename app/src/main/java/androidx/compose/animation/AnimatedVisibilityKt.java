package androidx.compose.animation;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.animation.core.Transition;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.exifinterface.media.ExifInterface;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: AnimatedVisibility.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a[\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a_\u0010\u0000\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0013\u001a_\u0010\u0000\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0015\u001aa\u0010\u0000\u001a\u00020\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00172\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0018\u001ae\u0010\u0000\u001a\u00020\u0001*\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00172\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u0019\u001ae\u0010\u0000\u001a\u00020\u0001*\u00020\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00172\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001a\u001am\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001b*\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\u00030\r2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0002\u0010\u001d\u001ak\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010 \u001a\u0091\u0001\u0010!\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001b2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0018\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00030#2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\u001c\u0010\f\u001a\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0001¢\u0006\u0002\u0010'\u001a9\u0010+\u001a\u00020$\"\u0004\b\u0000\u0010\u001b*\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010,\u001a\u0002H\u001bH\u0003¢\u0006\u0002\u0010-\"\u001e\u0010(\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020$0\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*¨\u0006.²\u0006\u001c\u0010/\u001a\u0014\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00030#X\u008a\u0084\u0002²\u0006\n\u00100\u001a\u00020\u0003X\u008a\u0084\u0002"}, d2 = {"AnimatedVisibility", "", "visible", "", "modifier", "Landroidx/compose/ui/Modifier;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", MetadataField.LABEL, "", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/animation/AnimatedVisibilityScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/RowScope;", "(Landroidx/compose/foundation/layout/RowScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/ColumnScope;", "(Landroidx/compose/foundation/layout/ColumnScope;ZLandroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "visibleState", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/RowScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/foundation/layout/ColumnScope;Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/animation/core/Transition;", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "AnimatedVisibilityImpl", "transition", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "AnimatedEnterExitImpl", "shouldDisposeBlock", "Lkotlin/Function2;", "Landroidx/compose/animation/EnterExitState;", "onLookaheadMeasured", "Landroidx/compose/animation/OnLookaheadMeasured;", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Lkotlin/jvm/functions/Function2;Landroidx/compose/animation/OnLookaheadMeasured;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "exitFinished", "getExitFinished", "(Landroidx/compose/animation/core/Transition;)Z", "targetEnterExit", "targetState", "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterExitState;", "animation", "shouldDisposeBlockUpdated", "shouldDisposeAfterExit"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AnimatedVisibilityKt {
    public static final void AnimatedVisibility(boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function4;
        final String label2;
        Modifier modifier3;
        final EnterTransition enter2;
        final ExitTransition exit2;
        int i2;
        EnterTransition enter3;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(-1448730565);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)131@7032L32,132@7104L6,132@7069L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = visible;
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
            enterTransition = enter;
        } else if (($changed & 384) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 256 : 128;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            exitTransition = exit;
        } else if (($changed & 3072) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 2048 : 1024;
        } else {
            exitTransition = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(label) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function3;
        }
        if (!$composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            label2 = label;
            modifier3 = modifier2;
            enter2 = enterTransition;
            exit2 = exitTransition;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
                i2 = i6;
            } else {
                i2 = i6;
                modifier3 = modifier2;
            }
            if (i4 == 0) {
                enter3 = enterTransition;
            } else {
                enter3 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            }
            if (i5 != 0) {
                exit3 = EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            } else {
                exit3 = exitTransition;
            }
            if (i2 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1448730565, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:130)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(z), label3, $composer2, ($dirty & 14) | (($dirty >> 9) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -660656671, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$1$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier3, enter3, exit3, function4, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            enter2 = enter3;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            final boolean z2 = z;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility(z2, modifier4, enter2, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final RowScope $this$AnimatedVisibility, boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        final boolean z;
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        final String label2;
        Modifier modifier4;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(234057107);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)205@11418L32,206@11490L6,206@11455L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 32 : 16;
        } else {
            z = visible;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(label) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 1048576 : 524288;
        } else {
            function4 = function3;
        }
        if (!$composer2.shouldExecute((599185 & $dirty) != 599184, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = label;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null));
            }
            if (i4 == 0) {
                exit3 = exitTransition;
            } else {
                exit3 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null));
            }
            if (i5 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(234057107, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:204)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(z), label3, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 1590593721, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$3$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier4, enter4, exit3, function4, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier4;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, z, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final ColumnScope $this$AnimatedVisibility, boolean visible, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        final boolean z;
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        final String label2;
        Modifier modifier4;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(1799879339);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)278@15785L32,279@15857L6,279@15822L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            z = visible;
            $dirty |= $composer2.changed(z) ? 32 : 16;
        } else {
            z = visible;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(label) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 1048576 : 524288;
        } else {
            function4 = function3;
        }
        if (!$composer2.shouldExecute((599185 & $dirty) != 599184, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = label;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null));
            }
            if (i4 == 0) {
                exit3 = exitTransition;
            } else {
                exit3 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null));
            }
            if (i5 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1799879339, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:277)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.updateTransition(Boolean.valueOf(z), label3, $composer2, (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -1187762319, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$5$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier4, enter4, exit3, function4, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier4;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.6
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, z, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function4;
        final String label2;
        Modifier modifier3;
        final EnterTransition enter2;
        final ExitTransition exit2;
        int i2;
        EnterTransition enter3;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(657024243);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)377@21247L39,378@21326L6,378@21291L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(mutableTransitionState) : $composer2.changedInstance(mutableTransitionState) ? 4 : 2;
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
            enterTransition = enter;
        } else if (($changed & 384) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 256 : 128;
        } else {
            enterTransition = enter;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            exitTransition = exit;
        } else if (($changed & 3072) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 2048 : 1024;
        } else {
            exitTransition = exit;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(label) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function3;
        }
        if (!$composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            label2 = label;
            modifier3 = modifier2;
            enter2 = enterTransition;
            exit2 = exitTransition;
        } else {
            if (i3 != 0) {
                modifier3 = Modifier.INSTANCE;
                i2 = i6;
            } else {
                i2 = i6;
                modifier3 = modifier2;
            }
            if (i4 == 0) {
                enter3 = enterTransition;
            } else {
                enter3 = EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null));
            }
            if (i5 == 0) {
                exit3 = exitTransition;
            } else {
                exit3 = EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null));
            }
            if (i2 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(657024243, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:376)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, label3, $composer2, MutableTransitionState.$stable | ($dirty & 14) | (($dirty >> 9) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, 40118553, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$7$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier3, enter3, exit3, function4, $composer2, (($dirty << 3) & 896) | 48 | (($dirty << 3) & 7168) | (($dirty << 3) & 57344) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            enter2 = enter3;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.8
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    AnimatedVisibilityKt.AnimatedVisibility(mutableTransitionState, modifier4, enter2, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final RowScope $this$AnimatedVisibility, final MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        final String label2;
        Modifier modifier4;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(1763490971);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)449@25685L39,450@25764L6,450@25729L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(mutableTransitionState) : $composer2.changedInstance(mutableTransitionState) ? 32 : 16;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(label) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 1048576 : 524288;
        } else {
            function4 = function3;
        }
        if (!$composer2.shouldExecute((599185 & $dirty) != 599184, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = label;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.expandHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            }
            if (i4 != 0) {
                exit3 = EnterExitTransitionKt.shrinkHorizontally$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            } else {
                exit3 = exitTransition;
            }
            if (i5 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1763490971, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:448)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, label3, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -1797244351, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$9$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier4, enter4, exit3, function4, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier4;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.10
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, mutableTransitionState, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void AnimatedVisibility(final ColumnScope $this$AnimatedVisibility, final MutableTransitionState<Boolean> mutableTransitionState, Modifier modifier, EnterTransition enter, ExitTransition exit, String label, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        EnterTransition enter2;
        ExitTransition exitTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function4;
        final Modifier modifier3;
        final EnterTransition enter3;
        final ExitTransition exit2;
        final String label2;
        Modifier modifier4;
        ExitTransition exit3;
        String label3;
        Composer $composer2 = $composer.startRestartGroup(-1238803325);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(5,4,1,2,3)523@30218L39,524@30297L6,524@30262L84:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(mutableTransitionState) : $composer2.changedInstance(mutableTransitionState) ? 32 : 16;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enter2 = enter;
        } else if (($changed & 3072) == 0) {
            enter2 = enter;
            $dirty |= $composer2.changed(enter2) ? 2048 : 1024;
        } else {
            enter2 = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
            exitTransition = exit;
        } else if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((196608 & $changed) == 0) {
            $dirty |= $composer2.changed(label) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 1048576 : 524288;
        } else {
            function4 = function3;
        }
        if (!$composer2.shouldExecute((599185 & $dirty) != 599184, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            enter3 = enter2;
            exit2 = exitTransition;
            label2 = label;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 != 0) {
                enter2 = EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            }
            if (i4 != 0) {
                exit3 = EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null));
            } else {
                exit3 = exitTransition;
            }
            if (i5 == 0) {
                label3 = label;
            } else {
                label3 = "AnimatedVisibility";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1238803325, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:522)");
            }
            Transition transition = androidx.compose.animation.core.TransitionKt.rememberTransition(mutableTransitionState, label3, $composer2, MutableTransitionState.$stable | (($dirty >> 3) & 14) | (($dirty >> 12) & 112), 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -784039927, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = (Function1) new Function1<Boolean, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibility$11$1
                    public final Boolean invoke(boolean it) {
                        return Boolean.valueOf(it);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(Boolean bool) {
                        return invoke(bool.booleanValue());
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EnterTransition enter4 = enter2;
            AnimatedVisibilityImpl(transition, (Function1) it$iv, modifier4, enter4, exit3, function4, $composer2, ($dirty & 896) | 48 | ($dirty & 7168) | (57344 & $dirty) | (($dirty >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            label2 = label3;
            modifier3 = modifier4;
            enter3 = enter4;
            exit2 = exit3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.12
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    AnimatedVisibilityKt.AnimatedVisibility($this$AnimatedVisibility, mutableTransitionState, modifier3, enter3, exit2, label2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final <T> void AnimatedVisibility(final Transition<T> transition, final Function1<? super T, Boolean> function1, Modifier modifier, EnterTransition enter, ExitTransition exit, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Transition<T> transition2;
        Function1<? super T, Boolean> function2;
        final Modifier modifier2;
        EnterTransition enterTransition;
        Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function4;
        final EnterTransition enter2;
        final ExitTransition exit2;
        Composer $composer2 = $composer.startRestartGroup(-1699747442);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibility)P(4,3,1,2)593@34536L79:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            transition2 = transition;
            $dirty |= $composer2.changed(transition2) ? 4 : 2;
        } else {
            transition2 = transition;
        }
        if (($changed & 48) == 0) {
            function2 = function1;
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = function1;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 3072;
            enterTransition = enter;
        } else if (($changed & 3072) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changed(exit) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            function4 = function3;
            $dirty |= $composer2.changedInstance(function4) ? 131072 : 65536;
        } else {
            function4 = function3;
        }
        if ($composer2.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            Modifier modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            EnterTransition enter3 = i3 != 0 ? EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null).plus(EnterExitTransitionKt.expandIn$default(null, null, false, null, 15, null)) : enterTransition;
            ExitTransition exit3 = i4 != 0 ? EnterExitTransitionKt.shrinkOut$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)) : exit;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1699747442, $dirty, -1, "androidx.compose.animation.AnimatedVisibility (AnimatedVisibility.kt:593)");
            }
            EnterTransition enter4 = enter3;
            AnimatedVisibilityImpl(transition2, function2, modifier3, enter4, exit3, function4, $composer2, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            enter2 = enter4;
            exit2 = exit3;
        } else {
            $composer2.skipToGroupEnd();
            enter2 = enterTransition;
            exit2 = exit;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility.13
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i5) {
                    AnimatedVisibilityKt.AnimatedVisibility(transition, function1, modifier2, enter2, exit2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:72:0x0111  */
    /* JADX WARN: Code duplicated, block: B:76:0x014a  */
    public static final <T> void AnimatedVisibilityImpl(final Transition<T> transition, final Function1<? super T, Boolean> function1, final Modifier modifier, final EnterTransition enter, final ExitTransition exit, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        EnterTransition enterTransition;
        ExitTransition exitTransition;
        int i;
        Object it$iv;
        Composer $composer2 = $composer.startRestartGroup(1706321816);
        ComposerKt.sourceInformation($composer2, "C(AnimatedVisibilityImpl)P(4,5,3,1,2)682@38821L423,694@39319L62,678@38691L724:AnimatedVisibility.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(transition) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            enterTransition = enter;
            $dirty |= $composer2.changed(enterTransition) ? 2048 : 1024;
        } else {
            enterTransition = enter;
        }
        if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 131072 : 65536;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1706321816, $dirty2, -1, "androidx.compose.animation.AnimatedVisibilityImpl (AnimatedVisibility.kt:677)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -902229601, "CC(remember):AnimatedVisibility.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 112) == 32) | (($dirty2 & 14) == 4);
            Object it$iv2 = $composer2.rememberedValue();
            if (!invalid$iv) {
                i = 196608;
                if (it$iv2 != Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier modifierLayout = LayoutModifierKt.layout(modifier, (Function3) it$iv2);
                ComposerKt.sourceInformationMarkerStart($composer2, -902214026, "CC(remember):AnimatedVisibility.kt#9igjgp");
                it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (Function2) new Function2<EnterExitState, EnterExitState, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$2$1
                        @Override // kotlin.jvm.functions.Function2
                        public final Boolean invoke(EnterExitState current, EnterExitState target) {
                            return Boolean.valueOf(current == target && target == EnterExitState.PostExit);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                AnimatedEnterExitImpl(transition, function1, modifierLayout, enterTransition, exitTransition, (Function2) it$iv, null, function3, $composer2, (($dirty2 << 6) & 29360128) | ($dirty2 & 14) | i | ($dirty2 & 112) | ($dirty2 & 7168) | (57344 & $dirty2), 64);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                i = 196608;
            }
            Object value$iv2 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                    return m73invoke3p2s80s(measureScope, measurable, constraints.getValue());
                }

                /* JADX WARN: Type inference incomplete: some casts might be missing */
                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                public final MeasureResult m73invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                    long jM8071constructorimpl;
                    final Placeable placeableMo6541measureBRTryo0 = measurable.mo6541measureBRTryo0(j);
                    if (measureScope.isLookingAhead() && !function1.invoke((T) transition.getTargetState()).booleanValue()) {
                        jM8071constructorimpl = IntSize.INSTANCE.m8081getZeroYbymL2g();
                    } else {
                        jM8071constructorimpl = IntSize.m8071constructorimpl((((long) placeableMo6541measureBRTryo0.getWidth()) << 32) | (((long) placeableMo6541measureBRTryo0.getHeight()) & 4294967295L));
                    }
                    return MeasureScope.layout$default(measureScope, (int) (jM8071constructorimpl >> 32), (int) (jM8071constructorimpl & 4294967295L), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$1$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                            invoke2(placementScope);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Placeable.PlacementScope $this$layout) {
                            Placeable.PlacementScope.place$default($this$layout, placeableMo6541measureBRTryo0, 0, 0, 0.0f, 4, null);
                        }
                    }, 4, null);
                }
            };
            $composer2.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierLayout2 = LayoutModifierKt.layout(modifier, (Function3) it$iv2);
            ComposerKt.sourceInformationMarkerStart($composer2, -902214026, "CC(remember):AnimatedVisibility.kt#9igjgp");
            it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = (Function2) new Function2<EnterExitState, EnterExitState, Boolean>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedVisibilityImpl$2$1
                    @Override // kotlin.jvm.functions.Function2
                    public final Boolean invoke(EnterExitState current, EnterExitState target) {
                        return Boolean.valueOf(current == target && target == EnterExitState.PostExit);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv = value$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            AnimatedEnterExitImpl(transition, function1, modifierLayout2, enterTransition, exitTransition, (Function2) it$iv, null, function3, $composer2, (($dirty2 << 6) & 29360128) | ($dirty2 & 14) | i | ($dirty2 & 112) | ($dirty2 & 7168) | (57344 & $dirty2), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibilityImpl.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i2) {
                    AnimatedVisibilityKt.AnimatedVisibilityImpl(transition, function1, modifier, enter, exit, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0175  */
    /* JADX WARN: Code duplicated, block: B:105:0x018b  */
    /* JADX WARN: Code duplicated, block: B:106:0x0190  */
    /* JADX WARN: Code duplicated, block: B:109:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:112:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:115:0x01ec  */
    /* JADX WARN: Code duplicated, block: B:118:0x0204  */
    /* JADX WARN: Code duplicated, block: B:121:0x0253  */
    /* JADX WARN: Code duplicated, block: B:124:0x025c  */
    /* JADX WARN: Code duplicated, block: B:127:0x027e  */
    /* JADX WARN: Code duplicated, block: B:130:0x0291  */
    /* JADX WARN: Code duplicated, block: B:132:0x02a7  */
    /* JADX WARN: Code duplicated, block: B:133:0x02aa  */
    /* JADX WARN: Code duplicated, block: B:136:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:139:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:142:0x02ef  */
    /* JADX WARN: Code duplicated, block: B:144:0x030a  */
    /* JADX WARN: Code duplicated, block: B:151:0x0320  */
    /* JADX WARN: Code duplicated, block: B:154:0x032b  */
    /* JADX WARN: Code duplicated, block: B:156:0x0348  */
    /* JADX WARN: Code duplicated, block: B:159:0x0375  */
    /* JADX WARN: Code duplicated, block: B:163:0x03d2  */
    /* JADX WARN: Code duplicated, block: B:166:0x03de  */
    /* JADX WARN: Code duplicated, block: B:167:0x03e4  */
    /* JADX WARN: Code duplicated, block: B:94:0x0159 A[PHI: r7
  0x0159: PHI (r7v36 androidx.compose.animation.core.Transition<T>) = (r7v17 androidx.compose.animation.core.Transition<T>), (r7v37 androidx.compose.animation.core.Transition<T>) binds: [B:93:0x0157, B:89:0x014f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:98:0x016a  */
    public static final <T> void AnimatedEnterExitImpl(final Transition<T> transition, final Function1<? super T, Boolean> function1, final Modifier modifier, final EnterTransition enter, final ExitTransition exit, final Function2<? super EnterExitState, ? super EnterExitState, Boolean> function2, OnLookaheadMeasured onLookaheadMeasured, final Function3<? super AnimatedVisibilityScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        ExitTransition exitTransition;
        final OnLookaheadMeasured onLookaheadMeasured2;
        Transition<T> transition2;
        boolean invalid$iv$iv;
        Object it$iv$iv;
        int $dirty;
        Object it;
        int $changed2;
        int $changed3;
        Transition childTransition;
        State shouldDisposeBlockUpdated$delegate;
        boolean invalid$iv;
        Object it$iv;
        boolean invalid$iv2;
        Object it$iv2;
        AnimatedVisibilityScopeImpl scope;
        Modifier.Companion companionLayout;
        Object it$iv3;
        Function0<ComposeUiNode> constructor;
        Function0<ComposeUiNode> function0;
        Object it$iv4;
        final OnLookaheadMeasured onLookaheadMeasured3 = onLookaheadMeasured;
        Composer $composer2 = $composer.startRestartGroup(1912839215);
        ComposerKt.sourceInformation($composer2, "C(AnimatedEnterExitImpl)P(6,7,3,1,2,5,4):AnimatedVisibility.kt#xbi5r1");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(transition) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changedInstance(function1) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer2.changed(enter) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            exitTransition = exit;
            $dirty2 |= $composer2.changed(exitTransition) ? 16384 : 8192;
        } else {
            exitTransition = exit;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function2) ? 131072 : 65536;
        }
        int i2 = i & 64;
        int i3 = 1572864;
        if (i2 != 0) {
            $dirty2 |= i3;
        } else if ((1572864 & $changed) == 0) {
            i3 = ($changed & 2097152) == 0 ? $composer2.changed(onLookaheadMeasured3) : $composer2.changedInstance(onLookaheadMeasured3) ? 1048576 : 524288;
            $dirty2 |= i3;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        boolean invalid$iv3 = true;
        if ($composer2.shouldExecute((4793491 & $dirty2) != 4793490, $dirty2 & 1)) {
            if (i2 != 0) {
                onLookaheadMeasured3 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1912839215, $dirty2, -1, "androidx.compose.animation.AnimatedEnterExitImpl (AnimatedVisibility.kt:715)");
            }
            if (!function1.invoke(transition.getTargetState()).booleanValue() && !function1.invoke(transition.getCurrentState()).booleanValue() && !transition.isSeeking() && !transition.getHasInitialValueAnimations()) {
                $composer2.startReplaceGroup(-272333293);
                $composer2.endReplaceGroup();
                onLookaheadMeasured3 = onLookaheadMeasured3;
            } else {
                $composer2.startReplaceGroup(-232413539);
                ComposerKt.sourceInformation($composer2, "723@40225L124,727@40392L40,733@40641L529,730@40484L686");
                int $changed$iv = ($dirty2 & 14) | 48;
                ComposerKt.sourceInformationMarkerStart($composer2, -539313577, "CC(createChildTransition)N(label,transformToChildState)1768@75281L36,1769@75341L74,1770@75438L39,1771@75489L63:Transition.kt#pdpnli");
                ComposerKt.sourceInformationMarkerStart($composer2, 1410701659, "CC(remember):Transition.kt#9igjgp");
                if ((($changed$iv & 14) ^ 6) > 4) {
                    transition2 = transition;
                    if ($composer2.changed(transition2)) {
                        invalid$iv$iv = true;
                    }
                    it$iv$iv = $composer2.rememberedValue();
                    if (!invalid$iv$iv) {
                        $dirty = $dirty2;
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (transition2.isSeeking()) {
                            it = transition2.getCurrentState();
                        } else {
                            it = it$iv$iv;
                        }
                        $changed2 = ($changed$iv >> 3) & 112;
                        $composer2.startReplaceGroup(1844425648);
                        ComposerKt.sourceInformation($composer2, "C724@40307L28:AnimatedVisibility.kt#xbi5r1");
                        Transition<T> transition3 = transition2;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1844425648, $changed2, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:724)");
                        }
                        Object initialState$iv = targetEnterExit(transition, function1, it, $composer2, ($dirty & 14) | ($dirty & 112) | (($changed2 << 6) & 896));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        $composer2.endReplaceGroup();
                        Object it2 = transition3.getTargetState();
                        $changed3 = ($changed$iv >> 3) & 112;
                        $composer2.startReplaceGroup(1844425648);
                        ComposerKt.sourceInformation($composer2, "C724@40307L28:AnimatedVisibility.kt#xbi5r1");
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1844425648, $changed3, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:724)");
                        }
                        Object targetState$iv = targetEnterExit(transition, function1, it2, $composer2, ($dirty & 14) | ($dirty & 112) | (($changed3 << 6) & 896));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        $composer2.endReplaceGroup();
                        childTransition = androidx.compose.animation.core.TransitionKt.createChildTransitionInternal(transition3, initialState$iv, targetState$iv, "EnterExitTransition", $composer2, ($changed$iv & 14) | (($changed$iv << 6) & 7168));
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        shouldDisposeBlockUpdated$delegate = SnapshotStateKt.rememberUpdatedState(function2, $composer2, ($dirty >> 15) & 14);
                        Boolean boolInvoke = function2.invoke(childTransition.getCurrentState(), childTransition.getTargetState());
                        ComposerKt.sourceInformationMarkerStart($composer2, -7483872, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        invalid$iv = $composer2.changed(childTransition) | $composer2.changed(shouldDisposeBlockUpdated$delegate);
                        it$iv = $composer2.rememberedValue();
                        if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                            Object value$iv = (Function2) new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(childTransition, shouldDisposeBlockUpdated$delegate, null);
                            $composer2.updateRememberedValue(value$iv);
                            it$iv = value$iv;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        State shouldDisposeAfterExit$delegate = SnapshotStateKt.produceState(boolInvoke, (Function2) it$iv, $composer2, 0);
                        if (getExitFinished(childTransition) || !AnimatedEnterExitImpl$lambda$3(shouldDisposeAfterExit$delegate)) {
                            $composer2.startReplaceGroup(-231383533);
                            ComposerKt.sourceInformation($composer2, "749@41268L69,755@41536L47,769@42351L50,750@41350L1066");
                            ComposerKt.sourceInformationMarkerStart($composer2, -7464268, "CC(remember):AnimatedVisibility.kt#9igjgp");
                            if (($dirty & 14) == 4) {
                                invalid$iv2 = true;
                            } else {
                                invalid$iv2 = false;
                            }
                            it$iv2 = $composer2.rememberedValue();
                            if (!invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv2 = new AnimatedVisibilityScopeImpl(childTransition);
                                $composer2.updateRememberedValue(value$iv2);
                                it$iv2 = value$iv2;
                            }
                            scope = (AnimatedVisibilityScopeImpl) it$iv2;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            Modifier modifierCreateModifier = EnterExitTransitionKt.createModifier(childTransition, enter, exitTransition, null, "Built-in", $composer2, (($dirty >> 6) & 112) | 24576 | (($dirty >> 6) & 896), 4);
                            if (onLookaheadMeasured3 != null) {
                                $composer2.startReplaceGroup(-230964196);
                                ComposerKt.sourceInformation($composer2, "758@41738L479");
                                Modifier.Companion companion = Modifier.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer2, -7448818, "CC(remember):AnimatedVisibility.kt#9igjgp");
                                if (($dirty & 3670016) != 1048576 && (($dirty & 2097152) == 0 || !$composer2.changedInstance(onLookaheadMeasured3))) {
                                    invalid$iv3 = false;
                                }
                                it$iv4 = $composer2.rememberedValue();
                                if (!invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                                    Object value$iv3 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                        {
                                            super(3);
                                        }

                                        @Override // kotlin.jvm.functions.Function3
                                        public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                            return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                        }

                                        /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                        public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                            final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                            OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                            if ($this$layout.isLookingAhead()) {
                                                int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                                int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                                onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                            }
                                            return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                    invoke2(placementScope);
                                                    return Unit.INSTANCE;
                                                }

                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                                    Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                                }
                                            }, 4, null);
                                        }
                                    };
                                    $composer2.updateRememberedValue(value$iv3);
                                    it$iv4 = value$iv3;
                                }
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                companionLayout = LayoutModifierKt.layout(companion, (Function3) it$iv4);
                                $composer2.endReplaceGroup();
                            } else {
                                $composer2.startReplaceGroup(-7432681);
                                $composer2.endReplaceGroup();
                                companionLayout = Modifier.INSTANCE;
                            }
                            Modifier modifier$iv = modifier.then(modifierCreateModifier.then(companionLayout));
                            ComposerKt.sourceInformationMarkerStart($composer2, -7429631, "CC(remember):AnimatedVisibility.kt#9igjgp");
                            it$iv3 = $composer2.rememberedValue();
                            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv4 = new AnimatedEnterExitMeasurePolicy(scope);
                                $composer2.updateRememberedValue(value$iv4);
                                it$iv3 = value$iv4;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            MeasurePolicy measurePolicy$iv = (AnimatedEnterExitMeasurePolicy) it$iv3;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int compositeKeyHash$iv = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
                            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv = ((384 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                            Composer $this$Layout_u24lambda_u240$iv = Updater.m4367constructorimpl($composer2);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4371initimpl($this$Layout_u24lambda_u240$iv, Integer.valueOf(compositeKeyHash$iv), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i4 = ($changed$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1766274760, "C751@41392L9:AnimatedVisibility.kt#xbi5r1");
                            function3.invoke(scope, $composer2, Integer.valueOf(($dirty >> 18) & 112));
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-272333293);
                            $composer2.endReplaceGroup();
                        }
                        $composer2.endReplaceGroup();
                    } else {
                        $dirty = $dirty2;
                    }
                    Object value$iv$iv = transition2.getCurrentState();
                    $composer2.updateRememberedValue(value$iv$iv);
                    it$iv$iv = value$iv$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (transition2.isSeeking()) {
                        it = transition2.getCurrentState();
                    } else {
                        it = it$iv$iv;
                    }
                    $changed2 = ($changed$iv >> 3) & 112;
                    $composer2.startReplaceGroup(1844425648);
                    ComposerKt.sourceInformation($composer2, "C724@40307L28:AnimatedVisibility.kt#xbi5r1");
                    Transition<T> transition4 = transition2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1844425648, $changed2, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:724)");
                    }
                    Object initialState$iv2 = targetEnterExit(transition, function1, it, $composer2, ($dirty & 14) | ($dirty & 112) | (($changed2 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer2.endReplaceGroup();
                    Object it3 = transition4.getTargetState();
                    $changed3 = ($changed$iv >> 3) & 112;
                    $composer2.startReplaceGroup(1844425648);
                    ComposerKt.sourceInformation($composer2, "C724@40307L28:AnimatedVisibility.kt#xbi5r1");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1844425648, $changed3, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:724)");
                    }
                    Object targetState$iv2 = targetEnterExit(transition, function1, it3, $composer2, ($dirty & 14) | ($dirty & 112) | (($changed3 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer2.endReplaceGroup();
                    childTransition = androidx.compose.animation.core.TransitionKt.createChildTransitionInternal(transition4, initialState$iv2, targetState$iv2, "EnterExitTransition", $composer2, ($changed$iv & 14) | (($changed$iv << 6) & 7168));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    shouldDisposeBlockUpdated$delegate = SnapshotStateKt.rememberUpdatedState(function2, $composer2, ($dirty >> 15) & 14);
                    Boolean boolInvoke2 = function2.invoke(childTransition.getCurrentState(), childTransition.getTargetState());
                    ComposerKt.sourceInformationMarkerStart($composer2, -7483872, "CC(remember):AnimatedVisibility.kt#9igjgp");
                    invalid$iv = $composer2.changed(childTransition) | $composer2.changed(shouldDisposeBlockUpdated$delegate);
                    it$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                        Object value$iv5 = (Function2) new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(childTransition, shouldDisposeBlockUpdated$delegate, null);
                        $composer2.updateRememberedValue(value$iv5);
                        it$iv = value$iv5;
                    } else {
                        Object value$iv6 = (Function2) new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(childTransition, shouldDisposeBlockUpdated$delegate, null);
                        $composer2.updateRememberedValue(value$iv6);
                        it$iv = value$iv6;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    State shouldDisposeAfterExit$delegate2 = SnapshotStateKt.produceState(boolInvoke2, (Function2) it$iv, $composer2, 0);
                    if (getExitFinished(childTransition)) {
                        $composer2.startReplaceGroup(-231383533);
                        ComposerKt.sourceInformation($composer2, "749@41268L69,755@41536L47,769@42351L50,750@41350L1066");
                        ComposerKt.sourceInformationMarkerStart($composer2, -7464268, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        if (($dirty & 14) == 4) {
                            invalid$iv2 = true;
                        } else {
                            invalid$iv2 = false;
                        }
                        it$iv2 = $composer2.rememberedValue();
                        if (!invalid$iv2) {
                            Object value$iv7 = new AnimatedVisibilityScopeImpl(childTransition);
                            $composer2.updateRememberedValue(value$iv7);
                            it$iv2 = value$iv7;
                        } else {
                            Object value$iv8 = new AnimatedVisibilityScopeImpl(childTransition);
                            $composer2.updateRememberedValue(value$iv8);
                            it$iv2 = value$iv8;
                        }
                        scope = (AnimatedVisibilityScopeImpl) it$iv2;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        Modifier modifierCreateModifier2 = EnterExitTransitionKt.createModifier(childTransition, enter, exitTransition, null, "Built-in", $composer2, (($dirty >> 6) & 112) | 24576 | (($dirty >> 6) & 896), 4);
                        if (onLookaheadMeasured3 != null) {
                            $composer2.startReplaceGroup(-230964196);
                            ComposerKt.sourceInformation($composer2, "758@41738L479");
                            Modifier.Companion companion2 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, -7448818, "CC(remember):AnimatedVisibility.kt#9igjgp");
                            if (($dirty & 3670016) != 1048576) {
                                invalid$iv3 = false;
                            }
                            it$iv4 = $composer2.rememberedValue();
                            if (!invalid$iv3) {
                                Object value$iv9 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                        final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                        OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                        if ($this$layout.isLookingAhead()) {
                                            int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                            int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                            onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                        }
                                        return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                                Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv9);
                                it$iv4 = value$iv9;
                            } else {
                                Object value$iv10 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                        final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                        OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                        if ($this$layout.isLookingAhead()) {
                                            int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                            int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                            onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                        }
                                        return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                                Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv10);
                                it$iv4 = value$iv10;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            companionLayout = LayoutModifierKt.layout(companion2, (Function3) it$iv4);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-7432681);
                            $composer2.endReplaceGroup();
                            companionLayout = Modifier.INSTANCE;
                        }
                        Modifier modifier$iv2 = modifier.then(modifierCreateModifier2.then(companionLayout));
                        ComposerKt.sourceInformationMarkerStart($composer2, -7429631, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        it$iv3 = $composer2.rememberedValue();
                        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv11 = new AnimatedEnterExitMeasurePolicy(scope);
                            $composer2.updateRememberedValue(value$iv11);
                            it$iv3 = value$iv11;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        MeasurePolicy measurePolicy$iv2 = (AnimatedEnterExitMeasurePolicy) it$iv3;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int compositeKeyHash$iv2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                        CompositionLocalMap localMap$iv2 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv2 = ComposedModifierKt.materializeModifier($composer2, modifier$iv2);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv2 = ((384 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                        Composer $this$Layout_u24lambda_u240$iv2 = Updater.m4367constructorimpl($composer2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv2, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv2, localMap$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl($this$Layout_u24lambda_u240$iv2, Integer.valueOf(compositeKeyHash$iv2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv2, materialized$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i5 = ($changed$iv$iv2 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1766274760, "C751@41392L9:AnimatedVisibility.kt#xbi5r1");
                        function3.invoke(scope, $composer2, Integer.valueOf(($dirty >> 18) & 112));
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(-231383533);
                        ComposerKt.sourceInformation($composer2, "749@41268L69,755@41536L47,769@42351L50,750@41350L1066");
                        ComposerKt.sourceInformationMarkerStart($composer2, -7464268, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        if (($dirty & 14) == 4) {
                            invalid$iv2 = true;
                        } else {
                            invalid$iv2 = false;
                        }
                        it$iv2 = $composer2.rememberedValue();
                        if (!invalid$iv2) {
                            Object value$iv12 = new AnimatedVisibilityScopeImpl(childTransition);
                            $composer2.updateRememberedValue(value$iv12);
                            it$iv2 = value$iv12;
                        } else {
                            Object value$iv13 = new AnimatedVisibilityScopeImpl(childTransition);
                            $composer2.updateRememberedValue(value$iv13);
                            it$iv2 = value$iv13;
                        }
                        scope = (AnimatedVisibilityScopeImpl) it$iv2;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        Modifier modifierCreateModifier3 = EnterExitTransitionKt.createModifier(childTransition, enter, exitTransition, null, "Built-in", $composer2, (($dirty >> 6) & 112) | 24576 | (($dirty >> 6) & 896), 4);
                        if (onLookaheadMeasured3 != null) {
                            $composer2.startReplaceGroup(-230964196);
                            ComposerKt.sourceInformation($composer2, "758@41738L479");
                            Modifier.Companion companion3 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, -7448818, "CC(remember):AnimatedVisibility.kt#9igjgp");
                            if (($dirty & 3670016) != 1048576) {
                                invalid$iv3 = false;
                            }
                            it$iv4 = $composer2.rememberedValue();
                            if (!invalid$iv3) {
                                Object value$iv14 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                        final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                        OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                        if ($this$layout.isLookingAhead()) {
                                            int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                            int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                            onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                        }
                                        return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                                Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv14);
                                it$iv4 = value$iv14;
                            } else {
                                Object value$iv15 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                        final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                        OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                        if ($this$layout.isLookingAhead()) {
                                            int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                            int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                            onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                        }
                                        return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                                Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv15);
                                it$iv4 = value$iv15;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            companionLayout = LayoutModifierKt.layout(companion3, (Function3) it$iv4);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-7432681);
                            $composer2.endReplaceGroup();
                            companionLayout = Modifier.INSTANCE;
                        }
                        Modifier modifier$iv3 = modifier.then(modifierCreateModifier3.then(companionLayout));
                        ComposerKt.sourceInformationMarkerStart($composer2, -7429631, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        it$iv3 = $composer2.rememberedValue();
                        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv16 = new AnimatedEnterExitMeasurePolicy(scope);
                            $composer2.updateRememberedValue(value$iv16);
                            it$iv3 = value$iv16;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        MeasurePolicy measurePolicy$iv3 = (AnimatedEnterExitMeasurePolicy) it$iv3;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int compositeKeyHash$iv3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                        CompositionLocalMap localMap$iv3 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv3 = ComposedModifierKt.materializeModifier($composer2, modifier$iv3);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv3 = ((384 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                        Composer $this$Layout_u24lambda_u240$iv3 = Updater.m4367constructorimpl($composer2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv3, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv3, localMap$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl($this$Layout_u24lambda_u240$iv3, Integer.valueOf(compositeKeyHash$iv3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv3, materialized$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                        int i6 = ($changed$iv$iv3 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1766274760, "C751@41392L9:AnimatedVisibility.kt#xbi5r1");
                        function3.invoke(scope, $composer2, Integer.valueOf(($dirty >> 18) & 112));
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endReplaceGroup();
                    }
                    $composer2.endReplaceGroup();
                } else {
                    transition2 = transition;
                }
                if (($changed$iv & 6) == 4) {
                    invalid$iv$iv = true;
                } else {
                    invalid$iv$iv = false;
                }
                it$iv$iv = $composer2.rememberedValue();
                if (!invalid$iv$iv) {
                    $dirty = $dirty2;
                    if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (transition2.isSeeking()) {
                        it = transition2.getCurrentState();
                    } else {
                        it = it$iv$iv;
                    }
                    $changed2 = ($changed$iv >> 3) & 112;
                    $composer2.startReplaceGroup(1844425648);
                    ComposerKt.sourceInformation($composer2, "C724@40307L28:AnimatedVisibility.kt#xbi5r1");
                    Transition<T> transition5 = transition2;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1844425648, $changed2, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:724)");
                    }
                    Object initialState$iv3 = targetEnterExit(transition, function1, it, $composer2, ($dirty & 14) | ($dirty & 112) | (($changed2 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer2.endReplaceGroup();
                    Object it4 = transition5.getTargetState();
                    $changed3 = ($changed$iv >> 3) & 112;
                    $composer2.startReplaceGroup(1844425648);
                    ComposerKt.sourceInformation($composer2, "C724@40307L28:AnimatedVisibility.kt#xbi5r1");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1844425648, $changed3, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:724)");
                    }
                    Object targetState$iv3 = targetEnterExit(transition, function1, it4, $composer2, ($dirty & 14) | ($dirty & 112) | (($changed3 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer2.endReplaceGroup();
                    childTransition = androidx.compose.animation.core.TransitionKt.createChildTransitionInternal(transition5, initialState$iv3, targetState$iv3, "EnterExitTransition", $composer2, ($changed$iv & 14) | (($changed$iv << 6) & 7168));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    shouldDisposeBlockUpdated$delegate = SnapshotStateKt.rememberUpdatedState(function2, $composer2, ($dirty >> 15) & 14);
                    Boolean boolInvoke3 = function2.invoke(childTransition.getCurrentState(), childTransition.getTargetState());
                    ComposerKt.sourceInformationMarkerStart($composer2, -7483872, "CC(remember):AnimatedVisibility.kt#9igjgp");
                    invalid$iv = $composer2.changed(childTransition) | $composer2.changed(shouldDisposeBlockUpdated$delegate);
                    it$iv = $composer2.rememberedValue();
                    if (!invalid$iv) {
                        Object value$iv17 = (Function2) new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(childTransition, shouldDisposeBlockUpdated$delegate, null);
                        $composer2.updateRememberedValue(value$iv17);
                        it$iv = value$iv17;
                    } else {
                        Object value$iv18 = (Function2) new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(childTransition, shouldDisposeBlockUpdated$delegate, null);
                        $composer2.updateRememberedValue(value$iv18);
                        it$iv = value$iv18;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    State shouldDisposeAfterExit$delegate3 = SnapshotStateKt.produceState(boolInvoke3, (Function2) it$iv, $composer2, 0);
                    if (getExitFinished(childTransition)) {
                        $composer2.startReplaceGroup(-231383533);
                        ComposerKt.sourceInformation($composer2, "749@41268L69,755@41536L47,769@42351L50,750@41350L1066");
                        ComposerKt.sourceInformationMarkerStart($composer2, -7464268, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        if (($dirty & 14) == 4) {
                            invalid$iv2 = true;
                        } else {
                            invalid$iv2 = false;
                        }
                        it$iv2 = $composer2.rememberedValue();
                        if (!invalid$iv2) {
                            Object value$iv19 = new AnimatedVisibilityScopeImpl(childTransition);
                            $composer2.updateRememberedValue(value$iv19);
                            it$iv2 = value$iv19;
                        } else {
                            Object value$iv110 = new AnimatedVisibilityScopeImpl(childTransition);
                            $composer2.updateRememberedValue(value$iv110);
                            it$iv2 = value$iv110;
                        }
                        scope = (AnimatedVisibilityScopeImpl) it$iv2;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        Modifier modifierCreateModifier4 = EnterExitTransitionKt.createModifier(childTransition, enter, exitTransition, null, "Built-in", $composer2, (($dirty >> 6) & 112) | 24576 | (($dirty >> 6) & 896), 4);
                        if (onLookaheadMeasured3 != null) {
                            $composer2.startReplaceGroup(-230964196);
                            ComposerKt.sourceInformation($composer2, "758@41738L479");
                            Modifier.Companion companion4 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, -7448818, "CC(remember):AnimatedVisibility.kt#9igjgp");
                            if (($dirty & 3670016) != 1048576) {
                                invalid$iv3 = false;
                            }
                            it$iv4 = $composer2.rememberedValue();
                            if (!invalid$iv3) {
                                Object value$iv111 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                        final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                        OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                        if ($this$layout.isLookingAhead()) {
                                            int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                            int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                            onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                        }
                                        return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                                Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv111);
                                it$iv4 = value$iv111;
                            } else {
                                Object value$iv112 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                        final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                        OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                        if ($this$layout.isLookingAhead()) {
                                            int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                            int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                            onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                        }
                                        return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                                Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv112);
                                it$iv4 = value$iv112;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            companionLayout = LayoutModifierKt.layout(companion4, (Function3) it$iv4);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-7432681);
                            $composer2.endReplaceGroup();
                            companionLayout = Modifier.INSTANCE;
                        }
                        Modifier modifier$iv4 = modifier.then(modifierCreateModifier4.then(companionLayout));
                        ComposerKt.sourceInformationMarkerStart($composer2, -7429631, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        it$iv3 = $composer2.rememberedValue();
                        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv113 = new AnimatedEnterExitMeasurePolicy(scope);
                            $composer2.updateRememberedValue(value$iv113);
                            it$iv3 = value$iv113;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        MeasurePolicy measurePolicy$iv4 = (AnimatedEnterExitMeasurePolicy) it$iv3;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int compositeKeyHash$iv4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                        CompositionLocalMap localMap$iv4 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv4 = ComposedModifierKt.materializeModifier($composer2, modifier$iv4);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv4 = ((384 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                        Composer $this$Layout_u24lambda_u240$iv4 = Updater.m4367constructorimpl($composer2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv4, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv4, localMap$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl($this$Layout_u24lambda_u240$iv4, Integer.valueOf(compositeKeyHash$iv4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv4, materialized$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                        int i7 = ($changed$iv$iv4 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1766274760, "C751@41392L9:AnimatedVisibility.kt#xbi5r1");
                        function3.invoke(scope, $composer2, Integer.valueOf(($dirty >> 18) & 112));
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(-231383533);
                        ComposerKt.sourceInformation($composer2, "749@41268L69,755@41536L47,769@42351L50,750@41350L1066");
                        ComposerKt.sourceInformationMarkerStart($composer2, -7464268, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        if (($dirty & 14) == 4) {
                            invalid$iv2 = true;
                        } else {
                            invalid$iv2 = false;
                        }
                        it$iv2 = $composer2.rememberedValue();
                        if (!invalid$iv2) {
                            Object value$iv114 = new AnimatedVisibilityScopeImpl(childTransition);
                            $composer2.updateRememberedValue(value$iv114);
                            it$iv2 = value$iv114;
                        } else {
                            Object value$iv115 = new AnimatedVisibilityScopeImpl(childTransition);
                            $composer2.updateRememberedValue(value$iv115);
                            it$iv2 = value$iv115;
                        }
                        scope = (AnimatedVisibilityScopeImpl) it$iv2;
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        Modifier modifierCreateModifier5 = EnterExitTransitionKt.createModifier(childTransition, enter, exitTransition, null, "Built-in", $composer2, (($dirty >> 6) & 112) | 24576 | (($dirty >> 6) & 896), 4);
                        if (onLookaheadMeasured3 != null) {
                            $composer2.startReplaceGroup(-230964196);
                            ComposerKt.sourceInformation($composer2, "758@41738L479");
                            Modifier.Companion companion5 = Modifier.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, -7448818, "CC(remember):AnimatedVisibility.kt#9igjgp");
                            if (($dirty & 3670016) != 1048576) {
                                invalid$iv3 = false;
                            }
                            it$iv4 = $composer2.rememberedValue();
                            if (!invalid$iv3) {
                                Object value$iv116 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                        final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                        OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                        if ($this$layout.isLookingAhead()) {
                                            int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                            int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                            onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                        }
                                        return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                                Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv116);
                                it$iv4 = value$iv116;
                            } else {
                                Object value$iv117 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                        return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                    }

                                    /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                    public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                        final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                        OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                        if ($this$layout.isLookingAhead()) {
                                            int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                            int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                            onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                        }
                                        return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                                invoke2(placementScope);
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                                Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                            }
                                        }, 4, null);
                                    }
                                };
                                $composer2.updateRememberedValue(value$iv117);
                                it$iv4 = value$iv117;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            companionLayout = LayoutModifierKt.layout(companion5, (Function3) it$iv4);
                            $composer2.endReplaceGroup();
                        } else {
                            $composer2.startReplaceGroup(-7432681);
                            $composer2.endReplaceGroup();
                            companionLayout = Modifier.INSTANCE;
                        }
                        Modifier modifier$iv5 = modifier.then(modifierCreateModifier5.then(companionLayout));
                        ComposerKt.sourceInformationMarkerStart($composer2, -7429631, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        it$iv3 = $composer2.rememberedValue();
                        if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                            Object value$iv118 = new AnimatedEnterExitMeasurePolicy(scope);
                            $composer2.updateRememberedValue(value$iv118);
                            it$iv3 = value$iv118;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        MeasurePolicy measurePolicy$iv5 = (AnimatedEnterExitMeasurePolicy) it$iv3;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int compositeKeyHash$iv5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                        CompositionLocalMap localMap$iv5 = $composer2.getCurrentCompositionLocalMap();
                        Modifier materialized$iv5 = ComposedModifierKt.materializeModifier($composer2, modifier$iv5);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv5 = ((384 << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                        Composer $this$Layout_u24lambda_u240$iv5 = Updater.m4367constructorimpl($composer2);
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv5, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv5, localMap$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl($this$Layout_u24lambda_u240$iv5, Integer.valueOf(compositeKeyHash$iv5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv5, materialized$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                        int i8 = ($changed$iv$iv5 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer2, -1766274760, "C751@41392L9:AnimatedVisibility.kt#xbi5r1");
                        function3.invoke(scope, $composer2, Integer.valueOf(($dirty >> 18) & 112));
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endReplaceGroup();
                    }
                    $composer2.endReplaceGroup();
                } else {
                    $dirty = $dirty2;
                }
                Object value$iv$iv2 = transition2.getCurrentState();
                $composer2.updateRememberedValue(value$iv$iv2);
                it$iv$iv = value$iv$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (transition2.isSeeking()) {
                    it = transition2.getCurrentState();
                } else {
                    it = it$iv$iv;
                }
                $changed2 = ($changed$iv >> 3) & 112;
                $composer2.startReplaceGroup(1844425648);
                ComposerKt.sourceInformation($composer2, "C724@40307L28:AnimatedVisibility.kt#xbi5r1");
                Transition<T> transition6 = transition2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1844425648, $changed2, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:724)");
                }
                Object initialState$iv4 = targetEnterExit(transition, function1, it, $composer2, ($dirty & 14) | ($dirty & 112) | (($changed2 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer2.endReplaceGroup();
                Object it5 = transition6.getTargetState();
                $changed3 = ($changed$iv >> 3) & 112;
                $composer2.startReplaceGroup(1844425648);
                ComposerKt.sourceInformation($composer2, "C724@40307L28:AnimatedVisibility.kt#xbi5r1");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1844425648, $changed3, -1, "androidx.compose.animation.AnimatedEnterExitImpl.<anonymous> (AnimatedVisibility.kt:724)");
                }
                Object targetState$iv4 = targetEnterExit(transition, function1, it5, $composer2, ($dirty & 14) | ($dirty & 112) | (($changed3 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer2.endReplaceGroup();
                childTransition = androidx.compose.animation.core.TransitionKt.createChildTransitionInternal(transition6, initialState$iv4, targetState$iv4, "EnterExitTransition", $composer2, ($changed$iv & 14) | (($changed$iv << 6) & 7168));
                ComposerKt.sourceInformationMarkerEnd($composer2);
                shouldDisposeBlockUpdated$delegate = SnapshotStateKt.rememberUpdatedState(function2, $composer2, ($dirty >> 15) & 14);
                Boolean boolInvoke4 = function2.invoke(childTransition.getCurrentState(), childTransition.getTargetState());
                ComposerKt.sourceInformationMarkerStart($composer2, -7483872, "CC(remember):AnimatedVisibility.kt#9igjgp");
                invalid$iv = $composer2.changed(childTransition) | $composer2.changed(shouldDisposeBlockUpdated$delegate);
                it$iv = $composer2.rememberedValue();
                if (!invalid$iv) {
                    Object value$iv119 = (Function2) new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(childTransition, shouldDisposeBlockUpdated$delegate, null);
                    $composer2.updateRememberedValue(value$iv119);
                    it$iv = value$iv119;
                } else {
                    Object value$iv1110 = (Function2) new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(childTransition, shouldDisposeBlockUpdated$delegate, null);
                    $composer2.updateRememberedValue(value$iv1110);
                    it$iv = value$iv1110;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                State shouldDisposeAfterExit$delegate4 = SnapshotStateKt.produceState(boolInvoke4, (Function2) it$iv, $composer2, 0);
                if (getExitFinished(childTransition)) {
                    $composer2.startReplaceGroup(-231383533);
                    ComposerKt.sourceInformation($composer2, "749@41268L69,755@41536L47,769@42351L50,750@41350L1066");
                    ComposerKt.sourceInformationMarkerStart($composer2, -7464268, "CC(remember):AnimatedVisibility.kt#9igjgp");
                    if (($dirty & 14) == 4) {
                        invalid$iv2 = true;
                    } else {
                        invalid$iv2 = false;
                    }
                    it$iv2 = $composer2.rememberedValue();
                    if (!invalid$iv2) {
                        Object value$iv1111 = new AnimatedVisibilityScopeImpl(childTransition);
                        $composer2.updateRememberedValue(value$iv1111);
                        it$iv2 = value$iv1111;
                    } else {
                        Object value$iv1112 = new AnimatedVisibilityScopeImpl(childTransition);
                        $composer2.updateRememberedValue(value$iv1112);
                        it$iv2 = value$iv1112;
                    }
                    scope = (AnimatedVisibilityScopeImpl) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierCreateModifier6 = EnterExitTransitionKt.createModifier(childTransition, enter, exitTransition, null, "Built-in", $composer2, (($dirty >> 6) & 112) | 24576 | (($dirty >> 6) & 896), 4);
                    if (onLookaheadMeasured3 != null) {
                        $composer2.startReplaceGroup(-230964196);
                        ComposerKt.sourceInformation($composer2, "758@41738L479");
                        Modifier.Companion companion6 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer2, -7448818, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        if (($dirty & 3670016) != 1048576) {
                            invalid$iv3 = false;
                        }
                        it$iv4 = $composer2.rememberedValue();
                        if (!invalid$iv3) {
                            Object value$iv1113 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                    return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                    final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                    OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                    if ($this$layout.isLookingAhead()) {
                                        int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                        int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                        onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                    }
                                    return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                            invoke2(placementScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                            Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                        }
                                    }, 4, null);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv1113);
                            it$iv4 = value$iv1113;
                        } else {
                            Object value$iv1114 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                    return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                    final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                    OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                    if ($this$layout.isLookingAhead()) {
                                        int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                        int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                        onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                    }
                                    return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                            invoke2(placementScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                            Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                        }
                                    }, 4, null);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv1114);
                            it$iv4 = value$iv1114;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        companionLayout = LayoutModifierKt.layout(companion6, (Function3) it$iv4);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(-7432681);
                        $composer2.endReplaceGroup();
                        companionLayout = Modifier.INSTANCE;
                    }
                    Modifier modifier$iv6 = modifier.then(modifierCreateModifier6.then(companionLayout));
                    ComposerKt.sourceInformationMarkerStart($composer2, -7429631, "CC(remember):AnimatedVisibility.kt#9igjgp");
                    it$iv3 = $composer2.rememberedValue();
                    if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv1115 = new AnimatedEnterExitMeasurePolicy(scope);
                        $composer2.updateRememberedValue(value$iv1115);
                        it$iv3 = value$iv1115;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    MeasurePolicy measurePolicy$iv6 = (AnimatedEnterExitMeasurePolicy) it$iv3;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int compositeKeyHash$iv6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap localMap$iv6 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv6 = ComposedModifierKt.materializeModifier($composer2, modifier$iv6);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv6 = ((384 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                    Composer $this$Layout_u24lambda_u240$iv6 = Updater.m4367constructorimpl($composer2);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv6, measurePolicy$iv6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv6, localMap$iv6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl($this$Layout_u24lambda_u240$iv6, Integer.valueOf(compositeKeyHash$iv6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv6, materialized$iv6, ComposeUiNode.INSTANCE.getSetModifier());
                    int i9 = ($changed$iv$iv6 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1766274760, "C751@41392L9:AnimatedVisibility.kt#xbi5r1");
                    function3.invoke(scope, $composer2, Integer.valueOf(($dirty >> 18) & 112));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(-231383533);
                    ComposerKt.sourceInformation($composer2, "749@41268L69,755@41536L47,769@42351L50,750@41350L1066");
                    ComposerKt.sourceInformationMarkerStart($composer2, -7464268, "CC(remember):AnimatedVisibility.kt#9igjgp");
                    if (($dirty & 14) == 4) {
                        invalid$iv2 = true;
                    } else {
                        invalid$iv2 = false;
                    }
                    it$iv2 = $composer2.rememberedValue();
                    if (!invalid$iv2) {
                        Object value$iv1116 = new AnimatedVisibilityScopeImpl(childTransition);
                        $composer2.updateRememberedValue(value$iv1116);
                        it$iv2 = value$iv1116;
                    } else {
                        Object value$iv1117 = new AnimatedVisibilityScopeImpl(childTransition);
                        $composer2.updateRememberedValue(value$iv1117);
                        it$iv2 = value$iv1117;
                    }
                    scope = (AnimatedVisibilityScopeImpl) it$iv2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier modifierCreateModifier7 = EnterExitTransitionKt.createModifier(childTransition, enter, exitTransition, null, "Built-in", $composer2, (($dirty >> 6) & 112) | 24576 | (($dirty >> 6) & 896), 4);
                    if (onLookaheadMeasured3 != null) {
                        $composer2.startReplaceGroup(-230964196);
                        ComposerKt.sourceInformation($composer2, "758@41738L479");
                        Modifier.Companion companion7 = Modifier.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer2, -7448818, "CC(remember):AnimatedVisibility.kt#9igjgp");
                        if (($dirty & 3670016) != 1048576) {
                            invalid$iv3 = false;
                        }
                        it$iv4 = $composer2.rememberedValue();
                        if (!invalid$iv3) {
                            Object value$iv1118 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                    return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                    final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                    OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                    if ($this$layout.isLookingAhead()) {
                                        int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                        int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                        onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                    }
                                    return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                            invoke2(placementScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                            Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                        }
                                    }, 4, null);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv1118);
                            it$iv4 = value$iv1118;
                        } else {
                            Object value$iv1119 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                                    return m72invoke3p2s80s(measureScope, measurable, constraints.getValue());
                                }

                                /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
                                public final MeasureResult m72invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long j) {
                                    final Placeable $this$invoke_3p2s80s_u24lambda_u240 = measurable.mo6541measureBRTryo0(j);
                                    OnLookaheadMeasured onLookaheadMeasured4 = onLookaheadMeasured3;
                                    if ($this$layout.isLookingAhead()) {
                                        int width$iv = $this$invoke_3p2s80s_u24lambda_u240.getWidth();
                                        int height$iv = $this$invoke_3p2s80s_u24lambda_u240.getHeight();
                                        onLookaheadMeasured4.m114invokeozmzZPI(IntSize.m8071constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
                                    }
                                    return MeasureScope.layout$default($this$layout, $this$invoke_3p2s80s_u24lambda_u240.getWidth(), $this$invoke_3p2s80s_u24lambda_u240.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2$1$1$1
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                            invoke2(placementScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                            Placeable.PlacementScope.place$default($this$layout2, $this$invoke_3p2s80s_u24lambda_u240, 0, 0, 0.0f, 4, null);
                                        }
                                    }, 4, null);
                                }
                            };
                            $composer2.updateRememberedValue(value$iv1119);
                            it$iv4 = value$iv1119;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        companionLayout = LayoutModifierKt.layout(companion7, (Function3) it$iv4);
                        $composer2.endReplaceGroup();
                    } else {
                        $composer2.startReplaceGroup(-7432681);
                        $composer2.endReplaceGroup();
                        companionLayout = Modifier.INSTANCE;
                    }
                    Modifier modifier$iv7 = modifier.then(modifierCreateModifier7.then(companionLayout));
                    ComposerKt.sourceInformationMarkerStart($composer2, -7429631, "CC(remember):AnimatedVisibility.kt#9igjgp");
                    it$iv3 = $composer2.rememberedValue();
                    if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                        Object value$iv11110 = new AnimatedEnterExitMeasurePolicy(scope);
                        $composer2.updateRememberedValue(value$iv11110);
                        it$iv3 = value$iv11110;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    MeasurePolicy measurePolicy$iv7 = (AnimatedEnterExitMeasurePolicy) it$iv3;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int compositeKeyHash$iv7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap localMap$iv7 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materialized$iv7 = ComposedModifierKt.materializeModifier($composer2, modifier$iv7);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int $changed$iv$iv7 = ((384 << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                    Composer $this$Layout_u24lambda_u240$iv7 = Updater.m4367constructorimpl($composer2);
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv7, measurePolicy$iv7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv7, localMap$iv7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl($this$Layout_u24lambda_u240$iv7, Integer.valueOf(compositeKeyHash$iv7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl($this$Layout_u24lambda_u240$iv7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv7, materialized$iv7, ComposeUiNode.INSTANCE.getSetModifier());
                    int i10 = ($changed$iv$iv7 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1766274760, "C751@41392L9:AnimatedVisibility.kt#xbi5r1");
                    function3.invoke(scope, $composer2, Integer.valueOf(($dirty >> 18) & 112));
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endReplaceGroup();
                }
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            onLookaheadMeasured2 = onLookaheadMeasured3;
        } else {
            $composer2.skipToGroupEnd();
            onLookaheadMeasured2 = onLookaheadMeasured3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.AnimatedVisibilityKt.AnimatedEnterExitImpl.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i11) {
                    AnimatedVisibilityKt.AnimatedEnterExitImpl(transition, function1, modifier, enter, exit, function2, onLookaheadMeasured2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<EnterExitState, EnterExitState, Boolean> AnimatedEnterExitImpl$lambda$1(State<? extends Function2<? super EnterExitState, ? super EnterExitState, Boolean>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    private static final boolean AnimatedEnterExitImpl$lambda$3(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getExitFinished(Transition<EnterExitState> transition) {
        return transition.getCurrentState() == EnterExitState.PostExit && transition.getTargetState() == EnterExitState.PostExit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> EnterExitState targetEnterExit(Transition<T> transition, Function1<? super T, Boolean> function1, T t, Composer $composer, int $changed) {
        EnterExitState enterExitState;
        ComposerKt.sourceInformationMarkerStart($composer, 361571134, "C(targetEnterExit)P(1):AnimatedVisibility.kt#xbi5r1");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(361571134, $changed, -1, "androidx.compose.animation.targetEnterExit (AnimatedVisibility.kt:833)");
        }
        $composer.startMovableGroup(-422486745, transition);
        ComposerKt.sourceInformation($composer, "");
        if (transition.isSeeking()) {
            $composer.startReplaceGroup(-212166497);
            $composer.endReplaceGroup();
            if (function1.invoke(t).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (function1.invoke(transition.getCurrentState()).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
        } else {
            $composer.startReplaceGroup(-211892364);
            ComposerKt.sourceInformation($composer, "845@44894L34");
            ComposerKt.sourceInformationMarkerStart($composer, -422476640, "CC(remember):AnimatedVisibility.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MutableState hasBeenVisible = (MutableState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (function1.invoke(transition.getCurrentState()).booleanValue()) {
                hasBeenVisible.setValue(true);
            }
            if (function1.invoke(t).booleanValue()) {
                enterExitState = EnterExitState.Visible;
            } else if (((Boolean) hasBeenVisible.getValue()).booleanValue()) {
                enterExitState = EnterExitState.PostExit;
            } else {
                enterExitState = EnterExitState.PreEnter;
            }
            $composer.endReplaceGroup();
        }
        $composer.endMovableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return enterExitState;
    }
}
