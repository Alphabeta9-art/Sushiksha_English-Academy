package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_jvmKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TraversableNode.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u001b\u0010\u0000\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u0005¢\u0006\u0002\u0010\u0006\u001a(\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n\u001a-\u0010\u0007\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\f\u001a(\u0010\r\u001a\u00020\b*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b0\n\u001a-\u0010\r\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\f\u001a(\u0010\u000e\u001a\u00020\b*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\n\u001a-\u0010\u000e\u001a\u00020\b\"\b\b\u0000\u0010\u0005*\u00020\u0001*\u0002H\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000f0\n¢\u0006\u0002\u0010\f¨\u0006\u0010"}, d2 = {"findNearestAncestor", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/DelegatableNode;", "key", "", ExifInterface.GPS_DIRECTION_TRUE, "(Landroidx/compose/ui/node/TraversableNode;)Landroidx/compose/ui/node/TraversableNode;", "traverseAncestors", "", "block", "Lkotlin/Function1;", "", "(Landroidx/compose/ui/node/TraversableNode;Lkotlin/jvm/functions/Function1;)V", "traverseChildren", "traverseDescendants", "Landroidx/compose/ui/node/TraversableNode$Companion$TraverseDescendantsAction;", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TraversableNodeKt {
    public static final TraversableNode findNearestAncestor(DelegatableNode $this$findNearestAncestor, Object key) {
        int i;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
        boolean includeSelf$iv;
        int i2;
        Modifier.Node node;
        NodeChain nodes;
        int i3;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
        boolean includeSelf$iv2;
        int i4;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
        boolean includeSelf$iv3;
        int i5;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(262144);
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4 = $this$findNearestAncestor;
        boolean includeSelf$iv4 = false;
        int i6 = 0;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv4);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i7 = iM6860constructorimpl;
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            iM6860constructorimpl = iM6860constructorimpl;
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4;
                                if (Intrinsics.areEqual(key, it.getTraverseKey())) {
                                    return it;
                                }
                                includeSelf$iv3 = includeSelf$iv4;
                                i5 = i6;
                            } else {
                                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4;
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i7) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i7) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                includeSelf$iv4 = includeSelf$iv4;
                                                i6 = i6;
                                            } else {
                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv = nodePop;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv);
                                                }
                                                mutableVector = mutableVector2;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            includeSelf$iv4 = includeSelf$iv4;
                                            i6 = i6;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        includeSelf$iv4 = includeSelf$iv4;
                                        i6 = i6;
                                    }
                                    includeSelf$iv3 = includeSelf$iv4;
                                    i5 = i6;
                                    if (count$iv$iv == 1) {
                                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                                        includeSelf$iv4 = includeSelf$iv3;
                                        i6 = i5;
                                    }
                                } else {
                                    includeSelf$iv3 = includeSelf$iv4;
                                    i5 = i6;
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                            includeSelf$iv4 = includeSelf$iv3;
                            i6 = i5;
                        }
                        i3 = iM6860constructorimpl;
                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4;
                        includeSelf$iv2 = includeSelf$iv4;
                        i4 = i6;
                    } else {
                        i3 = iM6860constructorimpl;
                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4;
                        includeSelf$iv2 = includeSelf$iv4;
                        i4 = i6;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    iM6860constructorimpl = i3;
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                    includeSelf$iv4 = includeSelf$iv2;
                    i6 = i4;
                }
                i = iM6860constructorimpl;
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4;
                includeSelf$iv = includeSelf$iv4;
                i2 = i6;
                node = null;
            } else {
                i = iM6860constructorimpl;
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4;
                includeSelf$iv = includeSelf$iv4;
                i2 = i6;
                node = null;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? node : nodes.getTail();
            iM6860constructorimpl = i;
            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
            includeSelf$iv4 = includeSelf$iv;
            i6 = i2;
        }
        return null;
    }

    public static final <T extends TraversableNode> T findNearestAncestor(T t) {
        T $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
        int i;
        boolean includeSelf$iv;
        Modifier.Node node;
        NodeChain nodes;
        T $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
        int i2;
        boolean includeSelf$iv2;
        int i3;
        boolean includeSelf$iv3;
        t = t;
        T $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3 = t;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(262144);
        boolean includeSelf$iv4 = false;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv3);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i4 = iM6860constructorimpl;
                        MutableVector mutableVector = null;
                        Modifier.Node this_$iv$iv$iv = it$iv;
                        while (this_$iv$iv$iv != null) {
                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                            if (this_$iv$iv$iv instanceof TraversableNode) {
                                T t2 = (T) this_$iv$iv$iv;
                                i3 = iM6860constructorimpl;
                                includeSelf$iv3 = includeSelf$iv4;
                                if (Intrinsics.areEqual(t.getTraverseKey(), t2.getTraverseKey()) && Actual_jvmKt.areObjectsOfSameType(t, t2)) {
                                    return t2;
                                }
                            } else {
                                i3 = iM6860constructorimpl;
                                includeSelf$iv3 = includeSelf$iv4;
                                int i5 = 1;
                                if (((this_$iv$iv$iv.getKindSet() & i4) != 0 ? 1 : 0) != 0 && (this_$iv$iv$iv instanceof DelegatingNode)) {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) this_$iv$iv$iv;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i4) != 0 ? i5 : 0) != 0) {
                                            count$iv$iv++;
                                            if (count$iv$iv == i5) {
                                                this_$iv$iv$iv = next$iv$iv;
                                                this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                            } else {
                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv = this_$iv$iv$iv;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    this_$iv$iv$iv = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv);
                                                }
                                                mutableVector = mutableVector2;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                        i5 = 1;
                                    }
                                    if (count$iv$iv == 1) {
                                        iM6860constructorimpl = i3;
                                        includeSelf$iv4 = includeSelf$iv3;
                                    }
                                }
                            }
                            this_$iv$iv$iv = DelegatableNodeKt.pop(mutableVector);
                            iM6860constructorimpl = i3;
                            includeSelf$iv4 = includeSelf$iv3;
                        }
                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                        i2 = iM6860constructorimpl;
                        includeSelf$iv2 = includeSelf$iv4;
                    } else {
                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                        i2 = iM6860constructorimpl;
                        includeSelf$iv2 = includeSelf$iv4;
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    t = t;
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                    iM6860constructorimpl = i2;
                    includeSelf$iv4 = includeSelf$iv2;
                }
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                i = iM6860constructorimpl;
                includeSelf$iv = includeSelf$iv4;
                node = null;
            } else {
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                i = iM6860constructorimpl;
                includeSelf$iv = includeSelf$iv4;
                node = null;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? node : nodes.getTail();
            t = t;
            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
            iM6860constructorimpl = i;
            includeSelf$iv4 = includeSelf$iv;
        }
        return null;
    }

    public static final void traverseAncestors(DelegatableNode $this$traverseAncestors, Object key, Function1<? super TraversableNode, Boolean> function1) {
        int i;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
        boolean includeSelf$iv;
        int i2;
        NodeChain nodes;
        boolean includeSelf$iv2;
        int i3;
        MutableVector mutableVector;
        Modifier.Node node;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(262144);
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$traverseAncestors;
        boolean includeSelf$iv3 = false;
        int i4 = 0;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv2);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i5 = iM6860constructorimpl;
                        MutableVector mutableVector2 = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                includeSelf$iv2 = includeSelf$iv3;
                                i3 = i4;
                                boolean continueTraversal = Intrinsics.areEqual(key, it.getTraverseKey()) ? function1.invoke(it).booleanValue() : true;
                                if (!continueTraversal) {
                                    return;
                                }
                            } else {
                                includeSelf$iv2 = includeSelf$iv3;
                                i3 = i4;
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i5) != 0) && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i5) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            Modifier.Node node2 = nodePop;
                                            if (count$iv$iv == 1) {
                                                mutableVector = mutableVector2;
                                                node = next$iv$iv;
                                            } else {
                                                MutableVector mutableVector3 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                if (node2 != null) {
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(node2);
                                                    }
                                                    node = null;
                                                } else {
                                                    node = node2;
                                                }
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv);
                                                }
                                                mutableVector = mutableVector3;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            mutableVector = mutableVector2;
                                            node = nodePop;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        nodePop = node;
                                        mutableVector2 = mutableVector;
                                    }
                                    Modifier.Node node3 = nodePop;
                                    if (count$iv$iv == 1) {
                                        includeSelf$iv3 = includeSelf$iv2;
                                        i4 = i3;
                                        nodePop = node3;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            includeSelf$iv3 = includeSelf$iv2;
                            i4 = i3;
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    iM6860constructorimpl = iM6860constructorimpl;
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                    includeSelf$iv3 = includeSelf$iv3;
                    i4 = i4;
                }
                i = iM6860constructorimpl;
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
                i2 = i4;
            } else {
                i = iM6860constructorimpl;
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
                i2 = i4;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            iM6860constructorimpl = i;
            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
            includeSelf$iv3 = includeSelf$iv;
            i4 = i2;
        }
    }

    public static final <T extends TraversableNode> void traverseAncestors(T t, Function1<? super T, Boolean> function1) {
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
        int i;
        boolean includeSelf$iv;
        int i2;
        NodeChain nodes;
        boolean includeSelf$iv2;
        int i3;
        t = t;
        T $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = t;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(262144);
        boolean includeSelf$iv3 = false;
        int i4 = 0;
        boolean value$iv$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv2);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i5 = iM6860constructorimpl;
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            iM6860constructorimpl = iM6860constructorimpl;
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                includeSelf$iv2 = includeSelf$iv3;
                                i3 = i4;
                                boolean continueTraversal = (Intrinsics.areEqual(t.getTraverseKey(), it.getTraverseKey()) && Actual_jvmKt.areObjectsOfSameType(t, it)) ? function1.invoke(it).booleanValue() : true;
                                if (!continueTraversal) {
                                    return;
                                }
                            } else {
                                includeSelf$iv2 = includeSelf$iv3;
                                i3 = i4;
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i5) != 0 ? 1 : 0) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    for (Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate(); node$iv$iv$iv != null; node$iv$iv$iv = node$iv$iv$iv.getChild()) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i5) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                            } else {
                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv = nodePop;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv);
                                                }
                                                mutableVector = mutableVector2;
                                                nodePop = nodePop;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        }
                                    }
                                    Modifier.Node node = nodePop;
                                    if (count$iv$iv == 1) {
                                        includeSelf$iv3 = includeSelf$iv2;
                                        i4 = i3;
                                        nodePop = node;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                            includeSelf$iv3 = includeSelf$iv2;
                            i4 = i3;
                        }
                    }
                    node$iv$iv = node$iv$iv.getParent();
                    t = t;
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                    iM6860constructorimpl = iM6860constructorimpl;
                    includeSelf$iv3 = includeSelf$iv3;
                    i4 = i4;
                }
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                i = iM6860constructorimpl;
                includeSelf$iv = includeSelf$iv3;
                i2 = i4;
            } else {
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                i = iM6860constructorimpl;
                includeSelf$iv = includeSelf$iv3;
                i2 = i4;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            t = t;
            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
            iM6860constructorimpl = i;
            includeSelf$iv3 = includeSelf$iv;
            i4 = i2;
        }
    }

    public static final void traverseChildren(DelegatableNode $this$traverseChildren, Object key, Function1<? super TraversableNode, Boolean> function1) {
        int i;
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        boolean zOrder$iv;
        boolean z;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(262144);
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$traverseChildren;
        boolean zOrder$iv2 = false;
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        boolean z2 = false;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            if (!(branches$iv$iv.getSize() != 0 ? true : z2)) {
                return;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        z2 = false;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i2 = iM6860constructorimpl;
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                i = iM6860constructorimpl;
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                boolean continueTraversal = Intrinsics.areEqual(key, it.getTraverseKey()) ? function1.invoke(it).booleanValue() : true;
                                if (!continueTraversal) {
                                    return;
                                }
                                zOrder$iv = zOrder$iv2;
                                z = true;
                            } else {
                                i = iM6860constructorimpl;
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i2) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    zOrder$iv = zOrder$iv2;
                                    z = true;
                                } else {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i2) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                zOrder$iv2 = zOrder$iv2;
                                            } else {
                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv = nodePop;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv);
                                                }
                                                mutableVector = mutableVector2;
                                            }
                                        } else {
                                            zOrder$iv2 = zOrder$iv2;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        zOrder$iv2 = zOrder$iv2;
                                    }
                                    zOrder$iv = zOrder$iv2;
                                    z = true;
                                    if (count$iv$iv == 1) {
                                        iM6860constructorimpl = i;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        zOrder$iv2 = zOrder$iv;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                            iM6860constructorimpl = i;
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            zOrder$iv2 = zOrder$iv;
                        }
                        z2 = false;
                        break;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    iM6860constructorimpl = iM6860constructorimpl;
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
    }

    public static final <T extends TraversableNode> void traverseChildren(T t, Function1<? super T, Boolean> function1) {
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        int i;
        boolean z;
        t = t;
        T $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = t;
        int count$iv$iv = NodeKind.m6860constructorimpl(262144);
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        boolean z2 = false;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            if (!(branches$iv$iv.getSize() != 0 ? true : z2)) {
                return;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & count$iv$iv) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        t = t;
                        z2 = false;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & count$iv$iv) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i2 = count$iv$iv;
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = it$iv;
                        while (nodePop != null) {
                            if (nodePop instanceof TraversableNode) {
                                TraversableNode it = (TraversableNode) nodePop;
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                i = count$iv$iv;
                                boolean continueTraversal = (Intrinsics.areEqual(t.getTraverseKey(), it.getTraverseKey()) && Actual_jvmKt.areObjectsOfSameType(t, it)) ? function1.invoke(it).booleanValue() : true;
                                if (!continueTraversal) {
                                    return;
                                } else {
                                    z = true;
                                }
                            } else {
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                i = count$iv$iv;
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i2) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    z = true;
                                } else {
                                    int count$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    for (Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate(); node$iv$iv$iv != null; node$iv$iv$iv = node$iv$iv$iv.getChild()) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i2) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv2++;
                                            if (count$iv$iv2 == 1) {
                                                nodePop = next$iv$iv;
                                            } else {
                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv = nodePop;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(next$iv$iv);
                                                }
                                                mutableVector = mutableVector2;
                                                count$iv$iv2 = count$iv$iv2;
                                            }
                                        }
                                    }
                                    z = true;
                                    if (count$iv$iv2 == 1) {
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        count$iv$iv = i;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            count$iv$iv = i;
                        }
                        t = t;
                        z2 = false;
                        break;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    t = t;
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
    }

    public static final void traverseDescendants(DelegatableNode $this$traverseDescendants, Object key, Function1<? super TraversableNode, ? extends TraversableNode.Companion.TraverseDescendantsAction> function1) {
        int i;
        DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
        boolean zOrder$iv;
        int i2;
        boolean z;
        boolean diveDeeper$iv$iv;
        int i3;
        boolean z2;
        DelegatingNode this_$iv$iv$iv;
        int count$iv$iv = NodeKind.m6860constructorimpl(262144);
        DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$traverseDescendants;
        boolean zOrder$iv2 = false;
        int i4 = 0;
        boolean value$iv$iv$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        int i5 = 0;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            boolean z3 = true;
            if ((branches$iv$iv.getSize() != 0 ? 1 : i5) == 0) {
                return;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & count$iv$iv) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv != null && node$iv$iv.getIsAttached()) {
                        if ((node$iv$iv.getKindSet() & count$iv$iv) != 0) {
                            Modifier.Node node$iv = node$iv$iv;
                            int i6 = count$iv$iv;
                            MutableVector mutableVector = null;
                            Modifier.Node this_$iv$iv$iv2 = node$iv;
                            while (true) {
                                if (this_$iv$iv$iv2 == null) {
                                    i = count$iv$iv;
                                    $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                    zOrder$iv = zOrder$iv2;
                                    z = z3;
                                    i2 = 0;
                                    diveDeeper$iv$iv = z;
                                    break;
                                }
                                if (this_$iv$iv$iv2 instanceof TraversableNode) {
                                    Object it$iv = this_$iv$iv$iv2;
                                    i = count$iv$iv;
                                    TraversableNode it = (TraversableNode) it$iv;
                                    $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                    zOrder$iv = zOrder$iv2;
                                    TraversableNode.Companion.TraverseDescendantsAction action = Intrinsics.areEqual(key, it.getTraverseKey()) ? function1.invoke(it) : TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
                                    if (action == TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal) {
                                        return;
                                    }
                                    if (!(action != TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal)) {
                                        z = true;
                                        i2 = 0;
                                        diveDeeper$iv$iv = false;
                                        break;
                                    }
                                    i3 = i4;
                                    z2 = true;
                                } else {
                                    i = count$iv$iv;
                                    $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                    zOrder$iv = zOrder$iv2;
                                    if (((this_$iv$iv$iv2.getKindSet() & i6) != 0 ? 1 : 0) == 0 || !(this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                        i3 = i4;
                                        z2 = true;
                                    } else {
                                        int count$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if (((next$iv$iv.getKindSet() & i6) != 0 ? 1 : 0) != 0) {
                                                count$iv$iv2++;
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                if (count$iv$iv2 == 1) {
                                                    this_$iv$iv$iv2 = next$iv$iv;
                                                    i4 = i4;
                                                } else {
                                                    MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                    Modifier.Node theNode$iv$iv = this_$iv$iv$iv2;
                                                    if (theNode$iv$iv != null) {
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        this_$iv$iv$iv2 = null;
                                                    }
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(next$iv$iv);
                                                    }
                                                    mutableVector = mutableVector2;
                                                    count$iv$iv2 = count$iv$iv2;
                                                }
                                            } else {
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                i4 = i4;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            this_$iv$iv$iv3 = this_$iv$iv$iv;
                                            i4 = i4;
                                        }
                                        i3 = i4;
                                        z2 = true;
                                        if (count$iv$iv2 == 1) {
                                            z3 = true;
                                            count$iv$iv = i;
                                            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                            zOrder$iv2 = zOrder$iv;
                                            i4 = i3;
                                        }
                                    }
                                }
                                this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector);
                                z3 = z2;
                                count$iv$iv = i;
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                zOrder$iv2 = zOrder$iv;
                                i4 = i3;
                            }
                            if (!diveDeeper$iv$iv) {
                                i5 = i2;
                                count$iv$iv = i;
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                zOrder$iv2 = zOrder$iv;
                                i4 = i4;
                                break;
                            }
                        } else {
                            i = count$iv$iv;
                            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                            zOrder$iv = zOrder$iv2;
                            i4 = i4;
                            i2 = i5;
                            z = z3;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        z3 = z;
                        i5 = i2;
                        count$iv$iv = i;
                        $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                        zOrder$iv2 = zOrder$iv;
                        i4 = i4;
                    }
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            i5 = i5;
            count$iv$iv = count$iv$iv;
            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
            zOrder$iv2 = zOrder$iv2;
            i4 = i4;
        }
    }

    public static final <T extends TraversableNode> void traverseDescendants(T t, Function1<? super T, ? extends TraversableNode.Companion.TraverseDescendantsAction> function1) {
        DelegatableNode $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
        int i;
        boolean zOrder$iv;
        boolean zOrder$iv2;
        boolean z;
        boolean diveDeeper$iv$iv;
        boolean z2;
        DelegatingNode this_$iv$iv$iv;
        t = t;
        T $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = t;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(262144);
        boolean zOrder$iv3 = false;
        boolean value$iv$iv$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        boolean z3 = false;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            boolean z4 = true;
            if (!(branches$iv$iv.getSize() != 0 ? true : z3)) {
                return;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv != null && node$iv$iv.getIsAttached()) {
                        if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                            Modifier.Node node$iv = node$iv$iv;
                            int i2 = iM6860constructorimpl;
                            MutableVector mutableVector = null;
                            Modifier.Node this_$iv$iv$iv2 = node$iv;
                            while (true) {
                                if (this_$iv$iv$iv2 == null) {
                                    $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                    i = iM6860constructorimpl;
                                    zOrder$iv = zOrder$iv3;
                                    z = z4;
                                    zOrder$iv2 = false;
                                    diveDeeper$iv$iv = z;
                                    break;
                                }
                                if (this_$iv$iv$iv2 instanceof TraversableNode) {
                                    Object it$iv = this_$iv$iv$iv2;
                                    $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                    TraversableNode it = (TraversableNode) it$iv;
                                    i = iM6860constructorimpl;
                                    zOrder$iv = zOrder$iv3;
                                    TraversableNode.Companion.TraverseDescendantsAction action = (Intrinsics.areEqual(t.getTraverseKey(), it.getTraverseKey()) && Actual_jvmKt.areObjectsOfSameType(t, it)) ? function1.invoke(it) : TraversableNode.Companion.TraverseDescendantsAction.ContinueTraversal;
                                    if (action == TraversableNode.Companion.TraverseDescendantsAction.CancelTraversal) {
                                        return;
                                    }
                                    if (!(action != TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal)) {
                                        z = true;
                                        zOrder$iv2 = false;
                                        diveDeeper$iv$iv = false;
                                        break;
                                    }
                                    z2 = true;
                                } else {
                                    $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                                    i = iM6860constructorimpl;
                                    zOrder$iv = zOrder$iv3;
                                    if (((this_$iv$iv$iv2.getKindSet() & i2) != 0 ? 1 : 0) == 0 || !(this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                        z2 = true;
                                    } else {
                                        int count$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                        int $i$f$forEachImmediateDelegate$ui = 0;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if (((next$iv$iv.getKindSet() & i2) != 0 ? 1 : 0) != 0) {
                                                count$iv$iv++;
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                if (count$iv$iv == 1) {
                                                    this_$iv$iv$iv2 = next$iv$iv;
                                                    $i$f$forEachImmediateDelegate$ui = $i$f$forEachImmediateDelegate$ui;
                                                } else {
                                                    MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                    Modifier.Node theNode$iv$iv = this_$iv$iv$iv2;
                                                    if (theNode$iv$iv != null) {
                                                        if (mutableVector2 != null) {
                                                            mutableVector2.add(theNode$iv$iv);
                                                        }
                                                        this_$iv$iv$iv2 = null;
                                                    }
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(next$iv$iv);
                                                    }
                                                    mutableVector = mutableVector2;
                                                    count$iv$iv = count$iv$iv;
                                                }
                                            } else {
                                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                                $i$f$forEachImmediateDelegate$ui = $i$f$forEachImmediateDelegate$ui;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            this_$iv$iv$iv3 = this_$iv$iv$iv;
                                            $i$f$forEachImmediateDelegate$ui = $i$f$forEachImmediateDelegate$ui;
                                        }
                                        z2 = true;
                                        if (count$iv$iv == 1) {
                                            z4 = true;
                                            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                            iM6860constructorimpl = i;
                                            zOrder$iv3 = zOrder$iv;
                                        }
                                    }
                                }
                                this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector);
                                z4 = z2;
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                iM6860constructorimpl = i;
                                zOrder$iv3 = zOrder$iv;
                            }
                            if (!diveDeeper$iv$iv) {
                                z3 = zOrder$iv2;
                                $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                                iM6860constructorimpl = i;
                                zOrder$iv3 = zOrder$iv;
                                break;
                            }
                        } else {
                            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
                            i = iM6860constructorimpl;
                            zOrder$iv = zOrder$iv3;
                            zOrder$iv2 = z3;
                            z = z4;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        t = t;
                        z4 = z;
                        z3 = zOrder$iv2;
                        $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv;
                        iM6860constructorimpl = i;
                        zOrder$iv3 = zOrder$iv;
                    }
                }
            }
            boolean zOrder$iv4 = zOrder$iv3;
            boolean zOrder$iv5 = z3;
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            z3 = zOrder$iv5;
            $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtreeIf_u2dY_u2dYKmho_u24default$iv2;
            iM6860constructorimpl = iM6860constructorimpl;
            zOrder$iv3 = zOrder$iv4;
        }
    }
}
