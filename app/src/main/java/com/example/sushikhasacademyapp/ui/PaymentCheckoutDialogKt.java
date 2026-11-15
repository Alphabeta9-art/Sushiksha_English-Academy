package com.example.sushikhasacademyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
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
import androidx.compose.foundation.text.selection.SelectionContainerKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.AccountBalanceWalletKt;
import androidx.compose.material.icons.filled.PaymentKt;
import androidx.compose.material.icons.filled.SendToMobileKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.AppBarKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.CardKt;
import androidx.compose.material3.ChipKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.material3.IconButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextKt;
import androidx.compose.material3.TopAppBarDefaults;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
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
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import androidx.profileinstaller.ProfileVerifier;
import com.example.sushikhasacademyapp.ui.animation.MotionUtilsKt;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PaymentCheckoutDialog.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u001aW\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007¢\u0006\u0002\u0010\f¨\u0006\r²\u0006\n\u0010\u000e\u001a\u00020\u0003X\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0011\u001a\u00020\u0006X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0003X\u008a\u008e\u0002"}, d2 = {"PaymentCheckoutDialog", "", "itemName", "", "feeAmount", "isBatch", "", "studentEmail", "upiVpa", "onDismiss", "Lkotlin/Function0;", "onPaymentSuccess", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "app", "transactionRefInput", "selectedPaymentMode", "", "showReceiptDialog", "lastReceiptNo"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PaymentCheckoutDialogKt {
    static final Unit PaymentCheckoutDialog$lambda$57(String str, String str2, boolean z, String str3, String str4, Function0 function0, Function0 function1, int i, int i2, Composer composer, int i3) {
        PaymentCheckoutDialog(str, str2, z, str3, str4, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void PaymentCheckoutDialog(final String itemName, String feeAmount, boolean isBatch, final String studentEmail, String upiVpa, final Function0<Unit> onDismiss, final Function0<Unit> onPaymentSuccess, Composer $composer, final int $changed, final int i) {
        String str;
        boolean z;
        String str2;
        Composer $composer2;
        final String feeAmount2;
        final boolean isBatch2;
        final String upiVpa2;
        String feeAmount3;
        final boolean isBatch3;
        String upiVpa3;
        String cleanAmount;
        boolean z2;
        int i2;
        Intrinsics.checkNotNullParameter(itemName, "itemName");
        Intrinsics.checkNotNullParameter(studentEmail, "studentEmail");
        Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
        Intrinsics.checkNotNullParameter(onPaymentSuccess, "onPaymentSuccess");
        Composer $composer3 = $composer.startRestartGroup(1007477955);
        ComposerKt.sourceInformation($composer3, "C(PaymentCheckoutDialog)N(itemName,feeAmount,isBatch,studentEmail,upiVpa,onDismiss,onPaymentSuccess)41@1561L7,42@1600L31,43@1663L33,44@1767L34,45@1827L31,142@6671L10160,139@6548L10283:PaymentCheckoutDialog.kt#untpz1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(itemName) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            str = feeAmount;
        } else if (($changed & 48) == 0) {
            str = feeAmount;
            $dirty |= $composer3.changed(str) ? 32 : 16;
        } else {
            str = feeAmount;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            z = isBatch;
        } else if (($changed & 384) == 0) {
            z = isBatch;
            $dirty |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = isBatch;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(studentEmail) ? 2048 : 1024;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            str2 = upiVpa;
        } else if (($changed & 24576) == 0) {
            str2 = upiVpa;
            $dirty |= $composer3.changed(str2) ? 16384 : 8192;
        } else {
            str2 = upiVpa;
        }
        if ((196608 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(onDismiss) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty |= $composer3.changedInstance(onPaymentSuccess) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            if (i3 != 0) {
                feeAmount3 = "₹1,499.00";
            } else {
                feeAmount3 = str;
            }
            if (i4 == 0) {
                isBatch3 = z;
            } else {
                isBatch3 = false;
            }
            if (i5 == 0) {
                upiVpa3 = str2;
            } else {
                upiVpa3 = "sushiksha@upi";
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1007477955, $dirty, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog (PaymentCheckoutDialog.kt:40)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer3, 1818684898, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            Object objRememberedValue = $composer3.rememberedValue();
            int $dirty2 = $dirty;
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default);
                objRememberedValue = objMutableStateOf$default;
            }
            final MutableState transactionRefInput$delegate = (MutableState) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1818686916, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            Object objRememberedValue2 = $composer3.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objMutableIntStateOf = SnapshotIntStateKt.mutableIntStateOf(0);
                $composer3.updateRememberedValue(objMutableIntStateOf);
                objRememberedValue2 = objMutableIntStateOf;
            }
            final MutableIntState selectedPaymentMode$delegate = (MutableIntState) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1818690245, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            Object objRememberedValue3 = $composer3.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(objRememberedValue3);
            }
            final MutableState showReceiptDialog$delegate = (MutableState) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1818692162, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            Object objRememberedValue4 = $composer3.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objMutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(objMutableStateOf$default2);
                objRememberedValue4 = objMutableStateOf$default2;
            }
            final MutableState lastReceiptNo$delegate = (MutableState) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final String feeAmount4 = feeAmount3;
            String cleanAmount2 = StringsKt.trim((CharSequence) StringsKt.replace$default(StringsKt.replace$default(feeAmount3, "₹", "", false, 4, (Object) null), ",", "", false, 4, (Object) null)).toString();
            if (PaymentCheckoutDialog$lambda$7(showReceiptDialog$delegate)) {
                $composer3.startReplaceGroup(547020494);
                ComposerKt.sourceInformation($composer3, "92@3955L92,125@6175L351,103@4492L1653,91@3911L2625");
                ComposerKt.sourceInformationMarkerStart($composer3, 1818760319, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                boolean z3 = ($dirty2 & 3670016) == 1048576;
                Object objRememberedValue5 = $composer3.rememberedValue();
                if (!z3) {
                    cleanAmount = cleanAmount2;
                    if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    z2 = true;
                    i2 = 54;
                    AndroidAlertDialog_androidKt.m2066AlertDialogOix01E0((Function0) objRememberedValue5, ComposableLambdaKt.rememberComposableLambda(-1197192810, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$17(onPaymentSuccess, showReceiptDialog$delegate, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer3, 54), null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.getLambda$1850332434$app(), ComposableLambdaKt.rememberComposableLambda(-1682753551, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$21(studentEmail, itemName, feeAmount4, lastReceiptNo$delegate, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer3, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer3, 1769520, 0, 16284);
                    $composer2 = $composer3;
                } else {
                    cleanAmount = cleanAmount2;
                }
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$14$lambda$13(onPaymentSuccess, showReceiptDialog$delegate);
                    }
                };
                $composer3.updateRememberedValue(obj);
                objRememberedValue5 = obj;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                z2 = true;
                i2 = 54;
                AndroidAlertDialog_androidKt.m2066AlertDialogOix01E0((Function0) objRememberedValue5, ComposableLambdaKt.rememberComposableLambda(-1197192810, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$17(onPaymentSuccess, showReceiptDialog$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer3, 54), null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.getLambda$1850332434$app(), ComposableLambdaKt.rememberComposableLambda(-1682753551, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$21(studentEmail, itemName, feeAmount4, lastReceiptNo$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer3, 54), null, 0L, 0L, 0L, 0L, 0.0f, null, $composer3, 1769520, 0, 16284);
                $composer2 = $composer3;
            } else {
                cleanAmount = cleanAmount2;
                $composer2 = $composer3;
                z2 = true;
                i2 = 54;
                $composer2.startReplaceGroup(543067839);
            }
            $composer2.endReplaceGroup();
            boolean z4 = z2;
            final String upiVpa4 = upiVpa3;
            final String cleanAmount3 = cleanAmount;
            AndroidDialog_androidKt.Dialog(onDismiss, new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(1021581594, z4, new Function2() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56(onDismiss, itemName, feeAmount4, selectedPaymentMode$delegate, upiVpa4, cleanAmount3, context, studentEmail, isBatch3, lastReceiptNo$delegate, showReceiptDialog$delegate, transactionRefInput$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, i2), $composer2, (($dirty2 >> 15) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isBatch2 = isBatch3;
            feeAmount2 = feeAmount4;
            upiVpa2 = upiVpa3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            feeAmount2 = str;
            isBatch2 = z;
            upiVpa2 = str2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$57(itemName, feeAmount2, isBatch2, studentEmail, upiVpa2, onDismiss, onPaymentSuccess, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final String PaymentCheckoutDialog$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final int PaymentCheckoutDialog$lambda$4(MutableIntState $selectedPaymentMode$delegate) {
        return $selectedPaymentMode$delegate.getIntValue();
    }

    private static final boolean PaymentCheckoutDialog$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PaymentCheckoutDialog$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String PaymentCheckoutDialog$lambda$10(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final void PaymentCheckoutDialog$launchUpiIntent(String $upiVpa, String cleanAmount, String $itemName, Context context, String $feeAmount, String upiAppName) {
        try {
            Uri upiUri = Uri.parse("upi://pay?pa=" + $upiVpa + "&pn=Sushiksha%20English%20Academy&am=" + cleanAmount + "&cu=INR&tn=Enrollment%20Fee%20for%20" + $itemName);
            Intent intent = new Intent("android.intent.action.VIEW", upiUri);
            context.startActivity(intent);
            Toast.makeText(context, "Opening " + upiAppName + " to pay " + $feeAmount + "...", 0).show();
        } catch (Exception e) {
            Toast.makeText(context, "Unable to launch " + upiAppName + ": " + e.getLocalizedMessage(), 0).show();
        }
    }

    private static final void PaymentCheckoutDialog$completeEnrollmentAndSavePayment(String $studentEmail, String $itemName, boolean $isBatch, String $feeAmount, Context context, MutableState<String> mutableState, MutableState<Boolean> mutableState2, String txRef) {
        String uid;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && (uid = user.getUid()) != null) {
            String receiptNumber = "REC-" + (System.currentTimeMillis() % 1000000);
            mutableState.setValue(receiptNumber);
            try {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Intrinsics.checkNotNullExpressionValue(db, "getInstance(...)");
                Pair[] pairArr = new Pair[10];
                pairArr[0] = TuplesKt.to("receiptNo", receiptNumber);
                pairArr[1] = TuplesKt.to("studentUid", uid);
                try {
                    pairArr[2] = TuplesKt.to("studentEmail", $studentEmail);
                    try {
                        pairArr[3] = TuplesKt.to("itemName", $itemName);
                        pairArr[4] = TuplesKt.to("isBatch", Boolean.valueOf($isBatch));
                        try {
                            pairArr[5] = TuplesKt.to("amount", $feeAmount);
                            String str = txRef;
                            if (StringsKt.isBlank(str)) {
                                str = "UPI-TRANSFER-" + System.currentTimeMillis();
                            }
                            pairArr[6] = TuplesKt.to("transactionRef", str);
                            pairArr[7] = TuplesKt.to("paymentDate", new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(new Date()));
                            pairArr[8] = TuplesKt.to(NotificationCompat.CATEGORY_STATUS, "PENDING_VERIFICATION");
                            pairArr[9] = TuplesKt.to("createdAt", Timestamp.INSTANCE.now());
                            HashMap paymentRecord = MapsKt.hashMapOf(pairArr);
                            db.collection("payments").add(paymentRecord);
                            try {
                                PaymentCheckoutDialog$lambda$8(mutableState2, true);
                            } catch (Exception e) {
                                e = e;
                                Toast.makeText(context, "Error submitting payment: " + e.getLocalizedMessage(), 1).show();
                            }
                        } catch (Exception e2) {
                            e = e2;
                            Toast.makeText(context, "Error submitting payment: " + e.getLocalizedMessage(), 1).show();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        Toast.makeText(context, "Error submitting payment: " + e.getLocalizedMessage(), 1).show();
                    }
                } catch (Exception e4) {
                    e = e4;
                    Toast.makeText(context, "Error submitting payment: " + e.getLocalizedMessage(), 1).show();
                }
            } catch (Exception e5) {
                e = e5;
            }
        }
    }

    static final Unit PaymentCheckoutDialog$lambda$14$lambda$13(Function0 $onPaymentSuccess, MutableState $showReceiptDialog$delegate) {
        PaymentCheckoutDialog$lambda$8($showReceiptDialog$delegate, false);
        $onPaymentSuccess.invoke();
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$21(final String $studentEmail, final String $itemName, final String $feeAmount, final MutableState $lastReceiptNo$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        ComposerKt.sourceInformation($composer, "C104@4510L1621:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1682753551, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous> (PaymentCheckoutDialog.kt:104)");
            }
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(10));
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
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
            ComposerKt.sourceInformationMarkerStart($composer, 1132940595, "C109@4736L46,111@4865L1248,108@4684L1429:PaymentCheckoutDialog.kt#untpz1");
            CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2144cardColorsro_MJ88(ColorKt.Color(4294047225L), 0L, 0L, 0L, $composer, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(712500489, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$21$lambda$20$lambda$19($studentEmail, $itemName, $feeAmount, $lastReceiptNo$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 196614, 26);
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

    static final Unit PaymentCheckoutDialog$lambda$21$lambda$20$lambda$19(String $studentEmail, String $itemName, String $feeAmount, MutableState $lastReceiptNo$delegate, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C112@4891L1200:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(712500489, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:112)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(14));
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(6));
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
            ComposerKt.sourceInformationMarkerStart($composer, -630545963, "C113@5014L90,114@5220L10,114@5133L108,115@5270L19,116@5371L10,116@5318L74,117@5467L10,117@5421L97,118@5600L10,118@5547L132,119@5767L10,119@5708L138,120@6017L10,120@5875L190:PaymentCheckoutDialog.kt#untpz1");
            TextKt.m3072TextNvy7gAk("Sushiksha English Academy", null, ColorKt.Color(4279522093L), null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1573254, 0, 262074);
            TextKt.m3072TextNvy7gAk("Receipt No: " + PaymentCheckoutDialog$lambda$10($lastReceiptNo$delegate), null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 1572864, 0, 131006);
            DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, $composer, 0, 7);
            TextKt.m3072TextNvy7gAk("Student: " + $studentEmail, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 0, 0, 131070);
            TextKt.m3072TextNvy7gAk("Item: " + $itemName, null, 0L, null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 1572864, 0, 131006);
            TextKt.m3072TextNvy7gAk("Fee Amount: " + $feeAmount, null, ColorKt.Color(4279179050L), null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 1573248, 0, 131002);
            TextKt.m3072TextNvy7gAk("Status: PENDING VERIFICATION", null, ColorKt.Color(4292441862L), null, 0L, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelSmall(), $composer, 1573254, 0, 131002);
            TextKt.m3072TextNvy7gAk("Your transaction reference has been recorded. Admin will verify the UTR reference and activate your enrollment.", null, ColorKt.Color(4284773515L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 390, 0, 131066);
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

    static final Unit PaymentCheckoutDialog$lambda$17(final Function0 $onPaymentSuccess, final MutableState $showReceiptDialog$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C127@6231L116,131@6393L48,126@6193L319:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1197192810, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous> (PaymentCheckoutDialog.kt:126)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 548946826, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            boolean zChanged = $composer.changed($onPaymentSuccess);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$17$lambda$16$lambda$15($onPaymentSuccess, $showReceiptDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                objRememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue, null, false, null, ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14), null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.getLambda$1259786150$app(), $composer, C.ENCODING_PCM_32BIT, 494);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$17$lambda$16$lambda$15(Function0 $onPaymentSuccess, MutableState $showReceiptDialog$delegate) {
        PaymentCheckoutDialog$lambda$8($showReceiptDialog$delegate, false);
        $onPaymentSuccess.invoke();
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56(final Function0 $onDismiss, final String $itemName, final String $feeAmount, final MutableIntState $selectedPaymentMode$delegate, final String $upiVpa, final String $cleanAmount, final Context $context, final String $studentEmail, final boolean $isBatch, final MutableState $lastReceiptNo$delegate, final MutableState $showReceiptDialog$delegate, final MutableState $transactionRefInput$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C146@6785L10044,143@6681L10148:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1021581594, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous> (PaymentCheckoutDialog.kt:143)");
            }
            SurfaceKt.m2929SurfaceT9BRK9s(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, ColorKt.Color(4294507260L), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1726937205, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55($onDismiss, $itemName, $feeAmount, $selectedPaymentMode$delegate, $upiVpa, $cleanAmount, $context, $studentEmail, $isBatch, $lastReceiptNo$delegate, $showReceiptDialog$delegate, $transactionRefInput$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12583302, 122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55(final Function0 $onDismiss, final String $itemName, final String $feeAmount, final MutableIntState $selectedPaymentMode$delegate, final String $upiVpa, final String $cleanAmount, final Context $context, final String $studentEmail, final boolean $isBatch, final MutableState $lastReceiptNo$delegate, final MutableState $showReceiptDialog$delegate, final MutableState $transactionRefInput$delegate, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Function0<ComposeUiNode> function2;
        Function0<ComposeUiNode> function3;
        ComposerKt.sourceInformation($composer, "C147@6799L10024:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1726937205, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:147)");
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
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1997553538, "C148@6880L21,158@7376L45,153@7136L191,150@6919L520,161@7457L9356:PaymentCheckoutDialog.kt#untpz1");
            ScrollState scrollStateRememberScrollState = ScrollKt.rememberScrollState(0, $composer, 0, 1);
            AppBarKt.m2090TopAppBarGHTll3U(ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8731getLambda$1930277117$app(), MotionUtilsKt.m8744elevateOnScrollwH6b6FI$default(Modifier.INSTANCE, scrollStateRememberScrollState, 0.0f, 2, null), ComposableLambdaKt.rememberComposableLambda(-1904131323, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$22($onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), null, 0.0f, null, TopAppBarDefaults.INSTANCE.m3287topAppBarColors5tl4gsc(Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, 0L, 0L, 0L, 0L, $composer, (TopAppBarDefaults.$stable << 18) | 6, 62), null, $composer, 390, 184);
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxSize$default2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((6 << 3) & 112) << 6) & 896) | 6;
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
            int i6 = ((6 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1107614473, "C162@7518L9049,310@16584L215:PaymentCheckoutDialog.kt#untpz1");
            Modifier modifierVerticalScroll$default = ScrollKt.verticalScroll$default(PaddingKt.m819padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7905constructorimpl(18)), scrollStateRememberScrollState, false, null, false, 14, null);
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(14));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(verticalM689spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifierVerticalScroll$default);
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
            Updater.m4375setimpl(composerM4367constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl3, Integer.valueOf(iHashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1297827003, "C169@7840L53,177@8245L40,178@8340L38,179@8405L1058,172@7959L1504,194@9552L10,194@9485L124,196@9631L1026:PaymentCheckoutDialog.kt#untpz1");
            MotionUtilsKt.m8739ScrollProgressIndicatorpc5RIQQ(scrollStateRememberScrollState, null, 0L, 0L, 0.0f, $composer, 0, 30);
            CardKt.Card(MotionUtilsKt.scrollEntranceEffect$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), scrollStateRememberScrollState, 0.0f, 2, null), RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(16)), CardDefaults.INSTANCE.m2144cardColorsro_MJ88(Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, 0L, 0L, $composer, (CardDefaults.$stable << 12) | 6, 14), CardDefaults.INSTANCE.m2145cardElevationaqJV_2Y(Dp.m7905constructorimpl(2), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer, (CardDefaults.$stable << 18) | 6, 62), null, ComposableLambdaKt.rememberComposableLambda(-408946543, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$25($itemName, $feeAmount, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 16);
            TextKt.m3072TextNvy7gAk("Select Payment Method (0% Extra Fee)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleSmall(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 6, 0, 131070);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal horizontalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalM689spacedBy0680j_4, Alignment.INSTANCE.getTop(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((54 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4375setimpl(composerM4367constructorimpl4, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4375setimpl(composerM4367constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4371initimpl(composerM4367constructorimpl4, Integer.valueOf(iHashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4373reconcileimpl(composerM4367constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4375setimpl(composerM4367constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i12 = ((54 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1484975185, "C199@9865L27,197@9750L431,206@10321L27,204@10206L429:PaymentCheckoutDialog.kt#untpz1");
            boolean z = PaymentCheckoutDialog$lambda$4($selectedPaymentMode$delegate) == 0;
            Modifier modifierWeight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            boolean z2 = z;
            ComposerKt.sourceInformationMarkerStart($composer, 1476121050, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$30$lambda$27$lambda$26($selectedPaymentMode$delegate);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ChipKt.FilterChip(z2, (Function0) objRememberedValue, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.getLambda$228414412$app(), modifierWeight$default, false, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8733getLambda$205292503$app(), null, null, null, null, null, null, $composer, 197040, 0, 4048);
            boolean z3 = PaymentCheckoutDialog$lambda$4($selectedPaymentMode$delegate) == 1;
            Modifier modifierWeight$default2 = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1476135642, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            Object objRememberedValue2 = $composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$30$lambda$29$lambda$28($selectedPaymentMode$delegate);
                    }
                };
                $composer.updateRememberedValue(objRememberedValue2);
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ChipKt.FilterChip(z3, (Function0) objRememberedValue2, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.getLambda$326022645$app(), modifierWeight$default2, false, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.getLambda$145505042$app(), null, null, null, null, null, null, $composer, 197040, 0, 4048);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (PaymentCheckoutDialog$lambda$4($selectedPaymentMode$delegate) == 0) {
                $composer.startReplaceGroup(1300520654);
                ComposerKt.sourceInformation($composer, "216@10855L40,217@10922L3558,214@10735L3745");
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2144cardColorsro_MJ88(Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, 0L, 0L, $composer, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(577387158, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43($upiVpa, $cleanAmount, $itemName, $context, $feeAmount, $studentEmail, $isBatch, $lastReceiptNo$delegate, $showReceiptDialog$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), $composer, 196614, 26);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1304234950);
                ComposerKt.sourceInformation($composer, "279@14654L40,280@14721L1806,277@14534L1993");
                CardKt.Card(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), null, CardDefaults.INSTANCE.m2144cardColorsro_MJ88(Color.INSTANCE.m5134getWhite0d7_KjU(), 0L, 0L, 0L, $composer, (CardDefaults.$stable << 12) | 6, 14), null, null, ComposableLambdaKt.rememberComposableLambda(-724598675, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$51($studentEmail, $itemName, $isBatch, $feeAmount, $context, $upiVpa, $transactionRefInput$delegate, $lastReceiptNo$delegate, $showReceiptDialog$delegate, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, $composer, 54), $composer, 196614, 26);
                $composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            MotionUtilsKt.ScrollToTopFAB(scrollStateRememberScrollState, PaddingKt.m819padding3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomEnd()), Dp.m7905constructorimpl(16)), 0, (CoroutineScope) null, $composer, 0, 12);
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

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$22(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C154@7162L143:PaymentCheckoutDialog.kt#untpz1");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1904131323, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:154)");
            }
            IconButtonKt.IconButton($onDismiss, null, false, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.getLambda$1449631651$app(), $composer, 1572864, 62);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$25(String $itemName, String $feeAmount, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Function0<ComposeUiNode> function1;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C180@8435L1006:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-408946543, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:180)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16));
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(6));
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
            ComposerKt.sourceInformationMarkerStart($composer, 1277592531, "C181@8615L10,181@8562L96,182@8728L10,182@8691L122,183@8846L569:PaymentCheckoutDialog.kt#untpz1");
            TextKt.m3072TextNvy7gAk("Enrollment Fee Summary", null, Color.INSTANCE.m5127getGray0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 390, 0, 131066);
            TextKt.m3072TextNvy7gAk($itemName, null, ColorKt.Color(4279179050L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 384, 0, 131066);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifierFillMaxWidth$default);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            int i6 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1827819609, "C188@9208L10,188@9156L74,189@9301L10,189@9263L122:PaymentCheckoutDialog.kt#untpz1");
            TextKt.m3072TextNvy7gAk("Total Amount Payable:", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodyMedium(), $composer, 6, 0, 131070);
            TextKt.m3072TextNvy7gAk($feeAmount, null, ColorKt.Color(4279673674L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleLarge(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 384, 0, 131066);
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

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$30$lambda$27$lambda$26(MutableIntState $selectedPaymentMode$delegate) {
        $selectedPaymentMode$delegate.setIntValue(0);
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$30$lambda$29$lambda$28(MutableIntState $selectedPaymentMode$delegate) {
        $selectedPaymentMode$delegate.setIntValue(1);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:29:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:30:0x02d0  */
    /* JADX WARN: Code duplicated, block: B:34:0x0380  */
    /* JADX WARN: Code duplicated, block: B:36:0x038a  */
    /* JADX WARN: Code duplicated, block: B:37:0x038e  */
    /* JADX WARN: Code duplicated, block: B:41:0x044e  */
    /* JADX WARN: Code duplicated, block: B:44:0x045c  */
    /* JADX WARN: Code duplicated, block: B:47:0x04c5  */
    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43(final String $upiVpa, final String $cleanAmount, final String $itemName, final Context $context, final String $feeAmount, final String $studentEmail, final boolean $isBatch, final MutableState $lastReceiptNo$delegate, final MutableState $showReceiptDialog$delegate, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Composer composer;
        Object obj;
        Composer composer2;
        boolean zChanged;
        Object objRememberedValue;
        Composer composer3;
        Object obj2;
        boolean zChanged2;
        Object objRememberedValue2;
        Composer composer4;
        Object obj3;
        boolean zChanged3;
        Object objRememberedValue3;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C218@10952L3502:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(577387158, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:218)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16));
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(10));
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
            ComposerKt.sourceInformationMarkerStart($composer, -347293229, "C219@11145L10,219@11080L106,226@11537L48,222@11274L33,228@11691L297,221@11220L768,239@12336L48,235@12076L30,241@12490L274,234@12022L742,252@13117L48,248@12852L35,254@13271L297,247@12798L770,260@13602L19,267@13993L48,263@13709L54,262@13655L769:PaymentCheckoutDialog.kt#untpz1");
            TextKt.m3072TextNvy7gAk("Tap your preferred UPI app to pay:", null, Color.INSTANCE.m5127getGray0d7_KjU(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getBodySmall(), $composer, 390, 0, 131066);
            Modifier modifierBounceClick$default = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
            ButtonColors buttonColorsM2124buttonColorsro_MJ88 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4278355143L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14);
            RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer, -565389439, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            boolean zChanged4 = $composer.changed($upiVpa) | $composer.changed($cleanAmount) | $composer.changed($itemName) | $composer.changedInstance($context) | $composer.changed($feeAmount);
            Object objRememberedValue4 = $composer.rememberedValue();
            if (!zChanged4) {
                composer = $composer;
                if (objRememberedValue4 != Composer.INSTANCE.getEmpty()) {
                    obj = objRememberedValue4;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer2 = composer;
                ButtonKt.Button((Function0) obj, modifierBounceClick$default, false, roundedCornerShapeM1127RoundedCornerShape0680j_4, buttonColorsM2124buttonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1385023248, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$33($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                Modifier modifierBounceClick$default2 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                ButtonColors buttonColorsM2124buttonColorsro_MJ89 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4284162486L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart(composer2, -565363778, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                zChanged = composer2.changed($feeAmount) | composer2.changed($upiVpa) | composer2.changed($cleanAmount) | composer2.changed($itemName) | composer2.changedInstance($context);
                objRememberedValue = composer2.rememberedValue();
                if (!zChanged) {
                    composer3 = composer2;
                    if (objRememberedValue != Composer.INSTANCE.getEmpty()) {
                        obj2 = objRememberedValue;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ButtonKt.Button((Function0) obj2, modifierBounceClick$default2, false, roundedCornerShapeM1127RoundedCornerShape0680j_5, buttonColorsM2124buttonColorsro_MJ89, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1833033703, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj4, Object obj5, Object obj6) {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$36($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                        }
                    }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                    Modifier modifierBounceClick$default3 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                    ButtonColors buttonColorsM2124buttonColorsro_MJ810 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                    RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_6 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart(composer2, -565338941, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                    zChanged2 = composer2.changed($feeAmount) | composer2.changed($upiVpa) | composer2.changed($cleanAmount) | composer2.changed($itemName) | composer2.changedInstance($context);
                    objRememberedValue2 = composer2.rememberedValue();
                    if (!zChanged2) {
                        composer4 = composer2;
                        if (objRememberedValue2 != Composer.INSTANCE.getEmpty()) {
                            obj3 = objRememberedValue2;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ButtonKt.Button((Function0) obj3, modifierBounceClick$default3, false, roundedCornerShapeM1127RoundedCornerShape0680j_6, buttonColorsM2124buttonColorsro_MJ810, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1157984888, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda13
                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj4, Object obj5, Object obj6) {
                                return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$39($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                            }
                        }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                        DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer2, 0, 7);
                        Modifier modifierBounceClick$default4 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                        ButtonColors buttonColorsM2124buttonColorsro_MJ811 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279673674L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                        RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_7 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                        ComposerKt.sourceInformationMarkerStart(composer2, -565311498, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                        zChanged3 = composer2.changed($studentEmail) | composer2.changed($itemName) | composer2.changed($isBatch) | composer2.changed($feeAmount) | composer2.changedInstance($context);
                        objRememberedValue3 = composer2.rememberedValue();
                        if (!zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                                }
                            };
                            composer2.updateRememberedValue(objRememberedValue3);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ButtonKt.Button((Function0) objRememberedValue3, modifierBounceClick$default4, false, roundedCornerShapeM1127RoundedCornerShape0680j_7, buttonColorsM2124buttonColorsro_MJ811, null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8729getLambda$145963817$app(), composer2, C.ENCODING_PCM_32BIT, 484);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composer4 = composer2;
                    }
                    obj3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda12
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$38$lambda$37($upiVpa, $cleanAmount, $itemName, $context, $feeAmount);
                        }
                    };
                    composer4.updateRememberedValue(obj3);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ButtonKt.Button((Function0) obj3, modifierBounceClick$default3, false, roundedCornerShapeM1127RoundedCornerShape0680j_6, buttonColorsM2124buttonColorsro_MJ810, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1157984888, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj4, Object obj5, Object obj6) {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$39($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                        }
                    }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                    DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer2, 0, 7);
                    Modifier modifierBounceClick$default5 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                    ButtonColors buttonColorsM2124buttonColorsro_MJ812 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279673674L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                    RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_8 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart(composer2, -565311498, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                    zChanged3 = composer2.changed($studentEmail) | composer2.changed($itemName) | composer2.changed($isBatch) | composer2.changed($feeAmount) | composer2.changedInstance($context);
                    objRememberedValue3 = composer2.rememberedValue();
                    if (!zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ButtonKt.Button((Function0) objRememberedValue3, modifierBounceClick$default5, false, roundedCornerShapeM1127RoundedCornerShape0680j_8, buttonColorsM2124buttonColorsro_MJ812, null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8729getLambda$145963817$app(), composer2, C.ENCODING_PCM_32BIT, 484);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
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
                obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$35$lambda$34($upiVpa, $cleanAmount, $itemName, $context, $feeAmount);
                    }
                };
                composer3.updateRememberedValue(obj2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.Button((Function0) obj2, modifierBounceClick$default2, false, roundedCornerShapeM1127RoundedCornerShape0680j_5, buttonColorsM2124buttonColorsro_MJ89, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1833033703, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$36($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                Modifier modifierBounceClick$default6 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                ButtonColors buttonColorsM2124buttonColorsro_MJ813 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_9 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart(composer2, -565338941, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                zChanged2 = composer2.changed($feeAmount) | composer2.changed($upiVpa) | composer2.changed($cleanAmount) | composer2.changed($itemName) | composer2.changedInstance($context);
                objRememberedValue2 = composer2.rememberedValue();
                if (!zChanged2) {
                    composer4 = composer2;
                    if (objRememberedValue2 != Composer.INSTANCE.getEmpty()) {
                        obj3 = objRememberedValue2;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ButtonKt.Button((Function0) obj3, modifierBounceClick$default6, false, roundedCornerShapeM1127RoundedCornerShape0680j_9, buttonColorsM2124buttonColorsro_MJ813, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1157984888, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj4, Object obj5, Object obj6) {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$39($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                        }
                    }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                    DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer2, 0, 7);
                    Modifier modifierBounceClick$default7 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                    ButtonColors buttonColorsM2124buttonColorsro_MJ814 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279673674L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                    RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_10 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart(composer2, -565311498, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                    zChanged3 = composer2.changed($studentEmail) | composer2.changed($itemName) | composer2.changed($isBatch) | composer2.changed($feeAmount) | composer2.changedInstance($context);
                    objRememberedValue3 = composer2.rememberedValue();
                    if (!zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ButtonKt.Button((Function0) objRememberedValue3, modifierBounceClick$default7, false, roundedCornerShapeM1127RoundedCornerShape0680j_10, buttonColorsM2124buttonColorsro_MJ814, null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8729getLambda$145963817$app(), composer2, C.ENCODING_PCM_32BIT, 484);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer4 = composer2;
                }
                obj3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$38$lambda$37($upiVpa, $cleanAmount, $itemName, $context, $feeAmount);
                    }
                };
                composer4.updateRememberedValue(obj3);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.Button((Function0) obj3, modifierBounceClick$default6, false, roundedCornerShapeM1127RoundedCornerShape0680j_9, buttonColorsM2124buttonColorsro_MJ813, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1157984888, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$39($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer2, 0, 7);
                Modifier modifierBounceClick$default8 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                ButtonColors buttonColorsM2124buttonColorsro_MJ815 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279673674L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_11 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart(composer2, -565311498, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                zChanged3 = composer2.changed($studentEmail) | composer2.changed($itemName) | composer2.changed($isBatch) | composer2.changed($feeAmount) | composer2.changedInstance($context);
                objRememberedValue3 = composer2.rememberedValue();
                if (!zChanged3) {
                    objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.Button((Function0) objRememberedValue3, modifierBounceClick$default8, false, roundedCornerShapeM1127RoundedCornerShape0680j_11, buttonColorsM2124buttonColorsro_MJ815, null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8729getLambda$145963817$app(), composer2, C.ENCODING_PCM_32BIT, 484);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer = $composer;
            }
            obj = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$32$lambda$31($upiVpa, $cleanAmount, $itemName, $context, $feeAmount);
                }
            };
            $composer.updateRememberedValue(obj);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer2 = composer;
            ButtonKt.Button((Function0) obj, modifierBounceClick$default, false, roundedCornerShapeM1127RoundedCornerShape0680j_4, buttonColorsM2124buttonColorsro_MJ88, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1385023248, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$33($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
            Modifier modifierBounceClick$default9 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
            ButtonColors buttonColorsM2124buttonColorsro_MJ816 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4284162486L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
            RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_12 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart(composer2, -565363778, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            zChanged = composer2.changed($feeAmount) | composer2.changed($upiVpa) | composer2.changed($cleanAmount) | composer2.changed($itemName) | composer2.changedInstance($context);
            objRememberedValue = composer2.rememberedValue();
            if (!zChanged) {
                composer3 = composer2;
                if (objRememberedValue != Composer.INSTANCE.getEmpty()) {
                    obj2 = objRememberedValue;
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.Button((Function0) obj2, modifierBounceClick$default9, false, roundedCornerShapeM1127RoundedCornerShape0680j_12, buttonColorsM2124buttonColorsro_MJ816, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1833033703, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$36($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                Modifier modifierBounceClick$default10 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                ButtonColors buttonColorsM2124buttonColorsro_MJ817 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_13 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart(composer2, -565338941, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                zChanged2 = composer2.changed($feeAmount) | composer2.changed($upiVpa) | composer2.changed($cleanAmount) | composer2.changed($itemName) | composer2.changedInstance($context);
                objRememberedValue2 = composer2.rememberedValue();
                if (!zChanged2) {
                    composer4 = composer2;
                    if (objRememberedValue2 != Composer.INSTANCE.getEmpty()) {
                        obj3 = objRememberedValue2;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ButtonKt.Button((Function0) obj3, modifierBounceClick$default10, false, roundedCornerShapeM1127RoundedCornerShape0680j_13, buttonColorsM2124buttonColorsro_MJ817, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1157984888, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda13
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj4, Object obj5, Object obj6) {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$39($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                        }
                    }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                    DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer2, 0, 7);
                    Modifier modifierBounceClick$default11 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                    ButtonColors buttonColorsM2124buttonColorsro_MJ818 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279673674L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                    RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_14 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart(composer2, -565311498, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                    zChanged3 = composer2.changed($studentEmail) | composer2.changed($itemName) | composer2.changed($isBatch) | composer2.changed($feeAmount) | composer2.changedInstance($context);
                    objRememberedValue3 = composer2.rememberedValue();
                    if (!zChanged3) {
                        objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue3);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ButtonKt.Button((Function0) objRememberedValue3, modifierBounceClick$default11, false, roundedCornerShapeM1127RoundedCornerShape0680j_14, buttonColorsM2124buttonColorsro_MJ818, null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8729getLambda$145963817$app(), composer2, C.ENCODING_PCM_32BIT, 484);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composer4 = composer2;
                }
                obj3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$38$lambda$37($upiVpa, $cleanAmount, $itemName, $context, $feeAmount);
                    }
                };
                composer4.updateRememberedValue(obj3);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.Button((Function0) obj3, modifierBounceClick$default10, false, roundedCornerShapeM1127RoundedCornerShape0680j_13, buttonColorsM2124buttonColorsro_MJ817, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1157984888, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$39($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer2, 0, 7);
                Modifier modifierBounceClick$default12 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                ButtonColors buttonColorsM2124buttonColorsro_MJ819 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279673674L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_15 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart(composer2, -565311498, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                zChanged3 = composer2.changed($studentEmail) | composer2.changed($itemName) | composer2.changed($isBatch) | composer2.changed($feeAmount) | composer2.changedInstance($context);
                objRememberedValue3 = composer2.rememberedValue();
                if (!zChanged3) {
                    objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.Button((Function0) objRememberedValue3, modifierBounceClick$default12, false, roundedCornerShapeM1127RoundedCornerShape0680j_15, buttonColorsM2124buttonColorsro_MJ819, null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8729getLambda$145963817$app(), composer2, C.ENCODING_PCM_32BIT, 484);
                ComposerKt.sourceInformationMarkerEnd(composer2);
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
            obj2 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$35$lambda$34($upiVpa, $cleanAmount, $itemName, $context, $feeAmount);
                }
            };
            composer3.updateRememberedValue(obj2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ButtonKt.Button((Function0) obj2, modifierBounceClick$default9, false, roundedCornerShapeM1127RoundedCornerShape0680j_12, buttonColorsM2124buttonColorsro_MJ816, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1833033703, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$36($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
            Modifier modifierBounceClick$default13 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
            ButtonColors buttonColorsM2124buttonColorsro_MJ8110 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
            RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_16 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart(composer2, -565338941, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            zChanged2 = composer2.changed($feeAmount) | composer2.changed($upiVpa) | composer2.changed($cleanAmount) | composer2.changed($itemName) | composer2.changedInstance($context);
            objRememberedValue2 = composer2.rememberedValue();
            if (!zChanged2) {
                composer4 = composer2;
                if (objRememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    obj3 = objRememberedValue2;
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.Button((Function0) obj3, modifierBounceClick$default13, false, roundedCornerShapeM1127RoundedCornerShape0680j_16, buttonColorsM2124buttonColorsro_MJ8110, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1157984888, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$39($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
                DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer2, 0, 7);
                Modifier modifierBounceClick$default14 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
                ButtonColors buttonColorsM2124buttonColorsro_MJ8111 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279673674L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
                RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_17 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart(composer2, -565311498, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
                zChanged3 = composer2.changed($studentEmail) | composer2.changed($itemName) | composer2.changed($isBatch) | composer2.changed($feeAmount) | composer2.changedInstance($context);
                objRememberedValue3 = composer2.rememberedValue();
                if (!zChanged3) {
                    objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ButtonKt.Button((Function0) objRememberedValue3, modifierBounceClick$default14, false, roundedCornerShapeM1127RoundedCornerShape0680j_17, buttonColorsM2124buttonColorsro_MJ8111, null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8729getLambda$145963817$app(), composer2, C.ENCODING_PCM_32BIT, 484);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer4 = composer2;
            }
            obj3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$38$lambda$37($upiVpa, $cleanAmount, $itemName, $context, $feeAmount);
                }
            };
            composer4.updateRememberedValue(obj3);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ButtonKt.Button((Function0) obj3, modifierBounceClick$default13, false, roundedCornerShapeM1127RoundedCornerShape0680j_16, buttonColorsM2124buttonColorsro_MJ8110, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(1157984888, true, new Function3() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$39($feeAmount, (RowScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, composer2, 54), composer2, C.ENCODING_PCM_32BIT, 484);
            DividerKt.m2399HorizontalDivider9IZ8Weo(null, 0.0f, 0L, composer2, 0, 7);
            Modifier modifierBounceClick$default15 = MotionUtilsKt.bounceClick$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, null, 3, null);
            ButtonColors buttonColorsM2124buttonColorsro_MJ8112 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279673674L), 0L, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 14);
            RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_18 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart(composer2, -565311498, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            zChanged3 = composer2.changed($studentEmail) | composer2.changed($itemName) | composer2.changed($isBatch) | composer2.changed($feeAmount) | composer2.changedInstance($context);
            objRememberedValue3 = composer2.rememberedValue();
            if (!zChanged3) {
                objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ButtonKt.Button((Function0) objRememberedValue3, modifierBounceClick$default15, false, roundedCornerShapeM1127RoundedCornerShape0680j_18, buttonColorsM2124buttonColorsro_MJ8112, null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8729getLambda$145963817$app(), composer2, C.ENCODING_PCM_32BIT, 484);
            ComposerKt.sourceInformationMarkerEnd(composer2);
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

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$32$lambda$31(String $upiVpa, String $cleanAmount, String $itemName, Context $context, String $feeAmount) {
        PaymentCheckoutDialog$launchUpiIntent($upiVpa, $cleanAmount, $itemName, $context, $feeAmount, "Google Pay");
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$33(String $feeAmount, RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C229@11729L67,230@11833L39,231@11909L45:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1385023248, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:229)");
            }
            IconKt.m2520Iconww6aTOc(AccountBalanceWalletKt.getAccountBalanceWallet(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Pay " + $feeAmount + " with Google Pay / GPay", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$35$lambda$34(String $upiVpa, String $cleanAmount, String $itemName, Context $context, String $feeAmount) {
        PaymentCheckoutDialog$launchUpiIntent($upiVpa, $cleanAmount, $itemName, $context, $feeAmount, "PhonePe");
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$36(String $feeAmount, RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C242@12528L54,243@12619L39,244@12695L35:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1833033703, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:242)");
            }
            IconKt.m2520Iconww6aTOc(PaymentKt.getPayment(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Pay " + $feeAmount + " with PhonePe", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$38$lambda$37(String $upiVpa, String $cleanAmount, String $itemName, Context $context, String $feeAmount) {
        PaymentCheckoutDialog$launchUpiIntent($upiVpa, $cleanAmount, $itemName, $context, $feeAmount, "Paytm / BHIM");
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$39(String $feeAmount, RowScope Button, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(Button, "$this$Button");
        ComposerKt.sourceInformation($composer, "C255@13309L59,256@13405L39,257@13481L53:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1157984888, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:255)");
            }
            IconKt.m2520Iconww6aTOc(SendToMobileKt.getSendToMobile(Icons.INSTANCE.getDefault()), (String) null, (Modifier) null, 0L, $composer, 48, 12);
            SpacerKt.Spacer(SizeKt.m870width3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(8)), $composer, 6);
            TextKt.m3072TextNvy7gAk("Pay " + $feeAmount + " with Paytm / BHIM / Amazon Pay", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$43$lambda$42$lambda$41$lambda$40(String $studentEmail, String $itemName, boolean $isBatch, String $feeAmount, Context $context, MutableState $lastReceiptNo$delegate, MutableState $showReceiptDialog$delegate) {
        PaymentCheckoutDialog$completeEnrollmentAndSavePayment($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate, "UPI-DIRECT-APP");
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$51(final String $studentEmail, final String $itemName, final boolean $isBatch, final String $feeAmount, final Context $context, final String $upiVpa, final MutableState $transactionRefInput$delegate, final MutableState $lastReceiptNo$delegate, final MutableState $showReceiptDialog$delegate, ColumnScope Card, Composer $composer, int $changed) {
        Function0<ComposeUiNode> function0;
        Object obj;
        Intrinsics.checkNotNullParameter(Card, "$this$Card");
        ComposerKt.sourceInformation($composer, "C281@14751L1750:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-724598675, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:281)");
            }
            Modifier modifierM819padding3ABfNKs = PaddingKt.m819padding3ABfNKs(Modifier.INSTANCE, Dp.m7905constructorimpl(16));
            Arrangement.Vertical verticalM689spacedBy0680j_4 = Arrangement.INSTANCE.m689spacedBy0680j_4(Dp.m7905constructorimpl(10));
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
            ComposerKt.sourceInformationMarkerStart($composer, 773235188, "C282@14934L10,282@14879L78,283@15009L192,283@14990L211,289@15371L28,287@15235L499,299@16038L48,297@15822L82,296@15768L703:PaymentCheckoutDialog.kt#untpz1");
            TextKt.m3072TextNvy7gAk("Academy Official UPI ID:", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getLabelMedium(), $composer, 6, 0, 131070);
            SelectionContainerKt.SelectionContainer(null, ComposableLambdaKt.rememberComposableLambda(1402399668, true, new Function2() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$51$lambda$50$lambda$44($upiVpa, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 48, 1);
            String strPaymentCheckoutDialog$lambda$1 = PaymentCheckoutDialog$lambda$1($transactionRefInput$delegate);
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 579146579, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            Object objRememberedValue = $composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$51$lambda$50$lambda$46$lambda$45($transactionRefInput$delegate, (String) obj3);
                    }
                };
                $composer.updateRememberedValue(obj2);
                obj = obj2;
            } else {
                obj = objRememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(strPaymentCheckoutDialog$lambda$1, (Function1<? super String, Unit>) obj, modifierFillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8732getLambda$2028819491$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8730getLambda$1735206178$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 14156208, 12582912, 0, 8257336);
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ButtonColors buttonColorsM2124buttonColorsro_MJ88 = ButtonDefaults.INSTANCE.m2124buttonColorsro_MJ88(ColorKt.Color(4279522093L), 0L, 0L, 0L, $composer, (ButtonDefaults.$stable << 12) | 6, 14);
            RoundedCornerShape roundedCornerShapeM1127RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1127RoundedCornerShape0680j_4(Dp.m7905constructorimpl(12));
            ComposerKt.sourceInformationMarkerStart($composer, 579161065, "CC(remember):PaymentCheckoutDialog.kt#9igjgp");
            boolean zChanged = $composer.changed($studentEmail) | $composer.changed($itemName) | $composer.changed($isBatch) | $composer.changed($feeAmount) | $composer.changedInstance($context);
            Object objRememberedValue2 = $composer.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function0() { // from class: com.example.sushikhasacademyapp.ui.PaymentCheckoutDialogKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return PaymentCheckoutDialogKt.PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$51$lambda$50$lambda$49$lambda$48($transactionRefInput$delegate, $studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate);
                    }
                };
                $composer.updateRememberedValue(obj3);
                objRememberedValue2 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.Button((Function0) objRememberedValue2, modifierFillMaxWidth$default2, false, roundedCornerShapeM1127RoundedCornerShape0680j_4, buttonColorsM2124buttonColorsro_MJ88, null, null, null, null, ComposableSingletons$PaymentCheckoutDialogKt.INSTANCE.m8728getLambda$1298707129$app(), $composer, 805306416, 484);
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

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$51$lambda$50$lambda$44(String $upiVpa, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C284@15082L10,284@15047L120:PaymentCheckoutDialog.kt#untpz1");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1402399668, $changed, -1, "com.example.sushikhasacademyapp.ui.PaymentCheckoutDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PaymentCheckoutDialog.kt:284)");
            }
            TextKt.m3072TextNvy7gAk($upiVpa, null, ColorKt.Color(4278355143L), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m7341copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer, MaterialTheme.$stable).getTitleMedium(), 0L, 0L, FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), $composer, 384, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$51$lambda$50$lambda$46$lambda$45(MutableState $transactionRefInput$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $transactionRefInput$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit PaymentCheckoutDialog$lambda$56$lambda$55$lambda$54$lambda$53$lambda$52$lambda$51$lambda$50$lambda$49$lambda$48(MutableState $transactionRefInput$delegate, String $studentEmail, String $itemName, boolean $isBatch, String $feeAmount, Context $context, MutableState $lastReceiptNo$delegate, MutableState $showReceiptDialog$delegate) {
        String strPaymentCheckoutDialog$lambda$1 = PaymentCheckoutDialog$lambda$1($transactionRefInput$delegate);
        if (StringsKt.isBlank(strPaymentCheckoutDialog$lambda$1)) {
            strPaymentCheckoutDialog$lambda$1 = "MANUAL-REF";
        }
        PaymentCheckoutDialog$completeEnrollmentAndSavePayment($studentEmail, $itemName, $isBatch, $feeAmount, $context, $lastReceiptNo$delegate, $showReceiptDialog$delegate, strPaymentCheckoutDialog$lambda$1);
        return Unit.INSTANCE;
    }
}
