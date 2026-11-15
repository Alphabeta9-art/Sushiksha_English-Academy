package androidx.compose.material;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.input.VisualTransformation;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SecureTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;"}, k = 3, mv = {2, 0, 0}, xi = 48)
final class SecureTextFieldKt$OutlinedSecureTextField$3 implements TextFieldDecorator {
    final /* synthetic */ TextFieldColors $colors;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ boolean $isError;
    final /* synthetic */ Function2<Composer, Integer, Unit> $label;
    final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
    final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ TextFieldState $state;
    final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;

    /* JADX WARN: Multi-variable type inference failed */
    SecureTextFieldKt$OutlinedSecureTextField$3(TextFieldState textFieldState, boolean z, MutableInteractionSource mutableInteractionSource, boolean z2, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Shape shape, TextFieldColors textFieldColors) {
        this.$state = textFieldState;
        this.$enabled = z;
        this.$interactionSource = mutableInteractionSource;
        this.$isError = z2;
        this.$label = function2;
        this.$placeholder = function3;
        this.$leadingIcon = function4;
        this.$trailingIcon = function5;
        this.$shape = shape;
        this.$colors = textFieldColors;
    }

    static final Unit Decoration$lambda$1(SecureTextFieldKt$OutlinedSecureTextField$3 secureTextFieldKt$OutlinedSecureTextField$3, Function2 function2, int i, Composer composer, int i2) {
        secureTextFieldKt$OutlinedSecureTextField$3.Decoration(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.text.input.TextFieldDecorator
    public final void Decoration(Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function3 = function2;
        Composer $composer3 = $composer.startRestartGroup(-741592697);
        ComposerKt.sourceInformation($composer3, "C(Decoration)N(innerTextField)296@16549L119,282@15944L739:SecureTextField.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(this) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-741592697, $dirty, -1, "androidx.compose.material.OutlinedSecureTextField.<no name provided>.Decoration (SecureTextField.kt:282)");
            }
            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
            String string = this.$state.getText().toString();
            VisualTransformation none = VisualTransformation.INSTANCE.getNone();
            int $dirty2 = $dirty;
            boolean z = this.$enabled;
            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            boolean z2 = this.$isError;
            Function2<Composer, Integer, Unit> function4 = this.$label;
            Function2<Composer, Integer, Unit> function5 = this.$placeholder;
            Function2<Composer, Integer, Unit> function6 = this.$leadingIcon;
            Function2<Composer, Integer, Unit> function7 = this.$trailingIcon;
            Shape shape = this.$shape;
            TextFieldColors textFieldColors = this.$colors;
            final boolean z3 = this.$enabled;
            final boolean z4 = this.$isError;
            final MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
            final TextFieldColors textFieldColors2 = this.$colors;
            final Shape shape2 = this.$shape;
            $composer2 = $composer3;
            function3 = function2;
            textFieldDefaults.OutlinedTextFieldDecorationBox(string, function3, z, true, none, mutableInteractionSource, z2, function4, function5, function6, function7, shape, textFieldColors, null, ComposableLambdaKt.rememberComposableLambda(910489071, true, new Function2() { // from class: androidx.compose.material.SecureTextFieldKt$OutlinedSecureTextField$3$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SecureTextFieldKt$OutlinedSecureTextField$3.Decoration$lambda$0(z3, z4, mutableInteractionSource2, textFieldColors2, shape2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty2 << 3) & 112) | 27648, 221184, 8192);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SecureTextFieldKt$OutlinedSecureTextField$3$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SecureTextFieldKt$OutlinedSecureTextField$3.Decoration$lambda$1(this.f$0, function3, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit Decoration$lambda$0(boolean $enabled, boolean $isError, MutableInteractionSource $interactionSource, TextFieldColors $colors, Shape $shape, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C297@16589L61:SecureTextField.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(910489071, $changed, -1, "androidx.compose.material.OutlinedSecureTextField.<no name provided>.Decoration.<anonymous> (SecureTextField.kt:297)");
            }
            TextFieldDefaults.INSTANCE.m1990BorderBoxnbWgWpA($enabled, $isError, $interactionSource, $colors, $shape, 0.0f, 0.0f, $composer, 12582912, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
