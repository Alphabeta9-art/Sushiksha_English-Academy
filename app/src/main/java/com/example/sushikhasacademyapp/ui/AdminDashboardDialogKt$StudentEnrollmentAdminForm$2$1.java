package com.example.sushikhasacademyapp.ui;

import androidx.compose.runtime.MutableState;
import com.cloudinary.provisioning.Account;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AdminDashboardDialog.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1", f = "AdminDashboardDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Set<String>> $selectedBatches$delegate;
    final /* synthetic */ MutableState<Set<String>> $selectedCourses$delegate;
    final /* synthetic */ MutableState<FirestoreStudent> $selectedStudent$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1(MutableState<FirestoreStudent> mutableState, MutableState<Set<String>> mutableState2, MutableState<Set<String>> mutableState3, Continuation<? super AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1> continuation) {
        super(2, continuation);
        this.$selectedStudent$delegate = mutableState;
        this.$selectedCourses$delegate = mutableState2;
        this.$selectedBatches$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1(this.$selectedStudent$delegate, this.$selectedCourses$delegate, this.$selectedBatches$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                FirestoreStudent firestoreStudentStudentEnrollmentAdminForm$lambda$257 = AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$257(this.$selectedStudent$delegate);
                String sId = firestoreStudentStudentEnrollmentAdminForm$lambda$257 != null ? firestoreStudentStudentEnrollmentAdminForm$lambda$257.getId() : null;
                String str = sId;
                if (!(str == null || StringsKt.isBlank(str))) {
                    try {
                        Task<DocumentSnapshot> task = FirebaseFirestore.getInstance().collection(Account.USERS).document(sId).get();
                        final MutableState<Set<String>> mutableState = this.$selectedCourses$delegate;
                        final MutableState<Set<String>> mutableState2 = this.$selectedBatches$delegate;
                        final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1.invokeSuspend$lambda$2(mutableState, mutableState2, (DocumentSnapshot) obj);
                            }
                        };
                        Intrinsics.checkNotNull(task.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$StudentEnrollmentAdminForm$2$1$$ExternalSyntheticLambda1
                            @Override // com.google.android.gms.tasks.OnSuccessListener
                            public final void onSuccess(Object obj) {
                                function1.invoke(obj);
                            }
                        }));
                        break;
                    } catch (Exception e) {
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final Unit invokeSuspend$lambda$2(MutableState $selectedCourses$delegate, MutableState $selectedBatches$delegate, DocumentSnapshot doc) {
        Collection cList;
        if (doc != null && doc.exists()) {
            Object obj = doc.get("enrolledCourses");
            List bList = null;
            Iterable iterable = obj instanceof List ? (List) obj : null;
            if (iterable != null) {
                Collection arrayList = new ArrayList();
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    Object next = it.next();
                    String string = next != null ? next.toString() : null;
                    if (string != null) {
                        arrayList.add(string);
                    }
                }
                cList = (List) arrayList;
            } else {
                cList = null;
            }
            if (cList == null) {
                cList = CollectionsKt.emptyList();
            }
            Object obj2 = doc.get("enrolledBatches");
            Iterable iterable2 = obj2 instanceof List ? (List) obj2 : null;
            if (iterable2 != null) {
                Collection arrayList2 = new ArrayList();
                Iterator it2 = iterable2.iterator();
                while (it2.hasNext()) {
                    Object next2 = it2.next();
                    String string2 = next2 != null ? next2.toString() : null;
                    if (string2 != null) {
                        arrayList2.add(string2);
                    }
                }
                bList = (List) arrayList2;
            }
            if (bList == null) {
                bList = CollectionsKt.emptyList();
            }
            $selectedCourses$delegate.setValue(CollectionsKt.toSet(cList));
            $selectedBatches$delegate.setValue(CollectionsKt.toSet(bList));
        }
        return Unit.INSTANCE;
    }
}
