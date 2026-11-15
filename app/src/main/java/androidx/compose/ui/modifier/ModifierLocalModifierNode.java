package androidx.compose.ui.modifier;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* JADX INFO: compiled from: ModifierLocalModifierNode.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u00012\u00020\u0002J)\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\f\u001a\u0002H\tH\u0016¢\u0006\u0002\u0010\rR\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R$\u0010\u000e\u001a\u0002H\t\"\u0004\b\u0000\u0010\t*\b\u0012\u0004\u0012\u0002H\t0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalReadScope;", "Landroidx/compose/ui/node/DelegatableNode;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "provide", "", ExifInterface.GPS_DIRECTION_TRUE, "key", "Landroidx/compose/ui/modifier/ModifierLocal;", "value", "(Landroidx/compose/ui/modifier/ModifierLocal;Ljava/lang/Object;)V", "current", "getCurrent", "(Landroidx/compose/ui/modifier/ModifierLocal;)Ljava/lang/Object;", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface ModifierLocalModifierNode extends ModifierLocalReadScope, DelegatableNode {
    default ModifierLocalMap getProvidedValues() {
        return EmptyMap.INSTANCE;
    }

    default <T> void provide(ModifierLocal<T> key, T value) {
        boolean value$iv = getProvidedValues() != EmptyMap.INSTANCE;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("In order to provide locals you must override providedValues: ModifierLocalMap");
        }
        boolean value$iv2 = getProvidedValues().contains$ui(key);
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("Any provided key must be initially provided in the overridden providedValues: ModifierLocalMap property. Key " + key + " was not found.");
        }
        getProvidedValues().mo6694set$ui(key, value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v2, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r17v7 */
    /* JADX WARN: Type inference failed for: r17v8 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r1v20 */
    @Override // androidx.compose.ui.modifier.ModifierLocalReadScope
    default <T> T getCurrent(ModifierLocal<T> modifierLocal) {
        ModifierLocal<T> modifierLocal2;
        ModifierLocalModifierNode modifierLocalModifierNode;
        int i;
        NodeChain nodes;
        ModifierLocal<T> modifierLocal3;
        ModifierLocal<T> modifierLocal4;
        if (!getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalArgumentException("ModifierLocal accessed from an unattached node");
        }
        ModifierLocal<T> modifierLocal5 = modifierLocal;
        ModifierLocalModifierNode modifierLocalModifierNode2 = this;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(32);
        if (!modifierLocalModifierNode2.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node parent = modifierLocalModifierNode2.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(modifierLocalModifierNode2);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM6860constructorimpl) != 0) {
                        int i2 = iM6860constructorimpl;
                        ?? r17 = 0;
                        Modifier.Node nodePop = parent;
                        while (nodePop != null) {
                            iM6860constructorimpl = iM6860constructorimpl;
                            if (nodePop instanceof ModifierLocalModifierNode) {
                                ModifierLocalModifierNode modifierLocalModifierNode3 = (ModifierLocalModifierNode) nodePop;
                                if (modifierLocalModifierNode3.getProvidedValues().contains$ui(modifierLocal5)) {
                                    return (T) modifierLocalModifierNode3.getProvidedValues().get$ui(modifierLocal5);
                                }
                                modifierLocal4 = modifierLocal5;
                            } else {
                                modifierLocal4 = modifierLocal5;
                                int i3 = 1;
                                if (((nodePop.getKindSet() & i2) != 0 ? 1 : 0) != 0 && (nodePop instanceof DelegatingNode)) {
                                    int i4 = 0;
                                    Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                    while (delegate != null) {
                                        Modifier.Node node = delegate;
                                        if (((node.getKindSet() & i2) != 0 ? i3 : 0) != 0) {
                                            r17 = r17;
                                            i4++;
                                            if (i4 == i3) {
                                                r17 = r17;
                                                nodePop = node;
                                            } else {
                                                ?? mutableVector = r17 == 0 ? new MutableVector(new Modifier.Node[16], 0) : r17;
                                                Object obj = nodePop;
                                                if (obj != null) {
                                                    if (mutableVector != 0) {
                                                        mutableVector.add(obj);
                                                    }
                                                    nodePop = null;
                                                }
                                                if (mutableVector != 0) {
                                                    mutableVector.add(node);
                                                }
                                                r17 = mutableVector;
                                                nodePop = nodePop;
                                                i4 = i4;
                                            }
                                        } else {
                                            r17 = r17;
                                            r17 = r17;
                                        }
                                        delegate = delegate.getChild();
                                        i3 = 1;
                                        r17 = r17;
                                    }
                                    r17 = r17;
                                    Modifier.Node node2 = nodePop;
                                    if (i4 == 1) {
                                        modifierLocal5 = modifierLocal4;
                                        nodePop = node2;
                                    }
                                }
                            }
                            nodePop = DelegatableNodeKt.pop(r17);
                            modifierLocal5 = modifierLocal4;
                        }
                        modifierLocal3 = modifierLocal5;
                    } else {
                        modifierLocal3 = modifierLocal5;
                    }
                    parent = parent.getParent();
                    modifierLocalModifierNode2 = modifierLocalModifierNode2;
                    iM6860constructorimpl = iM6860constructorimpl;
                    modifierLocal5 = modifierLocal3;
                }
                modifierLocal2 = modifierLocal5;
                modifierLocalModifierNode = modifierLocalModifierNode2;
                i = iM6860constructorimpl;
            } else {
                modifierLocal2 = modifierLocal5;
                modifierLocalModifierNode = modifierLocalModifierNode2;
                i = iM6860constructorimpl;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
            modifierLocalModifierNode2 = modifierLocalModifierNode;
            iM6860constructorimpl = i;
            modifierLocal5 = modifierLocal2;
        }
        return modifierLocal5.getDefaultFactory$ui().invoke();
    }
}
