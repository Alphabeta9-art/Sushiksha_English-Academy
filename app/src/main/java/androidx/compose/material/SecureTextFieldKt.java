package androidx.compose.material;

import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.BasicSecureTextFieldKt;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.input.InputTransformation;
import androidx.compose.foundation.text.input.KeyboardActionHandler;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.input.TextObfuscationMode;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Density;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SecureTextField.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001añ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 H\u0007¢\u0006\u0004\b!\u0010\"\u001añ\u0001\u0010#\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\u0010\u001a\u00020\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 H\u0007¢\u0006\u0004\b$\u0010\"\"\u000e\u0010%\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010&\u001a\u00020\u0016X\u0082T¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"SecureTextField", "", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", "textStyle", "Landroidx/compose/ui/text/TextStyle;", MetadataField.LABEL, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "leadingIcon", "trailingIcon", "isError", "inputTransformation", "Landroidx/compose/foundation/text/input/InputTransformation;", "textObfuscationMode", "Landroidx/compose/foundation/text/input/TextObfuscationMode;", "textObfuscationCharacter", "", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "onKeyboardAction", "Landroidx/compose/foundation/text/input/KeyboardActionHandler;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material/TextFieldColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "SecureTextField-0vce7ms", "(Landroidx/compose/foundation/text/input/TextFieldState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/foundation/text/input/InputTransformation;ICLandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/input/KeyboardActionHandler;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "OutlinedSecureTextField", "OutlinedSecureTextField-0vce7ms", "SecureTextFieldKeyboardOptions", "DefaultObfuscationCharacter", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SecureTextFieldKt {
    private static final char DefaultObfuscationCharacter = 8226;
    private static final KeyboardOptions SecureTextFieldKeyboardOptions = new KeyboardOptions(0, (Boolean) false, KeyboardType.INSTANCE.m7568getPasswordPjHm6EE(), 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 121, (DefaultConstructorMarker) null);

    static final Unit OutlinedSecureTextField_0vce7ms$lambda$4(TextFieldState textFieldState, Modifier modifier, boolean z, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z2, InputTransformation inputTransformation, int i, char c, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Shape shape, TextFieldColors textFieldColors, MutableInteractionSource mutableInteractionSource, int i2, int i3, int i4, Composer composer, int i5) {
        m1916OutlinedSecureTextField0vce7ms(textFieldState, modifier, z, textStyle, function2, function3, function4, function5, z2, inputTransformation, i, c, keyboardOptions, keyboardActionHandler, shape, textFieldColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    static final Unit SecureTextField_0vce7ms$lambda$2(TextFieldState textFieldState, Modifier modifier, boolean z, TextStyle textStyle, Function2 function2, Function2 function3, Function2 function4, Function2 function5, boolean z2, InputTransformation inputTransformation, int i, char c, KeyboardOptions keyboardOptions, KeyboardActionHandler keyboardActionHandler, Shape shape, TextFieldColors textFieldColors, MutableInteractionSource mutableInteractionSource, int i2, int i3, int i4, Composer composer, int i5) {
        m1917SecureTextField0vce7ms(textFieldState, modifier, z, textStyle, function2, function3, function4, function5, z2, inputTransformation, i, c, keyboardOptions, keyboardActionHandler, shape, textFieldColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: SecureTextField-0vce7ms, reason: not valid java name */
    public static final void m1917SecureTextField0vce7ms(final TextFieldState state, Modifier modifier, boolean enabled, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, boolean isError, InputTransformation inputTransformation, int i, char textObfuscationCharacter, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, Shape shape, TextFieldColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i2) {
        Modifier modifier2;
        boolean enabled2;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        int i3;
        int i4;
        int $dirty1;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer2;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled3;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final InputTransformation inputTransformation2;
        final int i5;
        final char textObfuscationCharacter2;
        final KeyboardActionHandler onKeyboardAction2;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier4;
        boolean enabled4;
        TextStyle textStyle4;
        int $dirty;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        boolean isError3;
        InputTransformation inputTransformation3;
        int iM1360getRevealLastTypedvTwcZD0;
        char textObfuscationCharacter3;
        KeyboardOptions keyboardOptions3;
        KeyboardActionHandler onKeyboardAction3;
        Shape shape3;
        int $dirty2;
        Composer $composer3;
        TextFieldColors colors3;
        int $dirty3;
        boolean enabled5;
        InputTransformation inputTransformation4;
        int i6;
        char textObfuscationCharacter4;
        KeyboardOptions keyboardOptions4;
        int $dirty4;
        MutableInteractionSource interactionSource3;
        MutableInteractionSource interactionSource4;
        MutableInteractionSource interactionSource5;
        int $dirty5;
        int i7;
        int i8;
        Composer $composer4 = $composer.startRestartGroup(-308805078);
        ComposerKt.sourceInformation($composer4, "C(SecureTextField)N(state,modifier,enabled,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,inputTransformation,textObfuscationMode:c#foundation.text.input.TextObfuscationMode,textObfuscationCharacter,keyboardOptions,onKeyboardAction,shape,colors,interactionSource)133@7665L38,140@7981L20,128@7456L1524:SecureTextField.kt#jmzs0o");
        int $dirty6 = $changed;
        int $dirty7 = $changed1;
        if (($changed & 6) == 0) {
            $dirty6 |= $composer4.changed(state) ? 4 : 2;
        }
        int i9 = i2 & 2;
        if (i9 != 0) {
            $dirty6 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i10 = i2 & 4;
        if (i10 != 0) {
            $dirty6 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer4.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i2 & 8) == 0) {
                textStyle2 = textStyle;
                if ($composer4.changed(textStyle2)) {
                    i8 = 2048;
                }
                $dirty6 |= i8;
            } else {
                textStyle2 = textStyle;
            }
            i8 = 1024;
            $dirty6 |= i8;
        } else {
            textStyle2 = textStyle;
        }
        int i11 = i2 & 16;
        int i12 = 8192;
        if (i11 != 0) {
            $dirty6 |= 24576;
            function6 = function2;
        } else if (($changed & 24576) == 0) {
            function6 = function2;
            $dirty6 |= $composer4.changedInstance(function6) ? 16384 : 8192;
        } else {
            function6 = function2;
        }
        int i13 = i2 & 32;
        if (i13 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function7 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function7 = function3;
            $dirty6 |= $composer4.changedInstance(function7) ? 131072 : 65536;
        } else {
            function7 = function3;
        }
        int i14 = i2 & 64;
        if (i14 != 0) {
            $dirty6 |= 1572864;
            function8 = function4;
        } else if (($changed & 1572864) == 0) {
            function8 = function4;
            $dirty6 |= $composer4.changedInstance(function8) ? 1048576 : 524288;
        } else {
            function8 = function4;
        }
        int i15 = i2 & 128;
        if (i15 != 0) {
            $dirty6 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty6 |= $composer4.changedInstance(function5) ? 8388608 : 4194304;
        }
        int i16 = i2 & 256;
        if (i16 != 0) {
            $dirty6 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty6 |= $composer4.changed(isError) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i17 = i2 & 512;
        if (i17 != 0) {
            $dirty6 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= $composer4.changed(inputTransformation) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty7 |= ((i2 & 1024) == 0 && $composer4.changed(i)) ? 4 : 2;
        }
        int i18 = i2 & 2048;
        if (i18 != 0) {
            $dirty7 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty7 |= $composer4.changed(textObfuscationCharacter) ? 32 : 16;
        }
        int i19 = i2 & 4096;
        if (i19 != 0) {
            $dirty7 |= 384;
            i3 = i19;
        } else {
            i3 = i19;
            if (($changed1 & 384) == 0) {
                $dirty7 |= $composer4.changed(keyboardOptions) ? 256 : 128;
            }
        }
        int i20 = i2 & 8192;
        if (i20 != 0) {
            $dirty7 |= 3072;
            i4 = i20;
        } else {
            i4 = i20;
            if (($changed1 & 3072) == 0) {
                $dirty7 |= $composer4.changed(onKeyboardAction) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i2 & 16384) == 0 && $composer4.changed(shape)) {
                i12 = 16384;
            }
            $dirty7 |= i12;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i2 & 32768) == 0) {
                $dirty5 = $dirty7;
                if ($composer4.changed(colors)) {
                    i7 = 131072;
                }
                $dirty1 = $dirty5 | i7;
            } else {
                $dirty5 = $dirty7;
            }
            i7 = 65536;
            $dirty1 = $dirty5 | i7;
        } else {
            $dirty1 = $dirty7;
        }
        int i21 = i2 & 65536;
        if (i21 != 0) {
            $dirty1 |= 1572864;
            mutableInteractionSource = interactionSource;
        } else if (($changed1 & 1572864) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty1 |= $composer4.changed(mutableInteractionSource) ? 1048576 : 524288;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if ($composer4.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty1 & 599187) == 599186) ? false : true, $dirty6 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "107@6345L7,118@6948L14,119@7012L17");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i2 & 8) != 0) {
                    $dirty6 &= -7169;
                }
                if ((i2 & 1024) != 0) {
                    $dirty1 &= -15;
                }
                if ((i2 & 16384) != 0) {
                    $dirty1 &= -57345;
                }
                if ((i2 & 32768) != 0) {
                    $dirty1 &= -458753;
                }
                function16 = function5;
                isError3 = isError;
                i6 = i;
                onKeyboardAction3 = onKeyboardAction;
                shape3 = shape;
                interactionSource3 = mutableInteractionSource;
                enabled5 = enabled2;
                textStyle4 = textStyle2;
                function13 = function6;
                $dirty4 = $dirty1;
                inputTransformation4 = inputTransformation;
                textObfuscationCharacter4 = textObfuscationCharacter;
                keyboardOptions4 = keyboardOptions;
                $dirty3 = $dirty6;
                $composer3 = $composer4;
                colors3 = colors;
            } else {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i10 == 0) {
                    enabled4 = enabled2;
                } else {
                    enabled4 = true;
                }
                if ((i2 & 8) != 0) {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer4.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    textStyle4 = (TextStyle) objConsume;
                    $dirty = $dirty6 & (-7169);
                } else {
                    textStyle4 = textStyle2;
                    $dirty = $dirty6;
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function6;
                }
                if (i13 == 0) {
                    function14 = function7;
                } else {
                    function14 = null;
                }
                if (i14 == 0) {
                    function15 = function8;
                } else {
                    function15 = null;
                }
                if (i15 == 0) {
                    function16 = function5;
                } else {
                    function16 = null;
                }
                if (i16 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i17 == 0) {
                    inputTransformation3 = inputTransformation;
                } else {
                    inputTransformation3 = null;
                }
                if ((i2 & 1024) == 0) {
                    iM1360getRevealLastTypedvTwcZD0 = i;
                } else {
                    $dirty1 &= -15;
                    iM1360getRevealLastTypedvTwcZD0 = TextObfuscationMode.INSTANCE.m1360getRevealLastTypedvTwcZD0();
                }
                if (i18 == 0) {
                    textObfuscationCharacter3 = textObfuscationCharacter;
                } else {
                    textObfuscationCharacter3 = 8226;
                }
                if (i3 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = SecureTextFieldKeyboardOptions;
                }
                if (i4 == 0) {
                    onKeyboardAction3 = onKeyboardAction;
                } else {
                    onKeyboardAction3 = null;
                }
                if ((i2 & 16384) == 0) {
                    shape3 = shape;
                    $dirty2 = $dirty1;
                } else {
                    shape3 = TextFieldDefaults.INSTANCE.getTextFieldShape($composer4, 6);
                    $dirty2 = $dirty1 & (-57345);
                }
                if ((i2 & 32768) == 0) {
                    $composer3 = $composer4;
                    colors3 = colors;
                } else {
                    colors3 = TextFieldDefaults.INSTANCE.m1998textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, 48, 2097151);
                    $composer3 = $composer4;
                    $dirty2 &= -458753;
                }
                if (i21 == 0) {
                    modifier2 = modifier4;
                    $dirty3 = $dirty;
                    enabled5 = enabled4;
                    function7 = function14;
                    function8 = function15;
                    inputTransformation4 = inputTransformation3;
                    i6 = iM1360getRevealLastTypedvTwcZD0;
                    textObfuscationCharacter4 = textObfuscationCharacter3;
                    keyboardOptions4 = keyboardOptions3;
                    $dirty4 = $dirty2;
                    interactionSource3 = interactionSource;
                } else {
                    $dirty3 = $dirty;
                    interactionSource3 = null;
                    modifier2 = modifier4;
                    enabled5 = enabled4;
                    function7 = function14;
                    function8 = function15;
                    inputTransformation4 = inputTransformation3;
                    i6 = iM1360getRevealLastTypedvTwcZD0;
                    textObfuscationCharacter4 = textObfuscationCharacter3;
                    keyboardOptions4 = keyboardOptions3;
                    $dirty4 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-308805078, $dirty3, $dirty4, "androidx.compose.material.SecureTextField (SecureTextField.kt:121)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(208844689);
                ComposerKt.sourceInformation($composer3, "123@7173L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -408905071, "CC(remember):SecureTextField.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                interactionSource4 = interactionSource3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource5 = (MutableInteractionSource) it$iv;
            } else {
                interactionSource4 = interactionSource3;
                $composer3.startReplaceGroup(-408905722);
                $composer3.endReplaceGroup();
                interactionSource5 = interactionSource4;
            }
            $composer3.startReplaceGroup(-408899976);
            ComposerKt.sourceInformation($composer3, "*125@7352L18");
            long textColor = textStyle4.m7358getColor0d7_KjU();
            if (!(textColor != 16)) {
                textColor = colors3.textColor(enabled5, $composer3, (($dirty3 >> 6) & 14) | (($dirty4 >> 12) & 112)).getValue().m5107unboximpl();
            }
            $composer3.endReplaceGroup();
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            TextFieldColors colors4 = colors3;
            boolean enabled6 = enabled5;
            Modifier modifier5 = modifier2;
            boolean isError4 = isError3;
            TextStyle textStyle5 = textStyle4;
            Modifier modifierM849defaultMinSizeVpY3zN4 = SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(TextFieldDefaults.m1986indicatorLinegv0btCI$default(TextFieldDefaults.INSTANCE, modifier5, enabled6, isError4, interactionSource5, colors4, 0.0f, 0.0f, 48, null), isError4, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1947getDefaultErrorMessageUdPEhr4(), $composer3, 6)), TextFieldDefaults.INSTANCE.m1993getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m1992getMinHeightD9Ej5fM());
            SolidColor solidColor = new SolidColor(colors4.cursorColor(isError4, $composer3, (($dirty3 >> 24) & 14) | (($dirty4 >> 12) & 112)).getValue().m5107unboximpl(), null);
            Function2<? super Composer, ? super Integer, Unit> function17 = function7;
            Function2<? super Composer, ? super Integer, Unit> function18 = function8;
            Composer $composer5 = $composer3;
            KeyboardActionHandler onKeyboardAction4 = onKeyboardAction3;
            BasicSecureTextFieldKt.m1137BasicSecureTextFieldegD4TGM(state, modifierM849defaultMinSizeVpY3zN4, enabled6, false, inputTransformation4, mergedTextStyle, keyboardOptions4, onKeyboardAction4, null, interactionSource5, solidColor, new SecureTextFieldKt$SecureTextField$1(state, enabled6, interactionSource5, isError4, function13, function17, function18, function16, shape3, colors4), i6, textObfuscationCharacter4, $composer5, ($dirty3 & 14) | ($dirty3 & 896) | (($dirty3 >> 15) & 57344) | (($dirty4 << 12) & 3670016) | (($dirty4 << 12) & 29360128), (($dirty4 << 6) & 896) | (($dirty4 << 6) & 7168), 264);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            textStyle3 = textStyle5;
            keyboardOptions2 = keyboardOptions4;
            onKeyboardAction2 = onKeyboardAction4;
            i5 = i6;
            textObfuscationCharacter2 = textObfuscationCharacter4;
            modifier3 = modifier5;
            enabled3 = enabled6;
            function9 = function13;
            function10 = function17;
            function11 = function18;
            isError2 = isError3;
            shape2 = shape3;
            interactionSource2 = interactionSource4;
            colors2 = colors4;
            inputTransformation2 = inputTransformation4;
            function12 = function16;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            shape2 = shape;
            colors2 = colors;
            enabled3 = enabled2;
            textStyle3 = textStyle2;
            function9 = function6;
            function10 = function7;
            modifier3 = modifier2;
            function11 = function8;
            function12 = function5;
            inputTransformation2 = inputTransformation;
            i5 = i;
            textObfuscationCharacter2 = textObfuscationCharacter;
            onKeyboardAction2 = onKeyboardAction;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SecureTextFieldKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SecureTextFieldKt.SecureTextField_0vce7ms$lambda$2(state, modifier3, enabled3, textStyle3, function9, function10, function11, function12, isError2, inputTransformation2, i5, textObfuscationCharacter2, keyboardOptions2, onKeyboardAction2, shape2, colors2, interactionSource2, $changed, $changed1, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: OutlinedSecureTextField-0vce7ms, reason: not valid java name */
    public static final void m1916OutlinedSecureTextField0vce7ms(final TextFieldState state, Modifier modifier, boolean enabled, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, boolean isError, InputTransformation inputTransformation, int i, char textObfuscationCharacter, KeyboardOptions keyboardOptions, KeyboardActionHandler onKeyboardAction, Shape shape, TextFieldColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i2) {
        Modifier modifier2;
        boolean enabled2;
        TextStyle textStyle2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        int i3;
        int i4;
        int $dirty1;
        Composer $composer2;
        final boolean isError2;
        final KeyboardOptions keyboardOptions2;
        final Shape shape2;
        final TextFieldColors colors2;
        final boolean enabled3;
        final TextStyle textStyle3;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final InputTransformation inputTransformation2;
        final int i5;
        final char textObfuscationCharacter2;
        final KeyboardActionHandler onKeyboardAction2;
        final MutableInteractionSource interactionSource2;
        Modifier.Companion modifier4;
        boolean enabled4;
        TextStyle textStyle4;
        int $dirty;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        boolean isError3;
        InputTransformation inputTransformation3;
        int iM1360getRevealLastTypedvTwcZD0;
        char textObfuscationCharacter3;
        KeyboardOptions keyboardOptions3;
        KeyboardActionHandler onKeyboardAction3;
        Shape shape3;
        int $dirty2;
        Composer $composer3;
        TextFieldColors colors3;
        MutableInteractionSource interactionSource3;
        TextFieldColors colors4;
        boolean enabled5;
        int $dirty3;
        boolean isError4;
        InputTransformation inputTransformation4;
        int i6;
        char textObfuscationCharacter4;
        KeyboardOptions keyboardOptions4;
        int $dirty4;
        MutableInteractionSource interactionSource4;
        Modifier.Companion companionM823paddingqDBjuR0$default;
        int $dirty5;
        int i7;
        int i8;
        Composer $composer4 = $composer.startRestartGroup(-1856037588);
        ComposerKt.sourceInformation($composer4, "C(OutlinedSecureTextField)N(state,modifier,enabled,textStyle,label,placeholder,leadingIcon,trailingIcon,isError,inputTransformation,textObfuscationMode:c#foundation.text.input.TextObfuscationMode,textObfuscationCharacter,keyboardOptions,onKeyboardAction,shape,colors,interactionSource)250@14549L7,267@15231L38,274@15547L20,252@14562L2138:SecureTextField.kt#jmzs0o");
        int $dirty6 = $changed;
        int $dirty7 = $changed1;
        if (($changed & 6) == 0) {
            $dirty6 |= $composer4.changed(state) ? 4 : 2;
        }
        int i9 = i2 & 2;
        if (i9 != 0) {
            $dirty6 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer4.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i10 = i2 & 4;
        if (i10 != 0) {
            $dirty6 |= 384;
            enabled2 = enabled;
        } else if (($changed & 384) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer4.changed(enabled2) ? 256 : 128;
        } else {
            enabled2 = enabled;
        }
        if (($changed & 3072) == 0) {
            if ((i2 & 8) == 0) {
                textStyle2 = textStyle;
                if ($composer4.changed(textStyle2)) {
                    i8 = 2048;
                }
                $dirty6 |= i8;
            } else {
                textStyle2 = textStyle;
            }
            i8 = 1024;
            $dirty6 |= i8;
        } else {
            textStyle2 = textStyle;
        }
        int i11 = i2 & 16;
        int i12 = 8192;
        if (i11 != 0) {
            $dirty6 |= 24576;
            function6 = function2;
        } else if (($changed & 24576) == 0) {
            function6 = function2;
            $dirty6 |= $composer4.changedInstance(function6) ? 16384 : 8192;
        } else {
            function6 = function2;
        }
        int i13 = i2 & 32;
        if (i13 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function7 = function3;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function7 = function3;
            $dirty6 |= $composer4.changedInstance(function7) ? 131072 : 65536;
        } else {
            function7 = function3;
        }
        int i14 = i2 & 64;
        if (i14 != 0) {
            $dirty6 |= 1572864;
            function8 = function4;
        } else if (($changed & 1572864) == 0) {
            function8 = function4;
            $dirty6 |= $composer4.changedInstance(function8) ? 1048576 : 524288;
        } else {
            function8 = function4;
        }
        int i15 = i2 & 128;
        if (i15 != 0) {
            $dirty6 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty6 |= $composer4.changedInstance(function5) ? 8388608 : 4194304;
        }
        int i16 = i2 & 256;
        if (i16 != 0) {
            $dirty6 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty6 |= $composer4.changed(isError) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i17 = i2 & 512;
        if (i17 != 0) {
            $dirty6 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty6 |= $composer4.changed(inputTransformation) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty7 |= ((i2 & 1024) == 0 && $composer4.changed(i)) ? 4 : 2;
        }
        int i18 = i2 & 2048;
        if (i18 != 0) {
            $dirty7 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty7 |= $composer4.changed(textObfuscationCharacter) ? 32 : 16;
        }
        int i19 = i2 & 4096;
        if (i19 != 0) {
            $dirty7 |= 384;
            i3 = i19;
        } else {
            i3 = i19;
            if (($changed1 & 384) == 0) {
                $dirty7 |= $composer4.changed(keyboardOptions) ? 256 : 128;
            }
        }
        int i20 = i2 & 8192;
        if (i20 != 0) {
            $dirty7 |= 3072;
            i4 = i20;
        } else {
            i4 = i20;
            if (($changed1 & 3072) == 0) {
                $dirty7 |= $composer4.changed(onKeyboardAction) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i2 & 16384) == 0 && $composer4.changed(shape)) {
                i12 = 16384;
            }
            $dirty7 |= i12;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i2 & 32768) == 0) {
                $dirty5 = $dirty7;
                if ($composer4.changed(colors)) {
                    i7 = 131072;
                }
                $dirty1 = $dirty5 | i7;
            } else {
                $dirty5 = $dirty7;
            }
            i7 = 65536;
            $dirty1 = $dirty5 | i7;
        } else {
            $dirty1 = $dirty7;
        }
        int i21 = i2 & 65536;
        if (i21 != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer4.changed(interactionSource) ? 1048576 : 524288;
        }
        if ($composer4.shouldExecute((($dirty6 & 306783379) == 306783378 && ($dirty1 & 599187) == 599186) ? false : true, $dirty6 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "229@13395L7,240@13998L22,241@14070L25");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i2 & 8) != 0) {
                    $dirty6 &= -7169;
                }
                if ((i2 & 1024) != 0) {
                    $dirty1 &= -15;
                }
                if ((i2 & 16384) != 0) {
                    $dirty1 &= -57345;
                }
                if ((i2 & 32768) != 0) {
                    $dirty1 &= -458753;
                }
                function16 = function5;
                i6 = i;
                onKeyboardAction3 = onKeyboardAction;
                shape3 = shape;
                interactionSource3 = interactionSource;
                $composer3 = $composer4;
                $dirty3 = $dirty6;
                enabled5 = enabled2;
                textStyle4 = textStyle2;
                function13 = function6;
                modifier4 = modifier2;
                $dirty4 = $dirty1;
                isError4 = isError;
                inputTransformation4 = inputTransformation;
                textObfuscationCharacter4 = textObfuscationCharacter;
                keyboardOptions4 = keyboardOptions;
                colors4 = colors;
            } else {
                if (i9 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i10 == 0) {
                    enabled4 = enabled2;
                } else {
                    enabled4 = true;
                }
                if ((i2 & 8) == 0) {
                    textStyle4 = textStyle2;
                    $dirty = $dirty6;
                } else {
                    ProvidableCompositionLocal<TextStyle> localTextStyle = TextKt.getLocalTextStyle();
                    ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object objConsume = $composer4.consume(localTextStyle);
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    textStyle4 = (TextStyle) objConsume;
                    $dirty = $dirty6 & (-7169);
                }
                if (i11 != 0) {
                    function13 = null;
                } else {
                    function13 = function6;
                }
                if (i13 == 0) {
                    function14 = function7;
                } else {
                    function14 = null;
                }
                if (i14 == 0) {
                    function15 = function8;
                } else {
                    function15 = null;
                }
                if (i15 == 0) {
                    function16 = function5;
                } else {
                    function16 = null;
                }
                if (i16 == 0) {
                    isError3 = isError;
                } else {
                    isError3 = false;
                }
                if (i17 == 0) {
                    inputTransformation3 = inputTransformation;
                } else {
                    inputTransformation3 = null;
                }
                if ((i2 & 1024) == 0) {
                    iM1360getRevealLastTypedvTwcZD0 = i;
                } else {
                    $dirty1 &= -15;
                    iM1360getRevealLastTypedvTwcZD0 = TextObfuscationMode.INSTANCE.m1360getRevealLastTypedvTwcZD0();
                }
                if (i18 == 0) {
                    textObfuscationCharacter3 = textObfuscationCharacter;
                } else {
                    textObfuscationCharacter3 = 8226;
                }
                if (i3 == 0) {
                    keyboardOptions3 = keyboardOptions;
                } else {
                    keyboardOptions3 = SecureTextFieldKeyboardOptions;
                }
                if (i4 == 0) {
                    onKeyboardAction3 = onKeyboardAction;
                } else {
                    onKeyboardAction3 = null;
                }
                if ((i2 & 16384) == 0) {
                    shape3 = shape;
                    $dirty2 = $dirty1;
                } else {
                    shape3 = TextFieldDefaults.INSTANCE.getOutlinedTextFieldShape($composer4, 6);
                    $dirty2 = $dirty1 & (-57345);
                }
                if ((i2 & 32768) == 0) {
                    $composer3 = $composer4;
                    colors3 = colors;
                } else {
                    colors3 = TextFieldDefaults.INSTANCE.m1996outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, 48, 2097151);
                    $composer3 = $composer4;
                    $dirty2 &= -458753;
                }
                if (i21 == 0) {
                    interactionSource3 = interactionSource;
                    colors4 = colors3;
                    enabled5 = enabled4;
                    $dirty3 = $dirty;
                    function7 = function14;
                    function8 = function15;
                    isError4 = isError3;
                    inputTransformation4 = inputTransformation3;
                    i6 = iM1360getRevealLastTypedvTwcZD0;
                    textObfuscationCharacter4 = textObfuscationCharacter3;
                    keyboardOptions4 = keyboardOptions3;
                    $dirty4 = $dirty2;
                } else {
                    colors4 = colors3;
                    interactionSource3 = null;
                    enabled5 = enabled4;
                    $dirty3 = $dirty;
                    function7 = function14;
                    function8 = function15;
                    isError4 = isError3;
                    inputTransformation4 = inputTransformation3;
                    i6 = iM1360getRevealLastTypedvTwcZD0;
                    textObfuscationCharacter4 = textObfuscationCharacter3;
                    keyboardOptions4 = keyboardOptions3;
                    $dirty4 = $dirty2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1856037588, $dirty3, $dirty4, "androidx.compose.material.OutlinedSecureTextField (SecureTextField.kt:243)");
            }
            if (interactionSource3 == null) {
                $composer3.startReplaceGroup(194183759);
                ComposerKt.sourceInformation($composer3, "245@14239L39");
                ComposerKt.sourceInformationMarkerStart($composer3, -1240661997, "CC(remember):SecureTextField.kt#9igjgp");
                Composer $this$cache$iv = $composer3;
                Object it$iv = $this$cache$iv.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $this$cache$iv.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endReplaceGroup();
                interactionSource4 = (MutableInteractionSource) it$iv;
            } else {
                $composer3.startReplaceGroup(-1240662648);
                $composer3.endReplaceGroup();
                interactionSource4 = interactionSource3;
            }
            $composer3.startReplaceGroup(-1240656902);
            ComposerKt.sourceInformation($composer3, "*247@14418L18");
            long $this$takeOrElse_u2dDxMtmZc$iv = textStyle4.m7358getColor0d7_KjU();
            if (!($this$takeOrElse_u2dDxMtmZc$iv != 16)) {
                $this$takeOrElse_u2dDxMtmZc$iv = colors4.textColor(enabled5, $composer3, (($dirty3 >> 6) & 14) | (($dirty4 >> 12) & 112)).getValue().m5107unboximpl();
            }
            $composer3.endReplaceGroup();
            long textColor = $this$takeOrElse_u2dDxMtmZc$iv;
            TextStyle mergedTextStyle = textStyle4.merge(new TextStyle(textColor, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777214, (DefaultConstructorMarker) null));
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            Composer $composer$iv = $composer3;
            TextStyle textStyle5 = textStyle4;
            boolean enabled6 = enabled5;
            ComposerKt.sourceInformationMarkerStart($composer$iv, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer$iv.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer$iv);
            Density density = (Density) objConsume2;
            if (function13 != null) {
                $composer3.startReplaceGroup(194660973);
                ComposerKt.sourceInformation($composer3, "261@14981L2");
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -1240638290, "CC(remember):SecureTextField.kt#9igjgp");
                Composer $this$cache$iv2 = $composer3;
                Object it$iv2 = $this$cache$iv2.rememberedValue();
                if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = new Function1() { // from class: androidx.compose.material.SecureTextFieldKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return SecureTextFieldKt.OutlinedSecureTextField_0vce7ms$lambda$2$0((SemanticsPropertyReceiver) obj);
                        }
                    };
                    $this$cache$iv2.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                companionM823paddingqDBjuR0$default = PaddingKt.m823paddingqDBjuR0$default(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv2), 0.0f, density.mo401toDpGaN1DYA(OutlinedTextFieldKt.getOutlinedTextFieldTopPadding()), 0.0f, 0.0f, 13, null);
                $composer3.endReplaceGroup();
            } else {
                $composer3.startReplaceGroup(195046334);
                $composer3.endReplaceGroup();
                companionM823paddingqDBjuR0$default = Modifier.INSTANCE;
            }
            Function2<? super Composer, ? super Integer, Unit> function17 = function7;
            boolean isError5 = isError4;
            TextFieldColors colors5 = colors4;
            Function2<? super Composer, ? super Integer, Unit> function18 = function8;
            KeyboardOptions keyboardOptions5 = keyboardOptions4;
            Composer $composer5 = $composer3;
            KeyboardActionHandler onKeyboardAction4 = onKeyboardAction3;
            BasicSecureTextFieldKt.m1137BasicSecureTextFieldegD4TGM(state, SizeKt.m849defaultMinSizeVpY3zN4(TextFieldImplKt.defaultErrorSemantics(modifier4.then(companionM823paddingqDBjuR0$default), isError4, Strings_androidKt.m1953getString4foXLRw(Strings.INSTANCE.m1947getDefaultErrorMessageUdPEhr4(), $composer3, 6)), TextFieldDefaults.INSTANCE.m1993getMinWidthD9Ej5fM(), TextFieldDefaults.INSTANCE.m1992getMinHeightD9Ej5fM()), enabled6, false, inputTransformation4, mergedTextStyle, keyboardOptions5, onKeyboardAction4, null, interactionSource4, new SolidColor(colors4.cursorColor(isError4, $composer3, (($dirty3 >> 24) & 14) | (($dirty4 >> 12) & 112)).getValue().m5107unboximpl(), null), new SecureTextFieldKt$OutlinedSecureTextField$3(state, enabled6, interactionSource4, isError5, function13, function17, function18, function16, shape3, colors5), i6, textObfuscationCharacter4, $composer5, ($dirty3 & 14) | ($dirty3 & 896) | (($dirty3 >> 15) & 57344) | (($dirty4 << 12) & 3670016) | (($dirty4 << 12) & 29360128), (($dirty4 << 6) & 896) | (($dirty4 << 6) & 7168), 264);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            textStyle3 = textStyle5;
            modifier3 = modifier4;
            keyboardOptions2 = keyboardOptions5;
            onKeyboardAction2 = onKeyboardAction4;
            i5 = i6;
            textObfuscationCharacter2 = textObfuscationCharacter4;
            interactionSource2 = interactionSource3;
            function9 = function13;
            function11 = function18;
            isError2 = isError5;
            shape2 = shape3;
            colors2 = colors5;
            enabled3 = enabled6;
            inputTransformation2 = inputTransformation4;
            function10 = function17;
            function12 = function16;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
            isError2 = isError;
            keyboardOptions2 = keyboardOptions;
            shape2 = shape;
            colors2 = colors;
            enabled3 = enabled2;
            textStyle3 = textStyle2;
            function9 = function6;
            function10 = function7;
            modifier3 = modifier2;
            function11 = function8;
            function12 = function5;
            inputTransformation2 = inputTransformation;
            i5 = i;
            textObfuscationCharacter2 = textObfuscationCharacter;
            onKeyboardAction2 = onKeyboardAction;
            interactionSource2 = interactionSource;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.SecureTextFieldKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SecureTextFieldKt.OutlinedSecureTextField_0vce7ms$lambda$4(state, modifier3, enabled3, textStyle3, function9, function10, function11, function12, isError2, inputTransformation2, i5, textObfuscationCharacter2, keyboardOptions2, onKeyboardAction2, shape2, colors2, interactionSource2, $changed, $changed1, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit OutlinedSecureTextField_0vce7ms$lambda$2$0(SemanticsPropertyReceiver $this$semantics) {
        return Unit.INSTANCE;
    }
}
