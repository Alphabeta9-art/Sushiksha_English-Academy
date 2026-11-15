package androidx.compose.ui.spatial;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: ThrottledCallbacks.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aK\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"rectInfoFor", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "node", "Landroidx/compose/ui/node/DelegatableNode;", "topLeft", "", "bottomRight", "windowOffset", "Landroidx/compose/ui/unit/IntOffset;", "screenOffset", "windowSize", "viewToWindowMatrix", "Landroidx/compose/ui/graphics/Matrix;", "rectInfoFor-Dg36KO4", "(Landroidx/compose/ui/node/DelegatableNode;JJJJJ[F)Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ThrottledCallbacksKt {
    /* JADX INFO: renamed from: rectInfoFor-Dg36KO4, reason: not valid java name */
    public static final RelativeLayoutBounds m7141rectInfoForDg36KO4(DelegatableNode node, long topLeft, long bottomRight, long j, long j2, long windowSize, float[] viewToWindowMatrix) {
        NodeCoordinator coordinator = DelegatableNodeKt.m6712requireCoordinator64DMado(node, NodeKind.m6860constructorimpl(2));
        LayoutNode layoutNode = DelegatableNodeKt.requireLayoutNode(node);
        if (!layoutNode.isPlaced()) {
            return null;
        }
        boolean needsTransform = layoutNode.getOuterCoordinator$ui() != coordinator;
        if (needsTransform) {
            long jM8027constructorimpl = IntOffset.m8027constructorimpl(topLeft);
            float x$iv$iv = IntOffset.m8033getXimpl(jM8027constructorimpl);
            float y$iv$iv = IntOffset.m8034getYimpl(jM8027constructorimpl);
            long v1$iv$iv$iv = Float.floatToRawIntBits(x$iv$iv);
            long v2$iv$iv$iv = Float.floatToRawIntBits(y$iv$iv);
            long topLeftOffset = Offset.m4844constructorimpl((v1$iv$iv$iv << 32) | (v2$iv$iv$iv & 4294967295L));
            long size = coordinator.getCoordinates().mo6549getSizeYbymL2g();
            long transformedPos = IntOffsetKt.m8050roundk4lQ0M(layoutNode.getOuterCoordinator$ui().getCoordinates().mo6550localPositionOfR5De75A(coordinator, topLeftOffset));
            int x$iv = IntOffset.m8033getXimpl(transformedPos) + ((int) (size >> 32));
            int y$iv = IntOffset.m8034getYimpl(transformedPos) + ((int) (size & 4294967295L));
            return new RelativeLayoutBounds(transformedPos, IntOffset.m8027constructorimpl((((long) x$iv) << 32) | (((long) y$iv) & 4294967295L)), j, j2, windowSize, viewToWindowMatrix, node, null);
        }
        return new RelativeLayoutBounds(topLeft, bottomRight, j, j2, windowSize, viewToWindowMatrix, node, null);
    }
}
