package androidx.compose.ui.node;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: LayoutTreeConsistencyChecker.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J\f\u0010\u0010\u001a\u00020\u000e*\u00020\u0003H\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0003H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/node/LayoutTreeConsistencyChecker;", "", "root", "Landroidx/compose/ui/node/LayoutNode;", "relayoutNodes", "Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;", "postponedMeasureRequests", "", "Landroidx/compose/ui/node/MeasureAndLayoutDelegate$PostponedRequest;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/node/DepthSortedSetsForDifferentPasses;Ljava/util/List;)V", "assertConsistent", "", "isTreeConsistent", "", "node", "consistentLayoutState", "nodeToString", "", "logTree", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LayoutTreeConsistencyChecker {
    public static final int $stable = 8;
    private final List<MeasureAndLayoutDelegate.PostponedRequest> postponedMeasureRequests;
    private final DepthSortedSetsForDifferentPasses relayoutNodes;
    private final LayoutNode root;

    public LayoutTreeConsistencyChecker(LayoutNode root, DepthSortedSetsForDifferentPasses relayoutNodes, List<MeasureAndLayoutDelegate.PostponedRequest> list) {
        this.root = root;
        this.relayoutNodes = relayoutNodes;
        this.postponedMeasureRequests = list;
    }

    public final void assertConsistent() {
        boolean inconsistencyFound = !isTreeConsistent(this.root);
        if (inconsistencyFound) {
            System.out.println((Object) logTree());
            throw new IllegalStateException("Inconsistency found!");
        }
    }

    private final boolean isTreeConsistent(LayoutNode node) {
        if (!consistentLayoutState(node)) {
            return false;
        }
        List<LayoutNode> children$ui = node.getChildren$ui();
        int size = children$ui.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = children$ui.get(index$iv);
            LayoutNode it = (LayoutNode) item$iv;
            if (!isTreeConsistent(it)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:154:0x006a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:155:0x006c A[EDGE_INSN: B:155:0x006c->B:30:0x006c BREAK  A[LOOP:1: B:20:0x0043->B:28:0x0067], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:156:0x00fc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:157:0x00f6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:17:0x002f  */
    /* JADX WARN: Code duplicated, block: B:19:0x0036  */
    /* JADX WARN: Code duplicated, block: B:21:0x0045  */
    /* JADX WARN: Code duplicated, block: B:26:0x0063  */
    /* JADX WARN: Code duplicated, block: B:28:0x0067 A[LOOP:1: B:20:0x0043->B:28:0x0067, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:31:0x006e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:0x0075 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:35:0x0076  */
    /* JADX WARN: Code duplicated, block: B:37:0x007c  */
    /* JADX WARN: Code duplicated, block: B:45:0x0096  */
    /* JADX WARN: Code duplicated, block: B:47:0x0099  */
    /* JADX WARN: Code duplicated, block: B:51:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:58:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:60:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:73:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:76:0x00f8 A[LOOP:2: B:72:0x00df->B:76:0x00f8, LOOP_END] */
    private final boolean consistentLayoutState(LayoutNode $this$consistentLayoutState) {
        List<MeasureAndLayoutDelegate.PostponedRequest> list;
        int index$iv$iv;
        int size;
        boolean z;
        Object item$iv$iv;
        boolean z2;
        boolean z3;
        List<MeasureAndLayoutDelegate.PostponedRequest> list2;
        int index$iv$iv2;
        int size2;
        Object it$iv;
        MeasureAndLayoutDelegate.PostponedRequest it;
        boolean z4;
        boolean z5;
        Object obj;
        LayoutNode parent = $this$consistentLayoutState.getParent$ui();
        LayoutNode.LayoutState parentLayoutState = parent != null ? parent.getLayoutState$ui() : null;
        boolean z6 = true;
        if ($this$consistentLayoutState.isPlaced()) {
            if ($this$consistentLayoutState.getMeasurePending$ui()) {
                list2 = this.postponedMeasureRequests;
                index$iv$iv2 = 0;
                size2 = list2.size();
                while (true) {
                    if (index$iv$iv2 < size2) {
                        Object item$iv$iv2 = list2.get(index$iv$iv2);
                        it$iv = item$iv$iv2;
                        it = (MeasureAndLayoutDelegate.PostponedRequest) it$iv;
                        if (Intrinsics.areEqual(it.getNode(), $this$consistentLayoutState) || it.getIsLookahead()) {
                            z4 = false;
                        } else {
                            z4 = true;
                        }
                        if (!z4) {
                            break;
                        }
                        index$iv$iv2++;
                    } else {
                        it$iv = null;
                        break;
                    }
                }
                if (it$iv != null) {
                    return true;
                }
            }
            if ($this$consistentLayoutState.getIsDeactivated()) {
                return true;
            }
            if ($this$consistentLayoutState.getMeasurePending$ui()) {
                if (!this.relayoutNodes.contains($this$consistentLayoutState) && $this$consistentLayoutState.getLayoutState$ui() != LayoutNode.LayoutState.LookaheadMeasuring) {
                    if (parent == null && parent.getMeasurePending$ui()) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!z2) {
                        if (parent == null && parent.getLookaheadMeasurePending$ui()) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (!z3 && parentLayoutState != LayoutNode.LayoutState.Measuring) {
                            return false;
                        }
                    }
                }
                return true;
            }
            if ($this$consistentLayoutState.getLayoutPending$ui()) {
                if (!this.relayoutNodes.contains($this$consistentLayoutState) && parent != null && !parent.getMeasurePending$ui() && !parent.getLayoutPending$ui() && parentLayoutState != LayoutNode.LayoutState.Measuring && parentLayoutState != LayoutNode.LayoutState.LayingOut) {
                    list = this.postponedMeasureRequests;
                    index$iv$iv = 0;
                    size = list.size();
                    while (true) {
                        if (index$iv$iv < size) {
                            item$iv$iv = list.get(index$iv$iv);
                            if (Intrinsics.areEqual(((MeasureAndLayoutDelegate.PostponedRequest) item$iv$iv).getNode(), $this$consistentLayoutState)) {
                                z = true;
                                break;
                            }
                            index$iv$iv++;
                        } else {
                            z = false;
                            break;
                        }
                    }
                    if (!z && $this$consistentLayoutState.getLayoutState$ui() != LayoutNode.LayoutState.Measuring && $this$consistentLayoutState.getLayoutState$ui() != LayoutNode.LayoutState.LayingOut) {
                        return false;
                    }
                }
                return true;
            }
        } else if ($this$consistentLayoutState.getPlaceOrder$ui() != Integer.MAX_VALUE) {
            if (parent != null && parent.isPlaced()) {
                if ($this$consistentLayoutState.getMeasurePending$ui()) {
                    list2 = this.postponedMeasureRequests;
                    index$iv$iv2 = 0;
                    size2 = list2.size();
                    while (true) {
                        if (index$iv$iv2 < size2) {
                            Object item$iv$iv3 = list2.get(index$iv$iv2);
                            it$iv = item$iv$iv3;
                            it = (MeasureAndLayoutDelegate.PostponedRequest) it$iv;
                            if (Intrinsics.areEqual(it.getNode(), $this$consistentLayoutState)) {
                                z4 = false;
                            } else {
                                z4 = false;
                            }
                            if (!z4) {
                                break;
                                break;
                            }
                            index$iv$iv2++;
                        } else {
                            it$iv = null;
                            break;
                        }
                    }
                    if (it$iv != null) {
                        return true;
                    }
                }
                if ($this$consistentLayoutState.getIsDeactivated()) {
                    return true;
                }
                if ($this$consistentLayoutState.getMeasurePending$ui()) {
                    if (!this.relayoutNodes.contains($this$consistentLayoutState)) {
                        if (parent == null) {
                            z2 = false;
                        } else {
                            z2 = false;
                        }
                        if (!z2) {
                            if (parent == null) {
                                z3 = false;
                            } else {
                                z3 = false;
                            }
                            if (!z3) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                if ($this$consistentLayoutState.getLayoutPending$ui()) {
                    if (!this.relayoutNodes.contains($this$consistentLayoutState)) {
                        list = this.postponedMeasureRequests;
                        index$iv$iv = 0;
                        size = list.size();
                        while (true) {
                            if (index$iv$iv < size) {
                                item$iv$iv = list.get(index$iv$iv);
                                if (Intrinsics.areEqual(((MeasureAndLayoutDelegate.PostponedRequest) item$iv$iv).getNode(), $this$consistentLayoutState)) {
                                    z = true;
                                    break;
                                }
                                index$iv$iv++;
                            } else {
                                z = false;
                                break;
                            }
                        }
                        if (!z) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        if (Intrinsics.areEqual((Object) $this$consistentLayoutState.isPlacedInLookahead(), (Object) true)) {
            if (!$this$consistentLayoutState.getLookaheadMeasurePending$ui()) {
                z5 = true;
            } else {
                List<MeasureAndLayoutDelegate.PostponedRequest> list3 = this.postponedMeasureRequests;
                int index$iv$iv3 = 0;
                int size3 = list3.size();
                while (true) {
                    if (index$iv$iv3 < size3) {
                        Object item$iv$iv4 = list3.get(index$iv$iv3);
                        MeasureAndLayoutDelegate.PostponedRequest it2 = (MeasureAndLayoutDelegate.PostponedRequest) item$iv$iv4;
                        z5 = z6;
                        if (!((Intrinsics.areEqual(it2.getNode(), $this$consistentLayoutState) && it2.getIsLookahead()) ? z5 : false)) {
                            index$iv$iv3++;
                            z6 = z5;
                        } else {
                            obj = item$iv$iv4;
                            break;
                        }
                    } else {
                        z5 = z6;
                        obj = null;
                        break;
                    }
                }
                if (obj != null) {
                    return z5;
                }
            }
            if (!$this$consistentLayoutState.getLookaheadMeasurePending$ui()) {
                return !$this$consistentLayoutState.getLookaheadLayoutPending$ui() || this.relayoutNodes.contains($this$consistentLayoutState, true) || parent == null || parent.getLookaheadMeasurePending$ui() || parent.getLookaheadLayoutPending$ui() || parentLayoutState == LayoutNode.LayoutState.LookaheadMeasuring || parentLayoutState == LayoutNode.LayoutState.LookaheadLayingOut || (parent.getLayoutPending$ui() && Intrinsics.areEqual($this$consistentLayoutState.getLookaheadRoot(), $this$consistentLayoutState));
            }
            boolean z7 = z5;
            if (!this.relayoutNodes.contains($this$consistentLayoutState, z7)) {
                if (!(parent != null && parent.getLookaheadMeasurePending$ui() == z7) && parentLayoutState != LayoutNode.LayoutState.LookaheadMeasuring) {
                    if (!(parent != null && parent.getMeasurePending$ui()) || !Intrinsics.areEqual($this$consistentLayoutState.getLookaheadRoot(), $this$consistentLayoutState)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return true;
    }

    private final String nodeToString(LayoutNode node) {
        StringBuilder $this$nodeToString_u24lambda_u240 = new StringBuilder();
        $this$nodeToString_u24lambda_u240.append(node);
        $this$nodeToString_u24lambda_u240.append(new StringBuilder().append(AbstractJsonLexerKt.BEGIN_LIST).append(node.getLayoutState$ui()).append(AbstractJsonLexerKt.END_LIST).toString());
        if (!node.isPlaced()) {
            $this$nodeToString_u24lambda_u240.append("[!isPlaced]");
        }
        $this$nodeToString_u24lambda_u240.append("[measuredByParent=" + node.getMeasuredByParent$ui() + AbstractJsonLexerKt.END_LIST);
        if (!consistentLayoutState(node)) {
            $this$nodeToString_u24lambda_u240.append("[INCONSISTENT]");
        }
        return $this$nodeToString_u24lambda_u240.toString();
    }

    private final String logTree() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder sbAppend = stringBuilder.append("Tree state:");
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(...)");
        Intrinsics.checkNotNullExpressionValue(sbAppend.append('\n'), "append(...)");
        logTree$printSubTree(this, stringBuilder, this.root, 0);
        return stringBuilder.toString();
    }

    private static final void logTree$printSubTree(LayoutTreeConsistencyChecker this$0, StringBuilder stringBuilder, LayoutNode node, int depth) {
        int childrenDepth = depth;
        String nodeRepresentation = this$0.nodeToString(node);
        if (nodeRepresentation.length() > 0) {
            for (int i = 0; i < depth; i++) {
                stringBuilder.append("..");
            }
            StringBuilder sbAppend = stringBuilder.append(nodeRepresentation);
            Intrinsics.checkNotNullExpressionValue(sbAppend, "append(...)");
            Intrinsics.checkNotNullExpressionValue(sbAppend.append('\n'), "append(...)");
            childrenDepth++;
        }
        List<LayoutNode> children$ui = node.getChildren$ui();
        int size = children$ui.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = children$ui.get(index$iv);
            LayoutNode it = (LayoutNode) item$iv;
            logTree$printSubTree(this$0, stringBuilder, it, childrenDepth);
        }
    }
}
