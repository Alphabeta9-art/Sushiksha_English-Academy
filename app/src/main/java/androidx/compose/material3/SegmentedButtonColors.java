package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SegmentedButton.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0085\u0001\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b \u0010!J\u001f\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$H\u0001¢\u0006\u0004\b&\u0010'J\u001f\u0010(\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$2\u0006\u0010)\u001a\u00020$H\u0001¢\u0006\u0004\b*\u0010'J\u001f\u0010+\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$H\u0001¢\u0006\u0004\b,\u0010'J\u0013\u0010-\u001a\u00020$2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010/\u001a\u000200H\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0015\u0010\u0012R\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0016\u0010\u0012R\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0017\u0010\u0012R\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0018\u0010\u0012R\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0019\u0010\u0012R\u0013\u0010\n\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001a\u0010\u0012R\u0013\u0010\u000b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001b\u0010\u0012R\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001c\u0010\u0012R\u0013\u0010\r\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001d\u0010\u0012R\u0013\u0010\u000e\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001e\u0010\u0012¨\u00061"}, d2 = {"Landroidx/compose/material3/SegmentedButtonColors;", "", "activeContainerColor", "Landroidx/compose/ui/graphics/Color;", "activeContentColor", "activeBorderColor", "inactiveContainerColor", "inactiveContentColor", "inactiveBorderColor", "disabledActiveContainerColor", "disabledActiveContentColor", "disabledActiveBorderColor", "disabledInactiveContainerColor", "disabledInactiveContentColor", "disabledInactiveBorderColor", "<init>", "(JJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getActiveContainerColor-0d7_KjU", "()J", "J", "getActiveContentColor-0d7_KjU", "getActiveBorderColor-0d7_KjU", "getInactiveContainerColor-0d7_KjU", "getInactiveContentColor-0d7_KjU", "getInactiveBorderColor-0d7_KjU", "getDisabledActiveContainerColor-0d7_KjU", "getDisabledActiveContentColor-0d7_KjU", "getDisabledActiveBorderColor-0d7_KjU", "getDisabledInactiveContainerColor-0d7_KjU", "getDisabledInactiveContentColor-0d7_KjU", "getDisabledInactiveBorderColor-0d7_KjU", "copy", "copy-2qZNXz8", "(JJJJJJJJJJJJ)Landroidx/compose/material3/SegmentedButtonColors;", "borderColor", "enabled", "", "active", "borderColor-WaAFU9c$material3", "(ZZ)J", "contentColor", "checked", "contentColor-WaAFU9c$material3", "containerColor", "containerColor-WaAFU9c$material3", MetadataValidation.EQUALS, "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SegmentedButtonColors {
    public static final int $stable = 0;
    private final long activeBorderColor;
    private final long activeContainerColor;
    private final long activeContentColor;
    private final long disabledActiveBorderColor;
    private final long disabledActiveContainerColor;
    private final long disabledActiveContentColor;
    private final long disabledInactiveBorderColor;
    private final long disabledInactiveContainerColor;
    private final long disabledInactiveContentColor;
    private final long inactiveBorderColor;
    private final long inactiveContainerColor;
    private final long inactiveContentColor;

    public /* synthetic */ SegmentedButtonColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12);
    }

    private SegmentedButtonColors(long activeContainerColor, long activeContentColor, long activeBorderColor, long inactiveContainerColor, long inactiveContentColor, long inactiveBorderColor, long disabledActiveContainerColor, long disabledActiveContentColor, long disabledActiveBorderColor, long disabledInactiveContainerColor, long disabledInactiveContentColor, long disabledInactiveBorderColor) {
        this.activeContainerColor = activeContainerColor;
        this.activeContentColor = activeContentColor;
        this.activeBorderColor = activeBorderColor;
        this.inactiveContainerColor = inactiveContainerColor;
        this.inactiveContentColor = inactiveContentColor;
        this.inactiveBorderColor = inactiveBorderColor;
        this.disabledActiveContainerColor = disabledActiveContainerColor;
        this.disabledActiveContentColor = disabledActiveContentColor;
        this.disabledActiveBorderColor = disabledActiveBorderColor;
        this.disabledInactiveContainerColor = disabledInactiveContainerColor;
        this.disabledInactiveContentColor = disabledInactiveContentColor;
        this.disabledInactiveBorderColor = disabledInactiveBorderColor;
    }

    /* JADX INFO: renamed from: getActiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveContainerColor() {
        return this.activeContainerColor;
    }

    /* JADX INFO: renamed from: getActiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveContentColor() {
        return this.activeContentColor;
    }

    /* JADX INFO: renamed from: getActiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getActiveBorderColor() {
        return this.activeBorderColor;
    }

    /* JADX INFO: renamed from: getInactiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveContainerColor() {
        return this.inactiveContainerColor;
    }

    /* JADX INFO: renamed from: getInactiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveContentColor() {
        return this.inactiveContentColor;
    }

    /* JADX INFO: renamed from: getInactiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getInactiveBorderColor() {
        return this.inactiveBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveContainerColor() {
        return this.disabledActiveContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveContentColor() {
        return this.disabledActiveContentColor;
    }

    /* JADX INFO: renamed from: getDisabledActiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledActiveBorderColor() {
        return this.disabledActiveBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveContainerColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveContainerColor() {
        return this.disabledInactiveContainerColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveContentColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveContentColor() {
        return this.disabledInactiveContentColor;
    }

    /* JADX INFO: renamed from: getDisabledInactiveBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledInactiveBorderColor() {
        return this.disabledInactiveBorderColor;
    }

    /* JADX INFO: renamed from: copy-2qZNXz8, reason: not valid java name */
    public final SegmentedButtonColors m2804copy2qZNXz8(long activeContainerColor, long activeContentColor, long activeBorderColor, long inactiveContainerColor, long inactiveContentColor, long inactiveBorderColor, long disabledActiveContainerColor, long disabledActiveContentColor, long disabledActiveBorderColor, long disabledInactiveContainerColor, long disabledInactiveContentColor, long disabledInactiveBorderColor) {
        return new SegmentedButtonColors((activeContainerColor > 16L ? 1 : (activeContainerColor == 16L ? 0 : -1)) != 0 ? activeContainerColor : this.activeContainerColor, (activeContentColor > 16L ? 1 : (activeContentColor == 16L ? 0 : -1)) != 0 ? activeContentColor : this.activeContentColor, (activeBorderColor > 16L ? 1 : (activeBorderColor == 16L ? 0 : -1)) != 0 ? activeBorderColor : this.activeBorderColor, (inactiveContainerColor > 16L ? 1 : (inactiveContainerColor == 16L ? 0 : -1)) != 0 ? inactiveContainerColor : this.inactiveContainerColor, (inactiveContentColor > 16L ? 1 : (inactiveContentColor == 16L ? 0 : -1)) != 0 ? inactiveContentColor : this.inactiveContentColor, (inactiveBorderColor > 16L ? 1 : (inactiveBorderColor == 16L ? 0 : -1)) != 0 ? inactiveBorderColor : this.inactiveBorderColor, (disabledActiveContainerColor > 16L ? 1 : (disabledActiveContainerColor == 16L ? 0 : -1)) != 0 ? disabledActiveContainerColor : this.disabledActiveContainerColor, (disabledActiveContentColor > 16L ? 1 : (disabledActiveContentColor == 16L ? 0 : -1)) != 0 ? disabledActiveContentColor : this.disabledActiveContentColor, (disabledActiveBorderColor > 16L ? 1 : (disabledActiveBorderColor == 16L ? 0 : -1)) != 0 ? disabledActiveBorderColor : this.disabledActiveBorderColor, (disabledInactiveContainerColor > 16L ? 1 : (disabledInactiveContainerColor == 16L ? 0 : -1)) != 0 ? disabledInactiveContainerColor : this.disabledInactiveContainerColor, (disabledInactiveContentColor > 16L ? 1 : (disabledInactiveContentColor == 16L ? 0 : -1)) != 0 ? disabledInactiveContentColor : this.disabledInactiveContentColor, disabledInactiveBorderColor != 16 ? disabledInactiveBorderColor : this.disabledInactiveBorderColor, null);
    }

    /* JADX INFO: renamed from: borderColor-WaAFU9c$material3, reason: not valid java name */
    public final long m2801borderColorWaAFU9c$material3(boolean enabled, boolean active) {
        if (enabled && active) {
            return this.activeBorderColor;
        }
        if (!enabled || active) {
            return (enabled || !active) ? this.disabledInactiveBorderColor : this.disabledActiveBorderColor;
        }
        return this.inactiveBorderColor;
    }

    /* JADX INFO: renamed from: contentColor-WaAFU9c$material3, reason: not valid java name */
    public final long m2803contentColorWaAFU9c$material3(boolean enabled, boolean checked) {
        if (enabled && checked) {
            return this.activeContentColor;
        }
        if (!enabled || checked) {
            return (enabled || !checked) ? this.disabledInactiveContentColor : this.disabledActiveContentColor;
        }
        return this.inactiveContentColor;
    }

    /* JADX INFO: renamed from: containerColor-WaAFU9c$material3, reason: not valid java name */
    public final long m2802containerColorWaAFU9c$material3(boolean enabled, boolean active) {
        if (enabled && active) {
            return this.activeContainerColor;
        }
        if (!enabled || active) {
            return (enabled || !active) ? this.disabledInactiveContainerColor : this.disabledActiveContainerColor;
        }
        return this.inactiveContainerColor;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        if (Color.m5098equalsimpl0(this.activeBorderColor, ((SegmentedButtonColors) other).activeBorderColor) && Color.m5098equalsimpl0(this.activeContentColor, ((SegmentedButtonColors) other).activeContentColor) && Color.m5098equalsimpl0(this.activeContainerColor, ((SegmentedButtonColors) other).activeContainerColor) && Color.m5098equalsimpl0(this.inactiveBorderColor, ((SegmentedButtonColors) other).inactiveBorderColor) && Color.m5098equalsimpl0(this.inactiveContentColor, ((SegmentedButtonColors) other).inactiveContentColor) && Color.m5098equalsimpl0(this.inactiveContainerColor, ((SegmentedButtonColors) other).inactiveContainerColor) && Color.m5098equalsimpl0(this.disabledActiveBorderColor, ((SegmentedButtonColors) other).disabledActiveBorderColor) && Color.m5098equalsimpl0(this.disabledActiveContentColor, ((SegmentedButtonColors) other).disabledActiveContentColor) && Color.m5098equalsimpl0(this.disabledActiveContainerColor, ((SegmentedButtonColors) other).disabledActiveContainerColor) && Color.m5098equalsimpl0(this.disabledInactiveBorderColor, ((SegmentedButtonColors) other).disabledInactiveBorderColor) && Color.m5098equalsimpl0(this.disabledInactiveContentColor, ((SegmentedButtonColors) other).disabledInactiveContentColor) && Color.m5098equalsimpl0(this.disabledInactiveContainerColor, ((SegmentedButtonColors) other).disabledInactiveContainerColor)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Color.m5104hashCodeimpl(this.activeBorderColor);
        return (((((((((((((((((((((result * 31) + Color.m5104hashCodeimpl(this.activeContentColor)) * 31) + Color.m5104hashCodeimpl(this.activeContainerColor)) * 31) + Color.m5104hashCodeimpl(this.inactiveBorderColor)) * 31) + Color.m5104hashCodeimpl(this.inactiveContentColor)) * 31) + Color.m5104hashCodeimpl(this.inactiveContainerColor)) * 31) + Color.m5104hashCodeimpl(this.disabledActiveBorderColor)) * 31) + Color.m5104hashCodeimpl(this.disabledActiveContentColor)) * 31) + Color.m5104hashCodeimpl(this.disabledActiveContainerColor)) * 31) + Color.m5104hashCodeimpl(this.disabledInactiveBorderColor)) * 31) + Color.m5104hashCodeimpl(this.disabledInactiveContentColor)) * 31) + Color.m5104hashCodeimpl(this.disabledInactiveContainerColor);
    }
}
