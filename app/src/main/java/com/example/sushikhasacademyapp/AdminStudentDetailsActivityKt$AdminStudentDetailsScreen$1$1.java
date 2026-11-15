package com.example.sushikhasacademyapp;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.MutableState;
import com.cloudinary.provisioning.Account;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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

/* JADX INFO: compiled from: AdminStudentDetailsActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1", f = "AdminStudentDetailsActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<String> $attended$delegate;
    final /* synthetic */ MutableState<String> $dob$delegate;
    final /* synthetic */ MutableState<String> $email$delegate;
    final /* synthetic */ MutableState<String> $exam$delegate;
    final /* synthetic */ MutableState<Boolean> $isVerifiedState$delegate;
    final /* synthetic */ MutableState<String> $loadedStudentId$delegate;
    final /* synthetic */ MutableState<String> $name$delegate;
    final /* synthetic */ MutableState<String> $school$delegate;
    final /* synthetic */ MutableState<StudentUser> $selected$delegate;
    final /* synthetic */ MutableState<String> $studentClass$delegate;
    final /* synthetic */ MutableState<List<StudentUser>> $students$delegate;
    final /* synthetic */ MutableState<String> $working$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1(MutableState<List<StudentUser>> mutableState, MutableState<StudentUser> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<String> mutableState7, MutableState<String> mutableState8, MutableState<String> mutableState9, MutableState<String> mutableState10, MutableState<String> mutableState11, MutableState<Boolean> mutableState12, Continuation<? super AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1> continuation) {
        super(2, continuation);
        this.$students$delegate = mutableState;
        this.$selected$delegate = mutableState2;
        this.$loadedStudentId$delegate = mutableState3;
        this.$name$delegate = mutableState4;
        this.$email$delegate = mutableState5;
        this.$studentClass$delegate = mutableState6;
        this.$school$delegate = mutableState7;
        this.$dob$delegate = mutableState8;
        this.$exam$delegate = mutableState9;
        this.$attended$delegate = mutableState10;
        this.$working$delegate = mutableState11;
        this.$isVerifiedState$delegate = mutableState12;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1(this.$students$delegate, this.$selected$delegate, this.$loadedStudentId$delegate, this.$name$delegate, this.$email$delegate, this.$studentClass$delegate, this.$school$delegate, this.$dob$delegate, this.$exam$delegate, this.$attended$delegate, this.$working$delegate, this.$isVerifiedState$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                CollectionReference collectionReferenceCollection = FirebaseFirestore.getInstance().collection(Account.USERS);
                final MutableState<List<StudentUser>> mutableState = this.$students$delegate;
                final MutableState<StudentUser> mutableState2 = this.$selected$delegate;
                final MutableState<String> mutableState3 = this.$loadedStudentId$delegate;
                final MutableState<String> mutableState4 = this.$name$delegate;
                final MutableState<String> mutableState5 = this.$email$delegate;
                final MutableState<String> mutableState6 = this.$studentClass$delegate;
                final MutableState<String> mutableState7 = this.$school$delegate;
                final MutableState<String> mutableState8 = this.$dob$delegate;
                final MutableState<String> mutableState9 = this.$exam$delegate;
                final MutableState<String> mutableState10 = this.$attended$delegate;
                final MutableState<String> mutableState11 = this.$working$delegate;
                final MutableState<Boolean> mutableState12 = this.$isVerifiedState$delegate;
                collectionReferenceCollection.addSnapshotListener(new EventListener() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1$$ExternalSyntheticLambda0
                    @Override // com.google.firebase.firestore.EventListener
                    public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                        AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1.invokeSuspend$lambda$5(mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState9, mutableState10, mutableState11, mutableState12, (QuerySnapshot) obj, firebaseFirestoreException);
                    }
                });
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Code duplicated, block: B:56:0x0104  */
    static final void invokeSuspend$lambda$5(MutableState $students$delegate, MutableState $selected$delegate, MutableState $loadedStudentId$delegate, MutableState $name$delegate, MutableState $email$delegate, MutableState $studentClass$delegate, MutableState $school$delegate, MutableState $dob$delegate, MutableState $exam$delegate, MutableState $attended$delegate, MutableState $working$delegate, final MutableState $isVerifiedState$delegate, QuerySnapshot snapshot, FirebaseFirestoreException firebaseFirestoreException) {
        List listEmptyList;
        StudentUser nextSelected;
        Object next;
        Iterable documents;
        String str = null;
        if (snapshot == null || (documents = snapshot.getDocuments()) == null) {
            listEmptyList = null;
        } else {
            Iterable<DocumentSnapshot> iterable = documents;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (DocumentSnapshot documentSnapshot : iterable) {
                String id = documentSnapshot.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                String string = documentSnapshot.getString(HintConstants.AUTOFILL_HINT_USERNAME);
                if (string == null) {
                    String string2 = documentSnapshot.getString("email");
                    string = string2 != null ? StringsKt.substringBefore$default(string2, "@", str, 2, str) : str;
                    if (string == null) {
                        string = "Student";
                    }
                }
                String string3 = documentSnapshot.getString("email");
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
                String str2 = string5;
                String string6 = documentSnapshot.getString("dob");
                if (string6 == null) {
                    string6 = "";
                }
                String str3 = string6;
                String string7 = documentSnapshot.getString("exam");
                String str4 = string7 != null ? string7 : "";
                Long l = documentSnapshot.getLong("attendedDays");
                long jLongValue = 0;
                long jLongValue2 = l != null ? l.longValue() : 0L;
                Long l2 = documentSnapshot.getLong("totalWorkingDays");
                if (l2 != null) {
                    jLongValue = l2.longValue();
                }
                arrayList.add(new StudentUser(id, string, string3, string4, str2, str3, str4, jLongValue2, jLongValue));
                str = null;
            }
            listEmptyList = (List) arrayList;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        $students$delegate.setValue(listEmptyList);
        StudentUser studentUserAdminStudentDetailsScreen$lambda$4 = AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$4($selected$delegate);
        if (studentUserAdminStudentDetailsScreen$lambda$4 != null) {
            Iterator it = AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$1($students$delegate).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(((StudentUser) next).getId(), studentUserAdminStudentDetailsScreen$lambda$4.getId()));
            nextSelected = (StudentUser) next;
            if (nextSelected == null) {
                nextSelected = (StudentUser) CollectionsKt.firstOrNull(AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$1($students$delegate));
            }
        } else {
            nextSelected = (StudentUser) CollectionsKt.firstOrNull(AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$1($students$delegate));
        }
        $selected$delegate.setValue(nextSelected);
        if (nextSelected == null || Intrinsics.areEqual(AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$37($loadedStudentId$delegate), nextSelected.getId())) {
            return;
        }
        StudentUser student = nextSelected;
        $loadedStudentId$delegate.setValue(student.getId());
        $name$delegate.setValue(student.getUsername());
        $email$delegate.setValue(student.getEmail());
        $studentClass$delegate.setValue(student.getClassLevel());
        $school$delegate.setValue(student.getSchool());
        $dob$delegate.setValue(student.getDob());
        $exam$delegate.setValue(student.getExam());
        $attended$delegate.setValue(String.valueOf(student.getAttendedDays()));
        $working$delegate.setValue(String.valueOf(student.getTotalWorkingDays()));
        Task<DocumentSnapshot> task = FirebaseFirestore.getInstance().collection(Account.USERS).document(student.getId()).get();
        final Function1 function1 = new Function1() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1.invokeSuspend$lambda$5$lambda$3($isVerifiedState$delegate, (DocumentSnapshot) obj);
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: com.example.sushikhasacademyapp.AdminStudentDetailsActivityKt$AdminStudentDetailsScreen$1$1$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                function1.invoke(obj);
            }
        });
    }

    static final Unit invokeSuspend$lambda$5$lambda$3(MutableState $isVerifiedState$delegate, DocumentSnapshot doc) {
        if (doc != null && doc.exists()) {
            Boolean bool = doc.getBoolean("isVerified");
            AdminStudentDetailsActivityKt.AdminStudentDetailsScreen$lambda$35($isVerifiedState$delegate, bool != null ? bool.booleanValue() : true);
        }
        return Unit.INSTANCE;
    }
}
