package androidx.compose.ui.text.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Trace;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.compose.ui.text.android.selection.WordIterator;
import androidx.compose.ui.text.android.style.BaselineShiftSpan;
import androidx.compose.ui.text.android.style.IndentationFixSpan_androidKt;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextLayout.android.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001BÅ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0003\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\t\u0012\b\b\u0002\u0010\u0013\u001a\u00020\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\t\u0012\b\b\u0002\u0010\u0016\u001a\u00020\t\u0012\b\b\u0002\u0010\u0017\u001a\u00020\t\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010W\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tH\u0002J\u000e\u0010Y\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010[\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010\\\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010]\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010^\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010_\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010`\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\tJ\u000e\u0010a\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010b\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010c\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010d\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010e\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010f\u001a\u00020\u00102\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010g\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010h\u001a\u00020\t2\u0006\u0010Z\u001a\u00020\tJ\u000e\u0010i\u001a\u00020\t2\u0006\u0010j\u001a\u00020\tJ\u0016\u0010k\u001a\u00020\t2\u0006\u0010X\u001a\u00020\t2\u0006\u0010l\u001a\u00020\u0005J\u0018\u0010m\u001a\u00020\u00052\u0006\u0010n\u001a\u00020\t2\b\b\u0002\u0010o\u001a\u00020\u0010J\u0018\u0010p\u001a\u00020\u00052\u0006\u0010n\u001a\u00020\t2\b\b\u0002\u0010o\u001a\u00020\u0010J\u000e\u0010q\u001a\u00020\t2\u0006\u0010n\u001a\u00020\tJ\u000e\u0010r\u001a\u00020\u00102\u0006\u0010n\u001a\u00020\tJ\u000e\u0010s\u001a\u00020\t2\u0006\u0010X\u001a\u00020\tJ\u001e\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020\t2\u0006\u0010w\u001a\u00020\t2\u0006\u0010x\u001a\u00020yJ2\u0010z\u001a\u0004\u0018\u00010\u00192\u0006\u0010K\u001a\u00020{2\u0006\u0010|\u001a\u00020\t2\u0018\u0010}\u001a\u0014\u0012\u0004\u0012\u00020{\u0012\u0004\u0012\u00020{\u0012\u0004\u0012\u00020\u00100~J \u0010\u007f\u001a\u00020u2\u0006\u0010Z\u001a\u00020\t2\b\u0010\u0080\u0001\u001a\u00030\u0081\u0001H\u0000¢\u0006\u0003\b\u0082\u0001J,\u0010\u0083\u0001\u001a\u00020u2\u0007\u0010\u0084\u0001\u001a\u00020\t2\u0007\u0010\u0085\u0001\u001a\u00020\t2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0086\u0001\u001a\u00020\tJ\u000f\u0010\u0087\u0001\u001a\u00020{2\u0006\u0010n\u001a\u00020\tJ\u0011\u0010\u0088\u0001\u001a\u00020u2\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J\u000f\u0010\u008b\u0001\u001a\u00020\u0010H\u0000¢\u0006\u0003\b\u008c\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0011\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010&\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b*\u0010(R\u0011\u0010+\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010/\u001a\u00020.8F¢\u0006\u0006\u001a\u0004\b0\u00101R\u001c\u00102\u001a\u0002038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u00105\u001a\u0004\b6\u00107R\u0011\u00108\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001c\u0010;\u001a\u00020\t8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b<\u00105\u001a\u0004\b=\u0010:R\u001c\u0010>\u001a\u00020\t8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b?\u00105\u001a\u0004\b@\u0010:R\u000e\u0010A\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010G\u001a\n\u0012\u0004\u0012\u00020I\u0018\u00010HX\u0082\u0004¢\u0006\u0004\n\u0002\u0010JR\u000e\u0010K\u001a\u00020LX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010M\u001a\u0004\u0018\u00010NX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010O\u001a\u00020N8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u0011\u0010R\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0011\u0010U\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\bV\u0010:¨\u0006\u008d\u0001"}, d2 = {"Landroidx/compose/ui/text/android/TextLayout;", "", "charSequence", "", "width", "", "textPaint", "Landroid/text/TextPaint;", "alignment", "", "ellipsize", "Landroid/text/TextUtils$TruncateAt;", "textDirectionHeuristic", "lineSpacingMultiplier", "lineSpacingExtra", "includePadding", "", "fallbackLineSpacing", "maxLines", "breakStrategy", "lineBreakStyle", "lineBreakWordStyle", "hyphenationFrequency", "justificationMode", "leftIndents", "", "rightIndents", "layoutIntrinsics", "Landroidx/compose/ui/text/android/LayoutIntrinsics;", "<init>", "(Ljava/lang/CharSequence;FLandroid/text/TextPaint;ILandroid/text/TextUtils$TruncateAt;IFFZZIIIIII[I[ILandroidx/compose/ui/text/android/LayoutIntrinsics;)V", "getTextPaint", "()Landroid/text/TextPaint;", "getIncludePadding", "()Z", "getFallbackLineSpacing", "getLayoutIntrinsics", "()Landroidx/compose/ui/text/android/LayoutIntrinsics;", "maxIntrinsicWidth", "getMaxIntrinsicWidth", "()F", "minIntrinsicWidth", "getMinIntrinsicWidth", "didExceedMaxLines", "getDidExceedMaxLines", "backingWordIterator", "Landroidx/compose/ui/text/android/selection/WordIterator;", "wordIterator", "getWordIterator", "()Landroidx/compose/ui/text/android/selection/WordIterator;", "layout", "Landroid/text/Layout;", "getLayout$annotations", "()V", "getLayout", "()Landroid/text/Layout;", "lineCount", "getLineCount", "()I", "topPadding", "getTopPadding$ui_text$annotations", "getTopPadding$ui_text", "bottomPadding", "getBottomPadding$ui_text$annotations", "getBottomPadding$ui_text", "leftPadding", "rightPadding", "isBoringLayout", "lastLineFontMetrics", "Landroid/graphics/Paint$FontMetricsInt;", "lastLineExtra", "lineHeightSpans", "", "Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "[Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "rect", "Landroid/graphics/Rect;", "backingLayoutHelper", "Landroidx/compose/ui/text/android/LayoutHelper;", "layoutHelper", "getLayoutHelper", "()Landroidx/compose/ui/text/android/LayoutHelper;", "text", "getText", "()Ljava/lang/CharSequence;", "height", "getHeight", "getHorizontalPadding", "line", "getLineLeft", "lineIndex", "getLineRight", "getLineTop", "getLineBottom", "getLineAscent", "getLineBaseline", "getLineDescent", "getLineHeight", "getLineWidth", "getLineStart", "getLineEnd", "getLineVisibleEnd", "isLineEllipsized", "getLineEllipsisOffset", "getLineEllipsisCount", "getLineForVertical", "vertical", "getOffsetForHorizontal", "horizontal", "getPrimaryHorizontal", TypedValues.CycleType.S_WAVE_OFFSET, "upstream", "getSecondaryHorizontal", "getLineForOffset", "isRtlCharAt", "getParagraphDirection", "getSelectionPath", "", TtmlNode.START, TtmlNode.END, "dest", "Landroid/graphics/Path;", "getRangeForRect", "Landroid/graphics/RectF;", "granularity", "inclusionStrategy", "Lkotlin/Function2;", "fillLineHorizontalBounds", "array", "", "fillLineHorizontalBounds$ui_text", "fillBoundingBoxes", "startOffset", "endOffset", "arrayStart", "getBoundingBox", "paint", "canvas", "Landroid/graphics/Canvas;", "isFallbackLinespacingApplied", "isFallbackLinespacingApplied$ui_text", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextLayout {
    public static final int $stable = 8;
    private LayoutHelper backingLayoutHelper;
    private WordIterator backingWordIterator;
    private final int bottomPadding;
    private final boolean didExceedMaxLines;
    private final TextUtils.TruncateAt ellipsize;
    private final boolean fallbackLineSpacing;
    private final boolean includePadding;
    private final boolean isBoringLayout;
    private final int lastLineExtra;
    private final Paint.FontMetricsInt lastLineFontMetrics;
    private final Layout layout;
    private final LayoutIntrinsics layoutIntrinsics;
    private final float leftPadding;
    private final int lineCount;
    private final LineHeightStyleSpan[] lineHeightSpans;
    private final Rect rect;
    private final float rightPadding;
    private final TextPaint textPaint;
    private final int topPadding;

    public static /* synthetic */ void getBottomPadding$ui_text$annotations() {
    }

    public static /* synthetic */ void getLayout$annotations() {
    }

    public static /* synthetic */ void getTopPadding$ui_text$annotations() {
    }

    /* JADX WARN: Code duplicated, block: B:45:0x013e  */
    /* JADX WARN: Code duplicated, block: B:46:0x0140  */
    /* JADX WARN: Code duplicated, block: B:51:0x0156  */
    /* JADX WARN: Code duplicated, block: B:63:0x0189  */
    /* JADX WARN: Code duplicated, block: B:80:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:82:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:83:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:85:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:86:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:90:0x01df  */
    /* JADX WARN: Code duplicated, block: B:91:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:94:0x020f  */
    /* JADX WARN: Code duplicated, block: B:95:0x0218  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.ui.text.android.TextLayout, java.lang.Object] */
    public TextLayout(CharSequence charSequence, float f, TextPaint textPaint, int i, TextUtils.TruncateAt truncateAt, int i2, float f2, float f3, boolean z, boolean z2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr, int[] iArr2, LayoutIntrinsics layoutIntrinsics) throws Throwable {
        BoringLayout boringLayoutCreate;
        int i9;
        TextDirectionHeuristic textDirectionHeuristic;
        TextLayout textLayout;
        int i10;
        boolean z3;
        LineHeightStyleSpan[] lineHeightStyleSpanArr;
        boolean z4;
        LineHeightStyleSpan[] lineHeightStyleSpanArr2;
        long verticalPaddings;
        int iM7380getTopPaddingimpl;
        int iM7379getBottomPaddingimpl;
        long jVerticalPaddings;
        LineHeightStyleSpan[] lineHeightStyleSpanArr3;
        long lineHeightPaddings;
        Paint.FontMetricsInt lastLineMetrics;
        int lineHeight;
        LineHeightStyleSpan lineHeightStyleSpan;
        LineHeightStyleSpan lineHeightStyleSpan2;
        StaticLayoutFactory staticLayoutFactory;
        TextPaint textPaint2;
        int length;
        TextUtils.TruncateAt truncateAt2;
        TextLayout textLayout2;
        ?? obj = new Object();
        obj.textPaint = textPaint;
        obj.ellipsize = truncateAt;
        obj.includePadding = z;
        obj.fallbackLineSpacing = z2;
        obj.layoutIntrinsics = layoutIntrinsics;
        obj.rect = new Rect();
        int length2 = charSequence.length();
        TextDirectionHeuristic textDirectionHeuristic2 = TextLayout_androidKt.getTextDirectionHeuristic(i2);
        Layout.Alignment alignment = TextAlignmentAdapter.INSTANCE.get(i);
        boolean z5 = true;
        boolean z6 = (charSequence instanceof Spanned) && ((Spanned) charSequence).nextSpanTransition(-1, length2, BaselineShiftSpan.class) < length2;
        boolean z7 = z6;
        Trace.beginSection("TextLayout:initLayout");
        try {
            BoringLayout.Metrics boringMetrics = obj.layoutIntrinsics.getBoringMetrics();
            int iCeil = (int) Math.ceil(f);
            try {
                try {
                    try {
                        try {
                            if (boringMetrics != null) {
                                try {
                                    if (obj.layoutIntrinsics.getMaxIntrinsicWidth() <= f && !z7) {
                                        obj.isBoringLayout = true;
                                        try {
                                            z5 = true;
                                            try {
                                                boringLayoutCreate = BoringLayoutFactory.INSTANCE.create(charSequence, obj.textPaint, iCeil, boringMetrics, alignment, obj.includePadding, obj.fallbackLineSpacing, obj.ellipsize, iCeil);
                                                i9 = i3;
                                                textDirectionHeuristic = textDirectionHeuristic2;
                                                textLayout = obj;
                                                textLayout.layout = boringLayoutCreate;
                                                Trace.endSection();
                                                textLayout.lineCount = Math.min(textLayout.layout.getLineCount(), i9);
                                                i10 = textLayout.lineCount - 1;
                                                if (textLayout.lineCount < i9) {
                                                    z3 = false;
                                                } else if (textLayout.layout.getEllipsisCount(i10) <= 0 || textLayout.layout.getLineEnd(i10) != charSequence.length()) {
                                                    z3 = z5;
                                                } else {
                                                    z3 = false;
                                                }
                                                textLayout.didExceedMaxLines = z3;
                                                textLayout.lineHeightSpans = TextLayout_androidKt.getLineHeightSpans(textLayout);
                                                lineHeightStyleSpanArr = textLayout.lineHeightSpans;
                                                if (lineHeightStyleSpanArr != null || (lineHeightStyleSpan2 = (LineHeightStyleSpan) ArraysKt.firstOrNull(lineHeightStyleSpanArr)) == null) {
                                                    z4 = false;
                                                } else {
                                                    z4 = (lineHeightStyleSpan2.getTrimFirstLineTop() && LineHeightStyle.Mode.m7731equalsimpl0(lineHeightStyleSpan2.getMode(), LineHeightStyle.Mode.INSTANCE.m7737getTightlzQqcRY())) ? z5 : false;
                                                }
                                                lineHeightStyleSpanArr2 = textLayout.lineHeightSpans;
                                                if (lineHeightStyleSpanArr2 != null || (lineHeightStyleSpan = (LineHeightStyleSpan) ArraysKt.firstOrNull(lineHeightStyleSpanArr2)) == null || !lineHeightStyleSpan.getTrimLastLineBottom() || !LineHeightStyle.Mode.m7731equalsimpl0(lineHeightStyleSpan.getMode(), LineHeightStyle.Mode.INSTANCE.m7737getTightlzQqcRY())) {
                                                    z5 = false;
                                                }
                                                if (z4 || !z5) {
                                                    verticalPaddings = TextLayout_androidKt.getVerticalPaddings(textLayout);
                                                    if (z4) {
                                                        iM7380getTopPaddingimpl = 0;
                                                    } else {
                                                        iM7380getTopPaddingimpl = VerticalPaddings.m7380getTopPaddingimpl(verticalPaddings);
                                                    }
                                                    if (z5) {
                                                        iM7379getBottomPaddingimpl = 0;
                                                    } else {
                                                        iM7379getBottomPaddingimpl = VerticalPaddings.m7379getBottomPaddingimpl(verticalPaddings);
                                                    }
                                                    jVerticalPaddings = TextLayout_androidKt.VerticalPaddings(iM7380getTopPaddingimpl, iM7379getBottomPaddingimpl);
                                                } else {
                                                    jVerticalPaddings = TextLayout_androidKt.ZeroVerticalPadding;
                                                }
                                                lineHeightStyleSpanArr3 = textLayout.lineHeightSpans;
                                                if (lineHeightStyleSpanArr3 != null) {
                                                    lineHeightPaddings = TextLayout_androidKt.getLineHeightPaddings(lineHeightStyleSpanArr3);
                                                } else {
                                                    lineHeightPaddings = TextLayout_androidKt.ZeroVerticalPadding;
                                                }
                                                textLayout.topPadding = Math.max(VerticalPaddings.m7380getTopPaddingimpl(jVerticalPaddings), VerticalPaddings.m7380getTopPaddingimpl(lineHeightPaddings));
                                                textLayout.bottomPadding = Math.max(VerticalPaddings.m7379getBottomPaddingimpl(jVerticalPaddings), VerticalPaddings.m7379getBottomPaddingimpl(lineHeightPaddings));
                                                lastLineMetrics = TextLayout_androidKt.getLastLineMetrics(textLayout, textLayout.textPaint, textDirectionHeuristic, textLayout.lineHeightSpans);
                                                if (lastLineMetrics != null) {
                                                    lineHeight = lastLineMetrics.bottom - ((int) textLayout.getLineHeight(i10));
                                                } else {
                                                    lineHeight = 0;
                                                }
                                                textLayout.lastLineExtra = lineHeight;
                                                textLayout.lastLineFontMetrics = lastLineMetrics;
                                                textLayout.leftPadding = IndentationFixSpan_androidKt.getEllipsizedLeftPadding$default(textLayout.layout, i10, null, 2, null);
                                                textLayout.rightPadding = IndentationFixSpan_androidKt.getEllipsizedRightPadding$default(textLayout.layout, i10, null, 2, null);
                                                return;
                                            } catch (Throwable th) {
                                                th = th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                                Trace.endSection();
                                throw th;
                            }
                            boringLayoutCreate = staticLayoutFactory.create(charSequence, textPaint2, iCeil, 0, length, textDirectionHeuristic, alignment, i9, truncateAt2, (int) Math.ceil(f), f2, f3, i8, textLayout2.includePadding, textLayout2.fallbackLineSpacing, i4, i5, i6, i7, iArr, iArr2);
                            textLayout = textLayout2;
                            textLayout.layout = boringLayoutCreate;
                            Trace.endSection();
                            textLayout.lineCount = Math.min(textLayout.layout.getLineCount(), i9);
                            i10 = textLayout.lineCount - 1;
                            if (textLayout.lineCount < i9) {
                                z3 = false;
                            } else if (textLayout.layout.getEllipsisCount(i10) <= 0) {
                                z3 = z5;
                            } else {
                                z3 = z5;
                            }
                            textLayout.didExceedMaxLines = z3;
                            textLayout.lineHeightSpans = TextLayout_androidKt.getLineHeightSpans(textLayout);
                            lineHeightStyleSpanArr = textLayout.lineHeightSpans;
                            if (lineHeightStyleSpanArr != null) {
                                z4 = false;
                            } else {
                                z4 = false;
                            }
                            lineHeightStyleSpanArr2 = textLayout.lineHeightSpans;
                            z5 = lineHeightStyleSpanArr2 != null ? false : false;
                            if (z4) {
                                verticalPaddings = TextLayout_androidKt.getVerticalPaddings(textLayout);
                                if (z4) {
                                    iM7380getTopPaddingimpl = 0;
                                } else {
                                    iM7380getTopPaddingimpl = VerticalPaddings.m7380getTopPaddingimpl(verticalPaddings);
                                }
                                if (z5) {
                                    iM7379getBottomPaddingimpl = 0;
                                } else {
                                    iM7379getBottomPaddingimpl = VerticalPaddings.m7379getBottomPaddingimpl(verticalPaddings);
                                }
                                jVerticalPaddings = TextLayout_androidKt.VerticalPaddings(iM7380getTopPaddingimpl, iM7379getBottomPaddingimpl);
                            } else {
                                verticalPaddings = TextLayout_androidKt.getVerticalPaddings(textLayout);
                                if (z4) {
                                    iM7380getTopPaddingimpl = 0;
                                } else {
                                    iM7380getTopPaddingimpl = VerticalPaddings.m7380getTopPaddingimpl(verticalPaddings);
                                }
                                if (z5) {
                                    iM7379getBottomPaddingimpl = 0;
                                } else {
                                    iM7379getBottomPaddingimpl = VerticalPaddings.m7379getBottomPaddingimpl(verticalPaddings);
                                }
                                jVerticalPaddings = TextLayout_androidKt.VerticalPaddings(iM7380getTopPaddingimpl, iM7379getBottomPaddingimpl);
                            }
                            lineHeightStyleSpanArr3 = textLayout.lineHeightSpans;
                            if (lineHeightStyleSpanArr3 != null) {
                                lineHeightPaddings = TextLayout_androidKt.getLineHeightPaddings(lineHeightStyleSpanArr3);
                            } else {
                                lineHeightPaddings = TextLayout_androidKt.ZeroVerticalPadding;
                            }
                            textLayout.topPadding = Math.max(VerticalPaddings.m7380getTopPaddingimpl(jVerticalPaddings), VerticalPaddings.m7380getTopPaddingimpl(lineHeightPaddings));
                            textLayout.bottomPadding = Math.max(VerticalPaddings.m7379getBottomPaddingimpl(jVerticalPaddings), VerticalPaddings.m7379getBottomPaddingimpl(lineHeightPaddings));
                            lastLineMetrics = TextLayout_androidKt.getLastLineMetrics(textLayout, textLayout.textPaint, textDirectionHeuristic, textLayout.lineHeightSpans);
                            if (lastLineMetrics != null) {
                                lineHeight = lastLineMetrics.bottom - ((int) textLayout.getLineHeight(i10));
                            } else {
                                lineHeight = 0;
                            }
                            textLayout.lastLineExtra = lineHeight;
                            textLayout.lastLineFontMetrics = lastLineMetrics;
                            textLayout.leftPadding = IndentationFixSpan_androidKt.getEllipsizedLeftPadding$default(textLayout.layout, i10, null, 2, null);
                            textLayout.rightPadding = IndentationFixSpan_androidKt.getEllipsizedRightPadding$default(textLayout.layout, i10, null, 2, null);
                            return;
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                    textLayout2 = this;
                    i9 = i3;
                    textDirectionHeuristic = textDirectionHeuristic2;
                } catch (Throwable th6) {
                    th = th6;
                }
                obj.isBoringLayout = 0;
                staticLayoutFactory = StaticLayoutFactory.INSTANCE;
                textPaint2 = obj.textPaint;
                length = charSequence.length();
                truncateAt2 = obj.ellipsize;
            } catch (Throwable th7) {
                th = th7;
            }
        } catch (Throwable th8) {
            th = th8;
        }
    }

    public /* synthetic */ TextLayout(CharSequence charSequence, float f, TextPaint textPaint, int i, TextUtils.TruncateAt truncateAt, int i2, float f2, float f3, boolean z, boolean z2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr, int[] iArr2, LayoutIntrinsics layoutIntrinsics, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        CharSequence charSequence2;
        TextPaint textPaint2;
        LayoutIntrinsics layoutIntrinsics2;
        int i10 = (i9 & 8) != 0 ? 0 : i;
        TextUtils.TruncateAt truncateAt2 = (i9 & 16) != 0 ? null : truncateAt;
        int i11 = (i9 & 32) != 0 ? 2 : i2;
        float f4 = (i9 & 64) != 0 ? 1.0f : f2;
        float f5 = (i9 & 128) != 0 ? 0.0f : f3;
        boolean z3 = (i9 & 256) != 0 ? false : z;
        boolean z4 = (i9 & 512) != 0 ? true : z2;
        int i12 = (i9 & 1024) != 0 ? Integer.MAX_VALUE : i3;
        int i13 = (i9 & 2048) != 0 ? 0 : i4;
        int i14 = (i9 & 4096) != 0 ? 0 : i5;
        int i15 = (i9 & 8192) != 0 ? 0 : i6;
        int i16 = (i9 & 16384) != 0 ? 0 : i7;
        int i17 = (32768 & i9) != 0 ? 0 : i8;
        int[] iArr3 = (65536 & i9) != 0 ? null : iArr;
        int[] iArr4 = (131072 & i9) != 0 ? null : iArr2;
        if ((i9 & 262144) != 0) {
            charSequence2 = charSequence;
            textPaint2 = textPaint;
            layoutIntrinsics2 = new LayoutIntrinsics(charSequence2, textPaint2, i11);
        } else {
            charSequence2 = charSequence;
            textPaint2 = textPaint;
            layoutIntrinsics2 = layoutIntrinsics;
        }
        this(charSequence2, f, textPaint2, i10, truncateAt2, i11, f4, f5, z3, z4, i12, i13, i14, i15, i16, i17, iArr3, iArr4, layoutIntrinsics2);
    }

    public final TextPaint getTextPaint() {
        return this.textPaint;
    }

    public final boolean getIncludePadding() {
        return this.includePadding;
    }

    public final boolean getFallbackLineSpacing() {
        return this.fallbackLineSpacing;
    }

    public final LayoutIntrinsics getLayoutIntrinsics() {
        return this.layoutIntrinsics;
    }

    public final float getMaxIntrinsicWidth() {
        return this.layoutIntrinsics.getMaxIntrinsicWidth();
    }

    public final float getMinIntrinsicWidth() {
        return this.layoutIntrinsics.getMinIntrinsicWidth();
    }

    public final boolean getDidExceedMaxLines() {
        return this.didExceedMaxLines;
    }

    public final WordIterator getWordIterator() {
        WordIterator finalWordIterator = this.backingWordIterator;
        if (finalWordIterator != null) {
            return finalWordIterator;
        }
        WordIterator it = new WordIterator(this.layout.getText(), 0, this.layout.getText().length(), this.textPaint.getTextLocale());
        this.backingWordIterator = it;
        return it;
    }

    public final Layout getLayout() {
        return this.layout;
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    /* JADX INFO: renamed from: getTopPadding$ui_text, reason: from getter */
    public final int getTopPadding() {
        return this.topPadding;
    }

    /* JADX INFO: renamed from: getBottomPadding$ui_text, reason: from getter */
    public final int getBottomPadding() {
        return this.bottomPadding;
    }

    private final LayoutHelper getLayoutHelper() {
        if (this.backingLayoutHelper == null) {
            LayoutHelper it = new LayoutHelper(this.layout);
            this.backingLayoutHelper = it;
            return it;
        }
        LayoutHelper layoutHelper = this.backingLayoutHelper;
        Intrinsics.checkNotNull(layoutHelper);
        return layoutHelper;
    }

    public final CharSequence getText() {
        return this.layout.getText();
    }

    public final int getHeight() {
        return (this.didExceedMaxLines ? this.layout.getLineBottom(this.lineCount - 1) : this.layout.getHeight()) + this.topPadding + this.bottomPadding + this.lastLineExtra;
    }

    private final float getHorizontalPadding(int line) {
        if (line == this.lineCount - 1) {
            return this.leftPadding + this.rightPadding;
        }
        return 0.0f;
    }

    public final float getLineLeft(int lineIndex) {
        return this.layout.getLineLeft(lineIndex) + (lineIndex == this.lineCount + (-1) ? this.leftPadding : 0.0f);
    }

    public final float getLineRight(int lineIndex) {
        return this.layout.getLineRight(lineIndex) + (lineIndex == this.lineCount + (-1) ? this.rightPadding : 0.0f);
    }

    public final float getLineTop(int line) {
        float top = this.layout.getLineTop(line);
        return (line == 0 ? 0 : this.topPadding) + top;
    }

    public final float getLineBottom(int line) {
        if (line == this.lineCount - 1 && this.lastLineFontMetrics != null) {
            return this.layout.getLineBottom(line - 1) + this.lastLineFontMetrics.bottom;
        }
        return this.topPadding + this.layout.getLineBottom(line) + (line == this.lineCount + (-1) ? this.bottomPadding : 0);
    }

    public final float getLineAscent(int line) {
        if (line == this.lineCount - 1 && this.lastLineFontMetrics != null) {
            return this.lastLineFontMetrics.ascent;
        }
        return this.layout.getLineAscent(line);
    }

    public final float getLineBaseline(int line) {
        float lineBaseline;
        float f = this.topPadding;
        if (line == this.lineCount - 1 && this.lastLineFontMetrics != null) {
            lineBaseline = getLineTop(line) - this.lastLineFontMetrics.ascent;
        } else {
            lineBaseline = this.layout.getLineBaseline(line);
        }
        return f + lineBaseline;
    }

    public final float getLineDescent(int line) {
        if (line == this.lineCount - 1 && this.lastLineFontMetrics != null) {
            return this.lastLineFontMetrics.descent;
        }
        return this.layout.getLineDescent(line);
    }

    public final float getLineHeight(int lineIndex) {
        return getLineBottom(lineIndex) - getLineTop(lineIndex);
    }

    public final float getLineWidth(int lineIndex) {
        return this.layout.getLineWidth(lineIndex);
    }

    public final int getLineStart(int lineIndex) {
        return this.layout.getLineStart(lineIndex);
    }

    public final int getLineEnd(int lineIndex) {
        if (TextLayout_androidKt.isLineEllipsized(this.layout, lineIndex) && this.ellipsize == TextUtils.TruncateAt.END) {
            return this.layout.getText().length();
        }
        return this.layout.getLineEnd(lineIndex);
    }

    public final int getLineVisibleEnd(int lineIndex) {
        if (TextLayout_androidKt.isLineEllipsized(this.layout, lineIndex) && this.ellipsize == TextUtils.TruncateAt.END) {
            return this.layout.getLineStart(lineIndex) + this.layout.getEllipsisStart(lineIndex);
        }
        return getLayoutHelper().getLineVisibleEnd(lineIndex);
    }

    public final boolean isLineEllipsized(int lineIndex) {
        return TextLayout_androidKt.isLineEllipsized(this.layout, lineIndex);
    }

    public final int getLineEllipsisOffset(int lineIndex) {
        return this.layout.getEllipsisStart(lineIndex);
    }

    public final int getLineEllipsisCount(int lineIndex) {
        return this.layout.getEllipsisCount(lineIndex);
    }

    public final int getLineForVertical(int vertical) {
        return this.layout.getLineForVertical(vertical - this.topPadding);
    }

    public final int getOffsetForHorizontal(int line, float horizontal) {
        return this.layout.getOffsetForHorizontal(line, ((-1.0f) * getHorizontalPadding(line)) + horizontal);
    }

    public static /* synthetic */ float getPrimaryHorizontal$default(TextLayout textLayout, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return textLayout.getPrimaryHorizontal(i, z);
    }

    public final float getPrimaryHorizontal(int offset, boolean upstream) {
        return getLayoutHelper().getHorizontalPosition(offset, true, upstream) + getHorizontalPadding(getLineForOffset(offset));
    }

    public static /* synthetic */ float getSecondaryHorizontal$default(TextLayout textLayout, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return textLayout.getSecondaryHorizontal(i, z);
    }

    public final float getSecondaryHorizontal(int offset, boolean upstream) {
        return getLayoutHelper().getHorizontalPosition(offset, false, upstream) + getHorizontalPadding(getLineForOffset(offset));
    }

    public final int getLineForOffset(int offset) {
        return this.layout.getLineForOffset(offset);
    }

    public final boolean isRtlCharAt(int offset) {
        return this.layout.isRtlCharAt(offset);
    }

    public final int getParagraphDirection(int line) {
        return this.layout.getParagraphDirection(line);
    }

    public final void getSelectionPath(int start, int end, Path dest) {
        this.layout.getSelectionPath(start, end, dest);
        if (this.topPadding != 0 && !dest.isEmpty()) {
            dest.offset(0.0f, this.topPadding);
        }
    }

    public final int[] getRangeForRect(RectF rect, int granularity, Function2<? super RectF, ? super RectF, Boolean> inclusionStrategy) {
        if (Build.VERSION.SDK_INT >= 34) {
            return AndroidLayoutApi34.INSTANCE.getRangeForRect$ui_text(this, rect, granularity, inclusionStrategy);
        }
        return TextLayoutGetRangeForRectExtensions_androidKt.getRangeForRect(this, this.layout, getLayoutHelper(), rect, granularity, inclusionStrategy);
    }

    public final void fillLineHorizontalBounds$ui_text(int lineIndex, float[] array) {
        float left;
        float right;
        int lineStartOffset = getLineStart(lineIndex);
        int lineEndOffset = getLineEnd(lineIndex);
        int range = lineEndOffset - lineStartOffset;
        int minArraySize = range * 2;
        boolean value$iv = array.length >= minArraySize;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("array.size - arrayStart must be greater or equal than (endOffset - startOffset) * 2");
        }
        HorizontalPositionCache cache = new HorizontalPositionCache(this);
        boolean isLtrLine = getParagraphDirection(lineIndex) == 1;
        int arrayOffset = 0;
        for (int offset = lineStartOffset; offset < lineEndOffset; offset++) {
            boolean isRtlChar = isRtlCharAt(offset);
            if (isLtrLine && !isRtlChar) {
                left = cache.getPrimaryDownstream(offset);
                right = cache.getPrimaryUpstream(offset + 1);
            } else if (isLtrLine && isRtlChar) {
                right = cache.getSecondaryDownstream(offset);
                left = cache.getSecondaryUpstream(offset + 1);
            } else if (isRtlChar) {
                right = cache.getPrimaryDownstream(offset);
                left = cache.getPrimaryUpstream(offset + 1);
            } else {
                left = cache.getSecondaryDownstream(offset);
                right = cache.getSecondaryUpstream(offset + 1);
            }
            array[arrayOffset] = left;
            array[arrayOffset + 1] = right;
            arrayOffset += 2;
        }
    }

    public final void fillBoundingBoxes(int startOffset, int endOffset, float[] array, int arrayStart) {
        float left;
        float right;
        TextLayout textLayout = this;
        int i = startOffset;
        int textLength = textLayout.getText().length();
        int i2 = 1;
        boolean value$iv = i >= 0;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("startOffset must be > 0");
        }
        boolean value$iv2 = i < textLength;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("startOffset must be less than text length");
        }
        boolean value$iv3 = endOffset > i;
        if (!value$iv3) {
            InlineClassHelperKt.throwIllegalArgumentException("endOffset must be greater than startOffset");
        }
        boolean value$iv4 = endOffset <= textLength;
        if (!value$iv4) {
            InlineClassHelperKt.throwIllegalArgumentException("endOffset must be smaller or equal to text length");
        }
        int range = endOffset - i;
        int minArraySize = range * 4;
        boolean value$iv5 = array.length - arrayStart >= minArraySize;
        if (!value$iv5) {
            InlineClassHelperKt.throwIllegalArgumentException("array.size - arrayStart must be greater or equal than (endOffset - startOffset) * 4");
        }
        int firstLine = getLineForOffset(startOffset);
        int lastLine = textLayout.getLineForOffset(endOffset - 1);
        HorizontalPositionCache cache = new HorizontalPositionCache(textLayout);
        int arrayOffset = arrayStart;
        int line = firstLine;
        if (line > lastLine) {
            return;
        }
        while (true) {
            int lineStartOffset = textLayout.getLineStart(line);
            int lineEndOffset = textLayout.getLineEnd(line);
            int actualStartOffset = Math.max(i, lineStartOffset);
            int actualEndOffset = Math.min(endOffset, lineEndOffset);
            float lineTop = textLayout.getLineTop(line);
            float lineBottom = textLayout.getLineBottom(line);
            int i3 = textLayout.getParagraphDirection(line) == i2 ? i2 : 0;
            int i4 = i3 == 0 ? i2 : 0;
            int offset = actualStartOffset;
            while (offset < actualEndOffset) {
                boolean isRtlChar = textLayout.isRtlCharAt(offset);
                if (i3 != 0 && !isRtlChar) {
                    left = cache.getPrimaryDownstream(offset);
                    right = cache.getPrimaryUpstream(offset + 1);
                } else if (i3 != 0 && isRtlChar) {
                    float right2 = cache.getSecondaryDownstream(offset);
                    left = cache.getSecondaryUpstream(offset + 1);
                    right = right2;
                } else if (i4 != 0 && isRtlChar) {
                    float right3 = cache.getPrimaryDownstream(offset);
                    left = cache.getPrimaryUpstream(offset + 1);
                    right = right3;
                } else {
                    left = cache.getSecondaryDownstream(offset);
                    right = cache.getSecondaryUpstream(offset + 1);
                }
                array[arrayOffset] = left;
                array[arrayOffset + 1] = lineTop;
                array[arrayOffset + 2] = right;
                array[arrayOffset + 3] = lineBottom;
                arrayOffset += 4;
                offset++;
                textLayout = this;
            }
            if (line == lastLine) {
                return;
            }
            line++;
            i2 = 1;
            textLayout = this;
            i = startOffset;
        }
    }

    public final RectF getBoundingBox(int offset) {
        float left;
        float right;
        int line = getLineForOffset(offset);
        float lineTop = getLineTop(line);
        float lineBottom = getLineBottom(line);
        boolean isLtrLine = getParagraphDirection(line) == 1;
        boolean isRtlChar = this.layout.isRtlCharAt(offset);
        if (isLtrLine && !isRtlChar) {
            left = getPrimaryHorizontal(offset, false);
            right = getPrimaryHorizontal(offset + 1, true);
        } else if (isLtrLine && isRtlChar) {
            float right2 = getSecondaryHorizontal(offset, false);
            right = right2;
            left = getSecondaryHorizontal(offset + 1, true);
        } else if (isRtlChar) {
            float right3 = getPrimaryHorizontal(offset, false);
            right = right3;
            left = getPrimaryHorizontal(offset + 1, true);
        } else {
            left = getSecondaryHorizontal(offset, false);
            right = getSecondaryHorizontal(offset + 1, true);
        }
        return new RectF(left, lineTop, right, lineBottom);
    }

    public final void paint(Canvas canvas) {
        if (!canvas.getClipBounds(this.rect)) {
            return;
        }
        if (this.topPadding != 0) {
            canvas.translate(0.0f, this.topPadding);
        }
        ThreadLocal<TextAndroidCanvas> sharedTextAndroidCanvas = TextLayout_androidKt.getSharedTextAndroidCanvas();
        TextAndroidCanvas textAndroidCanvas = sharedTextAndroidCanvas.get();
        if (textAndroidCanvas == null) {
            TextAndroidCanvas textAndroidCanvas2 = new TextAndroidCanvas();
            sharedTextAndroidCanvas.set(textAndroidCanvas2);
            textAndroidCanvas = textAndroidCanvas2;
        }
        TextAndroidCanvas threadSharedTextAndroidCanvas = textAndroidCanvas;
        threadSharedTextAndroidCanvas.set_nativeCanvas$ui_text(canvas);
        try {
            TextAndroidCanvas clipFixedCanvas = threadSharedTextAndroidCanvas;
            this.layout.draw(clipFixedCanvas);
            threadSharedTextAndroidCanvas.set_nativeCanvas$ui_text(null);
            if (this.topPadding != 0) {
                canvas.translate(0.0f, (-1.0f) * this.topPadding);
            }
        } catch (Throwable th) {
            threadSharedTextAndroidCanvas.set_nativeCanvas$ui_text(null);
            throw th;
        }
    }

    public final boolean isFallbackLinespacingApplied$ui_text() {
        if (this.isBoringLayout) {
            BoringLayoutFactory boringLayoutFactory = BoringLayoutFactory.INSTANCE;
            Layout layout = this.layout;
            Intrinsics.checkNotNull(layout, "null cannot be cast to non-null type android.text.BoringLayout");
            return boringLayoutFactory.isFallbackLineSpacingEnabled((BoringLayout) layout);
        }
        StaticLayoutFactory staticLayoutFactory = StaticLayoutFactory.INSTANCE;
        Layout layout2 = this.layout;
        Intrinsics.checkNotNull(layout2, "null cannot be cast to non-null type android.text.StaticLayout");
        return staticLayoutFactory.isFallbackLineSpacingEnabled((StaticLayout) layout2, this.fallbackLineSpacing);
    }
}
