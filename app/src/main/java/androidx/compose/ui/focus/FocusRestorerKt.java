package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.PinnableContainer;
import androidx.compose.ui.layout.PinnableContainerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: FocusRestorer.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0000\u001a\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\u0004H\u0000\u001a\u0014\u0010\b\u001a\u00020\t*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\t2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"PrevFocusedChild", "", "saveFocusedChild", "", "Landroidx/compose/ui/focus/FocusTargetNode;", "restoreFocusedChild", "pinFocusedChild", "Landroidx/compose/ui/layout/PinnableContainer$PinnedHandle;", "focusRestorer", "Landroidx/compose/ui/Modifier;", "fallback", "Landroidx/compose/ui/focus/FocusRequester;", "onRestoreFailed", "Lkotlin/Function0;", "ui"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FocusRestorerKt {
    private static final String PrevFocusedChild = "previouslyFocusedChildHash";

    public static final boolean saveFocusedChild(final FocusTargetNode $this$saveFocusedChild) {
        DelegatableNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
        int i;
        boolean z;
        DelegatingNode this_$iv$iv$iv;
        Modifier.Node node;
        Modifier.Node node2;
        $this$saveFocusedChild = $this$saveFocusedChild;
        boolean z2 = false;
        if (!$this$saveFocusedChild.getFocusState().getHasFocus()) {
            return false;
        }
        FocusTargetNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$saveFocusedChild;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv2.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            boolean z3 = true;
            if (!(branches$iv$iv.getSize() != 0 ? true : z2)) {
                return false;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        $this$saveFocusedChild = $this$saveFocusedChild;
                        z2 = false;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i2 = iM6860constructorimpl;
                        MutableVector mutableVector = null;
                        Modifier.Node this_$iv$iv$iv2 = it$iv;
                        while (this_$iv$iv$iv2 != null) {
                            boolean z4 = z3;
                            if (this_$iv$iv$iv2 instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) this_$iv$iv$iv2;
                                if (it.getFocusState().getHasFocus()) {
                                    $this$saveFocusedChild.setPreviouslyFocusedChildHash(DelegatableNodeKt.requireLayoutNode(it).getCompositeKeyHash());
                                    SaveableStateRegistry saveableStateRegistry = (SaveableStateRegistry) CompositionLocalConsumerModifierNodeKt.currentValueOf($this$saveFocusedChild, SaveableStateRegistryKt.getLocalSaveableStateRegistry());
                                    if (saveableStateRegistry != null) {
                                        saveableStateRegistry.registerProvider(PrevFocusedChild, new Function0<Object>() { // from class: androidx.compose.ui.focus.FocusRestorerKt$saveFocusedChild$1$1
                                            {
                                                super(0);
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public final Object invoke() {
                                                return Integer.valueOf($this$saveFocusedChild.getPreviouslyFocusedChildHash());
                                            }
                                        });
                                    }
                                    return z4;
                                }
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                i = iM6860constructorimpl;
                                z = z4;
                            } else {
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                                i = iM6860constructorimpl;
                                if (((this_$iv$iv$iv2.getKindSet() & i2) != 0 ? z4 : false) && (this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if ((next$iv$iv.getKindSet() & i2) != 0 ? z4 : false) {
                                            count$iv$iv++;
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            if (count$iv$iv == z4) {
                                                node = next$iv$iv;
                                            } else {
                                                MutableVector mutableVector2 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                Modifier.Node theNode$iv$iv = this_$iv$iv$iv2;
                                                if (theNode$iv$iv != null) {
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv);
                                                    }
                                                    node2 = null;
                                                }
                                                if (mutableVector2 != null) {
                                                    node2 = this_$iv$iv$iv2;
                                                    mutableVector2.add(next$iv$iv);
                                                } else {
                                                    node2 = this_$iv$iv$iv2;
                                                }
                                                node = node2;
                                                count$iv$iv = count$iv$iv;
                                                mutableVector = mutableVector2;
                                            }
                                        } else {
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            node = this_$iv$iv$iv2;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        this_$iv$iv$iv3 = this_$iv$iv$iv;
                                        this_$iv$iv$iv2 = node;
                                        z4 = true;
                                    }
                                    Modifier.Node node3 = this_$iv$iv$iv2;
                                    z = true;
                                    if (count$iv$iv == 1) {
                                        z3 = true;
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        iM6860constructorimpl = i;
                                        this_$iv$iv$iv2 = node3;
                                    }
                                } else {
                                    z = z4;
                                }
                            }
                            this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector);
                            z3 = z;
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            iM6860constructorimpl = i;
                        }
                        $this$saveFocusedChild = $this$saveFocusedChild;
                        z2 = false;
                        break;
                    }
                    node$iv$iv = node$iv$iv.getChild();
                    $this$saveFocusedChild = $this$saveFocusedChild;
                    $this$visitChildren_u2dY_u2dYKmho_u24default$iv2 = $this$visitChildren_u2dY_u2dYKmho_u24default$iv2;
                }
            } else {
                DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            }
        }
    }

    public static final boolean restoreFocusedChild(FocusTargetNode $this$restoreFocusedChild) {
        int $i$f$forEachImmediateDelegate$ui;
        DelegatingNode this_$iv$iv$iv;
        SaveableStateRegistry savableStateRegistry;
        Object it;
        if ($this$restoreFocusedChild.getPreviouslyFocusedChildHash() == 0 && (savableStateRegistry = (SaveableStateRegistry) CompositionLocalConsumerModifierNodeKt.currentValueOf($this$restoreFocusedChild, SaveableStateRegistryKt.getLocalSaveableStateRegistry())) != null && (it = savableStateRegistry.consumeRestored(PrevFocusedChild)) != null) {
            $this$restoreFocusedChild.setPreviouslyFocusedChildHash(((Integer) it).intValue());
        }
        boolean z = false;
        if ($this$restoreFocusedChild.getPreviouslyFocusedChildHash() == 0) {
            return false;
        }
        FocusTargetNode $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$restoreFocusedChild;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
        boolean zOrder$iv = false;
        boolean value$iv$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitChildren_u2dY_u2dYKmho_u24default$iv.getNode(), false);
        } else {
            branches$iv$iv.add(child$iv$iv);
        }
        while (true) {
            if (!(branches$iv$iv.getSize() != 0 ? true : z)) {
                return false;
            }
            Modifier.Node branch$iv$iv = (Modifier.Node) branches$iv$iv.removeAt(branches$iv$iv.getSize() - 1);
            if ((branch$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                Modifier.Node node$iv$iv = branch$iv$iv;
                while (true) {
                    if (node$iv$iv == null) {
                        z = false;
                        break;
                    }
                    if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv;
                        int i = iM6860constructorimpl;
                        MutableVector mutableVector = null;
                        Modifier.Node this_$iv$iv$iv2 = it$iv;
                        while (this_$iv$iv$iv2 != null) {
                            if (this_$iv$iv$iv2 instanceof FocusTargetNode) {
                                FocusTargetNode it2 = (FocusTargetNode) this_$iv$iv$iv2;
                                if (it2.getIsAttached() && DelegatableNodeKt.requireLayoutNode(it2).getCompositeKeyHash() == $this$restoreFocusedChild.getPreviouslyFocusedChildHash()) {
                                    if (ComposeUiFlags.isRequestFocusOnNonFocusableFocusTargetEnabled) {
                                        return restoreFocusedChild(it2) || (it2.fetchFocusProperties$ui().getCanFocus() && FocusTargetModifierNode.m4759requestFocus3ESFkO8$default(it2, 0, 1, null));
                                    }
                                    return restoreFocusedChild(it2) || FocusTargetModifierNode.m4759requestFocus3ESFkO8$default(it2, 0, 1, null);
                                }
                                $i$f$forEachImmediateDelegate$ui = 1;
                            } else {
                                $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                iM6860constructorimpl = iM6860constructorimpl;
                                zOrder$iv = zOrder$iv;
                                if (((this_$iv$iv$iv2.getKindSet() & i) != 0 ? 1 : 0) == 0 || !(this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                    $i$f$forEachImmediateDelegate$ui = 1;
                                } else {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            if (count$iv$iv == 1) {
                                                this_$iv$iv$iv2 = next$iv$iv;
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
                                                    this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                                    mutableVector2.add(next$iv$iv);
                                                }
                                                this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                                mutableVector = mutableVector2;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        this_$iv$iv$iv3 = this_$iv$iv$iv;
                                    }
                                    Modifier.Node node = this_$iv$iv$iv2;
                                    $i$f$forEachImmediateDelegate$ui = 1;
                                    if (count$iv$iv == 1) {
                                        $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                                        iM6860constructorimpl = iM6860constructorimpl;
                                        zOrder$iv = zOrder$iv;
                                        this_$iv$iv$iv2 = node;
                                    }
                                }
                            }
                            this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector);
                            $this$visitChildren_u2dY_u2dYKmho_u24default$iv = $this$visitChildren_u2dY_u2dYKmho_u24default$iv;
                            iM6860constructorimpl = iM6860constructorimpl;
                            zOrder$iv = zOrder$iv;
                        }
                        z = false;
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

    public static final PinnableContainer.PinnedHandle pinFocusedChild(FocusTargetNode $this$pinFocusedChild) {
        PinnableContainer pinnableContainer;
        FocusTargetNode focusTargetNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode($this$pinFocusedChild);
        if (focusTargetNodeFindActiveFocusNode == null || (pinnableContainer = (PinnableContainer) CompositionLocalConsumerModifierNodeKt.currentValueOf(focusTargetNodeFindActiveFocusNode, PinnableContainerKt.getLocalPinnableContainer())) == null) {
            return null;
        }
        return pinnableContainer.pin();
    }

    public static /* synthetic */ Modifier focusRestorer$default(Modifier modifier, FocusRequester focusRequester, int i, Object obj) {
        if ((i & 1) != 0) {
            focusRequester = FocusRequester.INSTANCE.getDefault();
        }
        return focusRestorer(modifier, focusRequester);
    }

    public static final Modifier focusRestorer(Modifier $this$focusRestorer, FocusRequester fallback) {
        return $this$focusRestorer.then(new FocusRestorerElement(fallback));
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use focusRestorer(FocusRequester) instead", replaceWith = @ReplaceWith(expression = "this.focusRestorer(onRestoreFailed())", imports = {}))
    public static final Modifier focusRestorer(Modifier $this$focusRestorer, Function0<FocusRequester> function0) {
        FocusRequester focusRequesterInvoke;
        if (function0 == null || (focusRequesterInvoke = function0.invoke()) == null) {
            focusRequesterInvoke = FocusRequester.INSTANCE.getDefault();
        }
        return focusRestorer($this$focusRestorer, focusRequesterInvoke);
    }
}
