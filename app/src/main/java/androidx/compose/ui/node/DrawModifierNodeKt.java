package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import kotlin.Metadata;

/* JADX INFO: compiled from: DrawModifierNode.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"invalidateDraw", "", "Landroidx/compose/ui/node/DrawModifierNode;", "dispatchDraw", "Landroidx/compose/ui/node/DelegatableNode;", "scope", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DrawModifierNodeKt {
    public static final void invalidateDraw(DrawModifierNode $this$invalidateDraw) {
        if ($this$invalidateDraw.getNode().getIsAttached()) {
            DelegatableNodeKt.m6712requireCoordinator64DMado($this$invalidateDraw, NodeKind.m6860constructorimpl(1)).invalidateLayer();
        }
    }

    public static final void dispatchDraw(DelegatableNode $this$dispatchDraw, ContentDrawScope scope) {
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchDraw.getNode();
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(4);
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv2;
        while (nodePop != null) {
            if (nodePop instanceof DrawModifierNode) {
                DrawModifierNode it = (DrawModifierNode) nodePop;
                it.draw(scope);
                $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
            } else {
                Modifier.Node this_$iv$iv = nodePop;
                int i = 1;
                if (((this_$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
                } else {
                    int count$iv = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if (((next$iv.getKindSet() & iM6860constructorimpl) != 0 ? i : 0) != 0) {
                            count$iv++;
                            if (count$iv == i) {
                                nodePop = next$iv;
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                            } else {
                                mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                Modifier.Node theNode$iv = nodePop;
                                if (theNode$iv != null) {
                                    if (mutableVector != null) {
                                        mutableVector.add(theNode$iv);
                                    }
                                    nodePop = null;
                                }
                                if (mutableVector != null) {
                                    mutableVector.add(next$iv);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        i = 1;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
                    if (count$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv;
                    }
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv;
        }
    }
}
