package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: BackdropScaffold.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$BackdropScaffoldKt {
    public static final ComposableSingletons$BackdropScaffoldKt INSTANCE = new ComposableSingletons$BackdropScaffoldKt();
    private static Function3<SnackbarHostState, Composer, Integer, Unit> lambda$2057676608 = ComposableLambdaKt.composableLambdaInstance(2057676608, false, new Function3() { // from class: androidx.compose.material.ComposableSingletons$BackdropScaffoldKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$BackdropScaffoldKt.lambda_2057676608$lambda$0((SnackbarHostState) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    public final Function3<SnackbarHostState, Composer, Integer, Unit> getLambda$2057676608$material() {
        return lambda$2057676608;
    }

    static final Unit lambda_2057676608$lambda$0(SnackbarHostState it, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(it)350@15992L16:BackdropScaffold.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(it) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2057676608, $dirty, -1, "androidx.compose.material.ComposableSingletons$BackdropScaffoldKt.lambda$2057676608.<anonymous> (BackdropScaffold.kt:350)");
            }
            SnackbarHostKt.SnackbarHost(it, null, null, $composer, $dirty & 14, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
