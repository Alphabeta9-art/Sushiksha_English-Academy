package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.layout.BeyondBoundsLayoutKt;
import androidx.compose.ui.layout.BeyondBoundsLayoutProviderModifierNode;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.semantics.SemanticsInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DelegatableNode.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a3\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a\u0016\u0010\f\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a\"\u0010\u0011\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u000b0\u000e2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a1\u0010\u0013\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a1\u0010\u0014\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\nH\u0080\b\u001a)\u0010\u0015\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a3\u0010\u0015\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a)\u0010\u0016\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00050\nH\u0080\b\u001a>\u0010\u0017\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001b\u0010\u001c\u001a>\u0010\u0015\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001d\u0010\u001c\u001a>\u0010\u0016\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001e\u0010\u001c\u001aH\u0010\u0004\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\b\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b\u001f\u0010 \u001aJ\u0010!\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b#\u0010$\u001a<\u0010%\u001a\n\u0012\u0004\u0012\u0002H\u0018\u0018\u00010&\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\b\u001a\u00020\u0001H\u0080\b¢\u0006\u0004\b'\u0010(\u001a<\u0010)\u001a\n\u0012\u0004\u0012\u0002H\u0018\u0018\u00010*\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\b\u001a\u00020\u0001H\u0080\b¢\u0006\u0004\b+\u0010,\u001a0\u0010\f\u001a\u0004\u0018\u0001H\u0018\"\n\b\u0000\u0010\u0018\u0018\u0001*\u00020-*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0080\b¢\u0006\u0004\b.\u0010/\u001aH\u0010\u0013\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b0\u0010 \u001aH\u00101\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b2\u0010 \u001aH\u0010\u0014\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00010\nH\u0080\b¢\u0006\u0004\b3\u0010 \u001aH\u00104\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\b5\u0010 \u001a\u001f\u00106\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0000¢\u0006\u0004\b7\u00108\u001a\u001f\u00109\u001a\u00020:*\u00020\u00022\n\u0010;\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0000¢\u0006\u0004\b<\u0010=\u001a\f\u0010>\u001a\u00020\u000f*\u00020\u0002H\u0000\u001a\f\u0010?\u001a\u00020@*\u00020\u0002H\u0000\u001a\f\u0010A\u001a\u00020B*\u00020\u0002H\u0000\u001a\n\u0010C\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010D\u001a\u00020E*\u00020\u0002\u001a\n\u0010F\u001a\u00020G*\u00020\u0002\u001a\n\u0010H\u001a\u00020I*\u00020\u0002\u001a\n\u0010J\u001a\u00020K*\u00020\u0002\u001a\n\u0010L\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010M\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010N\u001a\u00020\u0005*\u00020\u0002\u001a\u0019\u0010O\u001a\u00020\u0005*\u00020\u00022\u0006\u0010P\u001a\u00020Q¢\u0006\u0004\bR\u0010S\u001a\f\u0010T\u001a\u0004\u0018\u00010U*\u00020\u0002\u001a\u000e\u0010V\u001a\u0004\u0018\u00010W*\u00020\u000bH\u0000\u001a>\u0010X\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0018\u0018\u0001*\u00020\u000b2\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001a2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u00020\u00050\nH\u0080\b¢\u0006\u0004\bY\u0010Z\u001a\u0016\u0010[\u001a\u0004\u0018\u00010\u000b*\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000eH\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\\"}, d2 = {"isDelegationRoot", "", "Landroidx/compose/ui/node/DelegatableNode;", "(Landroidx/compose/ui/node/DelegatableNode;)Z", "visitAncestors", "", "mask", "", "includeSelf", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/Modifier$Node;", "nearestAncestor", "getChildren", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/node/LayoutNode;", "zOrder", "addLayoutNodeChildren", "node", "visitChildren", "visitSubtreeIf", "visitLocalDescendants", "visitLocalAncestors", "visitSelfAndLocalDescendants", ExifInterface.GPS_DIRECTION_TRUE, "type", "Landroidx/compose/ui/node/NodeKind;", "visitSelfAndLocalDescendants-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;ILkotlin/jvm/functions/Function1;)V", "visitLocalDescendants-6rFNWt0", "visitLocalAncestors-6rFNWt0", "visitAncestors-Y-YKmho", "(Landroidx/compose/ui/node/DelegatableNode;IZLkotlin/jvm/functions/Function1;)V", "visitSelfAndAncestors", "untilType", "visitSelfAndAncestors-5BbP62I", "(Landroidx/compose/ui/node/DelegatableNode;IILkotlin/jvm/functions/Function1;)V", "ancestors", "", "ancestors-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;IZ)Ljava/util/List;", "setOfAncestors", "", "setOfAncestors-6rFNWt0", "(Landroidx/compose/ui/node/DelegatableNode;IZ)Ljava/util/Set;", "", "nearestAncestor-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/lang/Object;", "visitChildren-Y-YKmho", "visitSelfAndChildren", "visitSelfAndChildren-Y-YKmho", "visitSubtreeIf-Y-YKmho", "visitSubtree", "visitSubtree-Y-YKmho", "has", "has-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Z", "requireCoordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "kind", "requireCoordinator-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Landroidx/compose/ui/node/NodeCoordinator;", "requireLayoutNode", "requireSemanticsInfo", "Landroidx/compose/ui/semantics/SemanticsInfo;", "requireOwner", "Landroidx/compose/ui/node/Owner;", "requestAutofill", "requireDensity", "Landroidx/compose/ui/unit/Density;", "requireGraphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "requireLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "requireLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "invalidateSubtree", "invalidateMeasurementForSubtree", "invalidateDrawForSubtree", "dispatchOnScrollChanged", "delta", "Landroidx/compose/ui/geometry/Offset;", "dispatchOnScrollChanged-Uv8p0NA", "(Landroidx/compose/ui/node/DelegatableNode;J)V", "findNearestBeyondBoundsLayoutAncestor", "Landroidx/compose/ui/layout/BeyondBoundsLayout;", "asLayoutModifierNode", "Landroidx/compose/ui/node/LayoutModifierNode;", "dispatchForKind", "dispatchForKind-6rFNWt0", "(Landroidx/compose/ui/Modifier$Node;ILkotlin/jvm/functions/Function1;)V", "pop", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DelegatableNodeKt {
    public static final boolean isDelegationRoot(DelegatableNode $this$isDelegationRoot) {
        return $this$isDelegationRoot.getNode() == $this$isDelegationRoot;
    }

    public static /* synthetic */ void visitAncestors$default(DelegatableNode $this$visitAncestors_u24default, int mask, boolean includeSelf, Function1 block, int i, Object obj) {
        NodeChain nodes;
        if ((i & 2) != 0) {
            includeSelf = false;
        }
        boolean value$iv = $this$visitAncestors_u24default.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = $this$visitAncestors_u24default.getNode();
        if (!includeSelf) {
            node = node.getParent();
        }
        LayoutNode layout = requireLayoutNode($this$visitAncestors_u24default);
        while (layout != null) {
            Modifier.Node head = layout.getNodes().getHead();
            if ((head.getAggregateChildKindSet() & mask) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & mask) != 0) {
                        block.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layout = layout.getParent$ui();
            node = (layout == null || (nodes = layout.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static final void visitAncestors(DelegatableNode $this$visitAncestors, int mask, boolean includeSelf, Function1<? super Modifier.Node, Unit> function1) {
        NodeChain nodes;
        boolean value$iv = $this$visitAncestors.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node = $this$visitAncestors.getNode();
        if (!includeSelf) {
            node = node.getParent();
        }
        LayoutNode layout = requireLayoutNode($this$visitAncestors);
        while (layout != null) {
            Modifier.Node head = layout.getNodes().getHead();
            if ((head.getAggregateChildKindSet() & mask) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & mask) != 0) {
                        function1.invoke(node);
                    }
                    node = node.getParent();
                }
            }
            layout = layout.getParent$ui();
            node = (layout == null || (nodes = layout.getNodes()) == null) ? null : nodes.getTail();
        }
    }

    public static final Modifier.Node nearestAncestor(DelegatableNode $this$nearestAncestor, int mask) {
        NodeChain nodes;
        boolean value$iv = $this$nearestAncestor.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("nearestAncestor called on an unattached node");
        }
        Modifier.Node node = $this$nearestAncestor.getNode().getParent();
        LayoutNode layout = requireLayoutNode($this$nearestAncestor);
        while (true) {
            Modifier.Node tail = null;
            if (layout == null) {
                return null;
            }
            Modifier.Node head = layout.getNodes().getHead();
            if ((head.getAggregateChildKindSet() & mask) != 0) {
                while (node != null) {
                    if ((node.getKindSet() & mask) != 0) {
                        return node;
                    }
                    node = node.getParent();
                }
            }
            layout = layout.getParent$ui();
            if (layout != null && (nodes = layout.getNodes()) != null) {
                tail = nodes.getTail();
            }
            node = tail;
        }
    }

    private static final MutableVector<LayoutNode> getChildren(LayoutNode $this$getChildren, boolean zOrder) {
        if (zOrder) {
            return $this$getChildren.getZSortedChildren();
        }
        return $this$getChildren.get_children$ui();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addLayoutNodeChildren(MutableVector<Modifier.Node> mutableVector, Modifier.Node node, boolean zOrder) {
        MutableVector<LayoutNode> children = getChildren(requireLayoutNode(node), zOrder);
        int i$iv = children.getSize() - 1;
        Object[] content$iv = children.content;
        if (i$iv >= content$iv.length) {
            return;
        }
        while (i$iv >= 0) {
            LayoutNode it = (LayoutNode) content$iv[i$iv];
            mutableVector.add(it.getNodes().getHead());
            i$iv--;
        }
    }

    public static final void visitChildren(DelegatableNode $this$visitChildren, int mask, boolean zOrder, Function1<? super Modifier.Node, Unit> function1) {
        boolean value$iv = $this$visitChildren.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = $this$visitChildren.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(branches, $this$visitChildren.getNode(), zOrder);
        } else {
            branches.add(child);
        }
        while (true) {
            if (branches.getSize() != 0) {
                Modifier.Node branch = (Modifier.Node) branches.removeAt(branches.getSize() - 1);
                if ((branch.getAggregateChildKindSet() & mask) == 0) {
                    addLayoutNodeChildren(branches, branch, zOrder);
                } else {
                    for (Modifier.Node node = branch; node != null; node = node.getChild()) {
                        if ((node.getKindSet() & mask) != 0) {
                            function1.invoke(node);
                            break;
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    public static final void visitSubtreeIf(DelegatableNode $this$visitSubtreeIf, int mask, boolean zOrder, Function1<? super Modifier.Node, Boolean> function1) {
        boolean value$iv = $this$visitSubtreeIf.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector branches = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = $this$visitSubtreeIf.getNode().getChild();
        if (child == null) {
            addLayoutNodeChildren(branches, $this$visitSubtreeIf.getNode(), zOrder);
        } else {
            branches.add(child);
        }
        while (true) {
            if (branches.getSize() != 0) {
                Modifier.Node branch = (Modifier.Node) branches.removeAt(branches.getSize() - 1);
                if ((branch.getAggregateChildKindSet() & mask) != 0) {
                    Modifier.Node node = branch;
                    while (true) {
                        if (node != null && node.getIsAttached()) {
                            if ((node.getKindSet() & mask) != 0) {
                                boolean diveDeeper = function1.invoke(node).booleanValue();
                                if (!diveDeeper) {
                                    break;
                                }
                            }
                            node = node.getChild();
                        }
                    }
                }
                addLayoutNodeChildren(branches, branch, zOrder);
            } else {
                return;
            }
        }
    }

    public static final void visitLocalDescendants(DelegatableNode $this$visitLocalDescendants, int mask, Function1<? super Modifier.Node, Unit> function1) {
        boolean value$iv$iv = $this$visitLocalDescendants.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv = $this$visitLocalDescendants.getNode();
        if ((self$iv.getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node next$iv = self$iv.getChild(); next$iv != null; next$iv = next$iv.getChild()) {
            if ((next$iv.getKindSet() & mask) != 0) {
                function1.invoke(next$iv);
            }
        }
    }

    public static /* synthetic */ void visitLocalDescendants$default(DelegatableNode $this$visitLocalDescendants_u24default, int mask, boolean includeSelf, Function1 block, int i, Object obj) {
        if ((i & 2) != 0) {
            includeSelf = false;
        }
        boolean value$iv = $this$visitLocalDescendants_u24default.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self = $this$visitLocalDescendants_u24default.getNode();
        if ((self.getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node next = includeSelf ? self : self.getChild(); next != null; next = next.getChild()) {
            if ((next.getKindSet() & mask) != 0) {
                block.invoke(next);
            }
        }
    }

    public static final void visitLocalDescendants(DelegatableNode $this$visitLocalDescendants, int mask, boolean includeSelf, Function1<? super Modifier.Node, Unit> function1) {
        boolean value$iv = $this$visitLocalDescendants.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self = $this$visitLocalDescendants.getNode();
        if ((self.getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node next = includeSelf ? self : self.getChild(); next != null; next = next.getChild()) {
            if ((next.getKindSet() & mask) != 0) {
                function1.invoke(next);
            }
        }
    }

    public static final void visitLocalAncestors(DelegatableNode $this$visitLocalAncestors, int mask, Function1<? super Modifier.Node, Unit> function1) {
        boolean value$iv = $this$visitLocalAncestors.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
        }
        for (Modifier.Node next = $this$visitLocalAncestors.getNode().getParent(); next != null; next = next.getParent()) {
            if ((next.getKindSet() & mask) != 0) {
                function1.invoke(next);
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndLocalDescendants-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m6724visitSelfAndLocalDescendants6rFNWt0(DelegatableNode $this$visitSelfAndLocalDescendants_u2d6rFNWt0, int i, Function1<? super T, Unit> function1) {
        int i2;
        boolean includeSelf$iv;
        DelegatableNode $this$visitLocalDescendants$iv;
        int mask$iv;
        boolean includeSelf$iv2;
        Object mutableVector;
        int i3 = 0;
        boolean includeSelf$iv3 = true;
        DelegatableNode $this$visitLocalDescendants$iv2 = $this$visitSelfAndLocalDescendants_u2d6rFNWt0;
        int mask$iv2 = i;
        boolean value$iv$iv = $this$visitLocalDescendants$iv2.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv = $this$visitLocalDescendants$iv2.getNode();
        if ((self$iv.getAggregateChildKindSet() & mask$iv2) != 0) {
            Modifier.Node next$iv = self$iv;
            while (next$iv != null) {
                if ((next$iv.getKindSet() & mask$iv2) != 0) {
                    Object it = next$iv;
                    Object stack$iv = null;
                    Object node$iv = it;
                    while (node$iv != null) {
                        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                        if (node$iv instanceof Object) {
                            function1.invoke(node$iv);
                            i2 = i3;
                            includeSelf$iv = includeSelf$iv3;
                            $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv2;
                            mask$iv = mask$iv2;
                        } else {
                            Modifier.Node this_$iv$iv = (Modifier.Node) node$iv;
                            i2 = i3;
                            if (((this_$iv$iv.getKindSet() & i) != 0) && (node$iv instanceof DelegatingNode)) {
                                int count$iv = 0;
                                DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv;
                                Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                while (node$iv$iv != null) {
                                    Modifier.Node next$iv2 = node$iv$iv;
                                    if (((next$iv2.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                        count$iv++;
                                        includeSelf$iv2 = includeSelf$iv3;
                                        if (count$iv == 1) {
                                            node$iv = next$iv2;
                                            $this$visitLocalDescendants$iv2 = $this$visitLocalDescendants$iv2;
                                            mask$iv2 = mask$iv2;
                                        } else {
                                            Object obj = (MutableVector) stack$iv;
                                            if (obj == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                mutableVector = obj;
                                            }
                                            stack$iv = mutableVector;
                                            Modifier.Node theNode$iv = (Modifier.Node) node$iv;
                                            if (theNode$iv != null) {
                                                MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(theNode$iv);
                                                }
                                                node$iv = null;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) stack$iv;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(next$iv2);
                                            }
                                        }
                                    } else {
                                        includeSelf$iv2 = includeSelf$iv3;
                                        $this$visitLocalDescendants$iv2 = $this$visitLocalDescendants$iv2;
                                        mask$iv2 = mask$iv2;
                                    }
                                    node$iv$iv = node$iv$iv.getChild();
                                    includeSelf$iv3 = includeSelf$iv2;
                                    $this$visitLocalDescendants$iv2 = $this$visitLocalDescendants$iv2;
                                    mask$iv2 = mask$iv2;
                                }
                                includeSelf$iv = includeSelf$iv3;
                                $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv2;
                                mask$iv = mask$iv2;
                                if (count$iv == 1) {
                                    i3 = i2;
                                    includeSelf$iv3 = includeSelf$iv;
                                    $this$visitLocalDescendants$iv2 = $this$visitLocalDescendants$iv;
                                    mask$iv2 = mask$iv;
                                }
                            } else {
                                includeSelf$iv = includeSelf$iv3;
                                $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv2;
                                mask$iv = mask$iv2;
                            }
                        }
                        node$iv = pop((MutableVector) stack$iv);
                        i3 = i2;
                        includeSelf$iv3 = includeSelf$iv;
                        $this$visitLocalDescendants$iv2 = $this$visitLocalDescendants$iv;
                        mask$iv2 = mask$iv;
                    }
                }
                next$iv = next$iv.getChild();
                i3 = i3;
                includeSelf$iv3 = includeSelf$iv3;
                $this$visitLocalDescendants$iv2 = $this$visitLocalDescendants$iv2;
                mask$iv2 = mask$iv2;
            }
        }
    }

    /* JADX INFO: renamed from: visitLocalDescendants-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m6720visitLocalDescendants6rFNWt0(DelegatableNode $this$visitLocalDescendants_u2d6rFNWt0, int i, Function1<? super T, Unit> function1) {
        int $i$f$visitLocalDescendants;
        Object mutableVector;
        Object node$iv;
        int i2 = 0;
        int mask$iv = i;
        DelegatableNode $this$visitLocalDescendants$iv = $this$visitLocalDescendants_u2d6rFNWt0;
        int $i$f$visitLocalDescendants2 = 0;
        boolean value$iv$iv$iv = $this$visitLocalDescendants$iv.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv$iv = $this$visitLocalDescendants$iv.getNode();
        if ((self$iv$iv.getAggregateChildKindSet() & mask$iv) != 0) {
            Modifier.Node next$iv$iv = self$iv$iv.getChild();
            while (next$iv$iv != null) {
                if ((next$iv$iv.getKindSet() & mask$iv) != 0) {
                    Object it = next$iv$iv;
                    Object stack$iv = null;
                    Object node$iv2 = it;
                    while (node$iv2 != null) {
                        mask$iv = mask$iv;
                        $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv;
                        Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                        if (node$iv2 instanceof Object) {
                            function1.invoke(node$iv2);
                            $i$f$visitLocalDescendants = $i$f$visitLocalDescendants2;
                        } else {
                            Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                            if (((this_$iv$iv.getKindSet() & i) != 0) && (node$iv2 instanceof DelegatingNode)) {
                                int count$iv = 0;
                                DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                while (node$iv$iv != null) {
                                    Modifier.Node next$iv = node$iv$iv;
                                    if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                        count$iv++;
                                        Object node$iv3 = node$iv2;
                                        if (count$iv == 1) {
                                            node$iv2 = next$iv;
                                            $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants2;
                                        } else {
                                            Object node$iv4 = stack$iv;
                                            Object obj = (MutableVector) node$iv4;
                                            if (obj == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                mutableVector = obj;
                                            }
                                            stack$iv = mutableVector;
                                            Modifier.Node theNode$iv = (Modifier.Node) node$iv3;
                                            if (theNode$iv != null) {
                                                MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(theNode$iv);
                                                }
                                                node$iv = null;
                                            } else {
                                                node$iv = node$iv3;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) stack$iv;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(next$iv);
                                            }
                                            node$iv2 = node$iv;
                                            count$iv = count$iv;
                                        }
                                    } else {
                                        $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants2;
                                    }
                                    node$iv$iv = node$iv$iv.getChild();
                                    $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants2;
                                }
                                Object node$iv5 = node$iv2;
                                $i$f$visitLocalDescendants = $i$f$visitLocalDescendants2;
                                if (count$iv == 1) {
                                    node$iv2 = node$iv5;
                                    $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants;
                                }
                            } else {
                                $i$f$visitLocalDescendants = $i$f$visitLocalDescendants2;
                            }
                        }
                        node$iv2 = pop((MutableVector) stack$iv);
                        $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants;
                    }
                }
                next$iv$iv = next$iv$iv.getChild();
                i2 = i2;
                mask$iv = mask$iv;
                $this$visitLocalDescendants$iv = $this$visitLocalDescendants$iv;
                $i$f$visitLocalDescendants2 = $i$f$visitLocalDescendants2;
            }
        }
    }

    /* JADX INFO: renamed from: visitLocalAncestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m6719visitLocalAncestors6rFNWt0(DelegatableNode $this$visitLocalAncestors_u2d6rFNWt0, int i, Function1<? super T, Unit> function1) {
        int i2;
        int mask$iv;
        DelegatableNode $this$visitLocalAncestors$iv;
        int $i$f$visitLocalAncestors;
        int mask$iv2;
        Object mutableVector;
        int i3 = 0;
        int mask$iv3 = i;
        DelegatableNode $this$visitLocalAncestors$iv2 = $this$visitLocalAncestors_u2d6rFNWt0;
        int $i$f$visitLocalAncestors2 = 0;
        boolean value$iv$iv = $this$visitLocalAncestors$iv2.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
        }
        Modifier.Node next$iv = $this$visitLocalAncestors$iv2.getNode().getParent();
        while (next$iv != null) {
            if ((next$iv.getKindSet() & mask$iv3) != 0) {
                Object it = next$iv;
                Object stack$iv = null;
                Object node$iv = it;
                while (node$iv != null) {
                    Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                    if (node$iv instanceof Object) {
                        function1.invoke(node$iv);
                        i2 = i3;
                        mask$iv = mask$iv3;
                        $this$visitLocalAncestors$iv = $this$visitLocalAncestors$iv2;
                        $i$f$visitLocalAncestors = $i$f$visitLocalAncestors2;
                    } else {
                        Modifier.Node this_$iv$iv = (Modifier.Node) node$iv;
                        i2 = i3;
                        if (((this_$iv$iv.getKindSet() & i) != 0) && (node$iv instanceof DelegatingNode)) {
                            int count$iv = 0;
                            DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv;
                            Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                            while (node$iv$iv != null) {
                                Modifier.Node next$iv2 = node$iv$iv;
                                if (((next$iv2.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                    count$iv++;
                                    mask$iv2 = mask$iv3;
                                    if (count$iv == 1) {
                                        node$iv = next$iv2;
                                        $this$visitLocalAncestors$iv2 = $this$visitLocalAncestors$iv2;
                                        $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors2;
                                    } else {
                                        Object obj = (MutableVector) stack$iv;
                                        if (obj == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        } else {
                                            mutableVector = obj;
                                        }
                                        stack$iv = mutableVector;
                                        Modifier.Node theNode$iv = (Modifier.Node) node$iv;
                                        if (theNode$iv != null) {
                                            MutableVector mutableVector2 = (MutableVector) stack$iv;
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(theNode$iv);
                                            }
                                            node$iv = null;
                                        }
                                        MutableVector mutableVector3 = (MutableVector) stack$iv;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(next$iv2);
                                        }
                                    }
                                } else {
                                    mask$iv2 = mask$iv3;
                                    $this$visitLocalAncestors$iv2 = $this$visitLocalAncestors$iv2;
                                    $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors2;
                                }
                                node$iv$iv = node$iv$iv.getChild();
                                mask$iv3 = mask$iv2;
                                $this$visitLocalAncestors$iv2 = $this$visitLocalAncestors$iv2;
                                $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors2;
                            }
                            mask$iv = mask$iv3;
                            $this$visitLocalAncestors$iv = $this$visitLocalAncestors$iv2;
                            $i$f$visitLocalAncestors = $i$f$visitLocalAncestors2;
                            if (count$iv == 1) {
                                i3 = i2;
                                mask$iv3 = mask$iv;
                                $this$visitLocalAncestors$iv2 = $this$visitLocalAncestors$iv;
                                $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors;
                            }
                        } else {
                            mask$iv = mask$iv3;
                            $this$visitLocalAncestors$iv = $this$visitLocalAncestors$iv2;
                            $i$f$visitLocalAncestors = $i$f$visitLocalAncestors2;
                        }
                    }
                    node$iv = pop((MutableVector) stack$iv);
                    i3 = i2;
                    mask$iv3 = mask$iv;
                    $this$visitLocalAncestors$iv2 = $this$visitLocalAncestors$iv;
                    $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors;
                }
            }
            next$iv = next$iv.getParent();
            i3 = i3;
            mask$iv3 = mask$iv3;
            $this$visitLocalAncestors$iv2 = $this$visitLocalAncestors$iv2;
            $i$f$visitLocalAncestors2 = $i$f$visitLocalAncestors2;
        }
    }

    /* JADX INFO: renamed from: visitAncestors-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m6716visitAncestorsYYKmho$default(DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default, int i, boolean includeSelf, Function1 block, int i2, Object obj) {
        boolean includeSelf2;
        int i3;
        boolean includeSelf$iv;
        int mask$iv;
        DelegatableNode $this$visitAncestors$iv;
        NodeChain nodes;
        boolean includeSelf$iv2;
        int mask$iv2;
        DelegatableNode $this$visitAncestors$iv2;
        boolean includeSelf$iv3;
        boolean includeSelf3 = (i2 & 2) != 0 ? false : includeSelf;
        int i4 = 0;
        boolean includeSelf$iv4 = includeSelf3;
        int mask$iv3 = i;
        DelegatableNode $this$visitAncestors$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default;
        boolean value$iv$iv = $this$visitAncestors$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv = $this$visitAncestors$iv3.getNode();
        if (!includeSelf$iv4) {
            node$iv = node$iv.getParent();
        }
        LayoutNode layout$iv = requireLayoutNode($this$visitAncestors$iv3);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv3) != 0) {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv3) != 0) {
                        Modifier.Node it = node$iv;
                        Object stack$iv = null;
                        Modifier.Node nodePop = it;
                        while (nodePop != null) {
                            includeSelf3 = includeSelf3;
                            i4 = i4;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop instanceof Object) {
                                block.invoke(nodePop);
                                includeSelf$iv2 = includeSelf$iv4;
                                mask$iv2 = mask$iv3;
                                $this$visitAncestors$iv2 = $this$visitAncestors$iv3;
                            } else {
                                Modifier.Node this_$iv$iv = nodePop;
                                if (((this_$iv$iv.getKindSet() & i) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv = 0;
                                    DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node next$iv = node$iv$iv;
                                        if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                            count$iv++;
                                            includeSelf$iv3 = includeSelf$iv4;
                                            if (count$iv == 1) {
                                                nodePop = next$iv;
                                                mask$iv3 = mask$iv3;
                                                $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                                            } else {
                                                Object mutableVector = (MutableVector) stack$iv;
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = nodePop;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                                count$iv = count$iv;
                                            }
                                        } else {
                                            includeSelf$iv3 = includeSelf$iv4;
                                            mask$iv3 = mask$iv3;
                                            $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        includeSelf$iv4 = includeSelf$iv3;
                                        mask$iv3 = mask$iv3;
                                        $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                                    }
                                    includeSelf$iv2 = includeSelf$iv4;
                                    mask$iv2 = mask$iv3;
                                    $this$visitAncestors$iv2 = $this$visitAncestors$iv3;
                                    if (count$iv == 1) {
                                        includeSelf$iv4 = includeSelf$iv2;
                                        mask$iv3 = mask$iv2;
                                        $this$visitAncestors$iv3 = $this$visitAncestors$iv2;
                                    }
                                } else {
                                    includeSelf$iv2 = includeSelf$iv4;
                                    mask$iv2 = mask$iv3;
                                    $this$visitAncestors$iv2 = $this$visitAncestors$iv3;
                                }
                            }
                            nodePop = pop((MutableVector) stack$iv);
                            includeSelf$iv4 = includeSelf$iv2;
                            mask$iv3 = mask$iv2;
                            $this$visitAncestors$iv3 = $this$visitAncestors$iv2;
                        }
                    }
                    node$iv = node$iv.getParent();
                    includeSelf3 = includeSelf3;
                    i4 = i4;
                    includeSelf$iv4 = includeSelf$iv4;
                    mask$iv3 = mask$iv3;
                    $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                }
                includeSelf2 = includeSelf3;
                i3 = i4;
                includeSelf$iv = includeSelf$iv4;
                mask$iv = mask$iv3;
                $this$visitAncestors$iv = $this$visitAncestors$iv3;
            } else {
                includeSelf2 = includeSelf3;
                i3 = i4;
                includeSelf$iv = includeSelf$iv4;
                mask$iv = mask$iv3;
                $this$visitAncestors$iv = $this$visitAncestors$iv3;
            }
            layout$iv = layout$iv.getParent$ui();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? null : nodes.getTail();
            includeSelf3 = includeSelf2;
            i4 = i3;
            includeSelf$iv4 = includeSelf$iv;
            mask$iv3 = mask$iv;
            $this$visitAncestors$iv3 = $this$visitAncestors$iv;
        }
    }

    /* JADX INFO: renamed from: visitAncestors-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6715visitAncestorsYYKmho(DelegatableNode $this$visitAncestors_u2dY_u2dYKmho, int i, boolean includeSelf, Function1<? super T, Unit> function1) {
        int i2;
        boolean includeSelf$iv;
        int mask$iv;
        DelegatableNode $this$visitAncestors$iv;
        NodeChain nodes;
        boolean includeSelf$iv2;
        int mask$iv2;
        DelegatableNode $this$visitAncestors$iv2;
        boolean includeSelf$iv3;
        Object mutableVector;
        int i3 = 0;
        boolean includeSelf$iv4 = includeSelf;
        int mask$iv3 = i;
        DelegatableNode $this$visitAncestors$iv3 = $this$visitAncestors_u2dY_u2dYKmho;
        boolean value$iv$iv = $this$visitAncestors$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv = $this$visitAncestors$iv3.getNode();
        if (!includeSelf$iv4) {
            node$iv = node$iv.getParent();
        }
        LayoutNode layout$iv = requireLayoutNode($this$visitAncestors$iv3);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv3) != 0) {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv3) != 0) {
                        Object it = node$iv;
                        Object stack$iv = null;
                        Object node$iv2 = it;
                        while (node$iv2 != null) {
                            i3 = i3;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (node$iv2 instanceof Object) {
                                function1.invoke(node$iv2);
                                includeSelf$iv2 = includeSelf$iv4;
                                mask$iv2 = mask$iv3;
                                $this$visitAncestors$iv2 = $this$visitAncestors$iv3;
                            } else {
                                Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                if (((this_$iv$iv.getKindSet() & i) != 0) && (node$iv2 instanceof DelegatingNode)) {
                                    int count$iv = 0;
                                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node next$iv = node$iv$iv;
                                        if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                            count$iv++;
                                            includeSelf$iv3 = includeSelf$iv4;
                                            if (count$iv == 1) {
                                                node$iv2 = next$iv;
                                                mask$iv3 = mask$iv3;
                                                $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                                            } else {
                                                Object obj = (MutableVector) stack$iv;
                                                if (obj == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj;
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    node$iv2 = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                            }
                                        } else {
                                            includeSelf$iv3 = includeSelf$iv4;
                                            mask$iv3 = mask$iv3;
                                            $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        includeSelf$iv4 = includeSelf$iv3;
                                        mask$iv3 = mask$iv3;
                                        $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                                    }
                                    includeSelf$iv2 = includeSelf$iv4;
                                    mask$iv2 = mask$iv3;
                                    $this$visitAncestors$iv2 = $this$visitAncestors$iv3;
                                    if (count$iv == 1) {
                                        includeSelf$iv4 = includeSelf$iv2;
                                        mask$iv3 = mask$iv2;
                                        $this$visitAncestors$iv3 = $this$visitAncestors$iv2;
                                    }
                                } else {
                                    includeSelf$iv2 = includeSelf$iv4;
                                    mask$iv2 = mask$iv3;
                                    $this$visitAncestors$iv2 = $this$visitAncestors$iv3;
                                }
                            }
                            node$iv2 = pop((MutableVector) stack$iv);
                            includeSelf$iv4 = includeSelf$iv2;
                            mask$iv3 = mask$iv2;
                            $this$visitAncestors$iv3 = $this$visitAncestors$iv2;
                        }
                    }
                    node$iv = node$iv.getParent();
                    i3 = i3;
                    includeSelf$iv4 = includeSelf$iv4;
                    mask$iv3 = mask$iv3;
                    $this$visitAncestors$iv3 = $this$visitAncestors$iv3;
                }
                i2 = i3;
                includeSelf$iv = includeSelf$iv4;
                mask$iv = mask$iv3;
                $this$visitAncestors$iv = $this$visitAncestors$iv3;
            } else {
                i2 = i3;
                includeSelf$iv = includeSelf$iv4;
                mask$iv = mask$iv3;
                $this$visitAncestors$iv = $this$visitAncestors$iv3;
            }
            layout$iv = layout$iv.getParent$ui();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? null : nodes.getTail();
            i3 = i2;
            includeSelf$iv4 = includeSelf$iv;
            mask$iv3 = mask$iv;
            $this$visitAncestors$iv3 = $this$visitAncestors$iv;
        }
    }

    /* JADX INFO: renamed from: visitSelfAndAncestors-5BbP62I, reason: not valid java name */
    public static final /* synthetic */ <T> void m6721visitSelfAndAncestors5BbP62I(DelegatableNode $this$visitSelfAndAncestors_u2d5BbP62I, int i, int i2, Function1<? super T, Unit> function1) {
        int i3;
        Modifier.Node self;
        int mask$iv;
        boolean includeSelf$iv;
        NodeChain nodes;
        Modifier.Node self2;
        int mask$iv2;
        boolean includeSelf$iv2;
        Modifier.Node self3;
        Object mutableVector;
        int i4 = 0;
        Modifier.Node self4 = $this$visitSelfAndAncestors_u2d5BbP62I.getNode();
        int mask$iv3 = i | i2;
        boolean includeSelf$iv3 = true;
        boolean value$iv$iv = $this$visitSelfAndAncestors_u2d5BbP62I.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv = $this$visitSelfAndAncestors_u2d5BbP62I.getNode();
        LayoutNode layout$iv = requireLayoutNode($this$visitSelfAndAncestors_u2d5BbP62I);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv3) == 0) {
                i3 = i4;
                self = self4;
                mask$iv = mask$iv3;
                includeSelf$iv = includeSelf$iv3;
            } else {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv3) != 0) {
                        Modifier.Node it = node$iv;
                        if (it != self4) {
                            if (((it.getKindSet() & i2) != 0 ? 1 : 0) != 0) {
                                return;
                            }
                        }
                        if (((it.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                            Object stack$iv = null;
                            Object node$iv2 = it;
                            while (node$iv2 != null) {
                                i4 = i4;
                                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                                if (node$iv2 instanceof Object) {
                                    function1.invoke(node$iv2);
                                    self2 = self4;
                                    mask$iv2 = mask$iv3;
                                    includeSelf$iv2 = includeSelf$iv3;
                                } else {
                                    Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                    if (!((this_$iv$iv.getKindSet() & i) != 0) || !(node$iv2 instanceof DelegatingNode)) {
                                        self2 = self4;
                                        mask$iv2 = mask$iv3;
                                        includeSelf$iv2 = includeSelf$iv3;
                                    } else {
                                        int count$iv = 0;
                                        DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                        Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                        while (node$iv$iv != null) {
                                            Modifier.Node next$iv = node$iv$iv;
                                            if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) == 0) {
                                                self3 = self4;
                                                mask$iv3 = mask$iv3;
                                                includeSelf$iv3 = includeSelf$iv3;
                                            } else {
                                                count$iv++;
                                                self3 = self4;
                                                if (count$iv == 1) {
                                                    node$iv2 = next$iv;
                                                    mask$iv3 = mask$iv3;
                                                    includeSelf$iv3 = includeSelf$iv3;
                                                } else {
                                                    Object obj = (MutableVector) stack$iv;
                                                    if (obj == null) {
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        mutableVector = obj;
                                                    }
                                                    stack$iv = mutableVector;
                                                    Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                    if (theNode$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv);
                                                        }
                                                        node$iv2 = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv);
                                                    }
                                                }
                                            }
                                            node$iv$iv = node$iv$iv.getChild();
                                            self4 = self3;
                                            mask$iv3 = mask$iv3;
                                            includeSelf$iv3 = includeSelf$iv3;
                                        }
                                        self2 = self4;
                                        mask$iv2 = mask$iv3;
                                        includeSelf$iv2 = includeSelf$iv3;
                                        if (count$iv == 1) {
                                            self4 = self2;
                                            mask$iv3 = mask$iv2;
                                            includeSelf$iv3 = includeSelf$iv2;
                                        }
                                    }
                                }
                                node$iv2 = pop((MutableVector) stack$iv);
                                self4 = self2;
                                mask$iv3 = mask$iv2;
                                includeSelf$iv3 = includeSelf$iv2;
                            }
                        }
                    }
                    node$iv = node$iv.getParent();
                    i4 = i4;
                    self4 = self4;
                    mask$iv3 = mask$iv3;
                    includeSelf$iv3 = includeSelf$iv3;
                }
                i3 = i4;
                self = self4;
                mask$iv = mask$iv3;
                includeSelf$iv = includeSelf$iv3;
            }
            layout$iv = layout$iv.getParent$ui();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? null : nodes.getTail();
            i4 = i3;
            self4 = self;
            mask$iv3 = mask$iv;
            includeSelf$iv3 = includeSelf$iv;
        }
    }

    /* JADX INFO: renamed from: ancestors-6rFNWt0$default, reason: not valid java name */
    public static /* synthetic */ List m6707ancestors6rFNWt0$default(DelegatableNode $this$ancestors_u2d6rFNWt0_u24default, int i, boolean includeSelf, int i2, Object obj) {
        boolean includeSelf2;
        int i3;
        boolean includeSelf$iv;
        NodeChain nodes;
        int i4;
        boolean includeSelf$iv2;
        Object mutableVector;
        Modifier.Node node;
        boolean includeSelf3 = (i2 & 2) != 0 ? false : includeSelf;
        int i5 = 0;
        Object result = null;
        boolean includeSelf$iv3 = includeSelf3;
        boolean value$iv$iv$iv = $this$ancestors_u2d6rFNWt0_u24default.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$ancestors_u2d6rFNWt0_u24default.getNode();
        if (!includeSelf$iv3) {
            node$iv$iv = node$iv$iv.getParent();
        }
        LayoutNode layout$iv$iv = requireLayoutNode($this$ancestors_u2d6rFNWt0_u24default);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & i) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & i) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        Object stack$iv$iv = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            i5 = i5;
                            Object result2 = result;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop instanceof Object) {
                                Modifier.Node node2 = nodePop;
                                Object result3 = result2 == null ? (List) new ArrayList() : result2;
                                ((List) result3).add(node2);
                                includeSelf$iv2 = includeSelf$iv3;
                                result = result3;
                            } else {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    includeSelf$iv2 = includeSelf$iv3;
                                    result = result2;
                                } else {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            Modifier.Node node3 = nodePop;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                includeSelf$iv3 = includeSelf$iv3;
                                            } else {
                                                Object obj2 = (MutableVector) stack$iv$iv;
                                                if (obj2 == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj2;
                                                }
                                                stack$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv = node3;
                                                if (theNode$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    node = null;
                                                } else {
                                                    node = node3;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                nodePop = node;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            includeSelf$iv3 = includeSelf$iv3;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        includeSelf$iv3 = includeSelf$iv3;
                                    }
                                    Modifier.Node node4 = nodePop;
                                    includeSelf$iv2 = includeSelf$iv3;
                                    if (count$iv$iv == 1) {
                                        result = result2;
                                        nodePop = node4;
                                        includeSelf$iv3 = includeSelf$iv2;
                                    } else {
                                        result = result2;
                                    }
                                }
                            }
                            nodePop = pop((MutableVector) stack$iv$iv);
                            includeSelf$iv3 = includeSelf$iv2;
                        }
                        i4 = i5;
                    } else {
                        i4 = i5;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    includeSelf3 = includeSelf3;
                    i5 = i4;
                    includeSelf$iv3 = includeSelf$iv3;
                }
                includeSelf2 = includeSelf3;
                i3 = i5;
                includeSelf$iv = includeSelf$iv3;
            } else {
                includeSelf2 = includeSelf3;
                i3 = i5;
                includeSelf$iv = includeSelf$iv3;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            includeSelf3 = includeSelf2;
            i5 = i3;
            includeSelf$iv3 = includeSelf$iv;
        }
        return (List) result;
    }

    /* JADX INFO: renamed from: ancestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> List<T> m6706ancestors6rFNWt0(DelegatableNode $this$ancestors_u2d6rFNWt0, int i, boolean includeSelf) {
        int i2;
        boolean includeSelf$iv;
        int i3;
        NodeChain nodes;
        int i4;
        Object mutableVector;
        Modifier.Node node;
        int i5 = 0;
        Object result = null;
        boolean includeSelf$iv2 = includeSelf;
        int i6 = i;
        boolean value$iv$iv$iv = $this$ancestors_u2d6rFNWt0.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$ancestors_u2d6rFNWt0.getNode();
        if (!includeSelf$iv2) {
            node$iv$iv = node$iv$iv.getParent();
        }
        LayoutNode layout$iv$iv = requireLayoutNode($this$ancestors_u2d6rFNWt0);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & i6) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & i6) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i7 = i6;
                        Object stack$iv$iv = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            Object result2 = result;
                            includeSelf$iv2 = includeSelf$iv2;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop instanceof Object) {
                                Modifier.Node node2 = nodePop;
                                if (result2 == null) {
                                    Object result3 = new ArrayList();
                                    result2 = (List) result3;
                                }
                                ((List) result2).add(node2);
                                i4 = i6;
                                result = result2;
                            } else {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i7) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    i4 = i6;
                                    result = result2;
                                } else {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i7) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            Modifier.Node node3 = nodePop;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                i6 = i6;
                                            } else {
                                                Object obj = (MutableVector) stack$iv$iv;
                                                if (obj == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj;
                                                }
                                                stack$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv = node3;
                                                if (theNode$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    node = null;
                                                } else {
                                                    node = node3;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                nodePop = node;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            i6 = i6;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        i6 = i6;
                                    }
                                    Modifier.Node node4 = nodePop;
                                    i4 = i6;
                                    if (count$iv$iv == 1) {
                                        result = result2;
                                        nodePop = node4;
                                        i6 = i4;
                                    } else {
                                        result = result2;
                                    }
                                }
                            }
                            nodePop = pop((MutableVector) stack$iv$iv);
                            i6 = i4;
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    i5 = i5;
                    includeSelf$iv2 = includeSelf$iv2;
                    i6 = i6;
                }
                i2 = i5;
                includeSelf$iv = includeSelf$iv2;
                i3 = i6;
            } else {
                i2 = i5;
                includeSelf$iv = includeSelf$iv2;
                i3 = i6;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            i5 = i2;
            includeSelf$iv2 = includeSelf$iv;
            i6 = i3;
        }
        return (List) result;
    }

    /* JADX INFO: renamed from: setOfAncestors-6rFNWt0$default, reason: not valid java name */
    public static /* synthetic */ Set m6714setOfAncestors6rFNWt0$default(DelegatableNode $this$setOfAncestors_u2d6rFNWt0_u24default, int i, boolean includeSelf, int i2, Object obj) {
        boolean includeSelf2;
        int i3;
        boolean includeSelf$iv;
        NodeChain nodes;
        int i4;
        boolean includeSelf$iv2;
        Object mutableVector;
        Modifier.Node node;
        boolean includeSelf3 = (i2 & 2) != 0 ? false : includeSelf;
        int i5 = 0;
        Object result = null;
        boolean includeSelf$iv3 = includeSelf3;
        boolean value$iv$iv$iv = $this$setOfAncestors_u2d6rFNWt0_u24default.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$setOfAncestors_u2d6rFNWt0_u24default.getNode();
        if (!includeSelf$iv3) {
            node$iv$iv = node$iv$iv.getParent();
        }
        LayoutNode layout$iv$iv = requireLayoutNode($this$setOfAncestors_u2d6rFNWt0_u24default);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & i) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & i) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        Object stack$iv$iv = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            i5 = i5;
                            Object result2 = result;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop instanceof Object) {
                                Modifier.Node node2 = nodePop;
                                Object result3 = result2 == null ? (Set) new LinkedHashSet() : result2;
                                ((Set) result3).add(node2);
                                includeSelf$iv2 = includeSelf$iv3;
                                result = result3;
                            } else {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    includeSelf$iv2 = includeSelf$iv3;
                                    result = result2;
                                } else {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            Modifier.Node node3 = nodePop;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                includeSelf$iv3 = includeSelf$iv3;
                                            } else {
                                                Object obj2 = (MutableVector) stack$iv$iv;
                                                if (obj2 == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj2;
                                                }
                                                stack$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv = node3;
                                                if (theNode$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    node = null;
                                                } else {
                                                    node = node3;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                nodePop = node;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            includeSelf$iv3 = includeSelf$iv3;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        includeSelf$iv3 = includeSelf$iv3;
                                    }
                                    Modifier.Node node4 = nodePop;
                                    includeSelf$iv2 = includeSelf$iv3;
                                    if (count$iv$iv == 1) {
                                        result = result2;
                                        nodePop = node4;
                                        includeSelf$iv3 = includeSelf$iv2;
                                    } else {
                                        result = result2;
                                    }
                                }
                            }
                            nodePop = pop((MutableVector) stack$iv$iv);
                            includeSelf$iv3 = includeSelf$iv2;
                        }
                        i4 = i5;
                    } else {
                        i4 = i5;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    includeSelf3 = includeSelf3;
                    i5 = i4;
                    includeSelf$iv3 = includeSelf$iv3;
                }
                includeSelf2 = includeSelf3;
                i3 = i5;
                includeSelf$iv = includeSelf$iv3;
            } else {
                includeSelf2 = includeSelf3;
                i3 = i5;
                includeSelf$iv = includeSelf$iv3;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            includeSelf3 = includeSelf2;
            i5 = i3;
            includeSelf$iv3 = includeSelf$iv;
        }
        return (Set) result;
    }

    /* JADX INFO: renamed from: setOfAncestors-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> Set<T> m6713setOfAncestors6rFNWt0(DelegatableNode $this$setOfAncestors_u2d6rFNWt0, int i, boolean includeSelf) {
        int i2;
        boolean includeSelf$iv;
        int i3;
        NodeChain nodes;
        int i4;
        Object mutableVector;
        Modifier.Node node;
        int i5 = 0;
        Object result = null;
        boolean includeSelf$iv2 = includeSelf;
        int i6 = i;
        boolean value$iv$iv$iv = $this$setOfAncestors_u2d6rFNWt0.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$setOfAncestors_u2d6rFNWt0.getNode();
        if (!includeSelf$iv2) {
            node$iv$iv = node$iv$iv.getParent();
        }
        LayoutNode layout$iv$iv = requireLayoutNode($this$setOfAncestors_u2d6rFNWt0);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & i6) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & i6) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i7 = i6;
                        Object stack$iv$iv = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            Object result2 = result;
                            includeSelf$iv2 = includeSelf$iv2;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop instanceof Object) {
                                Modifier.Node node2 = nodePop;
                                if (result2 == null) {
                                    Object result3 = new LinkedHashSet();
                                    result2 = (Set) result3;
                                }
                                ((Set) result2).add(node2);
                                i4 = i6;
                                result = result2;
                            } else {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i7) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    i4 = i6;
                                    result = result2;
                                } else {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i7) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            Modifier.Node node3 = nodePop;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                i6 = i6;
                                            } else {
                                                Object obj = (MutableVector) stack$iv$iv;
                                                if (obj == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj;
                                                }
                                                stack$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv = node3;
                                                if (theNode$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    node = null;
                                                } else {
                                                    node = node3;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                nodePop = node;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            i6 = i6;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        i6 = i6;
                                    }
                                    Modifier.Node node4 = nodePop;
                                    i4 = i6;
                                    if (count$iv$iv == 1) {
                                        result = result2;
                                        nodePop = node4;
                                        i6 = i4;
                                    } else {
                                        result = result2;
                                    }
                                }
                            }
                            nodePop = pop((MutableVector) stack$iv$iv);
                            i6 = i4;
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    i5 = i5;
                    includeSelf$iv2 = includeSelf$iv2;
                    i6 = i6;
                }
                i2 = i5;
                includeSelf$iv = includeSelf$iv2;
                i3 = i6;
            } else {
                i2 = i5;
                includeSelf$iv = includeSelf$iv2;
                i3 = i6;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            i5 = i2;
            includeSelf$iv2 = includeSelf$iv;
            i6 = i3;
        }
        return (Set) result;
    }

    /* JADX INFO: renamed from: nearestAncestor-64DMado, reason: not valid java name */
    public static final /* synthetic */ <T> T m6711nearestAncestor64DMado(DelegatableNode delegatableNode, int i) {
        int i2;
        int i3;
        DelegatableNode delegatableNode2;
        boolean z;
        int i4;
        Modifier.Node node;
        NodeChain nodes;
        int i5;
        int i6;
        DelegatableNode delegatableNode3;
        boolean z2;
        int i7;
        DelegatableNode delegatableNode4;
        boolean z3;
        int i8;
        DelegatableNode delegatableNode5;
        int i9 = 0;
        int i10 = i;
        DelegatableNode delegatableNode6 = delegatableNode;
        boolean z4 = false;
        int i11 = 0;
        if (!delegatableNode6.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = delegatableNode6.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = requireLayoutNode(delegatableNode6);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i10) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & i10) != 0) {
                        int i12 = i10;
                        Object obj = null;
                        Modifier.Node nodePop = parent;
                        while (nodePop != null) {
                            i9 = i9;
                            i10 = i10;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop instanceof Object) {
                                return (T) nodePop;
                            }
                            if (((nodePop.getKindSet() & i12) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                delegatableNode4 = delegatableNode6;
                                z3 = z4;
                                i8 = i11;
                                nodePop = pop((MutableVector) obj);
                                delegatableNode6 = delegatableNode4;
                                z4 = z3;
                                i11 = i8;
                            } else {
                                int i13 = 0;
                                Modifier.Node delegate$ui = ((DelegatingNode) nodePop).getDelegate();
                                while (delegate$ui != null) {
                                    Modifier.Node node2 = delegate$ui;
                                    if (((node2.getKindSet() & i12) != 0 ? 1 : 0) != 0) {
                                        i13++;
                                        delegatableNode5 = delegatableNode6;
                                        if (i13 == 1) {
                                            nodePop = node2;
                                            z4 = z4;
                                            i11 = i11;
                                        } else {
                                            MutableVector mutableVector = (MutableVector) obj;
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            obj = mutableVector;
                                            Modifier.Node node3 = nodePop;
                                            if (node3 != null) {
                                                MutableVector mutableVector2 = (MutableVector) obj;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(node3);
                                                }
                                                nodePop = null;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) obj;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(node2);
                                            }
                                            i13 = i13;
                                        }
                                    } else {
                                        delegatableNode5 = delegatableNode6;
                                        z4 = z4;
                                        i11 = i11;
                                    }
                                    delegate$ui = delegate$ui.getChild();
                                    delegatableNode6 = delegatableNode5;
                                    z4 = z4;
                                    i11 = i11;
                                }
                                delegatableNode4 = delegatableNode6;
                                z3 = z4;
                                i8 = i11;
                                if (i13 == 1) {
                                    delegatableNode6 = delegatableNode4;
                                    z4 = z3;
                                    i11 = i8;
                                } else {
                                    nodePop = pop((MutableVector) obj);
                                    delegatableNode6 = delegatableNode4;
                                    z4 = z3;
                                    i11 = i8;
                                }
                            }
                        }
                        i5 = i9;
                        i6 = i10;
                        delegatableNode3 = delegatableNode6;
                        z2 = z4;
                        i7 = i11;
                    } else {
                        i5 = i9;
                        i6 = i10;
                        delegatableNode3 = delegatableNode6;
                        z2 = z4;
                        i7 = i11;
                    }
                    parent = parent.getParent();
                    i9 = i5;
                    i10 = i6;
                    delegatableNode6 = delegatableNode3;
                    z4 = z2;
                    i11 = i7;
                }
                i2 = i9;
                i3 = i10;
                delegatableNode2 = delegatableNode6;
                z = z4;
                i4 = i11;
                node = null;
            } else {
                i2 = i9;
                i3 = i10;
                delegatableNode2 = delegatableNode6;
                z = z4;
                i4 = i11;
                node = null;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? node : nodes.getTail();
            i9 = i2;
            i10 = i3;
            delegatableNode6 = delegatableNode2;
            z4 = z;
            i11 = i4;
        }
        return null;
    }

    /* JADX INFO: renamed from: visitChildren-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m6718visitChildrenYYKmho$default(DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default, int i, boolean zOrder, Function1 block, int i2, Object obj) {
        int i3;
        boolean zOrder$iv;
        int mask$iv;
        boolean z;
        int i4;
        Object mutableVector;
        boolean zOrder2 = (i2 & 2) != 0 ? false : zOrder;
        int i5 = 0;
        boolean zOrder$iv2 = zOrder2;
        int mask$iv2 = i;
        boolean value$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        int i6 = 0;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren_u2dY_u2dYKmho_u24default.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren_u2dY_u2dYKmho_u24default.getNode(), zOrder$iv2);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            if ((branches$iv.getSize() != 0 ? 1 : i6) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv2) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv == null) {
                        i5 = i5;
                        break;
                    }
                    if ((node$iv.getKindSet() & mask$iv2) != 0) {
                        Modifier.Node it = node$iv;
                        Object stack$iv = null;
                        Modifier.Node nodePop = it;
                        while (nodePop != null) {
                            zOrder2 = zOrder2;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop instanceof Object) {
                                block.invoke(nodePop);
                                i3 = i5;
                                zOrder$iv = zOrder$iv2;
                                mask$iv = mask$iv2;
                                z = true;
                            } else {
                                Modifier.Node this_$iv$iv = nodePop;
                                if (((this_$iv$iv.getKindSet() & i) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv = 0;
                                    DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node next$iv = node$iv$iv;
                                        if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                            count$iv++;
                                            i4 = i5;
                                            if (count$iv == 1) {
                                                nodePop = next$iv;
                                                zOrder$iv2 = zOrder$iv2;
                                                mask$iv2 = mask$iv2;
                                            } else {
                                                Object obj2 = (MutableVector) stack$iv;
                                                if (obj2 == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj2;
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = nodePop;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                            }
                                        } else {
                                            i4 = i5;
                                            zOrder$iv2 = zOrder$iv2;
                                            mask$iv2 = mask$iv2;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        i5 = i4;
                                        zOrder$iv2 = zOrder$iv2;
                                        mask$iv2 = mask$iv2;
                                    }
                                    i3 = i5;
                                    zOrder$iv = zOrder$iv2;
                                    mask$iv = mask$iv2;
                                    z = true;
                                    if (count$iv == 1) {
                                        i5 = i3;
                                        zOrder$iv2 = zOrder$iv;
                                        mask$iv2 = mask$iv;
                                    }
                                } else {
                                    i3 = i5;
                                    zOrder$iv = zOrder$iv2;
                                    mask$iv = mask$iv2;
                                    z = true;
                                }
                            }
                            nodePop = pop((MutableVector) stack$iv);
                            i5 = i3;
                            zOrder$iv2 = zOrder$iv;
                            mask$iv2 = mask$iv;
                        }
                        i6 = 0;
                        i5 = i5;
                        break;
                    }
                    node$iv = node$iv.getChild();
                    i5 = i5;
                    zOrder2 = zOrder2;
                }
            } else {
                addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv2);
            }
        }
    }

    /* JADX INFO: renamed from: visitChildren-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6717visitChildrenYYKmho(DelegatableNode $this$visitChildren_u2dY_u2dYKmho, int i, boolean zOrder, Function1<? super T, Unit> function1) {
        boolean zOrder$iv;
        int mask$iv;
        DelegatableNode $this$visitChildren$iv;
        boolean z;
        boolean zOrder$iv2;
        Object mutableVector;
        int i2 = 0;
        boolean zOrder$iv3 = zOrder;
        int mask$iv2 = i;
        DelegatableNode $this$visitChildren$iv2 = $this$visitChildren_u2dY_u2dYKmho;
        boolean value$iv$iv = $this$visitChildren$iv2.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        boolean z2 = false;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren$iv2.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren$iv2.getNode(), zOrder$iv3);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            if (!(branches$iv.getSize() != 0 ? true : z2)) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv2) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv == null) {
                        zOrder$iv3 = zOrder$iv3;
                        break;
                    }
                    if ((node$iv.getKindSet() & mask$iv2) != 0) {
                        Object it = node$iv;
                        Object stack$iv = null;
                        Object node$iv2 = it;
                        while (node$iv2 != null) {
                            i2 = i2;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (node$iv2 instanceof Object) {
                                function1.invoke(node$iv2);
                                zOrder$iv = zOrder$iv3;
                                mask$iv = mask$iv2;
                                $this$visitChildren$iv = $this$visitChildren$iv2;
                                z = true;
                            } else {
                                Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                if (((this_$iv$iv.getKindSet() & i) != 0) && (node$iv2 instanceof DelegatingNode)) {
                                    int count$iv = 0;
                                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                    while (node$iv$iv != null) {
                                        Modifier.Node next$iv = node$iv$iv;
                                        if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                            count$iv++;
                                            zOrder$iv2 = zOrder$iv3;
                                            if (count$iv == 1) {
                                                node$iv2 = next$iv;
                                                mask$iv2 = mask$iv2;
                                                $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                            } else {
                                                Object obj = (MutableVector) stack$iv;
                                                if (obj == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj;
                                                }
                                                stack$iv = mutableVector;
                                                Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                if (theNode$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv);
                                                    }
                                                    node$iv2 = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv);
                                                }
                                            }
                                        } else {
                                            zOrder$iv2 = zOrder$iv3;
                                            mask$iv2 = mask$iv2;
                                            $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                        }
                                        node$iv$iv = node$iv$iv.getChild();
                                        zOrder$iv3 = zOrder$iv2;
                                        mask$iv2 = mask$iv2;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                    }
                                    zOrder$iv = zOrder$iv3;
                                    mask$iv = mask$iv2;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                    z = true;
                                    if (count$iv == 1) {
                                        zOrder$iv3 = zOrder$iv;
                                        mask$iv2 = mask$iv;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv;
                                    }
                                } else {
                                    zOrder$iv = zOrder$iv3;
                                    mask$iv = mask$iv2;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                    z = true;
                                }
                            }
                            node$iv2 = pop((MutableVector) stack$iv);
                            zOrder$iv3 = zOrder$iv;
                            mask$iv2 = mask$iv;
                            $this$visitChildren$iv2 = $this$visitChildren$iv;
                        }
                        z2 = false;
                        zOrder$iv3 = zOrder$iv3;
                        break;
                    }
                    node$iv = node$iv.getChild();
                    i2 = i2;
                    zOrder$iv3 = zOrder$iv3;
                }
            } else {
                addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv3);
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndChildren-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m6723visitSelfAndChildrenYYKmho$default(DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default, int i, boolean zOrder, Function1 block, int i2, Object obj) {
        boolean zOrder2;
        int count$iv;
        boolean z;
        boolean zOrder$iv;
        int mask$iv;
        DelegatableNode $this$visitChildren$iv;
        boolean zOrder$iv2;
        Object mutableVector;
        boolean zOrder3;
        int i3;
        block = block;
        if ((i2 & 2) == 0) {
            zOrder2 = zOrder;
        } else {
            zOrder2 = false;
        }
        int i4 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default.getNode();
        Object stack$iv = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv;
        while (true) {
            count$iv = 3;
            z = false;
            int i5 = 1;
            if (nodePop == null) {
                break;
            }
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (nodePop instanceof Object) {
                block.invoke(nodePop);
                zOrder3 = zOrder2;
                i3 = i4;
            } else {
                Modifier.Node this_$iv$iv = nodePop;
                if (!((this_$iv$iv.getKindSet() & i) != 0) || !(nodePop instanceof DelegatingNode)) {
                    zOrder3 = zOrder2;
                    i3 = i4;
                } else {
                    int count$iv2 = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if (((next$iv.getKindSet() & i) != 0 ? i5 : 0) == 0) {
                            zOrder2 = zOrder2;
                            i4 = i4;
                        } else {
                            count$iv2++;
                            if (count$iv2 == i5) {
                                nodePop = next$iv;
                                zOrder2 = zOrder2;
                                i4 = i4;
                            } else {
                                Object obj2 = (MutableVector) stack$iv;
                                if (obj2 == null) {
                                    Object mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    obj2 = mutableVector2;
                                }
                                stack$iv = obj2;
                                Modifier.Node theNode$iv = nodePop;
                                if (theNode$iv != null) {
                                    MutableVector mutableVector3 = (MutableVector) stack$iv;
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(theNode$iv);
                                    }
                                    nodePop = null;
                                }
                                MutableVector mutableVector4 = (MutableVector) stack$iv;
                                if (mutableVector4 != null) {
                                    mutableVector4.add(next$iv);
                                }
                            }
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        zOrder2 = zOrder2;
                        i4 = i4;
                        i5 = 1;
                    }
                    zOrder3 = zOrder2;
                    i3 = i4;
                    if (count$iv2 == 1) {
                        zOrder2 = zOrder3;
                        i4 = i3;
                    }
                }
            }
            nodePop = pop((MutableVector) stack$iv);
            zOrder2 = zOrder3;
            i4 = i3;
        }
        int mask$iv2 = i;
        DelegatableNode $this$visitChildren$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default;
        boolean value$iv$iv = $this$visitChildren$iv2.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren$iv2.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren$iv2.getNode(), zOrder2);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            if (!(branches$iv.getSize() != 0 ? true : z)) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv2) == 0) {
                addLayoutNodeChildren(branches$iv, branch$iv, zOrder2);
            } else {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv == null) {
                        boolean zOrder$iv3 = zOrder2;
                        block = block;
                        zOrder2 = zOrder$iv3;
                        count$iv = 3;
                        break;
                    }
                    if ((node$iv.getKindSet() & mask$iv2) != 0) {
                        Modifier.Node it = node$iv;
                        Object stack$iv2 = null;
                        Modifier.Node nodePop2 = it;
                        while (nodePop2 != null) {
                            Intrinsics.reifiedOperationMarker(count$iv, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop2 instanceof Object) {
                                block.invoke(nodePop2);
                                zOrder$iv = zOrder2;
                                mask$iv = mask$iv2;
                                $this$visitChildren$iv = $this$visitChildren$iv2;
                            } else {
                                Modifier.Node this_$iv$iv3 = nodePop2;
                                if (!((this_$iv$iv3.getKindSet() & i) != 0) || !(nodePop2 instanceof DelegatingNode)) {
                                    zOrder$iv = zOrder2;
                                    mask$iv = mask$iv2;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                } else {
                                    int count$iv3 = 0;
                                    DelegatingNode this_$iv$iv4 = (DelegatingNode) nodePop2;
                                    Modifier.Node node$iv$iv2 = this_$iv$iv4.getDelegate();
                                    while (node$iv$iv2 != null) {
                                        Modifier.Node next$iv2 = node$iv$iv2;
                                        if (((next$iv2.getKindSet() & i) != 0 ? 1 : 0) == 0) {
                                            zOrder$iv2 = zOrder2;
                                            mask$iv2 = mask$iv2;
                                            $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                        } else {
                                            count$iv3++;
                                            zOrder$iv2 = zOrder2;
                                            if (count$iv3 == 1) {
                                                nodePop2 = next$iv2;
                                                mask$iv2 = mask$iv2;
                                                $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                            } else {
                                                Object obj3 = (MutableVector) stack$iv2;
                                                if (obj3 == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj3;
                                                }
                                                stack$iv2 = mutableVector;
                                                Modifier.Node theNode$iv2 = nodePop2;
                                                if (theNode$iv2 != null) {
                                                    MutableVector mutableVector5 = (MutableVector) stack$iv2;
                                                    if (mutableVector5 != null) {
                                                        mutableVector5.add(theNode$iv2);
                                                    }
                                                    nodePop2 = null;
                                                }
                                                MutableVector mutableVector6 = (MutableVector) stack$iv2;
                                                if (mutableVector6 != null) {
                                                    mutableVector6.add(next$iv2);
                                                }
                                            }
                                        }
                                        node$iv$iv2 = node$iv$iv2.getChild();
                                        zOrder2 = zOrder$iv2;
                                        mask$iv2 = mask$iv2;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                    }
                                    zOrder$iv = zOrder2;
                                    mask$iv = mask$iv2;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                    if (count$iv3 == 1) {
                                        zOrder2 = zOrder$iv;
                                        mask$iv2 = mask$iv;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv;
                                        count$iv = 3;
                                    }
                                }
                            }
                            nodePop2 = pop((MutableVector) stack$iv2);
                            zOrder2 = zOrder$iv;
                            mask$iv2 = mask$iv;
                            $this$visitChildren$iv2 = $this$visitChildren$iv;
                            count$iv = 3;
                        }
                        boolean zOrder$iv4 = zOrder2;
                        block = block;
                        z = false;
                        zOrder2 = zOrder$iv4;
                        count$iv = 3;
                        break;
                    }
                    boolean zOrder$iv5 = zOrder2;
                    node$iv = node$iv.getChild();
                    block = block;
                    zOrder2 = zOrder$iv5;
                    count$iv = 3;
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitSelfAndChildren-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6722visitSelfAndChildrenYYKmho(DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho, int i, boolean zOrder, Function1<? super T, Unit> function1) {
        int count$iv;
        boolean z;
        boolean zOrder$iv;
        int mask$iv;
        DelegatableNode $this$visitChildren$iv;
        boolean zOrder$iv2;
        Object mutableVector;
        int i2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        function1 = function1;
        int i3 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho.getNode();
        Object stack$iv = null;
        Modifier.Node node$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
        while (true) {
            count$iv = 3;
            z = false;
            int i4 = 1;
            if (node$iv == null) {
                break;
            }
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (node$iv instanceof Object) {
                function1.invoke(node$iv);
                i2 = i3;
                $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
            } else {
                Modifier.Node this_$iv$iv = node$iv;
                if (!((this_$iv$iv.getKindSet() & i) != 0) || !(node$iv instanceof DelegatingNode)) {
                    i2 = i3;
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
                } else {
                    int count$iv2 = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if (((next$iv.getKindSet() & i) != 0 ? i4 : 0) == 0) {
                            i3 = i3;
                            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        } else {
                            count$iv2++;
                            if (count$iv2 == i4) {
                                node$iv = next$iv;
                                i3 = i3;
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                            } else {
                                Object obj = (MutableVector) stack$iv;
                                if (obj == null) {
                                    Object mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                    obj = mutableVector2;
                                }
                                stack$iv = obj;
                                Modifier.Node theNode$iv = node$iv;
                                if (theNode$iv != null) {
                                    MutableVector mutableVector3 = (MutableVector) stack$iv;
                                    if (mutableVector3 != null) {
                                        mutableVector3.add(theNode$iv);
                                    }
                                    node$iv = null;
                                }
                                MutableVector mutableVector4 = (MutableVector) stack$iv;
                                if (mutableVector4 != null) {
                                    mutableVector4.add(next$iv);
                                }
                            }
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        i3 = i3;
                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                        i4 = 1;
                    }
                    i2 = i3;
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv2;
                    if (count$iv2 == 1) {
                        i3 = i2;
                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv;
                    }
                }
            }
            node$iv = pop((MutableVector) stack$iv);
            i3 = i2;
            $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv;
        }
        boolean zOrder$iv3 = zOrder;
        int mask$iv2 = i;
        DelegatableNode $this$visitChildren$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho;
        boolean value$iv$iv = $this$visitChildren$iv2.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitChildren$iv2.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitChildren$iv2.getNode(), zOrder$iv3);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            if (!(branches$iv.getSize() != 0 ? true : z)) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv2) == 0) {
                addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv3);
            } else {
                Modifier.Node node$iv2 = branch$iv;
                while (true) {
                    if (node$iv2 == null) {
                        function1 = function1;
                        zOrder$iv3 = zOrder$iv3;
                        count$iv = 3;
                        break;
                    }
                    if ((node$iv2.getKindSet() & mask$iv2) != 0) {
                        Object it = node$iv2;
                        Object stack$iv2 = null;
                        Object node$iv3 = it;
                        while (node$iv3 != null) {
                            Intrinsics.reifiedOperationMarker(count$iv, ExifInterface.GPS_DIRECTION_TRUE);
                            if (node$iv3 instanceof Object) {
                                function1.invoke(node$iv3);
                                zOrder$iv = zOrder$iv3;
                                mask$iv = mask$iv2;
                                $this$visitChildren$iv = $this$visitChildren$iv2;
                            } else {
                                Modifier.Node this_$iv$iv3 = (Modifier.Node) node$iv3;
                                if (!((this_$iv$iv3.getKindSet() & i) != 0) || !(node$iv3 instanceof DelegatingNode)) {
                                    zOrder$iv = zOrder$iv3;
                                    mask$iv = mask$iv2;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                } else {
                                    int count$iv3 = 0;
                                    DelegatingNode this_$iv$iv4 = (DelegatingNode) node$iv3;
                                    Modifier.Node node$iv$iv2 = this_$iv$iv4.getDelegate();
                                    while (node$iv$iv2 != null) {
                                        Modifier.Node next$iv2 = node$iv$iv2;
                                        if (((next$iv2.getKindSet() & i) != 0 ? 1 : 0) == 0) {
                                            zOrder$iv2 = zOrder$iv3;
                                            mask$iv2 = mask$iv2;
                                            $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                        } else {
                                            count$iv3++;
                                            zOrder$iv2 = zOrder$iv3;
                                            if (count$iv3 == 1) {
                                                node$iv3 = next$iv2;
                                                mask$iv2 = mask$iv2;
                                                $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                            } else {
                                                Object obj2 = (MutableVector) stack$iv2;
                                                if (obj2 == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                } else {
                                                    mutableVector = obj2;
                                                }
                                                stack$iv2 = mutableVector;
                                                Modifier.Node theNode$iv2 = (Modifier.Node) node$iv3;
                                                if (theNode$iv2 != null) {
                                                    MutableVector mutableVector5 = (MutableVector) stack$iv2;
                                                    if (mutableVector5 != null) {
                                                        mutableVector5.add(theNode$iv2);
                                                    }
                                                    node$iv3 = null;
                                                }
                                                MutableVector mutableVector6 = (MutableVector) stack$iv2;
                                                if (mutableVector6 != null) {
                                                    mutableVector6.add(next$iv2);
                                                }
                                            }
                                        }
                                        node$iv$iv2 = node$iv$iv2.getChild();
                                        zOrder$iv3 = zOrder$iv2;
                                        mask$iv2 = mask$iv2;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv2;
                                    }
                                    zOrder$iv = zOrder$iv3;
                                    mask$iv = mask$iv2;
                                    $this$visitChildren$iv = $this$visitChildren$iv2;
                                    if (count$iv3 == 1) {
                                        zOrder$iv3 = zOrder$iv;
                                        mask$iv2 = mask$iv;
                                        $this$visitChildren$iv2 = $this$visitChildren$iv;
                                        count$iv = 3;
                                    }
                                }
                            }
                            node$iv3 = pop((MutableVector) stack$iv2);
                            zOrder$iv3 = zOrder$iv;
                            mask$iv2 = mask$iv;
                            $this$visitChildren$iv2 = $this$visitChildren$iv;
                            count$iv = 3;
                        }
                        function1 = function1;
                        z = false;
                        zOrder$iv3 = zOrder$iv3;
                        count$iv = 3;
                        break;
                    }
                    node$iv2 = node$iv2.getChild();
                    function1 = function1;
                    zOrder$iv3 = zOrder$iv3;
                    count$iv = 3;
                }
            }
        }
    }

    /* JADX INFO: renamed from: visitSubtreeIf-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m6728visitSubtreeIfYYKmho$default(DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho_u24default, int i, boolean zOrder, Function1 block, int i2, Object obj) {
        boolean zOrder2;
        int i3;
        int $i$f$visitSubtreeIf;
        int mask$iv;
        int i4;
        int mask$iv2;
        DelegatableNode $this$visitSubtreeIf$iv;
        int $i$f$visitSubtreeIf2;
        int mask$iv3;
        int mask$iv4;
        if ((i2 & 2) == 0) {
            zOrder2 = zOrder;
        } else {
            zOrder2 = false;
        }
        int i5 = 0;
        boolean zOrder$iv = zOrder2;
        int mask$iv5 = i;
        DelegatableNode $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default;
        int $i$f$visitSubtreeIf3 = 0;
        boolean value$iv$iv = $this$visitSubtreeIf$iv2.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        int i6 = 0;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitSubtreeIf$iv2.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitSubtreeIf$iv2.getNode(), zOrder$iv);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            int i7 = 1;
            if ((branches$iv.getSize() != 0 ? 1 : i6) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv5) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv != null && node$iv.getIsAttached()) {
                        if ((node$iv.getKindSet() & mask$iv5) == 0) {
                            zOrder2 = zOrder2;
                            i3 = i5;
                            mask$iv5 = mask$iv5;
                            $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                            $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
                            $i$f$visitSubtreeIf = i6;
                            mask$iv = i7;
                        } else {
                            Modifier.Node node = node$iv;
                            Object stack$iv = null;
                            Modifier.Node nodePop = node;
                            while (true) {
                                if (nodePop != null) {
                                    zOrder2 = zOrder2;
                                    Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                                    if (nodePop instanceof Object) {
                                        i3 = i5;
                                        if (!((Boolean) block.invoke(nodePop)).booleanValue()) {
                                            i4 = 0;
                                            mask$iv = 1;
                                            $i$f$visitSubtreeIf = 0;
                                            break;
                                        } else {
                                            mask$iv2 = mask$iv5;
                                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv2;
                                            $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf3;
                                            mask$iv3 = 1;
                                        }
                                    } else {
                                        i3 = i5;
                                        Modifier.Node this_$iv$iv = nodePop;
                                        if (!((this_$iv$iv.getKindSet() & i) != 0) || !(nodePop instanceof DelegatingNode)) {
                                            mask$iv2 = mask$iv5;
                                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv2;
                                            $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf3;
                                            mask$iv3 = 1;
                                        } else {
                                            int count$iv = 0;
                                            DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                                            Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                            while (node$iv$iv != null) {
                                                Modifier.Node next$iv = node$iv$iv;
                                                if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) == 0) {
                                                    mask$iv4 = mask$iv5;
                                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                                                    $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
                                                } else {
                                                    count$iv++;
                                                    mask$iv4 = mask$iv5;
                                                    if (count$iv == 1) {
                                                        nodePop = next$iv;
                                                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                                                        $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
                                                    } else {
                                                        Object mutableVector = (MutableVector) stack$iv;
                                                        if (mutableVector == null) {
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        }
                                                        stack$iv = mutableVector;
                                                        Modifier.Node theNode$iv = nodePop;
                                                        if (theNode$iv != null) {
                                                            MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                            if (mutableVector2 != null) {
                                                                mutableVector2.add(theNode$iv);
                                                            }
                                                            nodePop = null;
                                                        }
                                                        MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(next$iv);
                                                        }
                                                        count$iv = count$iv;
                                                    }
                                                }
                                                node$iv$iv = node$iv$iv.getChild();
                                                mask$iv5 = mask$iv4;
                                                $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                                                $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
                                            }
                                            mask$iv2 = mask$iv5;
                                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv2;
                                            $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf3;
                                            mask$iv3 = 1;
                                            if (count$iv == 1) {
                                                i7 = 1;
                                                i5 = i3;
                                                mask$iv5 = mask$iv2;
                                                $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv;
                                                $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf2;
                                            }
                                        }
                                    }
                                    nodePop = pop((MutableVector) stack$iv);
                                    i7 = mask$iv3;
                                    i5 = i3;
                                    mask$iv5 = mask$iv2;
                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv;
                                    $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf2;
                                } else {
                                    zOrder2 = zOrder2;
                                    i3 = i5;
                                    mask$iv = i7;
                                    $i$f$visitSubtreeIf = 0;
                                    i4 = mask$iv;
                                    break;
                                }
                            }
                            if (i4 == 0) {
                                zOrder2 = zOrder2;
                                i6 = $i$f$visitSubtreeIf;
                                i5 = i3;
                                mask$iv5 = mask$iv5;
                                $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                                $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
                                break;
                            }
                        }
                        node$iv = node$iv.getChild();
                        zOrder2 = zOrder2;
                        i7 = mask$iv;
                        i6 = $i$f$visitSubtreeIf;
                        i5 = i3;
                        mask$iv5 = mask$iv5;
                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
                        $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
                    }
                }
            }
            int $i$f$visitSubtreeIf4 = $i$f$visitSubtreeIf3;
            int $i$f$visitSubtreeIf5 = i6;
            addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv);
            zOrder2 = zOrder2;
            i6 = $i$f$visitSubtreeIf5;
            i5 = i5;
            mask$iv5 = mask$iv5;
            $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv2;
            $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: visitSubtreeIf-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6727visitSubtreeIfYYKmho(DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho, int i, boolean zOrder, Function1<? super T, Boolean> function1) {
        int mask$iv;
        Modifier.Node child$iv;
        boolean z;
        boolean diveDeeper$iv;
        DelegatableNode $this$visitSubtreeIf$iv;
        int $i$f$visitSubtreeIf;
        Modifier.Node child$iv2;
        boolean z2;
        DelegatableNode $this$visitSubtreeIf$iv2;
        int i2 = 0;
        int mask$iv2 = i;
        DelegatableNode $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf_u2dY_u2dYKmho;
        int $i$f$visitSubtreeIf2 = 0;
        boolean value$iv$iv = $this$visitSubtreeIf$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        Modifier.Node child$iv3 = null;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv4 = $this$visitSubtreeIf$iv3.getNode().getChild();
        if (child$iv4 == null) {
            addLayoutNodeChildren(branches$iv, $this$visitSubtreeIf$iv3.getNode(), zOrder);
        } else {
            branches$iv.add(child$iv4);
        }
        while (true) {
            boolean z3 = true;
            if ((branches$iv.getSize() != 0 ? 1 : child$iv3) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv2) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv != null && node$iv.getIsAttached()) {
                        if ((node$iv.getKindSet() & mask$iv2) == 0) {
                            i2 = i2;
                            mask$iv = mask$iv2;
                            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
                            $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf2;
                            child$iv4 = child$iv4;
                            child$iv = child$iv3;
                            z = z3;
                        } else {
                            Object node = node$iv;
                            Object stack$iv = null;
                            Object node$iv2 = node;
                            while (true) {
                                if (node$iv2 != null) {
                                    i2 = i2;
                                    Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                                    if (node$iv2 instanceof Object) {
                                        Object it = node$iv2;
                                        mask$iv = mask$iv2;
                                        if (!function1.invoke(it).booleanValue()) {
                                            z = true;
                                            child$iv = null;
                                            diveDeeper$iv = false;
                                            break;
                                        } else {
                                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv3;
                                            $i$f$visitSubtreeIf = $i$f$visitSubtreeIf2;
                                            child$iv2 = child$iv4;
                                            z2 = true;
                                        }
                                    } else {
                                        mask$iv = mask$iv2;
                                        Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                        if (!((this_$iv$iv.getKindSet() & i) != 0) || !(node$iv2 instanceof DelegatingNode)) {
                                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv3;
                                            $i$f$visitSubtreeIf = $i$f$visitSubtreeIf2;
                                            child$iv2 = child$iv4;
                                            z2 = true;
                                        } else {
                                            int count$iv = 0;
                                            DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                            Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                            while (node$iv$iv != null) {
                                                Modifier.Node next$iv = node$iv$iv;
                                                if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) == 0) {
                                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv3;
                                                    $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf2;
                                                    child$iv4 = child$iv4;
                                                } else {
                                                    count$iv++;
                                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv3;
                                                    if (count$iv == 1) {
                                                        node$iv2 = next$iv;
                                                        $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf2;
                                                        child$iv4 = child$iv4;
                                                    } else {
                                                        Object mutableVector = (MutableVector) stack$iv;
                                                        if (mutableVector == null) {
                                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                        }
                                                        stack$iv = mutableVector;
                                                        Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                        if (theNode$iv != null) {
                                                            MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                            if (mutableVector2 != null) {
                                                                mutableVector2.add(theNode$iv);
                                                            }
                                                            node$iv2 = null;
                                                        }
                                                        MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(next$iv);
                                                        }
                                                        count$iv = count$iv;
                                                    }
                                                }
                                                node$iv$iv = node$iv$iv.getChild();
                                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv2;
                                                $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf2;
                                                child$iv4 = child$iv4;
                                            }
                                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv3;
                                            $i$f$visitSubtreeIf = $i$f$visitSubtreeIf2;
                                            child$iv2 = child$iv4;
                                            z2 = true;
                                            if (count$iv == 1) {
                                                z3 = true;
                                                mask$iv2 = mask$iv;
                                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv;
                                                $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf;
                                                child$iv4 = child$iv2;
                                            }
                                        }
                                    }
                                    node$iv2 = pop((MutableVector) stack$iv);
                                    z3 = z2;
                                    mask$iv2 = mask$iv;
                                    $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv;
                                    $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf;
                                    child$iv4 = child$iv2;
                                } else {
                                    i2 = i2;
                                    mask$iv = mask$iv2;
                                    z = z3;
                                    child$iv = null;
                                    diveDeeper$iv = z;
                                    break;
                                }
                            }
                            if (!diveDeeper$iv) {
                                child$iv3 = child$iv;
                                i2 = i2;
                                mask$iv2 = mask$iv;
                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
                                $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf2;
                                child$iv4 = child$iv4;
                                break;
                            }
                        }
                        node$iv = node$iv.getChild();
                        z3 = z;
                        child$iv3 = child$iv;
                        i2 = i2;
                        mask$iv2 = mask$iv;
                        $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
                        $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf2;
                        child$iv4 = child$iv4;
                    }
                }
            }
            addLayoutNodeChildren(branches$iv, branch$iv, zOrder);
            child$iv3 = child$iv3;
            i2 = i2;
            mask$iv2 = mask$iv2;
            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
            $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf2;
            child$iv4 = child$iv4;
        }
    }

    /* JADX INFO: renamed from: visitSubtree-Y-YKmho$default, reason: not valid java name */
    public static /* synthetic */ void m6726visitSubtreeYYKmho$default(DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default, int i, boolean zOrder, Function1 block, int i2, Object obj) {
        boolean zOrder2;
        boolean zOrder3;
        int i3;
        int mask$iv;
        DelegatableNode $this$visitSubtreeIf$iv;
        int i4;
        boolean zOrder4;
        int i5;
        int mask$iv2;
        DelegatableNode $this$visitSubtreeIf$iv2;
        int i6;
        Object mutableVector;
        if ((i2 & 2) == 0) {
            zOrder2 = zOrder;
        } else {
            zOrder2 = false;
        }
        int i7 = 0;
        boolean zOrder$iv = zOrder2;
        int mask$iv3 = i;
        DelegatableNode $this$visitSubtreeIf$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default;
        boolean value$iv$iv = $this$visitSubtreeIf$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        int i8 = 0;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitSubtreeIf$iv3.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitSubtreeIf$iv3.getNode(), zOrder$iv);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            boolean z = true;
            if ((branches$iv.getSize() != 0 ? 1 : i8) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv3) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv != null && node$iv.getIsAttached()) {
                        if ((node$iv.getKindSet() & mask$iv3) == 0) {
                            zOrder3 = zOrder2;
                            i3 = i7;
                            mask$iv = mask$iv3;
                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv3;
                            i4 = i8;
                            zOrder4 = z;
                        } else {
                            Modifier.Node it = node$iv;
                            Object stack$iv = null;
                            Modifier.Node nodePop = it;
                            while (nodePop != null) {
                                zOrder2 = zOrder2;
                                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                                if (nodePop instanceof Object) {
                                    block.invoke(nodePop);
                                    i5 = i7;
                                    mask$iv2 = mask$iv3;
                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv3;
                                } else {
                                    Modifier.Node this_$iv$iv = nodePop;
                                    if (!((this_$iv$iv.getKindSet() & i) != 0) || !(nodePop instanceof DelegatingNode)) {
                                        i5 = i7;
                                        mask$iv2 = mask$iv3;
                                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv3;
                                    } else {
                                        int count$iv = 0;
                                        DelegatingNode this_$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                        while (node$iv$iv != null) {
                                            Modifier.Node next$iv = node$iv$iv;
                                            if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) == 0) {
                                                i6 = i7;
                                                mask$iv3 = mask$iv3;
                                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
                                            } else {
                                                count$iv++;
                                                i6 = i7;
                                                if (count$iv == 1) {
                                                    nodePop = next$iv;
                                                    mask$iv3 = mask$iv3;
                                                    $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
                                                } else {
                                                    Object obj2 = (MutableVector) stack$iv;
                                                    if (obj2 == null) {
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        mutableVector = obj2;
                                                    }
                                                    stack$iv = mutableVector;
                                                    Modifier.Node theNode$iv = nodePop;
                                                    if (theNode$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv);
                                                        }
                                                        nodePop = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv);
                                                    }
                                                }
                                            }
                                            node$iv$iv = node$iv$iv.getChild();
                                            i7 = i6;
                                            mask$iv3 = mask$iv3;
                                            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
                                        }
                                        i5 = i7;
                                        mask$iv2 = mask$iv3;
                                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv3;
                                        if (count$iv == 1) {
                                            i7 = i5;
                                            mask$iv3 = mask$iv2;
                                            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv2;
                                        }
                                    }
                                }
                                nodePop = pop((MutableVector) stack$iv);
                                i7 = i5;
                                mask$iv3 = mask$iv2;
                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv2;
                            }
                            zOrder3 = zOrder2;
                            i3 = i7;
                            mask$iv = mask$iv3;
                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv3;
                            i4 = 0;
                            zOrder4 = true;
                            if (1 == 0) {
                                zOrder2 = zOrder3;
                                i8 = 0;
                                i7 = i3;
                                mask$iv3 = mask$iv;
                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv;
                                break;
                            }
                        }
                        node$iv = node$iv.getChild();
                        z = zOrder4;
                        i8 = i4;
                        i7 = i3;
                        mask$iv3 = mask$iv;
                        $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv;
                        zOrder2 = zOrder3;
                    }
                }
            }
            addLayoutNodeChildren(branches$iv, branch$iv, zOrder$iv);
            zOrder2 = zOrder2;
            i8 = i8;
            i7 = i7;
            mask$iv3 = mask$iv3;
            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
        }
    }

    /* JADX INFO: renamed from: visitSubtree-Y-YKmho, reason: not valid java name */
    public static final /* synthetic */ <T> void m6725visitSubtreeYYKmho(DelegatableNode $this$visitSubtree_u2dY_u2dYKmho, int i, boolean zOrder, Function1<? super T, Unit> function1) {
        int i2;
        int mask$iv;
        DelegatableNode $this$visitSubtreeIf$iv;
        int $i$f$visitSubtreeIf;
        int mask$iv2;
        boolean z;
        int mask$iv3;
        DelegatableNode $this$visitSubtreeIf$iv2;
        int $i$f$visitSubtreeIf2;
        int mask$iv4;
        Object mutableVector;
        int i3 = 0;
        int mask$iv5 = i;
        DelegatableNode $this$visitSubtreeIf$iv3 = $this$visitSubtree_u2dY_u2dYKmho;
        int $i$f$visitSubtreeIf3 = 0;
        boolean value$iv$iv = $this$visitSubtreeIf$iv3.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        int i4 = 0;
        MutableVector branches$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv = $this$visitSubtreeIf$iv3.getNode().getChild();
        if (child$iv == null) {
            addLayoutNodeChildren(branches$iv, $this$visitSubtreeIf$iv3.getNode(), zOrder);
        } else {
            branches$iv.add(child$iv);
        }
        while (true) {
            boolean z2 = true;
            if ((branches$iv.getSize() != 0 ? 1 : i4) == 0) {
                return;
            }
            Modifier.Node branch$iv = (Modifier.Node) branches$iv.removeAt(branches$iv.getSize() - 1);
            if ((branch$iv.getAggregateChildKindSet() & mask$iv5) != 0) {
                Modifier.Node node$iv = branch$iv;
                while (true) {
                    if (node$iv != null && node$iv.getIsAttached()) {
                        if ((node$iv.getKindSet() & mask$iv5) == 0) {
                            i2 = i3;
                            mask$iv = mask$iv5;
                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv3;
                            $i$f$visitSubtreeIf = $i$f$visitSubtreeIf3;
                            mask$iv2 = i4;
                            z = z2;
                        } else {
                            Object it = node$iv;
                            Object stack$iv = null;
                            Object node$iv2 = it;
                            while (node$iv2 != null) {
                                i3 = i3;
                                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                                if (node$iv2 instanceof Object) {
                                    function1.invoke(node$iv2);
                                    mask$iv3 = mask$iv5;
                                    $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv3;
                                    $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf3;
                                } else {
                                    Modifier.Node this_$iv$iv = (Modifier.Node) node$iv2;
                                    if (!((this_$iv$iv.getKindSet() & i) != 0) || !(node$iv2 instanceof DelegatingNode)) {
                                        mask$iv3 = mask$iv5;
                                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv3;
                                        $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf3;
                                    } else {
                                        int count$iv = 0;
                                        DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv2;
                                        Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                                        while (node$iv$iv != null) {
                                            Modifier.Node next$iv = node$iv$iv;
                                            if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) == 0) {
                                                mask$iv4 = mask$iv5;
                                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
                                                $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
                                            } else {
                                                count$iv++;
                                                mask$iv4 = mask$iv5;
                                                if (count$iv == 1) {
                                                    node$iv2 = next$iv;
                                                    $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
                                                    $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
                                                } else {
                                                    Object obj = (MutableVector) stack$iv;
                                                    if (obj == null) {
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    } else {
                                                        mutableVector = obj;
                                                    }
                                                    stack$iv = mutableVector;
                                                    Modifier.Node theNode$iv = (Modifier.Node) node$iv2;
                                                    if (theNode$iv != null) {
                                                        MutableVector mutableVector2 = (MutableVector) stack$iv;
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv);
                                                        }
                                                        node$iv2 = null;
                                                    }
                                                    MutableVector mutableVector3 = (MutableVector) stack$iv;
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv);
                                                    }
                                                }
                                            }
                                            node$iv$iv = node$iv$iv.getChild();
                                            mask$iv5 = mask$iv4;
                                            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
                                            $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
                                        }
                                        mask$iv3 = mask$iv5;
                                        $this$visitSubtreeIf$iv2 = $this$visitSubtreeIf$iv3;
                                        $i$f$visitSubtreeIf2 = $i$f$visitSubtreeIf3;
                                        if (count$iv == 1) {
                                            mask$iv5 = mask$iv3;
                                            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv2;
                                            $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf2;
                                        }
                                    }
                                }
                                node$iv2 = pop((MutableVector) stack$iv);
                                mask$iv5 = mask$iv3;
                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv2;
                                $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf2;
                            }
                            i2 = i3;
                            mask$iv = mask$iv5;
                            $this$visitSubtreeIf$iv = $this$visitSubtreeIf$iv3;
                            $i$f$visitSubtreeIf = $i$f$visitSubtreeIf3;
                            mask$iv2 = 0;
                            z = true;
                            if (1 == 0) {
                                i4 = 0;
                                i3 = i2;
                                mask$iv5 = mask$iv;
                                $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv;
                                $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf;
                                break;
                            }
                        }
                        node$iv = node$iv.getChild();
                        i4 = mask$iv2;
                        z2 = z;
                        i3 = i2;
                        mask$iv5 = mask$iv;
                        $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv;
                        $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf;
                    }
                }
            }
            int mask$iv6 = mask$iv5;
            int mask$iv7 = i4;
            addLayoutNodeChildren(branches$iv, branch$iv, zOrder);
            i4 = mask$iv7;
            i3 = i3;
            mask$iv5 = mask$iv6;
            $this$visitSubtreeIf$iv3 = $this$visitSubtreeIf$iv3;
            $i$f$visitSubtreeIf3 = $i$f$visitSubtreeIf3;
        }
    }

    /* JADX INFO: renamed from: has-64DMado, reason: not valid java name */
    public static final boolean m6710has64DMado(DelegatableNode $this$has_u2d64DMado, int i) {
        return ($this$has_u2d64DMado.getNode().getAggregateChildKindSet() & i) != 0;
    }

    /* JADX INFO: renamed from: requireCoordinator-64DMado, reason: not valid java name */
    public static final NodeCoordinator m6712requireCoordinator64DMado(DelegatableNode $this$requireCoordinator_u2d64DMado, int i) {
        NodeCoordinator coordinator = $this$requireCoordinator_u2d64DMado.getNode().getCoordinator();
        Intrinsics.checkNotNull(coordinator);
        if (coordinator.getTail() != $this$requireCoordinator_u2d64DMado || !NodeKindKt.m6869getIncludeSelfInTraversalH91voCI(i)) {
            return coordinator;
        }
        NodeCoordinator wrapped$ui = coordinator.getWrapped();
        Intrinsics.checkNotNull(wrapped$ui);
        return wrapped$ui;
    }

    public static final LayoutNode requireLayoutNode(DelegatableNode $this$requireLayoutNode) {
        NodeCoordinator coordinator = $this$requireLayoutNode.getNode().getCoordinator();
        if (coordinator != null) {
            return coordinator.getLayoutNode();
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Cannot obtain node coordinator. Is the Modifier.Node attached?");
        throw new KotlinNothingValueException();
    }

    public static final SemanticsInfo requireSemanticsInfo(DelegatableNode $this$requireSemanticsInfo) {
        return requireLayoutNode($this$requireSemanticsInfo);
    }

    public static final Owner requireOwner(DelegatableNode $this$requireOwner) {
        Owner owner = requireLayoutNode($this$requireOwner).getOwner();
        if (owner != null) {
            return owner;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("This node does not have an owner.");
        throw new KotlinNothingValueException();
    }

    public static final void requestAutofill(DelegatableNode $this$requestAutofill) {
        requireLayoutNode($this$requestAutofill).requestAutofill$ui();
    }

    public static final Density requireDensity(DelegatableNode $this$requireDensity) {
        return requireLayoutNode($this$requireDensity).getDensity();
    }

    public static final GraphicsContext requireGraphicsContext(DelegatableNode $this$requireGraphicsContext) {
        return requireOwner($this$requireGraphicsContext).getGraphicsContext();
    }

    public static final LayoutDirection requireLayoutDirection(DelegatableNode $this$requireLayoutDirection) {
        return requireLayoutNode($this$requireLayoutDirection).getLayoutDirection();
    }

    public static final LayoutCoordinates requireLayoutCoordinates(DelegatableNode $this$requireLayoutCoordinates) {
        boolean value$iv = $this$requireLayoutCoordinates.getNode().getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("Cannot get LayoutCoordinates, Modifier.Node is not attached.");
        }
        LayoutCoordinates coordinates = m6712requireCoordinator64DMado($this$requireLayoutCoordinates, NodeKind.m6860constructorimpl(2)).getCoordinates();
        boolean value$iv2 = coordinates.isAttached();
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinates is not attached.");
        }
        return coordinates;
    }

    public static final void invalidateSubtree(DelegatableNode $this$invalidateSubtree) {
        if ($this$invalidateSubtree.getNode().getIsAttached()) {
            LayoutNode.invalidateSubtree$default(requireLayoutNode($this$invalidateSubtree), false, 1, null);
        }
    }

    public static final void invalidateMeasurementForSubtree(DelegatableNode $this$invalidateMeasurementForSubtree) {
        if ($this$invalidateMeasurementForSubtree.getNode().getIsAttached()) {
            requireLayoutNode($this$invalidateMeasurementForSubtree).invalidateMeasurementForSubtree();
        }
    }

    public static final void invalidateDrawForSubtree(DelegatableNode $this$invalidateDrawForSubtree) {
        if ($this$invalidateDrawForSubtree.getNode().getIsAttached()) {
            LayoutNode.invalidateDrawForSubtree$default(requireLayoutNode($this$invalidateDrawForSubtree), false, 1, null);
        }
    }

    /* JADX INFO: renamed from: dispatchOnScrollChanged-Uv8p0NA, reason: not valid java name */
    public static final void m6709dispatchOnScrollChangedUv8p0NA(DelegatableNode $this$dispatchOnScrollChanged_u2dUv8p0NA, long j) {
        requireOwner($this$dispatchOnScrollChanged_u2dUv8p0NA).mo6926dispatchOnScrollChangedk4lQ0M(j);
    }

    public static final BeyondBoundsLayout findNearestBeyondBoundsLayoutAncestor(DelegatableNode $this$findNearestBeyondBoundsLayoutAncestor) {
        Modifier.Node node;
        NodeChain nodes;
        BeyondBoundsLayout beyondBoundsLayout;
        int i = 8388608;
        int mask$iv = NodeKind.m6860constructorimpl(8388608) | NodeKind.m6860constructorimpl(32);
        boolean value$iv$iv = $this$findNearestBeyondBoundsLayoutAncestor.getNode().getIsAttached();
        if (!value$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv = $this$findNearestBeyondBoundsLayoutAncestor.getNode().getParent();
        LayoutNode layout$iv = requireLayoutNode($this$findNearestBeyondBoundsLayoutAncestor);
        while (layout$iv != null) {
            Modifier.Node head$iv = layout$iv.getNodes().getHead();
            if ((head$iv.getAggregateChildKindSet() & mask$iv) != 0) {
                while (node$iv != null) {
                    if ((node$iv.getKindSet() & mask$iv) != 0) {
                        Modifier.Node it = node$iv;
                        if (((it.getKindSet() & NodeKind.m6860constructorimpl(i)) != 0 ? 1 : 0) != 0) {
                            Object beyondBoundsNode = null;
                            if (it instanceof BeyondBoundsLayoutProviderModifierNode) {
                                beyondBoundsNode = it;
                                beyondBoundsLayout = null;
                            } else if (it instanceof DelegatingNode) {
                                DelegatingNode this_$iv = (DelegatingNode) it;
                                for (Modifier.Node node$iv2 = this_$iv.getDelegate(); node$iv2 != null; node$iv2 = node$iv2.getChild()) {
                                    Object it2 = node$iv2;
                                    if (it2 instanceof BeyondBoundsLayoutProviderModifierNode) {
                                        beyondBoundsNode = it2;
                                    }
                                }
                                beyondBoundsLayout = null;
                            } else {
                                beyondBoundsLayout = null;
                            }
                            BeyondBoundsLayoutProviderModifierNode beyondBoundsLayoutProviderModifierNode = (BeyondBoundsLayoutProviderModifierNode) beyondBoundsNode;
                            return beyondBoundsLayoutProviderModifierNode != null ? beyondBoundsLayoutProviderModifierNode.getBeyondBoundsLayout() : beyondBoundsLayout;
                        }
                        if ((it.getKindSet() & NodeKind.m6860constructorimpl(32)) != 0) {
                            Object modifierLocalNode = null;
                            if (it instanceof ModifierLocalModifierNode) {
                                modifierLocalNode = it;
                            } else if (it instanceof DelegatingNode) {
                                DelegatingNode this_$iv2 = (DelegatingNode) it;
                                for (Modifier.Node node$iv3 = this_$iv2.getDelegate(); node$iv3 != null; node$iv3 = node$iv3.getChild()) {
                                    Object it3 = node$iv3;
                                    if (it3 instanceof ModifierLocalModifierNode) {
                                        modifierLocalNode = it3;
                                    }
                                }
                            }
                            ModifierLocalModifierNode localNode = (ModifierLocalModifierNode) modifierLocalNode;
                            if (localNode != null && localNode.getProvidedValues().contains$ui(BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout())) {
                                return (BeyondBoundsLayout) localNode.getProvidedValues().get$ui(BeyondBoundsLayoutKt.getModifierLocalBeyondBoundsLayout());
                            }
                        } else {
                            continue;
                        }
                    }
                    node$iv = node$iv.getParent();
                    i = 8388608;
                }
                node = null;
            } else {
                node = null;
            }
            layout$iv = layout$iv.getParent$ui();
            node$iv = (layout$iv == null || (nodes = layout$iv.getNodes()) == null) ? node : nodes.getTail();
            i = 8388608;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0056  */
    /* JADX WARN: Multi-variable type inference failed */
    public static final LayoutModifierNode asLayoutModifierNode(Modifier.Node node) {
        Modifier.Node child;
        if (((node.getKindSet() & NodeKind.m6860constructorimpl(2)) != 0 ? 1 : 0) == 0) {
            return null;
        }
        if (node instanceof LayoutModifierNode) {
            return (LayoutModifierNode) node;
        }
        if (node instanceof DelegatingNode) {
            Modifier.Node node2 = ((DelegatingNode) node).getDelegate();
            while (node2 != null) {
                if (node2 instanceof LayoutModifierNode) {
                    return (LayoutModifierNode) node2;
                }
                if (node2 instanceof DelegatingNode) {
                    Modifier.Node this_$iv = node2;
                    if (((this_$iv.getKindSet() & NodeKind.m6860constructorimpl(2)) != 0 ? 1 : 0) != 0) {
                        child = ((DelegatingNode) node2).getDelegate();
                    } else {
                        child = node2.getChild();
                    }
                } else {
                    child = node2.getChild();
                }
                node2 = child;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: dispatchForKind-6rFNWt0, reason: not valid java name */
    public static final /* synthetic */ <T> void m6708dispatchForKind6rFNWt0(Modifier.Node $this$dispatchForKind_u2d6rFNWt0, int i, Function1<? super T, Unit> function1) {
        Object stack = null;
        Object node = $this$dispatchForKind_u2d6rFNWt0;
        while (node != null) {
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (node instanceof Object) {
                function1.invoke(node);
            } else {
                Modifier.Node this_$iv = (Modifier.Node) node;
                int i2 = 1;
                if (((this_$iv.getKindSet() & i) != 0) && (node instanceof DelegatingNode)) {
                    int count = 0;
                    DelegatingNode this_$iv2 = (DelegatingNode) node;
                    Modifier.Node node$iv = this_$iv2.getDelegate();
                    while (node$iv != null) {
                        Modifier.Node next = node$iv;
                        if (((next.getKindSet() & i) != 0 ? i2 : 0) != 0) {
                            count++;
                            if (count == i2) {
                                node = next;
                            } else {
                                Object obj = (MutableVector) stack;
                                if (obj == null) {
                                    Object mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    obj = mutableVector;
                                }
                                stack = obj;
                                Modifier.Node theNode = (Modifier.Node) node;
                                if (theNode != null) {
                                    MutableVector mutableVector2 = (MutableVector) stack;
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(theNode);
                                    }
                                    node = null;
                                }
                                MutableVector mutableVector3 = (MutableVector) stack;
                                if (mutableVector3 != null) {
                                    mutableVector3.add(next);
                                }
                            }
                        }
                        node$iv = node$iv.getChild();
                        i2 = 1;
                    }
                    if (count == 1) {
                    }
                }
            }
            node = pop((MutableVector) stack);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier.Node pop(MutableVector<Modifier.Node> mutableVector) {
        if (mutableVector != null) {
            if (!(mutableVector.getSize() == 0)) {
                return mutableVector.removeAt(mutableVector.getSize() - 1);
            }
        }
        return null;
    }
}
