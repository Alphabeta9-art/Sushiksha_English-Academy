package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.DatePickerModalTokens;
import androidx.compose.material3.tokens.DialogTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.location.LocationRequestCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.facebook.common.util.UriUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DatePickerDialog.android.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0097\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0015\b\u0002\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\"\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0019\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a\"\u0010\u0010\u001b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006\u001c"}, d2 = {"DatePickerDialog", "", "onDismissRequest", "Lkotlin/Function0;", "confirmButton", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "dismissButton", "shape", "Landroidx/compose/ui/graphics/Shape;", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "colors", "Landroidx/compose/material3/DatePickerColors;", "properties", "Landroidx/compose/ui/window/DialogProperties;", UriUtil.LOCAL_CONTENT_SCHEME, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "DatePickerDialog-GmEhDVc", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;FLandroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DialogButtonsPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "DialogButtonsMainAxisSpacing", "F", "DialogButtonsCrossAxisSpacing", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DatePickerDialog_androidKt {
    private static final PaddingValues DialogButtonsPadding = PaddingKt.m816PaddingValuesa9UjIt4$default(0.0f, 0.0f, Dp.m7905constructorimpl(6), Dp.m7905constructorimpl(8), 3, null);
    private static final float DialogButtonsMainAxisSpacing = Dp.m7905constructorimpl(8);
    private static final float DialogButtonsCrossAxisSpacing = Dp.m7905constructorimpl(12);

    static final Unit DatePickerDialog_GmEhDVc$lambda$0(Function0 function0, Function2 function2, Modifier modifier, Function2 function3, Shape shape, float f, DatePickerColors datePickerColors, DialogProperties dialogProperties, Function3 function4, int i, int i2, Composer composer, int i3) {
        m2347DatePickerDialogGmEhDVc(function0, function2, modifier, function3, shape, f, datePickerColors, dialogProperties, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: DatePickerDialog-GmEhDVc, reason: not valid java name */
    public static final void m2347DatePickerDialogGmEhDVc(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Shape shape, float tonalElevation, DatePickerColors colors, DialogProperties properties, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function4, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Shape shape2;
        float tonalElevation2;
        final DatePickerColors colors2;
        Composer $composer2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Shape shape3;
        final float tonalElevation3;
        final DialogProperties properties2;
        final DatePickerColors colors3;
        int $dirty;
        DialogProperties properties3;
        Modifier modifier4;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final float tonalElevation4;
        boolean z;
        int $dirty2;
        final Shape shape4;
        Composer $composer3 = $composer.startRestartGroup(219718641);
        ComposerKt.sourceInformation($composer3, "C(DatePickerDialog)N(onDismissRequest,confirmButton,modifier,dismissButton,shape,tonalElevation:c#ui.unit.Dp,colors,properties,content)79@3746L1617,75@3595L1768:DatePickerDialog.android.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty3 |= 3072;
            function5 = function3;
        } else if (($changed & 3072) == 0) {
            function5 = function3;
            $dirty3 |= $composer3.changedInstance(function5) ? 2048 : 1024;
        } else {
            function5 = function3;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                int i4 = $composer3.changed(shape2) ? 16384 : 8192;
                $dirty3 |= i4;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i4;
        } else {
            shape2 = shape;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            tonalElevation2 = tonalElevation;
        } else if ((196608 & $changed) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty3 |= $composer3.changed(tonalElevation2) ? 131072 : 65536;
        } else {
            tonalElevation2 = tonalElevation;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                int i6 = $composer3.changed(colors2) ? 1048576 : 524288;
                $dirty3 |= i6;
            } else {
                colors2 = colors;
            }
            $dirty3 |= i6;
        } else {
            colors2 = colors;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changed(properties) ? 8388608 : 4194304;
        }
        if ((i & 256) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function4) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if (!$composer3.shouldExecute(($dirty3 & 38347923) != 38347922, $dirty4 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function6 = function5;
            shape3 = shape2;
            tonalElevation3 = tonalElevation2;
            properties2 = properties;
            colors3 = colors2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "55@2765L5,57@2882L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 16) != 0 ? $dirty4 & (-57345) : $dirty4;
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                properties3 = properties;
                function7 = function5;
                tonalElevation4 = tonalElevation2;
                z = false;
                $dirty2 = $dirty5;
                modifier4 = modifier2;
                shape4 = shape2;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function5 = null;
                }
                if ((i & 16) != 0) {
                    $dirty = $dirty4 & (-57345);
                    shape2 = DatePickerDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty4;
                }
                if (i5 != 0) {
                    tonalElevation2 = DatePickerDefaults.INSTANCE.m2346getTonalElevationD9Ej5fM();
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    colors2 = DatePickerDefaults.INSTANCE.colors($composer3, 6);
                }
                if (i7 != 0) {
                    modifier4 = modifier2;
                    function7 = function5;
                    tonalElevation4 = tonalElevation2;
                    properties3 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                    z = false;
                    $dirty2 = $dirty;
                    shape4 = shape2;
                } else {
                    properties3 = properties;
                    modifier4 = modifier2;
                    function7 = function5;
                    tonalElevation4 = tonalElevation2;
                    z = false;
                    $dirty2 = $dirty;
                    shape4 = shape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(219718641, $dirty2, -1, "androidx.compose.material3.DatePickerDialog (DatePickerDialog.android.kt:74)");
            }
            AlertDialogKt.BasicAlertDialog(function0, SizeKt.wrapContentHeight$default(modifier4, null, z, 3, null), properties3, ComposableLambdaKt.rememberComposableLambda(1108953335, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C87@4066L1291,80@3756L1601:DatePickerDialog.android.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1108953335, $changed2, -1, "androidx.compose.material3.DatePickerDialog.<anonymous> (DatePickerDialog.android.kt:80)");
                    }
                    Modifier modifierM853heightInVpY3zN4$default = SizeKt.m853heightInVpY3zN4$default(SizeKt.m862requiredWidth3ABfNKs(Modifier.INSTANCE, DatePickerModalTokens.INSTANCE.m3674getContainerWidthD9Ej5fM()), 0.0f, DatePickerModalTokens.INSTANCE.m3673getContainerHeightD9Ej5fM(), 1, null);
                    Shape shape5 = shape4;
                    long containerColor = colors2.getContainerColor();
                    float f = tonalElevation4;
                    final Function3<ColumnScope, Composer, Integer, Unit> function8 = function4;
                    final Function2<Composer, Integer, Unit> function9 = function7;
                    final Function2<Composer, Integer, Unit> function10 = function2;
                    SurfaceKt.m2929SurfaceT9BRK9s(modifierM853heightInVpY3zN4$default, shape5, containerColor, 0L, f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1782015378, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Code duplicated, block: B:27:0x01b7  */
                        /* JADX WARN: Code duplicated, block: B:30:0x01c3  */
                        /* JADX WARN: Code duplicated, block: B:31:0x01c9  */
                        /* JADX WARN: Code duplicated, block: B:41:0x02ed  */
                        /* JADX WARN: Code duplicated, block: B:44:0x02f9  */
                        /* JADX WARN: Code duplicated, block: B:45:0x02ff  */
                        /* JADX WARN: Code duplicated, block: B:48:0x0330  */
                        /* JADX WARN: Code duplicated, block: B:50:0x0342  */
                        /* JADX WARN: Code duplicated, block: B:51:0x0345  */
                        /* JADX WARN: Code duplicated, block: B:55:0x03f1  */
                        /* JADX WARN: Code duplicated, block: B:58:? A[RETURN, SYNTHETIC] */
                        public final void invoke(Composer $composer5, int $changed3) {
                            Function0<ComposeUiNode> function1;
                            int $changed$iv;
                            int compositeKeyHash$iv$iv;
                            Function0<ComposeUiNode> constructor;
                            Function0<ComposeUiNode> function11;
                            Composer $this$Layout_u24lambda_u240$iv$iv;
                            int compositeKeyHash$iv$iv2;
                            Function0<ComposeUiNode> constructor2;
                            Function0<ComposeUiNode> function12;
                            Composer $this$Layout_u24lambda_u240$iv$iv2;
                            int $changed$iv2;
                            ComposerKt.sourceInformation($composer5, "C88@4080L1267:DatePickerDialog.android.kt#uh7d8r");
                            if (!$composer5.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer5.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1782015378, $changed3, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous> (DatePickerDialog.android.kt:88)");
                            }
                            Arrangement.Vertical verticalArrangement$iv = Arrangement.INSTANCE.getSpaceBetween();
                            Function3<ColumnScope, Composer, Integer, Unit> function13 = function8;
                            final Function2<Composer, Integer, Unit> function14 = function9;
                            final Function2<Composer, Integer, Unit> function15 = function10;
                            ComposerKt.sourceInformationMarkerStart($composer5, -483455358, "CC(Column)P(2,3,1)87@4442L61,88@4508L133:Column.kt#2w3rfo");
                            Modifier modifier$iv = Modifier.INSTANCE;
                            Alignment.Horizontal horizontalAlignment$iv = Alignment.INSTANCE.getStart();
                            MeasurePolicy measurePolicy$iv = ColumnKt.columnMeasurePolicy(verticalArrangement$iv, horizontalAlignment$iv, $composer5, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                            int $changed$iv$iv = (48 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv3 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer5, modifier$iv);
                            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                function1 = constructor3;
                                $composer5.createNode(function1);
                            } else {
                                function1 = constructor3;
                                $composer5.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv3 = Updater.m4367constructorimpl($composer5);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv3.getInserting()) {
                                $changed$iv = 48;
                                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv3.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv3))) {
                                }
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                                int i8 = ($changed$iv$iv$iv >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                                int $changed4 = (($changed$iv >> 6) & 112) | 6;
                                ColumnScope $this$invoke_u24lambda_u242 = ColumnScopeInstance.INSTANCE;
                                ComposerKt.sourceInformationMarkerStart($composer5, -1407374204, "C94@4530L64,96@4638L695:DatePickerDialog.android.kt#uh7d8r");
                                Modifier modifier$iv2 = $this$invoke_u24lambda_u242.weight(Modifier.INSTANCE, 1.0f, false);
                                ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                                MeasurePolicy measurePolicy$iv2 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                                int $changed$iv$iv2 = (0 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv2 = $composer5.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv2 = ComposedModifierKt.materializeModifier($composer5, modifier$iv2);
                                constructor = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv2 = (($changed$iv$iv2 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                if (!($composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer5.startReusableNode();
                                if ($composer5.getInserting()) {
                                    function11 = constructor;
                                    $composer5.createNode(function11);
                                } else {
                                    function11 = constructor;
                                    $composer5.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer5);
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                    $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                    $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash2);
                                }
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv2, ComposeUiNode.INSTANCE.getSetModifier());
                                int i9 = ($changed$iv$iv$iv2 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                int i10 = ((0 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, -114868488, "C94@4583L9:DatePickerDialog.android.kt#uh7d8r");
                                function13.invoke($this$invoke_u24lambda_u242, $composer5, Integer.valueOf($changed4 & 14));
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                Modifier modifier$iv3 = PaddingKt.padding($this$invoke_u24lambda_u242.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd()), DatePickerDialog_androidKt.DialogButtonsPadding);
                                ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                                Alignment contentAlignment$iv2 = Alignment.INSTANCE.getTopStart();
                                MeasurePolicy measurePolicy$iv3 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv2, false);
                                int $changed$iv$iv3 = (0 << 3) & 112;
                                ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                                compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                                CompositionLocalMap localMap$iv$iv3 = $composer5.getCurrentCompositionLocalMap();
                                Modifier materialized$iv$iv3 = ComposedModifierKt.materializeModifier($composer5, modifier$iv3);
                                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                int $changed$iv$iv$iv3 = (($changed$iv$iv3 << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                                if (!($composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                $composer5.startReusableNode();
                                if ($composer5.getInserting()) {
                                    function12 = constructor2;
                                    $composer5.createNode(function12);
                                } else {
                                    function12 = constructor2;
                                    $composer5.useNode();
                                }
                                $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer5);
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                                    $changed$iv2 = 0;
                                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                    }
                                    Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i11 = ($changed$iv$iv$iv3 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                    int i12 = (($changed$iv2 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer5, 775961793, "C98@4839L5,99@4915L5,100@4944L371,97@4736L579:DatePickerDialog.android.kt#uh7d8r");
                                    ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(ColorSchemeKt.getValue(DialogTokens.INSTANCE.getActionLabelTextColor(), $composer5, 6), TypographyKt.getValue(DialogTokens.INSTANCE.getActionLabelTextFont(), $composer5, 6), ComposableLambdaKt.rememberComposableLambda(-1103927529, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$2$1
                                        @Override // kotlin.jvm.functions.Function2
                                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                            invoke(composer, num.intValue());
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(Composer $composer6, int $changed5) {
                                            ComposerKt.sourceInformation($composer6, "C104@5170L123,101@4970L323:DatePickerDialog.android.kt#uh7d8r");
                                            if ($composer6.shouldExecute(($changed5 & 3) != 2, $changed5 & 1)) {
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1103927529, $changed5, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:101)");
                                                }
                                                float f2 = DatePickerDialog_androidKt.DialogButtonsMainAxisSpacing;
                                                float f3 = DatePickerDialog_androidKt.DialogButtonsCrossAxisSpacing;
                                                final Function2<Composer, Integer, Unit> function16 = function14;
                                                final Function2<Composer, Integer, Unit> function17 = function15;
                                                AlertDialogKt.m2060AlertDialogFlowRowixp7dh8(f2, f3, ComposableLambdaKt.rememberComposableLambda(-1980163584, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$2$1.1
                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                        invoke(composer, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer $composer7, int $changed6) {
                                                        ComposerKt.sourceInformation($composer7, "C106@5252L15:DatePickerDialog.android.kt#uh7d8r");
                                                        if (!$composer7.shouldExecute(($changed6 & 3) != 2, $changed6 & 1)) {
                                                            $composer7.skipToGroupEnd();
                                                            return;
                                                        }
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventStart(-1980163584, $changed6, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:105)");
                                                        }
                                                        Function2<Composer, Integer, Unit> function18 = function16;
                                                        if (function18 == null) {
                                                            $composer7.startReplaceGroup(322524505);
                                                        } else {
                                                            $composer7.startReplaceGroup(-266690648);
                                                            ComposerKt.sourceInformation($composer7, "105@5215L8");
                                                            function18.invoke($composer7, 0);
                                                        }
                                                        $composer7.endReplaceGroup();
                                                        function17.invoke($composer7, 0);
                                                        if (ComposerKt.isTraceInProgress()) {
                                                            ComposerKt.traceEventEnd();
                                                        }
                                                    }
                                                }, $composer6, 54), $composer6, 438);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                    return;
                                                }
                                                return;
                                            }
                                            $composer6.skipToGroupEnd();
                                        }
                                    }, $composer5, 54), $composer5, 384);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    $composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    $composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    ComposerKt.sourceInformationMarkerEnd($composer5);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                                $changed$iv2 = 0;
                                $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                                $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash3);
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv3, ComposeUiNode.INSTANCE.getSetModifier());
                                int i13 = ($changed$iv$iv$iv3 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                                int i14 = (($changed$iv2 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, 775961793, "C98@4839L5,99@4915L5,100@4944L371,97@4736L579:DatePickerDialog.android.kt#uh7d8r");
                                ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(ColorSchemeKt.getValue(DialogTokens.INSTANCE.getActionLabelTextColor(), $composer5, 6), TypographyKt.getValue(DialogTokens.INSTANCE.getActionLabelTextFont(), $composer5, 6), ComposableLambdaKt.rememberComposableLambda(-1103927529, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$2$1
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer6, int $changed5) {
                                        ComposerKt.sourceInformation($composer6, "C104@5170L123,101@4970L323:DatePickerDialog.android.kt#uh7d8r");
                                        if ($composer6.shouldExecute(($changed5 & 3) != 2, $changed5 & 1)) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1103927529, $changed5, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:101)");
                                            }
                                            float f2 = DatePickerDialog_androidKt.DialogButtonsMainAxisSpacing;
                                            float f3 = DatePickerDialog_androidKt.DialogButtonsCrossAxisSpacing;
                                            final Function2<? super Composer, ? super Integer, Unit> function16 = function14;
                                            final Function2<? super Composer, ? super Integer, Unit> function17 = function15;
                                            AlertDialogKt.m2060AlertDialogFlowRowixp7dh8(f2, f3, ComposableLambdaKt.rememberComposableLambda(-1980163584, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$2$1.1
                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                    invoke(composer, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer $composer7, int $changed6) {
                                                    ComposerKt.sourceInformation($composer7, "C106@5252L15:DatePickerDialog.android.kt#uh7d8r");
                                                    if (!$composer7.shouldExecute(($changed6 & 3) != 2, $changed6 & 1)) {
                                                        $composer7.skipToGroupEnd();
                                                        return;
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-1980163584, $changed6, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:105)");
                                                    }
                                                    Function2<Composer, Integer, Unit> function18 = function16;
                                                    if (function18 == null) {
                                                        $composer7.startReplaceGroup(322524505);
                                                    } else {
                                                        $composer7.startReplaceGroup(-266690648);
                                                        ComposerKt.sourceInformation($composer7, "105@5215L8");
                                                        function18.invoke($composer7, 0);
                                                    }
                                                    $composer7.endReplaceGroup();
                                                    function17.invoke($composer7, 0);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                    }
                                                }
                                            }, $composer6, 54), $composer6, 438);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer6.skipToGroupEnd();
                                    }
                                }, $composer5, 54), $composer5, 384);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                            $changed$iv = 48;
                            $this$Layout_u24lambda_u240$iv$iv3.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv3));
                            $this$Layout_u24lambda_u240$iv$iv3.apply(Integer.valueOf(compositeKeyHash$iv$iv3), setCompositeKeyHash);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv3, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i15 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -384672921, "C89@4556L9:Column.kt#2w3rfo");
                            int $changed5 = (($changed$iv >> 6) & 112) | 6;
                            ColumnScope $this$invoke_u24lambda_u243 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1407374204, "C94@4530L64,96@4638L695:DatePickerDialog.android.kt#uh7d8r");
                            Modifier modifier$iv4 = $this$invoke_u24lambda_u243.weight(Modifier.INSTANCE, 1.0f, false);
                            ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv3 = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv4 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv3, false);
                            int $changed$iv$iv4 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv4 = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv4 = ComposedModifierKt.materializeModifier($composer5, modifier$iv4);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv4 = (($changed$iv$iv4 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                function11 = constructor;
                                $composer5.createNode(function11);
                            } else {
                                function11 = constructor;
                                $composer5.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv = Updater.m4367constructorimpl($composer5);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv.getInserting()) {
                            }
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash4);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv4, ComposeUiNode.INSTANCE.getSetModifier());
                            int i16 = ($changed$iv$iv$iv4 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                            int i17 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -114868488, "C94@4583L9:DatePickerDialog.android.kt#uh7d8r");
                            function13.invoke($this$invoke_u24lambda_u243, $composer5, Integer.valueOf($changed5 & 14));
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            Modifier modifier$iv5 = PaddingKt.padding($this$invoke_u24lambda_u243.align(Modifier.INSTANCE, Alignment.INSTANCE.getEnd()), DatePickerDialog_androidKt.DialogButtonsPadding);
                            ComposerKt.sourceInformationMarkerStart($composer5, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                            Alignment contentAlignment$iv4 = Alignment.INSTANCE.getTopStart();
                            MeasurePolicy measurePolicy$iv5 = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv4, false);
                            int $changed$iv$iv5 = (0 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            compositeKeyHash$iv$iv2 = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv5 = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv5 = ComposedModifierKt.materializeModifier($composer5, modifier$iv5);
                            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv5 = (($changed$iv$iv5 << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                function12 = constructor2;
                                $composer5.createNode(function12);
                            } else {
                                function12 = constructor2;
                                $composer5.useNode();
                            }
                            $this$Layout_u24lambda_u240$iv$iv2 = Updater.m4367constructorimpl($composer5);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, measurePolicy$iv5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, localMap$iv$iv5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if (!$this$Layout_u24lambda_u240$iv$iv2.getInserting()) {
                                $changed$iv2 = 0;
                                if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv2.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv2))) {
                                }
                                Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                                int i18 = ($changed$iv$iv$iv5 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                                int i19 = (($changed$iv2 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart($composer5, 775961793, "C98@4839L5,99@4915L5,100@4944L371,97@4736L579:DatePickerDialog.android.kt#uh7d8r");
                                ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(ColorSchemeKt.getValue(DialogTokens.INSTANCE.getActionLabelTextColor(), $composer5, 6), TypographyKt.getValue(DialogTokens.INSTANCE.getActionLabelTextFont(), $composer5, 6), ComposableLambdaKt.rememberComposableLambda(-1103927529, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$2$1
                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer6, int $changed6) {
                                        ComposerKt.sourceInformation($composer6, "C104@5170L123,101@4970L323:DatePickerDialog.android.kt#uh7d8r");
                                        if ($composer6.shouldExecute(($changed6 & 3) != 2, $changed6 & 1)) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1103927529, $changed6, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:101)");
                                            }
                                            float f2 = DatePickerDialog_androidKt.DialogButtonsMainAxisSpacing;
                                            float f3 = DatePickerDialog_androidKt.DialogButtonsCrossAxisSpacing;
                                            final Function2<? super Composer, ? super Integer, Unit> function16 = function14;
                                            final Function2<? super Composer, ? super Integer, Unit> function17 = function15;
                                            AlertDialogKt.m2060AlertDialogFlowRowixp7dh8(f2, f3, ComposableLambdaKt.rememberComposableLambda(-1980163584, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$2$1.1
                                                @Override // kotlin.jvm.functions.Function2
                                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                    invoke(composer, num.intValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(Composer $composer7, int $changed7) {
                                                    ComposerKt.sourceInformation($composer7, "C106@5252L15:DatePickerDialog.android.kt#uh7d8r");
                                                    if (!$composer7.shouldExecute(($changed7 & 3) != 2, $changed7 & 1)) {
                                                        $composer7.skipToGroupEnd();
                                                        return;
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-1980163584, $changed7, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:105)");
                                                    }
                                                    Function2<Composer, Integer, Unit> function18 = function16;
                                                    if (function18 == null) {
                                                        $composer7.startReplaceGroup(322524505);
                                                    } else {
                                                        $composer7.startReplaceGroup(-266690648);
                                                        ComposerKt.sourceInformation($composer7, "105@5215L8");
                                                        function18.invoke($composer7, 0);
                                                    }
                                                    $composer7.endReplaceGroup();
                                                    function17.invoke($composer7, 0);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                    }
                                                }
                                            }, $composer6, 54), $composer6, 438);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        $composer6.skipToGroupEnd();
                                    }
                                }, $composer5, 54), $composer5, 384);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                $composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                ComposerKt.sourceInformationMarkerEnd($composer5);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                            $changed$iv2 = 0;
                            $this$Layout_u24lambda_u240$iv$iv2.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv2));
                            $this$Layout_u24lambda_u240$iv$iv2.apply(Integer.valueOf(compositeKeyHash$iv$iv2), setCompositeKeyHash5);
                            Updater.m4375setimpl($this$Layout_u24lambda_u240$iv$iv2, materialized$iv$iv5, ComposeUiNode.INSTANCE.getSetModifier());
                            int i110 = ($changed$iv$iv$iv5 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                            int i111 = (($changed$iv2 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, 775961793, "C98@4839L5,99@4915L5,100@4944L371,97@4736L579:DatePickerDialog.android.kt#uh7d8r");
                            ProvideContentColorTextStyleKt.m3367ProvideContentColorTextStyle3JVO9M(ColorSchemeKt.getValue(DialogTokens.INSTANCE.getActionLabelTextColor(), $composer5, 6), TypographyKt.getValue(DialogTokens.INSTANCE.getActionLabelTextFont(), $composer5, 6), ComposableLambdaKt.rememberComposableLambda(-1103927529, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$2$1
                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                    invoke(composer, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer $composer6, int $changed6) {
                                    ComposerKt.sourceInformation($composer6, "C104@5170L123,101@4970L323:DatePickerDialog.android.kt#uh7d8r");
                                    if ($composer6.shouldExecute(($changed6 & 3) != 2, $changed6 & 1)) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1103927529, $changed6, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:101)");
                                        }
                                        float f2 = DatePickerDialog_androidKt.DialogButtonsMainAxisSpacing;
                                        float f3 = DatePickerDialog_androidKt.DialogButtonsCrossAxisSpacing;
                                        final Function2<? super Composer, ? super Integer, Unit> function16 = function14;
                                        final Function2<? super Composer, ? super Integer, Unit> function17 = function15;
                                        AlertDialogKt.m2060AlertDialogFlowRowixp7dh8(f2, f3, ComposableLambdaKt.rememberComposableLambda(-1980163584, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$2$1.1
                                            @Override // kotlin.jvm.functions.Function2
                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                                invoke(composer, num.intValue());
                                                return Unit.INSTANCE;
                                            }

                                            public final void invoke(Composer $composer7, int $changed7) {
                                                ComposerKt.sourceInformation($composer7, "C106@5252L15:DatePickerDialog.android.kt#uh7d8r");
                                                if (!$composer7.shouldExecute(($changed7 & 3) != 2, $changed7 & 1)) {
                                                    $composer7.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-1980163584, $changed7, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:105)");
                                                }
                                                Function2<Composer, Integer, Unit> function18 = function16;
                                                if (function18 == null) {
                                                    $composer7.startReplaceGroup(322524505);
                                                } else {
                                                    $composer7.startReplaceGroup(-266690648);
                                                    ComposerKt.sourceInformation($composer7, "105@5215L8");
                                                    function18.invoke($composer7, 0);
                                                }
                                                $composer7.endReplaceGroup();
                                                function17.invoke($composer7, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }, $composer6, 54), $composer6, 438);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    $composer6.skipToGroupEnd();
                                }
                            }, $composer5, 54), $composer5, 384);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54), $composer4, 12582918, LocationRequestCompat.QUALITY_LOW_POWER);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer3, (($dirty2 >> 15) & 896) | ($dirty2 & 14) | 3072, 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape3 = shape4;
            tonalElevation3 = tonalElevation4;
            function6 = function7;
            properties2 = properties3;
            modifier3 = modifier4;
            colors3 = colors2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DatePickerDialog_androidKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DatePickerDialog_androidKt.DatePickerDialog_GmEhDVc$lambda$0(function0, function2, modifier3, function6, shape3, tonalElevation3, colors3, properties2, function4, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
