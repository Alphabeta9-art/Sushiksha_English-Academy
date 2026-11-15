package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BeyondBoundsLayout.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\u0010\u0005\u001a\u0015\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0006¢\u0006\u0002\b\bH\u0000¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"searchBeyondBounds", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/focus/FocusTargetNode;", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "Lkotlin/ExtensionFunctionType;", "searchBeyondBounds--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetNode;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BeyondBoundsLayoutKt {
    /* JADX INFO: renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final <T> T m4722searchBeyondBoundsOMvw8(FocusTargetNode focusTargetNode, int i, Function1<? super BeyondBoundsLayout.BeyondBoundsScope, ? extends T> function1) {
        T t;
        Modifier.Node node;
        int iM6530getBeforehoxUOeE;
        FocusTargetNode focusTargetNode2;
        int i2;
        int i3;
        int i4;
        Modifier.Node node2;
        NodeChain nodes;
        FocusTargetNode focusTargetNode3;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        FocusTargetNode focusTargetNode4 = focusTargetNode;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
        int i12 = 0;
        int i13 = iM6860constructorimpl;
        if (!focusTargetNode4.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = focusTargetNode4.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode4);
        loop0: while (true) {
            if (layoutNodeRequireLayoutNode == null) {
                t = null;
                node = null;
                break;
            }
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i13) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & i13) != 0) {
                        int i14 = i13;
                        MutableVector mutableVector = null;
                        Modifier.Node nodePop = parent;
                        t = null;
                        while (nodePop != null) {
                            focusTargetNode4 = focusTargetNode4;
                            if (nodePop instanceof FocusTargetNode) {
                                node = nodePop;
                                break loop0;
                            }
                            if (((nodePop.getKindSet() & i14) != 0) && (nodePop instanceof DelegatingNode)) {
                                int i15 = 0;
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                while (delegate != null) {
                                    Modifier.Node node3 = delegate;
                                    if (((node3.getKindSet() & i14) != 0 ? 1 : 0) != 0) {
                                        i15++;
                                        i11 = iM6860constructorimpl;
                                        if (i15 == 1) {
                                            nodePop = node3;
                                            i12 = i12;
                                            i13 = i13;
                                        } else {
                                            MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                            Modifier.Node node4 = nodePop;
                                            if (node4 != null) {
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(node4);
                                                }
                                                nodePop = null;
                                            }
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(node3);
                                            }
                                            mutableVector = mutableVector2;
                                            i15 = i15;
                                        }
                                    } else {
                                        i11 = iM6860constructorimpl;
                                        i12 = i12;
                                        i13 = i13;
                                    }
                                    delegate = delegate.getChild();
                                    iM6860constructorimpl = i11;
                                    i12 = i12;
                                    i13 = i13;
                                }
                                i8 = iM6860constructorimpl;
                                i9 = i12;
                                i10 = i13;
                                if (i15 == 1) {
                                    iM6860constructorimpl = i8;
                                    i12 = i9;
                                    i13 = i10;
                                } else {
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    iM6860constructorimpl = i8;
                                    i12 = i9;
                                    i13 = i10;
                                }
                            } else {
                                i8 = iM6860constructorimpl;
                                i9 = i12;
                                i10 = i13;
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                                iM6860constructorimpl = i8;
                                i12 = i9;
                                i13 = i10;
                            }
                        }
                        focusTargetNode3 = focusTargetNode4;
                        i5 = iM6860constructorimpl;
                        i6 = i12;
                        i7 = i13;
                    } else {
                        focusTargetNode3 = focusTargetNode4;
                        i5 = iM6860constructorimpl;
                        i6 = i12;
                        i7 = i13;
                    }
                    parent = parent.getParent();
                    focusTargetNode4 = focusTargetNode3;
                    iM6860constructorimpl = i5;
                    i12 = i6;
                    i13 = i7;
                }
                focusTargetNode2 = focusTargetNode4;
                i2 = iM6860constructorimpl;
                i3 = i12;
                i4 = i13;
                node2 = null;
            } else {
                focusTargetNode2 = focusTargetNode4;
                i2 = iM6860constructorimpl;
                i3 = i12;
                i4 = i13;
                node2 = null;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? node2 : nodes.getTail();
            focusTargetNode4 = focusTargetNode2;
            iM6860constructorimpl = i2;
            i12 = i3;
            i13 = i4;
        }
        FocusTargetNode focusTargetNode5 = (FocusTargetNode) node;
        if (focusTargetNode5 != null && Intrinsics.areEqual(focusTargetNode5.getBeyondBoundsLayoutParent(), focusTargetNode.getBeyondBoundsLayoutParent())) {
            return t;
        }
        BeyondBoundsLayout beyondBoundsLayoutParent = focusTargetNode.getBeyondBoundsLayoutParent();
        if (beyondBoundsLayoutParent == null) {
            return t;
        }
        if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4738getUpdhqQ8s())) {
            iM6530getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6528getAbovehoxUOeE();
        } else if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4731getDowndhqQ8s())) {
            iM6530getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6531getBelowhoxUOeE();
        } else if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4734getLeftdhqQ8s())) {
            iM6530getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6532getLefthoxUOeE();
        } else if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4737getRightdhqQ8s())) {
            iM6530getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6533getRighthoxUOeE();
        } else if (FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4735getNextdhqQ8s())) {
            iM6530getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6529getAfterhoxUOeE();
        } else {
            if (!FocusDirection.m4727equalsimpl0(i, FocusDirection.INSTANCE.m4736getPreviousdhqQ8s())) {
                throw new IllegalStateException("Unsupported direction for beyond bounds layout".toString());
            }
            iM6530getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m6530getBeforehoxUOeE();
        }
        return (T) beyondBoundsLayoutParent.mo974layouto7g1Pn8(iM6530getBeforehoxUOeE, function1);
    }
}
