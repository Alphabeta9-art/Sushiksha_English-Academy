package androidx.work;

import android.net.Uri;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes11.dex */
public final class ContentUriTriggers {
    private final Set<Trigger> mTriggers = new HashSet();

    public void add(Uri uri, boolean triggerForDescendants) {
        Trigger trigger = new Trigger(uri, triggerForDescendants);
        this.mTriggers.add(trigger);
    }

    public Set<Trigger> getTriggers() {
        return this.mTriggers;
    }

    public int size() {
        return this.mTriggers.size();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContentUriTriggers that = (ContentUriTriggers) o;
        return this.mTriggers.equals(that.mTriggers);
    }

    public int hashCode() {
        return this.mTriggers.hashCode();
    }

    public static final class Trigger {
        private final boolean mTriggerForDescendants;
        private final Uri mUri;

        Trigger(Uri uri, boolean triggerForDescendants) {
            this.mUri = uri;
            this.mTriggerForDescendants = triggerForDescendants;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public boolean shouldTriggerForDescendants() {
            return this.mTriggerForDescendants;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Trigger trigger = (Trigger) o;
            if (this.mTriggerForDescendants == trigger.mTriggerForDescendants && this.mUri.equals(trigger.mUri)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (this.mUri.hashCode() * 31) + (this.mTriggerForDescendants ? 1 : 0);
        }
    }
}
