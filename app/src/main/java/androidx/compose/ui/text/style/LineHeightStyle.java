package androidx.compose.ui.text.style;

import androidx.compose.ui.text.internal.InlineClassHelperKt;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: LineHeightStyle.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0004\u001c\u001d\u001e\u001fB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\nJ+\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\u000f¨\u0006 "}, d2 = {"Landroidx/compose/ui/text/style/LineHeightStyle;", "", "alignment", "Landroidx/compose/ui/text/style/LineHeightStyle$Alignment;", "trim", "Landroidx/compose/ui/text/style/LineHeightStyle$Trim;", "mode", "Landroidx/compose/ui/text/style/LineHeightStyle$Mode;", "<init>", "(FIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "(FILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAlignment-PIaL0Z0", "()F", "F", "getTrim-EVpEnUU", "()I", "I", "getMode-lzQqcRY", "copy", "copy-38bxuX8", "(FII)Landroidx/compose/ui/text/style/LineHeightStyle;", MetadataValidation.EQUALS, "", "other", "hashCode", "", "toString", "", "Companion", "Trim", "Alignment", "Mode", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LineHeightStyle {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    private static final LineHeightStyle Default;
    private final float alignment;
    private final int mode;
    private final int trim;

    public /* synthetic */ LineHeightStyle(float f, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, i, i2);
    }

    public /* synthetic */ LineHeightStyle(float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, i);
    }

    private LineHeightStyle(float alignment, int trim, int mode) {
        this.alignment = alignment;
        this.trim = trim;
        this.mode = mode;
    }

    /* JADX INFO: renamed from: getAlignment-PIaL0Z0, reason: not valid java name and from getter */
    public final float getAlignment() {
        return this.alignment;
    }

    /* JADX INFO: renamed from: getMode-lzQqcRY, reason: not valid java name and from getter */
    public final int getMode() {
        return this.mode;
    }

    /* JADX INFO: renamed from: getTrim-EVpEnUU, reason: not valid java name and from getter */
    public final int getTrim() {
        return this.trim;
    }

    private LineHeightStyle(float alignment, int trim) {
        this(alignment, trim, Mode.INSTANCE.m7735getFixedlzQqcRY(), null);
    }

    /* JADX INFO: compiled from: LineHeightStyle.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/text/style/LineHeightStyle$Companion;", "", "<init>", "()V", "Default", "Landroidx/compose/ui/text/style/LineHeightStyle;", "getDefault", "()Landroidx/compose/ui/text/style/LineHeightStyle;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LineHeightStyle getDefault() {
            return LineHeightStyle.Default;
        }
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        INSTANCE = new Companion(defaultConstructorMarker);
        Default = new LineHeightStyle(Alignment.INSTANCE.m7726getProportionalPIaL0Z0(), Trim.INSTANCE.m7747getBothEVpEnUU(), Mode.INSTANCE.m7735getFixedlzQqcRY(), defaultConstructorMarker);
    }

    /* JADX INFO: renamed from: copy-38bxuX8$default, reason: not valid java name */
    public static /* synthetic */ LineHeightStyle m7712copy38bxuX8$default(LineHeightStyle lineHeightStyle, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            f = lineHeightStyle.alignment;
        }
        if ((i3 & 2) != 0) {
            i = lineHeightStyle.trim;
        }
        if ((i3 & 4) != 0) {
            i2 = lineHeightStyle.mode;
        }
        return lineHeightStyle.m7713copy38bxuX8(f, i, i2);
    }

    /* JADX INFO: renamed from: copy-38bxuX8, reason: not valid java name */
    public final LineHeightStyle m7713copy38bxuX8(float alignment, int trim, int mode) {
        return new LineHeightStyle(alignment, trim, mode, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LineHeightStyle) && Alignment.m7720equalsimpl0(this.alignment, ((LineHeightStyle) other).alignment) && Trim.m7741equalsimpl0(this.trim, ((LineHeightStyle) other).trim) && Mode.m7731equalsimpl0(this.mode, ((LineHeightStyle) other).mode);
    }

    public int hashCode() {
        int result = Alignment.m7721hashCodeimpl(this.alignment);
        return (((result * 31) + Trim.m7742hashCodeimpl(this.trim)) * 31) + Mode.m7732hashCodeimpl(this.mode);
    }

    public String toString() {
        return "LineHeightStyle(alignment=" + ((Object) Alignment.m7722toStringimpl(this.alignment)) + ", trim=" + ((Object) Trim.m7745toStringimpl(this.trim)) + ",mode=" + ((Object) Mode.m7733toStringimpl(this.mode)) + ')';
    }

    /* JADX INFO: compiled from: LineHeightStyle.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087@\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u000f\u0010\rJ\u0013\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/text/style/LineHeightStyle$Trim;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "isTrimFirstLineTop", "", "isTrimFirstLineTop-impl$ui_text", "(I)Z", "isTrimLastLineBottom", "isTrimLastLineBottom-impl$ui_text", MetadataValidation.EQUALS, "other", "hashCode", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @JvmInline
    public static final class Trim {
        private static final int FlagTrimBottom = 16;
        private static final int FlagTrimTop = 1;
        private final int value;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final int FirstLineTop = m7739constructorimpl(1);
        private static final int LastLineBottom = m7739constructorimpl(16);
        private static final int Both = m7739constructorimpl(17);
        private static final int None = m7739constructorimpl(0);

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ Trim m7738boximpl(int i) {
            return new Trim(i);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m7739constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m7740equalsimpl(int i, Object obj) {
            return (obj instanceof Trim) && i == ((Trim) obj).getValue();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m7741equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m7742hashCodeimpl(int i) {
            return Integer.hashCode(i);
        }

        public boolean equals(Object other) {
            return m7740equalsimpl(this.value, other);
        }

        public int hashCode() {
            return m7742hashCodeimpl(this.value);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ int getValue() {
            return this.value;
        }

        private /* synthetic */ Trim(int value) {
            this.value = value;
        }

        public String toString() {
            return m7745toStringimpl(this.value);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m7745toStringimpl(int i) {
            if (i == FirstLineTop) {
                return "LineHeightStyle.Trim.FirstLineTop";
            }
            if (i == LastLineBottom) {
                return "LineHeightStyle.Trim.LastLineBottom";
            }
            if (i == Both) {
                return "LineHeightStyle.Trim.Both";
            }
            return i == None ? "LineHeightStyle.Trim.None" : "Invalid";
        }

        /* JADX INFO: compiled from: LineHeightStyle.kt */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\f\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0013\u0010\u000e\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000f\u0010\nR\u0013\u0010\u0010\u001a\u00020\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u0011\u0010\n¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/text/style/LineHeightStyle$Trim$Companion;", "", "<init>", "()V", "FlagTrimTop", "", "FlagTrimBottom", "FirstLineTop", "Landroidx/compose/ui/text/style/LineHeightStyle$Trim;", "getFirstLineTop-EVpEnUU", "()I", "I", "LastLineBottom", "getLastLineBottom-EVpEnUU", "Both", "getBoth-EVpEnUU", "None", "getNone-EVpEnUU", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getFirstLineTop-EVpEnUU, reason: not valid java name */
            public final int m7748getFirstLineTopEVpEnUU() {
                return Trim.FirstLineTop;
            }

            /* JADX INFO: renamed from: getLastLineBottom-EVpEnUU, reason: not valid java name */
            public final int m7749getLastLineBottomEVpEnUU() {
                return Trim.LastLineBottom;
            }

            /* JADX INFO: renamed from: getBoth-EVpEnUU, reason: not valid java name */
            public final int m7747getBothEVpEnUU() {
                return Trim.Both;
            }

            /* JADX INFO: renamed from: getNone-EVpEnUU, reason: not valid java name */
            public final int m7750getNoneEVpEnUU() {
                return Trim.None;
            }
        }

        /* JADX INFO: renamed from: isTrimFirstLineTop-impl$ui_text, reason: not valid java name */
        public static final boolean m7743isTrimFirstLineTopimpl$ui_text(int i) {
            return (i & 1) > 0;
        }

        /* JADX INFO: renamed from: isTrimLastLineBottom-impl$ui_text, reason: not valid java name */
        public static final boolean m7744isTrimLastLineBottomimpl$ui_text(int i) {
            return (i & 16) > 0;
        }
    }

    /* JADX INFO: compiled from: LineHeightStyle.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087@\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/text/style/LineHeightStyle$Alignment;", "", "topRatio", "", "constructor-impl", "(F)F", "toString", "", "toString-impl", "(F)Ljava/lang/String;", MetadataValidation.EQUALS, "", "other", "hashCode", "", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @JvmInline
    public static final class Alignment {
        private final float topRatio;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final float Top = m7718constructorimpl(0.0f);
        private static final float Center = m7718constructorimpl(0.5f);
        private static final float Proportional = m7718constructorimpl(-1.0f);
        private static final float Bottom = m7718constructorimpl(1.0f);

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ Alignment m7717boximpl(float f) {
            return new Alignment(f);
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m7719equalsimpl(float f, Object obj) {
            return (obj instanceof Alignment) && Float.compare(f, ((Alignment) obj).getTopRatio()) == 0;
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m7720equalsimpl0(float f, float f2) {
            return Float.compare(f, f2) == 0;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m7721hashCodeimpl(float f) {
            return Float.hashCode(f);
        }

        public boolean equals(Object other) {
            return m7719equalsimpl(this.topRatio, other);
        }

        public int hashCode() {
            return m7721hashCodeimpl(this.topRatio);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ float getTopRatio() {
            return this.topRatio;
        }

        private /* synthetic */ Alignment(float topRatio) {
            this.topRatio = topRatio;
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static float m7718constructorimpl(float topRatio) {
            boolean value$iv = true;
            if (!(0.0f <= topRatio && topRatio <= 1.0f)) {
                if (!(topRatio == -1.0f)) {
                    value$iv = false;
                }
            }
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalStateException("topRatio should be in [0..1] range or -1");
            }
            return topRatio;
        }

        public String toString() {
            return m7722toStringimpl(this.topRatio);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m7722toStringimpl(float f) {
            if (f == Top) {
                return "LineHeightStyle.Alignment.Top";
            }
            if (f == Center) {
                return "LineHeightStyle.Alignment.Center";
            }
            if (f == Proportional) {
                return "LineHeightStyle.Alignment.Proportional";
            }
            return f == Bottom ? "LineHeightStyle.Alignment.Bottom" : "LineHeightStyle.Alignment(topPercentage = " + f + ')';
        }

        /* JADX INFO: compiled from: LineHeightStyle.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/text/style/LineHeightStyle$Alignment$Companion;", "", "<init>", "()V", "Top", "Landroidx/compose/ui/text/style/LineHeightStyle$Alignment;", "getTop-PIaL0Z0", "()F", "F", "Center", "getCenter-PIaL0Z0", "Proportional", "getProportional-PIaL0Z0", "Bottom", "getBottom-PIaL0Z0", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getTop-PIaL0Z0, reason: not valid java name */
            public final float m7727getTopPIaL0Z0() {
                return Alignment.Top;
            }

            /* JADX INFO: renamed from: getCenter-PIaL0Z0, reason: not valid java name */
            public final float m7725getCenterPIaL0Z0() {
                return Alignment.Center;
            }

            /* JADX INFO: renamed from: getProportional-PIaL0Z0, reason: not valid java name */
            public final float m7726getProportionalPIaL0Z0() {
                return Alignment.Proportional;
            }

            /* JADX INFO: renamed from: getBottom-PIaL0Z0, reason: not valid java name */
            public final float m7724getBottomPIaL0Z0() {
                return Alignment.Bottom;
            }
        }
    }

    /* JADX INFO: compiled from: LineHeightStyle.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/text/style/LineHeightStyle$Mode;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", MetadataValidation.EQUALS, "", "other", "hashCode", "Companion", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @JvmInline
    public static final class Mode {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final int Fixed = m7729constructorimpl(0);
        private static final int Minimum = m7729constructorimpl(1);
        private static final int Tight = m7729constructorimpl(2);
        private final int value;

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ Mode m7728boximpl(int i) {
            return new Mode(i);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m7729constructorimpl(int i) {
            return i;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m7730equalsimpl(int i, Object obj) {
            return (obj instanceof Mode) && i == ((Mode) obj).getValue();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m7731equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m7732hashCodeimpl(int i) {
            return Integer.hashCode(i);
        }

        public boolean equals(Object other) {
            return m7730equalsimpl(this.value, other);
        }

        public int hashCode() {
            return m7732hashCodeimpl(this.value);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ int getValue() {
            return this.value;
        }

        private /* synthetic */ Mode(int value) {
            this.value = value;
        }

        public String toString() {
            return m7733toStringimpl(this.value);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m7733toStringimpl(int i) {
            if (m7731equalsimpl0(i, Fixed)) {
                return "LineHeightStyle.Mode.Fixed";
            }
            if (m7731equalsimpl0(i, Minimum)) {
                return "LineHeightStyle.Mode.Minimum";
            }
            return m7731equalsimpl0(i, Tight) ? "LineHeightStyle.Mode.Tight" : "Invalid";
        }

        /* JADX INFO: compiled from: LineHeightStyle.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/compose/ui/text/style/LineHeightStyle$Mode$Companion;", "", "<init>", "()V", "Fixed", "Landroidx/compose/ui/text/style/LineHeightStyle$Mode;", "getFixed-lzQqcRY", "()I", "I", "Minimum", "getMinimum-lzQqcRY", "Tight", "getTight-lzQqcRY", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            /* JADX INFO: renamed from: getFixed-lzQqcRY, reason: not valid java name */
            public final int m7735getFixedlzQqcRY() {
                return Mode.Fixed;
            }

            /* JADX INFO: renamed from: getMinimum-lzQqcRY, reason: not valid java name */
            public final int m7736getMinimumlzQqcRY() {
                return Mode.Minimum;
            }

            /* JADX INFO: renamed from: getTight-lzQqcRY, reason: not valid java name */
            public final int m7737getTightlzQqcRY() {
                return Mode.Tight;
            }
        }
    }
}
