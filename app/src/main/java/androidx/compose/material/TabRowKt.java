package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TabRow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\u001a\u009c\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u000723\b\u0002\u0010\t\u001a-\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u0018\b\u0002\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a¦\u0001\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u001923\b\u0002\u0010\t\u001a-\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u0018\b\u0002\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u0013¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\"\u0010\u0010\u001c\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001d\"\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"TabRow", "", "selectedTabIndex", "", "modifier", "Landroidx/compose/ui/Modifier;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "indicator", "Lkotlin/Function1;", "", "Landroidx/compose/material/TabPosition;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "tabPositions", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/UiComposable;", "divider", "Lkotlin/Function0;", "tabs", "TabRow-pAZo6Ak", "(ILandroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ScrollableTabRow", "edgePadding", "Landroidx/compose/ui/unit/Dp;", "ScrollableTabRow-sKfQg0A", "(ILandroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ScrollableTabRowMinimumTabWidth", "F", "ScrollableTabRowScrollSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TabRowKt {
    private static final float ScrollableTabRowMinimumTabWidth = Dp.m7905constructorimpl(90);
    private static final AnimationSpec<Float> ScrollableTabRowScrollSpec = AnimationSpecKt.tween$default(250, 0, EasingKt.getFastOutSlowInEasing(), 2, null);

    static final Unit ScrollableTabRow_sKfQg0A$lambda$2(int i, Modifier modifier, long j, long j2, float f, Function3 function3, Function2 function2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m1984ScrollableTabRowsKfQg0A(i, modifier, j, j2, f, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit TabRow_pAZo6Ak$lambda$2(int i, Modifier modifier, long j, long j2, Function3 function3, Function2 function2, Function2 function4, int i2, int i3, Composer composer, int i4) {
        m1985TabRowpAZo6Ak(i, modifier, j, j2, function3, function2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit TabRow_pAZo6Ak$lambda$0(int $selectedTabIndex, List tabPositions, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(tabPositions)141@6972L70:TabRow.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896966245, $changed, -1, "androidx.compose.material.TabRow.<anonymous> (TabRow.kt:141)");
        }
        TabRowDefaults.INSTANCE.m1977Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, (TabPosition) tabPositions.get($selectedTabIndex)), 0.0f, 0L, $composer, 3072, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TabRow-pAZo6Ak, reason: not valid java name */
    public static final void m1985TabRowpAZo6Ak(final int selectedTabIndex, Modifier modifier, long j, long j2, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        long j3;
        long j4;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3RememberComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Composer $composer2;
        final long j5;
        final long j6;
        final Modifier modifier2;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function6;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Modifier.Companion modifier3;
        long primarySurface;
        long jM1783contentColorForek8zF_U;
        int $dirty;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function8;
        long j7;
        Modifier modifier4;
        final Function2<? super Composer, ? super Integer, Unit> function2M1793getLambda$1216856915$material;
        long j8;
        Composer $composer3 = $composer.startRestartGroup(113221600);
        ComposerKt.sourceInformation($composer3, "C(TabRow)N(selectedTabIndex,modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,indicator,divider,tabs)150@7336L1392,146@7204L1524:TabRow.kt#jmzs0o");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j3 = j;
                int i3 = $composer3.changed(j3) ? 256 : 128;
                $dirty2 |= i3;
            } else {
                j3 = j;
            }
            $dirty2 |= i3;
        } else {
            j3 = j;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j4 = j2;
                int i4 = $composer3.changed(j4) ? 2048 : 1024;
                $dirty2 |= i4;
            } else {
                j4 = j2;
            }
            $dirty2 |= i4;
        } else {
            j4 = j2;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            function3RememberComposableLambda = function3;
        } else if (($changed & 24576) == 0) {
            function3RememberComposableLambda = function3;
            $dirty2 |= $composer3.changedInstance(function3RememberComposableLambda) ? 16384 : 8192;
        } else {
            function3RememberComposableLambda = function3;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function2;
        } else if ((196608 & $changed) == 0) {
            function5 = function2;
            $dirty2 |= $composer3.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function2;
        }
        if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function4) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute(($dirty2 & 599187) != 599186, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "137@6739L6,138@6788L32,140@6927L125");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty2 &= -7169;
                }
                modifier4 = modifier;
                $dirty = $dirty2;
                function8 = function3RememberComposableLambda;
                function2M1793getLambda$1216856915$material = function5;
                j7 = j3;
                j8 = j4;
            } else {
                if (i2 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) == 0) {
                    primarySurface = j3;
                } else {
                    primarySurface = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty2 &= -897;
                }
                if ((i & 8) == 0) {
                    jM1783contentColorForek8zF_U = j4;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(primarySurface, $composer3, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i5 != 0) {
                    function3RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1896966245, true, new Function3() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return TabRowKt.TabRow_pAZo6Ak$lambda$0(selectedTabIndex, (List) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54);
                }
                if (i6 != 0) {
                    $dirty = $dirty2;
                    function8 = function3RememberComposableLambda;
                    j7 = primarySurface;
                    j8 = jM1783contentColorForek8zF_U;
                    function2M1793getLambda$1216856915$material = ComposableSingletons$TabRowKt.INSTANCE.m1793getLambda$1216856915$material();
                    modifier4 = modifier3;
                } else {
                    $dirty = $dirty2;
                    function8 = function3RememberComposableLambda;
                    j7 = primarySurface;
                    modifier4 = modifier3;
                    function2M1793getLambda$1216856915$material = function5;
                    j8 = jM1783contentColorForek8zF_U;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(113221600, $dirty, -1, "androidx.compose.material.TabRow (TabRow.kt:145)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1954SurfaceFjzlyU(SelectableGroupKt.selectableGroup(modifier4), null, j7, j8, null, 0.0f, ComposableLambdaKt.rememberComposableLambda(-638448612, true, new Function2() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.TabRow_pAZo6Ak$lambda$1(function4, function2M1793getLambda$1216856915$material, function8, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, ($dirty & 896) | 1572864 | ($dirty & 7168), 50);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            function6 = function8;
            function7 = function2M1793getLambda$1216856915$material;
            j5 = j7;
            j6 = j8;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            j5 = j3;
            j6 = j4;
            modifier2 = modifier;
            function6 = function3RememberComposableLambda;
            function7 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.TabRow_pAZo6Ak$lambda$2(selectedTabIndex, modifier2, j5, j6, function6, function7, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TabRow_pAZo6Ak$lambda$1(final Function2 $tabs, final Function2 $divider, final Function3 $indicator, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C151@7388L1334,151@7346L1376:TabRow.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-638448612, $changed, -1, "androidx.compose.material.TabRow.<anonymous> (TabRow.kt:151)");
            }
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 631210194, "CC(remember):TabRow.kt#9igjgp");
            boolean invalid$iv = $composer.changed($tabs) | $composer.changed($divider) | $composer.changed($indicator);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function2() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TabRowKt.TabRow_pAZo6Ak$lambda$1$0$0($tabs, $divider, $indicator, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            SubcomposeLayoutKt.SubcomposeLayout(modifierFillMaxWidth$default, (Function2) it$iv, $composer, 6, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult TabRow_pAZo6Ak$lambda$1$0$0(Function2 $tabs, final Function2 $divider, final Function3 $indicator, final SubcomposeMeasureScope $this$SubcomposeLayout, final Constraints constraints) {
        Object maxElem$iv;
        final int tabRowWidth = Constraints.m7858getMaxWidthimpl(constraints.getValue());
        List<Measurable> listSubcompose = $this$SubcomposeLayout.subcompose(TabSlots.Tabs, $tabs);
        int tabCount = listSubcompose.size();
        final int tabWidth = tabRowWidth / tabCount;
        List<Measurable> list = listSubcompose;
        int $i$f$fastMap = 0;
        List target$iv = new ArrayList(list.size());
        int index$iv$iv = 0;
        int size = list.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = list.get(index$iv$iv);
            Measurable it = (Measurable) item$iv$iv;
            long value = constraints.getValue();
            target$iv.add(it.mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(value) : tabWidth, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(value) : tabWidth, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(value) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(value) : 0)));
            index$iv$iv++;
            $i$f$fastMap = $i$f$fastMap;
            list = list;
        }
        final List tabPlaceables = target$iv;
        if (!tabPlaceables.isEmpty()) {
            maxElem$iv = tabPlaceables.get(0);
            Placeable it2 = (Placeable) maxElem$iv;
            int maxValue$iv = it2.getHeight();
            int i$iv = 1;
            int lastIndex = CollectionsKt.getLastIndex(tabPlaceables);
            if (1 <= lastIndex) {
                while (true) {
                    Object e$iv = tabPlaceables.get(i$iv);
                    Placeable it3 = (Placeable) e$iv;
                    int height = it3.getHeight();
                    if (maxValue$iv < height) {
                        maxElem$iv = e$iv;
                        maxValue$iv = height;
                    }
                    if (i$iv == lastIndex) {
                        break;
                    }
                    i$iv++;
                }
            }
        } else {
            maxElem$iv = null;
        }
        Placeable placeable = (Placeable) maxElem$iv;
        int tabRowHeight = placeable != null ? placeable.getHeight() : 0;
        ArrayList arrayList = new ArrayList(tabCount);
        for (int i = 0; i < tabCount; i++) {
            int index = i;
            float arg0$iv = $this$SubcomposeLayout.mo403toDpu2uoSUM(tabWidth);
            float fM7905constructorimpl = Dp.m7905constructorimpl(index * arg0$iv);
            float arg0$iv2 = $this$SubcomposeLayout.mo403toDpu2uoSUM(tabWidth);
            arrayList.add(new TabPosition(fM7905constructorimpl, arg0$iv2, null));
        }
        final ArrayList tabPositions = arrayList;
        final int tabRowHeight2 = tabRowHeight;
        return MeasureScope.layout$default($this$SubcomposeLayout, tabRowWidth, tabRowHeight2, null, new Function1() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TabRowKt.TabRow_pAZo6Ak$lambda$1$0$0$3(tabPlaceables, $this$SubcomposeLayout, $divider, tabWidth, constraints, tabRowHeight2, $indicator, tabPositions, tabRowWidth, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TabRow_pAZo6Ak$lambda$1$0$0$3(List $tabPlaceables, SubcomposeMeasureScope $this_SubcomposeLayout, Function2 $divider, int $tabWidth, Constraints $constraints, int $tabRowHeight, final Function3 $indicator, final List $tabPositions, int $tabRowWidth, Placeable.PlacementScope $this$layout) {
        int size = $tabPlaceables.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $tabPlaceables.get(index$iv);
            int index = index$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, (Placeable) item$iv, index * $tabWidth, 0, 0.0f, 4, null);
        }
        List<Measurable> listSubcompose = $this_SubcomposeLayout.subcompose(TabSlots.Divider, $divider);
        int size2 = listSubcompose.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = listSubcompose.get(index$iv2);
            Measurable it = (Measurable) item$iv2;
            long value = $constraints.getValue();
            Placeable placeable = it.mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(value) : 0, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(value) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(value) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(value) : 0));
            Placeable.PlacementScope.placeRelative$default($this$layout, placeable, 0, $tabRowHeight - placeable.getHeight(), 0.0f, 4, null);
        }
        List<Measurable> listSubcompose2 = $this_SubcomposeLayout.subcompose(TabSlots.Indicator, ComposableLambdaKt.composableLambdaInstance(-220665376, true, new Function2() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return TabRowKt.TabRow_pAZo6Ak$lambda$1$0$0$3$2($indicator, $tabPositions, (Composer) obj, ((Integer) obj2).intValue());
            }
        }));
        int size3 = listSubcompose2.size();
        for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
            Object item$iv3 = listSubcompose2.get(index$iv3);
            Measurable it2 = (Measurable) item$iv3;
            Placeable.PlacementScope.placeRelative$default($this$layout, it2.mo6541measureBRTryo0(Constraints.INSTANCE.m7868fixedJhjzzOo($tabRowWidth, $tabRowHeight)), 0, 0, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TabRow_pAZo6Ak$lambda$1$0$0$3$2(Function3 $indicator, List $tabPositions, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C176@8515L23:TabRow.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-220665376, $changed, -1, "androidx.compose.material.TabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:176)");
            }
            $indicator.invoke($tabPositions, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit ScrollableTabRow_sKfQg0A$lambda$0(int $selectedTabIndex, List tabPositions, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(tabPositions)232@11660L70:TabRow.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-233732148, $changed, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:232)");
        }
        TabRowDefaults.INSTANCE.m1977Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, (TabPosition) tabPositions.get($selectedTabIndex)), 0.0f, 0L, $composer, 3072, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: ScrollableTabRow-sKfQg0A, reason: not valid java name */
    public static final void m1984ScrollableTabRowsKfQg0A(final int selectedTabIndex, Modifier modifier, long j, long j2, float f, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        long j3;
        long j4;
        float f2;
        Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function5;
        Composer $composer2;
        final Modifier modifier2;
        final long j5;
        final long j6;
        final float f3;
        final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function6;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        Modifier.Companion modifier3;
        long primarySurface;
        long jM1783contentColorForek8zF_U;
        float fM1980getScrollableTabRowPaddingD9Ej5fM;
        ComposableLambda composableLambdaRememberComposableLambda;
        Function2<? super Composer, ? super Integer, Unit> function2M1794getLambda$222041980$material;
        Modifier modifier4;
        long j7;
        int i2;
        long j8;
        Composer $composer3 = $composer.startRestartGroup(-1291546575);
        ComposerKt.sourceInformation($composer3, "C(ScrollableTabRow)N(selectedTabIndex,modifier,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,edgePadding:c#ui.unit.Dp,indicator,divider,tabs)237@11975L2929,237@11892L3012:TabRow.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                j3 = j;
                int i4 = $composer3.changed(j3) ? 256 : 128;
                $dirty |= i4;
            } else {
                j3 = j;
            }
            $dirty |= i4;
        } else {
            j3 = j;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                j4 = j2;
                int i5 = $composer3.changed(j4) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                j4 = j2;
            }
            $dirty |= i5;
        } else {
            j4 = j2;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            f2 = f;
        } else if (($changed & 24576) == 0) {
            f2 = f;
            $dirty |= $composer3.changed(f2) ? 16384 : 8192;
        } else {
            f2 = f;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
        } else if ((196608 & $changed) == 0) {
            function5 = function3;
            $dirty |= $composer3.changedInstance(function5) ? 131072 : 65536;
        } else {
            function5 = function3;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute(($dirty & 4793491) != 4793490, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "227@11365L6,228@11414L32,231@11615L125");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                function2M1794getLambda$222041980$material = function2;
                i2 = -1291546575;
                j8 = j4;
                fM1980getScrollableTabRowPaddingD9Ej5fM = f2;
                composableLambdaRememberComposableLambda = function5;
                modifier4 = modifier;
                j7 = j3;
            } else {
                if (i3 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) == 0) {
                    primarySurface = j3;
                } else {
                    primarySurface = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty &= -897;
                }
                if ((i & 8) == 0) {
                    jM1783contentColorForek8zF_U = j4;
                } else {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(primarySurface, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i6 == 0) {
                    fM1980getScrollableTabRowPaddingD9Ej5fM = f2;
                } else {
                    fM1980getScrollableTabRowPaddingD9Ej5fM = TabRowDefaults.INSTANCE.m1980getScrollableTabRowPaddingD9Ej5fM();
                }
                if (i7 == 0) {
                    composableLambdaRememberComposableLambda = function5;
                } else {
                    composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-233732148, true, new Function3() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return TabRowKt.ScrollableTabRow_sKfQg0A$lambda$0(selectedTabIndex, (List) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54);
                }
                if (i8 != 0) {
                    function2M1794getLambda$222041980$material = ComposableSingletons$TabRowKt.INSTANCE.m1794getLambda$222041980$material();
                    modifier4 = modifier3;
                    j7 = primarySurface;
                    i2 = -1291546575;
                    j8 = jM1783contentColorForek8zF_U;
                } else {
                    function2M1794getLambda$222041980$material = function2;
                    modifier4 = modifier3;
                    j7 = primarySurface;
                    i2 = -1291546575;
                    j8 = jM1783contentColorForek8zF_U;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material.ScrollableTabRow (TabRow.kt:236)");
            }
            final float f4 = fM1980getScrollableTabRowPaddingD9Ej5fM;
            final Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function8 = composableLambdaRememberComposableLambda;
            final Function2<? super Composer, ? super Integer, Unit> function9 = function2M1794getLambda$222041980$material;
            $composer2 = $composer3;
            SurfaceKt.m1954SurfaceFjzlyU(modifier4, null, j7, j8, null, 0.0f, ComposableLambdaKt.rememberComposableLambda(-1575164555, true, new Function2() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.ScrollableTabRow_sKfQg0A$lambda$1(f4, function4, function9, function8, selectedTabIndex, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | 1572864 | ($dirty & 896) | ($dirty & 7168), 50);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f3 = fM1980getScrollableTabRowPaddingD9Ej5fM;
            function6 = composableLambdaRememberComposableLambda;
            function7 = function2M1794getLambda$222041980$material;
            modifier2 = modifier4;
            j5 = j7;
            j6 = j8;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            j5 = j3;
            j6 = j4;
            f3 = f2;
            function6 = function5;
            function7 = function2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowKt.ScrollableTabRow_sKfQg0A$lambda$2(selectedTabIndex, modifier2, j5, j6, f3, function6, function7, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit ScrollableTabRow_sKfQg0A$lambda$1(final float f, final Function2 $tabs, final Function2 $divider, final Function3 $indicator, final int $selectedTabIndex, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C238@12003L21,239@12054L24,241@12123L147,250@12521L2377,244@12279L2619:TabRow.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1575164555, $changed, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:238)");
            }
            ScrollState scrollState = ScrollKt.rememberScrollState(0, $composer, 0, 1);
            ComposerKt.sourceInformationMarkerStart($composer, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object it$iv$iv = $composer.rememberedValue();
            if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer);
                $composer.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            CoroutineScope coroutineScope = (CoroutineScope) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, -1904644280, "CC(remember):TabRow.kt#9igjgp");
            boolean invalid$iv = $composer.changed(scrollState) | $composer.changed(coroutineScope);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new ScrollableTabData(scrollState, coroutineScope);
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            final ScrollableTabData scrollableTabData = (ScrollableTabData) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierClipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), scrollState, false, null, false, 14, null)));
            ComposerKt.sourceInformationMarkerStart($composer, -1904629314, "CC(remember):TabRow.kt#9igjgp");
            boolean invalid$iv2 = $composer.changed(f) | $composer.changed($tabs) | $composer.changed($divider) | $composer.changed($indicator) | $composer.changedInstance(scrollableTabData) | $composer.changed($selectedTabIndex);
            Object it$iv2 = $composer.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function2() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TabRowKt.ScrollableTabRow_sKfQg0A$lambda$1$1$0(f, $tabs, $divider, scrollableTabData, $selectedTabIndex, $indicator, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                    }
                };
                $composer.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            SubcomposeLayoutKt.SubcomposeLayout(modifierClipToBounds, (Function2) it$iv2, $composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult ScrollableTabRow_sKfQg0A$lambda$1$1$0(float f, Function2 $tabs, final Function2 $divider, final ScrollableTabData $scrollableTabData, final int $selectedTabIndex, final Function3 $indicator, final SubcomposeMeasureScope $this$SubcomposeLayout, final Constraints constraints) {
        int minTabWidth = $this$SubcomposeLayout.mo400roundToPx0680j_4(ScrollableTabRowMinimumTabWidth);
        int padding = $this$SubcomposeLayout.mo400roundToPx0680j_4(f);
        long value = constraints.getValue();
        long tabConstraints = Constraints.m7847copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(value) : minTabWidth, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(value) : 0, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(value) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(value) : 0);
        List<Measurable> listSubcompose = $this$SubcomposeLayout.subcompose(TabSlots.Tabs, $tabs);
        List target$iv = new ArrayList(listSubcompose.size());
        int index$iv$iv = 0;
        int size = listSubcompose.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = listSubcompose.get(index$iv$iv);
            target$iv.add(((Measurable) item$iv$iv).mo6541measureBRTryo0(tabConstraints));
            index$iv$iv++;
            listSubcompose = listSubcompose;
            padding = padding;
        }
        final int padding2 = padding;
        final List tabPlaceables = target$iv;
        final Ref.IntRef layoutWidth = new Ref.IntRef();
        layoutWidth.element = padding2 * 2;
        final Ref.IntRef layoutHeight = new Ref.IntRef();
        List $this$fastForEach$iv = tabPlaceables;
        int $i$f$fastForEach = 0;
        int index$iv = 0;
        int size2 = $this$fastForEach$iv.size();
        while (index$iv < size2) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            Placeable it = (Placeable) item$iv;
            List $this$fastForEach$iv2 = $this$fastForEach$iv;
            layoutWidth.element += it.getWidth();
            int i = layoutHeight.element;
            int $i$f$fastForEach2 = $i$f$fastForEach;
            int $i$f$fastForEach3 = it.getHeight();
            layoutHeight.element = Math.max(i, $i$f$fastForEach3);
            index$iv++;
            $this$fastForEach$iv = $this$fastForEach$iv2;
            $i$f$fastForEach = $i$f$fastForEach2;
        }
        int i2 = layoutWidth.element;
        int padding3 = layoutHeight.element;
        return MeasureScope.layout$default($this$SubcomposeLayout, i2, padding3, null, new Function1() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TabRowKt.ScrollableTabRow_sKfQg0A$lambda$1$1$0$2(padding2, tabPlaceables, $this$SubcomposeLayout, $divider, $scrollableTabData, $selectedTabIndex, constraints, layoutWidth, layoutHeight, $indicator, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScrollableTabRow_sKfQg0A$lambda$1$1$0$2(int $padding, List $tabPlaceables, SubcomposeMeasureScope $this_SubcomposeLayout, Function2 $divider, ScrollableTabData $scrollableTabData, int $selectedTabIndex, Constraints $constraints, Ref.IntRef $layoutWidth, Ref.IntRef $layoutHeight, final Function3 $indicator, Placeable.PlacementScope $this$layout) {
        final List tabPositions = new ArrayList();
        int size = $tabPlaceables.size();
        int left = $padding;
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $tabPlaceables.get(index$iv);
            Placeable it = (Placeable) item$iv;
            Placeable.PlacementScope.placeRelative$default($this$layout, it, left, 0, 0.0f, 4, null);
            tabPositions.add(new TabPosition($this_SubcomposeLayout.mo403toDpu2uoSUM(left), $this_SubcomposeLayout.mo403toDpu2uoSUM(it.getWidth()), null));
            left += it.getWidth();
        }
        List<Measurable> listSubcompose = $this_SubcomposeLayout.subcompose(TabSlots.Divider, $divider);
        int index$iv2 = 0;
        int size2 = listSubcompose.size();
        while (index$iv2 < size2) {
            Object item$iv2 = listSubcompose.get(index$iv2);
            Measurable it2 = (Measurable) item$iv2;
            long value = $constraints.getValue();
            Placeable placeable = it2.mo6541measureBRTryo0(Constraints.m7847copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m7860getMinWidthimpl(value) : $layoutWidth.element, (11 & 2) != 0 ? Constraints.m7858getMaxWidthimpl(value) : $layoutWidth.element, (11 & 4) != 0 ? Constraints.m7859getMinHeightimpl(value) : 0, (11 & 8) != 0 ? Constraints.m7857getMaxHeightimpl(value) : 0));
            Placeable.PlacementScope.placeRelative$default($this$layout, placeable, 0, $layoutHeight.element - placeable.getHeight(), 0.0f, 4, null);
            index$iv2++;
            listSubcompose = listSubcompose;
        }
        List<Measurable> listSubcompose2 = $this_SubcomposeLayout.subcompose(TabSlots.Indicator, ComposableLambdaKt.composableLambdaInstance(-43203918, true, new Function2() { // from class: androidx.compose.material.TabRowKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return TabRowKt.ScrollableTabRow_sKfQg0A$lambda$1$1$0$2$2($indicator, tabPositions, (Composer) obj, ((Integer) obj2).intValue());
            }
        }));
        int size3 = listSubcompose2.size();
        for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
            Object item$iv3 = listSubcompose2.get(index$iv3);
            Measurable it3 = (Measurable) item$iv3;
            Placeable.PlacementScope.placeRelative$default($this$layout, it3.mo6541measureBRTryo0(Constraints.INSTANCE.m7868fixedJhjzzOo($layoutWidth.element, $layoutHeight.element)), 0, 0, 0.0f, 4, null);
        }
        $scrollableTabData.onLaidOut($this_SubcomposeLayout, $padding, tabPositions, $selectedTabIndex);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ScrollableTabRow_sKfQg0A$lambda$1$1$0$2$2(Function3 $indicator, List $tabPositions, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C292@14431L23:TabRow.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-43203918, $changed, -1, "androidx.compose.material.ScrollableTabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:292)");
            }
            $indicator.invoke($tabPositions, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
