package com.google.android.recaptcha.internal;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzbs implements zzav {
    private final Context zza;
    private final String zzb = "rce_";

    public zzbs(Context context) {
        this.zza = context;
    }

    @Override // com.google.android.recaptcha.internal.zzav
    public final String zza(String str) {
        File file = new File(this.zza.getCacheDir(), this.zzb.concat(String.valueOf(str)));
        if (file.exists()) {
            return new String(zzbx.zza(file), StandardCharsets.UTF_8);
        }
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzav
    public final void zzb() {
        try {
            File[] fileArrListFiles = this.zza.getCacheDir().listFiles();
            if (fileArrListFiles != null) {
                ArrayList arrayList = new ArrayList();
                for (File file : fileArrListFiles) {
                    if (StringsKt.startsWith$default(file.getName(), this.zzb, false, 2, (Object) null)) {
                        arrayList.add(file);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((File) it.next()).delete();
                }
            }
        } catch (Exception e) {
        }
    }

    @Override // com.google.android.recaptcha.internal.zzav
    public final void zzc(String str, String str2) throws GeneralSecurityException, IOException {
        CharRange charRange = new CharRange('A', 'z');
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(charRange, 10));
        Iterator<Character> it = charRange.iterator();
        while (it.hasNext()) {
            arrayList.add(Character.valueOf(((CharIterator) it).nextChar()));
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(CollectionsKt.shuffled(arrayList).subList(0, 8), "", null, null, 0, null, null, 62, null);
        File file = new File(this.zza.getCacheDir(), this.zzb.concat(String.valueOf(strJoinToString$default)));
        zzbx.zzb(file, String.valueOf(str2).getBytes(StandardCharsets.UTF_8));
        file.renameTo(new File(this.zza.getCacheDir(), this.zzb.concat(String.valueOf(str))));
    }

    @Override // com.google.android.recaptcha.internal.zzav
    public final boolean zzd(String str) {
        try {
            File[] fileArrListFiles = this.zza.getCacheDir().listFiles();
            File file = null;
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    if (Intrinsics.areEqual(file2.getName(), this.zzb + str)) {
                        file = file2;
                        break;
                    }
                }
            }
            return file != null;
        } catch (Exception e) {
            return false;
        }
    }
}
