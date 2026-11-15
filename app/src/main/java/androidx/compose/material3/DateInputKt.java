package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DateInput.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001an\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032#\u0010\u0004\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0001¢\u0006\u0002\u0010\u0015\u001a\u009f\u0001\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001b¢\u0006\u0002\b\u001c2\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001b¢\u0006\u0002\b\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0001¢\u0006\u0004\b'\u0010(\"\u0014\u0010)\u001a\u00020*X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,\"\u0010\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/¨\u00060²\u0006\n\u00101\u001a\u000202X\u008a\u008e\u0002"}, d2 = {"DateInputContent", "", "selectedDateMillis", "", "onDateSelectionChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "dateInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "colors", "Landroidx/compose/material3/DatePickerColors;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;I)V", "DateInputTextField", "modifier", "Landroidx/compose/ui/Modifier;", "initialDateMillis", MetadataField.LABEL, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "inputIdentifier", "Landroidx/compose/material3/InputIdentifier;", "dateInputValidator", "Landroidx/compose/material3/DateInputValidator;", "dateInputFormat", "Landroidx/compose/material3/internal/DateInputFormat;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DateInputTextField-xJ3Ic0Y", "(Landroidx/compose/ui/Modifier;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ILandroidx/compose/material3/DateInputValidator;Landroidx/compose/material3/internal/DateInputFormat;Ljava/util/Locale;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "InputTextFieldPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getInputTextFieldPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "InputTextNonErroneousBottomPadding", "Landroidx/compose/ui/unit/Dp;", "F", "material3", "text", "Landroidx/compose/ui/text/input/TextFieldValue;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DateInputKt {
    private static final PaddingValues InputTextFieldPadding = PaddingKt.m816PaddingValuesa9UjIt4$default(Dp.m7905constructorimpl(24), Dp.m7905constructorimpl(10), Dp.m7905constructorimpl(24), 0.0f, 8, null);
    private static final float InputTextNonErroneousBottomPadding = Dp.m7905constructorimpl(16);

    static final Unit DateInputContent$lambda$3(Long l, Function1 function1, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, FocusRequester focusRequester, int i, Composer composer, int i2) {
        DateInputContent(l, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DateInputTextField_xJ3Ic0Y$lambda$17(Modifier modifier, Long l, Function1 function1, CalendarModel calendarModel, Function2 function2, Function2 function3, int i, DateInputValidator dateInputValidator, DateInputFormat dateInputFormat, Locale locale, DatePickerColors datePickerColors, FocusRequester focusRequester, int i2, int i3, Composer composer, int i4) {
        m2315DateInputTextFieldxJ3Ic0Y(modifier, l, function1, calendarModel, function2, function3, i, dateInputValidator, dateInputFormat, locale, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [boolean, int] */
    public static final void DateInputContent(final Long selectedDateMillis, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange yearRange, final DatePickerFormatter dateFormatter, final SelectableDates selectableDates, final DatePickerColors colors, final FocusRequester focusRequester, Composer $composer, final int $changed) {
        Function1<? super Long, Unit> function2;
        IntRange intRange;
        SelectableDates selectableDates2;
        DatePickerColors datePickerColors;
        Composer $composer2;
        int $dirty;
        ?? r3;
        Composer $composer3 = $composer.startRestartGroup(-432341251);
        ComposerKt.sourceInformation($composer3, "C(DateInputContent)N(selectedDateMillis,onDateSelectionChange,calendarModel,yearRange,dateFormatter,selectableDates,colors,focusRequester)70@2926L89,71@3043L45,72@3123L44,73@3201L45,75@3284L552,88@3925L42,92@4122L165,98@4311L62,89@3972L951:DateInput.kt#uh7d8r");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedDateMillis) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            function2 = function1;
            $dirty2 |= $composer3.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = function1;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(calendarModel) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            intRange = yearRange;
            $dirty2 |= $composer3.changedInstance(intRange) ? 2048 : 1024;
        } else {
            intRange = yearRange;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= ($changed & 32768) == 0 ? $composer3.changed(dateFormatter) : $composer3.changedInstance(dateFormatter) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            selectableDates2 = selectableDates;
            $dirty2 |= $composer3.changed(selectableDates2) ? 131072 : 65536;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((1572864 & $changed) == 0) {
            datePickerColors = colors;
            $dirty2 |= $composer3.changed(datePickerColors) ? 1048576 : 524288;
        } else {
            datePickerColors = colors;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer3.changed(focusRequester) ? 8388608 : 4194304;
        }
        if (!$composer3.shouldExecute((4793491 & $dirty2) != 4793490, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-432341251, $dirty2, -1, "androidx.compose.material3.DateInputContent (DateInput.kt:67)");
            }
            Locale locale = calendarModel.getLocale();
            ComposerKt.sourceInformationMarkerStart($composer3, 1131899414, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(locale);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = calendarModel.getDateInputFormat(calendarModel.getLocale());
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            DateInputFormat dateInputFormat = (DateInputFormat) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Strings.Companion companion = Strings.INSTANCE;
            String errorDatePattern = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_date_input_invalid_for_pattern), $composer3, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            String errorDateOutOfYearRange = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_date_input_invalid_year_range), $composer3, 0);
            Strings.Companion companion3 = Strings.INSTANCE;
            String errorInvalidNotAllowed = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_date_input_invalid_not_allowed), $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, 1131911333, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv2 = $composer3.changed(dateInputFormat) | ((57344 & $dirty2) == 16384 || (($dirty2 & 32768) != 0 && $composer3.changed(dateFormatter)));
            Object value$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                $dirty = $dirty2;
                r3 = 1;
                value$iv2 = new DateInputValidator(intRange, selectableDates2, dateInputFormat, dateFormatter, errorDatePattern, errorDateOutOfYearRange, errorInvalidNotAllowed, "");
                $composer3.updateRememberedValue(value$iv2);
            } else {
                $dirty = $dirty2;
                r3 = 1;
            }
            DateInputValidator dateInputValidator = (DateInputValidator) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String pattern = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(pattern, "toUpperCase(...)");
            Strings.Companion companion4 = Strings.INSTANCE;
            String labelText = Strings_androidKt.m3448getString2EP1pXo(Strings.m3369constructorimpl(R.string.m3c_date_input_label), $composer3, 0);
            Modifier modifierPadding = PaddingKt.padding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, r3, null), InputTextFieldPadding);
            int iM2551getSingleDateInputJ2x2o4M = InputIdentifier.INSTANCE.m2551getSingleDateInputJ2x2o4M();
            dateInputValidator.setCurrentStartDateMillis(selectedDateMillis);
            $composer2 = $composer3;
            m2315DateInputTextFieldxJ3Ic0Y(modifierPadding, selectedDateMillis, function2, calendarModel, ComposableLambdaKt.rememberComposableLambda(-752164549, r3, new AnonymousClass2(labelText, pattern), $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-1179434278, r3, new AnonymousClass3(pattern), $composer3, 54), iM2551getSingleDateInputJ2x2o4M, dateInputValidator, dateInputFormat, calendarModel.getLocale(), datePickerColors, focusRequester, $composer2, (($dirty << 3) & 112) | 1794054 | (($dirty << 3) & 896) | (($dirty << 3) & 7168), (($dirty >> 18) & 14) | (($dirty >> 18) & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DateInputKt.DateInputContent$lambda$3(selectedDateMillis, function1, calendarModel, yearRange, dateFormatter, selectableDates, colors, focusRequester, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$2, reason: invalid class name */
    /* JADX INFO: compiled from: DateInput.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ String $labelText;
        final /* synthetic */ String $pattern;

        AnonymousClass2(String str, String str2) {
            this.$labelText = str;
            this.$pattern = str2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C95@4215L47,93@4136L141:DateInput.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-752164549, $changed, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:93)");
            }
            String str = this.$labelText;
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1275622838, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv = $composer.changed(this.$labelText) | $composer.changed(this.$pattern);
            final String str2 = this.$labelText;
            final String str3 = this.$pattern;
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DateInputKt.AnonymousClass2.invoke$lambda$1$lambda$0(str2, str3, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.m3072TextNvy7gAk(str, SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262140);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        static final Unit invoke$lambda$1$lambda$0(String $labelText, String $pattern, SemanticsPropertyReceiver $this$semantics) {
            SemanticsPropertiesKt.setContentDescription($this$semantics, $labelText + ", " + $pattern);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$3, reason: invalid class name */
    /* JADX INFO: compiled from: DateInput.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass3 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ String $pattern;

        AnonymousClass3(String str) {
            this.$pattern = str;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C98@4368L2,98@4313L58:DateInput.kt#uh7d8r");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1179434278, $changed, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:98)");
            }
            String str = this.$pattern;
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 435026812, "CC(remember):DateInput.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.DateInputKt$DateInputContent$3$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextKt.m3072TextNvy7gAk(str, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) it$iv), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262140);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:129:0x021b  */
    /* JADX WARN: Code duplicated, block: B:130:0x021e  */
    /* JADX WARN: Code duplicated, block: B:133:0x025c  */
    /* JADX WARN: Code duplicated, block: B:134:0x025e  */
    /* JADX WARN: Code duplicated, block: B:137:0x026f  */
    /* JADX WARN: Code duplicated, block: B:138:0x0271  */
    /* JADX WARN: Code duplicated, block: B:141:0x0283  */
    /* JADX WARN: Code duplicated, block: B:142:0x0285  */
    /* JADX WARN: Code duplicated, block: B:145:0x028d  */
    /* JADX WARN: Code duplicated, block: B:146:0x028f  */
    /* JADX WARN: Code duplicated, block: B:152:0x02b2  */
    /* JADX WARN: Code duplicated, block: B:158:0x0312  */
    /* JADX WARN: Code duplicated, block: B:161:0x032b  */
    /* JADX WARN: Code duplicated, block: B:162:0x0334  */
    /* JADX WARN: Code duplicated, block: B:165:0x03d1  */
    /* JADX WARN: Code duplicated, block: B:166:0x03d3  */
    /* JADX WARN: Code duplicated, block: B:172:0x03e9  */
    /* JADX WARN: Code duplicated, block: B:175:0x040e  */
    /* JADX WARN: Code duplicated, block: B:176:0x0410  */
    /* JADX WARN: Code duplicated, block: B:179:0x041d  */
    /* JADX WARN: Code duplicated, block: B:180:0x0420  */
    /* JADX WARN: Code duplicated, block: B:186:0x0443  */
    /* JADX WARN: Code duplicated, block: B:189:0x0469  */
    /* JADX INFO: renamed from: DateInputTextField-xJ3Ic0Y, reason: not valid java name */
    public static final void m2315DateInputTextFieldxJ3Ic0Y(final Modifier modifier, Long initialDateMillis, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final int inputIdentifier, final DateInputValidator dateInputValidator, final DateInputFormat dateInputFormat, final Locale locale, final DatePickerColors colors, final FocusRequester focusRequester, Composer $composer, final int $changed, final int $changed1) {
        Modifier modifier2;
        int i;
        DateInputValidator dateInputValidator2;
        Long l;
        Composer $composer2;
        Object value$iv;
        MutableState text$delegate;
        MutableState text$delegate2;
        final MutableState errorText;
        float fM7905constructorimpl;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean invalid$iv;
        Object value$iv2;
        final DateInputFormat dateInputFormat2;
        final MutableState errorText2;
        boolean invalid$iv2;
        Object it$iv;
        Modifier.Companion companionFocusRequester;
        boolean invalid$iv3;
        Object it$iv2;
        boolean z5;
        boolean z6;
        boolean invalid$iv4;
        Object it$iv3;
        final CalendarModel calendarModel2 = calendarModel;
        final Locale locale2 = locale;
        Composer $composer3 = $composer.startRestartGroup(1456309913);
        ComposerKt.sourceInformation($composer3, "C(DateInputTextField)N(modifier,initialDateMillis,onDateSelectionChange,calendarModel,label,placeholder,inputIdentifier:c#material3.InputIdentifier,dateInputValidator,dateInputFormat,locale,colors,focusRequester)130@5481L36,130@5429L88,132@5569L715,132@5546L738,164@6980L1604,205@8702L60,215@9086L59,162@6915L2641,228@9583L297,228@9562L318,237@9920L727,237@9886L761:DateInput.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(initialDateMillis) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(calendarModel2) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            i = inputIdentifier;
            $dirty |= $composer3.changed(i) ? 1048576 : 524288;
        } else {
            i = inputIdentifier;
        }
        if (($changed & 12582912) == 0) {
            dateInputValidator2 = dateInputValidator;
            $dirty |= $composer3.changed(dateInputValidator2) ? 8388608 : 4194304;
        } else {
            dateInputValidator2 = dateInputValidator;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(dateInputFormat) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changedInstance(locale2) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(colors) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(focusRequester) ? 32 : 16;
        }
        if (!$composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty1 & 19) == 18) ? false : true, $dirty & 1)) {
            l = initialDateMillis;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1456309913, $dirty, $dirty1, "androidx.compose.material3.DateInputTextField (DateInput.kt:128)");
            }
            Object[] objArr = new Object[0];
            Saver<TextFieldValue, Object> saver = TextFieldValue.INSTANCE.getSaver();
            ComposerKt.sourceInformationMarkerStart($composer3, -645494339, "CC(remember):DateInput.kt#9igjgp");
            int $dirty2 = $dirty;
            Object it$iv4 = $composer3.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function0() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null), null, 2, null);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            MutableState text$delegate3 = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (Function0) value$iv, $composer3, 384);
            Object[] objArr2 = {DateInputTextField_xJ3Ic0Y$lambda$6(text$delegate3)};
            ComposerKt.sourceInformationMarkerStart($composer3, -645490844, "CC(remember):DateInput.kt#9igjgp");
            boolean invalid$iv5 = $composer3.changed(text$delegate3) | (($dirty2 & 29360128) == 8388608) | $composer3.changedInstance(calendarModel2) | (($dirty2 & 234881024) == 67108864) | $composer3.changedInstance(locale2) | (($dirty2 & 3670016) == 1048576);
            Object value$iv3 = $composer3.rememberedValue();
            if (!invalid$iv5) {
                text$delegate = text$delegate3;
                if (value$iv3 != Composer.INSTANCE.getEmpty()) {
                    text$delegate2 = text$delegate;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                errorText = (MutableState) RememberSaveableKt.rememberSaveable(objArr2, (Function0) value$iv3, $composer3, 0);
                if (StringsKt.isBlank((CharSequence) errorText.getValue())) {
                    fM7905constructorimpl = InputTextNonErroneousBottomPadding;
                } else {
                    PaddingValues textFieldPadding = TextFieldDefaults.m3048supportingTextPaddinga9UjIt4$material3$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    float arg0$iv = InputTextNonErroneousBottomPadding;
                    float arg0$iv2 = textFieldPadding.getBottom();
                    float other$iv = textFieldPadding.getTop();
                    fM7905constructorimpl = Dp.m7905constructorimpl(arg0$iv - Dp.m7905constructorimpl(arg0$iv2 + other$iv));
                }
                float bottomPadding = fM7905constructorimpl;
                TextFieldValue textFieldValueDateInputTextField_xJ3Ic0Y$lambda$6 = DateInputTextField_xJ3Ic0Y$lambda$6(text$delegate2);
                ComposerKt.sourceInformationMarkerStart($composer3, -645444803, "CC(remember):DateInput.kt#9igjgp");
                if (($dirty2 & 234881024) == 67108864) {
                    z = true;
                } else {
                    z = false;
                }
                boolean zChanged = z | $composer3.changed(text$delegate2) | $composer3.changed(errorText);
                if (($dirty2 & 896) == 256) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                boolean zChangedInstance = zChanged | z2 | $composer3.changedInstance(calendarModel2) | $composer3.changedInstance(locale2);
                if (($dirty2 & 29360128) == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z7 = zChangedInstance | z3;
                if (($dirty2 & 3670016) == 1048576) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                invalid$iv = z7 | z4;
                Object it$iv5 = $composer3.rememberedValue();
                if (!invalid$iv || it$iv5 == Composer.INSTANCE.getEmpty()) {
                    final MutableState text$delegate4 = text$delegate2;
                    dateInputFormat2 = dateInputFormat;
                    value$iv2 = new Function1() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DateInputKt.DateInputTextField_xJ3Ic0Y$lambda$12$lambda$11(dateInputFormat2, errorText, function1, calendarModel2, locale2, dateInputValidator, inputIdentifier, text$delegate4, (TextFieldValue) obj);
                        }
                    };
                    errorText2 = errorText;
                    calendarModel2 = calendarModel2;
                    locale2 = locale2;
                    text$delegate2 = text$delegate4;
                    $composer3.updateRememberedValue(value$iv2);
                } else {
                    errorText2 = errorText;
                    value$iv2 = it$iv5;
                    dateInputFormat2 = dateInputFormat;
                }
                Function1 function4 = (Function1) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(modifier2, 0.0f, 0.0f, 0.0f, bottomPadding, 7, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -645391243, "CC(remember):DateInput.kt#9igjgp");
                invalid$iv2 = $composer3.changed(errorText2);
                it$iv = $composer3.rememberedValue();
                if (!invalid$iv2 || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv4 = new Function1() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return DateInputKt.DateInputTextField_xJ3Ic0Y$lambda$14$lambda$13(errorText2, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    $composer3.updateRememberedValue(value$iv4);
                    it$iv = value$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierM823paddingqDBjuR0$default, false, (Function1) it$iv, 1, null);
                if (focusRequester != null) {
                    companionFocusRequester = FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester);
                } else {
                    companionFocusRequester = Modifier.INSTANCE;
                }
                OutlinedTextFieldKt.OutlinedTextField(textFieldValueDateInputTextField_xJ3Ic0Y$lambda$6, (Function1<? super TextFieldValue, Unit>) function4, modifierSemantics$default.then(companionFocusRequester), false, false, (TextStyle) null, function2, function3, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-357881838, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$3
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        Composer composer = $composer4;
                        ComposerKt.sourceInformation(composer, "C:DateInput.kt#uh7d8r");
                        if (!composer.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-357881838, $changed2, -1, "androidx.compose.material3.DateInputTextField.<anonymous> (DateInput.kt:215)");
                        }
                        if (StringsKt.isBlank(errorText2.getValue())) {
                            composer.startReplaceGroup(-1548950640);
                        } else {
                            composer.startReplaceGroup(-327061465);
                            ComposerKt.sourceInformation(composer, "215@9122L21");
                            TextKt.m3072TextNvy7gAk(errorText2.getValue(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer4, 0, 0, 262142);
                            composer = $composer4;
                        }
                        composer.endReplaceGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54), !StringsKt.isBlank((CharSequence) errorText2.getValue()), (VisualTransformation) new DateVisualTransformation(dateInputFormat2), new KeyboardOptions(0, (Boolean) false, KeyboardType.INSTANCE.m7566getNumberPjHm6EE(), ImeAction.INSTANCE.m7510getDoneeUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 113, (DefaultConstructorMarker) null), (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, colors.getDateTextFieldColors(), $composer3, (($dirty2 << 6) & 3670016) | (($dirty2 << 6) & 29360128), 12779904, 0, 4001592);
                $composer2 = $composer3;
                Unit unit = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -645362814, "CC(remember):DateInput.kt#9igjgp");
                if (($dirty1 & 112) == 32) {
                    invalid$iv3 = true;
                } else {
                    invalid$iv3 = false;
                }
                it$iv2 = $composer2.rememberedValue();
                if (!invalid$iv3 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv5 = (Function2) new DateInputKt$DateInputTextField$4$1(focusRequester, null);
                    $composer2.updateRememberedValue(value$iv5);
                    it$iv2 = value$iv5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, -645351600, "CC(remember):DateInput.kt#9igjgp");
                if (($dirty2 & 112) == 32) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                boolean zChangedInstance2 = z5 | $composer2.changedInstance(calendarModel2);
                if (($dirty2 & 234881024) == 67108864) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                invalid$iv4 = zChangedInstance2 | z6 | $composer2.changedInstance(locale2) | $composer2.changed(text$delegate2);
                it$iv3 = $composer2.rememberedValue();
                if (!invalid$iv4 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    l = initialDateMillis;
                    Object value$iv6 = (Function2) new DateInputKt$DateInputTextField$5$1(l, calendarModel2, dateInputFormat2, locale, text$delegate2, null);
                    $composer2.updateRememberedValue(value$iv6);
                    it$iv3 = value$iv6;
                } else {
                    l = initialDateMillis;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(l, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv3, $composer2, ($dirty2 >> 3) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                text$delegate = text$delegate3;
            }
            final int i2 = i;
            final MutableState text$delegate5 = text$delegate;
            final DateInputValidator dateInputValidator3 = dateInputValidator2;
            text$delegate2 = text$delegate5;
            value$iv3 = new Function0() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DateInputKt.DateInputTextField_xJ3Ic0Y$lambda$9$lambda$8(dateInputValidator3, calendarModel2, dateInputFormat, locale2, i2, text$delegate5);
                }
            };
            $composer3.updateRememberedValue(value$iv3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            errorText = (MutableState) RememberSaveableKt.rememberSaveable(objArr2, (Function0) value$iv3, $composer3, 0);
            if (StringsKt.isBlank((CharSequence) errorText.getValue())) {
                fM7905constructorimpl = InputTextNonErroneousBottomPadding;
            } else {
                PaddingValues textFieldPadding2 = TextFieldDefaults.m3048supportingTextPaddinga9UjIt4$material3$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                float arg0$iv3 = InputTextNonErroneousBottomPadding;
                float arg0$iv4 = textFieldPadding2.getBottom();
                float other$iv2 = textFieldPadding2.getTop();
                fM7905constructorimpl = Dp.m7905constructorimpl(arg0$iv3 - Dp.m7905constructorimpl(arg0$iv4 + other$iv2));
            }
            float bottomPadding2 = fM7905constructorimpl;
            TextFieldValue textFieldValueDateInputTextField_xJ3Ic0Y$lambda$7 = DateInputTextField_xJ3Ic0Y$lambda$6(text$delegate2);
            ComposerKt.sourceInformationMarkerStart($composer3, -645444803, "CC(remember):DateInput.kt#9igjgp");
            if (($dirty2 & 234881024) == 67108864) {
                z = true;
            } else {
                z = false;
            }
            boolean zChanged2 = z | $composer3.changed(text$delegate2) | $composer3.changed(errorText);
            if (($dirty2 & 896) == 256) {
                z2 = true;
            } else {
                z2 = false;
            }
            boolean zChangedInstance3 = zChanged2 | z2 | $composer3.changedInstance(calendarModel2) | $composer3.changedInstance(locale2);
            if (($dirty2 & 29360128) == 8388608) {
                z3 = true;
            } else {
                z3 = false;
            }
            boolean z8 = zChangedInstance3 | z3;
            if (($dirty2 & 3670016) == 1048576) {
                z4 = true;
            } else {
                z4 = false;
            }
            invalid$iv = z8 | z4;
            Object it$iv6 = $composer3.rememberedValue();
            if (!invalid$iv) {
                final MutableState text$delegate6 = text$delegate2;
                dateInputFormat2 = dateInputFormat;
                value$iv2 = new Function1() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DateInputKt.DateInputTextField_xJ3Ic0Y$lambda$12$lambda$11(dateInputFormat2, errorText, function1, calendarModel2, locale2, dateInputValidator, inputIdentifier, text$delegate6, (TextFieldValue) obj);
                    }
                };
                errorText2 = errorText;
                calendarModel2 = calendarModel2;
                locale2 = locale2;
                text$delegate2 = text$delegate6;
                $composer3.updateRememberedValue(value$iv2);
            } else {
                final MutableState text$delegate7 = text$delegate2;
                dateInputFormat2 = dateInputFormat;
                value$iv2 = new Function1() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DateInputKt.DateInputTextField_xJ3Ic0Y$lambda$12$lambda$11(dateInputFormat2, errorText, function1, calendarModel2, locale2, dateInputValidator, inputIdentifier, text$delegate7, (TextFieldValue) obj);
                    }
                };
                errorText2 = errorText;
                calendarModel2 = calendarModel2;
                locale2 = locale2;
                text$delegate2 = text$delegate7;
                $composer3.updateRememberedValue(value$iv2);
            }
            Function1 function5 = (Function1) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierM823paddingqDBjuR0$default2 = PaddingKt.m823paddingqDBjuR0$default(modifier2, 0.0f, 0.0f, 0.0f, bottomPadding2, 7, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -645391243, "CC(remember):DateInput.kt#9igjgp");
            invalid$iv2 = $composer3.changed(errorText2);
            it$iv = $composer3.rememberedValue();
            if (!invalid$iv2) {
                Object value$iv7 = new Function1() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DateInputKt.DateInputTextField_xJ3Ic0Y$lambda$14$lambda$13(errorText2, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv7);
                it$iv = value$iv7;
            } else {
                Object value$iv8 = new Function1() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DateInputKt.DateInputTextField_xJ3Ic0Y$lambda$14$lambda$13(errorText2, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer3.updateRememberedValue(value$iv8);
                it$iv = value$iv8;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifierSemantics$default2 = SemanticsModifierKt.semantics$default(modifierM823paddingqDBjuR0$default2, false, (Function1) it$iv, 1, null);
            if (focusRequester != null) {
                companionFocusRequester = FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester);
            } else {
                companionFocusRequester = Modifier.INSTANCE;
            }
            OutlinedTextFieldKt.OutlinedTextField(textFieldValueDateInputTextField_xJ3Ic0Y$lambda$7, (Function1<? super TextFieldValue, Unit>) function5, modifierSemantics$default2.then(companionFocusRequester), false, false, (TextStyle) null, function2, function3, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-357881838, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    Composer composer = $composer4;
                    ComposerKt.sourceInformation(composer, "C:DateInput.kt#uh7d8r");
                    if (!composer.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-357881838, $changed2, -1, "androidx.compose.material3.DateInputTextField.<anonymous> (DateInput.kt:215)");
                    }
                    if (StringsKt.isBlank(errorText2.getValue())) {
                        composer.startReplaceGroup(-1548950640);
                    } else {
                        composer.startReplaceGroup(-327061465);
                        ComposerKt.sourceInformation(composer, "215@9122L21");
                        TextKt.m3072TextNvy7gAk(errorText2.getValue(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer4, 0, 0, 262142);
                        composer = $composer4;
                    }
                    composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), !StringsKt.isBlank((CharSequence) errorText2.getValue()), (VisualTransformation) new DateVisualTransformation(dateInputFormat2), new KeyboardOptions(0, (Boolean) false, KeyboardType.INSTANCE.m7566getNumberPjHm6EE(), ImeAction.INSTANCE.m7510getDoneeUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 113, (DefaultConstructorMarker) null), (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, colors.getDateTextFieldColors(), $composer3, (($dirty2 << 6) & 3670016) | (($dirty2 << 6) & 29360128), 12779904, 0, 4001592);
            $composer2 = $composer3;
            Unit unit2 = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -645362814, "CC(remember):DateInput.kt#9igjgp");
            if (($dirty1 & 112) == 32) {
                invalid$iv3 = true;
            } else {
                invalid$iv3 = false;
            }
            it$iv2 = $composer2.rememberedValue();
            if (!invalid$iv3) {
                Object value$iv9 = (Function2) new DateInputKt$DateInputTextField$4$1(focusRequester, null);
                $composer2.updateRememberedValue(value$iv9);
                it$iv2 = value$iv9;
            } else {
                Object value$iv10 = (Function2) new DateInputKt$DateInputTextField$4$1(focusRequester, null);
                $composer2.updateRememberedValue(value$iv10);
                it$iv2 = value$iv10;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(unit2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer2, 6);
            ComposerKt.sourceInformationMarkerStart($composer2, -645351600, "CC(remember):DateInput.kt#9igjgp");
            if (($dirty2 & 112) == 32) {
                z5 = true;
            } else {
                z5 = false;
            }
            boolean zChangedInstance4 = z5 | $composer2.changedInstance(calendarModel2);
            if (($dirty2 & 234881024) == 67108864) {
                z6 = true;
            } else {
                z6 = false;
            }
            invalid$iv4 = zChangedInstance4 | z6 | $composer2.changedInstance(locale2) | $composer2.changed(text$delegate2);
            it$iv3 = $composer2.rememberedValue();
            if (!invalid$iv4) {
                l = initialDateMillis;
                Object value$iv11 = (Function2) new DateInputKt$DateInputTextField$5$1(l, calendarModel2, dateInputFormat2, locale, text$delegate2, null);
                $composer2.updateRememberedValue(value$iv11);
                it$iv3 = value$iv11;
            } else {
                l = initialDateMillis;
                Object value$iv12 = (Function2) new DateInputKt$DateInputTextField$5$1(l, calendarModel2, dateInputFormat2, locale, text$delegate2, null);
                $composer2.updateRememberedValue(value$iv12);
                it$iv3 = value$iv12;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(l, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv3, $composer2, ($dirty2 >> 3) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Long l2 = l;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DateInputKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DateInputKt.DateInputTextField_xJ3Ic0Y$lambda$17(modifier, l2, function1, calendarModel, function2, function3, inputIdentifier, dateInputValidator, dateInputFormat, locale, colors, focusRequester, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final TextFieldValue DateInputTextField_xJ3Ic0Y$lambda$6(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    static final MutableState DateInputTextField_xJ3Ic0Y$lambda$9$lambda$8(DateInputValidator $dateInputValidator, CalendarModel $calendarModel, DateInputFormat $dateInputFormat, Locale $locale, int $inputIdentifier, MutableState $text$delegate) {
        String initialError = "";
        if (DateInputTextField_xJ3Ic0Y$lambda$6($text$delegate).getText().length() > 0) {
            initialError = $dateInputValidator.m2316validateXivgLIo($calendarModel.parse(DateInputTextField_xJ3Ic0Y$lambda$6($text$delegate).getText(), $dateInputFormat.getPatternWithoutDelimiters(), $locale), $inputIdentifier, $locale);
        }
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(initialError, null, 2, null);
    }

    static final Unit DateInputTextField_xJ3Ic0Y$lambda$12$lambda$11(DateInputFormat $dateInputFormat, MutableState $errorText, Function1 $onDateSelectionChange, CalendarModel $calendarModel, Locale $locale, DateInputValidator $dateInputValidator, int $inputIdentifier, MutableState $text$delegate, TextFieldValue input) {
        boolean z;
        if (input.getText().length() <= $dateInputFormat.getPatternWithoutDelimiters().length()) {
            CharSequence $this$all$iv = input.getText();
            int i = 0;
            while (true) {
                if (i < $this$all$iv.length()) {
                    char element$iv = $this$all$iv.charAt(i);
                    if (!Character.isDigit(element$iv)) {
                        z = false;
                        break;
                    }
                    i++;
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                $text$delegate.setValue(input);
                String trimmedText = StringsKt.trim((CharSequence) input.getText()).toString();
                Long lValueOf = null;
                if ((trimmedText.length() == 0) || trimmedText.length() < $dateInputFormat.getPatternWithoutDelimiters().length()) {
                    $errorText.setValue("");
                    $onDateSelectionChange.invoke(null);
                } else {
                    CalendarDate parsedDate = $calendarModel.parse(trimmedText, $dateInputFormat.getPatternWithoutDelimiters(), $locale);
                    $errorText.setValue($dateInputValidator.m2316validateXivgLIo(parsedDate, $inputIdentifier, $locale));
                    if ((((CharSequence) $errorText.getValue()).length() == 0) && parsedDate != null) {
                        lValueOf = Long.valueOf(parsedDate.getUtcTimeMillis());
                    }
                    $onDateSelectionChange.invoke(lValueOf);
                }
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DateInputTextField_xJ3Ic0Y$lambda$14$lambda$13(MutableState $errorText, SemanticsPropertyReceiver $this$semantics) {
        if (!StringsKt.isBlank((CharSequence) $errorText.getValue())) {
            SemanticsPropertiesKt.error($this$semantics, (String) $errorText.getValue());
        }
        return Unit.INSTANCE;
    }

    public static final PaddingValues getInputTextFieldPadding() {
        return InputTextFieldPadding;
    }
}
