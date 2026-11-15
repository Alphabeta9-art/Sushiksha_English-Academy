package androidx.compose.ui.text;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import androidx.compose.ui.text.android.SpannedExtensions_androidKt;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.android.style.IndentationFixSpan;
import androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidParagraph.android.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0017\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\u0005\u001a\u0017\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u0005\u001a\u0017\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0005\u001a\u0017\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0015\u0010\u0005\u001a\u0014\u0010\u0016\u001a\u00020\u0001*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001aH\u0002\u001a\f\u0010\u001e\u001a\u00020\u001f*\u00020\u001fH\u0002\u001a\u0013\u0010 \u001a\u00020\u0001*\u00020!H\u0002¢\u0006\u0004\b\"\u0010\u0005¨\u0006#"}, d2 = {"toLayoutAlign", "", "align", "Landroidx/compose/ui/text/style/TextAlign;", "toLayoutAlign-aXe7zB0", "(I)I", "toLayoutHyphenationFrequency", "hyphens", "Landroidx/compose/ui/text/style/Hyphens;", "toLayoutHyphenationFrequency--3fSNIE", "toLayoutBreakStrategy", "breakStrategy", "Landroidx/compose/ui/text/style/LineBreak$Strategy;", "toLayoutBreakStrategy-xImikfE", "toLayoutLineBreakStyle", "lineBreakStrictness", "Landroidx/compose/ui/text/style/LineBreak$Strictness;", "toLayoutLineBreakStyle-hpcqdu8", "toLayoutLineBreakWordStyle", "lineBreakWordStyle", "Landroidx/compose/ui/text/style/LineBreak$WordBreak;", "toLayoutLineBreakWordStyle-wPN0Rpw", "numberOfLinesThatFitMaxHeight", "Landroidx/compose/ui/text/android/TextLayout;", "maxHeight", "shouldAttachIndentationFixSpan", "", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "ellipsis", "attachIndentationFixSpan", "", "toLayoutTextGranularity", "Landroidx/compose/ui/text/TextGranularity;", "toLayoutTextGranularity-duNsdkg", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidParagraph_androidKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutAlign-aXe7zB0, reason: not valid java name */
    public static final int m7157toLayoutAlignaXe7zB0(int i) {
        if (TextAlign.m7754equalsimpl0(i, TextAlign.INSTANCE.m7761getLefte0LSkKk())) {
            return 3;
        }
        if (TextAlign.m7754equalsimpl0(i, TextAlign.INSTANCE.m7762getRighte0LSkKk())) {
            return 4;
        }
        if (TextAlign.m7754equalsimpl0(i, TextAlign.INSTANCE.m7758getCentere0LSkKk())) {
            return 2;
        }
        return (!TextAlign.m7754equalsimpl0(i, TextAlign.INSTANCE.m7763getStarte0LSkKk()) && TextAlign.m7754equalsimpl0(i, TextAlign.INSTANCE.m7759getEnde0LSkKk())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutHyphenationFrequency--3fSNIE, reason: not valid java name */
    public static final int m7159toLayoutHyphenationFrequency3fSNIE(int i) {
        if (!Hyphens.m7646equalsimpl0(i, Hyphens.INSTANCE.m7650getAutovmbZdU8())) {
            return Hyphens.m7646equalsimpl0(i, Hyphens.INSTANCE.m7651getNonevmbZdU8()) ? 0 : 0;
        }
        if (Build.VERSION.SDK_INT <= 32) {
            return 2;
        }
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutBreakStrategy-xImikfE, reason: not valid java name */
    public static final int m7158toLayoutBreakStrategyxImikfE(int i) {
        if (LineBreak.Strategy.m7680equalsimpl0(i, LineBreak.Strategy.INSTANCE.m7686getSimplefcGXIks())) {
            return 0;
        }
        if (LineBreak.Strategy.m7680equalsimpl0(i, LineBreak.Strategy.INSTANCE.m7685getHighQualityfcGXIks())) {
            return 1;
        }
        return LineBreak.Strategy.m7680equalsimpl0(i, LineBreak.Strategy.INSTANCE.m7684getBalancedfcGXIks()) ? 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutLineBreakStyle-hpcqdu8, reason: not valid java name */
    public static final int m7160toLayoutLineBreakStylehpcqdu8(int i) {
        if (LineBreak.Strictness.m7691equalsimpl0(i, LineBreak.Strictness.INSTANCE.m7695getDefaultusljTpc())) {
            return 0;
        }
        if (LineBreak.Strictness.m7691equalsimpl0(i, LineBreak.Strictness.INSTANCE.m7696getLooseusljTpc())) {
            return 1;
        }
        if (LineBreak.Strictness.m7691equalsimpl0(i, LineBreak.Strictness.INSTANCE.m7697getNormalusljTpc())) {
            return 2;
        }
        return LineBreak.Strictness.m7691equalsimpl0(i, LineBreak.Strictness.INSTANCE.m7698getStrictusljTpc()) ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutLineBreakWordStyle-wPN0Rpw, reason: not valid java name */
    public static final int m7161toLayoutLineBreakWordStylewPN0Rpw(int i) {
        return (!LineBreak.WordBreak.m7703equalsimpl0(i, LineBreak.WordBreak.INSTANCE.m7707getDefaultjp8hJ3c()) && LineBreak.WordBreak.m7703equalsimpl0(i, LineBreak.WordBreak.INSTANCE.m7708getPhrasejp8hJ3c())) ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int numberOfLinesThatFitMaxHeight(TextLayout $this$numberOfLinesThatFitMaxHeight, int maxHeight) {
        int lineCount = $this$numberOfLinesThatFitMaxHeight.getLineCount();
        for (int lineIndex = 0; lineIndex < lineCount; lineIndex++) {
            if ($this$numberOfLinesThatFitMaxHeight.getLineBottom(lineIndex) > maxHeight) {
                return lineIndex;
            }
        }
        int lineIndex2 = $this$numberOfLinesThatFitMaxHeight.getLineCount();
        return lineIndex2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldAttachIndentationFixSpan(TextStyle textStyle, boolean ellipsis) {
        return (!ellipsis || TextUnit.m8096equalsimpl0(textStyle.m7364getLetterSpacingXSAIIZE(), TextUnitKt.getSp(0)) || TextUnit.m8096equalsimpl0(textStyle.m7364getLetterSpacingXSAIIZE(), TextUnit.INSTANCE.m8110getUnspecifiedXSAIIZE()) || TextAlign.m7754equalsimpl0(textStyle.m7369getTextAligne0LSkKk(), TextAlign.INSTANCE.m7764getUnspecifiede0LSkKk()) || TextAlign.m7754equalsimpl0(textStyle.m7369getTextAligne0LSkKk(), TextAlign.INSTANCE.m7763getStarte0LSkKk()) || TextAlign.m7754equalsimpl0(textStyle.m7369getTextAligne0LSkKk(), TextAlign.INSTANCE.m7760getJustifye0LSkKk())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence attachIndentationFixSpan(CharSequence $this$attachIndentationFixSpan) {
        if ($this$attachIndentationFixSpan.length() == 0) {
            return $this$attachIndentationFixSpan;
        }
        SpannableString spannable = $this$attachIndentationFixSpan instanceof Spannable ? (Spannable) $this$attachIndentationFixSpan : null;
        if (spannable == null) {
            spannable = new SpannableString($this$attachIndentationFixSpan);
        }
        if (!SpannedExtensions_androidKt.hasSpan(spannable, IndentationFixSpan.class)) {
            SpannableExtensions_androidKt.setSpan(spannable, new IndentationFixSpan(), spannable.length() - 1, spannable.length() - 1);
        }
        return spannable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toLayoutTextGranularity-duNsdkg, reason: not valid java name */
    public static final int m7162toLayoutTextGranularityduNsdkg(int i) {
        return (!TextGranularity.m7286equalsimpl0(i, TextGranularity.INSTANCE.m7290getCharacterDRrd7Zo()) && TextGranularity.m7286equalsimpl0(i, TextGranularity.INSTANCE.m7291getWordDRrd7Zo())) ? 1 : 0;
    }
}
