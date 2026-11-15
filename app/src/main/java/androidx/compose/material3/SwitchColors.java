package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u00ad\u0001\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0003¢\u0006\u0004\b(\u0010)J\u001f\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b.\u0010/J\u001f\u00100\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b1\u0010/J\u001f\u00102\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b3\u0010/J\u001f\u00104\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0001¢\u0006\u0004\b5\u0010/J\u0013\u00106\u001a\u00020,2\b\u00107\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u00108\u001a\u000209H\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0018\u0010\u0016R\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0019\u0010\u0016R\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001a\u0010\u0016R\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001c\u0010\u0016R\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001d\u0010\u0016R\u0013\u0010\n\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001e\u0010\u0016R\u0013\u0010\u000b\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u001f\u0010\u0016R\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b \u0010\u0016R\u0013\u0010\r\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b!\u0010\u0016R\u0013\u0010\u000e\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\"\u0010\u0016R\u0013\u0010\u000f\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b#\u0010\u0016R\u0013\u0010\u0010\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b$\u0010\u0016R\u0013\u0010\u0011\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b%\u0010\u0016R\u0013\u0010\u0012\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b&\u0010\u0016¨\u0006:"}, d2 = {"Landroidx/compose/material3/SwitchColors;", "", "checkedThumbColor", "Landroidx/compose/ui/graphics/Color;", "checkedTrackColor", "checkedBorderColor", "checkedIconColor", "uncheckedThumbColor", "uncheckedTrackColor", "uncheckedBorderColor", "uncheckedIconColor", "disabledCheckedThumbColor", "disabledCheckedTrackColor", "disabledCheckedBorderColor", "disabledCheckedIconColor", "disabledUncheckedThumbColor", "disabledUncheckedTrackColor", "disabledUncheckedBorderColor", "disabledUncheckedIconColor", "<init>", "(JJJJJJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCheckedThumbColor-0d7_KjU", "()J", "J", "getCheckedTrackColor-0d7_KjU", "getCheckedBorderColor-0d7_KjU", "getCheckedIconColor-0d7_KjU", "getUncheckedThumbColor-0d7_KjU", "getUncheckedTrackColor-0d7_KjU", "getUncheckedBorderColor-0d7_KjU", "getUncheckedIconColor-0d7_KjU", "getDisabledCheckedThumbColor-0d7_KjU", "getDisabledCheckedTrackColor-0d7_KjU", "getDisabledCheckedBorderColor-0d7_KjU", "getDisabledCheckedIconColor-0d7_KjU", "getDisabledUncheckedThumbColor-0d7_KjU", "getDisabledUncheckedTrackColor-0d7_KjU", "getDisabledUncheckedBorderColor-0d7_KjU", "getDisabledUncheckedIconColor-0d7_KjU", "copy", "copy-Q_H9qLU", "(JJJJJJJJJJJJJJJJ)Landroidx/compose/material3/SwitchColors;", "thumbColor", "enabled", "", "checked", "thumbColor-WaAFU9c$material3", "(ZZ)J", "trackColor", "trackColor-WaAFU9c$material3", "borderColor", "borderColor-WaAFU9c$material3", "iconColor", "iconColor-WaAFU9c$material3", MetadataValidation.EQUALS, "other", "hashCode", "", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SwitchColors {
    public static final int $stable = 0;
    private final long checkedBorderColor;
    private final long checkedIconColor;
    private final long checkedThumbColor;
    private final long checkedTrackColor;
    private final long disabledCheckedBorderColor;
    private final long disabledCheckedIconColor;
    private final long disabledCheckedThumbColor;
    private final long disabledCheckedTrackColor;
    private final long disabledUncheckedBorderColor;
    private final long disabledUncheckedIconColor;
    private final long disabledUncheckedThumbColor;
    private final long disabledUncheckedTrackColor;
    private final long uncheckedBorderColor;
    private final long uncheckedIconColor;
    private final long uncheckedThumbColor;
    private final long uncheckedTrackColor;

    public /* synthetic */ SwitchColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16);
    }

    private SwitchColors(long checkedThumbColor, long checkedTrackColor, long checkedBorderColor, long checkedIconColor, long uncheckedThumbColor, long uncheckedTrackColor, long uncheckedBorderColor, long uncheckedIconColor, long disabledCheckedThumbColor, long disabledCheckedTrackColor, long disabledCheckedBorderColor, long disabledCheckedIconColor, long disabledUncheckedThumbColor, long disabledUncheckedTrackColor, long disabledUncheckedBorderColor, long disabledUncheckedIconColor) {
        this.checkedThumbColor = checkedThumbColor;
        this.checkedTrackColor = checkedTrackColor;
        this.checkedBorderColor = checkedBorderColor;
        this.checkedIconColor = checkedIconColor;
        this.uncheckedThumbColor = uncheckedThumbColor;
        this.uncheckedTrackColor = uncheckedTrackColor;
        this.uncheckedBorderColor = uncheckedBorderColor;
        this.uncheckedIconColor = uncheckedIconColor;
        this.disabledCheckedThumbColor = disabledCheckedThumbColor;
        this.disabledCheckedTrackColor = disabledCheckedTrackColor;
        this.disabledCheckedBorderColor = disabledCheckedBorderColor;
        this.disabledCheckedIconColor = disabledCheckedIconColor;
        this.disabledUncheckedThumbColor = disabledUncheckedThumbColor;
        this.disabledUncheckedTrackColor = disabledUncheckedTrackColor;
        this.disabledUncheckedBorderColor = disabledUncheckedBorderColor;
        this.disabledUncheckedIconColor = disabledUncheckedIconColor;
    }

    /* JADX INFO: renamed from: getCheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedThumbColor() {
        return this.checkedThumbColor;
    }

    /* JADX INFO: renamed from: getCheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedTrackColor() {
        return this.checkedTrackColor;
    }

    /* JADX INFO: renamed from: getCheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedBorderColor() {
        return this.checkedBorderColor;
    }

    /* JADX INFO: renamed from: getCheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getCheckedIconColor() {
        return this.checkedIconColor;
    }

    /* JADX INFO: renamed from: getUncheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedThumbColor() {
        return this.uncheckedThumbColor;
    }

    /* JADX INFO: renamed from: getUncheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedTrackColor() {
        return this.uncheckedTrackColor;
    }

    /* JADX INFO: renamed from: getUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedBorderColor() {
        return this.uncheckedBorderColor;
    }

    /* JADX INFO: renamed from: getUncheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getUncheckedIconColor() {
        return this.uncheckedIconColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedThumbColor() {
        return this.disabledCheckedThumbColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedTrackColor() {
        return this.disabledCheckedTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedBorderColor() {
        return this.disabledCheckedBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledCheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledCheckedIconColor() {
        return this.disabledCheckedIconColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedThumbColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedThumbColor() {
        return this.disabledUncheckedThumbColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedTrackColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedTrackColor() {
        return this.disabledUncheckedTrackColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedBorderColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedBorderColor() {
        return this.disabledUncheckedBorderColor;
    }

    /* JADX INFO: renamed from: getDisabledUncheckedIconColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getDisabledUncheckedIconColor() {
        return this.disabledUncheckedIconColor;
    }

    /* JADX INFO: renamed from: copy-Q_H9qLU, reason: not valid java name */
    public final SwitchColors m2939copyQ_H9qLU(long checkedThumbColor, long checkedTrackColor, long checkedBorderColor, long checkedIconColor, long uncheckedThumbColor, long uncheckedTrackColor, long uncheckedBorderColor, long uncheckedIconColor, long disabledCheckedThumbColor, long disabledCheckedTrackColor, long disabledCheckedBorderColor, long disabledCheckedIconColor, long disabledUncheckedThumbColor, long disabledUncheckedTrackColor, long disabledUncheckedBorderColor, long disabledUncheckedIconColor) {
        return new SwitchColors((checkedThumbColor > 16L ? 1 : (checkedThumbColor == 16L ? 0 : -1)) != 0 ? checkedThumbColor : this.checkedThumbColor, (checkedTrackColor > 16L ? 1 : (checkedTrackColor == 16L ? 0 : -1)) != 0 ? checkedTrackColor : this.checkedTrackColor, (checkedBorderColor > 16L ? 1 : (checkedBorderColor == 16L ? 0 : -1)) != 0 ? checkedBorderColor : this.checkedBorderColor, (checkedIconColor > 16L ? 1 : (checkedIconColor == 16L ? 0 : -1)) != 0 ? checkedIconColor : this.checkedIconColor, (uncheckedThumbColor > 16L ? 1 : (uncheckedThumbColor == 16L ? 0 : -1)) != 0 ? uncheckedThumbColor : this.uncheckedThumbColor, (uncheckedTrackColor > 16L ? 1 : (uncheckedTrackColor == 16L ? 0 : -1)) != 0 ? uncheckedTrackColor : this.uncheckedTrackColor, (uncheckedBorderColor > 16L ? 1 : (uncheckedBorderColor == 16L ? 0 : -1)) != 0 ? uncheckedBorderColor : this.uncheckedBorderColor, (uncheckedIconColor > 16L ? 1 : (uncheckedIconColor == 16L ? 0 : -1)) != 0 ? uncheckedIconColor : this.uncheckedIconColor, (disabledCheckedThumbColor > 16L ? 1 : (disabledCheckedThumbColor == 16L ? 0 : -1)) != 0 ? disabledCheckedThumbColor : this.disabledCheckedThumbColor, (disabledCheckedTrackColor > 16L ? 1 : (disabledCheckedTrackColor == 16L ? 0 : -1)) != 0 ? disabledCheckedTrackColor : this.disabledCheckedTrackColor, (disabledCheckedBorderColor > 16L ? 1 : (disabledCheckedBorderColor == 16L ? 0 : -1)) != 0 ? disabledCheckedBorderColor : this.disabledCheckedBorderColor, (disabledCheckedIconColor > 16L ? 1 : (disabledCheckedIconColor == 16L ? 0 : -1)) != 0 ? disabledCheckedIconColor : this.disabledCheckedIconColor, (disabledUncheckedThumbColor > 16L ? 1 : (disabledUncheckedThumbColor == 16L ? 0 : -1)) != 0 ? disabledUncheckedThumbColor : this.disabledUncheckedThumbColor, (disabledUncheckedTrackColor > 16L ? 1 : (disabledUncheckedTrackColor == 16L ? 0 : -1)) != 0 ? disabledUncheckedTrackColor : this.disabledUncheckedTrackColor, (disabledUncheckedBorderColor > 16L ? 1 : (disabledUncheckedBorderColor == 16L ? 0 : -1)) != 0 ? disabledUncheckedBorderColor : this.disabledUncheckedBorderColor, disabledUncheckedIconColor != 16 ? disabledUncheckedIconColor : this.disabledUncheckedIconColor, null);
    }

    /* JADX INFO: renamed from: thumbColor-WaAFU9c$material3, reason: not valid java name */
    public final long m2957thumbColorWaAFU9c$material3(boolean enabled, boolean checked) {
        if (enabled) {
            return checked ? this.checkedThumbColor : this.uncheckedThumbColor;
        }
        return checked ? this.disabledCheckedThumbColor : this.disabledUncheckedThumbColor;
    }

    /* JADX INFO: renamed from: trackColor-WaAFU9c$material3, reason: not valid java name */
    public final long m2958trackColorWaAFU9c$material3(boolean enabled, boolean checked) {
        if (enabled) {
            return checked ? this.checkedTrackColor : this.uncheckedTrackColor;
        }
        return checked ? this.disabledCheckedTrackColor : this.disabledUncheckedTrackColor;
    }

    /* JADX INFO: renamed from: borderColor-WaAFU9c$material3, reason: not valid java name */
    public final long m2938borderColorWaAFU9c$material3(boolean enabled, boolean checked) {
        if (enabled) {
            return checked ? this.checkedBorderColor : this.uncheckedBorderColor;
        }
        return checked ? this.disabledCheckedBorderColor : this.disabledUncheckedBorderColor;
    }

    /* JADX INFO: renamed from: iconColor-WaAFU9c$material3, reason: not valid java name */
    public final long m2956iconColorWaAFU9c$material3(boolean enabled, boolean checked) {
        if (enabled) {
            return checked ? this.checkedIconColor : this.uncheckedIconColor;
        }
        return checked ? this.disabledCheckedIconColor : this.disabledUncheckedIconColor;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof SwitchColors) && Color.m5098equalsimpl0(this.checkedThumbColor, ((SwitchColors) other).checkedThumbColor) && Color.m5098equalsimpl0(this.checkedTrackColor, ((SwitchColors) other).checkedTrackColor) && Color.m5098equalsimpl0(this.checkedBorderColor, ((SwitchColors) other).checkedBorderColor) && Color.m5098equalsimpl0(this.checkedIconColor, ((SwitchColors) other).checkedIconColor) && Color.m5098equalsimpl0(this.uncheckedThumbColor, ((SwitchColors) other).uncheckedThumbColor) && Color.m5098equalsimpl0(this.uncheckedTrackColor, ((SwitchColors) other).uncheckedTrackColor) && Color.m5098equalsimpl0(this.uncheckedBorderColor, ((SwitchColors) other).uncheckedBorderColor) && Color.m5098equalsimpl0(this.uncheckedIconColor, ((SwitchColors) other).uncheckedIconColor) && Color.m5098equalsimpl0(this.disabledCheckedThumbColor, ((SwitchColors) other).disabledCheckedThumbColor) && Color.m5098equalsimpl0(this.disabledCheckedTrackColor, ((SwitchColors) other).disabledCheckedTrackColor) && Color.m5098equalsimpl0(this.disabledCheckedBorderColor, ((SwitchColors) other).disabledCheckedBorderColor) && Color.m5098equalsimpl0(this.disabledCheckedIconColor, ((SwitchColors) other).disabledCheckedIconColor) && Color.m5098equalsimpl0(this.disabledUncheckedThumbColor, ((SwitchColors) other).disabledUncheckedThumbColor) && Color.m5098equalsimpl0(this.disabledUncheckedTrackColor, ((SwitchColors) other).disabledUncheckedTrackColor) && Color.m5098equalsimpl0(this.disabledUncheckedBorderColor, ((SwitchColors) other).disabledUncheckedBorderColor) && Color.m5098equalsimpl0(this.disabledUncheckedIconColor, ((SwitchColors) other).disabledUncheckedIconColor)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Color.m5104hashCodeimpl(this.checkedThumbColor);
        return (((((((((((((((((((((((((((((result * 31) + Color.m5104hashCodeimpl(this.checkedTrackColor)) * 31) + Color.m5104hashCodeimpl(this.checkedBorderColor)) * 31) + Color.m5104hashCodeimpl(this.checkedIconColor)) * 31) + Color.m5104hashCodeimpl(this.uncheckedThumbColor)) * 31) + Color.m5104hashCodeimpl(this.uncheckedTrackColor)) * 31) + Color.m5104hashCodeimpl(this.uncheckedBorderColor)) * 31) + Color.m5104hashCodeimpl(this.uncheckedIconColor)) * 31) + Color.m5104hashCodeimpl(this.disabledCheckedThumbColor)) * 31) + Color.m5104hashCodeimpl(this.disabledCheckedTrackColor)) * 31) + Color.m5104hashCodeimpl(this.disabledCheckedBorderColor)) * 31) + Color.m5104hashCodeimpl(this.disabledCheckedIconColor)) * 31) + Color.m5104hashCodeimpl(this.disabledUncheckedThumbColor)) * 31) + Color.m5104hashCodeimpl(this.disabledUncheckedTrackColor)) * 31) + Color.m5104hashCodeimpl(this.disabledUncheckedBorderColor)) * 31) + Color.m5104hashCodeimpl(this.disabledUncheckedIconColor);
    }
}
