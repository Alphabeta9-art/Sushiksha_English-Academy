package androidx.work.impl.model;

/* JADX INFO: loaded from: classes11.dex */
public class SystemIdInfo {
    public final int systemId;
    public final String workSpecId;

    public SystemIdInfo(String workSpecId, int systemId) {
        this.workSpecId = workSpecId;
        this.systemId = systemId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SystemIdInfo)) {
            return false;
        }
        SystemIdInfo that = (SystemIdInfo) o;
        if (this.systemId != that.systemId) {
            return false;
        }
        return this.workSpecId.equals(that.workSpecId);
    }

    public int hashCode() {
        int result = this.workSpecId.hashCode();
        return (result * 31) + this.systemId;
    }
}
