package androidx.compose.material3.internal;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityManager$AccessibilityServicesStateChangeListener;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AccessibilityServiceStateProvider.android.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006*\u0002\u0014\u0017\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001(B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0003H\u0016J\u000e\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001aJ\u000e\u0010'\u001a\u00020#2\u0006\u0010&\u001a\u00020\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR+\u0010\r\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00038B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u0018\u0010\u0019\u001a\u00020\u0003*\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u00020\u0003*\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006)"}, d2 = {"Landroidx/compose/material3/internal/Listener;", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "Landroidx/compose/runtime/State;", "", "listenToTouchExplorationState", "listenToSwitchAccessState", "listenToVoiceAccessState", "<init>", "(ZZZ)V", "getListenToSwitchAccessState", "()Z", "getListenToVoiceAccessState", "<set-?>", "accessibilityEnabled", "getAccessibilityEnabled", "setAccessibilityEnabled", "(Z)V", "accessibilityEnabled$delegate", "Landroidx/compose/runtime/MutableState;", "touchExplorationListener", "androidx/compose/material3/internal/Listener$touchExplorationListener$1", "Landroidx/compose/material3/internal/Listener$touchExplorationListener$1;", "otherA11yServicesListener", "androidx/compose/material3/internal/Listener$otherA11yServicesListener$1", "Landroidx/compose/material3/internal/Listener$otherA11yServicesListener$1;", "switchAccessEnabled", "Landroid/view/accessibility/AccessibilityManager;", "getSwitchAccessEnabled", "(Landroid/view/accessibility/AccessibilityManager;)Z", "voiceAccessEnabled", "getVoiceAccessEnabled", "value", "getValue", "()Ljava/lang/Boolean;", "onAccessibilityStateChanged", "", "enabled", "register", "am", "unregister", "Api33Impl", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class Listener implements AccessibilityManager.AccessibilityStateChangeListener, State<Boolean> {

    /* JADX INFO: renamed from: accessibilityEnabled$delegate, reason: from kotlin metadata */
    private final MutableState accessibilityEnabled = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    private final boolean listenToSwitchAccessState;
    private final boolean listenToVoiceAccessState;
    private final Listener$otherA11yServicesListener$1 otherA11yServicesListener;
    private final Listener$touchExplorationListener$1 touchExplorationListener;

    public Listener(boolean listenToTouchExplorationState, boolean listenToSwitchAccessState, boolean listenToVoiceAccessState) {
        Listener$touchExplorationListener$1 listener$touchExplorationListener$1;
        this.listenToSwitchAccessState = listenToSwitchAccessState;
        this.listenToVoiceAccessState = listenToVoiceAccessState;
        Listener$otherA11yServicesListener$1 listener$otherA11yServicesListener$1 = null;
        if (listenToTouchExplorationState) {
            listener$touchExplorationListener$1 = new Listener$touchExplorationListener$1();
        } else {
            listener$touchExplorationListener$1 = null;
        }
        this.touchExplorationListener = listener$touchExplorationListener$1;
        if ((this.listenToSwitchAccessState || this.listenToVoiceAccessState) && Build.VERSION.SDK_INT >= 33) {
            listener$otherA11yServicesListener$1 = new Listener$otherA11yServicesListener$1(this);
        }
        this.otherA11yServicesListener = listener$otherA11yServicesListener$1;
    }

    public final boolean getListenToSwitchAccessState() {
        return this.listenToSwitchAccessState;
    }

    public final boolean getListenToVoiceAccessState() {
        return this.listenToVoiceAccessState;
    }

    private final boolean getAccessibilityEnabled() {
        State $this$getValue$iv = this.accessibilityEnabled;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    private final void setAccessibilityEnabled(boolean z) {
        MutableState $this$setValue$iv = this.accessibilityEnabled;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSwitchAccessEnabled(AccessibilityManager $this$switchAccessEnabled) {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = $this$switchAccessEnabled.getEnabledAccessibilityServiceList(16);
        int index$iv$iv = 0;
        int size = enabledAccessibilityServiceList.size();
        while (true) {
            boolean z = false;
            if (index$iv$iv >= size) {
                return false;
            }
            Object item$iv$iv = enabledAccessibilityServiceList.get(index$iv$iv);
            AccessibilityServiceInfo it = (AccessibilityServiceInfo) item$iv$iv;
            String settingsActivityName = it.getSettingsActivityName();
            if (settingsActivityName != null && StringsKt.contains((CharSequence) settingsActivityName, (CharSequence) "SwitchAccess", true)) {
                z = true;
            }
            if (z) {
                return true;
            }
            index$iv$iv++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getVoiceAccessEnabled(AccessibilityManager $this$voiceAccessEnabled) {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = $this$voiceAccessEnabled.getEnabledAccessibilityServiceList(16);
        int index$iv$iv = 0;
        int size = enabledAccessibilityServiceList.size();
        while (true) {
            boolean z = false;
            if (index$iv$iv >= size) {
                return false;
            }
            Object item$iv$iv = enabledAccessibilityServiceList.get(index$iv$iv);
            AccessibilityServiceInfo it = (AccessibilityServiceInfo) item$iv$iv;
            String settingsActivityName = it.getSettingsActivityName();
            if (settingsActivityName != null && StringsKt.contains((CharSequence) settingsActivityName, (CharSequence) "VoiceAccess", true)) {
                z = true;
            }
            if (z) {
                return true;
            }
            index$iv$iv++;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Code duplicated, block: B:20:0x002a  */
    /* JADX WARN: Code duplicated, block: B:22:0x002e  */
    /* JADX WARN: Code duplicated, block: B:27:0x003a  */
    /* JADX WARN: Code duplicated, block: B:29:0x003d  */
    @Override // androidx.compose.runtime.State
    public Boolean getValue() {
        Listener$otherA11yServicesListener$1 listener$otherA11yServicesListener$1;
        boolean z;
        boolean z2 = false;
        if (getAccessibilityEnabled()) {
            Listener$touchExplorationListener$1 listener$touchExplorationListener$1 = this.touchExplorationListener;
            if (listener$touchExplorationListener$1 != null && listener$touchExplorationListener$1.getEnabled()) {
                z2 = true;
            } else if (this.listenToSwitchAccessState) {
                Listener$otherA11yServicesListener$1 listener$otherA11yServicesListener$2 = this.otherA11yServicesListener;
                if (listener$otherA11yServicesListener$2 != null && listener$otherA11yServicesListener$2.getSwitchAccessEnabled()) {
                    z2 = true;
                } else if (this.listenToVoiceAccessState) {
                    listener$otherA11yServicesListener$1 = this.otherA11yServicesListener;
                    if (listener$otherA11yServicesListener$1 == null) {
                        z = false;
                    } else {
                        z = false;
                    }
                    if (z) {
                        z2 = true;
                    }
                }
            } else if (this.listenToVoiceAccessState) {
                listener$otherA11yServicesListener$1 = this.otherA11yServicesListener;
                if (listener$otherA11yServicesListener$1 == null && listener$otherA11yServicesListener$1.getVoiceAccessEnabled()) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    z2 = true;
                }
            }
        }
        return Boolean.valueOf(z2);
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean enabled) {
        setAccessibilityEnabled(enabled);
    }

    public final void register(AccessibilityManager am) {
        Listener$otherA11yServicesListener$1 it;
        setAccessibilityEnabled(am.isEnabled());
        am.addAccessibilityStateChangeListener(this);
        Listener$touchExplorationListener$1 it2 = this.touchExplorationListener;
        if (it2 != null) {
            it2.setEnabled(am.isTouchExplorationEnabled());
            am.addTouchExplorationStateChangeListener(it2);
        }
        if (Build.VERSION.SDK_INT >= 33 && (it = this.otherA11yServicesListener) != null) {
            it.setSwitchAccessEnabled(getSwitchAccessEnabled(am));
            it.setVoiceAccessEnabled(getVoiceAccessEnabled(am));
            Api33Impl.addAccessibilityServicesStateChangeListener(am, it);
        }
    }

    public final void unregister(AccessibilityManager am) {
        Listener$otherA11yServicesListener$1 it;
        am.removeAccessibilityStateChangeListener(this);
        Listener$touchExplorationListener$1 it2 = this.touchExplorationListener;
        if (it2 != null) {
            am.removeTouchExplorationStateChangeListener(it2);
        }
        if (Build.VERSION.SDK_INT >= 33 && (it = this.otherA11yServicesListener) != null) {
            Api33Impl.removeAccessibilityServicesStateChangeListener(am, it);
        }
    }

    /* JADX INFO: compiled from: AccessibilityServiceStateProvider.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\u000b"}, d2 = {"Landroidx/compose/material3/internal/Listener$Api33Impl;", "", "<init>", "()V", "addAccessibilityServicesStateChangeListener", "", "am", "Landroid/view/accessibility/AccessibilityManager;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/accessibility/AccessibilityManager$AccessibilityServicesStateChangeListener;", "removeAccessibilityServicesStateChangeListener", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {
        }

        @JvmStatic
        public static final void addAccessibilityServicesStateChangeListener(AccessibilityManager am, AccessibilityManager$AccessibilityServicesStateChangeListener listener) {
            am.addAccessibilityServicesStateChangeListener(listener);
        }

        @JvmStatic
        public static final void removeAccessibilityServicesStateChangeListener(AccessibilityManager am, AccessibilityManager$AccessibilityServicesStateChangeListener listener) {
            am.removeAccessibilityServicesStateChangeListener(listener);
        }
    }
}
