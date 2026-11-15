package com.cloudinary.utils;

import androidx.compose.runtime.ComposerKt;
import androidx.media3.extractor.ts.PsExtractor;
import com.facebook.imageutils.JfifUtil;

/* JADX INFO: loaded from: classes11.dex */
public class HtmlEscape {
    private static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String escapeTextArea(String original) {
        return escapeTags(escapeSpecial(original));
    }

    public static String escape(String original) {
        return escapeBr(escapeTags(escapeSpecial(original)));
    }

    public static String escapeTags(String original) {
        if (original == null) {
            return "";
        }
        StringBuffer out = new StringBuffer("");
        char[] chars = original.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            boolean found = true;
            switch (chars[i]) {
                case '\"':
                    out.append("&quot;");
                    break;
                case '<':
                    out.append("&lt;");
                    break;
                case '>':
                    out.append("&gt;");
                    break;
                default:
                    found = false;
                    break;
            }
            if (!found) {
                out.append(chars[i]);
            }
        }
        return out.toString();
    }

    public static String escapeBr(String original) {
        if (original == null) {
            return "";
        }
        StringBuffer out = new StringBuffer("");
        char[] chars = original.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            boolean found = true;
            switch (chars[i]) {
                case '\n':
                    out.append("<br/>");
                    break;
                case '\r':
                    break;
                default:
                    found = false;
                    break;
            }
            if (!found) {
                out.append(chars[i]);
            }
        }
        return out.toString();
    }

    public static String escapeSpecial(String original) {
        if (original == null) {
            return "";
        }
        StringBuffer out = new StringBuffer("");
        char[] chars = original.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            boolean found = true;
            switch (chars[i]) {
                case '&':
                    out.append("&amp;");
                    break;
                case 162:
                    out.append("&cent;");
                    break;
                case 192:
                    out.append("&Agrave;");
                    break;
                case 193:
                    out.append("&Aacute;");
                    break;
                case 194:
                    out.append("&Acirc;");
                    break;
                case 195:
                    out.append("&Atilde;");
                    break;
                case 196:
                    out.append("&Auml;");
                    break;
                case 197:
                    out.append("&Aring;");
                    break;
                case 198:
                    out.append("&AElig;");
                    break;
                case 199:
                    out.append("&Ccedil;");
                    break;
                case 200:
                    out.append("&Egrave;");
                    break;
                case 201:
                    out.append("&Eacute;");
                    break;
                case 202:
                    out.append("&Ecirc;");
                    break;
                case 203:
                    out.append("&Euml;");
                    break;
                case ComposerKt.providerMapsKey /* 204 */:
                    out.append("&Igrave;");
                    break;
                case 205:
                    out.append("&Iacute;");
                    break;
                case ComposerKt.referenceKey /* 206 */:
                    out.append("&Icirc;");
                    break;
                case ComposerKt.reuseKey /* 207 */:
                    out.append("&Iuml;");
                    break;
                case JfifUtil.MARKER_RST0 /* 208 */:
                    out.append("&ETH;");
                    break;
                case 209:
                    out.append("&Ntilde;");
                    break;
                case 210:
                    out.append("&Ograve;");
                    break;
                case 211:
                    out.append("&Oacute;");
                    break;
                case 212:
                    out.append("&Ocirc;");
                    break;
                case 213:
                    out.append("&Otilde;");
                    break;
                case 214:
                    out.append("&Ouml;");
                    break;
                case JfifUtil.MARKER_SOI /* 216 */:
                    out.append("&Oslash;");
                    break;
                case JfifUtil.MARKER_EOI /* 217 */:
                    out.append("&Ugrave;");
                    break;
                case JfifUtil.MARKER_SOS /* 218 */:
                    out.append("&Uacute;");
                    break;
                case 219:
                    out.append("&Ucirc;");
                    break;
                case 220:
                    out.append("&Uuml;");
                    break;
                case 221:
                    out.append("&Yacute;");
                    break;
                case 222:
                    out.append("&THORN;");
                    break;
                case 223:
                    out.append("&szlig;");
                    break;
                case 224:
                    out.append("&agrave;");
                    break;
                case JfifUtil.MARKER_APP1 /* 225 */:
                    out.append("&aacute;");
                    break;
                case 226:
                    out.append("&acirc;");
                    break;
                case 227:
                    out.append("&atilde;");
                    break;
                case 228:
                    out.append("&auml;");
                    break;
                case 229:
                    out.append("&aring;");
                    break;
                case 230:
                    out.append("&aelig;");
                    break;
                case 231:
                    out.append("&ccedil;");
                    break;
                case 232:
                    out.append("&egrave;");
                    break;
                case 233:
                    out.append("&eacute;");
                    break;
                case 234:
                    out.append("&ecirc;");
                    break;
                case 235:
                    out.append("&euml;");
                    break;
                case 236:
                    out.append("&igrave;");
                    break;
                case 237:
                    out.append("&iacute;");
                    break;
                case 238:
                    out.append("&icirc;");
                    break;
                case 239:
                    out.append("&iuml;");
                    break;
                case PsExtractor.VIDEO_STREAM_MASK /* 240 */:
                    out.append("&eth;");
                    break;
                case 241:
                    out.append("&ntilde;");
                    break;
                case 242:
                    out.append("&ograve;");
                    break;
                case 243:
                    out.append("&oacute;");
                    break;
                case 244:
                    out.append("&ocirc;");
                    break;
                case 245:
                    out.append("&otilde;");
                    break;
                case 246:
                    out.append("&ouml;");
                    break;
                case 248:
                    out.append("&oslash;");
                    break;
                case 249:
                    out.append("&ugrave;");
                    break;
                case 250:
                    out.append("&uacute;");
                    break;
                case 251:
                    out.append("&ucirc;");
                    break;
                case 252:
                    out.append("&uuml;");
                    break;
                case 253:
                    out.append("&yacute;");
                    break;
                case 254:
                    out.append("&thorn;");
                    break;
                case 255:
                    out.append("&yuml;");
                    break;
                default:
                    found = false;
                    break;
            }
            if (!found) {
                if (chars[i] > 127) {
                    char c = chars[i];
                    int a4 = c % 16;
                    char c2 = (char) (c / 16);
                    int a3 = c2 % 16;
                    char c3 = (char) (c2 / 16);
                    int a2 = c3 % 16;
                    int a1 = ((char) (c3 / 16)) % 16;
                    out.append("&#x" + hex[a1] + hex[a2] + hex[a3] + hex[a4] + ";");
                } else {
                    out.append(chars[i]);
                }
            }
        }
        return out.toString();
    }
}
