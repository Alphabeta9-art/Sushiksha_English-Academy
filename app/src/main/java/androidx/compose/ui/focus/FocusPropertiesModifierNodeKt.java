package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;

/* JADX INFO: compiled from: FocusPropertiesModifierNode.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"invalidateFocusProperties", "", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FocusPropertiesModifierNodeKt {
    public static final void invalidateFocusProperties(FocusPropertiesModifierNode $this$invalidateFocusProperties) {
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        int i;
        boolean zOrder$iv;
        boolean z;
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
        FocusPropertiesModifierNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$invalidateFocusProperties;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
        boolean zOrder$iv2 = false;
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        boolean z2 = false;
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
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
                            if (nodePop instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) nodePop;
                                FocusTargetNodeKt.invalidateFocusTarget(it);
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                i = iM6860constructorimpl;
                                zOrder$iv = zOrder$iv2;
                                z = true;
                            } else {
                                Modifier.Node this_$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv.getKindSet() & i2) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                    i = iM6860constructorimpl;
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
                                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                            if (count$iv$iv == 1) {
                                                nodePop = next$iv$iv;
                                                iM6860constructorimpl = iM6860constructorimpl;
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
                                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                            iM6860constructorimpl = iM6860constructorimpl;
                                            zOrder$iv2 = zOrder$iv2;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                        iM6860constructorimpl = iM6860constructorimpl;
                                        zOrder$iv2 = zOrder$iv2;
                                    }
                                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                                    i = iM6860constructorimpl;
                                    zOrder$iv = zOrder$iv2;
                                    z = true;
                                    if (count$iv$iv == 1) {
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        iM6860constructorimpl = i;
                                        zOrder$iv2 = zOrder$iv;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(mutableVector);
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            iM6860constructorimpl = i;
                            zOrder$iv2 = zOrder$iv;
                        }
                        z2 = false;
                        break;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv3 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv3;
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
    }
}
