package com.example.sushikhasacademyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.autofill.HintConstants;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.PersonAddKt;
import androidx.compose.material.icons.filled.ScheduleKt;
import androidx.compose.material.icons.filled.SchoolKt;
import androidx.compose.material3.AlertDialogKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.CheckboxKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.ExposedDropdownMenuBoxScope;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.ExposedDropdownMenuKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TabKt;
import androidx.compose.material3.TabRowKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.DialogProperties;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.location.LocationRequestCompat;
import androidx.media3.common.C;
import com.cloudinary.provisioning.Account;
import com.example.sushikhasacademyapp.BatchDetailActivity;
import com.example.sushikhasacademyapp.data.BatchSchedule;
import com.example.sushikhasacademyapp.data.ContactInfo;
import com.example.sushikhasacademyapp.data.CourseInfo;
import com.example.sushikhasacademyapp.data.SqlConnectRepository;
import com.example.sushikhasacademyapp.data.TeacherProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.logging.type.LogSeverity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: AdminDashboardDialog.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\u001a)\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001aO\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0012\u001a+\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\n2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0016\u001a#\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007¢\u0006\u0002\u0010\u0018\u001a#\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0007¢\u0006\u0002\u0010\u0018\u001a+\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007¢\u0006\u0002\u0010\u001c\u001a)\u0010\u001d\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0007¢\u0006\u0002\u0010\u001e¨\u0006\u001f²\u0006\n\u0010 \u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010\"\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010#\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010$\u001a\u00020%X\u008a\u008e\u0002²\u0006\n\u0010&\u001a\u00020'X\u008a\u008e\u0002²\u0006\n\u0010(\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010)\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010*\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010+\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010,\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010-\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010.\u001a\u00020%X\u008a\u008e\u0002²\u0006\f\u0010/\u001a\u0004\u0018\u00010\rX\u008a\u008e\u0002²\u0006\n\u0010)\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u00100\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u00101\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u00102\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u00103\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u00104\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u00105\u001a\u00020%X\u008a\u008e\u0002²\u0006\f\u00106\u001a\u0004\u0018\u00010\u000fX\u008a\u008e\u0002²\u0006\n\u00107\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u00108\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u00109\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010:\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u00102\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010;\u001a\u00020%X\u008a\u008e\u0002²\u0006\n\u0010<\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010=\u001a\u00020!X\u008a\u008e\u0002²\u0006\n\u0010>\u001a\u00020!X\u008a\u008e\u0002²\u0006\u0010\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\fX\u008a\u008e\u0002²\u0006\n\u0010A\u001a\u00020%X\u008a\u008e\u0002²\u0006\f\u0010B\u001a\u0004\u0018\u00010@X\u008a\u008e\u0002²\u0006\n\u0010C\u001a\u00020%X\u008a\u008e\u0002²\u0006\u0010\u0010D\u001a\b\u0012\u0004\u0012\u00020!0EX\u008a\u008e\u0002²\u0006\u0010\u0010F\u001a\b\u0012\u0004\u0012\u00020!0EX\u008a\u008e\u0002"}, d2 = {"AdminLoginDialog", "", "onDismiss", "Lkotlin/Function0;", "onLoginSuccess", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "AdminDashboardDialog", "repository", "Lcom/example/sushikhasacademyapp/data/SqlConnectRepository;", "teacherProfile", "Lcom/example/sushikhasacademyapp/data/TeacherProfile;", "courses", "", "Lcom/example/sushikhasacademyapp/data/CourseInfo;", "batches", "Lcom/example/sushikhasacademyapp/data/BatchSchedule;", "contactInfo", "Lcom/example/sushikhasacademyapp/data/ContactInfo;", "(Lcom/example/sushikhasacademyapp/data/SqlConnectRepository;Lcom/example/sushikhasacademyapp/data/TeacherProfile;Ljava/util/List;Ljava/util/List;Lcom/example/sushikhasacademyapp/data/ContactInfo;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "TeacherProfileAdminForm", "currentProfile", "onSaved", "(Lcom/example/sushikhasacademyapp/data/SqlConnectRepository;Lcom/example/sushikhasacademyapp/data/TeacherProfile;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "CoursesAdminForm", "(Lcom/example/sushikhasacademyapp/data/SqlConnectRepository;Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "BatchesAdminForm", "ContactInfoAdminForm", "currentContact", "(Lcom/example/sushikhasacademyapp/data/SqlConnectRepository;Lcom/example/sushikhasacademyapp/data/ContactInfo;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "StudentEnrollmentAdminForm", "(Ljava/util/List;Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "app", "emailInput", "", "password", "errorMessage", "isLoading", "", "selectedTab", "", HintConstants.AUTOFILL_HINT_NAME, "title", "credentials", "studentsCount", "dpUrl", "bio", "showAddCourseDialog", "editingCourse", "description", TypedValues.TransitionType.S_DURATION, "mode", "level", "fee", "showAddBatchDialog", "editingBatch", "courseTitle", "timing", "days", "seatsLeft", "isOnline", "address", "phone", "email", "studentsList", "Lcom/example/sushikhasacademyapp/ui/FirestoreStudent;", "isLoadingStudents", "selectedStudent", "expandedStudentDropdown", "selectedCourses", "", "selectedBatches"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AdminDashboardDialogKt {
    static final Unit AdminDashboardDialog$lambda$63(SqlConnectRepository sqlConnectRepository, TeacherProfile teacherProfile, List list, List list2, ContactInfo contactInfo, Function0 function0, int i, Composer composer, int i2) {
        AdminDashboardDialog(sqlConnectRepository, teacherProfile, list, list2, contactInfo, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit AdminLoginDialog$lambda$32(Function0 function0, Function0 function1, int i, Composer composer, int i2) {
        AdminLoginDialog(function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit BatchesAdminForm$lambda$229(SqlConnectRepository sqlConnectRepository, List list, int i, Composer composer, int i2) {
        BatchesAdminForm(sqlConnectRepository, list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ContactInfoAdminForm$lambda$249(SqlConnectRepository sqlConnectRepository, ContactInfo contactInfo, Function0 function0, int i, Composer composer, int i2) {
        ContactInfoAdminForm(sqlConnectRepository, contactInfo, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit CoursesAdminForm$lambda$162(SqlConnectRepository sqlConnectRepository, List list, int i, Composer composer, int i2) {
        CoursesAdminForm(sqlConnectRepository, list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit StudentEnrollmentAdminForm$lambda$330(List list, List list2, int i, Composer composer, int i2) {
        StudentEnrollmentAdminForm(list, list2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TeacherProfileAdminForm$lambda$99(SqlConnectRepository sqlConnectRepository, TeacherProfile teacherProfile, Function0 function0, int i, Composer composer, int i2) {
        TeacherProfileAdminForm(sqlConnectRepository, teacherProfile, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void AdminLoginDialog(final Function0<Unit> onDismiss, final Function0<Unit> onLoginSuccess, Composer $composer, final int $changed) {
        Composer $composer2;
        Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
        Intrinsics.checkNotNullParameter(onLoginSuccess, "onLoginSuccess");
        Composer $composer3 = $composer.startRestartGroup(1199002652);
        ComposerKt.sourceInformation($composer3, "C(AdminLoginDialog)N(onDismiss,onLoginSuccess)44@1509L31,45@1561L31,46@1617L31,47@1670L34,100@3987L3722,165@7735L102,58@2183L1778,49@1710L6133:AdminDashboardDialog.kt#untpz1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(onDismiss) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onLoginSuccess) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1199002652, $dirty2, -1, "com.example.sushikhasacademyapp.ui.AdminLoginDialog (AdminDashboardDialog.kt:43)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 1190317051, "CC(remember):AdminDashboardDialog.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState emailInput$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1190318715, "CC(remember):AdminDashboardDialog.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue2 = objMutableStateOf$default2;
            }
            final MutableState password$delegate = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1190320507, "CC(remember):AdminDashboardDialog.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default3);
                objRememberedValue3 = objMutableStateOf$default3;
            }
            final MutableState errorMessage$delegate = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1190322206, "CC(remember):AdminDashboardDialog.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default4);
                objRememberedValue4 = objMutableStateOf$default4;
            }
            final MutableState isLoading$delegate = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.m2066AlertDialogOix01E0(onDismiss, ComposableLambdaKt.rememberComposableLambda(-1815043884, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda89
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminDashboardDialogKt.AdminLoginDialog$lambda$23(onLoginSuccess, isLoading$delegate, emailInput$delegate, password$delegate, errorMessage$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(-1526224494, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda90
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminDashboardDialogKt.AdminLoginDialog$lambda$24(onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8710getLambda$1237405104$app(), ComposableLambdaKt.rememberComposableLambda(-1092995409, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda91
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminDashboardDialogKt.AdminLoginDialog$lambda$31(emailInput$delegate, password$delegate, errorMessage$delegate, isLoading$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer2, ($dirty2 & 14) | 1772592, 0, 16276);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda92
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AdminDashboardDialogKt.AdminLoginDialog$lambda$32(onDismiss, onLoginSuccess, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String AdminLoginDialog$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AdminLoginDialog$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String AdminLoginDialog$lambda$7(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean AdminLoginDialog$lambda$10(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void AdminLoginDialog$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit AdminLoginDialog$lambda$31(final MutableState $emailInput$delegate, final MutableState $password$delegate, MutableState $errorMessage$delegate, MutableState $isLoading$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Object obj;
        Function0<ComposeUiNode> function1;
        ComposerKt.sourceInformation($composer, "C59@2197L1754:AdminDashboardDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1092995409, $changed, -1, "com.example.sushikhasacademyapp.ui.AdminLoginDialog.<anonymous> (AdminDashboardDialog.kt:59)");
            }
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
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
            ComposerKt.sourceInformationMarkerStart($composer, -204756566, "C62@2446L10,63@2510L11,60@2273L283,68@2669L19,66@2574L280,76@2965L17,74@2872L348:AdminDashboardDialog.kt#untpz1");
            TextKt.m3072TextNvy7gAk("Authenticate with your administrator email and password to access teacher management controls.", null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 6, 0, 131066);
            String strAdminLoginDialog$lambda$1 = AdminLoginDialog$lambda$1($emailInput$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -422236020, "CC(remember):AdminDashboardDialog.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda47
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return AdminDashboardDialogKt.AdminLoginDialog$lambda$31$lambda$30$lambda$26$lambda$25($emailInput$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(strAdminLoginDialog$lambda$1, (Function1<? super String, Unit>) obj, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1248331295$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 12582912, 0, 8257464);
            String strAdminLoginDialog$lambda$4 = AdminLoginDialog$lambda$4($password$delegate);
            PasswordVisualTransformation passwordVisualTransformation = new PasswordVisualTransformation((char) 0, 1, null);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -422226550, "CC(remember):AdminDashboardDialog.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda48
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return AdminDashboardDialogKt.AdminLoginDialog$lambda$31$lambda$30$lambda$28$lambda$27($password$delegate, (String) obj3);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd(
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0255: INVOKE (r2v19 ?? I:??[OBJECT, ARRAY]) STATIC call: androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(androidx.compose.runtime.Composer):void A[MD:(androidx.compose.runtime.Composer):void (m)] in method: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt.AdminLoginDialog$lambda$31(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit, file: classes6.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
                	at java.base/java.util.ArrayList.forEach(Unknown Source)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r2v19 ??
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
                */
            /*
                Method dump skipped, instruction units count: 1262
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt.AdminLoginDialog$lambda$31(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int):kotlin.Unit");
        }

        static final Unit AdminLoginDialog$lambda$31$lambda$30$lambda$26$lambda$25(MutableState $emailInput$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $emailInput$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit AdminLoginDialog$lambda$31$lambda$30$lambda$28$lambda$27(MutableState $password$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $password$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit AdminLoginDialog$lambda$23(final Function0 $onLoginSuccess, final MutableState $isLoading$delegate, final MutableState $emailInput$delegate, final MutableState $password$delegate, final MutableState $errorMessage$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C103@4073L3557,101@4001L3698:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1815043884, $changed, -1, "com.example.sushikhasacademyapp.ui.AdminLoginDialog.<anonymous> (AdminDashboardDialog.kt:101)");
                }
                boolean zAdminLoginDialog$lambda$10 = true ^ AdminLoginDialog$lambda$10($isLoading$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, -500444039, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                boolean zChanged = $composer.changed($onLoginSuccess);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda78
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.AdminLoginDialog$lambda$23$lambda$22$lambda$21($emailInput$delegate, $password$delegate, $errorMessage$delegate, $isLoading$delegate, $onLoginSuccess);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.Button((Function0) objRememberedValue, null, zAdminLoginDialog$lambda$10, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$2001932004$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_PERCENT_X);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit AdminLoginDialog$lambda$23$lambda$22$lambda$21(MutableState $emailInput$delegate, MutableState $password$delegate, final MutableState $errorMessage$delegate, final MutableState $isLoading$delegate, final Function0 $onLoginSuccess) {
            final String cleanEmail = StringsKt.trim((CharSequence) AdminLoginDialog$lambda$1($emailInput$delegate)).toString().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(cleanEmail, "toLowerCase(...)");
            String cleanPass = StringsKt.trim((CharSequence) AdminLoginDialog$lambda$4($password$delegate)).toString();
            if (!StringsKt.isBlank(cleanEmail) && !StringsKt.isBlank(cleanPass)) {
                AdminLoginDialog$lambda$11($isLoading$delegate, true);
                $errorMessage$delegate.setValue("");
                Task<AuthResult> taskSignInWithEmailAndPassword = FirebaseAuth.getInstance().signInWithEmailAndPassword(cleanEmail, cleanPass);
                final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda49
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AdminDashboardDialogKt.AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18(cleanEmail, $onLoginSuccess, $isLoading$delegate, $errorMessage$delegate, (AuthResult) obj);
                    }
                };
                taskSignInWithEmailAndPassword.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda50
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        function1.invoke(obj);
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda51
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        AdminDashboardDialogKt.AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$20($isLoading$delegate, $errorMessage$delegate, exc);
                    }
                });
                return Unit.INSTANCE;
            }
            $errorMessage$delegate.setValue("Please enter both admin email and password.");
            return Unit.INSTANCE;
        }

        static final Unit AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18(final String $cleanEmail, final Function0 $onLoginSuccess, final MutableState $isLoading$delegate, final MutableState $errorMessage$delegate, AuthResult authResult) {
            final FirebaseUser user = authResult.getUser();
            if (user == null) {
                AdminLoginDialog$lambda$11($isLoading$delegate, false);
                $errorMessage$delegate.setValue("Invalid administrator credentials.");
            } else {
                Task<GetTokenResult> idToken = user.getIdToken(false);
                final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AdminDashboardDialogKt.AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18$lambda$15(user, $cleanEmail, $onLoginSuccess, $isLoading$delegate, $errorMessage$delegate, (GetTokenResult) obj);
                    }
                };
                idToken.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda10
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        function1.invoke(obj);
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda11
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        AdminDashboardDialogKt.AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18$lambda$17($isLoading$delegate, $errorMessage$delegate, exc);
                    }
                });
            }
            return Unit.INSTANCE;
        }

        static final Unit AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18$lambda$15(FirebaseUser $user, final String $cleanEmail, final Function0 $onLoginSuccess, final MutableState $isLoading$delegate, final MutableState $errorMessage$delegate, GetTokenResult tokenResult) {
            boolean z = true;
            if (!Intrinsics.areEqual(tokenResult.getClaims().get("admin"), (Object) true) && !Intrinsics.areEqual(tokenResult.getClaims().get("admin"), "true")) {
                z = false;
            }
            final boolean isAdminClaim = z;
            Task<DocumentSnapshot> task = FirebaseFirestore.getInstance().collection(Account.USERS).document($user.getUid()).get();
            final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AdminDashboardDialogKt.AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18$lambda$15$lambda$12(isAdminClaim, $cleanEmail, $onLoginSuccess, $isLoading$delegate, $errorMessage$delegate, (DocumentSnapshot) obj);
                }
            };
            task.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda19
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    function1.invoke(obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda20
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    AdminDashboardDialogKt.AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18$lambda$15$lambda$14(isAdminClaim, $cleanEmail, $onLoginSuccess, $isLoading$delegate, $errorMessage$delegate, exc);
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18$lambda$15$lambda$12(boolean $isAdminClaim, String $cleanEmail, Function0 $onLoginSuccess, MutableState $isLoading$delegate, MutableState $errorMessage$delegate, DocumentSnapshot userDoc) {
            boolean finalIsAdmin = true;
            boolean isDocAdmin = Intrinsics.areEqual((Object) userDoc.getBoolean("isAdmin"), (Object) true) || Intrinsics.areEqual(userDoc.getString("role"), "admin");
            if (!$isAdminClaim && !isDocAdmin && !Intrinsics.areEqual($cleanEmail, "sushiksha.konnagar@gmail.com")) {
                finalIsAdmin = false;
            }
            if (finalIsAdmin) {
                AdminLoginDialog$lambda$11($isLoading$delegate, false);
                $errorMessage$delegate.setValue("");
                $onLoginSuccess.invoke();
            } else {
                AdminLoginDialog$lambda$11($isLoading$delegate, false);
                $errorMessage$delegate.setValue("Access Denied: Account lacks administrator privileges.");
            }
            return Unit.INSTANCE;
        }

        static final void AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18$lambda$15$lambda$14(boolean $isAdminClaim, String $cleanEmail, Function0 $onLoginSuccess, MutableState $isLoading$delegate, MutableState $errorMessage$delegate, Exception it) {
            Intrinsics.checkNotNullParameter(it, "it");
            if ($isAdminClaim || Intrinsics.areEqual($cleanEmail, "admin@sushiksha.com")) {
                AdminLoginDialog$lambda$11($isLoading$delegate, false);
                $onLoginSuccess.invoke();
            } else {
                AdminLoginDialog$lambda$11($isLoading$delegate, false);
                $errorMessage$delegate.setValue("Access Denied: Account lacks administrator privileges.");
            }
        }

        static final void AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$18$lambda$17(MutableState $isLoading$delegate, MutableState $errorMessage$delegate, Exception it) {
            Intrinsics.checkNotNullParameter(it, "it");
            AdminLoginDialog$lambda$11($isLoading$delegate, false);
            $errorMessage$delegate.setValue("Failed to verify admin token.");
        }

        static final void AdminLoginDialog$lambda$23$lambda$22$lambda$21$lambda$20(MutableState $isLoading$delegate, MutableState $errorMessage$delegate, Exception it) {
            Intrinsics.checkNotNullParameter(it, "it");
            AdminLoginDialog$lambda$11($isLoading$delegate, false);
            $errorMessage$delegate.setValue("Invalid admin email or password.");
        }

        static final Unit AdminLoginDialog$lambda$24(Function0 $onDismiss, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C166@7749L78:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1526224494, $changed, -1, "com.example.sushikhasacademyapp.ui.AdminLoginDialog.<anonymous> (AdminDashboardDialog.kt:166)");
                }
                ButtonKt.TextButton($onDismiss, null, false, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1917966581$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        public static final void AdminDashboardDialog(final SqlConnectRepository repository, final TeacherProfile teacherProfile, final List<CourseInfo> courses, final List<BatchSchedule> batches, final ContactInfo contactInfo, final Function0<Unit> onDismiss, Composer $composer, final int $changed) {
            Intrinsics.checkNotNullParameter(repository, "repository");
            Intrinsics.checkNotNullParameter(teacherProfile, "teacherProfile");
            Intrinsics.checkNotNullParameter(courses, "courses");
            Intrinsics.checkNotNullParameter(batches, "batches");
            Intrinsics.checkNotNullParameter(contactInfo, "contactInfo");
            Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
            Composer $composer2 = $composer.startRestartGroup(-1210846566);
            ComposerKt.sourceInformation($composer2, "C(AdminDashboardDialog)N(repository,teacherProfile,courses,batches,contactInfo,onDismiss)183@8155L7,184@8186L33,193@8509L4219,186@8225L4503:AdminDashboardDialog.kt#untpz1");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer2.changed(repository) : $composer2.changedInstance(repository) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= ($changed & 64) == 0 ? $composer2.changed(teacherProfile) : $composer2.changedInstance(teacherProfile) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                $dirty |= $composer2.changedInstance(courses) ? 256 : 128;
            }
            if (($changed & 3072) == 0) {
                $dirty |= $composer2.changedInstance(batches) ? 2048 : 1024;
            }
            if (($changed & 24576) == 0) {
                $dirty |= (32768 & $changed) == 0 ? $composer2.changed(contactInfo) : $composer2.changedInstance(contactInfo) ? 16384 : 8192;
            }
            if ((196608 & $changed) == 0) {
                $dirty |= $composer2.changedInstance(onDismiss) ? 131072 : 65536;
            }
            int $dirty2 = $dirty;
            if (!$composer2.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1210846566, $dirty2, -1, "com.example.sushikhasacademyapp.ui.AdminDashboardDialog (AdminDashboardDialog.kt:182)");
                }
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer2.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final Context context = (Context) objConsume;
                ComposerKt.sourceInformationMarkerStart($composer2, 312598171, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer2.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableIntStateOf = SnapshotIntStateKt.mutableIntStateOf(0);
                    $composer2.updateRememberedValue(objMutableIntStateOf);
                    objRememberedValue = objMutableIntStateOf;
                }
                final MutableIntState selectedTab$delegate = (MutableIntState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                AlertDialogKt.AlertDialog(onDismiss, PaddingKt.m821paddingVpY3zN4$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.9f), 0.0f, Dp.m7905constructorimpl(12), 1, null), new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(56727824, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62(onDismiss, selectedTab$delegate, repository, teacherProfile, context, courses, batches, contactInfo, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer2, 54), $composer2, (($dirty2 >> 15) & 14) | 3504, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AdminDashboardDialogKt.AdminDashboardDialog$lambda$63(repository, teacherProfile, courses, batches, contactInfo, onDismiss, $changed, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }

        private static final int AdminDashboardDialog$lambda$34(MutableIntState $selectedTab$delegate) {
            return $selectedTab$delegate.getIntValue();
        }

        static final Unit AdminDashboardDialog$lambda$62(final Function0 $onDismiss, final MutableIntState $selectedTab$delegate, final SqlConnectRepository $repository, final TeacherProfile $teacherProfile, final Context $context, final List $courses, final List $batches, final ContactInfo $contactInfo, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C196@8609L11,201@8770L3952,194@8519L4203:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(56727824, $changed, -1, "com.example.sushikhasacademyapp.ui.AdminDashboardDialog.<anonymous> (AdminDashboardDialog.kt:194)");
                }
                SurfaceKt.m2929SurfaceT9BRK9s(PaddingKt.m819padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(16)), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(20)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getSurface(), 0L, Dp.m7905constructorimpl(6), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-2093565867, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61($onDismiss, $selectedTab$delegate, $repository, $teacherProfile, $context, $courses, $batches, $contactInfo, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), $composer, 12607494, LocationRequestCompat.QUALITY_LOW_POWER);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61(final Function0 $onDismiss, final MutableIntState $selectedTab$delegate, final SqlConnectRepository $repository, final TeacherProfile $teacherProfile, final Context $context, final List $courses, final List $batches, final ContactInfo $contactInfo, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            ComposerKt.sourceInformation($composer, "C202@8784L3928:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2093565867, $changed, -1, "com.example.sushikhasacademyapp.ui.AdminDashboardDialog.<anonymous>.<anonymous> (AdminDashboardDialog.kt:202)");
                }
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
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
                int i3 = ((6 >> 6) & 112) | 6;
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 278994152, "C211@9319L191,204@8874L654,219@9700L1253,219@9631L1322,237@10971L1727:AdminDashboardDialog.kt#untpz1");
                AppBarKt.m2090TopAppBarGHTll3U(ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1447408647$app(), null, null, ComposableLambdaKt.rememberComposableLambda(-636547332, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda42
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$36($onDismiss, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), 0.0f, null, null, null, $composer, 3078, 246);
                TabRowKt.m2977ScrollableTabRowsKfQg0A(AdminDashboardDialog$lambda$34($selectedTab$delegate), null, 0L, 0L, Dp.m7905constructorimpl(8), null, null, ComposableLambdaKt.rememberComposableLambda(1506251563, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda43
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47($selectedTab$delegate, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), $composer, 12607488, 110);
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m7905constructorimpl(16));
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 2114931270, "C244@11241L572,254@11890L790,242@11132L1548:AdminDashboardDialog.kt#untpz1");
                Integer numValueOf = Integer.valueOf(AdminDashboardDialog$lambda$34($selectedTab$delegate));
                ComposerKt.sourceInformationMarkerStart($composer, -208868563, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda44
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52((AnimatedContentTransitionScope) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                AnimatedContentKt.AnimatedContent(numValueOf, null, (Function1) objRememberedValue, null, "AdminTabTransition", null, ComposableLambdaKt.rememberComposableLambda(720233115, true, new Function4() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda45
                    @Override // kotlin.jvm.functions.Function4
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$58($repository, $teacherProfile, $context, $courses, $batches, $contactInfo, (AnimatedContentScope) obj2, ((Integer) obj3).intValue(), (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, $composer, 54), $composer, 1597824, 42);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$36(Function0 $onDismiss, RowScope TopAppBar, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(TopAppBar, "$this$TopAppBar");
            ComposerKt.sourceInformation($composer, "C212@9345L143:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-636547332, $changed, -1, "com.example.sushikhasacademyapp.ui.AdminDashboardDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:212)");
                }
                IconButtonKt.IconButton($onDismiss, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8706getLambda$1049562726$app(), $composer, 1572864, 62);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47(final MutableIntState $selectedTab$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C220@9765L19,220@9722L212,223@9998L19,223@9955L209,226@10228L19,226@10185L209,229@10458L19,229@10415L209,232@10688L19,232@10645L290:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1506251563, $changed, -1, "com.example.sushikhasacademyapp.ui.AdminDashboardDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:220)");
                }
                boolean z = AdminDashboardDialog$lambda$34($selectedTab$delegate) == 0;
                ComposerKt.sourceInformationMarkerStart($composer, 103775390, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda101
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$38$lambda$37($selectedTab$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TabKt.m2962TabbogVsAg(z, (Function0) objRememberedValue, null, false, 0L, 0L, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1012176670$app(), $composer, 12582960, 124);
                boolean z2 = AdminDashboardDialog$lambda$34($selectedTab$delegate) == 1;
                ComposerKt.sourceInformationMarkerStart($composer, 103782846, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda102
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$40$lambda$39($selectedTab$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TabKt.m2962TabbogVsAg(z2, (Function0) objRememberedValue2, null, false, 0L, 0L, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$291401749$app(), $composer, 12582960, 124);
                boolean z3 = AdminDashboardDialog$lambda$34($selectedTab$delegate) == 2;
                ComposerKt.sourceInformationMarkerStart($composer, 103790206, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue3 = $composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda103
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$42$lambda$41($selectedTab$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj3);
                    objRememberedValue3 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TabKt.m2962TabbogVsAg(z3, (Function0) objRememberedValue3, null, false, 0L, 0L, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1109297110$app(), $composer, 12582960, 124);
                boolean z4 = AdminDashboardDialog$lambda$34($selectedTab$delegate) == 3;
                ComposerKt.sourceInformationMarkerStart($composer, 103797566, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue4 = $composer.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object obj4 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda104
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$44$lambda$43($selectedTab$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj4);
                    objRememberedValue4 = obj4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TabKt.m2962TabbogVsAg(z4, (Function0) objRememberedValue4, null, false, 0L, 0L, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1927192471$app(), $composer, 12582960, 124);
                boolean z5 = AdminDashboardDialog$lambda$34($selectedTab$delegate) == 4;
                ComposerKt.sourceInformationMarkerStart($composer, 103804926, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue5 = $composer.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    Object obj5 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda105
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$46$lambda$45($selectedTab$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj5);
                    objRememberedValue5 = obj5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                TabKt.m2962TabbogVsAg(z5, (Function0) objRememberedValue5, null, false, 0L, 0L, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8714getLambda$1549879464$app(), $composer, 12582960, 124);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$38$lambda$37(MutableIntState $selectedTab$delegate) {
            $selectedTab$delegate.setIntValue(0);
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$40$lambda$39(MutableIntState $selectedTab$delegate) {
            $selectedTab$delegate.setIntValue(1);
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$42$lambda$41(MutableIntState $selectedTab$delegate) {
            $selectedTab$delegate.setIntValue(2);
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$44$lambda$43(MutableIntState $selectedTab$delegate) {
            $selectedTab$delegate.setIntValue(3);
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$47$lambda$46$lambda$45(MutableIntState $selectedTab$delegate) {
            $selectedTab$delegate.setIntValue(4);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        static final ContentTransform AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52(AnimatedContentTransitionScope AnimatedContent) {
            Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
            if (((Number) AnimatedContent.getTargetState()).intValue() > ((Number) AnimatedContent.getInitialState()).intValue()) {
                return AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInHorizontally$default(null, new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda66
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52$lambda$48(((Integer) obj).intValue()));
                    }
                }, 1, null).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(280, 0, null, 6, null), 0.0f, 2, null)), EnterExitTransitionKt.slideOutHorizontally$default(null, new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda67
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52$lambda$49(((Integer) obj).intValue()));
                    }
                }, 1, null).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(280, 0, null, 6, null), 0.0f, 2, null)));
            }
            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.slideInHorizontally$default(null, new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda68
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52$lambda$50(((Integer) obj).intValue()));
                }
            }, 1, null).plus(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(280, 0, null, 6, null), 0.0f, 2, null)), EnterExitTransitionKt.slideOutHorizontally$default(null, new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda69
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52$lambda$51(((Integer) obj).intValue()));
                }
            }, 1, null).plus(EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(280, 0, null, 6, null), 0.0f, 2, null)));
        }

        static final int AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52$lambda$48(int width) {
            return width;
        }

        static final int AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52$lambda$49(int width) {
            return -width;
        }

        static final int AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52$lambda$50(int width) {
            return -width;
        }

        static final int AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$53$lambda$52$lambda$51(int width) {
            return width;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$58(SqlConnectRepository $repository, TeacherProfile $teacherProfile, final Context $context, List $courses, List $batches, ContactInfo $contactInfo, AnimatedContentScope AnimatedContent, int tab, Composer $composer, int $changed) {
            Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
            ComposerKt.sourceInformation($composer, "CN(tab):AdminDashboardDialog.kt#untpz1");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(720233115, $changed, -1, "com.example.sushikhasacademyapp.ui.AdminDashboardDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:255)");
            }
            switch (tab) {
                case 0:
                    $composer.startReplaceGroup(654291596);
                    ComposerKt.sourceInformation($composer, "256@12021L157,256@11969L209");
                    ComposerKt.sourceInformationMarkerStart($composer, 654293208, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    boolean zChangedInstance = $composer.changedInstance($context);
                    Object objRememberedValue = $composer.rememberedValue();
                    if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda39
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$58$lambda$55$lambda$54($context);
                            }
                        };
                        $composer.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    TeacherProfileAdminForm($repository, $teacherProfile, (Function0) objRememberedValue, $composer, SqlConnectRepository.$stable | (TeacherProfile.$stable << 3));
                    $composer.endReplaceGroup();
                    break;
                case 1:
                    $composer.startReplaceGroup(654299200);
                    ComposerKt.sourceInformation($composer, "259@12212L37");
                    CoursesAdminForm($repository, $courses, $composer, SqlConnectRepository.$stable);
                    $composer.endReplaceGroup();
                    break;
                case 2:
                    $composer.startReplaceGroup(654301472);
                    ComposerKt.sourceInformation($composer, "260@12283L37");
                    BatchesAdminForm($repository, $batches, $composer, SqlConnectRepository.$stable);
                    $composer.endReplaceGroup();
                    break;
                case 3:
                    $composer.startReplaceGroup(654303907);
                    ComposerKt.sourceInformation($composer, "261@12400L154,261@12354L200");
                    ComposerKt.sourceInformationMarkerStart($composer, 654305333, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    boolean zChangedInstance2 = $composer.changedInstance($context);
                    Object objRememberedValue2 = $composer.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda40
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$58$lambda$57$lambda$56($context);
                            }
                        };
                        $composer.updateRememberedValue(obj2);
                        objRememberedValue2 = obj2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ContactInfoAdminForm($repository, $contactInfo, (Function0) objRememberedValue2, $composer, SqlConnectRepository.$stable | (ContactInfo.$stable << 3));
                    $composer.endReplaceGroup();
                    break;
                case 4:
                    $composer.startReplaceGroup(654311239);
                    ComposerKt.sourceInformation($composer, "264@12588L44");
                    StudentEnrollmentAdminForm($courses, $batches, $composer, 0);
                    $composer.endReplaceGroup();
                    break;
                default:
                    $composer.startReplaceGroup(-1203677721);
                    $composer.endReplaceGroup();
                    break;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$58$lambda$55$lambda$54(Context $context) {
            Toast.makeText($context, "Teacher details synced to SQL Database!", 0).show();
            return Unit.INSTANCE;
        }

        static final Unit AdminDashboardDialog$lambda$62$lambda$61$lambda$60$lambda$59$lambda$58$lambda$57$lambda$56(Context $context) {
            Toast.makeText($context, "Contact info synced to SQL Database!", 0).show();
            return Unit.INSTANCE;
        }

        public static final void TeacherProfileAdminForm(final SqlConnectRepository repository, final TeacherProfile currentProfile, Function0<Unit> function0, Composer $composer, final int $changed) {
            Function0<ComposeUiNode> function1;
            Composer $composer2;
            final Function0<Unit> onSaved = function0;
            Intrinsics.checkNotNullParameter(repository, "repository");
            Intrinsics.checkNotNullParameter(currentProfile, "currentProfile");
            Intrinsics.checkNotNullParameter(onSaved, "onSaved");
            Composer $composer3 = $composer.startRestartGroup(1096765593);
            ComposerKt.sourceInformation($composer3, "C(TeacherProfileAdminForm)N(repository,currentProfile,onSaved)279@12891L48,280@12957L49,281@13030L55,282@13111L116,283@13245L49,284@13310L47,289@13454L21,286@13363L3920:AdminDashboardDialog.kt#untpz1");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer3.changed(repository) : $composer3.changedInstance(repository) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= ($changed & 64) == 0 ? $composer3.changed(currentProfile) : $composer3.changedInstance(currentProfile) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                $dirty |= $composer3.changedInstance(onSaved) ? 256 : 128;
            }
            int $dirty2 = $dirty;
            if (!$composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
                $composer3 = $composer3;
                $composer3.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1096765593, $dirty2, -1, "com.example.sushikhasacademyapp.ui.TeacherProfileAdminForm (AdminDashboardDialog.kt:278)");
                }
                ComposerKt.sourceInformationMarkerStart($composer3, 1724752489, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer3.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentProfile.getName(), null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue = objMutableStateOf$default;
                }
                final MutableState name$delegate = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 1724754602, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue2 = $composer3.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentProfile.getTitle(), null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default2);
                    objRememberedValue2 = objMutableStateOf$default2;
                }
                final MutableState title$delegate = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 1724756944, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue3 = $composer3.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentProfile.getCredentials(), null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default3);
                    objRememberedValue3 = objMutableStateOf$default3;
                }
                final MutableState credentials$delegate = (MutableState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 1724759597, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue4 = $composer3.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentProfile.getStudentsGuided() > 0 ? String.valueOf(currentProfile.getStudentsGuided()) : "", null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default4);
                    objRememberedValue4 = objMutableStateOf$default4;
                }
                final MutableState studentsCount$delegate = (MutableState) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 1724763818, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue5 = $composer3.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentProfile.getDpUrl(), null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default5);
                    objRememberedValue5 = objMutableStateOf$default5;
                }
                final MutableState dpUrl$delegate = (MutableState) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 1724765896, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue6 = $composer3.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentProfile.getBio(), null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default6);
                    objRememberedValue6 = objMutableStateOf$default6;
                }
                final MutableState bio$delegate = (MutableState) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null);
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(10));
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierVerticalScroll$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 896543507, "C292@13608L10,292@13552L113,296@13748L13,294@13675L246,304@14005L14,302@13931L273,312@14294L20,310@14214L305,320@14603L14,318@14529L281,328@14950L11,328@14908L69,326@14820L843,347@15755L22,345@15673L260,355@16015L12,353@15943L332,362@16285L41,365@16366L674,364@16336L941:AdminDashboardDialog.kt#untpz1");
                TextKt.m3072TextNvy7gAk("Edit Teacher Profile & DP", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getTitleSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer3, 6, 0, 131070);
                String strTeacherProfileAdminForm$lambda$65 = TeacherProfileAdminForm$lambda$65(name$delegate);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1968585968, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue7 = $composer3.rememberedValue();
                if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda52
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AdminDashboardDialogKt.TeacherProfileAdminForm$lambda$98$lambda$83$lambda$82(name$delegate, (String) obj2);
                        }
                    };
                    $composer3.updateRememberedValue(obj);
                    objRememberedValue7 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(strTeacherProfileAdminForm$lambda$65, (Function1<? super String, Unit>) objRememberedValue7, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1930741769$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8725getLambda$891946934$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 14156208, 0, 0, 8388408);
                String strTeacherProfileAdminForm$lambda$68 = TeacherProfileAdminForm$lambda$68(title$delegate);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1968594193, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue8 = $composer3.rememberedValue();
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda53
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return AdminDashboardDialogKt.TeacherProfileAdminForm$lambda$98$lambda$85$lambda$84(title$delegate, (String) obj3);
                        }
                    };
                    $composer3.updateRememberedValue(obj2);
                    objRememberedValue8 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(strTeacherProfileAdminForm$lambda$68, (Function1<? super String, Unit>) objRememberedValue8, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1175010098$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8712getLambda$1304469709$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 14156208, 0, 0, 8388408);
                String strTeacherProfileAdminForm$lambda$71 = TeacherProfileAdminForm$lambda$71(credentials$delegate);
                Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1968603447, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue9 = $composer3.rememberedValue();
                if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda54
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return AdminDashboardDialogKt.TeacherProfileAdminForm$lambda$98$lambda$87$lambda$86(credentials$delegate, (String) obj4);
                        }
                    };
                    $composer3.updateRememberedValue(obj3);
                    objRememberedValue9 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(strTeacherProfileAdminForm$lambda$71, (Function1<? super String, Unit>) objRememberedValue9, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8723getLambda$865665007$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$949822482$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 14156208, 0, 0, 8388408);
                String strTeacherProfileAdminForm$lambda$77 = TeacherProfileAdminForm$lambda$77(dpUrl$delegate);
                Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1968613329, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue10 = $composer3.rememberedValue();
                if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    Object obj4 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda55
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj5) {
                            return AdminDashboardDialogKt.TeacherProfileAdminForm$lambda$98$lambda$89$lambda$88(dpUrl$delegate, (String) obj5);
                        }
                    };
                    $composer3.updateRememberedValue(obj4);
                    objRememberedValue10 = obj4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(strTeacherProfileAdminForm$lambda$77, (Function1<? super String, Unit>) objRememberedValue10, modifierFillMaxWidth$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1388627184$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8707getLambda$1090852623$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 14156208, 0, 0, 8388408);
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(8)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getSurfaceVariant(), 0L, 0L, 0L, $composer3, CardDefaults.$stable << 12, 14), null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1209580977$app(), $composer3, 196614, 24);
                String strTeacherProfileAdminForm$lambda$74 = TeacherProfileAdminForm$lambda$74(studentsCount$delegate);
                Modifier modifierFillMaxWidth$default5 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1968650201, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue11 = $composer3.rememberedValue();
                if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    Object obj5 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda56
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj6) {
                            return AdminDashboardDialogKt.TeacherProfileAdminForm$lambda$98$lambda$91$lambda$90(studentsCount$delegate, (String) obj6);
                        }
                    };
                    $composer3.updateRememberedValue(obj5);
                    objRememberedValue11 = obj5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(strTeacherProfileAdminForm$lambda$74, (Function1<? super String, Unit>) objRememberedValue11, modifierFillMaxWidth$default5, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8722getLambda$652047921$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1163439568$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 14156208, 0, 0, 8388408);
                String strTeacherProfileAdminForm$lambda$80 = TeacherProfileAdminForm$lambda$80(bio$delegate);
                Modifier modifierFillMaxWidth$default6 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1968658511, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue12 = $composer3.rememberedValue();
                if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                    Object obj6 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda57
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj7) {
                            return AdminDashboardDialogKt.TeacherProfileAdminForm$lambda$98$lambda$93$lambda$92(bio$delegate, (String) obj7);
                        }
                    };
                    $composer3.updateRememberedValue(obj6);
                    objRememberedValue12 = obj6;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(strTeacherProfileAdminForm$lambda$80, (Function1<? super String, Unit>) objRememberedValue12, modifierFillMaxWidth$default6, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1602244270$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8724getLambda$877235537$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 2, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 14156208, C.ENCODING_PCM_32BIT, 0, 7864120);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer3, 6);
                ComposerKt.sourceInformationMarkerStart($composer3, 1968670405, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                boolean z = (($dirty2 & 112) == 32 || (($dirty2 & 64) != 0 && $composer3.changedInstance(currentProfile))) | (($dirty2 & 14) == 4 || (($dirty2 & 8) != 0 && $composer3.changedInstance(repository))) | (($dirty2 & 896) == 256);
                Object objRememberedValue13 = $composer3.rememberedValue();
                if (z || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                    onSaved = function0;
                    $composer2 = null;
                    objRememberedValue13 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda58
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.TeacherProfileAdminForm$lambda$98$lambda$97$lambda$96(currentProfile, repository, name$delegate, title$delegate, credentials$delegate, studentsCount$delegate, dpUrl$delegate, bio$delegate, onSaved);
                        }
                    };
                    $composer3.updateRememberedValue(objRememberedValue13);
                } else {
                    onSaved = function0;
                    $composer2 = null;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ButtonKt.Button((Function0) objRememberedValue13, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, $composer2), false, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1337568755$app(), $composer3, 805306416, TypedValues.PositionType.TYPE_CURVE_FIT);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda59
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj7, Object obj8) {
                        return AdminDashboardDialogKt.TeacherProfileAdminForm$lambda$99(repository, currentProfile, onSaved, $changed, (Composer) obj7, ((Integer) obj8).intValue());
                    }
                });
            }
        }

        private static final String TeacherProfileAdminForm$lambda$65(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String TeacherProfileAdminForm$lambda$68(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String TeacherProfileAdminForm$lambda$71(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String TeacherProfileAdminForm$lambda$74(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String TeacherProfileAdminForm$lambda$77(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String TeacherProfileAdminForm$lambda$80(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        static final Unit TeacherProfileAdminForm$lambda$98$lambda$83$lambda$82(MutableState $name$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $name$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit TeacherProfileAdminForm$lambda$98$lambda$85$lambda$84(MutableState $title$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $title$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit TeacherProfileAdminForm$lambda$98$lambda$87$lambda$86(MutableState $credentials$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $credentials$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit TeacherProfileAdminForm$lambda$98$lambda$89$lambda$88(MutableState $dpUrl$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $dpUrl$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit TeacherProfileAdminForm$lambda$98$lambda$91$lambda$90(MutableState $studentsCount$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $studentsCount$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit TeacherProfileAdminForm$lambda$98$lambda$93$lambda$92(MutableState $bio$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $bio$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit TeacherProfileAdminForm$lambda$98$lambda$97$lambda$96(TeacherProfile $currentProfile, SqlConnectRepository $repository, MutableState $name$delegate, MutableState $title$delegate, MutableState $credentials$delegate, MutableState $studentsCount$delegate, MutableState $dpUrl$delegate, MutableState $bio$delegate, final Function0 $onSaved) {
            String strTeacherProfileAdminForm$lambda$65 = TeacherProfileAdminForm$lambda$65($name$delegate);
            String strTeacherProfileAdminForm$lambda$68 = TeacherProfileAdminForm$lambda$68($title$delegate);
            String strTeacherProfileAdminForm$lambda$71 = TeacherProfileAdminForm$lambda$71($credentials$delegate);
            Integer intOrNull = StringsKt.toIntOrNull(TeacherProfileAdminForm$lambda$74($studentsCount$delegate));
            int iIntValue = intOrNull != null ? intOrNull.intValue() : 0;
            String strTeacherProfileAdminForm$lambda$77 = TeacherProfileAdminForm$lambda$77($dpUrl$delegate);
            String strTeacherProfileAdminForm$lambda$80 = TeacherProfileAdminForm$lambda$80($bio$delegate);
            List<String> specialties = $currentProfile.getSpecialties();
            if (specialties.isEmpty()) {
                specialties = CollectionsKt.listOf((Object[]) new String[]{"Spoken English", "Advanced Grammar & Literature", "IELTS & Public Speaking"});
            }
            TeacherProfile updated = new TeacherProfile(strTeacherProfileAdminForm$lambda$65, strTeacherProfileAdminForm$lambda$68, strTeacherProfileAdminForm$lambda$71, 4.9d, iIntValue, strTeacherProfileAdminForm$lambda$77, strTeacherProfileAdminForm$lambda$80, specialties);
            $repository.updateTeacherProfile(updated, new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AdminDashboardDialogKt.TeacherProfileAdminForm$lambda$98$lambda$97$lambda$96$lambda$95($onSaved, ((Boolean) obj).booleanValue());
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit TeacherProfileAdminForm$lambda$98$lambda$97$lambda$96$lambda$95(Function0 $onSaved, boolean it) {
            $onSaved.invoke();
            return Unit.INSTANCE;
        }

        public static final void CoursesAdminForm(final SqlConnectRepository repository, final List<CourseInfo> courses, Composer $composer, final int $changed) {
            Composer $composer2;
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            SnapshotMutationPolicy snapshotMutationPolicy;
            final SqlConnectRepository repository2 = repository;
            Intrinsics.checkNotNullParameter(repository2, "repository");
            Intrinsics.checkNotNullParameter(courses, "courses");
            Composer $composer3 = $composer.startRestartGroup(1211279811);
            ComposerKt.sourceInformation($composer3, "C(CoursesAdminForm)N(repository,courses)396@17424L7,397@17463L34,398@17523L46,400@17575L2521:AdminDashboardDialog.kt#untpz1");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer3.changed(repository2) : $composer3.changedInstance(repository2) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changedInstance(courses) ? 32 : 16;
            }
            int $dirty2 = $dirty;
            if (!$composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1211279811, $dirty2, -1, "com.example.sushikhasacademyapp.ui.CoursesAdminForm (AdminDashboardDialog.kt:395)");
                }
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Context context = (Context) objConsume;
                ComposerKt.sourceInformationMarkerStart($composer3, 103766821, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer3.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue = objMutableStateOf$default;
                }
                final MutableState showAddCourseDialog$delegate = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 103768753, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue2 = $composer3.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default2);
                    objRememberedValue2 = objMutableStateOf$default2;
                }
                final MutableState editingCourse$delegate = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierFillMaxSize$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                Context context2 = context;
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i3 = ((6 >> 6) & 112) | 6;
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -150558293, "C402@17657L103,401@17627L353,413@17990L41,418@18141L21,415@18041L2049:AdminDashboardDialog.kt#untpz1");
                ComposerKt.sourceInformationMarkerStart($composer3, -836142112, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue3 = $composer3.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda80
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$119$lambda$107$lambda$106(editingCourse$delegate, showAddCourseDialog$delegate);
                        }
                    };
                    $composer3.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ButtonKt.Button((Function0) objRememberedValue3, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1930669225$app(), $composer3, 805306422, TypedValues.PositionType.TYPE_CURVE_FIT);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer3, 6);
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null);
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierVerticalScroll$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Composer composer = $composer3;
                int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor2;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer3);
                MeasurePolicy measurePolicy = measurePolicyColumnMeasurePolicy2;
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = ((48 >> 6) & 112) | 6;
                Composer composer2 = $composer3;
                Composer composer3 = $composer3;
                ComposerKt.sourceInformationMarkerStart(composer2, -2093664050, "C:AdminDashboardDialog.kt#untpz1");
                composer2.startReplaceGroup(-1037368627);
                ComposerKt.sourceInformation(composer2, "*424@18440L11,424@18398L69,425@18486L1580,422@18294L1772");
                for (final CourseInfo courseInfo : courses) {
                    Composer composer4 = composer2;
                    final Context context3 = context2;
                    CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getSurfaceVariant(), 0L, 0L, 0L, composer4, CardDefaults.$stable << 12, 14), null, null, ComposableLambdaKt.rememberComposableLambda(-716973147, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda81
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$119$lambda$118$lambda$117$lambda$116(courseInfo, repository, context3, editingCourse$delegate, showAddCourseDialog$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composer4, 54), composer4, 196614, 26);
                    context2 = context3;
                    function1 = function1;
                    $dirty2 = $dirty2;
                    composer = composer;
                    modifierMaterializeModifier2 = modifierMaterializeModifier2;
                    composer2 = composer4;
                    measurePolicy = measurePolicy;
                    composer3 = composer3;
                }
                final Context context4 = context2;
                Composer composer5 = composer2;
                composer5.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer5);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (!CoursesAdminForm$lambda$101(showAddCourseDialog$delegate) || CoursesAdminForm$lambda$104(editingCourse$delegate) == null) {
                    repository2 = repository;
                    $composer2 = $composer3;
                    $composer2.startReplaceGroup(-1095521185);
                } else {
                    $composer3.startReplaceGroup(-1075436347);
                    ComposerKt.sourceInformation($composer3, "462@20212L41,463@20281L47,464@20353L44,465@20418L40,466@20480L41,467@20541L39,470@20634L31,487@22110L858,507@22998L140,471@20687L70,472@20778L1302,469@20590L2558");
                    final CourseInfo course = CoursesAdminForm$lambda$104(editingCourse$delegate);
                    Intrinsics.checkNotNull(course);
                    ComposerKt.sourceInformationMarkerStart($composer3, 103854796, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue4 = $composer3.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        snapshotMutationPolicy = null;
                        Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(course.getTitle(), null, 2, null);
                        $composer3.updateRememberedValue(objMutableStateOf$default3);
                        objRememberedValue4 = objMutableStateOf$default3;
                    } else {
                        snapshotMutationPolicy = null;
                    }
                    final MutableState title$delegate = (MutableState) objRememberedValue4;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, 103857010, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue5 = $composer3.rememberedValue();
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(course.getDescription(), snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default4);
                        objRememberedValue5 = objMutableStateOf$default4;
                    }
                    final MutableState description$delegate = (MutableState) objRememberedValue5;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, 103859311, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue6 = $composer3.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(course.getDuration(), snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default5);
                        objRememberedValue6 = objMutableStateOf$default5;
                    }
                    final MutableState duration$delegate = (MutableState) objRememberedValue6;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, 103861387, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue7 = $composer3.rememberedValue();
                    if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(course.getMode(), snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default6);
                        objRememberedValue7 = objMutableStateOf$default6;
                    }
                    final MutableState mode$delegate = (MutableState) objRememberedValue7;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, 103863372, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue8 = $composer3.rememberedValue();
                    if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(course.getLevel(), snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default7);
                        objRememberedValue8 = objMutableStateOf$default7;
                    }
                    final MutableState level$delegate = (MutableState) objRememberedValue8;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, 103865322, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue9 = $composer3.rememberedValue();
                    if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(course.getFee(), snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default8);
                        objRememberedValue9 = objMutableStateOf$default8;
                    }
                    final MutableState fee$delegate = (MutableState) objRememberedValue9;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, 103868290, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue10 = $composer3.rememberedValue();
                    if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda82
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.CoursesAdminForm$lambda$139$lambda$138(showAddCourseDialog$delegate);
                            }
                        };
                        $composer3.updateRememberedValue(obj);
                        objRememberedValue10 = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    repository2 = repository;
                    AndroidAlertDialog_androidKt.m2066AlertDialogOix01E0((Function0) objRememberedValue10, ComposableLambdaKt.rememberComposableLambda(-119440362, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda83
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$143(course, repository2, context4, title$delegate, description$delegate, duration$delegate, mode$delegate, level$delegate, fee$delegate, showAddCourseDialog$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(-384087852, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda84
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$146(showAddCourseDialog$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(-648735342, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda85
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$147(course, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(-781059087, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda86
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$161(title$delegate, description$delegate, duration$delegate, mode$delegate, level$delegate, fee$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer3, 1772598, 0, 16276);
                    $composer2 = $composer3;
                }
                $composer2.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda87
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return AdminDashboardDialogKt.CoursesAdminForm$lambda$162(repository2, courses, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }

        private static final boolean CoursesAdminForm$lambda$101(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void CoursesAdminForm$lambda$102(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        private static final CourseInfo CoursesAdminForm$lambda$104(MutableState<CourseInfo> mutableState) {
            return mutableState.getValue();
        }

        static final Unit CoursesAdminForm$lambda$119$lambda$107$lambda$106(MutableState $editingCourse$delegate, MutableState $showAddCourseDialog$delegate) {
            $editingCourse$delegate.setValue(new CourseInfo(null, null, null, null, null, null, null, 0.0d, null, FrameMetricsAggregator.EVERY_DURATION, null));
            CoursesAdminForm$lambda$102($showAddCourseDialog$delegate, true);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:41:0x04d8  */
        /* JADX WARN: Code duplicated, block: B:44:0x04e3  */
        /* JADX WARN: Code duplicated, block: B:48:0x0545  */
        static final Unit CoursesAdminForm$lambda$119$lambda$118$lambda$117$lambda$116(final CourseInfo $course, final SqlConnectRepository $repository, final Context $context, final MutableState $editingCourse$delegate, final MutableState $showAddCourseDialog$delegate, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Composer composer;
            Composer composer2;
            boolean zChangedInstance;
            Object objRememberedValue;
            Composer composer3;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C426@18508L1540:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-716973147, $changed, -1, "com.example.sushikhasacademyapp.ui.CoursesAdminForm.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:426)");
                }
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), 0.0f, 1, null);
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i3 = ((438 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1426183199, "C433@18839L325,438@19190L836:AdminDashboardDialog.kt#untpz1");
                Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 957353010, "C434@18949L10,434@18908L98,435@19117L10,435@19035L103:AdminDashboardDialog.kt#untpz1");
                TextKt.m3072TextNvy7gAk($course.getTitle(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 0, 131070);
                TextKt.m3072TextNvy7gAk($course.getDuration() + " • " + $course.getFee() + " • " + $course.getMode(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131070);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i9 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 530611262, "C439@19245L145,439@19224L327,446@19602L257,446@19581L419:AdminDashboardDialog.kt#untpz1");
                ComposerKt.sourceInformationMarkerStart($composer, 848400526, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                boolean zChangedInstance2 = $composer.changedInstance($course);
                Object objRememberedValue2 = $composer.rememberedValue();
                if (zChangedInstance2) {
                    composer = $composer;
                } else {
                    composer = $composer;
                    if (objRememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$990088959$app(), composer, 1572864, 62);
                    composer2 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer2, 848412062, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    zChangedInstance = composer2.changedInstance($repository) | composer2.changedInstance($course) | composer2.changedInstance($context);
                    objRememberedValue = composer2.rememberedValue();
                    if (zChangedInstance) {
                        composer3 = composer2;
                    } else {
                        composer3 = composer2;
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$274541032$app(), composer3, 1572864, 62);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda71
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$119$lambda$118$lambda$117$lambda$116$lambda$115$lambda$114$lambda$113$lambda$112($repository, $course, $context);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$274541032$app(), composer3, 1572864, 62);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                objRememberedValue2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda70
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AdminDashboardDialogKt.CoursesAdminForm$lambda$119$lambda$118$lambda$117$lambda$116$lambda$115$lambda$114$lambda$110$lambda$109($course, $editingCourse$delegate, $showAddCourseDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue2);
                ComposerKt.sourceInformationMarkerEnd(composer);
                IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$990088959$app(), composer, 1572864, 62);
                composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, 848412062, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                zChangedInstance = composer2.changedInstance($repository) | composer2.changedInstance($course) | composer2.changedInstance($context);
                objRememberedValue = composer2.rememberedValue();
                if (zChangedInstance) {
                    composer3 = composer2;
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$274541032$app(), composer3, 1572864, 62);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
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
                    composer3 = composer2;
                }
                objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda71
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AdminDashboardDialogKt.CoursesAdminForm$lambda$119$lambda$118$lambda$117$lambda$116$lambda$115$lambda$114$lambda$113$lambda$112($repository, $course, $context);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$274541032$app(), composer3, 1572864, 62);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
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

        static final Unit CoursesAdminForm$lambda$119$lambda$118$lambda$117$lambda$116$lambda$115$lambda$114$lambda$110$lambda$109(CourseInfo $course, MutableState $editingCourse$delegate, MutableState $showAddCourseDialog$delegate) {
            $editingCourse$delegate.setValue($course);
            CoursesAdminForm$lambda$102($showAddCourseDialog$delegate, true);
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$119$lambda$118$lambda$117$lambda$116$lambda$115$lambda$114$lambda$113$lambda$112(SqlConnectRepository $repository, CourseInfo $course, final Context $context) {
            $repository.deleteCourse($course.getId(), new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda79
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AdminDashboardDialogKt.CoursesAdminForm$lambda$119$lambda$118$lambda$117$lambda$116$lambda$115$lambda$114$lambda$113$lambda$112$lambda$111($context, ((Boolean) obj).booleanValue());
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$119$lambda$118$lambda$117$lambda$116$lambda$115$lambda$114$lambda$113$lambda$112$lambda$111(Context $context, boolean it) {
            Toast.makeText($context, "Course deleted from SQL Database", 0).show();
            return Unit.INSTANCE;
        }

        private static final String CoursesAdminForm$lambda$121(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String CoursesAdminForm$lambda$124(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String CoursesAdminForm$lambda$127(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String CoursesAdminForm$lambda$130(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String CoursesAdminForm$lambda$133(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String CoursesAdminForm$lambda$136(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        static final Unit CoursesAdminForm$lambda$139$lambda$138(MutableState $showAddCourseDialog$delegate) {
            CoursesAdminForm$lambda$102($showAddCourseDialog$delegate, false);
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$147(CourseInfo $course, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C471@20689L66:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-648735342, $changed, -1, "com.example.sushikhasacademyapp.ui.CoursesAdminForm.<anonymous> (AdminDashboardDialog.kt:471)");
                }
                TextKt.m3072TextNvy7gAk(StringsKt.isBlank($course.getId()) ? "Add New Course" : "Edit Course", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$161(final MutableState $title$delegate, final MutableState $description$delegate, final MutableState $duration$delegate, final MutableState $mode$delegate, final MutableState $level$delegate, final MutableState $fee$delegate, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C476@20924L21,473@20796L1270:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-781059087, $changed, -1, "com.example.sushikhasacademyapp.ui.CoursesAdminForm.<anonymous> (AdminDashboardDialog.kt:473)");
                }
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer, 0, 1), false, null, false, 14, null);
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
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
                ComposerKt.sourceInformationMarkerStart($composer, -1721379031, "C479@21106L14,479@21057L134,480@21267L20,480@21212L145,481@21430L17,481@21378L152,482@21599L13,482@21551L150,483@21771L14,483@21722L160,484@21950L12,484@21903L145:AdminDashboardDialog.kt#untpz1");
                String strCoursesAdminForm$lambda$121 = CoursesAdminForm$lambda$121($title$delegate);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 2022682217, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda60
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$161$lambda$160$lambda$149$lambda$148($title$delegate, (String) obj);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strCoursesAdminForm$lambda$121, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1205005153$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                String strCoursesAdminForm$lambda$124 = CoursesAdminForm$lambda$124($description$delegate);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 2022687375, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda61
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$161$lambda$160$lambda$151$lambda$150($description$delegate, (String) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strCoursesAdminForm$lambda$124, (Function1<? super String, Unit>) objRememberedValue2, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1714976522$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                String strCoursesAdminForm$lambda$127 = CoursesAdminForm$lambda$127($duration$delegate);
                Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 2022692588, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue3 = $composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda62
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$161$lambda$160$lambda$153$lambda$152($duration$delegate, (String) obj3);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue3 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strCoursesAdminForm$lambda$127, (Function1<? super String, Unit>) objRememberedValue3, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$379602793$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                String strCoursesAdminForm$lambda$130 = CoursesAdminForm$lambda$130($mode$delegate);
                Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 2022697992, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue4 = $composer.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda63
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$161$lambda$160$lambda$155$lambda$154($mode$delegate, (String) obj4);
                        }
                    };
                    $composer.updateRememberedValue(obj3);
                    objRememberedValue4 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strCoursesAdminForm$lambda$130, (Function1<? super String, Unit>) objRememberedValue4, modifierFillMaxWidth$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8727getLambda$955770936$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                String strCoursesAdminForm$lambda$133 = CoursesAdminForm$lambda$133($level$delegate);
                Modifier modifierFillMaxWidth$default5 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 2022703497, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue5 = $composer.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    Object obj4 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda64
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj5) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$161$lambda$160$lambda$157$lambda$156($level$delegate, (String) obj5);
                        }
                    };
                    $composer.updateRememberedValue(obj4);
                    objRememberedValue5 = obj4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strCoursesAdminForm$lambda$133, (Function1<? super String, Unit>) objRememberedValue5, modifierFillMaxWidth$default5, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$2003822631$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                String strCoursesAdminForm$lambda$136 = CoursesAdminForm$lambda$136($fee$delegate);
                Modifier modifierFillMaxWidth$default6 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 2022709223, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue6 = $composer.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    Object obj5 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda65
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj6) {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$161$lambda$160$lambda$159$lambda$158($fee$delegate, (String) obj6);
                        }
                    };
                    $composer.updateRememberedValue(obj5);
                    objRememberedValue6 = obj5;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strCoursesAdminForm$lambda$136, (Function1<? super String, Unit>) objRememberedValue6, modifierFillMaxWidth$default6, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$668448902$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
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

        static final Unit CoursesAdminForm$lambda$161$lambda$160$lambda$149$lambda$148(MutableState $title$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $title$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$161$lambda$160$lambda$151$lambda$150(MutableState $description$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $description$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$161$lambda$160$lambda$153$lambda$152(MutableState $duration$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $duration$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$161$lambda$160$lambda$155$lambda$154(MutableState $mode$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $mode$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$161$lambda$160$lambda$157$lambda$156(MutableState $level$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $level$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$161$lambda$160$lambda$159$lambda$158(MutableState $fee$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $fee$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$143(final CourseInfo $course, final SqlConnectRepository $repository, final Context $context, final MutableState $title$delegate, final MutableState $description$delegate, final MutableState $duration$delegate, final MutableState $mode$delegate, final MutableState $level$delegate, final MutableState $fee$delegate, final MutableState $showAddCourseDialog$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C488@22145L748,488@22128L826:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-119440362, $changed, -1, "com.example.sushikhasacademyapp.ui.CoursesAdminForm.<anonymous> (AdminDashboardDialog.kt:488)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 1181930978, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance($course) | $composer.changedInstance($repository) | $composer.changedInstance($context);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda73
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$143$lambda$142$lambda$141($course, $repository, $title$delegate, $description$delegate, $duration$delegate, $mode$delegate, $level$delegate, $fee$delegate, $context, $showAddCourseDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8716getLambda$1603941850$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$143$lambda$142$lambda$141(CourseInfo $course, SqlConnectRepository $repository, MutableState $title$delegate, MutableState $description$delegate, MutableState $duration$delegate, MutableState $mode$delegate, MutableState $level$delegate, MutableState $fee$delegate, final Context $context, final MutableState $showAddCourseDialog$delegate) {
            CourseInfo updated = CourseInfo.copy$default($course, null, CoursesAdminForm$lambda$121($title$delegate), CoursesAdminForm$lambda$124($description$delegate), CoursesAdminForm$lambda$127($duration$delegate), CoursesAdminForm$lambda$130($mode$delegate), CoursesAdminForm$lambda$133($level$delegate), CoursesAdminForm$lambda$136($fee$delegate), $course.getRating() > 0.0d ? $course.getRating() : 4.9d, !StringsKt.isBlank($course.getIconName()) ? $course.getIconName() : "Book", 1, null);
            $repository.saveCourse(updated, new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda72
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AdminDashboardDialogKt.CoursesAdminForm$lambda$143$lambda$142$lambda$141$lambda$140($context, $showAddCourseDialog$delegate, ((Boolean) obj).booleanValue());
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$143$lambda$142$lambda$141$lambda$140(Context $context, MutableState $showAddCourseDialog$delegate, boolean it) {
            CoursesAdminForm$lambda$102($showAddCourseDialog$delegate, false);
            Toast.makeText($context, "Course saved to SQL Database!", 0).show();
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$146(final MutableState $showAddCourseDialog$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C508@23037L31,508@23016L108:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-384087852, $changed, -1, "com.example.sushikhasacademyapp.ui.CoursesAdminForm.<anonymous> (AdminDashboardDialog.kt:508)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 258762963, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda38
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.CoursesAdminForm$lambda$146$lambda$145$lambda$144($showAddCourseDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1138250295$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit CoursesAdminForm$lambda$146$lambda$145$lambda$144(MutableState $showAddCourseDialog$delegate) {
            CoursesAdminForm$lambda$102($showAddCourseDialog$delegate, false);
            return Unit.INSTANCE;
        }

        public static final void BatchesAdminForm(final SqlConnectRepository repository, final List<BatchSchedule> batches, Composer $composer, final int $changed) {
            Composer $composer2;
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            SnapshotMutationPolicy snapshotMutationPolicy;
            final SqlConnectRepository repository2 = repository;
            Intrinsics.checkNotNullParameter(repository2, "repository");
            Intrinsics.checkNotNullParameter(batches, "batches");
            Composer $composer3 = $composer.startRestartGroup(-514704525);
            ComposerKt.sourceInformation($composer3, "C(BatchesAdminForm)N(repository,batches)521@23298L7,522@23336L34,523@23395L49,525@23450L3218:AdminDashboardDialog.kt#untpz1");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer3.changed(repository2) : $composer3.changedInstance(repository2) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= $composer3.changedInstance(batches) ? 32 : 16;
            }
            int $dirty2 = $dirty;
            if (!$composer3.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-514704525, $dirty2, -1, "com.example.sushikhasacademyapp.ui.BatchesAdminForm (AdminDashboardDialog.kt:520)");
                }
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = $composer3.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Context context = (Context) objConsume;
                ComposerKt.sourceInformationMarkerStart($composer3, -709803755, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer3.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue = objMutableStateOf$default;
                }
                final MutableState showAddBatchDialog$delegate = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -709801852, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue2 = $composer3.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default2);
                    objRememberedValue2 = objMutableStateOf$default2;
                }
                final MutableState editingBatch$delegate = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierFillMaxSize$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                Context context2 = context;
                int i = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function0 = constructor;
                    $composer3.createNode(function0);
                } else {
                    function0 = constructor;
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i3 = ((6 >> 6) & 112) | 6;
                ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 398577250, "C527@23532L104,526@23502L353,538@23865L41,543@24016L21,540@23916L2746:AdminDashboardDialog.kt#untpz1");
                ComposerKt.sourceInformationMarkerStart($composer3, -1649712751, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue3 = $composer3.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda93
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$170$lambda$169(editingBatch$delegate, showAddBatchDialog$delegate);
                        }
                    };
                    $composer3.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ButtonKt.Button((Function0) objRememberedValue3, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$204684889$app(), $composer3, 805306422, TypedValues.PositionType.TYPE_CURVE_FIT);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer3, 6);
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null);
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer3, modifierVerticalScroll$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Composer composer = $composer3;
                int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor2;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer3);
                MeasurePolicy measurePolicy = measurePolicyColumnMeasurePolicy2;
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = ((48 >> 6) & 112) | 6;
                Composer composer2 = $composer3;
                Composer composer3 = $composer3;
                ComposerKt.sourceInformationMarkerStart(composer2, -1544528507, "C:AdminDashboardDialog.kt#untpz1");
                composer2.startReplaceGroup(-1850938570);
                ComposerKt.sourceInformation(composer2, "*549@24314L11,549@24272L69,550@24360L2278,547@24168L2470");
                for (final BatchSchedule batchSchedule : batches) {
                    Modifier modifier = modifierMaterializeModifier2;
                    Composer composer4 = composer2;
                    final Context context3 = context2;
                    CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme(composer2, MaterialTheme.$stable).getSurfaceVariant(), 0L, 0L, 0L, composer4, CardDefaults.$stable << 12, 14), null, null, ComposableLambdaKt.rememberComposableLambda(1413316523, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda94
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182(batchSchedule, context3, repository, editingBatch$delegate, showAddBatchDialog$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, composer4, 54), composer4, 196614, 26);
                    $dirty2 = $dirty2;
                    composer = composer;
                    function1 = function1;
                    composer2 = composer4;
                    measurePolicy = measurePolicy;
                    context2 = context3;
                    modifierMaterializeModifier2 = modifier;
                    composer3 = composer3;
                }
                Composer composer5 = composer2;
                final Context context4 = context2;
                composer5.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer5);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (!BatchesAdminForm$lambda$164(showAddBatchDialog$delegate) || BatchesAdminForm$lambda$167(editingBatch$delegate) == null) {
                    $composer2 = $composer3;
                    repository2 = repository;
                    $composer2.startReplaceGroup(-552231281);
                } else {
                    $composer3.startReplaceGroup(-525615642);
                    ComposerKt.sourceInformation($composer3, "598@26786L46,599@26855L41,600@26917L39,601@26982L88,602@27091L39,603@27155L43,606@27252L30,628@28864L1106,649@30000L139,607@27304L67,608@27392L1442,605@27208L2941");
                    final BatchSchedule batch = BatchesAdminForm$lambda$167(editingBatch$delegate);
                    Intrinsics.checkNotNull(batch);
                    ComposerKt.sourceInformationMarkerStart($composer3, -709693343, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue4 = $composer3.rememberedValue();
                    if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        snapshotMutationPolicy = null;
                        Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(batch.getCourseTitle(), null, 2, null);
                        $composer3.updateRememberedValue(objMutableStateOf$default3);
                        objRememberedValue4 = objMutableStateOf$default3;
                    } else {
                        snapshotMutationPolicy = null;
                    }
                    final MutableState courseTitle$delegate = (MutableState) objRememberedValue4;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -709691140, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue5 = $composer3.rememberedValue();
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(batch.getTiming(), snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default4);
                        objRememberedValue5 = objMutableStateOf$default4;
                    }
                    final MutableState timing$delegate = (MutableState) objRememberedValue5;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -709689158, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue6 = $composer3.rememberedValue();
                    if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(batch.getDays(), snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default5);
                        objRememberedValue6 = objMutableStateOf$default5;
                    }
                    final MutableState days$delegate = (MutableState) objRememberedValue6;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -709687029, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue7 = $composer3.rememberedValue();
                    if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(batch.getSeatsLeft() > 0 ? String.valueOf(batch.getSeatsLeft()) : "", snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default6);
                        objRememberedValue7 = objMutableStateOf$default6;
                    }
                    final MutableState seatsLeft$delegate = (MutableState) objRememberedValue7;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -709683590, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue8 = $composer3.rememberedValue();
                    if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(batch.getMode(), snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default7);
                        objRememberedValue8 = objMutableStateOf$default7;
                    }
                    final MutableState mode$delegate = (MutableState) objRememberedValue8;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -709681538, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue9 = $composer3.rememberedValue();
                    if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                        Object objMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(batch.isOnline()), snapshotMutationPolicy, 2, snapshotMutationPolicy);
                        $composer3.updateRememberedValue(objMutableStateOf$default8);
                        objRememberedValue9 = objMutableStateOf$default8;
                    }
                    final MutableState isOnline$delegate = (MutableState) objRememberedValue9;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerStart($composer3, -709678447, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue10 = $composer3.rememberedValue();
                    if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda95
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.BatchesAdminForm$lambda$205$lambda$204(showAddBatchDialog$delegate);
                            }
                        };
                        $composer3.updateRememberedValue(obj);
                        objRememberedValue10 = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    repository2 = repository;
                    AndroidAlertDialog_androidKt.m2066AlertDialogOix01E0((Function0) objRememberedValue10, ComposableLambdaKt.rememberComposableLambda(-1845424698, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda96
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$209(batch, repository2, context4, courseTitle$delegate, timing$delegate, days$delegate, seatsLeft$delegate, isOnline$delegate, mode$delegate, showAddBatchDialog$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(-2110072188, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda97
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$212(showAddBatchDialog$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(1920247618, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda98
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$213(batch, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54), ComposableLambdaKt.rememberComposableLambda(1787923873, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda99
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$228(courseTitle$delegate, timing$delegate, days$delegate, seatsLeft$delegate, mode$delegate, isOnline$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    }, $composer3, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer3, 1772598, 0, 16276);
                    $composer2 = $composer3;
                }
                $composer2.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda100
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return AdminDashboardDialogKt.BatchesAdminForm$lambda$229(repository2, batches, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }

        private static final boolean BatchesAdminForm$lambda$164(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void BatchesAdminForm$lambda$165(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        private static final BatchSchedule BatchesAdminForm$lambda$167(MutableState<BatchSchedule> mutableState) {
            return mutableState.getValue();
        }

        static final Unit BatchesAdminForm$lambda$185$lambda$170$lambda$169(MutableState $editingBatch$delegate, MutableState $showAddBatchDialog$delegate) {
            $editingBatch$delegate.setValue(new BatchSchedule(null, null, null, null, 0, false, null, 127, null));
            BatchesAdminForm$lambda$165($showAddBatchDialog$delegate, true);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:41:0x04cf  */
        /* JADX WARN: Code duplicated, block: B:43:0x04d9  */
        /* JADX WARN: Code duplicated, block: B:44:0x04e0  */
        /* JADX WARN: Code duplicated, block: B:48:0x053b  */
        /* JADX WARN: Code duplicated, block: B:51:0x0546  */
        /* JADX WARN: Code duplicated, block: B:55:0x05a8  */
        static final Unit BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182(final BatchSchedule $batch, final Context $context, final SqlConnectRepository $repository, final MutableState $editingBatch$delegate, final MutableState $showAddBatchDialog$delegate, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Composer composer;
            Composer composer2;
            boolean zChangedInstance;
            Object objRememberedValue;
            Composer composer3;
            Composer composer4;
            boolean zChangedInstance2;
            Object objRememberedValue2;
            Composer composer5;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C551@24382L2238:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1413316523, $changed, -1, "com.example.sushikhasacademyapp.ui.BatchesAdminForm.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:551)");
                }
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12)), 0.0f, 1, null);
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i3 = ((438 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1832673687, "C558@24713L337,563@25076L1522:AdminDashboardDialog.kt#untpz1");
                Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierWeight$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i6 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1383255572, "C559@24828L10,559@24782L103,560@25003L10,560@24914L110:AdminDashboardDialog.kt#untpz1");
                TextKt.m3072TextNvy7gAk($batch.getCourseTitle(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 0, 0, 131070);
                TextKt.m3072TextNvy7gAk($batch.getTiming() + " • " + $batch.getDays() + " • " + $batch.getSeatsLeft() + " seats", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131070);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor3;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i8 = (i7 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i9 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1847678726, "C564@25131L467,564@25110L662,575@25823L142,575@25802L324,582@26177L254,582@26156L416:AdminDashboardDialog.kt#untpz1");
                ComposerKt.sourceInformationMarkerStart($composer, 633133798, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                boolean zChangedInstance3 = $composer.changedInstance($context) | $composer.changedInstance($batch);
                Object objRememberedValue3 = $composer.rememberedValue();
                if (zChangedInstance3) {
                    composer = $composer;
                } else {
                    composer = $composer;
                    if (objRememberedValue3 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    IconButtonKt.IconButton((Function0) objRememberedValue3, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8719getLambda$207235567$app(), composer, 1572864, 62);
                    composer2 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer2, 633155617, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    zChangedInstance = composer2.changedInstance($batch);
                    objRememberedValue = composer2.rememberedValue();
                    if (zChangedInstance) {
                        composer3 = composer2;
                    } else {
                        composer3 = composer2;
                        if (objRememberedValue != Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8709getLambda$1172838776$app(), composer3, 1572864, 62);
                        composer4 = composer3;
                        ComposerKt.sourceInformationMarkerStart(composer4, 633167057, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                        zChangedInstance2 = composer4.changedInstance($repository) | composer4.changedInstance($batch) | composer4.changedInstance($context);
                        objRememberedValue2 = composer4.rememberedValue();
                        if (zChangedInstance2) {
                            composer5 = composer4;
                        } else {
                            composer5 = composer4;
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8726getLambda$908817911$app(), composer5, 1572864, 62);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            $composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                        objRememberedValue2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda35
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$179$lambda$178($repository, $batch, $context);
                            }
                        };
                        composer4.updateRememberedValue(objRememberedValue2);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8726getLambda$908817911$app(), composer5, 1572864, 62);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                    objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda34
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$176$lambda$175($batch, $editingBatch$delegate, $showAddBatchDialog$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8709getLambda$1172838776$app(), composer3, 1572864, 62);
                    composer4 = composer3;
                    ComposerKt.sourceInformationMarkerStart(composer4, 633167057, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    zChangedInstance2 = composer4.changedInstance($repository) | composer4.changedInstance($batch) | composer4.changedInstance($context);
                    objRememberedValue2 = composer4.rememberedValue();
                    if (zChangedInstance2) {
                        composer5 = composer4;
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8726getLambda$908817911$app(), composer5, 1572864, 62);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
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
                        composer5 = composer4;
                    }
                    objRememberedValue2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda35
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$179$lambda$178($repository, $batch, $context);
                        }
                    };
                    composer4.updateRememberedValue(objRememberedValue2);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8726getLambda$908817911$app(), composer5, 1572864, 62);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
                objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda33
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$174$lambda$173($context, $batch);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue3);
                ComposerKt.sourceInformationMarkerEnd(composer);
                IconButtonKt.IconButton((Function0) objRememberedValue3, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8719getLambda$207235567$app(), composer, 1572864, 62);
                composer2 = composer;
                ComposerKt.sourceInformationMarkerStart(composer2, 633155617, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                zChangedInstance = composer2.changedInstance($batch);
                objRememberedValue = composer2.rememberedValue();
                if (zChangedInstance) {
                    composer3 = composer2;
                    if (objRememberedValue != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8709getLambda$1172838776$app(), composer3, 1572864, 62);
                    composer4 = composer3;
                    ComposerKt.sourceInformationMarkerStart(composer4, 633167057, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    zChangedInstance2 = composer4.changedInstance($repository) | composer4.changedInstance($batch) | composer4.changedInstance($context);
                    objRememberedValue2 = composer4.rememberedValue();
                    if (zChangedInstance2) {
                        composer5 = composer4;
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8726getLambda$908817911$app(), composer5, 1572864, 62);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
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
                        composer5 = composer4;
                    }
                    objRememberedValue2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda35
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$179$lambda$178($repository, $batch, $context);
                        }
                    };
                    composer4.updateRememberedValue(objRememberedValue2);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8726getLambda$908817911$app(), composer5, 1572864, 62);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
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
                    composer3 = composer2;
                }
                objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda34
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$176$lambda$175($batch, $editingBatch$delegate, $showAddBatchDialog$delegate);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8709getLambda$1172838776$app(), composer3, 1572864, 62);
                composer4 = composer3;
                ComposerKt.sourceInformationMarkerStart(composer4, 633167057, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                zChangedInstance2 = composer4.changedInstance($repository) | composer4.changedInstance($batch) | composer4.changedInstance($context);
                objRememberedValue2 = composer4.rememberedValue();
                if (zChangedInstance2) {
                    composer5 = composer4;
                    if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8726getLambda$908817911$app(), composer5, 1572864, 62);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
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
                    composer5 = composer4;
                }
                objRememberedValue2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$179$lambda$178($repository, $batch, $context);
                    }
                };
                composer4.updateRememberedValue(objRememberedValue2);
                ComposerKt.sourceInformationMarkerEnd(composer5);
                IconButtonKt.IconButton((Function0) objRememberedValue2, null, false, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8726getLambda$908817911$app(), composer5, 1572864, 62);
                ComposerKt.sourceInformationMarkerEnd(composer5);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
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

        static final Unit BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$174$lambda$173(Context $context, BatchSchedule $batch) {
            Intent intent = new Intent($context, (Class<?>) BatchDetailActivity.class);
            intent.putExtra("BATCH_TITLE", $batch.getCourseTitle());
            intent.putExtra("BATCH_TIMING", $batch.getTiming() + " (" + $batch.getDays() + ")");
            intent.putExtra("IS_ADMIN", true);
            $context.startActivity(intent);
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$176$lambda$175(BatchSchedule $batch, MutableState $editingBatch$delegate, MutableState $showAddBatchDialog$delegate) {
            $editingBatch$delegate.setValue($batch);
            BatchesAdminForm$lambda$165($showAddBatchDialog$delegate, true);
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$179$lambda$178(SqlConnectRepository $repository, BatchSchedule $batch, final Context $context) {
            $repository.deleteBatch($batch.getId(), new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AdminDashboardDialogKt.BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$179$lambda$178$lambda$177($context, ((Boolean) obj).booleanValue());
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$185$lambda$184$lambda$183$lambda$182$lambda$181$lambda$180$lambda$179$lambda$178$lambda$177(Context $context, boolean it) {
            Toast.makeText($context, "Batch deleted from SQL Database", 0).show();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String BatchesAdminForm$lambda$187(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String BatchesAdminForm$lambda$190(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String BatchesAdminForm$lambda$193(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String BatchesAdminForm$lambda$196(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String BatchesAdminForm$lambda$199(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final boolean BatchesAdminForm$lambda$202(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void BatchesAdminForm$lambda$203(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        static final Unit BatchesAdminForm$lambda$205$lambda$204(MutableState $showAddBatchDialog$delegate) {
            BatchesAdminForm$lambda$165($showAddBatchDialog$delegate, false);
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$213(BatchSchedule $batch, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C607@27306L63:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1920247618, $changed, -1, "com.example.sushikhasacademyapp.ui.BatchesAdminForm.<anonymous> (AdminDashboardDialog.kt:607)");
                }
                TextKt.m3072TextNvy7gAk(StringsKt.isBlank($batch.getId()) ? "Add New Batch" : "Edit Batch", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$228(final MutableState $courseTitle$delegate, final MutableState $timing$delegate, final MutableState $days$delegate, final MutableState $seatsLeft$delegate, final MutableState $mode$delegate, final MutableState $isOnline$delegate, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            ComposerKt.sourceInformation($composer, "C612@27538L21,609@27410L1410:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1787923873, $changed, -1, "com.example.sushikhasacademyapp.ui.BatchesAdminForm.<anonymous> (AdminDashboardDialog.kt:609)");
                }
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer, 0, 1), false, null, false, 14, null);
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
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
                ComposerKt.sourceInformationMarkerStart($composer, -1172260755, "C615@27726L20,615@27671L146,616@27888L15,616@27838L157,617@28064L13,617@28016L145,618@28235L18,618@28182L140,619@28391L13,619@28343L158,621@28523L279:AdminDashboardDialog.kt#untpz1");
                String strBatchesAdminForm$lambda$187 = BatchesAdminForm$lambda$187($courseTitle$delegate);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1209111775, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda21
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$228$lambda$227$lambda$215$lambda$214($courseTitle$delegate, (String) obj);
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strBatchesAdminForm$lambda$187, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8720getLambda$520979183$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                String strBatchesAdminForm$lambda$190 = BatchesAdminForm$lambda$190($timing$delegate);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1209116954, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda22
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$228$lambda$227$lambda$217$lambda$216($timing$delegate, (String) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strBatchesAdminForm$lambda$190, (Function1<? super String, Unit>) objRememberedValue2, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8708getLambda$11007814$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                String strBatchesAdminForm$lambda$193 = BatchesAdminForm$lambda$193($days$delegate);
                Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1209122584, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue3 = $composer.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$228$lambda$227$lambda$219$lambda$218($days$delegate, (String) obj3);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue3 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strBatchesAdminForm$lambda$193, (Function1<? super String, Unit>) objRememberedValue3, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8713getLambda$1346381543$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                String strBatchesAdminForm$lambda$196 = BatchesAdminForm$lambda$196($seatsLeft$delegate);
                Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1209128061, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue4 = $composer.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda24
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$228$lambda$227$lambda$221$lambda$220($seatsLeft$delegate, (String) obj4);
                        }
                    };
                    $composer.updateRememberedValue(obj3);
                    objRememberedValue4 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strBatchesAdminForm$lambda$196, (Function1<? super String, Unit>) objRememberedValue4, modifierFillMaxWidth$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1613212024$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                String strBatchesAdminForm$lambda$199 = BatchesAdminForm$lambda$199($mode$delegate);
                Modifier modifierFillMaxWidth$default5 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1209133048, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue5 = $composer.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    Object obj4 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda25
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj5) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$228$lambda$227$lambda$223$lambda$222($mode$delegate, (String) obj5);
                        }
                    };
                    $composer.updateRememberedValue(obj4);
                    objRememberedValue5 = obj4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                OutlinedTextFieldKt.OutlinedTextField(strBatchesAdminForm$lambda$199, (Function1<? super String, Unit>) objRememberedValue5, modifierFillMaxWidth$default5, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$277838295$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 0, 0, 8388536);
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor2;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i6 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1632806665, "C622@28648L17,622@28601L65,623@28691L39,624@28755L25:AdminDashboardDialog.kt#untpz1");
                boolean zBatchesAdminForm$lambda$202 = BatchesAdminForm$lambda$202($isOnline$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, -640064136, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue6 = $composer.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda26
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj5) {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$228$lambda$227$lambda$226$lambda$225$lambda$224($isOnline$delegate, ((Boolean) obj5).booleanValue());
                        }
                    };
                    $composer.updateRememberedValue(objRememberedValue6);
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                CheckboxKt.Checkbox(zBatchesAdminForm$lambda$202, (Function1) objRememberedValue6, null, false, null, null, $composer, 48, 60);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
                TextKt.m3072TextNvy7gAk("Online Live Class", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
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

        static final Unit BatchesAdminForm$lambda$228$lambda$227$lambda$215$lambda$214(MutableState $courseTitle$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $courseTitle$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$228$lambda$227$lambda$217$lambda$216(MutableState $timing$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $timing$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$228$lambda$227$lambda$219$lambda$218(MutableState $days$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $days$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$228$lambda$227$lambda$221$lambda$220(MutableState $seatsLeft$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $seatsLeft$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$228$lambda$227$lambda$223$lambda$222(MutableState $mode$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $mode$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$228$lambda$227$lambda$226$lambda$225$lambda$224(MutableState $isOnline$delegate, boolean it) {
            BatchesAdminForm$lambda$203($isOnline$delegate, it);
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$209(final BatchSchedule $batch, final SqlConnectRepository $repository, final Context $context, final MutableState $courseTitle$delegate, final MutableState $timing$delegate, final MutableState $days$delegate, final MutableState $seatsLeft$delegate, final MutableState $isOnline$delegate, final MutableState $mode$delegate, final MutableState $showAddBatchDialog$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C629@28899L997,629@28882L1074:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1845424698, $changed, -1, "com.example.sushikhasacademyapp.ui.BatchesAdminForm.<anonymous> (AdminDashboardDialog.kt:629)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 368360587, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                boolean zChangedInstance = $composer.changedInstance($batch) | $composer.changedInstance($repository) | $composer.changedInstance($context);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda32
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$209$lambda$208$lambda$207($batch, $repository, $courseTitle$delegate, $timing$delegate, $days$delegate, $seatsLeft$delegate, $isOnline$delegate, $mode$delegate, $context, $showAddBatchDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.Button((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$965041110$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$209$lambda$208$lambda$207(BatchSchedule $batch, SqlConnectRepository $repository, final MutableState $courseTitle$delegate, MutableState $timing$delegate, MutableState $days$delegate, MutableState $seatsLeft$delegate, MutableState $isOnline$delegate, MutableState $mode$delegate, final Context $context, final MutableState $showAddBatchDialog$delegate) {
            String strBatchesAdminForm$lambda$199;
            String strBatchesAdminForm$lambda$187 = BatchesAdminForm$lambda$187($courseTitle$delegate);
            String strBatchesAdminForm$lambda$190 = BatchesAdminForm$lambda$190($timing$delegate);
            String strBatchesAdminForm$lambda$193 = BatchesAdminForm$lambda$193($days$delegate);
            Integer intOrNull = StringsKt.toIntOrNull(BatchesAdminForm$lambda$196($seatsLeft$delegate));
            int iIntValue = intOrNull != null ? intOrNull.intValue() : 5;
            boolean z = true;
            if (!BatchesAdminForm$lambda$202($isOnline$delegate) && !StringsKt.contains((CharSequence) BatchesAdminForm$lambda$199($mode$delegate), (CharSequence) "Online", true)) {
                z = false;
            }
            boolean z2 = z;
            if (StringsKt.isBlank(BatchesAdminForm$lambda$199($mode$delegate))) {
                strBatchesAdminForm$lambda$199 = BatchesAdminForm$lambda$202($isOnline$delegate) ? "Live Online" : "Offline Classroom";
            } else {
                strBatchesAdminForm$lambda$199 = BatchesAdminForm$lambda$199($mode$delegate);
            }
            BatchSchedule updated = BatchSchedule.copy$default($batch, null, strBatchesAdminForm$lambda$187, strBatchesAdminForm$lambda$190, strBatchesAdminForm$lambda$193, iIntValue, z2, strBatchesAdminForm$lambda$199, 1, null);
            $repository.saveBatch(updated, new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda88
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AdminDashboardDialogKt.BatchesAdminForm$lambda$209$lambda$208$lambda$207$lambda$206($context, $showAddBatchDialog$delegate, $courseTitle$delegate, ((Boolean) obj).booleanValue());
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$209$lambda$208$lambda$207$lambda$206(Context $context, MutableState $showAddBatchDialog$delegate, MutableState $courseTitle$delegate, boolean it) {
            BatchesAdminForm$lambda$165($showAddBatchDialog$delegate, false);
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AdminDashboardDialogKt$BatchesAdminForm$3$1$1$1$1($courseTitle$delegate, null), 3, null);
            Toast.makeText($context, "Batch saved & Google Drive folder created!", 1).show();
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$212(final MutableState $showAddBatchDialog$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C650@30039L30,650@30018L107:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2110072188, $changed, -1, "com.example.sushikhasacademyapp.ui.BatchesAdminForm.<anonymous> (AdminDashboardDialog.kt:650)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, -554807678, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda46
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.BatchesAdminForm$lambda$212$lambda$211$lambda$210($showAddBatchDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8721getLambda$587734041$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit BatchesAdminForm$lambda$212$lambda$211$lambda$210(MutableState $showAddBatchDialog$delegate) {
            BatchesAdminForm$lambda$165($showAddBatchDialog$delegate, false);
            return Unit.INSTANCE;
        }

        public static final void ContactInfoAdminForm(final SqlConnectRepository repository, final ContactInfo currentContact, Function0<Unit> function0, Composer $composer, final int $changed) {
            Function0<ComposeUiNode> function1;
            Composer $composer2;
            final Function0<Unit> onSaved = function0;
            Intrinsics.checkNotNullParameter(repository, "repository");
            Intrinsics.checkNotNullParameter(currentContact, "currentContact");
            Intrinsics.checkNotNullParameter(onSaved, "onSaved");
            Composer $composer3 = $composer.startRestartGroup(1891619743);
            ComposerKt.sourceInformation($composer3, "C(ContactInfoAdminForm)N(repository,currentContact,onSaved)664@30315L51,665@30384L49,666@30451L49,671@30597L21,668@30506L1549:AdminDashboardDialog.kt#untpz1");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer3.changed(repository) : $composer3.changedInstance(repository) ? 4 : 2;
            }
            if (($changed & 48) == 0) {
                $dirty |= ($changed & 64) == 0 ? $composer3.changed(currentContact) : $composer3.changedInstance(currentContact) ? 32 : 16;
            }
            if (($changed & 384) == 0) {
                $dirty |= $composer3.changedInstance(onSaved) ? 256 : 128;
            }
            int $dirty2 = $dirty;
            if (!$composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
                $composer3 = $composer3;
                $composer3.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1891619743, $dirty2, -1, "com.example.sushikhasacademyapp.ui.ContactInfoAdminForm (AdminDashboardDialog.kt:663)");
                }
                ComposerKt.sourceInformationMarkerStart($composer3, 1075411858, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer3.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentContact.getAddress(), null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default);
                    objRememberedValue = objMutableStateOf$default;
                }
                final MutableState address$delegate = (MutableState) objRememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 1075414064, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue2 = $composer3.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentContact.getPhone(), null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default2);
                    objRememberedValue2 = objMutableStateOf$default2;
                }
                final MutableState phone$delegate = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, 1075416208, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue3 = $composer3.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(currentContact.getEmail(), null, 2, null);
                    $composer3.updateRememberedValue(objMutableStateOf$default3);
                    objRememberedValue3 = objMutableStateOf$default3;
                }
                final MutableState email$delegate = (MutableState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null);
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(10));
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer3, modifierVerticalScroll$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function1 = constructor;
                    $composer3.createNode(function1);
                } else {
                    function1 = constructor;
                    $composer3.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer3);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = (i >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i3 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1530891344, "C674@30754L10,674@30695L116,678@30897L16,676@30821L199,685@31104L14,683@31030L192,692@31306L14,690@31232L193,697@31435L41,700@31516L288,699@31486L563:AdminDashboardDialog.kt#untpz1");
                TextKt.m3072TextNvy7gAk("Edit Academy Contact Details", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer3, MaterialTheme.$stable).getTitleSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer3, 6, 0, 131070);
                String strContactInfoAdminForm$lambda$231 = ContactInfoAdminForm$lambda$231(address$delegate);
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -1296304455, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue4 = $composer3.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AdminDashboardDialogKt.ContactInfoAdminForm$lambda$248$lambda$240$lambda$239(address$delegate, (String) obj2);
                        }
                    };
                    $composer3.updateRememberedValue(obj);
                    objRememberedValue4 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(strContactInfoAdminForm$lambda$231, (Function1<? super String, Unit>) objRememberedValue4, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8717getLambda$1744855281$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 1573296, 0, 0, 8388536);
                String strContactInfoAdminForm$lambda$234 = ContactInfoAdminForm$lambda$234(phone$delegate);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -1296297833, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue5 = $composer3.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return AdminDashboardDialogKt.ContactInfoAdminForm$lambda$248$lambda$242$lambda$241(phone$delegate, (String) obj3);
                        }
                    };
                    $composer3.updateRememberedValue(obj2);
                    objRememberedValue5 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(strContactInfoAdminForm$lambda$234, (Function1<? super String, Unit>) objRememberedValue5, modifierFillMaxWidth$default2, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8718getLambda$1955601160$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 1573296, 0, 0, 8388536);
                String strContactInfoAdminForm$lambda$237 = ContactInfoAdminForm$lambda$237(email$delegate);
                Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -1296291369, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue6 = $composer3.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return AdminDashboardDialogKt.ContactInfoAdminForm$lambda$248$lambda$244$lambda$243(email$delegate, (String) obj4);
                        }
                    };
                    $composer3.updateRememberedValue(obj3);
                    objRememberedValue6 = obj3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                OutlinedTextFieldKt.OutlinedTextField(strContactInfoAdminForm$lambda$237, (Function1<? super String, Unit>) objRememberedValue6, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$155090199$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer3, 1573296, 0, 0, 8388536);
                SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer3, 6);
                ComposerKt.sourceInformationMarkerStart($composer3, -1296284375, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                boolean z = (($dirty2 & 14) == 4 || (($dirty2 & 8) != 0 && $composer3.changedInstance(repository))) | (($dirty2 & 896) == 256);
                Object objRememberedValue7 = $composer3.rememberedValue();
                if (z || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    onSaved = function0;
                    $composer2 = null;
                    objRememberedValue7 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.ContactInfoAdminForm$lambda$248$lambda$247$lambda$246(repository, address$delegate, phone$delegate, email$delegate, onSaved);
                        }
                    };
                    $composer3.updateRememberedValue(objRememberedValue7);
                } else {
                    onSaved = function0;
                    $composer2 = null;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ButtonKt.Button((Function0) objRememberedValue7, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, $composer2), false, null, null, null, null, null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$1947843705$app(), $composer3, 805306416, TypedValues.PositionType.TYPE_CURVE_FIT);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer3.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj4, Object obj5) {
                        return AdminDashboardDialogKt.ContactInfoAdminForm$lambda$249(repository, currentContact, onSaved, $changed, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                });
            }
        }

        private static final String ContactInfoAdminForm$lambda$231(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String ContactInfoAdminForm$lambda$234(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        private static final String ContactInfoAdminForm$lambda$237(MutableState<String> mutableState) {
            return mutableState.getValue();
        }

        static final Unit ContactInfoAdminForm$lambda$248$lambda$240$lambda$239(MutableState $address$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $address$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit ContactInfoAdminForm$lambda$248$lambda$242$lambda$241(MutableState $phone$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $phone$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit ContactInfoAdminForm$lambda$248$lambda$244$lambda$243(MutableState $email$delegate, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            $email$delegate.setValue(it);
            return Unit.INSTANCE;
        }

        static final Unit ContactInfoAdminForm$lambda$248$lambda$247$lambda$246(SqlConnectRepository $repository, MutableState $address$delegate, MutableState $phone$delegate, MutableState $email$delegate, final Function0 $onSaved) {
            ContactInfo updated = new ContactInfo(ContactInfoAdminForm$lambda$231($address$delegate), ContactInfoAdminForm$lambda$234($phone$delegate), ContactInfoAdminForm$lambda$237($email$delegate));
            $repository.updateContactInfo(updated, new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AdminDashboardDialogKt.ContactInfoAdminForm$lambda$248$lambda$247$lambda$246$lambda$245($onSaved, ((Boolean) obj).booleanValue());
                }
            });
            return Unit.INSTANCE;
        }

        static final Unit ContactInfoAdminForm$lambda$248$lambda$247$lambda$246$lambda$245(Function0 $onSaved, boolean it) {
            $onSaved.invoke();
            return Unit.INSTANCE;
        }

        public static final void StudentEnrollmentAdminForm(final List<CourseInfo> courses, final List<BatchSchedule> batches, Composer composer, final int i) {
            Composer composer2;
            boolean z;
            Function0<ComposeUiNode> function0;
            Intrinsics.checkNotNullParameter(courses, "courses");
            Intrinsics.checkNotNullParameter(batches, "batches");
            Composer composerStartRestartGroup = composer.startRestartGroup(-1931627614);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(StudentEnrollmentAdminForm)N(courses,batches)734@32446L7,735@32478L64,736@32572L33,738@32634L52,739@32722L34,742@32811L52,743@32891L52,746@33031L96,751@33215L102,755@33344L1216,755@33323L1237,782@34670L808,782@34634L844,803@35575L21,800@35484L15047:AdminDashboardDialog.kt#untpz1");
            int i2 = i;
            if ((i & 6) == 0) {
                i2 |= composerStartRestartGroup.changedInstance(courses) ? 4 : 2;
            }
            if ((i & 48) == 0) {
                i2 |= composerStartRestartGroup.changedInstance(batches) ? 32 : 16;
            }
            if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1931627614, i2, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm (AdminDashboardDialog.kt:733)");
                }
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object objConsume = composerStartRestartGroup.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                final Context context = (Context) objConsume;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868258238, "CC(remember):AdminDashboardDialog.kt#9igjgp");
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
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868255261, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    MutableState mutableStateMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default2);
                    objRememberedValue2 = mutableStateMutableStateOf$default2;
                }
                MutableState mutableState2 = (MutableState) objRememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868253258, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    MutableState mutableStateMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default3);
                    objRememberedValue3 = mutableStateMutableStateOf$default3;
                }
                final MutableState mutableState3 = (MutableState) objRememberedValue3;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868250460, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    MutableState mutableStateMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default4);
                    objRememberedValue4 = mutableStateMutableStateOf$default4;
                }
                final MutableState mutableState4 = (MutableState) objRememberedValue4;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868247594, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    MutableState mutableStateMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(SetsKt.emptySet(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default5);
                    objRememberedValue5 = mutableStateMutableStateOf$default5;
                }
                final MutableState mutableState5 = (MutableState) objRememberedValue5;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868245034, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    MutableState mutableStateMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(SetsKt.emptySet(), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(mutableStateMutableStateOf$default6);
                    objRememberedValue6 = mutableStateMutableStateOf$default6;
                }
                final MutableState mutableState6 = (MutableState) objRememberedValue6;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868240510, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                boolean zChanged = composerStartRestartGroup.changed(courses);
                Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    List<CourseInfo> list = courses;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((CourseInfo) it.next()).getTitle());
                        list = list;
                    }
                    ArrayList arrayList2 = arrayList;
                    ArrayList arrayList3 = new ArrayList();
                    for (Object obj : arrayList2) {
                        ArrayList arrayList4 = arrayList2;
                        if (!StringsKt.isBlank((String) obj)) {
                            arrayList3.add(obj);
                        }
                        arrayList2 = arrayList4;
                    }
                    List listDistinct = CollectionsKt.distinct(arrayList3);
                    composerStartRestartGroup.updateRememberedValue(listDistinct);
                    objRememberedValue7 = listDistinct;
                }
                final List list2 = (List) objRememberedValue7;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868234616, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                boolean zChanged2 = composerStartRestartGroup.changed(batches);
                Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    List<BatchSchedule> list3 = batches;
                    ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
                    Iterator<T> it2 = list3.iterator();
                    while (it2.hasNext()) {
                        arrayList5.add(((BatchSchedule) it2.next()).getCourseTitle());
                        list3 = list3;
                    }
                    ArrayList arrayList6 = arrayList5;
                    ArrayList arrayList7 = new ArrayList();
                    for (Object obj2 : arrayList6) {
                        ArrayList arrayList8 = arrayList6;
                        if (!StringsKt.isBlank((String) obj2)) {
                            arrayList7.add(obj2);
                        }
                        arrayList6 = arrayList8;
                    }
                    List listDistinct2 = CollectionsKt.distinct(arrayList7);
                    composerStartRestartGroup.updateRememberedValue(listDistinct2);
                    objRememberedValue8 = listDistinct2;
                }
                final List list4 = (List) objRememberedValue8;
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Unit unit = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868229374, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1 adminDashboardDialogKt$StudentEnrollmentAdminForm$1$1 = new AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1(mutableState2, mutableState, mutableState3, null);
                    composerStartRestartGroup.updateRememberedValue(adminDashboardDialogKt$StudentEnrollmentAdminForm$1$1);
                    objRememberedValue9 = adminDashboardDialogKt$StudentEnrollmentAdminForm$1$1;
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue9, composerStartRestartGroup, 6);
                FirestoreStudent firestoreStudentStudentEnrollmentAdminForm$lambda$257 = StudentEnrollmentAdminForm$lambda$257(mutableState3);
                String id = firestoreStudentStudentEnrollmentAdminForm$lambda$257 != null ? firestoreStudentStudentEnrollmentAdminForm$lambda$257.getId() : null;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -868187350, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1 adminDashboardDialogKt$StudentEnrollmentAdminForm$2$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (adminDashboardDialogKt$StudentEnrollmentAdminForm$2$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    adminDashboardDialogKt$StudentEnrollmentAdminForm$2$1RememberedValue = new AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1(mutableState3, mutableState5, mutableState6, null);
                    composerStartRestartGroup.updateRememberedValue(adminDashboardDialogKt$StudentEnrollmentAdminForm$2$1RememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                boolean z2 = z;
                EffectsKt.LaunchedEffect(id, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) adminDashboardDialogKt$StudentEnrollmentAdminForm$2$1RememberedValue, composerStartRestartGroup, z2 ? 1 : 0);
                Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(z2 ? 1 : 0, composerStartRestartGroup, z2 ? 1 : 0, 1), false, null, false, 14, null);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(14));
                composer2 = composerStartRestartGroup;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVerticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), composerStartRestartGroup, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierVerticalScroll$default);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i3 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    function0 = constructor;
                    composerStartRestartGroup.createNode(function0);
                } else {
                    function0 = constructor;
                    composerStartRestartGroup.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl(composerStartRestartGroup);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i4 = (i3 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i5 = ((48 >> 6) & 112) | 6;
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1710258983, "C808@35780L10,809@35873L11,806@35673L229,813@36081L10,814@36137L11,811@35911L264,817@36185L19:AdminDashboardDialog.kt#untpz1");
                TextKt.m3072TextNvy7gAk("Enroll Student in Multiple Courses & Batches", null, MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getPrimary(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composerStartRestartGroup, 6, 0, 131066);
                TextKt.m3072TextNvy7gAk("Select a registered student, then select one or multiple courses and batches to enroll them simultaneously.", null, MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getOnSurfaceVariant(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 6, 0, 131066);
                DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composerStartRestartGroup, 0, 7);
                if (StudentEnrollmentAdminForm$lambda$254(mutableState2)) {
                    composerStartRestartGroup.startReplaceGroup(1710361344);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "820@36251L82");
                    ProgressIndicatorKt.m2727CircularProgressIndicator4lLiAd8(columnScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally()), 0L, 0.0f, 0L, 0, 0.0f, composerStartRestartGroup, 0, 62);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (StudentEnrollmentAdminForm$lambda$251(mutableState).isEmpty()) {
                    composerStartRestartGroup.startReplaceGroup(1710514918);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "823@36477L11,823@36435L69,822@36391L556");
                    CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2144cardColorsro_MJ88(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, MaterialTheme.$stable).getErrorContainer(), 0L, 0L, 0L, composerStartRestartGroup, CardDefaults.$stable << 12, 14), null, null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$791030286$app(), composerStartRestartGroup, 196614, 26);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1711498672);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "837@37127L54,839@37248L1544,835@37016L1776,874@38896L46,877@39060L4226,873@38852L4434,958@43390L46,961@43554L4221,957@43346L4429,1040@47789L41,1043@47878L2121,1087@50173L342,1042@47844L2671");
                    boolean zStudentEnrollmentAdminForm$lambda$260 = StudentEnrollmentAdminForm$lambda$260(mutableState4);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -498987954, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue10 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda109
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj3) {
                                return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$277$lambda$276(mutableState4, ((Boolean) obj3).booleanValue());
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ExposedDropdownMenuKt.ExposedDropdownMenuBox(zStudentEnrollmentAdminForm$lambda$260, (Function1) objRememberedValue10, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ComposableLambdaKt.rememberComposableLambda(-2093476910, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda110
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$290(mutableState3, mutableState4, mutableState, (ExposedDropdownMenuBoxScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 3504, 0);
                    CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(ColorKt.Color(4294507260L), 0L, 0L, 0L, composerStartRestartGroup, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(1615588910, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda111
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306(list2, mutableState5, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 196614, 24);
                    CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(ColorKt.Color(4294507260L), 0L, 0L, 0L, composerStartRestartGroup, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(1054798053, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda112
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322(list4, mutableState6, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 196614, 24);
                    SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), composerStartRestartGroup, 6);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -498641855, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    boolean zChangedInstance = composerStartRestartGroup.changedInstance(context);
                    Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                        Function0 function1 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda113
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$327$lambda$326(context, mutableState3, mutableState5, mutableState6);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(function1);
                        objRememberedValue11 = function1;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ButtonKt.Button((Function0) objRememberedValue11, SizeKt.m851height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(52)), false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12)), null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(893388716, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda114
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj3, Object obj4, Object obj5) {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$328(mutableState3, (RowScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, 805306416, LogSeverity.ERROR_VALUE);
                    composerStartRestartGroup.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
            }
            ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda115
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj3, Object obj4) {
                        return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$330(courses, batches, i, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final List<FirestoreStudent> StudentEnrollmentAdminForm$lambda$251(MutableState<List<FirestoreStudent>> mutableState) {
            return mutableState.getValue();
        }

        private static final boolean StudentEnrollmentAdminForm$lambda$254(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void StudentEnrollmentAdminForm$lambda$255(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final FirestoreStudent StudentEnrollmentAdminForm$lambda$257(MutableState<FirestoreStudent> mutableState) {
            return mutableState.getValue();
        }

        private static final boolean StudentEnrollmentAdminForm$lambda$260(MutableState<Boolean> mutableState) {
            return mutableState.getValue().booleanValue();
        }

        private static final void StudentEnrollmentAdminForm$lambda$261(MutableState<Boolean> mutableState, boolean z) {
            mutableState.setValue(Boolean.valueOf(z));
        }

        private static final Set<String> StudentEnrollmentAdminForm$lambda$263(MutableState<Set<String>> mutableState) {
            return mutableState.getValue();
        }

        private static final Set<String> StudentEnrollmentAdminForm$lambda$266(MutableState<Set<String>> mutableState) {
            return mutableState.getValue();
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$277$lambda$276(MutableState $expandedStudentDropdown$delegate, boolean it) {
            StudentEnrollmentAdminForm$lambda$261($expandedStudentDropdown$delegate, !StudentEnrollmentAdminForm$lambda$260($expandedStudentDropdown$delegate));
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:26:0x0079  */
        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$290(final MutableState $selectedStudent$delegate, final MutableState $expandedStudentDropdown$delegate, final MutableState $studentsList$delegate, ExposedDropdownMenuBoxScope ExposedDropdownMenuBox, Composer $composer, int $changed) {
            String str;
            Intrinsics.checkNotNullParameter(ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
            ComposerKt.sourceInformation($composer, "C842@37426L2,845@37583L80,840@37266L589,853@37988L35,854@38042L736,851@37872L906:AdminDashboardDialog.kt#untpz1");
            int $dirty = $changed;
            if (($changed & 6) == 0) {
                $dirty |= ($changed & 8) == 0 ? $composer.changed(ExposedDropdownMenuBox) : $composer.changedInstance(ExposedDropdownMenuBox) ? 4 : 2;
            }
            if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2093476910, $dirty, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm.<anonymous>.<anonymous> (AdminDashboardDialog.kt:840)");
                }
                FirestoreStudent firestoreStudentStudentEnrollmentAdminForm$lambda$257 = StudentEnrollmentAdminForm$lambda$257($selectedStudent$delegate);
                if (firestoreStudentStudentEnrollmentAdminForm$lambda$257 != null) {
                    str = firestoreStudentStudentEnrollmentAdminForm$lambda$257.getUsername() + " (" + firestoreStudentStudentEnrollmentAdminForm$lambda$257.getEmail() + ")";
                    if (str == null) {
                        str = "Select Student";
                    }
                } else {
                    str = "Select Student";
                }
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(ExposedDropdownMenuBox.menuAnchor(Modifier.INSTANCE), 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer, -1786610284, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue = $composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda74
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$280$lambda$279((String) obj2);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                int $dirty2 = $dirty;
                OutlinedTextFieldKt.OutlinedTextField(str, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8715getLambda$1565848596$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(751920457, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda75
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$281($expandedStudentDropdown$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1127RoundedCornerShape0680j_4, (TextFieldColors) null, $composer, 806903856, 0, 0, 6290856);
                boolean zStudentEnrollmentAdminForm$lambda$260 = StudentEnrollmentAdminForm$lambda$260($expandedStudentDropdown$delegate);
                ComposerKt.sourceInformationMarkerStart($composer, -1786592267, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda76
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$283$lambda$282($expandedStudentDropdown$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    objRememberedValue2 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ExposedDropdownMenuBox.m2426ExposedDropdownMenuvNxi1II(zStudentEnrollmentAdminForm$lambda$260, (Function0) objRememberedValue2, null, null, false, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1296821104, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda77
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj3, Object obj4, Object obj5) {
                        return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$289($studentsList$delegate, $selectedStudent$delegate, $expandedStudentDropdown$delegate, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, $composer, 54), $composer, 48, (ExposedDropdownMenuBoxScope.$stable << 3) | 6 | (($dirty2 << 3) & 112), 1020);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$280$lambda$279(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$281(MutableState $expandedStudentDropdown$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C845@37613L48:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(751920457, $changed, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:845)");
                }
                ExposedDropdownMenuDefaults.INSTANCE.TrailingIcon(StudentEnrollmentAdminForm$lambda$260($expandedStudentDropdown$delegate), null, $composer, ExposedDropdownMenuDefaults.$stable << 6, 2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$283$lambda$282(MutableState $expandedStudentDropdown$delegate) {
            StudentEnrollmentAdminForm$lambda$261($expandedStudentDropdown$delegate, false);
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$289(MutableState $studentsList$delegate, final MutableState $selectedStudent$delegate, final MutableState $expandedStudentDropdown$delegate, ColumnScope ExposedDropdownMenu, Composer $composer, int $changed) {
            Composer composer = $composer;
            Intrinsics.checkNotNullParameter(ExposedDropdownMenu, "$this$ExposedDropdownMenu");
            ComposerKt.sourceInformation(composer, "C*857@38175L344,863@38559L153,856@38122L616:AdminDashboardDialog.kt#untpz1");
            if (!composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1296821104, $changed, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:855)");
                }
                for (final FirestoreStudent firestoreStudent : StudentEnrollmentAdminForm$lambda$251($studentsList$delegate)) {
                    ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(2136165391, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$289$lambda$288$lambda$285(firestoreStudent, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, composer, 54);
                    ComposerKt.sourceInformationMarkerStart(composer, -1650106888, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    boolean zChanged = composer.changed(firestoreStudent);
                    Object objRememberedValue = $composer.rememberedValue();
                    if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$289$lambda$288$lambda$287$lambda$286(firestoreStudent, $selectedStudent$delegate, $expandedStudentDropdown$delegate);
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
            }
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$289$lambda$288$lambda$285(FirestoreStudent $student, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            ComposerKt.sourceInformation($composer, "C858@38209L280:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2136165391, $changed, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:858)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i = ((((0 << 3) & 112) << 6) & 896) | 6;
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
                int i3 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1232423073, "C859@38254L52,860@38414L10,860@38343L112:AdminDashboardDialog.kt#untpz1");
                TextKt.m3072TextNvy7gAk($student.getUsername(), null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572864, 0, 262078);
                TextKt.m3072TextNvy7gAk($student.getEmail() + " • " + $student.getClassLevel(), null, Color.INSTANCE.m5127getGray0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 384, 0, 131066);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$290$lambda$289$lambda$288$lambda$287$lambda$286(FirestoreStudent $student, MutableState $selectedStudent$delegate, MutableState $expandedStudentDropdown$delegate) {
            $selectedStudent$delegate.setValue($student);
            StudentEnrollmentAdminForm$lambda$261($expandedStudentDropdown$delegate, false);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:51:0x06cb  */
        /* JADX WARN: Code duplicated, block: B:52:0x06d9  */
        /* JADX WARN: Code duplicated, block: B:56:0x0730  */
        /* JADX WARN: Code duplicated, block: B:58:0x0776  */
        /* JADX WARN: Code duplicated, block: B:61:0x0781  */
        /* JADX WARN: Code duplicated, block: B:65:0x083a  */
        /* JADX WARN: Code duplicated, block: B:68:0x0846  */
        /* JADX WARN: Code duplicated, block: B:69:0x084c  */
        /* JADX WARN: Code duplicated, block: B:72:0x08db  */
        /* JADX WARN: Code duplicated, block: B:75:0x08e6  */
        /* JADX WARN: Code duplicated, block: B:87:0x08f5 A[SYNTHETIC] */
        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$306(final List $allCoursesList, MutableState $selectedCourses$delegate, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            Composer composer;
            Composer composer2;
            Object objRememberedValue;
            List list;
            int i;
            Iterator it;
            Composer composer3;
            boolean zChanged;
            Object objRememberedValue2;
            Composer composer4;
            Composer composer5;
            Function0<ComposeUiNode> constructor;
            Function0<ComposeUiNode> function4;
            boolean zChanged2;
            Object objRememberedValue3;
            Composer composer6;
            final MutableState mutableState = $selectedCourses$delegate;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C878@39078L4194:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1615588910, $changed, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm.<anonymous>.<anonymous> (AdminDashboardDialog.kt:878)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14));
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i2 = ((((54 << 3) & 112) << 6) & 896) | 6;
                String str = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor2;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = (i2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i4 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 766808156, "C882@39251L1395:AdminDashboardDialog.kt#untpz1");
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                String str2 = "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo";
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i5 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor3;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = (i5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i7 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1774447552, "C887@39509L496,896@40089L11,898@40206L418,895@40030L594:AdminDashboardDialog.kt#untpz1");
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i8 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor4;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = (i8 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i10 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1568754899, "C888@39666L11,888@39591L95,889@39715L28,892@39893L10,890@39772L207:AdminDashboardDialog.kt#untpz1");
                IconKt.m2520Iconww6aTOc(SchoolKt.getSchool(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
                TextKt.m3072TextNvy7gAk("Select Courses (Multiple)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131070);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SurfaceKt.m2929SurfaceT9BRK9s(null, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(8)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimaryContainer(), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-690572125, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$293$lambda$292(mutableState, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), $composer, 12582912, 121);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if ($allCoursesList.isEmpty()) {
                    $composer.startReplaceGroup(768132134);
                    ComposerKt.sourceInformation($composer, "911@40895L10,912@40967L11,909@40724L360");
                    composer3 = $composer;
                    TextKt.m3072TextNvy7gAk("No admin-created courses found. Add courses in the 'Courses' tab to list them here.", PaddingKt.m821paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m7905constructorimpl(4), 1, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), composer3, 54, 0, 131064);
                    composer3.endReplaceGroup();
                } else {
                    Composer composer7 = $composer;
                    composer7.startReplaceGroup(768596576);
                    ComposerKt.sourceInformation(composer7, "916@41138L832,*937@42375L177,933@42139L1067");
                    Arrangement.Horizontal horizontalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
                    ComposerKt.sourceInformationMarkerStart(composer7, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier2 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalM689spacedBy0680j_4, Alignment.INSTANCE.getTop(), composer7, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer7, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer7, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = composer7.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer7, modifier2);
                    Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int i11 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer7, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer7.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer7.startReusableNode();
                    if (composer7.getInserting()) {
                        function3 = constructor5;
                        composer7.createNode(function3);
                    } else {
                        function3 = constructor5;
                        composer7.useNode();
                    }
                    Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl(composer7);
                    Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i12 = (i11 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer7, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    int i13 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer7, -1405581369, "C918@41278L64,917@41224L354,924@41661L32,923@41607L337:AdminDashboardDialog.kt#untpz1");
                    ComposerKt.sourceInformationMarkerStart(composer7, -1569360916, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    boolean zChangedInstance = composer7.changedInstance($allCoursesList);
                    Object objRememberedValue4 = composer7.rememberedValue();
                    if (!zChangedInstance) {
                        composer = composer7;
                        if (objRememberedValue4 != Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ButtonKt.TextButton((Function0) objRememberedValue4, null, false, null, null, null, null, PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0)), null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$319300841$app(), composer, 817889280, 382);
                        composer2 = composer;
                        ComposerKt.sourceInformationMarkerStart(composer2, -1569348692, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                        objRememberedValue = composer2.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$298$lambda$297$lambda$296(mutableState);
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0)), null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$4253792$app(), composer, 817889286, 382);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer7);
                        composer7.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer7);
                        ComposerKt.sourceInformationMarkerEnd(composer7);
                        ComposerKt.sourceInformationMarkerEnd(composer7);
                        list = $allCoursesList;
                        i = 0;
                        it = list.iterator();
                        while (it.hasNext()) {
                            final String str3 = (String) it.next();
                            Iterable iterable = list;
                            final boolean zContains = StudentEnrollmentAdminForm$lambda$263(mutableState).contains(str3);
                            Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                            int i14 = i;
                            Iterator it2 = it;
                            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            ComposerKt.sourceInformationMarkerStart(composer7, -1765500855, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                            zChanged = composer7.changed(zContains) | composer7.changed(str3);
                            Composer composer8 = composer7;
                            objRememberedValue2 = composer8.rememberedValue();
                            if (!zChanged) {
                                composer4 = composer7;
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                Modifier modifierM821paddingVpY3zN4$default = PaddingKt.m821paddingVpY3zN4$default(ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default2, false, null, null, null, (Function0) objRememberedValue2, 15, null), 0.0f, Dp.m7905constructorimpl(2), 1, null);
                                composer5 = composer4;
                                ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str2);
                                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                                CompositionLocalMap currentCompositionLocalMap5 = composer5.getCurrentCompositionLocalMap();
                                String str4 = str2;
                                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer5, modifierM821paddingVpY3zN4$default);
                                constructor = ComposeUiNode.INSTANCE.getConstructor();
                                int i15 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer5, -553112988, str);
                                if (!(composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer5.startReusableNode();
                                if (composer5.getInserting()) {
                                    function4 = constructor;
                                    composer5.createNode(function4);
                                } else {
                                    function4 = constructor;
                                    composer5.useNode();
                                }
                                Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl(composer5);
                                String str5 = str;
                                Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                                int i16 = (i15 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                                int i17 = ((384 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer5, -1842497986, "C944@42800L186,942@42679L341,948@43053L28,949@43154L10,949@43114L62:AdminDashboardDialog.kt#untpz1");
                                ComposerKt.sourceInformationMarkerStart(composer5, -59431858, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                                zChanged2 = composer5.changed(str3);
                                objRememberedValue3 = composer5.rememberedValue();
                                if (!zChanged2) {
                                    composer6 = composer5;
                                    if (objRememberedValue3 != Composer.INSTANCE.getEmpty()) {
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    CheckboxKt.Checkbox(zContains, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                                    Composer composer9 = composer6;
                                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer9, 6);
                                    TextKt.m3072TextNvy7gAk(str3, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer9, MaterialTheme.$stable).getBodyMedium(), composer9, 0, 0, 131070);
                                    ComposerKt.sourceInformationMarkerEnd(composer9);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    mutableState = $selectedCourses$delegate;
                                    list = iterable;
                                    it = it2;
                                    i = i14;
                                    str2 = str4;
                                    composer7 = composer4;
                                    str = str5;
                                } else {
                                    composer6 = composer5;
                                }
                                objRememberedValue3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda4
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$304$lambda$303$lambda$302$lambda$301(str3, mutableState, ((Boolean) obj).booleanValue());
                                    }
                                };
                                composer5.updateRememberedValue(objRememberedValue3);
                                ComposerKt.sourceInformationMarkerEnd(composer6);
                                CheckboxKt.Checkbox(zContains, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                                Composer composer10 = composer6;
                                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer10, 6);
                                TextKt.m3072TextNvy7gAk(str3, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer10, MaterialTheme.$stable).getBodyMedium(), composer10, 0, 0, 131070);
                                ComposerKt.sourceInformationMarkerEnd(composer10);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                mutableState = $selectedCourses$delegate;
                                list = iterable;
                                it = it2;
                                i = i14;
                                str2 = str4;
                                composer7 = composer4;
                                str = str5;
                            } else {
                                composer4 = composer7;
                            }
                            Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$304$lambda$300$lambda$299(zContains, str3, mutableState);
                                }
                            };
                            composer8.updateRememberedValue(obj);
                            objRememberedValue2 = obj;
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            Modifier modifierM821paddingVpY3zN4$default2 = PaddingKt.m821paddingVpY3zN4$default(ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default2, false, null, null, null, (Function0) objRememberedValue2, 15, null), 0.0f, Dp.m7905constructorimpl(2), 1, null);
                            composer5 = composer4;
                            ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str2);
                            MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                            CompositionLocalMap currentCompositionLocalMap6 = composer5.getCurrentCompositionLocalMap();
                            String str6 = str2;
                            Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composer5, modifierM821paddingVpY3zN4$default2);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int i18 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer5, -553112988, str);
                            if (!(composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer5.startReusableNode();
                            if (composer5.getInserting()) {
                                function4 = constructor;
                                composer5.createNode(function4);
                            } else {
                                function4 = constructor;
                                composer5.useNode();
                            }
                            Composer composerM4367constructorimpl6 = Updater.m4367constructorimpl(composer5);
                            String str7 = str;
                            Updater.m4375setimpl(composerM4367constructorimpl6, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl(composerM4367constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4371initimpl(composerM4367constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4373reconcileimpl(composerM4367constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4375setimpl(composerM4367constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                            int i19 = (i18 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                            int i110 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer5, -1842497986, "C944@42800L186,942@42679L341,948@43053L28,949@43154L10,949@43114L62:AdminDashboardDialog.kt#untpz1");
                            ComposerKt.sourceInformationMarkerStart(composer5, -59431858, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                            zChanged2 = composer5.changed(str3);
                            objRememberedValue3 = composer5.rememberedValue();
                            if (!zChanged2) {
                                composer6 = composer5;
                                if (objRememberedValue3 != Composer.INSTANCE.getEmpty()) {
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer6);
                                CheckboxKt.Checkbox(zContains, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                                Composer composer11 = composer6;
                                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer11, 6);
                                TextKt.m3072TextNvy7gAk(str3, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer11, MaterialTheme.$stable).getBodyMedium(), composer11, 0, 0, 131070);
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                mutableState = $selectedCourses$delegate;
                                list = iterable;
                                it = it2;
                                i = i14;
                                str2 = str6;
                                composer7 = composer4;
                                str = str7;
                            } else {
                                composer6 = composer5;
                            }
                            objRememberedValue3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$304$lambda$303$lambda$302$lambda$301(str3, mutableState, ((Boolean) obj2).booleanValue());
                                }
                            };
                            composer5.updateRememberedValue(objRememberedValue3);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            CheckboxKt.Checkbox(zContains, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                            Composer composer12 = composer6;
                            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer12, 6);
                            TextKt.m3072TextNvy7gAk(str3, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer12, MaterialTheme.$stable).getBodyMedium(), composer12, 0, 0, 131070);
                            ComposerKt.sourceInformationMarkerEnd(composer12);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            mutableState = $selectedCourses$delegate;
                            list = iterable;
                            it = it2;
                            i = i14;
                            str2 = str6;
                            composer7 = composer4;
                            str = str7;
                        }
                        composer3 = composer7;
                        composer3.endReplaceGroup();
                    } else {
                        composer = composer7;
                    }
                    objRememberedValue4 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$298$lambda$295$lambda$294($allCoursesList, mutableState);
                        }
                    };
                    composer7.updateRememberedValue(objRememberedValue4);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ButtonKt.TextButton((Function0) objRememberedValue4, null, false, null, null, null, null, PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0)), null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$319300841$app(), composer, 817889280, 382);
                    composer2 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer2, -1569348692, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    objRememberedValue = composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda2
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$298$lambda$297$lambda$296(mutableState);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0)), null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$4253792$app(), composer, 817889286, 382);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    composer7.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    list = $allCoursesList;
                    i = 0;
                    it = list.iterator();
                    while (it.hasNext()) {
                        final String str8 = (String) it.next();
                        Iterable iterable2 = list;
                        final boolean zContains2 = StudentEnrollmentAdminForm$lambda$263(mutableState).contains(str8);
                        Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                        int i111 = i;
                        Iterator it3 = it;
                        Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart(composer7, -1765500855, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                        zChanged = composer7.changed(zContains2) | composer7.changed(str8);
                        Composer composer13 = composer7;
                        objRememberedValue2 = composer13.rememberedValue();
                        if (!zChanged) {
                            composer4 = composer7;
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            Modifier modifierM821paddingVpY3zN4$default3 = PaddingKt.m821paddingVpY3zN4$default(ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default3, false, null, null, null, (Function0) objRememberedValue2, 15, null), 0.0f, Dp.m7905constructorimpl(2), 1, null);
                            composer5 = composer4;
                            ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str2);
                            MeasurePolicy measurePolicyRowMeasurePolicy6 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                            CompositionLocalMap currentCompositionLocalMap7 = composer5.getCurrentCompositionLocalMap();
                            String str9 = str2;
                            Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composer5, modifierM821paddingVpY3zN4$default3);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int i112 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer5, -553112988, str);
                            if (!(composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer5.startReusableNode();
                            if (composer5.getInserting()) {
                                function4 = constructor;
                                composer5.createNode(function4);
                            } else {
                                function4 = constructor;
                                composer5.useNode();
                            }
                            Composer composerM4367constructorimpl7 = Updater.m4367constructorimpl(composer5);
                            String str10 = str;
                            Updater.m4375setimpl(composerM4367constructorimpl7, measurePolicyRowMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl(composerM4367constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4371initimpl(composerM4367constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4373reconcileimpl(composerM4367constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4375setimpl(composerM4367constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                            int i113 = (i112 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                            int i114 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer5, -1842497986, "C944@42800L186,942@42679L341,948@43053L28,949@43154L10,949@43114L62:AdminDashboardDialog.kt#untpz1");
                            ComposerKt.sourceInformationMarkerStart(composer5, -59431858, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                            zChanged2 = composer5.changed(str8);
                            objRememberedValue3 = composer5.rememberedValue();
                            if (!zChanged2) {
                                composer6 = composer5;
                                if (objRememberedValue3 != Composer.INSTANCE.getEmpty()) {
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer6);
                                CheckboxKt.Checkbox(zContains2, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                                Composer composer14 = composer6;
                                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer14, 6);
                                TextKt.m3072TextNvy7gAk(str8, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer14, MaterialTheme.$stable).getBodyMedium(), composer14, 0, 0, 131070);
                                ComposerKt.sourceInformationMarkerEnd(composer14);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                mutableState = $selectedCourses$delegate;
                                list = iterable2;
                                it = it3;
                                i = i111;
                                str2 = str9;
                                composer7 = composer4;
                                str = str10;
                            } else {
                                composer6 = composer5;
                            }
                            objRememberedValue3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$304$lambda$303$lambda$302$lambda$301(str8, mutableState, ((Boolean) obj2).booleanValue());
                                }
                            };
                            composer5.updateRememberedValue(objRememberedValue3);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            CheckboxKt.Checkbox(zContains2, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                            Composer composer15 = composer6;
                            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer15, 6);
                            TextKt.m3072TextNvy7gAk(str8, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer15, MaterialTheme.$stable).getBodyMedium(), composer15, 0, 0, 131070);
                            ComposerKt.sourceInformationMarkerEnd(composer15);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            mutableState = $selectedCourses$delegate;
                            list = iterable2;
                            it = it3;
                            i = i111;
                            str2 = str9;
                            composer7 = composer4;
                            str = str10;
                        } else {
                            composer4 = composer7;
                        }
                        Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda3
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$304$lambda$300$lambda$299(zContains2, str8, mutableState);
                            }
                        };
                        composer13.updateRememberedValue(obj2);
                        objRememberedValue2 = obj2;
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        Modifier modifierM821paddingVpY3zN4$default4 = PaddingKt.m821paddingVpY3zN4$default(ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default3, false, null, null, null, (Function0) objRememberedValue2, 15, null), 0.0f, Dp.m7905constructorimpl(2), 1, null);
                        composer5 = composer4;
                        ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str2);
                        MeasurePolicy measurePolicyRowMeasurePolicy7 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                        CompositionLocalMap currentCompositionLocalMap8 = composer5.getCurrentCompositionLocalMap();
                        String str11 = str2;
                        Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composer5, modifierM821paddingVpY3zN4$default4);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int i115 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer5, -553112988, str);
                        if (!(composer5.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer5.startReusableNode();
                        if (composer5.getInserting()) {
                            function4 = constructor;
                            composer5.createNode(function4);
                        } else {
                            function4 = constructor;
                            composer5.useNode();
                        }
                        Composer composerM4367constructorimpl8 = Updater.m4367constructorimpl(composer5);
                        String str12 = str;
                        Updater.m4375setimpl(composerM4367constructorimpl8, measurePolicyRowMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl8, Integer.valueOf(iHashCode8), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl8, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl8, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                        int i116 = (i115 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance7 = RowScopeInstance.INSTANCE;
                        int i117 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer5, -1842497986, "C944@42800L186,942@42679L341,948@43053L28,949@43154L10,949@43114L62:AdminDashboardDialog.kt#untpz1");
                        ComposerKt.sourceInformationMarkerStart(composer5, -59431858, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                        zChanged2 = composer5.changed(str8);
                        objRememberedValue3 = composer5.rememberedValue();
                        if (!zChanged2) {
                            composer6 = composer5;
                            if (objRememberedValue3 != Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            CheckboxKt.Checkbox(zContains2, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                            Composer composer16 = composer6;
                            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer16, 6);
                            TextKt.m3072TextNvy7gAk(str8, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer16, MaterialTheme.$stable).getBodyMedium(), composer16, 0, 0, 131070);
                            ComposerKt.sourceInformationMarkerEnd(composer16);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            mutableState = $selectedCourses$delegate;
                            list = iterable2;
                            it = it3;
                            i = i111;
                            str2 = str11;
                            composer7 = composer4;
                            str = str12;
                        } else {
                            composer6 = composer5;
                        }
                        objRememberedValue3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj3) {
                                return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$304$lambda$303$lambda$302$lambda$301(str8, mutableState, ((Boolean) obj3).booleanValue());
                            }
                        };
                        composer5.updateRememberedValue(objRememberedValue3);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        CheckboxKt.Checkbox(zContains2, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                        Composer composer17 = composer6;
                        SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer17, 6);
                        TextKt.m3072TextNvy7gAk(str8, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer17, MaterialTheme.$stable).getBodyMedium(), composer17, 0, 0, 131070);
                        ComposerKt.sourceInformationMarkerEnd(composer17);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        mutableState = $selectedCourses$delegate;
                        list = iterable2;
                        it = it3;
                        i = i111;
                        str2 = str11;
                        composer7 = composer4;
                        str = str12;
                    }
                    composer3 = composer7;
                    composer3.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$293$lambda$292(MutableState $selectedCourses$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C902@40461L10,903@40538L11,899@40236L362:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-690572125, $changed, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:899)");
                }
                TextKt.m3072TextNvy7gAk(StudentEnrollmentAdminForm$lambda$263($selectedCourses$delegate).size() + " Selected", PaddingKt.m820paddingVpY3zN4(Modifier.INSTANCE, Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryFixed(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 48, 0, 131064);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$298$lambda$295$lambda$294(List $allCoursesList, MutableState $selectedCourses$delegate) {
            $selectedCourses$delegate.setValue(CollectionsKt.toSet(CollectionsKt.plus((Collection) $allCoursesList, (Iterable) StudentEnrollmentAdminForm$lambda$263($selectedCourses$delegate))));
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$298$lambda$297$lambda$296(MutableState $selectedCourses$delegate) {
            $selectedCourses$delegate.setValue(SetsKt.emptySet());
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$304$lambda$300$lambda$299(boolean $isChecked, String $courseTitle, MutableState $selectedCourses$delegate) {
            Set<String> setStudentEnrollmentAdminForm$lambda$263 = StudentEnrollmentAdminForm$lambda$263($selectedCourses$delegate);
            $selectedCourses$delegate.setValue($isChecked ? SetsKt.minus(setStudentEnrollmentAdminForm$lambda$263, $courseTitle) : SetsKt.plus(setStudentEnrollmentAdminForm$lambda$263, $courseTitle));
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$306$lambda$305$lambda$304$lambda$303$lambda$302$lambda$301(String $courseTitle, MutableState $selectedCourses$delegate, boolean checked) {
            Set<String> setStudentEnrollmentAdminForm$lambda$263 = StudentEnrollmentAdminForm$lambda$263($selectedCourses$delegate);
            $selectedCourses$delegate.setValue(checked ? SetsKt.plus(setStudentEnrollmentAdminForm$lambda$263, $courseTitle) : SetsKt.minus(setStudentEnrollmentAdminForm$lambda$263, $courseTitle));
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:51:0x06cb  */
        /* JADX WARN: Code duplicated, block: B:52:0x06d9  */
        /* JADX WARN: Code duplicated, block: B:56:0x0730  */
        /* JADX WARN: Code duplicated, block: B:58:0x0776  */
        /* JADX WARN: Code duplicated, block: B:61:0x0781  */
        /* JADX WARN: Code duplicated, block: B:65:0x083a  */
        /* JADX WARN: Code duplicated, block: B:68:0x0846  */
        /* JADX WARN: Code duplicated, block: B:69:0x084c  */
        /* JADX WARN: Code duplicated, block: B:72:0x08db  */
        /* JADX WARN: Code duplicated, block: B:75:0x08e6  */
        /* JADX WARN: Code duplicated, block: B:87:0x08f5 A[SYNTHETIC] */
        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$322(final List $allBatchesList, MutableState $selectedBatches$delegate, ColumnScope Card, Composer $composer, int $changed) {
            Function0<ComposeUiNode> function0;
            Function0<ComposeUiNode> function1;
            Function0<ComposeUiNode> function2;
            Function0<ComposeUiNode> function3;
            Composer composer;
            Composer composer2;
            Object objRememberedValue;
            List list;
            int i;
            Iterator it;
            Composer composer3;
            boolean zChanged;
            Object objRememberedValue2;
            Composer composer4;
            Composer composer5;
            Function0<ComposeUiNode> constructor;
            Function0<ComposeUiNode> function4;
            boolean zChanged2;
            Object objRememberedValue3;
            Composer composer6;
            final MutableState mutableState = $selectedBatches$delegate;
            Intrinsics.checkNotNullParameter(Card, "$this$Card");
            ComposerKt.sourceInformation($composer, "C962@43572L4189:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1054798053, $changed, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm.<anonymous>.<anonymous> (AdminDashboardDialog.kt:962)");
                }
                Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14));
                Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i2 = ((((54 << 3) & 112) << 6) & 896) | 6;
                String str = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function0 = constructor2;
                    $composer.createNode(function0);
                } else {
                    function0 = constructor2;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i3 = (i2 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i4 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, 1575211818, "C966@43745L1397:AdminDashboardDialog.kt#untpz1");
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                String str2 = "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo";
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i5 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function1 = constructor3;
                    $composer.createNode(function1);
                } else {
                    function1 = constructor3;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = (i5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i7 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -947961625, "C971@44003L498,980@44585L11,982@44702L418,979@44526L594:AdminDashboardDialog.kt#untpz1");
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i8 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                    function2 = constructor4;
                    $composer.createNode(function2);
                } else {
                    function2 = constructor4;
                    $composer.useNode();
                }
                Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl($composer);
                Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = (i8 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                int i10 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -748117382, "C972@44162L11,972@44085L97,973@44211L28,976@44389L10,974@44268L207:AdminDashboardDialog.kt#untpz1");
                IconKt.m2520Iconww6aTOc(ScheduleKt.getSchedule(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimary(), $composer, 48, 4);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), $composer, 6);
                TextKt.m3072TextNvy7gAk("Select Batches (Multiple)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131070);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SurfaceKt.m2929SurfaceT9BRK9s(null, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(8)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getPrimaryContainer(), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1037168154, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda27
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$309$lambda$308(mutableState, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, $composer, 54), $composer, 12582912, 121);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if ($allBatchesList.isEmpty()) {
                    $composer.startReplaceGroup(1576537935);
                    ComposerKt.sourceInformation($composer, "995@45391L10,996@45463L11,993@45220L360");
                    composer3 = $composer;
                    TextKt.m3072TextNvy7gAk("No admin-created batches found. Add batches in the 'Batches' tab to list them here.", PaddingKt.m821paddingVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m7905constructorimpl(4), 1, null), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getError(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), composer3, 54, 0, 131064);
                    composer3.endReplaceGroup();
                } else {
                    Composer composer7 = $composer;
                    composer7.startReplaceGroup(1577002160);
                    ComposerKt.sourceInformation(composer7, "1000@45634L832,*1021@46869L175,1017@46633L1062");
                    Arrangement.Horizontal horizontalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(8));
                    ComposerKt.sourceInformationMarkerStart(composer7, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier2 = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(horizontalM689spacedBy0680j_4, Alignment.INSTANCE.getTop(), composer7, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer7, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer7, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = composer7.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer7, modifier2);
                    Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int i11 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer7, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer7.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer7.startReusableNode();
                    if (composer7.getInserting()) {
                        function3 = constructor5;
                        composer7.createNode(function3);
                    } else {
                        function3 = constructor5;
                        composer7.useNode();
                    }
                    Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl(composer7);
                    Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i12 = (i11 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer7, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    int i13 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer7, 1353067824, "C1002@45774L64,1001@45720L354,1008@46157L32,1007@46103L337:AdminDashboardDialog.kt#untpz1");
                    ComposerKt.sourceInformationMarkerStart(composer7, 2121858403, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    boolean zChangedInstance = composer7.changedInstance($allBatchesList);
                    Object objRememberedValue4 = composer7.rememberedValue();
                    if (!zChangedInstance) {
                        composer = composer7;
                        if (objRememberedValue4 != Composer.INSTANCE.getEmpty()) {
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ButtonKt.TextButton((Function0) objRememberedValue4, null, false, null, null, null, null, PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0)), null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$862479584$app(), composer, 817889280, 382);
                        composer2 = composer;
                        ComposerKt.sourceInformationMarkerStart(composer2, 2121870627, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                        objRememberedValue = composer2.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda29
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$314$lambda$313$lambda$312(mutableState);
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0)), null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8711getLambda$1250023785$app(), composer, 817889286, 382);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer7);
                        composer7.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer7);
                        ComposerKt.sourceInformationMarkerEnd(composer7);
                        ComposerKt.sourceInformationMarkerEnd(composer7);
                        list = $allBatchesList;
                        i = 0;
                        it = list.iterator();
                        while (it.hasNext()) {
                            final String str3 = (String) it.next();
                            Iterable iterable = list;
                            final boolean zContains = StudentEnrollmentAdminForm$lambda$266(mutableState).contains(str3);
                            Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                            int i14 = i;
                            Iterator it2 = it;
                            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            ComposerKt.sourceInformationMarkerStart(composer7, -1693493634, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                            zChanged = composer7.changed(zContains) | composer7.changed(str3);
                            Composer composer8 = composer7;
                            objRememberedValue2 = composer8.rememberedValue();
                            if (!zChanged) {
                                composer4 = composer7;
                                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                Modifier modifierM821paddingVpY3zN4$default = PaddingKt.m821paddingVpY3zN4$default(ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default2, false, null, null, null, (Function0) objRememberedValue2, 15, null), 0.0f, Dp.m7905constructorimpl(2), 1, null);
                                composer5 = composer4;
                                ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str2);
                                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                                CompositionLocalMap currentCompositionLocalMap5 = composer5.getCurrentCompositionLocalMap();
                                String str4 = str2;
                                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer5, modifierM821paddingVpY3zN4$default);
                                constructor = ComposeUiNode.INSTANCE.getConstructor();
                                int i15 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer5, -553112988, str);
                                if (!(composer5.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer5.startReusableNode();
                                if (composer5.getInserting()) {
                                    function4 = constructor;
                                    composer5.createNode(function4);
                                } else {
                                    function4 = constructor;
                                    composer5.useNode();
                                }
                                Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl(composer5);
                                String str5 = str;
                                Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                                int i16 = (i15 >> 6) & 14;
                                ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                                int i17 = ((384 >> 6) & 112) | 6;
                                ComposerKt.sourceInformationMarkerStart(composer5, -1909146294, "C1028@47292L184,1026@47171L339,1032@47543L28,1033@47643L10,1033@47604L61:AdminDashboardDialog.kt#untpz1");
                                ComposerKt.sourceInformationMarkerStart(composer5, 1462438851, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                                zChanged2 = composer5.changed(str3);
                                objRememberedValue3 = composer5.rememberedValue();
                                if (!zChanged2) {
                                    composer6 = composer5;
                                    if (objRememberedValue3 != Composer.INSTANCE.getEmpty()) {
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    CheckboxKt.Checkbox(zContains, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                                    Composer composer9 = composer6;
                                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer9, 6);
                                    TextKt.m3072TextNvy7gAk(str3, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer9, MaterialTheme.$stable).getBodyMedium(), composer9, 0, 0, 131070);
                                    ComposerKt.sourceInformationMarkerEnd(composer9);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    mutableState = $selectedBatches$delegate;
                                    list = iterable;
                                    it = it2;
                                    i = i14;
                                    str2 = str4;
                                    composer7 = composer4;
                                    str = str5;
                                } else {
                                    composer6 = composer5;
                                }
                                objRememberedValue3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda31
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$320$lambda$319$lambda$318$lambda$317(str3, mutableState, ((Boolean) obj).booleanValue());
                                    }
                                };
                                composer5.updateRememberedValue(objRememberedValue3);
                                ComposerKt.sourceInformationMarkerEnd(composer6);
                                CheckboxKt.Checkbox(zContains, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                                Composer composer10 = composer6;
                                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer10, 6);
                                TextKt.m3072TextNvy7gAk(str3, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer10, MaterialTheme.$stable).getBodyMedium(), composer10, 0, 0, 131070);
                                ComposerKt.sourceInformationMarkerEnd(composer10);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                mutableState = $selectedBatches$delegate;
                                list = iterable;
                                it = it2;
                                i = i14;
                                str2 = str4;
                                composer7 = composer4;
                                str = str5;
                            } else {
                                composer4 = composer7;
                            }
                            Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda30
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$320$lambda$316$lambda$315(zContains, str3, mutableState);
                                }
                            };
                            composer8.updateRememberedValue(obj);
                            objRememberedValue2 = obj;
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            Modifier modifierM821paddingVpY3zN4$default2 = PaddingKt.m821paddingVpY3zN4$default(ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default2, false, null, null, null, (Function0) objRememberedValue2, 15, null), 0.0f, Dp.m7905constructorimpl(2), 1, null);
                            composer5 = composer4;
                            ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str2);
                            MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                            CompositionLocalMap currentCompositionLocalMap6 = composer5.getCurrentCompositionLocalMap();
                            String str6 = str2;
                            Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composer5, modifierM821paddingVpY3zN4$default2);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int i18 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer5, -553112988, str);
                            if (!(composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer5.startReusableNode();
                            if (composer5.getInserting()) {
                                function4 = constructor;
                                composer5.createNode(function4);
                            } else {
                                function4 = constructor;
                                composer5.useNode();
                            }
                            Composer composerM4367constructorimpl6 = Updater.m4367constructorimpl(composer5);
                            String str7 = str;
                            Updater.m4375setimpl(composerM4367constructorimpl6, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl(composerM4367constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4371initimpl(composerM4367constructorimpl6, Integer.valueOf(iHashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4373reconcileimpl(composerM4367constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4375setimpl(composerM4367constructorimpl6, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                            int i19 = (i18 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                            int i110 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer5, -1909146294, "C1028@47292L184,1026@47171L339,1032@47543L28,1033@47643L10,1033@47604L61:AdminDashboardDialog.kt#untpz1");
                            ComposerKt.sourceInformationMarkerStart(composer5, 1462438851, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                            zChanged2 = composer5.changed(str3);
                            objRememberedValue3 = composer5.rememberedValue();
                            if (!zChanged2) {
                                composer6 = composer5;
                                if (objRememberedValue3 != Composer.INSTANCE.getEmpty()) {
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer6);
                                CheckboxKt.Checkbox(zContains, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                                Composer composer11 = composer6;
                                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer11, 6);
                                TextKt.m3072TextNvy7gAk(str3, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer11, MaterialTheme.$stable).getBodyMedium(), composer11, 0, 0, 131070);
                                ComposerKt.sourceInformationMarkerEnd(composer11);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                mutableState = $selectedBatches$delegate;
                                list = iterable;
                                it = it2;
                                i = i14;
                                str2 = str6;
                                composer7 = composer4;
                                str = str7;
                            } else {
                                composer6 = composer5;
                            }
                            objRememberedValue3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda31
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$320$lambda$319$lambda$318$lambda$317(str3, mutableState, ((Boolean) obj2).booleanValue());
                                }
                            };
                            composer5.updateRememberedValue(objRememberedValue3);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            CheckboxKt.Checkbox(zContains, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                            Composer composer12 = composer6;
                            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer12, 6);
                            TextKt.m3072TextNvy7gAk(str3, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer12, MaterialTheme.$stable).getBodyMedium(), composer12, 0, 0, 131070);
                            ComposerKt.sourceInformationMarkerEnd(composer12);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            mutableState = $selectedBatches$delegate;
                            list = iterable;
                            it = it2;
                            i = i14;
                            str2 = str6;
                            composer7 = composer4;
                            str = str7;
                        }
                        composer3 = composer7;
                        composer3.endReplaceGroup();
                    } else {
                        composer = composer7;
                    }
                    objRememberedValue4 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda28
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$314$lambda$311$lambda$310($allBatchesList, mutableState);
                        }
                    };
                    composer7.updateRememberedValue(objRememberedValue4);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ButtonKt.TextButton((Function0) objRememberedValue4, null, false, null, null, null, null, PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0)), null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.getLambda$862479584$app(), composer, 817889280, 382);
                    composer2 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer2, 2121870627, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                    objRememberedValue = composer2.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda29
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$314$lambda$313$lambda$312(mutableState);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, PaddingKt.m812PaddingValues0680j_4(Dp.m7905constructorimpl(0)), null, ComposableSingletons$AdminDashboardDialogKt.INSTANCE.m8711getLambda$1250023785$app(), composer, 817889286, 382);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    composer7.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    list = $allBatchesList;
                    i = 0;
                    it = list.iterator();
                    while (it.hasNext()) {
                        final String str8 = (String) it.next();
                        Iterable iterable2 = list;
                        final boolean zContains2 = StudentEnrollmentAdminForm$lambda$266(mutableState).contains(str8);
                        Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                        int i111 = i;
                        Iterator it3 = it;
                        Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart(composer7, -1693493634, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                        zChanged = composer7.changed(zContains2) | composer7.changed(str8);
                        Composer composer13 = composer7;
                        objRememberedValue2 = composer13.rememberedValue();
                        if (!zChanged) {
                            composer4 = composer7;
                            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            Modifier modifierM821paddingVpY3zN4$default3 = PaddingKt.m821paddingVpY3zN4$default(ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default3, false, null, null, null, (Function0) objRememberedValue2, 15, null), 0.0f, Dp.m7905constructorimpl(2), 1, null);
                            composer5 = composer4;
                            ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str2);
                            MeasurePolicy measurePolicyRowMeasurePolicy6 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                            CompositionLocalMap currentCompositionLocalMap7 = composer5.getCurrentCompositionLocalMap();
                            String str9 = str2;
                            Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composer5, modifierM821paddingVpY3zN4$default3);
                            constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int i112 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer5, -553112988, str);
                            if (!(composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer5.startReusableNode();
                            if (composer5.getInserting()) {
                                function4 = constructor;
                                composer5.createNode(function4);
                            } else {
                                function4 = constructor;
                                composer5.useNode();
                            }
                            Composer composerM4367constructorimpl7 = Updater.m4367constructorimpl(composer5);
                            String str10 = str;
                            Updater.m4375setimpl(composerM4367constructorimpl7, measurePolicyRowMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4375setimpl(composerM4367constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4371initimpl(composerM4367constructorimpl7, Integer.valueOf(iHashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4373reconcileimpl(composerM4367constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4375setimpl(composerM4367constructorimpl7, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                            int i113 = (i112 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                            int i114 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer5, -1909146294, "C1028@47292L184,1026@47171L339,1032@47543L28,1033@47643L10,1033@47604L61:AdminDashboardDialog.kt#untpz1");
                            ComposerKt.sourceInformationMarkerStart(composer5, 1462438851, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                            zChanged2 = composer5.changed(str8);
                            objRememberedValue3 = composer5.rememberedValue();
                            if (!zChanged2) {
                                composer6 = composer5;
                                if (objRememberedValue3 != Composer.INSTANCE.getEmpty()) {
                                }
                                ComposerKt.sourceInformationMarkerEnd(composer6);
                                CheckboxKt.Checkbox(zContains2, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                                Composer composer14 = composer6;
                                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer14, 6);
                                TextKt.m3072TextNvy7gAk(str8, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer14, MaterialTheme.$stable).getBodyMedium(), composer14, 0, 0, 131070);
                                ComposerKt.sourceInformationMarkerEnd(composer14);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                composer5.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                ComposerKt.sourceInformationMarkerEnd(composer5);
                                mutableState = $selectedBatches$delegate;
                                list = iterable2;
                                it = it3;
                                i = i111;
                                str2 = str9;
                                composer7 = composer4;
                                str = str10;
                            } else {
                                composer6 = composer5;
                            }
                            objRememberedValue3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda31
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$320$lambda$319$lambda$318$lambda$317(str8, mutableState, ((Boolean) obj2).booleanValue());
                                }
                            };
                            composer5.updateRememberedValue(objRememberedValue3);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            CheckboxKt.Checkbox(zContains2, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                            Composer composer15 = composer6;
                            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer15, 6);
                            TextKt.m3072TextNvy7gAk(str8, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer15, MaterialTheme.$stable).getBodyMedium(), composer15, 0, 0, 131070);
                            ComposerKt.sourceInformationMarkerEnd(composer15);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            mutableState = $selectedBatches$delegate;
                            list = iterable2;
                            it = it3;
                            i = i111;
                            str2 = str9;
                            composer7 = composer4;
                            str = str10;
                        } else {
                            composer4 = composer7;
                        }
                        Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda30
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$320$lambda$316$lambda$315(zContains2, str8, mutableState);
                            }
                        };
                        composer13.updateRememberedValue(obj2);
                        objRememberedValue2 = obj2;
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        Modifier modifierM821paddingVpY3zN4$default4 = PaddingKt.m821paddingVpY3zN4$default(ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default3, false, null, null, null, (Function0) objRememberedValue2, 15, null), 0.0f, Dp.m7905constructorimpl(2), 1, null);
                        composer5 = composer4;
                        ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str2);
                        MeasurePolicy measurePolicyRowMeasurePolicy7 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, composer5, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                        CompositionLocalMap currentCompositionLocalMap8 = composer5.getCurrentCompositionLocalMap();
                        String str11 = str2;
                        Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composer5, modifierM821paddingVpY3zN4$default4);
                        constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int i115 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer5, -553112988, str);
                        if (!(composer5.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer5.startReusableNode();
                        if (composer5.getInserting()) {
                            function4 = constructor;
                            composer5.createNode(function4);
                        } else {
                            function4 = constructor;
                            composer5.useNode();
                        }
                        Composer composerM4367constructorimpl8 = Updater.m4367constructorimpl(composer5);
                        String str12 = str;
                        Updater.m4375setimpl(composerM4367constructorimpl8, measurePolicyRowMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4375setimpl(composerM4367constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4371initimpl(composerM4367constructorimpl8, Integer.valueOf(iHashCode8), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4373reconcileimpl(composerM4367constructorimpl8, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4375setimpl(composerM4367constructorimpl8, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                        int i116 = (i115 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance7 = RowScopeInstance.INSTANCE;
                        int i117 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer5, -1909146294, "C1028@47292L184,1026@47171L339,1032@47543L28,1033@47643L10,1033@47604L61:AdminDashboardDialog.kt#untpz1");
                        ComposerKt.sourceInformationMarkerStart(composer5, 1462438851, "CC(remember):AdminDashboardDialog.kt#9igjgp");
                        zChanged2 = composer5.changed(str8);
                        objRememberedValue3 = composer5.rememberedValue();
                        if (!zChanged2) {
                            composer6 = composer5;
                            if (objRememberedValue3 != Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            CheckboxKt.Checkbox(zContains2, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                            Composer composer16 = composer6;
                            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer16, 6);
                            TextKt.m3072TextNvy7gAk(str8, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer16, MaterialTheme.$stable).getBodyMedium(), composer16, 0, 0, 131070);
                            ComposerKt.sourceInformationMarkerEnd(composer16);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            mutableState = $selectedBatches$delegate;
                            list = iterable2;
                            it = it3;
                            i = i111;
                            str2 = str11;
                            composer7 = composer4;
                            str = str12;
                        } else {
                            composer6 = composer5;
                        }
                        objRememberedValue3 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda31
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj3) {
                                return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$320$lambda$319$lambda$318$lambda$317(str8, mutableState, ((Boolean) obj3).booleanValue());
                            }
                        };
                        composer5.updateRememberedValue(objRememberedValue3);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        CheckboxKt.Checkbox(zContains2, (Function1) objRememberedValue3, null, false, null, null, composer6, 0, 60);
                        Composer composer17 = composer6;
                        SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(6)), composer17, 6);
                        TextKt.m3072TextNvy7gAk(str8, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer17, MaterialTheme.$stable).getBodyMedium(), composer17, 0, 0, 131070);
                        ComposerKt.sourceInformationMarkerEnd(composer17);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        mutableState = $selectedBatches$delegate;
                        list = iterable2;
                        it = it3;
                        i = i111;
                        str2 = str11;
                        composer7 = composer4;
                        str = str12;
                    }
                    composer3 = composer7;
                    composer3.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$309$lambda$308(MutableState $selectedBatches$delegate, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "C986@44957L10,987@45034L11,983@44732L362:AdminDashboardDialog.kt#untpz1");
            if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1037168154, $changed, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AdminDashboardDialog.kt:983)");
                }
                TextKt.m3072TextNvy7gAk(StudentEnrollmentAdminForm$lambda$266($selectedBatches$delegate).size() + " Selected", PaddingKt.m820paddingVpY3zN4(Modifier.INSTANCE, Dp.m7905constructorimpl(8), Dp.m7905constructorimpl(2)), MaterialTheme.INSTANCE.getColorScheme($composer, MaterialTheme.$stable).getOnPrimaryFixed(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 48, 0, 131064);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                $composer.skipToGroupEnd();
            }
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$314$lambda$311$lambda$310(List $allBatchesList, MutableState $selectedBatches$delegate) {
            $selectedBatches$delegate.setValue(CollectionsKt.toSet(CollectionsKt.plus((Collection) $allBatchesList, (Iterable) StudentEnrollmentAdminForm$lambda$266($selectedBatches$delegate))));
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$314$lambda$313$lambda$312(MutableState $selectedBatches$delegate) {
            $selectedBatches$delegate.setValue(SetsKt.emptySet());
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$320$lambda$316$lambda$315(boolean $isChecked, String $batchTitle, MutableState $selectedBatches$delegate) {
            Set<String> setStudentEnrollmentAdminForm$lambda$266 = StudentEnrollmentAdminForm$lambda$266($selectedBatches$delegate);
            $selectedBatches$delegate.setValue($isChecked ? SetsKt.minus(setStudentEnrollmentAdminForm$lambda$266, $batchTitle) : SetsKt.plus(setStudentEnrollmentAdminForm$lambda$266, $batchTitle));
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$322$lambda$321$lambda$320$lambda$319$lambda$318$lambda$317(String $batchTitle, MutableState $selectedBatches$delegate, boolean checked) {
            Set<String> setStudentEnrollmentAdminForm$lambda$266 = StudentEnrollmentAdminForm$lambda$266($selectedBatches$delegate);
            $selectedBatches$delegate.setValue(checked ? SetsKt.plus(setStudentEnrollmentAdminForm$lambda$266, $batchTitle) : SetsKt.minus(setStudentEnrollmentAdminForm$lambda$266, $batchTitle));
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$327$lambda$326(Context $context, MutableState $selectedStudent$delegate, MutableState $selectedCourses$delegate, MutableState $selectedBatches$delegate) {
            final Context $context2;
            Exception e;
            final FirestoreStudent student = StudentEnrollmentAdminForm$lambda$257($selectedStudent$delegate);
            if (student == null) {
                Toast.makeText($context, "Please select a student", 0).show();
                return Unit.INSTANCE;
            }
            if (StudentEnrollmentAdminForm$lambda$263($selectedCourses$delegate).isEmpty() && StudentEnrollmentAdminForm$lambda$266($selectedBatches$delegate).isEmpty()) {
                Toast.makeText($context, "Please select at least one course or batch", 0).show();
                return Unit.INSTANCE;
            }
            try {
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
                final List courseList = CollectionsKt.toList(StudentEnrollmentAdminForm$lambda$263($selectedCourses$delegate));
                final List batchList = CollectionsKt.toList(StudentEnrollmentAdminForm$lambda$266($selectedBatches$delegate));
                final Map updates = MapsKt.mapOf(TuplesKt.to("enrolledCourses", courseList), TuplesKt.to("enrolledBatches", batchList));
                Task<Void> task = db.collection(Account.USERS).document(student.getId()).set(updates, SetOptions.merge());
                $context2 = $context;
                try {
                    final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda106
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$327$lambda$326$lambda$323(db, student, updates, $context2, courseList, batchList, (Void) obj);
                        }
                    };
                    Intrinsics.checkNotNull(task.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda107
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public final void onSuccess(Object obj) {
                            function1.invoke(obj);
                        }
                    }).addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$$ExternalSyntheticLambda108
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public final void onFailure(Exception exc) {
                            AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$329$lambda$327$lambda$326$lambda$325($context2, exc);
                        }
                    }));
                } catch (Exception e2) {
                    e = e2;
                    Toast.makeText($context2, "Error: " + e.getLocalizedMessage(), 0).show();
                }
            } catch (Exception e3) {
                $context2 = $context;
                e = e3;
            }
            return Unit.INSTANCE;
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$327$lambda$326$lambda$323(FirebaseFirestore $db, FirestoreStudent $student, Map $updates, Context $context, List $courseList, List $batchList, Void it) {
            $db.collection("Users").document($student.getId()).set($updates, SetOptions.merge());
            Toast.makeText($context, "Successfully enrolled " + $student.getUsername() + " into " + $courseList.size() + " courses and " + $batchList.size() + " batches!", 1).show();
            return Unit.INSTANCE;
        }

        static final void StudentEnrollmentAdminForm$lambda$329$lambda$327$lambda$326$lambda$325(Context $context, Exception e) {
            Intrinsics.checkNotNullParameter(e, "e");
            Toast.makeText($context, "Failed to enroll: " + e.getLocalizedMessage(), 0).show();
        }

        static final Unit StudentEnrollmentAdminForm$lambda$329$lambda$328(MutableState $selectedStudent$delegate, RowScope Button, Composer $composer, int $changed) {
            String username;
            Intrinsics.checkNotNullParameter(Button, "$this$Button");
            ComposerKt.sourceInformation($composer, "C1088@50191L56,1089@50264L39,1090@50320L181:AdminDashboardDialog.kt#untpz1");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(893388716, $changed, -1, "com.example.sushikhasacademyapp.ui.StudentEnrollmentAdminForm.<anonymous>.<anonymous> (AdminDashboardDialog.kt:1088)");
                }
                IconKt.m2520Iconww6aTOc(PersonAddKt.getPersonAdd(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
                SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
                FirestoreStudent firestoreStudentStudentEnrollmentAdminForm$lambda$257 = StudentEnrollmentAdminForm$lambda$257($selectedStudent$delegate);
                if (firestoreStudentStudentEnrollmentAdminForm$lambda$257 == null || (username = firestoreStudentStudentEnrollmentAdminForm$lambda$257.getUsername()) == null) {
                    username = "Student";
                }
                TextKt.m3072TextNvy7gAk("Enroll " + username + " in Selected Courses & Batches", null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572864, 0, 262078);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            return Unit.INSTANCE;
        }
    }
