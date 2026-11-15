package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.CalendarMonth;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DatePicker.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class DatePickerKt$DatePickerContent$2$4$2 implements Function3<AnimatedVisibilityScope, Composer, Integer, Unit> {
    final /* synthetic */ CalendarModel $calendarModel;
    final /* synthetic */ DatePickerColors $colors;
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ CalendarMonth $displayedMonth;
    final /* synthetic */ long $displayedMonthMillis;
    final /* synthetic */ LazyListState $monthsListState;
    final /* synthetic */ SelectableDates $selectableDates;
    final /* synthetic */ MutableState<Boolean> $yearPickerVisible$delegate;
    final /* synthetic */ IntRange $yearRange;

    DatePickerKt$DatePickerContent$2$4$2(long j, MutableState<Boolean> mutableState, CoroutineScope coroutineScope, LazyListState lazyListState, IntRange intRange, CalendarMonth calendarMonth, SelectableDates selectableDates, CalendarModel calendarModel, DatePickerColors datePickerColors) {
        this.$displayedMonthMillis = j;
        this.$yearPickerVisible$delegate = mutableState;
        this.$coroutineScope = coroutineScope;
        this.$monthsListState = lazyListState;
        this.$yearRange = intRange;
        this.$displayedMonth = calendarMonth;
        this.$selectableDates = selectableDates;
        this.$calendarModel = calendarModel;
        this.$colors = datePickerColors;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
        invoke(animatedVisibilityScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x025e  */
    /* JADX WARN: Code duplicated, block: B:37:? A[RETURN, SYNTHETIC] */
    public final void invoke(AnimatedVisibilityScope $this$AnimatedVisibility, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Modifier modifier;
        SelectableDates selectableDates;
        IntRange intRange;
        ComposerKt.sourceInformation($composer, "C1632@75845L48,1633@75947L30,1633@75910L1888:DatePicker.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1193716082, $changed, -1, "androidx.compose.material3.DatePickerContent.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:1632)");
        }
        Strings.Companion companion = Strings.INSTANCE;
        final String yearsPaneTitle = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_date_picker_year_picker_pane_title), $composer, 0);
        Modifier.Companion companion2 = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 404900432, "CC(remember):DatePicker.kt#9igjgp");
        boolean invalid$iv = $composer.changed(yearsPaneTitle);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function1() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DatePickerKt$DatePickerContent$2$4$2.invoke$lambda$1$lambda$0(yearsPaneTitle, (SemanticsPropertyReceiver) obj);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifier$iv = SemanticsModifierKt.semantics$default(companion2, false, (Function1) it$iv, 1, null);
        long j = this.$displayedMonthMillis;
        final MutableState<Boolean> mutableState = this.$yearPickerVisible$delegate;
        final CoroutineScope coroutineScope = this.$coroutineScope;
        final LazyListState lazyListState = this.$monthsListState;
        final IntRange intRange2 = this.$yearRange;
        final CalendarMonth calendarMonth = this.$displayedMonth;
        SelectableDates selectableDates2 = this.$selectableDates;
        CalendarModel calendarModel = this.$calendarModel;
        DatePickerColors datePickerColors = this.$colors;
        ComposerKt.sourceInformationMarkerStart($composer, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
        Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getTop();
        Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
        MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
        int $changed$iv$iv = (0 << 3) & 112;
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
        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
        }
        Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
        int i = ($changed$iv$iv$iv >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -384672921, "C89@4556L9:Column.kt#2w3rfo");
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        int i2 = ((0 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, 872659188, "C1645@76721L767,1634@76001L1712,1662@77734L46:DatePicker.kt#uh7d8r");
        Modifier.Companion companion3 = Modifier.INSTANCE;
        float arg0$iv = DatePickerKt.getRecommendedSizeForAccessibility();
        float arg0$iv2 = Dp.m7905constructorimpl(7 * arg0$iv);
        float other$iv = DividerDefaults.INSTANCE.m2397getThicknessD9Ej5fM();
        Modifier modifierM821paddingVpY3zN4$default = PaddingKt.m821paddingVpY3zN4$default(SizeKt.m854requiredHeight3ABfNKs(companion3, Dp.m7905constructorimpl(arg0$iv2 - other$iv)), DatePickerKt.getDatePickerHorizontalPadding(), 0.0f, 2, null);
        ComposerKt.sourceInformationMarkerStart($composer, -1357300997, "CC(remember):DatePicker.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(mutableState) | $composer.changedInstance(coroutineScope) | $composer.changed(lazyListState) | $composer.changedInstance(intRange2) | $composer.changed(calendarMonth);
        Object it$iv2 = $composer.rememberedValue();
        if (!invalid$iv2) {
            modifier = modifierM821paddingVpY3zN4$default;
            if (it$iv2 != Composer.INSTANCE.getEmpty()) {
                intRange = intRange2;
                selectableDates = selectableDates2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            DatePickerKt.YearPicker(modifier, j, (Function1) it$iv2, selectableDates, calendarModel, intRange, datePickerColors, $composer, 6);
            DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), $composer, 0, 3);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        modifier = modifierM821paddingVpY3zN4$default;
        selectableDates = selectableDates2;
        Object value$iv2 = new Function1() { // from class: androidx.compose.material3.DatePickerKt$DatePickerContent$2$4$2$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DatePickerKt$DatePickerContent$2$4$2.invoke$lambda$4$lambda$3$lambda$2(coroutineScope, mutableState, lazyListState, intRange2, calendarMonth, ((Integer) obj).intValue());
            }
        };
        intRange = intRange2;
        $composer.updateRememberedValue(value$iv2);
        it$iv2 = value$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        DatePickerKt.YearPicker(modifier, j, (Function1) it$iv2, selectableDates, calendarModel, intRange, datePickerColors, $composer, 6);
        DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, datePickerColors.getDividerColor(), $composer, 0, 3);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    static final Unit invoke$lambda$1$lambda$0(String $yearsPaneTitle, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setPaneTitle($this$semantics, $yearsPaneTitle);
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$4$lambda$3$lambda$2(CoroutineScope $coroutineScope, MutableState $yearPickerVisible$delegate, LazyListState $monthsListState, IntRange $yearRange, CalendarMonth $displayedMonth, int year) {
        DatePickerKt.DatePickerContent$lambda$27($yearPickerVisible$delegate, !DatePickerKt.DatePickerContent$lambda$26($yearPickerVisible$delegate));
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new DatePickerKt$DatePickerContent$2$4$2$2$1$1$1($monthsListState, year, $yearRange, $displayedMonth, null), 3, null);
        return Unit.INSTANCE;
    }
}
