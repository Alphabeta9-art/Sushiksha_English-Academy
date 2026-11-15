package androidx.compose.ui.geometry;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RoundRect.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u0000 ?2\u00020\u0001:\u0001?BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u001e\u001a\u00020\u0000H\u0002J(\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0003H\u0002J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0086\u0002¢\u0006\u0004\b(\u0010)J\b\u0010*\u001a\u00020+H\u0016J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\u0010\u00100\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b1\u0010\u0014J\u0010\u00102\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b3\u0010\u0014J\u0010\u00104\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b5\u0010\u0014J\u0010\u00106\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b7\u0010\u0014J`\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bHÆ\u0001¢\u0006\u0004\b9\u0010:J\u0013\u0010;\u001a\u00020%2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020>HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\u0007\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\t\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\n\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0017\u0010\u0014R\u0013\u0010\u000b\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0019\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000fR\u0011\u0010\u001b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000fR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect;", "", TtmlNode.LEFT, "", "top", TtmlNode.RIGHT, "bottom", "topLeftCornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "topRightCornerRadius", "bottomRightCornerRadius", "bottomLeftCornerRadius", "<init>", "(FFFFJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getLeft", "()F", "getTop", "getRight", "getBottom", "getTopLeftCornerRadius-kKHJgLs", "()J", "J", "getTopRightCornerRadius-kKHJgLs", "getBottomRightCornerRadius-kKHJgLs", "getBottomLeftCornerRadius-kKHJgLs", "width", "getWidth", "height", "getHeight", "_scaledRadiiRect", "scaledRadiiRect", "minRadius", MetadataValidation.MIN, "radius1", "radius2", "limit", "contains", "", "point", "Landroidx/compose/ui/geometry/Offset;", "contains-k-4lQ0M", "(J)Z", "toString", "", "component1", "component2", "component3", "component4", "component5", "component5-kKHJgLs", "component6", "component6-kKHJgLs", "component7", "component7-kKHJgLs", "component8", "component8-kKHJgLs", "copy", "copy-MDFrsts", "(FFFFJJJJ)Landroidx/compose/ui/geometry/RoundRect;", MetadataValidation.EQUALS, "other", "hashCode", "", "Companion", "ui-geometry"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class RoundRect {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RoundRect Zero = RoundRectKt.m4906RoundRectgG7oq9Y(0.0f, 0.0f, 0.0f, 0.0f, CornerRadius.INSTANCE.m4824getZerokKHJgLs());
    private RoundRect _scaledRadiiRect;
    private final float bottom;
    private final long bottomLeftCornerRadius;
    private final long bottomRightCornerRadius;
    private final float left;
    private final float right;
    private final float top;
    private final long topLeftCornerRadius;
    private final long topRightCornerRadius;

    public /* synthetic */ RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, j, j2, j3, j4);
    }

    /* JADX INFO: renamed from: copy-MDFrsts$default, reason: not valid java name */
    public static /* synthetic */ RoundRect m4893copyMDFrsts$default(RoundRect roundRect, float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = roundRect.left;
        }
        if ((i & 2) != 0) {
            f2 = roundRect.top;
        }
        if ((i & 4) != 0) {
            f3 = roundRect.right;
        }
        if ((i & 8) != 0) {
            f4 = roundRect.bottom;
        }
        if ((i & 16) != 0) {
            j = roundRect.topLeftCornerRadius;
        }
        if ((i & 32) != 0) {
            j2 = roundRect.topRightCornerRadius;
        }
        if ((i & 64) != 0) {
            j3 = roundRect.bottomRightCornerRadius;
        }
        if ((i & 128) != 0) {
            j4 = roundRect.bottomLeftCornerRadius;
        }
        long j5 = j4;
        long j6 = j3;
        long j7 = j2;
        long j8 = j;
        return roundRect.m4899copyMDFrsts(f, f2, f3, f4, j8, j7, j6, j5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getLeft() {
        return this.left;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getTop() {
        return this.top;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getRight() {
        return this.right;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: component5-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopLeftCornerRadius() {
        return this.topLeftCornerRadius;
    }

    /* JADX INFO: renamed from: component6-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopRightCornerRadius() {
        return this.topRightCornerRadius;
    }

    /* JADX INFO: renamed from: component7-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomRightCornerRadius() {
        return this.bottomRightCornerRadius;
    }

    /* JADX INFO: renamed from: component8-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomLeftCornerRadius() {
        return this.bottomLeftCornerRadius;
    }

    /* JADX INFO: renamed from: copy-MDFrsts, reason: not valid java name */
    public final RoundRect m4899copyMDFrsts(float left, float top, float right, float bottom, long topLeftCornerRadius, long topRightCornerRadius, long bottomRightCornerRadius, long bottomLeftCornerRadius) {
        return new RoundRect(left, top, right, bottom, topLeftCornerRadius, topRightCornerRadius, bottomRightCornerRadius, bottomLeftCornerRadius, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoundRect)) {
            return false;
        }
        RoundRect roundRect = (RoundRect) other;
        return Float.compare(this.left, roundRect.left) == 0 && Float.compare(this.top, roundRect.top) == 0 && Float.compare(this.right, roundRect.right) == 0 && Float.compare(this.bottom, roundRect.bottom) == 0 && CornerRadius.m4811equalsimpl0(this.topLeftCornerRadius, roundRect.topLeftCornerRadius) && CornerRadius.m4811equalsimpl0(this.topRightCornerRadius, roundRect.topRightCornerRadius) && CornerRadius.m4811equalsimpl0(this.bottomRightCornerRadius, roundRect.bottomRightCornerRadius) && CornerRadius.m4811equalsimpl0(this.bottomLeftCornerRadius, roundRect.bottomLeftCornerRadius);
    }

    public int hashCode() {
        return (((((((((((((Float.hashCode(this.left) * 31) + Float.hashCode(this.top)) * 31) + Float.hashCode(this.right)) * 31) + Float.hashCode(this.bottom)) * 31) + CornerRadius.m4814hashCodeimpl(this.topLeftCornerRadius)) * 31) + CornerRadius.m4814hashCodeimpl(this.topRightCornerRadius)) * 31) + CornerRadius.m4814hashCodeimpl(this.bottomRightCornerRadius)) * 31) + CornerRadius.m4814hashCodeimpl(this.bottomLeftCornerRadius);
    }

    private RoundRect(float left, float top, float right, float bottom, long topLeftCornerRadius, long topRightCornerRadius, long bottomRightCornerRadius, long bottomLeftCornerRadius) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.topLeftCornerRadius = topLeftCornerRadius;
        this.topRightCornerRadius = topRightCornerRadius;
        this.bottomRightCornerRadius = bottomRightCornerRadius;
        this.bottomLeftCornerRadius = bottomLeftCornerRadius;
    }

    public /* synthetic */ RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, (i & 16) != 0 ? CornerRadius.INSTANCE.m4824getZerokKHJgLs() : j, (i & 32) != 0 ? CornerRadius.INSTANCE.m4824getZerokKHJgLs() : j2, (i & 64) != 0 ? CornerRadius.INSTANCE.m4824getZerokKHJgLs() : j3, (i & 128) != 0 ? CornerRadius.INSTANCE.m4824getZerokKHJgLs() : j4, null);
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getTop() {
        return this.top;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: getTopLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m4902getTopLeftCornerRadiuskKHJgLs() {
        return this.topLeftCornerRadius;
    }

    /* JADX INFO: renamed from: getTopRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m4903getTopRightCornerRadiuskKHJgLs() {
        return this.topRightCornerRadius;
    }

    /* JADX INFO: renamed from: getBottomRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m4901getBottomRightCornerRadiuskKHJgLs() {
        return this.bottomRightCornerRadius;
    }

    /* JADX INFO: renamed from: getBottomLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m4900getBottomLeftCornerRadiuskKHJgLs() {
        return this.bottomLeftCornerRadius;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    private final RoundRect scaledRadiiRect() {
        RoundRect roundRect = this._scaledRadiiRect;
        if (roundRect != null) {
            return roundRect;
        }
        RoundRect $this$scaledRadiiRect_u24lambda_u240 = this;
        int bits$iv$iv$iv = (int) ($this$scaledRadiiRect_u24lambda_u240.bottomLeftCornerRadius & 4294967295L);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) ($this$scaledRadiiRect_u24lambda_u240.topLeftCornerRadius & 4294967295L);
        float scale = $this$scaledRadiiRect_u24lambda_u240.minRadius(1.0f, fIntBitsToFloat, Float.intBitsToFloat(bits$iv$iv$iv2), $this$scaledRadiiRect_u24lambda_u240.getHeight());
        int bits$iv$iv$iv3 = (int) ($this$scaledRadiiRect_u24lambda_u240.topLeftCornerRadius >> 32);
        float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv3);
        int bits$iv$iv$iv4 = (int) ($this$scaledRadiiRect_u24lambda_u240.topRightCornerRadius >> 32);
        float scale2 = $this$scaledRadiiRect_u24lambda_u240.minRadius(scale, fIntBitsToFloat2, Float.intBitsToFloat(bits$iv$iv$iv4), $this$scaledRadiiRect_u24lambda_u240.getWidth());
        int bits$iv$iv$iv5 = (int) ($this$scaledRadiiRect_u24lambda_u240.topRightCornerRadius & 4294967295L);
        float fIntBitsToFloat3 = Float.intBitsToFloat(bits$iv$iv$iv5);
        int bits$iv$iv$iv6 = (int) ($this$scaledRadiiRect_u24lambda_u240.bottomRightCornerRadius & 4294967295L);
        float scale3 = $this$scaledRadiiRect_u24lambda_u240.minRadius(scale2, fIntBitsToFloat3, Float.intBitsToFloat(bits$iv$iv$iv6), $this$scaledRadiiRect_u24lambda_u240.getHeight());
        int bits$iv$iv$iv7 = (int) ($this$scaledRadiiRect_u24lambda_u240.bottomRightCornerRadius >> 32);
        float fIntBitsToFloat4 = Float.intBitsToFloat(bits$iv$iv$iv7);
        int bits$iv$iv$iv8 = (int) ($this$scaledRadiiRect_u24lambda_u240.bottomLeftCornerRadius >> 32);
        float scale4 = $this$scaledRadiiRect_u24lambda_u240.minRadius(scale3, fIntBitsToFloat4, Float.intBitsToFloat(bits$iv$iv$iv8), $this$scaledRadiiRect_u24lambda_u240.getWidth());
        float f = $this$scaledRadiiRect_u24lambda_u240.left * scale4;
        float f2 = $this$scaledRadiiRect_u24lambda_u240.top * scale4;
        float f3 = $this$scaledRadiiRect_u24lambda_u240.right * scale4;
        float f4 = $this$scaledRadiiRect_u24lambda_u240.bottom * scale4;
        int bits$iv$iv$iv9 = (int) ($this$scaledRadiiRect_u24lambda_u240.topLeftCornerRadius >> 32);
        float x$iv = Float.intBitsToFloat(bits$iv$iv$iv9) * scale4;
        int bits$iv$iv$iv10 = (int) ($this$scaledRadiiRect_u24lambda_u240.topLeftCornerRadius & 4294967295L);
        float y$iv = Float.intBitsToFloat(bits$iv$iv$iv10) * scale4;
        long v1$iv$iv = Float.floatToRawIntBits(x$iv);
        long v2$iv$iv = Float.floatToRawIntBits(y$iv);
        long jM4806constructorimpl = CornerRadius.m4806constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
        int bits$iv$iv$iv11 = (int) ($this$scaledRadiiRect_u24lambda_u240.topRightCornerRadius >> 32);
        float x$iv2 = Float.intBitsToFloat(bits$iv$iv$iv11) * scale4;
        int bits$iv$iv$iv12 = (int) ($this$scaledRadiiRect_u24lambda_u240.topRightCornerRadius & 4294967295L);
        float y$iv2 = Float.intBitsToFloat(bits$iv$iv$iv12) * scale4;
        long v1$iv$iv2 = Float.floatToRawIntBits(x$iv2);
        long v2$iv$iv2 = Float.floatToRawIntBits(y$iv2);
        long v2$iv$iv3 = CornerRadius.m4806constructorimpl((v1$iv$iv2 << 32) | (v2$iv$iv2 & 4294967295L));
        int bits$iv$iv$iv13 = (int) ($this$scaledRadiiRect_u24lambda_u240.bottomRightCornerRadius >> 32);
        float x$iv3 = Float.intBitsToFloat(bits$iv$iv$iv13) * scale4;
        int bits$iv$iv$iv14 = (int) ($this$scaledRadiiRect_u24lambda_u240.bottomRightCornerRadius & 4294967295L);
        float y$iv3 = Float.intBitsToFloat(bits$iv$iv$iv14) * scale4;
        long v1$iv$iv3 = Float.floatToRawIntBits(x$iv3);
        long v2$iv$iv4 = Float.floatToRawIntBits(y$iv3);
        long v2$iv$iv5 = CornerRadius.m4806constructorimpl((v1$iv$iv3 << 32) | (v2$iv$iv4 & 4294967295L));
        int bits$iv$iv$iv15 = (int) ($this$scaledRadiiRect_u24lambda_u240.bottomLeftCornerRadius >> 32);
        float x$iv4 = Float.intBitsToFloat(bits$iv$iv$iv15) * scale4;
        int bits$iv$iv$iv16 = (int) ($this$scaledRadiiRect_u24lambda_u240.bottomLeftCornerRadius & 4294967295L);
        float y$iv4 = Float.intBitsToFloat(bits$iv$iv$iv16) * scale4;
        long v1$iv$iv4 = Float.floatToRawIntBits(x$iv4);
        long v2$iv$iv6 = Float.floatToRawIntBits(y$iv4);
        RoundRect it = new RoundRect(f, f2, f3, f4, jM4806constructorimpl, v2$iv$iv3, v2$iv$iv5, CornerRadius.m4806constructorimpl((v1$iv$iv4 << 32) | (v2$iv$iv6 & 4294967295L)), null);
        this._scaledRadiiRect = it;
        return it;
    }

    private final float minRadius(float min, float radius1, float radius2, float limit) {
        float sum = radius1 + radius2;
        if (sum > limit) {
            if (!(sum == 0.0f)) {
                return Math.min(min, limit / sum);
            }
        }
        return min;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:24:0x0202  */
    /* JADX WARN: Code duplicated, block: B:26:0x0227  */
    /* JADX WARN: Code duplicated, block: B:27:0x028f  */
    /* JADX WARN: Code duplicated, block: B:29:0x02b4  */
    /* JADX WARN: Code duplicated, block: B:31:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:34:0x034e  */
    /* JADX WARN: Code duplicated, block: B:39:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: contains-k-4lQ0M, reason: not valid java name */
    public final boolean m4898containsk4lQ0M(long point) {
        boolean z;
        float fIntBitsToFloat;
        float f;
        int bits$iv$iv$iv;
        float fIntBitsToFloat2;
        float f2;
        int bits$iv$iv$iv2;
        float fIntBitsToFloat3;
        float f3;
        int bits$iv$iv$iv3;
        float x;
        float y;
        float radiusX;
        float radiusY;
        float fIntBitsToFloat4;
        float f4;
        int bits$iv$iv$iv4;
        float newX;
        float newY;
        int bits$iv$iv$iv5 = (int) (point >> 32);
        if (Float.intBitsToFloat(bits$iv$iv$iv5) >= this.left) {
            int bits$iv$iv$iv6 = (int) (point >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv6) < this.right) {
                int bits$iv$iv$iv7 = (int) (point & 4294967295L);
                if (Float.intBitsToFloat(bits$iv$iv$iv7) >= this.top) {
                    int bits$iv$iv$iv8 = (int) (point & 4294967295L);
                    if (Float.intBitsToFloat(bits$iv$iv$iv8) >= this.bottom) {
                        return false;
                    }
                    RoundRect scaled = scaledRadiiRect();
                    int bits$iv$iv$iv9 = (int) (point >> 32);
                    float fIntBitsToFloat5 = Float.intBitsToFloat(bits$iv$iv$iv9);
                    float f5 = this.left;
                    int bits$iv$iv$iv10 = (int) (scaled.topLeftCornerRadius >> 32);
                    if (fIntBitsToFloat5 < f5 + Float.intBitsToFloat(bits$iv$iv$iv10)) {
                        int bits$iv$iv$iv11 = (int) (point & 4294967295L);
                        float fIntBitsToFloat6 = Float.intBitsToFloat(bits$iv$iv$iv11);
                        float f6 = this.top;
                        z = true;
                        int bits$iv$iv$iv12 = (int) (scaled.topLeftCornerRadius & 4294967295L);
                        if (fIntBitsToFloat6 < f6 + Float.intBitsToFloat(bits$iv$iv$iv12)) {
                            int bits$iv$iv$iv13 = (int) (point >> 32);
                            float fIntBitsToFloat7 = Float.intBitsToFloat(bits$iv$iv$iv13) - this.left;
                            int bits$iv$iv$iv14 = (int) (scaled.topLeftCornerRadius >> 32);
                            x = fIntBitsToFloat7 - Float.intBitsToFloat(bits$iv$iv$iv14);
                            int bits$iv$iv$iv15 = (int) (point & 4294967295L);
                            float fIntBitsToFloat8 = Float.intBitsToFloat(bits$iv$iv$iv15) - this.top;
                            int bits$iv$iv$iv16 = (int) (scaled.topLeftCornerRadius & 4294967295L);
                            y = fIntBitsToFloat8 - Float.intBitsToFloat(bits$iv$iv$iv16);
                            int bits$iv$iv$iv17 = (int) (scaled.topLeftCornerRadius >> 32);
                            radiusX = Float.intBitsToFloat(bits$iv$iv$iv17);
                            int bits$iv$iv$iv18 = (int) (4294967295L & scaled.topLeftCornerRadius);
                            radiusY = Float.intBitsToFloat(bits$iv$iv$iv18);
                        }
                        newX = x / radiusX;
                        newY = y / radiusY;
                        if ((newX * newX) + (newY * newY) <= 1.0f) {
                            return z;
                        }
                        return false;
                    }
                    z = true;
                    int bits$iv$iv$iv19 = (int) (point >> 32);
                    float fIntBitsToFloat9 = Float.intBitsToFloat(bits$iv$iv$iv19);
                    float f7 = this.right;
                    int bits$iv$iv$iv20 = (int) (scaled.topRightCornerRadius >> 32);
                    if (fIntBitsToFloat9 > f7 - Float.intBitsToFloat(bits$iv$iv$iv20)) {
                        int bits$iv$iv$iv21 = (int) (point & 4294967295L);
                        float fIntBitsToFloat10 = Float.intBitsToFloat(bits$iv$iv$iv21);
                        float f8 = this.top;
                        int bits$iv$iv$iv22 = (int) (scaled.topRightCornerRadius & 4294967295L);
                        if (fIntBitsToFloat10 < f8 + Float.intBitsToFloat(bits$iv$iv$iv22)) {
                            int bits$iv$iv$iv23 = (int) (point >> 32);
                            float fIntBitsToFloat11 = Float.intBitsToFloat(bits$iv$iv$iv23) - this.right;
                            int bits$iv$iv$iv24 = (int) (scaled.topRightCornerRadius >> 32);
                            x = fIntBitsToFloat11 + Float.intBitsToFloat(bits$iv$iv$iv24);
                            int bits$iv$iv$iv25 = (int) (point & 4294967295L);
                            float fIntBitsToFloat12 = Float.intBitsToFloat(bits$iv$iv$iv25) - this.top;
                            int bits$iv$iv$iv26 = (int) (scaled.topRightCornerRadius & 4294967295L);
                            y = fIntBitsToFloat12 - Float.intBitsToFloat(bits$iv$iv$iv26);
                            int bits$iv$iv$iv27 = (int) (scaled.topRightCornerRadius >> 32);
                            radiusX = Float.intBitsToFloat(bits$iv$iv$iv27);
                            int bits$iv$iv$iv28 = (int) (scaled.topRightCornerRadius & 4294967295L);
                            radiusY = Float.intBitsToFloat(bits$iv$iv$iv28);
                        } else {
                            int bits$iv$iv$iv29 = (int) (point >> 32);
                            fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv29);
                            f = this.right;
                            bits$iv$iv$iv = (int) (scaled.bottomRightCornerRadius >> 32);
                            if (fIntBitsToFloat > f - Float.intBitsToFloat(bits$iv$iv$iv)) {
                                int bits$iv$iv$iv30 = (int) (point >> 32);
                                fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv30);
                                f2 = this.left;
                                bits$iv$iv$iv2 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                if (fIntBitsToFloat2 < f2 + Float.intBitsToFloat(bits$iv$iv$iv2)) {
                                    int bits$iv$iv$iv31 = (int) (point & 4294967295L);
                                    fIntBitsToFloat3 = Float.intBitsToFloat(bits$iv$iv$iv31);
                                    f3 = this.bottom;
                                    bits$iv$iv$iv3 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                    if (fIntBitsToFloat3 > f3 - Float.intBitsToFloat(bits$iv$iv$iv3)) {
                                        int bits$iv$iv$iv32 = (int) (point >> 32);
                                        float fIntBitsToFloat13 = Float.intBitsToFloat(bits$iv$iv$iv32) - this.left;
                                        int bits$iv$iv$iv33 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                        x = fIntBitsToFloat13 - Float.intBitsToFloat(bits$iv$iv$iv33);
                                        int bits$iv$iv$iv34 = (int) (point & 4294967295L);
                                        float fIntBitsToFloat14 = Float.intBitsToFloat(bits$iv$iv$iv34) - this.bottom;
                                        int bits$iv$iv$iv35 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                        y = fIntBitsToFloat14 + Float.intBitsToFloat(bits$iv$iv$iv35);
                                        int bits$iv$iv$iv36 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                        radiusX = Float.intBitsToFloat(bits$iv$iv$iv36);
                                        int bits$iv$iv$iv37 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                        radiusY = Float.intBitsToFloat(bits$iv$iv$iv37);
                                    }
                                }
                                return z;
                            }
                            int bits$iv$iv$iv38 = (int) (point & 4294967295L);
                            fIntBitsToFloat4 = Float.intBitsToFloat(bits$iv$iv$iv38);
                            f4 = this.bottom;
                            bits$iv$iv$iv4 = (int) (scaled.bottomRightCornerRadius & 4294967295L);
                            if (fIntBitsToFloat4 > f4 - Float.intBitsToFloat(bits$iv$iv$iv4)) {
                                int bits$iv$iv$iv39 = (int) (point >> 32);
                                fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv39);
                                f2 = this.left;
                                bits$iv$iv$iv2 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                if (fIntBitsToFloat2 < f2 + Float.intBitsToFloat(bits$iv$iv$iv2)) {
                                    int bits$iv$iv$iv310 = (int) (point & 4294967295L);
                                    fIntBitsToFloat3 = Float.intBitsToFloat(bits$iv$iv$iv310);
                                    f3 = this.bottom;
                                    bits$iv$iv$iv3 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                    if (fIntBitsToFloat3 > f3 - Float.intBitsToFloat(bits$iv$iv$iv3)) {
                                        int bits$iv$iv$iv311 = (int) (point >> 32);
                                        float fIntBitsToFloat15 = Float.intBitsToFloat(bits$iv$iv$iv311) - this.left;
                                        int bits$iv$iv$iv312 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                        x = fIntBitsToFloat15 - Float.intBitsToFloat(bits$iv$iv$iv312);
                                        int bits$iv$iv$iv313 = (int) (point & 4294967295L);
                                        float fIntBitsToFloat16 = Float.intBitsToFloat(bits$iv$iv$iv313) - this.bottom;
                                        int bits$iv$iv$iv314 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                        y = fIntBitsToFloat16 + Float.intBitsToFloat(bits$iv$iv$iv314);
                                        int bits$iv$iv$iv315 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                        radiusX = Float.intBitsToFloat(bits$iv$iv$iv315);
                                        int bits$iv$iv$iv316 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                        radiusY = Float.intBitsToFloat(bits$iv$iv$iv316);
                                    }
                                }
                                return z;
                            }
                            int bits$iv$iv$iv40 = (int) (point >> 32);
                            float fIntBitsToFloat17 = Float.intBitsToFloat(bits$iv$iv$iv40) - this.right;
                            int bits$iv$iv$iv41 = (int) (scaled.bottomRightCornerRadius >> 32);
                            x = fIntBitsToFloat17 + Float.intBitsToFloat(bits$iv$iv$iv41);
                            int bits$iv$iv$iv42 = (int) (point & 4294967295L);
                            float fIntBitsToFloat18 = Float.intBitsToFloat(bits$iv$iv$iv42) - this.bottom;
                            int bits$iv$iv$iv43 = (int) (scaled.bottomRightCornerRadius & 4294967295L);
                            y = fIntBitsToFloat18 + Float.intBitsToFloat(bits$iv$iv$iv43);
                            int bits$iv$iv$iv44 = (int) (scaled.bottomRightCornerRadius >> 32);
                            radiusX = Float.intBitsToFloat(bits$iv$iv$iv44);
                            int bits$iv$iv$iv45 = (int) (scaled.bottomRightCornerRadius & 4294967295L);
                            radiusY = Float.intBitsToFloat(bits$iv$iv$iv45);
                        }
                    } else {
                        int bits$iv$iv$iv210 = (int) (point >> 32);
                        fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv210);
                        f = this.right;
                        bits$iv$iv$iv = (int) (scaled.bottomRightCornerRadius >> 32);
                        if (fIntBitsToFloat > f - Float.intBitsToFloat(bits$iv$iv$iv)) {
                            int bits$iv$iv$iv317 = (int) (point >> 32);
                            fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv317);
                            f2 = this.left;
                            bits$iv$iv$iv2 = (int) (scaled.bottomLeftCornerRadius >> 32);
                            if (fIntBitsToFloat2 < f2 + Float.intBitsToFloat(bits$iv$iv$iv2)) {
                                int bits$iv$iv$iv318 = (int) (point & 4294967295L);
                                fIntBitsToFloat3 = Float.intBitsToFloat(bits$iv$iv$iv318);
                                f3 = this.bottom;
                                bits$iv$iv$iv3 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                if (fIntBitsToFloat3 > f3 - Float.intBitsToFloat(bits$iv$iv$iv3)) {
                                    int bits$iv$iv$iv319 = (int) (point >> 32);
                                    float fIntBitsToFloat19 = Float.intBitsToFloat(bits$iv$iv$iv319) - this.left;
                                    int bits$iv$iv$iv3110 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                    x = fIntBitsToFloat19 - Float.intBitsToFloat(bits$iv$iv$iv3110);
                                    int bits$iv$iv$iv3111 = (int) (point & 4294967295L);
                                    float fIntBitsToFloat110 = Float.intBitsToFloat(bits$iv$iv$iv3111) - this.bottom;
                                    int bits$iv$iv$iv3112 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                    y = fIntBitsToFloat110 + Float.intBitsToFloat(bits$iv$iv$iv3112);
                                    int bits$iv$iv$iv3113 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                    radiusX = Float.intBitsToFloat(bits$iv$iv$iv3113);
                                    int bits$iv$iv$iv3114 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                    radiusY = Float.intBitsToFloat(bits$iv$iv$iv3114);
                                }
                            }
                            return z;
                        }
                        int bits$iv$iv$iv320 = (int) (point & 4294967295L);
                        fIntBitsToFloat4 = Float.intBitsToFloat(bits$iv$iv$iv320);
                        f4 = this.bottom;
                        bits$iv$iv$iv4 = (int) (scaled.bottomRightCornerRadius & 4294967295L);
                        if (fIntBitsToFloat4 > f4 - Float.intBitsToFloat(bits$iv$iv$iv4)) {
                            int bits$iv$iv$iv3115 = (int) (point >> 32);
                            fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv3115);
                            f2 = this.left;
                            bits$iv$iv$iv2 = (int) (scaled.bottomLeftCornerRadius >> 32);
                            if (fIntBitsToFloat2 < f2 + Float.intBitsToFloat(bits$iv$iv$iv2)) {
                                int bits$iv$iv$iv3116 = (int) (point & 4294967295L);
                                fIntBitsToFloat3 = Float.intBitsToFloat(bits$iv$iv$iv3116);
                                f3 = this.bottom;
                                bits$iv$iv$iv3 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                if (fIntBitsToFloat3 > f3 - Float.intBitsToFloat(bits$iv$iv$iv3)) {
                                    int bits$iv$iv$iv3117 = (int) (point >> 32);
                                    float fIntBitsToFloat111 = Float.intBitsToFloat(bits$iv$iv$iv3117) - this.left;
                                    int bits$iv$iv$iv3118 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                    x = fIntBitsToFloat111 - Float.intBitsToFloat(bits$iv$iv$iv3118);
                                    int bits$iv$iv$iv3119 = (int) (point & 4294967295L);
                                    float fIntBitsToFloat112 = Float.intBitsToFloat(bits$iv$iv$iv3119) - this.bottom;
                                    int bits$iv$iv$iv31110 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                    y = fIntBitsToFloat112 + Float.intBitsToFloat(bits$iv$iv$iv31110);
                                    int bits$iv$iv$iv31111 = (int) (scaled.bottomLeftCornerRadius >> 32);
                                    radiusX = Float.intBitsToFloat(bits$iv$iv$iv31111);
                                    int bits$iv$iv$iv31112 = (int) (scaled.bottomLeftCornerRadius & 4294967295L);
                                    radiusY = Float.intBitsToFloat(bits$iv$iv$iv31112);
                                }
                            }
                            return z;
                        }
                        int bits$iv$iv$iv46 = (int) (point >> 32);
                        float fIntBitsToFloat113 = Float.intBitsToFloat(bits$iv$iv$iv46) - this.right;
                        int bits$iv$iv$iv47 = (int) (scaled.bottomRightCornerRadius >> 32);
                        x = fIntBitsToFloat113 + Float.intBitsToFloat(bits$iv$iv$iv47);
                        int bits$iv$iv$iv48 = (int) (point & 4294967295L);
                        float fIntBitsToFloat114 = Float.intBitsToFloat(bits$iv$iv$iv48) - this.bottom;
                        int bits$iv$iv$iv49 = (int) (scaled.bottomRightCornerRadius & 4294967295L);
                        y = fIntBitsToFloat114 + Float.intBitsToFloat(bits$iv$iv$iv49);
                        int bits$iv$iv$iv410 = (int) (scaled.bottomRightCornerRadius >> 32);
                        radiusX = Float.intBitsToFloat(bits$iv$iv$iv410);
                        int bits$iv$iv$iv411 = (int) (scaled.bottomRightCornerRadius & 4294967295L);
                        radiusY = Float.intBitsToFloat(bits$iv$iv$iv411);
                    }
                    newX = x / radiusX;
                    newY = y / radiusY;
                    if ((newX * newX) + (newY * newY) <= 1.0f) {
                        return z;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public String toString() {
        long tlRadius = this.topLeftCornerRadius;
        long trRadius = this.topRightCornerRadius;
        long brRadius = this.bottomRightCornerRadius;
        long blRadius = this.bottomLeftCornerRadius;
        String rect = GeometryUtilsKt.toStringAsFixed(this.left, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.top, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.right, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom, 1);
        if (!CornerRadius.m4811equalsimpl0(tlRadius, trRadius) || !CornerRadius.m4811equalsimpl0(trRadius, brRadius) || !CornerRadius.m4811equalsimpl0(brRadius, blRadius)) {
            return "RoundRect(rect=" + rect + ", topLeft=" + ((Object) CornerRadius.m4820toStringimpl(tlRadius)) + ", topRight=" + ((Object) CornerRadius.m4820toStringimpl(trRadius)) + ", bottomRight=" + ((Object) CornerRadius.m4820toStringimpl(brRadius)) + ", bottomLeft=" + ((Object) CornerRadius.m4820toStringimpl(blRadius)) + ')';
        }
        int bits$iv$iv$iv = (int) (tlRadius >> 32);
        int bits$iv$iv$iv2 = (int) (tlRadius & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv) == Float.intBitsToFloat(bits$iv$iv$iv2)) {
            int bits$iv$iv$iv3 = (int) (tlRadius >> 32);
            return "RoundRect(rect=" + rect + ", radius=" + GeometryUtilsKt.toStringAsFixed(Float.intBitsToFloat(bits$iv$iv$iv3), 1) + ')';
        }
        int bits$iv$iv$iv4 = (int) (tlRadius >> 32);
        StringBuilder sbAppend = new StringBuilder().append("RoundRect(rect=").append(rect).append(", x=").append(GeometryUtilsKt.toStringAsFixed(Float.intBitsToFloat(bits$iv$iv$iv4), 1)).append(", y=");
        int bits$iv$iv$iv5 = (int) (tlRadius & 4294967295L);
        return sbAppend.append(GeometryUtilsKt.toStringAsFixed(Float.intBitsToFloat(bits$iv$iv$iv5), 1)).append(')').toString();
    }

    /* JADX INFO: compiled from: RoundRect.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect$Companion;", "", "<init>", "()V", "Zero", "Landroidx/compose/ui/geometry/RoundRect;", "getZero$annotations", "getZero", "()Landroidx/compose/ui/geometry/RoundRect;", "ui-geometry"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getZero$annotations() {
        }

        private Companion() {
        }

        public final RoundRect getZero() {
            return RoundRect.Zero;
        }
    }

    public static final RoundRect getZero() {
        return INSTANCE.getZero();
    }
}
