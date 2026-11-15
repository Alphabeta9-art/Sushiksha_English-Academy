package androidx.compose.material;

import androidx.compose.material.internal.Icons;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ExposedDropdownMenu.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0007¢\u0006\u0002\u0010\nJë\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\u000e2\b\b\u0002\u0010\u0019\u001a\u00020\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u000e2\b\b\u0002\u0010\u001d\u001a\u00020\u000e2\b\b\u0002\u0010\u001e\u001a\u00020\u000e2\b\b\u0002\u0010\u001f\u001a\u00020\u000e2\b\b\u0002\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010!\u001a\u00020\u000e2\b\b\u0002\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020\u000eH\u0007¢\u0006\u0004\b$\u0010%Jë\u0001\u0010&\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u000e2\b\b\u0002\u0010'\u001a\u00020\u000e2\b\b\u0002\u0010(\u001a\u00020\u000e2\b\b\u0002\u0010)\u001a\u00020\u000e2\b\b\u0002\u0010*\u001a\u00020\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\u000e2\b\b\u0002\u0010\u0019\u001a\u00020\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u000e2\b\b\u0002\u0010\u001d\u001a\u00020\u000e2\b\b\u0002\u0010\u001e\u001a\u00020\u000e2\b\b\u0002\u0010\u001f\u001a\u00020\u000e2\b\b\u0002\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010!\u001a\u00020\u000e2\b\b\u0002\u0010\"\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020\u000eH\u0007¢\u0006\u0004\b+\u0010%¨\u0006,"}, d2 = {"Landroidx/compose/material/ExposedDropdownMenuDefaults;", "", "<init>", "()V", "TrailingIcon", "", "expanded", "", "onIconClick", "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "textFieldColors", "Landroidx/compose/material/TextFieldColors;", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "cursorColor", "errorCursorColor", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "leadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "trailingIconColor", "focusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "textFieldColors-DlUQjxs", "(JJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material/TextFieldColors;", "outlinedTextFieldColors", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "outlinedTextFieldColors-DlUQjxs", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ExposedDropdownMenuDefaults {
    public static final int $stable = 0;
    public static final ExposedDropdownMenuDefaults INSTANCE = new ExposedDropdownMenuDefaults();

    static final Unit TrailingIcon$lambda$3(ExposedDropdownMenuDefaults exposedDropdownMenuDefaults, boolean z, Function0 function0, int i, int i2, Composer composer, int i3) {
        exposedDropdownMenuDefaults.TrailingIcon(z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private ExposedDropdownMenuDefaults() {
    }

    public final void TrailingIcon(final boolean expanded, Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function1;
        final Function0<Unit> function2;
        Composer $composer2 = $composer.startRestartGroup(876077373);
        ComposerKt.sourceInformation($composer2, "C(TrailingIcon)N(expanded,onIconClick)235@9845L2,240@10231L2,240@10235L209,240@10156L288:ExposedDropdownMenu.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(expanded) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            function1 = function0;
        } else if (($changed & 48) == 0) {
            function1 = function0;
            $dirty |= $composer2.changedInstance(function1) ? 32 : 16;
        } else {
            function1 = function0;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            function2 = function1;
        } else {
            if (i2 != 0) {
                ComposerKt.sourceInformationMarkerStart($composer2, 96767423, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new Function0() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return Unit.INSTANCE;
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                function2 = (Function0) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
            } else {
                function2 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(876077373, $dirty2, -1, "androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon (ExposedDropdownMenu.kt:235)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 96779775, "CC(remember):ExposedDropdownMenu.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = new Function1() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ExposedDropdownMenuDefaults.TrailingIcon$lambda$1$0((SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            IconButtonKt.IconButton(function2, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) it$iv2), false, null, ComposableLambdaKt.rememberComposableLambda(726122713, true, new Function2() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ExposedDropdownMenuDefaults.TrailingIcon$lambda$2(expanded, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, (($dirty2 >> 3) & 14) | 24576, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.ExposedDropdownMenuDefaults$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ExposedDropdownMenuDefaults.TrailingIcon$lambda$3(this.f$0, expanded, function2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TrailingIcon$lambda$1$0(SemanticsPropertyReceiver $this$clearAndSetSemantics) {
        return Unit.INSTANCE;
    }

    static final Unit TrailingIcon$lambda$2(boolean $expanded, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C241@10249L185:ExposedDropdownMenu.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(726122713, $changed, -1, "androidx.compose.material.ExposedDropdownMenuDefaults.TrailingIcon.<anonymous> (ExposedDropdownMenu.kt:241)");
            }
            IconKt.m1849Iconww6aTOc(Icons.Filled.INSTANCE.getArrowDropDown$material(), "Trailing icon for exposed dropdown menu", RotateKt.rotate(Modifier.INSTANCE, $expanded ? 180.0f : 360.0f), 0L, $composer, 48, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: textFieldColors-DlUQjxs, reason: not valid java name */
    public final TextFieldColors m1824textFieldColorsDlUQjxs(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long jM5095copywmQWz5c;
        long jM5095copywmQWz5c2;
        long jM5095copywmQWz5c3;
        long jM5095copywmQWz5c4;
        long j23;
        long jM5095copywmQWz5c5;
        long jM5095copywmQWz5c6;
        long j24;
        long jM5095copywmQWz5c7;
        long jM5095copywmQWz5c8;
        long jM5095copywmQWz5c9;
        long j25;
        long jM5095copywmQWz5c10;
        long jM5095copywmQWz5c11;
        long jM5095copywmQWz5c12;
        long j26;
        long jM5095copywmQWz5c13;
        long jM5095copywmQWz5c14;
        long jM5095copywmQWz5c15;
        ComposerKt.sourceInformationMarkerStart($composer, 1208167904, "C(textFieldColors)N(textColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color,cursorColor:c#ui.graphics.Color,errorCursorColor:c#ui.graphics.Color,focusedIndicatorColor:c#ui.graphics.Color,unfocusedIndicatorColor:c#ui.graphics.Color,disabledIndicatorColor:c#ui.graphics.Color,errorIndicatorColor:c#ui.graphics.Color,leadingIconColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,errorLeadingIconColor:c#ui.graphics.Color,trailingIconColor:c#ui.graphics.Color,focusedTrailingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color,errorTrailingIconColor:c#ui.graphics.Color,focusedLabelColor:c#ui.graphics.Color,unfocusedLabelColor:c#ui.graphics.Color,disabledLabelColor:c#ui.graphics.Color,errorLabelColor:c#ui.graphics.Color,placeholderColor:c#ui.graphics.Color,disabledPlaceholderColor:c#ui.graphics.Color)292@13137L7,292@13168L7,293@13241L8,295@13311L6,296@13422L6,297@13486L6,298@13553L6,298@13594L4,300@13668L6,303@13868L8,304@13930L6,306@14004L6,307@14151L8,310@14280L6,312@14410L6,312@14451L4,313@14545L8,314@14610L6,315@14673L6,315@14714L4,316@14772L6,316@14807L6,317@14890L8,318@14948L6,319@15010L6,319@15045L6,320@15131L8:ExposedDropdownMenu.kt#jmzs0o");
        if ((i & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ((Number) objConsume2).floatValue(), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
        } else {
            jM5095copywmQWz5c = j;
        }
        long jM5095copywmQWz5c16 = (i & 2) != 0 ? Color.m5095copywmQWz5c(jM5095copywmQWz5c, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5095copywmQWz5c) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5095copywmQWz5c) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5095copywmQWz5c) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5095copywmQWz5c) : 0.0f) : j2;
        if ((i & 4) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : 0.12f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f);
        } else {
            jM5095copywmQWz5c2 = j3;
        }
        long jM1765getPrimary0d7_KjU = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU() : j4;
        long jM1759getError0d7_KjU = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j5;
        if ((i & 32) != 0) {
            long jM1765getPrimary0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU();
            jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU2) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU2) : 0.0f);
        } else {
            jM5095copywmQWz5c3 = j6;
        }
        if ((i & 64) != 0) {
            long jM1764getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c4 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU2) : 0.42f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU2) : 0.0f);
        } else {
            jM5095copywmQWz5c4 = j7;
        }
        if ((i & 128) != 0) {
            long j27 = jM5095copywmQWz5c4;
            j23 = j27;
            jM5095copywmQWz5c5 = Color.m5095copywmQWz5c(j27, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j27) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j27) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j27) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j27) : 0.0f);
        } else {
            j23 = jM5095copywmQWz5c4;
            jM5095copywmQWz5c5 = j8;
        }
        long jM1759getError0d7_KjU2 = (i & 256) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j9;
        if ((i & 512) != 0) {
            long jM1764getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c6 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU3) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU3) : 0.0f);
        } else {
            jM5095copywmQWz5c6 = j10;
        }
        if ((i & 1024) != 0) {
            long j28 = jM5095copywmQWz5c6;
            jM5095copywmQWz5c7 = Color.m5095copywmQWz5c(j28, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j28) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j28) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j28) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j28) : 0.0f);
            j24 = j28;
        } else {
            j24 = jM5095copywmQWz5c6;
            jM5095copywmQWz5c7 = j11;
        }
        long j29 = (i & 2048) != 0 ? j24 : j12;
        if ((i & 4096) != 0) {
            long jM1764getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c8 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU4) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU4) : 0.0f);
        } else {
            jM5095copywmQWz5c8 = j13;
        }
        if ((i & 8192) != 0) {
            long jM1765getPrimary0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU();
            jM5095copywmQWz5c9 = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU3) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU3) : 0.0f);
        } else {
            jM5095copywmQWz5c9 = j14;
        }
        if ((i & 16384) != 0) {
            long j30 = jM5095copywmQWz5c8;
            jM5095copywmQWz5c10 = Color.m5095copywmQWz5c(j30, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j30) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j30) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j30) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j30) : 0.0f);
            j25 = j30;
        } else {
            j25 = jM5095copywmQWz5c8;
            jM5095copywmQWz5c10 = j15;
        }
        long jM1759getError0d7_KjU3 = (32768 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j16;
        if ((65536 & i) != 0) {
            long jM1765getPrimary0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU();
            jM5095copywmQWz5c11 = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU4) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU4) : 0.0f);
        } else {
            jM5095copywmQWz5c11 = j17;
        }
        if ((131072 & i) != 0) {
            long jM1764getOnSurface0d7_KjU5 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c12 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU5, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU5) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU5) : 0.0f);
        } else {
            jM5095copywmQWz5c12 = j18;
        }
        if ((262144 & i) != 0) {
            long j31 = jM5095copywmQWz5c12;
            jM5095copywmQWz5c13 = Color.m5095copywmQWz5c(j31, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j31) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j31) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j31) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j31) : 0.0f);
            j26 = j31;
        } else {
            j26 = jM5095copywmQWz5c12;
            jM5095copywmQWz5c13 = j19;
        }
        long jM1759getError0d7_KjU4 = (524288 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j20;
        if ((1048576 & i) != 0) {
            long jM1764getOnSurface0d7_KjU6 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c14 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU6, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU6) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU6) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU6) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU6) : 0.0f);
        } else {
            jM5095copywmQWz5c14 = j21;
        }
        if ((i & 2097152) != 0) {
            long j32 = jM5095copywmQWz5c14;
            jM5095copywmQWz5c15 = Color.m5095copywmQWz5c(j32, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j32) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j32) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j32) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j32) : 0.0f);
        } else {
            jM5095copywmQWz5c15 = j22;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1208167904, $changed, $changed1, "androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors (ExposedDropdownMenu.kt:322)");
        }
        DefaultTextFieldForExposedDropdownMenusColors defaultTextFieldForExposedDropdownMenusColors = new DefaultTextFieldForExposedDropdownMenusColors(jM5095copywmQWz5c, jM5095copywmQWz5c16, jM1765getPrimary0d7_KjU, jM1759getError0d7_KjU, jM5095copywmQWz5c3, j23, jM1759getError0d7_KjU2, jM5095copywmQWz5c5, j24, jM5095copywmQWz5c7, j29, j25, jM5095copywmQWz5c9, jM5095copywmQWz5c10, jM1759getError0d7_KjU3, jM5095copywmQWz5c2, jM5095copywmQWz5c11, j26, jM5095copywmQWz5c13, jM1759getError0d7_KjU4, jM5095copywmQWz5c14, jM5095copywmQWz5c15, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldForExposedDropdownMenusColors;
    }

    /* JADX INFO: renamed from: outlinedTextFieldColors-DlUQjxs, reason: not valid java name */
    public final TextFieldColors m1823outlinedTextFieldColorsDlUQjxs(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long jM5095copywmQWz5c;
        long jM5095copywmQWz5c2;
        long jM5095copywmQWz5c3;
        long j23;
        long jM5095copywmQWz5c4;
        long jM5095copywmQWz5c5;
        long j24;
        long jM5095copywmQWz5c6;
        long jM5095copywmQWz5c7;
        long jM5095copywmQWz5c8;
        long j25;
        long jM5095copywmQWz5c9;
        long jM5095copywmQWz5c10;
        long jM5095copywmQWz5c11;
        long j26;
        long jM5095copywmQWz5c12;
        long jM5095copywmQWz5c13;
        long jM5095copywmQWz5c14;
        ComposerKt.sourceInformationMarkerStart($composer, 1162641182, "C(outlinedTextFieldColors)N(textColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color,cursorColor:c#ui.graphics.Color,errorCursorColor:c#ui.graphics.Color,focusedBorderColor:c#ui.graphics.Color,unfocusedBorderColor:c#ui.graphics.Color,disabledBorderColor:c#ui.graphics.Color,errorBorderColor:c#ui.graphics.Color,leadingIconColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,errorLeadingIconColor:c#ui.graphics.Color,trailingIconColor:c#ui.graphics.Color,focusedTrailingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color,errorTrailingIconColor:c#ui.graphics.Color,focusedLabelColor:c#ui.graphics.Color,unfocusedLabelColor:c#ui.graphics.Color,disabledLabelColor:c#ui.graphics.Color,errorLabelColor:c#ui.graphics.Color,placeholderColor:c#ui.graphics.Color,disabledPlaceholderColor:c#ui.graphics.Color)390@19105L7,390@19136L7,391@19209L8,393@19315L6,394@19379L6,395@19443L6,395@19484L4,397@19555L6,397@19598L8,398@19693L8,399@19752L6,401@19826L6,402@19973L8,405@20102L6,407@20232L6,407@20273L4,408@20367L8,409@20432L6,410@20495L6,410@20536L4,411@20594L6,411@20629L6,412@20712L8,413@20770L6,414@20832L6,414@20867L6,415@20953L8:ExposedDropdownMenu.kt#jmzs0o");
        if ((i & 1) != 0) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd($composer);
            long jM5107unboximpl = ((Color) objConsume).m5107unboximpl();
            ProvidableCompositionLocal<Float> localContentAlpha = ContentAlphaKt.getLocalContentAlpha();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localContentAlpha);
            ComposerKt.sourceInformationMarkerEnd($composer);
            jM5095copywmQWz5c = Color.m5095copywmQWz5c(jM5107unboximpl, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5107unboximpl) : ((Number) objConsume2).floatValue(), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5107unboximpl) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5107unboximpl) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5107unboximpl) : 0.0f);
        } else {
            jM5095copywmQWz5c = j;
        }
        long jM5095copywmQWz5c15 = (i & 2) != 0 ? Color.m5095copywmQWz5c(jM5095copywmQWz5c, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5095copywmQWz5c) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5095copywmQWz5c) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5095copywmQWz5c) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5095copywmQWz5c) : 0.0f) : j2;
        long jM5132getTransparent0d7_KjU = (i & 4) != 0 ? Color.INSTANCE.m5132getTransparent0d7_KjU() : j3;
        long jM1765getPrimary0d7_KjU = (i & 8) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU() : j4;
        long jM1759getError0d7_KjU = (i & 16) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j5;
        if ((i & 32) != 0) {
            long jM1765getPrimary0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU();
            jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU2) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU2) : 0.0f);
        } else {
            jM5095copywmQWz5c2 = j6;
        }
        if ((i & 64) != 0) {
            long jM1764getOnSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU) : 0.0f);
        } else {
            jM5095copywmQWz5c3 = j7;
        }
        if ((i & 128) != 0) {
            long j27 = jM5095copywmQWz5c3;
            j23 = j27;
            jM5095copywmQWz5c4 = Color.m5095copywmQWz5c(j27, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j27) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j27) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j27) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j27) : 0.0f);
        } else {
            j23 = jM5095copywmQWz5c3;
            jM5095copywmQWz5c4 = j8;
        }
        long jM1759getError0d7_KjU2 = (i & 256) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j9;
        if ((i & 512) != 0) {
            long jM1764getOnSurface0d7_KjU2 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c5 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU2, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU2) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU2) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU2) : 0.0f);
        } else {
            jM5095copywmQWz5c5 = j10;
        }
        if ((i & 1024) != 0) {
            long j28 = jM5095copywmQWz5c5;
            jM5095copywmQWz5c6 = Color.m5095copywmQWz5c(j28, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j28) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j28) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j28) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j28) : 0.0f);
            j24 = j28;
        } else {
            j24 = jM5095copywmQWz5c5;
            jM5095copywmQWz5c6 = j11;
        }
        long j29 = (i & 2048) != 0 ? j24 : j12;
        if ((i & 4096) != 0) {
            long jM1764getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c7 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU3) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU3) : 0.0f);
        } else {
            jM5095copywmQWz5c7 = j13;
        }
        if ((i & 8192) != 0) {
            long jM1765getPrimary0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU();
            jM5095copywmQWz5c8 = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU3) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU3) : 0.0f);
        } else {
            jM5095copywmQWz5c8 = j14;
        }
        if ((i & 16384) != 0) {
            long j30 = jM5095copywmQWz5c7;
            jM5095copywmQWz5c9 = Color.m5095copywmQWz5c(j30, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j30) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j30) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j30) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j30) : 0.0f);
            j25 = j30;
        } else {
            j25 = jM5095copywmQWz5c7;
            jM5095copywmQWz5c9 = j15;
        }
        long jM1759getError0d7_KjU3 = (32768 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j16;
        if ((65536 & i) != 0) {
            long jM1765getPrimary0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU();
            jM5095copywmQWz5c10 = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU4) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU4) : 0.0f);
        } else {
            jM5095copywmQWz5c10 = j17;
        }
        if ((131072 & i) != 0) {
            long jM1764getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c11 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU4) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU4) : 0.0f);
        } else {
            jM5095copywmQWz5c11 = j18;
        }
        if ((262144 & i) != 0) {
            long j31 = jM5095copywmQWz5c11;
            jM5095copywmQWz5c12 = Color.m5095copywmQWz5c(j31, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j31) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j31) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j31) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j31) : 0.0f);
            j26 = j31;
        } else {
            j26 = jM5095copywmQWz5c11;
            jM5095copywmQWz5c12 = j19;
        }
        long jM1759getError0d7_KjU4 = (524288 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j20;
        if ((1048576 & i) != 0) {
            long jM1764getOnSurface0d7_KjU5 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c13 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU5, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU5) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU5) : 0.0f);
        } else {
            jM5095copywmQWz5c13 = j21;
        }
        if ((i & 2097152) != 0) {
            long j32 = jM5095copywmQWz5c13;
            jM5095copywmQWz5c14 = Color.m5095copywmQWz5c(j32, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j32) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j32) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j32) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j32) : 0.0f);
        } else {
            jM5095copywmQWz5c14 = j22;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1162641182, $changed, $changed1, "androidx.compose.material.ExposedDropdownMenuDefaults.outlinedTextFieldColors (ExposedDropdownMenu.kt:417)");
        }
        DefaultTextFieldForExposedDropdownMenusColors defaultTextFieldForExposedDropdownMenusColors = new DefaultTextFieldForExposedDropdownMenusColors(jM5095copywmQWz5c, jM5095copywmQWz5c15, jM1765getPrimary0d7_KjU, jM1759getError0d7_KjU, jM5095copywmQWz5c2, j23, jM1759getError0d7_KjU2, jM5095copywmQWz5c4, j24, jM5095copywmQWz5c6, j29, j25, jM5095copywmQWz5c8, jM5095copywmQWz5c9, jM1759getError0d7_KjU3, jM5132getTransparent0d7_KjU, jM5095copywmQWz5c10, j26, jM5095copywmQWz5c12, jM1759getError0d7_KjU4, jM5095copywmQWz5c13, jM5095copywmQWz5c14, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldForExposedDropdownMenusColors;
    }
}
