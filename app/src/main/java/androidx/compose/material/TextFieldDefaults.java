package androidx.compose.material;

import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.cloudinary.metadata.MetadataField;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JE\u0010\u0019\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u0005¢\u0006\u0004\b$\u0010%JM\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\b\u0002\u0010(\u001a\u00020\u000e2\b\b\u0002\u0010)\u001a\u00020\u00052\b\b\u0002\u0010*\u001a\u00020\u0005H\u0007¢\u0006\u0004\b+\u0010,J5\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020\u00052\b\b\u0002\u00100\u001a\u00020\u00052\b\b\u0002\u00101\u001a\u00020\u00052\b\b\u0002\u00102\u001a\u00020\u0005¢\u0006\u0004\b3\u00104J5\u00105\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020\u00052\b\b\u0002\u00101\u001a\u00020\u00052\b\b\u0002\u00100\u001a\u00020\u00052\b\b\u0002\u00102\u001a\u00020\u0005¢\u0006\u0004\b6\u00104J5\u00107\u001a\u00020.2\b\b\u0002\u0010/\u001a\u00020\u00052\b\b\u0002\u00101\u001a\u00020\u00052\b\b\u0002\u00100\u001a\u00020\u00052\b\b\u0002\u00102\u001a\u00020\u0005¢\u0006\u0004\b8\u00104Já\u0001\u00109\u001a\u00020!2\b\b\u0002\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020;2\b\b\u0002\u0010=\u001a\u00020;2\b\b\u0002\u0010>\u001a\u00020;2\b\b\u0002\u0010?\u001a\u00020;2\b\b\u0002\u0010@\u001a\u00020;2\b\b\u0002\u0010A\u001a\u00020;2\b\b\u0002\u0010B\u001a\u00020;2\b\b\u0002\u0010C\u001a\u00020;2\b\b\u0002\u0010D\u001a\u00020;2\b\b\u0002\u0010E\u001a\u00020;2\b\b\u0002\u0010F\u001a\u00020;2\b\b\u0002\u0010G\u001a\u00020;2\b\b\u0002\u0010H\u001a\u00020;2\b\b\u0002\u0010I\u001a\u00020;2\b\b\u0002\u0010J\u001a\u00020;2\b\b\u0002\u0010K\u001a\u00020;2\b\b\u0002\u0010L\u001a\u00020;2\b\b\u0002\u0010M\u001a\u00020;2\b\b\u0002\u0010N\u001a\u00020;2\b\b\u0002\u0010O\u001a\u00020;H\u0007¢\u0006\u0004\bP\u0010QJá\u0001\u0010R\u001a\u00020!2\b\b\u0002\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020;2\b\b\u0002\u0010=\u001a\u00020;2\b\b\u0002\u0010>\u001a\u00020;2\b\b\u0002\u0010?\u001a\u00020;2\b\b\u0002\u0010S\u001a\u00020;2\b\b\u0002\u0010T\u001a\u00020;2\b\b\u0002\u0010U\u001a\u00020;2\b\b\u0002\u0010V\u001a\u00020;2\b\b\u0002\u0010D\u001a\u00020;2\b\b\u0002\u0010E\u001a\u00020;2\b\b\u0002\u0010F\u001a\u00020;2\b\b\u0002\u0010G\u001a\u00020;2\b\b\u0002\u0010H\u001a\u00020;2\b\b\u0002\u0010I\u001a\u00020;2\b\b\u0002\u0010J\u001a\u00020;2\b\b\u0002\u0010K\u001a\u00020;2\b\b\u0002\u0010L\u001a\u00020;2\b\b\u0002\u0010M\u001a\u00020;2\b\b\u0002\u0010N\u001a\u00020;2\b\b\u0002\u0010O\u001a\u00020;H\u0007¢\u0006\u0004\bW\u0010QJÌ\u0001\u0010X\u001a\u00020'2\u0006\u0010Y\u001a\u00020Z2\u0011\u0010[\u001a\r\u0012\u0004\u0012\u00020'0\\¢\u0006\u0002\b]2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010^\u001a\u00020\u001c2\u0006\u0010_\u001a\u00020`2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010b\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010c\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010d\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\b\b\u0002\u0010(\u001a\u00020\u000e2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010e\u001a\u00020.H\u0007¢\u0006\u0002\u0010fJá\u0001\u0010g\u001a\u00020'2\u0006\u0010Y\u001a\u00020Z2\u0011\u0010[\u001a\r\u0012\u0004\u0012\u00020'0\\¢\u0006\u0002\b]2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010^\u001a\u00020\u001c2\u0006\u0010_\u001a\u00020`2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010b\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010c\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010d\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\b\b\u0002\u0010(\u001a\u00020\u000e2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010e\u001a\u00020.2\u0013\b\u0002\u0010h\u001a\r\u0012\u0004\u0012\u00020'0\\¢\u0006\u0002\b]H\u0007¢\u0006\u0002\u0010iJÂ\u0001\u0010X\u001a\u00020'2\u0006\u0010Y\u001a\u00020Z2\u0011\u0010[\u001a\r\u0012\u0004\u0012\u00020'0\\¢\u0006\u0002\b]2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010^\u001a\u00020\u001c2\u0006\u0010_\u001a\u00020`2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010b\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010c\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010d\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010e\u001a\u00020.H\u0007¢\u0006\u0002\u0010jJ×\u0001\u0010g\u001a\u00020'2\u0006\u0010Y\u001a\u00020Z2\u0011\u0010[\u001a\r\u0012\u0004\u0012\u00020'0\\¢\u0006\u0002\b]2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010^\u001a\u00020\u001c2\u0006\u0010_\u001a\u00020`2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\u0015\b\u0002\u0010a\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010b\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010c\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\u0015\b\u0002\u0010d\u001a\u000f\u0012\u0004\u0012\u00020'\u0018\u00010\\¢\u0006\u0002\b]2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010e\u001a\u00020.2\u0013\b\u0002\u0010h\u001a\r\u0012\u0004\u0012\u00020'0\\¢\u0006\u0002\b]H\u0007¢\u0006\u0002\u0010kR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e8G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u000e\u0010\u0017\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000¨\u0006l"}, d2 = {"Landroidx/compose/material/TextFieldDefaults;", "", "<init>", "()V", "MinHeight", "Landroidx/compose/ui/unit/Dp;", "getMinHeight-D9Ej5fM", "()F", "F", "MinWidth", "getMinWidth-D9Ej5fM", "IconOpacity", "", "TextFieldShape", "Landroidx/compose/ui/graphics/Shape;", "getTextFieldShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "OutlinedTextFieldShape", "getOutlinedTextFieldShape", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM", "FocusedBorderThickness", "getFocusedBorderThickness-D9Ej5fM", "BackgroundOpacity", "UnfocusedIndicatorLineOpacity", "indicatorLine", "Landroidx/compose/ui/Modifier;", "enabled", "", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "colors", "Landroidx/compose/material/TextFieldColors;", "focusedIndicatorLineThickness", "unfocusedIndicatorLineThickness", "indicatorLine-gv0btCI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material/TextFieldColors;FF)Landroidx/compose/ui/Modifier;", "BorderBox", "", "shape", "focusedBorderThickness", "unfocusedBorderThickness", "BorderBox-nbWgWpA", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "textFieldWithLabelPadding", "Landroidx/compose/foundation/layout/PaddingValues;", TtmlNode.START, TtmlNode.END, "top", "bottom", "textFieldWithLabelPadding-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "textFieldWithoutLabelPadding", "textFieldWithoutLabelPadding-a9UjIt4", "outlinedTextFieldPadding", "outlinedTextFieldPadding-a9UjIt4", "textFieldColors", "textColor", "Landroidx/compose/ui/graphics/Color;", "disabledTextColor", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "cursorColor", "errorCursorColor", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "leadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "trailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "placeholderColor", "disabledPlaceholderColor", "textFieldColors-dx8h9Zs", "(JJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIII)Landroidx/compose/material/TextFieldColors;", "outlinedTextFieldColors", "focusedBorderColor", "unfocusedBorderColor", "disabledBorderColor", "errorBorderColor", "outlinedTextFieldColors-dx8h9Zs", "TextFieldDecorationBox", "value", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", MetadataField.LABEL, "placeholder", "leadingIcon", "trailingIcon", "contentPadding", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;III)V", "OutlinedTextFieldDecorationBox", OutlinedTextFieldKt.BorderId, "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "material"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextFieldDefaults {
    public static final int $stable = 0;
    public static final float BackgroundOpacity = 0.12f;
    public static final float IconOpacity = 0.54f;
    public static final float UnfocusedIndicatorLineOpacity = 0.42f;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m7905constructorimpl(56);
    private static final float MinWidth = Dp.m7905constructorimpl(280);
    private static final float UnfocusedBorderThickness = Dp.m7905constructorimpl(1);
    private static final float FocusedBorderThickness = Dp.m7905constructorimpl(2);

    static final Unit BorderBox_nbWgWpA$lambda$0(TextFieldDefaults textFieldDefaults, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape, float f, float f2, int i, int i2, Composer composer, int i3) {
        textFieldDefaults.m1990BorderBoxnbWgWpA(z, z2, interactionSource, textFieldColors, shape, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextFieldDecorationBox$lambda$1(TextFieldDefaults textFieldDefaults, String str, Function2 function2, boolean z, boolean z2, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z3, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, Function2 function7, int i, int i2, int i3, Composer composer, int i4) {
        textFieldDefaults.OutlinedTextFieldDecorationBox(str, function2, z, z2, visualTransformation, interactionSource, z3, function3, function4, function5, function6, shape, textFieldColors, paddingValues, function7, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit OutlinedTextFieldDecorationBox$lambda$3(TextFieldDefaults textFieldDefaults, String str, Function2 function2, boolean z, boolean z2, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z3, Function2 function3, Function2 function4, Function2 function5, Function2 function6, TextFieldColors textFieldColors, PaddingValues paddingValues, Function2 function7, int i, int i2, int i3, Composer composer, int i4) {
        textFieldDefaults.OutlinedTextFieldDecorationBox(str, function2, z, z2, visualTransformation, interactionSource, z3, function3, function4, function5, function6, textFieldColors, paddingValues, function7, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit TextFieldDecorationBox$lambda$0(TextFieldDefaults textFieldDefaults, String str, Function2 function2, boolean z, boolean z2, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z3, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, int i, int i2, int i3, Composer composer, int i4) {
        textFieldDefaults.TextFieldDecorationBox(str, function2, z, z2, visualTransformation, interactionSource, z3, function3, function4, function5, function6, shape, textFieldColors, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit TextFieldDecorationBox$lambda$1(TextFieldDefaults textFieldDefaults, String str, Function2 function2, boolean z, boolean z2, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z3, Function2 function3, Function2 function4, Function2 function5, Function2 function6, TextFieldColors textFieldColors, PaddingValues paddingValues, int i, int i2, int i3, Composer composer, int i4) {
        textFieldDefaults.TextFieldDecorationBox(str, function2, z, z2, visualTransformation, interactionSource, z3, function3, function4, function5, function6, textFieldColors, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    private TextFieldDefaults() {
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1992getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1993getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    public final Shape getTextFieldShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1117199624, "C(<get-TextFieldShape>)221@8326L6:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1117199624, $changed, -1, "androidx.compose.material.TextFieldDefaults.<get-TextFieldShape> (TextFieldDefaults.kt:221)");
        }
        CornerBasedShape cornerBasedShapeCopy$default = CornerBasedShape.copy$default(MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall(), null, null, CornerSizeKt.getZeroCornerSize(), CornerSizeKt.getZeroCornerSize(), 3, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return cornerBasedShapeCopy$default;
    }

    public final Shape getOutlinedTextFieldShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1899109048, "C(<get-OutlinedTextFieldShape>)228@8634L6:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1899109048, $changed, -1, "androidx.compose.material.TextFieldDefaults.<get-OutlinedTextFieldShape> (TextFieldDefaults.kt:228)");
        }
        CornerBasedShape small = MaterialTheme.INSTANCE.getShapes($composer, 6).getSmall();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return small;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1994getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m1991getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1986indicatorLinegv0btCI$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, float f, float f2, int i, Object obj) {
        float f3;
        float f4;
        if ((i & 16) == 0) {
            f3 = f;
        } else {
            f3 = FocusedBorderThickness;
        }
        if ((i & 32) == 0) {
            f4 = f2;
        } else {
            f4 = UnfocusedBorderThickness;
        }
        return textFieldDefaults.m1995indicatorLinegv0btCI(modifier, z, z2, interactionSource, textFieldColors, f3, f4);
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final Modifier m1995indicatorLinegv0btCI(Modifier $this$indicatorLine_u2dgv0btCI, final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, final float f, final float f2) {
        return ComposedModifierKt.composed($this$indicatorLine_u2dgv0btCI, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material.TextFieldDefaults$indicatorLine-gv0btCI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                $this$null.setName("indicatorLine");
                $this$null.getProperties().set("enabled", Boolean.valueOf(enabled));
                $this$null.getProperties().set("isError", Boolean.valueOf(isError));
                $this$null.getProperties().set("interactionSource", interactionSource);
                $this$null.getProperties().set("colors", colors);
                $this$null.getProperties().set("focusedIndicatorLineThickness", Dp.m7903boximpl(f));
                $this$null.getProperties().set("unfocusedIndicatorLineThickness", Dp.m7903boximpl(f2));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.material.TextFieldDefaults$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextFieldDefaults.indicatorLine_gv0btCI$lambda$1(enabled, isError, interactionSource, colors, f, f2, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    static final Modifier indicatorLine_gv0btCI$lambda$1(boolean $enabled, boolean $isError, InteractionSource $interactionSource, TextFieldColors $colors, float f, float f2, Modifier $this$composed, Composer $composer, int $changed) {
        $composer.startReplaceGroup(1398930845);
        ComposerKt.sourceInformation($composer, "C289@11188L375:TextFieldDefaults.kt#jmzs0o");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1398930845, $changed, -1, "androidx.compose.material.TextFieldDefaults.indicatorLine.<anonymous> (TextFieldDefaults.kt:288)");
        }
        State stroke = TextFieldDefaultsKt.m2002animateBorderStrokeAsStateNuRrP5Q($enabled, $isError, $interactionSource, $colors, f, f2, $composer, 0);
        Modifier modifierDrawIndicatorLine = TextFieldKt.drawIndicatorLine(Modifier.INSTANCE, (BorderStroke) stroke.getValue());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierDrawIndicatorLine;
    }

    /* JADX INFO: renamed from: BorderBox-nbWgWpA, reason: not valid java name */
    public final void m1990BorderBoxnbWgWpA(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, float f, float f2, Composer $composer, final int $changed, final int i) {
        boolean z;
        boolean z2;
        InteractionSource interactionSource2;
        TextFieldColors textFieldColors;
        Shape shape2;
        float f3;
        float f4;
        Composer $composer2;
        final float f5;
        final float f6;
        final Shape shape3;
        float f7;
        int i2;
        float f8;
        Composer $composer3 = $composer.startRestartGroup(943754022);
        ComposerKt.sourceInformation($composer3, "C(BorderBox)N(enabled,isError,interactionSource,colors,shape,focusedBorderThickness:c#ui.unit.Dp,unfocusedBorderThickness:c#ui.unit.Dp)326@12897L333,334@13239L47:TextFieldDefaults.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = enabled;
        }
        if (($changed & 48) == 0) {
            z2 = isError;
            $dirty |= $composer3.changed(z2) ? 32 : 16;
        } else {
            z2 = isError;
        }
        if (($changed & 384) == 0) {
            interactionSource2 = interactionSource;
            $dirty |= $composer3.changed(interactionSource2) ? 256 : 128;
        } else {
            interactionSource2 = interactionSource;
        }
        if (($changed & 3072) == 0) {
            textFieldColors = colors;
            $dirty |= $composer3.changed(textFieldColors) ? 2048 : 1024;
        } else {
            textFieldColors = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i3 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty |= i3;
            } else {
                shape2 = shape;
            }
            $dirty |= i3;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                f3 = f;
                int i4 = $composer3.changed(f3) ? 131072 : 65536;
                $dirty |= i4;
            } else {
                f3 = f;
            }
            $dirty |= i4;
        } else {
            f3 = f;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                f4 = f2;
                int i5 = $composer3.changed(f4) ? 1048576 : 524288;
                $dirty |= i5;
            } else {
                f4 = f2;
            }
            $dirty |= i5;
        } else {
            f4 = f2;
        }
        if ((12582912 & $changed) == 0) {
            $dirty |= $composer3.changed(this) ? 8388608 : 4194304;
        }
        if ($composer3.shouldExecute((4793491 & $dirty) != 4793490, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "321@12700L22");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                f7 = f3;
                i2 = 0;
                f8 = f4;
            } else {
                if ((i & 16) != 0) {
                    shape2 = getOutlinedTextFieldShape($composer3, ($dirty >> 21) & 14);
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    f3 = FocusedBorderThickness;
                    $dirty &= -458753;
                }
                if ((i & 64) == 0) {
                    f7 = f3;
                    i2 = 0;
                    f8 = f4;
                } else {
                    $dirty &= -3670017;
                    f7 = f3;
                    i2 = 0;
                    f8 = UnfocusedBorderThickness;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(943754022, $dirty, -1, "androidx.compose.material.TextFieldDefaults.BorderBox (TextFieldDefaults.kt:324)");
            }
            State borderStroke = TextFieldDefaultsKt.m2002animateBorderStrokeAsStateNuRrP5Q(z, z2, interactionSource2, textFieldColors, f7, f8, $composer3, ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty >> 3) & 57344) | (($dirty >> 3) & 458752));
            $composer2 = $composer3;
            BoxKt.Box(BorderKt.border(Modifier.INSTANCE, (BorderStroke) borderStroke.getValue(), shape2), $composer2, i2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            f6 = f7;
            f5 = f8;
            shape3 = shape2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            f5 = f4;
            f6 = f3;
            shape3 = shape2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldDefaults$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldDefaults.BorderBox_nbWgWpA$lambda$0(this.f$0, enabled, isError, interactionSource, colors, shape3, f6, f5, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1988textFieldWithLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getFirstBaselineOffset();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldBottomPadding();
        }
        return textFieldDefaults.m1999textFieldWithLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1999textFieldWithLabelPaddinga9UjIt4(float start, float end, float top, float bottom) {
        return PaddingKt.m815PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1989textFieldWithoutLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m2000textFieldWithoutLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m2000textFieldWithoutLabelPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m815PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m1987outlinedTextFieldPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldImplKt.getTextFieldPadding();
        }
        return textFieldDefaults.m1997outlinedTextFieldPaddinga9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m1997outlinedTextFieldPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m815PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: textFieldColors-dx8h9Zs, reason: not valid java name */
    public final TextFieldColors m1998textFieldColorsdx8h9Zs(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long jM5095copywmQWz5c;
        long jM5095copywmQWz5c2;
        long jM5095copywmQWz5c3;
        long jM5095copywmQWz5c4;
        long j22;
        long jM5095copywmQWz5c5;
        long jM5095copywmQWz5c6;
        long j23;
        long jM5095copywmQWz5c7;
        long jM5095copywmQWz5c8;
        long j24;
        long jM5095copywmQWz5c9;
        long jM5095copywmQWz5c10;
        long jM5095copywmQWz5c11;
        long j25;
        long jM5095copywmQWz5c12;
        long jM5095copywmQWz5c13;
        long jM5095copywmQWz5c14;
        ComposerKt.sourceInformationMarkerStart($composer, 231892599, "C(textFieldColors)N(textColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color,cursorColor:c#ui.graphics.Color,errorCursorColor:c#ui.graphics.Color,focusedIndicatorColor:c#ui.graphics.Color,unfocusedIndicatorColor:c#ui.graphics.Color,disabledIndicatorColor:c#ui.graphics.Color,errorIndicatorColor:c#ui.graphics.Color,leadingIconColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,errorLeadingIconColor:c#ui.graphics.Color,trailingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color,errorTrailingIconColor:c#ui.graphics.Color,focusedLabelColor:c#ui.graphics.Color,unfocusedLabelColor:c#ui.graphics.Color,disabledLabelColor:c#ui.graphics.Color,errorLabelColor:c#ui.graphics.Color,placeholderColor:c#ui.graphics.Color,disabledPlaceholderColor:c#ui.graphics.Color)374@14935L7,374@14966L7,375@15039L8,376@15097L6,377@15190L6,378@15254L6,379@15321L6,379@15362L4,381@15436L6,382@15588L8,383@15650L6,384@15712L6,385@15841L8,387@15958L6,388@16089L8,389@16154L6,390@16217L6,390@16258L4,391@16316L6,391@16351L6,392@16434L8,393@16492L6,394@16554L6,394@16589L6,395@16675L8:TextFieldDefaults.kt#jmzs0o");
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
            long j26 = jM5095copywmQWz5c4;
            j22 = j26;
            jM5095copywmQWz5c5 = Color.m5095copywmQWz5c(j26, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j26) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j26) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j26) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j26) : 0.0f);
        } else {
            j22 = jM5095copywmQWz5c4;
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
            long j27 = jM5095copywmQWz5c6;
            jM5095copywmQWz5c7 = Color.m5095copywmQWz5c(j27, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j27) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j27) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j27) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j27) : 0.0f);
            j23 = j27;
        } else {
            j23 = jM5095copywmQWz5c6;
            jM5095copywmQWz5c7 = j11;
        }
        long j28 = (i & 2048) != 0 ? j23 : j12;
        if ((i & 4096) != 0) {
            long jM1764getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c8 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU4) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU4) : 0.0f);
        } else {
            jM5095copywmQWz5c8 = j13;
        }
        if ((i & 8192) != 0) {
            long j29 = jM5095copywmQWz5c8;
            jM5095copywmQWz5c9 = Color.m5095copywmQWz5c(j29, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j29) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j29) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j29) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j29) : 0.0f);
            j24 = j29;
        } else {
            j24 = jM5095copywmQWz5c8;
            jM5095copywmQWz5c9 = j14;
        }
        long jM1759getError0d7_KjU3 = (i & 16384) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j15;
        if ((32768 & i) != 0) {
            long jM1765getPrimary0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU();
            jM5095copywmQWz5c10 = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU3) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU3) : 0.0f);
        } else {
            jM5095copywmQWz5c10 = j16;
        }
        if ((65536 & i) != 0) {
            long jM1764getOnSurface0d7_KjU5 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c11 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU5, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU5) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU5) : 0.0f);
        } else {
            jM5095copywmQWz5c11 = j17;
        }
        if ((131072 & i) != 0) {
            long j30 = jM5095copywmQWz5c11;
            jM5095copywmQWz5c12 = Color.m5095copywmQWz5c(j30, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j30) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j30) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j30) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j30) : 0.0f);
            j25 = j30;
        } else {
            j25 = jM5095copywmQWz5c11;
            jM5095copywmQWz5c12 = j18;
        }
        long jM1759getError0d7_KjU4 = (262144 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j19;
        if ((524288 & i) != 0) {
            long jM1764getOnSurface0d7_KjU6 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c13 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU6, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU6) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU6) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU6) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU6) : 0.0f);
        } else {
            jM5095copywmQWz5c13 = j20;
        }
        if ((i & 1048576) != 0) {
            long j31 = jM5095copywmQWz5c13;
            jM5095copywmQWz5c14 = Color.m5095copywmQWz5c(j31, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j31) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j31) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j31) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j31) : 0.0f);
        } else {
            jM5095copywmQWz5c14 = j21;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(231892599, $changed, $changed1, "androidx.compose.material.TextFieldDefaults.textFieldColors (TextFieldDefaults.kt:397)");
        }
        DefaultTextFieldColors defaultTextFieldColors = new DefaultTextFieldColors(jM5095copywmQWz5c, jM5095copywmQWz5c15, jM1765getPrimary0d7_KjU, jM1759getError0d7_KjU, jM5095copywmQWz5c3, j22, jM1759getError0d7_KjU2, jM5095copywmQWz5c5, j23, jM5095copywmQWz5c7, j28, j24, jM5095copywmQWz5c9, jM1759getError0d7_KjU3, jM5095copywmQWz5c2, jM5095copywmQWz5c10, j25, jM5095copywmQWz5c12, jM1759getError0d7_KjU4, jM5095copywmQWz5c13, jM5095copywmQWz5c14, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldColors;
    }

    /* JADX INFO: renamed from: outlinedTextFieldColors-dx8h9Zs, reason: not valid java name */
    public final TextFieldColors m1996outlinedTextFieldColorsdx8h9Zs(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, Composer $composer, int $changed, int $changed1, int $changed2, int i) {
        long jM5095copywmQWz5c;
        long jM5095copywmQWz5c2;
        long jM5095copywmQWz5c3;
        long j22;
        long jM5095copywmQWz5c4;
        long jM5095copywmQWz5c5;
        long j23;
        long jM5095copywmQWz5c6;
        long jM5095copywmQWz5c7;
        long j24;
        long jM5095copywmQWz5c8;
        long jM5095copywmQWz5c9;
        long jM5095copywmQWz5c10;
        long j25;
        long jM5095copywmQWz5c11;
        long jM5095copywmQWz5c12;
        long jM5095copywmQWz5c13;
        ComposerKt.sourceInformationMarkerStart($composer, 1762667317, "C(outlinedTextFieldColors)N(textColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,backgroundColor:c#ui.graphics.Color,cursorColor:c#ui.graphics.Color,errorCursorColor:c#ui.graphics.Color,focusedBorderColor:c#ui.graphics.Color,unfocusedBorderColor:c#ui.graphics.Color,disabledBorderColor:c#ui.graphics.Color,errorBorderColor:c#ui.graphics.Color,leadingIconColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,errorLeadingIconColor:c#ui.graphics.Color,trailingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color,errorTrailingIconColor:c#ui.graphics.Color,focusedLabelColor:c#ui.graphics.Color,unfocusedLabelColor:c#ui.graphics.Color,disabledLabelColor:c#ui.graphics.Color,errorLabelColor:c#ui.graphics.Color,placeholderColor:c#ui.graphics.Color,disabledPlaceholderColor:c#ui.graphics.Color)428@18205L7,428@18236L7,429@18309L8,431@18415L6,432@18479L6,433@18543L6,433@18584L4,435@18655L6,435@18698L8,436@18793L8,437@18852L6,438@18914L6,439@19043L8,441@19160L6,442@19291L8,443@19356L6,444@19419L6,444@19460L4,445@19518L6,445@19553L6,446@19636L8,447@19694L6,448@19756L6,448@19791L6,449@19877L8:TextFieldDefaults.kt#jmzs0o");
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
        long jM5095copywmQWz5c14 = (i & 2) != 0 ? Color.m5095copywmQWz5c(jM5095copywmQWz5c, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM5095copywmQWz5c) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM5095copywmQWz5c) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM5095copywmQWz5c) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM5095copywmQWz5c) : 0.0f) : j2;
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
            long j26 = jM5095copywmQWz5c3;
            j22 = j26;
            jM5095copywmQWz5c4 = Color.m5095copywmQWz5c(j26, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j26) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j26) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j26) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j26) : 0.0f);
        } else {
            j22 = jM5095copywmQWz5c3;
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
            long j27 = jM5095copywmQWz5c5;
            jM5095copywmQWz5c6 = Color.m5095copywmQWz5c(j27, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j27) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j27) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j27) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j27) : 0.0f);
            j23 = j27;
        } else {
            j23 = jM5095copywmQWz5c5;
            jM5095copywmQWz5c6 = j11;
        }
        long j28 = (i & 2048) != 0 ? j23 : j12;
        if ((i & 4096) != 0) {
            long jM1764getOnSurface0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c7 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU3) : 0.54f, (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU3) : 0.0f);
        } else {
            jM5095copywmQWz5c7 = j13;
        }
        if ((i & 8192) != 0) {
            long j29 = jM5095copywmQWz5c7;
            jM5095copywmQWz5c8 = Color.m5095copywmQWz5c(j29, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j29) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j29) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j29) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j29) : 0.0f);
            j24 = j29;
        } else {
            j24 = jM5095copywmQWz5c7;
            jM5095copywmQWz5c8 = j14;
        }
        long jM1759getError0d7_KjU3 = (i & 16384) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j15;
        if ((32768 & i) != 0) {
            long jM1765getPrimary0d7_KjU3 = MaterialTheme.INSTANCE.getColors($composer, 6).m1765getPrimary0d7_KjU();
            jM5095copywmQWz5c9 = Color.m5095copywmQWz5c(jM1765getPrimary0d7_KjU3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1765getPrimary0d7_KjU3) : ContentAlpha.INSTANCE.getHigh($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1765getPrimary0d7_KjU3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1765getPrimary0d7_KjU3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1765getPrimary0d7_KjU3) : 0.0f);
        } else {
            jM5095copywmQWz5c9 = j16;
        }
        if ((65536 & i) != 0) {
            long jM1764getOnSurface0d7_KjU4 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c10 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU4, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU4) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU4) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU4) : 0.0f);
        } else {
            jM5095copywmQWz5c10 = j17;
        }
        if ((131072 & i) != 0) {
            long j30 = jM5095copywmQWz5c10;
            jM5095copywmQWz5c11 = Color.m5095copywmQWz5c(j30, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j30) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j30) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j30) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j30) : 0.0f);
            j25 = j30;
        } else {
            j25 = jM5095copywmQWz5c10;
            jM5095copywmQWz5c11 = j18;
        }
        long jM1759getError0d7_KjU4 = (262144 & i) != 0 ? MaterialTheme.INSTANCE.getColors($composer, 6).m1759getError0d7_KjU() : j19;
        if ((524288 & i) != 0) {
            long jM1764getOnSurface0d7_KjU5 = MaterialTheme.INSTANCE.getColors($composer, 6).m1764getOnSurface0d7_KjU();
            jM5095copywmQWz5c12 = Color.m5095copywmQWz5c(jM1764getOnSurface0d7_KjU5, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jM1764getOnSurface0d7_KjU5) : ContentAlpha.INSTANCE.getMedium($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jM1764getOnSurface0d7_KjU5) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jM1764getOnSurface0d7_KjU5) : 0.0f);
        } else {
            jM5095copywmQWz5c12 = j20;
        }
        if ((i & 1048576) != 0) {
            long j31 = jM5095copywmQWz5c12;
            jM5095copywmQWz5c13 = Color.m5095copywmQWz5c(j31, (14 & 1) != 0 ? Color.m5099getAlphaimpl(j31) : ContentAlpha.INSTANCE.getDisabled($composer, 6), (14 & 2) != 0 ? Color.m5103getRedimpl(j31) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(j31) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(j31) : 0.0f);
        } else {
            jM5095copywmQWz5c13 = j21;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1762667317, $changed, $changed1, "androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors (TextFieldDefaults.kt:451)");
        }
        DefaultTextFieldColors defaultTextFieldColors = new DefaultTextFieldColors(jM5095copywmQWz5c, jM5095copywmQWz5c14, jM1765getPrimary0d7_KjU, jM1759getError0d7_KjU, jM5095copywmQWz5c2, j22, jM1759getError0d7_KjU2, jM5095copywmQWz5c4, j23, jM5095copywmQWz5c6, j28, j24, jM5095copywmQWz5c8, jM1759getError0d7_KjU3, jM5132getTransparent0d7_KjU, jM5095copywmQWz5c9, j25, jM5095copywmQWz5c11, jM1759getError0d7_KjU4, jM5095copywmQWz5c12, jM5095copywmQWz5c13, null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return defaultTextFieldColors;
    }

    public final void TextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Composer $composer, final int $changed, final int $changed1, int i) {
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Composer $composer2;
        int $dirty;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final TextFieldColors colors2;
        final PaddingValues contentPadding2;
        final boolean isError2;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Shape shape2;
        boolean isError3;
        Function2<? super Composer, ? super Integer, Unit> function12;
        Function2<? super Composer, ? super Integer, Unit> function13;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Shape shape3;
        int $dirty2;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        boolean isError4;
        TextFieldColors colors4;
        int $dirty1;
        Shape shape4;
        PaddingValues contentPadding4;
        int i2 = i;
        Composer $composer3 = $composer.startRestartGroup(2088762355);
        ComposerKt.sourceInformation($composer3, "C(TextFieldDecorationBox)N(value,innerTextField,enabled,singleLine,visualTransformation,interactionSource,isError,label,placeholder,leadingIcon,trailingIcon,shape,colors,contentPadding)551@25856L624:TextFieldDefaults.kt#jmzs0o");
        int $dirty3 = $changed;
        int $dirty4 = $changed1;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        int i3 = 128;
        if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changed(enabled) ? 256 : 128;
        }
        int i4 = 1024;
        if (($changed & 3072) == 0) {
            $dirty3 |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty3 |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changed(interactionSource) ? 131072 : 65536;
        }
        int i5 = i2 & 64;
        if (i5 != 0) {
            $dirty3 |= 1572864;
            z = isError;
        } else if (($changed & 1572864) == 0) {
            z = isError;
            $dirty3 |= $composer3.changed(z) ? 1048576 : 524288;
        } else {
            z = isError;
        }
        int i6 = i2 & 128;
        if (i6 != 0) {
            $dirty3 |= 12582912;
            function7 = function3;
        } else if (($changed & 12582912) == 0) {
            function7 = function3;
            $dirty3 |= $composer3.changedInstance(function7) ? 8388608 : 4194304;
        } else {
            function7 = function3;
        }
        int i7 = i2 & 256;
        if (i7 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i8 = i2 & 512;
        if (i8 != 0) {
            $dirty3 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer3.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i9 = i2 & 1024;
        if (i9 != 0) {
            $dirty4 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(function6) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty4 |= ((i2 & 2048) == 0 && $composer3.changed(shape)) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            if ((i2 & 4096) == 0 && $composer3.changed(colors)) {
                i3 = 256;
            }
            $dirty4 |= i3;
        }
        if (($changed1 & 3072) == 0) {
            if ((i2 & 8192) == 0 && $composer3.changed(contentPadding)) {
                i4 = 2048;
            }
            $dirty4 |= i4;
        }
        if (($changed1 & 24576) == 0) {
            $dirty4 |= $composer3.changed(this) ? 16384 : 8192;
        }
        if ($composer3.shouldExecute(((306783379 & $dirty3) == 306783378 && ($dirty4 & 9363) == 9362) ? false : true, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "542@25571L14,543@25621L17");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i2 & 2048) != 0) {
                    $dirty4 &= -113;
                }
                if ((i2 & 4096) != 0) {
                    $dirty4 &= -897;
                }
                if ((i2 & 8192) != 0) {
                    $dirty4 &= -7169;
                }
                function8 = function5;
                function9 = function6;
                shape4 = shape;
                colors4 = colors;
                contentPadding3 = contentPadding;
                $composer2 = $composer3;
                $dirty2 = $dirty3;
                $dirty1 = $dirty4;
                isError4 = z;
                function10 = function7;
                function11 = function4;
            } else {
                if (i5 != 0) {
                    isError3 = false;
                } else {
                    isError3 = z;
                }
                if (i6 == 0) {
                    function12 = function7;
                } else {
                    function12 = null;
                }
                if (i7 == 0) {
                    function13 = function4;
                } else {
                    function13 = null;
                }
                if (i8 == 0) {
                    function14 = function5;
                } else {
                    function14 = null;
                }
                if (i9 == 0) {
                    function15 = function6;
                } else {
                    function15 = null;
                }
                if ((i2 & 2048) == 0) {
                    shape3 = shape;
                } else {
                    Shape shape5 = getTextFieldShape($composer3, ($dirty4 >> 12) & 14);
                    $dirty4 &= -113;
                    shape3 = shape5;
                }
                if ((i2 & 4096) != 0) {
                    $dirty2 = $dirty3;
                    colors3 = m1998textFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 0, 0, ($dirty4 >> 9) & 112, 2097151);
                    $composer2 = $composer3;
                    $dirty4 &= -897;
                } else {
                    $composer2 = $composer3;
                    $dirty2 = $dirty3;
                    colors3 = colors;
                }
                i2 = i;
                if ((i2 & 8192) == 0) {
                    contentPadding3 = contentPadding;
                    isError4 = isError3;
                    colors4 = colors3;
                    $dirty1 = $dirty4;
                    function10 = function12;
                    function11 = function13;
                    function8 = function14;
                    function9 = function15;
                    shape4 = shape3;
                } else {
                    if (function12 == null) {
                        contentPadding4 = m1989textFieldWithoutLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding4 = m1988textFieldWithLabelPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    isError4 = isError3;
                    contentPadding3 = contentPadding4;
                    colors4 = colors3;
                    $dirty1 = $dirty4 & (-7169);
                    function10 = function12;
                    function11 = function13;
                    function8 = function14;
                    function9 = function15;
                    shape4 = shape3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty = $dirty2;
                ComposerKt.traceEventStart(2088762355, $dirty, $dirty1, "androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox (TextFieldDefaults.kt:550)");
            } else {
                $dirty = $dirty2;
            }
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, value, function2, visualTransformation, function10, function11, function8, function9, singleLine, enabled, isError4, interactionSource, contentPadding3, shape4, colors4, null, $composer2, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty << 15) & 234881024) | (($dirty << 21) & 1879048192), (($dirty >> 18) & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (($dirty >> 12) & 112) | (($dirty1 >> 3) & 896) | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isError2 = isError4;
            contentPadding2 = contentPadding3;
            shape2 = shape4;
            colors2 = colors4;
        } else {
            $composer2 = $composer3;
            $dirty = $dirty3;
            $composer2.skipToGroupEnd();
            function8 = function5;
            function9 = function6;
            colors2 = colors;
            contentPadding2 = contentPadding;
            isError2 = z;
            function10 = function7;
            function11 = function4;
            shape2 = shape;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i10 = i2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldDefaults$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldDefaults.TextFieldDecorationBox$lambda$0(this.f$0, value, function2, enabled, singleLine, visualTransformation, interactionSource, isError2, function10, function11, function8, function9, shape2, colors2, contentPadding2, $changed, $changed1, i10, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit OutlinedTextFieldDecorationBox$lambda$0(boolean $enabled, boolean $isError, InteractionSource $interactionSource, TextFieldColors $colors, Shape $shape, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C644@31420L61:TextFieldDefaults.kt#jmzs0o");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1212923596, $changed, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:644)");
            }
            INSTANCE.m1990BorderBoxnbWgWpA($enabled, $isError, $interactionSource, $colors, $shape, 0.0f, 0.0f, $composer, 12582912, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public final void OutlinedTextFieldDecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function7, Composer $composer, final int $changed, final int $changed1, int i) {
        boolean z;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Composer $composer2;
        int $dirty;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final TextFieldColors colors2;
        final PaddingValues contentPadding2;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final boolean isError2;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final Shape shape2;
        boolean isError3;
        Function2<? super Composer, ? super Integer, Unit> function14;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Shape shape3;
        Composer $composer3;
        int $dirty2;
        int i2;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        PaddingValues contentPadding4;
        TextFieldColors colors4;
        Shape shape4;
        int $dirty1;
        boolean isError4;
        int i3 = i;
        Composer $composer4 = $composer.startRestartGroup(1154925202);
        ComposerKt.sourceInformation($composer4, "C(OutlinedTextFieldDecorationBox)N(value,innerTextField,enabled,singleLine,visualTransformation,interactionSource,isError,label,placeholder,leadingIcon,trailingIcon,shape,colors,contentPadding,border)647@31509L628:TextFieldDefaults.kt#jmzs0o");
        int $dirty3 = $changed;
        int $dirty4 = $changed1;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer4.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer4.changedInstance(function2) ? 32 : 16;
        }
        int i4 = 128;
        if (($changed & 384) == 0) {
            $dirty3 |= $composer4.changed(enabled) ? 256 : 128;
        }
        int i5 = 1024;
        if (($changed & 3072) == 0) {
            $dirty3 |= $composer4.changed(singleLine) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty3 |= $composer4.changed(visualTransformation) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer4.changed(interactionSource) ? 131072 : 65536;
        }
        int i6 = i3 & 64;
        if (i6 != 0) {
            $dirty3 |= 1572864;
            z = isError;
        } else if (($changed & 1572864) == 0) {
            z = isError;
            $dirty3 |= $composer4.changed(z) ? 1048576 : 524288;
        } else {
            z = isError;
        }
        int i7 = i3 & 128;
        if (i7 != 0) {
            $dirty3 |= 12582912;
            function8 = function3;
        } else if (($changed & 12582912) == 0) {
            function8 = function3;
            $dirty3 |= $composer4.changedInstance(function8) ? 8388608 : 4194304;
        } else {
            function8 = function3;
        }
        int i8 = i3 & 256;
        if (i8 != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer4.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i9 = i3 & 512;
        if (i9 != 0) {
            $dirty3 |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty3 |= $composer4.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i10 = i3 & 1024;
        if (i10 != 0) {
            $dirty4 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty4 |= $composer4.changedInstance(function6) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty4 |= ((i3 & 2048) == 0 && $composer4.changed(shape)) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            if ((i3 & 4096) == 0 && $composer4.changed(colors)) {
                i4 = 256;
            }
            $dirty4 |= i4;
        }
        if (($changed1 & 3072) == 0) {
            if ((i3 & 8192) == 0 && $composer4.changed(contentPadding)) {
                i5 = 2048;
            }
            $dirty4 |= i5;
        }
        int i11 = i3 & 16384;
        if (i11 != 0) {
            $dirty4 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty4 |= $composer4.changedInstance(function7) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty4 |= $composer4.changed(this) ? 131072 : 65536;
        }
        if ($composer4.shouldExecute((($dirty3 & 306783379) == 306783378 && (74899 & $dirty4) == 74898) ? false : true, $dirty3 & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "640@31212L22,641@31270L25,643@31406L85");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i3 & 2048) != 0) {
                    $dirty4 &= -113;
                }
                if ((i3 & 4096) != 0) {
                    $dirty4 &= -897;
                }
                if ((i3 & 8192) != 0) {
                    $dirty4 &= -7169;
                }
                function9 = function5;
                function10 = function6;
                shape4 = shape;
                colors4 = colors;
                contentPadding4 = contentPadding;
                function2RememberComposableLambda = function7;
                $composer3 = $composer4;
                $dirty2 = $dirty3;
                $dirty1 = $dirty4;
                isError4 = z;
                function12 = function8;
                function13 = function4;
            } else {
                if (i6 != 0) {
                    isError3 = false;
                } else {
                    isError3 = z;
                }
                if (i7 == 0) {
                    function14 = function8;
                } else {
                    function14 = null;
                }
                if (i8 == 0) {
                    function15 = function4;
                } else {
                    function15 = null;
                }
                if (i9 == 0) {
                    function16 = function5;
                } else {
                    function16 = null;
                }
                if (i10 == 0) {
                    function17 = function6;
                } else {
                    function17 = null;
                }
                if ((i3 & 2048) == 0) {
                    shape3 = shape;
                } else {
                    Shape shape5 = getOutlinedTextFieldShape($composer4, ($dirty4 >> 15) & 14);
                    $dirty4 &= -113;
                    shape3 = shape5;
                }
                if ((i3 & 4096) != 0) {
                    i2 = i11;
                    $dirty2 = $dirty3;
                    colors3 = m1996outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer4, 0, 0, ($dirty4 >> 12) & 112, 2097151);
                    $composer3 = $composer4;
                    $dirty4 &= -897;
                } else {
                    $composer3 = $composer4;
                    $dirty2 = $dirty3;
                    i2 = i11;
                    colors3 = colors;
                }
                i3 = i;
                if ((i3 & 8192) == 0) {
                    contentPadding3 = contentPadding;
                } else {
                    contentPadding3 = m1987outlinedTextFieldPaddinga9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    $dirty4 &= -7169;
                }
                if (i2 == 0) {
                    boolean isError5 = isError3;
                    function2RememberComposableLambda = function7;
                    contentPadding4 = contentPadding3;
                    colors4 = colors3;
                    shape4 = shape3;
                    $dirty1 = $dirty4;
                    isError4 = isError5;
                    function12 = function14;
                    function13 = function15;
                    function9 = function16;
                    function10 = function17;
                } else {
                    final boolean isError6 = isError3;
                    final TextFieldColors colors5 = colors3;
                    final Shape shape6 = shape3;
                    contentPadding4 = contentPadding3;
                    colors4 = colors3;
                    shape4 = shape6;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1212923596, true, new Function2() { // from class: androidx.compose.material.TextFieldDefaults$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return TextFieldDefaults.OutlinedTextFieldDecorationBox$lambda$0(enabled, isError6, interactionSource, colors5, shape6, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer3, 54);
                    $dirty1 = $dirty4;
                    isError4 = isError6;
                    function12 = function14;
                    function13 = function15;
                    function9 = function16;
                    function10 = function17;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                $dirty = $dirty2;
                ComposerKt.traceEventStart(1154925202, $dirty, $dirty1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:646)");
            } else {
                $dirty = $dirty2;
            }
            Composer $composer5 = $composer3;
            TextFieldImplKt.CommonDecorationBox(TextFieldType.Outlined, value, function2, visualTransformation, function12, function13, function9, function10, singleLine, enabled, isError4, interactionSource, contentPadding4, shape4, colors4, function2RememberComposableLambda, $composer5, (($dirty << 3) & 112) | 6 | (($dirty << 3) & 896) | (($dirty >> 3) & 7168) | (($dirty >> 9) & 57344) | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty1 << 21) & 29360128) | (($dirty << 15) & 234881024) | (($dirty << 21) & 1879048192), (($dirty >> 18) & 14) | (($dirty >> 12) & 112) | (($dirty1 >> 3) & 896) | (($dirty1 << 6) & 7168) | (($dirty1 << 6) & 57344) | (($dirty1 << 3) & 458752));
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isError2 = isError4;
            contentPadding2 = contentPadding4;
            shape2 = shape4;
            colors2 = colors4;
            function11 = function2RememberComposableLambda;
        } else {
            $composer2 = $composer4;
            $dirty = $dirty3;
            $composer2.skipToGroupEnd();
            function9 = function5;
            function10 = function6;
            colors2 = colors;
            contentPadding2 = contentPadding;
            function11 = function7;
            isError2 = z;
            function12 = function8;
            function13 = function4;
            shape2 = shape;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i12 = i3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldDefaults$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldDefaults.OutlinedTextFieldDecorationBox$lambda$1(this.f$0, value, function2, enabled, singleLine, visualTransformation, interactionSource, isError2, function12, function13, function9, function10, shape2, colors2, contentPadding2, function11, $changed, $changed1, i12, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 9121. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with `shape` parameter.")
    public final /* synthetic */ void TextFieldDecorationBox(java.lang.String r58, kotlin.jvm.functions.Function2 r59, boolean r60, boolean r61, androidx.compose.ui.text.input.VisualTransformation r62, androidx.compose.foundation.interaction.InteractionSource r63, boolean r64, kotlin.jvm.functions.Function2 r65, kotlin.jvm.functions.Function2 r66, kotlin.jvm.functions.Function2 r67, kotlin.jvm.functions.Function2 r68, androidx.compose.material.TextFieldColors r69, androidx.compose.foundation.layout.PaddingValues r70, androidx.compose.runtime.Composer r71, int r72, int r73, int r74) {
        /*
            Method dump skipped, instruction units count: 912
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox(java.lang.String, kotlin.jvm.functions.Function2, boolean, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.InteractionSource, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.material.TextFieldColors, androidx.compose.foundation.layout.PaddingValues, androidx.compose.runtime.Composer, int, int, int):void");
    }

    static final Unit OutlinedTextFieldDecorationBox$lambda$2(boolean $enabled, boolean $isError, InteractionSource $interactionSource, TextFieldColors $colors, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C730@34582L54:TextFieldDefaults.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1922186815, $changed, -1, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox.<anonymous> (TextFieldDefaults.kt:730)");
            }
            INSTANCE.m1990BorderBoxnbWgWpA($enabled, $isError, $interactionSource, $colors, null, 0.0f, 0.0f, $composer, 12582912, 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15, types: [androidx.compose.material.TextFieldDefaults] */
    /* JADX WARN: Type inference failed for: r0v18, types: [androidx.compose.material.TextFieldDefaults] */
    /* JADX WARN: Type inference failed for: r0v42 */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v46 */
    /* JADX WARN: Type inference failed for: r0v47 */
    /* JADX WARN: Type inference failed for: r59v0, types: [androidx.compose.material.TextFieldDefaults, java.lang.Object] */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with `shape` parameter.")
    public final /* synthetic */ void OutlinedTextFieldDecorationBox(final String str, final Function2 function2, final boolean z, final boolean z2, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean z3, Function2 function3, Function2 function4, Function2 function5, Function2 function6, TextFieldColors textFieldColors, PaddingValues paddingValues, Function2 function7, Composer composer, final int i, final int i2, final int i3) {
        final boolean z4;
        Function2 function8;
        Composer composer2;
        final Function2 function9;
        final Function2 function10;
        final TextFieldColors textFieldColors2;
        final PaddingValues paddingValues2;
        final boolean z5;
        final Function2 function11;
        final Function2 function12;
        final Function2 function2RememberComposableLambda;
        int i4;
        int i5;
        int i6;
        final TextFieldColors textFieldColors3;
        int i7;
        ?? r0;
        PaddingValues paddingValuesM1987outlinedTextFieldPaddinga9UjIt4$default;
        final boolean z6;
        final InteractionSource interactionSource2;
        Function2 function13;
        Function2 function14;
        Function2 function15;
        Function2 function16;
        ?? r1;
        ?? r2;
        int i8;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2141154809);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(OutlinedTextFieldDecorationBox)N(value,innerTextField,enabled,singleLine,visualTransformation,interactionSource,isError,label,placeholder,leadingIcon,trailingIcon,colors,contentPadding,border)744@35133L22,732@34656L613:TextFieldDefaults.kt#jmzs0o");
        int i9 = i;
        int i10 = i2;
        if ((i & 6) == 0) {
            i9 |= composerStartRestartGroup.changed(str) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i9 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i9 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i9 |= composerStartRestartGroup.changed(z2) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i9 |= composerStartRestartGroup.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i9 |= composerStartRestartGroup.changed(interactionSource) ? 131072 : 65536;
        }
        int i11 = i3 & 64;
        if (i11 != 0) {
            i9 |= 1572864;
            z4 = z3;
        } else if ((i & 1572864) == 0) {
            z4 = z3;
            i9 |= composerStartRestartGroup.changed(z4) ? 1048576 : 524288;
        } else {
            z4 = z3;
        }
        int i12 = i3 & 128;
        if (i12 != 0) {
            i9 |= 12582912;
            function8 = function3;
        } else if ((i & 12582912) == 0) {
            function8 = function3;
            i9 |= composerStartRestartGroup.changedInstance(function8) ? 8388608 : 4194304;
        } else {
            function8 = function3;
        }
        int i13 = i3 & 256;
        if (i13 != 0) {
            i9 |= 100663296;
        } else if ((i & 100663296) == 0) {
            i9 |= composerStartRestartGroup.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i14 = i3 & 512;
        if (i14 != 0) {
            i9 |= C.ENCODING_PCM_32BIT;
        } else if ((i & C.ENCODING_PCM_32BIT) == 0) {
            i9 |= composerStartRestartGroup.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i15 = i3 & 1024;
        if (i15 != 0) {
            i10 |= 6;
        } else if ((i2 & 6) == 0) {
            i10 |= composerStartRestartGroup.changedInstance(function6) ? 4 : 2;
        }
        if ((i2 & 48) == 0) {
            i10 |= ((i3 & 2048) == 0 && composerStartRestartGroup.changed(textFieldColors)) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i10 |= ((i3 & 4096) == 0 && composerStartRestartGroup.changed(paddingValues)) ? 256 : 128;
        }
        int i16 = i3 & 8192;
        if (i16 != 0) {
            i10 |= 3072;
        } else if ((i2 & 3072) == 0) {
            i10 |= composerStartRestartGroup.changedInstance(function7) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i10 |= composerStartRestartGroup.changed((Object) this) ? 16384 : 8192;
        }
        int i17 = i10;
        if (composerStartRestartGroup.shouldExecute(((306783379 & i9) == 306783378 && (i17 & 9363) == 9362) ? false : true, i9 & 1)) {
            composerStartRestartGroup.startDefaults();
            ComposerKt.sourceInformation(composerStartRestartGroup, "728@34444L25,730@34580L58");
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                z4 = i11 != 0 ? false : z4;
                Function2 function17 = i12 != 0 ? null : function8;
                Function2 function18 = i13 != 0 ? null : function4;
                Function2 function19 = i14 != 0 ? null : function5;
                Function2 function20 = i15 != 0 ? null : function6;
                if ((i3 & 2048) != 0) {
                    i6 = i16;
                    i5 = i9;
                    i4 = i3;
                    TextFieldColors textFieldColorsM1996outlinedTextFieldColorsdx8h9Zs = m1996outlinedTextFieldColorsdx8h9Zs(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composerStartRestartGroup, 0, 0, (i17 >> 9) & 112, 2097151);
                    composerStartRestartGroup = composerStartRestartGroup;
                    i7 = i17 & (-113);
                    textFieldColors3 = textFieldColorsM1996outlinedTextFieldColorsdx8h9Zs;
                } else {
                    i4 = i3;
                    i5 = i9;
                    i6 = i16;
                    textFieldColors3 = textFieldColors;
                    i7 = i17;
                }
                if ((i4 & 4096) != 0) {
                    r1 = this;
                    paddingValuesM1987outlinedTextFieldPaddinga9UjIt4$default = m1987outlinedTextFieldPaddinga9UjIt4$default(r1, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    i7 &= -897;
                } else {
                    r0 = this;
                    paddingValuesM1987outlinedTextFieldPaddinga9UjIt4$default = paddingValues;
                }
                if (i6 != 0) {
                    r0 = r1;
                    z6 = z;
                    interactionSource2 = interactionSource;
                    paddingValues2 = paddingValuesM1987outlinedTextFieldPaddinga9UjIt4$default;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1922186815, true, new Function2() { // from class: androidx.compose.material.TextFieldDefaults$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return TextFieldDefaults.OutlinedTextFieldDecorationBox$lambda$2(z6, z4, interactionSource2, textFieldColors3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, composerStartRestartGroup, 54);
                    textFieldColors2 = textFieldColors3;
                    i17 = i7;
                    function13 = function17;
                    function14 = function18;
                    function15 = function19;
                    function16 = function20;
                    r2 = r0;
                } else {
                    r0 = r1;
                    z6 = z;
                    interactionSource2 = interactionSource;
                    function2RememberComposableLambda = function7;
                    paddingValues2 = paddingValuesM1987outlinedTextFieldPaddinga9UjIt4$default;
                    textFieldColors2 = textFieldColors3;
                    i17 = i7;
                    function13 = function17;
                    function14 = function18;
                    function15 = function19;
                    function16 = function20;
                    r2 = r0;
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i3 & 2048) != 0) {
                    i17 &= -113;
                }
                if ((i3 & 4096) != 0) {
                    i17 &= -897;
                }
                r2 = this;
                z6 = z;
                interactionSource2 = interactionSource;
                function14 = function4;
                function15 = function5;
                function16 = function6;
                textFieldColors2 = textFieldColors;
                paddingValues2 = paddingValues;
                i5 = i9;
                function13 = function8;
                function2RememberComposableLambda = function7;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                i8 = i5;
                ComposerKt.traceEventStart(-2141154809, i8, i17, "androidx.compose.material.TextFieldDefaults.OutlinedTextFieldDecorationBox (TextFieldDefaults.kt:732)");
            } else {
                i8 = i5;
            }
            Composer composer3 = composerStartRestartGroup;
            boolean z7 = z4;
            OutlinedTextFieldDecorationBox(str, function2, z6, z2, visualTransformation, interactionSource2, z7, function13, function14, function15, function16, r2.getOutlinedTextFieldShape(composerStartRestartGroup, (i17 >> 12) & 14), textFieldColors2, paddingValues2, function2RememberComposableLambda, composer3, (i8 & 14) | (i8 & 112) | (i8 & 896) | (i8 & 7168) | (57344 & i8) | (458752 & i8) | (3670016 & i8) | (29360128 & i8) | (234881024 & i8) | (1879048192 & i8), (i17 & 14) | ((i17 << 3) & 896) | ((i17 << 3) & 7168) | ((i17 << 3) & 57344) | ((i17 << 3) & 458752), 0);
            composer2 = composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function12 = function16;
            function10 = function15;
            function9 = function14;
            function11 = function13;
            z5 = z7;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            function9 = function4;
            function10 = function5;
            textFieldColors2 = textFieldColors;
            paddingValues2 = paddingValues;
            z5 = z4;
            function11 = function8;
            function12 = function6;
            function2RememberComposableLambda = function7;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.TextFieldDefaults$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldDefaults.OutlinedTextFieldDecorationBox$lambda$3(this.f$0, str, function2, z, z2, visualTransformation, interactionSource, z5, function11, function9, function10, function12, textFieldColors2, paddingValues2, function2RememberComposableLambda, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
