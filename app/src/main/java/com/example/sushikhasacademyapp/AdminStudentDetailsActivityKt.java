package com.example.sushikhasacademyapp;

import android.content.Context;
import android.widget.Toast;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.ExposedDropdownMenuBoxScope;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.ExposedDropdownMenuKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.cloudinary.metadata.MetadataField;
import com.cloudinary.provisioning.Account;
import com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AdminStudentDetailsActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003¢\u0006\u0002\u0010\u0006\u001a1\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u000bH\u0003¢\u0006\u0002\u0010\f¨\u0006\r²\u0006\u0010\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u008a\u008e\u0002²\u0006\f\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0017\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u0019\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u001a\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u001c\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"AdminStudentDetailsScreen", "", "batchTitle", "", "onBack", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "StudentInput", MetadataField.LABEL, "value", "onChange", "Lkotlin/Function1;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app", "students", "", "Lcom/example/sushikhasacademyapp/StudentUser;", "selected", "menuExpanded", "", HintConstants.AUTOFILL_HINT_NAME, "email", "dob", "studentClass", "school", "exam", "attended", "working", "isVerifiedState", "loadedStudentId"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AdminStudentDetailsActivityKt {
    static final Unit AdminStudentDetailsScreen$lambda$85(String str, Function0 function0, int i, Composer composer, int i2) {
        AdminStudentDetailsScreen(str, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit StudentInput$lambda$87(String str, String str2, Function1 function1, int i, Composer composer, int i2) {
        StudentInput(str, str2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AdminStudentDetailsScreen(final String str, final Function0<Unit> function0, Composer composer, final int i) {
        final String str2;
        Composer composer2;
        boolean z;
        Composer composerStartRestartGroup = composer.startRestartGroup(130300421);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AdminStudentDetailsScreen)N(batchTitle,onBack)46@1890L7,47@1918L59,48@1998L47,49@2070L34,51@2122L31,52@2171L31,53@2218L31,54@2274L31,55@2324L31,56@2372L31,57@2424L31,58@2475L31,59@2534L33,60@2595L31,62@2653L1914,62@2632L1935,97@4591L21,100@4645L359,107@5037L65,110@5109L5944,99@4618L6435:AdminStudentDetailsActivity.kt#t7vdsr");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= composerStartRestartGroup.changed(str) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if (!composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            str2 = str;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(130300421, i2, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsScreen (AdminStudentDetailsActivity.kt:45)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664659168, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                z = false;
                MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default);
                objRememberedValue = mutableStateMutableStateOf$default;
            } else {
                z = false;
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664661716, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                objRememberedValue2 = mutableStateMutableStateOf$default2;
            }
            final MutableState mutableState2 = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664664007, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                objRememberedValue3 = mutableStateMutableStateOf$default3;
            }
            final MutableState mutableState3 = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664665668, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                objRememberedValue4 = mutableStateMutableStateOf$default4;
            }
            final MutableState mutableState4 = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664667236, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default5);
                objRememberedValue5 = mutableStateMutableStateOf$default5;
            }
            final MutableState mutableState5 = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664668740, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default6);
                objRememberedValue6 = mutableStateMutableStateOf$default6;
            }
            final MutableState mutableState6 = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664670532, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default7);
                objRememberedValue7 = mutableStateMutableStateOf$default7;
            }
            final MutableState mutableState7 = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664672132, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default8);
                objRememberedValue8 = mutableStateMutableStateOf$default8;
            }
            final MutableState mutableState8 = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664673668, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default9);
                objRememberedValue9 = mutableStateMutableStateOf$default9;
            }
            final MutableState mutableState9 = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664675332, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default10);
                objRememberedValue10 = mutableStateMutableStateOf$default10;
            }
            final MutableState mutableState10 = (MutableState) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664676964, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default11 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default11);
                objRememberedValue11 = mutableStateMutableStateOf$default11;
            }
            final MutableState mutableState11 = (MutableState) objRememberedValue11;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664678854, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default12 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default12);
                objRememberedValue12 = mutableStateMutableStateOf$default12;
            }
            final MutableState mutableState12 = (MutableState) objRememberedValue12;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664680804, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                MutableState mutableStateMutableStateOf$default13 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default13);
                objRememberedValue13 = mutableStateMutableStateOf$default13;
            }
            final MutableState mutableState13 = (MutableState) objRememberedValue13;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 664684543, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue14 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1 adminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1 = new AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1(mutableState, mutableState2, mutableState13, mutableState4, mutableState5, mutableState7, mutableState8, mutableState6, mutableState9, mutableState10, mutableState11, mutableState12, null);
                composerStartRestartGroup.updateRememberedValue(adminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1);
                objRememberedValue14 = adminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue14, composerStartRestartGroup, 6);
            boolean z2 = z;
            final ScrollState scrollStateRememberScrollState = ScrollKt.rememberScrollState(z2 ? 1 : 0, composerStartRestartGroup, z2 ? 1 : 0, 1);
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-143756855, true, new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$41(scrollStateRememberScrollState, function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54);
            ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1469405708, true, new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$42(scrollStateRememberScrollState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54);
            Function3 function3 = new Function3() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84(scrollStateRememberScrollState, str, context, mutableState3, mutableState2, mutableState, mutableState13, mutableState4, mutableState5, mutableState7, mutableState8, mutableState6, mutableState9, mutableState10, mutableState11, mutableState12, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            };
            str2 = str;
            composer2 = composerStartRestartGroup;
            ScaffoldKt.m2765ScaffoldTvnljyQ(null, composableLambdaRememberComposableLambda, null, null, composableLambdaRememberComposableLambda2, 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(-99679020, true, function3, composer2, 54), composer2, 805330992, 493);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$85(str2, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<StudentUser> AdminStudentDetailsScreen$lambda$1(MutableState<List<StudentUser>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StudentUser AdminStudentDetailsScreen$lambda$4(MutableState<StudentUser> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean AdminStudentDetailsScreen$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AdminStudentDetailsScreen$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String AdminStudentDetailsScreen$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AdminStudentDetailsScreen$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AdminStudentDetailsScreen$lambda$16(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AdminStudentDetailsScreen$lambda$19(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AdminStudentDetailsScreen$lambda$22(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AdminStudentDetailsScreen$lambda$25(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AdminStudentDetailsScreen$lambda$28(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AdminStudentDetailsScreen$lambda$31(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AdminStudentDetailsScreen$lambda$35(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AdminStudentDetailsScreen$lambda$37(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit AdminStudentDetailsScreen$lambda$41(ScrollState $scrollState, final Function0 $onBack, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C104@4873L107,101@4659L335:AdminStudentDetailsActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-143756855, $changed, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsScreen.<anonymous> (AdminStudentDetailsActivity.kt:101)");
            }
            AppBarKt.m2090TopAppBarGHTll3U(ComposableSingletons$AdminStudentDetailsActivityKt.INSTANCE.m8616getLambda$1462756219$app(), MotionUtilsKt.m8744elevateOnScrollwH6b6FI$default(Modifier.INSTANCE, $scrollState, 0.0f, 2, null), ComposableLambdaKt.rememberComposableLambda(1484077507, true, new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$41$lambda$40($onBack, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), null, 0.0f, null, null, null, $composer, 390, 248);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$41$lambda$40(Function0 $onBack, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C104@4875L103:AdminStudentDetailsActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1484077507, $changed, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsScreen.<anonymous>.<anonymous> (AdminStudentDetailsActivity.kt:104)");
            }
            IconButtonKt.IconButton($onBack, null, false, null, null, null, ComposableSingletons$AdminStudentDetailsActivityKt.INSTANCE.getLambda$566898661$app(), $composer, 1572864, 62);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$42(ScrollState $scrollState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C108@5051L41:AdminStudentDetailsActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1469405708, $changed, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsScreen.<anonymous> (AdminStudentDetailsActivity.kt:108)");
            }
            MotionUtilsKt.ScrollToTopFAB($scrollState, (Modifier) null, 0, (CoroutineScope) null, $composer, 0, 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84(ScrollState $scrollState, String $batchTitle, final Context $context, MutableState $menuExpanded$delegate, final MutableState $selected$delegate, final MutableState $students$delegate, final MutableState $loadedStudentId$delegate, final MutableState $name$delegate, final MutableState $email$delegate, final MutableState $studentClass$delegate, final MutableState $school$delegate, final MutableState $dob$delegate, final MutableState $exam$delegate, final MutableState $attended$delegate, final MutableState $working$delegate, final MutableState $isVerifiedState$delegate, PaddingValues padding, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        final MutableState mutableState;
        final MutableState mutableState2;
        Intrinsics.checkNotNullParameter(padding, "padding");
        ComposerKt.sourceInformation($composer, "CN(padding)111@5130L5917:AdminStudentDetailsActivity.kt#t7vdsr");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(padding) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-99679020, $dirty, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsScreen.<anonymous> (AdminStudentDetailsActivity.kt:111)");
            }
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(PaddingKt.m819padding3ABfNKs(PaddingKt.padding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), padding), Dp.m7905constructorimpl(18)), $scrollState, false, null, false, 14, null);
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierVerticalScroll$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 347105311, "C119@5399L50,120@5581L10,120@5462L141,122@5684L32,122@5718L1421,122@5617L1522,144@7224L46,143@7180L1299,175@8574L2,174@8532L470,185@9051L13,185@9016L48,186@9106L14,186@9077L43,187@9168L12,187@9133L47,188@9247L21,188@9193L75,189@9312L15,189@9281L46,190@9376L13,190@9340L49,191@9442L39,191@9402L79,192@9538L38,192@9494L82,196@9668L1129,194@9590L1447:AdminStudentDetailsActivity.kt#t7vdsr");
            MotionUtilsKt.m8739ScrollProgressIndicatorpc5RIQQ($scrollState, null, 0L, 0L, 0.0f, $composer, 0, 30);
            TextKt.m3072TextNvy7gAk(StringsKt.isBlank($batchTitle) ? "Edit student profiles and attendance" : "Batch: " + $batchTitle, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 0, 0, 131070);
            boolean zAdminStudentDetailsScreen$lambda$7 = AdminStudentDetailsScreen$lambda$7($menuExpanded$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1512820194, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                mutableState = $menuExpanded$delegate;
                Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$44$lambda$43(mutableState, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            } else {
                mutableState = $menuExpanded$delegate;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            final MutableState mutableState3 = mutableState;
            ExposedDropdownMenuKt.ExposedDropdownMenuBox(zAdminStudentDetailsScreen$lambda$7, (Function1) objRememberedValue, null, ComposableLambdaKt.rememberComposableLambda(2128370888, true, new Function3() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58($selected$delegate, mutableState3, $students$delegate, $loadedStudentId$delegate, $name$delegate, $email$delegate, $studentClass$delegate, $school$delegate, $dob$delegate, $exam$delegate, $attended$delegate, $working$delegate, $isVerifiedState$delegate, (ExposedDropdownMenuBoxScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 3120, 4);
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(ColorKt.Color(4292672743L), 0L, 0L, 0L, $composer, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableSingletons$AdminStudentDetailsActivityKt.INSTANCE.m8619getLambda$2079452692$app(), $composer, 196614, 24);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart($composer, -1512727744, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.OutlinedButton((Function0) objRememberedValue2, modifierFillMaxWidth$default, false, roundedCornerShapeM1127RoundedCornerShape0680j_4, null, null, null, null, null, ComposableSingletons$AdminStudentDetailsActivityKt.INSTANCE.m8617getLambda$1744220692$app(), $composer, 805306806, 496);
            String strAdminStudentDetailsScreen$lambda$10 = AdminStudentDetailsScreen$lambda$10($name$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1512712469, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue3 = $composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$62$lambda$61($name$delegate, (String) obj4);
                    }
                };
                $composer.updateRememberedValue(obj3);
                objRememberedValue3 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            StudentInput("Student Name", strAdminStudentDetailsScreen$lambda$10, (Function1) objRememberedValue3, $composer, 390);
            String strAdminStudentDetailsScreen$lambda$13 = AdminStudentDetailsScreen$lambda$13($email$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1512710708, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue4 = $composer.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj5) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$64$lambda$63($email$delegate, (String) obj5);
                    }
                };
                $composer.updateRememberedValue(obj4);
                objRememberedValue4 = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            StudentInput("Email", strAdminStudentDetailsScreen$lambda$13, (Function1) objRememberedValue4, $composer, 390);
            String strAdminStudentDetailsScreen$lambda$16 = AdminStudentDetailsScreen$lambda$16($dob$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1512708726, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue5 = $composer.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object obj5 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj6) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$66$lambda$65($dob$delegate, (String) obj6);
                    }
                };
                $composer.updateRememberedValue(obj5);
                objRememberedValue5 = obj5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            StudentInput("Date of Birth", strAdminStudentDetailsScreen$lambda$16, (Function1) objRememberedValue5, $composer, 390);
            String strAdminStudentDetailsScreen$lambda$19 = AdminStudentDetailsScreen$lambda$19($studentClass$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1512706189, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue6 = $composer.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object obj6 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj7) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$68$lambda$67($studentClass$delegate, (String) obj7);
                    }
                };
                $composer.updateRememberedValue(obj6);
                objRememberedValue6 = obj6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            StudentInput("Class / Education Level", strAdminStudentDetailsScreen$lambda$19, (Function1) objRememberedValue6, $composer, 390);
            String strAdminStudentDetailsScreen$lambda$22 = AdminStudentDetailsScreen$lambda$22($school$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1512704115, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue7 = $composer.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object obj7 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj8) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$70$lambda$69($school$delegate, (String) obj8);
                    }
                };
                $composer.updateRememberedValue(obj7);
                objRememberedValue7 = obj7;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            StudentInput("School", strAdminStudentDetailsScreen$lambda$22, (Function1) objRememberedValue7, $composer, 390);
            String strAdminStudentDetailsScreen$lambda$25 = AdminStudentDetailsScreen$lambda$25($exam$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1512702069, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue8 = $composer.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                Object obj8 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj9) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$72$lambda$71($exam$delegate, (String) obj9);
                    }
                };
                $composer.updateRememberedValue(obj8);
                objRememberedValue8 = obj8;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            StudentInput("Exam / Course", strAdminStudentDetailsScreen$lambda$25, (Function1) objRememberedValue8, $composer, 390);
            String strAdminStudentDetailsScreen$lambda$28 = AdminStudentDetailsScreen$lambda$28($attended$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1512699931, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue9 = $composer.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                Object obj9 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj10) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$74$lambda$73($attended$delegate, (String) obj10);
                    }
                };
                $composer.updateRememberedValue(obj9);
                objRememberedValue9 = obj9;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            StudentInput("Attended Days", strAdminStudentDetailsScreen$lambda$28, (Function1) objRememberedValue9, $composer, 390);
            String strAdminStudentDetailsScreen$lambda$31 = AdminStudentDetailsScreen$lambda$31($working$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1512696860, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue10 = $composer.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                mutableState2 = $working$delegate;
                Object obj10 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj11) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$77$lambda$76(mutableState2, (String) obj11);
                    }
                };
                $composer.updateRememberedValue(obj10);
                objRememberedValue10 = obj10;
            } else {
                mutableState2 = $working$delegate;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            StudentInput("Total Working Days", strAdminStudentDetailsScreen$lambda$31, (Function1) objRememberedValue10, $composer, 390);
            boolean z = AdminStudentDetailsScreen$lambda$4($selected$delegate) != null;
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1512691609, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($context);
            Object objRememberedValue11 = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                final MutableState mutableState4 = mutableState2;
                Object obj11 = new Function0() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$82$lambda$81($context, $selected$delegate, $attended$delegate, mutableState4, $name$delegate, $email$delegate, $dob$delegate, $studentClass$delegate, $school$delegate, $exam$delegate);
                    }
                };
                $composer.updateRememberedValue(obj11);
                objRememberedValue11 = obj11;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue11, modifierFillMaxWidth$default2, z, null, null, null, null, null, null, ComposableSingletons$AdminStudentDetailsActivityKt.INSTANCE.m8615getLambda$1114801618$app(), $composer, 805306416, TypedValues.PositionType.TYPE_PERCENT_HEIGHT);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$44$lambda$43(MutableState $menuExpanded$delegate, boolean it) {
        AdminStudentDetailsScreen$lambda$8($menuExpanded$delegate, !AdminStudentDetailsScreen$lambda$7($menuExpanded$delegate));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0079  */
    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58(final MutableState $selected$delegate, final MutableState $menuExpanded$delegate, final MutableState $students$delegate, final MutableState $loadedStudentId$delegate, final MutableState $name$delegate, final MutableState $email$delegate, final MutableState $studentClass$delegate, final MutableState $school$delegate, final MutableState $dob$delegate, final MutableState $exam$delegate, final MutableState $attended$delegate, final MutableState $working$delegate, final MutableState $isVerifiedState$delegate, ExposedDropdownMenuBoxScope ExposedDropdownMenuBox, Composer $composer, int $changed) {
        String str;
        Intrinsics.checkNotNullParameter(ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
        ComposerKt.sourceInformation($composer, "C123@5851L2,124@5943L58,123@5736L335,126@6152L24,126@6178L947,126@6088L1037:AdminStudentDetailsActivity.kt#t7vdsr");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer.changed(ExposedDropdownMenuBox) : $composer.changedInstance(ExposedDropdownMenuBox) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2128370888, $dirty, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsScreen.<anonymous>.<anonymous>.<anonymous> (AdminStudentDetailsActivity.kt:123)");
            }
            StudentUser studentUserAdminStudentDetailsScreen$lambda$4 = AdminStudentDetailsScreen$lambda$4($selected$delegate);
            if (studentUserAdminStudentDetailsScreen$lambda$4 != null) {
                str = studentUserAdminStudentDetailsScreen$lambda$4.getUsername() + " (" + studentUserAdminStudentDetailsScreen$lambda$4.getEmail() + ")";
                if (str == null) {
                    str = "No students found";
                }
            } else {
                str = "No students found";
            }
            String str2 = str;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(ExposedDropdownMenuBox.menuAnchor(Modifier.INSTANCE), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 959994730, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$47$lambda$46((String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(str2, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminStudentDetailsActivityKt.INSTANCE.m8618getLambda$1977303570$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1307175729, true, new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$48($menuExpanded$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 806903856, 0, 0, 8388008);
            boolean zAdminStudentDetailsScreen$lambda$7 = AdminStudentDetailsScreen$lambda$7($menuExpanded$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 960004384, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$50$lambda$49($menuExpanded$delegate);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ExposedDropdownMenuBox.m2426ExposedDropdownMenuvNxi1II(zAdminStudentDetailsScreen$lambda$7, (Function0) objRememberedValue2, null, null, false, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-86538806, true, new Function3() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$57($students$delegate, $selected$delegate, $loadedStudentId$delegate, $name$delegate, $email$delegate, $studentClass$delegate, $school$delegate, $dob$delegate, $exam$delegate, $attended$delegate, $working$delegate, $menuExpanded$delegate, $isVerifiedState$delegate, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, $composer, 54), $composer, 48, (ExposedDropdownMenuBoxScope.$stable << 3) | 6 | (($dirty << 3) & 112), 1020);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$47$lambda$46(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$48(MutableState $menuExpanded$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C124@5973L26:AdminStudentDetailsActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1307175729, $changed, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminStudentDetailsActivity.kt:124)");
            }
            ExposedDropdownMenuDefaults.INSTANCE.TrailingIcon(AdminStudentDetailsScreen$lambda$7($menuExpanded$delegate), null, $composer, ExposedDropdownMenuDefaults.$stable << 6, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$50$lambda$49(MutableState $menuExpanded$delegate) {
        AdminStudentDetailsScreen$lambda$8($menuExpanded$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$57(MutableState $students$delegate, final MutableState $selected$delegate, final MutableState $loadedStudentId$delegate, final MutableState $name$delegate, final MutableState $email$delegate, final MutableState $studentClass$delegate, final MutableState $school$delegate, final MutableState $dob$delegate, final MutableState $exam$delegate, final MutableState $attended$delegate, final MutableState $working$delegate, final MutableState $menuExpanded$delegate, final MutableState $isVerifiedState$delegate, ColumnScope ExposedDropdownMenu, Composer $composer, int $changed) {
        Composer composer = $composer;
        Intrinsics.checkNotNullParameter(ExposedDropdownMenu, "$this$ExposedDropdownMenu");
        ComposerKt.sourceInformation(composer, "C*127@6254L50,127@6316L788,127@6230L875:AdminStudentDetailsActivity.kt#t7vdsr");
        if (composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-86538806, $changed, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminStudentDetailsActivity.kt:127)");
            }
            for (final StudentUser studentUser : AdminStudentDetailsScreen$lambda$1($students$delegate)) {
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-207735157, true, new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$57$lambda$56$lambda$51(studentUser, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer, 54);
                ComposerKt.sourceInformationMarkerStart(composer, -1754585841, "CC(remember):AdminStudentDetailsActivity.kt#9igjgp");
                boolean zChanged = composer.changed(studentUser);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda24
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$57$lambda$56$lambda$55$lambda$54(studentUser, $selected$delegate, $loadedStudentId$delegate, $name$delegate, $email$delegate, $studentClass$delegate, $school$delegate, $dob$delegate, $exam$delegate, $attended$delegate, $working$delegate, $menuExpanded$delegate, $isVerifiedState$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                AndroidMenu_androidKt.DropdownMenuItem(composableLambdaRememberComposableLambda, (Function0) objRememberedValue, null, null, null, false, null, null, null, composer, 6, TypedValues.PositionType.TYPE_CURVE_FIT);
                composer = $composer;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$57$lambda$56$lambda$51(StudentUser $student, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C127@6256L46:AdminStudentDetailsActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-207735157, $changed, -1, "com.example.sushikhasacademyapp.AdminStudentDetailsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminStudentDetailsActivity.kt:127)");
            }
            TextKt.m3072TextNvy7gAk($student.getUsername() + " (" + $student.getEmail() + ")", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$57$lambda$56$lambda$55$lambda$54(StudentUser $student, MutableState $selected$delegate, MutableState $loadedStudentId$delegate, MutableState $name$delegate, MutableState $email$delegate, MutableState $studentClass$delegate, MutableState $school$delegate, MutableState $dob$delegate, MutableState $exam$delegate, MutableState $attended$delegate, MutableState $working$delegate, MutableState $menuExpanded$delegate, final MutableState $isVerifiedState$delegate) {
        $selected$delegate.setValue($student);
        $loadedStudentId$delegate.setValue($student.getId());
        $name$delegate.setValue($student.getUsername());
        $email$delegate.setValue($student.getEmail());
        $studentClass$delegate.setValue($student.getClassLevel());
        $school$delegate.setValue($student.getSchool());
        $dob$delegate.setValue($student.getDob());
        $exam$delegate.setValue($student.getExam());
        $attended$delegate.setValue(String.valueOf($student.getAttendedDays()));
        $working$delegate.setValue(String.valueOf($student.getTotalWorkingDays()));
        AdminStudentDetailsScreen$lambda$8($menuExpanded$delegate, false);
        Task<DocumentSnapshot> task = FirebaseFirestore.getInstance().collection(Account.USERS).document($student.getId()).get();
        final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$57$lambda$56$lambda$55$lambda$54$lambda$52($isVerifiedState$delegate, (DocumentSnapshot) obj);
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda26
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                function1.invoke(obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$58$lambda$57$lambda$56$lambda$55$lambda$54$lambda$52(MutableState $isVerifiedState$delegate, DocumentSnapshot doc) {
        if (doc != null && doc.exists()) {
            Boolean bool = doc.getBoolean("isVerified");
            AdminStudentDetailsScreen$lambda$35($isVerifiedState$delegate, bool != null ? bool.booleanValue() : true);
        }
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$62$lambda$61(MutableState $name$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $name$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$64$lambda$63(MutableState $email$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $email$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$66$lambda$65(MutableState $dob$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $dob$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$68$lambda$67(MutableState $studentClass$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $studentClass$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$70$lambda$69(MutableState $school$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $school$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$72$lambda$71(MutableState $exam$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $exam$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$74$lambda$73(MutableState $attended$delegate, String it) throws IOException {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = it;
        Appendable sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        $attended$delegate.setValue(((StringBuilder) sb).toString());
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$77$lambda$76(MutableState $working$delegate, String it) throws IOException {
        Intrinsics.checkNotNullParameter(it, "it");
        String str = it;
        Appendable sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        $working$delegate.setValue(((StringBuilder) sb).toString());
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$82$lambda$81(final Context $context, MutableState $selected$delegate, MutableState $attended$delegate, MutableState $working$delegate, MutableState $name$delegate, MutableState $email$delegate, MutableState $dob$delegate, MutableState $studentClass$delegate, MutableState $school$delegate, MutableState $exam$delegate) {
        StudentUser student = AdminStudentDetailsScreen$lambda$4($selected$delegate);
        if (student == null) {
            return Unit.INSTANCE;
        }
        Long longOrNull = StringsKt.toLongOrNull(AdminStudentDetailsScreen$lambda$28($attended$delegate));
        long attendance = longOrNull != null ? longOrNull.longValue() : 0L;
        Long longOrNull2 = StringsKt.toLongOrNull(AdminStudentDetailsScreen$lambda$31($working$delegate));
        long days = longOrNull2 != null ? longOrNull2.longValue() : 0L;
        if (attendance > days) {
            Toast.makeText($context, "Attended days cannot exceed working days", 0).show();
            return Unit.INSTANCE;
        }
        Task<Void> task = FirebaseFirestore.getInstance().collection(Account.USERS).document(student.getId()).set(MapsKt.mapOf(TuplesKt.to(HintConstants.AUTOFILL_HINT_USERNAME, StringsKt.trim((CharSequence) AdminStudentDetailsScreen$lambda$10($name$delegate)).toString()), TuplesKt.to("email", StringsKt.trim((CharSequence) AdminStudentDetailsScreen$lambda$13($email$delegate)).toString()), TuplesKt.to("dob", StringsKt.trim((CharSequence) AdminStudentDetailsScreen$lambda$16($dob$delegate)).toString()), TuplesKt.to("classLevel", StringsKt.trim((CharSequence) AdminStudentDetailsScreen$lambda$19($studentClass$delegate)).toString()), TuplesKt.to("school", StringsKt.trim((CharSequence) AdminStudentDetailsScreen$lambda$22($school$delegate)).toString()), TuplesKt.to("exam", StringsKt.trim((CharSequence) AdminStudentDetailsScreen$lambda$25($exam$delegate)).toString()), TuplesKt.to("attendedDays", Long.valueOf(attendance)), TuplesKt.to("totalWorkingDays", Long.valueOf(days))), SetOptions.merge());
        final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$82$lambda$81$lambda$78($context, (Void) obj);
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda21
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                function1.invoke(obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda22
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$82$lambda$81$lambda$80($context, exc);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$82$lambda$81$lambda$78(Context $context, Void it) {
        Toast.makeText($context, "Student details saved", 0).show();
        return Unit.INSTANCE;
    }

    static final void AdminStudentDetailsScreen$lambda$84$lambda$83$lambda$82$lambda$81$lambda$80(Context $context, Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Toast.makeText($context, "Save failed: " + e.getLocalizedMessage(), 1).show();
    }

    private static final void StudentInput(final String label, final String value, final Function1<? super String, Unit> function1, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1539529224);
        ComposerKt.sourceInformation($composer3, "C(StudentInput)N(label,value,onChange)224@11201L15,224@11158L114:AdminStudentDetailsActivity.kt#t7vdsr");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(label) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        if (!$composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1539529224, $dirty, -1, "com.example.sushikhasacademyapp.StudentInput (AdminStudentDetailsActivity.kt:223)");
            }
            $composer2 = $composer3;
            OutlinedTextFieldKt.OutlinedTextField(value, function1, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-2026780382, true, new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminStudentDetailsActivityKt.StudentInput$lambda$86(label, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer2, (($dirty >> 3) & 14) | 1573248 | (($dirty >> 3) & 112), 12582912, 0, 8257464);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminStudentDetailsActivityKt.StudentInput$lambda$87(label, value, function1, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit StudentInput$lambda$86(String $label, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C224@11203L11:AdminStudentDetailsActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2026780382, $changed, -1, "com.example.sushikhasacademyapp.StudentInput.<anonymous> (AdminStudentDetailsActivity.kt:224)");
            }
            TextKt.m3072TextNvy7gAk($label, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
