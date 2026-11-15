package androidx.compose.ui.spatial;

import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.metadata.MetadataValidation;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RelativeLayoutBounds.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001BC\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001f0'J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0000J&\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u0019J\u0006\u00100\u001a\u00020)J\u001d\u00101\u001a\u00020)2\u0006\u00102\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006¢\u0006\u0004\b4\u00105J\u0013\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u00109\u001a\u00020\u0019H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b#\u0010!R\u0011\u0010$\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b%\u0010!¨\u0006:"}, d2 = {"Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "", "topLeft", "", "bottomRight", "windowOffset", "Landroidx/compose/ui/unit/IntOffset;", "screenOffset", "windowSize", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "node", "Landroidx/compose/ui/node/DelegatableNode;", "<init>", "(JJJJJ[FLandroidx/compose/ui/node/DelegatableNode;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "[F", "positionInRoot", "getPositionInRoot-nOcc-ac", "()J", "positionInWindow", "getPositionInWindow-nOcc-ac", "positionInScreen", "getPositionInScreen-nOcc-ac", "width", "", "getWidth", "()I", "height", "getHeight", "boundsInRoot", "Landroidx/compose/ui/unit/IntRect;", "getBoundsInRoot", "()Landroidx/compose/ui/unit/IntRect;", "boundsInWindow", "getBoundsInWindow", "boundsInScreen", "getBoundsInScreen", "calculateOcclusions", "", "fractionVisibleIn", "", "viewport", "fractionVisibleInRect", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "fractionVisibleInWindow", "fractionVisibleInWindowWithInsets", "topLeftInset", "bottomRightInset", "fractionVisibleInWindowWithInsets-E1MhUcY", "(JJ)F", MetadataValidation.EQUALS, "", "other", "hashCode", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RelativeLayoutBounds {
    public static final int $stable = 8;
    private final long bottomRight;
    private final DelegatableNode node;
    private final long screenOffset;
    private final long topLeft;
    private final float[] viewToWindowMatrix;
    private final long windowOffset;
    private final long windowSize;

    public /* synthetic */ RelativeLayoutBounds(long j, long j2, long j3, long j4, long j5, float[] fArr, DelegatableNode delegatableNode, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, fArr, delegatableNode);
    }

    private RelativeLayoutBounds(long topLeft, long bottomRight, long windowOffset, long screenOffset, long windowSize, float[] viewToWindowMatrix, DelegatableNode node) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.windowOffset = windowOffset;
        this.screenOffset = screenOffset;
        this.windowSize = windowSize;
        this.viewToWindowMatrix = viewToWindowMatrix;
        this.node = node;
    }

    /* JADX INFO: renamed from: getPositionInRoot-nOcc-ac, reason: not valid java name */
    public final long m7128getPositionInRootnOccac() {
        return IntOffset.m8027constructorimpl(this.topLeft);
    }

    /* JADX INFO: renamed from: getPositionInWindow-nOcc-ac, reason: not valid java name */
    public final long m7130getPositionInWindownOccac() {
        int x = IntOffset.m8033getXimpl(this.screenOffset) - IntOffset.m8033getXimpl(this.windowOffset);
        int y = IntOffset.m8034getYimpl(this.screenOffset) - IntOffset.m8034getYimpl(this.windowOffset);
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        int x$iv = l + x;
        int y$iv = t + y;
        return IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) y$iv) & 4294967295L));
    }

    /* JADX INFO: renamed from: getPositionInScreen-nOcc-ac, reason: not valid java name */
    public final long m7129getPositionInScreennOccac() {
        int x = IntOffset.m8033getXimpl(this.screenOffset);
        int y = IntOffset.m8034getYimpl(this.screenOffset);
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        int x$iv = l + x;
        int y$iv = t + y;
        return IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) y$iv) & 4294967295L));
    }

    public final int getWidth() {
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.bottomRight;
        int r = (int) (xy$iv2 >> 32);
        return r - l;
    }

    public final int getHeight() {
        long xy$iv = this.topLeft;
        int t = (int) xy$iv;
        long xy$iv2 = this.bottomRight;
        int b = (int) xy$iv2;
        return b - t;
    }

    public final IntRect getBoundsInRoot() {
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        long xy$iv3 = this.bottomRight;
        int $i$f$unpackX = (int) (xy$iv3 >> 32);
        long xy$iv4 = this.bottomRight;
        int b = (int) xy$iv4;
        return new IntRect(l, t, $i$f$unpackX, b);
    }

    public final IntRect getBoundsInWindow() {
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        long xy$iv3 = this.bottomRight;
        int $i$f$unpackX = (int) (xy$iv3 >> 32);
        long xy$iv4 = this.bottomRight;
        int b = (int) xy$iv4;
        if (this.viewToWindowMatrix != null) {
            return IntRectKt.roundToIntRect(Matrix.m5348mapimpl(this.viewToWindowMatrix, new Rect(l, t, $i$f$unpackX, b)));
        }
        int x = IntOffset.m8033getXimpl(this.screenOffset) - IntOffset.m8033getXimpl(this.windowOffset);
        int y = IntOffset.m8034getYimpl(this.screenOffset) - IntOffset.m8034getYimpl(this.windowOffset);
        return new IntRect(l + x, t + y, $i$f$unpackX + x, b + y);
    }

    public final IntRect getBoundsInScreen() {
        if (this.viewToWindowMatrix != null) {
            IntRect windowRect = getBoundsInWindow();
            long offset = this.windowOffset;
            return new IntRect(windowRect.getLeft() + IntOffset.m8033getXimpl(offset), windowRect.getTop() + IntOffset.m8034getYimpl(offset), windowRect.getRight() + IntOffset.m8033getXimpl(offset), windowRect.getBottom() + IntOffset.m8034getYimpl(offset));
        }
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        long xy$iv2 = this.topLeft;
        int t = (int) xy$iv2;
        long xy$iv3 = this.bottomRight;
        int $i$f$unpackX = (int) (xy$iv3 >> 32);
        long xy$iv4 = this.bottomRight;
        int b = (int) xy$iv4;
        int x = IntOffset.m8033getXimpl(this.screenOffset);
        int y = IntOffset.m8034getYimpl(this.screenOffset);
        return new IntRect(l + x, t + y, $i$f$unpackX + x, b + y);
    }

    public final List<IntRect> calculateOcclusions() {
        int i$iv;
        int idIndex;
        RectManager rectManager = DelegatableNodeKt.requireOwner(this.node).getRectManager();
        int id = DelegatableNodeKt.requireLayoutNode(this.node).getSemanticsId();
        RectList rectList = rectManager.getRects();
        int idIndex2 = rectList.indexOf(id);
        if (idIndex2 < 0) {
            return CollectionsKt.emptyList();
        }
        List $this$calculateOcclusions_u24lambda_u240 = CollectionsKt.createListBuilder();
        long[] items$iv = rectList.items;
        int size$iv = rectList.itemsSize;
        long destTopLeft$iv = items$iv[idIndex2];
        long destBottomRight$iv = items$iv[idIndex2 + 1];
        int i$iv2 = 0;
        while (true) {
            rectList = rectList;
            if (i$iv2 >= items$iv.length - 2 || i$iv2 >= size$iv) {
                break;
                break;
            }
            if (i$iv2 == idIndex2) {
                i$iv2 += 3;
            } else {
                long topLeft$iv = items$iv[i$iv2 + 0];
                long bottomRight$iv = items$iv[i$iv2 + 1];
                long a$iv$iv = ((destBottomRight$iv - topLeft$iv) - InlineClassHelperKt.Uint64Low32) | ((bottomRight$iv - destTopLeft$iv) - InlineClassHelperKt.Uint64Low32);
                if (!((a$iv$iv & (-9223372034707292160L)) == 0)) {
                    i$iv = i$iv2;
                    idIndex = idIndex2;
                } else {
                    idIndex = idIndex2;
                    int l = (int) (topLeft$iv >> 32);
                    int t = (int) topLeft$iv;
                    int r = (int) (bottomRight$iv >> 32);
                    int b = (int) bottomRight$iv;
                    long meta$iv$iv = items$iv[i$iv2 + 2];
                    i$iv = i$iv2;
                    int i$iv3 = (int) meta$iv$iv;
                    int intersectingId = i$iv3 & 33554431;
                    if (rectManager.isTargetDrawnFirst$ui(id, intersectingId)) {
                        $this$calculateOcclusions_u24lambda_u240.add(new IntRect(l, t, r, b));
                    }
                }
                i$iv2 = i$iv + 3;
                id = id;
                idIndex2 = idIndex;
                rectManager = rectManager;
            }
        }
        return CollectionsKt.build($this$calculateOcclusions_u24lambda_u240);
    }

    public final float fractionVisibleIn(RelativeLayoutBounds viewport) {
        long tl = viewport.topLeft;
        long br = viewport.bottomRight;
        int $i$f$unpackX = (int) (br >> 32);
        return fractionVisibleInRect((int) (tl >> 32), (int) tl, $i$f$unpackX, (int) br);
    }

    public final float fractionVisibleInRect(int left, int top, int right, int bottom) {
        long xy$iv = this.topLeft;
        int l = (int) (xy$iv >> 32);
        int clippedLeft = Math.min(Math.max(l, left), right);
        long xy$iv2 = this.topLeft;
        int $i$f$unpackY = (int) xy$iv2;
        int clippedTop = Math.min(Math.max($i$f$unpackY, top), bottom);
        long xy$iv3 = this.bottomRight;
        int r = (int) (xy$iv3 >> 32);
        int clippedRight = Math.max(Math.min(r, right), left);
        long xy$iv4 = this.bottomRight;
        int b = (int) xy$iv4;
        int clippedBottom = Math.max(Math.min(b, bottom), top);
        int viewportArea = (right - left) * (bottom - top);
        int rectArea = (r - l) * (b - $i$f$unpackY);
        int clippedArea = Math.max((clippedRight - clippedLeft) * (clippedBottom - clippedTop), 0);
        int maxArea = Math.min(viewportArea, rectArea);
        return clippedArea / maxArea;
    }

    public final float fractionVisibleInWindow() {
        long windowSize = this.windowSize;
        return fractionVisibleInRect(0, 0, (int) (windowSize >> 32), (int) windowSize);
    }

    /* JADX INFO: renamed from: fractionVisibleInWindowWithInsets-E1MhUcY, reason: not valid java name */
    public final float m7127fractionVisibleInWindowWithInsetsE1MhUcY(long topLeftInset, long bottomRightInset) {
        long windowSize = this.windowSize;
        int x = IntOffset.m8033getXimpl(this.windowOffset);
        int y = IntOffset.m8034getYimpl(this.windowOffset);
        return fractionVisibleInRect(IntOffset.m8033getXimpl(topLeftInset) + x, IntOffset.m8034getYimpl(topLeftInset) + y, (((int) (windowSize >> 32)) + x) - IntOffset.m8033getXimpl(bottomRightInset), (((int) windowSize) + y) - IntOffset.m8034getYimpl(bottomRightInset));
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0063  */
    public boolean equals(Object other) {
        boolean zM5343equalsimpl0;
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        if (this.topLeft != ((RelativeLayoutBounds) other).topLeft || this.bottomRight != ((RelativeLayoutBounds) other).bottomRight || this.windowSize != ((RelativeLayoutBounds) other).windowSize || !IntOffset.m8032equalsimpl0(this.windowOffset, ((RelativeLayoutBounds) other).windowOffset) || !IntOffset.m8032equalsimpl0(this.screenOffset, ((RelativeLayoutBounds) other).screenOffset)) {
            return false;
        }
        float[] fArr = this.viewToWindowMatrix;
        float[] fArr2 = ((RelativeLayoutBounds) other).viewToWindowMatrix;
        if (fArr == null) {
            if (fArr2 == null) {
                zM5343equalsimpl0 = true;
            } else {
                zM5343equalsimpl0 = false;
            }
        } else if (fArr2 == null) {
            zM5343equalsimpl0 = false;
        } else {
            zM5343equalsimpl0 = Matrix.m5343equalsimpl0(fArr, fArr2);
        }
        if (zM5343equalsimpl0 && Intrinsics.areEqual(this.node, ((RelativeLayoutBounds) other).node)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Long.hashCode(this.topLeft);
        int result2 = ((((((((result * 31) + Long.hashCode(this.bottomRight)) * 31) + Long.hashCode(this.windowSize)) * 31) + IntOffset.m8035hashCodeimpl(this.windowOffset)) * 31) + IntOffset.m8035hashCodeimpl(this.screenOffset)) * 31;
        float[] fArr = this.viewToWindowMatrix;
        return ((result2 + (fArr != null ? Matrix.m5345hashCodeimpl(fArr) : 0)) * 31) + this.node.hashCode();
    }
}
