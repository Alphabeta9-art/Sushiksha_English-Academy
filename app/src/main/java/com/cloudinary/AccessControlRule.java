package com.cloudinary;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.cloudinary.utils.ObjectUtils;
import java.util.Date;
import org.cloudinary.json.JSONObject;

/* JADX INFO: loaded from: classes11.dex */
public class AccessControlRule extends JSONObject {

    public enum AccessType {
        anonymous,
        token
    }

    public static AccessControlRule token() {
        return new AccessControlRule(AccessType.token, null, null);
    }

    public static AccessControlRule anonymousFrom(Date start) {
        return new AccessControlRule(AccessType.anonymous, start, null);
    }

    public static AccessControlRule anonymousUntil(Date end) {
        return new AccessControlRule(AccessType.anonymous, null, end);
    }

    public static AccessControlRule anonymous(Date start, Date end) {
        return new AccessControlRule(AccessType.anonymous, start, end);
    }

    private AccessControlRule(AccessType accessType, Date start, Date end) {
        put("access_type", accessType.name());
        if (start != null) {
            put(TtmlNode.START, ObjectUtils.toISO8601(start));
        }
        if (end != null) {
            put(TtmlNode.END, ObjectUtils.toISO8601(end));
        }
    }
}
