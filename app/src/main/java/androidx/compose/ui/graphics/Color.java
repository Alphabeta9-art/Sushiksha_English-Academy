package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaceKt;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.Connector;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import com.cloudinary.metadata.MetadataValidation;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: Color.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u001a\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087@\u0018\u0000 62\u00020\u0001:\u00016B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010 \u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b!\u0010\u0016J\u0010\u0010\"\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b#\u0010\u0016J\u0010\u0010$\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b%\u0010\u0016J\u0010\u0010&\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b'\u0010\u0016J\u0010\u0010(\u001a\u00020\nH\u0087\n¢\u0006\u0004\b)\u0010\u000eJ7\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u001d\u001a\u00020\u00132\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u0013H\u0007¢\u0006\u0004\b+\u0010,J\u000f\u0010-\u001a\u00020.H\u0016¢\u0006\u0004\b/\u00100J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\t\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u00138FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\f\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u00138FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\f\u001a\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u00138FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\f\u001a\u0004\b\u001c\u0010\u0016R\u001a\u0010\u001d\u001a\u00020\u00138FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\f\u001a\u0004\b\u001f\u0010\u0016\u0088\u0001\u0002¨\u00067"}, d2 = {"Landroidx/compose/ui/graphics/Color;", "", "value", "Lkotlin/ULong;", "constructor-impl", "(J)J", "getValue-s-VKNKU", "()J", "J", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "getColorSpace$annotations", "()V", "getColorSpace-impl", "(J)Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "convert", "convert-vNxB06k", "(JLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "red", "", "getRed$annotations", "getRed-impl", "(J)F", "green", "getGreen$annotations", "getGreen-impl", "blue", "getBlue$annotations", "getBlue-impl", "alpha", "getAlpha$annotations", "getAlpha-impl", "component1", "component1-impl", "component2", "component2-impl", "component3", "component3-impl", "component4", "component4-impl", "component5", "component5-impl", "copy", "copy-wmQWz5c", "(JFFFF)J", "toString", "", "toString-impl", "(J)Ljava/lang/String;", MetadataValidation.EQUALS, "", "other", "hashCode", "", "Companion", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
public final class Color {
    private final long value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Black = ColorKt.Color(4278190080L);
    private static final long DarkGray = ColorKt.Color(4282664004L);
    private static final long Gray = ColorKt.Color(4287137928L);
    private static final long LightGray = ColorKt.Color(4291611852L);
    private static final long White = ColorKt.Color(4294967295L);
    private static final long Red = ColorKt.Color(4294901760L);
    private static final long Green = ColorKt.Color(4278255360L);
    private static final long Blue = ColorKt.Color(4278190335L);
    private static final long Yellow = ColorKt.Color(4294967040L);
    private static final long Cyan = ColorKt.Color(4278255615L);
    private static final long Magenta = ColorKt.Color(4294902015L);
    private static final long Transparent = ColorKt.Color(0);
    private static final long Unspecified = ColorKt.Color(0.0f, 0.0f, 0.0f, 0.0f, ColorSpaces.INSTANCE.getUnspecified$ui_graphics());

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Color m5087boximpl(long j) {
        return new Color(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m5093constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5097equalsimpl(long j, Object obj) {
        return (obj instanceof Color) && j == ((Color) obj).m5107unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5098equalsimpl0(long j, long j2) {
        return ULong.m9269equalsimpl0(j, j2);
    }

    public static /* synthetic */ void getAlpha$annotations() {
    }

    public static /* synthetic */ void getBlue$annotations() {
    }

    public static /* synthetic */ void getColorSpace$annotations() {
    }

    public static /* synthetic */ void getGreen$annotations() {
    }

    public static /* synthetic */ void getRed$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5104hashCodeimpl(long j) {
        return ULong.m9274hashCodeimpl(j);
    }

    public boolean equals(Object other) {
        return m5097equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m5104hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m5107unboximpl() {
        return this.value;
    }

    private /* synthetic */ Color(long value) {
        this.value = value;
    }

    /* JADX INFO: renamed from: getValue-s-VKNKU, reason: not valid java name and from getter */
    public final long getValue() {
        return this.value;
    }

    /* JADX INFO: renamed from: getColorSpace-impl, reason: not valid java name */
    public static final ColorSpace m5101getColorSpaceimpl(long j) {
        ColorSpaces this_$iv = ColorSpaces.INSTANCE;
        int id$iv = (int) ULong.m9262constructorimpl(63 & j);
        return this_$iv.getColorSpacesArray$ui_graphics()[id$iv];
    }

    /* JADX INFO: renamed from: convert-vNxB06k, reason: not valid java name */
    public static final long m5094convertvNxB06k(long j, ColorSpace colorSpace) {
        Connector connector = ColorSpaceKt.m5539connectYBCOT_4$default(m5101getColorSpaceimpl(j), colorSpace, 0, 2, null);
        return connector.mo5542transformToColorl2rxGTc$ui_graphics(j);
    }

    /* JADX INFO: renamed from: getRed-impl, reason: not valid java name */
    public static final float m5103getRedimpl(long j) {
        if (ULong.m9262constructorimpl(63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m9262constructorimpl(ULong.m9262constructorimpl(j >>> 48) & 255))) / 255.0f;
        }
        short h$iv = (short) ULong.m9262constructorimpl(ULong.m9262constructorimpl(j >>> 48) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
        int bits$iv = 65535 & h$iv;
        int s$iv = 32768 & bits$iv;
        int e$iv = (bits$iv >>> 10) & 31;
        int m$iv = bits$iv & 1023;
        int outE$iv = 0;
        int outM$iv = 0;
        if (e$iv == 0) {
            if (m$iv != 0) {
                int bits$iv$iv = 1056964608 + m$iv;
                float o$iv = Float.intBitsToFloat(bits$iv$iv) - Float16Kt.Fp32DenormalFloat;
                return s$iv == 0 ? o$iv : -o$iv;
            }
        } else {
            outM$iv = m$iv << 13;
            if (e$iv == 31) {
                outE$iv = 255;
                if (outM$iv != 0) {
                    outM$iv |= 4194304;
                }
            } else {
                outE$iv = (e$iv - 15) + 127;
            }
        }
        int out$iv = (s$iv << 16) | (outE$iv << 23) | outM$iv;
        return Float.intBitsToFloat(out$iv);
    }

    /* JADX INFO: renamed from: getGreen-impl, reason: not valid java name */
    public static final float m5102getGreenimpl(long j) {
        if (ULong.m9262constructorimpl(63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m9262constructorimpl(ULong.m9262constructorimpl(j >>> 40) & 255))) / 255.0f;
        }
        short h$iv = (short) ULong.m9262constructorimpl(ULong.m9262constructorimpl(j >>> 32) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
        int bits$iv = 65535 & h$iv;
        int s$iv = 32768 & bits$iv;
        int e$iv = (bits$iv >>> 10) & 31;
        int m$iv = bits$iv & 1023;
        int outE$iv = 0;
        int outM$iv = 0;
        if (e$iv == 0) {
            if (m$iv != 0) {
                int bits$iv$iv = 1056964608 + m$iv;
                float o$iv = Float.intBitsToFloat(bits$iv$iv) - Float16Kt.Fp32DenormalFloat;
                return s$iv == 0 ? o$iv : -o$iv;
            }
        } else {
            outM$iv = m$iv << 13;
            if (e$iv == 31) {
                outE$iv = 255;
                if (outM$iv != 0) {
                    outM$iv |= 4194304;
                }
            } else {
                outE$iv = (e$iv - 15) + 127;
            }
        }
        int out$iv = (s$iv << 16) | (outE$iv << 23) | outM$iv;
        return Float.intBitsToFloat(out$iv);
    }

    /* JADX INFO: renamed from: getBlue-impl, reason: not valid java name */
    public static final float m5100getBlueimpl(long j) {
        if (ULong.m9262constructorimpl(63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m9262constructorimpl(ULong.m9262constructorimpl(j >>> 32) & 255))) / 255.0f;
        }
        short h$iv = (short) ULong.m9262constructorimpl(ULong.m9262constructorimpl(j >>> 16) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
        int bits$iv = 65535 & h$iv;
        int s$iv = 32768 & bits$iv;
        int e$iv = (bits$iv >>> 10) & 31;
        int m$iv = bits$iv & 1023;
        int outE$iv = 0;
        int outM$iv = 0;
        if (e$iv == 0) {
            if (m$iv != 0) {
                int bits$iv$iv = 1056964608 + m$iv;
                float o$iv = Float.intBitsToFloat(bits$iv$iv) - Float16Kt.Fp32DenormalFloat;
                return s$iv == 0 ? o$iv : -o$iv;
            }
        } else {
            outM$iv = m$iv << 13;
            if (e$iv == 31) {
                outE$iv = 255;
                if (outM$iv != 0) {
                    outM$iv |= 4194304;
                }
            } else {
                outE$iv = (e$iv - 15) + 127;
            }
        }
        int out$iv = (s$iv << 16) | (outE$iv << 23) | outM$iv;
        return Float.intBitsToFloat(out$iv);
    }

    /* JADX INFO: renamed from: getAlpha-impl, reason: not valid java name */
    public static final float m5099getAlphaimpl(long j) {
        if (ULong.m9262constructorimpl(63 & j) == 0) {
            return ((float) UnsignedKt.ulongToDouble(ULong.m9262constructorimpl(ULong.m9262constructorimpl(j >>> 56) & 255))) / 255.0f;
        }
        return ((float) UnsignedKt.ulongToDouble(ULong.m9262constructorimpl(ULong.m9262constructorimpl(j >>> 6) & 1023))) / 1023.0f;
    }

    /* JADX INFO: renamed from: component1-impl, reason: not valid java name */
    public static final float m5088component1impl(long j) {
        return m5103getRedimpl(j);
    }

    /* JADX INFO: renamed from: component2-impl, reason: not valid java name */
    public static final float m5089component2impl(long j) {
        return m5102getGreenimpl(j);
    }

    /* JADX INFO: renamed from: component3-impl, reason: not valid java name */
    public static final float m5090component3impl(long j) {
        return m5100getBlueimpl(j);
    }

    /* JADX INFO: renamed from: component4-impl, reason: not valid java name */
    public static final float m5091component4impl(long j) {
        return m5099getAlphaimpl(j);
    }

    /* JADX INFO: renamed from: component5-impl, reason: not valid java name */
    public static final ColorSpace m5092component5impl(long j) {
        return m5101getColorSpaceimpl(j);
    }

    /* JADX INFO: renamed from: copy-wmQWz5c, reason: not valid java name */
    public static final long m5095copywmQWz5c(long j, float alpha, float red, float green, float blue) {
        return ColorKt.Color(red, green, blue, alpha, m5101getColorSpaceimpl(j));
    }

    public String toString() {
        return m5105toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5105toStringimpl(long j) {
        return "Color(" + m5103getRedimpl(j) + ", " + m5102getGreenimpl(j) + ", " + m5100getBlueimpl(j) + ", " + m5099getAlphaimpl(j) + ", " + m5101getColorSpaceimpl(j).getName() + ')';
    }

    /* JADX INFO: compiled from: Color.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u0002002\b\b\u0002\u00103\u001a\u0002002\b\b\u0002\u00104\u001a\u000205¢\u0006\u0004\b6\u00107J(\u00108\u001a\u0002002\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u0002002\u0006\u0010<\u001a\u0002002\u0006\u0010=\u001a\u000200H\u0002J9\u0010>\u001a\u00020\u00052\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u0010?\u001a\u0002002\b\b\u0002\u00103\u001a\u0002002\b\b\u0002\u00104\u001a\u000205¢\u0006\u0004\b@\u00107J(\u0010A\u001a\u0002002\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u0002002\u0006\u0010<\u001a\u0002002\u0006\u0010B\u001a\u000200H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\bR\u001e\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\bR\u001e\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\bR\u001e\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\bR\u001e\u0010\u0016\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0017\u0010\u0003\u001a\u0004\b\u0018\u0010\bR\u001e\u0010\u0019\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u001a\u0010\u0003\u001a\u0004\b\u001b\u0010\bR\u001e\u0010\u001c\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u001d\u0010\u0003\u001a\u0004\b\u001e\u0010\bR\u001e\u0010\u001f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b \u0010\u0003\u001a\u0004\b!\u0010\bR\u001e\u0010\"\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b#\u0010\u0003\u001a\u0004\b$\u0010\bR\u001e\u0010%\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b&\u0010\u0003\u001a\u0004\b'\u0010\bR\u001e\u0010(\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b)\u0010\u0003\u001a\u0004\b*\u0010\bR\u001e\u0010+\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b,\u0010\u0003\u001a\u0004\b-\u0010\b¨\u0006C"}, d2 = {"Landroidx/compose/ui/graphics/Color$Companion;", "", "<init>", "()V", "Black", "Landroidx/compose/ui/graphics/Color;", "getBlack-0d7_KjU$annotations", "getBlack-0d7_KjU", "()J", "J", "DarkGray", "getDarkGray-0d7_KjU$annotations", "getDarkGray-0d7_KjU", "Gray", "getGray-0d7_KjU$annotations", "getGray-0d7_KjU", "LightGray", "getLightGray-0d7_KjU$annotations", "getLightGray-0d7_KjU", "White", "getWhite-0d7_KjU$annotations", "getWhite-0d7_KjU", "Red", "getRed-0d7_KjU$annotations", "getRed-0d7_KjU", "Green", "getGreen-0d7_KjU$annotations", "getGreen-0d7_KjU", "Blue", "getBlue-0d7_KjU$annotations", "getBlue-0d7_KjU", "Yellow", "getYellow-0d7_KjU$annotations", "getYellow-0d7_KjU", "Cyan", "getCyan-0d7_KjU$annotations", "getCyan-0d7_KjU", "Magenta", "getMagenta-0d7_KjU$annotations", "getMagenta-0d7_KjU", "Transparent", "getTransparent-0d7_KjU$annotations", "getTransparent-0d7_KjU", "Unspecified", "getUnspecified-0d7_KjU$annotations", "getUnspecified-0d7_KjU", "hsv", "hue", "", "saturation", "value", "alpha", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/Rgb;", "hsv-JlNiLsg", "(FFFFLandroidx/compose/ui/graphics/colorspace/Rgb;)J", "hsvToRgbComponent", "n", "", CmcdHeadersFactory.STREAMING_FORMAT_HLS, CmcdHeadersFactory.STREAMING_FORMAT_SS, "v", "hsl", "lightness", "hsl-JlNiLsg", "hslToRgbComponent", CmcdHeadersFactory.STREAM_TYPE_LIVE, "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getBlack-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5108getBlack0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getBlue-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5109getBlue0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getCyan-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5110getCyan0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getDarkGray-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5111getDarkGray0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getGray-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5112getGray0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getGreen-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5113getGreen0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getLightGray-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5114getLightGray0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getMagenta-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5115getMagenta0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getRed-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5116getRed0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getTransparent-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5117getTransparent0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getUnspecified-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5118getUnspecified0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getWhite-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5119getWhite0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getYellow-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m5120getYellow0d7_KjU$annotations() {
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getBlack-0d7_KjU, reason: not valid java name */
        public final long m5123getBlack0d7_KjU() {
            return Color.Black;
        }

        /* JADX INFO: renamed from: getDarkGray-0d7_KjU, reason: not valid java name */
        public final long m5126getDarkGray0d7_KjU() {
            return Color.DarkGray;
        }

        /* JADX INFO: renamed from: getGray-0d7_KjU, reason: not valid java name */
        public final long m5127getGray0d7_KjU() {
            return Color.Gray;
        }

        /* JADX INFO: renamed from: getLightGray-0d7_KjU, reason: not valid java name */
        public final long m5129getLightGray0d7_KjU() {
            return Color.LightGray;
        }

        /* JADX INFO: renamed from: getWhite-0d7_KjU, reason: not valid java name */
        public final long m5134getWhite0d7_KjU() {
            return Color.White;
        }

        /* JADX INFO: renamed from: getRed-0d7_KjU, reason: not valid java name */
        public final long m5131getRed0d7_KjU() {
            return Color.Red;
        }

        /* JADX INFO: renamed from: getGreen-0d7_KjU, reason: not valid java name */
        public final long m5128getGreen0d7_KjU() {
            return Color.Green;
        }

        /* JADX INFO: renamed from: getBlue-0d7_KjU, reason: not valid java name */
        public final long m5124getBlue0d7_KjU() {
            return Color.Blue;
        }

        /* JADX INFO: renamed from: getYellow-0d7_KjU, reason: not valid java name */
        public final long m5135getYellow0d7_KjU() {
            return Color.Yellow;
        }

        /* JADX INFO: renamed from: getCyan-0d7_KjU, reason: not valid java name */
        public final long m5125getCyan0d7_KjU() {
            return Color.Cyan;
        }

        /* JADX INFO: renamed from: getMagenta-0d7_KjU, reason: not valid java name */
        public final long m5130getMagenta0d7_KjU() {
            return Color.Magenta;
        }

        /* JADX INFO: renamed from: getTransparent-0d7_KjU, reason: not valid java name */
        public final long m5132getTransparent0d7_KjU() {
            return Color.Transparent;
        }

        /* JADX INFO: renamed from: getUnspecified-0d7_KjU, reason: not valid java name */
        public final long m5133getUnspecified0d7_KjU() {
            return Color.Unspecified;
        }

        /* JADX INFO: renamed from: hsv-JlNiLsg$default, reason: not valid java name */
        public static /* synthetic */ long m5122hsvJlNiLsg$default(Companion companion, float f, float f2, float f3, float f4, Rgb rgb, int i, Object obj) {
            float f5;
            Rgb srgb;
            if ((i & 8) == 0) {
                f5 = f4;
            } else {
                f5 = 1.0f;
            }
            if ((i & 16) == 0) {
                srgb = rgb;
            } else {
                srgb = ColorSpaces.INSTANCE.getSrgb();
            }
            return companion.m5137hsvJlNiLsg(f, f2, f3, f5, srgb);
        }

        /* JADX INFO: renamed from: hsv-JlNiLsg, reason: not valid java name */
        public final long m5137hsvJlNiLsg(float hue, float saturation, float value, float alpha, Rgb colorSpace) {
            boolean value$iv = false;
            if (0.0f <= hue && hue <= 360.0f) {
                if (0.0f <= saturation && saturation <= 1.0f) {
                    if (0.0f <= value && value <= 1.0f) {
                        value$iv = true;
                    }
                }
            }
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("HSV (" + hue + ", " + saturation + ", " + value + ") must be in range (0..360, 0..1, 0..1)");
            }
            float red = hsvToRgbComponent(5, hue, saturation, value);
            float green = hsvToRgbComponent(3, hue, saturation, value);
            float blue = hsvToRgbComponent(1, hue, saturation, value);
            return ColorKt.Color(red, green, blue, alpha, colorSpace);
        }

        private final float hsvToRgbComponent(int n, float h, float s, float v) {
            float k = (n + (h / 60.0f)) % 6.0f;
            return v - ((v * s) * Math.max(0.0f, Math.min(k, Math.min(4.0f - k, 1.0f))));
        }

        /* JADX INFO: renamed from: hsl-JlNiLsg$default, reason: not valid java name */
        public static /* synthetic */ long m5121hslJlNiLsg$default(Companion companion, float f, float f2, float f3, float f4, Rgb rgb, int i, Object obj) {
            float f5;
            Rgb srgb;
            if ((i & 8) == 0) {
                f5 = f4;
            } else {
                f5 = 1.0f;
            }
            if ((i & 16) == 0) {
                srgb = rgb;
            } else {
                srgb = ColorSpaces.INSTANCE.getSrgb();
            }
            return companion.m5136hslJlNiLsg(f, f2, f3, f5, srgb);
        }

        /* JADX WARN: Code duplicated, block: B:24:0x002f  */
        /* JADX INFO: renamed from: hsl-JlNiLsg, reason: not valid java name */
        public final long m5136hslJlNiLsg(float hue, float saturation, float lightness, float alpha, Rgb colorSpace) {
            boolean value$iv;
            if (0.0f <= hue && hue <= 360.0f) {
                if (0.0f <= saturation && saturation <= 1.0f) {
                    value$iv = (0.0f > lightness ? 1 : (0.0f == lightness ? 0 : -1)) <= 0 && (lightness > 1.0f ? 1 : (lightness == 1.0f ? 0 : -1)) <= 0;
                }
            }
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("HSL (" + hue + ", " + saturation + ", " + lightness + ") must be in range (0..360, 0..1, 0..1)");
            }
            float red = hslToRgbComponent(0, hue, saturation, lightness);
            float green = hslToRgbComponent(8, hue, saturation, lightness);
            float blue = hslToRgbComponent(4, hue, saturation, lightness);
            return ColorKt.Color(red, green, blue, alpha, colorSpace);
        }

        private final float hslToRgbComponent(int n, float h, float s, float l) {
            float k = (n + (h / 30.0f)) % 12.0f;
            float a = Math.min(l, 1.0f - l) * s;
            return l - (Math.max(-1.0f, Math.min(k - 3.0f, Math.min(9.0f - k, 1.0f))) * a);
        }
    }
}
