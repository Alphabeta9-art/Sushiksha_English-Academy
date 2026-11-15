package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: SnackbarHost.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$SnackbarHostKt {
    public static final ComposableSingletons$SnackbarHostKt INSTANCE = new ComposableSingletons$SnackbarHostKt();
    private static Function3<SnackbarData, Composer, Integer, Unit> lambda$1890101041 = ComposableLambdaKt.composableLambdaInstance(1890101041, false, new Function3() { // from class: androidx.compose.material.ComposableSingletons$SnackbarHostKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$SnackbarHostKt.lambda_1890101041$lambda$0((SnackbarData) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    public final Function3<SnackbarData, Composer, Integer, Unit> getLambda$1890101041$material() {
        return lambda$1890101041;
    }

    static final Unit lambda_1890101041$lambda$0(SnackbarData it, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(it)154@6547L12:SnackbarHost.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer.changed(it) : $composer.changedInstance(it) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if ($composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1890101041, $dirty2, -1, "androidx.compose.material.ComposableSingletons$SnackbarHostKt.lambda$1890101041.<anonymous> (SnackbarHost.kt:154)");
            }
            SnackbarKt.m1937SnackbarsPrSdHI(it, null, false, null, 0L, 0L, 0L, 0.0f, $composer, $dirty2 & 14, 254);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
