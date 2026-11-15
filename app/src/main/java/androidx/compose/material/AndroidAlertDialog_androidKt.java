package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AndroidAlertDialog.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a§\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0015\b\u0002\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u0090\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"AlertDialog", "", "onDismissRequest", "Lkotlin/Function0;", "confirmButton", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "dismissButton", "title", "text", "shape", "Landroidx/compose/ui/graphics/Shape;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "properties", "Landroidx/compose/ui/window/DialogProperties;", "AlertDialog-6oU6zVQ", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/ui/window/DialogProperties;Landroidx/compose/runtime/Composer;II)V", "buttons", "AlertDialog-wqdebIU", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/ui/window/DialogProperties;Landroidx/compose/runtime/Composer;II)V", "material"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidAlertDialog_androidKt {
    static final Unit AlertDialog_6oU6zVQ$lambda$0(Function0 function0, Function2 function2, Modifier modifier, Function2 function3, Function2 function4, Function2 function5, Shape shape, long j, long j2, DialogProperties dialogProperties, int i, int i2, Composer composer, int i3) {
        m1682AlertDialog6oU6zVQ(function0, function2, modifier, function3, function4, function5, shape, j, j2, dialogProperties, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit AlertDialog_wqdebIU$lambda$0(Function0 function0, Function2 function2, Modifier modifier, Function2 function3, Function2 function4, Shape shape, long j, long j2, DialogProperties dialogProperties, int i, int i2, Composer composer, int i3) {
        m1683AlertDialogwqdebIU(function0, function2, modifier, function3, function4, shape, j, j2, dialogProperties, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: AlertDialog-6oU6zVQ, reason: not valid java name */
    public static final void m1682AlertDialog6oU6zVQ(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Shape shape, long j, long j2, DialogProperties properties, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Function2<? super Composer, ? super Integer, Unit> function7;
        Function2<? super Composer, ? super Integer, Unit> function8;
        Shape shape2;
        int $dirty;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function9;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Shape shape3;
        final long j3;
        final long j4;
        final DialogProperties properties2;
        long jM1769getSurface0d7_KjU;
        long jM1783contentColorForek8zF_U;
        DialogProperties properties3;
        int $dirty2;
        int $dirty3;
        Composer $composer2 = $composer.startRestartGroup(1967984963);
        ComposerKt.sourceInformation($composer2, "C(AlertDialog)N(onDismissRequest,confirmButton,modifier,dismissButton,title,text,shape,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,properties)41@1490L354:AndroidAlertDialog.android.kt#jmzs0o");
        int $dirty4 = $changed;
        if (($changed & 6) == 0) {
            $dirty4 |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty4 |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty4 |= 3072;
            function6 = function3;
        } else if (($changed & 3072) == 0) {
            function6 = function3;
            $dirty4 |= $composer2.changedInstance(function6) ? 2048 : 1024;
        } else {
            function6 = function3;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty4 |= 24576;
            function7 = function4;
        } else if (($changed & 24576) == 0) {
            function7 = function4;
            $dirty4 |= $composer2.changedInstance(function7) ? 16384 : 8192;
        } else {
            function7 = function4;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function8 = function5;
        } else if ((196608 & $changed) == 0) {
            function8 = function5;
            $dirty4 |= $composer2.changedInstance(function8) ? 131072 : 65536;
        } else {
            function8 = function5;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                shape2 = shape;
                int i6 = $composer2.changed(shape2) ? 1048576 : 524288;
                $dirty4 |= i6;
            } else {
                shape2 = shape;
            }
            $dirty4 |= i6;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                int i7 = $composer2.changed(j) ? 8388608 : 4194304;
                $dirty = $dirty3 | i7;
            } else {
                $dirty3 = $dirty4;
            }
            $dirty = $dirty3 | i7;
        } else {
            $dirty = $dirty4;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= ((i & 256) == 0 && $composer2.changed(j2)) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i8 = i & 512;
        if (i8 != 0) {
            $dirty |= C.ENCODING_PCM_32BIT;
        } else if ((805306368 & $changed) == 0) {
            $dirty |= $composer2.changed(properties) ? 536870912 : 268435456;
        }
        if (!$composer2.shouldExecute(($dirty & 306783379) != 306783378, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function9 = function6;
            function10 = function7;
            function11 = function8;
            shape3 = shape2;
            j3 = j;
            j4 = j2;
            properties2 = properties;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "77@4252L6,77@4310L6,77@4352L32");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty &= -234881025;
                }
                jM1769getSurface0d7_KjU = j;
                jM1783contentColorForek8zF_U = j2;
                properties3 = properties;
                $dirty2 = $dirty;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function6 = null;
                }
                if (i4 != 0) {
                    function7 = null;
                }
                if (i5 != 0) {
                    function8 = null;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getMedium();
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1769getSurface0d7_KjU();
                } else {
                    jM1769getSurface0d7_KjU = j;
                }
                if ((i & 256) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer2, ($dirty >> 21) & 14);
                    $dirty &= -234881025;
                } else {
                    jM1783contentColorForek8zF_U = j2;
                }
                if (i8 != 0) {
                    properties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    $dirty2 = $dirty;
                } else {
                    properties3 = properties;
                    $dirty2 = $dirty;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1967984963, $dirty2, -1, "androidx.compose.material.AlertDialog (AndroidAlertDialog.android.kt:41)");
            }
            int $changed$iv = ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (29360128 & $dirty2) | (234881024 & $dirty2) | (1879048192 & $dirty2);
            Modifier modifier$iv = modifier2;
            Shape shape$iv = shape2;
            DialogProperties properties$iv = properties3;
            long j5 = jM1769getSurface0d7_KjU;
            ComposerKt.sourceInformationMarkerStart($composer2, 1483448977, "CC(AlertDialogImpl)N(onDismissRequest,confirmButton,modifier,dismissButton,title,text,shape,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,properties)149@6875L386,147@6799L678:AlertDialog.kt#jmzs0o");
            DialogProperties properties4 = properties3;
            m1683AlertDialogwqdebIU(function0, ComposableLambdaKt.rememberComposableLambda(-309297447, true, new AlertDialogKt$AlertDialogImpl$1(function6, function2), $composer2, 54), modifier$iv, function7, function8, shape$iv, jM1769getSurface0d7_KjU, jM1783contentColorForek8zF_U, properties$iv, $composer2, ($changed$iv & 14) | 48 | ($changed$iv & 896) | (($changed$iv >> 3) & 7168) | (($changed$iv >> 3) & 57344) | (($changed$iv >> 3) & 458752) | (($changed$iv >> 3) & 3670016) | (($changed$iv >> 3) & 29360128) | (($changed$iv >> 3) & 234881024), 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function9 = function6;
            function10 = function7;
            function11 = function8;
            shape3 = shape2;
            j4 = jM1783contentColorForek8zF_U;
            properties2 = properties4;
            j3 = j5;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AndroidAlertDialog_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidAlertDialog_androidKt.AlertDialog_6oU6zVQ$lambda$0(function0, function2, modifier3, function9, function10, function11, shape3, j3, j4, properties2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: AlertDialog-wqdebIU, reason: not valid java name */
    public static final void m1683AlertDialogwqdebIU(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Shape shape, long j, long j2, DialogProperties properties, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Function2<? super Composer, ? super Integer, Unit> function6;
        Shape shape2;
        int $dirty;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final Function2<? super Composer, ? super Integer, Unit> function8;
        final Shape shape3;
        final long j3;
        final long j4;
        final DialogProperties properties2;
        long jM1769getSurface0d7_KjU;
        long jM1783contentColorForek8zF_U;
        DialogProperties properties3;
        int $dirty2;
        Composer $composer2 = $composer.startRestartGroup(1409209698);
        ComposerKt.sourceInformation($composer2, "C(AlertDialog)N(onDismissRequest,buttons,modifier,title,text,shape,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,properties)66@2171L303:AndroidAlertDialog.android.kt#jmzs0o");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer2.changedInstance(function0) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty3 |= 3072;
            function5 = function3;
        } else if (($changed & 3072) == 0) {
            function5 = function3;
            $dirty3 |= $composer2.changedInstance(function5) ? 2048 : 1024;
        } else {
            function5 = function3;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty3 |= 24576;
            function6 = function4;
        } else if (($changed & 24576) == 0) {
            function6 = function4;
            $dirty3 |= $composer2.changedInstance(function6) ? 16384 : 8192;
        } else {
            function6 = function4;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i5 = $composer2.changed(shape2) ? 131072 : 65536;
                $dirty3 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i5;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                $dirty2 = $dirty3;
                int i6 = $composer2.changed(j) ? 1048576 : 524288;
                $dirty = $dirty2 | i6;
            } else {
                $dirty2 = $dirty3;
            }
            $dirty = $dirty2 | i6;
        } else {
            $dirty = $dirty3;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= ((i & 128) == 0 && $composer2.changed(j2)) ? 8388608 : 4194304;
        }
        int i7 = i & 256;
        if (i7 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(properties) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (!$composer2.shouldExecute((38347923 & $dirty) != 38347922, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function7 = function5;
            function8 = function6;
            shape3 = shape2;
            j3 = j;
            j4 = j2;
            properties2 = properties;
        } else {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "77@6141L6,77@6199L6,77@6241L32");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                jM1769getSurface0d7_KjU = j;
                jM1783contentColorForek8zF_U = j2;
                properties3 = properties;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function5 = null;
                }
                if (i4 != 0) {
                    function6 = null;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    shape2 = MaterialTheme.INSTANCE.getShapes($composer2, 6).getMedium();
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    jM1769getSurface0d7_KjU = MaterialTheme.INSTANCE.getColors($composer2, 6).m1769getSurface0d7_KjU();
                } else {
                    jM1769getSurface0d7_KjU = j;
                }
                if ((i & 128) != 0) {
                    jM1783contentColorForek8zF_U = ColorsKt.m1783contentColorForek8zF_U(jM1769getSurface0d7_KjU, $composer2, ($dirty >> 18) & 14);
                    $dirty &= -29360129;
                } else {
                    jM1783contentColorForek8zF_U = j2;
                }
                properties3 = i7 != 0 ? new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null) : properties;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1409209698, $dirty, -1, "androidx.compose.material.AlertDialog (AndroidAlertDialog.android.kt:66)");
            }
            int $changed$iv = ($dirty & 14) | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty) | (234881024 & $dirty);
            DialogProperties properties$iv = properties3;
            Modifier modifier$iv = modifier2;
            Shape shape$iv = shape2;
            long j5 = jM1769getSurface0d7_KjU;
            ComposerKt.sourceInformationMarkerStart($composer2, -214309806, "CC(AlertDialogImpl)N(onDismissRequest,buttons,modifier,title,text,shape,backgroundColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,properties)180@7953L276,180@7884L345:AlertDialog.kt#jmzs0o");
            DialogProperties properties4 = properties3;
            AndroidDialog_androidKt.Dialog(function0, properties$iv, ComposableLambdaKt.rememberComposableLambda(-488319269, true, new AlertDialogKt$AlertDialogImpl$2(function2, modifier$iv, function5, function6, shape$iv, jM1769getSurface0d7_KjU, jM1783contentColorForek8zF_U), $composer2, 54), $composer2, ($changed$iv & 14) | 384 | (($changed$iv >> 21) & 112), 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function7 = function5;
            function8 = function6;
            shape3 = shape2;
            j4 = jM1783contentColorForek8zF_U;
            j3 = j5;
            properties2 = properties4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material.AndroidAlertDialog_androidKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidAlertDialog_androidKt.AlertDialog_wqdebIU$lambda$0(function0, function2, modifier3, function7, function8, shape3, j3, j4, properties2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
