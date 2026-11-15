package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.CharConversionException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
/* JADX INFO: loaded from: classes11.dex */
public final class zzma {
    private static final Object zza = new Object();
    private static final String zzb = zzma.class.getSimpleName();
    private zzcc zzc;

    public final synchronized zzbt zza() throws GeneralSecurityException {
        return this.zzc.zza();
    }

    static /* synthetic */ void zza(zzbt zzbtVar, zzce zzceVar, zzbg zzbgVar) throws GeneralSecurityException {
        try {
            if (zzbgVar != null) {
                zzbtVar.zza(zzceVar, zzbgVar, new byte[0]);
            } else {
                zzcf.zza(zzbtVar, zzceVar, zzbq.zza());
            }
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    private zzma(zza zzaVar) {
        new zzmh(zzaVar.zza, zzaVar.zzb, zzaVar.zzc);
        this.zzc = zzaVar.zzi;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@23.1.0 */
    public static final class zza {
        private Context zza = null;
        private String zzb = null;
        private String zzc = null;
        private String zzd = null;
        private zzbg zze = null;
        private boolean zzf = true;
        private zzbu zzg = null;
        private zzvu zzh = null;
        private zzcc zzi;

        private final zzbg zzb() throws GeneralSecurityException {
            zzma.zzd();
            zzmf zzmfVar = new zzmf();
            try {
                boolean zZzc = zzmf.zzc(this.zzd);
                try {
                    return zzmfVar.zza(this.zzd);
                } catch (GeneralSecurityException | ProviderException e) {
                    if (!zZzc) {
                        throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.zzd), e);
                    }
                    Log.w(zzma.zzb, "cannot use Android Keystore, it'll be disabled", e);
                    return null;
                }
            } catch (GeneralSecurityException | ProviderException e2) {
                Log.w(zzma.zzb, "cannot use Android Keystore, it'll be disabled", e2);
                return null;
            }
        }

        private static zzcc zza(byte[] bArr) throws GeneralSecurityException, IOException {
            return zzcc.zza(zzbh.zza(zzbf.zza(bArr)));
        }

        private final zzcc zzb(byte[] bArr) throws GeneralSecurityException, IOException {
            try {
                this.zze = new zzmf().zza(this.zzd);
                try {
                    return zzcc.zza(zzbt.zza(zzbf.zza(bArr), this.zze, new byte[0]));
                } catch (IOException | GeneralSecurityException e) {
                    try {
                        return zza(bArr);
                    } catch (IOException e2) {
                        throw e;
                    }
                }
            } catch (GeneralSecurityException | ProviderException e3) {
                try {
                    zzcc zzccVarZza = zza(bArr);
                    Log.w(zzma.zzb, "cannot use Android Keystore, it'll be disabled", e3);
                    return zzccVarZza;
                } catch (IOException e4) {
                    throw e3;
                }
            }
        }

        public final zza zza(zzvu zzvuVar) {
            this.zzh = zzvuVar;
            return this;
        }

        public final zza zza(String str) {
            if (!str.startsWith("android-keystore://")) {
                throw new IllegalArgumentException("key URI must start with android-keystore://");
            }
            if (!this.zzf) {
                throw new IllegalArgumentException("cannot call withMasterKeyUri() after calling doNotUseKeystore()");
            }
            this.zzd = str;
            return this;
        }

        public final zza zza(Context context, String str, String str2) throws IOException {
            if (context == null) {
                throw new IllegalArgumentException("need an Android context");
            }
            this.zza = context;
            this.zzb = str;
            this.zzc = str2;
            return this;
        }

        public final synchronized zzma zza() throws GeneralSecurityException, IOException {
            zzma zzmaVar;
            if (this.zzb == null) {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
            if (this.zzh != null && this.zzg == null) {
                this.zzg = zzbu.zza(zzcp.zza(this.zzh.zzk()));
            }
            synchronized (zzma.zza) {
                try {
                    byte[] bArrZzb = zzb(this.zza, this.zzb, this.zzc);
                    if (bArrZzb == null) {
                        if (this.zzd != null) {
                            this.zze = zzb();
                        }
                        if (this.zzg == null) {
                            throw new GeneralSecurityException("cannot read or generate keyset");
                        }
                        zzbt zzbtVarZza = zzbt.zza(this.zzg);
                        zzma.zza(zzbtVarZza, new zzmh(this.zza, this.zzb, this.zzc), this.zze);
                        this.zzi = zzcc.zza(zzbtVarZza);
                    } else if (this.zzd == null) {
                        this.zzi = zza(bArrZzb);
                    } else {
                        zzma.zzd();
                        this.zzi = zzb(bArrZzb);
                    }
                    zzmaVar = new zzma(this);
                } catch (Throwable th) {
                    throw th;
                }
            }
            return zzmaVar;
        }

        private static byte[] zzb(Context context, String str, String str2) throws IOException {
            SharedPreferences sharedPreferences;
            if (str == null) {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
            Context applicationContext = context.getApplicationContext();
            if (str2 == null) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
            } else {
                sharedPreferences = applicationContext.getSharedPreferences(str2, 0);
            }
            try {
                String string = sharedPreferences.getString(str, null);
                if (string == null) {
                    return null;
                }
                return zzyt.zza(string);
            } catch (ClassCastException | IllegalArgumentException e) {
                throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", str));
            }
        }
    }

    static /* synthetic */ boolean zzd() {
        return true;
    }
}
