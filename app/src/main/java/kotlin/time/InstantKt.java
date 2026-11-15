package kotlin.time;

import androidx.media3.exoplayer.upstream.CmcdHeadersFactory;
import com.facebook.imagepipeline.common.RotationOptions;
import java.io.IOException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: Instant.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\f\n\u0002\u0010\u0015\n\u0002\b\u0006\u001a\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0003\u001a\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0003\u001a'\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0082\b\u001a'\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0082\b\u001a\u0010\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u0015H\u0000\u001a\u0014\u0010'\u001a\u00020\u0015*\u00020\u00152\u0006\u0010%\u001a\u00020\u0001H\u0002\u001a\u0014\u0010-\u001a\u00020\u0012*\u00020\u00102\u0006\u0010.\u001a\u00020\u0015H\u0002\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0000\u0010\u0005\"\u001f\u0010\u0006\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\u0004\u001a\u0004\b\u0006\u0010\u0005\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0015X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010*\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010+\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010,\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"isDistantPast", "", "Lkotlin/time/Instant;", "isDistantPast$annotations", "(Lkotlin/time/Instant;)V", "(Lkotlin/time/Instant;)Z", "isDistantFuture", "isDistantFuture$annotations", "DISTANT_PAST_SECONDS", "", "DISTANT_FUTURE_SECONDS", "MIN_SECOND", "MAX_SECOND", "parseIso", "Lkotlin/time/InstantParseResult;", "isoString", "", "formatIso", "", "instant", "DAYS_PER_CYCLE", "", "DAYS_0000_TO_1970", "safeAddOrElse", CmcdHeadersFactory.OBJECT_TYPE_AUDIO_ONLY, "b", "action", "Lkotlin/Function0;", "", "safeMultiplyOrElse", "SECONDS_PER_HOUR", "SECONDS_PER_MINUTE", "HOURS_PER_DAY", "SECONDS_PER_DAY", "NANOS_PER_SECOND", "NANOS_PER_MILLI", "MILLIS_PER_SECOND", "isLeapYear", "year", "monthLength", "POWERS_OF_TEN", "", "asciiDigitPositionsInIsoStringAfterYear", "colonsInIsoOffsetString", "asciiDigitsInIsoOffsetString", "truncateForErrorMessage", "maxLength", "kotlin-stdlib"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class InstantKt {
    private static final int DAYS_0000_TO_1970 = 719528;
    private static final int DAYS_PER_CYCLE = 146097;
    private static final long DISTANT_FUTURE_SECONDS = 3093527980800L;
    private static final long DISTANT_PAST_SECONDS = -3217862419201L;
    private static final int HOURS_PER_DAY = 24;
    private static final long MAX_SECOND = 31556889864403199L;
    private static final int MILLIS_PER_SECOND = 1000;
    private static final long MIN_SECOND = -31557014167219200L;
    private static final int NANOS_PER_MILLI = 1000000;
    public static final int NANOS_PER_SECOND = 1000000000;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int[] POWERS_OF_TEN = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    private static final int[] asciiDigitPositionsInIsoStringAfterYear = {1, 2, 4, 5, 7, 8, 10, 11, 13, 14};
    private static final int[] colonsInIsoOffsetString = {3, 6};
    private static final int[] asciiDigitsInIsoOffsetString = {1, 2, 4, 5, 7, 8};

    public static /* synthetic */ void isDistantFuture$annotations(Instant instant) {
    }

    public static /* synthetic */ void isDistantPast$annotations(Instant instant) {
    }

    private static final boolean isDistantPast(Instant $this$isDistantPast) {
        Intrinsics.checkNotNullParameter($this$isDistantPast, "<this>");
        return $this$isDistantPast.compareTo(Instant.INSTANCE.getDISTANT_PAST()) <= 0;
    }

    private static final boolean isDistantFuture(Instant $this$isDistantFuture) {
        Intrinsics.checkNotNullParameter($this$isDistantFuture, "<this>");
        return $this$isDistantFuture.compareTo(Instant.INSTANCE.getDISTANT_FUTURE()) >= 0;
    }

    private static final InstantParseResult.Failure parseIso$parseFailure(CharSequence $isoString, String error) {
        return new InstantParseResult.Failure(error + " when parsing an Instant from \"" + truncateForErrorMessage($isoString, 64) + '\"', $isoString);
    }

    private static final InstantParseResult.Failure parseIso$expect(CharSequence $isoString, String what, int where, Function1<? super Character, Boolean> function1) {
        char c = $isoString.charAt(where);
        if (function1.invoke(Character.valueOf(c)).booleanValue()) {
            return null;
        }
        return parseIso$parseFailure($isoString, "Expected " + what + ", but got '" + c + "' at position " + where);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:101:0x01e1  */
    /* JADX WARN: Code duplicated, block: B:102:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:104:0x020b  */
    /* JADX WARN: Code duplicated, block: B:107:0x021a  */
    /* JADX WARN: Code duplicated, block: B:109:0x0223  */
    /* JADX WARN: Code duplicated, block: B:111:0x022c  */
    /* JADX WARN: Code duplicated, block: B:113:0x025a  */
    /* JADX WARN: Code duplicated, block: B:115:0x0262  */
    /* JADX WARN: Code duplicated, block: B:116:0x0269  */
    /* JADX WARN: Code duplicated, block: B:118:0x0285  */
    /* JADX WARN: Code duplicated, block: B:120:0x028e  */
    /* JADX WARN: Code duplicated, block: B:122:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:124:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:126:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:128:0x02fa  */
    /* JADX WARN: Code duplicated, block: B:131:0x0309  */
    /* JADX WARN: Code duplicated, block: B:135:0x0343 A[LOOP:3: B:127:0x02f8->B:135:0x0343, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:138:0x034e  */
    /* JADX WARN: Code duplicated, block: B:141:0x035d  */
    /* JADX WARN: Code duplicated, block: B:143:0x0367  */
    /* JADX WARN: Code duplicated, block: B:146:0x036d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:151:0x03a2 A[LOOP:4: B:137:0x034c->B:151:0x03a2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:154:0x03b2  */
    /* JADX WARN: Code duplicated, block: B:155:0x03b9  */
    /* JADX WARN: Code duplicated, block: B:158:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:159:0x03c4  */
    /* JADX WARN: Code duplicated, block: B:162:0x03c9  */
    /* JADX WARN: Code duplicated, block: B:164:0x03e5  */
    /* JADX WARN: Code duplicated, block: B:166:0x03eb  */
    /* JADX WARN: Code duplicated, block: B:168:0x0405  */
    /* JADX WARN: Code duplicated, block: B:170:0x0409  */
    /* JADX WARN: Code duplicated, block: B:178:0x0441  */
    /* JADX WARN: Code duplicated, block: B:179:0x0443  */
    /* JADX WARN: Code duplicated, block: B:186:0x044f  */
    /* JADX WARN: Code duplicated, block: B:188:0x0452  */
    /* JADX WARN: Code duplicated, block: B:190:0x046c  */
    /* JADX WARN: Code duplicated, block: B:195:0x047c  */
    /* JADX WARN: Code duplicated, block: B:197:0x0480  */
    /* JADX WARN: Code duplicated, block: B:199:0x04ae  */
    /* JADX WARN: Code duplicated, block: B:201:0x04b2  */
    /* JADX WARN: Code duplicated, block: B:203:0x04cc  */
    /* JADX WARN: Code duplicated, block: B:205:0x04d0  */
    /* JADX WARN: Code duplicated, block: B:207:0x04ea  */
    /* JADX WARN: Code duplicated, block: B:209:0x04ee  */
    /* JADX WARN: Code duplicated, block: B:211:0x0508  */
    /* JADX WARN: Code duplicated, block: B:213:0x052e  */
    /* JADX WARN: Code duplicated, block: B:214:0x054d  */
    /* JADX WARN: Code duplicated, block: B:217:0x0580  */
    /* JADX WARN: Code duplicated, block: B:219:0x058d  */
    /* JADX WARN: Code duplicated, block: B:225:0x016b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:226:0x01d1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:227:0x01d3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:228:0x0348 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:229:0x0313 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:231:0x0372 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:233:0x03a9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x0056  */
    /* JADX WARN: Code duplicated, block: B:27:0x0074  */
    /* JADX WARN: Code duplicated, block: B:34:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:36:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:47:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:48:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:51:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:53:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:55:0x0103  */
    /* JADX WARN: Code duplicated, block: B:57:0x0108  */
    /* JADX WARN: Code duplicated, block: B:59:0x0115  */
    /* JADX WARN: Code duplicated, block: B:61:0x011a  */
    /* JADX WARN: Code duplicated, block: B:63:0x0129  */
    /* JADX WARN: Code duplicated, block: B:65:0x012e  */
    /* JADX WARN: Code duplicated, block: B:67:0x013d  */
    /* JADX WARN: Code duplicated, block: B:69:0x0142  */
    /* JADX WARN: Code duplicated, block: B:71:0x014f  */
    /* JADX WARN: Code duplicated, block: B:73:0x0154  */
    /* JADX WARN: Code duplicated, block: B:75:0x015a  */
    /* JADX WARN: Code duplicated, block: B:79:0x0170 A[LOOP:1: B:74:0x0158->B:79:0x0170, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:82:0x019f  */
    /* JADX WARN: Code duplicated, block: B:85:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:90:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:92:0x01be A[LOOP:2: B:83:0x01a3->B:92:0x01be, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:99:0x01de  */
    /* JADX WARN: Instruction removed from duplicated block: B:73:0x0154, please report this as an issue */
    public static final InstantParseResult parseIso(CharSequence isoString) {
        int yearStrLength;
        int year;
        InstantParseResult.Failure it;
        InstantParseResult.Failure it2;
        InstantParseResult.Failure it3;
        InstantParseResult.Failure it4;
        InstantParseResult.Failure it5;
        int i;
        int month;
        int day;
        int hour;
        int minute;
        int second;
        int i2;
        int nanosecond;
        char sign;
        int offsetStrLength;
        int[] iArr;
        int length;
        int absYear;
        int[] iArr2;
        int length2;
        int i3;
        int offsetHour;
        int offsetMinute;
        int offsetSecond;
        int i4;
        int yearStrLength2;
        int j;
        int[] iArr3;
        int i5;
        char cCharAt;
        int j2;
        int i6;
        int i7;
        boolean z;
        boolean z2;
        UnboundLocalDateTime this_$iv;
        long y$iv;
        long total$iv;
        long total$iv2;
        long total$iv3;
        int fraction;
        char yearSign;
        int fractionStrLength;
        boolean z3;
        char cCharAt2;
        int yearStart;
        boolean z4;
        InstantParseResult.Failure it6;
        int i8 = 0;
        if (isoString.length() == 0) {
            return new InstantParseResult.Failure("An empty string is not a valid Instant", isoString);
        }
        char c = isoString.charAt(0);
        switch (c) {
            case '+':
            case '-':
                i8 = 0 + 1;
                break;
            case ',':
            default:
                c = ' ';
                break;
        }
        int yearStart2 = i8;
        int absYear2 = 0;
        while (i8 < isoString.length()) {
            char cCharAt3 = isoString.charAt(i8);
            if (!('0' <= cCharAt3 && cCharAt3 < ':')) {
                yearStrLength = i8 - yearStart2;
                if (yearStrLength > 10) {
                    return parseIso$parseFailure(isoString, "Expected at most 10 digits for the year number, got " + yearStrLength + " digits");
                }
                if (yearStrLength != 10 && Intrinsics.compare((int) isoString.charAt(yearStart2), 50) >= 0) {
                    return parseIso$parseFailure(isoString, "Expected at most 9 digits for the year number or year 1000000000, got " + yearStrLength + " digits");
                }
                if (yearStrLength < 4) {
                    return parseIso$parseFailure(isoString, "The year number must be padded to 4 digits, got " + yearStrLength + " digits");
                }
                if (c != '+' && yearStrLength == 4) {
                    return parseIso$parseFailure(isoString, "The '+' sign at the start is only valid for year numbers longer than 4 digits");
                }
                if (c != ' ' && yearStrLength != 4) {
                    return parseIso$parseFailure(isoString, "A '+' or '-' sign is required for year numbers longer than 4 digits");
                }
                if (c == '-') {
                    year = -absYear2;
                } else {
                    year = absYear2;
                }
                if (isoString.length() < i8 + 16) {
                    return parseIso$parseFailure(isoString, "The input string is too short");
                }
                it = parseIso$expect(isoString, "'-'", i8, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(InstantKt.parseIso$lambda$0(((Character) obj).charValue()));
                    }
                });
                if (it != null) {
                    return it;
                }
                it2 = parseIso$expect(isoString, "'-'", i8 + 3, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(InstantKt.parseIso$lambda$2(((Character) obj).charValue()));
                    }
                });
                if (it2 != null) {
                    return it2;
                }
                it3 = parseIso$expect(isoString, "'T' or 't'", i8 + 6, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(InstantKt.parseIso$lambda$4(((Character) obj).charValue()));
                    }
                });
                if (it3 != null) {
                    return it3;
                }
                it4 = parseIso$expect(isoString, "':'", i8 + 9, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(InstantKt.parseIso$lambda$6(((Character) obj).charValue()));
                    }
                });
                if (it4 != null) {
                    return it4;
                }
                it5 = parseIso$expect(isoString, "':'", i8 + 12, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(InstantKt.parseIso$lambda$8(((Character) obj).charValue()));
                    }
                });
                if (it5 != null) {
                    return it5;
                }
                for (int i9 : asciiDigitPositionsInIsoStringAfterYear) {
                    it6 = parseIso$expect(isoString, "an ASCII digit", i8 + i9, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Boolean.valueOf(InstantKt.parseIso$lambda$10(((Character) obj).charValue()));
                        }
                    });
                    if (it6 != null) {
                        return it6;
                    }
                }
                month = parseIso$twoDigitNumber(isoString, i8 + 1);
                day = parseIso$twoDigitNumber(isoString, i8 + 4);
                hour = parseIso$twoDigitNumber(isoString, i8 + 7);
                minute = parseIso$twoDigitNumber(isoString, i8 + 10);
                second = parseIso$twoDigitNumber(isoString, i8 + 13);
                if (isoString.charAt(i8 + 15) == '.') {
                    int fractionStart = i8 + 16;
                    i2 = fractionStart;
                    fraction = 0;
                    while (true) {
                        yearSign = c;
                        if (i2 < isoString.length()) {
                            cCharAt2 = isoString.charAt(i2);
                            yearStart = yearStart2;
                            if ('0' <= cCharAt2 || cCharAt2 >= ':') {
                                z4 = false;
                            } else {
                                z4 = true;
                            }
                            if (z4) {
                                fraction = (fraction * 10) + (isoString.charAt(i2) - '0');
                                i2++;
                                c = yearSign;
                                yearStart2 = yearStart;
                            }
                        }
                    }
                    fractionStrLength = i2 - fractionStart;
                    if (1 <= fractionStrLength || fractionStrLength >= 10) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    if (z3) {
                        return parseIso$parseFailure(isoString, "1..9 digits are supported for the fraction of the second, got " + fractionStrLength + " digits");
                    }
                    nanosecond = POWERS_OF_TEN[9 - fractionStrLength] * fraction;
                } else {
                    i2 = i8 + 15;
                    nanosecond = 0;
                }
                if (i2 >= isoString.length()) {
                    return parseIso$parseFailure(isoString, "The UTC offset at the end of the string is missing");
                }
                sign = isoString.charAt(i2);
                switch (sign) {
                    case '+':
                    case '-':
                        offsetStrLength = isoString.length() - i2;
                        if (offsetStrLength > 9) {
                            return parseIso$parseFailure(isoString, "The UTC offset string \"" + truncateForErrorMessage(isoString.subSequence(i2, isoString.length()).toString(), 16) + "\" is too long");
                        }
                        if (offsetStrLength % 3 != 0) {
                            return parseIso$parseFailure(isoString, "Invalid UTC offset string \"" + isoString.subSequence(i2, isoString.length()).toString() + '\"');
                        }
                        iArr = colonsInIsoOffsetString;
                        length = iArr.length;
                        absYear = 0;
                        while (absYear < length) {
                            j2 = iArr[absYear];
                            i6 = length;
                            i7 = absYear;
                            if (i2 + j2 >= isoString.length()) {
                                iArr2 = asciiDigitsInIsoOffsetString;
                                length2 = iArr2.length;
                                i3 = 0;
                                while (i3 < length2) {
                                    j = iArr2[i3];
                                    iArr3 = iArr2;
                                    i5 = length2;
                                    if (i2 + j >= isoString.length()) {
                                        offsetHour = parseIso$twoDigitNumber(isoString, i2 + 1);
                                        if (offsetStrLength > 3) {
                                            offsetMinute = parseIso$twoDigitNumber(isoString, i2 + 4);
                                        } else {
                                            offsetMinute = 0;
                                        }
                                        if (offsetStrLength > 6) {
                                            offsetSecond = parseIso$twoDigitNumber(isoString, i2 + 7);
                                        } else {
                                            offsetSecond = 0;
                                        }
                                        if (offsetMinute > 59) {
                                            return parseIso$parseFailure(isoString, "Expected offset-minute-of-hour in 0..59, got " + offsetMinute);
                                        }
                                        if (offsetSecond > 59) {
                                            return parseIso$parseFailure(isoString, "Expected offset-second-of-minute in 0..59, got " + offsetSecond);
                                        }
                                        if (offsetHour <= 17 && (offsetHour != 18 || offsetMinute != 0 || offsetSecond != 0)) {
                                            return parseIso$parseFailure(isoString, "Expected an offset in -18:00..+18:00, got " + isoString.subSequence(i2, isoString.length()).toString());
                                        }
                                        int i10 = (offsetHour * SECONDS_PER_HOUR) + (offsetMinute * 60) + offsetSecond;
                                        if (sign == '-') {
                                            i4 = -1;
                                        } else {
                                            i4 = 1;
                                        }
                                        yearStrLength2 = i10 * i4;
                                    } else {
                                        cCharAt = isoString.charAt(i2 + j);
                                        if (!('0' > cCharAt && cCharAt < ':')) {
                                            return parseIso$parseFailure(isoString, "Expected an ASCII digit at index " + (i2 + j) + ", got '" + isoString.charAt(i2 + j) + '\'');
                                        }
                                        i3++;
                                        iArr2 = iArr3;
                                        length2 = i5;
                                    }
                                    break;
                                }
                                offsetHour = parseIso$twoDigitNumber(isoString, i2 + 1);
                                if (offsetStrLength > 3) {
                                    offsetMinute = parseIso$twoDigitNumber(isoString, i2 + 4);
                                } else {
                                    offsetMinute = 0;
                                }
                                if (offsetStrLength > 6) {
                                    offsetSecond = parseIso$twoDigitNumber(isoString, i2 + 7);
                                } else {
                                    offsetSecond = 0;
                                }
                                if (offsetMinute > 59) {
                                    return parseIso$parseFailure(isoString, "Expected offset-minute-of-hour in 0..59, got " + offsetMinute);
                                }
                                if (offsetSecond > 59) {
                                    return parseIso$parseFailure(isoString, "Expected offset-second-of-minute in 0..59, got " + offsetSecond);
                                }
                                if (offsetHour <= 17) {
                                }
                                int i11 = (offsetHour * SECONDS_PER_HOUR) + (offsetMinute * 60) + offsetSecond;
                                if (sign == '-') {
                                    i4 = -1;
                                } else {
                                    i4 = 1;
                                }
                                yearStrLength2 = i11 * i4;
                            } else {
                                if (isoString.charAt(i2 + j2) != ':') {
                                    return parseIso$parseFailure(isoString, "Expected ':' at index " + (i2 + j2) + ", got '" + isoString.charAt(i2 + j2) + '\'');
                                }
                                absYear = i7 + 1;
                                length = i6;
                            }
                            break;
                        }
                        iArr2 = asciiDigitsInIsoOffsetString;
                        length2 = iArr2.length;
                        i3 = 0;
                        while (i3 < length2) {
                            j = iArr2[i3];
                            iArr3 = iArr2;
                            i5 = length2;
                            if (i2 + j >= isoString.length()) {
                                offsetHour = parseIso$twoDigitNumber(isoString, i2 + 1);
                                if (offsetStrLength > 3) {
                                    offsetMinute = parseIso$twoDigitNumber(isoString, i2 + 4);
                                } else {
                                    offsetMinute = 0;
                                }
                                if (offsetStrLength > 6) {
                                    offsetSecond = parseIso$twoDigitNumber(isoString, i2 + 7);
                                } else {
                                    offsetSecond = 0;
                                }
                                if (offsetMinute > 59) {
                                    return parseIso$parseFailure(isoString, "Expected offset-minute-of-hour in 0..59, got " + offsetMinute);
                                }
                                if (offsetSecond > 59) {
                                    return parseIso$parseFailure(isoString, "Expected offset-second-of-minute in 0..59, got " + offsetSecond);
                                }
                                if (offsetHour <= 17) {
                                }
                                int i12 = (offsetHour * SECONDS_PER_HOUR) + (offsetMinute * 60) + offsetSecond;
                                if (sign == '-') {
                                    i4 = -1;
                                } else {
                                    i4 = 1;
                                }
                                yearStrLength2 = i12 * i4;
                            } else {
                                cCharAt = isoString.charAt(i2 + j);
                                if (!('0' > cCharAt && cCharAt < ':')) {
                                    return parseIso$parseFailure(isoString, "Expected an ASCII digit at index " + (i2 + j) + ", got '" + isoString.charAt(i2 + j) + '\'');
                                }
                                i3++;
                                iArr2 = iArr3;
                                length2 = i5;
                            }
                            break;
                        }
                        offsetHour = parseIso$twoDigitNumber(isoString, i2 + 1);
                        if (offsetStrLength > 3) {
                            offsetMinute = parseIso$twoDigitNumber(isoString, i2 + 4);
                        } else {
                            offsetMinute = 0;
                        }
                        if (offsetStrLength > 6) {
                            offsetSecond = parseIso$twoDigitNumber(isoString, i2 + 7);
                        } else {
                            offsetSecond = 0;
                        }
                        if (offsetMinute > 59) {
                            return parseIso$parseFailure(isoString, "Expected offset-minute-of-hour in 0..59, got " + offsetMinute);
                        }
                        if (offsetSecond > 59) {
                            return parseIso$parseFailure(isoString, "Expected offset-second-of-minute in 0..59, got " + offsetSecond);
                        }
                        if (offsetHour <= 17) {
                        }
                        int i13 = (offsetHour * SECONDS_PER_HOUR) + (offsetMinute * 60) + offsetSecond;
                        if (sign == '-') {
                            i4 = -1;
                        } else {
                            i4 = 1;
                        }
                        yearStrLength2 = i13 * i4;
                        break;
                        break;
                    case RotationOptions.ROTATE_90 /* 90 */:
                    case 'z':
                        if (isoString.length() == i2 + 1) {
                            return parseIso$parseFailure(isoString, "Extra text after the instant at position " + (i2 + 1));
                        }
                        yearStrLength2 = 0;
                        break;
                        break;
                    default:
                        return parseIso$parseFailure(isoString, "Expected the UTC offset at position " + i2 + ", got '" + sign + '\'');
                }
                if (1 <= month || month >= 13) {
                    z = false;
                } else {
                    z = true;
                }
                if (!z) {
                    return parseIso$parseFailure(isoString, "Expected a month number in 1..12, got " + month);
                }
                if (1 <= day || day > monthLength(month, isLeapYear(year))) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (!z2) {
                    return parseIso$parseFailure(isoString, "Expected a valid day-of-month for month " + month + " of year " + year + ", got " + day);
                }
                if (hour > 23) {
                    return parseIso$parseFailure(isoString, "Expected hour in 0..23, got " + hour);
                }
                if (minute > 59) {
                    return parseIso$parseFailure(isoString, "Expected minute-of-hour in 0..59, got " + minute);
                }
                if (second > 59) {
                    return parseIso$parseFailure(isoString, "Expected second-of-minute in 0..59, got " + second);
                }
                this_$iv = new UnboundLocalDateTime(year, month, day, hour, minute, second, nanosecond);
                y$iv = this_$iv.getYear();
                total$iv = 365 * y$iv;
                if (y$iv >= 0) {
                    total$iv2 = total$iv + (((y$iv + 3) / 4) - ((y$iv + 99) / 100)) + ((y$iv + 399) / 400);
                } else {
                    total$iv2 = total$iv - (((y$iv / (-4)) - (y$iv / (-100))) + (y$iv / (-400)));
                }
                total$iv3 = total$iv2 + ((long) (((this_$iv.getMonth() * 367) - 362) / 12)) + ((long) (this_$iv.getDay() - 1));
                if (this_$iv.getMonth() > 2) {
                    total$iv3--;
                    if (!isLeapYear(this_$iv.getYear())) {
                        total$iv3--;
                    }
                }
                long epochDays$iv = total$iv3 - 719528;
                int daySeconds$iv = (this_$iv.getHour() * SECONDS_PER_HOUR) + (this_$iv.getMinute() * 60) + this_$iv.getSecond();
                long epochSeconds$iv = ((86400 * epochDays$iv) + ((long) daySeconds$iv)) - ((long) yearStrLength2);
                int p1 = this_$iv.getNanosecond();
                return new InstantParseResult.Success(epochSeconds$iv, p1);
            }
            absYear2 = (absYear2 * 10) + (isoString.charAt(i8) - '0');
            i8++;
        }
        yearStrLength = i8 - yearStart2;
        if (yearStrLength > 10) {
            return parseIso$parseFailure(isoString, "Expected at most 10 digits for the year number, got " + yearStrLength + " digits");
        }
        if (yearStrLength != 10) {
        }
        if (yearStrLength < 4) {
            return parseIso$parseFailure(isoString, "The year number must be padded to 4 digits, got " + yearStrLength + " digits");
        }
        if (c != '+') {
        }
        if (c != ' ') {
        }
        if (c == '-') {
            year = -absYear2;
        } else {
            year = absYear2;
        }
        if (isoString.length() < i8 + 16) {
            return parseIso$parseFailure(isoString, "The input string is too short");
        }
        it = parseIso$expect(isoString, "'-'", i8, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(InstantKt.parseIso$lambda$0(((Character) obj).charValue()));
            }
        });
        if (it != null) {
            return it;
        }
        it2 = parseIso$expect(isoString, "'-'", i8 + 3, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(InstantKt.parseIso$lambda$2(((Character) obj).charValue()));
            }
        });
        if (it2 != null) {
            return it2;
        }
        it3 = parseIso$expect(isoString, "'T' or 't'", i8 + 6, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(InstantKt.parseIso$lambda$4(((Character) obj).charValue()));
            }
        });
        if (it3 != null) {
            return it3;
        }
        it4 = parseIso$expect(isoString, "':'", i8 + 9, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(InstantKt.parseIso$lambda$6(((Character) obj).charValue()));
            }
        });
        if (it4 != null) {
            return it4;
        }
        it5 = parseIso$expect(isoString, "':'", i8 + 12, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(InstantKt.parseIso$lambda$8(((Character) obj).charValue()));
            }
        });
        if (it5 != null) {
            return it5;
        }
        while (i < r6) {
            it6 = parseIso$expect(isoString, "an ASCII digit", i8 + i9, new Function1() { // from class: kotlin.time.InstantKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(InstantKt.parseIso$lambda$10(((Character) obj).charValue()));
                }
            });
            if (it6 != null) {
                return it6;
            }
        }
        month = parseIso$twoDigitNumber(isoString, i8 + 1);
        day = parseIso$twoDigitNumber(isoString, i8 + 4);
        hour = parseIso$twoDigitNumber(isoString, i8 + 7);
        minute = parseIso$twoDigitNumber(isoString, i8 + 10);
        second = parseIso$twoDigitNumber(isoString, i8 + 13);
        if (isoString.charAt(i8 + 15) == '.') {
            int fractionStart2 = i8 + 16;
            i2 = fractionStart2;
            fraction = 0;
            while (true) {
                yearSign = c;
                if (i2 < isoString.length()) {
                    cCharAt2 = isoString.charAt(i2);
                    yearStart = yearStart2;
                    if ('0' <= cCharAt2) {
                        z4 = false;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        fraction = (fraction * 10) + (isoString.charAt(i2) - '0');
                        i2++;
                        c = yearSign;
                        yearStart2 = yearStart;
                    }
                }
            }
            fractionStrLength = i2 - fractionStart2;
            if (1 <= fractionStrLength) {
                z3 = false;
            } else {
                z3 = false;
            }
            if (z3) {
                return parseIso$parseFailure(isoString, "1..9 digits are supported for the fraction of the second, got " + fractionStrLength + " digits");
            }
            nanosecond = POWERS_OF_TEN[9 - fractionStrLength] * fraction;
        } else {
            i2 = i8 + 15;
            nanosecond = 0;
        }
        if (i2 >= isoString.length()) {
            return parseIso$parseFailure(isoString, "The UTC offset at the end of the string is missing");
        }
        sign = isoString.charAt(i2);
        switch (sign) {
            case '+':
            case '-':
                offsetStrLength = isoString.length() - i2;
                if (offsetStrLength > 9) {
                    return parseIso$parseFailure(isoString, "The UTC offset string \"" + truncateForErrorMessage(isoString.subSequence(i2, isoString.length()).toString(), 16) + "\" is too long");
                }
                if (offsetStrLength % 3 != 0) {
                    return parseIso$parseFailure(isoString, "Invalid UTC offset string \"" + isoString.subSequence(i2, isoString.length()).toString() + '\"');
                }
                iArr = colonsInIsoOffsetString;
                length = iArr.length;
                absYear = 0;
                while (absYear < length) {
                    j2 = iArr[absYear];
                    i6 = length;
                    i7 = absYear;
                    if (i2 + j2 >= isoString.length()) {
                        iArr2 = asciiDigitsInIsoOffsetString;
                        length2 = iArr2.length;
                        i3 = 0;
                        while (i3 < length2) {
                            j = iArr2[i3];
                            iArr3 = iArr2;
                            i5 = length2;
                            if (i2 + j >= isoString.length()) {
                                offsetHour = parseIso$twoDigitNumber(isoString, i2 + 1);
                                if (offsetStrLength > 3) {
                                    offsetMinute = parseIso$twoDigitNumber(isoString, i2 + 4);
                                } else {
                                    offsetMinute = 0;
                                }
                                if (offsetStrLength > 6) {
                                    offsetSecond = parseIso$twoDigitNumber(isoString, i2 + 7);
                                } else {
                                    offsetSecond = 0;
                                }
                                if (offsetMinute > 59) {
                                    return parseIso$parseFailure(isoString, "Expected offset-minute-of-hour in 0..59, got " + offsetMinute);
                                }
                                if (offsetSecond > 59) {
                                    return parseIso$parseFailure(isoString, "Expected offset-second-of-minute in 0..59, got " + offsetSecond);
                                }
                                if (offsetHour <= 17) {
                                }
                                int i14 = (offsetHour * SECONDS_PER_HOUR) + (offsetMinute * 60) + offsetSecond;
                                if (sign == '-') {
                                    i4 = -1;
                                } else {
                                    i4 = 1;
                                }
                                yearStrLength2 = i14 * i4;
                            } else {
                                cCharAt = isoString.charAt(i2 + j);
                                if ('0' > cCharAt) {
                                    break;
                                }
                                if (!('0' > cCharAt && cCharAt < ':')) {
                                    return parseIso$parseFailure(isoString, "Expected an ASCII digit at index " + (i2 + j) + ", got '" + isoString.charAt(i2 + j) + '\'');
                                }
                                i3++;
                                iArr2 = iArr3;
                                length2 = i5;
                            }
                            break;
                        }
                        offsetHour = parseIso$twoDigitNumber(isoString, i2 + 1);
                        if (offsetStrLength > 3) {
                            offsetMinute = parseIso$twoDigitNumber(isoString, i2 + 4);
                        } else {
                            offsetMinute = 0;
                        }
                        if (offsetStrLength > 6) {
                            offsetSecond = parseIso$twoDigitNumber(isoString, i2 + 7);
                        } else {
                            offsetSecond = 0;
                        }
                        if (offsetMinute > 59) {
                            return parseIso$parseFailure(isoString, "Expected offset-minute-of-hour in 0..59, got " + offsetMinute);
                        }
                        if (offsetSecond > 59) {
                            return parseIso$parseFailure(isoString, "Expected offset-second-of-minute in 0..59, got " + offsetSecond);
                        }
                        if (offsetHour <= 17) {
                        }
                        int i15 = (offsetHour * SECONDS_PER_HOUR) + (offsetMinute * 60) + offsetSecond;
                        if (sign == '-') {
                            i4 = -1;
                        } else {
                            i4 = 1;
                        }
                        yearStrLength2 = i15 * i4;
                    } else {
                        if (isoString.charAt(i2 + j2) != ':') {
                            return parseIso$parseFailure(isoString, "Expected ':' at index " + (i2 + j2) + ", got '" + isoString.charAt(i2 + j2) + '\'');
                        }
                        absYear = i7 + 1;
                        length = i6;
                    }
                    break;
                }
                iArr2 = asciiDigitsInIsoOffsetString;
                length2 = iArr2.length;
                i3 = 0;
                while (i3 < length2) {
                    j = iArr2[i3];
                    iArr3 = iArr2;
                    i5 = length2;
                    if (i2 + j >= isoString.length()) {
                        offsetHour = parseIso$twoDigitNumber(isoString, i2 + 1);
                        if (offsetStrLength > 3) {
                            offsetMinute = parseIso$twoDigitNumber(isoString, i2 + 4);
                        } else {
                            offsetMinute = 0;
                        }
                        if (offsetStrLength > 6) {
                            offsetSecond = parseIso$twoDigitNumber(isoString, i2 + 7);
                        } else {
                            offsetSecond = 0;
                        }
                        if (offsetMinute > 59) {
                            return parseIso$parseFailure(isoString, "Expected offset-minute-of-hour in 0..59, got " + offsetMinute);
                        }
                        if (offsetSecond > 59) {
                            return parseIso$parseFailure(isoString, "Expected offset-second-of-minute in 0..59, got " + offsetSecond);
                        }
                        if (offsetHour <= 17) {
                        }
                        int i16 = (offsetHour * SECONDS_PER_HOUR) + (offsetMinute * 60) + offsetSecond;
                        if (sign == '-') {
                            i4 = -1;
                        } else {
                            i4 = 1;
                        }
                        yearStrLength2 = i16 * i4;
                    } else {
                        cCharAt = isoString.charAt(i2 + j);
                        if ('0' > cCharAt) {
                            break;
                        }
                        if (!('0' > cCharAt && cCharAt < ':')) {
                            return parseIso$parseFailure(isoString, "Expected an ASCII digit at index " + (i2 + j) + ", got '" + isoString.charAt(i2 + j) + '\'');
                        }
                        i3++;
                        iArr2 = iArr3;
                        length2 = i5;
                    }
                    break;
                }
                offsetHour = parseIso$twoDigitNumber(isoString, i2 + 1);
                if (offsetStrLength > 3) {
                    offsetMinute = parseIso$twoDigitNumber(isoString, i2 + 4);
                } else {
                    offsetMinute = 0;
                }
                if (offsetStrLength > 6) {
                    offsetSecond = parseIso$twoDigitNumber(isoString, i2 + 7);
                } else {
                    offsetSecond = 0;
                }
                if (offsetMinute > 59) {
                    return parseIso$parseFailure(isoString, "Expected offset-minute-of-hour in 0..59, got " + offsetMinute);
                }
                if (offsetSecond > 59) {
                    return parseIso$parseFailure(isoString, "Expected offset-second-of-minute in 0..59, got " + offsetSecond);
                }
                if (offsetHour <= 17) {
                }
                int i17 = (offsetHour * SECONDS_PER_HOUR) + (offsetMinute * 60) + offsetSecond;
                if (sign == '-') {
                    i4 = -1;
                } else {
                    i4 = 1;
                }
                yearStrLength2 = i17 * i4;
                break;
                break;
            case RotationOptions.ROTATE_90 /* 90 */:
            case 'z':
                if (isoString.length() == i2 + 1) {
                    return parseIso$parseFailure(isoString, "Extra text after the instant at position " + (i2 + 1));
                }
                yearStrLength2 = 0;
                break;
                break;
            default:
                return parseIso$parseFailure(isoString, "Expected the UTC offset at position " + i2 + ", got '" + sign + '\'');
        }
        if (1 <= month) {
            z = false;
        } else {
            z = false;
        }
        if (!z) {
            return parseIso$parseFailure(isoString, "Expected a month number in 1..12, got " + month);
        }
        if (1 <= day) {
            z2 = false;
        } else {
            z2 = false;
        }
        if (!z2) {
            return parseIso$parseFailure(isoString, "Expected a valid day-of-month for month " + month + " of year " + year + ", got " + day);
        }
        if (hour > 23) {
            return parseIso$parseFailure(isoString, "Expected hour in 0..23, got " + hour);
        }
        if (minute > 59) {
            return parseIso$parseFailure(isoString, "Expected minute-of-hour in 0..59, got " + minute);
        }
        if (second > 59) {
            return parseIso$parseFailure(isoString, "Expected second-of-minute in 0..59, got " + second);
        }
        this_$iv = new UnboundLocalDateTime(year, month, day, hour, minute, second, nanosecond);
        y$iv = this_$iv.getYear();
        total$iv = 365 * y$iv;
        if (y$iv >= 0) {
            total$iv2 = total$iv + (((y$iv + 3) / 4) - ((y$iv + 99) / 100)) + ((y$iv + 399) / 400);
        } else {
            total$iv2 = total$iv - (((y$iv / (-4)) - (y$iv / (-100))) + (y$iv / (-400)));
        }
        total$iv3 = total$iv2 + ((long) (((this_$iv.getMonth() * 367) - 362) / 12)) + ((long) (this_$iv.getDay() - 1));
        if (this_$iv.getMonth() > 2) {
            total$iv3--;
            if (!isLeapYear(this_$iv.getYear())) {
                total$iv3--;
            }
        }
        long epochDays$iv2 = total$iv3 - 719528;
        int daySeconds$iv2 = (this_$iv.getHour() * SECONDS_PER_HOUR) + (this_$iv.getMinute() * 60) + this_$iv.getSecond();
        long epochSeconds$iv2 = ((86400 * epochDays$iv2) + ((long) daySeconds$iv2)) - ((long) yearStrLength2);
        int p2 = this_$iv.getNanosecond();
        return new InstantParseResult.Success(epochSeconds$iv2, p2);
    }

    static final boolean parseIso$lambda$0(char it) {
        return it == '-';
    }

    static final boolean parseIso$lambda$2(char it) {
        return it == '-';
    }

    static final boolean parseIso$lambda$4(char it) {
        return it == 'T' || it == 't';
    }

    static final boolean parseIso$lambda$6(char it) {
        return it == ':';
    }

    static final boolean parseIso$lambda$8(char it) {
        return it == ':';
    }

    static final boolean parseIso$lambda$10(char it) {
        return '0' <= it && it < ':';
    }

    private static final int parseIso$twoDigitNumber(CharSequence s, int index) {
        return ((s.charAt(index) - '0') * 10) + (s.charAt(index + 1) - '0');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatIso(Instant instant) throws IOException {
        StringBuilder $this$formatIso_u24lambda_u2413 = new StringBuilder();
        UnboundLocalDateTime ldt = UnboundLocalDateTime.INSTANCE.fromInstant(instant);
        int number = ldt.getYear();
        if (Math.abs(number) < 1000) {
            StringBuilder innerBuilder = new StringBuilder();
            if (number >= 0) {
                Intrinsics.checkNotNullExpressionValue(innerBuilder.append(number + 10000).deleteCharAt(0), "deleteCharAt(...)");
            } else {
                Intrinsics.checkNotNullExpressionValue(innerBuilder.append(number - 10000).deleteCharAt(1), "deleteCharAt(...)");
            }
            $this$formatIso_u24lambda_u2413.append((CharSequence) innerBuilder);
        } else {
            if (number >= 10000) {
                $this$formatIso_u24lambda_u2413.append('+');
            }
            $this$formatIso_u24lambda_u2413.append(number);
        }
        $this$formatIso_u24lambda_u2413.append('-');
        formatIso$lambda$13$appendTwoDigits($this$formatIso_u24lambda_u2413, $this$formatIso_u24lambda_u2413, ldt.getMonth());
        $this$formatIso_u24lambda_u2413.append('-');
        formatIso$lambda$13$appendTwoDigits($this$formatIso_u24lambda_u2413, $this$formatIso_u24lambda_u2413, ldt.getDay());
        $this$formatIso_u24lambda_u2413.append('T');
        formatIso$lambda$13$appendTwoDigits($this$formatIso_u24lambda_u2413, $this$formatIso_u24lambda_u2413, ldt.getHour());
        $this$formatIso_u24lambda_u2413.append(AbstractJsonLexerKt.COLON);
        formatIso$lambda$13$appendTwoDigits($this$formatIso_u24lambda_u2413, $this$formatIso_u24lambda_u2413, ldt.getMinute());
        $this$formatIso_u24lambda_u2413.append(AbstractJsonLexerKt.COLON);
        formatIso$lambda$13$appendTwoDigits($this$formatIso_u24lambda_u2413, $this$formatIso_u24lambda_u2413, ldt.getSecond());
        if (ldt.getNanosecond() != 0) {
            $this$formatIso_u24lambda_u2413.append('.');
            int zerosToStrip = 0;
            while (ldt.getNanosecond() % POWERS_OF_TEN[zerosToStrip + 1] == 0) {
                zerosToStrip++;
            }
            int zerosToStrip2 = zerosToStrip - (zerosToStrip % 3);
            int numberToOutput = ldt.getNanosecond() / POWERS_OF_TEN[zerosToStrip2];
            String strValueOf = String.valueOf(POWERS_OF_TEN[9 - zerosToStrip2] + numberToOutput);
            Intrinsics.checkNotNull(strValueOf, "null cannot be cast to non-null type java.lang.String");
            String strSubstring = strValueOf.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            $this$formatIso_u24lambda_u2413.append(strSubstring);
        }
        $this$formatIso_u24lambda_u2413.append('Z');
        return $this$formatIso_u24lambda_u2413.toString();
    }

    private static final void formatIso$lambda$13$appendTwoDigits(Appendable $this$formatIso_u24lambda_u2413_u24appendTwoDigits, StringBuilder $this_buildString, int number) throws IOException {
        if (number < 10) {
            $this$formatIso_u24lambda_u2413_u24appendTwoDigits.append('0');
        }
        $this_buildString.append(number);
    }

    private static final long safeAddOrElse(long a, long b, Function0 action) {
        long sum = a + b;
        if ((a ^ sum) < 0 && (a ^ b) >= 0) {
            action.invoke();
            throw new KotlinNothingValueException();
        }
        return sum;
    }

    private static final long safeMultiplyOrElse(long a, long b, Function0 action) {
        if (b == 1) {
            return a;
        }
        if (a == 1) {
            return b;
        }
        if (a == 0 || b == 0) {
            return 0L;
        }
        long total = a * b;
        if (total / b != a || ((a == Long.MIN_VALUE && b == -1) || (b == Long.MIN_VALUE && a == -1))) {
            action.invoke();
            throw new KotlinNothingValueException();
        }
        return total;
    }

    public static final boolean isLeapYear(int year) {
        return (year & 3) == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    private static final int monthLength(int $this$monthLength, boolean isLeapYear) {
        switch ($this$monthLength) {
            case 2:
                return isLeapYear ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String truncateForErrorMessage(CharSequence $this$truncateForErrorMessage, int maxLength) {
        return $this$truncateForErrorMessage.length() <= maxLength ? $this$truncateForErrorMessage.toString() : $this$truncateForErrorMessage.subSequence(0, maxLength).toString() + "...";
    }
}
