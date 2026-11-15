package com.google.android.recaptcha;

import android.app.Application;
import androidx.media3.common.MimeTypes;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.internal.zzco;
import com.google.android.recaptcha.internal.zzcx;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmStatic;

/* JADX INFO: compiled from: com.google.android.recaptcha:recaptcha@@18.5.1 */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086@ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J&\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0011"}, d2 = {"Lcom/google/android/recaptcha/Recaptcha;", "", "()V", "getClient", "Lkotlin/Result;", "Lcom/google/android/recaptcha/RecaptchaClient;", MimeTypes.BASE_TYPE_APPLICATION, "Landroid/app/Application;", "siteKey", "", "timeout", "", "getClient-BWLJW6A", "(Landroid/app/Application;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTasksClient", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/recaptcha/RecaptchaTasksClient;", "java.com.google.android.libraries.abuse.recaptcha.enterprise_enterprise"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class Recaptcha {
    public static final Recaptcha INSTANCE = new Recaptcha();

    private Recaptcha() {
    }

    /* JADX INFO: renamed from: getClient-BWLJW6A$default, reason: not valid java name */
    public static /* synthetic */ Object m8888getClientBWLJW6A$default(Recaptcha recaptcha, Application application, String str, long j, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            j = 10000;
        }
        return recaptcha.m8889getClientBWLJW6A(application, str, j, continuation);
    }

    @JvmStatic
    public static final Task<RecaptchaTasksClient> getTasksClient(Application application, String siteKey) {
        zzco zzcoVar = zzco.zza;
        return zzco.zzh(application, siteKey, 10000L);
    }

    @JvmStatic
    public static final Task<RecaptchaTasksClient> getTasksClient(Application application, String siteKey, long timeout) {
        zzco zzcoVar = zzco.zza;
        return zzco.zzh(application, siteKey, timeout);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    /* JADX INFO: renamed from: getClient-BWLJW6A, reason: not valid java name */
    public final Object m8889getClientBWLJW6A(Application application, String str, long j, Continuation<? super Result<? extends RecaptchaClient>> continuation) {
        Recaptcha$getClient$1 recaptcha$getClient$1;
        if (continuation instanceof Recaptcha$getClient$1) {
            Recaptcha$getClient$1 recaptcha$getClient$2 = (Recaptcha$getClient$1) continuation;
            int i = recaptcha$getClient$2.zzc;
            if ((i & Integer.MIN_VALUE) != 0) {
                recaptcha$getClient$2.zzc = i - Integer.MIN_VALUE;
                recaptcha$getClient$1 = recaptcha$getClient$2;
            } else {
                recaptcha$getClient$1 = new Recaptcha$getClient$1(this, continuation);
            }
        } else {
            recaptcha$getClient$1 = new Recaptcha$getClient$1(this, continuation);
        }
        Object objZzg = recaptcha$getClient$1.zza;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (recaptcha$getClient$1.zzc) {
                case 0:
                    ResultKt.throwOnFailure(objZzg);
                    Result.Companion companion = Result.INSTANCE;
                    zzco zzcoVar = zzco.zza;
                    recaptcha$getClient$1.zzc = 1;
                    objZzg = zzco.zzg(application, str, j, null, recaptcha$getClient$1);
                    if (objZzg == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure(objZzg);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Result.m9088constructorimpl((zzcx) objZzg);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m9088constructorimpl(ResultKt.createFailure(th));
        }
    }
}
