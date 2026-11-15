package com.cloudinary.android.cldvideoplayer.analytics.models;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes11.dex */
public enum PlayerKeyPath {
    STATUS(NotificationCompat.CATEGORY_STATUS),
    TIME_CONTROL_STATUS("timeControlStatus"),
    DURATION(TypedValues.TransitionType.S_DURATION);

    private final String value;

    PlayerKeyPath(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
