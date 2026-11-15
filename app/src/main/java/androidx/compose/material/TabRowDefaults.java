package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: TabRow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\rJ-\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0010\u0010\rJ\u0012\u0010\u0011\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010\u0016\u001a\u00020\t¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u001a\u001a\u00020\t¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001b\u0010\u0018R\u0013\u0010\u001c\u001a\u00020\t¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001d\u0010\u0018¨\u0006\u001e²\u0006\n\u0010\u001f\u001a\u00020\tX\u008a\u0084\u0002²\u0006\n\u0010 \u001a\u00020\tX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material/TabRowDefaults;", "", "<init>", "()V", "Divider", "", "modifier", "Landroidx/compose/ui/Modifier;", "thickness", "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/ui/graphics/Color;", "Divider-9IZ8Weo", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/runtime/Composer;II)V", "Indicator", "height", "Indicator-9IZ8Weo", "tabIndicatorOffset", "currentTabPosition", "Landroidx/compose/material/TabPosition;", "DividerOpacity", "", "DividerThickness", "getDividerThickness-D9Ej5fM", "()F", "F", "IndicatorHeight", "getIndicatorHeight-D9Ej5fM", "ScrollableTabRowPadding", "getScrollableTabRowPadding-D9Ej5fM", "material", "currentTabWidth", "indicatorOffset"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TabRowDefaults {
    public static final int $stable = 0;
    public static final float DividerOpacity = 0.12f;
    public static final TabRowDefaults INSTANCE = new TabRowDefaults();
    private static final float DividerThickness = Dp.m7905constructorimpl(1);
    private static final float IndicatorHeight = Dp.m7905constructorimpl(2);
    private static final float ScrollableTabRowPadding = Dp.m7905constructorimpl(52);

    static final Unit Divider_9IZ8Weo$lambda$0(TabRowDefaults tabRowDefaults, Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        tabRowDefaults.m1976Divider9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Indicator_9IZ8Weo$lambda$0(TabRowDefaults tabRowDefaults, Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        tabRowDefaults.m1977Indicator9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private TabRowDefaults() {
    }

    /* JADX INFO: renamed from: Divider-9IZ8Weo, reason: not valid java name */
    public final void m1976Divider9IZ8Weo(Modifier modifier, float f, long j, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f2;
        long j2;
        final Modifier modifier3;
        final float f3;
        final long j3;
        Modifier.Companion modifier4;
        float f4;
        float f5;
        long jM5095copywmQWz5c;
        Modifier modifier5;
        Composer $composer2 = $composer.startRestartGroup(910934799);
        ComposerKt.sourceInformation($composer2, "C(Divider)N(modifier,thickness:c#ui.unit.Dp,color:c#ui.graphics.Color)358@16538L66:TabRow.kt#jmzs0o");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                f2 = f;
                int i3 = $composer2.changed(f2) ? 32 : 16;
                $dirty |= i3;
            } else {
                f2 = f;
            }
            $dirty |= i3;
        } else {
            f2 = f;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j2 = j;
                int i4 = $composer2.changed(j2) ? 256 : 128;
                $dirty |= i4;
            } else {
                j2 = j;
            }
            $dirty |= i4;
        } else {
            j2 = j;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(this) ? 2048 : 1024;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            f3 = f2;
            j3 = j2;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "356@16458L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                f5 = f2;
                jM5095copywmQWz5c = j2;
                modifier5 = modifier2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 2) == 0) {
                    f4 = f2;
                } else {
                    f4 = DividerThickness;
                    $dirty &= -113;
                }
                if ((i & 4) == 0) {
                    f5 = f4;
                    jM5095copywmQWz5c = j2;
                    modifier5 = modifier4;
                } else {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    long jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
                    $dirty &= -897;
                    f5 = f4;
                    jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
                    modifier5 = modifier4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(910934799, $dirty, -1, "androidx.compose.material.TabRowDefaults.Divider (TabRow.kt:357)");
            }
            DividerKt.m1798DivideroMI9zvI(modifier5, jM5095copywmQWz5c, f5, 0.0f, $composer2, ($dirty & 14) | (($dirty >> 3) & 112) | (($dirty << 3) & 896), 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            j3 = jM5095copywmQWz5c;
            f3 = f5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TabRowDefaults$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowDefaults.Divider_9IZ8Weo$lambda$0(this.f$0, modifier3, f3, j3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: Indicator-9IZ8Weo, reason: not valid java name */
    public final void m1977Indicator9IZ8Weo(Modifier modifier, float f, long j, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f2;
        long j2;
        final Modifier.Companion modifier3;
        final float f3;
        final long j3;
        long jM5107unboximpl;
        Composer $composer2 = $composer.startRestartGroup(1499002201);
        ComposerKt.sourceInformation($composer2, "C(Indicator)N(modifier,height:c#ui.unit.Dp,color:c#ui.graphics.Color)375@17072L69:TabRow.kt#jmzs0o");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                f2 = f;
                int i3 = $composer2.changed(f2) ? 32 : 16;
                $dirty |= i3;
            } else {
                f2 = f;
            }
            $dirty |= i3;
        } else {
            f2 = f;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j2 = j;
                int i4 = $composer2.changed(j2) ? 256 : 128;
                $dirty |= i4;
            } else {
                j2 = j;
            }
            $dirty |= i4;
        } else {
            j2 = j;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(this) ? 2048 : 1024;
        }
        if (!$composer2.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            f3 = f2;
            j3 = j2;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "373@17047L7");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                modifier3 = modifier2;
                f3 = f2;
                jM5107unboximpl = j2;
            } else {
                if (i2 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i & 2) == 0) {
                    f3 = f2;
                } else {
                    f3 = IndicatorHeight;
                    $dirty &= -113;
                }
                if ((i & 4) == 0) {
                    jM5107unboximpl = j2;
                } else {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer2.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $dirty &= -897;
                    jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1499002201, $dirty, -1, "androidx.compose.material.TabRowDefaults.Indicator (TabRow.kt:374)");
            }
            BoxKt.Box(BackgroundKt.m265backgroundbw27NRU$default(SizeKt.m851height3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), f3), jM5107unboximpl, null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = jM5107unboximpl;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TabRowDefaults$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowDefaults.Indicator_9IZ8Weo$lambda$0(this.f$0, modifier3, f3, j3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final Modifier tabIndicatorOffset(Modifier $this$tabIndicatorOffset, final TabPosition currentTabPosition) {
        return ComposedModifierKt.composed($this$tabIndicatorOffset, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.TabRowDefaults$tabIndicatorOffset$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("tabIndicatorOffset");
                $this$null.setValue(currentTabPosition);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.material.TabRowDefaults$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TabRowDefaults.tabIndicatorOffset$lambda$1(currentTabPosition, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    private static final float tabIndicatorOffset$lambda$1$0(State<Dp> state) {
        Object thisObj$iv = state.getValue();
        return ((Dp) thisObj$iv).m7919unboximpl();
    }

    static final Modifier tabIndicatorOffset$lambda$1(TabPosition $currentTabPosition, Modifier $this$composed, Composer $composer, int $changed) {
        $composer.startReplaceGroup(-398757863);
        ComposerKt.sourceInformation($composer, "C394@17891L190,399@18133L189,405@18430L53:TabRow.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-398757863, $changed, -1, "androidx.compose.material.TabRowDefaults.tabIndicatorOffset.<anonymous> (TabRow.kt:393)");
        }
        State<Dp> stateM162animateDpAsStateAjpBEmI = AnimateAsStateKt.m162animateDpAsStateAjpBEmI($currentTabPosition.getWidth(), AnimationSpecKt.tween$default(250, 0, EasingKt.getFastOutSlowInEasing(), 2, null), null, null, $composer, 0, 12);
        final State<Dp> stateM162animateDpAsStateAjpBEmI2 = AnimateAsStateKt.m162animateDpAsStateAjpBEmI($currentTabPosition.getLeft(), AnimationSpecKt.tween$default(250, 0, EasingKt.getFastOutSlowInEasing(), 2, null), null, null, $composer, 0, 12);
        Modifier modifierWrapContentSize$default = SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default($this$composed, 0.0f, 1, null), Alignment.INSTANCE.getBottomStart(), false, 2, null);
        ComposerKt.sourceInformationMarkerStart($composer, -954198002, "CC(remember):TabRow.kt#9igjgp");
        boolean invalid$iv = $composer.changed(stateM162animateDpAsStateAjpBEmI2);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function1() { // from class: androidx.compose.material.TabRowDefaults$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TabRowDefaults.tabIndicatorOffset$lambda$1$2$0(stateM162animateDpAsStateAjpBEmI2, (Density) obj);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierM870width3ABfNKs = SizeKt.m870width3ABfNKs(OffsetKt.offset(modifierWrapContentSize$default, (Function1) it$iv), tabIndicatorOffset$lambda$1$0(stateM162animateDpAsStateAjpBEmI));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierM870width3ABfNKs;
    }

    private static final float tabIndicatorOffset$lambda$1$1(State<Dp> state) {
        Object thisObj$iv = state.getValue();
        return ((Dp) thisObj$iv).m7919unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset tabIndicatorOffset$lambda$1$2$0(State $indicatorOffset$delegate, Density $this$offset) {
        int x$iv = $this$offset.mo400roundToPx0680j_4(tabIndicatorOffset$lambda$1$1($indicatorOffset$delegate));
        return IntOffset.m8024boximpl(IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) 0) & 4294967295L)));
    }

    /* JADX INFO: renamed from: getDividerThickness-D9Ej5fM, reason: not valid java name */
    public final float m1978getDividerThicknessD9Ej5fM() {
        return DividerThickness;
    }

    /* JADX INFO: renamed from: getIndicatorHeight-D9Ej5fM, reason: not valid java name */
    public final float m1979getIndicatorHeightD9Ej5fM() {
        return IndicatorHeight;
    }

    /* JADX INFO: renamed from: getScrollableTabRowPadding-D9Ej5fM, reason: not valid java name */
    public final float m1980getScrollableTabRowPaddingD9Ej5fM() {
        return ScrollableTabRowPadding;
    }
}
