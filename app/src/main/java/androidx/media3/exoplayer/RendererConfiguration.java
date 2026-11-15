package androidx.media3.exoplayer;

/* JADX INFO: loaded from: classes11.dex */
public final class RendererConfiguration {
    public static final RendererConfiguration DEFAULT = new RendererConfiguration(false);
    public final boolean tunneling;

    public RendererConfiguration(boolean tunneling) {
        this.tunneling = tunneling;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RendererConfiguration other = (RendererConfiguration) obj;
        if (this.tunneling == other.tunneling) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return !this.tunneling ? 1 : 0;
    }
}
