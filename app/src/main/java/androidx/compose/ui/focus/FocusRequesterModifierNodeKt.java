package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: FocusRequesterModifierNode.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0002H\u0000¨\u0006\t"}, d2 = {"requestFocus", "", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "captureFocus", "freeFocus", "saveFocusedChild", "restoreFocusedChild", "pinFocusedChild", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FocusRequesterModifierNodeKt {
    public static final boolean requestFocus(FocusRequesterModifierNode $this$requestFocus) {
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int i;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$requestFocus;
        int mask$iv$iv = NodeKind.m6860constructorimpl(1024);
        boolean zOrder$iv = false;
        int i2 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2.getNode();
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i3 = 1;
            if (nodePop == null) {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                int i4 = mask$iv$iv;
                boolean zOrder$iv$iv = false;
                boolean value$iv$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0 ? true : z)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv = mask$iv$iv;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                Modifier.Node this_$iv$iv$iv = it$iv;
                                while (this_$iv$iv$iv != null) {
                                    if (this_$iv$iv$iv instanceof FocusTargetNode) {
                                        FocusTargetNode focusTarget = (FocusTargetNode) this_$iv$iv$iv;
                                        if (ComposeUiFlags.isRequestFocusOnNonFocusableFocusTargetEnabled) {
                                            return FocusTargetModifierNode.m4759requestFocus3ESFkO8$default(focusTarget, 0, 1, null);
                                        }
                                        return focusTarget.fetchFocusProperties$ui().getCanFocus() ? FocusTargetModifierNode.m4759requestFocus3ESFkO8$default(focusTarget, 0, 1, null) : TwoDimensionalFocusSearchKt.m4798findChildCorrespondingToFocusEnterOMvw8(focusTarget, FocusDirection.INSTANCE.m4732getEnterdhqQ8s(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusRequesterModifierNodeKt$requestFocus$1$1
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Boolean invoke(FocusTargetNode it) {
                                                return Boolean.valueOf(FocusTargetModifierNode.m4759requestFocus3ESFkO8$default(it, 0, 1, null));
                                            }
                                        });
                                    }
                                    zOrder$iv$iv = zOrder$iv$iv;
                                    mask$iv$iv = mask$iv$iv;
                                    zOrder$iv = zOrder$iv;
                                    i2 = i2;
                                    if (((this_$iv$iv$iv.getKindSet() & i4) != 0 ? 1 : 0) == 0 || !(this_$iv$iv$iv instanceof DelegatingNode)) {
                                        this_$iv$iv$iv = DelegatableNodeKt.pop(mutableVector2);
                                    } else {
                                        int count$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) this_$iv$iv$iv;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if (((next$iv$iv.getKindSet() & i4) != 0 ? 1 : 0) != 0) {
                                                count$iv$iv++;
                                                if (count$iv$iv == 1) {
                                                    this_$iv$iv$iv = next$iv$iv;
                                                    this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                                } else {
                                                    MutableVector mutableVector3 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                    Modifier.Node theNode$iv$iv = this_$iv$iv$iv;
                                                    if (theNode$iv$iv != null) {
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(theNode$iv$iv);
                                                        }
                                                        this_$iv$iv$iv = null;
                                                    }
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv$iv);
                                                    }
                                                    mutableVector2 = mutableVector3;
                                                    count$iv$iv = count$iv$iv;
                                                }
                                            } else {
                                                this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                        }
                                        if (count$iv$iv != 1) {
                                            this_$iv$iv$iv = DelegatableNodeKt.pop(mutableVector2);
                                        }
                                    }
                                }
                                mask$iv$iv = mask$iv$iv;
                                z = false;
                                break;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            mask$iv$iv = mask$iv$iv;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            } else {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode focusTarget2 = (FocusTargetNode) nodePop;
                    if (!ComposeUiFlags.isRequestFocusOnNonFocusableFocusTargetEnabled && !focusTarget2.fetchFocusProperties$ui().getCanFocus()) {
                        return TwoDimensionalFocusSearchKt.m4798findChildCorrespondingToFocusEnterOMvw8(focusTarget2, FocusDirection.INSTANCE.m4732getEnterdhqQ8s(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusRequesterModifierNodeKt$requestFocus$1$1
                            @Override // kotlin.jvm.functions.Function1
                            public final Boolean invoke(FocusTargetNode it) {
                                return Boolean.valueOf(FocusTargetModifierNode.m4759requestFocus3ESFkO8$default(it, 0, 1, null));
                            }
                        });
                    }
                    return FocusTargetModifierNode.m4759requestFocus3ESFkO8$default(focusTarget2, 0, 1, null);
                }
                Modifier.Node this_$iv$iv$iv3 = nodePop;
                if (((this_$iv$iv$iv3.getKindSet() & mask$iv$iv) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                    i = mask$iv$iv;
                    nodePop = DelegatableNodeKt.pop(mutableVector);
                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                    mask$iv$iv = i;
                } else {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                        if (((next$iv$iv2.getKindSet() & mask$iv$iv) != 0 ? i3 : 0) != 0) {
                            count$iv$iv2++;
                            if (count$iv$iv2 == i3) {
                                nodePop = next$iv$iv2;
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                mask$iv$iv = mask$iv$iv;
                            } else {
                                mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                Modifier.Node theNode$iv$iv2 = nodePop;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector != null) {
                                        mutableVector.add(theNode$iv$iv2);
                                    }
                                    nodePop = null;
                                }
                                if (mutableVector != null) {
                                    mutableVector.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            mask$iv$iv = mask$iv$iv;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        mask$iv$iv = mask$iv$iv;
                        i3 = 1;
                    }
                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                    i = mask$iv$iv;
                    if (count$iv$iv2 == 1) {
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                        mask$iv$iv = i;
                    } else {
                        nodePop = DelegatableNodeKt.pop(mutableVector);
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                        mask$iv$iv = i;
                    }
                }
            }
        }
    }

    public static final boolean captureFocus(FocusRequesterModifierNode $this$captureFocus) {
        int mask$iv$iv;
        boolean zOrder$iv;
        int mask$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int i;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$captureFocus;
        int mask$iv$iv3 = NodeKind.m6860constructorimpl(1024);
        boolean zOrder$iv2 = false;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2.getNode();
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i2 = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode it = (FocusTargetNode) nodePop;
                    if (FocusTransactionsKt.captureFocus(it)) {
                        return true;
                    }
                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                    i = mask$iv$iv3;
                } else {
                    Modifier.Node this_$iv$iv$iv = nodePop;
                    if (((this_$iv$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i = mask$iv$iv3;
                    } else {
                        int count$iv$iv = 0;
                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                            if (((next$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? i2 : 0) != 0) {
                                count$iv$iv++;
                                if (count$iv$iv == i2) {
                                    nodePop = next$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                    mask$iv$iv3 = mask$iv$iv3;
                                } else {
                                    mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                    Modifier.Node theNode$iv$iv = nodePop;
                                    if (theNode$iv$iv != null) {
                                        if (mutableVector != null) {
                                            mutableVector.add(theNode$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(next$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                mask$iv$iv3 = mask$iv$iv3;
                            }
                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            mask$iv$iv3 = mask$iv$iv3;
                            i2 = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i = mask$iv$iv3;
                        if (count$iv$iv == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            mask$iv$iv3 = i;
                        }
                    }
                }
                nodePop = DelegatableNodeKt.pop(mutableVector);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                mask$iv$iv3 = i;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                int i3 = mask$iv$iv3;
                boolean zOrder$iv$iv = false;
                boolean value$iv$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0 ? true : z)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    zOrder$iv$iv = zOrder$iv$iv;
                                    boolean zOrder$iv$iv2 = nodePop2 instanceof FocusTargetNode;
                                    if (zOrder$iv$iv2) {
                                        FocusTargetNode it2 = (FocusTargetNode) nodePop2;
                                        if (FocusTransactionsKt.captureFocus(it2)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        zOrder$iv = zOrder$iv2;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv3 = nodePop2;
                                        if (((this_$iv$iv$iv3.getKindSet() & i3) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                        } else {
                                            int count$iv$iv2 = 0;
                                            DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                            Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                                            while (node$iv$iv$iv2 != null) {
                                                Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                                                if (((next$iv$iv2.getKindSet() & i3) != 0 ? 1 : 0) != 0) {
                                                    count$iv$iv2++;
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    if (count$iv$iv2 == 1) {
                                                        nodePop2 = next$iv$iv2;
                                                        zOrder$iv2 = zOrder$iv2;
                                                    } else {
                                                        MutableVector mutableVector3 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                        Modifier.Node theNode$iv$iv2 = nodePop2;
                                                        if (theNode$iv$iv2 != null) {
                                                            if (mutableVector3 != null) {
                                                                mutableVector3.add(theNode$iv$iv2);
                                                            }
                                                            nodePop2 = null;
                                                        }
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(next$iv$iv2);
                                                        }
                                                        mutableVector2 = mutableVector3;
                                                        count$iv$iv2 = count$iv$iv2;
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    zOrder$iv2 = zOrder$iv2;
                                                }
                                                node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                zOrder$iv2 = zOrder$iv2;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                            if (count$iv$iv2 == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                zOrder$iv2 = zOrder$iv;
                                            }
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                    mask$iv$iv3 = mask$iv$iv;
                                    zOrder$iv2 = zOrder$iv;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            mask$iv$iv3 = mask$iv$iv3;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }

    public static final boolean freeFocus(FocusRequesterModifierNode $this$freeFocus) {
        int mask$iv$iv;
        boolean zOrder$iv;
        int mask$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int i;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$freeFocus;
        int mask$iv$iv3 = NodeKind.m6860constructorimpl(1024);
        boolean zOrder$iv2 = false;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2.getNode();
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i2 = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode it = (FocusTargetNode) nodePop;
                    if (FocusTransactionsKt.freeFocus(it)) {
                        return true;
                    }
                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                    i = mask$iv$iv3;
                } else {
                    Modifier.Node this_$iv$iv$iv = nodePop;
                    if (((this_$iv$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i = mask$iv$iv3;
                    } else {
                        int count$iv$iv = 0;
                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                            if (((next$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? i2 : 0) != 0) {
                                count$iv$iv++;
                                if (count$iv$iv == i2) {
                                    nodePop = next$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                    mask$iv$iv3 = mask$iv$iv3;
                                } else {
                                    mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                    Modifier.Node theNode$iv$iv = nodePop;
                                    if (theNode$iv$iv != null) {
                                        if (mutableVector != null) {
                                            mutableVector.add(theNode$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(next$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                mask$iv$iv3 = mask$iv$iv3;
                            }
                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            mask$iv$iv3 = mask$iv$iv3;
                            i2 = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i = mask$iv$iv3;
                        if (count$iv$iv == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            mask$iv$iv3 = i;
                        }
                    }
                }
                nodePop = DelegatableNodeKt.pop(mutableVector);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                mask$iv$iv3 = i;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                int i3 = mask$iv$iv3;
                boolean zOrder$iv$iv = false;
                boolean value$iv$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0 ? true : z)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    zOrder$iv$iv = zOrder$iv$iv;
                                    boolean zOrder$iv$iv2 = nodePop2 instanceof FocusTargetNode;
                                    if (zOrder$iv$iv2) {
                                        FocusTargetNode it2 = (FocusTargetNode) nodePop2;
                                        if (FocusTransactionsKt.freeFocus(it2)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        zOrder$iv = zOrder$iv2;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv3 = nodePop2;
                                        if (((this_$iv$iv$iv3.getKindSet() & i3) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                        } else {
                                            int count$iv$iv2 = 0;
                                            DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                            Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                                            while (node$iv$iv$iv2 != null) {
                                                Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                                                if (((next$iv$iv2.getKindSet() & i3) != 0 ? 1 : 0) != 0) {
                                                    count$iv$iv2++;
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    if (count$iv$iv2 == 1) {
                                                        nodePop2 = next$iv$iv2;
                                                        zOrder$iv2 = zOrder$iv2;
                                                    } else {
                                                        MutableVector mutableVector3 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                        Modifier.Node theNode$iv$iv2 = nodePop2;
                                                        if (theNode$iv$iv2 != null) {
                                                            if (mutableVector3 != null) {
                                                                mutableVector3.add(theNode$iv$iv2);
                                                            }
                                                            nodePop2 = null;
                                                        }
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(next$iv$iv2);
                                                        }
                                                        mutableVector2 = mutableVector3;
                                                        count$iv$iv2 = count$iv$iv2;
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    zOrder$iv2 = zOrder$iv2;
                                                }
                                                node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                zOrder$iv2 = zOrder$iv2;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                            if (count$iv$iv2 == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                zOrder$iv2 = zOrder$iv;
                                            }
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                    mask$iv$iv3 = mask$iv$iv;
                                    zOrder$iv2 = zOrder$iv;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            mask$iv$iv3 = mask$iv$iv3;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }

    public static final boolean saveFocusedChild(FocusRequesterModifierNode $this$saveFocusedChild) {
        int mask$iv$iv;
        boolean zOrder$iv;
        int mask$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int i;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$saveFocusedChild;
        int mask$iv$iv3 = NodeKind.m6860constructorimpl(1024);
        boolean zOrder$iv2 = false;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2.getNode();
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i2 = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode it = (FocusTargetNode) nodePop;
                    if (FocusRestorerKt.saveFocusedChild(it)) {
                        return true;
                    }
                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                    i = mask$iv$iv3;
                } else {
                    Modifier.Node this_$iv$iv$iv = nodePop;
                    if (((this_$iv$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i = mask$iv$iv3;
                    } else {
                        int count$iv$iv = 0;
                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                            if (((next$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? i2 : 0) != 0) {
                                count$iv$iv++;
                                if (count$iv$iv == i2) {
                                    nodePop = next$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                    mask$iv$iv3 = mask$iv$iv3;
                                } else {
                                    mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                    Modifier.Node theNode$iv$iv = nodePop;
                                    if (theNode$iv$iv != null) {
                                        if (mutableVector != null) {
                                            mutableVector.add(theNode$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(next$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                mask$iv$iv3 = mask$iv$iv3;
                            }
                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            mask$iv$iv3 = mask$iv$iv3;
                            i2 = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i = mask$iv$iv3;
                        if (count$iv$iv == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            mask$iv$iv3 = i;
                        }
                    }
                }
                nodePop = DelegatableNodeKt.pop(mutableVector);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                mask$iv$iv3 = i;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                int i3 = mask$iv$iv3;
                boolean zOrder$iv$iv = false;
                boolean value$iv$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0 ? true : z)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    zOrder$iv$iv = zOrder$iv$iv;
                                    boolean zOrder$iv$iv2 = nodePop2 instanceof FocusTargetNode;
                                    if (zOrder$iv$iv2) {
                                        FocusTargetNode it2 = (FocusTargetNode) nodePop2;
                                        if (FocusRestorerKt.saveFocusedChild(it2)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        zOrder$iv = zOrder$iv2;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv3 = nodePop2;
                                        if (((this_$iv$iv$iv3.getKindSet() & i3) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                        } else {
                                            int count$iv$iv2 = 0;
                                            DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                            Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                                            while (node$iv$iv$iv2 != null) {
                                                Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                                                if (((next$iv$iv2.getKindSet() & i3) != 0 ? 1 : 0) != 0) {
                                                    count$iv$iv2++;
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    if (count$iv$iv2 == 1) {
                                                        nodePop2 = next$iv$iv2;
                                                        zOrder$iv2 = zOrder$iv2;
                                                    } else {
                                                        MutableVector mutableVector3 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                        Modifier.Node theNode$iv$iv2 = nodePop2;
                                                        if (theNode$iv$iv2 != null) {
                                                            if (mutableVector3 != null) {
                                                                mutableVector3.add(theNode$iv$iv2);
                                                            }
                                                            nodePop2 = null;
                                                        }
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(next$iv$iv2);
                                                        }
                                                        mutableVector2 = mutableVector3;
                                                        count$iv$iv2 = count$iv$iv2;
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    zOrder$iv2 = zOrder$iv2;
                                                }
                                                node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                zOrder$iv2 = zOrder$iv2;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                            if (count$iv$iv2 == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                zOrder$iv2 = zOrder$iv;
                                            }
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                    mask$iv$iv3 = mask$iv$iv;
                                    zOrder$iv2 = zOrder$iv;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            mask$iv$iv3 = mask$iv$iv3;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }

    public static final boolean restoreFocusedChild(FocusRequesterModifierNode $this$restoreFocusedChild) {
        int mask$iv$iv;
        boolean zOrder$iv;
        int mask$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int i;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$restoreFocusedChild;
        int mask$iv$iv3 = NodeKind.m6860constructorimpl(1024);
        boolean zOrder$iv2 = false;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2.getNode();
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            boolean z = false;
            int i2 = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    FocusTargetNode it = (FocusTargetNode) nodePop;
                    if (FocusRestorerKt.restoreFocusedChild(it)) {
                        return true;
                    }
                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                    i = mask$iv$iv3;
                } else {
                    Modifier.Node this_$iv$iv$iv = nodePop;
                    if (((this_$iv$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i = mask$iv$iv3;
                    } else {
                        int count$iv$iv = 0;
                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                            if (((next$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? i2 : 0) != 0) {
                                count$iv$iv++;
                                if (count$iv$iv == i2) {
                                    nodePop = next$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                    mask$iv$iv3 = mask$iv$iv3;
                                } else {
                                    mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                    Modifier.Node theNode$iv$iv = nodePop;
                                    if (theNode$iv$iv != null) {
                                        if (mutableVector != null) {
                                            mutableVector.add(theNode$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(next$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                mask$iv$iv3 = mask$iv$iv3;
                            }
                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            mask$iv$iv3 = mask$iv$iv3;
                            i2 = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i = mask$iv$iv3;
                        if (count$iv$iv == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            mask$iv$iv3 = i;
                        }
                    }
                }
                nodePop = DelegatableNodeKt.pop(mutableVector);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                mask$iv$iv3 = i;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                int i3 = mask$iv$iv3;
                boolean zOrder$iv$iv = false;
                boolean value$iv$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if (!(branches$iv$iv.getSize() != 0 ? true : z)) {
                        return false;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    zOrder$iv$iv = zOrder$iv$iv;
                                    boolean zOrder$iv$iv2 = nodePop2 instanceof FocusTargetNode;
                                    if (zOrder$iv$iv2) {
                                        FocusTargetNode it2 = (FocusTargetNode) nodePop2;
                                        if (FocusRestorerKt.restoreFocusedChild(it2)) {
                                            return true;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        zOrder$iv = zOrder$iv2;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv3 = nodePop2;
                                        if (((this_$iv$iv$iv3.getKindSet() & i3) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                        } else {
                                            int count$iv$iv2 = 0;
                                            DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                            Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                                            while (node$iv$iv$iv2 != null) {
                                                Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                                                if (((next$iv$iv2.getKindSet() & i3) != 0 ? 1 : 0) != 0) {
                                                    count$iv$iv2++;
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    if (count$iv$iv2 == 1) {
                                                        nodePop2 = next$iv$iv2;
                                                        zOrder$iv2 = zOrder$iv2;
                                                    } else {
                                                        MutableVector mutableVector3 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                        Modifier.Node theNode$iv$iv2 = nodePop2;
                                                        if (theNode$iv$iv2 != null) {
                                                            if (mutableVector3 != null) {
                                                                mutableVector3.add(theNode$iv$iv2);
                                                            }
                                                            nodePop2 = null;
                                                        }
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(next$iv$iv2);
                                                        }
                                                        mutableVector2 = mutableVector3;
                                                        count$iv$iv2 = count$iv$iv2;
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    zOrder$iv2 = zOrder$iv2;
                                                }
                                                node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                zOrder$iv2 = zOrder$iv2;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                            if (count$iv$iv2 == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                zOrder$iv2 = zOrder$iv;
                                            }
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                    mask$iv$iv3 = mask$iv$iv;
                                    zOrder$iv2 = zOrder$iv;
                                }
                                mask$iv$iv3 = mask$iv$iv3;
                                z = false;
                                break;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            mask$iv$iv3 = mask$iv$iv3;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }

    public static final PinnableContainer.PinnedHandle pinFocusedChild(FocusRequesterModifierNode $this$pinFocusedChild) {
        int mask$iv$iv;
        boolean zOrder$iv;
        int i;
        int mask$iv$iv2;
        DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
        int i2;
        FocusRequesterModifierNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$pinFocusedChild;
        int mask$iv$iv3 = NodeKind.m6860constructorimpl(1024);
        boolean zOrder$iv2 = false;
        int i3 = 0;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2.getNode();
        MutableVector mutableVector = null;
        Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        while (true) {
            int i4 = 0;
            int i5 = 1;
            if (nodePop != null) {
                if (nodePop instanceof FocusTargetNode) {
                    PinnableContainer.PinnedHandle it = FocusRestorerKt.pinFocusedChild((FocusTargetNode) nodePop);
                    if (it != null) {
                        return it;
                    }
                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                    i2 = mask$iv$iv3;
                } else {
                    Modifier.Node this_$iv$iv$iv = nodePop;
                    if (((this_$iv$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i2 = mask$iv$iv3;
                    } else {
                        int count$iv$iv = 0;
                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                            if (((next$iv$iv.getKindSet() & mask$iv$iv3) != 0 ? i5 : 0) != 0) {
                                count$iv$iv++;
                                if (count$iv$iv == i5) {
                                    nodePop = next$iv$iv;
                                    $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                    mask$iv$iv3 = mask$iv$iv3;
                                } else {
                                    mutableVector = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                    Modifier.Node theNode$iv$iv = nodePop;
                                    if (theNode$iv$iv != null) {
                                        if (mutableVector != null) {
                                            mutableVector.add(theNode$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector != null) {
                                        mutableVector.add(next$iv$iv);
                                    }
                                }
                            } else {
                                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                                mask$iv$iv3 = mask$iv$iv3;
                            }
                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                            mask$iv$iv3 = mask$iv$iv3;
                            i5 = 1;
                        }
                        $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                        i2 = mask$iv$iv3;
                        if (count$iv$iv == 1) {
                            $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                            mask$iv$iv3 = i2;
                        }
                    }
                }
                nodePop = DelegatableNodeKt.pop(mutableVector);
                $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv;
                mask$iv$iv3 = i2;
            } else {
                DelegatableNode $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv2;
                int i6 = mask$iv$iv3;
                boolean zOrder$iv$iv = false;
                boolean value$iv$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
                if (!value$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                }
                MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
                Modifier.Node child$iv$iv = $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
                if (child$iv$iv == null) {
                    DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSelfAndChildren_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
                } else {
                    branches$iv$iv.add(child$iv$iv);
                }
                while (true) {
                    if ((branches$iv$iv.getSize() != 0 ? 1 : i4) == 0) {
                        return null;
                    }
                    Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
                    if ((branch$iv$iv.getAggregateChildKindSet() & mask$iv$iv3) != 0) {
                        Modifier.Node node$iv$iv = branch$iv$iv;
                        while (true) {
                            if (node$iv$iv == null) {
                                mask$iv$iv3 = mask$iv$iv3;
                                i3 = i3;
                                break;
                            }
                            if ((node$iv$iv.getKindSet() & mask$iv$iv3) != 0) {
                                Modifier.Node it$iv = node$iv$iv;
                                MutableVector mutableVector2 = null;
                                Modifier.Node nodePop2 = it$iv;
                                while (nodePop2 != null) {
                                    zOrder$iv$iv = zOrder$iv$iv;
                                    boolean zOrder$iv$iv2 = nodePop2 instanceof FocusTargetNode;
                                    if (zOrder$iv$iv2) {
                                        PinnableContainer.PinnedHandle it2 = FocusRestorerKt.pinFocusedChild((FocusTargetNode) nodePop2);
                                        if (it2 != null) {
                                            return it2;
                                        }
                                        mask$iv$iv = mask$iv$iv3;
                                        zOrder$iv = zOrder$iv2;
                                        i = i3;
                                    } else {
                                        Modifier.Node this_$iv$iv$iv3 = nodePop2;
                                        if (((this_$iv$iv$iv3.getKindSet() & i6) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                            i = i3;
                                        } else {
                                            int count$iv$iv2 = 0;
                                            DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                            Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                                            while (node$iv$iv$iv2 != null) {
                                                Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                                                if (((next$iv$iv2.getKindSet() & i6) != 0 ? 1 : 0) != 0) {
                                                    count$iv$iv2++;
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    if (count$iv$iv2 == 1) {
                                                        nodePop2 = next$iv$iv2;
                                                        zOrder$iv2 = zOrder$iv2;
                                                        i3 = i3;
                                                    } else {
                                                        MutableVector mutableVector3 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                        Modifier.Node theNode$iv$iv2 = nodePop2;
                                                        if (theNode$iv$iv2 != null) {
                                                            if (mutableVector3 != null) {
                                                                mutableVector3.add(theNode$iv$iv2);
                                                            }
                                                            nodePop2 = null;
                                                        }
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(next$iv$iv2);
                                                        }
                                                        mutableVector2 = mutableVector3;
                                                        count$iv$iv2 = count$iv$iv2;
                                                    }
                                                } else {
                                                    mask$iv$iv2 = mask$iv$iv3;
                                                    zOrder$iv2 = zOrder$iv2;
                                                    i3 = i3;
                                                }
                                                node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                                                mask$iv$iv3 = mask$iv$iv2;
                                                zOrder$iv2 = zOrder$iv2;
                                                i3 = i3;
                                            }
                                            mask$iv$iv = mask$iv$iv3;
                                            zOrder$iv = zOrder$iv2;
                                            i = i3;
                                            if (count$iv$iv2 == 1) {
                                                mask$iv$iv3 = mask$iv$iv;
                                                zOrder$iv2 = zOrder$iv;
                                                i3 = i;
                                            }
                                        }
                                    }
                                    nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                    mask$iv$iv3 = mask$iv$iv;
                                    zOrder$iv2 = zOrder$iv;
                                    i3 = i;
                                }
                                i4 = 0;
                                mask$iv$iv3 = mask$iv$iv3;
                                i3 = i3;
                                break;
                            }
                            node$iv$iv = node$iv$iv.getChild();
                            mask$iv$iv3 = mask$iv$iv3;
                            i3 = i3;
                        }
                    } else {
                        DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, zOrder$iv$iv);
                    }
                }
            }
        }
    }
}
