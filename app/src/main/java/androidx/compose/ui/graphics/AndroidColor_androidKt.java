package androidx.compose.ui.graphics;

import kotlin.Metadata;
import kotlin.ULong;

/* JADX INFO: compiled from: AndroidColor.android.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u0002*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"toColorLong", "", "Landroidx/compose/ui/graphics/Color;", "toColorLong-8_81llA", "(J)J", "fromColorLong", "Landroidx/compose/ui/graphics/Color$Companion;", "colorLong", "(Landroidx/compose/ui/graphics/Color$Companion;J)J", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidColor_androidKt {
    /* JADX INFO: renamed from: toColorLong-8_81llA, reason: not valid java name */
    public static final long m4960toColorLong8_81llA(long j) {
        if (Long.compare(ULong.m9262constructorimpl(j & 63) ^ Long.MIN_VALUE, 16 ^ Long.MIN_VALUE) >= 0) {
            return ULong.m9262constructorimpl(ULong.m9262constructorimpl(ULong.m9262constructorimpl(-64L) & j) | ULong.m9262constructorimpl(ULong.m9262constructorimpl(63 & j) - 1));
        }
        return j;
    }

    public static final long fromColorLong(Color.Companion $this$fromColorLong, long colorLong) {
        long color;
        if ((colorLong & 63) >= 16) {
            color = ((-64) & colorLong) | ((63 & colorLong) + 1);
        } else {
            color = colorLong;
        }
        return Color.m5093constructorimpl(ULong.m9262constructorimpl(color));
    }
}
