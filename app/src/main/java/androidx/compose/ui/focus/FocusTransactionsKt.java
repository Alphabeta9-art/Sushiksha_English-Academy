package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: FocusTransactions.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u001e\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a \u0010\t\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\u0001H\u0002\u001a)\u0010\n\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\b\u000f\u001a\f\u0010\u0010\u001a\u00020\u0002*\u00020\u0002H\u0002\u001a\u001b\u0010\u0011\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001b\u0010\u0015\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0016\u0010\u0014\u001a\u001b\u0010\u0017\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0018\u0010\u0014\u001a\u001b\u0010\u0019\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0004\b\u001a\u0010\u0014¨\u0006\u001b"}, d2 = {"performRequestFocus", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "captureFocus", "freeFocus", "clearFocus", "forced", "refreshFocusEvents", "grantFocus", "clearChildFocus", "requestOwnerFocus", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "requestOwnerFocus-Etdf9zw", "requireActiveChild", "performCustomRequestFocus", "Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomRequestFocus-Mxy_nc0", "(Landroidx/compose/ui/focus/FocusTargetNode;I)Landroidx/compose/ui/focus/CustomDestinationResult;", "performCustomClearFocus", "performCustomClearFocus-Mxy_nc0", "performCustomEnter", "performCustomEnter-Mxy_nc0", "performCustomExit", "performCustomExit-Mxy_nc0", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FocusTransactionsKt {

    /* JADX INFO: compiled from: FocusTransactions.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:174:0x0383 A[RETURN] */
    public static final boolean performRequestFocus(FocusTargetNode $this$performRequestFocus) {
        FocusOwner focusOwner;
        FocusStateImpl previousFocusState;
        int i;
        boolean z;
        boolean z2;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
        int i2;
        NodeChain nodes;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
        int i3;
        DelegatableNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
        FocusOwner focusOwner2;
        FocusStateImpl previousFocusState2;
        int i4;
        NodeChain nodes2;
        FocusOwner focusOwner3;
        FocusStateImpl previousFocusState3;
        FocusOwner focusOwner4;
        FocusStateImpl previousFocusState4;
        FocusOwner focusOwner5 = DelegatableNodeKt.requireOwner($this$performRequestFocus).getFocusOwner();
        FocusTargetNode previousActiveNode = focusOwner5.getActiveFocusTargetNode();
        FocusStateImpl previousFocusState5 = $this$performRequestFocus.getFocusState();
        int i5 = 1;
        if (previousActiveNode == $this$performRequestFocus) {
            $this$performRequestFocus.dispatchFocusCallbacks$ui(previousFocusState5, previousFocusState5);
            return true;
        }
        if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
            if (!((previousActiveNode == null || previousActiveNode.getIsInteropViewHost()) ? false : true) && !$this$performRequestFocus.getIsInteropViewHost() && !m4775requestOwnerFocusEtdf9zw$default($this$performRequestFocus, null, null, 3, null)) {
                return false;
            }
        } else if (previousActiveNode == null && !m4775requestOwnerFocusEtdf9zw$default($this$performRequestFocus, null, null, 3, null)) {
            return false;
        }
        MutableVector mutableVector = null;
        int i6 = 1024;
        if (previousActiveNode != null) {
            mutableVector = new MutableVector(new FocusTargetNode[16], 0);
            FocusTargetNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv4 = previousActiveNode;
            int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
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
                            MutableVector mutableVector2 = null;
                            Modifier.Node nodePop = it$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof FocusTargetNode) {
                                    mutableVector.add((FocusTargetNode) nodePop);
                                    focusOwner4 = focusOwner5;
                                    previousFocusState4 = previousFocusState5;
                                } else {
                                    Modifier.Node this_$iv$iv$iv = nodePop;
                                    if (((this_$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i5 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                        focusOwner4 = focusOwner5;
                                        previousFocusState4 = previousFocusState5;
                                    } else {
                                        int count$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if (((next$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i5 : 0) != 0) {
                                                count$iv$iv++;
                                                if (count$iv$iv == i5) {
                                                    nodePop = next$iv$iv;
                                                    focusOwner5 = focusOwner5;
                                                    previousFocusState5 = previousFocusState5;
                                                } else {
                                                    MutableVector mutableVector3 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                    Modifier.Node theNode$iv$iv = nodePop;
                                                    if (theNode$iv$iv != null) {
                                                        if (mutableVector3 != null) {
                                                            mutableVector3.add(theNode$iv$iv);
                                                        }
                                                        nodePop = null;
                                                    }
                                                    if (mutableVector3 != null) {
                                                        mutableVector3.add(next$iv$iv);
                                                    }
                                                    mutableVector2 = mutableVector3;
                                                }
                                            } else {
                                                focusOwner5 = focusOwner5;
                                                previousFocusState5 = previousFocusState5;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            focusOwner5 = focusOwner5;
                                            previousFocusState5 = previousFocusState5;
                                            i5 = 1;
                                        }
                                        focusOwner4 = focusOwner5;
                                        previousFocusState4 = previousFocusState5;
                                        if (count$iv$iv == 1) {
                                            focusOwner5 = focusOwner4;
                                            previousFocusState5 = previousFocusState4;
                                            i5 = 1;
                                        }
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                focusOwner5 = focusOwner4;
                                previousFocusState5 = previousFocusState4;
                                i5 = 1;
                            }
                            focusOwner3 = focusOwner5;
                            previousFocusState3 = previousFocusState5;
                        } else {
                            focusOwner3 = focusOwner5;
                            previousFocusState3 = previousFocusState5;
                        }
                        node$iv$iv = node$iv$iv.getParent();
                        i6 = i6;
                        focusOwner5 = focusOwner3;
                        previousFocusState5 = previousFocusState3;
                        i5 = 1;
                    }
                    focusOwner2 = focusOwner5;
                    previousFocusState2 = previousFocusState5;
                    i4 = i6;
                } else {
                    focusOwner2 = focusOwner5;
                    previousFocusState2 = previousFocusState5;
                    i4 = i6;
                }
                layout$iv$iv = layout$iv$iv.getParent$ui();
                node$iv$iv = (layout$iv$iv == null || (nodes2 = layout$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i6 = i4;
                focusOwner5 = focusOwner2;
                previousFocusState5 = previousFocusState2;
                i5 = 1;
            }
            focusOwner = focusOwner5;
            previousFocusState = previousFocusState5;
            i = i6;
        } else {
            focusOwner = focusOwner5;
            previousFocusState = previousFocusState5;
            i = 1024;
        }
        boolean shouldClearFocusFromPreviousActiveNode = true;
        MutableVector ancestorTargetNodes = new MutableVector(new FocusTargetNode[16], 0);
        FocusTargetNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5 = $this$performRequestFocus;
        int iM6860constructorimpl2 = NodeKind.m6860constructorimpl(i);
        boolean value$iv$iv$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5.getNode().getIsAttached();
        if (!value$iv$iv$iv2) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5.getNode().getParent();
        LayoutNode layout$iv$iv2 = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv5);
        while (layout$iv$iv2 != null) {
            Modifier.Node head$iv$iv2 = layout$iv$iv2.getNodes().getHead();
            if ((head$iv$iv2.getAggregateChildKindSet() & iM6860constructorimpl2) != 0) {
                while (node$iv$iv2 != null) {
                    if ((node$iv$iv2.getKindSet() & iM6860constructorimpl2) != 0) {
                        Modifier.Node it$iv2 = node$iv$iv2;
                        int i7 = iM6860constructorimpl2;
                        MutableVector mutableVector4 = null;
                        Modifier.Node nodePop2 = it$iv2;
                        while (nodePop2 != null) {
                            boolean shouldClearFocusFromPreviousActiveNode2 = shouldClearFocusFromPreviousActiveNode;
                            boolean shouldClearFocusFromPreviousActiveNode3 = nodePop2 instanceof FocusTargetNode;
                            if (shouldClearFocusFromPreviousActiveNode3) {
                                FocusTargetNode it = (FocusTargetNode) nodePop2;
                                Boolean removed = mutableVector != null ? Boolean.valueOf(mutableVector.remove(it)) : null;
                                if (removed == null || !removed.booleanValue()) {
                                    ancestorTargetNodes.add(it);
                                }
                                if (it == previousActiveNode) {
                                    shouldClearFocusFromPreviousActiveNode2 = false;
                                }
                                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5;
                                i3 = iM6860constructorimpl2;
                            } else {
                                Modifier.Node this_$iv$iv$iv3 = nodePop2;
                                if (((this_$iv$iv$iv3.getKindSet() & i7) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5;
                                    i3 = iM6860constructorimpl2;
                                } else {
                                    int count$iv$iv2 = 0;
                                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv4.getDelegate();
                                    while (node$iv$iv$iv2 != null) {
                                        Modifier.Node next$iv$iv2 = node$iv$iv$iv2;
                                        if (((next$iv$iv2.getKindSet() & i7) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv2++;
                                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5;
                                            if (count$iv$iv2 == 1) {
                                                nodePop2 = next$iv$iv2;
                                                iM6860constructorimpl2 = iM6860constructorimpl2;
                                            } else {
                                                MutableVector mutableVector5 = mutableVector4 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector4;
                                                Modifier.Node theNode$iv$iv2 = nodePop2;
                                                if (theNode$iv$iv2 != null) {
                                                    if (mutableVector5 != null) {
                                                        mutableVector5.add(theNode$iv$iv2);
                                                    }
                                                    nodePop2 = null;
                                                }
                                                if (mutableVector5 != null) {
                                                    mutableVector5.add(next$iv$iv2);
                                                }
                                                mutableVector4 = mutableVector5;
                                                count$iv$iv2 = count$iv$iv2;
                                            }
                                        } else {
                                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5;
                                            iM6860constructorimpl2 = iM6860constructorimpl2;
                                        }
                                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv3;
                                        iM6860constructorimpl2 = iM6860constructorimpl2;
                                    }
                                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5;
                                    i3 = iM6860constructorimpl2;
                                    if (count$iv$iv2 == 1) {
                                        shouldClearFocusFromPreviousActiveNode = shouldClearFocusFromPreviousActiveNode2;
                                        $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                                        iM6860constructorimpl2 = i3;
                                    }
                                }
                            }
                            shouldClearFocusFromPreviousActiveNode = shouldClearFocusFromPreviousActiveNode2;
                            nodePop2 = DelegatableNodeKt.pop(mutableVector4);
                            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv2;
                            iM6860constructorimpl2 = i3;
                        }
                    }
                    node$iv$iv2 = node$iv$iv2.getParent();
                    $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5;
                    iM6860constructorimpl2 = iM6860constructorimpl2;
                }
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5;
                i2 = iM6860constructorimpl2;
            } else {
                $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5;
                i2 = iM6860constructorimpl2;
            }
            layout$iv$iv2 = layout$iv$iv2.getParent$ui();
            node$iv$iv2 = (layout$iv$iv2 == null || (nodes = layout$iv$iv2.getNodes()) == null) ? null : nodes.getTail();
            $this$visitAncestors_u2dY_u2dYKmho_u24default$iv5 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv;
            iM6860constructorimpl2 = i2;
        }
        if (shouldClearFocusFromPreviousActiveNode) {
            if (previousActiveNode != null) {
                z = false;
                if (!clearFocus$default(previousActiveNode, false, true, 1, null)) {
                    z2 = true;
                }
                if (z2) {
                    return z;
                }
            } else {
                z = false;
            }
            z2 = z;
            if (z2) {
                return z;
            }
        }
        grantFocus($this$performRequestFocus);
        if (ComposeUiFlags.isOptimizedFocusEventDispatchEnabled && shouldClearFocusFromPreviousActiveNode && previousActiveNode != null) {
            previousActiveNode.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
        }
        if (mutableVector != null) {
            MutableVector this_$iv = mutableVector;
            int i$iv = this_$iv.getSize() - 1;
            Object[] content$iv = this_$iv.content;
            if (i$iv < content$iv.length) {
                while (i$iv >= 0) {
                    FocusTargetNode it2 = (FocusTargetNode) content$iv[i$iv];
                    if (focusOwner.getActiveFocusTargetNode() != $this$performRequestFocus) {
                        return false;
                    }
                    it2.dispatchFocusCallbacks$ui(FocusStateImpl.ActiveParent, FocusStateImpl.Inactive);
                    i$iv--;
                }
            }
        }
        int i$iv2 = ancestorTargetNodes.getSize() - 1;
        Object[] content$iv2 = ancestorTargetNodes.content;
        if (i$iv2 < content$iv2.length) {
            while (i$iv2 >= 0) {
                FocusTargetNode it3 = (FocusTargetNode) content$iv2[i$iv2];
                if (focusOwner.getActiveFocusTargetNode() != $this$performRequestFocus) {
                    return false;
                }
                it3.dispatchFocusCallbacks$ui(it3 == previousActiveNode ? FocusStateImpl.Active : FocusStateImpl.Inactive, FocusStateImpl.ActiveParent);
                i$iv2--;
            }
        }
        if (focusOwner.getActiveFocusTargetNode() != $this$performRequestFocus) {
            return false;
        }
        $this$performRequestFocus.dispatchFocusCallbacks$ui(previousFocusState, FocusStateImpl.Active);
        if (focusOwner.getActiveFocusTargetNode() != $this$performRequestFocus) {
            return false;
        }
        if (!ComposeUiFlags.isViewFocusFixEnabled || DelegatableNodeKt.requireLayoutNode($this$performRequestFocus).getInteropView() != null) {
            return true;
        }
        m4774requestOwnerFocusEtdf9zw($this$performRequestFocus, FocusDirection.m4724boximpl(FocusDirection.INSTANCE.m4735getNextdhqQ8s()), null);
        return true;
    }

    public static final boolean captureFocus(FocusTargetNode $this$captureFocus) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$captureFocus.getFocusState().ordinal()]) {
            case 1:
                DelegatableNodeKt.requireOwner($this$captureFocus).getFocusOwner().setFocusCaptured(true);
                $this$captureFocus.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Captured);
                return true;
            case 2:
                return true;
            case 3:
            case 4:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final boolean freeFocus(FocusTargetNode $this$freeFocus) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$freeFocus.getFocusState().ordinal()]) {
            case 1:
                return true;
            case 2:
                DelegatableNodeKt.requireOwner($this$freeFocus).getFocusOwner().setFocusCaptured(false);
                $this$freeFocus.dispatchFocusCallbacks$ui(FocusStateImpl.Captured, FocusStateImpl.Active);
                return true;
            case 3:
            case 4:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ boolean clearFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return clearFocus(focusTargetNode, z, z2);
    }

    public static final boolean clearFocus(FocusTargetNode $this$clearFocus, boolean forced, boolean refreshFocusEvents) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$clearFocus.getFocusState().ordinal()]) {
            case 1:
                if (ComposeUiFlags.isOptimizedFocusEventDispatchEnabled) {
                    return true;
                }
                DelegatableNodeKt.requireOwner($this$clearFocus).getFocusOwner().setActiveFocusTargetNode(null);
                if (!refreshFocusEvents) {
                    return true;
                }
                $this$clearFocus.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
                return true;
            case 2:
                if (forced && !ComposeUiFlags.isOptimizedFocusEventDispatchEnabled) {
                    DelegatableNodeKt.requireOwner($this$clearFocus).getFocusOwner().setActiveFocusTargetNode(null);
                    if (refreshFocusEvents) {
                        $this$clearFocus.dispatchFocusCallbacks$ui(FocusStateImpl.Captured, FocusStateImpl.Inactive);
                    }
                }
                return forced;
            case 3:
                if (clearChildFocus($this$clearFocus, forced, refreshFocusEvents)) {
                    if (!refreshFocusEvents) {
                        return true;
                    }
                    $this$clearFocus.dispatchFocusCallbacks$ui(FocusStateImpl.ActiveParent, FocusStateImpl.Inactive);
                    return true;
                }
                return false;
            case 4:
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final boolean grantFocus(final FocusTargetNode $this$grantFocus) {
        ObserverModifierNodeKt.observeReads($this$grantFocus, new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusTransactionsKt.grantFocus.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                $this$grantFocus.fetchFocusProperties$ui();
            }
        });
        switch (WhenMappings.$EnumSwitchMapping$0[$this$grantFocus.getFocusState().ordinal()]) {
            case 1:
            case 2:
                return true;
            case 3:
            case 4:
                DelegatableNodeKt.requireOwner($this$grantFocus).getFocusOwner().setActiveFocusTargetNode($this$grantFocus);
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    static /* synthetic */ boolean clearChildFocus$default(FocusTargetNode focusTargetNode, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        return clearChildFocus(focusTargetNode, z, z2);
    }

    private static final boolean clearChildFocus(FocusTargetNode $this$clearChildFocus, boolean forced, boolean refreshFocusEvents) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild($this$clearChildFocus);
        if (activeChild != null) {
            return clearFocus(activeChild, forced, refreshFocusEvents);
        }
        return true;
    }

    /* JADX INFO: renamed from: requestOwnerFocus-Etdf9zw$default, reason: not valid java name */
    static /* synthetic */ boolean m4775requestOwnerFocusEtdf9zw$default(FocusTargetNode focusTargetNode, FocusDirection focusDirection, Rect rect, int i, Object obj) {
        if ((i & 1) != 0) {
            focusDirection = null;
        }
        if ((i & 2) != 0) {
            rect = null;
        }
        return m4774requestOwnerFocusEtdf9zw(focusTargetNode, focusDirection, rect);
    }

    /* JADX INFO: renamed from: requestOwnerFocus-Etdf9zw, reason: not valid java name */
    private static final boolean m4774requestOwnerFocusEtdf9zw(FocusTargetNode $this$requestOwnerFocus_u2dEtdf9zw, FocusDirection focusDirection, Rect previouslyFocusedRect) {
        return DelegatableNodeKt.requireOwner($this$requestOwnerFocus_u2dEtdf9zw).getFocusOwner().mo4748requestOwnerFocus7o62pno(focusDirection, previouslyFocusedRect);
    }

    private static final FocusTargetNode requireActiveChild(FocusTargetNode $this$requireActiveChild) {
        FocusTargetNode activeChild = FocusTraversalKt.getActiveChild($this$requireActiveChild);
        if (activeChild != null) {
            return activeChild;
        }
        throw new IllegalArgumentException("ActiveParent with no focused child".toString());
    }

    /* JADX INFO: renamed from: performCustomRequestFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m4773performCustomRequestFocusMxy_nc0(FocusTargetNode $this$performCustomRequestFocus_u2dMxy_nc0, int i) {
        boolean z;
        Modifier.Node node;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        int i2;
        int i3;
        NodeChain nodes;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv2;
        int i4;
        int i5;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv3;
        switch (WhenMappings.$EnumSwitchMapping$0[$this$performCustomRequestFocus_u2dMxy_nc0.getFocusState().ordinal()]) {
            case 1:
            case 2:
                return CustomDestinationResult.None;
            case 3:
                return m4770performCustomClearFocusMxy_nc0(requireActiveChild($this$performCustomRequestFocus_u2dMxy_nc0), i);
            case 4:
                FocusTargetNode $this$nearestAncestor_u2d64DMado$iv4 = $this$performCustomRequestFocus_u2dMxy_nc0;
                int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
                int i6 = 0;
                boolean value$iv$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv4.getNode().getIsAttached();
                if (!value$iv$iv$iv$iv) {
                    InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
                }
                Modifier.Node node$iv$iv$iv = $this$nearestAncestor_u2d64DMado$iv4.getNode().getParent();
                LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv4);
                while (true) {
                    if (layout$iv$iv$iv != null) {
                        Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                        if ((head$iv$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                            while (true) {
                                if (node$iv$iv$iv != null) {
                                    if ((node$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                                        Modifier.Node it$iv$iv = node$iv$iv$iv;
                                        MutableVector mutableVector = null;
                                        Modifier.Node nodePop = it$iv$iv;
                                        while (true) {
                                            if (nodePop == null) {
                                                continue;
                                            } else if (nodePop instanceof FocusTargetNode) {
                                                node = nodePop;
                                                z = true;
                                            } else {
                                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                                if (((this_$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv4;
                                                    i4 = iM6860constructorimpl;
                                                    i5 = i6;
                                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv2;
                                                    iM6860constructorimpl = i4;
                                                    i6 = i5;
                                                } else {
                                                    int count$iv$iv$iv = 0;
                                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                                    while (node$iv$iv$iv$iv != null) {
                                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                                        if (((next$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? 1 : 0) != 0) {
                                                            count$iv$iv$iv++;
                                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                            if (count$iv$iv$iv == 1) {
                                                                nodePop = next$iv$iv$iv;
                                                                iM6860constructorimpl = iM6860constructorimpl;
                                                                i6 = i6;
                                                            } else {
                                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                                Modifier.Node theNode$iv$iv$iv = nodePop;
                                                                if (theNode$iv$iv$iv != null) {
                                                                    if (mutableVector2 != null) {
                                                                        mutableVector2.add(theNode$iv$iv$iv);
                                                                    }
                                                                    nodePop = null;
                                                                }
                                                                if (mutableVector2 != null) {
                                                                    mutableVector2.add(next$iv$iv$iv);
                                                                }
                                                                mutableVector = mutableVector2;
                                                            }
                                                        } else {
                                                            $this$nearestAncestor_u2d64DMado$iv3 = $this$nearestAncestor_u2d64DMado$iv4;
                                                            iM6860constructorimpl = iM6860constructorimpl;
                                                            i6 = i6;
                                                        }
                                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                                        $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv3;
                                                        iM6860constructorimpl = iM6860constructorimpl;
                                                        i6 = i6;
                                                    }
                                                    $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv4;
                                                    i4 = iM6860constructorimpl;
                                                    i5 = i6;
                                                    if (count$iv$iv$iv == 1) {
                                                        $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv2;
                                                        iM6860constructorimpl = i4;
                                                        i6 = i5;
                                                    } else {
                                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                                        $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv2;
                                                        iM6860constructorimpl = i4;
                                                        i6 = i5;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    node$iv$iv$iv = node$iv$iv$iv.getParent();
                                    $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv4;
                                    iM6860constructorimpl = iM6860constructorimpl;
                                    i6 = i6;
                                } else {
                                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv4;
                                    i2 = iM6860constructorimpl;
                                    i3 = i6;
                                }
                            }
                        } else {
                            $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv4;
                            i2 = iM6860constructorimpl;
                            i3 = i6;
                        }
                        layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                        node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
                        $this$nearestAncestor_u2d64DMado$iv4 = $this$nearestAncestor_u2d64DMado$iv;
                        iM6860constructorimpl = i2;
                        i6 = i3;
                    } else {
                        z = true;
                        node = null;
                    }
                }
                FocusTargetNode focusParent = (FocusTargetNode) node;
                if (focusParent == null) {
                    return CustomDestinationResult.None;
                }
                switch (WhenMappings.$EnumSwitchMapping$0[focusParent.getFocusState().ordinal()]) {
                    case 1:
                        return m4771performCustomEnterMxy_nc0(focusParent, i);
                    case 2:
                        return CustomDestinationResult.Cancelled;
                    case 3:
                        return m4773performCustomRequestFocusMxy_nc0(focusParent, i);
                    case 4:
                        CustomDestinationResult it = m4773performCustomRequestFocusMxy_nc0(focusParent, i);
                        CustomDestinationResult customDestinationResult = !(it == CustomDestinationResult.None ? z : false) ? it : null;
                        return customDestinationResult == null ? m4771performCustomEnterMxy_nc0(focusParent, i) : customDestinationResult;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: performCustomClearFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m4770performCustomClearFocusMxy_nc0(FocusTargetNode $this$performCustomClearFocus_u2dMxy_nc0, int i) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$performCustomClearFocus_u2dMxy_nc0.getFocusState().ordinal()]) {
            case 1:
            case 4:
                return CustomDestinationResult.None;
            case 2:
                return CustomDestinationResult.Cancelled;
            case 3:
                CustomDestinationResult it = m4770performCustomClearFocusMxy_nc0(requireActiveChild($this$performCustomClearFocus_u2dMxy_nc0), i);
                if (it == CustomDestinationResult.None) {
                    it = null;
                }
                if (it == null) {
                    return m4772performCustomExitMxy_nc0($this$performCustomClearFocus_u2dMxy_nc0, i);
                }
                return it;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0077 A[Catch: all -> 0x009b, TRY_LEAVE, TryCatch #0 {all -> 0x009b, blocks: (B:5:0x0015, B:7:0x0045, B:9:0x0054, B:11:0x005c, B:27:0x0090, B:28:0x0093, B:24:0x0086, B:18:0x0077, B:16:0x0068, B:22:0x007e, B:25:0x0089), top: B:36:0x0015 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x0093 A[Catch: all -> 0x009b, TRY_LEAVE, TryCatch #0 {all -> 0x009b, blocks: (B:5:0x0015, B:7:0x0045, B:9:0x0054, B:11:0x005c, B:27:0x0090, B:28:0x0093, B:24:0x0086, B:18:0x0077, B:16:0x0068, B:22:0x007e, B:25:0x0089), top: B:36:0x0015 }] */
    /* JADX INFO: renamed from: performCustomEnter-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m4771performCustomEnterMxy_nc0(FocusTargetNode $this$performCustomEnter_u2dMxy_nc0, int i) {
        CustomDestinationResult customDestinationResult;
        if (!$this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter) {
            $this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter = true;
            try {
                FocusProperties focusProperties$iv$iv = $this$performCustomEnter_u2dMxy_nc0.fetchFocusProperties$ui();
                CancelIndicatingFocusBoundaryScope scope$iv$iv = new CancelIndicatingFocusBoundaryScope(i, null);
                FocusOwner focusOwner$iv$iv = DelegatableNodeKt.requireOwner($this$performCustomEnter_u2dMxy_nc0).getFocusOwner();
                FocusTargetNode activeNodeBefore$iv$iv = focusOwner$iv$iv.getActiveFocusTargetNode();
                CancelIndicatingFocusBoundaryScope it$iv = scope$iv$iv;
                focusProperties$iv$iv.getOnEnter().invoke(it$iv);
                FocusTargetNode activeNodeAfter$iv$iv = focusOwner$iv$iv.getActiveFocusTargetNode();
                if (scope$iv$iv.getIsCanceled()) {
                    FocusRequester it = FocusRequester.INSTANCE.getCancel();
                    if (it == FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = CustomDestinationResult.Cancelled;
                    } else if (it == FocusRequester.INSTANCE.getRedirect$ui()) {
                        customDestinationResult = CustomDestinationResult.Redirected;
                    } else if (FocusRequester.m4757requestFocus3ESFkO8$default(it, 0, 1, null)) {
                        customDestinationResult = CustomDestinationResult.Redirected;
                    } else {
                        customDestinationResult = CustomDestinationResult.RedirectCancelled;
                    }
                } else if (activeNodeBefore$iv$iv != activeNodeAfter$iv$iv && activeNodeAfter$iv$iv != null) {
                    FocusRequester it2 = FocusRequester.INSTANCE.getRedirect$ui();
                    if (it2 == FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = CustomDestinationResult.Cancelled;
                    } else if (it2 == FocusRequester.INSTANCE.getRedirect$ui() || FocusRequester.m4757requestFocus3ESFkO8$default(it2, 0, 1, null)) {
                        customDestinationResult = CustomDestinationResult.Redirected;
                    } else {
                        customDestinationResult = CustomDestinationResult.RedirectCancelled;
                    }
                }
                return customDestinationResult;
            } finally {
                $this$performCustomEnter_u2dMxy_nc0.isProcessingCustomEnter = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0077 A[Catch: all -> 0x009b, TRY_LEAVE, TryCatch #0 {all -> 0x009b, blocks: (B:5:0x0015, B:7:0x0045, B:9:0x0054, B:11:0x005c, B:27:0x0090, B:28:0x0093, B:24:0x0086, B:18:0x0077, B:16:0x0068, B:22:0x007e, B:25:0x0089), top: B:36:0x0015 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x0093 A[Catch: all -> 0x009b, TRY_LEAVE, TryCatch #0 {all -> 0x009b, blocks: (B:5:0x0015, B:7:0x0045, B:9:0x0054, B:11:0x005c, B:27:0x0090, B:28:0x0093, B:24:0x0086, B:18:0x0077, B:16:0x0068, B:22:0x007e, B:25:0x0089), top: B:36:0x0015 }] */
    /* JADX INFO: renamed from: performCustomExit-Mxy_nc0, reason: not valid java name */
    private static final CustomDestinationResult m4772performCustomExitMxy_nc0(FocusTargetNode $this$performCustomExit_u2dMxy_nc0, int i) {
        CustomDestinationResult customDestinationResult;
        if (!$this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit) {
            $this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit = true;
            try {
                FocusProperties focusProperties$iv$iv = $this$performCustomExit_u2dMxy_nc0.fetchFocusProperties$ui();
                CancelIndicatingFocusBoundaryScope scope$iv$iv = new CancelIndicatingFocusBoundaryScope(i, null);
                FocusOwner focusOwner$iv$iv = DelegatableNodeKt.requireOwner($this$performCustomExit_u2dMxy_nc0).getFocusOwner();
                FocusTargetNode activeNodeBefore$iv$iv = focusOwner$iv$iv.getActiveFocusTargetNode();
                CancelIndicatingFocusBoundaryScope it$iv = scope$iv$iv;
                focusProperties$iv$iv.getOnExit().invoke(it$iv);
                FocusTargetNode activeNodeAfter$iv$iv = focusOwner$iv$iv.getActiveFocusTargetNode();
                if (scope$iv$iv.getIsCanceled()) {
                    FocusRequester it = FocusRequester.INSTANCE.getCancel();
                    if (it == FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = CustomDestinationResult.Cancelled;
                    } else if (it == FocusRequester.INSTANCE.getRedirect$ui()) {
                        customDestinationResult = CustomDestinationResult.Redirected;
                    } else if (FocusRequester.m4757requestFocus3ESFkO8$default(it, 0, 1, null)) {
                        customDestinationResult = CustomDestinationResult.Redirected;
                    } else {
                        customDestinationResult = CustomDestinationResult.RedirectCancelled;
                    }
                } else if (activeNodeBefore$iv$iv != activeNodeAfter$iv$iv && activeNodeAfter$iv$iv != null) {
                    FocusRequester it2 = FocusRequester.INSTANCE.getRedirect$ui();
                    if (it2 == FocusRequester.INSTANCE.getCancel()) {
                        customDestinationResult = CustomDestinationResult.Cancelled;
                    } else if (it2 == FocusRequester.INSTANCE.getRedirect$ui() || FocusRequester.m4757requestFocus3ESFkO8$default(it2, 0, 1, null)) {
                        customDestinationResult = CustomDestinationResult.Redirected;
                    } else {
                        customDestinationResult = CustomDestinationResult.RedirectCancelled;
                    }
                }
                return customDestinationResult;
            } finally {
                $this$performCustomExit_u2dMxy_nc0.isProcessingCustomExit = false;
            }
        }
        return CustomDestinationResult.None;
    }
}
