package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public class AuthenticationExtensionsClientOutputs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthenticationExtensionsClientOutputs> CREATOR = new zzc();
    private final UvmEntries zza;
    private final zzf zzb;
    private final AuthenticationExtensionsCredPropsOutputs zzc;
    private final zzh zzd;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
    public static final class Builder {
        private UvmEntries zza;
        private AuthenticationExtensionsCredPropsOutputs zzb;

        public AuthenticationExtensionsClientOutputs build() {
            return new AuthenticationExtensionsClientOutputs(this.zza, null, this.zzb, null);
        }

        public Builder setCredProps(AuthenticationExtensionsCredPropsOutputs authenticationExtensionsCredPropsOutputs) {
            this.zzb = authenticationExtensionsCredPropsOutputs;
            return this;
        }

        public Builder setUserVerificationMethodEntries(UvmEntries uvmEntries) {
            this.zza = uvmEntries;
            return this;
        }
    }

    AuthenticationExtensionsClientOutputs(UvmEntries uvmEntries, zzf zzfVar, AuthenticationExtensionsCredPropsOutputs authenticationExtensionsCredPropsOutputs, zzh zzhVar) {
        this.zza = uvmEntries;
        this.zzb = zzfVar;
        this.zzc = authenticationExtensionsCredPropsOutputs;
        this.zzd = zzhVar;
    }

    public static AuthenticationExtensionsClientOutputs deserializeFromBytes(byte[] serializedBytes) {
        return (AuthenticationExtensionsClientOutputs) SafeParcelableSerializer.deserializeFromBytes(serializedBytes, CREATOR);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthenticationExtensionsClientOutputs)) {
            return false;
        }
        AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs = (AuthenticationExtensionsClientOutputs) obj;
        return Objects.equal(this.zza, authenticationExtensionsClientOutputs.zza) && Objects.equal(this.zzb, authenticationExtensionsClientOutputs.zzb) && Objects.equal(this.zzc, authenticationExtensionsClientOutputs.zzc) && Objects.equal(this.zzd, authenticationExtensionsClientOutputs.zzd);
    }

    public AuthenticationExtensionsCredPropsOutputs getCredProps() {
        return this.zzc;
    }

    public UvmEntries getUvmEntries() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzb, this.zzc, this.zzd);
    }

    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(dest);
        SafeParcelWriter.writeParcelable(dest, 1, getUvmEntries(), flags, false);
        SafeParcelWriter.writeParcelable(dest, 2, this.zzb, flags, false);
        SafeParcelWriter.writeParcelable(dest, 3, getCredProps(), flags, false);
        SafeParcelWriter.writeParcelable(dest, 4, this.zzd, flags, false);
        SafeParcelWriter.finishObjectHeader(dest, iBeginObjectHeader);
    }

    public final JSONObject zza() {
        try {
            JSONObject jSONObject = new JSONObject();
            AuthenticationExtensionsCredPropsOutputs authenticationExtensionsCredPropsOutputs = this.zzc;
            if (authenticationExtensionsCredPropsOutputs != null) {
                jSONObject.put("credProps", authenticationExtensionsCredPropsOutputs.zza());
            }
            UvmEntries uvmEntries = this.zza;
            if (uvmEntries != null) {
                jSONObject.put("uvm", uvmEntries.zza());
            }
            zzh zzhVar = this.zzd;
            if (zzhVar != null) {
                jSONObject.put("prf", zzhVar.zza());
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException("Error encoding AuthenticationExtensionsClientOutputs to JSON object", e);
        }
    }
}
