package com.google.android.recaptcha.internal;

import android.net.Uri;
import android.text.TextUtils;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.facebook.common.util.UriUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
public final class zzdt {
    public static final /* synthetic */ int zza = 0;
    private static final List zzb = zzf(CollectionsKt.listOf((Object[]) new String[]{"www.recaptcha.net", "www.gstatic.com/recaptcha", "www.gstatic.cn/recaptcha"}));

    public static final boolean zza(Uri uri) {
        return zze(uri) && zzd(uri.toString());
    }

    public static final boolean zzb(String str) {
        Uri uri = Uri.parse(str);
        Intrinsics.checkNotNull(uri);
        return zze(uri) && zzd(uri.toString());
    }

    public static final boolean zzc(Uri uri) {
        return zze(uri);
    }

    private static final boolean zzd(String str) {
        List list = zzb;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (StringsKt.startsWith$default(str, (String) it.next(), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean zze(Uri uri) {
        return (TextUtils.isEmpty(uri.toString()) || !Intrinsics.areEqual(UriUtil.HTTPS_SCHEME, uri.getScheme()) || TextUtils.isEmpty(uri.getHost())) ? false : true;
    }

    private static final List zzf(List list) {
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add("https://" + ((String) it.next()) + DomExceptionUtils.SEPARATOR);
        }
        return arrayList;
    }
}
