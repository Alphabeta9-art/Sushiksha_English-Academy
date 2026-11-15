package com.google.android.gms.internal.fido;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public abstract class zzd extends zzb implements zze {
    public zzd() {
        super("com.google.android.gms.fido.fido2.api.IBooleanCallback");
    }

    @Override // com.google.android.gms.internal.fido.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                int i3 = zzc.zza;
                int i4 = parcel.readInt();
                zzc.zzc(parcel);
                zzb(i4 != 0);
                break;
            case 2:
                Status status = (Status) zzc.zza(parcel, Status.CREATOR);
                zzc.zzc(parcel);
                zzc(status);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
