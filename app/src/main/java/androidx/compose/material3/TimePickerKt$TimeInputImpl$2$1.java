package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.tokens.TimeInputTokens;
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
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.intl.LocaleList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimePicker.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class TimePickerKt$TimeInputImpl$2$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ TimePickerColors $colors;
    final /* synthetic */ MutableState<TextFieldValue> $hourValue$delegate;
    final /* synthetic */ MutableState<TextFieldValue> $minuteValue$delegate;
    final /* synthetic */ TimePickerState $state;
    final /* synthetic */ Ref<Boolean> $userOverride;

    TimePickerKt$TimeInputImpl$2$1(MutableState<TextFieldValue> mutableState, TimePickerState timePickerState, Ref<Boolean> ref, TimePickerColors timePickerColors, MutableState<TextFieldValue> mutableState2) {
        this.$hourValue$delegate = mutableState;
        this.$state = timePickerState;
        this.$userOverride = ref;
        this.$colors = timePickerColors;
        this.$minuteValue$delegate = mutableState2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x02af  */
    /* JADX WARN: Code duplicated, block: B:56:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:62:0x034b  */
    /* JADX WARN: Code duplicated, block: B:65:0x039a  */
    /* JADX WARN: Code duplicated, block: B:68:? A[RETURN, SYNTHETIC] */
    public final void invoke(Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        int i;
        boolean invalid$iv;
        Object it$iv;
        boolean invalid$iv2;
        Object it$iv2;
        boolean invalid$iv3;
        Object it$iv3;
        ComposerKt.sourceInformation($composer, "C1032@43808L3645:TimePicker.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1306700887, $changed, -1, "androidx.compose.material3.TimeInputImpl.<anonymous>.<anonymous> (TimePicker.kt:1032)");
        }
        final MutableState<TextFieldValue> mutableState = this.$hourValue$delegate;
        final TimePickerState timePickerState = this.$state;
        final Ref<Boolean> ref = this.$userOverride;
        TimePickerColors timePickerColors = this.$colors;
        final MutableState<TextFieldValue> mutableState2 = this.$minuteValue$delegate;
        ComposerKt.sourceInformationMarkerStart($composer, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
        Modifier modifier$iv = Modifier.INSTANCE;
        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
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
        int i2 = ($changed$iv$iv$iv >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -407735110, "C101@5232L9:Row.kt#2w3rfo");
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        int i3 = ((0 >> 6) & 112) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, 587485559, "C1035@43926L529,1049@44532L508,1070@45481L52,1033@43830L1785,1074@45632L123,1079@45875L422,1091@46376L486,1112@47305L52,1077@45772L1667:TimePicker.kt#uh7d8r");
        Modifier.Companion companion = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 1127329796, "CC(remember):TimePicker.kt#9igjgp");
        boolean invalid$iv4 = $composer.changed(mutableState) | $composer.changedInstance(timePickerState);
        Object value$iv = $composer.rememberedValue();
        if (invalid$iv4 || value$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$1$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                    return m3124invokeZmokQxo(keyEvent.m6242unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m3124invokeZmokQxo(android.view.KeyEvent event) {
                    int iM6255getUtf16CodePointZmokQxo = KeyEvent_androidKt.m6255getUtf16CodePointZmokQxo(event);
                    boolean switchFocus = (48 <= iM6255getUtf16CodePointZmokQxo && iM6255getUtf16CodePointZmokQxo < 58) && TextRange.m7328getStartimpl(TimePickerKt.TimeInputImpl$lambda$18(mutableState).getSelection()) == 2 && TimePickerKt.TimeInputImpl$lambda$18(mutableState).getText().length() == 2;
                    if (switchFocus) {
                        timePickerState.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI());
                    }
                    return false;
                }
            };
            $composer.updateRememberedValue(value$iv);
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(companion, (Function1) value$iv);
        TextFieldValue textFieldValueTimeInputImpl$lambda$18 = TimePickerKt.TimeInputImpl$lambda$18(mutableState);
        ComposerKt.sourceInformationMarkerStart($composer, 1127349167, "CC(remember):TimePicker.kt#9igjgp");
        boolean invalid$iv5 = $composer.changedInstance(timePickerState) | $composer.changed(mutableState) | $composer.changedInstance(ref);
        Object value$iv2 = $composer.rememberedValue();
        if (invalid$iv5 || value$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$3$lambda$2(timePickerState, ref, mutableState, (TextFieldValue) obj);
                }
            };
            $composer.updateRememberedValue(value$iv2);
        }
        Function1 function1 = (Function1) value$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        int iM3142getHouryecRtBI = TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI();
        KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, KeyboardType.INSTANCE.m7566getNumberPjHm6EE(), ImeAction.INSTANCE.m7512getNexteUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 115, (DefaultConstructorMarker) null);
        ComposerKt.sourceInformationMarkerStart($composer, 1127379079, "CC(remember):TimePicker.kt#9igjgp");
        boolean invalid$iv6 = $composer.changedInstance(timePickerState);
        Object value$iv3 = $composer.rememberedValue();
        if (!invalid$iv6) {
            i = iM3142getHouryecRtBI;
            if (value$iv3 == Composer.INSTANCE.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TimePickerKt.m3113TimePickerTextField1vLObsk(modifierOnKeyEvent, textFieldValueTimeInputImpl$lambda$18, function1, timePickerState, i, keyboardOptions, new KeyboardActions(null, null, (Function1) value$iv3, null, null, null, 59, null), timePickerColors, $composer, 24576, 0);
            TimePickerKt.DisplaySeparator(SizeKt.m867sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimeInputTokens.INSTANCE.m4166getPeriodSelectorContainerHeightD9Ej5fM()), $composer, 6);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1127392057, "CC(remember):TimePicker.kt#9igjgp");
            invalid$iv = $composer.changed(mutableState2) | $composer.changedInstance(timePickerState);
            it$iv = $composer.rememberedValue();
            if (!invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$1$4$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                        return m3125invokeZmokQxo(keyEvent.m6242unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m3125invokeZmokQxo(android.view.KeyEvent event) {
                        boolean switchFocus = KeyEvent_androidKt.m6255getUtf16CodePointZmokQxo(event) == 0 && TextRange.m7328getStartimpl(TimePickerKt.TimeInputImpl$lambda$22(mutableState2).getSelection()) == 0;
                        if (switchFocus) {
                            timePickerState.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI());
                        }
                        return Boolean.valueOf(switchFocus);
                    }
                };
                $composer.updateRememberedValue(value$iv4);
                it$iv = value$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(companion2, (Function1) it$iv);
            TextFieldValue textFieldValueTimeInputImpl$lambda$22 = TimePickerKt.TimeInputImpl$lambda$22(mutableState2);
            ComposerKt.sourceInformationMarkerStart($composer, 1127408153, "CC(remember):TimePicker.kt#9igjgp");
            invalid$iv2 = $composer.changedInstance(timePickerState) | $composer.changed(mutableState2) | $composer.changedInstance(ref);
            it$iv2 = $composer.rememberedValue();
            if (!invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$9$lambda$8(timePickerState, ref, mutableState2, (TextFieldValue) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv5);
                it$iv2 = value$iv5;
            }
            Function1 function2 = (Function1) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            int iM3143getMinuteyecRtBI = TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI();
            KeyboardOptions keyboardOptions2 = new KeyboardOptions(0, (Boolean) null, KeyboardType.INSTANCE.m7566getNumberPjHm6EE(), ImeAction.INSTANCE.m7510getDoneeUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 115, (DefaultConstructorMarker) null);
            ComposerKt.sourceInformationMarkerStart($composer, 1127437447, "CC(remember):TimePicker.kt#9igjgp");
            invalid$iv3 = $composer.changedInstance(timePickerState);
            it$iv3 = $composer.rememberedValue();
            if (!invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$11$lambda$10(timePickerState, (KeyboardActionScope) obj);
                    }
                };
                $composer.updateRememberedValue(value$iv6);
                it$iv3 = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TimePickerKt.m3113TimePickerTextField1vLObsk(modifierOnPreviewKeyEvent, textFieldValueTimeInputImpl$lambda$22, function2, timePickerState, iM3143getMinuteyecRtBI, keyboardOptions2, new KeyboardActions(null, null, (Function1) it$iv3, null, null, null, 59, null), timePickerColors, $composer, 24576, 0);
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
        i = iM3142getHouryecRtBI;
        value$iv3 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$5$lambda$4(timePickerState, (KeyboardActionScope) obj);
            }
        };
        $composer.updateRememberedValue(value$iv3);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TimePickerKt.m3113TimePickerTextField1vLObsk(modifierOnKeyEvent, textFieldValueTimeInputImpl$lambda$18, function1, timePickerState, i, keyboardOptions, new KeyboardActions(null, null, (Function1) value$iv3, null, null, null, 59, null), timePickerColors, $composer, 24576, 0);
        TimePickerKt.DisplaySeparator(SizeKt.m867sizeVpY3zN4(Modifier.INSTANCE, TimePickerKt.DisplaySeparatorWidth, TimeInputTokens.INSTANCE.m4166getPeriodSelectorContainerHeightD9Ej5fM()), $composer, 6);
        Modifier.Companion companion3 = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 1127392057, "CC(remember):TimePicker.kt#9igjgp");
        invalid$iv = $composer.changed(mutableState2) | $composer.changedInstance(timePickerState);
        it$iv = $composer.rememberedValue();
        if (!invalid$iv) {
            Object value$iv7 = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$1$4$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                    return m3125invokeZmokQxo(keyEvent.m6242unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m3125invokeZmokQxo(android.view.KeyEvent event) {
                    boolean switchFocus = KeyEvent_androidKt.m6255getUtf16CodePointZmokQxo(event) == 0 && TextRange.m7328getStartimpl(TimePickerKt.TimeInputImpl$lambda$22(mutableState2).getSelection()) == 0;
                    if (switchFocus) {
                        timePickerState.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI());
                    }
                    return Boolean.valueOf(switchFocus);
                }
            };
            $composer.updateRememberedValue(value$iv7);
            it$iv = value$iv7;
        } else {
            Object value$iv8 = (Function1) new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$1$4$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(KeyEvent keyEvent) {
                    return m3125invokeZmokQxo(keyEvent.m6242unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m3125invokeZmokQxo(android.view.KeyEvent event) {
                    boolean switchFocus = KeyEvent_androidKt.m6255getUtf16CodePointZmokQxo(event) == 0 && TextRange.m7328getStartimpl(TimePickerKt.TimeInputImpl$lambda$22(mutableState2).getSelection()) == 0;
                    if (switchFocus) {
                        timePickerState.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI());
                    }
                    return Boolean.valueOf(switchFocus);
                }
            };
            $composer.updateRememberedValue(value$iv8);
            it$iv = value$iv8;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier modifierOnPreviewKeyEvent2 = KeyInputModifierKt.onPreviewKeyEvent(companion3, (Function1) it$iv);
        TextFieldValue textFieldValueTimeInputImpl$lambda$23 = TimePickerKt.TimeInputImpl$lambda$22(mutableState2);
        ComposerKt.sourceInformationMarkerStart($composer, 1127408153, "CC(remember):TimePicker.kt#9igjgp");
        invalid$iv2 = $composer.changedInstance(timePickerState) | $composer.changed(mutableState2) | $composer.changedInstance(ref);
        it$iv2 = $composer.rememberedValue();
        if (!invalid$iv2) {
            Object value$iv9 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$9$lambda$8(timePickerState, ref, mutableState2, (TextFieldValue) obj);
                }
            };
            $composer.updateRememberedValue(value$iv9);
            it$iv2 = value$iv9;
        } else {
            Object value$iv10 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$9$lambda$8(timePickerState, ref, mutableState2, (TextFieldValue) obj);
                }
            };
            $composer.updateRememberedValue(value$iv10);
            it$iv2 = value$iv10;
        }
        Function1 function3 = (Function1) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        int iM3143getMinuteyecRtBI2 = TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI();
        KeyboardOptions keyboardOptions3 = new KeyboardOptions(0, (Boolean) null, KeyboardType.INSTANCE.m7566getNumberPjHm6EE(), ImeAction.INSTANCE.m7510getDoneeUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 115, (DefaultConstructorMarker) null);
        ComposerKt.sourceInformationMarkerStart($composer, 1127437447, "CC(remember):TimePicker.kt#9igjgp");
        invalid$iv3 = $composer.changedInstance(timePickerState);
        it$iv3 = $composer.rememberedValue();
        if (!invalid$iv3) {
            Object value$iv11 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$11$lambda$10(timePickerState, (KeyboardActionScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv11);
            it$iv3 = value$iv11;
        } else {
            Object value$iv12 = new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$11$lambda$10(timePickerState, (KeyboardActionScope) obj);
                }
            };
            $composer.updateRememberedValue(value$iv12);
            it$iv3 = value$iv12;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TimePickerKt.m3113TimePickerTextField1vLObsk(modifierOnPreviewKeyEvent2, textFieldValueTimeInputImpl$lambda$23, function3, timePickerState, iM3143getMinuteyecRtBI2, keyboardOptions3, new KeyboardActions(null, null, (Function1) it$iv3, null, null, null, 59, null), timePickerColors, $composer, 24576, 0);
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

    static final Unit invoke$lambda$12$lambda$3$lambda$2(TimePickerState $state, Ref $userOverride, final MutableState $hourValue$delegate, TextFieldValue newValue) {
        TimePickerKt.m3123timeInputOnChange_K77t0(TimePickerSelectionMode.INSTANCE.m3142getHouryecRtBI(), $state, newValue, TimePickerKt.TimeInputImpl$lambda$18($hourValue$delegate), $state.getIs24hour() ? 23 : 12, $userOverride, new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$3$lambda$2$lambda$1($hourValue$delegate, (TextFieldValue) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$3$lambda$2$lambda$1(MutableState $hourValue$delegate, TextFieldValue it) {
        $hourValue$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$5$lambda$4(TimePickerState $state, KeyboardActionScope $this$KeyboardActions) {
        $state.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI());
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$9$lambda$8(TimePickerState $state, Ref $userOverride, final MutableState $minuteValue$delegate, TextFieldValue newValue) {
        TimePickerKt.m3123timeInputOnChange_K77t0(TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI(), $state, newValue, TimePickerKt.TimeInputImpl$lambda$22($minuteValue$delegate), 59, $userOverride, new Function1() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TimePickerKt$TimeInputImpl$2$1.invoke$lambda$12$lambda$9$lambda$8$lambda$7($minuteValue$delegate, (TextFieldValue) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$9$lambda$8$lambda$7(MutableState $minuteValue$delegate, TextFieldValue it) {
        $minuteValue$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$12$lambda$11$lambda$10(TimePickerState $state, KeyboardActionScope $this$KeyboardActions) {
        $state.mo2065setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m3143getMinuteyecRtBI());
        return Unit.INSTANCE;
    }
}
