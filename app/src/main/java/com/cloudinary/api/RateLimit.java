package com.cloudinary.api;

import java.util.Date;

/* JADX INFO: loaded from: classes11.dex */
public class RateLimit {
    private long limit = 0;
    private long remaining = 0;
    private Date reset = null;

    public long getLimit() {
        return this.limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getRemaining() {
        return this.remaining;
    }

    public void setRemaining(long remaining) {
        this.remaining = remaining;
    }

    public Date getReset() {
        return this.reset;
    }

    public void setReset(Date reset) {
        this.reset = reset;
    }
}
