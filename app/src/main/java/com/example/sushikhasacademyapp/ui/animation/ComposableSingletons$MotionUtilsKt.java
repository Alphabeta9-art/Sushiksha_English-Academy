package com.example.sushikhasacademyapp.ui.animation;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.KeyboardArrowUpKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: MotionUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ComposableSingletons$MotionUtilsKt {
    public static final ComposableSingletons$MotionUtilsKt INSTANCE = new ComposableSingletons$MotionUtilsKt();

    /* JADX INFO: renamed from: lambda$-2105741857, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f267lambda$2105741857 = ComposableLambdaKt.composableLambdaInstance(-2105741857, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.ComposableSingletons$MotionUtilsKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MotionUtilsKt.lambda__2105741857$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1979134950, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f266lambda$1979134950 = ComposableLambdaKt.composableLambdaInstance(-1979134950, false, new Function2() { // from class: com.example.sushikhasacademyapp.ui.animation.ComposableSingletons$MotionUtilsKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$MotionUtilsKt.lambda__1979134950$lambda$1((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* JADX INFO: renamed from: getLambda$-1979134950$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8734getLambda$1979134950$app() {
        return f266lambda$1979134950;
    }

    /* JADX INFO: renamed from: getLambda$-2105741857$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8735getLambda$2105741857$app() {
        return f267lambda$2105741857;
    }

    static final Unit lambda__2105741857$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C613@19376L133:MotionUtils.kt#b4fcsz");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2105741857, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.ComposableSingletons$MotionUtilsKt.lambda$-2105741857.<anonymous> (MotionUtils.kt:613)");
            }
            IconKt.m2520Iconww6aTOc(KeyboardArrowUpKt.getKeyboardArrowUp(Icons.INSTANCE.getDefault()), "Scroll to top", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1979134950$lambda$1(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C650@20689L133:MotionUtils.kt#b4fcsz");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1979134950, $changed, -1, "com.example.sushikhasacademyapp.ui.animation.ComposableSingletons$MotionUtilsKt.lambda$-1979134950.<anonymous> (MotionUtils.kt:650)");
            }
            IconKt.m2520Iconww6aTOc(KeyboardArrowUpKt.getKeyboardArrowUp(Icons.INSTANCE.getDefault()), "Scroll to top", (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
