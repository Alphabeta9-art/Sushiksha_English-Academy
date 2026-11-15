package androidx.compose.material3;

import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.text.input.OutputTransformation;
import androidx.compose.foundation.text.input.TextFieldBuffer;
import androidx.compose.foundation.text.input.TextFieldDecorator;
import androidx.compose.foundation.text.input.TextFieldLineLimits;
import androidx.compose.foundation.text.input.TextFieldState;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.internal.TextFieldType;
import androidx.compose.material3.tokens.FilledTextFieldTokens;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.input.TransformedText;
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
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0019\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0002\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2 \b\u0002\u0010!\u001a\u001a\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0018\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&2\u0015\b\u0002\u0010'\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010+\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010,\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010-\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\b\b\u0002\u0010.\u001a\u00020\u00182\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\u0013\b\u0002\u00103\u001a\r\u0012\u0004\u0012\u00020$0(¢\u0006\u0002\b%H\u0007¢\u0006\u0002\u00104JY\u00105\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u00106\u001a\u0002072\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\tH\u0007¢\u0006\u0004\b:\u0010;JU\u0010<\u001a\u000207*\u0002072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010/\u001a\u0004\u0018\u0001002\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\t¢\u0006\u0004\b>\u0010?J¦\u0002\u0010@\u001a\u00020$2\u0006\u0010A\u001a\u00020B2\u0011\u0010C\u001a\r\u0012\u0004\u0012\u00020$0(¢\u0006\u0002\b%2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010D\u001a\u00020\u00182\u0006\u0010E\u001a\u00020F2\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010.\u001a\u00020\u00182\u0015\b\u0002\u0010!\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010'\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010+\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010,\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\u0015\b\u0002\u0010-\u001a\u000f\u0012\u0004\u0012\u00020$\u0018\u00010(¢\u0006\u0002\b%2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\u0013\b\u0002\u00103\u001a\r\u0012\u0004\u0012\u00020$0(¢\u0006\u0002\b%H\u0007¢\u0006\u0002\u0010GJ5\u0010H\u001a\u0002022\b\b\u0002\u0010I\u001a\u00020\t2\b\b\u0002\u0010J\u001a\u00020\t2\b\b\u0002\u0010K\u001a\u00020\t2\b\b\u0002\u0010L\u001a\u00020\t¢\u0006\u0004\bM\u0010NJ5\u0010O\u001a\u0002022\b\b\u0002\u0010I\u001a\u00020\t2\b\b\u0002\u0010K\u001a\u00020\t2\b\b\u0002\u0010J\u001a\u00020\t2\b\b\u0002\u0010L\u001a\u00020\t¢\u0006\u0004\bP\u0010NJ7\u0010Q\u001a\u0002022\b\b\u0002\u0010I\u001a\u00020\t2\b\b\u0002\u0010K\u001a\u00020\t2\b\b\u0002\u0010J\u001a\u00020\t2\b\b\u0002\u0010L\u001a\u00020\tH\u0000¢\u0006\u0004\bR\u0010NJ\r\u0010/\u001a\u000200H\u0007¢\u0006\u0002\u0010SJÂ\u0003\u0010/\u001a\u0002002\b\b\u0002\u0010T\u001a\u00020U2\b\b\u0002\u0010V\u001a\u00020U2\b\b\u0002\u0010W\u001a\u00020U2\b\b\u0002\u0010X\u001a\u00020U2\b\b\u0002\u0010Y\u001a\u00020U2\b\b\u0002\u0010Z\u001a\u00020U2\b\b\u0002\u0010[\u001a\u00020U2\b\b\u0002\u0010\\\u001a\u00020U2\b\b\u0002\u0010]\u001a\u00020U2\b\b\u0002\u0010^\u001a\u00020U2\n\b\u0002\u0010_\u001a\u0004\u0018\u00010`2\b\b\u0002\u0010a\u001a\u00020U2\b\b\u0002\u0010b\u001a\u00020U2\b\b\u0002\u0010c\u001a\u00020U2\b\b\u0002\u0010d\u001a\u00020U2\b\b\u0002\u0010e\u001a\u00020U2\b\b\u0002\u0010f\u001a\u00020U2\b\b\u0002\u0010g\u001a\u00020U2\b\b\u0002\u0010h\u001a\u00020U2\b\b\u0002\u0010i\u001a\u00020U2\b\b\u0002\u0010j\u001a\u00020U2\b\b\u0002\u0010k\u001a\u00020U2\b\b\u0002\u0010l\u001a\u00020U2\b\b\u0002\u0010m\u001a\u00020U2\b\b\u0002\u0010n\u001a\u00020U2\b\b\u0002\u0010o\u001a\u00020U2\b\b\u0002\u0010p\u001a\u00020U2\b\b\u0002\u0010q\u001a\u00020U2\b\b\u0002\u0010r\u001a\u00020U2\b\b\u0002\u0010s\u001a\u00020U2\b\b\u0002\u0010t\u001a\u00020U2\b\b\u0002\u0010u\u001a\u00020U2\b\b\u0002\u0010v\u001a\u00020U2\b\b\u0002\u0010w\u001a\u00020U2\b\b\u0002\u0010x\u001a\u00020U2\b\b\u0002\u0010y\u001a\u00020U2\b\b\u0002\u0010z\u001a\u00020U2\b\b\u0002\u0010{\u001a\u00020U2\b\b\u0002\u0010|\u001a\u00020U2\b\b\u0002\u0010}\u001a\u00020U2\b\b\u0002\u0010~\u001a\u00020U2\b\b\u0002\u0010\u007f\u001a\u00020U2\t\b\u0002\u0010\u0080\u0001\u001a\u00020UH\u0007¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001d\u0010\u0083\u0001\u001a\u000200*\u00030\u0084\u00012\u0007\u0010\u0085\u0001\u001a\u00020`H\u0000¢\u0006\u0003\b\u0086\u0001JI\u0010<\u001a\u000207*\u0002072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010/\u001a\u0002002\b\b\u0002\u00108\u001a\u00020\t2\b\b\u0002\u00109\u001a\u00020\tH\u0007¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J9\u0010\u0089\u0001\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010/\u001a\u0002002\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0003\u0010\u008a\u0001J9\u0010\u0097\u0001\u001a\u0002022\b\b\u0002\u0010I\u001a\u00020\t2\b\b\u0002\u0010J\u001a\u00020\t2\b\b\u0002\u0010K\u001a\u00020\t2\b\b\u0002\u0010L\u001a\u00020\tH\u0007¢\u0006\u0005\b\u0098\u0001\u0010NJ9\u0010\u0099\u0001\u001a\u0002022\b\b\u0002\u0010I\u001a\u00020\t2\b\b\u0002\u0010K\u001a\u00020\t2\b\b\u0002\u0010J\u001a\u00020\t2\b\b\u0002\u0010L\u001a\u00020\tH\u0007¢\u0006\u0005\b\u009a\u0001\u0010NJ9\u0010\u009b\u0001\u001a\u0002022\b\b\u0002\u0010I\u001a\u00020\t2\b\b\u0002\u0010K\u001a\u00020\t2\b\b\u0002\u0010J\u001a\u00020\t2\b\b\u0002\u0010L\u001a\u00020\tH\u0007¢\u0006\u0005\b\u009c\u0001\u0010NR\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\r\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000bR\u0013\u0010\u000f\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0013\u0010\u0011\u001a\u00020\t¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0012\u0010\u000bR\u001d\u0010\u008b\u0001\u001a\u00020\u00058GX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u008c\u0001\u0010\u0003\u001a\u0005\b\u008d\u0001\u0010\u0007R\u001d\u0010\u008e\u0001\u001a\u00020\u00058GX\u0087\u0004¢\u0006\u000e\u0012\u0005\b\u008f\u0001\u0010\u0003\u001a\u0005\b\u0090\u0001\u0010\u0007R!\u0010\u0091\u0001\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0012\n\u0002\u0010\f\u0012\u0005\b\u0092\u0001\u0010\u0003\u001a\u0005\b\u0093\u0001\u0010\u000bR!\u0010\u0094\u0001\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0012\n\u0002\u0010\f\u0012\u0005\b\u0095\u0001\u0010\u0003\u001a\u0005\b\u0096\u0001\u0010\u000b¨\u0006\u009d\u0001"}, d2 = {"Landroidx/compose/material3/TextFieldDefaults;", "", "<init>", "()V", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "MinHeight", "Landroidx/compose/ui/unit/Dp;", "getMinHeight-D9Ej5fM", "()F", "F", "MinWidth", "getMinWidth-D9Ej5fM", "UnfocusedIndicatorThickness", "getUnfocusedIndicatorThickness-D9Ej5fM", "FocusedIndicatorThickness", "getFocusedIndicatorThickness-D9Ej5fM", "decorator", "Landroidx/compose/foundation/text/input/TextFieldDecorator;", "state", "Landroidx/compose/foundation/text/input/TextFieldState;", "enabled", "", "lineLimits", "Landroidx/compose/foundation/text/input/TextFieldLineLimits;", "outputTransformation", "Landroidx/compose/foundation/text/input/OutputTransformation;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", MetadataField.LABEL, "Lkotlin/Function1;", "Landroidx/compose/material3/TextFieldLabelScope;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "placeholder", "Lkotlin/Function0;", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "colors", "Landroidx/compose/material3/TextFieldColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", TtmlNode.RUBY_CONTAINER, "(Landroidx/compose/foundation/text/input/TextFieldState;ZLandroidx/compose/foundation/text/input/TextFieldLineLimits;Landroidx/compose/foundation/text/input/OutputTransformation;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldLabelPosition;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)Landroidx/compose/foundation/text/input/TextFieldDecorator;", TextFieldImplKt.ContainerId, "modifier", "Landroidx/compose/ui/Modifier;", "focusedIndicatorLineThickness", "unfocusedIndicatorLineThickness", "Container-4EFweAY", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FFLandroidx/compose/runtime/Composer;II)V", "indicatorLine", "textFieldShape", "indicatorLine-AWlRVLg", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;FF)Landroidx/compose/ui/Modifier;", "DecorationBox", "value", "", "innerTextField", "singleLine", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/InteractionSource;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "contentPaddingWithLabel", TtmlNode.START, TtmlNode.END, "top", "bottom", "contentPaddingWithLabel-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "contentPaddingWithoutLabel", "contentPaddingWithoutLabel-a9UjIt4", "supportingTextPadding", "supportingTextPadding-a9UjIt4$material3", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/TextFieldColors;", "focusedTextColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "selectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "colors-0hiis_0", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLandroidx/compose/runtime/Composer;IIIIIII)Landroidx/compose/material3/TextFieldColors;", "defaultTextFieldColors", "Landroidx/compose/material3/ColorScheme;", "localTextSelectionColors", "defaultTextFieldColors$material3", "indicatorLine-gv0btCI", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;FF)Landroidx/compose/ui/Modifier;", "ContainerBox", "(ZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "outlinedShape", "getOutlinedShape$annotations", "getOutlinedShape", "filledShape", "getFilledShape$annotations", "getFilledShape", "UnfocusedBorderThickness", "getUnfocusedBorderThickness-D9Ej5fM$annotations", "getUnfocusedBorderThickness-D9Ej5fM", "FocusedBorderThickness", "getFocusedBorderThickness-D9Ej5fM$annotations", "getFocusedBorderThickness-D9Ej5fM", "textFieldWithLabelPadding", "textFieldWithLabelPadding-a9UjIt4", "textFieldWithoutLabelPadding", "textFieldWithoutLabelPadding-a9UjIt4", "outlinedTextFieldPadding", "outlinedTextFieldPadding-a9UjIt4", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextFieldDefaults {
    public static final int $stable = 0;
    private static final float FocusedBorderThickness;
    private static final float UnfocusedBorderThickness;
    public static final TextFieldDefaults INSTANCE = new TextFieldDefaults();
    private static final float MinHeight = Dp.m7905constructorimpl(56);
    private static final float MinWidth = Dp.m7905constructorimpl(280);
    private static final float UnfocusedIndicatorThickness = Dp.m7905constructorimpl(1);
    private static final float FocusedIndicatorThickness = Dp.m7905constructorimpl(2);

    static final Unit ContainerBox$lambda$7(TextFieldDefaults textFieldDefaults, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape, int i, int i2, Composer composer, int i3) {
        textFieldDefaults.ContainerBox(z, z2, interactionSource, textFieldColors, shape, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Container_4EFweAY$lambda$0(TextFieldDefaults textFieldDefaults, boolean z, boolean z2, InteractionSource interactionSource, Modifier modifier, TextFieldColors textFieldColors, Shape shape, float f, float f2, int i, int i2, Composer composer, int i3) {
        textFieldDefaults.m3051Container4EFweAY(z, z2, interactionSource, modifier, textFieldColors, shape, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DecorationBox$lambda$3(TextFieldDefaults textFieldDefaults, String str, Function2 function2, boolean z, boolean z2, VisualTransformation visualTransformation, InteractionSource interactionSource, boolean z3, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, Function2 function9, Shape shape, TextFieldColors textFieldColors, PaddingValues paddingValues, Function2 function10, int i, int i2, int i3, Composer composer, int i4) {
        textFieldDefaults.DecorationBox(str, function2, z, z2, visualTransformation, interactionSource, z3, function3, function4, function5, function6, function7, function8, function9, shape, textFieldColors, paddingValues, function10, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.shape`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.shape", imports = {}))
    public static /* synthetic */ void getFilledShape$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Split into `TextFieldDefaults.FocusedIndicatorThickness` and `OutlinedTextFieldDefaults.FocusedBorderThickness`. Please update as appropriate.", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.FocusedIndicatorThickness", imports = {}))
    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m3043getFocusedBorderThicknessD9Ej5fM$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.shape`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.shape", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    public static /* synthetic */ void getOutlinedShape$annotations() {
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Split into `TextFieldDefaults.UnfocusedIndicatorThickness` and `OutlinedTextFieldDefaults.UnfocusedBorderThickness`. Please update as appropriate.", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.UnfocusedIndicatorThickness", imports = {}))
    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM$annotations, reason: not valid java name */
    public static /* synthetic */ void m3044getUnfocusedBorderThicknessD9Ej5fM$annotations() {
    }

    private TextFieldDefaults() {
    }

    public final Shape getShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -1941327459, "C(<get-shape>)68@3251L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1941327459, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-shape> (TextFieldDefaults.kt:68)");
        }
        Shape value = ShapesKt.getValue(FilledTextFieldTokens.INSTANCE.getContainerShape(), $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return value;
    }

    static {
        TextFieldDefaults textFieldDefaults = INSTANCE;
        UnfocusedBorderThickness = UnfocusedIndicatorThickness;
        TextFieldDefaults textFieldDefaults2 = INSTANCE;
        FocusedBorderThickness = FocusedIndicatorThickness;
    }

    /* JADX INFO: renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m3057getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* JADX INFO: renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m3058getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* JADX INFO: renamed from: getUnfocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m3060getUnfocusedIndicatorThicknessD9Ej5fM() {
        return UnfocusedIndicatorThickness;
    }

    /* JADX INFO: renamed from: getFocusedIndicatorThickness-D9Ej5fM, reason: not valid java name */
    public final float m3056getFocusedIndicatorThicknessD9Ej5fM() {
        return FocusedIndicatorThickness;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$decorator$2, reason: invalid class name */
    /* JADX INFO: compiled from: TextFieldDefaults.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004H\n"}, d2 = {"<anonymous>", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass2 implements TextFieldDecorator {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ Function2<Composer, Integer, Unit> $container;
        final /* synthetic */ PaddingValues $contentPadding;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ InteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ Function3<TextFieldLabelScope, Composer, Integer, Unit> $label;
        final /* synthetic */ TextFieldLabelPosition $labelPosition;
        final /* synthetic */ Function2<Composer, Integer, Unit> $leadingIcon;
        final /* synthetic */ TextFieldLineLimits $lineLimits;
        final /* synthetic */ OutputTransformation $outputTransformation;
        final /* synthetic */ Function2<Composer, Integer, Unit> $placeholder;
        final /* synthetic */ Function2<Composer, Integer, Unit> $prefix;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ Function2<Composer, Integer, Unit> $suffix;
        final /* synthetic */ Function2<Composer, Integer, Unit> $supportingText;
        final /* synthetic */ Function2<Composer, Integer, Unit> $trailingIcon;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(OutputTransformation outputTransformation, TextFieldState textFieldState, TextFieldLineLimits textFieldLineLimits, TextFieldLabelPosition textFieldLabelPosition, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean z, boolean z2, InteractionSource interactionSource, PaddingValues paddingValues, TextFieldColors textFieldColors, Function2<? super Composer, ? super Integer, Unit> function9) {
            this.$outputTransformation = outputTransformation;
            this.$state = textFieldState;
            this.$lineLimits = textFieldLineLimits;
            this.$labelPosition = textFieldLabelPosition;
            this.$label = function3;
            this.$placeholder = function2;
            this.$leadingIcon = function4;
            this.$trailingIcon = function5;
            this.$prefix = function6;
            this.$suffix = function7;
            this.$supportingText = function8;
            this.$enabled = z;
            this.$isError = z2;
            this.$interactionSource = interactionSource;
            this.$contentPadding = paddingValues;
            this.$colors = textFieldColors;
            this.$container = function9;
        }

        static final Unit Decoration$lambda$2(AnonymousClass2 anonymousClass2, Function2 function2, int i, Composer composer, int i2) {
            anonymousClass2.Decoration(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            return Unit.INSTANCE;
        }

        @Override // androidx.compose.foundation.text.input.TextFieldDecorator
        public final void Decoration(final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
            Composer $composer2;
            TextFieldBuffer textFieldBuffer;
            CharSequence visualText;
            Composer $composer3 = $composer.startRestartGroup(-94654579);
            ComposerKt.sourceInformation($composer3, "C(Decoration)N(innerTextField)191@10152L718:TextFieldDefaults.kt#uh7d8r");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changed(this) ? 32 : 16;
            }
            if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-94654579, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.decorator.<no name provided>.Decoration (TextFieldDefaults.kt:179)");
                }
                if (this.$outputTransformation == null) {
                    visualText = this.$state.getText();
                } else {
                    TextFieldState this_$iv = this.$state;
                    TextFieldBuffer mutableValue$iv = this_$iv.startEdit();
                    try {
                        this_$iv.commitEdit(mutableValue$iv);
                        this_$iv.finishEditing();
                        OutputTransformation $this$Decoration_u24lambda_u241 = this.$outputTransformation;
                        if (mutableValue$iv == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("buffer");
                            textFieldBuffer = null;
                        } else {
                            textFieldBuffer = mutableValue$iv;
                        }
                        $this$Decoration_u24lambda_u241.transformOutput(textFieldBuffer);
                        visualText = mutableValue$iv.asCharSequence();
                    } catch (Throwable th) {
                        this_$iv.finishEditing();
                        throw th;
                    }
                }
                $composer2 = $composer3;
                TextFieldImplKt.CommonDecorationBox(TextFieldType.Filled, visualText, function2, this.$labelPosition, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, Intrinsics.areEqual(this.$lineLimits, TextFieldLineLimits.SingleLine.INSTANCE), this.$enabled, this.$isError, this.$interactionSource, this.$contentPadding, this.$colors, this.$container, $composer2, (($dirty << 6) & 896) | 6, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldDefaults$decorator$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return TextFieldDefaults.AnonymousClass2.Decoration$lambda$2(this.f$0, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
    }

    public final TextFieldDecorator decorator(TextFieldState state, boolean enabled, TextFieldLineLimits lineLimits, OutputTransformation outputTransformation, InteractionSource interactionSource, TextFieldLabelPosition labelPosition, Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, boolean isError, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function9, Composer $composer, int $changed, int $changed1, int i) {
        TextFieldDefaults textFieldDefaults;
        final TextFieldColors colors2;
        PaddingValues contentPadding2;
        final boolean z;
        final InteractionSource interactionSource2;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        ComposerKt.sourceInformationMarkerStart($composer, 320881373, "C(decorator)N(state,enabled,lineLimits,outputTransformation,interactionSource,labelPosition,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,isError,colors,contentPadding,container)160@8861L8,167@9160L391:TextFieldDefaults.kt#uh7d8r");
        TextFieldLabelPosition labelPosition2 = (i & 32) != 0 ? new TextFieldLabelPosition.Attached(false, null, null, 7, null) : labelPosition;
        Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function10 = (i & 64) != 0 ? null : function3;
        Function2<? super Composer, ? super Integer, Unit> function11 = (i & 128) != 0 ? null : function2;
        Function2<? super Composer, ? super Integer, Unit> function12 = (i & 256) != 0 ? null : function4;
        Function2<? super Composer, ? super Integer, Unit> function13 = (i & 512) != 0 ? null : function5;
        Function2<? super Composer, ? super Integer, Unit> function14 = (i & 1024) != 0 ? null : function6;
        Function2<? super Composer, ? super Integer, Unit> function15 = (i & 2048) != 0 ? null : function7;
        Function2<? super Composer, ? super Integer, Unit> function16 = (i & 4096) != 0 ? null : function8;
        final boolean isError2 = (i & 8192) != 0 ? false : isError;
        if ((i & 16384) != 0) {
            textFieldDefaults = this;
            colors2 = textFieldDefaults.colors($composer, ($changed1 >> 21) & 14);
        } else {
            textFieldDefaults = this;
            colors2 = colors;
        }
        if ((32768 & i) != 0) {
            contentPadding2 = (function10 == null || (labelPosition2 instanceof TextFieldLabelPosition.Above)) ? m3042contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null) : m3041contentPaddingWithLabela9UjIt4$default(textFieldDefaults, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
        } else {
            contentPadding2 = contentPadding;
        }
        if ((i & 65536) != 0) {
            z = enabled;
            interactionSource2 = interactionSource;
            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(245572296, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.decorator.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer2, int $changed2) {
                    ComposerKt.sourceInformation($composer2, "C173@9367L5,168@9174L367:TextFieldDefaults.kt#uh7d8r");
                    if (!$composer2.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(245572296, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.decorator.<anonymous> (TextFieldDefaults.kt:168)");
                    }
                    TextFieldDefaults.INSTANCE.m3051Container4EFweAY(z, isError2, interactionSource2, null, colors2, TextFieldDefaults.INSTANCE.getShape($composer2, 6), TextFieldDefaults.INSTANCE.m3056getFocusedIndicatorThicknessD9Ej5fM(), TextFieldDefaults.INSTANCE.m3060getUnfocusedIndicatorThicknessD9Ej5fM(), $composer2, 114819072, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer, 54);
        } else {
            z = enabled;
            interactionSource2 = interactionSource;
            function2RememberComposableLambda = function9;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(320881373, $changed, $changed1, "androidx.compose.material3.TextFieldDefaults.decorator (TextFieldDefaults.kt:178)");
        }
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(outputTransformation, state, lineLimits, labelPosition2, function10, function11, function12, function13, function14, function15, function16, z, isError2, interactionSource2, contentPadding2, colors2, function2RememberComposableLambda);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return anonymousClass2;
    }

    /* JADX INFO: renamed from: Container-4EFweAY, reason: not valid java name */
    public final void m3051Container4EFweAY(final boolean enabled, final boolean isError, final InteractionSource interactionSource, Modifier modifier, TextFieldColors colors, Shape shape, float focusedIndicatorLineThickness, float unfocusedIndicatorLineThickness, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        float f;
        float f2;
        Composer $composer2;
        final Modifier modifier3;
        final Shape shape3;
        final float unfocusedIndicatorLineThickness2;
        final float unfocusedIndicatorLineThickness3;
        final TextFieldColors colors2;
        TextFieldColors colors3;
        Shape shape4;
        float focusedIndicatorLineThickness2;
        TextFieldColors colors4;
        Modifier modifier4;
        float focusedIndicatorLineThickness3;
        int $dirty;
        float unfocusedIndicatorLineThickness4;
        Composer $composer3 = $composer.startRestartGroup(-818661242);
        ComposerKt.sourceInformation($composer3, "C(Container)N(enabled,isError,interactionSource,modifier,colors,shape,focusedIndicatorLineThickness:c#ui.unit.Dp,unfocusedIndicatorLineThickness:c#ui.unit.Dp)242@12387L25,247@12703L7,245@12536L189,249@12734L540:TextFieldDefaults.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(isError) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changed(interactionSource) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty2 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= ((i & 16) == 0 && $composer3.changed(colors)) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i3 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty2 |= i3;
            } else {
                shape2 = shape;
            }
            $dirty2 |= i3;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                f = focusedIndicatorLineThickness;
                int i4 = $composer3.changed(f) ? 1048576 : 524288;
                $dirty2 |= i4;
            } else {
                f = focusedIndicatorLineThickness;
            }
            $dirty2 |= i4;
        } else {
            f = focusedIndicatorLineThickness;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                f2 = unfocusedIndicatorLineThickness;
                int i5 = $composer3.changed(f2) ? 8388608 : 4194304;
                $dirty2 |= i5;
            } else {
                f2 = unfocusedIndicatorLineThickness;
            }
            $dirty2 |= i5;
        } else {
            f2 = unfocusedIndicatorLineThickness;
        }
        if ((i & 256) != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ($composer3.shouldExecute((38347923 & $dirty2) != 38347922, $dirty2 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "237@12135L8,238@12186L5");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty2 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                modifier4 = modifier2;
                shape4 = shape2;
                focusedIndicatorLineThickness3 = f;
                unfocusedIndicatorLineThickness4 = f2;
                $dirty = $dirty2;
                colors4 = colors;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 16) == 0) {
                    colors3 = colors;
                } else {
                    colors3 = colors($composer3, ($dirty2 >> 24) & 14);
                    $dirty2 &= -57345;
                }
                if ((i & 32) == 0) {
                    shape4 = shape2;
                } else {
                    shape4 = INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -458753;
                }
                if ((i & 64) == 0) {
                    focusedIndicatorLineThickness2 = f;
                } else {
                    focusedIndicatorLineThickness2 = FocusedIndicatorThickness;
                    $dirty2 &= -3670017;
                }
                if ((i & 128) == 0) {
                    int i6 = $dirty2;
                    colors4 = colors3;
                    modifier4 = modifier2;
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
                    $dirty = i6;
                    unfocusedIndicatorLineThickness4 = f2;
                } else {
                    int $dirty3 = $dirty2 & (-29360129);
                    colors4 = colors3;
                    modifier4 = modifier2;
                    focusedIndicatorLineThickness3 = focusedIndicatorLineThickness2;
                    $dirty = $dirty3;
                    unfocusedIndicatorLineThickness4 = UnfocusedIndicatorThickness;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-818661242, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.Container (TextFieldDefaults.kt:241)");
            }
            boolean focused = FocusInteractionKt.collectIsFocusedAsState(interactionSource, $composer3, ($dirty >> 6) & 14).getValue().booleanValue();
            final State<Color> stateM135animateColorAsStateeuL9pac = SingleValueAnimationKt.m135animateColorAsStateeuL9pac(colors4.m2987containerColorXeAY9LY$material3(enabled, isError, focused), MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, $composer3, 6), null, null, $composer3, 0, 12);
            $composer2 = $composer3;
            Modifier modifier5 = TextFieldImplKt.textFieldBackground(modifier4, new TextFieldDefaults$sam$androidx_compose_ui_graphics_ColorProducer$0(new PropertyReference0Impl(stateM135animateColorAsStateeuL9pac) { // from class: androidx.compose.material3.TextFieldDefaults$Container$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            }), shape4);
            Modifier modifier6 = modifier4;
            TextFieldColors colors5 = colors4;
            Shape shape5 = shape4;
            float focusedIndicatorLineThickness4 = focusedIndicatorLineThickness3;
            float focusedIndicatorLineThickness5 = unfocusedIndicatorLineThickness4;
            BoxKt.Box(m3061indicatorLineAWlRVLg(modifier5, enabled, isError, interactionSource, colors5, shape5, focusedIndicatorLineThickness4, focusedIndicatorLineThickness5), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            unfocusedIndicatorLineThickness3 = focusedIndicatorLineThickness5;
            unfocusedIndicatorLineThickness2 = focusedIndicatorLineThickness4;
            shape3 = shape5;
            colors2 = colors5;
            modifier3 = modifier6;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            unfocusedIndicatorLineThickness2 = f;
            unfocusedIndicatorLineThickness3 = f2;
            colors2 = colors;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldDefaults$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldDefaults.Container_4EFweAY$lambda$0(this.f$0, enabled, isError, interactionSource, modifier3, colors2, shape3, unfocusedIndicatorLineThickness2, unfocusedIndicatorLineThickness3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: indicatorLine-AWlRVLg$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3045indicatorLineAWlRVLg$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, Shape shape, float f, float f2, int i, Object obj) {
        if ((i & 8) != 0) {
            textFieldColors = null;
        }
        if ((i & 16) != 0) {
            shape = null;
        }
        if ((i & 32) != 0) {
            f = FocusedIndicatorThickness;
        }
        if ((i & 64) != 0) {
            f2 = UnfocusedIndicatorThickness;
        }
        return textFieldDefaults.m3061indicatorLineAWlRVLg(modifier, z, z2, interactionSource, textFieldColors, shape, f, f2);
    }

    /* JADX INFO: renamed from: indicatorLine-AWlRVLg, reason: not valid java name */
    public final Modifier m3061indicatorLineAWlRVLg(Modifier $this$indicatorLine_u2dAWlRVLg, boolean enabled, boolean isError, InteractionSource interactionSource, TextFieldColors colors, Shape textFieldShape, float focusedIndicatorLineThickness, float unfocusedIndicatorLineThickness) {
        return $this$indicatorLine_u2dAWlRVLg.then(new IndicatorLineElement(enabled, isError, interactionSource, colors, textFieldShape, focusedIndicatorLineThickness, unfocusedIndicatorLineThickness, null));
    }

    /* JADX WARN: Code duplicated, block: B:288:0x047c  */
    /* JADX WARN: Code duplicated, block: B:289:0x048c  */
    /* JADX WARN: Code duplicated, block: B:292:0x052e  */
    public final void DecorationBox(final String value, final Function2<? super Composer, ? super Integer, Unit> function2, final boolean enabled, final boolean singleLine, final VisualTransformation visualTransformation, final InteractionSource interactionSource, boolean isError, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Function2<? super Composer, ? super Integer, Unit> function7, Function2<? super Composer, ? super Integer, Unit> function8, Function2<? super Composer, ? super Integer, Unit> function9, Shape shape, TextFieldColors colors, PaddingValues contentPadding, Function2<? super Composer, ? super Integer, Unit> function10, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function11;
        boolean z;
        InteractionSource interactionSource2;
        boolean isError2;
        Function2<? super Composer, ? super Integer, Unit> function12;
        int i2;
        int i3;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        final Function2<? super Composer, ? super Integer, Unit> function15;
        final Function2<? super Composer, ? super Integer, Unit> function16;
        final Shape shape2;
        final TextFieldColors colors2;
        final PaddingValues contentPadding2;
        final Function2<? super Composer, ? super Integer, Unit> function17;
        final Function2<? super Composer, ? super Integer, Unit> function18;
        final boolean isError3;
        final Function2<? super Composer, ? super Integer, Unit> function19;
        final Function2<? super Composer, ? super Integer, Unit> function20;
        Function2<? super Composer, ? super Integer, Unit> function21;
        Function2<? super Composer, ? super Integer, Unit> function22;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Shape shape3;
        int $dirty1;
        TextFieldColors colors3;
        PaddingValues contentPadding3;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2<? super Composer, ? super Integer, Unit> function2RememberComposableLambda;
        Shape shape4;
        Function2<? super Composer, ? super Integer, Unit> function28;
        TextFieldColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function29;
        Function2<? super Composer, ? super Integer, Unit> function30;
        Function2<? super Composer, ? super Integer, Unit> function31;
        Function2<? super Composer, ? super Integer, Unit> function32;
        int $dirty2;
        PaddingValues contentPadding4;
        boolean isError4;
        Shape shape5;
        ComposableLambda composableLambda;
        Composer $composer3 = $composer.startRestartGroup(1806980801);
        ComposerKt.sourceInformation($composer3, "C(DecorationBox)N(value,innerTextField,enabled,singleLine,visualTransformation,interactionSource,isError,label,placeholder,leadingIcon,trailingIcon,prefix,suffix,supportingText,shape,colors,contentPadding,container)403@21131L129,409@21314L749:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        int $dirty3 = $changed1;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            function11 = function2;
        } else if (($changed & 48) == 0) {
            function11 = function2;
            $dirty |= $composer3.changedInstance(function11) ? 32 : 16;
        } else {
            function11 = function2;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
            z = enabled;
        } else if (($changed & 384) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = enabled;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(singleLine) ? 2048 : 1024;
        }
        int i4 = 8192;
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(visualTransformation) ? 16384 : 8192;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            interactionSource2 = interactionSource;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            interactionSource2 = interactionSource;
            $dirty |= $composer3.changed(interactionSource2) ? 131072 : 65536;
        } else {
            interactionSource2 = interactionSource;
        }
        int i5 = i & 64;
        if (i5 != 0) {
            $dirty |= 1572864;
            isError2 = isError;
        } else if (($changed & 1572864) == 0) {
            isError2 = isError;
            $dirty |= $composer3.changed(isError2) ? 1048576 : 524288;
        } else {
            isError2 = isError;
        }
        int i6 = i & 128;
        if (i6 != 0) {
            $dirty |= 12582912;
            function12 = function3;
        } else if (($changed & 12582912) == 0) {
            function12 = function3;
            $dirty |= $composer3.changedInstance(function12) ? 8388608 : 4194304;
        } else {
            function12 = function3;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i8 = i & 512;
        if (i8 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if (($changed & C.ENCODING_PCM_32BIT) == 0) {
            $dirty |= $composer3.changedInstance(function5) ? 536870912 : 268435456;
        }
        int i9 = i & 1024;
        if (i9 != 0) {
            $dirty3 |= 6;
        } else if (($changed1 & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function6) ? 4 : 2;
        }
        int i10 = i & 2048;
        if (i10 != 0) {
            $dirty3 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function7) ? 32 : 16;
        }
        int i11 = i & 4096;
        if (i11 != 0) {
            $dirty3 |= 384;
            i2 = i11;
        } else {
            i2 = i11;
            if (($changed1 & 384) == 0) {
                $dirty3 |= $composer3.changedInstance(function8) ? 256 : 128;
            }
        }
        int i12 = i & 8192;
        if (i12 != 0) {
            $dirty3 |= 3072;
            i3 = i12;
        } else {
            i3 = i12;
            if (($changed1 & 3072) == 0) {
                $dirty3 |= $composer3.changedInstance(function9) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer3.changed(shape)) {
                i4 = 16384;
            }
            $dirty3 |= i4;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= ((i & 32768) == 0 && $composer3.changed(colors)) ? 131072 : 65536;
        }
        if (($changed1 & 1572864) == 0) {
            $dirty3 |= ((i & 65536) == 0 && $composer3.changed(contentPadding)) ? 1048576 : 524288;
        }
        int i13 = i & 131072;
        if (i13 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function10) ? 8388608 : 4194304;
        }
        if ((i & 262144) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed1 & 100663296) == 0) {
            $dirty3 |= $composer3.changed(this) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if ($composer3.shouldExecute((($dirty & 306783379) == 306783378 && ($dirty4 & 38347923) == 38347922) ? false : true, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "381@20365L5,382@20406L8,389@20656L428");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 16384) != 0 ? $dirty4 & (-57345) : $dirty4;
                if ((32768 & i) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 65536) != 0) {
                    $dirty5 &= -3670017;
                }
                function27 = function4;
                function22 = function5;
                function30 = function6;
                function31 = function7;
                function32 = function8;
                function29 = function9;
                shape4 = shape;
                colors4 = colors;
                contentPadding4 = contentPadding;
                function2RememberComposableLambda = function10;
                isError4 = isError2;
                $dirty2 = $dirty5;
                function28 = function12;
            } else {
                if (i5 != 0) {
                    isError2 = false;
                }
                if (i6 != 0) {
                    function12 = null;
                }
                if (i7 == 0) {
                    function21 = function4;
                } else {
                    function21 = null;
                }
                if (i8 == 0) {
                    function22 = function5;
                } else {
                    function22 = null;
                }
                if (i9 == 0) {
                    function23 = function6;
                } else {
                    function23 = null;
                }
                if (i10 == 0) {
                    function24 = function7;
                } else {
                    function24 = null;
                }
                if (i2 == 0) {
                    function25 = function8;
                } else {
                    function25 = null;
                }
                if (i3 == 0) {
                    function26 = function9;
                } else {
                    function26 = null;
                }
                if ((i & 16384) == 0) {
                    shape3 = shape;
                    $dirty1 = $dirty4;
                } else {
                    shape3 = INSTANCE.getShape($composer3, 6);
                    $dirty1 = $dirty4 & (-57345);
                }
                if ((i & 32768) != 0) {
                    colors3 = colors($composer3, ($dirty1 >> 24) & 14);
                    $dirty1 &= -458753;
                } else {
                    colors3 = colors;
                }
                if ((i & 65536) == 0) {
                    contentPadding3 = contentPadding;
                } else {
                    if (function12 != null) {
                        contentPadding3 = m3041contentPaddingWithLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    } else {
                        contentPadding3 = m3042contentPaddingWithoutLabela9UjIt4$default(this, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                    }
                    $dirty1 &= -3670017;
                }
                if (i13 == 0) {
                    PaddingValues contentPadding5 = contentPadding3;
                    function27 = function21;
                    function2RememberComposableLambda = function10;
                    shape4 = shape3;
                    function28 = function12;
                    colors4 = colors3;
                    function29 = function26;
                    function30 = function23;
                    function31 = function24;
                    function32 = function25;
                    $dirty2 = $dirty1;
                    contentPadding4 = contentPadding5;
                    isError4 = isError2;
                } else {
                    final TextFieldColors colors5 = colors3;
                    final Shape shape6 = shape3;
                    final boolean isError5 = isError2;
                    final boolean z2 = z;
                    final InteractionSource interactionSource3 = interactionSource2;
                    Function2<Composer, Integer, Unit> function33 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults.DecorationBox.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C390@20670L404:TextFieldDefaults.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(417908150, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous> (TextFieldDefaults.kt:390)");
                            }
                            TextFieldDefaults.INSTANCE.m3051Container4EFweAY(z2, isError5, interactionSource3, Modifier.INSTANCE, colors5, shape6, TextFieldDefaults.INSTANCE.m3056getFocusedIndicatorThicknessD9Ej5fM(), TextFieldDefaults.INSTANCE.m3060getUnfocusedIndicatorThicknessD9Ej5fM(), $composer4, 114822144, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    };
                    PaddingValues contentPadding6 = contentPadding3;
                    function27 = function21;
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(417908150, true, function33, $composer3, 54);
                    shape4 = shape3;
                    function28 = function12;
                    colors4 = colors5;
                    function29 = function26;
                    function30 = function23;
                    function31 = function24;
                    function32 = function25;
                    $dirty2 = $dirty1;
                    contentPadding4 = contentPadding6;
                    isError4 = isError2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1806980801, $dirty, $dirty2, "androidx.compose.material3.TextFieldDefaults.DecorationBox (TextFieldDefaults.kt:401)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1341809442, "CC(remember):TextFieldDefaults.kt#9igjgp");
            boolean invalid$iv = (($dirty & 14) == 4) | ((57344 & $dirty) == 16384);
            final Function2<? super Composer, ? super Integer, Unit> function34 = function28;
            Object value$iv = $composer3.rememberedValue();
            if (!invalid$iv) {
                shape5 = shape4;
                if (value$iv != Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                String text = ((TransformedText) value$iv).getText().getText();
                TextFieldType textFieldType = TextFieldType.Filled;
                TextFieldLabelPosition.Attached attached = new TextFieldLabelPosition.Attached(false, null, null, 7, null);
                if (function34 == null) {
                    $composer3.startReplaceGroup(-1353131191);
                    $composer3.endReplaceGroup();
                    composableLambda = null;
                } else {
                    $composer3.startReplaceGroup(-1353131190);
                    ComposerKt.sourceInformation($composer3, "*415@21593L15");
                    Function2 it = ComposableLambdaKt.rememberComposableLambda(1110058497, true, new Function3<TextFieldLabelScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$DecorationBox$2$1
                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(TextFieldLabelScope textFieldLabelScope, Composer composer, Integer num) {
                            invoke(textFieldLabelScope, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(TextFieldLabelScope $this$let, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C415@21598L8:TextFieldDefaults.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed2 & 17) != 16, $changed2 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1110058497, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous>.<anonymous> (TextFieldDefaults.kt:415)");
                            }
                            function34.invoke($composer4, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                    $composer3.endReplaceGroup();
                    composableLambda = (Function3) it;
                }
                $composer2 = $composer3;
                TextFieldImplKt.CommonDecorationBox(textFieldType, text, function11, attached, composableLambda, function27, function22, function30, function31, function32, function29, singleLine, enabled, isError4, interactionSource, contentPadding4, colors4, function2RememberComposableLambda, $composer2, (($dirty << 3) & 896) | 6 | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty2 << 21) & 29360128) | (($dirty2 << 21) & 234881024) | (($dirty2 << 21) & 1879048192), (($dirty2 >> 9) & 14) | (($dirty >> 6) & 112) | ($dirty & 896) | (($dirty >> 9) & 7168) | (($dirty >> 3) & 57344) | (($dirty2 >> 3) & 458752) | (($dirty2 << 3) & 3670016) | ($dirty2 & 29360128));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function18 = function34;
                function19 = function27;
                function20 = function22;
                function13 = function30;
                function14 = function31;
                function15 = function32;
                function16 = function29;
                isError3 = isError4;
                contentPadding2 = contentPadding4;
                colors2 = colors4;
                function17 = function2RememberComposableLambda;
                shape2 = shape5;
            } else {
                shape5 = shape4;
            }
            value$iv = visualTransformation.filter(new AnnotatedString(value, null, 2, null));
            $composer3.updateRememberedValue(value$iv);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            String text2 = ((TransformedText) value$iv).getText().getText();
            TextFieldType textFieldType2 = TextFieldType.Filled;
            TextFieldLabelPosition.Attached attached2 = new TextFieldLabelPosition.Attached(false, null, null, 7, null);
            if (function34 == null) {
                $composer3.startReplaceGroup(-1353131191);
                $composer3.endReplaceGroup();
                composableLambda = null;
            } else {
                $composer3.startReplaceGroup(-1353131190);
                ComposerKt.sourceInformation($composer3, "*415@21593L15");
                Function2 it2 = ComposableLambdaKt.rememberComposableLambda(1110058497, true, new Function3<TextFieldLabelScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TextFieldDefaults$DecorationBox$2$1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(TextFieldLabelScope textFieldLabelScope, Composer composer, Integer num) {
                        invoke(textFieldLabelScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(TextFieldLabelScope $this$let, Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C415@21598L8:TextFieldDefaults.kt#uh7d8r");
                        if (!$composer4.shouldExecute(($changed2 & 17) != 16, $changed2 & 1)) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1110058497, $changed2, -1, "androidx.compose.material3.TextFieldDefaults.DecorationBox.<anonymous>.<anonymous> (TextFieldDefaults.kt:415)");
                        }
                        function34.invoke($composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
                composableLambda = (Function3) it2;
            }
            $composer2 = $composer3;
            TextFieldImplKt.CommonDecorationBox(textFieldType2, text2, function11, attached2, composableLambda, function27, function22, function30, function31, function32, function29, singleLine, enabled, isError4, interactionSource, contentPadding4, colors4, function2RememberComposableLambda, $composer2, (($dirty << 3) & 896) | 6 | (($dirty >> 9) & 458752) | (($dirty >> 9) & 3670016) | (($dirty2 << 21) & 29360128) | (($dirty2 << 21) & 234881024) | (($dirty2 << 21) & 1879048192), (($dirty2 >> 9) & 14) | (($dirty >> 6) & 112) | ($dirty & 896) | (($dirty >> 9) & 7168) | (($dirty >> 3) & 57344) | (($dirty2 >> 3) & 458752) | (($dirty2 << 3) & 3670016) | ($dirty2 & 29360128));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function18 = function34;
            function19 = function27;
            function20 = function22;
            function13 = function30;
            function14 = function31;
            function15 = function32;
            function16 = function29;
            isError3 = isError4;
            contentPadding2 = contentPadding4;
            colors2 = colors4;
            function17 = function2RememberComposableLambda;
            shape2 = shape5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function13 = function6;
            function14 = function7;
            function15 = function8;
            function16 = function9;
            shape2 = shape;
            colors2 = colors;
            contentPadding2 = contentPadding;
            function17 = function10;
            function18 = function12;
            isError3 = isError2;
            function19 = function4;
            function20 = function5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldDefaults$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldDefaults.DecorationBox$lambda$3(this.f$0, value, function2, enabled, singleLine, visualTransformation, interactionSource, isError3, function18, function19, function20, function13, function14, function15, function16, shape2, colors2, contentPadding2, function17, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3041contentPaddingWithLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m3053contentPaddingWithLabela9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPaddingWithLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3053contentPaddingWithLabela9UjIt4(float start, float end, float top, float bottom) {
        return PaddingKt.m815PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3042contentPaddingWithoutLabela9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m3054contentPaddingWithoutLabela9UjIt4(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: contentPaddingWithoutLabel-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3054contentPaddingWithoutLabela9UjIt4(float start, float top, float end, float bottom) {
        return PaddingKt.m815PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3048supportingTextPaddinga9UjIt4$material3$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getSupportingTopPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 8) != 0) {
            f4 = Dp.m7905constructorimpl(0);
        }
        return textFieldDefaults.m3064supportingTextPaddinga9UjIt4$material3(f, f2, f3, f4);
    }

    /* JADX INFO: renamed from: supportingTextPadding-a9UjIt4$material3, reason: not valid java name */
    public final PaddingValues m3064supportingTextPaddinga9UjIt4$material3(float start, float top, float end, float bottom) {
        return PaddingKt.m815PaddingValuesa9UjIt4(start, top, end, bottom);
    }

    public final TextFieldColors colors(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 831731228, "C(colors)478@24135L11,478@24195L7:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(831731228, $changed, -1, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:478)");
        }
        ColorScheme colorScheme = MaterialTheme.INSTANCE.getColorScheme($composer, 6);
        ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localTextSelectionColors);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextFieldColors textFieldColorsDefaultTextFieldColors$material3 = defaultTextFieldColors$material3(colorScheme, (SelectionColors) objConsume);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return textFieldColorsDefaultTextFieldColors$material3;
    }

    /* JADX INFO: renamed from: colors-0hiis_0, reason: not valid java name */
    public final TextFieldColors m3052colors0hiis_0(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors selectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor, Composer $composer, int $changed, int $changed1, int $changed2, int $changed3, int $changed4, int i, int i2) {
        ComposerKt.sourceInformationMarkerStart($composer, 1513344955, "C(colors)N(focusedTextColor:c#ui.graphics.Color,unfocusedTextColor:c#ui.graphics.Color,disabledTextColor:c#ui.graphics.Color,errorTextColor:c#ui.graphics.Color,focusedContainerColor:c#ui.graphics.Color,unfocusedContainerColor:c#ui.graphics.Color,disabledContainerColor:c#ui.graphics.Color,errorContainerColor:c#ui.graphics.Color,cursorColor:c#ui.graphics.Color,errorCursorColor:c#ui.graphics.Color,selectionColors,focusedIndicatorColor:c#ui.graphics.Color,unfocusedIndicatorColor:c#ui.graphics.Color,disabledIndicatorColor:c#ui.graphics.Color,errorIndicatorColor:c#ui.graphics.Color,focusedLeadingIconColor:c#ui.graphics.Color,unfocusedLeadingIconColor:c#ui.graphics.Color,disabledLeadingIconColor:c#ui.graphics.Color,errorLeadingIconColor:c#ui.graphics.Color,focusedTrailingIconColor:c#ui.graphics.Color,unfocusedTrailingIconColor:c#ui.graphics.Color,disabledTrailingIconColor:c#ui.graphics.Color,errorTrailingIconColor:c#ui.graphics.Color,focusedLabelColor:c#ui.graphics.Color,unfocusedLabelColor:c#ui.graphics.Color,disabledLabelColor:c#ui.graphics.Color,errorLabelColor:c#ui.graphics.Color,focusedPlaceholderColor:c#ui.graphics.Color,unfocusedPlaceholderColor:c#ui.graphics.Color,disabledPlaceholderColor:c#ui.graphics.Color,errorPlaceholderColor:c#ui.graphics.Color,focusedSupportingTextColor:c#ui.graphics.Color,unfocusedSupportingTextColor:c#ui.graphics.Color,disabledSupportingTextColor:c#ui.graphics.Color,errorSupportingTextColor:c#ui.graphics.Color,focusedPrefixColor:c#ui.graphics.Color,unfocusedPrefixColor:c#ui.graphics.Color,disabledPrefixColor:c#ui.graphics.Color,errorPrefixColor:c#ui.graphics.Color,focusedSuffixColor:c#ui.graphics.Color,unfocusedSuffixColor:c#ui.graphics.Color,disabledSuffixColor:c#ui.graphics.Color,errorSuffixColor:c#ui.graphics.Color)580@31011L11,581@31084L7:TextFieldDefaults.kt#uh7d8r");
        long focusedTextColor2 = (i & 1) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedTextColor;
        long unfocusedTextColor2 = (i & 2) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedTextColor;
        long disabledTextColor2 = (i & 4) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledTextColor;
        long errorTextColor2 = (i & 8) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorTextColor;
        long focusedContainerColor2 = (i & 16) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedContainerColor;
        long unfocusedContainerColor2 = (i & 32) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedContainerColor;
        long disabledContainerColor2 = (i & 64) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledContainerColor;
        long errorContainerColor2 = (i & 128) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorContainerColor;
        long cursorColor2 = (i & 256) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : cursorColor;
        long errorCursorColor2 = (i & 512) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorCursorColor;
        SelectionColors selectionColors2 = (i & 1024) != 0 ? null : selectionColors;
        long focusedIndicatorColor2 = (i & 2048) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedIndicatorColor;
        long unfocusedIndicatorColor2 = (i & 4096) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedIndicatorColor;
        long disabledIndicatorColor2 = (i & 8192) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledIndicatorColor;
        long errorIndicatorColor2 = (i & 16384) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorIndicatorColor;
        long focusedLeadingIconColor2 = (32768 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedLeadingIconColor;
        long unfocusedLeadingIconColor2 = (65536 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedLeadingIconColor;
        long disabledLeadingIconColor2 = (131072 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledLeadingIconColor;
        long errorLeadingIconColor2 = (262144 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorLeadingIconColor;
        long focusedTrailingIconColor2 = (524288 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedTrailingIconColor;
        long unfocusedTrailingIconColor2 = (1048576 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedTrailingIconColor;
        long disabledTrailingIconColor2 = (2097152 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledTrailingIconColor;
        long errorTrailingIconColor2 = (4194304 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorTrailingIconColor;
        long focusedLabelColor2 = (8388608 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedLabelColor;
        long unfocusedLabelColor2 = (16777216 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedLabelColor;
        long disabledLabelColor2 = (33554432 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledLabelColor;
        long errorLabelColor2 = (67108864 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorLabelColor;
        long focusedPlaceholderColor2 = (134217728 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedPlaceholderColor;
        long unfocusedPlaceholderColor2 = (268435456 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedPlaceholderColor;
        long disabledPlaceholderColor2 = (536870912 & i) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledPlaceholderColor;
        long errorPlaceholderColor2 = (i & 1073741824) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorPlaceholderColor;
        long focusedSupportingTextColor2 = (i2 & 1) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedSupportingTextColor;
        long unfocusedSupportingTextColor2 = (i2 & 2) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedSupportingTextColor;
        long disabledSupportingTextColor2 = (i2 & 4) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledSupportingTextColor;
        long errorSupportingTextColor2 = (i2 & 8) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorSupportingTextColor;
        long focusedPrefixColor2 = (i2 & 16) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedPrefixColor;
        long unfocusedPrefixColor2 = (i2 & 32) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedPrefixColor;
        long disabledPrefixColor2 = (i2 & 64) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledPrefixColor;
        long errorPrefixColor2 = (i2 & 128) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorPrefixColor;
        long focusedSuffixColor2 = (i2 & 256) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : focusedSuffixColor;
        long unfocusedSuffixColor2 = (i2 & 512) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : unfocusedSuffixColor;
        long disabledSuffixColor2 = (i2 & 1024) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : disabledSuffixColor;
        long errorSuffixColor2 = (i2 & 2048) != 0 ? Color.INSTANCE.m5133getUnspecified0d7_KjU() : errorSuffixColor;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1513344955, $changed, $changed1, "androidx.compose.material3.TextFieldDefaults.colors (TextFieldDefaults.kt:580)");
        }
        ColorScheme colorScheme = MaterialTheme.INSTANCE.getColorScheme($composer, 6);
        ProvidableCompositionLocal<SelectionColors> localTextSelectionColors = TextSelectionColorsKt.getLocalTextSelectionColors();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localTextSelectionColors);
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextFieldColors textFieldColorsM2988copyejIjP34 = defaultTextFieldColors$material3(colorScheme, (SelectionColors) objConsume).m2988copyejIjP34(focusedTextColor2, unfocusedTextColor2, disabledTextColor2, errorTextColor2, focusedContainerColor2, unfocusedContainerColor2, disabledContainerColor2, errorContainerColor2, cursorColor2, errorCursorColor2, selectionColors2, focusedIndicatorColor2, unfocusedIndicatorColor2, disabledIndicatorColor2, errorIndicatorColor2, focusedLeadingIconColor2, unfocusedLeadingIconColor2, disabledLeadingIconColor2, errorLeadingIconColor2, focusedTrailingIconColor2, unfocusedTrailingIconColor2, disabledTrailingIconColor2, errorTrailingIconColor2, focusedLabelColor2, unfocusedLabelColor2, disabledLabelColor2, errorLabelColor2, focusedPlaceholderColor2, unfocusedPlaceholderColor2, disabledPlaceholderColor2, errorPlaceholderColor2, focusedSupportingTextColor2, unfocusedSupportingTextColor2, disabledSupportingTextColor2, errorSupportingTextColor2, focusedPrefixColor2, unfocusedPrefixColor2, disabledPrefixColor2, errorPrefixColor2, focusedSuffixColor2, unfocusedSuffixColor2, disabledSuffixColor2, errorSuffixColor2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return textFieldColorsM2988copyejIjP34;
    }

    public final TextFieldColors defaultTextFieldColors$material3(ColorScheme $this$defaultTextFieldColors, SelectionColors localTextSelectionColors) {
        TextFieldColors it;
        TextFieldColors cachedColors = $this$defaultTextFieldColors.getDefaultTextFieldColorsCached();
        if (cachedColors != null) {
            if (Intrinsics.areEqual(cachedColors.getTextSelectionColors(), localTextSelectionColors)) {
                it = cachedColors;
            } else {
                it = cachedColors.m2988copyejIjP34(((-1025) & 1) != 0 ? cachedColors.focusedTextColor : 0L, ((-1025) & 2) != 0 ? cachedColors.unfocusedTextColor : 0L, ((-1025) & 4) != 0 ? cachedColors.disabledTextColor : 0L, ((-1025) & 8) != 0 ? cachedColors.errorTextColor : 0L, ((-1025) & 16) != 0 ? cachedColors.focusedContainerColor : 0L, ((-1025) & 32) != 0 ? cachedColors.unfocusedContainerColor : 0L, ((-1025) & 64) != 0 ? cachedColors.disabledContainerColor : 0L, ((-1025) & 128) != 0 ? cachedColors.errorContainerColor : 0L, ((-1025) & 256) != 0 ? cachedColors.cursorColor : 0L, ((-1025) & 512) != 0 ? cachedColors.errorCursorColor : 0L, ((-1025) & 1024) != 0 ? cachedColors.textSelectionColors : localTextSelectionColors, ((-1025) & 2048) != 0 ? cachedColors.focusedIndicatorColor : 0L, ((-1025) & 4096) != 0 ? cachedColors.unfocusedIndicatorColor : 0L, ((-1025) & 8192) != 0 ? cachedColors.disabledIndicatorColor : 0L, ((-1025) & 16384) != 0 ? cachedColors.errorIndicatorColor : 0L, ((-1025) & 32768) != 0 ? cachedColors.focusedLeadingIconColor : 0L, ((-1025) & 65536) != 0 ? cachedColors.unfocusedLeadingIconColor : 0L, ((-1025) & 131072) != 0 ? cachedColors.disabledLeadingIconColor : 0L, ((-1025) & 262144) != 0 ? cachedColors.errorLeadingIconColor : 0L, ((-1025) & 524288) != 0 ? cachedColors.focusedTrailingIconColor : 0L, ((-1025) & 1048576) != 0 ? cachedColors.unfocusedTrailingIconColor : 0L, ((-1025) & 2097152) != 0 ? cachedColors.disabledTrailingIconColor : 0L, ((-1025) & 4194304) != 0 ? cachedColors.errorTrailingIconColor : 0L, ((-1025) & 8388608) != 0 ? cachedColors.focusedLabelColor : 0L, ((-1025) & 16777216) != 0 ? cachedColors.unfocusedLabelColor : 0L, ((-1025) & 33554432) != 0 ? cachedColors.disabledLabelColor : 0L, ((-1025) & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? cachedColors.errorLabelColor : 0L, ((-1025) & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? cachedColors.focusedPlaceholderColor : 0L, ((-1025) & 268435456) != 0 ? cachedColors.unfocusedPlaceholderColor : 0L, ((-1025) & 536870912) != 0 ? cachedColors.disabledPlaceholderColor : 0L, ((-1025) & 1073741824) != 0 ? cachedColors.errorPlaceholderColor : 0L, ((-1025) & Integer.MIN_VALUE) != 0 ? cachedColors.focusedSupportingTextColor : 0L, (2047 & 1) != 0 ? cachedColors.unfocusedSupportingTextColor : 0L, (2047 & 2) != 0 ? cachedColors.disabledSupportingTextColor : 0L, (2047 & 4) != 0 ? cachedColors.errorSupportingTextColor : 0L, (2047 & 8) != 0 ? cachedColors.focusedPrefixColor : 0L, (2047 & 16) != 0 ? cachedColors.unfocusedPrefixColor : 0L, (2047 & 32) != 0 ? cachedColors.disabledPrefixColor : 0L, (2047 & 64) != 0 ? cachedColors.errorPrefixColor : 0L, (2047 & 128) != 0 ? cachedColors.focusedSuffixColor : 0L, (2047 & 256) != 0 ? cachedColors.unfocusedSuffixColor : 0L, (2047 & 512) != 0 ? cachedColors.disabledSuffixColor : 0L, (2047 & 1024) != 0 ? cachedColors.errorSuffixColor : 0L);
                $this$defaultTextFieldColors.setDefaultTextFieldColorsCached$material3(it);
            }
            if (it != null) {
                return it;
            }
        }
        long jFromToken = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusInputColor());
        long jFromToken2 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputColor());
        long jFromToken3 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledInputColor());
        long jM5095copywmQWz5c = Color.m5095copywmQWz5c(jFromToken3, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken3) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken3) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken3) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken3) : 0.0f);
        long jFromToken4 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorInputColor());
        long jFromToken5 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
        long jFromToken6 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
        long jFromToken7 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
        long jFromToken8 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getContainerColor());
        long jFromToken9 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getCaretColor());
        long jFromToken10 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorFocusCaretColor());
        long jFromToken11 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusActiveIndicatorColor());
        long jFromToken12 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getActiveIndicatorColor());
        long jFromToken13 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorColor());
        long jM5095copywmQWz5c2 = Color.m5095copywmQWz5c(jFromToken13, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken13) : FilledTextFieldTokens.INSTANCE.getDisabledActiveIndicatorOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken13) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken13) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken13) : 0.0f);
        long jFromToken14 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorActiveIndicatorColor());
        long jFromToken15 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusLeadingIconColor());
        long jFromToken16 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getLeadingIconColor());
        long jFromToken17 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconColor());
        long jM5095copywmQWz5c3 = Color.m5095copywmQWz5c(jFromToken17, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken17) : FilledTextFieldTokens.INSTANCE.getDisabledLeadingIconOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken17) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken17) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken17) : 0.0f);
        long jFromToken18 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorLeadingIconColor());
        long jFromToken19 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusTrailingIconColor());
        long jFromToken20 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getTrailingIconColor());
        long jFromToken21 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconColor());
        long jM5095copywmQWz5c4 = Color.m5095copywmQWz5c(jFromToken21, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken21) : FilledTextFieldTokens.INSTANCE.getDisabledTrailingIconOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken21) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken21) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken21) : 0.0f);
        long jFromToken22 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorTrailingIconColor());
        long jFromToken23 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusLabelColor());
        long jFromToken24 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getLabelColor());
        long jFromToken25 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledLabelColor());
        long jM5095copywmQWz5c5 = Color.m5095copywmQWz5c(jFromToken25, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken25) : FilledTextFieldTokens.INSTANCE.getDisabledLabelOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken25) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken25) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken25) : 0.0f);
        long jFromToken26 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorLabelColor());
        long jFromToken27 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor());
        long jFromToken28 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor());
        long jFromToken29 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledInputColor());
        long jM5095copywmQWz5c6 = Color.m5095copywmQWz5c(jFromToken29, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken29) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken29) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken29) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken29) : 0.0f);
        long jFromToken30 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPlaceholderColor());
        long jFromToken31 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getFocusSupportingColor());
        long jFromToken32 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getSupportingColor());
        long jFromToken33 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getDisabledSupportingColor());
        long jM5095copywmQWz5c7 = Color.m5095copywmQWz5c(jFromToken33, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken33) : FilledTextFieldTokens.INSTANCE.getDisabledSupportingOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken33) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken33) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken33) : 0.0f);
        long jFromToken34 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getErrorSupportingColor());
        long jFromToken35 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
        long jFromToken36 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
        long jFromToken37 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
        long jM5095copywmQWz5c8 = Color.m5095copywmQWz5c(jFromToken37, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken37) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken37) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken37) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken37) : 0.0f);
        long jFromToken38 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputPrefixColor());
        long jFromToken39 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor());
        long jFromToken40 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor());
        long jFromToken41 = ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor());
        TextFieldColors it2 = new TextFieldColors(jFromToken, jFromToken2, jM5095copywmQWz5c, jFromToken4, jFromToken5, jFromToken6, jFromToken7, jFromToken8, jFromToken9, jFromToken10, localTextSelectionColors, jFromToken11, jFromToken12, jM5095copywmQWz5c2, jFromToken14, jFromToken15, jFromToken16, jM5095copywmQWz5c3, jFromToken18, jFromToken19, jFromToken20, jM5095copywmQWz5c4, jFromToken22, jFromToken23, jFromToken24, jM5095copywmQWz5c5, jFromToken26, jFromToken27, jFromToken28, jM5095copywmQWz5c6, jFromToken30, jFromToken31, jFromToken32, jM5095copywmQWz5c7, jFromToken34, jFromToken35, jFromToken36, jM5095copywmQWz5c8, jFromToken38, jFromToken39, jFromToken40, Color.m5095copywmQWz5c(jFromToken41, (14 & 1) != 0 ? Color.m5099getAlphaimpl(jFromToken41) : FilledTextFieldTokens.INSTANCE.getDisabledInputOpacity(), (14 & 2) != 0 ? Color.m5103getRedimpl(jFromToken41) : 0.0f, (14 & 4) != 0 ? Color.m5102getGreenimpl(jFromToken41) : 0.0f, (14 & 8) != 0 ? Color.m5100getBlueimpl(jFromToken41) : 0.0f), ColorSchemeKt.fromToken($this$defaultTextFieldColors, FilledTextFieldTokens.INSTANCE.getInputSuffixColor()), null);
        $this$defaultTextFieldColors.setDefaultTextFieldColorsCached$material3(it2);
        return it2;
    }

    /* JADX INFO: renamed from: indicatorLine-gv0btCI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3046indicatorLinegv0btCI$default(TextFieldDefaults textFieldDefaults, Modifier modifier, boolean z, boolean z2, InteractionSource interactionSource, TextFieldColors textFieldColors, float f, float f2, int i, Object obj) {
        float f3;
        float f4;
        if ((i & 16) == 0) {
            f3 = f;
        } else {
            f3 = FocusedIndicatorThickness;
        }
        if ((i & 32) == 0) {
            f4 = f2;
        } else {
            f4 = UnfocusedIndicatorThickness;
        }
        return textFieldDefaults.m3062indicatorLinegv0btCI(modifier, z, z2, interactionSource, textFieldColors, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with `textFieldShape`.")
    /* JADX INFO: renamed from: indicatorLine-gv0btCI, reason: not valid java name */
    public final /* synthetic */ Modifier m3062indicatorLinegv0btCI(Modifier $this$indicatorLine_u2dgv0btCI, boolean enabled, boolean isError, InteractionSource interactionSource, TextFieldColors colors, float focusedIndicatorLineThickness, float unfocusedIndicatorLineThickness) {
        return m3061indicatorLineAWlRVLg($this$indicatorLine_u2dgv0btCI, enabled, isError, interactionSource, colors, null, focusedIndicatorLineThickness, unfocusedIndicatorLineThickness);
    }

    /* JADX WARN: Code duplicated, block: B:87:0x010c  */
    /* JADX WARN: Code duplicated, block: B:90:0x014c  */
    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to TextFieldDefaults.Container", replaceWith = @ReplaceWith(expression = "Container(\n    enabled = enabled,\n    isError = isError,\n    interactionSource = interactionSource,\n    colors = colors,\n    shape = shape,\n)", imports = {}))
    public final void ContainerBox(final boolean enabled, final boolean isError, final InteractionSource interactionSource, final TextFieldColors colors, Shape shape, Composer $composer, final int $changed, final int i) {
        boolean z;
        boolean z2;
        InteractionSource interactionSource2;
        TextFieldColors textFieldColors;
        Shape shape2;
        TextFieldDefaults textFieldDefaults;
        Composer $composer2;
        final Shape shape3;
        Shape shape4;
        Composer $composer3 = $composer.startRestartGroup(918564008);
        ComposerKt.sourceInformation($composer3, "C(ContainerBox)N(enabled,isError,interactionSource,colors,shape)761@41419L368:TextFieldDefaults.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            z = enabled;
        } else if (($changed & 6) == 0) {
            z = enabled;
            $dirty |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = enabled;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            z2 = isError;
        } else if (($changed & 48) == 0) {
            z2 = isError;
            $dirty |= $composer3.changed(z2) ? 32 : 16;
        } else {
            z2 = isError;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
            interactionSource2 = interactionSource;
        } else if (($changed & 384) == 0) {
            interactionSource2 = interactionSource;
            $dirty |= $composer3.changed(interactionSource2) ? 256 : 128;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
            textFieldColors = colors;
        } else if (($changed & 3072) == 0) {
            textFieldColors = colors;
            $dirty |= $composer3.changed(textFieldColors) ? 2048 : 1024;
        } else {
            textFieldColors = colors;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i2 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty |= i2;
            } else {
                shape2 = shape;
            }
            $dirty |= i2;
        } else {
            shape2 = shape;
        }
        if ((i & 32) != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            textFieldDefaults = this;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textFieldDefaults = this;
            $dirty |= $composer3.changed(textFieldDefaults) ? 131072 : 65536;
        } else {
            textFieldDefaults = this;
        }
        if ($composer3.shouldExecute((74899 & $dirty) != 74898, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "759@41396L5");
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    shape4 = INSTANCE.getShape($composer3, 6);
                }
                $composer3.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(918564008, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.ContainerBox (TextFieldDefaults.kt:761)");
                }
                $composer2 = $composer3;
                textFieldDefaults.m3051Container4EFweAY(z, z2, interactionSource2, Modifier.INSTANCE, textFieldColors, shape4, FocusedIndicatorThickness, UnfocusedIndicatorThickness, $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 9) & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                shape3 = shape4;
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
            }
            shape4 = shape2;
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(918564008, $dirty, -1, "androidx.compose.material3.TextFieldDefaults.ContainerBox (TextFieldDefaults.kt:761)");
            }
            $composer2 = $composer3;
            textFieldDefaults.m3051Container4EFweAY(z, z2, interactionSource2, Modifier.INSTANCE, textFieldColors, shape4, FocusedIndicatorThickness, UnfocusedIndicatorThickness, $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896) | (($dirty << 3) & 57344) | (($dirty << 3) & 458752) | (($dirty << 9) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            shape3 = shape2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TextFieldDefaults$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldDefaults.ContainerBox$lambda$7(this.f$0, enabled, isError, interactionSource, colors, shape3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final Shape getOutlinedShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -584749279, "C(<get-outlinedShape>)782@42187L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-584749279, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-outlinedShape> (TextFieldDefaults.kt:782)");
        }
        Shape shape = OutlinedTextFieldDefaults.INSTANCE.getShape($composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return shape;
    }

    public final Shape getFilledShape(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 611926497, "C(<get-filledShape>)790@42434L5:TextFieldDefaults.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(611926497, $changed, -1, "androidx.compose.material3.TextFieldDefaults.<get-filledShape> (TextFieldDefaults.kt:790)");
        }
        Shape shape = getShape($composer, $changed & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return shape;
    }

    /* JADX INFO: renamed from: getUnfocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m3059getUnfocusedBorderThicknessD9Ej5fM() {
        return UnfocusedBorderThickness;
    }

    /* JADX INFO: renamed from: getFocusedBorderThickness-D9Ej5fM, reason: not valid java name */
    public final float m3055getFocusedBorderThicknessD9Ej5fM() {
        return FocusedBorderThickness;
    }

    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3049textFieldWithLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 2) != 0) {
            f2 = TextFieldImplKt.getTextFieldPadding();
        }
        if ((i & 4) != 0) {
            f3 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        if ((i & 8) != 0) {
            f4 = TextFieldKt.getTextFieldWithLabelVerticalPadding();
        }
        return textFieldDefaults.m3065textFieldWithLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.contentPaddingWithLabel`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.contentPaddingWithLabel(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {}))
    /* JADX INFO: renamed from: textFieldWithLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3065textFieldWithLabelPaddinga9UjIt4(float start, float end, float top, float bottom) {
        return m3053contentPaddingWithLabela9UjIt4(start, end, top, bottom);
    }

    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3050textFieldWithoutLabelPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m3066textFieldWithoutLabelPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `TextFieldDefaults.contentPaddingWithoutLabel`", replaceWith = @ReplaceWith(expression = "TextFieldDefaults.contentPaddingWithoutLabel(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {}))
    /* JADX INFO: renamed from: textFieldWithoutLabelPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3066textFieldWithoutLabelPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return m3054contentPaddingWithoutLabela9UjIt4(start, top, end, bottom);
    }

    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m3047outlinedTextFieldPaddinga9UjIt4$default(TextFieldDefaults textFieldDefaults, float f, float f2, float f3, float f4, int i, Object obj) {
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
        return textFieldDefaults.m3063outlinedTextFieldPaddinga9UjIt4(f, f2, f3, f4);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Renamed to `OutlinedTextFieldDefaults.contentPadding`", replaceWith = @ReplaceWith(expression = "OutlinedTextFieldDefaults.contentPadding(\n        start = start,\n        top = top,\n        end = end,\n        bottom = bottom,\n    )", imports = {"androidx.compose.material.OutlinedTextFieldDefaults"}))
    /* JADX INFO: renamed from: outlinedTextFieldPadding-a9UjIt4, reason: not valid java name */
    public final PaddingValues m3063outlinedTextFieldPaddinga9UjIt4(float start, float top, float end, float bottom) {
        return OutlinedTextFieldDefaults.INSTANCE.m2709contentPaddinga9UjIt4(start, top, end, bottom);
    }
}
