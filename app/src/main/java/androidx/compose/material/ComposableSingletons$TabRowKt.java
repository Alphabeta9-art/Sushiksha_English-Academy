package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TabRow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$TabRowKt {
    public static final ComposableSingletons$TabRowKt INSTANCE = new ComposableSingletons$TabRowKt();

    /* JADX INFO: renamed from: lambda$-1216856915, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f72lambda$1216856915 = ComposableLambdaKt.composableLambdaInstance(-1216856915, false, new Function2() { // from class: androidx.compose.material.ComposableSingletons$TabRowKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TabRowKt.lambda__1216856915$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-222041980, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f73lambda$222041980 = ComposableLambdaKt.composableLambdaInstance(-222041980, false, new Function2() { // from class: androidx.compose.material.ComposableSingletons$TabRowKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$TabRowKt.lambda__222041980$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1216856915$material, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1793getLambda$1216856915$material() {
        return f72lambda$1216856915;
    }

    /* JADX INFO: renamed from: getLambda$-222041980$material, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m1794getLambda$222041980$material() {
        return f73lambda$222041980;
    }

    static final Unit lambda__1216856915$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C143@7135L9:TabRow.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1216856915, $changed, -1, "androidx.compose.material.ComposableSingletons$TabRowKt.lambda$-1216856915.<anonymous> (TabRow.kt:143)");
            }
            TabRowDefaults.INSTANCE.m1976Divider9IZ8Weo(null, 0.0f, 0L, $composer, 3072, 7);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__222041980$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C234@11823L9:TabRow.kt#jmzs0o");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-222041980, $changed, -1, "androidx.compose.material.ComposableSingletons$TabRowKt.lambda$-222041980.<anonymous> (TabRow.kt:234)");
            }
            TabRowDefaults.INSTANCE.m1976Divider9IZ8Weo(null, 0.0f, 0L, $composer, 3072, 7);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
