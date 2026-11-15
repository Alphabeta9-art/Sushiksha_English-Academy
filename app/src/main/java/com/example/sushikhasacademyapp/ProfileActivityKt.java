package com.example.sushikhasacademyapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.AccountCircleKt;
import androidx.compose.material.icons.filled.CameraAltKt;
import androidx.compose.material.icons.filled.CheckCircleKt;
import androidx.compose.material.icons.filled.ChevronRightKt;
import androidx.compose.material.icons.filled.ScheduleKt;
import androidx.compose.material.icons.filled.VisibilityKt;
import androidx.compose.material.icons.filled.VisibilityOffKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.AndroidMenu_androidKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.DatePickerDialog_androidKt;
import androidx.compose.material3.DatePickerKt;
import androidx.compose.material3.DatePickerState;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.ExposedDropdownMenuBoxScope;
import androidx.compose.material3.ExposedDropdownMenuDefaults;
import androidx.compose.material3.ExposedDropdownMenuKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
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
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.PasswordVisualTransformation;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.C;
import coil.compose.SingletonAsyncImageKt;
import com.cloudinary.provisioning.Account;
import com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.logging.type.LogSeverity;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ProfileActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010 \n\u0000\u001a½\u0001\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\u008b\u0001\u0010\u0007\u001a\u0086\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000f\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00010\bH\u0007¢\u0006\u0002\u0010\u0011\u001a9\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0018¨\u0006\u0019²\u0006\n\u0010\u000b\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\u001a\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010\r\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\u001c\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\u001e\u001a\u00020\u0004X\u008a\u008e\u0002²\u0006\n\u0010\u001f\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\f\u0010 \u001a\u0004\u0018\u00010\u0004X\u008a\u008e\u0002²\u0006\u0016\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u008a\u008e\u0002²\u0006\n\u0010\"\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\u0010\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040$X\u008a\u008e\u0002²\u0006\u0010\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040$X\u008a\u008e\u0002"}, d2 = {"EditProfileDialog", "", "initialDetails", "", "", "onDismiss", "Lkotlin/Function0;", "onSave", "Lkotlin/Function6;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, HintConstants.AUTOFILL_HINT_USERNAME, "dob", "studentClass", "school", "exam", "newPass", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function6;Landroidx/compose/runtime/Composer;I)V", "ProfileScreen", "userEmail", "isAdmin", "", "onBack", "onSignOut", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app", "showDatePicker", "expandedClass", "expandedExam", "selectedExam", HintConstants.AUTOFILL_HINT_NEW_PASSWORD, "passwordVisible", "profileImagePath", "userDetails", "showEditDialog", "enrolledCourses", "", "enrolledBatches"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ProfileActivityKt {
    static final Unit EditProfileDialog$lambda$90(Map map, Function0 function0, Function6 function6, int i, Composer composer, int i2) {
        EditProfileDialog(map, function0, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ProfileScreen$lambda$149(String str, boolean z, Function0 function0, Function0 function1, int i, Composer composer, int i2) {
        ProfileScreen(str, z, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:75:0x028c  */
    public static final void EditProfileDialog(final Map<String, String> initialDetails, final Function0<Unit> onDismiss, final Function6<? super String, ? super String, ? super String, ? super String, ? super String, ? super String, Unit> onSave, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3;
        boolean z;
        String str;
        Intrinsics.checkNotNullParameter(initialDetails, "initialDetails");
        Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
        Intrinsics.checkNotNullParameter(onSave, "onSave");
        Composer $composer4 = $composer.startRestartGroup(1153746072);
        ComposerKt.sourceInformation($composer4, "C(EditProfileDialog)N(initialDetails,onDismiss,onSave)170@6827L61,171@6904L56,172@6987L34,180@7364L34,181@7423L58,191@7727L34,192@7786L117,194@7923L59,195@8006L31,196@8065L34,347@14609L325,357@14960L102,228@9370L5213,219@8919L6149:ProfileActivity.kt#t7vdsr");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer4.changedInstance(initialDetails) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer4.changedInstance(onDismiss) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer4.changedInstance(onSave) ? 256 : 128;
        }
        if ($composer4.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1153746072, $dirty, -1, "com.example.sushikhasacademyapp.EditProfileDialog (ProfileActivity.kt:169)");
            }
            ComposerKt.sourceInformationMarkerStart($composer4, 648422101, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue = $composer4.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                String str2 = initialDetails.get(HintConstants.AUTOFILL_HINT_USERNAME);
                if (str2 == null) {
                    str2 = "";
                }
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str2, null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState username$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, 648424560, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue2 = $composer4.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                String str3 = initialDetails.get("dob");
                if (str3 == null) {
                    str3 = "";
                }
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str3, null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue2 = objMutableStateOf$default2;
            }
            final MutableState dob$delegate = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, 648427194, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue3 = $composer4.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default3);
                objRememberedValue3 = objMutableStateOf$default3;
            }
            final MutableState showDatePicker$delegate = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            final List classOptions = CollectionsKt.listOf((Object[]) new String[]{"Class 1", "Class 2", "Class 3", "Class 4", "Class 5", "Class 6", "Class 7", "Class 8", "Class 9", "Class 10", "Class 11", "Class 12", "Undergraduate / College", "Post Graduate", "Spoken English & Fluency Program", "Advanced Professional English"});
            ComposerKt.sourceInformationMarkerStart($composer4, 648439258, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue4 = $composer4.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default4);
                objRememberedValue4 = objMutableStateOf$default4;
            }
            final MutableState expandedClass$delegate = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, 648441170, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue5 = $composer4.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                String str4 = initialDetails.get("class");
                if (str4 == null) {
                    str4 = "";
                }
                Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str4, null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default5);
                objRememberedValue5 = objMutableStateOf$default5;
            }
            final MutableState studentClass$delegate = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            final List examOptions = CollectionsKt.listOf((Object[]) new String[]{"None / General English", "IELTS Preparation", "TOEFL Preparation", "WBCS / UPSC English", "Banking & SSC English", "GRE / GMAT Verbal"});
            ComposerKt.sourceInformationMarkerStart($composer4, 648450874, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue6 = $composer4.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default6);
                objRememberedValue6 = objMutableStateOf$default6;
            }
            final MutableState expandedExam$delegate = (MutableState) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, 648452845, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue7 = $composer4.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                String str5 = initialDetails.get("exam");
                if (str5 != null) {
                    String str6 = str5;
                    if (StringsKt.isBlank(str6)) {
                        str6 = "None / General English";
                    }
                    str = str6;
                    if (str == null) {
                        str = r8;
                    }
                } else {
                    str = r8;
                }
                Object objMutableStateOf$default7 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str, null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default7);
                objRememberedValue7 = objMutableStateOf$default7;
            }
            final MutableState selectedExam$delegate = (MutableState) objRememberedValue7;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, 648457171, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue8 = $composer4.rememberedValue();
            if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                String str7 = initialDetails.get("school");
                if (str7 == null) {
                    str7 = "";
                }
                Object objMutableStateOf$default8 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str7, null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default8);
                objRememberedValue8 = objMutableStateOf$default8;
            }
            final MutableState school$delegate = (MutableState) objRememberedValue8;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, 648459799, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue9 = $composer4.rememberedValue();
            if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default9);
                objRememberedValue9 = objMutableStateOf$default9;
            }
            final MutableState newPassword$delegate = (MutableState) objRememberedValue9;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, 648461690, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue10 = $composer4.rememberedValue();
            if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer4.updateRememberedValue(objMutableStateOf$default10);
                objRememberedValue10 = objMutableStateOf$default10;
            }
            final MutableState passwordVisible$delegate = (MutableState) objRememberedValue10;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            if (EditProfileDialog$lambda$7(showDatePicker$delegate)) {
                $composer4.startReplaceGroup(-1372441194);
                ComposerKt.sourceInformation($composer4, "199@8157L25,201@8240L26,202@8296L412,211@8738L99,214@8848L59,200@8191L716");
                z = true;
                final DatePickerState datePickerState = DatePickerKt.m2355rememberDatePickerStateEU0dCGE(null, null, null, 0, null, $composer4, 0, 31);
                ComposerKt.sourceInformationMarkerStart($composer4, 648467282, "CC(remember):ProfileActivity.kt#9igjgp");
                Object objRememberedValue11 = $composer4.rememberedValue();
                if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ProfileActivityKt.EditProfileDialog$lambda$32$lambda$31(showDatePicker$delegate);
                        }
                    };
                    $composer4.updateRememberedValue(obj);
                    objRememberedValue11 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer4);
                DatePickerDialog_androidKt.m2347DatePickerDialogGmEhDVc((Function0) objRememberedValue11, ComposableLambdaKt.rememberComposableLambda(1456594379, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ProfileActivityKt.EditProfileDialog$lambda$36(datePickerState, showDatePicker$delegate, dob$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer4, 54), null, ComposableLambdaKt.rememberComposableLambda(1794881929, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ProfileActivityKt.EditProfileDialog$lambda$39(showDatePicker$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer4, 54), null, 0.0f, null, null, ComposableLambdaKt.rememberComposableLambda(1846275412, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj2, Object obj3, Object obj4) {
                        return ProfileActivityKt.EditProfileDialog$lambda$40(datePickerState, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                    }
                }, $composer4, 54), $composer4, 100666422, 244);
                $composer3 = $composer4;
            } else {
                $composer3 = $composer4;
                z = true;
                $composer3.startReplaceGroup(-1380526614);
            }
            $composer3.endReplaceGroup();
            boolean z2 = z;
            Composer $composer5 = $composer3;
            AndroidAlertDialog_androidKt.m2066AlertDialogOix01E0(onDismiss, ComposableLambdaKt.rememberComposableLambda(1537569504, z2, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ProfileActivityKt.EditProfileDialog$lambda$43(onSave, username$delegate, dob$delegate, studentClass$delegate, school$delegate, selectedExam$delegate, newPassword$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(589517538, z2, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ProfileActivityKt.EditProfileDialog$lambda$44(onDismiss, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), null, ComposableSingletons$ProfileActivityKt.INSTANCE.m8699getLambda$358534428$app(), ComposableLambdaKt.rememberComposableLambda(-832560411, z2, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89(username$delegate, dob$delegate, showDatePicker$delegate, expandedClass$delegate, studentClass$delegate, classOptions, expandedExam$delegate, selectedExam$delegate, examOptions, school$delegate, newPassword$delegate, passwordVisible$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer5, (($dirty >> 3) & 14) | 1772592, 0, 16276);
            $composer2 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ProfileActivityKt.EditProfileDialog$lambda$90(initialDetails, onDismiss, onSave, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final String EditProfileDialog$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String EditProfileDialog$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean EditProfileDialog$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void EditProfileDialog$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean EditProfileDialog$lambda$10(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void EditProfileDialog$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String EditProfileDialog$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean EditProfileDialog$lambda$16(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void EditProfileDialog$lambda$17(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String EditProfileDialog$lambda$20(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String EditProfileDialog$lambda$23(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String EditProfileDialog$lambda$26(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean EditProfileDialog$lambda$29(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void EditProfileDialog$lambda$30(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit EditProfileDialog$lambda$32$lambda$31(MutableState $showDatePicker$delegate) {
        EditProfileDialog$lambda$8($showDatePicker$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$36(final DatePickerState $datePickerState, final MutableState $showDatePicker$delegate, final MutableState $dob$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C203@8335L343,203@8314L380:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1456594379, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous> (ProfileActivity.kt:203)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -372138686, "CC(remember):ProfileActivity.kt#9igjgp");
            boolean zChanged = $composer.changed($datePickerState);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivityKt.EditProfileDialog$lambda$36$lambda$35$lambda$34($datePickerState, $showDatePicker$delegate, $dob$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ProfileActivityKt.INSTANCE.m8703getLambda$805760850$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$36$lambda$35$lambda$34(DatePickerState $datePickerState, MutableState $showDatePicker$delegate, MutableState $dob$delegate) {
        EditProfileDialog$lambda$8($showDatePicker$delegate, false);
        Long selectedDateMillis = $datePickerState.getSelectedDateMillis();
        if (selectedDateMillis != null) {
            String str = new SimpleDateFormat("dd / MM / yyyy", Locale.getDefault()).format(new Date(selectedDateMillis.longValue()));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            $dob$delegate.setValue(str);
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$39(final MutableState $showDatePicker$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C212@8777L26,212@8756L67:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1794881929, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous> (ProfileActivity.kt:212)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1695317949, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivityKt.EditProfileDialog$lambda$39$lambda$38$lambda$37($showDatePicker$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) objRememberedValue, null, false, null, null, null, null, null, null, ComposableSingletons$ProfileActivityKt.INSTANCE.m8701getLambda$467473300$app(), $composer, 805306374, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$39$lambda$38$lambda$37(MutableState $showDatePicker$delegate) {
        EditProfileDialog$lambda$8($showDatePicker$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$40(DatePickerState $datePickerState, ColumnScope DatePickerDialog, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(DatePickerDialog, "$this$DatePickerDialog");
        ComposerKt.sourceInformation($composer, "C215@8862L35:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1846275412, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous> (ProfileActivity.kt:215)");
            }
            DatePickerKt.DatePicker($datePickerState, null, null, null, null, null, false, null, $composer, 0, 254);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89(final MutableState $username$delegate, MutableState $dob$delegate, final MutableState $showDatePicker$delegate, final MutableState $expandedClass$delegate, final MutableState $studentClass$delegate, final List $classOptions, final MutableState $expandedExam$delegate, final MutableState $selectedExam$delegate, final List $examOptions, final MutableState $school$delegate, final MutableState $newPassword$delegate, final MutableState $passwordVisible$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        int i;
        Object obj;
        PasswordVisualTransformation passwordVisualTransformation;
        ComposerKt.sourceInformation($composer, "C232@9500L21,229@9384L5189:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-832560411, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous> (ProfileActivity.kt:229)");
            }
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer, 0, 1), false, null, false, 14, null);
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierVerticalScroll$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i4 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1574171359, "C237@9715L17,235@9622L273,255@10492L25,245@10001L3,248@10133L242,243@9913L622,260@10662L34,262@10771L1137,258@10553L1355,291@12034L32,293@12141L1130,289@11926L1345,322@13380L15,320@13289L272,330@13675L20,334@14062L384,328@13579L980:ProfileActivity.kt#t7vdsr");
            String strEditProfileDialog$lambda$1 = EditProfileDialog$lambda$1($username$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1196148212, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda40
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$46$lambda$45($username$delegate, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(strEditProfileDialog$lambda$1, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfileActivityKt.INSTANCE.m8696getLambda$1140717195$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 12582912, 0, 8257464);
            String strEditProfileDialog$lambda$4 = EditProfileDialog$lambda$4($dob$delegate);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1196123340, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda42
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$48$lambda$47($showDatePicker$delegate);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierM300clickableoSLSa3U$default = ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default2, false, null, null, null, (Function0) objRememberedValue2, 15, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1196139074, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue3 = $composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda43
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$50$lambda$49((String) obj4);
                    }
                };
                $composer.updateRememberedValue(obj3);
                objRememberedValue3 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(strEditProfileDialog$lambda$4, (Function1<? super String, Unit>) objRememberedValue3, modifierM300clickableoSLSa3U$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfileActivityKt.INSTANCE.getLambda$421663852$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1017807927, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$53($showDatePicker$delegate, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 806903856, 0, 0, 8388008);
            boolean zEditProfileDialog$lambda$10 = EditProfileDialog$lambda$10($expandedClass$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1196117891, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue4 = $composer.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda45
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj5) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$55$lambda$54($expandedClass$delegate, ((Boolean) obj5).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj4);
                objRememberedValue4 = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ExposedDropdownMenuKt.ExposedDropdownMenuBox(zEditProfileDialog$lambda$10, (Function1) objRememberedValue4, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ComposableLambdaKt.rememberComposableLambda(307342705, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda46
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj5, Object obj6, Object obj7) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$66($studentClass$delegate, $expandedClass$delegate, $classOptions, (ExposedDropdownMenuBoxScope) obj5, (Composer) obj6, ((Integer) obj7).intValue());
                }
            }, $composer, 54), $composer, 3504, 0);
            boolean zEditProfileDialog$lambda$16 = EditProfileDialog$lambda$16($expandedExam$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1196073989, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue5 = $composer.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object obj5 = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda47
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj6) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$68$lambda$67($expandedExam$delegate, ((Boolean) obj6).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj5);
                objRememberedValue5 = obj5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ExposedDropdownMenuKt.ExposedDropdownMenuBox(zEditProfileDialog$lambda$16, (Function1) objRememberedValue5, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ComposableLambdaKt.rememberComposableLambda(437823848, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda48
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj6, Object obj7, Object obj8) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$79($selectedExam$delegate, $expandedExam$delegate, $examOptions, (ExposedDropdownMenuBoxScope) obj6, (Composer) obj7, ((Integer) obj8).intValue());
                }
            }, $composer, 54), $composer, 3504, 0);
            String strEditProfileDialog$lambda$23 = EditProfileDialog$lambda$23($school$delegate);
            Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1196030934, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue6 = $composer.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object obj6 = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda49
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj7) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$81$lambda$80($school$delegate, (String) obj7);
                    }
                };
                $composer.updateRememberedValue(obj6);
                objRememberedValue6 = obj6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(strEditProfileDialog$lambda$23, (Function1<? super String, Unit>) objRememberedValue6, modifierFillMaxWidth$default3, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfileActivityKt.INSTANCE.getLambda$1912232557$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 12582912, 0, 8257464);
            String strEditProfileDialog$lambda$26 = EditProfileDialog$lambda$26($newPassword$delegate);
            if (EditProfileDialog$lambda$29($passwordVisible$delegate)) {
                passwordVisualTransformation = VisualTransformation.INSTANCE.getNone();
                i = 1;
                obj = null;
            } else {
                i = 1;
                obj = null;
                passwordVisualTransformation = new PasswordVisualTransformation((char) 0, 1, null);
            }
            VisualTransformation visualTransformation = passwordVisualTransformation;
            Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, i, obj);
            ComposerKt.sourceInformationMarkerStart($composer, -1196021489, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue7 = $composer.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object obj7 = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda50
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj8) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$83$lambda$82($newPassword$delegate, (String) obj8);
                    }
                };
                $composer.updateRememberedValue(obj7);
                objRememberedValue7 = obj7;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(strEditProfileDialog$lambda$26, (Function1<? super String, Unit>) objRememberedValue7, modifierFillMaxWidth$default4, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfileActivityKt.INSTANCE.m8705getLambda$892166034$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfileActivityKt.INSTANCE.getLambda$59665805$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(1963329483, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj8, Object obj9) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$87($passwordVisible$delegate, (Composer) obj8, ((Integer) obj9).intValue());
                }
            }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, visualTransformation, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 819462576, 12582912, 0, 8240440);
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

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$46$lambda$45(MutableState $username$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $username$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$50$lambda$49(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$53(final MutableState $showDatePicker$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C249@10180L25,249@10159L194:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1017807927, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:249)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1139127170, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda39
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$53$lambda$52$lambda$51($showDatePicker$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableSingletons$ProfileActivityKt.INSTANCE.getLambda$1929839851$app(), $composer, 1572870, 62);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$53$lambda$52$lambda$51(MutableState $showDatePicker$delegate) {
        EditProfileDialog$lambda$8($showDatePicker$delegate, true);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$48$lambda$47(MutableState $showDatePicker$delegate) {
        EditProfileDialog$lambda$8($showDatePicker$delegate, true);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$55$lambda$54(MutableState $expandedClass$delegate, boolean it) {
        EditProfileDialog$lambda$11($expandedClass$delegate, !EditProfileDialog$lambda$10($expandedClass$delegate));
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$66(final MutableState $studentClass$delegate, final MutableState $expandedClass$delegate, final List $classOptions, ExposedDropdownMenuBoxScope ExposedDropdownMenuBox, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
        ComposerKt.sourceInformation($composer, "C265@10898L2,268@11051L70,263@10793L481,275@11409L25,276@11457L433,273@11295L595:ProfileActivity.kt#t7vdsr");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer.changed(ExposedDropdownMenuBox) : $composer.changedInstance(ExposedDropdownMenuBox) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(307342705, $dirty, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:263)");
            }
            int $dirty2 = $dirty;
            String strEditProfileDialog$lambda$13 = EditProfileDialog$lambda$13($studentClass$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(ExposedDropdownMenuBox.menuAnchor(Modifier.INSTANCE), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -996399853, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$57$lambda$56((String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(strEditProfileDialog$lambda$13, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfileActivityKt.INSTANCE.getLambda$1091182347$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(2031608104, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$58($expandedClass$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 806903856, 0, 0, 8388008);
            boolean zEditProfileDialog$lambda$10 = EditProfileDialog$lambda$10($expandedClass$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -996383478, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$60$lambda$59($expandedClass$delegate);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ExposedDropdownMenuBox.m2426ExposedDropdownMenuvNxi1II(zEditProfileDialog$lambda$10, (Function0) objRememberedValue2, null, null, false, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1213464913, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$65($classOptions, $studentClass$delegate, $expandedClass$delegate, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
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

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$57$lambda$56(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$58(MutableState $expandedClass$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C268@11081L38:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2031608104, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:268)");
            }
            ExposedDropdownMenuDefaults.INSTANCE.TrailingIcon(EditProfileDialog$lambda$10($expandedClass$delegate), null, $composer, ExposedDropdownMenuDefaults.$stable << 6, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$60$lambda$59(MutableState $expandedClass$delegate) {
        EditProfileDialog$lambda$11($expandedClass$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$65(List $classOptions, final MutableState $studentClass$delegate, final MutableState $expandedClass$delegate, ColumnScope ExposedDropdownMenu, Composer $composer, int $changed) {
        Composer composer = $composer;
        Intrinsics.checkNotNullParameter(ExposedDropdownMenu, "$this$ExposedDropdownMenu");
        ComposerKt.sourceInformation(composer, "C*279@11601L16,280@11661L151,278@11544L298:ProfileActivity.kt#t7vdsr");
        if (!composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1213464913, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:277)");
            }
            Iterator it = $classOptions.iterator();
            while (it.hasNext()) {
                final String str = (String) it.next();
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(2084939055, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda51
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$65$lambda$64$lambda$61(str, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer, 54);
                ComposerKt.sourceInformationMarkerStart(composer, -1847765130, "CC(remember):ProfileActivity.kt#9igjgp");
                boolean zChanged = composer.changed(str);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda52
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$65$lambda$64$lambda$63$lambda$62(str, $studentClass$delegate, $expandedClass$delegate);
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

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$65$lambda$64$lambda$61(String $option, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C279@11603L12:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2084939055, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:279)");
            }
            TextKt.m3072TextNvy7gAk($option, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$66$lambda$65$lambda$64$lambda$63$lambda$62(String $option, MutableState $studentClass$delegate, MutableState $expandedClass$delegate) {
        $studentClass$delegate.setValue($option);
        EditProfileDialog$lambda$11($expandedClass$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$68$lambda$67(MutableState $expandedExam$delegate, boolean it) {
        EditProfileDialog$lambda$17($expandedExam$delegate, !EditProfileDialog$lambda$16($expandedExam$delegate));
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$79(final MutableState $selectedExam$delegate, final MutableState $expandedExam$delegate, final List $examOptions, ExposedDropdownMenuBoxScope ExposedDropdownMenuBox, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ExposedDropdownMenuBox, "$this$ExposedDropdownMenuBox");
        ComposerKt.sourceInformation($composer, "C296@12268L2,299@12419L69,294@12163L478,306@12775L24,307@12822L431,304@12662L591:ProfileActivity.kt#t7vdsr");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer.changed(ExposedDropdownMenuBox) : $composer.changedInstance(ExposedDropdownMenuBox) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(437823848, $dirty, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:294)");
            }
            int $dirty2 = $dirty;
            String strEditProfileDialog$lambda$20 = EditProfileDialog$lambda$20($selectedExam$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(ExposedDropdownMenuBox.menuAnchor(Modifier.INSTANCE), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, -158073014, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$70$lambda$69((String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(strEditProfileDialog$lambda$20, (Function1<? super String, Unit>) objRememberedValue, modifierFillMaxWidth$default, false, true, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$ProfileActivityKt.INSTANCE.getLambda$2088443010$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-400503969, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$71($expandedExam$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, false, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 806903856, 0, 0, 8388008);
            boolean zEditProfileDialog$lambda$16 = EditProfileDialog$lambda$16($expandedExam$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -158056768, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$73$lambda$72($expandedExam$delegate);
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue2 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ExposedDropdownMenuBox.m2426ExposedDropdownMenuvNxi1II(zEditProfileDialog$lambda$16, (Function0) objRememberedValue2, null, null, false, null, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-769416410, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$78($examOptions, $selectedExam$delegate, $expandedExam$delegate, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
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

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$70$lambda$69(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$71(MutableState $expandedExam$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C299@12449L37:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-400503969, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:299)");
            }
            ExposedDropdownMenuDefaults.INSTANCE.TrailingIcon(EditProfileDialog$lambda$16($expandedExam$delegate), null, $composer, ExposedDropdownMenuDefaults.$stable << 6, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$73$lambda$72(MutableState $expandedExam$delegate) {
        EditProfileDialog$lambda$17($expandedExam$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$78(List $examOptions, final MutableState $selectedExam$delegate, final MutableState $expandedExam$delegate, ColumnScope ExposedDropdownMenu, Composer $composer, int $changed) {
        Composer composer = $composer;
        Intrinsics.checkNotNullParameter(ExposedDropdownMenu, "$this$ExposedDropdownMenu");
        ComposerKt.sourceInformation(composer, "C*310@12965L16,311@13025L150,309@12908L297:ProfileActivity.kt#t7vdsr");
        if (!composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-769416410, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:308)");
            }
            Iterator it = $examOptions.iterator();
            while (it.hasNext()) {
                final String str = (String) it.next();
                ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-689067610, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda35
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$78$lambda$77$lambda$74(str, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composer, 54);
                ComposerKt.sourceInformationMarkerStart(composer, 2017584684, "CC(remember):ProfileActivity.kt#9igjgp");
                boolean zChanged = composer.changed(str);
                Object objRememberedValue = $composer.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda36
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$78$lambda$77$lambda$76$lambda$75(str, $selectedExam$delegate, $expandedExam$delegate);
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

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$78$lambda$77$lambda$74(String $option, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C310@12967L12:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-689067610, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:310)");
            }
            TextKt.m3072TextNvy7gAk($option, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$79$lambda$78$lambda$77$lambda$76$lambda$75(String $option, MutableState $selectedExam$delegate, MutableState $expandedExam$delegate) {
        $selectedExam$delegate.setValue($option);
        EditProfileDialog$lambda$17($expandedExam$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$81$lambda$80(MutableState $school$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $school$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$83$lambda$82(MutableState $newPassword$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $newPassword$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$87(final MutableState $passwordVisible$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C335@14109L38,335@14149L275,335@14088L336:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1963329483, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:335)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1268991761, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda54
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$87$lambda$85$lambda$84($passwordVisible$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            IconButtonKt.IconButton((Function0) objRememberedValue, null, false, null, null, null, ComposableLambdaKt.rememberComposableLambda(616009965, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda55
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ProfileActivityKt.EditProfileDialog$lambda$89$lambda$88$lambda$87$lambda$86($passwordVisible$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 1572870, 62);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$87$lambda$85$lambda$84(MutableState $passwordVisible$delegate) {
        EditProfileDialog$lambda$30($passwordVisible$delegate, !EditProfileDialog$lambda$29($passwordVisible$delegate));
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$89$lambda$88$lambda$87$lambda$86(MutableState $passwordVisible$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C336@14179L219:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(616009965, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:336)");
            }
            IconKt.m2520Iconww6aTOc(EditProfileDialog$lambda$29($passwordVisible$delegate) ? VisibilityKt.getVisibility(Icons.INSTANCE.getDefault()) : VisibilityOffKt.getVisibilityOff(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$43(final Function6 $onSave, final MutableState $username$delegate, final MutableState $dob$delegate, final MutableState $studentClass$delegate, final MutableState $school$delegate, final MutableState $selectedExam$delegate, final MutableState $newPassword$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C349@14657L110,352@14809L48,348@14623L301:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1537569504, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous> (ProfileActivity.kt:348)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 135545166, "CC(remember):ProfileActivity.kt#9igjgp");
            boolean zChanged = $composer.changed($onSave);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivityKt.EditProfileDialog$lambda$43$lambda$42$lambda$41($onSave, $username$delegate, $dob$delegate, $studentClass$delegate, $school$delegate, $selectedExam$delegate, $newPassword$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$ProfileActivityKt.INSTANCE.m8698getLambda$141017392$app(), $composer, C.ENCODING_PCM_32BIT, 494);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$43$lambda$42$lambda$41(Function6 $onSave, MutableState $username$delegate, MutableState $dob$delegate, MutableState $studentClass$delegate, MutableState $school$delegate, MutableState $selectedExam$delegate, MutableState $newPassword$delegate) {
        $onSave.invoke(EditProfileDialog$lambda$1($username$delegate), EditProfileDialog$lambda$4($dob$delegate), EditProfileDialog$lambda$13($studentClass$delegate), EditProfileDialog$lambda$23($school$delegate), EditProfileDialog$lambda$20($selectedExam$delegate), EditProfileDialog$lambda$26($newPassword$delegate));
        return Unit.INSTANCE;
    }

    static final Unit EditProfileDialog$lambda$44(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C358@14974L78:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(589517538, $changed, -1, "com.example.sushikhasacademyapp.EditProfileDialog.<anonymous> (ProfileActivity.kt:358)");
            }
            ButtonKt.TextButton($onDismiss, null, false, null, null, null, null, null, null, ComposableSingletons$ProfileActivityKt.INSTANCE.m8704getLambda$841542049$app(), $composer, C.ENCODING_PCM_32BIT, TypedValues.PositionType.TYPE_POSITION_TYPE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public static final void ProfileScreen(final String userEmail, final boolean isAdmin, final Function0<Unit> onBack, final Function0<Unit> onSignOut, Composer $composer, final int $changed) {
        Composer $composer2;
        Intrinsics.checkNotNullParameter(userEmail, "userEmail");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Intrinsics.checkNotNullParameter(onSignOut, "onSignOut");
        Composer $composer3 = $composer.startRestartGroup(565750333);
        ComposerKt.sourceInformation($composer3, "C(ProfileScreen)N(userEmail,isAdmin,onBack,onSignOut)373@15273L7,374@15309L82,377@15415L65,378@15507L34,380@15570L66,383@15664L66,387@15762L844,387@15736L870,409@16731L225,407@16634L322,457@18945L21,460@18999L516,472@19548L72,475@19627L12544,459@18972L13199:ProfileActivity.kt#t7vdsr");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(userEmail) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(isAdmin) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(onSignOut) ? 2048 : 1024;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(565750333, $dirty2, -1, "com.example.sushikhasacademyapp.ProfileScreen (ProfileActivity.kt:372)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer3, -1774768913, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ProfilePrefs.INSTANCE.getProfileImagePath(context), null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState profileImagePath$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1774765538, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ProfilePrefs.INSTANCE.getUserDetails(context), null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue2 = objMutableStateOf$default2;
            }
            final MutableState userDetails$delegate = (MutableState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1774762625, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default3);
                objRememberedValue3 = objMutableStateOf$default3;
            }
            final MutableState showEditDialog$delegate = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1774760577, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default4);
                objRememberedValue4 = objMutableStateOf$default4;
            }
            final MutableState enrolledCourses$delegate = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1774757569, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue5 = $composer3.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default5);
                objRememberedValue5 = objMutableStateOf$default5;
            }
            final MutableState enrolledBatches$delegate = (MutableState) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1774753655, "CC(remember):ProfileActivity.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            Object objRememberedValue6 = $composer3.rememberedValue();
            if (z || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new ProfileActivityKt$ProfileScreen$1$1(userEmail, enrolledCourses$delegate, enrolledBatches$delegate, null);
                $composer3.updateRememberedValue(obj);
                objRememberedValue6 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(userEmail, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue6, $composer3, $dirty2 & 14);
            ActivityResultContracts.GetContent getContent = new ActivityResultContracts.GetContent();
            ComposerKt.sourceInformationMarkerStart($composer3, -1774723266, "CC(remember):ProfileActivity.kt#9igjgp");
            boolean zChangedInstance = $composer3.changedInstance(context);
            Object objRememberedValue7 = $composer3.rememberedValue();
            if (zChangedInstance || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return ProfileActivityKt.ProfileScreen$lambda$108$lambda$107(context, profileImagePath$delegate, (Uri) obj2);
                    }
                };
                $composer3.updateRememberedValue(objRememberedValue7);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final ManagedActivityResultLauncher galleryLauncher = ActivityResultRegistryKt.rememberLauncherForActivityResult(getContent, (Function1) objRememberedValue7, $composer3, 0);
            if (ProfileScreen$lambda$98(showEditDialog$delegate)) {
                $composer3.startReplaceGroup(818455513);
                ComposerKt.sourceInformation($composer3, "421@17077L26,422@17126L1772,419@16992L1916");
                Map<String, String> mapProfileScreen$lambda$95 = ProfileScreen$lambda$95(userDetails$delegate);
                ComposerKt.sourceInformationMarkerStart($composer3, -1774712393, "CC(remember):ProfileActivity.kt#9igjgp");
                Object objRememberedValue8 = $composer3.rememberedValue();
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue8 = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ProfileActivityKt.ProfileScreen$lambda$110$lambda$109(showEditDialog$delegate);
                        }
                    };
                    $composer3.updateRememberedValue(objRememberedValue8);
                }
                Function0 function0 = (Function0) objRememberedValue8;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -1774709079, "CC(remember):ProfileActivity.kt#9igjgp");
                boolean zChangedInstance2 = $composer3.changedInstance(context) | (($dirty2 & 14) == 4);
                Object objRememberedValue9 = $composer3.rememberedValue();
                if (zChangedInstance2 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function6() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function6
                        public final Object invoke(Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
                            return ProfileActivityKt.ProfileScreen$lambda$115$lambda$114(context, userEmail, showEditDialog$delegate, userDetails$delegate, (String) obj3, (String) obj4, (String) obj5, (String) obj6, (String) obj7, (String) obj8);
                        }
                    };
                    $composer3.updateRememberedValue(obj2);
                    objRememberedValue9 = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EditProfileDialog(mapProfileScreen$lambda$95, function0, (Function6) objRememberedValue9, $composer3, 48);
            } else {
                $composer3.startReplaceGroup(801548485);
            }
            $composer3.endReplaceGroup();
            final ScrollState profileScrollState = ScrollKt.rememberScrollState(0, $composer3, 0, 1);
            $composer2 = $composer3;
            ScaffoldKt.m2765ScaffoldTvnljyQ(null, ComposableLambdaKt.rememberComposableLambda(-867629823, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return ProfileActivityKt.ProfileScreen$lambda$117(profileScrollState, onBack, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), null, null, ComposableLambdaKt.rememberComposableLambda(-322430076, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return ProfileActivityKt.ProfileScreen$lambda$118(profileScrollState, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), 0, 0L, 0L, null, ComposableLambdaKt.rememberComposableLambda(-1419019188, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda18
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return ProfileActivityKt.ProfileScreen$lambda$148(profileScrollState, onSignOut, galleryLauncher, userEmail, isAdmin, profileImagePath$delegate, userDetails$delegate, showEditDialog$delegate, enrolledCourses$delegate, enrolledBatches$delegate, context, (PaddingValues) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, $composer3, 54), $composer2, 805330992, 493);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return ProfileActivityKt.ProfileScreen$lambda$149(userEmail, isAdmin, onBack, onSignOut, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final String ProfileScreen$lambda$92(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final Map<String, String> ProfileScreen$lambda$95(MutableState<Map<String, String>> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean ProfileScreen$lambda$98(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void ProfileScreen$lambda$99(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final List<String> ProfileScreen$lambda$101(MutableState<List<String>> mutableState) {
        return mutableState.getValue();
    }

    private static final List<String> ProfileScreen$lambda$104(MutableState<List<String>> mutableState) {
        return mutableState.getValue();
    }

    static final Unit ProfileScreen$lambda$108$lambda$107(Context $context, MutableState $profileImagePath$delegate, Uri uri) {
        String savedPath;
        if (uri != null && (savedPath = ProfilePrefs.INSTANCE.saveProfileImage($context, uri)) != null) {
            $profileImagePath$delegate.setValue(savedPath);
        }
        return Unit.INSTANCE;
    }

    static final Unit ProfileScreen$lambda$110$lambda$109(MutableState $showEditDialog$delegate) {
        ProfileScreen$lambda$99($showEditDialog$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x00d5 A[Catch: Exception -> 0x00df, TRY_LEAVE, TryCatch #1 {Exception -> 0x00df, blocks: (B:15:0x00b3, B:17:0x00bd, B:19:0x00c4, B:21:0x00d5), top: B:37:0x00b3 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x00b3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static final Unit ProfileScreen$lambda$115$lambda$114(final Context $context, String $userEmail, MutableState $showEditDialog$delegate, MutableState $userDetails$delegate, String newUsername, String newDob, String newClass, String newSchool, String newExam, String newPass) {
        FirebaseUser currentUser;
        Task<Void> taskUpdatePassword;
        Task<Void> taskAddOnSuccessListener;
        Intrinsics.checkNotNullParameter(newUsername, "newUsername");
        Intrinsics.checkNotNullParameter(newDob, "newDob");
        Intrinsics.checkNotNullParameter(newClass, "newClass");
        Intrinsics.checkNotNullParameter(newSchool, "newSchool");
        Intrinsics.checkNotNullParameter(newExam, "newExam");
        Intrinsics.checkNotNullParameter(newPass, "newPass");
        ProfileScreen$lambda$99($showEditDialog$delegate, false);
        ProfilePrefs.INSTANCE.saveUserDetails($context, newUsername, newDob, newClass, newSchool, newExam);
        $userDetails$delegate.setValue(ProfilePrefs.INSTANCE.getUserDetails($context));
        try {
            FirebaseUser currentUser2 = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser2 == null || (strReplace$default = currentUser2.getUid()) == null) {
                try {
                    String strReplace$default = StringsKt.replace$default($userEmail, ".", "_", false, 4, (Object) null);
                    String uid = strReplace$default;
                    Pair[] pairArr = new Pair[6];
                    pairArr[0] = TuplesKt.to(HintConstants.AUTOFILL_HINT_USERNAME, newUsername);
                    try {
                        pairArr[1] = TuplesKt.to("email", $userEmail);
                        pairArr[2] = TuplesKt.to("dob", newDob);
                        pairArr[3] = TuplesKt.to("classLevel", newClass);
                        pairArr[4] = TuplesKt.to("school", newSchool);
                        pairArr[5] = TuplesKt.to("exam", newExam);
                        HashMap userData = MapsKt.hashMapOf(pairArr);
                        if (!StringsKt.isBlank(newPass)) {
                            try {
                                currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                if (currentUser != null && (taskUpdatePassword = currentUser.updatePassword(newPass)) != null) {
                                    final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda20
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj) {
                                            return ProfileActivityKt.ProfileScreen$lambda$115$lambda$114$lambda$111($context, (Void) obj);
                                        }
                                    };
                                    taskAddOnSuccessListener = taskUpdatePassword.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda21
                                        @Override // com.google.android.gms.tasks.OnSuccessListener
                                        public final void onSuccess(Object obj) {
                                            function1.invoke(obj);
                                        }
                                    });
                                    if (taskAddOnSuccessListener != null) {
                                        taskAddOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda22
                                            @Override // com.google.android.gms.tasks.OnFailureListener
                                            public final void onFailure(Exception exc) {
                                                ProfileActivityKt.ProfileScreen$lambda$115$lambda$114$lambda$113($context, exc);
                                            }
                                        });
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }
                        Intrinsics.checkNotNull(FirebaseFirestore.getInstance().collection(Account.USERS).document(uid).set(userData, SetOptions.merge()));
                    } catch (Exception e2) {
                    }
                } catch (Exception e3) {
                }
            } else {
                String uid2 = strReplace$default;
                Pair[] pairArr2 = new Pair[6];
                pairArr2[0] = TuplesKt.to(HintConstants.AUTOFILL_HINT_USERNAME, newUsername);
                pairArr2[1] = TuplesKt.to("email", $userEmail);
                pairArr2[2] = TuplesKt.to("dob", newDob);
                pairArr2[3] = TuplesKt.to("classLevel", newClass);
                pairArr2[4] = TuplesKt.to("school", newSchool);
                pairArr2[5] = TuplesKt.to("exam", newExam);
                HashMap userData2 = MapsKt.hashMapOf(pairArr2);
                if (!StringsKt.isBlank(newPass)) {
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser != null) {
                        final Function1 function2 = new Function1() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda20
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return ProfileActivityKt.ProfileScreen$lambda$115$lambda$114$lambda$111($context, (Void) obj);
                            }
                        };
                        taskAddOnSuccessListener = taskUpdatePassword.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda21
                            @Override // com.google.android.gms.tasks.OnSuccessListener
                            public final void onSuccess(Object obj) {
                                function2.invoke(obj);
                            }
                        });
                        if (taskAddOnSuccessListener != null) {
                            taskAddOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda22
                                @Override // com.google.android.gms.tasks.OnFailureListener
                                public final void onFailure(Exception exc) {
                                    ProfileActivityKt.ProfileScreen$lambda$115$lambda$114$lambda$113($context, exc);
                                }
                            });
                        }
                    }
                }
                Intrinsics.checkNotNull(FirebaseFirestore.getInstance().collection(Account.USERS).document(uid2).set(userData2, SetOptions.merge()));
            }
        } catch (Exception e4) {
        }
        Toast.makeText($context, "Profile details updated successfully!", 0).show();
        return Unit.INSTANCE;
    }

    static final Unit ProfileScreen$lambda$115$lambda$114$lambda$111(Context $context, Void it) {
        Toast.makeText($context, "Password updated successfully in Firebase Auth!", 0).show();
        return Unit.INSTANCE;
    }

    static final void ProfileScreen$lambda$115$lambda$114$lambda$113(Context $context, Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Toast.makeText($context, "Password update failed: " + e.getLocalizedMessage(), 1).show();
    }

    static final Unit ProfileScreen$lambda$117(ScrollState $profileScrollState, final Function0 $onBack, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C469@19440L51,464@19208L187,461@19013L492:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-867629823, $changed, -1, "com.example.sushikhasacademyapp.ProfileScreen.<anonymous> (ProfileActivity.kt:461)");
            }
            AppBarKt.m2090TopAppBarGHTll3U(ComposableSingletons$ProfileActivityKt.INSTANCE.m8700getLambda$402625347$app(), MotionUtilsKt.m8744elevateOnScrollwH6b6FI$default(Modifier.INSTANCE, $profileScrollState, 0.0f, 2, null), ComposableLambdaKt.rememberComposableLambda(203149435, true, new Function2() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda53
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ProfileActivityKt.ProfileScreen$lambda$117$lambda$116($onBack, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), null, 0.0f, null, TopAppBarDefaults.INSTANCE.m3287topAppBarColors5tl4gsc(ColorKt.Color(4294047225L), 0L, 0L, 0L, 0L, 0L, $composer, (TopAppBarDefaults.$stable << 18) | 6, 62), null, $composer, 390, 184);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit ProfileScreen$lambda$117$lambda$116(Function0 $onBack, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C465@19230L147:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(203149435, $changed, -1, "com.example.sushikhasacademyapp.ProfileScreen.<anonymous>.<anonymous> (ProfileActivity.kt:465)");
            }
            IconButtonKt.IconButton($onBack, null, false, null, null, null, ComposableSingletons$ProfileActivityKt.INSTANCE.m8702getLambda$580614115$app(), $composer, 1572864, 62);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit ProfileScreen$lambda$118(ScrollState $profileScrollState, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C473@19562L48:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-322430076, $changed, -1, "com.example.sushikhasacademyapp.ProfileScreen.<anonymous> (ProfileActivity.kt:473)");
            }
            MotionUtilsKt.ScrollToTopFAB($profileScrollState, (Modifier) null, 0, (CoroutineScope) null, $composer, 0, 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit ProfileScreen$lambda$148(ScrollState $profileScrollState, Function0 $onSignOut, final ManagedActivityResultLauncher $galleryLauncher, final String $userEmail, final boolean $isAdmin, final MutableState $profileImagePath$delegate, final MutableState $userDetails$delegate, final MutableState $showEditDialog$delegate, final MutableState $enrolledCourses$delegate, final MutableState $enrolledBatches$delegate, final Context $context, PaddingValues padding, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(padding, "padding");
        ComposerKt.sourceInformation($composer, "CN(padding)476@19648L12517:ProfileActivity.kt#t7vdsr");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(padding) ? 4 : 2;
        }
        if ($composer.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1419019188, $dirty, -1, "com.example.sushikhasacademyapp.ProfileScreen.<anonymous> (ProfileActivity.kt:476)");
            }
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(PaddingKt.m820paddingVpY3zN4(PaddingKt.padding(BackgroundKt.background$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Brush.Companion.m5052verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(ColorKt.Color(4294047225L)), Color.m5087boximpl(ColorKt.Color(4293060848L))}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), padding), Dp.m7905constructorimpl(20), Dp.m7905constructorimpl(12)), $profileScrollState, false, null, false, 14, null);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(20));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, centerHorizontally, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierVerticalScroll$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                $composer.createNode(constructor);
            } else {
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
            int i3 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1256595446, "C493@20292L57,495@20363L6850,626@27417L40,627@27500L38,628@27553L1248,623@27270L1531,655@29053L40,656@29136L38,657@29189L2407,652@28906L2690,697@31610L41,706@31944L48,699@31665L490:ProfileActivity.kt#t7vdsr");
            MotionUtilsKt.m8739ScrollProgressIndicatorpc5RIQQ($profileScrollState, null, 0L, 0L, 0.0f, $composer, 0, 30);
            Modifier modifierParallaxScroll = MotionUtilsKt.parallaxScroll(Modifier.INSTANCE, $profileScrollState, 0.25f);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierParallaxScroll);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            ComposerKt.sourceInformationMarkerStart($composer, -897068773, "C499@20614L40,500@20701L38,501@20758L6441,496@20455L6744:ProfileActivity.kt#t7vdsr");
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(28)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, 0L, 0L, $composer, (CardDefaults.$stable << 12) | 6, 14), CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(8), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer, (CardDefaults.$stable << 18) | 6, 62), null, ComposableLambdaKt.rememberComposableLambda(-1804192994, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ProfileActivityKt.ProfileScreen$lambda$148$lambda$147$lambda$134$lambda$133($galleryLauncher, $userEmail, $isAdmin, $profileImagePath$delegate, $userDetails$delegate, $showEditDialog$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 196614, 16);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(24)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, 0L, 0L, $composer, (CardDefaults.$stable << 12) | 6, 14), CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(4), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer, (CardDefaults.$stable << 18) | 6, 62), null, ComposableLambdaKt.rememberComposableLambda(815807332, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ProfileActivityKt.ProfileScreen$lambda$148$lambda$147$lambda$138($enrolledCourses$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 196614, 16);
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(24)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, 0L, 0L, $composer, (CardDefaults.$stable << 12) | 6, 14), CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(4), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer, (CardDefaults.$stable << 18) | 6, 62), null, ComposableLambdaKt.rememberComposableLambda(-1372040371, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return ProfileActivityKt.ProfileScreen$lambda$148$lambda$147$lambda$146($enrolledBatches$delegate, $context, $isAdmin, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 196614, 16);
            SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), $composer, 6);
            ButtonKt.Button($onSignOut, MotionUtilsKt.bounceClick$default(SizeKt.m851height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(52)), 0.0f, null, 3, null), false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(14)), ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4292617766L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$ProfileActivityKt.INSTANCE.m8697getLambda$1227129562$app(), $composer, C.ENCODING_PCM_32BIT, 484);
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

    /* JADX WARN: Code duplicated, block: B:65:0x0794  */
    static final Unit ProfileScreen$lambda$148$lambda$147$lambda$134$lambda$133(final ManagedActivityResultLauncher $galleryLauncher, final String $userEmail, boolean $isAdmin, MutableState $profileImagePath$delegate, final MutableState $userDetails$delegate, final MutableState $showEditDialog$delegate, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        Composer composer;
        Function0<ComposeUiNode> function4;
        String strSubstringBefore$default;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C502@20780L6405:ProfileActivity.kt#t7vdsr");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1804192994, $changed, -1, "com.example.sushikhasacademyapp.ProfileScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:502)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(24));
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, centerHorizontally, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
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
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -2128273390, "C510@21175L37,507@21046L2820,568@24140L10,566@24002L267,573@24467L10,571@24290L271,577@24583L40,608@26648L24,614@26956L48,607@26606L561:ProfileActivity.kt#t7vdsr");
            Modifier modifierM865size3ABfNKs = SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(96));
            ComposerKt.sourceInformationMarkerStart($composer, 1455364717, "CC(remember):ProfileActivity.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($galleryLauncher);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda31
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return ProfileActivityKt.ProfileScreen$lambda$148$lambda$147$lambda$134$lambda$133$lambda$132$lambda$120$lambda$119($galleryLauncher);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierM300clickableoSLSa3U$default = ClickableKt.m300clickableoSLSa3U$default(modifierM865size3ABfNKs, false, null, null, null, (Function0) objRememberedValue, 15, null);
            Alignment bottomEnd = Alignment.INSTANCE.getBottomEnd();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(bottomEnd, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierM300clickableoSLSa3U$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            int i6 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 174135914, "C513@21325L1873,550@23224L620:ProfileActivity.kt#t7vdsr");
            Modifier modifierBackground$default = BackgroundKt.background$default(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(96)), Brush.INSTANCE.m5057linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m5087boximpl(ColorKt.Color(4279673674L)), Color.m5087boximpl(ColorKt.Color(4280468830L))}), (8 & 2) != 0 ? Offset.INSTANCE.m4868getZeroF1C5BW0() : 0L, (8 & 4) != 0 ? Offset.INSTANCE.m4866getInfiniteF1C5BW0() : 0L, (8 & 8) != 0 ? TileMode.INSTANCE.m5492getClamp3opZhB0() : 0), RoundedCornerShapeKt.getCircleShape(), 0.0f, 4, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierBackground$default);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1959643793, "C524@21870L1302:ProfileActivity.kt#t7vdsr");
            Modifier modifierClip = ClipKt.clip(BackgroundKt.m264backgroundbw27NRU(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(90)), Color.INSTANCE.m5134getWhite0d7_KjU(), RoundedCornerShapeKt.getCircleShape()), RoundedCornerShapeKt.getCircleShape());
            Alignment center2 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierClip);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function3 = constructor4;
                $composer.createNode(function3);
            } else {
                function3 = constructor4;
                $composer.useNode();
            }
            Composer composerM4367constructorimpl4 = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            int i12 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 958186093, "C:ProfileActivity.kt#t7vdsr");
            String strProfileScreen$lambda$92 = ProfileScreen$lambda$92($profileImagePath$delegate);
            if (strProfileScreen$lambda$92 != null) {
                $composer.startReplaceGroup(958248371);
                ComposerKt.sourceInformation($composer, "533@22362L337");
                composer = $composer;
                SingletonAsyncImageKt.m8524AsyncImagegl8XCv8(new File(strProfileScreen$lambda$92), "Profile Picture", SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, false, null, composer, 1573296, 0, 4024);
                composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(958659865);
                ComposerKt.sourceInformation($composer, "540@22777L331");
                composer = $composer;
                IconKt.m2520Iconww6aTOc(AccountCircleKt.getAccountCircle(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(82)), ColorKt.Color(4279673674L), composer, 3504, 0);
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
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
            Modifier modifierM264backgroundbw27NRU = BackgroundKt.m264backgroundbw27NRU(SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(30)), ColorKt.Color(4279522093L), RoundedCornerShapeKt.getCircleShape());
            Alignment center3 = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(center3, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier($composer, modifierM264backgroundbw27NRU);
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            int i13 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function4 = constructor5;
                $composer.createNode(function4);
            } else {
                function4 = constructor5;
                $composer.useNode();
            }
            Composer composerM4367constructorimpl5 = Updater.m4367constructorimpl($composer);
            Updater.m4375setimpl(composerM4367constructorimpl5, measurePolicyMaybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl5, Integer.valueOf(iHashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl5, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = (i13 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            int i15 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1147438299, "C556@23527L291:ProfileActivity.kt#t7vdsr");
            IconKt.m2520Iconww6aTOc(CameraAltKt.getCameraAlt(Icons.INSTANCE.getDefault()), "Change Photo", SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16)), Color.INSTANCE.m5134getWhite0d7_KjU(), $composer, 3504, 0);
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
            String str = ProfileScreen$lambda$95($userDetails$delegate).get(HintConstants.AUTOFILL_HINT_USERNAME);
            if (str != null) {
                String str2 = str;
                if (StringsKt.isBlank(str2)) {
                    str2 = null;
                }
                strSubstringBefore$default = str2;
                if (strSubstringBefore$default == null) {
                    strSubstringBefore$default = StringsKt.substringBefore$default($userEmail, "@", (String) null, 2, (Object) null);
                }
            } else {
                strSubstringBefore$default = StringsKt.substringBefore$default($userEmail, "@", (String) null, 2, (Object) null);
            }
            TextKt.m3072TextNvy7gAk($isAdmin ? "@sumanghosh_admin" : "@" + strSubstringBefore$default, null, ColorKt.Color(4279179050L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleLarge(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 384, 0, 131066);
            TextKt.m3072TextNvy7gAk($isAdmin ? "Lead English Trainer & Administrator" : "Sushiksha English Academy Student", null, ColorKt.Color(4284773515L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 384, 0, 131066);
            SpacerKt.Spacer(SizeKt.m851height3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(4)), $composer, 6);
            if (!$isAdmin) {
                $composer.startReplaceGroup(-2124819371);
                ComposerKt.sourceInformation($composer, "581@24741L46,583@24878L980,580@24685L1173,595@26008L25,594@25954L608");
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2144cardColorsro_MJ88(ColorKt.Color(4294507260L), 0L, 0L, 0L, $composer, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(-165088005, true, new Function3() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda32
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return ProfileActivityKt.ProfileScreen$lambda$148$lambda$147$lambda$134$lambda$133$lambda$132$lambda$127($userEmail, $userDetails$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), $composer, 196614, 26);
                ComposerKt.sourceInformationMarkerStart($composer, 1455519361, "CC(remember):ProfileActivity.kt#9igjgp");
                Object objRememberedValue2 = $composer.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda33
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ProfileActivityKt.ProfileScreen$lambda$148$lambda$147$lambda$134$lambda$133$lambda$132$lambda$129$lambda$128($showEditDialog$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    objRememberedValue2 = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                ButtonKt.OutlinedButton((Function0) objRememberedValue2, MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null), false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12)), null, null, null, null, null, ComposableSingletons$ProfileActivityKt.INSTANCE.getLambda$728772859$app(), $composer, 805306374, LogSeverity.ERROR_VALUE);
            } else {
                $composer.startReplaceGroup(2145625530);
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerStart($composer, 1455539840, "CC(remember):ProfileActivity.kt#9igjgp");
            Object objRememberedValue3 = $composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda34
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return Unit.INSTANCE;
                    }
                };
                $composer.updateRememberedValue(obj2);
                objRememberedValue3 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue3, MotionUtilsKt.bounceClick$default(SizeKt.m851height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(50)), 0.0f, null, 3, null), false, RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(25)), ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$ProfileActivityKt.INSTANCE.getLambda$1389339384$app(), $composer, 805306374, 484);
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

    static final Unit ProfileScreen$lambda$148$lambda$147$lambda$134$lambda$133$lambda$132$lambda$120$lambda$119(ManagedActivityResultLauncher $galleryLauncher) {
        $galleryLauncher.launch("image/*");
        return Unit.INSTANCE;
    }

    static final Unit ProfileScreen$lambda$148$lambda$147$lambda$134$lambda$133$lambda$132$lambda$127(String $userEmail, MutableState $userDetails$delegate, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C584@24908L924:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-165088005, $changed, -1, "com.example.sushikhasacademyapp.ProfileScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:584)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(12));
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(4));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1065216065, "C585@25083L10,585@25035L69:ProfileActivity.kt#t7vdsr");
            TextKt.m3072TextNvy7gAk("Email: " + $userEmail, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131070);
            String str = ProfileScreen$lambda$95($userDetails$delegate).get("dob");
            if (str == null || StringsKt.isBlank(str)) {
                $composer.startReplaceGroup(-1090075555);
            } else {
                $composer.startReplaceGroup(658378749);
                ComposerKt.sourceInformation($composer, "586@25245L10,586@25178L88");
                TextKt.m3072TextNvy7gAk("Date of Birth: " + ((Object) ProfileScreen$lambda$95($userDetails$delegate).get("dob")), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131070);
            }
            $composer.endReplaceGroup();
            String str2 = ProfileScreen$lambda$95($userDetails$delegate).get("class");
            if (str2 == null || StringsKt.isBlank(str2)) {
                $composer.startReplaceGroup(-1090075555);
            } else {
                $composer.startReplaceGroup(658383991);
                ComposerKt.sourceInformation($composer, "587@25403L10,587@25342L82");
                TextKt.m3072TextNvy7gAk("Class: " + ((Object) ProfileScreen$lambda$95($userDetails$delegate).get("class")), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131070);
            }
            $composer.endReplaceGroup();
            String str3 = ProfileScreen$lambda$95($userDetails$delegate).get("school");
            if (str3 == null || StringsKt.isBlank(str3)) {
                $composer.startReplaceGroup(-1090075555);
            } else {
                $composer.startReplaceGroup(658389081);
                ComposerKt.sourceInformation($composer, "588@25564L10,588@25501L84");
                TextKt.m3072TextNvy7gAk("School: " + ((Object) ProfileScreen$lambda$95($userDetails$delegate).get("school")), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131070);
            }
            $composer.endReplaceGroup();
            String str4 = ProfileScreen$lambda$95($userDetails$delegate).get("exam");
            if (str4 == null || StringsKt.isBlank(str4)) {
                $composer.startReplaceGroup(-1090075555);
            } else {
                $composer.startReplaceGroup(-1064615441);
                ComposerKt.sourceInformation($composer, "589@25724L10,589@25660L142");
                TextKt.m3072TextNvy7gAk("Exam Prep: " + ((Object) ProfileScreen$lambda$95($userDetails$delegate).get("exam")), null, ColorKt.Color(4279673674L), null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 1573248, 0, 131002);
            }
            $composer.endReplaceGroup();
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

    static final Unit ProfileScreen$lambda$148$lambda$147$lambda$134$lambda$133$lambda$132$lambda$129$lambda$128(MutableState $showEditDialog$delegate) {
        ProfileScreen$lambda$99($showEditDialog$delegate, true);
        return Unit.INSTANCE;
    }

    static final Unit ProfileScreen$lambda$148$lambda$147$lambda$138(MutableState $enrolledCourses$delegate, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        Function0<ComposeUiNode> function1;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C629@27571L1216:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(815807332, $changed, -1, "com.example.sushikhasacademyapp.ProfileScreen.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:629)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20));
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            String str = "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierM819padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
            String str2 = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
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
            int i3 = 0;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i4 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1095970859, "C633@27795L10,633@27745L135,635@27902L44:ProfileActivity.kt#t7vdsr");
            TextKt.m3072TextNvy7gAk("My Enrolled Courses", null, ColorKt.Color(4279179050L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 390, 0, 131066);
            DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, ColorKt.Color(4293060848L), $composer, 384, 3);
            Composer composer2 = $composer;
            if (ProfileScreen$lambda$101($enrolledCourses$delegate).isEmpty()) {
                composer2.startReplaceGroup(-1095744498);
                ComposerKt.sourceInformation(composer2, "638@28134L10,638@28025L150");
                composer = composer2;
                TextKt.m3072TextNvy7gAk("You are not enrolled in any courses yet. Please contact Admin to get enrolled.", null, Color.INSTANCE.m5127getGray0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer2, MaterialTheme.$stable).getBodySmall(), composer, 390, 0, 131066);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-1095530722);
                ComposerKt.sourceInformation(composer2, "*641@28293L428");
                Iterable<String> iterableProfileScreen$lambda$101 = ProfileScreen$lambda$101($enrolledCourses$delegate);
                int i5 = 0;
                for (String str3 : iterableProfileScreen$lambda$101) {
                    Iterable iterable = iterableProfileScreen$lambda$101;
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Composer composer3 = composer2;
                    int i6 = i5;
                    Composer composer4 = composer2;
                    int i7 = i3;
                    ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier = Modifier.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, str);
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                    String str4 = str;
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifier);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i8 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, str2);
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        function1 = constructor2;
                        composer3.createNode(function1);
                    } else {
                        function1 = constructor2;
                        composer3.useNode();
                    }
                    Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl(composer3);
                    Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i9 = (i8 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i10 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -287022547, "C642@28379L117,643@28529L40,644@28637L10,644@28602L89:ProfileActivity.kt#t7vdsr");
                    IconKt.m2520Iconww6aTOc(CheckCircleKt.getCheckCircle(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20)), ColorKt.Color(4279673674L), composer3, 3504, 0);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), composer3, 6);
                    TextKt.m3072TextNvy7gAk(str3, null, 0L, null, 0L, null, FontWeight.INSTANCE.getMedium(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getBodyMedium(), composer3, 1572864, 0, 131006);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    iterableProfileScreen$lambda$101 = iterable;
                    i5 = i6;
                    i3 = i7;
                    str = str4;
                    composer2 = composer4;
                    str2 = str2;
                }
                composer = composer2;
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
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

    static final Unit ProfileScreen$lambda$148$lambda$147$lambda$146(MutableState $enrolledBatches$delegate, final Context $context, final boolean $isAdmin, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        Composer composer2;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C658@29207L2375:ProfileActivity.kt#t7vdsr");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1372040371, $changed, -1, "com.example.sushikhasacademyapp.ProfileScreen.<anonymous>.<anonymous>.<anonymous> (ProfileActivity.kt:658)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(20));
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(12));
            Composer composer3 = $composer;
            int i = 54;
            ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), composer3, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierM819padding3ABfNKs);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((((54 << 3) & 112) << 6) & 896) | 6;
            int i3 = 6;
            String str = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
                function0 = constructor;
                composer3.createNode(function0);
            } else {
                function0 = constructor;
                composer3.useNode();
            }
            Composer composerM4367constructorimpl = Updater.m4367constructorimpl(composer3);
            MeasurePolicy measurePolicy = measurePolicyColumnMeasurePolicy;
            Updater.m4375setimpl(composerM4367constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl, Integer.valueOf(iHashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, 1683786277, "C662@29431L10,662@29381L135,664@29538L44:ProfileActivity.kt#t7vdsr");
            TextKt.m3072TextNvy7gAk("My Enrolled Batches", null, ColorKt.Color(4279179050L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography(composer3, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer3, 390, 0, 131066);
            DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, ColorKt.Color(4293060848L), composer3, 384, 3);
            Composer composer4 = composer3;
            if (ProfileScreen$lambda$104($enrolledBatches$delegate).isEmpty()) {
                composer4.startReplaceGroup(1683976709);
                ComposerKt.sourceInformation(composer4, "667@29770L10,667@29661L150");
                composer = composer4;
                TextKt.m3072TextNvy7gAk("You are not enrolled in any batches yet. Please contact Admin to get enrolled.", null, Color.INSTANCE.m5127getGray0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer4, MaterialTheme.$stable).getBodySmall(), composer, 390, 0, 131066);
                composer4.endReplaceGroup();
                composer2 = composer3;
            } else {
                composer4.startReplaceGroup(1684226414);
                ComposerKt.sourceInformation(composer4, "*674@30164L512,670@29928L1588");
                Iterable<String> iterableProfileScreen$lambda$104 = ProfileScreen$lambda$104($enrolledBatches$delegate);
                int i6 = 0;
                for (final String str2 : iterableProfileScreen$lambda$104) {
                    Iterable iterable = iterableProfileScreen$lambda$104;
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    int i7 = i6;
                    MeasurePolicy measurePolicy2 = measurePolicy;
                    Composer composer5 = composer3;
                    int i8 = i;
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composer4, 1837758019, "CC(remember):ProfileActivity.kt#9igjgp");
                    boolean zChangedInstance = composer4.changedInstance($context) | composer4.changed(str2) | composer4.changed($isAdmin);
                    Composer composer6 = composer4;
                    Object objRememberedValue = composer6.rememberedValue();
                    if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ProfileActivityKt$$ExternalSyntheticLambda38
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return ProfileActivityKt.ProfileScreen$lambda$148$lambda$147$lambda$146$lambda$145$lambda$144$lambda$141$lambda$140($context, str2, $isAdmin);
                            }
                        };
                        composer6.updateRememberedValue(obj);
                        objRememberedValue = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    Modifier modifierM821paddingVpY3zN4$default = PaddingKt.m821paddingVpY3zN4$default(ClickableKt.m300clickableoSLSa3U$default(modifierFillMaxWidth$default, false, null, null, null, (Function0) objRememberedValue, 15, null), 0.0f, Dp.m7905constructorimpl(6), 1, null);
                    Composer composer7 = composer4;
                    ComposerKt.sourceInformationMarkerStart(composer7, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer7, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer7, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer7, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = composer7.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer7, modifierM821paddingVpY3zN4$default);
                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i9 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer7, -553112988, str);
                    if (!(composer7.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer7.startReusableNode();
                    if (composer7.getInserting()) {
                        function1 = constructor2;
                        composer7.createNode(function1);
                    } else {
                        function1 = constructor2;
                        composer7.useNode();
                    }
                    Composer composerM4367constructorimpl2 = Updater.m4367constructorimpl(composer7);
                    Composer composer8 = composer4;
                    Updater.m4375setimpl(composerM4367constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl2, Integer.valueOf(iHashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i10 = (i9 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer7, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i11 = ((384 >> 6) & 112) | 6;
                    RowScope rowScope = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(composer7, 1703770385, "C684@30803L114,685@30950L40,686@31023L352,690@31408L78:ProfileActivity.kt#t7vdsr");
                    IconKt.m2520Iconww6aTOc(ScheduleKt.getSchedule(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m865size3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(22)), ColorKt.Color(4279673674L), composer7, 3504, 0);
                    SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(10)), composer7, i3);
                    Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composer7, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer7, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer7, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer7, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = composer7.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer7, modifierWeight$default);
                    Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int i12 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer7, -553112988, str);
                    if (!(composer7.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer7.startReusableNode();
                    if (composer7.getInserting()) {
                        function2 = constructor3;
                        composer7.createNode(function2);
                    } else {
                        function2 = constructor3;
                        composer7.useNode();
                    }
                    Composer composerM4367constructorimpl3 = Updater.m4367constructorimpl(composer7);
                    Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i13 = (i12 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer7, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    i3 = 6;
                    int i14 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer7, 913110229, "C687@31134L10,687@31100L86,688@31292L10,688@31223L118:ProfileActivity.kt#t7vdsr");
                    TextKt.m3072TextNvy7gAk(str2, null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer7, MaterialTheme.$stable).getBodyMedium(), composer7, 1572864, 0, 131006);
                    TextKt.m3072TextNvy7gAk("Tap to view Uploads, Details & Tests →", null, ColorKt.Color(4279673674L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer7, MaterialTheme.$stable).getLabelSmall(), composer7, 390, 0, 131066);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    composer7.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    IconKt.m2520Iconww6aTOc(ChevronRightKt.getChevronRight(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, Color.INSTANCE.m5127getGray0d7_KjU(), composer7, 3120, 4);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    composer7.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    ComposerKt.sourceInformationMarkerEnd(composer7);
                    iterableProfileScreen$lambda$104 = iterable;
                    measurePolicy = measurePolicy2;
                    i6 = i7;
                    composer3 = composer5;
                    i = i8;
                    composer4 = composer8;
                    str = str;
                }
                composer = composer4;
                composer2 = composer3;
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit ProfileScreen$lambda$148$lambda$147$lambda$146$lambda$145$lambda$144$lambda$141$lambda$140(Context $context, String $batch, boolean $isAdmin) {
        Intent intent = new Intent($context, (Class<?>) BatchDetailActivity.class);
        intent.putExtra("BATCH_TITLE", $batch);
        intent.putExtra("BATCH_TIMING", "8:30 AM - 10:00 AM (Mon, Sat)");
        intent.putExtra("IS_ADMIN", $isAdmin);
        $context.startActivity(intent);
        return Unit.INSTANCE;
    }
}
