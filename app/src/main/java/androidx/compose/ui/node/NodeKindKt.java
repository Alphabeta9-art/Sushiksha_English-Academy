package androidx.compose.ui.node;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.ui.Actual_jvmKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusEventModifierNodeKt;
import androidx.compose.ui.focus.FocusOrderModifier;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusPropertiesModifierNodeKt;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode;
import androidx.compose.ui.input.pointer.PointerInputModifier;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.BeyondBoundsLayoutProviderModifierNode;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.layout.OnPlacedModifier;
import androidx.compose.ui.layout.OnPlacedNode;
import androidx.compose.ui.layout.OnRemeasuredModifier;
import androidx.compose.ui.layout.OnSizeChangedNode;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.relocation.BringIntoViewModifierNode;
import androidx.compose.ui.semantics.SemanticsModifier;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: NodeKind.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0080\f¢\u0006\u0004\b\u0004\u0010\u0005\u001a \u0010\u0006\u001a\u00020\u0007*\u00020\u00012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0080\n¢\u0006\u0004\b\t\u0010\n\u001a\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u001a\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u001a\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u001a\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u001a\u0010\u0010 \u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u001a \u0010!\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u0001H\u0000\u001a \u0010$\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u0001H\u0002\u001a\f\u0010&\u001a\u00020\u0007*\u00020'H\u0002\u001a\u0010\u0010(\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\"\u001c\u0010\u000b\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0016\u001a\u00020\u0001X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0017\u0010\u0018\"\u0014\u0010\u0019\u001a\u00020\u0001X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u0018\"\u0014\u0010\u001b\u001a\u00020\u0001X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u0018¨\u0006)"}, d2 = {"or", "", "other", "Landroidx/compose/ui/node/NodeKind;", "or-64DMado", "(II)I", "contains", "", "value", "contains-64DMado", "(II)Z", "includeSelfInTraversal", "getIncludeSelfInTraversal-H91voCI", "(I)Z", "calculateNodeKindSetFrom", "element", "Landroidx/compose/ui/Modifier$Element;", "classToKindSetMap", "Landroidx/collection/MutableObjectIntMap;", "", "node", "Landroidx/compose/ui/Modifier$Node;", "Updated", "getUpdated$annotations", "()V", "Inserted", "getInserted$annotations", "Removed", "getRemoved$annotations", "autoInvalidateRemovedNode", "", "autoInvalidateInsertedNode", "autoInvalidateUpdatedNode", "autoInvalidateNodeIncludingDelegates", "remainingSet", TypedValues.CycleType.S_WAVE_PHASE, "autoInvalidateNodeSelf", "selfKindSet", "specifiesCanFocusProperty", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "calculateNodeKindSetFromIncludingDelegates", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NodeKindKt {
    private static final int Inserted = 1;
    private static final int Removed = 2;
    private static final int Updated = 0;
    private static final MutableObjectIntMap<Object> classToKindSetMap = ObjectIntMapKt.mutableObjectIntMapOf();

    private static /* synthetic */ void getInserted$annotations() {
    }

    private static /* synthetic */ void getRemoved$annotations() {
    }

    private static /* synthetic */ void getUpdated$annotations() {
    }

    /* JADX INFO: renamed from: or-64DMado, reason: not valid java name */
    public static final int m6870or64DMado(int $this$or_u2d64DMado, int i) {
        return $this$or_u2d64DMado | i;
    }

    /* JADX INFO: renamed from: contains-64DMado, reason: not valid java name */
    public static final boolean m6868contains64DMado(int $this$contains_u2d64DMado, int i) {
        return ($this$contains_u2d64DMado & i) != 0;
    }

    /* JADX INFO: renamed from: getIncludeSelfInTraversal-H91voCI, reason: not valid java name */
    public static final boolean m6869getIncludeSelfInTraversalH91voCI(int i) {
        return ((NodeKind.m6860constructorimpl(128) & i) != 0) | ((NodeKind.m6860constructorimpl(4194304) & i) != 0);
    }

    public static final int calculateNodeKindSetFrom(Modifier.Element element) {
        int mask = NodeKind.m6860constructorimpl(1);
        if (element instanceof LayoutModifier) {
            int $this$or_u2d64DMado$iv = mask | NodeKind.m6860constructorimpl(2);
            mask = $this$or_u2d64DMado$iv;
        }
        if (element instanceof DrawModifier) {
            int $this$or_u2d64DMado$iv2 = mask;
            mask = $this$or_u2d64DMado$iv2 | NodeKind.m6860constructorimpl(4);
        }
        if (element instanceof SemanticsModifier) {
            int $this$or_u2d64DMado$iv3 = mask;
            mask = $this$or_u2d64DMado$iv3 | NodeKind.m6860constructorimpl(8);
        }
        if (element instanceof PointerInputModifier) {
            int $this$or_u2d64DMado$iv4 = mask;
            mask = $this$or_u2d64DMado$iv4 | NodeKind.m6860constructorimpl(16);
        }
        if ((element instanceof ModifierLocalConsumer) || (element instanceof ModifierLocalProvider)) {
            int $this$or_u2d64DMado$iv5 = mask;
            mask = $this$or_u2d64DMado$iv5 | NodeKind.m6860constructorimpl(32);
        }
        if (element instanceof FocusEventModifier) {
            int $this$or_u2d64DMado$iv6 = mask;
            mask = $this$or_u2d64DMado$iv6 | NodeKind.m6860constructorimpl(4096);
        }
        if (element instanceof FocusOrderModifier) {
            int $this$or_u2d64DMado$iv7 = mask;
            mask = $this$or_u2d64DMado$iv7 | NodeKind.m6860constructorimpl(2048);
        }
        if (element instanceof OnGloballyPositionedModifier) {
            int $this$or_u2d64DMado$iv8 = mask;
            mask = $this$or_u2d64DMado$iv8 | NodeKind.m6860constructorimpl(256);
        }
        if (element instanceof ParentDataModifier) {
            int $this$or_u2d64DMado$iv9 = mask;
            mask = $this$or_u2d64DMado$iv9 | NodeKind.m6860constructorimpl(64);
        }
        if (element instanceof OnPlacedModifier) {
            int $this$or_u2d64DMado$iv10 = mask;
            mask = $this$or_u2d64DMado$iv10 | NodeKind.m6860constructorimpl(4194304);
        }
        if (element instanceof OnRemeasuredModifier) {
            int $this$or_u2d64DMado$iv11 = mask;
            mask = $this$or_u2d64DMado$iv11 | NodeKind.m6860constructorimpl(128);
        }
        if (element instanceof BringIntoViewModifierNode) {
            int $this$or_u2d64DMado$iv12 = mask;
            return $this$or_u2d64DMado$iv12 | NodeKind.m6860constructorimpl(524288);
        }
        return mask;
    }

    public static final int calculateNodeKindSetFrom(Modifier.Node node) {
        if (node.getKindSet() != 0) {
            return node.getKindSet();
        }
        MutableObjectIntMap<Object> mutableObjectIntMap = classToKindSetMap;
        Object key$iv = Actual_jvmKt.classKeyForObject(node);
        int index$iv = mutableObjectIntMap.findKeyIndex(key$iv);
        if (index$iv >= 0) {
            int value$iv = mutableObjectIntMap.values[index$iv];
            return value$iv;
        }
        int mask = NodeKind.m6860constructorimpl(1);
        if (node instanceof LayoutModifierNode) {
            int $this$or_u2d64DMado$iv = mask | NodeKind.m6860constructorimpl(2);
            mask = $this$or_u2d64DMado$iv;
        }
        if (node instanceof DrawModifierNode) {
            int $this$or_u2d64DMado$iv2 = mask;
            mask = $this$or_u2d64DMado$iv2 | NodeKind.m6860constructorimpl(4);
        }
        if (node instanceof SemanticsModifierNode) {
            int $this$or_u2d64DMado$iv3 = mask;
            mask = $this$or_u2d64DMado$iv3 | NodeKind.m6860constructorimpl(8);
        }
        if (node instanceof PointerInputModifierNode) {
            int $this$or_u2d64DMado$iv4 = mask;
            mask = $this$or_u2d64DMado$iv4 | NodeKind.m6860constructorimpl(16);
        }
        if (node instanceof ModifierLocalModifierNode) {
            int $this$or_u2d64DMado$iv5 = mask;
            mask = $this$or_u2d64DMado$iv5 | NodeKind.m6860constructorimpl(32);
        }
        if (node instanceof ParentDataModifierNode) {
            int $this$or_u2d64DMado$iv6 = mask;
            mask = $this$or_u2d64DMado$iv6 | NodeKind.m6860constructorimpl(64);
        }
        if (node instanceof OnPlacedNode) {
            int $this$or_u2d64DMado$iv7 = mask;
            mask = $this$or_u2d64DMado$iv7 | NodeKind.m6860constructorimpl(4194304);
        } else if (node instanceof OnSizeChangedNode) {
            int $this$or_u2d64DMado$iv8 = mask;
            mask = $this$or_u2d64DMado$iv8 | NodeKind.m6860constructorimpl(128);
        } else if (node instanceof LayoutAwareModifierNode) {
            int $this$or_u2d64DMado$iv9 = mask;
            mask = $this$or_u2d64DMado$iv9 | NodeKind.m6860constructorimpl(128) | NodeKind.m6860constructorimpl(4194304);
        }
        if (node instanceof GlobalPositionAwareModifierNode) {
            int $this$or_u2d64DMado$iv10 = mask;
            mask = $this$or_u2d64DMado$iv10 | NodeKind.m6860constructorimpl(256);
        }
        if (node instanceof ApproachLayoutModifierNode) {
            int $this$or_u2d64DMado$iv11 = mask;
            mask = $this$or_u2d64DMado$iv11 | NodeKind.m6860constructorimpl(512);
        }
        if (node instanceof FocusTargetNode) {
            int $this$or_u2d64DMado$iv12 = mask;
            mask = $this$or_u2d64DMado$iv12 | NodeKind.m6860constructorimpl(1024);
        }
        if (node instanceof FocusPropertiesModifierNode) {
            int $this$or_u2d64DMado$iv13 = mask;
            mask = $this$or_u2d64DMado$iv13 | NodeKind.m6860constructorimpl(2048);
        }
        if (node instanceof FocusEventModifierNode) {
            int $this$or_u2d64DMado$iv14 = mask;
            mask = $this$or_u2d64DMado$iv14 | NodeKind.m6860constructorimpl(4096);
        }
        if (node instanceof KeyInputModifierNode) {
            int $this$or_u2d64DMado$iv15 = mask;
            mask = $this$or_u2d64DMado$iv15 | NodeKind.m6860constructorimpl(8192);
        }
        if (node instanceof RotaryInputModifierNode) {
            int $this$or_u2d64DMado$iv16 = mask;
            mask = $this$or_u2d64DMado$iv16 | NodeKind.m6860constructorimpl(16384);
        }
        if (node instanceof CompositionLocalConsumerModifierNode) {
            int $this$or_u2d64DMado$iv17 = mask;
            mask = $this$or_u2d64DMado$iv17 | NodeKind.m6860constructorimpl(32768);
        }
        if (node instanceof SoftKeyboardInterceptionModifierNode) {
            int $this$or_u2d64DMado$iv18 = mask;
            mask = $this$or_u2d64DMado$iv18 | NodeKind.m6860constructorimpl(131072);
        }
        if (node instanceof TraversableNode) {
            int $this$or_u2d64DMado$iv19 = mask;
            mask = $this$or_u2d64DMado$iv19 | NodeKind.m6860constructorimpl(262144);
        }
        if (node instanceof BringIntoViewModifierNode) {
            int $this$or_u2d64DMado$iv20 = mask;
            mask = $this$or_u2d64DMado$iv20 | NodeKind.m6860constructorimpl(524288);
        }
        if (node instanceof UnplacedAwareModifierNode) {
            int $this$or_u2d64DMado$iv21 = mask;
            mask = $this$or_u2d64DMado$iv21 | NodeKind.m6860constructorimpl(1048576);
        }
        if (node instanceof IndirectPointerInputModifierNode) {
            int $this$or_u2d64DMado$iv22 = mask;
            mask = $this$or_u2d64DMado$iv22 | NodeKind.m6860constructorimpl(2097152);
        }
        if (node instanceof BeyondBoundsLayoutProviderModifierNode) {
            int $this$or_u2d64DMado$iv23 = mask;
            mask = $this$or_u2d64DMado$iv23 | NodeKind.m6860constructorimpl(8388608);
        }
        int i = mask;
        mutableObjectIntMap.set(key$iv, i);
        return i;
    }

    public static final void autoInvalidateRemovedNode(Modifier.Node node) {
        boolean value$iv = node.getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("autoInvalidateRemovedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 2);
    }

    public static final void autoInvalidateInsertedNode(Modifier.Node node) {
        boolean value$iv = node.getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("autoInvalidateInsertedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 1);
    }

    public static final void autoInvalidateUpdatedNode(Modifier.Node node) {
        boolean value$iv = node.getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("autoInvalidateUpdatedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 0);
    }

    public static final void autoInvalidateNodeIncludingDelegates(Modifier.Node node, int remainingSet, int phase) {
        if (node instanceof DelegatingNode) {
            autoInvalidateNodeSelf(node, ((DelegatingNode) node).getSelfKindSet() & remainingSet, phase);
            int newRemaining = (~((DelegatingNode) node).getSelfKindSet()) & remainingSet;
            DelegatingNode this_$iv = (DelegatingNode) node;
            for (Modifier.Node node$iv = this_$iv.getDelegate(); node$iv != null; node$iv = node$iv.getChild()) {
                Modifier.Node it = node$iv;
                autoInvalidateNodeIncludingDelegates(it, newRemaining, phase);
            }
            return;
        }
        autoInvalidateNodeSelf(node, node.getKindSet() & remainingSet, phase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void autoInvalidateNodeSelf(Modifier.Node node, int selfKindSet, int phase) {
        if (phase != 0 || node.getShouldAutoInvalidate()) {
            if (((selfKindSet & NodeKind.m6860constructorimpl(2)) != 0 ? 1 : 0) != 0 && (node instanceof LayoutModifierNode)) {
                LayoutModifierNodeKt.invalidateMeasurement((LayoutModifierNode) node);
                if (phase == 2) {
                    NodeCoordinator coordinator = DelegatableNodeKt.m6712requireCoordinator64DMado(node, NodeKind.m6860constructorimpl(2));
                    coordinator.onRelease();
                }
            }
            if (((selfKindSet & NodeKind.m6860constructorimpl(128)) != 0 ? 1 : 0) != 0 && phase != 2) {
                DelegatableNodeKt.requireLayoutNode(node).invalidateMeasurements$ui();
            }
            if (((selfKindSet & NodeKind.m6860constructorimpl(4194304)) != 0 ? 1 : 0) != 0 && phase != 2) {
                LayoutNode.requestRelayout$ui$default(DelegatableNodeKt.requireLayoutNode(node), false, 1, null);
            }
            if (((selfKindSet & NodeKind.m6860constructorimpl(256)) != 0 ? 1 : 0) != 0 && (node instanceof GlobalPositionAwareModifierNode)) {
                switch (phase) {
                    case 1:
                        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(node);
                        layoutNodeRequireLayoutNode.setGloballyPositionedObservers(layoutNodeRequireLayoutNode.getGloballyPositionedObservers() + 1);
                        break;
                    case 2:
                        LayoutNode layoutNodeRequireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(node);
                        layoutNodeRequireLayoutNode2.setGloballyPositionedObservers(layoutNodeRequireLayoutNode2.getGloballyPositionedObservers() - 1);
                        break;
                }
                if (phase != 2) {
                    DelegatableNodeKt.requireLayoutNode(node).invalidateOnPositioned$ui();
                }
            }
            if (((selfKindSet & NodeKind.m6860constructorimpl(4)) != 0 ? 1 : 0) != 0 && (node instanceof DrawModifierNode)) {
                DrawModifierNodeKt.invalidateDraw((DrawModifierNode) node);
            }
            if (((selfKindSet & NodeKind.m6860constructorimpl(8)) != 0 ? 1 : 0) != 0 && (node instanceof SemanticsModifierNode)) {
                DelegatableNodeKt.requireLayoutNode(node).setSemanticsInvalidated$ui(true);
            }
            if (((selfKindSet & NodeKind.m6860constructorimpl(64)) != 0 ? 1 : 0) != 0 && (node instanceof ParentDataModifierNode)) {
                ParentDataModifierNodeKt.invalidateParentData((ParentDataModifierNode) node);
            }
            if (((selfKindSet & NodeKind.m6860constructorimpl(2048)) != 0 ? 1 : 0) != 0 && (node instanceof FocusPropertiesModifierNode) && specifiesCanFocusProperty((FocusPropertiesModifierNode) node)) {
                FocusPropertiesModifierNodeKt.invalidateFocusProperties((FocusPropertiesModifierNode) node);
            }
            if (((selfKindSet & NodeKind.m6860constructorimpl(4096)) != 0) && (node instanceof FocusEventModifierNode)) {
                FocusEventModifierNodeKt.invalidateFocusEvent((FocusEventModifierNode) node);
            }
        }
    }

    private static final boolean specifiesCanFocusProperty(FocusPropertiesModifierNode $this$specifiesCanFocusProperty) {
        CanFocusChecker.INSTANCE.reset();
        $this$specifiesCanFocusProperty.applyFocusProperties(CanFocusChecker.INSTANCE);
        return CanFocusChecker.INSTANCE.isCanFocusSet();
    }

    public static final int calculateNodeKindSetFromIncludingDelegates(Modifier.Node node) {
        if (node instanceof DelegatingNode) {
            int mask = ((DelegatingNode) node).getSelfKindSet();
            DelegatingNode this_$iv = (DelegatingNode) node;
            for (Modifier.Node node$iv = this_$iv.getDelegate(); node$iv != null; node$iv = node$iv.getChild()) {
                Modifier.Node it = node$iv;
                mask |= calculateNodeKindSetFromIncludingDelegates(it);
            }
            return mask;
        }
        return calculateNodeKindSetFrom(node);
    }
}
