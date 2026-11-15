package com.facebook.common.callercontext;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes11.dex */
public class ContextChain implements Parcelable {
    private static final char PARENT_SEPARATOR = '/';
    public static final String TAG_INFRA = "i";
    public static final String TAG_PRODUCT = "p";
    public static final String TAG_PRODUCT_AND_INFRA = "pi";

    @Nullable
    private Map<String, Object> mExtraData;
    private final String mName;

    @Nullable
    private final ContextChain mParent;

    @Nullable
    private String mSerializedChainString;
    private String mSerializedNodeString;
    private final String mTag;
    private static boolean sUseConcurrentHashMap = false;
    public static final Parcelable.Creator<ContextChain> CREATOR = new Parcelable.Creator<ContextChain>() { // from class: com.facebook.common.callercontext.ContextChain.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContextChain createFromParcel(Parcel in) {
            return new ContextChain(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ContextChain[] newArray(int size) {
            return new ContextChain[size];
        }
    };

    public ContextChain(String tag, String name, @Nullable Map<String, String> extraData, @Nullable ContextChain parent) {
        this.mTag = tag;
        this.mName = name;
        this.mSerializedNodeString = this.mTag + ":" + this.mName;
        this.mParent = parent;
        Map<String, Object> parentExtraData = parent != null ? parent.getExtraData() : null;
        if (parentExtraData != null) {
            if (sUseConcurrentHashMap) {
                this.mExtraData = new ConcurrentHashMap(parentExtraData);
            } else {
                this.mExtraData = new HashMap(parentExtraData);
            }
        }
        if (extraData != null) {
            if (this.mExtraData == null) {
                if (sUseConcurrentHashMap) {
                    this.mExtraData = new ConcurrentHashMap();
                } else {
                    this.mExtraData = new HashMap();
                }
            }
            this.mExtraData.putAll(extraData);
        }
    }

    public ContextChain(String serializedNodeString, @Nullable ContextChain parent) {
        this("serialized_tag", "serialized_name", null, parent);
        this.mSerializedNodeString = serializedNodeString;
    }

    public ContextChain(String tag, String name, @Nullable ContextChain parent) {
        this(tag, name, null, parent);
    }

    protected ContextChain(Parcel in) {
        this.mTag = in.readString();
        this.mName = in.readString();
        this.mSerializedNodeString = in.readString();
        this.mParent = (ContextChain) in.readParcelable(ContextChain.class.getClassLoader());
    }

    public static void setUseConcurrentHashMap(boolean useConcurrentHashMap) {
        sUseConcurrentHashMap = useConcurrentHashMap;
    }

    public String getName() {
        return this.mName;
    }

    public String getTag() {
        return this.mTag;
    }

    @Nullable
    public Map<String, Object> getExtraData() {
        return this.mExtraData;
    }

    @Nullable
    public ContextChain getParent() {
        return this.mParent;
    }

    public ContextChain getRootContextChain() {
        return this.mParent == null ? this : this.mParent.getRootContextChain();
    }

    @Nullable
    public String getStringExtra(String key) {
        Object val;
        if (this.mExtraData == null) {
            return null;
        }
        if ((sUseConcurrentHashMap && key == null) || (val = this.mExtraData.get(key)) == null) {
            return null;
        }
        return String.valueOf(val);
    }

    public void putObjectExtra(String key, Object value) {
        if (sUseConcurrentHashMap && (key == null || value == null)) {
            return;
        }
        if (this.mExtraData == null) {
            if (sUseConcurrentHashMap) {
                this.mExtraData = new ConcurrentHashMap();
            } else {
                this.mExtraData = new HashMap();
            }
        }
        this.mExtraData.put(key, value);
    }

    public String toString() {
        if (this.mSerializedChainString == null) {
            this.mSerializedChainString = getNodeString();
            if (this.mParent != null) {
                this.mSerializedChainString = this.mParent.toString() + PARENT_SEPARATOR + this.mSerializedChainString;
            }
        }
        return this.mSerializedChainString;
    }

    protected String getNodeString() {
        return this.mSerializedNodeString;
    }

    public String[] toStringArray() {
        return toString().split(String.valueOf(PARENT_SEPARATOR));
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ContextChain other = (ContextChain) obj;
        if (Objects.equals(getNodeString(), other.getNodeString()) && Objects.equals(this.mParent, other.mParent)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mParent, getNodeString());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTag);
        dest.writeString(this.mName);
        dest.writeString(getNodeString());
        dest.writeParcelable(this.mParent, flags);
    }
}
