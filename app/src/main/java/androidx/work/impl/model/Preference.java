package androidx.work.impl.model;

/* JADX INFO: loaded from: classes11.dex */
public class Preference {
    public String mKey;
    public Long mValue;

    public Preference(String key, boolean value) {
        this(key, value ? 1L : 0L);
    }

    public Preference(String key, long value) {
        this.mKey = key;
        this.mValue = Long.valueOf(value);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Preference)) {
            return false;
        }
        Preference that = (Preference) o;
        if (!this.mKey.equals(that.mKey)) {
            return false;
        }
        if (this.mValue != null) {
            return this.mValue.equals(that.mValue);
        }
        return that.mValue == null;
    }

    public int hashCode() {
        int result = this.mKey.hashCode();
        return (result * 31) + (this.mValue != null ? this.mValue.hashCode() : 0);
    }
}
