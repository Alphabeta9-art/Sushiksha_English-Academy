package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.p002firebaseauthapi.zzagl;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzag implements Parcelable.Creator<zzad> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzad createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzagl zzaglVar = null;
        zzz zzzVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        ArrayList arrayListCreateTypedList = null;
        ArrayList<String> arrayListCreateStringList = null;
        String strCreateString3 = null;
        Boolean booleanObject = null;
        zzaf zzafVar = null;
        com.google.firebase.auth.zze zzeVar = null;
        zzbl zzblVar = null;
        ArrayList arrayListCreateTypedList2 = null;
        boolean z = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    zzaglVar = (zzagl) SafeParcelReader.createParcelable(parcel, header, zzagl.CREATOR);
                    break;
                case 2:
                    zzzVar = (zzz) SafeParcelReader.createParcelable(parcel, header, zzz.CREATOR);
                    break;
                case 3:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, zzz.CREATOR);
                    break;
                case 6:
                    arrayListCreateStringList = SafeParcelReader.createStringList(parcel, header);
                    break;
                case 7:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 8:
                    booleanObject = SafeParcelReader.readBooleanObject(parcel, header);
                    break;
                case 9:
                    zzafVar = (zzaf) SafeParcelReader.createParcelable(parcel, header, zzaf.CREATOR);
                    break;
                case 10:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 11:
                    zzeVar = (com.google.firebase.auth.zze) SafeParcelReader.createParcelable(parcel, header, com.google.firebase.auth.zze.CREATOR);
                    break;
                case 12:
                    zzblVar = (zzbl) SafeParcelReader.createParcelable(parcel, header, zzbl.CREATOR);
                    break;
                case 13:
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, header, com.google.firebase.auth.zzan.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzad(zzaglVar, zzzVar, strCreateString, strCreateString2, arrayListCreateTypedList, arrayListCreateStringList, strCreateString3, booleanObject, zzafVar, z, zzeVar, zzblVar, arrayListCreateTypedList2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzad[] newArray(int i) {
        return new zzad[i];
    }
}
