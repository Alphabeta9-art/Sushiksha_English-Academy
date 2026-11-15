package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* JADX INFO: compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzv implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        zzw zzwVar = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    int i2 = SafeParcelReader.readInt(parcel, header);
                    hashSet.add(1);
                    i = i2;
                    break;
                case 2:
                    zzw zzwVar2 = (zzw) SafeParcelReader.createParcelable(parcel, header, zzw.CREATOR);
                    hashSet.add(2);
                    zzwVar = zzwVar2;
                    break;
                case 3:
                    String strCreateString = SafeParcelReader.createString(parcel, header);
                    hashSet.add(3);
                    str = strCreateString;
                    break;
                case 4:
                    String strCreateString2 = SafeParcelReader.createString(parcel, header);
                    hashSet.add(4);
                    str2 = strCreateString2;
                    break;
                case 5:
                    String strCreateString3 = SafeParcelReader.createString(parcel, header);
                    hashSet.add(5);
                    str3 = strCreateString3;
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzu(hashSet, i, zzwVar, str, str2, str3);
        }
        throw new SafeParcelReader.ParseException("Overread allowed size end=" + iValidateObjectHeader, parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzu[i];
    }
}
