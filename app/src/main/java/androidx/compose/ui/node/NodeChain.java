package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.ModifierInfo;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NodeChain.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0089\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\t\b\u0001\u0018\u00002\u00020\u0001:\u0002pqB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010,\u001a\u00020-2\b\u0010*\u001a\u0004\u0018\u00010+H\u0000¢\u0006\u0002\b.J\b\u0010/\u001a\u00020\u0015H\u0002J\u0010\u00100\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u0015H\u0002J\u0015\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020'H\u0000¢\u0006\u0002\b4J\r\u00105\u001a\u00020-H\u0000¢\u0006\u0002\b6J\u0006\u00107\u001a\u00020-J\b\u00108\u001a\u00020-H\u0002J\u0006\u00109\u001a\u00020-J\u0006\u0010:\u001a\u00020-J\f\u0010;\u001a\b\u0012\u0004\u0012\u00020=0<J\r\u0010>\u001a\u00020-H\u0000¢\u0006\u0002\b?J\r\u0010@\u001a\u00020-H\u0000¢\u0006\u0002\bAJ@\u0010B\u001a\u00060)R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010C\u001a\u00020\u001f2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010F\u001a\u00020\u001bH\u0002J\u0018\u0010G\u001a\u00020-2\u0006\u0010H\u001a\u00020\u00152\u0006\u0010I\u001a\u00020\u0010H\u0002J<\u0010J\u001a\u00020-2\u0006\u0010C\u001a\u00020\u001f2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010F\u001a\u00020\u001bH\u0002J\u0010\u0010K\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u0015H\u0002J\u0010\u0010M\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u0015H\u0002J\u0018\u0010N\u001a\u00020\u00152\u0006\u0010O\u001a\u00020$2\u0006\u0010P\u001a\u00020\u0015H\u0002J\u0018\u0010Q\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u00152\u0006\u0010P\u001a\u00020\u0015H\u0002J \u0010R\u001a\u00020-2\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$2\u0006\u0010L\u001a\u00020\u0015H\u0002J<\u0010U\u001a\u0004\u0018\u0001HV\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0X2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u0002HV\u0012\u0004\u0012\u00020\u001b0ZH\u0080\b¢\u0006\u0004\b[\u0010\\J:\u0010]\u001a\u00020-\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0X2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u0002HV\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0004\b^\u0010_J*\u0010]\u001a\u00020-2\u0006\u0010`\u001a\u00020\u001f2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\baJ\"\u0010]\u001a\u00020-2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\baJ\"\u0010b\u001a\u00020-2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\bcJ:\u0010d\u001a\u00020-\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0X2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u0002HV\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0004\be\u0010_J*\u0010d\u001a\u00020-2\u0006\u0010`\u001a\u00020\u001f2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\bfJ\"\u0010d\u001a\u00020-2\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020-0ZH\u0080\b¢\u0006\u0002\bfJ(\u0010\u0014\u001a\u0004\u0018\u0001HV\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0XH\u0080\b¢\u0006\u0004\bg\u0010hJ(\u0010\u0018\u001a\u0004\u0018\u0001HV\"\u0006\b\u0000\u0010V\u0018\u00012\f\u0010W\u001a\b\u0012\u0004\u0012\u0002HV0XH\u0080\b¢\u0006\u0004\bi\u0010hJ\u001b\u0010j\u001a\u00020\u001b2\n\u0010W\u001a\u0006\u0012\u0002\b\u00030XH\u0000¢\u0006\u0004\bk\u0010lJ\u0015\u0010j\u001a\u00020\u001b2\u0006\u0010`\u001a\u00020\u001fH\u0000¢\u0006\u0002\bmJ\b\u0010n\u001a\u00020oH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0015@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0014\u0010\u001a\u001a\u00020\u001b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0016\u0010\"\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020'0#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0018\u00010)R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006r"}, d2 = {"Landroidx/compose/ui/node/NodeChain;", "", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "sentinelHead", "androidx/compose/ui/node/NodeChain$sentinelHead$1", "Landroidx/compose/ui/node/NodeChain$sentinelHead$1;", "innerCoordinator", "Landroidx/compose/ui/node/InnerNodeCoordinator;", "getInnerCoordinator$ui", "()Landroidx/compose/ui/node/InnerNodeCoordinator;", "value", "Landroidx/compose/ui/node/NodeCoordinator;", "outerCoordinator", "getOuterCoordinator$ui", "()Landroidx/compose/ui/node/NodeCoordinator;", "tail", "Landroidx/compose/ui/Modifier$Node;", "getTail$ui", "()Landroidx/compose/ui/Modifier$Node;", TtmlNode.TAG_HEAD, "getHead$ui", "isUpdating", "", "isUpdating$ui", "()Z", "aggregateChildKindSet", "", "getAggregateChildKindSet", "()I", "current", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/Modifier$Element;", "buffer", "stack", "Landroidx/compose/ui/Modifier;", "cachedDiffer", "Landroidx/compose/ui/node/NodeChain$Differ;", "logger", "Landroidx/compose/ui/node/NodeChain$Logger;", "useLogger", "", "useLogger$ui", "padChain", "trimChain", "paddedHead", "updateFrom", "m", "updateFrom$ui", "resetState", "resetState$ui", "syncCoordinators", "syncAggregateChildKindSet", "markAsAttached", "runAttachLifecycle", "getModifierInfo", "", "Landroidx/compose/ui/layout/ModifierInfo;", "markAsDetached", "markAsDetached$ui", "runDetachLifecycle", "runDetachLifecycle$ui", "getDiffer", TypedValues.CycleType.S_WAVE_OFFSET, TtmlNode.ANNOTATION_POSITION_BEFORE, TtmlNode.ANNOTATION_POSITION_AFTER, "shouldAttachOnInsert", "propagateCoordinator", TtmlNode.START, "coordinator", "structuralUpdate", "detachAndRemoveNode", "node", "removeNode", "createAndInsertNodeAsChild", "element", "parent", "insertChild", "updateNode", "prev", "next", "firstFromHead", ExifInterface.GPS_DIRECTION_TRUE, "type", "Landroidx/compose/ui/node/NodeKind;", "block", "Lkotlin/Function1;", "firstFromHead-aLcG6gQ$ui", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "headToTail", "headToTail-aLcG6gQ$ui", "(ILkotlin/jvm/functions/Function1;)V", "mask", "headToTail$ui", "headToTailExclusive", "headToTailExclusive$ui", "tailToHead", "tailToHead-aLcG6gQ$ui", "tailToHead$ui", "tail-H91voCI$ui", "(I)Ljava/lang/Object;", "head-H91voCI$ui", "has", "has-H91voCI$ui", "(I)Z", "has$ui", "toString", "", "Differ", "Logger", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NodeChain {
    public static final int $stable = 8;
    private MutableVector<Modifier.Element> buffer;
    private Differ cachedDiffer;
    private MutableVector<Modifier.Element> current;
    private Modifier.Node head;
    private final InnerNodeCoordinator innerCoordinator;
    private final LayoutNode layoutNode;
    private Logger logger;
    private NodeCoordinator outerCoordinator;
    private final NodeChain$sentinelHead$1 sentinelHead;
    private final MutableVector<Modifier> stack;
    private final Modifier.Node tail;

    /* JADX INFO: compiled from: NodeChain.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b`\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J0\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J0\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J0\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH&J \u0010\u0014\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/NodeChain$Logger;", "", "linearDiffAborted", "", "index", "", "prev", "Landroidx/compose/ui/Modifier$Element;", "next", "node", "Landroidx/compose/ui/Modifier$Node;", "nodeUpdated", "oldIndex", "newIndex", "nodeReused", "nodeInserted", "atIndex", "element", "child", "inserted", "nodeRemoved", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Logger {
        void linearDiffAborted(int index, Modifier.Element prev, Modifier.Element next, Modifier.Node node);

        void nodeInserted(int atIndex, int newIndex, Modifier.Element element, Modifier.Node child, Modifier.Node inserted);

        void nodeRemoved(int oldIndex, Modifier.Element element, Modifier.Node node);

        void nodeReused(int oldIndex, int newIndex, Modifier.Element prev, Modifier.Element next, Modifier.Node node);

        void nodeUpdated(int oldIndex, int newIndex, Modifier.Element prev, Modifier.Element next, Modifier.Node node);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.node.NodeChain$sentinelHead$1] */
    public NodeChain(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
        ?? r0 = new Modifier.Node() { // from class: androidx.compose.ui.node.NodeChain$sentinelHead$1
            public String toString() {
                return "<Head>";
            }
        };
        r0.setAggregateChildKindSet$ui(-1);
        this.sentinelHead = r0;
        this.innerCoordinator = new InnerNodeCoordinator(this.layoutNode);
        this.outerCoordinator = this.innerCoordinator;
        this.tail = this.innerCoordinator.getTail();
        this.head = this.tail;
        this.stack = new MutableVector<>(new Modifier[16], 0);
    }

    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    /* JADX INFO: renamed from: getInnerCoordinator$ui, reason: from getter */
    public final InnerNodeCoordinator getInnerCoordinator() {
        return this.innerCoordinator;
    }

    /* JADX INFO: renamed from: getOuterCoordinator$ui, reason: from getter */
    public final NodeCoordinator getOuterCoordinator() {
        return this.outerCoordinator;
    }

    /* JADX INFO: renamed from: getTail$ui, reason: from getter */
    public final Modifier.Node getTail() {
        return this.tail;
    }

    /* JADX INFO: renamed from: getHead$ui, reason: from getter */
    public final Modifier.Node getHead() {
        return this.head;
    }

    public final boolean isUpdating$ui() {
        return getChild() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getAggregateChildKindSet() {
        return this.head.getAggregateChildKindSet();
    }

    public final void useLogger$ui(Logger logger) {
        this.logger = logger;
    }

    private final Modifier.Node padChain() {
        boolean value$iv = this.head != this.sentinelHead;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("padChain called on already padded chain");
        }
        Modifier.Node currentHead = this.head;
        currentHead.setParent$ui(this.sentinelHead);
        setChild$ui(currentHead);
        return this.sentinelHead;
    }

    private final Modifier.Node trimChain(Modifier.Node paddedHead) {
        boolean value$iv = paddedHead == this.sentinelHead;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("trimChain called on already trimmed chain");
        }
        Modifier.Node result = getChild();
        if (result == null) {
            result = this.tail;
        }
        result.setParent$ui(null);
        setChild$ui(null);
        setAggregateChildKindSet$ui(-1);
        updateCoordinator$ui(null);
        boolean value$iv2 = result != this.sentinelHead;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalStateException("trimChain did not update the head");
        }
        return result;
    }

    public final void updateFrom$ui(Modifier m) {
        MutableVector<Modifier.Element> mutableVector;
        MutableVector<Modifier.Element> mutableVector2;
        Modifier.Node node;
        Modifier.Node node2;
        Modifier.Node node3;
        boolean coordinatorSyncNeeded = false;
        Modifier.Node paddedHead = padChain();
        MutableVector<Modifier.Element> mutableVector3 = this.current;
        int beforeSize = mutableVector3 != null ? mutableVector3.getSize() : 0;
        MutableVector<Modifier.Element> mutableVector4 = this.buffer;
        if (mutableVector4 == null) {
            mutableVector4 = new MutableVector<>(new Modifier.Element[16], 0);
        }
        MutableVector<Modifier.Element> mutableVectorFillVector = NodeChainKt.fillVector(m, mutableVector4, this.stack);
        MutableVector<Modifier.Element> mutableVector5 = null;
        if (mutableVectorFillVector.getSize() == beforeSize) {
            Modifier.Node node4 = paddedHead.getChild();
            int i = 0;
            while (true) {
                if (node4 == null || i >= beforeSize) {
                    node2 = node4;
                } else if (mutableVector3 != null) {
                    int index$iv = i;
                    Modifier.Element prev = mutableVector3.content[index$iv];
                    int index$iv2 = i;
                    Modifier.Element next = mutableVectorFillVector.content[index$iv2];
                    switch (NodeChainKt.actionForModifiers(prev, next)) {
                        case 0:
                            Modifier.Node node5 = node4;
                            Logger logger = this.logger;
                            if (logger != null) {
                                logger.linearDiffAborted(i, prev, next, node5);
                            }
                            node2 = node5.getParent();
                            break;
                        case 1:
                            Modifier.Node node6 = node4;
                            updateNode(prev, next, node6);
                            Logger logger2 = this.logger;
                            if (logger2 != null) {
                                logger2.nodeUpdated(i, i, prev, next, node6);
                                node3 = node6;
                                continue;
                            } else {
                                node3 = node6;
                            }
                            node4 = node3.getChild();
                            i++;
                            break;
                        case 2:
                            Logger logger3 = this.logger;
                            if (logger3 != null) {
                                logger3.nodeReused(i, i, prev, next, node4);
                                node3 = node4;
                                continue;
                            } else {
                                node3 = node4;
                            }
                            node4 = node3.getChild();
                            i++;
                            break;
                        default:
                            node3 = node4;
                            continue;
                            node4 = node3.getChild();
                            i++;
                            break;
                    }
                } else {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("expected prior modifier list to be non-empty");
                    throw new KotlinNothingValueException();
                }
            }
            if (i >= beforeSize) {
                mutableVector2 = mutableVectorFillVector;
                node = paddedHead;
            } else if (mutableVector3 != null) {
                Object value$iv = node2;
                if (value$iv != null) {
                    mutableVector2 = mutableVectorFillVector;
                    structuralUpdate(i, mutableVector3, mutableVector2, node2, !this.layoutNode.getApplyingModifierOnAttach$ui());
                    node = paddedHead;
                    coordinatorSyncNeeded = true;
                } else {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("structuralUpdate requires a non-null tail");
                    throw new KotlinNothingValueException();
                }
            } else {
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("expected prior modifier list to be non-empty");
                throw new KotlinNothingValueException();
            }
        } else if (this.layoutNode.getApplyingModifierOnAttach$ui() && beforeSize == 0) {
            coordinatorSyncNeeded = true;
            Modifier.Node node7 = paddedHead;
            int i2 = 0;
            while (i2 < mutableVectorFillVector.getSize()) {
                int index$iv3 = i2;
                Modifier.Element next2 = mutableVectorFillVector.content[index$iv3];
                Modifier.Node parent = node7;
                Modifier.Node node8 = createAndInsertNodeAsChild(next2, parent);
                Logger logger4 = this.logger;
                if (logger4 != null) {
                    logger4.nodeInserted(0, i2, next2, parent, node8);
                }
                i2++;
                node7 = node8;
            }
            syncAggregateChildKindSet();
            mutableVector2 = mutableVectorFillVector;
            node = paddedHead;
        } else if (mutableVectorFillVector.getSize() != 0) {
            if (mutableVector3 != null) {
                mutableVector = mutableVector3;
            } else {
                mutableVector = new MutableVector<>(new Modifier.Element[16], 0);
            }
            mutableVector3 = mutableVector;
            mutableVector2 = mutableVectorFillVector;
            node = paddedHead;
            structuralUpdate(0, mutableVector3, mutableVector2, node, !this.layoutNode.getApplyingModifierOnAttach$ui());
            coordinatorSyncNeeded = true;
        } else if (mutableVector3 != null) {
            Modifier.Node node9 = paddedHead.getChild();
            for (int i3 = 0; node9 != null && i3 < mutableVector3.getSize(); i3++) {
                Logger logger5 = this.logger;
                if (logger5 != null) {
                    int index$iv4 = i3;
                    logger5.nodeRemoved(i3, mutableVector3.content[index$iv4], node9);
                }
                node9 = detachAndRemoveNode(node9).getChild();
            }
            InnerNodeCoordinator innerNodeCoordinator = this.innerCoordinator;
            LayoutNode parent$ui = this.layoutNode.getParent$ui();
            innerNodeCoordinator.setWrappedBy$ui(parent$ui != null ? parent$ui.getInnerCoordinator$ui() : null);
            this.outerCoordinator = this.innerCoordinator;
            mutableVector2 = mutableVectorFillVector;
            node = paddedHead;
        } else {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("expected prior modifier list to be non-empty");
            throw new KotlinNothingValueException();
        }
        this.current = mutableVector2;
        if (mutableVector3 != null) {
            mutableVector3.clear();
            mutableVector5 = mutableVector3;
        }
        this.buffer = mutableVector5;
        this.head = trimChain(node);
        if (coordinatorSyncNeeded) {
            syncCoordinators();
        }
    }

    public final void resetState$ui() {
        for (Modifier.Node node$iv = getTail(); node$iv != null; node$iv = node$iv.getParent()) {
            Modifier.Node it = node$iv;
            if (it.getIsAttached()) {
                it.reset$ui();
            }
        }
        runDetachLifecycle$ui();
        markAsDetached$ui();
    }

    public final void syncCoordinators() {
        LayoutModifierNodeCoordinator c;
        NodeCoordinator coordinator = this.innerCoordinator;
        for (Modifier.Node node = this.tail.getParent(); node != null; node = node.getParent()) {
            LayoutModifierNode layoutmod = DelegatableNodeKt.asLayoutModifierNode(node);
            if (layoutmod != null) {
                if (node.getCoordinator() != null) {
                    NodeCoordinator coordinator2 = node.getCoordinator();
                    Intrinsics.checkNotNull(coordinator2, "null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator");
                    c = (LayoutModifierNodeCoordinator) coordinator2;
                    LayoutModifierNode prevNode = c.getLayoutModifierNode();
                    c.setLayoutModifierNode$ui(layoutmod);
                    if (prevNode != node) {
                        c.onLayoutModifierNodeChanged();
                    }
                } else {
                    c = new LayoutModifierNodeCoordinator(this.layoutNode, layoutmod);
                    node.updateCoordinator$ui(c);
                }
                coordinator.setWrappedBy$ui(c);
                c.setWrapped$ui(coordinator);
                NodeCoordinator coordinator3 = c;
                coordinator = coordinator3;
            } else {
                node.updateCoordinator$ui(coordinator);
            }
        }
        LayoutNode parent$ui = this.layoutNode.getParent$ui();
        coordinator.setWrappedBy$ui(parent$ui != null ? parent$ui.getInnerCoordinator$ui() : null);
        this.outerCoordinator = coordinator;
    }

    private final void syncAggregateChildKindSet() {
        int aggregateChildKindSet = 0;
        for (Modifier.Node node = this.tail.getParent(); node != null && node != this.sentinelHead; node = node.getParent()) {
            aggregateChildKindSet |= node.getKindSet();
            node.setAggregateChildKindSet$ui(aggregateChildKindSet);
        }
    }

    public final void markAsAttached() {
        for (Modifier.Node node$iv = getHead(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.markAsAttached$ui();
        }
    }

    public final void runAttachLifecycle() {
        for (Modifier.Node node$iv = getHead(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            it.runAttachLifecycle$ui();
            if (it.getInsertedNodeAwaitingAttachForInvalidation()) {
                NodeKindKt.autoInvalidateInsertedNode(it);
            }
            if (it.getUpdatedNodeAwaitingAttachForInvalidation()) {
                NodeKindKt.autoInvalidateUpdatedNode(it);
            }
            it.setInsertedNodeAwaitingAttachForInvalidation$ui(false);
            it.setUpdatedNodeAwaitingAttachForInvalidation$ui(false);
        }
    }

    public final List<ModifierInfo> getModifierInfo() {
        NodeChain nodeChain = this;
        MutableVector<Modifier.Element> mutableVector = nodeChain.current;
        if (mutableVector == null) {
            return CollectionsKt.emptyList();
        }
        int capacity$iv = mutableVector.getSize();
        MutableVector infoList = new MutableVector(new ModifierInfo[capacity$iv], 0);
        int i = 0;
        Modifier.Node node$iv = getHead();
        while (node$iv != null && node$iv != getTail()) {
            Modifier.Node node = node$iv;
            NodeCoordinator coordinator = node.getCoordinator();
            if (coordinator == null) {
                throw new IllegalArgumentException("getModifierInfo called on node with no coordinator".toString());
            }
            OwnedLayer currentNodeLayer = coordinator.getLayer();
            OwnedLayer innerNodeLayer = nodeChain.innerCoordinator.getLayer();
            Modifier.Node localChild = node.getChild();
            if (!(localChild == nodeChain.tail && node.getCoordinator() != localChild.getCoordinator())) {
                innerNodeLayer = null;
            }
            OwnedLayer layer = currentNodeLayer == null ? innerNodeLayer : currentNodeLayer;
            infoList.add(new ModifierInfo(mutableVector.content[i], coordinator, layer));
            node$iv = node$iv.getChild();
            nodeChain = this;
            i++;
            mutableVector = mutableVector;
        }
        return infoList.asMutableList();
    }

    public final void markAsDetached$ui() {
        for (Modifier.Node node$iv = getTail(); node$iv != null; node$iv = node$iv.getParent()) {
            Modifier.Node it = node$iv;
            if (it.getIsAttached()) {
                it.markAsDetached$ui();
            }
        }
    }

    public final void runDetachLifecycle$ui() {
        for (Modifier.Node node$iv = getTail(); node$iv != null; node$iv = node$iv.getParent()) {
            Modifier.Node it = node$iv;
            if (it.getIsAttached()) {
                it.runDetachLifecycle$ui();
            }
        }
    }

    private final Differ getDiffer(Modifier.Node head, int offset, MutableVector<Modifier.Element> before, MutableVector<Modifier.Element> after, boolean shouldAttachOnInsert) {
        Differ current = this.cachedDiffer;
        if (current == null) {
            Differ it = new Differ(head, offset, before, after, shouldAttachOnInsert);
            this.cachedDiffer = it;
            return it;
        }
        current.setNode(head);
        current.setOffset(offset);
        current.setBefore(before);
        current.setAfter(after);
        current.setShouldAttachOnInsert(shouldAttachOnInsert);
        return current;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void propagateCoordinator(Modifier.Node start, NodeCoordinator coordinator) {
        for (Modifier.Node node = start.getParent(); node != null; node = node.getParent()) {
            if (node == this.sentinelHead) {
                LayoutNode parent$ui = this.layoutNode.getParent$ui();
                coordinator.setWrappedBy$ui(parent$ui != null ? parent$ui.getInnerCoordinator$ui() : null);
                this.outerCoordinator = coordinator;
                return;
            } else {
                Modifier.Node this_$iv = node;
                if (!((this_$iv.getKindSet() & NodeKind.m6860constructorimpl(2)) != 0)) {
                    node.updateCoordinator$ui(coordinator);
                } else {
                    return;
                }
            }
        }
    }

    /* JADX INFO: compiled from: NodeChain.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010\"\u001a\u00020\u0005H\u0016J\u0018\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0016J\u0018\u0010'\u001a\u00020$2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006("}, d2 = {"Landroidx/compose/ui/node/NodeChain$Differ;", "Landroidx/compose/ui/node/DiffCallback;", "node", "Landroidx/compose/ui/Modifier$Node;", TypedValues.CycleType.S_WAVE_OFFSET, "", TtmlNode.ANNOTATION_POSITION_BEFORE, "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/Modifier$Element;", TtmlNode.ANNOTATION_POSITION_AFTER, "shouldAttachOnInsert", "", "<init>", "(Landroidx/compose/ui/node/NodeChain;Landroidx/compose/ui/Modifier$Node;ILandroidx/compose/runtime/collection/MutableVector;Landroidx/compose/runtime/collection/MutableVector;Z)V", "getNode", "()Landroidx/compose/ui/Modifier$Node;", "setNode", "(Landroidx/compose/ui/Modifier$Node;)V", "getOffset", "()I", "setOffset", "(I)V", "getBefore", "()Landroidx/compose/runtime/collection/MutableVector;", "setBefore", "(Landroidx/compose/runtime/collection/MutableVector;)V", "getAfter", "setAfter", "getShouldAttachOnInsert", "()Z", "setShouldAttachOnInsert", "(Z)V", "areItemsTheSame", "oldIndex", "newIndex", "insert", "", "remove", "atIndex", "same", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private final class Differ implements DiffCallback {
        private MutableVector<Modifier.Element> after;
        private MutableVector<Modifier.Element> before;
        private Modifier.Node node;
        private int offset;
        private boolean shouldAttachOnInsert;

        public Differ(Modifier.Node node, int offset, MutableVector<Modifier.Element> mutableVector, MutableVector<Modifier.Element> mutableVector2, boolean shouldAttachOnInsert) {
            this.node = node;
            this.offset = offset;
            this.before = mutableVector;
            this.after = mutableVector2;
            this.shouldAttachOnInsert = shouldAttachOnInsert;
        }

        public final Modifier.Node getNode() {
            return this.node;
        }

        public final void setNode(Modifier.Node node) {
            this.node = node;
        }

        public final int getOffset() {
            return this.offset;
        }

        public final void setOffset(int i) {
            this.offset = i;
        }

        public final MutableVector<Modifier.Element> getBefore() {
            return this.before;
        }

        public final void setBefore(MutableVector<Modifier.Element> mutableVector) {
            this.before = mutableVector;
        }

        public final MutableVector<Modifier.Element> getAfter() {
            return this.after;
        }

        public final void setAfter(MutableVector<Modifier.Element> mutableVector) {
            this.after = mutableVector;
        }

        public final boolean getShouldAttachOnInsert() {
            return this.shouldAttachOnInsert;
        }

        public final void setShouldAttachOnInsert(boolean z) {
            this.shouldAttachOnInsert = z;
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public boolean areItemsTheSame(int oldIndex, int newIndex) {
            MutableVector<Modifier.Element> mutableVector = this.before;
            int index$iv = this.offset + oldIndex;
            Modifier.Element element = mutableVector.content[index$iv];
            MutableVector<Modifier.Element> mutableVector2 = this.after;
            int index$iv2 = this.offset + newIndex;
            return NodeChainKt.actionForModifiers(element, mutableVector2.content[index$iv2]) != 0;
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public void insert(int newIndex) {
            int index = this.offset + newIndex;
            Modifier.Node parent = this.node;
            this.node = NodeChain.this.createAndInsertNodeAsChild(this.after.content[index], parent);
            Logger logger = NodeChain.this.logger;
            if (logger != null) {
                logger.nodeInserted(index, index, this.after.content[index], parent, this.node);
            }
            if (this.shouldAttachOnInsert) {
                Modifier.Node child = this.node.getChild();
                Intrinsics.checkNotNull(child);
                NodeCoordinator childCoordinator = child.getCoordinator();
                Intrinsics.checkNotNull(childCoordinator);
                LayoutModifierNode layoutmod = DelegatableNodeKt.asLayoutModifierNode(this.node);
                if (layoutmod != null) {
                    LayoutModifierNodeCoordinator thisCoordinator = new LayoutModifierNodeCoordinator(NodeChain.this.getLayoutNode(), layoutmod);
                    this.node.updateCoordinator$ui(thisCoordinator);
                    NodeChain.this.propagateCoordinator(this.node, thisCoordinator);
                    thisCoordinator.setWrappedBy$ui(childCoordinator.getWrappedBy());
                    thisCoordinator.setWrapped$ui(childCoordinator);
                    childCoordinator.setWrappedBy$ui(thisCoordinator);
                } else {
                    this.node.updateCoordinator$ui(childCoordinator);
                }
                this.node.markAsAttached$ui();
                this.node.runAttachLifecycle$ui();
                NodeKindKt.autoInvalidateInsertedNode(this.node);
                return;
            }
            this.node.setInsertedNodeAwaitingAttachForInvalidation$ui(true);
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public void remove(int atIndex, int oldIndex) {
            Modifier.Node toRemove = this.node.getChild();
            Intrinsics.checkNotNull(toRemove);
            Logger logger = NodeChain.this.logger;
            if (logger != null) {
                MutableVector<Modifier.Element> mutableVector = this.before;
                int index$iv = this.offset + oldIndex;
                logger.nodeRemoved(oldIndex, mutableVector.content[index$iv], toRemove);
            }
            if ((toRemove.getKindSet() & NodeKind.m6860constructorimpl(2)) != 0) {
                NodeCoordinator removedCoordinator = toRemove.getCoordinator();
                Intrinsics.checkNotNull(removedCoordinator);
                NodeCoordinator parentCoordinator = removedCoordinator.getWrappedBy();
                NodeCoordinator childCoordinator = removedCoordinator.getWrapped();
                Intrinsics.checkNotNull(childCoordinator);
                if (parentCoordinator != null) {
                    parentCoordinator.setWrapped$ui(childCoordinator);
                }
                childCoordinator.setWrappedBy$ui(parentCoordinator);
                NodeChain.this.propagateCoordinator(this.node, childCoordinator);
            }
            this.node = NodeChain.this.detachAndRemoveNode(toRemove);
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public void same(int oldIndex, int newIndex) {
            Modifier.Node child = this.node.getChild();
            Intrinsics.checkNotNull(child);
            this.node = child;
            MutableVector<Modifier.Element> mutableVector = this.before;
            int index$iv = this.offset + oldIndex;
            Modifier.Element prev = mutableVector.content[index$iv];
            MutableVector<Modifier.Element> mutableVector2 = this.after;
            int index$iv2 = this.offset + newIndex;
            Modifier.Element next = mutableVector2.content[index$iv2];
            if (!Intrinsics.areEqual(prev, next)) {
                NodeChain.this.updateNode(prev, next, this.node);
                Logger logger = NodeChain.this.logger;
                if (logger != null) {
                    logger.nodeUpdated(this.offset + oldIndex, this.offset + newIndex, prev, next, this.node);
                    return;
                }
                return;
            }
            Logger logger2 = NodeChain.this.logger;
            if (logger2 != null) {
                logger2.nodeReused(this.offset + oldIndex, this.offset + newIndex, prev, next, this.node);
            }
        }
    }

    private final void structuralUpdate(int offset, MutableVector<Modifier.Element> before, MutableVector<Modifier.Element> after, Modifier.Node tail, boolean shouldAttachOnInsert) {
        Differ differ = getDiffer(tail, offset, before, after, shouldAttachOnInsert);
        MyersDiffKt.executeDiff(before.getSize() - offset, after.getSize() - offset, differ);
        syncAggregateChildKindSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node detachAndRemoveNode(Modifier.Node node) {
        if (node.getIsAttached()) {
            NodeKindKt.autoInvalidateRemovedNode(node);
            node.runDetachLifecycle$ui();
            node.markAsDetached$ui();
        }
        return removeNode(node);
    }

    private final Modifier.Node removeNode(Modifier.Node node) {
        Modifier.Node child = node.getChild();
        Modifier.Node parent = node.getParent();
        if (child != null) {
            child.setParent$ui(parent);
            node.setChild$ui(null);
        }
        if (parent != null) {
            parent.setChild$ui(child);
            node.setParent$ui(null);
        }
        Intrinsics.checkNotNull(parent);
        return parent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node createAndInsertNodeAsChild(Modifier.Element element, Modifier.Node parent) {
        BackwardsCompatNode it;
        if (element instanceof ModifierNodeElement) {
            it = ((ModifierNodeElement) element).getNode();
            it.setKindSet$ui(NodeKindKt.calculateNodeKindSetFromIncludingDelegates(it));
        } else {
            it = new BackwardsCompatNode(element);
        }
        boolean value$iv = !it.getIsAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException("A ModifierNodeElement cannot return an already attached node from create() ");
        }
        it.setInsertedNodeAwaitingAttachForInvalidation$ui(true);
        return insertChild(it, parent);
    }

    private final Modifier.Node insertChild(Modifier.Node node, Modifier.Node parent) {
        Modifier.Node theChild = parent.getChild();
        if (theChild != null) {
            theChild.setParent$ui(node);
            node.setChild$ui(theChild);
        }
        parent.setChild$ui(node);
        node.setParent$ui(parent);
        return node;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNode(Modifier.Element prev, Modifier.Element next, Modifier.Node node) {
        if ((prev instanceof ModifierNodeElement) && (next instanceof ModifierNodeElement)) {
            NodeChainKt.updateUnsafe((ModifierNodeElement) next, node);
            if (node.getIsAttached()) {
                NodeKindKt.autoInvalidateUpdatedNode(node);
                return;
            } else {
                node.setUpdatedNodeAwaitingAttachForInvalidation$ui(true);
                return;
            }
        }
        if (node instanceof BackwardsCompatNode) {
            ((BackwardsCompatNode) node).setElement(next);
            if (node.getIsAttached()) {
                NodeKindKt.autoInvalidateUpdatedNode(node);
                return;
            } else {
                node.setUpdatedNodeAwaitingAttachForInvalidation$ui(true);
                return;
            }
        }
        InlineClassHelperKt.throwIllegalStateException("Unknown Modifier.Node type");
    }

    /* JADX WARN: Type inference failed for: r1v6, types: [T, java.lang.Object] */
    /* JADX INFO: renamed from: firstFromHead-aLcG6gQ$ui, reason: not valid java name */
    public final /* synthetic */ <T> T m6820firstFromHeadaLcG6gQ$ui(int type, Function1<? super T, Boolean> block) {
        int i;
        NodeChain nodeChain;
        int i2;
        int i3;
        int i4;
        int i5;
        Object obj;
        int i6 = 0;
        int i7 = type;
        NodeChain nodeChain2 = this;
        int i8 = 0;
        int i9 = i7;
        if ((nodeChain2.getAggregateChildKindSet() & i9) == 0) {
            return null;
        }
        Modifier.Node head = nodeChain2.getHead();
        while (head != null) {
            Modifier.Node node = head;
            if ((node.getKindSet() & i9) != 0) {
                int i10 = i7;
                Object obj2 = null;
                Object objPop = node;
                while (objPop != null) {
                    i7 = i7;
                    nodeChain2 = nodeChain2;
                    Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                    if (objPop instanceof Object) {
                        ?? r1 = (Object) objPop;
                        if (block.invoke(r1).booleanValue()) {
                            return r1;
                        }
                        i4 = i8;
                        i5 = i9;
                    } else {
                        if (((((Modifier.Node) objPop).getKindSet() & i10) != 0 ? 1 : 0) == 0 || !(objPop instanceof DelegatingNode)) {
                            i4 = i8;
                            i5 = i9;
                        } else {
                            int i11 = 0;
                            Modifier.Node delegate$ui = ((DelegatingNode) objPop).getDelegate();
                            while (delegate$ui != null) {
                                Object obj3 = objPop;
                                Modifier.Node node2 = delegate$ui;
                                if (((node2.getKindSet() & i10) != 0 ? 1 : 0) == 0) {
                                    i8 = i8;
                                    i9 = i9;
                                    obj = obj3;
                                } else {
                                    i11++;
                                    if (i11 == 1) {
                                        obj = node2;
                                        i8 = i8;
                                        i9 = i9;
                                    } else {
                                        MutableVector mutableVector = (MutableVector) obj2;
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        obj2 = mutableVector;
                                        Modifier.Node node3 = (Modifier.Node) obj3;
                                        if (node3 == null) {
                                            obj = obj3;
                                        } else {
                                            MutableVector mutableVector2 = (MutableVector) obj2;
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(node3);
                                            }
                                            obj = null;
                                        }
                                        MutableVector mutableVector3 = (MutableVector) obj2;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(node2);
                                        }
                                        i11 = i11;
                                    }
                                }
                                delegate$ui = delegate$ui.getChild();
                                objPop = obj;
                                i8 = i8;
                                i9 = i9;
                            }
                            Object obj4 = objPop;
                            i4 = i8;
                            i5 = i9;
                            if (i11 == 1) {
                                objPop = obj4;
                                i8 = i4;
                                i9 = i5;
                            }
                        }
                    }
                    objPop = DelegatableNodeKt.pop((MutableVector) obj2);
                    i8 = i4;
                    i9 = i5;
                }
                i = i7;
                nodeChain = nodeChain2;
                i2 = i8;
                i3 = i9;
            } else {
                i = i7;
                nodeChain = nodeChain2;
                i2 = i8;
                i3 = i9;
            }
            if ((node.getAggregateChildKindSet() & i3) == 0) {
                return null;
            }
            head = head.getChild();
            i6 = i6;
            i7 = i;
            nodeChain2 = nodeChain;
            i8 = i2;
            i9 = i3;
        }
        return null;
    }

    /* JADX INFO: renamed from: headToTail-aLcG6gQ$ui, reason: not valid java name */
    public final /* synthetic */ <T> void m6823headToTailaLcG6gQ$ui(int type, Function1<? super T, Unit> block) {
        int i;
        int mask$iv;
        NodeChain this_$iv;
        int $i$f$headToTail$ui;
        NodeChain this_$iv$iv;
        NodeChain this_$iv2;
        int $i$f$headToTail$ui2;
        NodeChain this_$iv$iv2;
        NodeChain this_$iv3;
        int i2 = 0;
        int mask$iv2 = type;
        NodeChain this_$iv4 = this;
        int $i$f$headToTail$ui3 = 0;
        if ((this_$iv4.getAggregateChildKindSet() & mask$iv2) == 0) {
            return;
        }
        NodeChain this_$iv$iv3 = this_$iv4;
        Modifier.Node node$iv$iv = this_$iv$iv3.getHead();
        while (node$iv$iv != null) {
            Modifier.Node it$iv = node$iv$iv;
            if ((it$iv.getKindSet() & mask$iv2) == 0) {
                i = i2;
                mask$iv = mask$iv2;
                this_$iv = this_$iv4;
                $i$f$headToTail$ui = $i$f$headToTail$ui3;
                this_$iv$iv = this_$iv$iv3;
            } else {
                Modifier.Node it = it$iv;
                Object stack$iv = null;
                Object node$iv = it;
                while (node$iv != null) {
                    i2 = i2;
                    mask$iv2 = mask$iv2;
                    Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                    if (node$iv instanceof Object) {
                        block.invoke(node$iv);
                        this_$iv2 = this_$iv4;
                        $i$f$headToTail$ui2 = $i$f$headToTail$ui3;
                        this_$iv$iv2 = this_$iv$iv3;
                    } else {
                        if (!((((Modifier.Node) node$iv).getKindSet() & type) != 0) || !(node$iv instanceof DelegatingNode)) {
                            this_$iv2 = this_$iv4;
                            $i$f$headToTail$ui2 = $i$f$headToTail$ui3;
                            this_$iv$iv2 = this_$iv$iv3;
                        } else {
                            int count$iv = 0;
                            Modifier.Node node$iv$iv2 = ((DelegatingNode) node$iv).getDelegate();
                            while (node$iv$iv2 != null) {
                                Modifier.Node next$iv = node$iv$iv2;
                                if (((next$iv.getKindSet() & type) != 0 ? 1 : 0) == 0) {
                                    this_$iv3 = this_$iv4;
                                    $i$f$headToTail$ui3 = $i$f$headToTail$ui3;
                                    this_$iv$iv3 = this_$iv$iv3;
                                } else {
                                    count$iv++;
                                    this_$iv3 = this_$iv4;
                                    if (count$iv == 1) {
                                        node$iv = next$iv;
                                        $i$f$headToTail$ui3 = $i$f$headToTail$ui3;
                                        this_$iv$iv3 = this_$iv$iv3;
                                    } else {
                                        Object mutableVector = (MutableVector) stack$iv;
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
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
                                            mutableVector3.add(next$iv);
                                        }
                                        count$iv = count$iv;
                                    }
                                }
                                node$iv$iv2 = node$iv$iv2.getChild();
                                this_$iv4 = this_$iv3;
                                $i$f$headToTail$ui3 = $i$f$headToTail$ui3;
                                this_$iv$iv3 = this_$iv$iv3;
                            }
                            this_$iv2 = this_$iv4;
                            $i$f$headToTail$ui2 = $i$f$headToTail$ui3;
                            this_$iv$iv2 = this_$iv$iv3;
                            if (count$iv == 1) {
                                this_$iv4 = this_$iv2;
                                $i$f$headToTail$ui3 = $i$f$headToTail$ui2;
                                this_$iv$iv3 = this_$iv$iv2;
                            }
                        }
                    }
                    node$iv = DelegatableNodeKt.pop((MutableVector) stack$iv);
                    this_$iv4 = this_$iv2;
                    $i$f$headToTail$ui3 = $i$f$headToTail$ui2;
                    this_$iv$iv3 = this_$iv$iv2;
                }
                i = i2;
                mask$iv = mask$iv2;
                this_$iv = this_$iv4;
                $i$f$headToTail$ui = $i$f$headToTail$ui3;
                this_$iv$iv = this_$iv$iv3;
            }
            if ((it$iv.getAggregateChildKindSet() & mask$iv) == 0) {
                return;
            }
            node$iv$iv = node$iv$iv.getChild();
            i2 = i;
            mask$iv2 = mask$iv;
            this_$iv4 = this_$iv;
            $i$f$headToTail$ui3 = $i$f$headToTail$ui;
            this_$iv$iv3 = this_$iv$iv;
        }
    }

    public final void headToTail$ui(int mask, Function1<? super Modifier.Node, Unit> block) {
        if ((getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node node$iv = getHead(); node$iv != null; node$iv = node$iv.getChild()) {
            Modifier.Node it = node$iv;
            if ((it.getKindSet() & mask) != 0) {
                block.invoke(it);
            }
            if ((it.getAggregateChildKindSet() & mask) == 0) {
                return;
            }
        }
    }

    public final void headToTail$ui(Function1<? super Modifier.Node, Unit> block) {
        for (Modifier.Node node = getHead(); node != null; node = node.getChild()) {
            block.invoke(node);
        }
    }

    public final void headToTailExclusive$ui(Function1<? super Modifier.Node, Unit> block) {
        for (Modifier.Node node = getHead(); node != null && node != getTail(); node = node.getChild()) {
            block.invoke(node);
        }
    }

    /* JADX INFO: renamed from: tailToHead-aLcG6gQ$ui, reason: not valid java name */
    public final /* synthetic */ <T> void m6825tailToHeadaLcG6gQ$ui(int type, Function1<? super T, Unit> block) {
        NodeChain this_$iv;
        int $i$f$tailToHead$ui;
        NodeChain this_$iv$iv;
        NodeChain this_$iv2;
        int i = 0;
        int mask$iv = type;
        NodeChain this_$iv3 = this;
        int $i$f$tailToHead$ui2 = 0;
        if ((this_$iv3.getAggregateChildKindSet() & mask$iv) == 0) {
            return;
        }
        NodeChain this_$iv$iv2 = this_$iv3;
        Modifier.Node node$iv$iv = this_$iv$iv2.getTail();
        while (node$iv$iv != null) {
            Modifier.Node it$iv = node$iv$iv;
            if ((it$iv.getKindSet() & mask$iv) != 0) {
                Modifier.Node it = it$iv;
                Object stack$iv = null;
                Object node$iv = it;
                while (node$iv != null) {
                    i = i;
                    mask$iv = mask$iv;
                    Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                    if (node$iv instanceof Object) {
                        block.invoke(node$iv);
                        this_$iv = this_$iv3;
                        $i$f$tailToHead$ui = $i$f$tailToHead$ui2;
                        this_$iv$iv = this_$iv$iv2;
                    } else {
                        if (!((((Modifier.Node) node$iv).getKindSet() & type) != 0) || !(node$iv instanceof DelegatingNode)) {
                            this_$iv = this_$iv3;
                            $i$f$tailToHead$ui = $i$f$tailToHead$ui2;
                            this_$iv$iv = this_$iv$iv2;
                        } else {
                            int count$iv = 0;
                            Modifier.Node node$iv$iv2 = ((DelegatingNode) node$iv).getDelegate();
                            while (node$iv$iv2 != null) {
                                Modifier.Node next$iv = node$iv$iv2;
                                if (((next$iv.getKindSet() & type) != 0 ? 1 : 0) == 0) {
                                    this_$iv2 = this_$iv3;
                                    $i$f$tailToHead$ui2 = $i$f$tailToHead$ui2;
                                    this_$iv$iv2 = this_$iv$iv2;
                                } else {
                                    count$iv++;
                                    this_$iv2 = this_$iv3;
                                    if (count$iv == 1) {
                                        node$iv = next$iv;
                                        $i$f$tailToHead$ui2 = $i$f$tailToHead$ui2;
                                        this_$iv$iv2 = this_$iv$iv2;
                                    } else {
                                        Object mutableVector = (MutableVector) stack$iv;
                                        if (mutableVector == null) {
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
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
                                            mutableVector3.add(next$iv);
                                        }
                                        count$iv = count$iv;
                                    }
                                }
                                node$iv$iv2 = node$iv$iv2.getChild();
                                this_$iv3 = this_$iv2;
                                $i$f$tailToHead$ui2 = $i$f$tailToHead$ui2;
                                this_$iv$iv2 = this_$iv$iv2;
                            }
                            this_$iv = this_$iv3;
                            $i$f$tailToHead$ui = $i$f$tailToHead$ui2;
                            this_$iv$iv = this_$iv$iv2;
                            if (count$iv == 1) {
                                this_$iv3 = this_$iv;
                                $i$f$tailToHead$ui2 = $i$f$tailToHead$ui;
                                this_$iv$iv2 = this_$iv$iv;
                            }
                        }
                    }
                    node$iv = DelegatableNodeKt.pop((MutableVector) stack$iv);
                    this_$iv3 = this_$iv;
                    $i$f$tailToHead$ui2 = $i$f$tailToHead$ui;
                    this_$iv$iv2 = this_$iv$iv;
                }
            }
            node$iv$iv = node$iv$iv.getParent();
            i = i;
            mask$iv = mask$iv;
            this_$iv3 = this_$iv3;
            $i$f$tailToHead$ui2 = $i$f$tailToHead$ui2;
            this_$iv$iv2 = this_$iv$iv2;
        }
    }

    public final void tailToHead$ui(int mask, Function1<? super Modifier.Node, Unit> block) {
        if ((getAggregateChildKindSet() & mask) == 0) {
            return;
        }
        for (Modifier.Node node$iv = getTail(); node$iv != null; node$iv = node$iv.getParent()) {
            Modifier.Node it = node$iv;
            if ((it.getKindSet() & mask) != 0) {
                block.invoke(it);
            }
        }
    }

    public final void tailToHead$ui(Function1<? super Modifier.Node, Unit> block) {
        for (Modifier.Node node = getTail(); node != null; node = node.getParent()) {
            block.invoke(node);
        }
    }

    /* JADX INFO: renamed from: tail-H91voCI$ui, reason: not valid java name */
    public final /* synthetic */ <T> T m6824tailH91voCI$ui(int type) {
        int i;
        Object obj;
        int i2 = 0;
        int i3 = type;
        NodeChain nodeChain = this;
        int i4 = 0;
        if ((nodeChain.getAggregateChildKindSet() & i3) == 0) {
            return null;
        }
        Modifier.Node tail = nodeChain.getTail();
        while (tail != null) {
            Modifier.Node node = tail;
            if ((node.getKindSet() & i3) != 0) {
                int i5 = i3;
                Object mutableVector = null;
                Object objPop = node;
                while (objPop != null) {
                    i3 = i3;
                    nodeChain = nodeChain;
                    Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                    if (objPop instanceof Object) {
                        return (T) objPop;
                    }
                    if (((((Modifier.Node) objPop).getKindSet() & i5) != 0 ? 1 : 0) == 0 || !(objPop instanceof DelegatingNode)) {
                        i = i4;
                        objPop = DelegatableNodeKt.pop((MutableVector) mutableVector);
                        i4 = i;
                    } else {
                        int i6 = 0;
                        Modifier.Node delegate$ui = ((DelegatingNode) objPop).getDelegate();
                        while (delegate$ui != null) {
                            Modifier.Node node2 = delegate$ui;
                            if (((node2.getKindSet() & i5) != 0 ? 1 : 0) != 0) {
                                i6++;
                                Object obj2 = objPop;
                                if (i6 == 1) {
                                    objPop = node2;
                                    i4 = i4;
                                } else {
                                    MutableVector mutableVector2 = (MutableVector) mutableVector;
                                    mutableVector = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                    Modifier.Node node3 = (Modifier.Node) obj2;
                                    if (node3 == null) {
                                        obj = obj2;
                                    } else {
                                        MutableVector mutableVector3 = (MutableVector) mutableVector;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(node3);
                                        }
                                        obj = null;
                                    }
                                    MutableVector mutableVector4 = (MutableVector) mutableVector;
                                    if (mutableVector4 != null) {
                                        mutableVector4.add(node2);
                                    }
                                    objPop = obj;
                                    i6 = i6;
                                }
                            } else {
                                i4 = i4;
                            }
                            delegate$ui = delegate$ui.getChild();
                            i4 = i4;
                        }
                        Object obj3 = objPop;
                        i = i4;
                        if (i6 == 1) {
                            objPop = obj3;
                            i4 = i;
                        } else {
                            objPop = DelegatableNodeKt.pop((MutableVector) mutableVector);
                            i4 = i;
                        }
                    }
                }
            }
            tail = tail.getParent();
            i2 = i2;
            i3 = i3;
            nodeChain = nodeChain;
            i4 = i4;
        }
        return null;
    }

    /* JADX INFO: renamed from: head-H91voCI$ui, reason: not valid java name */
    public final /* synthetic */ <T> T m6822headH91voCI$ui(int type) {
        int i;
        NodeChain nodeChain;
        int i2;
        int i3;
        Object obj;
        int i4 = 0;
        int i5 = type;
        NodeChain nodeChain2 = this;
        int i6 = 0;
        if ((nodeChain2.getAggregateChildKindSet() & i5) == 0) {
            return null;
        }
        Modifier.Node head = nodeChain2.getHead();
        while (head != null) {
            Modifier.Node node = head;
            if ((node.getKindSet() & i5) != 0) {
                int i7 = i5;
                Object mutableVector = null;
                Object objPop = node;
                while (objPop != null) {
                    i5 = i5;
                    nodeChain2 = nodeChain2;
                    Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                    if (objPop instanceof Object) {
                        return (T) objPop;
                    }
                    if (((((Modifier.Node) objPop).getKindSet() & i7) != 0 ? 1 : 0) == 0 || !(objPop instanceof DelegatingNode)) {
                        i3 = i6;
                        objPop = DelegatableNodeKt.pop((MutableVector) mutableVector);
                        i6 = i3;
                    } else {
                        int i8 = 0;
                        Modifier.Node delegate$ui = ((DelegatingNode) objPop).getDelegate();
                        while (delegate$ui != null) {
                            Modifier.Node node2 = delegate$ui;
                            if (((node2.getKindSet() & i7) != 0 ? 1 : 0) != 0) {
                                i8++;
                                Object obj2 = objPop;
                                if (i8 == 1) {
                                    objPop = node2;
                                    i6 = i6;
                                } else {
                                    MutableVector mutableVector2 = (MutableVector) mutableVector;
                                    mutableVector = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                    Modifier.Node node3 = (Modifier.Node) obj2;
                                    if (node3 == null) {
                                        obj = obj2;
                                    } else {
                                        MutableVector mutableVector3 = (MutableVector) mutableVector;
                                        if (mutableVector3 != null) {
                                            mutableVector3.add(node3);
                                        }
                                        obj = null;
                                    }
                                    MutableVector mutableVector4 = (MutableVector) mutableVector;
                                    if (mutableVector4 != null) {
                                        mutableVector4.add(node2);
                                    }
                                    objPop = obj;
                                    i8 = i8;
                                }
                            } else {
                                i6 = i6;
                            }
                            delegate$ui = delegate$ui.getChild();
                            i6 = i6;
                        }
                        Object obj3 = objPop;
                        i3 = i6;
                        if (i8 == 1) {
                            objPop = obj3;
                            i6 = i3;
                        } else {
                            objPop = DelegatableNodeKt.pop((MutableVector) mutableVector);
                            i6 = i3;
                        }
                    }
                }
                i = i5;
                nodeChain = nodeChain2;
                i2 = i6;
            } else {
                i = i5;
                nodeChain = nodeChain2;
                i2 = i6;
            }
            if ((node.getAggregateChildKindSet() & i5) == 0) {
                return null;
            }
            head = head.getChild();
            i4 = i4;
            i5 = i;
            nodeChain2 = nodeChain;
            i6 = i2;
        }
        return null;
    }

    /* JADX INFO: renamed from: has-H91voCI$ui, reason: not valid java name */
    public final boolean m6821hasH91voCI$ui(int type) {
        return (getAggregateChildKindSet() & type) != 0;
    }

    public final boolean has$ui(int mask) {
        return (getAggregateChildKindSet() & mask) != 0;
    }

    public String toString() {
        StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
        $this$toString_u24lambda_u240.append("[");
        if (this.head == this.tail) {
            $this$toString_u24lambda_u240.append("]");
        } else {
            for (Modifier.Node node$iv = getHead(); node$iv != null && node$iv != getTail(); node$iv = node$iv.getChild()) {
                Modifier.Node it = node$iv;
                $this$toString_u24lambda_u240.append(String.valueOf(it));
                if (it.getChild() == this.tail) {
                    $this$toString_u24lambda_u240.append("]");
                    break;
                }
                $this$toString_u24lambda_u240.append(",");
            }
        }
        String string = $this$toString_u24lambda_u240.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
