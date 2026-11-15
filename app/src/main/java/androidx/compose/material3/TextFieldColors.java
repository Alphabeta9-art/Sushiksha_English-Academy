package androidx.compose.material3;

import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.ui.graphics.Color;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\bU\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001Bß\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\u0006\u0010!\u001a\u00020\u0003\u0012\u0006\u0010\"\u001a\u00020\u0003\u0012\u0006\u0010#\u001a\u00020\u0003\u0012\u0006\u0010$\u001a\u00020\u0003\u0012\u0006\u0010%\u001a\u00020\u0003\u0012\u0006\u0010&\u001a\u00020\u0003\u0012\u0006\u0010'\u001a\u00020\u0003\u0012\u0006\u0010(\u001a\u00020\u0003\u0012\u0006\u0010)\u001a\u00020\u0003\u0012\u0006\u0010*\u001a\u00020\u0003\u0012\u0006\u0010+\u001a\u00020\u0003\u0012\u0006\u0010,\u001a\u00020\u0003\u0012\u0006\u0010-\u001a\u00020\u0003\u0012\u0006\u0010.\u001a\u00020\u0003¢\u0006\u0004\b/\u00100J½\u0003\u0010_\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00032\b\b\u0002\u0010$\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010'\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010*\u001a\u00020\u00032\b\b\u0002\u0010+\u001a\u00020\u00032\b\b\u0002\u0010,\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u0003¢\u0006\u0004\b`\u0010aJ!\u0010b\u001a\u00020\u000e*\u0004\u0018\u00010\u000e2\f\u0010c\u001a\b\u0012\u0004\u0012\u00020\u000e0dH\u0000¢\u0006\u0002\beJ'\u0010f\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\bk\u0010lJ'\u0010m\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\bn\u0010lJ'\u0010o\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\bp\u0010lJ'\u0010q\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\br\u0010lJ'\u0010s\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\bt\u0010lJ'\u0010u\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\bv\u0010lJ'\u0010w\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\bx\u0010lJ'\u0010y\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\bz\u0010lJ'\u0010{\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\b|\u0010lJ'\u0010}\u001a\u00020\u00032\u0006\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020h2\u0006\u0010j\u001a\u00020hH\u0001¢\u0006\u0004\b~\u0010lJ\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010i\u001a\u00020hH\u0001¢\u0006\u0005\b\u007f\u0010\u0080\u0001J\u0015\u0010\u0081\u0001\u001a\u00020h2\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\n\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b1\u00102R\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b4\u00102R\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b5\u00102R\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b6\u00102R\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b7\u00102R\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b8\u00102R\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b9\u00102R\u0013\u0010\n\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b:\u00102R\u0013\u0010\u000b\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b;\u00102R\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b<\u00102R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0013\u0010\u000f\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b?\u00102R\u0013\u0010\u0010\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b@\u00102R\u0013\u0010\u0011\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bA\u00102R\u0013\u0010\u0012\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bB\u00102R\u0013\u0010\u0013\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bC\u00102R\u0013\u0010\u0014\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bD\u00102R\u0013\u0010\u0015\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bE\u00102R\u0013\u0010\u0016\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bF\u00102R\u0013\u0010\u0017\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bG\u00102R\u0013\u0010\u0018\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bH\u00102R\u0013\u0010\u0019\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bI\u00102R\u0013\u0010\u001a\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bJ\u00102R\u0013\u0010\u001b\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bK\u00102R\u0013\u0010\u001c\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bL\u00102R\u0013\u0010\u001d\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bM\u00102R\u0013\u0010\u001e\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bN\u00102R\u0013\u0010\u001f\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bO\u00102R\u0013\u0010 \u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bP\u00102R\u0013\u0010!\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bQ\u00102R\u0013\u0010\"\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bR\u00102R\u0013\u0010#\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bS\u00102R\u0013\u0010$\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bT\u00102R\u0013\u0010%\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bU\u00102R\u0013\u0010&\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bV\u00102R\u0013\u0010'\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bW\u00102R\u0013\u0010(\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bX\u00102R\u0013\u0010)\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bY\u00102R\u0013\u0010*\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\bZ\u00102R\u0013\u0010+\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b[\u00102R\u0013\u0010,\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b\\\u00102R\u0013\u0010-\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b]\u00102R\u0013\u0010.\u001a\u00020\u0003¢\u0006\n\n\u0002\u00103\u001a\u0004\b^\u00102¨\u0006\u0085\u0001"}, d2 = {"Landroidx/compose/material3/TextFieldColors;", "", "focusedTextColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextColor", "disabledTextColor", "errorTextColor", "focusedContainerColor", "unfocusedContainerColor", "disabledContainerColor", "errorContainerColor", "cursorColor", "errorCursorColor", "textSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "focusedIndicatorColor", "unfocusedIndicatorColor", "disabledIndicatorColor", "errorIndicatorColor", "focusedLeadingIconColor", "unfocusedLeadingIconColor", "disabledLeadingIconColor", "errorLeadingIconColor", "focusedTrailingIconColor", "unfocusedTrailingIconColor", "disabledTrailingIconColor", "errorTrailingIconColor", "focusedLabelColor", "unfocusedLabelColor", "disabledLabelColor", "errorLabelColor", "focusedPlaceholderColor", "unfocusedPlaceholderColor", "disabledPlaceholderColor", "errorPlaceholderColor", "focusedSupportingTextColor", "unfocusedSupportingTextColor", "disabledSupportingTextColor", "errorSupportingTextColor", "focusedPrefixColor", "unfocusedPrefixColor", "disabledPrefixColor", "errorPrefixColor", "focusedSuffixColor", "unfocusedSuffixColor", "disabledSuffixColor", "errorSuffixColor", "<init>", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getFocusedTextColor-0d7_KjU", "()J", "J", "getUnfocusedTextColor-0d7_KjU", "getDisabledTextColor-0d7_KjU", "getErrorTextColor-0d7_KjU", "getFocusedContainerColor-0d7_KjU", "getUnfocusedContainerColor-0d7_KjU", "getDisabledContainerColor-0d7_KjU", "getErrorContainerColor-0d7_KjU", "getCursorColor-0d7_KjU", "getErrorCursorColor-0d7_KjU", "getTextSelectionColors", "()Landroidx/compose/foundation/text/selection/TextSelectionColors;", "getFocusedIndicatorColor-0d7_KjU", "getUnfocusedIndicatorColor-0d7_KjU", "getDisabledIndicatorColor-0d7_KjU", "getErrorIndicatorColor-0d7_KjU", "getFocusedLeadingIconColor-0d7_KjU", "getUnfocusedLeadingIconColor-0d7_KjU", "getDisabledLeadingIconColor-0d7_KjU", "getErrorLeadingIconColor-0d7_KjU", "getFocusedTrailingIconColor-0d7_KjU", "getUnfocusedTrailingIconColor-0d7_KjU", "getDisabledTrailingIconColor-0d7_KjU", "getErrorTrailingIconColor-0d7_KjU", "getFocusedLabelColor-0d7_KjU", "getUnfocusedLabelColor-0d7_KjU", "getDisabledLabelColor-0d7_KjU", "getErrorLabelColor-0d7_KjU", "getFocusedPlaceholderColor-0d7_KjU", "getUnfocusedPlaceholderColor-0d7_KjU", "getDisabledPlaceholderColor-0d7_KjU", "getErrorPlaceholderColor-0d7_KjU", "getFocusedSupportingTextColor-0d7_KjU", "getUnfocusedSupportingTextColor-0d7_KjU", "getDisabledSupportingTextColor-0d7_KjU", "getErrorSupportingTextColor-0d7_KjU", "getFocusedPrefixColor-0d7_KjU", "getUnfocusedPrefixColor-0d7_KjU", "getDisabledPrefixColor-0d7_KjU", "getErrorPrefixColor-0d7_KjU", "getFocusedSuffixColor-0d7_KjU", "getUnfocusedSuffixColor-0d7_KjU", "getDisabledSuffixColor-0d7_KjU", "getErrorSuffixColor-0d7_KjU", "copy", "copy-ejIjP34", "(JJJJJJJJJJLandroidx/compose/foundation/text/selection/TextSelectionColors;JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/TextFieldColors;", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse$material3", "leadingIconColor", "enabled", "", "isError", "focused", "leadingIconColor-XeAY9LY$material3", "(ZZZ)J", "trailingIconColor", "trailingIconColor-XeAY9LY$material3", "indicatorColor", "indicatorColor-XeAY9LY$material3", "containerColor", "containerColor-XeAY9LY$material3", "placeholderColor", "placeholderColor-XeAY9LY$material3", "labelColor", "labelColor-XeAY9LY$material3", "textColor", "textColor-XeAY9LY$material3", "supportingTextColor", "supportingTextColor-XeAY9LY$material3", "prefixColor", "prefixColor-XeAY9LY$material3", "suffixColor", "suffixColor-XeAY9LY$material3", "cursorColor-vNxB06k$material3", "(Z)J", MetadataValidation.EQUALS, "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextFieldColors {
    public static final int $stable = 0;
    private final long cursorColor;
    private final long disabledContainerColor;
    private final long disabledIndicatorColor;
    private final long disabledLabelColor;
    private final long disabledLeadingIconColor;
    private final long disabledPlaceholderColor;
    private final long disabledPrefixColor;
    private final long disabledSuffixColor;
    private final long disabledSupportingTextColor;
    private final long disabledTextColor;
    private final long disabledTrailingIconColor;
    private final long errorContainerColor;
    private final long errorCursorColor;
    private final long errorIndicatorColor;
    private final long errorLabelColor;
    private final long errorLeadingIconColor;
    private final long errorPlaceholderColor;
    private final long errorPrefixColor;
    private final long errorSuffixColor;
    private final long errorSupportingTextColor;
    private final long errorTextColor;
    private final long errorTrailingIconColor;
    private final long focusedContainerColor;
    private final long focusedIndicatorColor;
    private final long focusedLabelColor;
    private final long focusedLeadingIconColor;
    private final long focusedPlaceholderColor;
    private final long focusedPrefixColor;
    private final long focusedSuffixColor;
    private final long focusedSupportingTextColor;
    private final long focusedTextColor;
    private final long focusedTrailingIconColor;
    private final SelectionColors textSelectionColors;
    private final long unfocusedContainerColor;
    private final long unfocusedIndicatorColor;
    private final long unfocusedLabelColor;
    private final long unfocusedLeadingIconColor;
    private final long unfocusedPlaceholderColor;
    private final long unfocusedPrefixColor;
    private final long unfocusedSuffixColor;
    private final long unfocusedSupportingTextColor;
    private final long unfocusedTextColor;
    private final long unfocusedTrailingIconColor;

    public /* synthetic */ TextFieldColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, SelectionColors selectionColors, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, selectionColors, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, j39, j40, j41, j42);
    }

    private TextFieldColors(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors textSelectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor) {
        this.focusedTextColor = focusedTextColor;
        this.unfocusedTextColor = unfocusedTextColor;
        this.disabledTextColor = disabledTextColor;
        this.errorTextColor = errorTextColor;
        this.focusedContainerColor = focusedContainerColor;
        this.unfocusedContainerColor = unfocusedContainerColor;
        this.disabledContainerColor = disabledContainerColor;
        this.errorContainerColor = errorContainerColor;
        this.cursorColor = cursorColor;
        this.errorCursorColor = errorCursorColor;
        this.textSelectionColors = textSelectionColors;
        this.focusedIndicatorColor = focusedIndicatorColor;
        this.unfocusedIndicatorColor = unfocusedIndicatorColor;
        this.disabledIndicatorColor = disabledIndicatorColor;
        this.errorIndicatorColor = errorIndicatorColor;
        this.focusedLeadingIconColor = focusedLeadingIconColor;
        this.unfocusedLeadingIconColor = unfocusedLeadingIconColor;
        this.disabledLeadingIconColor = disabledLeadingIconColor;
        this.errorLeadingIconColor = errorLeadingIconColor;
        this.focusedTrailingIconColor = focusedTrailingIconColor;
        this.unfocusedTrailingIconColor = unfocusedTrailingIconColor;
        this.disabledTrailingIconColor = disabledTrailingIconColor;
        this.errorTrailingIconColor = errorTrailingIconColor;
        this.focusedLabelColor = focusedLabelColor;
        this.unfocusedLabelColor = unfocusedLabelColor;
        this.disabledLabelColor = disabledLabelColor;
        this.errorLabelColor = errorLabelColor;
        this.focusedPlaceholderColor = focusedPlaceholderColor;
        this.unfocusedPlaceholderColor = unfocusedPlaceholderColor;
        this.disabledPlaceholderColor = disabledPlaceholderColor;
        this.errorPlaceholderColor = errorPlaceholderColor;
        this.focusedSupportingTextColor = focusedSupportingTextColor;
        this.unfocusedSupportingTextColor = unfocusedSupportingTextColor;
        this.disabledSupportingTextColor = disabledSupportingTextColor;
        this.errorSupportingTextColor = errorSupportingTextColor;
        this.focusedPrefixColor = focusedPrefixColor;
        this.unfocusedPrefixColor = unfocusedPrefixColor;
        this.disabledPrefixColor = disabledPrefixColor;
        this.errorPrefixColor = errorPrefixColor;
        this.focusedSuffixColor = focusedSuffixColor;
        this.unfocusedSuffixColor = unfocusedSuffixColor;
        this.disabledSuffixColor = disabledSuffixColor;
        this.errorSuffixColor = errorSuffixColor;
    }

    /* JADX INFO: renamed from: getFocusedTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedTextColor() {
        return this.focusedTextColor;
    }

    /* JADX INFO: renamed from: getUnfocusedTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedTextColor() {
        return this.unfocusedTextColor;
    }

    /* JADX INFO: renamed from: getDisabledTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledTextColor() {
        return this.disabledTextColor;
    }

    /* JADX INFO: renamed from: getErrorTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorTextColor() {
        return this.errorTextColor;
    }

    /* JADX INFO: renamed from: getFocusedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedContainerColor() {
        return this.focusedContainerColor;
    }

    /* JADX INFO: renamed from: getUnfocusedContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedContainerColor() {
        return this.unfocusedContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledContainerColor() {
        return this.disabledContainerColor;
    }

    /* JADX INFO: renamed from: getErrorContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorContainerColor() {
        return this.errorContainerColor;
    }

    /* JADX INFO: renamed from: getCursorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCursorColor() {
        return this.cursorColor;
    }

    /* JADX INFO: renamed from: getErrorCursorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorCursorColor() {
        return this.errorCursorColor;
    }

    public final SelectionColors getTextSelectionColors() {
        return this.textSelectionColors;
    }

    /* JADX INFO: renamed from: getFocusedIndicatorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedIndicatorColor() {
        return this.focusedIndicatorColor;
    }

    /* JADX INFO: renamed from: getUnfocusedIndicatorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedIndicatorColor() {
        return this.unfocusedIndicatorColor;
    }

    /* JADX INFO: renamed from: getDisabledIndicatorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledIndicatorColor() {
        return this.disabledIndicatorColor;
    }

    /* JADX INFO: renamed from: getErrorIndicatorColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorIndicatorColor() {
        return this.errorIndicatorColor;
    }

    /* JADX INFO: renamed from: getFocusedLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedLeadingIconColor() {
        return this.focusedLeadingIconColor;
    }

    /* JADX INFO: renamed from: getUnfocusedLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedLeadingIconColor() {
        return this.unfocusedLeadingIconColor;
    }

    /* JADX INFO: renamed from: getDisabledLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledLeadingIconColor() {
        return this.disabledLeadingIconColor;
    }

    /* JADX INFO: renamed from: getErrorLeadingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorLeadingIconColor() {
        return this.errorLeadingIconColor;
    }

    /* JADX INFO: renamed from: getFocusedTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedTrailingIconColor() {
        return this.focusedTrailingIconColor;
    }

    /* JADX INFO: renamed from: getUnfocusedTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedTrailingIconColor() {
        return this.unfocusedTrailingIconColor;
    }

    /* JADX INFO: renamed from: getDisabledTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledTrailingIconColor() {
        return this.disabledTrailingIconColor;
    }

    /* JADX INFO: renamed from: getErrorTrailingIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorTrailingIconColor() {
        return this.errorTrailingIconColor;
    }

    /* JADX INFO: renamed from: getFocusedLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedLabelColor() {
        return this.focusedLabelColor;
    }

    /* JADX INFO: renamed from: getUnfocusedLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedLabelColor() {
        return this.unfocusedLabelColor;
    }

    /* JADX INFO: renamed from: getDisabledLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledLabelColor() {
        return this.disabledLabelColor;
    }

    /* JADX INFO: renamed from: getErrorLabelColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorLabelColor() {
        return this.errorLabelColor;
    }

    /* JADX INFO: renamed from: getFocusedPlaceholderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedPlaceholderColor() {
        return this.focusedPlaceholderColor;
    }

    /* JADX INFO: renamed from: getUnfocusedPlaceholderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedPlaceholderColor() {
        return this.unfocusedPlaceholderColor;
    }

    /* JADX INFO: renamed from: getDisabledPlaceholderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledPlaceholderColor() {
        return this.disabledPlaceholderColor;
    }

    /* JADX INFO: renamed from: getErrorPlaceholderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorPlaceholderColor() {
        return this.errorPlaceholderColor;
    }

    /* JADX INFO: renamed from: getFocusedSupportingTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedSupportingTextColor() {
        return this.focusedSupportingTextColor;
    }

    /* JADX INFO: renamed from: getUnfocusedSupportingTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedSupportingTextColor() {
        return this.unfocusedSupportingTextColor;
    }

    /* JADX INFO: renamed from: getDisabledSupportingTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSupportingTextColor() {
        return this.disabledSupportingTextColor;
    }

    /* JADX INFO: renamed from: getErrorSupportingTextColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorSupportingTextColor() {
        return this.errorSupportingTextColor;
    }

    /* JADX INFO: renamed from: getFocusedPrefixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedPrefixColor() {
        return this.focusedPrefixColor;
    }

    /* JADX INFO: renamed from: getUnfocusedPrefixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedPrefixColor() {
        return this.unfocusedPrefixColor;
    }

    /* JADX INFO: renamed from: getDisabledPrefixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledPrefixColor() {
        return this.disabledPrefixColor;
    }

    /* JADX INFO: renamed from: getErrorPrefixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorPrefixColor() {
        return this.errorPrefixColor;
    }

    /* JADX INFO: renamed from: getFocusedSuffixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getFocusedSuffixColor() {
        return this.focusedSuffixColor;
    }

    /* JADX INFO: renamed from: getUnfocusedSuffixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUnfocusedSuffixColor() {
        return this.unfocusedSuffixColor;
    }

    /* JADX INFO: renamed from: getDisabledSuffixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledSuffixColor() {
        return this.disabledSuffixColor;
    }

    /* JADX INFO: renamed from: getErrorSuffixColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorSuffixColor() {
        return this.errorSuffixColor;
    }

    /* JADX INFO: renamed from: copy-ejIjP34, reason: not valid java name */
    public final TextFieldColors m2988copyejIjP34(long focusedTextColor, long unfocusedTextColor, long disabledTextColor, long errorTextColor, long focusedContainerColor, long unfocusedContainerColor, long disabledContainerColor, long errorContainerColor, long cursorColor, long errorCursorColor, SelectionColors textSelectionColors, long focusedIndicatorColor, long unfocusedIndicatorColor, long disabledIndicatorColor, long errorIndicatorColor, long focusedLeadingIconColor, long unfocusedLeadingIconColor, long disabledLeadingIconColor, long errorLeadingIconColor, long focusedTrailingIconColor, long unfocusedTrailingIconColor, long disabledTrailingIconColor, long errorTrailingIconColor, long focusedLabelColor, long unfocusedLabelColor, long disabledLabelColor, long errorLabelColor, long focusedPlaceholderColor, long unfocusedPlaceholderColor, long disabledPlaceholderColor, long errorPlaceholderColor, long focusedSupportingTextColor, long unfocusedSupportingTextColor, long disabledSupportingTextColor, long errorSupportingTextColor, long focusedPrefixColor, long unfocusedPrefixColor, long disabledPrefixColor, long errorPrefixColor, long focusedSuffixColor, long unfocusedSuffixColor, long disabledSuffixColor, long errorSuffixColor) {
        return new TextFieldColors((focusedTextColor > 16L ? 1 : (focusedTextColor == 16L ? 0 : -1)) != 0 ? focusedTextColor : this.focusedTextColor, (unfocusedTextColor > 16L ? 1 : (unfocusedTextColor == 16L ? 0 : -1)) != 0 ? unfocusedTextColor : this.unfocusedTextColor, (disabledTextColor > 16L ? 1 : (disabledTextColor == 16L ? 0 : -1)) != 0 ? disabledTextColor : this.disabledTextColor, (errorTextColor > 16L ? 1 : (errorTextColor == 16L ? 0 : -1)) != 0 ? errorTextColor : this.errorTextColor, (focusedContainerColor > 16L ? 1 : (focusedContainerColor == 16L ? 0 : -1)) != 0 ? focusedContainerColor : this.focusedContainerColor, (unfocusedContainerColor > 16L ? 1 : (unfocusedContainerColor == 16L ? 0 : -1)) != 0 ? unfocusedContainerColor : this.unfocusedContainerColor, (disabledContainerColor > 16L ? 1 : (disabledContainerColor == 16L ? 0 : -1)) != 0 ? disabledContainerColor : this.disabledContainerColor, (errorContainerColor > 16L ? 1 : (errorContainerColor == 16L ? 0 : -1)) != 0 ? errorContainerColor : this.errorContainerColor, (cursorColor > 16L ? 1 : (cursorColor == 16L ? 0 : -1)) != 0 ? cursorColor : this.cursorColor, (errorCursorColor > 16L ? 1 : (errorCursorColor == 16L ? 0 : -1)) != 0 ? errorCursorColor : this.errorCursorColor, takeOrElse$material3(textSelectionColors, new Function0() { // from class: androidx.compose.material3.TextFieldColors$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.textSelectionColors;
            }
        }), (focusedIndicatorColor > 16L ? 1 : (focusedIndicatorColor == 16L ? 0 : -1)) != 0 ? focusedIndicatorColor : this.focusedIndicatorColor, (unfocusedIndicatorColor > 16L ? 1 : (unfocusedIndicatorColor == 16L ? 0 : -1)) != 0 ? unfocusedIndicatorColor : this.unfocusedIndicatorColor, (disabledIndicatorColor > 16L ? 1 : (disabledIndicatorColor == 16L ? 0 : -1)) != 0 ? disabledIndicatorColor : this.disabledIndicatorColor, (errorIndicatorColor > 16L ? 1 : (errorIndicatorColor == 16L ? 0 : -1)) != 0 ? errorIndicatorColor : this.errorIndicatorColor, (focusedLeadingIconColor > 16L ? 1 : (focusedLeadingIconColor == 16L ? 0 : -1)) != 0 ? focusedLeadingIconColor : this.focusedLeadingIconColor, (unfocusedLeadingIconColor > 16L ? 1 : (unfocusedLeadingIconColor == 16L ? 0 : -1)) != 0 ? unfocusedLeadingIconColor : this.unfocusedLeadingIconColor, (disabledLeadingIconColor > 16L ? 1 : (disabledLeadingIconColor == 16L ? 0 : -1)) != 0 ? disabledLeadingIconColor : this.disabledLeadingIconColor, (errorLeadingIconColor > 16L ? 1 : (errorLeadingIconColor == 16L ? 0 : -1)) != 0 ? errorLeadingIconColor : this.errorLeadingIconColor, (focusedTrailingIconColor > 16L ? 1 : (focusedTrailingIconColor == 16L ? 0 : -1)) != 0 ? focusedTrailingIconColor : this.focusedTrailingIconColor, (unfocusedTrailingIconColor > 16L ? 1 : (unfocusedTrailingIconColor == 16L ? 0 : -1)) != 0 ? unfocusedTrailingIconColor : this.unfocusedTrailingIconColor, (disabledTrailingIconColor > 16L ? 1 : (disabledTrailingIconColor == 16L ? 0 : -1)) != 0 ? disabledTrailingIconColor : this.disabledTrailingIconColor, (errorTrailingIconColor > 16L ? 1 : (errorTrailingIconColor == 16L ? 0 : -1)) != 0 ? errorTrailingIconColor : this.errorTrailingIconColor, (focusedLabelColor > 16L ? 1 : (focusedLabelColor == 16L ? 0 : -1)) != 0 ? focusedLabelColor : this.focusedLabelColor, (unfocusedLabelColor > 16L ? 1 : (unfocusedLabelColor == 16L ? 0 : -1)) != 0 ? unfocusedLabelColor : this.unfocusedLabelColor, (disabledLabelColor > 16L ? 1 : (disabledLabelColor == 16L ? 0 : -1)) != 0 ? disabledLabelColor : this.disabledLabelColor, (errorLabelColor > 16L ? 1 : (errorLabelColor == 16L ? 0 : -1)) != 0 ? errorLabelColor : this.errorLabelColor, (focusedPlaceholderColor > 16L ? 1 : (focusedPlaceholderColor == 16L ? 0 : -1)) != 0 ? focusedPlaceholderColor : this.focusedPlaceholderColor, (unfocusedPlaceholderColor > 16L ? 1 : (unfocusedPlaceholderColor == 16L ? 0 : -1)) != 0 ? unfocusedPlaceholderColor : this.unfocusedPlaceholderColor, (disabledPlaceholderColor > 16L ? 1 : (disabledPlaceholderColor == 16L ? 0 : -1)) != 0 ? disabledPlaceholderColor : this.disabledPlaceholderColor, (errorPlaceholderColor > 16L ? 1 : (errorPlaceholderColor == 16L ? 0 : -1)) != 0 ? errorPlaceholderColor : this.errorPlaceholderColor, (focusedSupportingTextColor > 16L ? 1 : (focusedSupportingTextColor == 16L ? 0 : -1)) != 0 ? focusedSupportingTextColor : this.focusedSupportingTextColor, (unfocusedSupportingTextColor > 16L ? 1 : (unfocusedSupportingTextColor == 16L ? 0 : -1)) != 0 ? unfocusedSupportingTextColor : this.unfocusedSupportingTextColor, (disabledSupportingTextColor > 16L ? 1 : (disabledSupportingTextColor == 16L ? 0 : -1)) != 0 ? disabledSupportingTextColor : this.disabledSupportingTextColor, (errorSupportingTextColor > 16L ? 1 : (errorSupportingTextColor == 16L ? 0 : -1)) != 0 ? errorSupportingTextColor : this.errorSupportingTextColor, (focusedPrefixColor > 16L ? 1 : (focusedPrefixColor == 16L ? 0 : -1)) != 0 ? focusedPrefixColor : this.focusedPrefixColor, (unfocusedPrefixColor > 16L ? 1 : (unfocusedPrefixColor == 16L ? 0 : -1)) != 0 ? unfocusedPrefixColor : this.unfocusedPrefixColor, (disabledPrefixColor > 16L ? 1 : (disabledPrefixColor == 16L ? 0 : -1)) != 0 ? disabledPrefixColor : this.disabledPrefixColor, (errorPrefixColor > 16L ? 1 : (errorPrefixColor == 16L ? 0 : -1)) != 0 ? errorPrefixColor : this.errorPrefixColor, (focusedSuffixColor > 16L ? 1 : (focusedSuffixColor == 16L ? 0 : -1)) != 0 ? focusedSuffixColor : this.focusedSuffixColor, (unfocusedSuffixColor > 16L ? 1 : (unfocusedSuffixColor == 16L ? 0 : -1)) != 0 ? unfocusedSuffixColor : this.unfocusedSuffixColor, (disabledSuffixColor > 16L ? 1 : (disabledSuffixColor == 16L ? 0 : -1)) != 0 ? disabledSuffixColor : this.disabledSuffixColor, errorSuffixColor != 16 ? errorSuffixColor : this.errorSuffixColor, null);
    }

    public final SelectionColors takeOrElse$material3(SelectionColors $this$takeOrElse, Function0<SelectionColors> function0) {
        return $this$takeOrElse == null ? function0.invoke() : $this$takeOrElse;
    }

    /* JADX INFO: renamed from: leadingIconColor-XeAY9LY$material3, reason: not valid java name */
    public final long m3034leadingIconColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledLeadingIconColor;
        }
        if (isError) {
            return this.errorLeadingIconColor;
        }
        return focused ? this.focusedLeadingIconColor : this.unfocusedLeadingIconColor;
    }

    /* JADX INFO: renamed from: trailingIconColor-XeAY9LY$material3, reason: not valid java name */
    public final long m3040trailingIconColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledTrailingIconColor;
        }
        if (isError) {
            return this.errorTrailingIconColor;
        }
        return focused ? this.focusedTrailingIconColor : this.unfocusedTrailingIconColor;
    }

    /* JADX INFO: renamed from: indicatorColor-XeAY9LY$material3, reason: not valid java name */
    public final long m3032indicatorColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledIndicatorColor;
        }
        if (isError) {
            return this.errorIndicatorColor;
        }
        return focused ? this.focusedIndicatorColor : this.unfocusedIndicatorColor;
    }

    /* JADX INFO: renamed from: containerColor-XeAY9LY$material3, reason: not valid java name */
    public final long m2987containerColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledContainerColor;
        }
        if (isError) {
            return this.errorContainerColor;
        }
        return focused ? this.focusedContainerColor : this.unfocusedContainerColor;
    }

    /* JADX INFO: renamed from: placeholderColor-XeAY9LY$material3, reason: not valid java name */
    public final long m3035placeholderColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledPlaceholderColor;
        }
        if (isError) {
            return this.errorPlaceholderColor;
        }
        return focused ? this.focusedPlaceholderColor : this.unfocusedPlaceholderColor;
    }

    /* JADX INFO: renamed from: labelColor-XeAY9LY$material3, reason: not valid java name */
    public final long m3033labelColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledLabelColor;
        }
        if (isError) {
            return this.errorLabelColor;
        }
        return focused ? this.focusedLabelColor : this.unfocusedLabelColor;
    }

    /* JADX INFO: renamed from: textColor-XeAY9LY$material3, reason: not valid java name */
    public final long m3039textColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledTextColor;
        }
        if (isError) {
            return this.errorTextColor;
        }
        return focused ? this.focusedTextColor : this.unfocusedTextColor;
    }

    /* JADX INFO: renamed from: supportingTextColor-XeAY9LY$material3, reason: not valid java name */
    public final long m3038supportingTextColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledSupportingTextColor;
        }
        if (isError) {
            return this.errorSupportingTextColor;
        }
        return focused ? this.focusedSupportingTextColor : this.unfocusedSupportingTextColor;
    }

    /* JADX INFO: renamed from: prefixColor-XeAY9LY$material3, reason: not valid java name */
    public final long m3036prefixColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledPrefixColor;
        }
        if (isError) {
            return this.errorPrefixColor;
        }
        return focused ? this.focusedPrefixColor : this.unfocusedPrefixColor;
    }

    /* JADX INFO: renamed from: suffixColor-XeAY9LY$material3, reason: not valid java name */
    public final long m3037suffixColorXeAY9LY$material3(boolean enabled, boolean isError, boolean focused) {
        if (!enabled) {
            return this.disabledSuffixColor;
        }
        if (isError) {
            return this.errorSuffixColor;
        }
        return focused ? this.focusedSuffixColor : this.unfocusedSuffixColor;
    }

    /* JADX INFO: renamed from: cursorColor-vNxB06k$material3, reason: not valid java name */
    public final long m2989cursorColorvNxB06k$material3(boolean isError) {
        return isError ? this.errorCursorColor : this.cursorColor;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof TextFieldColors) && Color.m5098equalsimpl0(this.focusedTextColor, ((TextFieldColors) other).focusedTextColor) && Color.m5098equalsimpl0(this.unfocusedTextColor, ((TextFieldColors) other).unfocusedTextColor) && Color.m5098equalsimpl0(this.disabledTextColor, ((TextFieldColors) other).disabledTextColor) && Color.m5098equalsimpl0(this.errorTextColor, ((TextFieldColors) other).errorTextColor) && Color.m5098equalsimpl0(this.focusedContainerColor, ((TextFieldColors) other).focusedContainerColor) && Color.m5098equalsimpl0(this.unfocusedContainerColor, ((TextFieldColors) other).unfocusedContainerColor) && Color.m5098equalsimpl0(this.disabledContainerColor, ((TextFieldColors) other).disabledContainerColor) && Color.m5098equalsimpl0(this.errorContainerColor, ((TextFieldColors) other).errorContainerColor) && Color.m5098equalsimpl0(this.cursorColor, ((TextFieldColors) other).cursorColor) && Color.m5098equalsimpl0(this.errorCursorColor, ((TextFieldColors) other).errorCursorColor) && Intrinsics.areEqual(this.textSelectionColors, ((TextFieldColors) other).textSelectionColors) && Color.m5098equalsimpl0(this.focusedIndicatorColor, ((TextFieldColors) other).focusedIndicatorColor) && Color.m5098equalsimpl0(this.unfocusedIndicatorColor, ((TextFieldColors) other).unfocusedIndicatorColor) && Color.m5098equalsimpl0(this.disabledIndicatorColor, ((TextFieldColors) other).disabledIndicatorColor) && Color.m5098equalsimpl0(this.errorIndicatorColor, ((TextFieldColors) other).errorIndicatorColor) && Color.m5098equalsimpl0(this.focusedLeadingIconColor, ((TextFieldColors) other).focusedLeadingIconColor) && Color.m5098equalsimpl0(this.unfocusedLeadingIconColor, ((TextFieldColors) other).unfocusedLeadingIconColor) && Color.m5098equalsimpl0(this.disabledLeadingIconColor, ((TextFieldColors) other).disabledLeadingIconColor) && Color.m5098equalsimpl0(this.errorLeadingIconColor, ((TextFieldColors) other).errorLeadingIconColor) && Color.m5098equalsimpl0(this.focusedTrailingIconColor, ((TextFieldColors) other).focusedTrailingIconColor) && Color.m5098equalsimpl0(this.unfocusedTrailingIconColor, ((TextFieldColors) other).unfocusedTrailingIconColor) && Color.m5098equalsimpl0(this.disabledTrailingIconColor, ((TextFieldColors) other).disabledTrailingIconColor) && Color.m5098equalsimpl0(this.errorTrailingIconColor, ((TextFieldColors) other).errorTrailingIconColor) && Color.m5098equalsimpl0(this.focusedLabelColor, ((TextFieldColors) other).focusedLabelColor) && Color.m5098equalsimpl0(this.unfocusedLabelColor, ((TextFieldColors) other).unfocusedLabelColor) && Color.m5098equalsimpl0(this.disabledLabelColor, ((TextFieldColors) other).disabledLabelColor) && Color.m5098equalsimpl0(this.errorLabelColor, ((TextFieldColors) other).errorLabelColor) && Color.m5098equalsimpl0(this.focusedPlaceholderColor, ((TextFieldColors) other).focusedPlaceholderColor) && Color.m5098equalsimpl0(this.unfocusedPlaceholderColor, ((TextFieldColors) other).unfocusedPlaceholderColor) && Color.m5098equalsimpl0(this.disabledPlaceholderColor, ((TextFieldColors) other).disabledPlaceholderColor) && Color.m5098equalsimpl0(this.errorPlaceholderColor, ((TextFieldColors) other).errorPlaceholderColor) && Color.m5098equalsimpl0(this.focusedSupportingTextColor, ((TextFieldColors) other).focusedSupportingTextColor) && Color.m5098equalsimpl0(this.unfocusedSupportingTextColor, ((TextFieldColors) other).unfocusedSupportingTextColor) && Color.m5098equalsimpl0(this.disabledSupportingTextColor, ((TextFieldColors) other).disabledSupportingTextColor) && Color.m5098equalsimpl0(this.errorSupportingTextColor, ((TextFieldColors) other).errorSupportingTextColor) && Color.m5098equalsimpl0(this.focusedPrefixColor, ((TextFieldColors) other).focusedPrefixColor) && Color.m5098equalsimpl0(this.unfocusedPrefixColor, ((TextFieldColors) other).unfocusedPrefixColor) && Color.m5098equalsimpl0(this.disabledPrefixColor, ((TextFieldColors) other).disabledPrefixColor) && Color.m5098equalsimpl0(this.errorPrefixColor, ((TextFieldColors) other).errorPrefixColor) && Color.m5098equalsimpl0(this.focusedSuffixColor, ((TextFieldColors) other).focusedSuffixColor) && Color.m5098equalsimpl0(this.unfocusedSuffixColor, ((TextFieldColors) other).unfocusedSuffixColor) && Color.m5098equalsimpl0(this.disabledSuffixColor, ((TextFieldColors) other).disabledSuffixColor) && Color.m5098equalsimpl0(this.errorSuffixColor, ((TextFieldColors) other).errorSuffixColor)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Color.m5104hashCodeimpl(this.focusedTextColor);
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((result * 31) + Color.m5104hashCodeimpl(this.unfocusedTextColor)) * 31) + Color.m5104hashCodeimpl(this.disabledTextColor)) * 31) + Color.m5104hashCodeimpl(this.errorTextColor)) * 31) + Color.m5104hashCodeimpl(this.focusedContainerColor)) * 31) + Color.m5104hashCodeimpl(this.unfocusedContainerColor)) * 31) + Color.m5104hashCodeimpl(this.disabledContainerColor)) * 31) + Color.m5104hashCodeimpl(this.errorContainerColor)) * 31) + Color.m5104hashCodeimpl(this.cursorColor)) * 31) + Color.m5104hashCodeimpl(this.errorCursorColor)) * 31) + this.textSelectionColors.hashCode()) * 31) + Color.m5104hashCodeimpl(this.focusedIndicatorColor)) * 31) + Color.m5104hashCodeimpl(this.unfocusedIndicatorColor)) * 31) + Color.m5104hashCodeimpl(this.disabledIndicatorColor)) * 31) + Color.m5104hashCodeimpl(this.errorIndicatorColor)) * 31) + Color.m5104hashCodeimpl(this.focusedLeadingIconColor)) * 31) + Color.m5104hashCodeimpl(this.unfocusedLeadingIconColor)) * 31) + Color.m5104hashCodeimpl(this.disabledLeadingIconColor)) * 31) + Color.m5104hashCodeimpl(this.errorLeadingIconColor)) * 31) + Color.m5104hashCodeimpl(this.focusedTrailingIconColor)) * 31) + Color.m5104hashCodeimpl(this.unfocusedTrailingIconColor)) * 31) + Color.m5104hashCodeimpl(this.disabledTrailingIconColor)) * 31) + Color.m5104hashCodeimpl(this.errorTrailingIconColor)) * 31) + Color.m5104hashCodeimpl(this.focusedLabelColor)) * 31) + Color.m5104hashCodeimpl(this.unfocusedLabelColor)) * 31) + Color.m5104hashCodeimpl(this.disabledLabelColor)) * 31) + Color.m5104hashCodeimpl(this.errorLabelColor)) * 31) + Color.m5104hashCodeimpl(this.focusedPlaceholderColor)) * 31) + Color.m5104hashCodeimpl(this.unfocusedPlaceholderColor)) * 31) + Color.m5104hashCodeimpl(this.disabledPlaceholderColor)) * 31) + Color.m5104hashCodeimpl(this.errorPlaceholderColor)) * 31) + Color.m5104hashCodeimpl(this.focusedSupportingTextColor)) * 31) + Color.m5104hashCodeimpl(this.unfocusedSupportingTextColor)) * 31) + Color.m5104hashCodeimpl(this.disabledSupportingTextColor)) * 31) + Color.m5104hashCodeimpl(this.errorSupportingTextColor)) * 31) + Color.m5104hashCodeimpl(this.focusedPrefixColor)) * 31) + Color.m5104hashCodeimpl(this.unfocusedPrefixColor)) * 31) + Color.m5104hashCodeimpl(this.disabledPrefixColor)) * 31) + Color.m5104hashCodeimpl(this.errorPrefixColor)) * 31) + Color.m5104hashCodeimpl(this.focusedSuffixColor)) * 31) + Color.m5104hashCodeimpl(this.unfocusedSuffixColor)) * 31) + Color.m5104hashCodeimpl(this.disabledSuffixColor)) * 31) + Color.m5104hashCodeimpl(this.errorSuffixColor);
    }
}
