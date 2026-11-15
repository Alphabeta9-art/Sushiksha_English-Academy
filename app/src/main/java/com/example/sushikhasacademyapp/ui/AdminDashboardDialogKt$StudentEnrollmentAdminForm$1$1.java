package com.example.sushikhasacademyapp.ui;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.MutableState;
import com.cloudinary.provisioning.Account;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AdminDashboardDialog.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1", f = "AdminDashboardDialog.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $isLoadingStudents$delegate;
    final /* synthetic */ MutableState<FirestoreStudent> $selectedStudent$delegate;
    final /* synthetic */ MutableState<List<FirestoreStudent>> $studentsList$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1(MutableState<Boolean> mutableState, MutableState<List<FirestoreStudent>> mutableState2, MutableState<FirestoreStudent> mutableState3, Continuation<? super AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1> continuation) {
        super(2, continuation);
        this.$isLoadingStudents$delegate = mutableState;
        this.$studentsList$delegate = mutableState2;
        this.$selectedStudent$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1(this.$isLoadingStudents$delegate, this.$studentsList$delegate, this.$selectedStudent$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    CollectionReference collectionReferenceCollection = FirebaseFirestore.getInstance().collection(Account.USERS);
                    final MutableState<Boolean> mutableState = this.$isLoadingStudents$delegate;
                    final MutableState<List<FirestoreStudent>> mutableState2 = this.$studentsList$delegate;
                    final MutableState<FirestoreStudent> mutableState3 = this.$selectedStudent$delegate;
                    Intrinsics.checkNotNull(collectionReferenceCollection.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.ui.AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1$$ExternalSyntheticLambda0
                        @Override // com.google.firebase.firestore.EventListener
                        public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                            AdminDashboardDialogKt$StudentEnrollmentAdminForm$1$1.invokeSuspend$lambda$1(mutableState, mutableState2, mutableState3, (QuerySnapshot) obj, firebaseFirestoreException);
                        }
                    }));
                    break;
                } catch (Exception e) {
                    AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$255(this.$isLoadingStudents$delegate, false);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final void invokeSuspend$lambda$1(MutableState $isLoadingStudents$delegate, MutableState $studentsList$delegate, MutableState $selectedStudent$delegate, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$255($isLoadingStudents$delegate, false);
        if (snapshot != null && !snapshot.isEmpty()) {
            Iterable documents = snapshot.getDocuments();
            Intrinsics.checkNotNullExpressionValue(documents, "getDocuments(...)");
            Iterable<DocumentSnapshot> iterable = documents;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (DocumentSnapshot documentSnapshot : iterable) {
                String id = documentSnapshot.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                String string = documentSnapshot.getString(HintConstants.AUTOFILL_HINT_USERNAME);
                if (string == null) {
                    String string2 = documentSnapshot.getString("email");
                    string = string2 != null ? StringsKt.substringBefore$default(string2, "@", (String) null, 2, (Object) null) : null;
                    if (string == null) {
                        string = "Student";
                    }
                } else {
                    iterable = iterable;
                }
                String string3 = documentSnapshot.getString("email");
                String str = "";
                if (string3 == null) {
                    string3 = "";
                }
                String string4 = documentSnapshot.getString("classLevel");
                if (string4 == null) {
                    string4 = "";
                }
                String string5 = documentSnapshot.getString("school");
                if (string5 == null) {
                    string5 = "";
                }
                String str2 = string3;
                String string6 = documentSnapshot.getString("exam");
                if (string6 != null) {
                    str = string6;
                }
                arrayList.add(new FirestoreStudent(id, string, str2, string4, string5, str));
                iterable = iterable;
            }
            $studentsList$delegate.setValue((List) arrayList);
            if (AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$257($selectedStudent$delegate) != null || AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$251($studentsList$delegate).isEmpty()) {
                return;
            }
            $selectedStudent$delegate.setValue((FirestoreStudent) CollectionsKt.first(AdminDashboardDialogKt.StudentEnrollmentAdminForm$lambda$251($studentsList$delegate)));
        }
    }
}
