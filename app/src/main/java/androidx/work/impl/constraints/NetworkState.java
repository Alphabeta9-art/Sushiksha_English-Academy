package androidx.work.impl.constraints;

/* JADX INFO: loaded from: classes11.dex */
public class NetworkState {
    private boolean mIsConnected;
    private boolean mIsMetered;
    private boolean mIsNotRoaming;
    private boolean mIsValidated;

    public NetworkState(boolean isConnected, boolean isValidated, boolean isMetered, boolean isNotRoaming) {
        this.mIsConnected = isConnected;
        this.mIsValidated = isValidated;
        this.mIsMetered = isMetered;
        this.mIsNotRoaming = isNotRoaming;
    }

    public boolean isConnected() {
        return this.mIsConnected;
    }

    public boolean isValidated() {
        return this.mIsValidated;
    }

    public boolean isMetered() {
        return this.mIsMetered;
    }

    public boolean isNotRoaming() {
        return this.mIsNotRoaming;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NetworkState)) {
            return false;
        }
        NetworkState other = (NetworkState) o;
        return this.mIsConnected == other.mIsConnected && this.mIsValidated == other.mIsValidated && this.mIsMetered == other.mIsMetered && this.mIsNotRoaming == other.mIsNotRoaming;
    }

    public int hashCode() {
        int result = this.mIsConnected ? 0 + 1 : 0;
        if (this.mIsValidated) {
            result += 16;
        }
        if (this.mIsMetered) {
            result += 256;
        }
        return this.mIsNotRoaming ? result + 4096 : result;
    }

    public String toString() {
        return String.format("[ Connected=%b Validated=%b Metered=%b NotRoaming=%b ]", Boolean.valueOf(this.mIsConnected), Boolean.valueOf(this.mIsValidated), Boolean.valueOf(this.mIsMetered), Boolean.valueOf(this.mIsNotRoaming));
    }
}
