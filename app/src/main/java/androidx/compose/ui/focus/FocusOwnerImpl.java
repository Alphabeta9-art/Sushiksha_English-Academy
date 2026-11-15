package androidx.compose.ui.focus;

import android.os.Trace;
import android.view.KeyEvent;
import androidx.collection.MutableLongSet;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.indirect.IndirectPointerEvent;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.indirect.IndirectPointerInputModifierNode;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: FocusOwnerImpl.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0002\b\u001aJ!\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020!H\u0016J\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0015H\u0016J/\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b&\u0010'J\u0017\u0010(\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b)\u0010*J\u001a\u0010#\u001a\u00020\u00152\b\b\u0002\u0010+\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0015H\u0002J\u0017\u0010,\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b-\u0010*J\u001f\u0010,\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u0015H\u0016¢\u0006\u0004\b/\u00100J7\u00101\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u00102\u001a\u0004\u0018\u00010\u00192\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001504H\u0016¢\u0006\u0004\b5\u00106J%\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00150;H\u0016¢\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\u00020\u00152\u0006\u00108\u001a\u000209H\u0016¢\u0006\u0004\b?\u0010@J\u001e\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020C2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00150;H\u0016J\u0010\u0010D\u001a\u00020\u00152\u0006\u0010B\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020!H\u0016J\b\u0010G\u001a\u00020!H\u0016J\u0010\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020\tH\u0016J\u0010\u0010H\u001a\u00020!2\u0006\u0010I\u001a\u00020JH\u0016J\b\u0010K\u001a\u00020!H\u0016Jd\u0010L\u001a\u00020!\"\n\b\u0000\u0010M\u0018\u0001*\u00020N*\u00020N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002HM0P2\u0012\u0010Q\u001a\u000e\u0012\u0004\u0012\u0002HM\u0012\u0004\u0012\u00020!042\f\u0010R\u001a\b\u0012\u0004\u0012\u00020!0;2\u0012\u0010S\u001a\u000e\u0012\u0004\u0012\u0002HM\u0012\u0004\u0012\u00020!04H\u0082\b¢\u0006\u0004\bT\u0010UJ0\u0010V\u001a\u0004\u0018\u0001HM\"\n\b\u0000\u0010M\u0018\u0001*\u00020W*\u00020N2\f\u0010O\u001a\b\u0012\u0004\u0012\u0002HM0PH\u0082\b¢\u0006\u0004\bX\u0010YJ\n\u0010Z\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010[\u001a\u00020\u0015H\u0016J\b\u0010\\\u001a\u00020\u0015H\u0016J\n\u0010]\u001a\u0004\u0018\u00010\tH\u0002J\u000e\u0010o\u001a\u0004\u0018\u00010p*\u00020NH\u0002J\u0017\u0010q\u001a\u00020\u00152\u0006\u00108\u001a\u000209H\u0002¢\u0006\u0004\br\u0010@R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010^\u001a\u00020_8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u001a\u0010b\u001a\b\u0012\u0004\u0012\u00020d0cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\be\u0010fR*\u0010h\u001a\u0004\u0018\u00010\t2\b\u0010g\u001a\u0004\u0018\u00010\t8V@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010\u000b\"\u0004\bj\u0010\rR$\u0010k\u001a\u00020\u00152\u0006\u0010g\u001a\u00020\u0015@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010l\"\u0004\bm\u0010n¨\u0006s"}, d2 = {"Landroidx/compose/ui/focus/FocusOwnerImpl;", "Landroidx/compose/ui/focus/FocusOwner;", "platformFocusOwner", "Landroidx/compose/ui/focus/PlatformFocusOwner;", "owner", "Landroidx/compose/ui/node/Owner;", "<init>", "(Landroidx/compose/ui/focus/PlatformFocusOwner;Landroidx/compose/ui/node/Owner;)V", "rootFocusNode", "Landroidx/compose/ui/focus/FocusTargetNode;", "getRootFocusNode$ui", "()Landroidx/compose/ui/focus/FocusTargetNode;", "setRootFocusNode$ui", "(Landroidx/compose/ui/focus/FocusTargetNode;)V", "focusInvalidationManager", "Landroidx/compose/ui/focus/FocusInvalidationManager;", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "requestOwnerFocus", "", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", "previouslyFocusedRect", "Landroidx/compose/ui/geometry/Rect;", "requestOwnerFocus-7o62pno", "keysCurrentlyDown", "Landroidx/collection/MutableLongSet;", "takeFocus", "takeFocus-aToIllA", "(ILandroidx/compose/ui/geometry/Rect;)Z", "releaseFocus", "", "clearOwnerFocus", "clearFocus", "force", "refreshFocusEvents", "clearFocus-I7lrPNg", "(ZZZI)Z", "resetFocus", "resetFocus-3ESFkO8", "(I)Z", "forced", "moveFocus", "moveFocus-3ESFkO8", "wrapAroundForOneDimensionalFocus", "moveFocus-aToIllA", "(IZ)Z", "focusSearch", "focusedRect", "onFound", "Lkotlin/Function1;", "focusSearch-ULY8qGw", "(ILandroidx/compose/ui/geometry/Rect;Lkotlin/jvm/functions/Function1;)Ljava/lang/Boolean;", "dispatchKeyEvent", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "onFocusedItem", "Lkotlin/Function0;", "dispatchKeyEvent-YhN2O0w", "(Landroid/view/KeyEvent;Lkotlin/jvm/functions/Function0;)Z", "dispatchInterceptedSoftKeyboardEvent", "dispatchInterceptedSoftKeyboardEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "dispatchRotaryEvent", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/rotary/RotaryScrollEvent;", "dispatchIndirectPointerEvent", "Landroidx/compose/ui/input/indirect/IndirectPointerEvent;", "dispatchIndirectPointerCancel", "focusTargetAvailable", "scheduleInvalidation", "node", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "scheduleInvalidationForOwner", "traverseAncestorsIncludingSelf", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/node/DelegatableNode;", "type", "Landroidx/compose/ui/node/NodeKind;", "onPreVisit", "onVisit", "onPostVisit", "traverseAncestorsIncludingSelf-QFhIj7k", "(Landroidx/compose/ui/node/DelegatableNode;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "nearestAncestorIncludingSelf", "", "nearestAncestorIncludingSelf-64DMado", "(Landroidx/compose/ui/node/DelegatableNode;I)Ljava/lang/Object;", "getFocusRect", "hasFocusableContent", "hasNonInteropFocusableContent", "findFocusTargetNode", "rootState", "Landroidx/compose/ui/focus/FocusState;", "getRootState", "()Landroidx/compose/ui/focus/FocusState;", "listeners", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/ui/focus/FocusListener;", "getListeners", "()Landroidx/collection/MutableObjectList;", "value", "activeFocusTargetNode", "getActiveFocusTargetNode", "setActiveFocusTargetNode", "isFocusCaptured", "()Z", "setFocusCaptured", "(Z)V", "lastLocalKeyInputNode", "Landroidx/compose/ui/Modifier$Node;", "validateKeyEvent", "validateKeyEvent-ZmokQxo", "ui"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FocusOwnerImpl implements FocusOwner {
    public static final int $stable = 8;
    private FocusTargetNode activeFocusTargetNode;
    private final FocusInvalidationManager focusInvalidationManager;
    private boolean isFocusCaptured;
    private MutableLongSet keysCurrentlyDown;
    private final Owner owner;
    private final PlatformFocusOwner platformFocusOwner;
    private FocusTargetNode rootFocusNode = new FocusTargetNode(Focusability.INSTANCE.m4787getNeverLCbbffg(), false, null, null, 14, null);
    private final Modifier modifier = new ModifierNodeElement<FocusTargetNode>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$1
        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* JADX INFO: renamed from: create */
        public FocusTargetNode getNode() {
            return this.this$0.getRootFocusNode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(FocusTargetNode node) {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
            $this$inspectableProperties.setName("RootFocusTarget");
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return this.this$0.getRootFocusNode().hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            return other == this;
        }
    };
    private final MutableObjectList<FocusListener> listeners = new MutableObjectList<>(1);

    /* JADX INFO: compiled from: FocusOwnerImpl.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CustomDestinationResult.values().length];
            try {
                iArr[CustomDestinationResult.Redirected.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CustomDestinationResult.Cancelled.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[CustomDestinationResult.RedirectCancelled.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[CustomDestinationResult.None.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FocusOwnerImpl(PlatformFocusOwner platformFocusOwner, Owner owner) {
        this.platformFocusOwner = platformFocusOwner;
        this.owner = owner;
        this.focusInvalidationManager = new FocusInvalidationManager(this, this.owner);
    }

    /* JADX INFO: renamed from: getRootFocusNode$ui, reason: from getter */
    public final FocusTargetNode getRootFocusNode() {
        return this.rootFocusNode;
    }

    public final void setRootFocusNode$ui(FocusTargetNode focusTargetNode) {
        this.rootFocusNode = focusTargetNode;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Modifier getModifier() {
        return this.modifier;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: requestOwnerFocus-7o62pno */
    public boolean mo4748requestOwnerFocus7o62pno(FocusDirection focusDirection, Rect previouslyFocusedRect) {
        return this.platformFocusOwner.mo4794requestOwnerFocus7o62pno(focusDirection, previouslyFocusedRect);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: takeFocus-aToIllA */
    public boolean mo4750takeFocusaToIllA(final int focusDirection, Rect previouslyFocusedRect) {
        Boolean boolMo4746focusSearchULY8qGw = mo4746focusSearchULY8qGw(focusDirection, previouslyFocusedRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$takeFocus$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection));
            }
        });
        if (boolMo4746focusSearchULY8qGw != null) {
            return boolMo4746focusSearchULY8qGw.booleanValue();
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void releaseFocus() {
        FocusTransactionsKt.clearFocus(this.rootFocusNode, true, true);
        if (ComposeUiFlags.isOptimizedFocusEventDispatchEnabled && getActiveFocusTargetNode() != null) {
            FocusTargetNode previousActive = getActiveFocusTargetNode();
            setActiveFocusTargetNode(null);
            if (previousActive != null) {
                previousActive.dispatchFocusCallbacks$ui(FocusStateImpl.Active, FocusStateImpl.Inactive);
            }
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void clearOwnerFocus() {
        this.platformFocusOwner.clearOwnerFocus();
    }

    @Override // androidx.compose.ui.focus.FocusManager
    public void clearFocus(boolean force) {
        mo4743clearFocusI7lrPNg(force, true, true, FocusDirection.INSTANCE.m4733getExitdhqQ8s());
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: clearFocus-I7lrPNg */
    public boolean mo4743clearFocusI7lrPNg(boolean force, boolean refreshFocusEvents, boolean clearOwnerFocus, int focusDirection) {
        boolean clearedFocusSuccessfully;
        if (!force) {
            switch (WhenMappings.$EnumSwitchMapping$0[FocusTransactionsKt.m4770performCustomClearFocusMxy_nc0(this.rootFocusNode, focusDirection).ordinal()]) {
                case 1:
                case 2:
                case 3:
                    clearedFocusSuccessfully = false;
                    break;
                case 4:
                    clearedFocusSuccessfully = clearFocus(force, refreshFocusEvents);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else {
            clearedFocusSuccessfully = clearFocus(force, refreshFocusEvents);
        }
        if (clearedFocusSuccessfully && clearOwnerFocus) {
            clearOwnerFocus();
        }
        return clearedFocusSuccessfully;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: resetFocus-3ESFkO8 */
    public boolean mo4749resetFocus3ESFkO8(final int focusDirection) {
        boolean successfulClear = mo4743clearFocusI7lrPNg(false, true, false, focusDirection);
        if (!successfulClear) {
            return false;
        }
        Boolean boolMo4746focusSearchULY8qGw = mo4746focusSearchULY8qGw(focusDirection, null, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$resetFocus$successfulReset$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                return Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection));
            }
        });
        boolean successfulReset = boolMo4746focusSearchULY8qGw != null ? boolMo4746focusSearchULY8qGw.booleanValue() : false;
        if (!successfulReset) {
            clearOwnerFocus();
        }
        return successfulReset;
    }

    static /* synthetic */ boolean clearFocus$default(FocusOwnerImpl focusOwnerImpl, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return focusOwnerImpl.clearFocus(z, z2);
    }

    private final boolean clearFocus(boolean forced, boolean refreshFocusEvents) {
        FocusTargetNode previousActiveFocusTargetNode;
        Object obj;
        NodeChain nodes;
        FocusTargetNode previousActiveFocusTargetNode2;
        FocusTargetNode previousActiveFocusTargetNode3;
        Object node$iv$iv;
        DelegatingNode this_$iv$iv$iv;
        if (getActiveFocusTargetNode() == null) {
            return true;
        }
        Object obj2 = null;
        if (getIsFocusCaptured() && !forced) {
            return false;
        }
        FocusTargetNode previousActiveFocusTargetNode4 = getActiveFocusTargetNode();
        setActiveFocusTargetNode(null);
        if (!refreshFocusEvents || previousActiveFocusTargetNode4 == null) {
            return true;
        }
        previousActiveFocusTargetNode4.dispatchFocusCallbacks$ui(getIsFocusCaptured() ? FocusStateImpl.Captured : FocusStateImpl.Active, FocusStateImpl.Inactive);
        FocusTargetNode $this$visitAncestors_u2dY_u2dYKmho_u24default$iv = previousActiveFocusTargetNode4;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv2 = $this$visitAncestors_u2dY_u2dYKmho_u24default$iv.getNode().getParent();
        LayoutNode layout$iv$iv = DelegatableNodeKt.requireLayoutNode($this$visitAncestors_u2dY_u2dYKmho_u24default$iv);
        while (layout$iv$iv != null) {
            Modifier.Node head$iv$iv = layout$iv$iv.getNodes().getHead();
            if ((head$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                while (node$iv$iv2 != null) {
                    if ((node$iv$iv2.getKindSet() & iM6860constructorimpl) != 0) {
                        Modifier.Node it$iv = node$iv$iv2;
                        MutableVector mutableVector = null;
                        Modifier.Node this_$iv$iv$iv2 = it$iv;
                        while (this_$iv$iv$iv2 != null) {
                            if (this_$iv$iv$iv2 instanceof FocusTargetNode) {
                                FocusTargetNode it = (FocusTargetNode) this_$iv$iv$iv2;
                                previousActiveFocusTargetNode3 = previousActiveFocusTargetNode4;
                                it.dispatchFocusCallbacks$ui(FocusStateImpl.ActiveParent, FocusStateImpl.Inactive);
                                node$iv$iv = null;
                            } else {
                                previousActiveFocusTargetNode3 = previousActiveFocusTargetNode4;
                                if (((this_$iv$iv$iv2.getKindSet() & iM6860constructorimpl) != 0 ? 1 : 0) == 0 || !(this_$iv$iv$iv2 instanceof DelegatingNode)) {
                                    node$iv$iv = null;
                                } else {
                                    int count$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                                    int $i$f$forEachImmediateDelegate$ui = 0;
                                    Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                                    while (node$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv = node$iv$iv$iv;
                                        if (((next$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv++;
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            if (count$iv$iv == 1) {
                                                $i$f$forEachImmediateDelegate$ui = $i$f$forEachImmediateDelegate$ui;
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
                                                    mutableVector2.add(next$iv$iv);
                                                }
                                                mutableVector = mutableVector2;
                                                count$iv$iv = count$iv$iv;
                                            }
                                        } else {
                                            this_$iv$iv$iv = this_$iv$iv$iv3;
                                            $i$f$forEachImmediateDelegate$ui = $i$f$forEachImmediateDelegate$ui;
                                            this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                        }
                                        node$iv$iv$iv = node$iv$iv$iv.getChild();
                                        this_$iv$iv$iv3 = this_$iv$iv$iv;
                                        $i$f$forEachImmediateDelegate$ui = $i$f$forEachImmediateDelegate$ui;
                                        this_$iv$iv$iv2 = this_$iv$iv$iv2;
                                    }
                                    Modifier.Node node = this_$iv$iv$iv2;
                                    node$iv$iv = null;
                                    if (count$iv$iv == 1) {
                                        obj2 = null;
                                        previousActiveFocusTargetNode4 = previousActiveFocusTargetNode3;
                                        this_$iv$iv$iv2 = node;
                                    }
                                }
                            }
                            obj2 = node$iv$iv;
                            this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector);
                            previousActiveFocusTargetNode4 = previousActiveFocusTargetNode3;
                        }
                        previousActiveFocusTargetNode2 = previousActiveFocusTargetNode4;
                    } else {
                        previousActiveFocusTargetNode2 = previousActiveFocusTargetNode4;
                    }
                    node$iv$iv2 = node$iv$iv2.getParent();
                    obj2 = obj2;
                    previousActiveFocusTargetNode4 = previousActiveFocusTargetNode2;
                }
                previousActiveFocusTargetNode = previousActiveFocusTargetNode4;
                obj = obj2;
            } else {
                previousActiveFocusTargetNode = previousActiveFocusTargetNode4;
                obj = obj2;
            }
            layout$iv$iv = layout$iv$iv.getParent$ui();
            node$iv$iv2 = (layout$iv$iv == null || (nodes = layout$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            obj2 = obj;
            previousActiveFocusTargetNode4 = previousActiveFocusTargetNode;
        }
        return true;
    }

    @Override // androidx.compose.ui.focus.FocusManager
    /* JADX INFO: renamed from: moveFocus-3ESFkO8 */
    public boolean mo4741moveFocus3ESFkO8(int focusDirection) {
        return mo4747moveFocusaToIllA(focusDirection, true);
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001c  */
    /* JADX WARN: Code duplicated, block: B:15:0x0024 A[RETURN] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.lang.Boolean] */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: moveFocus-aToIllA */
    public boolean mo4747moveFocusaToIllA(final int focusDirection, boolean wrapAroundForOneDimensionalFocus) {
        if (!ComposeUiFlags.isViewFocusFixEnabled) {
            if (ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
                FocusTargetNode activeFocusTargetNode = getActiveFocusTargetNode();
                if (activeFocusTargetNode != null && activeFocusTargetNode.getIsInteropViewHost()) {
                    if (this.platformFocusOwner.mo4793moveFocusInChildren3ESFkO8(focusDirection)) {
                        return true;
                    }
                }
            }
        } else if (this.platformFocusOwner.mo4793moveFocusInChildren3ESFkO8(focusDirection)) {
            return true;
        }
        final Ref.ObjectRef requestFocusSuccess = new Ref.ObjectRef();
        requestFocusSuccess.element = false;
        FocusTargetNode activeNodeBefore = getActiveFocusTargetNode();
        Boolean focusSearchSuccess = mo4746focusSearchULY8qGw(focusDirection, this.platformFocusOwner.getEmbeddedViewFocusRect(), new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$moveFocus$focusSearchSuccess$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.Boolean] */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                requestFocusSuccess.element = Boolean.valueOf(it.mo4761requestFocus3ESFkO8(focusDirection));
                return requestFocusSuccess.element;
            }
        });
        if (Intrinsics.areEqual((Object) focusSearchSuccess, (Object) true) && activeNodeBefore != getActiveFocusTargetNode()) {
            return true;
        }
        if (focusSearchSuccess == null || requestFocusSuccess.element == 0) {
            return false;
        }
        if (focusSearchSuccess.booleanValue() && ((Boolean) requestFocusSuccess.element).booleanValue()) {
            return true;
        }
        if (FocusOwnerImplKt.m4754is1dFocusSearch3ESFkO8(focusDirection) && wrapAroundForOneDimensionalFocus) {
            boolean clearFocus = mo4743clearFocusI7lrPNg(false, true, false, focusDirection);
            return clearFocus && mo4750takeFocusaToIllA(focusDirection, null);
        }
        if (ComposeUiFlags.isViewFocusFixEnabled || ComposeUiFlags.isBypassUnfocusableComposeViewEnabled) {
            return false;
        }
        return this.platformFocusOwner.mo4793moveFocusInChildren3ESFkO8(focusDirection);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: focusSearch-ULY8qGw */
    public Boolean mo4746focusSearchULY8qGw(int focusDirection, Rect focusedRect, final Function1<? super FocusTargetNode, Boolean> onFound) {
        boolean z;
        FocusTargetNode focusTargetNode;
        int i;
        Modifier.Node node;
        final FocusTargetNode focusTargetNodeFindFocusTargetNode = findFocusTargetNode();
        if (focusTargetNodeFindFocusTargetNode != null) {
            FocusTargetNode focusTargetNode2 = focusTargetNodeFindFocusTargetNode;
            FocusRequester focusRequesterM4776customFocusSearchOMvw8 = FocusTraversalKt.m4776customFocusSearchOMvw8(focusTargetNode2, focusDirection, this.owner.getLayoutDirection());
            if (Intrinsics.areEqual(focusRequesterM4776customFocusSearchOMvw8, FocusRequester.INSTANCE.getCancel())) {
                return null;
            }
            if (Intrinsics.areEqual(focusRequesterM4776customFocusSearchOMvw8, FocusRequester.INSTANCE.getRedirect$ui())) {
                FocusTargetNode focusTargetNodeFindFocusTargetNode2 = findFocusTargetNode();
                if (focusTargetNodeFindFocusTargetNode2 != null) {
                    return onFound.invoke(focusTargetNodeFindFocusTargetNode2);
                }
                return null;
            }
            if (!Intrinsics.areEqual(focusRequesterM4776customFocusSearchOMvw8, FocusRequester.INSTANCE.getDefault())) {
                if (!ComposeUiFlags.isRequestFocusOnNonFocusableFocusTargetEnabled) {
                    return Boolean.valueOf(focusRequesterM4776customFocusSearchOMvw8.findFocusTargetNode$ui(onFound));
                }
                FocusRequester focusRequester = focusRequesterM4776customFocusSearchOMvw8;
                int i2 = 0;
                int i3 = 0;
                boolean z2 = false;
                if (!(focusRequester != FocusRequester.INSTANCE.getDefault())) {
                    throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n".toString());
                }
                if (!(focusRequester != FocusRequester.INSTANCE.getCancel())) {
                    throw new IllegalStateException("\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n".toString());
                }
                if (focusRequester.getFocusRequesterNodes$ui().getSize() == 0) {
                    System.out.println((Object) "FocusRelatedWarning: \n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n");
                } else {
                    boolean z3 = false;
                    MutableVector<FocusRequesterModifierNode> focusRequesterNodes$ui = focusRequester.getFocusRequesterNodes$ui();
                    int i4 = 0;
                    FocusRequesterModifierNode[] focusRequesterModifierNodeArr = focusRequesterNodes$ui.content;
                    int size = focusRequesterNodes$ui.getSize();
                    while (i4 < size) {
                        FocusRequesterModifierNode focusRequesterModifierNode = focusRequesterModifierNodeArr[i4];
                        int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
                        if (!focusRequesterModifierNode.getNode().getIsAttached()) {
                            InlineClassHelperKt.throwIllegalStateException("visitChildren called on an unattached node");
                        }
                        int i5 = 1;
                        FocusRequester focusRequester2 = focusRequester;
                        int i6 = i2;
                        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], i3);
                        Modifier.Node child = focusRequesterModifierNode.getNode().getChild();
                        if (child == null) {
                            z = false;
                            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusRequesterModifierNode.getNode(), false);
                        } else {
                            z = false;
                            mutableVector.add(child);
                        }
                        while (true) {
                            if ((mutableVector.getSize() != 0 ? i5 : 0) == 0) {
                                break;
                            }
                            Modifier.Node node2 = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
                            if ((node2.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                                Modifier.Node child2 = node2;
                                while (true) {
                                    if (child2 == null) {
                                        mutableVector = mutableVector;
                                        break;
                                    }
                                    if ((child2.getKindSet() & iM6860constructorimpl) != 0) {
                                        MutableVector mutableVector2 = null;
                                        MutableVector mutableVector3 = mutableVector;
                                        Modifier.Node nodePop = child2;
                                        while (nodePop != null) {
                                            child = child;
                                            if (!(nodePop instanceof FocusTargetNode)) {
                                                if (((nodePop.getKindSet() & iM6860constructorimpl) != 0 ? i5 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                                    focusTargetNode = focusTargetNode2;
                                                    i = i5;
                                                } else {
                                                    int i7 = 0;
                                                    Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                                    while (delegate != null) {
                                                        Modifier.Node node3 = delegate;
                                                        if (((node3.getKindSet() & iM6860constructorimpl) != 0 ? i5 : 0) != 0) {
                                                            i7++;
                                                            Modifier.Node node4 = nodePop;
                                                            if (i7 == i5) {
                                                                node = node3;
                                                                focusTargetNode2 = focusTargetNode2;
                                                            } else {
                                                                MutableVector mutableVector4 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                                if (node4 != null) {
                                                                    if (mutableVector4 != null) {
                                                                        mutableVector4.add(node4);
                                                                    }
                                                                    node = null;
                                                                } else {
                                                                    node = node4;
                                                                }
                                                                if (mutableVector4 != null) {
                                                                    mutableVector4.add(node3);
                                                                }
                                                                mutableVector2 = mutableVector4;
                                                                i7 = i7;
                                                            }
                                                        } else {
                                                            focusTargetNode2 = focusTargetNode2;
                                                            node = nodePop;
                                                        }
                                                        delegate = delegate.getChild();
                                                        nodePop = node;
                                                        focusTargetNode2 = focusTargetNode2;
                                                        i5 = 1;
                                                    }
                                                    Modifier.Node node5 = nodePop;
                                                    focusTargetNode = focusTargetNode2;
                                                    i = 1;
                                                    if (i7 == 1) {
                                                        i5 = 1;
                                                        nodePop = node5;
                                                        focusTargetNode2 = focusTargetNode;
                                                    }
                                                }
                                            } else {
                                                if (onFound.invoke((FocusTargetNode) nodePop).booleanValue()) {
                                                    z3 = true;
                                                    break;
                                                }
                                                focusTargetNode = focusTargetNode2;
                                                i = i5;
                                            }
                                            i5 = i;
                                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                                            focusTargetNode2 = focusTargetNode;
                                        }
                                        mutableVector = mutableVector3;
                                        break;
                                    }
                                    child2 = child2.getChild();
                                    mutableVector = mutableVector;
                                }
                            } else {
                                DelegatableNodeKt.addLayoutNodeChildren(mutableVector, node2, z);
                            }
                        }
                        i4++;
                        focusRequester = focusRequester2;
                        i2 = i6;
                        focusTargetNode2 = focusTargetNode2;
                        i3 = 0;
                    }
                    z2 = z3;
                }
                return Boolean.valueOf(z2);
            }
        } else {
            focusTargetNodeFindFocusTargetNode = null;
        }
        return FocusTraversalKt.m4777focusSearch0X8WOeE(this.rootFocusNode, focusDirection, this.owner.getLayoutDirection(), focusedRect, new Function1<FocusTargetNode, Boolean>() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$focusSearch$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(FocusTargetNode it) {
                boolean zBooleanValue;
                if (Intrinsics.areEqual(it, focusTargetNodeFindFocusTargetNode)) {
                    zBooleanValue = false;
                } else {
                    if (Intrinsics.areEqual(it, this.getRootFocusNode())) {
                        throw new IllegalStateException("Focus search landed at the root.".toString());
                    }
                    zBooleanValue = onFound.invoke(it).booleanValue();
                }
                return Boolean.valueOf(zBooleanValue);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:254:0x0546 A[Catch: all -> 0x073c, TryCatch #1 {all -> 0x073c, blocks: (B:171:0x0367, B:173:0x0393, B:174:0x0399, B:176:0x03a8, B:179:0x03ba, B:183:0x03da, B:187:0x03e5, B:188:0x03ee, B:227:0x04b7, B:189:0x03f6, B:194:0x0409, B:196:0x040d, B:198:0x041a, B:203:0x0433, B:205:0x043a, B:221:0x0496, B:207:0x0448, B:212:0x046c, B:216:0x0479, B:230:0x04ce, B:233:0x04e8, B:235:0x04f0, B:237:0x04f6, B:242:0x0510, B:244:0x051d, B:251:0x0538, B:252:0x053a, B:254:0x0546, B:256:0x054a, B:295:0x0606, B:260:0x055d, B:265:0x056c, B:267:0x0570, B:269:0x057b, B:274:0x0593, B:276:0x059a, B:290:0x05e0, B:278:0x05a5, B:283:0x05c7, B:286:0x05d1, B:296:0x0613, B:299:0x0629, B:301:0x0636, B:303:0x063a, B:342:0x06f7, B:307:0x064e, B:312:0x065d, B:314:0x0661, B:316:0x066c, B:321:0x0684, B:323:0x068b, B:337:0x06d1, B:325:0x0696, B:330:0x06b8, B:333:0x06c2, B:345:0x070d, B:347:0x0719, B:350:0x0729, B:351:0x072f, B:352:0x0732, B:95:0x01d0, B:97:0x01d4, B:99:0x01e0, B:101:0x0201, B:102:0x0207, B:104:0x0216, B:107:0x0228, B:111:0x0248, B:165:0x0359, B:167:0x035d, B:114:0x0251, B:119:0x0264, B:121:0x0268, B:123:0x0275, B:128:0x028e, B:130:0x0295, B:145:0x02eb, B:132:0x02a2, B:137:0x02c6, B:140:0x02d2, B:151:0x030b, B:154:0x031e, B:157:0x0335, B:159:0x033d, B:161:0x0343, B:76:0x0150, B:63:0x0121, B:68:0x0131, B:71:0x013b, B:81:0x0172, B:84:0x018b, B:87:0x01a8, B:89:0x01b0, B:91:0x01b6), top: B:362:0x0150 }] */
    /* JADX WARN: Code duplicated, block: B:259:0x0555  */
    /* JADX WARN: Code duplicated, block: B:260:0x055d A[Catch: all -> 0x073c, TryCatch #1 {all -> 0x073c, blocks: (B:171:0x0367, B:173:0x0393, B:174:0x0399, B:176:0x03a8, B:179:0x03ba, B:183:0x03da, B:187:0x03e5, B:188:0x03ee, B:227:0x04b7, B:189:0x03f6, B:194:0x0409, B:196:0x040d, B:198:0x041a, B:203:0x0433, B:205:0x043a, B:221:0x0496, B:207:0x0448, B:212:0x046c, B:216:0x0479, B:230:0x04ce, B:233:0x04e8, B:235:0x04f0, B:237:0x04f6, B:242:0x0510, B:244:0x051d, B:251:0x0538, B:252:0x053a, B:254:0x0546, B:256:0x054a, B:295:0x0606, B:260:0x055d, B:265:0x056c, B:267:0x0570, B:269:0x057b, B:274:0x0593, B:276:0x059a, B:290:0x05e0, B:278:0x05a5, B:283:0x05c7, B:286:0x05d1, B:296:0x0613, B:299:0x0629, B:301:0x0636, B:303:0x063a, B:342:0x06f7, B:307:0x064e, B:312:0x065d, B:314:0x0661, B:316:0x066c, B:321:0x0684, B:323:0x068b, B:337:0x06d1, B:325:0x0696, B:330:0x06b8, B:333:0x06c2, B:345:0x070d, B:347:0x0719, B:350:0x0729, B:351:0x072f, B:352:0x0732, B:95:0x01d0, B:97:0x01d4, B:99:0x01e0, B:101:0x0201, B:102:0x0207, B:104:0x0216, B:107:0x0228, B:111:0x0248, B:165:0x0359, B:167:0x035d, B:114:0x0251, B:119:0x0264, B:121:0x0268, B:123:0x0275, B:128:0x028e, B:130:0x0295, B:145:0x02eb, B:132:0x02a2, B:137:0x02c6, B:140:0x02d2, B:151:0x030b, B:154:0x031e, B:157:0x0335, B:159:0x033d, B:161:0x0343, B:76:0x0150, B:63:0x0121, B:68:0x0131, B:71:0x013b, B:81:0x0172, B:84:0x018b, B:87:0x01a8, B:89:0x01b0, B:91:0x01b6), top: B:362:0x0150 }] */
    /* JADX WARN: Code duplicated, block: B:262:0x0567  */
    /* JADX WARN: Code duplicated, block: B:263:0x0569  */
    /* JADX WARN: Code duplicated, block: B:298:0x0627  */
    /* JADX WARN: Code duplicated, block: B:299:0x0629 A[Catch: all -> 0x073c, TryCatch #1 {all -> 0x073c, blocks: (B:171:0x0367, B:173:0x0393, B:174:0x0399, B:176:0x03a8, B:179:0x03ba, B:183:0x03da, B:187:0x03e5, B:188:0x03ee, B:227:0x04b7, B:189:0x03f6, B:194:0x0409, B:196:0x040d, B:198:0x041a, B:203:0x0433, B:205:0x043a, B:221:0x0496, B:207:0x0448, B:212:0x046c, B:216:0x0479, B:230:0x04ce, B:233:0x04e8, B:235:0x04f0, B:237:0x04f6, B:242:0x0510, B:244:0x051d, B:251:0x0538, B:252:0x053a, B:254:0x0546, B:256:0x054a, B:295:0x0606, B:260:0x055d, B:265:0x056c, B:267:0x0570, B:269:0x057b, B:274:0x0593, B:276:0x059a, B:290:0x05e0, B:278:0x05a5, B:283:0x05c7, B:286:0x05d1, B:296:0x0613, B:299:0x0629, B:301:0x0636, B:303:0x063a, B:342:0x06f7, B:307:0x064e, B:312:0x065d, B:314:0x0661, B:316:0x066c, B:321:0x0684, B:323:0x068b, B:337:0x06d1, B:325:0x0696, B:330:0x06b8, B:333:0x06c2, B:345:0x070d, B:347:0x0719, B:350:0x0729, B:351:0x072f, B:352:0x0732, B:95:0x01d0, B:97:0x01d4, B:99:0x01e0, B:101:0x0201, B:102:0x0207, B:104:0x0216, B:107:0x0228, B:111:0x0248, B:165:0x0359, B:167:0x035d, B:114:0x0251, B:119:0x0264, B:121:0x0268, B:123:0x0275, B:128:0x028e, B:130:0x0295, B:145:0x02eb, B:132:0x02a2, B:137:0x02c6, B:140:0x02d2, B:151:0x030b, B:154:0x031e, B:157:0x0335, B:159:0x033d, B:161:0x0343, B:76:0x0150, B:63:0x0121, B:68:0x0131, B:71:0x013b, B:81:0x0172, B:84:0x018b, B:87:0x01a8, B:89:0x01b0, B:91:0x01b6), top: B:362:0x0150 }] */
    /* JADX WARN: Code duplicated, block: B:301:0x0636 A[Catch: all -> 0x073c, TryCatch #1 {all -> 0x073c, blocks: (B:171:0x0367, B:173:0x0393, B:174:0x0399, B:176:0x03a8, B:179:0x03ba, B:183:0x03da, B:187:0x03e5, B:188:0x03ee, B:227:0x04b7, B:189:0x03f6, B:194:0x0409, B:196:0x040d, B:198:0x041a, B:203:0x0433, B:205:0x043a, B:221:0x0496, B:207:0x0448, B:212:0x046c, B:216:0x0479, B:230:0x04ce, B:233:0x04e8, B:235:0x04f0, B:237:0x04f6, B:242:0x0510, B:244:0x051d, B:251:0x0538, B:252:0x053a, B:254:0x0546, B:256:0x054a, B:295:0x0606, B:260:0x055d, B:265:0x056c, B:267:0x0570, B:269:0x057b, B:274:0x0593, B:276:0x059a, B:290:0x05e0, B:278:0x05a5, B:283:0x05c7, B:286:0x05d1, B:296:0x0613, B:299:0x0629, B:301:0x0636, B:303:0x063a, B:342:0x06f7, B:307:0x064e, B:312:0x065d, B:314:0x0661, B:316:0x066c, B:321:0x0684, B:323:0x068b, B:337:0x06d1, B:325:0x0696, B:330:0x06b8, B:333:0x06c2, B:345:0x070d, B:347:0x0719, B:350:0x0729, B:351:0x072f, B:352:0x0732, B:95:0x01d0, B:97:0x01d4, B:99:0x01e0, B:101:0x0201, B:102:0x0207, B:104:0x0216, B:107:0x0228, B:111:0x0248, B:165:0x0359, B:167:0x035d, B:114:0x0251, B:119:0x0264, B:121:0x0268, B:123:0x0275, B:128:0x028e, B:130:0x0295, B:145:0x02eb, B:132:0x02a2, B:137:0x02c6, B:140:0x02d2, B:151:0x030b, B:154:0x031e, B:157:0x0335, B:159:0x033d, B:161:0x0343, B:76:0x0150, B:63:0x0121, B:68:0x0131, B:71:0x013b, B:81:0x0172, B:84:0x018b, B:87:0x01a8, B:89:0x01b0, B:91:0x01b6), top: B:362:0x0150 }] */
    /* JADX WARN: Code duplicated, block: B:306:0x0646  */
    /* JADX WARN: Code duplicated, block: B:307:0x064e A[Catch: all -> 0x073c, TryCatch #1 {all -> 0x073c, blocks: (B:171:0x0367, B:173:0x0393, B:174:0x0399, B:176:0x03a8, B:179:0x03ba, B:183:0x03da, B:187:0x03e5, B:188:0x03ee, B:227:0x04b7, B:189:0x03f6, B:194:0x0409, B:196:0x040d, B:198:0x041a, B:203:0x0433, B:205:0x043a, B:221:0x0496, B:207:0x0448, B:212:0x046c, B:216:0x0479, B:230:0x04ce, B:233:0x04e8, B:235:0x04f0, B:237:0x04f6, B:242:0x0510, B:244:0x051d, B:251:0x0538, B:252:0x053a, B:254:0x0546, B:256:0x054a, B:295:0x0606, B:260:0x055d, B:265:0x056c, B:267:0x0570, B:269:0x057b, B:274:0x0593, B:276:0x059a, B:290:0x05e0, B:278:0x05a5, B:283:0x05c7, B:286:0x05d1, B:296:0x0613, B:299:0x0629, B:301:0x0636, B:303:0x063a, B:342:0x06f7, B:307:0x064e, B:312:0x065d, B:314:0x0661, B:316:0x066c, B:321:0x0684, B:323:0x068b, B:337:0x06d1, B:325:0x0696, B:330:0x06b8, B:333:0x06c2, B:345:0x070d, B:347:0x0719, B:350:0x0729, B:351:0x072f, B:352:0x0732, B:95:0x01d0, B:97:0x01d4, B:99:0x01e0, B:101:0x0201, B:102:0x0207, B:104:0x0216, B:107:0x0228, B:111:0x0248, B:165:0x0359, B:167:0x035d, B:114:0x0251, B:119:0x0264, B:121:0x0268, B:123:0x0275, B:128:0x028e, B:130:0x0295, B:145:0x02eb, B:132:0x02a2, B:137:0x02c6, B:140:0x02d2, B:151:0x030b, B:154:0x031e, B:157:0x0335, B:159:0x033d, B:161:0x0343, B:76:0x0150, B:63:0x0121, B:68:0x0131, B:71:0x013b, B:81:0x0172, B:84:0x018b, B:87:0x01a8, B:89:0x01b0, B:91:0x01b6), top: B:362:0x0150 }] */
    /* JADX WARN: Code duplicated, block: B:309:0x0658  */
    /* JADX WARN: Code duplicated, block: B:310:0x065a  */
    /* JADX WARN: Code duplicated, block: B:345:0x070d A[Catch: all -> 0x073c, TryCatch #1 {all -> 0x073c, blocks: (B:171:0x0367, B:173:0x0393, B:174:0x0399, B:176:0x03a8, B:179:0x03ba, B:183:0x03da, B:187:0x03e5, B:188:0x03ee, B:227:0x04b7, B:189:0x03f6, B:194:0x0409, B:196:0x040d, B:198:0x041a, B:203:0x0433, B:205:0x043a, B:221:0x0496, B:207:0x0448, B:212:0x046c, B:216:0x0479, B:230:0x04ce, B:233:0x04e8, B:235:0x04f0, B:237:0x04f6, B:242:0x0510, B:244:0x051d, B:251:0x0538, B:252:0x053a, B:254:0x0546, B:256:0x054a, B:295:0x0606, B:260:0x055d, B:265:0x056c, B:267:0x0570, B:269:0x057b, B:274:0x0593, B:276:0x059a, B:290:0x05e0, B:278:0x05a5, B:283:0x05c7, B:286:0x05d1, B:296:0x0613, B:299:0x0629, B:301:0x0636, B:303:0x063a, B:342:0x06f7, B:307:0x064e, B:312:0x065d, B:314:0x0661, B:316:0x066c, B:321:0x0684, B:323:0x068b, B:337:0x06d1, B:325:0x0696, B:330:0x06b8, B:333:0x06c2, B:345:0x070d, B:347:0x0719, B:350:0x0729, B:351:0x072f, B:352:0x0732, B:95:0x01d0, B:97:0x01d4, B:99:0x01e0, B:101:0x0201, B:102:0x0207, B:104:0x0216, B:107:0x0228, B:111:0x0248, B:165:0x0359, B:167:0x035d, B:114:0x0251, B:119:0x0264, B:121:0x0268, B:123:0x0275, B:128:0x028e, B:130:0x0295, B:145:0x02eb, B:132:0x02a2, B:137:0x02c6, B:140:0x02d2, B:151:0x030b, B:154:0x031e, B:157:0x0335, B:159:0x033d, B:161:0x0343, B:76:0x0150, B:63:0x0121, B:68:0x0131, B:71:0x013b, B:81:0x0172, B:84:0x018b, B:87:0x01a8, B:89:0x01b0, B:91:0x01b6), top: B:362:0x0150 }] */
    /* JADX WARN: Code duplicated, block: B:347:0x0719 A[Catch: all -> 0x073c, TryCatch #1 {all -> 0x073c, blocks: (B:171:0x0367, B:173:0x0393, B:174:0x0399, B:176:0x03a8, B:179:0x03ba, B:183:0x03da, B:187:0x03e5, B:188:0x03ee, B:227:0x04b7, B:189:0x03f6, B:194:0x0409, B:196:0x040d, B:198:0x041a, B:203:0x0433, B:205:0x043a, B:221:0x0496, B:207:0x0448, B:212:0x046c, B:216:0x0479, B:230:0x04ce, B:233:0x04e8, B:235:0x04f0, B:237:0x04f6, B:242:0x0510, B:244:0x051d, B:251:0x0538, B:252:0x053a, B:254:0x0546, B:256:0x054a, B:295:0x0606, B:260:0x055d, B:265:0x056c, B:267:0x0570, B:269:0x057b, B:274:0x0593, B:276:0x059a, B:290:0x05e0, B:278:0x05a5, B:283:0x05c7, B:286:0x05d1, B:296:0x0613, B:299:0x0629, B:301:0x0636, B:303:0x063a, B:342:0x06f7, B:307:0x064e, B:312:0x065d, B:314:0x0661, B:316:0x066c, B:321:0x0684, B:323:0x068b, B:337:0x06d1, B:325:0x0696, B:330:0x06b8, B:333:0x06c2, B:345:0x070d, B:347:0x0719, B:350:0x0729, B:351:0x072f, B:352:0x0732, B:95:0x01d0, B:97:0x01d4, B:99:0x01e0, B:101:0x0201, B:102:0x0207, B:104:0x0216, B:107:0x0228, B:111:0x0248, B:165:0x0359, B:167:0x035d, B:114:0x0251, B:119:0x0264, B:121:0x0268, B:123:0x0275, B:128:0x028e, B:130:0x0295, B:145:0x02eb, B:132:0x02a2, B:137:0x02c6, B:140:0x02d2, B:151:0x030b, B:154:0x031e, B:157:0x0335, B:159:0x033d, B:161:0x0343, B:76:0x0150, B:63:0x0121, B:68:0x0131, B:71:0x013b, B:81:0x0172, B:84:0x018b, B:87:0x01a8, B:89:0x01b0, B:91:0x01b6), top: B:362:0x0150 }] */
    /* JADX WARN: Code duplicated, block: B:350:0x0729 A[Catch: all -> 0x073c, LOOP:9: B:346:0x0717->B:350:0x0729, LOOP_END, TryCatch #1 {all -> 0x073c, blocks: (B:171:0x0367, B:173:0x0393, B:174:0x0399, B:176:0x03a8, B:179:0x03ba, B:183:0x03da, B:187:0x03e5, B:188:0x03ee, B:227:0x04b7, B:189:0x03f6, B:194:0x0409, B:196:0x040d, B:198:0x041a, B:203:0x0433, B:205:0x043a, B:221:0x0496, B:207:0x0448, B:212:0x046c, B:216:0x0479, B:230:0x04ce, B:233:0x04e8, B:235:0x04f0, B:237:0x04f6, B:242:0x0510, B:244:0x051d, B:251:0x0538, B:252:0x053a, B:254:0x0546, B:256:0x054a, B:295:0x0606, B:260:0x055d, B:265:0x056c, B:267:0x0570, B:269:0x057b, B:274:0x0593, B:276:0x059a, B:290:0x05e0, B:278:0x05a5, B:283:0x05c7, B:286:0x05d1, B:296:0x0613, B:299:0x0629, B:301:0x0636, B:303:0x063a, B:342:0x06f7, B:307:0x064e, B:312:0x065d, B:314:0x0661, B:316:0x066c, B:321:0x0684, B:323:0x068b, B:337:0x06d1, B:325:0x0696, B:330:0x06b8, B:333:0x06c2, B:345:0x070d, B:347:0x0719, B:350:0x0729, B:351:0x072f, B:352:0x0732, B:95:0x01d0, B:97:0x01d4, B:99:0x01e0, B:101:0x0201, B:102:0x0207, B:104:0x0216, B:107:0x0228, B:111:0x0248, B:165:0x0359, B:167:0x035d, B:114:0x0251, B:119:0x0264, B:121:0x0268, B:123:0x0275, B:128:0x028e, B:130:0x0295, B:145:0x02eb, B:132:0x02a2, B:137:0x02c6, B:140:0x02d2, B:151:0x030b, B:154:0x031e, B:157:0x0335, B:159:0x033d, B:161:0x0343, B:76:0x0150, B:63:0x0121, B:68:0x0131, B:71:0x013b, B:81:0x0172, B:84:0x018b, B:87:0x01a8, B:89:0x01b0, B:91:0x01b6), top: B:362:0x0150 }] */
    /* JADX WARN: Code duplicated, block: B:387:0x0554 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:393:0x054a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:402:0x0644 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:404:0x063a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:414:0x0727 A[SYNTHETIC] */
    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchKeyEvent-YhN2O0w */
    public boolean mo4745dispatchKeyEventYhN2O0w(KeyEvent keyEvent, Function0<Boolean> onFocusedItem) throws Throwable {
        int i;
        Object obj;
        Modifier.Node focusedKeyInputNode;
        DelegatableNode $this$nearestAncestor_u2d64DMado$iv;
        int i2;
        NodeChain nodes;
        int i3;
        int i4;
        Object obj2;
        int i5;
        String sectionName$iv;
        int i6;
        NodeChain nodes2;
        int i7;
        String sectionName$iv2;
        int i8;
        String sectionName$iv3;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        int i9;
        int i10;
        MutableVector mutableVector;
        Modifier.Node nodePop;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
        int i11;
        int i12;
        MutableVector mutableVector2;
        Modifier.Node nodePop2;
        List $this$fastForEach$iv$iv;
        int index$iv$iv;
        int size;
        KeyInputModifierNode it;
        KeyInputModifierNode it2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
        int i13;
        int i14;
        Modifier.Node this_$iv$iv$iv;
        int i15;
        KeyInputModifierNode it3;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
        int i16;
        int i17;
        Modifier.Node this_$iv$iv$iv2;
        int i18;
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
        FocusOwnerImpl this_$iv;
        int i19;
        NodeChain nodes3;
        FocusOwnerImpl this_$iv2;
        int i20;
        int i21;
        Modifier.Node node;
        String sectionName$iv4 = "FocusOwnerImpl:dispatchKeyEvent";
        Trace.beginSection("FocusOwnerImpl:dispatchKeyEvent");
        int i22 = 0;
        try {
            int i23 = 0;
            try {
                if (!this.focusInvalidationManager.getIsInvalidationScheduled()) {
                    if (m4753validateKeyEventZmokQxo(keyEvent)) {
                        DelegatableNode activeFocusTarget = findFocusTargetNode();
                        int i24 = 8192;
                        int i25 = 1;
                        if (activeFocusTarget == null || (focusedKeyInputNode = lastLocalKeyInputNode(activeFocusTarget)) == null) {
                            if (activeFocusTarget != null) {
                                DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv = activeFocusTarget;
                                int iM6860constructorimpl = NodeKind.m6860constructorimpl(8192);
                                boolean value$iv$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv.getNode().getIsAttached();
                                if (!value$iv$iv$iv$iv) {
                                    InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
                                }
                                Modifier.Node node$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv.getNode();
                                LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestorIncludingSelf_u2d64DMado$iv);
                                loop10: while (true) {
                                    if (layout$iv$iv$iv == null) {
                                        i = i24;
                                        obj2 = null;
                                        break;
                                    }
                                    Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                                    if ((head$iv$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                                        while (node$iv$iv$iv != null) {
                                            if ((node$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                                                Modifier.Node it$iv$iv = node$iv$iv$iv;
                                                MutableVector mutableVector3 = null;
                                                i = i24;
                                                Modifier.Node nodePop3 = it$iv$iv;
                                                while (nodePop3 != null) {
                                                    if (nodePop3 instanceof KeyInputModifierNode) {
                                                        obj2 = nodePop3;
                                                        break loop10;
                                                    }
                                                    Modifier.Node this_$iv$iv$iv$iv = nodePop3;
                                                    if (((this_$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i25 : i23) == 0 || !(nodePop3 instanceof DelegatingNode)) {
                                                        i8 = i22;
                                                        sectionName$iv3 = sectionName$iv4;
                                                        nodePop3 = DelegatableNodeKt.pop(mutableVector3);
                                                        i22 = i8;
                                                        sectionName$iv4 = sectionName$iv3;
                                                        i23 = 0;
                                                        i25 = 1;
                                                    } else {
                                                        int count$iv$iv$iv = 0;
                                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop3;
                                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                                        while (node$iv$iv$iv$iv != null) {
                                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                                            if (((next$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i25 : i23) != 0) {
                                                                count$iv$iv$iv++;
                                                                if (count$iv$iv$iv == i25) {
                                                                    nodePop3 = next$iv$iv$iv;
                                                                    Unit unit = Unit.INSTANCE;
                                                                    i22 = i22;
                                                                    sectionName$iv4 = sectionName$iv4;
                                                                } else {
                                                                    MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                                    Modifier.Node theNode$iv$iv$iv = nodePop3;
                                                                    if (theNode$iv$iv$iv != null) {
                                                                        if (mutableVector4 != null) {
                                                                            Boolean.valueOf(mutableVector4.add(theNode$iv$iv$iv));
                                                                        }
                                                                        nodePop3 = null;
                                                                    }
                                                                    if (mutableVector4 != null) {
                                                                        Boolean.valueOf(mutableVector4.add(next$iv$iv$iv));
                                                                    }
                                                                    mutableVector3 = mutableVector4;
                                                                }
                                                            } else {
                                                                i22 = i22;
                                                                sectionName$iv4 = sectionName$iv4;
                                                            }
                                                            try {
                                                                node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                                                i22 = i22;
                                                                sectionName$iv4 = sectionName$iv4;
                                                                i23 = 0;
                                                                i25 = 1;
                                                            } catch (Throwable th) {
                                                                th = th;
                                                            }
                                                        }
                                                        i8 = i22;
                                                        sectionName$iv3 = sectionName$iv4;
                                                        if (count$iv$iv$iv == 1) {
                                                            i22 = i8;
                                                            sectionName$iv4 = sectionName$iv3;
                                                            i23 = 0;
                                                            i25 = 1;
                                                        } else {
                                                            nodePop3 = DelegatableNodeKt.pop(mutableVector3);
                                                            i22 = i8;
                                                            sectionName$iv4 = sectionName$iv3;
                                                            i23 = 0;
                                                            i25 = 1;
                                                        }
                                                    }
                                                    th = th;
                                                }
                                                i7 = i22;
                                                sectionName$iv2 = sectionName$iv4;
                                            } else {
                                                i7 = i22;
                                                sectionName$iv2 = sectionName$iv4;
                                                i = i24;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getParent();
                                            i24 = i;
                                            i22 = i7;
                                            sectionName$iv4 = sectionName$iv2;
                                            i23 = 0;
                                            i25 = 1;
                                        }
                                        i5 = i22;
                                        sectionName$iv = sectionName$iv4;
                                        i6 = i24;
                                    } else {
                                        i5 = i22;
                                        sectionName$iv = sectionName$iv4;
                                        i6 = i24;
                                    }
                                    layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                                    node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                                    i24 = i6;
                                    i22 = i5;
                                    sectionName$iv4 = sectionName$iv;
                                    i23 = 0;
                                    i25 = 1;
                                }
                                KeyInputModifierNode keyInputModifierNode = (KeyInputModifierNode) obj2;
                                if (keyInputModifierNode != null) {
                                    focusedKeyInputNode = keyInputModifierNode.getNode();
                                }
                            } else {
                                i = 8192;
                            }
                            DelegatableNode $this$nearestAncestor_u2d64DMado$iv2 = this.rootFocusNode;
                            int iM6860constructorimpl2 = NodeKind.m6860constructorimpl(i);
                            int i26 = 0;
                            boolean value$iv$iv$iv$iv2 = $this$nearestAncestor_u2d64DMado$iv2.getNode().getIsAttached();
                            if (!value$iv$iv$iv$iv2) {
                                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
                            }
                            Modifier.Node node$iv$iv$iv2 = $this$nearestAncestor_u2d64DMado$iv2.getNode().getParent();
                            LayoutNode layout$iv$iv$iv2 = DelegatableNodeKt.requireLayoutNode($this$nearestAncestor_u2d64DMado$iv2);
                            loop14: while (true) {
                                if (layout$iv$iv$iv2 == null) {
                                    obj = null;
                                    break;
                                }
                                Modifier.Node head$iv$iv$iv2 = layout$iv$iv$iv2.getNodes().getHead();
                                if ((head$iv$iv$iv2.getAggregateChildKindSet() & iM6860constructorimpl2) != 0) {
                                    while (node$iv$iv$iv2 != null) {
                                        if ((node$iv$iv$iv2.getKindSet() & iM6860constructorimpl2) != 0) {
                                            Modifier.Node it$iv$iv2 = node$iv$iv$iv2;
                                            MutableVector mutableVector5 = null;
                                            Modifier.Node nodePop4 = it$iv$iv2;
                                            while (nodePop4 != null) {
                                                if (nodePop4 instanceof KeyInputModifierNode) {
                                                    obj = nodePop4;
                                                    break loop14;
                                                }
                                                Modifier.Node this_$iv$iv$iv$iv3 = nodePop4;
                                                if (((this_$iv$iv$iv$iv3.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) == 0 || !(nodePop4 instanceof DelegatingNode)) {
                                                    i4 = i26;
                                                    nodePop4 = DelegatableNodeKt.pop(mutableVector5);
                                                    i26 = i4;
                                                } else {
                                                    int count$iv$iv$iv2 = 0;
                                                    DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop4;
                                                    Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                                    while (node$iv$iv$iv$iv2 != null) {
                                                        Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                                        if (((next$iv$iv$iv2.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) != 0) {
                                                            int count$iv$iv$iv3 = count$iv$iv$iv2 + 1;
                                                            Modifier.Node node2 = nodePop4;
                                                            if (count$iv$iv$iv3 == 1) {
                                                                nodePop4 = next$iv$iv$iv2;
                                                                Object node$iv$iv$iv3 = Unit.INSTANCE;
                                                                count$iv$iv$iv3 = count$iv$iv$iv3;
                                                                i26 = i26;
                                                            } else {
                                                                MutableVector mutableVector6 = mutableVector5 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector5;
                                                                if (node2 != null) {
                                                                    if (mutableVector6 != null) {
                                                                        Boolean.valueOf(mutableVector6.add(node2));
                                                                    }
                                                                    node2 = null;
                                                                }
                                                                if (mutableVector6 != null) {
                                                                    Boolean.valueOf(mutableVector6.add(next$iv$iv$iv2));
                                                                }
                                                                mutableVector5 = mutableVector6;
                                                                nodePop4 = node2;
                                                            }
                                                            count$iv$iv$iv2 = count$iv$iv$iv3;
                                                        } else {
                                                            i26 = i26;
                                                        }
                                                        node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                                        i26 = i26;
                                                    }
                                                    Modifier.Node node3 = nodePop4;
                                                    i4 = i26;
                                                    if (count$iv$iv$iv2 == 1) {
                                                        nodePop4 = node3;
                                                        i26 = i4;
                                                    } else {
                                                        nodePop4 = DelegatableNodeKt.pop(mutableVector5);
                                                        i26 = i4;
                                                    }
                                                }
                                            }
                                            i3 = i26;
                                        } else {
                                            i3 = i26;
                                        }
                                        node$iv$iv$iv2 = node$iv$iv$iv2.getParent();
                                        $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv2;
                                        i26 = i3;
                                    }
                                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv2;
                                    i2 = i26;
                                } else {
                                    $this$nearestAncestor_u2d64DMado$iv = $this$nearestAncestor_u2d64DMado$iv2;
                                    i2 = i26;
                                }
                                layout$iv$iv$iv2 = layout$iv$iv$iv2.getParent$ui();
                                node$iv$iv$iv2 = (layout$iv$iv$iv2 == null || (nodes = layout$iv$iv$iv2.getNodes()) == null) ? null : nodes.getTail();
                                $this$nearestAncestor_u2d64DMado$iv2 = $this$nearestAncestor_u2d64DMado$iv;
                                i26 = i2;
                            }
                            KeyInputModifierNode keyInputModifierNode2 = (KeyInputModifierNode) obj;
                            focusedKeyInputNode = keyInputModifierNode2 != null ? keyInputModifierNode2.getNode() : null;
                        } else {
                            i = 8192;
                        }
                        if (focusedKeyInputNode != null) {
                            Modifier.Node $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = focusedKeyInputNode;
                            int iM6860constructorimpl3 = NodeKind.m6860constructorimpl(i);
                            FocusOwnerImpl this_$iv3 = this;
                            List ancestors$iv = null;
                            boolean value$iv$iv$iv$iv$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2.getNode().getIsAttached();
                            if (!value$iv$iv$iv$iv$iv) {
                                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
                            }
                            Modifier.Node node$iv$iv$iv$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2.getNode().getParent();
                            LayoutNode layout$iv$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2);
                            while (layout$iv$iv$iv$iv != null) {
                                Modifier.Node head$iv$iv$iv$iv = layout$iv$iv$iv$iv.getNodes().getHead();
                                if ((head$iv$iv$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl3) != 0) {
                                    while (node$iv$iv$iv$iv3 != null) {
                                        if ((node$iv$iv$iv$iv3.getKindSet() & iM6860constructorimpl3) != 0) {
                                            Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv3;
                                            MutableVector mutableVector7 = null;
                                            Modifier.Node nodePop5 = it$iv$iv$iv;
                                            while (nodePop5 != null) {
                                                this_$iv3 = this_$iv3;
                                                if (nodePop5 instanceof KeyInputModifierNode) {
                                                    Modifier.Node node4 = nodePop5;
                                                    if (ancestors$iv == null) {
                                                        ancestors$iv = new ArrayList();
                                                    }
                                                    ancestors$iv.add(node4);
                                                    i21 = iM6860constructorimpl3;
                                                } else {
                                                    Modifier.Node this_$iv$iv$iv$iv$iv = nodePop5;
                                                    if (((this_$iv$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl3) != 0 ? 1 : 0) == 0 || !(nodePop5 instanceof DelegatingNode)) {
                                                        i21 = iM6860constructorimpl3;
                                                    } else {
                                                        int count$iv$iv$iv$iv = 0;
                                                        DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop5;
                                                        Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                                        while (node$iv$iv$iv$iv$iv != null) {
                                                            Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                                            if (((next$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl3) != 0 ? 1 : 0) != 0) {
                                                                int count$iv$iv$iv$iv2 = count$iv$iv$iv$iv + 1;
                                                                Modifier.Node node5 = nodePop5;
                                                                if (count$iv$iv$iv$iv2 == 1) {
                                                                    Object node$iv$iv$iv$iv4 = Unit.INSTANCE;
                                                                    count$iv$iv$iv$iv2 = count$iv$iv$iv$iv2;
                                                                    iM6860constructorimpl3 = iM6860constructorimpl3;
                                                                    node = next$iv$iv$iv$iv;
                                                                } else {
                                                                    MutableVector mutableVector8 = mutableVector7 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector7;
                                                                    if (node5 != null) {
                                                                        if (mutableVector8 != null) {
                                                                            Boolean.valueOf(mutableVector8.add(node5));
                                                                        }
                                                                        node = null;
                                                                    } else {
                                                                        node = node5;
                                                                    }
                                                                    if (mutableVector8 != null) {
                                                                        Boolean.valueOf(mutableVector8.add(next$iv$iv$iv$iv));
                                                                    }
                                                                    mutableVector7 = mutableVector8;
                                                                }
                                                                count$iv$iv$iv$iv = count$iv$iv$iv$iv2;
                                                            } else {
                                                                iM6860constructorimpl3 = iM6860constructorimpl3;
                                                                node = nodePop5;
                                                            }
                                                            node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                                            nodePop5 = node;
                                                            iM6860constructorimpl3 = iM6860constructorimpl3;
                                                        }
                                                        Modifier.Node node6 = nodePop5;
                                                        i21 = iM6860constructorimpl3;
                                                        if (count$iv$iv$iv$iv == 1) {
                                                            nodePop5 = node6;
                                                            iM6860constructorimpl3 = i21;
                                                        }
                                                    }
                                                }
                                                nodePop5 = DelegatableNodeKt.pop(mutableVector7);
                                                iM6860constructorimpl3 = i21;
                                            }
                                            this_$iv2 = this_$iv3;
                                            i20 = iM6860constructorimpl3;
                                        } else {
                                            this_$iv2 = this_$iv3;
                                            i20 = iM6860constructorimpl3;
                                        }
                                        node$iv$iv$iv$iv3 = node$iv$iv$iv$iv3.getParent();
                                        $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                                        this_$iv3 = this_$iv2;
                                        iM6860constructorimpl3 = i20;
                                    }
                                    $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                                    this_$iv = this_$iv3;
                                    i19 = iM6860constructorimpl3;
                                } else {
                                    $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                                    this_$iv = this_$iv3;
                                    i19 = iM6860constructorimpl3;
                                }
                                layout$iv$iv$iv$iv = layout$iv$iv$iv$iv.getParent$ui();
                                node$iv$iv$iv$iv3 = (layout$iv$iv$iv$iv == null || (nodes3 = layout$iv$iv$iv$iv.getNodes()) == null) ? null : nodes3.getTail();
                                $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
                                this_$iv3 = this_$iv;
                                iM6860constructorimpl3 = i19;
                            }
                            DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                            int i27 = iM6860constructorimpl3;
                            if (ancestors$iv != null) {
                                List $this$fastForEachReversed$iv$iv = ancestors$iv;
                                int size2 = $this$fastForEachReversed$iv$iv.size() - 1;
                                if (size2 >= 0) {
                                    do {
                                        int index$iv$iv2 = size2;
                                        size2--;
                                        Object item$iv$iv = $this$fastForEachReversed$iv$iv.get(index$iv$iv2);
                                        KeyInputModifierNode it4 = (KeyInputModifierNode) item$iv$iv;
                                        if (it4.mo235onPreKeyEventZmokQxo(keyEvent)) {
                                        }
                                    } while (size2 >= 0);
                                }
                                Unit unit2 = Unit.INSTANCE;
                                $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode();
                                i9 = i27;
                                i10 = 0;
                                mutableVector = null;
                                nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                                while (nodePop != null) {
                                    if (nodePop instanceof KeyInputModifierNode) {
                                        it3 = (KeyInputModifierNode) nodePop;
                                        if (it3.mo235onPreKeyEventZmokQxo(keyEvent)) {
                                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                                            i16 = i9;
                                            i17 = i10;
                                        }
                                    } else {
                                        this_$iv$iv$iv2 = nodePop;
                                        if ((this_$iv$iv$iv2.getKindSet() & i9) != 0) {
                                            i18 = 1;
                                        } else {
                                            i18 = 0;
                                        }
                                        if (i18 == 0 && (nodePop instanceof DelegatingNode)) {
                                            int count$iv$iv = 0;
                                            DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) nodePop;
                                            Modifier.Node node$iv$iv$iv4 = this_$iv$iv$iv3.getDelegate();
                                            while (node$iv$iv$iv4 != null) {
                                                Modifier.Node next$iv$iv = node$iv$iv$iv4;
                                                if (((next$iv$iv.getKindSet() & i9) != 0 ? 1 : 0) != 0) {
                                                    count$iv$iv++;
                                                    if (count$iv$iv == 1) {
                                                        Unit unit3 = Unit.INSTANCE;
                                                        nodePop = next$iv$iv;
                                                        i9 = i9;
                                                        i10 = i10;
                                                    } else {
                                                        MutableVector mutableVector9 = mutableVector == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector;
                                                        Modifier.Node theNode$iv$iv = nodePop;
                                                        if (theNode$iv$iv != null) {
                                                            if (mutableVector9 != null) {
                                                                Boolean.valueOf(mutableVector9.add(theNode$iv$iv));
                                                            }
                                                            nodePop = null;
                                                        }
                                                        if (mutableVector9 != null) {
                                                            Boolean.valueOf(mutableVector9.add(next$iv$iv));
                                                        }
                                                        mutableVector = mutableVector9;
                                                    }
                                                } else {
                                                    i9 = i9;
                                                    i10 = i10;
                                                }
                                                node$iv$iv$iv4 = node$iv$iv$iv4.getChild();
                                                $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                                                i9 = i9;
                                                i10 = i10;
                                            }
                                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                                            i16 = i9;
                                            i17 = i10;
                                            if (count$iv$iv == 1) {
                                                $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                                                i9 = i16;
                                                i10 = i17;
                                            }
                                        } else {
                                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                                            i16 = i9;
                                            i17 = i10;
                                        }
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                                    i9 = i16;
                                    i10 = i17;
                                }
                                if (onFocusedItem.invoke().booleanValue()) {
                                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode();
                                    i11 = i27;
                                    i12 = 0;
                                    mutableVector2 = null;
                                    nodePop2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                                    while (nodePop2 != null) {
                                        if (nodePop2 instanceof KeyInputModifierNode) {
                                            it2 = (KeyInputModifierNode) nodePop2;
                                            if (it2.mo233onKeyEventZmokQxo(keyEvent)) {
                                                $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                                                i13 = i11;
                                                i14 = i12;
                                            }
                                        } else {
                                            this_$iv$iv$iv = nodePop2;
                                            if ((this_$iv$iv$iv.getKindSet() & i11) != 0) {
                                                i15 = 1;
                                            } else {
                                                i15 = 0;
                                            }
                                            if (i15 == 0 && (nodePop2 instanceof DelegatingNode)) {
                                                int count$iv$iv2 = 0;
                                                DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                                Modifier.Node node$iv$iv$iv5 = this_$iv$iv$iv4.getDelegate();
                                                while (node$iv$iv$iv5 != null) {
                                                    Modifier.Node next$iv$iv2 = node$iv$iv$iv5;
                                                    if (((next$iv$iv2.getKindSet() & i11) != 0 ? 1 : 0) != 0) {
                                                        count$iv$iv2++;
                                                        if (count$iv$iv2 == 1) {
                                                            Unit unit4 = Unit.INSTANCE;
                                                            nodePop2 = next$iv$iv2;
                                                            i11 = i11;
                                                            i12 = i12;
                                                        } else {
                                                            MutableVector mutableVector10 = mutableVector2 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector2;
                                                            Modifier.Node theNode$iv$iv2 = nodePop2;
                                                            if (theNode$iv$iv2 != null) {
                                                                if (mutableVector10 != null) {
                                                                    Boolean.valueOf(mutableVector10.add(theNode$iv$iv2));
                                                                }
                                                                nodePop2 = null;
                                                            }
                                                            if (mutableVector10 != null) {
                                                                Boolean.valueOf(mutableVector10.add(next$iv$iv2));
                                                            }
                                                            mutableVector2 = mutableVector10;
                                                        }
                                                    } else {
                                                        i11 = i11;
                                                        i12 = i12;
                                                    }
                                                    node$iv$iv$iv5 = node$iv$iv$iv5.getChild();
                                                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                                                    i11 = i11;
                                                    i12 = i12;
                                                }
                                                $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                                                i13 = i11;
                                                i14 = i12;
                                                if (count$iv$iv2 == 1) {
                                                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                                                    i11 = i13;
                                                    i12 = i14;
                                                }
                                            } else {
                                                $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                                                i13 = i11;
                                                i14 = i12;
                                            }
                                        }
                                        nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                        $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                                        i11 = i13;
                                        i12 = i14;
                                    }
                                    if (ancestors$iv != null) {
                                        $this$fastForEach$iv$iv = ancestors$iv;
                                        size = $this$fastForEach$iv$iv.size();
                                        for (index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                                            Object item$iv$iv2 = $this$fastForEach$iv$iv.get(index$iv$iv);
                                            it = (KeyInputModifierNode) item$iv$iv2;
                                            if (it.mo233onKeyEventZmokQxo(keyEvent)) {
                                            }
                                        }
                                        Unit unit5 = Unit.INSTANCE;
                                    }
                                    Unit unit6 = Unit.INSTANCE;
                                }
                            } else {
                                $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode();
                                i9 = i27;
                                i10 = 0;
                                mutableVector = null;
                                nodePop = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                                while (nodePop != null) {
                                    if (nodePop instanceof KeyInputModifierNode) {
                                        it3 = (KeyInputModifierNode) nodePop;
                                        if (it3.mo235onPreKeyEventZmokQxo(keyEvent)) {
                                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                                            i16 = i9;
                                            i17 = i10;
                                        }
                                    } else {
                                        this_$iv$iv$iv2 = nodePop;
                                        if ((this_$iv$iv$iv2.getKindSet() & i9) != 0) {
                                            i18 = 1;
                                        } else {
                                            i18 = 0;
                                        }
                                        if (i18 == 0) {
                                        }
                                        $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                                        i16 = i9;
                                        i17 = i10;
                                    }
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                                    i9 = i16;
                                    i10 = i17;
                                }
                                if (onFocusedItem.invoke().booleanValue()) {
                                    $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode();
                                    i11 = i27;
                                    i12 = 0;
                                    mutableVector2 = null;
                                    nodePop2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                                    while (nodePop2 != null) {
                                        if (nodePop2 instanceof KeyInputModifierNode) {
                                            it2 = (KeyInputModifierNode) nodePop2;
                                            if (it2.mo233onKeyEventZmokQxo(keyEvent)) {
                                                $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                                                i13 = i11;
                                                i14 = i12;
                                            }
                                        } else {
                                            this_$iv$iv$iv = nodePop2;
                                            if ((this_$iv$iv$iv.getKindSet() & i11) != 0) {
                                                i15 = 1;
                                            } else {
                                                i15 = 0;
                                            }
                                            if (i15 == 0) {
                                            }
                                            $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                                            i13 = i11;
                                            i14 = i12;
                                        }
                                        nodePop2 = DelegatableNodeKt.pop(mutableVector2);
                                        $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                                        i11 = i13;
                                        i12 = i14;
                                    }
                                    if (ancestors$iv != null) {
                                        $this$fastForEach$iv$iv = ancestors$iv;
                                        size = $this$fastForEach$iv$iv.size();
                                        while (index$iv$iv < size) {
                                            Object item$iv$iv3 = $this$fastForEach$iv$iv.get(index$iv$iv);
                                            it = (KeyInputModifierNode) item$iv$iv3;
                                            if (it.mo233onKeyEventZmokQxo(keyEvent)) {
                                            }
                                        }
                                        Unit unit7 = Unit.INSTANCE;
                                    }
                                    Unit unit8 = Unit.INSTANCE;
                                }
                            }
                            Trace.endSection();
                            return true;
                        }
                        Trace.endSection();
                        return false;
                    }
                    Trace.endSection();
                    throw th;
                }
                System.out.println((Object) "FocusRelatedWarning: Dispatching key event while focus system is invalidated.");
                Trace.endSection();
                return false;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: dispatchInterceptedSoftKeyboardEvent-ZmokQxo */
    public boolean mo4744dispatchInterceptedSoftKeyboardEventZmokQxo(KeyEvent keyEvent) {
        int i;
        SoftKeyboardInterceptionModifierNode focusedSoftKeyboardInterceptionNode;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        int i2;
        int i3;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
        int i4;
        int i5;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
        int i6;
        NodeChain nodes;
        int i7;
        int i8;
        Modifier.Node node;
        Modifier.Node node2;
        DelegatableNode delegatableNode;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
        int i9;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
        if (this.focusInvalidationManager.getIsInvalidationScheduled()) {
            System.out.println((Object) "FocusRelatedWarning: Dispatching intercepted soft keyboard event while the focus system is invalidated.");
            return false;
        }
        DelegatableNode delegatableNodeFindActiveFocusNode = FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
        int i10 = 131072;
        int i11 = 1;
        if (delegatableNodeFindActiveFocusNode != null) {
            DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = delegatableNodeFindActiveFocusNode;
            int iM6860constructorimpl = NodeKind.m6860constructorimpl(131072);
            boolean value$iv$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4.getNode();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestorIncludingSelf_u2d64DMado$iv4);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    i = i10;
                    delegatableNode = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                    while (node$iv$iv$iv != null) {
                        if ((node$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv;
                            MutableVector mutableVector = null;
                            i = i10;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof SoftKeyboardInterceptionModifierNode) {
                                    delegatableNode = nodePop;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i11 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                    i11 = 1;
                                } else {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if (((next$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i11 : 0) != 0) {
                                            count$iv$iv$iv++;
                                            if (count$iv$iv$iv == i11) {
                                                nodePop = next$iv$iv$iv;
                                                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
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
                                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        i11 = 1;
                                    }
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                    if (count$iv$iv$iv == 1) {
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        i11 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        i11 = 1;
                                    }
                                }
                            }
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                        } else {
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                            i = i10;
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getParent();
                        i11 = 1;
                        i10 = i;
                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
                    }
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                    i9 = i10;
                } else {
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                    i9 = i10;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i11 = 1;
                i10 = i9;
                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
            }
            focusedSoftKeyboardInterceptionNode = (SoftKeyboardInterceptionModifierNode) delegatableNode;
        } else {
            i = 131072;
            focusedSoftKeyboardInterceptionNode = null;
        }
        if (focusedSoftKeyboardInterceptionNode == null) {
            return false;
        }
        SoftKeyboardInterceptionModifierNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = focusedSoftKeyboardInterceptionNode;
        int iM6860constructorimpl2 = NodeKind.m6860constructorimpl(i);
        List ancestors$iv = null;
        boolean value$iv$iv$iv$iv$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv$iv$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2.getNode().getParent();
        LayoutNode layout$iv$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2);
        while (layout$iv$iv$iv$iv != null) {
            Modifier.Node head$iv$iv$iv$iv = layout$iv$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl2) != 0) {
                while (node$iv$iv$iv$iv2 != null) {
                    if ((node$iv$iv$iv$iv2.getKindSet() & iM6860constructorimpl2) != 0) {
                        Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv2;
                        MutableVector mutableVector3 = null;
                        Modifier.Node nodePop2 = it$iv$iv$iv;
                        while (nodePop2 != null) {
                            $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                            if (nodePop2 instanceof SoftKeyboardInterceptionModifierNode) {
                                Modifier.Node node3 = nodePop2;
                                if (ancestors$iv == null) {
                                    Object result$iv$iv = new ArrayList();
                                    ancestors$iv = (List) result$iv$iv;
                                }
                                ancestors$iv.add(node3);
                                i7 = iM6860constructorimpl2;
                            } else {
                                Modifier.Node this_$iv$iv$iv$iv$iv = nodePop2;
                                if (((this_$iv$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                    i7 = iM6860constructorimpl2;
                                } else {
                                    int count$iv$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                    Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                        if (((next$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv$iv$iv++;
                                            i8 = iM6860constructorimpl2;
                                            if (count$iv$iv$iv$iv == 1) {
                                                node = next$iv$iv$iv$iv;
                                            } else {
                                                MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                Modifier.Node theNode$iv$iv$iv$iv = nodePop2;
                                                if (theNode$iv$iv$iv$iv != null) {
                                                    if (mutableVector4 != null) {
                                                        mutableVector4.add(theNode$iv$iv$iv$iv);
                                                    }
                                                    node2 = null;
                                                }
                                                if (mutableVector4 != null) {
                                                    node2 = nodePop2;
                                                    mutableVector4.add(next$iv$iv$iv$iv);
                                                } else {
                                                    node2 = nodePop2;
                                                }
                                                node = node2;
                                                count$iv$iv$iv$iv = count$iv$iv$iv$iv;
                                                mutableVector3 = mutableVector4;
                                            }
                                        } else {
                                            i8 = iM6860constructorimpl2;
                                            node = nodePop2;
                                        }
                                        node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                        iM6860constructorimpl2 = i8;
                                        nodePop2 = node;
                                    }
                                    i7 = iM6860constructorimpl2;
                                    Modifier.Node node4 = nodePop2;
                                    if (count$iv$iv$iv$iv == 1) {
                                        iM6860constructorimpl2 = i7;
                                        nodePop2 = node4;
                                    }
                                }
                            }
                            nodePop2 = DelegatableNodeKt.pop(mutableVector3);
                            iM6860constructorimpl2 = i7;
                        }
                    }
                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getParent();
                    $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                    iM6860constructorimpl2 = iM6860constructorimpl2;
                }
                $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                i6 = iM6860constructorimpl2;
            } else {
                $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                i6 = iM6860constructorimpl2;
            }
            layout$iv$iv$iv$iv = layout$iv$iv$iv$iv.getParent$ui();
            node$iv$iv$iv$iv2 = (layout$iv$iv$iv$iv == null || (nodes = layout$iv$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
            iM6860constructorimpl2 = i6;
        }
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
        int i12 = iM6860constructorimpl2;
        if (ancestors$iv != null) {
            List $this$fastForEachReversed$iv$iv = ancestors$iv;
            int size = $this$fastForEachReversed$iv$iv.size() - 1;
            if (size >= 0) {
                do {
                    int index$iv$iv = size;
                    size--;
                    Object item$iv$iv = $this$fastForEachReversed$iv$iv.get(index$iv$iv);
                    SoftKeyboardInterceptionModifierNode it = (SoftKeyboardInterceptionModifierNode) item$iv$iv;
                    if (it.mo5941onPreInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                        return true;
                    }
                } while (size >= 0);
            }
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode();
        int i13 = i12;
        int i14 = 0;
        MutableVector mutableVector5 = null;
        Modifier.Node nodePop3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
        while (nodePop3 != null) {
            if (nodePop3 instanceof SoftKeyboardInterceptionModifierNode) {
                SoftKeyboardInterceptionModifierNode it2 = (SoftKeyboardInterceptionModifierNode) nodePop3;
                if (it2.mo5941onPreInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                    return true;
                }
                $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                i4 = i13;
                i5 = i14;
            } else {
                Modifier.Node this_$iv$iv$iv = nodePop3;
                if (((this_$iv$iv$iv.getKindSet() & i13) != 0 ? 1 : 0) == 0 || !(nodePop3 instanceof DelegatingNode)) {
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                    i4 = i13;
                    i5 = i14;
                } else {
                    int count$iv$iv = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop3;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv2;
                        if (((next$iv$iv.getKindSet() & i13) != 0 ? 1 : 0) != 0) {
                            count$iv$iv++;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                            if (count$iv$iv == 1) {
                                nodePop3 = next$iv$iv;
                                i13 = i13;
                                i14 = i14;
                            } else {
                                mutableVector5 = mutableVector5 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector5;
                                Modifier.Node theNode$iv$iv = nodePop3;
                                if (theNode$iv$iv != null) {
                                    if (mutableVector5 != null) {
                                        mutableVector5.add(theNode$iv$iv);
                                    }
                                    nodePop3 = null;
                                }
                                if (mutableVector5 != null) {
                                    mutableVector5.add(next$iv$iv);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                            i13 = i13;
                            i14 = i14;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                        i13 = i13;
                        i14 = i14;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                    i4 = i13;
                    i5 = i14;
                    if (count$iv$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                        i13 = i4;
                        i14 = i5;
                    }
                }
            }
            nodePop3 = DelegatableNodeKt.pop(mutableVector5);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
            i13 = i4;
            i14 = i5;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode();
        int i15 = i12;
        int i16 = 0;
        MutableVector mutableVector6 = null;
        Modifier.Node nodePop4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
        while (nodePop4 != null) {
            if (nodePop4 instanceof SoftKeyboardInterceptionModifierNode) {
                SoftKeyboardInterceptionModifierNode it3 = (SoftKeyboardInterceptionModifierNode) nodePop4;
                if (it3.mo5940onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                    return true;
                }
                $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                i2 = i15;
                i3 = i16;
            } else {
                Modifier.Node this_$iv$iv$iv3 = nodePop4;
                if (((this_$iv$iv$iv3.getKindSet() & i15) != 0 ? 1 : 0) == 0 || !(nodePop4 instanceof DelegatingNode)) {
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                    i2 = i15;
                    i3 = i16;
                } else {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop4;
                    Modifier.Node node$iv$iv$iv3 = this_$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv3 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv3;
                        if (((next$iv$iv2.getKindSet() & i15) != 0 ? 1 : 0) != 0) {
                            count$iv$iv2++;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                            if (count$iv$iv2 == 1) {
                                nodePop4 = next$iv$iv2;
                                i15 = i15;
                                i16 = i16;
                            } else {
                                mutableVector6 = mutableVector6 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector6;
                                Modifier.Node theNode$iv$iv2 = nodePop4;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector6 != null) {
                                        mutableVector6.add(theNode$iv$iv2);
                                    }
                                    nodePop4 = null;
                                }
                                if (mutableVector6 != null) {
                                    mutableVector6.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                            i15 = i15;
                            i16 = i16;
                        }
                        node$iv$iv$iv3 = node$iv$iv$iv3.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                        i15 = i15;
                        i16 = i16;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                    i2 = i15;
                    i3 = i16;
                    if (count$iv$iv2 == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                        i15 = i2;
                        i16 = i3;
                    }
                }
            }
            nodePop4 = DelegatableNodeKt.pop(mutableVector6);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
            i15 = i2;
            i16 = i3;
        }
        if (ancestors$iv == null) {
            return false;
        }
        List $this$fastForEach$iv$iv = ancestors$iv;
        int size2 = $this$fastForEach$iv$iv.size();
        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
            Object item$iv$iv2 = $this$fastForEach$iv$iv.get(index$iv$iv2);
            SoftKeyboardInterceptionModifierNode it4 = (SoftKeyboardInterceptionModifierNode) item$iv$iv2;
            if (it4.mo5940onInterceptKeyBeforeSoftKeyboardZmokQxo(keyEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchRotaryEvent(RotaryScrollEvent event, Function0<Boolean> onFocusedItem) {
        int i;
        RotaryInputModifierNode focusedRotaryInputNode;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv;
        int i2;
        int i3;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
        int i4;
        int i5;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
        int i6;
        NodeChain nodes;
        int i7;
        int i8;
        Modifier.Node node;
        Modifier.Node node2;
        DelegatableNode delegatableNode;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
        int i9;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
        if (this.focusInvalidationManager.getIsInvalidationScheduled()) {
            System.out.println((Object) "FocusRelatedWarning: Dispatching rotary event while the focus system is invalidated.");
            return false;
        }
        DelegatableNode delegatableNodeFindFocusTargetNode = findFocusTargetNode();
        int i10 = 16384;
        int i11 = 1;
        if (delegatableNodeFindFocusTargetNode != null) {
            DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = delegatableNodeFindFocusTargetNode;
            int iM6860constructorimpl = NodeKind.m6860constructorimpl(16384);
            boolean value$iv$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4.getNode();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestorIncludingSelf_u2d64DMado$iv4);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    i = i10;
                    delegatableNode = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                    while (node$iv$iv$iv != null) {
                        if ((node$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv;
                            MutableVector mutableVector = null;
                            i = i10;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof RotaryInputModifierNode) {
                                    delegatableNode = nodePop;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i11 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                    i11 = 1;
                                } else {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if (((next$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i11 : 0) != 0) {
                                            count$iv$iv$iv++;
                                            if (count$iv$iv$iv == i11) {
                                                nodePop = next$iv$iv$iv;
                                                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
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
                                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        i11 = 1;
                                    }
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                    if (count$iv$iv$iv == 1) {
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        i11 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        i11 = 1;
                                    }
                                }
                            }
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                        } else {
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                            i = i10;
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getParent();
                        i11 = 1;
                        i10 = i;
                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
                    }
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                    i9 = i10;
                } else {
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                    i9 = i10;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i11 = 1;
                i10 = i9;
                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
            }
            focusedRotaryInputNode = (RotaryInputModifierNode) delegatableNode;
        } else {
            i = 16384;
            focusedRotaryInputNode = null;
        }
        if (focusedRotaryInputNode == null) {
            return false;
        }
        RotaryInputModifierNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = focusedRotaryInputNode;
        int iM6860constructorimpl2 = NodeKind.m6860constructorimpl(i);
        List ancestors$iv = null;
        boolean value$iv$iv$iv$iv$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv$iv$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2.getNode().getParent();
        LayoutNode layout$iv$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2);
        while (layout$iv$iv$iv$iv != null) {
            Modifier.Node head$iv$iv$iv$iv = layout$iv$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl2) != 0) {
                while (node$iv$iv$iv$iv2 != null) {
                    if ((node$iv$iv$iv$iv2.getKindSet() & iM6860constructorimpl2) != 0) {
                        Modifier.Node it$iv$iv$iv = node$iv$iv$iv$iv2;
                        MutableVector mutableVector3 = null;
                        Modifier.Node nodePop2 = it$iv$iv$iv;
                        while (nodePop2 != null) {
                            $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                            if (nodePop2 instanceof RotaryInputModifierNode) {
                                Modifier.Node node3 = nodePop2;
                                if (ancestors$iv == null) {
                                    Object result$iv$iv = new ArrayList();
                                    ancestors$iv = (List) result$iv$iv;
                                }
                                ancestors$iv.add(node3);
                                i7 = iM6860constructorimpl2;
                            } else {
                                Modifier.Node this_$iv$iv$iv$iv$iv = nodePop2;
                                if (((this_$iv$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                    i7 = iM6860constructorimpl2;
                                } else {
                                    int count$iv$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv$iv2 = (DelegatingNode) nodePop2;
                                    Modifier.Node node$iv$iv$iv$iv$iv = this_$iv$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv$iv = node$iv$iv$iv$iv$iv;
                                        if (((next$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv$iv$iv++;
                                            i8 = iM6860constructorimpl2;
                                            if (count$iv$iv$iv$iv == 1) {
                                                node = next$iv$iv$iv$iv;
                                            } else {
                                                MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                Modifier.Node theNode$iv$iv$iv$iv = nodePop2;
                                                if (theNode$iv$iv$iv$iv != null) {
                                                    if (mutableVector4 != null) {
                                                        mutableVector4.add(theNode$iv$iv$iv$iv);
                                                    }
                                                    node2 = null;
                                                }
                                                if (mutableVector4 != null) {
                                                    node2 = nodePop2;
                                                    mutableVector4.add(next$iv$iv$iv$iv);
                                                } else {
                                                    node2 = nodePop2;
                                                }
                                                node = node2;
                                                count$iv$iv$iv$iv = count$iv$iv$iv$iv;
                                                mutableVector3 = mutableVector4;
                                            }
                                        } else {
                                            i8 = iM6860constructorimpl2;
                                            node = nodePop2;
                                        }
                                        node$iv$iv$iv$iv$iv = node$iv$iv$iv$iv$iv.getChild();
                                        iM6860constructorimpl2 = i8;
                                        nodePop2 = node;
                                    }
                                    i7 = iM6860constructorimpl2;
                                    Modifier.Node node4 = nodePop2;
                                    if (count$iv$iv$iv$iv == 1) {
                                        iM6860constructorimpl2 = i7;
                                        nodePop2 = node4;
                                    }
                                }
                            }
                            nodePop2 = DelegatableNodeKt.pop(mutableVector3);
                            iM6860constructorimpl2 = i7;
                        }
                    }
                    node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getParent();
                    $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                    iM6860constructorimpl2 = iM6860constructorimpl2;
                }
                $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                i6 = iM6860constructorimpl2;
            } else {
                $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
                i6 = iM6860constructorimpl2;
            }
            layout$iv$iv$iv$iv = layout$iv$iv$iv$iv.getParent$ui();
            node$iv$iv$iv$iv2 = (layout$iv$iv$iv$iv == null || (nodes = layout$iv$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv;
            iM6860constructorimpl2 = i6;
        }
        DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv2;
        int i12 = iM6860constructorimpl2;
        if (ancestors$iv != null) {
            List $this$fastForEachReversed$iv$iv = ancestors$iv;
            int size = $this$fastForEachReversed$iv$iv.size() - 1;
            if (size >= 0) {
                do {
                    int index$iv$iv = size;
                    size--;
                    Object item$iv$iv = $this$fastForEachReversed$iv$iv.get(index$iv$iv);
                    RotaryInputModifierNode it = (RotaryInputModifierNode) item$iv$iv;
                    if (it.onPreRotaryScrollEvent(event)) {
                        return true;
                    }
                } while (size >= 0);
            }
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode();
        int i13 = i12;
        int i14 = 0;
        MutableVector mutableVector5 = null;
        Modifier.Node nodePop3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
        while (nodePop3 != null) {
            if (nodePop3 instanceof RotaryInputModifierNode) {
                RotaryInputModifierNode it2 = (RotaryInputModifierNode) nodePop3;
                if (it2.onPreRotaryScrollEvent(event)) {
                    return true;
                }
                $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                i4 = i13;
                i5 = i14;
            } else {
                Modifier.Node this_$iv$iv$iv = nodePop3;
                if (((this_$iv$iv$iv.getKindSet() & i13) != 0 ? 1 : 0) == 0 || !(nodePop3 instanceof DelegatingNode)) {
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                    i4 = i13;
                    i5 = i14;
                } else {
                    int count$iv$iv = 0;
                    DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop3;
                    Modifier.Node node$iv$iv$iv2 = this_$iv$iv$iv2.getDelegate();
                    while (node$iv$iv$iv2 != null) {
                        Modifier.Node next$iv$iv = node$iv$iv$iv2;
                        if (((next$iv$iv.getKindSet() & i13) != 0 ? 1 : 0) != 0) {
                            count$iv$iv++;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                            if (count$iv$iv == 1) {
                                nodePop3 = next$iv$iv;
                                i13 = i13;
                                i14 = i14;
                            } else {
                                mutableVector5 = mutableVector5 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector5;
                                Modifier.Node theNode$iv$iv = nodePop3;
                                if (theNode$iv$iv != null) {
                                    if (mutableVector5 != null) {
                                        mutableVector5.add(theNode$iv$iv);
                                    }
                                    nodePop3 = null;
                                }
                                if (mutableVector5 != null) {
                                    mutableVector5.add(next$iv$iv);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                            i13 = i13;
                            i14 = i14;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv4;
                        i13 = i13;
                        i14 = i14;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv$iv5;
                    i4 = i13;
                    i5 = i14;
                    if (count$iv$iv == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
                        i13 = i4;
                        i14 = i5;
                    }
                }
            }
            nodePop3 = DelegatableNodeKt.pop(mutableVector5);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv5 = $this$dispatchForKind_u2d6rFNWt0$iv$iv3;
            i13 = i4;
            i14 = i5;
        }
        if (onFocusedItem.invoke().booleanValue()) {
            return true;
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k$iv3.getNode();
        int i15 = i12;
        int i16 = 0;
        MutableVector mutableVector6 = null;
        Modifier.Node nodePop4 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
        while (nodePop4 != null) {
            if (nodePop4 instanceof RotaryInputModifierNode) {
                RotaryInputModifierNode it3 = (RotaryInputModifierNode) nodePop4;
                if (it3.onRotaryScrollEvent(event)) {
                    return true;
                }
                $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                i2 = i15;
                i3 = i16;
            } else {
                Modifier.Node this_$iv$iv$iv3 = nodePop4;
                if (((this_$iv$iv$iv3.getKindSet() & i15) != 0 ? 1 : 0) == 0 || !(nodePop4 instanceof DelegatingNode)) {
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                    i2 = i15;
                    i3 = i16;
                } else {
                    int count$iv$iv2 = 0;
                    DelegatingNode this_$iv$iv$iv4 = (DelegatingNode) nodePop4;
                    Modifier.Node node$iv$iv$iv3 = this_$iv$iv$iv4.getDelegate();
                    while (node$iv$iv$iv3 != null) {
                        Modifier.Node next$iv$iv2 = node$iv$iv$iv3;
                        if (((next$iv$iv2.getKindSet() & i15) != 0 ? 1 : 0) != 0) {
                            count$iv$iv2++;
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                            if (count$iv$iv2 == 1) {
                                nodePop4 = next$iv$iv2;
                                i15 = i15;
                                i16 = i16;
                            } else {
                                mutableVector6 = mutableVector6 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector6;
                                Modifier.Node theNode$iv$iv2 = nodePop4;
                                if (theNode$iv$iv2 != null) {
                                    if (mutableVector6 != null) {
                                        mutableVector6.add(theNode$iv$iv2);
                                    }
                                    nodePop4 = null;
                                }
                                if (mutableVector6 != null) {
                                    mutableVector6.add(next$iv$iv2);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                            i15 = i15;
                            i16 = i16;
                        }
                        node$iv$iv$iv3 = node$iv$iv$iv3.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv2;
                        i15 = i15;
                        i16 = i16;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv$iv = $this$dispatchForKind_u2d6rFNWt0$iv$iv6;
                    i2 = i15;
                    i3 = i16;
                    if (count$iv$iv2 == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
                        i15 = i2;
                        i16 = i3;
                    }
                }
            }
            nodePop4 = DelegatableNodeKt.pop(mutableVector6);
            $this$dispatchForKind_u2d6rFNWt0$iv$iv6 = $this$dispatchForKind_u2d6rFNWt0$iv$iv;
            i15 = i2;
            i16 = i3;
        }
        if (ancestors$iv == null) {
            return false;
        }
        List $this$fastForEach$iv$iv = ancestors$iv;
        int size2 = $this$fastForEach$iv$iv.size();
        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
            Object item$iv$iv2 = $this$fastForEach$iv$iv.get(index$iv$iv2);
            RotaryInputModifierNode it4 = (RotaryInputModifierNode) item$iv$iv2;
            if (it4.onRotaryScrollEvent(event)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean dispatchIndirectPointerEvent(IndirectPointerEvent event) {
        int i;
        IndirectPointerInputModifierNode focusedIndirectPointerInputNode;
        boolean z;
        boolean z2;
        int i2;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv;
        boolean z3;
        NodeChain nodes;
        int i3;
        Modifier.Node node$iv$iv$iv;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv2;
        boolean z4;
        Modifier.Node node$iv$iv$iv2;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv3;
        Object obj;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
        int i4;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
        boolean z5 = false;
        if (this.focusInvalidationManager.getIsInvalidationScheduled()) {
            System.out.println((Object) "FocusRelatedWarning: Dispatching indirect pointer event while the focus system is invalidated.");
            return false;
        }
        DelegatableNode activeFocusTargetNode = getActiveFocusTargetNode();
        int i5 = 2097152;
        int i6 = 1;
        if (activeFocusTargetNode != null) {
            DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = activeFocusTargetNode;
            int iM6860constructorimpl = NodeKind.m6860constructorimpl(2097152);
            boolean value$iv$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4.getNode();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestorIncludingSelf_u2d64DMado$iv4);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    i = i5;
                    obj = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                    while (node$iv$iv$iv3 != null) {
                        if ((node$iv$iv$iv3.getKindSet() & iM6860constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv3;
                            MutableVector mutableVector = null;
                            i = i5;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof IndirectPointerInputModifierNode) {
                                    obj = nodePop;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i6 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                    i6 = 1;
                                } else {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if (((next$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i6 : 0) != 0) {
                                            count$iv$iv$iv++;
                                            if (count$iv$iv$iv == i6) {
                                                nodePop = next$iv$iv$iv;
                                                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
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
                                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        i6 = 1;
                                    }
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                    if (count$iv$iv$iv == 1) {
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        i6 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        i6 = 1;
                                    }
                                }
                            }
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                        } else {
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                            i = i5;
                        }
                        node$iv$iv$iv3 = node$iv$iv$iv3.getParent();
                        i6 = 1;
                        i5 = i;
                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
                    }
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                    i4 = i5;
                } else {
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                    i4 = i5;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                node$iv$iv$iv3 = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i6 = 1;
                i5 = i4;
                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
            }
            focusedIndirectPointerInputNode = (IndirectPointerInputModifierNode) obj;
        } else {
            i = 2097152;
            focusedIndirectPointerInputNode = null;
        }
        if (focusedIndirectPointerInputNode != null) {
            IndirectPointerInputModifierNode node = focusedIndirectPointerInputNode;
            int i7 = 0;
            IndirectPointerInputModifierNode $this$ancestors_u2d6rFNWt0_u24default$iv4 = node;
            int iM6860constructorimpl2 = NodeKind.m6860constructorimpl(i);
            List ancestors = null;
            boolean value$iv$iv$iv$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv2) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv4.getNode().getParent();
            LayoutNode layout$iv$iv$iv2 = DelegatableNodeKt.requireLayoutNode($this$ancestors_u2d6rFNWt0_u24default$iv4);
            while (layout$iv$iv$iv2 != null) {
                Modifier.Node head$iv$iv$iv2 = layout$iv$iv$iv2.getNodes().getHead();
                if ((head$iv$iv$iv2.getAggregateChildKindSet() & iM6860constructorimpl2) != 0) {
                    while (node$iv$iv$iv4 != null) {
                        if ((node$iv$iv$iv4.getKindSet() & iM6860constructorimpl2) != 0) {
                            Modifier.Node it$iv$iv2 = node$iv$iv$iv4;
                            MutableVector mutableVector3 = null;
                            Modifier.Node nodePop2 = it$iv$iv2;
                            while (nodePop2 != null) {
                                i7 = i7;
                                if (nodePop2 instanceof IndirectPointerInputModifierNode) {
                                    Modifier.Node node2 = nodePop2;
                                    if (ancestors == null) {
                                        Object result$iv = new ArrayList();
                                        ancestors = (List) result$iv;
                                    }
                                    ancestors.add(node2);
                                    node$iv$iv$iv2 = node$iv$iv$iv4;
                                    $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                } else {
                                    Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                    if (((this_$iv$iv$iv$iv3.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                        node$iv$iv$iv2 = node$iv$iv$iv4;
                                        $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                    } else {
                                        int count$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                        Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                        while (node$iv$iv$iv$iv2 != null) {
                                            Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                            if (((next$iv$iv$iv2.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) != 0) {
                                                count$iv$iv$iv2++;
                                                Modifier.Node node3 = nodePop2;
                                                if (count$iv$iv$iv2 == 1) {
                                                    nodePop2 = next$iv$iv$iv2;
                                                    node$iv$iv$iv4 = node$iv$iv$iv4;
                                                    $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                                } else {
                                                    MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                    if (node3 != null) {
                                                        if (mutableVector4 != null) {
                                                            mutableVector4.add(node3);
                                                        }
                                                        node3 = null;
                                                    }
                                                    if (mutableVector4 != null) {
                                                        mutableVector4.add(next$iv$iv$iv2);
                                                    }
                                                    mutableVector3 = mutableVector4;
                                                    nodePop2 = node3;
                                                    count$iv$iv$iv2 = count$iv$iv$iv2;
                                                }
                                            } else {
                                                node$iv$iv$iv4 = node$iv$iv$iv4;
                                                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                            }
                                            node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                            node$iv$iv$iv4 = node$iv$iv$iv4;
                                            $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                        }
                                        Modifier.Node node4 = nodePop2;
                                        node$iv$iv$iv2 = node$iv$iv$iv4;
                                        $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                        if (count$iv$iv$iv2 == 1) {
                                            nodePop2 = node4;
                                            node$iv$iv$iv4 = node$iv$iv$iv2;
                                            $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                                        }
                                    }
                                }
                                nodePop2 = DelegatableNodeKt.pop(mutableVector3);
                                node$iv$iv$iv4 = node$iv$iv$iv2;
                                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                            }
                            i3 = i7;
                            node$iv$iv$iv = node$iv$iv$iv4;
                            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                            z4 = false;
                        } else {
                            i3 = i7;
                            node$iv$iv$iv = node$iv$iv$iv4;
                            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                            z4 = z5;
                        }
                        node$iv$iv$iv4 = node$iv$iv$iv.getParent();
                        z5 = z4;
                        i7 = i3;
                        $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                    }
                    i2 = i7;
                    $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                    z3 = z5;
                } else {
                    i2 = i7;
                    $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                    z3 = z5;
                }
                layout$iv$iv$iv2 = layout$iv$iv$iv2.getParent$ui();
                node$iv$iv$iv4 = (layout$iv$iv$iv2 == null || (nodes = layout$iv$iv$iv2.getNodes()) == null) ? null : nodes.getTail();
                z5 = z3;
                i7 = i2;
                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv;
            }
            z2 = true;
            z = z5;
            if (ancestors != null) {
                List $this$fastForEachReversed$iv = ancestors;
                int size = $this$fastForEachReversed$iv.size() - 1;
                if (size >= 0) {
                    do {
                        int index$iv = size;
                        size--;
                        Object item$iv = $this$fastForEachReversed$iv.get(index$iv);
                        IndirectPointerInputModifierNode it = (IndirectPointerInputModifierNode) item$iv;
                        it.onIndirectPointerEvent(event, PointerEventPass.Initial);
                    } while (size >= 0);
                }
            }
            node.onIndirectPointerEvent(event, PointerEventPass.Initial);
            node.onIndirectPointerEvent(event, PointerEventPass.Main);
            if (ancestors != null) {
                List $this$fastForEach$iv = ancestors;
                int size2 = $this$fastForEach$iv.size();
                for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                    Object item$iv2 = $this$fastForEach$iv.get(index$iv2);
                    IndirectPointerInputModifierNode it2 = (IndirectPointerInputModifierNode) item$iv2;
                    it2.onIndirectPointerEvent(event, PointerEventPass.Main);
                }
            }
            if (ancestors != null) {
                List $this$fastForEachReversed$iv2 = ancestors;
                int size3 = $this$fastForEachReversed$iv2.size() - 1;
                if (size3 >= 0) {
                    do {
                        int index$iv3 = size3;
                        size3--;
                        Object item$iv3 = $this$fastForEachReversed$iv2.get(index$iv3);
                        IndirectPointerInputModifierNode it3 = (IndirectPointerInputModifierNode) item$iv3;
                        it3.onIndirectPointerEvent(event, PointerEventPass.Final);
                    } while (size3 >= 0);
                }
            }
            node.onIndirectPointerEvent(event, PointerEventPass.Final);
        } else {
            z = false;
            z2 = true;
        }
        List<IndirectPointerInputChange> changes = event.getChanges();
        int size4 = changes.size();
        for (int index$iv$iv = 0; index$iv$iv < size4; index$iv$iv++) {
            Object item$iv$iv = changes.get(index$iv$iv);
            IndirectPointerInputChange it4 = (IndirectPointerInputChange) item$iv$iv;
            if (it4.getIsConsumed()) {
                boolean isConsumed = z2;
                return isConsumed;
            }
        }
        boolean isConsumed2 = z;
        return isConsumed2;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void dispatchIndirectPointerCancel() {
        String str;
        int i;
        IndirectPointerInputModifierNode focusedIndirectPointerInputNode;
        IndirectPointerInputModifierNode node;
        int i2;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv;
        IndirectPointerInputModifierNode focusedIndirectPointerInputNode2;
        IndirectPointerInputModifierNode focusedIndirectPointerInputNode3;
        NodeChain nodes;
        IndirectPointerInputModifierNode node2;
        int i3;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv2;
        IndirectPointerInputModifierNode focusedIndirectPointerInputNode4;
        IndirectPointerInputModifierNode focusedIndirectPointerInputNode5;
        int i4;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv3;
        IndirectPointerInputModifierNode focusedIndirectPointerInputNode6;
        int i5;
        Object obj;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
        String str2;
        int i6;
        NodeChain nodes2;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
        String str3;
        DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
        String str4;
        DelegatableNode activeFocusTargetNode = getActiveFocusTargetNode();
        String str5 = "visitAncestors called on an unattached node";
        int i7 = 2097152;
        IndirectPointerInputModifierNode indirectPointerInputModifierNode = null;
        int i8 = 1;
        if (activeFocusTargetNode != null) {
            DelegatableNode $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = activeFocusTargetNode;
            int iM6860constructorimpl = NodeKind.m6860constructorimpl(2097152);
            boolean value$iv$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node$iv$iv$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4.getNode();
            LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$nearestAncestorIncludingSelf_u2d64DMado$iv4);
            loop0: while (true) {
                if (layout$iv$iv$iv == null) {
                    str = str5;
                    i = i7;
                    obj = null;
                    break;
                }
                Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
                if ((head$iv$iv$iv.getAggregateChildKindSet() & iM6860constructorimpl) != 0) {
                    while (node$iv$iv$iv != null) {
                        if ((node$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                            Modifier.Node it$iv$iv = node$iv$iv$iv;
                            MutableVector mutableVector = null;
                            i = i7;
                            Modifier.Node nodePop = it$iv$iv;
                            while (nodePop != null) {
                                if (nodePop instanceof IndirectPointerInputModifierNode) {
                                    obj = nodePop;
                                    str = str5;
                                    break loop0;
                                }
                                Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                if (((this_$iv$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i8 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                    str4 = str5;
                                    nodePop = DelegatableNodeKt.pop(mutableVector);
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                    str5 = str4;
                                    i8 = 1;
                                } else {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                        if (((next$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? i8 : 0) != 0) {
                                            count$iv$iv$iv++;
                                            if (count$iv$iv$iv == i8) {
                                                nodePop = next$iv$iv$iv;
                                                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                                str5 = str5;
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
                                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                            str5 = str5;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                        str5 = str5;
                                        i8 = 1;
                                    }
                                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv3 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                                    str4 = str5;
                                    if (count$iv$iv$iv == 1) {
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        str5 = str4;
                                        i8 = 1;
                                    } else {
                                        nodePop = DelegatableNodeKt.pop(mutableVector);
                                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv3;
                                        str5 = str4;
                                        i8 = 1;
                                    }
                                }
                            }
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                            str3 = str5;
                        } else {
                            $this$nearestAncestorIncludingSelf_u2d64DMado$iv2 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                            str3 = str5;
                            i = i7;
                        }
                        node$iv$iv$iv = node$iv$iv$iv.getParent();
                        i7 = i;
                        $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv2;
                        str5 = str3;
                        i8 = 1;
                    }
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                    str2 = str5;
                    i6 = i7;
                } else {
                    $this$nearestAncestorIncludingSelf_u2d64DMado$iv = $this$nearestAncestorIncludingSelf_u2d64DMado$iv4;
                    str2 = str5;
                    i6 = i7;
                }
                layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
                node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes2 = layout$iv$iv$iv.getNodes()) == null) ? null : nodes2.getTail();
                i7 = i6;
                $this$nearestAncestorIncludingSelf_u2d64DMado$iv4 = $this$nearestAncestorIncludingSelf_u2d64DMado$iv;
                str5 = str2;
                i8 = 1;
            }
            focusedIndirectPointerInputNode = (IndirectPointerInputModifierNode) obj;
        } else {
            str = "visitAncestors called on an unattached node";
            i = 2097152;
            focusedIndirectPointerInputNode = null;
        }
        if (focusedIndirectPointerInputNode != null) {
            IndirectPointerInputModifierNode node3 = focusedIndirectPointerInputNode;
            int i9 = 0;
            IndirectPointerInputModifierNode $this$ancestors_u2d6rFNWt0_u24default$iv4 = node3;
            int iM6860constructorimpl2 = NodeKind.m6860constructorimpl(i);
            List ancestors = null;
            boolean value$iv$iv$iv$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4.getNode().getIsAttached();
            if (!value$iv$iv$iv$iv2) {
                InlineClassHelperKt.throwIllegalStateException(str);
            }
            Modifier.Node node$iv$iv$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4.getNode().getParent();
            LayoutNode layout$iv$iv$iv2 = DelegatableNodeKt.requireLayoutNode($this$ancestors_u2d6rFNWt0_u24default$iv4);
            while (layout$iv$iv$iv2 != null) {
                Modifier.Node head$iv$iv$iv2 = layout$iv$iv$iv2.getNodes().getHead();
                if ((head$iv$iv$iv2.getAggregateChildKindSet() & iM6860constructorimpl2) != 0) {
                    while (node$iv$iv$iv2 != null) {
                        if ((node$iv$iv$iv2.getKindSet() & iM6860constructorimpl2) != 0) {
                            Modifier.Node it$iv$iv2 = node$iv$iv$iv2;
                            MutableVector mutableVector3 = null;
                            Modifier.Node nodePop2 = it$iv$iv2;
                            while (nodePop2 != null) {
                                node3 = node3;
                                if (nodePop2 instanceof IndirectPointerInputModifierNode) {
                                    Modifier.Node node4 = nodePop2;
                                    if (ancestors == null) {
                                        Object result$iv = new ArrayList();
                                        ancestors = (List) result$iv;
                                    }
                                    ancestors.add(node4);
                                    i4 = i9;
                                    $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                    focusedIndirectPointerInputNode6 = focusedIndirectPointerInputNode;
                                } else {
                                    Modifier.Node this_$iv$iv$iv$iv3 = nodePop2;
                                    if (((this_$iv$iv$iv$iv3.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) == 0 || !(nodePop2 instanceof DelegatingNode)) {
                                        i4 = i9;
                                        $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                        focusedIndirectPointerInputNode6 = focusedIndirectPointerInputNode;
                                    } else {
                                        int count$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv4 = (DelegatingNode) nodePop2;
                                        Modifier.Node node$iv$iv$iv$iv2 = this_$iv$iv$iv$iv4.getDelegate();
                                        while (node$iv$iv$iv$iv2 != null) {
                                            Modifier.Node next$iv$iv$iv2 = node$iv$iv$iv$iv2;
                                            if (((next$iv$iv$iv2.getKindSet() & iM6860constructorimpl2) != 0 ? 1 : 0) != 0) {
                                                count$iv$iv$iv2++;
                                                i5 = i9;
                                                if (count$iv$iv$iv2 == 1) {
                                                    nodePop2 = next$iv$iv$iv2;
                                                    $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                                    focusedIndirectPointerInputNode = focusedIndirectPointerInputNode;
                                                } else {
                                                    MutableVector mutableVector4 = mutableVector3 == null ? new MutableVector(new Modifier.Node[16], 0) : mutableVector3;
                                                    Modifier.Node theNode$iv$iv$iv2 = nodePop2;
                                                    if (theNode$iv$iv$iv2 != null) {
                                                        if (mutableVector4 != null) {
                                                            mutableVector4.add(theNode$iv$iv$iv2);
                                                        }
                                                        nodePop2 = null;
                                                    }
                                                    if (mutableVector4 != null) {
                                                        mutableVector4.add(next$iv$iv$iv2);
                                                    }
                                                    mutableVector3 = mutableVector4;
                                                    count$iv$iv$iv2 = count$iv$iv$iv2;
                                                }
                                            } else {
                                                i5 = i9;
                                                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                                focusedIndirectPointerInputNode = focusedIndirectPointerInputNode;
                                            }
                                            node$iv$iv$iv$iv2 = node$iv$iv$iv$iv2.getChild();
                                            i9 = i5;
                                            $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                            focusedIndirectPointerInputNode = focusedIndirectPointerInputNode;
                                        }
                                        i4 = i9;
                                        $this$ancestors_u2d6rFNWt0_u24default$iv3 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                                        focusedIndirectPointerInputNode6 = focusedIndirectPointerInputNode;
                                        if (count$iv$iv$iv2 == 1) {
                                            i9 = i4;
                                            $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                                            focusedIndirectPointerInputNode = focusedIndirectPointerInputNode6;
                                        }
                                    }
                                }
                                nodePop2 = DelegatableNodeKt.pop(mutableVector3);
                                i9 = i4;
                                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv3;
                                focusedIndirectPointerInputNode = focusedIndirectPointerInputNode6;
                            }
                            node2 = node3;
                            i3 = i9;
                            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                            focusedIndirectPointerInputNode4 = focusedIndirectPointerInputNode;
                            focusedIndirectPointerInputNode5 = null;
                        } else {
                            node2 = node3;
                            i3 = i9;
                            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                            focusedIndirectPointerInputNode4 = focusedIndirectPointerInputNode;
                            focusedIndirectPointerInputNode5 = indirectPointerInputModifierNode;
                        }
                        node$iv$iv$iv2 = node$iv$iv$iv2.getParent();
                        indirectPointerInputModifierNode = focusedIndirectPointerInputNode5;
                        node3 = node2;
                        i9 = i3;
                        $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                        focusedIndirectPointerInputNode = focusedIndirectPointerInputNode4;
                    }
                    node = node3;
                    i2 = i9;
                    $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                    focusedIndirectPointerInputNode2 = focusedIndirectPointerInputNode;
                    focusedIndirectPointerInputNode3 = indirectPointerInputModifierNode;
                } else {
                    node = node3;
                    i2 = i9;
                    $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv4;
                    focusedIndirectPointerInputNode2 = focusedIndirectPointerInputNode;
                    focusedIndirectPointerInputNode3 = indirectPointerInputModifierNode;
                }
                layout$iv$iv$iv2 = layout$iv$iv$iv2.getParent$ui();
                node$iv$iv$iv2 = (layout$iv$iv$iv2 == null || (nodes = layout$iv$iv$iv2.getNodes()) == null) ? null : nodes.getTail();
                indirectPointerInputModifierNode = focusedIndirectPointerInputNode3;
                node3 = node;
                i9 = i2;
                $this$ancestors_u2d6rFNWt0_u24default$iv4 = $this$ancestors_u2d6rFNWt0_u24default$iv;
                focusedIndirectPointerInputNode = focusedIndirectPointerInputNode2;
            }
            node3.onCancelIndirectPointerInput();
            if (ancestors != null) {
                List $this$fastForEach$iv = ancestors;
                int size = $this$fastForEach$iv.size();
                for (int index$iv = 0; index$iv < size; index$iv++) {
                    Object item$iv = $this$fastForEach$iv.get(index$iv);
                    IndirectPointerInputModifierNode it = (IndirectPointerInputModifierNode) item$iv;
                    it.onCancelIndirectPointerInput();
                }
            }
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void focusTargetAvailable() {
        this.platformFocusOwner.focusTargetAvailable();
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusTargetNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidation(FocusEventModifierNode node) {
        this.focusInvalidationManager.scheduleInvalidation(node);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void scheduleInvalidationForOwner() {
        this.focusInvalidationManager.scheduleInvalidation();
    }

    /* JADX INFO: renamed from: traverseAncestorsIncludingSelf-QFhIj7k, reason: not valid java name */
    private final /* synthetic */ <T extends DelegatableNode> void m4752traverseAncestorsIncludingSelfQFhIj7k(DelegatableNode $this$traverseAncestorsIncludingSelf_u2dQFhIj7k, int i, Function1<? super T, Unit> function1, Function0<Unit> function0, Function1<? super T, Unit> function2) {
        String str;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
        String str2;
        List ancestors;
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2;
        int size;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv;
        boolean includeSelf$iv;
        int i2;
        NodeChain nodes;
        boolean includeSelf$iv2;
        int i3;
        DelegatingNode this_$iv$iv$iv$iv;
        function1 = function1;
        int i4 = 0;
        int i5 = i;
        DelegatableNode $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k;
        boolean includeSelf$iv3 = false;
        int i6 = 0;
        Object result$iv = null;
        boolean value$iv$iv$iv$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2.getNode().getIsAttached();
        if (!value$iv$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node$iv$iv$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2.getNode().getParent();
        LayoutNode layout$iv$iv$iv = DelegatableNodeKt.requireLayoutNode($this$ancestors_u2d6rFNWt0_u24default$iv2);
        while (true) {
            int i7 = i4;
            str = ExifInterface.GPS_DIRECTION_TRUE;
            int i8 = i5;
            if (layout$iv$iv$iv == null) {
                break;
            }
            Modifier.Node head$iv$iv$iv = layout$iv$iv$iv.getNodes().getHead();
            if ((head$iv$iv$iv.getAggregateChildKindSet() & i5) != 0) {
                while (node$iv$iv$iv != null) {
                    if ((node$iv$iv$iv.getKindSet() & i5) != 0) {
                        Modifier.Node it$iv$iv = node$iv$iv$iv;
                        Object stack$iv$iv$iv = null;
                        Modifier.Node this_$iv$iv$iv$iv2 = it$iv$iv;
                        while (this_$iv$iv$iv$iv2 != null) {
                            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (this_$iv$iv$iv$iv2 instanceof Object) {
                                Modifier.Node node = this_$iv$iv$iv$iv2;
                                if (result$iv == null) {
                                    Object result$iv2 = new ArrayList();
                                    result$iv = (List) result$iv2;
                                }
                                includeSelf$iv2 = includeSelf$iv3;
                                ((List) result$iv).add(node);
                                i3 = i6;
                            } else {
                                includeSelf$iv2 = includeSelf$iv3;
                                if (((this_$iv$iv$iv$iv2.getKindSet() & i5) != 0) && (this_$iv$iv$iv$iv2 instanceof DelegatingNode)) {
                                    int count$iv$iv$iv = 0;
                                    DelegatingNode this_$iv$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv$iv2;
                                    Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv3.getDelegate();
                                    while (node$iv$iv$iv$iv != null) {
                                        Modifier.Node node2 = this_$iv$iv$iv$iv2;
                                        Object node$iv$iv$iv2 = node$iv$iv$iv$iv;
                                        Modifier.Node next$iv$iv$iv = (Modifier.Node) node$iv$iv$iv2;
                                        if (((next$iv$iv$iv.getKindSet() & i5) != 0 ? 1 : 0) != 0) {
                                            count$iv$iv$iv++;
                                            this_$iv$iv$iv$iv = this_$iv$iv$iv$iv3;
                                            if (count$iv$iv$iv == 1) {
                                                node2 = next$iv$iv$iv;
                                                i6 = i6;
                                            } else {
                                                Object node$iv$iv$iv3 = stack$iv$iv$iv;
                                                Object mutableVector = (MutableVector) node$iv$iv$iv3;
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                stack$iv$iv$iv = mutableVector;
                                                Modifier.Node theNode$iv$iv$iv = node2;
                                                if (theNode$iv$iv$iv != null) {
                                                    MutableVector mutableVector2 = (MutableVector) stack$iv$iv$iv;
                                                    if (mutableVector2 != null) {
                                                        mutableVector2.add(theNode$iv$iv$iv);
                                                    }
                                                    node2 = null;
                                                }
                                                MutableVector mutableVector3 = (MutableVector) stack$iv$iv$iv;
                                                if (mutableVector3 != null) {
                                                    mutableVector3.add(next$iv$iv$iv);
                                                }
                                                count$iv$iv$iv = count$iv$iv$iv;
                                            }
                                        } else {
                                            this_$iv$iv$iv$iv = this_$iv$iv$iv$iv3;
                                            i6 = i6;
                                        }
                                        node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                        this_$iv$iv$iv$iv2 = node2;
                                        this_$iv$iv$iv$iv3 = this_$iv$iv$iv$iv;
                                        i6 = i6;
                                    }
                                    Modifier.Node node3 = this_$iv$iv$iv$iv2;
                                    i3 = i6;
                                    if (count$iv$iv$iv == 1) {
                                        includeSelf$iv3 = includeSelf$iv2;
                                        this_$iv$iv$iv$iv2 = node3;
                                        i6 = i3;
                                    }
                                } else {
                                    i3 = i6;
                                }
                            }
                            this_$iv$iv$iv$iv2 = DelegatableNodeKt.pop((MutableVector) stack$iv$iv$iv);
                            includeSelf$iv3 = includeSelf$iv2;
                            i6 = i3;
                        }
                    }
                    node$iv$iv$iv = node$iv$iv$iv.getParent();
                    $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                    includeSelf$iv3 = includeSelf$iv3;
                    i6 = i6;
                }
                $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
                i2 = i6;
            } else {
                $this$ancestors_u2d6rFNWt0_u24default$iv = $this$ancestors_u2d6rFNWt0_u24default$iv2;
                includeSelf$iv = includeSelf$iv3;
                i2 = i6;
            }
            layout$iv$iv$iv = layout$iv$iv$iv.getParent$ui();
            node$iv$iv$iv = (layout$iv$iv$iv == null || (nodes = layout$iv$iv$iv.getNodes()) == null) ? null : nodes.getTail();
            i4 = i7;
            i5 = i8;
            $this$ancestors_u2d6rFNWt0_u24default$iv2 = $this$ancestors_u2d6rFNWt0_u24default$iv;
            includeSelf$iv3 = includeSelf$iv;
            i6 = i2;
        }
        List ancestors2 = (List) result$iv;
        if (ancestors2 != null && (size = ancestors2.size() - 1) >= 0) {
            do {
                int index$iv = size;
                size--;
                Object item$iv = ancestors2.get(index$iv);
                function1.invoke(item$iv);
            } while (size >= 0);
        }
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k.getNode();
        Object stack$iv = null;
        Modifier.Node node$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
        while (node$iv != null) {
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (node$iv instanceof Object) {
                function1.invoke(node$iv);
                ancestors = ancestors2;
                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
            } else {
                Modifier.Node this_$iv$iv = node$iv;
                if (((this_$iv$iv.getKindSet() & i) != 0) && (node$iv instanceof DelegatingNode)) {
                    int count$iv = 0;
                    DelegatingNode this_$iv$iv2 = (DelegatingNode) node$iv;
                    Modifier.Node node$iv$iv = this_$iv$iv2.getDelegate();
                    while (node$iv$iv != null) {
                        Modifier.Node next$iv = node$iv$iv;
                        if (((next$iv.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                            count$iv++;
                            if (count$iv == 1) {
                                node$iv = next$iv;
                                ancestors2 = ancestors2;
                                $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                            } else {
                                Object obj = (MutableVector) stack$iv;
                                if (obj == null) {
                                    Object mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                    obj = mutableVector4;
                                }
                                stack$iv = obj;
                                Modifier.Node theNode$iv = node$iv;
                                if (theNode$iv != null) {
                                    MutableVector mutableVector5 = (MutableVector) stack$iv;
                                    if (mutableVector5 != null) {
                                        mutableVector5.add(theNode$iv);
                                    }
                                    node$iv = null;
                                }
                                MutableVector mutableVector6 = (MutableVector) stack$iv;
                                if (mutableVector6 != null) {
                                    mutableVector6.add(next$iv);
                                }
                            }
                        } else {
                            ancestors2 = ancestors2;
                            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        ancestors2 = ancestors2;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    }
                    ancestors = ancestors2;
                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                    if (count$iv == 1) {
                        ancestors2 = ancestors;
                        $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                    }
                } else {
                    ancestors = ancestors2;
                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                }
            }
            node$iv = DelegatableNodeKt.pop((MutableVector) stack$iv);
            ancestors2 = ancestors;
            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
        }
        List ancestors3 = ancestors2;
        function0.invoke();
        Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$traverseAncestorsIncludingSelf_u2dQFhIj7k.getNode();
        Object stack$iv2 = null;
        Modifier.Node node$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv4;
        while (node$iv2 != null) {
            Intrinsics.reifiedOperationMarker(3, str);
            if (node$iv2 instanceof Object) {
                function2.invoke(node$iv2);
                $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv4;
                str2 = str;
            } else {
                Modifier.Node this_$iv$iv3 = node$iv2;
                if (((this_$iv$iv3.getKindSet() & i) != 0) && (node$iv2 instanceof DelegatingNode)) {
                    int count$iv2 = 0;
                    DelegatingNode this_$iv$iv4 = (DelegatingNode) node$iv2;
                    Modifier.Node node$iv$iv2 = this_$iv$iv4.getDelegate();
                    while (node$iv$iv2 != null) {
                        Modifier.Node next$iv2 = node$iv$iv2;
                        if (((next$iv2.getKindSet() & i) != 0 ? 1 : 0) != 0) {
                            count$iv2++;
                            if (count$iv2 == 1) {
                                node$iv2 = next$iv2;
                                $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                                str = str;
                            } else {
                                Object obj2 = (MutableVector) stack$iv2;
                                if (obj2 == null) {
                                    Object mutableVector7 = new MutableVector(new Modifier.Node[16], 0);
                                    obj2 = mutableVector7;
                                }
                                stack$iv2 = obj2;
                                Modifier.Node theNode$iv2 = node$iv2;
                                if (theNode$iv2 != null) {
                                    MutableVector mutableVector8 = (MutableVector) stack$iv2;
                                    if (mutableVector8 != null) {
                                        mutableVector8.add(theNode$iv2);
                                    }
                                    node$iv2 = null;
                                }
                                MutableVector mutableVector9 = (MutableVector) stack$iv2;
                                if (mutableVector9 != null) {
                                    mutableVector9.add(next$iv2);
                                }
                            }
                        } else {
                            $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                            str = str;
                        }
                        node$iv$iv2 = node$iv$iv2.getChild();
                        $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv4;
                        str = str;
                    }
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv4;
                    str2 = str;
                    if (count$iv2 == 1) {
                        $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        str = str2;
                    }
                } else {
                    $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv4;
                    str2 = str;
                }
            }
            node$iv2 = DelegatableNodeKt.pop((MutableVector) stack$iv2);
            $this$dispatchForKind_u2d6rFNWt0$iv4 = $this$dispatchForKind_u2d6rFNWt0$iv;
            str = str2;
        }
        if (ancestors3 != null) {
            int size2 = ancestors3.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = ancestors3.get(index$iv2);
                function2.invoke(item$iv2);
            }
        }
    }

    /* JADX INFO: renamed from: nearestAncestorIncludingSelf-64DMado, reason: not valid java name */
    private final /* synthetic */ <T> T m4751nearestAncestorIncludingSelf64DMado(DelegatableNode delegatableNode, int i) {
        int i2;
        boolean z;
        DelegatableNode delegatableNode2;
        int i3;
        int i4;
        Modifier.Node node;
        NodeChain nodes;
        int i5;
        boolean z2;
        DelegatableNode delegatableNode3;
        int i6;
        int i7;
        DelegatableNode delegatableNode4;
        int i8;
        int i9;
        DelegatableNode delegatableNode5;
        int i10 = 0;
        boolean z3 = true;
        DelegatableNode delegatableNode6 = delegatableNode;
        int i11 = i;
        int i12 = 0;
        if (!delegatableNode6.getNode().getIsAttached()) {
            InlineClassHelperKt.throwIllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = delegatableNode6.getNode();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(delegatableNode6);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & i11) != 0) {
                while (node2 != null) {
                    if ((node2.getKindSet() & i11) != 0) {
                        int i13 = i11;
                        Object obj = null;
                        Modifier.Node nodePop = node2;
                        while (nodePop != null) {
                            i10 = i10;
                            z3 = z3;
                            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                            if (nodePop instanceof Object) {
                                return (T) nodePop;
                            }
                            if (((nodePop.getKindSet() & i13) != 0 ? 1 : 0) == 0 || !(nodePop instanceof DelegatingNode)) {
                                delegatableNode4 = delegatableNode6;
                                i8 = i11;
                                i9 = i12;
                                nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                                delegatableNode6 = delegatableNode4;
                                i11 = i8;
                                i12 = i9;
                            } else {
                                int i14 = 0;
                                Modifier.Node delegate = ((DelegatingNode) nodePop).getDelegate();
                                while (delegate != null) {
                                    Modifier.Node node3 = delegate;
                                    if (((node3.getKindSet() & i13) != 0 ? 1 : 0) != 0) {
                                        i14++;
                                        delegatableNode5 = delegatableNode6;
                                        if (i14 == 1) {
                                            nodePop = node3;
                                            i11 = i11;
                                            i12 = i12;
                                        } else {
                                            MutableVector mutableVector = (MutableVector) obj;
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            obj = mutableVector;
                                            Modifier.Node node4 = nodePop;
                                            if (node4 != null) {
                                                MutableVector mutableVector2 = (MutableVector) obj;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(node4);
                                                }
                                                nodePop = null;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) obj;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(node3);
                                            }
                                            i14 = i14;
                                        }
                                    } else {
                                        delegatableNode5 = delegatableNode6;
                                        i11 = i11;
                                        i12 = i12;
                                    }
                                    delegate = delegate.getChild();
                                    delegatableNode6 = delegatableNode5;
                                    i11 = i11;
                                    i12 = i12;
                                }
                                delegatableNode4 = delegatableNode6;
                                i8 = i11;
                                i9 = i12;
                                if (i14 == 1) {
                                    delegatableNode6 = delegatableNode4;
                                    i11 = i8;
                                    i12 = i9;
                                } else {
                                    nodePop = DelegatableNodeKt.pop((MutableVector) obj);
                                    delegatableNode6 = delegatableNode4;
                                    i11 = i8;
                                    i12 = i9;
                                }
                            }
                        }
                        i5 = i10;
                        z2 = z3;
                        delegatableNode3 = delegatableNode6;
                        i6 = i11;
                        i7 = i12;
                    } else {
                        i5 = i10;
                        z2 = z3;
                        delegatableNode3 = delegatableNode6;
                        i6 = i11;
                        i7 = i12;
                    }
                    node2 = node2.getParent();
                    i10 = i5;
                    z3 = z2;
                    delegatableNode6 = delegatableNode3;
                    i11 = i6;
                    i12 = i7;
                }
                i2 = i10;
                z = z3;
                delegatableNode2 = delegatableNode6;
                i3 = i11;
                i4 = i12;
                node = null;
            } else {
                i2 = i10;
                z = z3;
                delegatableNode2 = delegatableNode6;
                i3 = i11;
                i4 = i12;
                node = null;
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui();
            node2 = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? node : nodes.getTail();
            i10 = i2;
            z3 = z;
            delegatableNode6 = delegatableNode2;
            i11 = i3;
            i12 = i4;
        }
        return null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public Rect getFocusRect() {
        FocusTargetNode focusTargetNodeFindFocusTargetNode = findFocusTargetNode();
        if (focusTargetNodeFindFocusTargetNode != null) {
            return FocusTraversalKt.focusRect(focusTargetNodeFindFocusTargetNode);
        }
        return null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean hasFocusableContent() {
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
        boolean z;
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
        boolean z2 = false;
        if (!this.rootFocusNode.getIsAttached()) {
            return false;
        }
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = this.rootFocusNode;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
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
                    if (node$iv$iv != null && node$iv$iv.getIsAttached()) {
                        if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                            Modifier.Node it$iv = node$iv$iv;
                            MutableVector mutableVector = null;
                            Modifier.Node nodePop = it$iv;
                            while (nodePop != null) {
                                boolean z4 = z3;
                                if (nodePop instanceof FocusTargetNode) {
                                    FocusTargetNode it = (FocusTargetNode) nodePop;
                                    if (it.getIsAttached() && it.fetchFocusProperties$ui().getCanFocus()) {
                                        return z4;
                                    }
                                    $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                } else {
                                    Modifier.Node this_$iv$iv$iv = nodePop;
                                    if (((this_$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? z4 : false) && (nodePop instanceof DelegatingNode)) {
                                        int count$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if ((next$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? z4 : false) {
                                                count$iv$iv++;
                                                if (count$iv$iv == z4) {
                                                    nodePop = next$iv$iv;
                                                    $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
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
                                                        nodePop = nodePop;
                                                        mutableVector2.add(next$iv$iv);
                                                    } else {
                                                        nodePop = nodePop;
                                                    }
                                                    mutableVector = mutableVector2;
                                                }
                                            } else {
                                                $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                            z4 = true;
                                        }
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                        Modifier.Node node = nodePop;
                                        if (count$iv$iv == 1) {
                                            z3 = true;
                                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                                            nodePop = node;
                                        }
                                    } else {
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                                z3 = true;
                                $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                            }
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                            z = true;
                            if (1 == 0) {
                                $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
                                z2 = false;
                                break;
                            }
                        } else {
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                            z = z3;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        z3 = z;
                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
                    }
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
            z2 = false;
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public boolean hasNonInteropFocusableContent() {
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
        boolean z;
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
        boolean z2 = false;
        if (!this.rootFocusNode.getIsAttached()) {
            return false;
        }
        DelegatableNode $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = this.rootFocusNode;
        int iM6860constructorimpl = NodeKind.m6860constructorimpl(1024);
        boolean value$iv$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector branches$iv$iv = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child$iv$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3.getNode().getChild();
        if (child$iv$iv == null) {
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3.getNode(), false);
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
                    if (node$iv$iv != null && node$iv$iv.getIsAttached()) {
                        if ((node$iv$iv.getKindSet() & iM6860constructorimpl) != 0) {
                            Modifier.Node it$iv = node$iv$iv;
                            MutableVector mutableVector = null;
                            Modifier.Node nodePop = it$iv;
                            while (nodePop != null) {
                                boolean z4 = z3;
                                if (nodePop instanceof FocusTargetNode) {
                                    FocusTargetNode it = (FocusTargetNode) nodePop;
                                    if (it.getIsAttached()) {
                                        FocusProperties focusProperties = it.fetchFocusProperties$ui();
                                        if (it.getIsAttached() && !it.getIsInteropViewHost() && focusProperties.getCanFocus()) {
                                            return z4;
                                        }
                                    }
                                    $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                } else {
                                    Modifier.Node this_$iv$iv$iv = nodePop;
                                    if (((this_$iv$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? z4 : false) && (nodePop instanceof DelegatingNode)) {
                                        int count$iv$iv = 0;
                                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                                            if ((next$iv$iv.getKindSet() & iM6860constructorimpl) != 0 ? z4 : false) {
                                                count$iv$iv++;
                                                if (count$iv$iv == z4) {
                                                    nodePop = next$iv$iv;
                                                    $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
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
                                                        nodePop = nodePop;
                                                        mutableVector2.add(next$iv$iv);
                                                    } else {
                                                        nodePop = nodePop;
                                                    }
                                                    mutableVector = mutableVector2;
                                                }
                                            } else {
                                                $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                            }
                                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                            z4 = true;
                                        }
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                        Modifier.Node node = nodePop;
                                        if (count$iv$iv == 1) {
                                            z3 = true;
                                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                                            nodePop = node;
                                        }
                                    } else {
                                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector);
                                z3 = true;
                                $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv2;
                            }
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                            z = true;
                            if (1 == 0) {
                                $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
                                z2 = false;
                                break;
                            }
                        } else {
                            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
                            z = z3;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        z3 = z;
                        $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv;
                    }
                }
            }
            DelegatableNodeKt.addLayoutNodeChildren(branches$iv$iv, branch$iv$iv, false);
            $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3 = $this$visitSubtree_u2dY_u2dYKmho_u24default$iv3;
            z2 = false;
        }
    }

    private final FocusTargetNode findFocusTargetNode() {
        return FocusTraversalKt.findActiveFocusNode(this.rootFocusNode);
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public FocusState getRootState() {
        return this.rootFocusNode.getFocusState();
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public MutableObjectList<FocusListener> getListeners() {
        return this.listeners;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public FocusTargetNode getActiveFocusTargetNode() {
        FocusTargetNode focusTargetNode = this.activeFocusTargetNode;
        boolean z = false;
        if (focusTargetNode != null && focusTargetNode.getIsAttached()) {
            z = true;
        }
        if (z) {
            return this.activeFocusTargetNode;
        }
        return null;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setActiveFocusTargetNode(FocusTargetNode value) {
        FocusTargetNode previousValue = this.activeFocusTargetNode;
        this.activeFocusTargetNode = value;
        if (value == null || previousValue != value) {
            setFocusCaptured(false);
        }
        if (ComposeUiFlags.isSemanticAutofillEnabled) {
            ObjectList this_$iv = getListeners();
            Object[] content$iv = this_$iv.content;
            int i = this_$iv._size;
            for (int i$iv = 0; i$iv < i; i$iv++) {
                FocusListener it = (FocusListener) content$iv[i$iv];
                it.onFocusChanged(previousValue, value);
            }
        }
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    /* JADX INFO: renamed from: isFocusCaptured, reason: from getter */
    public boolean getIsFocusCaptured() {
        return this.isFocusCaptured;
    }

    @Override // androidx.compose.ui.focus.FocusOwner
    public void setFocusCaptured(boolean value) {
        boolean value$iv = (value && getActiveFocusTargetNode() == null) ? false : true;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Cannot capture focus when the active focus target node is unset");
        }
        this.isFocusCaptured = value;
    }

    private final Modifier.Node lastLocalKeyInputNode(DelegatableNode $this$lastLocalKeyInputNode) {
        Modifier.Node node = null;
        int mask$iv = NodeKind.m6860constructorimpl(1024) | NodeKind.m6860constructorimpl(8192);
        boolean value$iv$iv$iv = $this$lastLocalKeyInputNode.getNode().getIsAttached();
        if (!value$iv$iv$iv) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node self$iv$iv = $this$lastLocalKeyInputNode.getNode();
        if ((self$iv$iv.getAggregateChildKindSet() & mask$iv) != 0) {
            for (Modifier.Node next$iv$iv = self$iv$iv.getChild(); next$iv$iv != null; next$iv$iv = next$iv$iv.getChild()) {
                if ((next$iv$iv.getKindSet() & mask$iv) != 0) {
                    Modifier.Node modifierNode = next$iv$iv;
                    if ((modifierNode.getKindSet() & NodeKind.m6860constructorimpl(1024)) != 0) {
                        return node;
                    }
                    node = modifierNode;
                }
            }
        }
        return node;
    }

    /* JADX INFO: renamed from: validateKeyEvent-ZmokQxo, reason: not valid java name */
    private final boolean m4753validateKeyEventZmokQxo(KeyEvent keyEvent) {
        long keyCode = KeyEvent_androidKt.m6253getKeyZmokQxo(keyEvent);
        int iM6254getTypeZmokQxo = KeyEvent_androidKt.m6254getTypeZmokQxo(keyEvent);
        if (KeyEventType.m6246equalsimpl0(iM6254getTypeZmokQxo, KeyEventType.INSTANCE.m6250getKeyDownCS__XNY())) {
            MutableLongSet it = this.keysCurrentlyDown;
            if (it == null) {
                it = new MutableLongSet(3);
                this.keysCurrentlyDown = it;
            }
            it.plusAssign(keyCode);
        } else if (KeyEventType.m6246equalsimpl0(iM6254getTypeZmokQxo, KeyEventType.INSTANCE.m6251getKeyUpCS__XNY())) {
            MutableLongSet mutableLongSet = this.keysCurrentlyDown;
            if (!(mutableLongSet != null && mutableLongSet.contains(keyCode))) {
                return false;
            }
            MutableLongSet mutableLongSet2 = this.keysCurrentlyDown;
            if (mutableLongSet2 != null) {
                mutableLongSet2.remove(keyCode);
            }
        }
        return true;
    }
}
