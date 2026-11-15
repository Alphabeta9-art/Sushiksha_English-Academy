package androidx.compose.ui.graphics;

import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.colorspace.ColorModel;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.DoubleFunction;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: compiled from: Color.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a9\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u000e\u001a9\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0001¢\u0006\u0002\u0010\u000e\u001a\u0017\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001a\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001a5\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00112\b\b\u0001\u0010\t\u001a\u00020\u00112\b\b\u0001\u0010\n\u001a\u00020\u00112\b\b\u0003\u0010\u000b\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010\u0015\u001a)\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\b\b\u0001\u0010\u0019\u001a\u00020\bH\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001b\u0010\u001c\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a1\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bH\u0082\b\u001a\u0013\u0010&\u001a\u00020'*\u00020\u0006H\u0003¢\u0006\u0004\b(\u0010)\u001a\u0013\u0010*\u001a\u00020\b*\u00020\u0006H\u0007¢\u0006\u0004\b+\u0010,\u001a\u0013\u0010-\u001a\u00020\u0011*\u00020\u0006H\u0007¢\u0006\u0004\b.\u0010/\u001a\"\u00109\u001a\u00020\u0006*\u00020\u00062\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00060;H\u0086\b¢\u0006\u0004\b<\u0010=\"\u0018\u0010\u0000\u001a\u00020\u00018\u0000X\u0081T¢\u0006\n\n\u0002\u0010\u0004\u0012\u0004\b\u0002\u0010\u0003\"\u001f\u00100\u001a\u000201*\u00020\u00068Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u001f\u00106\u001a\u000201*\u00020\u00068Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b7\u00103\u001a\u0004\b8\u00105¨\u0006>"}, d2 = {"UnspecifiedColor", "Lkotlin/ULong;", "getUnspecifiedColor$annotations", "()V", "J", "Color", "Landroidx/compose/ui/graphics/Color;", "red", "", "green", "blue", "alpha", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "UncheckedColor", "color", "", "(I)J", "", "(J)J", "(IIII)J", "lerp", TtmlNode.START, "stop", "fraction", "lerp-jxsXWHM", "(JJF)J", "compositeOver", "background", "compositeOver--OWjLjI", "(JJ)J", "compositeComponent", "fgC", "bgC", "fgA", "bgA", CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, "getComponents", "", "getComponents-8_81llA", "(J)[F", "luminance", "luminance-8_81llA", "(J)F", "toArgb", "toArgb-8_81llA", "(J)I", "isSpecified", "", "isSpecified-8_81llA$annotations", "(J)V", "isSpecified-8_81llA", "(J)Z", "isUnspecified", "isUnspecified-8_81llA$annotations", "isUnspecified-8_81llA", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-DxMtmZc", "(JLkotlin/jvm/functions/Function0;)J", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ColorKt {
    public static final long UnspecifiedColor = 16;

    public static /* synthetic */ void getUnspecifiedColor$annotations() {
    }

    /* JADX INFO: renamed from: isSpecified-8_81llA$annotations, reason: not valid java name */
    public static /* synthetic */ void m5145isSpecified8_81llA$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified-8_81llA$annotations, reason: not valid java name */
    public static /* synthetic */ void m5147isUnspecified8_81llA$annotations(long j) {
    }

    public static /* synthetic */ long Color$default(float f, float f2, float f3, float f4, ColorSpace colorSpace, int i, Object obj) {
        if ((i & 8) != 0) {
            f4 = 1.0f;
        }
        if ((i & 16) != 0) {
            colorSpace = ColorSpaces.INSTANCE.getSrgb();
        }
        return Color(f, f2, f3, f4, colorSpace);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01dc  */
    /* JADX WARN: Code duplicated, block: B:104:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:107:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:109:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:111:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:113:0x0205  */
    /* JADX WARN: Code duplicated, block: B:114:0x0208 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:115:0x020a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:116:0x020c  */
    /* JADX WARN: Code duplicated, block: B:118:0x0216  */
    /* JADX WARN: Code duplicated, block: B:120:0x021c  */
    /* JADX WARN: Code duplicated, block: B:122:0x0223  */
    /* JADX WARN: Code duplicated, block: B:126:0x0242  */
    /* JADX WARN: Code duplicated, block: B:129:0x0249  */
    /* JADX WARN: Code duplicated, block: B:73:0x0156  */
    /* JADX WARN: Code duplicated, block: B:76:0x015d  */
    /* JADX WARN: Code duplicated, block: B:79:0x0170  */
    /* JADX WARN: Code duplicated, block: B:81:0x0174  */
    /* JADX WARN: Code duplicated, block: B:82:0x0177  */
    /* JADX WARN: Code duplicated, block: B:84:0x0180  */
    /* JADX WARN: Code duplicated, block: B:86:0x0186  */
    /* JADX WARN: Code duplicated, block: B:87:0x018d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x018f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:89:0x0191  */
    /* JADX WARN: Code duplicated, block: B:91:0x019d  */
    /* JADX WARN: Code duplicated, block: B:93:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:94:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:96:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:97:0x01bf  */
    public static final long Color(float red, float green, float blue, float alpha, ColorSpace colorSpace) {
        float f;
        short r;
        float minimumValue$iv;
        float maximumValue$iv;
        float $this$fastCoerceAtLeast$iv$iv;
        int s$iv;
        int e$iv;
        int m$iv;
        int outE$iv;
        int outM$iv;
        int e$iv2;
        short g;
        int m$iv2;
        float minimumValue$iv$iv;
        float maximumValue$iv$iv;
        int s$iv2;
        int e$iv3;
        int m$iv3;
        int outE$iv2;
        int outM$iv2;
        int e$iv4;
        short b;
        int m$iv4;
        float minimumValue$iv$iv2;
        float maximumValue$iv$iv2;
        int i;
        if (colorSpace.getIsSrgb()) {
            float minimumValue$iv$iv3 = 0.0f;
            if (alpha >= 0.0f) {
                minimumValue$iv$iv3 = alpha;
            }
            float maximumValue$iv$iv3 = 1.0f;
            if (minimumValue$iv$iv3 <= 1.0f) {
                maximumValue$iv$iv3 = minimumValue$iv$iv3;
            }
            int i2 = ((int) ((maximumValue$iv$iv3 * 255.0f) + 0.5f)) << 24;
            float minimumValue$iv$iv4 = 0.0f;
            if (red >= 0.0f) {
                minimumValue$iv$iv4 = red;
            }
            float maximumValue$iv$iv4 = 1.0f;
            if (minimumValue$iv$iv4 <= 1.0f) {
                maximumValue$iv$iv4 = minimumValue$iv$iv4;
            }
            int i3 = i2 | (((int) ((maximumValue$iv$iv4 * 255.0f) + 0.5f)) << 16);
            float minimumValue$iv$iv5 = 0.0f;
            if (green >= 0.0f) {
                minimumValue$iv$iv5 = green;
            }
            float maximumValue$iv$iv5 = 1.0f;
            if (minimumValue$iv$iv5 <= 1.0f) {
                maximumValue$iv$iv5 = minimumValue$iv$iv5;
            }
            int i4 = i3 | (((int) ((maximumValue$iv$iv5 * 255.0f) + 0.5f)) << 8);
            float minimumValue$iv$iv6 = 0.0f;
            if (blue >= 0.0f) {
                minimumValue$iv$iv6 = blue;
            }
            float maximumValue$iv$iv6 = 1.0f;
            if (minimumValue$iv$iv6 <= 1.0f) {
                maximumValue$iv$iv6 = minimumValue$iv$iv6;
            }
            int argb = i4 | ((int) ((maximumValue$iv$iv6 * 255.0f) + 0.5f));
            return Color.m5093constructorimpl(ULong.m9262constructorimpl(ULong.m9262constructorimpl(argb) << 32));
        }
        boolean value$iv = colorSpace.getComponentCount() == 3;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("Color only works with ColorSpaces with 3 components");
        }
        int id = colorSpace.getId();
        boolean value$iv2 = id != -1;
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalArgumentException("Unknown color space, please use a color space in ColorSpaces");
        }
        float minimumValue$iv2 = colorSpace.getMinValue(0);
        float maximumValue$iv2 = colorSpace.getMaxValue(0);
        float minimumValue$iv$iv7 = minimumValue$iv2;
        if (red >= minimumValue$iv$iv7) {
            minimumValue$iv$iv7 = red;
        }
        float maximumValue$iv$iv7 = maximumValue$iv2;
        if (minimumValue$iv$iv7 <= maximumValue$iv$iv7) {
            maximumValue$iv$iv7 = minimumValue$iv$iv7;
        }
        int bits$iv = Float.floatToRawIntBits(maximumValue$iv$iv7);
        int s$iv3 = bits$iv >>> 31;
        int e$iv5 = (bits$iv >>> 23) & 255;
        int i5 = 8388607;
        int m$iv5 = bits$iv & 8388607;
        int outE$iv3 = 0;
        int outM$iv3 = 0;
        if (e$iv5 == 255) {
            outE$iv3 = 31;
            outM$iv3 = m$iv5 != 0 ? 512 : 0;
            f = 0.5f;
        } else {
            int e$iv6 = e$iv5 + ComposerKt.defaultsKey + 15;
            if (e$iv6 >= 31) {
                outE$iv3 = 49;
                f = 0.5f;
            } else {
                if (e$iv6 > 0) {
                    f = 0.5f;
                    outE$iv3 = e$iv6;
                    outM$iv3 = m$iv5 >> 13;
                    if ((m$iv5 & 4096) != 0) {
                        int out$iv = (outE$iv3 << 10) | outM$iv3;
                        r = (short) ((out$iv + 1) | (s$iv3 << 15));
                    }
                    minimumValue$iv = colorSpace.getMinValue(1);
                    maximumValue$iv = colorSpace.getMaxValue(1);
                    $this$fastCoerceAtLeast$iv$iv = green;
                    if ($this$fastCoerceAtLeast$iv$iv < minimumValue$iv) {
                        $this$fastCoerceAtLeast$iv$iv = minimumValue$iv;
                    }
                    if ($this$fastCoerceAtLeast$iv$iv > maximumValue$iv) {
                        $this$fastCoerceAtLeast$iv$iv = maximumValue$iv;
                    }
                    int bits$iv2 = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv);
                    s$iv = bits$iv2 >>> 31;
                    e$iv = (bits$iv2 >>> 23) & 255;
                    m$iv = bits$iv2 & 8388607;
                    outE$iv = 0;
                    outM$iv = 0;
                    if (e$iv == 255) {
                        outE$iv = 31;
                        if (m$iv != 0) {
                            i = 512;
                        } else {
                            i = 0;
                        }
                        outM$iv = i;
                    } else {
                        e$iv2 = e$iv + ComposerKt.defaultsKey + 15;
                        if (e$iv2 >= 31) {
                            outE$iv = 49;
                        } else {
                            if (e$iv2 <= 0) {
                                outE$iv = e$iv2;
                                outM$iv = m$iv >> 13;
                                if ((m$iv & 4096) != 0) {
                                    int out$iv2 = (outE$iv << 10) | outM$iv;
                                    i5 = 8388607;
                                    g = (short) ((out$iv2 + 1) | (s$iv << 15));
                                }
                                float minimumValue$iv3 = colorSpace.getMinValue(2);
                                float maximumValue$iv3 = colorSpace.getMaxValue(2);
                                minimumValue$iv$iv = minimumValue$iv3;
                                if (blue >= minimumValue$iv$iv) {
                                    minimumValue$iv$iv = blue;
                                }
                                maximumValue$iv$iv = maximumValue$iv3;
                                if (minimumValue$iv$iv <= maximumValue$iv$iv) {
                                    maximumValue$iv$iv = minimumValue$iv$iv;
                                }
                                int bits$iv3 = Float.floatToRawIntBits(maximumValue$iv$iv);
                                s$iv2 = bits$iv3 >>> 31;
                                e$iv3 = (bits$iv3 >>> 23) & 255;
                                m$iv3 = bits$iv3 & i5;
                                outE$iv2 = 0;
                                outM$iv2 = 0;
                                if (e$iv3 == 255) {
                                    outE$iv2 = 31;
                                    outM$iv2 = m$iv3 == 0 ? 0 : 512;
                                } else {
                                    e$iv4 = e$iv3 + ComposerKt.defaultsKey + 15;
                                    if (e$iv4 >= 31) {
                                        outE$iv2 = 49;
                                    } else {
                                        if (e$iv4 <= 0) {
                                            outE$iv2 = e$iv4;
                                            outM$iv2 = m$iv3 >> 13;
                                            if ((m$iv3 & 4096) != 0) {
                                                int out$iv3 = (outE$iv2 << 10) | outM$iv2;
                                                b = (short) ((s$iv2 << 15) | (out$iv3 + 1));
                                            }
                                            minimumValue$iv$iv2 = 0.0f;
                                            if (alpha >= 0.0f) {
                                                minimumValue$iv$iv2 = alpha;
                                            }
                                            maximumValue$iv$iv2 = 1.0f;
                                            if (minimumValue$iv$iv2 <= 1.0f) {
                                                maximumValue$iv$iv2 = minimumValue$iv$iv2;
                                            }
                                            int a = (int) ((maximumValue$iv$iv2 * 1023.0f) + f);
                                            return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((((long) a) & 1023) << 6) | (((long) id) & 63)));
                                        }
                                        if (e$iv4 >= -10) {
                                            m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                                            if ((m$iv4 & 4096) != 0) {
                                                m$iv4 += 8192;
                                            }
                                            outM$iv2 = m$iv4 >> 13;
                                        }
                                    }
                                }
                                int out$iv4 = s$iv2 << 15;
                                b = (short) (out$iv4 | (outE$iv2 << 10) | outM$iv2);
                                minimumValue$iv$iv2 = 0.0f;
                                if (alpha >= 0.0f) {
                                    minimumValue$iv$iv2 = alpha;
                                }
                                maximumValue$iv$iv2 = 1.0f;
                                if (minimumValue$iv$iv2 <= 1.0f) {
                                    maximumValue$iv$iv2 = minimumValue$iv$iv2;
                                }
                                int a2 = (int) ((maximumValue$iv$iv2 * 1023.0f) + f);
                                return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((((long) a2) & 1023) << 6) | (((long) id) & 63)));
                            }
                            if (e$iv2 >= -10) {
                                m$iv2 = (m$iv | 8388608) >> (1 - e$iv2);
                                if ((m$iv2 & 4096) != 0) {
                                    m$iv2 += 8192;
                                }
                                outM$iv = m$iv2 >> 13;
                            }
                        }
                    }
                    g = (short) ((s$iv << 15) | (outE$iv << 10) | outM$iv);
                    float minimumValue$iv4 = colorSpace.getMinValue(2);
                    float maximumValue$iv4 = colorSpace.getMaxValue(2);
                    minimumValue$iv$iv = minimumValue$iv4;
                    if (blue >= minimumValue$iv$iv) {
                        minimumValue$iv$iv = blue;
                    }
                    maximumValue$iv$iv = maximumValue$iv4;
                    if (minimumValue$iv$iv <= maximumValue$iv$iv) {
                        maximumValue$iv$iv = minimumValue$iv$iv;
                    }
                    int bits$iv4 = Float.floatToRawIntBits(maximumValue$iv$iv);
                    s$iv2 = bits$iv4 >>> 31;
                    e$iv3 = (bits$iv4 >>> 23) & 255;
                    m$iv3 = bits$iv4 & i5;
                    outE$iv2 = 0;
                    outM$iv2 = 0;
                    if (e$iv3 == 255) {
                        outE$iv2 = 31;
                        outM$iv2 = m$iv3 == 0 ? 0 : 512;
                    } else {
                        e$iv4 = e$iv3 + ComposerKt.defaultsKey + 15;
                        if (e$iv4 >= 31) {
                            outE$iv2 = 49;
                        } else {
                            if (e$iv4 <= 0) {
                                outE$iv2 = e$iv4;
                                outM$iv2 = m$iv3 >> 13;
                                if ((m$iv3 & 4096) != 0) {
                                    int out$iv5 = (outE$iv2 << 10) | outM$iv2;
                                    b = (short) ((s$iv2 << 15) | (out$iv5 + 1));
                                }
                                minimumValue$iv$iv2 = 0.0f;
                                if (alpha >= 0.0f) {
                                    minimumValue$iv$iv2 = alpha;
                                }
                                maximumValue$iv$iv2 = 1.0f;
                                if (minimumValue$iv$iv2 <= 1.0f) {
                                    maximumValue$iv$iv2 = minimumValue$iv$iv2;
                                }
                                int a3 = (int) ((maximumValue$iv$iv2 * 1023.0f) + f);
                                return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((((long) a3) & 1023) << 6) | (((long) id) & 63)));
                            }
                            if (e$iv4 >= -10) {
                                m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                                if ((m$iv4 & 4096) != 0) {
                                    m$iv4 += 8192;
                                }
                                outM$iv2 = m$iv4 >> 13;
                            }
                        }
                    }
                    int out$iv6 = s$iv2 << 15;
                    b = (short) (out$iv6 | (outE$iv2 << 10) | outM$iv2);
                    minimumValue$iv$iv2 = 0.0f;
                    if (alpha >= 0.0f) {
                        minimumValue$iv$iv2 = alpha;
                    }
                    maximumValue$iv$iv2 = 1.0f;
                    if (minimumValue$iv$iv2 <= 1.0f) {
                        maximumValue$iv$iv2 = minimumValue$iv$iv2;
                    }
                    int a4 = (int) ((maximumValue$iv$iv2 * 1023.0f) + f);
                    return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((((long) a4) & 1023) << 6) | (((long) id) & 63)));
                }
                if (e$iv6 >= -10) {
                    int m$iv6 = (m$iv5 | 8388608) >> (1 - e$iv6);
                    f = 0.5f;
                    if ((m$iv6 & 4096) != 0) {
                        m$iv6 += 8192;
                    }
                    outM$iv3 = m$iv6 >> 13;
                } else {
                    f = 0.5f;
                }
            }
        }
        int out$iv7 = s$iv3 << 15;
        r = (short) (out$iv7 | (outE$iv3 << 10) | outM$iv3);
        minimumValue$iv = colorSpace.getMinValue(1);
        maximumValue$iv = colorSpace.getMaxValue(1);
        $this$fastCoerceAtLeast$iv$iv = green;
        if ($this$fastCoerceAtLeast$iv$iv < minimumValue$iv) {
            $this$fastCoerceAtLeast$iv$iv = minimumValue$iv;
        }
        if ($this$fastCoerceAtLeast$iv$iv > maximumValue$iv) {
            $this$fastCoerceAtLeast$iv$iv = maximumValue$iv;
        }
        int bits$iv5 = Float.floatToRawIntBits($this$fastCoerceAtLeast$iv$iv);
        s$iv = bits$iv5 >>> 31;
        e$iv = (bits$iv5 >>> 23) & 255;
        m$iv = bits$iv5 & 8388607;
        outE$iv = 0;
        outM$iv = 0;
        if (e$iv == 255) {
            outE$iv = 31;
            if (m$iv != 0) {
                i = 512;
            } else {
                i = 0;
            }
            outM$iv = i;
        } else {
            e$iv2 = e$iv + ComposerKt.defaultsKey + 15;
            if (e$iv2 >= 31) {
                outE$iv = 49;
            } else {
                if (e$iv2 <= 0) {
                    outE$iv = e$iv2;
                    outM$iv = m$iv >> 13;
                    if ((m$iv & 4096) != 0) {
                        int out$iv8 = (outE$iv << 10) | outM$iv;
                        i5 = 8388607;
                        g = (short) ((out$iv8 + 1) | (s$iv << 15));
                    }
                    float minimumValue$iv5 = colorSpace.getMinValue(2);
                    float maximumValue$iv5 = colorSpace.getMaxValue(2);
                    minimumValue$iv$iv = minimumValue$iv5;
                    if (blue >= minimumValue$iv$iv) {
                        minimumValue$iv$iv = blue;
                    }
                    maximumValue$iv$iv = maximumValue$iv5;
                    if (minimumValue$iv$iv <= maximumValue$iv$iv) {
                        maximumValue$iv$iv = minimumValue$iv$iv;
                    }
                    int bits$iv6 = Float.floatToRawIntBits(maximumValue$iv$iv);
                    s$iv2 = bits$iv6 >>> 31;
                    e$iv3 = (bits$iv6 >>> 23) & 255;
                    m$iv3 = bits$iv6 & i5;
                    outE$iv2 = 0;
                    outM$iv2 = 0;
                    if (e$iv3 == 255) {
                        outE$iv2 = 31;
                        outM$iv2 = m$iv3 == 0 ? 0 : 512;
                    } else {
                        e$iv4 = e$iv3 + ComposerKt.defaultsKey + 15;
                        if (e$iv4 >= 31) {
                            outE$iv2 = 49;
                        } else {
                            if (e$iv4 <= 0) {
                                outE$iv2 = e$iv4;
                                outM$iv2 = m$iv3 >> 13;
                                if ((m$iv3 & 4096) != 0) {
                                    int out$iv9 = (outE$iv2 << 10) | outM$iv2;
                                    b = (short) ((s$iv2 << 15) | (out$iv9 + 1));
                                }
                                minimumValue$iv$iv2 = 0.0f;
                                if (alpha >= 0.0f) {
                                    minimumValue$iv$iv2 = alpha;
                                }
                                maximumValue$iv$iv2 = 1.0f;
                                if (minimumValue$iv$iv2 <= 1.0f) {
                                    maximumValue$iv$iv2 = minimumValue$iv$iv2;
                                }
                                int a5 = (int) ((maximumValue$iv$iv2 * 1023.0f) + f);
                                return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((((long) a5) & 1023) << 6) | (((long) id) & 63)));
                            }
                            if (e$iv4 >= -10) {
                                m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                                if ((m$iv4 & 4096) != 0) {
                                    m$iv4 += 8192;
                                }
                                outM$iv2 = m$iv4 >> 13;
                            }
                        }
                    }
                    int out$iv10 = s$iv2 << 15;
                    b = (short) (out$iv10 | (outE$iv2 << 10) | outM$iv2);
                    minimumValue$iv$iv2 = 0.0f;
                    if (alpha >= 0.0f) {
                        minimumValue$iv$iv2 = alpha;
                    }
                    maximumValue$iv$iv2 = 1.0f;
                    if (minimumValue$iv$iv2 <= 1.0f) {
                        maximumValue$iv$iv2 = minimumValue$iv$iv2;
                    }
                    int a6 = (int) ((maximumValue$iv$iv2 * 1023.0f) + f);
                    return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((((long) a6) & 1023) << 6) | (((long) id) & 63)));
                }
                if (e$iv2 >= -10) {
                    m$iv2 = (m$iv | 8388608) >> (1 - e$iv2);
                    if ((m$iv2 & 4096) != 0) {
                        m$iv2 += 8192;
                    }
                    outM$iv = m$iv2 >> 13;
                }
            }
        }
        g = (short) ((s$iv << 15) | (outE$iv << 10) | outM$iv);
        float minimumValue$iv6 = colorSpace.getMinValue(2);
        float maximumValue$iv6 = colorSpace.getMaxValue(2);
        minimumValue$iv$iv = minimumValue$iv6;
        if (blue >= minimumValue$iv$iv) {
            minimumValue$iv$iv = blue;
        }
        maximumValue$iv$iv = maximumValue$iv6;
        if (minimumValue$iv$iv <= maximumValue$iv$iv) {
            maximumValue$iv$iv = minimumValue$iv$iv;
        }
        int bits$iv7 = Float.floatToRawIntBits(maximumValue$iv$iv);
        s$iv2 = bits$iv7 >>> 31;
        e$iv3 = (bits$iv7 >>> 23) & 255;
        m$iv3 = bits$iv7 & i5;
        outE$iv2 = 0;
        outM$iv2 = 0;
        if (e$iv3 == 255) {
            outE$iv2 = 31;
            outM$iv2 = m$iv3 == 0 ? 0 : 512;
        } else {
            e$iv4 = e$iv3 + ComposerKt.defaultsKey + 15;
            if (e$iv4 >= 31) {
                outE$iv2 = 49;
            } else {
                if (e$iv4 <= 0) {
                    outE$iv2 = e$iv4;
                    outM$iv2 = m$iv3 >> 13;
                    if ((m$iv3 & 4096) != 0) {
                        int out$iv11 = (outE$iv2 << 10) | outM$iv2;
                        b = (short) ((s$iv2 << 15) | (out$iv11 + 1));
                    }
                    minimumValue$iv$iv2 = 0.0f;
                    if (alpha >= 0.0f) {
                        minimumValue$iv$iv2 = alpha;
                    }
                    maximumValue$iv$iv2 = 1.0f;
                    if (minimumValue$iv$iv2 <= 1.0f) {
                        maximumValue$iv$iv2 = minimumValue$iv$iv2;
                    }
                    int a7 = (int) ((maximumValue$iv$iv2 * 1023.0f) + f);
                    return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((((long) a7) & 1023) << 6) | (((long) id) & 63)));
                }
                if (e$iv4 >= -10) {
                    m$iv4 = (m$iv3 | 8388608) >> (1 - e$iv4);
                    if ((m$iv4 & 4096) != 0) {
                        m$iv4 += 8192;
                    }
                    outM$iv2 = m$iv4 >> 13;
                }
            }
        }
        int out$iv12 = s$iv2 << 15;
        b = (short) (out$iv12 | (outE$iv2 << 10) | outM$iv2);
        minimumValue$iv$iv2 = 0.0f;
        if (alpha >= 0.0f) {
            minimumValue$iv$iv2 = alpha;
        }
        maximumValue$iv$iv2 = 1.0f;
        if (minimumValue$iv$iv2 <= 1.0f) {
            maximumValue$iv$iv2 = minimumValue$iv$iv2;
        }
        int a8 = (int) ((maximumValue$iv$iv2 * 1023.0f) + f);
        return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 16) | ((((long) a8) & 1023) << 6) | (((long) id) & 63)));
    }

    public static /* synthetic */ long UncheckedColor$default(float f, float f2, float f3, float f4, ColorSpace colorSpace, int i, Object obj) {
        if ((i & 8) != 0) {
            f4 = 1.0f;
        }
        if ((i & 16) != 0) {
            colorSpace = ColorSpaces.INSTANCE.getSrgb();
        }
        return UncheckedColor(f, f2, f3, f4, colorSpace);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:32:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:33:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:35:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:37:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:38:0x00de A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:39:0x00e0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:44:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:45:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:47:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:48:0x010b  */
    /* JADX WARN: Code duplicated, block: B:52:0x0127  */
    /* JADX WARN: Code duplicated, block: B:54:0x012b  */
    /* JADX WARN: Code duplicated, block: B:56:0x0130  */
    /* JADX WARN: Code duplicated, block: B:58:0x0136  */
    /* JADX WARN: Code duplicated, block: B:59:0x0139 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x013b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:61:0x013d  */
    /* JADX WARN: Code duplicated, block: B:63:0x0145  */
    /* JADX WARN: Code duplicated, block: B:65:0x014b  */
    /* JADX WARN: Code duplicated, block: B:67:0x0152  */
    public static final long UncheckedColor(float red, float green, float blue, float alpha, ColorSpace colorSpace) {
        char c;
        short r;
        int s$iv;
        int e$iv;
        int m$iv;
        int outE$iv;
        int outM$iv;
        int e$iv2;
        short g;
        int m$iv2;
        int s$iv2;
        int e$iv3;
        int m$iv3;
        int outE$iv2;
        int outM$iv2;
        int e$iv4;
        short b;
        int m$iv4;
        int i;
        float f = 0.5f;
        if (colorSpace.getIsSrgb()) {
            int argb = (((int) ((alpha * 255.0f) + 0.5f)) << 24) | (((int) ((red * 255.0f) + 0.5f)) << 16) | (((int) ((green * 255.0f) + 0.5f)) << 8) | ((int) ((255.0f * blue) + 0.5f));
            return Color.m5093constructorimpl(ULong.m9262constructorimpl(ULong.m9262constructorimpl(argb) << 32));
        }
        int bits$iv = Float.floatToRawIntBits(red);
        int s$iv3 = bits$iv >>> 31;
        int e$iv5 = (bits$iv >>> 23) & 255;
        int i2 = 8388607;
        int m$iv5 = bits$iv & 8388607;
        int outE$iv3 = 0;
        int outM$iv3 = 0;
        if (e$iv5 == 255) {
            outE$iv3 = 31;
            outM$iv3 = m$iv5 != 0 ? 512 : 0;
            c = 16;
        } else {
            int e$iv6 = e$iv5 + ComposerKt.defaultsKey + 15;
            if (e$iv6 >= 31) {
                outE$iv3 = 49;
                c = 16;
            } else {
                if (e$iv6 > 0) {
                    c = 16;
                    outE$iv3 = e$iv6;
                    outM$iv3 = m$iv5 >> 13;
                    if ((m$iv5 & 4096) != 0) {
                        int out$iv = (outE$iv3 << 10) | outM$iv3;
                        f = 0.5f;
                        r = (short) ((out$iv + 1) | (s$iv3 << 15));
                    }
                    int bits$iv2 = Float.floatToRawIntBits(green);
                    s$iv = bits$iv2 >>> 31;
                    e$iv = (bits$iv2 >>> 23) & 255;
                    m$iv = bits$iv2 & 8388607;
                    outE$iv = 0;
                    outM$iv = 0;
                    if (e$iv == 255) {
                        outE$iv = 31;
                        if (m$iv != 0) {
                            i = 512;
                        } else {
                            i = 0;
                        }
                        outM$iv = i;
                    } else {
                        e$iv2 = e$iv + ComposerKt.defaultsKey + 15;
                        if (e$iv2 >= 31) {
                            outE$iv = 49;
                        } else {
                            if (e$iv2 <= 0) {
                                outE$iv = e$iv2;
                                outM$iv = m$iv >> 13;
                                if ((m$iv & 4096) != 0) {
                                    int out$iv2 = (outE$iv << 10) | outM$iv;
                                    i2 = 8388607;
                                    g = (short) ((out$iv2 + 1) | (s$iv << 15));
                                }
                                int bits$iv3 = Float.floatToRawIntBits(blue);
                                s$iv2 = bits$iv3 >>> 31;
                                e$iv3 = (bits$iv3 >>> 23) & 255;
                                m$iv3 = bits$iv3 & i2;
                                outE$iv2 = 0;
                                outM$iv2 = 0;
                                if (e$iv3 == 255) {
                                    outE$iv2 = 31;
                                    outM$iv2 = m$iv3 == 0 ? 0 : 512;
                                } else {
                                    e$iv4 = e$iv3 + ComposerKt.defaultsKey + 15;
                                    if (e$iv4 >= 31) {
                                        outE$iv2 = 49;
                                    } else {
                                        if (e$iv4 <= 0) {
                                            outE$iv2 = e$iv4;
                                            outM$iv2 = m$iv3 >> 13;
                                            if ((m$iv3 & 4096) != 0) {
                                                int out$iv3 = (outE$iv2 << 10) | outM$iv2;
                                                b = (short) ((s$iv2 << 15) | (out$iv3 + 1));
                                            }
                                            int a = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + f);
                                            int id = colorSpace.getId();
                                            return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << c) | ((((long) a) & 1023) << 6) | (((long) id) & 63)));
                                        }
                                        if (e$iv4 >= -10) {
                                            m$iv4 = (8388608 | m$iv3) >> (1 - e$iv4);
                                            if ((m$iv4 & 4096) != 0) {
                                                m$iv4 += 8192;
                                            }
                                            outM$iv2 = m$iv4 >> 13;
                                        }
                                    }
                                }
                                int out$iv4 = s$iv2 << 15;
                                b = (short) (out$iv4 | (outE$iv2 << 10) | outM$iv2);
                                int a2 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + f);
                                int id2 = colorSpace.getId();
                                return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << c) | ((((long) a2) & 1023) << 6) | (((long) id2) & 63)));
                            }
                            if (e$iv2 >= -10) {
                                m$iv2 = (m$iv | 8388608) >> (1 - e$iv2);
                                if ((m$iv2 & 4096) != 0) {
                                    m$iv2 += 8192;
                                }
                                outM$iv = m$iv2 >> 13;
                            }
                        }
                    }
                    g = (short) ((s$iv << 15) | (outE$iv << 10) | outM$iv);
                    int bits$iv4 = Float.floatToRawIntBits(blue);
                    s$iv2 = bits$iv4 >>> 31;
                    e$iv3 = (bits$iv4 >>> 23) & 255;
                    m$iv3 = bits$iv4 & i2;
                    outE$iv2 = 0;
                    outM$iv2 = 0;
                    if (e$iv3 == 255) {
                        outE$iv2 = 31;
                        outM$iv2 = m$iv3 == 0 ? 0 : 512;
                    } else {
                        e$iv4 = e$iv3 + ComposerKt.defaultsKey + 15;
                        if (e$iv4 >= 31) {
                            outE$iv2 = 49;
                        } else {
                            if (e$iv4 <= 0) {
                                outE$iv2 = e$iv4;
                                outM$iv2 = m$iv3 >> 13;
                                if ((m$iv3 & 4096) != 0) {
                                    int out$iv5 = (outE$iv2 << 10) | outM$iv2;
                                    b = (short) ((s$iv2 << 15) | (out$iv5 + 1));
                                }
                                int a3 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + f);
                                int id3 = colorSpace.getId();
                                return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << c) | ((((long) a3) & 1023) << 6) | (((long) id3) & 63)));
                            }
                            if (e$iv4 >= -10) {
                                m$iv4 = (8388608 | m$iv3) >> (1 - e$iv4);
                                if ((m$iv4 & 4096) != 0) {
                                    m$iv4 += 8192;
                                }
                                outM$iv2 = m$iv4 >> 13;
                            }
                        }
                    }
                    int out$iv6 = s$iv2 << 15;
                    b = (short) (out$iv6 | (outE$iv2 << 10) | outM$iv2);
                    int a4 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + f);
                    int id4 = colorSpace.getId();
                    return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << c) | ((((long) a4) & 1023) << 6) | (((long) id4) & 63)));
                }
                if (e$iv6 >= -10) {
                    int m$iv6 = (m$iv5 | 8388608) >> (1 - e$iv6);
                    c = 16;
                    if ((m$iv6 & 4096) != 0) {
                        m$iv6 += 8192;
                    }
                    outM$iv3 = m$iv6 >> 13;
                } else {
                    c = 16;
                }
            }
        }
        r = (short) ((s$iv3 << 15) | (outE$iv3 << 10) | outM$iv3);
        int bits$iv5 = Float.floatToRawIntBits(green);
        s$iv = bits$iv5 >>> 31;
        e$iv = (bits$iv5 >>> 23) & 255;
        m$iv = bits$iv5 & 8388607;
        outE$iv = 0;
        outM$iv = 0;
        if (e$iv == 255) {
            outE$iv = 31;
            if (m$iv != 0) {
                i = 512;
            } else {
                i = 0;
            }
            outM$iv = i;
        } else {
            e$iv2 = e$iv + ComposerKt.defaultsKey + 15;
            if (e$iv2 >= 31) {
                outE$iv = 49;
            } else {
                if (e$iv2 <= 0) {
                    outE$iv = e$iv2;
                    outM$iv = m$iv >> 13;
                    if ((m$iv & 4096) != 0) {
                        int out$iv7 = (outE$iv << 10) | outM$iv;
                        i2 = 8388607;
                        g = (short) ((out$iv7 + 1) | (s$iv << 15));
                    }
                    int bits$iv6 = Float.floatToRawIntBits(blue);
                    s$iv2 = bits$iv6 >>> 31;
                    e$iv3 = (bits$iv6 >>> 23) & 255;
                    m$iv3 = bits$iv6 & i2;
                    outE$iv2 = 0;
                    outM$iv2 = 0;
                    if (e$iv3 == 255) {
                        outE$iv2 = 31;
                        outM$iv2 = m$iv3 == 0 ? 0 : 512;
                    } else {
                        e$iv4 = e$iv3 + ComposerKt.defaultsKey + 15;
                        if (e$iv4 >= 31) {
                            outE$iv2 = 49;
                        } else {
                            if (e$iv4 <= 0) {
                                outE$iv2 = e$iv4;
                                outM$iv2 = m$iv3 >> 13;
                                if ((m$iv3 & 4096) != 0) {
                                    int out$iv8 = (outE$iv2 << 10) | outM$iv2;
                                    b = (short) ((s$iv2 << 15) | (out$iv8 + 1));
                                }
                                int a5 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + f);
                                int id5 = colorSpace.getId();
                                return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << c) | ((((long) a5) & 1023) << 6) | (((long) id5) & 63)));
                            }
                            if (e$iv4 >= -10) {
                                m$iv4 = (8388608 | m$iv3) >> (1 - e$iv4);
                                if ((m$iv4 & 4096) != 0) {
                                    m$iv4 += 8192;
                                }
                                outM$iv2 = m$iv4 >> 13;
                            }
                        }
                    }
                    int out$iv9 = s$iv2 << 15;
                    b = (short) (out$iv9 | (outE$iv2 << 10) | outM$iv2);
                    int a6 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + f);
                    int id6 = colorSpace.getId();
                    return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << c) | ((((long) a6) & 1023) << 6) | (((long) id6) & 63)));
                }
                if (e$iv2 >= -10) {
                    m$iv2 = (m$iv | 8388608) >> (1 - e$iv2);
                    if ((m$iv2 & 4096) != 0) {
                        m$iv2 += 8192;
                    }
                    outM$iv = m$iv2 >> 13;
                }
            }
        }
        g = (short) ((s$iv << 15) | (outE$iv << 10) | outM$iv);
        int bits$iv7 = Float.floatToRawIntBits(blue);
        s$iv2 = bits$iv7 >>> 31;
        e$iv3 = (bits$iv7 >>> 23) & 255;
        m$iv3 = bits$iv7 & i2;
        outE$iv2 = 0;
        outM$iv2 = 0;
        if (e$iv3 == 255) {
            outE$iv2 = 31;
            outM$iv2 = m$iv3 == 0 ? 0 : 512;
        } else {
            e$iv4 = e$iv3 + ComposerKt.defaultsKey + 15;
            if (e$iv4 >= 31) {
                outE$iv2 = 49;
            } else {
                if (e$iv4 <= 0) {
                    outE$iv2 = e$iv4;
                    outM$iv2 = m$iv3 >> 13;
                    if ((m$iv3 & 4096) != 0) {
                        int out$iv10 = (outE$iv2 << 10) | outM$iv2;
                        b = (short) ((s$iv2 << 15) | (out$iv10 + 1));
                    }
                    int a7 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + f);
                    int id7 = colorSpace.getId();
                    return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << c) | ((((long) a7) & 1023) << 6) | (((long) id7) & 63)));
                }
                if (e$iv4 >= -10) {
                    m$iv4 = (8388608 | m$iv3) >> (1 - e$iv4);
                    if ((m$iv4 & 4096) != 0) {
                        m$iv4 += 8192;
                    }
                    outM$iv2 = m$iv4 >> 13;
                }
            }
        }
        int out$iv11 = s$iv2 << 15;
        b = (short) (out$iv11 | (outE$iv2 << 10) | outM$iv2);
        int a8 = (int) ((Math.max(0.0f, Math.min(alpha, 1.0f)) * 1023.0f) + f);
        int id8 = colorSpace.getId();
        return Color.m5093constructorimpl(ULong.m9262constructorimpl(((((long) r) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 48) | ((((long) g) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 32) | ((((long) b) & WebSocketProtocol.PAYLOAD_SHORT_MAX) << c) | ((((long) a8) & 1023) << 6) | (((long) id8) & 63)));
    }

    public static final long Color(int color) {
        return Color.m5093constructorimpl(ULong.m9262constructorimpl(ULong.m9262constructorimpl(color) << 32));
    }

    public static final long Color(long color) {
        return Color.m5093constructorimpl(ULong.m9262constructorimpl(color << 32));
    }

    public static /* synthetic */ long Color$default(int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 8) != 0) {
            i4 = 255;
        }
        return Color(i, i2, i3, i4);
    }

    public static final long Color(int red, int green, int blue, int alpha) {
        int color = ((alpha & 255) << 24) | ((red & 255) << 16) | ((green & 255) << 8) | (blue & 255);
        return Color(color);
    }

    /* JADX INFO: renamed from: lerp-jxsXWHM, reason: not valid java name */
    public static final long m5148lerpjxsXWHM(long j, long j2, float fraction) {
        ColorSpace colorSpace = ColorSpaces.INSTANCE.getOklab();
        long startColor = Color.m5094convertvNxB06k(j, colorSpace);
        long endColor = Color.m5094convertvNxB06k(j2, colorSpace);
        float startAlpha = Color.m5099getAlphaimpl(startColor);
        float startL = Color.m5103getRedimpl(startColor);
        float startA = Color.m5102getGreenimpl(startColor);
        float startB = Color.m5100getBlueimpl(startColor);
        float endAlpha = Color.m5099getAlphaimpl(endColor);
        float endL = Color.m5103getRedimpl(endColor);
        float endA = Color.m5102getGreenimpl(endColor);
        float endB = Color.m5100getBlueimpl(endColor);
        float minimumValue$iv$iv = 0.0f;
        if (fraction >= 0.0f) {
            minimumValue$iv$iv = fraction;
        }
        float maximumValue$iv$iv = 1.0f;
        if (minimumValue$iv$iv <= 1.0f) {
            maximumValue$iv$iv = minimumValue$iv$iv;
        }
        float minimumValue$iv = maximumValue$iv$iv;
        long interpolated = UncheckedColor(MathHelpersKt.lerp(startL, endL, minimumValue$iv), MathHelpersKt.lerp(startA, endA, minimumValue$iv), MathHelpersKt.lerp(startB, endB, minimumValue$iv), MathHelpersKt.lerp(startAlpha, endAlpha, minimumValue$iv), colorSpace);
        return Color.m5094convertvNxB06k(interpolated, Color.m5101getColorSpaceimpl(j2));
    }

    /* JADX INFO: renamed from: compositeOver--OWjLjI, reason: not valid java name */
    public static final long m5142compositeOverOWjLjI(long j, long j2) {
        float r;
        float f;
        long fg = Color.m5094convertvNxB06k(j, Color.m5101getColorSpaceimpl(j2));
        float bgA = Color.m5099getAlphaimpl(j2);
        float fgA = Color.m5099getAlphaimpl(fg);
        float a = ((1.0f - fgA) * bgA) + fgA;
        float fgC$iv = Color.m5103getRedimpl(fg);
        float bgC$iv = Color.m5103getRedimpl(j2);
        float b = 0.0f;
        if (!(a == 0.0f)) {
            r = ((fgC$iv * fgA) + ((bgC$iv * bgA) * (1.0f - fgA))) / a;
        } else {
            r = 0.0f;
        }
        float fgC$iv2 = Color.m5102getGreenimpl(fg);
        float bgC$iv2 = Color.m5102getGreenimpl(j2);
        if (!(a == 0.0f)) {
            f = ((fgC$iv2 * fgA) + ((bgC$iv2 * bgA) * (1.0f - fgA))) / a;
        } else {
            f = 0.0f;
        }
        float fgC$iv3 = f;
        float fgC$iv4 = Color.m5100getBlueimpl(fg);
        float bgC$iv3 = Color.m5100getBlueimpl(j2);
        if (!(a == 0.0f)) {
            b = ((fgC$iv4 * fgA) + ((bgC$iv3 * bgA) * (1.0f - fgA))) / a;
        }
        return UncheckedColor(r, fgC$iv3, b, a, Color.m5101getColorSpaceimpl(j2));
    }

    private static final float compositeComponent(float fgC, float bgC, float fgA, float bgA, float a) {
        if (a == 0.0f) {
            return 0.0f;
        }
        return ((fgC * fgA) + ((bgC * bgA) * (1.0f - fgA))) / a;
    }

    /* JADX INFO: renamed from: getComponents-8_81llA, reason: not valid java name */
    private static final float[] m5143getComponents8_81llA(long j) {
        return new float[]{Color.m5103getRedimpl(j), Color.m5102getGreenimpl(j), Color.m5100getBlueimpl(j), Color.m5099getAlphaimpl(j)};
    }

    /* JADX INFO: renamed from: luminance-8_81llA, reason: not valid java name */
    public static final float m5149luminance8_81llA(long j) {
        ColorSpace colorSpace = Color.m5101getColorSpaceimpl(j);
        boolean value$iv = ColorModel.m5527equalsimpl0(colorSpace.getModel(), ColorModel.INSTANCE.m5534getRgbxdoWZVw());
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("The specified color must be encoded in an RGB color space. The supplied color space is " + ((Object) ColorModel.m5530toStringimpl(colorSpace.getModel())));
        }
        Intrinsics.checkNotNull(colorSpace, "null cannot be cast to non-null type androidx.compose.ui.graphics.colorspace.Rgb");
        DoubleFunction eotf = ((Rgb) colorSpace).getEotfFunc();
        double r = eotf.invoke(Color.m5103getRedimpl(j));
        double g = eotf.invoke(Color.m5102getGreenimpl(j));
        double b = eotf.invoke(Color.m5100getBlueimpl(j));
        float $this$fastCoerceIn$iv = (float) ((0.2126d * r) + (0.7152d * g) + (0.0722d * b));
        float minimumValue$iv$iv = 0.0f;
        if ($this$fastCoerceIn$iv >= 0.0f) {
            minimumValue$iv$iv = $this$fastCoerceIn$iv;
        }
        if (minimumValue$iv$iv > 1.0f) {
            return 1.0f;
        }
        float maximumValue$iv$iv = minimumValue$iv$iv;
        return maximumValue$iv$iv;
    }

    /* JADX INFO: renamed from: toArgb-8_81llA, reason: not valid java name */
    public static final int m5151toArgb8_81llA(long j) {
        return (int) ULong.m9262constructorimpl(Color.m5094convertvNxB06k(j, ColorSpaces.INSTANCE.getSrgb()) >>> 32);
    }

    /* JADX INFO: renamed from: isSpecified-8_81llA, reason: not valid java name */
    public static final boolean m5144isSpecified8_81llA(long j) {
        return j != 16;
    }

    /* JADX INFO: renamed from: isUnspecified-8_81llA, reason: not valid java name */
    public static final boolean m5146isUnspecified8_81llA(long j) {
        return j == 16;
    }

    /* JADX INFO: renamed from: takeOrElse-DxMtmZc, reason: not valid java name */
    public static final long m5150takeOrElseDxMtmZc(long j, Function0<Color> function0) {
        return (j > 16L ? 1 : (j == 16L ? 0 : -1)) != 0 ? j : function0.invoke().m5107unboximpl();
    }
}
